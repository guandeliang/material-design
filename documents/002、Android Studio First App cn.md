### 1

在上一个视频中，已经介绍了如何下载、安装、配置 Android Studio，
并且创建了第一个 Android Studio 项目。

在这个视频中，将更加详细的讲述使用 Android Studio 创建 Android 项目的过程。
内容包括：

1. 项目模板和项目设置
2. 项目结构
3. 使用 Gradle 构建项目的过程
4. 安装和使用虚拟机
5. 布局设计工具

In the previous video, has show how to download, install, configure Android Studio, 
and create the first Android Studio project.

In this video, you'll learn more about the process of creating an Android project using Android Studio. 
The contents include:

1. Project template and configuration
2. Project structure
3. Gradle lifecycle of project build
4. Install、Config and Use Virtual Machines
5. Layout Design Tools


### 2

现在，让我们到 Windows 的开始菜单中找到并且启动 Android Studio。

在 Android Studio 启动窗口屏过之后，出现的是欢迎窗口，提示选择要进行的工作。

总共有六个可选项目，分别是：

1. 开始一个新的 Android Studio 项目
2. 打开一个已经存在的 Android Studio 项目
3. 从版本控制服务器中拉取一个项目
4. 分析和调试APK文件
5. 导入 Gradle 或 Eclipse 项目
6. 导入 Android 样本代码


Now, open the Windows Start menu, find Android Studio, and start it.

After the Android Studio splash screen, a welcome window appears, prompting you to choose what you can do.

There are six optional items, namely:

1. Start a new Android Studio project.
2. Open an existing Android Studio project.
3. Get from version control server.
4. APK Profile or Debug APK.
5. Import project from Gradle or Eclipse.
6. Import an Android code simple.

### 3



点击"Start a new Android Studio project"之后，进入的是项目模板选择窗口

Android Studio提供了五种类型的项目模板，分别是：

1. 手机和平板
2. 可穿戴设备
3. 电视
4. Android Automotive 是 Android 为汽车仪表提供的一个分支
5. Android Things 是 Android 为嵌入式系统提供的一个分支。



After clicking "Start a new Android Studio project", the project template selection window appeared.

Android Studio provide five types of project template:

1. Phone and Tablet
2. Wear Os
3. Tv 
4. Android Automotive is a variation of Android operating system, tailored for its use in vehicle dashboards.
5. Android Things is an Android-based embedded operating system platform

### 4


为了介绍 Android 项目结构，我们选择最基础的项目模板，也就是No Activity模板，

创建一个新的Android项目。

在这里输入的项目名称，将显示在 Android 启动器上。
Android 启动器类似于 Windows 的桌面，
每一个 Android 应用在上面都有一个图标和一个名称。
点击图标和名称，就可以启动 Android 应用。
大家在我的手机屏幕上看到的是为本系列视频编写的一个样板应用。
"Material"就是这个应用的"Name"。

因为Android Luncher分配给每个应用位置特别小。
所以应用的名称不能太长，
要控制在两个单词以内。
并且与其他应用有明显的区别。

我们把这个应用的“Name”设置为“First App”

To introduce the Android project structure, 

we choose Activity, a most basic project template,  to create a new Android project.

The project name you enter here will be displayed on the Android launcher.

Android launcher is similar to the Windows desktop, 
each Android application has an icon and a name on it, 
click the icon and name, you can launch Android applications. 

What you see on my cell phone screen is a sample application written for this series of videos.
"Material" is the "Name" of the application.

Because that Android lunch allocates a very small location to each application.

The name of the app should not be too long, 
less than two words, 
and clearly different from other apps.

Let's set the Name of this application to First App

### 5


默认情况下，Package Name 既是项目类文件所在的包，也是应用的Application Id。

每一个 Android app 都需要有一个看上去很像java包名的application ID。

Application Id 也是应用在设备和应用商店中的唯一标志。

当你向应用商店上传一个新版本应用时，

新版本 APK 的 application ID 和签名证书必须与旧版本完全相同。

如果application ID发生变化，应用商店会认为这是一个全新的应用。
所以应用一旦发布，将永远不能再更改application ID。

不过大家不用紧张，在应用正式发布之前，application ID是可以随意更改的。
所以不用太在意这里设置的Package Name以后是否会发生变化。

另外 Android Studio 使用的 Gradle 构建工具也允许 Package Name 和 application ID 使用不同的值。


By default, Package Name is both the package where the project class file is located and the Application Id of the application.

Every Android app has a unique application ID that looks like a Java package name, such as com.jacob.material.firstapp. 

This ID uniquely identifies your app on the device and in App Store. 

When you upload a new version of an app to the store.

The application ID and sig certificate of that new version of the APK must be identical to the old version.

If the application ID changes, the App Store will recognize it as a new application

So once the application is releaseed, you can never change the application ID again.

But don't be nervous, before release of it, application ID can be changed at will. 
So don't worry too much about whether the Package Name set here will change during the development process.

In addition, the Gradle build tool used by Android Studio allows different values for Package Name and application ID.

### 6


Save location是项目在本地的保存位置。
现代项目开发都会将本地内容与版本控制服务器进行同步管理，
本地保存的内容只是版本控制服务器中内容的一个副本。

Language 是指当 Android Studio 为新项目自动生成代码时，所使用的编程语言。
Android Studio 并没有限制在项目中只能使用一种程序语言。
也就是说Android Studio项目可以采用多种语言混合编写。

我们选择java作为本项目的主要程序语言。

Minimum SDK是项目最低兼容的Android SDK版本。

选择的 Android SDK 版本越低，应用所能支持的 Android 设备就越多，
但是会导致应用无法使用高版本的 Android SDK 才有的特性，反之亦然。

如果你不知道怎么选择，点击 Help me choose，可以看到更详细的信息。

虽然还有大概 6% 的设备操作系统版本在 Android 5 以下，
但是这些设备基本上都处于闲置或者停止更新状态，
所以在开发新的 Android 项目的时候，完全不用考虑这些设备。

"use legacy android support libraries" 是询问是否使用旧版本的 Android 支持库。

如果取消这个选项，则表示只使用最新的AndroidX支持库。

