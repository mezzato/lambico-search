package org.lambico.search.example.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.lambico.search.example.dao.RecipeDao;
import org.lambico.search.example.po.Person;
import org.lambico.search.example.po.Recipe;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RecipeBo {

    @Resource
    private RecipeDao recipeDao;
	

    @Transactional()
    public void populateArchive() throws ParseException {
    	
    	Map<String, String> samples = new HashMap<String, String>();
    	samples.put("Rocky salmon on the spit", "Jamie Oliver");
    	samples.put("A grill on an electric guitar", "Carlos Santana");
    	samples.put("Fist mashed patatoes", "Mike Tyson");
    	
    	//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	
    	Calendar calendar = new GregorianCalendar(2011,Calendar.NOVEMBER,11);
    	
    	
    	List<Recipe> searches = null;
		for (Map.Entry<String, String> item : samples.entrySet()) {
			String title = item.getKey();
			String author = item.getValue();
			
			searches = recipeDao.findByAuthorAndTitle(author, title);
			
			if (searches.isEmpty()) {
	            Recipe r = new Recipe(author, title);
	            
	            Person cook = new Person();
	            cook.setFirstName(String.format("Cook for recipe: %s", title));
	            calendar.add(Calendar.HOUR, 24);
	            cook.setBirthDate(calendar.getTime());
	            
	            r.setCook(cook);
	            
	            recipeDao.create(r);
	        }
		}
    	
        
        
    }

    @Transactional(readOnly = true)
    public void printRecipe(Long id) {
        Recipe r = recipeDao.read(id);
        if (r != null) {
            System.out.println(r.getAuthor() + " " + r.getTitle() + " " + r.getCook());
        } else {
            System.out.println("Person ("+id+") not found");
        }
    }
    

    /**
     * This method is called when a book is returned to the library.
     * It resets the borrower of that book and returns the Person
     * that had this book.
     * @param BookTitle
     */
    @Transactional()
    public Person resetCook(String author, String title) {
        Recipe recipe = recipeDao.findByAuthorAndTitle(author, title).get(0);
        Person oldCook = recipe.getCook();
        recipe.setCook(null);
        recipeDao.store(recipe);
        return oldCook;
    }

}
