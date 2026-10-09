# Lab 3 — SOLUTION proposel

Identical to `../lab3` except:
new packages `exo1.q1.v2`, `exo1.q2.v2`, `exo2.q1` (refactored), `exo2.q2`,
plus JUnit tests under `src/test/java`.

## Ex1 Q1 — `Logger` interface (package `exo1.q1.v2`)

**Problems in v1:** (1) `LogicielDeCalcul` creates `new FileLogger(monlog.txt)`
itself — the business code chooses the infrastructure; (2) every method
`throws IOException` because of the logger, so logging pollutes the whole API;
(3) unit tests would create real files.

**Fix:**
- `Logger` interface with `log(String)` + `end()` and **no checked
  exceptions**.
- `NullLogger` (no-op), `MemoryLogger` (records into `ArrayList<String>`,
  exposes `getMessages()` — this is what tests use), `FileLogger` (original
  behavior, `IOException` wrapped in `UncheckedIOException`).
- `LogicielDeCalcul(Logger logger)` — **constructor injection**: the caller
  creates the logger. Answer to "where should the logger be created?": at the
  application's entry point (`Main`), never inside business code.

## Ex1 Q2 — `Dumper` abstraction (package `exo1.q2.v2`)

**Problem in v1:** `Catalogue.xmlDumper()` builds `new XMLDumper()` itself and
hard-codes the XML traversal — adding JSON means editing `Catalogue`.

**Fix:** `Dumper` interface (`openTag`/`text`/`closeTag`/`getResult`).
`Catalogue.exporter(Dumper)` holds the single traversal; `XMLDumper` (same
rendering as v1) and `JSONDumper` (`{"@type":"…","contenu":[…]}` nodes,
comma-tracking via a per-level flag stack — the same depth-tracking trick the
handout suggests for YAML) just render. `Main` proves switching formats
touches only the caller.

## Ex2 Q1 — Demeter fix (package `exo2.q1`, refactored in place)

**Violation:** `Entreprise.listeDirecteursDepartement` chained
`d.getServices()` → `s.getEquipes()` → `e.getDirecteur()` — it knew the whole
hierarchy. Any change (extra level, rename) breaks this client.

**Fix:** each level exposes `getDirecteursEquipes()` and only talks to its
direct children: `Entreprise → Departement → Service → Equipe → directeur`.
Old method kept as a delegating alias so `Main` still works. Tests assert both
methods agree.

## Ex2 Q2 — `AvecDirecteur` (package `exo2.q2`)

New hierarchy where every entity has a director: `AvecDirecteur` interface
with `getDirecteur()`, implemented by all four levels. Each level's
`getDirecteurs()` returns its own director plus its children's (delegated),
so `Entreprise.getDirecteurs()` collects the whole company top-down while
every class still talks only to its direct parts.

## Ex3 — SOLID reading guide (for class discussion)

| Principle | Respected | Violated (slightly) |
| --- | --- | --- |
| SRP | `MemoryLogger` only records; `Produit` only holds data | v1 `Catalogue` mixes business + XML rendering |
| OCP | v2: new format = new `Dumper`, `Catalogue` closed | v1: new format = edit `Catalogue` |
| LSP | any `Logger`/`Dumper` substitutable in business code | `FileLogger` constructor can throw unchecked on bad path (documented) |
| ISP | `Dumper`/`Logger` are minimal, client-specific | — (interfaces already narrow; point this out) |
| DIP | v2 business code depends on `Logger`/`Dumper` abstractions | v1 depends on `FileLogger`/`XMLDumper` concretions |

## Tests (solution only — the starter ships none)

`.\mvnw.cmd test` → `Tests run: 13, Failures: 0, Errors: 0`:
`TestLogger` (4: result value, message capture, null logger, file content),
`TestExport` (4: XML shape, JSON shape + brace balance, format switch, empty
catalogue), `TestDemeter` (5: directors found, old/new agreement, per-level
delegation, top-down collection, uniform interface).
