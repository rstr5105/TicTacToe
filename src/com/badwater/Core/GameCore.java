package com.badwater.Core;

import com.badwater.Helpers.HelperFuncs;
import com.badwater.Helpers.Tuple;
import com.badwater.Networking.ErrorCodes;
import com.badwater.Networking.Protocol;

import java.io.IOException;

/**
 * Created by irinix on 8/23/14.
 */

public class GameCore implements Runnable {
	private boolean isRunning;
	private PlayerCore[] players = new PlayerCore[2];
	Protocol protocol = new Protocol();
	GameBoard gameBoard;
	public GameCore(PlayerCore p1, PlayerCore p2) {
		initPlayers ( p1, p2 );
		gameBoard = new GameBoard ();

	}

	@Override public void run() {
		isRunning = true;
		gameLoop ();

	}

	private void gameLoop() {
		while ( isRunning ) {
			for(PlayerCore p : players) {
				if(protocol.sendPing (p).equals(ErrorCodes.PLAYER_DISSCONNECT);
			}
				if ( !update () ) {
				isRunning = false;
				System.out.println ( "Error in game update.  Quitting!" );
			}
		}
	}

	private boolean update() throws IOException {
		boolean success = false;
		for (PlayerCore player : players){
			if(protocol.sendPing(player).equals ( ErrorCodes.PLAYER_DISSCONNECT )){
				break;
			}
			else{
				Tuple<Integer, Integer> Move = protocol.sendRequestMove ( player );
			}
		}
		return true;
	}


	private void executePlayerMove(PlayerCore p, Tuple<Integer, Integer> playerMove) throws IOException {
		if(!HelperFuncs.boundsCheck ( gameBoard.getBoard(),playerMove.getFirst (), playerMove.getSecond () )){
			protocol.sendError(p, ErrorCodes.INVALID_MOVE);
		}
		else if(gameBoard.isCellOccupied(playerMove)){
			protocol.sendError(p, ErrorCodes.TILE_OCCUPIED);
		}
		else{
			gameBoard.setBoardTile ( playerMove, p.getPiece() );
		}
	}


	private boolean initPlayers(PlayerCore p1, PlayerCore p2) {

		if ( HelperFuncs.coinFlip ( .50f ) ) {
			this.players[0] = p1;
			this.players[1] = p2;
			players[0].setPiece('X');
			players[1].setPiece('O');

			return true;
		}
		else {
			this.players[0] = p2;
			this.players[1] = p1;
			players[0].setPiece('X');
			players[1].setPiece('O');

			return true;
		}


	}
}
