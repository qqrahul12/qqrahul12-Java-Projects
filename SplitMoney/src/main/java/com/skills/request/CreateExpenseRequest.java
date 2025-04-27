package com.skills.request;

import com.skills.util.ExpenseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateExpenseRequest(
        @NotBlank(message = "Expense type cannot be blank")
        ExpenseType expenseType,
        @NotBlank(message = "Expense name cannot be blank")
        String name,
        @Min(value = 0, message = "Expense amount must be greater than zero")
        @Max(value = Long.MAX_VALUE, message = "Expense amount is too large")
        Double amount,
        @NotBlank(message = "Expense must have at least one payer")
        String paidBy,
        @Size(min = 1, message = "Expense must have at least one user")
        List<@NotBlank(message = "userId can't be blank") String> userIds,
        String groupId
) {}
