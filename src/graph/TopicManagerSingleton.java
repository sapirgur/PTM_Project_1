package graph;
import java.util.Collection;

//We use concurrent map so many agents actions won't override one another, its Thread-safe
import java.util.concurrent.ConcurrentHashMap;

//Outer class
public class TopicManagerSingleton {

    //Private CTOR - ensure only one place to crate an instance
    private TopicManagerSingleton() {
    }

    //Inner class
    public static class TopicManager {

        //Inner class fields
        private static final TopicManager instance = new TopicManager();
        private ConcurrentHashMap<String, Topic> topicsMap = new ConcurrentHashMap<>();

        //Inner class private CTOR - prevent external instantiation
        private TopicManager() {}

        public Topic getTopic(String topicName) {
            return topicsMap.computeIfAbsent(topicName, key -> new Topic(key));
        }


        public Collection<Topic> getTopics() {
            return topicsMap.values();
        }

        public void clear(){
            topicsMap.clear();
        }
    }

    public static TopicManager get() {
        return TopicManager.instance;
    }
}