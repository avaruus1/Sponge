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
package org.spongepowered.common.mixin.api.mcp.world.entity.player;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.bridge.entity.PlatformEntityBridge;
import org.spongepowered.common.mixin.api.mcp.world.entity.LivingEntityMixin_API;

@Mixin(net.minecraft.world.entity.player.Player.class)
@Implements(@Interface(iface = Player.class, prefix = "player$"))
public abstract class PlayerMixin_API extends LivingEntityMixin_API {

    // @formatter:off
    @Shadow public AbstractContainerMenu containerMenu;
    @Shadow public float experienceProgress;
    @Shadow public abstract ItemCooldowns shadow$getCooldowns();
    @Shadow public abstract Component shadow$getName();
    // @formatter:on

    @Shadow protected abstract Vec3 maybeBackOffFromEdge(final Vec3 var1, final MoverType var2);

    public final boolean impl$isFake = ((PlatformEntityBridge) (net.minecraft.world.entity.player.Player) (Object) this).bridge$isFakePlayer();

    @Intrinsic
    public String player$getName() {
        return this.shadow$getName().getString();
    }

}