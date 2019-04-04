package com.zhaxd.web.quartz;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.DSTransactionManager;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.pentaho.di.core.ProgressNullMonitorListener;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleMissingPluginsException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.LoggingBuffer;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.quartz.*;

import com.zhaxd.common.kettle.repository.RepositoryUtil;
import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.model.KJobMonitor;
import com.zhaxd.core.model.KJobRecord;
import com.zhaxd.core.model.KRepository;
import com.zhaxd.web.quartz.model.DBConnectionModel;

@DisallowConcurrentExecution
public class JobQuartz implements InterruptableJob {
    private org.pentaho.di.job.Job job;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Object KRepositoryObject = jobDataMap.get(Constant.REPOSITORYOBJECT);
        Object DbConnectionObject = jobDataMap.get(Constant.DBCONNECTIONOBJECT);
        String jobName_str = context.getJobDetail().getKey().getName();
        String[] names = jobName_str.split("@");
        String jobId = String.valueOf(jobDataMap.get(Constant.JOBID));
        String jobPath = String.valueOf(jobDataMap.get(Constant.JOBPATH));
        String jobName = String.valueOf(jobDataMap.get(Constant.JOBNAME));
        String userId = String.valueOf(jobDataMap.get(Constant.USERID));
        String logLevel = String.valueOf(jobDataMap.get(Constant.LOGLEVEL));
        String logFilePath = String.valueOf(jobDataMap.get(Constant.LOGFILEPATH));
        Date lastExecuteTime = context.getFireTime();
        Date nexExecuteTime = context.getNextFireTime();

