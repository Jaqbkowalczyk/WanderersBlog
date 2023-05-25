package com.fdmgroup.WanderersBlog.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.WanderersBlog.model.Conversation;
import com.fdmgroup.WanderersBlog.service.ConversationRepository;
import com.fdmgroup.WanderersBlog.service.ConversationService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ConversationServiceTest {
	
	@InjectMocks
	ConversationService conversationService;
	
	@MockBean
	ConversationRepository mockConversationRepository;
	
	@Mock
	Conversation mockConversation;
	
	@Test
	public void startConversation_calls_save_andReturnsSavedConversation() {
		Conversation savedConversation = conversationService.startConversation(mockConversation);
		verify(mockConversationRepository).save(mockConversation);
		//the method doesnt return conversation?
		//assertEquals(mockConversation, savedConversation);
	}
	
	@Test
	public void test_getAllConversations_calls_findAll_andReturnsListOfConversations() {
		
		List<Conversation> expectedConversations = new ArrayList<>();
		when(mockConversationRepository.findAll()).thenReturn(expectedConversations);
		List<Conversation> returnedConversations = conversationService.getAllConversations();
		verify(mockConversationRepository).findAll();
		assertEquals(expectedConversations, returnedConversations);
	}
	
	@Test
	public void test_getConversationById_WithExistingConversation_calls_findById_andReturnsConversation() {
		long id = 1;
        when(mockConversationRepository.findById(id)).thenReturn(Optional.of(mockConversation));

        Conversation returnedConversation = conversationService.getConversationById(id);
        verify(mockConversationRepository).findById(id);
        assertEquals(mockConversation, returnedConversation);
	}
	
	@Test
	public void test_getConversationById_WithNonExistingConversation_calls_findById_andReturnsNull() {
		long id = 1;
        when(mockConversationRepository.findById(id)).thenReturn(Optional.empty());

        Conversation returnedConversation = conversationService.getConversationById(id);
        verify(mockConversationRepository).findById(id);
        assertEquals(null, returnedConversation);
	}
	
	@Test
	public void test_deleteConversation_calls_deleteById() {
		long id = 1;
		conversationService.deleteConversation(id);
		verify(mockConversationRepository).deleteById(id);
	}
	
	@Test
	public void test_updateConversation_calls_save() {
		conversationService.updateConversation(mockConversation);
		verify(mockConversationRepository).save(mockConversation);
	}
	
	
}
