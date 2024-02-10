package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class SurveyMapper implements Function<SurveyRequest, Survey> {

    @Override
    public Survey apply(SurveyRequest dto) {
        var survey = new Survey(null, dto.survey());
        applyQuestions(survey, dto.questions());

        return survey;
    }

    private void applyQuestions(Survey survey, List<QuestionRequest> questions) {
        survey.getQuestions().addAll(
            questions.stream().map(new QuestionMapper(survey)).toList()
        );
    }


    public static class QuestionMapper implements Function<QuestionRequest, Question> {

        private final Survey survey;

        public QuestionMapper(Survey survey) {
            this.survey = survey;
        }

        @Override
        public Question apply(QuestionRequest dto) {
            var question = new Question(null, dto.question(), survey);
            applyChoices(question, dto.choices());

            return question;
        }

        private void applyChoices(Question question, List<String> choices) {
            question.getChoices().addAll(
                choices.stream().map(new ChoiceMapper(question)).toList()
            );
        }
    }


    static class ChoiceMapper implements Function<String, Choice> {
        private final Question question;

        public ChoiceMapper(Question question) {
            this.question = question;
        }

        @Override
        public Choice apply(String dto) {
            return new Choice(null, dto, question);
        }
    }
}
