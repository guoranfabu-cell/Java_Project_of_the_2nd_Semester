/** start button Gradients Offset */

package com.pam;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    private final Color[] colors = {new Color(0x002C7A), new Color(0x03509C), new Color(0x3472AF)};
    private final float[] dist = {0.0f, 0.5f, 1.0f};

    private final int shadowOffset = 4;
    private final Color shadowColor = new Color(0, 0, 0, 40);

    public GradientButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
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

        LinearGradientPaint p = new LinearGradientPaint(
                0, 0, btnW, btnH,
                dist, colors,
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