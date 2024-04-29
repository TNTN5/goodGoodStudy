#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_btnGetSize_clicked();

    void on_btnSetIcon_clicked();

    void on_btnSetFixedSize_clicked();

    void on_btnSetMinSize_clicked();

    void on_btnSetMaxSize_clicked();

    void on_btnMove_clicked();

    void on_btnSetTitle_clicked();

    void on_btnSetIco_clicked();

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
