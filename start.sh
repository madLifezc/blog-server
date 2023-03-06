echo "开始启动Blog项目。。。。。。"

echo "mogu-eureka"
echo "停止eureka服务..."
ps -ef | grep mogu_eureka-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束"
nohup java -jar mogu_eureka/target/mogu_eureka-0.0.1-SNAPSHOT.jar & 
sleep 10

echo "mogu-monitor"
echo "停止monitor服务..."
ps -ef | grep mogu_monitor-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束" 
nohup java -jar mogu_monitor/target/mogu_monitor-0.0.1-SNAPSHOT.jar &
sleep 10

echo "mogu-sms"
echo "停止sms服务..."
ps -ef | grep mogu_sms-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束"
nohup java -jar mogu_sms/target/mogu_sms-0.0.1-SNAPSHOT.jar &
sleep 5 

echo "mogu-search"
echo "停止search服务..."
ps -ef | grep mogu_search-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束" 
nohup java -jar mogu_search/target/mogu_search-0.0.1-SNAPSHOT.jar &
sleep 5 

echo "mogu-picture" 
echo "停止picture服务..."
ps -ef | grep mogu_picture-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束"
nohup java -jar mogu_picture/target/mogu_picture-0.0.1-SNAPSHOT.jar &

echo "mogu-admin"
echo "停止admin服务..."
ps -ef | grep mogu_admin-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束"
nohup java -jar mogu_admin/target/mogu_admin-0.0.1-SNAPSHOT.jar &

echo "mogu-web"
echo "停止monitor服务..."
ps -ef | grep mogu_webj-0.0.1-SNAPSHOT.jar  | grep -v grep | grep -v tail | awk '{print $2}' | xargs kill -9
echo "停止服务结束"
nohup java -jar mogu_web/target/mogu_web-0.0.1-SNAPSHOT.jar &


echo "所以服务启动完毕！！！"
