Best Pattern of Android App Launch Screen Animation (Splash Screen or Welcome Screen)


通过两个案例的对比，详细说明如何优化应用启动过程
在本案例中，结合了Thread、MotionLayout、Animated Vector等技术
实现了一个流畅优雅的App Launch Screen

Through the comparison of the two cases, the video explains how to optimize the application startup process in detail
In the case, Thread, MotionLayout, Animated Vector and other technologies are combined
Achieve a smooth and elegant App Launch Screen.



> 001 


这不仅是一个重度依赖电子设备的时代，

也是一个快文化的时代。

大多数手机用户都缺乏足够的耐心去学习如何使用一个新的App。

即使是一个已经被用户使用习惯的App，

除非用户必须使用它，并且没有可以替代的产品

否则一旦用户体验出了问题，就可能马上被用户抛弃，

转向使用其他的产品。

所以，设计和改善一个App的用户体验非常重要。

今天要给大家分享的就是如何改进App启动过程的用户体验。


> 002

为了让大家对用户体验有一个直观的感受，

我编写了两个案例。

这两个案例无论是功能，还是代码，都非常接近

但是它们给用户带来的体验却有很大差别

为了方便后面的叙述，我们先给这两个案例各取一个名字

一个叫做 T，是糟糕这个单词的开头字母

另一个叫做 E，是优雅这个单词的开头字母

T 给用户的带来的感受非常糟糕

E 则会让用户感觉非常舒适、优雅

在发布这个视频之前，我已经把这两个案例推送到的Github仓库里，

在视频的末尾，可以看到Github仓库地址。

> 003

现在，在Android Studio项目导航区中被展开的就是T和E两个案例

这两个案例也已经全部安装到了我的手机中

在我的手机屏幕上可以看到三个应用

中间的一个是 T, 右边的一个是 E

现在我的手机和 Android Sutidio 也处于连接状态

从 Logcat 中可以看到两个案例的运行日志。

为了方便看到他们启动过程所消耗的时间，

先给 Logcat 输入一个过滤条件

在Android 4.4 及以上版本中，

Logcat会输出一行带有 Displayed 关键字的信息。

这行信息提供了从 App 开始启动到第一个 Activity 窗口显示之前所消耗的时间。

> 004

现在 Android Studio 调试环境已经准备好，可以开始运行这两个案例。

第一个运行的是 T，大家注意仔细观察这个案例的启动过程。

> 005

从视频中可以看到，在 T 的启动过程中出现了一个持续时间很长的空白窗口。

除空白窗口外，T 的启动动画和 Activity 过渡动画也非常呆板。

现在在 Logcat 中被选中的部分，显示的是 T 启动过程所消耗的时间

大约在2秒半左右。我没有说具体时间，是因为我录音的时候还没有录制屏幕，

所以看不到具体时间，只能说个大概。

> 006

再让我们看一下 E 的启动过程。

很明显，E 的启动过程要比 T 流畅很多

也让我们看一下 E 启动过程所消耗的时间

通过对比，会发现 E 的启动过程要比 T 快了大概一秒钟。

> 007

其实这两个例子的启动过程没有什么本质区别，

都可以划分为六个步骤，但是每一步都会有一些细微差别。

现在屏幕上显示的是 T 和 E 启动过程中每一步的对比。



第一步是在点击启动图标之后，到出现满屏窗口之前。

在这一步，

T 显示的是一个逐渐扩张的空白窗口

E 显示的是一个逐渐扩张的，带有背景图的窗口

> 009 

第二步是从窗口满屏之后，到启动动画开始之前。

在这一步，T 显示的是一个空白窗口

E 显示的是一个带有背景图的窗口。

> 010
 
第三步是从启动动画开始到启动动画结束

在这一步，T 和 E 没有特别大的区别

只是因为实现动画所使用的技术不同，

导致 T 效果不如 E 活泼流畅

> 011

第四步是一个短暂的屏幕静止时间

提供这一步的目的是为了让用户能够看清楚屏幕上显示的内容

> 012

第五步的作用是完成从启动窗口到功能窗口之间的切换

T 的切换方式比较突兀，E 的切换方式比较自然

> 013

最后一步，也就是功能窗口显示之后

在这一步两个案例之间没有任何分别

实际上，这一步还可以继续进行优化，让内容加载过程变得更为人性化

但是因为这部分内容不是本视频的重点

所以就没有进行处理。

> 014

大家注意，在第二步和第三步之间有一条红线

这条红线是启动窗口被显示到屏幕上的时间点

在这条红线之前，屏幕上显示的并不是启动窗口

而是系统创建的一个空白窗口。

在前面，Logcat 中显示的时间，就是这条红线的时间

大家还记得，T 要比 E 慢大概一秒钟


> 015-------------------

在查看这两个案例的实现代码之前，先让我们了解一下与 App 启动过程相关的知识

了解这些知识之后，更容易明白造成 T 和 E 之间差异的原因，

也能够更清楚开发 App 启动界面需要注意的事项。


> 016


现在屏幕上看到的 1 到 6 分别对应的是前面我们所说的，App 启动过程的 6 个步骤

