package tech.ada.bookstore.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.service.BookService;

import java.awt.print.Book;
import java.util.List;


@RestController
@RequestMapping(value = "/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> save(@Valid @RequestBody BookDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(service.findById(id));
    }

}
