package org.example.collectivepurchases.services.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    String uploadFile(MultipartFile file, String bucketName) throws IOException;

    String deleteFileByUrl(List<String> urls) throws IOException;
}

