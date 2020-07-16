package com.scan4kids.project.daos;

import com.scan4kids.project.models.UsersEvents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersEventsRepository extends JpaRepository<UsersEvents, Long> {
    List<UsersEvents> findAllByUserId(long id);
}
