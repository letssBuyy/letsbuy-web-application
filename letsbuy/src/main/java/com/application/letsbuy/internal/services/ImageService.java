package com.application.letsbuy.internal.services;

import com.application.letsbuy.api.usecase.ImageInterface;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@AllArgsConstructor
@Service
public class ImageService implements ImageInterface {

    @Override
    public Boolean delete(String url){
        try {
            String constr = "AccountName=letsbuy;AccountKey=//6vf+/Fci6X1ecJTMMzSHS+9cy/GvyXegw8VBbV3f+HFyn0JlXVvXDX8xSW8UQt2AvEtKOp1OJq+AStrWtzEw==;" +
                    "EndpointSuffix=core.windows.net;DefaultEndpointsProtocol=https;";
            BlobContainerClient container = new BlobContainerClientBuilder()
                    .connectionString(constr)
                    .containerName("images")
                    .buildClient();
            String blobName = url.replaceAll("https://letsbuy.blob.core.windows.net/images/","");
            BlobClient blob = container.getBlobClient(blobName);
            blob.delete();
            return true;
        } catch (Exception e){
            return false;
        }
    }
    @Override
    public String upload(MultipartFile file) {
        try {
            String constr = "AccountName=letsbuy;AccountKey=//6vf+/Fci6X1ecJTMMzSHS+9cy/GvyXegw8VBbV3f+HFyn0JlXVvXDX8xSW8UQt2AvEtKOp1OJq+AStrWtzEw==;" +
                    "EndpointSuffix=core.windows.net;DefaultEndpointsProtocol=https;";
            BlobContainerClient container = new BlobContainerClientBuilder()
                    .connectionString(constr)
                    .containerName("images")
                    .buildClient();
            String blobName = UUID.randomUUID() + file.getOriginalFilename();
            BlobClient blob = container.getBlobClient(blobName);
            blob.upload(file.getInputStream(), file.getSize(), true);
            return "https://letsbuy.blob.core.windows.net/images/" + blobName;
        } catch (IOException e){
            return "https://letsbuy.blob.core.windows.net/images/12942784-icone-de-imagem-quebrada-isolado-em-um-fundo-branco-nenhum-simbolo-de-imagem-para-aplicativos-da-web-e-moveis-gratis-vetor.jpg";
        }
    }
}
