package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionDto;
import br.com.maurigvs.surveyapi.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class SurveyDtoMapper implements Function<Survey, SurveyDto> {

    @Override
    public SurveyDto apply(Survey survey) {
        return new SurveyDto(
                survey.getTitle(),
                mapQuestions(survey.getQuestions())
        );
    }

    private List<QuestionDto> mapQuestions(List<Question> questions) {
        return questions.stream().map(new QuestionDtoMapper()).toList();
    }

    static class QuestionDtoMapper implements Function<Question, QuestionDto> {

        @Override
        public QuestionDto apply(Question question) {
            return new QuestionDto(
                    question.getTitle(),
                    mapChoices(question.getChoices())
            );
        }

        private List<String> mapChoices(List<Choice> choices) {
            return choices.stream().map(Choice::getTitle).toList();
        }
    }
}
