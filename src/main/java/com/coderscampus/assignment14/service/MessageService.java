package com.coderscampus.assignment14.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.dao.chatConnect;
import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.repository.MessageRepository;

@Service
public class MessageService { 
@Autowired
private MessageRepository messageRepo;
@Autowired
private ChannelService channelService;
@Autowired
private UserService userService;

	
	public void createMessage(chatConnect message, Long channelId) {
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


	public List<chatConnect> getMessageByChannelId(Long channelId) {
		List<Message> messageList = messageRepo.findAllByChannelId(channelId);
		List<chatConnect> messagesDto = new ArrayList<>();
		for (Message message:messageList) {
			chatConnect messageDto = new chatConnect();
			messageDto.setMessage(message.getMessage());
			messageDto.setUserId(message.getUser().getUserId());
			messageDto.setChannelId(message.getMessageId());
			messageDto.setUsername(message.getUser().getUsername());
			messagesDto.add(messageDto);
		}
		return messagesDto;
	}
}	

