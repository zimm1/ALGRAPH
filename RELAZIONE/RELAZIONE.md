# ALGRAPH

## Introduzione

Implementazione visuale dell'algoritmo di Dijkstra.

* Trello (gestione attività): https://trello.com/b/xdQK3W6z
* GitHub: https://github.com/Zimm1/ALGRAPH

### Caratteristiche

* <b>Esecuzione passo a passo</b>
* <b>Esecuzione automatica</b>
* <b>Visualizzazione della coda di priorità</b>
* <b>Indicatore della linea di pseudo-codice in esecuzione</b>
* <b>Generazione casuale di un Grafo (orientato o non orientato)</b>
* <b>Salvare Grafo (orientato o non orientato) su file</b>
* <b>Aprire Grafo (orientato o non orientato) da file</b>
* <b>Modifiche possibili da apportare al grafo: </b>
    * <b>Aggiungere nodo</b>
    * <b>Rimuovere nodo</b>
    * <b>Aggiungere arco (orientato o non orientato)</b>
    * <b>Rimuovere arco (orientato o non orientato)</b>
    * <b>Modificare peso arco</b>
    * <b>Invertire arco (se orientato)</b>

## Scelte implementative

### MVCS - Model View Controller Service
Il progetto è stato organizzato in package e classi in modo da attenersi il più possibile all'architettura <b>MVCS</b>:<br>
- <b>Model</b>: classi base di dati, come Node, Edge, Graph...<br>
- <b>View</b>: classi per la gestione della parte grafica di ogni oggetto
- <b>Controller</b>: mettono in comunicazione View e Model, gestiscono i vari componenti dell'interfaccia
e utilizzano i Service per fornire funzionalità aggiuntive
- <b>Service</b>: hanno lo scopo di effettuare operazioni in background come salvare, aprire o generare un grafo

### Strutture dati
Per la gestione delle informazioni relative al grafo e alla sua rappresentazione visuale,
si è scelto di utilizzare una <b>HashMap</b> avente come chiavi tutti i nodi del grafo,
a ognuno dei quali è associato un <b>HashSet</b> contenente gli archi che partono dal suddetto nodo chiave.<br>
In questo modo, i dati sono stati gestiti in modo semplice e veloce utilizzando la programmazione funzionale
grazie alla classe <b>Stream</b>, introdotta con <b>Java 8</b>.

### Esecuzione passo-passo temporizzata
Nelle modalità di esecuzione dell'algoritmo è stata aggiunta la possibilità di seguire l'andamento
passo-passo in modo automatizzato, tramite uno slider che permette di scegliere la velocità alla quale
le operazioni si susseguono.<br>
Se la velocità viene impostata al massimo, l'intera esecuzione viene svolta istantaneamente.


## Strumenti utilizzati

Durante lo sviluppo il team si è appoggiato ad alcuni fondamentali strumenti:

### Git
* Tecnologia che agevola lo sviluppo in contemporanea di un progetto riducendo drasticamente i conflitti tra parti di codice diverse
* Permette di tenere la cronologia di tutte le modifiche apportate (repository)
* Rende il progetto facilmente condivisibile ad altri sviluppatori per la visualizzazione, l'utilizzo o la modifica
* GitHub
    * Principale servizio di hosting per repository Git
    * Usato grazie ai benefici dell'account universitario UniBo

### Trello
* Eccellente strumento per la gestione di attività, incarichi e andamento di un progetto
* Permette di organizzare lo sviluppo in micro-attività assegnabili a membri differenti del team, con la possibilità di controllarne lo stato di avanzamento e l'effettivo completamento

### IntelliJ IDEA
* IDE sviluppato da JetBrains per lo sviluppo in Java e altri linguaggi
* Fornisce molteplici strumenti, come l'integrazione con Git, l'auto-completamento intelligente e il supporto per linguaggio MarkDown
* E' stata utilizzata una piattaforma di sviluppo comune per ridurre al minimo i problemi di compatibilità interni al team
* Licenza inclusa nei benefici dell'account universitario UniBo

### MarkDown
* Linguaggio di markup con una semplice sintassi che permette la creazione di documenti di testo convertibili facilmente in molteplici formati come HTML, DOC, PDF, TXT, Rich Text Format (RTF)
* Usato per la creazione del ReadMe e della Relazione
* Supportato in modo nativo da GitHub per fornire una descrizione del progetto


## Note Finali

Il progetto è stato pensato, realizzato e documentato per essere reso pubblico e open-source,
in modo da fornire un valido aiuto nella comprensione dell'algoritmo di Dijkstra su diversi tipi di grafo.

### Il team

* <b>Simone Cavazzoni</b> - [Website](https://simonecavazzoni.com) 
* <b>Stefano Notari</b>
* <b>Alessandro Tedeschi</b>