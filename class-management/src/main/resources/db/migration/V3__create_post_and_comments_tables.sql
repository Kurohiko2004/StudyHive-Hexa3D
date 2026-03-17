CREATE TABLE IF NOT EXISTS class_posts (
    id VARCHAR(36) PRIMARY KEY,
    classroom_id VARCHAR(36) NOT NULL,
    author_id VARCHAR(36) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(1200) NOT NULL,
    is_pinned BOOLEAN DEFAULT FALSE,
    allow_comments BOOLEAN DEFAULT TRUE,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);

CREATE TABLE IF NOT EXISTS class_post_comments (
    id VARCHAR(36) PRIMARY KEY,
    post_id VARCHAR(36) NOT NULL,
    author_id VARCHAR(36) NOT NULL,
    parent_id VARCHAR(36) DEFAULT NULL,
    content VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES class_posts(id),
    CONSTRAINT fk_comment_parent FOREIGN KEY (parent_id) REFERENCES class_post_comments(id)
);

CREATE TABLE IF NOT EXISTS class_post_attachments (
    post_id VARCHAR(36) NOT NULL,
    file_url VARCHAR(500) NOT NULL,
    PRIMARY KEY (post_id, file_url),
    CONSTRAINT fk_attachment_post FOREIGN KEY (post_id) REFERENCES class_posts(id) ON DELETE CASCADE
);

