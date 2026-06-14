# Jabberpoint

A Java-based presentation tool. Part of a refactoring/maintaining exercise at NHL Stenden, done by Alexandros and Nick.

---

## Table of Contents

- [Architecture & Design Patterns](#architecture--design-patterns)
- [Building & Running](#building--running)
- [Extending the Application](#extending-the-application)
- [XML Presentation Format](#xml-presentation-format)

---

## Architecture & Design Patterns

### MVC

| Layer      | Classes                                                          |
|------------|------------------------------------------------------------------|
| Model      | `Presentation`, `Slide`, `SlideItem` subtypes (data only)        |
| View       | `SlideViewerFrame`, `SlideViewerComponent`, `SlideItemRenderer`s |
| Controller | `MenuController`, `KeyController`                                 |

### Single Responsibility: data vs. drawing

A `SlideItem` (`TextItem`, `BitmapItem`) only holds its **data**. Turning that
data into pixels is the job of a matching `SlideItemRenderer`
(`TextItemRenderer`, `BitmapItemRenderer`), looked up through
`DefaultSlideItemRendererFactory`. This keeps each item responsible for one
thing only, and makes the drawing logic independently testable.

### Interface Segregation: loading vs. saving

Reading and writing presentations are split into two interfaces:
`PresentationLoader` (`loadFile`) and `PresentationWriter` (`saveFile`).
`XMLAccessor` implements both; the read-only `DemoPresentation` implements only
`PresentationLoader`, so no implementation is ever forced to provide an
operation it cannot honour (Liskov-substitutable).

### Command Pattern

Each user action is encapsulated as a standalone object implementing the `Command` interface (`execute()`). Both `MenuController` and `KeyController` hold `Command` references, enabling reuse across input methods and straightforward unit testing without the GUI.

Available commands: `NextSlideCommand`, `PrevSlideCommand`, `GoToSlideCommand`, `ExitCommand`, `OpenFileCommand`, `SaveFileCommand`, `NewPresentationCommand`, `AboutCommand`.

### Builder Pattern

`PresentationBuilder` (abstract) defines the steps for assembling a `Presentation`, reducing duplicate code and improving readability for future presentation features. Two concrete builders are provided:

- `DefaultPresentationBuilder` — builds an in-memory presentation (used when loading from XML).
- `XMLPresentationBuilder` — builds an XML string representation (used when saving to file).

### Factory Method Pattern

`DefaultSlideItemFactory`, `DefaultWriterFactory`, and `DefaultSlideItemRendererFactory` centralise the creation, serialisation, and rendering lookups for `SlideItem` types. Each maintains an internal registry, so new item types can be added without modifying `XMLAccessor` or any existing class — see [Extending the Application](#extending-the-application).

---

## Building & Running

### Prerequisites

- JDK 21+
- Maven 3.8+

### Compile

```bash
mvn compile
```

### Test

```bash
mvn test
```

The full JUnit 5 suite runs automatically on every pull request and push (see `.github/workflows/build-and-test.yml`). Because some tests construct Swing/AWT components, the CI runs them under `Xvfb`.

### Run

> **Note:** on startup the application runs the full JUnit suite
> (`JabberPoint.runTests()`), prints `All 142 tests passed.` to the console, and
> then opens the window. It therefore needs the compiled **test** classes and
> JUnit on the classpath. Build with `test-compile` (which compiles the tests
> into `target/classes`) and add the dependencies to the classpath:

```bash
# Compile the app + tests, and capture the dependency classpath
mvn test-compile
mvn dependency:build-classpath -Dmdep.outputFile=cp.txt

# Demo mode
java -cp "target/classes:$(cat cp.txt)" com.nhlstenden.jabberpoint.JabberPoint

# With an XML file
java -cp "target/classes:$(cat cp.txt)" com.nhlstenden.jabberpoint.JabberPoint test.xml
```

Running `java -cp target/classes …` on its own will fail, because `main()`
needs JUnit on the classpath to run the startup tests. (On Windows, use `;`
instead of `:` as the classpath separator.)

### Keyboard Shortcuts

| Key                          | Action         |
|------------------------------|----------------|
| Page Down / Down / Enter / + | Next slide     |
| Page Up / Up / -             | Previous slide |
| Q                            | Quit           |

---

## Extending the Application

### Adding a new SlideItem type

1. Create a class that extends `SlideItem` holding only the item's **data** (no drawing code).
2. Create a renderer that implements `SlideItemRenderer` (`getBoundingBox()` + `draw()`) and register it with `DefaultSlideItemRendererFactory`:

```java
rendererFactory.registerRenderer(VideoItem.class, new VideoItemRenderer());
```

3. Register it with `DefaultSlideItemFactory` so the XML loader can instantiate it by `kind` name:

```java
slideItemFactory.registerItem("video", (level, content) -> new VideoItem(level, content));
```

### Registering a writer for the new type

Register a corresponding writer in `DefaultWriterFactory` so the XML saver knows how to serialise it:

```java
writerFactory.registerWriter(VideoItem.class, (item, level) -> {
    VideoItem v = (VideoItem) item;
    return String.format("<item kind=\"video\" level=\"%d\">%s</item>", level, v.getUrl());
});
```

Both registrations should be performed at application startup, before any file is loaded or saved (e.g. in `JabberPoint.main()` or via dependency injection).

---

## XML Presentation Format

See `jabberpoint.dtd` for the full schema. A minimal example:

```xml
<?xml version="1.0"?>
<!DOCTYPE presentation SYSTEM "jabberpoint.dtd">
<presentation>
  <showtitle>My Presentation</showtitle>
  <slide>
    <title>Slide Title</title>
    <item kind="text"  level="1">Some text</item>
    <item kind="image" level="1">image.jpg</item>
  </slide>
</presentation>
```

Valid `kind` values out of the box: `text`, `image`. Additional kinds can be added via the factory registration described above.

