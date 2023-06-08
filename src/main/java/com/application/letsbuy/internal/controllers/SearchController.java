package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.AllAdversimentsAndLikeDtoResponse;
import com.application.letsbuy.internal.services.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/searches")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<Page<AllAdversimentsAndLikeDtoResponse>> searchAdversiments(@RequestParam Optional<Long> idUser, @RequestParam String title, Pageable pageable) {

        List<AllAdversimentsAndLikeDtoResponse> results = searchService.searchAdversiments(idUser, title);

        if(results.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        Page<AllAdversimentsAndLikeDtoResponse> pages = new PageImpl<>(results, pageable, results.size());
        return ResponseEntity.ok(pages);
    }
}
