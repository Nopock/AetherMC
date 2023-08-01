package net.aethermc.particles.shapes;

import net.aethermc.particles.ParticleShape;
import net.aethermc.particles.ParticleType;
import net.aethermc.particles.types.DustParticle;
import net.minestom.server.color.Color;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.utils.PacketUtils;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ParticleImage implements ParticleShape {

    private final String imageURL;
    private double scale;
    private int pixelSize;

    public ParticleImage(String imageURL) {
        this(imageURL, 1, 1);
    }

    public ParticleImage(String imageURL, double scale, int pixelSize) {
        this.imageURL = imageURL;
        this.scale = scale;
        this.pixelSize = pixelSize;
    }

    public String getImageURL() {
        return imageURL;
    }

    public double getScale() {
        return scale;
    }

    public int getPixelSize() {
        return pixelSize;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setPixelSize(int pixelSize) {
        this.pixelSize = pixelSize;
    }

    @Override
    public @NotNull CompletableFuture<Void> draw(@NotNull Pos pos, @NotNull ParticleType type, int amount, boolean force, @NotNull Collection<Player> displayPlayers) {
        return CompletableFuture.runAsync(() -> {
            try {
                URL url = new URL(imageURL);
                BufferedImage image = ImageIO.read(url);

                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();

                int loopLimitX = (int) (imageWidth * scale);
                int loopLimitY = (int) (imageHeight * scale);

                for (int x = 0; x < loopLimitX; x++) {
                    for (int y = 0; y < loopLimitY; y++) {
                        int pixelColor = image.getRGB((int) (x * scale), (int) (y * scale));

                        int red = (pixelColor >> 16) & 0xFF;
                        int green = (pixelColor >> 8) & 0xFF;
                        int blue = pixelColor & 0xFF;

                        double locX = x - ((double) loopLimitX / 2) * scale;
                        double locY = y - ((double) loopLimitY / 2) * scale;

                        DustParticle particle = new DustParticle(new Color(red, green, blue));
                        ParticlePacket packet = particle.construct(new Pos(locX, locY, 0));
                        Objects.requireNonNull(packet, "Packet cannot be null");

                        PacketUtils.sendGroupedPacket(displayPlayers, packet);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
