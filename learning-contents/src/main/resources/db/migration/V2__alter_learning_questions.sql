ALTER TABLE learning_questions ADD COLUMN group_id VARCHAR(36) NULL;
ALTER TABLE learning_questions CHANGE COLUMN difficulty level_id VARCHAR(36) NOT NULL;
ALTER TABLE learning_questions CHANGE COLUMN skill skill_id VARCHAR(50) NOT NULL;

