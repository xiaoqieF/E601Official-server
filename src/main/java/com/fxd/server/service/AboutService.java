package com.fxd.server.service;

import com.fxd.server.pojo.About;

public interface AboutService {
    int updateAbout(About about);
    About getAbout();
    About getRawAbout();
}
