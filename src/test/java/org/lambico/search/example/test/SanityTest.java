package org.lambico.search.example.test;

import javax.annotation.Resource;
import org.lambico.search.example.bo.RecipeBo;
import org.lambico.search.example.dao.RecipeDao;

public class SanityTest extends BaseTest {

    @Resource
    private RecipeDao recipeD;
    @Resource
    private RecipeBo recipeBO1;

    /**
     * Test the wiring of BO resources.
     */
    public void testBoResources() {
        assertNotNull(recipeBO1);
    }

    /**
     * Test the wiring of DAO resources.
     */
    public void testDaoResources() {
        assertNotNull(recipeD);
    }
}
