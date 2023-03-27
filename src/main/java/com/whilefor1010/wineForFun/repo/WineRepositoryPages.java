package com.whilefor1010.wineForFun.repo;


import com.whilefor1010.wineForFun.models.Wine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WineRepositoryPages extends PagingAndSortingRepository<Wine, Long> {


}