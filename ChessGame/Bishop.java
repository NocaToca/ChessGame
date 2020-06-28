import objectdraw.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bishop extends Pieces
{
    Image body;
    static Toolkit kit = Toolkit.getDefaultToolkit();
    public Bishop(DrawingCanvas c, Tiles startTile, int teams){
        super(startTile);
        
        if(teams == 0){
        
            body = kit.createImage("bishop1.png");
        
        } else {
        
            body = kit.createImage("blackbishop.png");
        
        }
        team = teams;
        
        points = 3;
        
        image = new VisibleImage(body, startTile.posX, startTile.posY, startTile.use, startTile.use, c);

    
    }
    public Bishop(Tiles startTile, int teams){
        super(startTile);
        
        team = teams;
        
        points = 3;
    
    }
    public void selectPiece(){
    
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        
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
    
    public ArrayList<Tiles> tilesCanMove(){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        
        
        
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
    public ArrayList<Tiles> tilesCanMove(int depth){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        
        Board chessBoard = ontopofTile.chessBoard;
        int x = pseudoXList.get(depth);
        int y = pseudoYList.get(depth);
        
        
        
        while(x < 7 && y < 7){
        
            x++;
            y++;
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
        while(x < 7 && y > 0){
            x++;
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
