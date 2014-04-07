package personal.tianjie.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import personal.tianjie.util.DateUtil;
import personal.tianjie.util.Guid;

public enum TypeEnum
{
	DEFAULTTYPE( "Object", 0, Object.class), 
	STRINGTYPEB("String", 1, String.class), 
	SHORTTYPE("Short", 2, Short.class), 
	INTEGERTYPE("Integer", 3, Integer.class), 
	LONGTYPE("Long", 4, Long.class), 
	FLOATTYPE("Float", 5, Float.class), 
	DOUBLETYPOE("Double", 6, Double.class), 
	BYTETYPE("Byte", 7, Byte.class), 
	CHARTYPE("Character", 8, Character.class), 
	BOOLEANTYPE("Boolean", 9, Boolean.class), 
	ENUMTYPE("Enum", 10, Enum.class), 
	BASEPROTOBUFTYPE("ProtoEntity", 11, ProtoEntity.class), 
	LISTTYPE("List", 12, null), 
	MAPTYPE("Map", 13, null), 
	DATETYPE("Date", 14, null), 
	
	STRINGARRAYTYPE("String[]", 15, String[].class), 
	SHORTARRAYTYPE("Short[]", 16, Short[].class), 
	INTEGERARRAYTYPE("Integer[]", 17, Integer[].class), 
	LONGARRAYATYPE("Long[]", 18,  Long[].class), 
	FLOAARRAYTTYPE("Float[]", 19, Float[].class), 
	DOUBLEARRAYTYPOE("Double[]", 20, Double[].class), 
	BYTEARRAYTYPE("Byte[]", 21, Byte[].class), 
	CHARARRAYTYPE("Character[]", 22,  Character[].class), 
	BOOLEANARRAYTYPE("Boolean[]", 23, Boolean[].class), 

	SHORT("short", 24, Short.TYPE), 
	INT("int", 25, Integer.TYPE), 
	LONG("long", 26, Long.TYPE), 
	FLOAT("float", 27, Float.TYPE), 
	DOUBLE("double", 28, Double.TYPE), 
	BTYE("byte", 29, Byte.TYPE), 
	CHAR("char", 30, Character.TYPE), 
	BOOLEAN("boolean", 31, Boolean.TYPE), 
	
	SHORTARRAY("short[]", 32, short[].class), 
	INTARRAY("int[]", 33, int[].class), 
	LONGARRAY("long[]", 34, long[].class), 
	FLOATARRAY("float[]", 35, float[].class), 
	DOUBLEARRAY("double[]", 36, double[].class), 
	BYTEARRAY("byte[]", 37, byte[].class), 
	CHARARRAY("char[]", 38, char[].class), 
	BOOLEANARRAY("boolean[]", 39, boolean[].class), 
	FLAGSTYPE("Flags", 40, Integer.class), 
//	SQLDATE("sqlDate", 41, java.sql.Date.class), 
	GUIDTYPE("Guid", 42, Guid.class);

	private String name;
	private Integer code;
	private Class<?> clazz;
	private static Map<String, TypeEnum> typeMap;
	private static Logger LOG = LoggerFactory.getLogger(TypeEnum.class);

	static {
		Map<String,TypeEnum> _typeMap = new HashMap<String, TypeEnum>();
		for (TypeEnum ct : values())
			_typeMap.put(ct.getName(), ct);
		typeMap = Collections.unmodifiableMap(_typeMap);
	}

	private TypeEnum(String name, Integer code, Class<?> clazz) {
		this.name = name;
		this.code = code;
		this.clazz = clazz;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Class<?> getClazz() {
		return this.clazz;
	}

	public void setCla(Class<?> cla) {
		this.clazz = cla;
	}

	public static TypeEnum getCode(String name) {
		return (TypeEnum) typeMap.get(name);
	}

	public static TypeEnum getCode(Object obj) {
		TypeEnum ct = (TypeEnum) typeMap.get(obj.getClass().getSimpleName());
		if (ct == null) {
			if ((obj instanceof List)) {
				ct = LISTTYPE;
			} else if ((obj instanceof Map)) {
				ct = MAPTYPE;
			} else if ((obj instanceof java.util.Date)) {
				ct = DATETYPE;
			} else if ((obj instanceof ProtoEntity)) {
				ct = BASEPROTOBUFTYPE;
			} else if ((obj instanceof Enum)) {
				ct = ENUMTYPE;
			} else if (obj.getClass().isArray()) {
				Class<?> clazz = obj.getClass().getComponentType();
				try {
					getCode(clazz.newInstance());
				} catch (Exception e) {
					LOG.error(e.getMessage(),e);
				} 
			}
		}
		return ct;
	}

	public static Class<?> getPrimitiveClass(Class<?> cla) {
		TypeEnum ct = (TypeEnum) typeMap.get(cla.getSimpleName());
		if (ct == null) {
			if (cla.isArray())
				cla = cla.getComponentType();
			try {
				ct = getCode(cla.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		switch (ct.getCode().intValue()) {
		case 32:
			return SHORTTYPE.getClazz();
		case 33:
			return INTEGERTYPE.getClazz();
		case 34:
			return LONGTYPE.getClazz();
		case 35:
			return FLOATTYPE.getClazz();
		case 36:
			return DOUBLETYPOE.getClazz();
		case 38:
			return CHARTYPE.getClazz();
		case 39:
			return BOOLEANTYPE.getClazz();
		case 41:
			return BASEPROTOBUFTYPE.getClazz();
		case 37:
			return BYTEARRAY.getClazz();
		case 40:
		}
		return cla;
	}

	public static Class<?> arrayToClassType(Class<?> cla) {
		TypeEnum ct = (TypeEnum) typeMap.get(cla.getSimpleName());
		switch (ct.getCode().intValue()) {
		case 15:
			return STRINGTYPEB.getClazz();
		case 16:
			return SHORTTYPE.getClazz();
		case 17:
			return INTEGERTYPE.getClazz();
		case 18:
			return LONGTYPE.getClazz();
		case 19:
			return FLOATTYPE.getClazz();
		case 20:
			return DOUBLETYPOE.getClazz();
		case 22:
			return CHARTYPE.getClazz();
		case 23:
			return BOOLEANTYPE.getClazz();
		case 32:
			return SHORT.getClazz();
		case 33:
			return INT.getClazz();
		case 34:
			return LONG.getClazz();
		case 35:
			return FLOAT.getClazz();
		case 36:
			return DOUBLE.getClazz();
		case 38:
			return CHAR.getClazz();
		case 39:
			return BOOLEAN.getClazz();
		case 41:
			return BASEPROTOBUFTYPE.getClazz();
		case 21:
			return BYTEARRAYTYPE.getClazz();
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
		case 31:
		case 37:
		case 40:
		}
		return cla;
	}

	public static Object getDate(Object obj) {
		if ((obj instanceof java.sql.Date)) {
			try {
				return DateUtil.getUtilDate(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}