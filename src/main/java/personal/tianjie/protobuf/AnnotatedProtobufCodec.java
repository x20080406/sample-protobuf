package personal.tianjie.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotatedProtobufCodec {
	private Class<?> clazz;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AnnotatedProtobufCodec.class);

	public AnnotatedProtobufCodec(Class<?> clazz) {
		this.clazz = clazz;
	}

	public void encode(Object obj, OutputStream stream) throws IOException {
		ProtoEntity entity = (ProtoEntity) obj;
		entity.writeTo(stream);
	}

	public byte[] encode(Object obj) throws IOException {
		ProtoEntity entity = (ProtoEntity) obj;
		return entity.toByteArray();
	}

	public <E> E decode(InputStream stream, int length) throws IOException {
		byte[] buffer = new byte[length];
		stream.read(buffer, 0, length);
		return decode(buffer);
	}

	public <E> E decode(byte[] buffer) throws IOException {
		try {
			Object ret = this.clazz.newInstance();
			return (E) ProtoEntity.parseFrom(ret, buffer);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage());
			return null;
		} catch (InstantiationException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}