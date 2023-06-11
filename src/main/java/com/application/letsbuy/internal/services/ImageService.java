package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.BlobStorageAdapter;
import com.application.letsbuy.api.usecase.ImageInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ImageService implements ImageInterface {

    private final BlobStorageAdapter blobStorageAdapter;

    @Override
    public Boolean delete(String url) {
        try {
            blobStorageAdapter.deleteBlob(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String upload(MultipartFile file) {
        try {
            String blobName = UUID.randomUUID() + file.getOriginalFilename();
            blobStorageAdapter.uploadBlob(blobName, file.getInputStream(), file.getSize());
            return "https://letsbuy.blob.core.windows.net/images/" + blobName;
        } catch (IOException e) {
            return "https://letsbuy.blob.core.windows.net/images/12942784-icone-de-imagem-quebrada-isolado-em-um-fundo-branco-nenhum-simbolo-de-imagem-para-aplicativos-da-web-e-moveis-gratis-vetor.jpg";
        }
    }
}
