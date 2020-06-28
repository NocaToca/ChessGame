
import java.util.*;
import java.awt.Image;
import java.awt.Toolkit;
import objectdraw.*;
/**
 * Write a description of class Rook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rook extends Pieces
{
    Image body;
    static Toolkit kit = Toolkit.getDefaultToolkit();
    public Rook(DrawingCanvas c, Tiles startTile, int teams){
    
        super(startTile);
        
        
        if(teams == 0){
        
            body = kit.createImage("rookwhite.png");
        
        } else {
        
            body = kit.createImage("rookblack.png");
            
        }
        team = teams;
        
        points = 5;
        
        
        image = new VisibleImage(body, startTile.posX, startTile.posY, startTile.use, startTile.use, c);
        
    
    }
    public Rook(Tiles startTile, int teams){
        super(startTile);
        
        team = teams;
        
        points = 5;
    
    }
    
    public void selectPiece(){
    
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
        
    }
    
    public void deSelectPiece(){
    
        Board chessBoard = ontopofTile.chessBoard;
        chessBoard.unHighlightAll();
    
    }
    
    public ArrayList<Tiles> tilesCanMove(){
    
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
        
        return moveableTiles;
    
    } 
    public ArrayList<Tiles> tilesCanMove(int depth){
    
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        int x = pseudoXList.get(depth);
        int y = pseudoYList.get(depth);
        //System.out.println(x);
        //System.out.println(y);
        int moveRight = 7 - x;
        int moveLeft = x;
        int moveUp = y;
        int moveDown = 7 - y; 
        //System.out.println(moveRight);
        for(int i = 1; i < moveRight+1; i++){
        
            
            if(chessBoard.boardTiles[y][x+(i)].isPiece(x+i, y, depth)){
                if(chessBoard.boardTiles[y][x+(i)].team(x+i, y, depth, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y][x + i]);
                
                }
                break;
            
            }
            //System.out.println("d");
            moveableTiles.add(chessBoard.boardTiles[y][x + i]);
        
        }
        for(int i = 1; i < moveLeft+1; i++){
        
            if(chessBoard.boardTiles[y][x-(i)].isPiece(x - i, y, depth)){
                if(chessBoard.boardTiles[y][x-(i)].team(x - i, y, depth, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y][x - i]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y][x - i]);
        
        }
        for(int i = 1; i < moveUp+1; i++){
        
            if(chessBoard.boardTiles[y-(i)][x].isPiece(x, y - i, depth)){
                if(chessBoard.boardTiles[y-(i)][x].team(x, y - i, depth, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y - i][x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y - i][x]);
        
        }
        for(int i = 1; i < moveDown+1; i++){
        
            if(chessBoard.boardTiles[y+(i)][x].isPiece(x, y + i, depth)){
                if(chessBoard.boardTiles[y+(i)][x].team(x, y + i, depth, team)){
                    
                    moveableTiles.add(chessBoard.boardTiles[y + i][x]);
                
                }
                break;
            
            }
            moveableTiles.add(chessBoard.boardTiles[y + i][x]);
        
        }
        
        return moveableTiles;
    
    }
}
