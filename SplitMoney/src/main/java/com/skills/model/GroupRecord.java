package com.skills.model;

import java.util.List;

public record GroupRecord(
        String id,
        String name,
        String description,
        String createdBy,
        String createdAt,
        List<UserInfo> users,
        String groupCode,
        boolean isMember
) {
    public record UserInfo(String id, String firstName, String lastName, String email) {}

    // Factory method to create a GroupRecord from a Group entity
    public static GroupRecord fromGroup(Group group) {
        List<UserInfo> users = group.getUsers().stream()
                .map(user -> new UserInfo(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()))
                .toList();
        return new GroupRecord(
                group.getId(),
                group.getName(),
                group.getDescription(),
                group.getCreatedBy().getId(),
                group.getCreatedAt(),
                users,
                group.getCode(),
                false
        );
    }

    public GroupRecord withIsMember(boolean isMember) {
        return new GroupRecord(
            this.id,
            this.name,
            this.description,
            this.createdBy,
            this.createdAt,
            this.users,
            this.groupCode,
            isMember
        );
    }
}
