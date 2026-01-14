# FAQ Assistant

FAQ Assistant is an AI-powered backend system that allows users to create, manage, search, and categorize FAQs. It also provides an intelligent answer suggestion feature using Google Gemini, with Redis caching to optimize performance and cost.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web** (REST APIs)
- **Spring Data JPA**
- **MySQL**
- **Redis**
- **Google Gemini API**
- **Maven**

---

## 📂 Project Structure
```
faq-assistant
├── controller         # REST controllers
├── service            # Business logic
├── repository         # JPA repositories
├── entity             # JPA entities
├── dto                # Request/response DTOs
├── config             # Redis & Gemini configuration
├── exception          # Global exception handling
├── db                 # Database schema
└── README.md
```

---

## 🗄️ Database Setup

1. **Create a MySQL database:**
```sql
CREATE DATABASE faq_assistant;
```

2. **Run the database schema:**
```bash
db/schema.sql
```

- Sample seed data is included in the schema file.
- Hibernate is configured in `validate` mode to ensure schema consistency and prevent unintended schema changes.

---

## 🔧 Application Configuration

Update `application.properties`:
```properties
# Database
spring.datasource.url=jdbc:mysql://localhost:3306/faq_assistant
spring.datasource.username=root
spring.datasource.password=root

# Redis
spring.redis.host=localhost
spring.redis.port=6379

# Google Gemini
gemini.api-key=${GEMINI_API_KEY}
gemini.model=gemini-1.5-flash
```

⚠️ **Do not commit your Gemini API key.**

Set it as an environment variable:
```bash
export GEMINI_API_KEY=your_api_key_here
```

---

## ▶️ Running the Application
```bash
mvn clean install
mvn spring-boot:run
```

Application will start at:
```
http://localhost:8080
```

---

## 📌 API Endpoints

### FAQ APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/faqs` | Create a new FAQ |
| GET | `/api/v1/faqs/{id}` | Get FAQ by ID |
| GET | `/api/v1/faqs` | Search FAQs (pagination & filters) |
| PUT | `/api/v1/faqs/{id}` | Update FAQ |
| DELETE | `/api/v1/faqs/{id}` | Delete FAQ |

### Category APIs

| Method | Endpoint |
|--------|----------|
| POST | `/api/v1/categories` |
| GET | `/api/v1/categories` |

### Tag APIs

| Method | Endpoint |
|--------|----------|
| POST | `/api/v1/tags` |
| GET | `/api/v1/tags` |

---

## 🤖 AI Suggestion API

### Endpoint
```
POST /api/v1/ai/suggest-answer
```

### Request
```json
{
  "question": "How do I reset my password?"
}
```

### Response
```json
{
  "success": true,
  "data": {
    "suggestedAnswer": "To reset your password, go to Account Settings..."
  }
}
```

---

## ⚡ Caching Strategy

- AI-generated answers are cached using **Redis** and **Spring Cache**
- **Cache key:** FAQ question text
- **Cache TTL:** 6 hours
- Prevents repeated LLM calls, improving performance and reducing cost

---

## 🛡️ Exception Handling

- Centralized exception handling using `@RestControllerAdvice`
- Custom domain exceptions such as `ResourceNotFoundException`
- Validation errors handled via `MethodArgumentNotValidException`
- Consistent error responses with appropriate HTTP status codes (400, 404, 500)

### Example Error Response
```json
{
  "success": false,
  "data": null,
  "message": "FAQ not found"
}
```

---
