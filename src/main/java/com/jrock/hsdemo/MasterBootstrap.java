package com.jrock.hsdemo;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.backend.impl.jms.AbstractJMSHibernateSearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author berinle
 */
@Component("masterBootstrap")
public class MasterBootstrap extends AbstractJMSHibernateSearchController {

    private static Logger log = Logger.getLogger(MasterBootstrap.class);

    @Autowired private SessionFactory sessionFactory;

    private Session session;

    @Override
    public void onMessage(Message message) {
        log.debug("Processing message " + message);
        super.onMessage(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected Session getSession() {
        session = sessionFactory.openSession();
        return session;
    }

    @Override
    protected void cleanSessionIfNeeded(Session session) {
        session.close();
    }
}
