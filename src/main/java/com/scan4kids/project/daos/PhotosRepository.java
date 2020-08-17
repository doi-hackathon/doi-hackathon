package com.scan4kids.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photo, Long> {

    Photo findByDescription(String description);
}