中间的红线依然是 Displayed 时间，也就是启动窗口真正被显示到屏幕上的时间。

> 017

当点击屏幕上的启动图标之后

Android Runtime 首先会分析应用的 AndroidManifest.xml 配置文件，

并从中找到应用的启动窗口以及启动窗口所使用的主题。

然后会创建一个与应用启动窗口相同主题的空白窗口。

T 的启动窗口主题背景是白色，所以启动的时候看到的是一个白色的窗口。

E 的启动窗口主题背景是一张图片，所以启动的时候看到的是一个使用图片为背景的窗口。

> 019

Android Runtime 在创建并显示空白窗口之后，马上开始创建 Application 对象

Application 对象的 onCreate 方法是启动过程必须经过的一个环节

如果在这个方法中执行任何耗时的工作，都会导致应用启动过程变慢，成为性能瓶颈

在实际开发中，经常有开发者在 Application 对象的 onCreate 方法添加一些与应用初始化有关的代码，

比如用于处理 GPS、SIM 卡信息，用户 token 信息的代码。

Android 给开发者的建议是，尽量不要继承和改变 Application 的默认执行过程

更不要在 Application 对象的 onCreate 方法中执行任何耗时的任务。

任何初始化工作都可以通过懒加载的方式转移到应用的其他部分进行，

只要在这些信息必须使用之前完成就可以。


出于演示需要，我在两个案例 Application 对象的 onCreate 方法中各增加了一行代码，

用来模拟一个耗时任务。

> 021

在 Application 对象的 onCreate 方法执行完成之后，开始创建和初始化启动 Activity 对象

Activity 对象中的 onCreate 方法也是一个必须经过的环节。

也就是说，这个方法也是一个容易成为性能瓶颈的地方

在这个方法中，主要完成两项工作

一个是窗口布局的加载和渲染工作

另一个是本 Activity 需要使用到的数据的初始化工作.

如果布局过于复杂或者数据初始化工作过于耗时，

都会导致启动过程变慢，界面显示不够流畅等问题。

就是因为在两个案例中，我们采用了不同的方式来时实现启动 Activity 的 onCreate 方法

才导致 E 比 T 的启动过程快了一秒多。

稍后我们会结合代码解释他们之间的差异。

> 022

在启动动画结束之后，两个案例都暂停了一秒钟

然后才开始进行 Activity 切换。

T 采用的是默认的切换动画，E 采用的是自定义的切换动画

使用不同的切换动画，给用户带来的体验也会不同，

这就是E的切换过程看上去比较自然的原因。


> 023

前面我们完成了两个案例的演示，并介绍了产生他们之间差异的原因。

接下来，让我们结合实际代码，进一步了解这两个案例实现方式上一些细节区别。


> 024

让我们把两个案例的AndroidManifest.xml文件放在一起做一个对比

左边是 T 案例的 AndroidManifest.xml 文件，右边是 E 案例的 AndroidManifest.xml 文件

可以看出，这两个文件一模一样, 没有任何区别

Android Runtime 就是通过这两个文件找到Lanuch Activity，

并通过Launch Activity 确定其所创建的空白窗口主题的。

在这里可以看到，两个Launch Activity 主题都指向了 AppTheme.Launch

接下来，让我们看一下，两个 AppTheme.Launch 之间有什么区别

> 025


可以看出，两个 AppTheme.Launch 唯一区别就是 window Background item 的值

T 案例中指向的是 color background 颜色资源

E 案例中指向的是 launch bg 001 图片资源

window Translucent Status item的作用是将状态栏设置为透明色

这样，window Background 就可以延伸到状态栏中。

navigation Bar Color item 的作用是把导航栏背景色设置为 color background 颜色资源。

因为 AppTheme.Launch 既是Launch Activity 窗口主题，也是系统创建的空白窗口的主题

所以我们要分别说一下 AppTheme.Launch 在每一个窗口中的所其作用。

对于 T 案例中系统创建的空白窗口来说

因为状态栏、窗口背景、导航栏都是浅灰色，

所以我们看到的是一个浅灰色的空白窗口。

对于 T 案例中的Launch Activity 来说，状态栏、窗口背景会被 Activity 布局中的内容所遮盖

导航栏继续保持浅灰色

所以我们看到的是一个上半部分能够侵入状态栏

下半部分却无法侵入导航栏的动画。

对于 E 案例中系统创建的空白窗口来说

因为状态栏、窗口背景都被图片覆盖

虽然导航栏是灰色，但是与图片的下半颜色相同

所以我们看到的是一个被图片填满的窗口。


对于 E 案例中的Launch Activity 来说

状态栏、窗口背景会被 Activity 布局内容所遮盖

导航栏继续保持浅灰色

启动动画也通过代码设置侵入了导航栏区域

所以我们看到的是完整的动画

另外在 E 案例中，空白窗体的背景图与Launch Activity 动画的第一帧画面完全相同

所以用户无法感觉到发生了窗体切换。

> 026

现在就让我们看一下 E 案例中的背景图片和导航动画

