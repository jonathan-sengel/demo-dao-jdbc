/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.DepartmentDao;
import model.entities.Department;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Jow
 */
public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Department obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return instantiateDepartment(rs);
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
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                list.add(instantiateDepartment(rs));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dept = new Department();
        dept.setId(rs.getInt("id"));
        dept.setName(rs.getString("name"));
        return dept;
    }

    @Override
    public List<Integer> sellersIdInDepartment(Integer departmentId) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT id FROM seller WHERE department_id = ?");
            st.setInt(1, departmentId);
            rs = st.executeQuery();
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getInt("id"));
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }
}
