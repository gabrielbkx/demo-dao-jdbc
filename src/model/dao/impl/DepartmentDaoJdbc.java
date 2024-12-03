package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJdbc implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJdbc(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement ps = null;
        String sql = "insert into department (Name) values(?)";

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, department.getName());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    department.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Insert failed: No rows affected");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("update department set Name = ? where id = ?");
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("delete from department where id = ?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            String result = (rows == 0) ? "That id not exist" : "Deleted";
            System.out.println(result);

        } catch (SQLException e) {
            throw new DbException("Deleted failed " + e.getMessage());
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from department where id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("id"));
                dep.setName(rs.getString("Name"));
                return dep;
            }
            return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("select * from department order by Name");
            rs = ps.executeQuery();

            List<Department> list = new ArrayList<>();
            while (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("id"));
                dep.setName(rs.getString("Name"));
                list.add(dep);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }
}
