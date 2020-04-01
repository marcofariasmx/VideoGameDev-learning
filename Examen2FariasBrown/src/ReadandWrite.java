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
            int contEnemigos=0;
            int xenemy;
            int yenemy;
            int xamigo;
            int yamigo;
            int contAmigos=0;
            Player player=game.getPlayer();
            int vidas = game.getNumVidas();
            int score = game.getScore();
            int xplayer=player.getX();
            int yplayer=player.getY();
            LinkedList <Enemy> enemies=game.getEnemies();
            LinkedList <Friend> friends=game.getFriends();
            writer.print("" + vidas + "/" + score +"/"+xplayer+"/"+yplayer+"/");
            for (Enemy enemy: enemies){
                contEnemigos++;
            }
            writer.print(contEnemigos+"/");
            for (Enemy enemy: enemies){
                xenemy=enemy.getX();
                yenemy=enemy.getY();
                writer.print( xenemy+ "/" +yenemy +"/");
            }
            for (Friend friend : friends) {
                contAmigos++;
            }
            writer.print(contAmigos+"/");
            for (Friend friend : friends){
                xamigo=friend.getX();
                yamigo=friend.getY();
                writer.print( xamigo+ "/" +yamigo +"/");
            }
            writer.close();
        } catch (IOException ioe) {
            System.out.print("File Not found CALL 911");
        }

    }

    public static void Load(String strFileName,Game game) {
        try {
            int contadori=0;
            int xenemy;
            int yenemy;
            int xamigo;
            int yamigo;
            Player player=game.getPlayer();
            LinkedList <Enemy> enemies=game.getEnemies();
            LinkedList <Friend> friends=game.getFriends();
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            int xplayer= Integer.parseInt(datos[2]);
            int yplayer= Integer.parseInt(datos[3]);
            int contEnemigos= Integer.parseInt(datos[4]);
            for(int i=0;i<contEnemigos;i++){
                Enemy enemy= new Enemy(Integer.parseInt(datos[5+2*i]),Integer.parseInt(datos[6+2*i]),1,100,100,game);
                enemies.add(enemy);
                contadori=7+2*i;
            }
            int contAmigos=Integer.parseInt(datos[contadori]);
            System.out.println(contadori+" "+contAmigos);
            for(int h=0;h<contAmigos;h++){
                Friend friend = new Friend(Integer.parseInt(datos[contadori+h+1]),Integer.parseInt(datos[contadori+h+2]), 1, 100, 100, game);
                friends.add(friend); //la función add ya existe porque viene de la linkedlist
            }
            game.setNumVidas(vidas);
            game.setScore(score);
            player= new Player(xplayer,yplayer, 1, 100, 100, game);
            game.setPlayer(player);
            game.setEnemies(enemies);
            game.setFriends(friends);
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
