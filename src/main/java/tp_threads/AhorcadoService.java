package tp_threads;

import tp_threads.controller.Player;

import java.time.LocalDateTime;

/**
 * Created by Yo on 24/4/2019.
 */
public abstract class AhorcadoService {

    private String getWord(){
        return "word";
    }

    public static void writeResult(Player winner/*, Result */){
        java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
        String word = winner.getOriginalWord();
        String player = winner.getName();
        ThreadsDBWriter threadsDBWriter = new ThreadsDBWriter();
        threadsDBWriter.writeResult(word,player,sqlDate);
    }

    public static String getRandomWord() {
        return "p";
    }
}
