package ru.irina.subscription.services.impl;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.DTO.UserDTO;
import ru.irina.subscription.converters.SubscriptionConverter;
import ru.irina.subscription.converters.UserConverter;
import ru.irina.subscription.models.Subscription;
import ru.irina.subscription.models.User;
import ru.irina.subscription.repositories.UserRepository;
import ru.irina.subscription.services.api.BaseService;
import ru.irina.subscription.services.api.SubscriptionService;
import ru.irina.subscription.services.api.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl extends BaseService<User, Long> implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final SubscriptionService subscriptionService;
    private final SubscriptionConverter subscriptionConverter;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
                           MessageSource messageSource, SubscriptionService subscriptionService,
                           SubscriptionConverter subscriptionConverter) {
        super(messageSource);

        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.subscriptionService = subscriptionService;
        this.subscriptionConverter = subscriptionConverter;
    }

    @Override
    public CrudRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User create(@Nonnull UserDTO userDto) {
        User user = userConverter.DTOToEntity(null, userDto);

        return save(user);
    }

    @Override
    @Transactional
    public User update(@Nonnull UserDTO userDto) {
        User user = userConverter.DTOToEntity(findById(userDto.getId()), userDto);

        return save(user);
    }

    @Override
    public void addSubscription(@Nonnull Long userId, @Nonnull Long subscriptionId) {
        User user = findById(userId);
        Subscription subscription = subscriptionService.findById(subscriptionId);

        subscription.getUsers().add(user);
        user.getSubscriptions().add(subscription);

        subscriptionService.save(subscription);
    }

    @Override
    public Set<SubscriptionDTO> getSubscriptions(@Nonnull Long userId) {
        User user = findById(userId);

        return user.getSubscriptions().stream()
                .map(subscriptionConverter :: entityToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void deleteSubscriptionById(@Nonnull Long userId, @Nonnull Long subscriptionId) {
        User user = findById(userId);
        Subscription subscription = subscriptionService.findById(subscriptionId);

        user.getSubscriptions().remove(subscription);
        subscription.getUsers().remove(user);

        subscriptionService.save(subscription);
    }

    @Override
    @Transactional
    public void deleteById(@Nonnull Long id) {
        User user = findById(id);

        userRepository.deleteUserSubscriptions(id);
        userRepository.delete(user);
    }
}
