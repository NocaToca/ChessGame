import objectdraw.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends Pieces
{
    Image body;
    static Toolkit kit = Toolkit.getDefaultToolkit();

    boolean firstMove;
    public Pawn(DrawingCanvas c, Tiles startTile, int teams){

        super(startTile);

        if(teams == 0) {

            body = kit.createImage("pawnwhite.png");

        } else {

            body = kit.createImage("pawnblack.png");

        }

        team = teams;

        firstMove = true;

        points = 1;

        image = new VisibleImage(body, startTile.posX, startTile.posY, startTile.use, startTile.use, c);

    }
    public Pawn(Tiles startTile, int teams){
        super(startTile);
        
        team = teams;
        
        points = 1;
    
    }

    public void selectPiece(){
        Board chessBoard = ontopofTile.chessBoard;
        if(team == 0){

            if(firstMove){

                if(!chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x].isPiece){
                    chessBoard.highlight(ontopofTile.x, ontopofTile.y + 1);
                    if(!chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x].isPiece){
                        chessBoard.highlight(ontopofTile.x, ontopofTile.y + 2);

                    }

                }

            } else {
                if(!chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x].isPiece){
                    chessBoard.highlight(ontopofTile.x, ontopofTile.y + 1);

                }

            }

            if(ontopofTile.x+1 < 8){
                if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1].pieceOn.team != team){

                        chessBoard.highlight(ontopofTile.x+1, ontopofTile.y+1);

                    }

                }
            }

            if(ontopofTile.x-1 >= 0){
                if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1].pieceOn.team != team){

                        chessBoard.highlight(ontopofTile.x-1, ontopofTile.y+1);

                    }

                }
            }

        } else {
            if(firstMove){

                if(!chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x].isPiece){
                    chessBoard.highlight(ontopofTile.x, ontopofTile.y - 2);

                }
                if(!chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x].isPiece){
                    chessBoard.highlight(ontopofTile.x, ontopofTile.y - 1);

                }

            } else {
                if(!chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x].isPiece){
                    chessBoard.highlight(ontopofTile.x, ontopofTile.y - 1);

                }

            }
            if(ontopofTile.x+1 < 8){
                if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1].pieceOn.team != team){

                        chessBoard.highlight(ontopofTile.x+1, ontopofTile.y-1);

                    }

                }
            }

            if(ontopofTile.x-1 >=0){
                if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1].pieceOn.team != team){

                        chessBoard.highlight(ontopofTile.x-1, ontopofTile.y-1);

                    }

                }
            }

        }

    }

    public void movePiece(Tiles tile){
        super.movePiece(tile);
        if(firstMove){

            firstMove = false;

        }

    }

    public ArrayList<Tiles> tilesCanMove(){
        ArrayList<Tiles> moveAbleTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        if(team == 0){

            if(firstMove){
                if(ontopofTile.y + 2 < 8){

                    if(!chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x].isPiece){
                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y + 1][ontopofTile.x]);
                        if(!chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x].isPiece){
                            moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y + 2][ontopofTile.x]);

                        }

                    }

                }

            } else {
                if(ontopofTile.y + 1 < 8){
                    if(!chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x].isPiece){
                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y + 1][ontopofTile.x]);

                    }

                }

            }
            if(ontopofTile.x+1 < 8 && ontopofTile.y + 1 < 8){
                if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1].pieceOn.team != team){

                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y + 1][ontopofTile.x + 1]);

                    }

                }
            }

            if(ontopofTile.x-1 >= 0 && ontopofTile.y + 1 < 8){
                if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1].pieceOn.team != team){

                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y + 1][ontopofTile.x - 1]);

                    }

                }
            }

        } else {
            if(firstMove){
                if(ontopofTile.y - 2 >= 0){

                    if(!chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x].isPiece){
                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y - 1][ontopofTile.x]);
                        if(!chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x].isPiece){
                            moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y - 2][ontopofTile.x]);

                        }

                    }
                }

            } else {
                if(ontopofTile.y - 1 >= 0){
                    if(!chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x].isPiece){
                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y - 1][ontopofTile.x]);

                    }

                }

            }
            if(ontopofTile.x+1 < 8 && ontopofTile.y - 1 >= 0){
                if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1].pieceOn.team != team){

                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y - 1][ontopofTile.x + 1]);

                    }

                }
            }

            if(ontopofTile.x-1 >=0 && ontopofTile.y - 1 >= 0){
                if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1].isPiece){
                    if(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1].pieceOn.team != team){

                        moveAbleTiles.add(chessBoard.boardTiles[ontopofTile.y - 1][ontopofTile.x - 1]);

                    }

                }
            }

        }

        return moveAbleTiles;

    }
    
    public ArrayList<Tiles> tilesCanMove(int useless){
        ArrayList<Tiles> moveAbleTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        int pseudoX = pseudoXList.get(useless);
        int pseudoY = pseudoYList.get(useless);
        if(team == 0){

            if(firstMove){
                if(pseudoY + 2 < 8){

                    if(!chessBoard.boardTiles[pseudoY+1][pseudoX].isPiece(pseudoX, pseudoY+1, useless)){
                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY + 1][pseudoX]);
                        if(!chessBoard.boardTiles[pseudoY+2][ontopofTile.x].isPiece(pseudoX, pseudoY+2, useless)){
                            moveAbleTiles.add(chessBoard.boardTiles[pseudoY + 2][pseudoX]);

                        }

                    }

                }

            } else {
                if(pseudoY + 1 < 8){
                    if(!chessBoard.boardTiles[pseudoY+1][pseudoX].isPiece(pseudoX, pseudoY+1, useless)){
                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY + 1][pseudoX]);

                    }

                }

            }
            if(pseudoX+1 < 8 && pseudoY + 1 < 8){
                if(chessBoard.boardTiles[pseudoY+1][pseudoX+1].isPiece(pseudoX+1, pseudoY+1, useless)){
                    if(chessBoard.boardTiles[pseudoY+1][pseudoX+1].team(pseudoX+1, pseudoY+1, useless, team)){

                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY + 1][pseudoX + 1]);

                    }

                }
            }

            if(pseudoX-1 >= 0 && pseudoY + 1 < 8){
                if(chessBoard.boardTiles[pseudoY+1][pseudoX-1].isPiece(pseudoX-1, pseudoY+1, useless)){
                    if(chessBoard.boardTiles[pseudoY+1][pseudoX-1].team(pseudoX-1, pseudoY+1, useless, team)){

                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY + 1][pseudoX - 1]);

                    }

                }
            }

        } else {
            if(firstMove){
                if(pseudoY - 2 >= 0){

                    if(!chessBoard.boardTiles[pseudoY-1][ontopofTile.x].isPiece(pseudoX, pseudoY-1, useless)){
                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY - 1][pseudoX]);
                        if(!chessBoard.boardTiles[pseudoY-2][pseudoX].isPiece(pseudoX, pseudoY-2, useless)){
                            moveAbleTiles.add(chessBoard.boardTiles[pseudoY - 2][pseudoX]);

                        }

                    }
                }

            } else {
                if(pseudoY - 1 >= 0){
                    if(!chessBoard.boardTiles[pseudoY-1][pseudoX].isPiece(pseudoX, pseudoY-1, useless)){
                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY - 1][pseudoX]);

                    }

                }

            }
            if(pseudoX+1 < 8 && pseudoY - 1 >= 0){
                if(chessBoard.boardTiles[pseudoY-1][pseudoX+1].isPiece(pseudoX+1, pseudoY-1, useless)){
                    if(chessBoard.boardTiles[pseudoY-1][pseudoX+1].team(pseudoX+1, pseudoY-1, useless, team)){

                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY - 1][pseudoX + 1]);

                    }

                }
            }

            if(pseudoX-1 >=0 && pseudoY - 1 >= 0){
                if(chessBoard.boardTiles[pseudoY-1][pseudoX-1].isPiece(pseudoX-1, pseudoY-1, useless)){
                    if(chessBoard.boardTiles[pseudoY-1][pseudoX-1].team(pseudoX-1, pseudoY-1, useless, team)){

                        moveAbleTiles.add(chessBoard.boardTiles[pseudoY - 1][pseudoX - 1]);

                    }

                }
            }

        }

        return moveAbleTiles;

    }

}
