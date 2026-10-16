# Lab 5 — The Builder Design Pattern: HTTP Request Project



## Objective

Discover and practice the **creational design pattern: the Builder**, through
a concrete mini-project: **building complex HTTP requests**.

You will:

- build HTTP requests without a Builder and observe the limits;
- add consistency rules that make construction painful;
- implement a clean, fluent, safe **HttpRequestBuilder**;
- compare both approaches.

> Unit tests for Exercise 1 are to be written by the student.

## Exercise 1

Context: you must represent an **HTTP request** as a Java object. For this,
you are given an `HttpRequest` class with a deliberately very heavy
constructor (9 parameters).

### Question 1 — build two requests

In package `exo1.without_builder`, implement `Main1` which builds **two** HTTP
requests using the constructor:

- a request: `GET /users?limit=10`
- a request: `POST /login` with a JSON body

Work required:

- Use the constructor as-is, **with all parameters** (use `null` where needed).
- Comment in a few lines on the readability of this approach and the possible
  errors.

### Question 2 — add consistency rules

**Without touching the `HttpRequest` class**, add the following rules:

- A **GET** request can never have a body.
- A request **must always have a host**.
- `keepAlive = true` and `followRedirects = true` are incompatible.

Work required:

- Add these validations in `Main1` or via a helper method.
- Explain in a few lines why this approach becomes hard to maintain and extend.

### Goal

We will replace construction with an **HttpRequestBuilder**, creating a
request step by step in a **fluent**, **clear** and **safe** way.

### Question 3 — implement `HttpRequestBuilder`

Implement an `HttpRequestBuilder` (skeleton in
`exo1.with_builder.builder`, all setters currently return `null`).

Constraints:

- `host` mandatory
- body forbidden if `method = GET`
- `keepAlive` and `followRedirects` cannot both be `true`

Tests to write:

- valid construction
- missing host
- body forbidden on GET
- illegal keepAlive/followRedirects combination

> Note: the skeleton has no `keepAlive(...)` / `followRedirects(...)`
> setters although the fields exist — adding them is part of the exercise
> ("ease of adding new options" is exactly what Q4 asks you to compare).

### Question 4 — rewrite the client code

Rewrite your two initial requests in a `Main2` class:

```java
HttpRequest req = new HttpRequestBuilder()
    .method(...)
    .url(...)
    .host(...)
    .body(...)
    .timeout(...)
    .build();
```

Work required — compare in a few lines:
- readability before/after
- ease of adding new options
- safety and state consistency

## Exercise 2

Context: files contain student information, one per line:

```
numero:nom:prenom:note1:note2:note3:...
```

A `LecteurFichierEtudiants` class (the "director") feeds each line to a
`ListEtudiantBuilder`. An `Etudiant` class (numero, nom, prenom, grade list)
is provided, plus a `ListEtudiantBuilder` interface with a partial
implementation in `ArrayListEtudiantBuilder`.

### Question — debug `ArrayListEtudiantBuilder`

One of the provided tests (`TestNomEtudiantsBuilder.testSimple`) fails: with
the 3-student fixture in `FichierUtil`, `getNomsEtudiants()` must return
`["Turing", "Lovelace", "Wirth"]` but currently returns `null`.

🔧 Work required:

- Understand why the test fails.
- Implement `reset()`, `traiterLigne(String)` and `getNomsEtudiants()` in
  `ArrayListEtudiantBuilder` (split each line on `":"`; skip blank lines).

🧪 Tests to write:

- file with 3 students (result count + parsed fields, not just names);
- student without grades;
- student with spaces in the name;
- student with many grades.

## How to run (VS Code + JDK 11+, tested with JDK 25)

1. Open THIS folder (`lab5`, the one containing `pom.xml`) via
   `File > Open Folder`.
2. Install `Extension Pack for Java` (Microsoft), trust the workspace, wait
   for the Maven import.
3. Run tests:
   - UI: `Testing` panel (beaker icon) > `Run Tests`.
   - Terminal (project root):
     ```powershell
     .\mvnw.cmd test
     .\mvnw.cmd -Dtest=TestNomEtudiantsBuilder test
     ```

> Note: `pom.xml` was modernized for this course (original TP pinned JUnit
> 3.8.1 with no compiler level, which cannot compile the provided
> `org.junit.Test`-based test on a modern JDK). It now uses JUnit 4.12 +
> Java 11, like the other labs. A `mvnw` wrapper (borrowed from lab4 — the
> original TP has none) is included. Language features used stay Java 8
> compatible.

## Test map

| Question | Test class | What it checks |
| --- | --- | --- |
| Ex1 Q3 | *you write it* (`HttpRequestBuilderTest` in solution) | valid build, missing host, GET+body, keepAlive/followRedirects |
| Ex2 | `TestNomEtudiantsBuilder` (1 test, given) | 3-student file → names list |
| Ex2 | *you write them* (4 in solution) | parsed fields, no grades, spaces, many grades |

Starter state: `TestNomEtudiantsBuilder` red (`null` vs expected names).
Solution state: all green.
