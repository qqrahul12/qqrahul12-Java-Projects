package com.skills.controller;

import com.skills.model.Expense;
import com.skills.request.CreateExpenseRequest;
import com.skills.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<Expense> getByExpenseId(@RequestParam String id) {
        Expense expense = expenseService.findById(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity addExpense(@RequestBody @Valid CreateExpenseRequest expenseRequest) {
        try {
            expenseService.addExpense(expenseRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
