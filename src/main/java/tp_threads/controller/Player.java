package tp_threads.controller;

import commons.logger.Logger;
import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Scanner;

/**
 * Created by Yo on 24/4/2019.
 */
@Getter
@Setter
public class Player extends Observable implements Runnable {

    private String name;
    private String originalWord;
    private String nonUsedLetters;
    private String wordCollect;
    private Ahorcado game;

    public Player(String name, String word, Ahorcado game){
        this.name = name;
        this.originalWord = word;
        this.nonUsedLetters = "abcdefghijklmnopqrstuvwxyz";
        this.wordCollect = word;
        this.game = game;
        this.addObserver(game);
    }

    public void playTurn() {
        String letter = getLetter();
        if (originalWord.contains(letter)){
            nonUsedLetters = nonUsedLetters.toString().replace(letter,"X");
            refreshWordMap();
            notifyEndGame();
            Logger.hit(this);
        }else {
            Logger.wordNotContainsThatLetter(this);
        }
    }

    private void refreshWordMap(){
        wordCollect = originalWord;
        for (char c : nonUsedLetters.toCharArray()){
            wordCollect =  wordCollect.replaceAll(String.valueOf(c), "X");
        }
    }

    private String getLetter(){
        Scanner scanner = new Scanner(System.in);
        Logger.playTurnMessage(this);
        String letter = Character.toString(scanner.next().charAt(0));
        Logger.selectedLetterMsg(letter);
        return letter;
    }

    @Override
    public void run() {
        while (!game.getEnd()){
            game.play(this);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                Logger.concurrenceErrorExceptionMsg(name);
            }
        }
        game.persistResult();
    }

    private void notifyEndGame(){
        if(wordCollect == originalWord){
            setChanged();
            notifyObservers(Boolean.TRUE);
        }
    }
}
