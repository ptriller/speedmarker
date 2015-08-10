package de.soapwars.speedmarker;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.soapwars.speedmarker.SpeedMarkerLexer.*;

/**
 * Ok we are getting redundant. A Handcrafted Parser for my automated parser ....
 * Created by ptriller on 09.08.2015.
 */
public class LineParser {

   private Pattern LAST_STUFF = Pattern.compile("(([ \t\u000B\f]*[\n\r])*).*", Pattern.DOTALL);

   private Pattern FIRST_STUFF = Pattern.compile("([ \t\u000B\f]*\r?\n)?(.*)", Pattern.DOTALL);

   private final TokenSource tokenSource;

   private final List<Token> inputTokens;

   private List<Token> outputToken = new ArrayList<>();

   private List<Token> leftoverToken = new ArrayList<>();

   boolean trimFront = false;

   boolean trimBack = false;

   boolean neverTrim = false;

   private List<Token> skipped = new ArrayList<>();

   private enum State {
      DEFAULT,
      DIRECTIVE,
      CLOSETAG;
   }

   ;

   private State state = State.DEFAULT;

   public LineParser(TokenSource tokenSource, List<Token> tokens) {
      inputTokens = tokens;
      this.tokenSource = tokenSource;
   }

   public List<Token> calculateOutputLine() {
      for (Token token : inputTokens) {
         switch (state) {
            case DEFAULT:
               handleDefault(token);
               break;
            case DIRECTIVE:
               handleDirective(token);
               break;
            case CLOSETAG:
               handleCloseTag(token);
         }
      }
      if (outputToken.isEmpty()) {
         return outputToken;
      }
      if (!neverTrim && trimFront) {
         Token frontToken = outputToken.get(0);
         if (isWhitespace(frontToken)) {
            Matcher matcher = LAST_STUFF.matcher(frontToken.getText());
            matcher.matches();
            String group = matcher.group(1);
            outputToken.set(0, tokenSource.getTokenFactory().create(frontToken.getType(), group == null ? "" : group));
         }
      }
      Token backToken = outputToken.get(outputToken.size() - 1);
      if(isWhitespace(backToken)) {
         Matcher matcher = FIRST_STUFF.matcher(backToken.getText());
         boolean match = matcher.matches();
         if(!neverTrim  && trimBack) {
            outputToken.remove(outputToken.size()-1);
         } else {
            String group = matcher.group(1);
            outputToken.set(outputToken.size()-1, tokenSource.getTokenFactory()
                  .create(WHITESPACE, group == null ? "" : group));
         }
         String group2 = matcher.group(2);
         if(group2 != null) {
            leftoverToken.add(tokenSource.getTokenFactory().create(backToken.getType(), group2));
         }
      }
      return outputToken;
   }

   private boolean isWhitespace(Token token) {
      return token.getType() == WHITESPACE || token.getType() == EX_WHITESPACE;
   }

   public List<Token> getLeftoverToken() {
      return leftoverToken;
   }

   private void handleCloseTag(Token token) {
      if (token.getType() != SpeedMarkerLexer.TAG_END &&
            token.getType() != SpeedMarkerLexer.EMPTY_TAG_END) {
         outputToken.addAll(skipped);
         skipped.clear();
         state = State.DEFAULT;
      } else {
         Token type = skipped.get(1);
         skipped.clear();
         switch (type.getType()) {
            case TAG_T:
               trimFront = true;
               trimBack = true;
               break;
            case TAG_LT:
               trimFront = true;
               break;
            case TAG_RT:
               trimBack = true;
               break;
            case TAG_NT:
               neverTrim = true;
               break;
            default:
               break;
         }
         state = State.DEFAULT;
      }
   }

   private void handleDirective(Token token) {
      switch (token.getType()) {
         case TAG_T:
         case TAG_LT:
         case TAG_RT:
         case TAG_NT:
            skipped.add(token);
            state = State.CLOSETAG;
            break;
         default:
            outputToken.addAll(skipped);
            skipped.clear();
            outputToken.add(token);
            state = State.DEFAULT;
            break;
      }
   }

   private void handleDefault(Token token) {
      if (token.getType() == SpeedMarkerLexer.DIRECTIVE_START) {
         skipped.add(token);
         state = State.DIRECTIVE;
      } else {
         outputToken.add(token);
      }
   }
}

