import java.util.*;
/**
 * La classe Carta costruisce gli oggetti "Carta" che hanno come variabili d'istanza un numero e un seme. 
 * 
 * @author Paccini Letizia, Rizzotto Sofia, Signoretto Alessandro, Ambrosi Alberto
 * @version 14_05_2019 
 */
public class Carta
{
    // variabili d'istanza 
    private int numero;
    private char seme;
    
    /**
     * Costruttore dell'oggetto Carta
     * 
     * @param      n numero della Carta, s seme della Carta
     * @return     (implicito) oggetto Carta istanziata
     */
    public Carta(int n, char s)
    {
        numero = n;
        seme = s;
    }

    /**
     * Metodo getNumero()
     * 
     * @return     numero della Carta
     */
    public int getNumero ()
    {
        return this.numero;
    }
    
    /**
     * Metodo getSeme()
     * 
     * @return     seme della carta
     */
    public char getSeme ()
    {
        return this.seme;
    }
    
    /**
     * Metodo setNumero()
     * 
     * Modifica il numero della Carta
     * @param n intero nuovo numero della Carta (1<=n<=13)
     */
    public void setNumero(int n){
        numero = n;
    }
    
    /**
     * Metodo setSeme()
     * 
     * Modifica il seme della Carta
     * @param s nuovo seme della Carta (s appartiene a {♥,♦,♣,♠})
     */
    public void setSeme(char s){
        seme = s;
    }
    
    /**
     * Metodo toStringCarta()
     * 
     * @return  s stringa di lunghezza 2 contenente numero e seme della Carta
     */
    public String toStringCarta()
    {   
        String s = ""+ this.numero + this.seme; // aggiungo alla stringa vuota il numero e il seme della Carta
        return s;
    }
    
    /**
     * Metodo eliminaCarta()
     * 
     * @return  sostituisce il numero della carta con 0
     */
    public void eliminaCarta()
    {
        this.numero = 0; // rendo la carta non giocabile
    }
   
}
