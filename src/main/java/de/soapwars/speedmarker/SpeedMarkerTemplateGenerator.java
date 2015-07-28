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
      return new AssignNode(ctx.variableName().getText(), ctx.expression().accept(this), AssignNode.Scope.DEFAULT);
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
      return ctx.expression().accept(this);
   }


   @Override
   public ConditionalNode visitCaseDirective(SpeedMarker.CaseDirectiveContext ctx) {
      return new ConditionalNode(ctx.expression().accept(this), visitSequence(ctx.sequence()));
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
      return new SwitchNode(ctx.expression().accept(this), cases);
   }

   @Override
   public ValueNode visitContent(SpeedMarker.ContentContext ctx) {
      return new ValueNode(ctx.getText());
   }

   @Override
   public IfNode visitIfDirective(SpeedMarker.IfDirectiveContext ctx) {
      List<ConditionalNode> nodes = new ArrayList<>();
      nodes.add(new ConditionalNode(ctx.ifex.accept(this), visitSequence(ctx.ifseq)));
      for (SpeedMarker.ElseIfDirectiveContext elCtx : ctx.elseIfDirective()) {
         nodes.add(new ConditionalNode(elCtx.expression().accept(this), visitSequence(elCtx.sequence())));
      }
      if (ctx.elseseq != null) {
         nodes.add(new ConditionalNode(new ValueNode(Boolean.TRUE), visitSequence(ctx.elseseq)));
      }
      return new IfNode(nodes);
   }

   @Override
   public ConditionalNode visitElseIfDirective(SpeedMarker.ElseIfDirectiveContext ctx) {
      return new ConditionalNode(ctx.expression().accept(this), visitSequence(ctx.sequence()));
   }

   @Override
   public EscapeNode visitEscapeDirective(SpeedMarker.EscapeDirectiveContext ctx) {
      return new EscapeNode(ctx.variableName().getText(),
            ctx.expression().accept(this), visitSequence(ctx.sequence()));
   }

   @Override
   public EscapeNode visitNoescapeDirective(SpeedMarker.NoescapeDirectiveContext ctx) {
      return new EscapeNode(null, null, visitSequence(ctx.sequence()));
   }

   @Override
   public ReturnNode visitReturnDirective(SpeedMarker.ReturnDirectiveContext ctx) {
      if (ctx.expression() != null) {
         return new ReturnNode(ctx.expression().accept(this));
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
      return new ParameterNode(ctx.variableName().getText(), ctx.expression().accept(this), false);
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
      return new AssignNode(ctx.variableName().getText(), ctx.expression().accept(this), AssignNode.Scope.GLOBAL);
   }

   @Override
   public Node visitImportDirective(SpeedMarker.ImportDirectiveContext ctx) {
      return new ImportNode(unescape(ctx.STRINGLITERAL().getText()), ctx.variableName().getText());
   }

   @Override
   public Node visitIncludeDirective(SpeedMarker.IncludeDirectiveContext ctx) {
      List<ParameterNode> parameters = new ArrayList<>();
      for (SpeedMarker.DefaultParamContext dCtx : ctx.defaultParam()) {
         parameters.add(visitDefaultParam(dCtx));
      }
      return new IncludeNode(ctx.STRINGLITERAL().getText(), parameters);
   }

   @Override
   public Node visitListSimpleDirective(SpeedMarker.ListSimpleDirectiveContext ctx) {
      Node emptyNode = null;
      if (ctx.empty != null) {
         emptyNode = visitSequence(ctx.empty);
      }
      return new ListNode(
            ctx.expression().accept(this),
            ctx.variableName().getText(),
            null,
            visitSequence(ctx.body),
            null,
            emptyNode);
   }

   @Override
   public Node visitListComplexDirective(SpeedMarker.ListComplexDirectiveContext ctx) {
      Node emptyNode = null;
      if (ctx.empty != null) {
         emptyNode = visitSequence(ctx.empty);
      }
      return new ListNode(
            ctx.expression().accept(this),
            ctx.variableName().getText(),
            visitSequence(ctx.prefix),
            visitSequence(ctx.body),
            visitSequence(ctx.postfix),
            emptyNode);
   }

   @Override
   public AssignNode visitComplexLocalDirective(SpeedMarker.ComplexLocalDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), visitSequence(ctx.sequence()), AssignNode.Scope.LOCAL);
   }

   @Override
   public AssignNode visitSimpleLocalDirective(SpeedMarker.SimpleLocalDirectiveContext ctx) {
      return new AssignNode(ctx.variableName().getText(), ctx.expression().accept(this), AssignNode.Scope.LOCAL);
   }

   @Override
   public Node visitLtDirective(SpeedMarker.LtDirectiveContext ctx) {
      return TrimNode.TRIM_LEFT;
   }

   @Override
   public Node visitNtDirective(SpeedMarker.NtDirectiveContext ctx) {
      return TrimNode.NO_TRIM;
   }

   @Override
   public Node visitRtDirective(SpeedMarker.RtDirectiveContext ctx) {
      return TrimNode.TRIM_RIGHT;
   }

   @Override
   public Node visitStopDirective(SpeedMarker.StopDirectiveContext ctx) {
      if (ctx.STRINGLITERAL() != null) {
         return new StopNode(ctx.STRINGLITERAL().getText());
      } else {
         return new StopNode(null);
      }
   }

   @Override
   public Node visitTDirective(SpeedMarker.TDirectiveContext ctx) {
      return TrimNode.TRIM_BOTH;
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
