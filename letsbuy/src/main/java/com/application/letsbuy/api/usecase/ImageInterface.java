package com.application.letsbuy.api.usecase;

import org.springframework.web.multipart.MultipartFile;

public interface ImageInterface {

    String upload(MultipartFile file);
    Boolean delete(String url);
}
