package com.terryliu.springbootmockdemo.service.impl;

import com.terryliu.springbootmockdemo.config.ThreadSafe;
import com.terryliu.springbootmockdemo.service.ThreadSafeService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/22
 */
@Service
public class ThreadSafeServiceImpl implements ThreadSafeService {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    @Override
    public String date(int seconds) {
//        return ThreadSafe.dateFormatThreadLocal.get().format(new Date(seconds * 1000L));
        return dateFormat.format(new Date(seconds * 1000L));
    }

}
