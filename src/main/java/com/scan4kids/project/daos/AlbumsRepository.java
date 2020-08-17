package com.scan4kids.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumsRepository extends JpaRepository <Album, Long> {

    Album findByTitle(String title);
}
