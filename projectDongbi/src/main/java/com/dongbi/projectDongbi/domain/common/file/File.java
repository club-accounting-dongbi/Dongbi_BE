package com.dongbi.projectDongbi.domain.common.file;

import com.dongbi.projectDongbi.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String filePath;


    public File(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
