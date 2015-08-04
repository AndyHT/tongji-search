package com.huoteng.spider;

import us.codecraft.webmagic.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 知网爬虫
 * Created by huoteng on 7/28/15.
 */
public class ZhiwangSpider {

    public static URLConnection connection = null;

    /**
     * 向知网发送GET请求然后得到cookie
     * @return 知网返回的cookie
     */
    public static List<String> getCookiesFromZhiwang() {
        try {
            URL realUrl = new URL("http://epub.cnki.net/kns/brief/result.aspx?dbprefix=scdb&action=scdbsearch&db_opt=SCDB");
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Host", "epub.cnki.net");
            connection.setRequestProperty("Referer", "http://www.cnki.net/");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36");
            // 建立实际的连接
            connection.connect();

            //得到知网的cookie
            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");

            int i = 0;
            for (String cookie : cookies) {
                System.out.print(i + ":");
                System.out.println(cookie);
                i++;
            }

            return cookies;
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param theCookie 需要验证的cookie
     * @return
     */
    public static boolean openSearchHandler(String theCookie) {
        try {
            URL realUrl = new URL("http://epub.cnki.net/KNS/request/SearchHandler.ashx?action=&NaviCode=*&PageName=ASP.brief_result_aspx&DbPrefix=SCDB&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&db_opt=CJFQ%2CCJFN%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND%2CCCJD%2CHBRD&base_special1=%25&magazine_special1=%25&his=0&__=Tue%20Jul%2028%202015%2021%3A10%3A00%20GMT%2B0800%20(CST)");
            URL targetUrl = new URL("http://epub.cnki.net/KNS/request/SearchHandler.ashx?action=&NaviCode=*&PageName=ASP.brief_result_aspx&DbPrefix=SCDB&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&db_opt=CJFQ%2CCJFN%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND%2CCCJD%2CHBRD&base_special1=%25&magazine_special1=%25&his=0&__=Wed%20Jul%2029%202015%2015%3A43%3A15%20GMT%2B0800%20(CST)");

            // 打开和URL之间的连接
            connection = targetUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cookie", theCookie);
            connection.setRequestProperty("Host", "epub.cnki.net");
            connection.setRequestProperty("Referer", "http://www.cnki.net/");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36");
            // 建立实际的连接
            connection.connect();

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }




    /**
     * 得到从知网返回的结果
     * @return
     */
    public static String getResult(String cookie) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL("http://epub.cnki.net/kns/brief/brief.aspx?pagename=ASP.brief_result_aspx&dbPrefix=SCDB&dbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&research=off&t=1438154626698&keyValue=&S=1");
            // 打开和URL之间的连接
            connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Cookie", cookie);
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Host", "epub.cnki.net");
            connection.setRequestProperty("Referer", "http://www.cnki.net/");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36");
            // 建立实际的连接
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     *
     * @param page
     * @return
     */
    public static String getNextPageUrl(String page) {

        //正则匹配下一页的url
        Pattern nextPageUrlPattern = Pattern.compile("<a href=\"[?].*下一页</a>");
        Matcher urlMatcher = nextPageUrlPattern.matcher(page);

        String result = "";

        if (urlMatcher.find()) {
            result = urlMatcher.group();
        }

        return result;
    }


    /**
     *
     * @param page
     * @return
     */
    public List<String> getArticleUrls(String page) {

        List<String> urls = new ArrayList<String>();

        //正则匹配网页上的article的url

        return urls;
    }



    /**
     * 拼接cookie
     * @param responseCookie 服务器端返回的cookie
     * @return 发送请求所需的cookie
     */
    public static String getRequestCookieFromResponseCookie(List<String> responseCookie) {
        String requestCookie = "";

        //正则匹配cookie需要的参数
        Pattern sessionIdPattern = Pattern.compile("ASP[.]NET_SessionId=[a-z0-9]{24};\\s");
        Pattern LIDPattern = Pattern.compile("LID=[0-9a-zA-Z=$_]+!!;\\s");
        Pattern cmLinIDPattern = Pattern.compile("c_m_LinID=[A-Za-z0-9=$_]*!!&ot=[0-9/:\\s]*");

        //SeesionId
        Matcher sessionIdMatcher = sessionIdPattern.matcher(responseCookie.get(2));
        if (sessionIdMatcher.find()) {
            requestCookie += sessionIdMatcher.group();
        }

        //page number
        requestCookie += "RsPerPage=20; ";

        //LID
        Matcher LIDMatcher = LIDPattern.matcher(responseCookie.get(0));
        if (LIDMatcher.find()) {
            requestCookie += LIDMatcher.group();
        }

        //c_m_LinID and outTime
        Matcher cmLindIDMatcher = cmLinIDPattern.matcher(responseCookie.get(1));
        if (cmLindIDMatcher.find()) {
            requestCookie += cmLindIDMatcher.group();
        }

        return requestCookie;
    }


    public static void main(String[] args) {
        List<String> RequestCookie = getCookiesFromZhiwang();

        String cookie = getRequestCookieFromResponseCookie(RequestCookie);
        System.out.println("COOKIE:\n" + cookie);

        if (openSearchHandler(cookie)) {
            String result = getResult(cookie);
            System.out.println("RESULT:\n" + result + "\n");
        }

//        String resultUrl = getNextPageUrl(result);
//        System.out.println("URL:\n" + resultUrl);

    }

}
