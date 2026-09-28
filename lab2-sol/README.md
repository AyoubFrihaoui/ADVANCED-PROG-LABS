# Lab 2 — proposed SOLUTION

English port of TP02 with reference solutions. Identical to `../lab2` except
`MaListe.java` (Ex1 Q1–Q4) and `MaListeHelper.java` (Ex2 Q5–Q6) are implemented.

## The whole solution in one idea: PECS

**Producer Extends, Consumer Super.** When a method only *reads* from a list,
declare it `? extends T`; when it only *writes* into a list, `? super T`.

## `MaListe<T>` internals

Type erasure forbids `new T[...]`, so storage is an `Object[]` (initial
capacity 10, doubled when full) with an unchecked cast on read — safe because
only `T` instances are ever stored:

```java
public void ajouter(T element) { ... grow if needed ...; elements[taille++] = element; }
public int taille() { return taille; }
@SuppressWarnings("unchecked")
public T element(int i) { bounds-check; return (T) elements[i]; }
public String toString() { /* space-joined, e.g. "un deux" */ }
```

## Q2 → Q3: `ajouterListe`

Naive version `ajouterListe(MaListe<T> autre)` passes TestQ2 (same type) but
TestQ3 (`MaListe<Object>.ajouterListe(MaListe<String>)`) doesn't compile:
**`MaListe<String>` is NOT a `MaListe<Object>`** (generics are invariant —
otherwise you could smuggle an `Integer` into a `String` list).

Fix: the parameter is a *producer* (we only read from it), so widen it:

```java
public void ajouterListe(MaListe<? extends T> autre)
```

`? extends Object` includes `String` → TestQ3 compiles; `? extends String`
includes `String` → TestQ2 still compiles. Nothing breaks.

## Q4: `ajouterDansListe` (mirror image)

Now `this` is the producer and the destination is the *consumer*:

```java
public void ajouterDansListe(MaListe<? super T> destination)
```

`MaListe<String>.ajouterDansListe(MaListe<Object>)`: `? super String` includes
`Object` → compiles, and adding a `String` into an `Object` list is safe.
Same-type case (`TestQ4bis`, `String` into `String`) also compiles since
`? super T` includes `T` itself.

## Q5 → Q6: `MaListeHelper.concat`

```java
public static <T> MaListe<T> concat(MaListe<? extends T> l1, MaListe<? extends T> l2) {
    MaListe<T> resultat = new MaListe<>();
    resultat.ajouterListe(l1);
    resultat.ajouterListe(l2);
    return resultat;
}
```

`<T>` before the return type makes it a *generic method*: the compiler infers
`T` from the call. `concat(String-list, String-list)` → `T = String`
(TestQ5); `concat(Integer-list, String-list)` → only common super-type
`T = Object` (TestQ6). Both sources are producers → `? extends T`.

## How tests work

JUnit 4, run by Maven Surefire (`.\mvnw.cmd test`): `TestQ1` (4 tests:
`toString`/`taille`/`element`), `TestQ2` (2: same-type `ajouterListe`),
`TestQ3` (1: `Object` absorbs `String`), `TestQ4` (2: `ajouterDansListe`,
incl. same-type), `TestQ5` (1: same-type `concat`), `TestQ6` (1: mixed-type
`concat`). Expected here: `Tests run: 11, Failures: 0, Errors: 0`.

> Teaching trap the handout hides: the French text points Ex2 at `TestQ4` —
> wrong. `concat` is covered by `TestQ5`/`TestQ6`. The translated `lab2`
> README documents the correct mapping.
