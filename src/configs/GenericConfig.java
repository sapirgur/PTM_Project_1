package configs;

import graph.Agent;
import graph.ParallelAgent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericConfig implements Config {

    private final List<ParallelAgent> agents = new ArrayList<>();
    private String configFile;

    @Override
    public void create() {
        if (configFile == null || configFile.isEmpty()) {
            throw new IllegalStateException("Configuration file is not set.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            List<String> lines = new ArrayList<>();
            String line;

            // Read all lines from the file
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }

            // Ensure the file has valid structure (multiple of 3 lines per agent)
            if (lines.size() % 3 != 0) {
                throw new IllegalArgumentException("Invalid configuration file format.");
            }

            // Process each agent (3 lines at a time)
            for (int i = 0; i < lines.size(); i += 3) {
                String className = lines.get(i);
                String[] subs = lines.get(i + 1).split(",");
                String[] pubs = lines.get(i + 2).split(",");

                // Dynamically load the agent class and create an instance
                Class<?> agentClass = Class.forName(className);
                Agent agent = (Agent) agentClass.getConstructor(String[].class, String[].class)
                        .newInstance((Object) subs, (Object) pubs);

                // Wrap the agent with ParallelAgent
                ParallelAgent parallelAgent = new ParallelAgent(agent);
                agents.add(parallelAgent);

                // Create the agent (e.g., initialize it)
                parallelAgent.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create agents from configuration file.", e);
        }
    }


}
