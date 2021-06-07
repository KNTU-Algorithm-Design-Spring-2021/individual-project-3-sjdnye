import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
public class Main {
   static class Graph{
//        Set<Integer> vertices = new HashSet<>();
//       Map<Integer, List<Integer>> adjacencyList;
//
//       Graph() {
//           adjacencyList = new HashMap<>();
//       }
//
//       public void add_vertex(int u, int v){
//            if(u > 21 || u < 1 || v > 21 || v < 1){
//                return;
//            }
//            vertices.add(u); vertices.add(v);
//
//            if (!adjacencyList.containsKey(u))
//                adjacencyList.put(u, new ArrayList<>());
//            adjacencyList.get(u).add(v);
//
//            if (!adjacencyList.containsKey(v))
//                adjacencyList.put(v, new ArrayList<>());
//            adjacencyList.get(v).add(u);
//
//        }
//
//       void getValidPath(int u, int v, boolean[] visited, Deque<Integer> path, List<List<Integer>> result) {
//           visited[u] = true;
//           path.add(u);
//           if (u == v)
//               result.add(new ArrayList<>(path));
//           else if (adjacencyList.get(u) != null)
//               for (Iterator iterator = adjacencyList.get(u).iterator() ; iterator.hasNext();) {
//                   Integer i = (Integer) iterator.next();
//                   if (!visited[i])
//                       getValidPath(i, v, visited, path, result);
//               }
//           path.removeLast();
//           visited[u] = false;
//       }
//
//       List<List<Integer>> findPath(int u, int v) {
//           List<List<Integer>> result = new ArrayList<>();
//           if (u == v) {
//               List<Integer> temp = new ArrayList<>();
//               temp.add(u);
//               result.add(temp);
//               return result;
//           }
//
//           boolean[] visited = new boolean[vertices.size()];
//           Deque<Integer> path = new ArrayDeque<>();
//           getValidPath(u, v, visited, path, result);
//           return result;
//       }
//
//   }
Set<Integer> vertices = new HashSet<>();
       Map<Integer, List<Integer>> adjacencyList;

       Graph() {
           adjacencyList = new HashMap<>();
       }

       void add_vertex(int u, int v) {
           vertices.add(u);
           vertices.add(v);

           if (!adjacencyList.containsKey(u))
               adjacencyList.put(u, new ArrayList<>());
           adjacencyList.get(u).add(v);

           if (!adjacencyList.containsKey(v))
               adjacencyList.put(v, new ArrayList<>());
           adjacencyList.get(v).add(u);
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
           getAllPathsDFS(u, v, visited, path, result);
           return result;
       }

       void getAllPathsDFS(int u, int v, boolean[] visited, Deque<Integer> path, List<List<Integer>> result) {
           visited[u] = true;
           path.add(u);
           if (u == v)
               result.add(new ArrayList<>(path));
           else if (adjacencyList.containsKey(u))
               for (Integer i : adjacencyList.get(u))
                   if (!visited[i])
                       getAllPathsDFS(i, v, visited, path, result);
           path.removeLast();
           visited[u] = false;
       }
   }
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Asus\\Desktop\\input.txt";
        String line = null;
        int Case = 1;
        try{
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while((line = br.readLine()) != null){

                Graph g = new Graph();
                int destination = Integer.parseInt(String.valueOf(line.charAt(0)));
                int a,b;
                line = br.readLine();
                a =Integer.parseInt(String.valueOf(line.charAt(0)));
                b = Integer.parseInt(String.valueOf(line.charAt(2)));

                while (a != 0){
                    g.add_vertex(a , b);
                    line = br.readLine();
                    a = Integer.parseInt(String.valueOf(line.charAt(0)));
                    b = Integer.parseInt(String.valueOf(line.charAt(2)));
                }
                System.out.println("CASE " + Case + ":");
                List<List<Integer>> results = g.findPath(1, destination);
                for (List<Integer> l : results) {
                    StringBuilder sb = new StringBuilder();
                    for (int s : l) {
                        sb.append(s);
                        sb.append("\t");
                    }
                    System.out.println(sb);
                }
               Case++;

            }
            br.close();

        }

        catch (IOException e){
            e.printStackTrace();
        }


    }
}
