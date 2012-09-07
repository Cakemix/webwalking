package org.webwalking;


public abstract class Plugin implements Providable {
	protected final <T extends Providable> void provide(T v) {
		v.onProvided();
	}
	
	public abstract void init();
	
	public void onProvided() {
		init();
	}

}
