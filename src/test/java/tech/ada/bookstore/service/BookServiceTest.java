package tech.ada.bookstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.ada.bookstore.exceptions.BusinessExcepion;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;
import tech.ada.bookstore.model.mapper.BookMapper;
import tech.ada.bookstore.repository.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    BookService service;
    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    @Test
    public void saveBook(){
        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setNumberPage(100);
        dto.setIsbn("1234567890123");
        dto.setDate(LocalDate.now().plusDays(1));
        dto = service.save(dto);
        Optional<BookEntity> entity = repository.findById(dto.getId());
        BookDTO dtoRetorno = mapper.toDTO(entity.get());
        Assertions.assertEquals(dto, dtoRetorno);
    }

    @Test
    public void titleIsNull(){
        BookDTO dto = new BookDTO();
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setNumberPage(100);
        dto.setIsbn("1234567890123");
        dto.setDate(LocalDate.now().plusDays(1));

        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
    }

    @Test
    public void resumeIsNullOrMore500Char(){
        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setNumberPage(100);
        dto.setIsbn("1234567890123");
        dto.setDate(LocalDate.now().plusDays(1));

        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));

        dto.setResume("Testando com mais de 500 caracteres xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxX");

        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
    }

    @Test
    public void priceIsNullOrLess20(){

        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setNumberPage(100);
        dto.setIsbn("1234567890123");
        dto.setDate(LocalDate.now().plusDays(1));
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
        dto.setPrice(new BigDecimal("19.99"));
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));

    }

    @Test
    public void numberPageIsNullOrLess100(){

        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setIsbn("1234567890123");
        dto.setDate(LocalDate.now().plusDays(1));
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
        dto.setNumberPage(99);
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));

    }

    @Test
    public void isbnIsNull(){

        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setNumberPage(100);
        dto.setDate(LocalDate.now().plusDays(1));
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));

    }


    @Test
    public void dateIsNotFuture(){

        BookDTO dto = new BookDTO();
        dto.setTitle("XPTO");
        dto.setResume("Teste salvando Livro");
        dto.setSummary("nao e obrigatorio");
        dto.setPrice(new BigDecimal("20.00"));
        dto.setNumberPage(100);
        dto.setIsbn("1234567890123");

        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
        dto.setDate(LocalDate.now().minusDays(1));
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));
        dto.setDate(LocalDate.now());
        Assertions.assertThrows(BusinessExcepion.class, () -> service.save(dto));

    }







}
