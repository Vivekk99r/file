package com.api.fileupload.fileupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private Long id;
    private String photoFilename;
    private String photoFileType;

    private String resumeFilename;
    private String resumeFileType;
}
