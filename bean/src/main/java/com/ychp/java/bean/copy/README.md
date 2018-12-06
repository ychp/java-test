# BeanCopy工具性能对比
- Java get/set
- Apache BeanUtils
- Spring BeanUtils
- cglib BeanCopier

# 结果
| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| java get/set | 0.000 ms | 0.070 ms | 0.247 ms | 0.224 ms | 0.520 ms | 5.083 ms | 59.377 ms | 
| apache BeanUtils | 0.000 ms | 24.757 ms | 17.119 ms | 83.261 ms | 324.445 ms | 3286.659 ms | 35469.126 ms | 
| spring BeanUtils | 0.000 ms | 24.709 ms | 7.129 ms | 13.308 ms | 21.944 ms | 258.051 ms | 2360.481 ms | 
| cglib BeanCopier(不需要new copier) | 0.000 ms | 0.069 ms | 0.072 ms | 0.125 ms | 0.576 ms | 6.545 ms | 56.318 ms | 
| cglib BeanCopier(BeanCopyUtils) | 0.000 ms | 0.290 ms | 0.246 ms | 1.008 ms | 2.991 ms | 30.855 ms | 303.033 ms | 

# 结论
在必须使用BeanCopy工具时，优先使用cglib，其次是spring beanUtils
在性能要求比较高的情况下，建议还是使用java的get/set