package com.skills.model;

import com.skills.request.CreateGroupRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@Entity @Table(name = "groups")
public class Group {
    @Id
    private String id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    private String createdAt;

    public Group() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.description = "";
        this.createdAt = "";
    }

    public Group(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now().toString();
    }

    public static Group fromGroupRequest(CreateGroupRequest request) {
        return new Group(request.name(), request.description());
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

