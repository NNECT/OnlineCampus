package com.education.onlinecampus.repository;

import com.education.onlinecampus.data.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
}
