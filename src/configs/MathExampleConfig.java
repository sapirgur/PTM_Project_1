package configs;
import graph.ParallelAgent;
import graph.Agent;
import graph.BinOpAgent;

import java.util.ArrayList;
import java.util.List;

public class MathExampleConfig implements Config {
    private final List<ParallelAgent> agents = new ArrayList<>(); // Store all agents

    @Override
    public void create() {
        new BinOpAgent("plus", "A", "B", "R1", (x,y)->x+y);
        new BinOpAgent("minus", "A", "B", "R2", (x,y)->x-y);
        new BinOpAgent("mul", "R1", "R2", "R3", (x,y)->x*y);
    }

    @Override
    public String getName() {
        return "Math Example";
    }

    @Override
    public int getVersion() {
        return 1;
    }
    
}
