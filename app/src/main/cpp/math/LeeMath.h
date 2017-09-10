//
// Created by Li on 2017/9/10.
//

#ifndef LEEUTIL_LEEMATH_H
#define LEEUTIL_LEEMATH_H

class LeeMathImpl;
class LeeMath {

public:
    virtual int add(int a, int b){ return  0;};
    virtual int minus(int a, int b){return  0;};

public:
    static LeeMath * pLeeMath;

    // 这里使用了简单的单例，推荐使用双重锁单例
    static LeeMath * getIns() ;
};



#endif //LEEUTIL_LEEMATH_H
