package it.exercise.TestUnit.controllers;

import it.exercise.TestUnit.entities.UserEntity;
import it.exercise.TestUnit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * @author Drumstyle92
 * Spring Boot controller class that handles HTTP requests for the "users" resource
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * UserRepository dependency with Autowired annotation to access user data
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * @return returns a list of all users in the database
     * Get method that takes the list of users through the HTTP request
     */
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers() {

        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.ok(users);

    }

    /**
     * @param id Specified userid parameter
     * @return returns the user with the specified id
     * Get method that takes a single selected user through the HTTP request
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            UserEntity user = optionalUser.get();

            return ResponseEntity.ok(user);

        } else {

            return ResponseEntity.notFound().build();

        }

    }

    /**
     * @param user Parameter where the user is created (request body resides)
     * @return The user returns with his data
     * Post method that creates a new user in the database
     */
    @PostMapping("")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {

        UserEntity savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser);

    }

    /**
     * @param id          Parameter where to enter the id for selecting the user to modify
     * @param updatedUser Parameter where to make the changes to the user
     * @return Return the updated user
     * Put method that updates the user with the specified id with the new data provided
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            UserEntity user = optionalUser.get();

            user.setFirstName(updatedUser.getFirstName());

            user.setLastName(updatedUser.getLastName());

            user.setEmail(updatedUser.getEmail());

            UserEntity savedUser = userRepository.save(user);

            return ResponseEntity.ok(savedUser);

        } else {

            return ResponseEntity.notFound().build();

        }

    }

    /**
     * @param id parameter where to insert the id of the user to delete
     * @return Confirmation of cancellation returns
     * Delete method which deletes the user with the specified id from the database
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        Optional<UserEntity> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            userRepository.delete(optionalUser.get());

            return ResponseEntity.ok().build();

        } else {

            return ResponseEntity.notFound().build();

        }

    }

}
