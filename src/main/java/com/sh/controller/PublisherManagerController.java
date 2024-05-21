package com.sh.controller;


import com.sh.model.entity.MemberDto;
import com.sh.model.entity.PublisherManagerDto;
import com.sh.model.service.PublisherManagerService;
import com.sh.model.service.publisherService.PublisherService;
import com.sh.view.result.MemberResultView;

public class PublisherManagerController {
    PublisherManagerService publisherManagerService = new PublisherManagerService();

    PublisherService publisherService = new PublisherService();

    public PublisherManagerDto findPublisherManagerInfo(MemberDto memberDto) {
        PublisherManagerDto publisherManagerDto = publisherManagerService.findPublisherManagerInfo(memberDto);
        if (publisherManagerDto != null) {
            MemberResultView.displayPublisherManager(publisherManagerDto);
        }
        return publisherManagerDto;
    }

    public void updatePublisherManager(MemberDto memberDto) {
        int result  = publisherManagerService.updatePublisherManager(memberDto);
        MemberResultView.displayResult("출판사 매니저 정보 수정", result);

    }

    public void insertPublisher(MemberDto memberDto, String publisher) {
        PublisherManagerDto publisherManagerDto = new PublisherManagerDto();

        int publisherId = publisherService.findByName(publisher).getPublisherId();

        publisherManagerDto.setPublisherId(publisherId);
        publisherManagerDto.setMemberId(memberDto.getMemberId());
        publisherManagerService.insertPublisher(publisherManagerDto);
    }

}
