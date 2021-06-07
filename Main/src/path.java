
import java.util.*;
import java.io.*;

public class path {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\Asus\\Desktop\\input.txt";
        String line = null;
        int Case = 1;
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                boolean[][] graph = new boolean[22][22];
                Set<Integer> vertices = new HashSet<>();
                List<Integer> Path = new ArrayList<>();


                int destination = Integer.parseInt(String.valueOf(line.charAt(0)));
                int a, b;
                line = br.readLine();
                a = Integer.parseInt(String.valueOf(line.charAt(0)));
                b = Integer.parseInt(String.valueOf(line.charAt(2)));
                vertices.add(a);
                vertices.add(b);
                graph[a][b] = true;

                while (a != 0) {

                    line = br.readLine();
                    a = Integer.parseInt(String.valueOf(line.charAt(0)));
                    b = Integer.parseInt(String.valueOf(line.charAt(2)));
                    vertices.add(a);
                    vertices.add(b);
                    graph[a][b] = true;

                }
                boolean[] visited = new boolean[vertices.size()+1];
                visited[1] = true ;
                Path.add(1);

                find_station(graph, 2, destination, visited, Path);

                System.out.println("CASE " + Case + " ");
                System.out.println(" ");
                Case++;

            }
            br.close();
        }



             catch (IOException e){
                e.printStackTrace();
            }
    }
    public  static void find_station(boolean[][] graph,int i,int destination, boolean[] visited, List<Integer> Path){

        if(promising(i,graph,destination,visited,Path)){
           if(Path.contains(destination)){

               for(Iterator iterator = Path.iterator(); iterator.hasNext();){
                   Integer q = (Integer) iterator.next();
                   System.out.print(q+" -> ");
               }

           }
           else{
               Path.add(i+1);
               visited[i+1] = true;
               find_station(graph,i+1,destination,visited,Path);


           }


        }


    }

    public static boolean promising(int i,boolean[][] graph,int destination, boolean[] visited, List<Integer> Path){

        boolean Switch = false;
        for (int j=1;j<22;j++){
            if(graph[i][j] && !(visited[j]) && graph[Path.get(Path.size()-1)][j]){
                Switch = true;

                if(graph[i][destination]){
                    Path.add(destination);
                }
                break;
            }
        }

        return Switch;

    }
}
