import objectdraw.*;
import java.awt.Color;
import java.applet.*;
import java.awt.Image;
import java.net.URL;
/**
 * Write a description of class mian here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class mian extends WindowController
{
    Board chessBoard;
    Pieces selectedPiece;
    Game gameMode;
    Player p1;
    Player p2;

    public void begin(){

        ((JDrawingCanvas)canvas).setBackground(Color.gray);

        chessBoard = new Board(canvas);

        p1 = new Human(0);
        p2 = new AI(1);

        chessBoard.initializeBoard();

        gameMode = new Game(p1, p2, chessBoard);

        //FilledRect Jacen = new FilledRect(0, 0, canvas.getWidth()/8.0, canvas.getHeight()/8.0, canvas);

        //Color mmm = new Color(255, 0, 0);

        //Jacen.setColor(mmm);

    }

    public void onMousePress(Location p) {

        Tiles selectedTile = chessBoard.contains(p);
        boolean move = false;
        Player temp;
        if(gameMode.playerTurn == 0){

            temp = p1;

        } else {

            temp = p2;

        }
        
        if(gameMode.isPlayerInCheck(temp)){
                if(gameMode.checkMate(temp)){
                
                    return;
                    
                }
            
                if(selectedTile.isPiece){
                    if(selectedPiece != null){
                        
                        if(gameMode.playerTurn != selectedTile.pieceOn.team || selectedPiece.team != selectedTile.pieceOn.team){
                            if(selectedTile.isHighlighted){
                                if(gameMode.moveOutOfCheck(selectedPiece, selectedTile, temp)){
                                gameMode.updateMove(temp, selectedTile.pieceOn);
                                //System.out.println("lmao");
                                selectedTile.pieceOn.beKilled();
                                selectedPiece.movePiece(selectedTile);
                                selectedPiece.deSelectPiece();
                                gameMode.moveAI();
                                if(selectedPiece != null){

                                    selectedPiece = null;

                                }
                                }
                                

                            } else {
                                selectedPiece.deSelectPiece();
                            }
                        } else {
                            selectedPiece.deSelectPiece();
                            selectedPiece = selectedTile.pieceOn;
                            selectedPiece.selectPiece();

                        }
                        //System.out.println("Selected Piece Team: " + selectedTile.pieceOn.team);
                    
                    } else if(selectedTile.pieceOn.team == gameMode.playerTurn){

                        selectedPiece = selectedTile.pieceOn;
                        selectedPiece.selectPiece();

                    }
                
            } else if(selectedTile.isHighlighted && gameMode.moveOutOfCheck(selectedPiece, selectedTile, temp)){
                selectedPiece.deSelectPiece();
                
                selectedPiece.movePiece(selectedTile);
                gameMode.updateMove(temp);
                gameMode.moveAI();
                if(selectedPiece != null){

                    selectedPiece = null;

                }

            } else if(selectedPiece != null){
                selectedPiece.deSelectPiece();
            }

        } else {
            //System.out.println("Player Turn: " + gameMode.playerTurn);
            if(selectedTile.isPiece){
                if(selectedPiece != null){
                    if(gameMode.playerTurn != selectedTile.pieceOn.team || selectedPiece.team != selectedTile.pieceOn.team){
                        if(selectedTile.isHighlighted && gameMode.moveOutOfCheck(selectedPiece, selectedTile, temp)){

                            gameMode.updateMove(temp, selectedTile.pieceOn);
                            //System.out.println("lmao");
                            selectedTile.pieceOn.beKilled();
                            selectedPiece.movePiece(selectedTile);
                            selectedPiece.deSelectPiece();
                            gameMode.moveAI();
                            if(selectedPiece != null){

                                selectedPiece = null;

                            }

                        } else {
                            selectedPiece.deSelectPiece();
                        }
                    } else {
                        selectedPiece.deSelectPiece();
                        selectedPiece = selectedTile.pieceOn;
                        selectedPiece.selectPiece();

                    }
                    //System.out.println("Selected Piece Team: " + selectedTile.pieceOn.team);

                } else if(selectedTile.pieceOn.team == gameMode.playerTurn){

                    selectedPiece = selectedTile.pieceOn;
                    selectedPiece.selectPiece();

                }

            } else if(selectedTile.isHighlighted && gameMode.moveOutOfCheck(selectedPiece, selectedTile, temp)){
                selectedPiece.deSelectPiece();


                selectedPiece.movePiece(selectedTile);

                gameMode.updateMove(temp);
                gameMode.moveAI();
                if(selectedPiece != null){

                    selectedPiece = null;

                }

            } else if(selectedPiece != null){
                selectedPiece.deSelectPiece();
            }
        }
        
        

    }

    public static void main(String [] args){
        (new mian()).startController(600, 600);
    }
}
