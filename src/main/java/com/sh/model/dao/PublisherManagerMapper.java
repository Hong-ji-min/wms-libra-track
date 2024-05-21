package com.sh.model.dao;

import com.sh.model.entity.MemberDto;
import com.sh.model.entity.PublisherManagerDto;

public interface PublisherManagerMapper {
    PublisherManagerDto findPublisherManagerInfo(MemberDto memberDto);

    int updatePublisherManagerInfo(MemberDto memberDto);

    PublisherManagerDto findByPublisherManagerId(int publisherManagerId);

    int insertPublisherManager(PublisherManagerDto publisherManagerDto);
}
