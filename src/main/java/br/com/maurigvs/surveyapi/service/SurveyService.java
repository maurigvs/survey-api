package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.model.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public void createSurvey(SurveyRequest request) {
        Survey survey = parseFromDto(request);
        surveyRepository.save(survey);
    }

    private Survey parseFromDto(SurveyRequest request) {

        if(request.getTitle() == null || request.getTitle().isBlank() || request.getTitle().isEmpty())
            throw new IllegalArgumentException("Survey title is required");
        if(request.getQuestions().isEmpty())
            throw new IllegalArgumentException("Surveys must have at least 1 question");

        Survey survey = new Survey(request.getTitle());
        survey.getQuestions().addAll(request.getQuestions().stream().map(q -> {

            if(q.getTitle() == null || q.getTitle().isEmpty() || q.getTitle().isBlank())
                throw new IllegalArgumentException("Question title is required");
            if(q.getChoices().isEmpty())
                throw new IllegalArgumentException("Questions must have at least 1 choice");

            Question question = new Question(q.getTitle());
            question.getChoices().addAll(q.getChoices().stream().map(c -> {

                if(c.isEmpty() || c.isBlank())
                    throw new IllegalArgumentException("Choices can not be null or blank");

                Choice choice = new Choice(c);
                choice.setQuestion(question);
                return choice;

            }).collect(Collectors.toList()));
            question.setSurvey(survey);
            return question;
        }).collect(Collectors.toList()));
        return survey;
    }
}