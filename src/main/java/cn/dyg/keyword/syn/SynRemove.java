package cn.dyg.keyword.syn;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * SynRemove 类是 锁消除,JDK 对 synchronized 的优化措施
 *
 * @author dongyinggang
 * @date 2021-03-04 15:17
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(2)
@State(Scope.Benchmark)
public class SynRemove {

    int x;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SynRemove.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public void baseline() {
        x++;
    }

    /**
     * 虽然使用了 synchronized
     * 但由于 obj 是局部变量，每次调用都会是新的对象，
     * 因此实际会被优化，执行锁消除，和没有 synchronized 的代码效率一样高
     * 如果锁消除被关闭：则会慢很多,JVM参数增加
     */
    @Benchmark
    public void locked() {
        synchronized (new Object()) {
            x++;
        }
    }
}
