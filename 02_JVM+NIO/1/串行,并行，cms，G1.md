### GC日志分析



> 环境 
>
> Windows 10  20H2   X64      16G
>
> JDK17 version:2021-09-14



#### 一.  默认情况

```shell
 java -XX:+PrintGCDetails .\GCLogAnalysis.java
```



执行结果

```shell
Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF-8
[0.002s][warning][gc] -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.
[0.007s][info   ][gc] Using G1
[0.008s][info   ][gc,init] Version: 17+35-LTS-2724 (release)
[0.009s][info   ][gc,init] CPUs: 8 total, 8 available
[0.010s][info   ][gc,init] Memory: 16183M
[0.010s][info   ][gc,init] Large Page Support: Disabled
[0.010s][info   ][gc,init] NUMA Support: Disabled
[0.010s][info   ][gc,init] Compressed Oops: Enabled (Zero based)
[0.010s][info   ][gc,init] Heap Region Size: 2M
[0.010s][info   ][gc,init] Heap Min Capacity: 8M
[0.010s][info   ][gc,init] Heap Initial Capacity: 254M
[0.010s][info   ][gc,init] Heap Max Capacity: 4046M
[0.010s][info   ][gc,init] Pre-touch: Disabled
[0.010s][info   ][gc,init] Parallel Workers: 8
[0.010s][info   ][gc,init] Concurrent Workers: 2
[0.010s][info   ][gc,init] Concurrent Refinement Workers: 8
[0.010s][info   ][gc,init] Periodic GC: Disabled
[0.011s][info   ][gc,metaspace] CDS archive(s) mapped at: [0x0000000800000000-0x0000000800bc0000-0x0000000800bc0000), size 12320768, SharedBaseAddress: 0x0000000800000000, ArchiveRelocationMode: 0.
[0.011s][info   ][gc,metaspace] Compressed class space mapped at: 0x0000000800c00000-0x0000000840c00000, reserved size: 1073741824
[0.011s][info   ][gc,metaspace] Narrow klass base: 0x0000000800000000, Narrow klass shift: 0, Narrow klass range: 0x100000000
[0.368s][info   ][gc,start    ] GC(0) Pause Young (Normal) (G1 Evacuation Pause)
[0.369s][info   ][gc,task     ] GC(0) Using 6 workers of 8 for evacuation
...省略
[1.411s][info   ][gc,heap     ] GC(18) Archive regions: 0->0
[1.412s][info   ][gc,heap     ] GC(18) Humongous regions: 0->0
[1.412s][info   ][gc,metaspace] GC(18) Metaspace: 9372K(9600K)->9372K(9600K) NonClass: 8261K(8384K)->8261K(8384K) Class: 1111K(1216K)->1111K(1216K)
[1.412s][info   ][gc          ] GC(18) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 1433M->995M(2628M) 22.212ms
[1.412s][info   ][gc,cpu      ] GC(18) User=0.12s Sys=0.00s Real=0.02s
执行结束!共生成对象次数:21894
[1.415s][info   ][gc,heap,exit] Heap
[1.416s][info   ][gc,heap,exit]  garbage-first heap   total 2691072K, used 1021911K [0x0000000703200000, 0x0000000800000000)
[1.416s][info   ][gc,heap,exit]   region size 2048K, 43 young (88064K), 42 survivors (86016K)
[1.416s][info   ][gc,heap,exit]  Metaspace       used 9388K, committed 9600K, reserved 1064960K
[1.416s][info   ][gc,heap,exit]   class space    used 1114K, committed 1216K, reserved 1048576K
```



##### 说明

16G内存，默认情况下使用`1/4`  4G最大堆内存   小于1G则会使用`1/2` 

`-XX:PrintGCDataStamps`  会显示时间

`-Xloggc:(filename)` 写入到文件里面 



#### 使用1G内存的情况

```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1G .\GCLogAnalysis.java
```

JDK8

