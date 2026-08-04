package org.kamal.library_management.specification;

import org.kamal.library_management.entity.Book;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

public class BookSpecification {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, cb) ->
                title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> hasAuthorId(Long authorId) {
        return (root, query, cb) ->
                authorId == null ? null : cb.equal(root.get("author").get("id"), authorId);
    }

    public static Specification<Book> hasCategoryName(String categoryName) {
        return (root, query, cb) -> {
            if (categoryName == null) {
                return null;
            }
            query.distinct(true);
            return cb.equal(cb.lower(root.join("categories").get("name")), categoryName.toLowerCase());
        };
    }

    public static Specification<Book> hasPublishedYear(Integer publishedYear) {
        return (root, query, cb) ->
                publishedYear == null ? null : cb.equal(root.get("publishedYear"), Year.of(publishedYear));
    }
}