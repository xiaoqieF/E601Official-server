package com.fxd.server.service;

import com.fxd.server.pojo.About;

public interface SiteInfoService {
    int updateAbout(About about);
    About getAbout();
    About getRawAbout();
}
