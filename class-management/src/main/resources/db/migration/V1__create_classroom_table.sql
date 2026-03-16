
DROP TABLE IF EXISTS classroom_schedule;
DROP TABLE IF EXISTS classroom;
DROP TABLE IF EXISTS level;
DROP TABLE IF EXISTS subject;

CREATE TABLE subject (
                         id VARCHAR(36) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE level (
                       id VARCHAR(36) PRIMARY KEY,
                       subject_id VARCHAR(36) NOT NULL,
                       name VARCHAR(50) NOT NULL,
                       description VARCHAR(255),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_level_subject FOREIGN KEY (subject_id) REFERENCES subject(id)
);


CREATE TABLE classroom (
                           id VARCHAR(36) PRIMARY KEY,
                           name VARCHAR(50) NOT NULL,
                           code VARCHAR(50) NOT NULL UNIQUE,
                           description VARCHAR(1000),
                           subject_id VARCHAR(36) NOT NULL,
                           level_id VARCHAR(36) NOT NULL,
                           teacher_id VARCHAR(36),
                           status VARCHAR(20) NOT NULL,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           created_by VARCHAR(50),
                           updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
                           updated_by VARCHAR(50),
                           CONSTRAINT fk_classroom_subject FOREIGN KEY (subject_id) REFERENCES subject(id),
                           CONSTRAINT fk_classroom_level FOREIGN KEY (level_id) REFERENCES level(id)
);

CREATE TABLE classroom_schedule (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    classroom_id VARCHAR(36) NOT NULL,
                                    day_of_week VARCHAR(15) NOT NULL,
                                    CONSTRAINT fk_schedule_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(id) ON DELETE CASCADE
);