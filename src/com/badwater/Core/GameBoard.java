package com.badwater.Core;

import com.badwater.Helpers.Tuple;

/**
 * Created by irinix on 8/23/14.
 */
public class GameBoard {
	char[][] board;

	public GameBoard() {
		initBoard ();
	}

	private void initBoard() {
		board = new char[3][3];
	}

	private void placePiece(int x, int y) {
		//BoundsCheck it.
		//If not in bounds, send error to client.
		//if it is in bounds, check to see if slot is available.
		//if slot is not avaliable.  send error to client.
		//if slot is available place player piece in slot.

	}

	public boolean isCellOccupied(Tuple<Integer, Integer> boardAddr) {
		if ( board[boardAddr.getFirst ()][boardAddr.getSecond ()] == 'X'
		     || board[boardAddr.getFirst ()][boardAddr.getSecond ()] == 'O' ) {
			return true;
		}
		else {
			return false;
		}
	}
	public void setBoardTile(Tuple<Integer, Integer> boardAddr,char c){
		board[boardAddr.getFirst ()][boardAddr.getSecond ()] = c;
	}

	public char[][] getBoard() {
		return board;
	}
}

