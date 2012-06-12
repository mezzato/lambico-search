package org.lambico.search.example.app;

import java.util.List;
import javax.annotation.Resource;

import org.lambico.search.example.dao.RecipeDao;
import org.lambico.search.example.po.Recipe;
import org.lambico.search.example.util.ApplicationContextHolder;

public class UseRecipeDao {

    @Resource
    private RecipeDao recipeDao;

    public UseRecipeDao() {
        ApplicationContextHolder.autowireBeanProperties(this);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        UseRecipeDao app = new UseRecipeDao();
        Recipe myRecipe = new Recipe("Lambico Team", "Lambico: easy persistence");

        Recipe myRecipe2 = new Recipe("Mario Rossi", "My life");
        Recipe myRecipe3 = new Recipe("Lambico Team", "Lambico: tips & trick");
        Recipe myRecipe4 = new Recipe("The Hitchhiker's Guide to the Galaxy",
                "Douglas Adams", 320);

        app.recipeDao.create(myRecipe);
        app.recipeDao.create(myRecipe2);
        app.recipeDao.create(myRecipe3);
        app.recipeDao.create(myRecipe4);


        System.out.println("Searching for Lambico Team's books...");
        List<Recipe> myRecipes = app.recipeDao.findByAuthor("Lambico Team");
        System.out.println(myRecipes.size()+" result(s) found!");
        for (Recipe book : myRecipes) {
            System.out.println(book);
        }
    }
}
