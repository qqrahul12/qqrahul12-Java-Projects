package com.skills.model;

import java.util.List;

public record UserRecord(String id, String firstName, String lastName, String email, String phoneNumber, List<GroupInfo> groups) {
    public record GroupInfo(String id, String name) {
    }

    // Factory method to create a UserRecord from a User entity
    public static UserRecord fromUser(User user) {
        List<GroupInfo> groups = user.getGroups().stream()
                .map((group -> new GroupInfo(group.getId(), group.getName()))).toList();
        return new UserRecord(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), groups);
    }
}
