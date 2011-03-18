package com.jrock.hsdemo;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author berinle
 */
@Component
public class IndexBuilder implements ApplicationListener {
    private static Logger log = Logger.getLogger(IndexBuilder.class);
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent){

            ApplicationContext context = ((ContextRefreshedEvent) event).getApplicationContext();
            SessionFactory sf  = (SessionFactory) context.getBean("sessionFactory");
            Session session = sf.openSession();
            FullTextSession fts = Search.getFullTextSession(session);

            fts.beginTransaction();

            List<Movie> list = fts.createCriteria(Movie.class).list();

            log.debug("about to index " + list.size() + " movies");

            for (Movie movie : list) {

                fts.index(movie);
            }

            fts.flushToIndexes();
            fts.clear();


            fts.getTransaction().commit();
            session.close();

            log.info(" Done doing initial indexing");
        }
    }
}
