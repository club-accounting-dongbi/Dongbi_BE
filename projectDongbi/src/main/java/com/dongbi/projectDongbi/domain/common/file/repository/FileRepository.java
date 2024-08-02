package com.dongbi.projectDongbi.domain.common.file.repository;

import com.dongbi.projectDongbi.domain.common.file.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    Optional<File> findByFilePath(String filePath);
}
