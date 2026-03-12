package com.sep.classManagement.adapter.out.persistance;

import com.sep.classManagement.application.port.in.query.GetClassroomsQuery;
import com.sep.commonModule.domain.model.EntityStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassroomSpecification {

    public static Specification<ClassroomJpaEntity> filterBy(GetClassroomsQuery query) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Nếu có nhập Keyword -> Tìm kiếm gần đúng (LIKE) vào Tên hoặc Mã lớp
            if (StringUtils.hasText(query.getKeyword())) {
                String keyword = "%" + query.getKeyword().toLowerCase() + "%";
                Predicate matchName = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword);
                Predicate matchCode = criteriaBuilder.like(criteriaBuilder.lower(root.get("code")), keyword);
                predicates.add(criteriaBuilder.or(matchName, matchCode));
            }

            // 2. Nếu có chọn Môn học -> Lọc chính xác theo subjectId
            if (StringUtils.hasText(query.getSubjectId())) {
                predicates.add(criteriaBuilder.equal(root.get("subjectId"), query.getSubjectId()));
            }

            // 3. Nếu có chọn Cấp độ -> Lọc chính xác theo levelId
            if (StringUtils.hasText(query.getLevelId())) {
                predicates.add(criteriaBuilder.equal(root.get("levelId"), query.getLevelId()));
            }

            // 4. Nếu có chọn Trạng thái (ACTIVE / INACTIVE)
            if (StringUtils.hasText(query.getStatus())) {
                try {
                    EntityStatus statusEnum = EntityStatus.valueOf(query.getStatus().toUpperCase());
                    predicates.add(criteriaBuilder.equal(root.get("status"), statusEnum));
                } catch (Exception e) {
                }
            }

            // Gộp tất cả điều kiện lại với nhau bằng chữ AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}