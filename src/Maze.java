import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Maze{

    private int[][] maze;
    private int cols;
    private int rows;

    JFrame frame = new JFrame("MAZE GENERATOR");

    private boolean[][] UP;
    private boolean[][] DOWN;
    private boolean[][] LEFT;
    private boolean[][] RIGHT;

    public Maze(){}

    public void setMaze(int rows, int cols) {

        this.cols = rows;
        this.rows = cols;

        maze = new int[rows + 2][cols + 2];

        for(int i = 0; i < cols + 2; i++){
            maze[rows + 1][i] = 1;
            maze[0][i] = 1;
        }
        for(int i = 0; i < rows + 2; i++){
            maze[i][cols + 1] = 1;
            maze[i][0] = 1;
        }

        UP = new boolean[rows + 2][cols + 2];
        DOWN = new boolean[rows + 2][cols + 2];
        LEFT = new boolean[rows + 2][cols + 2];
        RIGHT = new boolean[rows + 2][cols + 2];

        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                UP[i][j] = true;
                DOWN[i][j] = true;
                LEFT[i][j] = true;
                RIGHT[i][j] = true;
            }
        }
    }

    boolean isWall(int col, int row){
        return col <= 0 || row <= 0 || col >= this.cols + 1 || row >= rows + 1;
    }

    public void createMaze(int col, int row) {

        if (isWall(col, row)) {
            return;
        }

        maze[col][row] = 1;
        Random random = new Random();

        while (maze[col][row + 1] == 0 || maze[col][row - 1] == 0
                || maze[col + 1][row] == 0 || maze[col - 1][row] == 0) {
            while (true) {
                int neighbour = random.nextInt(4);
                if (neighbour == 0) {
                    if(maze[col][row + 1] == 0) {
                        UP[col][row] = false;
                        DOWN[col][row + 1] = false;
                        maze[col][row + 1] = 1;
                        createMaze(col, row + 1);
                        break;
                    }
                } else if (neighbour == 2) {
                    if(maze[col][row - 1] == 0) {
                        DOWN[col][row] = false;
                        UP[col][row - 1] = false;
                        maze[col][row - 1] = 1;
                        createMaze(col, row - 1);
                        break;
                    }
                } else if (neighbour == 1) {
                    if(maze[col + 1][row] == 0) {
                        RIGHT[col][row] = false;
                        LEFT[col + 1][row] = false;
                        maze[col + 1][row] = 1;
                        createMaze(col + 1, row);
                        break;
                    }
                } else {
                    if(maze[col - 1][row] == 0) {
                        LEFT[col][row] = false;
                        RIGHT[col - 1][row] = false;
                        maze[col - 1][row] = 1;
                        createMaze(col - 1, row);
                        break;
                    }
                }
            }
        }
    }

    public void visualiseMaze(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(cols * 60, rows * 60);
        frame.setBackground(Color.lightGray);

        createMaze(1, 1);

        for(int i = -1; i < rows + 2; i++){
            frame.add(new Wall(cols + 1, i));
            frame.setVisible(true);
            frame.add(new Wall(0, i));
            frame.setVisible(true);
        }

        for(int i = -1; i < cols + 2; i++){
            frame.add(new Wall(i, rows + 1));
            frame.setVisible(true);
            frame.add(new Wall(i, 0));
            frame.setVisible(true);
        }

        for (int i = 1; i <= cols; i++) {
            for (int j = 1; j <= rows; j++) {
                if (UP[i][j]) {
                    frame.add(new Line(i, j + 1, i + 1, j + 1));
                    frame.setVisible(true);
                }
                if (DOWN[i][j]) {
                    frame.add(new Line(i, j, i + 1, j));
                    frame.setVisible(true);
                }
                if (LEFT[i][j]){
                    frame.add(new Line(i, j, i, j + 1));
                    frame.setVisible(true);
                }
                if (RIGHT[i][j]){
                    frame.add(new Line(i + 1, j, i + 1, j + 1));
                    frame.setVisible(true);
                }
            }
        }
    }

    public void askForMazeSize(){
        CustomerInput input = new CustomerInput();
        String cols = JOptionPane.showInputDialog(input, "Input the number of maze cols: ");
        int customCols = Integer.parseInt(cols);
        String rows = JOptionPane.showInputDialog(input, "Input the number of maze rows: ");
        int customRows = Integer.parseInt(rows);
        if(!cols.isEmpty() && !rows.isEmpty()){
            input.closeWindow();
        }
        this.cols = customCols;
        this.rows = customRows;
    }

    public static void main(String[] args){
        Maze maze = new Maze();
        maze.askForMazeSize();
        maze.setMaze(maze.cols, maze.rows);
        maze.visualiseMaze();
    }
}
