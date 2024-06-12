package com.terryliu.springbootmockdemo.service;

import org.springframework.context.annotation.Scope;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/22
 */
@Scope("prototype")
public interface ThreadSafeService {
    String date(int seconds);
}
