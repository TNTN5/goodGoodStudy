/********************************************************************************
** Form generated from reading UI file 'subwidget.ui'
**
** Created by: Qt User Interface Compiler version 5.13.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SUBWIDGET_H
#define UI_SUBWIDGET_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_SubWidget
{
public:
    QPushButton *pushButton;

    void setupUi(QWidget *SubWidget)
    {
        if (SubWidget->objectName().isEmpty())
            SubWidget->setObjectName(QString::fromUtf8("SubWidget"));
        SubWidget->resize(400, 300);
        SubWidget->setCursor(QCursor(Qt::PointingHandCursor));
        pushButton = new QPushButton(SubWidget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setGeometry(QRect(160, 179, 141, 41));

        retranslateUi(SubWidget);

        QMetaObject::connectSlotsByName(SubWidget);
    } // setupUi

    void retranslateUi(QWidget *SubWidget)
    {
        SubWidget->setWindowTitle(QCoreApplication::translate("SubWidget", "Form", nullptr));
        pushButton->setText(QCoreApplication::translate("SubWidget", "PushButton", nullptr));
    } // retranslateUi

};

namespace Ui {
    class SubWidget: public Ui_SubWidget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SUBWIDGET_H
