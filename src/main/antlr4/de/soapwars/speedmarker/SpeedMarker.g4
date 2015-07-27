parser grammar SpeedMarker;

options {
    tokenVocab = SpeedMarkerLexer;
}

start:
    seq=sequence
    EOF;

sequence:
    ( comment
    | inlineexpression
    | directive
    | content)*;


comment:
    DIRECTIVE_START TAG_COMMENT (COMMENT_CONTENT)+ END_COMMENT;

inlineexpression:
    EXPRESSION_START  expression CURLY_CLOSE;

directive:
      assignDirective
    | attemptDirective
    | breakDirective
    | switchDirective
    | ifDirective
    | escapeDirective
    | noescapeDirective
    | returnDirective
    | functionDirective
    | flushDirective
    | ftlDirective
    | globalDirective
    | importDirective
    | includeDirective
    | listDirective;


assignDirective:
    (DIRECTIVE_START TAG_ASSIGN var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_ASSIGN TAG_END) #complexAssignDirective
  | (DIRECTIVE_START TAG_ASSIGN
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END)) #simpleAssignDirective;


attemptDirective:
    DIRECTIVE_START TAG_ATTEMPT TAG_END
    attemptBlock=sequence
    DIRECTIVE_START TAG_RECOVER TAG_END
    recoverBlock=sequence
    DIRECTIVE_END TAG_ATTEMPT TAG_END;

breakDirective:
    DIRECTIVE_START TAG_BREAK (TAG_END | EMPTY_TAG_END);

switchDirective:
    DIRECTIVE_START TAG_SWITCH expression TAG_END
    WHITESPACE*
    caseDirective*
    (DIRECTIVE_START TAG_DEFAULT TAG_END
        sequence)?
    DIRECTIVE_END TAG_SWITCH TAG_END;

ifDirective:
    DIRECTIVE_START TAG_IF ifex=expression TAG_END
     ifseq=sequence
     elseIfDirective*
   (DIRECTIVE_START TAG_ELSE TAG_END
    elseseq=sequence)?
   DIRECTIVE_END TAG_IF TAG_END;

elseIfDirective:
    DIRECTIVE_START TAG_ELSEIF expression TAG_END sequence;


caseDirective:
    DIRECTIVE_START TAG_CASE expression TAG_END
        sequence;

escapeDirective:
    DIRECTIVE_START TAG_ESCAPE variableName KEY_AS expression TAG_END
    sequence
    DIRECTIVE_END TAG_ESCAPE TAG_END;


noescapeDirective:
    DIRECTIVE_START TAG_NOESCAPE TAG_END
    sequence
    DIRECTIVE_END TAG_NOESCAPE TAG_END;

returnDirective:
    DIRECTIVE_START TAG_RETURN expression? (TAG_END | EMPTY_TAG_END);

functionDirective:
    DIRECTIVE_START TAG_FUNCTION functionName=variableName
        simpleParam*
        defaultParam*
        (ellipse=variableName ELIPSE_DOTS)? TAG_END
    sequence
    DIRECTIVE_END TAG_FUNCTION TAG_END;

simpleParam:
    variableName;

defaultParam:
    variableName EQUALS expression;

flushDirective:
    DIRECTIVE_START TAG_FLUSH (TAG_END |  EMPTY_TAG_END);

ftlDirective:
    DIRECTIVE_START TAG_FTL defaultParam* (TAG_END |  EMPTY_TAG_END);


globalDirective:
    (DIRECTIVE_START TAG_GLOBAL var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_GLOBAL TAG_END) #complexGlobalDirective
  | (DIRECTIVE_START TAG_GLOBAL
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END)) #simpleGlobalDirective;

importDirective:
    DIRECTIVE_START TAG_IMPORT STRINGLITERAL KEY_AS variableName (TAG_END |  EMPTY_TAG_END);

includeDirective:
    DIRECTIVE_START TAG_INCLUDE STRINGLITERAL defaultParam* (TAG_END |  EMPTY_TAG_END);

localDirective:
    (DIRECTIVE_START TAG_LOCAL var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_LOCAL TAG_END) #complexLocalDirective
  | (DIRECTIVE_START TAG_LOCAL
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END)) #simpleLocalDirective;

listDirective:
	(DIRECTIVE_START TAG_LIST expression KEY_AS variableName TAG_END
		sequence
	(DIRECTIVE_START TAG_ELSE TAG_END
		sequence)?
	DIRECTIVE_END TAG_LIST TAG_END) #listSimpleDirective
	| (DIRECTIVE_START TAG_LIST expression TAG_END
	sequence
	DIRECTIVE_START TAG_ITEMS KEY_AS variableName TAG_END
	sequence
	DIRECTIVE_END TAG_ITEMS TAG_END
	sequence
	(DIRECTIVE_START TAG_ELSE TAG_END
		sequence)?
	DIRECTIVE_END TAG_LIST TAG_END) #listComplexDirective;


variableName:
    IDENTIFIER;

// CONTENT
content:
    (WHITESPACE | CONTENT);

// EXPRESSION

hashAccess:
	( SQUARE_OPEN  ex2=expression  SQUARE_CLOSE);

expression:
      string
    | expression ( hashAccess
                | ( DOT IDENTIFIER)
                | (QUESTIONMARK IDENTIFIER)
                | ( BRACET_OPEN
                  (expression (COMMA expression)* )? BRACET_CLOSE)
                | (expression ( EXCLAMATION (expression)? ))
                | DOUBLE_QUEST )+
    | PLUS expression
    | MINUS expression
    | EXCLAMATION expression
    | expression (ASTERISK expression)+
    | expression (DIVIDE expression)+
    | expression (MODULO expression)+
    | expression (PLUS expression)+
    | expression (MINUS expression)+
    | expression RANGE_DOTS (WHITESPACE*(LT|EXCLAMATION|ASTERISK))? expression
    | expression (LT expression)+
    | expression (GT expression)+
    | expression (LTE expression)+
    | expression (GTE expression)+
    | expression (CMP_EQUALS expression)+
    | expression (EQUALS expression)+
    | expression (CMP_NOTEQUALS expression)+
    | expression (LOG_AND expression)+
    | expression (LOG_OR expression)+
    | BRACET_OPEN expression BRACET_CLOSE
    ;

string:
      STRINGLITERAL #defaultstring
    | RAWSTRINGLITERAL #rawstring;

