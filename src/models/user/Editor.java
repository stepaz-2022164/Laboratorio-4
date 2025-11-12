package models.user;

import models.auxiliary.Category;

import java.util.ArrayList;

public class Editor extends User {
    private ArrayList<Category> assignedCategories;

    public Editor(String username, String email, String password) {
        super(username, email, password);
        this.role = "Editor";
        this.assignedCategories = new ArrayList<>();
    }

    @Override
    public boolean canPublish() { return false; }

    @Override
    public boolean canDelete() { return false; }

    public ArrayList<Category> getAssignedCategories() { return assignedCategories; }
    public void addAssignedCategory(Category category) { assignedCategories.add(category); }
}
