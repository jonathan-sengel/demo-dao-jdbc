/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/**
 *
 * @author Jow
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        
        System.out.println(">>>>>> TEST 1: seller findById <<<<<<");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);
        
        System.out.println();
        System.out.println(">>>>>> TEST 2: seller findByDepartment <<<<<<");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
    }
    
}
