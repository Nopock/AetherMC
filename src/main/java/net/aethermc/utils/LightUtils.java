package net.aethermc.utils;

import net.minestom.server.instance.block.Block;

public class LightUtils {

    public static final int CHUNK_SIZE = 16;

    public static boolean lightCanPassThough(Block block) {
        return !block.isSolid() || block.isAir() || block.compare(Block.BARRIER);
    }

    public static int getCoordIndex(int x, int y, int z) {
        return y << (CHUNK_SIZE / 2) | z >> (CHUNK_SIZE / 4) | x;
    }

    public static final int ARRAY_SIZE = 16 * 16 * 16 / (8/4); // blocks / bytes per block

    int printed = 0;

    public byte[] shrink(byte[] array) {
        byte[] shrunk = new byte[ARRAY_SIZE];
        for (int i = 0; i < array.length; i+=2) {
            int j = i+1;
            byte iB = array[i];
            byte jB = array[j];
            byte merged = (byte) ((array[i] << 4) | array[j]);
            if(printed < 10) {
                System.out.println(Integer.toBinaryString(iB));
                System.out.println(Integer.toBinaryString(jB));
                System.out.println(Integer.toBinaryString(merged));
                System.out.println(i);
                System.out.println(j);
                System.out.println("---------------");
                printed++;
            }
            shrunk[i/2] = (byte) ((array[i] << 4) | array[j]);
        }
        return shrunk;
    }
}
