package com.fis.hrmservice.domain.port.output.user;

public interface FileStoragePort {
  String upload(byte[] content, String fileName, String contentType, String folder);
}
