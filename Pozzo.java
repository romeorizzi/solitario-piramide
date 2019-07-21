/**
 * La classe Pozzo costruisce un oggetto Pozzo composto da un array di 52 Carte.
 * 
 * @author Paccini Letizia, Rizzotto Sofia, Signoretto Alessandro, Ambrosi Alberto
 * @version 14_05_2019 
 */
public class Pozzo
{
    // variabili d'istanza
    private Carta[] poz; // poz array di carte
    private Carta top; // top prima carta giocabile del pozzo
    private int occupate; // numero di carte giocabili nel pozzo che coincide con la posizione del top nel pozzo
    // Nel Pozzo le carte giocabili vanno dalla posizione 0 alla posizione occupate-1
    /**
     * Costruttore degli oggetti Pozzo
     * 
     * @return (implicito) oggetto Pozzo istanziato
     */
    public Pozzo()
    {
        poz = new Carta[52]; // array che contiene le carte ordinate del pozzo
        top = new Carta(0,' '); // la prima carta del pozzo è zero e non ha seme
        occupate = 0; // non ci sono carte nel pozzo
    }
   
    /**
     * Metodo getOccupate()
     * Restituisce il valore dell'intero occupate
     * 
     * @return occupate
     */
    public int getOccupate(){
        return occupate;
    }
    
    /**
     * Metodo getTop()
     * Restituisce la carta top
     * 
     * @return top
     */
    public Carta getTop(){
        return top;
    }
    
    /**
     * Metodo getPozzo()
     * Restituisce l'array con le carte del Pozzo
     * 
     * @return poz
     */
    public Carta[] getPozzo(){
        return poz;
    }
    
    /**
     * Metodo setTop()
     * Modifica il top del Pozzo
     */
    public void setTop(){ 
        if (occupate>0 && top.getNumero()==0){ // se l'array non è vuoto e se il numero del top è 0
            occupate--;
            top = poz[occupate-1]; // top
            if(occupate==0){ //se avevo solo una carta
                top = new Carta(0,' ');
            }
        }
    }
    
    /**
     * Metodo add()
     * Aggiunge nel pozzo la carta cima del mazzo
     *
     * @param   m Mazzo 
     */
    public void add(Mazzo m)
    {
        if(m.getCima().getNumero()!=0){ 
            top= new Carta (m.getCima().getNumero(),m.getCima().getSeme()); // creo una nuova Carta top che ha il numero e il seme della cima del mazzo
            poz[occupate] = top;
            occupate++;
        }
        m.getCima().eliminaCarta(); // elimina la cima del mazzo
        m.setCima(); // l'inizio del mazzo si sposta in avanti di una carta
    }
}
