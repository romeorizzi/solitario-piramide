import java.util.*;
/**
 * La classe Partita costruisce un oggetto Partita composto da una Piramide, un Mazzo, un Pozzo, il numero di giri del mazzo fatti e un booleano per 
 * capire se la partita è finita
 * 
 * 
 * @author Paccini Letizia, Rizzotto Sofia, Signoretto Alessandro, Ambrosi Alberto
 * @version 14_05_2019
 */
public class Partita
{
    static Scanner in = new Scanner(System.in);
    // variabili d'istanza 
    private boolean over; // indica quando è finita la partita
    private int girimazzofatti; // indica il numero di giri del mazzo fatti
    private Piramide p;
    private Mazzo m;
    private Pozzo poz;

    /**
     * Costruttore dell'oggetto Partita
     */
    public Partita()
    {
        over = false; // false se la partita non è finita, true altrimenti
        girimazzofatti = 0;
        m = new Mazzo();
        m.mescolaMazzo();
        poz = new Pozzo();
        p = new Piramide(m);
    }
    
    /**
     * Metodo isOver()
     * Verifica se la partita è finita
     * 
     * @return true se la partita è finita, false altrimenti
     */
    public boolean isOver(){
        if (p.getMat()[0][0].getNumero()==0){over = true;}
        return (over==true || girimazzofatti==3); // la partita è finita se ho giocato l'ultima carta della piramide oppure se ho fatto tre giri del mazzo
    }
    
    /**
     * Metodo faTredici()
     * 
     * @param a,b Carte
     * @return true se la somma dei numeri di a e b fa 13, false altrimenti
     */
    public boolean faTredici(Carta a, Carta b)
    {
        return (a.getNumero()+b.getNumero())==13;
    }
    
    /**
     * Metodo combPiramide()
     * 
     * Guarda quali sono (e se ci sono) le combinazioni di due carte nella piramide che sommate danno 13.
     * Fissa la prima carta libera, guarda se ci sono combinazioni nella piramide con tale carta e se non ne trova fissa la carta libera successiva.
     * 
     * @return posizioni matrice di interi che contiene in riga le posizioni delle due carte che sommate danno 13. Se viene trovato un k le due righe di posizioni sono uguali.
     * Se non ci sono carte che sommate danno 13, la matrice posizioni contiene -1 nella prima cella e 0 nelle altre.
     */
    public int[][] combPiramide(){
        int[][] posizioni = new int[2][2]; // 
        int i=6; // righa della carta fissata
        int j=0; // colonna della carta fissata
        boolean trovata = false; // false se non trovo la combinazione
        posizioni[0][0] = -1; 
        while (!trovata && i>=0){ // finchè non ho trovato la combinazione e non sono arrivato alla fine della piramide
            if (p.cartaLibera(i,j)){
                if (p.getMat()[i][j].getNumero()==13) { // se la carta è un k mi restituisce 2 carte uguali
                    posizioni[0][0] = i;
                    posizioni[0][1] = j;
                    posizioni[1][0] = i;
                    posizioni[1][1] = j;
                    trovata = true;
                }
                else {
                    for(int k=i; k>=0; k--){ // k scorre le righe dal basso verso l'alto
                        for(int h=0; h<=i; h++){ // h scorre le colonne da sinistra a destra
                            if ( !trovata && (p.cartaLibera(k,h)) && (faTredici(p.getMat()[i][j],p.getMat()[k][h])) ){
                                posizioni[0][0] = i;
                                posizioni[0][1] = j;
                                posizioni[1][0] = k;
                                posizioni[1][1] = h;
                                trovata = true;
                            }
                        }
                    }
                    if (!trovata){ // se non ho ancora trovato la combinazione, fisso la carta successiva
                        if (i>j){ 
                            j ++;
                        }
                        else { // se sono sulla diagonale salgo di riga e torno indietro a 0 di colonna
                            i--;
                            j=0;
                        }
                    }
                }
            }
            else{
                if (i>j){ // se la carta non è libera fisso la carta successiva
                            j ++;
                        }
                        else { // se sono sulla diagonale salgo di riga e torno indietro a 0 di colonna
                            i--;
                            j=0;
                        }
            }
        }
        return posizioni;
    }
        
    /**
     * Metodo combMazzoPozzo()
     * 
     * Guarda quali sono (e se ci sono) le combinazioni di una carta nella piramide e una nel mazzo (o pozzo) che sommate danno 13
     * 
     * @param a Carta (Cima o Top)
     * @return posizioni matrice 2x2 contenete due volte la posizione della carta trovata
     */
    public int[][] combMazzoPozzo(Carta a){ // a carta nel mazzo o nel pozzo
        boolean trovata = false; 
        int[][] posizioni = new int[2][2];
        posizioni[0][0] = -1;
        for(int k=6; k>=0; k--){ // k scorre le righe dal basso verso l'alto
            for(int h=0; h<=k; h++){ // h scorre le colonne da sinistra a destra
                if (!trovata && p.cartaLibera(k,h) && (faTredici(a,p.getMat()[k][h])) ){ 
                    posizioni[0][0] = k;
                    posizioni[0][1] = h;
                    posizioni[1][0] = k;
                    posizioni[1][1] = h;
                    trovata = true;
                }
            }
        }
        return posizioni;
    }
    
