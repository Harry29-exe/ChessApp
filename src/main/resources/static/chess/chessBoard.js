const white = "rgb(227,227,227)", black = "rgb(28,28,28)";

export const chessPieces = {
    WHITE_KING: 6,
    WHITE_QUEEN: 5,
    WHITE_ROOK: 2,
    WHITE_BISHOP: 4,
    WHITE_KNIGHT: 3,
    WHITE_PAWN: 1,

    NULL: 0,

    BLACK_KING: -6,
    BLACK_QUEEN: -5,
    BLACK_ROOK: -2,
    BLACK_BISHOP: -4,
    BLACK_KNIGHT: -3,
    BLACK_PAWN: -1,


    assignPieceToInt: function (value) {
        switch (value) {
            case 6:
                return chessPieces.WHITE_KING;
            case 5:
                return chessPieces.WHITE_QUEEN;
            case 2:
                return chessPieces.WHITE_ROOK;
            case 4:
                return chessPieces.WHITE_BISHOP;
            case 3:
                return chessPieces.WHITE_KNIGHT;
            case 1:
                return chessPieces.WHITE_PAWN;
            case -6:
                return chessPieces.BLACK_KING;
            case -5:
                return chessPieces.BLACK_QUEEN;
            case -2:
                return chessPieces.BLACK_ROOK;
            case -4:
                return chessPieces.BLACK_BISHOP;
            case -3:
                return chessPieces.BLACK_KNIGHT;
            case -1:
                return chessPieces.BLACK_PAWN;
        }
    },
    //LAST_WHITE_PIECE_INDEX: 0,
    BOTTOM_PIECES_COLOR: white
}

export class Board {
    constructor() {
        this.pieces = [
            [chessPieces.BLACK_ROOK, chessPieces.BLACK_KNIGHT, chessPieces.BLACK_BISHOP, chessPieces.BLACK_KING,
                chessPieces.BLACK_QUEEN, chessPieces.BLACK_BISHOP, chessPieces.BLACK_KNIGHT, chessPieces.BLACK_ROOK],
            [chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN,
                chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN, chessPieces.BLACK_PAWN],
            [chessPieces.NULL,chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL],
            [chessPieces.NULL,chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL],
            [chessPieces.NULL,chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL],
            [chessPieces.NULL,chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL, chessPieces.NULL],
            [chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN,
                chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN, chessPieces.WHITE_PAWN],
            [chessPieces.WHITE_ROOK, chessPieces.WHITE_KNIGHT, chessPieces.WHITE_BISHOP, chessPieces.WHITE_KING,
                chessPieces.WHITE_QUEEN, chessPieces.WHITE_BISHOP, chessPieces.WHITE_KNIGHT, chessPieces.WHITE_ROOK]
        ];

        this.castling = {
            whiteLong: true,
            whiteShort: true,
            blackLong: true,
            blackShort: true
        };

        this.elPassantBeatingAvailableAt = {x: -1, y: -1};
        this.isItWhitesTurn = true;

    }

    setPieceAt( x, y , piece){
        this.pieces[y][x] = piece;
    }

    getPieceAt( x, y ) {
        return this.pieces[y][x];
    }

    isPieceWhite(x,y) {
        if(this.getPieceAt(x,y) !== chessPieces.NULL)
            return this.getPieceAt(x,y).valueOf() > 0;
        else
            return null;
    }

}
