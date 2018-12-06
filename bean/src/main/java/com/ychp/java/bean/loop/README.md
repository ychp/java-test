# 循环性能对比
- native
- foreach
- iterator
- lambdas

# 结果

| 方式 | 10 | 100 | 1000 | 10000 |
| --- | --- | --- | --- | --- |
| native | 0.078 ms | 3.177 ms | 74.740 ms | 4243.402 ms | 
| foreach | 0.180 ms | 5.581 ms | 29.423 ms | 1612.920 ms | 
| iterator | 0.152 ms | 4.965 ms | 74.992 ms | 3861.439 ms | 
| lambdas | 15.317 ms | 2.535 ms | 50.191 ms | 1552.320 ms | 


# 结论
推荐使用的优先级为 lambdas > foreach > iterator > native
