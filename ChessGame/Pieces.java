import java.awt.Image;
import java.awt.Toolkit;
import objectdraw.*;
import java.util.*;
/**
 * Write a description of class Pieces here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pieces
{
    int team;
    VisibleImage image;
    static Toolkit kit = Toolkit.getDefaultToolkit();
    Tiles ontopofTile;
    int points;
    boolean isDead;
    ArrayList<Integer> pseudoXList = new ArrayList<Integer>(50);
    ArrayList<Integer> pseudoYList = new ArrayList<Integer>(50);
    
    public Pieces(Tiles startTile){
    
        startTile.pieceOn = this;
        startTile.isPiece = true;
        ontopofTile = startTile;
        isDead = false;
        for(int i = 0; i < 50; i++){
        
            pseudoXList.add(1);
            pseudoYList.add(1);
        
        }
    
    }
    
    public void movePiece(Tiles tile){
        
        ontopofTile.isPiece = false;
        ontopofTile.pieceOn = null;
        
        ontopofTile = tile;
        ontopofTile.isPiece = true;
        ontopofTile.pieceOn = this;
        image.moveTo(tile.posX, tile.posY);
    
    
    }
    public void mockMove(Tiles tile){
    
        if(ontopofTile != null){
        
            ontopofTile.isPiece = false;
            ontopofTile.pieceOn = null;
        
        }
        
        
        ontopofTile = tile;
        ontopofTile.isPiece = true;
        ontopofTile.pieceOn = this;
    
    }
    
    public void kill(Pieces piece){
     
        
        
    }
    
    public void beKilled(){
    
        image.moveTo(1000, 1000);
        ontopofTile.pieceOn = null;
        ontopofTile.isPiece = false;
        ontopofTile = null;
        isDead = true;
        System.out.println("Ded");
    
    }
    
    public void mockKill(){
    
        ontopofTile.pieceOn = null;
        ontopofTile.isPiece = false;
        ontopofTile = null;
        isDead = true;
        System.out.println("Mock Ded");
    
    }
    
    public void selectPiece(){
    
    
    
    }
    
    public void deSelectPiece(){
    
        Board chessBoard = ontopofTile.chessBoard;
        chessBoard.unHighlightAll();
    
    }
    
    public ArrayList<Tiles> tilesCanMove(){
    
        return null;
    
    
    }
    
    public ArrayList<Tiles> tilesCanMove(int useless){
    
        return null;
    
    
    }
    
    public boolean matches(Pieces piece){
    
        if(piece.team == team && piece.points == points && piece.ontopofTile.x == ontopofTile.x && piece.ontopofTile.y == ontopofTile.y){
            //System.out.println("bee7");
            return true;
        
        }
        //System.out.println("bee8");
        return false;
    
    }
    
    
    
    
    
    
}
