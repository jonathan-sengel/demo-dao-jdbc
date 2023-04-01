/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(">>>>>> TEST 1: seller findById <<<<<<");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);

        System.out.println();
        System.out.println(">>>>>> TEST 2: seller findByDepartment <<<<<<");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println("total rows: " + list.size());

        System.out.println();
        System.out.println(">>>>>> TEST 3: seller findAll <<<<<<");
        list = sellerDao.findAll();
        list.forEach(System.out::println);
        System.out.println("total rows: " + list.size());

        System.out.println();
        System.out.println(">>>>>> TEST 4: seller insert <<<<<<");
        Seller newSeller = new Seller(null, "Greg Silva", "greg@mail.com", new Date(), 1850.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted. New id = " + newSeller.getId());

        System.out.println();
        System.out.println(">>>>>> TEST 5: seller update <<<<<<");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println();
        System.out.println(">>>>>> TEST 6: seller delete <<<<<<");
        System.out.print("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed");

        sc.close();
    }

}
