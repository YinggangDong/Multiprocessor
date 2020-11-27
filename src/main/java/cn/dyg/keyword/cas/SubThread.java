package cn.dyg.keyword.cas;

/**
 * SubThread 类是 子线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 18:29
 **/
public class SubThread implements Runnable {

    private Integer i;

    public SubThread(Integer i) {
        this.i = i;
    }

    @Override
    public void run() {
        synchronized (this){
            for (int j = 0; j < 100; j++) {
                i++;
            }

        }
        System.out.println(Thread.currentThread().getName() + "的a：" + i);


    }
}
