package ru.irina.subscription.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Schema(description = "Модель данных подписки")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    @Schema(description = "Id подписки", example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long id;

    @Schema(description = "ФИО пользователя", maxLength = 50, minLength = 3, example = "Пушкин Александр Сергеевич")
    @Size(min = 3, max = 50, message = "{valid.message50.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String title;

    @Size(min = 0,max = 200, message = "{valid.message200.size}")
    private String description;
}