在所有的内容都填写完成之后，点击Finish按钮，
Android Studio开始生成项目所必须的文件。


Save location is the local location where the project is saved.
Modern project development synchronizes local content with the version control server, 
and locally stored content is only a copy of the content in the version control server.


Language is the programming language used when Android Studio automatically generates code for a new project.

Android Studio does not limit only single programming language can used in a project.
This means that Android Studio projects can be written in a mixture of languages.
We chose Java as the main programming language for this project. 


The Minimum SDK is the minimum compatible Android SDK version for the project.
The lower the Android SDK version you choose, the more Android devices your app will support.
But this will prevent the application from using features that are only available in later versions of the Android SDK, and vice versa.

If you don't know how to choose, click the Help me choose to see more information.


Although about 6% of the devices are below the Android 5 version, 
these devices are basically idle or stopped updating, 
so when developing new Android projects, 
these devices should not be considered at all.


"The use legacy android support libraries" is asking whether to use older versions of Android support libraries. 
If you uncheck this option, only the latest Android X support libraries will be used.

Once everything is filled in, click the Finish button, and Android Studio starts generating the files necessary for the project.

### 7


在Android Studio窗口的左侧是项目导航区，
点击Project开关按钮可以打开或关闭项目导航区。

为了满足不同工作场景需要，Android Studio 提供了多个导航视图。

让开发者能够从不同的视角查看项目内容。

我们现在看到的是 Android 导航视图。
在这个视图中，项目内容被分为 App 和 Gradle 两大类。

App文件夹中存放的是程序原代码和资源文件。

与构建、编译、打包相关的配置信息和可执行脚本则存放放在Gradle文件夹中。

Gradle 是一个开源的自动化构建工具，
通过执行采用 Groovy 动态语言编写的脚本完成构建过程。
用户可以根据项目需要编写自己的构建脚本，以满足特有的配置、编译、构建和打包工作。

Android Studio 为 Gradle 提供了专门的插件和包装类，使其成为 Android 应用的构建工具。


通过导航区上面的下拉菜单，可以切换到其他导航视图。

我经常使用的是 Project 导航视图，
因为这个视图既能够提现出项目文件彼此之间的关系，
又能够看出每个文件的实际存储位置，方便于查找和编辑每一个文件。



On the left side of the Android Studio window is the project navigation area. 
The project's navigation area can be open or close by clicking the Project toggle button.

In order to meet the needs of different work scenarios, Android Studio provides multiple navigation views.
Allows developers to view project content from different perspectives.

What we see now is the "Android" navigation view. 
In this view, the project content is divided into "App" and "Gradle" categories.

The main content of "App" is program code and resource files.
Content related to building, compiling and packaging is placed in "Gradle".


Gradle is an open source automated build tool 
that executes scripts written in the Groovy dynamic language to complete the build process. 
You can write your own build scripts 
to meet your specific configuration, compilation, build, and packaging needs.

Android Studio provides specialized plugins and wrapper classes for Gradle, making it a building tool for Android applications.


You can switch to other navigation views by using the drop-down menus above the navigation area.

I use the Project navigation view a lot 
because it shows how the project files relate to each other 
and where each file is actually stored, 
making it easy to find and edit each file.

### 8

在Project导航视图的最上方，显示的是项目名称和项目在本地的保存路径。

展开之后就是完整的项目结构树。

右边显示的图形是重新规整之后的项目结构，

接下来我会逐个介绍每个文件夹或者文件的用途。

与 Android Studio 本身环境有关的信息，比如色彩主题、字符集、插件等内容，全部都保存在 .idea文件夹中。
因为这些内容都可以通过 Android Studio 图形界面进行设置，
所以无需关心这个文件夹具体存放了什么。
如果需要调整 Android Studio 环境，直接找到相应的图形界面工具进行修改就可以了。


默认情况下，Android Studio 使用 Git 作为版本控制服务器，
所以当创建新的项目的时候，也会同时创建 .gitignore 文件。
在.gitignore 中列出的是那些需要排除在版本控制以外的文件或者文件夹。
现在让我们看一下这两个文件的内容。

首先打开项目根目录中的 .gitignore 文件。
从文件的内容可以看出，.idea文件夹中所有内容都被排除在版本控制以外。

这是因为在团队合作开发过程中，不可能要求每个成员的 Android Studio 配置完全一样，
所以也就不需要在他们之间共享这些环境配置信息。

在 app 文件夹中也有一个 .gitignore 文件，
打开之后会发现里面列出的是还一个不存在的 build 文件夹。
这个文件夹中保存的是 app 构建之后生成的文件，
因为我们还没有对项目进行过构建，所以这个文件夹还没不存在。

Git 版本控制是一项单独的技术，与 Android 应用开发没有直接关系，
所以这里就不在做更多说明。
如果大家想进一步学习Git相关技术，可以参考相关的教程和文档。


At that top of the project navigation view is the project name and the path where the project is save locally.

Once expanded, the complete project structure tree is displayed.

The graphic on the right shows the re-organized project structure, and I'll go through the purpose of each folder or file.


Information about the Android Studio environment itself, such as color themes, character sets, plug-ins, and so on, is all stored in the.idea folder.
Because all of this content can be set up through the Android Studio graphical interface, you don't need to worry about what is stored in this folder.
If you need to adjust the Android Studio environment, you can directly find the graphical tools and modify it.

By default, Android Studio uses Git as the version control server, 
so when it creates a new project, 
it also creates the .gitignore file. 
The files or folders listed in .gitignore are those that do not require versioning. 
Now let's look at the contents of these two files.


First,  open the .gitignore file in the project root directory. 
As you can see from the contents of the file, everything in the.idea folder is excluded from version control.


This is because in team development, everyone's Android Studio configuration may not be exactly the same, 
so there is no need to share this environment configuration information between them.

There is also a .gitignore file in the app module folder, 
which when opened contains a non-existent build folder. 
This folder holds the files generated by the app module build, 
but since we haven't built the project yet, this folder isn't there yet.


Version Control is a separate technology, 
so I won't go into the details here. 
If you want to learn more, you can refer to the relevant tutorials or documentation.


### 9

在项目结构树中，可以看到多个名字中带有 gradle 的文件，这些文件都与 Gradle 有关。

