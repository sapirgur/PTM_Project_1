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


}
