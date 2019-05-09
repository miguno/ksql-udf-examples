package com.miguno.ksql.udfdemo.udaf;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import io.confluent.ksql.function.udaf.TableUdaf;

import static org.assertj.core.api.Assertions.assertThat;

public class SumUdafTest {

  @Test
  public void shouldSumInts() {
    // Given
    TableUdaf<Integer, Long> udaf = SumUdaf.createSumInt();

    // When/Then
    assertThat(udaf.initialize()).isEqualTo(0L);

    assertThat(udaf.undo(4, 10L)).isEqualTo(6L);
    assertThat(udaf.undo(-4, 10L)).isEqualTo(14L);
    assertThat(udaf.undo(null, 10L)).isEqualTo(10L);
    assertThat(udaf.undo(4, 0L)).isEqualTo(-4L);
    assertThat(udaf.undo(0, 0L)).isEqualTo(0L);

    assertThat(udaf.aggregate(4, 10L)).isEqualTo(14L);
    assertThat(udaf.aggregate(4, -10L)).isEqualTo(-6L);
    assertThat(udaf.aggregate(null, 10L)).isEqualTo(10L);
    assertThat(udaf.aggregate(-4, 10L)).isEqualTo(6L);
    assertThat(udaf.aggregate(-4, -10L)).isEqualTo(-14L);

    assertThat(udaf.merge(10L, 30L)).isEqualTo(40L);
    assertThat(udaf.merge(10L, -30L)).isEqualTo(-20L);
    assertThat(udaf.merge(-10L, 30L)).isEqualTo(20L);
    assertThat(udaf.merge(-10L, -30L)).isEqualTo(-40L);
  }

  @Test
  public void shouldSumBigInts() {
    // Given
    TableUdaf<Long, Long> udaf = SumUdaf.createSumBigInt();

    // When/Then
    assertThat(udaf.initialize()).isEqualTo(0L);

    assertThat(udaf.undo(4L, 10L)).isEqualTo(6L);
    assertThat(udaf.undo(-4L, 10L)).isEqualTo(14L);
    assertThat(udaf.undo(null, 10L)).isEqualTo(10L);
    assertThat(udaf.undo(4L, 0L)).isEqualTo(-4L);
    assertThat(udaf.undo(0L, 0L)).isEqualTo(0L);

    assertThat(udaf.aggregate(4L, 10L)).isEqualTo(14L);
    assertThat(udaf.aggregate(4L, -10L)).isEqualTo(-6L);
    assertThat(udaf.aggregate(null, 10L)).isEqualTo(10L);
    assertThat(udaf.aggregate(-4L, 10L)).isEqualTo(6L);
    assertThat(udaf.aggregate(-4L, -10L)).isEqualTo(-14L);

    assertThat(udaf.merge(10L, 30L)).isEqualTo(40L);
    assertThat(udaf.merge(10L, -30L)).isEqualTo(-20L);
    assertThat(udaf.merge(-10L, 30L)).isEqualTo(20L);
    assertThat(udaf.merge(-10L, -30L)).isEqualTo(-40L);
  }

  @Test
  public void shouldSumDoubles() {
    // Given
    TableUdaf<Double, Double> udaf = SumUdaf.createSumDouble();

    // When/Then
    assertThat(udaf.initialize()).isEqualTo(0L);

    assertThat(udaf.undo(4.1, 10.2)).isCloseTo(6.1, Assertions.offset(0.00001d));
    assertThat(udaf.undo(-4.1, 10.2)).isCloseTo(14.3, Assertions.offset(0.00001d));
    assertThat(udaf.undo(null, 10.2)).isCloseTo(10.2, Assertions.offset(0.00001d));
    assertThat(udaf.undo(4.1, 0.0)).isCloseTo(-4.1, Assertions.offset(0.00001d));
    assertThat(udaf.undo(0.0, 0.0)).isCloseTo(0.0, Assertions.offset(0.00001d));

    assertThat(udaf.aggregate(4.1, 10.2)).isCloseTo(14.3, Assertions.offset(0.00001d));
    assertThat(udaf.aggregate(4.1, -10.2)).isCloseTo(-6.1, Assertions.offset(0.00001d));
    assertThat(udaf.aggregate(null, -10.2)).isCloseTo(-10.2, Assertions.offset(0.00001d));
    assertThat(udaf.aggregate(-4.1, 10.2)).isCloseTo(6.1, Assertions.offset(0.00001d));
    assertThat(udaf.aggregate(-4.1, -10.2)).isCloseTo(-14.3, Assertions.offset(0.00001d));

    assertThat(udaf.merge(10.1, 30.2)).isCloseTo(40.3, Assertions.offset(0.00001d));
    assertThat(udaf.merge(10.1, -30.2)).isCloseTo(-20.1, Assertions.offset(0.00001d));
    assertThat(udaf.merge(-10.1, 30.2)).isCloseTo(20.1, Assertions.offset(0.00001d));
    assertThat(udaf.merge(-10.1, -30.2)).isCloseTo(-40.3, Assertions.offset(0.00001d));
  }

  @Test
  public void shouldSumVarchars() {
    // Given/When/Then
    assertThat(SumUdaf.createSumLengthVarchar(null).initialize()).isEqualTo(0L);
    assertThat(SumUdaf.createSumLengthVarchar("").initialize()).isEqualTo(0L);

    // Given
    String initialString = "foo";
    TableUdaf<String, Long> udaf = SumUdaf.createSumLengthVarchar(initialString);

    // When/Then
    assertThat(udaf.initialize()).isEqualTo(initialString.length());

    assertThat(udaf.undo("ksql", 10L)).isEqualTo(6L);
    assertThat(udaf.undo("", 10L)).isEqualTo(10L);
    assertThat(udaf.undo(null, 10L)).isEqualTo(10L);

    assertThat(udaf.aggregate("ksql", 10L)).isEqualTo(14L);
    assertThat(udaf.aggregate("", 10L)).isEqualTo(10L);
    assertThat(udaf.aggregate(null, 10L)).isEqualTo(10L);

    assertThat(udaf.merge(10L, 30L)).isEqualTo(40L);
  }

}