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
    String strFileName;
    public ReadandWrite(String strFileName,Game game){
        this.strFileName=strFileName;
        this.game = game;
    }
    
    public static void Save(String strFileName,Game game) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int contBricks=0;
            int xBrick;
            int yBrick;
            Paddle paddle = game.getPaddle();
            int xPaddle = paddle.getX();
            int yPaddle = paddle.getY();
            //int xamigo;
            //int yamigo;
            int contAmigos=0;
            Ball ball = game.getBall();
            int vidas = game.getNumVidas();
            int score = game.getScore();
            int xBall=ball.getX();
            int yBall=ball.getY();
            int xDirBall = ball.getXDir();
            int yDirBall = ball.getYDir();
            
            LinkedList <Brick> bricks = game.getBricks();
            
            //Capturamos datos iniciales como vidas, score y posición de la bola
            writer.print("" + vidas + "/" + score +"/"+xBall+"/"+yBall+"/"+xDirBall+"/"+yDirBall+"/"+xPaddle+"/"+yPaddle+"/");
            for (Brick brick: bricks){
                contBricks++;
            }
            
            //Capturamos los bricks que hay y sus posiciones
            writer.print(contBricks+"/");
            for (Brick brick: bricks){
                xBrick=brick.getX();
                yBrick=brick.getY();
                writer.print( xBrick+ "/" +yBrick +"/");
            }
            
            writer.close();
        } catch (IOException ioe) {
            System.out.print("File Not found CALL 911");
        }

    }

    public static void Load(String strFileName,Game game) {
        try {
            int contadori = 0;
            int xBrick;
            int yBrick;
            //int xamigo;
            //int yamigo;
            Ball ball=game.getBall();
            Paddle paddle = game.getPaddle();
            
            LinkedList <Brick> bricks=game.getBricks();
            //LinkedList <Friend> friends=game.getFriends();
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            int xBall= Integer.parseInt(datos[2]);
            int yBall= Integer.parseInt(datos[3]);
            int xDirBall= Integer.parseInt(datos[4]);
            int yDirBall= Integer.parseInt(datos[5]);
            int xPaddle= Integer.parseInt(datos[6]);
            int yPaddle= Integer.parseInt(datos[7]);
            int contBricks= Integer.parseInt(datos[8]);
            
            for(int i=0; i<contBricks; i++){
                Brick brick= new Brick(Integer.parseInt(datos[9+2*i]),Integer.parseInt(datos[10+2*i]),40,10,game);
                bricks.add(brick);
                contadori=11+2*i;
            }
            /*
            int contAmigos=Integer.parseInt(datos[contadori]);
            System.out.println(contadori+" "+contAmigos);
            for(int h=0;h<contAmigos;h++){
                Friend friend = new Friend(Integer.parseInt(datos[contadori+h+1]),Integer.parseInt(datos[contadori+h+2]), 1, 100, 100, game);
                friends.add(friend); //la función add ya existe porque viene de la linkedlist
            }
            */
            game.setNumVidas(vidas);
            game.setScore(score);
            
            //Cargamos la bola
            ball = new Ball(xBall, yBall, xDirBall, yDirBall, 5, 5, game); //Ball(int x, int y, int xdir,int ydir, int width, int height, Game game)
            game.setBall(ball);
            
            //Cargamos el paddle
            paddle = new Paddle(xPaddle, yPaddle, 1, 40, 10, game); //Paddle(int x, int y, int direction, int width, int height, Game game)
            game.setBall(ball);
            
            
            game.setBricks(bricks);
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