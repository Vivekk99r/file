package com.api.fileupload.fileupload.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.fileupload.fileupload.Repository.FileRepository;
import com.api.fileupload.fileupload.entity.FileEntity;
import com.api.fileupload.fileupload.model.FileDTO;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Optional<FileEntity> getFile(Long fileId) {
        return fileRepository.findById(fileId);
    }

    @Override
    public FileEntity saveFiles(MultipartFile photo, MultipartFile resume) throws Exception {
        String photoFilename = photo.getOriginalFilename();
        String photoFileType = photo.getContentType();
        byte[] photoData = photo.getBytes();

        String resumeFilename = resume.getOriginalFilename();
        String resumeFileType = resume.getContentType();
        byte[] resumeData = resume.getBytes();

        FileEntity fileEntity = FileEntity.builder()
                .photoFilename(photoFilename)
                .photoFileType(photoFileType)
                .photoData(photoData)
                .resumeFilename(resumeFilename)
                .resumeFileType(resumeFileType)
                .resumeData(resumeData)
                .build();

        return fileRepository.save(fileEntity);
    }


    @Override
    public Optional<FileDTO> getFileMetadata(Long fileId) {
        return fileRepository.findById(fileId)
                .map(file -> FileDTO.builder()
                        .id(file.getId())
                        .photoFilename(file.getPhotoFilename())
                        .photoFileType(file.getPhotoFileType())
                        .resumeFilename(file.getResumeFilename())
                        .resumeFileType(file.getResumeFileType())
                        .build());
    }
}
