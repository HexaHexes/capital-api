package org.bitwisemadness.capitalapi;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

/**
 * The spawn-engine side of the contract, implemented by
 * {@code tensura_faction_spawn}: arrival, placement and the spawn-rule schema.
 */
public interface CapitalSpawnEngine {

    /**
     * Runs the full arrival pipeline for a player whose race was just set:
     * rule selection by faction, capital lookup (cached), placement and
     * respawn-point handling.
     *
     * @return the anchor position the player was moved to, or empty when
     *         nothing applied (no rule, no capital, disabled feature)
     */
    Optional<BlockPos> relocate(ServerPlayer player);
}
