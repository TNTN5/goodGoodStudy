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
    QPushButton *btnGetSize;
    QPushButton *btnSetIcon;
    QPushButton *btnSetFixedSize;
    QPushButton *btnSetMinSize;
    QPushButton *btnSetMaxSize;
    QPushButton *btnMove;
    QPushButton *btnSetTitle;
    QPushButton *btnSetIco;
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
        btnGetSize = new QPushButton(centralwidget);
        btnGetSize->setObjectName(QString::fromUtf8("btnGetSize"));

        verticalLayout->addWidget(btnGetSize);

        btnSetIcon = new QPushButton(centralwidget);
        btnSetIcon->setObjectName(QString::fromUtf8("btnSetIcon"));

        verticalLayout->addWidget(btnSetIcon);

        btnSetFixedSize = new QPushButton(centralwidget);
        btnSetFixedSize->setObjectName(QString::fromUtf8("btnSetFixedSize"));

        verticalLayout->addWidget(btnSetFixedSize);

        btnSetMinSize = new QPushButton(centralwidget);
        btnSetMinSize->setObjectName(QString::fromUtf8("btnSetMinSize"));

        verticalLayout->addWidget(btnSetMinSize);

        btnSetMaxSize = new QPushButton(centralwidget);
        btnSetMaxSize->setObjectName(QString::fromUtf8("btnSetMaxSize"));

        verticalLayout->addWidget(btnSetMaxSize);

        btnMove = new QPushButton(centralwidget);
        btnMove->setObjectName(QString::fromUtf8("btnMove"));

        verticalLayout->addWidget(btnMove);

        btnSetTitle = new QPushButton(centralwidget);
        btnSetTitle->setObjectName(QString::fromUtf8("btnSetTitle"));

        verticalLayout->addWidget(btnSetTitle);

        btnSetIco = new QPushButton(centralwidget);
        btnSetIco->setObjectName(QString::fromUtf8("btnSetIco"));

        verticalLayout->addWidget(btnSetIco);

        MainWindow->setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 21));
        MainWindow->setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        btnGetSize->setText(QCoreApplication::translate("MainWindow", "\350\216\267\345\217\226\347\252\227\345\217\243\344\275\215\347\275\256\345\222\214\345\244\247\345\260\217", nullptr));
        btnSetIcon->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\345\244\247\345\260\217\344\270\272400*400", nullptr));
        btnSetFixedSize->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\345\244\247\345\260\217\344\270\272500*500", nullptr));
        btnSetMinSize->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\345\244\247\345\260\217\344\270\272300*300", nullptr));
        btnSetMaxSize->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\345\244\247\345\260\217\344\270\272600*600", nullptr));
        btnMove->setText(QCoreApplication::translate("MainWindow", "\347\247\273\345\212\250\347\252\227\345\217\243\345\210\260100*100", nullptr));
        btnSetTitle->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\346\240\207\351\242\230", nullptr));
        btnSetIco->setText(QCoreApplication::translate("MainWindow", "\350\256\276\347\275\256\347\252\227\345\217\243\345\233\276\346\240\207", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
