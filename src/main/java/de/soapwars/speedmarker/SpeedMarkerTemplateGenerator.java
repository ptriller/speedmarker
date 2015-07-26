package de.soapwars.speedmarker;

import de.soapwars.speedmarker.ast.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ptriller on 20.07.2015.
 */
public class SpeedMarkerTemplateGenerator extends SpeedMarkerBaseVisitor<Node> {

   @Override
   public Node visitStart(SpeedMarker.StartContext ctx) {
      return visitSequence(ctx.sequence());
   }

   @Override
   public CommentNode visitComment(SpeedMarker.CommentContext ctx) {
      StringBuilder builder = new StringBuilder();
      for (TerminalNode child : ctx.COMMENT_CONTENT()) {
         builder.append(child.getText());
      }
      return new CommentNode(builder.toString());
   }

   @Override
   public ValueNode visitDefaultstring(SpeedMarker.DefaultstringContext ctx) {
      return new ValueNode(unescape(ctx.STRINGLITERAL().getText()));
   }

   @Override
   public ValueNode visitRawstring(SpeedMarker.RawstringContext ctx) {
      return new ValueNode(ctx.RAWSTRINGLITERAL().getText());
   }

   @Override
   public AssignNode visitComplexAssignDirective(SpeedMarker.ComplexAssignDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), visitSequence(ctx.sequence()), AssignNode.Scope.DEFAULT);
   }

   @Override
   public AssignNode visitSimpleAssignDirective(SpeedMarker.SimpleAssignDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), visitExpression(ctx.expression()), AssignNode.Scope.DEFAULT);
   }

   @Override
   public AttemptNode visitAttemptDirective(SpeedMarker.AttemptDirectiveContext ctx) {
      return new AttemptNode(visitSequence(ctx.attemptBlock), visitSequence(ctx.recoverBlock));
   }

   @Override
   public BreakNode visitBreakDirective(SpeedMarker.BreakDirectiveContext ctx) {
      return BreakNode.BREAK_NODE;
   }

   @Override
   public SequenceNode visitSequence(SpeedMarker.SequenceContext ctx) {
      List<Node> result = new ArrayList<>();
      int n = ctx.getChildCount();
      for (int i = 0; i < n; i++) {
         ParseTree c = ctx.getChild(i);
         Node childNode = c.accept(this);
         result.add(childNode);
      }
      return new SequenceNode(result);
   }

   @Override
   public Node visitDirective(SpeedMarker.DirectiveContext ctx) {
      return ctx.getChild(0).accept(this);
   }

   @Override
   public Node visitInlineexpression(SpeedMarker.InlineexpressionContext ctx) {
      return visitExpression(ctx.expression());
   }


   @Override
   public ConditionalNode visitCaseDirective(SpeedMarker.CaseDirectiveContext ctx) {
      return new ConditionalNode(visitExpression(ctx.expression()), visitSequence(ctx.sequence()));
   }

   @Override
   public SwitchNode visitSwitchDirective(SpeedMarker.SwitchDirectiveContext ctx) {
      List<ConditionalNode> cases = new ArrayList<>();
      for (SpeedMarker.CaseDirectiveContext caseCtx : ctx.caseDirective()) {
         cases.add(visitCaseDirective(caseCtx));
      }
      if (ctx.sequence() != null) {
         cases.add(new ConditionalNode(new ValueNode(Boolean.TRUE), visitSequence(ctx.sequence())));
      }
      return new SwitchNode(visitExpression(ctx.expression()), cases);
   }

   @Override
   public ValueNode visitContent(SpeedMarker.ContentContext ctx) {
      return new ValueNode(ctx.getText());
   }

   @Override
   public IfNode visitIfDirective(SpeedMarker.IfDirectiveContext ctx) {
      List<ConditionalNode> nodes = new ArrayList<>();
      nodes.add(new ConditionalNode(visitExpression(ctx.ifex), visitSequence(ctx.ifseq)));
      for (SpeedMarker.ElseIfDirectiveContext elCtx : ctx.elseIfDirective()) {
         nodes.add(new ConditionalNode(visitExpression(elCtx.expression()), visitSequence(elCtx.sequence())));
      }
      if (ctx.elseseq != null) {
         nodes.add(new ConditionalNode(new ValueNode(Boolean.TRUE), visitSequence(ctx.elseseq)));
      }
      return new IfNode(nodes);
   }

   @Override
   public ConditionalNode visitElseIfDirective(SpeedMarker.ElseIfDirectiveContext ctx) {
      return new ConditionalNode(visitExpression(ctx.expression()), visitSequence(ctx.sequence()));
   }

   @Override
   public EscapeNode visitEscapeDirective(SpeedMarker.EscapeDirectiveContext ctx) {
      return new EscapeNode(ctx.variableName().getText(),
            visitExpression(ctx.expression()), visitSequence(ctx.sequence()));
   }

   @Override
   public EscapeNode visitNoescapeDirective(SpeedMarker.NoescapeDirectiveContext ctx) {
      return new EscapeNode(null, null, visitSequence(ctx.sequence()));
   }

   @Override
   public ReturnNode visitReturnDirective(SpeedMarker.ReturnDirectiveContext ctx) {
      if (ctx.expression() != null) {
         return new ReturnNode(visitExpression(ctx.expression()));
      } else {
         return new ReturnNode(null);
      }
   }

   @Override
   public Node visitFunctionDirective(SpeedMarker.FunctionDirectiveContext ctx) {
      List<ParameterNode> parameters = new ArrayList<>();
      for (SpeedMarker.SimpleParamContext sCtx : ctx.simpleParam()) {
         parameters.add(visitSimpleParam(sCtx));
      }
      for (SpeedMarker.DefaultParamContext dCtx : ctx.defaultParam()) {
         parameters.add(visitDefaultParam(dCtx));
      }
      if (ctx.ellipse != null) {
         parameters.add(new ParameterNode(ctx.ellipse.getText(), null, true));
      }
      return new FunctionNode(ctx.functionName.getText(), parameters, visitSequence(ctx.sequence()));
   }

   @Override
   public ParameterNode visitSimpleParam(SpeedMarker.SimpleParamContext ctx) {
      return new ParameterNode(ctx.variableName().getText(), null, false);
   }

   @Override
   public ParameterNode visitDefaultParam(SpeedMarker.DefaultParamContext ctx) {
      return new ParameterNode(ctx.variableName().getText(), visitExpression(ctx.expression()), false);
   }

   @Override
   public Node visitFlushDirective(SpeedMarker.FlushDirectiveContext ctx) {
      return FlushNode.FLUSH_NODE;
   }

   @Override
   public Node visitFtlDirective(SpeedMarker.FtlDirectiveContext ctx) {
      return super.visitFtlDirective(ctx);
   }

   @Override
   public AssignNode visitComplexGlobalDirective(SpeedMarker.ComplexGlobalDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), visitSequence(ctx.sequence()), AssignNode.Scope.GLOBAL);
   }

   @Override
   public AssignNode visitSimpleGlobalDirective(SpeedMarker.SimpleGlobalDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), visitExpression(ctx.expression()), AssignNode.Scope.GLOBAL);
   }

   @Override
   public Node visitImportDirective(SpeedMarker.ImportDirectiveContext ctx) {
      return new ImportNode(unescape(ctx.STRINGLITERAL().getText()), ctx.variableName().getText());
   }

   private String unescape(String value) {
      StringBuilder builder = new StringBuilder();
      StringLexer lexer = new StringLexer(new ANTLRInputStream(value));
      for (Token token = lexer.nextToken(); token.getType() != Token.EOF; token = lexer.nextToken()) {
         switch (token.getType()) {
            case StringLexer.ESC_SQUOTES:
               builder.append('\'');
               break;
            case StringLexer.ESC_DQUOTES:
               builder.append('"');
               break;
            case StringLexer.ESC_CURLOPEN:
               builder.append('{');
               break;
            case StringLexer.ESC_BACKSLASH:
               builder.append('\\');
               break;
            case StringLexer.ESC_NEWLINE:
               builder.append('\n');
               break;
            case StringLexer.ESC_CRET:
               builder.append('\r');
               break;
            case StringLexer.ESC_TAB:
               builder.append('\t');
               break;
            case StringLexer.ESC_BACKSPACE:
               builder.append('\b');
               break;
            case StringLexer.ESC_FORMFEED:
               builder.append('\f');
               break;
            case StringLexer.ESC_LT:
               builder.append('<');
               break;
            case StringLexer.ESC_GT:
               builder.append('>');
               break;
            case StringLexer.ESC_AMP:
               builder.append('&');
               break;
            case StringLexer.ESC_UNICODE:
               char chr = (char) Integer.parseInt(token.getText().substring(2), 16);
               builder.append(chr);
               break;
            default:
               builder.append(token.getText());
               break;
         }
      }
      return builder.toString();
   }
}
