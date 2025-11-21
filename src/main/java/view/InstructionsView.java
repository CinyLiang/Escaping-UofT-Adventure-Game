package view;

import javax.swing.*;
import java.awt.*;

public class InstructionsView extends JPanel {

    public static final String VIEW_NAME = "instructions";

    public InstructionsView() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);

        JLabel title = new JLabel("How to Play");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea text = new JTextArea(
                "Welcome to the Escaping UofT Game!\n\n" +
                        "- Make choices\n" +
                        "- Explore Buildings\n" +
                        "- Escape the campus\n\n" +
                        "Good luck!"
        );
        text.setEditable(false);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setBackground(Color.DARK_GRAY);
        text.setForeground(Color.WHITE);

        this.add(title, BorderLayout.NORTH);
        this.add(text, BorderLayout.CENTER);
    }
}
