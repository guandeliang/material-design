1. 前言
1. Preface



对于每个软件开发人员来说，在学习与软件开发相关知识和技能之前，首先，也是最重要的一件事情，就是要先熟悉和掌握一套开发工具。
For every software developer, before learning the knowledge and skills of software development, the first and most important thing is to be learn and master a set of development tools. 

因为只有熟悉和掌握了开发工具之后，才能练习和实践所学到的知识和技能。

Because you can not practice the knowledge and skills you have learned, until you have master the development tools.


学习 Android 应用开发，首先要学习的就是Android Studio。
To learn Android application development, the first thing to learn is Android Studio.

Android Studio 是一套在在IntelliJ IDEA基础上定制的，适合于开发各种类型 Android 设备应用的，由 Google 官方提供的 Android 应用集成开发环境（IDE）。
Android Studio is the Google's official Integrated Development Environment (IDE) for Android app development of all Android devices, based on IntelliJ IDEA . 


Android Studio 在 IntelliJ 强大的代码编辑器以及开发工具的基础上，又增加了有助于提高工作效率的功能。

On top of IntelliJ's powerful code editor and developer tools, Android Studio offers even more features that enhance your productivity when building Android apps.


帮助开发者提升 Android 应用的构建和开发效率。
Help developers improve the efficiency of Android application build and development.

接下来，我们就介绍一下如何下载、安装和配置Android Studio，以及使用Android Studio创建第一个Android应用。
Next, we'll look at how to download, install, and setup Android Studio, and use Android Studio to create the first Android application.








2. 下载
1. Download


Android 开发者网站是 Android Studio的官方下载地址。
The Android Developer website is the official download address for Android Studio. 


大家一定要记住这个网站的地址，因为在以后的学习和实践过程中，会经常使用到这个网站。
Everyone must remember the address of this website, because in the future study and practice process, will often use this website.

现在让我们打开Android开发者网站。
Now let's open the Android Developer website.

打开Android开发者网站之后，在网页的最上方可以看到一个导航栏。
After opening the Android developer website, you can see a navigation bar at the top of the page.


Platform 是 Android 平台介绍，紧挨着他就是 Android Studio 是的下载页面入口。
Platform is the introduction of Android platform, and next to him is the download page portal of Android Studio.

点击该链接就可以进入 Android Studio 的下载页面。
Click on the link to access the Android Studio download page.

在进入 Android Studio 下载页面之后，网站会根据你所使用的电脑操作系统，自动向你推荐符合需要的 Android Studio 版本。
After entering the Android Studio download page, the website will automatically recommend the appropriate version of Android Studio to you according to the computer operating system you use. 


我所使用的电脑安装的是Windows 10操作系统，所以网站向我推荐的是Windows 64位版本。
The computer I use is installed with Windows 10 operating system, so the website recommends me the 64-bit version of Windows.

如果你希望下载其他版本，可以向下滚动网页，在网页的下方可以看到全部可下载的版本。
If you want to download other versions, you can scroll down the web page and see all downloadable versions at the bottom of the web page.

在这里列出的，可以下载的版本有Windows 64位和32位版本，Mac版本以及Linux和Chrome OS版本。Windows 64位版本提供了两个可下载的安装包，分别是EXE可执行文件安装包，以及ZIP压缩安装包。
The downloadable versions listed here are Windows 64-bit and 32-bit versions, Mac versions, and Linux and Chrome OS versions. The 64-bit version of Windows provides two downloadable installation packages, EXE executable installation package and ZIP compressed installation package.

再向下滚动网页，可以看到 Android Studio 对于不同操作系统环境的配置要求。比如 Windows 操作系统环境的配置要求是：
Scroll down the web page to see Android Studio's configuration requirements for different operating system environments. For example, the configuration requirements for the Windows operating system environment are:

64位的 Windows7、Windows8或者Windows 10。
64-bit Windows 7, Windows 8, or Windows 10.

