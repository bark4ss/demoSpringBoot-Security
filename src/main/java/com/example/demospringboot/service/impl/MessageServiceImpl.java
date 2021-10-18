package com.example.demospringboot.service.impl;

import com.example.demospringboot.model.Message;
import com.example.demospringboot.repository.MessageRepo;
import com.example.demospringboot.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    @Override
    public List<Message> findByCriteria(String tag, String text) {

        return messageRepo.findByTagContainingOrTextContaining(tag,text);
    }

    @Override
    public void save(Message message) {
        messageRepo.save(message);
    }
}
