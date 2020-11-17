
/**
 * 
 */
package net.codejava.contact.dao;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import net.codejava.contact.model.Contact;

/**
 * @author rebec
 *
 */
public class ContactDAOImpl implements ContactDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	
	public ContactDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Contact c) {
		String sql = "INSERT INTO Contact (name, email, address, phone) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone());
	}

	@Override
	public int update(Contact c) {
		//String sql = "UPDATE Contact SET name=?, email=?, address=?, phone=? WHERE contact_id=?";
		String sql = "UPDATE Contact SET name=?, email=?, address=?, phone=? WHERE contactid=?";
		return jdbcTemplate.update(sql, c.getName(), c.getEmail(), c.getAddress(), c.getPhone(), c.getId());
	}

	@Override
	public Contact get(Integer id) {
		//String sql = "SELECT * FROM Contact WHERE contact_id=" + id;
		String sql = "SELECT * FROM Contact WHERE contactid=" + id;
		
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>(){

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					
					return new Contact(id, name, email, address, phone);
				}
				
				return null;
			}
			
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		String sql = "DELETE FROM Contact WHERE contactid=" + id;
		return jdbcTemplate.update(sql);
	}

	
	@Override
	public List<Contact> list() {
	    //String sql = "SELECT * FROM Contact";
	    String sql = "SELECT contactid as id, name as name, email as email, address as address, phone as phone FROM Contact";
	    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Contact.class));  	
	}

	@Override
	public <T> List<Contact> list1() {
		// TODO Auto-generated method stub
		return null;
	}



}
