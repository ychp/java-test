# BeanCopy工具性能对比
- Java get/set
- Spring BeanUtils
- cglib BeanCopier
- yangtu BeanCopyUtils

# 结果
| 类库 | 1 | 10 | 100 | 1000 | 10000 | 100000 | 1000000 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| java get/set | 0 | 0 | 1 | 8 | 12 | 7 | 56 | 
| spring BeanUtils | 0 | 22 | 10 | 20 | 38 | 226 | 2009 | 
| cglib BeanCopier(不需要new copier) | 0 | 0 | 0 | 1 | 3 | 5 | 60 | 
| cglib BeanCopier(每次new copier) | 0 | 1 | 3 | 7 | 8 | 16 | 145 | 
| yangtu BeanCopyUtils(指定source) | 0 | 2 | 0 | 2 | 4 | 15 | 124 | 
| yangtu BeanCopyUtils(未指定source) | 0 | 48 | 1 | 3 | 3 | 12 | 122 | 
