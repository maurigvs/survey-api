package br.com.maurigvs.surveyapi.controller;

import java.util.Properties;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/properties")
@Slf4j
public class PropertiesController {

    @GetMapping
    public ResponseEntity<Properties> getProperties(){

        Properties result = System.getProperties();

        if(log.isDebugEnabled()) log.info("Get Properties response: {}", result);    
        else log.info("Get Properties response");
        
        return ResponseEntity.ok(result);
    }
}