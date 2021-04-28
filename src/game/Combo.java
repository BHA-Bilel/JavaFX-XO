package game;

public class Combo {
	private final Tile[] tiles;

	public Combo(Tile... tiles) {
		this.tiles = tiles;
	}

	public boolean isComplete() {
		return !tiles[0].getValue().isEmpty() && tiles[0].getValue().equals(tiles[1].getValue())
				&& tiles[1].getValue().equals(tiles[2].getValue());
	}
	
	public Tile[] getTiles() {
		return tiles;
	}
}
