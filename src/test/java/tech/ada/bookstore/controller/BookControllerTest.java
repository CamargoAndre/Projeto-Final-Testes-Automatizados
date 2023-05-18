package tech.ada.bookstore.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tech.ada.bookstore.exceptions.NotFoundException;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.repository.BookRepository;
import tech.ada.bookstore.service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private BookController controller;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookService service;

    @BeforeEach
    public void zerarTable() {
        repository.deleteAll();
    }

    @Test
    public void loadContexto(){

        assertNotNull(controller);
       // Assertions.assertTrue(controller != null);
    }


    @Test
    public void testOk() throws Exception{
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/books"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void registerBookSuccessfully() throws Exception {

        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\"," +
                " \"price\" : \"20.00\", " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void registerBookNoTitle() throws Exception{
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", \"price\" : 20.01, " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
    @Test
    public void registerBookNoResume() throws Exception{
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\"," +
                "\"summary\" : \"não é obrigatório\", \"price\" : 20.01, " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    public void registerBookMore500CharactersResume() throws Exception{
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\"," +
                "\"resume\" : \"Testando com mais de 500 caracteres xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\", " +
                "\"summary\" : \"não é obrigatório\", \"price\" : 20.00, " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void registerBookExactly500CharactersResume() throws Exception{
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\"," +
                "\"resume\" : \"Testando com Exato de 500 caracteres xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
                "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\", " +
                "\"summary\" : \"não é obrigatório\", \"price\" : 20.00, " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void registerBookNoSummary() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"price\" : 20.01, " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void registerBookNoPrice() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }

    @Test
    public void registerBookPriceLess20() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 19.99" +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookNoNumberPage() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookNumberPageLess100() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"numberPage\" : 99," +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookNoIsbn() throws Exception {
        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"numberPage\" : 100," +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookNoDate() throws Exception {

        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"numberPage\" : 100," +
                "\"numberPage\" : \"1234567890123\"," +
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookDateIsPresent() throws Exception {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"numberPage\" : 100," +
                "\"numberPage\" : \"1234567890123\"," +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void registerBookDateIsPast() throws Exception {
        String data = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\", " +
                "\"price\" : 20," +
                "\"numberPage\" : 100," +
                "\"numberPage\" : \"1234567890123\"," +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    // Teste do Controller busca por ID

    @Test
    public void findById() throws Exception{

        String data = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        String json = "{" +
                "\"title\" : \"XPTO\", " +
                "\"resume\" : \"Testando o com quantidade menor que 500\", " +
                "\"summary\" : \"não é obrigatório\"," +
                " \"price\" : \"20.00\", " +
                "\"numberPage\" : 100, " +
                "\"isbn\": \"1234567890123\", " +
                "\"date\" : " + "\""+ data + "\""+
                "}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/books")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<BookDTO> dtos = service.findAll();

        Optional<BookDTO> optional = dtos.stream().findFirst();

        BookDTO dto = optional.get();

        Assertions.assertEquals(ResponseEntity.ok(dto), controller.findById(dto.getId()));

        Assertions.assertThrows(NotFoundException.class, () -> controller.findById(-1) );


    }


}
