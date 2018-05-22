# ALGRAPH

## Introduzione

Implementazione visuale dell'algoritmo di Dijkstra.

* <b>Trello</b> (gestione attività): https://trello.com/b/xdQK3W6z
* <b>GitHub</b>: https://github.com/Zimm1/ALGRAPH
* <b>JavaDoc</b>: https://zimm1.github.io/ALGRAPH
* <b>Ultima versione & JAR</b>: https://github.com/Zimm1/ALGRAPH/releases/latest

### Caratteristiche

* Esecuzione <b>passo - passo</b>
* Esecuzione <b>automatica</b>
* Visualizzazione della <b>coda di priorità</b>
* Indicatore della linea di <b>pseudo-codice</b> eseguita
* <b>Generazione casuale</b> di un grafo (orientato o non orientato)
* <b>Salvataggio</b> di un grafo (orientato o non orientato) su <b>file</b>
* <b>Apertura</b> di un grafo (orientato o non orientato) da <b>file</b>
* <b>Modifica</b> di un grafo:
    * <b>Aggiunta e rimozione di un nodo</b>
    * <b>Aggiunta e rimozione di un arco (orientato o non orientato)</b>
    * <b>Modifica del peso di un arco</b>
    * <b>Invertimento della direzione di un arco (se orientato)</b>

## Scelte implementative

### MVCS - Model View Controller Service
Il progetto è stato organizzato in package e classi in modo da attenersi il più possibile all'architettura <b>MVCS</b>:<br>
* <b>Model</b>: classi base di dati, come Node, Edge, Graph...<br>
* <b>View</b>: classi per la gestione della parte grafica di ogni oggetto
* <b>Controller</b>: mettono in comunicazione View e Model, gestiscono i vari componenti dell'interfaccia
e utilizzano i Service per fornire funzionalità aggiuntive
* <b>Service</b>: hanno lo scopo di effettuare operazioni in background come salvare, aprire o generare un grafo

### Struttura dati Grafo
Per la gestione delle informazioni relative al grafo e alla sua rappresentazione visuale,
si è scelto di utilizzare una <b>HashMap</b> avente come chiavi tutti i nodi del grafo,
a ognuno dei quali è associato un <b>HashSet</b> contenente gli archi che partono dal suddetto nodo chiave.<br>
In questo modo, i dati sono stati gestiti in modo semplice e veloce utilizzando la programmazione funzionale
grazie alla classe <b>Stream</b>, introdotta con <b>Java 8</b>.

### Esecuzione passo-passo temporizzata
Nelle modalità di esecuzione dell'algoritmo è stata aggiunta la possibilità di seguire l'andamento
passo-passo in modo <b>automatizzato</b>, tramite uno <b>slider</b> che permette di scegliere la velocità alla quale
le operazioni si susseguono.<br>
Se la velocità viene impostata al massimo, l'intera esecuzione viene svolta <b>istantaneamente</b>.

### Visualizzazione Coda con Priorità

La coda con priorità è una <b>lista non ordinata</b> contenente i nodi del grafo che non sono ancora stati scoperti.<br>
Durante l'esecuzione l'algoritmo estrae il nodo con distanza minima.<br>
Nella visualizzazione della coda con priorità viene mostrato l'elemento correntemente estratto dalla coda 
(Vuoto se non è ancora stato estratto nessun nodo) e la coda.<br>
gni elemento della coda vengono visualizzate le seguenti informazioni:
* <b>Nome del nodo</b>
* <b>Distanza dal nodo di partenza</b>

Durante l'esecuzione vengono modificati i colori di distanze e archi:

* <b>![#F44336](https://placehold.it/15/F44336/000000?text=+) Rosso</b>
    * Distanza del <b>nodo appena estratto</b> dalla coda <b>(u)</b>
    * Nodi e archi già estratti e utilizzati
* <b>![#34A853](https://placehold.it/15/34A853/000000?text=+) Verde</b>
    * <b>Distanza</b> del nodo in coda adiacente al nodo estratto <b>(v)</b>
* <b>![#4285F4](https://placehold.it/15/4285F4/000000?text=+) Blu</b>
    * Peso dell'<b>arco (u-v)</b>
    * <b>Arco correntemente analizzato</b>

### Visualizzazione pseudo-codice

La visualizzazione del pseudo-codice dell'algoritmo di Dijkstra contiene le righe di codice dell'algoritmo stesso, e in aggiunta
implementa animazioni il cui scopo è quello di rendere più chiaro all'utente le scelte che l'algoritmo compie.<br>
Animazioni del codice:
* <b>Indicatore della linea in esecuzione</b>
    * Evidenzia in <b>![#F44336](https://placehold.it/15/F44336/000000?text=+) rosso</b> la <b>linea</b> che è stata <b>eseguita</b>
* <b>Variabili attuali</b>
    * Mostra i nomi dei <b>nodi che si stanno considerando</b>
* <b>If - Statement</b>
    * Visualizza la <b>condizione</b> con i <b>valori attuali</b>

### File Picker (caricamento file da schermo)
E' stato preferito utilizzare un <b>file picker</b> per il caricamento e il salvataggio di file da sistema in modo dinamico,
così da evitare la modifica del codice ogni qual volta si vuole utilizzare una sotto-cartella differente.

### Sintassi File
I file utilizzati dal programma sono strutturati nel seguente modo:<br>
* <b>Prima riga</b>
    * 1 se il grafo è orientato, 0 altrimenti
* <b>Seconda riga</b>
    * N stringhe separate da spazio, rappresentanti i nomi dei nodi che compongono il grafo
* <b>Successive M righe</b>
    * contengono il nome del nodo di inizio, quello del nodo di arrivo e il peso di un arco, in questo ordine, separati da spazi

<i><b>Legenda</b>
* N = Numero di Nodi
* M = Numero di Archi
</i>

### Material Color
I colori usati sono indicati da <b>Google</b> nella <b>[Guida ufficiale di Material Design](https://material.io/design/color/the-color-system.html#color-usage-palettes)</b>.<br>
In questo modo l'interfaccia grafica del programma risulta all'utente più <b>gradevole</b> e <b>moderna</b>.

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