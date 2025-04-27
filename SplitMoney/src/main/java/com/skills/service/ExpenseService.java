package com.skills.service;

import com.skills.model.Expense;
import com.skills.model.Group;
import com.skills.model.UserExpenseMapping;
import com.skills.repository.ExpenseRepository;
import com.skills.repository.GroupRepository;
import com.skills.repository.UserExpenseMappingRepository;
import com.skills.repository.UserRepository;
import com.skills.request.CreateExpenseRequest;
import com.skills.util.ExpenseType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserExpenseMappingRepository userExpenseMappingRepository;

    public Expense addExpense(CreateExpenseRequest expenseRequest) {
        Expense expense = Expense.from(expenseRequest);
        if(expenseRequest.expenseType() == ExpenseType.GROUP && expenseRequest.groupId() != null) {
            Optional<Group> group = groupRepository.findById(expenseRequest.groupId());
            if(group.isPresent()) {
                Group expenseGroup = group.get();
                boolean areValidGroupUsers = expenseRequest.userIds().stream().allMatch(
                        (userId) -> groupRepository.existsByIdAndUsersId(expenseGroup.getId(), userId)
                );
                boolean isValidPayer = groupRepository.existsByIdAndUsersId(
                        expenseGroup.getId(), expenseRequest.paidBy()
                );
                if(areValidGroupUsers && isValidPayer) {
                    expense.setGroup(group.get());
                } else {
                    throw new IllegalArgumentException("User does not belong to the group or is not a valid payer");
                }
            } else {
                throw new EntityNotFoundException("Group not found");
            }
        } else if(expenseRequest.expenseType() == ExpenseType.GROUP) {
            throw new IllegalArgumentException("Group ID is required for group expenses");
        } else {
            boolean areValidUsers = expenseRequest.userIds().stream().allMatch(
                    (userId) -> userRepository.existsById(userId)
            );
            if(!areValidUsers) {
                throw new IllegalArgumentException("User does not exist");
            }
        }
        Expense e = expenseRepository.save(expense);
        System.out.println("Expense created with ID: " + e.getId());

        // Add user expense mappings
        expenseRequest.userIds().forEach(userId -> {
            if (userRepository.existsById(userId)) {
                UserExpenseMapping userExpenseMapping = new UserExpenseMapping();
                userExpenseMapping.setUser(userRepository.findById(userId).get());
                userExpenseMapping.setExpense(e);
                userExpenseMapping.setPaidBy(expenseRequest.paidBy());
                userExpenseMapping.setIsPaid(userId.equals(expenseRequest.paidBy()));
                userExpenseMapping.setShareAmount(e.getAmount() / expenseRequest.userIds().size());
                userExpenseMappingRepository.save(userExpenseMapping);
            } else {
                throw new IllegalArgumentException("User does not exist");
            }
        });
        return e;
    }

    public List<UserExpenseMapping> findAllByUserId(String userId) {
        return userExpenseMappingRepository.findAllByUserId(userId);
    }

    public Expense findById(String id) {
        return expenseRepository.findById(id).get();
    }
}
