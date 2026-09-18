package com.pam.dao;

import com.pam.model.User;
import org.sqlite.core.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean insertUser(String email, String password, String nickname, String gender) {
        String sql = """
                INSERT INTO users (email, password, nickname, gender)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, nickname);
            pstmt.setString(4, gender);

            return pstmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("회원 저장 실패" + e.getMessage());
            return false;
        }
    }

    public boolean existByEmail(String email)  {
        String sql = """
                SELECT 1 FROM users WHERE email = ?;
        """;

        try (Connection conn =  DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("이메일 확인 실패: " + e.getMessage());
            return false;
        }
    }

    public boolean isValidLogin(String email, String password)  {
        String sql = """
                SELECT 1
                FROM users
                WHERE email = ? AND password = ?;
                """;

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Invalid email or password!");
            return false;
        }
    }

    public String getNickname(String email)  {
        String sql = """
                SELECT nickname FROM users WHERE email = ?
        """;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            System.out.println("Invalid email or password!" + e.getMessage());
        }

        return null;
    }

    public String getGender(String email)  {
        String sql = """
                SELECT gender FROM users WHERE email = ?;
        """;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getString("gender");
            }
        } catch (SQLException e) {
            System.out.println("Invalid email!" + e.getMessage());
        }

        return null;
    }

    public String getPassword(String email) {
        String sql = """
                SELECT password FROM users WHERE email = ?;
        """;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Invalid email!" + e.getMessage());
        }

        return null;
    }
}
