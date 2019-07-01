"use strict";

const ASCII_CODE_A = 48;
const ASCII_CODE_Z = 57;

let res = false;

$(document).ready(() => {
    $("body").keydown(function (e) {
        keyController(e.which);
    });
    $(".calc_button").click(function () {
        buttonController($(this).text());
    });
});

function buttonController(buttonValue) {
    if (buttonValue.charCodeAt(0) >= ASCII_CODE_A && buttonValue.charCodeAt(0) <= ASCII_CODE_Z) {
        print(buttonValue);
    } else if (buttonValue === "(" || buttonValue === ")") {
        printBracket(buttonValue);
    } else {
        switch (buttonValue) {
            case "=":
                Calculator.calculate();
                res = true;
                break;
            case ".":
                printPoint(buttonValue);
                break;
            case "CE":
                Calculator.clear();
                break;
            case "C":
                Calculator.clearAll();
                break;
            default:
                printSign(buttonValue);
                break;
        }
    }
}

function keyController(keyValue) {
    if (keyValue >= ASCII_CODE_A && keyValue <= ASCII_CODE_Z) {
        print(String.fromCharCode(keyValue));
    } else {
        switch (keyValue) {
            case 111:
                printSign("/");
                break;
            case 106:
                printSign("x");
                break;
            case 109:
                printSign("-");
                break;
            case 107:
                printSign("+");
                break;
            case 79:
                printBracket("(");
                break;
            case 80:
                printBracket(")");
                break;
            case 191:
                printPoint(".");
                break;
            case 13:
                Calculator.calculate();
                res = true;
                break;
            case 8:
                Calculator.clear();
                break;
        }
    }
}

function print(buttonValue) {
    let input = $(".input_field");
    let expression = input.val();
    let lastChar = expression.substring(expression.length - 1);

    if (lastChar !== ")") {
        input.val(input.val() + buttonValue);
    }
}

function printSign(buttonValue) {
    let input = $(".input_field");
    let expression = input.val();
    let lastChar = expression.substring(expression.length - 1);

    if (expression.length !== 0 && lastChar !== "(") {
        if (buttonValue === lastChar) {
            input.val(expression);
        } else if (!(lastChar.charCodeAt(0) >= ASCII_CODE_A && lastChar.charCodeAt(0) <= ASCII_CODE_Z)
                 && lastChar !== ")" && lastChar !== ".") {
            input.val(expression.substring(0, expression.length - 1) + buttonValue);
        } else {
            input.val(input.val() + buttonValue);
        }
    }
}

function printPoint(buttonValue) {
    let input = $(".input_field");
    let expression = input.val();
    let lastChar = expression.substring(expression.length - 1);

    if (expression.length !== 0 && (lastChar.charCodeAt(0) >= ASCII_CODE_A && lastChar.charCodeAt(0) <= ASCII_CODE_Z) 
            && Validator.validPoint(expression)) {
        input.val(expression + buttonValue);
    }
}

function printBracket(buttonValue) {
    let input = $(".input_field");
    let expression = input.val();
    let lastChar = expression.substring(expression.length - 1);

    if (buttonValue === ")") {
        if (expression.length !== 0 || (lastChar.charCodeAt(0) >= ASCII_CODE_A && lastChar.charCodeAt(0) <= ASCII_CODE_Z) 
                || lastChar === ")") {
            input.val(input.val() + buttonValue);
        }
    } else {
        if (expression.length === 0 || (!(lastChar.charCodeAt(0) >= ASCII_CODE_A && lastChar.charCodeAt(0) <= ASCII_CODE_Z)
            && lastChar !== ")" && lastChar !== ".")) {
            input.val(input.val() + buttonValue);
        }
    }
}
