package org.newsio.webCrawlerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao<T, I>{
    Optional<T> get(int id);
    Collection<T> getAll();
    Optional<List<String>> getLatestDatabase();

    Optional<I> save(T t);
    Optional<List<T>> getLatest();
    void update(T t);
    void delete(T t);
}
