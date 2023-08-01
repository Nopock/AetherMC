package net.aethermc.particles;

import net.aethermc.Aether;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.particle.Particle;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParticleDisplay {

    private List<ParticleShape> shapes;
    private ParticleType type;
    private int amount;
    private boolean force;

    public ParticleDisplay(List<ParticleShape> shapes, ParticleType type, int amount, boolean force) {
        this.shapes = shapes;
        this.type = type;
        this.amount = amount;
        this.force = force;
    }

    public ParticleDisplay(ParticleType type, int amount, boolean force) {
        this.shapes = new ArrayList<>();
        this.type = type;
        this.amount = amount;
        this.force = force;
    }

    public List<ParticleShape> getShapes() {
        return shapes;
    }

    public ParticleType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isForce() {
        return force;
    }

    public void setShapes(List<ParticleShape> shapes) {
        this.shapes = shapes;
    }

    public void setType(ParticleType type) {
        this.type = type;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public ParticleShape getShape(int index) {
        return shapes.get(index);
    }

    public ParticleShape getShape(ParticleShape shape) {
        return shapes.get(shapes.indexOf(shape));
    }

    public void show() {
        show(new Pos(0, 0, 0), Aether.getAllPlayers());
    }

    public void show(@NotNull Pos pos) {
        show(pos, Aether.getAllPlayers());
    }

    public void show(@NotNull Pos pos, @NotNull Collection<Player> players) {
        for (ParticleShape shape : shapes) {
            shape.draw(pos, type, amount, force, players).exceptionally(e -> {
                e.fillInStackTrace();
                return null;
            });
        }
    }
}
