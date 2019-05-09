package com.miguno.ksql.udfdemo.udf;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;

/**
 * An example UDF that operates on two scalars.
 *
 * Usage in KSQL: `MULTIPLY(col1, col2)`.
 */
@UdfDescription(name = "multiply", description = "multiplies 2 numbers")
public class MultiplyUdf {

  // See https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html#null-handling
  // for more information how your UDF should handle `null` input.

  @Udf(description = "multiply two non-nullable INTs.")
  public long multiply(final int v1, final int v2) {
    return v1 * v2;
  }

  @Udf(description = "multiply two nullable INTs. If either param is null, null is returned.")
  public Integer multiply(final Integer v1, final Integer v2) {
    return v1 == null || v2 == null ? null : v1 * v2;
  }

  @Udf(description = "multiply two non-nullable BIGINTs.")
  public long multiply(final long v1, final long v2) {
    return v1 * v2;
  }

  @Udf(description = "multiply two nullable BIGINTs. If either param is null, null is returned.")
  public Long multiply(final Long v1, final Long v2) {
    return v1 == null || v2 == null ? null : v1 * v2;
  }

  @Udf(description = "multiply two non-nullable DOUBLEs.")
  public double multiply(final double v1, double v2) {
    return v1 * v2;
  }

  @Udf(description = "multiply two nullable DOUBLEs. If either param is null, null is returned.")
  public Double multiply(final Double v1, final Double v2) {
    return v1 == null || v2 == null ? null : v1 * v2;
  }

}
