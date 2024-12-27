package com.api.fileupload.fileupload.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.api.fileupload.fileupload.entity.FileEntity;
import com.api.fileupload.fileupload.model.FileDTO;
import com.api.fileupload.fileupload.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String uploadFiles(@RequestParam("photo") MultipartFile photo, 
                              @RequestParam("resume") MultipartFile resume) {
        try {
            FileEntity savedFile = fileService.saveFiles(photo, resume);
            return "Files uploaded successfully with ID: " + savedFile.getId();
        } catch (Exception e) {
            return "Files upload failed: " + e.getMessage();
        }
    }

    @GetMapping("/{id}/photo")
    public String downloadPhoto(@PathVariable Long id, HttpServletResponse response) {
        Optional<FileEntity> fileEntityOpt = fileService.getFile(id);

        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            try {
                // response.setContentType(MediaType.parseMediaType(fileEntity.getPhotoFileType()).toString());
                // response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                //         "attachment; filename=\"" + fileEntity.getPhotoFilename() + "\"");

                response.getOutputStream().write(fileEntity.getPhotoData());
                response.getOutputStream().flush();
                return "Photo download started.";
            } catch (IOException e) {
                return "Error while downloading the photo: " + e.getMessage();
            }
        } else {
            return "Photo not found.";
        }
    }

    @GetMapping("/{id}/resume")
    public String downloadResume(@PathVariable Long id, HttpServletResponse response) {
        Optional<FileEntity> fileEntityOpt = fileService.getFile(id);

        if (fileEntityOpt.isPresent()) {
            FileEntity fileEntity = fileEntityOpt.get();
            try {
                // response.setContentType(MediaType.parseMediaType(fileEntity.getResumeFileType()).toString());
                // response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                //         "attachment; filename=\"" + fileEntity.getResumeFilename() + "\"");

                response.getOutputStream().write(fileEntity.getResumeData());
                response.getOutputStream().flush();
                return "Resume download started.";
            } catch (IOException e) {
                return "Error while downloading the resume: " + e.getMessage();
            }
        } else {
            return "Resume not found.";
        }
    }

    @GetMapping("/{id}/metadata")
    public String getFileMetadata(@PathVariable Long id) {
        Optional<FileDTO> fileMetadataOpt = fileService.getFileMetadata(id);

        if (fileMetadataOpt.isPresent()) {
            FileDTO metadata = fileMetadataOpt.get();
            return "File Metadata - Photo Name: " + metadata.getPhotoFilename() +
                   ", Resume Name: " + metadata.getResumeFilename();
        } else {
            return "File metadata not found.";
        }
    }
}
