package org.bitwisemadness.capitalapi.gametest;

import java.util.Optional;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import org.bitwisemadness.capitalapi.CapitalApi;
import org.bitwisemadness.capitalapi.CapitalApiMod;
import org.bitwisemadness.capitalapi.CapitalSpawnEngine;
import org.bitwisemadness.capitalapi.RaceIntegration;
import org.bitwisemadness.capitalapi.ResolvedSpawn;

/** The holder's register/lookup contract, exercised live. */
@GameTestHolder(CapitalApiMod.MOD_ID)
@PrefixGameTestTemplate(false)
public final class CapitalApiGameTests {

    private CapitalApiGameTests() {
    }

    @GameTest(template = "empty")
    public static void mod_loads(GameTestHelper helper) {
        helper.succeed();
    }

    /** Both sides of the contract register and resolve through the holder. */
    @GameTest(template = "empty")
    public static void holder_roundtrips(GameTestHelper helper) {
        RaceIntegration integration = player -> Optional.of(new ResolvedSpawn() {
            @Override
            public ResourceLocation factionTag() {
                return ResourceLocation.parse("capital_api:test_faction");
            }

            @Override
            public ResourceLocation defaultDimension() {
                return ResourceLocation.parse("minecraft:overworld");
            }
        });
        CapitalSpawnEngine engine = player -> Optional.empty();
        CapitalApi.registerIntegration(integration);
        CapitalApi.registerEngine(engine);
        helper.assertTrue(CapitalApi.integration().isPresent(), "integration lookup failed");
        helper.assertTrue(CapitalApi.engine().isPresent(), "engine lookup failed");
        helper.assertTrue(CapitalApi.integration().get()
                .resolveSpawn(null).isPresent(), "registered integration did not resolve");
        helper.assertTrue(CapitalApi.engine().get().relocate(null).isEmpty(), "stub engine should no-op");
        helper.succeed();
    }
}
