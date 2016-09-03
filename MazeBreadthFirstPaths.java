import java.util.Observable;
import java.util.LinkedList;
/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze; 

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;    
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {
        LinkedList<Integer> fringe = new LinkedList<Integer>();
        fringe.add(s);
        marked[s] = true;
        announce();
        while(!fringe.isEmpty()) {
            int v = fringe.remove();
            if (v == t) {
                targetFound = true;
            }
            if (targetFound) {
                return;
            }
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    fringe.add(w);
                    edgeTo[w] = v;
                    marked[w] = true;  
                    distTo[w] = distTo[v] + 1;
                    announce();             
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs(s);
    }
}

