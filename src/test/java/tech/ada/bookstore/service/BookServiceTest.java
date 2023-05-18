package tech.ada.bookstore.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import tech.ada.bookstore.repository.BookRepositoryTest;
import tech.ada.bookstore.exceptions.BusinessExcepion;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.repository.BookRepository;
import tech.ada.bookstore.model.mapper.BookMapper;


import java.math.BigDecimal;
import java.time.LocalDate;

public class BookServiceTest {

    private BookMapper mapper = new BookMapper();
    private BookRepository repository = new BookRepositoryTest();
    private BookService service = new BookService(repository, mapper);





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
