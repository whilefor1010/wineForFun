package com.whilefor1010.wineForFun.repo;


import com.whilefor1010.wineForFun.models.Wine;
import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long> {


}