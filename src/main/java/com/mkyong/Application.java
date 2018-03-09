package com.mkyong;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import com.examples.web.book.Book;
import com.examples.web.book.service.BookService;

import java.util.Map;

/**
 * 
 * @author Sandhya
 * Command Line Runner Application
 *
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private BookService bookService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

        bookService.save(new Book("1001", "Elasticsearch Basics", "Sandhya", "23-FEB-2018"));
        bookService.save(new Book("1002", "Apache Lucene Basics", "Sandhya", "13-FEB-2018"));
        bookService.save(new Book("1003", "Apache Solr Basics", "Sandhya", "21-FEB-2018"));

        //fuzzey search
        Page<Book> books = bookService.findByAuthor("Sandhya", new PageRequest(0, 10));

        //List<Book> books = bookService.findByTitle("Elasticsearch Basics");

        books.forEach(x -> System.out.println(x));


    }

    //useful for debug, print elastic search details
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch--");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("--ElasticSearch--");
    }

}