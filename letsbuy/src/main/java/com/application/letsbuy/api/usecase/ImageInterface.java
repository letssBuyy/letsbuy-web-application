package com.application.letsbuy.api.usecase;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageInterface {

    List<String> insertImages(Long id,List<MultipartFile> images);
}
