import objectdraw.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.*;
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Pieces
{
    Image body;
    static Toolkit kit = Toolkit.getDefaultToolkit();
    public King(DrawingCanvas c, Tiles startTile, int teams){
    
        super(startTile);
        
        if(teams == 0){
        
            body = kit.createImage("kingwhite.png");
            
        } else {
        
            body = kit.createImage("blackking.png");
            
        }
        
        team = teams;
        
        points = 10000000;
        
        image = new VisibleImage(body, startTile.posX, startTile.posY, startTile.use, startTile.use, c);
    
    
    }
    public King(Tiles startTile, int teams){
        super(startTile);
        
        team = teams;
        
        points = 10000000;
    
    }
    
    public void selectPiece(){
    
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        
        if(x - 1 >= 0){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-1].isPiece){
                
                    chessBoard.highlight(x-1, y-1);
                
                } else if(chessBoard.boardTiles[y-1][x-1].pieceOn.team != team){
                
                    chessBoard.highlight(x-1, y-1);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x-1].isPiece){
                
                    chessBoard.highlight(x-1, y+1);
                
                } else if(chessBoard.boardTiles[y+1][x-1].pieceOn.team != team){
                
                    chessBoard.highlight(x-1, y+1);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x-1].isPiece){
                
                    chessBoard.highlight(x-1, y);
                
                } else if(chessBoard.boardTiles[y][x-1].pieceOn.team != team){
                
                    chessBoard.highlight(x-1, y);
                
                }
        
        }
        
        if(x + 1 < 8){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+1].isPiece){
                
                    chessBoard.highlight(x+1, y-1);
                
                } else if(chessBoard.boardTiles[y-1][x+1].pieceOn.team != team){
                
                    chessBoard.highlight(x+1, y-1);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x+1].isPiece){
                
                    chessBoard.highlight(x+1, y+1);
                
                } else if(chessBoard.boardTiles[y+1][x+1].pieceOn.team != team){
                
                    chessBoard.highlight(x+1, y+1);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x+1].isPiece){
                
                    chessBoard.highlight(x+1, y);
                
                } else if(chessBoard.boardTiles[y][x+1].pieceOn.team != team){
                
                    chessBoard.highlight(x+1, y);
                
                }
        
        }
        if(y - 1 >= 0){
        
            if(!chessBoard.boardTiles[y-1][x].isPiece){
                
                    chessBoard.highlight(x, y-1);
                
            } else if(chessBoard.boardTiles[y-1][x].pieceOn.team != team){
                
                    chessBoard.highlight(x, y-1);
                
                }
        
        }
        if(y + 1 < 8){
        
            if(!chessBoard.boardTiles[y+1][x].isPiece){
                
                    chessBoard.highlight(x, y+1);
                
            } else if(chessBoard.boardTiles[y+1][x].pieceOn.team != team){
                
                    chessBoard.highlight(x, y+1);
                
                }
        
        }
        
        
    
    }
    
    public ArrayList<Tiles> tilesCanMove(){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
    
        Board chessBoard = ontopofTile.chessBoard;
        int x = ontopofTile.x;
        int y = ontopofTile.y;
        
        
        
        if(x - 1 >= 0){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1]);
                
                } else if(chessBoard.boardTiles[y-1][x-1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x-1]);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x-1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1]);
                
                } else if(chessBoard.boardTiles[y+1][x-1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x-1]);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x-1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x-1]);
                
                } else if(chessBoard.boardTiles[y][x-1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x-1]);
                
                }
        
        }
        
        if(x + 1 < 8){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1]);
                
                } else if(chessBoard.boardTiles[y-1][x+1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x+1]);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x+1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1]);
                
                } else if(chessBoard.boardTiles[y+1][x+1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x+1]);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x+1].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x+1]);
                
                } else if(chessBoard.boardTiles[y][x+1].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y][ontopofTile.x+1]);
                
                }
        
        }
        if(y - 1 >= 0){
        
            if(!chessBoard.boardTiles[y-1][x].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x]);
                
            } else if(chessBoard.boardTiles[y-1][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y-1][ontopofTile.x]);
                
                }
        
        }
        if(y + 1 < 8){
        
            if(!chessBoard.boardTiles[y+1][x].isPiece){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x]);
                
            } else if(chessBoard.boardTiles[y+1][x].pieceOn.team != team){
                
                    moveableTiles.add(chessBoard.boardTiles[ontopofTile.y+1][ontopofTile.x]);
                
                }
        
        }
        
        return moveableTiles;
    
    }
    public ArrayList<Tiles> tilesCanMove(int depth){
        ArrayList<Tiles> moveableTiles = new ArrayList<Tiles>();
    
        Board chessBoard = ontopofTile.chessBoard;
        int x = pseudoXList.get(depth);
        int y = pseudoYList.get(depth);
        
        
        
        if(x - 1 >= 0){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x-1].isPiece(x-1, y-1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x-1]);
                
                } else if(chessBoard.boardTiles[y-1][x-1].team(x-1, y-1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x-1]);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x-1].isPiece(x-1, y+1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x-1]);
                
                } else if(chessBoard.boardTiles[y+1][x-1].team(x-1, y+1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x-1]);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x-1].isPiece(x-1, y, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x-1]);
                
                } else if(chessBoard.boardTiles[y][x-1].team(x-1, y, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x-1]);
                
                }
        
        }
        
        if(x + 1 < 8){
            if(y - 1 >= 0){
                if(!chessBoard.boardTiles[y-1][x+1].isPiece(x+1, y-1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x+1]);
                
                } else if(chessBoard.boardTiles[y-1][x+1].team(x+1, y-1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x+1]);
                
                }
            
            }
            if(y + 1 < 8){
                if(!chessBoard.boardTiles[y+1][x+1].isPiece(x+1, y+1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x+1]);
                
                } else if(chessBoard.boardTiles[y+1][x+1].team(x+1, y+1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x+1]);
                
                }
            
            }
            if(!chessBoard.boardTiles[y][x+1].isPiece(x+1, y, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x+1]);
                
                } else if(chessBoard.boardTiles[y][x+1].team(x+1, y, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y][x+1]);
                
                }
        
        }
        if(y - 1 >= 0){
        
            if(!chessBoard.boardTiles[y-1][x].isPiece(x, y-1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x]);
                
            } else if(chessBoard.boardTiles[y-1][x].team(x, y-1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y-1][x]);
                
                }
        
        }
        if(y + 1 < 8){
        
            if(!chessBoard.boardTiles[y+1][x].isPiece(x, y+1, depth)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x]);
                
            } else if(chessBoard.boardTiles[y+1][x].team(x, y+1, depth, team)){
                
                    moveableTiles.add(chessBoard.boardTiles[y+1][x]);
                
                }
        
        }
        
        return moveableTiles;
    
    }
}
