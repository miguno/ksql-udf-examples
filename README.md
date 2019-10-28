# KSQL UDF/UDAF Examples (Tutorial)

Example implementations of
[UDFs](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html) (user-defined functions, like `FLOOR`, `MASK`) and
[UDAFs](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html) (user-defined aggregate functions, like `SUM`, `COUNT`)
for [KSQL](https://github.com/confluentinc/ksql).

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

* The examples in this repository are built against KSQL 5.3.1 (see [pom.xml](pom.xml#L28)) for Apache Kafka 2.3.1
  and Confluent Platform 5.3.1.

Requirements to locally build, test, package the UDF/UDAF examples:

* Java 8+
* Maven 3.0+


<a name="Examples"></a>

# Examples

To write UDFs/UDAFs ([details](https://docs.confluent.io/current/ksql/docs/developer-guide/udf.html)), you can use
the following examples as a starting point. Simply [fork](https://github.com/miguno/ksql-udf-examples/fork) this
repository and add or modify the examples as you see fit. Of course, you can also contribute examples to this
repository by sending a [pull request](https://github.com/miguno/ksql-udf-examples/pulls).

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
You can verify that the UDFs/UDAFs are available for use by running `SHOW FUNCTIONS`, and show the details of
any specific function with `DESCRIBE FUNCTION <name>`.

To use the UDFs/UDAFs in KSQL ([details]()):

```sql
CREATE STREAM numbers (b1 BIGINT, b2 BIGINT, d1 DOUBLE, d2 DOUBLE, v VARCHAR)
  WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'numbers');

SELECT MULTIPLY(b1, b2), MULTIPLY(d1, d2) FROM numbers;

SELECT SUM_NULL(b1), SUM_NULL(d1), SUM_NULL(v) FROM numbers;
```


<a name="Learn"></a>

# Want to Learn More?

* Head over to the [KSQL documentation](https://docs.confluent.io/current/ksql/).
* [Mitch Seymour](http://blog.mitchseymour.com/)'s talk [The Exciting Frontier of Custom KSQL Functions](https://kafka-summit.org/sessions/exciting-frontier-custom-ksql-functions/) ([slides](http://blog.mitchseymour.com/presentations/kafka-summit-london-2019/slides/#/)), Kafka Summit London 2019, which includes UDF usage for machine learning as well as a POC for writing UDFs in non-JVM languages like Ruby, Python


<a name="License"></a>

# License

See [LICENSE](LICENSE) for licensing information.
