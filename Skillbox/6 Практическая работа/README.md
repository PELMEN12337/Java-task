# Order Service Application

## Описание
Приложение предназначено для обработки заказов и отправки событий в Kafka. Оно состоит из REST API для создания заказов и интеграции с Kafka для передачи событий.

---

## Запуск приложения

### Требования
- **Java 17+**
- **Maven**
- **Docker (опционально для запуска Kafka)**

### Шаги запуска

1. **Склонировать репозиторий:**

   ```bash
   git clone <URL репозитория>
   cd <папка репозитория>
   ```

2. **Собрать проект:**

   ```bash
   mvn clean install
   ```

3. **Запустить Kafka:**

   Если Kafka не установлена локально, можно использовать Docker:

   ```bash
   docker-compose up -d
   ```

   Убедитесь, что Kafka работает и создан топик `order-topic`.

   Для создания топика выполните:

   ```bash
   kafka-topics.sh --create --topic order-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```

4. **Запустить приложение:**

   ```bash
   mvn spring-boot:run
   ```

---

## Использование

### API Endpoints

#### 1. **POST /orders**

Создание нового заказа. Отправляет событие в Kafka.

- **URL:** `/orders`
- **Метод:** `POST`
- **Тело запроса:**

  ```json
  {
    "product": "Laptop",
    "quantity": 1
  }
  ```

- **Ответ:**

  ```json
  "Order event sent to Kafka"
  ```

---

## Настройка

### Конфигурация Kafka

Конфигурация находится в `application.properties`:

```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```

Убедитесь, что указанный `bootstrap-servers` соответствует вашей установке Kafka.

### Kafka Топик

Название топика указано в контроллере:

```java
private static final String TOPIC = "order-topic";
```

---

## Отладка

### Возможные проблемы

1. **500 Internal Server Error:**
   - Проверьте логи приложения.
   - Убедитесь, что тело запроса соответствует модели `Order`.

2. **Ошибки Kafka:**
   - Проверьте, что Kafka запущен.
   - Убедитесь, что топик `order-topic` существует.

### Логи

Логи доступны в консоли при запуске приложения. Используйте их для диагностики.

---

## Дополнительная информация

### Структура проекта

- **`controller/OrderController`**: Обрабатывает HTTP-запросы.
- **`model/Order` и `OrderEvent`**: Представляют данные заказов и событий.
- **`application.properties`**: Основная конфигурация приложения.

### Тестирование API

Для тестирования можно использовать **Postman** или команду `curl`:

```bash
curl -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-d '{"product": "Laptop", "quantity": 1}'
```

---
