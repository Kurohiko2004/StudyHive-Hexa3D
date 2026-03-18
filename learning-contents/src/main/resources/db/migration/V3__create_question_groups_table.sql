CREATE TABLE IF NOT EXISTS learning_question_groups (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    subject_id VARCHAR(36) NOT NULL,
    skill_id VARCHAR(36) NOT NULL,
    level_id VARCHAR(36) NOT NULL,
    instructions TEXT,
    passage TEXT,
    media_url VARCHAR(500),
    author_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP
);

