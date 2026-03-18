package com.sep.learningContents.domain.model.question;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderingConfig implements QuestionConfig {
    
    private List<OrderedItem> items;

    @Getter
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderedItem {
        private String text; 
        private int order; 
    }

    @Override
    public void validate() {
        if (items == null || items.size() < 2) {
            throw new IllegalArgumentException("Ordering question must have at least 2 items");
        }
        
        // Kiểm tra xem thứ tự có liên tiếp bắt đầu từ 1 không
        List<Integer> orders = items.stream()
                .map(OrderedItem::getOrder)
                .sorted()
                .collect(Collectors.toList());
        
        for (int i = 0; i < orders.size(); i++) {
             // i=0 -> expected 1
             // i=1 -> expected 2
             if (orders.get(i) != i + 1) {
                 throw new IllegalArgumentException("Order numbers must be consecutive starting from 1");
             }
        }
    }
}
