package com.skills.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter @Setter
public class ChatInMessage {
    private String senderId;
    private String receiverId;
    private String message;
    private String messageType;
    private LocalDateTime sendTime;

    public ChatInMessage(String message) {
        this.message = message;
    }
}
