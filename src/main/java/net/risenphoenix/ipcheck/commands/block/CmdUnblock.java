/*
 * Copyright © 2014 Jacob Keep (Jnk1296). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, 
 *   this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice, 
 *   this list of conditions and the following disclaimer in the documentation 
 *   and/or other materials provided with the distribution.
 *
 *  * Neither the name of JuNK Software nor the names of its contributors may 
 *   be used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package net.risenphoenix.ipcheck.commands.block;

import net.risenphoenix.commons.Plugin;
import net.risenphoenix.commons.commands.CommandType;
import net.risenphoenix.ipcheck.IPCheck;
import net.risenphoenix.ipcheck.commands.IPCCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class CmdUnblock extends IPCCommand {

    private BlockManager cBlockManager;

    public CmdUnblock(final Plugin plugin, String[] callArgs, CommandType type) {
        super((IPCheck) plugin, callArgs, type);

        IPCheck ipc = IPCheck.getInstance();
        this.cBlockManager = ipc.getBlockManager();

        setName(getLocalString("CMD_UNBLOCK"));
        setHelp(getLocalString("HELP_UNBLOCK"));
        setSyntax("ipc unblock <COUNTRY_ID | help>");
        setPermissions(new Permission[]{
                new Permission("ipcheck.use"),
                new Permission("ipcheck.unblock")
        });
    }

    @Override
    public void onExecute(CommandSender sender, String[] args) {
        // If the player requests help, return a link to Bukkit.
        if (args[1].equalsIgnoreCase("help")) {
            sendPlayerMessage(sender, getLocalString("BLOCK_HELP"));
            return;
        }

        boolean result = cBlockManager.unblockCountry(args[1]);
        String msg = ((result) ? getLocalString("UNBLOCK_SUC") :
                getLocalString("UNBLOCK_ERR"));

        // Respond to Sender
        sendPlayerMessage(sender, msg);
    }

}
