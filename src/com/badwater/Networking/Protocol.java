package com.badwater.Networking;

import com.badwater.Core.PlayerCore;
import com.badwater.Helpers.HelperFuncs;
import com.badwater.Helpers.Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by irinix on 8/23/14.
 */


public class Protocol {
	private ArrayList<Integer> availablePorts = new ArrayList<Integer> ();
	private HashMap<PlayerCore, Socket> playerSocks = new HashMap<PlayerCore, Socket> ();
	private PrintWriter out;
	private InputStreamReader in;

	public Protocol() {
		initPorts ();
	}

	private void initPorts() {
		for ( int i = 53000; i < 65535; i++ ) {
			availablePorts.add ( i );
		}
	}

	private void bindSocks(PlayerCore player) {
		//bind player socket to random available port.
		int x = availablePorts.size ();
		int y = availablePorts.get ( 0 );
		try {
			playerSocks.put ( player, new Socket ( player.getAddress (),
			                                       availablePorts.get ( HelperFuncs.nextIntInRange ( x, y ) ) ) );
			availablePorts.remove ( playerSocks.get ( player ) );
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	private void unBindSocks(PlayerCore player) {
		//unbind player socket,
		//return port to list of available ports.
		availablePorts.add ( playerSocks.get ( player ).getPort (), playerSocks.get ( player ).getPort () );
		try {
			playerSocks.get ( player ).close ();
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	public void sendError(PlayerCore p, ErrorCodes error) throws IOException {
		Socket socket = playerSocks.get ( p );
		try (PrintWriter out = new PrintWriter ( socket.getOutputStream (), true )) {
			out.write ( error.toString () );
		}
	}

	private boolean sendPing(PlayerCore p) throws IOException {
		boolean success = false;
		try (BufferedReader in = new BufferedReader (
			   new InputStreamReader ( playerSocks.get ( p ).getInputStream () ) )) {
			String line = "";
			while ( ( !TimeOut () )) {
				line = in.readLine ();
				if ( line.equalsIgnoreCase ( "PONG" ) ) {
					success = true;
				}
			}

		} return success;
	}


	public Tuple<Integer, Integer> sendRequestMove(PlayerCore p) throws IOException {
		Socket socket = playerSocks.get ( p );
		Tuple<Integer, Integer> playerMove;
		try (PrintWriter out = new PrintWriter ( socket.getOutputStream (), true )) {
			out.write ( "?" );
		}
		while ( !TimeOut () ) {
			try (BufferedReader in = new BufferedReader (
				   new InputStreamReader ( playerSocks.get ( p ).getInputStream () ) )) {
				playerMove = HelperFuncs.convertToMove ( in.readLine () );
				return playerMove;
			}
		}
		return null;
	}

	public boolean TimeOut() throws IOException {
		long elapsedTime = 0;
		long currTime = System.currentTimeMillis ();
		while ( elapsedTime < 3000 ) {
			elapsedTime = System.currentTimeMillis () - currTime;
		}
		return false;
	}
}
