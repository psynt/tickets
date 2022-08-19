package com.entity;

public enum TicketType {
	SL(50), AC(100);
	
	private TicketType(int i) {
		this.refundAmount = i;
	}

	public final int refundAmount;
}
