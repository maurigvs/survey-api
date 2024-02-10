package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionDto;
import br.com.maurigvs.surveyapi.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class SurveyMapper implements Function<SurveyDto, Survey> {

    @Override
    public Survey apply(SurveyDto dto) {
        var survey = new Survey(null, dto.survey());
        survey.getQuestions().addAll(mapQuestions(survey, dto.questions()));

        return survey;
    }

    private List<Question> mapQuestions(Survey survey, List<QuestionDto> questions) {
        return questions.stream().map(new QuestionMapper(survey)).toList();
    }


    public static class QuestionMapper implements Function<QuestionDto, Question> {

        private final Survey survey;

        public QuestionMapper(Survey survey) {
            this.survey = survey;
        }

        @Override
        public Question apply(QuestionDto dto) {
            var question = new Question(null, dto.question(), survey);
            question.getChoices().addAll(mapChoices(question, dto.choices()));

            return question;
        }

        private List<Choice> mapChoices(Question question, List<String> choices) {
            return choices.stream().map(new ChoiceMapper(question)).toList();
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
