package org.lambico.search.example.dao;

import org.lambico.search.example.dao.RecipeDao;
import java.util.List;

import javax.annotation.Resource;
import org.lambico.search.example.po.Recipe;
import org.lambico.search.example.test.BaseTest;

public class RecipeDaoTest extends BaseTest {
    
    @Resource
    private RecipeDao recipeDao;
    
    
    public void testAllRecipesByCook() {
    	System.out.println(String.format("The classpath is %s",  System.getProperty("java.class.path")));
    	List<Recipe> list = recipeDao.allRecipesByCook("Sow", "Boar");
    	assertEquals(1, list.size());      
    }
    
    public void testFindByAuthor() {
    	
        List<Recipe> books = recipeDao.findByAuthor("Mr Bean");
        assertEquals(1, books.size());
        assertEquals(books.get(0).getTitle(), "What an amazon eats");
        
    }
    
   
    
}
