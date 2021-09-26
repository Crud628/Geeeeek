服务端：[点我](GatewayTest)

客户端：[点我](HttpClientTest)

服务端启动类为 HttpClientTest

客户端直接启动 springboot应用



执行结果

```java
HTTP/1.1 200 OK
url: http://127.0.0.1:8808 ; response: 
hello,guys
```

```java
九月 26, 2021 10:48:55 下午 io.netty.handler.logging.LoggingHandler channelRegistered
信息: [id: 0x0a89d92e] REGISTERED
九月 26, 2021 10:48:55 下午 io.netty.handler.logging.LoggingHandler bind
信息: [id: 0x0a89d92e] BIND: 0.0.0.0/0.0.0.0:8808
开启netty http服务器，监听地址和端口为 http://127.0.0.1:8808/
九月 26, 2021 10:48:55 下午 io.netty.handler.logging.LoggingHandler channelActive
信息: [id: 0x0a89d92e, L:/0:0:0:0:0:0:0:0:8808] ACTIVE
九月 26, 2021 10:49:10 下午 io.netty.handler.logging.LoggingHandler channelRead
信息: [id: 0x0a89d92e, L:/0:0:0:0:0:0:0:0:8808] READ: [id: 0x5a9964a0, L:/127.0.0.1:8808 - R:/127.0.0.1:4199]
九月 26, 2021 10:49:10 下午 io.netty.bootstrap.AbstractBootstrap setChannelOption
警告: Unknown channel option 'io.netty.channel.unix.UnixChannelOption#SO_REUSEPORT' for channel '[id: 0x5a9964a0, L:/127.0.0.1:8808 - R:/127.0.0.1:4199]'
九月 26, 2021 10:49:10 下午 io.netty.handler.logging.LoggingHandler channelReadComplete
信息: [id: 0x0a89d92e, L:/0:0:0:0:0:0:0:0:8808] READ COMPLETE
```

