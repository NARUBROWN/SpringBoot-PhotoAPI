package com.appcenter.photoapi.service;

import com.appcenter.photoapi.domain.ImageData;
import com.appcenter.photoapi.repository.ImgRepository;
import com.appcenter.photoapi.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final ImgRepository imgRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        imgRepository.save(
                ImageData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .build());
        return "file uploaded successfully : " + file.getOriginalFilename();
    }

    public byte[] downloadImage(String fileName) {
        ImageData imageData = imgRepository.findByName(fileName)
                .orElseThrow(RuntimeException::new);

        return ImageUtils.decompressImage(imageData.getImageData());
    }
}
