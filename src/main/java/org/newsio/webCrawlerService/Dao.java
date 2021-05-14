package org.newsio.webCrawlerService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Dao<T>{
    Optional<T> get(int id);
    Collection<T> getAll();
    Optional<List<String>> getLatestDatabase();

    void save(T t);
    void update(T t);
    void delete(T t);
}
