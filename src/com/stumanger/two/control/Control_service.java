package com.stumanger.two.control;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.stumanger.two.biz.StudentBiz;
import com.stumanger.two.biz.impl.StudentBizImpl;
import com.stumanger.two.domain.Student;
import com.stumanger.two.util.Rule;
import com.stumanger.two.util.Watch;

//负责接收客户端连接创建线程
public class Control_service {

	private ServerSocket server;
	public static final int PORT = 10086;
	private Socket client;
	// 创建集合存储当前有几个线程
	List<ControlThread> list;
	/*// 创建守护线程
	private Watch watch;
*/
	//创建线程池对象
	private ExecutorService es;
	public Control_service() {
		super();

		try {
			System.out.print("正在开启服务器");
			/*for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print(".");
			}*/
			System.out.println();
			list = new ArrayList<>();
			this.server = new ServerSocket(PORT);
			System.out.println("服务器已开启！");
		
			// this.client = server.accept();
			
			/*
			 *自设计线程池调用 
			 * watch = new Watch(list);
			watch.start();*/
			//实例化线程池
//			es=Executors.newFixedThreadPool(10);
			es=Executors.newCachedThreadPool();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void start() {
		// 创建while循环为死循环 服务器一旦开启 不关闭
		while (true) {
			try {
				this.client = server.accept();
				System.out.println(this.client.getInetAddress() + "已连接！");
				// 创建线程对象
				ControlThread ct = new ControlThread(client);
				//返回当前处于活跃状态的线程
				es.submit(ct);
				int activeCount = ((ThreadPoolExecutor)es).getActiveCount();
			
				System.out.println("当前在线活跃用户："+activeCount);
				//list.add(ct);
//				ct.start();
				//System.out.println("当前在线人数：" + list.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
