```c++
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);		//初始化，有且只有一个QApplication对象，主要用于管理Qt GUI应用程序的实例和环境									   变量映射，是每个Qt GUI应用程序所必须的
    MainWindow w;    				//Qt界面显示MainWindow
    w.show();
    return a.exec();				//主事件循环，程序运行停在这里等待事件的发生，等待用户操作窗口
}

```





### 信号和槽

信号：

只有 QObject 及其派生类才能使用信号和槽机制，且在类之中还需要Q_OBJECT宏。当一个对象的内部状态发生改变时，如果其他对象对它的状态需要做出反应，这时就应该让这个类发出状态改变的事件，这个事件就是事件信号。声明信号（signals）发送信号（emit）

​	1、所用信号声明都是公有的，所以不能在signals前面加public、private、protected

​	2、所有的信号都没有返回值，所以返回值都用void。

​	3、所有的信号只需要定义，不需要实现。

​	4、在同一个线程中，当一个信号被emit发出时，会立即执行其槽函数，等槽函数执行完毕后，才会执行emit后面的代码，如果一个	信号链接了多个槽，那么会等所有的槽函数执行完毕后才执行后面的代码，槽函数的执行顺序是按照它们的链接时的顺序执行的。

​	5、信号与槽机制要求信号与槽的参数类型一致

槽函数：

 槽函数其实就是普通的函数，唯一的特点是可以与信号链接。参数类型要与信号一致，允许信号参数比槽函数多，信号的参数顺序必须和信号前面几个一致起来，后面多出来的在槽函数中选择忽略。声明槽：slots;不需要参数名称

信号和槽通过connect链接

<img src="C:\Users\Apollo\AppData\Roaming\Typora\typora-user-images\image-20230602030337806.png" alt="image-20230602030337806" style="zoom:20%;" />



信号和槽连接的五种方式

```c++
//    SINAL/SLOT(编译时不检查)
//    函数地址（编译时检查）
//    转到槽
//    信号槽编辑器
//    lambda表达式
    connect(ui->btnMax,SIGNAL(clicked()),this,SLOT(showMaximized()));
    connect(ui->btnNomal,&QPushButton::clicked,this,&QMainWindow::showNormal);
    connect(ui->btnSetTitle,&QPushButton::clicked,this,[this](){
        this->setWindowTitle("连接槽的五种方式");
    });
```





