package org.nonreactive.repository;

import org.nonreactive.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM nonitems i, pg_sleep(2)", nativeQuery = true)
    List<Item> findAllWithDelay();
}