-- Oracle作数据源初始化脚本(首次执行需要注释掉DROP语句)

DROP TABLE "K_CATEGORY";
DROP TABLE "K_JOB";
DROP TABLE "K_JOB_MONITOR";
DROP TABLE "K_JOB_RECORD";
DROP TABLE "K_QUARTZ";
DROP TABLE "K_REPOSITORY";
DROP TABLE "K_REPOSITORY_TYPE";
DROP TABLE "K_TRANS";
DROP TABLE "K_TRANS_MONITOR";
DROP TABLE "K_TRANS_RECORD";
DROP TABLE "K_USER";
DROP SEQUENCE CATEGORY_ID_SEQ;
DROP SEQUENCE JOB_ID_SEQ;
DROP SEQUENCE JOB_MONITOR_ID_SEQ;
DROP SEQUENCE JOB_RECORD_ID_SEQ;
DROP SEQUENCE QUARTZ_ID_SEQ;
DROP SEQUENCE REPOSITORY_ID_SEQ;
DROP SEQUENCE REPOSITORY_TYPE_ID_SEQ;
DROP SEQUENCE TRANS_ID_SEQ;
DROP SEQUENCE TRANS_MONITOR_ID_SEQ;
DROP SEQUENCE TRANS_RECORD_ID_SEQ;
DROP SEQUENCE USER_ID_SEQ;

