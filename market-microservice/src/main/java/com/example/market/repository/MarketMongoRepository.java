package com.example.market.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.market.document.TradeDocument;

public interface MarketMongoRepository extends MongoRepository<TradeDocument, String> {

}
