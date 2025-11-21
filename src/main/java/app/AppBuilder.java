package app;

import data_access.*;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.clear_history.*;
import interface_adapter.navigate.*;
import interface_adapter.play_card_game.*;
import interface_adapter.card_game_hints.*;
import interface_adapter.return_from_card.*;
import interface_adapter.validate_card_answer.*;
import interface_adapter.quit_game.QuitGameController;
import interface_adapter.save_progress.*;
import interface_adapter.view_progress.*;
import interface_adapter.trivia_game.*;
import interface_adapter.win_game.*;

import view.*;
import use_case.card_game_hints.*;
import use_case.card_return_to_home.*;
import use_case.clear_history.*;
import use_case.navigate.*;
import use_case.play_card_game.*;
import use_case.save_progress.*;
import use_case.validateCardAnswer.*;
import use_case.view_progress.*;
import use_case.trivia_game.*;
import use_case.win_game.*;

import javax.swing.*;
import java.awt.*;

public class AppBuilder {

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager =
            new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private NavigateViewModel navigateViewModel;
    private ClearHistoryViewModel clearHistoryViewModel;
    private ViewProgressViewModel viewProgressViewModel;
    private CardGameViewModel cardGameViewModel;
    private TriviaGameViewModel triviaGameViewModel;
    private WinGameViewModel winGameViewModel;

    private HomeView homeView;
    private NavigateView navigateView;
    private CardGameView cardGameView;
    private TriviaGameView triviaGameView;
    private WinGameView winGameView;

    private FileGameDataAccessObject fileDataAccess;

    private Player player;

