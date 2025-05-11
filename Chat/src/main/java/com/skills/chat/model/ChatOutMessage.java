package com.skills.chat.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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
