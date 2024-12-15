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
        private ConcurrentHashMap<String, Topic> TopicsMap = new ConcurrentHashMap<>();

        //Inner class private CTOR - prevent external instantiation
        private TopicManager() {}

        public Topic getTopic(String topicName) {
            for (Topic topic : TopicsMap.values()) {
                if(topic.name.equals(topicName)) {
                    return topic;
                }
            }
            TopicsMap.put(topicName, new Topic(topicName));
            return TopicsMap.get(topicName);
        }

        public Collection<Topic> getTopics() {
            return TopicsMap.values();
        }

        public void clear(){
            for(Topic topic : TopicsMap.values()) {
                TopicsMap.remove(topic.name);
            }

            TopicsMap.clear();
        }
    }

    public static TopicManager get() {
        return TopicManager.instance;
    }
}