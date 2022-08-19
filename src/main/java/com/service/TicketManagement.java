package com.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.dao.Tickets;
import com.dao.Trains;
import com.dao.Users;
import com.entity.TicketType;
import com.entity.User;
import com.entity.UserType;

/**
 * This class is part of com.service package here you have to autowire TicketDao
 * class and declare required methods in order to call TicketDao class methods.
 * 
 */

@Service
public class TicketManagement {
	
	private final Trains trains;
	private final Users users;
	private final Tickets tickets;
	private User loggedInUser;
	
	public TicketManagement(Trains trains, Users users, Tickets tickets) {
		this.trains = trains;
		this.users = users;
		this.tickets = tickets;
	}
	
	
	/**
	 * Validate user. If valid, print all trains
	 * 
	 * @param unm username
	 * @param pwd password
	 */
	public UserType login(String unm, String pwd) {
		final User user = users.get(unm);
		if(user == null || !users.validatePassword(user, pwd))
			return UserType.UNAUTHENTICATED;
		this.loggedInUser = user;
		return user.type();
	}

	/**
	 * Print details for all trains
	 */
	public void showTrains() {
		System.out.println(trains);
	}

	/**
	 * In this method generate a unique ticket id and take input for class(SL/AC)
	 * and number of passengers from the keyboard. Other information will be
	 * retrieved from Train_details table based on train number. Check if class
	 * equals AC then fare will be increased by 200 otherwise fare is unchanged
	 * calculate total fare and insert booked ticket details along with other
	 * details inside Ticket_Details table and also print on the console.
	 * 
	 * @param trainno
	 */
	public int bookTicket(int trainno) {
		int input = 42;
		while(input != 1 && input !=2) {
			System.out.println("Please specify ticket type:");
			System.out.println("1 - SL");
			System.out.println("2 - AC");
			input = new Scanner(System.in).nextInt();
		}
		final TicketType type = input == 1 ? TicketType.SL : TicketType.AC;
		return tickets.create(trains.get(trainno), loggedInUser, type);
	}

	/**
	 * This method remove the booked ticket d from Ticket_Details table based on
	 * ticket number and prints the refund amount to the passenger. 
	 * The cancellation charge is applicable as below:
	 *     For SL class  50 rupee per passenger 
	 *     For AC class 100 rupee per passenger
	 * 
	 * @param ticketno
	 */
	public int cancelTicket(int ticketno) {
		return tickets.delete(ticketno).type().refundAmount;
	}


}
