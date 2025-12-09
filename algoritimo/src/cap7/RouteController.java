// Isto é um exemplo de um "Controller" do Spring Boot
package cap7;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class RouteController {

    // Representação simples de um "pedido" da UI
    static class GraphRequest {
        public List<Node> nodes; // Nós (com id, x, y)
        public List<Edge> edges; // Arestas (com v1, v2, peso)
    }

    @PostMapping("/optimize") // A UI vai chamar esta URL
    public PathResponse optimizeRoute(@RequestBody GraphRequest request) {
        
        // 1. RECEBER DADOS DA UI
        // Criar o Grafo a partir do 'request'
        int numVertices = request.nodes.size();
        Grafo grafo = new Grafo(numVertices);
        
        for (Edge edge : request.edges) {
            grafo.insereAresta(edge.v1, edge.v2, edge.peso);
        }

        try {
            // 2. EXECUTAR SEU CÓDIGO DIJKSTRA
            Dijkstra dijkstra = new Dijkstra(grafo);
            int raiz = 0; // Por exemplo, começar da raiz 0
            dijkstra.obterArvoreCMC(raiz);

            // 3. PREPARAR A RESPOSTA
            // Montar o caminho para um destino (ex: 4)
            int destino = 4;
            List<Integer> optimizedPath = dijkstra.getSequentialPath(raiz, destino); // Você precisaria criar este método
            
            // 4. ENVIAR A RESPOSTA DE VOLTA PARA A UI
            return new PathResponse(optimizedPath, dijkstra.peso(destino));

        } catch (Exception e) {
            return new PathResponse(null, 0.0); // Tratar erro
        }
    }
}

// Nota: Você precisaria de classes auxiliares como PathResponse, Node, Edge
// e um método getSequentialPath() no seu Dijkstra para retornar a lista do caminho.