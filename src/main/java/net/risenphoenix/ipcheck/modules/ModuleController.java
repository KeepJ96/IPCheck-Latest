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
import net.risenphoenix.ipcheck.IPCheck;
import net.risenphoenix.ipcheck.modules.sysmods.*;
import net.risenphoenix.ipcheck.stores.ModuleStore;
import net.risenphoenix.ipcheck.util.ListFormatter;

import java.util.ArrayList;
import java.util.logging.Level;

public class ModuleController {

    private final IPCheck plugin;
    private final ConfigurationManager config;

    private ArrayList<ModuleBase> modules;

    public ModuleController(final IPCheck plugin) {
        this.plugin = plugin;
        this.config = new ConfigurationManager(plugin, "module-control");
        this.config.initializeConfiguration(new ModuleStore(plugin)
                .getOptions());

        // Initialize System Components
        initializeModules();
        reportLoad();
    }

    protected final void initializeModules() {
        this.modules = new ArrayList<>();

        // Active Mode Module
        if (config.getBoolean("ACTIVESYS") && config.getBoolean("BANCTRSYS")) {
            modules.add(new ModuleActive(plugin));
        }

        // Ban Control System
        if (config.getBoolean("BANCTRSYS")) {
            modules.add(new ModuleBan(plugin));
        }

        // Ban Messages System
        if (config.getBoolean("BANMSGSYS") && config.getBoolean("BANCTRSYS")) {
            modules.add(new ModuleBanMsg(plugin));
        }

        // Country Block System
        if (config.getBoolean("CNTBLKSYS") && config.getBoolean("GEOIPRSYS")) {
            modules.add(new ModuleBlock(plugin));
        }

        // Exemption System
        if (config.getBoolean("EXEMPTSYS")) {
            modules.add(new ModuleExempt(plugin));
        }

        // GeoIP Control System
        if (config.getBoolean("GEOIPRSYS")) {
            modules.add(new ModuleGeoIP(plugin));
        }

        // Kick System
        if (config.getBoolean("KCKSYSTEM")) {
            modules.add(new ModuleKick(plugin));
        }

        // If the plugin is in Development Mode, do not initialize Metrics or
        // the plugin updater
        if (!plugin.isDevBuild()) {
            // Metrics Monitoring System
            if (config.getBoolean("METRICSYS")) {
                modules.add(new ModuleMetrics(plugin));
            }

            // Update System
            if (config.getBoolean("UPDATESYS")) {
                modules.add(new ModuleUpdate(plugin));
            }
        } else {
            plugin.sendConsoleMessage(Level.INFO, plugin
                    .getLocalizationManager()
                    .getLocalString("DEV_BUILD_WARN"));
        }

        // Notification System
        if (config.getBoolean("NOTIFYSYS")) {
            modules.add(new ModuleNotify(plugin));
        }

        // Protection System
        if (config.getBoolean("PROTCTSYS")) {
            modules.add(new ModuleProtect(plugin));
        }

        // Secure Kick System
        if (config.getBoolean("SECBANSYS") && config.getBoolean("SECURESYS") &&
                config.getBoolean("BANCTRSYS")) {
            modules.add(new ModuleSecureBan(plugin));
        }

        // Secure Mode System
        if (config.getBoolean("SECURESYS")) {
            modules.add(new ModuleSecure(plugin));
        }

        // System Core (ALTACTSYS)
        modules.add(new ModuleCore(plugin));
    }

    private void reportLoad() {
        ArrayList<String> loadedMods = new ArrayList<>();

        for (ModuleBase m : getModules()) {
            loadedMods.add(m.getCallHandle());
        }

        // Create Formatted List of Loaded Modules
        StringBuilder out = new ListFormatter(loadedMods).getFormattedList();

        // Report Loaded Modules to Console
        plugin.sendConsoleMessage(Level.INFO, plugin.getLocalizationManager()
                .getLocalString("MODULE_LOADOUT") + out.toString());
    }

    public final ModuleBase getModule(Module module) {
        ModuleBase mod = null;

        for (ModuleBase m : modules) {
            if (m.getModuleType().equals(module)) {
                mod = m;
                break;
            }
        }

        return mod;
    }

    public final ArrayList<ModuleBase> getModules() {
        return this.modules;
    }
}

