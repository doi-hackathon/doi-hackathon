package com.scan4kids.project.daos;

import com.scan4kids.project.models.UsersEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersEventsRepository extends JpaRepository<UsersEvents, Long> {

}
