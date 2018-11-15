pageQuery
===
    *数据库表分页查询
    select #use("cols")# from 
    k_trans kt left join k_trans_monitor ktm on ktm.monitor_trans=kt.trans_id left join k_category kc on kt.category_id=kc.category_id
    #use("condition")#
    #use("sort")#
    #use("limit")#

cols
===
    *列名
    category_name,
    trans_name,
    monitor_Id,
    monitor_trans,
    last_Execute_Time,
    next_Execute_Time,
    monitor_Success,
    monitor_Fail
    
condition
===
    where 1=1
     @if(!isEmpty(kTransMonitor.transName)){
            and kt.trans_name like #'%'+kTransMonitor.transName+'%'#
     @}
     @if(!isEmpty(categoryId)){
            and kc.category_id =#categoryId#
     @}
     @if(!isEmpty(kTransMonitor.addUser)){
            and ktm.add_user =#kTransMonitor.addUser#
     @}
     @if(!isEmpty(kTransMonitor.monitorStatus)){
             and ktm.monitor_status =#kTransMonitor.monitorStatus#
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
    k_trans kt left join k_trans_monitor ktm on ktm.monitor_trans=kt.trans_id left join k_category kc on kt.category_id=kc.category_id
    #use("condition")#
