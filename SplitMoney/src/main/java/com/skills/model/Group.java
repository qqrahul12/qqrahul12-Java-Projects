package com.skills.model;

import com.skills.request.CreateGroupRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Random;

@Getter @Setter
@Entity @Table(name = "groups")
public class Group {
    @Id
    private String id;
    private String name;
    private String description;

    @Column(length = 12, unique = true, nullable = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    private String createdAt;

    private static final Random RANDOM = new Random();

    public Group() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.createdAt = "";
        this.code = generateRandomCode();
    }

    public Group(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now().toString();
        this.code = generateRandomCode();
    }

    public static Group fromGroupRequest(CreateGroupRequest request) {
        return new Group(request.name(), request.description());
    }

    private static String generateRandomCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int idx = RANDOM.nextInt(chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();
    
    /**
     * Adds a user to this group and maintains the bidirectional relationship
     * by adding this group to the user's groups as well.
     * 
     * @param user The user to add to this group
     */
    public void addUser(User user) {
        this.users.add(user);
        user.getGroups().add(this);
    }
    
    /**
     * Removes a user from this group and maintains the bidirectional relationship
     * by removing this group from the user's groups as well.
     * 
     * @param user The user to remove from this group
     */
    public void removeUser(User user) {
        this.users.remove(user);
        user.getGroups().remove(this);
    }
}
