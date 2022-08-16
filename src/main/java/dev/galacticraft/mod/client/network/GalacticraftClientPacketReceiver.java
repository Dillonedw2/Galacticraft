/*
 * Copyright (c) 2019-2022 Team Galacticraft
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.galacticraft.mod.client.network;

import dev.galacticraft.mod.Constant;
import dev.galacticraft.mod.block.entity.BubbleDistributorBlockEntity;
import dev.galacticraft.mod.client.gui.screen.ingame.CelestialSelectionScreen;
import dev.galacticraft.mod.entity.RocketEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.EntityType;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import java.util.UUID;

/**
 * Handles client-bound (S2C) packets
 * @author <a href="https://github.com/TeamGalacticraft">TeamGalacticraft</a>
 */
@Environment(EnvType.CLIENT)
public class GalacticraftClientPacketReceiver {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(Constant.MOD_ID, "entity_spawn"), (client, handler, buf, responseSender) -> { //todo(marcus): 1.17?
            FriendlyByteBuf buffer = new FriendlyByteBuf(buf.copy());
            client.execute(() -> {
                int id = buffer.readVarInt();
                UUID uuid = buffer.readUUID();
                Entity entity = Registry.ENTITY_TYPE.byId(buffer.readVarInt()).create(Minecraft.getInstance().level);
                entity.setId(id);
                entity.setUUID(uuid);
                entity.setPosRaw(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
                entity.setYRot((float) (buffer.readByte() * 360) / 256.0F);
                entity.setXRot((float) (buffer.readByte() * 360) / 256.0F);
                entity.setDeltaMovement(buffer.readShort(), buffer.readShort(), buffer.readShort());
                Minecraft.getInstance().level.putNonPlayerEntity(id, entity);
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(Constant.MOD_ID, "bubble_size"), (client, handler, buf, responseSender) -> {
            FriendlyByteBuf buffer = new FriendlyByteBuf(buf.copy());
            client.execute(() -> {
                BlockPos pos = buffer.readBlockPos();
                if (client.level.hasChunk(pos.getX() >> 4, pos.getZ() >> 4)) {
                    BlockEntity entity = client.level.getBlockEntity(pos);
                    if (entity instanceof BubbleDistributorBlockEntity machine) {
                        machine.setSize(buffer.readDouble());
                    }
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation(Constant.MOD_ID, "open_screen"), (client, handler, buf, responseSender) -> {

        });

        ClientPlayNetworking.registerGlobalReceiver(new Identifier(Constant.MOD_ID, "planet_menu_open"), (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
            RocketData rocketData = RocketData.fromNbt(packetByteBuf.readNbt());
            minecraftClient.execute(() -> minecraftClient.setScreen(new CelestialSelectionScreen(false, rocketData, true)));
        });

        ClientPlayNetworking.registerGlobalReceiver(new Identifier(Constant.MOD_ID, "rocket_spawn"), ((client, handler, buf, responseSender) -> {
            EntityType<? extends RocketEntity> type = (EntityType<? extends RocketEntity>) Registry.ENTITY_TYPE.get(buf.readVarInt());

            int entityID = buf.readVarInt();
            UUID entityUUID = buf.readUuid();

            double x = buf.readDouble();
            double y = buf.readDouble();
            double z = buf.readDouble();

            float pitch = (buf.readByte() * 360) / 256.0F;
            float yaw = (buf.readByte() * 360) / 256.0F;

            RocketData data = RocketData.fromNbt(buf.readNbt());

            client.execute(() -> {
                RocketEntity entity = type.create(client.world);
                assert entity != null;
                entity.updateTrackedPosition(x, y, z);
                entity.setPos(x, y, z);
                entity.setPitch(pitch);
                entity.setYaw(yaw);
                entity.setId(entityID);
                entity.setUuid(entityUUID);

                entity.setColor(data.color());
                entity.setParts(data.parts());

                MinecraftClient.getInstance().world.addEntity(entityID, entity);
            });
        }));
    }
}