推荐8GB，最小4GB的内存
Recommended 8GB and minimum 4GB of memory

推荐4GB，最少2GB的可用可用硬盘空间
Recommended 4GB, minimum 2GB of available hard disk space

显示器的分辨率要在 1280 X 800 以上
The resolution of the display should be above 1280 X 800

现在我们回到网页的最上方，把适合我电脑的版本，也就是网站所推荐的版本，下载下来。
Now let's go back to the top of the web page and download the version suitable for my computer, that is, the version recommended by the website.

点击下载按钮之后会显示 Android Studio 使用限制条款，首先选择“我已经阅读并且同意以上限制条款”，
After clicking the Download button, Android Studio usage restrictions will be displayed. First, select "I have read and agreed to the above restrictions",

然后点击“DOWNLOAD ANDROID STUDIO FOR WINDOWS”，就会开始下载Android Studio安装包。
Then click "DOWNLOAD ANDROID STUDIO FOR WINDOWS" and you will start downloading the Android Studio installation package.

在网页的下方可以看到下载进度条，等待下载结束之后，就可以开始进行安装了。
You can see the download progress bar at the bottom of the web page. After waiting for the download to end, you can start the installation.


3. 安装
4. Installation

到电脑里找到刚刚下载的安装包，找到之后，点击启动该程序。
Find the installation package just downloaded in the computer, click to start the program.

在安装文件开正式始运行之前，Windows会显示一个安全警告，提问是否要运行这个来自于Internet的文件，点击运行按钮之后，安装文件正式开始运行。
Before the installation file begin to run, Windows will display a security warning asking if you want to run the file from the Internet. After clicking the Run button, the installation file start to run.

接下来，Windows还会给出一个提示，提问是否允许应用对系统进行更改。
Next, Windows will also give a prompt asking whether the application is allowed to make changes to the system.

因为我的录屏软件没有办法录下来这个提示，所以只能跟大家说一下。
Because my screen recording software cannot record this prompt, I can only tell you about it. 

遇到这个提示的时候选择允许就可以了，因为安装软件过程一定会涉及到硬盘写入，注册表写入等工作，所以这个提示对于正常安装的软件完全没有任何意义。
When encountering this prompt, it is OK to select Allow, because the software installation process will definitely involve hard disk writing, registry writing and other work, so this prompt has no meaning for normally installed software.

在在点击允许按钮之后，会显示 Welcome 界面，在 Welcome 界面显示一些开始安装之前的注意事项，在这个界面直接点击 Next 按钮，继续进行安装。
After clicking the Allow button, the Welcome interface will be displayed, and some precautions before starting the installation will be displayed in the Welcome interface. Click the Next button directly in this interface to continue the installation.

在 Welcome 界面之后，是可选组件界面，总共有两个可以选的组件，一个是必选组件 Android Studio，另一个是可选安装的 Android Virtual Devices。
After the Welcome interface, there is the Optional Components interface, with a total of two optional components, one is the required component Android Studio, and the other is the optional installation of Android Virtual Devices.

Android Virtual Devices 是用来调试 Android App 虚拟机。
Android Virtual Devices is a virtual machine used to debug Android App.

一般情况下，有两种调试 Android App 的方式，一种是使用实体硬件设备，一种是使用虚拟机。
In general, there are two ways to debug Android App, one is to use physical hardware devices and the other is to use virtual machines.

具体使用哪种方式，与每个人的习惯有关。有的人大部分时间使用虚拟机，少部分时间使用实体硬件设备。
The specific method used is related to everyone's habits. Some people use virtual machines most of the time and physical hardware devices less of the time.

我个人的习惯是大部分时间使用实体硬件设备。总之这个组件是在App开发过程中必须使用到的，所以一定要选择安装这个组件。
My personal habit is to use physical hardware equipment most of the time. In short, this component must be used in the App development process, so you must choose to install this component.

