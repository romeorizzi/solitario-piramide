
/**
 * La classe Piramide costruisce un oggetto Piramide composto da una matrice di 28 Carte.
 * 
 * @author Paccini Letizia, Rizzotto Sofia, Signoretto Alessandro, Ambrosi Alberto
 * @version 14_05_2019
 */
public class Piramide
{
    // variabili d'istanza 
    private Carta[][] mat; // mat matrice triangolare inferiore di carte

    /**
     * Costruttore dell'oggetto Piramide
     * 
     * @param m Mazzo
     * @return (implicito) oggetto Piramide istanziato
     */
    public Piramide(Mazzo m)
    {
        mat = new Carta[7][7];
        for (int i=0; i<7; i++){
            for (int j=i; j<7; j++){
                mat [j][i] = m.getCima(); // distribuisco le carte del mazzo nella matrice mat
                m.setCima(); // cima diventa la carta successiva nel mazzo
            }
        }
    }
    
    /**
     * Metodo cartaLibera()
     * Stabilisce se una carta è giocabile
     * i = righe, j = colonne
     * 
     * @param  i, j interi, 0<=i<=6, 0<=j<=6
     * @return t=true se la carta è libera, t=false altrimenti
     */
    public boolean cartaLibera(int i, int j) 
    {
        boolean t = true; 
        if (j<=i){ // eseguo il controllo solo se sono sotto la diagonale poichè le carte sopra la diagonale sono nulle
            if (mat[i][j].getNumero()!=0){ // controllo se la carta non è zero (cioè se non è eliminata)
                if (i!=6){ // l'ultima riga è sempre libera
                    if ((mat[i+1][j].getNumero() != 0)||(mat[i+1][j+1].getNumero() != 0)){ // la carta in posizione i,j in mat è libera se le carte che stanno in posizione i+1,j+1 
                                                                                       //e i+1,j hanno numero 0
                        t = false;
                    }
                }
            }
            else {t=false;}
        }
        else {t=false;}
        return t;
    }
    
    // /**
     // * Metodo ()
     // * Crea un mazzo ordinato composto da 52 carte, divise per i 4 semi ♥ ♦ ♣ ♠
     // * 
     // * @return    m array che contiene tutte le carte del mazzo
     // */
    // public void sostituisciSpazio(int i, int j){
        // x[i][j] = null;
    // }
    
    /**
     * Metodo getMat()
     * 
     * @return    mat
     */
    public Carta[][] getMat (){
        return mat;
    }
    
    /**
     * Metodo toStringPiramide()
     * Crea una matrice res di stringhe contenenti carte e spazi per poter stampare la piramide
     * Sotto ogni riga di carte c'è una riga di spazi
     * In ogni posizione della matrice res è presente una stringa di lunghezza due che contiene un numero e un seme oppure due spazi bianchi
     * 
     * @return    res matrice di stringhe
     */
    public String[][] toStringPiramide() 
    {
        String[][] res = new String[15][15];
        int i0;
        
        for (i0=0; i0<7; i0++){ // inserisco gli spazi alla sinistra della matrice 
            res[0][i0] = "  ";
        }
        
        int m=2; // m indica le righe della matrice res
        for (int k=5; k>0; k--){ // k indica le righe della matrice mat
            for (int i=0; i<k; i++){
                res[m][i] = "  ";
            }
            m = m+2; // salto la riga di spazi
        }
        
        // inserisco i valori in mat nella prima riga
        switch (mat[0][0].getNumero()){
        case 10 : res[0][7] = "X" + mat[0][0].getSeme(); break;
        case 11 : res[0][7] = "J" + mat[0][0].getSeme(); break;
        case 12 : res[0][7] = "Q" + mat[0][0].getSeme(); break;
        case 13 : res[0][7] = "K" + mat[0][0].getSeme(); break;
        case 1  : res[0][7] = "A" + mat[0][0].getSeme(); break;
        default : res[0][7] = mat[0][0].toStringCarta();
        }
        
        int a =1; // indica le righe in mat
        i0--; // indica la posizione in res della prima carta di ogni riga dalla seconda in poi
        while (a<=6){
            for(int i=0; i<=a; i++){
                if (mat[a][i].getNumero()==0){ // metto lo spazio se la carta è eliminata
                    res[a*2][i0+2*i] = "  "; // i0+2*i perchè tra una carta e l'altra è presente una stringa lunga due di spazi
                }
                else { // inserisco le carte nella matrice di stringhe
                    switch(mat[a][i].getNumero()){
                    case 10 : res[a*2][i0+2*i] = "X" + mat[a][i].getSeme(); break;
                    case 11 : res[a*2][i0+2*i] = "J" + mat[a][i].getSeme(); break;
                    case 12 : res[a*2][i0+2*i] = "Q" + mat[a][i].getSeme(); break;
                    case 13 : res[a*2][i0+2*i] = "K" + mat[a][i].getSeme(); break;
                    case 1  : res[a*2][i0+2*i] = "A" + mat[a][i].getSeme(); break;
                    default : res[a*2][i0+2*i] = mat[a][i].toStringCarta();
                    }
                }
            }
            i0--; // scendendo ci sono meno spazi a sinistra
            a++; // scendo di una riga
        }
        
        // mette due spazi dove la carta è nulla (sopra la diagonale)
        for (int i=0; i<14; i++){
            for (int j=0; j<14; j++){
                if (res[i][j]==null) {
                    res[i][j]= "  ";
                }
            }
        }
        return res;
    }
}
