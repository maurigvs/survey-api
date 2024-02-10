package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionResponse;
import br.com.maurigvs.surveyapi.dto.SurveyResponse;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SurveyResponseMapper implements Function<Survey, SurveyResponse> {

    @Override
    public SurveyResponse apply(Survey survey) {
        return new SurveyResponse(
                survey.getId(),
                survey.getTitle(),
                applyQuestions(survey.getQuestions()));
    }

    private Map<Integer, QuestionResponse> applyQuestions(List<Question> questions) {
        return questions.stream().collect(
            Collectors.toMap(Question::getId, new QuestionResponseMapper())
        );
    }

    static class QuestionResponseMapper implements Function<Question, QuestionResponse> {

        @Override
        public QuestionResponse apply(Question question) {
            return new QuestionResponse(
                    question.getTitle(),
                    applyChoices(question.getChoices()));
        }

        private Map<Integer, String> applyChoices(List<Choice> choices) {
            return choices.stream().collect(
                Collectors.toMap(Choice::getId, Choice::getTitle)
            );
        }
    }
}
