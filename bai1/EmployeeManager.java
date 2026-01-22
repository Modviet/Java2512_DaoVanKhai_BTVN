package com.example.bai9.bai1;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EmployeeManager {
           private Employee[][] company;
           private int numberOfDepartments;

           public EmployeeManager(int numberOfDepartments){
               this.numberOfDepartments = numberOfDepartments;
               this.company = new Employee[numberOfDepartments][];
           }

           public EmployeeManager(){
               this.numberOfDepartments = 3;
               this.company = new Employee[numberOfDepartments][];
           }

           public double inputSalary() throws InvalidDataException{
               try {
                   double salary = new Scanner(System.in).nextDouble();

                   if(salary <=0) {
                       throw new InvalidDataException("Luong phai lon hon 0 VND");
                   }
                   return salary;
               } catch (Exception e) {
                   throw new InvalidDataException("Dinh dang tien luong khong hop le.");
               }
           }

           public void inputDepartmentData(){
               System.out.println("====/Nhap thong tin cong ty====");
               System.out.println("Nhap vao so cua phong ban muon them nhan vien : ");
               int departmentIndex = new Scanner(System.in).nextInt()-1;
               System.out.println("Nhap so luong nhan vien trong phong ban : ");
               int numberOfEmployees = new Scanner(System.in).nextInt();
               Scanner sc = new Scanner(System.in);
               company[departmentIndex] = new Employee[numberOfEmployees];

               System.out.println("Nhao thong tin phong ban "+(departmentIndex+1));
               for(int i=0;i<numberOfEmployees;i++){
                   System.out.println("Nhan vien : "+(i+1));

                   try{
                       System.out.println("Nhan vien thuoc (1-Dev, 2-Tester) : ");
                       int type = sc.nextInt();
                       sc.nextLine();

                       System.out.println("ID Nhan vien : ");
                       String id = sc.nextLine();

                       System.out.println("Ten nhan vien : ");
                       String name = sc.nextLine();

                       System.out.println("Muc luong co ban : ");
                       double salary = inputSalary();

                       Employee emp;
                       if(type == 1){
                           System.out.println("So luong ky nang : ");
                           int numSkills = sc.nextInt();
                           sc.nextLine();

                           String[] skills = new String[numSkills];
                           for(int j =0;j<numSkills;j++){
                               System.out.println("Ky nang "+(j+1)+" : ");
                               skills[j] = sc.nextLine();
                           }
                           emp = new Developer(id,name,salary,skills);
                       } else {
                           System.out.println("So bug tim thay : ");
                           int bugsFound = sc.nextInt();
                           emp = new Tester(id,name,salary,bugsFound);
                       }
                    company[departmentIndex][i] = emp;
                       System.out.println("Them nhan vien thanh cong");
                   } catch (InvalidDataException e){
                       System.out.println("Loi nhap lieu : "+e.getMessage());
                       System.out.println("Vui long nhap lai.");
                       sc.nextLine();
                       i--;
                   } finally {
                       Runtime runtime = Runtime.getRuntime();
                       long usedMemory = (runtime.totalMemory()-runtime.freeMemory())/(1024*1024);
                       System.out.println("Bo nho dang su dung : "+usedMemory);
                   }
               }
           }

           public void displayCompanyInfo(){
               SalaryBonus bonusNewYear = e->e.calculateSalary() * 0.1;
               SalaryBonus bonusProject = e->5000000;

               System.out.println("Thong tin cong ty ");
               for(int i=0;i<numberOfDepartments;i++){
                   if( company[i] != null){
                       System.out.println("Phong ban : "+(i+1));
                       for(int j=0;j<company[i].length;j++){
                           if(company[i][j] !=null ){
                               System.out.println("Nhan vien "+(j+1));
                               company[i][j].displayInfo();
                               System.out.println("Tien thuong Tet : "+bonusNewYear.calculate(company[i][j])+" VND");
                               System.out.println("Tien thuong du an : "+bonusProject.calculate(company[i][j])+" VND");
                           }
                       }
                   }
               }
           }

           public void filterEmployee(){
               System.out.println("Nhap vao tien luong muon loc : ");
               double salaryFilter = new Scanner(System.in).nextDouble();
               System.out.println("Nhap vao ky nang muon loc : ");
               String skillFilter = new Scanner(System.in).nextLine();

               Predicate<Employee> salaryPredicate = e ->e.calculateSalary() > salaryFilter;

               Predicate<Employee> skillDev = e->{
                   if(!(e instanceof Developer)){
                       return false;
                   }
                   Developer dev = (Developer) e;
                   for(String skill : dev.getSkill()){
                       if(skill.equalsIgnoreCase(skillFilter)){
                           return true;
                       }
                   }
                   return false;
                };

               System.out.println("Nhan vien co luong > "+String.format("%,.0f",salaryFilter)+"VND : ");
               for(Employee[] department : company){
                   if(department == null){
                       continue;
                   }
                   for(Employee emp:department){
                       if(emp != null && salaryPredicate.test(emp)){
                           System.out.println(" - "+emp.getName()+" - Luong : "+String.format("%,.0f",emp.calculateSalary()+"VND"));
                       }
                   }
               }

               System.out.println("Developer co ky nang : "+skillFilter);
               for(Employee[] department : company){
                   if(department == null){
                       continue;
                   }
                   for(Employee emp:department){
                       if(emp != null && skillDev.test(emp)){
                           Developer dev = (Developer) emp;
                           System.out.println(" - "+dev.getName()+" Ky nang : "+String.join(", ",dev.getSkill()));
                       }
                   }
               }
           }

}
