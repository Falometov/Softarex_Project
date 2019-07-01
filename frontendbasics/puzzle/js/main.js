"use strict";

let elements = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];
let comparator = () => Math.random() - 0.5;
let buttonTag = '<div class="puzzle_button button" id="$0">$1</div>';
let emptyButtonTag = '<div class="empty_button button" id="$0"></div>';
let tableTag = '<table id="table_puzzle"></table>';
let rowTag = '<tr id="tr$0"></tr>';
let numberOfMoves = 0;
let newGameFlag = false;
let stopwatchId;
let time = 0;

$(document).ready(() => {
    printPuzzle();
    $("button#new_game_button").click(() => {
        newGame();
    });
    $("button#restore_button").click(() => {
        restoreGame();
    });
});

function printPuzzle() {
    let selector;

    while (!validCombination(elements.sort(comparator)));
    $("#puzzle").append(tableTag);
    for (let index = 0; index < elements.length; index++) {
        if (!(index % 4)) {
            selector = index;
           $("#table_puzzle").append(format(rowTag, [selector]));        
        }
        $(format("#tr$0", [selector])).append(format(buttonTag, [index, elements[index]]));
    }
    $(format("#tr$0", [selector])).append(format(emptyButtonTag, [elements.length]));
    for (let index = 0; index < 16; index++) {
        $("div#" + index).click(() => {
            if (!isCompleted()) {
                move(index);
            } else {
                alert("You win!\nNumber of moves: " + numberOfMoves + "\nCount of seconds: " + time);
            }
        });
    }
}

function move(idButton) {
    let idEmptyButton = findEmptyButton();
    let res = idEmptyButton - idButton;
    let flag;

    if (newGameFlag) {
        switch (res) {
            case 4:
                changeButtonById(idButton, idEmptyButton);
                break;
            case -4:
                changeButtonById(idButton, idEmptyButton);
                break;
            case 1:
                flag = idButton + 1;
                if (flag % 4 !== 0) {
                    changeButtonById(idButton, idEmptyButton);
                }
                break;
            case -1:
                if (idButton % 4 !== 0) {
                    changeButtonById(idButton, idEmptyButton);
                }
                break; 
            default:
                break;                       
        }
    }
}

function isCompleted() {
    let result = true;
    let checkNumber;

    for (let index = 0; index < 15; index++) {
        checkNumber = index + 1;
        if (checkNumber !== parseInt($("div#" + index).text())) {
            result = false;
            break;
        }
    }
    return result;
}

function changeButtonById(idButton, idEmptyButton) {
    let button = $("div#" + idButton);
    let emptyButton = $("div#" + idEmptyButton);
    let numberLabel = $("div#number_of_moves");

    numberOfMoves++;
    numberLabel.text(numberLabel.text().replace(/[0-9]+/g, numberOfMoves));
    emptyButton.text(button.text());
    button.text('');
    emptyButton.css({
        "text-align": "center",
        "color": "white",
        "background-color": "brown",
        "border": "5px solid rgb(255, 166, 0)"
    });
    button.css({
        "background-color": "grey"
    });
}

function findEmptyButton() {
    for (let index = 0; index < 16; index++) {
        if ($("div#" + index).text() === '') {
            return index;
        }
    }
}

function validCombination(elements) {
    let sum = 0;

    elements.concat(0);
    for (let index = 1; index < elements.length - 1; index++) {
        for (let j = index - 1; j >= 0; j--) {
            if (elements[j] > elements[index]) {
                sum++;
            }        
        }            
    }
    return !(sum % 2);
}

function format(string, args) {
    for (let index = 0; index < args.length; index++) {
        string = string.replace(new RegExp("\\$" + index, "gi"), args[index]);
    }
    return string;
}

function newGame() {
    if (!newGameFlag) {
        newGameFlag = true;
        stopwatch();
    } else {
        alert("Game is started!");
    }
}

function restoreGame() {
    time = 0;
    numberOfMoves = 0;
    $("div#number_of_moves").text("Number of moves: " + numberOfMoves);
    $("#table_puzzle").remove();
    printPuzzle();
    if (newGameFlag) {
        stopwatch();
    }
}

function stopwatch() {
    $("div#stopwatch").text("Stopwatch: 0");
    clearInterval(stopwatchId);
    stopwatchId = setInterval(() => $("div#stopwatch").text($("div#stopwatch").text().replace(/[0-9]+/g, ++time)), 1000);
}