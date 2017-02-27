package de.soapwars.speedmarker.ast;

import de.soapwars.speedmarker.ast.builder.*;

/**
 * Created by ptriller on 26.02.2017.
 */
public interface NodeBuilderFactory {

  AssignNodeBuilder assignNode(ParseState state);

  AssignNodeBuilder globalNode(ParseState state);

  AttemptNodeBuilder attemptNode(ParseState state);

  CommentNodeBuilder commentNode(ParseState state);

  CompressNodeBuilder compressNode(ParseState state);

  ContentNodeBuilder contentNode(ParseState state);

  EscapeNodeBuilder escapeNode(ParseState state);

  FunctionNodeBuilder functionNode(ParseState state);

  IfNodeBuilder ifNode(ParseState state);

  ImportNodeBuilder importNode(ParseState state);

  IncludeNodeBuilder includeNode(ParseState state);

  InterpolationNodeBuilder interpolationNode(ParseState state);

  ListNodeBuilder listNode(ParseState state);

  SequenceNodeBuilder sequenceNode(ParseState state);

  NoparseNodeBuilder noparseNode(ParseState state);

}
