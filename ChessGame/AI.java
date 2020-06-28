import java.util.*;
import objectdraw.*;
/**
 * Write a description of class AI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AI extends Player
{
    Game tempGame;
    public AI(int teams){

        super(teams);

        isAnAI = true;

    }

    public void findLoc(Game newGame){
            ArrayList<Integer> ignoreIndexPlayer = new ArrayList<Integer>();
            ArrayList<Integer> ignoreIndexEnemy = new ArrayList<Integer>();
            
            Pieces pieceToMove = null;
            Tiles tileToMove = null;
            for(int i = 0; i < newGame.playerOne.pieces.length; i++){
                if(!newGame.playerOne.pieces[i].isDead){
                    newGame.playerOne.pieces[i].pseudoXList.set(0, newGame.playerOne.pieces[i].ontopofTile.x);
                    newGame.playerOne.pieces[i].pseudoYList.set(0, newGame.playerOne.pieces[i].ontopofTile.y);
                } else {
                
                    ignoreIndexEnemy.add(i);
                
                }
                
                if(!newGame.playerTwo.pieces[i].isDead){
                
                    newGame.playerTwo.pieces[i].pseudoXList.set(0, newGame.playerTwo.pieces[i].ontopofTile.x);
                    newGame.playerTwo.pieces[i].pseudoYList.set(0, newGame.playerTwo.pieces[i].ontopofTile.y);
                    
                } else {
                
                    ignoreIndexPlayer.add(i);
                
                }
                
        
            }
        
            int eval = 0;
            int maxEval = Integer.MIN_VALUE;
            for(int i = 0; i < newGame.playerTwo.pieces.length; i++){

                if(!ignoreIndexPlayer.contains(i) && !newGame.playerTwo.pieces[i].isDead){
                    ArrayList<Tiles> tempTiles = newGame.playerTwo.pieces[i].tilesCanMove();
                    for(int k = 0; k < tempTiles.size(); k++){

                        if(tempTiles.get(k).isPiece && tempTiles.get(k).pieceOn.team != team){
                            //killing an enemy unit
                            for(int g = 0; g < newGame.playerOne.pieces.length; g++){

                                if(tempTiles.get(k).pieceOn == newGame.playerOne.pieces[g]){

                                    ignoreIndexEnemy.add(i);
                                    break;

                                }

                            }
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, tempTiles.get(k).y);
                            
                            eval = miniMax(newGame, 0 + 1, false, ignoreIndexPlayer, ignoreIndexEnemy, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, newGame.playerTwo.pieces[i].ontopofTile.x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, newGame.playerTwo.pieces[i].ontopofTile.y);

                        } else if(!tempTiles.get(k).isPiece){
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, tempTiles.get(k).y);
                            eval = miniMax(newGame, 0 + 1, false, ignoreIndexPlayer, ignoreIndexEnemy, 4, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, newGame.playerTwo.pieces[i].ontopofTile.x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, newGame.playerTwo.pieces[i].ontopofTile.y);

                        }
                        if(eval > maxEval){
                        
                            maxEval = eval;
                            pieceToMove = newGame.playerTwo.pieces[i];
                            tileToMove = tempTiles.get(k);
                        
                        }
                    }
            
                }

            }
            if(tileToMove.isPiece){
            
                tileToMove.pieceOn.beKilled();
            
            } 
            pieceToMove.movePiece(tileToMove);
    }

    public int miniMax(Game newGame, int depth, boolean isMaximizing, ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy, int maxDepth, int alpha, int beta){

        ArrayList<Integer> ignoreIndexPlayerCopy = (ArrayList<Integer>)ignoreIndexPlayer.clone();
        ArrayList<Integer> ignoreIndexEnemyCopy = (ArrayList<Integer>)ignoreIndexEnemy.clone();
        boolean prunned = false;
        
        
        if(newGame.checkMate(ignoreIndexPlayer, ignoreIndexEnemy)){
            if(isMaximizing){
            
                return Integer.MAX_VALUE;
                
            } else {
            
                return Integer.MIN_VALUE;
            
            }

        } else if(depth == maxDepth){
        
            int score = 0;

            for(int i = 0; i < newGame.playerOne.pieces.length; i++){

                if(ignoreIndexEnemy.contains(i)){

                    score += newGame.playerOne.pieces[i].points;

                }

            }
            for(int i = 0; i < newGame.playerTwo.pieces.length; i++){

                if(ignoreIndexPlayer.contains(i)){

                    score -= newGame.playerTwo.pieces[i].points;

                }

            }
            System.out.println(score);
            return score;
        
        }
        
        int index = 0;

        if(isMaximizing){

            int eval = Integer.MIN_VALUE;
            int maxEval = Integer.MIN_VALUE;
            for(int i = 0; i < newGame.playerTwo.pieces.length; i++){
                if(prunned){
                break;
                }

                if(!ignoreIndexPlayer.contains(i) && !newGame.playerTwo.pieces[i].isDead){
                    ArrayList<Tiles> tempTiles = newGame.playerTwo.pieces[i].tilesCanMove(depth-1);
                    for(int k = 0; k < tempTiles.size(); k++){

                        if(tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1) && tempTiles.get(k).team(tempTiles.get(k).x, tempTiles.get(k).y, depth-1, team)){
                            //killing an enemy unit
                            for(int g = 0; g < newGame.playerOne.pieces.length; g++){

                                if(tempTiles.get(k).x == newGame.playerOne.pieces[g].pseudoXList.get(depth-1) && tempTiles.get(k).y == newGame.playerOne.pieces[g].pseudoYList.get(depth-1)){

                                    ignoreIndexEnemyCopy.add(g);
                                    index = g;
                                    break;

                                }

                            }
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            
                            eval = miniMax(newGame, depth + 1, false, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, newGame.playerTwo.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, newGame.playerTwo.pieces[i].pseudoYList.get(depth-1));
                            
                            ignoreIndexEnemyCopy.remove(index);

                        } else if(!tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1)){
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            eval = miniMax(newGame, depth + 1, false, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, newGame.playerTwo.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, newGame.playerTwo.pieces[i].pseudoYList.get(depth-1));

                        }
                        //Integer.max(eval, maxEval);
                        if(eval > maxEval){
                            maxEval = eval;
                        }
                        if(eval > alpha){
                        
                            alpha = eval;
                        
                        }
                        if(beta <= alpha){
                            System.out.println("Pruned");
                            prunned = true;
                            break;
                        }
                    }
            
                }

            }
            System.out.println("Max Eval: " + maxEval);
            return maxEval;

        } else {
        
            int eval = Integer.MAX_VALUE;
            int minEval = Integer.MAX_VALUE;
            for(int i = 0; i < newGame.playerOne.pieces.length; i++){
                if(prunned){ break; }
                if(!ignoreIndexEnemy.contains(i) && !newGame.playerOne.pieces[i].isDead){
                    ArrayList<Tiles> tempTiles = newGame.playerOne.pieces[i].tilesCanMove(depth-1);
                    for(int k = 0; k < tempTiles.size(); k++){

                        if(tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1) && tempTiles.get(k).team(tempTiles.get(k).x, tempTiles.get(k).y, depth-1, team)){
                            //killing an enemy unit
                            for(int g = 0; i < newGame.playerTwo.pieces.length; g++){

                                if(tempTiles.get(k).pieceOn == newGame.playerTwo.pieces[i]){

                                    ignoreIndexPlayerCopy.add(g);
                                    index = g;
                                    break;

                                }

                            }
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            
                            eval = miniMax(newGame, depth + 1, true, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, newGame.playerOne.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, newGame.playerOne.pieces[i].pseudoYList.get(depth-1));
                            
                            ignoreIndexPlayerCopy.remove(index);
                            

                        } else if(!tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1)){
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            eval = miniMax(newGame, depth + 1, true, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, newGame.playerOne.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, newGame.playerOne.pieces[i].pseudoYList.get(depth-1));

                        }
                        //Integer.min(eval, minEval);
                        if(eval < minEval){
                            minEval = eval;
                        }
                        if(eval < beta){
                        
                            beta = eval;
                        
                        }
                        if(beta <= alpha){
                            System.out.println("Pruned");
                            prunned = true;
                            break;
                        }
                    }
            
                }

            }
            System.out.println("Min Eval: " + minEval);
            return minEval;
        }
    }
}
