package com.appcenter.photoapi.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FileData")
@Getter
@NoArgsConstructor
public class FileData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String filePath;

    @Builder
    public FileData(String name, String type, String filePath) {
        this.name = name;
        this.type = type;
        this.filePath = filePath;
    }
}
