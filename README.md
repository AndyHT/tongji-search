
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
进行集合测试，完成项目
###前端部分
* 找一张足够分辨率的背景图
* 完成ajax,实现manage页面的target URL的管理
* 如果可能实现文本的存储，当got URL有变化时重新进行分词处理和建立索引
* 每次启动爬虫前需要删除数据库里的记录和索引文件

###服务器端
* 需要完成Servlet以实现Ajax

###搜索引擎核心
* completed!

进行集合测试