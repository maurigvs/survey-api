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
        Survey survey = new Survey(request.getTitle());
        survey.getQuestions().addAll(request.getQuestions().stream().map(q -> {
            Question question = new Question(q.getTitle());
            question.getChoices().addAll(q.getChoices().stream().map(c -> {
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