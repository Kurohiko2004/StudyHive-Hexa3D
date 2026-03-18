# Question & Question Group Data Samples (For Postman Testing)

Tôi đã chuẩn bị các mẫu JSON đầy đủ cho tất cả các loại câu hỏi (Question Types) và nhóm câu hỏi (Question Group) để bạn test API.

Hãy thêm vào Body của request Postman (chọn `Raw` -> `JSON`).

---

## 1. Create Question (Đơn lẻ)
**Endpoint:** `POST {{baseUrl}}/api/v1/questions`

### Type: MULTIPLE_CHOICE (Trắc nghiệm)
```json
{
  "type": "MULTIPLE_CHOICE",
  "subjectId": "ENG",
  "skillId": "READING", 
  "levelId": "EASY",
  "defaultPoint": 5.0,
  "questionText": "Chọn từ đồng nghĩa với 'Happy'?",
  "additionalInstructions": "Chọn 1 đáp án đúng nhất",
  "config": {
    "type": "MULTIPLE_CHOICE",
    "isMultiple": false,
    "options": [
      { "text": "Sad", "isCorrect": false },
      { "text": "Joyful", "isCorrect": true },
      { "text": "Angry", "isCorrect": false },
      { "text": "Tired", "isCorrect": false }
    ]
  }
}
```

### Type: ESSAY (Tự luận)
```json
{
  "type": "ESSAY",
  "subjectId": "VIE",
  "skillId": "WRITING",
  "levelId": "HARD",
  "defaultPoint": 20.0, 
  "questionText": "Viết một bài văn nghị luận về tầm quan trọng của việc đọc sách.",
  "config": {
    "type": "ESSAY",
    "minWords": 200,
    "maxWords": 1000
  }
}
```

### Type: FILL_IN_THE_BLANK (Điền từ)
```json
{
  "type": "FILL_IN_THE_BLANK",
  "subjectId": "ENG",
  "skillId": "GRAMMAR",
  "levelId": "MEDIUM",
  "defaultPoint": 10.0,
  "questionText": "She ___ (go) to school everyday.",
  "config": {
    "type": "FILL_IN_THE_BLANK",
    "blanks": [
      {
        "position": 1,
        "correctAnswer": "goes",
        "caseSensitive": false,
        "alternativeAnswers": ["is going"]
      }
    ]
  }
}
```

### Type: TRUE_FALSE (Đúng/Sai)
```json
{
  "type": "TRUE_FALSE",
  "subjectId": "HIS",
  "skillId": "KNOWLEDGE",
  "levelId": "EASY",
  "defaultPoint": 5.0,
  "questionText": "Chiến thắng Điện Biên Phủ diễn ra năm 1954?",
  "config": {
    "type": "TRUE_FALSE",
    "correctAnswer": "TRUE", 
    "includeNotGiven": false
  }
}
```

### Type: ORDERING (Sắp xếp câu)
```json
{
  "type": "ORDERING",
  "subjectId": "ENG",
  "skillId": "WRITING",
  "levelId": "MEDIUM",
  "defaultPoint": 10.0,
  "questionText": "Sắp xếp các từ sau thành câu hoàn chỉnh.",
  "config": {
    "type": "ORDERING",
    "items": [
      { "text": "is", "order": 2 },
      { "text": "This", "order": 1 },
      { "text": "apple", "order": 4 },
      { "text": "an", "order": 3 }
    ]
  }
}
```

### Type: MATCHING (Nối đáp án)
```json
{
  "type": "MATCHING",
  "subjectId": "GEO",
  "skillId": "KNOWLEDGE", 
  "levelId": "MEDIUM",
  "defaultPoint": 10.0,
  "questionText": "Nối tên quốc gia với thủ đô tương ứng.",
  "config": {
    "type": "MATCHING",
    "pairs": [
      { "prompt": "Vietnam", "match": "Hanoi" },
      { "prompt": "Japan", "match": "Tokyo" },
      { "prompt": "France", "match": "Paris" }
    ]
  }
}
```

### Type: REPEAT_SENTENCE (Lặp lại câu - Speaking)
```json
{
  "type": "REPEAT_SENTENCE",
  "subjectId": "ENG",
  "skillId": "SPEAKING",
  "levelId": "HARD",
  "defaultPoint": 10.0,
  "questionText": "Repeat the sentence exactly as you hear it.",
  "mediaUrl": "https://example.com/audio/sample_sentence.mp3",
  "config": {
    "type": "REPEAT_SENTENCE",
    "maxRecordingTime": 30,
    "maxRecordAttempts": 2
  }
}
```

---

## 2. Create Question Group (Nhóm câu hỏi)
**Endpoint:** `POST {{baseUrl}}/api/v1/question-groups`

*Lưu ý: Các câu hỏi con (questions) bên dưới **KHÔNG CẦN** gửi lại `subjectId`, `skillId`, `levelId` vì chúng sẽ tự động kế thừa từ Group cha.*

```json
{
  "title": "Reading Passage 1: The History of AI",
  "description": "Bài đọc hiểu về lịch sử trí tuệ nhân tạo dùng cho bài kiểm tra giữa kỳ.",
  "subjectId": "ENG",
  "skillId": "READING",
  "levelId": "HARD",
  "instructions": "Read the passage carefully and answer the questions below.",
  "passage": "<p>Artificial Intelligence (AI) began in the 1950s...</p>",
  "mediaUrl": "https://example.com/images/ai_history_banner.jpg",
  "questions": [
    {
      "type": "MULTIPLE_CHOICE",
      "questionText": "When did AI begin?",
      "defaultPoint": 5.0,
      "config": {
        "type": "MULTIPLE_CHOICE",
        "isMultiple": false,
        "options": [
          { "text": "1940s", "isCorrect": false },
          { "text": "1950s", "isCorrect": true },
          { "text": "1990s", "isCorrect": false }
        ]
      }
    },
    {
      "type": "TRUE_FALSE",
      "questionText": "AI was invented by a single person.",
      "defaultPoint": 5.0,
      "config": {
        "type": "TRUE_FALSE",
        "correctAnswer": "FALSE",
        "includeNotGiven": true
      }
    },
    {
       "type": "FILL_IN_THE_BLANK",
       "questionText": "The term 'Artificial Intelligence' was coined at the ___ conference.",
       "defaultPoint": 10.0,
       "config": {
         "type": "FILL_IN_THE_BLANK",
         "blanks": [
           {
             "position": 1,
             "correctAnswer": "Dartmouth",
             "caseSensitive": true,
             "alternativeAnswers": []
           }
         ]
       }
    }
  ]
}
```
