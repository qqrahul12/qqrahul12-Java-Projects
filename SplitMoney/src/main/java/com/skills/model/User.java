package com.skills.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
    }
    public User(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_groups", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserExpenseMapping> userExpenseMappings = new HashSet<>();
}
