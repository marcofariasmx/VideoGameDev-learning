
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MARBROCU
 */
public class Game implements Runnable{
    private BufferStrategy bs; //to have several buffers when displaying
    private Graphics g;// to paint objects
    private Display display; //to display in the game
    String title; //title f the window
    private int width;// width window
    private int height;// height window
    private Thread thread;// thread to create the game
    private boolean running;//to set the game
    private Player player;
    private LinkedList <Enemy> enemies;
    private LinkedList<Friend> friends;     //linkedlist of enemies
    private KeyManager keyManager;
    private boolean statePause;
    private ReadandWrite Archivo;
    
    // Variables para puntos y vidas
    private int numVidas;
    private int numPuntos;
    private int enemyCollisionCounter;

    private String numVidasText;
    private String numPuntosText;
    
    public int getNumVidas(){
        return numVidas;
    }
    public void setNumVidas(int vidas){
        numVidas=vidas;
    }
    public int getScore(){
        return numPuntos;
    }
    public void setScore(int score){
        numPuntos=score;
    }
    public Player getPlayer(){
        return player;
    }
    public void setPlayer(Player player1){
        player=player1;
    }
    public LinkedList <Enemy> getEnemies(){
        return enemies;
    }
    public void setEnemies(LinkedList <Enemy> enemies1){
        enemies=enemies1;
    }
    public LinkedList <Friend> getFriends(){
        return friends;
    }
    public void setFriends(LinkedList <Friend> friends1){
        friends=friends1;
    }
    
    private void init(){
        display=new Display(title,width, height);
        Assets.init();
        player= new Player(0, getHeight()-100, 1, 100, 100, this);
        display.getJframe().addKeyListener(keyManager);
        
        // Creamos a los enemigos
        enemies= new LinkedList();
        int random = (int)(Math.random()*3)+2;
         for (int i=1; i<=random;i++){
             Enemy enemy= new Enemy(((int)(Math.random()*getWidth())-100), (0-(int)(Math.random()*getHeight())),1, 100, 100, this);
             enemies.add(enemy);
         }
         
        //Creamos a los friends
        friends = new LinkedList();
        int randomFriends = (int) (Math.random() * 6) + 10; //(rand() * b-a+1) + a   -> 15-10+1 = 6

        for (int i = 1; i <= randomFriends; i++) {
            //Hacemos random la aparición del enemigo.
            Friend friend = new Friend((int) (Math.random() * getWidth() - 100), getHeight()+150, 1, 100, 100, this);
            friends.add(friend); //la función add ya existe porque viene de la linkedlist
        }
         
         //Iniciamos las variables del juego
        numVidas = (int) (Math.random() * 3) + 5; //(rand() * b-a+1) + a   -> 7-5+1 = 3
        numPuntos = 0;
        enemyCollisionCounter = 0;

        numVidasText = Integer.toString(numVidas);
        numPuntosText = Integer.toString(numPuntos);
        
    }
    
    @Override
    public void run() {
        init();
        
        //frames per second
        int fps=50;
        //time each tick nano segs
        double timeTick=1000000000/fps;
        //initializing delta
        double delta=0;
        //define to use in loop
        long now;
        //initializin las time to the computer time in nanosecs
        long lastTime=System.nanoTime();
        while(running){
            //setting the time now to actua time
            now=System.nanoTime();
            //acummulating to delta the difference between imes in timeT
            delta+=(now-lastTime)/timeTick;
            //updating last time
            lastTime=now;
            //if delta positive we tick
            if (delta>=1){
                tick();
                render();
                delta--;
            }
        }
        tick();
        render();
        stop();
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    private void tick(){
        if(this.getKeyManager().pause){
            if(statePause==false){
                statePause=true;
            }
            else{
                statePause=false;
            }
        }
        if(statePause==false){
            keyManager.tick();
        //advancing player with collision
        player.tick();
        
        //Tickeamos a cada uno de los enemigos
        for(Enemy enemy:enemies){
            enemy.tick();
            
            // if collision, then resta vida
            if (player.collision(enemy)) {

                //Hacemos un ruido de beep
                //beep();

                //Reseteamos enemigo y Randomizamos su aparición
                enemy.setX((int) (Math.random() * getWidth()) - 100);
                enemy.setY(-150);

                //Sumamos al enemyCollisionCounter
                enemyCollisionCounter++;

                if (enemyCollisionCounter == 3) {
                    numVidas--;
                    //Actualizamos el valor del texto
                    numVidasText = Integer.toString(numVidas);

                    //Lo reseteamos
                    enemyCollisionCounter = 0;
                }

                if (numVidas == 0) {

                    //Detenemos el juego
                    running = false;

                }

            }
            
            //Sí el enemigo llega a mero abajo, lo volvemos a reaparecer random
            if (enemy.getY() + 100 >= getHeight()) {
                enemy.setX((int) (Math.random() * getWidth()) - 100);
                enemy.setY(-150);
            }
        
        }
        
        //tickeamos a cada uno de los friends
        for (Friend friend : friends) {
            friend.tick();
            // if collision, then reset positions
            if (player.collision(friend)) {

                //Reseteamos friend y Randomizamos su aparición
                friend.setX((int) (Math.random() * getWidth()) - 100);
                friend.setY(getHeight() + 150);

                //Sumamos al enemyCollisionCounter
                numPuntos = numPuntos + 10;
                //Reseteamos el valor del texto
                numPuntosText = Integer.toString(numPuntos);

            }
            //Sí el amigo llega a mero arriba, lo volvemos a reaparecer random
            if (friend.getY() - 100 <= 0) {
                friend.setX((int) (Math.random() * getWidth()) - 100);
                friend.setY(getHeight() + 150);
            }
        }
        }
        else{
            keyManager.tick();
        }
        if(this.getKeyManager().save){
            Archivo.Save("Archivo.txt",this);
        }
        if(this.getKeyManager().load){
            enemies.clear();
            friends.clear();
            Archivo.Load("Archivo.txt",this);
        }
    }
    
    /**
     * 
     * @param title
     * @param width
     * @param height 
     */
    public Game(String title, int width, int height){
        this.title=title;
        this.width=width;
        this.height=height;
        running=false;
        keyManager= new KeyManager();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    /**
     * 
     */
    
    
    private void render(){
        //get the buffer strategy from the display
        bs=display.getCanvas().getBufferStrategy();
        /*
        
        */
        if(bs==null){
            display.getCanvas().createBufferStrategy(3);
        }
        else{
            g=bs.getDrawGraphics();
            //g.clearRect(0,0,width, height);
            g.drawImage(Assets.background,0,0,width,height,null);
            player.render(g);
            //Render de enemigos
            for (Enemy enemy: enemies){
                enemy.render(g);
            }
            //Render de friends
            for (Friend friend : friends) {
                friend.render(g);
            }
            
            //String de Vidas
            g.drawString("Vidas = " + numVidasText, 10, 30);
            //String de Puntos
            g.drawString("Puntos = " + numPuntosText, 10, 60);
            
            bs.show();
            g.dispose();
        }
    }
    /**
     * 
     */
    public synchronized void start(){
        if(!running){
            running=true;
            thread=new Thread(this);
            thread.start();
        }
    }
    /**
     * 
     */
    public synchronized void stop(){
        if(running){
            running=false;
            try{
                thread.join();
            }
            catch (InterruptedException ie){
                ie.printStackTrace();
            }
        }
    }
    
}
