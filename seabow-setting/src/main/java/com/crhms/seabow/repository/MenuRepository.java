package com.crhms.seabow.repository;

import com.crhms.seabow.Entity.Menu;
import com.crhms.seabow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
