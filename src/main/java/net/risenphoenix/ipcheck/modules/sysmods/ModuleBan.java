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
import net.risenphoenix.ipcheck.commands.ban.*;
import net.risenphoenix.ipcheck.modules.Module;
import net.risenphoenix.ipcheck.modules.ModuleBase;

public class ModuleBan extends ModuleBase {

    public ModuleBan(final IPCheck plugin) {
        super(plugin);
        setCallHandle("BANCTRSYS");
        setModuleType(Module.BANCTRSYS);
    }

    @Override
    public void registerCommands(CommandStore store) {
        // Ban Command
        store.add(
                new CmdBan(getPlugin(), new String[]{"ipc", "ban", "VAR_ARG"},
                        CommandType.DYNAMIC));

        // Ban-All Command
        store.add(
                new CmdBanAll(getPlugin(), new String[]{"ipc", "banall",
                        "VAR_ARG", "VAR_ARG"}, CommandType.DYNAMIC));

        // SBan Command
        store.add(
                new CmdSBan(getPlugin(), new String[]{"ipc", "sban", "VAR_ARG"},
                        CommandType.DYNAMIC));

        // Unban Command
        store.add(
                new CmdUnban(getPlugin(), new String[]{"ipc", "unban",
                        "VAR_ARG"}, CommandType.VARIABLE));

        // Unban-All Command
        store.add(
                new CmdUnbanAll(getPlugin(), new String[]{"ipc", "unbanall",
                        "VAR_ARG", "VAR_ARG"}, CommandType.VARIABLE));
    }

}