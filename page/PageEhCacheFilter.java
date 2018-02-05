package com.neusoft.ess.filter.pageehcache;



import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;


public class PageEhCacheFilter extends  SimplePageCachingFilter{
//	private final static Logger logger = Logger.getLogger(PageEhCacheFilter.class);  
	  
    private static String[] cacheURLArray;  
  
    protected void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws AlreadyGzippedException, AlreadyCommittedException, FilterNonReentrantException, LockTimeoutException, Exception {  
    	if (cacheURLArray == null) {  
            String patterns = filterConfig.getInitParameter("patterns");  
            cacheURLArray = StringUtils.split(patterns, ",");  
        }  
        String requestURL = request.getRequestURL().toString();
        boolean containCacheURLFlag = false;  
        if (cacheURLArray != null && cacheURLArray.length > 0) {  
            for (String cacheURL : cacheURLArray) {  
                if (requestURL.contains(cacheURL.trim())) {//判断当前请求是否是要缓存的url  
                    containCacheURLFlag = true;  
                    break;  
                }  
            }  
        }  
  
        if (containCacheURLFlag) {//当前请求是要缓存的url  
            String queryString = request.getQueryString();
            if (StringUtils.isNotEmpty(queryString)) {//当前请求含有采用问号传过来的参数  
                queryString = "?" + queryString;  
                System.out.println("当前请求被缓存：" + requestURL + queryString);
            }else{  
            	System.out.println("当前请求被缓存：" + requestURL);
            }  
            super.doFilter(request, response, chain); //执行父类的dofilter方法 
        } else {//当前请求不是要缓存的url  
            chain.doFilter(request, response);//继续执行代码继续过滤  
        }  
    }
    /** 
     * 重写acceptsGzipEncoding方法，使该过滤器兼容对客户使用IE6和IE7时发过来请求时的gzip压缩 
     * 使用Gzip压缩时，需注意两个问题： 
     * 1、Filter进行Gzip压缩时，采用系统默认编码方式，对于使用GBK编码的中文网页来说，需要将操作系统的语言设置为“zh_CN.GBK”，否则会出现乱码问题。 
     * 2、默认情况下CachingFilter类（SimplePageCachingFilter类的父类）会根据浏览器发送的请求头部所包含的Accept-Encoding参数值来判断是否进行Gzip压缩。虽然浏览器IE6和IE7支持Gzip压缩，但是在发送请求的时候却不带该参数，因此可以通过继承CachingFilter类，重写acceptsGzipEncoding方法来实现。 
     * 
     */  
    @Override  
    protected boolean acceptsGzipEncoding(HttpServletRequest request) {  
        boolean ie6 = headerContains(request, "User-Agent", "MSIE 6.0");  
        boolean ie7 = headerContains(request, "User-Agent", "MSIE 7.0");  
        return acceptsEncoding(request, "gzip") || ie6 || ie7;  
    }  
  
    private boolean headerContains(final HttpServletRequest request, final String header, final String value) {  
        logRequestHeaders(request);  
        final Enumeration<?> accepted = request.getHeaders(header);  
        while (accepted.hasMoreElements()) {  
            final String headerValue = (String) accepted.nextElement();  
            if (headerValue.indexOf(value) != -1) {  
                return true;  
            }  
        }  
        return false;  
    }  
}
