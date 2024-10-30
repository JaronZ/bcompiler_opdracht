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
COMMA: ',';




//--- PARSER: ---
stylesheet: (stylerule | variableAssignment)* EOF;

// Variables
variableAssignment: variableReference ASSIGNMENT_OPERATOR expression SEMICOLON;
variableReference: CAPITAL_IDENT;

// Stylerules
stylerule: selector (COMMA selector)* OPEN_BRACE (declaration|ifClause|variableAssignment)* CLOSE_BRACE;

// Declaration
declaration: propertyName COLON expression SEMICOLON;
propertyName: LOWER_IDENT;

// Selectors
selector: LOWER_IDENT # tagSelector
    | ID_IDENT # idSelector
    | CLASS_IDENT # classSelector
;

// Expressions
expression: expression MUL expression # multiplyOperation
    // Merged to make sure neither plus nor minus has precedence over the other
    | expression operator = (PLUS|MIN) expression # additiveOperation
    | literal # literalExpression
    | variableReference # variableExpression
;
literal: COLOR # colorLiteral
    | PIXELSIZE # pixelLiteral
    | PERCENTAGE # percentageLiteral
    | TRUE # boolLiteral
    | FALSE # boolLiteral
    | SCALAR # scalarLiteral
;

// If clauses
ifClause: IF BOX_BRACKET_OPEN expression BOX_BRACKET_CLOSE
    OPEN_BRACE (declaration|ifClause|variableAssignment)* CLOSE_BRACE elseClause?;
elseClause: ELSE OPEN_BRACE (declaration|ifClause|variableAssignment)* CLOSE_BRACE;
