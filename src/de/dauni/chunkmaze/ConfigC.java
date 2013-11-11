package de.dauni.chunkmaze;

import de.dauni.chunkmaze.Managers.ConfigM;

public enum ConfigC {

	chat_tag ("[Maze] ", W.config),
	chat_normal ("&b", W.config),
	chat_warning ("&c", W.config),
	chat_error ("&c", W.config),
	chat_arg ("&e", W.config),
	chat_header ("&9", W.config),
	chat_headerhigh ("%H_______.[ %A%header%%H ]._______", W.config),

	commandEnabled_info (true, W.config),
	commandEnabled_help (true, W.config),
	commandEnabled_reload (true, W.config),
	commandEnabled_join (true, W.config),
	commandEnabled_leave (true, W.config),
	commandEnabled_list (true, W.config),
	commandEnabled_start (true, W.config),
	commandEnabled_wand (true, W.config),
	commandEnabled_create (true, W.config),
	commandEnabled_setwarp (true, W.config),
	commandEnabled_remove (true, W.config),
	commandEnabled_reward (true, W.config),
	commandEnabled_dif (true, W.config),

	autoUpdateCheck (true, W.config),
	autoDownloadUpdate (false, W.config),

	wandIDname ("STICK", W.config),
	wandName ("%A&l" + ChunkMaze.pdfFile.getName() + "%N's selection wand",
			W.config),
	wandDescription (new String[] {
			"%NUse this item to select an arena for your arena.",
			"%ALeft-Click%N to select point #1.",
			"%ARight-Click%N to select point #2.",
			"%NUse the create command to define your arena.",
			"%A/" + ChunkMaze.pdfFile.getName() + " <help|h>" }, W.config),
	requireInventoryClearOnJoin (false, W.config),


	sign_LEAVE (new String[] { "%H[" + ChunkMaze.pdfFile.getName() + "%H]",
			"&4LEAVE", "&8Right-Click", "&8To leave." }, W.config),
	sign_INGAME (new String[] { "%H[" + ChunkMaze.pdfFile.getName() + "%H]",
			"%A%arenaname%", "%A%players%%N",
			"&2Active" }, W.config),

	log_enabledPlugin ("%TAG%N%name%&a&k + %N%version% is now Enabled. Made by %A%autors%%N.",
			W.messages),
	log_disabledPlugin ("%TAG%N%name%&c&k - %N%version% is now Disabled. Made by %A%autors%%N.",
			W.messages),

	help_info ("%NDisplays the plugin's info.", W.messages),
	help_help ("%NShows a list of commands.", W.messages),
	help_reload ("%NReloads all configs.", W.messages),
	help_join ("%NJoins a " + ChunkMaze.pdfFile.getName() + " game.",
			W.messages),
	help_stop ("%NStopps a " + ChunkMaze.pdfFile.getName() + " game.",
			W.messages),
	help_start ("%NStarts a " + ChunkMaze.pdfFile.getName() + " game.",
			W.messages),
	help_leave ("%NLeave a " + ChunkMaze.pdfFile.getName() + " game.",
			W.messages),
	help_list ("%NShows a list of available arenas.", W.messages),
	help_shop ("%NOpens the " + ChunkMaze.pdfFile.getName() + " shop.",
			W.messages),
	help_wand ("%NGives you the wand selection tool.", W.messages),
	help_create ("%NCreates an arena from your selection.", W.messages),
	help_set ("%NOpens a panel to set settings.", W.messages),
	help_setwarp ("%NSets warps for your arena.", W.messages),
	help_remove ("%NDeletes an Arena.", W.messages),
	help_reward ("%NSets the rewarded Bytes for an Arena.", W.messages),
	help_dif ("%NSets the time difference between 2 Maze wins.", W.messages),


