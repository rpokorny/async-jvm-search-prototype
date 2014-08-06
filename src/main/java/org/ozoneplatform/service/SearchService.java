package org.ozoneplatform.service;

import javax.annotation.PreDestroy;

import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.node.Node;
import org.elasticsearch.client.Client;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.action.ListenableActionFuture;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private Node localNode;
    private Client esClient;

    private static class SearchListener implements ActionListener<SearchResponse> {
        private SettableFuture<SearchHits> promise = SettableFuture.<SearchHits>create();

        public ListenableFuture<SearchHits> getPromise() {
            return promise;
        }

        public void onFailure(Throwable t) {
            promise.setException(t);
        }

        public void onResponse(SearchResponse response) {
            promise.set(response.getHits());
        }
    }

    public SearchService() {
        localNode = NodeBuilder.nodeBuilder().node();
        esClient = localNode.client();
    }

    @PreDestroy
    public void shutdown() {
        localNode.close();

        localNode = null;
        esClient = null;
    }

    /**
     * A simple method to search for all listings.  This method is for
     * prototyping only and should ultimately be removed.
     */
    public ListenableFuture<SearchHits> searchAll() {
        SearchListener searchListener = new SearchListener();
        ListenableActionFuture<SearchResponse> responseFuture =
            esClient.prepareSearch().execute();

        responseFuture.addListener(searchListener);

        return searchListener.getPromise();
    }
}
