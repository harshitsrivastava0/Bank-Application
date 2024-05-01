package com.jsp.view;

import com.jsp.dao.CustomerDaoImplentation;
import com.jsp.dao.EmployeeDao;
import com.jsp.dao.EmployeeDaoImplementation;
import com.jsp.model.Customer;
import com.jsp.model.Employee;
import java.util.List;
import java.util.Scanner;

public class Main {

   public static void main(String[] args) {
      EmployeeDao empDao = new EmployeeDaoImplementation();
      CustomerDao customerDao = new CustomerDaoImplentation();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Welcome to bank login");

      while(true) {
         while(true) {
            System.out.println("1.Customer");
            System.out.println("2.Employee");
            System.out.println("select the choice");
            int choice = scanner.nextInt();
            String username;
            String password;
            long accNo;
            switch (choice) {
               case 1:
                  System.out.println("1.login");
                  System.out.println("Select the choice");
                  switch (scanner.nextInt()) {
                     case 1:
                        System.out.println("enter the email");
                        username = scanner.next();
                        System.out.println("enter the password");
                        password = scanner.next();
                        Customer customerlogin = customerDao.login(username, password);
                        if (customerlogin != null) {
                           System.out.println("1.deposit");
                           System.out.println("2.withdraw");
                           System.out.println("3.check balance");
                           System.out.println("4.transfer");
                           System.out.println("5.log out");
                           double balance;
                           switch (scanner.nextInt()) {
                              case 1:
                                 System.out.println("enter the account number ");
                                 accNo = scanner.nextLong();
                                 System.out.println("enter the amount");
                                 balance = scanner.nextDouble();
                                 customerDao.deposit(accNo, balance);
                                 balance = customerDao.checkBalance(accNo);
                                 System.out.println("available balance " + balance);
                                 continue;
                              case 2:
                                 System.out.println("enter the account number ");
                                 accNo = scanner.nextLong();
                                 System.out.println("enter the amount");
                                 balance = scanner.nextDouble();
                                 System.out.println("enter the pin");
                                 int pin = scanner.nextInt();
                                 boolean result = customerDao.withdraw(accNo, balance, pin);
                                 if (!result) {
                                    System.out.println("invaliad pin");
                                 }

                                 balance = customerDao.checkBalance(accNo);
                                 System.out.println("available balance " + balance);
                                 continue;
                              case 3:
                                 System.out.println("enter the account number ");
                                 accNo = scanner.nextLong();
                                 balance = customerDao.checkBalance(accNo);
                                 System.out.println("available balance " + balance);
                                 continue;
                              case 4:
                              case 5:
                                 System.out.println("thank for using");
                           }
                        } else {
                           System.out.println("invalid credentials...");
                        }
                     default:
                        continue;
                  }
               case 2:
                  System.out.println("1.Register");
                  System.out.println("2.login");
                  switch (scanner.nextInt()) {
                     case 1:
                        Employee employee = Util.createEmployee(scanner, new Employee());
                        boolean result = empDao.saveEmployee(employee);
                        System.out.println(result);
                        continue;
                     case 2:
                        System.out.println("enter the email");
                        username = scanner.next();
                        System.out.println("enter the password");
                        password = scanner.next();
                        Employee employeelogin = empDao.login(username, password);
                        if (employeelogin != null) {
                           System.out.println("1.create customer");
                           System.out.println("2.update customer");
                           System.out.println("3.delete customer");
                           System.out.println("4.find customer");
                           System.out.println("5.find All customer");
                           System.out.println("6.find All Transcation");
                           System.out.println("6.logout");
                           System.out.println("enter the choice");
                           switch (scanner.nextInt()) {
                              case 1:
                                 Customer customer = Util.createCustomer(scanner, new Customer());
                                 empDao.createCustomer(customer);
                                 continue;
                              case 2:
                                 System.out.println("enter the name");
                                 String name = scanner.next();
                                 System.out.println("enter the id of customer");
                                 int id = scanner.nextInt();
                                 empDao.updateCustomerNameById(name, id);
                                 continue;
                              case 3:
                                 System.out.println("enter the id of customer");
//                                 int id = scanner.nextInt();
//                                 empDao.deleteCustomer(id);
                                 continue;
                              case 4:
                                 System.out.println("enter the id of customer");
                                 accNo = scanner.nextLong();
                                 empDao.findCustomerBy(accNo);
                                 continue;
                              case 5:
                                 List<Customer> customers = empDao.findCustomers();
                                 System.out.println(customers);
                                 continue;
                              case 6:
                                 System.out.println("logout sucessfully");
                           }
                        } else {
                           System.out.println("invalid credentials");
                        }
                        continue;
                  }
               default:
                  System.out.println("Invalid choice!...");
            }
         }
      }
   }
}
