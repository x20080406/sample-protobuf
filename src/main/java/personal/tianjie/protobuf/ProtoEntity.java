package personal.tianjie.protobuf;

import static java.lang.Boolean.TRUE;
import static personal.tianjie.util.DateUtil.MAX_VALUE;
import static personal.tianjie.util.DateUtil.MIN_VALUE;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import personal.tianjie.util.EnumParser;
import personal.tianjie.util.Flags;
import personal.tianjie.util.GenericsUtils;
import personal.tianjie.util.Guid;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
 
public class ProtoEntity extends GeneratedMessage {
	private static Logger LOG=LoggerFactory .getLogger(ProtoEntity.class); 

	private static ProtoEntity defaultInstance = null;
	private List<Object> objectList;
	private int memoizedSerializedSize = -1;
	private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();
	private static TimeZone timeZone = TimeZone.getDefault();

	private static Descriptors.FileDescriptor descriptor;
	private static Descriptors.Descriptor internal_static_BaseManager_descriptor;
	private static GeneratedMessage.FieldAccessorTable internal_static_BaseManager_fieldAccessorTable;
	
	private static Map<Class<?>, Field[]> fieldsMap = new ConcurrentHashMap<Class<?>, Field[]>();

	public static Field[] getFields(Object obj) {
		Field[] fields = (Field[]) fieldsMap.get(obj.getClass());
		if (fields == null) {
			Class<?> cl = obj.getClass();
			fields = cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, TRUE);
			fieldsMap.put(obj.getClass(), fields);
		}
		return fields;
	}

	public int getSerializedSize() {
		this.memoizedSerializedSize = -1;
		return getSerializedSize(this);
	}

	@Override
	public UnknownFieldSet getUnknownFields() {
		return unknownFields;
	}
	
	public int getSerializedSize(Object obj) {
		int size = this.memoizedSerializedSize;
		if (size != -1)
			return size;
		size = 0;
		for (Field field : getFields(obj)) {
			ProtoMember dm = (ProtoMember) field
					.getAnnotation(ProtoMember.class);
			Object o = null;
			try {
				o = field.get(obj);
				size += getSize(o, dm);
			} catch (IllegalArgumentException e) {
				LOG.error(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				LOG.error(e.getMessage(),e);
			} catch (SecurityException e) {
				LOG.error(e.getMessage(),e);
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
			}
		}

		size += getUnknownFields().getSerializedSize();
		this.memoizedSerializedSize = size;
		return size;
	}

	public int getSize(Object obj, ProtoMember dm) throws Exception {
		int size = 0;
		if ((dm == null) || (obj == null)
				|| ((dm.required()) && (obj.equals(Integer.valueOf(0))))) {
			return size;
		}
		int fieldNumber = dm.value();
		TypeEnum ct = TypeEnum.getCode(obj);
		switch (ct.getCode().intValue()) {
		case 1:
			size = CodedOutputStream.computeStringSize(fieldNumber,
					(String) obj);
			break;
		case 2:
			size = CodedOutputStream.computeInt32Size(fieldNumber,
					((Short) obj).shortValue());
			break;
		case 3:
			size = CodedOutputStream.computeInt32Size(fieldNumber,
					((Integer) obj).intValue());
			break;
		case 4:
			size = CodedOutputStream.computeInt64Size(fieldNumber,
					((Long) obj).longValue());
			break;
		case 5:
			size = CodedOutputStream.computeFloatSize(fieldNumber,
					((Float) obj).floatValue());
			break;
		case 6:
			size = CodedOutputStream.computeDoubleSize(fieldNumber,
					((Double) obj).doubleValue());
			break;
		case 7:
			size = CodedOutputStream.computeInt32Size(fieldNumber, Integer
					.valueOf(((Byte) obj).intValue()).intValue());
			break;
		case 8:
			size = CodedOutputStream.computeInt32Size(fieldNumber,
					((Character) obj).charValue());
			break;
		case 9:
			size = CodedOutputStream.computeBoolSize(fieldNumber,
					((Boolean) obj).booleanValue());
			break;
		case 10:
			size = CodedOutputStream.computeEnumSize(fieldNumber,
					((Enum) obj).ordinal());
			break;
		case 11:
			size = CodedOutputStream.computeMessageSize(fieldNumber,
					(ProtoEntity) obj);
			break;
		case 12:
			for (Iterator localIterator = ((List) obj).iterator(); localIterator
					.hasNext();) {
				Object element = localIterator.next();
				size += getSize(element, dm);
			}
			break;
		case 13:
			break;
		case 14:
			obj = TypeEnum.getDate(obj);
			Date d = (Date)obj;
			size = CodedOutputStream.computeFixed64Size(fieldNumber, d.getTime());
			break;
		case 24:
			size = CodedOutputStream.computeStringSize(fieldNumber,
					(String) obj);
			break;
		case 25:
			size = CodedOutputStream.computeInt32Size(fieldNumber,
					((Integer) obj).intValue());
			break;
		case 26:
			size = CodedOutputStream.computeInt64Size(fieldNumber,
					((Long) obj).longValue());
			break;
		case 27:
			size = CodedOutputStream.computeFloatSize(fieldNumber,
					((Float) obj).floatValue());
			break;
		case 28:
			size = CodedOutputStream.computeDoubleSize(fieldNumber,
					((Double) obj).doubleValue());
			break;
		case 29:
			size = CodedOutputStream.computeInt32Size(fieldNumber, Integer
					.valueOf(((Byte) obj).intValue()).intValue());
			break;
		case 30:
			size = CodedOutputStream.computeInt32Size(fieldNumber,
					((Character) obj).charValue());
			break;
		case 31:
			size = CodedOutputStream.computeBoolSize(fieldNumber,
					((Boolean) obj).booleanValue());
			break;
		case 21:
			byte[] byteArray = new byte[((Byte[]) obj).length];
			for (int i = 0; i < ((Byte[]) obj).length; i++) {
				byteArray[i] = ((Byte[]) obj)[i].byteValue();
			}
			ByteString bs = ByteString.copyFrom(byteArray);
			size = CodedOutputStream.computeBytesSize(fieldNumber, bs);
			break;
		case 37:
			bs = ByteString.copyFrom((byte[]) obj);
			size = CodedOutputStream.computeBytesSize(fieldNumber, bs);
			break;
		case 40:
			size = getSize(Integer.valueOf(((Flags) obj).intValue()), dm);
			break;
		case 42:
			size = 19 + CodedOutputStream.computeTagSize(fieldNumber);
			break;
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 22:
		case 23:
		case 32:
		case 33:
		case 34:
		case 35:
		case 36:
		case 38:
		case 39:
		case 41:
		default:
			for (int i = 0; i < Array.getLength(obj); i++) {
				Object val = Array.get(obj, i);
				size += getSize(val, dm);
			}
		}

		return size;

	}

	public byte[] toByteArray(Object obj) {
		byte[] result = new byte[getSerializedSize(obj)];
		CodedOutputStream output = CodedOutputStream.newInstance(result);
		writeTo(output, obj);
		output.checkNoSpaceLeft();
		return result;
	}
	
	public void writeTo(CodedOutputStream output) {
		writeTo(output, this);
	}

	public void writeTo(CodedOutputStream output, Object obj) {
		Field[] fields = getFields(obj);
		for (Field field : fields) {
			Object innerObj = null;
			try {
				innerObj = field.get(obj);
				ProtoMember dm = (ProtoMember) field
						.getAnnotation(ProtoMember.class);
				if ((dm != null)
						&& (innerObj != null)
						&& ((!dm.required()) || (!innerObj.equals(Integer
								.valueOf(0)))))
					writeTo(output, dm.value(), innerObj);
			} catch (Exception e) {
				LOG.error(e.getMessage(),e);
			} 
		}
		try {
			getUnknownFields().writeTo(output);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}

	public void writeTo(CodedOutputStream output, int fieldNumber,
			Object innerObj) {
		TypeEnum ct = TypeEnum.getCode(innerObj);
		Method method = null;
		try {
			switch (ct.getCode().intValue()) {
			case 1:
				output.writeString(fieldNumber, (String) innerObj);
				break;
			case 2:
				output.writeInt32(fieldNumber, ((Short) innerObj).shortValue());
				break;
			case 3:
				output.writeInt32(fieldNumber, ((Integer) innerObj).intValue());
				break;
			case 4:
				output.writeInt64(fieldNumber, ((Long) innerObj).longValue());
				break;
			case 5:
				output.writeFloat(fieldNumber, ((Float) innerObj).floatValue());
				break;
			case 6:
				output.writeDouble(fieldNumber,
						((Double) innerObj).doubleValue());
				break;
			case 7:
				output.writeInt32(fieldNumber,
						Integer.valueOf(((Byte) innerObj).intValue())
								.intValue());
				break;
			case 8:
				output.writeInt32(fieldNumber,
						((Character) innerObj).charValue());
				break;
			case 9:
				output.writeBool(fieldNumber,
						((Boolean) innerObj).booleanValue());
				break;
			case 10:
				method = innerObj.getClass().getDeclaredMethod("intValue",
						new Class[0]);
				Object ob = method.invoke(innerObj, new Object[0]);
				output.writeEnum(fieldNumber, ((Integer) ob).intValue());
				break;
			case 11:
				output.writeMessage(fieldNumber, (ProtoEntity) innerObj);
				break;
			case 12:
				for (Iterator localIterator = ((List) innerObj).iterator(); localIterator
						.hasNext();) {
					Object element = localIterator.next();
					writeTo(output, fieldNumber, element);
				}
				break;
			case 13:
				break;
			case 14:
				innerObj = TypeEnum.getDate(innerObj);
				method = innerObj.getClass().getDeclaredMethod("getTime",
						new Class[0]);

				Long millisecond = Long.valueOf((((Long) method.invoke(
						innerObj, new Object[0])).longValue() + timeZone
						.getRawOffset()) * 10000L);
				if (millisecond.longValue() >= MAX_VALUE.getTime())
					millisecond = Long.valueOf(MAX_VALUE.getTime());
				else if (millisecond.longValue() < MIN_VALUE.getTime())
					millisecond = Long.valueOf(MIN_VALUE.getTime());
				output.writeFixed64(fieldNumber, millisecond.longValue());
				break;
			case 24:
				output.writeInt32(fieldNumber, ((Short) innerObj).shortValue());
				break;
			case 25:
				output.writeInt32(fieldNumber, ((Integer) innerObj).intValue());
				break;
			case 26:
				output.writeInt64(fieldNumber, ((Long) innerObj).longValue());
				break;
			case 27:
				output.writeFloat(fieldNumber, ((Float) innerObj).floatValue());
				break;
			case 28:
				output.writeDouble(fieldNumber,
						((Double) innerObj).doubleValue());
				break;
			case 29:
				output.writeInt32(fieldNumber,
						Integer.valueOf(((Byte) innerObj).intValue())
								.intValue());
				break;
			case 30:
				output.writeInt32(fieldNumber,
						((Character) innerObj).charValue());
				break;
			case 31:
				output.writeBool(fieldNumber,
						((Boolean) innerObj).booleanValue());
				break;
			case 21:
				byte[] byteArray = new byte[((Byte[]) innerObj).length];
				for (int i = 0; i < ((Byte[]) innerObj).length; i++) {
					byteArray[i] = ((Byte[]) innerObj)[i].byteValue();
				}

				ByteString bs = ByteString.copyFrom(byteArray);
				output.writeBytes(fieldNumber, bs);
				break;
			case 37:
				output.writeBytes(fieldNumber,
						ByteString.copyFrom((byte[]) innerObj));
				break;
			case 40:
				writeTo(output, fieldNumber,
						Integer.valueOf(((Flags) innerObj).intValue()));
				break;
			case 42:
				output.writeTag(fieldNumber, 2);
				ProtoGuid.serialize((Guid) innerObj, output, true);
				break;
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
			case 22:
			case 23:
			case 32:
			case 33:
			case 34:
			case 35:
			case 36:
			case 38:
			case 39:
			case 41:
			default:
				for (int i = 0; i < Array.getLength(innerObj); i++) {
					Object val = Array.get(innerObj, i);
					writeTo(output, fieldNumber, val);
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
		} 
	}	
	
	public static Object parseFrom(Object obj, byte[] data)
			throws InvalidProtocolBufferException, InstantiationException,
			IllegalAccessException {
		return ((Builder) newBuilder(obj).mergeFrom(data)).buildParsed();
	}

	public boolean isInitialized() {
		return true;
	}

	public static Builder newBuilder(Object obj) {
		return Builder.create(obj);
	}

	public Builder newBuilderForType() {
		Builder builder = new Builder();
		return builder;
	}

	protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
		return internal_static_BaseManager_fieldAccessorTable;
	}

	public ProtoEntity getDefaultInstanceForType() {
		return defaultInstance;
	}

	public static final Descriptors.Descriptor getDescriptor() {
		return internal_static_BaseManager_descriptor;
	}

	public static Descriptors.FileDescriptor getFileDescriptor() {
		return descriptor;
	}

	public static final class Builder extends GeneratedMessage.Builder<Builder> {
		private ProtoEntity result = null;

		private Object obj = null;
		private int tag;
		private Map<Integer, List<Object>> tagMap = new HashMap();
		private static final int TAG_TYPE_BITS = 3;

		private static Builder create(Object obj) {
			Builder builder = new Builder();
			builder.obj = obj;
			builder.result = new ProtoEntity();
			return builder;
		}

		public boolean isInitialized() {
			return this.result.isInitialized();
		}

		public ProtoEntity build() {
			if ((this.result != null) && (!isInitialized())) {
				throw newUninitializedMessageException(this.result);
			}
			return buildPartial();
		}

		public ProtoEntity buildPartial() {
			if (this.result == null) {
				throw new IllegalStateException(
						"build() has already been called on this Builder.");
			}
			ProtoEntity returnMe = this.result;
			this.result = null;
			return returnMe;
		}

		private ProtoEntity buildParsed() throws InvalidProtocolBufferException {
			if (!isInitialized()) {
				throw newUninitializedMessageException(this.result)
						.asInvalidProtocolBufferException();
			}
			return buildPartial();
		}

		public Builder mergeFrom(Message other) {
			if ((other instanceof ProtoEntity)) {
				return mergeFrom((ProtoEntity) other);
			}
			super.mergeFrom(other);
			return this;
		}

		public Builder mergeFrom(Object obj) {
			if (obj == this.result.getDefaultInstanceForType()) {
				return this;
			}

			mergeUnknownFields(((ProtoEntity) obj).getUnknownFields());
			return this;
		}

		public Builder mergeFrom(CodedInputStream input,
				ExtensionRegistryLite extensionRegistry) throws IOException {
			UnknownFieldSet.Builder unknownFields = UnknownFieldSet
					.newBuilder(getUnknownFields());
			while (true) {
				int tag = input.readTag();

				if (tag == 0) {
					setUnknownFields(unknownFields.build());
					this.result = ((ProtoEntity) this.obj);
					return this;
				}
				try {
					getFieldValue(tag, input, extensionRegistry);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		private void getFieldValue(int tag, CodedInputStream input,
				ExtensionRegistryLite extensionRegistry) throws IOException,
				SecurityException, NoSuchMethodException,
				IllegalArgumentException, IllegalAccessException,
				InvocationTargetException, InstantiationException,
				ClassNotFoundException {
			Field field = getFieldTagValue(tag, this.obj);
			if (field != null) {
				this.tag = tag;
				String attributeName = field.getName().substring(0, 1)
						.toUpperCase()
						+ field.getName().substring(1);

				String methodName = "set" + attributeName;
				Class fieldType = field.getType();

				Method method = null;
				TypeEnum ct = TypeEnum.getCode(field.getType().getSimpleName());

				if ((ct == null) && (field.get(this.obj) != null)) {
					ct = TypeEnum.getCode(field.get(this.obj));
				} else if (ct == null) {
					Class c = null;
					if (field.getType().getComponentType() == null)
						c = field.getType();
					else {
						c = field.getType().getComponentType();
					}
					try {
						Object o = c.newInstance();
						ct = TypeEnum.getCode(o);
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}
				method = this.obj.getClass().getDeclaredMethod(methodName,
						new Class[] { fieldType });
				Object methodValue = getInputObject(ct, input, field,
						extensionRegistry);

				if (ct.getCode() == TypeEnum.ENUMTYPE.getCode())
					methodValue = EnumParser.parseInt(field.getType(),
							((Integer) methodValue).intValue());
				method.invoke(this.obj, new Object[] { methodValue });
			} else {
				skipTag(tag, input);
			}
		}

		private void skipTag(int tag, CodedInputStream input)
				throws IOException {
			int wireType = tag & 0x7;

			switch (wireType) {
			case 0:
				input.readInt64();
				break;
			case 1:
				input.readFixed64();
				break;
			case 2:
				input.readString();
				break;
			case 5:
				input.readFixed32();
				break;
			case 3:
			case 4:
			default:
				throw new IllegalArgumentException(
						"skip tag error. not implements wiretype:" + wireType);
			}
		}

		public Object getInputObject(TypeEnum ct, CodedInputStream input,
				Field field, ExtensionRegistryLite extensionRegistry)
				throws IOException, InstantiationException,
				IllegalAccessException {
			Object methodValue = null;
			Object[] objectArray = (Object[]) null;
			Class genericsClass = null;
			Builder objBuilder = null;
			switch (ct.getCode().intValue()) {
			case 1:
				methodValue = input.readString();
				break;
			case 2:
				methodValue = input.readString();
				break;
			case 3:
				methodValue = Integer.valueOf(input.readInt32());
				break;
			case 4:
				methodValue = Long.valueOf(input.readInt64());
				break;
			case 5:
				methodValue = Float.valueOf(input.readFloat());
				break;
			case 6:
				methodValue = Double.valueOf(input.readDouble());
				break;
			case 7:
				methodValue = Byte.valueOf((byte) input.readInt32());
				break;
			case 8:
				methodValue = Character.valueOf((char) input.readInt32());
				break;
			case 9:
				methodValue = Boolean.valueOf(input.readBool());
				break;
			case 10:
				methodValue = Integer.valueOf(input.readEnum());
				break;
			case 11:
				genericsClass = GenericsUtils.getFieldGenericType(field);
				if (genericsClass == Object.class) {
					genericsClass = GenericsUtils.getFieldEntityType(field);
				}
				objBuilder = ProtoEntity
						.newBuilder(genericsClass.newInstance());
				input.readMessage(objBuilder, extensionRegistry);
				methodValue = objBuilder.buildParsed();
				break;
			case 12:
				genericsClass = GenericsUtils.getFieldGenericType(field);

				ct = TypeEnum.getCode(genericsClass.getSimpleName());
				if (ct == null) {
					ct = TypeEnum.getCode(genericsClass.getSuperclass()
							.getSimpleName());
				}
				Object oList = getInputObject(ct, input, field,
						extensionRegistry);

				addObjectList(oList, genericsClass);
				methodValue = this.result.objectList;
				break;
			case 13:
				break;
			case 14:
				long l = input.readFixed64();
				if (l > MAX_VALUE.getTime()) {
					l = MAX_VALUE.getTime();
				} else if (l == MIN_VALUE.getTime()) {
					l = MIN_VALUE.getTime();
					l = l / 10000L - ProtoEntity.timeZone.getRawOffset();
				} else {
					l = l / 10000L - ProtoEntity.timeZone.getRawOffset();
				}

				Date d = new Date(l);
				methodValue = d;
				break;
			case 15:
				methodValue = getArrayTypeValue(input.readString(), field);
				break;
			case 16:
				methodValue = getArrayTypeValue(input.readString(), field);
				break;
			case 17:
				methodValue = getArrayTypeValue(
						Integer.valueOf(input.readInt32()), field);
				break;
			case 18:
				methodValue = getArrayTypeValue(
						Long.valueOf(input.readInt64()), field);
				break;
			case 19:
				methodValue = getArrayTypeValue(
						Float.valueOf(input.readFloat()), field);
				break;
			case 20:
				methodValue = getArrayTypeValue(
						Double.valueOf(input.readDouble()), field);
				break;
			case 21:
				byte[] byteArray = input.readBytes().toByteArray();
				Byte[] byteObjectArray = new Byte[byteArray.length];
				for (int i = 0; i < byteArray.length; i++) {
					byteObjectArray[i] = Byte.valueOf(byteArray[i]);
				}
				methodValue = byteObjectArray;
				break;
			case 22:
				methodValue = getArrayTypeValue(
						Character.valueOf((char) input.readInt32()), field);
				break;
			case 23:
				methodValue = getArrayTypeValue(
						Boolean.valueOf(input.readBool()), field);
				break;
			case 24:
				methodValue = Short.valueOf((short) input.readInt32());
				break;
			case 25:
				methodValue = Integer.valueOf(input.readInt32());
				break;
			case 26:
				methodValue = Long.valueOf(input.readInt64());
				break;
			case 27:
				methodValue = Float.valueOf(input.readFloat());
				break;
			case 28:
				methodValue = Double.valueOf(input.readDouble());
				break;
			case 29:
				methodValue = Byte.valueOf((byte) input.readInt32());
				break;
			case 30:
				methodValue = Character.valueOf((char) input.readInt32());
				break;
			case 31:
				methodValue = Boolean.valueOf(input.readBool());
				break;
			case 32:
				objectArray = getArrayPrimitiveValue(input.readString(), field);
				short[] shortArray = new short[objectArray.length];
				for (int i = 0; i < shortArray.length; i++) {
					shortArray[i] = ((Short) objectArray[i]).shortValue();
				}
				methodValue = shortArray;
				break;
			case 33:
				objectArray = getArrayPrimitiveValue(
						Integer.valueOf(input.readInt32()), field);
				int[] intArray = new int[objectArray.length];
				for (int i = 0; i < intArray.length; i++) {
					intArray[i] = ((Integer) objectArray[i]).intValue();
				}
				methodValue = intArray;
				break;
			case 34:
				objectArray = getArrayPrimitiveValue(
						Long.valueOf(input.readInt64()), field);
				long[] longArray = new long[objectArray.length];
				for (int i = 0; i < longArray.length; i++) {
					longArray[i] = ((Long) objectArray[i]).longValue();
				}
				methodValue = longArray;
				break;
			case 35:
				objectArray = getArrayPrimitiveValue(
						Float.valueOf(input.readFloat()), field);
				float[] floatArray = new float[objectArray.length];
				for (int i = 0; i < floatArray.length; i++) {
					floatArray[i] = ((Float) objectArray[i]).floatValue();
				}
				methodValue = floatArray;
				break;
			case 36:
				objectArray = getArrayPrimitiveValue(
						Double.valueOf(input.readDouble()), field);
				double[] doubleArray = new double[objectArray.length];
				for (int i = 0; i < doubleArray.length; i++) {
					doubleArray[i] = ((Double) objectArray[i]).doubleValue();
				}
				methodValue = doubleArray;
				break;
			case 37:
				methodValue = input.readBytes().toByteArray();
				break;
			case 38:
				objectArray = getArrayPrimitiveValue(
						Character.valueOf((char) input.readInt32()), field);
				char[] charArray = new char[objectArray.length];
				for (int i = 0; i < charArray.length; i++) {
					charArray[i] = ((Character) objectArray[i]).charValue();
				}
				methodValue = charArray;
				break;
			case 39:
				objectArray = getArrayPrimitiveValue(
						Boolean.valueOf(input.readBool()), field);
				boolean[] booleanArray = new boolean[objectArray.length];
				for (int i = 0; i < booleanArray.length; i++) {
					booleanArray[i] = ((Boolean) objectArray[i]).booleanValue();
				}
				methodValue = booleanArray;
				break;
			case 40:
				methodValue = Flags.valueOf(input.readInt32());
				break;
			case 42:
				methodValue = ProtoGuid.deserialize(input);
				break;
			case 41:
				long l1 = input.readFixed64();
				if (l1 > MAX_VALUE.getTime()) {
					l1 = MAX_VALUE.getTime();
				} else if (l1 == MIN_VALUE.getTime()) {
					l1 = MIN_VALUE.getTime();
					l1 = l1 / 10000L - ProtoEntity.timeZone.getRawOffset();
				} else {
					l1 = l1 / 10000L - ProtoEntity.timeZone.getRawOffset();
				}

				methodValue = new java.sql.Date(l1);
				break;
			}

			return methodValue;
		}

		private Object[] getArrayPrimitiveValue(Object methodValue, Field field) {
			Class genericsClass = TypeEnum.getPrimitiveClass(field.getType());

			addObjectList(methodValue, genericsClass);

			return this.result.objectList.toArray();
		}

		private Object[] getArrayTypeValue(Object methodValue, Field field) {
			Class genericsClass = TypeEnum.arrayToClassType(field.getType());

			addObjectList(methodValue, genericsClass);

			Object oArray = Array.newInstance(
					TypeEnum.arrayToClassType(genericsClass),
					this.result.objectList.size());

			Object[] object = this.result.objectList.toArray((Object[]) oArray);
			return object;
		}

		private Field getFieldTagValue(int tag, Object obj)
				throws IllegalAccessException, InstantiationException {
			int objDm = 0;
			for (Field field : ProtoEntity.getFields(obj)) {
				ProtoMember dm = (ProtoMember) field
						.getAnnotation(ProtoMember.class);
				if (dm != null) {
					try {
						TypeEnum ct = TypeEnum.getCode(field.getType()
								.getSimpleName());
						Class c = null;

						if (ct == TypeEnum.LISTTYPE) {
							c = GenericsUtils.getFieldGenericType(field);
							ct = TypeEnum.getCode(c.getSimpleName());

							if (ct == null) {
								Class fatherClass = c.getSuperclass();
								ct = TypeEnum.getCode(fatherClass
										.getSimpleName());
							}
						}

						if (ct == null) {
							if ((field.get(obj) != null)
									&& ((field.get(obj) instanceof Enum))) {
								ct = TypeEnum.getCode(field.get(obj));
							} else if ((c == null) && (field.get(obj) == null)) {
								ct = TypeEnum.getCode(field.getType()
										.newInstance());
							} else if (field.get(obj) != null) {
								ct = TypeEnum.getCode(field.get(obj));
							} else {
								Class fatherClass = c.getSuperclass();
								ct = TypeEnum.getCode(fatherClass
										.getSimpleName());
							}

						}

						objDm = getFieldDmCode(ct, dm.value());
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
					if (objDm == tag) {
						return field;
					}
				}
			}
			return null;
		}

		private int getFieldDmCode(TypeEnum ct, int dm) {
			int objDm = 0;
			switch (ct.getCode().intValue()) {
			case 1:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 2:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 3:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 4:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT64.getWireType());
				break;
			case 5:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.FLOAT.getWireType());
				break;
			case 6:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.DOUBLE.getWireType());
				break;
			case 7:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 8:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 9:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BOOL.getWireType());
				break;
			case 10:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.ENUM.getWireType());
				break;
			case 11:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 12:
				break;
			case 13:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 14:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.FIXED64.getWireType());

				break;
			case 15:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 16:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 17:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 18:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT64.getWireType());
				break;
			case 19:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.FLOAT.getWireType());
				break;
			case 20:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.DOUBLE.getWireType());
				break;
			case 21:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BYTES.getWireType());
				break;
			case 22:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 23:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BOOL.getWireType());
				break;
			case 24:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 25:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 26:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT64.getWireType());
				break;
			case 27:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.FLOAT.getWireType());
				break;
			case 28:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.DOUBLE.getWireType());
				break;
			case 29:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 30:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 31:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BOOL.getWireType());
				break;
			case 32:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.STRING.getWireType());
				break;
			case 33:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 34:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT64.getWireType());
				break;
			case 35:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.FLOAT.getWireType());
				break;
			case 36:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.DOUBLE.getWireType());
				break;
			case 37:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BYTES.getWireType());
				break;
			case 38:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 39:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.BOOL.getWireType());
				break;
			case 40:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.INT32.getWireType());
				break;
			case 42:
				objDm = makeFeildTag(dm,
						WireFormat.FieldType.MESSAGE.getWireType());
				break;
			case 41:
			}

			return objDm;
		}

		private Builder addObjectList(Object value, Class<?> genericsClass) {
			this.result.objectList = ((List) this.tagMap.get(Integer
					.valueOf(this.tag)));
			if (this.result.objectList == null) {
				this.result.objectList = new ArrayList();
				this.tagMap.put(Integer.valueOf(this.tag),
						this.result.objectList);
			}

			if (value == null) {
				throw new NullPointerException();
			}

			this.result.objectList.add(value);
			return this;
		}

		private int makeFeildTag(int fieldNumber, int wireType) {
			return fieldNumber << 3 | wireType;
		}

		protected GeneratedMessage internalGetResult() {
			return this.result;
		}

		public ProtoEntity getDefaultInstanceForType() {
			return this.result.getDefaultInstanceForType();
		}

		public Descriptors.Descriptor getDescriptorForType() {
			return ProtoEntity.getDescriptor();
		}

		@Override
		protected FieldAccessorTable internalGetFieldAccessorTable() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public com.google.protobuf.Message.Builder toBuilder() {
		return newBuilder(this);
	}

	@Override
	protected com.google.protobuf.Message.Builder newBuilderForType(
			BuilderParent parent) {
		Builder builder = new Builder();
		return builder;
	}
}
