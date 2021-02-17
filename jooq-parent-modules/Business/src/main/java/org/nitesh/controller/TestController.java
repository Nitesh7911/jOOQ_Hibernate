package org.nitesh.controller;


import org.nitesh.domain.Book;
import org.nitesh.jOOQ.tables.pojos.Author;
import org.nitesh.jOOQ.tables.records.AuthorRecord;
import org.nitesh.repository.AuthorRepository;
import org.nitesh.repository.BookRepository;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

import static org.nitesh.jOOQ.tables.Author.AUTHOR;
import static org.nitesh.jOOQ.tables.AuthorBook.AUTHOR_BOOK;
import static org.nitesh.jOOQ.tables.Book.BOOK;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    DSLContext ctx;

    @Autowired
    EntityManager em;

    @GetMapping
    String hello() {
        return "Hello World!";
    }

    @GetMapping("hibernate")
    @Transactional
    String hibernate() {
        //  EntityManager em = emf.createEntityManager();
        //	em.getTransaction().begin();

        org.nitesh.domain.Author a = new org.nitesh.domain.Author();
        a.setFirstName("Nitesh");
        a.setLastName("Akhouri");
        authorRepository.save(a);

        Book b = new Book();
        b.setTitle("Hibernate and JOOQ");
        b.getAuthors().add(a);
        a.getBooks().add(b);
        bookRepository.save(b);

        //	em.getTransaction().commit();
        //	em.close();
        return "Inserted records!";
    }

    @RequestMapping("jooq_query")
    List<Object> jooq() {
        SelectConditionStep<Record3<String, String, String>> jooqQuery =
                ctx.select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.TITLE)
                        .from(AUTHOR)
                        .leftJoin(AUTHOR_BOOK).on(AUTHOR.ID.eq(AUTHOR_BOOK.AUTHOR_ID))
                        .leftJoin(BOOK).on(AUTHOR_BOOK.BOOK_ID.eq(BOOK.ID))
                        .where(AUTHOR.FIRST_NAME.like("Thor%"));
        Query q = em.createNativeQuery(jooqQuery.getSQL());
        setBindParameterValues(q, jooqQuery);
        List<Object> bookAuthors = q.getResultList();
        System.out.println(bookAuthors);
        return bookAuthors;
    }

    @RequestMapping("jooq_record")
    Author jooqrecord() {
        AuthorRecord author = ctx.fetchOne(AUTHOR, AUTHOR.ID.eq(1L));
        return author.into(Author.class);
    }

    private static void setBindParameterValues(Query hibernateQuery, org.jooq.Query jooqQuery) {
        List<Object> values = jooqQuery.getBindValues();
        for (int i = 0; i < values.size(); i++) {
            hibernateQuery.setParameter(i + 1, values.get(i));
        }
    }
}
