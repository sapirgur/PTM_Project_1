package configs;

import graph.Agent;
import graph.Topic;
import graph.TopicManagerSingleton;

import java.util.ArrayList;

public class Graph extends ArrayList<Node> {
    public Boolean hasCycle(){
        for(Node n : this){
            if(n.hasCycles()){return true;}
        }
        return false;
    }

    public void CreateFromTopics(TopicManagerSingleton.TopicManager tm){
        for(Topic t : tm.getTopics()){
            Node topicNode = new Node("T: " + t.name);
            for(Agent agent: t.getSubscribers()){
                Node agentNode = new Node("A: " + agent.getName());
                topicNode.addEdge(agentNode);
                agentNode.addEdge(topicNode);
            }
            this.add(topicNode);
        }
    }
}
