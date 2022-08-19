package com.dao;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.Train;

import lombok.val;

@Repository
public class Trains {
	
	private Map<Integer, Train> trains = new HashMap<>();
	
	public int create(String from, String to, LocalTime time) {
		val t = new Train(Train.getId(), from, to, time);
		trains.put(t.trainId(), t);
		return t.trainId();
	}
	
	public Train get(int trainId) {
		return trains.get(trainId);
	}
	
	{
		create("Bucuresti", "Brasov", LocalTime.of(12, 0));
		create("Bucuresti", "Sinaia", LocalTime.of(8, 0));
		create("Bucuresti", "Braila", LocalTime.of(18, 0));
	}
	
	@Override
	public String toString() {
		return trains.toString();
	}

}
