package com.lx.retrofit.bean;

import java.util.List;

/**
 * Created on 17-8-2 下午3:38
 */

public class ArticleList {

    private List<Results> articleList;

    private boolean error;

    public List<Results> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Results> articleList) {
        this.articleList = articleList;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        error = error;
    }
}
