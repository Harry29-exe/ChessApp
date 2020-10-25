let chessBoardContainer,chessBoardCanvas,chessBoardCTX;
const white = "rgb(227,227,227)", black = "rgb(28,28,28)";
let chessService, ui;

const chessPieces = {
    WHITE_KING: 9812,
    WHITE_QUEEN: 9813,
    WHITE_ROOK: 9814,
    WHITE_BISHOP: 9815,
    WHITE_KNIGHT: 9816,
    WHITE_PAWN: 9817,

    BLACK_KING: 9818,
    BLACK_QUEEN: 9819,
    BLACK_ROOK: 9820,
    BLACK_BISHOP: 9821,
    BLACK_KNIGHT: 9822,
    BLACK_PAWN: 9823,

    LAST_WHITE_PIECE_INDEX: 9817,
    BOTTOM_PIECES_COLOR: white
}

class Board {
    constructor() {
        this.chessBoardArray = [
            [chessPieces.BLACK_ROOK, chessPieces.BLACK_KNIGHT, chessPieces.BLACK_BISHOP, chessPieces.BLACK_KING,
                chessPieces.BLACK_QUEEN, chessPieces.BLACK_BISHOP, chessPieces.BLACK_KNIGHT, chessPieces.BLACK_ROOK],
            [chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN,
                chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN],
            [null, null, null, null, null, null, null, null],
            [null, null, null, null, null, null, null, null],
            [null, null, null, null, null, null, null, null],
            [null, null, null, null, null, null, null, null],
            [chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN,
                chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN],
            [chessPieces.WHITE_ROOK, chessPieces.WHITE_KNIGHT, chessPieces.WHITE_BISHOP, chessPieces.WHITE_KING,
                chessPieces.WHITE_QUEEN, chessPieces.WHITE_BISHOP, chessPieces.WHITE_KNIGHT, chessPieces.WHITE_ROOK]
        ];
        this.isCastlingAvailable = {
            WHITE_SHORT: true,
            WHITE_LONG: true,
            BLACK_SHORT: true,
            BLACK_LONG: true
        };

        this.enPassantMove = null;
    }

    setPieceAt( x, y , piece){
        this.chessBoardArray[y][x] = piece;
    }

    getPieceAt( x, y ) {
        return this.chessBoardArray[y][x];
    }

    isPieceWhite(x,y) {
        if(this.getPieceAt(x,y) !== null)
            return this.getPieceAt(x,y).valueOf() <= chessPieces.LAST_WHITE_PIECE_INDEX;
        else
            return null;
    }

}

class ChessService {
    constructor() {
        this.board = new Board();
        this.selectedX = null;
        this.selectedY = null;
        this.isItWhitesTurn = true;
    }

    determinePlayerIntention(clickedX, clickedY) {
        let clickedPiece = this.board.getPieceAt(clickedX, clickedY);
        let moves;

        if (this.selectedX == null && clickedPiece !== null) {
            this.selectedX = clickedX;
            this.selectedY = clickedY;
            return this.getColoredFields(clickedX, clickedY);
        } else if(this.selectedX === clickedX && this.selectedY === clickedY ) {
            this.selectedX = null;
            this.selectedY = null;
            return [];
        } else {
            moves = getAvailableMoves(this.selectedX, this.selectedY);
            console.log("Moves: ");
            console.log(moves);

            if ((this.isItWhitesTurn && this.selectedIsWhite()) || (!this.isItWhitesTurn && !this.selectedIsWhite())) {
                if (isMoveInMoves()) {
                    console.log("move");
                    this.makeMove(this.selectedX, this.selectedY, clickedX, clickedY);
                } else {
                    console.log("not a move");
                    this.selectedX = clickedX;
                    this.selectedY = clickedY;
                    return this.getColoredFields(clickedX, clickedY);
                }
            } else {
                console.log("player is not a piece owner");
                this.selectedX = clickedX;
                this.selectedY = clickedY;
                return this.getColoredFields(clickedX, clickedY);
            }
        }
        console.log("null");
        return [];

        function isMoveInMoves() {
            for( let i = 0; i < moves.length; i++ ){
                if(moves[i][0] === clickedX && moves[i][1] === clickedY)
                    return true;
            }
            return false;
        }



    }

