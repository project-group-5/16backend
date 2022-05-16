package com.SeatBookingProject.SeatClass;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.dao.UserProfileService;
import com.model.UserProfileDetails;

import junit.framework.Assert;
@SpringBootTest
class UserProfileTest {
  @Autowired
  UserProfileService service;
	@Test
	void testAdd() {
		UserProfileDetails profile=new UserProfileDetails();
		profile.setFirstname("Ranjitha");
		profile.setUseremail("Ranjitha1997@gmail.com");
		profile.setLastname("Ramesh");
		profile.setPassword("Ranji123");
		profile.setConpassword("Ranji123");
		profile.setDob("10/08/1997");
		profile.setMobileNo("9980763490");	
		service.add(profile);
		
		UserProfileDetails profile1=service.find(profile.getUseremail());
			Assert.assertEquals("Ranjitha", profile1.getFirstname());
		Assert.assertEquals("Ramesh", profile1.getLastname());
		Assert.assertEquals("Ranji123", profile1.getPassword());
		Assert.assertEquals("Ranji123", profile1.getConpassword());
		Assert.assertEquals("10/08/1997", profile1.getDob());
		Assert.assertEquals("9980763490", profile1.getMobileNo());
		
		
	}
	
	
	@Test
	void testFind() {
		UserProfileDetails profile4=new UserProfileDetails();
		profile4.setUseremail("hhhf@gmail.com");
		try
		{
			service.add(profile4);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		UserProfileDetails profile8=service.find(profile4.getUseremail());
		Assert.assertNotNull(profile8);
	}

	@Test
	void testFindAll() {
		UserProfileDetails profile5=new UserProfileDetails();
		profile5.setUseremail("vinod@gmail.com");
		profile5.setFirstname("Vinod");
		profile5.setLastname("Kumar");
		profile5.setDob("11/09/1997");
		profile5.setMobileNo("9987628990")	;
		profile5.setPassword("Vinod123");
		profile5.setConpassword("Vinod123");
		service.add(profile5);
		
		
		List<UserProfileDetails> profilelist=service.findAll();
		Assert.assertEquals(profilelist.get(0).getFirstname(),"Ranjitha");
		Assert.assertEquals(profilelist.get(0).getLastname(),"Ramesh");
	
		
	}

	@Test
	void testUpdate() {
	UserProfileDetails uc=new UserProfileDetails();
	uc.setFirstname("Nitin");
	uc.setUseremail("Nitin@gmail.com");
	uc.setLastname("Mohurle");
	uc.setPassword("Nitin123");
	uc.setConpassword("Nitin123");
	uc.setDob("07/08/2000");
	uc.setMobileNo("9017543790");
	service.add(uc);
	uc.setLastname("kumar");
	Assert.assertEquals(true, service.update(uc));
			
	}

	@Test
	void testDelete() {
		service.delete("hhhf@gmail.com");
		UserProfileDetails profile=service.find("hhhf@gmail.com");
		Assert.assertNull(profile);
		
		
		
		
	}

}
