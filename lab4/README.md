# Lab 4 — Introduction to Design Patterns


## Objective

Discover and practice **structural design patterns** through small, concrete
examples. You will manipulate two patterns:

- the **Decorator**
- the **Composite**

💡 This lab emphasizes **reusability**, **object composition** and
**separation of concerns**.

> Unit tests for Exercise 1 are to be written by the student (a reference
> `ArraySequenceTest` is provided as a model).

## Exercise 1 — the Decorator pattern

Context: inspired by Python's `itertools` library. We work on **sequences of
strings** (e.g. lines of a text), progressively building a hierarchy of
objects that *decorate* an existing sequence with extra behavior.

We assume an interface `Sequence` defining one method:

```java
String ligneSuivante(); // next line, or null when finished
```

plus a base implementation, `ArraySequence`, backed by a `String[]`.

### Question 1 — UpperCase decorator

Write and test an `UpperCaseDecoratorSequence` class, a **decorator** of
`Sequence`. It returns the strings in **uppercase**.

💡 *Reminder:* a decorator must **implement the same interface** as the object
it decorates, and **delegate** part of the behavior to the decorated object.

```java
Sequence s = new UpperCaseDecoratorSequence(new ArraySequence(lignes));
System.out.println(s.ligneSuivante());
```

### Question 2 — Grep decorator

Write and test a `GrepSequence` class, a decorator filtering another
sequence's lines by a given text:

```java
GrepSequence s = new GrepSequence(autreSequence, "chemin");
String l;
while ((l = s.ligneSuivante()) != null) {
    System.out.println(l);
}
```

prints only the lines of `autreSequence` containing `"chemin"`.

💭 **Food for thought**
- Why is a decorator more flexible than direct inheritance?
- What if we wanted to combine `UpperCaseDecoratorSequence` and `GrepSequence`?

### Question 3 — LineNumber decorator

Write and test a `LineNumberSequence` class, a `Sequence` decorator keeping in
memory the **number of the last line read** (0-based: the first line read is
number 0, `-1` means "nothing read yet"):

```java
LineNumberSequence s = new LineNumberSequence(autreSequence);
String l;
while ((l = s.ligneSuivante()) != null) {
    System.out.println(s.getLineNumber() + ": " + l);
}
```

💡 **Key idea:** store the line counter in an instance variable incremented on
each read.

### Question 4 — Slice decorator

Create a `SliceSequence` class, a decorator returning **only part** of the
original sequence's lines. The constructor takes two integers:
- `start` : index of the first included line
- `end` : index of the first line to ignore.

```java
SliceSequence s = new SliceSequence(autreSequence, 10, 20);
```

displays lines 10 (included) to 20 (excluded).

💡 **Tip:** reuse your `LineNumberSequence` to simplify index handling!

### To remember

The **Decorator pattern**:
- adds responsibilities to an object dynamically;
- composes behaviors without a subclass explosion;
- follows the **Open/Closed Principle** (SOLID).

## Exercise 2 — the Composite pattern

Context: an **XML** document represented as a tree of objects. Each node
(element, text, attribute…) belongs to a common hierarchy. The goal is to
**generate XML from this structure**, following the **Composite** pattern.

### Question 1 — XML generation

You are given a **composite XML** implementation plus an example showing how
to extract the text of the whole document (`textContent()`).

Implement:

```java
String toXML();
```

for the composite classes (`Element`, `TextNode`).

💡 **Display rules:**
- Attributes must be listed in **alphabetical** order of their name
  (deterministic behavior — `TreeMap` already sorts them, use
  `XMLHelper.protectAttributeValue` for values and `XMLHelper.protectText`
  for text nodes).
- You may ignore the difference between empty tags `<img/>` and
  `<img></img>`.

Run the demo: `tp04.exo2.Main` prints the document text, then its XML.

### Question 2 — reflecting on the patterns

💭 **Analyze** your code:
- What concrete benefits do the *Decorator* and *Composite* patterns bring?
- What evolution/maintenance problems could have appeared without them?

## How to run (VS Code + JDK 11+, tested with JDK 25)

1. Open THIS folder (`lab4`, the one containing `pom.xml`) via
   `File > Open Folder`.
2. Install `Extension Pack for Java` (Microsoft), trust the workspace, wait
   for the Maven import.
3. Run tests:
   - UI: `Testing` panel (beaker icon) > `Run Tests`.
   - Terminal (project root):
     ```powershell
     .\mvnw.cmd test
     .\mvnw.cmd -Dtest=ToXMLTest test
     .\mvnw.cmd -Dtest=ArraySequenceTest test
     ```

> Note: `pom.xml` targets Java 11 (original TP targeted Java 8; bumped so it
> compiles on modern JDKs). `README.html` is the original French rendering,
> kept for reference.

## Test map

| Question | Test class | What it checks |
| --- | --- | --- |
| Ex1 (base) | `ArraySequenceTest` (3 tests) | base sequence, incl. empty + over-read |
| Ex1 Q1–Q4 | *you write them* | one test class per decorator (see solution) |
| Ex2 Q1 | `ToXMLTest` (9 tests) | text nodes, escaping, attrs order, full doc |

Starter state: `ArraySequenceTest` green (3/3), `ToXMLTest` red (0/9,
`UnsupportedOperationException`). Solution state: all green.
