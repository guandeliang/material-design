

### 前言

在Android应用开发中使用MotionLayout的地方越来越多，

为了能够说以简单明了的方式说清楚 MotionLayout，我总共准备了七个案例，

现在屏幕上打开的Activity，就是这个七个案例的入口

在开始讲解之前，先向大家展示一下这个几个案例的运行效果

现在看到的是Intro 案例，

Intro 案例用于介绍构成 Motion 动画的主要文件和要素

这个案例非常简单，只有一个可以运动的ImageView



Index 案例演示的是如何通过 KeyPosition 为每个View分配出场时间

以及如何使用 KeyAttribute 修改View的属性


Motion And Trigger 案例演示的是如何通过 KeyTrigger 在 Motion 动画过程中切入 Vector 动画


Mulit-Transition 案例演示了如何在一个 Motion动画中使用多个Transition，

并将这些 Transition 衔接起来，形成一个动画

KeyFrameSet 案例演示了在不改变 View 的位置情况下，

通过修改View的属性形成动画，

其中特别介绍了如何KeyCycle来定义View运动轨迹


CustomAttribute 案例演示了如何使用CustomAttribute

Details 案例演示了如何调试 MotionLayout，

以及如何使用各种运动轨迹、属性和坐标系，

其中也特别介绍了KeyCycle的使用



接下来，向让我们先看一下 Intro 案例，

因为了解了Motion动画基础知识之后，

其他几个案例就非常容易理解了

对于其他几个案例，

主要会介绍其实现思路，

以及案例中所使用到的知识点。

大家可以到我的 Github 仓库中下载这些案例的原始代码

在片尾可以看到 Github 仓库地址。

### intro案例

Intro 案例主要有三个文件构成，分别是

Activity 类文件、MotionLayout 布局文件，Scene 动画控制文件，

为了便于理解，我们将这个案例的代码拷贝到 PPT 中，并按照实现步骤，向大家逐步进行讲解。

在布局文件中，通过 layoutDescription 确定指定了与布局文件相关联的 Scene 动画控制文件

布局文件的用途是确定窗口所要使用到的View

并不是每一个View都会参与到动画过程中

对于那些始终静止不动的View，只需要在布局文件中确定位置就可以了。

Scene 动画控制文件的用途是确定有哪些View会参与到动画过程

以及这些View的变化规律


在本案例的布局文件中，总共只有两个View

一个是 ImageView，另一个是 Button


现在，无论是在 MotionLayout 布局中，还是 Scene 控制文件中

都没有为 ImageView 添加任何位置约束条件

按照 ConstraintLayout 布局原则，

没有任何约束条件的View会出现在窗口的左上角，

所有，我们现在我们看到的ImageView位于窗口的左上角上。


Button 是动画的控制按钮，其位置和属性不会随着动画过程发生任何变化

所以我们把他约束窗口底部的中间位置

现在，Scene动画控制文件只是一个空框架，没有添加任何实质内容

也就是不会产生任何动画效果

在 Scene 动画控制文件中，duration 属性定义的是以毫秒为单位的动画持续时间，

在这里定义的是1000毫秒，也就是1秒

constraintSetStart 属性定义的是动画开始点布局

也就是在动画第一帧中View的布局

constraintSetEnd属性定义的是动画结束点布局

也就是在动画的最后一帧布局View的布局


如果一个 View 既出现在 MotionLayout 布局文件中

也出现在 Scene 动画控制文件中

则优先采用 Scene 文件中定义的位置约束条件和属性

Scene动画文件中没有定义的位置约束条件，

则以 MotionLayout 布局文件中定义的为准

因为在后续步骤中，MotionLayout 布局不在发生变化

所以就不在幻灯片中继续显示 MotionLayout 布局文件内容了


因为现在在Scene 动画控制文件中还没有任何内容

所以点击按钮，还不会有任何反应


接下来，我们开始向Scene动画控制文件添加动画控制条件




