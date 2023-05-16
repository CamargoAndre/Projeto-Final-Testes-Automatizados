package tech.ada.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.bookstore.model.entity.BookEntity;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
