-- DATABASE
CREATE DATABASE IF NOT EXISTS faq_assistant;
USE faq_assistant;

-- USERS
CREATE TABLE IF NOT EXISTS users (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(150) NOT NULL UNIQUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- CATEGORIES
CREATE TABLE IF NOT EXISTS categories (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- FAQS
CREATE TABLE IF NOT EXISTS faqs (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	question TEXT NOT NULL,
	answer TEXT,
	category_id BIGINT NOT NULL,
	created_by BIGINT NOT NULL,
	updated_by BIGINT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	
	CONSTRAINT fk_faq_category
		FOREIGN KEY (category_id) REFERENCES categories(id),
		
	CONSTRAINT fk_faq_created_by
		FOREIGN KEY (created_by) REFERENCES users(id),
	
	CONSTRAINT fk_faq_updated_by
		FOREIGN KEY (updated_by) REFERENCES users(id)
);

-- FAQ TAGS
CREATE TABLE IF NOT EXISTS faq_tags (
	faq_id BIGINT NOT NULL,
	tag_id BIGINT NOT NULL,
	
	PRIMARY KEY (faq_id, tag_id),
	
	CONSTRAINT fk_faq_tags_faq
        FOREIGN KEY (faq_id) REFERENCES faqs(id) ON DELETE CASCADE,

    CONSTRAINT fk_faq_tags_tag
        FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
)

-- INDEXES (For Search)
CREATE FULLTEXT INDEX idx_faq_question ON faqs(question);
CREATE INDEX idx_tag_name ON tags(name);
CREATE INDEX idx_category_name ON categories(name);


-- SEED DATA
INSERT INTO users (name, email) VALUES
	('admin', 'admin.faqassistant@gmail.com'),
	('Divyanshu Kumar', 'divyanshu@gmail.com');
	
INSERT INTO categories (name, description) VALUES
	('Billing', 'Questions related to billing and invoices'),
	('Account', 'Account setup and access related questions'); 
	
INSERT INTO tags (name) VALUES
	('Invoice'),
	('Refund'),
	('Password'),
	('Login');

INSERT INTO faqs (question, answer, category_id, created_by) VALUES (
 	'How do I generate an invoice?',
 	'Go to Billing > Invoices and click on Generate Invoice.',
 	1,
 	1
);

INSERT INTO faq_tags (faq_id, tag_id) VALUES (1, 1);
