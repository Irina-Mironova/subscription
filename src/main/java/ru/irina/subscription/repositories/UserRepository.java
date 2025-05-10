package ru.irina.subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.irina.subscription.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "DELETE FROM users_subscriptions WHERE user_id = :userId", nativeQuery = true)
    void deleteUserSubscriptions(@Param("userId") Long userId);
}
