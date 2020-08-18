package com.scan4kids.project.daos;

import com.scan4kids.project.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
