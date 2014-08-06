package org.ozoneplatform.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.FutureCallback;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.elasticsearch.search.SearchHits;

import org.ozoneplatform.service.SearchService;
import org.ozoneplatform.dto.DtoFactoryFactory;
import org.ozoneplatform.dto.DtoFactory;

@Path("search")
@Component
@Produces({
    "application/vnd.ozp.store.searchresult+json",
    "application/json"
})
public class SearchResource {
    @Autowired private SearchService searchService;

    @Autowired private DtoFactoryFactory dtoFactoryFactory;

    @GET
    public void search(@Suspended final AsyncResponse asyncResponse) {
        ListenableFuture<SearchHits> future = searchService.searchAll();

        Futures.addCallback(future, new FutureCallback<SearchHits>() {
            public void onSuccess(SearchHits hits) {
                try {
                    //TODO replace null with actual value
                    asyncResponse.resume(dtoFactoryFactory.createDtoFactory(hits, null));
                }
                catch (Throwable t) {
                    asyncResponse.resume(t);
                }
            }

            public void onFailure(Throwable t) {
                asyncResponse.resume(t);
            }
        });
    }
}
