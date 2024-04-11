package sae.solver;

import sae.graph.GraphSoluce;

public interface Solver {
    void resolve();
    GraphSoluce getSoluce();
    int getSteps();
}