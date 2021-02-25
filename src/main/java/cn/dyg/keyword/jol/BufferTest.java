package cn.dyg.keyword.jol;

/**
 * BufferTest 类是 缓存测试
 * 验证对象头中对齐填充的作用
 *
 * @author dongyinggang
 * @date 2021-02-24 10:40
 **/
public class BufferTest {

    public static void main(String[] args) {
        int[][] array = new int[5000][5000];

        // 纵向遍历
        System.out.println("纵向遍历开始");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 5000; j++) {
                array[j][i]++;
            }
        }
        System.out.println("纵向遍历结束,耗时：" + (System.currentTimeMillis() - start));

        // 横向遍历
        System.out.println("横向遍历开始");
        start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            for (int j = 0; j < 5000; j++) {
                array[i][j]++;
            }
        }
        System.out.println("横向遍历结束,耗时：" + (System.currentTimeMillis() - start));

    }
}
