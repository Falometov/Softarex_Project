"use strict";

class Calculator {

    static calculate() {
        let input = $(".input_field");
        let expression = parseExpression(input.val());
        let data = [];
        let result;
        let value;
        let number1;
        let number2;

        if (expression.length !== 0) {
            while (expression.length !== 0) {
                value = expression.shift();
                switch (value) {
                    case "+":
                        number2 = data.pop();
                        if (number2.toString().indexOf(".") === -1) {
                            number2 = parseInt(number2);
                        } else {
                            number2 = parseFloat(number2);
                        }
                        number1 = data.pop();
                        if (number1.toString().indexOf(".") === -1) {
                            number1 = parseInt(number1);
                        } else {
                            number1 = parseFloat(number1);
                        }
                        data.push(MathOperation.sum(number1, number2));
                        break;
                    case "-":
                        number2 = data.pop();
                        number1 = data.pop();
                        data.push(MathOperation.difference(number1, number2));
                        break;
                    case "x":
                        number2 = data.pop();
                        number1 = data.pop();
                        data.push(MathOperation.multiple(number1, number2));
                        break;
                    case "/":
                        number2 = data.pop();
                        number1 = data.pop();
                        data.push(MathOperation.division(number1, number2));
                        break;
                    default:
                        data.push(value);
                        break;
                }
            }
            result = data.pop();
            result !== Infinity ? input.val(result) : input.val("Division by zero is impossible");
        }
    }

    static clear() {
        let input = $(".input_field");
        let expression = input.val();

        if (expression === "Error") {
            input.val("");
        } else {
            input.val(expression.substring(0, expression.length - 1));
        }
    }

    static clearAll() {
        $(".input_field").val("");
    }

}