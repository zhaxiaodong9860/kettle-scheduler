pageQuery
===
    *数据库表分页查询
    SELECT #use("cols")# FROM 
    k_job 
    #use("condition")#
    #use("sort")#
    #use("limit")#

queryByCondition
===
    SELECT job_Id FROM k_job #use("condition")#

cols
===
    *列名
    job_Id,
    category_Id,
    job_Name,
    job_Description,
    job_Path,
    job_Quartz,
    job_Status
    
condition
===
    where 1=1
     @if(!isEmpty(kJob.categoryId)){
            and category_id =#kJob.categoryId#
     @}
     @if(!isEmpty(kJob.jobName)){
            and job_name like #'%'+kJob.jobName+'%'#
     @}
     @if(!isEmpty(kJob.addUser)){
            and add_user =#kJob.addUser#
     @}
     @if(!isEmpty(kJob.delFlag)){
            and del_flag =#kJob.delFlag#
     @}
     @if(!isEmpty(kJob.jobStatus)){
            and job_status =#kJob.jobStatus#
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
    k_job
    #use("condition")#
