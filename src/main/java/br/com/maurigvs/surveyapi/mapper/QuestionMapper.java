package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.QuestionRequest;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;
import java.util.function.Function;

public class QuestionMapper implements Function<QuestionRequest, Question> {

    private final Survey survey;

    public QuestionMapper(Survey survey) {
        this.survey = survey;
    }

    @Override
    public Question apply(QuestionRequest request) {
        var question = new Question(null, request.question(), survey);
        applyChoices(question, request.choices());

        return question;
    }

    private void applyChoices(Question question, List<String> choices) {
        question.getChoices().addAll(
            choices.stream().map(new ChoiceMapper(question)).toList()
        );
    }
}