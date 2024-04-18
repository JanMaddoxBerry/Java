import java.util.*; 


class Graph { 
    class Edge implements Comparable<Edge> { 
        int src, dest, weight; 
        public int compareTo(Edge compareEdge) { 
            return this.weight - compareEdge.weight; 
        } 
    }; 
 
    int V, E;  
    Edge edge[]; 
 
    Graph(int v, int e) { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i = 0; i < e; ++i) 
            edge[i] = new Edge(); 
    } 

    int find(int subsets[], int i) { 
        if (subsets[i] == -1) 
            return i; 
        return find(subsets, subsets[i]); 
    } 

    void Union(int subsets[], int x, int y) { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 
        subsets[xroot] = yroot; 
    } 
    void KruskalMST() { 
        Edge result[] = new Edge[V]; 
        int e = 0; 
        int i = 0; 
        for (i = 0; i < V; ++i) 
            result[i] = new Edge(); 
        Arrays.sort(edge); 

        int subsets[] = new int[V]; 
        Arrays.fill(subsets, -1); 
        i = 0;  
        while (e < V - 1) { 
            Edge next_edge = new Edge(); 
            next_edge = edge[i++]; 

            int x = find(subsets, next_edge.src); 
            int y = find(subsets, next_edge.dest); 

            if (x != y) { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 
        } 
        System.out.println("Following are the edges in the constructed MST"); 
        for (i = 0; i < e; ++i) 
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight); 
    } 

    void PrimMST() { 
        int parent[] = new int[V]; 
        int key[] = new int[V]; 
        Boolean mstSet[] = new Boolean[V]; 
 
        for (int i = 0; i < V; i++) { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
        key[0] = 0; 
        parent[0] = -1; 

        for (int count = 0; count < V - 1; count++) { 
            int u = minKey(key, mstSet); 
            mstSet[u] = true; 
            for (int v = 0; v < V; v++) { 
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
            } 
        } 
        System.out.println("Following are the edges in the constructed MST"); 
        for (int i = 1; i < V; i++) 
            System.out.println(parent[i] + " -- " + i + " == " + graph[i][parent[i]]); 
    } 

    int minKey(int key[], Boolean mstSet[]) { 
        int min = Integer.MAX_VALUE, min_index = -1; 
        for (int v = 0; v < V; v++) { 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
        } 

        return min_index; 
    } 
} 

public class Main { 
    public static void main(String[] args) { 
        int V = 4; 
        int E = 5; 
        Graph graph = new Graph(V, E); 
 

        graph.edge[0].src = 0; 
        graph.edge[0].dest = 1; 
        graph.edge[0].weight = 10; 
 

        graph.edge[1].src = 0; 
        graph.edge[1].dest = 2; 
        graph.edge[1].weight = 6; 


        graph.edge[2].src = 0; 
        graph.edge[2].dest = 3; 
        graph.edge[2].weight = 5; 


        graph.edge[3].src = 1; 
        graph.edge[3].dest = 3; 
        graph.edge[3].weight = 15; 


        graph.edge[4].src = 2; 
        graph.edge[4].dest = 3; 
        graph.edge[4].weight = 4; 
 

        graph.KruskalMST(); 
        graph.PrimMST(); 
    } 
} 
