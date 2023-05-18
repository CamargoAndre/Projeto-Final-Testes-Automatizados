package tech.ada.bookstore.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import tech.ada.bookstore.model.entity.BookEntity;
import tech.ada.bookstore.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BookRepositoryTest implements BookRepository {
    @Override
    public void flush() {

    }

    @Override
    public <S extends BookEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends BookEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<BookEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public BookEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public BookEntity getById(Long aLong) {
        return null;
    }

    @Override
    public BookEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends BookEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends BookEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends BookEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends BookEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends BookEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends BookEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends BookEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends BookEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends BookEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<BookEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<BookEntity> findAll() {
        return null;
    }

    @Override
    public List<BookEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(BookEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends BookEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<BookEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return null;
    }
}
