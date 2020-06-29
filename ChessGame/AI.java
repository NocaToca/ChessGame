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
    long calls;
    int callsRan;
    double mark;
    double percentComplete;
    double guess;
    public AI(int teams){

        super(teams);

        isAnAI = true;

    }

    public void findLoc(Game newGame){
            ArrayList<Integer> ignoreIndexPlayer = new ArrayList<Integer>();
            ArrayList<Integer> ignoreIndexEnemy = new ArrayList<Integer>();
            
            Pieces pieceToMove = null;
            Tiles tileToMove = null;
            int depth = 7;
            calculateCalls(depth);
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
                            
                            eval = miniMax(newGame, 0 + 1, false, ignoreIndexPlayer, ignoreIndexEnemy, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, newGame.playerTwo.pieces[i].ontopofTile.x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, newGame.playerTwo.pieces[i].ontopofTile.y);

                        } else if(!tempTiles.get(k).isPiece){
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, tempTiles.get(k).y);
                            eval = miniMax(newGame, 0 + 1, false, ignoreIndexPlayer, ignoreIndexEnemy, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                            newGame.playerTwo.pieces[i].pseudoXList.set(0, newGame.playerTwo.pieces[i].ontopofTile.x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(0, newGame.playerTwo.pieces[i].ontopofTile.y);

                        }
                        if(eval > maxEval){
                            if(newGame.isPlayerInCheck(this)){
                                if(newGame.moveOutOfCheck(newGame.playerTwo.pieces[i], tempTiles.get(k), this)){
                                
                                    maxEval = eval;
                                    pieceToMove = newGame.playerTwo.pieces[i];
                                    tileToMove = tempTiles.get(k);
                                
                                    
                                }
                                
                                
                            } else {
                            
                                maxEval = eval;
                                pieceToMove = newGame.playerTwo.pieces[i];
                                tileToMove = tempTiles.get(k);
                            
                            }
                            
                        
                        }
                    }
            
                }

            }
            if(tileToMove.isPiece){
            
                tileToMove.pieceOn.beKilled();
            
            } 
            pieceToMove.movePiece(tileToMove);
    }
    
    //Passes in the game, the depth, if the player is maximizing, the "dead" players, the max depth and the alpha and beta values
    public int miniMax(Game newGame, int depth, boolean isMaximizing, ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy, int maxDepth, int alpha, int beta){

        //Clones the two "dead" pieces array lists, and then initializes the prunned boolean
        ArrayList<Integer> ignoreIndexPlayerCopy = (ArrayList<Integer>)ignoreIndexPlayer.clone();
        ArrayList<Integer> ignoreIndexEnemyCopy = (ArrayList<Integer>)ignoreIndexEnemy.clone();
        boolean prunned = false;
        
        //Before running anything else, checks to make sure the game is over
        if(newGame.checkMate(ignoreIndexPlayer, ignoreIndexEnemy, depth)){
            percentComplete();
            //If the enemy is in check, we return the max possible value. If the AI is in check we return the lowest
            if(isMaximizing){
            
                return Integer.MAX_VALUE; 
                
            } else {
            
                return Integer.MIN_VALUE;
            
            }

        } else if(depth == maxDepth){
            percentComplete();
            //If the depth is the maxDepth, we exit the recursion, assessing the "dead" pieces and subtracting score if they're on the AI's team and adding otherwise
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
            //System.out.println("Score :" + score);
            return score;
        
        }
        
        int index = -1; //initializing the index variable

        if(isMaximizing){

            //if it's the maximizing player's turn, we set the eval and maxEval to the lowest possible numbers so they have to be changed
            int eval = Integer.MIN_VALUE;
            int maxEval = Integer.MIN_VALUE;
            for(int i = 0; i < newGame.playerTwo.pieces.length; i++){
                if(prunned){
                break; //if this run is prunned, we just break the loop
                }

                //We check to see if the piece is either dead or "dead"
                if(!ignoreIndexPlayer.contains(i) && !newGame.playerTwo.pieces[i].isDead){
                    ArrayList<Tiles> tempTiles = newGame.playerTwo.pieces[i].tilesCanMove(depth-1); //getting the tiles the piece can move
                    for(int k = 0; k < tempTiles.size(); k++){

                        //We check to see if the tile is a piece, and if it is we check to see if they're on different teams
                        //System.out.println(team);
                        if(tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1) && tempTiles.get(k).pieceOn(tempTiles.get(k), depth-1).team != team){
                            //killing an enemy unit
                            for(int g = 0; g < newGame.playerOne.pieces.length; g++){

                                //if the piece is "in the same spot" as the tile, we add it to the "dead" list
                                if(tempTiles.get(k).x == newGame.playerOne.pieces[g].pseudoXList.get(depth-1) && tempTiles.get(k).y == newGame.playerOne.pieces[g].pseudoYList.get(depth-1)){
                                    //System.out.println("Hello");
                                    ignoreIndexEnemyCopy.add(g);
                                    index = g; //set the index to g so we can remove it from the "dead" list
                                    break;

                                }

                            }
                            //We then set the pieces "location" to the tile location
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            
                            //And then run it through with that "location"
                            eval = miniMax(newGame, depth + 1, false, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            //Then we reset the location back to the previous location
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, newGame.playerTwo.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, newGame.playerTwo.pieces[i].pseudoYList.get(depth-1));
                            
                            //and we also remove the dead unit
                            ignoreIndexEnemyCopy.remove(new Integer(index));

                        } else if(!tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1)){
                            //Same thing as if above, except no killing a unit
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            
                            eval = miniMax(newGame, depth + 1, false, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerTwo.pieces[i].pseudoXList.set(depth, newGame.playerTwo.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerTwo.pieces[i].pseudoYList.set(depth, newGame.playerTwo.pieces[i].pseudoYList.get(depth-1));

                        }
                        //Integer.max(eval, maxEval);
                        
                        //doing the minimaxing stuff
                        if(eval > maxEval){
                            maxEval = eval;
                        }
                        if(eval > alpha){
                        
                            alpha = eval;
                        
                        }
                        if(beta <= alpha){
                            //System.out.println("Pruned");
                            prunned = true;
                            break;
                        }
                    }
            
                }

            }
            //System.out.println("Max Eval: " + maxEval);
            return maxEval;

        } else {
        
            //The maxing algoritm, but opposite.
            int eval = Integer.MAX_VALUE;
            int minEval = Integer.MAX_VALUE;
            for(int i = 0; i < newGame.playerOne.pieces.length; i++){
                if(prunned){ break; }
                if(!ignoreIndexEnemy.contains(i) && !newGame.playerOne.pieces[i].isDead){
                    ArrayList<Tiles> tempTiles = newGame.playerOne.pieces[i].tilesCanMove(depth-1);
                    //System.out.println("tempTiles.size(): " + tempTiles.size());
                    //System.out.println("PieceWeight: " + newGame.playerOne.pieces[i].points);
                    for(int k = 0; k < tempTiles.size(); k++){
                        //System.out.println(team);
                        if(tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1) && tempTiles.get(k).pieceOn(tempTiles.get(k), depth-1).team == team){
                            //killing an enemy unit
                            for(int g = 0; g < newGame.playerOne.pieces.length; g++){

                                if(tempTiles.get(k).pieceOn(tempTiles.get(k), depth-1) == newGame.playerTwo.pieces[g]){
                                    
                                    ignoreIndexPlayerCopy.add(g);
                                    index = g;
                                    //System.out.println("G: " + g);
                                    //return 108349085;
                                    break;

                                }
                                

                            }
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            
                            eval = miniMax(newGame, depth + 1, true, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, newGame.playerOne.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, newGame.playerOne.pieces[i].pseudoYList.get(depth-1));
                            
                            ignoreIndexPlayerCopy.remove(new Integer(index));
                            //return 108349085;
                            

                        } else if(!tempTiles.get(k).isPiece(tempTiles.get(k).x, tempTiles.get(k).y, depth-1)){
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, tempTiles.get(k).x);
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, tempTiles.get(k).y);
                            eval = miniMax(newGame, depth + 1, true, ignoreIndexPlayerCopy, ignoreIndexEnemyCopy, maxDepth, alpha, beta);
                            
                            newGame.playerOne.pieces[i].pseudoXList.set(depth, newGame.playerOne.pieces[i].pseudoXList.get(depth-1));
                            newGame.playerOne.pieces[i].pseudoYList.set(depth, newGame.playerOne.pieces[i].pseudoYList.get(depth-1));
                            //return 108349085;
                        }
                        //Integer.min(eval, minEval);
                        if(eval < minEval){
                            minEval = eval;
                        }
                        if(eval < beta){
                        
                            beta = eval;
                        
                        }
                        if(beta <= alpha){
                            //System.out.println("Pruned");
                            prunned = true;
                            break;
                        }
                    }
            
                }

            }
            //System.out.println("Min Eval: " + minEval);
            return minEval;
        }
    }
    
    public void calculateCalls(int depth){
        callsRan = 0;
        percentComplete = 0;
        int first = 1;
        mark = .05;
        guess = 0;
        
        calls = recursive(depth, first);
        calls = (long)Math.pow(calls, 32);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(calls);
    
    }
    private int recursive(int depth, int number){
        
        if(depth == 0){
        
            return number;
        
        } else {
        
            number = recursive(depth-1, number + number * 2);
        
            return number;
        }
        
    }
    public void percentComplete(){
    
        callsRan++;
        double percent = (double)callsRan/(double)calls;
        percent *= 100;
        if((int)percent % 500000000 == 0){
        
            percentComplete += .00005;
            //guess += .00308;
            if(percentComplete >= mark){
            
            guess += .1584276;
            System.out.println("Loading move... around " + guess + "% done.");
            mark += .05;
            }
            
        }
    
    }
}
