
package hydronorth.loop_0_1;

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
 * Job: Loop Purpose: <br>
 * Description: <br>
 * 
 * @author removed, thiagosanti
 * @version 8.0.1.20240619_0909-patch
 * @status
 */
public class Loop implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Loop.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Loop.class);

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
	private final String jobName = "Loop";
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
			"_BTk94ERzEe-BtO_VxZtYWw", "0.1");
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
					Loop.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Loop.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tJavaRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tHashInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tHashInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_1_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_1_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_1_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSortRow_2_SortOut_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tSortRow_2_SortIn_error(exception, errorComponent, globalMap);

	}

	public void tSortRow_2_SortIn_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tHashInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
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

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFileInputExcel_1");
		org.slf4j.MDC.put("_subJobPid", "BINyV8_" + subJobPidCounter.getAndIncrement());

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

				row1Struct row1 = new row1Struct();

				/**
				 * [tHashOutput_1 begin ] start
				 */

				ok_Hash.put("tHashOutput_1", false);
				start_Hash.put("tHashOutput_1", System.currentTimeMillis());

				currentComponent = "tHashOutput_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tHashOutput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_1", "tHashOutput_1", "tHashOutput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct> tHashFile_tHashOutput_1 = null;
				String hashKey_tHashOutput_1 = "tHashFile_Loop_" + pid + "_tHashOutput_1";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_1)) {
					if (mf_tHashOutput_1.getResourceMap().get(hashKey_tHashOutput_1) == null) {
						mf_tHashOutput_1.getResourceMap().put(hashKey_tHashOutput_1,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct>(
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
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputExcel_1 = new StringBuilder();
							log4jParamters_tFileInputExcel_1.append("Parameters:");
							log4jParamters_tFileInputExcel_1.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1
									.append("FILENAME" + " = " + "\"C:/Users/joao.santos/Downloads/Estoque.xlsx\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:BEsNeUVZNMJ/bp8CCh/SGwrvw3IWI2tH48uQHA==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ALL_SHEETS" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("SHEETLIST" + " = " + "[{USE_REGEX=" + ("false")
									+ ", SHEETNAME=" + ("\"Estoque\"") + "}]");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("AFFECT_EACH_SHEET" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FIRST_COLUMN" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LAST_COLUMN" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append(
									"TRIMSELECT" + " = " + "[{TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("ChaveProduto")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("EstoqueInicial") + "}]");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("CONVERTDATETOSTRING" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("STOPREAD_ON_EMPTYROW" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("GENERATION_MODE" + " = " + "USER_MODE");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("CONFIGURE_INFLATION_RATIO" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputExcel_1 - " + (log4jParamters_tFileInputExcel_1));
						}
					}
					new BytesLimit65535_tFileInputExcel_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel");
					talendJobLogProcess(globalMap);
				}

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:DXAF5dkUEsqtvbXhxJVpL0Hk8iJ2MI8Whv0dPg==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "C:/Users/joao.santos/Downloads/Estoque.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_1.addAll(
							regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Estoque", false));
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					int nb_line_tFileInputExcel_1 = 0;
					if (sheetList_tFileInputExcel_1.size() > 0) {

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();
						log.debug("tFileInputExcel_1 - Retrieving records from the datasource.");

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 2;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ChaveProduto";

									row1.ChaveProduto = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.ChaveProduto = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "EstoqueInicial";

									row1.EstoqueInicial = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.EstoqueInicial = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

								log.debug("tFileInputExcel_1 - Retrieving the record " + (nb_line_tFileInputExcel_1)
										+ ".");

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								log.error("tFileInputExcel_1 - " + e.getMessage());

								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tHashOutput_1 main ] start
								 */

								currentComponent = "tHashOutput_1";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row1", "tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel",
										"tHashOutput_1", "tHashOutput_1", "tHashOutput"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
								}

								row1Struct oneRow_tHashOutput_1 = new row1Struct();

								oneRow_tHashOutput_1.ChaveProduto = row1.ChaveProduto;
								oneRow_tHashOutput_1.EstoqueInicial = row1.EstoqueInicial;

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

								/**
								 * [tHashOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tHashOutput_1 process_data_end ] start
								 */

								currentComponent = "tHashOutput_1";

								/**
								 * [tHashOutput_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						log.debug("tFileInputExcel_1 - Retrieved records count: " + nb_line_tFileInputExcel_1 + " .");

					}

					globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);
				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Done."));

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tHashOutput_1 end ] start
				 */

				currentComponent = "tHashOutput_1";

				globalMap.put("tHashOutput_1_NB_LINE", nb_line_tHashOutput_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputExcel_1", "tFileInputExcel_1", "tFileInputExcel", "tHashOutput_1", "tHashOutput_1",
						"tHashOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_1", true);
				end_Hash.put("tHashOutput_1", System.currentTimeMillis());

				/**
				 * [tHashOutput_1 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputExcel_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tFileInputDelimited_1Process(globalMap);

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
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tHashOutput_1 finally ] start
				 */

				currentComponent = "tHashOutput_1";

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

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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
			return null;
		}

		public Integer EstoqueAtualizadoPrecision() {
			return null;
		}

		public String EstoqueAtualizadoDefault() {

			return null;

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

		public String FlagSemEstoque;

		public String getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return null;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

					this.EstoqueAtualizado = readInteger(dis);

					this.FlagSemEstoque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// String

				writeString(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

				// Integer

				writeInteger(this.EstoqueAtualizado, dos);

				// String

				writeString(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append(",EstoqueAtualizado=" + String.valueOf(EstoqueAtualizado));
			sb.append(",FlagSemEstoque=" + FlagSemEstoque);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
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
		public int compareTo(row6Struct other) {

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

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

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

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

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

	public static class OnRowsEndStructtSortRow_2
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_2> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_2 other) {

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

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.EstoqueInicial = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.EstoqueInicial, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

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

	public static class OriginalStruct implements routines.system.IPersistableRow<OriginalStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OriginalStruct other) {

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

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

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

	public static class OnRowsEndStructtSortRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtSortRow_1> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtSortRow_1 other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

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

	public static class OnRowsEndStructtAggregateRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_1> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_1 other) {

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

	public static class VendasMesStruct implements routines.system.IPersistableRow<VendasMesStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataEstoque = readInteger(dis);

					this.ChaveProduto = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(VendasMesStruct other) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public java.util.Date DataPedido;

		public java.util.Date getDataPedido() {
			return this.DataPedido;
		}

		public Boolean DataPedidoIsNullable() {
			return true;
		}

		public Boolean DataPedidoIsKey() {
			return false;
		}

		public Integer DataPedidoLength() {
			return 10;
		}

		public Integer DataPedidoPrecision() {
			return 0;
		}

		public String DataPedidoDefault() {

			return null;

		}

		public String DataPedidoComment() {

			return "";

		}

		public String DataPedidoPattern() {

			return "dd/MM/yyyy";

		}

		public String DataPedidoOriginalDbColumnName() {

			return "DataPedido";

		}

		public java.util.Date DataEstoque;

		public java.util.Date getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd/MM/yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public String NumeroPedido;

		public String getNumeroPedido() {
			return this.NumeroPedido;
		}

		public Boolean NumeroPedidoIsNullable() {
			return true;
		}

		public Boolean NumeroPedidoIsKey() {
			return false;
		}

		public Integer NumeroPedidoLength() {
			return 7;
		}

		public Integer NumeroPedidoPrecision() {
			return 0;
		}

		public String NumeroPedidoDefault() {

			return null;

		}

		public String NumeroPedidoComment() {

			return "";

		}

		public String NumeroPedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer ChaveCliente;

		public Integer getChaveCliente() {
			return this.ChaveCliente;
		}

		public Boolean ChaveClienteIsNullable() {
			return true;
		}

		public Boolean ChaveClienteIsKey() {
			return false;
		}

		public Integer ChaveClienteLength() {
			return 5;
		}

		public Integer ChaveClientePrecision() {
			return 0;
		}

		public String ChaveClienteDefault() {

			return null;

		}

		public String ChaveClienteComment() {

			return "";

		}

		public String ChaveClientePattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveClienteOriginalDbColumnName() {

			return "ChaveCliente";

		}

		public Integer ChaveTerritorio;

		public Integer getChaveTerritorio() {
			return this.ChaveTerritorio;
		}

		public Boolean ChaveTerritorioIsNullable() {
			return true;
		}

		public Boolean ChaveTerritorioIsKey() {
			return false;
		}

		public Integer ChaveTerritorioLength() {
			return 2;
		}

		public Integer ChaveTerritorioPrecision() {
			return 0;
		}

		public String ChaveTerritorioDefault() {

			return null;

		}

		public String ChaveTerritorioComment() {

			return "";

		}

		public String ChaveTerritorioPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveTerritorioOriginalDbColumnName() {

			return "ChaveTerritorio";

		}

		public Integer ItemLinhaPedido;

		public Integer getItemLinhaPedido() {
			return this.ItemLinhaPedido;
		}

		public Boolean ItemLinhaPedidoIsNullable() {
			return true;
		}

		public Boolean ItemLinhaPedidoIsKey() {
			return false;
		}

		public Integer ItemLinhaPedidoLength() {
			return 1;
		}

		public Integer ItemLinhaPedidoPrecision() {
			return 0;
		}

		public String ItemLinhaPedidoDefault() {

			return null;

		}

		public String ItemLinhaPedidoComment() {

			return "";

		}

		public String ItemLinhaPedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String ItemLinhaPedidoOriginalDbColumnName() {

			return "ItemLinhaPedido";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataPedido = readDate(dis);

					this.DataEstoque = readDate(dis);

					this.NumeroPedido = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.ChaveCliente = readInteger(dis);

					this.ChaveTerritorio = readInteger(dis);

					this.ItemLinhaPedido = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataPedido = readDate(dis);

					this.DataEstoque = readDate(dis);

					this.NumeroPedido = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.ChaveCliente = readInteger(dis);

					this.ChaveTerritorio = readInteger(dis);

					this.ItemLinhaPedido = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.DataPedido, dos);

				// java.util.Date

				writeDate(this.DataEstoque, dos);

				// String

				writeString(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.ChaveCliente, dos);

				// Integer

				writeInteger(this.ChaveTerritorio, dos);

				// Integer

				writeInteger(this.ItemLinhaPedido, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.DataPedido, dos);

				// java.util.Date

				writeDate(this.DataEstoque, dos);

				// String

				writeString(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.ChaveCliente, dos);

				// Integer

				writeInteger(this.ChaveTerritorio, dos);

				// Integer

				writeInteger(this.ItemLinhaPedido, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataPedido=" + String.valueOf(DataPedido));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",NumeroPedido=" + NumeroPedido);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",ChaveCliente=" + String.valueOf(ChaveCliente));
			sb.append(",ChaveTerritorio=" + String.valueOf(ChaveTerritorio));
			sb.append(",ItemLinhaPedido=" + String.valueOf(ItemLinhaPedido));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(DataPedido);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (ChaveCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveCliente);
			}

			sb.append("|");

			if (ChaveTerritorio == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveTerritorio);
			}

			sb.append("|");

			if (ItemLinhaPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemLinhaPedido);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

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

	public static class after_tFileInputDelimited_1Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public java.util.Date DataPedido;

		public java.util.Date getDataPedido() {
			return this.DataPedido;
		}

		public Boolean DataPedidoIsNullable() {
			return true;
		}

		public Boolean DataPedidoIsKey() {
			return false;
		}

		public Integer DataPedidoLength() {
			return 10;
		}

		public Integer DataPedidoPrecision() {
			return 0;
		}

		public String DataPedidoDefault() {

			return null;

		}

		public String DataPedidoComment() {

			return "";

		}

		public String DataPedidoPattern() {

			return "dd/MM/yyyy";

		}

		public String DataPedidoOriginalDbColumnName() {

			return "DataPedido";

		}

		public java.util.Date DataEstoque;

		public java.util.Date getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return 10;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd/MM/yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public String NumeroPedido;

		public String getNumeroPedido() {
			return this.NumeroPedido;
		}

		public Boolean NumeroPedidoIsNullable() {
			return true;
		}

		public Boolean NumeroPedidoIsKey() {
			return false;
		}

		public Integer NumeroPedidoLength() {
			return 7;
		}

		public Integer NumeroPedidoPrecision() {
			return 0;
		}

		public String NumeroPedidoDefault() {

			return null;

		}

		public String NumeroPedidoComment() {

			return "";

		}

		public String NumeroPedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return 3;
		}

		public Integer ChaveProdutoPrecision() {
			return 0;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer ChaveCliente;

		public Integer getChaveCliente() {
			return this.ChaveCliente;
		}

		public Boolean ChaveClienteIsNullable() {
			return true;
		}

		public Boolean ChaveClienteIsKey() {
			return false;
		}

		public Integer ChaveClienteLength() {
			return 5;
		}

		public Integer ChaveClientePrecision() {
			return 0;
		}

		public String ChaveClienteDefault() {

			return null;

		}

		public String ChaveClienteComment() {

			return "";

		}

		public String ChaveClientePattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveClienteOriginalDbColumnName() {

			return "ChaveCliente";

		}

		public Integer ChaveTerritorio;

		public Integer getChaveTerritorio() {
			return this.ChaveTerritorio;
		}

		public Boolean ChaveTerritorioIsNullable() {
			return true;
		}

		public Boolean ChaveTerritorioIsKey() {
			return false;
		}

		public Integer ChaveTerritorioLength() {
			return 2;
		}

		public Integer ChaveTerritorioPrecision() {
			return 0;
		}

		public String ChaveTerritorioDefault() {

			return null;

		}

		public String ChaveTerritorioComment() {

			return "";

		}

		public String ChaveTerritorioPattern() {

			return "dd-MM-yyyy";

		}

		public String ChaveTerritorioOriginalDbColumnName() {

			return "ChaveTerritorio";

		}

		public Integer ItemLinhaPedido;

		public Integer getItemLinhaPedido() {
			return this.ItemLinhaPedido;
		}

		public Boolean ItemLinhaPedidoIsNullable() {
			return true;
		}

		public Boolean ItemLinhaPedidoIsKey() {
			return false;
		}

		public Integer ItemLinhaPedidoLength() {
			return 1;
		}

		public Integer ItemLinhaPedidoPrecision() {
			return 0;
		}

		public String ItemLinhaPedidoDefault() {

			return null;

		}

		public String ItemLinhaPedidoComment() {

			return "";

		}

		public String ItemLinhaPedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String ItemLinhaPedidoOriginalDbColumnName() {

			return "ItemLinhaPedido";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return 1;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataPedido = readDate(dis);

					this.DataEstoque = readDate(dis);

					this.NumeroPedido = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.ChaveCliente = readInteger(dis);

					this.ChaveTerritorio = readInteger(dis);

					this.ItemLinhaPedido = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.DataPedido = readDate(dis);

					this.DataEstoque = readDate(dis);

					this.NumeroPedido = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.ChaveCliente = readInteger(dis);

					this.ChaveTerritorio = readInteger(dis);

					this.ItemLinhaPedido = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// java.util.Date

				writeDate(this.DataPedido, dos);

				// java.util.Date

				writeDate(this.DataEstoque, dos);

				// String

				writeString(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.ChaveCliente, dos);

				// Integer

				writeInteger(this.ChaveTerritorio, dos);

				// Integer

				writeInteger(this.ItemLinhaPedido, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// java.util.Date

				writeDate(this.DataPedido, dos);

				// java.util.Date

				writeDate(this.DataEstoque, dos);

				// String

				writeString(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.ChaveCliente, dos);

				// Integer

				writeInteger(this.ChaveTerritorio, dos);

				// Integer

				writeInteger(this.ItemLinhaPedido, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("DataPedido=" + String.valueOf(DataPedido));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",NumeroPedido=" + NumeroPedido);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",ChaveCliente=" + String.valueOf(ChaveCliente));
			sb.append(",ChaveTerritorio=" + String.valueOf(ChaveTerritorio));
			sb.append(",ItemLinhaPedido=" + String.valueOf(ItemLinhaPedido));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (DataPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(DataPedido);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (ChaveCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveCliente);
			}

			sb.append("|");

			if (ChaveTerritorio == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveTerritorio);
			}

			sb.append("|");

			if (ItemLinhaPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemLinhaPedido);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_1Struct other) {

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

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFileInputDelimited_1");
		org.slf4j.MDC.put("_subJobPid", "wZdq16_" + subJobPidCounter.getAndIncrement());

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

				tHashInput_1Process(globalMap);

				row2Struct row2 = new row2Struct();
				VendasMesStruct VendasMes = new VendasMesStruct();
				row3Struct row3 = new row3Struct();
				row5Struct row5 = new row5Struct();
				out1Struct out1 = new out1Struct();
				row8Struct row8 = new row8Struct();
				row8Struct row9 = row8;
				row6Struct row6 = new row6Struct();
				OriginalStruct Original = new OriginalStruct();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] start
				 */

				ok_Hash.put("tAggregateRow_1_AGGOUT", false);
				start_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "VendasMes");

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
									+ ("ChaveProduto") + ", INPUT_COLUMN=" + ("ChaveProduto") + "}, {OUTPUT_COLUMN="
									+ ("DataEstoque") + ", INPUT_COLUMN=" + ("DataEstoque") + "}]");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("QuantidadePedido") + ", INPUT_COLUMN=" + ("QuantidadePedido") + ", IGNORE_NULL="
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

					Integer DataEstoque;
					Integer ChaveProduto;
					Integer QuantidadePedido_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.DataEstoque == null) ? 0 : this.DataEstoque.hashCode());

							result = prime * result + ((this.ChaveProduto == null) ? 0 : this.ChaveProduto.hashCode());

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

						if (this.DataEstoque == null) {
							if (other.DataEstoque != null)
								return false;
						} else if (!this.DataEstoque.equals(other.DataEstoque))
							return false;

						if (this.ChaveProduto == null) {
							if (other.ChaveProduto != null)
								return false;
						} else if (!this.ChaveProduto.equals(other.ChaveProduto))
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
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

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
				int count_row2_tMap_1 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_VendasMes_tMap_1 = 0;

				VendasMesStruct VendasMes_tmp = new VendasMesStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				cLabel = "Vendas";

				int tos_count_tFileInputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_1 = new StringBuilder();
							log4jParamters_tFileInputDelimited_1.append("Parameters:");
							log4jParamters_tFileInputDelimited_1.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1
									.append("FILENAME" + " = " + "\"C:/Users/joao.santos/Downloads/Vendas.csv\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("DataPedido") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DataEstoque") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("NumeroPedido")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("ChaveProduto") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("ChaveCliente") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ChaveTerritorio") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ItemLinhaPedido") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("QuantidadePedido") + "}]");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"US-ASCII\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_1 - " + (log4jParamters_tFileInputDelimited_1));
						}
					}
					new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_1", "Vendas", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/Users/joao.santos/Downloads/Vendas.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/joao.santos/Downloads/Vendas.csv", "US-ASCII", ";", "\n", false, 1, 0,
								limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_1 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row2 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.DataPedido = ParserUtils.parseTo_Date(temp, "dd/MM/yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DataPedido", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.DataPedido = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.DataEstoque = ParserUtils.parseTo_Date(temp, "dd/MM/yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DataEstoque", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.DataEstoque = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row2.NumeroPedido = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.ChaveProduto = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ChaveProduto", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.ChaveProduto = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 4;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.ChaveCliente = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ChaveCliente", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.ChaveCliente = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 5;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.ChaveTerritorio = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ChaveTerritorio", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.ChaveTerritorio = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 6;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.ItemLinhaPedido = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"ItemLinhaPedido", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.ItemLinhaPedido = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row2.QuantidadePedido = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"QuantidadePedido", "row2", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row2.QuantidadePedido = null;

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							log.error("tFileInputDelimited_1 - " + e.getMessage());

							System.err.println(e.getMessage());
							row2 = null;

						}

						log.debug("tFileInputDelimited_1 - Retrieving the record "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						cLabel = "Vendas";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						cLabel = "Vendas";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row2", "tFileInputDelimited_1", "Vendas", "tFileInputDelimited", "tMap_1",
									"tMap_1", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;
							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								VendasMes = null;

// # Output table : 'VendasMes'
// # Filter conditions 
								if (

								row2.ChaveProduto == 480 || row2.ChaveProduto == 214

								) {
									count_VendasMes_tMap_1++;

									VendasMes_tmp.DataEstoque = Integer
											.valueOf(TalendDate.formatDate("yyyyMM", row2.DataEstoque));
									VendasMes_tmp.ChaveProduto = row2.ChaveProduto;
									VendasMes_tmp.QuantidadePedido = row2.QuantidadePedido;
									VendasMes = VendasMes_tmp;
									log.debug("tMap_1 - Outputting the record " + count_VendasMes_tMap_1
											+ " of the output table 'VendasMes'.");

								} // closing filter/reject
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
// Start of branch "VendasMes"
							if (VendasMes != null) {

								/**
								 * [tAggregateRow_1_AGGOUT main ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								currentComponent = "tAggregateRow_1_AGGOUT";

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "VendasMes", "tMap_1", "tMap_1", "tMap", "tAggregateRow_1_AGGOUT",
										"tAggregateRow_1_AGGOUT", "tAggregateOut"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("VendasMes - " + (VendasMes == null ? "" : VendasMes.toLogString()));
								}

								operation_finder_tAggregateRow_1.DataEstoque = VendasMes.DataEstoque;
								operation_finder_tAggregateRow_1.ChaveProduto = VendasMes.ChaveProduto;

								operation_finder_tAggregateRow_1.hashCodeDirty = true;

								operation_result_tAggregateRow_1 = hash_tAggregateRow_1
										.get(operation_finder_tAggregateRow_1);

								if (operation_result_tAggregateRow_1 == null) { // G_OutMain_AggR_001

									operation_result_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();

									operation_result_tAggregateRow_1.DataEstoque = operation_finder_tAggregateRow_1.DataEstoque;
									operation_result_tAggregateRow_1.ChaveProduto = operation_finder_tAggregateRow_1.ChaveProduto;

									hash_tAggregateRow_1.put(operation_result_tAggregateRow_1,
											operation_result_tAggregateRow_1);

								} // G_OutMain_AggR_001

								if (operation_result_tAggregateRow_1.QuantidadePedido_sum == null) {
									operation_result_tAggregateRow_1.QuantidadePedido_sum = (int) 0;
								}

								if (VendasMes.QuantidadePedido != null)
									operation_result_tAggregateRow_1.QuantidadePedido_sum += VendasMes.QuantidadePedido;

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

							} // End of branch "VendasMes"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						cLabel = "Vendas";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						cLabel = "Vendas";

					}
				} finally {
					if (!((Object) ("C:/Users/joao.santos/Downloads/Vendas.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

						log.info("tFileInputDelimited_1 - Retrieved records count: "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_1 - Written records count in the table 'VendasMes': " + count_VendasMes_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tFileInputDelimited_1", "Vendas", "tFileInputDelimited", "tMap_1", "tMap_1", "tMap",
						"output")) {
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
				 * [tAggregateRow_1_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				currentComponent = "tAggregateRow_1_AGGOUT";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "VendasMes", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tAggregateRow_1_AGGOUT", "tAggregateRow_1_AGGOUT", "tAggregateOut",
						"output")) {
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
				 * [tSortRow_1_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_1_SortOut", false);
				start_Hash.put("tSortRow_1_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

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
							log4jParamters_tSortRow_1_SortOut.append("CRITERIA" + " = " + "[{ORDER=" + ("asc")
									+ ", COLNAME=" + ("DataEstoque") + ", SORT=" + ("num") + "}]");
							log4jParamters_tSortRow_1_SortOut.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSortRow_1_SortOut - " + (log4jParamters_tSortRow_1_SortOut));
						}
					}
					new BytesLimit65535_tSortRow_1_SortOut().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSortRow_1_SortOut", "tSortRow_1_SortOut", "tSortOut");
					talendJobLogProcess(globalMap);
				}

				class Comparablerow3Struct extends row3Struct implements Comparable<Comparablerow3Struct> {

					public int compareTo(Comparablerow3Struct other) {

						if (this.DataEstoque == null && other.DataEstoque != null) {
							return -1;

						} else if (this.DataEstoque != null && other.DataEstoque == null) {
							return 1;

						} else if (this.DataEstoque != null && other.DataEstoque != null) {
							if (!this.DataEstoque.equals(other.DataEstoque)) {
								return this.DataEstoque.compareTo(other.DataEstoque);
							}
						}
						return 0;
					}
				}

				java.util.List<Comparablerow3Struct> list_tSortRow_1_SortOut = new java.util.ArrayList<Comparablerow3Struct>();

				/**
				 * [tSortRow_1_SortOut begin ] stop
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
									+ ("ChaveProduto") + ", INPUT_COLUMN=" + ("ChaveProduto") + "}, {OUTPUT_COLUMN="
									+ ("DataEstoque") + ", INPUT_COLUMN=" + ("DataEstoque") + "}]");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("QuantidadePedido") + ", INPUT_COLUMN=" + ("QuantidadePedido") + ", IGNORE_NULL="
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

					row3.DataEstoque = aggregated_row_tAggregateRow_1.DataEstoque;

					row3.ChaveProduto = aggregated_row_tAggregateRow_1.ChaveProduto;
					row3.QuantidadePedido = aggregated_row_tAggregateRow_1.QuantidadePedido_sum;

					if (log.isDebugEnabled())
						log.debug("tAggregateRow_1_AGGIN - "
								+ ("Operation function: 'sum' on the column 'QuantidadePedido'."));

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
					 * [tSortRow_1_SortOut main ] start
					 */

					currentVirtualComponent = "tSortRow_1";

					currentComponent = "tSortRow_1_SortOut";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row3", "tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn",
							"tSortRow_1_SortOut", "tSortRow_1_SortOut", "tSortOut"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
					}

					Comparablerow3Struct arrayRowtSortRow_1_SortOut = new Comparablerow3Struct();

					arrayRowtSortRow_1_SortOut.DataEstoque = row3.DataEstoque;
					arrayRowtSortRow_1_SortOut.ChaveProduto = row3.ChaveProduto;
					arrayRowtSortRow_1_SortOut.QuantidadePedido = row3.QuantidadePedido;
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
				 * [tSortRow_1_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_1";

				currentComponent = "tSortRow_1_SortOut";

				row3Struct[] array_tSortRow_1_SortOut = list_tSortRow_1_SortOut.toArray(new Comparablerow3Struct[0]);

				java.util.Arrays.sort(array_tSortRow_1_SortOut);

				globalMap.put("tSortRow_1", array_tSortRow_1_SortOut);

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tSortRow_1_SortOut",
						"tSortRow_1_SortOut", "tSortOut", "output")) {
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
				 * [tSortRow_2_SortOut begin ] start
				 */

				ok_Hash.put("tSortRow_2_SortOut", false);
				start_Hash.put("tSortRow_2_SortOut", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tSortRow_2_SortOut = 0;

				if (log.isDebugEnabled())
					log.debug("tSortRow_2_SortOut - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSortRow_2_SortOut {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSortRow_2_SortOut = new StringBuilder();
							log4jParamters_tSortRow_2_SortOut.append("Parameters:");
							log4jParamters_tSortRow_2_SortOut.append("DESTINATION" + " = " + "tSortRow_2");
							log4jParamters_tSortRow_2_SortOut.append(" | ");
							log4jParamters_tSortRow_2_SortOut.append("EXTERNAL" + " = " + "false");
							log4jParamters_tSortRow_2_SortOut.append(" | ");
							log4jParamters_tSortRow_2_SortOut.append("CRITERIA" + " = " + "[{ORDER=" + ("asc")
									+ ", COLNAME=" + ("ChaveProduto") + ", SORT=" + ("num") + "}, {ORDER=" + ("asc")
									+ ", COLNAME=" + ("DataEstoque") + ", SORT=" + ("num") + "}]");
							log4jParamters_tSortRow_2_SortOut.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSortRow_2_SortOut - " + (log4jParamters_tSortRow_2_SortOut));
						}
					}
					new BytesLimit65535_tSortRow_2_SortOut().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSortRow_2_SortOut", "tSortRow_2_SortOut", "tSortOut");
					talendJobLogProcess(globalMap);
				}

				class Comparableout1Struct extends out1Struct implements Comparable<Comparableout1Struct> {

					public int compareTo(Comparableout1Struct other) {

						if (this.ChaveProduto == null && other.ChaveProduto != null) {
							return -1;

						} else if (this.ChaveProduto != null && other.ChaveProduto == null) {
							return 1;

						} else if (this.ChaveProduto != null && other.ChaveProduto != null) {
							if (!this.ChaveProduto.equals(other.ChaveProduto)) {
								return this.ChaveProduto.compareTo(other.ChaveProduto);
							}
						}
						if (this.DataEstoque == null && other.DataEstoque != null) {
							return -1;

						} else if (this.DataEstoque != null && other.DataEstoque == null) {
							return 1;

						} else if (this.DataEstoque != null && other.DataEstoque != null) {
							if (!this.DataEstoque.equals(other.DataEstoque)) {
								return this.DataEstoque.compareTo(other.DataEstoque);
							}
						}
						return 0;
					}
				}

				java.util.List<Comparableout1Struct> list_tSortRow_2_SortOut = new java.util.ArrayList<Comparableout1Struct>();

				/**
				 * [tSortRow_2_SortOut begin ] stop
				 */

				/**
				 * [tHashOutput_2 begin ] start
				 */

				ok_Hash.put("tHashOutput_2", false);
				start_Hash.put("tHashOutput_2", System.currentTimeMillis());

				currentComponent = "tHashOutput_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Original");

				int tos_count_tHashOutput_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_2", "tHashOutput_2", "tHashOutput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_2 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<OriginalStruct> tHashFile_tHashOutput_2 = null;
				String hashKey_tHashOutput_2 = "tHashFile_Loop_" + pid + "_tHashOutput_2";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_2)) {
					if (mf_tHashOutput_2.getResourceMap().get(hashKey_tHashOutput_2) == null) {
						mf_tHashOutput_2.getResourceMap().put(hashKey_tHashOutput_2,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<OriginalStruct>(
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
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

				int tos_count_tMap_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_2 = new StringBuilder();
							log4jParamters_tMap_2.append("Parameters:");
							log4jParamters_tMap_2.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_2 - " + (log4jParamters_tMap_2));
						}
					}
					new BytesLimit65535_tMap_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_2", "tMap_2", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_row5_tMap_2 = 0;

				int count_row4_tMap_2 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_2 = 0;

				out1Struct out1_tmp = new out1Struct();
				int count_Original_tMap_2 = 0;

				OriginalStruct Original_tmp = new OriginalStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
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
					talendJobLog.addCM("tSortRow_1_SortIn", "tSortRow_1_SortIn", "tSortIn");
					talendJobLogProcess(globalMap);
				}

				row3Struct[] array_tSortRow_1_SortIn = (row3Struct[]) globalMap.remove("tSortRow_1");

				int nb_line_tSortRow_1_SortIn = 0;

				row3Struct current_tSortRow_1_SortIn = null;

				for (int i_tSortRow_1_SortIn = 0; i_tSortRow_1_SortIn < array_tSortRow_1_SortIn.length; i_tSortRow_1_SortIn++) {
					current_tSortRow_1_SortIn = array_tSortRow_1_SortIn[i_tSortRow_1_SortIn];
					row5.DataEstoque = current_tSortRow_1_SortIn.DataEstoque;
					row5.ChaveProduto = current_tSortRow_1_SortIn.ChaveProduto;
					row5.QuantidadePedido = current_tSortRow_1_SortIn.QuantidadePedido;
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
					 * [tMap_2 main ] start
					 */

					currentComponent = "tMap_2";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row5", "tSortRow_1_SortIn", "tSortRow_1_SortIn", "tSortIn", "tMap_2", "tMap_2", "tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

					row4Struct row4 = null;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_2 = false;
					boolean mainRowRejected_tMap_2 = false;

					///////////////////////////////////////////////
					// Starting Lookup Table "row4"
					///////////////////////////////////////////////

					boolean forceLooprow4 = false;

					row4Struct row4ObjectFromLookup = null;

					if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

						hasCasePrimitiveKeyWithNull_tMap_2 = false;

						row4HashKey.ChaveProduto = row5.ChaveProduto;

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

						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
							// ###############################
							// # Output tables

							out1 = null;
							Original = null;

// # Output table : 'out1'
							count_out1_tMap_2++;

							out1_tmp.ChaveProdutoData = row5.ChaveProduto + "|" + row5.DataEstoque;
							out1_tmp.ChaveProduto = row5.ChaveProduto;
							out1_tmp.EstoqueInicial = row4.EstoqueInicial;
							out1_tmp.DataEstoque = row5.DataEstoque;
							out1_tmp.QuantidadePedido = row5.QuantidadePedido;
							out1 = out1_tmp;
							log.debug("tMap_2 - Outputting the record " + count_out1_tMap_2
									+ " of the output table 'out1'.");

// # Output table : 'Original'
							count_Original_tMap_2++;

							Original_tmp.ChaveProdutoData = row5.ChaveProduto + "|" + row5.DataEstoque;
							Original_tmp.DataEstoque = row5.DataEstoque;
							Original_tmp.ChaveProduto = row5.ChaveProduto;
							Original_tmp.QuantidadePedido = row5.QuantidadePedido;
							Original = Original_tmp;
							log.debug("tMap_2 - Outputting the record " + count_Original_tMap_2
									+ " of the output table 'Original'.");

// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_2 = false;

						tos_count_tMap_2++;

						/**
						 * [tMap_2 main ] stop
						 */

						/**
						 * [tMap_2 process_data_begin ] start
						 */

						currentComponent = "tMap_2";

						/**
						 * [tMap_2 process_data_begin ] stop
						 */
// Start of branch "out1"
						if (out1 != null) {

							/**
							 * [tSortRow_2_SortOut main ] start
							 */

							currentVirtualComponent = "tSortRow_2";

							currentComponent = "tSortRow_2_SortOut";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "out1", "tMap_2", "tMap_2", "tMap", "tSortRow_2_SortOut", "tSortRow_2_SortOut",
									"tSortOut"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
							}

							Comparableout1Struct arrayRowtSortRow_2_SortOut = new Comparableout1Struct();

							arrayRowtSortRow_2_SortOut.ChaveProdutoData = out1.ChaveProdutoData;
							arrayRowtSortRow_2_SortOut.ChaveProduto = out1.ChaveProduto;
							arrayRowtSortRow_2_SortOut.EstoqueInicial = out1.EstoqueInicial;
							arrayRowtSortRow_2_SortOut.DataEstoque = out1.DataEstoque;
							arrayRowtSortRow_2_SortOut.QuantidadePedido = out1.QuantidadePedido;
							list_tSortRow_2_SortOut.add(arrayRowtSortRow_2_SortOut);

							tos_count_tSortRow_2_SortOut++;

							/**
							 * [tSortRow_2_SortOut main ] stop
							 */

							/**
							 * [tSortRow_2_SortOut process_data_begin ] start
							 */

							currentVirtualComponent = "tSortRow_2";

							currentComponent = "tSortRow_2_SortOut";

							/**
							 * [tSortRow_2_SortOut process_data_begin ] stop
							 */

							/**
							 * [tSortRow_2_SortOut process_data_end ] start
							 */

							currentVirtualComponent = "tSortRow_2";

							currentComponent = "tSortRow_2_SortOut";

							/**
							 * [tSortRow_2_SortOut process_data_end ] stop
							 */

						} // End of branch "out1"

// Start of branch "Original"
						if (Original != null) {

							/**
							 * [tHashOutput_2 main ] start
							 */

							currentComponent = "tHashOutput_2";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "Original", "tMap_2", "tMap_2", "tMap", "tHashOutput_2", "tHashOutput_2",
									"tHashOutput"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("Original - " + (Original == null ? "" : Original.toLogString()));
							}

							OriginalStruct oneRow_tHashOutput_2 = new OriginalStruct();

							oneRow_tHashOutput_2.ChaveProdutoData = Original.ChaveProdutoData;
							oneRow_tHashOutput_2.DataEstoque = Original.DataEstoque;
							oneRow_tHashOutput_2.ChaveProduto = Original.ChaveProduto;
							oneRow_tHashOutput_2.QuantidadePedido = Original.QuantidadePedido;

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

							/**
							 * [tHashOutput_2 process_data_begin ] stop
							 */

							/**
							 * [tHashOutput_2 process_data_end ] start
							 */

							currentComponent = "tHashOutput_2";

							/**
							 * [tHashOutput_2 process_data_end ] stop
							 */

						} // End of branch "Original"

					} // close loop of lookup 'row4' // G_TM_M_043

					/**
					 * [tMap_2 process_data_end ] start
					 */

					currentComponent = "tMap_2";

					/**
					 * [tMap_2 process_data_end ] stop
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
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

// ###############################      
				log.debug("tMap_2 - Written records count in the table 'out1': " + count_out1_tMap_2 + ".");
				log.debug("tMap_2 - Written records count in the table 'Original': " + count_Original_tMap_2 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
						"tSortRow_1_SortIn", "tSortRow_1_SortIn", "tSortIn", "tMap_2", "tMap_2", "tMap", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Done."));

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tSortRow_2_SortOut end ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				out1Struct[] array_tSortRow_2_SortOut = list_tSortRow_2_SortOut.toArray(new Comparableout1Struct[0]);

				java.util.Arrays.sort(array_tSortRow_2_SortOut);

				globalMap.put("tSortRow_2", array_tSortRow_2_SortOut);

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_2",
						"tMap_2", "tMap", "tSortRow_2_SortOut", "tSortRow_2_SortOut", "tSortOut", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tSortRow_2_SortOut - " + ("Done."));

				ok_Hash.put("tSortRow_2_SortOut", true);
				end_Hash.put("tSortRow_2_SortOut", System.currentTimeMillis());

				/**
				 * [tSortRow_2_SortOut end ] stop
				 */

				/**
				 * [tHashOutput_3 begin ] start
				 */

				ok_Hash.put("tHashOutput_3", false);
				start_Hash.put("tHashOutput_3", System.currentTimeMillis());

				currentComponent = "tHashOutput_3";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

				int tos_count_tHashOutput_3 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashOutput_3", "tHashOutput_3", "tHashOutput");
					talendJobLogProcess(globalMap);
				}

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashOutput_3 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row6Struct> tHashFile_tHashOutput_3 = null;
				String hashKey_tHashOutput_3 = "tHashFile_Loop_" + pid + "_tHashOutput_3";
				synchronized (org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.get(hashKey_tHashOutput_3)) {
					if (mf_tHashOutput_3.getResourceMap().get(hashKey_tHashOutput_3) == null) {
						mf_tHashOutput_3.getResourceMap().put(hashKey_tHashOutput_3,
								new org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row6Struct>(
										org.talend.designer.components.hashfile.common.MATCHING_MODE.KEEP_ALL));
						tHashFile_tHashOutput_3 = mf_tHashOutput_3.getResourceMap().get(hashKey_tHashOutput_3);
					} else {
						tHashFile_tHashOutput_3 = mf_tHashOutput_3.getResourceMap().get(hashKey_tHashOutput_3);
					}
				}
				int nb_line_tHashOutput_3 = 0;

				/**
				 * [tHashOutput_3 begin ] stop
				 */

				/**
				 * [tJavaRow_1 begin ] start
				 */

				ok_Hash.put("tJavaRow_1", false);
				start_Hash.put("tJavaRow_1", System.currentTimeMillis());

				currentComponent = "tJavaRow_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

				int tos_count_tJavaRow_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tJavaRow_1", "tJavaRow_1", "tJavaRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tJavaRow_1 = 0;

				/**
				 * [tJavaRow_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
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

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[5];

					public void addRow(String[] row) {

						for (int i = 0; i < 5; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 4 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 4 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[4] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "ChaveProdutoData", "ChaveProduto", "EstoqueInicial",
						"DataEstoque", "QuantidadePedido", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tSortRow_2_SortIn begin ] start
				 */

				ok_Hash.put("tSortRow_2_SortIn", false);
				start_Hash.put("tSortRow_2_SortIn", System.currentTimeMillis());

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortIn";

				int tos_count_tSortRow_2_SortIn = 0;

				if (log.isDebugEnabled())
					log.debug("tSortRow_2_SortIn - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSortRow_2_SortIn {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSortRow_2_SortIn = new StringBuilder();
							log4jParamters_tSortRow_2_SortIn.append("Parameters:");
							log4jParamters_tSortRow_2_SortIn.append("ORIGIN" + " = " + "tSortRow_2");
							log4jParamters_tSortRow_2_SortIn.append(" | ");
							log4jParamters_tSortRow_2_SortIn.append("EXTERNAL" + " = " + "false");
							log4jParamters_tSortRow_2_SortIn.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSortRow_2_SortIn - " + (log4jParamters_tSortRow_2_SortIn));
						}
					}
					new BytesLimit65535_tSortRow_2_SortIn().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSortRow_2_SortIn", "tSortRow_2_SortIn", "tSortIn");
					talendJobLogProcess(globalMap);
				}

				out1Struct[] array_tSortRow_2_SortIn = (out1Struct[]) globalMap.remove("tSortRow_2");

				int nb_line_tSortRow_2_SortIn = 0;

				out1Struct current_tSortRow_2_SortIn = null;

				for (int i_tSortRow_2_SortIn = 0; i_tSortRow_2_SortIn < array_tSortRow_2_SortIn.length; i_tSortRow_2_SortIn++) {
					current_tSortRow_2_SortIn = array_tSortRow_2_SortIn[i_tSortRow_2_SortIn];
					row8.ChaveProdutoData = current_tSortRow_2_SortIn.ChaveProdutoData;
					row8.ChaveProduto = current_tSortRow_2_SortIn.ChaveProduto;
					row8.EstoqueInicial = current_tSortRow_2_SortIn.EstoqueInicial;
					row8.DataEstoque = current_tSortRow_2_SortIn.DataEstoque;
					row8.QuantidadePedido = current_tSortRow_2_SortIn.QuantidadePedido;
					// increase number of line sorted
					nb_line_tSortRow_2_SortIn++;

					/**
					 * [tSortRow_2_SortIn begin ] stop
					 */

					/**
					 * [tSortRow_2_SortIn main ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					tos_count_tSortRow_2_SortIn++;

					/**
					 * [tSortRow_2_SortIn main ] stop
					 */

					/**
					 * [tSortRow_2_SortIn process_data_begin ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					/**
					 * [tSortRow_2_SortIn process_data_begin ] stop
					 */

					/**
					 * [tLogRow_1 main ] start
					 */

					currentComponent = "tLogRow_1";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row8", "tSortRow_2_SortIn", "tSortRow_2_SortIn", "tSortIn", "tLogRow_1", "tLogRow_1",
							"tLogRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
					}

