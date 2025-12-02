package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import interface_adapter.ViewManagerModel;

public class InstructionsView extends JPanel {

    public static final String VIEW_NAME = "instructions";
    private JLabel imageLabel;

    public InstructionsView(ViewManagerModel viewManagerModel) {
        this.setLayout(new GridBagLayout());


        Font quintessential = UISettings.quintessential;
        Font texturina = UISettings.texturina;

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


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(parchmentBackground);


        if (UISettings.instructionsImage != null && UISettings.instructionsImage.getImage() != null) {
            ImageIcon originalImage = UISettings.instructionsImage;
            int newWidth = 400;
            int newHeight = (originalImage.getIconHeight() * newWidth) / originalImage.getIconWidth();
            Image scaledImage = originalImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            imageLabel = new JLabel(scaledIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(imageLabel);
            mainPanel.add(Box.createVerticalStrut(20));
        } else {
            JLabel placeholder = new JLabel("[Image Not Found]");
            placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            placeholder.setFont(texturina);
            placeholder.setForeground(accentColor);
            mainPanel.add(placeholder);
            mainPanel.add(Box.createVerticalStrut(20));
        }

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
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
                startButton.setBackground(hoverColor);
                startButton.setForeground(accentColor);
                startButton.repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
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