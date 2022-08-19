package com.entity;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;

public record Train(int trainId, String from, String to, LocalTime time) {
	
	private static int id = 0;
	public static int getId() { return ++id; }

}
