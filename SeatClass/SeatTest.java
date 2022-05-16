package com.SeatBookingProject.SeatClass;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dao.SeatService;
import com.model.Seat;


import junit.framework.Assert;

@SpringBootTest
class SeatTest {
	@Autowired
	SeatService service;

	@Test
	void testAdd() {
		Seat s=new Seat();
		s.setSeatNo(100);
		s.setSeatType("AC");
		s.setOffice("First");
		s.setFloor(12);
		s.setLocation("Bangalore");
		s.setDuration(5.3F);
		service.add(s);
		
		Seat s1=service.find(s.getSeatId());
		Assert.assertEquals("AC",s.getSeatType());
		Assert.assertEquals(100, s.getSeatNo());
		Assert.assertEquals("First", s.getOffice());
		Assert.assertEquals(12,s.getFloor());
		Assert.assertEquals("Bangalore",s.getLocation());
		Assert.assertEquals(5.3F, s.getDuration());
		
	}

	@Test
	void validateSeat() {
		Seat s=new Seat();
		s.setSeatNo(200);
		try {
		service.add(s);
		}
		catch(Exception e) {
			System.out.println(e);
		}	
		s.setOffice("Normal");
		try {
		service.add(s);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		Seat s3=service.find(s.getSeatId());
		Assert.assertNotNull(s3);
		Assert.assertNotNull(s3);
	}
	
	@Test
	void testFind() {
		Seat s4=new Seat();
		s4.setSeatId(4);
		try
		{
			service.add(s4);
		}
		catch (Exception e){
			System.out.println(e);
			
		}
	 service.find(s4.getSeatId());
		
		
		
	}
	

	@Test
	void testFindAll() {
		Seat s=new Seat();
		s.setSeatNo(10);
		s.setSeatType("AC");
		s.setOffice("Second");
		s.setFloor(3);
		s.setLocation("Mumbai");
		s.setDuration(4.8F);
		service.add(s);
		Seat s1=new Seat();
		s1.setSeatNo(11);
		s1.setSeatType("Normal");
		s1.setOffice("Third");
		s1.setFloor(11);
		s1.setLocation("Goa");
		s1.setDuration(2.3F);
		service.add(s1);
		List<Seat> seatlist=service.findAll();
		
		Assert.assertEquals(seatlist.get(0).getLocation(),"Bangalore");
		Assert.assertEquals(seatlist.get(1).getLocation(),"Mumbai");
		
		
	}

	@Test
	void testUpdate() {
		
		Seat sc=new Seat();
		sc.setSeatNo(19);
		sc.setSeatType("Normal");
		sc.setOffice("fifth");
		sc.setFloor(7);
		sc.setLocation("kerala");
		service.add(sc);
		sc.setSeatType("AC");
		
		Assert.assertEquals(true, service.update(sc));
	}

	@Test
	void testDelete() {
		
		service.delete(6);
		
		Seat s2=service.find(6);
		
		Assert.assertNull(s2);
	}

}
