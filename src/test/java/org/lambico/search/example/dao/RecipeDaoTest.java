package org.lambico.search.example.dao;

import org.lambico.search.example.dao.RecipeDao;
import java.util.List;

import javax.annotation.Resource;
import org.lambico.search.example.po.Recipe;
import org.lambico.search.example.test.BaseTest;

public class RecipeDaoTest extends BaseTest {
    
    @Resource
    private RecipeDao recipeDao;
    
    
    public void testAllBooksByBorrower() {
    	System.out.println(String.format("The classpath is %s",  System.getProperty("java.class.path")));
    	List<Recipe> list = recipeDao.allRecipesByCook("Ugo", "Benfante");
    	assertEquals(2, list.size());      
    }
    
    public void testFindByAuthor() {
    	
        List<Recipe> books = recipeDao.findByAuthor("Doug Lea");
        assertEquals(1, books.size());
        assertEquals(books.get(0).getTitle(), "Concurrent programming in java second edition");
        
    }
    
   
    
}
