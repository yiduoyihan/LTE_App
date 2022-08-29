package com.leidi.lteapp.util;

/**
 * 分页加载的配置
 */
public class PageInfoUtil {
    public int page = 1;

    public void nextPage() {
        page++;
    }

    public void reset() {
        page = 1;
    }

    public boolean isFirstPage() {
        return page == 1;
    }

}
