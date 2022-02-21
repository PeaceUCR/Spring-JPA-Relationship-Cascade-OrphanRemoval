package guru.springframework.demo.bootstrap;

import guru.springframework.demo.domain.Book;
import guru.springframework.demo.domain.User;
import guru.springframework.demo.repository.BookRepository;
import guru.springframework.demo.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    initData();

  }

  @Transactional
  public void initData() {
    User user1 = new User();
    user1.setName("User 1");

    Book book1 = new Book("Book 1");
    book1.setUser(user1); // set relation

    Book book2 = new Book("Book 1");
    book2.setUser(user1); // set relation

    user1.setBooks(Arrays.asList(book1, book2));
    userRepository.save(user1);

//    testCascadeRemove();
    testOrphanremoval();
  }

  @Transactional
  public void testCascadeRemove() {
    // if no CascadeType.REMOVE on user.books, then delete blocked by foreign key constraint
    // if has CascadeType.REMOVE on user.books, then delete user & its related books
    userRepository.delete(
        userRepository.findByName("User 1").orElseThrow(EntityNotFoundException::new));
  }

  @Transactional
  public void testOrphanremoval() {
    // if no orphanRemoval=true on user.books, this way can't delete the relationship, book1, book2 always here in db
    // if orphanRemoval=true on user.books, it will delete the first book in db that will not link on user
    User user = userRepository.findByName("User 1").orElseThrow(EntityNotFoundException::new);
    List<Book> books = user.getBooks();
    books.remove(0);
//    Book removed = books.remove(0);
//    removed.setUser(null);
    user.setBooks(books);
    userRepository.save(user);
  }
}
