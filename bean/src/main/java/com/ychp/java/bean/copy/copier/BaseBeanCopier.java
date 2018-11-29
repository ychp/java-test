package com.ychp.java.bean.copy.copier;

import com.google.common.collect.Lists;
import com.ychp.java.bean.copy.model.FromBean;
import com.ychp.java.bean.copy.model.ToBean;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author yingchengpeng
 * @date 2018/11/29
 */
public abstract class BaseBeanCopier {

    public Long testCopy(long loopTimes) {
        long cost = 0;
        for (int i = 1; i < loopTimes; i++) {
                cost += testOne(i);
        }
        return cost;
    }

    public Long testCopyWithThread(long loopTimes) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long cost = 0;
        List<Callable<Long>> callables = Lists.newArrayListWithCapacity(10);
        for (int i = 1; i < loopTimes; i++) {
            final int fi = i;
            if(callables.size() < 10) {
                callables.add( () -> testOne(fi));
                continue;
            }

            List<Future<Long>> futures = executorService.invokeAll(callables);

            for(Future<Long> future : futures) {
                cost += future.get();
            }
            callables.clear();
        }
        return cost;
    }

    private long testOne(int i) {
        ToBean toBean = new ToBean();
        FromBean fromBean = new FromBean(i);
        long pre = System.nanoTime();
        copyProperties(fromBean, toBean);
        return System.nanoTime() - pre;
    }

    public abstract void copyProperties(FromBean fromBean, ToBean toBean);

}
