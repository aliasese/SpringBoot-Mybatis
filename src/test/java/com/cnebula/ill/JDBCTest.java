package com.cnebula.ill;

import com.cnebula.ill.dto.BaseData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JDBCTest {

    @Test
    public void testJDBCSELECT(){
        try {
            Connection connection = JDBCUtil.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM manage_tenant");
            while (rs.next()) {
                Object object = rs.getObject(1);
                System.out.println(object);
            }
            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }

    }

    @Test
    public void testJDBCSELECT2(){
        try {
            Connection connection = JDBCUtil.getConnection();
            String sql = "SELECT * FROM manage_tenant WHERE true AND id = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, "a001150");
            Statement st = connection.createStatement();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getRow());
                Object object = rs.getObject(2);
                System.out.println(object);
            }
            rs.close();
            st.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            System.exit(1);
        }

    }

    @Test
    public void testJDBCINSERT() {
        Connection connection = JDBCUtil.getConnection();
        String INSERTSQL = "INSERT INTO manage_tenant(id, tenant, regdate, udpdate) VALUES (?,?::jsonb,now(), now())";
        try {
            PreparedStatement ps = connection.prepareStatement(INSERTSQL);
            ps.setString(1, "j000001");
            ps.setString(2, "{}");
            ps.execute();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testJDBCDELETE() {
        Connection connection = JDBCUtil.getConnection();
        String DELETESQL = "DELETE FROM manage_tenant WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(DELETESQL);
            ps.setString(1, "j000001");
            int i = ps.executeUpdate();
            System.out.println(i);
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testJDBCDPROCEDURE() {
        Connection connection = JDBCUtil.getConnection();
        String PROCEDURESQL = "SELECT pr_select()";
        try {
            PreparedStatement ps = connection.prepareStatement(PROCEDURESQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object object = rs.getObject(1);
                System.out.println(object);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
