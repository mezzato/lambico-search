package org.lambico.search.example.bo;

import org.lambico.search.example.bo.RecipeBo;
import org.lambico.search.example.po.Person;
import org.lambico.search.example.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

public class RecipeBoTest extends BaseTest {

	@Autowired
	private RecipeBo recipeBo;

	public void testBookReturned() {
		Person p = recipeBo.resetCook("Doug Lea", "Concurrent programming in java second edition");
		assertTrue(p.getFirstName().equals("Ugo"));

	}

}
