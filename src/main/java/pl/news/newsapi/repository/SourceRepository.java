package pl.news.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.news.newsapi.entity.Source;

@Repository
public interface SourceRepository extends JpaRepository<Source, String> {
    Source findByName(String name);
}
