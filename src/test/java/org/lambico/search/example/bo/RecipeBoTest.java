package org.lambico.search.example.bo;

import org.lambico.search.example.bo.RecipeBo;
import org.lambico.search.example.po.Person;
import org.lambico.search.example.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeBoTest extends BaseTest {

	@Autowired
	private RecipeBo recipeBo;

	public void testBookReturned() {
		Person p = recipeBo.resetCook("Chick Corea", "Jazzed skewer");
		assertTrue(p.getFirstName().equals("Hen"));

	}

}
