package main.java.ContactsApp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String contactsFilePath = "src/main/resources/default-contacts.txt";
        ContactsManager contactsManager = new ContactsManager(contactsFilePath);

        // Загрузка контактов, если профиль init
        if (args.length > 0 && args[0].equals("init")) {
            contactsManager.loadContacts();
        }

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Показать контакты");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт");
            System.out.println("4. Сохранить контакты");
            System.out.println("5. Выйти");
            System.out.print("Ваш выбор: ");
            
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    contactsManager.displayContacts();
                    break;
                case "2":
                    System.out.print("Введите ФИО: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Введите номер телефона: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Введите email: ");
                    String email = scanner.nextLine();
                    contactsManager.addContact(fullName, phoneNumber, email);
                    break;
                case "3":
                    System.out.print("Введите email контакта, который нужно удалить: ");
                    String emailToRemove = scanner.nextLine();
                    contactsManager.removeContact(emailToRemove);
                    break;
                case "4":
                    contactsManager.saveContacts();
                    break;
                case "5":
                    contactsManager.saveContacts();
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}

