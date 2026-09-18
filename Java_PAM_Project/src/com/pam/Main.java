package com.pam;

import com.pam.model.*;
import com.pam.dao.*;
import com.pam.view.*;
import com.pam.util.*;
import com.pam.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // 1. DB 및 테이블 자동 생성 테스트
        DBConnection.initDatabase();

        LoginView loginView = new LoginView();
    }
}