package com.inspur.dsp.open.common.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchy05 on 2023/12/18 9:56
 * @Version: 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.inspur.dsp.open")
@ConditionalOnProperty(name = "xxl.job.enable", havingValue = "1")
public class XxlJobConfig {
    private final static Logger log = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobSpringExecutor() {
        if (!this.isTrueIp(ip)) {
            log.info("配置的xxl.job.executor.ip不正确");
        }
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    private boolean isTrueIp(String ipString) {
        List<String> sip = new ArrayList<String>();
        InetAddress ip = null;
        Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                // 遍历所有ip
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是loopback地址
                            && ip.getHostAddress().indexOf(":") == -1) {
                        sip.add(ip.getHostAddress());
                    }
                }
            }
            return sip.indexOf(ipString) != -1 ? true : false;
        } catch (SocketException e) {
            return true;
        }
    }
}
