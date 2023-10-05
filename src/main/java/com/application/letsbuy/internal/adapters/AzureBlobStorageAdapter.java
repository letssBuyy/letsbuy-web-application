package com.application.letsbuy.internal.adapters;

import com.application.letsbuy.api.usecase.BlobStorageAdapter;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class AzureBlobStorageAdapter implements BlobStorageAdapter {

    private final BlobContainerClient container;

    public AzureBlobStorageAdapter(@Value("${azure.storage.connectionString}") String connectionString,
                                   @Value("${azure.storage.containerName}") String containerName) {
        this.container = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();
    }

    @Override
    public void deleteBlob(String url) {
        String blobName = url.replaceAll("https://letsbuyblob.blob.core.windows.net/letsbuy-images/", "");
        BlobClient blob = container.getBlobClient(blobName);
        blob.delete();
    }

    @Override
    public void uploadBlob(String blobName, InputStream inputStream, long size) {
        BlobClient blob = container.getBlobClient(blobName);
        blob.upload(inputStream, size, true);
    }
}


