package org.webwalking.plugins;

import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.ChatOptions;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.ChatOption;
import org.webwalking.Condition;
import org.webwalking.Edge;
import org.webwalking.Node;
import org.webwalking.PathEdge;
import org.webwalking.Plugin;

public class Varrock extends Plugin {

	public static final Node GrandExchange_NW = new Node(3151, 3504, 0);
	public static final Node GrandExchange_NE = new Node(3181, 3502, 0);
	public static final Node GrandExchange_SW = new Node(3148, 3476, 0);
	public static final Node GrandExchange_SE = new Node(3180, 3482, 0);
	public static final Node GrandExchange_Exit_East = new Node(3191, 3492, 0);
	public static final Node GrandExchange_Exit_South = new Node(3167, 3466, 0);
	public static final Node GrandExchange_Exit_NW = new Node(3143, 3513, 0);
	public static final Node GrandExchange_Exit_NW_Outside = new Node(3138,
			3516, 0);

	@Override
	public void init() {

		provide(new PathEdge(GrandExchange_NW, GrandExchange_NE, new Tile[] {
				new Tile(3166, 3505, 0), new Tile(3174, 3505, 0),
				new Tile(3181, 3502, 0) }));

		provide(new PathEdge(GrandExchange_NE, GrandExchange_Exit_East,
				new Tile[] { new Tile(3186, 3497, 0), new Tile(3191, 3492, 0) }));

		provide(new PathEdge(GrandExchange_Exit_East, GrandExchange_SE,
				new Tile[] { new Tile(3186, 3487, 0), new Tile(3180, 3482, 0) }));

		provide(new PathEdge(GrandExchange_SE, GrandExchange_Exit_South,
				new Tile[] { new Tile(3174, 3478, 0), new Tile(3169, 3473, 0),
						new Tile(3167, 3466, 0) }));

		provide(new PathEdge(GrandExchange_Exit_South, GrandExchange_SW,
				new Tile[] { new Tile(3160, 3469, 0), new Tile(3155, 3475, 0),
						new Tile(3148, 3476, 0) }));

		provide(new PathEdge(GrandExchange_SW, GrandExchange_NW, new Tile[] {
				new Tile(3145, 3483, 0), new Tile(3145, 3491, 0),
				new Tile(3151, 3504, 0) }));

		provide(new PathEdge(GrandExchange_NW, GrandExchange_Exit_NW,
				new Tile[] { new Tile(3147, 3510, 0), new Tile(3143, 3513, 0) }));
		

		provide(new Edge(GrandExchange_Exit_NW, GrandExchange_Exit_NW_Outside,
				new Condition() {
					public boolean validate() {
						return Skills.getLevel(Skills.AGILITY) >= 21;
					}
				}) {
			public boolean run() {
				SceneObject tunnel = SceneEntities.getNearest(9312);
				return tunnel != null
						&& tunnel.interact("Climb-into", "Underwall tunnel");
			}
		});
		
		provide(new Edge(GrandExchange_Exit_NW_Outside, GrandExchange_Exit_NW,
				new Condition() {
					public boolean validate() {
						return Skills.getLevel(Skills.AGILITY) >= 21;
					}
				}) {
			public boolean run() {
				SceneObject tunnel = SceneEntities.getNearest(9311);
				return tunnel != null
						&& tunnel.interact("Climb-into", "Underwall tunnel");
			}
		});
		
		provide(new Edge(Node.ANYWHERE, GrandExchange_Exit_South, new Condition() {
			public boolean validate() {
				return Inventory.getCount(20653,20655,20657,20659) > 0;
			}
		}) {
			public boolean run() {
				Item ringOfWealth = Inventory.getItem(20653,20655,20657,20659);
				if(ringOfWealth == null || !ringOfWealth.getWidgetChild().interact("Rub")) {
					return false;
				}
				ChatOption option = ChatOptions.getOption("Grand Exchange.");
				return option != null && option.getWidgetChild().interact("Continue");
			}
		});

	}

}
