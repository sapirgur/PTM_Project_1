package test;

import java.util.function.BinaryOperator;

import graph.Agent;
import graph.Message;
import graph.Topic;
import graph.TopicManagerSingleton;
import graph.TopicManagerSingleton.TopicManager;

public class BinOpAgent implements Agent {
    private String agentName;
    private Topic inputTopic1, inputTopic2;
    private Topic outputTopic;
    private BinaryOperator<Double> computation;
    private Double input1Value = null;
    private Double input2Value = null;

    public BinOpAgent(String agentName, String inputTopic1Name, String inputTopic2Name, String outputTopicName, BinaryOperator<Double> computation) {
        this.agentName = agentName;
        this.computation = computation;

        TopicManager tm = TopicManagerSingleton.get();
        this.inputTopic1 = tm.getTopic(inputTopic1Name);
        this.inputTopic2 =tm.getTopic(inputTopic2Name);
        this.outputTopic = tm.getTopic(outputTopicName);
        inputTopic1.subscribe(this);
        inputTopic2.subscribe(this);

        outputTopic.addPublisher(this);


    }

    @Override
    public String getName() {return agentName;}

    @Override
    public void callback(String topic, Message msg) {
        if (topic.equals(inputTopic1.name)) {
            input1Value = msg.asDouble;
        } else if (topic.equals(inputTopic2.name)) {
            input2Value = msg.asDouble;
        }

        if (input1Value != null && input2Value != null) {
            double result = computation.apply(input1Value, input2Value);
            Message outputMsg = new Message(result);
            outputTopic.publish(outputMsg);

            input2Value = null;
            input1Value = null;

        }
    }

    @Override
    public void close(){
        inputTopic1.unsubscribe(this);
        inputTopic2.unsubscribe(this);
        outputTopic.removePublisher(this);
    }

    @Override
    public void reset() {
        input1Value = null;
        input2Value = null;
    }


}
