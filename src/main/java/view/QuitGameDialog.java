package view;

import java.awt.*;
import javax.swing.*;

import interface_adapter.navigate.NavigateViewModel;
import interface_adapter.quit_game.QuitGameController;
import interface_adapter.save_progress.SaveProgressController;

public class QuitGameDialog extends JDialog {

    private QuitGameController quitGameController;

    public QuitGameDialog(QuitGameController quitGameController,
                          SaveProgressController saveProgressController, NavigateViewModel navigateViewModel) {

        this.quitGameController = quitGameController;
        this.quitGameController.setShowSaveDialog(() -> {
            SaveGameDialog saveDialog = new SaveGameDialog(saveProgressController, navigateViewModel);
            saveDialog.show();
        });

        setTitle("Quit Game?");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Quit Game?");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttons = new JPanel();
        JButton quit = new JButton("Quit");
        JButton cancel = new JButton("Cancel");

        quit.addActionListener(e -> {
            dispose();
            quitGameController.showSave();
        });

        cancel.addActionListener(e -> dispose());

        buttons.add(quit);
        buttons.add(cancel);

        add(title, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public void showDialog() {
        setVisible(true);
    }
}