在选择好需要安装的组件之后，点击 Next 按钮，开始进行下一步安装工作。
After selecting the components to be installed, click the Next button to start the next step of installation.


接下来是选择安装路径。默认情况下，Android Studio 会安装在Program File 目录下，如果不想更改到其他目录，直接点击 Next 按钮就可以。依然是出于个人习惯原因，我会把各种工具软件安装在一个单独的地方，这样方便于统一管理。现在让我们为他重新指定一个安装目录。
The next step is to select the installation folder. By default, Android Studio is installed in the Program File folder. If you don't want to change to another directory, just click the Next button. Still for personal reasons, I like install all kinds of tools and software in a separate place, which is easy for management. Now let's specify an installation folder for it.

修改完安装目录之后，点击Next按钮。
After modifying the installation folder, click the Next button.

接下来会提示是否需要在 Window 开始菜单中选择一个目录放置 Android Studio 快捷方式，使用默认的 Android Studio 就可以。到这里，与安装相关的配置工作已经完成，点击 install 按钮之后，安装工具开始向电脑里拷贝需要安装的文件。
Next, you will be prompted whether you need to select a position in the Window Start menu to place Android Studio shortcuts. You can use the default Android Studio. Here, the configuration OF the installation has been completed. After clicking the Install button, the installation tool starts copying the files to be installed to the computer.


待文件拷贝结束之后，点击Next按钮，会提示已经完成安装，并询问是否直接启动 Android Studio。我们选择直接启动，然后点击Finish 按钮结束工作。
After the file copy is finished, clicking the Next button will prompt that the installation has been completed and ask whether to start Android Studio directly. We choose to start directly, and then click the Finish button to finish the installation.


稍等之后，会在屏幕下方出现一个闪烁的 Android Studio 进程图标。点击这个进程图标，把已经开始启动的 Android Studio 界面切换到前面来。
After a moment, a flashing Android Studio process icon appears at the bottom of the screen. Click this process icon to switch the Android Studio interface that has already started to the front of the screen.

因为这是第一次运行Android Studio，所以接下来还需要进行一些正式运行之前的配置工作。
Since this is the first time Android Studio is running, some setup work needs to be done before to use.



4. 首次运行配置
5. Setup of first run

虽然前面我们已经完成了 Android Studio 的安装，但是在首次运行之前，还需要进行一些环境配置工作。
Although we have already completed the installation of Android Studio, but it still need to do some environment setup work before use it.

首次运行的环境配置工作的第一个界面会询问是否要导入以前的配置。
The first window of the environment setup job is asks if you want to import the previous configuration.

如果在你的计算机中以前安装过 Android Studio，并且希望继续使用以前的环境配置，就选择 Config or installation folder，然后选择以前 Android Studio 的安装目录就可以。
If you have installed Android Studio on your computer and want to continue using the previous environment configuration, select Config or installation folder, and then select the previous Android Studio installation directory.

因为我们是首次安装，所以选择的是 Do not imports Setting。
Because we are installing for the first time, we chose Do not imports Setting.


点击 OK 按钮之后，出现 Android Studio 启动界面。在启动界面关闭之后，会出现配置向导的 Welcome 界面。点击 Next 按钮开始进行配置。
After clicking the OK button, the Android Studio startup window appears. After the startup window closes, the Welcome window of the Setup Wizard appears. Click the Next button to start the configuration.

接下来会提示选择安装类型。Standard 类型基本上可以满足大多数用户要求。但是为了让大家清楚，这个过程到底进行了哪些配置工作，我们选择 Custom 类型，然后点击 Next 按钮继续进行配置。
Next, you will be prompted to select the installation type. The Standard type can basically meet most user requirements. However, in order to let everyone know exactly what configuration work has been done in this process, we select the Custom type and then click the Next button to continue the configuration.


接下来会提示 JRE 的安装路径，这个路径与我们所期望的的一致，所以在这一步直接点击 Next 按钮。
Next, the JRE installation folder will be prompted, which is the same as we expected, so click the Next button to the next step.

