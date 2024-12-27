package com.api.fileupload.fileupload.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.fileupload.fileupload.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
