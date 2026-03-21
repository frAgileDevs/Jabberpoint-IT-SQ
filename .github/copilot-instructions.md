# Copilot Instructions for Jabberpoint-IT-SQ

## Project Overview

This is **Jabberpoint**, a Java-based presentation software similar to PowerPoint. It's a legacy educational project (originally from 1996-2014) used for teaching software quality and refactoring at NHL Stenden/Open University.

**Tech Stack:**

- Java (compatible with Java 8+, currently using Java 25)
- Swing/AWT for GUI
- XML for presentation storage
- No external dependencies (only JDK standard library)

**Architecture:**

- MVC-style pattern (Controllers, Views, Models)
- `Presentation` - manages slides and navigation
- `Slide` - contains slide items
- `SlideItem` (abstract) → `TextItem`, `BitmapItem`
- Controllers: `MenuController`, `KeyController`
- Views: `SlideViewerFrame`, `SlideViewerComponent`
- Accessors: `XMLAccessor`, `DemoPresentation`

## Code Style & Conventions

### Java Conventions

- **Class naming**: PascalCase (e.g., `SlideViewerComponent`)
- **Method naming**: camelCase (e.g., `getSlideNumber()`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `JABVERSION`, `TESTFILE`)
- **Access modifiers**: Use `protected` for constants that might be used by subclasses
- **Indentation**: Tabs (not spaces) - maintain consistency with existing code

### Legacy Patterns to Maintain

- Some classes use `Vector<T>` instead of `ArrayList<T>` - **keep this for consistency** in existing code
- Protected static final constants for error messages and file paths
- Inline variable declarations in loops (old Java style)
- Abstract factory pattern for Accessors (`Accessor.getDemoAccessor()`)

### Comment Style

- Use JavaDoc for all public classes and methods
- Include `@author` and `@version` tags in class headers
- Comments should be in **English** (the codebase is transitioning from Dutch)
- Update version history when making significant changes

## File Organization

```
src/               # All Java source files (no packages - default package)
bin/               # Compiled .class files (gitignored)
.github/workflows/ # CI/CD configuration
test.xml           # Sample presentation file
dump.xml           # Runtime-generated save file (gitignored)
```

**No package structure** - all classes are in the default package. Maintain this structure.

## Development Workflow

### Branch Strategy

- `main` - stable releases
- `develop` - active development branch
- `production` - production-ready code
- Feature branches: descriptive names (e.g., `workflow-yaml-init`, `add-unit-tests`)

### Pull Request Requirements

- All changes must go through PRs to `develop` or `production`
- CI must pass (build + smoke tests)
- PRs should be reviewed before merging
- Keep commits atomic and meaningful

### CI/CD Pipeline

- GitHub Actions workflow: `.github/workflows/build-and-test.yml`
- Triggers on PRs to `develop` and `production`
- Runs: compilation check, smoke tests (demo mode, XML loading)
- Uses Java 25 with Temurin distribution
- **No unit tests yet** - smoke testing only

## When Adding New Features

### Creating New SlideItem Types

1. Extend `SlideItem` abstract class
2. Implement `getBoundingBox()` and `draw()` methods
3. Add XML loading logic to `XMLAccessor.loadSlideItem()`
4. Add XML saving logic to `XMLAccessor.saveFile()`
5. Define constants for XML tag names

### Adding New Menu Items

1. Add menu item in `MenuController` constructor
2. Use `mkMenuItem()` helper method
3. Add ActionListener with presentation method calls
4. Add corresponding constants for labels

### Adding New Keyboard Shortcuts

1. Update `KeyController.keyPressed()` switch statement
2. Map to `presentation.nextSlide()`, `presentation.prevSlide()`, etc.
3. Document shortcuts in demo presentation

## Testing Guidelines

### Current State

- **No unit testing framework** yet (consider adding JUnit in future)
- Smoke testing via GitHub Actions
- Manual testing required for GUI changes

### When Testing

1. Compile: `javac -d bin src/*.java`
2. Run demo: `java -cp bin JabberPoint`
3. Run with XML: `java -cp bin JabberPoint test.xml`
4. Test keyboard navigation (PgUp/PgDn, arrows, q)
5. Test menu operations (Open, Save, New)

### Test Coverage Goals

- Every class should eventually have unit tests
- Focus on: `Presentation`, `Slide`, `XMLAccessor` first
- GUI components are lower priority for unit testing

## Common Tasks

### Compiling the Project

```bash
mkdir -p bin
javac -d bin src/*.java
```

### Running the Application

```bash
# Demo mode
java -cp bin JabberPoint

# With XML file
java -cp bin JabberPoint test.xml
```

### Creating a New Presentation File

1. Follow the DTD structure in `jabberpoint.dtd`
2. Include `<showtitle>`, `<slide>`, `<title>`, `<item>` elements
3. Item attributes: `kind` (text/image), `level` (1-4)
4. See `test.xml` as reference

## What to AVOID

**Don't introduce external dependencies** without discussion (keep it JDK-only)
**Don't break backwards compatibility** with existing XML files
**Don't use modern Java syntax** that requires Java 11+ unless approved (e.g., var, records, pattern matching)
**Don't create packages** - maintain flat structure in src/
**Don't commit** `bin/`, `dump.xml`, `.idea/`, `*.iml` files
**Don't change existing class APIs** without refactoring all usages
**Don't mix Dutch and English** - use English for all new code/comments

## Refactoring Opportunities (For Future)

### Code Smells to Address

1. **God classes**: `MenuController` has too many responsibilities
2. **Magic numbers**: Style sizes hardcoded in `Style.createStyles()`
3. **Vector usage**: Replace with `ArrayList` in new code
4. **Static state**: `Style.styles` is mutable static array
5. **No interfaces**: Consider `Presentation` interface for testing
6. **Exception handling**: Many empty catch blocks need proper error handling
7. **Tight coupling**: GUI components directly reference models

### Suggested Improvements

- Add dependency injection for better testability
- Extract constants to configuration files
- Implement proper error reporting/logging
- Add builder pattern for Slide/Presentation construction
- Consider adding a proper MVC framework layer

## AI Assistant Guidelines

### When Making Changes

1. **Read existing code first** - understand the pattern before modifying
2. **Maintain consistency** - match the style of surrounding code
3. **Update JavaDoc** - keep documentation in sync
4. **Test manually** - run the application after changes
5. **Check for side effects** - search for all usages before refactoring

### When Reviewing Code

1. Verify Java version compatibility (Java 8+ syntax only)
2. Check for proper error handling
3. Ensure XML compatibility maintained
4. Verify GUI changes work with Swing/AWT
5. Confirm no external dependencies added

### Communication Style

- Be concise but thorough
- Explain **why** not just **what** when making suggestions
- Provide file paths as markdown links: `[FileName.java](src/FileName.java)`
- When referencing line numbers: `[FileName.java#L25](src/FileName.java#L25)`
- Use code blocks with proper syntax highlighting

## Resources

- **DTD Reference**: `jabberpoint.dtd` - XML structure definition
- **Sample Presentation**: `test.xml` - example slides
- **Demo Presentation**: See `DemoPresentation.java` for programmatic creation
- **Copyright**: All changes must respect `COPYRIGHT.txt` license terms

## Questions?

If you encounter ambiguous requirements:

1. Check existing patterns in similar classes
2. Favor simplicity over complexity
3. When in doubt, ask the team before implementing
4. Document assumptions in comments

---

**Last Updated**: February 2026
**Maintainer**: frAgileDevs Team
**Repository**: https://github.com/AlexMakesC0de/Jabberpoint-IT-SQ
