package de.soapwars.speedmarker;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by ptriller on 08.08.2015.
 */
public class WhitespaceFIlteringTokenSource implements TokenSource {

   private TokenSource delegate;

   private Deque<Token> acceptedToken = new ArrayDeque<>();

   private List<Token> pendingToken = new ArrayList<>();

   public WhitespaceFIlteringTokenSource(TokenSource delegate) {
      this.delegate = delegate;
   }

   @Override
   public Token nextToken() {
      if (acceptedToken.isEmpty() && !refillQueue()) {
         return delegate.getTokenFactory().create(Token.EOF,"");
      }
      return acceptedToken.removeLast();
   }

   @Override
   public int getLine() {
      return delegate.getLine();
   }

   @Override
   public int getCharPositionInLine() {
      return delegate.getCharPositionInLine();
   }

   @Override
   public CharStream getInputStream() {
      return delegate.getInputStream();
   }

   @Override
   public String getSourceName() {
      return delegate.getSourceName();
   }

   @Override
   public void setTokenFactory(TokenFactory<?> factory) {
      delegate.setTokenFactory(factory);
   }

   @Override
   public TokenFactory<?> getTokenFactory() {
      return delegate.getTokenFactory();
   }

   private boolean refillQueue() {
      Token token = delegate.nextToken();
      while (token.getType() != Token.EOF) {
         pendingToken.add(token);
         if (isWhitespace(token) && token.getText().indexOf('\n') > -1) {
            break;
         }
         token = delegate.nextToken();
      }
      LineParser parser = new LineParser(this, pendingToken);

      for (Token t : parser.calculateOutputLine()) {
         if(t.getType() != SpeedMarkerLexer.EX_WHITESPACE)
         acceptedToken.addFirst(t);
      }
      pendingToken.clear();
      pendingToken.addAll(parser.getLeftoverToken());
      return !(pendingToken.isEmpty() && acceptedToken.isEmpty());
   }

   private boolean isWhitespace(Token token) {
      return token.getType() == SpeedMarkerLexer.WHITESPACE || token.getType() == SpeedMarkerLexer.EX_WHITESPACE;
   }


}
