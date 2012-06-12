package org.lambico.search.example.po;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.lambico.po.hibernate.EntityBase;

@javax.persistence.Entity()
@NamedQueries(
value = {
    @NamedQuery(name = "Recipe.allRecipesByCook",
    query =
    "from Recipe r where upper(r.cook.firstName) = upper(?) and upper(r.cook.lastName) = upper(?)")})
public class Recipe extends EntityBase {

	private int numPages = 0;
    private String author = null;
    private String title = null;
    private Person cook = null;

    /**
     *
     */
    public Recipe() {
        // TODO Auto-generated constructor stub
    }

    public Recipe(String author, String title) {
        this(author, title, 0);
    }

    public Recipe(String author, String title, int numPages) {
        this.author = author;
        this.title = title;
        this.numPages = numPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    public Person getCook() {
        return cook;
    }

    public void setCook(Person cook) {
        this.cook = cook;
    }

    @Override
    public String toString() {
        return "Recipe{" + "numPages=" + numPages + " ; author=" + author + " ; title=" + title + " ; cook=" +
                cook + '}';
    }

}
