import objectdraw.*;
/**
 * Write a description of class Board here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Board implements Cloneable
{
    Tiles [][] boardTiles;
    Pawn [] pawns;
    Rook [] rooks;
    Knight [] knights;
    King [] kings;
    Bishop [] bishops;
    Queen [] queens;

    DrawingCanvas canvas;
   
    public Board(DrawingCanvas c){

        boardTiles = new Tiles [8][8]; 
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){

                boardTiles[i][k] = new Tiles(k, i, c, ((k % 2) + (i % 2)) % 2, this);

            }

        }

        canvas = c;

    }
    public Board(){
    
        boardTiles = new Tiles [8][8]; 
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){

                boardTiles[i][k] = new Tiles(k, i, this);

            }

        }
        
        initializeMockBoard();
    
    }
    public void initializeMockBoard(){
    
        pawns = new Pawn[16];
        rooks = new Rook[4];
        knights = new Knight[4];
        kings = new King[2];
        bishops = new Bishop[4];
        queens = new Queen[2];
        queens[0] = new Queen(boardTiles[0][3], 0);
        queens[1] = new Queen(boardTiles[7][3], 1);
        bishops[0] = new Bishop(boardTiles[0][2], 0);
        bishops[1] = new Bishop(boardTiles[0][5], 0);
        bishops[2] = new Bishop(boardTiles[7][2], 1);
        bishops[3] = new Bishop(boardTiles[7][5], 1);
        kings[0] = new King(boardTiles[0][4], 0);
        kings[1] = new King(boardTiles[7][4], 1);
        rooks[0] = new Rook(boardTiles[0][0], 0);
        rooks[1] = new Rook(boardTiles[0][7], 0);
        rooks[2] = new Rook(boardTiles[7][0], 1);
        rooks[3] = new Rook(boardTiles[7][7], 1);
        knights[0] = new Knight(boardTiles[0][1], 0);
        knights[1] = new Knight(boardTiles[0][6], 0);
        knights[2] = new Knight(boardTiles[7][1], 1);
        knights[3] = new Knight(boardTiles[7][6], 1);
        for(int i = 0; i < 8; i++){

            pawns[i] = new Pawn(boardTiles[1][i], 0);

        }
        for(int i = 0; i < 8; i++){

            pawns[8+i] = new Pawn(boardTiles[6][i], 1);

        }
    
    }
    

    public void initializeBoard(){

        pawns = new Pawn[16];
        rooks = new Rook[4];
        knights = new Knight[4];
        kings = new King[2];
        bishops = new Bishop[4];
        queens = new Queen[2];
        queens[0] = new Queen(canvas, boardTiles[0][3], 0);
        queens[1] = new Queen(canvas, boardTiles[7][3], 1);
        bishops[0] = new Bishop(canvas, boardTiles[0][2], 0);
        bishops[1] = new Bishop(canvas, boardTiles[0][5], 0);
        bishops[2] = new Bishop(canvas, boardTiles[7][2], 1);
        bishops[3] = new Bishop(canvas, boardTiles[7][5], 1);
        kings[0] = new King(canvas, boardTiles[0][4], 0);
        kings[1] = new King(canvas, boardTiles[7][4], 1);
        rooks[0] = new Rook(canvas, boardTiles[0][0], 0);
        rooks[1] = new Rook(canvas, boardTiles[0][7], 0);
        rooks[2] = new Rook(canvas, boardTiles[7][0], 1);
        rooks[3] = new Rook(canvas, boardTiles[7][7], 1);
        knights[0] = new Knight(canvas, boardTiles[0][1], 0);
        knights[1] = new Knight(canvas, boardTiles[0][6], 0);
        knights[2] = new Knight(canvas, boardTiles[7][1], 1);
        knights[3] = new Knight(canvas, boardTiles[7][6], 1);
        for(int i = 0; i < 8; i++){

            pawns[i] = new Pawn(canvas, boardTiles[1][i], 0);

        }
        for(int i = 0; i < 8; i++){

            pawns[8+i] = new Pawn(canvas, boardTiles[6][i], 1);

        }

    
    }

    public Tiles contains(Location p){

        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){

                if((boardTiles[i][k]).contains(p)){

                    return boardTiles[i][k];

                }

            }

        }
        return boardTiles[0][0];
    }

    public void highlight(int xk, int yk){

        boardTiles[yk][xk].highlight();

    }

    public void unHighlight(int xk, int yk){

        boardTiles[yk][xk].unHighlight();

    }

    public void unHighlightAll(){
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){

                if((boardTiles[i][k]).isHighlighted){

                    boardTiles[i][k].unHighlight();

                }

            }

        }

    }
    
    public Board copy(){
    
        Board returnBoard = this;
       
        for(int i = 0; i < 8; i++){
        
            returnBoard.boardTiles[i] = boardTiles[i].clone();
        
        }
        
        returnBoard.pawns = pawns.clone();
        returnBoard.rooks = rooks.clone();
        returnBoard.knights = knights.clone();
        returnBoard.kings = kings.clone();
        returnBoard.bishops = bishops.clone();
        returnBoard.queens = queens.clone();
        
        return returnBoard;
    
    }
    
}
