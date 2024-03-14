package org.mess.service;
import java.util.*;
import org.mess.model.CategoryModel;
import org.mess.repository.CategoryRepository;

public class CategoryService {
	CategoryRepository categoryRepo= new CategoryRepository();
	public int addCategory(CategoryModel model) {
		return categoryRepo.isCategoryPresent(model)?-1:categoryRepo.addCategory(model)?1:0;
	}
	public List<CategoryModel> getAllCategories(){
		return this.categoryRepo.getAllCategories();
	}
	public boolean updateCategory(CategoryModel model) {
		return categoryRepo.updateCategory(model);
	}
	public boolean deleteCategory(String category) {
		return categoryRepo.deleteCategory(category);
	}
}
