package com.scan4kids.project.daos;

import com.scan4kids.project.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradesRepository extends JpaRepository<Grade, Long> {
}
