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
    boolean isQueen = false;
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
        if(!isQueen){
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
    } else {
    
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        //System.out.println(x);
        //System.out.println(y);
        int moveRight = 7 - x;
        int moveLeft = x;
        int moveUp = y;
        int moveDown = 7 - y; 
        //System.out.println(moveRight);
        for(int i = 1; i < moveRight+1; i++){

            if(chessBoard.boardTiles[y][x+(i)].isPiece){
                if(chessBoard.boardTiles[y][x+(i)].pieceOn.team != team){

                    chessBoard.highlight(ontopofTile.x + i, ontopofTile.y);

                }
                break;

            }
            //System.out.println("d");
            chessBoard.highlight(ontopofTile.x + i, ontopofTile.y);

        }
        for(int i = 1; i < moveLeft+1; i++){

            if(chessBoard.boardTiles[y][x-(i)].isPiece){
                if(chessBoard.boardTiles[y][x-(i)].pieceOn.team != team){

                    chessBoard.highlight(ontopofTile.x - i, ontopofTile.y);

                }
                break;

            }
            chessBoard.highlight(ontopofTile.x - i, ontopofTile.y);

        }
        for(int i = 1; i < moveUp+1; i++){

            if(chessBoard.boardTiles[y-(i)][x].isPiece){
                if(chessBoard.boardTiles[y-(i)][x].pieceOn.team != team){

                    chessBoard.highlight(ontopofTile.x, ontopofTile.y - i);

                }
                break;

            }
            chessBoard.highlight(ontopofTile.x, ontopofTile.y - i);

        }
        for(int i = 1; i < moveDown+1; i++){

            if(chessBoard.boardTiles[y+(i)][x].isPiece){
                if(chessBoard.boardTiles[y+(i)][x].pieceOn.team != team){

                    chessBoard.highlight(ontopofTile.x , ontopofTile.y + i);

                }
                break;

            }
            chessBoard.highlight(ontopofTile.x, ontopofTile.y + i);

        }
        while(x < 7 && y < 7){

            x++;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){

                    chessBoard.highlight(x, y);

                }
                break;
            }
            chessBoard.highlight(x, y);

        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x < 7 && y > 0){
            x++;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){

                    chessBoard.highlight(x, y);

                }
                break;
            }
            chessBoard.highlight(x, y);

        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x > 0 && y > 0){
            x--;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){

                    chessBoard.highlight(x, y);

                }
                break;
            }
            chessBoard.highlight(x, y);

        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x > 0 && y < 7){
            x--;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){

                    chessBoard.highlight(x, y);

                }
                break;
            }
            chessBoard.highlight(x, y);

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
        if(!isQueen){
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
    } else {
    ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        //System.out.println(x);
        //System.out.println(y);
        int moveRight = 7 - x;
        int moveLeft = x;
        int moveUp = y;
        int moveDown = 7 - y; 
        //System.out.println(moveRight);
        for(int i = 1; i < moveRight+1; i++){
        
            
            if(chessBoard.boardTiles[y][x+(i)].isPiece){
                if(chessBoard.boardTiles[y][x+(i)].pieceOn.team != team){
                    
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x + i]);
                
                }
                break;
            
            }
            //System.out.println("d");
            moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x + i]);
        
        }
        for(int i = 1; i < moveLeft+1; i++){
        
            if(chessBoard.boardTiles[y][x-(i)].isPiece){
                if(chessBoard.boardTiles[y][x-(i)].pieceOn.team != team){
                    
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x - i]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x - i]);
        
        }
        for(int i = 1; i < moveUp+1; i++){
        
            if(chessBoard.boardTiles[y-(i)][x].isPiece){
                if(chessBoard.boardTiles[y-(i)][x].pieceOn.team != team){
                    
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y - i][ontopofTile.x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[ontopofTile.y - i][ontopofTile.x]);
        
        }
        for(int i = 1; i < moveDown+1; i++){
        
            if(chessBoard.boardTiles[y+(i)][x].isPiece){
                if(chessBoard.boardTiles[y+(i)][x].pieceOn.team != team){
                    
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y + i][ontopofTile.x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[ontopofTile.y + i][ontopofTile.x]);
        
        }
        
        
        while(x < 7 && y < 7){
        
            x++;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x < 7 && y > 0){
            x++;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x > 0 && y > 0){
            x--;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = ontopofTile.x;
        y = ontopofTile.y;
        while(x > 0 && y < 7){
            x--;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece){
                if(chessBoard.boardTiles[y][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        
        
        return moveableTiles;
    }
    }

    public ArrayList<Tiles> tilesCanMove(int useless){
        if(!isQueen){
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
    } else {
     ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        int x = pseudoXList.get(useless);
        int y = pseudoYList.get(useless);
        int depth = useless;
        //System.out.println(x);
        //System.out.println(y);
        int moveRight = 7 - x;
        int moveLeft = x;
        int moveUp = y;
        int moveDown = 7 - y; 
        //System.out.println(moveRight);
        for(int i = 1; i < moveRight+1; i++){
        
            
            if(chessBoard.boardTiles[y][x+(i)].isPiece(x+i, y, useless)){
                if(chessBoard.boardTiles[y][x+(i)].team(x+i, y, useless, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y][x + i]);
                
                }
                break;
            
            }
            //System.out.println("d");
            moveableTiles.add(chessBoard.boardTiles[y][x + i]);
        
        }
        for(int i = 1; i < moveLeft+1; i++){
        
            if(chessBoard.boardTiles[y][x-(i)].isPiece(x - i, y, useless)){
                if(chessBoard.boardTiles[y][x-(i)].team(x - i, y, useless, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y][x - i]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y][x - i]);
        
        }
        for(int i = 1; i < moveUp+1; i++){
        
            if(chessBoard.boardTiles[y-(i)][x].isPiece(x, y - i, useless)){
                if(chessBoard.boardTiles[y-(i)][x].team(x, y - i, useless, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y - i][x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y - i][x]);
        
        }
        for(int i = 1; i < moveDown+1; i++){
        
            if(chessBoard.boardTiles[y+(i)][x].isPiece(x, y + i, useless)){
                if(chessBoard.boardTiles[y+(i)][x].team(x, y + i, useless, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y + i][x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y + i][x]);
        
        }
        
        while(x < 7 && y < 7){
        
            x++;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece(x, y, useless)){
                if(chessBoard.boardTiles[y][x].team(x, y, useless, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = pseudoXList.get(useless);
        y = pseudoYList.get(useless);
        while(x < 7 && y > 0){
            x++;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece(x, y, useless)){
                if(chessBoard.boardTiles[y][x].team(x, y, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = pseudoXList.get(depth);
        y = pseudoYList.get(depth);
        while(x > 0 && y > 0){
            x--;
            y--;
            if(chessBoard.boardTiles[y][x].isPiece(x, y, depth)){
                if(chessBoard.boardTiles[y][x].team(x, y, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        x = pseudoXList.get(depth);
        y = pseudoYList.get(depth);
        while(x > 0 && y < 7){
            x--;
            y++;
            if(chessBoard.boardTiles[y][x].isPiece(x, y, depth)){
                if(chessBoard.boardTiles[y][x].team(x, y, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x]);
                
                }
                break;
            }
            moveableTiles.add(chessBoard.boardTiles[y][x]);
        
        }
        
        return moveableTiles;
    
    }
    }

    public void checkPosition(){

        if(ontopofTile.y == 0 || ontopofTile.y == 7){

            isQueen = true;
            if(team == 0){

                body = kit.createImage("queenwhite.png");

            } else {

                body = kit.createImage("queenblack.png");

            }
            
            points = 9;

        }

    }

}
