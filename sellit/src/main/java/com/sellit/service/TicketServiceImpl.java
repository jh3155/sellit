package com.sellit.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.SellitApplication;
import com.sellit.dao.TicketDao;
import com.sellit.persistence.Ticket;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;

	@Override
	public Ticket save(final Ticket ticket) {

		if (ticket.getCreatedBy() == null) {
			ticket.setCreatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
			ticket.setCreatedDatetime(LocalDateTime.now());
		}

		ticket.setUpdatedBy(SellitApplication.getApplicationContainer().getLoggedInEmployee());
		ticket.setUpdatedDatetime(LocalDateTime.now());

		return ticketDao.save(ticket);
	}

}