///////////////////////		

					String[] row_tLogRow_1 = new String[5];

					if (row8.ChaveProdutoData != null) { //
						row_tLogRow_1[0] = String.valueOf(row8.ChaveProdutoData);

					} //

					if (row8.ChaveProduto != null) { //
						row_tLogRow_1[1] = String.valueOf(row8.ChaveProduto);

					} //

					if (row8.EstoqueInicial != null) { //
						row_tLogRow_1[2] = String.valueOf(row8.EstoqueInicial);

					} //

					if (row8.DataEstoque != null) { //
						row_tLogRow_1[3] = String.valueOf(row8.DataEstoque);

					} //

					if (row8.QuantidadePedido != null) { //
						row_tLogRow_1[4] = String.valueOf(row8.QuantidadePedido);

					} //

					util_tLogRow_1.addRow(row_tLogRow_1);
					nb_line_tLogRow_1++;
					log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
							+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

					row9 = row8;

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
					 * [tJavaRow_1 main ] start
					 */

					currentComponent = "tJavaRow_1";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row9", "tLogRow_1", "tLogRow_1", "tLogRow", "tJavaRow_1", "tJavaRow_1", "tJavaRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
					}

					row6.ChaveProdutoData = row9.ChaveProdutoData;
					row6.ChaveProduto = row9.ChaveProduto;
					row6.DataEstoque = row9.DataEstoque;
					row6.QuantidadePedido = row9.QuantidadePedido;

					if (globalMap.get("prodAnt") != row9.ChaveProduto)
						globalMap.put("prevRowVal", row9.EstoqueInicial);

					if (Relational.ISNULL((Integer) globalMap.get("prevRowVal")))
						globalMap.put("prevRowVal", row9.EstoqueInicial);

					row6.EstoqueAtualizado = (Integer) globalMap.get("prevRowVal") - row9.QuantidadePedido;

					globalMap.put("prevRowVal", row6.EstoqueAtualizado);

					if (row6.EstoqueAtualizado < 0)
						row6.FlagSemEstoque = "S";
					else
						row6.FlagSemEstoque = "N";

					globalMap.put("prodAnt", row9.ChaveProduto);
