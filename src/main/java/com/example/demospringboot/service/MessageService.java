package com.example.demospringboot.service;

import com.example.demospringboot.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();
    List<Message> findByCriteria(String tag, String text);
    void save(Message message);
}
