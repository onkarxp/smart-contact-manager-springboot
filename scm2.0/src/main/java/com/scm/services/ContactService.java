package com.scm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.scm.entities.Contact;

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
}
