Android MotionLayout Case Study and Introduction to the Basic

This is the first in a series of MotionLayout videos. 
In this series of videos, you'll learn more about how to use MotionLayout ：
1. MotionLayout Basic Knowledge 
1. Five very beautiful cases 
1. Scene File Structure 
1. StartConstraintSet and EndConstraintSet 
1. KeyPosition and KeyAttribute 
1. Coordinate system



### 前言

MotionLayout 在 Android 应用开发中用到的次数越来越多，

为了能够以简单明了的方式说清楚 MotionLayout，

我为这个视频准备了七个案例，

现在屏幕上打开的两个 Activity，就是这七个案例的入口

在开始讲解之前，先向大家展示一下这个几个案例的运行效果

现在看到的是 Intro 案例，

Intro 案例用于介绍构成 Motion 动画的所必须文件和主要元素

这个案例非常简单，只有一个可以运动的ImageView



Index 案例演示的是如何通过 KeyPosition 为每个View分配出场时间

以及如何使用 KeyAttribute 修改View的属性


Motion And Trigger 案例演示的是如何通过 KeyTrigger 在 Motion 动画过程中切入 Vector 动画


Mulit-Transition 案例演示了如何在一个 Motion动画中使用多个Transition，

并将这些 Transition 衔接起来，形成一个动画

KeyFrameSet 案例演示了在不改变 View 的位置情况下，通过修改View的属性形成动画，

其中特别介绍了如何使用KeyCycle来定义View运动轨迹


CustomAttribute 案例演示了如何使用CustomAttribute

Details 案例演示了如何调试 MotionLayout，

以及如何使用各种运动轨迹、属性和坐标系，

其中也特别介绍了KeyCycle的使用



接下来，向让我们先看一下 Intro 案例，

因为了解了Motion动画基础知识之后，

其他几个案例就非常容易理解了

对于其他几个案例，主要会介绍其实现思路，以及案例中所使用到的知识点。

大家可以到我的 Github 仓库中下载这些案例的原始代码

在片尾可以看到 Github 仓库地址。

### intro案例

Intro 案例主要有三个文件构成，分别是Activity 类文件、MotionLayout 布局文件，Scene 动画控制文件，

为了便于理解，我们将这个案例的代码拷贝到 PPT 中，并按照实现步骤，向大家逐步进行讲解。

在布局文件中，通过 layoutDescription 指定了与布局文件相关联的 Scene 动画控制文件

布局文件的用途是确定窗口所要使用到的View, 并不是每一个View都会参与到动画过程中

对于那些始终静止不动的View，只需要在布局文件中确定位置就可以了。

Scene 动画控制文件的用途是确定有哪些View会参与到动画过程,以及这些View的变化规律


在本案例的布局文件中，总共只有两个View,一个是 ImageView，另一个是 Button


现在，无论是在 MotionLayout 布局中，还是 Scene 控制文件中,都没有为 ImageView 添加任何位置约束条件

按照 ConstraintLayout 布局原则，没有任何约束条件的View会出现在窗口的左上角，

所有，我们现在我们看到的ImageView位于窗口的左上角上。


Button 是动画的控制按钮，其位置和属性不会随着动画过程发生任何变化

所以我们把他约束窗口底部的中间位置

现在，Scene动画控制文件只是一个空框架，没有添加任何实质内容

也就是不会产生任何动画效果

在 Scene 动画控制文件中，duration 属性定义的是以毫秒为单位的动画持续时间，

在这里定义的是1000毫秒，也就是1秒

constraintSetStart 属性定义的是动画开始点的布局,也就是在动画第一帧中View的布局

constraintSetEnd属性定义的是动画结束点的布局,也就是在动画的最后一帧布局View的布局


如果一个 View 既出现在 MotionLayout 布局文件中,也出现在 Scene 动画控制文件中

则优先采用 Scene 文件中定义的位置约束条件和属性

Scene动画文件中没有定义的位置约束条件，则以 MotionLayout 布局文件中定义的为准

因为在后续步骤中，MotionLayout 布局不在发生变化,所以就不在幻灯片中继续显示 MotionLayout 布局文件内容了


因为现在在Scene 动画控制文件中还没有任何内容,所以点击按钮，还不会有任何反应


接下来，我们开始向Scene动画控制文件添加动画控制条件




在 Start ConstraintSet ，也就是动画的起点,将 ImageView 约束在屏幕垂直居中靠左的位置

在EndConstraintSet ,也就是动画的终点,将 ImageView 约束在屏幕垂直居中靠右的位置

当确定 ImageView 在动画的起点和终点的位置之后

两点之间形成的直线就是动画过程中 ImageView 的运动轨迹

到这里，一个 Motion 动画所需的基本条件已经全部具备了

剩下的就是如何开始播放这个动画

