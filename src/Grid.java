import javafx.scene.paint.Color;

public class Grid extends ConnectFour{

    //creates a 2d array of disks and sets turn
    public Grid() {
        grid = new Disk[7][6];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 6; j++) {
                grid[i][j] = new Disk(i, j);
            }
        }

        red = false;
    }

    //checks if a player has won or not
    public boolean checkForWin() {

        //check for a vertical win
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 3; j++) {

                if(grid[i][j].getCircle().getFill() != Color.WHITE) {
                    if(grid[i][j].getCircle().getFill() == grid[i][j+1].getCircle().getFill()
                            && grid[i][j+1].getCircle().getFill() == grid[i][j+2].getCircle().getFill()
                            && grid[i][j+2].getCircle().getFill() == grid[i][j+3].getCircle().getFill()) {
                        //set all borders to green to signify a win
                        grid[i][j].getCircle().setStroke(Color.GREEN);
                        grid[i][j+1].getCircle().setStroke(Color.GREEN);
                        grid[i][j+2].getCircle().setStroke(Color.GREEN);
                        grid[i][j+3].getCircle().setStroke(Color.GREEN);
                        return true;
                    }
                }

            }
        }

        //search for a horizontal win
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {

                if(grid[i][j].getCircle().getFill() != Color.WHITE) {
                    if(grid[i][j].getCircle().getFill() == grid[i+1][j].getCircle().getFill()
                            && grid[i+1][j].getCircle().getFill() == grid[i+2][j].getCircle().getFill()
                            && grid[i+2][j].getCircle().getFill() == grid[i+3][j].getCircle().getFill()) {
                        //set all borders to green to signify a win
                        grid[i][j].getCircle().setStroke(Color.GREEN);
                        grid[i+1][j].getCircle().setStroke(Color.GREEN);
                        grid[i+2][j].getCircle().setStroke(Color.GREEN);
                        grid[i+3][j].getCircle().setStroke(Color.GREEN);
                        return true;
                    }
                }

            }
        }

        //check for a diagonal win
        //top right to bottom left
        for(int i = 3; i < 7; i++) {
            for(int j = 0; j < 3; j++ ) {

                if(grid[i][j].getCircle().getFill() != Color.WHITE) {
                    if ((grid[i][j].getCircle().getFill() == grid[i - 1][j + 1].getCircle().getFill()
                            && grid[i - 1][j + 1].getCircle().getFill() == grid[i - 2][j + 2].getCircle().getFill()
                            && grid[i - 2][j + 2].getCircle().getFill() == grid[i - 3][j + 3].getCircle().getFill())) {
                        //set all borders to green to signify a win
                        grid[i][j].getCircle().setStroke(Color.GREEN);
                        grid[i-1][j+1].getCircle().setStroke(Color.GREEN);
                        grid[i-2][j+2].getCircle().setStroke(Color.GREEN);
                        grid[i-3][j+3].getCircle().setStroke(Color.GREEN);
                        return true;
                    }
                }
            }
        }
        //bottom right to top left
        for (int i = 3; i < 7; i++) {
            for(int j = 3; j < 6; j++) {
                if(grid[i][j].getCircle().getFill() != Color.WHITE) {
                    if ((grid[i][j].getCircle().getFill() == grid[i - 1][j - 1].getCircle().getFill()
                            && grid[i - 1][j - 1].getCircle().getFill() == grid[i - 2][j - 2].getCircle().getFill()
                            && grid[i - 2][j - 2].getCircle().getFill() == grid[i - 3][j - 3].getCircle().getFill())) {
                        //set all borders to green to signify a win
                        grid[i][j].getCircle().setStroke(Color.GREEN);
                        grid[i-1][j-1].getCircle().setStroke(Color.GREEN);
                        grid[i-2][j-2].getCircle().setStroke(Color.GREEN);
                        grid[i-3][j-3].getCircle().setStroke(Color.GREEN);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    //checks if the players are in a stalemate
    private boolean checkStalemate() {
        int counter = 0;
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 6; j++) {
                if(grid[i][j].getCircle().getFill() != Color.WHITE) {
                    counter++;
                }
            }
        }

        if(counter == 42) {
            return true;
        }
        else return false;
    }

    //checks if a white space can be taken by a player
    public boolean isAvailable(int col, int row) {
        if(row == 5) {
            if(grid[col][row].getCircle().getFill() == Color.WHITE) {
                red = !red;
                return true;
            }
            else return false;

        }
        else if((grid[col][row + 1].getCircle().getFill() != Color.WHITE) && (grid[col][row].getCircle().getFill() == Color.WHITE)){
            red = !red;
            return true;
        }
        else return false;
    }

    public Disk[][] getGrid() {
        return grid;
    }

    //checks for either a win or a stalemate and stops all further moves
    private void check() {
        //if true setOnClick will be deleted
        boolean shutdown = false;
        if(checkForWin()) {
            shutdown = true;
            if(red) { //red wins
                System.out.println("RED WINS!!!");
            }
            else{ //yellow wins
                System.out.println("YELLOW WINS");
            }
        }
        else if(checkStalemate()) {
            shutdown = true;
            System.out.println("STALEMATE");
        }

        if(shutdown) { //sets all disks setOnClicked to null
          for(int i = 0; i < 7; i++) {
              for(int j = 0; j < 6; j++) {
                  grid[i][j].getCircle().setOnMouseClicked(e -> {});
              }
          }
        }
    }

    //sets all disks on mouse clicked
    public void setAllOnClicked() {
        grid[0][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][0].getRow(), grid[0][0].getCol())) { //if the disk is available
                if(red) {
                    grid[0][0].getCircle().setFill(Color.RED); //change color based on turn
                }
                else grid[0][0].getCircle().setFill(Color.YELLOW);

            }

            check(); //check for a win/stalemate
        });

        grid[1][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][0].getRow(), grid[1][0].getCol())) {
                if(red) {
                    grid[1][0].getCircle().setFill(Color.RED);
                }
                else grid[1][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][0].getRow(), grid[2][0].getCol())) {
                if(red) {
                    grid[2][0].getCircle().setFill(Color.RED);
                }
                else grid[2][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][0].getRow(), grid[3][0].getCol())) {
                if(red) {
                    grid[3][0].getCircle().setFill(Color.RED);
                }
                else grid[3][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][0].getRow(), grid[4][0].getCol())) {
                if(red) {
                    grid[4][0].getCircle().setFill(Color.RED);
                }
                else grid[4][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][0].getRow(), grid[5][0].getCol())) {
                if(red) {
                    grid[5][0].getCircle().setFill(Color.RED);
                }
                else grid[5][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][0].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][0].getRow(), grid[6][0].getCol())) {
                if(red) {
                    grid[6][0].getCircle().setFill(Color.RED);
                }
                else grid[6][0].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[0][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][1].getRow(), grid[0][1].getCol())) {
                if(red) {
                    grid[0][1].getCircle().setFill(Color.RED);
                }
                else grid[0][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[1][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][1].getRow(), grid[1][1].getCol())) {
                if(red) {
                    grid[1][1].getCircle().setFill(Color.RED);
                }
                else grid[1][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][1].getRow(), grid[2][1].getCol())) {
                if(red) {
                    grid[2][1].getCircle().setFill(Color.RED);
                }
                else grid[2][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][1].getRow(), grid[3][1].getCol())) {
                if(red) {
                    grid[3][1].getCircle().setFill(Color.RED);
                }
                else grid[3][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][1].getRow(), grid[4][1].getCol())) {
                if(red) {
                    grid[4][1].getCircle().setFill(Color.RED);
                }
                else grid[4][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][1].getRow(), grid[5][1].getCol())) {
                if(red) {
                    grid[5][1].getCircle().setFill(Color.RED);
                }
                else grid[5][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][1].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][1].getRow(), grid[6][1].getCol())) {
                if(red) {
                    grid[6][1].getCircle().setFill(Color.RED);
                }
                else grid[6][1].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[0][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][2].getRow(), grid[0][2].getCol())) {
                if(red) {
                    grid[0][2].getCircle().setFill(Color.RED);
                }
                else grid[0][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[1][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][2].getRow(), grid[1][2].getCol())) {
                if(red) {
                    grid[1][2].getCircle().setFill(Color.RED);
                }
                else grid[1][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][2].getRow(), grid[2][2].getCol())) {
                if(red) {
                    grid[2][2].getCircle().setFill(Color.RED);
                }
                else grid[2][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][2].getRow(), grid[3][2].getCol())) {
                if(red) {
                    grid[3][2].getCircle().setFill(Color.RED);
                }
                else grid[3][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][2].getRow(), grid[4][2].getCol())) {
                if(red) {
                    grid[4][2].getCircle().setFill(Color.RED);
                }
                else grid[4][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][2].getRow(), grid[5][2].getCol())) {
                if(red) {
                    grid[5][2].getCircle().setFill(Color.RED);
                }
                else grid[5][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][2].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][2].getRow(), grid[6][2].getCol())) {
                if(red) {
                    grid[6][2].getCircle().setFill(Color.RED);
                }
                else grid[6][2].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[0][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][3].getRow(), grid[0][3].getCol())) {
                if(red) {
                    grid[0][3].getCircle().setFill(Color.RED);
                }
                else grid[0][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[1][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][3].getRow(), grid[1][3].getCol())) {
                if(red) {
                    grid[1][3].getCircle().setFill(Color.RED);
                }
                else grid[1][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][3].getRow(), grid[2][3].getCol())) {
                if(red) {
                    grid[2][3].getCircle().setFill(Color.RED);
                }
                else grid[2][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][3].getRow(), grid[3][3].getCol())) {
                if(red) {
                    grid[3][3].getCircle().setFill(Color.RED);
                }
                else grid[3][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][3].getRow(), grid[4][3].getCol())) {
                if(red) {
                    grid[4][3].getCircle().setFill(Color.RED);
                }
                else grid[4][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][3].getRow(), grid[5][3].getCol())) {
                if(red) {
                    grid[5][3].getCircle().setFill(Color.RED);
                }
                else grid[5][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][3].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][3].getRow(), grid[6][3].getCol())) {
                if(red) {
                    grid[6][3].getCircle().setFill(Color.RED);
                }
                else grid[6][3].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[0][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][4].getRow(), grid[0][4].getCol())) {
                if(red) {
                    grid[0][4].getCircle().setFill(Color.RED);
                }
                else grid[0][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[1][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][4].getRow(), grid[1][4].getCol())) {
                if(red) {
                    grid[1][4].getCircle().setFill(Color.RED);
                }
                else grid[1][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][4].getRow(), grid[2][4].getCol())) {
                if(red) {
                    grid[2][4].getCircle().setFill(Color.RED);
                }
                else grid[2][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][4].getRow(), grid[3][4].getCol())) {
                if(red) {
                    grid[3][4].getCircle().setFill(Color.RED);
                }
                else grid[3][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][4].getRow(), grid[4][4].getCol())) {
                if(red) {
                    grid[4][4].getCircle().setFill(Color.RED);
                }
                else grid[4][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][4].getRow(), grid[5][4].getCol())) {
                if(red) {
                    grid[5][4].getCircle().setFill(Color.RED);
                }
                else grid[5][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][4].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][4].getRow(), grid[6][4].getCol())) {
                if(red) {
                    grid[6][4].getCircle().setFill(Color.RED);
                }
                else grid[6][4].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[0][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[0][5].getRow(), grid[0][5].getCol())) {
                if(red) {
                    grid[0][5].getCircle().setFill(Color.RED);
                }
                else grid[0][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[1][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[1][5].getRow(), grid[1][5].getCol())) {
                if(red) {
                    grid[1][5].getCircle().setFill(Color.RED);
                }
                else grid[1][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[2][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[2][5].getRow(), grid[2][5].getCol())) {
                if(red) {
                    grid[2][5].getCircle().setFill(Color.RED);
                }
                else grid[2][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[3][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[3][5].getRow(), grid[3][5].getCol())) {
                if(red) {
                    grid[3][5].getCircle().setFill(Color.RED);
                }
                else grid[3][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[4][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[4][5].getRow(), grid[4][5].getCol())) {
                if(red) {
                    grid[4][5].getCircle().setFill(Color.RED);
                }
                else grid[4][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[5][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[5][5].getRow(), grid[5][5].getCol())) {
                if(red) {
                    grid[5][5].getCircle().setFill(Color.RED);
                }
                else grid[5][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });

        grid[6][5].getCircle().setOnMouseClicked(e -> {
            if(isAvailable(grid[6][5].getRow(), grid[6][5].getCol())) {
                if(red) {
                    grid[6][5].getCircle().setFill(Color.RED);
                }
                else grid[6][5].getCircle().setFill(Color.YELLOW);

            }

            check();
        });



    }

    private Disk[][] grid;
    private Boolean red;
}