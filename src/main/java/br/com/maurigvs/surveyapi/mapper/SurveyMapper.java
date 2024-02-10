package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.model.dto.QuestionDto;
import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Choice;
import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.model.entity.Survey;

import java.util.function.Function;

public class SurveyMapper implements Function<SurveyDto, Survey> {

    @Override
    public Survey apply(SurveyDto surveyDto) {
        final var survey = new Survey(null, surveyDto.survey());
        survey.getQuestions().addAll(surveyDto.questions().stream()
                .map(q -> new QuestionMapper(survey).apply(q)).toList());
        return survey;
    }

    static class QuestionMapper implements Function<QuestionDto, Question> {

        private final Survey survey;

        public QuestionMapper(Survey survey) {
            this.survey = survey;
        }

        @Override
        public Question apply(QuestionDto questionDto) {
            final var question = new Question(null, questionDto.question(), survey);
            question.getChoices().addAll(questionDto.choices().stream()
                    .map(c -> new ChoiceMapper(question).apply(c)).toList());
            return question;
        }
    }

    static class ChoiceMapper implements Function<String, Choice> {
        private final Question question;

        public ChoiceMapper(Question question) {
            this.question = question;
        }

        @Override
        public Choice apply(String choiceDto) {
            return new Choice(null, choiceDto, question);
        }
    }
}