    getColoredFields(clickedX, clickedY) {
        let availableMoves = getAvailableMoves(clickedX, clickedY)
        let fieldsToColor = [[clickedX, clickedY, "rgba(32,167,221,0.6)"]];
        for (let i = 0; i < availableMoves.length; i++) {
            fieldsToColor.push([availableMoves[i][0],availableMoves[i][1],
                this.board.isPieceWhite(availableMoves[i][0],availableMoves[i][1]) === !this.isItWhitesTurn?
                    "rgba(210,62,36,0.6)": "rgba(125,212,26,0.6)"]);
        }
        return fieldsToColor;
    }

    selectedIsWhite() {
            return this.board.isPieceWhite(this.selectedX, this.selectedY);
    }


    makeMove(fromX, fromY, toX, toY) {
        let piece = this.board.getPieceAt(fromX, fromY);
        this.board.setPieceAt(fromX, fromY, null);
        this.board.setPieceAt(toX, toY, piece);
        this.isItWhitesTurn = !this.isItWhitesTurn;
    }
}

class UI {
    constructor(bottomPlayerColor = white) {
        this.fieldSize = Math.min(window.innerWidth*(3/4),window.innerHeight*(3/4) ) / 9;
        this.bottomPlayerColor = bottomPlayerColor;
    }

    drawChessBoard() {
        for (let row = 0; row < 8; row++) {
            for (let col = 0; col < 8; col++) {
                this.drawField(row, col);
                this.drawPiece(row, col);
            }
        }

        chessBoardCTX.fillStyle = "rgb(98,44,4)";
        chessBoardCTX.fillRect(0, 0, this.fieldSize * 9, this.fieldSize * 0.5);
        chessBoardCTX.fillRect(0, 0, this.fieldSize * 0.5, this.fieldSize * 9);
        chessBoardCTX.fillRect(this.fieldSize * 8.5, 0, this.fieldSize * 9, this.fieldSize * 9);
        chessBoardCTX.fillRect(0, this.fieldSize * 8.5, this.fieldSize * 9, this.fieldSize * 9);

        chessBoardCTX.strokeStyle = "rgb(110,110,110)";
        chessBoardCTX.lineWidth = this.fieldSize * 0.05;
        for (let i = 0; i < 9; i++) {
            this.drawHorizontalLine(i);
            this.drawVerticalLine(i);
        }
    }

    drawHorizontalLine(xLineIndex) {
        chessBoardCTX.beginPath();
        chessBoardCTX.moveTo(0, this.fieldSize * (0.5 + xLineIndex));
        chessBoardCTX.lineTo(this.fieldSize * 9, this.fieldSize * (0.5 + xLineIndex));
        chessBoardCTX.stroke();
    }

    drawVerticalLine(yLineIndex) {
        chessBoardCTX.beginPath();
        chessBoardCTX.moveTo(this.fieldSize * (0.5 + yLineIndex), 0);
        chessBoardCTX.lineTo(this.fieldSize * (0.5 + yLineIndex), this.fieldSize * 9);
        chessBoardCTX.stroke();
    }

    drawField(xIndex, yIndex) {
        this.fillField(xIndex, yIndex, (xIndex + yIndex) % 2 === 0 ? white : black);
    }

    fillField(xIndex, yIndex, color) {
        let xStart = this.fieldSize * (0.5 + xIndex);
        let yStart = this.fieldSize * (0.5 + yIndex);
        chessBoardCTX.fillStyle = color;
        chessBoardCTX.fillRect(xStart, yStart, this.fieldSize, this.fieldSize);
    }

