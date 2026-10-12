# Design Notes

## Why ArrayList Instead of Array?

We chose `ArrayList` over plain arrays for storing Students, Courses, and Enrollments for several reasons:

1. **Dynamic sizing** — Arrays in Java have a fixed size that must be declared at creation time. In a management system, we don't know in advance how many students or courses will be added. `ArrayList` grows and shrinks automatically as elements are added or removed.

2. **Built-in methods** — `ArrayList` provides convenient methods like `add()`, `remove()`, `size()`, and `contains()` that would need to be manually implemented with arrays. For example, removing an element from the middle of an array requires shifting all subsequent elements, but `ArrayList.remove()` handles this automatically.

3. **Type safety with generics** — `ArrayList<Student>` ensures at compile-time that only `Student` objects can be stored, preventing type errors that could occur with `Object[]` arrays.

4. **Cleaner code** — Using `ArrayList` results in more readable and maintainable code, which aligns with the clean code principles this project emphasizes.

---

## Where Static Members Are Used and Why

### `IdGenerator` — Static Fields and Methods

The `IdGenerator` class uses static fields (`studentIdCounter`, `courseIdCounter`, `enrollmentIdCounter`) and static methods (`getNextStudentId()`, `getNextCourseId()`, `getNextEnrollmentId()`).

**Why static?** ID counters need to be **shared across the entire application** — not tied to any specific object instance. Every time a new student is created, the counter increments regardless of which part of the code creates it. Static ensures there's exactly one counter for each entity type, living at the class level.

The class also has a private constructor to prevent instantiation, since all its methods are static — it's a **utility class pattern**.

### `InputValidator` — Static Methods

Similarly, `InputValidator` uses static methods (`isNonEmptyString()`, `isPositiveInt()`, `isValidEmail()`) because validation logic doesn't depend on any instance state. These are pure utility functions that take input and return a result.

### `Main` — Static Fields and Methods

The `Main` class uses static fields for the `Scanner` and service objects because they are accessed from `static` methods (including `main()`). In a console application with a single entry point, static is the natural fit.

---

## Where Inheritance Is Used and What We Gained

### `Person` → `Student` / `Trainer`

We introduced a `Person` base class with common fields (`id`, `firstName`, `lastName`, `email`) and a `getDisplayName()` method. Both `Student` and `Trainer` extend `Person`.

**What we gained:**

1. **Code reuse** — The common fields and their getters/setters are defined once in `Person` and inherited by both `Student` and `Trainer`. Without inheritance, we'd duplicate these fields in both classes.

2. **Polymorphism** — By overriding `getDisplayName()` in each subclass, we can treat `Student` and `Trainer` objects through a `Person` reference and still get the correct behavior. This is demonstrated at application startup where we create `Person student = new Student(...)` and call `student.getDisplayName()` — Java automatically calls the `Student` version.

3. **Extensibility** — If we later need to add other types (e.g., `Admin`, `TA`), they can extend `Person` and immediately inherit all common functionality. We only need to add what's specific to the new type.

4. **Constructor chaining** — Subclass constructors use `super(...)` to call the parent's constructor, ensuring proper initialization of inherited fields. This demonstrates a core Java concept (constructor chaining) in a practical context.

---

## Separation of Concerns

The project follows a three-layer architecture:

| Layer | Package | Responsibility |
|-------|---------|----------------|
| **Entity** | `entity` | Data classes — define the shape of the data |
| **Service** | `service` | Business logic — CRUD operations, validation, rules |
| **UI** | `ui` | User interaction — menus, input reading, output display |

**Why?** This separation means:
- Changing the UI (e.g., switching from console to a web interface) doesn't require touching the service or entity code
- Business rules live in one place (services), making them easier to test and maintain
- Entities remain simple data holders without logic leaking into them
