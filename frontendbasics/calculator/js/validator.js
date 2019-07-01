"use strict";

class Validator {

    static validPoint(expression) {
        let numbers = expression.split(/[^\d.]+/g);
        let number = numbers[numbers.length - 1];

        return number.indexOf(".") === -1;
    }

    static validBracket(expression) {
        let stack = [];
        let res = true;

        expression.split("").forEach((symbol) => {
            switch (symbol) {
                case "(":
                    stack.push(symbol);
                    break;
                case ")":
                    if (stack.length === 0) {
                        res = false;
                    } else {
                        stack.pop();
                    }
                 break;
                default:
                    break;    
            }
        });
        if (stack.length !== 0) {
            res = false;
        }
        return res;
    }
    
}