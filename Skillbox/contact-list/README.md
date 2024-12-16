```markdown
# Приложение "Список контактов"

Это простое веб-приложение на базе Spring Boot для управления списком контактов. Приложение позволяет добавлять, редактировать, удалять и просматривать контакты.

## Функции

- Просмотр всех контактов в таблице.
- Добавление нового контакта.
- Редактирование существующего контакта.
- Удаление контакта.

## Стек технологий

- **Java 17** - язык программирования.
- **Spring Boot** - фреймворк для разработки веб-приложений.
- **Thymeleaf** - шаблонизатор для отображения данных на страницах.
- **PostgreSQL** - база данных для хранения контактов.
- **Docker** - контейнеризация для запуска базы данных.
- **Maven** - система сборки проекта.

## Установка и запуск

### 1. Подготовка базы данных с помощью Docker

1. Убедитесь, что установлен **Docker Desktop**.
2. Создайте файл **docker-compose.yml** с следующим содержимым:

   ```yaml
   version: '3.8'
   services:
     postgres:
       image: postgres:15
       container_name: contact-list-db
       environment:
         POSTGRES_DB: contactdb
         POSTGRES_USER: user
         POSTGRES_PASSWORD: password
       ports:
         - "5432:5432"
       restart: always
   ```

3. Запустите базу данных командой:

   ```bash
   docker-compose up -d
   ```

### 2. Настройка приложения

1. Склонируйте репозиторий проекта:

   ```bash
   git clone <ссылка на репозиторий>
   cd contact-list
   ```

2. Настройте подключение к базе данных в файле **application.properties**:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/contactdb
   spring.datasource.username=user
   spring.datasource.password=password
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Соберите проект с помощью **Maven**:

   ```bash
   mvn clean install
   ```

4. Запустите приложение:

   ```bash
   mvn spring-boot:run
   ```

### 3. Доступ к приложению

После запуска приложение будет доступно по адресу:

- **Главная страница:** [http://localhost:8080/contacts](http://localhost:8080/contacts)

## Использование

1. **Добавить контакт:**  
   Перейдите по ссылке `/contacts/add` и заполните форму.

2. **Редактировать контакт:**  
   Нажмите кнопку "Edit" напротив нужного контакта.

3. **Удалить контакт:**  
   Нажмите кнопку "Delete" напротив контакта.

## Структура проекта

```
contact-list/
│
├── src/main/java/com/example/contactlist/
│   ├── model/                # Классы моделей данных
│   ├── repository/           # Репозиторий для работы с БД
│   ├── controller/           # Контроллеры для обработки запросов
│   └── ContactListApplication.java # Главный класс приложения
│
├── src/main/resources/
│   ├── templates/            # Шаблоны страниц Thymeleaf
│   ├── application.properties # Конфигурация приложения
│
└── docker-compose.yml        # Настройка PostgreSQL через Docker
```

## Примеры SQL-запросов

Создание таблицы `contacts` вручную (если необходимо):

```sql
CREATE TABLE contacts (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);
```

## Требования

- **Java 17** или выше
- **Maven**
- **Docker**
