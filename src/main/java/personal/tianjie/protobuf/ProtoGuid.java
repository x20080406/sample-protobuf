package personal.tianjie.protobuf;

import java.io.IOException;

import personal.tianjie.util.Guid;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.WireFormat;

public class ProtoGuid {
	static final byte FieldLo = 1;
	static final byte FieldHi = 2;
	public static final int GuidSize = 20;

	public static Guid deserialize(CodedInputStream input) throws IOException {
		Guid inner = new Guid();

		byte length = input.readRawByte();
		if (length == 0) {
			return null;
		}
		input.readRawByte();
		byte[] data1 = new byte[8];
		for (int i = 0; i < 8; i++) {
			data1[i] = input.readRawByte();
		}
		input.readRawByte();
		byte[] data2 = new byte[8];
		for (int i = 0; i < 8; i++)
			data2[i] = input.readRawByte();
		inner.setData1(data1);
		inner.setData2(data2);
		return inner;
	}

	public static void serialize(Guid value, CodedOutputStream output,
			boolean lengthPrefix) throws IOException {
		if (value == null) {
			if (lengthPrefix) {
				output.writeRawByte(0);

				return;
			}

			return;
		}
		byte[] buffer = value.toByteArray();

		if (lengthPrefix) {
			output.writeRawByte((byte) 18);
		}

		output.writeRawByte(0x8 | WireFormat.FieldType.FIXED64.getWireType());

		for (int i = 0; i < 8; i++)
			output.writeRawByte(buffer[i]);
		output.writeRawByte(0x10 | WireFormat.FieldType.FIXED64.getWireType());
		for (int i = 0; i < 8; i++)
			output.writeRawByte(buffer[(i + 8)]);
	}
}