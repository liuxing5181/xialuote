##Glide原理
https://www.cnblogs.com/billshen/p/13306285.html
Glide
三步走
Glide.with(content)
     .load(url)
     .into(imageView);
with绑定生命周期，load指定加载资源，into指明加载目标

with
传入上下文环境context，调用RequestManagerRetriever.get()方法获取RequestManagerRetriever对象，然后通过这个对象获取RequestManager对象

1，传入得是application，不需要处理生命周期（应用的生命周期）通过调用getApplicationManager()来获取一个RequestManager对象
   非主线程使用glide，不管是activity还是fragment，都是强制当成application来处理

2，activity/fragment，会向当前的Activity当中添加一个隐藏的Fragment，返回RequestManager对象
   创建隐藏fragment的目的是Glide需要知道加载的生命周期，可是Glide并没有办法知道Activity的生命周期，
   于是Glide就使用了添加隐藏Fragment的这种小技巧，因为Fragment的生命周期和Activity是同步的，如果Activity被销毁了，
   Fragment是可以监听到的，这样Glide就可以捕获这个事件并停止图片加载了。


load
参数类型：String,File,byte[],URL,图片资源ID,Uri,我这里只看参数类型为String类型的，这些方法都会返回DrawableTypeRequest对象。


into()方法的作用：
①初始化各种参数，做好准备工作（网络请求、基于MVP的各种接口回调），
②使用最原始的HTTPConnect网络连接，读取文件流，
③根据文件判断是GIF动图还是Bitmap静态图片，
④通过相关复杂逻辑将下载的图片资源取出来，赋值给ImageView控件。


Glide的核心思想：
【对象池】：
Glide原理的核心是为bitmap维护一个对象池。对象池的主要目的是通过减少大对象内存的分配以重用来提高性能。
【生命周期绑定】：
第一个with方法，这个其实就是一个工厂方法，虽然有许多重载的形式，其实都是要创建一个RequestManager对象。
创建一个透明的 RequestManagerFragment 加入到FragmentManager 之中
通过添加的这个 Fragment 感知 Activity 、Fragment 的生命周期。
图片的加载任务会与activity或者Fragment的生命周期绑定，当界面执行onStop的使用自动暂定，而当执行onStart的时候又会自动重新开启，同样的，动态Gif图的加载也是如此，以用来节省电量，同时Glide会对网络状态做监听，当网络状态发生改变时，会重启失败的任务，以减少任务因网络连接问题而失败的概率



#######
总结
1Glide在加载绑定了Activity的生命周期。
2在Activity内新建一个无UI的Fragment，这个特殊的Fragment持有一个Lifecycle。通过Lifecycle在Fragment关键生命周期通知RequestManger进行相关的操作。
3在生命周期onStart时继续加载，onStop时暂停加载，onDestory是停止加载任务和清除操作。
缓存
1.ActivityResource获取，采用弱引用workR
2.LruCache [memoryCache-内存缓存meimenrui][DiskCache-磁盘缓存]

发起请求
    |
通过ActivityResources获取资源   ok --> 加载完成
    |未获取到
通过MemoryCache获取资源  ok  缓存至ActivityResources-->加载完成
    |未获取到
通过DiskCache获取资源    ok  缓存到MemoryCache -- 缓存至ActivityResources-->加载完成
    |未获取到
通过网络加载数据   数据加载成功-- 缓存DiskCache -- 缓存至ActivityResources-->加载完成














##############end###########