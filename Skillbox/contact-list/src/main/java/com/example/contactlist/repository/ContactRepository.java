package com.example.contactlist.repository;

import com.example.contactlist.model.Contact;
import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();

    void save(Contact contact);

    void update(Contact contact);

    void delete(Long id);

    Contact findById(Long id);

}
