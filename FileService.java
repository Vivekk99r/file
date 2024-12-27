package com.api.fileupload.fileupload.services;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.api.fileupload.fileupload.entity.FileEntity;
import com.api.fileupload.fileupload.model.FileDTO;

public interface FileService {

    FileEntity saveFiles(MultipartFile photo, MultipartFile resume) throws Exception;

    Optional<FileDTO> getFileMetadata(Long fileId);

    Optional<FileEntity> getFile(Long fileId);
}
