package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import interface_adapter.ViewManagerModel;

public class InstructionsView extends JPanel {

    public static final String VIEW_NAME = "instructions";

    public InstructionsView(ViewManagerModel viewManagerModel) throws IOException, FontFormatException {
        this.setLayout(new GridBagLayout());


//        // font
        Font quintessentialBase = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/vanessa.hanbao/Downloads/Quintessential/Quintessential-Regular.ttf"));
        Font quintessential = quintessentialBase.deriveFont(Font.PLAIN, 24);

        Font texturinaBase = Font.createFont(Font.TRUETYPE_FONT, new File("/Users/vanessa.hanbao/Downloads/Quintessential,Texturina/Texturina/Texturina-VariableFont_opsz,wght.ttf"));
        Font texturina = texturinaBase.deriveFont(Font.PLAIN, 24);

        // colors to match map aesthetic
        final Color parchmentBackground = new Color(245, 235, 209);
        final Color darkMapText = new Color(40, 25, 15);
        final Color accentColor = new Color(110, 40, 40);
        final Color hoverColor = new Color(150, 75, 75);

        this.setBackground(parchmentBackground);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // title
        JLabel title = new JLabel("Escape UofT!");
        title.setForeground(accentColor);
        title.setFont(quintessential);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridy++;
        this.add(title, gbc);

        // instructions
        JTextArea text = new JTextArea(
                "Welcome to the Escaping UofT Adventure Game!\n\n" +
                        "You are trapped on campus and must find the optimal route to freedom.\n\n" +
                        "GUIDELINES:\n" +
                        "• Navigate between iconic UofT buildings.\n" +
                        "• Make critical choices to advance or face setbacks.\n" +
                        "• Manage your time and energy wisely.\n" +
                        "• Your goal is to reach the campus boundary undetected.\n\n" +
                        "Good luck, future escapee!"
        );
        text.setEditable(false);
        text.setFont(texturina);
        text.setBackground(parchmentBackground);
        text.setForeground(darkMapText);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 3),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        gbc.gridy++;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;

        // start button
        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(300, 70));
        startButton.setFont(quintessential);

        startButton.setBackground(parchmentBackground);
        startButton.setForeground(accentColor);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);

        // hover effect
        startButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                // Invert colors on hover
                startButton.setBackground(hoverColor);
                startButton.setForeground(accentColor);
                startButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                // Restore default colors
                startButton.setBackground(parchmentBackground);
                startButton.setForeground(accentColor);
                startButton.repaint();
            }
        });

        // border
        startButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 4),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // navigate view opens next
        startButton.addActionListener(e -> {
            viewManagerModel.setState("navigate_view");
            viewManagerModel.firePropertyChange();
        });

        gbc.gridy++;
        gbc.insets = new Insets(30, 15, 30, 15);
        this.add(startButton, gbc);
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}