在Androind中，既可以通过从程序代码调用MotionLayout提供的方法来播放动画、

也可以在 Scene 动画控制文件中指定播放动画的触发条件

在这里使用按钮的点击事件作为播放动画的触发条件

OnClick 的用途就是指定产生点击事件的元件

也就是当点击 Button 的时候，开始播放动画


在点击 Button 的时候，动画可能在第一帧，也可能在最后一针，还可能是中间任何位置

Android 会根据目前动画播放进度，决定如何处理Button点击事件

在处于动画的第一帧的时候，点击Button，动画会从第一帧开始向后播放

当处于动画的最后一帧的时候，点击Button，动画会从最后一帧开始向前反向播放


当动画中间某一帧的时候，如果播放进度小于50%，动画会跳回到第一帧，然后开始向后播放

当播放进度大于50%，动画会跳回到第一帧并停止播放



除了定义起点和终点以外，还可以通过定义关键帧的方式修改 ImageView 的运动轨迹、以及运动过程中属性的变化

Motion 动画的关键帧全部都定义在 KeyFrameSet 标签中


目前 Motion 支持的关键帧类型有 KeyPosition、KeyAttribute、KeyCycle、KeyTrigger

在本案例中分别定义了一个 KeyPosition 和三个 KeyAttribute 类型关键帧


KeyPosition 的用途是指定在某一帧动画中，ImageView 所在的坐标位置

motionTarget 用于定义本KeyPosition所针对的 View Id

在 Motion 中，无论动画多么复杂，持续时间有多长，都只能定义99个关键帧，编号为1到99

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

因为开始点相当于第一个关键帧，结束点相当于最后一个关键帧

通过这三个关键帧，为 ImageView 形成了一条中间凸起的运动曲线。



KeyAttribute 的用途是指定在某一个关键帧中，ImageView 的属性。

KeyAttribute 可以直接支持 alpha、rotation、scale、translation 等十几个定义动画常用的属性

除了这些动画常用属性以外，还可以通过 CustomerAttribute 标签向 KeyAttribute 添加自定义属性

要求是这些自定义属性必须支持get/set方法


在本案例中，我们分别在第1帧、第50帧和第99帧调整了 ImageView 的旋转角度

使 ImageView 看上去，似乎方向一直与运动路径保持垂直状态

编写本案例的主要目的是为了向大家介绍构成 Motion 动画的主要元素

接下来，让我们通过其他几个案例，进一步了解如何使用这些元素。




### index


Android MotionLayout Case 002

This is the second in a series of MotionLayout videos. 
In this series of videos, you'll learn more about how to use MotionLayout ：
1、Transition
2、ConstraintSet
3、KeyFrameSet
4、KeyAttribute
5、KeyPosition




本视频是MotionLayout系列视频中的第二个视频

内容可以分为两大部分

第一个部分是Index案例介绍

第二部分是相关知识的详细说明

涉及到知识点有 Transition
ConstraintSet
KeyFrameSet
KeyAttribute & KeyPosition

Index案例是其他几个案例的首页，也是一个Motion动画

首先，让我们看一下Index案例的布局文件

GuideLine 是 ConstraintLayout 布局的一个帮助类

用来为布局增加看不见的辅助线

通过GuideLine可以把布局划分为多个区域

在这个案例中，我们通过 GuideLine 把整个布局划分为左右两个区域，左边占40%，右边占60%.

接下来是两个ImageView

one_image_view 被约束在 GuideLine 和布局右侧之间，靠近布局底部的位置。

two_image_view 被约束在 GuideLine 和布局右侧之间， one_image_view 顶部位置


因为 GuideLine 是按照比例划分布局的，

所以当屏幕宽度发生变化的时候，两个ImageView的宽度也会随之改变

达到自动适应屏幕大小的效果


title_text_view 是一级标题，该 TextView 被约束在屏幕顶部靠左的位置


在title_text_view下面，还有五个TextView


这五个TextView二级标题，也是进入其他几个案例的链接按钮


大家会发现，这五个 TextView 与前面介绍的 ImageView 和 title_text_view 有一个明显的区别

就是在这五个 TextView 上没有添加位置约束

这是因为在整个动画过程中，两个 ImageView 和 title_text_view 的位置是固定不变的

而五个 TextView 的位置则会随着动画过程发生变化

所以ImageView 和 title_text_view 的位置直接在布局文件中进行设置就可以

作为二级标题的五个 TextView 的位置则需要在 Scene 动画控制文件中进行设置




在了解 Scene 动画控制文件之前，先看一下动画效果

可以看出在动画过程中

两个ImageView和title_text_view的不透明度在逐渐增减，直到完全不透明为止

作为二级标题的五个 TextView 则按照先后顺序，从屏幕的右侧进入屏幕，最后停靠在靠近屏幕左侧的位置


