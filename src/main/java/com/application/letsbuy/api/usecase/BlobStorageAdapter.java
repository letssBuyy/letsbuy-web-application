package com.application.letsbuy.api.usecase;

import java.io.InputStream;

public interface BlobStorageAdapter {
    void deleteBlob(String url);
    void uploadBlob(String blobName, InputStream inputStream, long size);
}

