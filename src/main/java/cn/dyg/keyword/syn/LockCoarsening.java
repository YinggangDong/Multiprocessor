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
 * LockCoarsening 类是 锁粗化技术
 *
 * @author dongyinggang
 * @date 2021-03-06 16:44
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(2)
@State(Scope.Benchmark)
public class LockCoarsening {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(LockCoarsening.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    @Benchmark
    public static void coarsening() {
        Object object = new Object();
        int count = 0;
        for (int i = 0; i < 10000; i++) {
            synchronized (object) {
                count++;
            }
        }
    }

    @Benchmark
    public static void base() {
        Object object = new Object();
        int count = 0;
        synchronized (object) {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }
}
