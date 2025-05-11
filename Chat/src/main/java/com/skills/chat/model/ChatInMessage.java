package com.skills.chat.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ChatInMessage {
    private String senderId;
    private String receiverId;
    private String message;
    private String messageType;
    private LocalDateTime sendTime;
}
