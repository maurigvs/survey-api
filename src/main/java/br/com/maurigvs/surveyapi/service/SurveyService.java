package br.com.maurigvs.surveyapi.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maurigvs.surveyapi.controller.exception.BusinessException;
import br.com.maurigvs.surveyapi.entity.dto.AnswerRequest;
import br.com.maurigvs.surveyapi.entity.dto.SurveyRequest;
import br.com.maurigvs.surveyapi.entity.model.Answer;
import br.com.maurigvs.surveyapi.entity.model.AnswerChoice;
import br.com.maurigvs.surveyapi.entity.model.AnswerQuestion;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;
import br.com.maurigvs.surveyapi.entity.model.SurveyQuestion;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@Service
public class SurveyService {

    Logger log = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    AnswerRepository answerRepository;

    public void createSurvey(SurveyRequest request) {
        Survey survey = parseFromDto(request);
        log.info("Saving survey");
        surveyRepository.save(survey);
    }

    public void createAnswer(AnswerRequest request){
        Answer answer = parseFromDto(request);
        log.info("Saving Survey Answer");
        answerRepository.save(answer);
    }

    public List<Survey> listSurveys() {
        log.info("Listing all surveys");
        return surveyRepository.findAll();
    }

    private Survey parseFromDto(SurveyRequest request) {
        log.info("Parsing DTO to Entity");
        if(isNullString(request.getTitle()))
            throw new IllegalArgumentException("Survey title is required");

        if(request.getQuestions().isEmpty())
            throw new IllegalArgumentException("Surveys must have at least 1 question");

        Survey survey = new Survey(request.getTitle());
        survey.getQuestions().addAll(request.getQuestions().stream().map(q -> {

            if(isNullString(q.getTitle()))
                throw new IllegalArgumentException("Question title is required");

            if(q.getChoices().isEmpty())
                throw new IllegalArgumentException("Questions must have at least 1 choice");

            SurveyQuestion question = new SurveyQuestion(q.getTitle());
            question.getChoices().addAll(q.getChoices().stream().map(c -> {

                if(isNullString(c))
                    throw new IllegalArgumentException("Choices can not be null or blank");

                SurveyChoice choice = new SurveyChoice(c);
                choice.setQuestion(question);
                return choice;

            }).collect(Collectors.toList()));
            question.setSurvey(survey);
            return question;
        }).collect(Collectors.toList()));
        return survey;
    }

    private Answer parseFromDto(AnswerRequest request){

        log.info("Parsing DTO to Entity");

        if(isNullString(request.getEmail()))
            throw new IllegalArgumentException("Email is required for identification");

        if(Objects.equals(request.getSurveyId(), 0L))
            throw new IllegalArgumentException("Survey Id is required");

        if(request.getItems().isEmpty())
            throw new IllegalArgumentException("Survey must have at least 1 answer");

        Survey survey = surveyRepository.findById(request.getSurveyId())
                .orElseThrow(() -> new BusinessException(String.format("String Id [%s] not found", request.getSurveyId())));

        Answer answer = new Answer();
        answer.setEmail(request.getEmail());
        answer.setSurvey(survey);
        answer.getQuestions().addAll(
            request.getItems().stream().map(i -> {
            for (SurveyQuestion question : survey.getQuestions()) {
                if(Objects.equals(question.getId(), i.getQuestionId())){
                    AnswerQuestion aq = new AnswerQuestion();
                    aq.setAnswer(answer);
                    aq.setQuestion(question);
                    aq.getChoices().addAll(
                        i.getChoicesIds().stream().map(c -> {
                        for (SurveyChoice choice : question.getChoices()) {
                            if(Objects.equals(choice.getId(), c)){
                                AnswerChoice ac = new AnswerChoice();
                                ac.setQuestion(aq);
                                ac.setChoice(choice);
                                return ac;
                            }
                        }
                        return null;
                    }).collect(Collectors.toList()));
                    return aq;
                }
            }
            return null;
        }).collect(Collectors.toList()));
        return answer;
    }

    private boolean isNullString(String value){
        return (value == null || value.isEmpty() || value.isBlank());
    }
}