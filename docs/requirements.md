# Mô tả hệ thống bằng ngôn ngữ tự nhiên
I. Mục đích của hệ thống: Hệ thống Smart Education Platform (SEP) là một nền tảng LMS (Learning Management System). Trong đó:
1. Guest (chưa đăng nhập):
    - Đăng ký (phải có xác thực của hệ thống gửi qua email)
    - Đăng nhập
    - Xem Homepage
    - Reset Password 
2. Authenticated User (đã đăng nhập):
   - Xem profile
   - Xem dashboard (phụ thuộc vào user role)
   - Đổi password
   - Nhận notification
3. Student (kế thừa Authenticated User):
   - Tham gia lớp học theo mã lớp
   - Làm/nộp bài tập của lớp
   - Xem bài đăng trong lớp
   - Xem lịch sử nộp bài
   - Xem phản hồi của giáo viên trong 1 lần nộp cụ thể
   - Comment post
4. Teacher (kế thừa Authenticated User):
   - Xem lịch sử nộp bài của học sinh
   - Review bài được AI chấm
   - Chấm lại bài (update Submission details)
   - Quản lý bài tập cho lớp (phải có thông báo gửi học sinh)
   - Comment post
5. Content Moderator (kế thừa Teacher):
   - Quản lý rubric, có thể tạo bằng AI rồi review
   - Quản lý câu hỏi, có thể tạo bằng AI rồi review
   - Quản lý bộ câu hỏi (question group), có thể tạo bằng AI rồi review
   - Quản lý bài tập
   - Gắn bài tập vào một môn học
6.  Class Admin:
   - Quản lý lớp học
   - Quản lý bài tập của lớp
   - Quản lý bài đăng của lớp
   - Thêm học sinh
   - Gửi lời mời qua mã lớp
7. Center Manager (kế thừa Teacher):
   - Quản lý nhân viên 
   - Quản lý môn học
   - Quản lý cấp độ của lớp học (level: beginner, intermediate, advanced)
   - Quản lý bài tập của môn học, có thể gắn bài tập cho 1 môn
8. Admin
   - Quản lý người dùng
   - Quản lý settings
   - Quản lý môn học
   - Quản lý khóa học (1 khóa có nhiều lớp )
   - Quản lý trung tâm


- user-role: 1-1
- user-notification:1-n
- user-class:n-n (user học sinh) -> enrollment
- user-class:1-n (user giáo viên)
- user-post:1-n
- class-post: 1-n
- post-comment:1-n

assignment-question:n-n
- -> assignmentQuestion 

user-submission:1-n
user-
subject-class:1-n
subject-assignment:1-n
subject-questionGroup:1-n




một assignment có nhiều question. một question có thể nằm trong nhiều assignment
-> assignment-question:n-n. Đề xuất bảng AssignmentQuestion

một questionGroup sẽ có nhiều question. 
*