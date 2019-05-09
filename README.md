# KSQL UDF/UDAF Examples (Tutorial)

Example implementations of [KSQL](https://github.com/confluentinc/ksql) UDFs and UDAFs
(user-defined functions and user-defined aggregate functions).

[![Build Status](https://travis-ci.org/miguno/ksql-udf-examples.svg?branch=master)](https://travis-ci.org/miguno/ksql-udf-examples)
[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

---

Table of Contents

* <a href="#Requirements">Versions and Requirements</a>
* <a href="#Examples">Examples</a>
* <a href="#Learn">Want to Learn More?</a>
* <a href="#License">License</a>

---

<a name="Requirements"></a>

# Versions and Requirements

Compatible [KSQL](https://github.com/confluentinc/ksql) versions:

* The examples in this repository are built against KSQL 5.2.1 (see [pom.xml](pom.xml#L28)).

Requirements to locally build, test, package the UDF/UDAF examples:

* Java 8+
* Maven 3.0+


<a name="Examples"></a>

# Examples

To write UDFs/UDAFs ([details](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html)), you can use
the following examples as a starting point:

| Example                                  | Type      | Description                                           |
| ---------------------------------------- | --------- | ----------------------------------------------------- |
| [`MULTIPLY(col1, col2)`][1] ([tests][2]) | [UDF][5]  | Multiplies two numbers                                |
| [`SUM_NULL(col1)`][3] ([tests][4])       | [UDAF][5] | Sums values in a colum, treating `null` as `0` (zero) |

[1]: src/main/java/com/miguno/ksql/udfdemo/udf/MultiplyUdf.java
[2]: src/test/java/com/miguno/ksql/udfdemo/udf/MultiplyUdfTest.java
[3]: src/main/java/com/miguno/ksql/udfdemo/udaf/SumUdaf.java
[4]: src/test/java/com/miguno/ksql/udfdemo/udaf/SumUdafTest.java
[5]: https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html

To unit-test the UDFs/UDAFs:

```bash
$ mvn clean test
```

To package the UDFs/UDAFs ([details](https://docs.confluent.io/current/ksql/docs/developer-guide/implement-a-udf.html#build-the-udf-package)):

```bash
# Create a standalone jar ("fat jar")
$ mvn clean package

# >>> Creates target/ksql-udf-demo-1.0-jar-with-dependencies.jar
```

To deploy the packaged UDFs/UDAFs to KSQL servers, refer to the
[KSQL documentation on UDF/UDAF](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html#deploying).
You can verify that the UDFs/UDAFs are available for use by running `SHOW FUNCTIONS`.

To use the UDFs/UDAFs in KSQL ([details]()):

```sql
CREATE STREAM numbers (b1 BIGINT, b2 BIGINT, d1 DOUBLE, d2 DOUBLE)
  WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'numbers');

SELECT MULTIPLY(b1, b2), MULTIPLY(d1, d2) FROM numbers;

SELECT SUM_NULL(b1) FROM numbers;
```


<a name="Learn"></a>

# Want to Learn More?

Head over to the [KSQL documentation](https://docs.confluent.io/current/ksql/).


<a name="License"></a>

# License

See [LICENSE](LICENSE) for licensing information.