    private NavigateInteractor navigateInteractor;

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
        if (initialViewName == null) {
            initialViewName = name;
        }
        return this;
    }

    private void addNavigateUseCase() {
        NavigatePresenter presenter = new NavigatePresenter(navigateViewModel);
        navigateInteractor = new NavigateInteractor(presenter, viewManagerModel);
        NavigateController controller = new NavigateController(navigateInteractor);
        navigateView.setNavigateController(controller);
        navigateView.setNavigateViewModel(navigateViewModel);
    }

    private void addClearHistoryUseCase() {
        ClearHistoryPresenter presenter = new ClearHistoryPresenter(clearHistoryViewModel);
        ClearHistoryInputBoundary interactor = new ClearHistoryInteractor(presenter);
        ClearHistoryController controller = new ClearHistoryController(interactor);

        ConfirmRestartGameDialog confirmDialog = new ConfirmRestartGameDialog(controller);
        controller.setShowConfirmDialog(() -> confirmDialog.show());

        navigateView.setClearHistoryController(controller);
        navigateView.setClearHistoryViewModel(clearHistoryViewModel);
    }

    private void addSaveProgressUseCase() {
        SaveProgressPresenter presenter = new SaveProgressPresenter();
        SaveProgressInputBoundary interactor = new SaveProgressInteractor(fileDataAccess, presenter);
        SaveProgressController controller = new SaveProgressController(interactor);
        navigateView.setSaveProgressController(controller);
    }

    private void addViewProgressUseCase() {
        viewProgressViewModel = new ViewProgressViewModel();
        ViewProgressPresenter presenter = new ViewProgressPresenter(viewProgressViewModel);
        ViewProgressInputBoundary interactor = new ViewProgressInteractor(fileDataAccess, presenter);
        ViewProgressController controller = new ViewProgressController(interactor);
        navigateView.setViewProgressController(controller);
    }

    private void addCardGameUseCase() {
        CardGameDataAccessObject cardDAO = new CardGameDataAccessObject();

        CardGamePresenter playPresenter = new CardGamePresenter(cardGameViewModel, viewManagerModel);
        PlayCardGameInputBoundary playInteractor = new PlayCardGameInteractor(cardDAO, playPresenter);
        CardGameController playController = new CardGameController(playInteractor);

        CardGameHintsPresenter hintsPresenter = new CardGameHintsPresenter(cardGameViewModel, viewManagerModel);
        CardGameHintsInteractor hintsInteractor = new CardGameHintsInteractor(hintsPresenter);
        CardGameHintsController hintsController = new CardGameHintsController(hintsInteractor);

        ValidateCardPresenter validatePresenter = new ValidateCardPresenter(cardGameViewModel);
        ValidateCardAnswerInteractor validateInteractor = new ValidateCardAnswerInteractor(validatePresenter);
        ValidateCardController validateController = new ValidateCardController(validateInteractor);

        ReturnFromCardPresenter returnPresenter = new ReturnFromCardPresenter(
                viewManagerModel, navigateViewModel, cardGameViewModel
        );
        CardReturnInteractor returnInteractor = new CardReturnInteractor(returnPresenter);
        ReturnFromCardController returnController = new ReturnFromCardController(returnInteractor);

        ReturnFromCardDialogue returnDialog = new ReturnFromCardDialogue(returnController);

        cardGameView = new CardGameView(
                playController, hintsController, validateController,
                returnController, returnDialog, cardGameViewModel, player
        );

        addView(cardGameView, "card game");
    }

    private void addTriviaGameUseCase() {
        OpenTriviaAPI triviaAPI = new OpenTriviaAPI();

        TriviaPuzzle triviaPuzzle = new TriviaPuzzle(3);

        TriviaGamePresenter presenter = new TriviaGamePresenter(triviaGameViewModel);
        TriviaGameInteractor interactor = new TriviaGameInteractor(triviaAPI, presenter, triviaPuzzle);
        TriviaGameController controller = new TriviaGameController(interactor);

        triviaGameView = new TriviaGameView(triviaGameViewModel, viewManagerModel);
        triviaGameView.setController(controller);

        addView(triviaGameView, "trivia game");
    }

    private void addWinGameUseCase() {
        WinCondition winCondition = new WinCondition(3);

        WinGamePresenter presenter = new WinGamePresenter(winGameViewModel, viewManagerModel);
        WinGameInteractor interactor = new WinGameInteractor(presenter, winCondition);
        WinGameController controller = new WinGameController(interactor);

        winGameView = new WinGameView(winGameViewModel, viewManagerModel);

        addView(winGameView, "win game");
    }

    public JFrame build(FileGameDataAccessObject dataAccess) {
        this.fileDataAccess = dataAccess;

        navigateViewModel = new NavigateViewModel();
        clearHistoryViewModel = new ClearHistoryViewModel();
        cardGameViewModel = new CardGameViewModel();
        triviaGameViewModel = new TriviaGameViewModel();
        winGameViewModel = new WinGameViewModel();

        homeView = new HomeView(viewManagerModel);
        navigateView = new NavigateView();

        addView(homeView, HomeView.VIEW_NAME);
        addView(navigateView, NavigateView.VIEW_NAME);

        addNavigateUseCase();
        addClearHistoryUseCase();
        addSaveProgressUseCase();
        addViewProgressUseCase();
        addCardGameUseCase();
        addTriviaGameUseCase();
        addWinGameUseCase();

        navigateInteractor.setCardGameView(cardGameView);
        navigateInteractor.setTriviaGameView(triviaGameView);

        SaveProgressPresenter savePresenter = new SaveProgressPresenter();
        SaveProgressInputBoundary saveInteractor = new SaveProgressInteractor(fileDataAccess, savePresenter);
        SaveProgressController saveController = new SaveProgressController(saveInteractor);

        QuitGameController quitController = new QuitGameController();

        navigateView.setQuitGameController(quitController, saveController);

        JFrame window = new JFrame("UofT Adventure Game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(900, 650);
        window.setResizable(false);

        window.add(cardPanel);

        viewManagerModel.setState(initialViewName);
        viewManagerModel.firePropertyChange();

        return window;
    }
}