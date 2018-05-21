package com.simonecavazzoni.algraph.res;

public final class Strings {
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
            pseudo_code_add_root = "S.add(r)",
            pseudo_code_while = "while not S.isEmpty() do",
            //u2190 left arrow
            pseudo_code_pop_item = "\tu \u2190 S.pop()",
            //u2208 element of
            pseudo_code_foreach = "\tforeach v \u2208 to G.adj(u) do",
            pseudo_code_condition = "\t\tif d[u] + w(u, v) < d[v] then",
            pseudo_code_exist_item = "\t\t\tif not b[v] then",
            pseudo_code_insert_item = "\t\t\t\tS.insert(v, d[u] + w(u, v))",
            pseudo_code_update_priority = "\t\t\telse S.update(v, d[u] + w(u, v))",
            pseudo_code_update_distance = "\t\t\td[v] \u2190 d[u] + w(u, v)",
            pseudo_code_update_tree = "\t\t\tT[v] \u2190 u",
            pseudo_code_title = "Algoritmo di Dijkstra",
            program_files = "ALGR file",
            program_extension = "algr",
            all_files = "Tutti i file",
            untitled = "Senza titolo",
            num_nodes = "Numero nodi:",
            min_weight = "Peso minimo:",
            max_weight = "Peso massimo:",
            error_generate = "Impossibile generare grafo",
            directed_input = "Orientato:",
            choose_root_title = "Radice",
            choose_root_header = "Selezionare la radice di partenza",
            choose_root_content = "Nodo di partenza:",
            choose_root_error_header = "Nodo inesistente",
            choose_root_error_content = "Inserire un nodo presente nel grafo",
            algorithm_info_title = "Info",
            algorithm_info_description = "L'algoritmo di Dijkstra viene usato per trovare il percorso minimo tra due nodi a e b.\n" +
                    "L'algoritmo aggiunge alla coda i nodi ancora non scoperti, calcola la distanza dei nodi adiacenti (non scoperti) e se minore aggiorna" +
                    " la distanza del nodo.\nMarca il nodo di rosso quando ha esaurito le adiacenze.",
            algorithm_info_hypothesis = "Tutti i pesi sono positivi",
            algorithm_info_hypothesis_1 = "Ogni nodo viene estratto una e una sola volta",
            algorithm_info_hypothesis_2 = "Al momento dell'estrazione la sua distanza è minima",
            algorithm_info_data_structure_title = "Struttura di dati",
            algorithm_info_fata_structure_description = "Coda con priorità, realizzata tramite vettore/lista non ordinati",
            algorithm_info_hypothesis_title = "Ipotesi",
            slow = "Lento",
            instant = "Veloce",
            execute_pause = "Pausa",
            help = "Aiuto",
            graph_type = "Tipo di grafo",
            graph_type_choice = "Scegli il tipo di grafo",
            type = "Tipo:",
            directed = "Orientato",
            undirected = "Non Orientato",
            error_file_header = "Salvataggio grafo",
            error_file_content = "Il grafo non esiste, impossibile salvare",
            priority_queue_title = "Coda con priorità";
}