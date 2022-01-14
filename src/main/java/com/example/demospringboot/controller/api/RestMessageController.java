package com.example.demospringboot.controller.api;

import com.example.demospringboot.model.Message;
import com.example.demospringboot.service.MessageService;
import com.example.demospringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestMessageController {

    private MessageService messageService;

    @Autowired
    public RestMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/messages", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Message>> getAll() {
        List<Message> messages = messageService.findAll();
        HttpStatus status;
        if (messages != null && !messages.isEmpty()) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(messages, status);
    }

    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<Message> getById(@PathVariable(name = "id") int id) {
        Message message = messageService.getById(id);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("secret", "temp");
        return message != null ? new ResponseEntity<>(message, headers, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/messages")
    public ResponseEntity<?> create(@RequestBody Message message) {
        messageService.save(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