再接下来是选择 Android Studio 的 UI 主题，左边的 Darcula 是深色主题，右边的 Light 是浅色主题。
Next, choose the UI theme for Android Studio. Darcula on the left isa dark theme and Light on the right is a light theme.

我个人习惯是使用深色主题，但是深色主题下录出来的视频不够清晰，为了方便大家能够看清操作步骤，我还是选择浅色主题。事实上，在这里无论选择哪种主题都没有关系，因为在正式启动Android Studio之后，还可以重新选择。
My personal habit is to use dark themes, but the videos recorded under dark themes are not clear enough. In order to facilitate everyone to see the operation steps clearly, I still choose light themes. In fact, it doesn't matter which theme you choose here, because you can choose again after Android Studio is launched.

下一步是选择 Android SDK 和 Intel 硬件虚拟化的安装路径。
The next step is to choose the installation path for Android SDK and Intel Hardware Virtualization.

在开发过程中，会经常查看 Android SDK 内容，特别是 Android 的源代码，所以为了方便使用，我们也把 Android SDK 安装到单独指定的路径。
During the development process, we will often check the Android SDK content, especially the Android source code, so for convenience of use, we also installed the Android SDK to a specified path.

虽然我们前面说过，开发过程会使用到虚拟机进行调试，但是虚拟机安装时间比较长，在这一步选择不安装虚拟机，等使用到他的时候在进行安装。
Although we said earlier that the development process will use the virtual machine for debugging, the installation time of the virtual machine is relatively long. In this step, we choose not to install the virtual machine, and when we use it, we will install it.


修改好 Android SDK 安装目录之后，点击 Next 按钮，继续进行安装工作。
After modifying the Android SDK installation directory, click the Next button to continue the setup work.

下一步是 Emulator 设置工作，Emulator 是一个 Android 虚拟机产品，因为在以后实际使用过程中，这些设置信息还可以重新进行调整，所有在这里选择默认设置就可以。
The next step is to set up the Emulator, which is an Android virtual machine product, because in the actual use process in the future, these setting information can be readjusted, so you can select the default setting here.

到这里所有首次启动前配置工作已经完成，配置向导会提示我们核对已经选择的配置。如果有需要调整的地方，可以倒退回去，重新进行设配置，如果没有需要调整的地方，就点击Finish按钮，完成配置工作。
At this point, all the setup work before the first startup has been completed, and the setup wizard will prompt us to check the selected configuration. If there is something error, you can go back and reset the configuration. If nothing is error, click Finish to complete the setup.

接下里是一个漫长的 Android SDK 下载过程。这个过程时间的长短取决于你的网络速度和硬盘写入速度。
The following is a long Android SDK download process. The length of this process depends on your network speed and hard disk write speed.

我们就不慢慢等待了，把这段视频直接跳过去。
We will not wait it and jump this video to the nest step.

等 Android SDK 下载完成之后，会提示已经下载的 Android SDK 内容以及他们安装的位置。
After the Android SDK download is completed, you will be prompted for the downloaded Android SDK content and where they were installed.

简单核对一下之后，点击Finish按钮，结束配置工作。接下来会显示一个界面，提示使用 Android Studio 创建新的Android项目还是打开已有的Android项目。
After a brief check, click the Finish button to finish the configuration. Next, an window will be displayed, prompting whether to use Android Studio to create a new Android project or to open an existing Android project.





5. 第一个Android项目
5. First Android Project

首次使用 Android Studio 的时候，会提示我们希望进行哪些操作。总共有六个可选项，前面三个分别是：
When we first use Android Studio, we will be prompted what we want to do. There are a total of six options, the first three are:

开始一个新的 Android 项目
Start a new Android project

打开一个已经存在的 Android 项目
Open an existing Android project

从版本控制服务器拉取一个项目出来
Pull a project out of the version control server

