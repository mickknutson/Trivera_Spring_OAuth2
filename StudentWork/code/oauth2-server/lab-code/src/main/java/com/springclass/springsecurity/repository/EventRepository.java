package com.springclass.springsecurity.repository;

import com.springclass.springsecurity.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

} // The End...
