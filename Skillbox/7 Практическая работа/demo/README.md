# Task Tracker Application

## Описание
Task Tracker — это приложение для управления пользователями и задачами. Оно позволяет создавать, обновлять, удалять и просматривать пользователей, а также управлять задачами с различными статусами и связанными сущностями (автор, исполнитель, наблюдатели).

---

## Запуск приложения

### 1. Установка необходимых инструментов
- Убедитесь, что у вас установлены следующие инструменты:
  - **Docker Desktop**
  - **Postman**
  - **Java 17+**
  - **Maven**

### 2. Запуск MongoDB в Docker
Для запуска MongoDB выполните следующую команду:

```bash
docker run --name mongo-container -d -p 27017:27017 mongo
```

Проверьте, что контейнер работает:
```bash
docker ps
```

### 3. Сборка и запуск приложения
1. Клонируйте проект или убедитесь, что код проекта доступен локально.
2. В терминале в папке проекта выполните:

```bash
mvn clean install
mvn spring-boot:run
```

Приложение будет доступно по адресу:
```bash
http://localhost:8080
```

---

## Использование API

### Базовые маршруты:
- Пользователи: `/users`
- Задачи: `/tasks`

### Тестирование через Postman

#### **1. Работа с пользователями**

- **Создать пользователя**
  - Метод: `POST`
  - URL: `http://localhost:8080/users`
  - Тело запроса:
    ```json
    {
      "username": "john_doe",
      "email": "john.doe@example.com"
    }
    ```

- **Получить всех пользователей**
  - Метод: `GET`
  - URL: `http://localhost:8080/users`

- **Получить пользователя по ID**
  - Метод: `GET`
  - URL: `http://localhost:8080/users/{id}`

- **Обновить пользователя**
  - Метод: `PUT`
  - URL: `http://localhost:8080/users/{id}`
  - Тело запроса:
    ```json
    {
      "username": "jane_doe",
      "email": "jane.doe@example.com"
    }
    ```

- **Удалить пользователя**
  - Метод: `DELETE`
  - URL: `http://localhost:8080/users/{id}`

#### **2. Работа с задачами**

- **Создать задачу**
  - Метод: `POST`
  - URL: `http://localhost:8080/tasks`
  - Тело запроса:
    ```json
    {
      "name": "Complete project",
      "description": "Finish the task tracker project",
      "status": "TODO",
      "authorId": "userId1",
      "assigneeId": "userId2",
      "observerIds": ["userId3"]
    }
    ```

- **Получить все задачи**
  - Метод: `GET`
  - URL: `http://localhost:8080/tasks`

- **Получить задачу по ID**
  - Метод: `GET`
  - URL: `http://localhost:8080/tasks/{id}`

- **Обновить задачу**
  - Метод: `PUT`
  - URL: `http://localhost:8080/tasks/{id}`
  - Тело запроса:
    ```json
    {
      "name": "Update project",
      "description": "Fix issues in the task tracker",
      "status": "IN_PROGRESS",
      "assigneeId": "newUserId"
    }
    ```

- **Добавить наблюдателя в задачу**
  - Метод: `PATCH`
  - URL: `http://localhost:8080/tasks/{id}/addObserver`
  - Тело запроса:
    ```json
    {
      "observerId": "userId3"
    }
    ```

- **Удалить задачу**
  - Метод: `DELETE`
  - URL: `http://localhost:8080/tasks/{id}`

---

## Работа с базой данных MongoDB

1. **Подключение через MongoDB Compass:**
   - URL подключения: `mongodb://localhost:27017`
   - База данных: `task_tracker`

2. **Просмотр коллекций:**
   - Коллекция пользователей: `users`
   - Коллекция задач: `tasks`

3. **Примеры запросов в консоли MongoDB:**
   - Найти всех пользователей:
     ```javascript
     db.users.find()
     ```
   - Найти все задачи:
     ```javascript
     db.tasks.find()
     ```
