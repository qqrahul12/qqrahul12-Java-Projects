package com.skills.chat.controller;

import com.skills.chat.model.ChatInMessage;
import com.skills.chat.model.ChatOutMessage;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/receive")
    @SendTo("/chat/send")
    public ChatOutMessage handle(ChatInMessage message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Message received: " + message.getMessage());
        return new ChatOutMessage(message.getMessage());
    }

    @MessageMapping("/isTyping")
    @SendTo("/chat/typing")
    public ChatOutMessage handleIsTyping(ChatInMessage message) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("User is typing: " + message.getSenderId());
        return new ChatOutMessage(message.getMessage());
    }

    @MessageExceptionHandler
    @SendTo("/chat/errors")
    public ChatOutMessage handleException(Throwable exception) {
        System.out.println("Error: " + exception.getMessage());
        return new ChatOutMessage("Error: " + exception.getMessage());
    }
}
