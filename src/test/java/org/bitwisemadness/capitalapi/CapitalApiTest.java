package org.bitwisemadness.capitalapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/** Holder semantics: registration, replacement and the absent-state contract. */
class CapitalApiTest {

    @AfterEach
    void clear() {
        CapitalApi.registerIntegration(null);
        CapitalApi.registerEngine(null);
    }

    @Test
    void absentByDefault() {
        assertTrue(CapitalApi.integration().isEmpty());
        assertTrue(CapitalApi.engine().isEmpty());
    }

    @Test
    void registrationResolves() {
        RaceIntegration integration = player -> Optional.empty();
        CapitalApi.registerIntegration(integration);
        assertFalse(CapitalApi.integration().isEmpty());
        assertSame(integration, CapitalApi.integration().get());
    }

    @Test
    void registrationIsReplaceable() {
        RaceIntegration first = player -> Optional.empty();
        RaceIntegration second = player -> Optional.empty();
        CapitalApi.registerIntegration(first);
        CapitalApi.registerIntegration(second);
        assertSame(second, CapitalApi.integration().get());
    }
}
