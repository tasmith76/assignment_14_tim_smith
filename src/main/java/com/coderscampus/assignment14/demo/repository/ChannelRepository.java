package com.coderscampus.assignment14.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderscampus.assignment14.demo.domain.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

	public Channel findByChannelId(Long channelId);
}
