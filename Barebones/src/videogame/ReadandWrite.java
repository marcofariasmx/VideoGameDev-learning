package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


/**
 *
 * @author antoniomejorado
 */
public class ReadandWrite {
    private Game game;
    String strFileName;//name of the file
    public ReadandWrite(String strFileName,Game game){
        this.strFileName=strFileName;
        this.game = game;
    }
    
    public static void Save(String strFileName,Game game) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));//name of the fil in new writer
            int contBricks=0;//counter of bricks
            int xBrick;//positions of bricks
            int yBrick;
            Paddle paddle = game.getPaddle();//storing the paddle
            int xPaddle = paddle.getX();//x of paddle
            int yPaddle = paddle.getY();//y of paddle
            Ball ball = game.getBall();//store the ball of game
            int vidas = game.getNumVidas();//store the lives in game
            int score = game.getScore();//store the score in game
            int xBall=ball.getX();//store position of ball in game
            int yBall=ball.getY();
            int xDirBall = ball.getXDir();//store the direction of the ball
            int yDirBall = ball.getYDir();
            int contColisiones= game.getContColisiones();//store the quantity of collisions
            
            LinkedList <Brick> bricks = game.getBricks();// sotres bricks
            
            //Capturamos datos iniciales como vidas, score y posición de la bola
            writer.print("" + vidas + "/" + score +"/"+xBall+"/"+yBall+"/"+xDirBall+"/"+yDirBall+"/"+xPaddle+"/"+yPaddle+"/");
            for (Brick brick: bricks){
                if(!brick.isDestroyed()){//only no destroyed bricks
                    contBricks++;
                }
            }
            
            //Capturamos los bricks que hay y sus posiciones
            writer.print(contBricks+"/");
            for (Brick brick: bricks){
                if(!brick.isDestroyed()){
                    xBrick=brick.getX();
                    yBrick=brick.getY();
                    writer.print( xBrick+ "/" +yBrick +"/");
                }
            }
            writer.print(contColisiones+"/");
            writer.close();
        } catch (IOException ioe) {
            System.out.print("File Not found CALL 911");
        }

    }

    public static void Load(String strFileName,Game game) {
        try {
            int contadori = 0;
            //initializae all the objects used
            Ball ball=game.getBall();// new ball
            Paddle paddle = game.getPaddle();//new paddle
            
            LinkedList <Brick> bricks;//new list of bricks
            bricks= new LinkedList();
            FileReader file = new FileReader(strFileName);//get the file
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            //everything stored in the file
            int vidas = Integer.parseInt(datos[0]);//vidas
            int score = Integer.parseInt(datos[1]);//score
            int xBall= Integer.parseInt(datos[2]);//ball position
            int yBall= Integer.parseInt(datos[3]);//
            int xDirBall= Integer.parseInt(datos[4]);//direction of the ball
            int yDirBall= Integer.parseInt(datos[5]);
            int xPaddle= Integer.parseInt(datos[6]);//position of the paddle
            int yPaddle= Integer.parseInt(datos[7]);
            int contBricks= Integer.parseInt(datos[8]);//number of bricks and properties in for
            for(int i=0; i<contBricks; i++){
                Brick brick= new Brick(Integer.parseInt(datos[9+2*i]),Integer.parseInt(datos[10+2*i]),40,10,game);
                bricks.add(brick);
                contadori=11+2*i;
            }//cont of collisions
            int contColisiones= Integer.parseInt(datos[contadori]);
            //setting up the data into the game
            game.setNumVidas(vidas);//set lives
            game.setScore(score);//set score
            
            //Cargamos la bola
            ball = new Ball(xBall, yBall, xDirBall, yDirBall, 5, 5, game); //Ball(int x, int y, int xdir,int ydir, int width, int height, Game game)
            game.setBall(ball);
            
            //Cargamos el paddle
            paddle = new Paddle(xPaddle, yPaddle, 1, 40, 10, game); //Paddle(int x, int y, int direction, int width, int height, Game game)
            game.setPaddle(paddle);
            
            //set the bricks
            game.setBricks(bricks);
            //set the cont of collisions
            game.setContColisiones(contColisiones);
            //game.setFriends(friends);
            System.out.println("Se leyo  vidas = "+ vidas + " y score = " + score);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args,Game game) {
        // TODO code application logic here
        Save("Archivo.txt", game);
        Load("Archivo.txt", game);
    }

}