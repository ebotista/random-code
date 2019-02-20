package cisco.graph.util;

import cisco.graph.node.GNodeImp;
import cisco.graph.node.interfaces.GNode;

import java.util.*;

public class GraphUtil {

    public static ArrayList walkGraph(GNode gNode) {

        return walkGraphFromNode((gNode));

    }

    public static ArrayList<ArrayList<GNode>> paths(GNode gNode) {

        return pathsTraversal(gNode);

    }

    private static ArrayList<GNode> walkGraphFromNode(GNode gNode) {

        if(gNode.getChildren().length == 0) {
            ArrayList<GNode> empty = new ArrayList<GNode>();
            empty.add(gNode);
            return empty;
        }

        // the interface does not provide a visited method
        // using map to track visited for ease of use
        Map<String, GNode> visited = new HashMap<>();
        Queue<GNode> queue = new LinkedList();

        //add first node to end of queue
        queue.add(gNode);

        while(!queue.isEmpty()) {

            //pop first in queue
            GNode currentNode = queue.remove();

            visited.put(currentNode.getName(), currentNode);

            for(GNode sibling : currentNode.getChildren()) {
                if(!visited.containsKey(sibling.getName())) {
                    queue.add(sibling);
                }
            }

        }

        return new ArrayList<GNode>(visited.values());

    }

    private static void traversal(GNode gNode, Map<String, GNode> visited, Stack path, ArrayList<ArrayList<GNode>> paths) {

        visited.put(gNode.getName(), gNode);

        for(GNode node : gNode.getChildren()) {
            if(!visited.containsKey(node.getName())) {
                path.add(node);
                traversal(node, visited, path, paths);
            }

        }

        if(gNode.getChildren().length == 0)
            paths.add(new ArrayList<>(path));

        path.pop();

        return;

    }

    private static ArrayList<ArrayList<GNode>> pathsTraversal(GNode gNode) {

        Map<String, GNode> visited = new HashMap<>();
        ArrayList<ArrayList<GNode>> paths = new ArrayList();
        Stack<GNode> path = new Stack();

        path.add(gNode);
        traversal(gNode, visited, path, paths);

        return paths;

    }

}
