package com.sellit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellit.persistence.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {

}
