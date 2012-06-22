package org.lambico.search.example.action;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.lambico.search.example.po.Person;

/**
 * Example 2.8, 2.9
 */
public class Indexer {
	public void indexWithJPA() {
		EntityManager em = org.lambico.search.example.util.EntityManagerHolder.getEntityManager();
		
		//wrap a EntityManager object
		FullTextEntityManager ftem = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
		
		ftem.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Person> items = em.createQuery("select i from Person i").getResultList();
		
		for (Person item : items) {
		    ftem.index(item);  //manually index an Person instance
		}
		
		ftem.getTransaction().commit(); //index are written at commit time
	}
	
	public void indexWithHibernate() {
		Session session = org.lambico.search.example.util.SessionHolder.getSession();
		
		//wrap a Session object
		FullTextSession ftSession = org.hibernate.search.Search.getFullTextSession(session);
		ftSession.getTransaction().begin();
		
		@SuppressWarnings("unchecked")
		List<Person> items = session.createCriteria(Person.class).list();
		
		for (Person item : items) {
		    ftSession.index(item);  //manually index an Person instance
		}
		
		ftSession.getTransaction().commit(); //index are written at commit time
	}
}
