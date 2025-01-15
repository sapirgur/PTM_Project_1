package configs;
import graph.ParallelAgent;

import configs.BinOpAgent;
import java.util.ArrayList;
import java.util.List;

public class MathExampleConfig implements Config {
    private final List<ParallelAgent> agents = new ArrayList<>(); // Store all agents

    @Override
    public void create() {
        // Create and wrap BinOpAgents
        agents.add(new ParallelAgent(new BinOpAgent("plus", "A", "B", "R1", (x, y) -> x + y)));
        agents.add(new ParallelAgent(new BinOpAgent("minus", "A", "B", "R2", (x, y) -> x - y)));
        agents.add(new ParallelAgent(new BinOpAgent("mul", "R1", "R2", "R3", (x, y) -> x * y)));

        // Initialize agents (optional if they require setup in create())
        for (ParallelAgent agent : agents) {
            agent.reset(); // Assuming `reset()` prepares agents (use `create()` if needed)
        }
    }

    @Override
    public String getName() {
        return "Math Example";
    }

    @Override
    public int getVersion() {
        return 1;
    }

    @Override
    public void close() {
        // Close all agents
        for (ParallelAgent agent : agents) {
            agent.close();
        }
        agents.clear(); // Clear the list after closing
    }
    
}
