/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.13.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QVBoxLayout *verticalLayout;
    QPushButton *btnMax;
    QPushButton *btnNomal;
    QPushButton *btnMin;
    QPushButton *btnClose;
    QPushButton *btnSetTitle;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(800, 600);
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        verticalLayout = new QVBoxLayout(centralwidget);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        btnMax = new QPushButton(centralwidget);
        btnMax->setObjectName(QString::fromUtf8("btnMax"));

        verticalLayout->addWidget(btnMax);

        btnNomal = new QPushButton(centralwidget);
        btnNomal->setObjectName(QString::fromUtf8("btnNomal"));

        verticalLayout->addWidget(btnNomal);

        btnMin = new QPushButton(centralwidget);
        btnMin->setObjectName(QString::fromUtf8("btnMin"));

        verticalLayout->addWidget(btnMin);

        btnClose = new QPushButton(centralwidget);
        btnClose->setObjectName(QString::fromUtf8("btnClose"));

        verticalLayout->addWidget(btnClose);

        btnSetTitle = new QPushButton(centralwidget);
        btnSetTitle->setObjectName(QString::fromUtf8("btnSetTitle"));

        verticalLayout->addWidget(btnSetTitle);

        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 21));
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);

        retranslateUi(MainWindow);
        QObject::connect(btnClose, SIGNAL(clicked()), MainWindow, SLOT(close()));

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        btnMax->setText(QCoreApplication::translate("MainWindow", "\346\234\200\345\244\247\345\214\226\346\230\276\347\244\272", nullptr));
        btnNomal->setText(QCoreApplication::translate("MainWindow", "\346\255\243\345\270\270\346\230\276\347\244\272", nullptr));
        btnMin->setText(QCoreApplication::translate("MainWindow", "\346\234\200\345\260\217\345\214\226\346\230\276\347\244\272", nullptr));
        btnClose->setText(QCoreApplication::translate("MainWindow", "\345\205\263\351\227\255\347\252\227\345\217\243", nullptr));
        btnSetTitle->setText(QCoreApplication::translate("MainWindow", "\344\277\256\346\224\271\347\252\227\345\217\243\346\240\207\351\242\230", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