```shell
正在执行...
2021-09-25T17:11:32.685+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.685+0800: [ParNew: 279616K->34943K(314560K), 0.0110115 secs] 279616K->83764K(1013632K), 0.0110686 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
# ParNew: 279616K->34943K(314560K)  youngGC
2021-09-25T17:11:32.726+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.726+0800: [ParNew: 314559K->34943K(314560K), 0.0136094 secs] 363380K->165505K(1013632K), 0.0136605 secs] [Times: user=0.00 sys=0.13, real=0.01 secs] 
2021-09-25T17:11:32.767+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.767+0800: [ParNew: 314559K->34943K(314560K), 0.0282932 secs] 445121K->249028K(1013632K), 0.0283351 secs] [Times: user=0.05 sys=0.02, real=0.03 secs] 
2021-09-25T17:11:32.823+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.823+0800: [ParNew: 314559K->34944K(314560K), 0.0258498 secs] 528644K->328045K(1013632K), 0.0258852 secs] [Times: user=0.03 sys=0.03, real=0.03 secs] 
2021-09-25T17:11:32.872+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.872+0800: [ParNew: 314560K->34942K(314560K), 0.0281640 secs] 607661K->415233K(1013632K), 0.0282084 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-09-25T17:11:32.901+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 380291K(699072K)] 421226K(1013632K), 0.0005705 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:32.902+0800: [CMS-concurrent-mark-start]
2021-09-25T17:11:32.903+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:32.903+0800: [CMS-concurrent-preclean-start]
2021-09-25T17:11:32.904+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:32.904+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T17:11:32.927+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.927+0800: [ParNew: 314558K->34943K(314560K), 0.0265226 secs] 694849K->490428K(1013632K), 0.0265609 secs] [Times: user=0.06 sys=0.00, real=0.03 secs] 
2021-09-25T17:11:32.978+0800: [GC (Allocation Failure) 2021-09-25T17:11:32.978+0800: [ParNew: 314559K->34940K(314560K), 0.0250440 secs] 770044K->566657K(1013632K), 0.0250998 secs] [Times: user=0.05 sys=0.01, real=0.03 secs] 
2021-09-25T17:11:33.027+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.027+0800: [ParNew: 314556K->34943K(314560K), 0.0261273 secs] 846273K->646176K(1013632K), 0.0261644 secs] [Times: user=0.14 sys=0.02, real=0.03 secs] 
2021-09-25T17:11:33.076+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.076+0800: [ParNew (promotion failed): 314559K->314559K(314560K), 0.0295634 secs]2021-09-25T17:11:33.106+0800: [CMS2021-09-25T17:11:33.106+0800: [CMS-concurrent-abortable-preclean: 0.008/0.202 secs] [Times: user=0.36 sys=0.05, real=0.20 secs] 
 (concurrent mode failure): 698207K->360780K(699072K), 0.0364534 secs] 925792K->360780K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0660634 secs] [Times: user=0.06 sys=0.03, real=0.07 secs] 
2021-09-25T17:11:33.167+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.167+0800: [ParNew: 279616K->34943K(314560K), 0.0094167 secs] 640396K->452463K(1013632K), 0.0094573 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.176+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 417519K(699072K)] 458282K(1013632K), 0.0002806 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.177+0800: [CMS-concurrent-mark-start]
2021-09-25T17:11:33.178+0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.178+0800: [CMS-concurrent-preclean-start]
2021-09-25T17:11:33.179+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.179+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T17:11:33.202+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.202+0800: [ParNew: 314559K->34943K(314560K), 0.0117762 secs] 732079K->531293K(1013632K), 0.0118128 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.240+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.240+0800: [ParNew: 314559K->34943K(314560K), 0.0108668 secs] 810909K->611704K(1013632K), 0.0109000 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.276+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.276+0800: [ParNew: 314559K->34942K(314560K), 0.0108592 secs] 891320K->693844K(1013632K), 0.0108952 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.289+0800: [CMS-concurrent-abortable-preclean: 0.007/0.111 secs] [Times: user=0.17 sys=0.00, real=0.11 secs] 
2021-09-25T17:11:33.289+0800: [GC (CMS Final Remark) [YG occupancy: 51934 K (314560 K)]2021-09-25T17:11:33.289+0800: [Rescan (parallel) , 0.0003453 secs]2021-09-25T17:11:33.289+0800: [weak refs processing, 0.0000088 secs]2021-09-25T17:11:33.289+0800: [class unloading, 0.0001601 secs]2021-09-25T17:11:33.290+0800: [scrub symbol table, 0.0002439 secs]2021-09-25T17:11:33.290+0800: [scrub string table, 0.0000699 secs][1 CMS-remark: 658902K(699072K)] 710837K(1013632K), 0.0008750 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.290+0800: [CMS-concurrent-sweep-start]
2021-09-25T17:11:33.291+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.291+0800: [CMS-concurrent-reset-start]
2021-09-25T17:11:33.292+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.315+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.315+0800: [ParNew: 314558K->34943K(314560K), 0.0108042 secs] 889199K->690968K(1013632K), 0.0108373 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.326+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 656024K(699072K)] 691146K(1013632K), 0.0001178 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.326+0800: [CMS-concurrent-mark-start]
2021-09-25T17:11:33.328+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.328+0800: [CMS-concurrent-preclean-start]
2021-09-25T17:11:33.329+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.329+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T17:11:33.329+0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.329+0800: [GC (CMS Final Remark) [YG occupancy: 63211 K (314560 K)]2021-09-25T17:11:33.329+0800: [Rescan (parallel) , 0.0003131 secs]2021-09-25T17:11:33.330+0800: [weak refs processing, 0.0000085 secs]2021-09-25T17:11:33.330+0800: [class unloading, 0.0001604 secs]2021-09-25T17:11:33.330+0800: [scrub symbol table, 0.0002508 secs]2021-09-25T17:11:33.330+0800: [scrub string table, 0.0000745 secs][1 CMS-remark: 656024K(699072K)] 719235K(1013632K), 0.0008521 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.330+0800: [CMS-concurrent-sweep-start]
2021-09-25T17:11:33.331+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.331+0800: [CMS-concurrent-reset-start]
2021-09-25T17:11:33.332+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.353+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.353+0800: [ParNew: 314559K->34943K(314560K), 0.0116752 secs] 669523K->464177K(1013632K), 0.0117062 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.365+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 429233K(699072K)] 464915K(1013632K), 0.0001611 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.365+0800: [CMS-concurrent-mark-start]
2021-09-25T17:11:33.367+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.367+0800: [CMS-concurrent-preclean-start]
2021-09-25T17:11:33.368+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.368+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T17:11:33.390+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.390+0800: [ParNew: 314559K->34943K(314560K), 0.0110492 secs] 743793K->547558K(1013632K), 0.0110855 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.428+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.428+0800: [ParNew: 314559K->34942K(314560K), 0.0108358 secs] 827174K->622827K(1013632K), 0.0108709 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.464+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.464+0800: [ParNew: 314558K->34941K(314560K), 0.0105489 secs] 902443K->699361K(1013632K), 0.0105844 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.474+0800: [CMS-concurrent-abortable-preclean: 0.006/0.106 secs] [Times: user=0.16 sys=0.00, real=0.11 secs] 
2021-09-25T17:11:33.474+0800: [GC (CMS Final Remark) [YG occupancy: 41028 K (314560 K)]2021-09-25T17:11:33.474+0800: [Rescan (parallel) , 0.0003451 secs]2021-09-25T17:11:33.475+0800: [weak refs processing, 0.0000088 secs]2021-09-25T17:11:33.475+0800: [class unloading, 0.0001686 secs]2021-09-25T17:11:33.475+0800: [scrub symbol table, 0.0002582 secs]2021-09-25T17:11:33.475+0800: [scrub string table, 0.0000732 secs][1 CMS-remark: 664419K(699072K)] 705448K(1013632K), 0.0009010 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.475+0800: [CMS-concurrent-sweep-start]
2021-09-25T17:11:33.477+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.477+0800: [CMS-concurrent-reset-start]
2021-09-25T17:11:33.477+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.500+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.500+0800: [ParNew: 314557K->34943K(314560K), 0.0105227 secs] 896550K->687796K(1013632K), 0.0105589 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.511+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 652852K(699072K)] 688084K(1013632K), 0.0001261 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.511+0800: [CMS-concurrent-mark-start]
2021-09-25T17:11:33.513+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.05 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.513+0800: [CMS-concurrent-preclean-start]
2021-09-25T17:11:33.514+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.514+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T17:11:33.514+0800: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.514+0800: [GC (CMS Final Remark) [YG occupancy: 58221 K (314560 K)]2021-09-25T17:11:33.514+0800: [Rescan (parallel) , 0.0003107 secs]2021-09-25T17:11:33.514+0800: [weak refs processing, 0.0000093 secs]2021-09-25T17:11:33.514+0800: [class unloading, 0.0001616 secs]2021-09-25T17:11:33.514+0800: [scrub symbol table, 0.0003477 secs]2021-09-25T17:11:33.515+0800: [scrub string table, 0.0001090 secs][1 CMS-remark: 652852K(699072K)] 711074K(1013632K), 0.0009843 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.515+0800: [CMS-concurrent-sweep-start]
2021-09-25T17:11:33.517+0800: [CMS-concurrent-sweep: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.517+0800: [CMS-concurrent-reset-start]
2021-09-25T17:11:33.518+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.541+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.541+0800: [ParNew: 314559K->34943K(314560K), 0.0120492 secs] 656408K->454364K(1013632K), 0.0120912 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.580+0800: [GC (Allocation Failure) 2021-09-25T17:11:33.580+0800: [ParNew: 314559K->34943K(314560K), 0.0115816 secs] 733980K->536304K(1013632K), 0.0116158 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T17:11:33.592+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 501360K(699072K)] 542624K(1013632K), 0.0001807 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T17:11:33.592+0800: [CMS-concurrent-mark-start]
执行结束!共生成对象次数:22098
Heap
 par new generation   total 314560K, used 56029K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,   7% used [0x00000000c0000000, 0x00000000c14977b8, 0x00000000d1110000)
  from space 34944K,  99% used [0x00000000d3330000, 0x00000000d554fd80, 0x00000000d5550000)
  to   space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
 concurrent mark-sweep generation total 699072K, used 501360K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3007K, capacity 4490K, committed 4864K, reserved 1056768K
  class space    used 318K, capacity 386K, committed 512K, reserved 1048576K
```