    drawPiece(xIndex, yIndex) {
        let xStart = this.fieldSize * (0.5 + xIndex);
        let yStart = this.fieldSize * (0.5 + yIndex) + this.fieldSize * 0.15;
        if (chessService.board.getPieceAt(xIndex, yIndex) != null) {
            chessBoardCTX.font = this.fieldSize + "px Pecita";//"px FreeSerif";
            chessBoardCTX.textBaseline = "top";

            let fieldColor = (xIndex + yIndex) % 2 === 0 ? white : black;
            let piece = chessService.board.getPieceAt(xIndex, yIndex).valueOf();
            if (piece > chessPieces.LAST_WHITE_PIECE_INDEX) {
                if (fieldColor === black) {
                    chessBoardCTX.fillStyle = black;
                    chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
                    chessBoardCTX.fillStyle = "rgb(200,200,200)";
                    chessBoardCTX.fillText(String.fromCharCode(piece - 6), xStart, yStart);

                } else {
                    chessBoardCTX.fillStyle = black;
                    chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
                }
            } else {
                chessBoardCTX.fillStyle = white;
                chessBoardCTX.fillText(String.fromCharCode(piece + 6), xStart, yStart);
                chessBoardCTX.fillStyle = black;
                chessBoardCTX.fillText(String.fromCharCode(piece), xStart, yStart);
            }
        }
    }

    chessBoardOnClick(event) {
        let x = Math.floor((event.clientX - chessBoardCanvas.offsetLeft - ui.fieldSize * .5) / ui.fieldSize);
        let y = Math.floor((event.clientY - chessBoardCanvas.offsetTop - ui.fieldSize * .5) / ui.fieldSize);
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return;
        }

        let filesToColor = chessService.determinePlayerIntention(x, y);
        ui.drawChessBoard();
        if (filesToColor !== null) {
            for (let i = 0; i < filesToColor.length; i++) {
                ui.fillField(filesToColor[i][0], filesToColor[i][1], filesToColor[i][2]);
            }
        }
    }
}




function setupChessBoard() {
    chessService = new ChessService();
    ui = new UI();
    chessBoardContainer = document.getElementById("chessBoardContainer");
    chessBoardContainer.innerHTML = "<canvas id='chessBoard' height='"+ui.fieldSize*9+"' width='"+ui.fieldSize*9+
        "' style='margin-left:"+
        (window.innerWidth - ui.fieldSize*9)/2+"px; "+
        "margin-top: "+
        (window.innerHeight - ui.fieldSize*9)/2+"px;" +
        "'></canvas>";
    chessBoardCanvas = document.getElementById("chessBoard");
    chessBoardCanvas.addEventListener("click", ui.chessBoardOnClick, false);
    chessBoardCTX = chessBoardCanvas.getContext("2d");
    ui.drawChessBoard();
}




