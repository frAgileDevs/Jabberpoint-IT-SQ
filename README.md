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

| Layer      | Classes                                       |
|------------|-----------------------------------------------|
| Model      | `Presentation`, `Slide`, `SlideItem` subtypes |
| View       | `SlideViewerFrame`, `SlideViewerComponent`    |
| Controller | `MenuController`, `KeyController`             |

### Command Pattern

Each user action is encapsulated as a standalone object implementing the `Command` interface (`execute()`). Both `MenuController` and `KeyController` hold `Command` references, enabling reuse across input methods and straightforward unit testing without the GUI.

Available commands: `NextSlideCommand`, `PrevSlideCommand`, `GoToSlideCommand`, `ExitCommand`, `OpenFileCommand`, `SaveFileCommand`, `NewPresentationCommand`, `AboutCommand`.

### Builder Pattern

`PresentationBuilder` (abstract) defines the steps for assembling a `Presentation`, reducing duplicate code and improving readability for future presentation features. Two concrete builders are provided:

- `DefaultPresentationBuilder` — builds an in-memory presentation (used when loading from XML).
- `XMLPresentationBuilder` — builds an XML string representation (used when saving to file).

### Factory Method Pattern

`DefaultSlideItemFactory` and `DefaultWriterFactory` centralise creation logic for `SlideItem` types. Each maintains an internal registry, so new item types can be added without modifying `XMLAccessor` or any existing class — see [Extending the Application](#extending-the-application).

---

## Building & Running

### Prerequisites

- JDK 21+
- Maven 3.8+

### Compile

```bash
mvn compile
```

### Run

```bash
# Demo mode
java -cp target/classes com.nhlstenden.jabberpoint.JabberPoint

# With an XML file
java -cp target/classes com.nhlstenden.jabberpoint.JabberPoint test.xml
```

### Keyboard Shortcuts

| Key                          | Action         |
|------------------------------|----------------|
| Page Down / Down / Enter / + | Next slide     |
| Page Up / Up / -             | Previous slide |
| Q                            | Quit           |

---

## Extending the Application

### Adding a new SlideItem type

1. Create a class that extends `SlideItem` and implement `getBoundingBox()` and `draw()`.
2. Register it with `DefaultSlideItemFactory` so the XML loader can instantiate it by `kind` name:

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

