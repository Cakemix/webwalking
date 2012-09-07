package org.webwalking;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.map.TilePath;


public class PathEdge extends BiEdge {
	
	Tile[] path;

	public PathEdge(Node start, Node end, Tile[] path) {
		super(start, end);
	}

	public boolean run(boolean reverse) {
		TilePath tilePath = Walking.newTilePath(path);
		if(reverse) {
			return tilePath.reverse().traverse();
		} else {
			return tilePath.traverse();
		}
	}

}
