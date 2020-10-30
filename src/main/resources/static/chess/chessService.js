import {Board, chessPieces} from "/chess/chessBoard.js";

export class ChessService {
    constructor() {
        this.board = new Board();
        this.selectedX = null;
        this.selectedY = null;
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
            moves = getAvailableMoves(this.selectedX, this.selectedY, this.board);
            // console.log("Moves: ");
            // console.log(moves);

            if ((this.board.isItWhitesTurn && this.selectedIsWhite()) || (!this.board.isItWhitesTurn && !this.selectedIsWhite())) {
                if (isMoveInMoves()) {
                    // console.log("move");
                    this.makeMove(this.selectedX, this.selectedY, clickedX, clickedY);
                } else {
                    // console.log("not a move");
                    this.selectedX = clickedX;
                    this.selectedY = clickedY;
                    return this.getColoredFields(clickedX, clickedY);
                }
            } else {
                // console.log("player is not a piece owner");
                this.selectedX = clickedX;
                this.selectedY = clickedY;
                return this.getColoredFields(clickedX, clickedY);
            }
        }
        // console.log("null");
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
        let availableMoves = getAvailableMoves(clickedX, clickedY, this.board)
        let fieldsToColor = [[clickedX, clickedY, "rgba(32,167,221,0.6)"]];
        for (let i = 0; i < availableMoves.length; i++) {
            fieldsToColor.push([availableMoves[i][0],availableMoves[i][1],
                this.board.isPieceWhite(availableMoves[i][0],availableMoves[i][1]) === !this.board.isItWhitesTurn?
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
        this.board.isItWhitesTurn = !this.board.isItWhitesTurn;
    }
}


function getAvailableMoves(xLocation,yLocation, board) {
    let moves = [];
    let x,y;

    let isWhite = board.isPieceWhite(xLocation,yLocation);
    switch( board.getPieceAt(xLocation,yLocation) ) {
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
        if( board.getPieceAt(x, y) === null ){
            moves.push([x,y]);
            x += xChange;
            y += yChange;
        } else if( board.isPieceWhite(x, y) !== isWhite ) {
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
                if( board.getPieceAt(x, y) === null ){
                    moves.push([x, y]);
                } else if( board.isPieceWhite(x, y) !== isWhite ){
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
                    if( board.getPieceAt(x+xChange, y+yChange) === null ){
                        moves.push([x+xChange, y+yChange]);
                    } else if( board.isPieceWhite(x+xChange, y+yChange) !== isWhite ){
                        moves.push([x+xChange, y+yChange]);
                    }
                }
            }
        }

        if( isWhite ){
            if( board.isCastlingAvailable.WHITE_LONG ){
                if( board.getPieceAt(4,7) === null &&
                    board.getPieceAt(5,7) === null &&
                    board.getPieceAt(6,7) === null ){
                    //moves.push
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
                if( board.getPieceAt(xLocation, yLocation-1) === null ){
                    if( board.getPieceAt(xLocation, yLocation-2) === null ){
                        moves.push([xLocation, yLocation-2]);
                    }
                    moves.push([xLocation, yLocation-1]);
                }
            } else {
                if( board.getPieceAt(xLocation, yLocation-1) === null ) {
                    moves.push([xLocation, yLocation-1]);
                }
            }

            if( (xLocation-1 >= 0 && xLocation-1<8) && board.getPieceAt(xLocation-1, yLocation-1) !== null
                && isWhite !== board.isPieceWhite(xLocation-1, yLocation-1) ){
                moves.push([xLocation-1, yLocation-1]);
            }
            if( (xLocation+1 >= 0 && xLocation+1<8) && board.getPieceAt(xLocation+1, yLocation-1) !== null
                && isWhite !== board.isPieceWhite(xLocation+1, yLocation-1) ){
                moves.push([xLocation+1, yLocation-1]);
            }
        }

        function blackPawnCheck(){
            if( yLocation === 1 ){
                if( board.getPieceAt(xLocation, yLocation+1) === null ){
                    if( board.getPieceAt(xLocation, yLocation+2) === null ){
                        moves.push([xLocation, yLocation+2]);
                    }
                    moves.push([xLocation, yLocation+1]);
                }
            } else {
                if( board.getPieceAt(xLocation, yLocation+1) === null ) {
                    moves.push([xLocation, yLocation+1]);
                }
            }

            if( (xLocation+1 >= 0 && xLocation+1<8) && board.getPieceAt(xLocation+1, yLocation+1) !== null
                && isWhite !== board.isPieceWhite(xLocation+1, yLocation+1) ){
                moves.push([xLocation+1, yLocation+1]);
            }
            if( (xLocation-1 >= 0 && xLocation-1<8) && board.getPieceAt(xLocation-1, yLocation+1) !== null
                && isWhite !== board.isPieceWhite(xLocation-1, yLocation+1) ){
                moves.push([xLocation-1, yLocation+1]);
            }
        }
    }
}
