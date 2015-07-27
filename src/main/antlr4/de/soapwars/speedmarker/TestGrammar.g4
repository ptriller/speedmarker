grammar TestGrammar;


NUMBER: [0-9]+;

PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
BO: '[';
BC: ']';
WS: [ \t\n\r]+ -> skip;

start:
	calc
	EOF;

calc:
	 number
	| calc ( (MUL calc)
		|(DIV calc))+
	| calc ( ( PLUS calc)
		|(MINUS calc))+
	| BO calc BC;

number:
	NUMBER;