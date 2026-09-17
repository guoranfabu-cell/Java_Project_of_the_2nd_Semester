/** start button Gradients Offset */

package com.pam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GradientButton extends JButton {
    private final Color[] colors = {new Color(0x002C7A), new Color(0x03509C), new Color(0x3472AF)};
    private final Color[] mouseColor = {new Color(0x3B6DC5), new Color(0x4B89C5), new Color(0x8FB2D8)};
    private final float[] dist = {0.0f, 0.5f, 1.0f};
    private final boolean[] isMouseOnTop = {false};

    private final int shadowOffset = 4;
    private final Color shadowColor = new Color(0, 0, 0, 40);

    public GradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOnTop[0] = true;
                repaint(); // 버튼을 다시 그림 (paintComponent 호출)
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOnTop[0] = false;
                repaint(); // 버튼을 다시 그림
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g2d.setColor(shadowColor);
        g2d.fillRoundRect(shadowOffset, shadowOffset, w - shadowOffset, h - shadowOffset, 12, 12);

        int btnW = w - shadowOffset;
        int btnH = h - shadowOffset;

        Color[] currentColors = isMouseOnTop[0] ? mouseColor : colors;

        LinearGradientPaint p = new LinearGradientPaint(
                0, 0, btnW, btnH,
                dist, currentColors, // 수정된 변수 적용
                MultipleGradientPaint.CycleMethod.NO_CYCLE
        );

        g2d.setPaint(p);
        g2d.fillRoundRect(0, 0, btnW, btnH, 12, 12);

        if (getText() != null && !getText().isEmpty()) {
            g2d.setColor(getForeground());
            g2d.setFont(getFont());

            FontMetrics metrics = g2d.getFontMetrics(getFont());
            int textX = (btnW - metrics.stringWidth(getText())) / 2;
            int textY = ((btnH - metrics.getHeight()) / 2) + metrics.getAscent();

            g2d.drawString(getText(), textX, textY);
        }

        g2d.dispose();
    }
}