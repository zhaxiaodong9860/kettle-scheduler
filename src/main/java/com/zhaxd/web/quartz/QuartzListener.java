package com.zhaxd.web.quartz;

import java.util.Date;

import com.zhaxd.common.toolkit.Constant;
import com.zhaxd.core.model.KJob;
import com.zhaxd.core.model.KTrans;
import com.zhaxd.web.quartz.model.DBConnectionModel;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class QuartzListener implements JobListener{

	@Override
	public String getName() {
		return new Date().getTime() + "QuartzListener";
	}
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
	}
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().getName();
		String jobGroupName = context.getJobDetail().getKey().getGroup();
		String triggerName = context.getTrigger().getKey().getName();
		String triggerGroupName = context.getTrigger().getKey().getGroup();
		//一次性任务，执行完之后需要移除
		QuartzManager.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String jobId = String.valueOf(dataMap.get(Constant.TRANSID));
		String jobtype = String.valueOf(dataMap.get(Constant.JOBTYPE));

		Object DbConnectionObject = dataMap.get(Constant.DBCONNECTIONOBJECT);
		DBConnectionModel DBConnectionModel = (DBConnectionModel) DbConnectionObject;
		ConnectionSource source = ConnectionSourceHelper.getSimple(DBConnectionModel.getConnectionDriveClassName(),
				DBConnectionModel.getConnectionUrl(), DBConnectionModel.getConnectionUser(), DBConnectionModel.getConnectionPassword());
		DBStyle mysql = new MySqlStyle();
		SQLLoader loader = new ClasspathLoader("/");
		UnderlinedNameConversion nc = new  UnderlinedNameConversion();
		SQLManager sqlManager = new SQLManager(mysql, loader,
				source, nc, new Interceptor[]{new DebugInterceptor()});
		if (jobtype.equals("1")){
			KJob kJob = sqlManager.unique(KJob.class,Integer.valueOf(jobId));
			kJob.setJobStatus(2);
			sqlManager.updateTemplateById(kJob);
		}
		else if (jobtype.equals("2")) {
			KTrans kTrans = sqlManager.unique(KTrans.class, Integer.valueOf(jobId));
			kTrans.setTransStatus(2);
			sqlManager.updateTemplateById(kTrans);
		}
	}
}
