package com.linkedin.learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.linkedin.learning.entity.RoomEntity;
import com.linkedin.learning.repository.RoomRepository;

@Component
public class H2Bootstrap implements CommandLineRunner{

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Bootstrapping data----------->");
		
		roomRepository.save(new RoomEntity(101, "25"));
		roomRepository.save(new RoomEntity(102, "25"));
		roomRepository.save(new RoomEntity(201, "20"));
		roomRepository.save(new RoomEntity(202, "20"));
		roomRepository.save(new RoomEntity(301, "30"));
		roomRepository.save(new RoomEntity(302, "30"));
		
		Iterable<RoomEntity> itr = roomRepository.findAll();
		
		System.out.println("Printing data:");
		for (RoomEntity room : itr) {
			System.out.println(room.getId()+"_"+room.getRoomNumber()+"_"+room.getPrice());
		}
		System.out.println("<------- End Bootstrapping data:");
	}

	
	
}
