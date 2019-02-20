package cisco.graph.node;

import cisco.graph.node.interfaces.GNode;

import java.util.ArrayList;


public class GNodeImp implements GNode{

    private String name;
    private ArrayList<GNode> children = new ArrayList<GNode>();
    private boolean visited = false;

    public GNodeImp(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public GNode[] getChildren() {
        return children.toArray(new GNode[children.size()]);
    }

    public void addChild(GNode gNode) {
       this.children.add(gNode);
    }

}
