package com.skills;

import com.skills.model.Expense;
import com.skills.model.Group;
import com.skills.model.User;
import com.skills.model.UserExpenseMapping;
import com.skills.repository.GroupRepository;
import com.skills.repository.UserRepository;
import com.skills.request.CreateExpenseRequest;
import com.skills.request.CreateGroupRequest;
import com.skills.service.ExpenseService;
import com.skills.service.GroupService;
import com.skills.service.UserService;
import com.skills.util.ExpenseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class SplitMoneyApplication implements CommandLineRunner
{
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @Autowired
    ExpenseService expenseService;

    public static void main(String[] args) {
        SpringApplication.run(SplitMoneyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Rahul", "Jain", "rahul@gmail.com", "3243232456", "123456789");
        User user2 = new User("Sachin", "Gupta", "sachin@gmail.com", "987654321", "123456789");
        User user3 = new User("Anubhav", "Nema", "anubhav@gmail.com", "1234567890", "123456789");



        User savedUser1 = userService.saveUser(user1);
        User savedUser2 = userService.saveUser(user2);
        User savedUser3 = userService.saveUser(user3);
        CreateGroupRequest group = new CreateGroupRequest("Flatmates", "Flatmates group", savedUser1.getId());
        Group savedGroup1 = groupService.save(group);
        groupService.addUserToGroup(savedGroup1.getId(), savedUser2.getId());
        groupService.addUserToGroup(savedGroup1.getId(), savedUser3.getId());
        CreateExpenseRequest createExpenseRequest = new CreateExpenseRequest(
                ExpenseType.GROUP,
                "Room Rent",
                15000.00,
                savedUser1.getId(),
                List.of(savedUser2.getId(), savedUser3.getId(), savedUser1.getId()),
                savedGroup1.getId()
        );
        Expense e = expenseService.addExpense(createExpenseRequest);
        List<UserExpenseMapping> u1 = expenseService.findAllByUserId(savedUser1.getId());
        List<UserExpenseMapping> u2 = expenseService.findAllByUserId(savedUser2.getId());
        List<UserExpenseMapping> u3 = expenseService.findAllByUserId(savedUser3.getId());
        System.out.println("User 1: " + u1);
        System.out.println("User 2: " + u2);
        System.out.println("User 3: " + u3);
    }
}