.gradle 文件夹中存放的是构建过程中产生的缓存文件。
gradle 文件夹中存放的是 Android Studio 为 Gradle 提供的封装类。
使用过程中，不需要对这两个文件夹中的内容进行任何修改。

gradlew 和 gradlew.bat 分别是为 UNIX 家族系统和 Windows 系统提供的，用于启动 Gradle 的脚本文件。



In the project tree, you can see several files with gradle in their names. These files are related to Gradle. 

The .gradle folder holds the cache files generated during the build process. The Gradle folder contains the Gradle wrapper classes. 

During use, you do not need to make any changes to the contents of these two folders.

Gradlew and gradlew.bat are the script files used to start Gradle on UN*X and Windows systems.



### 10


为了更容易理解与Gradle相关的其他几个文件，我们首先需要了解一下 Gradle 构建项目的过程。
Gradle 通过以下几个步骤完成项目的构建工作。

1. 从gradle.properties和local.properties 文件中读取构建脚本运行所需要的全局变量，为构建过程做准备。

2. 执行 settings.gradle 脚本，获取需要构建的模块列表。

3. 执行 project 文件架中的 build.gradle 脚本，初始化整个项目的构建环境。

4. 执行 module 文件夹中的 build.gradle 脚本，完成 module 构建工作。

5. 完成构建收尾工作。


默认情况下，Gradle 使用 Groovy DSL 作为构建脚本语言，
除了 Groovy DSL 以外， Gradle 还可以使用 Kotlin DSL 作为构建脚本语言。
在本项目中使用的是 Groovy DSL。 
DSL 是domain-specific language 的缩写，意思就是特定领域语言。
用在这里，含义就是项目构建领域语言。

现在屏幕上显示的两个网络地址分别是 Groovy DSL 和 Android Plugin DSL 文档地址，
在这两个文档中可以查阅到构建脚本中使用到的全部方法。



To make it easier to understand the other files associated with Gradle, we first need to look at the process by which Gradle builds the project. Gradle completes the build of the project in the following steps.
1. To prepare for the build process, read the global variables that the build script needs to run from the gradle.properties and local.properties files.
2. Execute the settings.gradle script to get the list of modules that need to be built.
3. Execute the build.gradle script in the project folder to initialize the build environment for the entire project.
4. Execute the build.gradle script in the module folder to complete the module build.
5. Finish up the build.

By default, Gradle uses the Groovy DSL as the build scripting language, and in addition to the Groovy DSL, Gradle can also use the Kotlin DSL as the build scripting language.
The Groovy DSL is used in this project. DSL is short for domain-specific language, Used here, the meaning is the project build domain language.

The two network addresses now on the screen are the Groovy DSL and the Android Plugin DSL document address, where you can see all the methods used in the build script.


### 11


gradle.properties 中存放的是可以在团队成员之间共享的，与项目构建过程有关的全局变量。

org.gradle.jvmargs参数用于分配虚拟机内存。

因为 Gradle 是运行在 Java 虚拟机里的，所以要为其分配虚拟机内存。
增大org.gradle.jvmargs参数值，可以提高Gradle的运行效率。

android.useAndroidX=true 表示使用 AndroidX 作为项目的支持库。
android.enableJetifier=true 表示项目中使用到的其他依赖包也全部迁移到AndroidX。


local.properties 中存放的是无法在关于成员之间共享的，与项目构建过程有关的全局变量。

在本项目的这个文件中只定义了一个变量，就是Android SDK 在本地的保存位置。



Global variables associated with the project build process are stored in gradle.properties and can be shared among team members.
The org.gradle.jvmargs parameter is used to allocate virtual machine memory.
Because Gradle is running in the Java Virtual Machine, virtual machine memory is allocated for it. Appropriately increasing the org.gradle.jvmargs parameter value can improve the running efficiency of Gradle.
Because Gradle is running in the Java Virtual Machine, virtual machine memory is allocated for it. Increase the org.gradle.jvmargs parameter value to make Gradle run more efficiently.

android.useAndroidX=true indicates that Android X is used as the project's support library.
android.enableJetifier=true indicates that all other dependent packages used in the project are also migrated to Android X.

local.properties holds global variables related to the project build process that cannot be shared between members.

There is only one variable defined in this file of this project, which is the local storage location of Android SDK.

### 12


settings.gradle 类似于 Java 的 main 方法，是 Gradle 脚本的执行入口。
通过这个脚本，Gradle 可以知道有哪些module要参与构建。

include 方法用于把module加入到等待构建的项目列表中。
如果有多个项目需要构建，就多次执行include 方法。

第二行内容定义了根项目名称。

Gradle 中的Project与 Android Studio 中的 Module是同一个概念。 
因为 Gradle 和 Android Studio 分属于不同的组织的，所以他们在一些元素的命名上会存在冲突，
大家只需要注意这些名词之间的对应关系，不要产生混淆就可以。
另外需要提醒大家的是，settings.gradle 以及要接着介绍的两个文件

都是使用 Groovy 程序语言编写的可执行脚本，不是普通配置文件。
也就是说所以我们看到的每一行内容都是一行程序代码，并不是某种特殊格式的配置信息。


Settings.gradle is similar to Java's main method, which is the execution entry of Gradle script. With this script, Gradle knows which modules are involved in the build.
The include method is used to add module to the list of projects waiting to be built. If there are multiple projects to build, execute the include method multiple times.

The second line defines the root project name.

Project in Gradle is the same concept as Module in Android Studio

Because Gradle and Android Studio belong to different organizations, 
there will be conflicts in the naming of some elements, 
you only need to pay attention to the correspondence between these nouns, so as not to cause confusion.

It's also important to note that settings.gradle and the two files that follow are executable scripts written in the Groovy programming language, not regular configuration files.

That is to say, every line of content we see is a line of program code, not some special form configuration information.

### 13

project 文件夹中的 build.gradle 是整个项目的构建脚本。

在这个脚本文件中有三个方法。

buildscript 方法用于定义项目构建脚本的 classpath。
repositories 方法用于定义下载依赖包的仓库地址
google() 方法返回的是 Google Maven 仓库地址
jcenter() 方法返回的是 JCenter 仓库地址
现在屏幕上显示的两个网络地址分别就是Google Maven 仓库地址和JCenter 仓库地址。

