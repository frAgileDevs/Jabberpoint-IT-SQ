# Draft Advice and Recommendations

This document analyzes the JabberPoint codebase, identifying SOLID violations and recommending design patterns to improve maintainability, extensibility, and testability.

---

## Code Quality and Conventions

**Key Issues:**
- **No Package Structure**: All classes reside in the default package, hindering organization and namespace management.
- **Mixed Language Comments**: Code contains both Dutch and English comments; standardize to English.
- **Magic Numbers**: Hardcoded values (e.g., style array size `5`, font sizes, indents) lack named constants.
- **Legacy Java Patterns**: Uses `Vector<T>` instead of `ArrayList<T>`, old-style for-loops, and mutable static state.
- **Inconsistent Access Modifiers**: Protected static constants in some classes, public in others.
- **Empty Catch Blocks**: Several locations silently swallow exceptions without logging or handling.

**Recommendations:** Introduce consistent naming conventions, extract constants to a configuration file, add proper exception handling, and consider adding package structure for future growth.

---

## 1. Menu and Keyboard Controller Commands

**Current Status:** `MenuController` contains inline anonymous `ActionListener` classes with duplicated patterns. `KeyController` uses a large switch statement. Both violate **SRP** (handles construction + execution + I/O + error handling) and **OCP** (adding commands requires modification).

**Recommended Pattern:** Command Pattern (Behavioural)

**Justification:** Encapsulates each action as a standalone object with an `execute()` method. Enables reuse across menu/keyboard, unit testing without GUI, and undo/redo support.

**Alternative Considered:** Strategy Pattern — **rejected** because Strategy is for interchangeable algorithms, while Command is specifically for encapsulating discrete requests with support for queuing and undoing.

---

## 2. SlideItem Creation (TextItem, BitmapItem)

**Current Status:** `XMLAccessor.loadSlideItem()` uses if-else chains to instantiate `SlideItem` types; `saveFile()` uses `instanceof` checks. Violates **OCP** (new types require multiple modifications) and **SRP** (`XMLAccessor` knows too much about concrete implementations).

**Recommended Pattern:** Factory Method Pattern (Creational)

**Justification:** Centralizes creation logic in a factory class. New item types can be added by extending the factory without modifying `XMLAccessor`.

**Alternative Considered:** Abstract Factory Pattern — **rejected** because Abstract Factory creates families of related objects, while JabberPoint creates individual, independent `SlideItem` objects. Factory Method is simpler and sufficient.

---

## 3. Presentation Data Access (Load/Save)

**Current Status:** `JabberPoint` and `MenuController` directly instantiate `XMLAccessor`. `DemoPresentation.saveFile()` throws `IllegalStateException`. Violates **DIP** (hard-coded dependencies) and **LSP** (broken substitutability).

**Recommended Pattern:** Strategy Pattern with Dependency Injection (Behavioural)

**Justification:** High-level modules depend on abstractions. Strategies can be swapped at runtime (XML, JSON, etc.). Separate `Reader`/`Writer` interfaces ensure LSP compliance.

**Alternative Considered:** Template Method Pattern — **rejected** because load/save operations don't share a common algorithmic skeleton. Strategy provides composition over inheritance.

---

## 4. Style Management

**Current Status:** `Style` uses a mutable static array with hardcoded values. Violates **OCP** (changing styles requires modification), has global mutable state (testing issues), and magic numbers.

**Recommended Pattern:** Singleton + Flyweight Pattern (Creational/Structural)

**Justification:** `StyleManager` singleton provides controlled access with proper initialization. Flyweight ensures styles are shared efficiently. Styles can be loaded from external configuration.

**Alternative Considered:** Prototype Pattern — **rejected** because styles should be shared, not cloned. Flyweight explicitly addresses sharing; Prototype encourages duplication.

---

## 5. Presentation-View Communication

**Current Status:** `Presentation` directly calls `SlideViewerComponent.update()`. Violates **SRP** (manages data + notification), **DIP** (model depends on concrete view), and limits extensibility to one observer.

**Recommended Pattern:** Observer Pattern (Behavioural)

**Justification:** Decouples `Presentation` from views. Multiple observers can register (slide viewer, thumbnails, outline). Java's `PropertyChangeSupport` simplifies implementation.

**Alternative Considered:** Mediator Pattern — **rejected** because Mediator handles peer-to-peer communication, while Observer is designed for one-to-many dependencies (subject → observers).

---

## 6. Slide Rendering

**Current Status:** `Slide.draw()` and `SlideItem` subclasses handle rendering, mixing domain objects with rendering logic. A new `TextItem` is created every render call. Violates **SRP** and couples domain to AWT.

**Recommended Pattern:** Visitor Pattern (Behavioural)

**Justification:** Separates rendering from domain model. Different visitors can render to screen, export to PDF, or generate HTML. Domain classes become testable without graphics.

**Alternative Considered:** Decorator Pattern — **rejected** because Decorator adds behaviour to individual objects, while Visitor handles operations across a class hierarchy.

---

## Summary

| Component | SOLID Violation | Pattern | Category | Key Benefit |
|-----------|----------------|---------|----------|-------------|
| Controllers | SRP, OCP | Command | Behavioural | Decoupling; undo/redo |
| SlideItem Creation | OCP, SRP | Factory Method | Creational | Extensible item types |
| Presentation I/O | DIP, LSP | Strategy + DI | Behavioural | Swappable; testable |
| Style Management | OCP | Singleton + Flyweight | Creational/Structural | Configurable; efficient |
| Presentation-View | SRP, DIP | Observer | Behavioural | Multiple observers |
| Slide Rendering | SRP | Visitor | Behavioural | Separates concerns |

**Priority:** (1) Command + Observer (foundation), (2) Factory Method + Strategy (extensibility), (3) Singleton/Flyweight + Visitor (optimization).
