package br.com.maurigvs.surveyapi.controller;

import br.com.maurigvs.surveyapi.dto.requests.UserRequest;
import br.com.maurigvs.surveyapi.dto.responses.UserCreatedResponse;
import br.com.maurigvs.surveyapi.mapper.UserCreatedResponseMapper;
import br.com.maurigvs.surveyapi.mapper.UserMapper;
import br.com.maurigvs.surveyapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Tag(name = "user")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "new user created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserCreatedResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "request fields are missing; " +
                    "user already exists with same email")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserCreatedResponse> postUser(@RequestBody Mono<UserRequest> requestMono){
        return requestMono
                .map(new UserMapper(LocalDateTime.now()))
                .map(Mono::just)
                .flatMap(userService::create)
                .map(new UserCreatedResponseMapper());
    }
}
