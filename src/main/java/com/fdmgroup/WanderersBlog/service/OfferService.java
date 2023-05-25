package com.fdmgroup.WanderersBlog.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.OfferRepository;

@Service
public class OfferService implements IOfferService {

	@Autowired
	private OfferRepository repo;

	@Override
	public List<Offer> findByTitle(String title) {
		return repo.findByTitle(title);
	}

	@Override
	public List<Offer> findByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public List<Offer> findByLocation(String location) {
		return repo.findByLocation(location);
	}

	@Override
	public List<Offer> findByLocationIgnoreCase(String location) {
		return repo.findByLocationIgnoreCase(location);
	}

	@Override
	public List<Offer> filterOffers(String filter) {
		String[] filtersArray = filter.split(" ");
		List<List<Offer>> results = new ArrayList<List<Offer>>();

		for (String filters : filtersArray) {
			List<Offer> filteredByTitle = repo.findByTitleIgnoreCaseContaining(filters);
			List<Offer> filteredByLocation = repo.findByLocationIgnoreCaseContaining(filters);
			List<Offer> filteredByDescription = repo.findByDescriptionIgnoreCaseContaining(filters);

			List<Offer> filteredOffers = new ArrayList<>();
			Stream.of(filteredByTitle, filteredByLocation, filteredByDescription).forEach(filteredOffers::addAll);
			results.add(filteredOffers);
		}

		List<Offer> finalFilteredOffers = new ArrayList<Offer>();
		finalFilteredOffers.addAll(results.get(0));
		for (ListIterator<List<Offer>> iter = results.listIterator(0); iter.hasNext();) {
			finalFilteredOffers.retainAll(iter.next());
		}

		return finalFilteredOffers.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Offer> findAllOffers() {
		return repo.findAll();
	}

	@Override
	public void saveOffer(Offer offer) {
		repo.save(offer);

	}

	@Override
	public Offer findById(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public List<Offer> findByDateRange(Date startDate, Date endDate) {

		return repo.findByDateBetween(startDate, endDate);
	}

	@Override
	public List<Offer> findByStartDate(Date startDate) {

		return repo.findByStartDateGreaterThanEqual(startDate);
	}

	@Override
	public List<Offer> findByEndDate(Date endDate) {

		return repo.findByEndDateLessThanEqual(endDate);
	}

	@Override
	public void deleteOffer(Offer offer) {
		repo.delete(offer);

	}

	@Override
	public List<Offer> findByHowManyLookingFor(int howManyLookingFor) {

		return repo.findByHowManyLookingForGreaterThanEqual(howManyLookingFor);
	}

	@Override
	public List<Offer> getAllOffersSortedByDate() {
		List<Offer> sortedOffers = repo.findAll();
		Collections.sort(sortedOffers, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
		return sortedOffers;

	}

}