下面就让我们通过 Scene 动画控制文件解释整个动画过程。


在每个Scene动画控制文件中，可以定义多组动画

每个 Transition 代表就是一组动画

在 Transition 中，可以指定动画的持续时间、起点，终点以及任何所需的中间状态

constraintSetStart 属性值就是动画的起点
constraintSetEnd 属性值就是动画的终点
duration 属性值是以毫秒为单位的动画持续时间
autoTransition 属性定义是在布局加载结束之后，是否自动开始播放动画以及如何播放动画

animateToEnd 的含义是从起点向终点播放动画。




ConstraintSet 用来定义在动画某一点上，一组View的位置和属性

我们现在看到的是作为动画起点的ConstraintSet

在 Start ConstraintSet中

从 one 到 five 五个 TextView 全部都被约束在屏幕以外靠近布局右侧的位置

所以在动画开始开始的时候，完全看不到这个五个TextView。



在End ConstraintSet中，也就是作为终点的ConstraintSet中

从one到five五个TextView全部都被约束在靠近布局左侧的位置

通过在两个 ConstraintSet 中为 这些TextView 设置不同的位置

形成了 TextView 从右侧滑入屏幕，并逐渐靠近左侧的动画效果


在确定了动画的起点和终点之后，接下来让我们看一下如何定义动画的中间状态

动画的中间状态也叫做关键帧

这些关键帧全部都定义在 Transition 下的 KeyFrameSet 中

Motion 动画支持 KeyPosition、KeyAttribute、KeyCycle 等多种类型关键帧

每个关键帧都有一个对应的，0 到 100 之间的整数编号，

其中起点所对应的关键帧编号为 0，终点对应的关键帧编号为 100

KeyPosition、KeyAttribute 等标签的 framePosition 属性就是这些关键帧的编号


KeyAttribute 用来指定 View 在特定关键帧中的属性。

例如，在开始点 ConstraintSet 中把一个 View 的 alpha 值设为 0

在结束点 ConstraintSet 设为 1

默认情况下，视图在整个动画中会以线性方式逐渐淡入

如果你希望动画进度达到 80% 之前，View 一直都处于不可见状态

在进度超过 80% 之后，快读淡入

就可以在 KeyFrameSet 增加一个 KeyAttribute 节点，

将该节点的 framePosition 设为 80 并将 alpha 设为 0

在KeyAttribute 中可以设置 alpha、elevation、scale、rotation、translation 等常用的，与动画相关的属性。

我们会本视频的下半段中详细解释这些属性的使用

在本案例中，因为 title_text_view 和两个 ImageView 不需要改变位置，只需要调整不透明度

所以在布局文件中就已经把这个三个View确定了位置

在 Scene 动画控制文件中，只需要以关键帧的方式调整他们的不透明度就可以

这三个 View 的处理方式完全相同

都是在第 0 帧，把 alpha 值设置为 0

在第 50 帧把 alpha 值设置为 1

也就是说在动画的前半程，这三个 View 逐渐淡入，后半程处于完全显现状态



如果在开始点 ConstraintSet 中没有特别指定 View 的运动轨迹，View 会沿着起点到终点之间的直线进行运动。

KeyPosition 的作用是定义 View 在指关键帧中所产生的路径偏差。

偏差值是以百分比的形式定义的，percentX 和 percentY 属性就是在 X 和 Y 方向偏差所占的百分比值

百分比的计算基数取决于所采纳参考系

Motion 提供了三种参考系

分别是 deltaRelative、 pathRelative、parentRelative

deltaRelative 是以起点和终点作为斜对角的矩形参考系

水平方向为 X 方向距离

垂直方向为 Y 方向距离

关于参考系，也会在本视频的下半段中详细解释

在本案例中，通过控制路径偏差，达到让五个 TextView 按照顺序，先后出场的效果

第一个TextView 在第 0 到第 33 个关键帧之间行走全部路径
第二个TextView 在第 16 到第 50 个关键帧之间行走全部路径
第三个TextView 在第 33 到第 66个关键帧之间行走全部路径
第四个TextView 在第 50 到第 83 个关键帧之间行走全部路径
第五个TextView 在第 66 到第 99 个关键帧之间行走全部路径


这五个TextView在出场的同时，也逐渐增加其不透明度，形成运动过程中逐渐淡入的效果。


transitionEasing用于定义View的运动速度模式，

总共有四种速度模式，分别是linear、standard、accelerate、decelerate

linear是匀速模式

standard 是先加速、然后匀速、最后减速模式

accelerate是逐渐加速模式

decelerate是逐渐减速模式

关于速度模式，我们放在视频的后半段进行讲解

到这里，我们已经全部介绍完index案例

在详细讲解之前，让我们在看一下index案例的实际运行效果



