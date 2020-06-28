
import java.util.*;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    Player playerOne;
    Player playerTwo;
    Board board;
    int p1Score;
    int p2Score;
    int playerTurn;
    Tiles [][] previousTiles;
    Pawn [] pawns;
    Rook [] rooks;
    Knight [] knights;
    King [] kings;
    Bishop [] bishops;
    Queen [] queens;
    public Game(Player p1, Player p2, Board chessBoard){

        board = chessBoard;
        previousTiles = new Tiles[8][8];
        previousTiles = board.boardTiles.clone();

        p1Score = 0;
        p2Score = 0;

        playerTurn = 0;

        pawns = board.pawns;
        rooks = board.rooks;
        knights = board.knights;
        kings = board.kings;
        bishops = board.bishops;
        queens = board.queens;

        for(int i = 0; i < 8; i++){

            p1.pawns[i] = pawns[i];

        }
        for(int i = 8; i < 16; i++){

            p2.pawns[i-8] = pawns[i];

        }
        p1.rooks[0] = rooks[0];
        p1.rooks[1] = rooks[1];
        p2.rooks[0] = rooks[2];
        p2.rooks[1] = rooks[3];

        p1.knights[0] = knights[0];
        p1.knights[1] = knights[1];
        p2.knights[0] = knights[2];
        p2.knights[1] = knights[3];

        p1.king = kings[0];
        p2.king = kings[1];

        p1.bishops[0] = bishops[0];
        p1.bishops[1] = bishops[1];
        p2.bishops[0] = bishops[2];
        p2.bishops[1] = bishops[3];

        p1.queen = queens[0];
        p2.queen = queens[1];

        p1.initializePieces();
        p2.initializePieces();
        playerOne = p1;
        playerTwo = p2;

    }

    private void update(Player player, int points){

        player.score += points;
        playerTurn = (playerTurn + 1) % 2;
        if(playerTurn == 0){
            if(playerOne.isAnAI){

                //playerOne.findLoc();
                //updateMove(playerOne);

            }

        } else if(playerTwo.isAnAI){

            //playerTwo.findLoc(this);
            //updateMove(playerTwo);

        }

    }
    public void updateMove(Player player){
        update(player, 0);

    }

    public void updateMove(Player player, Pieces killedPiece){

        update(player, killedPiece.points);

    }

    public boolean isPlayerInCheck(Player player){

        Player temp;
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        ArrayList<Tiles> assessingTiles = new ArrayList<Tiles>();
        if(player.team == 0){

            temp = playerTwo;

        } else {

            temp = playerOne;

        }

        //System.out.println("Other team: " + temp.team);
        //lSystem.out.println("Your team: " + player.team);
        for(int i = 0; i < temp.pieces.length; i++){

            //System.out.println(temp.pieces[i].isDead);
            if(!temp.pieces[i].isDead){

                tempTiles = temp.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!assessingTiles.contains(tempTiles.get(k))){

                        assessingTiles.add(tempTiles.get(k));

                    }

                }

            }

        }

        System.out.println(assessingTiles.size());
        for(int i = 0; i < assessingTiles.size(); i++){

            //Tiles tempfg = assessingTiles.get(i);
            //assessingTiles.get(i).highlight();
            if(assessingTiles.get(i) == player.king.ontopofTile){

                System.out.println("Check!");
                return true;

            }
        }
        return false;

    }

    public boolean moveOutOfCheck(Pieces selectedPiece, Tiles selectedTile, Player player){
        selectedPiece.deSelectPiece();
        Tiles tempTile = selectedPiece.ontopofTile;
        Pieces tempPiece = selectedTile.pieceOn;

        //selectedPiece.deSelectPiece();
        selectedPiece.ontopofTile.isPiece = false;
        selectedPiece.ontopofTile.pieceOn = null;
        selectedPiece.ontopofTile = selectedTile;

        if(selectedTile.isPiece){
            selectedTile.pieceOn.isDead = true;
        }
        //selectedPiece.movePiece(selectedTile);
        selectedPiece.movePiece(selectedTile);
        selectedTile.pieceOn = selectedPiece;
        selectedTile.isPiece = true;

        Player temp;
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        ArrayList<Tiles> assessingTiles = new ArrayList<Tiles>();
        if(player.team == 0){

            temp = playerTwo;

        } else {

            temp = playerOne;

        }

        System.out.println("Other team: " + temp.team);
        System.out.println("Your team: " + player.team);
        for(int i = 0; i < temp.pieces.length; i++){

            System.out.println(temp.pieces[i].isDead);
            if(!temp.pieces[i].isDead){

                tempTiles = temp.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!assessingTiles.contains(tempTiles.get(k))){

                        assessingTiles.add(tempTiles.get(k));

                    }

                }

            }

        }

        //System.out.println(assessingTiles.size());
        for(int i = 0; i < assessingTiles.size(); i++){

            //Tiles tempfg = assessingTiles.get(i);
            //assessingTiles.get(i).highlight();
            if(assessingTiles.get(i) == player.king.ontopofTile){

                System.out.println("Check!");
                selectedPiece.movePiece(tempTile);
                if(selectedTile.isPiece){

                    selectedTile.pieceOn.isDead = false;

                }

                return false;

            }
        }

        if(selectedTile.isPiece){

            selectedTile.pieceOn.isDead = false;

        }

        selectedPiece.ontopofTile.pieceOn = null;

        
        selectedPiece.ontopofTile.isPiece = false;
        selectedPiece.ontopofTile = tempTile;
        selectedTile.pieceOn = tempPiece;
        selectedPiece.ontopofTile.isPiece = true;
        selectedPiece.ontopofTile.pieceOn = selectedPiece;
        selectedPiece.movePiece(tempTile);
        return true;

    }

    public boolean checkMate(){

        if(checkMate(playerOne) || checkMate(playerTwo)){

            return true;

        }
        return false;

    }

    public boolean checkMate(Player player){

        King kindInCheck = player.king;
        Player otherPlayer;
        if(player != playerOne){

            otherPlayer = playerOne;

        } else {

            otherPlayer = playerTwo;

        }

        ArrayList<Tiles> tilesUnitCanMove = new ArrayList<Tiles>();

        ArrayList<Tiles> tilesTheEnemyCanMove = new ArrayList<Tiles>();

        Pieces [] enemyUnits = otherPlayer.pieces;
        Pieces [] yourUnits = player.pieces;

        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();

        for(int i = 0; i < enemyUnits.length; i++){
            
            if(!enemyUnits[i].isDead){

                tempTiles = enemyUnits[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!tilesTheEnemyCanMove.contains(tempTiles.get(k))){

                        tilesTheEnemyCanMove.add(tempTiles.get(k));

                    }

                }

            }

        }
        for(int i = 0; i < yourUnits.length; i++){

            if(!yourUnits[i].isDead){
                tempTiles = yourUnits[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(tempTiles.get(k).isPiece && tempTiles.get(k).pieceOn.team != player.team){

                        ArrayList<Tiles> checkingTiles = editTiles(tilesTheEnemyCanMove, tempTiles.get(k).pieceOn, otherPlayer);
                        for(int g = 0; g < checkingTiles.size(); g++){

                            if(checkingTiles.get(g).pieceOn != player.king && g == checkingTiles.size()-1){

                                return false;

                            } else if(checkingTiles.get(g).pieceOn == player.king){

                                break;

                            }

                        }

                    } else if(!tempTiles.get(k).isPiece){

                        ArrayList<Tiles> checkingTiles = editTiles(yourUnits[i], tilesTheEnemyCanMove, otherPlayer, tempTiles.get(k));
                        for(int g = 0; g < checkingTiles.size(); g++){

                            if(checkingTiles.get(g).pieceOn != player.king && g == checkingTiles.size()-1){

                                return false;

                            } else if(checkingTiles.get(g).pieceOn == player.king){

                                break;

                            }

                        }

                    }

                }

            }
        
        }

        //System.out.println("Check mate!");
        playerTurn = -1;
        //otherPlayer.score += 1000;
        //player.score -= 100;

        return true;


    }

    public ArrayList<Tiles> editTiles(ArrayList<Tiles> currentTiles, Pieces pieceRemove, Player playerPieceBelongsTo){

        ArrayList<Tiles> newList = new ArrayList<Tiles>();
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        int ignoreIndex = 0;
        for(int i = 0; i < playerPieceBelongsTo.pieces.length; i++){

            if(pieceRemove == playerPieceBelongsTo.pieces[i]){

                ignoreIndex = i;
                break;

            }

        }
        for(int i = 0; i < playerPieceBelongsTo.pieces.length; i++){

            if(!playerPieceBelongsTo.pieces[i].isDead && i != ignoreIndex){

                tempTiles = playerPieceBelongsTo.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!newList.contains(tempTiles.get(k))){

                        newList.add(tempTiles.get(k));

                    }

                }

            }

        }

        return newList;

    }

    public ArrayList<Tiles> editTiles(Pieces movedPiece, ArrayList<Tiles> currentTiles, Player otherPlayer, Tiles tileToMoveTo){

        Tiles tempTile = movedPiece.ontopofTile;
        movedPiece.mockMove(tileToMoveTo);
        ArrayList<Tiles> newList = new ArrayList<Tiles>();
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        for(int i = 0; i < otherPlayer.pieces.length; i++){

            if(!otherPlayer.pieces[i].isDead){

                tempTiles = otherPlayer.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!newList.contains(tempTiles.get(k))){

                        newList.add(tempTiles.get(k));

                    }

                }

            }

        }
        movedPiece.mockMove(tempTile);
        return newList;

    }

    public Board returnFakeBoard(Pieces movingPiece, Tiles editedTile){

        Board fakeBoard = board.copy();

       

        return fakeBoard;
    }
    
    public boolean checkMate(ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy, Player player){

        King kindInCheck = player.king;
        Player otherPlayer;
        if(player != playerOne){

            otherPlayer = playerOne;

        } else {

            otherPlayer = playerTwo;

        }

        ArrayList<Tiles> tilesUnitCanMove = new ArrayList<Tiles>();

        ArrayList<Tiles> tilesTheEnemyCanMove = new ArrayList<Tiles>();

        Pieces [] enemyUnits = otherPlayer.pieces;
        Pieces [] yourUnits = player.pieces;

        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();

        for(int i = 0; i < enemyUnits.length; i++){
            
            if(!enemyUnits[i].isDead && !ignoreIndexEnemy.contains(i)){

                tempTiles = enemyUnits[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!tilesTheEnemyCanMove.contains(tempTiles.get(k))){

                        tilesTheEnemyCanMove.add(tempTiles.get(k));

                    }

                }

            }

        }
        for(int i = 0; i < yourUnits.length; i++){

            if(!yourUnits[i].isDead && !ignoreIndexPlayer.contains(i)){
                tempTiles = yourUnits[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(tempTiles.get(k).isPiece && tempTiles.get(k).pieceOn.team != player.team){

                        ArrayList<Tiles> checkingTiles = editTiles(tilesTheEnemyCanMove, tempTiles.get(k).pieceOn, otherPlayer, ignoreIndexPlayer, ignoreIndexEnemy);
                        for(int g = 0; g < checkingTiles.size(); g++){

                            if(checkingTiles.get(g).pieceOn != player.king && g == checkingTiles.size()-1){

                                return false;

                            } else if(checkingTiles.get(g).pieceOn == player.king){

                                break;

                            }

                        }

                    } else if(!tempTiles.get(k).isPiece){

                        ArrayList<Tiles> checkingTiles = editTiles(yourUnits[i], tilesTheEnemyCanMove, otherPlayer, tempTiles.get(k), ignoreIndexPlayer, ignoreIndexEnemy);
                        for(int g = 0; g < checkingTiles.size(); g++){

                            if(checkingTiles.get(g).pieceOn != player.king && g == checkingTiles.size()-1){

                                return false;

                            } else if(checkingTiles.get(g).pieceOn == player.king){

                                break;

                            }

                        }

                    }

                }

            }
        
        }

       

        return true;


    }

    public ArrayList<Tiles> editTiles(ArrayList<Tiles> currentTiles, Pieces pieceRemove, Player playerPieceBelongsTo, ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy){

        ArrayList<Tiles> newList = new ArrayList<Tiles>();
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        int ignoreIndex = 0;
        for(int i = 0; i < playerPieceBelongsTo.pieces.length; i++){

            if(pieceRemove == playerPieceBelongsTo.pieces[i]){

                ignoreIndexEnemy.add(i);
                break;

            }

        }
        for(int i = 0; i < playerPieceBelongsTo.pieces.length; i++){

            if(!playerPieceBelongsTo.pieces[i].isDead && !ignoreIndexEnemy.contains(i)){

                tempTiles = playerPieceBelongsTo.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!newList.contains(tempTiles.get(k))){

                        newList.add(tempTiles.get(k));

                    }

                }

            }

        }

        return newList;

    }

    public ArrayList<Tiles> editTiles(Pieces movedPiece, ArrayList<Tiles> currentTiles, Player otherPlayer, Tiles tileToMoveTo, ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy){

        Tiles tempTile = movedPiece.ontopofTile;
        movedPiece.mockMove(tileToMoveTo);
        ArrayList<Tiles> newList = new ArrayList<Tiles>();
        ArrayList<Tiles> tempTiles = new ArrayList<Tiles>();
        for(int i = 0; i < otherPlayer.pieces.length; i++){

            if(!otherPlayer.pieces[i].isDead && !ignoreIndexEnemy.contains(i)){

                tempTiles = otherPlayer.pieces[i].tilesCanMove();
                for(int k = 0; k < tempTiles.size(); k++){

                    if(!newList.contains(tempTiles.get(k))){

                        newList.add(tempTiles.get(k));

                    }

                }

            }

        }
        movedPiece.mockMove(tempTile);
        return newList;

    }
    
    public void moveAI(){
    
        playerTwo.findLoc(this);
        updateMove(playerTwo);
    
    }
    
    public boolean checkMate(ArrayList<Integer> ignoreIndexPlayer, ArrayList<Integer> ignoreIndexEnemy){
    
        if(checkMate(ignoreIndexPlayer, ignoreIndexEnemy, playerTwo) || checkMate(ignoreIndexEnemy, ignoreIndexPlayer, playerOne)){
        
            return true;
        
        }
        return false;
    
    }

}