function getAvailableMoves(xLocation,yLocation) {
    let moves = [];
    let x,y;

    let isWhite = chessService.board.isPieceWhite(xLocation,yLocation);
    switch( chessService.board.getPieceAt(xLocation,yLocation) ) {
        case chessPieces.BLACK_ROOK:
        case chessPieces.WHITE_ROOK:
            checkRookMoves();
            break;
        case chessPieces.BLACK_KNIGHT:
        case chessPieces.WHITE_KNIGHT:
            checkKnightMoves();
            break;
        case chessPieces.BLACK_BISHOP:
        case chessPieces.WHITE_BISHOP:
            checkBishopMoves();
            break;
        case chessPieces.BLACK_QUEEN:
        case chessPieces.WHITE_QUEEN:
            checkQueenMoves();
            break;
        case chessPieces.BLACK_KING:
        case chessPieces.WHITE_KING:
            checkKingMoves();
            break;
        case chessPieces.BLACK_PAWN:
        case chessPieces.WHITE_PAWN:
            checkPawnMoves();
    }
    return moves;

    function iterate(xChange, yChange, checkFunction ) {
        x = xLocation + xChange;
        y = yLocation + yChange;
        while( (x < 8 && x >= 0) && (y < 8 && y >= 0) ){
            checkFunction(xChange,yChange);
        }
    }

    function check(xChange, yChange) {
        if( chessService.board.getPieceAt(x, y) === null ){
            moves.push([x,y]);
            x += xChange;
            y += yChange;
        } else if( chessService.board.isPieceWhite(x, y) !== isWhite ) {
            moves.push([x,y]);
            x = 9;
        } else {
            x = 9;
        }
    }

    function checkRookMoves() {
        iterate(0,1, check);
        iterate(1,0, check);
        iterate(-1,0, check);
        iterate(0,-1, check);
    }

    function checkKnightMoves() {
        knightCheck(2,-1);
        knightCheck(2,1);
        knightCheck(-2,1);
        knightCheck(-2,-1);
        knightCheck(-1,2);
        knightCheck(-1,-2);
        knightCheck(1,2);
        knightCheck(1,-2);

        function knightCheck(xChange, yChange) {
            x = xLocation + xChange;
            y = yLocation + yChange;
            if( (x < 8 && x >= 0) && (y < 8 && y >= 0) ) {
                if( chessService.board.getPieceAt(x, y) === null ){
                    moves.push([x, y]);
                } else if( chessService.board.isPieceWhite(x, y) !== isWhite ){
                    moves.push([x, y]);
                }
            }
        }
    }

    function checkBishopMoves() {
        iterate(1,1,check);
        iterate(1,-1,check);
        iterate(-1,1,check);
        iterate(-1,-1,check);
    }

    function checkQueenMoves() {
        checkBishopMoves();
        checkRookMoves();
    }

    function checkKingMoves() {
        x = xLocation;
        y = yLocation;
        for( let xChange = -1; xChange < 2; xChange++ ){
            for( let yChange = -1; yChange < 2; yChange++){
                if( (xChange !== 0 || yChange !== 0) && (x+xChange < 8 && x+xChange >= 0) && (y+yChange < 8 && y+yChange >= 0)) {
                    if( chessService.board.getPieceAt(x+xChange, y+yChange) === null ){
                        moves.push([x+xChange, y+yChange]);
                    } else if( chessService.board.isPieceWhite(x+xChange, y+yChange) !== isWhite ){
                        moves.push([x+xChange, y+yChange]);
                    }
                }
            }
        }
        if( isWhite ){
            if( chessService.board.isCastlingAvailable.WHITE_LONG ){
                if( chessService.board.getPieceAt(4,7) === null &&
                    chessService.board.getPieceAt(5,7) === null &&
                    chessService.board.getPieceAt(6,7) === null ){
                    moves.push
                }
            }
        }
    }

    function checkPawnMoves() {
        if(isWhite) {
            whitePawnCheck();
        } else {
            blackPawnCheck();
        }

        function whitePawnCheck(){
            if(yLocation === 6){
                if( chessService.board.getPieceAt(xLocation, yLocation-1) === null ){
                    if( chessService.board.getPieceAt(xLocation, yLocation-2) === null ){
                        moves.push([xLocation, yLocation-2]);
                    }
                    moves.push([xLocation, yLocation-1]);
                }
            } else {
                if(chessService.board.getPieceAt(xLocation, yLocation-1) === null ) {
                    moves.push([xLocation, yLocation-1]);
                }
            }

            if( (xLocation-1 >= 0 && xLocation-1<8) && chessService.board.getPieceAt(xLocation-1, yLocation-1) !== null
                && isWhite !== chessService.board.isPieceWhite(xLocation-1, yLocation-1) ){
                moves.push([xLocation-1, yLocation-1]);
            }
            if( (xLocation+1 >= 0 && xLocation+1<8) && chessService.board.getPieceAt(xLocation+1, yLocation-1) !== null
            && isWhite !== chessService.board.isPieceWhite(xLocation+1, yLocation-1) ){
                moves.push([xLocation+1, yLocation-1]);
            }
        }

        function blackPawnCheck(){
            if( yLocation === 1 ){
                if( chessService.board.getPieceAt(xLocation, yLocation+1) === null ){
                    if( chessService.board.getPieceAt(xLocation, yLocation+2) === null ){
                        moves.push([xLocation, yLocation+2]);
                    }
                    moves.push([xLocation, yLocation+1]);
                }
            } else {
                if( chessService.board.getPieceAt(xLocation, yLocation+1) === null ) {
                    moves.push([xLocation, yLocation+1]);
                }
            }

            if( (xLocation+1 >= 0 && xLocation+1<8) && chessService.board.getPieceAt(xLocation+1, yLocation+1) !== null
                && isWhite !== chessService.board.isPieceWhite(xLocation+1, yLocation+1) ){
                moves.push([xLocation+1, yLocation+1]);
            }
            if( (xLocation-1 >= 0 && xLocation-1<8) && chessService.board.getPieceAt(xLocation-1, yLocation+1) !== null
                && isWhite !== chessService.board.isPieceWhite(xLocation-1, yLocation+1) ){
                moves.push([xLocation-1, yLocation+1]);
            }
        }
    }
}

