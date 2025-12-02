package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.theme.ThemeManager;

public class OptionSelector extends JPanel {

    private final JLabel label;
    private final JLabel leftArrow;
    private final JLabel rightArrow;
    private final JLabel valueLabel;

    private final String[] values;
    private int index;

    public OptionSelector(String labelText, String[] values, int initialIndex) {
        this.values = values;
        this.index = initialIndex;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 45));

        // THEME BACKGROUND + BORDER
        setBackground(ThemeManager.getPanelBackground());
        setBorder(BorderFactory.createMatteBorder(
                1, 0, 1, 0,
                ThemeManager.getTextPrimary()
        ));

        label = new JLabel(labelText);
        label.setForeground(ThemeManager.getTextPrimary());
        label.setFont(new Font("Consolas", Font.BOLD, ThemeManager.getFontSize(20)));
        label.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        leftArrow = new JLabel("<  ");
        rightArrow = new JLabel("  >");

        styleArrow(leftArrow);
        styleArrow(rightArrow);

        valueLabel = new JLabel(values[index], SwingConstants.CENTER);
        valueLabel.setForeground(ThemeManager.getTextPrimary());
        valueLabel.setFont(new Font("Consolas", Font.BOLD, ThemeManager.getFontSize(20)));

        JPanel rightBox = new JPanel(new BorderLayout());
        rightBox.setOpaque(false);

        rightBox.add(leftArrow, BorderLayout.WEST);
        rightBox.add(valueLabel, BorderLayout.CENTER);
        rightBox.add(rightArrow, BorderLayout.EAST);

        add(label, BorderLayout.WEST);
        add(rightBox, BorderLayout.EAST);

        leftArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = (index - 1 + values.length) % values.length;
                valueLabel.setText(values[index]);
            }
        });

        rightArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                index = (index + 1) % values.length;
                valueLabel.setText(values[index]);
            }
        });

        MouseAdapter hover = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Color red = ThemeManager.getTextPrimary();
                leftArrow.setForeground(red);
                rightArrow.setForeground(red);
                valueLabel.setForeground(red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Color normal = ThemeManager.getTextPrimary();
                leftArrow.setForeground(normal);
                rightArrow.setForeground(normal);
                valueLabel.setForeground(normal);
            }
        };

        leftArrow.addMouseListener(hover);
        rightArrow.addMouseListener(hover);
        valueLabel.addMouseListener(hover);
    }

    private void styleArrow(JLabel arrow) {
        arrow.setForeground(ThemeManager.getTextPrimary());
        arrow.setFont(new Font("Consolas", Font.BOLD, ThemeManager.getFontSize(20)));
        arrow.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public String getValue() {
        return values[index];
    }
}
