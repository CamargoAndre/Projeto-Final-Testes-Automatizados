package tech.ada.bookstore.model.mapper;

import org.springframework.stereotype.Component;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.entity.BookEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookMapper {

    public BookEntity toEntity(BookDTO dto){
        BookEntity entity = new BookEntity();

        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setResume(dto.getResume());
        entity.setSummary(dto.getSummary());
        entity.setPrice(dto.getPrice());
        entity.setNumberPage(dto.getNumberPage());
        entity.setIsbn(dto.getIsbn());
        entity.setDate(dto.getDate());

        return entity;
    }

    public BookDTO toDTO(BookEntity entity){
        BookDTO dto = new BookDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setResume(entity.getResume());
        dto.setSummary(entity.getSummary());
        dto.setPrice(entity.getPrice());
        dto.setNumberPage(entity.getNumberPage());
        dto.setIsbn(entity.getIsbn());
        dto.setDate(entity.getDate());

        return dto;
    }

    public List<BookDTO> toDTO(List<BookEntity> entities){
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }



}
