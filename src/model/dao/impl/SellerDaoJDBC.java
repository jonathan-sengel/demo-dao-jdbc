/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Seller;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.entities.Department;

/**
 *
 * @author Jow
 */
public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(findSellerById(id));
            rs = st.executeQuery();
            if (rs.next()) {
                Department dept = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dept);
                return seller;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(findSellersByDepartment(department.getId()));
            rs = st.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> deptMap = new HashMap<>();
            while (rs.next()) {
                Department dep = deptMap.get(rs.getInt("department_id"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    deptMap.put(rs.getInt("department_id"), dep);
                }
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private String findSellerById(Integer sellerId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  seller.*, ");
        sql.append("        department.name AS dept_name ");
        sql.append("FROM seller ");
        sql.append("INNER JOIN department ON seller.department_id = department.id ");
        sql.append("WHERE seller.id = ").append(sellerId);
        return sql.toString();
    }

    private String findSellersByDepartment(Integer departmentId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  seller.*,");
        sql.append("        department.name AS dept_name");
        sql.append(" FROM seller");
        sql.append(" INNER JOIN department ON seller.department_id = department.id");
        sql.append(" WHERE department_id = ").append(departmentId);
        sql.append(" ORDER BY name");
        return sql.toString();
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dept = new Department();
        dept.setId(rs.getInt("department_id"));
        dept.setName(rs.getString("dept_name"));
        return dept;
    }

    private Seller instantiateSeller(ResultSet rs, Department dept) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("id"));
        seller.setName(rs.getString("name"));
        seller.setEmail(rs.getString("email"));
        seller.setBaseSalary(rs.getDouble("base_salary"));
        seller.setBirthDate(rs.getDate("birth_date"));
        seller.setDepartment(dept);
        return seller;
    }

}