#### 串行

```shell
 java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1G -XX:+UseSerialGC .\GCLogAnalysis.java
```



```shell
正在执行...
2021-09-25T18:33:29.467+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.467+0800: [DefNew: 279504K->34944K(314560K), 0.0254950 secs] 279504K->82405K(1013632K), 0.0255606 secs] [Times: user=0.00 sys=0.01, real=0.03 secs] 
2021-09-25T18:33:29.533+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.533+0800: [DefNew: 314560K->34943K(314560K), 0.0330604 secs] 362021K->163057K(1013632K), 0.0331094 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-25T18:33:29.595+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.595+0800: [DefNew: 314559K->34943K(314560K), 0.0237705 secs] 442673K->237712K(1013632K), 0.0238023 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
2021-09-25T18:33:29.644+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.644+0800: [DefNew: 314559K->34943K(314560K), 0.0249217 secs] 517328K->317923K(1013632K), 0.0249519 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
2021-09-25T18:33:29.694+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.694+0800: [DefNew: 314389K->34943K(314560K), 0.0255572 secs] 597368K->394220K(1013632K), 0.0255901 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2021-09-25T18:33:29.746+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.746+0800: [DefNew: 314559K->34943K(314560K), 0.0266359 secs] 673836K->478579K(1013632K), 0.0266848 secs] [Times: user=0.00 sys=0.03, real=0.02 secs] 
2021-09-25T18:33:29.797+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.797+0800: [DefNew: 314559K->34943K(314560K), 0.0241521 secs] 758195K->557205K(1013632K), 0.0241840 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
2021-09-25T18:33:29.845+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.845+0800: [DefNew: 314452K->34943K(314560K), 0.0241349 secs] 836715K->633981K(1013632K), 0.0241642 secs] [Times: user=0.00 sys=0.01, real=0.02 secs] 
2021-09-25T18:33:29.894+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.895+0800: [DefNew: 314559K->34943K(314560K), 0.0249051 secs] 913597K->713820K(1013632K), 0.0249392 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
2021-09-25T18:33:29.945+0800: [GC (Allocation Failure) 2021-09-25T18:33:29.945+0800: [DefNew: 314559K->314559K(314560K), 0.0000123 secs]2021-09-25T18:33:29.945+0800: [Tenured: 678876K->389570K(699072K), 0.0304509 secs] 993436K->389570K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0305080 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-25T18:33:30.002+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.002+0800: [DefNew: 279616K->34944K(314560K), 0.0075494 secs] 669186K->473887K(1013632K), 0.0075814 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.036+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.036+0800: [DefNew: 314560K->34943K(314560K), 0.0094305 secs] 753503K->542863K(1013632K), 0.0094647 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.072+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.072+0800: [DefNew: 314559K->34943K(314560K), 0.0114745 secs] 822479K->624844K(1013632K), 0.0115092 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.110+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.110+0800: [DefNew: 314452K->34943K(314560K), 0.0099614 secs] 904352K->708517K(1013632K), 0.0099939 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.145+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.145+0800: [DefNew: 314559K->314559K(314560K), 0.0000288 secs]2021-09-25T18:33:30.145+0800: [Tenured: 673573K->410634K(699072K), 0.0325437 secs] 988133K->410634K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0326407 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-25T18:33:30.201+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.201+0800: [DefNew: 279616K->34943K(314560K), 0.0068483 secs] 690250K->493965K(1013632K), 0.0068794 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.233+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.233+0800: [DefNew: 314559K->34943K(314560K), 0.0094144 secs] 773581K->570480K(1013632K), 0.0094459 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.267+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.267+0800: [DefNew: 314426K->34943K(314560K), 0.0099179 secs] 849963K->653297K(1013632K), 0.0099570 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:33:30.303+0800: [GC (Allocation Failure) 2021-09-25T18:33:30.303+0800: [DefNew: 314507K->314507K(314560K), 0.0000131 secs]2021-09-25T18:33:30.303+0800: [Tenured: 618353K->411806K(699072K), 0.0321563 secs] 932860K->411806K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0322199 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
执行结束!共生成对象次数:20786
Heap
 def new generation   total 314560K, used 203756K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,  72% used [0x00000000c0000000, 0x00000000cc6fb3d8, 0x00000000d1110000)
  from space 34944K,   0% used [0x00000000d1110000, 0x00000000d1110000, 0x00000000d3330000)
  to   space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
 tenured generation   total 699072K, used 411806K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
   the space 699072K,  58% used [0x00000000d5550000, 0x00000000ee7779f8, 0x00000000ee777a00, 0x0000000100000000)
 Metaspace       used 3007K, capacity 4490K, committed 4864K, reserved 1056768K
  class space    used 318K, capacity 386K, committed 512K, reserved 1048576K
```



