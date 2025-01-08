package test;
import graph.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    private String name ;
    private List<Node> edges;
    private Message message;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<Node>();
        this.message = null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Node> getEdges() {
        return edges;
    }
    public void setEdges(List<Node> edges) {
        this.edges = edges;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }

    public void addEdge(Node node) {
        if (!edges.contains(node)) {
            edges.add(node);
        }
    }

    private boolean dfs(Node node, Set<Node> finishedNodes,Set<Node> processedNodes ) {
        if (processedNodes.contains(node)){return true;}

        if(finishedNodes.contains(node)){return false;}

        finishedNodes.add(node);
        processedNodes.add(node);

        for(Node son: node.getEdges()){
            if(dfs(son,finishedNodes,processedNodes)){
                return true;
            }
        }
        processedNodes.remove(node);
        return false;
    }

    public boolean hasCycles(){
        Set<Node> finishedNodes = new HashSet<Node>();
        Set<Node> processedNodes = new HashSet<Node>();

        return dfs(this,finishedNodes,processedNodes);

    }




}
