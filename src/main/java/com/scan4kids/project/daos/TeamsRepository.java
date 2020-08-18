package com.scan4kids.project.daos;


import com.scan4kids.project.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Team, Long> {



}
