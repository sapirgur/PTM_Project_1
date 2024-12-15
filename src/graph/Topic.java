package graph;
import java.util.ArrayList;
import java.util.List;

public class Topic {
    public final String name;
    private List<Agent> subscribers;
    private List<Agent> publishers;

    //CTOR
    Topic(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
        this.publishers = new ArrayList<>();
    }

    //Subscribe new agent if not already exist
    public void subscribe(Agent agent) {
        if(!subscribers.contains(agent)) {
            subscribers.add(agent);
        }
    }

    // Activate callBack method on publishers
    public void publish(Message msg) {
        for (Agent agent : subscribers) {
            agent.callback(name, msg);
        }
    }

    //Remove agent from the subscribers
    public void unsubscribe(Agent agent) {
        subscribers.remove(agent);
    }

    //Remove agent from publishers
    public void removePublisher(Agent agent) {
        publishers.remove(agent);
    }

    //Add agent to the publishers
    public void addPublisher(Agent agent) {
        if(!publishers.contains(agent)) {
            publishers.add(agent);
        }
    }


}
