package com.luizalabs.bookmarksapi.bookmarks.web.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.luizalabs.bookmarksapi.common.AbstractIntegrationTest;
import com.luizalabs.bookmarksapi.users.entities.User;
import com.luizalabs.bookmarksapi.users.models.ChangePasswordRequest;
import com.luizalabs.bookmarksapi.users.models.CreateUserRequest;
import com.luizalabs.bookmarksapi.users.models.UserDTO;
import com.luizalabs.bookmarksapi.users.services.UserService;
import com.luizalabs.bookmarksapi.utils.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

class UserControllerTest extends AbstractIntegrationTest {

    @Autowired private UserService userService;

    @Test
    void should_find_user_by_id() throws Exception {
        Long userId = 1L;
        this.mockMvc.perform(get("/api/users/{id}", userId)).andExpect(status().isOk());
    }

    @Test
    void should_create_new_user_with_valid_data() throws Exception {
        CreateUserRequest createUserRequestDTO =
                CreateUserRequest.builder()
                        .email("myemail@gmail.com")
                        .password("secret")
                        .name("myname")
                        .build();

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createUserRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_fail_to_create_new_user_with_existing_email() throws Exception {
        CreateUserRequest createUserRequestDTO =
                CreateUserRequest.builder()
                        .email("admin@gmail.com")
                        .password("secret")
                        .name("myname")
                        .build();

        this.mockMvc
                .perform(
                        post("/api/users")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createUserRequestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("caputo1@gmail.com")
    void should_update_own_user_details_when_user_is_authorized() throws Exception {
        User savedUser = createUser("caputo1@gmail.com");

        savedUser.setName("New name");

        this.mockMvc
                .perform(
                        put("/api/users/" + savedUser.getId())
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(savedUser)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("caputo@gmail.com")
    void should_not_update_other_user_details_when_user_is_authorized() throws Exception {
        User savedUser = createUser("caputo2@gmail.com");
        savedUser.setName("New name");

        this.mockMvc
                .perform(
                        put("/api/users/" + savedUser.getId())
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(savedUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("admin@gmail.com")
    void should_fail_to_delete_non_existing_user() throws Exception {
        this.mockMvc.perform(delete("/api/users/{id}", 9999)).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser("admin@gmail.com")
    void should_be_able_to_delete_other_user_if_user_is_admin() throws Exception {
        User savedUser = createUser("someuser@gmail.com");

        this.mockMvc
                .perform(delete("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser("caputo@gmail.com")
    void should_not_be_able_to_delete_other_user_if_not_admin() throws Exception {
        User savedUser = createUser("luizalabs123@gmail.com");
        this.mockMvc
                .perform(delete("/api/users/{id}", savedUser.getId()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("caputo@gmail.com")
    void should_update_password_when_user_is_authorized() throws Exception {
        ChangePasswordRequest changePasswordDTO =
                ChangePasswordRequest.builder().oldPassword("caputo").newPassword("newpwd").build();

        this.mockMvc
                .perform(
                        post("/api/user/change-password")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void should_fail_to_update_password_when_user_is_not_authorized() throws Exception {
        ChangePasswordRequest changePasswordDTO =
                ChangePasswordRequest.builder().oldPassword("admin").newPassword("newpwd").build();

        this.mockMvc
                .perform(
                        post("/api/user/change-password")
                                .contentType(APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(changePasswordDTO)))
                .andExpect(status().isForbidden());
    }

    private User createUser(String email) {
        User user = TestDataFactory.createUser(email);
        String plainPwd = user.getPassword();
        UserDTO userDTO = userService.createUser(UserDTO.fromEntity(user));
        user.setId(userDTO.getId());
        user.setPassword(plainPwd);
        return user;
    }
}
