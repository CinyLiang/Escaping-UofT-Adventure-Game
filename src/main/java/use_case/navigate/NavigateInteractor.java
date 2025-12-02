package use_case.navigate;

import org.jetbrains.annotations.NotNull;

public class NavigateInteractor implements NavigateInputBoundary {

    private final NavigateOutputBoundary navigatePresenter;

    public NavigateInteractor(NavigateOutputBoundary navigatePresenter) {
        this.navigatePresenter = navigatePresenter;
    }

    /*
     * Takes a direction and returns updated story
     */
//    @Override
//    public void execute(NavigateInputData inputData) {
//        String direction = inputData.getDirection();
//        navigatePresenter.prepareSuccessView(new NavigateOutputData2(getTargetView(direction)));
//    }

    @Override
    public void execute(NavigateInputData inputData) {
        String direction = inputData.getDirection();
        String target = getTargetView(direction);
        System.out.println("[NavigateInteractor] execute called. direction=\"" + direction + "\", resolvedTarget=\"" + target + "\"");
        navigatePresenter.prepareSuccessView(new NavigateOutputData2(target));
    }
    @NotNull
    private static String getTargetView(String direction) {
        return switch (direction.toLowerCase()) {
            case "north" -> "UC exterior"; // UC exterior > interior > card game
            case "south" -> "Win game";
            case "east" -> "Gerstein exterior"; // gerstein exterior > interior > trivia game
            case "west" -> "Knox exterior"; // knox exterior > interior > card game
            case "card game" -> "Card game";
            case "knox interior" -> "Knox interior";
            case "gerstein interior" -> "Gerstein interior";
            case "uc interior" -> "UC interior";
            case "trivia game" -> "Trivia game";
            default -> "";
        };
    }
}