现在我们选择开始一个新的Android项目。接下来会提示选择项目模板。
Now we choose to start a new Android project. You will be prompted to select a project template.

从界面中可以看出，Android Studio 提供了很多类型的项目模板。
As you can see from the window, Android Studio provides many types of project templates.


我一般会选择 No Activity 模板，因为其他项目模板虽然会帮助我们搭建一个基本的项目结构，并生成相应的文件，但是实际使用的时候都需要更改，还不如进入项目之后根据需要逐个创建方便。
I usually choose No Activity template, because although other project templates will help us build a basic project structure and generate files, but they need to be changed in actual use. It is better to create them one by one after entering the project.

选择No Activity 之后点击下一步。会要求输入项目的基本信息。
Select No Activity and click next. You will be asked to enter basic information about the project.

Name 是项目名称，名称的格式要尽量简短。这个名称就是应用在设备桌面上的快捷方式名称，所以尽量控制在三个单词以内，并且以大写字母作为每个单词的开头字母，以提高辨识度。
Name is the project name, and the format of the name should be as short as possible. This name is the shortcut name applied to the desktop of the device, so try to control it within three words and use capital letters as the beginning letter of each word to improve recognition.

Package name 是项目的包名。这个名称非常像程序类的包名，但是比程序类包名有更多的用途。因为这个名称是App在设备中的唯一标志，所以尽量不要与其他 App 包名冲突，并且要有一定的含义，比如带有你所在的组织、项目用途等信息。
Package name is the package name of the project. This name is very similar to the package name of a program class, but has more uses than the package name of a program class. Since this name is the only sign of App in the device, try not to conflict with other App package names, and have certain meanings, such as information about your organization and project purpose.


比如我在 github 上的一个 Android Material 演示项目包名就是 com.jacob.material
For example, one of my Android Material demo projects on github is called com.jacob.material


Save Location 是项目的保存路径。记得一定要习惯于使用自己定义的项目路径，否则以后管理起来会非常麻烦。我们现在为这个项目指定一个保存路径。
Save Location is the path to save the project. Remember to get used to using your own defined project path, otherwise it will be very troublesome to manage in the future. We will now specify a save path for this project.


Language 是项目所采用的程序语言。可以选择 Kotlin 或者 Java。Kotlin 是 Google 主推的 Android 开发语言，不过本示例选择 Java 作为项目的开发语言。
Language is the programming language used by the project. You can choose Kotlin or Java. Kotlin is Google's main Android development language, but this example chooses Java as the development language for the project.


Minimum SDK 是项目最低兼容的SDK版本，默认API 16:Android 4.1。
The Minimum SDK is the lowest compatible SDK version for the project, with the default API 16: Android 4.1.


点击 Help Me Choose 会显示目前所有 Android 版本使用情况，以及每个版本的特征。虽然还有大概 6% 的设备在使用 Android 5 以下版本，但是这些设备大部分都处于闲置或者很少使用状态，即使依然在使用，也基本不需要安装任何新的软件，所以完全不用考虑这些设备，直接选择 Android 5 作为最低兼容版本就可以。
Clicking Help Me Choose will display the current usage of all Android versions and the characteristics of each version. Although about 6% of the devices are still using Android 5 or below, most of these devices are idle or rarely used. Even if they are still in use, they basically do not need to install any new software, so they need not consider these devices at all and can directly choose Android 5 as the lowest compatible version.


Use Legacy Support Libraries 意思是询问是否支持古老的 Android 版本，对于一个新开发的项目，完全不用选这一项。
Use Legacy Support Libraries means asking if the old version of Android is supported. For a newly developed project, you don't have to choose this item at all.

所有信息都设置好之后点击完成按钮。Android Studio 开始创建新的项目，并进入IDE环境。
After all the information is set, click the Finish button. Android Studio starts creating new projects and enters the IDE environment.

Android Studio IDE 环境我们放在下一个视频中进行介绍。
The Android Studio IDE environment is described in the next video.








