import {UI} from "/chess/chessDrawing.js";
import {ChessService} from "/chess/chessService.js";

let chessService, ui;

setupChessBoard();

function setupChessBoard() {
    ui = new UI();
    document.getElementById("chessBoard").addEventListener("click", chessBoardOnClick, false);

    chessService = new ChessService();

    ui.drawChessBoard(chessService.board);

    function logTurn() {
        console.log(chessService.board.isItWhitesTurn);
        setTimeout(function () {logTurn();}, 1_000);
    }
    //logTurn();
}

function chessBoardOnClick(event) {
    let x = Math.floor((event.clientX - ui.chessBoardCanvas.offsetLeft - ui.fieldSize * .5) / ui.fieldSize);
    let y = Math.floor((event.clientY - ui.chessBoardCanvas.offsetTop - ui.fieldSize * .5) / ui.fieldSize);
    if (x < 0 || x > 7 || y < 0 || y > 7) {
        return;
    }

    let filesToColor = chessService.handlePayerAction(x, y);
    ui.drawChessBoard(chessService.board);
    if (filesToColor !== null) {
        for (let i = 0; i < filesToColor.length; i++) {
            ui.fillField(filesToColor[i][0], filesToColor[i][1], filesToColor[i][2]);
        }
    }
}

export function redraw() {
    ui.drawChessBoard(chessService.board);
}
