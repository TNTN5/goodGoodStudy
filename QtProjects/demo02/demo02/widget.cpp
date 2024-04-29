#include "widget.h"
#include "ui_widget.h"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);
    pixmap1=new QPixmap(":/image/bra.png");
    pixmap2=new QPixmap(":/image/briefs.png");
    connect(ui->pushButton,SIGNAL(clicked()),this,SLOT(showBra()));
    connect(ui->pushButton_2,SIGNAL(clicked()),this,SLOT(showBriefs()));
    ui->label->setPixmap(*pixmap2);
}

Widget::~Widget()
{
    delete ui;
}
void Widget::showBra(void){
    pixmap1->scaled(ui->label->size());
    ui->label->setPixmap(*pixmap1);
}

void Widget::showBriefs(void){
    pixmap2->scaled(ui->label->size());
    ui->label->setPixmap(*pixmap2);
}

