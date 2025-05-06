package org.theenergymashuplab.cts.sbe;

import org.agrona.concurrent.UnsafeBuffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SbeVarStringTest {
    public static void main(String[] args) {
        String input = "Hello SBE!";
        byte[] utf8Bytes = input.getBytes(StandardCharsets.UTF_8);
        int totalLength = 4 + utf8Bytes.length; // 4 bytes for length prefix

        // Create a buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        UnsafeBuffer buffer = new UnsafeBuffer(byteBuffer);

        // --- ENCODE ---
        int offset = 0;
        buffer.putInt(offset, utf8Bytes.length, java.nio.ByteOrder.LITTLE_ENDIAN); // write length
        buffer.putBytes(offset + 4, utf8Bytes); // write string bytes

        // --- DECODE ---
        int readLength = buffer.getInt(offset, java.nio.ByteOrder.LITTLE_ENDIAN);
        byte[] readBytes = new byte[readLength];
        buffer.getBytes(offset + 4, readBytes);

        String decoded = new String(readBytes, StandardCharsets.UTF_8);

        System.out.println("Original: " + input);
        System.out.println("Decoded : " + decoded);
    }
}
