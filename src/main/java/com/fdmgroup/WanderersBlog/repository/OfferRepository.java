package com.fdmgroup.WanderersBlog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	List<Offer> findByTitle(String title);

	List<Offer> findByLocation(String location);

	List<Offer> findByLocationIgnoreCase(String location);

	List<Offer> findByUser(User user);

	List<Offer> findByDateBetween(Date startDate, Date endDate);

	List<Offer> findByStartDateGreaterThanEqual(Date startDate);

	List<Offer> findByEndDateLessThanEqual(Date endDate);

	List<Offer> findByTitleIgnoreCaseContaining(String filters);

	List<Offer> findByDescriptionIgnoreCase(String filters);

	List<Offer> findByLocationIgnoreCaseContaining(String filters);

	List<Offer> findByDescriptionIgnoreCaseContaining(String filters);

	List<Offer> findByHowManyLookingForGreaterThanEqual(int howManyLookingFor);

}
