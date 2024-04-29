#include "mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
{
    pButton =new QPushButton("按钮",this);
    pButton->setGeometry(20,20,100,50);
}

MainWindow::~MainWindow()
{
}

