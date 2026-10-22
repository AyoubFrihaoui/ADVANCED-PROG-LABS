# Lab 5 — proposed SOLUTION 


## Ex1 — Builder (package `exo1`)

**Q1+Q2 `Main1`** builds both requests with the 9-arg constructor (`null`
where unused) and validates via a `valider(req)` helper: host required, no
body on GET, `keepAlive`/`followRedirects` mutually exclusive. The helper
proves the point: every client must remember to call it, every new rule edits
every client.

**Q3 `HttpRequestBuilder`**: each setter stores + `return this` (fluent);
defaults (`keepAlive = true`, `followRedirects = false`, `timeoutMs = 5000`)
so clients set only what matters. Added the two missing setters
`keepAlive(...)` / `followRedirects(...)` — without them the illegal-combo
test is unreachable, and "easy to add options" is the lesson. `build()` holds
the three checks in ONE place and throws `IllegalStateException`.

**Q4 `Main2`** rewrites both requests fluently. Note: the original
`with_builder` `HttpRequest` was package-private, but `Main2` lives in the
parent package and the handout's snippet names the type — so it is `public`
here (getters added too, tests need them). Alternative valid answer: keep it
package-private and put `Main2` in the builder package.

Comparison answers for class: named steps vs positional args, no `null`s,
defaults, invalid states unbuildable; new option = one setter + zero client
breakage.

## Ex2 — debugging the student builder (package `exo2`)

The failing test was `getNomsEtudiants()` returning `null` (stub never
implemented). Fix: `reset()` clears the list; `traiterLigne` splits on `":"`
(trimmed, blank lines skipped, `< 3` parts rejected), builds the `Etudiant`
and parses trailing grades; `getNomsEtudiants()` collects `getNom()`. Spaces
inside names ("De La Vega") survive because the separator is `":"`, not space.

## Tests

`.\mvnw.cmd test` → `Tests run: 14, Failures: 0, Errors: 0`:
`HttpRequestBuilderTest` (5: valid POST, valid GET+query, missing host,
GET+body, flag combo), `Main1Test` (4: same rules on the helper),
`TestNomEtudiantsBuilder` (1, given — now green),
`TestEtudiantsBuilderCases` (4: parsed fields, no grades, spaces, 12 grades).
