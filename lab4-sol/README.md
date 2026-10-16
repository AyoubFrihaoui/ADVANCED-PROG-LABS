# Lab 4 — SOLUTION proposed



## Ex1 — Decorators (package `tp04.exo1`)

One idea four times: **same interface, hold the decorated object, delegate,
transform**.

- **Q1 `UpperCaseDecoratorSequence`**: `line.toUpperCase()`, `null` passes
  through (end of sequence stays end of sequence).
- **Q2 `GrepSequence(seq, motif)`**: loop, return first line with
  `line.contains(motif)`; `null` when the inner sequence is exhausted.
- **Q3 `LineNumberSequence`**: instance counter, 0-based (`-1` = nothing read
  yet — consistent with `SliceSequence` indices), incremented only on
  successful reads so it freezes at the last line.
- **Q4 `SliceSequence(seq, start, end)`**: reuse by composition — wraps a
  `LineNumberSequence`, skips `start` lines in the constructor, returns
  `null` once `getLineNumber() + 1 >= end`. Lines `[start, end)` served.

Teaching answers: decorator beats inheritance because behaviors **stack at
runtime** (`UpperCase(Grep(Array))` — see `testDecoratorsStack`) instead of
requiring a subclass per combination; adding a behavior never touches existing
code (Open/Closed).

## Ex2 — Composite (package `tp04.exo2`)

- **`TextNode.toXML`** = `XMLHelper.protectText(text)` (leaf case).
- **`Element.toXML`** = `<tag` + attributes + `>` + children + `</tag>`.
  Attributes iterate a `TreeMap`, hence **already alphabetical**; values via
  `protectAttributeValue`, children via recursive `toXML()` — the Composite
  recursion (uniform treatment of leaves and composites) is the pattern.
- Empty tags render `<img …></img>`; the handout explicitly accepts this.

## Tests

`.\mvnw.cmd test` → `Tests run: 25, Failures: 0, Errors: 0`:
`ArraySequenceTest` (3, given), `UpperCaseDecoratorSequenceTest` (3, incl.
stacking), `GrepSequenceTest` (3), `LineNumberSequenceTest` (2),
`SliceSequenceTest` (5), `ToXMLTest` (9, given).
