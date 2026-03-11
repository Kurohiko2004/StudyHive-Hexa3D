CREATE TABLE IF NOT EXISTS classroom (
                                         id VARCHAR(255) PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
                                         code VARCHAR(255) NOT NULL UNIQUE,
                                         description TEXT NULL,
                                         teacher_id VARCHAR(255) NOT NULL,
                                         status VARCHAR(50) NOT NULL,
                                         created_at DATETIME NULL,
                                         created_by VARCHAR(255) NULL,
                                         updated_at DATETIME NULL,
                                         updated_by VARCHAR(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
