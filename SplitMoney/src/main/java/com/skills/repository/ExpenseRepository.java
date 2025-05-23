package com.skills.repository;

import com.skills.model.Expense;
import com.skills.model.Group;
import com.skills.util.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    Optional<Expense> findById(@NonNull String id);
    Optional<Expense> findByType(ExpenseType type);
    List<Expense> findAllByGroup(@NonNull Group group);
}
