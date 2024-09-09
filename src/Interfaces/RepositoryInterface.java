package Interfaces;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface<T> {
    Optional<T> create(T entity);
    Optional<T> update(T entity);
    Optional<T> findById(int id);
    List<T> getAll();
    boolean delete(int id);

}
