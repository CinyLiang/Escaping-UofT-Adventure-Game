package app;

import data_access.CardGameDataAccessObject;
import data_access.FileGameDataAccessObject;
import data_access.OpenTriviaAPI;
import entity.Location;
import entity.Player;
import entity.TriviaPuzzle;
import entity.WinCondition;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.card_game_hints.CardGameHintsController;
import interfaceadapter.card_game_hints.CardGameHintsPresenter;
import interfaceadapter.clear_history.ClearHistoryController;
import interfaceadapter.clear_history.ClearHistoryPresenter;
import interfaceadapter.navigate.NavigateController;
import interfaceadapter.navigate.NavigatePresenter;
import interfaceadapter.navigate.NavigateViewModel;
import interfaceadapter.play_card_game.CardGameController;
import interfaceadapter.play_card_game.CardGamePresenter;
import interfaceadapter.play_card_game.CardGameViewModel;
import interfaceadapter.quit_game.QuitGameController;
import interfaceadapter.return_from_card.ReturnFromCardController;
import interfaceadapter.return_from_card.ReturnFromCardPresenter;
import interfaceadapter.trivia_game.TriviaGameController;
import interfaceadapter.trivia_game.TriviaGamePresenter;
import interfaceadapter.trivia_game.TriviaGameViewModel;
import interfaceadapter.validate_card_answer.ValidateCardController;
import interfaceadapter.validate_card_answer.ValidateCardPresenter;
import interfaceadapter.win_game.WinGameController;
import interfaceadapter.win_game.WinGamePresenter;
import interfaceadapter.win_game.WinGameViewModel;
import interfaceadapter.settings.SettingsViewModel;
import interfaceadapter.settings.SettingsController;
import interfaceadapter.settings.SettingsPresenter;

import use_case.card_game_hints.CardGameHintsInputDataBoundary;
import use_case.card_game_hints.CardGameHintsInteractor;
import use_case.card_game_hints.CardGameHintsOutputBoundary;
import use_case.card_return_to_home.CardReturnInputBoundary;
import use_case.card_return_to_home.CardReturnInteractor;
import use_case.card_return_to_home.CardReturnOutputBoundary;
import use_case.clear_history.ClearHistoryInputBoundary;
import interfaceadapter.clear_history.ClearHistoryViewModel;
import use_case.clear_history.ClearHistoryInteractor;
import use_case.clear_history.ClearHistoryOutputBoundary;
import use_case.navigate.NavigateInputBoundary;
import use_case.navigate.NavigateInteractor;
import use_case.navigate.NavigateOutputBoundary;
import use_case.play_card_game.PlayCardGameInputBoundary;
import use_case.play_card_game.PlayCardGameInteractor;
import use_case.play_card_game.PlayCardGameOutputBoundary;
import use_case.settings.SettingsInputBoundary;
import use_case.trivia_game.TriviaGameInputBoundary;
import use_case.trivia_game.TriviaGameInteractor;
import use_case.trivia_game.TriviaGameOutputBoundary;
import use_case.validate_card_answer.ValidateCardAnswerInputBoundary;
import use_case.validate_card_answer.ValidateCardAnswerInteractor;
import use_case.validate_card_answer.ValidateCardAnswerOutputBoundary;
import use_case.win_game.WinGameInputBoundary;
import use_case.win_game.WinGameInteractor;
import use_case.win_game.WinGameOutputBoundary;
import use_case.settings.SettingsOutputBoundary;
import use_case.settings.SettingsInteractor;

import view.*;
import view.SettingsView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Save Progress imports
import interfaceadapter.save_progress.SaveProgressController;
import interfaceadapter.save_progress.SaveProgressPresenter;
import use_case.save_progress.SaveProgressInputBoundary;
import use_case.save_progress.SaveProgressInteractor;
import use_case.save_progress.SaveProgressOutputBoundary;

// View Progress imports
import interfaceadapter.view_progress.ViewProgressController;
import interfaceadapter.view_progress.ViewProgressPresenter;
import interfaceadapter.view_progress.ViewProgressViewModel;

