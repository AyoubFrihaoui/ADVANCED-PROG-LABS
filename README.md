# Advanced Programming — Labs

Course labs (Java, Maven). Each lab is a self-contained Maven project with its
own `README.md`, starter code, tests (`mvnw test`) and wrapper (`mvnw.cmd`).

## Labs

| Lab | Topic | Folder |
| --- | --- | --- |
| Lab 1 | Metaprogramming: introspection + annotations | [`lab1/`](lab1/) |
| Lab 2 | Generics: generic classes, bounds, wildcards | [`lab2/`](lab2/) |
| Lab 3 | OO design: coupling, Law of Demeter, SOLID | [`lab3/`](lab3/) |
| Lab 4 | Design patterns: Decorator + Composite | [`lab4/`](lab4/) |
| Lab 5 | Creational pattern: Builder (HTTP + parsing) | [`lab5/`](lab5/) |
| Lab 6 | Behavioral pattern: Template Method (JDBC/H2 DAO) | [`lab6/`](lab6/) |

## Mini-project

| Project | Topic | Folder |
| --- | --- | --- |
| Arena | Turn-based game engine (Connect-Four): evaluation project, one month, English | [`miniprojet/`](miniprojet/) |

Open the lab folder itself in VS Code (where `pom.xml` is), not this root.
Requires JDK 11+ (lab2) / 17+ (lab1) and the `Extension Pack for Java`. Run tests with
`.\mvnw.cmd test` (Windows) or `./mvnw test` (Linux/Mac).

## Lectures

PDF slides live in [`lectures/`](lectures/) (uploaded separately).

## For instructors

`lab1-sol/`, `lab2-sol/`, `lab3-sol/`, `lab4-sol/`, `lab5-sol/`, `lab6-sol/`, `miniprojet-sol/` (reference solutions) are intentionally **not
committed** — see `.gitignore`. Keep them local, never push them to the
student-facing repo. (`miniprojet-sol/` additionally holds the private
defense question bank.)

