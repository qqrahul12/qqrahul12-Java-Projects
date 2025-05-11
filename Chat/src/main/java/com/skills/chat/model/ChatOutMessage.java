package com.skills.chat.model;

import java.time.LocalDateTime;

public class ChatOutMessage {
    private String senderId;
    private String receiverId;
    private String content;
    private String messageType;
    private LocalDateTime sendTime;

    public ChatOutMessage(String content) {
        this.content = content;
    }
}
