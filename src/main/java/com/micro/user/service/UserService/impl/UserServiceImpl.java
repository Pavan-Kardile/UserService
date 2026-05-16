package com.micro.user.service.UserService.impl;

import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.exceptions.ResourceNotFoundException;
import com.micro.user.service.UserService.repositories.UserRepository;
import com.micro.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        logger.info("Saving user with email: {}", user.getEmail());
        String randomUserId = UUID.randomUUID().toString();
        user.setUserID(randomUserId);
        User savedUser = userRepository.save(user);
        logger.info("User saved successfully with ID: {}", savedUser.getUserID());
        return savedUser;
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        logger.info("Fetching user with ID: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given ID is not found on server !! "+userId)
                );
    }

    @Override
    public String deleteUser(String userId) {
        logger.info("Deleting user with ID: {}", userId);
        User existUser = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given ID is not found on server so could not be deleted")
                );
        userRepository.delete(existUser);
        logger.info("User deleted successfully with ID: {}", userId);
        return "Successfully deleted user with ID: " + userId;
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        logger.info("Updating user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        // Full update (override all fields)
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());
        User updated = userRepository.save(existingUser);
        logger.info("User updated successfully with ID: {}", userId);
        return updated;
    }

    @Override
    public User patchUser(String userId, User partialUser) {
        logger.info("Patching user with ID: {}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        if (partialUser.getName() != null) {
            existingUser.setName(partialUser.getName());
        }
        if (partialUser.getEmail() != null) {
            existingUser.setEmail(partialUser.getEmail());
        }
        if (partialUser.getAbout() != null) {
            existingUser.setAbout(partialUser.getAbout());
        }

        User patched = userRepository.save(existingUser);
        logger.info("User patched successfully with ID: {}", userId);
        return patched;
    }
}
