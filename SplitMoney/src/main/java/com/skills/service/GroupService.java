package com.skills.service;

import com.skills.model.Group;
import com.skills.model.GroupRecord;
import com.skills.model.User;
import com.skills.repository.GroupRepository;
import com.skills.repository.UserRepository;
import com.skills.request.CreateGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Group save(@NonNull CreateGroupRequest groupRequest) {
        Optional<User> user = userRepository.findById(groupRequest.creatorId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }
        Group group = Group.fromGroupRequest(groupRequest);
        group.setCreatedBy(user.get());
        
        // Save the group first
        Group createdGroup = groupRepository.save(group);
        
        // Use the helper method to maintain both sides of the relationship
        createdGroup.addUser(user.get());
        
        // Save once after establishing the relationship
        return groupRepository.save(createdGroup);
    }

    public GroupRecord findById(@NonNull String id) {
        Group group = groupRepository.findGroupById(id);
        return GroupRecord.fromGroup(group);
    }

    public void deleteById(@NonNull String id) {
        groupRepository.deleteById(id);
    }

    @Transactional
    public void addUserToGroup(@NonNull String groupId, @NonNull String userId) {
        Optional<Group> groupOpt = groupRepository.findById(groupId);
        if (groupOpt.isEmpty()) {
            throw new IllegalArgumentException("Group does not exist");
        }
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }
        
        Group group = groupOpt.get();
        User user = userOpt.get();
        
        group.addUser(user);
        
        groupRepository.save(group);
    }
}
