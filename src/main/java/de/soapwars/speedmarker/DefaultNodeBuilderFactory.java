package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.NodeBuilderFactory;
import de.soapwars.speedmarker.ast.ParseState;
import de.soapwars.speedmarker.ast.builder.*;

/**
 * Created by ptriller on 26.02.2017.
 */
public class DefaultNodeBuilderFactory implements NodeBuilderFactory {
  @Override
  public AssignNodeBuilder assignNode(ParseState state) {
    return new AssignNodeBuilder();
  }

  @Override
  public AssignNodeBuilder globalNode(ParseState state) {
    return new AssignNodeBuilder();
  }

  @Override
  public NoparseNodeBuilder noparseNode(ParseState state) {
    return new NoparseNodeBuilder();
  }

  @Override
  public AttemptNodeBuilder attemptNode(ParseState state) {
    return new AttemptNodeBuilder();
  }

  @Override
  public CommentNodeBuilder commentNode(ParseState state) {
    return new CommentNodeBuilder();
  }

  @Override
  public CompressNodeBuilder compressNode(ParseState state) {
    return new CompressNodeBuilder();
  }

  @Override
  public ContentNodeBuilder contentNode(ParseState state) {
    return new ContentNodeBuilder();
  }

  @Override
  public EscapeNodeBuilder escapeNode(ParseState state) {
    return new EscapeNodeBuilder();
  }

  @Override
  public FunctionNodeBuilder functionNode(ParseState state) {
    return new FunctionNodeBuilder();
  }

  @Override
  public IfNodeBuilder ifNode(ParseState state) {
    return new IfNodeBuilder();
  }

  @Override
  public ImportNodeBuilder importNode(ParseState state) {
    return new ImportNodeBuilder();
  }

  @Override
  public IncludeNodeBuilder includeNode(ParseState state) {
    return new IncludeNodeBuilder();
  }

  @Override
  public InterpolationNodeBuilder interpolationNode(ParseState state) {
    return new InterpolationNodeBuilder();
  }

  @Override
  public ListNodeBuilder listNode(ParseState state) {
    return new ListNodeBuilder();
  }

  @Override
  public SequenceNodeBuilder sequenceNode(ParseState state) {
    return new SequenceNodeBuilder();
  }
}
