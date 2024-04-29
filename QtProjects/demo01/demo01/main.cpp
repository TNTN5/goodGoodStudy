#include "mainwindow.h"

#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow w;
    w.setFixedSize(1800,1200);
    w.setWindowTitle("窗口标题");
    w.show();
    return a.exec();
}
