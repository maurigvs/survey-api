package br.com.maurigvs.surveyapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maurigvs.surveyapi.entity.dto.AnswerDto;
import br.com.maurigvs.surveyapi.entity.model.Answer;
import br.com.maurigvs.surveyapi.entity.model.AnswerChoice;
import br.com.maurigvs.surveyapi.entity.model.AnswerQuestion;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnswerService {
    
    @Autowired
    AnswerRepository repository;

    public void createAnswer(AnswerDto dto){
        Answer answer = parseDto(dto);
        log.info("Creating Answer: {}", answer);
        repository.save(answer);
    }

    public List<Answer> findByEmail(String email) {
        List<Answer> answers = repository.findByEmail(email);
        log.info("Listing Answers founded by email {}: {}", email, answers);
        return repository.findByEmail(email);
    }

    private Answer parseDto(AnswerDto dto){
        Answer answer = new Answer();
        answer.setSurveyId(dto.getSurveyId());
        answer.setEmail(dto.getEmail());
        answer.getQuestions().addAll(dto.getItems().stream().map(o -> {
            AnswerQuestion aq = new AnswerQuestion();
            aq.setAnswer(answer);
            aq.setQuestionId(o.getQuestionId());
            aq.getChoices().addAll(o.getChoicesIds().stream().map(c -> {
                AnswerChoice ac = new AnswerChoice();
                ac.setQuestion(aq);
                ac.setChoiceId(c);
                return ac;
            }).collect(Collectors.toList()));
            return aq;
        }).collect(Collectors.toList()));
        return answer;
    }
}