package com.scan4kids.project.daos;

import com.scan4kids.project.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository <Event, Long > {

    Event findByTitle(String title);
}
