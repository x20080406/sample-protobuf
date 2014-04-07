package personal.tianjie.util;
import java.util.Hashtable;

public class EnumParser
{
  private static Hashtable<Class, EnumTable> enums = new Hashtable();

  public static Object parse(Class clazz, String enumValue, boolean ignoreCase)
  {
    try
    {
      Integer i = Integer.valueOf(Integer.parseInt(enumValue));
      return parseInt(clazz, i.intValue()); } catch (Exception e) {
    }
    return parseString(clazz, enumValue, ignoreCase);
  }

  public static Object parseInt(Class<?> clazz, int value)
  {
    if (clazz.isEnum()) {
      EnumTable t = (EnumTable)enums.get(clazz);
      if (t == null) {
        t = new EnumTable(clazz);
        enums.put(clazz, t);
      }
      return t.parse(value);
    }
    throw new IllegalArgumentException("Not Enum");
  }

  public static <E extends EnumInteger> E valueOf(Class<E> clazz, int value)
  {
    if (clazz.isEnum()) {
      EnumTable t = (EnumTable)enums.get(clazz);
      if (t == null) {
        t = new EnumTable(clazz);
        enums.put(clazz, t);
      }
      return (E)t.parse(value);
    }
    throw new IllegalArgumentException("Not Enum");
  }

  public static Object parseString(Class clazz, String enumValue, boolean ignoreCase)
  {
    if (clazz.isEnum())
    {
      int n;
      if ((enumValue.startsWith("0x")) || (enumValue.startsWith("0X"))) {
        String hex = enumValue.substring(2);
        if (hex.length() > 8) {
          throw new IllegalArgumentException("Flag HexToLong: " + enumValue);
        }
        n = Integer.parseInt(hex, 16);
        return parseInt(clazz, n);
      }

      if (!ignoreCase) {
        return Enum.valueOf(clazz, enumValue);
      }
      for (Object o : clazz.getEnumConstants()) {
        if (o.toString().equalsIgnoreCase(enumValue))
          return o;
      }
      throw new IllegalArgumentException("Enum does not include Value:" + enumValue);
    }

    throw new IllegalArgumentException("Not Enum");
  }

  public static Flags parseFlags(Class clazz, String enumValue, boolean ignoreCase)
  {
    try
    {
      Integer i = Integer.valueOf(Integer.parseInt(enumValue));
      return new Flags(i.intValue()); } catch (Exception e) {
    }
    return parseFlagsString(clazz, enumValue, ignoreCase);
  }

  public static Flags parseFlagsString(Class clazz, String enumValue, boolean ignoreCase)
  {
    if ((enumValue.startsWith("0x")) || (enumValue.startsWith("0X"))) {
      String hex = enumValue.substring(2);
      if (hex.length() > 8) {
        throw new IllegalArgumentException("Flag HexToLong: " + enumValue);
      }
      int n = Integer.parseInt(hex, 16);
      return new Flags(n);
    }
    Flags flag = new Flags(0);
    for (String s : enumValue.split(",")) {
      try {
        Object e = parse(clazz, s, ignoreCase);
        flag = flag.or((EnumInteger)e);
      } catch (Exception ex) {
        throw new IllegalArgumentException("Flag Parse Failed:" + enumValue, ex);
      }
    }

    return flag;
  }

  private static class EnumTable
  {
    private Hashtable<Integer, Object> values = new Hashtable();

    EnumTable(Class clazz)
    {
      for (Object o : clazz.getEnumConstants())
        if ((o instanceof EnumInteger)) {
          int key = ((EnumInteger)o).intValue();
          this.values.put(Integer.valueOf(key), o);
        }
    }

    public Object parse(int v)
    {
      return this.values.get(Integer.valueOf(v));
    }
  }
}