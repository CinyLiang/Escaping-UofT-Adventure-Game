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
    private JLabel imageLabel;

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

        // make a scroll pane to keep everythign in view
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(parchmentBackground);

        // image
        ImageIcon originalImage = new ImageIcon("/Users/vanessa.hanbao/Downloads/Gemini_Generated_Image_1lcv901lcv901lcv.png");
        int newWidth = 400;
        int newHeight = (originalImage.getIconHeight() * newWidth) / originalImage.getIconWidth();
        Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(20));

        // instructions
        JTextArea text = new JTextArea(
                "The night before convocation, you find yourself at King's College Circle." +
                        "The heavy oak doors of Convocation Hall automatically deadbolt. They cannot be" +
                        " forced open. Entry is barred to all save the bearer of the two lost keys.... \n \n" +
                        "You are a student who accidentally got locked inside King's College Circle after "
                        + "dark. To escape the grounds, you must trigger the release mechanism " +
                        "inside Convocation Hall—but first, you have to get in."
        );
        text.setEditable(false);
        text.setFont(texturina);
        text.setBackground(parchmentBackground);
        text.setForeground(darkMapText);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accentColor, 3),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JPanel textWrapper = new JPanel(new BorderLayout());
        textWrapper.setBackground(parchmentBackground);
        textWrapper.add(text, BorderLayout.CENTER);

        mainPanel.add(textWrapper);
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // prevent horizontal scroll bar from appearing
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        gbc.gridy++;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(text);

        // wrap mainPanel into JScrollPane
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // main scroll pane to primary view
        gbc.gridy++;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(mainScrollPane, gbc);

        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;

        // start button
        JButton startButton = new JButton("Start Game");
        startButton.setPreferredSize(new Dimension(100, 25));
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
        gbc.insets = new Insets(8, 15, 8, 15);
        this.add(startButton, gbc);
    }

    public String getViewName() {
        return VIEW_NAME;
    }
}
