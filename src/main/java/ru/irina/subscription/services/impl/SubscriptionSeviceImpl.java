package ru.irina.subscription.services.impl;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.converters.SubscriptionConverter;
import ru.irina.subscription.models.Subscription;
import ru.irina.subscription.repositories.SubscriptionRepository;
import ru.irina.subscription.services.api.BaseService;
import ru.irina.subscription.services.api.SubscriptionService;

import java.util.List;

@Service
@Slf4j
public class SubscriptionSeviceImpl extends BaseService<Subscription, Long> implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionConverter subscriptionConverter;

    @Autowired
    public SubscriptionSeviceImpl(SubscriptionRepository subscriptionRepository,
                                  SubscriptionConverter subscriptionConverter, MessageSource messageSource) {
        super(messageSource);
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionConverter = subscriptionConverter;
    }

    @Override
    public CrudRepository<Subscription, Long> getRepository() {
        return subscriptionRepository;
    }

    @Override
    public Subscription create(@Nonnull SubscriptionDTO subscriptionDto) {
        Subscription subscription = subscriptionConverter.DTOToEntity(null, subscriptionDto);

        return save(subscription);
    }

    @Override
    public List<String> getTop3Subscriptions() {

        return subscriptionRepository.getTop3Subscriptions();
    }
}