dependencies 类似于 HashMap，可以保存 name-value 格式的信息。
在这里的用途是把 Android Studio 为 Gradle 提供的插件添加到classpath变量中。
后面我们还会看到使用dependencies保存其他信息。


在 allprojects 方法中定义的是适合于全部项目的依赖包仓库地址。
clean 方法负责在构建工作结束后删除构建过程所产生的临时文件。



app文件夹中的 build.gradle 是用于 app module 的构建脚本。
apply plugin 语句的用途是导入 com.android.application 插件。
com.android.application 是 Android 针对 Gradle 提供的扩展。

android{} 代码段是一个闭包对象，用于定义构建项目所需的信息。
compileSdkVersion 变量表示编译模块时所使用的SDK版本。
minSdkVersion 变量表示编译之后模块最低兼容的SDK版本。
targetSdkVersion 变量表示编译之后模块所具有的SDK特征。

这个变量可以是 minSdkVersion 和 compileSdkVersion 之间任何一个版本。
当 targetSdkVersion 低于 compileSdkVersion 的时候，
虽然编译过程使用的是 Compile Sdk，但是编译之后模块表现出的特征确只能达到 Target Sdk。
通过这个变量可以让开发者达到使用最新的 SDK 编写为早期系统开发应用的目的。

applicationId 是模块的唯一ID，这个ID在项目发布之前可以随意修改，
一旦发布到应用商店之后，就不能再做任何改变。
applicationId可以与模块的包名称相同，也可以不同。

buildTypes 中列出的是构建类型，release并不是关键字，用户可以根据需要随意增加新的构建类型。

在前面我们已经介绍过 dependencies 方法的功能，在这里的用途是添加模块所需要的各种依赖包。


前面我们用了很长的时间介绍 Gradle，
通过这些介绍只能对 Gradle 有一个大概的了解，还达不到熟练使用程度。
大家可以通过查阅前面提供的两个文档进行深入学习。
在本系列视频后续内容中，也会根据需要做一些深入的介绍。
总之，大家要清楚，深入了解和学习如何使用 Gradle，是提升 Android 应用开发的必备技能之一。


The build.gradle in the project folder is the build script for the entire project. There are three methods in this script file.
The buildscript method is used to define the classpath of the project build script. 
The repositories method is used to define the repository address to download dependency package. 
The google () method returns the address of the Google Maven repository. 
The jcenter () method returns the JCenter repository address.

The two network addresses now displayed on the screen are the Google Maven repository address and the JCenter repository address. 
Dependencies are similar to a HashMap and can hold information in the name-value format. 
The purpose here is to add the Android Studio plug-in for Gradle to the classpath variable. 
Later we will see the use of dependencies to hold additional information.


Defined in the allprojects method is the dependency package repository address that applies to the entire project. 
The clean method is responsible for deleting the temporary files produced by the build process when the build is complete.

build.gradle in the app folder is the build script for the app module. 
The purpose of the apply plugin statement is to import the com.android.application plug-in. 
com.android.application is an Android extension for Gradle.

The android {} is a closure object that defines the information needed to build the project. 
The compileSdkVersion variable indicates the SDK version with which the module was compiled. 
The minSdkVersion variable indicates the minimum compatible SDK version of the module after compilation. 
The targetSdkVersion variable indicates the SDK characteristics of the module after compilation.

This variable can be any version between minSdkVersion and compileSdkVersion. 
When targetSdkVersion is lower than compileSdkVersion, 
Although the compilation process uses Compile Sdk, 
but after compilation, the characteristics of the module can only achieve Target Sdk. 
This variable allows developers to write applications for early systems using the latest SDK.

ApplicationId is the unique ID of the module, 
which can be changed at will before the project is released, 
and cannot be changed once the project is released to the Store. 
The applicationId can be the same or different from the package name of the module.


buildTypes lists build types, release is not a keyword, and users are free to add new build types as needed. 
The dependencies method was introduced earlier and is used here to add the various dependencies required by the module.


We spent a long time introducing Gradle, 
through which we can only have a general understanding of Gradle, 
and can not reach the level of proficiency in use. 
You can learn more by consulting the two documents provided earlier. 
Later in this series of videos, we'll also go into some depth as needed. 
In short, we should be clear that in-depth understanding and learning how to use Gradle 
is one of the necessary skills to improve Android application development.


### 14

每个 Android Studio 项目可以包含多个 module，module 之间可以彼此独立，也可以互相依赖关系。

在本项目只包含一个 module，也就是app。

libs 文件夹用于存放模块的本地依赖包，比如一些jar文件。
src 文件夹用于存放原代码。其中 main 文件夹中存放的是正式代码，

测试代码之类的辅助代码则存放在main以外的地方。

以上介绍的这些就是 Android Studio 项目结构树中所包含的内容。

接下来要介绍的是 main 文件夹中存放的是正式代码。


在每一个 Android 项目的源代码根目录下都会有一个 AndroidManifest.xml 文件。
这个文件既是项目的整体描述文件，也是项目与 Gradle、应用商店之间的桥梁。
Gradle和应用商店通过这个文件来了解项目中包含的内容。

在查看这个文件内容之前，我们先展开 main 文件夹下所有的目录，
以便于了解AndroidManifest.xml与其他文件之间的关系。


Each Android Studio project can contain multiple modules, which can be independent of each other or dependent on each other. In this project, there is only one module, that is, app.

The libs folder is used to hold the local dependencies of the module, such as some jar files. 
The src folder is used to store the source code. 
The main folder stores the formal code, and the auxiliary code such as test code is stored in other place out of main.

This is what is contained in the Android Studio project tree. The next thing I want to talk about is that the main folder holds the official code.

There is an AndroidManifest.xml file in the source code root of each Android project. 
This file is not only the overall description file of the project, 
but also the bridge between the project, Gradle and the application store. 
This file is used by Gradle and the App Store to see what is included in the project. 
Before look at that contents of this file, 
let's expand all the directory under the main folder to see how AndroidManifest. XML relates to the other file.


### 15

