package org.nonreactive.controller;

import org.nonreactive.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("/posts")
    public ResponseEntity<List<Object>> getPosts() {
        List<Object> posts = externalApiService.getPosts();
        return ResponseEntity.ok(posts);
    }
}