package com.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.AppConfig;
import com.entity.UserType;
import com.service.TicketManagement;

/**
 * This class is part of com.main package having main method and provides
 * correct interface for calling methods of TicketManagement class. Use loop to
 * switch between methods.
 */
public class MainApp {

	public static final ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(AppConfig.class);

	private final TicketManagement ticketService;
	final private Scanner in = new Scanner(System.in);
	
	public MainApp() {
		ticketService = CONTEXT.getBean(TicketManagement.class);
	}

	private static void presentActions() {
		System.out.println("What would you like to do?");
		System.out.println("1 - view train details");
		System.out.println("2 - buy train ticket");
		System.out.println("3 - refund train ticket");
	}

	private void login() {
		String user;
		String pass;
		do {
			user = "";
			pass = "";
			System.out.println("Welcome to our train managment application. Please Log in:");
			System.out.print("User:");
			user = in.next();
			System.out.print("Password:");
			pass = in.next();
		} while (ticketService.login(user, pass) == UserType.UNAUTHENTICATED);
	}

	private void start() {
		login();
		while (true) {
			presentActions();
			int option = in.nextInt();
			switch (option) {
			case 1:
				ticketService.showTrains();
				break;
			case 2:
				System.out.println("Please specify the id of the train you would like to book for:");
				int trainId = in.nextInt();
				int ticketId = ticketService.bookTicket(trainId);
				System.out.println("Ticket successfully booked. Id is: " + ticketId);
				break;
			case 3:
				System.out.println("Please specify the id of the ticekt you would like to refund:");
				int refundId = in.nextInt();
				int refundAmount = ticketService.cancelTicket(refundId);
				System.out.println("Ticket has been cancelled and " + refundAmount
						+ " ruppees have been returned to your account.");
				break;
			default:
				System.out.println("Unknown option. Please try again");
			}
		}
	}

	public static void main(String[] args) {
		final MainApp app = new MainApp();
		app.start();
	}

}
