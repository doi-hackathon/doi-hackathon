package com.scan4kids.project.daos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository <Event, Long > {

    Event findByTitle(String title);
}
