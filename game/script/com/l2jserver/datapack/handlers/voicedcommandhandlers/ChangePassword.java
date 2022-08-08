/*
 * Copyright © 2004-2021 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.datapack.handlers.voicedcommandhandlers;

import java.util.StringTokenizer;
import java.util.logging.Level;

import com.l2jserver.gameserver.LoginServerThread;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.handler.IVoicedCommandHandler;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * Change Password voiced command handler.
 * @author Nik
 */
public class ChangePassword implements IVoicedCommandHandler {
	private static final String[] COMMANDS = {
		"changepassword"
	};
	
	@Override
	public boolean useVoicedCommand(String command, L2PcInstance activeChar, String target) {
		if (target != null) {
			final StringTokenizer st = new StringTokenizer(target);
			try {
				String curpass = null, newpass = null, repeatnewpass = null;
				if (st.hasMoreTokens()) {
					curpass = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					newpass = st.nextToken();
				}
				if (st.hasMoreTokens()) {
					repeatnewpass = st.nextToken();
				}
				
				if (!((curpass == null) || (newpass == null) || (repeatnewpass == null))) {
					if (!newpass.equals(repeatnewpass)) {
						activeChar.sendMessage("The new password doesn't match with the repeated one!");
						return false;
					}
					if (newpass.length() < 3) {
						activeChar.sendMessage("The new password is shorter than 3 chars! Please try with a longer one.");
						return false;
					}
					if (newpass.length() > 30) {
						activeChar.sendMessage("The new password is longer than 30 chars! Please try with a shorter one.");
						return false;
					}
					
					LoginServerThread.getInstance().sendChangePassword(activeChar.getAccountName(), activeChar.getName(), curpass, newpass);
				} else {
					activeChar.sendMessage("Invalid password data! You have to fill all boxes.");
					return false;
				}
			} catch (Exception e) {
				activeChar.sendMessage("A problem occured while changing password!");
				_log.log(Level.WARNING, "", e);
			}
		} else {
			String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/mods/ChangePassword.htm");
			if (html == null) {
				html = "<html><body><br><br><center><font color=LEVEL>404:</font> File Not Found</center></body></html>";
			}
			activeChar.sendPacket(new NpcHtmlMessage(html));
			return true;
		}
		return true;
	}
	
	@Override
	public String[] getVoicedCommandList() {
		return COMMANDS;
	}
}