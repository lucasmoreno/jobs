package com.github.rafaelcrz.paggichallenge.api.interfaces;

/**
 * Created by Rafael Felipe on 13/06/2017.
 *
 * Alguns parametros que são comuns
 */

public interface DefaultParams {

    String START_DATE = "start_date";
    String END_DATE = "end_date";
    String PAGE = "page";
    String PAGE_SIZE = "page_size";

    void setPage(String page);

    void setStartDate(String startDate);

    void setEndData(String endData);

    void setPageSize(String pageSize);
}
