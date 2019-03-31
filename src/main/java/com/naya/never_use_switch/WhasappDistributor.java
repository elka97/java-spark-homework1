package com.naya.never_use_switch;

import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */

@Component
public class WhasappDistributor implements Distributor {
    @Override
    public void distribute(Message message) {
        // 20 lines of code
        System.out.println("whasapp was sent");
    }

    @Override
    public int getMyCode() {
        return 2;
    }
}
