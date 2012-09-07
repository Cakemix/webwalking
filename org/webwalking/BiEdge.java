package org.webwalking;


public abstract class BiEdge implements Providable {
	
	Node start, end;

	public BiEdge(Node start, Node end) {
		this.start = start;
		this.end = end;
	}

	public abstract boolean run(boolean reverse);

	public void onProvided() {
		Edge.addEdgeToNode(start,new Edge(start,end) {
			public boolean run() {
				return BiEdge.this.run(false);
			}
		});
		Edge.addEdgeToNode(end,new Edge(end,start) {
			public boolean run() {
				return BiEdge.this.run(true);
			}
		});
	}
}
