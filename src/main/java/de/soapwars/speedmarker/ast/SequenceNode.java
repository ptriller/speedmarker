package de.soapwars.speedmarker.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 29.06.2015.
 */
public class SequenceNode implements Node {

    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node) {
        nodes.add(node);
    }
}
