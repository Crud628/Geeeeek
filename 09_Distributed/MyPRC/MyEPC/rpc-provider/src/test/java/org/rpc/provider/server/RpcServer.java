package org.rpc.provider.server;

import org.rpc.provider.handler.RpcServerHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Service
public class RpcServer implements DisposableBean {

	private NioEventLoopGroup bossGroup;
	private NioEventLoopGroup workerGroup;

	
    @Autowired
    RpcServerHandler rpcServerHandler;
	public void startServer(String ip, int port) {
		// 1.创建线程组
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();

		try {
			// 2.创建服务端启动助手
			ServerBootstrap serverBootstrap = new ServerBootstrap();

			// 3.设置启动参数
			serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							// TODO Auto-generated method stub
							ChannelPipeline pipeline = channel.pipeline();
							// 添加编解码器
							pipeline.addLast(new StringDecoder());
							pipeline.addLast(new StringEncoder());

							// 业务处理类 TODO
							pipeline.addLast(rpcServerHandler);
						}

					});

			// 4.绑定端口号
			ChannelFuture sync = serverBootstrap.bind(ip, port).sync();
			System.out.println("--------------服务端启动成功-----------------");
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bossGroup != null) {
				bossGroup.shutdownGracefully();
			}

			if (workerGroup != null) {
				workerGroup.shutdownGracefully();
			}
		}
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		if (bossGroup != null) {
			bossGroup.shutdownGracefully();
		}

		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
		}
	}

}
