package com.alugueaqui.repositories;

import com.alugueaqui.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i ")
    Page<Item> findAllPageable(Pageable pageable);
}
