package com.sep.learningContents.adapter.out.persistance;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sep.learningContents.domain.model.Question;
import com.sep.learningContents.domain.model.question.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class QuestionMapper {

    @Autowired
    protected ObjectMapper objectMapper;

    @Mapping(target = "specificConfig", source = "specificConfig", qualifiedByName = "configToJson")
    public abstract QuestionJpaEntity toJpaEntity(Question domain);
    
    @Mapping(target = "specificConfig", source = ".", qualifiedByName = "jsonToConfig")
    public abstract Question toDomain(QuestionJpaEntity entity);

    public abstract List<Question> toDomainList(List<QuestionJpaEntity> entities);

    @Named("configToJson")
    protected String configToJson(QuestionConfig config) {
        if (config == null) return null;
        try {
            return objectMapper.writeValueAsString(config);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting config to JSON", e);
        }
    }

    @Named("jsonToConfig")
    protected QuestionConfig jsonToConfig(QuestionJpaEntity entity) {
        if (entity.getSpecificConfig() == null) return null;
        String type = entity.getType();
        String json = entity.getSpecificConfig();
        
        try {
            switch (type) {
                case "MULTIPLE_CHOICE": return objectMapper.readValue(json, MultipleChoiceConfig.class);
                case "ESSAY": return objectMapper.readValue(json, EssayConfig.class);
                case "FILL_IN_THE_BLANK": return objectMapper.readValue(json, FillBlankConfig.class);
                case "TRUE_FALSE": return objectMapper.readValue(json, TrueFalseConfig.class);
                case "MATCHING": return objectMapper.readValue(json, MatchingConfig.class);
                case "ORDERING": return objectMapper.readValue(json, OrderingConfig.class);
                case "REPEAT_SENTENCE": return objectMapper.readValue(json, RepeatSentenceConfig.class);
                default: throw new IllegalArgumentException("Unknown question type: " + type);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to config for type " + type, e);
        }
    }
}
