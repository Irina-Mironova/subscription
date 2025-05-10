package ru.irina.subscription.services.api;

import jakarta.annotation.Nonnull;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.models.Subscription;

import java.util.List;

public interface SubscriptionService {
    /**
     * Находит подписку по идентификатору или выбрасывает исключение ResourceNotFoundException,
     * если подписка не найдена.
     *
     * @param id идентификатор подписки (не может быть null)
     * @return найденная подписка или исключение ResourceNotFoundException, если подписка
     * не существует
     */
    Subscription findById(Long id);

    /**
     * Возвращает список всех подписок.
     *
     * @return список подписок (может быть пустым)
     */
    List<Subscription> findAll();

    /**
     * Сохраняет или обновляет подписку.
     *
     * @param subscription подписка для сохранения (не может быть null)
     * @return сохранённая подписка (с обновлённым ID, если это новая подписка)
     */
    Subscription save(@Nonnull Subscription subscription);

    /**
     * Удаляет подписку.
     *
     * @param subscription подписка для удаления (не может быть null)
     */
    void delete(@Nonnull Subscription subscription);

    /**
     * Создаёт новую подписку на основе DTO.
     *
     * @param subscriptionDto DTO с данными для создания подписки (не может быть null)
     * @return созданная подписка
     */
    Subscription create(@Nonnull SubscriptionDTO subscriptionDto);

    /**
     * Возвращает топ-3 подписок.
     *
     * @return список названий подписок, отсортированный по убыванию популярности
     */
    List<String> getTop3Subscriptions();
}
