#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

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
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_btnMin_clicked()
{
    this->showMinimized();
}
