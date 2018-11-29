# BeanCopy工具性能对比
- Java get/set
- Spring BeanUtils
- cglib BeanCopier

# 结果
| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| java get/set | 0.000000 ms | 0.100100 ms | 0.240325 ms | 0.140567 ms | 0.631968 ms | 5.164935 ms | 49.825440 ms | 
| spring BeanUtils | 0.000000 ms | 21.942126 ms | 5.185316 ms | 9.868235 ms | 43.416542 ms | 219.456453 ms | 2110.776457 ms | 
| cglib BeanCopier(不需要new copier) | 0.000000 ms | 0.081027 ms | 0.210082 ms | 0.139820 ms | 0.617553 ms | 4.962969 ms | 49.529179 ms | 
| cglib BeanCopier(BeanCopyUtils) | 0.000000 ms | 0.191504 ms | 0.368216 ms | 1.306776 ms | 4.607721 ms | 25.962115 ms | 255.066961 ms | 
