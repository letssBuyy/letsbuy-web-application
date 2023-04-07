package com.application.letsbuy.internal.controllers;

import com.application.letsbuy.internal.services.AnnouncementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @ApiOperation("Whatsapp link generator")
    @GetMapping("/generateWppLink/{id}")
    public String generateWppLink(@PathVariable Long id){
        return announcementService.generateWppLink(id);
    }
}
