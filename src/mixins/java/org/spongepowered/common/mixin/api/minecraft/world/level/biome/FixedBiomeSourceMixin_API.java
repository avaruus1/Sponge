/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.api.minecraft.world.level.biome;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.biome.provider.ConfigurableBiomeProvider;
import org.spongepowered.api.world.biome.provider.FixedBiomeConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.SpongeCommon;
import org.spongepowered.common.server.BootstrapProperties;

import javax.annotation.Nullable;

@Mixin(FixedBiomeSource.class)
public abstract class FixedBiomeSourceMixin_API extends BiomeSourceMixin_API implements ConfigurableBiomeProvider<FixedBiomeConfig> {

    // @formatter:off
    @Shadow @Final private Holder<Biome> biome;
    // @formatter:on

    @Nullable private FixedBiomeConfig api$config;

    @Override
    public FixedBiomeConfig config() {
        if (this.api$config == null) {
            var biome = this.biome.value();
            final RegistryAccess registryAccess = SpongeCommon.server().registryAccess();
            var biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
            this.api$config = FixedBiomeConfig.of(RegistryTypes.BIOME.referenced((ResourceKey) (Object) biomeRegistry.getKey(biome)));
        }
        return this.api$config;
    }
}
