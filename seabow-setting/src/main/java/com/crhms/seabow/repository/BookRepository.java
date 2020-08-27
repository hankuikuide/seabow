package com.crhms.seabow.repository;

import com.crhms.seabow.Entity.Book;
import com.crhms.seabow.Entity.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
