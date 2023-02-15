package com.luizalabs.bookmarksapi.bookmarks.web.controllers;

import com.luizalabs.bookmarksapi.bookmarks.entities.Tag;
import com.luizalabs.bookmarksapi.bookmarks.services.BookmarkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Tag> allTags() {
        return bookmarkService.findAllTags();
    }
}