//System.out.println(row6.EstoqueAtualizado);

					nb_line_tJavaRow_1++;

					tos_count_tJavaRow_1++;

					/**
					 * [tJavaRow_1 main ] stop
					 */

					/**
					 * [tJavaRow_1 process_data_begin ] start
					 */

					currentComponent = "tJavaRow_1";

					/**
					 * [tJavaRow_1 process_data_begin ] stop
					 */

					/**
					 * [tHashOutput_3 main ] start
					 */

					currentComponent = "tHashOutput_3";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row6", "tJavaRow_1", "tJavaRow_1", "tJavaRow", "tHashOutput_3", "tHashOutput_3",
							"tHashOutput"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
					}

					row6Struct oneRow_tHashOutput_3 = new row6Struct();

					oneRow_tHashOutput_3.ChaveProdutoData = row6.ChaveProdutoData;
					oneRow_tHashOutput_3.ChaveProduto = row6.ChaveProduto;
					oneRow_tHashOutput_3.DataEstoque = row6.DataEstoque;
					oneRow_tHashOutput_3.QuantidadePedido = row6.QuantidadePedido;
					oneRow_tHashOutput_3.EstoqueAtualizado = row6.EstoqueAtualizado;
					oneRow_tHashOutput_3.FlagSemEstoque = row6.FlagSemEstoque;

					tHashFile_tHashOutput_3.put(oneRow_tHashOutput_3);
					nb_line_tHashOutput_3++;

					tos_count_tHashOutput_3++;

					/**
					 * [tHashOutput_3 main ] stop
					 */

					/**
					 * [tHashOutput_3 process_data_begin ] start
					 */

					currentComponent = "tHashOutput_3";

					/**
					 * [tHashOutput_3 process_data_begin ] stop
					 */

					/**
					 * [tHashOutput_3 process_data_end ] start
					 */

					currentComponent = "tHashOutput_3";

					/**
					 * [tHashOutput_3 process_data_end ] stop
					 */

					/**
					 * [tJavaRow_1 process_data_end ] start
					 */

					currentComponent = "tJavaRow_1";

					/**
					 * [tJavaRow_1 process_data_end ] stop
					 */

					/**
					 * [tLogRow_1 process_data_end ] start
					 */

					currentComponent = "tLogRow_1";

					/**
					 * [tLogRow_1 process_data_end ] stop
					 */

					/**
					 * [tSortRow_2_SortIn process_data_end ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

					/**
					 * [tSortRow_2_SortIn process_data_end ] stop
					 */

					/**
					 * [tSortRow_2_SortIn end ] start
					 */

					currentVirtualComponent = "tSortRow_2";

					currentComponent = "tSortRow_2_SortIn";

				}

				globalMap.put("tSortRow_2_SortIn_NB_LINE", nb_line_tSortRow_2_SortIn);

				if (log.isDebugEnabled())
					log.debug("tSortRow_2_SortIn - " + ("Done."));

				ok_Hash.put("tSortRow_2_SortIn", true);
				end_Hash.put("tSortRow_2_SortIn", System.currentTimeMillis());

				/**
				 * [tSortRow_2_SortIn end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tSortRow_2_SortIn", "tSortRow_2_SortIn", "tSortIn", "tLogRow_1", "tLogRow_1", "tLogRow",
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
				 * [tJavaRow_1 end ] start
				 */

				currentComponent = "tJavaRow_1";

				globalMap.put("tJavaRow_1_NB_LINE", nb_line_tJavaRow_1);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						"tLogRow_1", "tLogRow_1", "tLogRow", "tJavaRow_1", "tJavaRow_1", "tJavaRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tJavaRow_1", true);
				end_Hash.put("tJavaRow_1", System.currentTimeMillis());

				/**
				 * [tJavaRow_1 end ] stop
				 */

				/**
				 * [tHashOutput_3 end ] start
				 */

				currentComponent = "tHashOutput_3";

				globalMap.put("tHashOutput_3_NB_LINE", nb_line_tHashOutput_3);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
						"tJavaRow_1", "tJavaRow_1", "tJavaRow", "tHashOutput_3", "tHashOutput_3", "tHashOutput",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_3", true);
				end_Hash.put("tHashOutput_3", System.currentTimeMillis());

				/**
				 * [tHashOutput_3 end ] stop
				 */

				/**
				 * [tHashOutput_2 end ] start
				 */

				currentComponent = "tHashOutput_2";

				globalMap.put("tHashOutput_2_NB_LINE", nb_line_tHashOutput_2);
				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Original", 2, 0,
						"tMap_2", "tMap_2", "tMap", "tHashOutput_2", "tHashOutput_2", "tHashOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tHashOutput_2", true);
				end_Hash.put("tHashOutput_2", System.currentTimeMillis());

				/**
				 * [tHashOutput_2 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tHashInput_2Process(globalMap);

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

			// free memory for "tSortRow_2_SortIn"
			globalMap.remove("tSortRow_2");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tSortRow_1_SortIn"
			globalMap.remove("tSortRow_1");

			// free memory for "tAggregateRow_1_AGGIN"
			globalMap.remove("tAggregateRow_1");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				cLabel = "Vendas";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tSortRow_2_SortOut finally ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortOut";

				/**
				 * [tSortRow_2_SortOut finally ] stop
				 */

				/**
				 * [tSortRow_2_SortIn finally ] start
				 */

				currentVirtualComponent = "tSortRow_2";

				currentComponent = "tSortRow_2_SortIn";

				/**
				 * [tSortRow_2_SortIn finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tJavaRow_1 finally ] start
				 */

				currentComponent = "tJavaRow_1";

				/**
				 * [tJavaRow_1 finally ] stop
				 */

				/**
				 * [tHashOutput_3 finally ] start
				 */

				currentComponent = "tHashOutput_3";

				/**
				 * [tHashOutput_3 finally ] stop
				 */

				/**
				 * [tHashOutput_2 finally ] start
				 */

				currentComponent = "tHashOutput_2";

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

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];

		public String ChaveProdutoData;

		public String getChaveProdutoData() {
			return this.ChaveProdutoData;
		}

		public Boolean ChaveProdutoDataIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoDataIsKey() {
			return false;
		}

		public Integer ChaveProdutoDataLength() {
			return null;
		}

		public Integer ChaveProdutoDataPrecision() {
			return null;
		}

		public String ChaveProdutoDataDefault() {

			return null;

		}

		public String ChaveProdutoDataComment() {

			return "";

		}

		public String ChaveProdutoDataPattern() {

			return "";

		}

		public String ChaveProdutoDataOriginalDbColumnName() {

			return "ChaveProdutoData";

		}

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer DataEstoque;

		public Integer getDataEstoque() {
			return this.DataEstoque;
		}

		public Boolean DataEstoqueIsNullable() {
			return true;
		}

		public Boolean DataEstoqueIsKey() {
			return false;
		}

		public Integer DataEstoqueLength() {
			return null;
		}

		public Integer DataEstoquePrecision() {
			return 0;
		}

		public String DataEstoqueDefault() {

			return null;

		}

		public String DataEstoqueComment() {

			return "";

		}

		public String DataEstoquePattern() {

			return "dd-MM-yyyy";

		}

		public String DataEstoqueOriginalDbColumnName() {

			return "DataEstoque";

		}

		public Integer QuantidadePedido;

		public Integer getQuantidadePedido() {
			return this.QuantidadePedido;
		}

		public Boolean QuantidadePedidoIsNullable() {
			return true;
		}

		public Boolean QuantidadePedidoIsKey() {
			return false;
		}

		public Integer QuantidadePedidoLength() {
			return null;
		}

		public Integer QuantidadePedidoPrecision() {
			return 0;
		}

		public String QuantidadePedidoDefault() {

			return null;

		}

		public String QuantidadePedidoComment() {

			return "";

		}

		public String QuantidadePedidoPattern() {

			return "dd-MM-yyyy";

		}

		public String QuantidadePedidoOriginalDbColumnName() {

			return "QuantidadePedido";

		}

		public Integer QuantidadeAnterior;

		public Integer getQuantidadeAnterior() {
			return this.QuantidadeAnterior;
		}

		public Boolean QuantidadeAnteriorIsNullable() {
			return true;
		}

		public Boolean QuantidadeAnteriorIsKey() {
			return false;
		}

		public Integer QuantidadeAnteriorLength() {
			return null;
		}

		public Integer QuantidadeAnteriorPrecision() {
			return null;
		}

		public String QuantidadeAnteriorDefault() {

			return null;

		}

		public String QuantidadeAnteriorComment() {

			return "";

		}

		public String QuantidadeAnteriorPattern() {

			return "";

		}

		public String QuantidadeAnteriorOriginalDbColumnName() {

			return "QuantidadeAnterior";

		}

		public String FlagSemEstoque;

		public String getFlagSemEstoque() {
			return this.FlagSemEstoque;
		}

		public Boolean FlagSemEstoqueIsNullable() {
			return true;
		}

		public Boolean FlagSemEstoqueIsKey() {
			return false;
		}

		public Integer FlagSemEstoqueLength() {
			return null;
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

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Loop.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Loop.length == 0) {
						commonByteArray_HYDRONORTH_Loop = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Loop = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Loop, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Loop, 0, length, utf8Charset);
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

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

					this.QuantidadeAnterior = readInteger(dis);

					this.FlagSemEstoque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProdutoData = readString(dis);

					this.ChaveProduto = readInteger(dis);

					this.DataEstoque = readInteger(dis);

					this.QuantidadePedido = readInteger(dis);

					this.QuantidadeAnterior = readInteger(dis);

					this.FlagSemEstoque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

				// Integer

				writeInteger(this.QuantidadeAnterior, dos);

				// String

				writeString(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.ChaveProdutoData, dos);

				// Integer

				writeInteger(this.ChaveProduto, dos);

				// Integer

				writeInteger(this.DataEstoque, dos);

				// Integer

				writeInteger(this.QuantidadePedido, dos);

				// Integer

				writeInteger(this.QuantidadeAnterior, dos);

				// String

				writeString(this.FlagSemEstoque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("ChaveProdutoData=" + ChaveProdutoData);
			sb.append(",ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",DataEstoque=" + String.valueOf(DataEstoque));
			sb.append(",QuantidadePedido=" + String.valueOf(QuantidadePedido));
			sb.append(",QuantidadeAnterior=" + String.valueOf(QuantidadeAnterior));
			sb.append(",FlagSemEstoque=" + FlagSemEstoque);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProdutoData == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProdutoData);
			}

			sb.append("|");

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (DataEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEstoque);
			}

			sb.append("|");

			if (QuantidadePedido == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadePedido);
			}

			sb.append("|");

			if (QuantidadeAnterior == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeAnterior);
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
		public int compareTo(row7Struct other) {

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

	public void tHashInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tHashInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tHashInput_2");
		org.slf4j.MDC.put("_subJobPid", "BKyCco_" + subJobPidCounter.getAndIncrement());

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

				row7Struct row7 = new row7Struct();

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
							log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_2.append(" | ");
							log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
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

				class Util_tLogRow_2 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[6];

					public void addRow(String[] row) {

						for (int i = 0; i < 6; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 5 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 5 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[5] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_2 util_tLogRow_2 = new Util_tLogRow_2();
				util_tLogRow_2.setTableName("tLogRow_2");
				util_tLogRow_2.addRow(new String[] { "ChaveProdutoData", "ChaveProduto", "DataEstoque",
						"QuantidadePedido", "QuantidadeAnterior", "FlagSemEstoque", });
				StringBuilder strBuffer_tLogRow_2 = null;
				int nb_line_tLogRow_2 = 0;
///////////////////////    			

				/**
				 * [tLogRow_2 begin ] stop
				 */

				/**
				 * [tHashInput_2 begin ] start
				 */

				ok_Hash.put("tHashInput_2", false);
				start_Hash.put("tHashInput_2", System.currentTimeMillis());

				currentComponent = "tHashInput_2";

				int tos_count_tHashInput_2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_2", "tHashInput_2", "tHashInput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tHashInput_2 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_2 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row6Struct> tHashFile_tHashInput_2 = mf_tHashInput_2
						.getAdvancedMemoryHashFile("tHashFile_Loop_" + pid + "_tHashOutput_3");
				if (tHashFile_tHashInput_2 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<row6Struct> iterator_tHashInput_2 = tHashFile_tHashInput_2.iterator();
				while (iterator_tHashInput_2.hasNext()) {
					row6Struct next_tHashInput_2 = iterator_tHashInput_2.next();

					row7.ChaveProdutoData = next_tHashInput_2.ChaveProdutoData;
					row7.ChaveProduto = next_tHashInput_2.ChaveProduto;
					row7.DataEstoque = next_tHashInput_2.DataEstoque;
					row7.QuantidadePedido = next_tHashInput_2.QuantidadePedido;
					row7.QuantidadeAnterior = next_tHashInput_2.EstoqueAtualizado;
					row7.FlagSemEstoque = next_tHashInput_2.FlagSemEstoque;

					/**
					 * [tHashInput_2 begin ] stop
					 */

					/**
					 * [tHashInput_2 main ] start
					 */

					currentComponent = "tHashInput_2";

					tos_count_tHashInput_2++;

					/**
					 * [tHashInput_2 main ] stop
					 */

					/**
					 * [tHashInput_2 process_data_begin ] start
					 */

					currentComponent = "tHashInput_2";

					/**
					 * [tHashInput_2 process_data_begin ] stop
					 */

					/**
					 * [tLogRow_2 main ] start
					 */

					currentComponent = "tLogRow_2";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row7", "tHashInput_2", "tHashInput_2", "tHashInput", "tLogRow_2", "tLogRow_2", "tLogRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
					}

///////////////////////		

					String[] row_tLogRow_2 = new String[6];

					if (row7.ChaveProdutoData != null) { //
						row_tLogRow_2[0] = String.valueOf(row7.ChaveProdutoData);

					} //

					if (row7.ChaveProduto != null) { //
						row_tLogRow_2[1] = String.valueOf(row7.ChaveProduto);

					} //

					if (row7.DataEstoque != null) { //
						row_tLogRow_2[2] = String.valueOf(row7.DataEstoque);

					} //

					if (row7.QuantidadePedido != null) { //
						row_tLogRow_2[3] = String.valueOf(row7.QuantidadePedido);

					} //

					if (row7.QuantidadeAnterior != null) { //
						row_tLogRow_2[4] = String.valueOf(row7.QuantidadeAnterior);

					} //

					if (row7.FlagSemEstoque != null) { //
						row_tLogRow_2[5] = String.valueOf(row7.FlagSemEstoque);

					} //

					util_tLogRow_2.addRow(row_tLogRow_2);
					nb_line_tLogRow_2++;
					log.info("tLogRow_2 - Content of row " + nb_line_tLogRow_2 + ": "
							+ TalendString.unionString("|", row_tLogRow_2));
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

					/**
					 * [tHashInput_2 process_data_end ] start
					 */

					currentComponent = "tHashInput_2";

					/**
					 * [tHashInput_2 process_data_end ] stop
					 */

					/**
					 * [tHashInput_2 end ] start
					 */

					currentComponent = "tHashInput_2";

					nb_line_tHashInput_2++;
				}

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_Loop_" + pid + "_tHashOutput_3");

				globalMap.put("tHashInput_2_NB_LINE", nb_line_tHashInput_2);

				ok_Hash.put("tHashInput_2", true);
				end_Hash.put("tHashInput_2", System.currentTimeMillis());

				/**
				 * [tHashInput_2 end ] stop
				 */

				/**
				 * [tLogRow_2 end ] start
				 */

				currentComponent = "tLogRow_2";

//////

				java.io.PrintStream consoleOut_tLogRow_2 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
				}

				consoleOut_tLogRow_2.println(util_tLogRow_2.format().toString());
				consoleOut_tLogRow_2.flush();
//////
				globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);
				if (log.isInfoEnabled())
					log.info("tLogRow_2 - " + ("Printed row count: ") + (nb_line_tLogRow_2) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
						"tHashInput_2", "tHashInput_2", "tHashInput", "tLogRow_2", "tLogRow_2", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_2 - " + ("Done."));

				ok_Hash.put("tLogRow_2", true);
				end_Hash.put("tLogRow_2", System.currentTimeMillis());

				/**
				 * [tLogRow_2 end ] stop
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
				 * [tHashInput_2 finally ] start
				 */

				currentComponent = "tHashInput_2";

				/**
				 * [tHashInput_2 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
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

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Loop = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Loop = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer ChaveProduto;

		public Integer getChaveProduto() {
			return this.ChaveProduto;
		}

		public Boolean ChaveProdutoIsNullable() {
			return true;
		}

		public Boolean ChaveProdutoIsKey() {
			return false;
		}

		public Integer ChaveProdutoLength() {
			return null;
		}

		public Integer ChaveProdutoPrecision() {
			return null;
		}

		public String ChaveProdutoDefault() {

			return null;

		}

		public String ChaveProdutoComment() {

			return "";

		}

		public String ChaveProdutoPattern() {

			return "";

		}

		public String ChaveProdutoOriginalDbColumnName() {

			return "ChaveProduto";

		}

		public Integer EstoqueInicial;

		public Integer getEstoqueInicial() {
			return this.EstoqueInicial;
		}

		public Boolean EstoqueInicialIsNullable() {
			return true;
		}

		public Boolean EstoqueInicialIsKey() {
			return false;
		}

		public Integer EstoqueInicialLength() {
			return null;
		}

		public Integer EstoqueInicialPrecision() {
			return null;
		}

		public String EstoqueInicialDefault() {

			return null;

		}

		public String EstoqueInicialComment() {

			return "";

		}

		public String EstoqueInicialPattern() {

			return "";

		}

		public String EstoqueInicialOriginalDbColumnName() {

			return "EstoqueInicial";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.ChaveProduto == null) ? 0 : this.ChaveProduto.hashCode());

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

			if (this.ChaveProduto == null) {
				if (other.ChaveProduto != null)
					return false;

			} else if (!this.ChaveProduto.equals(other.ChaveProduto))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.ChaveProduto = this.ChaveProduto;
			other.EstoqueInicial = this.EstoqueInicial;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.ChaveProduto = this.ChaveProduto;

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

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProduto = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Loop) {

				try {

					int length = 0;

					this.ChaveProduto = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.ChaveProduto, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.ChaveProduto, dos);

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

				this.EstoqueInicial = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.EstoqueInicial = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.EstoqueInicial, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.EstoqueInicial, dos, objectOut);

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
			sb.append("ChaveProduto=" + String.valueOf(ChaveProduto));
			sb.append(",EstoqueInicial=" + String.valueOf(EstoqueInicial));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (ChaveProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(ChaveProduto);
			}

			sb.append("|");

			if (EstoqueInicial == null) {
				sb.append("<null>");
			} else {
				sb.append(EstoqueInicial);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.ChaveProduto, other.ChaveProduto);
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
		org.slf4j.MDC.put("_subJobPid", "UHJoYl_" + subJobPidCounter.getAndIncrement());

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
				// source node:tHashInput_1 - inputs:(after_tFileInputDelimited_1)
				// outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4)
				// outputs:()
				// linked node: tMap_2 - inputs:(row5,row4) outputs:(out1,Original)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tHashInput_1 begin ] start
				 */

				ok_Hash.put("tHashInput_1", false);
				start_Hash.put("tHashInput_1", System.currentTimeMillis());

				currentComponent = "tHashInput_1";

				int tos_count_tHashInput_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tHashInput_1", "tHashInput_1", "tHashInput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tHashInput_1 = 0;

				org.talend.designer.components.hashfile.common.MapHashFile mf_tHashInput_1 = org.talend.designer.components.hashfile.common.MapHashFile
						.getMapHashFile();
				org.talend.designer.components.hashfile.memory.AdvancedMemoryHashFile<row1Struct> tHashFile_tHashInput_1 = mf_tHashInput_1
						.getAdvancedMemoryHashFile("tHashFile_Loop_" + pid + "_tHashOutput_1");
				if (tHashFile_tHashInput_1 == null) {
					throw new RuntimeException(
							"The hash is not initialized : The hash must exist before you read from it");
				}
				java.util.Iterator<row1Struct> iterator_tHashInput_1 = tHashFile_tHashInput_1.iterator();
				while (iterator_tHashInput_1.hasNext()) {
					row1Struct next_tHashInput_1 = iterator_tHashInput_1.next();

					row4.ChaveProduto = next_tHashInput_1.ChaveProduto;
					row4.EstoqueInicial = next_tHashInput_1.EstoqueInicial;

					/**
					 * [tHashInput_1 begin ] stop
					 */

					/**
					 * [tHashInput_1 main ] start
					 */

					currentComponent = "tHashInput_1";

					tos_count_tHashInput_1++;

					/**
					 * [tHashInput_1 main ] stop
					 */

					/**
					 * [tHashInput_1 process_data_begin ] start
					 */

					currentComponent = "tHashInput_1";

					/**
					 * [tHashInput_1 process_data_begin ] stop
					 */

					/**
					 * [tAdvancedHash_row4 main ] start
					 */

					currentComponent = "tAdvancedHash_row4";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row4", "tHashInput_1", "tHashInput_1", "tHashInput", "tAdvancedHash_row4",
							"tAdvancedHash_row4", "tAdvancedHash"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
					}

					row4Struct row4_HashRow = new row4Struct();

					row4_HashRow.ChaveProduto = row4.ChaveProduto;

					row4_HashRow.EstoqueInicial = row4.EstoqueInicial;

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
					 * [tHashInput_1 process_data_end ] start
					 */

					currentComponent = "tHashInput_1";

					/**
					 * [tHashInput_1 process_data_end ] stop
					 */

					/**
					 * [tHashInput_1 end ] start
					 */

					currentComponent = "tHashInput_1";

					nb_line_tHashInput_1++;
				}

				mf_tHashInput_1.clearCache("tHashFile_Loop_" + pid + "_tHashOutput_1");

				org.talend.designer.components.hashfile.common.MapHashFile.resourceLockMap
						.remove("tHashFile_Loop_" + pid + "_tHashOutput_1");

				globalMap.put("tHashInput_1_NB_LINE", nb_line_tHashInput_1);

				ok_Hash.put("tHashInput_1", true);
				end_Hash.put("tHashInput_1", System.currentTimeMillis());

				/**
				 * [tHashInput_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				tHash_Lookup_row4.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tHashInput_1", "tHashInput_1", "tHashInput", "tAdvancedHash_row4", "tAdvancedHash_row4",
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
				 * [tHashInput_1 finally ] start
				 */

				currentComponent = "tHashInput_1";

				/**
				 * [tHashInput_1 finally ] stop
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

		globalMap.put("tHashInput_1_SUBPROCESS_STATE", 1);
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
		final Loop LoopClass = new Loop();

		int exitCode = LoopClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Loop' - Done.");
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
		log.info("TalendJob: 'Loop' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_BTk94ERzEe-BtO_VxZtYWw");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-08-20T12:41:02.803271900Z");

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
			java.io.InputStream inContext = Loop.class.getClassLoader()
					.getResourceAsStream("hydronorth/loop_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Loop.class.getClassLoader()
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
		log.info("TalendJob: 'Loop' - Started.");
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
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Loop");
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
		log.info("TalendJob: 'Loop' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 365137 characters generated by Talend Cloud Data Integration on the 20 de
 * agosto de 2024 09:41:02 BRT
 ************************************************************************************************/