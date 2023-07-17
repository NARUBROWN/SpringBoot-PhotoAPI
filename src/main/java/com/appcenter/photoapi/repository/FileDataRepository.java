package com.appcenter.photoapi.repository;

import com.appcenter.photoapi.domain.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String fileName);
}
