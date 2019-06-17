----------------------------------------------------------------------------------
******** File README del progetto ********    
----------------------------------------------------------------------------------

NOME DEL PROGETTO: Piramide

OBIETTIVO DEL PROGETTO: simulare il gioco "Pyramid" del solitario.

DATA: 05/06/2019 

COME AVVIARE IL PROGETTO: avviare il main nella classe Partita.

AUTORI:Letizia Paccini, Sofia Rizzotto, Alberto Ambrosi, Alessandro Signoretto

ISTRUZIONI PER L'UTENTE: una volta avviato il programma, premere invio per fare la mossa. Al termine della partita premere s se si vuole iniziare una nuova partita, premere n 
per uscire.

REGOLE DEL GIOCO:
Si ha a disposizione un mazzo di 52 carte francesi.
Con il mazzo mescolato si forma una piramide di 28 carte disposte su sette file, partendo da una carta al vertice superiore per finire con sette carte alla base. 
All'inizio sono utilizzabili solo le sette carte della fila in basso. Man mano che si abbineranno le carte, si renderanno libere le carte sovrastanti. 
Si possono eliminare coppie di carte la cui somma sia tredici, i Re si scartano da soli. Dal mazzo si può prendere una carta alla volta, abbinarla con una carta 
della piramide o spostarla nel pozzo. Si può scorrere il mazzo fino a tre volte. Si vince se si riesce a eliminare tutte le carte dalla piramide.

IMPLEMENTAZIONE:
- Classe Carta: una carta è un oggetto costituito da una coppia numero/seme.

- Classe Mazzo: un mazzo è un oggetto costituito da un array di 52 carte. All'inizio della partita viene mescolato e 28 delle sue carte vengono inserite nella piramide. Inoltre
quando il mazzo non ha più carte, le carte del pozzo vengono trasferite nel mazzo per un massimo di due volte. E' possibile utilizzare la prima carta del mazzo per trovare una 
combinazione con le carte della piramide. Se non ci sono combinazioni possibili, la prima carta del mazzo viene spostata nel pozzo.

- Classe Pozzo: un pozzo è un oggetto costituito da un array contenete al massimo 52 carte. Il pozzo contiene le carte scartate dal mazzo. E' possibile utilizzare l'ultima
carta aggiunta nel pozzo per trovare una combinazione con le carte della piramide. Tutte le volte che vengono spostate le carte dal pozzo al mazzo, il pozzo viene azzerato.

- Classe Piramide: una piramide è un oggetto costituito da una matrice di 28 carte. Le carte libere sono quelle che non hanno carte immediatamente sottostanti. 

- Classe Partita: una partita è un oggetto costituito da un mazzo, un pozzo, una piramide. Durante la partita si cercano combinazioni prima nella piramide, poi tra il pozzo e la 
piramide, poi tra il mazzo e la piramide. Infatti eliminare due carte dalla piramide è la giocata più conveniente; invece è preferibile eliminarne una dal pozzo rispetto al mazzo
poichè in questo modo risulta giocabile la successiva carta del pozzo, la quale altrimenti sarebbe stata disponibile solo nel giro del mazzo successivo. 