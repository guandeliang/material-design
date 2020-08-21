### 1、Shortcut



Android 11：002_2、shortcut、Notification和Bubble（02）

Android 11的主要关注点是People、Controls、和Privacy。
shortcut、Notification和Bubble则是该理念的具体提现方式。
我讲提供三个视频分别详细介绍在实际开发中如何使用这三项功能




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





基于People、Controls、和Privacy理念


Android 11 对 Notification 的功能和外观都进行了非常大的调整


全新的Notification服务除了具备通知功能以外

还可以直接与APP之间进行对话

闲话少说

我们直接结合代码和案例演示

来讲解如何使用全新的Notification

首先，让我们执行案例APP，并创建一个Notification



NotificationManager是负责Notification通道管理以及Notification发送的系统服务

在使用Notification之前，首先要获取NotificationManager服务

在得到NotificationManager服务之后，

发送Notification之前

需要先创建Notification通道

从Android 8开始，所有的消息都必须分配到对应的通道

同一个通道的消息，又可以进一步进行分组管理

在本案例中，首先会判断通道知否已经创建

如果没有创建，则会创建一个新的通道给消息使用

MessagingStyle负责控制Notification的外观

每个Notification可以添加多条消息

在本案例中，系统自动添加了两条消息

setGroupConversation方法用来确定是否按照消息的发送人对消息进行分组

sender是消息发送者

Message是消息的内容

Android 11与以前的版本相比

更加突出了消息发送者图标和名称

当存在多条消息的时候，每条消息占据一个段落

如果消息条数过多，会自动隐藏早期发送的消息


Action用来接收用户对消息的反馈

在本案例中，用户反馈的消息会被发送到NotificationReplyReceiver对象

当用户点击Reply按钮之后，会显示一个输入框

用来接收用户的输入

用户的输入信息以及一个用来标记用户的URI地址会同时传递到NotificationReplyReceiver中

在NotificationReplyReceiver中对URI地址和输入信息进行分析之后

自动给出一个回复信息



在Notification中，除了接收用户的回复以外

还可以处理用户的点击事件

在这里，我们对点击事件采用了和Shortcut相同的处理方式

也是传递给了Activity

由Activity的handleIntent进行处理

当消息创建完之后，就可以通过NotificationManager发送出去

关于Notification就介绍到这里

接下来要介绍的是如何将Shortcut、Notification、Bubble组合在一起使用





在前两个视频中，我们分别介绍了Shortcut和Notification

接下来要介绍的是如何将Notification融入到Shortcut中

在详细介绍之前，让我们先看一下演示案例

在这个演示案例中

前面两个按钮是关于Shortcut的演示

第三个按钮是关于Notification的演示

在这里就不在重复介绍

大家会注意到，与前一个演示相比

Notification内容框的右下角多了一个图标

这是因为在这个Notification绑定了Bubble

点击图标最后，会显示一个半屏的窗口

这个半屏窗口就是与BubbleMetadata绑定在一起的Activity

需要注意的是

Bubble并不能代替应用启动器

Bubble只是一种能够引起用户注意的，

帮助用户快速返回到应用某项功能的途径


因为在本案例中，Notification上不仅绑定了

Bubbule，还绑定了Shortcut

所以，在发送消息的时候，

在应用启动器的Shortcut上，也会看到Notificaiton





