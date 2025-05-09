package controller;

import model.Direction;
import model.MapModel;
import view.game.BoxComponent;
import view.game.GamePanel;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;
    private final MapModel model;

    public GameController(GamePanel view, MapModel model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }

    public void restartGame() {
        //在此加入重新开始游戏的逻辑
        System.out.println("Do restart game here");
        this.model.resetOriginalMatrix();
        this.view.clearAllBoxFromPanel();
        this.view.initialGame(model.getMatrix());
    }

    public boolean doMove(int row, int col, Direction direction) {
        //对单个的box进行操作
        if (model.getId(row, col) == 1) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol)) {
                if (model.getId(nextRow, nextCol) == 0) {
                    model.getMatrix()[row][col] = 0;
                    model.getMatrix()[nextRow][nextCol] = 1;
                    BoxComponent box = view.getSelectedBox();
                    box.setRow(nextRow);
                    box.setCol(nextCol);
                    box.setLocation(box.getCol() * view.getGRID_SIZE() + 2, box.getRow() * view.getGRID_SIZE() + 2);
                    box.repaint();
                    return true;
                }
            }
        }
        // 水平双箱子
        if (model.getId(row, col) == 2) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
//            if (model.checkInWidthSize(nextCol + 1) &&model.checkInWidthSize(nextCol) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow, nextCol + 1) == 0) {
//                model.getMatrix()[row][col] = 0;
//                model.getMatrix()[row][col + 1] = 0;
//                model.getMatrix()[nextRow][nextCol] = 2;
//                model.getMatrix()[nextRow][nextCol + 1] = 2;
//                updateBoxPosition(row, col, nextRow, nextCol);
//                return true;
//            }//只能满足上下的移动
//            //此时撞边界会超限
            if ((direction.getRow() == -1 || direction.getRow() == 1) && direction.getCol() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInWidthSize(nextCol + 1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow, nextCol + 1) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row][col + 1] = 0;
                        model.getMatrix()[nextRow][nextCol] = 2;
                        model.getMatrix()[nextRow][nextCol + 1] = 2;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInWidthSize(nextCol + 1) &&model.checkInWidthSize(nextCol) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow, nextCol + 1) == 2) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row][col + 1] = 0;
                model.getMatrix()[nextRow][nextCol] = 2;
                model.getMatrix()[nextRow][nextCol + 1] = 2;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以左
            else if (model.checkInWidthSize(nextCol + 1) &&model.checkInWidthSize(nextCol) && model.getId(nextRow, nextCol) == 2 && model.getId(nextRow, nextCol + 1) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row][col + 1] = 0;
                model.getMatrix()[nextRow][nextCol] = 2;
                model.getMatrix()[nextRow][nextCol + 1] = 2;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以右
        }


        //对垂直的双箱子操作
        //第一类:33
    if (model.getId(row, col) == 3) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if ((direction.getCol() == -1 || direction.getCol() == 1) && direction.getRow() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInHeightSize(nextRow + 1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[nextRow][nextCol] = 3;
                        model.getMatrix()[nextRow + 1][nextCol] = 3;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 3) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 3;
                model.getMatrix()[nextRow + 1][nextCol] = 3;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以上
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 3 && model.getId(nextRow + 1, nextCol) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 3;
                model.getMatrix()[nextRow + 1][nextCol] = 3;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以
        }
        //第二类:55
      if (model.getId(row, col) == 5) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if ((direction.getCol() == -1 || direction.getCol() == 1) && direction.getRow() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInHeightSize(nextRow + 1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[nextRow][nextCol] = 5;
                        model.getMatrix()[nextRow + 1][nextCol] = 5;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 5) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 5;
                model.getMatrix()[nextRow + 1][nextCol] = 5;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以上
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 5 && model.getId(nextRow + 1, nextCol) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 5;
                model.getMatrix()[nextRow + 1][nextCol] = 5;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以
        }
        //第3类:66
        if (model.getId(row, col) == 6) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if ((direction.getCol() == -1 || direction.getCol() == 1) && direction.getRow() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInHeightSize(nextRow + 1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[nextRow][nextCol] = 6;
                        model.getMatrix()[nextRow + 1][nextCol] = 6;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 6) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 6;
                model.getMatrix()[nextRow + 1][nextCol] = 6;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以上
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 6 && model.getId(nextRow + 1, nextCol) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 6;
                model.getMatrix()[nextRow + 1][nextCol] = 6;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以
        }
        //第4类:77
        if (model.getId(row, col) == 7) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if ((direction.getCol() == -1 || direction.getCol() == 1) && direction.getRow() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInHeightSize(nextRow + 1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[nextRow][nextCol] = 7;
                        model.getMatrix()[nextRow + 1][nextCol] = 7;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 7) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 7;
                model.getMatrix()[nextRow + 1][nextCol] = 7;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以上
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 7 && model.getId(nextRow + 1, nextCol) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[nextRow][nextCol] = 7;
                model.getMatrix()[nextRow + 1][nextCol] = 7;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以
        }
        if (model.getId(row, col) == 4) {
            int nextRow = row + direction.getRow();
            int nextCol = col + direction.getCol();
            if ((direction.getCol() == -1 || direction.getCol() == 1) && direction.getRow() == 0) {
                if (model.checkInHeightSize(nextRow) && model.checkInWidthSize(nextCol) && model.checkInHeightSize(nextRow + 1) &&model.checkInWidthSize(nextCol+1)) {
                    if (model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[row][col+1] = 0;
                        model.getMatrix()[row + 1][col+1] = 0;
                        model.getMatrix()[nextRow][nextCol] = 4;
                        model.getMatrix()[nextRow + 1][nextCol] = 4;
                        model.getMatrix()[nextRow][nextCol+1] = 4;
                        model.getMatrix()[nextRow + 1][nextCol+1] = 4;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }if (model.getId(nextRow, nextCol) == 4 && model.getId(nextRow + 1, nextCol) == 4 && model.getId(nextRow, nextCol+1) == 0 && model.getId(nextRow + 1, nextCol+1) == 0) {
                        model.getMatrix()[row][col] = 0;
                        model.getMatrix()[row + 1][col] = 0;
                        model.getMatrix()[row][col+1] = 0;
                        model.getMatrix()[row + 1][col+1] = 0;
                        model.getMatrix()[nextRow][nextCol] = 4;
                        model.getMatrix()[nextRow + 1][nextCol] = 4;
                        model.getMatrix()[nextRow][nextCol+1] = 4;
                        model.getMatrix()[nextRow + 1][nextCol+1] = 4;
                        updateBoxPosition(row, col, nextRow, nextCol);
                        return true;
                    }
                }
            }
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 0 && model.getId(nextRow + 1, nextCol) == 4 && model.getId(nextRow, nextCol+1) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[row][col+1] = 0;
                model.getMatrix()[row + 1][col+1] = 0;
                model.getMatrix()[nextRow][nextCol] = 4;
                model.getMatrix()[nextRow + 1][nextCol] = 4;
                model.getMatrix()[nextRow][nextCol+1] = 4;
                model.getMatrix()[nextRow + 1][nextCol+1] = 4;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以上
            else if (model.checkInHeightSize(nextRow + 1) &&model.checkInHeightSize(nextRow) && model.getId(nextRow, nextCol) == 4 && model.getId(nextRow + 1, nextCol) == 0 && model.getId(nextRow + 1, nextCol+1) == 0) {
                model.getMatrix()[row][col] = 0;
                model.getMatrix()[row + 1][col] = 0;
                model.getMatrix()[row][col+1] = 0;
                model.getMatrix()[row + 1][col+1] = 0;
                model.getMatrix()[nextRow][nextCol] = 4;
                model.getMatrix()[nextRow + 1][nextCol] = 4;
                model.getMatrix()[nextRow][nextCol+1] = 4;
                model.getMatrix()[nextRow + 1][nextCol+1] = 4;
                updateBoxPosition(row, col, nextRow, nextCol);
                return true;
            }//可以下

        }

        return false;
    }

    private void updateBoxPosition(int row, int col, int nextRow, int nextCol) {
        BoxComponent box = view.getSelectedBox();
        box.setRow(nextRow);
        box.setCol(nextCol);
        box.setLocation(box.getCol() * view.getGRID_SIZE() + 2, box.getRow() * view.getGRID_SIZE() + 2);
        box.repaint();
    }

    //todo: add other methods such as loadGame, saveGame...

}
