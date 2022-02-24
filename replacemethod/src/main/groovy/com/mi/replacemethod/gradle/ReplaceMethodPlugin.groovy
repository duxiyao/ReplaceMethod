package com.mi.replacemethod.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.invocation.Gradle

/**
 * https://blog.bihe0832.com/gradle_plugin_summary.html
 * gradle api https://docs.gradle.org/current/javadoc/org/gradle/api/Project.html
 * [Android Gradle Transform使用](https://blog.csdn.net/qq_15827013/article/details/98076409?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~aggregatepage~first_rank_ecpm_v1~rank_v31_ecpm-2-98076409.pc_agg_new_rank&utm_term=Android+Transform+%E8%B0%83%E8%AF%95&spm=1000.2123.3001.4430)
 * [Gradle 调试Transform代码](https://www.jianshu.com/p/91a43ef3d682)
 *
 * debug插件时候，需要配置环境变量 GRADLE_OPTS = -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
 * 然后右侧gradle 的tasks里右键build 选择debug即可
 * 插件类
 */
class ReplaceMethodPlugin implements Plugin<Project> {
    void apply(Project project) {
        //创建任务 测试断点调试
        //参考链接 https://blog.csdn.net/u014788540/article/details/116243489  https://www.jianshu.com/p/99c8e953654e
        project.task("testdxy1") {
            doLast {
                println '*****************testdxy*********************'
            }
        }

        //获得启动任务字符串
        Gradle gradle = project.getGradle()
        String tskReqStr = gradle.getStartParameter().getTaskRequests().toString()
        project.extensions.create("replaceMethod", Config)
        boolean isRelease = tskReqStr.contains("Release")
        println '*****************replacemethod Plugin apply*********************'

        //println '111------'+tskReqStr
        //release  111------[DefaultTaskExecutionRequest{args=[:app:assembleRelease],projectPath='null'}]
        //debug    111------[DefaultTaskExecutionRequest{args=[:replacemethod:assemble, :replacemethod:testClasses, :app:assembleDebug],projectPath='null'}]
        if (project.plugins.hasPlugin(AppPlugin)) {
            def android = project.extensions.findByType(AppExtension)
            android.registerTransform(new ReplaceMethodTransform(project, isRelease))
        } else if (project.plugins.hasPlugin(LibraryPlugin)) {
            def android = project.extensions.findByType(LibraryExtension)
            android.registerTransform(new ReplaceMethodTransform(project, isRelease))
        }
    }
}