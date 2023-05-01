package com.project.FileUpload.repository;

import com.project.FileUpload.entity.DocumentData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentDataRepository extends JpaRepository<DocumentData, Integer> {

    Optional<DocumentData> findByName(String name);

}