	win_bytes (1000,
			W.messages),
	normal_reloadedConfigs ("%TAG&aReloaded all configs!", W.messages),
	normal_joinJoinedArena ("%TAG%A%playername%%N joined the maze. (%A%1%%N Players)",
			W.messages),
	normal_winArena ("%TAG%A%playername%%N won the maze!",
			W.messages),
	normal_leaveYouLeft ("%TAG%NYou left the arena! Thanks for playing ;)!",
			W.messages),
	normal_leaveLeftArena ("%TAG%A%playername%%N left the maze. (%A%1%%N Players)",
			W.messages),
	normal_startForced ("%TAG%NYou forced to start maze '%A%arenaname%%N'!",
			W.messages),
	normal_wandGaveWand ("%TAG%NHere you go ;)! &o(Use the %A&o%type%%N&o!)",
			W.messages),
	normal_wandSetPosition ("%TAG%NSet position %A#%number%%N to location: (%A%x%%N, %A%y%%N, %A%z%%N).",
			W.messages),
	normal_createCreatedArena ("%TAG%NCreated an arena with the name '%A%name%%N'.",
			W.messages),
	normal_lobbyArenaIsStarting ("%TAG%NThe arena will start in %A%1%%N second(s)!",
			W.messages),
	normal_lobbyArenaStarted ("%TAG%NThe arena has been started! The seeker is coming to find you in %A%secs%%N seconds!",
			W.messages),
	normal_ingameSeekerChoosen ("%TAG%NPlayer %A%seeker%%N has been choosen as seeker!",
			W.messages),
	normal_ingameBlock ("%TAG%NYou're disguised as a(n) '%A%block%%N' block.",
			W.messages),
	normal_setwarpWarpSet ("%TAG%NSet warp '%A%warp%%N' to your location!",
			W.messages),
	normal_removeRemovedArena ("%TAG%NRemoved arena '%A%name%%N'!", W.messages),
	normal_rewardset ("%TAG%NReward set!", W.messages),
	normal_diffset ("%TAG%NTime difference set!", W.messages),
	warning_unableToCommand ("%TAG%WSorry but that command is disabled in the arena.",
			W.messages),
	warning_arenaStopped ("%TAG%WThe arena has been forced to stop!",
			W.messages),
	warning_arenaStarted ("%TAG%WThe arena has been started!",
			W.messages),

	error_noPermission ("%TAG%EYou don't have the permissions to do that!",
			W.messages),
	error_notANumber ("%TAG%E'%A%1%%E' is not a number!", W.messages),
	error_commandNotEnabled ("%TAG%EThis command has been disabled!",
			W.messages),
	error_commandNotFound ("%TAG%ECouldn't find the command. Try %A/"
			+ "ChunkMaze" + " help %Efor more info.",
			W.messages),
	error_notEnoughArguments ("%TAG%EYou're missing arguments, correct syntax: %A%syntax%",
			W.messages),
	error_noArena ("%TAG%ENo arena found with the name '%A%name%%E'.",
			W.messages),
	error_onlyIngame ("%TAG%EThis is an only in-game command!", W.messages),
	error_joinAlreadyJoined ("%TAG%EYou've already joined an arena!",
			W.messages),
	error_joinWarpsNotSet ("%TAG%EThere are no warps set for this arena. Notify the administrator.",
			W.messages),
	error_internal ("%TAG%EAn internal error occured. Write a /pe.",
			W.messages),
	error_joinArenaIngame ("%TAG%EThis game has already started.", W.messages),
	error_joinFull ("%TAG%EUnable to join this arena. It's full!", W.messages),
	error_joinInventoryNotEmpty ("%TAG%EYour inventory should be empty before joining!",
			W.messages),
	error_leaveNotInArena ("%TAG%EYou're not in an arena!", W.messages),
	error_createSelectionFirst ("%TAG%EMake a selection first. Use the wand command: %A/"
			+ ChunkMaze.pdfFile.getName() + " <wand|w>%E.",
			W.messages),
	error_createNotSameWorld ("%TAG%EMake your selection points in the same world!",
			W.messages),
	error_setwarpWarpNotFound ("%TAG%EWarp '%A%warp%%E' is not valid!",
			W.messages),
	error_notInArena ("%TAG%EYou can only use the Shop in an Arena",
			W.messages),
	error_notenoughmoney ("%TAG%EYou don't have enough Bytes.",
			W.messages),
	error_alreadywon ("%TAG%AYou have to wait %N%hours%Hour(s)%A. Last time: %N%lasttime%%A",
			W.messages),
	error_arenanotexists ("%TAG%AArena does not exist!",
			W.messages);
	
	public Object value;
	public ConfigM config;
	public String location;

	/**
	 * Makes an object from the list above.
	 * 
	 * @param value
	 *            Setting in the config file.
	 * @param config
	 *            The config file.
	 */
	private ConfigC (Object value, ConfigM config) {
		this.value = value;
		this.config = config;
		this.location = this.name().replaceAll("_", ".");
	}
}
