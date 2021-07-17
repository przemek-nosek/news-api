package pl.news.newsapi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.news.newsapi.entity.Article;
import pl.news.newsapi.entity.News;
import pl.news.newsapi.entity.Source;
import pl.news.newsapi.repository.ArticleRepository;
import pl.news.newsapi.repository.SourceRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@SpringBootApplication
public class NewsApiApplication implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;

    @Autowired
    public NewsApiApplication(ArticleRepository repository, SourceRepository sourceRepository) {
        this.articleRepository = repository;
        this.sourceRepository = sourceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(NewsApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://newsapi.org/v2/everything?q=apple&from=2021-07-16&to=2021-07-16&sortBy=popularity&apiKey=c403f12f1ae7425c9ce5b29b36138c31"))
                .build();

        String jsonResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        News news = new Gson().fromJson(jsonResponse, News.class);

        List<Article> articles = news.getArticles();


        for (Article article : articles) {
            Source source = article.getSource();

            Source found = sourceRepository.findByName(source.getName());

            if (found != null) {
                article.setSource(found);
            } else {
                sourceRepository.save(source);
            }
            articleRepository.save(article);
        }
    }
}
