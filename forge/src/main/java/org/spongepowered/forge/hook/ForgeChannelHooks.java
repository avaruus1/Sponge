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
package org.spongepowered.forge.hook;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.ForgePayload;
import org.spongepowered.api.network.EngineConnectionSide;
import org.spongepowered.api.network.channel.ChannelBuf;
import org.spongepowered.common.hooks.ChannelHooks;
import org.spongepowered.common.network.channel.SpongeChannelPayload;
import org.spongepowered.common.util.Constants;

import java.util.function.Consumer;

public final class ForgeChannelHooks implements ChannelHooks {

    @Override
    public void registerPlatformChannels(final Consumer<CustomPacketPayload.Type<SpongeChannelPayload>> consumer) {
    }

    @Override
    public Packet<?> createRegisterPayload(final ChannelBuf payload, final EngineConnectionSide<?> side) {
        if (side == EngineConnectionSide.CLIENT) {
            return new ServerboundCustomPayloadPacket(new ForgePayload((ResourceLocation) (Object) Constants.Channels.REGISTER_KEY, null, b -> b.writeBytes((FriendlyByteBuf) payload)));
        } else if (side == EngineConnectionSide.SERVER) {
            return new ClientboundCustomPayloadPacket(new ForgePayload((ResourceLocation) (Object) Constants.Channels.REGISTER_KEY, null, b -> b.writeBytes((FriendlyByteBuf) payload)));
        } else {
            throw new UnsupportedOperationException();
        }
    }
}