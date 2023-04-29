package br.com.maurigvs.surveyapi.controller;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    Logger log = LoggerFactory.getLogger(PropertiesController.class);

    @GetMapping
    public ResponseEntity<Properties> getProperties(){

        Properties result = System.getProperties();

        if(log.isDebugEnabled()){
            log.info("Get Properties response: {}", result);    
        } else {
            log.info("Get Properties response");
        }
        
        return ResponseEntity.ok(result);
    }
}