#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "subwidget.h"
#include <QDebug>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

#if 0
    SubWidget *subWidget=new SubWidget();
    subWidget->setWindowTitle("subWidget");
    subWidget->show();
#endif

#if 0
    SubWidget *subWidget=new SubWidget(this);
    subWidget->setWindowTitle("subWidget");
#endif



}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_btnGetSize_clicked()
{
    qDebug()<<"------------------------------------------";
    QRect rect=this->geometry();
    qDebug()<<"左上角："<<rect.topLeft();
    qDebug()<<"右上角："<<rect.topLeft();
    qDebug()<<"右下角："<<rect.topLeft();
    qDebug()<<"左下角："<<rect.topLeft();
    qDebug() << "宽度: " << rect.width();
    qDebug() << "高度: " << rect.height();

}

void MainWindow::on_btnSetIcon_clicked()
{
    this->resize(400,400);
}

void MainWindow::on_btnSetFixedSize_clicked()
{
    this->resize(500,500);
}

void MainWindow::on_btnSetMinSize_clicked()
{
    this->resize(300,300);
}

void MainWindow::on_btnSetMaxSize_clicked()
{
    this->resize(600,600);
}

void MainWindow::on_btnMove_clicked()
{
    this->move(100,100);
}

void MainWindow::on_btnSetTitle_clicked()
{
    this->setWindowTitle("演示QWidget");
}

void MainWindow::on_btnSetIco_clicked()
{
    this->setWindowIcon(QIcon(":/icon/uninstallerIcon.ico"));
}
