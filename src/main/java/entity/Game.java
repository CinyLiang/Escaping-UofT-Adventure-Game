public class Game {
    private Player player;
    private boolean isRunning;
    private int totalPuzzles;
    private String saveFilePath;
    private List<Puzzle> puzzles;

    public Game(Player player, List<Puzzle> puzzles) {
        this.player = player;
        this.puzzles = puzzles;
        this.totalPuzzles = puzzles.size();
//        this.isRunning = false;
//        this.saveFilePath = "savegame.dat";
        createLocationMap();
    }

    public void clearHistory() {}
    public String viewProgress() {}
    public void startGame() {


    }
    public void initializeWorld() {}
    public boolean saveGame() {}
    public boolean loadGame(String filePath) {}
    public void quitGame() {}
    public boolean isGameComplete() {}
    public void processCommand(String command) {}


}
