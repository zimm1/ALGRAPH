# ALGRAPH

Implementazione visuale dell'algoritmo di Dijkstra.

* <b>Trello</b> (gestione attività): https://trello.com/b/xdQK3W6z
* <b>GitHub</b>: https://github.com/Zimm1/ALGRAPH
* <b>JavaDoc</b>: https://zimm1.github.io/ALGRAPH

## Caratteristiche

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
    
## Funzionalità disponibili

* <b>Barra dei menù</b>
    * <b>Grafo</b>
        * <b>Genera</b>
            * Genera un grafo (orientato o non orientato) casualmente con i parametri inseriti da utente 
                    (massimo/minimo peso degli archi, massimo numero di nodi)
        * <b>Salva</b>
            * Salva il grafo attuale su file
        * <b>Apri</b>
            * Apre un grafo precedentemente salvato su file
    * <b>Esecuzione</b>
        * <b>Passo</b>
            * Esegue la linea successiva dell'algoritmo di Dijkstra, al primo passo richiede il nodo di partenza
        * <b>Esegui</b>
            * Esegue automaticamente gli steps dell'algoritmo di Dijkstra alla velocità scelta da utente
        * <b>Pausa</b>
            * Sospende l'esecuzione automatica dell'algoritmo
        * <b>Slider</b>
            * Seleziona la velocità di esecuzione automatica (massimo = istantanea)
    * <b>Aiuto</b>
        * <b>Info</b>
            * Apre una finestra di informazioni sul funzionamento dell'algoritmo di Dijkstra
* <b>Click destro sopra un nodo</b>
    * <b>Crea arco da qui</b>
        * Crea un arco che ha come origine il nodo selezionato
    * <b>Rimuovi nodo</b>
        * Elimina il nodo selezionato e gli archi uscenti/entranti
* <b>Click destro del mouse sopra un arco</b>
    * <b>Modifica peso arco</b>
        * Aggiorna il peso dell'arco selezionato con il peso inserito da utente
    * <b>Inverti arco</b>
        * Se il grafo è orientato inverte il nodo di origine con quello di destinazione
    * <b>Elimina arco</b>
        * Rimuove l'arco selezionato
* <b>Click destro del mouse nella finestra centrale</b>
    * <b>Crea nodo</b>
        * Crea un nuovo nodo
* <b>Click sinistro del mouse sopra un nodo</b>
    * Sposta il nodo selezionato tramite trascinamento
    
    
## Librerie

* <b>JavaFX</b> incluso nella versione standard di <b>Java</b>

##  Requisiti

* <b>[JAVA 8.0](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)</b> o versioni successive

## Membri del team

* <b>Simone Cavazzoni</b> - [Website](https://simonecavazzoni.com) 
* <b>Stefano Notari</b>
* <b>Alessandro Tedeschi</b>