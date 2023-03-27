package com.whilefor1010.wineForFun.services;

import com.whilefor1010.wineForFun.models.Wine;
import com.whilefor1010.wineForFun.repo.WineRepository;
import com.whilefor1010.wineForFun.repo.WineRepositoryPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import utils.WineUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WineService {

    @Autowired
    //private WineRepository wineRepository;
    private WineRepositoryPages wineRepository;

    final private WineUtils wineUtils = new WineUtils();
    private List<Wine> wines;// = wineUtils.buildWines();

    public Page<Wine> findPaginated(Pageable pageable, String sortField) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Wine> list;

        if(sortField==""){
            sortField="id";
        }

        Iterable<Wine> iterable = wineRepository.findAll(Sort.by(sortField));

        wines = StreamSupport.stream(iterable.spliterator(), false)
                       .collect(Collectors.toList());

        if (wines.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, wines.size());
            list = wines.subList(startItem, toIndex);
        }

        Page<Wine> winePage = new PageImpl<Wine>(list, PageRequest.of(currentPage, pageSize), wines.size());

        return winePage;
    }

}