#### 并行（JDK8默认）

```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1G -XX:+UseParalleGC .\GCLogAnalysis.java
```



```shell
正在执行...
2021-09-25T18:38:20.080+0800: [GC (Allocation Failure) [PSYoungGen: 262144K->43506K(305664K)] 262144K->77248K(1005056K), 0.0091456 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.120+0800: [GC (Allocation Failure) [PSYoungGen: 305650K->43518K(305664K)] 339392K->159035K(1005056K), 0.0135356 secs] [Times: user=0.02 sys=0.11, real=0.01 secs] 
2021-09-25T18:38:20.161+0800: [GC (Allocation Failure) [PSYoungGen: 305662K->43517K(305664K)] 421179K->224506K(1005056K), 0.0115380 secs] [Times: user=0.03 sys=0.09, real=0.01 secs] 
2021-09-25T18:38:20.197+0800: [GC (Allocation Failure) [PSYoungGen: 305661K->43518K(305664K)] 486650K->297718K(1005056K), 0.0125890 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.234+0800: [GC (Allocation Failure) [PSYoungGen: 305662K->43516K(305664K)] 559862K->370516K(1005056K), 0.0123886 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.272+0800: [GC (Allocation Failure) [PSYoungGen: 305660K->43509K(160256K)] 632660K->446686K(859648K), 0.0123959 secs] [Times: user=0.00 sys=0.13, real=0.01 secs] 
2021-09-25T18:38:20.295+0800: [GC (Allocation Failure) [PSYoungGen: 160205K->72435K(232960K)] 563382K->484745K(932352K), 0.0081479 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.315+0800: [GC (Allocation Failure) [PSYoungGen: 189171K->89588K(232960K)] 601481K->510544K(932352K), 0.0085496 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.333+0800: [GC (Allocation Failure) [PSYoungGen: 206280K->104152K(232960K)] 627236K->541411K(932352K), 0.0106403 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.355+0800: [GC (Allocation Failure) [PSYoungGen: 220888K->70499K(232960K)] 658147K->566611K(932352K), 0.0113577 secs] [Times: user=0.09 sys=0.03, real=0.01 secs] 
2021-09-25T18:38:20.377+0800: [GC (Allocation Failure) [PSYoungGen: 187235K->37650K(232960K)] 683347K->594377K(932352K), 0.0093140 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.399+0800: [GC (Allocation Failure) [PSYoungGen: 154386K->40824K(232960K)] 711113K->630416K(932352K), 0.0069194 secs] [Times: user=0.08 sys=0.03, real=0.01 secs] 
2021-09-25T18:38:20.418+0800: [GC (Allocation Failure) [PSYoungGen: 157560K->39320K(232960K)] 747152K->664969K(932352K), 0.0071737 secs] [Times: user=0.05 sys=0.06, real=0.01 secs] 
2021-09-25T18:38:20.425+0800: [Full GC (Ergonomics) [PSYoungGen: 39320K->0K(232960K)] [ParOldGen: 625648K->330413K(699392K)] 664969K->330413K(932352K), [Metaspace: 3000K->3000K(1056768K)], 0.0343267 secs] [Times: user=0.20 sys=0.00, real=0.03 secs] 
2021-09-25T18:38:20.471+0800: [GC (Allocation Failure) [PSYoungGen: 116736K->38191K(232960K)] 447149K->368605K(932352K), 0.0035067 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:38:20.486+0800: [GC (Allocation Failure) [PSYoungGen: 154927K->41237K(232960K)] 485341K->405370K(932352K), 0.0064372 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.503+0800: [GC (Allocation Failure) [PSYoungGen: 157973K->40548K(232960K)] 522106K->440265K(932352K), 0.0069685 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.521+0800: [GC (Allocation Failure) [PSYoungGen: 156721K->38792K(232960K)] 556438K->472053K(932352K), 0.0063248 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.540+0800: [GC (Allocation Failure) [PSYoungGen: 155528K->38434K(232960K)] 588789K->506892K(932352K), 0.0063414 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.560+0800: [GC (Allocation Failure) [PSYoungGen: 154948K->42501K(232960K)] 623406K->545555K(932352K), 0.0068239 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.577+0800: [GC (Allocation Failure) [PSYoungGen: 159237K->40216K(232960K)] 662291K->581204K(932352K), 0.0068278 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.596+0800: [GC (Allocation Failure) [PSYoungGen: 156952K->38019K(232960K)] 697940K->613809K(932352K), 0.0063691 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.613+0800: [GC (Allocation Failure) [PSYoungGen: 154755K->44951K(232960K)] 730545K->655134K(932352K), 0.0073392 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.632+0800: [GC (Allocation Failure) [PSYoungGen: 161687K->34100K(232960K)] 771870K->683135K(932352K), 0.0063886 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.637+0800: [Full GC (Ergonomics) [PSYoungGen: 34100K->0K(232960K)] [ParOldGen: 649035K->363804K(699392K)] 683135K->363804K(932352K), [Metaspace: 3000K->3000K(1056768K)], 0.0341846 secs] [Times: user=0.33 sys=0.00, real=0.03 secs] 
2021-09-25T18:38:20.684+0800: [GC (Allocation Failure) [PSYoungGen: 116728K->42613K(232960K)] 480533K->406418K(932352K), 0.0036514 secs] [Times: user=0.13 sys=0.00, real=0.00 secs] 
2021-09-25T18:38:20.699+0800: [GC (Allocation Failure) [PSYoungGen: 159323K->40792K(232960K)] 523128K->442568K(932352K), 0.0069488 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.717+0800: [GC (Allocation Failure) [PSYoungGen: 157528K->43520K(237568K)] 559304K->481156K(936960K), 0.0069574 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.734+0800: [GC (Allocation Failure) [PSYoungGen: 164864K->43988K(232960K)] 602500K->521441K(932352K), 0.0073521 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.752+0800: [GC (Allocation Failure) [PSYoungGen: 165020K->41527K(246272K)] 642473K->557364K(945664K), 0.0072202 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.773+0800: [GC (Allocation Failure) [PSYoungGen: 181303K->48222K(242688K)] 697140K->600222K(942080K), 0.0076288 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.794+0800: [GC (Allocation Failure) [PSYoungGen: 187926K->48528K(256000K)] 739926K->642053K(955392K), 0.0084962 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.818+0800: [GC (Allocation Failure) [PSYoungGen: 206736K->45818K(251392K)] 800261K->683013K(950784K), 0.0079593 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.840+0800: [GC (Allocation Failure) [PSYoungGen: 204026K->53649K(262144K)] 841221K->732318K(961536K), 0.0085449 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.848+0800: [Full GC (Ergonomics) [PSYoungGen: 53649K->0K(262144K)] [ParOldGen: 678668K->380378K(699392K)] 732318K->380378K(961536K), [Metaspace: 3000K->3000K(1056768K)], 0.0361507 secs] [Times: user=0.19 sys=0.00, real=0.04 secs] 
2021-09-25T18:38:20.902+0800: [GC (Allocation Failure) [PSYoungGen: 172544K->62927K(259584K)] 552922K->443306K(958976K), 0.0055947 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.926+0800: [GC (Allocation Failure) [PSYoungGen: 235471K->55369K(263168K)] 615850K->487599K(962560K), 0.0093606 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.953+0800: [GC (Allocation Failure) [PSYoungGen: 230457K->61286K(261120K)] 662687K->539480K(960512K), 0.0092312 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:38:20.979+0800: [GC (Allocation Failure) [PSYoungGen: 236390K->55080K(265216K)] 714584K->586652K(964608K), 0.0093538 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 
执行结束!共生成对象次数:20332
Heap
 PSYoungGen      total 265216K, used 62350K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 178688K, 4% used [0x00000000eab00000,0x00000000eb2196a0,0x00000000f5980000)
  from space 86528K, 63% used [0x00000000fab80000,0x00000000fe14a240,0x0000000100000000)
  to   space 83968K, 0% used [0x00000000f5980000,0x00000000f5980000,0x00000000fab80000)
 ParOldGen       total 699392K, used 531571K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 76% used [0x00000000c0000000,0x00000000e071ced8,0x00000000eab00000)
 Metaspace       used 3007K, capacity 4490K, committed 4864K, reserved 1056768K
  class space    used 318K, capacity 386K, committed 512K, reserved 1048576K
```



