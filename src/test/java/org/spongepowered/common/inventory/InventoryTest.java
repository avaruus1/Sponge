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
package org.spongepowered.common.inventory;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.metadata.PluginMetadata;

public final class InventoryTest {

    @Test
    void testUnion() {
        final PluginContainer plugin = Mockito.mock(PluginContainer.class);
        final PluginMetadata metadata = Mockito.mock(PluginMetadata.class);
        when(plugin.metadata()).thenReturn(metadata);
        when(metadata.id()).thenReturn("test");

        final var inv = Inventory.builder()
            .slots(1)
            .completeStructure()
            .plugin(plugin)
            .build();

        final var other = Inventory.builder()
            .slots(1)
            .completeStructure()
            .plugin(plugin)
            .build();

        final var combined = inv.union(other);

        Assertions.assertEquals(InventoryTransactionResult.Type.SUCCESS, combined.offer(ItemStack.of(ItemTypes.DIRT)).type());
        Assertions.assertEquals(InventoryTransactionResult.Type.SUCCESS, combined.offer(ItemStack.of(ItemTypes.STONE)).type());
    }

}
