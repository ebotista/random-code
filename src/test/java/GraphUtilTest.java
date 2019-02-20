import cisco.graph.node.GNodeImp;
import cisco.graph.node.interfaces.GNode;
import cisco.graph.util.GraphUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;

public class GraphUtilTest {

    GNodeImp gNode;
    Map<String, GNodeImp> graph;


    @Before
    public void called_before_each() {

        gNode = new GNodeImp("a");

        String[] arrayNodeNames = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

        Stream<String> stream = Arrays.stream(arrayNodeNames);
        graph = stream.map(name -> new GNodeImp(name)).
                collect(Collectors.toMap(gNode -> gNode.getName(), gNode -> gNode));

    }

    GraphUtilTest buildGraph() {

        //make test graph
        //create root node
        graph.get("a").addChild(graph.get("b"));
        graph.get("a").addChild(graph.get("c"));
        graph.get("a").addChild(graph.get("d"));
        //child 1 of root
        graph.get("b").addChild(graph.get("e"));
        graph.get("b").addChild(graph.get("f"));
        //child 2 of root
        graph.get("c").addChild(graph.get("g"));
        graph.get("c").addChild(graph.get("h"));
        graph.get("c").addChild(graph.get("i"));
        //child 3 of root
        graph.get("d").addChild(graph.get("j"));

        return this;

    }

    int randomWithRange(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1) + min);
    }

    String message(int numberOfItemsExpected) {
        return "walkGraph did not return " + numberOfItemsExpected + " items in ArrayList";
    }

    @Test
    public void walkGraph_returns_arrayList() {

        gNode.addChild(new GNodeImp("b"));

        assertTrue(GraphUtil.walkGraph(gNode) instanceof ArrayList);

    }

    @Test
    public void walkGraph_returns_full_graph_with_every_GNode() {

        buildGraph();

        int numberOfItemsExpected = 10;

        assertTrue(GraphUtil.walkGraph(graph.get("a")).size() == numberOfItemsExpected);

    }

    @Test
    public void walkGraph_returns_partial_graph_starting_at_b_with_test() {

        buildGraph();

        int numberOfItemsExpected = 3;

        assertTrue(GraphUtil.walkGraph(graph.get("b")).size() == numberOfItemsExpected);

    }

    @Test
    public void walkGraph_returns_full_graph_with_every_GNode_test_should_not_return_duplicate() {

        buildGraph();

        //adding node e twice to node b
        graph.get("b").addChild(graph.get("e"));
        //adding new none repeating node
        graph.get("d").addChild(new GNodeImp("k"));

        int numberOfItemsExpected = 11;

        assertTrue(GraphUtil.walkGraph(graph.get("a")).size() == numberOfItemsExpected);

    }

    @Test
    public void paths_returns_children_of_node_in_ArrayList_of_ArrayList() {
        ArrayList<ArrayList<GNode>> paths = GraphUtil.paths(graph.get("a"));
        assertTrue(paths instanceof ArrayList && paths.get(0) instanceof ArrayList);
    }

    @Test
    public void paths_returns_children_of_node_in_ArrayList_of_ArrayList_representing_all_paths_from_starting_node_test_return_ArrayList_of_ArrayList() {
        buildGraph();
        ArrayList<ArrayList<GNode>> paths = GraphUtil.paths(graph.get("a"));
        System.out.print("A = (");
        for(ArrayList<GNode> subList : paths) {
            System.out.print("( ");
            for(GNode node : subList) {
                System.out.print(node.getName() + " ");
            }
            System.out.print(")");
        }
        System.out.println(")");
        assertTrue(paths.get(0).get(0).getName().equals("a") && paths.get(0).get(1).getName().equals("b") && paths.get(0).get(2).getName().equals("e") &&
                paths.get(5).get(0).getName().equals("a") && paths.get(5).get(1).getName().equals("d") && paths.get(5).get(2).getName().equals("j"));
    }

    @Test
    public void paths_returns_children_of_node_in_ArrayList_of_ArrayList_representing_all_paths_from_starting_node_test_return_ArrayList_of_ArrayList_C() {
        buildGraph();
        ArrayList<ArrayList<GNode>> paths = GraphUtil.paths(graph.get("c"));
        assertTrue(paths.get(0).get(0).getName().equals("c") && paths.get(0).get(1).getName().equals("g") && paths.get(1).get(0).getName().equals("c")
                && paths.get(1).get(1).getName().equals("h") && paths.get(2).get(1).getName().equals("i"));
    }

    @Test
    public void paths_returns_ArrayList_of_ArrayList_representing_one_node_from_starting_node_e() {
        buildGraph();
        ArrayList<ArrayList<GNode>> paths = GraphUtil.paths(graph.get("e"));
        assertTrue(paths.get(0).get(0).getName().equals("e"));
    }

}
