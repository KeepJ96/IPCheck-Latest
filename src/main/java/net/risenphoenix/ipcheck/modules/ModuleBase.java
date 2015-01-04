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

package net.risenphoenix.ipcheck.modules;

import net.risenphoenix.commons.configuration.ConfigurationManager;
import net.risenphoenix.commons.localization.LocalizationManager;
import net.risenphoenix.commons.stores.CommandStore;
import net.risenphoenix.ipcheck.IPCheck;
import org.bukkit.command.CommandSender;

import java.util.logging.Level;

public class ModuleBase {

    private final IPCheck plugin;
    private final ConfigurationManager config;
    private final LocalizationManager lm;

    private String CALL_ID = "GENSYSMOD"; // Generic System Module
    private Module moduleType = Module.GENMODULE;

    public ModuleBase(final IPCheck plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigurationManager();
        this.lm = plugin.getLocalizationManager();
    }

    public final String getCallHandle() {
        return this.CALL_ID;
    }

    public final Module getModuleType() {
        return this.moduleType;
    }

    protected final void setCallHandle(String callHandle) {
        this.CALL_ID = callHandle;
    }

    protected final void setModuleType(Module moduleType) {
        this.moduleType = moduleType;
    }

    public void registerCommands(CommandStore store) {
        throw new UnsupportedOperationException(
                getLocalString("NO_IMPLEMENT")
        );
    }

    public final IPCheck getPlugin() {
        return this.plugin;
    }

    public final ConfigurationManager getConfig() {
        return this.config;
    }

    public final String getLocalString (String key) {
        return this.lm.getLocalString(key);
    }

    public final void sendPlayerMessage(CommandSender sender, String message) {
        this.sendPlayerMessage(sender, message, true);
    }

    public final void sendPlayerMessage(CommandSender sender, String message,
                                        boolean useName) {
        this.plugin.sendPlayerMessage(sender, message, useName);
    }

    public final void sendConsoleMessage(Level level, String message) {
        this.plugin.sendConsoleMessage(level, message);
    }

}
