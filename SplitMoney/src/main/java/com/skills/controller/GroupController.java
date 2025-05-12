package com.skills.controller;

import com.skills.model.Group;
import com.skills.model.GroupRecord;
import com.skills.request.CreateGroupRequest;
import com.skills.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/groups")
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
    public ResponseEntity<Iterable<GroupRecord>> findAll() {
        Iterable<GroupRecord> groups = groupService.findAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@NonNull @RequestBody CreateGroupRequest group) {
        try {
            groupService.save(group);
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

}
