lexer grammar StringLexer;

fragment HexDigit: [A-Za-z0-9];

ESC_SQUOTES: '\\\'';
ESC_DQUOTES: '\\"';
ESC_CURLOPEN: '\\{';
ESC_BACKSLASH: '\\\\';
ESC_NEWLINE: '\\n';
ESC_CRET: '\\r';
ESC_TAB: '\\t';
ESC_BACKSPACE: '\\b';
ESC_FORMFEED: '\\f';
ESC_LT: '\\l';
ESC_GT: '\\g';
ESC_AMP: '\\a';
ESC_UNICODE: '\\x' HexDigit HexDigit (HexDigit (HexDigit)?)?;
CHARACTER: ~'\\'+ | '\\';

