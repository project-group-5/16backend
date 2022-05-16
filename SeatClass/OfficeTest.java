package com.SeatBookingProject.SeatClass;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dao.OfficeService;
import com.model.Office;

import junit.framework.Assert;
@SpringBootTest
class OfficeTest {
	@Autowired
	OfficeService service;

	@Test
	void testAdd() {
		 
		Office office=new Office();
		
		office.setFloorDeatils(5);
		office.setLocation("Bangalore rural");
		office.setAvailableSpaces(5);
		office.setSeatingCapacity(6);
		
		service.add(office);
		
		Office office_to_be_tested=service.find(office.getLocation());
		
		Assert.assertEquals(5, office_to_be_tested.getFloorDeatils() );
		Assert.assertEquals("Bangalore rural", office_to_be_tested.getLocation());
		Assert.assertEquals(5, office_to_be_tested.getAvailableSpaces());
		Assert.assertEquals(6, office_to_be_tested.getSeatingCapacity());

		
		
		
		
	}

	@Test
	void testFind() {
		Office officedetails=new Office();
		officedetails.setLocation("hdhhddggfdf");
		try
		{
			service.add(officedetails);
		}
		catch(Exception e){
			System.out.println(e);
			
		}
		Office officedetails1=service.find(officedetails.getLocation());
		Assert.assertNull(officedetails1);
		
	}

	@Test
	void testFindAll() {
		Office office=new Office();
		
		office.setAvailableSpaces(4);
		office.setFloorDeatils(6);
		office.setLocation("hoskote kalkunte");
		
		office.setSeatingCapacity(6);
		service.add(office);
        
		Office office1=new Office();
		
		office1.setAvailableSpaces(9);
		office1.setFloorDeatils(7);
		office1.setLocation("Bangalore north");
		office1.setSeatingCapacity(4);
		service.add(office1);
		
		List<Office> officelist=service.findAll();
		Assert.assertEquals(officelist.get(0).getLocation(),"Bangalore rural" );
		Assert.assertEquals(officelist.get(1).getLocation(), "hoskote kalkunte");
		Assert.assertEquals(officelist.get(2).getLocation(), "Bangalore north");
	}

	@Test
	void testUpdate() {
		Office office=new Office();
		
		office.setAvailableSpaces(3);
		office.setFloorDeatils(2);
		office.setLocation("bangalore north");
		
		office.setSeatingCapacity(8);
		service.add(office);
        office.setFloorDeatils(8);
        
        Assert.assertEquals(true, service.update(office));
	}

    @Test
	void testDelete() {
    	
    	service.delete("hoskote kalkunte");
    	Office office=service.find("hoskote kalkunte");
    	Assert.assertNull(office);
	}

}

