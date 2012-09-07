package org.webwalking;

import java.util.HashSet;
import java.util.Set;

import org.powerbot.game.api.wrappers.Tile;

public class Node implements Providable {
	
	Tile location;
	
	public Node(int x, int y, int k) {
		this(x,y,k,true);
	}
	
	public Node(int x, int y, int k, boolean immediatelyProvide) {
		this(new Tile(x,y,k), immediatelyProvide);
	}
	
	public Node(Tile location, boolean immediatelyProvide) {
		this.location = location;
		if(immediatelyProvide) onProvided();
	}
	
	public Tile getLocation() {
		return location;
	}
	
	public static Node ANYWHERE = new Node(0,0,0);
	
	public static Set<Node> getAllNodes() {
		return nodes;
	}
	static Set<Node> nodes = new HashSet<Node>();

	public void onProvided() {
		nodes.add(this);
	}
}
