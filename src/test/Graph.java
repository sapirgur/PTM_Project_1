package test;

import graph.Agent;
import graph.Topic;
import graph.TopicManagerSingleton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Graph extends ArrayList<Node> {

    private Set<Node> uniqueNodes = new HashSet<>();

    public Boolean hasCycle() {
        for (Node n : this) {
            if (n.hasCycles()) {
                return true;
            }
        }
        return false;
    }

    public void CreateFromTopics() {
        this.clear();

        TopicManagerSingleton.TopicManager tm = TopicManagerSingleton.get();

        for (Topic topic : tm.getTopics()) {
            Node topicNode = getOrCreateNode("T" + topic.name);

            // Add edges to subscribers
            for (Agent agent : topic.getSubscribers()) {
                Node agentNode = getOrCreateNode("A" + agent.getName());
                topicNode.addEdge(agentNode);
            }

            // Add edges from publishers
            for (Agent agent : topic.getPublishers()) {
                Node agentNode = getOrCreateNode("A" + agent.getName());
                agentNode.addEdge(topicNode);
            }
        }
    }

    private Node getOrCreateNode(String name) { /* פה שיניתי */
        for (Node node : this) {
            if (node.getName().equals(name)) {
                return node; // Reuse existing node
            }
        }
        Node newNode = new Node(name);
        this.add(newNode);
        return newNode;
    }
}

