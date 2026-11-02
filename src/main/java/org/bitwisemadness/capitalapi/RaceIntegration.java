package org.bitwisemadness.capitalapi;

import java.util.Optional;
import net.minecraft.server.level.ServerPlayer;

/**
 * The race-integration side of the contract, implemented by
 * {@code capital-tensura-reincarnated}: everything that reads Tensura's
 * ManasCore race registry and the race-group tags.
 */
public interface RaceIntegration {

    /**
     * Resolves everything the spawn engine needs to know about a player's
     * current race.
     *
     * @param player the player whose race was just set or who is being placed
     * @return the faction tag id and the race's own default respawn dimension,
     *         or empty when the player has no race the integration knows
     */
    Optional<ResolvedSpawn> resolveSpawn(ServerPlayer player);
}
