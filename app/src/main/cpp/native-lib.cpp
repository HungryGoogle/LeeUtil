#include <jni.h>
#include <android/log.h>
#include "./math/LeeMath.h"
#include <iostream>
#include <string>

using namespace std;


#define LOG_TAG "leeTest------>"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

char *Jstring2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("GB2312");
    jmethodID method = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, method, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);

    if (alen > 0) {
        rtn = new char[alen + 1];         //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }

    env->ReleaseByteArrayElements(barr, ba, 0);  //
    return rtn;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_callMyMathAdd(JNIEnv *env, jobject instance,
                                                               jint a, jint b) {
    return LeeMath::getIns()->add(a, b);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_cCallJavaStaticFunc(JNIEnv *env,
                                                                     jobject instance) {

    //通过反射来调用java的方法，需要知道方法签名，使用javap得到方法签名
    //在bin/classes目录下执行 javap -s 全类名
    //1、得到类的字节码对象
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass clazz = env->FindClass("com/example/li/leeutil/jni/FirstJniActivity");
    if (clazz == 0) {
        LOGI("find class error");
        return;
    }
    LOGI("find class ");

    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID methodID = env->GetStaticMethodID(clazz, "staticShow", "(Ljava/lang/String;)V");
    if (methodID == 0) {
        LOGI("find static method error");
        return;
    }
    LOGI("find static method ");

    //void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
    env->CallVoidMethod(instance, methodID, env->NewStringUTF("这是C调用static func 传递过来的弹窗内容"));

}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_cCallJavaFunc(JNIEnv *env, jobject instance) {

    //通过反射来调用java的方法，需要知道方法签名，使用javap得到方法签名
    //在bin/classes目录下执行 javap -s 全类名
    //1、得到类的字节码对象
    //jclass      (*FindClass)(JNIEnv*, const char*);
    jclass clazz = env->FindClass("com/example/li/leeutil/jni/FirstJniActivity");
    if (clazz == 0) {
        LOGI("find class error");
        return;
    }
    LOGI("find class ");

    //jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
    jmethodID methodID = env->GetMethodID(clazz, "show", "(Ljava/lang/String;)V");
    if (methodID == 0) {
        LOGI("find method error");
        return;
    }
    LOGI("find method ");

    //void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
    env->CallVoidMethod(instance, methodID, env->NewStringUTF("这是C传递过来的弹窗内容"));
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_cLog(JNIEnv *env, jobject instance) {

    // 调用android打印log输出，在logcat里面
    LOGI("leeTest 我是C语言打印的info日志");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_encodeArray(JNIEnv *env, jobject instance,
                                                             jintArray arr_) {
    jint *arr = env->GetIntArrayElements(arr_, NULL);
    int length = env->GetArrayLength(arr_);
    for (int i = 0; i < length; i++) {
        *(arr + i) += 10; // 方案1： 将数组中的每个元素加10 测试ok
        // arr[i] += 10;  // 方案2： 将数组中的每个元素加10 测试ok
    }

    env->ReleaseIntArrayElements(arr_, arr, 0);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_encode(JNIEnv *env, jobject instance, jstring str_,
                                                        jint strLength) {
    const char *str = env->GetStringUTFChars(str_, 0);
//    cout<< "src striing : "<<str<<endl;
    env->ReleaseStringUTFChars(str_, str);

    char *cstr = Jstring2CStr(env, str_);
    int i;
    for (i = 0; i < strLength; i++) {
        *(cstr + i) += 1; //加密算法，将字符串每个字符加1
    }
    cout << "encode string : " << cstr << endl;
    return env->NewStringUTF(cstr);

//    std::string encode = "encode";
//    return env->NewStringUTF(encode.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_decode(JNIEnv *env, jobject instance, jstring str_,
                                                        jint strLength) {
    const char *str = env->GetStringUTFChars(str_, 0);
    char *cstr = Jstring2CStr(env, str_);
    int i;
    for (i = 0; i < strLength; i++) {
        *(cstr + i) -= 1;
    }

    env->ReleaseStringUTFChars(str_, str);
    return env->NewStringUTF(cstr);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_testInt(JNIEnv *env, jobject instance,
                                                         jint strLength) {
    cout << "leeTest----->" << strLength << endl;
    std::string hello = "testInt";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

