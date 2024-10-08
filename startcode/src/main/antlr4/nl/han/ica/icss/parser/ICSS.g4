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
stylesheet: (stylerule | variableAssignment)* EOF;

// Variables
// TODO: mag in variable assignment alleen literals staan of ook expressions?
variableAssignment: variableReference ASSIGNMENT_OPERATOR literal SEMICOLON;
variableReference: CAPITAL_IDENT;

// Stylerules
stylerule: selector OPEN_BRACE (declaration|ifClause)* CLOSE_BRACE;

// Declaration
declaration: propertyName COLON expression SEMICOLON;
propertyName: LOWER_IDENT;

// Selectors
selector: LOWER_IDENT # tagSelector
    | ID_IDENT # idSelector
    | CLASS_IDENT # classSelector
;

// Expressions
// TODO: mag ik op deze manier bepalen wat voor expression iets is?
expression: expression MUL expression # multiplyOperation
    | expression PLUS expression # addOperation
    | expression MIN expression # subtractOperation
    | literal # literalExpression
    | variableReference # variableExpression
;
// TODO: mag ik op deze manier bepalen wat voor literal iets is?
literal: COLOR # colorLiteral
    | PIXELSIZE # pixelLiteral
    | PERCENTAGE # percentageLiteral
    | TRUE # boolLiteral
    | FALSE # boolLiteral
    | SCALAR # scalarLiteral
;

// If clauses
ifClause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE OPEN_BRACE (declaration|ifClause)* CLOSE_BRACE elseClause?;
elseClause: ELSE OPEN_BRACE (declaration|ifClause)* CLOSE_BRACE;
