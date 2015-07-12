
Create by teng on 2015/04/11
#J2EE Project: tongji-search
web in ~/Desktop/search
#tongji-search
<p>Our website get url and title of news in <a href="http://sse.tongji.edu.cn">sse</a> using the spider.</p>
<p>It can help you find the news from sse, you can search news you want to find in our website.</p>
<p>Build with maven</p>
<p>IDE: IDEA 14</p>
####Search-engine
* lucene 4.0.0

####Analyzer
* IKAnalyzer 2012FF

####Spider
* Webmagic 0.5.2

####Database
* MySQL 5.6.23

####DB connect
* Hibernate 4.2.2.Final

##项目进度：
###前端部分
* 修改index.html为单页模式

###服务器端
* 优化：每次爬虫抓取新页面时和数据库中已有页面进行比对，不抓取已有页面
* 重构代码

###搜索引擎核心
* completed!

进行集合测试