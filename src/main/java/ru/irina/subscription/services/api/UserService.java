package ru.irina.subscription.services.api;

import jakarta.annotation.Nonnull;
import org.springframework.validation.BindingResult;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.DTO.UserDTO;
import ru.irina.subscription.exceptions.ResourceNotFoundException;
import ru.irina.subscription.exceptions.ValidationException;
import ru.irina.subscription.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    /**
     * Находит пользователя по идентификатору или выбрасывает исключение ResourceNotFoundException,
     * если пользователь не найден.
     *
     * @param id идентификатор пользователя (не может быть null)
     * @return найденный пользователь или исключение ResourceNotFoundException, если пользователь
     * не существует
     * @throws ResourceNotFoundException, если пользователь не существует
     */
    User findById(Long id);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список пользователей (может быть пустым, но не null)
     */
    List<User> findAll();

    /**
     * Проверяет результаты валидации и генерирует исключение при наличии ошибок.
     *
     * @param bindingResult результаты валидации (не может быть null)
     * @throws ValidationException, если обнаружены ошибки валидации
     */
    void checkValidation(@Nonnull BindingResult bindingResult);

    /**
     * Создает нового пользователя на основе DTO.
     *
     * @param userDto DTO с данными пользователя (не может быть null)
     * @return созданный пользователь (с заполненным ID)
     * @throws ValidationException, если данные не проходят валидацию
     */
    User create(@Nonnull UserDTO userDto);

    /**
     * Обновляет данные существующего пользователя.
     *
     * @param userDto DTO с обновленными данными (не может быть null)
     * @return обновленный пользователь
     * @throws ResourceNotFoundException, если пользователь не найден
     * @throws ValidationException, если данные не проходят валидацию
     */
    User update(@Nonnull UserDTO userDto);

    /**
     * Удаляет пользователя по идентификатору.
     *
     * @param id идентификатор пользователя (не может быть null)
     * @throws ResourceNotFoundException, если пользователь не найден
     */
    void deleteById(@Nonnull Long id);

    /**
     * Добавляет подписку пользователю.
     *
     * @param userId идентификатор пользователя (не может быть null)
     * @param subscriptionId идентификатор подписки (не может быть null)
     * @throws ResourceNotFoundException, если пользователь не найден или подписка не найдена
     */
    void addSubscription(@Nonnull Long userId, @Nonnull Long subscriptionId);

    /**
     * Возвращает список подписок пользователя в формате DTO.
     *
     * @param userId идентификатор пользователя (не может быть null)
     * @return множество DTO подписок (может быть пустым, но не null)
     * @throws ResourceNotFoundException, если пользователь не найден
     */
    Set<SubscriptionDTO> getSubscriptions(@Nonnull Long userId);

    /**
     * Удаляет конкретную подписку у пользователя.
     *
     * @param userId идентификатор пользователя (не может быть null)
     * @param subscriptionId идентификатор подписки (не может быть null)
     * @throws ResourceNotFoundException, если пользователь не найден или подписка не найдена
     */
    void deleteSubscriptionById(@Nonnull Long userId, @Nonnull Long subscriptionId);
}
