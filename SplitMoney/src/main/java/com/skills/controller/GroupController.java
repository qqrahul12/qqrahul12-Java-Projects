package com.skills.controller;

import com.skills.model.GroupRecord;
import com.skills.request.CreateGroupRequest;
import com.skills.request.JoinGroupRequest;
import com.skills.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import com.skills.model.UserRecord;
import java.util.ArrayList;
import java.util.List;

// Allow CORS from your frontend
@Controller
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

    @Autowired
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupRecord> findById(@NonNull @PathVariable String id) {
        GroupRecord group = groupService.findById(id);
        if (group == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<GroupRecord>> findAll(HttpSession session) {
        Iterable<GroupRecord> groups = groupService.findAll();
        UserRecord user = (UserRecord) session.getAttribute("user");
        List<GroupRecord> result = new ArrayList<>();
        for (GroupRecord group : groups) {
            boolean isMember = false;
            if (user != null && group.users() != null) {
                isMember = group.users().stream().anyMatch(member -> member.id().equals(user.id()));
            }
            result.add(group.withIsMember(isMember));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@NonNull @RequestBody CreateGroupRequest group, HttpSession session) {
        try {
            UserRecord user = (UserRecord) session.getAttribute("user");
            if (user == null) {
                return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
            }

            String creatorId = user.id();
            groupService.save(new CreateGroupRequest(group.name(), group.description(), creatorId));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@NonNull @PathVariable String id) {
        try {
            groupService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity update(@NonNull @RequestBody CreateGroupRequest group) {
        try {
            groupService.save(group);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/joinGroup")
    public ResponseEntity joinGroup(@NonNull @RequestBody JoinGroupRequest joinGroupRequest, HttpSession session) {
        try {
            UserRecord user = (UserRecord) session.getAttribute("user");
            if (user == null) {
                return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
            }

            String userId = user.id();
            groupService.joinGroupWithCode(joinGroupRequest.groupCode(), userId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
