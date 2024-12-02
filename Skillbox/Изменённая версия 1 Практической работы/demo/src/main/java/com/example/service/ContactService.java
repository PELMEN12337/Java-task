package com.example.service;

public interface ContactService {
    void loadContacts(); // Инициализация из файла

    void displayContacts(); // Вывод всех контактов

    void addContact(String fullName, String phoneNumber, String email); // Добавить контакт

    void deleteContactByEmail(String email); // Удалить контакт

    void saveContacts(); // Сохранить в файл
}
