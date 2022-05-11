package com.fxd.server.service;

import com.fxd.server.dao.SiteInfoMapper;
import com.fxd.server.pojo.About;
import com.fxd.server.utils.MarkdownUtil;
import org.springframework.stereotype.Service;

@Service
public class SiteInfoServiceImpl implements SiteInfoService{
    private final SiteInfoMapper mapper;

    public SiteInfoServiceImpl(SiteInfoMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int updateAbout(About about) {
        return mapper.updateAbout(about);
    }

    @Override
    public About getAbout() {
        About about = mapper.getAbout();
        if (about != null) {
            about.setContent(MarkdownUtil.markToHtml(about.getContent()));
        }
        return about;
    }

    @Override
    public About getRawAbout() {
        return mapper.getAbout();
    }
}