在 AndroidManifest.xml 根标签 manifest 中，可以看到 package 属性。

Gradle 构建项目时，通过这个属性确定原代码所在位置，箭头指向的文件夹，就是 package 的实际存储位置。

项目构建结束之后，打包之前，Gradle 会把这个属性替换成build.gradle 中定义的 applicationId 变量。


也就是说 build.gradl中的 applicationId 与 AndroidManifest.xml中的package可以采用不同的值。
有时候，采用不同的值，更便于源代码的更新和管理。
比如在项目发布之后，因为修改源代码导致 package 发生变化，
但是这时已经无法更改 applicationId，只能让二者采取不同的值。


把 application 标签中的 allowBackuo 属性设置为false, 
可以禁止用户通过USB调试模式来备份已经发布的应用。
在这里设置为true，是为了便于调试，
出于安全原因，在应用正式发布之前，一定要将这个属性修改为false。


icon 和 roundIcon 属性用来指定应用在 Android 启动器中所显示的图标，
label用来指定应用在Android启动器中所显示的名称。

因为有的Android启动器中使用的是方形图标，有的Android启动器使用的是圆形图标。
为了适应不同的 Android 启动器，application 标签提供了 icon 和 roundIcon 两个属性，

@mipmap/ic_launcher 和 @mipmap/ic_launcher_round 表示的是引用 mipmap 类别下的 ic_launcher 和 ic_launcher_round 资源。


In the AndroidManifest.xml root tag manifest, you can see the package attribute. 
When Gradle builds a project, he uses this property to determine where the source code is located. 
The folder the arrow points to is where the packages are actually stored. 
Gradle replaces this property with the applicationId variable defined in build.gradle at the end of the project build and before packaging.


That is, the applicationId in build.gradl and the package in AndroidManifest.xml can take different values. 
Sometimes different values are used to make it easier to update and manage the source code. 
For example, after the project is released, 
the package changes as a result of modifying the source code, 
but the applicationId cannot be changed at this time, 
and the two can only take different values.

By setting the allowBackuo attribute in the application tag to false, 
you can prevent users to backing up a releaseed applications through USB debug mode. 
This is set to true for debugging purposes, 
but for security reasons, be sure to change this attribute to false before releasing the application.

The icon and roundIcon attribute are used to specify which icon the application displays in the Android launcher, 
Label specifies the name of the application as it appears in the Android launcher.

Because some Android launchers use square icons and some Android launchers use round icons. 
To accommodate different Android launchers, 
the application tag provides the icon and roundIcon attribute.


@mipmap/ic_launcher and @mipmap/ic_launcher_round indicates a reference to the ic_launcher and ic_launcher_round resources under the mipmap category.


### 16

res文件夹是存放资源的地方，根据资源类型的不同，又细分层多个子文件夹。
根据资源用途，Android 将资源划分为动画、颜色、图片、字符串、风格、字体等多种类型。
虽然资源类型很多，但是除了传统格式的图片、字体等资源以外，大部分资源采用的都是XML格式。
采用XML格式定义资源有非常多的好处，
如占用的存储空间小，容易归类，可以直接通过程序进行分析和操作等。
这也是在Android中，每个类型的资源都有对应的封装类和操作类的原因。


屏幕上显示的是通过XML属性中使用资源的语法格式。

package_name 是资源所在的包名称，如果XML与资源在同一个包内，可以省略 package_name。

resource_type 是资源类型，ic_launcher和ic_launcher_round是资源名称。

mipmap 类别主要用于存放应用的启动图标。
为了满足不同 Android 启动器和不同屏幕分辨率的需要，
在Android中，同一资源名称可以对应多个资源。在应用执行的时候，根据应用所在的硬件环境，决定启用哪一个资源。


因为启动器图标过于复杂，为了减轻开发者工作，Android Studio 提供了专门的启动器图标管理工具。

除了 mipmap 类型资源以外，Android 还定义了 anim、drawable、color、layout、string、style 等很多其他类别资源。
稍等我们就会接触到string、style、color这三个类别。


res folder is use to store resources, according to the different types of resources, and many subfolders. 
According to the use of resources, Android divides resources into animation, color, drawable, string, style, font and other types. 
Although there are many types of resources, but in addition to the traditional format of images, fonts and other resources, most of the resources are in XML format.

Using XML format to define resources has a lot of advantages, 
such as small storage space, easy to classify, 
and can be analyzed and operated directly through the program. 
This is why in Android, there is a wrapper class and an action class for each type of resource.

The syntax format for using resources in XML attributes is displayed on the screen. 
The package_name is the package name of the resource. 
If the XML and the resource are in the same package, the package_name can be omitted.

resource_type is the resource type, and ic_launcher and ic_ launcher_round are the resource names. 
The mipmap type is mainly used to store the startup icon of the application. 
In order to meet the needs of different Android launchers and different screen resolutions, 
in Android, the same resource name can correspond to multiple resources. 
When the application is executed, 
which resource is enabled is determined according to the hardware environment of the application.

Because launcher icons are too complex, Android Studio provides a dedicated launcher icon management tool to ease the work of developers.


In addition to the mipmap type resource, 
Android defines many other type of resources such as anim, drawable, color, layout, string, style, and so on. 
In a moment we'll get to the string, style, and color types.


### 17

点击label属性之后，会发现 label 属性引用的是 @string/app_name 资源。
该资源定义在 values 文件夹下的 strings.xml文件中。

strings.xml 资源文件中保存的是字符串类资源。

通过引用资源的方式在代码中使用字符串，
不仅有利于字符串的统一管理和重复使用，还可以简化多语言支持这类复杂工作。支持一个新的语言，只需要增加一个XML文件。

现在strings.xml文件中只有app_name一行内容，稍后我们会向里面增加另外一些内容。

theme 属性的用途是为应用分配UI主题，
AppTheme 资源定义在values 目录下的styles.xml文件中。
使用不同的 UI 主题，可以让应用拥有不同的外观。

因为UI主题在Android中是一项非常重要，也非常复杂的技术。
所以我们会单独录制两个视频来专门讲解Android应用的UI主题，在这里就不再详细说明。


在AppTheme中引用了三个颜色资源，这三个资源定义在 values 目录下的 colors.xml 中。


