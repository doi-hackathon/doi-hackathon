package com.scan4kids.project.daos;

import com.scan4kids.project.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photo, Long> {

}
