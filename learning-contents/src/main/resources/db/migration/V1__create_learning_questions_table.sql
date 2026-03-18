CREATE TABLE IF NOT EXISTS learning_questions (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    subject_id VARCHAR(36) NOT NULL,
    skill VARCHAR(50) NOT NULL,
    difficulty VARCHAR(36) NOT NULL,
    default_point DOUBLE NOT NULL,
    question_text TEXT NOT NULL,
    additional_instructions TEXT,
    media_url VARCHAR(500),
    author_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    config_json JSON
);

