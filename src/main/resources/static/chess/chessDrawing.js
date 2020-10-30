import {Board, chessPieces} from "/chess/chessBoard.js";

export const white = "rgb(227,227,227)", black = "rgb(28,28,28)";

export class UI {
    constructor(bottomPlayerColor = white) {
        this.fieldSize = Math.min(window.innerWidth*(3/4),window.innerHeight*(3/4) ) / 9;

        this.chessBoardContainer = document.getElementById("chessBoardContainer");
        this.chessBoardContainer.innerHTML = "<canvas id='chessBoard' height='"+this.fieldSize*9+"' width='"+this.fieldSize*9+
            "' style='margin-left:"+
            (window.innerWidth - this.fieldSize*9)/2+"px; "+
            "margin-top: "+
            (window.innerHeight - this.fieldSize*9)/2+"px;" +
            "'></canvas>";
        this.chessBoardCanvas = document.getElementById("chessBoard");

        this.chessBoardCTX = this.chessBoardCanvas.getContext("2d");


    }

    drawChessBoard(board) {
        for (let row = 0; row < 8; row++) {
            for (let col = 0; col < 8; col++) {
                this.drawField(row, col);
                this.drawPiece(row, col, board);
            }
        }

        this.chessBoardCTX.fillStyle = "rgb(98,44,4)";
        this.chessBoardCTX.fillRect(0, 0, this.fieldSize * 9, this.fieldSize * 0.5);
        this.chessBoardCTX.fillRect(0, 0, this.fieldSize * 0.5, this.fieldSize * 9);
        this.chessBoardCTX.fillRect(this.fieldSize * 8.5, 0, this.fieldSize * 9, this.fieldSize * 9);
        this.chessBoardCTX.fillRect(0, this.fieldSize * 8.5, this.fieldSize * 9, this.fieldSize * 9);

        this.chessBoardCTX.strokeStyle = "rgb(110,110,110)";
        this.chessBoardCTX.lineWidth = this.fieldSize * 0.05;
        for (let i = 0; i < 9; i++) {
            this.drawHorizontalLine(i);
            this.drawVerticalLine(i);
        }
    }

    drawHorizontalLine(xLineIndex) {
        this.chessBoardCTX.beginPath();
        this.chessBoardCTX.moveTo(0, this.fieldSize * (0.5 + xLineIndex));
        this.chessBoardCTX.lineTo(this.fieldSize * 9, this.fieldSize * (0.5 + xLineIndex));
        this.chessBoardCTX.stroke();
    }

    drawVerticalLine(yLineIndex) {
        this.chessBoardCTX.beginPath();
        this.chessBoardCTX.moveTo(this.fieldSize * (0.5 + yLineIndex), 0);
        this.chessBoardCTX.lineTo(this.fieldSize * (0.5 + yLineIndex), this.fieldSize * 9);
        this.chessBoardCTX.stroke();
    }

    drawField(xIndex, yIndex) {
        this.fillField(xIndex, yIndex, (xIndex + yIndex) % 2 === 0 ? white : black);
    }

    fillField(xIndex, yIndex, color) {
        let xStart = this.fieldSize * (0.5 + xIndex);
        let yStart = this.fieldSize * (0.5 + yIndex);
        this.chessBoardCTX.fillStyle = color;
        this.chessBoardCTX.fillRect(xStart, yStart, this.fieldSize, this.fieldSize);
    }

    drawPiece(xIndex, yIndex, board) {
        let xStart = this.fieldSize * (0.5 + xIndex);
        let yStart = this.fieldSize * (0.5 + yIndex) + this.fieldSize * 0.15;
        if (board.getPieceAt(xIndex, yIndex) != null) {
            this.chessBoardCTX.font = this.fieldSize + "px Pecita";//"px FreeSerif";
            this.chessBoardCTX.textBaseline = "top";

            let fieldColor = (xIndex + yIndex) % 2 === 0 ? white : black;
            let piece = board.getPieceAt(xIndex, yIndex).valueOf();
            if (piece > chessPieces.LAST_WHITE_PIECE_INDEX) {
                if (fieldColor === black) {
                    this.chessBoardCTX.fillStyle = black;
                    this.chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
                    this.chessBoardCTX.fillStyle = "rgb(200,200,200)";
                    this.chessBoardCTX.fillText(String.fromCharCode(piece - 6), xStart, yStart);

                } else {
                    this.chessBoardCTX.fillStyle = black;
                    this.chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
                }
            } else {
                this.chessBoardCTX.fillStyle = white;
                this.chessBoardCTX.fillText(String.fromCharCode(piece + 6), xStart, yStart);
                this.chessBoardCTX.fillStyle = black;
                this.chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
            }
        }
    }
}