    /**
     * Metodo evidenziaPiramide()
     * 
     * Sottolinea le carte da eliminare nella piramide
     * 
     * @param posizioni matrice 2x2 contenente le posizioni delle carte da sottolineare
     * @return res matrice di stringhe con "--" sotto ogni carta da giocare
     */
    public String[][] evidenziaPiramide (int[][] posizioni){
        String[][] res = p.toStringPiramide();
        int i0 = 6; // numero di coppie di spazi a sinistra della matrice
        if (posizioni[0][0]!=-1) { // controlla se ci sono carte da sottolineare
            int r1=posizioni[0][0]; // riga dove si trova la prima carta da sottolineare
            int c1=posizioni[0][1]; // colonna dove si trova la prima carta da sottolineare
            res[r1*2+1][i0-r1+2*c1+1] = "--"; // [r1*2+1] indica la riga di spazi in cui devo sottolineare (+1 perchè devo sottolineare la riga sotto il numero), in [i0-r1+2*c1+1]
            // i0-r1 determina il numero di spazi che ci sono davanti, 2*c1 indica in numero di celle precedenti alla carta da sottolineare a partire dalla prima carta
        
            int r2=posizioni[1][0]; // riga dove si trova la seconda carta sottolineare
            int c2=posizioni[1][1]; // colonna dove si trova la seconda carta da sottolineare
            res[r2*2+1][i0-r2+2*c2+1] = "--"; 
        }
        return res;
    }
    
