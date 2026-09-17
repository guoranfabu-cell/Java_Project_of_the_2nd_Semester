package com.pam.view;

import com.pam.*;
import com.pam.dao.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    public LoginView() {
        this.setTitle("Welcome to PAM!");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 1. 바탕 패널 (정중앙 배치를 위해 GridBagLayout 사용)
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridBagLayout());

        // 2. 로그인 상자 패널 (가로 400, 세로 300)
        JPanel loginBackgroundPanel = new JPanel();
        loginBackgroundPanel.setPreferredSize(new Dimension(400, 300));
        loginBackgroundPanel.setBackground(new Color(255, 255, 255));
        loginBackgroundPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        loginBackgroundPanel.setLayout(null); // 내부 컴포넌트를 자유롭게 배치하기 위해 null 설정

        // --- 내부 입력 칸 및 버튼 컴포넌트 생성 ---

        // 제목 라벨
        JLabel titleLabel = new JLabel("PAM LOGIN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("굴림", Font.BOLD, 22));
        titleLabel.setBounds(50, 30, 300, 40);
        loginBackgroundPanel.add(titleLabel);

        // 아이디 라벨 & 입력 칸
        JLabel idLabel = new JLabel("Email");
        idLabel.setFont(new Font("굴림", Font.PLAIN, 12));
        idLabel.setBounds(50, 95, 60, 30);
        loginBackgroundPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 95, 230, 30);
        loginBackgroundPanel.add(idField);

        // 비밀번호 라벨 & 입력 칸 (보안을 위해 JPasswordField 사용)
        JLabel pwLabel = new JLabel("Password");
        pwLabel.setFont(new Font("굴림", Font.PLAIN, 11));
        pwLabel.setBounds(50, 145, 60, 30);
        loginBackgroundPanel.add(pwLabel);

        JPasswordField pwField = new JPasswordField();
        pwField.setBounds(120, 145, 230, 30);
        pwField.setDocument(new LimitDocument(20));
        loginBackgroundPanel.add(pwField);

        // 로그인 버튼
        JButton loginButton = LoginButton(idField, pwField, pwField);
        loginBackgroundPanel.add(loginButton);

        // 회원가입 버튼
        JButton signupButton = new GradientButton("SignUp");
        signupButton.setFont(new Font("굴림", Font.BOLD, 11));
        signupButton.setBounds(50, 260, 100, 20);
        signupButton.setBackground(new Color(0x002C7A));
        signupButton.setFocusPainted(false);
        signupButton.setForeground(new Color(255, 255, 255));
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpView signupView = new SignUpView();
                signupView.setVisible(true);

                dispose();
            }
        });

        loginBackgroundPanel.add(signupButton);

        // 비밀번호 까먹 버튼
        JButton forgotThePassword = new GradientButton("forgot the password?");
        forgotThePassword.setFont(new Font("굴림", Font.BOLD, 11));
        forgotThePassword.setBounds(170, 260, 180, 20);
        forgotThePassword.setBackground(new Color(0x002C7A));
        forgotThePassword.setForeground(new Color(255, 255, 255));
        forgotThePassword.setFocusPainted(false);
        loginBackgroundPanel.add(forgotThePassword);

        // ------------------------------------------

        // 조립
        panel.add(loginBackgroundPanel);
        this.add(panel);

        // 로고 이미지 설정
        java.net.URL imgUrl = getClass().getResource("PAMLogo.png");
        if (imgUrl != null) {
            this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgUrl));
        }

        this.setVisible(true);
    }

    public JButton LoginButton(JTextField idField, JPasswordField pwField, JTextField pwField2) {
        JButton loginButton = new GradientButton("Login");
        loginButton.setFont(new Font("굴림", Font.BOLD, 20));
        loginButton.setBounds(50, 210, 300, 40);
        loginButton.setBackground(new Color(0x002C7A)); // 버튼 배경색 (예시: 노란색 계열)
        loginButton.setFocusPainted(false); // 버튼 클릭 시 테두리 점선 제거
        loginButton.setForeground(new Color(255,255,255));
        loginButton.addActionListener(new ActionListener() {

            final UserDAO userDAO = new UserDAO();

            @Override
            public void actionPerformed(ActionEvent e) {
                String email = idField.getText().trim();
                String Password = pwField2.getText().trim();

                char[] id = idField.getText().toCharArray();
                char[] password = pwField.getPassword();

                if (password.length == 0 || id.length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    return;
                } else {
                    if (userDAO.isValidLogin(email, Password)) {
                        String nickname = userDAO.getNickname(email);

                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new MainDashboard(nickname).setVisible(true);
                        dispose();
                        return;
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid email or password!");
                        if (!userDAO.existByEmail(email)) {
                            idField.requestFocus();
                        } else {
                            pwField.requestFocus();
                        }
                    }
                }
            }
        });
        return loginButton;
    }
}
