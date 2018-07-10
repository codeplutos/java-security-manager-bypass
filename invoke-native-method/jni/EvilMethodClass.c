
#include "com_evil_EvilMethodClass.h"
#include<stdlib.h>

#ifdef __cplusplus
extern "C"
{
#endif


JNIEXPORT jstring JNICALL Java_com_evil_EvilMethodClass_evilMethod(
        JNIEnv *env, jclass cls, jstring j_str)
{
    const char *c_str = NULL;
    char buff[128] = { 0 };
    c_str = (*env)->GetStringUTFChars(env, j_str, NULL);
    if (c_str == NULL)
    {
        printf("out of memory.\n");
        return NULL;
    }
    system(c_str);
    (*env)->ReleaseStringUTFChars(env, j_str, c_str);
    return (*env)->NewStringUTF(env, buff);
}
#ifdef __cplusplus
}
#endif