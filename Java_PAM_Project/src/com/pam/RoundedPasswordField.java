package com.pam;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoundedPasswordField extends JPasswordField {
    private final int radius = 16;
    private String emptyMessage = "";

    public RoundedPasswordField(int columns) {
        super(columns);

        setOpaque(false);
        setBorder(new EmptyBorder(0, 16, 0, 16));
        setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        setMargin(new Insets(0, 12, 0, 12));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                repaint();
            }
        });

        getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                repaint();
            }

            public void removeUpdate(DocumentEvent e) {
                repaint();
            }

            public void changedUpdate(DocumentEvent e) {
                repaint();
            }
        });
    }

    public void setEmptyMessage(String message) {
        emptyMessage = message;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(new Color(0xEDEDED));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.setColor(hasFocus()
                ? new Color(50, 80, 125)
                : new Color(0xEDEDED));

        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
        g2.dispose();

        super.paintComponent(g);

        if (getPassword().length == 0 && !hasFocus() && !emptyMessage.isEmpty()) {
            Graphics2D placeholderG = (Graphics2D) g.create();
            placeholderG.setColor(Color.DARK_GRAY);
            placeholderG.setFont(getFont());

            FontMetrics fm = placeholderG.getFontMetrics();
            int x = getMargin() .left + 8;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();

            placeholderG.drawString(emptyMessage, x, y);
            placeholderG.dispose();
        }
    }

    protected void paintBorder(Graphics g) {

    }
}


