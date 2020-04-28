#include<stdio.h>
#include<jni.h>
#include<time.h>
#include<CmdAgentImpl.h>
JNIEXPORT jobject JNICALL Java_CmdAgentImpl_C_1GetLocalTime
(JNIEnv* env, jobject thisObject, jobject Cmdobj){

    //jclass cls = (*env)->GetObjectClass(env, Cmdobj);
 //   jclass cls = (*env) -> FindClass(env, "GetLocalTime");

    //jint tyme;
//    jfieldID fid = (*env)->GetFieldID(env,cls,"tyme","I");
//    int cur_time = time(NULL);
 //   printf("time: %d\n", cur_time);
 //   (*env)->SetIntField(env,cls,fid,cur_time);
    //printf("%i\n",tyme);
    jclass cls = (*env)->FindClass(env,"GetLocalTime");
    jfieldID fid = (*env)->GetStaticFieldID(env,cls,"tyme","I");

    int cur_time = time(NULL);
    (*env)->SetStaticIntField(env,cls,fid,cur_time);

    fid = (*env)->GetStaticFieldID(env,cls,"valid","I");
    (*env)->SetStaticIntField(env,cls,fid,1);

    return Cmdobj;
}
JNIEXPORT jobject JNICALL Java_CmdAgentImpl_C_1GetLocalOS
  (JNIEnv* env, jobject thisObject, jobject Cmdobj){
    //char OS[16] = "Windows";
    jclass cls = (*env)->FindClass(env,"GetLocalOS");
    jfieldID fid = (*env)->GetStaticFieldID(env,cls,"version","I");
    (*env)->SetStaticIntField(env,cls,fid,1);

    return Cmdobj;
}

