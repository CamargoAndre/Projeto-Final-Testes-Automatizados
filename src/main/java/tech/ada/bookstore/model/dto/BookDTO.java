package tech.ada.bookstore.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.ada.bookstore.util.MessageUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    @NotNull(message = MessageUtils.TITLE_IS_NULL)
    private String title;
    @NotNull
    @Size(max = 500)
    private String resume;
    private String summary;
    @NotNull
    @DecimalMin("20.00")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;
    @NotNull
    @Min(100)
    private Integer numberPage;
    @NotNull
    private String isbn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Future(message = "Data obrigatória no futuro")
    private LocalDate date;
}
