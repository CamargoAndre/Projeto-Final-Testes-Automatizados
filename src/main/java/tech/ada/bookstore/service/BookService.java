package tech.ada.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.ada.bookstore.exceptions.BusinessExcepion;
import tech.ada.bookstore.exceptions.NotFoundException;
import tech.ada.bookstore.model.dto.BookDTO;
import tech.ada.bookstore.model.mapper.BookMapper;
import tech.ada.bookstore.repository.BookRepository;
import tech.ada.bookstore.util.MessageUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;


    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public BookDTO save(BookDTO dto) {
        verificaDto(dto);
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Transactional(readOnly = true)
    public List<BookDTO> findAll() {
        return mapper.toDTO(repository.findAll());
    }
    @Transactional(readOnly = true)
    public BookDTO findById(long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }


    private void verificaDto(BookDTO dto){

        if(dto.getTitle() == null){
            throw new BusinessExcepion(MessageUtils.TITLE_IS_NULL);
        }
        if((dto.getResume() == null) || (dto.getResume().length() > 500)){
            throw new BusinessExcepion(MessageUtils.RESUME_IS_NULL_OR_MORE_500_CHAR);
        }
        if(dto.getPrice() == null || dto.getPrice().compareTo(new BigDecimal("20.00")) < 0){
            throw new BusinessExcepion(MessageUtils.PRICE_IS_NULL_OR_LESS_THAN_20);
        }
        if(dto.getNumberPage() == null || dto.getNumberPage() < 100){
            throw new BusinessExcepion(MessageUtils.NUMBER_PAGE_IS_NULL_OR_LESS_THAN_100);
        }
        if(dto.getIsbn() == null){
            throw new BusinessExcepion(MessageUtils.ISBN_IS_NULL);
        }
        if(dto.getDate() == null || dto.getDate().isBefore(LocalDate.now()) || dto.getDate().equals(LocalDate.now()) ){
            throw new BusinessExcepion(MessageUtils.DATE_NOT_FUTURE);
        }

    }


}
