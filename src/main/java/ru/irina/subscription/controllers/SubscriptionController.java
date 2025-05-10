package ru.irina.subscription.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.converters.SubscriptionConverter;
import ru.irina.subscription.services.api.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Подписки", description = "Методы работы с подписками")
@Slf4j
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    private final SubscriptionConverter subscriptionConverter;


    @Operation(
            summary = "Запрос на получение подписки по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = SubscriptionDTO.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public SubscriptionDTO findById(@PathVariable
                            @Parameter(description = "Идентификатор подписки", required = true) Long id){
        return subscriptionConverter.entityToDTO(subscriptionService.findById(id));
    }


    @Operation(
            summary = "Запрос на вывод ТОП-3 популярных подписок",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping("/top")
    public List<String> getTop3Subscriptions(){

        return subscriptionService.getTop3Subscriptions();
    }

}
