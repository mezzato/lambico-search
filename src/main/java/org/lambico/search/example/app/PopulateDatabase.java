package org.lambico.search.example.app;

import org.lambico.search.example.bo.RecipeBo;
import org.lambico.search.example.util.ApplicationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class PopulateDatabase {

    @Autowired
    private RecipeBo recipeBO;

    public PopulateDatabase() {
        ApplicationContextHolder.autowireBeanProperties(this);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        PopulateDatabase app = new PopulateDatabase();
        // Popoulating the database
        app.recipeBO.populateArchive();
        // Print the person with id=1
        app.recipeBO.printRecipe(1L);
    }
}
