package com.yang.springbootaliyunoss.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus配置文件
 *
 * @author canyou
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * ISqlInjector和PerformanceInterceptor已经被移除, PerformanceInterceptor使用p6spy代替, 由于存在性能损耗, 不建议在线上配置
     * PaginationInterceptor, OptimisticLockerInterceptor, IllegalSQLInterceptor分别变更为 PaginationInnerInterceptor, OptimisticLockerInnerInterceptor, IllegalSQLInnerInterceptor
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        // 设置请求的页面大于最大页后操作，true调回到首页，false继续请求。默认false
        pageInterceptor.setOverflow(true);
        // 单页分页条数限制，默认无限制
        pageInterceptor.setMaxLimit(50L);
        // 设置数据库类型
        pageInterceptor.setDbType(DbType.MYSQL);
        interceptor.addInnerInterceptor(pageInterceptor);
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 低效率sql拦截器开启会引发很多异常, 不开启
        // interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());
        return interceptor;
    }

}
