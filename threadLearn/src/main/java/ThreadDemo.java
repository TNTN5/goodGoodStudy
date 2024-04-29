public class ThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyThread thread=new MyThread(i);
            thread.start();
        }
    }
}

class MyThread extends Thread{
    private int id;

    public MyThread(int id){
        this.id=id;
    }
    public void run(){
        System.out.println("Thread "+id+" is running");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
