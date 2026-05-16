package com.micro.user.service.UserService.impl;

import com.micro.user.service.UserService.entities.User;
import com.micro.user.service.UserService.exceptions.ResourceNotFoundException;
import com.micro.user.service.UserService.repositories.UserRepository;
import com.micro.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        //Generate uniq ID
        String randomUserId = UUID.randomUUID().toString();
        user.setUserID(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given ID is not found on server !! "+userId)
                );
    }

    @Override
    public String deleteUser(String userId) {
        //validate the existence
        User existUser = userRepository.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with given ID is not found on server so could not be deleted")
                );
        userRepository.delete(existUser);
        return "Successfully deleted user with ID: " + userId;
    }

    @Override
    public User updateUser(String userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        // Full update (override all fields)
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAbout(updatedUser.getAbout());
        return userRepository.save(existingUser);
    }

    // Optional: PATCH-like behavior (partial update)
    public User patchUser(String userId, User partialUser) {
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

        return userRepository.save(existingUser);
    }
}
