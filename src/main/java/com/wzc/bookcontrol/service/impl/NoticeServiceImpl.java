package com.wzc.bookcontrol.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzc.bookcontrol.dao.pojo.Notice;
import com.wzc.bookcontrol.service.NoticeService;
import com.wzc.bookcontrol.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author 86188
* @description 针对表【notice(公告表)】的数据库操作Service实现
* @createDate 2023-01-26 20:09:03
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService {

}




