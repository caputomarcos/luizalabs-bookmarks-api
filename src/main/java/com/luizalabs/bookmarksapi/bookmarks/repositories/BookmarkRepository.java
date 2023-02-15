package com.luizalabs.bookmarksapi.bookmarks.repositories;

import com.luizalabs.bookmarksapi.bookmarks.entities.Bookmark;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByTitle(String title);

    @Query("select l.id from Bookmark l")
    Page<Long> fetchBookmarkIds(Pageable pageable);

    @Query("select l.id from Bookmark l where lower(l.title) like lower(concat('%', :query,'%'))")
    Page<Long> fetchBookmarkIdsByTitleContainingIgnoreCase(
            @Param("query") String query, Pageable pageable);

    @Query("select l.id from Bookmark l join l.tags t where t.name=?1")
    Page<Long> fetchBookmarkIdsByTag(String tagName, Pageable pageable);

    @Query(
            "select DISTINCT l from Bookmark l JOIN FETCH l.tags join fetch l.createdBy where l.id in ?1")
    List<Bookmark> findBookmarksWithTags(List<Long> bookmarkIds, Sort sort);
}