import use_case.view_progress.ViewProgressInputBoundary;
import use_case.view_progress.ViewProgressInteractor;
import use_case.view_progress.ViewProgressOutputBoundary;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    public final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // DAO
    private FileGameDataAccessObject fileGameDataAccessObject;

    // ViewModels
    private NavigateViewModel navigateViewModel;
    private ClearHistoryViewModel clearHistoryViewModel;
    private ViewProgressViewModel viewProgressViewModel;
    private WinGameViewModel winGameViewModel;
    private CardGameViewModel cardGameViewModel;
    private TriviaGameViewModel triviaGameViewModel;
    private SettingsViewModel settingsViewModel;

    // Views
    private HomeView homeView;
    private NavigateView navigateView;
    private InstructionsView instructionsView;
    private CardGameView cardGameView;
    private TriviaGameView triviaGameView;
    private WinGameView winGameView;
    private SaveGameDialog saveGameDialog;
    private QuitGameDialog quitGameDialog;
    private ReturnFromCardDialogue returnFromCardDialogue;
    private ConfirmRestartGameDialog confirmRestartGameDialog;
    private SettingsView settingsView;

    // oh god the player. OH GOD THE PLAYER
    Player player;

    // Interactor ?
    private NavigateInteractor navigateInteractor;

    // Track screen
    private String initialViewName = null;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);

        Location startLocation = new Location(
                "Kings College Circle",
                "The central lawn of the university.",
                false,
                "center",
                null
        );
        player = new Player(startLocation);
    }

    public AppBuilder addView(JPanel view, String name) {
        cardPanel.add(view, name);

        // first view becomes initial view
        if (initialViewName == null) {
            initialViewName = name;
        }
        return this;
    }

    // Navigate Use Case
    public AppBuilder addNavigateUseCase() {
        NavigateOutputBoundary presenter = new NavigatePresenter(
                navigateViewModel,
                viewManagerModel,
                winGameViewModel,
                cardGameViewModel,
                triviaGameViewModel
        );
        NavigateInputBoundary interactor = new NavigateInteractor(presenter);
        NavigateController controller = new NavigateController(interactor);

        navigateView.setNavigateController(controller);
        return this;
    }

    // Clear History Use Case
    // i am... not entirely sure why there is a view model but go off ig
    public AppBuilder addClearHistoryUseCase() {
        ClearHistoryOutputBoundary presenter = new ClearHistoryPresenter(navigateViewModel);
        ClearHistoryInputBoundary interactor = new ClearHistoryInteractor(presenter);
        ClearHistoryController controller = new ClearHistoryController(interactor);
        controller.setShowConfirmDialog(() -> confirmRestartGameDialog.show());

        navigateView.setClearHistoryController(controller);
        confirmRestartGameDialog = new ConfirmRestartGameDialog(controller);
        return this;
    }

    // View Progress Use Case
    public AppBuilder addViewProgressUseCase() {
        viewProgressViewModel = new ViewProgressViewModel();
        ViewProgressOutputBoundary presenter = new ViewProgressPresenter(navigateViewModel);
        ViewProgressInputBoundary interactor = new ViewProgressInteractor(presenter);
        // ngl i find this really dubious but fuck it i will FIGURE IT OUT
        ViewProgressController controller = new ViewProgressController(interactor);

        navigateView.setViewProgressController(controller);
        return this;
    }

    // Save Progress Use Case
    public AppBuilder addSaveProgressUseCase() {
        SaveProgressOutputBoundary presenter = new SaveProgressPresenter();
        SaveProgressInputBoundary interactor = new SaveProgressInteractor(fileGameDataAccessObject, presenter);
        SaveProgressController saveController = new SaveProgressController(interactor);
        navigateView.setSaveProgressController(saveController);

        // Also the quit thing as well
        QuitGameController quitController = new QuitGameController();
        quitGameDialog = new QuitGameDialog(quitController, saveController, navigateViewModel);

        // and save game
        saveGameDialog = new SaveGameDialog(saveController, navigateViewModel);

        // and navigate view
        navigateView.setQuitGameController(quitController, saveController);

        return this;
    }

    // Card Game Use Case
    public AppBuilder addCardGameUseCase() {
        // Main game
        PlayCardGameOutputBoundary cardPresenter = new CardGamePresenter(cardGameViewModel, viewManagerModel);
        PlayCardGameInputBoundary cardInteractor = new PlayCardGameInteractor(new CardGameDataAccessObject(), cardPresenter);
        CardGameController cardController = new CardGameController(cardInteractor);
        cardGameView.setCardGameController(cardController);

        // Hints
        CardGameHintsOutputBoundary hintsPresenter = new CardGameHintsPresenter(cardGameViewModel, viewManagerModel);
        CardGameHintsInputDataBoundary hintsInteractor = new CardGameHintsInteractor(hintsPresenter);
        CardGameHintsController hintsController = new CardGameHintsController(hintsInteractor);
        cardGameView.setCardGameHintsController(hintsController);

        // Validate
        ValidateCardAnswerOutputBoundary validatePresenter = new ValidateCardPresenter(cardGameViewModel);
        ValidateCardAnswerInputBoundary validateInteractor = new ValidateCardAnswerInteractor(validatePresenter);
        ValidateCardController validateController = new ValidateCardController(validateInteractor);
        cardGameView.setValidateCardController(validateController);

        // Return
        CardReturnOutputBoundary returnPresenter = new ReturnFromCardPresenter(viewManagerModel, navigateViewModel, cardGameViewModel);
        CardReturnInputBoundary returnInteractor = new CardReturnInteractor(returnPresenter);
        ReturnFromCardController returnController = new ReturnFromCardController(returnInteractor);
        returnFromCardDialogue = new ReturnFromCardDialogue(returnController);
        cardGameView.setReturnFromCardController(returnController);
        cardGameView.setReturnFromCardDialogue(returnFromCardDialogue);

        addView(cardGameView, cardGameViewModel.getViewName());
        return this;
    }

    // Trivia Use Case
    public AppBuilder addTriviaGameUseCase() {
        TriviaGameOutputBoundary presenter = new TriviaGamePresenter(triviaGameViewModel, navigateViewModel, viewManagerModel);
        // ok IDK NUMBER OF CORRECT ANSWERS REQUIRED SO SKDJFHSLKDFKJ yea no shit
        // also why is the puzzle entity passed sdkjfhskdfhdskf aaaaaaaaaaaaaaa
        TriviaGameInputBoundary interactor = new TriviaGameInteractor(new OpenTriviaAPI(), presenter, new TriviaPuzzle(3));
        // trivia game dao is opentriviaapi i believe???
        TriviaGameController controller = new TriviaGameController(interactor);
        triviaGameView.setController(controller);

        addView(triviaGameView, triviaGameViewModel.getViewName());
        return this;
    }

    // Win Game Use Case
    public AppBuilder addWinGameUseCase() {
        WinGameOutputBoundary presenter = new WinGamePresenter(winGameViewModel, viewManagerModel);
        WinGameInputBoundary interactor = new WinGameInteractor(presenter, new WinCondition(2));
        WinGameController controller = new WinGameController(interactor);
        navigateView.setWinGameController(controller);

        winGameView.setViewManagerModel(viewManagerModel);

        addView(winGameView, winGameViewModel.getViewName());
        return this;
    }

    public JFrame build(String filepath) throws IOException, FontFormatException {
        // DAO
        fileGameDataAccessObject = new FileGameDataAccessObject(filepath);

        // ViewModels
        navigateViewModel = new NavigateViewModel();
        clearHistoryViewModel = new ClearHistoryViewModel();
        cardGameViewModel = new CardGameViewModel();
        triviaGameViewModel = new TriviaGameViewModel();
        winGameViewModel = new WinGameViewModel();
        viewProgressViewModel = new ViewProgressViewModel();
        settingsViewModel = new SettingsViewModel();

        // Create Views
        homeView = new HomeView(viewManagerModel);
        navigateView = new NavigateView(navigateViewModel);
        instructionsView = new InstructionsView(viewManagerModel); // i have. no idea if this exists and how it's implemented but go off
        cardGameView = new CardGameView(cardGameViewModel);
        triviaGameView = new TriviaGameView(triviaGameViewModel);
        winGameView = new WinGameView(winGameViewModel);

        // Set VM
        navigateView.setClearHistoryViewModel(clearHistoryViewModel);

        // Register views
        // switch the name to something from view model because consistency and also. what.
        addView(homeView, HomeView.VIEW_NAME);
        addView(navigateView, NavigateView.VIEW_NAME);
        addView(instructionsView, InstructionsView.VIEW_NAME);

        // Settings
        SettingsOutputBoundary settingsPresenter =
                new SettingsPresenter(settingsViewModel, viewManagerModel);
        SettingsInputBoundary settingsInteractor =
                new SettingsInteractor(settingsPresenter);
        SettingsController settingsController =
                new SettingsController(settingsInteractor);
        settingsView = new SettingsView(settingsController, settingsViewModel, viewManagerModel);
        addView(settingsView, SettingsView.VIEW_NAME);

        // Add use cases
        addClearHistoryUseCase();
        addCardGameUseCase();
        addTriviaGameUseCase();
        addWinGameUseCase();
        addSaveProgressUseCase();
        addViewProgressUseCase();
        addNavigateUseCase();

        // Build window
        JFrame window = new JFrame("UofT Adventure Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(900, 650);
        window.setResizable(false);

        window.add(viewManager.getCardPanel());

        // Show initial view
        viewManagerModel.setState(initialViewName);
        viewManagerModel.firePropertyChange();

        window.setVisible(true);
        return window;
    }
}