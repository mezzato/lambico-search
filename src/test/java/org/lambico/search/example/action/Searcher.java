package org.lambico.search.example.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.lambico.search.example.po.Recipe;
import org.lambico.search.example.util.SessionHolder;

/**
 * Example 2.10, 2.11, 2.12, 2.13
 */
public class Searcher {
	public List<Recipe> search() {
		//Building the Lucene query
		String searchQuery = "title:Batman OR description:Batman";  //query string
		QueryParser parser = new QueryParser(
				null, "title",  //default field 
				new StandardAnalyzer(null) //analyzer used
		);

		org.apache.lucene.search.Query luceneQuery;
		try {
		    luceneQuery = parser.parse(searchQuery);  //build Lucene query
		}
		catch (ParseException e) {
		    throw new RuntimeException("Unable to parse query: " + searchQuery, e);
		}
		
		Session session = SessionHolder.getSession();
		FullTextSession ftSession = org.hibernate.search.Search.getFullTextSession(session);
		Query query = ftSession.createFullTextQuery(luceneQuery, Recipe.class);
		
		@SuppressWarnings("unchecked")
		final List<Recipe> results = query.list();  //execute the query
		return results;
	}
	
	public List<Recipe> searchMultipleFields() {
		// Building the Lucene query
		String searchQuery = "Batman";
		
		String[] productFields = {"title", "description"};   // targeted fields
		
		Map<String,Float> boostPerField = new HashMap<String,Float>(2); // boost factors
		boostPerField.put( "title", (float) 4); 
		boostPerField.put( "description", (float) 1); 
		
		QueryParser parser = new MultiFieldQueryParser(   // build query parser
			null, 
			productFields, 
			new StandardAnalyzer(null), 
			boostPerField 
		); 
		org.apache.lucene.search.Query luceneQuery; 
		try { 
			luceneQuery = parser.parse(searchQuery); 
		} 
		catch (ParseException e) { 
			throw new RuntimeException("Unable to parse query: " + searchQuery, e);
		}
		
		Session session = SessionHolder.getSession();
		FullTextSession ftSession = org.hibernate.search.Search.getFullTextSession(session);
		Query query = ftSession.createFullTextQuery(luceneQuery, Recipe.class);  //return matching Items
		
		query.setFirstResult(0).setMaxResults(20);  //Use pagination
		
		@SuppressWarnings("unchecked")
		final List<Recipe> results = query.list();  //execute the query
		return results;
	}
}
