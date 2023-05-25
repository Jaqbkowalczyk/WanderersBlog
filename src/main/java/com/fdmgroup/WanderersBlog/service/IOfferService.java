package com.fdmgroup.WanderersBlog.service;

import java.util.Date;
import java.util.List;

import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;

public interface IOfferService {

	List<Offer> findByTitle(String title);

	List<Offer> findByUser(User user);

	List<Offer> findByLocation(String location);

	List<Offer> findByLocationIgnoreCase(String location);

	List<Offer> filterOffers(String filter);

	List<Offer> findAllOffers();

	void saveOffer(Offer offer);

	Offer findById(int id);

	List<Offer> findByDateRange(Date startDate, Date endDate);

	List<Offer> findByStartDate(Date startDate);

	List<Offer> findByEndDate(Date endDate);

	void deleteOffer(Offer offer);


	List<Offer> findByHowManyLookingFor(int howManyLookingFor);

	List<Offer> getAllOffersSortedByDate();
}
