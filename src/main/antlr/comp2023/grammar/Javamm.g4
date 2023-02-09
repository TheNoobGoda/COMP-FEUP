grammar Javamm;

@header{ package comp2023.grammar; }

statement: expression ';' ;


expression
    : expression '+' expression
    | expression '-' expression
    | expression '*' expression
    | expression '/' expression
    | INTEGER
    ;

INTEGER: [0-9]+;

WS  : [ \t\r\n]+ -> skip ;

