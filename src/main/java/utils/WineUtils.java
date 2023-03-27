package utils;

import com.whilefor1010.wineForFun.models.Wine;
import com.whilefor1010.wineForFun.repo.WineRepository;
import com.whilefor1010.wineForFun.repo.WineRepositoryPages;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class WineUtils {

    private static List<Wine> wines = new ArrayList<Wine>();

    private static final int NUM_WINES = 30;

    private static final int MIN_WINE_NUM = 1000;

    public static List<Wine> buildWines() {


        //Iterable<Wine> iterable = wineRepositoryPages.findAll();

        //wines = StreamSupport.stream(iterable.spliterator(), false)
        //                .collect(Collectors.toList());

        if (wines.isEmpty()) {
            IntStream.range(0, NUM_WINES).forEach(n -> {
                wines.add(new Wine("Wine" + MIN_WINE_NUM + n + 1, "Spring in Action",""));
            });

        }

        return wines;

    }

}
