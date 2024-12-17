# Book Management Application

## Описание
Приложение для управления книгами с использованием Spring Boot, Spring Data JPA и Redis. Реализована возможность работы с сущностями **Book** и **Category**, а также кеширование данных с использованием Redis. 

### Основные функции:
- Создание книги с привязкой к категории.
- Поиск книги по названию и автору (с кешированием).
- Поиск списка книг по имени категории (с кешированием).
- Обновление информации о книге.
- Удаление книги по ID (с инвалидированием кеша).

## Требования
- **Java**: 17+
- **Redis**: Установлен и запущен (например, через Docker).
- **Maven** или **Gradle** для сборки проекта.
- **Postman** или другой инструмент для тестирования API.

## Установка

1. **Клонируйте репозиторий**
   ```bash
   git clone <ссылка-на-репозиторий>
   cd <название-проекта>
   ```

2. **Запустите Redis через Docker**
   ```bash
   docker run -d -p 6379:6379 redis
   ```

3. **Настройте подключение к Redis**
Убедитесь, что в `src/main/resources/application.properties` указаны правильные настройки:
   ```properties
   spring.data.redis.host=localhost
   spring.data.redis.port=6379
   ```

4. **Соберите проект**
   - Для Maven:
     ```bash
     mvn clean package
     ```
   - Для Gradle:
     ```bash
     gradlew clean build
     ```

5. **Запустите приложение**
   ```bash
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```

## Использование
Для тестирования приложения используйте Postman или другой инструмент. 

### Основные эндпоинты:

#### 1. Создание книги
**POST** `/books`
- **Пример тела запроса**:
  ```json
  {
    "title": "Spring Boot Guide",
    "author": "John Doe",
    "category": {
      "name": "Programming"
    }
  }
  ```
- **Ответ**: Созданная книга с ID.

#### 2. Получение книги по названию и автору
**GET** `/books/{title}/{author}`
- **Пример запроса**:
  ```
  GET http://localhost:8080/books/Spring%20Boot%20Guide/John%20Doe
  ```
- **Ответ**: Информация о книге.

#### 3. Получение списка книг по категории
**GET** `/books?category={categoryName}`
- **Пример запроса**:
  ```
  GET http://localhost:8080/books?category=Programming
  ```
- **Ответ**: Список книг в категории.

#### 4. Обновление книги
**PUT** `/books/{id}`
- **Пример тела запроса**:
  ```json
  {
    "title": "Advanced Spring Boot",
    "author": "Jane Doe",
    "category": {
      "name": "Programming"
    }
  }
  ```
- **Ответ**: Обновленные данные книги.

#### 5. Удаление книги
**DELETE** `/books/{id}`
- **Пример запроса**:
  ```
  DELETE http://localhost:8080/books/1
  ```
- **Ответ**: Успешное удаление (204 No Content).

## Проверка кеширования
1. После выполнения `GET`-запросов:
   - Подключитесь к Redis:
     ```bash
     docker exec -it <container_id> redis-cli
     ```
   - Выполните команду:
     ```bash
     KEYS *
     ```
   - Убедитесь, что ключи кеша сохранены.

2. Для проверки содержимого ключа используйте:
   ```bash
   GET <key>
   ```