package net.codejava.contact.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import net.codejava.contact.model.Contact;

class ContactDAOTest {
	private DriverManagerDataSource dataSource;
	private ContactDAO dao;

	@BeforeEach
	void setupBeforeEach() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contactdb?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("Jack?Maths");
		
		dao = new ContactDAOImpl(dataSource);
	}
	
	@Test
	void testSave() {
		
		Contact contact = new Contact("Billie Ray", "raygun2@greygun.com", "Seattle, WA", "1652754654321");
		int result = dao.save(contact);
		
		assertTrue(result > 0);
	}
	
	@Test
	void testUpdate() {
		
		Contact contact = new Contact(2, "Bill Nye", "scienceguy@grey.com", "Redmond, WA", "1200654654321");
		int result = dao.update(contact);
		
		assertTrue(result > 0);
	}
	
	@Test
	void testGet() {
		Integer id = 25;
		Contact contact = dao.get(id);
		
		if (contact != null) {
			System.out.println(contact);
		}
		assertNotNull(contact);
	}
	
	@Test
	void testDelete() {
		Integer id = 4;
		int result = dao.delete(id);
		
		assertTrue(result > 0);
	}
	
	@Test
	void testList() {
		List<Contact> listContacts = dao.list();

		for (Contact contact : listContacts) {
			System.out.println(contact);
		}

	}


}
