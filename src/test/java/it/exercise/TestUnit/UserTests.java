package it.exercise.TestUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.exercise.TestUnit.controllers.HomeController;
import it.exercise.TestUnit.entities.UserEntity;
import it.exercise.TestUnit.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Drumstyle92
 * Unit test class written in Java using the Spring Boot framework.
 * It is used to test various features related to user management.
 * The webEnvironment attribute set to SpringBootTest.WebEnvironment.RANDOM_PORT
 * is used to launch the Spring Boot application in a random port and test its
 * integration with the web server, finally a self-configuration for the
 * MockMvc class to be able to use it
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserTests {

    /**
     * Autowired annotated dependency that allows you to test REST methods of the class
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Autowired annotated dependency of the UserRepository
     * interface to access methods to interact with the database
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Autowired annotated dependency of the ObjectMapper
     * class for serializing and deserializing JSON objects
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Auotwired annotated dependency to be able to test its methods
     */
    @Autowired
    private HomeController homeController;

    /**
     * Test method that verifies that the controller loads correctly
     */
    @Test
    void contextLoadsHomeController() {
        assertThat(homeController).isNotNull();
    }

    /**
     * Test method to verify that the interface components on the class have been configured correctly
     */
    @Test
    void contextLoads() {
        assertThat(userRepository).isNotNull();
    }

    /**
     * @throws Exception the exception
     * Test method that makes a GET call to the "/users"
     * resource and verifies that the returned HTTP response is correct
     */
    @Test
    void getAll() throws Exception {

        this.mockMvc.perform(get("/users")).andDo(print()).andExpect(status().isOk());

    }

    /**
     * @throws Exception the exception
     * Test method that makes a GET call to the
     * "/users/{id}" resource to get a specific
     * user and verifies that the returned HTTP response is correct
     */
    @Test
    void getSingle() throws Exception {

        UserEntity user = userRepository.save(
                new UserEntity(1L,"Dino","Petrucci", "dino@example.com"));

        this.mockMvc.perform(get("/users/" + user.getId())).andDo(print()).andExpect(status().isOk());

    }

    /**
     * @throws Exception the exception
     * Test method that verifies the creation of a new user in the system through
     * a post call to the "/users" resource and that the returned HTTP response is correct
     */
    @Test
    void create() throws Exception {

        UserEntity user = new UserEntity(1L,"Dino","Petrucci", "dino@example.com");

        this.mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }

    /**
     * @throws Exception the exception
     * Test method that verifies that it is possible to update the data
     * of an existing user in the system through a PUT call to the resource
     * "/users/{id}" and that the returned HTTP response is correct
     */
    @Test
    void update() throws Exception {

        UserEntity user = userRepository.save(
                new UserEntity(1L,"Dino","Petrucci", "dino@example.com"));

        user.setFirstName("Donatello");

        this.mockMvc.perform(put("/users/" + user.getId())
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }

    /**
     * @throws Exception the exception
     * Test method that performs a DELETE call to the "/users/{id}"
     * resource to delete a specific user and verifies that
     * the returned HTTP response is correct
     */
    @Test
    void userDelete() throws Exception {

        UserEntity userEntity = userRepository.save(

                new UserEntity(1L,"Dino","Petrucci", "dino@example.com"));

        this.mockMvc.perform(delete("/users/" + userEntity.getId()))
                .andDo(print()).andExpect(status().isOk());

    }

}

