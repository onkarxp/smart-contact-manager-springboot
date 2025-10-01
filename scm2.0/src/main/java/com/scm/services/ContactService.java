package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;

@Service
public interface ContactService {


    // create contact
    Contact save(Contact contact);

    // update contact
    Contact update(Contact contact);

    // get contacts
    List<Contact> getAll();

    // get contact by id

    Contact getById(String id);

    // delete contact

    void delete(String id);


    List<Contact> search (String name, String email, String phoneNumber);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user,int page,int size, String sortBy, String direction);
}
