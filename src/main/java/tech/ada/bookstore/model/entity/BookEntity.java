package tech.ada.bookstore.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="title", nullable = false)
    private String title;
    @Column(name = "resume", nullable = false, length=500)
    private String resume;
    @Column(name = "sumary")
    private String summary;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "number_page", nullable = false)
    private Integer numberPage;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "date", nullable = false)
    private LocalDate date;
}
