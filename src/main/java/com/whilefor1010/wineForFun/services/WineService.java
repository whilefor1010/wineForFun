package com.whilefor1010.wineForFun.services;

import com.whilefor1010.wineForFun.models.Wine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utils.WineUtils;

import java.util.Collections;
import java.util.List;

@Service
public class WineServices {

    final private List<Wine> wines = WineUtils.buildWines();

    public Page<Wine> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Wine> list;

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
