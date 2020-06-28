
import objectdraw.*;
import java.awt.Color;
/**
 * Write a description of class Tiles here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tiles
{
    int posX;
    int posY;
    int pseudoX;
    int pseudoY;
    int x;
    int y;
    FilledRect tile;
    Color tileColor;
    Board chessBoard;
    int use;
    Pieces pieceOn;
    boolean isPiece = false;
    Color highlighted = new Color(128, 128, 255);
    boolean isHighlighted = false;

    public Tiles(int xk, int yk, DrawingCanvas c, int color, Board cboard){
        use = c.getHeight()/8;
        posX = xk * use;
        posY = yk * use;

        x = xk;
        y = yk;

        pseudoX = x;
        pseudoY = y;

        chessBoard = cboard;

        //System.out.println("Tile Constructed " + color);

        if(color == 0){
            tileColor = new Color(255, 255, 255);
        } else {
            tileColor = new Color(0, 0, 0);
        }
        //Color tileColor = new Color(255, 255, 255);
        tile = new FilledRect(x * use, y * use, use, use, c);

        tile.setColor(tileColor);

    }

    public Tiles(int xk, int yk, Board cboard){

        x = xk;
        y = yk;
        chessBoard = cboard;

    }

    public boolean contains(Location p){

        if(tile.contains(p)){

            return true;

        }

        return false;

    }

    public void highlight(){

        tile.setColor(highlighted);
        //System.out.println(x + " " + y);
        isHighlighted = true;

    }

    public void unHighlight(){
        tile.setColor(tileColor);
        isHighlighted = false;
    }

    public boolean equals(Tiles compare){

        if(compare.pieceOn.matches(pieceOn) && compare.x == x && compare.y == y){

            return true;

        }
        return false;

    }

    public boolean isPiece(int pseudoX, int pseudoY, int depth){

        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){

                if(chessBoard.boardTiles[i][k].isPiece){

                    if(pseudoX == chessBoard.boardTiles[i][k].pieceOn.pseudoXList.get(depth) && pseudoY == chessBoard.boardTiles[i][k].pieceOn.pseudoYList.get(depth)){

                        return true;

                    }

                }

            }
        }
        return false;

    }
    
    public boolean team(int pseudoX, int pseudoY, int depth, int team){
    
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){
                if(chessBoard.boardTiles[i][k].isPiece){
                if(pseudoX == chessBoard.boardTiles[i][k].pieceOn.pseudoXList.get(depth) && pseudoY == chessBoard.boardTiles[i][k].pieceOn.pseudoYList.get(depth)){
                
                    if(chessBoard.boardTiles[i][k].pieceOn.team == team){
                    
                        return true;
                    
                    }
                
                }
                }
                
            }
        }
        return false;
    }
}
