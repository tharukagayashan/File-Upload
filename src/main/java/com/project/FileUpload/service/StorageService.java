package com.project.FileUpload.service;

import com.project.FileUpload.entity.DocumentData;
import com.project.FileUpload.repository.DocumentDataRepository;
import com.project.FileUpload.util.DocumentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private DocumentDataRepository documentDataRepository;

    public String uploadDocument(MultipartFile file) throws IOException {
        DocumentData documentData = documentDataRepository.save(DocumentData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .docData(DocumentUtils.compressDoc(file.getBytes()))
                .build()
        );
        if (documentData != null){
            return "File uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadDocument(String name) throws NoSuchFieldException {
        Optional<DocumentData> optDocument = documentDataRepository.findByName(name);
        if (optDocument.isPresent()){
            byte[] document = DocumentUtils.decompressDoc(optDocument.get().getDocData());
            return document;
        }else {
            throw new NoSuchFieldException("File not found!!!");
        }
    }

}