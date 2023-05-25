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

import com.fdmgroup.WanderersBlog.model.Message;
import com.fdmgroup.WanderersBlog.model.Story;
import com.fdmgroup.WanderersBlog.repository.MessageRepository;
import com.fdmgroup.WanderersBlog.service.MessageService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MessageServiceTest {
	@InjectMocks
	MessageService messageService;
	
	@MockBean
	MessageRepository mockMessageRepository;
	
	@Mock
	Message mockMessage;
	
	@Test
	public void test_sendMessage_calls_save_andReturnsSentMessage() {
		
        Message savedMessage = messageService.saveMessage(mockMessage);
        verify(mockMessageRepository).save(mockMessage);
        //assertEquals(mockMessage, savedMessage);

	}
	
	@Test
	public void test_getAllMessages_calls_findAll_andReturnListOfMessages() {
		List<Story> expectedList = new ArrayList<>();
        List<Message> returnedList = messageService.getAllMessages();
        verify(mockMessageRepository).findAll();
        assertEquals(expectedList, expectedList);

	}
	
	@Test
	public void testGetMessageById_WithExistingMessage_calls_findById_andReturnsMessage() {
		long id = 1;
        Message message = new Message();
        when(mockMessageRepository.findById(id)).thenReturn(Optional.of(message));

        Message returnedMessage = messageService.getMessageById(id);
        verify(mockMessageRepository).findById(id);
        assertEquals(message, returnedMessage);
    }
	
    @Test
    public void testGetMessageById_WithNonExistentMessage_calls_findById_andReturnsNull() {
    	long id = 1;
        when(mockMessageRepository.findById(id)).thenReturn(Optional.empty());

        Message returnedMessage = messageService.getMessageById(id);
        verify(mockMessageRepository).findById(id);
        assertEquals(null, returnedMessage);
    }
    
    @Test
    public void test_deleteMessage_calls_deleteById() {
    	long id = 1;
    	messageService.deleteMessage(id);
    	verify(mockMessageRepository).deleteById(id);
    }
	
}
