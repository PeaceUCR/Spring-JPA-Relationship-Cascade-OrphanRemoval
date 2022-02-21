package guru.springframework.demo.repository;

import guru.springframework.demo.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Page<Book> findAll(Pageable pageable);
}
