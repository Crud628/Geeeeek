#### GC与堆内存使用的总结

当我们项目运行之后，一旦达到堆内存的上限，便会发生一次Full GC，十分影响使用的体验。可以使用Xmx，Xms等参数来适当调整，查看一段时间的fullGC频率以及GC时间。因为这是硬件条件，所以，最简单的就是加机器。在软件条件上，应适当优化项目，减少对象的产生和浪费。还有调整GC策略，多核的CPU可以考虑并行GC，CMS，以及G1。

详细的来看，三者GC的复杂度是递增的

CMS是这几个阶段

> 初始标记(CMS Initial Mark)—— 标记GC root能直接关联的对象(短暂STW)
>
> 并发标记(CMS Concurrent Mark)—— GCRootsTracing，从并发标记中的root遍历，对不可达的对象进行标记
>
> 重新标记(CMS Remark)—— 修正并发标记期间因为用户操作导致标记发生变更的对象，有STW
>
> 并发清除(CMS Concurrent Sweep)

G1

> 初始标记(Initial Mark)—— 标记GC root能直接关联的对象(短暂STW)
>
> 并发标记(Concurrent mark)—— GCRootsTracing，从并发标记中的root遍历，对不可达的对象进行标记，耗时长但可并行
>
> 最终标记(Final Remark)—— 收集并发标记期间产生的新垃圾(短暂STW),采用了SATB算法比CMS更快
>
> 筛选回收(Live Data Counting and Evacuation)—— 对各个Region的回收性价比排序，在保证时间可控的情况下清除失活对象，清除Remember Sets



G1的效率更高，但是会因为回收过大退化到串行影响效率。