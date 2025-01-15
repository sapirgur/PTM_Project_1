package graph;

public class PlusAgent implements Agent {
    private final String[] subs;
    private final String[] pubs;
    private double x = 0, y = 0; // Default values for internal state

    public PlusAgent(String[] subs, String[] pubs) {
        this.subs = subs;
        this.pubs = pubs;
    }

    @Override
    public String getName() {
        return "PlusAgent";
    }

    @Override
    public void callback(String topic, Message msg) {
        if (subs.length < 2 || pubs.length < 1) return;

        if (topic.equals(subs[0])) {
            x = msg.asDouble; // Update x if the topic matches
        } else if (topic.equals(subs[1])) {
            y = msg.asDouble; // Update y if the topic matches
        }

        // Check if both x and y are valid numbers before calculating
        if (!Double.isNaN(x) && !Double.isNaN(y)) {
            double result = x + y;
            Message resultMessage = new Message(result);
            System.out.println("Publishing to " + pubs[0] + ": " + resultMessage.asText);
            // Simulate publishing (e.g., send to a TopicManager)
        }
    }

    @Override
    public void close() {
        System.out.println("Closing PlusAgent");
    }

    @Override
    public void reset() {
        x = 0;
        y = 0;
    }
}
