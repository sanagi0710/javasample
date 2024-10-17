package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@Transactional
	public Message saveMessage(Message message) {
		return messageRepository.save(message);
	}

	@Transactional
	public List<Message> getAllMessage() {
		return messageRepository.findAll();
	}

	@Transactional
	public void deleteAllMessage() {
		messageRepository.deleteAll();
	}

	@Transactional
	public void deleteMessage(Long id) {
		messageRepository.deleteById(id);
	}

	public void updateMessage(Long id, Message updatedMessage) {
		Optional<Message> optionalMessage = messageRepository.findById(id);
		if (optionalMessage.isPresent()) {
			Message existingMessage = optionalMessage.get();
			existingMessage.setContent(updatedMessage.getContent()); // メッセージ内容を更新
			messageRepository.save(existingMessage); // 更新を保存
		} else {
			throw new RuntimeException("Message not found for id: " + id); // メッセージが見つからない場合
		}
	}

}
