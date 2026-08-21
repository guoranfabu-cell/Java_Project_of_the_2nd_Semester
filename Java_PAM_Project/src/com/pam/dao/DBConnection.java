package com.pam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    // db 폴더 밑에 SQLite 데이터베이스 파일 저장
    private static final String URL = "jdbc:sqlite:db/pam_database.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // 프로그램 최초 실행 시 테이블 자동 생성
    public static void initDatabase() {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                email TEXT PRIMARY KEY,
                nickname TEXT NOT NULL,
                gender TEXT NOT NULL CHECK(gender IN ('MALE', 'FEMALE', 'NONE')),
                created_at TEXT DEFAULT (datetime('now', 'localtime'))
            );
        """;

        String createSchedulesTable = """
            CREATE TABLE IF NOT EXISTS schedules (
                schedule_id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_email TEXT NOT NULL,
                title TEXT NOT NULL,
                schedule_date TEXT NOT NULL,
                schedule_time TEXT,
                memo TEXT CHECK (length(memo) <= 500),
                created_at TEXT DEFAULT (datetime('now', 'localtime')),
                FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE
            );
        """;

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // SQLite Foreign Key 활성화
            stmt.execute("PRAGMA foreign_keys = ON;");

            stmt.execute(createUsersTable);
            stmt.execute(createSchedulesTable);
            System.out.println("SQLite DB 및 테이블 자동 생성/연결 완료!");
        } catch (SQLException e) {
            System.err.println("DB 연결 오류: " + e.getMessage());
        }
    }
}