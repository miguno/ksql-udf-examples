package com.miguno.ksql.udfdemo.udf;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplyUdfTest {

  @Test
  public void shouldMultiplyNonNullableInts() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    int v1 = 12;
    int v2 = 34;

    // When/Then
    assertThat(m.multiply(v1, v2)).isEqualTo(408);
  }

  @Test
  public void shouldMultiplyNullableIntsWhenNotNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    Integer v1 = 12;
    Integer v2 = 34;

    // When/Then
    assertThat(m.multiply(v1, v2)).isEqualTo(408);
  }


  @Test
  public void shouldMultiplyNullableIntsWhenNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();

    // When/Then
    assertThat(m.multiply(12, null)).isEqualTo(null);
    assertThat(m.multiply(null, 34)).isEqualTo(null);
    assertThat(m.multiply((Integer) null, null)).isEqualTo(null);
  }

  @Test
  public void shouldMultiplyNonNullableBigInts() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    long v1 = 1212121212L;
    long v2 = 3434343434L;

    // When/Then
    assertThat(m.multiply(v1, v2)).isEqualTo(4162840525644322008L);
  }

  @Test
  public void shouldMultiplyNullableBigIntsWhenNotNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    Long v1 = 1212121212L;
    Long v2 = 3434343434L;

    // When/Then
    assertThat(m.multiply(v1, v2)).isEqualTo(4162840525644322008L);
  }

  @Test
  public void shouldMultiplyNullableBigIntsWhenNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();

    // When/Then
    assertThat(m.multiply(1212121212L, null)).isEqualTo(null);
    assertThat(m.multiply(null, 3434343434L)).isEqualTo(null);
    assertThat(m.multiply((Long) null, null)).isEqualTo(null);
  }

  @Test
  public void shouldMultiplyDoubles() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    double v1 = 12.56;
    double v2 = 34.78;

    // When/Then
    assertThat(m.multiply(v1, v2)).isCloseTo(436.8368, Assertions.offset(0.00001d));
  }

  @Test
  public void shouldMultiplyNullableDoublesWhenNotNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();
    Double v1 = 12.56;
    Double v2 = 34.78;

    // When/Then
    assertThat(m.multiply(v1, v2)).isCloseTo(436.8368, Assertions.offset(0.00001d));
  }

  @Test
  public void shouldMultiplyNullableDoublesWhenNull() {
    // Given
    MultiplyUdf m = new MultiplyUdf();

    // When/Then
    assertThat(m.multiply(12.56, null)).isEqualTo(null);
    assertThat(m.multiply(null, 34.78)).isEqualTo(null);
    assertThat(m.multiply((Double) null, null)).isEqualTo(null);
  }

}