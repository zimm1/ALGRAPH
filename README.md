# ALGRAPH

Implementazione visuale dell'algoritmo di Dijkstra.

* Trello (gestione attività): https://trello.com/b/xdQK3W6z
* GitHub: https://github.com/Zimm1/ALGRAPH

## Features

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
    
## Funzioni disponibili

* <b>Barra dei menu</b>
    * <b>Grafo</b>
        * <b>Genera: genera un grafo (orientato o non orientato) casualmente con i parametri inseriti da utente 
                    (massimo/minimo peso degli archi, massimo numero di nodi)</b>
        * <b>Salva: salva il grafo attuale su file</b>
        * <b>Apri: apre un grafo precedententemente salvato su file</b>
    * <b>Esecuzione</b>
        * <b>Passo: esegue la linea successiva dell'algoritmo di Dijkstra, al primo passo richiede il nodo di partenza</b>
        * <b>Esegui: esegue automaticamente gli steps dell'algoritmo di Dijkstra a una velocità scelta da utente</b>
        * <b>Pausa: sospende l'esecuzione automatica dell'algoritmo</b>
    * <b>Aiuto</b>
        * <b>Info: apre una mini-guida sul funzionamento dell'algoritmo di Dijkstra</b>
* <b>Click destro del mouse su un nodo</b>
    * <b>Crea arco da qui: crea un arco che ha come origine il nodo selezionato</b>
    * <b>Rimuovi nodo: elimina il nodo e gli archi a esso uscenti/entranti</b>
* <b>Click destro del mouse su un arco</b>
    * <b>Modifica peso arco: aggiorna il peso dell'arco con il peso inserito da utente</b>
    * <b>Inverti arco: se il grafo è orientato inverte il nodo di origine con quello di destinazione</b>
    * <b>Elimina arco: rimuove l'arco</b>
* <b>Click destro del mouse nella finestra centrale</b>
    * <b>Crea nodo: crea un nuovo nodo</b>
    
    
## Librerie

* <b>JAVAFX incluso nella versione standard di JAVA</b>

##  Requisiti

* <b>[JAVA 8.0](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) o versioni successive</b>

## Membri del team

* <b>Simone Cavazzoni</b> - [Website](https://simonecavazzoni.com) 
* <b>Stefano Notari</b>
* <b>Alessandro Tedeschi</b>