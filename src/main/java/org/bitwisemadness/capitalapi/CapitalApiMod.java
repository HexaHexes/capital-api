package org.bitwisemadness.capitalapi;

import net.neoforged.fml.common.Mod;

/**
 * Library marker: the jar carries the contract classes only. Both consuming
 * mods register their implementations in their own constructors.
 */
@Mod(CapitalApiMod.MOD_ID)
public final class CapitalApiMod {

    public static final String MOD_ID = "capital_api";

    public CapitalApiMod() {
    }
}
