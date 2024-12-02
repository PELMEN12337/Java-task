package com.example;

import com.example.service.ContactService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Profile("init") // Активируется только при профиле init
public class ConsoleApp implements CommandLineRunner {
    private final ContactService contactService;

    public ConsoleApp(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        contactService.loadContacts(); // Инициализация из файла
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Показать контакты");
            System.out.println("2. Добавить контакт");
            System.out.println("3. Удалить контакт по email");
            System.out.println("4. Сохранить контакты в файл");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        contactService.displayContacts();
                        break;
                    case 2:
                        System.out.print("Введите контакт (Ф. И. О.;номер телефона;email): ");
                        String[] parts = scanner.nextLine().split(";");
                        if (parts.length == 3) {
                            contactService.addContact(parts[0], parts[1], parts[2]);
                        } else {
                            System.out.println("Неверный формат ввода.");
                        }
                        break;
                    case 3:
                        System.out.print("Введите email для удаления: ");
                        String email = scanner.nextLine();
                        contactService.deleteContactByEmail(email);
                        break;
                    case 4:
                        contactService.saveContacts();
                        System.out.println("Контакты сохранены.");
                        break;
                    case 5:
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода. Попробуйте ещё раз.");
            }
        }
    }
}
