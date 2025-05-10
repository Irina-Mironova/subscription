package ru.irina.subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.irina.subscription.models.Subscription;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query(value = """
            SELECT s.title 
            FROM subscriptions s JOIN users_subscriptions us ON s.id = us.subscription_id
            GROUP BY s.title 
            ORDER BY COUNT(us.user_id) DESC 
            LIMIT 3
          """, nativeQuery = true)
    List<String> getTop3Subscriptions();
}
