package br.com.maurigvs.surveyapi.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.maurigvs.surveyapi.entity.dto.SurveyDto;
import br.com.maurigvs.surveyapi.entity.model.Survey;
import br.com.maurigvs.surveyapi.entity.model.SurveyChoice;
import br.com.maurigvs.surveyapi.entity.model.SurveyQuestion;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    private static final Logger log = LoggerFactory.getLogger(SurveyService.class);

    @Autowired
    SurveyRepository repository;

    public void createSurvey(SurveyDto dto) {
        Survey survey = parseDto(dto);
        log.info("Creating survey: {}", survey);
        repository.save(survey);
    }

    public List<Survey> findAll(){
        List<Survey> surveys = repository.findAll();
        log.info("Listing all surveys: {}", surveys);
        return surveys;
    }

    private Survey parseDto(SurveyDto dto){
        Survey survey = new Survey();
        survey.setTitle(dto.getTitle());
        survey.getQuestions().addAll(dto.getItems().stream().map(q -> {
            SurveyQuestion sq = new SurveyQuestion();
            sq.setSurvey(survey);
            sq.setTitle(q.getTitle());
            sq.getChoices().addAll(q.getChoices().stream().map(c -> {
                SurveyChoice sc = new SurveyChoice();
                sc.setQuestion(sq);
                sc.setTitle(c);
                return sc;
            }).collect(Collectors.toList()));
            return sq;
        }).collect(Collectors.toList()));
        return survey;
    }
}