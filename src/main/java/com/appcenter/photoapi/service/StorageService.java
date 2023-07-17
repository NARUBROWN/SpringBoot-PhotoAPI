package com.appcenter.photoapi.service;

import com.appcenter.photoapi.domain.FileData;
import com.appcenter.photoapi.domain.ImageData;
import com.appcenter.photoapi.repository.FileDataRepository;
import com.appcenter.photoapi.repository.ImgRepository;
import com.appcenter.photoapi.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final ImgRepository imgRepository;
    private final FileDataRepository fileDataRepository;

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


    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String FOLDER_PATH = "/Users/kim-wonjeong/";
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(
                FileData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .filePath(filePath)
                        .build()
        );

        file.transferTo(new File(filePath));

        return "파일이 성공적으로 업로드 됨; 파일 경로: " + filePath;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        FileData fileData = fileDataRepository.findByName(fileName)
                .orElseThrow(RuntimeException::new);

        String filePath = fileData.getFilePath();

        return Files.readAllBytes(new File(filePath).toPath());
    }





}
