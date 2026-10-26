# Lab 6 — The Template Method Design Pattern


## Objective

Reduce duplication and simplify data-access (DAO) code with the **behavioral
design pattern: Template Method**.

You will:

- spot the repetitive code in a naive DAO;
- create a *template* factoring out that behavior;
- use a generic version allowing result returns;
- discover an advanced version of the pattern, already implemented for you.

Tests must keep passing after each question (`.\mvnw.cmd test`).

> Extra unit tests (beyond the provided ones) are to be written by the
> student.

## Exercise 1

Context: you are given a classic, extremely verbose `EtudiantDAO`
(`tp05.templateMethod`):

- each method opens a connection;
- creates a `PreparedStatement`;
- executes SQL;
- closes the connection;
- handles repetitive exceptions…

Adding a new DAO or a cross-cutting behavior (logging, error handling,
audit…) means **duplicating everywhere**, raising the bug risk.

➝ The goal is to **factor out the skeleton** into an abstract class following
the *Template Method* pattern. The database is H2 in-memory
(`jdbc:h2:mem:testdb`), recreated before each test — run `Main` to see its
content, `DBAccessTest` (7 tests) for the expected DAO behavior.

### Question 1 — template with no return value

Create an abstract class providing:

```java
public final void executer() {
    ouvrirConnexion();
    try {
        executerAvecConnexion(connexion);
    } finally {
        fermerConnexion();
    }
}
```

and a method to implement:

```java
protected abstract void executerAvecConnexion(Connection c);
```

🔧 Work required:

- Implement this *template* (note: the hook must be allowed to throw
  `SQLException`, otherwise DAO code cannot use it — adapt signatures).
- Rewrite `EtudiantDAO.save(Etudiant)` to use your *template method*
  (anonymous subclass).

### Question 2 — generic template

Some operations like `findById(int)` must **return a result**. The previous
template cannot be used as-is.

Modify your template so it can return a value:

- either using the `Object` type;
- or (cleaner) making it **generic**.

A method returning nothing will use the `Void` class.

🔧 Work required:

- Implement this new *template method* version.
- Rewrite `findById(int)` to use it (and, for consistency, the other DAO
  methods too).

## Exercise 2

Context: package `templateMethod2.template` holds a much more advanced
version of the pattern (given, study-only):

- `SQLConnectionTemplate` : simple connection handling;
- `SQLStatementTemplate` : runs a query over several argument tuples, with or
  without a return;
- `SQLQueryTemplate` : handles `SELECT`s with object extraction from a
  `ResultSet` (you only write `extractData`);
- `SQLSimpleHelper` : simplified facade, the main API (`insert`, `update`,
  `sqlIntQuery`, …).

Study them in this order: `SQLConnectionTemplate`, then
`SQLStatementTemplate`, then `SQLQueryTemplate`.

### Question 3 — slimmed-down DAO rewrite

🔧 Work required:

- Rewrite `templateMethod2.EtudiantDAO` with **`SQLSimpleHelper`** (its
  `update`/`delete`/`findById`/`findAll` are `TODO` stubs; `save` is done and
  shows the way, `EtudiantQueryTemplate` shows the query side).

Your goal:

- drastically less code;
- same business logic;
- **all existing tests still pass** (plus the mirrored `DBAccess2Test`
  suite in the solution).

## How to run (VS Code + JDK 11+, tested with JDK 25)

1. Open THIS folder (`lab6`, the one containing `pom.xml`) via
   `File > Open Folder`.
2. Install `Extension Pack for Java` (Microsoft), trust the workspace, wait
   for the Maven import.
3. Run tests / demos:
   ```powershell
   .\mvnw.cmd test
   .\mvnw.cmd -Dtest=DBAccessTest test
   ```
   Run `tp05.templateMethod.Main` (or `templateMethod2.Main`) to print the DB
   content.

> Note: `pom.xml` keeps the original H2 1.4.200 dependency (works on JDK 25,
> verified); the upstream `<scope>implementation</scope>` (a Gradle-ism,
> invalid in Maven) was dropped to the default `compile` scope to silence the
> model warning.

## Test map

| Question | Test class | What it checks |
| --- | --- | --- |
| Ex1 (all) | `DBAccessTest` (7 tests, given) | fixture content, findById (+null), save ×2, delete, update |
| Ex2 Q3 | `DBAccess2Test` (7 tests, solution only) | same behavior through the rewritten DAO |

Starter state: `DBAccessTest` 7/7 green — the exercise is refactoring WITHOUT
breaking them. Solution state: 14/14 green.
