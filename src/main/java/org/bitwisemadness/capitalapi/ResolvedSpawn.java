package org.bitwisemadness.capitalapi;

import net.minecraft.resources.ResourceLocation;

/**
 * One resolved race: the faction the race belongs to and the dimension the
 * race itself wants for respawning. The engine combines these with its
 * spawn-rule schema (the rule may override the dimension).
 */
public interface ResolvedSpawn {

    /** The faction's race-group tag id, e.g. {@code tensura_faction_spawn:goblins}. */
    ResourceLocation factionTag();

    /** The race's own default respawn dimension, per the deferred-dimension design. */
    ResourceLocation defaultDimension();
}
