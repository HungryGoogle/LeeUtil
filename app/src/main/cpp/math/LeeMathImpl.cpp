//
// Created by Li on 2017/9/10.
//

#include "LeeMathImpl.h"


LeeMath * LeeMath::pLeeMath = 0;

LeeMath *LeeMath::getIns() {
    if(pLeeMath == 0){
        pLeeMath = (LeeMath *) new LeeMathImpl();
    }

    return pLeeMath;
}

int LeeMathImpl::add(int a, int b) {
    return a + b;
}

int LeeMathImpl::minus(int a, int b) {
    return a - b;
}
