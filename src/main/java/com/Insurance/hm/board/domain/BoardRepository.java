package com.Insurance.hm.board.domain;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    @EntityGraph(attributePaths = {"employee","fileList"})
    Optional<Board> findById(Long aLong);

    @Override
    @EntityGraph(attributePaths = {"employee","fileList"})
    List<Board> findAll();
}
