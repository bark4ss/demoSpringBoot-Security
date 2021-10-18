package com.example.demospringboot.controller;


import com.example.demospringboot.model.Message;
import com.example.demospringboot.repository.MessageRepo;
import com.example.demospringboot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping()
    public ModelAndView main(ModelAndView modelAndView) {
        Iterable<Message> messages = messageService.findAll();

        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("messages");

        return modelAndView;
    }

    @PostMapping()
    public ModelAndView add(@RequestParam String text, @RequestParam String tag, ModelAndView modelAndView) {
        Message message = new Message(text, tag);

        messageService.save(message);

        Iterable<Message> messages = messageService.findAll();

        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("messages");

        return modelAndView;
    }

    @PostMapping("/filter")
    public ModelAndView filter(@RequestParam String filter, ModelAndView modelAndView) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageService.findByCriteria(filter,filter);
        } else {
            messages = messageService.findAll();
        }

        modelAndView.addObject("messages", messages);
        modelAndView.setViewName("messages");

        return modelAndView;
    }
}
