package com.crhms.seabow.repository;

import com.crhms.seabow.Entity.Book;
import com.crhms.seabow.Entity.Lession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessionRepository extends JpaRepository<Lession, Long> {
}

