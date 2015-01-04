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

package net.risenphoenix.ipcheck.modules.sysmods;

import net.risenphoenix.commons.commands.CommandType;
import net.risenphoenix.commons.stores.CommandStore;
import net.risenphoenix.ipcheck.IPCheck;
import net.risenphoenix.ipcheck.commands.*;
import net.risenphoenix.ipcheck.commands.toggle.CmdToggle;
import net.risenphoenix.ipcheck.modules.Module;
import net.risenphoenix.ipcheck.modules.ModuleBase;

public class ModuleCore extends ModuleBase {

    public ModuleCore(final IPCheck plugin) {
        super(plugin);
        setCallHandle("ALTACTSYS");
        setModuleType(Module.ALTACTSYS);
    }

    @Override
    public void registerCommands(CommandStore store) {
        // About Command
        store.add(
                new CmdAbout(getPlugin(), new String[]{"ipc", "about"},
                        CommandType.STATIC));

        // Help Command
        store.add(
                new CmdHelp(getPlugin(), new String[]{"ipc", "help",
                        "VAR_ARG_OPT"}, CommandType.VARIABLE));

        // Purge Command
        store.add(
                new CmdPurge(getPlugin(), new String[]{"ipc", "purge",
                        "VAR_ARG"}, CommandType.VARIABLE));

        // Reload Command
        store.add(
                new CmdReload(getPlugin(), new String[]{"ipc", "reload"},
                        CommandType.STATIC));

        // Scan Command
        store.add(
                new CmdScan(getPlugin(), new String[]{"ipc", "scan"},
                        CommandType.STATIC));

        // Status Command
        store.add(
                new CmdStatus(getPlugin(), new String[]{"ipc", "status"},
                        CommandType.STATIC));

        /*// Toggle Command - DISABLED DUE TO MODULE CONVERSION (Most values it
            controlled have been converted to Module Toggles)
        store.add(
                new CmdToggle(getPlugin(), new String[]{"ipc", "toggle",
                        "VAR_ARG"}, CommandType.VARIABLE)); */

        // ROOT COMMAND
        store.add(
                new CmdCheck(getPlugin(), new String[]{"ipc", "VAR_ARG"},
                        CommandType.VARIABLE));
    }

}
