package com.example.contactlist.repository;

import com.example.contactlist.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        return jdbcTemplate.query("SELECT * FROM contacts",
                (rs, rowNum) -> {
                    Contact contact = new Contact();
                    contact.setId(rs.getLong("id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setEmail(rs.getString("email"));
                    contact.setPhone(rs.getString("phone"));
                    return contact;
                });
    }

    @Override
    public void save(Contact contact) {
        jdbcTemplate.update("INSERT INTO contacts (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)",
                contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
    }

    @Override
    public void update(Contact contact) {
        jdbcTemplate.update("UPDATE contacts SET first_name=?, last_name=?, email=?, phone=? WHERE id=?",
                contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM contacts WHERE id=?", id);
    }

    @Override
    public Contact findById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";

        return jdbcTemplate.query(
                con -> {
                    var ps = con.prepareStatement(sql);
                    ps.setLong(1, id);
                    return ps;
                },
                rs -> {
                    if (rs.next()) {
                        Contact contact = new Contact();
                        contact.setId(rs.getLong("id"));
                        contact.setFirstName(rs.getString("first_name"));
                        contact.setLastName(rs.getString("last_name"));
                        contact.setEmail(rs.getString("email"));
                        contact.setPhone(rs.getString("phone"));
                        return contact;
                    }
                    return null;
                });
    }

}
