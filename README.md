# Task 1 — Marshalling and Unmarshalling

**Covers:** POJO classes, ten dependent Subject objects, JAXB marshalling/unmarshalling, separate `XmlTransformer`, DTD and XSD validation, TCP server/client, JUnit tests.

## Run
1. Open this folder (not the parent archive folder) in IntelliJ.
2. Wait for Maven sync and dependency downloads.
3. Run `com.webservices.Main`. Expected messages: `DTD validation successful`, `XSD validation and unmarshalling successful`, and `Subjects restored: 10`.
4. Run `com.webservices.network.XmlServer`; leave it running.
5. Run `com.webservices.network.XmlClient` in a second run configuration. It receives `received-student.xml`, validates it against XSD, converts it to Student and prints values.

The server handles one connection per run; restart it before another client test.

## Tests and JavaDoc
Maven tool window → Lifecycle → `test`. Run Maven goal `javadoc:javadoc` to generate documentation under `target/site/apidocs`.

All paths above assume the working directory is this Task1 project folder.