通过屏幕上的内容，已经能够看出并且初步了解在 Android 是如何存放、定义和使用资源信息的。

因为现在First App还是一个空壳，任何实质内容，
所以在 AndroidManifest.xml 中还无法看到更多信息。



When you click the label attribute, you will see that the label attribute refers to the @string/app_name resource. 
This resource is defined in the strings.xml file under the values folder.


The strings.xml resource file holds string class resources. 
Using strings in code by referencing resources is not only conducive to the unified management and reuse of strings, 
but also simplifies the complex work of multi-language support. 
To support a new language, just add an XML file.

The strings.xml file now has only has one line named app_name, and we'll add some more to it later.

The theme attribute is used to assign a UI theme to the application, 
and the AppTheme resource is defined in the styles.xml file in the values directory. 
You can use different UI themes to give your application different looks. 
Because UI themes are a very important and complex technology in Android. 
So we will record two separate videos to explain the UI theme of Android applications, 
so I won't go into detail here.

Three color resources are referenced in the AppTheme and are defined in colors.xml under the values directory.

Through the content on the screen, 
we can see and get a preliminary understanding of how to store, define and use resource information in Android. 
Because First App is still an empty app, nothing of substance, 
there's no way to see any more information in AndroidManifest.xml.


### 18

接下来为 First App 创建一个Activity，然后再看一下 AndroidManifest.xml 会发生哪些变化。

一般情况下，每个Activity 至少包含一个 Class 文件和一个的 Layout 文件。

Class 文件负责交互逻辑，Layout 文件负责窗口中的元素以及元素之间的位置关系。

虽然通过 Activity 对话框，可以同时完成创建 Class 和 Layout 文件，

但是为了让大家更理解两个文件各自的作用，我们分两次完成文件的创建工作。

首先创建的是 Layout 文件

在左侧导航视图中的 App module 下任何位置点击鼠标右键，

在上下文菜单中选择 New - Android Resource File 之后，会出现新建资源文件对话框。

File Name 是 Layout 文件名称，在这里输入main_activity。

Resource Type 是资源文件类型，选择 Layout 类型。

从下来列表中可以看到 Drawable、Font、Menu 等很多种资源类型，

也就是说通过这个对话框可以创建多种类型的资源。


Rooot Element 是根元素类型。默认给出的是 ConstraintLayout。

ConstraintLayout 叫做约束布局，是 Android 中最强大的布局工具，但是用起来相对比较复杂。
我会提供一个单独的视频讲解如何使用 ConstraintLayout。
在这里我们选择一个相对简单的布局，就是 LinearLayout，也就是线性布局。

Andoid Studio 允许根据构建类型对源代码进行分组，比如调试类型为一组、发布类型为一组。main 代码组中包含的是公用代码，也就是所有构建类型都需要的代码。
Source Set 的用途就是为这个布局文件选择代码组。


Directory name 是布局文件的存放路径，直接使用默认的 layout 就可以。

Android 中的资源文件既可以用于全部场景，也可以限定只能用于某个国家、地区、语言等特定场景中。

Available qualifiers 列表框列出的就是可选场景类型。

因为这是一个通用的例子，并不打算进行场景区分，所以，对于这一项不用做任何选择。

在填写完全部内容之后，选择Ok按钮。


Next, create an Activity for First App, 
and then take a look at what happens to AndroidManifest.xml. 
In general, each Activity contains at least one Class file and one Layout file.

The Class file takes care of the interaction logic, 
and the Layout file takes care of the elements in the window 
and the positional relationships between them.

Although it is possible to create both Class and Layout files at the same time using the Activity dialog, 
the files are created in two separate iterations to give you a better understanding of what each file does.


First to create is the Layout file. 

Right-click anywhere under App module in the left navigation view. 
From the context menu, select New - Android Resource File. 
Opens the New Resource File dialog box.

File Name is the name of the Layout file, input main_ activity at here.

Resource Type is the resource file type, select Layout type. 
From the drop-down list , you can see that there are many types of resources, 
such as Drawable, Font, Menu, etc. 
That is to say, you can create many types of resources through this dialog box.

Rooot Element is the root element type. ConstraintLayout is given by default.


ConstraintLayout is the most powerful layout tool in Android, 
but it's relatively complex to use. 
I'll provide a separate video on how to use ConstraintLayout. 
Here we choose a relatively simple layout, namely LinearLayout.


Andoid Studio allows source code to be grouped by build type, 
such as debug type group and release type group. 
The main code group contains the common code, that is, the code required by all build types. 

The purpose of the Source Set is to select the code group for the layout file.

Directory name is the storage folder of the layout file, directly use the default layout.


Resource files in Android can be used for all scenarios, 
or can be limited to specific scenarios such as a country, region, language, and so on. 
The Available qualifiers list box lists the available scene types. 
Because this is a generic example and is not intended for scenario differentiation, 
no choice is made for this item.

When you have filled in everything, click the Ok button.

### 19

Android Studio 会在 res 路径下创建一个 layout 文件夹，并在其中创建一个 main_activity.xml 文件。

现在在屏幕上看到的就是刚刚创建的 main_activity.xml 文件。

LinearLayout 是线性布局元件，orientation 用于定义布局方向，在这里选择的是垂直方向。

layout_width 和 layout_height 用于定义布局覆盖范围，在这里定义的是充满上级布局。

既然 LinearLayout 是根布局，为什么还会存在有上级布局呢，

这个问题，我们会放在关于 App 启动过程的视频中进行讲解。

Android Studio 为布局文件提供了 Source、Split、Design 三个设计视图。

Source 视图只能查看布局文件的原代码。

Split 视图可以在查看源代码的同时对布局进行预览。

通过 Design 视图，可以采用拖拽的方式设计布局文件。


在 Design 视图的左上角列出的可用元件，右下角是元件树，中间是布局预览，右边是元件属性。

Design视图看上去像是一个非常方便的工具，但是实际操作的时候，特别是修改元件属性的时候，非常不方便。

对于熟练的开发者来说，Split视图是一个更好的选择，

在Split视图既可以快速编写代码，还可以同时预览编写出的代码结果。

出于演示目的，我们还是通过 Design 视图向布局中添加两个元件，分别是 TextView 和 Button。

