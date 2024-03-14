package org.mess.repository;

import java.sql.SQLException;
import java.util.*;
import org.mess.model.CategoryModel;

public class CategoryRepository extends DBConfig {
	List<CategoryModel> list = new ArrayList<CategoryModel>();

	public boolean addCategory(CategoryModel model) {
		try {
			stmt = conn.prepareStatement("insert into category values('0',?,?)");
			stmt.setString(1, model.getCategory());
			stmt.setInt(2, model.getFees());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {
			System.err.println("Error is re " + e);
			return false;
		}
	}

	public boolean isCategoryPresent(CategoryModel model) {
		try {
			stmt = conn.prepareStatement("select category from category where category=?");
			stmt.setString(1, model.getCategory());
			rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.err.println("Error is " + e);
			return false;
		}
	}

	public List<CategoryModel> getAllCategories() {
		try {

			stmt = conn.prepareStatement("select * from category");
			rs = stmt.executeQuery();
			while (rs.next()) {
				CategoryModel model1 = new CategoryModel();
				model1.setCid(rs.getInt(1));
				model1.setCategory(rs.getString(2));
				model1.setFees(rs.getInt(3));
				list.add(model1);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception e) {
			System.err.println("Error is " + e);
			return null;
		}
	}
	public boolean updateCategory(CategoryModel model) {
		try {
			stmt=conn.prepareStatement("update category set category=?,fees=? where cid=?");
			stmt.setString(1,model.getCategory());
			stmt.setInt(2, model.getFees());
			stmt.setInt(3, model.getCid());
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return false;
		}
	}
	public boolean deleteCategory(String category) {
		try {
			stmt=conn.prepareStatement("delete from category where category=?");
			stmt.setString(1, category);
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}catch(Exception e) {
			System.err.println("Error is "+e);
			return false;
		}
	}
}
