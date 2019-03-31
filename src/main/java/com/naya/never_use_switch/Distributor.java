package com.naya.never_use_switch;

import org.springframework.context.annotation.Bean;

/**
 * @author Evgeny Borisov
 */

public interface Distributor {
    void distribute(Message message);
    int getMyCode();
}
