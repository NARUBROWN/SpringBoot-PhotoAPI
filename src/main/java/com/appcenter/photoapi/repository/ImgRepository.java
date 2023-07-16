package com.appcenter.photoapi.repository;

import com.appcenter.photoapi.domain.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImgRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String fileName);
}