把这个两个元件拖拽到布局中之后，左下角的元件树也随之发生的变化。

现在让我们回到Split视图，进一步完成布局设计工作。


Android Studio creates a layout folder under the res path and creates a main_activity.xml file in it. 
You now see on the screen the main_activity.xml file that you just created.

LinearLayout is a linear layout element, 
orientation is used to define the layout direction, 
in this case the vertical direction is selected. 
layout_width and layout_height are used to define the size of the layout, 
in this case the size is full parent layout.

Since LinearLayout is the root layout, why should there be a superior layout? 
This question will be explained in the video about the startup process of App.


Android Studio provides three design views for layout files: Source, Split, and Design. 
The Source view can only view the original code of the layout file. 
The Split view lets you preview the layout while viewing the source code. 
The Design view allows you to design layout files by dragging them.

In the top left corner of the Design view is available components.
In the bottom left corner is component tree.
in the middle is the preview of layout.
In the right is Element properties.


The Design view looks like a very convenient tool, 
but in practice, especially when modifying element attributes, it is very inconvenient. 
For skilled developers, Split view is a better choice, 
where you can write code quickly and preview the results of writing code at the same time.


For demonstration purposes, let's add two element, TextView and Button. 

After dragging the two components into the layout, 
the component tree in the bottom left corner changes accordingly. 
Now let's go back to the Split view and finish the layout work.




### 20

在 LinearLayout 中出现的两个元件就是刚才在 Design 视图中通过拖拽添加的 TextView 和 Button。

LinearLayout 的 gravity属性的用途是定义子元素在父元素中的对齐方式。

大家会发现，当 gravity 属性的值设置为水平居中之后，TextView 和 Button 的位置并没有发生变化。

这是因为现在 TextView 和 Button 和 layout_width 属性值还都是 match_parent。也是就是充满父元素。

当把他们的 layout_width 属性值修改为 wrap_content，也就是恰好容纳自身内容的宽度之后，就可以看到水平居中效果了。

layout_marginTop属性是设置元素的上边白。在这里分别为 TextView 和 Button 设置 48dp 和 16dp 的上变白。

让他们与各自的顶部拉开一点距离。

接下来把 TextView 的文字大小增加到 36sp，Button 的文字大小增加到 16sp。

为什么边白的长度单位是dp，而文字大小的长度单位却是sp呢，

这个问题我们放在 UI 主题视频中统一进行介绍。

在代码的右上角可以看到一个黄色方框。鼠标移过去之后会提示存在两个警告信息。

在预览视图右上角有一个带有叹号的黄色框，

顺着提示会看到出现叹号是因为 TextView 和 Button 两个元件的 text 属性值使用的是硬编码字符串。

让我们打开 strings.xml 资源文件，向其中填写几个字符串资源，来解决这个问题。

在 strings.xml，我们添加了三条字符串资源，分别是 main_hello、main_title 和 main_btn。

添加过字符串资源之后吗，返回到 main_activity 中，

把 main_hello 和 main_btn 分别设置为 TextView 和 Button 两个元件的text属性。

@符号表示引用资源，string 表示的是资源类型为字符串，main_hello 和 main_btn 是资源名称。


到这里，大家会发现，预览视图右上角的叹号已经消失，说明整个布局文件已经没有任何问题。

The two elements that appear in the LinearLayout are the TextView and the Button that you just added by dragging them from the Design view.


The purpose of the gravity attribute of the LinearLayout is to define the alignment of child elements within the parent element. 

You'll notice that when the gravity attribute is set to horizontally centered, the positions of the TextView and Button do not change. 

This is because the TextView and Button and layout_width attributes are now match_parent. 
That is, filled with the parent element.

When you change the value of their layout_width attribute to wrap_content, 
which is the width that just about fits their content, you can see the effect of horizontal centering. 

The layout_marginTop attribute sets the top margin of the element. 
The top margin of 48dp and 16dp is set here for TextView and Button, respectively. 
Give them a little distance from their respective tops.

Next, increase the TextView text size to 36sp and the Button text size to 16sp. 

Why is the unit of margin is DP, he unit of text size is sp? This problem will be introduced in the UI theme video. 

You can see a yellow box in the top right corner of the code. 
There are two warning messages after mousing over. 

In the upper right corner of the preview view, there is a yellow box with an exclamation mark. 
Follow the step to see that the exclamation mark appears because the text attribute values of the TextView and Button components use hard-coded strings.

Let's solve this problem by opening the strings.xml resource file and add a few string resources. 
In strings.xml, we add three string resources, main_hello, main_title, and main_btn.


After adding string resources, return to main_activity and set main_hello and main_ btn to the text attributes of TextView and Button respectively. 

The @ symbol indicates a reference to a resource, the string indicates that the resource type is a string, and main_hello and main_btn are resource names.

At this point, you will find that the exclamation mark in the top right corner of the preview view has disappeared, indicating that the whole layout file has no problems.

### 21


接下来让我们开始创建Activity的类文件。

在左侧导航视图中，App module 下任何位置点击鼠标右键，

在上下文菜单中选择 New - Activity - Empty Activity 之后，会出现 Activity Configure 对话框。

Activity Name 是 Activity 对应的 Class Name。

因为这个 Activity 是主 Activity，所以继续使用 MainActivity 作为类的名字。

在创建这个 Activity 之前，已经创建过 Layout 文件，所以取消 Generate a Layout File 选项。


每一个Android项目，都需要有一个 Launcher Activity，也就是 App 启动之后，要显示的第一个窗口。

Main Activity 是 First App 第一个，也是唯一一个 Activity，所以必须勾选 Launcher Activity 选项。

Package Name 是Activity 类所在的包名称。

默认是项目的包名称，也可以修改成其他包名称，
如果修改之后的包名称不存在，Android Studio 会自动创建。


Source Language 是生成类文件所使用的程序语言，我们选择 Java 程序语言。

在所有内容都填写好之后，点击 Finish 按钮。

Android Studio 会自动生成并且打开 MainActivity.java 文件。


Now let's start to creating the Activity's class file. 

Right-click anywhere under App module in the left navigation view. 
From the context menu, select New - Activity - Empty Activity. 
Opens Activity Configure dialog.


