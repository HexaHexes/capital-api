package org.bitwisemadness.capitalapi;

import java.util.Optional;

/**
 * The boundary contract of the capital ecosystem (spec §3.2).
 *
 * <p>Two sides register here and never depend on each other's jars:</p>
 *
 * <ul>
 * <li>the <strong>spawn engine</strong> ({@code tensura_faction_spawn})
 * implements {@link CapitalSpawnEngine} - arrival, placement and the
 * spawn-rule schema;</li>
 * <li>the <strong>race integration</strong>
 * ({@code capital-tensura-reincarnated}) implements {@link RaceIntegration} -
 * everything that reads Tensura's ManasCore race registry and the race-group
 * tags.</li>
 * </ul>
 *
 * <p>Both register in their mod constructors; either side may be absent, and
 * every consumer must treat an empty lookup as "feature quietly off".</p>
 */
public final class CapitalApi {

    private static volatile RaceIntegration integration;
    private static volatile CapitalSpawnEngine engine;

    private CapitalApi() {
    }

    /** Called once by the race integration's mod constructor. */
    public static void registerIntegration(RaceIntegration impl) {
        integration = impl;
    }

    /** Called once by the spawn engine's mod constructor. */
    public static void registerEngine(CapitalSpawnEngine impl) {
        engine = impl;
    }

    public static Optional<RaceIntegration> integration() {
        return Optional.ofNullable(integration);
    }

    public static Optional<CapitalSpawnEngine> engine() {
        return Optional.ofNullable(engine);
    }
}