在 Start ConstraintSet ，
也就是动画的起点

将 ImageView 约束在屏幕垂直居中靠左的位置

在End ConstraintSet ,
也就是动画的终点

将 ImageView 约束在屏幕垂直居中靠右的位置

当确定 ImageView 在动画的起点和终点的位置之后

两点之间形成的直线就是动画过程中 ImageView 的运动轨迹

到这里，一个 Motion 动画所需的基本条件已经全部具备了

剩下的就是如何开始播放这个动画

在Androind中，既可以通过从程序代码调用MotionLayout提供的方法来播放动画、

也可以在 Scene 动画控制文件中指定播放动画的触发条件

在这里使用按钮的点击事件作为播放动画的触发条件

OnClick 的用途就是指定产生点击事件的元件

也就是当点击 Button 的时候，开始播放动画


在点击 Button 的时候

动画可能在第一帧，也可能在最后一针，还可能是中间任何位置

Android 会根据目前动画播放进度，决定如何处理Button点击事件

在处于动画的第一帧的时候

点击Button，动画会从第一帧开始向后播放

当处于动画的最后一帧的时候

点击Button，动画会从最后一帧开始向前反向播放


当动画中间某一帧的时候

如果播放进度小于50%

动画会跳回到第一帧，然后开始向后播放

当播放进度大于50%

动画会跳回到第一帧并停止播放



除了定义起点和终点以外，

还可以通过定义关键帧的方式修改 ImageView 的运动轨迹、

以及运动过程中属性的变化

Motion 动画的关键帧全部都定义在 KeyFrameSet 标签中


目前 Motion 支持的关键帧类型有 KeyPosition、KeyAttribute、KeyCycle、KeyTrigger

在本案例中分别定义了一个 KeyPosition 和三个 KeyAttribute 类型关键帧


KeyPosition 的用途是指定在某一帧动画中，ImageView 所在的坐标位置

motionTarget 用于定义本KeyPosition所针对的 View Id

在 Motion 中，无论动画多么复杂，持续时间有多长

都只能定义99个关键帧，编号为1到99

当然这些关键帧在不同的关键帧类型中可以重复使用

framePosition 定义的是 KeyPosition 所在的关键帧编号


在这里指定的关键帧编号是50，也就是动画进行到一半的位置

keyPositionType 定义的是当前标签所使用的坐标系 

pathRelative 是一种与屏幕大小无关，只于起点和终点之间直线距离有关的直角坐标系

该坐标系以起点为原点，穿过起点和终点的直线为X轴

从起点到终点之间，X轴的刻度是从0到1

Y轴单位长度与 X 轴相同

X 轴以上的部分为负值，X轴以下的部分为正值

percentX 定义的是 ImageView 的 X 坐标位置

percentY 定义的是 ImageView 的 Y 坐标位置

通过 KeyPosition，确定了 ImageView 在第 50 个关键帧中所处的坐标位置

也就是 X 等于 0.5，Y 等于 -0.2 的位置。


在这个案例中，实际上定义了三个 KeyPosition

因为开始点相当于就是第一个关键帧，结束点相当于最后一个关键帧

通过这三个关键帧，为 ImageView 形成了一条中间凸起的运动曲线。



KeyAttribute 的用途是指定在某一个关键帧中，ImageView 的属性。

KeyAttribute 可以直接支持 alpha、rotation、scale、translation 等十几个定义动画常用的属性

除了这些动画常用属性以外，还可以通过 CustomerAttribute 标签向 KeyAttribute 添加自定义属性

要求是这些自定义属性必须支持get/set方法


在本案例中

我们分别在第1帧、第50帧和第99帧调整了 ImageView 的旋转角度

使 ImageView 看上去，似乎方向一直与运动路径保持垂直状态

编写本案例的主要目的是为了向大家介绍构成 Motion 动画的主要元素

接下来，让我们通过其他几个案例，进一步了解如何使用这些元素。