这个案例中，背景图片是一张静态 Vector 矢量图

导航动画是一个 Animated Vector 矢量图

在导航动画中直接引用的就是静态的 Vector 矢量图

关于 Vector 和 Animated Vector 需要讲解的内容很多，

我会单独录制两个视频进行说明

在这里就不在详细叙述

> 027


接下来要说的是 Application 类。

两个例子的 Application 类完全相同

SystemClock.sleep 的在这里的除了用来模拟一个耗时任务以外，没有任何其他用途

严格来说，Android 开发规范不建议在 Application 中执行任何耗时任务

但是在实际项目开发中，经常出现在 Application 中执行各种代码的现象

比如应用初始化、获取 GPS 硬件信息等。

如果必须在 Application 中执行耗时任务，可以参考本案例的解决方案，来改善用户体验问题。

在 Application 中执行任务还有一个问题

就是几乎无法通过多线程来回避占用主线程时间的问题

因为如果在主线程以外的辅助线程中执行任务，

一旦主线程已经执行到 Application 以外，很难在跟踪辅助线程任务状态。

> 029

在 Application 对象的 onCreate 方法执行结束之后

是创建Launch Activity 对象，并执行该对象的 onCreate 方法

首先我们看一下 T 案例的Launch Activity

在这个 Activity 对象的 onCreate 方法中

先是通过 Data Binding Util 帮助类来绑定布局文件

本 Activity 对象采用的是 MotionLayout, 也就是动态效果布局。

在绑定布局文件之后，是为 MotionLayout 设置动画监听器


anyTask方法的作用模拟了一个耗时任务。

在anyTask方法中，首先将主线程暂停一秒钟

然后，在life Cycle范围内启动 Motion 动画。

将 Motion 动画的启动工作控制在 life Cycle 范围内，并不是出于什么技术目的，

而是因为在我编写这段代码的时候，恰好 MotionLayout 所发布的版本出了一个Bug

导致无法直接在主线程中启动动画，只能通过这种方式来绕过这个Bug。

到这里，所有需要在 onCreate 方法执行的代码已经执行完毕。

因为在 Application 对象的 onCreate 方法和 Activity对象的 onCreate 方法中

各暂停了一秒钟，所以 T 案例的启动时间总共消耗了两秒多钟。


> 030

当 Motion Listener 监听到动画结束之后

会再次将主线程暂停一秒钟

然后调用 showMainActivity 方法。

在 showMainActivity 方法中完成 Activity 的切换工作。

> 31


到这里我们已经全部介绍完 T 案例的启动过程。

接下来看一下 T 案例的Launch Activity 布局文件

这个布局文件的根布局采用的是 MotionLayout。

MotionLayout 是约束布局的子类，

关于约束布局和 MotionLayout 我会各单独录制一个视频进行讲解。

在这里不再详细解释

在这个布局中，包含了蓝、白、灰三张背景图片，以及一张 Logo 图片和一行标题

我们看一下通过这些图片和文字构成的动画效果


其实 Motion 动画是一种非常耗费资源的动画技术

比较老的手机运行起来，会有明显的卡顿现象。

T 案例我们就介绍到这里，接下里要看的是 E 案例的Launch Activity。

> 32

在 E 案例的Launch Activity 对象的 onCreate 方法中

首先也是绑定布局文件

接下里一段代码的作用是隐藏导航栏

隐藏导航栏的目的是为了让 Vector 动画能够侵入到导航栏中

否则动画的第一帧图片无法与主题中设置的窗口背景图片完全重叠。


然后是为 MotionLayout 和 Vector 动画设置监听器

anyTask方法的作用依然是用来模拟一个耗时任务

但是该方法把耗时任务放在了单独的线程中进行执行

所以没有占用主线程时间

这也是 E 案例启动过程比 T 案例启动过程快了一秒钟的原因


MotionLayout 动画的启动工作放在了 Vector 动画结束之后

通过这种方式，让两个动画无缝衔接，成为一体。

因为在耗时任务和动画结束之后，都会调用到 showMainActivity 方法

所以对 showMainActivity 方法进行了线程同步控制，以避免被多次执行

在 showMainActivity 方法中，override Pending Transition方法的作用是为Activity设置切换动画

通过这个切换动画，让 E 案例的 Activity 切换过程变得更为自然。

E 案例的启动过程就介绍到这里

最后让我们看一下 E 案例的Launch Activity 布局文件。

> 33

因为部分动画效果被转移到 Vector 动画中

所以 E 案例的Launch Activity 布局文件要比 T 案例d的布局文件简单得多

在这个布局中，只有一个 Vector 动画和被拆成两部分的标题文字

让我们看一下这个布局文件的动画效果


大家会发现，动的只要标题文字，背景一直是静止不动的

这是因为 MotionLayout 动画设计工具只能预览 MotionLayout 动画，不能预览 Vector 动画

> 34

本视频就到这里，近期会发布四个视频

分别用来介绍：约束布局、MotionLayout 、 Vector 以及 Vector 动画

在片尾可以看到Github仓库地址，

在Github仓库中可以下载本系列视频的全部代码