// function makeMove(fromX, fromY, toX, toY){
//     let piece = chessBoard.getPieceAt(fromX, fromY);
//     chessBoard.setPieceAt(fromX, fromY, null);
//     chessBoard.setPieceAt(toX, toY, piece);
// }
//
// function makeRoche(kingX, kingY, rookX, rookY ) {
//     if( rookX > kingX ){
//         let king = chessBoard.getPieceAt(kingX, kingY);
//         let rook = chessBoard.getPieceAt(rookX,rookY);
//         chessBoard.setPieceAt(kingX+2, kingY, king);
//         chessBoard.setPieceAt(rookX-2, rookY, rook);
//     } else {
//         let king = chessBoard.getPieceAt(kingX, kingY);
//         let rook = chessBoard.getPieceAt(rookX,rookY);
//         chessBoard.setPieceAt(kingX-2, kingY, king);
//         chessBoard.setPieceAt(rookX+3, rookY, rook);
//     }
//     chessBoard.setPieceAt(kingX, kingY, null);
//     chessBoard.setPieceAt(rookX,rookY, null);
// }
//
// function chessBoardOnClick(event) {
//     let xIndex = chessBoard.xPixelToX(event.clientX);
//     let yIndex = chessBoard.yPixelToY(event.clientY);
//     if( (xIndex < 0 || xIndex >7) || (yIndex < 0 || yIndex >7) ){
//         selectedField = null;
//         drawChessBoard();
//         return;
//     }
//     let moves = getAvailableMoves(xIndex, yIndex);
//     let playerMadeMove = false;
//     let playerMadeRoche = false;
//     readPlayerIntentions();
//
//     if( playerMadeMove ){
//         if( playerMadeRoche ){
//             makeRoche(selectedField[0], selectedField[1], xIndex, yIndex);
//             selectedField = null;
//         } else {
//             makeMove(selectedField[0], selectedField[1], xIndex, yIndex);
//             selectedField = null;
//         }
//         drawChessBoard();
//     } else if(selectedField !== null) {
//         drawChessBoard();
//         moves = getAvailableMoves(selectedField[0], selectedField[1]);
//         for (let i = 0; i < moves.length; i++) {
//             if( chessBoard.getPieceAt(moves[i][0], moves[i][1]) != null ){
//                 fillField(moves[i][0], moves[i][1], "rgba(215,46,46,0.6)");
//             } else {
//                 fillField(moves[i][0], moves[i][1], "rgba(83,208,57,0.6)");
//             }
//         }
//         fillField(xIndex, yIndex, "rgba(57,185,208,0.6)");
//     } else {
//         drawChessBoard();
//     }
//
//     function readPlayerIntentions(){
//         if( selectedField === null ){
//             if( chessBoard.getPieceAt(xIndex, yIndex) !== null ){
//                 selectedField = [xIndex, yIndex];
//             }
//         } else {
//             let moves = getAvailableMoves(selectedField[0], selectedField[1]);
//             for( let i = 0; i < moves.length; i++ ){
//                 if(moves[i][0] === xIndex && moves[i][1] === yIndex){
//                     playerMadeMove = true;
//                     if( (chessBoard.getPieceAt(selectedField[0], selectedField[1]) === chessPieces.BLACK_KING ||
//                          chessBoard.getPieceAt(selectedField[0], selectedField[1]) === chessPieces.WHITE_KING) &&
//                          Math.abs(selectedField[0] - xIndex) > 1 ){
//                         playerMadeRoche = true;
//                     }
//                     break;
//                 }
//             }
//             if(!playerMadeMove) {
//                 selectedField = null;
//             }
//         }
//     }
// }

