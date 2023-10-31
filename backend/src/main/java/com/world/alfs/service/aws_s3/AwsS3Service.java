package com.world.alfs.service.aws_s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
        File uploadFile = convert(multipartFile)  // 파일 변환할 수 없으면 에러
                    .orElseThrow(() -> new IllegalArgumentException("error: MultipartFile -> File convert fail"));

        log.debug("uploadFiles");
        return upload(uploadFile, dirName);
    }

    public String upload(File uploadFile, String filePath) {
        log.debug("upload 시작");
        String fileName = filePath + "/" + UUID.randomUUID() + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        deleteNewFile(uploadFile);
        log.debug("upload 끝");
        return uploadImageUrl;
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        log.debug("putS3 시작");
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        log.debug("putS3 끝");
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    private void deleteNewFile(File targetFile) {
        log.debug("deleteNewFile 시작");
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
        log.debug("deleteNewFile 끝");
    }

    // 파일 업로드 하기
    public Optional<File> convert(MultipartFile file) throws IOException {
        log.debug("convert 시작");
        File convertFile = new File(System.getProperty("user.dir") + "/img/" +file.getOriginalFilename());

        if (!convertFile.getParentFile().exists()) {
            if (convertFile.getParentFile().mkdirs()) {
                log.debug("상위 디렉토리 생성 완료");
            } else {
                log.debug("상위 디렉토리 생성 실패");
            }
        }else{
            log.debug("현재 경로: " + System.getProperty("user.dir"));
            log.debug("convert 폴더 이미 존재함");
        }

        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                log.debug("fos에 write");
                fos.write(file.getBytes());
            }catch (Exception e){
                log.debug("convert 에러 catch");
                log.debug(e.getMessage());
                e.printStackTrace();
            }
            return Optional.of(convertFile);
        }
        log.debug("convert 끝");
        return Optional.empty();
    }
}