package de.dauni.chunkmaze;

public class PermissionsC {
	/**
	 * Steffion's Engine - Made by Steffion.
	 * 
	 * You're allowed to use this engine for own usage, you're not allowed to
	 * republish the engine. Using this for your own plugin is allowed when a
	 * credit is placed somewhere in the plugin.
	 * 
	 * Thanks for your cooperate!
	 * 
	 * @author Steffion
	 */

	public static String main = ChunkMaze.pdfFile.getName().toLowerCase() + ".";

	public enum PType {
		ALL, PLAYER, MODERATOR, ADMIN, OP;
	}

	public enum Permissions {
		info ("info", PType.ALL),
		help ("help", PType.ALL),
		reload ("reload", PType.ADMIN),
		join ("join", PType.PLAYER),
		joinfull ("joinfull", PType.MODERATOR),
		joinsign ("joinsign", PType.PLAYER),
		leave ("leave", PType.PLAYER),
		list ("list", PType.PLAYER),
		start ("start", PType.ADMIN),
		create ("create", PType.ADMIN),
		stop ("create", PType.ADMIN),
		set ("set", PType.MODERATOR),
		setwarp ("setwarp", PType.MODERATOR),
		signcreate ("signcreate", PType.MODERATOR),
		remove ("remove", PType.ADMIN),
		reward ("reward", PType.ADMIN),
		dif ("dif", PType.ADMIN),
		allcommands ("allcommands", PType.OP);

		public String perm;
		public PType type;

		private Permissions (String perm, PType type) {
			this.perm = perm;
			this.type = type;
		}
	}
}
