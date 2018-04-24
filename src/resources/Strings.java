package resources;

public abstract class Strings {
    public static final String
            program_title = "ALGRAPH",
            remove_node = "Rimuovi nodo",
            create_node = "Crea nodo",
            name = "Nome:",
            error = "Errore",
            error_create_node = "Impossibile creare nuovo nodo",
            remove_edge = "Rimuovi arco",
            change_weight = "Modifica peso",
            weight = "Peso:",
            error_change_weight = "Impossibile modificare il peso",
            create_edge_from_here = "Crea arco da qui",
            create_edge = "Crea arco",
            error_create_edge = "Impossibile creare nuovo arco",
            error_min_weight = "Il peso non può essere minore di ",
            change_direction = "Inverti direzione",
            generate_graph = "Genera Nuovo Grafo",
            generate = "Genera",
            open = "Apri",
            open_file = "Apri Grafo da File",
            graph = "Grafo",
            save = "Salva",
            save_file = "Salva Grafo su File ",
            step = "Passo",
            execute_step = "Esegui un Passo",
            execute_all = "Esegui",
            execute_reset = "Reset",
            execution = "Esecuzione",
            emptyQueue = "Vuoto",
            pseudoCodeAddRootInPriorityQueue = "S.add(r)",
            pseudoCodeWhile = "while not S.isEmpty() do",
            //u2190 left arrow
            pseudoCodePopItem = "\tu \u2190 S.pop()",
            //u2208 element of
            pseudoCodeForeach = "\tforeach v \u2208 to G.adj(u) do",
            pseudoCodeCondition = "\t\tif d[u] + w(u, v) < d[v] then",
            pseudoCodeExistItemInPriorityQueueCondition = "\t\t\tif not b[v] then",
            pseudoCodeInsertItemInQueue = "\t\t\t\tS.insert(v, d[u] + w(u, v))",
            pseudoCodeUpdatePriority = "\t\t\telse S.update(v, d[u] + w(u, v))",
            pseudoCodeUpdateDistanceArray = "\t\t\td[v] \u2190 d[u] + w(u, v)",
            pseudoCodeUpdateTree = "\t\t\tT[v] \u2190 u",
            pseudoCodeTitle = "Algoritmo di Dijkstra",
            program_files = "ALGR file",
            program_extension = "algr",
            all_files = "Tutti i file",
            untitled = "Senza titolo",
            num_nodes = "Numero nodi:",
            min_weight = "Peso minimo:",
            max_weight = "Peso massimo:",
            error_generate = "Impossibile generare grafo",
            directed = "Orientato:",
            chooseRootTitle = "Radice",
            chooseRootHeader = "Selezionare la radice di partenza",
            chooseRootContent = "Inserire il nodo di partenza: ",
            chooseRootErrorTitle = "Errore",
            chooseRootErrorHeader = "Nodo inesistente",
            chooseRootErrorContent = "Inserire un nodo presente nel grafo",
            algorithmInfoTitle = "Info",
            algorithmInfoDescription = "L'algoritmo di Dijkstra viene usato per trovare il percorso minimo tra due nodi a e b.\n" +
                    "L'algoritmo aggiunge alla coda i nodi ancora non scoperti, calcola la distanza dei nodi adiacenti (non scoperti) e se minore aggiorna" +
                    " la distanza del nodo.\nMarca il nodo di rosso quando ha esaurito le adiacenze.",
            algorithmInfoHypothesis = "Tutti i pesi sono positivi",
            algorithmInfoHypothesis1 = "Ogni nodo viene estratto una e una sola volta",
            algorithmInfoHypothesis2 = "Al momento dell'estrazione la sua distanza è minima",
            algorithmInfoDataStructureTitle = "Struttura di dati",
            algorithmInfoDataStructureDescription = "Coda con priorità, realizzata tramite vettore/lista non ordinati",
            algorithmInfoHypothesisTitle = "Ipotesi";
}
