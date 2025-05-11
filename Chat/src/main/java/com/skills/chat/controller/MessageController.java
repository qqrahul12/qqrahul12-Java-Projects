package com.skills.chat.controller;

import com.skills.chat.model.ChatInMessage;
import com.skills.chat.model.ChatOutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/receive")
    @SendTo("/user/send")
    public ChatOutMessage handle(ChatInMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new ChatOutMessage(message.getMessage());
    }

    @MessageMapping("/isTyping")
    @SendTo("/user/typing")
    public ChatOutMessage handleIsTyping(ChatInMessage message) throws InterruptedException {
        Thread.sleep(1000);
        return new ChatOutMessage(message.getMessage());
    }
}
