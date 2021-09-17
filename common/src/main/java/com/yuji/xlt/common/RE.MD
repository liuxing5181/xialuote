   ## Application 中
   /**
     * 注册服务组件
     */
    private void initComponent() {
    //1.对外暴露api接口
        ServiceFactory.init(new InitListener() {
            @Override
            public void registerService(IServiceRegister register) {
                register.registerComponent(IPlayerService.class, "com.yujii.ptv.player.impl.service.PlayerServiceImpl");
            }
        });
    //2.api接口和实现都不对外暴露通过反射全量名注册和获取
     ServiceFactory.init(new InitListener() {
                @Override
                public void registerService(IServiceRegister register) {
                    register.registerComponent("com.yujii.ptv.player.impl.service.IPlayerService", "com.yujii.ptv.player.impl.service.PlayerServiceImpl");
                }
            });
    }