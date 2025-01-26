package org.nonreactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nonreactive.model.Item;
import org.nonreactive.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConnectionPoolingTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void testConnectionPooling() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                try {
                    Item item = new Item();
                    item.setName("Test Item");
                    item.setPrice(100.0);
                    Item savedItem = itemService.createItem(item);
                    assertNotNull(savedItem);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
