package graph;

public class IncAgent implements Agent {
    private final String[] subs;
    private final String[] pubs;

    public IncAgent(String[] subs, String[] pubs) {
        this.subs = subs;
        this.pubs = pubs;
    }

    @Override
    public String getName() {
        return "IncAgent";
    }
    @Override
    public void create() {
        System.out.println(getName() + " created.");
    }


    @Override
    public void callback(String topic, Message msg) {
        if (subs.length < 1 || pubs.length < 1) return;

        if (topic.equals(subs[0])) {
            double value = msg.asDouble;

            // Check if value is valid
            if (!Double.isNaN(value)) {
                double result = value + 1;
                Message resultMessage = new Message(result);
                System.out.println("Publishing to " + pubs[0] + ": " + resultMessage.asText);
                // Simulate publishing (e.g., send to a TopicManager)
            } else {
                System.err.println("Invalid value received: " + msg.asText);
            }
        }
    }

    @Override
    public void close() {
        System.out.println("Closing IncAgent");
    }

    @Override
    public void reset() {
        // No state to reset for IncAgent
    }
}
