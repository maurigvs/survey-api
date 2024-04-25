package br.com.maurigvs.surveyapi.mapper;

import br.com.maurigvs.surveyapi.dto.requests.QuestionRequest;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class QuestionMapper implements Function<QuestionRequest, Question> {

    private final Survey survey;

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
