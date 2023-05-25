package com.fdmgroup.WanderersBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findBySender(User user);

	List<Message> findByRecipient(User user);

}
