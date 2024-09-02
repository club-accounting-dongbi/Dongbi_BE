package com.dongbi.projectDongbi.domain.s3.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dongbi.projectDongbi.domain.s3.S3Properties;
import com.dongbi.projectDongbi.domain.s3.dto.UploadImageResponse;
import com.dongbi.projectDongbi.global.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3Client amazonS3Client;
    private final S3Properties s3Properties;
    private final String imagePath = "dongbi";

    @Transactional
    public UploadImageResponse uploadImage(MultipartFile file) throws IOException{
        String fileName = getFileName(file);
        ObjectMetadata objectMetadata = getObjectMetadata(file);

        amazonS3Client.putObject(
                s3Properties.getBucket(), fileName, file.getInputStream(), objectMetadata);
        return new UploadImageResponse(fileName);
    }

    public void deleteImage(String name){
        boolean isExist = amazonS3Client.doesObjectExist(s3Properties.getBucket(), name);

        if(isExist){
            amazonS3Client.deleteObject(s3Properties.getBucket(), name);
        }else{
            throw new DataNotFoundException();
        }
    }


    private String getFileName(MultipartFile image) {
        String originalFileName = image.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return  imagePath +  "/" + UUID.randomUUID() + extension;
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        return objectMetadata;
    }

}