-- ----------------------------
-- Table structure for K_CATEGORY
-- ----------------------------
CREATE TABLE "K_CATEGORY" (
"CATEGORY_ID" NUMBER(11) NOT NULL ,
"CATEGORY_NAME" NVARCHAR2(50) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_CATEGORY"."CATEGORY_ID" IS '分类ID';
COMMENT ON COLUMN "K_CATEGORY"."CATEGORY_NAME" IS '分类名称';
COMMENT ON COLUMN "K_CATEGORY"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_CATEGORY"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_CATEGORY"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_CATEGORY"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_CATEGORY"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_CATEGORY
-- ----------------------------

-- ----------------------------
-- Table structure for K_JOB
-- ----------------------------
CREATE TABLE "K_JOB" (
"JOB_ID" NUMBER(11) NOT NULL ,
"CATEGORY_ID" NUMBER(11) NULL ,
"JOB_NAME" NVARCHAR2(50) NULL ,
"JOB_DESCRIPTION" NVARCHAR2(500) NULL ,
"JOB_TYPE" NUMBER(11) NULL ,
"JOB_PATH" NVARCHAR2(200) NULL ,
"JOB_REPOSITORY_ID" NUMBER(11) NULL ,
"JOB_QUARTZ" NUMBER(11) NULL ,
"JOB_RECORD" NUMBER(11) NULL ,
"JOB_LOG_LEVEL" NVARCHAR2(10) NULL ,
"JOB_STATUS" NUMBER(11) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_JOB"."JOB_ID" IS '作业ID';
COMMENT ON COLUMN "K_JOB"."JOB_NAME" IS '作业名称';
COMMENT ON COLUMN "K_JOB"."JOB_DESCRIPTION" IS '任务描述';
COMMENT ON COLUMN "K_JOB"."JOB_TYPE" IS '1:数据库资源库；2:上传的文件';
COMMENT ON COLUMN "K_JOB"."JOB_PATH" IS '作业保存路径（可以是资源库中的路径也可以是服务器中保存作业文件的路径）';
COMMENT ON COLUMN "K_JOB"."JOB_REPOSITORY_ID" IS '作业的资源库ID';
COMMENT ON COLUMN "K_JOB"."JOB_QUARTZ" IS '定时策略（外键ID）';
COMMENT ON COLUMN "K_JOB"."JOB_RECORD" IS '作业执行记录（外键ID）';
COMMENT ON COLUMN "K_JOB"."JOB_LOG_LEVEL" IS '日志级别(basic，detail，error，debug，minimal，rowlevel）';
COMMENT ON COLUMN "K_JOB"."JOB_STATUS" IS '状态（1：正在运行；2：已停止）';
COMMENT ON COLUMN "K_JOB"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_JOB"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_JOB"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_JOB"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_JOB"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_JOB
-- ----------------------------

-- ----------------------------
-- Table structure for K_JOB_MONITOR
-- ----------------------------
CREATE TABLE "K_JOB_MONITOR" (
"MONITOR_ID" NUMBER(11) NOT NULL ,
"MONITOR_JOB" NUMBER(11) NULL ,
"MONITOR_SUCCESS" NUMBER(11) NULL ,
"MONITOR_FAIL" NUMBER(11) NULL ,
"ADD_USER" NUMBER(11) NULL ,
"MONITOR_STATUS" NUMBER(11) NULL ,
"RUN_STATUS" NCLOB NULL ,
"LAST_EXECUTE_TIME" DATE NULL ,
"NEXT_EXECUTE_TIME" DATE NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_JOB_MONITOR"."MONITOR_ID" IS '监控作业ID';
COMMENT ON COLUMN "K_JOB_MONITOR"."MONITOR_JOB" IS '监控的作业ID';
COMMENT ON COLUMN "K_JOB_MONITOR"."MONITOR_SUCCESS" IS '成功次数';
COMMENT ON COLUMN "K_JOB_MONITOR"."MONITOR_FAIL" IS '失败次数';
COMMENT ON COLUMN "K_JOB_MONITOR"."ADD_USER" IS '添加人';
COMMENT ON COLUMN "K_JOB_MONITOR"."MONITOR_STATUS" IS '监控状态（是否启动，1:启动；2:停止）';
COMMENT ON COLUMN "K_JOB_MONITOR"."RUN_STATUS" IS '运行状态（起始时间-结束时间,起始时间-结束时间……）';

-- ----------------------------
-- Records of K_JOB_MONITOR
-- ----------------------------

-- ----------------------------
-- Table structure for K_JOB_RECORD
-- ----------------------------
CREATE TABLE "K_JOB_RECORD" (
"RECORD_ID" NUMBER(11) NOT NULL ,
"RECORD_JOB" NUMBER(11) NULL ,
"START_TIME" DATE NULL ,
"STOP_TIME" DATE NULL ,
"RECORD_STATUS" NUMBER(11) NULL ,
"LOG_FILE_PATH" NVARCHAR2(100) NULL ,
"ADD_USER" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_JOB_RECORD"."RECORD_ID" IS '作业记录ID';
COMMENT ON COLUMN "K_JOB_RECORD"."RECORD_JOB" IS '作业ID';
COMMENT ON COLUMN "K_JOB_RECORD"."START_TIME" IS '启动时间';
COMMENT ON COLUMN "K_JOB_RECORD"."STOP_TIME" IS '停止时间';
COMMENT ON COLUMN "K_JOB_RECORD"."RECORD_STATUS" IS '任务执行结果（1：成功；2：失败）';
COMMENT ON COLUMN "K_JOB_RECORD"."LOG_FILE_PATH" IS '作业日志记录文件保存位置';
COMMENT ON COLUMN "K_JOB_RECORD"."ADD_USER" IS '添加人';

-- ----------------------------
-- Records of K_JOB_RECORD
-- ----------------------------

-- ----------------------------
-- Table structure for K_QUARTZ
-- ----------------------------
CREATE TABLE "K_QUARTZ" (
"QUARTZ_ID" NUMBER(11) NOT NULL ,
"QUARTZ_DESCRIPTION" NVARCHAR2(500) NULL ,
"QUARTZ_CRON" NVARCHAR2(100) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_QUARTZ"."QUARTZ_ID" IS '任务ID';
COMMENT ON COLUMN "K_QUARTZ"."QUARTZ_DESCRIPTION" IS '任务描述';
COMMENT ON COLUMN "K_QUARTZ"."QUARTZ_CRON" IS '定时策略';
COMMENT ON COLUMN "K_QUARTZ"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_QUARTZ"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_QUARTZ"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_QUARTZ"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_QUARTZ"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_QUARTZ
-- ----------------------------
INSERT INTO "K_QUARTZ" VALUES ('1', '立即执行一次', null, TO_DATE('2017-05-27 14:44:13', 'YYYY-MM-DD HH24:MI:SS'), '1', TO_DATE('2017-05-27 14:44:13', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "K_QUARTZ" VALUES ('2', '每周一0点执行一次', '0 0 0 ? * 2', TO_DATE('2017-05-27 14:56:38', 'YYYY-MM-DD HH24:MI:SS'), '1', TO_DATE('2017-05-27 14:56:38', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "K_QUARTZ" VALUES ('3', '每月1日0点执行一次', '0 0 0 1 * ?', TO_DATE('2017-05-27 14:56:38', 'YYYY-MM-DD HH24:MI:SS'), '1', TO_DATE('2017-05-27 14:56:38', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "K_QUARTZ" VALUES ('4', '每日0点执行一次', '0 0 0 * * ?', TO_DATE('2017-05-27 14:44:13', 'YYYY-MM-DD HH24:MI:SS'), '1', TO_DATE('2017-05-27 14:44:15', 'YYYY-MM-DD HH24:MI:SS'), null, '1');
INSERT INTO "K_QUARTZ" VALUES ('31', '每分钟执行一次', '0 * * * * ?', TO_DATE('2018-10-16 14:12:44', 'YYYY-MM-DD HH24:MI:SS'), '6', TO_DATE('2018-10-16 14:12:44', 'YYYY-MM-DD HH24:MI:SS'), '6', '1');

-- ----------------------------
-- Table structure for K_REPOSITORY
-- ----------------------------
CREATE TABLE "K_REPOSITORY" (
"REPOSITORY_ID" NUMBER(11) NOT NULL ,
"REPOSITORY_NAME" NVARCHAR2(50) NULL ,
"REPOSITORY_USERNAME" NVARCHAR2(50) NULL ,
"REPOSITORY_PASSWORD" NVARCHAR2(50) NULL ,
"REPOSITORY_TYPE" NVARCHAR2(10) NULL ,
"DATABASE_ACCESS" NVARCHAR2(10) NULL ,
"DATABASE_HOST" NVARCHAR2(50) NULL ,
"DATABASE_PORT" NVARCHAR2(10) NULL ,
"DATABASE_NAME" NVARCHAR2(20) NULL ,
"DATABASE_USERNAME" NVARCHAR2(50) NULL ,
"DATABASE_PASSWORD" NVARCHAR2(50) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_REPOSITORY"."REPOSITORY_ID" IS 'ID';
COMMENT ON COLUMN "K_REPOSITORY"."REPOSITORY_NAME" IS '资源库名称';
COMMENT ON COLUMN "K_REPOSITORY"."REPOSITORY_USERNAME" IS '登录用户名';
COMMENT ON COLUMN "K_REPOSITORY"."REPOSITORY_PASSWORD" IS '登录密码';
COMMENT ON COLUMN "K_REPOSITORY"."REPOSITORY_TYPE" IS '资源库数据库类型（MYSQL、ORACLE）';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_ACCESS" IS '资源库数据库访问模式（"Native", "ODBC", "OCI", "Plugin", "JNDI")';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_HOST" IS '资源库数据库主机名或者IP地址';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_PORT" IS '资源库数据库端口号';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_NAME" IS '资源库数据库名称';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_USERNAME" IS '数据库登录账号';
COMMENT ON COLUMN "K_REPOSITORY"."DATABASE_PASSWORD" IS '数据库登录密码';
COMMENT ON COLUMN "K_REPOSITORY"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_REPOSITORY"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_REPOSITORY"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_REPOSITORY"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_REPOSITORY"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_REPOSITORY
-- ----------------------------

-- ----------------------------
-- Table structure for K_REPOSITORY_TYPE
-- ----------------------------
CREATE TABLE "K_REPOSITORY_TYPE" (
"REPOSITORY_TYPE_ID" NUMBER(11) NOT NULL ,
"REPOSITORY_TYPE_CODE" NVARCHAR2(30) NULL ,
"REPOSITORY_TYPE_DES" NVARCHAR2(100) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of K_REPOSITORY_TYPE
-- ----------------------------
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('1', 'INGRES', 'Ingres');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('2', 'INTERBASE', 'Borland Interbase');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('3', 'INFOBRIGHT', 'Infobright');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('4', 'ORACLE', 'Oracle');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('5', 'EXTENDB', 'ExtenDB');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('6', 'MSACCESS', 'MS Access');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('7', 'SYBASE', 'Sybase');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('8', 'PALO', 'Palo MOLAP Server');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('9', 'INFORMIX', 'Informix');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('10', 'LucidDB', 'LucidDB');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('11', 'TERADATA', 'Teradata');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('12', 'UNIVERSE', 'UniVerse database');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('13', 'MONETDB', 'MonetDB');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('14', 'CACHE', 'Intersystems Cache');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('15', 'MSSQL', 'MS SQL Server');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('16', 'KettleThin', 'Pentaho Data Services');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('17', 'GREENPLUM', 'Greenplum');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('18', 'GENERIC', 'Generic database');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('19', 'IMPALA', 'Impala');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('20', 'SQLITE', 'SQLite');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('21', 'REMEDY-AR-SYSTEM', 'Remedy Action Request System');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('22', 'MONDRIAN', 'Native Mondrian');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('23', 'HIVE2', 'Hadoop Hive 2');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('24', 'NETEZZA', 'Netezza');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('25', 'VERTICA5', 'Vertica 5+');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('26', 'POSTGRESQL', 'PostgreSQL');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('27', 'EXASOL4', 'Exasol 4');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('28', 'HYPERSONIC', 'Hypersonic');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('29', 'AS/400', 'AS/400');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('30', 'ORACLERDB', 'Oracle RDB');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('31', 'DBASE', 'dBase III, IV or 5');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('32', 'IMPALASIMBA', 'Cloudera Impala');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('33', 'KINGBASEES', 'KingbaseES');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('34', 'SAPR3', 'SAP ERP System');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('35', 'SQLBASE', 'Gupta SQL Base');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('36', 'DERBY', 'Apache Derby');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('37', 'VERTICA', 'Vertica');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('38', 'INFINIDB', 'Calpont InfiniDB');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('39', 'HIVE', 'Hadoop Hive');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('40', 'MYSQL', 'MySQL');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('41', 'MSSQLNATIVE', 'MS SQL Server (Native)');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('42', 'H2', 'H2');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('43', 'SAPDB', 'MaxDB (SAP DB)');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('44', 'SPARKSIMBA', 'SparkSQL');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('45', 'VECTORWISE', 'Ingres VectorWise');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('46', 'DB2', 'IBM DB2');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('47', 'NEOVIEW', 'Neoview');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('48', 'SYBASEIQ', 'SybaseIQ');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('49', 'REDSHIFT', 'Redshift');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('50', 'FIREBIRD', 'Firebird SQL');
INSERT INTO "K_REPOSITORY_TYPE" VALUES ('51', 'OpenERPDatabaseMeta', 'OpenERP Server');

-- ----------------------------
-- Table structure for K_TRANS
-- ----------------------------
CREATE TABLE "K_TRANS" (
"TRANS_ID" NUMBER(11) NOT NULL ,
"CATEGORY_ID" NUMBER(11) NULL ,
"TRANS_NAME" NVARCHAR2(50) NULL ,
"TRANS_DESCRIPTION" NVARCHAR2(500) NULL ,
"TRANS_TYPE" NUMBER(11) NULL ,
"TRANS_PATH" NVARCHAR2(200) NULL ,
"TRANS_REPOSITORY_ID" NUMBER(11) NULL ,
"TRANS_QUARTZ" NUMBER(11) NULL ,
"TRANS_RECORD" NUMBER(11) NULL ,
"TRANS_LOG_LEVEL" NVARCHAR2(10) NULL ,
"TRANS_STATUS" NUMBER(11) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_TRANS"."TRANS_ID" IS '转换ID';
COMMENT ON COLUMN "K_TRANS"."TRANS_NAME" IS '转换名称';
COMMENT ON COLUMN "K_TRANS"."TRANS_DESCRIPTION" IS '转换描述';
COMMENT ON COLUMN "K_TRANS"."TRANS_TYPE" IS '1:数据库资源库；2:上传的文件';
COMMENT ON COLUMN "K_TRANS"."TRANS_PATH" IS '转换保存路径（可以是资源库中的路径也可以是服务器中保存作业文件的路径）';
COMMENT ON COLUMN "K_TRANS"."TRANS_REPOSITORY_ID" IS '转换的资源库ID';
COMMENT ON COLUMN "K_TRANS"."TRANS_QUARTZ" IS '定时策略（外键ID）';
COMMENT ON COLUMN "K_TRANS"."TRANS_RECORD" IS '转换执行记录（外键ID）';
COMMENT ON COLUMN "K_TRANS"."TRANS_LOG_LEVEL" IS '日志级别(basic，detail，error，debug，minimal，rowlevel）';
COMMENT ON COLUMN "K_TRANS"."TRANS_STATUS" IS '状态（1：正在运行；2：已停止）';
COMMENT ON COLUMN "K_TRANS"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_TRANS"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_TRANS"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_TRANS"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_TRANS"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_TRANS
-- ----------------------------

-- ----------------------------
-- Table structure for K_TRANS_MONITOR
-- ----------------------------
CREATE TABLE "K_TRANS_MONITOR" (
"MONITOR_ID" NUMBER(11) NOT NULL ,
"MONITOR_TRANS" NUMBER(11) NULL ,
"MONITOR_SUCCESS" NUMBER(11) NULL ,
"MONITOR_FAIL" NUMBER(11) NULL ,
"ADD_USER" NUMBER(11) NULL ,
"MONITOR_STATUS" NUMBER(11) NULL ,
"RUN_STATUS" NCLOB NULL ,
"LAST_EXECUTE_TIME" DATE NULL ,
"NEXT_EXECUTE_TIME" DATE NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_TRANS_MONITOR"."MONITOR_ID" IS '监控转换ID';
COMMENT ON COLUMN "K_TRANS_MONITOR"."MONITOR_TRANS" IS '监控的转换的ID';
COMMENT ON COLUMN "K_TRANS_MONITOR"."MONITOR_SUCCESS" IS '成功次数';
COMMENT ON COLUMN "K_TRANS_MONITOR"."MONITOR_FAIL" IS '失败次数';
COMMENT ON COLUMN "K_TRANS_MONITOR"."ADD_USER" IS '添加人';
COMMENT ON COLUMN "K_TRANS_MONITOR"."MONITOR_STATUS" IS '监控状态（是否启动，1:启动；2:停止）';
COMMENT ON COLUMN "K_TRANS_MONITOR"."RUN_STATUS" IS '运行状态（起始时间-结束时间,起始时间-结束时间……）';

-- ----------------------------
-- Records of K_TRANS_MONITOR
-- ----------------------------

-- ----------------------------
-- Table structure for K_TRANS_RECORD
-- ----------------------------
CREATE TABLE "K_TRANS_RECORD" (
"RECORD_ID" NUMBER(11) NOT NULL ,
"RECORD_TRANS" NUMBER(11) NULL ,
"START_TIME" DATE NULL ,
"STOP_TIME" DATE NULL ,
"RECORD_STATUS" NUMBER(11) NULL ,
"LOG_FILE_PATH" NVARCHAR2(100) NULL ,
"ADD_USER" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_TRANS_RECORD"."RECORD_ID" IS '转换记录ID';
COMMENT ON COLUMN "K_TRANS_RECORD"."RECORD_TRANS" IS '转换ID';
COMMENT ON COLUMN "K_TRANS_RECORD"."START_TIME" IS '启动时间';
COMMENT ON COLUMN "K_TRANS_RECORD"."STOP_TIME" IS '停止时间';
COMMENT ON COLUMN "K_TRANS_RECORD"."RECORD_STATUS" IS '任务执行结果（1：成功；2：失败）';
COMMENT ON COLUMN "K_TRANS_RECORD"."LOG_FILE_PATH" IS '转换日志记录文件保存位置';
COMMENT ON COLUMN "K_TRANS_RECORD"."ADD_USER" IS '添加人';

-- ----------------------------
-- Records of K_TRANS_RECORD
-- ----------------------------

-- ----------------------------
-- Table structure for K_USER
-- ----------------------------
CREATE TABLE "K_USER" (
"U_ID" NUMBER(11) NOT NULL ,
"U_NICKNAME" NVARCHAR2(50) NULL ,
"U_EMAIL" NVARCHAR2(30) NULL ,
"U_PHONE" NVARCHAR2(50) NULL ,
"U_ACCOUNT" NVARCHAR2(50) NULL ,
"U_PASSWORD" NVARCHAR2(50) NULL ,
"ADD_TIME" DATE NULL ,
"ADD_USER" NUMBER(11) NULL ,
"EDIT_TIME" DATE NULL ,
"EDIT_USER" NUMBER(11) NULL ,
"DEL_FLAG" NUMBER(11) NULL
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON COLUMN "K_USER"."U_ID" IS '用户ID';
COMMENT ON COLUMN "K_USER"."U_NICKNAME" IS '用户昵称';
COMMENT ON COLUMN "K_USER"."U_EMAIL" IS '用户邮箱';
COMMENT ON COLUMN "K_USER"."U_PHONE" IS '用于电话';
COMMENT ON COLUMN "K_USER"."U_ACCOUNT" IS '用户账号';
COMMENT ON COLUMN "K_USER"."U_PASSWORD" IS '用户密码';
COMMENT ON COLUMN "K_USER"."ADD_TIME" IS '添加时间';
COMMENT ON COLUMN "K_USER"."ADD_USER" IS '添加者';
COMMENT ON COLUMN "K_USER"."EDIT_TIME" IS '编辑时间';
COMMENT ON COLUMN "K_USER"."EDIT_USER" IS '编辑者';
COMMENT ON COLUMN "K_USER"."DEL_FLAG" IS '是否删除（1：存在；0：删除）';

-- ----------------------------
-- Records of K_USER
-- ----------------------------
INSERT INTO "K_USER" VALUES ('1', 'admin', null, null, 'admin', 'b1276925a59fd8d9e1a53c10637f271d', null, null, null, null, '1');

-- ----------------------------
-- Indexes structure for table K_CATEGORY
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_CATEGORY
-- ----------------------------
ALTER TABLE "K_CATEGORY" ADD CHECK ("CATEGORY_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_CATEGORY
-- ----------------------------
ALTER TABLE "K_CATEGORY" ADD PRIMARY KEY ("CATEGORY_ID");

-- ----------------------------
-- Indexes structure for table K_JOB
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_JOB
-- ----------------------------
ALTER TABLE "K_JOB" ADD CHECK ("JOB_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_JOB
-- ----------------------------
ALTER TABLE "K_JOB" ADD PRIMARY KEY ("JOB_ID");

-- ----------------------------
-- Indexes structure for table K_JOB_MONITOR
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_JOB_MONITOR
-- ----------------------------
ALTER TABLE "K_JOB_MONITOR" ADD CHECK ("MONITOR_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_JOB_MONITOR
-- ----------------------------
ALTER TABLE "K_JOB_MONITOR" ADD PRIMARY KEY ("MONITOR_ID");

-- ----------------------------
-- Indexes structure for table K_JOB_RECORD
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_JOB_RECORD
-- ----------------------------
ALTER TABLE "K_JOB_RECORD" ADD CHECK ("RECORD_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_JOB_RECORD
-- ----------------------------
ALTER TABLE "K_JOB_RECORD" ADD PRIMARY KEY ("RECORD_ID");

-- ----------------------------
-- Indexes structure for table K_QUARTZ
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_QUARTZ
-- ----------------------------
ALTER TABLE "K_QUARTZ" ADD CHECK ("QUARTZ_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_QUARTZ
-- ----------------------------
ALTER TABLE "K_QUARTZ" ADD PRIMARY KEY ("QUARTZ_ID");

-- ----------------------------
-- Indexes structure for table K_REPOSITORY
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_REPOSITORY
-- ----------------------------
ALTER TABLE "K_REPOSITORY" ADD CHECK ("REPOSITORY_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_REPOSITORY
-- ----------------------------
ALTER TABLE "K_REPOSITORY" ADD PRIMARY KEY ("REPOSITORY_ID");

-- ----------------------------
-- Indexes structure for table K_REPOSITORY_TYPE
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_REPOSITORY_TYPE
-- ----------------------------
ALTER TABLE "K_REPOSITORY_TYPE" ADD CHECK ("REPOSITORY_TYPE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_REPOSITORY_TYPE
-- ----------------------------
ALTER TABLE "K_REPOSITORY_TYPE" ADD PRIMARY KEY ("REPOSITORY_TYPE_ID");

-- ----------------------------
-- Indexes structure for table K_TRANS
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_TRANS
-- ----------------------------
ALTER TABLE "K_TRANS" ADD CHECK ("TRANS_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_TRANS
-- ----------------------------
ALTER TABLE "K_TRANS" ADD PRIMARY KEY ("TRANS_ID");

-- ----------------------------
-- Indexes structure for table K_TRANS_MONITOR
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_TRANS_MONITOR
-- ----------------------------
ALTER TABLE "K_TRANS_MONITOR" ADD CHECK ("MONITOR_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_TRANS_MONITOR
-- ----------------------------
ALTER TABLE "K_TRANS_MONITOR" ADD PRIMARY KEY ("MONITOR_ID");

-- ----------------------------
-- Indexes structure for table K_TRANS_RECORD
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_TRANS_RECORD
-- ----------------------------
ALTER TABLE "K_TRANS_RECORD" ADD CHECK ("RECORD_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_TRANS_RECORD
-- ----------------------------
ALTER TABLE "K_TRANS_RECORD" ADD PRIMARY KEY ("RECORD_ID");

-- ----------------------------
-- Indexes structure for table K_USER
-- ----------------------------

-- ----------------------------
-- Checks structure for table K_USER
-- ----------------------------
ALTER TABLE "K_USER" ADD CHECK ("U_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table K_USER
-- ----------------------------
ALTER TABLE "K_USER" ADD PRIMARY KEY ("U_ID");


-- 增加自增序列
CREATE SEQUENCE CATEGORY_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE JOB_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE JOB_MONITOR_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE JOB_RECORD_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE QUARTZ_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE REPOSITORY_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE REPOSITORY_TYPE_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE TRANS_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE TRANS_MONITOR_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE TRANS_RECORD_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;
CREATE SEQUENCE USER_ID_SEQ start with 1 maxvalue 9999999999 increment by 1 NOCYCLE NOCACHE;