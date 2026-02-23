package com.fis.hrmservice.infra.storage;

import com.fis.hrmservice.domain.port.output.user.FileStoragePort;
import com.intern.hub.library.common.exception.ConflictDataException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class R2FileStorageAdapter implements FileStoragePort {

  private final S3Client s3Client;

  @Value("${cloudflare.r2.bucket-name}")
  private String bucketName;

  @Value("${cloudflare.r2.public-base-url}")
  private String publicBaseUrl;

  @Override
  public String upload(byte[] content, String fileName, String contentType, String folder) {

    if (content == null || content.length == 0) {
      throw new ConflictDataException("File content is empty");
    }

    String safeFileName = fileName.replaceAll("\\s+", "_");
    String key = folder + "/" + UUID.randomUUID() + "-" + safeFileName;

    try {

      s3Client.putObject(
          PutObjectRequest.builder().bucket(bucketName).key(key).contentType(contentType).build(),
          RequestBody.fromBytes(content));

      log.info("Uploaded file to R2: {}", key);

    } catch (Exception e) {
      log.error("Upload to R2 failed", e);
      throw new ConflictDataException("Upload file failed");
    }

    return publicBaseUrl + "/" + key;
  }
}
