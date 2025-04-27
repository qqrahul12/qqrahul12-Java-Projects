package com.skills.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_expense_mapping")
@Getter @Setter
public class UserExpenseMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
    
    private Double shareAmount;
    private Boolean isPaid = false;

    private String paidBy;
    
    public UserExpenseMapping() {
        this.id = UUID.randomUUID().toString();
    }
}
