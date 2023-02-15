package com.luizalabs.bookmarksapi.bookmarks.repositories;

import com.luizalabs.bookmarksapi.bookmarks.entities.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String tag);
}
