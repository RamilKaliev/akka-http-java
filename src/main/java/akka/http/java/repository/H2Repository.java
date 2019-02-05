package akka.http.java.repository;

import akka.http.java.model.entity.AuthorEntity;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;


public class H2Repository {

    private static final Logger logger = LoggerFactory.getLogger(H2Repository.class);


    final String url;
    JdbcConnectionPool cp;

    public H2Repository(String url) {
        this.url = url;
    }


    public boolean check() {

        boolean initialized = false;

        try {
            cp = JdbcConnectionPool.create("jdbc:h2:~/bookstore", "", "");
            cp.setMaxConnections(10);

            if (cp.getConnection() != null) {
                initialized = true;
            }
        } catch(Exception e) {
            logger.error(e.toString());
        } finally {
            if (!initialized && cp != null) {
                cp.dispose();
            }
        }

        return initialized;
    }


    public void init() {

        try {

            Connection conn = cp.getConnection();

            boolean result1 = conn.prepareStatement("create table bookstore.author (id identity, firstName varchar(50), lastName varchar(50))").execute();
            logger.info(String.format("Creation of the 'Author' table result = %b", result1));

            boolean result2 = conn.prepareStatement("create table bookstore.book (id identity, name varchar(50), descr varchar(150), authorId int)").execute();
            logger.info(String.format("Creation of the 'Book' table result = %b", result2));

        } catch (Exception e) {

            logger.info(e.toString());

        }

    }



    public AuthorEntity saveAuthor(String firstName, String lastName) {

        AuthorEntity authorEntity = null;

        try {
            ResultSet rs = cp.getConnection()
                    .prepareStatement(String.format("insert into bookstore.author(firstName, lastName) values (%s, %s)", firstName, lastName))
                    .executeQuery();

            if (rs.first()) {
                authorEntity = new AuthorEntity(
                        rs.getLong("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName")
                );
            }

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return authorEntity;
    }


    // TODO: GET AUTHORS, POST BOOK, GET BOOKS

}
