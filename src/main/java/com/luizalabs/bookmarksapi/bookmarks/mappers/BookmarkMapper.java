package com.luizalabs.bookmarksapi.bookmarks.mappers;

import com.luizalabs.bookmarksapi.bookmarks.entities.Bookmark;
import com.luizalabs.bookmarksapi.bookmarks.entities.Tag;
import com.luizalabs.bookmarksapi.bookmarks.models.BookmarkDTO;
import com.luizalabs.bookmarksapi.users.services.SecurityService;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkMapper {
    private final SecurityService securityService;

    public BookmarkDTO toDTO(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setUrl(bookmark.getUrl());
        dto.setTitle(bookmark.getTitle());
        dto.setCreatedUserId(bookmark.getCreatedBy().getId());
        dto.setCreatedUserName(bookmark.getCreatedBy().getName());
        dto.setCreatedAt(bookmark.getCreatedAt());
        dto.setUpdatedAt(bookmark.getUpdatedAt());
        if (bookmark.getTags() != null) {
            dto.setTags(bookmark.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
        }
        return dto;
    }
}
