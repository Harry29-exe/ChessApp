import {UI} from "/chess/chessDrawing.js";
import {ChessService} from "/chess/chessService.js";

let chessService, ui;

setupChessBoard();

function setupChessBoard() {
    ui = new UI();
    document.getElementById("chessBoard").addEventListener("click", chessBoardOnClick, false);

    chessService = new ChessService();

    ui.drawChessBoard(chessService.board);
}

function chessBoardOnClick(event) {
    let x = Math.floor((event.clientX - ui.chessBoardCanvas.offsetLeft - ui.fieldSize * .5) / ui.fieldSize);
    let y = Math.floor((event.clientY - ui.chessBoardCanvas.offsetTop - ui.fieldSize * .5) / ui.fieldSize);
    if (x < 0 || x > 7 || y < 0 || y > 7) {
        return;
    }

    let filesToColor = chessService.determinePlayerIntention(x, y);
    ui.drawChessBoard(chessService.board);
    if (filesToColor !== null) {
        for (let i = 0; i < filesToColor.length; i++) {
            ui.fillField(filesToColor[i][0], filesToColor[i][1], filesToColor[i][2]);
        }
    }

    chessService.board.isItWhitesTurn = !chessService.board.isItWhitesTurn;

    let moveRequest = new XMLHttpRequest();
    let jsonBoard = JSON.stringify(chessService.board);
    console.log(jsonBoard);
    moveRequest.open("GET", "http://localhost:8080/api/chess/get-move");
    //moveRequest.setRequestHeader("Content-Length", ""+jsonBoard.length);
    moveRequest.overrideMimeType('application/json');
    moveRequest.send(jsonBoard);
}
