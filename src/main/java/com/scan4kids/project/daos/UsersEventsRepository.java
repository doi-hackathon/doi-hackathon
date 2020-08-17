package com.scan4kids.project.daos;

import com.scan4kids.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersEventsRepository extends JpaRepository<UsersEvents, Long> {
    List<UsersEvents> findAllByUserId(long id);
    byte countByEventAndUser(Event event, User user);
    // HQL
//    select * from users_events where is_volunteer is true and event_id = 1;
    @Query("from UsersEvents as ue where ue.isVolunteer = true and ue.event.id = ?1")
    List<UsersEvents> findAllVolunteeredEvents(long id);

}
