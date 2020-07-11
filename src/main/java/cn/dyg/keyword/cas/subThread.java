package cn.dyg.keyword.cas;

/**
 * subThread 类是 子线程类
 *
 * @author dongyinggang
 * @date 2020-07-08 18:29
 **/
public class subThread implements Runnable {

    private volatile Integer i ;
    public subThread(Integer i){
        this.i = i;
    }

    @Override
    public void run() {
        i++;
        System.out.println(i);
    }
}
