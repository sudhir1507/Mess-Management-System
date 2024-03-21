package org.mess.client;

import java.util.*;

import org.mess.helper.PathHelper;
import org.mess.model.AttendenceModel;
import org.mess.model.BillModel;
import org.mess.model.CategoryModel;
import org.mess.model.MealModel;
import org.mess.model.MenuModel;
import org.mess.model.RegistrationModel;
import org.mess.service.AttendenceService;
import org.mess.service.BillService;
import org.mess.service.CategoryService;
import org.mess.service.MealService;
import org.mess.service.MenuService;
import org.mess.service.RegistrationService;
import java.sql.Date;

public class MessManagementApplication {

	public static void main(String[] args) {
		CategoryModel model = new CategoryModel();
		CategoryService catgoryService = new CategoryService();
		RegistrationService regService = new RegistrationService();
		RegistrationModel rmodel = new RegistrationModel();
		MealModel mmodel = new MealModel();
		MealService mealService = new MealService();
		MenuService menuService = new MenuService();
		AttendenceModel amodel=new AttendenceModel();
		AttendenceService AtteSerive=new AttendenceService();
		BillModel bmodel=new BillModel();
		BillService billService=new BillService();
		String username = "";
		String password = "";
		Scanner sc = new Scanner(System.in);
		do {

			System.out.println("1. Admin");
			System.out.println("2. User");
			System.err.println("Choose Login Type");
			int loginType = sc.nextInt();
			switch (loginType) {
			case 1:
				boolean ishome;
				sc.nextLine();
				System.out.println("Adim Enter username and password");
				username = sc.nextLine();
				password = sc.nextLine();
				if (username.equals(PathHelper.username) && password.equals(PathHelper.password)) {
					System.err.println("Admin verified..!\n");
					System.out.println("================WELCOME ADMIN=====================\n");
					do {

						System.out.println("1. Add Category");
						System.out.println("2. View All Category");
						System.out.println("3. Update Category");
						System.out.println("4. Delete Category");
						System.out.println("5. Add MealType");
						System.out.println("6. Add Week Menu");
						System.out.println("7. Register Candidate");
						System.out.println("8. See All Registration");
						System.out.println("10. Take Attendence");
						System.out.println("11. Generate Bill");
						System.out.println("12. Count Monthly two times/one time/daily (Rice plate) Members");
						System.out.println("13. Total bill of Daily Mamber ");
						System.out.println("15. Back to Home");
						ishome = true;
						System.err.println("Enter choice");
						int choice = sc.nextInt();
						switch (choice) {
						case 1:
							sc.nextLine();
							System.out.println("Enter Category Name and fees");
							String category = sc.nextLine();
							int fees = sc.nextInt();
							model.setCategory(category);
							model.setFees(fees);
							int result = catgoryService.addCategory(model);
							if (result == 1) {
								System.out.println("Category Added SuccessFully..!");
							} else if (result == -1) {
								System.err.println("Category Already Present..!");
							} else {
								System.err.println("Some PROBLEM is there..Category Not Added..!");
							}
							break;
						case 2:
							System.out.println("Here, See All Category");
							System.out.println("Cid \tCategory Name\tFees");
							List<CategoryModel> list = catgoryService.getAllCategories();
							for (CategoryModel cm : list) {
								System.out.println(cm.getCid() + "\t" + cm.getCategory() + "\t" + cm.getFees());
							}
							break;
						case 3:
							sc.nextLine();
							System.out.println("Enter category ID, Category Name and Fees to Update");
							int cid = sc.nextInt();
							fees = sc.nextInt();
							sc.nextLine();
							category = sc.nextLine();
							model.setCid(cid);
							model.setCategory(category);
							model.setFees(fees);
							boolean b = catgoryService.updateCategory(model);
							if (b) {
								System.err.println("Category Updated Successfully..!");
							} else {
								System.err.println("Category Not Found..!");
							}
							break;
						case 4:
							sc.nextLine();
							System.out.println("Enter Category name to Delete");
							category = sc.nextLine();
							b = catgoryService.deleteCategory(category);
							if (b) {
								System.err.println("Category deleted Successfully..!");
							} else {
								System.err.println("Category Not Found..!");
							}
							break;
						case 5:
							sc.nextLine();
							System.out.println("Enter MealId and Meal Type");
							int mtid = sc.nextInt();
							sc.nextLine();
							String mealtype = sc.nextLine();
							mmodel.setMtid(mtid);
							mmodel.setMealtype(mealtype);
							b = mealService.addMealType(mmodel);
							if (b) {
								System.err.println("Meal Added Successfully..!");
							} else {
								System.err.println("Meal Not Added.!");
							}
							break;
						case 6:
							sc.nextLine();
							b = menuService.addMenu();
							if (b) {
								System.out.println("Menu added Succesfully..!");
							} else {
								System.out.println("Not added Menu");
							}
							break;
						case 7:
							sc.nextLine();
							System.err.println("Register Candidate..!");
							System.out.println("Choose Category");
							category = sc.nextLine();
							
							System.out.println("Enter Name");
							String name = sc.nextLine();
							System.out.println("Enter contact");
							String contact = sc.nextLine();
							System.out.println("Enter address");
							String address = sc.nextLine();
							System.out.println("Enter Start date yyyy-mm-dd");
							String sdate = sc.nextLine();
							Date rsdate = Date.valueOf(sdate);
							System.out.println("Enter some amount in advance");
							int amount = sc.nextInt();
							sc.nextLine();
							Date redate;
							if (category.equalsIgnoreCase("Monthlytt") || category.equalsIgnoreCase("Monthlyot")) {

								System.out.println("Enter username");
								username = sc.nextLine();
								System.out.println("Enter password");
								password = sc.nextLine();
								System.out.println("Enter End date");
								String edate = sc.nextLine();
								redate = Date.valueOf(edate);
							} else {
								username = null;
								password = null;
								redate = null;

							}
							rmodel = new RegistrationModel(name, contact, address, rsdate, redate, amount, username,
									password);
							b = regService.addRegistration(rmodel, category);
							if (b) {
								System.err.println("Registred Successfully..!");
								System.out.println("Your registertion Id " + regService.getCurrentRegID());

							} else {
								System.err.println("Not Registred..try Again..!");
							}
							break;
						case 8:
							System.out.println("rid\tname\t\tcontact\t\taddress\t\trsdate\t\tredate\t\tamount\tusername\tpassword");
							List<RegistrationModel> rlist=regService.getAllRegistrations();
							for(RegistrationModel rm:rlist) {
								System.out.println(rm.getRid()+"\t"+rm.getName()+"\t\t"+rm.getContact()+"\t\t"+ rm.getAddress()+"\t\t"+rm.getRsdate()+"\t"+ rm.getRedate()+"\t"+rm.getAmount()+"\t"+rm.getUsername()+"\t\t"+rm.getPassword());
							}
							break;
						case 9:
							
							break;
						case 10:
							sc.nextLine();
							System.out.println("Enter Registration ID Date and Status and meal type");
							int rid=sc.nextInt();
							sc.nextLine();
							String adate=sc.nextLine();
							Date date=Date.valueOf(adate);
							int status=sc.nextInt();
							mtid=sc.nextInt();
							amodel.setRid(rid);
							amodel.setAdate(date);
							amodel.setStatus(status);
							amodel.setMtid(mtid);
							result=AtteSerive.markAttenedence(amodel);
							if(result==1) {
								System.out.println("Attendence Marked..");
							}else {
								System.out.println("Attendence Not Marked..");
							}
							break;
						case 11:
							sc.nextLine();
							System.out.println("Enter date and your registration ID");
							String bdate=sc.nextLine();
							date=Date.valueOf(bdate);
							rid=sc.nextInt();
							bmodel.setBdate(date);
							List<BillModel> blist=billService.getBill(bmodel,rid);
							int remain=0,bid=0;
							if(blist!=null) {
								System.err.println("Bill Generated..");
								System.out.println("Bid\trid\tdate\t\tTotalAmount\tPaidAmount\tRamaining\tstatus");
								for(BillModel m:blist) {
									System.out.print(m.getBid()+"\t"+m.getRid()+"\t"+m.getBdate()+"\t"+m.getTotalAmount()+"\t\t"+m.getPaid()+"\t\t"+m.getRemaining()+"\t\t"+m.getBstatus()+"\n");
									System.out.println("Your Ramining Amount "+m.getRemaining());
									remain=m.getRemaining();
									bid=m.getBid();
								}
//								System.out.println();
//								if(remain>0) {
//							    	sc.nextLine();
//							    	System.out.println("Pay Amount Yes / No");
//							    	    String ispay=sc.nextLine();
//							    		if(ispay.equalsIgnoreCase("Yes")) {
//							    			System.out.println("Enter Your remining amount");
//							    			int remining=sc.nextInt();
//							    			result=billService.updateBill(remining,rid,bid);
//							    			if(result!=0) {
//							    				System.out.println("Your Bill is Nill");
//							    			}else {
//							    				System.out.println("Please Pay All Remaining bill");
//							    			}
//							    		}	
//							    }else {
//							    	System.out.println("Your Bill is Nill");
//							      }
							}else {
								System.out.println("Bill Not generated..");
							}
							
							break;
						case 12:
							sc.nextLine();
							System.out.println("Enter Month");
							int month=sc.nextInt();
							System.out.println("Enter Year :");
							int year=sc.nextInt();
							sc.nextLine();
							System.out.println("Enter category");
							category=sc.nextLine();
							result = regService.countMembers(month,year,category);
							if (result != 0)
								System.out.println("Number of Members Register is :" + result);
							else
								System.out.println("No Registrations found");
							break;
						case 15:
							ishome = false;
							break;
						default:
							System.err.println("Wrong Choice..!");
						}
					} while (ishome);
				} else {
					System.err.println("Invalid username or password..!");
					System.out.println("Try Again..");
				}
				break;

			case 2:
				ishome = true;
				System.out.println("=========================WELCOME USER=========================\n");
				do {

					System.out.println("1. See All Categories");
					System.out.println("2. See Today's Menu");
					System.out.println("3. Login and choose Meal");
					System.out.println("8. Back Menu");
					int choic = sc.nextInt();
					switch (choic) {
					case 1:
						System.out.println("Here, See All Category");
						System.out.println("Cid \tCategory Name\tFees");
						List<CategoryModel> list = catgoryService.getAllCategories();
						for (CategoryModel cm : list) {
							System.out.println(cm.getCid() + "\t" + cm.getCategory() + "\t" + cm.getFees());
						}
						break;
					case 2:
						sc.nextLine();
						System.out.println("Enter Today's Date");
						String d = sc.nextLine();
						System.out.println("Date\t\t\tLunch\t\t\tDinner\n");
						list = menuService.getTodaysMenu(d);
						for (Object item : list) {
							System.out.print(item + "\t");
						}
						System.out.println();
						break;
					case 3:
						sc.nextLine();
						System.out.println("Choose Category");
						String category = sc.nextLine();
						System.out.println("Enter Your Registration id");
						int rid = sc.nextInt();
						sc.nextLine();
						if (category.equals("Monthlytt") || category.equals("Monthlyot")) {
							System.out.println("Enter Username");
							username = sc.nextLine();
							System.out.println("Enter Password");
							password = sc.nextLine();
							rmodel = regService.getLoginInfo(rid);
							if (username.equals(rmodel.getUsername()) && password.equals(rmodel.getPassword())) {
								System.err.println("Candidate Verified..!");
							} else {
								System.err.println("Invalid Login..!");
								break;
							}
						}
						System.out.println("Enter Meal Type");
						String mealType = sc.nextLine();
						System.out.println("Enter date");
						String td = sc.nextLine();
						Date tdate = Date.valueOf(td);
						regService.addCatMealReg(rid, category, mealType);
						break;
					case 8:
						ishome = false;
						break;
					default:
						System.out.println("Wrong choice");
					}
				} while (ishome);
				break;
			}
		} while (true);
	}

}
