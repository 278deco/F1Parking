package fr.f1parking.core.helpers;

public class MathHelper {
	
	/**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified {@code byte} value.  This function is
     * sometimes referred to as the <i>population count</i>.
     *
     * @param b the value whose bits are to be counted
     * @return the number of one-bits in the two's complement binary
     *     representation of the specified {@code byte} value.
     */
	public static byte activeBitCount(byte b) {
		b = (byte) ((b & 0x55) + ((b >>> 1) & 0x55));
		b = (byte) ((b & 0x33) + ((b >>> 2) & 0x33));
		b = (byte) ((b & 0xf) + ((b >>> 4) & 0xf));
		return b;
	}
	
	public static byte bitCount(byte b) {
		return (byte)(Math.log(b) / Math.log(2) + 1);
	}

}
