package com.skills.model;

import com.skills.request.CreateExpenseRequest;
import com.skills.util.ExpenseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter @Setter
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private ExpenseType type;
    private String name;
    private Double amount;
    private String paidBy;

    public Expense() {
        this.type = ExpenseType.GROUP;
        this.name = "";
        this.amount = 0.0;
        this.paidBy = "";
    }
    public Expense(String name, Double amount, String paidBy, ExpenseType type) {
        this.type = type;
        this.name = name;
        this.amount = amount;
        this.paidBy = paidBy;
    }

    public static Expense from(CreateExpenseRequest request) {
        Expense expense = new Expense();
        expense.setType(request.expenseType());
        expense.setName(request.name());
        expense.setAmount(request.amount());
        expense.setPaidBy(request.paidBy());
        return expense;
    }

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "expense", fetch = FetchType.LAZY)
    private Set<UserExpenseMapping> userExpenseMappings = new HashSet<>();
}
