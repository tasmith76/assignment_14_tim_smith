package com.coderscampus.assignment14.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.demo.domain.Channel;
import com.coderscampus.assignment14.demo.repository.ChannelRepository;

@Service
public class ChannelService {
	@Autowired
	private ChannelRepository channelRepo;

	public Channel createChannel(Channel channel) {
		channel.setName("General");
		return channelRepo.save(channel);
	}

	public Channel findById(Long channelId) {
		return channelRepo.findByChannelId(channelId);
	}

	public List<Channel> findAll() {
		return channelRepo.findAll();
	}

	public void createCustomChannel(Channel channel) {
		channelRepo.save(channel);
	}
}
