package com.miguno.ksql.udfdemo.udaf;

import io.confluent.ksql.function.udaf.TableUdaf;
import io.confluent.ksql.function.udaf.UdafDescription;
import io.confluent.ksql.function.udaf.UdafFactory;

/**
 * An example UDAF for TABLE aggregations.
 *
 * Usage in KSQL: `SUM_NULL(col1)`.
 *
 * This SUM variant will treat `null` input values as `0`.
 *
 * Note: The UDAF's name is `SUM_NULL` because there already exist a `SUM` function in KSQL.
 */
@UdafDescription(name = "sum_null", description = "sums numbers, treats null as 0 (zero)")
public class SumUdaf {

  // See https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html#null-handling
  // for more information how your UDAF should handle `null` input.

  @UdafFactory(description = "sums INT numbers")
  public static TableUdaf<Integer, Long> createSumInt() {
    return new TableUdaf<Integer, Long>() {
      @Override
      public Long initialize() {
        return 0L;
      }

      @Override
      public Long undo(final Integer valueToUndo, final Long aggregate) {
        int v = valueToUndo == null? 0: valueToUndo;
        return aggregate - v;
      }

      @Override
      public Long aggregate(final Integer value, final Long aggregate) {
        int v = value == null? 0: value;
        return aggregate + v;
      }

      @Override
      public Long merge(final Long aggOne, final Long aggTwo) {
        return aggOne + aggTwo;
      }
    };
  }

  @UdafFactory(description = "sums BIGINT numbers")
  public static TableUdaf<Long, Long> createSumBigInt() {
    return new TableUdaf<Long, Long>() {
      @Override
      public Long initialize() {
        return 0L;
      }

      @Override
      public Long undo(final Long valueToUndo, final Long aggregate) {
        long v = valueToUndo == null? 0: valueToUndo;
        return aggregate - v;
      }

      @Override
      public Long aggregate(final Long value, final Long aggregate) {
        long v = value == null? 0: value;
        return aggregate + v;
      }

      @Override
      public Long merge(final Long aggOne, final Long aggTwo) {
        return aggOne + aggTwo;
      }
    };
  }

  @UdafFactory(description = "sums DOUBLE numbers")
  public static TableUdaf<Double, Double> createSumDouble() {
    return new TableUdaf<Double, Double>() {
      @Override
      public Double initialize() {
        return 0.0;
      }

      @Override
      public Double undo(final Double valueToUndo, final Double aggregate) {
        double v = valueToUndo == null? 0: valueToUndo;
        return aggregate - v;
      }

      @Override
      public Double aggregate(final Double value, final Double aggregate) {
        double v = value == null? 0: value;
        return aggregate + v;
      }

      @Override
      public Double merge(final Double aggOne, final Double aggTwo) {
        return aggOne + aggTwo;
      }
    };
  }

  /**
   * This method shows providing an initial value to an aggregated, i.e., it would be called
   * with `MY_SUM(column, 'some_initial_value')`.
   */
  @UdafFactory(description = "sums VARCHARs (aka STRINGs) by summing their length")
  public static TableUdaf<String, Long> createSumLengthVarchar(final String initialString) {
    return new TableUdaf<String, Long>() {
      @Override
      public Long initialize() {
        int initialLength = initialString == null? 0: initialString.length();
        return (long) initialLength;
      }

      @Override
      public Long undo(final String valueToUndo, final Long aggregate) {
        int undoLength = valueToUndo == null? 0: valueToUndo.length();
        return aggregate - undoLength;
      }

      @Override
      public Long aggregate(final String s, final Long aggregate) {
        int length = s == null? 0: s.length();
        return aggregate + length;
      }

      @Override
      public Long merge(final Long aggOne, final Long aggTwo) {
        return aggOne + aggTwo;
      }
    };
  }

}