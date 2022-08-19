package com.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.Ticket;
import com.entity.TicketType;
import com.entity.Train;
import com.entity.User;

import lombok.val;

@Repository
public class Tickets {

	private Map<Integer, Ticket> tickets = new HashMap<>();
	
	public int create(Train train, User user, TicketType type) {
		final Ticket t = new Ticket(Ticket.getId(), train, user, type);
		tickets.put(t.ticketId(), t);
		return t.ticketId();
	}
	
	public Ticket get(int ticketId) {
		return tickets.get(ticketId);
	}
	
	public Ticket delete(int ticketId) {
		return tickets.remove(ticketId);
	}

}
