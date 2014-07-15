package org.ozoneplatform.service;

import org.springframework.stereotype.Service;

import org.ozoneplatform.entity.Listing;

@Service
public class ListingService extends AbstractEntityService<Listing> {
    ListingService() {
        super(Listing.class);
    }
}
