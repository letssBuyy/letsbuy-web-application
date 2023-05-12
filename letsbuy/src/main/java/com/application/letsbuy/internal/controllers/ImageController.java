package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.dto.UserDtoResponse;
import com.application.letsbuy.internal.entities.Adversiment;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.ImageService;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/images")
public class ImageController{
    private final UserService userService;
    private final AdversimentService adversimentService;

    @PostMapping("/adversiment/{id}")
    public ResponseEntity<AdversimentDtoResponse> uploadImages(
            @PathVariable Long id,
            @RequestParam MultipartFile img1,
            @RequestParam MultipartFile img2,
            @RequestParam MultipartFile img3,
            @RequestParam MultipartFile img4,
            @RequestParam MultipartFile img5,
            @RequestParam MultipartFile img6
    ){
        List<MultipartFile> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        images.add(img3);
        images.add(img4);
        images.add(img5);
        images.add(img6);
        return ResponseEntity.status(200).body(
                new AdversimentDtoResponse(adversimentService.insertImages(id,images))
        );
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> uploadImagesUser(
            @PathVariable Long id,
            @RequestParam MultipartFile img
    ){
        return ResponseEntity.status(200).body(
                new UserDtoResponse(userService.insertProfileImage(id,img))
        );
    }
}
