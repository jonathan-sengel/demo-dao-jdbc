/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
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
    }

}
