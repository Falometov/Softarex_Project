"use strict";

function parseExpression(expression) {
    let operation = [];
    let data = [];
    let value;
    let lastChar = expression.substring(expression.length - 1);
    let numberCounter = 0;
    let bracketCounter = 0;

    if (!Validator.validBracket(expression)) {
        $(".input_field").val("Error");
    } else {
        if (lastChar !== ")" && (lastChar.charCodeAt(0) < ASCII_CODE_A || lastChar.charCodeAt(0) > ASCII_CODE_Z)) {
            expression = expression.substring(0, expression.length - 1);
        }
        while (expression.length !== 0) {
            if (expression.charAt(0).charCodeAt(0) >= ASCII_CODE_A && expression.charAt(0).charCodeAt(0) <= ASCII_CODE_Z) {
                expression = expression.replace(/[0-9.]+/, (str) => {
                    numberCounter++;
                    data.push(str);
                    return "";
                });
            } else if (expression.charAt(0) === "(" || expression.charAt(0) === ")") {
                expression = expression.replace(/[()]/, (str) => {
                    if (str === ")") {
                        while ((value = operation.pop()) !== "(") {
                            data.push(value);
                        }
                    } else {
                        operation.push(str);
                    }
                    return "";
                });
            } else {
                expression = expression.replace(/[+-/x]+/, (str) => {
                    bracketCounter++;
                    if (operation.length === 0) {
                        operation.push(str);
                    } else {
                        value = operation.pop();
                        if ((value === "+" || value === "-") && (str === "/" || str === "x")) {
                            operation.push(value);
                        } else {
                            if (value === "(") {
                                operation.push(value);
                            } else {
                                data.push(value);
                            }
                        }
                        operation.push(str);
                    }
                    return "";
                });
            }
        }
        while (operation.length !== 0) {
            data.push(operation.pop());
        }
        if (--numberCounter !== bracketCounter) {
            $(".input_field").val("Error");
            data = [];            
        }
    }
    return data;
}