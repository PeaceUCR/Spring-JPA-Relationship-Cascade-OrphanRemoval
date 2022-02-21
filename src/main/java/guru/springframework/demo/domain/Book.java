package guru.springframework.demo.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseEntity {
  private String title;
  private String isbn;
  private String publisher;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Book(String title) {
    this.title = title;
  }

}
