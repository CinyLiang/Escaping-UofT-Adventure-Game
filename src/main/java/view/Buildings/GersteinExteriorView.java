package view.Buildings;

import javax.swing.*;

import interface_adapter.navigate.Buildings.Gerstein.GersteinExterior.GersteinExtViewModel;
import interface_adapter.navigate.NavigateController;
import resources.UISettings;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GersteinExteriorView extends JPanel {
    public static final String VIEW_NAME = "gerstein_exterior_view";

    // IMAGES + FONT
    private final JLabel gersteinExteriorLabel;

    // CONTROLLERS
    private NavigateController navigateController;

    // VIEW MODEL
    private GersteinExtViewModel gersteinExtViewModel;

    // DIALOGS

    // NAV UI
    private JTextArea storyArea;
    private JButton continueButton;

    public GersteinExteriorView(GersteinExtViewModel gersteinExtViewModel) throws IOException, FontFormatException {
        this.gersteinExtViewModel = gersteinExtViewModel;
//        setNavigateController();
        this.setLayout(new BorderLayout());
        this.setBackground(UISettings.PARCHMENT_BACKGROUND);

        // insert header image

        storyArea = new JTextArea("A biting, late-autumn wind wraps around the broad, stone façade of " +
                "the Gerstein Library. The building looms before you, a formidable structure built on " +
                "layers of architectural history. Unlike the Gothic towers of other colleges, Gerstein's " +
                "entrance is solid, imposing, and vaguely industrial, a temple dedicated to sheer knowledge. " +
                "A faint, dry smell—not of old books, but of chemicals and burnt parchment, hinting at illicit " +
                "or forgotten research—drifts from the vents near the entrance.\n\n" + "You reach out " +
                "and touch the cold bronze railing. It's vibrating slightly, resonating with that " +
                "unseen rhythm, a slow, relentless pulse of arcane scholarship.\n\n"+
                "Do you dare enter the archive?");

        // status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        statusBar.setBackground(UISettings.PARCHMENT_BACKGROUND);
        statusBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, UISettings.ACCENT_COLOR));

        this.add(statusBar, BorderLayout.NORTH);

        // scroll pane containing center content
        // map, story, and selector all in here
        JPanel scrollableContent = new JPanel();
        scrollableContent.setLayout(new BoxLayout(scrollableContent, BoxLayout.Y_AXIS));
        scrollableContent.setBackground(UISettings.PARCHMENT_BACKGROUND);
        scrollableContent.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Overall padding

        // knox exterior image
        ImageIcon gersteinExt = UISettings.gersteinExtImage;
        int newWidth = 500;
        int newHeight = (gersteinExt.getIconHeight() * newWidth) / gersteinExt.getIconWidth();
        Image scaledGersteinExt = gersteinExt.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledGersteinExtIcon = new ImageIcon(scaledGersteinExt);

        gersteinExteriorLabel = new JLabel(scaledGersteinExtIcon);
        gersteinExteriorLabel.setHorizontalAlignment(JLabel.CENTER);
        gersteinExteriorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollableContent.add(gersteinExteriorLabel);
        scrollableContent.add(Box.createVerticalStrut(25));

        // storyArea
        // note: moved story text out of its own scroll pane to the main scroll pane
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setBackground(UISettings.PARCHMENT_BACKGROUND);
        storyArea.setForeground(UISettings.DARK_MAP_TEXT);
        storyArea.setFont(UISettings.texturina);

        scrollableContent.add(storyArea);

        // Wrap the scrollableContent panel in the main JScrollPane
        JScrollPane mainScrollPane = new JScrollPane(scrollableContent);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(mainScrollPane, BorderLayout.CENTER);

        // Bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setBackground(UISettings.ACCENT_COLOR);

        // Continue button
        continueButton = makeButton("Continue inside Gerstein Library");
        bottomPanel.add(continueButton);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // action listeners (button + dropdown logic)
        continueButton.addActionListener(e -> {
            if (navigateController != null) {
                System.out.println("Move from gerstein exterior to knox interior");
                navigateController.execute("Gerstein Interior");
            }
        });

    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(UISettings.PARCHMENT_BACKGROUND);
        b.setForeground(UISettings.ACCENT_COLOR);
        b.setFont(UISettings.quintessential.deriveFont(Font.PLAIN, 18));
        b.setFocusPainted(false);
        b.setOpaque(true);

        //border
        b.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UISettings.ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // hover effect
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                b.setBackground(UISettings.HOVER_COLOR);
                b.setForeground(UISettings.DARK_MAP_TEXT);
                b.repaint();
            }

            public void mouseExited(MouseEvent evt) {
                b.setBackground(UISettings.PARCHMENT_BACKGROUND);
                b.setForeground(UISettings.ACCENT_COLOR);
                b.repaint();
            }
        });
        return b;
    }

    // ACTION LISTENERS
    public void setNavigateController(NavigateController navigateController) {
        this.navigateController = navigateController;
    }

}