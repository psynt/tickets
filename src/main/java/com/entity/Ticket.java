package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

public record Ticket(int ticketId, Train train, User user, TicketType type){
	private static int id = 0;
	public static int getId() { return ++id; }
}