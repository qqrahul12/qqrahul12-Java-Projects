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

    }
}
