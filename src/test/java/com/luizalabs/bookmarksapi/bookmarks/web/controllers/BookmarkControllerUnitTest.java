package com.luizalabs.bookmarksapi.bookmarks.web.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.luizalabs.bookmarksapi.bookmarks.models.BookmarksDTO;
import com.luizalabs.bookmarksapi.bookmarks.services.BookmarkService;
import com.luizalabs.bookmarksapi.common.AbstractWebMvcTest;
import com.luizalabs.bookmarksapi.users.services.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

@WebMvcTest(controllers = BookmarkController.class)
class BookmarkControllerUnitTest extends AbstractWebMvcTest {
    @MockBean protected BookmarkService bookmarkService;

    @MockBean protected SecurityService securityService;

    @Test
    void shouldFetchBookmarksFirstPage() throws Exception {
        BookmarksDTO bookmarksDTO = new BookmarksDTO();
        given(bookmarkService.getAllBookmarks(any(Pageable.class))).willReturn(bookmarksDTO);

        this.mockMvc.perform(get("/api/bookmarks")).andExpect(status().isOk());
    }
}
