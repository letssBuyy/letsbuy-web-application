package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.dto.AdversimentDtoResponse;
import com.application.letsbuy.internal.dto.UserDtoResponse;
import com.application.letsbuy.internal.services.AdversimentService;
import com.application.letsbuy.internal.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<AdversimentDtoResponse> uploadImages(@PathVariable Long id, @RequestParam MultipartFile img1, @RequestParam MultipartFile img2, @RequestParam MultipartFile img3, @RequestParam MultipartFile img4, @RequestParam MultipartFile img5){
        List<MultipartFile> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        images.add(img3);
        images.add(img4);
        images.add(img5);
        return ResponseEntity.status(200).body(new AdversimentDtoResponse(adversimentService.insertImages(id,images)));
    }
    @PutMapping("/adversiment/{id}")
    @Transactional
    public ResponseEntity<AdversimentDtoResponse> updateImages(@PathVariable Long id, @RequestParam MultipartFile img1, @RequestParam MultipartFile img2, @RequestParam MultipartFile img3, @RequestParam MultipartFile img4, @RequestParam MultipartFile img5){
        List<MultipartFile> images = new ArrayList<>();
        images.add(img1);
        images.add(img2);
        images.add(img3);
        images.add(img4);
        images.add(img5);
        return ResponseEntity.status(200).body(
                new AdversimentDtoResponse(adversimentService.updateImages(id,images)));
    }
    @DeleteMapping("/adversiment")
    @Transactional
    public ResponseEntity<AdversimentDtoResponse> deleteImages(@RequestParam String url){
        this.adversimentService.deleteImage(url);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/user/{id}")
    @Transactional
    public ResponseEntity<UserDtoResponse> insertImageUser(@PathVariable Long id, @RequestParam MultipartFile img) {
        return ResponseEntity.status(200).body(new UserDtoResponse(userService.insertProfileImage(id,img)));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> deleteImageUser(@PathVariable Long id) {
        return ResponseEntity.status(200).body(new UserDtoResponse(userService.deleteProfileImage(id)));
    }
}
