pageQuery
===
    *数据库表分页查询
    select #use("cols")# from 
    k_job kj left join k_job_monitor kjm on kjm.monitor_job=kj.job_id left join k_category kc on kj.category_id=kc.category_id
    #use("condition")#
    #use("sort")#
    #use("limit")#

cols
===
    *列名
    category_name,
    job_name,
    monitor_Id,
    monitor_Job,
    last_Execute_Time,
    next_Execute_Time,
    monitor_Success,
    monitor_Fail
    
condition
===
    where 1=1
     @if(!isEmpty(kJobMonitor.jobName)){
            and kj.job_name like #'%'+kJobMonitor.jobName+'%'#
     @}
     @if(!isEmpty(categoryId)){
                      and kc.category_id =#categoryId#
     @}
     @if(!isEmpty(kJobMonitor.addUser)){
                 and kjm.add_user =#kJobMonitor.addUser#
     @}
     @if(!isEmpty(kJobMonitor.monitorStatus)){
                 and kjm.monitor_status =#kJobMonitor.monitorStatus#
     @}
sort
===
        order by last_execute_time desc

limit
===
    *分页
    @if(!isEmpty(start)){
        limit #start#   
    @}
    @if(!isEmpty(size)){
        ,#size#
    @}

allCount
===
    *总数量
    SELECT COUNT(1) FROM 
    k_job kj left join k_job_monitor kjm on kjm.monitor_job=kj.job_id left join k_category kc on kj.category_id=kc.category_id
    #use("condition")#