#### CMS

```shell
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1G -XX:+UseConcMarkSweepGC .\GCLogAnalysis.java
```



```shell
正在执行...
2021-09-25T18:48:31.736+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.736+0800: [ParNew: 279616K->34943K(314560K), 0.0111999 secs] 279616K->90143K(1013632K), 0.0112789 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:31.778+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.778+0800: [ParNew: 314559K->34944K(314560K), 0.0145244 secs] 369759K->173004K(1013632K), 0.0145708 secs] [Times: user=0.09 sys=0.03, real=0.01 secs] 
2021-09-25T18:48:31.818+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.818+0800: [ParNew: 314560K->34944K(314560K), 0.0255370 secs] 452620K->254036K(1013632K), 0.0255718 secs] [Times: user=0.03 sys=0.03, real=0.03 secs] 
2021-09-25T18:48:31.871+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.871+0800: [ParNew: 314560K->34942K(314560K), 0.0264816 secs] 533652K->336962K(1013632K), 0.0265233 secs] [Times: user=0.05 sys=0.01, real=0.03 secs] 
2021-09-25T18:48:31.925+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.925+0800: [ParNew: 314558K->34944K(314560K), 0.0239345 secs] 616578K->410206K(1013632K), 0.0239696 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2021-09-25T18:48:31.949+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 375262K(699072K)] 417145K(1013632K), 0.0007717 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:31.950+0800: [CMS-concurrent-mark-start]
2021-09-25T18:48:31.952+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:31.952+0800: [CMS-concurrent-preclean-start]
2021-09-25T18:48:31.952+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:31.952+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T18:48:31.976+0800: [GC (Allocation Failure) 2021-09-25T18:48:31.976+0800: [ParNew: 314560K->34943K(314560K), 0.0254797 secs] 689822K->487190K(1013632K), 0.0255167 secs] [Times: user=0.05 sys=0.02, real=0.02 secs] 
2021-09-25T18:48:32.027+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.027+0800: [ParNew: 314559K->34943K(314560K), 0.0273640 secs] 766806K->568844K(1013632K), 0.0274030 secs] [Times: user=0.13 sys=0.03, real=0.03 secs] 
2021-09-25T18:48:32.080+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.080+0800: [ParNew: 314559K->34942K(314560K), 0.0253118 secs] 848460K->644932K(1013632K), 0.0253620 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-25T18:48:32.130+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.130+0800: [ParNew: 314015K->34943K(314560K), 0.0261975 secs] 924004K->726101K(1013632K), 0.0262410 secs] [Times: user=0.05 sys=0.03, real=0.03 secs] 
2021-09-25T18:48:32.156+0800: [CMS-concurrent-abortable-preclean: 0.008/0.203 secs] [Times: user=0.36 sys=0.08, real=0.20 secs] 
2021-09-25T18:48:32.156+0800: [GC (CMS Final Remark) [YG occupancy: 41323 K (314560 K)]2021-09-25T18:48:32.156+0800: [Rescan (parallel) , 0.0006033 secs]2021-09-25T18:48:32.157+0800: [weak refs processing, 0.0000094 secs]2021-09-25T18:48:32.157+0800: [class unloading, 0.0002036 secs]2021-09-25T18:48:32.157+0800: [scrub symbol table, 0.0002632 secs]2021-09-25T18:48:32.157+0800: [scrub string table, 0.0000726 secs][1 CMS-remark: 691158K(699072K)] 732481K(1013632K), 0.0011996 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.157+0800: [CMS-concurrent-sweep-start]
2021-09-25T18:48:32.158+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.158+0800: [CMS-concurrent-reset-start]
2021-09-25T18:48:32.159+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.183+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.183+0800: [ParNew: 314559K->34943K(314560K), 0.0119079 secs] 859794K->662052K(1013632K), 0.0119549 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.195+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 627108K(699072K)] 668442K(1013632K), 0.0001227 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.195+0800: [CMS-concurrent-mark-start]
2021-09-25T18:48:32.196+0800: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.196+0800: [CMS-concurrent-preclean-start]
2021-09-25T18:48:32.197+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.197+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T18:48:32.223+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.223+0800: [ParNew: 314559K->314559K(314560K), 0.0000367 secs]2021-09-25T18:48:32.223+0800: [CMS2021-09-25T18:48:32.223+0800: [CMS-concurrent-abortable-preclean: 0.001/0.026 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
 (concurrent mode failure): 627108K->364380K(699072K), 0.0366583 secs] 941668K->364380K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0367487 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2021-09-25T18:48:32.288+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.288+0800: [ParNew: 279616K->34942K(314560K), 0.0092595 secs] 643996K->449583K(1013632K), 0.0092955 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.298+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 414640K(699072K)] 455378K(1013632K), 0.0008904 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.299+0800: [CMS-concurrent-mark-start]
2021-09-25T18:48:32.300+0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.300+0800: [CMS-concurrent-preclean-start]
2021-09-25T18:48:32.301+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.301+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T18:48:32.325+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.325+0800: [ParNew: 314558K->34943K(314560K), 0.0114718 secs] 729199K->529368K(1013632K), 0.0115212 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.362+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.362+0800: [ParNew: 314559K->34943K(314560K), 0.0099955 secs] 808984K->597786K(1013632K), 0.0100309 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.399+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.399+0800: [ParNew: 314559K->34943K(314560K), 0.0109089 secs] 877402K->678155K(1013632K), 0.0109430 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.435+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.435+0800: [ParNew: 314559K->314559K(314560K), 0.0000146 secs]2021-09-25T18:48:32.435+0800: [CMS2021-09-25T18:48:32.435+0800: [CMS-concurrent-abortable-preclean: 0.008/0.135 secs] [Times: user=0.25 sys=0.00, real=0.13 secs] 
 (concurrent mode failure): 643211K->372211K(699072K), 0.0323435 secs] 957771K->372211K(1013632K), [Metaspace: 3000K->3000K(1056768K)], 0.0324136 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2021-09-25T18:48:32.494+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.494+0800: [ParNew: 279616K->34943K(314560K), 0.0082617 secs] 651827K->449274K(1013632K), 0.0082957 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.503+0800: [GC (CMS Initial Mark) [1 CMS-initial-mark: 414330K(699072K)] 454928K(1013632K), 0.0004339 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.504+0800: [CMS-concurrent-mark-start]
2021-09-25T18:48:32.505+0800: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.505+0800: [CMS-concurrent-preclean-start]
2021-09-25T18:48:32.506+0800: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.506+0800: [CMS-concurrent-abortable-preclean-start]
2021-09-25T18:48:32.529+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.529+0800: [ParNew: 314559K->34943K(314560K), 0.0111495 secs] 728890K->530088K(1013632K), 0.0111844 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.568+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.568+0800: [ParNew: 314559K->34941K(314560K), 0.0115582 secs] 809704K->610473K(1013632K), 0.0115942 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.605+0800: [GC (Allocation Failure) 2021-09-25T18:48:32.605+0800: [ParNew: 314557K->34943K(314560K), 0.0109765 secs] 890089K->680348K(1013632K), 0.0110107 secs] [Times: user=0.13 sys=0.00, real=0.01 secs] 
2021-09-25T18:48:32.618+0800: [CMS-concurrent-abortable-preclean: 0.007/0.112 secs] [Times: user=0.23 sys=0.00, real=0.11 secs] 
2021-09-25T18:48:32.618+0800: [GC (CMS Final Remark) [YG occupancy: 53736 K (314560 K)]2021-09-25T18:48:32.618+0800: [Rescan (parallel) , 0.0003341 secs]2021-09-25T18:48:32.619+0800: [weak refs processing, 0.0000117 secs]2021-09-25T18:48:32.619+0800: [class unloading, 0.0002497 secs]2021-09-25T18:48:32.619+0800: [scrub symbol table, 0.0003157 secs]2021-09-25T18:48:32.619+0800: [scrub string table, 0.0000755 secs][1 CMS-remark: 645404K(699072K)] 699140K(1013632K), 0.0010425 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.619+0800: [CMS-concurrent-sweep-start]
2021-09-25T18:48:32.620+0800: [CMS-concurrent-sweep: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2021-09-25T18:48:32.620+0800: [CMS-concurrent-reset-start]
2021-09-25T18:48:32.621+0800: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
执行结束!共生成对象次数:21803
Heap
 par new generation   total 314560K, used 210152K [0x00000000c0000000, 0x00000000d5550000, 0x00000000d5550000)
  eden space 279616K,  62% used [0x00000000c0000000, 0x00000000cab1a520, 0x00000000d1110000)
  from space 34944K,  99% used [0x00000000d1110000, 0x00000000d332fec0, 0x00000000d3330000)
  to   space 34944K,   0% used [0x00000000d3330000, 0x00000000d3330000, 0x00000000d5550000)
 concurrent mark-sweep generation total 699072K, used 557100K [0x00000000d5550000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3007K, capacity 4490K, committed 4864K, reserved 1056768K
  class space    used 318K, capacity 386K, committed 512K, reserved 1048576K

```



