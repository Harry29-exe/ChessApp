const white = "rgb(227,227,227)", black = "rgb(28,28,28)";

export const chessPieces = {
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

export class Board {
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
        this.isItWhitesTurn = true;

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
