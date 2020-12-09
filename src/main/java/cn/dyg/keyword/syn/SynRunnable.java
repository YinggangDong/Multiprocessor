package cn.dyg.keyword.syn;

/**
 * SynRunnable 类是
 *
 * @author dongyinggang
 * @date 2020-12-09 18:17
 **/
public class SynRunnable implements Runnable {

    private SynObj synObj;

    public SynRunnable(SynObj synObj){
        this.synObj = synObj;
    }

    @Override
    public void run() {
        synObj.instanceMethod();
    }
}
