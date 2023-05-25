package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Offer;
import com.fdmgroup.WanderersBlog.model.User;
import com.fdmgroup.WanderersBlog.repository.OfferRepository;
import com.fdmgroup.WanderersBlog.service.OfferService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class OfferServiceTest {
	@InjectMocks
	OfferService offerService;
	
	@MockBean
	OfferRepository mockOfferRepository;
	
	@Mock
	User mockUser;
	
	@Mock
	Offer mockOffer;
	
	List<Offer> expectedOffers;
	
	@BeforeEach
	public void setUp() {
		 expectedOffers = Arrays.asList(new Offer(),new Offer(),new Offer());
	}
	
	@Test
	public void test_findByTitle_calls_findByTitle_andReturnsListOfOffers() {

		String title = "test title";
		when(mockOfferRepository.findByTitle(title)).thenReturn(expectedOffers);
		List<Offer> offers = offerService.findByTitle(title);
		verify(mockOfferRepository, times(1)).findByTitle(title);
		assertEquals(expectedOffers, offers);
	}
	
	@Test
	public void test_findByUser_calls_findByUser_andReturnsListOfOffers() {
		when(mockOfferRepository.findByUser(mockUser)).thenReturn(expectedOffers);
		List<Offer> offers = offerService.findByUser(mockUser);
		verify(mockOfferRepository, times(1)).findByUser(mockUser);
		assertEquals(expectedOffers, offers);
	}
	
	@Test
	public void test_findByLocation_calls_findByLocation_andReturnsListOfOffers() {
		when(mockOfferRepository.findByLocation("testLocation")).thenReturn(expectedOffers);
		List<Offer> offers = offerService.findByLocation("testLocation");
		verify(mockOfferRepository, times(1)).findByLocation("testLocation");
		assertEquals(expectedOffers, offers);
	}
	
	@Test
	public void test_findByLocationIgnoreCase_calls_findByLocationIgnoreCase_andReturnsListOfOffers() {
		when(mockOfferRepository.findByLocationIgnoreCase("testLocation")).thenReturn(expectedOffers);
		List<Offer> offers = offerService.findByLocationIgnoreCase("testLocation");
		verify(mockOfferRepository, times(1)).findByLocationIgnoreCase("testLocation");
		assertEquals(expectedOffers, offers);
	}
	
	@Test
	public void test_findAllOffers_calls_findAll_methodOf_OfferRepository() {
		when(mockOfferRepository.findAll()).thenReturn(expectedOffers);
		List<Offer> offers = offerService.findAllOffers();
		verify(mockOfferRepository, times(1)).findAll();
		assertEquals(expectedOffers, offers);
	}
	
	@Test
	public void test_saveOffer_calls_save_methodOf_OfferRepository() {
		offerService.saveOffer(mockOffer);
		verify(mockOfferRepository, times(1)).save(mockOffer);	
	}
	
	@Test
	public void test_findById_calls_findById_andReturnsListOfOffers() {
		int id = 1;
	    Offer expectedOffer = new Offer();
	    expectedOffer.setId(id);
	    when(mockOfferRepository.findById(id)).thenReturn(Optional.of(expectedOffer));
	    Offer offer = offerService.findById(1);
		verify(mockOfferRepository, times(1)).findById(1);
		assertEquals(expectedOffer, offer);
	}
	
	@Test
	public void test_findByDateRange_calls_findByDateBetween_andReturnsListOfOffers() {
		Date startDate = new Date();
        Date endDate = new Date();
        when(mockOfferRepository.findByDateBetween(startDate, endDate)).thenReturn(expectedOffers);
        List<Offer> offers = offerService.findByDateRange(startDate, endDate);
		verify(mockOfferRepository, times(1)).findByDateBetween(startDate, endDate);
		assertEquals(expectedOffers, offers);
	}
	
    @Test
    public void testFindByStartDate_calls_findByStartDateGreaterThanEqual_andReturnsListOfOffers() {
        Date startDate = new Date();
        when(mockOfferRepository.findByStartDateGreaterThanEqual(startDate)).thenReturn(expectedOffers);
        List<Offer> offers = offerService.findByStartDate(startDate);
        verify(mockOfferRepository).findByStartDateGreaterThanEqual(startDate);
        assertEquals(expectedOffers, offers);
    }
    
    @Test
    public void testFindByEndDate_calls_findByEndDateLessThanEqual_andReturnsListOfOffers() {
        Date endDate = new Date();
        when(mockOfferRepository.findByEndDateLessThanEqual(endDate)).thenReturn(expectedOffers);
        List<Offer> offers = offerService.findByEndDate(endDate);
        verify(mockOfferRepository).findByEndDateLessThanEqual(endDate);
        assertEquals(expectedOffers, offers);
    }
    
}
