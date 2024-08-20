
package hydronorth.integracao_pedidos_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

//the import part of tJavaRow_1
//import java.util.List;

@SuppressWarnings("unused")

/**
 * Job: Integracao_Pedidos Purpose: <br>
 * Description: <br>
 * 
 * @author removed, thiagosanti
 * @version 8.0.1.20240619_0909-patch
 * @status DEV
 */
public class Integracao_Pedidos implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Integracao_Pedidos.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Integracao_Pedidos.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
		return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
		return this.additionalProperties;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Integracao_Pedidos";
	private final String projectName = "HYDRONORTH";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_i5bikEg2Ee-c4sBnjyqvig", "0.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Integracao_Pedidos.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Integracao_Pedidos.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_20_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_8_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_8_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_8_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_27_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_28_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_6_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_29_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row23_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row13_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row17_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row22_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_1_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_1_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_1_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_2_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_2_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_2_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_3_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_3_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_3_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_9_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_6_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_8_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tHashInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_9_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tDBInput_10_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tHashInput_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DATA_ENVIO;

		public java.util.Date getDB_PED_DATA_ENVIO() {
			return this.DB_PED_DATA_ENVIO;
		}

		public Boolean DB_PED_DATA_ENVIOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DATA_ENVIOIsKey() {
			return false;
		}

		public Integer DB_PED_DATA_ENVIOLength() {
			return 23;
		}

		public Integer DB_PED_DATA_ENVIOPrecision() {
			return 3;
		}

		public String DB_PED_DATA_ENVIODefault() {

			return null;

		}

		public String DB_PED_DATA_ENVIOComment() {

			return "";

		}

		public String DB_PED_DATA_ENVIOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DATA_ENVIOOriginalDbColumnName() {

			return "DB_PED_DATA_ENVIO";

		}

		public Short DB_PED_SITUACAO;

		public Short getDB_PED_SITUACAO() {
			return this.DB_PED_SITUACAO;
		}

		public Boolean DB_PED_SITUACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_SITUACAOIsKey() {
			return false;
		}

		public Integer DB_PED_SITUACAOLength() {
			return 3;
		}

		public Integer DB_PED_SITUACAOPrecision() {
			return 0;
		}

		public String DB_PED_SITUACAODefault() {

			return "";

		}

		public String DB_PED_SITUACAOComment() {

			return "";

		}

		public String DB_PED_SITUACAOPattern() {

			return "";

		}

		public String DB_PED_SITUACAOOriginalDbColumnName() {

			return "DB_PED_SITUACAO";

		}

		public String DB_PED_EMPRESA;

		public String getDB_PED_EMPRESA() {
			return this.DB_PED_EMPRESA;
		}

		public Boolean DB_PED_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_PED_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_PED_EMPRESALength() {
			return 3;
		}

		public Integer DB_PED_EMPRESAPrecision() {
			return 0;
		}

		public String DB_PED_EMPRESADefault() {

			return null;

		}

		public String DB_PED_EMPRESAComment() {

			return "";

		}

		public String DB_PED_EMPRESAPattern() {

			return "";

		}

		public String DB_PED_EMPRESAOriginalDbColumnName() {

			return "DB_PED_EMPRESA";

		}

		public Double DB_PED_CLIENTE;

		public Double getDB_PED_CLIENTE() {
			return this.DB_PED_CLIENTE;
		}

		public Boolean DB_PED_CLIENTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_CLIENTEIsKey() {
			return false;
		}

		public Integer DB_PED_CLIENTELength() {
			return 15;
		}

		public Integer DB_PED_CLIENTEPrecision() {
			return 0;
		}

		public String DB_PED_CLIENTEDefault() {

			return "";

		}

		public String DB_PED_CLIENTEComment() {

			return "";

		}

		public String DB_PED_CLIENTEPattern() {

			return "";

		}

		public String DB_PED_CLIENTEOriginalDbColumnName() {

			return "DB_PED_CLIENTE";

		}

		public Integer DB_PEDC_LIBERASEMSALDO;

		public Integer getDB_PEDC_LIBERASEMSALDO() {
			return this.DB_PEDC_LIBERASEMSALDO;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsNullable() {
			return true;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsKey() {
			return false;
		}

		public Integer DB_PEDC_LIBERASEMSALDOLength() {
			return 10;
		}

		public Integer DB_PEDC_LIBERASEMSALDOPrecision() {
			return 0;
		}

		public String DB_PEDC_LIBERASEMSALDODefault() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOComment() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOPattern() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOOriginalDbColumnName() {

			return "DB_PEDC_LIBERASEMSALDO";

		}

		public Double DB_PED_COD_TRANSP;

		public Double getDB_PED_COD_TRANSP() {
			return this.DB_PED_COD_TRANSP;
		}

		public Boolean DB_PED_COD_TRANSPIsNullable() {
			return true;
		}

		public Boolean DB_PED_COD_TRANSPIsKey() {
			return false;
		}

		public Integer DB_PED_COD_TRANSPLength() {
			return 15;
		}

		public Integer DB_PED_COD_TRANSPPrecision() {
			return 0;
		}

		public String DB_PED_COD_TRANSPDefault() {

			return "";

		}

		public String DB_PED_COD_TRANSPComment() {

			return "";

		}

		public String DB_PED_COD_TRANSPPattern() {

			return "";

		}

		public String DB_PED_COD_TRANSPOriginalDbColumnName() {

			return "DB_PED_COD_TRANSP";

		}

		public Integer DB_PED_COND_PGTO;

		public Integer getDB_PED_COND_PGTO() {
			return this.DB_PED_COND_PGTO;
		}

		public Boolean DB_PED_COND_PGTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_COND_PGTOIsKey() {
			return false;
		}

		public Integer DB_PED_COND_PGTOLength() {
			return 10;
		}

		public Integer DB_PED_COND_PGTOPrecision() {
			return 0;
		}

		public String DB_PED_COND_PGTODefault() {

			return "";

		}

		public String DB_PED_COND_PGTOComment() {

			return "";

		}

		public String DB_PED_COND_PGTOPattern() {

			return "";

		}

		public String DB_PED_COND_PGTOOriginalDbColumnName() {

			return "DB_PED_COND_PGTO";

		}

		public String DB_PED_LISTA_PRECO;

		public String getDB_PED_LISTA_PRECO() {
			return this.DB_PED_LISTA_PRECO;
		}

		public Boolean DB_PED_LISTA_PRECOIsNullable() {
			return true;
		}

		public Boolean DB_PED_LISTA_PRECOIsKey() {
			return false;
		}

		public Integer DB_PED_LISTA_PRECOLength() {
			return 8;
		}

		public Integer DB_PED_LISTA_PRECOPrecision() {
			return 0;
		}

		public String DB_PED_LISTA_PRECODefault() {

			return null;

		}

		public String DB_PED_LISTA_PRECOComment() {

			return "";

		}

		public String DB_PED_LISTA_PRECOPattern() {

			return "";

		}

		public String DB_PED_LISTA_PRECOOriginalDbColumnName() {

			return "DB_PED_LISTA_PRECO";

		}

		public Integer DB_PED_REPRES;

		public Integer getDB_PED_REPRES() {
			return this.DB_PED_REPRES;
		}

		public Boolean DB_PED_REPRESIsNullable() {
			return true;
		}

		public Boolean DB_PED_REPRESIsKey() {
			return false;
		}

		public Integer DB_PED_REPRESLength() {
			return 10;
		}

		public Integer DB_PED_REPRESPrecision() {
			return 0;
		}

		public String DB_PED_REPRESDefault() {

			return "";

		}

		public String DB_PED_REPRESComment() {

			return "";

		}

		public String DB_PED_REPRESPattern() {

			return "";

		}

		public String DB_PED_REPRESOriginalDbColumnName() {

			return "DB_PED_REPRES";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		public String DB_PED_TIPO;

		public String getDB_PED_TIPO() {
			return this.DB_PED_TIPO;
		}

		public Boolean DB_PED_TIPOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPOIsKey() {
			return false;
		}

		public Integer DB_PED_TIPOLength() {
			return 3;
		}

		public Integer DB_PED_TIPOPrecision() {
			return 0;
		}

		public String DB_PED_TIPODefault() {

			return null;

		}

		public String DB_PED_TIPOComment() {

			return "";

		}

		public String DB_PED_TIPOPattern() {

			return "";

		}

		public String DB_PED_TIPOOriginalDbColumnName() {

			return "DB_PED_TIPO";

		}

		public String DB_PED_TEXTO;

		public String getDB_PED_TEXTO() {
			return this.DB_PED_TEXTO;
		}

		public Boolean DB_PED_TEXTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TEXTOIsKey() {
			return false;
		}

		public Integer DB_PED_TEXTOLength() {
			return 255;
		}

		public Integer DB_PED_TEXTOPrecision() {
			return 0;
		}

		public String DB_PED_TEXTODefault() {

			return null;

		}

		public String DB_PED_TEXTOComment() {

			return "";

		}

		public String DB_PED_TEXTOPattern() {

			return "";

		}

		public String DB_PED_TEXTOOriginalDbColumnName() {

			return "DB_PED_TEXTO";

		}

		public String DB_PED_ORD_COMPRA;

		public String getDB_PED_ORD_COMPRA() {
			return this.DB_PED_ORD_COMPRA;
		}

		public Boolean DB_PED_ORD_COMPRAIsNullable() {
			return true;
		}

		public Boolean DB_PED_ORD_COMPRAIsKey() {
			return false;
		}

		public Integer DB_PED_ORD_COMPRALength() {
			return 25;
		}

		public Integer DB_PED_ORD_COMPRAPrecision() {
			return 0;
		}

		public String DB_PED_ORD_COMPRADefault() {

			return null;

		}

		public String DB_PED_ORD_COMPRAComment() {

			return "";

		}

		public String DB_PED_ORD_COMPRAPattern() {

			return "";

		}

		public String DB_PED_ORD_COMPRAOriginalDbColumnName() {

			return "DB_PED_ORD_COMPRA";

		}

		public String DB_PED_OBSERV;

		public String getDB_PED_OBSERV() {
			return this.DB_PED_OBSERV;
		}

		public Boolean DB_PED_OBSERVIsNullable() {
			return true;
		}

		public Boolean DB_PED_OBSERVIsKey() {
			return false;
		}

		public Integer DB_PED_OBSERVLength() {
			return 255;
		}

		public Integer DB_PED_OBSERVPrecision() {
			return 0;
		}

		public String DB_PED_OBSERVDefault() {

			return null;

		}

		public String DB_PED_OBSERVComment() {

			return "";

		}

		public String DB_PED_OBSERVPattern() {

			return "";

		}

		public String DB_PED_OBSERVOriginalDbColumnName() {

			return "DB_PED_OBSERV";

		}

		public java.util.Date DB_PED_DT_PREVENT;

		public java.util.Date getDB_PED_DT_PREVENT() {
			return this.DB_PED_DT_PREVENT;
		}

		public Boolean DB_PED_DT_PREVENTIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_PREVENTIsKey() {
			return false;
		}

		public Integer DB_PED_DT_PREVENTLength() {
			return 23;
		}

		public Integer DB_PED_DT_PREVENTPrecision() {
			return 3;
		}

		public String DB_PED_DT_PREVENTDefault() {

			return null;

		}

		public String DB_PED_DT_PREVENTComment() {

			return "";

		}

		public String DB_PED_DT_PREVENTPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_PREVENTOriginalDbColumnName() {

			return "DB_PED_DT_PREVENT";

		}

		public Short DB_PED_TIPO_FRETE;

		public Short getDB_PED_TIPO_FRETE() {
			return this.DB_PED_TIPO_FRETE;
		}

		public Boolean DB_PED_TIPO_FRETEIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPO_FRETEIsKey() {
			return false;
		}

		public Integer DB_PED_TIPO_FRETELength() {
			return 5;
		}

		public Integer DB_PED_TIPO_FRETEPrecision() {
			return 0;
		}

		public String DB_PED_TIPO_FRETEDefault() {

			return "";

		}

		public String DB_PED_TIPO_FRETEComment() {

			return "";

		}

		public String DB_PED_TIPO_FRETEPattern() {

			return "";

		}

		public String DB_PED_TIPO_FRETEOriginalDbColumnName() {

			return "DB_PED_TIPO_FRETE";

		}

		public String DB_PED_ENT_ENDER;

		public String getDB_PED_ENT_ENDER() {
			return this.DB_PED_ENT_ENDER;
		}

		public Boolean DB_PED_ENT_ENDERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_ENDERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_ENDERLength() {
			return 50;
		}

		public Integer DB_PED_ENT_ENDERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_ENDERDefault() {

			return null;

		}

		public String DB_PED_ENT_ENDERComment() {

			return "";

		}

		public String DB_PED_ENT_ENDERPattern() {

			return "";

		}

		public String DB_PED_ENT_ENDEROriginalDbColumnName() {

			return "DB_PED_ENT_ENDER";

		}

		public String DB_PED_ENT_BAIRRO;

		public String getDB_PED_ENT_BAIRRO() {
			return this.DB_PED_ENT_BAIRRO;
		}

		public Boolean DB_PED_ENT_BAIRROIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_BAIRROIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_BAIRROLength() {
			return 40;
		}

		public Integer DB_PED_ENT_BAIRROPrecision() {
			return 0;
		}

		public String DB_PED_ENT_BAIRRODefault() {

			return null;

		}

		public String DB_PED_ENT_BAIRROComment() {

			return "";

		}

		public String DB_PED_ENT_BAIRROPattern() {

			return "";

		}

		public String DB_PED_ENT_BAIRROOriginalDbColumnName() {

			return "DB_PED_ENT_BAIRRO";

		}

		public String DB_PED_ENT_CIDADE;

		public String getDB_PED_ENT_CIDADE() {
			return this.DB_PED_ENT_CIDADE;
		}

		public Boolean DB_PED_ENT_CIDADEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CIDADEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CIDADELength() {
			return 30;
		}

		public Integer DB_PED_ENT_CIDADEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CIDADEDefault() {

			return null;

		}

		public String DB_PED_ENT_CIDADEComment() {

			return "";

		}

		public String DB_PED_ENT_CIDADEPattern() {

			return "";

		}

		public String DB_PED_ENT_CIDADEOriginalDbColumnName() {

			return "DB_PED_ENT_CIDADE";

		}

		public String DB_PED_ENT_CEP;

		public String getDB_PED_ENT_CEP() {
			return this.DB_PED_ENT_CEP;
		}

		public Boolean DB_PED_ENT_CEPIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CEPIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CEPLength() {
			return 9;
		}

		public Integer DB_PED_ENT_CEPPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CEPDefault() {

			return null;

		}

		public String DB_PED_ENT_CEPComment() {

			return "";

		}

		public String DB_PED_ENT_CEPPattern() {

			return "";

		}

		public String DB_PED_ENT_CEPOriginalDbColumnName() {

			return "DB_PED_ENT_CEP";

		}

		public String DB_PED_ENT_UF;

		public String getDB_PED_ENT_UF() {
			return this.DB_PED_ENT_UF;
		}

		public Boolean DB_PED_ENT_UFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_UFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_UFLength() {
			return 8;
		}

		public Integer DB_PED_ENT_UFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_UFDefault() {

			return null;

		}

		public String DB_PED_ENT_UFComment() {

			return "";

		}

		public String DB_PED_ENT_UFPattern() {

			return "";

		}

		public String DB_PED_ENT_UFOriginalDbColumnName() {

			return "DB_PED_ENT_UF";

		}

		public String DB_PED_ENT_CGCMF;

		public String getDB_PED_ENT_CGCMF() {
			return this.DB_PED_ENT_CGCMF;
		}

		public Boolean DB_PED_ENT_CGCMFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCMFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCMFLength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCMFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCMFDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCMFComment() {

			return "";

		}

		public String DB_PED_ENT_CGCMFPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCMFOriginalDbColumnName() {

			return "DB_PED_ENT_CGCMF";

		}

		public String DB_PED_ENT_CGCTE;

		public String getDB_PED_ENT_CGCTE() {
			return this.DB_PED_ENT_CGCTE;
		}

		public Boolean DB_PED_ENT_CGCTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCTEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCTELength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCTEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCTEDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCTEComment() {

			return "";

		}

		public String DB_PED_ENT_CGCTEPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCTEOriginalDbColumnName() {

			return "DB_PED_ENT_CGCTE";

		}

		public String DB_PED_ENT_COMPL;

		public String getDB_PED_ENT_COMPL() {
			return this.DB_PED_ENT_COMPL;
		}

		public Boolean DB_PED_ENT_COMPLIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_COMPLIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_COMPLLength() {
			return 50;
		}

		public Integer DB_PED_ENT_COMPLPrecision() {
			return 0;
		}

		public String DB_PED_ENT_COMPLDefault() {

			return null;

		}

		public String DB_PED_ENT_COMPLComment() {

			return "";

		}

		public String DB_PED_ENT_COMPLPattern() {

			return "";

		}

		public String DB_PED_ENT_COMPLOriginalDbColumnName() {

			return "DB_PED_ENT_COMPL";

		}

		public String DB_PED_ENT_PONTOREFER;

		public String getDB_PED_ENT_PONTOREFER() {
			return this.DB_PED_ENT_PONTOREFER;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_PONTOREFERLength() {
			return 40;
		}

		public Integer DB_PED_ENT_PONTOREFERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_PONTOREFERDefault() {

			return null;

		}

		public String DB_PED_ENT_PONTOREFERComment() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFERPattern() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFEROriginalDbColumnName() {

			return "DB_PED_ENT_PONTOREFER";

		}

		public String DB_PED_ENT_RAZAO;

		public String getDB_PED_ENT_RAZAO() {
			return this.DB_PED_ENT_RAZAO;
		}

		public Boolean DB_PED_ENT_RAZAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAZAOIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAZAOLength() {
			return 80;
		}

		public Integer DB_PED_ENT_RAZAOPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAZAODefault() {

			return null;

		}

		public String DB_PED_ENT_RAZAOComment() {

			return "";

		}

		public String DB_PED_ENT_RAZAOPattern() {

			return "";

		}

		public String DB_PED_ENT_RAZAOOriginalDbColumnName() {

			return "DB_PED_ENT_RAZAO";

		}

		public String DB_PED_ENT_TELEFONE;

		public String getDB_PED_ENT_TELEFONE() {
			return this.DB_PED_ENT_TELEFONE;
		}

		public Boolean DB_PED_ENT_TELEFONEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_TELEFONEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_TELEFONELength() {
			return 15;
		}

		public Integer DB_PED_ENT_TELEFONEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_TELEFONEDefault() {

			return null;

		}

		public String DB_PED_ENT_TELEFONEComment() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEPattern() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEOriginalDbColumnName() {

			return "DB_PED_ENT_TELEFONE";

		}

		public String DB_PED_ENT_RAMAL;

		public String getDB_PED_ENT_RAMAL() {
			return this.DB_PED_ENT_RAMAL;
		}

		public Boolean DB_PED_ENT_RAMALIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAMALIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAMALLength() {
			return 5;
		}

		public Integer DB_PED_ENT_RAMALPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAMALDefault() {

			return null;

		}

		public String DB_PED_ENT_RAMALComment() {

			return "";

		}

		public String DB_PED_ENT_RAMALPattern() {

			return "";

		}

		public String DB_PED_ENT_RAMALOriginalDbColumnName() {

			return "DB_PED_ENT_RAMAL";

		}

		public Float DB_PEDI_QTDE_SOLIC;

		public Float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Float DB_PEDI_PRECO_LIQ;

		public Float getDB_PEDI_PRECO_LIQ() {
			return this.DB_PEDI_PRECO_LIQ;
		}

		public Boolean DB_PEDI_PRECO_LIQIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRECO_LIQIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRECO_LIQLength() {
			return 7;
		}

		public Integer DB_PEDI_PRECO_LIQPrecision() {
			return 0;
		}

		public String DB_PEDI_PRECO_LIQDefault() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQComment() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQPattern() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQOriginalDbColumnName() {

			return "DB_PEDI_PRECO_LIQ";

		}

		public String DB_TBPGTO_ZTERM;

		public String getDB_TBPGTO_ZTERM() {
			return this.DB_TBPGTO_ZTERM;
		}

		public Boolean DB_TBPGTO_ZTERMIsNullable() {
			return true;
		}

		public Boolean DB_TBPGTO_ZTERMIsKey() {
			return false;
		}

		public Integer DB_TBPGTO_ZTERMLength() {
			return 10;
		}

		public Integer DB_TBPGTO_ZTERMPrecision() {
			return 0;
		}

		public String DB_TBPGTO_ZTERMDefault() {

			return null;

		}

		public String DB_TBPGTO_ZTERMComment() {

			return "";

		}

		public String DB_TBPGTO_ZTERMPattern() {

			return "";

		}

		public String DB_TBPGTO_ZTERMOriginalDbColumnName() {

			return "DB_TBPGTO_ZTERM";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PED_DATA_ENVIO = this.DB_PED_DATA_ENVIO;
			other.DB_PED_SITUACAO = this.DB_PED_SITUACAO;
			other.DB_PED_EMPRESA = this.DB_PED_EMPRESA;
			other.DB_PED_CLIENTE = this.DB_PED_CLIENTE;
			other.DB_PEDC_LIBERASEMSALDO = this.DB_PEDC_LIBERASEMSALDO;
			other.DB_PED_COD_TRANSP = this.DB_PED_COD_TRANSP;
			other.DB_PED_COND_PGTO = this.DB_PED_COND_PGTO;
			other.DB_PED_LISTA_PRECO = this.DB_PED_LISTA_PRECO;
			other.DB_PED_REPRES = this.DB_PED_REPRES;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;
			other.DB_PED_TIPO = this.DB_PED_TIPO;
			other.DB_PED_TEXTO = this.DB_PED_TEXTO;
			other.DB_PED_ORD_COMPRA = this.DB_PED_ORD_COMPRA;
			other.DB_PED_OBSERV = this.DB_PED_OBSERV;
			other.DB_PED_DT_PREVENT = this.DB_PED_DT_PREVENT;
			other.DB_PED_TIPO_FRETE = this.DB_PED_TIPO_FRETE;
			other.DB_PED_ENT_ENDER = this.DB_PED_ENT_ENDER;
			other.DB_PED_ENT_BAIRRO = this.DB_PED_ENT_BAIRRO;
			other.DB_PED_ENT_CIDADE = this.DB_PED_ENT_CIDADE;
			other.DB_PED_ENT_CEP = this.DB_PED_ENT_CEP;
			other.DB_PED_ENT_UF = this.DB_PED_ENT_UF;
			other.DB_PED_ENT_CGCMF = this.DB_PED_ENT_CGCMF;
			other.DB_PED_ENT_CGCTE = this.DB_PED_ENT_CGCTE;
			other.DB_PED_ENT_COMPL = this.DB_PED_ENT_COMPL;
			other.DB_PED_ENT_PONTOREFER = this.DB_PED_ENT_PONTOREFER;
			other.DB_PED_ENT_RAZAO = this.DB_PED_ENT_RAZAO;
			other.DB_PED_ENT_TELEFONE = this.DB_PED_ENT_TELEFONE;
			other.DB_PED_ENT_RAMAL = this.DB_PED_ENT_RAMAL;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.DB_PEDI_PRECO_LIQ = this.DB_PEDI_PRECO_LIQ;
			other.DB_TBPGTO_ZTERM = this.DB_TBPGTO_ZTERM;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					this.DB_PEDC_LIBERASEMSALDO = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_QTDE_SOLIC = null;
					} else {
						this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_PRECO_LIQ = null;
					} else {
						this.DB_PEDI_PRECO_LIQ = dis.readFloat();
					}

					this.DB_TBPGTO_ZTERM = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					this.DB_PEDC_LIBERASEMSALDO = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_QTDE_SOLIC = null;
					} else {
						this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_PRECO_LIQ = null;
					} else {
						this.DB_PEDI_PRECO_LIQ = dis.readFloat();
					}

					this.DB_TBPGTO_ZTERM = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Integer

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos);

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

				// Float

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				// Float

				if (this.DB_PEDI_PRECO_LIQ == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

				// String

				writeString(this.DB_TBPGTO_ZTERM, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Integer

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos);

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

				// Float

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				// Float

				if (this.DB_PEDI_PRECO_LIQ == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

				// String

				writeString(this.DB_TBPGTO_ZTERM, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DATA_ENVIO=" + String.valueOf(DB_PED_DATA_ENVIO));
			sb.append(",DB_PED_SITUACAO=" + String.valueOf(DB_PED_SITUACAO));
			sb.append(",DB_PED_EMPRESA=" + DB_PED_EMPRESA);
			sb.append(",DB_PED_CLIENTE=" + String.valueOf(DB_PED_CLIENTE));
			sb.append(",DB_PEDC_LIBERASEMSALDO=" + String.valueOf(DB_PEDC_LIBERASEMSALDO));
			sb.append(",DB_PED_COD_TRANSP=" + String.valueOf(DB_PED_COD_TRANSP));
			sb.append(",DB_PED_COND_PGTO=" + String.valueOf(DB_PED_COND_PGTO));
			sb.append(",DB_PED_LISTA_PRECO=" + DB_PED_LISTA_PRECO);
			sb.append(",DB_PED_REPRES=" + String.valueOf(DB_PED_REPRES));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append(",DB_PED_TIPO=" + DB_PED_TIPO);
			sb.append(",DB_PED_TEXTO=" + DB_PED_TEXTO);
			sb.append(",DB_PED_ORD_COMPRA=" + DB_PED_ORD_COMPRA);
			sb.append(",DB_PED_OBSERV=" + DB_PED_OBSERV);
			sb.append(",DB_PED_DT_PREVENT=" + String.valueOf(DB_PED_DT_PREVENT));
			sb.append(",DB_PED_TIPO_FRETE=" + String.valueOf(DB_PED_TIPO_FRETE));
			sb.append(",DB_PED_ENT_ENDER=" + DB_PED_ENT_ENDER);
			sb.append(",DB_PED_ENT_BAIRRO=" + DB_PED_ENT_BAIRRO);
			sb.append(",DB_PED_ENT_CIDADE=" + DB_PED_ENT_CIDADE);
			sb.append(",DB_PED_ENT_CEP=" + DB_PED_ENT_CEP);
			sb.append(",DB_PED_ENT_UF=" + DB_PED_ENT_UF);
			sb.append(",DB_PED_ENT_CGCMF=" + DB_PED_ENT_CGCMF);
			sb.append(",DB_PED_ENT_CGCTE=" + DB_PED_ENT_CGCTE);
			sb.append(",DB_PED_ENT_COMPL=" + DB_PED_ENT_COMPL);
			sb.append(",DB_PED_ENT_PONTOREFER=" + DB_PED_ENT_PONTOREFER);
			sb.append(",DB_PED_ENT_RAZAO=" + DB_PED_ENT_RAZAO);
			sb.append(",DB_PED_ENT_TELEFONE=" + DB_PED_ENT_TELEFONE);
			sb.append(",DB_PED_ENT_RAMAL=" + DB_PED_ENT_RAMAL);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",DB_PEDI_PRECO_LIQ=" + String.valueOf(DB_PEDI_PRECO_LIQ));
			sb.append(",DB_TBPGTO_ZTERM=" + DB_TBPGTO_ZTERM);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DATA_ENVIO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DATA_ENVIO);
			}

			sb.append("|");

			if (DB_PED_SITUACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_SITUACAO);
			}

			sb.append("|");

			if (DB_PED_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_EMPRESA);
			}

			sb.append("|");

			if (DB_PED_CLIENTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_CLIENTE);
			}

			sb.append("|");

			if (DB_PEDC_LIBERASEMSALDO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDC_LIBERASEMSALDO);
			}

			sb.append("|");

			if (DB_PED_COD_TRANSP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COD_TRANSP);
			}

			sb.append("|");

			if (DB_PED_COND_PGTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COND_PGTO);
			}

			sb.append("|");

			if (DB_PED_LISTA_PRECO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_LISTA_PRECO);
			}

			sb.append("|");

			if (DB_PED_REPRES == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_REPRES);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			if (DB_PED_TIPO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO);
			}

			sb.append("|");

			if (DB_PED_TEXTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TEXTO);
			}

			sb.append("|");

			if (DB_PED_ORD_COMPRA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ORD_COMPRA);
			}

			sb.append("|");

			if (DB_PED_OBSERV == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OBSERV);
			}

			sb.append("|");

			if (DB_PED_DT_PREVENT == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_PREVENT);
			}

			sb.append("|");

			if (DB_PED_TIPO_FRETE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO_FRETE);
			}

			sb.append("|");

			if (DB_PED_ENT_ENDER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_ENDER);
			}

			sb.append("|");

			if (DB_PED_ENT_BAIRRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_BAIRRO);
			}

			sb.append("|");

			if (DB_PED_ENT_CIDADE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CIDADE);
			}

			sb.append("|");

			if (DB_PED_ENT_CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CEP);
			}

			sb.append("|");

			if (DB_PED_ENT_UF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_UF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCMF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCMF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCTE);
			}

			sb.append("|");

			if (DB_PED_ENT_COMPL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_COMPL);
			}

			sb.append("|");

			if (DB_PED_ENT_PONTOREFER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_PONTOREFER);
			}

			sb.append("|");

			if (DB_PED_ENT_RAZAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAZAO);
			}

			sb.append("|");

			if (DB_PED_ENT_TELEFONE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_TELEFONE);
			}

			sb.append("|");

			if (DB_PED_ENT_RAMAL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAMAL);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (DB_PEDI_PRECO_LIQ == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRECO_LIQ);
			}

			sb.append("|");

			if (DB_TBPGTO_ZTERM == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_TBPGTO_ZTERM);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Pedido_complementoStruct implements routines.system.IPersistableRow<Pedido_complementoStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DATA_ENVIO;

		public java.util.Date getDB_PED_DATA_ENVIO() {
			return this.DB_PED_DATA_ENVIO;
		}

		public Boolean DB_PED_DATA_ENVIOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DATA_ENVIOIsKey() {
			return false;
		}

		public Integer DB_PED_DATA_ENVIOLength() {
			return 23;
		}

		public Integer DB_PED_DATA_ENVIOPrecision() {
			return 3;
		}

		public String DB_PED_DATA_ENVIODefault() {

			return null;

		}

		public String DB_PED_DATA_ENVIOComment() {

			return "";

		}

		public String DB_PED_DATA_ENVIOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DATA_ENVIOOriginalDbColumnName() {

			return "DB_PED_DATA_ENVIO";

		}

		public Short DB_PED_SITUACAO;

		public Short getDB_PED_SITUACAO() {
			return this.DB_PED_SITUACAO;
		}

		public Boolean DB_PED_SITUACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_SITUACAOIsKey() {
			return false;
		}

		public Integer DB_PED_SITUACAOLength() {
			return 3;
		}

		public Integer DB_PED_SITUACAOPrecision() {
			return 0;
		}

		public String DB_PED_SITUACAODefault() {

			return "";

		}

		public String DB_PED_SITUACAOComment() {

			return "";

		}

		public String DB_PED_SITUACAOPattern() {

			return "";

		}

		public String DB_PED_SITUACAOOriginalDbColumnName() {

			return "DB_PED_SITUACAO";

		}

		public String DB_PED_EMPRESA;

		public String getDB_PED_EMPRESA() {
			return this.DB_PED_EMPRESA;
		}

		public Boolean DB_PED_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_PED_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_PED_EMPRESALength() {
			return 3;
		}

		public Integer DB_PED_EMPRESAPrecision() {
			return 0;
		}

		public String DB_PED_EMPRESADefault() {

			return null;

		}

		public String DB_PED_EMPRESAComment() {

			return "";

		}

		public String DB_PED_EMPRESAPattern() {

			return "";

		}

		public String DB_PED_EMPRESAOriginalDbColumnName() {

			return "DB_PED_EMPRESA";

		}

		public Double DB_PED_CLIENTE;

		public Double getDB_PED_CLIENTE() {
			return this.DB_PED_CLIENTE;
		}

		public Boolean DB_PED_CLIENTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_CLIENTEIsKey() {
			return false;
		}

		public Integer DB_PED_CLIENTELength() {
			return 15;
		}

		public Integer DB_PED_CLIENTEPrecision() {
			return 0;
		}

		public String DB_PED_CLIENTEDefault() {

			return "";

		}

		public String DB_PED_CLIENTEComment() {

			return "";

		}

		public String DB_PED_CLIENTEPattern() {

			return "";

		}

		public String DB_PED_CLIENTEOriginalDbColumnName() {

			return "DB_PED_CLIENTE";

		}

		public Integer DB_PEDC_LIBERASEMSALDO;

		public Integer getDB_PEDC_LIBERASEMSALDO() {
			return this.DB_PEDC_LIBERASEMSALDO;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsNullable() {
			return true;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsKey() {
			return false;
		}

		public Integer DB_PEDC_LIBERASEMSALDOLength() {
			return 10;
		}

		public Integer DB_PEDC_LIBERASEMSALDOPrecision() {
			return 0;
		}

		public String DB_PEDC_LIBERASEMSALDODefault() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOComment() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOPattern() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOOriginalDbColumnName() {

			return "DB_PEDC_LIBERASEMSALDO";

		}

		public Double DB_PED_COD_TRANSP;

		public Double getDB_PED_COD_TRANSP() {
			return this.DB_PED_COD_TRANSP;
		}

		public Boolean DB_PED_COD_TRANSPIsNullable() {
			return true;
		}

		public Boolean DB_PED_COD_TRANSPIsKey() {
			return false;
		}

		public Integer DB_PED_COD_TRANSPLength() {
			return 15;
		}

		public Integer DB_PED_COD_TRANSPPrecision() {
			return 0;
		}

		public String DB_PED_COD_TRANSPDefault() {

			return "";

		}

		public String DB_PED_COD_TRANSPComment() {

			return "";

		}

		public String DB_PED_COD_TRANSPPattern() {

			return "";

		}

		public String DB_PED_COD_TRANSPOriginalDbColumnName() {

			return "DB_PED_COD_TRANSP";

		}

		public Integer DB_PED_COND_PGTO;

		public Integer getDB_PED_COND_PGTO() {
			return this.DB_PED_COND_PGTO;
		}

		public Boolean DB_PED_COND_PGTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_COND_PGTOIsKey() {
			return false;
		}

		public Integer DB_PED_COND_PGTOLength() {
			return 10;
		}

		public Integer DB_PED_COND_PGTOPrecision() {
			return 0;
		}

		public String DB_PED_COND_PGTODefault() {

			return "";

		}

		public String DB_PED_COND_PGTOComment() {

			return "";

		}

		public String DB_PED_COND_PGTOPattern() {

			return "";

		}

		public String DB_PED_COND_PGTOOriginalDbColumnName() {

			return "DB_PED_COND_PGTO";

		}

		public String DB_PED_LISTA_PRECO;

		public String getDB_PED_LISTA_PRECO() {
			return this.DB_PED_LISTA_PRECO;
		}

		public Boolean DB_PED_LISTA_PRECOIsNullable() {
			return true;
		}

		public Boolean DB_PED_LISTA_PRECOIsKey() {
			return false;
		}

		public Integer DB_PED_LISTA_PRECOLength() {
			return 8;
		}

		public Integer DB_PED_LISTA_PRECOPrecision() {
			return 0;
		}

		public String DB_PED_LISTA_PRECODefault() {

			return null;

		}

		public String DB_PED_LISTA_PRECOComment() {

			return "";

		}

		public String DB_PED_LISTA_PRECOPattern() {

			return "";

		}

		public String DB_PED_LISTA_PRECOOriginalDbColumnName() {

			return "DB_PED_LISTA_PRECO";

		}

		public Integer DB_PED_REPRES;

		public Integer getDB_PED_REPRES() {
			return this.DB_PED_REPRES;
		}

		public Boolean DB_PED_REPRESIsNullable() {
			return true;
		}

		public Boolean DB_PED_REPRESIsKey() {
			return false;
		}

		public Integer DB_PED_REPRESLength() {
			return 10;
		}

		public Integer DB_PED_REPRESPrecision() {
			return 0;
		}

		public String DB_PED_REPRESDefault() {

			return "";

		}

		public String DB_PED_REPRESComment() {

			return "";

		}

		public String DB_PED_REPRESPattern() {

			return "";

		}

		public String DB_PED_REPRESOriginalDbColumnName() {

			return "DB_PED_REPRES";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		public String DB_PED_TIPO;

		public String getDB_PED_TIPO() {
			return this.DB_PED_TIPO;
		}

		public Boolean DB_PED_TIPOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPOIsKey() {
			return false;
		}

		public Integer DB_PED_TIPOLength() {
			return 3;
		}

		public Integer DB_PED_TIPOPrecision() {
			return 0;
		}

		public String DB_PED_TIPODefault() {

			return null;

		}

		public String DB_PED_TIPOComment() {

			return "";

		}

		public String DB_PED_TIPOPattern() {

			return "";

		}

		public String DB_PED_TIPOOriginalDbColumnName() {

			return "DB_PED_TIPO";

		}

		public String DB_PED_TEXTO;

		public String getDB_PED_TEXTO() {
			return this.DB_PED_TEXTO;
		}

		public Boolean DB_PED_TEXTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TEXTOIsKey() {
			return false;
		}

		public Integer DB_PED_TEXTOLength() {
			return 255;
		}

		public Integer DB_PED_TEXTOPrecision() {
			return 0;
		}

		public String DB_PED_TEXTODefault() {

			return null;

		}

		public String DB_PED_TEXTOComment() {

			return "";

		}

		public String DB_PED_TEXTOPattern() {

			return "";

		}

		public String DB_PED_TEXTOOriginalDbColumnName() {

			return "DB_PED_TEXTO";

		}

		public String DB_PED_ORD_COMPRA;

		public String getDB_PED_ORD_COMPRA() {
			return this.DB_PED_ORD_COMPRA;
		}

		public Boolean DB_PED_ORD_COMPRAIsNullable() {
			return true;
		}

		public Boolean DB_PED_ORD_COMPRAIsKey() {
			return false;
		}

		public Integer DB_PED_ORD_COMPRALength() {
			return 25;
		}

		public Integer DB_PED_ORD_COMPRAPrecision() {
			return 0;
		}

		public String DB_PED_ORD_COMPRADefault() {

			return null;

		}

		public String DB_PED_ORD_COMPRAComment() {

			return "";

		}

		public String DB_PED_ORD_COMPRAPattern() {

			return "";

		}

		public String DB_PED_ORD_COMPRAOriginalDbColumnName() {

			return "DB_PED_ORD_COMPRA";

		}

		public String DB_PED_OBSERV;

		public String getDB_PED_OBSERV() {
			return this.DB_PED_OBSERV;
		}

		public Boolean DB_PED_OBSERVIsNullable() {
			return true;
		}

		public Boolean DB_PED_OBSERVIsKey() {
			return false;
		}

		public Integer DB_PED_OBSERVLength() {
			return 255;
		}

		public Integer DB_PED_OBSERVPrecision() {
			return 0;
		}

		public String DB_PED_OBSERVDefault() {

			return null;

		}

		public String DB_PED_OBSERVComment() {

			return "";

		}

		public String DB_PED_OBSERVPattern() {

			return "";

		}

		public String DB_PED_OBSERVOriginalDbColumnName() {

			return "DB_PED_OBSERV";

		}

		public java.util.Date DB_PED_DT_PREVENT;

		public java.util.Date getDB_PED_DT_PREVENT() {
			return this.DB_PED_DT_PREVENT;
		}

		public Boolean DB_PED_DT_PREVENTIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_PREVENTIsKey() {
			return false;
		}

		public Integer DB_PED_DT_PREVENTLength() {
			return 23;
		}

		public Integer DB_PED_DT_PREVENTPrecision() {
			return 3;
		}

		public String DB_PED_DT_PREVENTDefault() {

			return null;

		}

		public String DB_PED_DT_PREVENTComment() {

			return "";

		}

		public String DB_PED_DT_PREVENTPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_PREVENTOriginalDbColumnName() {

			return "DB_PED_DT_PREVENT";

		}

		public Short DB_PED_TIPO_FRETE;

		public Short getDB_PED_TIPO_FRETE() {
			return this.DB_PED_TIPO_FRETE;
		}

		public Boolean DB_PED_TIPO_FRETEIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPO_FRETEIsKey() {
			return false;
		}

		public Integer DB_PED_TIPO_FRETELength() {
			return 5;
		}

		public Integer DB_PED_TIPO_FRETEPrecision() {
			return 0;
		}

		public String DB_PED_TIPO_FRETEDefault() {

			return "";

		}

		public String DB_PED_TIPO_FRETEComment() {

			return "";

		}

		public String DB_PED_TIPO_FRETEPattern() {

			return "";

		}

		public String DB_PED_TIPO_FRETEOriginalDbColumnName() {

			return "DB_PED_TIPO_FRETE";

		}

		public String DB_PED_ENT_ENDER;

		public String getDB_PED_ENT_ENDER() {
			return this.DB_PED_ENT_ENDER;
		}

		public Boolean DB_PED_ENT_ENDERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_ENDERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_ENDERLength() {
			return 50;
		}

		public Integer DB_PED_ENT_ENDERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_ENDERDefault() {

			return null;

		}

		public String DB_PED_ENT_ENDERComment() {

			return "";

		}

		public String DB_PED_ENT_ENDERPattern() {

			return "";

		}

		public String DB_PED_ENT_ENDEROriginalDbColumnName() {

			return "DB_PED_ENT_ENDER";

		}

		public String DB_PED_ENT_BAIRRO;

		public String getDB_PED_ENT_BAIRRO() {
			return this.DB_PED_ENT_BAIRRO;
		}

		public Boolean DB_PED_ENT_BAIRROIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_BAIRROIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_BAIRROLength() {
			return 40;
		}

		public Integer DB_PED_ENT_BAIRROPrecision() {
			return 0;
		}

		public String DB_PED_ENT_BAIRRODefault() {

			return null;

		}

		public String DB_PED_ENT_BAIRROComment() {

			return "";

		}

		public String DB_PED_ENT_BAIRROPattern() {

			return "";

		}

		public String DB_PED_ENT_BAIRROOriginalDbColumnName() {

			return "DB_PED_ENT_BAIRRO";

		}

		public String DB_PED_ENT_CIDADE;

		public String getDB_PED_ENT_CIDADE() {
			return this.DB_PED_ENT_CIDADE;
		}

		public Boolean DB_PED_ENT_CIDADEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CIDADEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CIDADELength() {
			return 30;
		}

		public Integer DB_PED_ENT_CIDADEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CIDADEDefault() {

			return null;

		}

		public String DB_PED_ENT_CIDADEComment() {

			return "";

		}

		public String DB_PED_ENT_CIDADEPattern() {

			return "";

		}

		public String DB_PED_ENT_CIDADEOriginalDbColumnName() {

			return "DB_PED_ENT_CIDADE";

		}

		public String DB_PED_ENT_CEP;

		public String getDB_PED_ENT_CEP() {
			return this.DB_PED_ENT_CEP;
		}

		public Boolean DB_PED_ENT_CEPIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CEPIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CEPLength() {
			return 9;
		}

		public Integer DB_PED_ENT_CEPPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CEPDefault() {

			return null;

		}

		public String DB_PED_ENT_CEPComment() {

			return "";

		}

		public String DB_PED_ENT_CEPPattern() {

			return "";

		}

		public String DB_PED_ENT_CEPOriginalDbColumnName() {

			return "DB_PED_ENT_CEP";

		}

		public String DB_PED_ENT_UF;

		public String getDB_PED_ENT_UF() {
			return this.DB_PED_ENT_UF;
		}

		public Boolean DB_PED_ENT_UFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_UFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_UFLength() {
			return 8;
		}

		public Integer DB_PED_ENT_UFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_UFDefault() {

			return null;

		}

		public String DB_PED_ENT_UFComment() {

			return "";

		}

		public String DB_PED_ENT_UFPattern() {

			return "";

		}

		public String DB_PED_ENT_UFOriginalDbColumnName() {

			return "DB_PED_ENT_UF";

		}

		public String DB_PED_ENT_CGCMF;

		public String getDB_PED_ENT_CGCMF() {
			return this.DB_PED_ENT_CGCMF;
		}

		public Boolean DB_PED_ENT_CGCMFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCMFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCMFLength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCMFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCMFDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCMFComment() {

			return "";

		}

		public String DB_PED_ENT_CGCMFPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCMFOriginalDbColumnName() {

			return "DB_PED_ENT_CGCMF";

		}

		public String DB_PED_ENT_CGCTE;

		public String getDB_PED_ENT_CGCTE() {
			return this.DB_PED_ENT_CGCTE;
		}

		public Boolean DB_PED_ENT_CGCTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCTEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCTELength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCTEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCTEDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCTEComment() {

			return "";

		}

		public String DB_PED_ENT_CGCTEPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCTEOriginalDbColumnName() {

			return "DB_PED_ENT_CGCTE";

		}

		public String DB_PED_ENT_COMPL;

		public String getDB_PED_ENT_COMPL() {
			return this.DB_PED_ENT_COMPL;
		}

		public Boolean DB_PED_ENT_COMPLIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_COMPLIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_COMPLLength() {
			return 50;
		}

		public Integer DB_PED_ENT_COMPLPrecision() {
			return 0;
		}

		public String DB_PED_ENT_COMPLDefault() {

			return null;

		}

		public String DB_PED_ENT_COMPLComment() {

			return "";

		}

		public String DB_PED_ENT_COMPLPattern() {

			return "";

		}

		public String DB_PED_ENT_COMPLOriginalDbColumnName() {

			return "DB_PED_ENT_COMPL";

		}

		public String DB_PED_ENT_PONTOREFER;

		public String getDB_PED_ENT_PONTOREFER() {
			return this.DB_PED_ENT_PONTOREFER;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_PONTOREFERLength() {
			return 40;
		}

		public Integer DB_PED_ENT_PONTOREFERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_PONTOREFERDefault() {

			return null;

		}

		public String DB_PED_ENT_PONTOREFERComment() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFERPattern() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFEROriginalDbColumnName() {

			return "DB_PED_ENT_PONTOREFER";

		}

		public String DB_PED_ENT_RAZAO;

		public String getDB_PED_ENT_RAZAO() {
			return this.DB_PED_ENT_RAZAO;
		}

		public Boolean DB_PED_ENT_RAZAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAZAOIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAZAOLength() {
			return 80;
		}

		public Integer DB_PED_ENT_RAZAOPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAZAODefault() {

			return null;

		}

		public String DB_PED_ENT_RAZAOComment() {

			return "";

		}

		public String DB_PED_ENT_RAZAOPattern() {

			return "";

		}

		public String DB_PED_ENT_RAZAOOriginalDbColumnName() {

			return "DB_PED_ENT_RAZAO";

		}

		public String DB_PED_ENT_TELEFONE;

		public String getDB_PED_ENT_TELEFONE() {
			return this.DB_PED_ENT_TELEFONE;
		}

		public Boolean DB_PED_ENT_TELEFONEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_TELEFONEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_TELEFONELength() {
			return 15;
		}

		public Integer DB_PED_ENT_TELEFONEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_TELEFONEDefault() {

			return null;

		}

		public String DB_PED_ENT_TELEFONEComment() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEPattern() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEOriginalDbColumnName() {

			return "DB_PED_ENT_TELEFONE";

		}

		public String DB_PED_ENT_RAMAL;

		public String getDB_PED_ENT_RAMAL() {
			return this.DB_PED_ENT_RAMAL;
		}

		public Boolean DB_PED_ENT_RAMALIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAMALIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAMALLength() {
			return 5;
		}

		public Integer DB_PED_ENT_RAMALPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAMALDefault() {

			return null;

		}

		public String DB_PED_ENT_RAMALComment() {

			return "";

		}

		public String DB_PED_ENT_RAMALPattern() {

			return "";

		}

		public String DB_PED_ENT_RAMALOriginalDbColumnName() {

			return "DB_PED_ENT_RAMAL";

		}

		public Float DB_PEDI_QTDE_SOLIC;

		public Float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Float DB_PEDI_PRECO_LIQ;

		public Float getDB_PEDI_PRECO_LIQ() {
			return this.DB_PEDI_PRECO_LIQ;
		}

		public Boolean DB_PEDI_PRECO_LIQIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRECO_LIQIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRECO_LIQLength() {
			return 7;
		}

		public Integer DB_PEDI_PRECO_LIQPrecision() {
			return 0;
		}

		public String DB_PEDI_PRECO_LIQDefault() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQComment() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQPattern() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQOriginalDbColumnName() {

			return "DB_PEDI_PRECO_LIQ";

		}

		public String DB_TBPGTO_ZTERM;

		public String getDB_TBPGTO_ZTERM() {
			return this.DB_TBPGTO_ZTERM;
		}

		public Boolean DB_TBPGTO_ZTERMIsNullable() {
			return true;
		}

		public Boolean DB_TBPGTO_ZTERMIsKey() {
			return false;
		}

		public Integer DB_TBPGTO_ZTERMLength() {
			return 10;
		}

		public Integer DB_TBPGTO_ZTERMPrecision() {
			return 0;
		}

		public String DB_TBPGTO_ZTERMDefault() {

			return null;

		}

		public String DB_TBPGTO_ZTERMComment() {

			return "";

		}

		public String DB_TBPGTO_ZTERMPattern() {

			return "";

		}

		public String DB_TBPGTO_ZTERMOriginalDbColumnName() {

			return "DB_TBPGTO_ZTERM";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Pedido_complementoStruct other = (Pedido_complementoStruct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(Pedido_complementoStruct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PED_DATA_ENVIO = this.DB_PED_DATA_ENVIO;
			other.DB_PED_SITUACAO = this.DB_PED_SITUACAO;
			other.DB_PED_EMPRESA = this.DB_PED_EMPRESA;
			other.DB_PED_CLIENTE = this.DB_PED_CLIENTE;
			other.DB_PEDC_LIBERASEMSALDO = this.DB_PEDC_LIBERASEMSALDO;
			other.DB_PED_COD_TRANSP = this.DB_PED_COD_TRANSP;
			other.DB_PED_COND_PGTO = this.DB_PED_COND_PGTO;
			other.DB_PED_LISTA_PRECO = this.DB_PED_LISTA_PRECO;
			other.DB_PED_REPRES = this.DB_PED_REPRES;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;
			other.DB_PED_TIPO = this.DB_PED_TIPO;
			other.DB_PED_TEXTO = this.DB_PED_TEXTO;
			other.DB_PED_ORD_COMPRA = this.DB_PED_ORD_COMPRA;
			other.DB_PED_OBSERV = this.DB_PED_OBSERV;
			other.DB_PED_DT_PREVENT = this.DB_PED_DT_PREVENT;
			other.DB_PED_TIPO_FRETE = this.DB_PED_TIPO_FRETE;
			other.DB_PED_ENT_ENDER = this.DB_PED_ENT_ENDER;
			other.DB_PED_ENT_BAIRRO = this.DB_PED_ENT_BAIRRO;
			other.DB_PED_ENT_CIDADE = this.DB_PED_ENT_CIDADE;
			other.DB_PED_ENT_CEP = this.DB_PED_ENT_CEP;
			other.DB_PED_ENT_UF = this.DB_PED_ENT_UF;
			other.DB_PED_ENT_CGCMF = this.DB_PED_ENT_CGCMF;
			other.DB_PED_ENT_CGCTE = this.DB_PED_ENT_CGCTE;
			other.DB_PED_ENT_COMPL = this.DB_PED_ENT_COMPL;
			other.DB_PED_ENT_PONTOREFER = this.DB_PED_ENT_PONTOREFER;
			other.DB_PED_ENT_RAZAO = this.DB_PED_ENT_RAZAO;
			other.DB_PED_ENT_TELEFONE = this.DB_PED_ENT_TELEFONE;
			other.DB_PED_ENT_RAMAL = this.DB_PED_ENT_RAMAL;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.DB_PEDI_PRECO_LIQ = this.DB_PEDI_PRECO_LIQ;
			other.DB_TBPGTO_ZTERM = this.DB_TBPGTO_ZTERM;

		}

		public void copyKeysDataTo(Pedido_complementoStruct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					this.DB_PEDC_LIBERASEMSALDO = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_QTDE_SOLIC = null;
					} else {
						this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_PRECO_LIQ = null;
					} else {
						this.DB_PEDI_PRECO_LIQ = dis.readFloat();
					}

					this.DB_TBPGTO_ZTERM = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					this.DB_PEDC_LIBERASEMSALDO = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_QTDE_SOLIC = null;
					} else {
						this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PEDI_PRECO_LIQ = null;
					} else {
						this.DB_PEDI_PRECO_LIQ = dis.readFloat();
					}

					this.DB_TBPGTO_ZTERM = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Integer

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos);

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

				// Float

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				// Float

				if (this.DB_PEDI_PRECO_LIQ == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

				// String

				writeString(this.DB_TBPGTO_ZTERM, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Integer

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos);

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

				// Float

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				// Float

				if (this.DB_PEDI_PRECO_LIQ == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

				// String

				writeString(this.DB_TBPGTO_ZTERM, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DATA_ENVIO=" + String.valueOf(DB_PED_DATA_ENVIO));
			sb.append(",DB_PED_SITUACAO=" + String.valueOf(DB_PED_SITUACAO));
			sb.append(",DB_PED_EMPRESA=" + DB_PED_EMPRESA);
			sb.append(",DB_PED_CLIENTE=" + String.valueOf(DB_PED_CLIENTE));
			sb.append(",DB_PEDC_LIBERASEMSALDO=" + String.valueOf(DB_PEDC_LIBERASEMSALDO));
			sb.append(",DB_PED_COD_TRANSP=" + String.valueOf(DB_PED_COD_TRANSP));
			sb.append(",DB_PED_COND_PGTO=" + String.valueOf(DB_PED_COND_PGTO));
			sb.append(",DB_PED_LISTA_PRECO=" + DB_PED_LISTA_PRECO);
			sb.append(",DB_PED_REPRES=" + String.valueOf(DB_PED_REPRES));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append(",DB_PED_TIPO=" + DB_PED_TIPO);
			sb.append(",DB_PED_TEXTO=" + DB_PED_TEXTO);
			sb.append(",DB_PED_ORD_COMPRA=" + DB_PED_ORD_COMPRA);
			sb.append(",DB_PED_OBSERV=" + DB_PED_OBSERV);
			sb.append(",DB_PED_DT_PREVENT=" + String.valueOf(DB_PED_DT_PREVENT));
			sb.append(",DB_PED_TIPO_FRETE=" + String.valueOf(DB_PED_TIPO_FRETE));
			sb.append(",DB_PED_ENT_ENDER=" + DB_PED_ENT_ENDER);
			sb.append(",DB_PED_ENT_BAIRRO=" + DB_PED_ENT_BAIRRO);
			sb.append(",DB_PED_ENT_CIDADE=" + DB_PED_ENT_CIDADE);
			sb.append(",DB_PED_ENT_CEP=" + DB_PED_ENT_CEP);
			sb.append(",DB_PED_ENT_UF=" + DB_PED_ENT_UF);
			sb.append(",DB_PED_ENT_CGCMF=" + DB_PED_ENT_CGCMF);
			sb.append(",DB_PED_ENT_CGCTE=" + DB_PED_ENT_CGCTE);
			sb.append(",DB_PED_ENT_COMPL=" + DB_PED_ENT_COMPL);
			sb.append(",DB_PED_ENT_PONTOREFER=" + DB_PED_ENT_PONTOREFER);
			sb.append(",DB_PED_ENT_RAZAO=" + DB_PED_ENT_RAZAO);
			sb.append(",DB_PED_ENT_TELEFONE=" + DB_PED_ENT_TELEFONE);
			sb.append(",DB_PED_ENT_RAMAL=" + DB_PED_ENT_RAMAL);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",DB_PEDI_PRECO_LIQ=" + String.valueOf(DB_PEDI_PRECO_LIQ));
			sb.append(",DB_TBPGTO_ZTERM=" + DB_TBPGTO_ZTERM);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DATA_ENVIO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DATA_ENVIO);
			}

			sb.append("|");

			if (DB_PED_SITUACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_SITUACAO);
			}

			sb.append("|");

			if (DB_PED_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_EMPRESA);
			}

			sb.append("|");

			if (DB_PED_CLIENTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_CLIENTE);
			}

			sb.append("|");

			if (DB_PEDC_LIBERASEMSALDO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDC_LIBERASEMSALDO);
			}

			sb.append("|");

			if (DB_PED_COD_TRANSP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COD_TRANSP);
			}

			sb.append("|");

			if (DB_PED_COND_PGTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COND_PGTO);
			}

			sb.append("|");

			if (DB_PED_LISTA_PRECO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_LISTA_PRECO);
			}

			sb.append("|");

			if (DB_PED_REPRES == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_REPRES);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			if (DB_PED_TIPO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO);
			}

			sb.append("|");

			if (DB_PED_TEXTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TEXTO);
			}

			sb.append("|");

			if (DB_PED_ORD_COMPRA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ORD_COMPRA);
			}

			sb.append("|");

			if (DB_PED_OBSERV == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OBSERV);
			}

			sb.append("|");

			if (DB_PED_DT_PREVENT == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_PREVENT);
			}

			sb.append("|");

			if (DB_PED_TIPO_FRETE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO_FRETE);
			}

			sb.append("|");

			if (DB_PED_ENT_ENDER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_ENDER);
			}

			sb.append("|");

			if (DB_PED_ENT_BAIRRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_BAIRRO);
			}

			sb.append("|");

			if (DB_PED_ENT_CIDADE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CIDADE);
			}

			sb.append("|");

			if (DB_PED_ENT_CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CEP);
			}

			sb.append("|");

			if (DB_PED_ENT_UF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_UF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCMF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCMF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCTE);
			}

			sb.append("|");

			if (DB_PED_ENT_COMPL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_COMPL);
			}

			sb.append("|");

			if (DB_PED_ENT_PONTOREFER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_PONTOREFER);
			}

			sb.append("|");

			if (DB_PED_ENT_RAZAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAZAO);
			}

			sb.append("|");

			if (DB_PED_ENT_TELEFONE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_TELEFONE);
			}

			sb.append("|");

			if (DB_PED_ENT_RAMAL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAMAL);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (DB_PEDI_PRECO_LIQ == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRECO_LIQ);
			}

			sb.append("|");

			if (DB_TBPGTO_ZTERM == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_TBPGTO_ZTERM);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Pedido_complementoStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DATA_ENVIO;

		public java.util.Date getDB_PED_DATA_ENVIO() {
			return this.DB_PED_DATA_ENVIO;
		}

		public Boolean DB_PED_DATA_ENVIOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DATA_ENVIOIsKey() {
			return false;
		}

		public Integer DB_PED_DATA_ENVIOLength() {
			return 23;
		}

		public Integer DB_PED_DATA_ENVIOPrecision() {
			return 3;
		}

		public String DB_PED_DATA_ENVIODefault() {

			return null;

		}

		public String DB_PED_DATA_ENVIOComment() {

			return "";

		}

		public String DB_PED_DATA_ENVIOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DATA_ENVIOOriginalDbColumnName() {

			return "DB_PED_DATA_ENVIO";

		}

		public Short DB_PED_SITUACAO;

		public Short getDB_PED_SITUACAO() {
			return this.DB_PED_SITUACAO;
		}

		public Boolean DB_PED_SITUACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_SITUACAOIsKey() {
			return false;
		}

		public Integer DB_PED_SITUACAOLength() {
			return 3;
		}

		public Integer DB_PED_SITUACAOPrecision() {
			return 0;
		}

		public String DB_PED_SITUACAODefault() {

			return "";

		}

		public String DB_PED_SITUACAOComment() {

			return "";

		}

		public String DB_PED_SITUACAOPattern() {

			return "";

		}

		public String DB_PED_SITUACAOOriginalDbColumnName() {

			return "DB_PED_SITUACAO";

		}

		public String DB_PED_EMPRESA;

		public String getDB_PED_EMPRESA() {
			return this.DB_PED_EMPRESA;
		}

		public Boolean DB_PED_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_PED_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_PED_EMPRESALength() {
			return 3;
		}

		public Integer DB_PED_EMPRESAPrecision() {
			return 0;
		}

		public String DB_PED_EMPRESADefault() {

			return null;

		}

		public String DB_PED_EMPRESAComment() {

			return "";

		}

		public String DB_PED_EMPRESAPattern() {

			return "";

		}

		public String DB_PED_EMPRESAOriginalDbColumnName() {

			return "DB_PED_EMPRESA";

		}

		public Double DB_PED_CLIENTE;

		public Double getDB_PED_CLIENTE() {
			return this.DB_PED_CLIENTE;
		}

		public Boolean DB_PED_CLIENTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_CLIENTEIsKey() {
			return false;
		}

		public Integer DB_PED_CLIENTELength() {
			return 15;
		}

		public Integer DB_PED_CLIENTEPrecision() {
			return 0;
		}

		public String DB_PED_CLIENTEDefault() {

			return "";

		}

		public String DB_PED_CLIENTEComment() {

			return "";

		}

		public String DB_PED_CLIENTEPattern() {

			return "";

		}

		public String DB_PED_CLIENTEOriginalDbColumnName() {

			return "DB_PED_CLIENTE";

		}

		public Double DB_PED_COD_TRANSP;

		public Double getDB_PED_COD_TRANSP() {
			return this.DB_PED_COD_TRANSP;
		}

		public Boolean DB_PED_COD_TRANSPIsNullable() {
			return true;
		}

		public Boolean DB_PED_COD_TRANSPIsKey() {
			return false;
		}

		public Integer DB_PED_COD_TRANSPLength() {
			return 15;
		}

		public Integer DB_PED_COD_TRANSPPrecision() {
			return 0;
		}

		public String DB_PED_COD_TRANSPDefault() {

			return "";

		}

		public String DB_PED_COD_TRANSPComment() {

			return "";

		}

		public String DB_PED_COD_TRANSPPattern() {

			return "";

		}

		public String DB_PED_COD_TRANSPOriginalDbColumnName() {

			return "DB_PED_COD_TRANSP";

		}

		public Integer DB_PED_COND_PGTO;

		public Integer getDB_PED_COND_PGTO() {
			return this.DB_PED_COND_PGTO;
		}

		public Boolean DB_PED_COND_PGTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_COND_PGTOIsKey() {
			return false;
		}

		public Integer DB_PED_COND_PGTOLength() {
			return 10;
		}

		public Integer DB_PED_COND_PGTOPrecision() {
			return 0;
		}

		public String DB_PED_COND_PGTODefault() {

			return "";

		}

		public String DB_PED_COND_PGTOComment() {

			return "";

		}

		public String DB_PED_COND_PGTOPattern() {

			return "";

		}

		public String DB_PED_COND_PGTOOriginalDbColumnName() {

			return "DB_PED_COND_PGTO";

		}

		public String DB_PED_LISTA_PRECO;

		public String getDB_PED_LISTA_PRECO() {
			return this.DB_PED_LISTA_PRECO;
		}

		public Boolean DB_PED_LISTA_PRECOIsNullable() {
			return true;
		}

		public Boolean DB_PED_LISTA_PRECOIsKey() {
			return false;
		}

		public Integer DB_PED_LISTA_PRECOLength() {
			return 8;
		}

		public Integer DB_PED_LISTA_PRECOPrecision() {
			return 0;
		}

		public String DB_PED_LISTA_PRECODefault() {

			return null;

		}

		public String DB_PED_LISTA_PRECOComment() {

			return "";

		}

		public String DB_PED_LISTA_PRECOPattern() {

			return "";

		}

		public String DB_PED_LISTA_PRECOOriginalDbColumnName() {

			return "DB_PED_LISTA_PRECO";

		}

		public Integer DB_PED_REPRES;

		public Integer getDB_PED_REPRES() {
			return this.DB_PED_REPRES;
		}

		public Boolean DB_PED_REPRESIsNullable() {
			return true;
		}

		public Boolean DB_PED_REPRESIsKey() {
			return false;
		}

		public Integer DB_PED_REPRESLength() {
			return 10;
		}

		public Integer DB_PED_REPRESPrecision() {
			return 0;
		}

		public String DB_PED_REPRESDefault() {

			return "";

		}

		public String DB_PED_REPRESComment() {

			return "";

		}

		public String DB_PED_REPRESPattern() {

			return "";

		}

		public String DB_PED_REPRESOriginalDbColumnName() {

			return "DB_PED_REPRES";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		public String DB_PED_TIPO;

		public String getDB_PED_TIPO() {
			return this.DB_PED_TIPO;
		}

		public Boolean DB_PED_TIPOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPOIsKey() {
			return false;
		}

		public Integer DB_PED_TIPOLength() {
			return 3;
		}

		public Integer DB_PED_TIPOPrecision() {
			return 0;
		}

		public String DB_PED_TIPODefault() {

			return null;

		}

		public String DB_PED_TIPOComment() {

			return "";

		}

		public String DB_PED_TIPOPattern() {

			return "";

		}

		public String DB_PED_TIPOOriginalDbColumnName() {

			return "DB_PED_TIPO";

		}

		public String DB_PED_TEXTO;

		public String getDB_PED_TEXTO() {
			return this.DB_PED_TEXTO;
		}

		public Boolean DB_PED_TEXTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TEXTOIsKey() {
			return false;
		}

		public Integer DB_PED_TEXTOLength() {
			return 255;
		}

		public Integer DB_PED_TEXTOPrecision() {
			return 0;
		}

		public String DB_PED_TEXTODefault() {

			return null;

		}

		public String DB_PED_TEXTOComment() {

			return "";

		}

		public String DB_PED_TEXTOPattern() {

			return "";

		}

		public String DB_PED_TEXTOOriginalDbColumnName() {

			return "DB_PED_TEXTO";

		}

		public String DB_PED_ORD_COMPRA;

		public String getDB_PED_ORD_COMPRA() {
			return this.DB_PED_ORD_COMPRA;
		}

		public Boolean DB_PED_ORD_COMPRAIsNullable() {
			return true;
		}

		public Boolean DB_PED_ORD_COMPRAIsKey() {
			return false;
		}

		public Integer DB_PED_ORD_COMPRALength() {
			return 25;
		}

		public Integer DB_PED_ORD_COMPRAPrecision() {
			return 0;
		}

		public String DB_PED_ORD_COMPRADefault() {

			return null;

		}

		public String DB_PED_ORD_COMPRAComment() {

			return "";

		}

		public String DB_PED_ORD_COMPRAPattern() {

			return "";

		}

		public String DB_PED_ORD_COMPRAOriginalDbColumnName() {

			return "DB_PED_ORD_COMPRA";

		}

		public String DB_PED_OBSERV;

		public String getDB_PED_OBSERV() {
			return this.DB_PED_OBSERV;
		}

		public Boolean DB_PED_OBSERVIsNullable() {
			return true;
		}

		public Boolean DB_PED_OBSERVIsKey() {
			return false;
		}

		public Integer DB_PED_OBSERVLength() {
			return 255;
		}

		public Integer DB_PED_OBSERVPrecision() {
			return 0;
		}

		public String DB_PED_OBSERVDefault() {

			return null;

		}

		public String DB_PED_OBSERVComment() {

			return "";

		}

		public String DB_PED_OBSERVPattern() {

			return "";

		}

		public String DB_PED_OBSERVOriginalDbColumnName() {

			return "DB_PED_OBSERV";

		}

		public java.util.Date DB_PED_DT_PREVENT;

		public java.util.Date getDB_PED_DT_PREVENT() {
			return this.DB_PED_DT_PREVENT;
		}

		public Boolean DB_PED_DT_PREVENTIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_PREVENTIsKey() {
			return false;
		}

		public Integer DB_PED_DT_PREVENTLength() {
			return 23;
		}

		public Integer DB_PED_DT_PREVENTPrecision() {
			return 3;
		}

		public String DB_PED_DT_PREVENTDefault() {

			return null;

		}

		public String DB_PED_DT_PREVENTComment() {

			return "";

		}

		public String DB_PED_DT_PREVENTPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_PREVENTOriginalDbColumnName() {

			return "DB_PED_DT_PREVENT";

		}

		public Short DB_PED_TIPO_FRETE;

		public Short getDB_PED_TIPO_FRETE() {
			return this.DB_PED_TIPO_FRETE;
		}

		public Boolean DB_PED_TIPO_FRETEIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPO_FRETEIsKey() {
			return false;
		}

		public Integer DB_PED_TIPO_FRETELength() {
			return 5;
		}

		public Integer DB_PED_TIPO_FRETEPrecision() {
			return 0;
		}

		public String DB_PED_TIPO_FRETEDefault() {

			return "";

		}

		public String DB_PED_TIPO_FRETEComment() {

			return "";

		}

		public String DB_PED_TIPO_FRETEPattern() {

			return "";

		}

		public String DB_PED_TIPO_FRETEOriginalDbColumnName() {

			return "DB_PED_TIPO_FRETE";

		}

		public String DB_PED_ENT_ENDER;

		public String getDB_PED_ENT_ENDER() {
			return this.DB_PED_ENT_ENDER;
		}

		public Boolean DB_PED_ENT_ENDERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_ENDERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_ENDERLength() {
			return 50;
		}

		public Integer DB_PED_ENT_ENDERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_ENDERDefault() {

			return null;

		}

		public String DB_PED_ENT_ENDERComment() {

			return "";

		}

		public String DB_PED_ENT_ENDERPattern() {

			return "";

		}

		public String DB_PED_ENT_ENDEROriginalDbColumnName() {

			return "DB_PED_ENT_ENDER";

		}

		public String DB_PED_ENT_BAIRRO;

		public String getDB_PED_ENT_BAIRRO() {
			return this.DB_PED_ENT_BAIRRO;
		}

		public Boolean DB_PED_ENT_BAIRROIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_BAIRROIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_BAIRROLength() {
			return 40;
		}

		public Integer DB_PED_ENT_BAIRROPrecision() {
			return 0;
		}

		public String DB_PED_ENT_BAIRRODefault() {

			return null;

		}

		public String DB_PED_ENT_BAIRROComment() {

			return "";

		}

		public String DB_PED_ENT_BAIRROPattern() {

			return "";

		}

		public String DB_PED_ENT_BAIRROOriginalDbColumnName() {

			return "DB_PED_ENT_BAIRRO";

		}

		public String DB_PED_ENT_CIDADE;

		public String getDB_PED_ENT_CIDADE() {
			return this.DB_PED_ENT_CIDADE;
		}

		public Boolean DB_PED_ENT_CIDADEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CIDADEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CIDADELength() {
			return 30;
		}

		public Integer DB_PED_ENT_CIDADEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CIDADEDefault() {

			return null;

		}

		public String DB_PED_ENT_CIDADEComment() {

			return "";

		}

		public String DB_PED_ENT_CIDADEPattern() {

			return "";

		}

		public String DB_PED_ENT_CIDADEOriginalDbColumnName() {

			return "DB_PED_ENT_CIDADE";

		}

		public String DB_PED_ENT_CEP;

		public String getDB_PED_ENT_CEP() {
			return this.DB_PED_ENT_CEP;
		}

		public Boolean DB_PED_ENT_CEPIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CEPIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CEPLength() {
			return 9;
		}

		public Integer DB_PED_ENT_CEPPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CEPDefault() {

			return null;

		}

		public String DB_PED_ENT_CEPComment() {

			return "";

		}

		public String DB_PED_ENT_CEPPattern() {

			return "";

		}

		public String DB_PED_ENT_CEPOriginalDbColumnName() {

			return "DB_PED_ENT_CEP";

		}

		public String DB_PED_ENT_UF;

		public String getDB_PED_ENT_UF() {
			return this.DB_PED_ENT_UF;
		}

		public Boolean DB_PED_ENT_UFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_UFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_UFLength() {
			return 8;
		}

		public Integer DB_PED_ENT_UFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_UFDefault() {

			return null;

		}

		public String DB_PED_ENT_UFComment() {

			return "";

		}

		public String DB_PED_ENT_UFPattern() {

			return "";

		}

		public String DB_PED_ENT_UFOriginalDbColumnName() {

			return "DB_PED_ENT_UF";

		}

		public String DB_PED_ENT_CGCMF;

		public String getDB_PED_ENT_CGCMF() {
			return this.DB_PED_ENT_CGCMF;
		}

		public Boolean DB_PED_ENT_CGCMFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCMFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCMFLength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCMFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCMFDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCMFComment() {

			return "";

		}

		public String DB_PED_ENT_CGCMFPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCMFOriginalDbColumnName() {

			return "DB_PED_ENT_CGCMF";

		}

		public String DB_PED_ENT_CGCTE;

		public String getDB_PED_ENT_CGCTE() {
			return this.DB_PED_ENT_CGCTE;
		}

		public Boolean DB_PED_ENT_CGCTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCTEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCTELength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCTEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCTEDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCTEComment() {

			return "";

		}

		public String DB_PED_ENT_CGCTEPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCTEOriginalDbColumnName() {

			return "DB_PED_ENT_CGCTE";

		}

		public String DB_PED_ENT_COMPL;

		public String getDB_PED_ENT_COMPL() {
			return this.DB_PED_ENT_COMPL;
		}

		public Boolean DB_PED_ENT_COMPLIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_COMPLIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_COMPLLength() {
			return 50;
		}

		public Integer DB_PED_ENT_COMPLPrecision() {
			return 0;
		}

		public String DB_PED_ENT_COMPLDefault() {

			return null;

		}

		public String DB_PED_ENT_COMPLComment() {

			return "";

		}

		public String DB_PED_ENT_COMPLPattern() {

			return "";

		}

		public String DB_PED_ENT_COMPLOriginalDbColumnName() {

			return "DB_PED_ENT_COMPL";

		}

		public String DB_PED_ENT_PONTOREFER;

		public String getDB_PED_ENT_PONTOREFER() {
			return this.DB_PED_ENT_PONTOREFER;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_PONTOREFERLength() {
			return 40;
		}

		public Integer DB_PED_ENT_PONTOREFERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_PONTOREFERDefault() {

			return null;

		}

		public String DB_PED_ENT_PONTOREFERComment() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFERPattern() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFEROriginalDbColumnName() {

			return "DB_PED_ENT_PONTOREFER";

		}

		public String DB_PED_ENT_RAZAO;

		public String getDB_PED_ENT_RAZAO() {
			return this.DB_PED_ENT_RAZAO;
		}

		public Boolean DB_PED_ENT_RAZAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAZAOIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAZAOLength() {
			return 80;
		}

		public Integer DB_PED_ENT_RAZAOPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAZAODefault() {

			return null;

		}

		public String DB_PED_ENT_RAZAOComment() {

			return "";

		}

		public String DB_PED_ENT_RAZAOPattern() {

			return "";

		}

		public String DB_PED_ENT_RAZAOOriginalDbColumnName() {

			return "DB_PED_ENT_RAZAO";

		}

		public String DB_PED_ENT_TELEFONE;

		public String getDB_PED_ENT_TELEFONE() {
			return this.DB_PED_ENT_TELEFONE;
		}

		public Boolean DB_PED_ENT_TELEFONEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_TELEFONEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_TELEFONELength() {
			return 15;
		}

		public Integer DB_PED_ENT_TELEFONEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_TELEFONEDefault() {

			return null;

		}

		public String DB_PED_ENT_TELEFONEComment() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEPattern() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEOriginalDbColumnName() {

			return "DB_PED_ENT_TELEFONE";

		}

		public String DB_PED_ENT_RAMAL;

		public String getDB_PED_ENT_RAMAL() {
			return this.DB_PED_ENT_RAMAL;
		}

		public Boolean DB_PED_ENT_RAMALIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAMALIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAMALLength() {
			return 5;
		}

		public Integer DB_PED_ENT_RAMALPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAMALDefault() {

			return null;

		}

		public String DB_PED_ENT_RAMALComment() {

			return "";

		}

		public String DB_PED_ENT_RAMALPattern() {

			return "";

		}

		public String DB_PED_ENT_RAMALOriginalDbColumnName() {

			return "DB_PED_ENT_RAMAL";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DATA_ENVIO=" + String.valueOf(DB_PED_DATA_ENVIO));
			sb.append(",DB_PED_SITUACAO=" + String.valueOf(DB_PED_SITUACAO));
			sb.append(",DB_PED_EMPRESA=" + DB_PED_EMPRESA);
			sb.append(",DB_PED_CLIENTE=" + String.valueOf(DB_PED_CLIENTE));
			sb.append(",DB_PED_COD_TRANSP=" + String.valueOf(DB_PED_COD_TRANSP));
			sb.append(",DB_PED_COND_PGTO=" + String.valueOf(DB_PED_COND_PGTO));
			sb.append(",DB_PED_LISTA_PRECO=" + DB_PED_LISTA_PRECO);
			sb.append(",DB_PED_REPRES=" + String.valueOf(DB_PED_REPRES));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append(",DB_PED_TIPO=" + DB_PED_TIPO);
			sb.append(",DB_PED_TEXTO=" + DB_PED_TEXTO);
			sb.append(",DB_PED_ORD_COMPRA=" + DB_PED_ORD_COMPRA);
			sb.append(",DB_PED_OBSERV=" + DB_PED_OBSERV);
			sb.append(",DB_PED_DT_PREVENT=" + String.valueOf(DB_PED_DT_PREVENT));
			sb.append(",DB_PED_TIPO_FRETE=" + String.valueOf(DB_PED_TIPO_FRETE));
			sb.append(",DB_PED_ENT_ENDER=" + DB_PED_ENT_ENDER);
			sb.append(",DB_PED_ENT_BAIRRO=" + DB_PED_ENT_BAIRRO);
			sb.append(",DB_PED_ENT_CIDADE=" + DB_PED_ENT_CIDADE);
			sb.append(",DB_PED_ENT_CEP=" + DB_PED_ENT_CEP);
			sb.append(",DB_PED_ENT_UF=" + DB_PED_ENT_UF);
			sb.append(",DB_PED_ENT_CGCMF=" + DB_PED_ENT_CGCMF);
			sb.append(",DB_PED_ENT_CGCTE=" + DB_PED_ENT_CGCTE);
			sb.append(",DB_PED_ENT_COMPL=" + DB_PED_ENT_COMPL);
			sb.append(",DB_PED_ENT_PONTOREFER=" + DB_PED_ENT_PONTOREFER);
			sb.append(",DB_PED_ENT_RAZAO=" + DB_PED_ENT_RAZAO);
			sb.append(",DB_PED_ENT_TELEFONE=" + DB_PED_ENT_TELEFONE);
			sb.append(",DB_PED_ENT_RAMAL=" + DB_PED_ENT_RAMAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DATA_ENVIO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DATA_ENVIO);
			}

			sb.append("|");

			if (DB_PED_SITUACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_SITUACAO);
			}

			sb.append("|");

			if (DB_PED_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_EMPRESA);
			}

			sb.append("|");

			if (DB_PED_CLIENTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_CLIENTE);
			}

			sb.append("|");

			if (DB_PED_COD_TRANSP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COD_TRANSP);
			}

			sb.append("|");

			if (DB_PED_COND_PGTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COND_PGTO);
			}

			sb.append("|");

			if (DB_PED_LISTA_PRECO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_LISTA_PRECO);
			}

			sb.append("|");

			if (DB_PED_REPRES == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_REPRES);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			if (DB_PED_TIPO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO);
			}

			sb.append("|");

			if (DB_PED_TEXTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TEXTO);
			}

			sb.append("|");

			if (DB_PED_ORD_COMPRA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ORD_COMPRA);
			}

			sb.append("|");

			if (DB_PED_OBSERV == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OBSERV);
			}

			sb.append("|");

			if (DB_PED_DT_PREVENT == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_PREVENT);
			}

			sb.append("|");

			if (DB_PED_TIPO_FRETE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO_FRETE);
			}

			sb.append("|");

			if (DB_PED_ENT_ENDER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_ENDER);
			}

			sb.append("|");

			if (DB_PED_ENT_BAIRRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_BAIRRO);
			}

			sb.append("|");

			if (DB_PED_ENT_CIDADE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CIDADE);
			}

			sb.append("|");

			if (DB_PED_ENT_CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CEP);
			}

			sb.append("|");

			if (DB_PED_ENT_UF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_UF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCMF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCMF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCTE);
			}

			sb.append("|");

			if (DB_PED_ENT_COMPL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_COMPL);
			}

			sb.append("|");

			if (DB_PED_ENT_PONTOREFER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_PONTOREFER);
			}

			sb.append("|");

			if (DB_PED_ENT_RAZAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAZAO);
			}

			sb.append("|");

			if (DB_PED_ENT_TELEFONE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_TELEFONE);
			}

			sb.append("|");

			if (DB_PED_ENT_RAMAL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAMAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DATA_ENVIO;

		public java.util.Date getDB_PED_DATA_ENVIO() {
			return this.DB_PED_DATA_ENVIO;
		}

		public Boolean DB_PED_DATA_ENVIOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DATA_ENVIOIsKey() {
			return false;
		}

		public Integer DB_PED_DATA_ENVIOLength() {
			return 23;
		}

		public Integer DB_PED_DATA_ENVIOPrecision() {
			return 3;
		}

		public String DB_PED_DATA_ENVIODefault() {

			return null;

		}

		public String DB_PED_DATA_ENVIOComment() {

			return "";

		}

		public String DB_PED_DATA_ENVIOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DATA_ENVIOOriginalDbColumnName() {

			return "DB_PED_DATA_ENVIO";

		}

		public Short DB_PED_SITUACAO;

		public Short getDB_PED_SITUACAO() {
			return this.DB_PED_SITUACAO;
		}

		public Boolean DB_PED_SITUACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_SITUACAOIsKey() {
			return false;
		}

		public Integer DB_PED_SITUACAOLength() {
			return 3;
		}

		public Integer DB_PED_SITUACAOPrecision() {
			return 0;
		}

		public String DB_PED_SITUACAODefault() {

			return "";

		}

		public String DB_PED_SITUACAOComment() {

			return "";

		}

		public String DB_PED_SITUACAOPattern() {

			return "";

		}

		public String DB_PED_SITUACAOOriginalDbColumnName() {

			return "DB_PED_SITUACAO";

		}

		public String DB_PED_EMPRESA;

		public String getDB_PED_EMPRESA() {
			return this.DB_PED_EMPRESA;
		}

		public Boolean DB_PED_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_PED_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_PED_EMPRESALength() {
			return 3;
		}

		public Integer DB_PED_EMPRESAPrecision() {
			return 0;
		}

		public String DB_PED_EMPRESADefault() {

			return null;

		}

		public String DB_PED_EMPRESAComment() {

			return "";

		}

		public String DB_PED_EMPRESAPattern() {

			return "";

		}

		public String DB_PED_EMPRESAOriginalDbColumnName() {

			return "DB_PED_EMPRESA";

		}

		public Double DB_PED_CLIENTE;

		public Double getDB_PED_CLIENTE() {
			return this.DB_PED_CLIENTE;
		}

		public Boolean DB_PED_CLIENTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_CLIENTEIsKey() {
			return false;
		}

		public Integer DB_PED_CLIENTELength() {
			return 15;
		}

		public Integer DB_PED_CLIENTEPrecision() {
			return 0;
		}

		public String DB_PED_CLIENTEDefault() {

			return "";

		}

		public String DB_PED_CLIENTEComment() {

			return "";

		}

		public String DB_PED_CLIENTEPattern() {

			return "";

		}

		public String DB_PED_CLIENTEOriginalDbColumnName() {

			return "DB_PED_CLIENTE";

		}

		public Double DB_PED_COD_TRANSP;

		public Double getDB_PED_COD_TRANSP() {
			return this.DB_PED_COD_TRANSP;
		}

		public Boolean DB_PED_COD_TRANSPIsNullable() {
			return true;
		}

		public Boolean DB_PED_COD_TRANSPIsKey() {
			return false;
		}

		public Integer DB_PED_COD_TRANSPLength() {
			return 15;
		}

		public Integer DB_PED_COD_TRANSPPrecision() {
			return 0;
		}

		public String DB_PED_COD_TRANSPDefault() {

			return "";

		}

		public String DB_PED_COD_TRANSPComment() {

			return "";

		}

		public String DB_PED_COD_TRANSPPattern() {

			return "";

		}

		public String DB_PED_COD_TRANSPOriginalDbColumnName() {

			return "DB_PED_COD_TRANSP";

		}

		public Integer DB_PED_COND_PGTO;

		public Integer getDB_PED_COND_PGTO() {
			return this.DB_PED_COND_PGTO;
		}

		public Boolean DB_PED_COND_PGTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_COND_PGTOIsKey() {
			return false;
		}

		public Integer DB_PED_COND_PGTOLength() {
			return 10;
		}

		public Integer DB_PED_COND_PGTOPrecision() {
			return 0;
		}

		public String DB_PED_COND_PGTODefault() {

			return "";

		}

		public String DB_PED_COND_PGTOComment() {

			return "";

		}

		public String DB_PED_COND_PGTOPattern() {

			return "";

		}

		public String DB_PED_COND_PGTOOriginalDbColumnName() {

			return "DB_PED_COND_PGTO";

		}

		public String DB_PED_LISTA_PRECO;

		public String getDB_PED_LISTA_PRECO() {
			return this.DB_PED_LISTA_PRECO;
		}

		public Boolean DB_PED_LISTA_PRECOIsNullable() {
			return true;
		}

		public Boolean DB_PED_LISTA_PRECOIsKey() {
			return false;
		}

		public Integer DB_PED_LISTA_PRECOLength() {
			return 8;
		}

		public Integer DB_PED_LISTA_PRECOPrecision() {
			return 0;
		}

		public String DB_PED_LISTA_PRECODefault() {

			return null;

		}

		public String DB_PED_LISTA_PRECOComment() {

			return "";

		}

		public String DB_PED_LISTA_PRECOPattern() {

			return "";

		}

		public String DB_PED_LISTA_PRECOOriginalDbColumnName() {

			return "DB_PED_LISTA_PRECO";

		}

		public Integer DB_PED_REPRES;

		public Integer getDB_PED_REPRES() {
			return this.DB_PED_REPRES;
		}

		public Boolean DB_PED_REPRESIsNullable() {
			return true;
		}

		public Boolean DB_PED_REPRESIsKey() {
			return false;
		}

		public Integer DB_PED_REPRESLength() {
			return 10;
		}

		public Integer DB_PED_REPRESPrecision() {
			return 0;
		}

		public String DB_PED_REPRESDefault() {

			return "";

		}

		public String DB_PED_REPRESComment() {

			return "";

		}

		public String DB_PED_REPRESPattern() {

			return "";

		}

		public String DB_PED_REPRESOriginalDbColumnName() {

			return "DB_PED_REPRES";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		public String DB_PED_TIPO;

		public String getDB_PED_TIPO() {
			return this.DB_PED_TIPO;
		}

		public Boolean DB_PED_TIPOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPOIsKey() {
			return false;
		}

		public Integer DB_PED_TIPOLength() {
			return 3;
		}

		public Integer DB_PED_TIPOPrecision() {
			return 0;
		}

		public String DB_PED_TIPODefault() {

			return null;

		}

		public String DB_PED_TIPOComment() {

			return "";

		}

		public String DB_PED_TIPOPattern() {

			return "";

		}

		public String DB_PED_TIPOOriginalDbColumnName() {

			return "DB_PED_TIPO";

		}

		public String DB_PED_TEXTO;

		public String getDB_PED_TEXTO() {
			return this.DB_PED_TEXTO;
		}

		public Boolean DB_PED_TEXTOIsNullable() {
			return true;
		}

		public Boolean DB_PED_TEXTOIsKey() {
			return false;
		}

		public Integer DB_PED_TEXTOLength() {
			return 255;
		}

		public Integer DB_PED_TEXTOPrecision() {
			return 0;
		}

		public String DB_PED_TEXTODefault() {

			return null;

		}

		public String DB_PED_TEXTOComment() {

			return "";

		}

		public String DB_PED_TEXTOPattern() {

			return "";

		}

		public String DB_PED_TEXTOOriginalDbColumnName() {

			return "DB_PED_TEXTO";

		}

		public String DB_PED_ORD_COMPRA;

		public String getDB_PED_ORD_COMPRA() {
			return this.DB_PED_ORD_COMPRA;
		}

		public Boolean DB_PED_ORD_COMPRAIsNullable() {
			return true;
		}

		public Boolean DB_PED_ORD_COMPRAIsKey() {
			return false;
		}

		public Integer DB_PED_ORD_COMPRALength() {
			return 25;
		}

		public Integer DB_PED_ORD_COMPRAPrecision() {
			return 0;
		}

		public String DB_PED_ORD_COMPRADefault() {

			return null;

		}

		public String DB_PED_ORD_COMPRAComment() {

			return "";

		}

		public String DB_PED_ORD_COMPRAPattern() {

			return "";

		}

		public String DB_PED_ORD_COMPRAOriginalDbColumnName() {

			return "DB_PED_ORD_COMPRA";

		}

		public String DB_PED_OBSERV;

		public String getDB_PED_OBSERV() {
			return this.DB_PED_OBSERV;
		}

		public Boolean DB_PED_OBSERVIsNullable() {
			return true;
		}

		public Boolean DB_PED_OBSERVIsKey() {
			return false;
		}

		public Integer DB_PED_OBSERVLength() {
			return 255;
		}

		public Integer DB_PED_OBSERVPrecision() {
			return 0;
		}

		public String DB_PED_OBSERVDefault() {

			return null;

		}

		public String DB_PED_OBSERVComment() {

			return "";

		}

		public String DB_PED_OBSERVPattern() {

			return "";

		}

		public String DB_PED_OBSERVOriginalDbColumnName() {

			return "DB_PED_OBSERV";

		}

		public java.util.Date DB_PED_DT_PREVENT;

		public java.util.Date getDB_PED_DT_PREVENT() {
			return this.DB_PED_DT_PREVENT;
		}

		public Boolean DB_PED_DT_PREVENTIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_PREVENTIsKey() {
			return false;
		}

		public Integer DB_PED_DT_PREVENTLength() {
			return 23;
		}

		public Integer DB_PED_DT_PREVENTPrecision() {
			return 3;
		}

		public String DB_PED_DT_PREVENTDefault() {

			return null;

		}

		public String DB_PED_DT_PREVENTComment() {

			return "";

		}

		public String DB_PED_DT_PREVENTPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_PREVENTOriginalDbColumnName() {

			return "DB_PED_DT_PREVENT";

		}

		public Short DB_PED_TIPO_FRETE;

		public Short getDB_PED_TIPO_FRETE() {
			return this.DB_PED_TIPO_FRETE;
		}

		public Boolean DB_PED_TIPO_FRETEIsNullable() {
			return true;
		}

		public Boolean DB_PED_TIPO_FRETEIsKey() {
			return false;
		}

		public Integer DB_PED_TIPO_FRETELength() {
			return 5;
		}

		public Integer DB_PED_TIPO_FRETEPrecision() {
			return 0;
		}

		public String DB_PED_TIPO_FRETEDefault() {

			return "";

		}

		public String DB_PED_TIPO_FRETEComment() {

			return "";

		}

		public String DB_PED_TIPO_FRETEPattern() {

			return "";

		}

		public String DB_PED_TIPO_FRETEOriginalDbColumnName() {

			return "DB_PED_TIPO_FRETE";

		}

		public String DB_PED_ENT_ENDER;

		public String getDB_PED_ENT_ENDER() {
			return this.DB_PED_ENT_ENDER;
		}

		public Boolean DB_PED_ENT_ENDERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_ENDERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_ENDERLength() {
			return 50;
		}

		public Integer DB_PED_ENT_ENDERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_ENDERDefault() {

			return null;

		}

		public String DB_PED_ENT_ENDERComment() {

			return "";

		}

		public String DB_PED_ENT_ENDERPattern() {

			return "";

		}

		public String DB_PED_ENT_ENDEROriginalDbColumnName() {

			return "DB_PED_ENT_ENDER";

		}

		public String DB_PED_ENT_BAIRRO;

		public String getDB_PED_ENT_BAIRRO() {
			return this.DB_PED_ENT_BAIRRO;
		}

		public Boolean DB_PED_ENT_BAIRROIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_BAIRROIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_BAIRROLength() {
			return 40;
		}

		public Integer DB_PED_ENT_BAIRROPrecision() {
			return 0;
		}

		public String DB_PED_ENT_BAIRRODefault() {

			return null;

		}

		public String DB_PED_ENT_BAIRROComment() {

			return "";

		}

		public String DB_PED_ENT_BAIRROPattern() {

			return "";

		}

		public String DB_PED_ENT_BAIRROOriginalDbColumnName() {

			return "DB_PED_ENT_BAIRRO";

		}

		public String DB_PED_ENT_CIDADE;

		public String getDB_PED_ENT_CIDADE() {
			return this.DB_PED_ENT_CIDADE;
		}

		public Boolean DB_PED_ENT_CIDADEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CIDADEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CIDADELength() {
			return 30;
		}

		public Integer DB_PED_ENT_CIDADEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CIDADEDefault() {

			return null;

		}

		public String DB_PED_ENT_CIDADEComment() {

			return "";

		}

		public String DB_PED_ENT_CIDADEPattern() {

			return "";

		}

		public String DB_PED_ENT_CIDADEOriginalDbColumnName() {

			return "DB_PED_ENT_CIDADE";

		}

		public String DB_PED_ENT_CEP;

		public String getDB_PED_ENT_CEP() {
			return this.DB_PED_ENT_CEP;
		}

		public Boolean DB_PED_ENT_CEPIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CEPIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CEPLength() {
			return 9;
		}

		public Integer DB_PED_ENT_CEPPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CEPDefault() {

			return null;

		}

		public String DB_PED_ENT_CEPComment() {

			return "";

		}

		public String DB_PED_ENT_CEPPattern() {

			return "";

		}

		public String DB_PED_ENT_CEPOriginalDbColumnName() {

			return "DB_PED_ENT_CEP";

		}

		public String DB_PED_ENT_UF;

		public String getDB_PED_ENT_UF() {
			return this.DB_PED_ENT_UF;
		}

		public Boolean DB_PED_ENT_UFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_UFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_UFLength() {
			return 8;
		}

		public Integer DB_PED_ENT_UFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_UFDefault() {

			return null;

		}

		public String DB_PED_ENT_UFComment() {

			return "";

		}

		public String DB_PED_ENT_UFPattern() {

			return "";

		}

		public String DB_PED_ENT_UFOriginalDbColumnName() {

			return "DB_PED_ENT_UF";

		}

		public String DB_PED_ENT_CGCMF;

		public String getDB_PED_ENT_CGCMF() {
			return this.DB_PED_ENT_CGCMF;
		}

		public Boolean DB_PED_ENT_CGCMFIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCMFIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCMFLength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCMFPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCMFDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCMFComment() {

			return "";

		}

		public String DB_PED_ENT_CGCMFPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCMFOriginalDbColumnName() {

			return "DB_PED_ENT_CGCMF";

		}

		public String DB_PED_ENT_CGCTE;

		public String getDB_PED_ENT_CGCTE() {
			return this.DB_PED_ENT_CGCTE;
		}

		public Boolean DB_PED_ENT_CGCTEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_CGCTEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_CGCTELength() {
			return 19;
		}

		public Integer DB_PED_ENT_CGCTEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_CGCTEDefault() {

			return null;

		}

		public String DB_PED_ENT_CGCTEComment() {

			return "";

		}

		public String DB_PED_ENT_CGCTEPattern() {

			return "";

		}

		public String DB_PED_ENT_CGCTEOriginalDbColumnName() {

			return "DB_PED_ENT_CGCTE";

		}

		public String DB_PED_ENT_COMPL;

		public String getDB_PED_ENT_COMPL() {
			return this.DB_PED_ENT_COMPL;
		}

		public Boolean DB_PED_ENT_COMPLIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_COMPLIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_COMPLLength() {
			return 50;
		}

		public Integer DB_PED_ENT_COMPLPrecision() {
			return 0;
		}

		public String DB_PED_ENT_COMPLDefault() {

			return null;

		}

		public String DB_PED_ENT_COMPLComment() {

			return "";

		}

		public String DB_PED_ENT_COMPLPattern() {

			return "";

		}

		public String DB_PED_ENT_COMPLOriginalDbColumnName() {

			return "DB_PED_ENT_COMPL";

		}

		public String DB_PED_ENT_PONTOREFER;

		public String getDB_PED_ENT_PONTOREFER() {
			return this.DB_PED_ENT_PONTOREFER;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_PONTOREFERIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_PONTOREFERLength() {
			return 40;
		}

		public Integer DB_PED_ENT_PONTOREFERPrecision() {
			return 0;
		}

		public String DB_PED_ENT_PONTOREFERDefault() {

			return null;

		}

		public String DB_PED_ENT_PONTOREFERComment() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFERPattern() {

			return "";

		}

		public String DB_PED_ENT_PONTOREFEROriginalDbColumnName() {

			return "DB_PED_ENT_PONTOREFER";

		}

		public String DB_PED_ENT_RAZAO;

		public String getDB_PED_ENT_RAZAO() {
			return this.DB_PED_ENT_RAZAO;
		}

		public Boolean DB_PED_ENT_RAZAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAZAOIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAZAOLength() {
			return 80;
		}

		public Integer DB_PED_ENT_RAZAOPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAZAODefault() {

			return null;

		}

		public String DB_PED_ENT_RAZAOComment() {

			return "";

		}

		public String DB_PED_ENT_RAZAOPattern() {

			return "";

		}

		public String DB_PED_ENT_RAZAOOriginalDbColumnName() {

			return "DB_PED_ENT_RAZAO";

		}

		public String DB_PED_ENT_TELEFONE;

		public String getDB_PED_ENT_TELEFONE() {
			return this.DB_PED_ENT_TELEFONE;
		}

		public Boolean DB_PED_ENT_TELEFONEIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_TELEFONEIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_TELEFONELength() {
			return 15;
		}

		public Integer DB_PED_ENT_TELEFONEPrecision() {
			return 0;
		}

		public String DB_PED_ENT_TELEFONEDefault() {

			return null;

		}

		public String DB_PED_ENT_TELEFONEComment() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEPattern() {

			return "";

		}

		public String DB_PED_ENT_TELEFONEOriginalDbColumnName() {

			return "DB_PED_ENT_TELEFONE";

		}

		public String DB_PED_ENT_RAMAL;

		public String getDB_PED_ENT_RAMAL() {
			return this.DB_PED_ENT_RAMAL;
		}

		public Boolean DB_PED_ENT_RAMALIsNullable() {
			return true;
		}

		public Boolean DB_PED_ENT_RAMALIsKey() {
			return false;
		}

		public Integer DB_PED_ENT_RAMALLength() {
			return 5;
		}

		public Integer DB_PED_ENT_RAMALPrecision() {
			return 0;
		}

		public String DB_PED_ENT_RAMALDefault() {

			return null;

		}

		public String DB_PED_ENT_RAMALComment() {

			return "";

		}

		public String DB_PED_ENT_RAMALPattern() {

			return "";

		}

		public String DB_PED_ENT_RAMALOriginalDbColumnName() {

			return "DB_PED_ENT_RAMAL";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tDBInput_1Struct other = (after_tDBInput_1Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(after_tDBInput_1Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PED_DATA_ENVIO = this.DB_PED_DATA_ENVIO;
			other.DB_PED_SITUACAO = this.DB_PED_SITUACAO;
			other.DB_PED_EMPRESA = this.DB_PED_EMPRESA;
			other.DB_PED_CLIENTE = this.DB_PED_CLIENTE;
			other.DB_PED_COD_TRANSP = this.DB_PED_COD_TRANSP;
			other.DB_PED_COND_PGTO = this.DB_PED_COND_PGTO;
			other.DB_PED_LISTA_PRECO = this.DB_PED_LISTA_PRECO;
			other.DB_PED_REPRES = this.DB_PED_REPRES;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;
			other.DB_PED_TIPO = this.DB_PED_TIPO;
			other.DB_PED_TEXTO = this.DB_PED_TEXTO;
			other.DB_PED_ORD_COMPRA = this.DB_PED_ORD_COMPRA;
			other.DB_PED_OBSERV = this.DB_PED_OBSERV;
			other.DB_PED_DT_PREVENT = this.DB_PED_DT_PREVENT;
			other.DB_PED_TIPO_FRETE = this.DB_PED_TIPO_FRETE;
			other.DB_PED_ENT_ENDER = this.DB_PED_ENT_ENDER;
			other.DB_PED_ENT_BAIRRO = this.DB_PED_ENT_BAIRRO;
			other.DB_PED_ENT_CIDADE = this.DB_PED_ENT_CIDADE;
			other.DB_PED_ENT_CEP = this.DB_PED_ENT_CEP;
			other.DB_PED_ENT_UF = this.DB_PED_ENT_UF;
			other.DB_PED_ENT_CGCMF = this.DB_PED_ENT_CGCMF;
			other.DB_PED_ENT_CGCTE = this.DB_PED_ENT_CGCTE;
			other.DB_PED_ENT_COMPL = this.DB_PED_ENT_COMPL;
			other.DB_PED_ENT_PONTOREFER = this.DB_PED_ENT_PONTOREFER;
			other.DB_PED_ENT_RAZAO = this.DB_PED_ENT_RAZAO;
			other.DB_PED_ENT_TELEFONE = this.DB_PED_ENT_TELEFONE;
			other.DB_PED_ENT_RAMAL = this.DB_PED_ENT_RAMAL;

		}

		public void copyKeysDataTo(after_tDBInput_1Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DATA_ENVIO = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_SITUACAO = null;
					} else {
						this.DB_PED_SITUACAO = dis.readShort();
					}

					this.DB_PED_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_CLIENTE = null;
					} else {
						this.DB_PED_CLIENTE = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_COD_TRANSP = null;
					} else {
						this.DB_PED_COD_TRANSP = dis.readDouble();
					}

					this.DB_PED_COND_PGTO = readInteger(dis);

					this.DB_PED_LISTA_PRECO = readString(dis);

					this.DB_PED_REPRES = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

					this.DB_PED_TIPO = readString(dis);

					this.DB_PED_TEXTO = readString(dis);

					this.DB_PED_ORD_COMPRA = readString(dis);

					this.DB_PED_OBSERV = readString(dis);

					this.DB_PED_DT_PREVENT = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_PED_TIPO_FRETE = null;
					} else {
						this.DB_PED_TIPO_FRETE = dis.readShort();
					}

					this.DB_PED_ENT_ENDER = readString(dis);

					this.DB_PED_ENT_BAIRRO = readString(dis);

					this.DB_PED_ENT_CIDADE = readString(dis);

					this.DB_PED_ENT_CEP = readString(dis);

					this.DB_PED_ENT_UF = readString(dis);

					this.DB_PED_ENT_CGCMF = readString(dis);

					this.DB_PED_ENT_CGCTE = readString(dis);

					this.DB_PED_ENT_COMPL = readString(dis);

					this.DB_PED_ENT_PONTOREFER = readString(dis);

					this.DB_PED_ENT_RAZAO = readString(dis);

					this.DB_PED_ENT_TELEFONE = readString(dis);

					this.DB_PED_ENT_RAMAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DATA_ENVIO, dos);

				// Short

				if (this.DB_PED_SITUACAO == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_SITUACAO);
				}

				// String

				writeString(this.DB_PED_EMPRESA, dos);

				// Double

				if (this.DB_PED_CLIENTE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_CLIENTE);
				}

				// Double

				if (this.DB_PED_COD_TRANSP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_PED_COD_TRANSP);
				}

				// Integer

				writeInteger(this.DB_PED_COND_PGTO, dos);

				// String

				writeString(this.DB_PED_LISTA_PRECO, dos);

				// Integer

				writeInteger(this.DB_PED_REPRES, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

				// String

				writeString(this.DB_PED_TIPO, dos);

				// String

				writeString(this.DB_PED_TEXTO, dos);

				// String

				writeString(this.DB_PED_ORD_COMPRA, dos);

				// String

				writeString(this.DB_PED_OBSERV, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_PREVENT, dos);

				// Short

				if (this.DB_PED_TIPO_FRETE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DB_PED_TIPO_FRETE);
				}

				// String

				writeString(this.DB_PED_ENT_ENDER, dos);

				// String

				writeString(this.DB_PED_ENT_BAIRRO, dos);

				// String

				writeString(this.DB_PED_ENT_CIDADE, dos);

				// String

				writeString(this.DB_PED_ENT_CEP, dos);

				// String

				writeString(this.DB_PED_ENT_UF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCMF, dos);

				// String

				writeString(this.DB_PED_ENT_CGCTE, dos);

				// String

				writeString(this.DB_PED_ENT_COMPL, dos);

				// String

				writeString(this.DB_PED_ENT_PONTOREFER, dos);

				// String

				writeString(this.DB_PED_ENT_RAZAO, dos);

				// String

				writeString(this.DB_PED_ENT_TELEFONE, dos);

				// String

				writeString(this.DB_PED_ENT_RAMAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DATA_ENVIO=" + String.valueOf(DB_PED_DATA_ENVIO));
			sb.append(",DB_PED_SITUACAO=" + String.valueOf(DB_PED_SITUACAO));
			sb.append(",DB_PED_EMPRESA=" + DB_PED_EMPRESA);
			sb.append(",DB_PED_CLIENTE=" + String.valueOf(DB_PED_CLIENTE));
			sb.append(",DB_PED_COD_TRANSP=" + String.valueOf(DB_PED_COD_TRANSP));
			sb.append(",DB_PED_COND_PGTO=" + String.valueOf(DB_PED_COND_PGTO));
			sb.append(",DB_PED_LISTA_PRECO=" + DB_PED_LISTA_PRECO);
			sb.append(",DB_PED_REPRES=" + String.valueOf(DB_PED_REPRES));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append(",DB_PED_TIPO=" + DB_PED_TIPO);
			sb.append(",DB_PED_TEXTO=" + DB_PED_TEXTO);
			sb.append(",DB_PED_ORD_COMPRA=" + DB_PED_ORD_COMPRA);
			sb.append(",DB_PED_OBSERV=" + DB_PED_OBSERV);
			sb.append(",DB_PED_DT_PREVENT=" + String.valueOf(DB_PED_DT_PREVENT));
			sb.append(",DB_PED_TIPO_FRETE=" + String.valueOf(DB_PED_TIPO_FRETE));
			sb.append(",DB_PED_ENT_ENDER=" + DB_PED_ENT_ENDER);
			sb.append(",DB_PED_ENT_BAIRRO=" + DB_PED_ENT_BAIRRO);
			sb.append(",DB_PED_ENT_CIDADE=" + DB_PED_ENT_CIDADE);
			sb.append(",DB_PED_ENT_CEP=" + DB_PED_ENT_CEP);
			sb.append(",DB_PED_ENT_UF=" + DB_PED_ENT_UF);
			sb.append(",DB_PED_ENT_CGCMF=" + DB_PED_ENT_CGCMF);
			sb.append(",DB_PED_ENT_CGCTE=" + DB_PED_ENT_CGCTE);
			sb.append(",DB_PED_ENT_COMPL=" + DB_PED_ENT_COMPL);
			sb.append(",DB_PED_ENT_PONTOREFER=" + DB_PED_ENT_PONTOREFER);
			sb.append(",DB_PED_ENT_RAZAO=" + DB_PED_ENT_RAZAO);
			sb.append(",DB_PED_ENT_TELEFONE=" + DB_PED_ENT_TELEFONE);
			sb.append(",DB_PED_ENT_RAMAL=" + DB_PED_ENT_RAMAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DATA_ENVIO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DATA_ENVIO);
			}

			sb.append("|");

			if (DB_PED_SITUACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_SITUACAO);
			}

			sb.append("|");

			if (DB_PED_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_EMPRESA);
			}

			sb.append("|");

			if (DB_PED_CLIENTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_CLIENTE);
			}

			sb.append("|");

			if (DB_PED_COD_TRANSP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COD_TRANSP);
			}

			sb.append("|");

			if (DB_PED_COND_PGTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_COND_PGTO);
			}

			sb.append("|");

			if (DB_PED_LISTA_PRECO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_LISTA_PRECO);
			}

			sb.append("|");

			if (DB_PED_REPRES == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_REPRES);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			if (DB_PED_TIPO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO);
			}

			sb.append("|");

			if (DB_PED_TEXTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TEXTO);
			}

			sb.append("|");

			if (DB_PED_ORD_COMPRA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ORD_COMPRA);
			}

			sb.append("|");

			if (DB_PED_OBSERV == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OBSERV);
			}

			sb.append("|");

			if (DB_PED_DT_PREVENT == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_PREVENT);
			}

			sb.append("|");

			if (DB_PED_TIPO_FRETE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_TIPO_FRETE);
			}

			sb.append("|");

			if (DB_PED_ENT_ENDER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_ENDER);
			}

			sb.append("|");

			if (DB_PED_ENT_BAIRRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_BAIRRO);
			}

			sb.append("|");

			if (DB_PED_ENT_CIDADE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CIDADE);
			}

			sb.append("|");

			if (DB_PED_ENT_CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CEP);
			}

			sb.append("|");

			if (DB_PED_ENT_UF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_UF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCMF == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCMF);
			}

			sb.append("|");

			if (DB_PED_ENT_CGCTE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_CGCTE);
			}

			sb.append("|");

			if (DB_PED_ENT_COMPL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_COMPL);
			}

			sb.append("|");

			if (DB_PED_ENT_PONTOREFER == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_PONTOREFER);
			}

			sb.append("|");

			if (DB_PED_ENT_RAZAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAZAO);
			}

			sb.append("|");

			if (DB_PED_ENT_TELEFONE == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_TELEFONE);
			}

			sb.append("|");

			if (DB_PED_ENT_RAMAL == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_ENT_RAMAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_1");
		org.slf4j.MDC.put("_subJobPid", "oghP8F_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_2Process(globalMap);
				tDBInput_4Process(globalMap);
				tDBInput_5Process(globalMap);

				row1Struct row1 = new row1Struct();
				Pedido_complementoStruct Pedido_complemento = new Pedido_complementoStruct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tHashOutput_2 begin ] start
				 */

				ok_Hash.put("tHashOutput_2", false);
				start_Hash.put("tHashOutput_2", System.currentTimeMillis());

				currentComponent = "tHashOutput_2";

				cLabel = "Pedidos Completos";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

				int tos_count_tHashOutput_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_2", "Pedidos Completos", "tHashOutput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_2 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row3Struct> tHashFile_tHashOutput_2 = null;
				String hashKey_tHashOutput_2 = "tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_2";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_2)) {
					if (mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2) == null) {
						mf_tHashOutput_2.getResourceMap().put(hashKey_tHashOutput_2,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row3Struct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_2 = mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2);
					} else {
						tHashFile_tHashOutput_2 = mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2);
					}
				}
				int nb_line_tHashOutput_2 = 0;

				/**
				 * [tHashOutput_2 begin ] stop
				 */

				/**
				 * [tFilterRow_18 begin ] start
				 */

				ok_Hash.put("tFilterRow_18", false);
				start_Hash.put("tFilterRow_18", System.currentTimeMillis());

				currentComponent = "tFilterRow_18";

				cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Pedido_complemento");

				int tos_count_tFilterRow_18 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_18 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_18 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_18 = new StringBuilder();
							log4jParamters_tFilterRow_18.append("Parameters:");
							log4jParamters_tFilterRow_18.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_18.append(" | ");
							log4jParamters_tFilterRow_18.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==")
									+ ", RVALUE=" + ("\"0\"") + ", INPUT_COLUMN=" + ("DB_PED_SITUACAO") + ", FUNCTION="
									+ ("") + "}, {OPERATOR=" + ("==") + ", RVALUE=" + ("null") + ", INPUT_COLUMN="
									+ ("DB_PED_DATA_ENVIO") + ", FUNCTION=" + ("") + "}, {OPERATOR=" + ("!=")
									+ ", RVALUE=" + ("null") + ", INPUT_COLUMN=" + ("DB_PED_NRO") + ", FUNCTION=" + ("")
									+ "}]");
							log4jParamters_tFilterRow_18.append(" | ");
							log4jParamters_tFilterRow_18.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_18.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_18 - " + (log4jParamters_tFilterRow_18));
						}
					}
					new BytesLimit65535_tFilterRow_18().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_18", "Pedidos prontos p/ integra\u00E7\u00E3o", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_18 = 0;
				int nb_line_ok_tFilterRow_18 = 0;
				int nb_line_reject_tFilterRow_18 = 0;

				class Operator_tFilterRow_18 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_18(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_18 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tMap_5 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_5 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_5 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_5 = new StringBuilder();
							log4jParamters_tMap_5.append("Parameters:");
							log4jParamters_tMap_5.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_5.append(" | ");
							log4jParamters_tMap_5.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_5.append(" | ");
							log4jParamters_tMap_5.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_5.append(" | ");
							log4jParamters_tMap_5.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_5.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_5 - " + (log4jParamters_tMap_5));
						}
					}
					new BytesLimit65535_tMap_5().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_5", "tMap_5", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row1_tMap_5 = 0;

				int count_row2_tMap_5 = 0;

				int count_row8_tMap_5 = 0;

				int count_row23_tMap_5 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row23Struct> tHash_Lookup_row23 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row23Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row23Struct>) globalMap
						.get("tHash_Lookup_row23"));

				row23Struct row23HashKey = new row23Struct();
				row23Struct row23Default = new row23Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_Pedido_complemento_tMap_5 = 0;

				Pedido_complementoStruct Pedido_complemento_tmp = new Pedido_complementoStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				cLabel = "Input Pedido";

				int tos_count_tDBInput_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_1 = new StringBuilder();
							log4jParamters_tDBInput_1.append("Parameters:");
							log4jParamters_tDBInput_1.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PORT" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("DBNAME" + " = " + "\"mercanet\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:H0WkvPzv9xQVx53HNeRtYLbyC7lbUFc2SoUht8/xRZjIlrHjsc8JdVXv")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERY" + " = "
									+ "\"SELECT DB_PED_NRO,   		DB_PED_OPERACAO,  		DB_PED_DATA_ENVIO,  		DB_PED_SITUACAO,  		DB_PED_EMPRESA,  		DB_PED_CLIENTE,  		DB_PED_COD_TRANSP,  		DB_PED_COND_PGTO,  		DB_PED_LISTA_PRECO,  		DB_PED_REPRES,  		DB_PED_DT_EMISSAO,  		DB_PED_TIPO,  		DB_PED_TEXTO,  		DB_PED_ORD_COMPRA,  		DB_PED_OBSERV,  		DB_PED_DT_PREVENT,  		DB_PED_TIPO_FRETE,  		DB_PED_ENT_ENDER,  		DB_PED_ENT_BAIRRO,  		DB_PED_ENT_CIDADE,  		DB_PED_ENT_CEP,  		DB_PED_ENT_UF,  		DB_PED_ENT_CGCMF,  		DB_PED_ENT_CGCTE,  		DB_PED_ENT_COMPL,  		DB_PED_ENT_PONTOREFER,  		DB_PED_ENT_RAZAO,  		DB_PED_ENT_TELEFONE,  		DB_PED_ENT_RAMAL    FROM DB_PEDIDO  WHERE DB_PED_DT_EMISSAO >= CAST(GETDATE() - 90 AS DATE)\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_NRO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_OPERACAO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_DATA_ENVIO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_SITUACAO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_EMPRESA") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_CLIENTE") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_COD_TRANSP") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_COND_PGTO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_LISTA_PRECO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_REPRES") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_DT_EMISSAO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_PED_TIPO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("DB_PED_TEXTO")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("DB_PED_ORD_COMPRA") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("DB_PED_OBSERV") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_DT_PREVENT") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_TIPO_FRETE") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_ENDER") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_BAIRRO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_CIDADE") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_CEP") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_UF") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_CGCMF") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_CGCTE") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_COMPL") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_PONTOREFER") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_RAZAO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_TELEFONE") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_ENT_RAMAL") + "}]");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_1 - " + (log4jParamters_tDBInput_1));
						}
					}
					new BytesLimit65535_tDBInput_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_1", "Input Pedido", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_1 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_1 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_1 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_1, talendToDBArray_tDBInput_1);
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "analytics";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:wLXIpe98x95hCMY1ec+f5543BhYvsIJlYLji4m0FEGT0Z6SR8h3c4tvf");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String port_tDBInput_1 = "";
				String dbname_tDBInput_1 = "mercanet";
				String url_tDBInput_1 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_1)) {
					url_tDBInput_1 += ":" + "";
				}
				if (!"".equals(dbname_tDBInput_1)) {
					url_tDBInput_1 += ";databaseName=" + "mercanet";
				}
				url_tDBInput_1 += ";appName=" + projectName + ";" + "instanceName=mercanet;";
				String dbschema_tDBInput_1 = "";

				log.debug("tDBInput_1 - Driver ClassName: " + driverClass_tDBInput_1 + ".");

				log.debug("tDBInput_1 - Connection attempt to '" + url_tDBInput_1 + "' with the username '"
						+ dbUser_tDBInput_1 + "'.");

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);
				log.debug("tDBInput_1 - Connection to '" + url_tDBInput_1 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT DB_PED_NRO, \n		DB_PED_OPERACAO,\n		DB_PED_DATA_ENVIO,\n		DB_PED_SITUACAO,\n		DB_PED_EMPRESA,\n		DB_PED_CLIENTE,"
						+ "\n		DB_PED_COD_TRANSP,\n		DB_PED_COND_PGTO,\n		DB_PED_LISTA_PRECO,\n		DB_PED_REPRES,\n		DB_PED_DT_EMISSAO,\n		DB_PED_TIP"
						+ "O,\n		DB_PED_TEXTO,\n		DB_PED_ORD_COMPRA,\n		DB_PED_OBSERV,\n		DB_PED_DT_PREVENT,\n		DB_PED_TIPO_FRETE,\n		DB_PED_ENT_EN"
						+ "DER,\n		DB_PED_ENT_BAIRRO,\n		DB_PED_ENT_CIDADE,\n		DB_PED_ENT_CEP,\n		DB_PED_ENT_UF,\n		DB_PED_ENT_CGCMF,\n		DB_PED_ENT"
						+ "_CGCTE,\n		DB_PED_ENT_COMPL,\n		DB_PED_ENT_PONTOREFER,\n		DB_PED_ENT_RAZAO,\n		DB_PED_ENT_TELEFONE,\n		DB_PED_ENT_RAMAL"
						+ "\n  FROM DB_PEDIDO\nWHERE DB_PED_DT_EMISSAO >= CAST(GETDATE() - 90 AS DATE)";

				log.debug("tDBInput_1 - Executing the query: '" + dbquery_tDBInput_1 + "'.");

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);

				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					log.debug("tDBInput_1 - Retrieving records from the database.");

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							row1.DB_PED_NRO = null;
						} else {

							row1.DB_PED_NRO = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_NRO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.DB_PED_OPERACAO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_OPERACAO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_OPERACAO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_OPERACAO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.DB_PED_DATA_ENVIO = null;
						} else {

							row1.DB_PED_DATA_ENVIO = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 3);

						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.DB_PED_SITUACAO = null;
						} else {

							row1.DB_PED_SITUACAO = rs_tDBInput_1.getShort(4);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_SITUACAO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.DB_PED_EMPRESA = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_EMPRESA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_EMPRESA = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_EMPRESA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.DB_PED_CLIENTE = null;
						} else {

							row1.DB_PED_CLIENTE = rs_tDBInput_1.getDouble(6);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_CLIENTE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.DB_PED_COD_TRANSP = null;
						} else {

							row1.DB_PED_COD_TRANSP = rs_tDBInput_1.getDouble(7);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_COD_TRANSP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.DB_PED_COND_PGTO = null;
						} else {

							row1.DB_PED_COND_PGTO = rs_tDBInput_1.getInt(8);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_COND_PGTO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.DB_PED_LISTA_PRECO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(9);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_LISTA_PRECO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_LISTA_PRECO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_LISTA_PRECO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.DB_PED_REPRES = null;
						} else {

							row1.DB_PED_REPRES = rs_tDBInput_1.getInt(10);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_REPRES = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.DB_PED_DT_EMISSAO = null;
						} else {

							row1.DB_PED_DT_EMISSAO = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 11);

						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.DB_PED_TIPO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_TIPO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_TIPO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_TIPO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.DB_PED_TEXTO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(13);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(13).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_TEXTO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_TEXTO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_TEXTO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.DB_PED_ORD_COMPRA = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ORD_COMPRA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ORD_COMPRA = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ORD_COMPRA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.DB_PED_OBSERV = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(15);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_OBSERV = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_OBSERV = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_OBSERV = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.DB_PED_DT_PREVENT = null;
						} else {

							row1.DB_PED_DT_PREVENT = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 16);

						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.DB_PED_TIPO_FRETE = null;
						} else {

							row1.DB_PED_TIPO_FRETE = rs_tDBInput_1.getShort(17);
							if (rs_tDBInput_1.wasNull()) {
								row1.DB_PED_TIPO_FRETE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.DB_PED_ENT_ENDER = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_ENDER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_ENDER = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_ENDER = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.DB_PED_ENT_BAIRRO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(19);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_BAIRRO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_BAIRRO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_BAIRRO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.DB_PED_ENT_CIDADE = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_CIDADE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_CIDADE = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_CIDADE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.DB_PED_ENT_CEP = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_CEP = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_CEP = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_CEP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.DB_PED_ENT_UF = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_UF = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_UF = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_UF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.DB_PED_ENT_CGCMF = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_CGCMF = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_CGCMF = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_CGCMF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.DB_PED_ENT_CGCTE = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_CGCTE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_CGCTE = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_CGCTE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.DB_PED_ENT_COMPL = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(25);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(25).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_COMPL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_COMPL = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_COMPL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.DB_PED_ENT_PONTOREFER = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(26);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(26).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_PONTOREFER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_PONTOREFER = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_PONTOREFER = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.DB_PED_ENT_RAZAO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(27);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(27).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_RAZAO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_RAZAO = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_RAZAO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.DB_PED_ENT_TELEFONE = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_TELEFONE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_TELEFONE = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_TELEFONE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.DB_PED_ENT_RAMAL = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(29);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(29).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DB_PED_ENT_RAMAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DB_PED_ENT_RAMAL = tmpContent_tDBInput_1;
								}
							} else {
								row1.DB_PED_ENT_RAMAL = null;
							}
						}

						log.debug("tDBInput_1 - Retrieving the record " + nb_line_tDBInput_1 + ".");

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Input Pedido";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Input Pedido";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_5 main ] start
						 */

						currentComponent = "tMap_5";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row1", "tDBInput_1", "Input Pedido", "tMSSqlInput", "tMap_5", "tMap_5", "tMap"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

						row2Struct row2 = null;

						row8Struct row8 = null;

						row23Struct row23 = null;

						// ###############################
						// # Input tables (lookups)

						boolean rejectedInnerJoin_tMap_5 = false;
						boolean mainRowRejected_tMap_5 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row2"
						///////////////////////////////////////////////

						boolean forceLooprow2 = false;

						row2Struct row2ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_5 = false;

							row2HashKey.DB_PEDC_NRO = row1.DB_PED_NRO;

							row2HashKey.hashCodeDirty = true;

							tHash_Lookup_row2.lookup(row2HashKey);

						} // G_TM_M_020

						if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

							// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
							// and it contains more one result from keys : row2.DB_PEDC_NRO = '" +
							// row2HashKey.DB_PEDC_NRO + "'");
						} // G 071

						row2Struct fromLookup_row2 = null;
						row2 = row2Default;

						if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

							fromLookup_row2 = tHash_Lookup_row2.next();

						} // G 099

						if (fromLookup_row2 != null) {
							row2 = fromLookup_row2;
						}

						///////////////////////////////////////////////
						// Starting Lookup Table "row8"
						///////////////////////////////////////////////

						boolean forceLooprow8 = false;

						row8Struct row8ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_5 = false;

							row8HashKey.DB_PEDI_PEDIDO = row2.DB_PEDC_NRO;

							row8HashKey.hashCodeDirty = true;

							tHash_Lookup_row8.lookup(row8HashKey);

						} // G_TM_M_020

						if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071

							// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
							// and it contains more one result from keys : row8.DB_PEDI_PEDIDO = '" +
							// row8HashKey.DB_PEDI_PEDIDO + "'");
						} // G 071

						row8Struct fromLookup_row8 = null;
						row8 = row8Default;

						if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

							fromLookup_row8 = tHash_Lookup_row8.next();

						} // G 099

						if (fromLookup_row8 != null) {
							row8 = fromLookup_row8;
						}

						///////////////////////////////////////////////
						// Starting Lookup Table "row23"
						///////////////////////////////////////////////

						boolean forceLooprow23 = false;

						row23Struct row23ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_5) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_5 = false;

							row23HashKey.DB_TBPGTO_COD = row1.DB_PED_COND_PGTO;

							row23HashKey.hashCodeDirty = true;

							tHash_Lookup_row23.lookup(row23HashKey);

						} // G_TM_M_020

						if (tHash_Lookup_row23 != null && tHash_Lookup_row23.getCount(row23HashKey) > 1) { // G 071

							// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
							// 'row23' and it contains more one result from keys : row23.DB_TBPGTO_COD = '"
							// + row23HashKey.DB_TBPGTO_COD + "'");
						} // G 071

						row23Struct fromLookup_row23 = null;
						row23 = row23Default;

						if (tHash_Lookup_row23 != null && tHash_Lookup_row23.hasNext()) { // G 099

							fromLookup_row23 = tHash_Lookup_row23.next();

						} // G 099

						if (fromLookup_row23 != null) {
							row23 = fromLookup_row23;
						}

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
							// ###############################
							// # Output tables

							Pedido_complemento = null;

// # Output table : 'Pedido_complemento'
							count_Pedido_complemento_tMap_5++;

							Pedido_complemento_tmp.DB_PED_NRO = row1.DB_PED_NRO;
							Pedido_complemento_tmp.DB_PED_OPERACAO = row1.DB_PED_OPERACAO;
							Pedido_complemento_tmp.DB_PED_DATA_ENVIO = row1.DB_PED_DATA_ENVIO;
							Pedido_complemento_tmp.DB_PED_SITUACAO = row1.DB_PED_SITUACAO;
							Pedido_complemento_tmp.DB_PED_EMPRESA = row1.DB_PED_EMPRESA;
							Pedido_complemento_tmp.DB_PED_CLIENTE = row1.DB_PED_CLIENTE;
							Pedido_complemento_tmp.DB_PEDC_LIBERASEMSALDO = row2.DB_PEDC_LIBERASEMSALDO;
							Pedido_complemento_tmp.DB_PED_COD_TRANSP = row1.DB_PED_COD_TRANSP;
							Pedido_complemento_tmp.DB_PED_COND_PGTO = row1.DB_PED_COND_PGTO;
							Pedido_complemento_tmp.DB_PED_LISTA_PRECO = row1.DB_PED_LISTA_PRECO;
							Pedido_complemento_tmp.DB_PED_REPRES = row1.DB_PED_REPRES;
							Pedido_complemento_tmp.DB_PED_DT_EMISSAO = row1.DB_PED_DT_EMISSAO;
							Pedido_complemento_tmp.DB_PED_TIPO = row1.DB_PED_TIPO;
							Pedido_complemento_tmp.DB_PED_TEXTO = row1.DB_PED_TEXTO;
							Pedido_complemento_tmp.DB_PED_ORD_COMPRA = row1.DB_PED_ORD_COMPRA;
							Pedido_complemento_tmp.DB_PED_OBSERV = row1.DB_PED_OBSERV;
							Pedido_complemento_tmp.DB_PED_DT_PREVENT = row1.DB_PED_DT_PREVENT;
							Pedido_complemento_tmp.DB_PED_TIPO_FRETE = row1.DB_PED_TIPO_FRETE;
							Pedido_complemento_tmp.DB_PED_ENT_ENDER = row1.DB_PED_ENT_ENDER;
							Pedido_complemento_tmp.DB_PED_ENT_BAIRRO = row1.DB_PED_ENT_BAIRRO;
							Pedido_complemento_tmp.DB_PED_ENT_CIDADE = row1.DB_PED_ENT_CIDADE;
							Pedido_complemento_tmp.DB_PED_ENT_CEP = row1.DB_PED_ENT_CEP;
							Pedido_complemento_tmp.DB_PED_ENT_UF = row1.DB_PED_ENT_UF;
							Pedido_complemento_tmp.DB_PED_ENT_CGCMF = row1.DB_PED_ENT_CGCMF;
							Pedido_complemento_tmp.DB_PED_ENT_CGCTE = row1.DB_PED_ENT_CGCTE;
							Pedido_complemento_tmp.DB_PED_ENT_COMPL = row1.DB_PED_ENT_COMPL;
							Pedido_complemento_tmp.DB_PED_ENT_PONTOREFER = row1.DB_PED_ENT_PONTOREFER;
							Pedido_complemento_tmp.DB_PED_ENT_RAZAO = row1.DB_PED_ENT_RAZAO;
							Pedido_complemento_tmp.DB_PED_ENT_TELEFONE = row1.DB_PED_ENT_TELEFONE;
							Pedido_complemento_tmp.DB_PED_ENT_RAMAL = row1.DB_PED_ENT_RAMAL;
							Pedido_complemento_tmp.DB_PEDI_QTDE_SOLIC = row8.DB_PEDI_QTDE_SOLIC;
							Pedido_complemento_tmp.DB_PEDI_PRECO_LIQ = row8.DB_PEDI_PRECO_LIQ;
							Pedido_complemento_tmp.DB_TBPGTO_ZTERM = row23.DB_TBPGTO_ZTERM;
							Pedido_complemento = Pedido_complemento_tmp;
							log.debug("tMap_5 - Outputting the record " + count_Pedido_complemento_tMap_5
									+ " of the output table 'Pedido_complemento'.");

// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_5 = false;

						tos_count_tMap_5++;

						/**
						 * [tMap_5 main ] stop
						 */

						/**
						 * [tMap_5 process_data_begin ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_begin ] stop
						 */
// Start of branch "Pedido_complemento"
						if (Pedido_complemento != null) {

							/**
							 * [tFilterRow_18 main ] start
							 */

							currentComponent = "tFilterRow_18";

							cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "Pedido_complemento", "tMap_5", "tMap_5", "tMap", "tFilterRow_18",
									"Pedidos prontos p/ integra\u00E7\u00E3o", "tFilterRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("Pedido_complemento - "
										+ (Pedido_complemento == null ? "" : Pedido_complemento.toLogString()));
							}

							row3 = null;
							Operator_tFilterRow_18 ope_tFilterRow_18 = new Operator_tFilterRow_18("&&");
							ope_tFilterRow_18
									.matches(
											(Pedido_complemento.DB_PED_SITUACAO == null ? false
													: Pedido_complemento.DB_PED_SITUACAO.compareTo(
															ParserUtils.parseTo_Short(String.valueOf("0"))) == 0),
											"DB_PED_SITUACAO.compareTo(\"0\") == 0 failed");

							ope_tFilterRow_18.matches((Pedido_complemento.DB_PED_DATA_ENVIO == null),
									"DB_PED_DATA_ENVIO==null failed");
							ope_tFilterRow_18.matches((Pedido_complemento.DB_PED_NRO != null),
									"DB_PED_NRO!=null failed");

							if (ope_tFilterRow_18.getMatchFlag()) {
								if (row3 == null) {
									row3 = new row3Struct();
								}
								row3.DB_PED_NRO = Pedido_complemento.DB_PED_NRO;
								row3.DB_PED_OPERACAO = Pedido_complemento.DB_PED_OPERACAO;
								row3.DB_PED_DATA_ENVIO = Pedido_complemento.DB_PED_DATA_ENVIO;
								row3.DB_PED_SITUACAO = Pedido_complemento.DB_PED_SITUACAO;
								row3.DB_PED_EMPRESA = Pedido_complemento.DB_PED_EMPRESA;
								row3.DB_PED_CLIENTE = Pedido_complemento.DB_PED_CLIENTE;
								row3.DB_PEDC_LIBERASEMSALDO = Pedido_complemento.DB_PEDC_LIBERASEMSALDO;
								row3.DB_PED_COD_TRANSP = Pedido_complemento.DB_PED_COD_TRANSP;
								row3.DB_PED_COND_PGTO = Pedido_complemento.DB_PED_COND_PGTO;
								row3.DB_PED_LISTA_PRECO = Pedido_complemento.DB_PED_LISTA_PRECO;
								row3.DB_PED_REPRES = Pedido_complemento.DB_PED_REPRES;
								row3.DB_PED_DT_EMISSAO = Pedido_complemento.DB_PED_DT_EMISSAO;
								row3.DB_PED_TIPO = Pedido_complemento.DB_PED_TIPO;
								row3.DB_PED_TEXTO = Pedido_complemento.DB_PED_TEXTO;
								row3.DB_PED_ORD_COMPRA = Pedido_complemento.DB_PED_ORD_COMPRA;
								row3.DB_PED_OBSERV = Pedido_complemento.DB_PED_OBSERV;
								row3.DB_PED_DT_PREVENT = Pedido_complemento.DB_PED_DT_PREVENT;
								row3.DB_PED_TIPO_FRETE = Pedido_complemento.DB_PED_TIPO_FRETE;
								row3.DB_PED_ENT_ENDER = Pedido_complemento.DB_PED_ENT_ENDER;
								row3.DB_PED_ENT_BAIRRO = Pedido_complemento.DB_PED_ENT_BAIRRO;
								row3.DB_PED_ENT_CIDADE = Pedido_complemento.DB_PED_ENT_CIDADE;
								row3.DB_PED_ENT_CEP = Pedido_complemento.DB_PED_ENT_CEP;
								row3.DB_PED_ENT_UF = Pedido_complemento.DB_PED_ENT_UF;
								row3.DB_PED_ENT_CGCMF = Pedido_complemento.DB_PED_ENT_CGCMF;
								row3.DB_PED_ENT_CGCTE = Pedido_complemento.DB_PED_ENT_CGCTE;
								row3.DB_PED_ENT_COMPL = Pedido_complemento.DB_PED_ENT_COMPL;
								row3.DB_PED_ENT_PONTOREFER = Pedido_complemento.DB_PED_ENT_PONTOREFER;
								row3.DB_PED_ENT_RAZAO = Pedido_complemento.DB_PED_ENT_RAZAO;
								row3.DB_PED_ENT_TELEFONE = Pedido_complemento.DB_PED_ENT_TELEFONE;
								row3.DB_PED_ENT_RAMAL = Pedido_complemento.DB_PED_ENT_RAMAL;
								row3.DB_PEDI_QTDE_SOLIC = Pedido_complemento.DB_PEDI_QTDE_SOLIC;
								row3.DB_PEDI_PRECO_LIQ = Pedido_complemento.DB_PEDI_PRECO_LIQ;
								row3.DB_TBPGTO_ZTERM = Pedido_complemento.DB_TBPGTO_ZTERM;
								log.debug("tFilterRow_18 - Process the record " + (nb_line_tFilterRow_18 + 1) + ".");

								nb_line_ok_tFilterRow_18++;
							} else {
								nb_line_reject_tFilterRow_18++;
							}

							nb_line_tFilterRow_18++;

							tos_count_tFilterRow_18++;

							/**
							 * [tFilterRow_18 main ] stop
							 */

							/**
							 * [tFilterRow_18 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_18";

							cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

							/**
							 * [tFilterRow_18 process_data_begin ] stop
							 */
// Start of branch "row3"
							if (row3 != null) {

								/**
								 * [tHashOutput_2 main ] start
								 */

								currentComponent = "tHashOutput_2";

								cLabel = "Pedidos Completos";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row3", "tFilterRow_18", "Pedidos prontos p/ integra\u00E7\u00E3o",
										"tFilterRow", "tHashOutput_2", "Pedidos Completos", "tHashOutput"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
								}

								row3Struct oneRow_tHashOutput_2 = new row3Struct();

								oneRow_tHashOutput_2.DB_PED_NRO = row3.DB_PED_NRO;
								oneRow_tHashOutput_2.DB_PED_OPERACAO = row3.DB_PED_OPERACAO;
								oneRow_tHashOutput_2.DB_PED_DATA_ENVIO = row3.DB_PED_DATA_ENVIO;
								oneRow_tHashOutput_2.DB_PED_SITUACAO = row3.DB_PED_SITUACAO;
								oneRow_tHashOutput_2.DB_PED_EMPRESA = row3.DB_PED_EMPRESA;
								oneRow_tHashOutput_2.DB_PED_CLIENTE = row3.DB_PED_CLIENTE;
								oneRow_tHashOutput_2.DB_PEDC_LIBERASEMSALDO = row3.DB_PEDC_LIBERASEMSALDO;
								oneRow_tHashOutput_2.DB_PED_COD_TRANSP = row3.DB_PED_COD_TRANSP;
								oneRow_tHashOutput_2.DB_PED_COND_PGTO = row3.DB_PED_COND_PGTO;
								oneRow_tHashOutput_2.DB_PED_LISTA_PRECO = row3.DB_PED_LISTA_PRECO;
								oneRow_tHashOutput_2.DB_PED_REPRES = row3.DB_PED_REPRES;
								oneRow_tHashOutput_2.DB_PED_DT_EMISSAO = row3.DB_PED_DT_EMISSAO;
								oneRow_tHashOutput_2.DB_PED_TIPO = row3.DB_PED_TIPO;
								oneRow_tHashOutput_2.DB_PED_TEXTO = row3.DB_PED_TEXTO;
								oneRow_tHashOutput_2.DB_PED_ORD_COMPRA = row3.DB_PED_ORD_COMPRA;
								oneRow_tHashOutput_2.DB_PED_OBSERV = row3.DB_PED_OBSERV;
								oneRow_tHashOutput_2.DB_PED_DT_PREVENT = row3.DB_PED_DT_PREVENT;
								oneRow_tHashOutput_2.DB_PED_TIPO_FRETE = row3.DB_PED_TIPO_FRETE;
								oneRow_tHashOutput_2.DB_PED_ENT_ENDER = row3.DB_PED_ENT_ENDER;
								oneRow_tHashOutput_2.DB_PED_ENT_BAIRRO = row3.DB_PED_ENT_BAIRRO;
								oneRow_tHashOutput_2.DB_PED_ENT_CIDADE = row3.DB_PED_ENT_CIDADE;
								oneRow_tHashOutput_2.DB_PED_ENT_CEP = row3.DB_PED_ENT_CEP;
								oneRow_tHashOutput_2.DB_PED_ENT_UF = row3.DB_PED_ENT_UF;
								oneRow_tHashOutput_2.DB_PED_ENT_CGCMF = row3.DB_PED_ENT_CGCMF;
								oneRow_tHashOutput_2.DB_PED_ENT_CGCTE = row3.DB_PED_ENT_CGCTE;
								oneRow_tHashOutput_2.DB_PED_ENT_COMPL = row3.DB_PED_ENT_COMPL;
								oneRow_tHashOutput_2.DB_PED_ENT_PONTOREFER = row3.DB_PED_ENT_PONTOREFER;
								oneRow_tHashOutput_2.DB_PED_ENT_RAZAO = row3.DB_PED_ENT_RAZAO;
								oneRow_tHashOutput_2.DB_PED_ENT_TELEFONE = row3.DB_PED_ENT_TELEFONE;
								oneRow_tHashOutput_2.DB_PED_ENT_RAMAL = row3.DB_PED_ENT_RAMAL;
								oneRow_tHashOutput_2.DB_PEDI_QTDE_SOLIC = row3.DB_PEDI_QTDE_SOLIC;
								oneRow_tHashOutput_2.DB_PEDI_PRECO_LIQ = row3.DB_PEDI_PRECO_LIQ;
								oneRow_tHashOutput_2.DB_TBPGTO_ZTERM = row3.DB_TBPGTO_ZTERM;

								tHashFile_tHashOutput_2.put(oneRow_tHashOutput_2);
								nb_line_tHashOutput_2++;

								tos_count_tHashOutput_2++;

								/**
								 * [tHashOutput_2 main ] stop
								 */

								/**
								 * [tHashOutput_2 process_data_begin ] start
								 */

								currentComponent = "tHashOutput_2";

								cLabel = "Pedidos Completos";

								/**
								 * [tHashOutput_2 process_data_begin ] stop
								 */

								/**
								 * [tHashOutput_2 process_data_end ] start
								 */

								currentComponent = "tHashOutput_2";

								cLabel = "Pedidos Completos";

								/**
								 * [tHashOutput_2 process_data_end ] stop
								 */

							} // End of branch "row3"

							/**
							 * [tFilterRow_18 process_data_end ] start
							 */

							currentComponent = "tFilterRow_18";

							cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

							/**
							 * [tFilterRow_18 process_data_end ] stop
							 */

						} // End of branch "Pedido_complemento"

						/**
						 * [tMap_5 process_data_end ] start
						 */

						currentComponent = "tMap_5";

						/**
						 * [tMap_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Input Pedido";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Input Pedido";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						log.debug("tDBInput_1 - Closing the connection to the database.");

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_1 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);
				log.debug("tDBInput_1 - Retrieved records count: " + nb_line_tDBInput_1 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_1 - " + ("Done."));

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				if (tHash_Lookup_row23 != null) {
					tHash_Lookup_row23.endGet();
				}
				globalMap.remove("tHash_Lookup_row23");

// ###############################      
				log.debug("tMap_5 - Written records count in the table 'Pedido_complemento': "
						+ count_Pedido_complemento_tMap_5 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tDBInput_1", "Input Pedido", "tMSSqlInput", "tMap_5", "tMap_5", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_5 - " + ("Done."));

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tFilterRow_18 end ] start
				 */

				currentComponent = "tFilterRow_18";

				cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

				globalMap.put("tFilterRow_18_NB_LINE", nb_line_tFilterRow_18);
				globalMap.put("tFilterRow_18_NB_LINE_OK", nb_line_ok_tFilterRow_18);
				globalMap.put("tFilterRow_18_NB_LINE_REJECT", nb_line_reject_tFilterRow_18);

				log.info("tFilterRow_18 - Processed records count:" + nb_line_tFilterRow_18 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_18 + ". Rejected records count:" + nb_line_reject_tFilterRow_18 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Pedido_complemento", 2,
						0, "tMap_5", "tMap_5", "tMap", "tFilterRow_18", "Pedidos prontos p/ integra\u00E7\u00E3o",
						"tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_18 - " + ("Done."));

				ok_Hash.put("tFilterRow_18", true);
				end_Hash.put("tFilterRow_18", System.currentTimeMillis());

				/**
				 * [tFilterRow_18 end ] stop
				 */

				/**
				 * [tHashOutput_2 end ] start
				 */

				currentComponent = "tHashOutput_2";

				cLabel = "Pedidos Completos";

				globalMap.put("tHashOutput_2_NB_LINE", nb_line_tHashOutput_2);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tFilterRow_18", "Pedidos prontos p/ integra\u00E7\u00E3o", "tFilterRow", "tHashOutput_2",
						"Pedidos Completos", "tHashOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_2", true);
				end_Hash.put("tHashOutput_2", System.currentTimeMillis());

				/**
				 * [tHashOutput_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row2");

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row23");

			// free memory for "tMap_5"
			globalMap.remove("tHash_Lookup_row8");

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				cLabel = "Input Pedido";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tFilterRow_18 finally ] start
				 */

				currentComponent = "tFilterRow_18";

				cLabel = "Pedidos prontos p/ integra\u00E7\u00E3o";

				/**
				 * [tFilterRow_18 finally ] stop
				 */

				/**
				 * [tHashOutput_2 finally ] start
				 */

				currentComponent = "tHashOutput_2";

				cLabel = "Pedidos Completos";

				/**
				 * [tHashOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PEDC_NRO;

		public Integer getDB_PEDC_NRO() {
			return this.DB_PEDC_NRO;
		}

		public Boolean DB_PEDC_NROIsNullable() {
			return true;
		}

		public Boolean DB_PEDC_NROIsKey() {
			return true;
		}

		public Integer DB_PEDC_NROLength() {
			return 10;
		}

		public Integer DB_PEDC_NROPrecision() {
			return 0;
		}

		public String DB_PEDC_NRODefault() {

			return "";

		}

		public String DB_PEDC_NROComment() {

			return "";

		}

		public String DB_PEDC_NROPattern() {

			return "";

		}

		public String DB_PEDC_NROOriginalDbColumnName() {

			return "DB_PEDC_NRO";

		}

		public Integer DB_PEDC_LIBERASEMSALDO;

		public Integer getDB_PEDC_LIBERASEMSALDO() {
			return this.DB_PEDC_LIBERASEMSALDO;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsNullable() {
			return true;
		}

		public Boolean DB_PEDC_LIBERASEMSALDOIsKey() {
			return false;
		}

		public Integer DB_PEDC_LIBERASEMSALDOLength() {
			return 10;
		}

		public Integer DB_PEDC_LIBERASEMSALDOPrecision() {
			return 0;
		}

		public String DB_PEDC_LIBERASEMSALDODefault() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOComment() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOPattern() {

			return "";

		}

		public String DB_PEDC_LIBERASEMSALDOOriginalDbColumnName() {

			return "DB_PEDC_LIBERASEMSALDO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PEDC_NRO == null) ? 0 : this.DB_PEDC_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.DB_PEDC_NRO == null) {
				if (other.DB_PEDC_NRO != null)
					return false;

			} else if (!this.DB_PEDC_NRO.equals(other.DB_PEDC_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.DB_PEDC_NRO = this.DB_PEDC_NRO;
			other.DB_PEDC_LIBERASEMSALDO = this.DB_PEDC_LIBERASEMSALDO;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.DB_PEDC_NRO = this.DB_PEDC_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDC_NRO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDC_NRO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDC_NRO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDC_NRO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DB_PEDC_LIBERASEMSALDO = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DB_PEDC_LIBERASEMSALDO = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.DB_PEDC_LIBERASEMSALDO, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PEDC_NRO=" + String.valueOf(DB_PEDC_NRO));
			sb.append(",DB_PEDC_LIBERASEMSALDO=" + String.valueOf(DB_PEDC_LIBERASEMSALDO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PEDC_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDC_NRO);
			}

			sb.append("|");

			if (DB_PEDC_LIBERASEMSALDO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDC_LIBERASEMSALDO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PEDC_NRO, other.DB_PEDC_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_2");
		org.slf4j.MDC.put("_subJobPid", "ZpiEjJ_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row2", false);
				start_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

				int tos_count_tAdvancedHash_row2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row2", "tAdvancedHash_row2", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row2
				// source node:tDBInput_2 - inputs:(after_tDBInput_1) outputs:(row2,row2) |
				// target node:tAdvancedHash_row2 - inputs:(row2) outputs:()
				// linked node: tMap_5 - inputs:(row1,row2,row23,row8)
				// outputs:(Pedido_complemento)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				cLabel = "Input Complemento";

				int tos_count_tDBInput_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_2 = new StringBuilder();
							log4jParamters_tDBInput_2.append("Parameters:");
							log4jParamters_tDBInput_2.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PORT" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DBNAME" + " = " + "\"mercanet\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:ZC2yfbdhMMNVVtjFMufSmxz99WOa4kd028DscTaXM5yEwn8xjcODi/cJ")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERY" + " = "
									+ "\"SELECT DB_PEDC_NRO,  DB_PEDC_LIBERASEMSALDO   FROM DB_PEDIDO_COMPL\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDC_NRO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDC_LIBERASEMSALDO") + "}]");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_2 - " + (log4jParamters_tDBInput_2));
						}
					}
					new BytesLimit65535_tDBInput_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_2", "Input Complemento", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_2 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_2 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_2 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_2, talendToDBArray_tDBInput_2);
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = "analytics";

				final String decryptedPassword_tDBInput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:SH8uYw+0BHAxchvvujt7kZd2d4yYGl1kHqQhnVUAbe0LRPDnoCKAUcHe");

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String port_tDBInput_2 = "";
				String dbname_tDBInput_2 = "mercanet";
				String url_tDBInput_2 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_2)) {
					url_tDBInput_2 += ":" + "";
				}
				if (!"".equals(dbname_tDBInput_2)) {
					url_tDBInput_2 += ";databaseName=" + "mercanet";
				}
				url_tDBInput_2 += ";appName=" + projectName + ";" + "instanceName=mercanet;";
				String dbschema_tDBInput_2 = "";

				log.debug("tDBInput_2 - Driver ClassName: " + driverClass_tDBInput_2 + ".");

				log.debug("tDBInput_2 - Connection attempt to '" + url_tDBInput_2 + "' with the username '"
						+ dbUser_tDBInput_2 + "'.");

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);
				log.debug("tDBInput_2 - Connection to '" + url_tDBInput_2 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT DB_PEDC_NRO,\nDB_PEDC_LIBERASEMSALDO\n FROM DB_PEDIDO_COMPL";

				log.debug("tDBInput_2 - Executing the query: '" + dbquery_tDBInput_2 + "'.");

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);

				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					log.debug("tDBInput_2 - Retrieving records from the database.");

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row2.DB_PEDC_NRO = null;
						} else {

							row2.DB_PEDC_NRO = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								row2.DB_PEDC_NRO = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row2.DB_PEDC_LIBERASEMSALDO = null;
						} else {

							row2.DB_PEDC_LIBERASEMSALDO = rs_tDBInput_2.getInt(2);
							if (rs_tDBInput_2.wasNull()) {
								row2.DB_PEDC_LIBERASEMSALDO = null;
							}
						}

						log.debug("tDBInput_2 - Retrieving the record " + nb_line_tDBInput_2 + ".");

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Input Complemento";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Input Complemento";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row2 main ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row2", "tDBInput_2", "Input Complemento", "tMSSqlInput", "tAdvancedHash_row2",
								"tAdvancedHash_row2", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
						}

						row2Struct row2_HashRow = new row2Struct();

						row2_HashRow.DB_PEDC_NRO = row2.DB_PEDC_NRO;

						row2_HashRow.DB_PEDC_LIBERASEMSALDO = row2.DB_PEDC_LIBERASEMSALDO;

						tHash_Lookup_row2.put(row2_HashRow);

						tos_count_tAdvancedHash_row2++;

						/**
						 * [tAdvancedHash_row2 main ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row2 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row2";

						/**
						 * [tAdvancedHash_row2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Input Complemento";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Input Complemento";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						log.debug("tDBInput_2 - Closing the connection to the database.");

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_2 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);
				log.debug("tDBInput_2 - Retrieved records count: " + nb_line_tDBInput_2 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_2 - " + ("Done."));

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				tHash_Lookup_row2.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tDBInput_2", "Input Complemento", "tMSSqlInput", "tAdvancedHash_row2", "tAdvancedHash_row2",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				cLabel = "Input Complemento";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				currentComponent = "tAdvancedHash_row2";

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PEDI_PEDIDO;

		public Integer getDB_PEDI_PEDIDO() {
			return this.DB_PEDI_PEDIDO;
		}

		public Boolean DB_PEDI_PEDIDOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PEDIDOIsKey() {
			return true;
		}

		public Integer DB_PEDI_PEDIDOLength() {
			return 10;
		}

		public Integer DB_PEDI_PEDIDOPrecision() {
			return 0;
		}

		public String DB_PEDI_PEDIDODefault() {

			return "";

		}

		public String DB_PEDI_PEDIDOComment() {

			return "";

		}

		public String DB_PEDI_PEDIDOPattern() {

			return "";

		}

		public String DB_PEDI_PEDIDOOriginalDbColumnName() {

			return "DB_PEDI_PEDIDO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Float DB_PEDI_QTDE_SOLIC;

		public Float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PEDI_PEDIDO == null) ? 0 : this.DB_PEDI_PEDIDO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.DB_PEDI_PEDIDO == null) {
				if (other.DB_PEDI_PEDIDO != null)
					return false;

			} else if (!this.DB_PEDI_PEDIDO.equals(other.DB_PEDI_PEDIDO))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.DB_PEDI_PEDIDO = this.DB_PEDI_PEDIDO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.DB_PEDI_PEDIDO = this.DB_PEDI_PEDIDO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDI_PEDIDO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDI_PEDIDO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDI_PEDIDO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDI_PEDIDO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DB_PEDI_PRODUTO = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.DB_PEDI_QTDE_SOLIC = null;
				} else {
					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DB_PEDI_PRODUTO = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DB_PEDI_QTDE_SOLIC = null;
				} else {
					this.DB_PEDI_QTDE_SOLIC = objectIn.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.DB_PEDI_PRODUTO, dos, oos);

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.DB_PEDI_PRODUTO, dos, objectOut);

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PEDI_PEDIDO=" + String.valueOf(DB_PEDI_PEDIDO));
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PEDI_PEDIDO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PEDIDO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PEDI_PEDIDO, other.DB_PEDI_PEDIDO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_3");
		org.slf4j.MDC.put("_subJobPid", "1d5BiN_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();

				/**
				 * [tAdvancedHash_row4 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row4", false);
				start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row4";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

				int tos_count_tAdvancedHash_row4 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row4", "tAdvancedHash_row4", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row4
				// source node:tDBInput_3 - inputs:(after_tHashInput_3) outputs:(row4,row4) |
				// target node:tAdvancedHash_row4 - inputs:(row4) outputs:()
				// linked node: tMap_4 - inputs:(row4,row14,row24) outputs:(Itens_ped,out1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				cLabel = "Input Produtos";

				int tos_count_tDBInput_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_3 = new StringBuilder();
							log4jParamters_tDBInput_3.append("Parameters:");
							log4jParamters_tDBInput_3.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("PORT" + " = " + "\"\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("DBNAME" + " = " + "\"mercanet\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:vMmThUNILf9ytlU/o8eLGL2AYK+Ay3YTeB2S0QrVnml5h7Z3+yMVr+QW")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("QUERY" + " = "
									+ "\"SELECT DB_PEDI_PEDIDO, 		DB_PEDI_PRODUTO, 		DB_PEDI_QTDE_SOLIC FROM	DB_PEDIDO_PROD\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_PEDIDO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_PRODUTO") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_QTDE_SOLIC") + "}]");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_3 - " + (log4jParamters_tDBInput_3));
						}
					}
					new BytesLimit65535_tDBInput_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_3", "Input Produtos", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_3 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_3 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_3 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_3, talendToDBArray_tDBInput_3);
				int nb_line_tDBInput_3 = 0;
				java.sql.Connection conn_tDBInput_3 = null;
				String driverClass_tDBInput_3 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_3 = java.lang.Class.forName(driverClass_tDBInput_3);
				String dbUser_tDBInput_3 = "analytics";

				final String decryptedPassword_tDBInput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:Kf7ezzNwkAdcRQgJ3F/OITlpuusImRsa2BP3JDrTyl6giNHGQF2ILqPJ");

				String dbPwd_tDBInput_3 = decryptedPassword_tDBInput_3;

				String port_tDBInput_3 = "";
				String dbname_tDBInput_3 = "mercanet";
				String url_tDBInput_3 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_3)) {
					url_tDBInput_3 += ":" + "";
				}
				if (!"".equals(dbname_tDBInput_3)) {
					url_tDBInput_3 += ";databaseName=" + "mercanet";
				}
				url_tDBInput_3 += ";appName=" + projectName + ";" + "instanceName=mercanet;";
				String dbschema_tDBInput_3 = "";

				log.debug("tDBInput_3 - Driver ClassName: " + driverClass_tDBInput_3 + ".");

				log.debug("tDBInput_3 - Connection attempt to '" + url_tDBInput_3 + "' with the username '"
						+ dbUser_tDBInput_3 + "'.");

				conn_tDBInput_3 = java.sql.DriverManager.getConnection(url_tDBInput_3, dbUser_tDBInput_3,
						dbPwd_tDBInput_3);
				log.debug("tDBInput_3 - Connection to '" + url_tDBInput_3 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_3 = conn_tDBInput_3.createStatement();

				String dbquery_tDBInput_3 = "SELECT DB_PEDI_PEDIDO,\n		DB_PEDI_PRODUTO,\n		DB_PEDI_QTDE_SOLIC\nFROM	DB_PEDIDO_PROD";

				log.debug("tDBInput_3 - Executing the query: '" + dbquery_tDBInput_3 + "'.");

				globalMap.put("tDBInput_3_QUERY", dbquery_tDBInput_3);

				java.sql.ResultSet rs_tDBInput_3 = null;

				try {
					rs_tDBInput_3 = stmt_tDBInput_3.executeQuery(dbquery_tDBInput_3);
					java.sql.ResultSetMetaData rsmd_tDBInput_3 = rs_tDBInput_3.getMetaData();
					int colQtyInRs_tDBInput_3 = rsmd_tDBInput_3.getColumnCount();

					String tmpContent_tDBInput_3 = null;

					log.debug("tDBInput_3 - Retrieving records from the database.");

					while (rs_tDBInput_3.next()) {
						nb_line_tDBInput_3++;

						if (colQtyInRs_tDBInput_3 < 1) {
							row4.DB_PEDI_PEDIDO = null;
						} else {

							row4.DB_PEDI_PEDIDO = rs_tDBInput_3.getInt(1);
							if (rs_tDBInput_3.wasNull()) {
								row4.DB_PEDI_PEDIDO = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 2) {
							row4.DB_PEDI_PRODUTO = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(2);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row4.DB_PEDI_PRODUTO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									row4.DB_PEDI_PRODUTO = tmpContent_tDBInput_3;
								}
							} else {
								row4.DB_PEDI_PRODUTO = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 3) {
							row4.DB_PEDI_QTDE_SOLIC = null;
						} else {

							row4.DB_PEDI_QTDE_SOLIC = rs_tDBInput_3.getFloat(3);
							if (rs_tDBInput_3.wasNull()) {
								row4.DB_PEDI_QTDE_SOLIC = null;
							}
						}

						log.debug("tDBInput_3 - Retrieving the record " + nb_line_tDBInput_3 + ".");

						/**
						 * [tDBInput_3 begin ] stop
						 */

						/**
						 * [tDBInput_3 main ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Input Produtos";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Input Produtos";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 main ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row4", "tDBInput_3", "Input Produtos", "tMSSqlInput", "tAdvancedHash_row4",
								"tAdvancedHash_row4", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
						}

						row4Struct row4_HashRow = new row4Struct();

						row4_HashRow.DB_PEDI_PEDIDO = row4.DB_PEDI_PEDIDO;

						row4_HashRow.DB_PEDI_PRODUTO = row4.DB_PEDI_PRODUTO;

						row4_HashRow.DB_PEDI_QTDE_SOLIC = row4.DB_PEDI_QTDE_SOLIC;

						tHash_Lookup_row4.put(row4_HashRow);

						tos_count_tAdvancedHash_row4++;

						/**
						 * [tAdvancedHash_row4 main ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row4";

						/**
						 * [tAdvancedHash_row4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Input Produtos";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Input Produtos";

					}
				} finally {
					if (rs_tDBInput_3 != null) {
						rs_tDBInput_3.close();
					}
					if (stmt_tDBInput_3 != null) {
						stmt_tDBInput_3.close();
					}
					if (conn_tDBInput_3 != null && !conn_tDBInput_3.isClosed()) {

						log.debug("tDBInput_3 - Closing the connection to the database.");

						conn_tDBInput_3.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_3 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_3_NB_LINE", nb_line_tDBInput_3);
				log.debug("tDBInput_3 - Retrieved records count: " + nb_line_tDBInput_3 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_3 - " + ("Done."));

				ok_Hash.put("tDBInput_3", true);
				end_Hash.put("tDBInput_3", System.currentTimeMillis());

				/**
				 * [tDBInput_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				tHash_Lookup_row4.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tDBInput_3", "Input Produtos", "tMSSqlInput", "tAdvancedHash_row4", "tAdvancedHash_row4",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row4", true);
				end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_3 finally ] start
				 */

				currentComponent = "tDBInput_3";

				cLabel = "Input Produtos";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row4 finally ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				/**
				 * [tAdvancedHash_row4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_3_SUBPROCESS_STATE", 1);
	}

	public static class row23Struct implements routines.system.IPersistableComparableLookupRow<row23Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_TBPGTO_COD;

		public Integer getDB_TBPGTO_COD() {
			return this.DB_TBPGTO_COD;
		}

		public Boolean DB_TBPGTO_CODIsNullable() {
			return true;
		}

		public Boolean DB_TBPGTO_CODIsKey() {
			return true;
		}

		public Integer DB_TBPGTO_CODLength() {
			return 10;
		}

		public Integer DB_TBPGTO_CODPrecision() {
			return 0;
		}

		public String DB_TBPGTO_CODDefault() {

			return "";

		}

		public String DB_TBPGTO_CODComment() {

			return "";

		}

		public String DB_TBPGTO_CODPattern() {

			return "";

		}

		public String DB_TBPGTO_CODOriginalDbColumnName() {

			return "DB_TBPGTO_COD";

		}

		public String DB_TBPGTO_ZTERM;

		public String getDB_TBPGTO_ZTERM() {
			return this.DB_TBPGTO_ZTERM;
		}

		public Boolean DB_TBPGTO_ZTERMIsNullable() {
			return true;
		}

		public Boolean DB_TBPGTO_ZTERMIsKey() {
			return false;
		}

		public Integer DB_TBPGTO_ZTERMLength() {
			return 10;
		}

		public Integer DB_TBPGTO_ZTERMPrecision() {
			return 0;
		}

		public String DB_TBPGTO_ZTERMDefault() {

			return null;

		}

		public String DB_TBPGTO_ZTERMComment() {

			return "";

		}

		public String DB_TBPGTO_ZTERMPattern() {

			return "";

		}

		public String DB_TBPGTO_ZTERMOriginalDbColumnName() {

			return "DB_TBPGTO_ZTERM";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_TBPGTO_COD == null) ? 0 : this.DB_TBPGTO_COD.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row23Struct other = (row23Struct) obj;

			if (this.DB_TBPGTO_COD == null) {
				if (other.DB_TBPGTO_COD != null)
					return false;

			} else if (!this.DB_TBPGTO_COD.equals(other.DB_TBPGTO_COD))

				return false;

			return true;
		}

		public void copyDataTo(row23Struct other) {

			other.DB_TBPGTO_COD = this.DB_TBPGTO_COD;
			other.DB_TBPGTO_ZTERM = this.DB_TBPGTO_ZTERM;

		}

		public void copyKeysDataTo(row23Struct other) {

			other.DB_TBPGTO_COD = this.DB_TBPGTO_COD;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_TBPGTO_COD = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_TBPGTO_COD = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_TBPGTO_COD, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_TBPGTO_COD, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DB_TBPGTO_ZTERM = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DB_TBPGTO_ZTERM = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.DB_TBPGTO_ZTERM, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.DB_TBPGTO_ZTERM, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_TBPGTO_COD=" + String.valueOf(DB_TBPGTO_COD));
			sb.append(",DB_TBPGTO_ZTERM=" + DB_TBPGTO_ZTERM);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_TBPGTO_COD == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_TBPGTO_COD);
			}

			sb.append("|");

			if (DB_TBPGTO_ZTERM == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_TBPGTO_ZTERM);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row23Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_TBPGTO_COD, other.DB_TBPGTO_COD);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_4");
		org.slf4j.MDC.put("_subJobPid", "ppIqrQ_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row23Struct row23 = new row23Struct();

				/**
				 * [tAdvancedHash_row23 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row23", false);
				start_Hash.put("tAdvancedHash_row23", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row23";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row23");

				int tos_count_tAdvancedHash_row23 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row23", "tAdvancedHash_row23", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row23
				// source node:tDBInput_4 - inputs:(after_tDBInput_1) outputs:(row23,row23) |
				// target node:tAdvancedHash_row23 - inputs:(row23) outputs:()
				// linked node: tMap_5 - inputs:(row1,row2,row23,row8)
				// outputs:(Pedido_complemento)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row23 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row23Struct> tHash_Lookup_row23 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row23Struct>getLookup(matchingModeEnum_row23);

				globalMap.put("tHash_Lookup_row23", tHash_Lookup_row23);

				/**
				 * [tAdvancedHash_row23 begin ] stop
				 */

				/**
				 * [tDBInput_4 begin ] start
				 */

				ok_Hash.put("tDBInput_4", false);
				start_Hash.put("tDBInput_4", System.currentTimeMillis());

				currentComponent = "tDBInput_4";

				cLabel = "Input Condicao pagamento";

				int tos_count_tDBInput_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_4 = new StringBuilder();
							log4jParamters_tDBInput_4.append("Parameters:");
							log4jParamters_tDBInput_4.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("PORT" + " = " + "\"\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("DBNAME" + " = " + "\"mercanet\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:d/2dJ/WgolUSGd0nqh2WLBBGNCIGQUu+ojKu+eI93slSSYAcYkNZy5Z+")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("TABLE" + " = " + "\"DB_TB_CPGTO\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("QUERY" + " = "
									+ "\"SELECT DB_TBPGTO_COD,  		DB_TBPGTO_ZTERM    FROM DB_TB_CPGTO\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_TBPGTO_COD") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_TBPGTO_ZTERM") + "}]");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_4.append(" | ");
							log4jParamters_tDBInput_4.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_4 - " + (log4jParamters_tDBInput_4));
						}
					}
					new BytesLimit65535_tDBInput_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_4", "Input Condicao pagamento", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_4 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_4 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_4 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_4, talendToDBArray_tDBInput_4);
				int nb_line_tDBInput_4 = 0;
				java.sql.Connection conn_tDBInput_4 = null;
				String driverClass_tDBInput_4 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_4 = java.lang.Class.forName(driverClass_tDBInput_4);
				String dbUser_tDBInput_4 = "analytics";

				final String decryptedPassword_tDBInput_4 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:1T5Zt70pXzaotra0hPyfIYkWVf5Hc3yDCU93sybVSBvO2PIS9q8iel5F");

				String dbPwd_tDBInput_4 = decryptedPassword_tDBInput_4;

				String port_tDBInput_4 = "";
				String dbname_tDBInput_4 = "mercanet";
				String url_tDBInput_4 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_4)) {
					url_tDBInput_4 += ":" + "";
				}
				if (!"".equals(dbname_tDBInput_4)) {
					url_tDBInput_4 += ";databaseName=" + "mercanet";
				}
				url_tDBInput_4 += ";appName=" + projectName + ";" + "instanceName=mercanet;";
				String dbschema_tDBInput_4 = "";

				log.debug("tDBInput_4 - Driver ClassName: " + driverClass_tDBInput_4 + ".");

				log.debug("tDBInput_4 - Connection attempt to '" + url_tDBInput_4 + "' with the username '"
						+ dbUser_tDBInput_4 + "'.");

				conn_tDBInput_4 = java.sql.DriverManager.getConnection(url_tDBInput_4, dbUser_tDBInput_4,
						dbPwd_tDBInput_4);
				log.debug("tDBInput_4 - Connection to '" + url_tDBInput_4 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_4 = conn_tDBInput_4.createStatement();

				String dbquery_tDBInput_4 = "SELECT DB_TBPGTO_COD,\n		DB_TBPGTO_ZTERM\n  FROM DB_TB_CPGTO";

				log.debug("tDBInput_4 - Executing the query: '" + dbquery_tDBInput_4 + "'.");

				globalMap.put("tDBInput_4_QUERY", dbquery_tDBInput_4);

				java.sql.ResultSet rs_tDBInput_4 = null;

				try {
					rs_tDBInput_4 = stmt_tDBInput_4.executeQuery(dbquery_tDBInput_4);
					java.sql.ResultSetMetaData rsmd_tDBInput_4 = rs_tDBInput_4.getMetaData();
					int colQtyInRs_tDBInput_4 = rsmd_tDBInput_4.getColumnCount();

					String tmpContent_tDBInput_4 = null;

					log.debug("tDBInput_4 - Retrieving records from the database.");

					while (rs_tDBInput_4.next()) {
						nb_line_tDBInput_4++;

						if (colQtyInRs_tDBInput_4 < 1) {
							row23.DB_TBPGTO_COD = null;
						} else {

							row23.DB_TBPGTO_COD = rs_tDBInput_4.getInt(1);
							if (rs_tDBInput_4.wasNull()) {
								row23.DB_TBPGTO_COD = null;
							}
						}
						if (colQtyInRs_tDBInput_4 < 2) {
							row23.DB_TBPGTO_ZTERM = null;
						} else {

							tmpContent_tDBInput_4 = rs_tDBInput_4.getString(2);
							if (tmpContent_tDBInput_4 != null) {
								if (talendToDBList_tDBInput_4.contains(
										rsmd_tDBInput_4.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row23.DB_TBPGTO_ZTERM = FormatterUtils.formatUnwithE(tmpContent_tDBInput_4);
								} else {
									row23.DB_TBPGTO_ZTERM = tmpContent_tDBInput_4;
								}
							} else {
								row23.DB_TBPGTO_ZTERM = null;
							}
						}

						log.debug("tDBInput_4 - Retrieving the record " + nb_line_tDBInput_4 + ".");

						/**
						 * [tDBInput_4 begin ] stop
						 */

						/**
						 * [tDBInput_4 main ] start
						 */

						currentComponent = "tDBInput_4";

						cLabel = "Input Condicao pagamento";

						tos_count_tDBInput_4++;

						/**
						 * [tDBInput_4 main ] stop
						 */

						/**
						 * [tDBInput_4 process_data_begin ] start
						 */

						currentComponent = "tDBInput_4";

						cLabel = "Input Condicao pagamento";

						/**
						 * [tDBInput_4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row23 main ] start
						 */

						currentComponent = "tAdvancedHash_row23";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row23", "tDBInput_4", "Input Condicao pagamento", "tMSSqlInput",
								"tAdvancedHash_row23", "tAdvancedHash_row23", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row23 - " + (row23 == null ? "" : row23.toLogString()));
						}

						row23Struct row23_HashRow = new row23Struct();

						row23_HashRow.DB_TBPGTO_COD = row23.DB_TBPGTO_COD;

						row23_HashRow.DB_TBPGTO_ZTERM = row23.DB_TBPGTO_ZTERM;

						tHash_Lookup_row23.put(row23_HashRow);

						tos_count_tAdvancedHash_row23++;

						/**
						 * [tAdvancedHash_row23 main ] stop
						 */

						/**
						 * [tAdvancedHash_row23 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row23";

						/**
						 * [tAdvancedHash_row23 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row23 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row23";

						/**
						 * [tAdvancedHash_row23 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 process_data_end ] start
						 */

						currentComponent = "tDBInput_4";

						cLabel = "Input Condicao pagamento";

						/**
						 * [tDBInput_4 process_data_end ] stop
						 */

						/**
						 * [tDBInput_4 end ] start
						 */

						currentComponent = "tDBInput_4";

						cLabel = "Input Condicao pagamento";

					}
				} finally {
					if (rs_tDBInput_4 != null) {
						rs_tDBInput_4.close();
					}
					if (stmt_tDBInput_4 != null) {
						stmt_tDBInput_4.close();
					}
					if (conn_tDBInput_4 != null && !conn_tDBInput_4.isClosed()) {

						log.debug("tDBInput_4 - Closing the connection to the database.");

						conn_tDBInput_4.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_4 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_4_NB_LINE", nb_line_tDBInput_4);
				log.debug("tDBInput_4 - Retrieved records count: " + nb_line_tDBInput_4 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_4 - " + ("Done."));

				ok_Hash.put("tDBInput_4", true);
				end_Hash.put("tDBInput_4", System.currentTimeMillis());

				/**
				 * [tDBInput_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row23 end ] start
				 */

				currentComponent = "tAdvancedHash_row23";

				tHash_Lookup_row23.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row23", 2, 0,
						"tDBInput_4", "Input Condicao pagamento", "tMSSqlInput", "tAdvancedHash_row23",
						"tAdvancedHash_row23", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row23", true);
				end_Hash.put("tAdvancedHash_row23", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row23 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_4 finally ] start
				 */

				currentComponent = "tDBInput_4";

				cLabel = "Input Condicao pagamento";

				/**
				 * [tDBInput_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row23 finally ] start
				 */

				currentComponent = "tAdvancedHash_row23";

				/**
				 * [tAdvancedHash_row23 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_4_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Float DB_PEDI_QTDE_SOLIC;

		public Float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Float DB_PEDI_PRECO_LIQ;

		public Float getDB_PEDI_PRECO_LIQ() {
			return this.DB_PEDI_PRECO_LIQ;
		}

		public Boolean DB_PEDI_PRECO_LIQIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRECO_LIQIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRECO_LIQLength() {
			return 7;
		}

		public Integer DB_PEDI_PRECO_LIQPrecision() {
			return 0;
		}

		public String DB_PEDI_PRECO_LIQDefault() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQComment() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQPattern() {

			return "";

		}

		public String DB_PEDI_PRECO_LIQOriginalDbColumnName() {

			return "DB_PEDI_PRECO_LIQ";

		}

		public Integer DB_PEDI_PEDIDO;

		public Integer getDB_PEDI_PEDIDO() {
			return this.DB_PEDI_PEDIDO;
		}

		public Boolean DB_PEDI_PEDIDOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PEDIDOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PEDIDOLength() {
			return 10;
		}

		public Integer DB_PEDI_PEDIDOPrecision() {
			return 0;
		}

		public String DB_PEDI_PEDIDODefault() {

			return "";

		}

		public String DB_PEDI_PEDIDOComment() {

			return "";

		}

		public String DB_PEDI_PEDIDOPattern() {

			return "";

		}

		public String DB_PEDI_PEDIDOOriginalDbColumnName() {

			return "DB_PEDI_PEDIDO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PEDI_PEDIDO == null) ? 0 : this.DB_PEDI_PEDIDO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row8Struct other = (row8Struct) obj;

			if (this.DB_PEDI_PEDIDO == null) {
				if (other.DB_PEDI_PEDIDO != null)
					return false;

			} else if (!this.DB_PEDI_PEDIDO.equals(other.DB_PEDI_PEDIDO))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.DB_PEDI_PRECO_LIQ = this.DB_PEDI_PRECO_LIQ;
			other.DB_PEDI_PEDIDO = this.DB_PEDI_PEDIDO;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.DB_PEDI_PEDIDO = this.DB_PEDI_PEDIDO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDI_PEDIDO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PEDI_PEDIDO = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDI_PEDIDO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PEDI_PEDIDO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				length = dis.readByte();
				if (length == -1) {
					this.DB_PEDI_QTDE_SOLIC = null;
				} else {
					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.DB_PEDI_PRECO_LIQ = null;
				} else {
					this.DB_PEDI_PRECO_LIQ = dis.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				length = objectIn.readByte();
				if (length == -1) {
					this.DB_PEDI_QTDE_SOLIC = null;
				} else {
					this.DB_PEDI_QTDE_SOLIC = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.DB_PEDI_PRECO_LIQ = null;
				} else {
					this.DB_PEDI_PRECO_LIQ = objectIn.readFloat();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				if (this.DB_PEDI_PRECO_LIQ == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.DB_PEDI_QTDE_SOLIC == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.DB_PEDI_QTDE_SOLIC);
				}

				if (this.DB_PEDI_PRECO_LIQ == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.DB_PEDI_PRECO_LIQ);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",DB_PEDI_PRECO_LIQ=" + String.valueOf(DB_PEDI_PRECO_LIQ));
			sb.append(",DB_PEDI_PEDIDO=" + String.valueOf(DB_PEDI_PEDIDO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (DB_PEDI_PRECO_LIQ == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRECO_LIQ);
			}

			sb.append("|");

			if (DB_PEDI_PEDIDO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PEDIDO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PEDI_PEDIDO, other.DB_PEDI_PEDIDO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_5");
		org.slf4j.MDC.put("_subJobPid", "tDdLy6_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row8Struct row8 = new row8Struct();

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row8", false);
				start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row8";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tAdvancedHash_row8 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row8", "tAdvancedHash_row8", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row8
				// source node:tDBInput_5 - inputs:(after_tDBInput_1) outputs:(row8,row8) |
				// target node:tAdvancedHash_row8 - inputs:(row8) outputs:()
				// linked node: tMap_5 - inputs:(row1,row2,row23,row8)
				// outputs:(Pedido_complemento)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
				 */

				/**
				 * [tDBInput_5 begin ] start
				 */

				ok_Hash.put("tDBInput_5", false);
				start_Hash.put("tDBInput_5", System.currentTimeMillis());

				currentComponent = "tDBInput_5";

				cLabel = "Input Produtos";

				int tos_count_tDBInput_5 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_5 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_5 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_5 = new StringBuilder();
							log4jParamters_tDBInput_5.append("Parameters:");
							log4jParamters_tDBInput_5.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("PORT" + " = " + "\"\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("DBNAME" + " = " + "\"mercanet\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:KEvGvVfSzr/CMZuFeTzKtSu70rX4Ob1qmSId5z1H9x1NTM3cFCNhTL6Y")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("QUERY" + " = "
									+ "\"SELECT DB_PEDI_QTDE_SOLIC,  	   DB_PEDI_PRECO_LIQ,  	   DB_PEDI_PEDIDO    FROM DB_PEDIDO_PROD   WHERE  DB_PEDI_DTPREVENTO >= CAST(GETDATE() -90 AS DATE)\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_QTDE_SOLIC") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_PRECO_LIQ") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_PEDIDO") + "}]");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_5.append(" | ");
							log4jParamters_tDBInput_5.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_5.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_5 - " + (log4jParamters_tDBInput_5));
						}
					}
					new BytesLimit65535_tDBInput_5().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_5", "Input Produtos", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_5 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_5 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_5 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_5, talendToDBArray_tDBInput_5);
				int nb_line_tDBInput_5 = 0;
				java.sql.Connection conn_tDBInput_5 = null;
				String driverClass_tDBInput_5 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_5 = java.lang.Class.forName(driverClass_tDBInput_5);
				String dbUser_tDBInput_5 = "analytics";

				final String decryptedPassword_tDBInput_5 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:ilCBr0zAKHApIhkBqr6G6eLJy5Ex44DPihTJHi64RoJk2Eh2GU7MUpcO");

				String dbPwd_tDBInput_5 = decryptedPassword_tDBInput_5;

				String port_tDBInput_5 = "";
				String dbname_tDBInput_5 = "mercanet";
				String url_tDBInput_5 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_5)) {
					url_tDBInput_5 += ":" + "";
				}
				if (!"".equals(dbname_tDBInput_5)) {
					url_tDBInput_5 += ";databaseName=" + "mercanet";
				}
				url_tDBInput_5 += ";appName=" + projectName + ";" + "instanceName=mercanet;";
				String dbschema_tDBInput_5 = "";

				log.debug("tDBInput_5 - Driver ClassName: " + driverClass_tDBInput_5 + ".");

				log.debug("tDBInput_5 - Connection attempt to '" + url_tDBInput_5 + "' with the username '"
						+ dbUser_tDBInput_5 + "'.");

				conn_tDBInput_5 = java.sql.DriverManager.getConnection(url_tDBInput_5, dbUser_tDBInput_5,
						dbPwd_tDBInput_5);
				log.debug("tDBInput_5 - Connection to '" + url_tDBInput_5 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_5 = conn_tDBInput_5.createStatement();

				String dbquery_tDBInput_5 = "SELECT DB_PEDI_QTDE_SOLIC,\n	   DB_PEDI_PRECO_LIQ,\n	   DB_PEDI_PEDIDO\n  FROM DB_PEDIDO_PROD\n WHERE  DB_PEDI_DTPREVEN"
						+ "TO >= CAST(GETDATE() -90 AS DATE)";

				log.debug("tDBInput_5 - Executing the query: '" + dbquery_tDBInput_5 + "'.");

				globalMap.put("tDBInput_5_QUERY", dbquery_tDBInput_5);

				java.sql.ResultSet rs_tDBInput_5 = null;

				try {
					rs_tDBInput_5 = stmt_tDBInput_5.executeQuery(dbquery_tDBInput_5);
					java.sql.ResultSetMetaData rsmd_tDBInput_5 = rs_tDBInput_5.getMetaData();
					int colQtyInRs_tDBInput_5 = rsmd_tDBInput_5.getColumnCount();

					String tmpContent_tDBInput_5 = null;

					log.debug("tDBInput_5 - Retrieving records from the database.");

					while (rs_tDBInput_5.next()) {
						nb_line_tDBInput_5++;

						if (colQtyInRs_tDBInput_5 < 1) {
							row8.DB_PEDI_QTDE_SOLIC = null;
						} else {

							row8.DB_PEDI_QTDE_SOLIC = rs_tDBInput_5.getFloat(1);
							if (rs_tDBInput_5.wasNull()) {
								row8.DB_PEDI_QTDE_SOLIC = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 2) {
							row8.DB_PEDI_PRECO_LIQ = null;
						} else {

							row8.DB_PEDI_PRECO_LIQ = rs_tDBInput_5.getFloat(2);
							if (rs_tDBInput_5.wasNull()) {
								row8.DB_PEDI_PRECO_LIQ = null;
							}
						}
						if (colQtyInRs_tDBInput_5 < 3) {
							row8.DB_PEDI_PEDIDO = null;
						} else {

							row8.DB_PEDI_PEDIDO = rs_tDBInput_5.getInt(3);
							if (rs_tDBInput_5.wasNull()) {
								row8.DB_PEDI_PEDIDO = null;
							}
						}

						log.debug("tDBInput_5 - Retrieving the record " + nb_line_tDBInput_5 + ".");

						/**
						 * [tDBInput_5 begin ] stop
						 */

						/**
						 * [tDBInput_5 main ] start
						 */

						currentComponent = "tDBInput_5";

						cLabel = "Input Produtos";

						tos_count_tDBInput_5++;

						/**
						 * [tDBInput_5 main ] stop
						 */

						/**
						 * [tDBInput_5 process_data_begin ] start
						 */

						currentComponent = "tDBInput_5";

						cLabel = "Input Produtos";

						/**
						 * [tDBInput_5 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 main ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row8", "tDBInput_5", "Input Produtos", "tMSSqlInput", "tAdvancedHash_row8",
								"tAdvancedHash_row8", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
						}

						row8Struct row8_HashRow = new row8Struct();

						row8_HashRow.DB_PEDI_QTDE_SOLIC = row8.DB_PEDI_QTDE_SOLIC;

						row8_HashRow.DB_PEDI_PRECO_LIQ = row8.DB_PEDI_PRECO_LIQ;

						row8_HashRow.DB_PEDI_PEDIDO = row8.DB_PEDI_PEDIDO;

						tHash_Lookup_row8.put(row8_HashRow);

						tos_count_tAdvancedHash_row8++;

						/**
						 * [tAdvancedHash_row8 main ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row8 process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_row8";

						/**
						 * [tAdvancedHash_row8 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 process_data_end ] start
						 */

						currentComponent = "tDBInput_5";

						cLabel = "Input Produtos";

						/**
						 * [tDBInput_5 process_data_end ] stop
						 */

						/**
						 * [tDBInput_5 end ] start
						 */

						currentComponent = "tDBInput_5";

						cLabel = "Input Produtos";

					}
				} finally {
					if (rs_tDBInput_5 != null) {
						rs_tDBInput_5.close();
					}
					if (stmt_tDBInput_5 != null) {
						stmt_tDBInput_5.close();
					}
					if (conn_tDBInput_5 != null && !conn_tDBInput_5.isClosed()) {

						log.debug("tDBInput_5 - Closing the connection to the database.");

						conn_tDBInput_5.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_5 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_5_NB_LINE", nb_line_tDBInput_5);
				log.debug("tDBInput_5 - Retrieved records count: " + nb_line_tDBInput_5 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_5 - " + ("Done."));

				ok_Hash.put("tDBInput_5", true);
				end_Hash.put("tDBInput_5", System.currentTimeMillis());

				/**
				 * [tDBInput_5 end ] stop
				 */

				/**
				 * [tAdvancedHash_row8 end ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				tHash_Lookup_row8.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tDBInput_5", "Input Produtos", "tMSSqlInput", "tAdvancedHash_row8", "tAdvancedHash_row8",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_5 finally ] start
				 */

				currentComponent = "tDBInput_5";

				cLabel = "Input Produtos";

				/**
				 * [tDBInput_5 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				/**
				 * [tAdvancedHash_row8 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_5_SUBPROCESS_STATE", 1);
	}

	public static class row13Struct implements routines.system.IPersistableComparableLookupRow<row13Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		public String C9_FILIAL;

		public String getC9_FILIAL() {
			return this.C9_FILIAL;
		}

		public Boolean C9_FILIALIsNullable() {
			return true;
		}

		public Boolean C9_FILIALIsKey() {
			return false;
		}

		public Integer C9_FILIALLength() {
			return 2;
		}

		public Integer C9_FILIALPrecision() {
			return 0;
		}

		public String C9_FILIALDefault() {

			return null;

		}

		public String C9_FILIALComment() {

			return "";

		}

		public String C9_FILIALPattern() {

			return "";

		}

		public String C9_FILIALOriginalDbColumnName() {

			return "C9_FILIAL";

		}

		public String C9_LOCAL;

		public String getC9_LOCAL() {
			return this.C9_LOCAL;
		}

		public Boolean C9_LOCALIsNullable() {
			return true;
		}

		public Boolean C9_LOCALIsKey() {
			return false;
		}

		public Integer C9_LOCALLength() {
			return 2;
		}

		public Integer C9_LOCALPrecision() {
			return 0;
		}

		public String C9_LOCALDefault() {

			return null;

		}

		public String C9_LOCALComment() {

			return "";

		}

		public String C9_LOCALPattern() {

			return "";

		}

		public String C9_LOCALOriginalDbColumnName() {

			return "C9_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		public String C9_NFISCAL;

		public String getC9_NFISCAL() {
			return this.C9_NFISCAL;
		}

		public Boolean C9_NFISCALIsNullable() {
			return true;
		}

		public Boolean C9_NFISCALIsKey() {
			return false;
		}

		public Integer C9_NFISCALLength() {
			return 9;
		}

		public Integer C9_NFISCALPrecision() {
			return 0;
		}

		public String C9_NFISCALDefault() {

			return null;

		}

		public String C9_NFISCALComment() {

			return "";

		}

		public String C9_NFISCALPattern() {

			return "";

		}

		public String C9_NFISCALOriginalDbColumnName() {

			return "C9_NFISCAL";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.C9_PRODUTO == null) ? 0 : this.C9_PRODUTO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row13Struct other = (row13Struct) obj;

			if (this.C9_PRODUTO == null) {
				if (other.C9_PRODUTO != null)
					return false;

			} else if (!this.C9_PRODUTO.equals(other.C9_PRODUTO))

				return false;

			return true;
		}

		public void copyDataTo(row13Struct other) {

			other.C9_PRODUTO = this.C9_PRODUTO;
			other.C9_QTDLIB = this.C9_QTDLIB;
			other.C9_FILIAL = this.C9_FILIAL;
			other.C9_LOCAL = this.C9_LOCAL;
			other.D_E_L_E_T_ = this.D_E_L_E_T_;
			other.C9_NFISCAL = this.C9_NFISCAL;

		}

		public void copyKeysDataTo(row13Struct other) {

			other.C9_PRODUTO = this.C9_PRODUTO;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				length = dis.readByte();
				if (length == -1) {
					this.C9_QTDLIB = null;
				} else {
					this.C9_QTDLIB = dis.readDouble();
				}

				this.C9_FILIAL = readString(dis, ois);

				this.C9_LOCAL = readString(dis, ois);

				this.D_E_L_E_T_ = readString(dis, ois);

				this.C9_NFISCAL = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				length = objectIn.readByte();
				if (length == -1) {
					this.C9_QTDLIB = null;
				} else {
					this.C9_QTDLIB = objectIn.readDouble();
				}

				this.C9_FILIAL = readString(dis, objectIn);

				this.C9_LOCAL = readString(dis, objectIn);

				this.D_E_L_E_T_ = readString(dis, objectIn);

				this.C9_NFISCAL = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				writeString(this.C9_FILIAL, dos, oos);

				writeString(this.C9_LOCAL, dos, oos);

				writeString(this.D_E_L_E_T_, dos, oos);

				writeString(this.C9_NFISCAL, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.C9_QTDLIB == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.C9_QTDLIB);
				}

				writeString(this.C9_FILIAL, dos, objectOut);

				writeString(this.C9_LOCAL, dos, objectOut);

				writeString(this.D_E_L_E_T_, dos, objectOut);

				writeString(this.C9_NFISCAL, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append(",C9_FILIAL=" + C9_FILIAL);
			sb.append(",C9_LOCAL=" + C9_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append(",C9_NFISCAL=" + C9_NFISCAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			if (C9_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_FILIAL);
			}

			sb.append("|");

			if (C9_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			if (C9_NFISCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_NFISCAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row13Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.C9_PRODUTO, other.C9_PRODUTO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_2
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_2> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		public String C9_FILIAL;

		public String getC9_FILIAL() {
			return this.C9_FILIAL;
		}

		public Boolean C9_FILIALIsNullable() {
			return true;
		}

		public Boolean C9_FILIALIsKey() {
			return false;
		}

		public Integer C9_FILIALLength() {
			return 2;
		}

		public Integer C9_FILIALPrecision() {
			return 0;
		}

		public String C9_FILIALDefault() {

			return null;

		}

		public String C9_FILIALComment() {

			return "";

		}

		public String C9_FILIALPattern() {

			return "";

		}

		public String C9_FILIALOriginalDbColumnName() {

			return "C9_FILIAL";

		}

		public String C9_LOCAL;

		public String getC9_LOCAL() {
			return this.C9_LOCAL;
		}

		public Boolean C9_LOCALIsNullable() {
			return true;
		}

		public Boolean C9_LOCALIsKey() {
			return false;
		}

		public Integer C9_LOCALLength() {
			return 2;
		}

		public Integer C9_LOCALPrecision() {
			return 0;
		}

		public String C9_LOCALDefault() {

			return null;

		}

		public String C9_LOCALComment() {

			return "";

		}

		public String C9_LOCALPattern() {

			return "";

		}

		public String C9_LOCALOriginalDbColumnName() {

			return "C9_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		public String C9_NFISCAL;

		public String getC9_NFISCAL() {
			return this.C9_NFISCAL;
		}

		public Boolean C9_NFISCALIsNullable() {
			return true;
		}

		public Boolean C9_NFISCALIsKey() {
			return false;
		}

		public Integer C9_NFISCALLength() {
			return 9;
		}

		public Integer C9_NFISCALPrecision() {
			return 0;
		}

		public String C9_NFISCALDefault() {

			return null;

		}

		public String C9_NFISCALComment() {

			return "";

		}

		public String C9_NFISCALPattern() {

			return "";

		}

		public String C9_NFISCALOriginalDbColumnName() {

			return "C9_NFISCAL";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append(",C9_FILIAL=" + C9_FILIAL);
			sb.append(",C9_LOCAL=" + C9_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append(",C9_NFISCAL=" + C9_NFISCAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			if (C9_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_FILIAL);
			}

			sb.append("|");

			if (C9_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			if (C9_NFISCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_NFISCAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_2 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row12Struct implements routines.system.IPersistableRow<row12Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		public String C9_FILIAL;

		public String getC9_FILIAL() {
			return this.C9_FILIAL;
		}

		public Boolean C9_FILIALIsNullable() {
			return true;
		}

		public Boolean C9_FILIALIsKey() {
			return false;
		}

		public Integer C9_FILIALLength() {
			return 2;
		}

		public Integer C9_FILIALPrecision() {
			return 0;
		}

		public String C9_FILIALDefault() {

			return null;

		}

		public String C9_FILIALComment() {

			return "";

		}

		public String C9_FILIALPattern() {

			return "";

		}

		public String C9_FILIALOriginalDbColumnName() {

			return "C9_FILIAL";

		}

		public String C9_LOCAL;

		public String getC9_LOCAL() {
			return this.C9_LOCAL;
		}

		public Boolean C9_LOCALIsNullable() {
			return true;
		}

		public Boolean C9_LOCALIsKey() {
			return false;
		}

		public Integer C9_LOCALLength() {
			return 2;
		}

		public Integer C9_LOCALPrecision() {
			return 0;
		}

		public String C9_LOCALDefault() {

			return null;

		}

		public String C9_LOCALComment() {

			return "";

		}

		public String C9_LOCALPattern() {

			return "";

		}

		public String C9_LOCALOriginalDbColumnName() {

			return "C9_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		public String C9_NFISCAL;

		public String getC9_NFISCAL() {
			return this.C9_NFISCAL;
		}

		public Boolean C9_NFISCALIsNullable() {
			return true;
		}

		public Boolean C9_NFISCALIsKey() {
			return false;
		}

		public Integer C9_NFISCALLength() {
			return 9;
		}

		public Integer C9_NFISCALPrecision() {
			return 0;
		}

		public String C9_NFISCALDefault() {

			return null;

		}

		public String C9_NFISCALComment() {

			return "";

		}

		public String C9_NFISCALPattern() {

			return "";

		}

		public String C9_NFISCALOriginalDbColumnName() {

			return "C9_NFISCAL";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append(",C9_FILIAL=" + C9_FILIAL);
			sb.append(",C9_LOCAL=" + C9_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append(",C9_NFISCAL=" + C9_NFISCAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			if (C9_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_FILIAL);
			}

			sb.append("|");

			if (C9_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			if (C9_NFISCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_NFISCAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		public String C9_FILIAL;

		public String getC9_FILIAL() {
			return this.C9_FILIAL;
		}

		public Boolean C9_FILIALIsNullable() {
			return true;
		}

		public Boolean C9_FILIALIsKey() {
			return false;
		}

		public Integer C9_FILIALLength() {
			return 2;
		}

		public Integer C9_FILIALPrecision() {
			return 0;
		}

		public String C9_FILIALDefault() {

			return null;

		}

		public String C9_FILIALComment() {

			return "";

		}

		public String C9_FILIALPattern() {

			return "";

		}

		public String C9_FILIALOriginalDbColumnName() {

			return "C9_FILIAL";

		}

		public String C9_LOCAL;

		public String getC9_LOCAL() {
			return this.C9_LOCAL;
		}

		public Boolean C9_LOCALIsNullable() {
			return true;
		}

		public Boolean C9_LOCALIsKey() {
			return false;
		}

		public Integer C9_LOCALLength() {
			return 2;
		}

		public Integer C9_LOCALPrecision() {
			return 0;
		}

		public String C9_LOCALDefault() {

			return null;

		}

		public String C9_LOCALComment() {

			return "";

		}

		public String C9_LOCALPattern() {

			return "";

		}

		public String C9_LOCALOriginalDbColumnName() {

			return "C9_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		public String C9_NFISCAL;

		public String getC9_NFISCAL() {
			return this.C9_NFISCAL;
		}

		public Boolean C9_NFISCALIsNullable() {
			return true;
		}

		public Boolean C9_NFISCALIsKey() {
			return false;
		}

		public Integer C9_NFISCALLength() {
			return 9;
		}

		public Integer C9_NFISCALPrecision() {
			return 0;
		}

		public String C9_NFISCALDefault() {

			return null;

		}

		public String C9_NFISCALComment() {

			return "";

		}

		public String C9_NFISCALPattern() {

			return "";

		}

		public String C9_NFISCALOriginalDbColumnName() {

			return "C9_NFISCAL";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

					this.C9_FILIAL = readString(dis);

					this.C9_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

					this.C9_NFISCAL = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

				// String

				writeString(this.C9_FILIAL, dos);

				// String

				writeString(this.C9_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

				// String

				writeString(this.C9_NFISCAL, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append(",C9_FILIAL=" + C9_FILIAL);
			sb.append(",C9_LOCAL=" + C9_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append(",C9_NFISCAL=" + C9_NFISCAL);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			if (C9_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_FILIAL);
			}

			sb.append("|");

			if (C9_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			if (C9_NFISCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_NFISCAL);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_6");
		org.slf4j.MDC.put("_subJobPid", "zW2cd7_" + subJobPidCounter.getAndIncrement());

		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row11Struct row11 = new row11Struct();
				row12Struct row12 = new row12Struct();
				row13Struct row13 = new row13Struct();

				/**
				 * [tAggregateRow_2_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_2_AGGOUT", false);
				start_Hash.put("tAggregateRow_2_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_2";

				currentComponent = "tAggregateRow_2_AGGOUT";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row12");

				int tos_count_tAggregateRow_2_AGGOUT = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_2_AGGOUT - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_2_AGGOUT {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_2_AGGOUT = new StringBuilder();
							log4jParamters_tAggregateRow_2_AGGOUT.append("Parameters:");
							log4jParamters_tAggregateRow_2_AGGOUT.append("DESTINATION" + " = " + "tAggregateRow_2");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("C9_PRODUTO") + ", INPUT_COLUMN=" + ("C9_PRODUTO") + "}]");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("C9_QTDLIB") + ", INPUT_COLUMN=" + ("C9_QTDLIB") + ", IGNORE_NULL=" + ("false")
									+ ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_2_AGGOUT.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_2_AGGOUT.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_2_AGGOUT - " + (log4jParamters_tAggregateRow_2_AGGOUT));
						}
					}
					new BytesLimit65535_tAggregateRow_2_AGGOUT().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_2_AGGOUT", "tAggregateRow_2_AGGOUT", "tAggregateOut");
					talendJobLogProcess(globalMap);
				}

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_2 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_2 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_2 utilClass_tAggregateRow_2 = new UtilClass_tAggregateRow_2();

				class AggOperationStruct_tAggregateRow_2 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String C9_PRODUTO;
					BigDecimal C9_QTDLIB_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.C9_PRODUTO == null) ? 0 : this.C9_PRODUTO.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_2 other = (AggOperationStruct_tAggregateRow_2) obj;

						if (this.C9_PRODUTO == null) {
							if (other.C9_PRODUTO != null)
								return false;
						} else if (!this.C9_PRODUTO.equals(other.C9_PRODUTO))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_2 operation_result_tAggregateRow_2 = null;
				AggOperationStruct_tAggregateRow_2 operation_finder_tAggregateRow_2 = new AggOperationStruct_tAggregateRow_2();
				java.util.Map<AggOperationStruct_tAggregateRow_2, AggOperationStruct_tAggregateRow_2> hash_tAggregateRow_2 = new java.util.HashMap<AggOperationStruct_tAggregateRow_2, AggOperationStruct_tAggregateRow_2>();

				/**
				 * [tAggregateRow_2_AGGOUT begin ] stop
				 */

				/**
				 * [tFilterRow_20 begin ] start
				 */

				ok_Hash.put("tFilterRow_20", false);
				start_Hash.put("tFilterRow_20", System.currentTimeMillis());

				currentComponent = "tFilterRow_20";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row11");

				int tos_count_tFilterRow_20 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_20 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_20 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_20 = new StringBuilder();
							log4jParamters_tFilterRow_20.append("Parameters:");
							log4jParamters_tFilterRow_20.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_20.append(" | ");
							log4jParamters_tFilterRow_20.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==")
									+ ", RVALUE=" + ("\"02\"") + ", INPUT_COLUMN=" + ("C9_FILIAL") + ", FUNCTION="
									+ ("") + "}, {OPERATOR=" + ("==") + ", RVALUE=" + ("\"23\"") + ", INPUT_COLUMN="
									+ ("C9_LOCAL") + ", FUNCTION=" + ("") + "}, {OPERATOR=" + ("!=") + ", RVALUE="
									+ ("\"*\"") + ", INPUT_COLUMN=" + ("D_E_L_E_T_") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_20.append(" | ");
							log4jParamters_tFilterRow_20.append("USE_ADVANCED" + " = " + "true");
							log4jParamters_tFilterRow_20.append(" | ");
							log4jParamters_tFilterRow_20.append("ADVANCED_COND" + " = "
									+ "// code sample : use input_row to define the condition. // input_row.columnName1.equals(\"foo\") ||!(input_row.columnName2.equals(\"bar\")) // replace the following expression by your own filter condition  row11.C9_NFISCAL.trim().isEmpty() 			");
							log4jParamters_tFilterRow_20.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_20 - " + (log4jParamters_tFilterRow_20));
						}
					}
					new BytesLimit65535_tFilterRow_20().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_20", "tFilterRow_20", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_20 = 0;
				int nb_line_ok_tFilterRow_20 = 0;
				int nb_line_reject_tFilterRow_20 = 0;

				class Operator_tFilterRow_20 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_20(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_20 begin ] stop
				 */

				/**
				 * [tDBInput_6 begin ] start
				 */

				ok_Hash.put("tDBInput_6", false);
				start_Hash.put("tDBInput_6", System.currentTimeMillis());

				currentComponent = "tDBInput_6";

				cLabel = "Input Empenho";

				int tos_count_tDBInput_6 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_6 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_6 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_6 = new StringBuilder();
							log4jParamters_tDBInput_6.append("Parameters:");
							log4jParamters_tDBInput_6.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("DBNAME" + " = " + "\"TOTVS\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:uCHe1zBCrgmjIC6JOA/8Jos+9UdkFqThjawPbisrg7xn0uuYxYRECYAO")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("QUERY" + " = "
									+ "\"SELECT C9_PRODUTO,  				  C9_QTDLIB,  	C9_FILIAL,  	C9_LOCAL,     	 D_E_L_E_T_,  	 C9_NFISCAL     FROM SC9010  WHERE C9_DATALIB >= GETDATE() - 120\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("C9_PRODUTO") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("C9_QTDLIB") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("C9_FILIAL")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("C9_LOCAL") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("D_E_L_E_T_") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("C9_NFISCAL") + "}]");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_6.append(" | ");
							log4jParamters_tDBInput_6.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_6.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_6 - " + (log4jParamters_tDBInput_6));
						}
					}
					new BytesLimit65535_tDBInput_6().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_6", "Input Empenho", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_6 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_6 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_6 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_6, talendToDBArray_tDBInput_6);
				int nb_line_tDBInput_6 = 0;
				java.sql.Connection conn_tDBInput_6 = null;
				String driverClass_tDBInput_6 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_6 = java.lang.Class.forName(driverClass_tDBInput_6);
				String dbUser_tDBInput_6 = "analytics";

				final String decryptedPassword_tDBInput_6 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:fIo33nGX+GumgGs90ukSuaq1z58MTVjmtKyotZirxWh41Q1VI9a6gdDp");

				String dbPwd_tDBInput_6 = decryptedPassword_tDBInput_6;

				String port_tDBInput_6 = "1433";
				String dbname_tDBInput_6 = "TOTVS";
				String url_tDBInput_6 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_6)) {
					url_tDBInput_6 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_6)) {
					url_tDBInput_6 += ";databaseName=" + "TOTVS";
				}
				url_tDBInput_6 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_6 = "";

				log.debug("tDBInput_6 - Driver ClassName: " + driverClass_tDBInput_6 + ".");

				log.debug("tDBInput_6 - Connection attempt to '" + url_tDBInput_6 + "' with the username '"
						+ dbUser_tDBInput_6 + "'.");

				conn_tDBInput_6 = java.sql.DriverManager.getConnection(url_tDBInput_6, dbUser_tDBInput_6,
						dbPwd_tDBInput_6);
				log.debug("tDBInput_6 - Connection to '" + url_tDBInput_6 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_6 = conn_tDBInput_6.createStatement();

				String dbquery_tDBInput_6 = "SELECT C9_PRODUTO,\n				  C9_QTDLIB,\n	C9_FILIAL,\n	C9_LOCAL,\n   	 D_E_L_E_T_,\n	 C9_NFISCAL\n   FROM SC9010\nWHERE C9"
						+ "_DATALIB >= GETDATE() - 120";

				log.debug("tDBInput_6 - Executing the query: '" + dbquery_tDBInput_6 + "'.");

				globalMap.put("tDBInput_6_QUERY", dbquery_tDBInput_6);

				java.sql.ResultSet rs_tDBInput_6 = null;

				try {
					rs_tDBInput_6 = stmt_tDBInput_6.executeQuery(dbquery_tDBInput_6);
					java.sql.ResultSetMetaData rsmd_tDBInput_6 = rs_tDBInput_6.getMetaData();
					int colQtyInRs_tDBInput_6 = rsmd_tDBInput_6.getColumnCount();

					String tmpContent_tDBInput_6 = null;

					log.debug("tDBInput_6 - Retrieving records from the database.");

					while (rs_tDBInput_6.next()) {
						nb_line_tDBInput_6++;

						if (colQtyInRs_tDBInput_6 < 1) {
							row11.C9_PRODUTO = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(1);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.C9_PRODUTO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row11.C9_PRODUTO = tmpContent_tDBInput_6.trim();
								}
							} else {
								row11.C9_PRODUTO = null;
							}
						}
						if (colQtyInRs_tDBInput_6 < 2) {
							row11.C9_QTDLIB = null;
						} else {

							row11.C9_QTDLIB = rs_tDBInput_6.getDouble(2);
							if (rs_tDBInput_6.wasNull()) {
								row11.C9_QTDLIB = null;
							}
						}
						if (colQtyInRs_tDBInput_6 < 3) {
							row11.C9_FILIAL = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(3);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.C9_FILIAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row11.C9_FILIAL = tmpContent_tDBInput_6.trim();
								}
							} else {
								row11.C9_FILIAL = null;
							}
						}
						if (colQtyInRs_tDBInput_6 < 4) {
							row11.C9_LOCAL = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(4);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.C9_LOCAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row11.C9_LOCAL = tmpContent_tDBInput_6.trim();
								}
							} else {
								row11.C9_LOCAL = null;
							}
						}
						if (colQtyInRs_tDBInput_6 < 5) {
							row11.D_E_L_E_T_ = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(5);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.D_E_L_E_T_ = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row11.D_E_L_E_T_ = tmpContent_tDBInput_6.trim();
								}
							} else {
								row11.D_E_L_E_T_ = null;
							}
						}
						if (colQtyInRs_tDBInput_6 < 6) {
							row11.C9_NFISCAL = null;
						} else {

							tmpContent_tDBInput_6 = rs_tDBInput_6.getString(6);
							if (tmpContent_tDBInput_6 != null) {
								if (talendToDBList_tDBInput_6.contains(
										rsmd_tDBInput_6.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row11.C9_NFISCAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_6);
								} else {
									row11.C9_NFISCAL = tmpContent_tDBInput_6.trim();
								}
							} else {
								row11.C9_NFISCAL = null;
							}
						}

						log.debug("tDBInput_6 - Retrieving the record " + nb_line_tDBInput_6 + ".");

						/**
						 * [tDBInput_6 begin ] stop
						 */

						/**
						 * [tDBInput_6 main ] start
						 */

						currentComponent = "tDBInput_6";

						cLabel = "Input Empenho";

						tos_count_tDBInput_6++;

						/**
						 * [tDBInput_6 main ] stop
						 */

						/**
						 * [tDBInput_6 process_data_begin ] start
						 */

						currentComponent = "tDBInput_6";

						cLabel = "Input Empenho";

						/**
						 * [tDBInput_6 process_data_begin ] stop
						 */

						/**
						 * [tFilterRow_20 main ] start
						 */

						currentComponent = "tFilterRow_20";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row11", "tDBInput_6", "Input Empenho", "tMSSqlInput", "tFilterRow_20",
								"tFilterRow_20", "tFilterRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row11 - " + (row11 == null ? "" : row11.toLogString()));
						}

						row12 = null;
						Operator_tFilterRow_20 ope_tFilterRow_20 = new Operator_tFilterRow_20("&&");
						ope_tFilterRow_20.matches(
								(row11.C9_FILIAL == null ? false : row11.C9_FILIAL.compareTo("02") == 0),
								"C9_FILIAL.compareTo(\"02\") == 0 failed");
						ope_tFilterRow_20.matches(
								(row11.C9_LOCAL == null ? false : row11.C9_LOCAL.compareTo("23") == 0),
								"C9_LOCAL.compareTo(\"23\") == 0 failed");
						ope_tFilterRow_20.matches(
								(row11.D_E_L_E_T_ == null ? false : row11.D_E_L_E_T_.compareTo("*") != 0),
								"D_E_L_E_T_.compareTo(\"*\") != 0 failed");
						ope_tFilterRow_20.matches((// code sample : use row11 to define the condition.
// row11.columnName1.equals("foo") ||!(row11.columnName2.equals("bar"))
// replace the following expression by your own filter condition 
						row11.C9_NFISCAL.trim().isEmpty()), "advanced condition failed");

						if (ope_tFilterRow_20.getMatchFlag()) {
							if (row12 == null) {
								row12 = new row12Struct();
							}
							row12.C9_PRODUTO = row11.C9_PRODUTO;
							row12.C9_QTDLIB = row11.C9_QTDLIB;
							row12.C9_FILIAL = row11.C9_FILIAL;
							row12.C9_LOCAL = row11.C9_LOCAL;
							row12.D_E_L_E_T_ = row11.D_E_L_E_T_;
							row12.C9_NFISCAL = row11.C9_NFISCAL;
							log.debug("tFilterRow_20 - Process the record " + (nb_line_tFilterRow_20 + 1) + ".");

							nb_line_ok_tFilterRow_20++;
						} else {
							nb_line_reject_tFilterRow_20++;
						}

						nb_line_tFilterRow_20++;

						tos_count_tFilterRow_20++;

						/**
						 * [tFilterRow_20 main ] stop
						 */

						/**
						 * [tFilterRow_20 process_data_begin ] start
						 */

						currentComponent = "tFilterRow_20";

						/**
						 * [tFilterRow_20 process_data_begin ] stop
						 */
// Start of branch "row12"
						if (row12 != null) {

							/**
							 * [tAggregateRow_2_AGGOUT main ] start
							 */

							currentVirtualComponent = "tAggregateRow_2";

							currentComponent = "tAggregateRow_2_AGGOUT";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row12", "tFilterRow_20", "tFilterRow_20", "tFilterRow", "tAggregateRow_2_AGGOUT",
									"tAggregateRow_2_AGGOUT", "tAggregateOut"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
							}

							operation_finder_tAggregateRow_2.C9_PRODUTO = row12.C9_PRODUTO;

							operation_finder_tAggregateRow_2.hashCodeDirty = true;

							operation_result_tAggregateRow_2 = hash_tAggregateRow_2
									.get(operation_finder_tAggregateRow_2);

							if (operation_result_tAggregateRow_2 == null) { // G_OutMain_AggR_001

								operation_result_tAggregateRow_2 = new AggOperationStruct_tAggregateRow_2();

								operation_result_tAggregateRow_2.C9_PRODUTO = operation_finder_tAggregateRow_2.C9_PRODUTO;

								hash_tAggregateRow_2.put(operation_result_tAggregateRow_2,
										operation_result_tAggregateRow_2);

							} // G_OutMain_AggR_001

							if (operation_result_tAggregateRow_2.C9_QTDLIB_sum == null) {
								operation_result_tAggregateRow_2.C9_QTDLIB_sum = new BigDecimal(0).setScale(0);
							}
							operation_result_tAggregateRow_2.C9_QTDLIB_sum = operation_result_tAggregateRow_2.C9_QTDLIB_sum
									.add(new BigDecimal(String.valueOf(row12.C9_QTDLIB)));

							tos_count_tAggregateRow_2_AGGOUT++;

							/**
							 * [tAggregateRow_2_AGGOUT main ] stop
							 */

							/**
							 * [tAggregateRow_2_AGGOUT process_data_begin ] start
							 */

							currentVirtualComponent = "tAggregateRow_2";

							currentComponent = "tAggregateRow_2_AGGOUT";

							/**
							 * [tAggregateRow_2_AGGOUT process_data_begin ] stop
							 */

							/**
							 * [tAggregateRow_2_AGGOUT process_data_end ] start
							 */

							currentVirtualComponent = "tAggregateRow_2";

							currentComponent = "tAggregateRow_2_AGGOUT";

							/**
							 * [tAggregateRow_2_AGGOUT process_data_end ] stop
							 */

						} // End of branch "row12"

						/**
						 * [tFilterRow_20 process_data_end ] start
						 */

						currentComponent = "tFilterRow_20";

						/**
						 * [tFilterRow_20 process_data_end ] stop
						 */

						/**
						 * [tDBInput_6 process_data_end ] start
						 */

						currentComponent = "tDBInput_6";

						cLabel = "Input Empenho";

						/**
						 * [tDBInput_6 process_data_end ] stop
						 */

						/**
						 * [tDBInput_6 end ] start
						 */

						currentComponent = "tDBInput_6";

						cLabel = "Input Empenho";

					}
				} finally {
					if (rs_tDBInput_6 != null) {
						rs_tDBInput_6.close();
					}
					if (stmt_tDBInput_6 != null) {
						stmt_tDBInput_6.close();
					}
					if (conn_tDBInput_6 != null && !conn_tDBInput_6.isClosed()) {

						log.debug("tDBInput_6 - Closing the connection to the database.");

						conn_tDBInput_6.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_6 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_6_NB_LINE", nb_line_tDBInput_6);
				log.debug("tDBInput_6 - Retrieved records count: " + nb_line_tDBInput_6 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_6 - " + ("Done."));

				ok_Hash.put("tDBInput_6", true);
				end_Hash.put("tDBInput_6", System.currentTimeMillis());

				/**
				 * [tDBInput_6 end ] stop
				 */

				/**
				 * [tFilterRow_20 end ] start
				 */

				currentComponent = "tFilterRow_20";

				globalMap.put("tFilterRow_20_NB_LINE", nb_line_tFilterRow_20);
				globalMap.put("tFilterRow_20_NB_LINE_OK", nb_line_ok_tFilterRow_20);
				globalMap.put("tFilterRow_20_NB_LINE_REJECT", nb_line_reject_tFilterRow_20);

				log.info("tFilterRow_20 - Processed records count:" + nb_line_tFilterRow_20 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_20 + ". Rejected records count:" + nb_line_reject_tFilterRow_20 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row11", 2, 0,
						"tDBInput_6", "Input Empenho", "tMSSqlInput", "tFilterRow_20", "tFilterRow_20", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_20 - " + ("Done."));

				ok_Hash.put("tFilterRow_20", true);
				end_Hash.put("tFilterRow_20", System.currentTimeMillis());

				/**
				 * [tFilterRow_20 end ] stop
				 */

				/**
				 * [tAggregateRow_2_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_2";

				currentComponent = "tAggregateRow_2_AGGOUT";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row12", 2, 0,
						"tFilterRow_20", "tFilterRow_20", "tFilterRow", "tAggregateRow_2_AGGOUT",
						"tAggregateRow_2_AGGOUT", "tAggregateOut", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_2_AGGOUT - " + ("Done."));

				ok_Hash.put("tAggregateRow_2_AGGOUT", true);
				end_Hash.put("tAggregateRow_2_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_2_AGGOUT end ] stop
				 */

				/**
				 * [tAdvancedHash_row13 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row13", false);
				start_Hash.put("tAdvancedHash_row13", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row13";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row13");

				int tos_count_tAdvancedHash_row13 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row13", "tAdvancedHash_row13", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row13
				// source node:tAggregateRow_2_AGGIN - inputs:(OnRowsEnd) outputs:(row13,row13)
				// | target node:tAdvancedHash_row13 - inputs:(row13) outputs:()
				// linked node: tMap_1 - inputs:(row13,row15,row17) outputs:(Estoque)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row13 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct> tHash_Lookup_row13 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row13Struct>getLookup(matchingModeEnum_row13);

				globalMap.put("tHash_Lookup_row13", tHash_Lookup_row13);

				/**
				 * [tAdvancedHash_row13 begin ] stop
				 */

				/**
				 * [tAggregateRow_2_AGGIN begin ] start
				 */

				ok_Hash.put("tAggregateRow_2_AGGIN", false);
				start_Hash.put("tAggregateRow_2_AGGIN", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_2";

				currentComponent = "tAggregateRow_2_AGGIN";

				int tos_count_tAggregateRow_2_AGGIN = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_2_AGGIN - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_2_AGGIN {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_2_AGGIN = new StringBuilder();
							log4jParamters_tAggregateRow_2_AGGIN.append("Parameters:");
							log4jParamters_tAggregateRow_2_AGGIN.append("ORIGIN" + " = " + "tAggregateRow_2");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("C9_PRODUTO") + ", INPUT_COLUMN=" + ("C9_PRODUTO") + "}]");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("C9_QTDLIB") + ", INPUT_COLUMN=" + ("C9_QTDLIB") + ", IGNORE_NULL=" + ("false")
									+ ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_2_AGGIN.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_2_AGGIN.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_2_AGGIN - " + (log4jParamters_tAggregateRow_2_AGGIN));
						}
					}
					new BytesLimit65535_tAggregateRow_2_AGGIN().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_2_AGGIN", "tAggregateRow_2_AGGIN", "tAggregateIn");
					talendJobLogProcess(globalMap);
				}

				java.util.Collection<AggOperationStruct_tAggregateRow_2> values_tAggregateRow_2 = hash_tAggregateRow_2
						.values();

				globalMap.put("tAggregateRow_2_NB_LINE", values_tAggregateRow_2.size());

				if (log.isInfoEnabled())
					log.info("tAggregateRow_2_AGGIN - " + ("Retrieving the aggregation results."));
				for (AggOperationStruct_tAggregateRow_2 aggregated_row_tAggregateRow_2 : values_tAggregateRow_2) { // G_AggR_600

					/**
					 * [tAggregateRow_2_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_2_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_2";

					currentComponent = "tAggregateRow_2_AGGIN";

					row13.C9_PRODUTO = aggregated_row_tAggregateRow_2.C9_PRODUTO;

					if (aggregated_row_tAggregateRow_2.C9_QTDLIB_sum != null) {
						row13.C9_QTDLIB = aggregated_row_tAggregateRow_2.C9_QTDLIB_sum.doubleValue();

					} else {

						row13.C9_QTDLIB = null;

					}

					if (log.isDebugEnabled())
						log.debug(
								"tAggregateRow_2_AGGIN - " + ("Operation function: 'sum' on the column 'C9_QTDLIB'."));

					tos_count_tAggregateRow_2_AGGIN++;

					/**
					 * [tAggregateRow_2_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_2_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_2";

					currentComponent = "tAggregateRow_2_AGGIN";

					/**
					 * [tAggregateRow_2_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row13 main ] start
					 */

					currentComponent = "tAdvancedHash_row13";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row13", "tAggregateRow_2_AGGIN", "tAggregateRow_2_AGGIN", "tAggregateIn",
							"tAdvancedHash_row13", "tAdvancedHash_row13", "tAdvancedHash"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row13 - " + (row13 == null ? "" : row13.toLogString()));
					}

					row13Struct row13_HashRow = new row13Struct();

					row13_HashRow.C9_PRODUTO = row13.C9_PRODUTO;

					row13_HashRow.C9_QTDLIB = row13.C9_QTDLIB;

					row13_HashRow.C9_FILIAL = row13.C9_FILIAL;

					row13_HashRow.C9_LOCAL = row13.C9_LOCAL;

					row13_HashRow.D_E_L_E_T_ = row13.D_E_L_E_T_;

					row13_HashRow.C9_NFISCAL = row13.C9_NFISCAL;

					tHash_Lookup_row13.put(row13_HashRow);

					tos_count_tAdvancedHash_row13++;

					/**
					 * [tAdvancedHash_row13 main ] stop
					 */

					/**
					 * [tAdvancedHash_row13 process_data_begin ] start
					 */

					currentComponent = "tAdvancedHash_row13";

					/**
					 * [tAdvancedHash_row13 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row13 process_data_end ] start
					 */

					currentComponent = "tAdvancedHash_row13";

					/**
					 * [tAdvancedHash_row13 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_2_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_2";

					currentComponent = "tAggregateRow_2_AGGIN";

					/**
					 * [tAggregateRow_2_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_2_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_2";

					currentComponent = "tAggregateRow_2_AGGIN";

				} // G_AggR_600

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_2_AGGIN - " + ("Done."));

				ok_Hash.put("tAggregateRow_2_AGGIN", true);
				end_Hash.put("tAggregateRow_2_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_2_AGGIN end ] stop
				 */

				/**
				 * [tAdvancedHash_row13 end ] start
				 */

				currentComponent = "tAdvancedHash_row13";

				tHash_Lookup_row13.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row13", 2, 0,
						"tAggregateRow_2_AGGIN", "tAggregateRow_2_AGGIN", "tAggregateIn", "tAdvancedHash_row13",
						"tAdvancedHash_row13", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row13", true);
				end_Hash.put("tAdvancedHash_row13", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row13 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_2_AGGIN"
			globalMap.remove("tAggregateRow_2");

			try {

				/**
				 * [tDBInput_6 finally ] start
				 */

				currentComponent = "tDBInput_6";

				cLabel = "Input Empenho";

				/**
				 * [tDBInput_6 finally ] stop
				 */

				/**
				 * [tFilterRow_20 finally ] start
				 */

				currentComponent = "tFilterRow_20";

				/**
				 * [tFilterRow_20 finally ] stop
				 */

				/**
				 * [tAggregateRow_2_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_2";

				currentComponent = "tAggregateRow_2_AGGOUT";

				/**
				 * [tAggregateRow_2_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_2_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_2";

				currentComponent = "tAggregateRow_2_AGGIN";

				/**
				 * [tAggregateRow_2_AGGIN finally ] stop
				 */

				/**
				 * [tAdvancedHash_row13 finally ] start
				 */

				currentComponent = "tAdvancedHash_row13";

				/**
				 * [tAdvancedHash_row13 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_6_SUBPROCESS_STATE", 1);
	}

	public static class EstoqueStruct implements routines.system.IPersistableRow<EstoqueStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String D14_PRODUT;

		public String getD14_PRODUT() {
			return this.D14_PRODUT;
		}

		public Boolean D14_PRODUTIsNullable() {
			return true;
		}

		public Boolean D14_PRODUTIsKey() {
			return true;
		}

		public Integer D14_PRODUTLength() {
			return 15;
		}

		public Integer D14_PRODUTPrecision() {
			return 0;
		}

		public String D14_PRODUTDefault() {

			return null;

		}

		public String D14_PRODUTComment() {

			return "";

		}

		public String D14_PRODUTPattern() {

			return "";

		}

		public String D14_PRODUTOriginalDbColumnName() {

			return "D14_PRODUT";

		}

		public Double ESTOQUE;

		public Double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.D14_PRODUT == null) ? 0 : this.D14_PRODUT.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final EstoqueStruct other = (EstoqueStruct) obj;

			if (this.D14_PRODUT == null) {
				if (other.D14_PRODUT != null)
					return false;

			} else if (!this.D14_PRODUT.equals(other.D14_PRODUT))

				return false;

			return true;
		}

		public void copyDataTo(EstoqueStruct other) {

			other.D14_PRODUT = this.D14_PRODUT;
			other.ESTOQUE = this.ESTOQUE;
			other.C9_PRODUTO = this.C9_PRODUTO;
			other.C9_QTDLIB = this.C9_QTDLIB;

		}

		public void copyKeysDataTo(EstoqueStruct other) {

			other.D14_PRODUT = this.D14_PRODUT;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("D14_PRODUT=" + D14_PRODUT);
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (D14_PRODUT == null) {
				sb.append("<null>");
			} else {
				sb.append(D14_PRODUT);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(EstoqueStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.D14_PRODUT, other.D14_PRODUT);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row15Struct implements routines.system.IPersistableRow<row15Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String D14_PRODUT;

		public String getD14_PRODUT() {
			return this.D14_PRODUT;
		}

		public Boolean D14_PRODUTIsNullable() {
			return true;
		}

		public Boolean D14_PRODUTIsKey() {
			return true;
		}

		public Integer D14_PRODUTLength() {
			return 15;
		}

		public Integer D14_PRODUTPrecision() {
			return 0;
		}

		public String D14_PRODUTDefault() {

			return null;

		}

		public String D14_PRODUTComment() {

			return "";

		}

		public String D14_PRODUTPattern() {

			return "";

		}

		public String D14_PRODUTOriginalDbColumnName() {

			return "D14_PRODUT";

		}

		public Double ESTOQUE;

		public Double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("D14_PRODUT=" + D14_PRODUT);
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (D14_PRODUT == null) {
				sb.append("<null>");
			} else {
				sb.append(D14_PRODUT);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row15Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tDBInput_8Struct implements routines.system.IPersistableRow<after_tDBInput_8Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String D14_PRODUT;

		public String getD14_PRODUT() {
			return this.D14_PRODUT;
		}

		public Boolean D14_PRODUTIsNullable() {
			return true;
		}

		public Boolean D14_PRODUTIsKey() {
			return true;
		}

		public Integer D14_PRODUTLength() {
			return 15;
		}

		public Integer D14_PRODUTPrecision() {
			return 0;
		}

		public String D14_PRODUTDefault() {

			return null;

		}

		public String D14_PRODUTComment() {

			return "";

		}

		public String D14_PRODUTPattern() {

			return "";

		}

		public String D14_PRODUTOriginalDbColumnName() {

			return "D14_PRODUT";

		}

		public Double ESTOQUE;

		public Double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.D14_PRODUT == null) ? 0 : this.D14_PRODUT.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tDBInput_8Struct other = (after_tDBInput_8Struct) obj;

			if (this.D14_PRODUT == null) {
				if (other.D14_PRODUT != null)
					return false;

			} else if (!this.D14_PRODUT.equals(other.D14_PRODUT))

				return false;

			return true;
		}

		public void copyDataTo(after_tDBInput_8Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;
			other.ESTOQUE = this.ESTOQUE;

		}

		public void copyKeysDataTo(after_tDBInput_8Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("D14_PRODUT=" + D14_PRODUT);
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (D14_PRODUT == null) {
				sb.append("<null>");
			} else {
				sb.append(D14_PRODUT);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.D14_PRODUT, other.D14_PRODUT);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_8Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_8_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_8");
		org.slf4j.MDC.put("_subJobPid", "yPmouF_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_6Process(globalMap);
				tDBInput_9Process(globalMap);

				row15Struct row15 = new row15Struct();
				EstoqueStruct Estoque = new EstoqueStruct();

				/**
				 * [tHashOutput_1 begin ] start
				 */

				ok_Hash.put("tHashOutput_1", false);
				start_Hash.put("tHashOutput_1", System.currentTimeMillis());

				currentComponent = "tHashOutput_1";

				cLabel = "Estoque";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Estoque");

				int tos_count_tHashOutput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_1", "Estoque", "tHashOutput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<EstoqueStruct> tHashFile_tHashOutput_1 = null;
				String hashKey_tHashOutput_1 = "tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_1";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_1)) {
					if (mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1) == null) {
						mf_tHashOutput_1.getResourceMap().put(hashKey_tHashOutput_1,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<EstoqueStruct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					} else {
						tHashFile_tHashOutput_1 = mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1);
					}
				}
				int nb_line_tHashOutput_1 = 0;

				/**
				 * [tHashOutput_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row15");

				int tos_count_tMap_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_1 = new StringBuilder();
							log4jParamters_tMap_1.append("Parameters:");
							log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_1 - " + (log4jParamters_tMap_1));
						}
					}
					new BytesLimit65535_tMap_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_1", "tMap_1", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row15_tMap_1 = 0;

				int count_row13_tMap_1 = 0;

				int count_row17_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct> tHash_Lookup_row13 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row13Struct>) globalMap
						.get("tHash_Lookup_row13"));

				row13Struct row13HashKey = new row13Struct();
				row13Struct row13Default = new row13Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct> tHash_Lookup_row17 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct>) globalMap
						.get("tHash_Lookup_row17"));

				row17Struct row17HashKey = new row17Struct();
				row17Struct row17Default = new row17Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_Estoque_tMap_1 = 0;

				EstoqueStruct Estoque_tmp = new EstoqueStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_8 begin ] start
				 */

				ok_Hash.put("tDBInput_8", false);
				start_Hash.put("tDBInput_8", System.currentTimeMillis());

				currentComponent = "tDBInput_8";

				cLabel = "Input Estoque";

				int tos_count_tDBInput_8 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_8 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_8 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_8 = new StringBuilder();
							log4jParamters_tDBInput_8.append("Parameters:");
							log4jParamters_tDBInput_8.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("DBNAME" + " = " + "\"TOTVS\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:pUXqklcD5bWKuV4L0GUalTK23rJ8HjBE80DPp432QPCcar+WfIzpb2Jq")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("TABLE" + " = " + "\"\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("QUERY" + " = "
									+ "\"SELECT D14.D14_PRODUT,  	   SUM(D14.D14_QTDEST) - SUM(D14.D14_QTDBLQ) - SUM(D14_QTDSPR) - SUM(D14_QTDEMP)    FROM D14010 D14    LEFT JOIN SB1010 SB1 ON SB1.B1_COD = D14.D14_PRODUT   WHERE D14.D_E_L_E_T_ <> '*'     AND D14.D14_LOCAL = '23'     AND D14.D14_FILIAL = '02'     AND D14.D14_ENDER LIKE 'A0%'     AND DATEDIFF(DAY, GETDATE(), D14.D14_DTVALD) > SB1.B1_USHELFL   GROUP BY D14.D14_PRODUT\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append(
									"TRIM_COLUMN" + " = " + "[{TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("D14_PRODUT")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("ESTOQUE") + "}]");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_8.append(" | ");
							log4jParamters_tDBInput_8.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_8.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_8 - " + (log4jParamters_tDBInput_8));
						}
					}
					new BytesLimit65535_tDBInput_8().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_8", "Input Estoque", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_8 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_8 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_8 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_8, talendToDBArray_tDBInput_8);
				int nb_line_tDBInput_8 = 0;
				java.sql.Connection conn_tDBInput_8 = null;
				String driverClass_tDBInput_8 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_8 = java.lang.Class.forName(driverClass_tDBInput_8);
				String dbUser_tDBInput_8 = "analytics";

				final String decryptedPassword_tDBInput_8 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:D8spC39rPLafycRiP4wnmZEE0syW8CxKu9MThkNDzXBDo82fFmpZ/ERL");

				String dbPwd_tDBInput_8 = decryptedPassword_tDBInput_8;

				String port_tDBInput_8 = "1433";
				String dbname_tDBInput_8 = "TOTVS";
				String url_tDBInput_8 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_8)) {
					url_tDBInput_8 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_8)) {
					url_tDBInput_8 += ";databaseName=" + "TOTVS";
				}
				url_tDBInput_8 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_8 = "";

				log.debug("tDBInput_8 - Driver ClassName: " + driverClass_tDBInput_8 + ".");

				log.debug("tDBInput_8 - Connection attempt to '" + url_tDBInput_8 + "' with the username '"
						+ dbUser_tDBInput_8 + "'.");

				conn_tDBInput_8 = java.sql.DriverManager.getConnection(url_tDBInput_8, dbUser_tDBInput_8,
						dbPwd_tDBInput_8);
				log.debug("tDBInput_8 - Connection to '" + url_tDBInput_8 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_8 = conn_tDBInput_8.createStatement();

				String dbquery_tDBInput_8 = "SELECT D14.D14_PRODUT,\n	   SUM(D14.D14_QTDEST) - SUM(D14.D14_QTDBLQ) - SUM(D14_QTDSPR) - SUM(D14_QTDEMP)\n  FROM D1401"
						+ "0 D14\n  LEFT JOIN SB1010 SB1 ON SB1.B1_COD = D14.D14_PRODUT\n WHERE D14.D_E_L_E_T_ <> '*'\n   AND D14.D14_LOCAL = '23'"
						+ "\n   AND D14.D14_FILIAL = '02'\n   AND D14.D14_ENDER LIKE 'A0%'\n   AND DATEDIFF(DAY, GETDATE(), D14.D14_DTVALD) > SB1.B1"
						+ "_USHELFL\n GROUP BY D14.D14_PRODUT";

				log.debug("tDBInput_8 - Executing the query: '" + dbquery_tDBInput_8 + "'.");

				globalMap.put("tDBInput_8_QUERY", dbquery_tDBInput_8);

				java.sql.ResultSet rs_tDBInput_8 = null;

				try {
					rs_tDBInput_8 = stmt_tDBInput_8.executeQuery(dbquery_tDBInput_8);
					java.sql.ResultSetMetaData rsmd_tDBInput_8 = rs_tDBInput_8.getMetaData();
					int colQtyInRs_tDBInput_8 = rsmd_tDBInput_8.getColumnCount();

					String tmpContent_tDBInput_8 = null;

					log.debug("tDBInput_8 - Retrieving records from the database.");

					while (rs_tDBInput_8.next()) {
						nb_line_tDBInput_8++;

						if (colQtyInRs_tDBInput_8 < 1) {
							row15.D14_PRODUT = null;
						} else {

							tmpContent_tDBInput_8 = rs_tDBInput_8.getString(1);
							if (tmpContent_tDBInput_8 != null) {
								if (talendToDBList_tDBInput_8.contains(
										rsmd_tDBInput_8.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									row15.D14_PRODUT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_8);
								} else {
									row15.D14_PRODUT = tmpContent_tDBInput_8.trim();
								}
							} else {
								row15.D14_PRODUT = null;
							}
						}
						if (colQtyInRs_tDBInput_8 < 2) {
							row15.ESTOQUE = null;
						} else {

							row15.ESTOQUE = rs_tDBInput_8.getDouble(2);
							if (rs_tDBInput_8.wasNull()) {
								row15.ESTOQUE = null;
							}
						}

						log.debug("tDBInput_8 - Retrieving the record " + nb_line_tDBInput_8 + ".");

						/**
						 * [tDBInput_8 begin ] stop
						 */

						/**
						 * [tDBInput_8 main ] start
						 */

						currentComponent = "tDBInput_8";

						cLabel = "Input Estoque";

						tos_count_tDBInput_8++;

						/**
						 * [tDBInput_8 main ] stop
						 */

						/**
						 * [tDBInput_8 process_data_begin ] start
						 */

						currentComponent = "tDBInput_8";

						cLabel = "Input Estoque";

						/**
						 * [tDBInput_8 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row15", "tDBInput_8", "Input Estoque", "tMSSqlInput", "tMap_1", "tMap_1", "tMap"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row15 - " + (row15 == null ? "" : row15.toLogString()));
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						row13Struct row13 = null;

						row17Struct row17 = null;

						// ###############################
						// # Input tables (lookups)

						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row13"
						///////////////////////////////////////////////

						boolean forceLooprow13 = false;

						row13Struct row13ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_1 = false;

							row13HashKey.C9_PRODUTO = row15.D14_PRODUT;

							row13HashKey.hashCodeDirty = true;

							tHash_Lookup_row13.lookup(row13HashKey);

							if (!tHash_Lookup_row13.hasNext()) { // G_TM_M_090

								forceLooprow13 = true;

							} // G_TM_M_090

						} // G_TM_M_020

						else { // G 20 - G 21
							forceLooprow13 = true;
						} // G 21

						while ((tHash_Lookup_row13 != null && tHash_Lookup_row13.hasNext()) || forceLooprow13) { // G_TM_M_043

							// CALL close loop of lookup 'row13'

							row13Struct fromLookup_row13 = null;
							row13 = row13Default;

							if (!forceLooprow13) { // G 46

								fromLookup_row13 = tHash_Lookup_row13.next();

								if (fromLookup_row13 != null) {
									row13 = fromLookup_row13;
								}

							} // G 46

							forceLooprow13 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row17"
							///////////////////////////////////////////////

							boolean forceLooprow17 = false;

							row17Struct row17ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row17HashKey.DB_ESTAL_PRODUTO = row13.C9_PRODUTO;

								row17HashKey.hashCodeDirty = true;

								tHash_Lookup_row17.lookup(row17HashKey);

								if (!tHash_Lookup_row17.hasNext()) { // G_TM_M_090

									forceLooprow17 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLooprow17 = true;
							} // G 21

							while ((tHash_Lookup_row17 != null && tHash_Lookup_row17.hasNext()) || forceLooprow17) { // G_TM_M_043

								// CALL close loop of lookup 'row17'

								row17Struct fromLookup_row17 = null;
								row17 = row17Default;

								if (!forceLooprow17) { // G 46

									fromLookup_row17 = tHash_Lookup_row17.next();

									if (fromLookup_row17 != null) {
										row17 = fromLookup_row17;
									}

								} // G 46

								forceLooprow17 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									Estoque = null;

// # Output table : 'Estoque'
									count_Estoque_tMap_1++;

									Estoque_tmp.D14_PRODUT = row15.D14_PRODUT;
									Estoque_tmp.ESTOQUE = row15.ESTOQUE
											- ((Relational.ISNULL(row13.C9_QTDLIB) ? 0 : row13.C9_QTDLIB)
													+ (Relational.ISNULL(row17.DB_ESTAL_QTDE_EST) ? 0
															: row17.DB_ESTAL_QTDE_EST));
									Estoque_tmp.C9_PRODUTO = row13.C9_PRODUTO;
									Estoque_tmp.C9_QTDLIB = row13.C9_QTDLIB;
									Estoque = Estoque_tmp;
									log.debug("tMap_1 - Outputting the record " + count_Estoque_tMap_1
											+ " of the output table 'Estoque'.");

// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "Estoque"
								if (Estoque != null) {

									/**
									 * [tHashOutput_1 main ] start
									 */

									currentComponent = "tHashOutput_1";

									cLabel = "Estoque";

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "Estoque", "tMap_1", "tMap_1", "tMap", "tHashOutput_1", "Estoque",
											"tHashOutput"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("Estoque - " + (Estoque == null ? "" : Estoque.toLogString()));
									}

									EstoqueStruct oneRow_tHashOutput_1 = new EstoqueStruct();

									oneRow_tHashOutput_1.D14_PRODUT = Estoque.D14_PRODUT;
									oneRow_tHashOutput_1.ESTOQUE = Estoque.ESTOQUE;
									oneRow_tHashOutput_1.C9_PRODUTO = Estoque.C9_PRODUTO;
									oneRow_tHashOutput_1.C9_QTDLIB = Estoque.C9_QTDLIB;

									tHashFile_tHashOutput_1.put(oneRow_tHashOutput_1);
									nb_line_tHashOutput_1++;

									tos_count_tHashOutput_1++;

									/**
									 * [tHashOutput_1 main ] stop
									 */

									/**
									 * [tHashOutput_1 process_data_begin ] start
									 */

									currentComponent = "tHashOutput_1";

									cLabel = "Estoque";

									/**
									 * [tHashOutput_1 process_data_begin ] stop
									 */

									/**
									 * [tHashOutput_1 process_data_end ] start
									 */

									currentComponent = "tHashOutput_1";

									cLabel = "Estoque";

									/**
									 * [tHashOutput_1 process_data_end ] stop
									 */

								} // End of branch "Estoque"

							} // close loop of lookup 'row17' // G_TM_M_043

						} // close loop of lookup 'row13' // G_TM_M_043

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_8 process_data_end ] start
						 */

						currentComponent = "tDBInput_8";

						cLabel = "Input Estoque";

						/**
						 * [tDBInput_8 process_data_end ] stop
						 */

						/**
						 * [tDBInput_8 end ] start
						 */

						currentComponent = "tDBInput_8";

						cLabel = "Input Estoque";

					}
				} finally {
					if (rs_tDBInput_8 != null) {
						rs_tDBInput_8.close();
					}
					if (stmt_tDBInput_8 != null) {
						stmt_tDBInput_8.close();
					}
					if (conn_tDBInput_8 != null && !conn_tDBInput_8.isClosed()) {

						log.debug("tDBInput_8 - Closing the connection to the database.");

						conn_tDBInput_8.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_8 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_8_NB_LINE", nb_line_tDBInput_8);
				log.debug("tDBInput_8 - Retrieved records count: " + nb_line_tDBInput_8 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_8 - " + ("Done."));

				ok_Hash.put("tDBInput_8", true);
				end_Hash.put("tDBInput_8", System.currentTimeMillis());

				/**
				 * [tDBInput_8 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row13 != null) {
					tHash_Lookup_row13.endGet();
				}
				globalMap.remove("tHash_Lookup_row13");

				if (tHash_Lookup_row17 != null) {
					tHash_Lookup_row17.endGet();
				}
				globalMap.remove("tHash_Lookup_row17");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'Estoque': " + count_Estoque_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row15", 2, 0,
						"tDBInput_8", "Input Estoque", "tMSSqlInput", "tMap_1", "tMap_1", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Done."));

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tHashOutput_1 end ] start
				 */

				currentComponent = "tHashOutput_1";

				cLabel = "Estoque";

				globalMap.put("tHashOutput_1_NB_LINE", nb_line_tHashOutput_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Estoque", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tHashOutput_1", "Estoque", "tHashOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_1", true);
				end_Hash.put("tHashOutput_1", System.currentTimeMillis());

				/**
				 * [tHashOutput_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tDBInput_8:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tHashInput_2Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row13");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row17");

			try {

				/**
				 * [tDBInput_8 finally ] start
				 */

				currentComponent = "tDBInput_8";

				cLabel = "Input Estoque";

				/**
				 * [tDBInput_8 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tHashOutput_1 finally ] start
				 */

				currentComponent = "tHashOutput_1";

				cLabel = "Estoque";

				/**
				 * [tHashOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_8_SUBPROCESS_STATE", 1);
	}

	public static class row16Struct implements routines.system.IPersistableRow<row16Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String D14_PRODUT;

		public String getD14_PRODUT() {
			return this.D14_PRODUT;
		}

		public Boolean D14_PRODUTIsNullable() {
			return true;
		}

		public Boolean D14_PRODUTIsKey() {
			return true;
		}

		public Integer D14_PRODUTLength() {
			return 15;
		}

		public Integer D14_PRODUTPrecision() {
			return 0;
		}

		public String D14_PRODUTDefault() {

			return null;

		}

		public String D14_PRODUTComment() {

			return "";

		}

		public String D14_PRODUTPattern() {

			return "";

		}

		public String D14_PRODUTOriginalDbColumnName() {

			return "D14_PRODUT";

		}

		public Double ESTOQUE;

		public Double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public String C9_PRODUTO;

		public String getC9_PRODUTO() {
			return this.C9_PRODUTO;
		}

		public Boolean C9_PRODUTOIsNullable() {
			return true;
		}

		public Boolean C9_PRODUTOIsKey() {
			return false;
		}

		public Integer C9_PRODUTOLength() {
			return 15;
		}

		public Integer C9_PRODUTOPrecision() {
			return 0;
		}

		public String C9_PRODUTODefault() {

			return null;

		}

		public String C9_PRODUTOComment() {

			return "";

		}

		public String C9_PRODUTOPattern() {

			return "";

		}

		public String C9_PRODUTOOriginalDbColumnName() {

			return "C9_PRODUTO";

		}

		public Double C9_QTDLIB;

		public Double getC9_QTDLIB() {
			return this.C9_QTDLIB;
		}

		public Boolean C9_QTDLIBIsNullable() {
			return true;
		}

		public Boolean C9_QTDLIBIsKey() {
			return false;
		}

		public Integer C9_QTDLIBLength() {
			return 15;
		}

		public Integer C9_QTDLIBPrecision() {
			return 0;
		}

		public String C9_QTDLIBDefault() {

			return "";

		}

		public String C9_QTDLIBComment() {

			return "";

		}

		public String C9_QTDLIBPattern() {

			return "";

		}

		public String C9_QTDLIBOriginalDbColumnName() {

			return "C9_QTDLIB";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.D14_PRODUT == null) ? 0 : this.D14_PRODUT.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row16Struct other = (row16Struct) obj;

			if (this.D14_PRODUT == null) {
				if (other.D14_PRODUT != null)
					return false;

			} else if (!this.D14_PRODUT.equals(other.D14_PRODUT))

				return false;

			return true;
		}

		public void copyDataTo(row16Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;
			other.ESTOQUE = this.ESTOQUE;
			other.C9_PRODUTO = this.C9_PRODUTO;
			other.C9_QTDLIB = this.C9_QTDLIB;

		}

		public void copyKeysDataTo(row16Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ESTOQUE = null;
					} else {
						this.ESTOQUE = dis.readDouble();
					}

					this.C9_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.C9_QTDLIB = null;
					} else {
						this.C9_QTDLIB = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

				// Double

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

				// String

				writeString(this.C9_PRODUTO, dos);

				// Double

				if (this.C9_QTDLIB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.C9_QTDLIB);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("D14_PRODUT=" + D14_PRODUT);
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",C9_PRODUTO=" + C9_PRODUTO);
			sb.append(",C9_QTDLIB=" + String.valueOf(C9_QTDLIB));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (D14_PRODUT == null) {
				sb.append("<null>");
			} else {
				sb.append(D14_PRODUT);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (C9_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_PRODUTO);
			}

			sb.append("|");

			if (C9_QTDLIB == null) {
				sb.append("<null>");
			} else {
				sb.append(C9_QTDLIB);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row16Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.D14_PRODUT, other.D14_PRODUT);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tHashInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tHashInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tHashInput_2");
		org.slf4j.MDC.put("_subJobPid", "6f7KCF_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row16Struct row16 = new row16Struct();

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_1", false);
				start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row16");

				int tos_count_tFileOutputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputExcel_1 = new StringBuilder();
							log4jParamters_tFileOutputExcel_1.append("Parameters:");
							log4jParamters_tFileOutputExcel_1.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append(
									"FILENAME" + " = " + "\"C:/Users/joao.santos/Downloads/Estoque - Copia.xlsx\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("SHEETNAME" + " = " + "\"Sheet1\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("APPEND_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FIRST_CELL_Y_ABSOLUTE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FONT" + " = " + "");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("IS_ALL_AUTO_SZIE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("AUTO_SZIE_SETTING" + " = " + "[{IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("D14_PRODUT") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ESTOQUE") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("C9_PRODUTO") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("C9_QTDLIB") + "}]");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("PROTECT_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("TRUNCATE_EXCEEDING_CHARACTERS" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("USE_SHARED_STRINGS_TABLE" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputExcel_1 - " + (log4jParamters_tFileOutputExcel_1));
						}
					}
					new BytesLimit65535_tFileOutputExcel_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputExcel_1", "tFileOutputExcel_1", "tFileOutputExcel");
					talendJobLogProcess(globalMap);
				}

				int columnIndex_tFileOutputExcel_1 = 0;
				boolean headerIsInserted_tFileOutputExcel_1 = false;

				String fileName_tFileOutputExcel_1 = "C:/Users/joao.santos/Downloads/Estoque - Copia.xlsx";
				int nb_line_tFileOutputExcel_1 = 0;
				org.talend.ExcelTool xlsxTool_tFileOutputExcel_1 = new org.talend.ExcelTool();
				xlsxTool_tFileOutputExcel_1.setUseSharedStringTable(false);

				xlsxTool_tFileOutputExcel_1.setTruncateExceedingCharacters(false);
				xlsxTool_tFileOutputExcel_1.setSheet("Sheet1");
				xlsxTool_tFileOutputExcel_1.setAppend(false, false, false);
				xlsxTool_tFileOutputExcel_1.setRecalculateFormula(false);
				xlsxTool_tFileOutputExcel_1.setXY(false, 0, 0, false);

				java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object> chm_tFileOutputExcel_1 = (java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object>) globalMap
						.get("concurrentHashMap");
				java.lang.Object lockObj_tFileOutputExcel_1 = chm_tFileOutputExcel_1
						.computeIfAbsent("EXCEL_OUTPUT_LOCK_OBJ_tFileOutputExcel_1", k -> new Object());
				synchronized (lockObj_tFileOutputExcel_1) {

					xlsxTool_tFileOutputExcel_1.prepareXlsxFile(fileName_tFileOutputExcel_1);

				}

				xlsxTool_tFileOutputExcel_1.setFont("");

				if (xlsxTool_tFileOutputExcel_1.getStartRow() == 0) {

					xlsxTool_tFileOutputExcel_1.addRow();

					xlsxTool_tFileOutputExcel_1.addCellValue("D14_PRODUT");

					xlsxTool_tFileOutputExcel_1.addCellValue("ESTOQUE");

					xlsxTool_tFileOutputExcel_1.addCellValue("C9_PRODUTO");

					xlsxTool_tFileOutputExcel_1.addCellValue("C9_QTDLIB");

					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;

				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tHashInput_2 begin ] start
				 */

				ok_Hash.put("tHashInput_2", false);
				start_Hash.put("tHashInput_2", System.currentTimeMillis());

				currentComponent = "tHashInput_2";

				cLabel = "Estoque";

				int tos_count_tHashInput_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_2", "Estoque", "tHashInput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tHashInput_2 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_2 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<EstoqueStruct> tHashFile_tHashInput_2 = mf_tHashInput_2
						.getAdvancedMemoryHashFile("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_2 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<EstoqueStruct> iterator_tHashInput_2 = tHashFile_tHashInput_2.iterator();
				while (iterator_tHashInput_2.hasNext()) {
					EstoqueStruct next_tHashInput_2 = iterator_tHashInput_2.next();

					row16.D14_PRODUT = next_tHashInput_2.D14_PRODUT;
					row16.ESTOQUE = next_tHashInput_2.ESTOQUE;
					row16.C9_PRODUTO = next_tHashInput_2.C9_PRODUTO;
					row16.C9_QTDLIB = next_tHashInput_2.C9_QTDLIB;

					/**
					 * [tHashInput_2 begin ] stop
					 */

					/**
					 * [tHashInput_2 main ] start
					 */

					currentComponent = "tHashInput_2";

					cLabel = "Estoque";

					tos_count_tHashInput_2++;

					/**
					 * [tHashInput_2 main ] stop
					 */

					/**
					 * [tHashInput_2 process_data_begin ] start
					 */

					currentComponent = "tHashInput_2";

					cLabel = "Estoque";

					/**
					 * [tHashInput_2 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputExcel_1 main ] start
					 */

					currentComponent = "tFileOutputExcel_1";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row16", "tHashInput_2", "Estoque", "tHashInput", "tFileOutputExcel_1",
							"tFileOutputExcel_1", "tFileOutputExcel"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row16 - " + (row16 == null ? "" : row16.toLogString()));
					}

					xlsxTool_tFileOutputExcel_1.addRow();

					if (row16.D14_PRODUT != null) {

						xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row16.D14_PRODUT));
					} else {
						xlsxTool_tFileOutputExcel_1.addCellNullValue();
					}

					if (row16.ESTOQUE != null) {

						xlsxTool_tFileOutputExcel_1.addCellValue(row16.ESTOQUE);
					} else {
						xlsxTool_tFileOutputExcel_1.addCellNullValue();
					}

					if (row16.C9_PRODUTO != null) {

						xlsxTool_tFileOutputExcel_1.addCellValue(String.valueOf(row16.C9_PRODUTO));
					} else {
						xlsxTool_tFileOutputExcel_1.addCellNullValue();
					}

					if (row16.C9_QTDLIB != null) {

						xlsxTool_tFileOutputExcel_1.addCellValue(row16.C9_QTDLIB);
					} else {
						xlsxTool_tFileOutputExcel_1.addCellNullValue();
					}

					nb_line_tFileOutputExcel_1++;

					log.debug(
							"tFileOutputExcel_1 - Writing the record " + nb_line_tFileOutputExcel_1 + " to the file.");

					tos_count_tFileOutputExcel_1++;

					/**
					 * [tFileOutputExcel_1 main ] stop
					 */

					/**
					 * [tFileOutputExcel_1 process_data_begin ] start
					 */

					currentComponent = "tFileOutputExcel_1";

					/**
					 * [tFileOutputExcel_1 process_data_begin ] stop
					 */

					/**
					 * [tFileOutputExcel_1 process_data_end ] start
					 */

					currentComponent = "tFileOutputExcel_1";

					/**
					 * [tFileOutputExcel_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_2 process_data_end ] start
					 */

					currentComponent = "tHashInput_2";

					cLabel = "Estoque";

					/**
					 * [tHashInput_2 process_data_end ] stop
					 */

					/**
					 * [tHashInput_2 end ] start
					 */

					currentComponent = "tHashInput_2";

					cLabel = "Estoque";

					nb_line_tHashInput_2++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_1");

				globalMap.put("tHashInput_2_NB_LINE", nb_line_tHashInput_2);

				ok_Hash.put("tHashInput_2", true);
				end_Hash.put("tHashInput_2", System.currentTimeMillis());

				/**
				 * [tHashInput_2 end ] stop
				 */

				/**
				 * [tFileOutputExcel_1 end ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				xlsxTool_tFileOutputExcel_1.writeExcel(fileName_tFileOutputExcel_1, true);

				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				log.debug("tFileOutputExcel_1 - Written records count: " + nb_line_tFileOutputExcel_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row16", 2, 0,
						"tHashInput_2", "Estoque", "tHashInput", "tFileOutputExcel_1", "tFileOutputExcel_1",
						"tFileOutputExcel", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_1 - " + ("Done."));

				ok_Hash.put("tFileOutputExcel_1", true);
				end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tHashInput_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tDBInput_1Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tHashInput_2 finally ] start
				 */

				currentComponent = "tHashInput_2";

				cLabel = "Estoque";

				/**
				 * [tHashInput_2 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_1 finally ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				/**
				 * [tFileOutputExcel_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tHashInput_2_SUBPROCESS_STATE", 1);
	}

	public static class row17Struct implements routines.system.IPersistableComparableLookupRow<row17Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String DB_ESTAL_PRODUTO;

		public String getDB_ESTAL_PRODUTO() {
			return this.DB_ESTAL_PRODUTO;
		}

		public Boolean DB_ESTAL_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_ESTAL_PRODUTOLength() {
			return 15;
		}

		public Integer DB_ESTAL_PRODUTOPrecision() {
			return 0;
		}

		public String DB_ESTAL_PRODUTODefault() {

			return null;

		}

		public String DB_ESTAL_PRODUTOComment() {

			return "";

		}

		public String DB_ESTAL_PRODUTOPattern() {

			return "";

		}

		public String DB_ESTAL_PRODUTOOriginalDbColumnName() {

			return "DB_ESTAL_PRODUTO";

		}

		public String DB_ESTAL_EMPRESA;

		public String getDB_ESTAL_EMPRESA() {
			return this.DB_ESTAL_EMPRESA;
		}

		public Boolean DB_ESTAL_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_ESTAL_EMPRESALength() {
			return 2;
		}

		public Integer DB_ESTAL_EMPRESAPrecision() {
			return 0;
		}

		public String DB_ESTAL_EMPRESADefault() {

			return null;

		}

		public String DB_ESTAL_EMPRESAComment() {

			return "";

		}

		public String DB_ESTAL_EMPRESAPattern() {

			return "";

		}

		public String DB_ESTAL_EMPRESAOriginalDbColumnName() {

			return "DB_ESTAL_EMPRESA";

		}

		public Double DB_ESTAL_QTDE_EST;

		public Double getDB_ESTAL_QTDE_EST() {
			return this.DB_ESTAL_QTDE_EST;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsKey() {
			return false;
		}

		public Integer DB_ESTAL_QTDE_ESTLength() {
			return 15;
		}

		public Integer DB_ESTAL_QTDE_ESTPrecision() {
			return 0;
		}

		public String DB_ESTAL_QTDE_ESTDefault() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTComment() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTPattern() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTOriginalDbColumnName() {

			return "DB_ESTAL_QTDE_EST";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_ESTAL_PRODUTO == null) ? 0 : this.DB_ESTAL_PRODUTO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row17Struct other = (row17Struct) obj;

			if (this.DB_ESTAL_PRODUTO == null) {
				if (other.DB_ESTAL_PRODUTO != null)
					return false;

			} else if (!this.DB_ESTAL_PRODUTO.equals(other.DB_ESTAL_PRODUTO))

				return false;

			return true;
		}

		public void copyDataTo(row17Struct other) {

			other.DB_ESTAL_PRODUTO = this.DB_ESTAL_PRODUTO;
			other.DB_ESTAL_EMPRESA = this.DB_ESTAL_EMPRESA;
			other.DB_ESTAL_QTDE_EST = this.DB_ESTAL_QTDE_EST;

		}

		public void copyKeysDataTo(row17Struct other) {

			other.DB_ESTAL_PRODUTO = this.DB_ESTAL_PRODUTO;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DB_ESTAL_EMPRESA = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.DB_ESTAL_QTDE_EST = null;
				} else {
					this.DB_ESTAL_QTDE_EST = dis.readDouble();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DB_ESTAL_EMPRESA = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DB_ESTAL_QTDE_EST = null;
				} else {
					this.DB_ESTAL_QTDE_EST = objectIn.readDouble();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.DB_ESTAL_EMPRESA, dos, oos);

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.DB_ESTAL_EMPRESA, dos, objectOut);

				if (this.DB_ESTAL_QTDE_EST == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_ESTAL_PRODUTO=" + DB_ESTAL_PRODUTO);
			sb.append(",DB_ESTAL_EMPRESA=" + DB_ESTAL_EMPRESA);
			sb.append(",DB_ESTAL_QTDE_EST=" + String.valueOf(DB_ESTAL_QTDE_EST));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_ESTAL_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_PRODUTO);
			}

			sb.append("|");

			if (DB_ESTAL_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_EMPRESA);
			}

			sb.append("|");

			if (DB_ESTAL_QTDE_EST == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_QTDE_EST);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row17Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_ESTAL_PRODUTO, other.DB_ESTAL_PRODUTO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_3
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_3> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String DB_ESTAL_PRODUTO;

		public String getDB_ESTAL_PRODUTO() {
			return this.DB_ESTAL_PRODUTO;
		}

		public Boolean DB_ESTAL_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_ESTAL_PRODUTOLength() {
			return 15;
		}

		public Integer DB_ESTAL_PRODUTOPrecision() {
			return 0;
		}

		public String DB_ESTAL_PRODUTODefault() {

			return null;

		}

		public String DB_ESTAL_PRODUTOComment() {

			return "";

		}

		public String DB_ESTAL_PRODUTOPattern() {

			return "";

		}

		public String DB_ESTAL_PRODUTOOriginalDbColumnName() {

			return "DB_ESTAL_PRODUTO";

		}

		public String DB_ESTAL_EMPRESA;

		public String getDB_ESTAL_EMPRESA() {
			return this.DB_ESTAL_EMPRESA;
		}

		public Boolean DB_ESTAL_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_ESTAL_EMPRESALength() {
			return 2;
		}

		public Integer DB_ESTAL_EMPRESAPrecision() {
			return 0;
		}

		public String DB_ESTAL_EMPRESADefault() {

			return null;

		}

		public String DB_ESTAL_EMPRESAComment() {

			return "";

		}

		public String DB_ESTAL_EMPRESAPattern() {

			return "";

		}

		public String DB_ESTAL_EMPRESAOriginalDbColumnName() {

			return "DB_ESTAL_EMPRESA";

		}

		public Double DB_ESTAL_QTDE_EST;

		public Double getDB_ESTAL_QTDE_EST() {
			return this.DB_ESTAL_QTDE_EST;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsKey() {
			return false;
		}

		public Integer DB_ESTAL_QTDE_ESTLength() {
			return 15;
		}

		public Integer DB_ESTAL_QTDE_ESTPrecision() {
			return 0;
		}

		public String DB_ESTAL_QTDE_ESTDefault() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTComment() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTPattern() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTOriginalDbColumnName() {

			return "DB_ESTAL_QTDE_EST";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

					this.DB_ESTAL_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

					this.DB_ESTAL_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_ESTAL_PRODUTO=" + DB_ESTAL_PRODUTO);
			sb.append(",DB_ESTAL_EMPRESA=" + DB_ESTAL_EMPRESA);
			sb.append(",DB_ESTAL_QTDE_EST=" + String.valueOf(DB_ESTAL_QTDE_EST));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_ESTAL_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_PRODUTO);
			}

			sb.append("|");

			if (DB_ESTAL_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_EMPRESA);
			}

			sb.append("|");

			if (DB_ESTAL_QTDE_EST == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_QTDE_EST);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_3 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OUTStruct implements routines.system.IPersistableRow<OUTStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String DB_ESTAL_PRODUTO;

		public String getDB_ESTAL_PRODUTO() {
			return this.DB_ESTAL_PRODUTO;
		}

		public Boolean DB_ESTAL_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_ESTAL_PRODUTOLength() {
			return 15;
		}

		public Integer DB_ESTAL_PRODUTOPrecision() {
			return 0;
		}

		public String DB_ESTAL_PRODUTODefault() {

			return null;

		}

		public String DB_ESTAL_PRODUTOComment() {

			return "";

		}

		public String DB_ESTAL_PRODUTOPattern() {

			return "";

		}

		public String DB_ESTAL_PRODUTOOriginalDbColumnName() {

			return "DB_ESTAL_PRODUTO";

		}

		public String DB_ESTAL_EMPRESA;

		public String getDB_ESTAL_EMPRESA() {
			return this.DB_ESTAL_EMPRESA;
		}

		public Boolean DB_ESTAL_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_ESTAL_EMPRESALength() {
			return 2;
		}

		public Integer DB_ESTAL_EMPRESAPrecision() {
			return 0;
		}

		public String DB_ESTAL_EMPRESADefault() {

			return null;

		}

		public String DB_ESTAL_EMPRESAComment() {

			return "";

		}

		public String DB_ESTAL_EMPRESAPattern() {

			return "";

		}

		public String DB_ESTAL_EMPRESAOriginalDbColumnName() {

			return "DB_ESTAL_EMPRESA";

		}

		public Double DB_ESTAL_QTDE_EST;

		public Double getDB_ESTAL_QTDE_EST() {
			return this.DB_ESTAL_QTDE_EST;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsKey() {
			return false;
		}

		public Integer DB_ESTAL_QTDE_ESTLength() {
			return 15;
		}

		public Integer DB_ESTAL_QTDE_ESTPrecision() {
			return 0;
		}

		public String DB_ESTAL_QTDE_ESTDefault() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTComment() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTPattern() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTOriginalDbColumnName() {

			return "DB_ESTAL_QTDE_EST";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

					this.DB_ESTAL_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_PRODUTO = readString(dis);

					this.DB_ESTAL_EMPRESA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_ESTAL_PRODUTO=" + DB_ESTAL_PRODUTO);
			sb.append(",DB_ESTAL_EMPRESA=" + DB_ESTAL_EMPRESA);
			sb.append(",DB_ESTAL_QTDE_EST=" + String.valueOf(DB_ESTAL_QTDE_EST));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_ESTAL_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_PRODUTO);
			}

			sb.append("|");

			if (DB_ESTAL_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_EMPRESA);
			}

			sb.append("|");

			if (DB_ESTAL_QTDE_EST == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_QTDE_EST);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OUTStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row20Struct implements routines.system.IPersistableRow<row20Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String ZC5_PEDMER;

		public String getZC5_PEDMER() {
			return this.ZC5_PEDMER;
		}

		public Boolean ZC5_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC5_PEDMERIsKey() {
			return true;
		}

		public Integer ZC5_PEDMERLength() {
			return 20;
		}

		public Integer ZC5_PEDMERPrecision() {
			return 0;
		}

		public String ZC5_PEDMERDefault() {

			return null;

		}

		public String ZC5_PEDMERComment() {

			return "";

		}

		public String ZC5_PEDMERPattern() {

			return "";

		}

		public String ZC5_PEDMEROriginalDbColumnName() {

			return "ZC5_PEDMER";

		}

		public String ZC5_FILIAL;

		public String getZC5_FILIAL() {
			return this.ZC5_FILIAL;
		}

		public Boolean ZC5_FILIALIsNullable() {
			return true;
		}

		public Boolean ZC5_FILIALIsKey() {
			return false;
		}

		public Integer ZC5_FILIALLength() {
			return 2;
		}

		public Integer ZC5_FILIALPrecision() {
			return 0;
		}

		public String ZC5_FILIALDefault() {

			return null;

		}

		public String ZC5_FILIALComment() {

			return "";

		}

		public String ZC5_FILIALPattern() {

			return "";

		}

		public String ZC5_FILIALOriginalDbColumnName() {

			return "ZC5_FILIAL";

		}

		public String ZC5_STATUS;

		public String getZC5_STATUS() {
			return this.ZC5_STATUS;
		}

		public Boolean ZC5_STATUSIsNullable() {
			return true;
		}

		public Boolean ZC5_STATUSIsKey() {
			return false;
		}

		public Integer ZC5_STATUSLength() {
			return 3;
		}

		public Integer ZC5_STATUSPrecision() {
			return 0;
		}

		public String ZC5_STATUSDefault() {

			return null;

		}

		public String ZC5_STATUSComment() {

			return "";

		}

		public String ZC5_STATUSPattern() {

			return "";

		}

		public String ZC5_STATUSOriginalDbColumnName() {

			return "ZC5_STATUS";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ZC5_PEDMER=" + ZC5_PEDMER);
			sb.append(",ZC5_FILIAL=" + ZC5_FILIAL);
			sb.append(",ZC5_STATUS=" + ZC5_STATUS);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ZC5_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_PEDMER);
			}

			sb.append("|");

			if (ZC5_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_FILIAL);
			}

			sb.append("|");

			if (ZC5_STATUS == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_STATUS);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row20Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row19Struct implements routines.system.IPersistableRow<row19Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String ZC5_PEDMER;

		public String getZC5_PEDMER() {
			return this.ZC5_PEDMER;
		}

		public Boolean ZC5_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC5_PEDMERIsKey() {
			return true;
		}

		public Integer ZC5_PEDMERLength() {
			return 20;
		}

		public Integer ZC5_PEDMERPrecision() {
			return 0;
		}

		public String ZC5_PEDMERDefault() {

			return null;

		}

		public String ZC5_PEDMERComment() {

			return "";

		}

		public String ZC5_PEDMERPattern() {

			return "";

		}

		public String ZC5_PEDMEROriginalDbColumnName() {

			return "ZC5_PEDMER";

		}

		public String ZC5_FILIAL;

		public String getZC5_FILIAL() {
			return this.ZC5_FILIAL;
		}

		public Boolean ZC5_FILIALIsNullable() {
			return true;
		}

		public Boolean ZC5_FILIALIsKey() {
			return false;
		}

		public Integer ZC5_FILIALLength() {
			return 2;
		}

		public Integer ZC5_FILIALPrecision() {
			return 0;
		}

		public String ZC5_FILIALDefault() {

			return null;

		}

		public String ZC5_FILIALComment() {

			return "";

		}

		public String ZC5_FILIALPattern() {

			return "";

		}

		public String ZC5_FILIALOriginalDbColumnName() {

			return "ZC5_FILIAL";

		}

		public String ZC5_STATUS;

		public String getZC5_STATUS() {
			return this.ZC5_STATUS;
		}

		public Boolean ZC5_STATUSIsNullable() {
			return true;
		}

		public Boolean ZC5_STATUSIsKey() {
			return false;
		}

		public Integer ZC5_STATUSLength() {
			return 3;
		}

		public Integer ZC5_STATUSPrecision() {
			return 0;
		}

		public String ZC5_STATUSDefault() {

			return null;

		}

		public String ZC5_STATUSComment() {

			return "";

		}

		public String ZC5_STATUSPattern() {

			return "";

		}

		public String ZC5_STATUSOriginalDbColumnName() {

			return "ZC5_STATUS";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ZC5_PEDMER == null) ? 0 : this.ZC5_PEDMER.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row19Struct other = (row19Struct) obj;

			if (this.ZC5_PEDMER == null) {
				if (other.ZC5_PEDMER != null)
					return false;

			} else if (!this.ZC5_PEDMER.equals(other.ZC5_PEDMER))

				return false;

			return true;
		}

		public void copyDataTo(row19Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;
			other.ZC5_FILIAL = this.ZC5_FILIAL;
			other.ZC5_STATUS = this.ZC5_STATUS;
			other.D_E_L_E_T_ = this.D_E_L_E_T_;

		}

		public void copyKeysDataTo(row19Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ZC5_PEDMER=" + ZC5_PEDMER);
			sb.append(",ZC5_FILIAL=" + ZC5_FILIAL);
			sb.append(",ZC5_STATUS=" + ZC5_STATUS);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ZC5_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_PEDMER);
			}

			sb.append("|");

			if (ZC5_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_FILIAL);
			}

			sb.append("|");

			if (ZC5_STATUS == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_STATUS);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row19Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ZC5_PEDMER, other.ZC5_PEDMER);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row18Struct implements routines.system.IPersistableRow<row18Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String ZC5_PEDMER;

		public String getZC5_PEDMER() {
			return this.ZC5_PEDMER;
		}

		public Boolean ZC5_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC5_PEDMERIsKey() {
			return true;
		}

		public Integer ZC5_PEDMERLength() {
			return 20;
		}

		public Integer ZC5_PEDMERPrecision() {
			return 0;
		}

		public String ZC5_PEDMERDefault() {

			return null;

		}

		public String ZC5_PEDMERComment() {

			return "";

		}

		public String ZC5_PEDMERPattern() {

			return "";

		}

		public String ZC5_PEDMEROriginalDbColumnName() {

			return "ZC5_PEDMER";

		}

		public String ZC5_FILIAL;

		public String getZC5_FILIAL() {
			return this.ZC5_FILIAL;
		}

		public Boolean ZC5_FILIALIsNullable() {
			return true;
		}

		public Boolean ZC5_FILIALIsKey() {
			return false;
		}

		public Integer ZC5_FILIALLength() {
			return 2;
		}

		public Integer ZC5_FILIALPrecision() {
			return 0;
		}

		public String ZC5_FILIALDefault() {

			return null;

		}

		public String ZC5_FILIALComment() {

			return "";

		}

		public String ZC5_FILIALPattern() {

			return "";

		}

		public String ZC5_FILIALOriginalDbColumnName() {

			return "ZC5_FILIAL";

		}

		public String ZC5_STATUS;

		public String getZC5_STATUS() {
			return this.ZC5_STATUS;
		}

		public Boolean ZC5_STATUSIsNullable() {
			return true;
		}

		public Boolean ZC5_STATUSIsKey() {
			return false;
		}

		public Integer ZC5_STATUSLength() {
			return 3;
		}

		public Integer ZC5_STATUSPrecision() {
			return 0;
		}

		public String ZC5_STATUSDefault() {

			return null;

		}

		public String ZC5_STATUSComment() {

			return "";

		}

		public String ZC5_STATUSPattern() {

			return "";

		}

		public String ZC5_STATUSOriginalDbColumnName() {

			return "ZC5_STATUS";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ZC5_PEDMER == null) ? 0 : this.ZC5_PEDMER.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row18Struct other = (row18Struct) obj;

			if (this.ZC5_PEDMER == null) {
				if (other.ZC5_PEDMER != null)
					return false;

			} else if (!this.ZC5_PEDMER.equals(other.ZC5_PEDMER))

				return false;

			return true;
		}

		public void copyDataTo(row18Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;
			other.ZC5_FILIAL = this.ZC5_FILIAL;
			other.ZC5_STATUS = this.ZC5_STATUS;
			other.D_E_L_E_T_ = this.D_E_L_E_T_;

		}

		public void copyKeysDataTo(row18Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ZC5_PEDMER=" + ZC5_PEDMER);
			sb.append(",ZC5_FILIAL=" + ZC5_FILIAL);
			sb.append(",ZC5_STATUS=" + ZC5_STATUS);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ZC5_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_PEDMER);
			}

			sb.append("|");

			if (ZC5_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_FILIAL);
			}

			sb.append("|");

			if (ZC5_STATUS == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_STATUS);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row18Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ZC5_PEDMER, other.ZC5_PEDMER);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tDBInput_9Struct implements routines.system.IPersistableRow<after_tDBInput_9Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String ZC5_PEDMER;

		public String getZC5_PEDMER() {
			return this.ZC5_PEDMER;
		}

		public Boolean ZC5_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC5_PEDMERIsKey() {
			return true;
		}

		public Integer ZC5_PEDMERLength() {
			return 20;
		}

		public Integer ZC5_PEDMERPrecision() {
			return 0;
		}

		public String ZC5_PEDMERDefault() {

			return null;

		}

		public String ZC5_PEDMERComment() {

			return "";

		}

		public String ZC5_PEDMERPattern() {

			return "";

		}

		public String ZC5_PEDMEROriginalDbColumnName() {

			return "ZC5_PEDMER";

		}

		public String ZC5_FILIAL;

		public String getZC5_FILIAL() {
			return this.ZC5_FILIAL;
		}

		public Boolean ZC5_FILIALIsNullable() {
			return true;
		}

		public Boolean ZC5_FILIALIsKey() {
			return false;
		}

		public Integer ZC5_FILIALLength() {
			return 2;
		}

		public Integer ZC5_FILIALPrecision() {
			return 0;
		}

		public String ZC5_FILIALDefault() {

			return null;

		}

		public String ZC5_FILIALComment() {

			return "";

		}

		public String ZC5_FILIALPattern() {

			return "";

		}

		public String ZC5_FILIALOriginalDbColumnName() {

			return "ZC5_FILIAL";

		}

		public String ZC5_STATUS;

		public String getZC5_STATUS() {
			return this.ZC5_STATUS;
		}

		public Boolean ZC5_STATUSIsNullable() {
			return true;
		}

		public Boolean ZC5_STATUSIsKey() {
			return false;
		}

		public Integer ZC5_STATUSLength() {
			return 3;
		}

		public Integer ZC5_STATUSPrecision() {
			return 0;
		}

		public String ZC5_STATUSDefault() {

			return null;

		}

		public String ZC5_STATUSComment() {

			return "";

		}

		public String ZC5_STATUSPattern() {

			return "";

		}

		public String ZC5_STATUSOriginalDbColumnName() {

			return "ZC5_STATUS";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ZC5_PEDMER == null) ? 0 : this.ZC5_PEDMER.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tDBInput_9Struct other = (after_tDBInput_9Struct) obj;

			if (this.ZC5_PEDMER == null) {
				if (other.ZC5_PEDMER != null)
					return false;

			} else if (!this.ZC5_PEDMER.equals(other.ZC5_PEDMER))

				return false;

			return true;
		}

		public void copyDataTo(after_tDBInput_9Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;
			other.ZC5_FILIAL = this.ZC5_FILIAL;
			other.ZC5_STATUS = this.ZC5_STATUS;
			other.D_E_L_E_T_ = this.D_E_L_E_T_;

		}

		public void copyKeysDataTo(after_tDBInput_9Struct other) {

			other.ZC5_PEDMER = this.ZC5_PEDMER;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC5_PEDMER = readString(dis);

					this.ZC5_FILIAL = readString(dis);

					this.ZC5_STATUS = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ZC5_PEDMER, dos);

				// String

				writeString(this.ZC5_FILIAL, dos);

				// String

				writeString(this.ZC5_STATUS, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ZC5_PEDMER=" + ZC5_PEDMER);
			sb.append(",ZC5_FILIAL=" + ZC5_FILIAL);
			sb.append(",ZC5_STATUS=" + ZC5_STATUS);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ZC5_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_PEDMER);
			}

			sb.append("|");

			if (ZC5_FILIAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_FILIAL);
			}

			sb.append("|");

			if (ZC5_STATUS == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC5_STATUS);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ZC5_PEDMER, other.ZC5_PEDMER);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_9Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_9_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_9");
		org.slf4j.MDC.put("_subJobPid", "iuRhPs_" + subJobPidCounter.getAndIncrement());

		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_10Process(globalMap);

				row18Struct row18 = new row18Struct();
				row19Struct row19 = new row19Struct();
				row20Struct row20 = new row20Struct();
				OUTStruct OUT = new OUTStruct();
				row17Struct row17 = new row17Struct();

				/**
				 * [tAggregateRow_3_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_3_AGGOUT", false);
				start_Hash.put("tAggregateRow_3_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "OUT");

				int tos_count_tAggregateRow_3_AGGOUT = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_3_AGGOUT - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_3_AGGOUT {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_3_AGGOUT = new StringBuilder();
							log4jParamters_tAggregateRow_3_AGGOUT.append("Parameters:");
							log4jParamters_tAggregateRow_3_AGGOUT.append("DESTINATION" + " = " + "tAggregateRow_3");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT
									.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN=" + ("DB_ESTAL_PRODUTO")
											+ ", INPUT_COLUMN=" + ("DB_ESTAL_PRODUTO") + "}, {OUTPUT_COLUMN="
											+ ("DB_ESTAL_EMPRESA") + ", INPUT_COLUMN=" + ("DB_ESTAL_EMPRESA") + "}]");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("DB_ESTAL_QTDE_EST") + ", INPUT_COLUMN=" + ("DB_ESTAL_QTDE_EST")
									+ ", IGNORE_NULL=" + ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_3_AGGOUT.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_3_AGGOUT.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_3_AGGOUT - " + (log4jParamters_tAggregateRow_3_AGGOUT));
						}
					}
					new BytesLimit65535_tAggregateRow_3_AGGOUT().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_3_AGGOUT", "tAggregateRow_3_AGGOUT", "tAggregateOut");
					talendJobLogProcess(globalMap);
				}

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_3 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_3 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_3 utilClass_tAggregateRow_3 = new UtilClass_tAggregateRow_3();

				class AggOperationStruct_tAggregateRow_3 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String DB_ESTAL_PRODUTO;
					String DB_ESTAL_EMPRESA;
					BigDecimal DB_ESTAL_QTDE_EST_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result
									+ ((this.DB_ESTAL_PRODUTO == null) ? 0 : this.DB_ESTAL_PRODUTO.hashCode());

							result = prime * result
									+ ((this.DB_ESTAL_EMPRESA == null) ? 0 : this.DB_ESTAL_EMPRESA.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_3 other = (AggOperationStruct_tAggregateRow_3) obj;

						if (this.DB_ESTAL_PRODUTO == null) {
							if (other.DB_ESTAL_PRODUTO != null)
								return false;
						} else if (!this.DB_ESTAL_PRODUTO.equals(other.DB_ESTAL_PRODUTO))
							return false;

						if (this.DB_ESTAL_EMPRESA == null) {
							if (other.DB_ESTAL_EMPRESA != null)
								return false;
						} else if (!this.DB_ESTAL_EMPRESA.equals(other.DB_ESTAL_EMPRESA))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_3 operation_result_tAggregateRow_3 = null;
				AggOperationStruct_tAggregateRow_3 operation_finder_tAggregateRow_3 = new AggOperationStruct_tAggregateRow_3();
				java.util.Map<AggOperationStruct_tAggregateRow_3, AggOperationStruct_tAggregateRow_3> hash_tAggregateRow_3 = new java.util.HashMap<AggOperationStruct_tAggregateRow_3, AggOperationStruct_tAggregateRow_3>();

				/**
				 * [tAggregateRow_3_AGGOUT begin ] stop
				 */

				/**
				 * [tMap_6 begin ] start
				 */

				ok_Hash.put("tMap_6", false);
				start_Hash.put("tMap_6", System.currentTimeMillis());

				currentComponent = "tMap_6";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row20");

				int tos_count_tMap_6 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_6 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_6 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_6 = new StringBuilder();
							log4jParamters_tMap_6.append("Parameters:");
							log4jParamters_tMap_6.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_6.append(" | ");
							log4jParamters_tMap_6.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_6.append(" | ");
							log4jParamters_tMap_6.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_6.append(" | ");
							log4jParamters_tMap_6.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_6.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_6 - " + (log4jParamters_tMap_6));
						}
					}
					new BytesLimit65535_tMap_6().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_6", "tMap_6", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row20_tMap_6 = 0;

				int count_row22_tMap_6 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row22Struct> tHash_Lookup_row22 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row22Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row22Struct>) globalMap
						.get("tHash_Lookup_row22"));

				row22Struct row22HashKey = new row22Struct();
				row22Struct row22Default = new row22Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_6__Struct {
				}
				Var__tMap_6__Struct Var__tMap_6 = new Var__tMap_6__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_OUT_tMap_6 = 0;

				OUTStruct OUT_tmp = new OUTStruct();
// ###############################

				/**
				 * [tMap_6 begin ] stop
				 */

				/**
				 * [tFilterRow_28 begin ] start
				 */

				ok_Hash.put("tFilterRow_28", false);
				start_Hash.put("tFilterRow_28", System.currentTimeMillis());

				currentComponent = "tFilterRow_28";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row19");

				int tos_count_tFilterRow_28 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_28 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_28 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_28 = new StringBuilder();
							log4jParamters_tFilterRow_28.append("Parameters:");
							log4jParamters_tFilterRow_28.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_28.append(" | ");
							log4jParamters_tFilterRow_28
									.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("!=") + ", RVALUE=" + ("\"*\"")
											+ ", INPUT_COLUMN=" + ("D_E_L_E_T_") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_28.append(" | ");
							log4jParamters_tFilterRow_28.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_28.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_28 - " + (log4jParamters_tFilterRow_28));
						}
					}
					new BytesLimit65535_tFilterRow_28().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_28", "tFilterRow_28", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_28 = 0;
				int nb_line_ok_tFilterRow_28 = 0;
				int nb_line_reject_tFilterRow_28 = 0;

				class Operator_tFilterRow_28 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_28(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_28 begin ] stop
				 */

				/**
				 * [tFilterRow_27 begin ] start
				 */

				ok_Hash.put("tFilterRow_27", false);
				start_Hash.put("tFilterRow_27", System.currentTimeMillis());

				currentComponent = "tFilterRow_27";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row18");

				int tos_count_tFilterRow_27 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_27 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_27 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_27 = new StringBuilder();
							log4jParamters_tFilterRow_27.append("Parameters:");
							log4jParamters_tFilterRow_27.append("LOGICAL_OP" + " = " + "||");
							log4jParamters_tFilterRow_27.append(" | ");
							log4jParamters_tFilterRow_27.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==")
									+ ", RVALUE=" + ("\"INS\"") + ", INPUT_COLUMN=" + ("ZC5_STATUS") + ", FUNCTION="
									+ ("") + "}, {OPERATOR=" + ("==") + ", RVALUE=" + ("\"ERR\"") + ", INPUT_COLUMN="
									+ ("ZC5_STATUS") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_27.append(" | ");
							log4jParamters_tFilterRow_27.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_27.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_27 - " + (log4jParamters_tFilterRow_27));
						}
					}
					new BytesLimit65535_tFilterRow_27().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_27", "tFilterRow_27", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_27 = 0;
				int nb_line_ok_tFilterRow_27 = 0;
				int nb_line_reject_tFilterRow_27 = 0;

				class Operator_tFilterRow_27 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_27(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_27 begin ] stop
				 */

				/**
				 * [tDBInput_9 begin ] start
				 */

				ok_Hash.put("tDBInput_9", false);
				start_Hash.put("tDBInput_9", System.currentTimeMillis());

				currentComponent = "tDBInput_9";

				cLabel = "Input_ZC5";

				int tos_count_tDBInput_9 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_9 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_9 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_9 = new StringBuilder();
							log4jParamters_tDBInput_9.append("Parameters:");
							log4jParamters_tDBInput_9.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("DBNAME" + " = " + "\"TOTVS\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:SmC8zSAaAHlGBdZNBaow4rNAoZitkdQDvWDHFbHf2pOz3pp9No7dL8Xm")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("TABLE" + " = " + "\"ZC5010\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("QUERY" + " = "
									+ "\"                SELECT ZC5_PEDMER,  					ZC5_FILIAL,  					ZC5_STATUS,  					D_E_L_E_T_                  FROM  ZC5010\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ZC5_PEDMER") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("ZC5_FILIAL") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("ZC5_STATUS")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("D_E_L_E_T_") + "}]");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_9.append(" | ");
							log4jParamters_tDBInput_9.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_9.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_9 - " + (log4jParamters_tDBInput_9));
						}
					}
					new BytesLimit65535_tDBInput_9().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_9", "Input_ZC5", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_9 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_9 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_9 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_9, talendToDBArray_tDBInput_9);
				int nb_line_tDBInput_9 = 0;
				java.sql.Connection conn_tDBInput_9 = null;
				String driverClass_tDBInput_9 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_9 = java.lang.Class.forName(driverClass_tDBInput_9);
				String dbUser_tDBInput_9 = "analytics";

				final String decryptedPassword_tDBInput_9 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:2m5nvfP8ciBtmkME7FevQA0c7ygXfmwpjioINPKnIjw5pN8XIJip5DwH");

				String dbPwd_tDBInput_9 = decryptedPassword_tDBInput_9;

				String port_tDBInput_9 = "1433";
				String dbname_tDBInput_9 = "TOTVS";
				String url_tDBInput_9 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_9)) {
					url_tDBInput_9 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_9)) {
					url_tDBInput_9 += ";databaseName=" + "TOTVS";
				}
				url_tDBInput_9 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_9 = "";

				log.debug("tDBInput_9 - Driver ClassName: " + driverClass_tDBInput_9 + ".");

				log.debug("tDBInput_9 - Connection attempt to '" + url_tDBInput_9 + "' with the username '"
						+ dbUser_tDBInput_9 + "'.");

				conn_tDBInput_9 = java.sql.DriverManager.getConnection(url_tDBInput_9, dbUser_tDBInput_9,
						dbPwd_tDBInput_9);
				log.debug("tDBInput_9 - Connection to '" + url_tDBInput_9 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_9 = conn_tDBInput_9.createStatement();

				String dbquery_tDBInput_9 = "                SELECT ZC5_PEDMER,\n					ZC5_FILIAL,\n					ZC5_STATUS,\n					D_E_L_E_T_\n                FROM  ZC5010";

				log.debug("tDBInput_9 - Executing the query: '" + dbquery_tDBInput_9 + "'.");

				globalMap.put("tDBInput_9_QUERY", dbquery_tDBInput_9);

				java.sql.ResultSet rs_tDBInput_9 = null;

				try {
					rs_tDBInput_9 = stmt_tDBInput_9.executeQuery(dbquery_tDBInput_9);
					java.sql.ResultSetMetaData rsmd_tDBInput_9 = rs_tDBInput_9.getMetaData();
					int colQtyInRs_tDBInput_9 = rsmd_tDBInput_9.getColumnCount();

					String tmpContent_tDBInput_9 = null;

					log.debug("tDBInput_9 - Retrieving records from the database.");

					while (rs_tDBInput_9.next()) {
						nb_line_tDBInput_9++;

						if (colQtyInRs_tDBInput_9 < 1) {
							row18.ZC5_PEDMER = null;
						} else {

							tmpContent_tDBInput_9 = rs_tDBInput_9.getString(1);
							if (tmpContent_tDBInput_9 != null) {
								if (talendToDBList_tDBInput_9.contains(
										rsmd_tDBInput_9.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									row18.ZC5_PEDMER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_9);
								} else {
									row18.ZC5_PEDMER = tmpContent_tDBInput_9;
								}
							} else {
								row18.ZC5_PEDMER = null;
							}
						}
						if (colQtyInRs_tDBInput_9 < 2) {
							row18.ZC5_FILIAL = null;
						} else {

							tmpContent_tDBInput_9 = rs_tDBInput_9.getString(2);
							if (tmpContent_tDBInput_9 != null) {
								if (talendToDBList_tDBInput_9.contains(
										rsmd_tDBInput_9.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row18.ZC5_FILIAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_9);
								} else {
									row18.ZC5_FILIAL = tmpContent_tDBInput_9;
								}
							} else {
								row18.ZC5_FILIAL = null;
							}
						}
						if (colQtyInRs_tDBInput_9 < 3) {
							row18.ZC5_STATUS = null;
						} else {

							tmpContent_tDBInput_9 = rs_tDBInput_9.getString(3);
							if (tmpContent_tDBInput_9 != null) {
								if (talendToDBList_tDBInput_9.contains(
										rsmd_tDBInput_9.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row18.ZC5_STATUS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_9);
								} else {
									row18.ZC5_STATUS = tmpContent_tDBInput_9;
								}
							} else {
								row18.ZC5_STATUS = null;
							}
						}
						if (colQtyInRs_tDBInput_9 < 4) {
							row18.D_E_L_E_T_ = null;
						} else {

							tmpContent_tDBInput_9 = rs_tDBInput_9.getString(4);
							if (tmpContent_tDBInput_9 != null) {
								if (talendToDBList_tDBInput_9.contains(
										rsmd_tDBInput_9.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row18.D_E_L_E_T_ = FormatterUtils.formatUnwithE(tmpContent_tDBInput_9);
								} else {
									row18.D_E_L_E_T_ = tmpContent_tDBInput_9;
								}
							} else {
								row18.D_E_L_E_T_ = null;
							}
						}

						log.debug("tDBInput_9 - Retrieving the record " + nb_line_tDBInput_9 + ".");

						/**
						 * [tDBInput_9 begin ] stop
						 */

						/**
						 * [tDBInput_9 main ] start
						 */

						currentComponent = "tDBInput_9";

						cLabel = "Input_ZC5";

						tos_count_tDBInput_9++;

						/**
						 * [tDBInput_9 main ] stop
						 */

						/**
						 * [tDBInput_9 process_data_begin ] start
						 */

						currentComponent = "tDBInput_9";

						cLabel = "Input_ZC5";

						/**
						 * [tDBInput_9 process_data_begin ] stop
						 */

						/**
						 * [tFilterRow_27 main ] start
						 */

						currentComponent = "tFilterRow_27";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row18", "tDBInput_9", "Input_ZC5", "tMSSqlInput", "tFilterRow_27", "tFilterRow_27",
								"tFilterRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row18 - " + (row18 == null ? "" : row18.toLogString()));
						}

						row19 = null;
						Operator_tFilterRow_27 ope_tFilterRow_27 = new Operator_tFilterRow_27("||");
						ope_tFilterRow_27.matches(
								(row18.ZC5_STATUS == null ? false : row18.ZC5_STATUS.compareTo("INS") == 0),
								"ZC5_STATUS.compareTo(\"INS\") == 0 failed");
						ope_tFilterRow_27.matches(
								(row18.ZC5_STATUS == null ? false : row18.ZC5_STATUS.compareTo("ERR") == 0),
								"ZC5_STATUS.compareTo(\"ERR\") == 0 failed");

						if (ope_tFilterRow_27.getMatchFlag()) {
							if (row19 == null) {
								row19 = new row19Struct();
							}
							row19.ZC5_PEDMER = row18.ZC5_PEDMER;
							row19.ZC5_FILIAL = row18.ZC5_FILIAL;
							row19.ZC5_STATUS = row18.ZC5_STATUS;
							row19.D_E_L_E_T_ = row18.D_E_L_E_T_;
							log.debug("tFilterRow_27 - Process the record " + (nb_line_tFilterRow_27 + 1) + ".");

							nb_line_ok_tFilterRow_27++;
						} else {
							nb_line_reject_tFilterRow_27++;
						}

						nb_line_tFilterRow_27++;

						tos_count_tFilterRow_27++;

						/**
						 * [tFilterRow_27 main ] stop
						 */

						/**
						 * [tFilterRow_27 process_data_begin ] start
						 */

						currentComponent = "tFilterRow_27";

						/**
						 * [tFilterRow_27 process_data_begin ] stop
						 */
// Start of branch "row19"
						if (row19 != null) {

							/**
							 * [tFilterRow_28 main ] start
							 */

							currentComponent = "tFilterRow_28";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row19", "tFilterRow_27", "tFilterRow_27", "tFilterRow", "tFilterRow_28",
									"tFilterRow_28", "tFilterRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row19 - " + (row19 == null ? "" : row19.toLogString()));
							}

							row20 = null;
							Operator_tFilterRow_28 ope_tFilterRow_28 = new Operator_tFilterRow_28("&&");
							ope_tFilterRow_28.matches(
									(row19.D_E_L_E_T_ == null ? false : row19.D_E_L_E_T_.compareTo("*") != 0),
									"D_E_L_E_T_.compareTo(\"*\") != 0 failed");

							if (ope_tFilterRow_28.getMatchFlag()) {
								if (row20 == null) {
									row20 = new row20Struct();
								}
								row20.ZC5_PEDMER = row19.ZC5_PEDMER;
								row20.ZC5_FILIAL = row19.ZC5_FILIAL;
								row20.ZC5_STATUS = row19.ZC5_STATUS;
								row20.D_E_L_E_T_ = row19.D_E_L_E_T_;
								log.debug("tFilterRow_28 - Process the record " + (nb_line_tFilterRow_28 + 1) + ".");

								nb_line_ok_tFilterRow_28++;
							} else {
								nb_line_reject_tFilterRow_28++;
							}

							nb_line_tFilterRow_28++;

							tos_count_tFilterRow_28++;

							/**
							 * [tFilterRow_28 main ] stop
							 */

							/**
							 * [tFilterRow_28 process_data_begin ] start
							 */

							currentComponent = "tFilterRow_28";

							/**
							 * [tFilterRow_28 process_data_begin ] stop
							 */
// Start of branch "row20"
							if (row20 != null) {

								/**
								 * [tMap_6 main ] start
								 */

								currentComponent = "tMap_6";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row20", "tFilterRow_28", "tFilterRow_28", "tFilterRow", "tMap_6", "tMap_6",
										"tMap"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row20 - " + (row20 == null ? "" : row20.toLogString()));
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_6 = false;

								row22Struct row22 = null;

								// ###############################
								// # Input tables (lookups)

								boolean rejectedInnerJoin_tMap_6 = false;
								boolean mainRowRejected_tMap_6 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row22"
								///////////////////////////////////////////////

								boolean forceLooprow22 = false;

								row22Struct row22ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_6) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_6 = false;

									row22HashKey.ZC6_PEDMER = row20.ZC5_PEDMER;

									row22HashKey.hashCodeDirty = true;

									tHash_Lookup_row22.lookup(row22HashKey);

									if (!tHash_Lookup_row22.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_6 = true;

										forceLooprow22 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								else { // G 20 - G 21
									forceLooprow22 = true;
								} // G 21

								while ((tHash_Lookup_row22 != null && tHash_Lookup_row22.hasNext()) || forceLooprow22) { // G_TM_M_043

									// CALL close loop of lookup 'row22'

									row22Struct fromLookup_row22 = null;
									row22 = row22Default;

									if (!forceLooprow22) { // G 46

										fromLookup_row22 = tHash_Lookup_row22.next();

										if (fromLookup_row22 != null) {
											row22 = fromLookup_row22;
										}

									} // G 46

									forceLooprow22 = false;

									// ###############################
									{ // start of Var scope

										// ###############################
										// # Vars tables

										Var__tMap_6__Struct Var = Var__tMap_6;// ###############################
										// ###############################
										// # Output tables

										OUT = null;

										if (!rejectedInnerJoin_tMap_6) {

// # Output table : 'OUT'
											count_OUT_tMap_6++;

											OUT_tmp.DB_ESTAL_PRODUTO = row22.DB_ESTAL_PRODUTO;
											OUT_tmp.DB_ESTAL_EMPRESA = row22.DB_ESTAL_EMPRESA;
											OUT_tmp.DB_ESTAL_QTDE_EST = row22.DB_ESTAL_QTDE_EST;
											OUT = OUT_tmp;
											log.debug("tMap_6 - Outputting the record " + count_OUT_tMap_6
													+ " of the output table 'OUT'.");

										} // closing inner join bracket (2)
// ###############################

									} // end of Var scope

									rejectedInnerJoin_tMap_6 = false;

									tos_count_tMap_6++;

									/**
									 * [tMap_6 main ] stop
									 */

									/**
									 * [tMap_6 process_data_begin ] start
									 */

									currentComponent = "tMap_6";

									/**
									 * [tMap_6 process_data_begin ] stop
									 */
// Start of branch "OUT"
									if (OUT != null) {

										/**
										 * [tAggregateRow_3_AGGOUT main ] start
										 */

										currentVirtualComponent = "tAggregateRow_3";

										currentComponent = "tAggregateRow_3_AGGOUT";

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "OUT", "tMap_6", "tMap_6", "tMap", "tAggregateRow_3_AGGOUT",
												"tAggregateRow_3_AGGOUT", "tAggregateOut"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("OUT - " + (OUT == null ? "" : OUT.toLogString()));
										}

										operation_finder_tAggregateRow_3.DB_ESTAL_PRODUTO = OUT.DB_ESTAL_PRODUTO;
										operation_finder_tAggregateRow_3.DB_ESTAL_EMPRESA = OUT.DB_ESTAL_EMPRESA;

										operation_finder_tAggregateRow_3.hashCodeDirty = true;

										operation_result_tAggregateRow_3 = hash_tAggregateRow_3
												.get(operation_finder_tAggregateRow_3);

										if (operation_result_tAggregateRow_3 == null) { // G_OutMain_AggR_001

											operation_result_tAggregateRow_3 = new AggOperationStruct_tAggregateRow_3();

											operation_result_tAggregateRow_3.DB_ESTAL_PRODUTO = operation_finder_tAggregateRow_3.DB_ESTAL_PRODUTO;
											operation_result_tAggregateRow_3.DB_ESTAL_EMPRESA = operation_finder_tAggregateRow_3.DB_ESTAL_EMPRESA;

											hash_tAggregateRow_3.put(operation_result_tAggregateRow_3,
													operation_result_tAggregateRow_3);

										} // G_OutMain_AggR_001

										if (operation_result_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum == null) {
											operation_result_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum = new BigDecimal(0)
													.setScale(0);
										}
										operation_result_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum = operation_result_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum
												.add(new BigDecimal(String.valueOf(OUT.DB_ESTAL_QTDE_EST)));

										tos_count_tAggregateRow_3_AGGOUT++;

										/**
										 * [tAggregateRow_3_AGGOUT main ] stop
										 */

										/**
										 * [tAggregateRow_3_AGGOUT process_data_begin ] start
										 */

										currentVirtualComponent = "tAggregateRow_3";

										currentComponent = "tAggregateRow_3_AGGOUT";

										/**
										 * [tAggregateRow_3_AGGOUT process_data_begin ] stop
										 */

										/**
										 * [tAggregateRow_3_AGGOUT process_data_end ] start
										 */

										currentVirtualComponent = "tAggregateRow_3";

										currentComponent = "tAggregateRow_3_AGGOUT";

										/**
										 * [tAggregateRow_3_AGGOUT process_data_end ] stop
										 */

									} // End of branch "OUT"

								} // close loop of lookup 'row22' // G_TM_M_043

								/**
								 * [tMap_6 process_data_end ] start
								 */

								currentComponent = "tMap_6";

								/**
								 * [tMap_6 process_data_end ] stop
								 */

							} // End of branch "row20"

							/**
							 * [tFilterRow_28 process_data_end ] start
							 */

							currentComponent = "tFilterRow_28";

							/**
							 * [tFilterRow_28 process_data_end ] stop
							 */

						} // End of branch "row19"

						/**
						 * [tFilterRow_27 process_data_end ] start
						 */

						currentComponent = "tFilterRow_27";

						/**
						 * [tFilterRow_27 process_data_end ] stop
						 */

						/**
						 * [tDBInput_9 process_data_end ] start
						 */

						currentComponent = "tDBInput_9";

						cLabel = "Input_ZC5";

						/**
						 * [tDBInput_9 process_data_end ] stop
						 */

						/**
						 * [tDBInput_9 end ] start
						 */

						currentComponent = "tDBInput_9";

						cLabel = "Input_ZC5";

					}
				} finally {
					if (rs_tDBInput_9 != null) {
						rs_tDBInput_9.close();
					}
					if (stmt_tDBInput_9 != null) {
						stmt_tDBInput_9.close();
					}
					if (conn_tDBInput_9 != null && !conn_tDBInput_9.isClosed()) {

						log.debug("tDBInput_9 - Closing the connection to the database.");

						conn_tDBInput_9.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_9 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_9_NB_LINE", nb_line_tDBInput_9);
				log.debug("tDBInput_9 - Retrieved records count: " + nb_line_tDBInput_9 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_9 - " + ("Done."));

				ok_Hash.put("tDBInput_9", true);
				end_Hash.put("tDBInput_9", System.currentTimeMillis());

				/**
				 * [tDBInput_9 end ] stop
				 */

				/**
				 * [tFilterRow_27 end ] start
				 */

				currentComponent = "tFilterRow_27";

				globalMap.put("tFilterRow_27_NB_LINE", nb_line_tFilterRow_27);
				globalMap.put("tFilterRow_27_NB_LINE_OK", nb_line_ok_tFilterRow_27);
				globalMap.put("tFilterRow_27_NB_LINE_REJECT", nb_line_reject_tFilterRow_27);

				log.info("tFilterRow_27 - Processed records count:" + nb_line_tFilterRow_27 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_27 + ". Rejected records count:" + nb_line_reject_tFilterRow_27 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row18", 2, 0,
						"tDBInput_9", "Input_ZC5", "tMSSqlInput", "tFilterRow_27", "tFilterRow_27", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_27 - " + ("Done."));

				ok_Hash.put("tFilterRow_27", true);
				end_Hash.put("tFilterRow_27", System.currentTimeMillis());

				/**
				 * [tFilterRow_27 end ] stop
				 */

				/**
				 * [tFilterRow_28 end ] start
				 */

				currentComponent = "tFilterRow_28";

				globalMap.put("tFilterRow_28_NB_LINE", nb_line_tFilterRow_28);
				globalMap.put("tFilterRow_28_NB_LINE_OK", nb_line_ok_tFilterRow_28);
				globalMap.put("tFilterRow_28_NB_LINE_REJECT", nb_line_reject_tFilterRow_28);

				log.info("tFilterRow_28 - Processed records count:" + nb_line_tFilterRow_28 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_28 + ". Rejected records count:" + nb_line_reject_tFilterRow_28 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row19", 2, 0,
						"tFilterRow_27", "tFilterRow_27", "tFilterRow", "tFilterRow_28", "tFilterRow_28", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_28 - " + ("Done."));

				ok_Hash.put("tFilterRow_28", true);
				end_Hash.put("tFilterRow_28", System.currentTimeMillis());

				/**
				 * [tFilterRow_28 end ] stop
				 */

				/**
				 * [tMap_6 end ] start
				 */

				currentComponent = "tMap_6";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row22 != null) {
					tHash_Lookup_row22.endGet();
				}
				globalMap.remove("tHash_Lookup_row22");

// ###############################      
				log.debug("tMap_6 - Written records count in the table 'OUT': " + count_OUT_tMap_6 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row20", 2, 0,
						"tFilterRow_28", "tFilterRow_28", "tFilterRow", "tMap_6", "tMap_6", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_6 - " + ("Done."));

				ok_Hash.put("tMap_6", true);
				end_Hash.put("tMap_6", System.currentTimeMillis());

				/**
				 * [tMap_6 end ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "OUT", 2, 0, "tMap_6",
						"tMap_6", "tMap", "tAggregateRow_3_AGGOUT", "tAggregateRow_3_AGGOUT", "tAggregateOut",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_3_AGGOUT - " + ("Done."));

				ok_Hash.put("tAggregateRow_3_AGGOUT", true);
				end_Hash.put("tAggregateRow_3_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_3_AGGOUT end ] stop
				 */

				/**
				 * [tAdvancedHash_row17 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row17", false);
				start_Hash.put("tAdvancedHash_row17", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row17";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row17");

				int tos_count_tAdvancedHash_row17 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row17", "tAdvancedHash_row17", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row17
				// source node:tAggregateRow_3_AGGIN - inputs:(OnRowsEnd) outputs:(row17,row17)
				// | target node:tAdvancedHash_row17 - inputs:(row17) outputs:()
				// linked node: tMap_1 - inputs:(row13,row15,row17) outputs:(Estoque)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row17 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row17Struct> tHash_Lookup_row17 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row17Struct>getLookup(matchingModeEnum_row17);

				globalMap.put("tHash_Lookup_row17", tHash_Lookup_row17);

				/**
				 * [tAdvancedHash_row17 begin ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGIN begin ] start
				 */

				ok_Hash.put("tAggregateRow_3_AGGIN", false);
				start_Hash.put("tAggregateRow_3_AGGIN", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGIN";

				int tos_count_tAggregateRow_3_AGGIN = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_3_AGGIN - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_3_AGGIN {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_3_AGGIN = new StringBuilder();
							log4jParamters_tAggregateRow_3_AGGIN.append("Parameters:");
							log4jParamters_tAggregateRow_3_AGGIN.append("ORIGIN" + " = " + "tAggregateRow_3");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN
									.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN=" + ("DB_ESTAL_PRODUTO")
											+ ", INPUT_COLUMN=" + ("DB_ESTAL_PRODUTO") + "}, {OUTPUT_COLUMN="
											+ ("DB_ESTAL_EMPRESA") + ", INPUT_COLUMN=" + ("DB_ESTAL_EMPRESA") + "}]");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("DB_ESTAL_QTDE_EST") + ", INPUT_COLUMN=" + ("DB_ESTAL_QTDE_EST")
									+ ", IGNORE_NULL=" + ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_3_AGGIN.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_3_AGGIN.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_3_AGGIN - " + (log4jParamters_tAggregateRow_3_AGGIN));
						}
					}
					new BytesLimit65535_tAggregateRow_3_AGGIN().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_3_AGGIN", "tAggregateRow_3_AGGIN", "tAggregateIn");
					talendJobLogProcess(globalMap);
				}

				java.util.Collection<AggOperationStruct_tAggregateRow_3> values_tAggregateRow_3 = hash_tAggregateRow_3
						.values();

				globalMap.put("tAggregateRow_3_NB_LINE", values_tAggregateRow_3.size());

				if (log.isInfoEnabled())
					log.info("tAggregateRow_3_AGGIN - " + ("Retrieving the aggregation results."));
				for (AggOperationStruct_tAggregateRow_3 aggregated_row_tAggregateRow_3 : values_tAggregateRow_3) { // G_AggR_600

					/**
					 * [tAggregateRow_3_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					row17.DB_ESTAL_PRODUTO = aggregated_row_tAggregateRow_3.DB_ESTAL_PRODUTO;

					row17.DB_ESTAL_EMPRESA = aggregated_row_tAggregateRow_3.DB_ESTAL_EMPRESA;

					if (aggregated_row_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum != null) {
						row17.DB_ESTAL_QTDE_EST = aggregated_row_tAggregateRow_3.DB_ESTAL_QTDE_EST_sum.doubleValue();

					} else {

						row17.DB_ESTAL_QTDE_EST = null;

					}

					if (log.isDebugEnabled())
						log.debug("tAggregateRow_3_AGGIN - "
								+ ("Operation function: 'sum' on the column 'DB_ESTAL_QTDE_EST'."));

					tos_count_tAggregateRow_3_AGGIN++;

					/**
					 * [tAggregateRow_3_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					/**
					 * [tAggregateRow_3_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row17 main ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row17", "tAggregateRow_3_AGGIN", "tAggregateRow_3_AGGIN", "tAggregateIn",
							"tAdvancedHash_row17", "tAdvancedHash_row17", "tAdvancedHash"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row17 - " + (row17 == null ? "" : row17.toLogString()));
					}

					row17Struct row17_HashRow = new row17Struct();

					row17_HashRow.DB_ESTAL_PRODUTO = row17.DB_ESTAL_PRODUTO;

					row17_HashRow.DB_ESTAL_EMPRESA = row17.DB_ESTAL_EMPRESA;

					row17_HashRow.DB_ESTAL_QTDE_EST = row17.DB_ESTAL_QTDE_EST;

					tHash_Lookup_row17.put(row17_HashRow);

					tos_count_tAdvancedHash_row17++;

					/**
					 * [tAdvancedHash_row17 main ] stop
					 */

					/**
					 * [tAdvancedHash_row17 process_data_begin ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					/**
					 * [tAdvancedHash_row17 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row17 process_data_end ] start
					 */

					currentComponent = "tAdvancedHash_row17";

					/**
					 * [tAdvancedHash_row17 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

					/**
					 * [tAggregateRow_3_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_3_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_3";

					currentComponent = "tAggregateRow_3_AGGIN";

				} // G_AggR_600

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_3_AGGIN - " + ("Done."));

				ok_Hash.put("tAggregateRow_3_AGGIN", true);
				end_Hash.put("tAggregateRow_3_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_3_AGGIN end ] stop
				 */

				/**
				 * [tAdvancedHash_row17 end ] start
				 */

				currentComponent = "tAdvancedHash_row17";

				tHash_Lookup_row17.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row17", 2, 0,
						"tAggregateRow_3_AGGIN", "tAggregateRow_3_AGGIN", "tAggregateIn", "tAdvancedHash_row17",
						"tAdvancedHash_row17", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row17", true);
				end_Hash.put("tAdvancedHash_row17", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row17 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_3_AGGIN"
			globalMap.remove("tAggregateRow_3");

			// free memory for "tMap_6"
			globalMap.remove("tHash_Lookup_row22");

			try {

				/**
				 * [tDBInput_9 finally ] start
				 */

				currentComponent = "tDBInput_9";

				cLabel = "Input_ZC5";

				/**
				 * [tDBInput_9 finally ] stop
				 */

				/**
				 * [tFilterRow_27 finally ] start
				 */

				currentComponent = "tFilterRow_27";

				/**
				 * [tFilterRow_27 finally ] stop
				 */

				/**
				 * [tFilterRow_28 finally ] start
				 */

				currentComponent = "tFilterRow_28";

				/**
				 * [tFilterRow_28 finally ] stop
				 */

				/**
				 * [tMap_6 finally ] start
				 */

				currentComponent = "tMap_6";

				/**
				 * [tMap_6 finally ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGOUT";

				/**
				 * [tAggregateRow_3_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_3_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_3";

				currentComponent = "tAggregateRow_3_AGGIN";

				/**
				 * [tAggregateRow_3_AGGIN finally ] stop
				 */

				/**
				 * [tAdvancedHash_row17 finally ] start
				 */

				currentComponent = "tAdvancedHash_row17";

				/**
				 * [tAdvancedHash_row17 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_9_SUBPROCESS_STATE", 1);
	}

	public static class row22Struct implements routines.system.IPersistableComparableLookupRow<row22Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String DB_ESTAL_EMPRESA;

		public String getDB_ESTAL_EMPRESA() {
			return this.DB_ESTAL_EMPRESA;
		}

		public Boolean DB_ESTAL_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_ESTAL_EMPRESALength() {
			return 2;
		}

		public Integer DB_ESTAL_EMPRESAPrecision() {
			return 0;
		}

		public String DB_ESTAL_EMPRESADefault() {

			return null;

		}

		public String DB_ESTAL_EMPRESAComment() {

			return "";

		}

		public String DB_ESTAL_EMPRESAPattern() {

			return "";

		}

		public String DB_ESTAL_EMPRESAOriginalDbColumnName() {

			return "DB_ESTAL_EMPRESA";

		}

		public String ZC6_PEDMER;

		public String getZC6_PEDMER() {
			return this.ZC6_PEDMER;
		}

		public Boolean ZC6_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC6_PEDMERIsKey() {
			return false;
		}

		public Integer ZC6_PEDMERLength() {
			return 20;
		}

		public Integer ZC6_PEDMERPrecision() {
			return 0;
		}

		public String ZC6_PEDMERDefault() {

			return null;

		}

		public String ZC6_PEDMERComment() {

			return "";

		}

		public String ZC6_PEDMERPattern() {

			return "";

		}

		public String ZC6_PEDMEROriginalDbColumnName() {

			return "ZC6_PEDMER";

		}

		public String DB_ESTAL_PRODUTO;

		public String getDB_ESTAL_PRODUTO() {
			return this.DB_ESTAL_PRODUTO;
		}

		public Boolean DB_ESTAL_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_ESTAL_PRODUTOLength() {
			return 15;
		}

		public Integer DB_ESTAL_PRODUTOPrecision() {
			return 0;
		}

		public String DB_ESTAL_PRODUTODefault() {

			return null;

		}

		public String DB_ESTAL_PRODUTOComment() {

			return "";

		}

		public String DB_ESTAL_PRODUTOPattern() {

			return "";

		}

		public String DB_ESTAL_PRODUTOOriginalDbColumnName() {

			return "DB_ESTAL_PRODUTO";

		}

		public Double DB_ESTAL_QTDE_EST;

		public Double getDB_ESTAL_QTDE_EST() {
			return this.DB_ESTAL_QTDE_EST;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsKey() {
			return false;
		}

		public Integer DB_ESTAL_QTDE_ESTLength() {
			return 15;
		}

		public Integer DB_ESTAL_QTDE_ESTPrecision() {
			return 0;
		}

		public String DB_ESTAL_QTDE_ESTDefault() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTComment() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTPattern() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTOriginalDbColumnName() {

			return "DB_ESTAL_QTDE_EST";

		}

		public String ZC6_LOCAL;

		public String getZC6_LOCAL() {
			return this.ZC6_LOCAL;
		}

		public Boolean ZC6_LOCALIsNullable() {
			return true;
		}

		public Boolean ZC6_LOCALIsKey() {
			return false;
		}

		public Integer ZC6_LOCALLength() {
			return 2;
		}

		public Integer ZC6_LOCALPrecision() {
			return 0;
		}

		public String ZC6_LOCALDefault() {

			return null;

		}

		public String ZC6_LOCALComment() {

			return "";

		}

		public String ZC6_LOCALPattern() {

			return "";

		}

		public String ZC6_LOCALOriginalDbColumnName() {

			return "ZC6_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ZC6_PEDMER == null) ? 0 : this.ZC6_PEDMER.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row22Struct other = (row22Struct) obj;

			if (this.ZC6_PEDMER == null) {
				if (other.ZC6_PEDMER != null)
					return false;

			} else if (!this.ZC6_PEDMER.equals(other.ZC6_PEDMER))

				return false;

			return true;
		}

		public void copyDataTo(row22Struct other) {

			other.DB_ESTAL_EMPRESA = this.DB_ESTAL_EMPRESA;
			other.ZC6_PEDMER = this.ZC6_PEDMER;
			other.DB_ESTAL_PRODUTO = this.DB_ESTAL_PRODUTO;
			other.DB_ESTAL_QTDE_EST = this.DB_ESTAL_QTDE_EST;
			other.ZC6_LOCAL = this.ZC6_LOCAL;
			other.D_E_L_E_T_ = this.D_E_L_E_T_;

		}

		public void copyKeysDataTo(row22Struct other) {

			other.ZC6_PEDMER = this.ZC6_PEDMER;

		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC6_PEDMER = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.ZC6_PEDMER = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ZC6_PEDMER, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ZC6_PEDMER, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DB_ESTAL_EMPRESA = readString(dis, ois);

				this.DB_ESTAL_PRODUTO = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.DB_ESTAL_QTDE_EST = null;
				} else {
					this.DB_ESTAL_QTDE_EST = dis.readDouble();
				}

				this.ZC6_LOCAL = readString(dis, ois);

				this.D_E_L_E_T_ = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DB_ESTAL_EMPRESA = readString(dis, objectIn);

				this.DB_ESTAL_PRODUTO = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DB_ESTAL_QTDE_EST = null;
				} else {
					this.DB_ESTAL_QTDE_EST = objectIn.readDouble();
				}

				this.ZC6_LOCAL = readString(dis, objectIn);

				this.D_E_L_E_T_ = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.DB_ESTAL_EMPRESA, dos, oos);

				writeString(this.DB_ESTAL_PRODUTO, dos, oos);

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

				writeString(this.ZC6_LOCAL, dos, oos);

				writeString(this.D_E_L_E_T_, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.DB_ESTAL_EMPRESA, dos, objectOut);

				writeString(this.DB_ESTAL_PRODUTO, dos, objectOut);

				if (this.DB_ESTAL_QTDE_EST == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

				writeString(this.ZC6_LOCAL, dos, objectOut);

				writeString(this.D_E_L_E_T_, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_ESTAL_EMPRESA=" + DB_ESTAL_EMPRESA);
			sb.append(",ZC6_PEDMER=" + ZC6_PEDMER);
			sb.append(",DB_ESTAL_PRODUTO=" + DB_ESTAL_PRODUTO);
			sb.append(",DB_ESTAL_QTDE_EST=" + String.valueOf(DB_ESTAL_QTDE_EST));
			sb.append(",ZC6_LOCAL=" + ZC6_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_ESTAL_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_EMPRESA);
			}

			sb.append("|");

			if (ZC6_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC6_PEDMER);
			}

			sb.append("|");

			if (DB_ESTAL_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_PRODUTO);
			}

			sb.append("|");

			if (DB_ESTAL_QTDE_EST == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_QTDE_EST);
			}

			sb.append("|");

			if (ZC6_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC6_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row22Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ZC6_PEDMER, other.ZC6_PEDMER);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row21Struct implements routines.system.IPersistableRow<row21Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public String DB_ESTAL_EMPRESA;

		public String getDB_ESTAL_EMPRESA() {
			return this.DB_ESTAL_EMPRESA;
		}

		public Boolean DB_ESTAL_EMPRESAIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_EMPRESAIsKey() {
			return false;
		}

		public Integer DB_ESTAL_EMPRESALength() {
			return 2;
		}

		public Integer DB_ESTAL_EMPRESAPrecision() {
			return 0;
		}

		public String DB_ESTAL_EMPRESADefault() {

			return null;

		}

		public String DB_ESTAL_EMPRESAComment() {

			return "";

		}

		public String DB_ESTAL_EMPRESAPattern() {

			return "";

		}

		public String DB_ESTAL_EMPRESAOriginalDbColumnName() {

			return "DB_ESTAL_EMPRESA";

		}

		public String ZC6_PEDMER;

		public String getZC6_PEDMER() {
			return this.ZC6_PEDMER;
		}

		public Boolean ZC6_PEDMERIsNullable() {
			return true;
		}

		public Boolean ZC6_PEDMERIsKey() {
			return false;
		}

		public Integer ZC6_PEDMERLength() {
			return 20;
		}

		public Integer ZC6_PEDMERPrecision() {
			return 0;
		}

		public String ZC6_PEDMERDefault() {

			return null;

		}

		public String ZC6_PEDMERComment() {

			return "";

		}

		public String ZC6_PEDMERPattern() {

			return "";

		}

		public String ZC6_PEDMEROriginalDbColumnName() {

			return "ZC6_PEDMER";

		}

		public String DB_ESTAL_PRODUTO;

		public String getDB_ESTAL_PRODUTO() {
			return this.DB_ESTAL_PRODUTO;
		}

		public Boolean DB_ESTAL_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_ESTAL_PRODUTOLength() {
			return 15;
		}

		public Integer DB_ESTAL_PRODUTOPrecision() {
			return 0;
		}

		public String DB_ESTAL_PRODUTODefault() {

			return null;

		}

		public String DB_ESTAL_PRODUTOComment() {

			return "";

		}

		public String DB_ESTAL_PRODUTOPattern() {

			return "";

		}

		public String DB_ESTAL_PRODUTOOriginalDbColumnName() {

			return "DB_ESTAL_PRODUTO";

		}

		public Double DB_ESTAL_QTDE_EST;

		public Double getDB_ESTAL_QTDE_EST() {
			return this.DB_ESTAL_QTDE_EST;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsNullable() {
			return true;
		}

		public Boolean DB_ESTAL_QTDE_ESTIsKey() {
			return false;
		}

		public Integer DB_ESTAL_QTDE_ESTLength() {
			return 15;
		}

		public Integer DB_ESTAL_QTDE_ESTPrecision() {
			return 0;
		}

		public String DB_ESTAL_QTDE_ESTDefault() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTComment() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTPattern() {

			return "";

		}

		public String DB_ESTAL_QTDE_ESTOriginalDbColumnName() {

			return "DB_ESTAL_QTDE_EST";

		}

		public String ZC6_LOCAL;

		public String getZC6_LOCAL() {
			return this.ZC6_LOCAL;
		}

		public Boolean ZC6_LOCALIsNullable() {
			return true;
		}

		public Boolean ZC6_LOCALIsKey() {
			return false;
		}

		public Integer ZC6_LOCALLength() {
			return 2;
		}

		public Integer ZC6_LOCALPrecision() {
			return 0;
		}

		public String ZC6_LOCALDefault() {

			return null;

		}

		public String ZC6_LOCALComment() {

			return "";

		}

		public String ZC6_LOCALPattern() {

			return "";

		}

		public String ZC6_LOCALOriginalDbColumnName() {

			return "ZC6_LOCAL";

		}

		public String D_E_L_E_T_;

		public String getD_E_L_E_T_() {
			return this.D_E_L_E_T_;
		}

		public Boolean D_E_L_E_T_IsNullable() {
			return true;
		}

		public Boolean D_E_L_E_T_IsKey() {
			return false;
		}

		public Integer D_E_L_E_T_Length() {
			return 1;
		}

		public Integer D_E_L_E_T_Precision() {
			return 0;
		}

		public String D_E_L_E_T_Default() {

			return null;

		}

		public String D_E_L_E_T_Comment() {

			return "";

		}

		public String D_E_L_E_T_Pattern() {

			return "";

		}

		public String D_E_L_E_T_OriginalDbColumnName() {

			return "D_E_L_E_T_";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_EMPRESA = readString(dis);

					this.ZC6_PEDMER = readString(dis);

					this.DB_ESTAL_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

					this.ZC6_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_ESTAL_EMPRESA = readString(dis);

					this.ZC6_PEDMER = readString(dis);

					this.DB_ESTAL_PRODUTO = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DB_ESTAL_QTDE_EST = null;
					} else {
						this.DB_ESTAL_QTDE_EST = dis.readDouble();
					}

					this.ZC6_LOCAL = readString(dis);

					this.D_E_L_E_T_ = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// String

				writeString(this.ZC6_PEDMER, dos);

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

				// String

				writeString(this.ZC6_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.DB_ESTAL_EMPRESA, dos);

				// String

				writeString(this.ZC6_PEDMER, dos);

				// String

				writeString(this.DB_ESTAL_PRODUTO, dos);

				// Double

				if (this.DB_ESTAL_QTDE_EST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DB_ESTAL_QTDE_EST);
				}

				// String

				writeString(this.ZC6_LOCAL, dos);

				// String

				writeString(this.D_E_L_E_T_, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_ESTAL_EMPRESA=" + DB_ESTAL_EMPRESA);
			sb.append(",ZC6_PEDMER=" + ZC6_PEDMER);
			sb.append(",DB_ESTAL_PRODUTO=" + DB_ESTAL_PRODUTO);
			sb.append(",DB_ESTAL_QTDE_EST=" + String.valueOf(DB_ESTAL_QTDE_EST));
			sb.append(",ZC6_LOCAL=" + ZC6_LOCAL);
			sb.append(",D_E_L_E_T_=" + D_E_L_E_T_);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_ESTAL_EMPRESA == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_EMPRESA);
			}

			sb.append("|");

			if (ZC6_PEDMER == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC6_PEDMER);
			}

			sb.append("|");

			if (DB_ESTAL_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_PRODUTO);
			}

			sb.append("|");

			if (DB_ESTAL_QTDE_EST == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_ESTAL_QTDE_EST);
			}

			sb.append("|");

			if (ZC6_LOCAL == null) {
				sb.append("<null>");
			} else {
				sb.append(ZC6_LOCAL);
			}

			sb.append("|");

			if (D_E_L_E_T_ == null) {
				sb.append("<null>");
			} else {
				sb.append(D_E_L_E_T_);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row21Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_10_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_10");
		org.slf4j.MDC.put("_subJobPid", "ZdZxLN_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row21Struct row21 = new row21Struct();
				row22Struct row22 = new row22Struct();

				/**
				 * [tAdvancedHash_row22 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row22", false);
				start_Hash.put("tAdvancedHash_row22", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row22";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row22");

				int tos_count_tAdvancedHash_row22 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row22", "tAdvancedHash_row22", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row22
				// source node:tFilterRow_29 - inputs:(row21) outputs:(row22,row22) | target
				// node:tAdvancedHash_row22 - inputs:(row22) outputs:()
				// linked node: tMap_6 - inputs:(row20,row22) outputs:(OUT)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row22 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row22Struct> tHash_Lookup_row22 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row22Struct>getLookup(matchingModeEnum_row22);

				globalMap.put("tHash_Lookup_row22", tHash_Lookup_row22);

				/**
				 * [tAdvancedHash_row22 begin ] stop
				 */

				/**
				 * [tFilterRow_29 begin ] start
				 */

				ok_Hash.put("tFilterRow_29", false);
				start_Hash.put("tFilterRow_29", System.currentTimeMillis());

				currentComponent = "tFilterRow_29";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row21");

				int tos_count_tFilterRow_29 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_29 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_29 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_29 = new StringBuilder();
							log4jParamters_tFilterRow_29.append("Parameters:");
							log4jParamters_tFilterRow_29.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_29.append(" | ");
							log4jParamters_tFilterRow_29.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("!=")
									+ ", RVALUE=" + ("\"*\"") + ", INPUT_COLUMN=" + ("D_E_L_E_T_") + ", FUNCTION="
									+ ("") + "}, {OPERATOR=" + ("==") + ", RVALUE=" + ("\"02\"") + ", INPUT_COLUMN="
									+ ("DB_ESTAL_EMPRESA") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_29.append(" | ");
							log4jParamters_tFilterRow_29.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_29.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_29 - " + (log4jParamters_tFilterRow_29));
						}
					}
					new BytesLimit65535_tFilterRow_29().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_29", "tFilterRow_29", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_29 = 0;
				int nb_line_ok_tFilterRow_29 = 0;
				int nb_line_reject_tFilterRow_29 = 0;

				class Operator_tFilterRow_29 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_29(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_29 begin ] stop
				 */

				/**
				 * [tDBInput_10 begin ] start
				 */

				ok_Hash.put("tDBInput_10", false);
				start_Hash.put("tDBInput_10", System.currentTimeMillis());

				currentComponent = "tDBInput_10";

				cLabel = "Input_ZC6";

				int tos_count_tDBInput_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_10 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_10 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_10 = new StringBuilder();
							log4jParamters_tDBInput_10.append("Parameters:");
							log4jParamters_tDBInput_10.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("HOST" + " = " + "\"192.168.4.66\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("DBNAME" + " = " + "\"TOTVS\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:jzvCAMnFBf66zyKyJAJQzHBTNrUqIZTyUd+6tKZJvBhzIm5yZjUK7iVr")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("TABLE" + " = " + "\"ZC6010\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("QUERY" + " = "
									+ "\"                SELECT  ZC6_FILIAL,  						ZC6_PEDMER,  	 					ZC6_PRODUT,                         	ZC6_QTDVEN,  						ZC6_LOCAL,  						 D_E_L_E_T_                  FROM  ZC6010\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_ESTAL_EMPRESA") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ZC6_PEDMER") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_ESTAL_PRODUTO") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DB_ESTAL_QTDE_EST") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("ZC6_LOCAL") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("D_E_L_E_T_")
									+ "}]");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_10.append(" | ");
							log4jParamters_tDBInput_10.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_10.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_10 - " + (log4jParamters_tDBInput_10));
						}
					}
					new BytesLimit65535_tDBInput_10().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_10", "Input_ZC6", "tMSSqlInput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_10 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_10 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_10 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_10, talendToDBArray_tDBInput_10);
				int nb_line_tDBInput_10 = 0;
				java.sql.Connection conn_tDBInput_10 = null;
				String driverClass_tDBInput_10 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_10 = java.lang.Class.forName(driverClass_tDBInput_10);
				String dbUser_tDBInput_10 = "analytics";

				final String decryptedPassword_tDBInput_10 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:hqNjFZQepFWRQ3p6/6LIwrmKGZnwu85zniJLr994FSwbUg2SUyjHiu9j");

				String dbPwd_tDBInput_10 = decryptedPassword_tDBInput_10;

				String port_tDBInput_10 = "1433";
				String dbname_tDBInput_10 = "TOTVS";
				String url_tDBInput_10 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_10)) {
					url_tDBInput_10 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_10)) {
					url_tDBInput_10 += ";databaseName=" + "TOTVS";
				}
				url_tDBInput_10 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_10 = "";

				log.debug("tDBInput_10 - Driver ClassName: " + driverClass_tDBInput_10 + ".");

				log.debug("tDBInput_10 - Connection attempt to '" + url_tDBInput_10 + "' with the username '"
						+ dbUser_tDBInput_10 + "'.");

				conn_tDBInput_10 = java.sql.DriverManager.getConnection(url_tDBInput_10, dbUser_tDBInput_10,
						dbPwd_tDBInput_10);
				log.debug("tDBInput_10 - Connection to '" + url_tDBInput_10 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_10 = conn_tDBInput_10.createStatement();

				String dbquery_tDBInput_10 = "                SELECT  ZC6_FILIAL,\n						ZC6_PEDMER,\n	 					ZC6_PRODUT,\n                       	ZC6_QTDVEN,\n						"
						+ "ZC6_LOCAL,\n						 D_E_L_E_T_\n                FROM  ZC6010";

				log.debug("tDBInput_10 - Executing the query: '" + dbquery_tDBInput_10 + "'.");

				globalMap.put("tDBInput_10_QUERY", dbquery_tDBInput_10);

				java.sql.ResultSet rs_tDBInput_10 = null;

				try {
					rs_tDBInput_10 = stmt_tDBInput_10.executeQuery(dbquery_tDBInput_10);
					java.sql.ResultSetMetaData rsmd_tDBInput_10 = rs_tDBInput_10.getMetaData();
					int colQtyInRs_tDBInput_10 = rsmd_tDBInput_10.getColumnCount();

					String tmpContent_tDBInput_10 = null;

					log.debug("tDBInput_10 - Retrieving records from the database.");

					while (rs_tDBInput_10.next()) {
						nb_line_tDBInput_10++;

						if (colQtyInRs_tDBInput_10 < 1) {
							row21.DB_ESTAL_EMPRESA = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(1);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.DB_ESTAL_EMPRESA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row21.DB_ESTAL_EMPRESA = tmpContent_tDBInput_10;
								}
							} else {
								row21.DB_ESTAL_EMPRESA = null;
							}
						}
						if (colQtyInRs_tDBInput_10 < 2) {
							row21.ZC6_PEDMER = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(2);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.ZC6_PEDMER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row21.ZC6_PEDMER = tmpContent_tDBInput_10;
								}
							} else {
								row21.ZC6_PEDMER = null;
							}
						}
						if (colQtyInRs_tDBInput_10 < 3) {
							row21.DB_ESTAL_PRODUTO = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(3);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.DB_ESTAL_PRODUTO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row21.DB_ESTAL_PRODUTO = tmpContent_tDBInput_10;
								}
							} else {
								row21.DB_ESTAL_PRODUTO = null;
							}
						}
						if (colQtyInRs_tDBInput_10 < 4) {
							row21.DB_ESTAL_QTDE_EST = null;
						} else {

							row21.DB_ESTAL_QTDE_EST = rs_tDBInput_10.getDouble(4);
							if (rs_tDBInput_10.wasNull()) {
								row21.DB_ESTAL_QTDE_EST = null;
							}
						}
						if (colQtyInRs_tDBInput_10 < 5) {
							row21.ZC6_LOCAL = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(5);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.ZC6_LOCAL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row21.ZC6_LOCAL = tmpContent_tDBInput_10;
								}
							} else {
								row21.ZC6_LOCAL = null;
							}
						}
						if (colQtyInRs_tDBInput_10 < 6) {
							row21.D_E_L_E_T_ = null;
						} else {

							tmpContent_tDBInput_10 = rs_tDBInput_10.getString(6);
							if (tmpContent_tDBInput_10 != null) {
								if (talendToDBList_tDBInput_10.contains(
										rsmd_tDBInput_10.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row21.D_E_L_E_T_ = FormatterUtils.formatUnwithE(tmpContent_tDBInput_10);
								} else {
									row21.D_E_L_E_T_ = tmpContent_tDBInput_10;
								}
							} else {
								row21.D_E_L_E_T_ = null;
							}
						}

						log.debug("tDBInput_10 - Retrieving the record " + nb_line_tDBInput_10 + ".");

						/**
						 * [tDBInput_10 begin ] stop
						 */

						/**
						 * [tDBInput_10 main ] start
						 */

						currentComponent = "tDBInput_10";

						cLabel = "Input_ZC6";

						tos_count_tDBInput_10++;

						/**
						 * [tDBInput_10 main ] stop
						 */

						/**
						 * [tDBInput_10 process_data_begin ] start
						 */

						currentComponent = "tDBInput_10";

						cLabel = "Input_ZC6";

						/**
						 * [tDBInput_10 process_data_begin ] stop
						 */

						/**
						 * [tFilterRow_29 main ] start
						 */

						currentComponent = "tFilterRow_29";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row21", "tDBInput_10", "Input_ZC6", "tMSSqlInput", "tFilterRow_29", "tFilterRow_29",
								"tFilterRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row21 - " + (row21 == null ? "" : row21.toLogString()));
						}

						row22 = null;
						row22 = null;
						Operator_tFilterRow_29 ope_tFilterRow_29 = new Operator_tFilterRow_29("&&");
						ope_tFilterRow_29.matches(
								(row21.D_E_L_E_T_ == null ? false : row21.D_E_L_E_T_.compareTo("*") != 0),
								"D_E_L_E_T_.compareTo(\"*\") != 0 failed");
						ope_tFilterRow_29.matches(
								(row21.DB_ESTAL_EMPRESA == null ? false : row21.DB_ESTAL_EMPRESA.compareTo("02") == 0),
								"DB_ESTAL_EMPRESA.compareTo(\"02\") == 0 failed");

						if (ope_tFilterRow_29.getMatchFlag()) {
							if (row22 == null) {
								row22 = new row22Struct();
							}
							row22.DB_ESTAL_EMPRESA = row21.DB_ESTAL_EMPRESA;
							row22.ZC6_PEDMER = row21.ZC6_PEDMER;
							row22.DB_ESTAL_PRODUTO = row21.DB_ESTAL_PRODUTO;
							row22.DB_ESTAL_QTDE_EST = row21.DB_ESTAL_QTDE_EST;
							row22.ZC6_LOCAL = row21.ZC6_LOCAL;
							row22.D_E_L_E_T_ = row21.D_E_L_E_T_;
							log.debug("tFilterRow_29 - Process the record " + (nb_line_tFilterRow_29 + 1) + ".");

							if (row22 == null) {
								row22 = new row22Struct();
							}
							row22.DB_ESTAL_EMPRESA = row21.DB_ESTAL_EMPRESA;
							row22.ZC6_PEDMER = row21.ZC6_PEDMER;
							row22.DB_ESTAL_PRODUTO = row21.DB_ESTAL_PRODUTO;
							row22.DB_ESTAL_QTDE_EST = row21.DB_ESTAL_QTDE_EST;
							row22.ZC6_LOCAL = row21.ZC6_LOCAL;
							row22.D_E_L_E_T_ = row21.D_E_L_E_T_;
							log.debug("tFilterRow_29 - Process the record " + (nb_line_tFilterRow_29 + 1) + ".");

							nb_line_ok_tFilterRow_29++;
						} else {
							nb_line_reject_tFilterRow_29++;
						}

						nb_line_tFilterRow_29++;

						tos_count_tFilterRow_29++;

						/**
						 * [tFilterRow_29 main ] stop
						 */

						/**
						 * [tFilterRow_29 process_data_begin ] start
						 */

						currentComponent = "tFilterRow_29";

						/**
						 * [tFilterRow_29 process_data_begin ] stop
						 */
// Start of branch "row22"
						if (row22 != null) {

							/**
							 * [tAdvancedHash_row22 main ] start
							 */

							currentComponent = "tAdvancedHash_row22";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row22", "tFilterRow_29", "tFilterRow_29", "tFilterRow", "tAdvancedHash_row22",
									"tAdvancedHash_row22", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row22 - " + (row22 == null ? "" : row22.toLogString()));
							}

							row22Struct row22_HashRow = new row22Struct();

							row22_HashRow.DB_ESTAL_EMPRESA = row22.DB_ESTAL_EMPRESA;

							row22_HashRow.ZC6_PEDMER = row22.ZC6_PEDMER;

							row22_HashRow.DB_ESTAL_PRODUTO = row22.DB_ESTAL_PRODUTO;

							row22_HashRow.DB_ESTAL_QTDE_EST = row22.DB_ESTAL_QTDE_EST;

							row22_HashRow.ZC6_LOCAL = row22.ZC6_LOCAL;

							row22_HashRow.D_E_L_E_T_ = row22.D_E_L_E_T_;

							tHash_Lookup_row22.put(row22_HashRow);

							tos_count_tAdvancedHash_row22++;

							/**
							 * [tAdvancedHash_row22 main ] stop
							 */

							/**
							 * [tAdvancedHash_row22 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row22";

							/**
							 * [tAdvancedHash_row22 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row22 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row22";

							/**
							 * [tAdvancedHash_row22 process_data_end ] stop
							 */

						} // End of branch "row22"

						/**
						 * [tFilterRow_29 process_data_end ] start
						 */

						currentComponent = "tFilterRow_29";

						/**
						 * [tFilterRow_29 process_data_end ] stop
						 */

						/**
						 * [tDBInput_10 process_data_end ] start
						 */

						currentComponent = "tDBInput_10";

						cLabel = "Input_ZC6";

						/**
						 * [tDBInput_10 process_data_end ] stop
						 */

						/**
						 * [tDBInput_10 end ] start
						 */

						currentComponent = "tDBInput_10";

						cLabel = "Input_ZC6";

					}
				} finally {
					if (rs_tDBInput_10 != null) {
						rs_tDBInput_10.close();
					}
					if (stmt_tDBInput_10 != null) {
						stmt_tDBInput_10.close();
					}
					if (conn_tDBInput_10 != null && !conn_tDBInput_10.isClosed()) {

						log.debug("tDBInput_10 - Closing the connection to the database.");

						conn_tDBInput_10.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_10 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_10_NB_LINE", nb_line_tDBInput_10);
				log.debug("tDBInput_10 - Retrieved records count: " + nb_line_tDBInput_10 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_10 - " + ("Done."));

				ok_Hash.put("tDBInput_10", true);
				end_Hash.put("tDBInput_10", System.currentTimeMillis());

				/**
				 * [tDBInput_10 end ] stop
				 */

				/**
				 * [tFilterRow_29 end ] start
				 */

				currentComponent = "tFilterRow_29";

				globalMap.put("tFilterRow_29_NB_LINE", nb_line_tFilterRow_29);
				globalMap.put("tFilterRow_29_NB_LINE_OK", nb_line_ok_tFilterRow_29);
				globalMap.put("tFilterRow_29_NB_LINE_REJECT", nb_line_reject_tFilterRow_29);

				log.info("tFilterRow_29 - Processed records count:" + nb_line_tFilterRow_29 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_29 + ". Rejected records count:" + nb_line_reject_tFilterRow_29 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row21", 2, 0,
						"tDBInput_10", "Input_ZC6", "tMSSqlInput", "tFilterRow_29", "tFilterRow_29", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_29 - " + ("Done."));

				ok_Hash.put("tFilterRow_29", true);
				end_Hash.put("tFilterRow_29", System.currentTimeMillis());

				/**
				 * [tFilterRow_29 end ] stop
				 */

				/**
				 * [tAdvancedHash_row22 end ] start
				 */

				currentComponent = "tAdvancedHash_row22";

				tHash_Lookup_row22.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row22", 2, 0,
						"tFilterRow_29", "tFilterRow_29", "tFilterRow", "tAdvancedHash_row22", "tAdvancedHash_row22",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row22", true);
				end_Hash.put("tAdvancedHash_row22", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row22 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_10 finally ] start
				 */

				currentComponent = "tDBInput_10";

				cLabel = "Input_ZC6";

				/**
				 * [tDBInput_10 finally ] stop
				 */

				/**
				 * [tFilterRow_29 finally ] start
				 */

				currentComponent = "tFilterRow_29";

				/**
				 * [tFilterRow_29 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row22 finally ] start
				 */

				currentComponent = "tAdvancedHash_row22";

				/**
				 * [tAdvancedHash_row22 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_10_SUBPROCESS_STATE", 1);
	}

	public static class row14Struct implements routines.system.IPersistableComparableLookupRow<row14Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String D14_PRODUT;

		public String getD14_PRODUT() {
			return this.D14_PRODUT;
		}

		public Boolean D14_PRODUTIsNullable() {
			return true;
		}

		public Boolean D14_PRODUTIsKey() {
			return true;
		}

		public Integer D14_PRODUTLength() {
			return 15;
		}

		public Integer D14_PRODUTPrecision() {
			return 0;
		}

		public String D14_PRODUTDefault() {

			return null;

		}

		public String D14_PRODUTComment() {

			return "";

		}

		public String D14_PRODUTPattern() {

			return "";

		}

		public String D14_PRODUTOriginalDbColumnName() {

			return "D14_PRODUT";

		}

		public Double ESTOQUE;

		public Double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.D14_PRODUT == null) ? 0 : this.D14_PRODUT.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row14Struct other = (row14Struct) obj;

			if (this.D14_PRODUT == null) {
				if (other.D14_PRODUT != null)
					return false;

			} else if (!this.D14_PRODUT.equals(other.D14_PRODUT))

				return false;

			return true;
		}

		public void copyDataTo(row14Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;
			other.ESTOQUE = this.ESTOQUE;

		}

		public void copyKeysDataTo(row14Struct other) {

			other.D14_PRODUT = this.D14_PRODUT;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.D14_PRODUT = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.D14_PRODUT, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				length = dis.readByte();
				if (length == -1) {
					this.ESTOQUE = null;
				} else {
					this.ESTOQUE = dis.readDouble();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				length = objectIn.readByte();
				if (length == -1) {
					this.ESTOQUE = null;
				} else {
					this.ESTOQUE = objectIn.readDouble();
				}

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				if (this.ESTOQUE == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.ESTOQUE == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ESTOQUE);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("D14_PRODUT=" + D14_PRODUT);
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (D14_PRODUT == null) {
				sb.append("<null>");
			} else {
				sb.append(D14_PRODUT);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row14Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.D14_PRODUT, other.D14_PRODUT);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tHashInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tHashInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tHashInput_1");
		org.slf4j.MDC.put("_subJobPid", "jkhzyW_" + subJobPidCounter.getAndIncrement());

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row14Struct row14 = new row14Struct();

				/**
				 * [tAdvancedHash_row14 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row14", false);
				start_Hash.put("tAdvancedHash_row14", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row14";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row14");

				int tos_count_tAdvancedHash_row14 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row14", "tAdvancedHash_row14", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:row14
				// source node:tHashInput_1 - inputs:(after_tHashInput_3) outputs:(row14,row14)
				// | target node:tAdvancedHash_row14 - inputs:(row14) outputs:()
				// linked node: tMap_4 - inputs:(row4,row14,row24) outputs:(Itens_ped,out1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row14 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row14Struct> tHash_Lookup_row14 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row14Struct>getLookup(matchingModeEnum_row14);

				globalMap.put("tHash_Lookup_row14", tHash_Lookup_row14);

				/**
				 * [tAdvancedHash_row14 begin ] stop
				 */

				/**
				 * [tHashInput_1 begin ] start
				 */

				ok_Hash.put("tHashInput_1", false);
				start_Hash.put("tHashInput_1", System.currentTimeMillis());

				currentComponent = "tHashInput_1";

				cLabel = "Estoque";

				int tos_count_tHashInput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_1", "Estoque", "tHashInput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tHashInput_1 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<EstoqueStruct> tHashFile_tHashInput_1 = mf_tHashInput_1
						.getAdvancedMemoryHashFile("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_1 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<EstoqueStruct> iterator_tHashInput_1 = tHashFile_tHashInput_1.iterator();
				while (iterator_tHashInput_1.hasNext()) {
					EstoqueStruct next_tHashInput_1 = iterator_tHashInput_1.next();

					row14.D14_PRODUT = next_tHashInput_1.D14_PRODUT;
					row14.ESTOQUE = next_tHashInput_1.ESTOQUE;

					/**
					 * [tHashInput_1 begin ] stop
					 */

					/**
					 * [tHashInput_1 main ] start
					 */

					currentComponent = "tHashInput_1";

					cLabel = "Estoque";

					tos_count_tHashInput_1++;

					/**
					 * [tHashInput_1 main ] stop
					 */

					/**
					 * [tHashInput_1 process_data_begin ] start
					 */

					currentComponent = "tHashInput_1";

					cLabel = "Estoque";

					/**
					 * [tHashInput_1 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row14 main ] start
					 */

					currentComponent = "tAdvancedHash_row14";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row14", "tHashInput_1", "Estoque", "tHashInput", "tAdvancedHash_row14",
							"tAdvancedHash_row14", "tAdvancedHash"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row14 - " + (row14 == null ? "" : row14.toLogString()));
					}

					row14Struct row14_HashRow = new row14Struct();

					row14_HashRow.D14_PRODUT = row14.D14_PRODUT;

					row14_HashRow.ESTOQUE = row14.ESTOQUE;

					tHash_Lookup_row14.put(row14_HashRow);

					tos_count_tAdvancedHash_row14++;

					/**
					 * [tAdvancedHash_row14 main ] stop
					 */

					/**
					 * [tAdvancedHash_row14 process_data_begin ] start
					 */

					currentComponent = "tAdvancedHash_row14";

					/**
					 * [tAdvancedHash_row14 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row14 process_data_end ] start
					 */

					currentComponent = "tAdvancedHash_row14";

					/**
					 * [tAdvancedHash_row14 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 process_data_end ] start
					 */

					currentComponent = "tHashInput_1";

					cLabel = "Estoque";

					/**
					 * [tHashInput_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 end ] start
					 */

					currentComponent = "tHashInput_1";

					cLabel = "Estoque";

					nb_line_tHashInput_1++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_1");

				globalMap.put("tHashInput_1_NB_LINE", nb_line_tHashInput_1);

				ok_Hash.put("tHashInput_1", true);
				end_Hash.put("tHashInput_1", System.currentTimeMillis());

				/**
				 * [tHashInput_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row14 end ] start
				 */

				currentComponent = "tAdvancedHash_row14";

				tHash_Lookup_row14.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row14", 2, 0,
						"tHashInput_1", "Estoque", "tHashInput", "tAdvancedHash_row14", "tAdvancedHash_row14",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row14", true);
				end_Hash.put("tAdvancedHash_row14", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row14 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tHashInput_1 finally ] start
				 */

				currentComponent = "tHashInput_1";

				cLabel = "Estoque";

				/**
				 * [tHashInput_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row14 finally ] start
				 */

				currentComponent = "tAdvancedHash_row14";

				/**
				 * [tAdvancedHash_row14 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tHashInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return false;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public float DB_PEDI_QTDE_SOLIC;

		public float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public double ESTOQUE;

		public double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return false;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public Integer EstoqueAtualizado;

		public Integer getEstoqueAtualizado() {
			return this.EstoqueAtualizado;
		}

		public Boolean EstoqueAtualizadoIsNullable() {
			return true;
		}

		public Boolean EstoqueAtualizadoIsKey() {
			return false;
		}

		public Integer EstoqueAtualizadoLength() {
			return 10;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return "";

		}

		public String EstoqueAtualizadoComment() {

			return "";

		}

		public String EstoqueAtualizadoPattern() {

			return "";

		}

		public String EstoqueAtualizadoOriginalDbColumnName() {

			return "EstoqueAtualizado";

		}

		public Integer FlagSemEstoque;

		public Integer getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return 10;
		}

		public Integer FlagSemEstoquePrecision() {
			return null;
		}

		public String FlagSemEstoqueDefault() {

			return null;

		}

		public String FlagSemEstoqueComment() {

			return "";

		}

		public String FlagSemEstoquePattern() {

			return "";

		}

		public String FlagSemEstoqueOriginalDbColumnName() {

			return "FlagSemEstoque";

		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public Boolean errorMessageIsNullable() {
			return false;
		}

		public Boolean errorMessageIsKey() {
			return false;
		}

		public Integer errorMessageLength() {
			return 255;
		}

		public Integer errorMessagePrecision() {
			return 0;
		}

		public String errorMessageDefault() {

			return null;

		}

		public String errorMessageComment() {

			return null;

		}

		public String errorMessagePattern() {

			return null;

		}

		public String errorMessageOriginalDbColumnName() {

			return "errorMessage";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row6Struct other = (row6Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.EstoqueAtualizado = this.EstoqueAtualizado;
			other.FlagSemEstoque = this.FlagSemEstoque;
			other.errorMessage = this.errorMessage;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + String.valueOf(FlagSemEstoque));
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			sb.append(DB_PEDI_QTDE_SOLIC);

			sb.append("|");

			sb.append(ESTOQUE);

			sb.append("|");

			if (EstoqueAtualizado == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueAtualizado);
			}

			sb.append("|");

			if (FlagSemEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(FlagSemEstoque);
			}

			sb.append("|");

			if (errorMessage == null) {
				sb.append("<null>");
			} else {
				sb.append(errorMessage);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return false;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public float DB_PEDI_QTDE_SOLIC;

		public float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public double ESTOQUE;

		public double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return false;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public Integer EstoqueAtualizado;

		public Integer getEstoqueAtualizado() {
			return this.EstoqueAtualizado;
		}

		public Boolean EstoqueAtualizadoIsNullable() {
			return true;
		}

		public Boolean EstoqueAtualizadoIsKey() {
			return false;
		}

		public Integer EstoqueAtualizadoLength() {
			return 10;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return "";

		}

		public String EstoqueAtualizadoComment() {

			return "";

		}

		public String EstoqueAtualizadoPattern() {

			return "";

		}

		public String EstoqueAtualizadoOriginalDbColumnName() {

			return "EstoqueAtualizado";

		}

		public Integer FlagSemEstoque;

		public Integer getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return 10;
		}

		public Integer FlagSemEstoquePrecision() {
			return null;
		}

		public String FlagSemEstoqueDefault() {

			return null;

		}

		public String FlagSemEstoqueComment() {

			return "";

		}

		public String FlagSemEstoquePattern() {

			return "";

		}

		public String FlagSemEstoqueOriginalDbColumnName() {

			return "FlagSemEstoque";

		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public Boolean errorMessageIsNullable() {
			return true;
		}

		public Boolean errorMessageIsKey() {
			return false;
		}

		public Integer errorMessageLength() {
			return 255;
		}

		public Integer errorMessagePrecision() {
			return 0;
		}

		public String errorMessageDefault() {

			return null;

		}

		public String errorMessageComment() {

			return null;

		}

		public String errorMessagePattern() {

			return null;

		}

		public String errorMessageOriginalDbColumnName() {

			return "errorMessage";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row7Struct other = (row7Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row7Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.EstoqueAtualizado = this.EstoqueAtualizado;
			other.FlagSemEstoque = this.FlagSemEstoque;
			other.errorMessage = this.errorMessage;

		}

		public void copyKeysDataTo(row7Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + String.valueOf(FlagSemEstoque));
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			sb.append(DB_PEDI_QTDE_SOLIC);

			sb.append("|");

			sb.append(ESTOQUE);

			sb.append("|");

			if (EstoqueAtualizado == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueAtualizado);
			}

			sb.append("|");

			if (FlagSemEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(FlagSemEstoque);
			}

			sb.append("|");

			if (errorMessage == null) {
				sb.append("<null>");
			} else {
				sb.append(errorMessage);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return false;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public float DB_PEDI_QTDE_SOLIC;

		public float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public double ESTOQUE;

		public double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return false;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public Integer EstoqueAtualizado;

		public Integer getEstoqueAtualizado() {
			return this.EstoqueAtualizado;
		}

		public Boolean EstoqueAtualizadoIsNullable() {
			return true;
		}

		public Boolean EstoqueAtualizadoIsKey() {
			return false;
		}

		public Integer EstoqueAtualizadoLength() {
			return 10;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return "";

		}

		public String EstoqueAtualizadoComment() {

			return "";

		}

		public String EstoqueAtualizadoPattern() {

			return "";

		}

		public String EstoqueAtualizadoOriginalDbColumnName() {

			return "EstoqueAtualizado";

		}

		public Integer FlagSemEstoque;

		public Integer getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return 10;
		}

		public Integer FlagSemEstoquePrecision() {
			return null;
		}

		public String FlagSemEstoqueDefault() {

			return null;

		}

		public String FlagSemEstoqueComment() {

			return "";

		}

		public String FlagSemEstoquePattern() {

			return "";

		}

		public String FlagSemEstoqueOriginalDbColumnName() {

			return "FlagSemEstoque";

		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public Boolean errorMessageIsNullable() {
			return false;
		}

		public Boolean errorMessageIsKey() {
			return false;
		}

		public Integer errorMessageLength() {
			return 255;
		}

		public Integer errorMessagePrecision() {
			return 0;
		}

		public String errorMessageDefault() {

			return null;

		}

		public String errorMessageComment() {

			return null;

		}

		public String errorMessagePattern() {

			return null;

		}

		public String errorMessageOriginalDbColumnName() {

			return "errorMessage";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.EstoqueAtualizado = this.EstoqueAtualizado;
			other.FlagSemEstoque = this.FlagSemEstoque;
			other.errorMessage = this.errorMessage;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + String.valueOf(FlagSemEstoque));
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			sb.append(DB_PEDI_QTDE_SOLIC);

			sb.append("|");

			sb.append(ESTOQUE);

			sb.append("|");

			if (EstoqueAtualizado == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueAtualizado);
			}

			sb.append("|");

			if (FlagSemEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(FlagSemEstoque);
			}

			sb.append("|");

			if (errorMessage == null) {
				sb.append("<null>");
			} else {
				sb.append(errorMessage);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_1> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return false;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public float DB_PEDI_QTDE_SOLIC;

		public float getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return false;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public double ESTOQUE;

		public double getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return false;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public Integer EstoqueAtualizado;

		public Integer getEstoqueAtualizado() {
			return this.EstoqueAtualizado;
		}

		public Boolean EstoqueAtualizadoIsNullable() {
			return true;
		}

		public Boolean EstoqueAtualizadoIsKey() {
			return false;
		}

		public Integer EstoqueAtualizadoLength() {
			return 10;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return "";

		}

		public String EstoqueAtualizadoComment() {

			return "";

		}

		public String EstoqueAtualizadoPattern() {

			return "";

		}

		public String EstoqueAtualizadoOriginalDbColumnName() {

			return "EstoqueAtualizado";

		}

		public Integer FlagSemEstoque;

		public Integer getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return 10;
		}

		public Integer FlagSemEstoquePrecision() {
			return null;
		}

		public String FlagSemEstoqueDefault() {

			return null;

		}

		public String FlagSemEstoqueComment() {

			return "";

		}

		public String FlagSemEstoquePattern() {

			return "";

		}

		public String FlagSemEstoqueOriginalDbColumnName() {

			return "FlagSemEstoque";

		}

		public String errorMessage;

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public Boolean errorMessageIsNullable() {
			return false;
		}

		public Boolean errorMessageIsKey() {
			return false;
		}

		public Integer errorMessageLength() {
			return 255;
		}

		public Integer errorMessagePrecision() {
			return 0;
		}

		public String errorMessageDefault() {

			return null;

		}

		public String errorMessageComment() {

			return null;

		}

		public String errorMessagePattern() {

			return null;

		}

		public String errorMessageOriginalDbColumnName() {

			return "errorMessage";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final OnRowsEndStructtAggregateRow_1 other = (OnRowsEndStructtAggregateRow_1) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtAggregateRow_1 other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.EstoqueAtualizado = this.EstoqueAtualizado;
			other.FlagSemEstoque = this.FlagSemEstoque;
			other.errorMessage = this.errorMessage;

		}

		public void copyKeysDataTo(OnRowsEndStructtAggregateRow_1 other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = dis.readFloat();

					this.ESTOQUE = dis.readDouble();

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

					this.errorMessage = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// float

				dos.writeFloat(this.DB_PEDI_QTDE_SOLIC);

				// double

				dos.writeDouble(this.ESTOQUE);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

				// String

				writeString(this.errorMessage, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + String.valueOf(FlagSemEstoque));
			sb.append(",errorMessage=" + errorMessage);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			sb.append(DB_PEDI_QTDE_SOLIC);

			sb.append("|");

			sb.append(ESTOQUE);

			sb.append("|");

			if (EstoqueAtualizado == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueAtualizado);
			}

			sb.append("|");

			if (FlagSemEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(FlagSemEstoque);
			}

			sb.append("|");

			if (errorMessage == null) {
				sb.append("<null>");
			} else {
				sb.append(errorMessage);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_1 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Integer DB_PEDI_QTDE_SOLIC;

		public Integer getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Integer ESTOQUE;

		public Integer getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public Integer EstoqueAtualizado;

		public Integer getEstoqueAtualizado() {
			return this.EstoqueAtualizado;
		}

		public Boolean EstoqueAtualizadoIsNullable() {
			return true;
		}

		public Boolean EstoqueAtualizadoIsKey() {
			return false;
		}

		public Integer EstoqueAtualizadoLength() {
			return 10;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return "";

		}

		public String EstoqueAtualizadoComment() {

			return "";

		}

		public String EstoqueAtualizadoPattern() {

			return "";

		}

		public String EstoqueAtualizadoOriginalDbColumnName() {

			return "EstoqueAtualizado";

		}

		public Integer FlagSemEstoque;

		public Integer getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return 10;
		}

		public Integer FlagSemEstoquePrecision() {
			return null;
		}

		public String FlagSemEstoqueDefault() {

			return null;

		}

		public String FlagSemEstoqueComment() {

			return "";

		}

		public String FlagSemEstoquePattern() {

			return "";

		}

		public String FlagSemEstoqueOriginalDbColumnName() {

			return "FlagSemEstoque";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row9Struct other = (row9Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.EstoqueAtualizado = this.EstoqueAtualizado;
			other.FlagSemEstoque = this.FlagSemEstoque;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// Integer

				writeInteger(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + String.valueOf(FlagSemEstoque));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (EstoqueAtualizado == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueAtualizado);
			}

			sb.append("|");

			if (FlagSemEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(FlagSemEstoque);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row25Struct implements routines.system.IPersistableRow<row25Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Integer DB_PEDI_QTDE_SOLIC;

		public Integer getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Integer ESTOQUE;

		public Integer getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row25Struct other = (row25Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(row25Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;

		}

		public void copyKeysDataTo(row25Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row25Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtSortRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_1> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Integer DB_PEDI_QTDE_SOLIC;

		public Integer getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Integer ESTOQUE;

		public Integer getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final OnRowsEndStructtSortRow_1 other = (OnRowsEndStructtSortRow_1) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(OnRowsEndStructtSortRow_1 other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;

		}

		public void copyKeysDataTo(OnRowsEndStructtSortRow_1 other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_1 other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Itens_pedStruct implements routines.system.IPersistableRow<Itens_pedStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Integer DB_PEDI_QTDE_SOLIC;

		public Integer getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Integer ESTOQUE;

		public Integer getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Itens_pedStruct other = (Itens_pedStruct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(Itens_pedStruct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;

		}

		public void copyKeysDataTo(Itens_pedStruct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Itens_pedStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public String DB_PEDI_PRODUTO;

		public String getDB_PEDI_PRODUTO() {
			return this.DB_PEDI_PRODUTO;
		}

		public Boolean DB_PEDI_PRODUTOIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_PRODUTOIsKey() {
			return false;
		}

		public Integer DB_PEDI_PRODUTOLength() {
			return 16;
		}

		public Integer DB_PEDI_PRODUTOPrecision() {
			return 0;
		}

		public String DB_PEDI_PRODUTODefault() {

			return null;

		}

		public String DB_PEDI_PRODUTOComment() {

			return "";

		}

		public String DB_PEDI_PRODUTOPattern() {

			return "";

		}

		public String DB_PEDI_PRODUTOOriginalDbColumnName() {

			return "DB_PEDI_PRODUTO";

		}

		public Integer DB_PEDI_QTDE_SOLIC;

		public Integer getDB_PEDI_QTDE_SOLIC() {
			return this.DB_PEDI_QTDE_SOLIC;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsNullable() {
			return true;
		}

		public Boolean DB_PEDI_QTDE_SOLICIsKey() {
			return false;
		}

		public Integer DB_PEDI_QTDE_SOLICLength() {
			return 7;
		}

		public Integer DB_PEDI_QTDE_SOLICPrecision() {
			return 0;
		}

		public String DB_PEDI_QTDE_SOLICDefault() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICComment() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICPattern() {

			return "";

		}

		public String DB_PEDI_QTDE_SOLICOriginalDbColumnName() {

			return "DB_PEDI_QTDE_SOLIC";

		}

		public Integer ESTOQUE;

		public Integer getESTOQUE() {
			return this.ESTOQUE;
		}

		public Boolean ESTOQUEIsNullable() {
			return true;
		}

		public Boolean ESTOQUEIsKey() {
			return false;
		}

		public Integer ESTOQUELength() {
			return 15;
		}

		public Integer ESTOQUEPrecision() {
			return 0;
		}

		public String ESTOQUEDefault() {

			return "";

		}

		public String ESTOQUEComment() {

			return "";

		}

		public String ESTOQUEPattern() {

			return "";

		}

		public String ESTOQUEOriginalDbColumnName() {

			return "ESTOQUE";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final out1Struct other = (out1Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(out1Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PEDI_PRODUTO = this.DB_PEDI_PRODUTO;
			other.DB_PEDI_QTDE_SOLIC = this.DB_PEDI_QTDE_SOLIC;
			other.ESTOQUE = this.ESTOQUE;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;

		}

		public void copyKeysDataTo(out1Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PEDI_PRODUTO = readString(dis);

					this.DB_PEDI_QTDE_SOLIC = readInteger(dis);

					this.ESTOQUE = readInteger(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// String

				writeString(this.DB_PEDI_PRODUTO, dos);

				// Integer

				writeInteger(this.DB_PEDI_QTDE_SOLIC, dos);

				// Integer

				writeInteger(this.ESTOQUE, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PEDI_PRODUTO=" + DB_PEDI_PRODUTO);
			sb.append(",DB_PEDI_QTDE_SOLIC=" + String.valueOf(DB_PEDI_QTDE_SOLIC));
			sb.append(",ESTOQUE=" + String.valueOf(ESTOQUE));
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PEDI_PRODUTO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_PRODUTO);
			}

			sb.append("|");

			if (DB_PEDI_QTDE_SOLIC == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PEDI_QTDE_SOLIC);
			}

			sb.append("|");

			if (ESTOQUE == null) {
				sb.append("<null>");
			} else {
				sb.append(ESTOQUE);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row24Struct implements routines.system.IPersistableRow<row24Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row24Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tHashInput_3Struct implements routines.system.IPersistableRow<after_tHashInput_3Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Integracao_Pedidos = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer DB_PED_NRO;

		public Integer getDB_PED_NRO() {
			return this.DB_PED_NRO;
		}

		public Boolean DB_PED_NROIsNullable() {
			return true;
		}

		public Boolean DB_PED_NROIsKey() {
			return true;
		}

		public Integer DB_PED_NROLength() {
			return 10;
		}

		public Integer DB_PED_NROPrecision() {
			return 0;
		}

		public String DB_PED_NRODefault() {

			return "";

		}

		public String DB_PED_NROComment() {

			return "";

		}

		public String DB_PED_NROPattern() {

			return "";

		}

		public String DB_PED_NROOriginalDbColumnName() {

			return "DB_PED_NRO";

		}

		public String DB_PED_OPERACAO;

		public String getDB_PED_OPERACAO() {
			return this.DB_PED_OPERACAO;
		}

		public Boolean DB_PED_OPERACAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_OPERACAOIsKey() {
			return false;
		}

		public Integer DB_PED_OPERACAOLength() {
			return 10;
		}

		public Integer DB_PED_OPERACAOPrecision() {
			return 0;
		}

		public String DB_PED_OPERACAODefault() {

			return null;

		}

		public String DB_PED_OPERACAOComment() {

			return "";

		}

		public String DB_PED_OPERACAOPattern() {

			return "";

		}

		public String DB_PED_OPERACAOOriginalDbColumnName() {

			return "DB_PED_OPERACAO";

		}

		public java.util.Date DB_PED_DT_EMISSAO;

		public java.util.Date getDB_PED_DT_EMISSAO() {
			return this.DB_PED_DT_EMISSAO;
		}

		public Boolean DB_PED_DT_EMISSAOIsNullable() {
			return true;
		}

		public Boolean DB_PED_DT_EMISSAOIsKey() {
			return false;
		}

		public Integer DB_PED_DT_EMISSAOLength() {
			return 23;
		}

		public Integer DB_PED_DT_EMISSAOPrecision() {
			return 3;
		}

		public String DB_PED_DT_EMISSAODefault() {

			return null;

		}

		public String DB_PED_DT_EMISSAOComment() {

			return "";

		}

		public String DB_PED_DT_EMISSAOPattern() {

			return "dd-MM-yyyy";

		}

		public String DB_PED_DT_EMISSAOOriginalDbColumnName() {

			return "DB_PED_DT_EMISSAO";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tHashInput_3Struct other = (after_tHashInput_3Struct) obj;

			if (this.DB_PED_NRO == null) {
				if (other.DB_PED_NRO != null)
					return false;

			} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))

				return false;

			return true;
		}

		public void copyDataTo(after_tHashInput_3Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;
			other.DB_PED_OPERACAO = this.DB_PED_OPERACAO;
			other.DB_PED_DT_EMISSAO = this.DB_PED_DT_EMISSAO;

		}

		public void copyKeysDataTo(after_tHashInput_3Struct other) {

			other.DB_PED_NRO = this.DB_PED_NRO;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Integracao_Pedidos.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Integracao_Pedidos.length == 0) {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Integracao_Pedidos = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Integracao_Pedidos, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Integracao_Pedidos) {

				try {

					int length = 0;

					this.DB_PED_NRO = readInteger(dis);

					this.DB_PED_OPERACAO = readString(dis);

					this.DB_PED_DT_EMISSAO = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DB_PED_NRO, dos);

				// String

				writeString(this.DB_PED_OPERACAO, dos);

				// java.util.Date

				writeDate(this.DB_PED_DT_EMISSAO, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DB_PED_NRO=" + String.valueOf(DB_PED_NRO));
			sb.append(",DB_PED_OPERACAO=" + DB_PED_OPERACAO);
			sb.append(",DB_PED_DT_EMISSAO=" + String.valueOf(DB_PED_DT_EMISSAO));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DB_PED_NRO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_NRO);
			}

			sb.append("|");

			if (DB_PED_OPERACAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_OPERACAO);
			}

			sb.append("|");

			if (DB_PED_DT_EMISSAO == null) {
				sb.append("<null>");
			} else {
				sb.append(DB_PED_DT_EMISSAO);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tHashInput_3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.DB_PED_NRO, other.DB_PED_NRO);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tHashInput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tHashInput_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tHashInput_3");
		org.slf4j.MDC.put("_subJobPid", "KByYP9_" + subJobPidCounter.getAndIncrement());

		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tDBInput_3Process(globalMap);
				tHashInput_1Process(globalMap);

				row24Struct row24 = new row24Struct();
				Itens_pedStruct Itens_ped = new Itens_pedStruct();
				row25Struct row25 = new row25Struct();
				row9Struct row9 = new row9Struct();
				row5Struct row5 = new row5Struct();
				row6Struct row6 = new row6Struct();
				row7Struct row7 = new row7Struct();
				out1Struct out1 = new out1Struct();

				/**
				 * [tSortRow_1_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_1_SortOut", false);
				start_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Itens_ped");

				int tos_count_tSortRow_1_SortOut = 0;

				if (log.isDebugEnabled())
					log.debug("tSortRow_1_SortOut - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSortRow_1_SortOut {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSortRow_1_SortOut = new StringBuilder();
							log4jParamters_tSortRow_1_SortOut.append("Parameters:");
							log4jParamters_tSortRow_1_SortOut.append("DESTINATION" + " = " + "tSortRow_1");
							log4jParamters_tSortRow_1_SortOut.append(" | ");
							log4jParamters_tSortRow_1_SortOut.append("EXTERNAL" + " = " + "false");
							log4jParamters_tSortRow_1_SortOut.append(" | ");
							log4jParamters_tSortRow_1_SortOut
									.append("CRITERIA" + " = " + "[{ORDER=" + ("asc") + ", COLNAME="
											+ ("DB_PED_DT_EMISSAO") + ", SORT=" + ("date") + "}, {ORDER=" + ("asc")
											+ ", COLNAME=" + ("DB_PEDI_PRODUTO") + ", SORT=" + ("alpha") + "}, {ORDER="
											+ ("asc") + ", COLNAME=" + ("DB_PED_NRO") + ", SORT=" + ("num") + "}]");
							log4jParamters_tSortRow_1_SortOut.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSortRow_1_SortOut - " + (log4jParamters_tSortRow_1_SortOut));
						}
					}
					new BytesLimit65535_tSortRow_1_SortOut().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSortRow_1_SortOut", "Ordena\u00E7\u00E3o de pedido_SortOut", "tSortOut");
					talendJobLogProcess(globalMap);
				}

				class ComparableItens_pedStruct extends Itens_pedStruct
						implements Comparable<ComparableItens_pedStruct> {

					public int compareTo(ComparableItens_pedStruct other) {

						if (this.DB_PED_DT_EMISSAO == null && other.DB_PED_DT_EMISSAO != null) {
							return -1;

						} else if (this.DB_PED_DT_EMISSAO != null && other.DB_PED_DT_EMISSAO == null) {
							return 1;

						} else if (this.DB_PED_DT_EMISSAO != null && other.DB_PED_DT_EMISSAO != null) {
							if (!this.DB_PED_DT_EMISSAO.equals(other.DB_PED_DT_EMISSAO)) {
								return this.DB_PED_DT_EMISSAO.compareTo(other.DB_PED_DT_EMISSAO);
							}
						}
						if (this.DB_PEDI_PRODUTO == null && other.DB_PEDI_PRODUTO != null) {
							return -1;

						} else if (this.DB_PEDI_PRODUTO != null && other.DB_PEDI_PRODUTO == null) {
							return 1;

						} else if (this.DB_PEDI_PRODUTO != null && other.DB_PEDI_PRODUTO != null) {
							if (!this.DB_PEDI_PRODUTO.equals(other.DB_PEDI_PRODUTO)) {
								return this.DB_PEDI_PRODUTO.compareTo(other.DB_PEDI_PRODUTO);
							}
						}
						if (this.DB_PED_NRO == null && other.DB_PED_NRO != null) {
							return -1;

						} else if (this.DB_PED_NRO != null && other.DB_PED_NRO == null) {
							return 1;

						} else if (this.DB_PED_NRO != null && other.DB_PED_NRO != null) {
							if (!this.DB_PED_NRO.equals(other.DB_PED_NRO)) {
								return this.DB_PED_NRO.compareTo(other.DB_PED_NRO);
							}
						}
						return 0;
					}
				}

				java.util.List<ComparableItens_pedStruct> list_tSortRow_1_SortOut = new java.util.ArrayList<ComparableItens_pedStruct>();

				/**
				 * [tSortRow_1_SortOut begin ] stop
				 */

				/**
				 * [tFileOutputExcel_2 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_2", false);
				start_Hash.put("tFileOutputExcel_2", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tFileOutputExcel_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputExcel_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputExcel_2 = new StringBuilder();
							log4jParamters_tFileOutputExcel_2.append("Parameters:");
							log4jParamters_tFileOutputExcel_2.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2
									.append("FILENAME" + " = " + "\"C:/Users/joao.santos/Downloads/ped.xlsx\"");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("SHEETNAME" + " = " + "\"Sheet1\"");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("APPEND_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("FIRST_CELL_Y_ABSOLUTE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("FONT" + " = " + "");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("IS_ALL_AUTO_SZIE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("AUTO_SZIE_SETTING" + " = " + "[{IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("DB_PED_NRO") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_OPERACAO") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_PRODUTO") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PEDI_QTDE_SOLIC") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ESTOQUE") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DB_PED_DT_EMISSAO") + "}]");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("PROTECT_FILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("TRUNCATE_EXCEEDING_CHARACTERS" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							log4jParamters_tFileOutputExcel_2.append("USE_SHARED_STRINGS_TABLE" + " = " + "false");
							log4jParamters_tFileOutputExcel_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputExcel_2 - " + (log4jParamters_tFileOutputExcel_2));
						}
					}
					new BytesLimit65535_tFileOutputExcel_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputExcel_2", "tFileOutputExcel_2", "tFileOutputExcel");
					talendJobLogProcess(globalMap);
				}

				int columnIndex_tFileOutputExcel_2 = 0;
				boolean headerIsInserted_tFileOutputExcel_2 = false;

				String fileName_tFileOutputExcel_2 = "C:/Users/joao.santos/Downloads/ped.xlsx";
				int nb_line_tFileOutputExcel_2 = 0;
				org.talend.ExcelTool xlsxTool_tFileOutputExcel_2 = new org.talend.ExcelTool();
				xlsxTool_tFileOutputExcel_2.setUseSharedStringTable(false);

				xlsxTool_tFileOutputExcel_2.setTruncateExceedingCharacters(false);
				xlsxTool_tFileOutputExcel_2.setSheet("Sheet1");
				xlsxTool_tFileOutputExcel_2.setAppend(false, false, false);
				xlsxTool_tFileOutputExcel_2.setRecalculateFormula(false);
				xlsxTool_tFileOutputExcel_2.setXY(false, 0, 0, false);

				java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object> chm_tFileOutputExcel_2 = (java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object>) globalMap
						.get("concurrentHashMap");
				java.lang.Object lockObj_tFileOutputExcel_2 = chm_tFileOutputExcel_2
						.computeIfAbsent("EXCEL_OUTPUT_LOCK_OBJ_tFileOutputExcel_2", k -> new Object());
				synchronized (lockObj_tFileOutputExcel_2) {

					xlsxTool_tFileOutputExcel_2.prepareXlsxFile(fileName_tFileOutputExcel_2);

				}

				xlsxTool_tFileOutputExcel_2.setFont("");

				if (xlsxTool_tFileOutputExcel_2.getStartRow() == 0) {

					xlsxTool_tFileOutputExcel_2.addRow();

					xlsxTool_tFileOutputExcel_2.addCellValue("DB_PED_NRO");

					xlsxTool_tFileOutputExcel_2.addCellValue("DB_PED_OPERACAO");

					xlsxTool_tFileOutputExcel_2.addCellValue("DB_PEDI_PRODUTO");

					xlsxTool_tFileOutputExcel_2.addCellValue("DB_PEDI_QTDE_SOLIC");

					xlsxTool_tFileOutputExcel_2.addCellValue("ESTOQUE");

					xlsxTool_tFileOutputExcel_2.addCellValue("DB_PED_DT_EMISSAO");

					nb_line_tFileOutputExcel_2++;
					headerIsInserted_tFileOutputExcel_2 = true;

				}

				/**
				 * [tFileOutputExcel_2 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row24");

				int tos_count_tMap_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_4 = new StringBuilder();
							log4jParamters_tMap_4.append("Parameters:");
							log4jParamters_tMap_4.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_4.append(" | ");
							log4jParamters_tMap_4.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_4 - " + (log4jParamters_tMap_4));
						}
					}
					new BytesLimit65535_tMap_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_4", "tMap_4", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row24_tMap_4 = 0;

				int count_row4_tMap_4 = 0;

				int count_row14_tMap_4 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row14Struct> tHash_Lookup_row14 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row14Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row14Struct>) globalMap
						.get("tHash_Lookup_row14"));

				row14Struct row14HashKey = new row14Struct();
				row14Struct row14Default = new row14Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_Itens_ped_tMap_4 = 0;

				Itens_pedStruct Itens_ped_tmp = new Itens_pedStruct();
				int count_out1_tMap_4 = 0;

				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tHashInput_3 begin ] start
				 */

				ok_Hash.put("tHashInput_3", false);
				start_Hash.put("tHashInput_3", System.currentTimeMillis());

				currentComponent = "tHashInput_3";

				int tos_count_tHashInput_3 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_3", "tHashInput_3", "tHashInput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tHashInput_3 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_3 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row3Struct> tHashFile_tHashInput_3 = mf_tHashInput_3
						.getAdvancedMemoryHashFile("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_2");
				if (tHashFile_tHashInput_3 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<row3Struct> iterator_tHashInput_3 = tHashFile_tHashInput_3.iterator();
				while (iterator_tHashInput_3.hasNext()) {
					row3Struct next_tHashInput_3 = iterator_tHashInput_3.next();

					row24.DB_PED_NRO = next_tHashInput_3.DB_PED_NRO;
					row24.DB_PED_OPERACAO = next_tHashInput_3.DB_PED_OPERACAO;
					row24.DB_PED_DT_EMISSAO = next_tHashInput_3.DB_PED_DATA_ENVIO;

					/**
					 * [tHashInput_3 begin ] stop
					 */

					/**
					 * [tHashInput_3 main ] start
					 */

					currentComponent = "tHashInput_3";

					tos_count_tHashInput_3++;

					/**
					 * [tHashInput_3 main ] stop
					 */

					/**
					 * [tHashInput_3 process_data_begin ] start
					 */

					currentComponent = "tHashInput_3";

					/**
					 * [tHashInput_3 process_data_begin ] stop
					 */

					/**
					 * [tMap_4 main ] start
					 */

					currentComponent = "tMap_4";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row24", "tHashInput_3", "tHashInput_3", "tHashInput", "tMap_4", "tMap_4", "tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row24 - " + (row24 == null ? "" : row24.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

					row4Struct row4 = null;

					row14Struct row14 = null;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_4 = false;
					boolean mainRowRejected_tMap_4 = false;

					///////////////////////////////////////////////
					// Starting Lookup Table "row4"
					///////////////////////////////////////////////

					boolean forceLooprow4 = false;

					row4Struct row4ObjectFromLookup = null;

					if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

						hasCasePrimitiveKeyWithNull_tMap_4 = false;

						row4HashKey.DB_PEDI_PEDIDO = row24.DB_PED_NRO;

						row4HashKey.hashCodeDirty = true;

						tHash_Lookup_row4.lookup(row4HashKey);

						if (!tHash_Lookup_row4.hasNext()) { // G_TM_M_090

							forceLooprow4 = true;

						} // G_TM_M_090

					} // G_TM_M_020

					else { // G 20 - G 21
						forceLooprow4 = true;
					} // G 21

					while ((tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) || forceLooprow4) { // G_TM_M_043

						// CALL close loop of lookup 'row4'

						row4Struct fromLookup_row4 = null;
						row4 = row4Default;

						if (!forceLooprow4) { // G 46

							fromLookup_row4 = tHash_Lookup_row4.next();

							if (fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}

						} // G 46

						forceLooprow4 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row14"
						///////////////////////////////////////////////

						boolean forceLooprow14 = false;

						row14Struct row14ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_4 = false;

							row14HashKey.D14_PRODUT = row4.DB_PEDI_PRODUTO;

							row14HashKey.hashCodeDirty = true;

							tHash_Lookup_row14.lookup(row14HashKey);

							if (!tHash_Lookup_row14.hasNext()) { // G_TM_M_090

								forceLooprow14 = true;

							} // G_TM_M_090

						} // G_TM_M_020

						else { // G 20 - G 21
							forceLooprow14 = true;
						} // G 21

						while ((tHash_Lookup_row14 != null && tHash_Lookup_row14.hasNext()) || forceLooprow14) { // G_TM_M_043

							// CALL close loop of lookup 'row14'

							row14Struct fromLookup_row14 = null;
							row14 = row14Default;

							if (!forceLooprow14) { // G 46

								fromLookup_row14 = tHash_Lookup_row14.next();

								if (fromLookup_row14 != null) {
									row14 = fromLookup_row14;
								}

							} // G 46

							forceLooprow14 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
								// ###############################
								// # Output tables

								Itens_ped = null;
								out1 = null;

// # Output table : 'Itens_ped'
								count_Itens_ped_tMap_4++;

								Itens_ped_tmp.DB_PED_NRO = row24.DB_PED_NRO;
								Itens_ped_tmp.DB_PED_OPERACAO = row24.DB_PED_OPERACAO;
								Itens_ped_tmp.DB_PEDI_PRODUTO = row4.DB_PEDI_PRODUTO;
								Itens_ped_tmp.DB_PEDI_QTDE_SOLIC = (int) (Relational.ISNULL(row4.DB_PEDI_QTDE_SOLIC) ? 0
										: row4.DB_PEDI_QTDE_SOLIC);
								Itens_ped_tmp.ESTOQUE = (int) (Relational.ISNULL(row14.ESTOQUE) ? 0 : row14.ESTOQUE);
								Itens_ped_tmp.DB_PED_DT_EMISSAO = row24.DB_PED_DT_EMISSAO;
								Itens_ped = Itens_ped_tmp;
								log.debug("tMap_4 - Outputting the record " + count_Itens_ped_tMap_4
										+ " of the output table 'Itens_ped'.");

// # Output table : 'out1'
								count_out1_tMap_4++;

								out1_tmp.DB_PED_NRO = row24.DB_PED_NRO;
								out1_tmp.DB_PED_OPERACAO = row24.DB_PED_OPERACAO;
								out1_tmp.DB_PEDI_PRODUTO = row4.DB_PEDI_PRODUTO;
								out1_tmp.DB_PEDI_QTDE_SOLIC = (int) (Relational.ISNULL(row4.DB_PEDI_QTDE_SOLIC) ? 0
										: row4.DB_PEDI_QTDE_SOLIC);
								out1_tmp.ESTOQUE = (int) (Relational.ISNULL(row14.ESTOQUE) ? 0 : row14.ESTOQUE);
								out1_tmp.DB_PED_DT_EMISSAO = row24.DB_PED_DT_EMISSAO;
								out1 = out1_tmp;
								log.debug("tMap_4 - Outputting the record " + count_out1_tMap_4
										+ " of the output table 'out1'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_4 = false;

							tos_count_tMap_4++;

							/**
							 * [tMap_4 main ] stop
							 */

							/**
							 * [tMap_4 process_data_begin ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_begin ] stop
							 */
// Start of branch "Itens_ped"
							if (Itens_ped != null) {

								/**
								 * [tSortRow_1_SortOut main ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortOut";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "Itens_ped", "tMap_4", "tMap_4", "tMap", "tSortRow_1_SortOut",
										"Ordena\u00E7\u00E3o de pedido_SortOut", "tSortOut"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("Itens_ped - " + (Itens_ped == null ? "" : Itens_ped.toLogString()));
								}

								ComparableItens_pedStruct arrayRowtSortRow_1_SortOut = new ComparableItens_pedStruct();

								arrayRowtSortRow_1_SortOut.DB_PED_NRO = Itens_ped.DB_PED_NRO;
								arrayRowtSortRow_1_SortOut.DB_PED_OPERACAO = Itens_ped.DB_PED_OPERACAO;
								arrayRowtSortRow_1_SortOut.DB_PEDI_PRODUTO = Itens_ped.DB_PEDI_PRODUTO;
								arrayRowtSortRow_1_SortOut.DB_PEDI_QTDE_SOLIC = Itens_ped.DB_PEDI_QTDE_SOLIC;
								arrayRowtSortRow_1_SortOut.ESTOQUE = Itens_ped.ESTOQUE;
								arrayRowtSortRow_1_SortOut.DB_PED_DT_EMISSAO = Itens_ped.DB_PED_DT_EMISSAO;
								list_tSortRow_1_SortOut.add(arrayRowtSortRow_1_SortOut);

								tos_count_tSortRow_1_SortOut++;

								/**
								 * [tSortRow_1_SortOut main ] stop
								 */

								/**
								 * [tSortRow_1_SortOut process_data_begin ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortOut";

								/**
								 * [tSortRow_1_SortOut process_data_begin ] stop
								 */

								/**
								 * [tSortRow_1_SortOut process_data_end ] start
								 */

								currentVirtualComponent = "tSortRow_1";

								currentComponent = "tSortRow_1_SortOut";

								/**
								 * [tSortRow_1_SortOut process_data_end ] stop
								 */

							} // End of branch "Itens_ped"

// Start of branch "out1"
							if (out1 != null) {

								/**
								 * [tFileOutputExcel_2 main ] start
								 */

								currentComponent = "tFileOutputExcel_2";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out1", "tMap_4", "tMap_4", "tMap", "tFileOutputExcel_2",
										"tFileOutputExcel_2", "tFileOutputExcel"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
								}

								xlsxTool_tFileOutputExcel_2.addRow();

								if (out1.DB_PED_NRO != null) {

									xlsxTool_tFileOutputExcel_2
											.addCellValue(Double.parseDouble(String.valueOf(out1.DB_PED_NRO)));
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								if (out1.DB_PED_OPERACAO != null) {

									xlsxTool_tFileOutputExcel_2.addCellValue(String.valueOf(out1.DB_PED_OPERACAO));
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								if (out1.DB_PEDI_PRODUTO != null) {

									xlsxTool_tFileOutputExcel_2.addCellValue(String.valueOf(out1.DB_PEDI_PRODUTO));
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								if (out1.DB_PEDI_QTDE_SOLIC != null) {

									xlsxTool_tFileOutputExcel_2
											.addCellValue(Double.parseDouble(String.valueOf(out1.DB_PEDI_QTDE_SOLIC)));
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								if (out1.ESTOQUE != null) {

									xlsxTool_tFileOutputExcel_2
											.addCellValue(Double.parseDouble(String.valueOf(out1.ESTOQUE)));
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								if (out1.DB_PED_DT_EMISSAO != null) {

									xlsxTool_tFileOutputExcel_2.addCellValue(out1.DB_PED_DT_EMISSAO, "dd-MM-yyyy");
								} else {
									xlsxTool_tFileOutputExcel_2.addCellNullValue();
								}

								nb_line_tFileOutputExcel_2++;

								log.debug("tFileOutputExcel_2 - Writing the record " + nb_line_tFileOutputExcel_2
										+ " to the file.");

								tos_count_tFileOutputExcel_2++;

								/**
								 * [tFileOutputExcel_2 main ] stop
								 */

								/**
								 * [tFileOutputExcel_2 process_data_begin ] start
								 */

								currentComponent = "tFileOutputExcel_2";

								/**
								 * [tFileOutputExcel_2 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputExcel_2 process_data_end ] start
								 */

								currentComponent = "tFileOutputExcel_2";

								/**
								 * [tFileOutputExcel_2 process_data_end ] stop
								 */

							} // End of branch "out1"

						} // close loop of lookup 'row14' // G_TM_M_043

					} // close loop of lookup 'row4' // G_TM_M_043

					/**
					 * [tMap_4 process_data_end ] start
					 */

					currentComponent = "tMap_4";

					/**
					 * [tMap_4 process_data_end ] stop
					 */

					/**
					 * [tHashInput_3 process_data_end ] start
					 */

					currentComponent = "tHashInput_3";

					/**
					 * [tHashInput_3 process_data_end ] stop
					 */

					/**
					 * [tHashInput_3 end ] start
					 */

					currentComponent = "tHashInput_3";

					nb_line_tHashInput_3++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_Integracao_Pedidos_" + pid + "_tHashOutput_2");

				globalMap.put("tHashInput_3_NB_LINE", nb_line_tHashInput_3);

				ok_Hash.put("tHashInput_3", true);
				end_Hash.put("tHashInput_3", System.currentTimeMillis());

				/**
				 * [tHashInput_3 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

				if (tHash_Lookup_row14 != null) {
					tHash_Lookup_row14.endGet();
				}
				globalMap.remove("tHash_Lookup_row14");

// ###############################      
				log.debug("tMap_4 - Written records count in the table 'Itens_ped': " + count_Itens_ped_tMap_4 + ".");
				log.debug("tMap_4 - Written records count in the table 'out1': " + count_out1_tMap_4 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row24", 2, 0,
						"tHashInput_3", "tHashInput_3", "tHashInput", "tMap_4", "tMap_4", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_4 - " + ("Done."));

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tSortRow_1_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				Itens_pedStruct[] array_tSortRow_1_SortOut = list_tSortRow_1_SortOut
						.toArray(new ComparableItens_pedStruct[0]);

				java.util.Arrays.sort(array_tSortRow_1_SortOut);

				globalMap.put("tSortRow_1", array_tSortRow_1_SortOut);

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Itens_ped", 2, 0,
						"tMap_4", "tMap_4", "tMap", "tSortRow_1_SortOut", "Ordena\u00E7\u00E3o de pedido_SortOut",
						"tSortOut", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tSortRow_1_SortOut - " + ("Done."));

				ok_Hash.put("tSortRow_1_SortOut", true);
				end_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_1_SortOut end ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_1_AGGOUT", false);
				start_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

				int tos_count_tAggregateRow_1_AGGOUT = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGOUT - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_1_AGGOUT {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_1_AGGOUT = new StringBuilder();
							log4jParamters_tAggregateRow_1_AGGOUT.append("Parameters:");
							log4jParamters_tAggregateRow_1_AGGOUT.append("DESTINATION" + " = " + "tAggregateRow_1");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("DB_PED_NRO") + ", INPUT_COLUMN=" + ("DB_PED_NRO") + "}]");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("FlagSemEstoque") + ", INPUT_COLUMN=" + ("FlagSemEstoque") + ", IGNORE_NULL="
									+ ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_1_AGGOUT - " + (log4jParamters_tAggregateRow_1_AGGOUT));
						}
					}
					new BytesLimit65535_tAggregateRow_1_AGGOUT().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_1_AGGOUT", "tAggregateRow_1_AGGOUT", "tAggregateOut");
					talendJobLogProcess(globalMap);
				}

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_1 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_1 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_1 utilClass_tAggregateRow_1 = new UtilClass_tAggregateRow_1();

				class AggOperationStruct_tAggregateRow_1 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					Integer DB_PED_NRO;
					Integer FlagSemEstoque_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.DB_PED_NRO == null) ? 0 : this.DB_PED_NRO.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_1 other = (AggOperationStruct_tAggregateRow_1) obj;

						if (this.DB_PED_NRO == null) {
							if (other.DB_PED_NRO != null)
								return false;
						} else if (!this.DB_PED_NRO.equals(other.DB_PED_NRO))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_1 operation_result_tAggregateRow_1 = null;
				AggOperationStruct_tAggregateRow_1 operation_finder_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();
				java.util.Map<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1> hash_tAggregateRow_1 = new java.util.HashMap<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1>();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] stop
				 */

				/**
				 * [tJavaRow_1 begin ] start
				 */

				ok_Hash.put("tJavaRow_1", false);
				start_Hash.put("tJavaRow_1", System.currentTimeMillis());

				currentComponent = "tJavaRow_1";

				cLabel = "Verifica\u00E7\u00E3o de estoque";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row25");

				int tos_count_tJavaRow_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tJavaRow_1", "Verifica\u00E7\u00E3o de estoque", "tJavaRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tJavaRow_1 = 0;

				/**
				 * [tJavaRow_1 begin ] stop
				 */

				/**
				 * [tSortRow_1_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_1_SortIn", false);
				start_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortIn";

				int tos_count_tSortRow_1_SortIn = 0;

				if (log.isDebugEnabled())
					log.debug("tSortRow_1_SortIn - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSortRow_1_SortIn {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSortRow_1_SortIn = new StringBuilder();
							log4jParamters_tSortRow_1_SortIn.append("Parameters:");
							log4jParamters_tSortRow_1_SortIn.append("ORIGIN" + " = " + "tSortRow_1");
							log4jParamters_tSortRow_1_SortIn.append(" | ");
							log4jParamters_tSortRow_1_SortIn.append("EXTERNAL" + " = " + "false");
							log4jParamters_tSortRow_1_SortIn.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSortRow_1_SortIn - " + (log4jParamters_tSortRow_1_SortIn));
						}
					}
					new BytesLimit65535_tSortRow_1_SortIn().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSortRow_1_SortIn", "Ordena\u00E7\u00E3o de pedido_SortIn", "tSortIn");
					talendJobLogProcess(globalMap);
				}

				Itens_pedStruct[] array_tSortRow_1_SortIn = (Itens_pedStruct[]) globalMap.remove("tSortRow_1");

				int nb_line_tSortRow_1_SortIn = 0;

				Itens_pedStruct current_tSortRow_1_SortIn = null;

				for (int i_tSortRow_1_SortIn = 0; i_tSortRow_1_SortIn < array_tSortRow_1_SortIn.length; i_tSortRow_1_SortIn++) {
					current_tSortRow_1_SortIn = array_tSortRow_1_SortIn[i_tSortRow_1_SortIn];
					row25.DB_PED_NRO = current_tSortRow_1_SortIn.DB_PED_NRO;
					row25.DB_PED_OPERACAO = current_tSortRow_1_SortIn.DB_PED_OPERACAO;
					row25.DB_PEDI_PRODUTO = current_tSortRow_1_SortIn.DB_PEDI_PRODUTO;
					row25.DB_PEDI_QTDE_SOLIC = current_tSortRow_1_SortIn.DB_PEDI_QTDE_SOLIC;
					row25.ESTOQUE = current_tSortRow_1_SortIn.ESTOQUE;
					row25.DB_PED_DT_EMISSAO = current_tSortRow_1_SortIn.DB_PED_DT_EMISSAO;
					// increase number of line sorted
					nb_line_tSortRow_1_SortIn++;

					/**
					 * [tSortRow_1_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_1_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					tos_count_tSortRow_1_SortIn++;

					/**
					 * [tSortRow_1_SortIn main ] stop
					 */

					/**
					 * [tSortRow_1_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					/**
					 * [tSortRow_1_SortIn process_data_begin ] stop
					 */

					/**
					 * [tJavaRow_1 main ] start
					 */

					currentComponent = "tJavaRow_1";

					cLabel = "Verifica\u00E7\u00E3o de estoque";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row25", "tSortRow_1_SortIn", "Ordena\u00E7\u00E3o de pedido_SortIn", "tSortIn",
							"tJavaRow_1", "Verifica\u00E7\u00E3o de estoque", "tJavaRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row25 - " + (row25 == null ? "" : row25.toLogString()));
					}

					row9.DB_PED_NRO = row25.DB_PED_NRO;
					row9.DB_PED_OPERACAO = row25.DB_PED_OPERACAO;
					row9.DB_PEDI_PRODUTO = row25.DB_PEDI_PRODUTO;
					row9.DB_PEDI_QTDE_SOLIC = row25.DB_PEDI_QTDE_SOLIC;
					row9.ESTOQUE = row25.ESTOQUE;

					if (globalMap.get("prodAnt") != row25.DB_PEDI_PRODUTO)
						globalMap.put("prevRowVal", row25.ESTOQUE);

					if (Relational.ISNULL((Integer) globalMap.get("prevRowVal")))
						globalMap.put("prevRowVal", row25.ESTOQUE);

//System.out.println((Integer)globalMap.get("prevRowVal"));

					row9.EstoqueAtualizado = (Integer) globalMap.get("prevRowVal") - row25.DB_PEDI_QTDE_SOLIC;

					globalMap.put("prevRowVal", row9.EstoqueAtualizado);

					if (row9.EstoqueAtualizado < 0)
						row9.FlagSemEstoque = 1;
					else
						row9.FlagSemEstoque = 0;

					globalMap.put("prodAnt", row25.DB_PEDI_PRODUTO);

//System.out.println(row9.EstoqueAtualizado);
//System.out.println(row9.DB_PEDI_PRODUTO);
//System.out.println(row9.DB_PEDI_QTDE_SOLIC);
//System.out.println(row9.ESTOQUE);
					nb_line_tJavaRow_1++;

					tos_count_tJavaRow_1++;

					/**
					 * [tJavaRow_1 main ] stop
					 */

					/**
					 * [tJavaRow_1 process_data_begin ] start
					 */

					currentComponent = "tJavaRow_1";

					cLabel = "Verifica\u00E7\u00E3o de estoque";

					/**
					 * [tJavaRow_1 process_data_begin ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGOUT main ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGOUT";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row9", "tJavaRow_1", "Verifica\u00E7\u00E3o de estoque", "tJavaRow",
							"tAggregateRow_1_AGGOUT", "tAggregateRow_1_AGGOUT", "tAggregateOut"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
					}

					operation_finder_tAggregateRow_1.DB_PED_NRO = row9.DB_PED_NRO;

					operation_finder_tAggregateRow_1.hashCodeDirty = true;

					operation_result_tAggregateRow_1 = hash_tAggregateRow_1.get(operation_finder_tAggregateRow_1);

					if (operation_result_tAggregateRow_1 == null) { // G_OutMain_AggR_001

						operation_result_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();

						operation_result_tAggregateRow_1.DB_PED_NRO = operation_finder_tAggregateRow_1.DB_PED_NRO;

						hash_tAggregateRow_1.put(operation_result_tAggregateRow_1, operation_result_tAggregateRow_1);

					} // G_OutMain_AggR_001

					if (operation_result_tAggregateRow_1.FlagSemEstoque_sum == null) {
						operation_result_tAggregateRow_1.FlagSemEstoque_sum = (int) 0;
					}

					if (row9.FlagSemEstoque != null)
						operation_result_tAggregateRow_1.FlagSemEstoque_sum += row9.FlagSemEstoque;

					tos_count_tAggregateRow_1_AGGOUT++;

					/**
					 * [tAggregateRow_1_AGGOUT main ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGOUT process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGOUT";

					/**
					 * [tAggregateRow_1_AGGOUT process_data_begin ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGOUT process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGOUT";

					/**
					 * [tAggregateRow_1_AGGOUT process_data_end ] stop
					 */

					/**
					 * [tJavaRow_1 process_data_end ] start
					 */

					currentComponent = "tJavaRow_1";

					cLabel = "Verifica\u00E7\u00E3o de estoque";

					/**
					 * [tJavaRow_1 process_data_end ] stop
					 */

					/**
					 * [tSortRow_1_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

					/**
					 * [tSortRow_1_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_1_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortIn";

				}

				globalMap.put("tSortRow_1_SortIn_NB_LINE", nb_line_tSortRow_1_SortIn);

				if (log.isDebugEnabled())
					log.debug("tSortRow_1_SortIn - " + ("Done."));

				ok_Hash.put("tSortRow_1_SortIn", true);
				end_Hash.put("tSortRow_1_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_1_SortIn end ] stop
				 */

				/**
				 * [tJavaRow_1 end ] start
				 */

				currentComponent = "tJavaRow_1";

				cLabel = "Verifica\u00E7\u00E3o de estoque";

				globalMap.put("tJavaRow_1_NB_LINE", nb_line_tJavaRow_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row25", 2, 0,
						"tSortRow_1_SortIn", "Ordena\u00E7\u00E3o de pedido_SortIn", "tSortIn", "tJavaRow_1",
						"Verifica\u00E7\u00E3o de estoque", "tJavaRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tJavaRow_1", true);
				end_Hash.put("tJavaRow_1", System.currentTimeMillis());

				/**
				 * [tJavaRow_1 end ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						"tJavaRow_1", "Verifica\u00E7\u00E3o de estoque", "tJavaRow", "tAggregateRow_1_AGGOUT",
						"tAggregateRow_1_AGGOUT", "tAggregateOut", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGOUT - " + ("Done."));

				ok_Hash.put("tAggregateRow_1_AGGOUT", true);
				end_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGOUT end ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("FIELDSEPARATOR" + " = " + "\"|\"");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_HEADER" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
						}
					}
					new BytesLimit65535_tLogRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder sbHeader_tLogRow_1 = new StringBuilder();

				sbHeader_tLogRow_1.append("DB_PED_NRO");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("DB_PED_OPERACAO");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("DB_PEDI_PRODUTO");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("DB_PEDI_QTDE_SOLIC");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("ESTOQUE");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("EstoqueAtualizado");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("FlagSemEstoque");

				sbHeader_tLogRow_1.append("\t");

				sbHeader_tLogRow_1.append("errorMessage");

				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}
				log.info("tLogRow_1 - Header names: " + sbHeader_tLogRow_1.toString());
				consoleOut_tLogRow_1.println(sbHeader_tLogRow_1.toString());
				consoleOut_tLogRow_1.flush();

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_2 begin ] start
				 */

				ok_Hash.put("tLogRow_2", false);
				start_Hash.put("tLogRow_2", System.currentTimeMillis());

				currentComponent = "tLogRow_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

				int tos_count_tLogRow_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_2 = new StringBuilder();
							log4jParamters_tLogRow_2.append("Parameters:");
							log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("FIELDSEPARATOR" + " = " + "\"|\"");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_HEADER" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("USE_FIXED_LENGTH" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_2 - " + (log4jParamters_tLogRow_2));
						}
					}
					new BytesLimit65535_tLogRow_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_2", "tLogRow_2", "tLogRow");
					talendJobLogProcess(globalMap);
				}

				///////////////////////

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_2 = "|";
				java.io.PrintStream consoleOut_tLogRow_2 = null;

				StringBuilder sbHeader_tLogRow_2 = new StringBuilder();

				sbHeader_tLogRow_2.append("DB_PED_NRO");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("DB_PED_OPERACAO");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("DB_PEDI_PRODUTO");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("DB_PEDI_QTDE_SOLIC");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("ESTOQUE");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("EstoqueAtualizado");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("FlagSemEstoque");

				sbHeader_tLogRow_2.append("\t");

				sbHeader_tLogRow_2.append("errorMessage");

				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
				}
				log.info("tLogRow_2 - Header names: " + sbHeader_tLogRow_2.toString());
				consoleOut_tLogRow_2.println(sbHeader_tLogRow_2.toString());
				consoleOut_tLogRow_2.flush();

				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tFilterRow_12 begin ] start
				 */

				ok_Hash.put("tFilterRow_12", false);
				start_Hash.put("tFilterRow_12", System.currentTimeMillis());

				currentComponent = "tFilterRow_12";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

				int tos_count_tFilterRow_12 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_12 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_12 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_12 = new StringBuilder();
							log4jParamters_tFilterRow_12.append("Parameters:");
							log4jParamters_tFilterRow_12.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_12.append(" | ");
							log4jParamters_tFilterRow_12
									.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==") + ", RVALUE=" + ("0")
											+ ", INPUT_COLUMN=" + ("FlagSemEstoque") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_12.append(" | ");
							log4jParamters_tFilterRow_12.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_12.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_12 - " + (log4jParamters_tFilterRow_12));
						}
					}
					new BytesLimit65535_tFilterRow_12().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_12", "tFilterRow_12", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_12 = 0;
				int nb_line_ok_tFilterRow_12 = 0;
				int nb_line_reject_tFilterRow_12 = 0;

				class Operator_tFilterRow_12 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_12(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_12 begin ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN begin ] start
				 */

				ok_Hash.put("tAggregateRow_1_AGGIN", false);
				start_Hash.put("tAggregateRow_1_AGGIN", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGIN";

				int tos_count_tAggregateRow_1_AGGIN = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGIN - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_1_AGGIN {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_1_AGGIN = new StringBuilder();
							log4jParamters_tAggregateRow_1_AGGIN.append("Parameters:");
							log4jParamters_tAggregateRow_1_AGGIN.append("ORIGIN" + " = " + "tAggregateRow_1");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("DB_PED_NRO") + ", INPUT_COLUMN=" + ("DB_PED_NRO") + "}]");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("FlagSemEstoque") + ", INPUT_COLUMN=" + ("FlagSemEstoque") + ", IGNORE_NULL="
									+ ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_1_AGGIN - " + (log4jParamters_tAggregateRow_1_AGGIN));
						}
					}
					new BytesLimit65535_tAggregateRow_1_AGGIN().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn");
					talendJobLogProcess(globalMap);
				}

				java.util.Collection<AggOperationStruct_tAggregateRow_1> values_tAggregateRow_1 = hash_tAggregateRow_1
						.values();

				globalMap.put("tAggregateRow_1_NB_LINE", values_tAggregateRow_1.size());

				if (log.isInfoEnabled())
					log.info("tAggregateRow_1_AGGIN - " + ("Retrieving the aggregation results."));
				for (AggOperationStruct_tAggregateRow_1 aggregated_row_tAggregateRow_1 : values_tAggregateRow_1) { // G_AggR_600

					/**
					 * [tAggregateRow_1_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					row5.DB_PED_NRO = aggregated_row_tAggregateRow_1.DB_PED_NRO;
					row5.FlagSemEstoque = aggregated_row_tAggregateRow_1.FlagSemEstoque_sum;

					if (log.isDebugEnabled())
						log.debug("tAggregateRow_1_AGGIN - "
								+ ("Operation function: 'sum' on the column 'FlagSemEstoque'."));

					tos_count_tAggregateRow_1_AGGIN++;

					/**
					 * [tAggregateRow_1_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tFilterRow_12 main ] start
					 */

					currentComponent = "tFilterRow_12";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row5", "tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tFilterRow_12",
							"tFilterRow_12", "tFilterRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
					}

					row7 = null;
					row6 = null;
					Operator_tFilterRow_12 ope_tFilterRow_12 = new Operator_tFilterRow_12("&&");
					ope_tFilterRow_12.matches(
							(row5.FlagSemEstoque == null ? false
									: row5.FlagSemEstoque
											.compareTo(ParserUtils.parseTo_Integer(String.valueOf(0))) == 0),
							"FlagSemEstoque.compareTo(0) == 0 failed");

					if (ope_tFilterRow_12.getMatchFlag()) {
						if (row6 == null) {
							row6 = new row6Struct();
						}
						row6.DB_PED_NRO = row5.DB_PED_NRO;
						row6.DB_PED_OPERACAO = row5.DB_PED_OPERACAO;
						row6.DB_PEDI_PRODUTO = row5.DB_PEDI_PRODUTO;
						row6.DB_PEDI_QTDE_SOLIC = row5.DB_PEDI_QTDE_SOLIC;
						row6.ESTOQUE = row5.ESTOQUE;
						row6.EstoqueAtualizado = row5.EstoqueAtualizado;
						row6.FlagSemEstoque = row5.FlagSemEstoque;
						row6.errorMessage = row5.errorMessage;
						log.debug("tFilterRow_12 - Process the record " + (nb_line_tFilterRow_12 + 1) + ".");

						nb_line_ok_tFilterRow_12++;
					} else {
						if (row7 == null) {
							row7 = new row7Struct();
						}
						row7.DB_PED_NRO = row5.DB_PED_NRO;
						row7.DB_PED_OPERACAO = row5.DB_PED_OPERACAO;
						row7.DB_PEDI_PRODUTO = row5.DB_PEDI_PRODUTO;
						row7.DB_PEDI_QTDE_SOLIC = row5.DB_PEDI_QTDE_SOLIC;
						row7.ESTOQUE = row5.ESTOQUE;
						row7.EstoqueAtualizado = row5.EstoqueAtualizado;
						row7.FlagSemEstoque = row5.FlagSemEstoque;
						row7.errorMessage = ope_tFilterRow_12.getErrorMsg();
						log.debug("tFilterRow_12 - Process the record " + (nb_line_tFilterRow_12 + 1) + ".");

						log.debug("tFilterRow_12 - Reject the record " + (nb_line_tFilterRow_12 + 1) + ". Caused by: "
								+ row7.errorMessage + ".");

						nb_line_reject_tFilterRow_12++;
					}

					nb_line_tFilterRow_12++;

					tos_count_tFilterRow_12++;

					/**
					 * [tFilterRow_12 main ] stop
					 */

					/**
					 * [tFilterRow_12 process_data_begin ] start
					 */

					currentComponent = "tFilterRow_12";

					/**
					 * [tFilterRow_12 process_data_begin ] stop
					 */
// Start of branch "row6"
					if (row6 != null) {

						/**
						 * [tLogRow_1 main ] start
						 */

						currentComponent = "tLogRow_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row6", "tFilterRow_12", "tFilterRow_12", "tFilterRow", "tLogRow_1", "tLogRow_1",
								"tLogRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
						}

///////////////////////		

						strBuffer_tLogRow_1 = new StringBuilder();

						if (row6.DB_PED_NRO != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.DB_PED_NRO));

						} //

						strBuffer_tLogRow_1.append("|");

						if (row6.DB_PED_OPERACAO != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.DB_PED_OPERACAO));

						} //

						strBuffer_tLogRow_1.append("|");

						if (row6.DB_PEDI_PRODUTO != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.DB_PEDI_PRODUTO));

						} //

						strBuffer_tLogRow_1.append("|");

						strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row6.DB_PEDI_QTDE_SOLIC));

						strBuffer_tLogRow_1.append("|");

						strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row6.ESTOQUE));

						strBuffer_tLogRow_1.append("|");

						if (row6.EstoqueAtualizado != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.EstoqueAtualizado));

						} //

						strBuffer_tLogRow_1.append("|");

						if (row6.FlagSemEstoque != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.FlagSemEstoque));

						} //

						strBuffer_tLogRow_1.append("|");

						if (row6.errorMessage != null) { //

							strBuffer_tLogRow_1.append(String.valueOf(row6.errorMessage));

						} //

						if (globalMap.get("tLogRow_CONSOLE") != null) {
							consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
						} else {
							consoleOut_tLogRow_1 = new java.io.PrintStream(
									new java.io.BufferedOutputStream(System.out));
							globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
						}
						log.info("tLogRow_1 - Content of row " + (nb_line_tLogRow_1 + 1) + ": "
								+ strBuffer_tLogRow_1.toString());
						consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
						consoleOut_tLogRow_1.flush();
						nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

						tos_count_tLogRow_1++;

						/**
						 * [tLogRow_1 main ] stop
						 */

						/**
						 * [tLogRow_1 process_data_begin ] start
						 */

						currentComponent = "tLogRow_1";

						/**
						 * [tLogRow_1 process_data_begin ] stop
						 */

						/**
						 * [tLogRow_1 process_data_end ] start
						 */

						currentComponent = "tLogRow_1";

						/**
						 * [tLogRow_1 process_data_end ] stop
						 */

					} // End of branch "row6"

// Start of branch "row7"
					if (row7 != null) {

						/**
						 * [tLogRow_2 main ] start
						 */

						currentComponent = "tLogRow_2";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row7", "tFilterRow_12", "tFilterRow_12", "tFilterRow", "tLogRow_2", "tLogRow_2",
								"tLogRow"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
						}

///////////////////////		

						strBuffer_tLogRow_2 = new StringBuilder();

						if (row7.DB_PED_NRO != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.DB_PED_NRO));

						} //

						strBuffer_tLogRow_2.append("|");

						if (row7.DB_PED_OPERACAO != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.DB_PED_OPERACAO));

						} //

						strBuffer_tLogRow_2.append("|");

						if (row7.DB_PEDI_PRODUTO != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.DB_PEDI_PRODUTO));

						} //

						strBuffer_tLogRow_2.append("|");

						strBuffer_tLogRow_2.append(FormatterUtils.formatUnwithE(row7.DB_PEDI_QTDE_SOLIC));

						strBuffer_tLogRow_2.append("|");

						strBuffer_tLogRow_2.append(FormatterUtils.formatUnwithE(row7.ESTOQUE));

						strBuffer_tLogRow_2.append("|");

						if (row7.EstoqueAtualizado != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.EstoqueAtualizado));

						} //

						strBuffer_tLogRow_2.append("|");

						if (row7.FlagSemEstoque != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.FlagSemEstoque));

						} //

						strBuffer_tLogRow_2.append("|");

						if (row7.errorMessage != null) { //

							strBuffer_tLogRow_2.append(String.valueOf(row7.errorMessage));

						} //

						if (globalMap.get("tLogRow_CONSOLE") != null) {
							consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
						} else {
							consoleOut_tLogRow_2 = new java.io.PrintStream(
									new java.io.BufferedOutputStream(System.out));
							globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
						}
						log.info("tLogRow_2 - Content of row " + (nb_line_tLogRow_2 + 1) + ": "
								+ strBuffer_tLogRow_2.toString());
						consoleOut_tLogRow_2.println(strBuffer_tLogRow_2.toString());
						consoleOut_tLogRow_2.flush();
						nb_line_tLogRow_2++;
//////

//////                    

///////////////////////    			

						tos_count_tLogRow_2++;

						/**
						 * [tLogRow_2 main ] stop
						 */

						/**
						 * [tLogRow_2 process_data_begin ] start
						 */

						currentComponent = "tLogRow_2";

						/**
						 * [tLogRow_2 process_data_begin ] stop
						 */

						/**
						 * [tLogRow_2 process_data_end ] start
						 */

						currentComponent = "tLogRow_2";

						/**
						 * [tLogRow_2 process_data_end ] stop
						 */

					} // End of branch "row7"

					/**
					 * [tFilterRow_12 process_data_end ] start
					 */

					currentComponent = "tFilterRow_12";

					/**
					 * [tFilterRow_12 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					currentComponent = "tAggregateRow_1_AGGIN";

				} // G_AggR_600

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGIN - " + ("Done."));

				ok_Hash.put("tAggregateRow_1_AGGIN", true);
				end_Hash.put("tAggregateRow_1_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGIN end ] stop
				 */

				/**
				 * [tFilterRow_12 end ] start
				 */

				currentComponent = "tFilterRow_12";

				globalMap.put("tFilterRow_12_NB_LINE", nb_line_tFilterRow_12);
				globalMap.put("tFilterRow_12_NB_LINE_OK", nb_line_ok_tFilterRow_12);
				globalMap.put("tFilterRow_12_NB_LINE_REJECT", nb_line_reject_tFilterRow_12);

				log.info("tFilterRow_12 - Processed records count:" + nb_line_tFilterRow_12 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_12 + ". Rejected records count:" + nb_line_reject_tFilterRow_12 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
						"tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tFilterRow_12",
						"tFilterRow_12", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_12 - " + ("Done."));

				ok_Hash.put("tFilterRow_12", true);
				end_Hash.put("tFilterRow_12", System.currentTimeMillis());

				/**
				 * [tFilterRow_12 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
						"tFilterRow_12", "tFilterRow_12", "tFilterRow", "tLogRow_1", "tLogRow_1", "tLogRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);
				if (log.isInfoEnabled())
					log.info("tLogRow_2 - " + ("Printed row count: ") + (nb_line_tLogRow_2) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
						"tFilterRow_12", "tFilterRow_12", "tFilterRow", "tLogRow_2", "tLogRow_2", "tLogRow",
						"reject")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Done."));

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
				 */

				/**
				 * [tFileOutputExcel_2 end ] start
				 */

				currentComponent = "tFileOutputExcel_2";

				xlsxTool_tFileOutputExcel_2.writeExcel(fileName_tFileOutputExcel_2, true);

				if (headerIsInserted_tFileOutputExcel_2 && nb_line_tFileOutputExcel_2 > 0) {
					nb_line_tFileOutputExcel_2 = nb_line_tFileOutputExcel_2 - 1;
				}
				globalMap.put("tFileOutputExcel_2_NB_LINE", nb_line_tFileOutputExcel_2);

				log.debug("tFileOutputExcel_2 - Written records count: " + nb_line_tFileOutputExcel_2 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_4",
						"tMap_4", "tMap", "tFileOutputExcel_2", "tFileOutputExcel_2", "tFileOutputExcel", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_2 - " + ("Done."));

				ok_Hash.put("tFileOutputExcel_2", true);
				end_Hash.put("tFileOutputExcel_2", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_1_AGGIN"
			globalMap.remove("tAggregateRow_1");

			// free memory for "tSortRow_1_SortIn"
			globalMap.remove("tSortRow_1");

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_row14");

			try {

				/**
				 * [tHashInput_3 finally ] start
				 */

				currentComponent = "tHashInput_3";

				/**
				 * [tHashInput_3 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tSortRow_1_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				/**
				 * [tSortRow_1_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_1_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortIn";

				/**
				 * [tSortRow_1_SortIn finally ] stop
				 */

				/**
				 * [tJavaRow_1 finally ] start
				 */

				currentComponent = "tJavaRow_1";

				cLabel = "Verifica\u00E7\u00E3o de estoque";

				/**
				 * [tJavaRow_1 finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				/**
				 * [tAggregateRow_1_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGIN";

				/**
				 * [tAggregateRow_1_AGGIN finally ] stop
				 */

				/**
				 * [tFilterRow_12 finally ] start
				 */

				currentComponent = "tFilterRow_12";

				/**
				 * [tFilterRow_12 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_2 finally ] start
				 */

				currentComponent = "tFileOutputExcel_2";

				/**
				 * [tFileOutputExcel_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tHashInput_3_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				ok_Hash.put("talendJobLog", false);
				start_Hash.put("talendJobLog", System.currentTimeMillis());

				currentComponent = "talendJobLog";

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				currentComponent = "talendJobLog";

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				currentComponent = "talendJobLog";

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final Integracao_Pedidos Integracao_PedidosClass = new Integracao_Pedidos();

		int exitCode = Integracao_PedidosClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Integracao_Pedidos' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20240619_0909-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'Integracao_Pedidos' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_i5bikEg2Ee-c4sBnjyqvig");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-08-20T16:23:17.848994Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}
			}

			// first load default key-value pairs from application.properties
			if (isStandaloneMS) {
				context.putAll(this.getDefaultProperties());
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Integracao_Pedidos.class.getClassLoader()
					.getResourceAsStream("hydronorth/integracao_pedidos_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Integracao_Pedidos.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
					if (isStandaloneMS) {
						// override context key-value pairs if provided using --context=contextName
						defaultProps.load(inContext);
						context.putAll(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}
			// override key-value pairs if provided via --config.location=file1.file2 OR
			// --config.additional-location=file1,file2
			if (isStandaloneMS) {
				context.putAll(this.getAdditionalProperties());
			}

			// override key-value pairs if provide via command line like
			// --key1=value1,--key2=value2
			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'Integracao_Pedidos' - Started.");
		java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBInput_8Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_8) {
			globalMap.put("tDBInput_8_SUBPROCESS_STATE", -1);

			e_tDBInput_8.printStackTrace();

		}
		try {
			errorCode = null;
			tHashInput_3Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tHashInput_3) {
			globalMap.put("tHashInput_3_SUBPROCESS_STATE", -1);

			e_tHashInput_3.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Integracao_Pedidos");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'Integracao_Pedidos' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 1104927 characters generated by Talend Cloud Data Integration on the 20 de
 * agosto de 2024 13:23:17 BRT
 ************************************************************************************************/