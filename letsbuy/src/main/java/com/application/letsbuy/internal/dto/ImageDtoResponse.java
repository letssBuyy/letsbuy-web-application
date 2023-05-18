package com.application.letsbuy.internal.dto;

import com.application.letsbuy.internal.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDtoResponse {
    private String url;

    public static List<ImageDtoResponse> convert(List<Image> images) {
        return images.stream().map(ImageDtoResponse::new).collect(Collectors.toList());
    }

    public ImageDtoResponse(Image img) {
        this.url = img.getUrl();
    }
}
