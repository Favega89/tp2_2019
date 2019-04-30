package tp_threads.controller;

import lombok.Getter;
import lombok.Setter;
import tp_threads.AhorcadoService;

import java.util.*;

/**
 * Created by Yo on 24/4/2019.
 */
@Getter
@Setter
public class Ahorcado implements Observer {

    private List<Thread> players;
    private Boolean end;
    private Player winner;
    private boolean available = true;


    public Ahorcado (String ... playersNames){
        end = false;
        players = new ArrayList<>();
        Arrays.stream(playersNames)
                .forEach( p -> {
                            Thread t  = new Thread(new Player(p, AhorcadoService.getRandomWord(),this));
                            t.start();
                            players.add(t);
                        }
                );

    }

    public synchronized void play(Player current){
        if (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Contenedor: Error en put -> " + e.getMessage());
            }
        } else {
            available = false;
            current.playTurn();
            available = true;
            notifyAll();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        end = (Boolean) arg;
        winner = (Player)o;
    }

    public void persistResult(){
        AhorcadoService.writeResult(winner);
    }
}
