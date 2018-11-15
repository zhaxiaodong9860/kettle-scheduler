pageQuery
===
    *数据库表分页查询
    SELECT #use("cols")# FROM 
    k_trans
    #use("condition")#
    #use("sort")#
    #use("limit")#

queryByCondition
===
    SELECT trans_id FROM k_trans #use("condition")#

cols
===
    *列名
    trans_id,
    category_Id,
    trans_name,
    trans_description,
    trans_path,
    trans_quartz,
    trans_status
    
condition
===
    where 1=1
     @if(!isEmpty(kTrans.categoryId)){
            and category_Id =#kTrans.categoryId#
     @}
     @if(!isEmpty(kTrans.transName)){
            and trans_name like #'%'+kTrans.transName+'%'#
     @}
     @if(!isEmpty(kTrans.addUser)){
            and add_user =#kTrans.addUser#
     @}
     @if(!isEmpty(kTrans.delFlag)){
            and del_flag =#kTrans.delFlag#
     @}
     @if(!isEmpty(kTrans.transStatus)){
            and trans_Status =#kTrans.transStatus#
     @}
sort
===
        order by add_time desc

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
    k_trans
    #use("condition")#