    /**
     * Metodo stampaPiramide ()
     * 
     * Stampa una matrice di stringhe
     * 
     * @param m matrice di stringhe
     */
    public void stampaPiramide (String[][] m){
        for (int i=0; i<14; i++){
            for (int j=0; j<14; j++){
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * Metodo eliminaCartePiramide()
     * 
     * Elimina le carte evidenziate nella piramide e le sottolineature
     * 
     * @param posizioni matrice 2x2
     * @return res matrice di stringhe senza le carte giocate
     */
    public String[][] eliminaCartePiramide (int[][] posizioni){
        String[][] res = p.toStringPiramide();
        int i0 = 6;
        if (posizioni[0][0]!=-1) {
            int r1=posizioni[0][0]; // riga dove si trova la carta 1
            int c1=posizioni[0][1]; // colonna dove si trova la carta 1
            res[r1*2][i0-r1+2*c1+1] = "  "; 
        
            int r2=posizioni[1][0]; // riga dove si trova la carta 
            int c2=posizioni[1][1]; // colonna dove si trova la carta 2
            res[r2*2][i0-r2+2*c2+1] = "  "; 
            
            p.getMat()[posizioni[0][0]][posizioni[0][1]].eliminaCarta(); // elimino le carte selezionate
            p.getMat()[posizioni[1][0]][posizioni[1][1]].eliminaCarta(); 
        }
        return res;
    }
    
    /**
     * Metodo stampaMazzoPozzo()
     * 
     * Stampa la prima carta del mazzo e del pozzo
     * 
     * @return res matrice di stringhe senza le carte giocate
     */
    public void stampaMazzoPozzo(){
        String sm; // stringa per il mazzo
        String sp; // stringa per il pozzo
        switch (m.getCima().getNumero()){
            case 0  : sm = "0 "; break;
            case 10 : sm = "X" + m.getCima().getSeme(); break;
            case 11 : sm = "J" + m.getCima().getSeme(); break;
            case 12 : sm = "Q" + m.getCima().getSeme(); break;
            case 13 : sm = "K" + m.getCima().getSeme(); break;
            case 1  : sm = "A" + m.getCima().getSeme(); break;
            default : sm = m.getCima().toStringCarta();
        }
        switch (poz.getTop().getNumero()){
            case 10 : sp = "X" + poz.getTop().getSeme(); break;
            case 11 : sp = "J" + poz.getTop().getSeme(); break;
            case 12 : sp = "Q" + poz.getTop().getSeme(); break;
            case 13 : sp = "K" + poz.getTop().getSeme(); break;
            case 1  : sp = "A" + poz.getTop().getSeme(); break;
            default : sp = poz.getTop().toStringCarta();
        }
        System.out.println("        Mazzo   Pozzo");
        System.out.println("          "+ sm + "      " + sp);
    }
    
    /**
     * Metodo evidenziaMazzo()
     * 
     * Stampa la sottolineatura del mazzo
     */
    public void evidenziaMazzo(){
        System.out.println("          --");
    }
    
    /**
     * Metodo evidenziaPozzo()
     * 
     * Stampa la sottolineatura del pozzo
     */
    public void evidenziaPozzo(){
        System.out.println("                  --");
    }

    /**
     * Metodo faiMossa()
     * 
     * Esegue la mossa
     */
    public void faiMossa(){
        int[][] posizioni = this.combPiramide(); // cerco se ci sono combinazioni nella piramide e salvo le posizioni
        System.out.println("Giri del mazzo fatti: "+girimazzofatti+"\n");
        String[][] res = p.toStringPiramide(); // res matrice di stringhe sulla quale farò la mossa
        int dove=1; // dove indica se ho trovato una combinazione nel mazzo, nel pozzo o nella piramide
        if(girimazzofatti>0 & m.getInizioMazzo()==0){ // se ho appena girato il mazzo stampo la piramide prima di effettuare la nuova mossa
            stampaPiramide(res);
            stampaMazzoPozzo();
            System.out.println("\n------------------------------");
            in.nextLine();
        }
        if (posizioni[0][0]==-1){ // se non ho trovato la combinazione nella piramide cerco nel pozzo
            posizioni = this.combMazzoPozzo(poz.getTop()); 
            if (posizioni[0][0] == -1){ // se non ho trovato la combinazione nel pozzo cerco nel mazzo
                posizioni = this.combMazzoPozzo(m.getCima());
                if (posizioni[0][0] == -1){ // se non l'ho ancora trovata
                    poz.add(m);
                    dove = -1; // cioè non ho trovato la combinazione
                }
                else{ 
                     dove = 3; // se è nel mazzo
                     res = this.evidenziaPiramide(posizioni); // evidenzio le carte da giocare
                }
            }
            else{ 
                dove = 2; // se è nel pozzo
                res = this.evidenziaPiramide(posizioni);
            }
        }
        else{// se è nella piramide
            res = this.evidenziaPiramide(posizioni);
        }
        
        if(girimazzofatti>0 & (m.getInizioMazzo()==0 | m.getInizioMazzo()==1)){ 
           //serve solo per la grafica (la condizione è uguale a quella sopra con l'aggiunta del caso in cui come prima mossa giro la carta del mazzo)
           System.out.println("Giri del mazzo fatti: "+girimazzofatti+"\n");
        }
        stampaPiramide(res);
        stampaMazzoPozzo(); 
        res = eliminaCartePiramide(posizioni); // tolgo le carte giocate
        
        if (dove == 2){
            evidenziaPozzo();
            poz.getTop().eliminaCarta(); // modifico la prima carta del pozzo
            poz.setTop();
        }
        if (dove == 3){
            evidenziaMazzo();
            m.getCima().eliminaCarta(); // modifico la prima carta del mazzo
            m.setCima(); // se sono all'ultima carta setCima non aumenta inizioMazzo
        }
        System.out.println("\n------------------------------");
        if (dove!=-1){ // se ho trovato la combinazione (riferito al caso generale) stampo la nuova piramide senza le carte appena giocate
            in.nextLine();
            System.out.println("Giri del mazzo fatti: "+girimazzofatti+"\n");
            stampaPiramide(res);
            stampaMazzoPozzo();
            System.out.println("\n------------------------------");
        }
        if (m.getCima().getNumero()==0){ // cima è 0 se ho finito le carte giocabili nel mazzo
            boolean trovato = false;
            int[][] posizioni2 = this.combPiramide(); //prima di girare il mazzo cerco se ci sono altre combinazioni
            if (posizioni2[0][0]==-1){ // se non ho trovato la combinazione nella piramide cerco nel pozzo
                posizioni2 = this.combMazzoPozzo(poz.getTop()); 
                if (posizioni2[0][0] != -1){ // se l'ho trovata nel pozzo
                    trovato = true;
                }//se non l'ho trovata neanche nel pozzo "trovato" rimane false
            }
            else trovato = true; //caso in cui la combinazione è stata trovata nella piramide
            if(!trovato){ //se non ho trovato combinazioni allora giro il mazzo e il pozzo
                m.trasformaPozzoInMazzo(poz);
                girimazzofatti++;
                System.out.println("Hai finito il mazzo \n");
                poz = new Pozzo();
            }
        }
        in.nextLine();
    }
    
    public static void main (String[]Args){
        boolean termina = false;
        while(!termina){
            Partita game = new Partita();
            System.out.println("Giri del mazzo fatti: "+game.girimazzofatti+"\n");
            game.stampaPiramide(game.p.toStringPiramide()); // stampo la piramide
            game.stampaMazzoPozzo(); // stampa il mazzo e il pozzo
            System.out.println("\n------------------------------");
            in.nextLine();  
            
            while(!game.isOver()){
                game.faiMossa();
            }
            
            if (game.p.getMat()[0][0].getNumero()==0){
                System.out.println ("CIAO HAI VINTO LA PARTITA :-)");
            }
            else {
                System.out.println ("CIAO HAI PERSO LA PARTITA :-(");
            }
            
            System.out.println("Vuoi fare una nuova partita? [y/n]");
            termina="n".equals(in.next("\\w"));
        }
    }
}
