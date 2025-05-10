package ru.irina.subscription.converters;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.irina.subscription.DTO.SubscriptionDTO;
import ru.irina.subscription.models.Subscription;

@Component
public class SubscriptionConverter {
    public SubscriptionDTO entityToDTO(@Nonnull Subscription subscription){
        return new SubscriptionDTO(subscription.getId(),
                subscription.getTitle(),
                subscription.getDescription()
        );
    }

    public Subscription DTOToEntity(Subscription entity, @Nonnull SubscriptionDTO dto){
        if (entity == null) {
            entity = new Subscription();
        }

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
