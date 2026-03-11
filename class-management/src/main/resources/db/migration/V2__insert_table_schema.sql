-- 1. Đảm bảo môn học TOEIC-01 có tồn tại
INSERT IGNORE INTO subject (id, name, status)
VALUES ('TOEIC-01', 'Lớp TOEIC', 'ACTIVE');

-- 2. Tạo 1 cấp độ đi kèm với môn TOEIC-01
INSERT IGNORE INTO level (id, subject_id, name, description)
VALUES ('LVL-TOEIC-500', 'TOEIC-01', 'TOEIC 500+', 'Mục tiêu 500 điểm');