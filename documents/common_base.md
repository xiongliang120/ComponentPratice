## 核心类说明

#### AppUtils
核心工具类，用于常用应用跳转等方法，如：
```java
AppUtils.launchApp(Context ctx, Intent intent)
```

其他方法有对应注释

#### BitmapUtils
对`Bitmap`操作处理的工具类，主要用于压缩，加载一些图片

#### DeviceUtils
获取设备信息相关的工具类，可以获取设备的`宽`和`高`，以及唯一的一个`udid`

```java
DeviceUtils.getDeviceID();
```

#### ImageUtils
对图片库`glide`进行了一次封装，用于加载网络图片

```java
ImageUtils.get().loadImage(mIvLogo, detailItem.images);
```

#### ResUtils
主要通过借助`AppCore`的全局`Context`来加载本地的相关资源，如：图片，字符串等等

```java
//获取字符串
ResUtils.getStringRes(R.string.remain_notify_times_format);
//获取字符串数组
ResUtils.getStringArrayRes(R.array.quizzes_tables);
//获取dimen适配数据
ResUtils.getDimenPixRes(R.dimen.px_48);
```

#### SPUtils
本地`SharedPreferences`的一个封装，主要用于对各种变量的`存储与读取`

#### ToastUtils
基于全局的`ApplicationContext`，封装了对`Toast`，相关的操作
