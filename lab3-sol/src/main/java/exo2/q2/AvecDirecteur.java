package exo2.q2;

/**
 * SOLUTION (Ex2 Q2) — abstraction for "anything that has a director".
 * Entreprise, Departement, Service and Equipe all implement it, so directors
 * can be collected uniformly instead of special-casing teams.
 */
public interface AvecDirecteur {
    Employe getDirecteur();
}
