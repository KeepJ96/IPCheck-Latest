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

package net.risenphoenix.ipcheck.actions;

import net.risenphoenix.commons.configuration.ConfigurationManager;
import net.risenphoenix.commons.localization.LocalizationManager;
import net.risenphoenix.ipcheck.IPCheck;
import net.risenphoenix.ipcheck.database.DatabaseController;
import net.risenphoenix.ipcheck.objects.IPObject;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActionKick {

    private IPCheck ipc;
    private DatabaseController db;
    private LocalizationManager local;
    private ConfigurationManager config;

    private CommandSender sender;
    private String input;
    private String message;

    public ActionKick(final IPCheck ipcheck, CommandSender sender, String input,
                     String message) {
        this.ipc = ipcheck;
        this.db = ipcheck.getDatabaseController();
        this.local = ipcheck.getLocalizationManager();
        this.config = ipcheck.getConfigurationManager();

        this.sender = sender;
        this.input = input;
        this.message = message;
    }

    public Object[] execute() {
        // Variable Storage
        IPObject ipo;
        int accounts = 0;

        // Differentiate between Player-name and IP
        String ip_filter = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

        // Fetch IP-Object
        if (this.input.matches(ip_filter)) {
            ipo = this.db.getIPObject(this.input);
        } else {
            ipo = this.db.getIPObject(this.db.getLastKnownIP(this.input));
        }

        // Validate IPO
        if (!this.db.isValidIP(ipo.getIP())) {
            this.ipc.sendPlayerMessage(sender, local.getLocalString("NO_FIND"));
            return new Object[]{0};
        }

        // Store Ban Message
        String kickMsg = (message == null || message.length() <= 0) ?
                config.getString("kick-message") : message;

        // Execute Kick
        for (String s : ipo.getUsers()) {
            // Fetch Player Object
            Player player = Bukkit.getPlayer(s);

            // Kick Player
            if (player != null) {
                player.kickPlayer(kickMsg);
                accounts++;
            }
        }

        String calledAcct;

        if (input.matches(ip_filter)) {
            calledAcct = ipo.getUsers().get(0);
        } else{
            calledAcct = input;
        }

        return new Object[]{accounts, calledAcct};
    }

}
