package com.crhms.seabow.repository;

import com.crhms.seabow.Entity.Subtitle;
import com.crhms.seabow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtitleRepository extends JpaRepository<Subtitle, Long> {
}

