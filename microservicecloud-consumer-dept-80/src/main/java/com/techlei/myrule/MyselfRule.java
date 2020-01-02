package com.techlei.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 每个服务五次之后轮询
 * @author aaaaaaa
 *
 */
public class MyselfRule extends AbstractLoadBalancerRule {
	/*
	 * total=5次之后，切到下一个服务器
	 * index 记录服务器 8001 8002 8003
	 *  
	 */
	private int totalCount=0;
	private int currentIndex=0;
	
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

//            int index = rand.nextInt(serverCount);
//            server = upList.get(index);
//        	private int totalCount=0;
//        	private int currentIndex=0;
            
            if(totalCount<5) {
            	server=upList.get(currentIndex);
            	totalCount++;
            }else {
            	totalCount=0;
            	currentIndex++;
            	if(currentIndex>=allList.size()) {
            		totalCount=0;
            		currentIndex=0;
            	}
            	server=upList.get(currentIndex);
            }
            
            
            
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub
		
	}
}
