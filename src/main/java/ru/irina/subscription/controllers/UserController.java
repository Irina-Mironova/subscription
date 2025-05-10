package ru.irina.subscription.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.DTO.UserDTO;
import ru.irina.subscription.converters.UserConverter;
import org.springframework.validation.BindingResult;
import ru.irina.subscription.services.api.UserService;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Методы работы с пользователями")
@Slf4j
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    @Operation(
            summary = "Запрос на получение пользователя по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable
                             @Parameter(description = "Идентификатор пользователя", required = true) Long id){
        return userConverter.entityToDTO(userService.findById(id));
    }


    @Operation(
            summary = "Запрос на добавление нового пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь успешно добавлен", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Validated UserDTO userDto, BindingResult bindingResult) {
        userService.checkValidation(bindingResult);

        return userConverter.entityToDTO(userService.create(userDto));
    }


    @Operation(
            summary = "Запрос на обновление данных о пользователе",
            responses = {
                    @ApiResponse(
                            description = "Информация о пользователе успешно обновлена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody @Validated UserDTO userDto, BindingResult bindingResult) {
        userService.checkValidation(bindingResult);

        return userConverter.entityToDTO(userService.update(userDto));
    }


    @Operation(
            summary = "Запрос на удаление пользователя по id",
            responses = {
                    @ApiResponse(
                            description = "Пользователь успешно удален", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Parameter(description = "Идентификатор пользователя", required = true) Long id) {
        userService.deleteById(id);
    }


    @Operation(
            summary = "Запрос на добавление новой подписки указанному пользователю",
            responses = {
                    @ApiResponse(
                            description = "Подписка успешно добавлена", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))
                    )
            }
    )
    @PostMapping("/{userId}/subscriptions/{subscriptionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubscription(@PathVariable @Parameter(description = "Идентификатор пользователя", required = true)
                                    Long userId,
                                @PathVariable @Parameter(description = "Идентификатор подписки", required = true)
                                    Long subscriptionId) {

        userService.addSubscription(userId, subscriptionId);
    }


    @Operation(
            summary = "Запрос на вывод всех подписок пользователя",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Set.class))
                    )
            }
    )
    @GetMapping("/{id}/subscriptions")
    public Set<SubscriptionDTO> getSubscriptions(@PathVariable
                            @Parameter(description = "Идентификатор пользователя", required = true) Long id){

        return userService.getSubscriptions(id);
    }


    @Operation(
            summary = "Запрос на удаление подписки пользователя",
            responses = {
                    @ApiResponse(
                            description = "Подписка успешно удалена", responseCode = "200"
                    )
            }
    )
    @DeleteMapping("/{userId}/subscriptions/{subscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @Parameter(description = "Идентификатор пользователя", required = true)
                           Long userId,
                       @PathVariable @Parameter(description = "Идентификатор подписки", required = true)
                           Long subscriptionId) {

        userService.deleteSubscriptionById(userId, subscriptionId);
    }

}
