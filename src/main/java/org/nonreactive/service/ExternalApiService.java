package org.nonreactive.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Object> getPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        Object[] posts = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(posts);
    }
}
