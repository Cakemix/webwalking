package org.webwalking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Edge implements Providable {
	
	public Edge(Node start, Node end) {
		this(start,end,Condition.TRUE);
	}
	
	public Edge(Node start, Node end, Condition condition) {
		this.start = start;
		this.end = end;
		this.condition = condition;
	}
	
	Condition condition;
	Node start, end;
	
	public abstract boolean run();

	public static List<Edge> getEdges(Node n) {
		if (edges.containsKey(n)) {
			return edges.get(n);
		} else {
			return new ArrayList<Edge>();
		}
	}

	static Map<Node, List<Edge>> edges = new HashMap<Node, List<Edge>>();
	
	protected static void addEdgeToNode(Node n,Edge e) {
		if (!edges.containsKey(n)) {
			edges.put(n, new ArrayList<Edge>());
		}
		edges.get(n).add(e);
	}

	public void onProvided() {
		addEdgeToNode(start,this);
	}
}
