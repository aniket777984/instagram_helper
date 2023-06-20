package com.aniket777984.instagramsaver;

import java.util.ArrayList;

public class ModelClass {

    private SearchModel graphql;

    public ModelClass(SearchModel graphql) {
        this.graphql = graphql;
    }

    public SearchModel getGraphql() {
        return graphql;
    }

    public void setGraphql(SearchModel graphql) {
        this.graphql = graphql;
    }
}
