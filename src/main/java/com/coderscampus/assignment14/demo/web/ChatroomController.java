package com.coderscampus.assignment14.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.assignment14.demo.domain.Channel;
import com.coderscampus.assignment14.demo.domain.User;
import com.coderscampus.assignment14.demo.dto.messageDto;
import com.coderscampus.assignment14.demo.service.ChannelService;
import com.coderscampus.assignment14.demo.service.MessageService;
import com.coderscampus.assignment14.demo.service.UserService;

@Controller
public class ChatroomController {
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/")
	public String directToWelcome() {
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public String welcomePage(ModelMap model) {
		Channel channel = new Channel();
		List<Channel> allChannels = channelService.findAll();
		if(allChannels.size() == 0) {
			channel = channelService.createChannel(channel);
		}else {
			channel = allChannels.get(0);
		}
		model.put("channel", channel);
		model.put("channels", allChannels);
		return "welcome";
	}
	@ResponseBody
	@PostMapping("/welcome/createuser")
	public User createUser(@RequestBody String username, Channel channel) {
		return userService.createUser(username, channel);
	}
	@GetMapping("/createChannel")
	public String createChannel(ModelMap model) {
		Channel channel = new Channel();
		model.put("channel", channel);
		return "createChannel";
	}
	
	@PostMapping("/createChannel")
	public String createChannel(Channel channelSubmission) {
		channelService.createCustomChannel(channelSubmission);
		return "redirect:/welcome";
	}
	@GetMapping("/channels/{channelId}")
		public String showChannels(ModelMap model, @PathVariable Long channelId) {
		Channel channel = channelService.findById(channelId);
		model.put("channel", channel);
		return "channels";
	}
	@ResponseBody
	@PostMapping("/messageSent/{channelId}")
		private void messageReceived (@RequestBody messageDto message, @PathVariable Long channelId) {
		messageDto messageDto = new messageDto();
		System.out.println(message.getChannelId());
		messageDto.setChannelId(message.getChannelId());
		messageDto.setMessage(message.getMessage());
		messageDto.setUserId(message.getUserId());
		messageService.createMessage(message,channelId);
		
	}
	@ResponseBody
	@PostMapping("/obtainMessages/{channelId}")
		private List<messageDto> obtainMessages(@PathVariable Long channelId) {
			
			return messageService.getMessageByChannelId(channelId);
		
	}
}
