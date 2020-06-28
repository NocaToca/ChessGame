
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    int team;
    int score;
    boolean isAnAI;
    Pawn [] pawns;
    Rook [] rooks;
    Knight [] knights;
    King king;
    Bishop [] bishops;
    Queen queen;
    Pieces [] pieces;
    boolean isInCheck;
    public Player(int teams){
    
        team = teams;
        score = 0;
        
        pawns = new Pawn[8];
        rooks = new Rook[2];
        knights = new Knight[2];
        bishops = new Bishop[2];
        pieces = new Pieces[16];
       
        isInCheck = false;
    
    }
    
    public void initializePieces(){
    
        for(int i = 0; i < 8; i++){
        
            pieces[i] = pawns[i];
            //System.out.println(i);
        
        }
        for(int i = 8; i < 10; i++){
        
            pieces[i] = rooks[i-8];
            //System.out.println(i);
        
        }
        for(int i = 10; i < 12; i++){
        
            pieces[i] = knights[i-10];
            //System.out.println(i);
        
        }
        for(int i = 12; i < 14; i++){
        
            pieces[i] = bishops[i-12];
            //System.out.println(i);
        
        }
        pieces[14] = king;
        pieces[15] = queen;
    
    
    }
    
    public void findLoc(){}
    public void findLoc(Game newGame){}
}
