package br.com.maurigvs.surveyapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;

@Schema
public record ErrorResponse(
        HttpStatus error,
        String... message
){

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse that)) return false;
        return Objects.equals(error, that.error) && Objects.deepEquals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, Arrays.hashCode(message));
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + error + '\'' +
                ", message=" + String.join(", ", message) +
                '}';
    }
}
