pageQuery
===
* 注释
     SELECT * FROM ( SELECT beeltT.*, ROWNUM beetl_rn FROM ( select #use("cols")# from KETTLE.DIM_ETL_CONFIG where #use("condition")# ) beeltT WHERE ROWNUM <#end#) WHERE beetl_rn >= #start#

cols
===

	ID,SOURCE_TABLE,SOURCE_TABLE_NAME,TARGET_TABLE,TARGET_TABLE_NAME,TARGET_TABLE_HIS,DATA_SRC,ETL_LEVEL,C_FLAG,STR_SQL,STR_SQL2,DB_CONN,DB_IP,DB_TYPE,PROJECT,DR,TS,SOURCE_SCHEMA,TARGET_SCHEMA,DATA_TGT,FIELDS,GROUPID,DAYS_KEEP,SOURCE_TABLE1,FIELDS2

count
===

    select count(*) from KETTLE.DIM_ETL_CONFIG where #use("condition")# 

updateSample
===

	ID=#id#,SOURCE_TABLE=#sourceTable#,SOURCE_TABLE_NAME=#sourceTableName#,TARGET_TABLE=#targetTable#,TARGET_TABLE_NAME=#targetTableName#,TARGET_TABLE_HIS=#targetTableHis#,DATA_SRC=#dataSrc#,ETL_LEVEL=#etlLevel#,C_FLAG=#cFlag#,STR_SQL=#strSql#,STR_SQL2=#strSql2#,DB_CONN=#dbConn#,DB_IP=#dbIp#,DB_TYPE=#dbType#,PROJECT=#project#,DR=#dr#,TS=#ts#,SOURCE_SCHEMA=#sourceSchema#,TARGET_SCHEMA=#targetSchema#,DATA_TGT=#dataTgt#,FIELDS=#fields#,GROUPID=#groupid#,DAYS_KEEP=#daysKeep#,SOURCE_TABLE1=#sourceTable1#,FIELDS2=#fields2#

condition
===

	1 = 1  
	@if(!isEmpty(sourceTable)){
	 and SOURCE_TABLE like #'%'+sourceTable+'%'#
	@}
	@if(!isEmpty(sourceTableName)){
	 and SOURCE_TABLE_NAME=#sourceTableName#
	@}
	@if(!isEmpty(targetTable)){
	 and TARGET_TABLE like #'%'+targetTable+'%'#
	@}
	@if(!isEmpty(targetTableName)){
	 and TARGET_TABLE_NAME=#targetTableName#
	@}
	
	
