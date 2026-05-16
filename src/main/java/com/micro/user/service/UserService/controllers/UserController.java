package com.micro.user.service.UserService.controllers;

import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.exceptions.ApiResponse;
import com.micro.user.service.UserService.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create and save a new user to the database")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) {
        logger.info("Creating user with email: {}", user.getEmail());
        User user1 = userService.saveUser(user);
        ApiResponse response = new ApiResponse("User created successfully", true, user1);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Fetch a user by their unique ID")
    public ResponseEntity<ApiResponse> getUserByID(@PathVariable String userId) {
        logger.info("Fetching user with ID: {}", userId);
        User user = userService.getUser(userId);
        ApiResponse response = new ApiResponse("User retrieved successfully", true, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    @Operation(summary = "Get all users", description = "Retrieve all users from the database")
    public ResponseEntity<ApiResponse> getAllUsers() {
        logger.info("Fetching all users");
        List<User> allUser = userService.getAllUser();
        ApiResponse response = new ApiResponse("Users retrieved successfully", true, allUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete a user", description = "Delete a user by their unique ID")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        logger.info("Deleting user with ID: {}", userId);
        String result = userService.deleteUser(userId);
        ApiResponse response = new ApiResponse(result, true);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update a user", description = "Update all fields of a user")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String userId, @RequestBody User user) {
        logger.info("Updating user with ID: {}", userId);
        User user1 = userService.updateUser(userId, user);
        ApiResponse response = new ApiResponse("User updated successfully", true, user1);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}")
    @Operation(summary = "Partially update a user", description = "Update specific fields of a user")
    public ResponseEntity<ApiResponse> patchUser(@PathVariable String userId, @RequestBody User user) {
        logger.info("Patching user with ID: {}", userId);
        User user1 = userService.patchUser(userId, user);
        ApiResponse response = new ApiResponse("User patched successfully", true, user1);
        return ResponseEntity.ok(response);
    }
}
