package main.java.ContactsApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
    private List<Contact> contacts = new ArrayList<>();
    private String contactsFilePath;

    public ContactsManager(String contactsFilePath) {
        this.contactsFilePath = contactsFilePath;
    }

    // Загрузка контактов из файла
    public void loadContacts() {
        try (BufferedReader br = new BufferedReader(new FileReader(contactsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    contacts.add(new Contact(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке контактов: " + e.getMessage());
        }
    }

    // Сохранение контактов в файл
    public void saveContacts() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(contactsFilePath))) {
            for (Contact contact : contacts) {
                bw.write(contact.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении контактов: " + e.getMessage());
        }
    }

    // Добавление нового контакта
    public void addContact(String fullName, String phoneNumber, String email) {
        contacts.add(new Contact(fullName, phoneNumber, email));
    }

    // Удаление контакта по email
    public void removeContact(String email) {
        contacts.removeIf(contact -> contact.getEmail().equals(email));
    }

    // Вывод всех контактов
    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Список контактов пуст.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}