#### G1

```shell
-XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1G -XX:+UseG1GC 
```



```shell
正在执行...
2021-09-25T19:01:54.319+0800: [GC pause (G1 Evacuation Pause) (young) 68554K->23503K(1024M), 0.0037539 secs]
2021-09-25T19:01:54.333+0800: [GC pause (G1 Evacuation Pause) (young) 79388K->44070K(1024M), 0.0030986 secs]
2021-09-25T19:01:54.346+0800: [GC pause (G1 Evacuation Pause) (young) 101M->62823K(1024M), 0.0031283 secs]
2021-09-25T19:01:54.363+0800: [GC pause (G1 Evacuation Pause) (young) 139M->89617K(1024M), 0.0051543 secs]
2021-09-25T19:01:54.385+0800: [GC pause (G1 Evacuation Pause) (young) 184M->122M(1024M), 0.0048145 secs]
2021-09-25T19:01:54.420+0800: [GC pause (G1 Evacuation Pause) (young) 281M->169M(1024M), 0.0067574 secs]
2021-09-25T19:01:54.453+0800: [GC pause (G1 Evacuation Pause) (young) 349M->219M(1024M), 0.0063395 secs]
2021-09-25T19:01:54.493+0800: [GC pause (G1 Evacuation Pause) (young) 463M->288M(1024M), 0.0097835 secs]
2021-09-25T19:01:54.536+0800: [GC pause (G1 Evacuation Pause) (young) 556M->365M(1024M), 0.0104511 secs]
2021-09-25T19:01:54.578+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 644M->425M(1024M), 0.0116141 secs]
2021-09-25T19:01:54.589+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.589+0800: [GC concurrent-root-region-scan-end, 0.0001884 secs]
2021-09-25T19:01:54.589+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.590+0800: [GC concurrent-mark-end, 0.0013497 secs]
2021-09-25T19:01:54.591+0800: [GC remark, 0.0018517 secs]
2021-09-25T19:01:54.592+0800: [GC cleanup 442M->429M(1024M), 0.0006260 secs]
2021-09-25T19:01:54.593+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.593+0800: [GC concurrent-cleanup-end, 0.0000207 secs]
2021-09-25T19:01:54.648+0800: [GC pause (G1 Evacuation Pause) (young) 808M->504M(1024M), 0.0134075 secs]
2021-09-25T19:01:54.661+0800: [GC pause (G1 Evacuation Pause) (mixed) 513M->423M(1024M), 0.0064016 secs]
2021-09-25T19:01:54.668+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 427M->424M(1024M), 0.0016854 secs]
2021-09-25T19:01:54.670+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.670+0800: [GC concurrent-root-region-scan-end, 0.0001083 secs]
2021-09-25T19:01:54.670+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.671+0800: [GC concurrent-mark-end, 0.0011517 secs]
2021-09-25T19:01:54.671+0800: [GC remark, 0.0012412 secs]
2021-09-25T19:01:54.672+0800: [GC cleanup 437M->434M(1024M), 0.0003909 secs]
2021-09-25T19:01:54.673+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.673+0800: [GC concurrent-cleanup-end, 0.0000135 secs]
2021-09-25T19:01:54.713+0800: [GC pause (G1 Evacuation Pause) (young)-- 844M->573M(1024M), 0.0097600 secs]
2021-09-25T19:01:54.724+0800: [GC pause (G1 Evacuation Pause) (mixed) 584M->524M(1024M), 0.0076193 secs]
2021-09-25T19:01:54.732+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 527M->524M(1024M), 0.0009002 secs]
2021-09-25T19:01:54.733+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.733+0800: [GC concurrent-root-region-scan-end, 0.0000712 secs]
2021-09-25T19:01:54.733+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.734+0800: [GC concurrent-mark-end, 0.0012796 secs]
2021-09-25T19:01:54.734+0800: [GC remark, 0.0011003 secs]
2021-09-25T19:01:54.736+0800: [GC cleanup 535M->529M(1024M), 0.0003849 secs]
2021-09-25T19:01:54.736+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.736+0800: [GC concurrent-cleanup-end, 0.0000143 secs]
2021-09-25T19:01:54.769+0800: [GC pause (G1 Evacuation Pause) (young)-- 868M->668M(1024M), 0.0078883 secs]
2021-09-25T19:01:54.778+0800: [GC pause (G1 Evacuation Pause) (mixed) 688M->580M(1024M), 0.0059336 secs]
2021-09-25T19:01:54.790+0800: [GC pause (G1 Evacuation Pause) (mixed) 635M->538M(1024M), 0.0059574 secs]
2021-09-25T19:01:54.796+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 539M->539M(1024M), 0.0016217 secs]
2021-09-25T19:01:54.797+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.797+0800: [GC concurrent-root-region-scan-end, 0.0000777 secs]
2021-09-25T19:01:54.797+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.799+0800: [GC concurrent-mark-end, 0.0014593 secs]
2021-09-25T19:01:54.799+0800: [GC remark, 0.0011642 secs]
2021-09-25T19:01:54.800+0800: [GC cleanup 553M->542M(1024M), 0.0004397 secs]
2021-09-25T19:01:54.801+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.801+0800: [GC concurrent-cleanup-end, 0.0000161 secs]
2021-09-25T19:01:54.830+0800: [GC pause (G1 Evacuation Pause) (young)-- 854M->675M(1024M), 0.0085917 secs]
2021-09-25T19:01:54.841+0800: [GC pause (G1 Evacuation Pause) (mixed) 696M->590M(1024M), 0.0067092 secs]
2021-09-25T19:01:54.854+0800: [GC pause (G1 Evacuation Pause) (mixed) 645M->559M(1024M), 0.0059034 secs]
2021-09-25T19:01:54.860+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 560M->559M(1024M), 0.0013634 secs]
2021-09-25T19:01:54.862+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.862+0800: [GC concurrent-root-region-scan-end, 0.0000971 secs]
2021-09-25T19:01:54.862+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.863+0800: [GC concurrent-mark-end, 0.0013615 secs]
2021-09-25T19:01:54.863+0800: [GC remark, 0.0010949 secs]
2021-09-25T19:01:54.864+0800: [GC cleanup 572M->565M(1024M), 0.0004194 secs]
2021-09-25T19:01:54.865+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.865+0800: [GC concurrent-cleanup-end, 0.0000153 secs]
2021-09-25T19:01:54.892+0800: [GC pause (G1 Evacuation Pause) (young)-- 865M->685M(1024M), 0.0073816 secs]
2021-09-25T19:01:54.901+0800: [GC pause (G1 Evacuation Pause) (mixed) 710M->598M(1024M), 0.0059868 secs]
2021-09-25T19:01:54.912+0800: [GC pause (G1 Evacuation Pause) (mixed) 648M->552M(1024M), 0.0061441 secs]
2021-09-25T19:01:54.920+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 561M->554M(1024M), 0.0019234 secs]
2021-09-25T19:01:54.922+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.922+0800: [GC concurrent-root-region-scan-end, 0.0001154 secs]
2021-09-25T19:01:54.922+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.924+0800: [GC concurrent-mark-end, 0.0014335 secs]
2021-09-25T19:01:54.924+0800: [GC remark, 0.0012008 secs]
2021-09-25T19:01:54.925+0800: [GC cleanup 568M->564M(1024M), 0.0004595 secs]
2021-09-25T19:01:54.926+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.926+0800: [GC concurrent-cleanup-end, 0.0000124 secs]
2021-09-25T19:01:54.954+0800: [GC pause (G1 Evacuation Pause) (young)-- 857M->624M(1024M), 0.0089516 secs]
2021-09-25T19:01:54.965+0800: [GC pause (G1 Evacuation Pause) (mixed) 646M->538M(1024M), 0.0065223 secs]
2021-09-25T19:01:54.977+0800: [GC pause (G1 Evacuation Pause) (mixed) 590M->491M(1024M), 0.0055429 secs]
2021-09-25T19:01:54.983+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 492M->491M(1024M), 0.0011605 secs]
2021-09-25T19:01:54.984+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:54.984+0800: [GC concurrent-root-region-scan-end, 0.0001020 secs]
2021-09-25T19:01:54.984+0800: [GC concurrent-mark-start]
2021-09-25T19:01:54.986+0800: [GC concurrent-mark-end, 0.0015351 secs]
2021-09-25T19:01:54.986+0800: [GC remark, 0.0011741 secs]
2021-09-25T19:01:54.987+0800: [GC cleanup 504M->503M(1024M), 0.0004697 secs]
2021-09-25T19:01:54.988+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:54.988+0800: [GC concurrent-cleanup-end, 0.0000118 secs]
2021-09-25T19:01:55.024+0800: [GC pause (G1 Evacuation Pause) (young)-- 881M->736M(1024M), 0.0052389 secs]
2021-09-25T19:01:55.033+0800: [GC pause (G1 Evacuation Pause) (mixed) 764M->663M(1024M), 0.0076888 secs]
2021-09-25T19:01:55.047+0800: [GC pause (G1 Evacuation Pause) (mixed) 720M->664M(1024M), 0.0040741 secs]
2021-09-25T19:01:55.051+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 667M->664M(1024M), 0.0023125 secs]
2021-09-25T19:01:55.053+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:55.053+0800: [GC concurrent-root-region-scan-end, 0.0000933 secs]
2021-09-25T19:01:55.053+0800: [GC concurrent-mark-start]
2021-09-25T19:01:55.055+0800: [GC concurrent-mark-end, 0.0015325 secs]
2021-09-25T19:01:55.055+0800: [GC remark, 0.0011370 secs]
2021-09-25T19:01:55.056+0800: [GC cleanup 681M->670M(1024M), 0.0004629 secs]
2021-09-25T19:01:55.057+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:55.057+0800: [GC concurrent-cleanup-end, 0.0000138 secs]
2021-09-25T19:01:55.073+0800: [GC pause (G1 Evacuation Pause) (young) 836M->695M(1024M), 0.0065202 secs]
2021-09-25T19:01:55.084+0800: [GC pause (G1 Evacuation Pause) (mixed) 735M->609M(1024M), 0.0048375 secs]
2021-09-25T19:01:55.094+0800: [GC pause (G1 Evacuation Pause) (mixed) 658M->537M(1024M), 0.0050401 secs]
2021-09-25T19:01:55.104+0800: [GC pause (G1 Evacuation Pause) (mixed) 591M->490M(1024M), 0.0081923 secs]
2021-09-25T19:01:55.118+0800: [GC pause (G1 Evacuation Pause) (mixed) 543M->497M(1024M), 0.0052143 secs]
2021-09-25T19:01:55.123+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 497M->497M(1024M), 0.0016685 secs]
2021-09-25T19:01:55.125+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:55.125+0800: [GC concurrent-root-region-scan-end, 0.0000868 secs]
2021-09-25T19:01:55.125+0800: [GC concurrent-mark-start]
2021-09-25T19:01:55.127+0800: [GC concurrent-mark-end, 0.0013572 secs]
2021-09-25T19:01:55.127+0800: [GC remark, 0.0011408 secs]
2021-09-25T19:01:55.128+0800: [GC cleanup 514M->510M(1024M), 0.0004332 secs]
2021-09-25T19:01:55.129+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:55.129+0800: [GC concurrent-cleanup-end, 0.0000163 secs]
2021-09-25T19:01:55.158+0800: [GC pause (G1 Evacuation Pause) (young) 823M->571M(1024M), 0.0088312 secs]
2021-09-25T19:01:55.169+0800: [GC pause (G1 Evacuation Pause) (mixed) 593M->503M(1024M), 0.0091969 secs]
2021-09-25T19:01:55.185+0800: [GC pause (G1 Evacuation Pause) (mixed) 563M->518M(1024M), 0.0062953 secs]
2021-09-25T19:01:55.192+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 518M->517M(1024M), 0.0017855 secs]
2021-09-25T19:01:55.194+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:55.194+0800: [GC concurrent-root-region-scan-end, 0.0001043 secs]
2021-09-25T19:01:55.194+0800: [GC concurrent-mark-start]
2021-09-25T19:01:55.195+0800: [GC concurrent-mark-end, 0.0014482 secs]
2021-09-25T19:01:55.195+0800: [GC remark, 0.0011507 secs]
2021-09-25T19:01:55.196+0800: [GC cleanup 533M->526M(1024M), 0.0004468 secs]
2021-09-25T19:01:55.197+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:55.197+0800: [GC concurrent-cleanup-end, 0.0000158 secs]
2021-09-25T19:01:55.226+0800: [GC pause (G1 Evacuation Pause) (young) 832M->579M(1024M), 0.0084823 secs]
2021-09-25T19:01:55.236+0800: [GC pause (G1 Evacuation Pause) (mixed) 601M->514M(1024M), 0.0077540 secs]
2021-09-25T19:01:55.249+0800: [GC pause (G1 Evacuation Pause) (mixed) 566M->512M(1024M), 0.0053451 secs]
2021-09-25T19:01:55.255+0800: [GC pause (G1 Humongous Allocation) (young) (initial-mark) 513M->513M(1024M), 0.0010405 secs]
2021-09-25T19:01:55.256+0800: [GC concurrent-root-region-scan-start]
2021-09-25T19:01:55.256+0800: [GC concurrent-root-region-scan-end, 0.0000902 secs]
2021-09-25T19:01:55.256+0800: [GC concurrent-mark-start]
2021-09-25T19:01:55.257+0800: [GC concurrent-mark-end, 0.0013449 secs]
2021-09-25T19:01:55.257+0800: [GC remark, 0.0011719 secs]
2021-09-25T19:01:55.259+0800: [GC cleanup 527M->524M(1024M), 0.0004572 secs]
2021-09-25T19:01:55.259+0800: [GC concurrent-cleanup-start]
2021-09-25T19:01:55.259+0800: [GC concurrent-cleanup-end, 0.0000147 secs]
执行结束!共生成对象次数:21348
```

