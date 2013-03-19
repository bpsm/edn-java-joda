# edn-java-guava

*edn-java-joda* provides instant (`#inst`) parsers that produce
[Joda Time][JT] [instants][JTi] and [DateTimes][JTdt].

[JT]: http://joda-time.sourceforge.net/
[JTi]: http://joda-time.sourceforge.net/api-release/org/joda/time/Instant.html
[JTdt]: http://joda-time.sourceforge.net/api-release/org/joda/time/DateTime.html

## Installation

This is a Maven project with the following coordinates:

```xml
<dependency>
  <groupId>us.bpsm</groupId>
  <artifactId>edn-java-joda</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

It depends on [edn-java][EJ] and [joda-time][JT]

[EJ]: http://github.com/bpsm/edn-java

## Usage

See [RoundTripTest.java][RTT].

[RTT]: https://github.com/bpsm/edn-java-joda/blob/master/src/test/java/us/bpsm/edn/joda/RoundTripTest.java
