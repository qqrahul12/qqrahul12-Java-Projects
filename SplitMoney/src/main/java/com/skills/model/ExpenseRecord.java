package com.skills.model;

import com.skills.util.ExpenseType;

public record ExpenseRecord(String expenseId, ExpenseType type, Double amount, String name, String paidBy) {
    public static ExpenseRecord fromExpense(Expense expense) {
        return new ExpenseRecord(expense.getId(), expense.getType(), expense.getAmount(), expense.getName(), expense.getPaidBy());
    }
}
