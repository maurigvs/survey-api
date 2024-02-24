package br.com.maurigvs.surveyapi.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class LocalDateTimeMapper implements Function<LocalDateTime, String> {

    private static final String FORMAT = "dd/MM/yyyy HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    @Override
    public String apply(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER);
    }
}
