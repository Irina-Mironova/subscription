package ru.irina.subscription.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Schema(description = "Модель данных пользователя")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    @Schema(description = "Id пользователя", example = "1")
    @NotNull(message = "{valid.message.empty}")
    private Long id;

    @Schema(description = "ФИО пользователя", maxLength = 100, minLength = 3, example = "Пушкин Александр Сергеевич")
    @Size(min = 3, max = 100, message = "{valid.message100.size}")
    @NotBlank(message = "{valid.message.empty}")
    private String username;

    @Schema(description = "Email пользователя", example = "user@example.com")
    @NotBlank(message = "{valid.message.empty}")
    @Email(message = "{valid.message.email}")
    @Size(max = 80, message = "{valid.message80.size}")
    private String email;

    @Schema(description = "Телефон", example = "+79174363697")
    @Pattern(regexp = "^(\\+7)([0-9]{10})$", message = "{valid.message.phone}")
    private String phone;

}
