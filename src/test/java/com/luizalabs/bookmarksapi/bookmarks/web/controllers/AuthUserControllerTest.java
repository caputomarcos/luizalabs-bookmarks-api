package com.luizalabs.bookmarksapi.bookmarks.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.luizalabs.bookmarksapi.common.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

class AuthUserControllerTest extends AbstractIntegrationTest {

    @Test
    @WithMockUser("caputo@gmail.com")
    void should_get_login_user_details() throws Exception {
        this.mockMvc.perform(get("/api/user")).andExpect(status().isOk());
    }

    @Test
    void should_fail_to_get_login_user_details_if_unauthorized() throws Exception {
        this.mockMvc.perform(get("/api/user")).andExpect(status().isForbidden());
    }
}
