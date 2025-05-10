package ru.irina.subscription.converters;

import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;
import ru.irina.subscription.DTO.UserDTO;
import ru.irina.subscription.models.User;

@Component
public class UserConverter {
    public UserDTO entityToDTO(@Nonnull User user){
        return new UserDTO(user.getId(),
                           user.getUsername(),
                           user.getEmail(),
                           user.getPhone()
                          );
    }

    public User DTOToEntity(User entity, @Nonnull UserDTO dto) {
        if (entity == null) {
            entity = new User();
        }

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        return entity;
    }
}
