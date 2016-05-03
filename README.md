#tongji-search
Our website get url and title of news in <a href="http://sse.tongji.edu.cn">sse</a> using the spider.

It can help you find the news from sse, you can search news you want to find in our website.

Build with maven
####Search-engine
* lucene 4.0.0【更新Lucene6.0.0】

####Analyzer
* IKAnalyzer 2012FF

####Spider
* Webmagic 0.5.2【考虑使用nodejs替代】

####Database
* 野狗云

##项目进度：
###前端部分
* 重建搜索首页
* 一个后端登录，配置爬虫用

####数据库
* 更换数据库源，使用野狗云数据库

###搜索引擎核心
* 适配野狗云

###爬虫
* 适配野狗云，或者考虑使用nodejs爬虫
* 爬虫可配置

##Issue
* 需要一个云服务器，阿里云太贵了
* 将爬虫和Lucene分割
* 网页文件使用文件存储