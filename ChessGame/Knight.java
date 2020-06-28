import objectdraw.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
/**
 * Write a description of class Knights here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Pieces
{
    Image body;
    static Toolkit kit = Toolkit.getDefaultToolkit();
    public Knight(DrawingCanvas c, Tiles startTile, int teams){
    
        super(startTile);
        
        if(teams == 0) {
        
            body = kit.createImage("knightwhite.png");
        
        } else {
        
            body = kit.createImage("blackknight.png");
        
        }
        team = teams;
        
        points = 4;
        
        image = new VisibleImage(body, startTile.posX, startTile.posY, startTile.use, startTile.use, c);
    
    }
    public Knight(Tiles startTile, int teams){
        super(startTile);
        
        team = teams;
        
        points = 4;
    
    }
    
    public void selectPiece(){
        Board chessBoard = ontopofTile.chessBoard;
        
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        //System.out.println(x + " " + y);
        if(x-2 >= 0){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x-2].isPiece){
                
                    chessBoard.highlight(x-2, y+1);
                    
                } else if(chessBoard.boardTiles[y+1][x-2].pieceOn.team != team){
                
                    chessBoard.highlight(x-2, y+1);
                
                }                
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-2].isPiece){
                
                    chessBoard.highlight(x-2, y-1);
                    
                }else if(chessBoard.boardTiles[y-1][x-2].pieceOn.team != team){
                
                    chessBoard.highlight(x-2, y-1);
                
                } 
            }
        
        }
        if(x+2 < 8){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x+2].isPiece){
                
                    chessBoard.highlight(x+2, y+1);
                    
                }else if(chessBoard.boardTiles[y+1][x+2].pieceOn.team != team){
                
                    chessBoard.highlight(x+2, y+1);
                
                }                 
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+2].isPiece){
                
                    chessBoard.highlight(x+2, y-1);
                    
                }else if(chessBoard.boardTiles[y-1][x+2].pieceOn.team != team){
                
                    chessBoard.highlight(x+2, y-1);
                
                } 
            }
        
        }
        if(y-2 >= 0){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y-2][x+1].isPiece){
                
                    chessBoard.highlight(x+1, y-2);
                    
                }else if(chessBoard.boardTiles[y-2][x+1].pieceOn.team != team){
                
                    chessBoard.highlight(x+1, y-2);
                
                }                 
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y-2][x-1].isPiece){
                
                    chessBoard.highlight(x-1, y-2);
                    
                }else if(chessBoard.boardTiles[y-2][x-1].pieceOn.team != team){
                
                    chessBoard.highlight(x-1, y-2);
                
                } 
            }
        
        }
        if(y+2 < 8){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y+2][x+1].isPiece){
                
                    chessBoard.highlight(x+1, y+2);
                    
                } else if(chessBoard.boardTiles[y+2][x+1].pieceOn.team != team){
                
                    chessBoard.highlight(x+1, y+2);
                
                }                
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y+2][x-1].isPiece){
                
                    chessBoard.highlight(x-1, y+2);
                    
                }else if(chessBoard.boardTiles[y+2][x-1].pieceOn.team != team){
                
                    chessBoard.highlight(x-1, y+2);
                
                } 
            }
        
        }
    
    
    }
    public ArrayList<Tiles> tilesCanMove(){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        //System.out.println(x + " " + y);
        if(x-2 >= 0){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x-2].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-2]);
                    
                } else if(chessBoard.boardTiles[y+1][x-2].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-2]);
                
                }                
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-2].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-2]);
                    
                }else if(chessBoard.boardTiles[y-1][x-2].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-2]);
                
                } 
            }
        
        }
        if(x+2 < 8){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x+2].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+2]);
                    
                }else if(chessBoard.boardTiles[y+1][x+2].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+2]);
                
                }                 
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+2].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+2]);
                    
                }else if(chessBoard.boardTiles[y-1][x+2].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+2]);
                
                } 
            }
        
        }
        if(y-2 >= 0){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y-2][x+1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x+1]);
                    
                }else if(chessBoard.boardTiles[y-2][x+1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x+1]);
                
                }                 
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y-2][x-1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x-1]);
                    
                }else if(chessBoard.boardTiles[y-2][x-1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-2][ontopofTile.x-1]);
                
                } 
            }
        
        }
        if(y+2 < 8){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y+2][x+1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x+1]);
                    
                } else if(chessBoard.boardTiles[y+2][x+1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x+1]);
                
                }                
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y+2][x-1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x-1]);
                    
                }else if(chessBoard.boardTiles[y+2][x-1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+2][ontopofTile.x-1]);
                
                } 
            }
        
        }
        
        return moveableTiles;
        
    }
    public ArrayList<Tiles> tilesCanMove(int depth){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
        Board chessBoard = ontopofTile.chessBoard;
        
        int x = pseudoXList.get(depth);
        int y = pseudoYList.get(depth);
        //System.out.println(x + " " + y);
        if(x-2 >= 0){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x-2].isPiece(x-2,y+1,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x-2]);
                    
                } else if(chessBoard.boardTiles[y+1][x-2].team(x-2,y+1,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x-2]);
                
                }                
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-2].isPiece(x-2,y-1,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x-2]);
                    
                }else if(chessBoard.boardTiles[y-1][x-2].team(x-2,y-1,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x-2]);
                
                } 
            }
        
        }
        if(x+2 < 8){
            if(y + 1 < 8){            
                if(!chessBoard.boardTiles[y+1][x+2].isPiece(x+2,y+1,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x+2]);
                    
                }else if(chessBoard.boardTiles[y+1][x+2].team(x+2,y+1,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x+2]);
                
                }                 
            }
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+2].isPiece(x+2,y-1,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x+2]);
                    
                }else if(chessBoard.boardTiles[y-1][x+2].team(x+2,y-1,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x+2]);
                
                } 
            }
        
        }
        if(y-2 >= 0){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y-2][x+1].isPiece(x+1,y-2,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-2][x+1]);
                    
                }else if(chessBoard.boardTiles[y-2][x+1].team(x+1,y-2,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-2][x+1]);
                
                }                 
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y-2][x-1].isPiece(x-1,y-2,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-2][x-1]);
                    
                }else if(chessBoard.boardTiles[y-2][x-1].team(x-1,y-2,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-2][x-1]);
                
                } 
            }
        
        }
        if(y+2 < 8){
            if(x + 1 < 8){            
                if(!chessBoard.boardTiles[y+2][x+1].isPiece(x+1,y+2,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+2][x+1]);
                    
                } else if(chessBoard.boardTiles[y+2][x+1].team(x+1,y+2,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+2][x+1]);
                
                }                
            }
            if(x - 1 >= 0){
                if(!chessBoard.boardTiles[y+2][x-1].isPiece(x-1,y+2,depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+2][x-1]);
                    
                }else if(chessBoard.boardTiles[y+2][x-1].team(x-1,y+2,depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+2][x-1]);
                
                } 
            }
        
        }
        
        return moveableTiles;
        
    }
}
