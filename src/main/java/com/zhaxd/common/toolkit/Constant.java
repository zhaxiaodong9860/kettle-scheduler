package com.zhaxd.common.toolkit;

import java.io.FileInputStream;
import java.util.Properties;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.logging.LogLevel;

/**
 * 
 * @ClassName Constant 
 * @Description 常量类 
 * @author zhaxd
 * @date 2017年5月28日 下午3:33:55 
 */
public class Constant extends Const {

	/** Public */
	public static final String VERSION = "7.0.0.0-25";
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String DEFAULT_TIMEZONE = "GMT+8";
	public static final String UKETTLE = "resource/kettle.properties";
	public static final String SESSION_ID = "SESSION_ID";
	
	public static final String STATUS_ENABLED = "ENABLED";
	public static final String STATUS_DISABLED = "DISABLED";
	public static final String STATUS_FINISHED = "FINISHED";
	public static final String STATUS_CLOSED = "CLOSED";

	public static final String STATUS_ERROR = "ERROR";
	public static final String STATUS_AGAIN = "AGAIN";
	public static final String STATUS_COMPLETE = "COMPLETE";
	
	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_XML = "xml";
	public static final String FORMAT_STREAM = "stream";
	public static final int VALID_TIMESTAMP = 15;

	/** Kettle */
	public static final String TYPE_JOB = "job";
	public static final String TYPE_TRANS = "transformation";
	public static final String TYPE_JOB_SUFFIX = ".kjb";
	public static final String TYPE_TRANS_SUFFIX = ".ktr";
	public static final String TYPE_TESTING = "TESTING";
	public static final String TYPE_RUNNING = "RUNNING";
	public static final String TYPE_USER_KETTLE = "KETTLE";
	public static final String TYPE_AGAIN = "AGAIN";

	public static final String STARTS_WITH_USD = "$";
	public static final String STARTS_WITH_PARAM = "-param:";
	public static final String SPLIT_PARAM = "-param:";
	public static final String SPLIT_EQUAL = "=";
	public static final String SPLIT_USD = "$";
	public static final String KETTLE_REPO = "repo";
	
	/** quartz **/
	
	public static final String JOB_PREFIX = "JOB";
	public static final String JOB_GROUP_PREFIX = "JOB_GROUP";
	public static final String TRIGGER_PREFIX = "TRIGGER";
	public static final String TRIGGER_GROUP_PREFIX = "TRIGGER_GROUP";
	public static final String QUARTZ_SEPARATE = "@";
	
	/** quartz parameter **/
	public static final String REPOSITORYOBJECT = "REPOSITORYOBJECT";
	public static final String DBCONNECTIONOBJECT = "DBCONNECTIONOBJECT";
	public static final String JOBID = "JOBID";
	public static final String TRANSID= "TRANSID";
	public static final String JOBTYPE="JOBTYPE";
	public static final String JOBPATH = "JOBPATH";
	public static final String TRANSPATH = "TRANSPATH";
	public static final String JOBNAME = "JOBNAME";
	public static final String TRANSNAME = "TRANSNAME";
	public static final String USERID = "USERID";
	public static final String LOGLEVEL = "LOGLEVEL";
	public static final String LOGFILEPATH = "LOGFILEPATH";
	
	public static final String RUNSTATUS_SEPARATE = "-";
	
	public static String KETTLE_HOME;
	public static String KETTLE_PLUGIN;
	public static String KETTLE_SCRIPT;
	public static LogLevel KETTLE_LOGLEVEL;

	public static Properties props;

	static {
		props = readProperties();
		KETTLE_HOME = props.getProperty("kettle.home");
//		KETTLE_PLUGIN = KETTLE_HOME + FILE_SEPARATOR
//				+ props.getProperty("kettle.plugin");
		KETTLE_PLUGIN = props.getProperty("kettle.plugin");
		KETTLE_SCRIPT = uKettle()
				+ props.getProperty("kettle.script");
		KETTLE_LOGLEVEL = logger(props
				.getProperty("kettle.loglevel"));

	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	public static void set(Properties p) {
		props = p;
	}

	public static Properties readProperties() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(Constant.class.getResource("/")
					.getPath().replace("%20", " ")
					+ UKETTLE));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	public static LogLevel logger(String level) {
		LogLevel logLevel = null;
		if ("basic".equals(level)) {
			logLevel = LogLevel.BASIC;
		} else if ("detail".equals(level)) {
			logLevel = LogLevel.DETAILED;
		} else if ("error".equals(level)) {
			logLevel = LogLevel.ERROR;
		} else if ("debug".equals(level)) {
			logLevel = LogLevel.DEBUG;
		} else if ("minimal".equals(level)) {
			logLevel = LogLevel.MINIMAL;
		} else if ("rowlevel".equals(level)) {
			logLevel = LogLevel.ROWLEVEL;
		} else if ("Nothing".endsWith(level)){
			logLevel = LogLevel.NOTHING;
		}else {
			logLevel = KETTLE_LOGLEVEL;
		}
		return logLevel;
	}

	private static String uKettle() {
		String classPath = Constant.class.getResource("/").getPath()
				.replace("%20", " ");
		String iQuartz = "";
		String index = "WEB-INF";
		if (classPath.indexOf("target") > 0) {
			index = "target";
		}
		// windows path
		if ("\\".equals(Constant.FILE_SEPARATOR)) {
			iQuartz = classPath.substring(1, classPath.indexOf(index));
			iQuartz = iQuartz.replace("/", "\\");
		}
		// linux path
		if ("/".equals(Constant.FILE_SEPARATOR)) {
			iQuartz = classPath.substring(0, classPath.indexOf(index));
			iQuartz = iQuartz.replace("\\", "/");
		}
		return iQuartz;
	}

}