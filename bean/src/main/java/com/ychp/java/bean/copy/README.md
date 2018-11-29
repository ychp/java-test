# BeanCopy工具性能对比
- Java get/set
- Spring BeanUtils
- cglib BeanCopier

# 结果
| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| java get/set | 0.000 ms | 0.057 ms | 0.354 ms | 0.190 ms | 0.867 ms | 5.169 ms | 59.244 ms | 
| spring BeanUtils | 0.000 ms | 28.799 ms | 13.219 ms | 13.559 ms | 45.580 ms | 205.999 ms | 2373.020 ms | 
| cglib BeanCopier | 0.000 ms | 0.129 ms | 0.298 ms | 0.104 ms | 0.802 ms | 4.986 ms | 54.235 ms | 
| cglib BeanCopier(BeanCopyUtils) | 0.000 ms | 1.091 ms | 0.410 ms | 1.002 ms | 5.484 ms | 25.265 ms | 295.116 ms | 
