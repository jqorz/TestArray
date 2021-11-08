/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <string>
#include<android/log.h>
/* Header for class com_jqorz_jni_JniGet */

#ifndef _Included_com_jqorz_jni_JniGet
#define _Included_com_jqorz_jni_JniGet
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_jqorz_jni_JniGet
 * Method:    getHello
 * Signature: ()Ljava/lang/String;
 */

#define TAG "jqjq-jni" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型

static const char *className = "com/jqorz/jni/JniGet";

JNIEXPORT jstring JNICALL Java_com_jqorz_jni_JniGet_getHello
        (JNIEnv *env, jclass) {
    //获取类
    jclass clazz = (env)->FindClass(className);
    //获取类构造方法id
    jmethodID jmethod1 = (env)->GetMethodID(clazz, "<init>", "()V");
    //通过构造方法id获取类实例
    jobject jobject1 = (env)->NewObject(clazz, jmethod1);

    //获取想调用的方法id
    jmethodID jmethod2 = (env)->GetMethodID(clazz, "getNum1", "()I");
    //通过实例和方法id调用方法
    jint num1 = (env)->CallIntMethod(jobject1, jmethod2);

    //获取想调用的方法
    jmethodID jmethod3 = (env)->GetMethodID(clazz, "getNum2", "()I");
    //调用方法
    jint num2 = (env)->CallIntMethod(jobject1, jmethod3);

    char buf[64];
    sprintf(buf, "%d", num1 + num2);

    env->DeleteLocalRef(clazz);
    env->DeleteLocalRef(jobject1);

    return (*env).NewStringUTF(buf);
}
typedef struct {
    jstring _stuName;
} JniBean;
JNIEXPORT void JNICALL
Java_com_jqorz_jni_JniGet_getListFromJava(JNIEnv *env, jclass) {

    //获取类
    jclass aClass = (env)->FindClass(className);
    //获取想调用的方法id
    jmethodID _midGetDataList = (env)->GetStaticMethodID(aClass, "getDataList",
                                                         "(Z)Ljava/util/List;");
    int sb =1;
    //通过实例和方法id调用方法
    jobject _obejctList = (env)->CallStaticObjectMethod(aClass, _midGetDataList,sb);

    int i;
    //class ArrayList
    jclass cls_arraylist = env->GetObjectClass(_obejctList);
    //method in class ArrayList
    jmethodID arraylist_get = env->GetMethodID(cls_arraylist, "get", "(I)Ljava/lang/Object;");
    jmethodID arraylist_size = env->GetMethodID(cls_arraylist, "size", "()I");
    jint len = env->CallIntMethod(_obejctList, arraylist_size);
    LOGI("get java ArrayList<User> object by C++ , then print it...../n");

    for (i = 0; i < len; i++) {
        jobject obj_user = env->CallObjectMethod(_obejctList, arraylist_get, i);
        jclass cls_user = env->GetObjectClass(obj_user);
        jmethodID user_getUserName = env->GetMethodID(cls_user, "getStuName","()Ljava/lang/String;");
        jmethodID user_getAge = env->GetMethodID(cls_user, "getAge","()I");
        auto name = (jstring) env->CallObjectMethod(obj_user, user_getUserName);
        jint age =env->CallIntMethod(obj_user,user_getAge);
        jboolean b = true;
        const char *namePtr = env->GetStringUTFChars(name, &b);
        LOGI("Name:name =%s, age = %d ", namePtr,age);
    }


    env->DeleteLocalRef(aClass);
    env->DeleteLocalRef(_obejctList);
}

#ifdef __cplusplus
}
#endif
#endif