        if (null != DbConnectionObject && DbConnectionObject instanceof DBConnectionModel) {// 首先判断数据库连接对象是否正确
            // 判断作业类型
            if (null != KRepositoryObject && KRepositoryObject instanceof KRepository) {// 证明该作业是从资源库中获取到的
                try {
                    runRepositoryJob(KRepositoryObject, DbConnectionObject, jobId, jobPath, jobName, userId, logLevel,
                            logFilePath, lastExecuteTime, nexExecuteTime);
                } catch (KettleException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    runFileJob(DbConnectionObject, jobId, jobPath, jobName, userId, logLevel, logFilePath, lastExecuteTime, nexExecuteTime);
                } catch (KettleXMLException | KettleMissingPluginsException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param KRepositoryObject 数据库连接对象
     * @param KRepositoryObject 资源库对象
     * @param jobId             作业ID
     * @param jobPath           作业在资源库中的路径信息
     * @param jobName           作业名称
     * @param userId            作业归属者ID
     * @param logLevel          作业的日志等级
     * @param logFilePath       作业日志保存的根路径
     * @return void
     * @throws KettleException
     * @Title runRepositoryJob
     * @Description 运行资源库中的作业
     */
    public void runRepositoryJob(Object KRepositoryObject, Object DbConnectionObject, String jobId,
                                 String jobPath, String jobName, String userId, String logLevel, String logFilePath, Date executeTime, Date nexExecuteTime) throws KettleException {
        KRepository kRepository = (KRepository) KRepositoryObject;
        Integer repositoryId = kRepository.getRepositoryId();
        KettleDatabaseRepository kettleDatabaseRepository = null;
        if (RepositoryUtil.KettleDatabaseRepositoryCatch.containsKey(repositoryId)) {
            kettleDatabaseRepository = RepositoryUtil.KettleDatabaseRepositoryCatch.get(repositoryId);
        } else {
            kettleDatabaseRepository = RepositoryUtil.connectionRepository(kRepository);
        }
        if (null != kettleDatabaseRepository) {
            RepositoryDirectoryInterface directory = kettleDatabaseRepository.loadRepositoryDirectoryTree()
                    .findDirectory(jobPath);
            JobMeta jobMeta = kettleDatabaseRepository.loadJob(jobName, directory, new ProgressNullMonitorListener(),
                    null);
            job = new org.pentaho.di.job.Job(kettleDatabaseRepository, jobMeta);
            job.setDaemon(true);
            job.setLogLevel(LogLevel.DEBUG);
            if (StringUtils.isNotEmpty(logLevel)) {
                job.setLogLevel(Constant.logger(logLevel));
            }
            String exception = null;
            Integer recordStatus = 1;
//            Date jobStartDate = null;
            Date jobStopDate = null;
            String logText = null;
            try {
//                jobStartDate = new Date();
                job.run();
                job.waitUntilFinished();
                jobStopDate = new Date();
            } catch (Exception e) {
                exception = e.getMessage();
                recordStatus = 2;
            } finally {
                if (job.isFinished()) {
                    if (job.getErrors() > 0) {
                        recordStatus = 2;
                        if(null == job.getResult().getLogText() || "".equals(job.getResult().getLogText())){
                            logText = exception;
                        }
                    }
                    // 写入作业执行结果
                    StringBuilder allLogFilePath = new StringBuilder();
                    allLogFilePath.append(logFilePath).append("/").append(userId).append("/")
                            .append(StringUtils.remove(jobPath, "/")).append("@").append(jobName).append("-log")
                            .append("/").append(new Date().getTime()).append(".").append("txt");
                    String logChannelId = job.getLogChannelId();
                    LoggingBuffer appender = KettleLogStore.getAppender();
                    logText = appender.getBuffer(logChannelId, true).toString();
                    try {
                        KJobRecord kJobRecord = new KJobRecord();
                        kJobRecord.setRecordJob(Integer.parseInt(jobId));
                        kJobRecord.setAddUser(Integer.parseInt(userId));
                        kJobRecord.setLogFilePath(allLogFilePath.toString());
                        kJobRecord.setRecordStatus(recordStatus);
                        kJobRecord.setStartTime(executeTime);
                        kJobRecord.setStopTime(jobStopDate);
                        writeToDBAndFile(DbConnectionObject, kJobRecord, logText, executeTime, nexExecuteTime);
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void runFileJob(Object DbConnectionObject, String jobId, String jobPath, String jobName,
                           String userId, String logLevel, String logFilePath, Date lastExecuteTime, Date nexExecuteTime) throws KettleXMLException, KettleMissingPluginsException {
        JobMeta jobMeta = new JobMeta(jobPath, null);
        job = new org.pentaho.di.job.Job(null, jobMeta);
        job.setDaemon(true);
        job.setLogLevel(LogLevel.DEBUG);
        if (StringUtils.isNotEmpty(logLevel)) {
            job.setLogLevel(Constant.logger(logLevel));
        }
        String exception = null;
        Integer recordStatus = 1;
        Date jobStartDate = null;
        Date jobStopDate = null;
        String logText = null;
        try {
            jobStartDate = new Date();
            job.run();
            job.waitUntilFinished();
            jobStopDate = new Date();
        } catch (Exception e) {
            exception = e.getMessage();
            recordStatus = 2;
        } finally {
            if (null != job && job.isFinished()) {
                if (job.getErrors() > 0
                        && (null == job.getResult().getLogText() || "".equals(job.getResult().getLogText()))) {
                    logText = exception;
                }
                // 写入作业执行结果
                StringBuilder allLogFilePath = new StringBuilder();
                allLogFilePath.append(logFilePath).append("/").append(userId).append("/")
                        .append(StringUtils.remove(jobPath, "/")).append("@").append(jobName).append("-log").append("/")
                        .append(new Date().getTime()).append(".").append("txt");
                String logChannelId = job.getLogChannelId();
                LoggingBuffer appender = KettleLogStore.getAppender();
                logText = appender.getBuffer(logChannelId, true).toString();
                try {
                    KJobRecord kJobRecord = new KJobRecord();
                    kJobRecord.setRecordJob(Integer.parseInt(jobId));
                    kJobRecord.setAddUser(Integer.parseInt(userId));
                    kJobRecord.setLogFilePath(allLogFilePath.toString());
                    kJobRecord.setRecordStatus(recordStatus);
                    kJobRecord.setStartTime(jobStartDate);
                    kJobRecord.setStopTime(jobStopDate);
                    writeToDBAndFile(DbConnectionObject, kJobRecord, logText, lastExecuteTime, nexExecuteTime);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @param DbConnectionObject 数据库连接对象
     * @param kJobRecord         作业记录信息
     * @param logText            日志信息
     * @return void
     * @throws IOException
     * @throws SQLException
     * @Title writeToDBAndFile
     * @Description 保存作业运行日志信息到文件和数据库
     */
    private void writeToDBAndFile(Object DbConnectionObject, KJobRecord kJobRecord, String logText, Date lastExecuteTime, Date nextExecuteTime)
            throws IOException, SQLException {
        // 将日志信息写入文件
        FileUtils.writeStringToFile(new File(kJobRecord.getLogFilePath()), logText, Constant.DEFAULT_ENCODING, false);
        // 写入转换运行记录到数据库
        DBConnectionModel DBConnectionModel = (DBConnectionModel) DbConnectionObject;
        ConnectionSource source = ConnectionSourceHelper.getSimple(DBConnectionModel.getConnectionDriveClassName(),
                DBConnectionModel.getConnectionUrl(), DBConnectionModel.getConnectionUser(), DBConnectionModel.getConnectionPassword());
        DBStyle mysql = new MySqlStyle();
        SQLLoader loader = new ClasspathLoader("/");
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        SQLManager sqlManager = new SQLManager(mysql, loader,
                source, nc, new Interceptor[]{new DebugInterceptor()});
        DSTransactionManager.start();
        sqlManager.insert(kJobRecord);
        KJobMonitor template = new KJobMonitor();
        template.setAddUser(kJobRecord.getAddUser());
        template.setMonitorJob(kJobRecord.getRecordJob());
        KJobMonitor templateOne = sqlManager.templateOne(template);
        templateOne.setLastExecuteTime(lastExecuteTime);
        //在监控表中增加下一次执行时间
        templateOne.setNextExecuteTime(nextExecuteTime);
        if (kJobRecord.getRecordStatus() == 1) {// 证明成功
            //成功次数加1
            templateOne.setMonitorSuccess(templateOne.getMonitorSuccess() + 1);
            sqlManager.updateById(templateOne);
        } else if (kJobRecord.getRecordStatus() == 2) {// 证明失败
            //失败次数加1
            templateOne.setMonitorFail(templateOne.getMonitorFail() + 1);
            sqlManager.updateById(templateOne);
        }
        DSTransactionManager.commit();
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        //stop the running job
        this.job.stopAll();
    }
}
