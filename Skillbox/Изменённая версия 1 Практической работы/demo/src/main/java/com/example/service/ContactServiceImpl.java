package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private final List<Contact> contacts = new ArrayList<>();

    @Value("${contacts.file.input}")
    private String inputFilePath;

    @Value("${contacts.file.output}")
    private String outputFilePath;

    @Override
    public void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Контакты загружены.");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки контактов: " + e.getMessage());
        }
    }

    @Override
    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Контактов нет.");
        } else {
            contacts.forEach(contact -> System.out.println(contact));
        }
    }

    @Override
    public void addContact(String fullName, String phoneNumber, String email) {
        contacts.add(new Contact(fullName, phoneNumber, email));
        System.out.println("Контакт добавлен.");
    }

    @Override
    public void deleteContactByEmail(String email) {
        List<Contact> removedContacts = contacts.stream()
                .filter(contact -> contact.getEmail().equals(email))
                .collect(Collectors.toList());
        contacts.removeAll(removedContacts);
        if (removedContacts.isEmpty()) {
            System.out.println("Контакт не найден.");
        } else {
            System.out.println("Контакт удалён.");
        }
    }

    @Override
    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Contact contact : contacts) {
                writer.write(contact.toFileString());
                writer.newLine();
            }
            System.out.println("Контакты сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения контактов: " + e.getMessage());
        }
    }
}
