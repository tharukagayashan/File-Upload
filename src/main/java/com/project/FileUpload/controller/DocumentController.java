package com.project.FileUpload.controller;

import com.project.FileUpload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDocument(@RequestParam("document") MultipartFile file) throws IOException {
        String uploadDoc = storageService.uploadDocument(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadDoc);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadDocument(@PathVariable String fileName) throws NoSuchFieldException {
        byte[] document = storageService.downloadDocument(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.ALL).body(document);
    }

}
