package pl.news.newsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.news.newsapi.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
