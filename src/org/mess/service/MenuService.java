package org.mess.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import org.mess.helper.PathHelper;
import org.mess.repository.MenuRepository;

public class MenuService {
	MenuRepository menuRepo=new MenuRepository();
	
	public boolean addMenu() {
		
	    try {
	    	FileReader f=new FileReader(PathHelper.filePath);
	    	BufferedReader br=new BufferedReader(f);   // line by line data read
			String weekmenu;
			boolean flag=false;
			while((weekmenu=br.readLine())!=null) { 
				String weekm[]=weekmenu.split(",");  
				flag=menuRepo.addMenu(weekm);  
			}
			return true;
	    }catch(Exception e) {
	    	System.out.println("Error is "+e);
	    	return false;
	    }
	}
	public List getTodaysMenu(String d){
		return menuRepo.getTodaysMenu(d);
	}
}
