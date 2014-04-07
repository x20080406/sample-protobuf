package personal.tianjie.util;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.UUID;

import personal.tianjie.protobuf.ProtoEntity;
import personal.tianjie.protobuf.ProtoMember;
import static org.apache.commons.lang3.StringUtils.isEmpty;

public class Guid extends ProtoEntity {
	private static final String strFormat = "%02x%02x%02x%02x-%02x%02x-%02x%02x-%02x%02x-%02x%02x%02x%02x%02x%02x";
	public static final Guid Empty = fromStr("00000000-0000-0000-0000-000000000000");

	@ProtoMember(1)
	private byte[] data1;

	@ProtoMember(2)
	private byte[] data2;

	public byte[] getData1() {
		return this.data1;
	}

	public void setData1(byte[] d1) {
		this.data1 = d1;
	}

	public byte[] getData2() {
		return this.data2;
	}

	public void setData2(byte[] d2) {
		this.data2 = d2;
	}

	public String toStr() {
		return String.format(
				strFormat,
				new Object[] { Byte.valueOf(this.data1[3]),
						Byte.valueOf(this.data1[2]),
						Byte.valueOf(this.data1[1]),
						Byte.valueOf(this.data1[0]),
						Byte.valueOf(this.data1[5]),
						Byte.valueOf(this.data1[4]),
						Byte.valueOf(this.data1[7]),
						Byte.valueOf(this.data1[6]),
						Byte.valueOf(this.data2[0]),
						Byte.valueOf(this.data2[1]),
						Byte.valueOf(this.data2[2]),
						Byte.valueOf(this.data2[3]),
						Byte.valueOf(this.data2[4]),
						Byte.valueOf(this.data2[5]),
						Byte.valueOf(this.data2[6]),
						Byte.valueOf(this.data2[7]) });
	}

	public byte[] toByteArray() {
		byte[] result = new byte[16];
		for (int i = 0; i < 8; i++)
			result[i] = this.data1[i];
		for (int i = 0; i < 8; i++)
			result[(i + 8)] = this.data2[i];
		return result;
	}

	public static Guid fromByteArray(byte[] bytes) {
		Guid guid = new Guid();
		byte[] d1 = new byte[8];
		byte[] d2 = new byte[8];
		for (int i = 0; i < 8; i++)
			d1[i] = bytes[i];
		for (int i = 0; i < 8; i++)
			d2[i] = bytes[(i + 8)];
		guid.setData1(d1);
		guid.setData2(d2);
		return guid;
	}

	public static Guid randomGuid() {
		Random rand = new Random();
		byte[] bytes = new byte[16];
		rand.nextBytes(bytes);
		return fromByteArray(bytes);
	}

	public static Guid fromStr(String str) {
		if (isEmpty(str))
			return null;
		Guid guid = new Guid();

		UUID u = UUID.fromString(str);
		long l1 = u.getMostSignificantBits();
		byte[] d1 = new byte[8];
		ByteBuffer bb1 = ByteBuffer.wrap(d1);
		bb1.putLong(l1);
		d1 = bb1.array();
		byte[] dd1 = new byte[8];

		for (int i = 0; i < 4; i++)
			dd1[i] = d1[(3 - i)];
		dd1[4] = d1[5];
		dd1[5] = d1[4];
		dd1[6] = d1[7];
		dd1[7] = d1[6];
		guid.setData1(dd1);

		byte[] d2 = new byte[8];
		ByteBuffer bb2 = ByteBuffer.wrap(d2);
		long l2 = u.getLeastSignificantBits();
		bb2.putLong(l2);
		guid.setData2(bb2.array());

		return guid;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Guid))
			return false;
		Guid target = (Guid) obj;
		return (this.data1.equals(target.data1))
				&& (this.data2.equals(target.data2));
	}

	public int hashCode() {
		return this.data1.hashCode() ^ this.data2.hashCode();
	}
}