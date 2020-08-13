### 1、Shortcut



使用Shortcut可以帮助用户快速开启App的某一项功能

比如快速回复邮件

与经常联系的朋友进行通话

从上一次保存的地方加载游戏

在Android中可以定义三种类型的Shortcut

分别是静态Shortcut、动态Shortcut和固定Shortcut

本视频主要讲解的是在Android 11中如何使用动态Shortcut

内容包括

如何创建动态Shortcut

以及如何接收来自动态Shortcut的请求


在开始讲解代码之前，让我们先看一下案例演示


在App刚刚运行的时候，Shortcut还没有创建

这时候长按启动图标，只会看到应用信息链接，不会看到其他链接

在创建Shortcut之后，再长按启动图标

除了应用信息链接以外，还可以看到五个链接

这五个链接就是刚刚创建的Shortcut

点击其中一个，应用会被启动，并且接收到请求信息

当不需要Shortcut的时候，可以将他们删除

删除之后，就不会再看到Shortcut链接

让我们把Shortcut重新出来，然后结合界面介绍实际代码





ShortcutManager是一个系统服务

该对象负责Shortcut的创建、保存和删除服务，

在使用Shortcut之前，首先好获取该服务


CONTACTS是一个联系人列表

在代码中，通过循环这个联系人列表创建了四个Shortcut

Person对象是一个可选项，并没有任何实际用途

提供Person对象，只是为了表现业务含义

并不会改变功能

Intent是Shortcut的核心

其负责接收和分配Shortcut发出的请求

在本案例中，Shortcut会被分配给NotificationActivity类

Activity的handleIntent方法负责接收和分析请求内容

在handleIntent方法中

首先会判断Action类型

然后会分析数据中的Uri地址

根据Uri地址中包含的id寻找到联系人

并将联系人信息显示到界面上


ShortcutInfo对象负责统一管理Shortcut信息

setLongLived方法用来指定是否对Shortcut信息进行缓存
ShortLabel、LongLabel、Icon分别是Shortcut标题和图标
setCategories方法用来对Shortcut进行分类
setIntent方法则是用来指定如何处理Shortcut发出的请求


关于动态Shortcut我们就介绍到这里

接下来要介绍的是Notification









