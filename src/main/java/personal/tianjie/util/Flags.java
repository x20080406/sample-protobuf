package personal.tianjie.util;
public class Flags<E extends EnumInteger>
{
  private int value;

  public Flags(int value)
  {
    this.value = value;
  }

  public Flags(E value)
  {
    this.value = value.intValue();
  }

  public Flags<E> or(E rval)
  {
    this.value |= rval.intValue();
    return this;
  }

  public Flags<E> or(Flags<E> rval)
  {
    this.value |= rval.value;
    return this;
  }

  public Flags<E> and(Flags<E> rval)
  {
    this.value &= rval.value;
    return this;
  }

  public Flags<E> and(E rval)
  {
    this.value &= rval.intValue();
    return this;
  }

  public Flags<E> xor(E rval)
  {
    this.value ^= rval.intValue();
    return this;
  }

  public boolean has(E rval)
  {
    return (this.value & rval.intValue()) > 0;
  }

  public void setFlag(E mask, boolean bool)
  {
    this.value = (this.value ^ this.value & mask.intValue() | (bool ? mask.intValue() : 0));
  }

  public void setFlags(E mask, int value)
  {
    int order = getMaskOrder(mask.intValue());
    this.value = (this.value ^ this.value & mask.intValue() | value << order);
  }

  public boolean getFlag(E mask)
  {
    return (this.value & mask.intValue()) > 0;
  }

  public int getFlags(E mask)
  {
    int order = getMaskOrder(mask.intValue());
    return (this.value & mask.intValue()) >> order;
  }

  public int extract(E mask)
  {
    return (this.value & mask.intValue()) >> getMaskOrder(mask);
  }

  public static <E extends EnumInteger> int getMaskOrder(E e)
  {
    int mask = e.intValue();
    int n = 0;
    while (mask > 0) {
      if ((mask & 0x1) > 0)
        return n;
      n++;
      mask >>= 1;
    }
    return n;
  }

  public static <E extends EnumInteger> Flags<E> valueOf(int value)
  {
    return new Flags(value);
  }

  public static <E extends EnumInteger> Flags<E> of(E first, E[] last)
  {
    int a = first.intValue();
    for (EnumInteger e : last) {
      a |= e.intValue();
    }
    return new Flags(a);
  }

  @Deprecated
  public int value()
  {
    return this.value;
  }

  public int intValue()
  {
    return this.value;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!(obj instanceof Flags))
      return false;
    Flags target = (Flags)obj;
    return this.value == target.value;
  }

  public int hashCode()
  {
    return super.hashCode();
  }

  public static int getMaskOrder(int mask)
  {
    int n = 0;
    while (mask > 0) {
      if ((mask & 0x1) > 0)
        return n;
      n++;
      mask >>= 1;
    }
    throw new RuntimeException("Holyshit!!!");
  }
}