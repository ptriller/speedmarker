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
    | listDirective
    | ltDirective
    | rtDirective
    | ntDirective
    | stopDirective
    | tDirective;


assignDirective:
    DIRECTIVE_START TAG_ASSIGN var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_ASSIGN TAG_END #complexAssignDirective
  | DIRECTIVE_START TAG_ASSIGN
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END) #simpleAssignDirective;


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
    DIRECTIVE_START TAG_GLOBAL var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_GLOBAL TAG_END #complexGlobalDirective
  | DIRECTIVE_START TAG_GLOBAL
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END) #simpleGlobalDirective;

importDirective:
    DIRECTIVE_START TAG_IMPORT STRINGLITERAL KEY_AS variableName (TAG_END |  EMPTY_TAG_END);

includeDirective:
    DIRECTIVE_START TAG_INCLUDE STRINGLITERAL defaultParam* (TAG_END |  EMPTY_TAG_END);

localDirective:
    DIRECTIVE_START TAG_LOCAL var=variableName TAG_END
             sequence
             DIRECTIVE_END TAG_LOCAL TAG_END #complexLocalDirective
  | DIRECTIVE_START TAG_LOCAL
                 variableName EQUALS
                 expression (TAG_END | EMPTY_TAG_END) #simpleLocalDirective;

listDirective:
	DIRECTIVE_START TAG_LIST expression KEY_AS variableName TAG_END
		body=sequence
	(DIRECTIVE_START TAG_ELSE TAG_END
		empty=sequence)?
	DIRECTIVE_END TAG_LIST TAG_END #listSimpleDirective
	| DIRECTIVE_START TAG_LIST expression TAG_END
	prefix=sequence
	DIRECTIVE_START TAG_ITEMS KEY_AS variableName TAG_END
	body=sequence
	DIRECTIVE_END TAG_ITEMS TAG_END
	postfix=sequence
	(DIRECTIVE_START TAG_ELSE TAG_END
		empty=sequence)?
	DIRECTIVE_END TAG_LIST TAG_END #listComplexDirective;


ltDirective:
    DIRECTIVE_START TAG_LT (TAG_END | EMPTY_TAG_END);

ntDirective:
    DIRECTIVE_START TAG_NT (TAG_END | EMPTY_TAG_END);

rtDirective:
    DIRECTIVE_START TAG_RT (TAG_END | EMPTY_TAG_END);

sepDirectove:
    DIRECTIVE_START TAG_SEP TAG_END
        sequence
    DIRECTIVE_END TAG_SEP TAG_END

stopDirective:
    DIRECTIVE_START TAG_STOP STRINGLITERAL (TAG_END | EMPTY_TAG_END);

tDirective:
    DIRECTIVE_START TAG_T (TAG_END | EMPTY_TAG_END);

variableName:
    IDENTIFIER;

// CONTENT
content:
    (WHITESPACE | CONTENT);

// EXPRESSION

hashAccess:
	SQUARE_OPEN  expression  SQUARE_CLOSE;

propertyAccess:
    DOT variableName;

builtinCall:
    QUESTIONMARK IDENTIFIER;

methodParameter:
    BRACET_OPEN (expression (COMMA expression)* )? BRACET_CLOSE;

defaultValue:
    EXCLAMATION expression?;

hasContent:
    DOUBLE_QUEST;

expression:
      literalExpression #literals
    | expression (
          hashAccess
        | propertyAccess
        | builtinCall
        | methodParameter
        | defaultValue
        | hasContent
        )+ #highestPrecedence
    | (PLUS | MINUS | EXCLAMATION)+ expression #unaryOperators
    | expression ((ASTERISK|DIVIDE|MODULO) expression)+ #multiplicativeOperators
    | expression ((PLUS|MINUS) expression)+ #additiveOperators
    | expression RANGE_DOTS (LT|EXCLAMATION|ASTERISK)? expression #rangeOperators
    | expression ((LT|GT|LTE|GTE) expression)+ #comperativeOperators
    | expression ((CMP_EQUALS|EQUALS|CMP_NOTEQUALS) expression)+ #equalOperators
    | expression (LOG_AND expression)+ #logicalAnd
    | expression (LOG_OR expression)+ #logicalOr
    | BRACET_OPEN expression BRACET_CLOSE #bracetExpression
    ;

literalExpression:
      string;


string:
      STRINGLITERAL #defaultstring
    | RAWSTRINGLITERAL #rawstring;