Because intend this Activity to be the primary Activity, 
you continue to keep the name MainActivity. 
Because we already created the Layout file before we created the Activity, 
uncheck the Generate a Layout File option.

Because this Activity is the primary Activity, 
we continue to use MainActivity as the class name. 
The Layout file was created before the Activity was created, 
so uncheck the Generate a Layout File option.

Every Android project needs to have a Launcher Activity, 
which is the first window to be displayed after the App starts. 
Main activity is that first and only activity in First App, 
so you must check the Launcher activity option.

Package Name is the name of the package in which the Activity class resides. 
The default is the package name of the project, 
or you can change it to another package name. 
If the modified package name does not exist, 
it will be automatically created by Android Studio.

Source Language is the programming language used to generate the class files, 
and we choose the Java programming language. 
Once everything is filled in, click the Finish button. 
Android Studio automatically generates and opens the MainActivity.java file.

### 22


在解释和修改 MainActivity 类文件之前，我们先看一下 AndroidManifest.xml 文件发生了什么变化。

在 AndroidManifest 文件中，出现了刚刚生成的 MainActivity。

android.intent.action.MAIN 和 android.intent.category.LAUNCHER 两个属性值用来指定 MainActivity 既是主窗口，也是启动窗口。

需要注意的是，这个属性必须同时使用，才能生效。

MainActivity 继承自 AppCompatActivity。
为了确保用户能够使用最新的 Android 特性，
Android 使用 AppCompatActivity 替代了老版本的 Activity 类。

现在布局文件和 Activity 类文件还没有建立关联关系。

在 onCreate 方法中，通过调用 setContentView 方法，将布局文件指定给 Activity 类文件。

现在屏幕上显示是在类文件中引用资源的语法格式。

大家会发现，在类文件中应用资源的语法格式与在xml中引用资源的语法格式非常类似。

只是少了前缀的@符号，并且在resource_type增加了一个R。

R是 Android Studio 自动生成的资源引用封装类。


Before explaining and modifying the MainActivity class file, 
let's look at what happened to the AndroidManifest.xml file. 
In the AndroidManifest file, the MainActivity that was just generated appears.

Two attribute values, android.intent.action.MAin and android.intent.category.LAUNCHER, 
are used to specify that MainActivity is both the main window and the luncher window. 
Note that this attribute must be used at the same time to take effect.


MainActivity inherits from AppCompatActivity. 
To ensure that users enable to use the latest Android features, 
Android replaces the old Activity class with an AppCompatActivity. 

Now the layout file and the Activity class file have not been associated.


In the onCreate method, assign the layout file to the Activity class file by calling the setContentView method. 

A syntax format for reference resources in class files is on the screen. 

You will notice that the syntax for referencing resources in class files is very similar to the syntax for referencing resources in XML. 

Only the @ sign is missing from the prefix and an R is added to the resource_type. 

R is a resource reference wrapper class automatically generated by Android Studio.


### 23

现在First App已经是一个可以运行的App，
在运行之前，先检查一下，Run Configurations。

确保 Launch 选择的是 Default Activity。
否则调试工具只会向虚拟机或实体设备中安装应用，不会帮助自动运行应用。

接下来，为了运行 First App，还需要安装虚拟机。


在运行下拉框中选择 Open AVD Mananger 之后，点击创建虚拟设备按钮。

从选择硬件窗口中可以看出，可选的硬件几乎都是 Google 的手机设备，
选择 Pixel XL 之后，点击 Next 按钮。

接下来会要求选择系统镜像文件，因为 Android Q，也就是是 API 29 镜像文件还没有下载，所以需要先下载API 29镜像文件。

镜像文件下载结束之后，选择 Android Q, 并选择下一步。接下来是虚拟机设置窗口。

展开高级选项后，会出现CPU、内存、摄像头、SD卡等设置选项。采用默认值就可以。

设置完成之后会提示已经存在一个名称为Pixel XL API 29的虚拟机。

关闭提示窗口之后，会发现在运行下拉列表中也出现了 Pixel XL API 29 选项。

点击运行按钮，Android Studio 开始启动虚拟机，并向其中安装应用。

虚拟机的启动时间取决于每个人电脑硬件配置情况，我的电脑比较慢，实际启动时间比较长，大家看到的是剪辑之后的速度。

Now that First App is a working App, 
check Run Configurations before you run it. Ensure that Launch selects the Default Activity. 
Otherwise, the debugging tool will only install the application into the virtual machine or the physical device, 
and will not help to automatically run the application. 

Next, in order to run First App, you also need to install a virtual machine.

After selecting the Open AVD Mananger in the Run drop-down box, click the Create Virtual Device button. 

As you can see from the hardware selection window, 
the available hardware options are almost all Google's cell phone devices. 

After selecting the Pixel XL, click the Next button. Next, 
you will be asked to select the system image file, 
because Android Q, that is, the API 29 image file has not yet been downloaded, 
so you need to download the API 29 image file first.

When the image file has finished downloading, select Android Q, and select Next. 
Next is the Virtual Machine Settings window. 
After expanding the advanced options, the CPU, memory, camera, SD card and other settings options will appear. 
You can use the default values. Once setup is complete, 
you will be prompted that a virtual machine named Pixel XL API 29 already exists.

After you close the prompt, you'll notice Pixel XL API 29 options appear in the Run drop-down list as well.

When you click the Run button, 
Android Studio starts the virtual machine and installs applications into it. 

The startup time of the virtual machine depends on the hardware configuration of each person's computer, 
my computer is slower, the actual startup time is longer, what you see is the speed after editing.

### 24

这时候，点击 First App 的按钮还没有任何反应。接下来我们为它添加一个事件处理方法，

具体代码就不在这里解释。

重新启动First App。点击按钮已经可以看到效果。

关于 Android Studio 项目结构就介绍到这里，

里面涉及到的一些细节知识，会在后续视频中陆续详细说明。

At this time, clicking on the First App button has not yet responded. 
Next, we add an event handler to it, which is not explained here. 

Restart First App. Click the button to see the effect. 

So much for the structure of the Android Studio project. Some of the details will be explained in the following videos.