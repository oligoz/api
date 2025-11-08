package br.senac.rj.api.repository;

import br.senac.rj.api.model.Lingua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinguaRepository extends JpaRepository<Lingua, Long> {
}
