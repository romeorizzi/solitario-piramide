import java.util.Random;
/**
 * La classe mazzo costruisce un oggetto Mazzo composto da un array di 52 Carte.
 * 
 * @author Paccini Letizia, Rizzotto Sofia, Signoretto Alessandro, Ambrosi Alberto
 * @version 14_05_2019 
 */
public class Mazzo {
    // variabili d'istanza
    private Carta[] a = new Carta[52]; // array a che contiene le carte
    private Carta cima; // prima carta giocabile del mazzo
    private int inizioMazzo; // posizione della Cima nell'array a

    /**
     * Costruttore degli oggetti Mazzo
     * @return (implicito) oggetto Mazzo istanziato
     */
    public Mazzo()
    {
        a = riempiMazzo(); // l'array viene inizializzato nel metodo creaMazzo()
        inizioMazzo = 0;
        cima = a[inizioMazzo];
    }

    /**
     * Metodo riempiMazzo()
     * Crea un mazzo ordinato composto da 52 carte, divise per i 4 semi ♥ ♦ ♣ ♠
     * 
     * @return    m array che contiene tutte le carte del mazzo
     */
    private Carta[] riempiMazzo()
    {
        Carta[] m = new Carta[52];
        char[] semi = new char[4]; // semi è un array che contiene i caratteri dei 4 semi
        semi [0] = '\u2665';
        semi [1] = '\u2666';
        semi [2] = '\u2663';
        semi [3] = '\u2660';
        int h=0; // contatore delle posizioni delle Carte nell'array m
        for (int i=0; i<=3; i++){ // scorre i semi
            for (int j=1; j<=13; j++){ // scorre i numeri
                m[h] = new Carta(j,semi[i]);
                h++;
            }
        }
        return m;
    }
    
    /**
     * Metodo getCima()
     * Restituisce la cima del mazzo
     * 
     * @return cima
     */
    public Carta getCima(){
        return cima;
    }
    
    /**
     * Metodo getInizioMazzo() 
     * Restituisce la posizione della cima del mazzo
     * 
     * @return inizioMazzo
     */
    public int getInizioMazzo(){
        return inizioMazzo;
    }
    
    /**
     * Metodo setInizioMazzo()
     * Azzera inizioMazzo
     * 
     */
    public void setInizioMazzo(){
        inizioMazzo=0;
    }
    
    /**
     * Metodo setCima()
     * Modifica la cima del mazzo: sposta la cima alla carta successiva
     */
    public void setCima(){
        if(inizioMazzo!=51){ // se la Carta è in posizione 51, è stata raggiunta la fine del mazzo
            inizioMazzo++;
            cima = a[inizioMazzo];
        }
    }
    
    /**
     * Metodo mescolaMazzo()
     * 
     */
    public void mescolaMazzo(){
        Random rand = new Random(); // creo l'oggetto rand di tipo Random
        int[] posizioni = new int[52]; // array di supporto che conterrà le posizioni secondo cui ordinare le carte
        int i=0;
        while (i<52){
            int x = rand.nextInt(52)+1; // assegno a x un valore "casuale" compreso tra 1 e 52
            boolean t = false; // false se x non è già presente nell'array posizioni
            for (int j=0; j<i; j++){ // scorro i valori già inseriti nell'array di supporto per vedere se il numero generato è già presente
                if (posizioni[j]==x){
                    t = true; // true se x è già presente nell'array posizioni
                }
            }
            if (t==false){ // se il numero generato non è ancora presente in posizioni, lo aggiungo in posizione i
                posizioni[i]=x;
                i++;
            }
        }
        Carta[] mescolate = new Carta[52]; //array in cui inseriamo le carte nell'ordine dettato da "posizioni"
        for (int h=0; h<52; h++){
            mescolate[posizioni[h]-1]=this.a[h];
        }
        a = mescolate; // sposto il puntatore di a su mescolate
        cima = a[0]; // sposto la cima alla prima carta del mazzo
        return ;
    }
    
    /**
     * Metodo trasformaPozzoInMazzo()
     * Sposta le carte del pozzo nel mazzo
     * 
     * @param p Pozzo
     */
    public void trasformaPozzoInMazzo(Pozzo p){
        int i =0 ;
        while (i<p.getOccupate()){ // scorro il pozzo
            if (p.getPozzo()[i].getNumero()!=0) { // se la carta del pozzo è giocabile
                a[i] = p.getPozzo()[i]; // la metto nell'array del mazzo
            }
            i++;
        }
        inizioMazzo=0;
        cima = a[inizioMazzo]; // ristabilisco le condizioni iniziali
    }
    
}

