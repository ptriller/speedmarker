lexer grammar SpeedMarkerLexer;

// CONTENT LEVEL TOKENS
fragment Whitespace: [ \t\r\n\u000C]+;

fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0xFF
	|	// covers all characters above 0xFF which are not a surrogate
		~[\u0000-\u00FF\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0xFF
	|	// covers all characters above 0xFF which are not a surrogate
		~[\u0000-\u00FF\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

WHITESPACE: (Whitespace)+;

EXPRESSION_START: '${' -> pushMode(EXPRESSION);

DIRECTIVE_START: '<#' -> pushMode(DIRECTIVE);

DIRECTIVE_END: '</#' -> pushMode(DIRECTIVE);

CONTENT: ~('\u0020' | '\u00A0' | '\u1680' | '\u180E' | '\u2000' |
          '\u2001' | '\u2002' | '\u2003' | '\u2004' | '\u2005' |
          '\u2006' | '\u2007' | '\u2008' | '\u2009' | '\u200A' |
          '\u200B' | '\u202F' | '\u205F' | '\u3000' | '\uFEFF' |
          '\u000a' | '\u000b' | '\u000c' | '\u000d' | '\u0085' |
          '\u2028' | '\u2029' | '$'      | '<' )+ | .;

ILLEGAL_INPUT: .;

mode EXPRESSION;
EX_WHITESPACE: Whitespace+ -> type(WHITESPACE),skip;

CURLY_CLOSE: '}' -> popMode;
CURLY_OPEN: '{' -> pushMode(EXPRESSION);
TAG_END: '>' -> popMode;
EMPTY_TAG_END: '/>' -> popMode;

// String Literals
RAW_STRING_START: 'r"' -> skip,pushMode(RAW_STRING);
DOUBLE_STRING_START: '"' -> skip,pushMode(DOUBLE_STRING);
SINGLE_STRING_START: '\'' -> skip,pushMode(SINGLE_STRING);

//Number Literals
NUMBERLITERAL: ([0-9])+('.'([0-9])+)?;

//Boolean Literals
BOOLEAN_TRUE: 'true';
BOOLEAN_FALSE: 'false';

CMP_EQUALS: '==';
CMP_NOTEQUALS: '!=';
LT_ENTITY: '&lt;' -> type(LT);
LTE_ENTITY: '&lt;=' -> type(LTE);
ESC_LT: '\\lt' -> type(LT);
ESC_LTE: '\\lte ' -> type(LTE);
STR_LT: 'lt' -> type(LT);
STR_LTE: 'lte ' -> type(LTE);
GT: 'gt';
GTE: 'gte';
ESC_GT: '\\gt' -> type(GT);
ESC_GTE: '\\gte' -> type(GTE);
GTE_ENTITY: '&gt;=' -> type(GTE);
GT_ENTITY: '&gt;' -> type(GT);
DOUBLE_QUEST: '??';
LOG_AND: '&&';
LOG_OR: '||';

ELIPSE_DOTS: '...';
RANGE_DOTS: '..';
SQUARE_OPEN: '[';
SQUARE_CLOSE: ']';
COMMA: ',';
LT: '<';
LTE: '<=';
EXCLAMATION: '!';
SEMICOLON: ':';
DOT: '.';
ASTERISK: '*';
PLUS: '+';
MINUS: '-';
DIVIDE: '/';
MODULO: '%';
EQUALS: '=';
BRACET_OPEN: '(';
BRACET_CLOSE: ')';
QUESTIONMARK: '?';
KEY_TO: 'to';
KEY_AS: 'as';
IDENTIFIER: JavaLetter JavaLetterOrDigit*;

EX_ILLEGAL_IMPUT: . -> type(ILLEGAL_INPUT);
mode DOUBLE_STRING;
STRINGLITERAL:    ('\\'. | ~["])+;
D_STR_END: '"' ->  skip,popMode;
DS_ILLEGAL_IMPUT: . -> type(ILLEGAL_INPUT);

mode SINGLE_STRING;
S_STR_CONTENT: ('\\'. | ~['])+ -> type(STRINGLITERAL);
S_STR_END: '\'' -> skip, popMode;
SS_ILLEGAL_IMPUT: . -> type(ILLEGAL_INPUT);

mode RAW_STRING;
RAWSTRINGLITERAL: ~["]+;
R_STR_END: '"' -> skip, popMode;
RS_ILLEGAL_IMPUT: . -> type(ILLEGAL_INPUT);

mode DIRECTIVE;
TAG_NOPARSE: 'noparse>' -> mode(NOPARSE); //DONE
TAG_COMMENT: '--' -> mode(COMMENT); //DONE

TAG_ASSIGN: 'assign' -> mode(EXPRESSION); // DONE
TAG_ATTEMPT: 'attempt' -> mode(EXPRESSION); //DONE
TAG_BREAK: 'break' -> mode(EXPRESSION); //DONE
TAG_CASE: 'case' -> mode(EXPRESSION); //DONE
TAG_COMPRESS: 'compress' -> mode(EXPRESSION); //DONE
TAG_DEFAULT: 'default' -> mode(EXPRESSION); //DONE
TAG_ELSE: 'else' -> mode(EXPRESSION); //DONE
TAG_ELSEIF: 'elseif' -> mode(EXPRESSION); //DONE
TAG_ESCAPE: 'escape' -> mode(EXPRESSION); //DONE
TAG_FALLBACK: 'fallback' -> mode(EXPRESSION);
TAG_FUNCTION: 'function' -> mode(EXPRESSION); // DONE
TAG_FLUSH: 'flush' -> mode(EXPRESSION); //DONE
TAG_FTL: 'ftl' -> mode(EXPRESSION); // DONE
TAG_GLOBAL: 'global' -> mode(EXPRESSION); //DONE
TAG_IF: 'if' -> mode(EXPRESSION); //DONE
TAG_IMPORT: 'import' -> mode(EXPRESSION); //DONE
TAG_INCLUDE: 'include' -> mode(EXPRESSION);
TAG_ITEMS: 'items' -> mode(EXPRESSION);
TAG_LIST: 'list'-> mode(EXPRESSION);
TAG_LOCAL: 'local' -> mode(EXPRESSION);
TAG_LT: 'lt' -> mode(EXPRESSION);
TAG_MACRO: 'macro' -> mode(EXPRESSION);
TAG_NESTED: 'nested' -> mode(EXPRESSION);
TAG_NOESCAPE: 'noescape' -> mode(EXPRESSION); //DONE
TAG_NT: 'nt' -> mode(EXPRESSION);
TAG_RECOVER: 'recover' -> mode(EXPRESSION); //DONE
TAG_RECURSE: 'recurse' -> mode(EXPRESSION);
TAG_RETURN: 'return' -> mode(EXPRESSION); //DONE
TAG_SEP: 'sep' -> mode(EXPRESSION);
TAG_SETTING: 'settimg' -> mode(EXPRESSION);
TAG_STOP: 'stop' -> mode(EXPRESSION);
TAG_SWITCH: 'switch' -> mode(EXPRESSION); //DONE
TAG_RT: 'rt' -> mode(EXPRESSION);
TAG_T: 't' -> mode(EXPRESSION);
TAG_VISIT: 'visit' -> mode(EXPRESSION);
TAG_IDENTIFIER: JavaLetter JavaLetterOrDigit* -> type(IDENTIFIER);

mode NOPARSE;
NOPARSE_END: '</#noparse>' -> popMode;
NOPARSE_CONTENT: ~('<')+ | .;

mode COMMENT;
END_COMMENT: '-->' -> popMode;
COMMENT_CONTENT: ~('-')+ | .;