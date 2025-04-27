package com.skills.repository;

import com.skills.model.UserExpenseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExpenseMappingRepository extends JpaRepository<UserExpenseMapping, String> {
    Optional<UserExpenseMapping> findById(String id);
    UserExpenseMapping save(UserExpenseMapping userExpenseMapping);
    void deleteById(String id);
    boolean existsById(String id);
    List<UserExpenseMapping> findAllByUserId(String userId);
}
