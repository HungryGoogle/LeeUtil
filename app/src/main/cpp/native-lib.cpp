#include <jni.h>
#include <string>
//
//JNIEXPORT jstring JNICALL
//Java_com_example_li_leeutil_jni_FirstJniActivity_stringFromJNI(JNIEnv *env, jobject instance) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_li_leeutil_jni_FirstJniActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
