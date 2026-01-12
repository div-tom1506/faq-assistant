FAQ AssistantFAQ Assistant is a backend system that allows users to create, manage, search, and categorize FAQs. It also provides an AI-powered answer suggestion feature using Google Gemini, with Redis caching to optimize performance and reduce API costs.🚀 Tech StackLanguage: Java 17Framework: Spring Boot 3Web: Spring Web (REST APIs)Persistence: Spring Data JPADatabase: MySQLCaching: RedisAI Engine: Google Gemini APIBuild Tool: Maven📂 Project StructurePlaintextfaq-assistant
├── controller   # REST Endpoints
├── service      # Business Logic
├── repository   # Database Access (JPA)
├── entity       # Database Models
├── dto          # Data Transfer Objects
├── config       # Configuration (Redis, Gemini, etc.)
├── exception    # Global Exception Handling
├── db           # SQL Scripts
└── README.md
🗄️ Database SetupCreate a MySQL database:SQLCREATE DATABASE faq_assistant;
Run the database schema:Execute the script found in db/schema.sql. Sample seed data is included in the schema file.Validation:Hibernate is configured in validate mode to ensure schema consistency.🔧 Application ConfigurationUpdate your src/main/resources/application.yml:YAMLspring:
  datasource:
    url: jdbc:mysql://localhost:3306/faq_assistant
    username: root
    password: YOUR_PASSWORD

  redis:
    host: localhost
    port: 6379

gemini:
  api-key: YOUR_GEMINI_API_KEY
  model: gemini-1.5-flash
[!WARNING]Do not commit your Gemini API key to version control. Use environment variables for production.▶️ Running the ApplicationBashmvn clean install
mvn spring-boot:run
The application will start at: http://localhost:8080📌 API EndpointsFAQ APIsMethodEndpointDescriptionPOST/api/v1/faqsCreate FAQGET/api/v1/faqs/{id}Get FAQ by IDGET/api/v1/faqsSearch/List FAQsPUT/api/v1/faqs/{id}Update FAQDELETE/api/v1/faqs/{id}Delete FAQCategory & Tag APIsMethodEndpointEntityPOST/api/v1/categoriesCreate CategoryGET/api/v1/categoriesList CategoriesPOST/api/v1/tagsCreate TagGET/api/v1/tagsList Tags🤖 AI Suggestion APIGenerate suggested answers using the Google Gemini model.Endpoint: POST /api/v1/ai/suggest-answerRequest Body:JSON{
  "question": "How do I reset my password?"
}
Response Body:JSON{
  "success": true,
  "data": {
    "suggestedAnswer": "To reset your password, go to Account Settings..."
  }
}
⚡ Caching StrategyAI responses are cached using Redis and Spring Cache to ensure high performance.Cache Key: Question textCache TTL: 6 hoursBenefit: Prevents repeated LLM calls, significantly improving response times and reducing API usage costs.🛡️ Error HandlingThe project uses a global exception handling strategy via @RestControllerAdvice. This ensures:Consistent error response structures.Appropriate HTTP status codes (400, 404, 500, etc.).User-friendly error messages.