package com.coderscampus.assignment14.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.demo.domain.Channel;
import com.coderscampus.assignment14.demo.domain.Message;
import com.coderscampus.assignment14.demo.domain.User;
import com.coderscampus.assignment14.demo.dto.messageDto;
import com.coderscampus.assignment14.demo.repository.MessageRepository;

@Service
public class MessageService {
@Autowired
private MessageRepository messageRepo;
@Autowired
private ChannelService channelService;
@Autowired
private UserService userService;

	
	public void createMessage(messageDto message, Long channelId) {
		Channel channel = channelService.findById(channelId);
		Message newMessage = new Message();
		User user = new User();
		user = userService.findByUserId(message.getUserId());
		newMessage.setUser(user);
		newMessage.setMessage(message.getMessage());
		newMessage.setChannel(channel);
		messageRepo.save(newMessage);
		
	}


//	public List<messageDto> getAllMessages() {
//		
//		List<Message> messageList = messageRepo.findAll();
//		List<messageDto> messagesDto = new ArrayList<>();
//		for (Message message:messageList) {
//			messageDto messageDto = new messageDto();
//			messageDto.setMessage(message.getMessage());
//			messageDto.setUserId(message.getUser().getUserId());
//			messageDto.setChannelId(message.getMessageId());
//			messageDto.setUsername(message.getUser().getUsername());
//			messagesDto.add(messageDto);
//		}
//		return messagesDto;
//	}


	public List<messageDto> getMessageByChannelId(Long channelId) {
		List<Message> messageList = messageRepo.findAllByChannelId(channelId);
		List<messageDto> messagesDto = new ArrayList<>();
		for (Message message:messageList) {
			messageDto messageDto = new messageDto();
			messageDto.setMessage(message.getMessage());
			messageDto.setUserId(message.getUser().getUserId());
			messageDto.setChannelId(message.getMessageId());
			messageDto.setUsername(message.getUser().getUsername());
			messagesDto.add(messageDto);
		}
		return messagesDto;
	}
}	

