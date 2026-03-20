package com.sep.learningContents.adapter.out.persistance;

import com.sep.commonModule.dto.PageResponse;
import com.sep.learningContents.application.port.in.query.GetQuestionGroupsQuery;
import com.sep.learningContents.application.port.out.LoadQuestionGroupPort;
import com.sep.learningContents.application.port.out.SaveQuestionGroupPort;
import com.sep.learningContents.domain.model.QuestionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class QuestionGroupPersistenceAdapter implements SaveQuestionGroupPort, LoadQuestionGroupPort {

    private final QuestionGroupRepository questionGroupRepository;
    private final QuestionGroupMapper questionGroupMapper;

    @Override
    public void save(QuestionGroup questionGroup) {
        QuestionGroupJpaEntity entity = questionGroupMapper.toJpaEntity(questionGroup);
        questionGroupRepository.save(entity);
    }

    @Override
    public PageResponse<QuestionGroup> loadQuestionGroups(GetQuestionGroupsQuery query) {

        Pageable pageable = query.getPageable();

        Specification<QuestionGroupJpaEntity> spe = QuestionGroupSpecification.filterBy(
                query.getSubjectId(),
                query.getLevelId(),
                query.getSkillId(),
                query.getType(),
                query.getKeyword()
        );

        Page<QuestionGroupJpaEntity> entityPage = questionGroupRepository.findAll(spe, pageable);

        List<QuestionGroup> questionGroupList = entityPage.getContent().stream()
                .map(questionGroupMapper::toDomain)
                .toList();


        return PageResponse.<QuestionGroup>builder()
                .data(questionGroupList)
                .currentPage(entityPage.getNumber() + 1)
                .totalPages(entityPage.getTotalPages())
                .totalElements(entityPage.getTotalElements())
                .hasNext(entityPage.hasNext())
                .build();
    }

    @Override
    public Optional<QuestionGroup> loadQuestionGroupById(String id) {
        return questionGroupRepository.findById(id).map(questionGroupMapper::toDomain);
    }
}
