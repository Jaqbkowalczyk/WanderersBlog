package com.fdmgroup.WanderersBlog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Subscription;
import com.fdmgroup.WanderersBlog.model.User;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	List<Subscription> findByUser(User user);

	List<Subscription> findByCreator(User creator);

	Optional<Subscription> findByUserAndCreator(User user, User creator);

}
