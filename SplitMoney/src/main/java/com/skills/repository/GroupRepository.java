package com.skills.repository;

import com.skills.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    Group findGroupById(String id);

    // These are all redundant - JpaRepository already provides these methods
    // Group save(Group group);
    // void deleteById(String id);

    // This is fine - it's a custom query method
    boolean existsByIdAndUsersId(String id, String userId);
}
