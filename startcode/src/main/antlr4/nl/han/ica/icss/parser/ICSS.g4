grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';




//--- PARSER: ---
stylesheet: (stylerule | variable_assignment)* EOF;

// Variables
// TODO: mag in variable assignment alleen literals staan of ook expressions?
variable_assignment: variable_reference ASSIGNMENT_OPERATOR literal SEMICOLON;
variable_reference: CAPITAL_IDENT;

// Stylerules
stylerule: selector OPEN_BRACE (declaration|if_clause)* CLOSE_BRACE;
selector: LOWER_IDENT | ID_IDENT | CLASS_IDENT;
declaration: LOWER_IDENT COLON expression SEMICOLON;

// Expressions
expression: expression MUL expression | expression (PLUS|MIN) expression | literal | variable_reference;
literal: COLOR | PIXELSIZE | PERCENTAGE | bool | SCALAR;
bool: TRUE | FALSE;

// If clauses
if_clause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE OPEN_BRACE (declaration|if_clause)* CLOSE_BRACE else_clause?;
else_clause: ELSE OPEN_BRACE (declaration|if_clause)* CLOSE_BRACE;
