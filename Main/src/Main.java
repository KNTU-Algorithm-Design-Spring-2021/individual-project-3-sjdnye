import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {
   static class Graph{
        Set<Integer> vertices = new HashSet<>();
        List<List<Integer>> adjacency ;
        public Graph() {
            adjacency = new ArrayList<>();
        }
        public void add_vertex(int u, int v){
            if(u > 21 || u < 1 || v > 21 || v < 1){
                return;
            }
            vertices.add(u); vertices.add(v);

            if(adjacency.get(u) != null)
                adjacency.get(u).add(v);
            else{
                    adjacency.set(u, new ArrayList<>());
                    adjacency.get(u).add(v);
            }

            if(adjacency.get(v) != null)
                adjacency.get(v).add(u);
            else{
                adjacency.set(v, new ArrayList<>());
                adjacency.get(v).add(u);
            }

        }

       void getValidPath(int u, int v, boolean[] visited, Deque<Integer> path, List<List<Integer>> result) {
           visited[u] = true;
           path.add(u);
           if (u == v)
               result.add(new ArrayList<>(path));
           else if (adjacency.get(u) != null)
               for (Iterator iterator = adjacency.get(u).iterator() ; iterator.hasNext();) {
                   Integer i = (Integer) iterator.next();
                   if (!visited[i])
                       getValidPath(i, v, visited, path, result);
               }
           path.removeLast();
           visited[u] = false;
       }

       List<List<Integer>> findPath(int u, int v) {
           List<List<Integer>> result = new ArrayList<>();
           if (u == v) {
               List<Integer> temp = new ArrayList<>();
               temp.add(u);
               result.add(temp);
               return result;
           }

           boolean[] visited = new boolean[vertices.size()];
           Deque<Integer> path = new ArrayDeque<>();
           getValidPath(u, v, visited, path, result);
           return result;
       }

   }
    public static void main(String[] args) {
        String fileName = "input.txt";
        String line = null;
        int Case = 1;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null){

                Graph g = new Graph();
                int destination = (int)br.read();
                int a,b;
                line = br.readLine();
                a = (int)line.charAt(0);
                b = (int)line.charAt(2);
                while (a != 0 && b != 0){
                    g.add_vertex(a , b);
                    line = br.readLine();
                    a = (int)line.charAt(0);
                    b = (int)line.charAt(2);
                }
                System.out.println("CASE " + Case + ":");
                List<List<Integer>> results = g.findPath(1, destination);

            }

            br.close();
        }


        catch (IOException e){
            e.printStackTrace();
        }


    }
}
