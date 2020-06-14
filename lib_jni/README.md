`生成so文件有两种方式，一种是ndk-build，一种是cmake，这里演示的是cmake`
1. 新建一个类JniGet，声明native方法，加载        System.loadLibrary("jni_test");

2. 到JniGet所在目录，打开底部命令行，使用javac编译成.class文件
> D:\Coding\Coding\Test\lib_jni\src\main\java\com\jqorz\jni>javac JniGet.java

3. 到JniGet上层的main/java目录，使用javah生成.class对应的.h文件
> D:\Coding\Coding\Test\lib_jni\src\main\java>javah com.jqorz.jni.JniGet

4.在main下新建jni目录，目录中新建main.cpp文件，把生成的`com_jqorz_jni_JniGet.h`文件内容拷贝进去

5.添加一些返回值，让函数返回一些字符串

6.在项目的Project Structure弹框中的SDK Location中指定NDK的文件夹路径

6.在module下的build.gradle中添加
```
android {
    defaultConfig {
        externalNativeBuild {
            cmake {
                cppFlags ""
                abiFilters "arm64-v8a", "armeabi-v7a", "x86"
            }
        }
    }
    externalNativeBuild {
        cmake {
            path 'CMakeLists.txt'
        }
    }
}
```
7.在module的build.gradle同级目录下新建CMakeLists.txt

8.build项目，so文件会生成在`Test\lib_jni\build\intermediates`下面，默认是`build\intermediates\stripped_native_libs\debug\out\lib`下面

9.如果想使用so，拷贝生成的`libjni_test.so`到`main\jniLibs`目录下面，然后把`build.gradle`中修改的注释掉