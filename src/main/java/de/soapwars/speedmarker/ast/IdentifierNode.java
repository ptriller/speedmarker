package de.soapwars.speedmarker.ast;

/**
 * Created by ptriller on 30.06.2015.
 */
public class IdentifierNode implements Node {

    private String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }
}
