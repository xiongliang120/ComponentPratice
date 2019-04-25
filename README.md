##概述
组件化项目实践

##架构图
![](documents\images\架构图.png)

## 环境配置
gradle 插件版本:
```
classpath 'com.android.tools.build:gradle:3.0.1'
```

gradle 版本:
```
gradle-4.4-all
```

发布正式版本配置:
```
//是否开启测试环境
MODE_DEBUG       : true,
//是否开启日志输出
MODE_LOGGER      : true,
//是否打市场输出包(此标记=true,则上述自动为正式环境,关闭日志输出)
MODE_MARKET      : false
```

##组件切换说明
在需要对业务进行独立开发的情况下，可能需要切换成标准的`application`运行
在单独的组件目录下，有一个名为`gradle.properties`配置文件
```
isModule = true
````

##组件说明

###组件配置文档
第一步:
以`module_article`为例,修改项目根目录下`gradle.properties`文件`
```
isModule = true
```

第二步:
在`src/main` 目录下, 新建`module`文件夹,用于存放`application模式`的清单文件
![](documents\images\图片20190425175950.png)

完成以上两个步骤,基本完成了组件的配置

### [common-base](documents\common_base.md)
作为项目最核心的基本组件，包含一些核心类`Appcore`的初始化,以及部分核心的工具类
















