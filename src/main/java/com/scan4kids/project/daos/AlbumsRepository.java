package com.scan4kids.project.daos;

import com.scan4kids.project.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumsRepository extends JpaRepository <Album, Long> {
}
