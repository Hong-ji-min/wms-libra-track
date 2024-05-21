package com.sh.model.dao;

import com.sh.model.dto.publishserDto.Publisher;

public interface PublisherDAO {
    int getPublisherId(String publisherName);

    int insertPublisher(Publisher publisher);

    int updatePublisher(Publisher publisher);

    Publisher findPublisherInformation(int publisherId);

    int deletePublisher(int id);

    Publisher findByName(String name);
}
