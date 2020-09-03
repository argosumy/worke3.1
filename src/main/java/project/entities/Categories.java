package main.java.project.entities;

import org.springframework.stereotype.Component;


@Component
public class Categories extends Entity {
    private String description;
    private int parentId;



    public Categories() {
        super();
    }

    public Categories(String name, String description, int parentId) {
        super(name);
        this.description = description;
        this.parentId = parentId;
    }
    public Categories(int id, String name, String description, int parentId) {
        super(id,name);
        this.description = description;
        this.parentId = parentId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.description + " " + this.parentId;
    }
}
