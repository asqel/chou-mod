package fr.asqel.chou.entity;

import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class chair_entity extends Entity {

    public chair_entity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
    }

    @Override
    protected void defineSynchedData(Builder entityData) {
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float damage) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
    }
    
    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (passenger.level() instanceof ServerLevel lv)
            this.kill(lv);
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server) {
            if (this.getPassengers().isEmpty())
                this.kill(server);
        }

        super.tick();
    }
}
