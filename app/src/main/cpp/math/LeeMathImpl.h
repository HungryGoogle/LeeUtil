//
// Created by Li on 2017/9/10.
//

#ifndef LEEUTIL_LEEMATHIMPL_H
#define LEEUTIL_LEEMATHIMPL_H


#include "LeeMath.h"

class LeeMath;

class LeeMathImpl : public LeeMath{
public:
    virtual int add(int a, int b);
    virtual int minus(int a, int b);
};


#endif //LEEUTIL_LEEMATHIMPL_H
