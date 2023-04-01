/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.util.List;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 *
 * @author Jow
 */
public class Program2 {

    public static void main(String[] args) {
        
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        
        System.out.println(">>>>>> TEST 1: department findAll <<<<<<");
        List<Department> list = depDao.findAll();
        list.forEach(System.out::println);
        System.out.println("total rows: " + list.size());
        
        System.out.println();
        System.out.println(">>>>>> TEST 2: department findById <<<<<<");
        Department dep = depDao.findById(4);
        System.out.println(dep);
    }
}
