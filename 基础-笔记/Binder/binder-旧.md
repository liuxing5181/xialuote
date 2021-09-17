binder概述

背景

目前linux支持的IPC包括：

管道，信号，报文（Message）队列，共享内存，套接字（Socket）



#### 1，android为什么选择Binder

从内存开销，安全方面考虑，

1.1 性能方面

​     socket支持Client-Server的通信方式，其传输效率低，开销大，主要用在跨网络的进程间通信和本机上进程间的低速通信。

消息队列和管道采用存储-转发方式，即数据先从发送方缓存区拷贝到内核开辟的缓存区中，然后再从内核缓存区拷贝到接收方缓存区，至少有两次拷贝过程

共享内存虽然无需拷贝，但控制复杂，难以使用。

1.2 安全方面

首先传统IPC的接收方无法获得对方进程可靠的UID和PID（用户ID进程ID），从而无法鉴别对方身份。Android为每个安装好的应用程序分配了自己的UID，故进程的UID是鉴别进程身份的重要标志。使用传统IPC只能由用户在数据包里填入UID和PID，但这样不可靠，容易被恶意程序利用。可靠的身份标记只有由IPC机制本身在内核中添加。其次传统IPC访问接入点是开放的，无法建立私有通道。比如命名管道的名称，systemV的键值，socket的ip地址或文件名都是开放的，只要知道这些接入点的程序都可以和对端建立连接，不管怎样都无法阻止恶意程序通过猜测接收方地址获得连接。

**Binder基于Client-Server通信模式，传输过程只需一次拷贝，为发送发添加UID/PID身份，既支持实名Binder也支持匿名Binder，安全性高。**

#### 2，Binder实现机制

2.1Binder指的是一种通信机制

2.2 对于Server进程来说，Binder指的是Binder本地对象，对于Client来说，Binder指的是Binder代理对象，对于传输过程而言，binder是可以进行跨进程传递的对象。

#### 3，C/S结构

Binder的通信模型有4个角色：Binder Client、Binder Server、Binder Driver（Binder驱动）、ServiceManager。

首先是有一个ServiceManager，刚开始这个通讯录是空白的，然后Server进程向ServiceManager注册一个映射关系表，之后Client进程想要和Server进程通信，首先向ServiceManager查询地址，ServiceManager收到查询的请求之后，返回查询结果给Client。

注意到这里不管是Server进程注册，还是Client查询，都是经过Binder驱动的，这也真是Binder驱动的作用所在，



#### 4，Binder通信模式

4.1 Server进程向ServiceManager注册，告诉ServiceManager我是谁，我有什么，我能做什么。就好比徐同学（Server进程）有一台笔记本（computer对象），这台笔记本有个add方法。这时映射关系表就生成了。

4.2 Client进程向ServiceManager查询，我要调用Server进程的computer对象的add方法，可以看到这个过程经过Binder驱动，这时候Binder驱动就开始发挥他的作用了。当向ServiceManager查询完毕，是返回一个computer对象给Client进程吗？其实不然，Binder驱动将computer对象转换成了computerProxy对象，并转发给了Client进程，因此，Client进程拿到的并不是真实的computer对象，而是一个代理对象，即computerProxy对象。很容易理解这个computerProxy对象也是有add方法，（如果连add方法都没有，岂不是欺骗了Client？），但是这个add方法只是对参数进行一些包装而已。

4.3 当Client进程调用add方法，这个消息发送给Binder驱动，这时驱动发现，原来是computerProxy，那么Client进程应该是需要调用computer对象的add方法的，这时驱动通知Server进程，调用你的computer对象的add方法，将结果给我。然后Server进程就将计算结果发送给驱动，驱动再转发给Client进程，这时Client进程还蒙在了鼓里，他以为自己调用的是真实的computer对象的add方法，其实他只是调用了代理而已。不过Client最终还是拿到了计算结果。

1，Service 向ServiceManager注册生成映射关系表，

2，Client想ServiceManager 查询请求computer对象的x方法，

3，Servicemanager 返回computerProxy对象，只是一个代理对象

4，Client调用x方法 消息发送到Binder驱动，驱动会通知Server调用x方法，结果返回给binder驱动，驱动在转发给Client进程。



ServiceManager

#### 5，Binder角色定位

##
进程间通信的本质内存的拷贝
管道 类似于中介。
送快递
发送方 - 快递员 = 一次拷贝
快递员 - 接收方 = 二次拷贝
#binder怎么解决 快递柜的出现
发送方 - 快递员 = 一次拷贝
快递员 - 快递柜 = 无需拷贝
binder 进程 拷贝 发生在哪里，发生在调用端（clint）而不是服务端service


#平时用到多进程地方 
系统保活，推送，插件化，内存不够，webView，输入，电话，闹钟



#进程通讯机制
socket。共享内存。管道。文件。信号。广播。binder


         
#MMAP--内存映射
虚拟地址--映射到物理地址。