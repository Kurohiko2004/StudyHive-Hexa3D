package com.sep.learningContents.adapter.out.persistance;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionsQuery;
import com.sep.learningContents.application.port.out.LoadQuestionPort;
import com.sep.learningContents.application.port.out.SaveQuestionPort;
import com.sep.learningContents.domain.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionPersistenceAdapter implements SaveQuestionPort, LoadQuestionPort {

    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;

    @Override
    public void save(Question question) {
        QuestionJpaEntity entity = mapper.toJpaEntity(question);
        questionRepository.save(entity);
    }
    @Override
    public PageResponse<Question> loadQuestions(GetQuestionsQuery query) {

        Pageable pageable = query.getPageable();

        Specification<QuestionJpaEntity> spec = QuestionSpecification.filterBy(
                query.getSubjectId(),
                query.getGroupId(),
                query.getLevelId(),
                query.getSkillId(),
                query.getType(),
                query.getKeyword()
        );

        Page<QuestionJpaEntity> entityPage = questionRepository.findAll(spec, pageable);

        List<Question> classrooms = entityPage.getContent().stream()
                .map(mapper::toDomain)
                .toList();

        return PageResponse.<Question>builder()
                .data(classrooms)
                .currentPage(entityPage.getNumber() + 1)
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .hasNext(entityPage.hasNext())
                .build();
    }
}
