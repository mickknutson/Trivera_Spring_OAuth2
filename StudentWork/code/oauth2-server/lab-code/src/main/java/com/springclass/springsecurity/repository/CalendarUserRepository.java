package com.springclass.springsecurity.repository;

import com.springclass.springsecurity.domain.CalendarUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer> {

    CalendarUser findByEmail(String email);

} // The End...
