package net.easysmarthouse.satellite.integration.util;

import java.nio.ByteBuffer;

public class BytesUtil {

    private BytesUtil() {
    }

    public static byte[] toByteArray(boolean bool) {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (bool ? 1 : 0);
        return bytes;
    }

    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

}
