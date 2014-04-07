package personal.tianjie.protobuf;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
public @interface ProtoMember
{
	  public abstract int value();

	  public abstract boolean required() default false;

	  public abstract String name() default "";
}