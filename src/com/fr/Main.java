package com.fr;

public class Main {
    public static void main(String[] args) {
        Eval eval = new Eval();
        System.out.println(eval.eval(new Parser(new Lexical("test.scm").run()).parse()));
    }
}