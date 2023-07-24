# newTest
职工管理系统
1、用户登录认证使用security+JWT的形式。admin 123456
2、token使用redis缓存。
3、职工数据使用CsvReader读取、修改性别、部门、学历信息后拼接成sql语句写入文本。
4、数据库文件在后端项目的resource/static中
5、分页使用mybatisPlus插件。
6、导出Excel。后端使用easyexcel生成Excel文件流。前端下载。
7、shell脚本在后端项目的resource/static中