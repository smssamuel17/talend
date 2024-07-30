
package hydronorth.pedidos_mercanet_0_1;

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

@SuppressWarnings("unused")

/**
 * Job: Pedidos_mercanet Purpose: <br>
 * Description: <br>
 * 
 * @author Oliveira Santi, Thiago
 * @version 8.0.1.20240619_0909-patch
 * @status DEV
 */
public class Pedidos_mercanet implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Pedidos_mercanet.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Pedidos_mercanet.class);

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
	private final String jobName = "Pedidos_mercanet";
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
			"_QWUlIERDEe-YS8RQVZZjcw", "0.1");
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
					Pedidos_mercanet.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Pedidos_mercanet.this, new Object[] { e, currentComponent, globalMap });
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

	public void tReplicate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBOutput_2_onSubJobError(exception, errorComponent, globalMap);
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

	public void tDBOutput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Pedidos_mercanet = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer NumeroPedido;

		public Integer getNumeroPedido() {
			return this.NumeroPedido;
		}

		public Boolean NumeroPedidoIsNullable() {
			return true;
		}

		public Boolean NumeroPedidoIsKey() {
			return true;
		}

		public Integer NumeroPedidoLength() {
			return 10;
		}

		public Integer NumeroPedidoPrecision() {
			return 0;
		}

		public String NumeroPedidoDefault() {

			return "";

		}

		public String NumeroPedidoComment() {

			return "";

		}

		public String NumeroPedidoPattern() {

			return "";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public Integer SequenciaItem;

		public Integer getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return false;
		}

		public Integer SequenciaItemLength() {
			return 10;
		}

		public Integer SequenciaItemPrecision() {
			return 0;
		}

		public String SequenciaItemDefault() {

			return "";

		}

		public String SequenciaItemComment() {

			return "";

		}

		public String SequenciaItemPattern() {

			return "";

		}

		public String SequenciaItemOriginalDbColumnName() {

			return "SequenciaItem";

		}

		public String CodigoProduto;

		public String getCodigoProduto() {
			return this.CodigoProduto;
		}

		public Boolean CodigoProdutoIsNullable() {
			return true;
		}

		public Boolean CodigoProdutoIsKey() {
			return true;
		}

		public Integer CodigoProdutoLength() {
			return 16;
		}

		public Integer CodigoProdutoPrecision() {
			return 0;
		}

		public String CodigoProdutoDefault() {

			return null;

		}

		public String CodigoProdutoComment() {

			return "";

		}

		public String CodigoProdutoPattern() {

			return "";

		}

		public String CodigoProdutoOriginalDbColumnName() {

			return "CodigoProduto";

		}

		public Float QuantidadeProduto;

		public Float getQuantidadeProduto() {
			return this.QuantidadeProduto;
		}

		public Boolean QuantidadeProdutoIsNullable() {
			return true;
		}

		public Boolean QuantidadeProdutoIsKey() {
			return false;
		}

		public Integer QuantidadeProdutoLength() {
			return 7;
		}

		public Integer QuantidadeProdutoPrecision() {
			return 0;
		}

		public String QuantidadeProdutoDefault() {

			return "";

		}

		public String QuantidadeProdutoComment() {

			return "";

		}

		public String QuantidadeProdutoPattern() {

			return "";

		}

		public String QuantidadeProdutoOriginalDbColumnName() {

			return "QuantidadeProduto";

		}

		public Float QuantidadeAtendida;

		public Float getQuantidadeAtendida() {
			return this.QuantidadeAtendida;
		}

		public Boolean QuantidadeAtendidaIsNullable() {
			return true;
		}

		public Boolean QuantidadeAtendidaIsKey() {
			return false;
		}

		public Integer QuantidadeAtendidaLength() {
			return 7;
		}

		public Integer QuantidadeAtendidaPrecision() {
			return 0;
		}

		public String QuantidadeAtendidaDefault() {

			return "";

		}

		public String QuantidadeAtendidaComment() {

			return "";

		}

		public String QuantidadeAtendidaPattern() {

			return "";

		}

		public String QuantidadeAtendidaOriginalDbColumnName() {

			return "QuantidadeAtendida";

		}

		public Float QuantidadeCancelada;

		public Float getQuantidadeCancelada() {
			return this.QuantidadeCancelada;
		}

		public Boolean QuantidadeCanceladaIsNullable() {
			return true;
		}

		public Boolean QuantidadeCanceladaIsKey() {
			return false;
		}

		public Integer QuantidadeCanceladaLength() {
			return 7;
		}

		public Integer QuantidadeCanceladaPrecision() {
			return 0;
		}

		public String QuantidadeCanceladaDefault() {

			return "";

		}

		public String QuantidadeCanceladaComment() {

			return "";

		}

		public String QuantidadeCanceladaPattern() {

			return "";

		}

		public String QuantidadeCanceladaOriginalDbColumnName() {

			return "QuantidadeCancelada";

		}

		public Float PrecoUnitario;

		public Float getPrecoUnitario() {
			return this.PrecoUnitario;
		}

		public Boolean PrecoUnitarioIsNullable() {
			return true;
		}

		public Boolean PrecoUnitarioIsKey() {
			return false;
		}

		public Integer PrecoUnitarioLength() {
			return 7;
		}

		public Integer PrecoUnitarioPrecision() {
			return 0;
		}

		public String PrecoUnitarioDefault() {

			return "";

		}

		public String PrecoUnitarioComment() {

			return "";

		}

		public String PrecoUnitarioPattern() {

			return "";

		}

		public String PrecoUnitarioOriginalDbColumnName() {

			return "PrecoUnitario";

		}

		public Float PrecoLiquido;

		public Float getPrecoLiquido() {
			return this.PrecoLiquido;
		}

		public Boolean PrecoLiquidoIsNullable() {
			return true;
		}

		public Boolean PrecoLiquidoIsKey() {
			return false;
		}

		public Integer PrecoLiquidoLength() {
			return 7;
		}

		public Integer PrecoLiquidoPrecision() {
			return 0;
		}

		public String PrecoLiquidoDefault() {

			return "";

		}

		public String PrecoLiquidoComment() {

			return "";

		}

		public String PrecoLiquidoPattern() {

			return "";

		}

		public String PrecoLiquidoOriginalDbColumnName() {

			return "PrecoLiquido";

		}

		public Float Desctop;

		public Float getDesctop() {
			return this.Desctop;
		}

		public Boolean DesctopIsNullable() {
			return true;
		}

		public Boolean DesctopIsKey() {
			return false;
		}

		public Integer DesctopLength() {
			return 7;
		}

		public Integer DesctopPrecision() {
			return 0;
		}

		public String DesctopDefault() {

			return "";

		}

		public String DesctopComment() {

			return "";

		}

		public String DesctopPattern() {

			return "";

		}

		public String DesctopOriginalDbColumnName() {

			return "Desctop";

		}

		public Double DesctoIcm;

		public Double getDesctoIcm() {
			return this.DesctoIcm;
		}

		public Boolean DesctoIcmIsNullable() {
			return true;
		}

		public Boolean DesctoIcmIsKey() {
			return false;
		}

		public Integer DesctoIcmLength() {
			return 15;
		}

		public Integer DesctoIcmPrecision() {
			return 0;
		}

		public String DesctoIcmDefault() {

			return "";

		}

		public String DesctoIcmComment() {

			return "";

		}

		public String DesctoIcmPattern() {

			return "";

		}

		public String DesctoIcmOriginalDbColumnName() {

			return "DesctoIcm";

		}

		public java.util.Date PrevEntr;

		public java.util.Date getPrevEntr() {
			return this.PrevEntr;
		}

		public Boolean PrevEntrIsNullable() {
			return true;
		}

		public Boolean PrevEntrIsKey() {
			return false;
		}

		public Integer PrevEntrLength() {
			return 23;
		}

		public Integer PrevEntrPrecision() {
			return 3;
		}

		public String PrevEntrDefault() {

			return null;

		}

		public String PrevEntrComment() {

			return "";

		}

		public String PrevEntrPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevEntrOriginalDbColumnName() {

			return "PrevEntr";

		}

		public java.util.Date AtdData;

		public java.util.Date getAtdData() {
			return this.AtdData;
		}

		public Boolean AtdDataIsNullable() {
			return true;
		}

		public Boolean AtdDataIsKey() {
			return false;
		}

		public Integer AtdDataLength() {
			return 23;
		}

		public Integer AtdDataPrecision() {
			return 3;
		}

		public String AtdDataDefault() {

			return null;

		}

		public String AtdDataComment() {

			return "";

		}

		public String AtdDataPattern() {

			return "dd-MM-yyyy";

		}

		public String AtdDataOriginalDbColumnName() {

			return "AtdData";

		}

		public Float AtdQtde;

		public Float getAtdQtde() {
			return this.AtdQtde;
		}

		public Boolean AtdQtdeIsNullable() {
			return true;
		}

		public Boolean AtdQtdeIsKey() {
			return false;
		}

		public Integer AtdQtdeLength() {
			return 7;
		}

		public Integer AtdQtdePrecision() {
			return 0;
		}

		public String AtdQtdeDefault() {

			return "";

		}

		public String AtdQtdeComment() {

			return "";

		}

		public String AtdQtdePattern() {

			return "";

		}

		public String AtdQtdeOriginalDbColumnName() {

			return "AtdQtde";

		}

		public String AtdDcto;

		public String getAtdDcto() {
			return this.AtdDcto;
		}

		public Boolean AtdDctoIsNullable() {
			return true;
		}

		public Boolean AtdDctoIsKey() {
			return false;
		}

		public Integer AtdDctoLength() {
			return 20;
		}

		public Integer AtdDctoPrecision() {
			return 0;
		}

		public String AtdDctoDefault() {

			return null;

		}

		public String AtdDctoComment() {

			return "";

		}

		public String AtdDctoPattern() {

			return "";

		}

		public String AtdDctoOriginalDbColumnName() {

			return "AtdDcto";

		}

		public java.util.Date AtdData1;

		public java.util.Date getAtdData1() {
			return this.AtdData1;
		}

		public Boolean AtdData1IsNullable() {
			return true;
		}

		public Boolean AtdData1IsKey() {
			return false;
		}

		public Integer AtdData1Length() {
			return 23;
		}

		public Integer AtdData1Precision() {
			return 3;
		}

		public String AtdData1Default() {

			return null;

		}

		public String AtdData1Comment() {

			return "";

		}

		public String AtdData1Pattern() {

			return "dd-MM-yyyy";

		}

		public String AtdData1OriginalDbColumnName() {

			return "AtdData1";

		}

		public Float AtdQtde1;

		public Float getAtdQtde1() {
			return this.AtdQtde1;
		}

		public Boolean AtdQtde1IsNullable() {
			return true;
		}

		public Boolean AtdQtde1IsKey() {
			return false;
		}

		public Integer AtdQtde1Length() {
			return 7;
		}

		public Integer AtdQtde1Precision() {
			return 0;
		}

		public String AtdQtde1Default() {

			return "";

		}

		public String AtdQtde1Comment() {

			return "";

		}

		public String AtdQtde1Pattern() {

			return "";

		}

		public String AtdQtde1OriginalDbColumnName() {

			return "AtdQtde1";

		}

		public String AtdDcto1;

		public String getAtdDcto1() {
			return this.AtdDcto1;
		}

		public Boolean AtdDcto1IsNullable() {
			return true;
		}

		public Boolean AtdDcto1IsKey() {
			return false;
		}

		public Integer AtdDcto1Length() {
			return 20;
		}

		public Integer AtdDcto1Precision() {
			return 0;
		}

		public String AtdDcto1Default() {

			return null;

		}

		public String AtdDcto1Comment() {

			return "";

		}

		public String AtdDcto1Pattern() {

			return "";

		}

		public String AtdDcto1OriginalDbColumnName() {

			return "AtdDcto1";

		}

		public java.util.Date AtdData2;

		public java.util.Date getAtdData2() {
			return this.AtdData2;
		}

		public Boolean AtdData2IsNullable() {
			return true;
		}

		public Boolean AtdData2IsKey() {
			return false;
		}

		public Integer AtdData2Length() {
			return 23;
		}

		public Integer AtdData2Precision() {
			return 3;
		}

		public String AtdData2Default() {

			return null;

		}

		public String AtdData2Comment() {

			return "";

		}

		public String AtdData2Pattern() {

			return "dd-MM-yyyy";

		}

		public String AtdData2OriginalDbColumnName() {

			return "AtdData2";

		}

		public Float AtdQtde2;

		public Float getAtdQtde2() {
			return this.AtdQtde2;
		}

		public Boolean AtdQtde2IsNullable() {
			return true;
		}

		public Boolean AtdQtde2IsKey() {
			return false;
		}

		public Integer AtdQtde2Length() {
			return 7;
		}

		public Integer AtdQtde2Precision() {
			return 0;
		}

		public String AtdQtde2Default() {

			return "";

		}

		public String AtdQtde2Comment() {

			return "";

		}

		public String AtdQtde2Pattern() {

			return "";

		}

		public String AtdQtde2OriginalDbColumnName() {

			return "AtdQtde2";

		}

		public String AtdDcto2;

		public String getAtdDcto2() {
			return this.AtdDcto2;
		}

		public Boolean AtdDcto2IsNullable() {
			return true;
		}

		public Boolean AtdDcto2IsKey() {
			return false;
		}

		public Integer AtdDcto2Length() {
			return 20;
		}

		public Integer AtdDcto2Precision() {
			return 0;
		}

		public String AtdDcto2Default() {

			return null;

		}

		public String AtdDcto2Comment() {

			return "";

		}

		public String AtdDcto2Pattern() {

			return "";

		}

		public String AtdDcto2OriginalDbColumnName() {

			return "AtdDcto2";

		}

		public Short SituacaoPedido;

		public Short getSituacaoPedido() {
			return this.SituacaoPedido;
		}

		public Boolean SituacaoPedidoIsNullable() {
			return true;
		}

		public Boolean SituacaoPedidoIsKey() {
			return false;
		}

		public Integer SituacaoPedidoLength() {
			return 5;
		}

		public Integer SituacaoPedidoPrecision() {
			return 0;
		}

		public String SituacaoPedidoDefault() {

			return "";

		}

		public String SituacaoPedidoComment() {

			return "";

		}

		public String SituacaoPedidoPattern() {

			return "";

		}

		public String SituacaoPedidoOriginalDbColumnName() {

			return "SituacaoPedido";

		}

		public Double Ipi;

		public Double getIpi() {
			return this.Ipi;
		}

		public Boolean IpiIsNullable() {
			return true;
		}

		public Boolean IpiIsKey() {
			return false;
		}

		public Integer IpiLength() {
			return 15;
		}

		public Integer IpiPrecision() {
			return 0;
		}

		public String IpiDefault() {

			return "";

		}

		public String IpiComment() {

			return "";

		}

		public String IpiPattern() {

			return "";

		}

		public String IpiOriginalDbColumnName() {

			return "Ipi";

		}

		public Float Dctoprom;

		public Float getDctoprom() {
			return this.Dctoprom;
		}

		public Boolean DctopromIsNullable() {
			return true;
		}

		public Boolean DctopromIsKey() {
			return false;
		}

		public Integer DctopromLength() {
			return 7;
		}

		public Integer DctopromPrecision() {
			return 0;
		}

		public String DctopromDefault() {

			return "";

		}

		public String DctopromComment() {

			return "";

		}

		public String DctopromPattern() {

			return "";

		}

		public String DctopromOriginalDbColumnName() {

			return "Dctoprom";

		}

		public Float EconVlr;

		public Float getEconVlr() {
			return this.EconVlr;
		}

		public Boolean EconVlrIsNullable() {
			return true;
		}

		public Boolean EconVlrIsKey() {
			return false;
		}

		public Integer EconVlrLength() {
			return 7;
		}

		public Integer EconVlrPrecision() {
			return 0;
		}

		public String EconVlrDefault() {

			return "";

		}

		public String EconVlrComment() {

			return "";

		}

		public String EconVlrPattern() {

			return "";

		}

		public String EconVlrOriginalDbColumnName() {

			return "EconVlr";

		}

		public Short EconCalc;

		public Short getEconCalc() {
			return this.EconCalc;
		}

		public Boolean EconCalcIsNullable() {
			return true;
		}

		public Boolean EconCalcIsKey() {
			return false;
		}

		public Integer EconCalcLength() {
			return 3;
		}

		public Integer EconCalcPrecision() {
			return 0;
		}

		public String EconCalcDefault() {

			return "";

		}

		public String EconCalcComment() {

			return "";

		}

		public String EconCalcPattern() {

			return "";

		}

		public String EconCalcOriginalDbColumnName() {

			return "EconCalc";

		}

		public Float EconDcto;

		public Float getEconDcto() {
			return this.EconDcto;
		}

		public Boolean EconDctoIsNullable() {
			return true;
		}

		public Boolean EconDctoIsKey() {
			return false;
		}

		public Integer EconDctoLength() {
			return 7;
		}

		public Integer EconDctoPrecision() {
			return 0;
		}

		public String EconDctoDefault() {

			return "";

		}

		public String EconDctoComment() {

			return "";

		}

		public String EconDctoPattern() {

			return "";

		}

		public String EconDctoOriginalDbColumnName() {

			return "EconDcto";

		}

		public Float PrecoMin;

		public Float getPrecoMin() {
			return this.PrecoMin;
		}

		public Boolean PrecoMinIsNullable() {
			return true;
		}

		public Boolean PrecoMinIsKey() {
			return false;
		}

		public Integer PrecoMinLength() {
			return 7;
		}

		public Integer PrecoMinPrecision() {
			return 0;
		}

		public String PrecoMinDefault() {

			return "";

		}

		public String PrecoMinComment() {

			return "";

		}

		public String PrecoMinPattern() {

			return "";

		}

		public String PrecoMinOriginalDbColumnName() {

			return "PrecoMin";

		}

		public String Tipo;

		public String getTipo() {
			return this.Tipo;
		}

		public Boolean TipoIsNullable() {
			return true;
		}

		public Boolean TipoIsKey() {
			return false;
		}

		public Integer TipoLength() {
			return 1;
		}

		public Integer TipoPrecision() {
			return 0;
		}

		public String TipoDefault() {

			return null;

		}

		public String TipoComment() {

			return "";

		}

		public String TipoPattern() {

			return "";

		}

		public String TipoOriginalDbColumnName() {

			return "Tipo";

		}

		public Double Dctovda;

		public Double getDctovda() {
			return this.Dctovda;
		}

		public Boolean DctovdaIsNullable() {
			return true;
		}

		public Boolean DctovdaIsKey() {
			return false;
		}

		public Integer DctovdaLength() {
			return 15;
		}

		public Integer DctovdaPrecision() {
			return 0;
		}

		public String DctovdaDefault() {

			return "";

		}

		public String DctovdaComment() {

			return "";

		}

		public String DctovdaPattern() {

			return "";

		}

		public String DctovdaOriginalDbColumnName() {

			return "Dctovda";

		}

		public Short TipoPreco;

		public Short getTipoPreco() {
			return this.TipoPreco;
		}

		public Boolean TipoPrecoIsNullable() {
			return true;
		}

		public Boolean TipoPrecoIsKey() {
			return false;
		}

		public Integer TipoPrecoLength() {
			return 5;
		}

		public Integer TipoPrecoPrecision() {
			return 0;
		}

		public String TipoPrecoDefault() {

			return "";

		}

		public String TipoPrecoComment() {

			return "";

		}

		public String TipoPrecoPattern() {

			return "";

		}

		public String TipoPrecoOriginalDbColumnName() {

			return "TipoPreco";

		}

		public Short EstVlr;

		public Short getEstVlr() {
			return this.EstVlr;
		}

		public Boolean EstVlrIsNullable() {
			return true;
		}

		public Boolean EstVlrIsKey() {
			return false;
		}

		public Integer EstVlrLength() {
			return 5;
		}

		public Integer EstVlrPrecision() {
			return 0;
		}

		public String EstVlrDefault() {

			return "";

		}

		public String EstVlrComment() {

			return "";

		}

		public String EstVlrPattern() {

			return "";

		}

		public String EstVlrOriginalDbColumnName() {

			return "EstVlr";

		}

		public Double Acrescimo;

		public Double getAcrescimo() {
			return this.Acrescimo;
		}

		public Boolean AcrescimoIsNullable() {
			return true;
		}

		public Boolean AcrescimoIsKey() {
			return false;
		}

		public Integer AcrescimoLength() {
			return 15;
		}

		public Integer AcrescimoPrecision() {
			return 0;
		}

		public String AcrescimoDefault() {

			return "";

		}

		public String AcrescimoComment() {

			return "";

		}

		public String AcrescimoPattern() {

			return "";

		}

		public String AcrescimoOriginalDbColumnName() {

			return "Acrescimo";

		}

		public String Lpreco;

		public String getLpreco() {
			return this.Lpreco;
		}

		public Boolean LprecoIsNullable() {
			return true;
		}

		public Boolean LprecoIsKey() {
			return false;
		}

		public Integer LprecoLength() {
			return 8;
		}

		public Integer LprecoPrecision() {
			return 0;
		}

		public String LprecoDefault() {

			return null;

		}

		public String LprecoComment() {

			return "";

		}

		public String LprecoPattern() {

			return "";

		}

		public String LprecoOriginalDbColumnName() {

			return "Lpreco";

		}

		public String Grade;

		public String getGrade() {
			return this.Grade;
		}

		public Boolean GradeIsNullable() {
			return true;
		}

		public Boolean GradeIsKey() {
			return false;
		}

		public Integer GradeLength() {
			return 4;
		}

		public Integer GradePrecision() {
			return 0;
		}

		public String GradeDefault() {

			return null;

		}

		public String GradeComment() {

			return "";

		}

		public String GradePattern() {

			return "";

		}

		public String GradeOriginalDbColumnName() {

			return "Grade";

		}

		public Short TpGrade;

		public Short getTpGrade() {
			return this.TpGrade;
		}

		public Boolean TpGradeIsNullable() {
			return true;
		}

		public Boolean TpGradeIsKey() {
			return false;
		}

		public Integer TpGradeLength() {
			return 5;
		}

		public Integer TpGradePrecision() {
			return 0;
		}

		public String TpGradeDefault() {

			return "";

		}

		public String TpGradeComment() {

			return "";

		}

		public String TpGradePattern() {

			return "";

		}

		public String TpGradeOriginalDbColumnName() {

			return "TpGrade";

		}

		public String CodEtiq;

		public String getCodEtiq() {
			return this.CodEtiq;
		}

		public Boolean CodEtiqIsNullable() {
			return true;
		}

		public Boolean CodEtiqIsKey() {
			return false;
		}

		public Integer CodEtiqLength() {
			return 5;
		}

		public Integer CodEtiqPrecision() {
			return 0;
		}

		public String CodEtiqDefault() {

			return null;

		}

		public String CodEtiqComment() {

			return "";

		}

		public String CodEtiqPattern() {

			return "";

		}

		public String CodEtiqOriginalDbColumnName() {

			return "CodEtiq";

		}

		public Double Embalagem;

		public Double getEmbalagem() {
			return this.Embalagem;
		}

		public Boolean EmbalagemIsNullable() {
			return true;
		}

		public Boolean EmbalagemIsKey() {
			return false;
		}

		public Integer EmbalagemLength() {
			return 15;
		}

		public Integer EmbalagemPrecision() {
			return 0;
		}

		public String EmbalagemDefault() {

			return "";

		}

		public String EmbalagemComment() {

			return "";

		}

		public String EmbalagemPattern() {

			return "";

		}

		public String EmbalagemOriginalDbColumnName() {

			return "Embalagem";

		}

		public String MotCanc;

		public String getMotCanc() {
			return this.MotCanc;
		}

		public Boolean MotCancIsNullable() {
			return true;
		}

		public Boolean MotCancIsKey() {
			return false;
		}

		public Integer MotCancLength() {
			return 5;
		}

		public Integer MotCancPrecision() {
			return 0;
		}

		public String MotCancDefault() {

			return null;

		}

		public String MotCancComment() {

			return "";

		}

		public String MotCancPattern() {

			return "";

		}

		public String MotCancOriginalDbColumnName() {

			return "MotCanc";

		}

		public Float PercComis;

		public Float getPercComis() {
			return this.PercComis;
		}

		public Boolean PercComisIsNullable() {
			return true;
		}

		public Boolean PercComisIsKey() {
			return false;
		}

		public Integer PercComisLength() {
			return 7;
		}

		public Integer PercComisPrecision() {
			return 0;
		}

		public String PercComisDefault() {

			return "";

		}

		public String PercComisComment() {

			return "";

		}

		public String PercComisPattern() {

			return "";

		}

		public String PercComisOriginalDbColumnName() {

			return "PercComis";

		}

		public String PrdCfgCar;

		public String getPrdCfgCar() {
			return this.PrdCfgCar;
		}

		public Boolean PrdCfgCarIsNullable() {
			return true;
		}

		public Boolean PrdCfgCarIsKey() {
			return false;
		}

		public Integer PrdCfgCarLength() {
			return 1024;
		}

		public Integer PrdCfgCarPrecision() {
			return 0;
		}

		public String PrdCfgCarDefault() {

			return null;

		}

		public String PrdCfgCarComment() {

			return "";

		}

		public String PrdCfgCarPattern() {

			return "";

		}

		public String PrdCfgCarOriginalDbColumnName() {

			return "PrdCfgCar";

		}

		public String Observacao;

		public String getObservacao() {
			return this.Observacao;
		}

		public Boolean ObservacaoIsNullable() {
			return true;
		}

		public Boolean ObservacaoIsKey() {
			return false;
		}

		public Integer ObservacaoLength() {
			return 100;
		}

		public Integer ObservacaoPrecision() {
			return 0;
		}

		public String ObservacaoDefault() {

			return null;

		}

		public String ObservacaoComment() {

			return "";

		}

		public String ObservacaoPattern() {

			return "";

		}

		public String ObservacaoOriginalDbColumnName() {

			return "Observacao";

		}

		public String BonifEcon;

		public String getBonifEcon() {
			return this.BonifEcon;
		}

		public Boolean BonifEconIsNullable() {
			return true;
		}

		public Boolean BonifEconIsKey() {
			return false;
		}

		public Integer BonifEconLength() {
			return 1;
		}

		public Integer BonifEconPrecision() {
			return 0;
		}

		public String BonifEconDefault() {

			return null;

		}

		public String BonifEconComment() {

			return "";

		}

		public String BonifEconPattern() {

			return "";

		}

		public String BonifEconOriginalDbColumnName() {

			return "BonifEcon";

		}

		public Double PcomisExt;

		public Double getPcomisExt() {
			return this.PcomisExt;
		}

		public Boolean PcomisExtIsNullable() {
			return true;
		}

		public Boolean PcomisExtIsKey() {
			return false;
		}

		public Integer PcomisExtLength() {
			return 15;
		}

		public Integer PcomisExtPrecision() {
			return 0;
		}

		public String PcomisExtDefault() {

			return "";

		}

		public String PcomisExtComment() {

			return "";

		}

		public String PcomisExtPattern() {

			return "";

		}

		public String PcomisExtOriginalDbColumnName() {

			return "PcomisExt";

		}

		public String AplicDcto;

		public String getAplicDcto() {
			return this.AplicDcto;
		}

		public Boolean AplicDctoIsNullable() {
			return true;
		}

		public Boolean AplicDctoIsKey() {
			return false;
		}

		public Integer AplicDctoLength() {
			return 10;
		}

		public Integer AplicDctoPrecision() {
			return 0;
		}

		public String AplicDctoDefault() {

			return null;

		}

		public String AplicDctoComment() {

			return "";

		}

		public String AplicDctoPattern() {

			return "";

		}

		public String AplicDctoOriginalDbColumnName() {

			return "AplicDcto";

		}

		public String Operacao;

		public String getOperacao() {
			return this.Operacao;
		}

		public Boolean OperacaoIsNullable() {
			return true;
		}

		public Boolean OperacaoIsKey() {
			return false;
		}

		public Integer OperacaoLength() {
			return 10;
		}

		public Integer OperacaoPrecision() {
			return 0;
		}

		public String OperacaoDefault() {

			return null;

		}

		public String OperacaoComment() {

			return "";

		}

		public String OperacaoPattern() {

			return "";

		}

		public String OperacaoOriginalDbColumnName() {

			return "Operacao";

		}

		public Double QtdeRes;

		public Double getQtdeRes() {
			return this.QtdeRes;
		}

		public Boolean QtdeResIsNullable() {
			return true;
		}

		public Boolean QtdeResIsKey() {
			return false;
		}

		public Integer QtdeResLength() {
			return 15;
		}

		public Integer QtdeResPrecision() {
			return 0;
		}

		public String QtdeResDefault() {

			return "";

		}

		public String QtdeResComment() {

			return "";

		}

		public String QtdeResPattern() {

			return "";

		}

		public String QtdeResOriginalDbColumnName() {

			return "QtdeRes";

		}

		public String Bloqsug;

		public String getBloqsug() {
			return this.Bloqsug;
		}

		public Boolean BloqsugIsNullable() {
			return true;
		}

		public Boolean BloqsugIsKey() {
			return false;
		}

		public Integer BloqsugLength() {
			return 1;
		}

		public Integer BloqsugPrecision() {
			return 0;
		}

		public String BloqsugDefault() {

			return null;

		}

		public String BloqsugComment() {

			return "";

		}

		public String BloqsugPattern() {

			return "";

		}

		public String BloqsugOriginalDbColumnName() {

			return "Bloqsug";

		}

		public Float Dctoinc;

		public Float getDctoinc() {
			return this.Dctoinc;
		}

		public Boolean DctoincIsNullable() {
			return true;
		}

		public Boolean DctoincIsKey() {
			return false;
		}

		public Integer DctoincLength() {
			return 7;
		}

		public Integer DctoincPrecision() {
			return 0;
		}

		public String DctoincDefault() {

			return "";

		}

		public String DctoincComment() {

			return "";

		}

		public String DctoincPattern() {

			return "";

		}

		public String DctoincOriginalDbColumnName() {

			return "Dctoinc";

		}

		public Double VlrIpi;

		public Double getVlrIpi() {
			return this.VlrIpi;
		}

		public Boolean VlrIpiIsNullable() {
			return true;
		}

		public Boolean VlrIpiIsKey() {
			return false;
		}

		public Integer VlrIpiLength() {
			return 15;
		}

		public Integer VlrIpiPrecision() {
			return 0;
		}

		public String VlrIpiDefault() {

			return "";

		}

		public String VlrIpiComment() {

			return "";

		}

		public String VlrIpiPattern() {

			return "";

		}

		public String VlrIpiOriginalDbColumnName() {

			return "VlrIpi";

		}

		public String SeqGrd;

		public String getSeqGrd() {
			return this.SeqGrd;
		}

		public Boolean SeqGrdIsNullable() {
			return true;
		}

		public Boolean SeqGrdIsKey() {
			return false;
		}

		public Integer SeqGrdLength() {
			return 7;
		}

		public Integer SeqGrdPrecision() {
			return 0;
		}

		public String SeqGrdDefault() {

			return null;

		}

		public String SeqGrdComment() {

			return "";

		}

		public String SeqGrdPattern() {

			return "";

		}

		public String SeqGrdOriginalDbColumnName() {

			return "SeqGrd";

		}

		public Integer SeqErp;

		public Integer getSeqErp() {
			return this.SeqErp;
		}

		public Boolean SeqErpIsNullable() {
			return true;
		}

		public Boolean SeqErpIsKey() {
			return false;
		}

		public Integer SeqErpLength() {
			return 10;
		}

		public Integer SeqErpPrecision() {
			return 0;
		}

		public String SeqErpDefault() {

			return "";

		}

		public String SeqErpComment() {

			return "";

		}

		public String SeqErpPattern() {

			return "";

		}

		public String SeqErpOriginalDbColumnName() {

			return "SeqErp";

		}

		public java.util.Date PrevCalc;

		public java.util.Date getPrevCalc() {
			return this.PrevCalc;
		}

		public Boolean PrevCalcIsNullable() {
			return true;
		}

		public Boolean PrevCalcIsKey() {
			return false;
		}

		public Integer PrevCalcLength() {
			return 23;
		}

		public Integer PrevCalcPrecision() {
			return 3;
		}

		public String PrevCalcDefault() {

			return null;

		}

		public String PrevCalcComment() {

			return "";

		}

		public String PrevCalcPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevCalcOriginalDbColumnName() {

			return "PrevCalc";

		}

		public String Almoxarif;

		public String getAlmoxarif() {
			return this.Almoxarif;
		}

		public Boolean AlmoxarifIsNullable() {
			return true;
		}

		public Boolean AlmoxarifIsKey() {
			return false;
		}

		public Integer AlmoxarifLength() {
			return 3;
		}

		public Integer AlmoxarifPrecision() {
			return 0;
		}

		public String AlmoxarifDefault() {

			return null;

		}

		public String AlmoxarifComment() {

			return "";

		}

		public String AlmoxarifPattern() {

			return "";

		}

		public String AlmoxarifOriginalDbColumnName() {

			return "Almoxarif";

		}

		public Double ComisGer;

		public Double getComisGer() {
			return this.ComisGer;
		}

		public Boolean ComisGerIsNullable() {
			return true;
		}

		public Boolean ComisGerIsKey() {
			return false;
		}

		public Integer ComisGerLength() {
			return 15;
		}

		public Integer ComisGerPrecision() {
			return 0;
		}

		public String ComisGerDefault() {

			return "";

		}

		public String ComisGerComment() {

			return "";

		}

		public String ComisGerPattern() {

			return "";

		}

		public String ComisGerOriginalDbColumnName() {

			return "ComisGer";

		}

		public Double CredProp;

		public Double getCredProp() {
			return this.CredProp;
		}

		public Boolean CredPropIsNullable() {
			return true;
		}

		public Boolean CredPropIsKey() {
			return false;
		}

		public Integer CredPropLength() {
			return 15;
		}

		public Integer CredPropPrecision() {
			return 0;
		}

		public String CredPropDefault() {

			return "";

		}

		public String CredPropComment() {

			return "";

		}

		public String CredPropPattern() {

			return "";

		}

		public String CredPropOriginalDbColumnName() {

			return "CredProp";

		}

		public Double NroEnvio;

		public Double getNroEnvio() {
			return this.NroEnvio;
		}

		public Boolean NroEnvioIsNullable() {
			return true;
		}

		public Boolean NroEnvioIsKey() {
			return false;
		}

		public Integer NroEnvioLength() {
			return 15;
		}

		public Integer NroEnvioPrecision() {
			return 0;
		}

		public String NroEnvioDefault() {

			return "";

		}

		public String NroEnvioComment() {

			return "";

		}

		public String NroEnvioPattern() {

			return "";

		}

		public String NroEnvioOriginalDbColumnName() {

			return "NroEnvio";

		}

		public String Chave;

		public String getChave() {
			return this.Chave;
		}

		public Boolean ChaveIsNullable() {
			return true;
		}

		public Boolean ChaveIsKey() {
			return false;
		}

		public Integer ChaveLength() {
			return 1;
		}

		public Integer ChavePrecision() {
			return 0;
		}

		public String ChaveDefault() {

			return null;

		}

		public String ChaveComment() {

			return "";

		}

		public String ChavePattern() {

			return "";

		}

		public String ChaveOriginalDbColumnName() {

			return "Chave";

		}

		public String ItemConf;

		public String getItemConf() {
			return this.ItemConf;
		}

		public Boolean ItemConfIsNullable() {
			return true;
		}

		public Boolean ItemConfIsKey() {
			return false;
		}

		public Integer ItemConfLength() {
			return 16;
		}

		public Integer ItemConfPrecision() {
			return 0;
		}

		public String ItemConfDefault() {

			return null;

		}

		public String ItemConfComment() {

			return "";

		}

		public String ItemConfPattern() {

			return "";

		}

		public String ItemConfOriginalDbColumnName() {

			return "ItemConf";

		}

		public String CvdaDcti;

		public String getCvdaDcti() {
			return this.CvdaDcti;
		}

		public Boolean CvdaDctiIsNullable() {
			return true;
		}

		public Boolean CvdaDctiIsKey() {
			return false;
		}

		public Integer CvdaDctiLength() {
			return 10;
		}

		public Integer CvdaDctiPrecision() {
			return 0;
		}

		public String CvdaDctiDefault() {

			return null;

		}

		public String CvdaDctiComment() {

			return "";

		}

		public String CvdaDctiPattern() {

			return "";

		}

		public String CvdaDctiOriginalDbColumnName() {

			return "CvdaDcti";

		}

		public String ObsNota;

		public String getObsNota() {
			return this.ObsNota;
		}

		public Boolean ObsNotaIsNullable() {
			return true;
		}

		public Boolean ObsNotaIsKey() {
			return false;
		}

		public Integer ObsNotaLength() {
			return 50;
		}

		public Integer ObsNotaPrecision() {
			return 0;
		}

		public String ObsNotaDefault() {

			return null;

		}

		public String ObsNotaComment() {

			return "";

		}

		public String ObsNotaPattern() {

			return "";

		}

		public String ObsNotaOriginalDbColumnName() {

			return "ObsNota";

		}

		public String ObsExped;

		public String getObsExped() {
			return this.ObsExped;
		}

		public Boolean ObsExpedIsNullable() {
			return true;
		}

		public Boolean ObsExpedIsKey() {
			return false;
		}

		public Integer ObsExpedLength() {
			return 50;
		}

		public Integer ObsExpedPrecision() {
			return 0;
		}

		public String ObsExpedDefault() {

			return null;

		}

		public String ObsExpedComment() {

			return "";

		}

		public String ObsExpedPattern() {

			return "";

		}

		public String ObsExpedOriginalDbColumnName() {

			return "ObsExped";

		}

		public String ObsSepar;

		public String getObsSepar() {
			return this.ObsSepar;
		}

		public Boolean ObsSeparIsNullable() {
			return true;
		}

		public Boolean ObsSeparIsKey() {
			return false;
		}

		public Integer ObsSeparLength() {
			return 255;
		}

		public Integer ObsSeparPrecision() {
			return 0;
		}

		public String ObsSeparDefault() {

			return null;

		}

		public String ObsSeparComment() {

			return "";

		}

		public String ObsSeparPattern() {

			return "";

		}

		public String ObsSeparOriginalDbColumnName() {

			return "ObsSepar";

		}

		public Double VlrSubst;

		public Double getVlrSubst() {
			return this.VlrSubst;
		}

		public Boolean VlrSubstIsNullable() {
			return true;
		}

		public Boolean VlrSubstIsKey() {
			return false;
		}

		public Integer VlrSubstLength() {
			return 15;
		}

		public Integer VlrSubstPrecision() {
			return 0;
		}

		public String VlrSubstDefault() {

			return "";

		}

		public String VlrSubstComment() {

			return "";

		}

		public String VlrSubstPattern() {

			return "";

		}

		public String VlrSubstOriginalDbColumnName() {

			return "VlrSubst";

		}

		public Double DesctoGer;

		public Double getDesctoGer() {
			return this.DesctoGer;
		}

		public Boolean DesctoGerIsNullable() {
			return true;
		}

		public Boolean DesctoGerIsKey() {
			return false;
		}

		public Integer DesctoGerLength() {
			return 15;
		}

		public Integer DesctoGerPrecision() {
			return 0;
		}

		public String DesctoGerDefault() {

			return "";

		}

		public String DesctoGerComment() {

			return "";

		}

		public String DesctoGerPattern() {

			return "";

		}

		public String DesctoGerOriginalDbColumnName() {

			return "DesctoGer";

		}

		public Double VlrFreTit;

		public Double getVlrFreTit() {
			return this.VlrFreTit;
		}

		public Boolean VlrFreTitIsNullable() {
			return true;
		}

		public Boolean VlrFreTitIsKey() {
			return false;
		}

		public Integer VlrFreTitLength() {
			return 15;
		}

		public Integer VlrFreTitPrecision() {
			return 0;
		}

		public String VlrFreTitDefault() {

			return "";

		}

		public String VlrFreTitComment() {

			return "";

		}

		public String VlrFreTitPattern() {

			return "";

		}

		public String VlrFreTitOriginalDbColumnName() {

			return "VlrFreTit";

		}

		public String MotTroca;

		public String getMotTroca() {
			return this.MotTroca;
		}

		public Boolean MotTrocaIsNullable() {
			return true;
		}

		public Boolean MotTrocaIsKey() {
			return false;
		}

		public Integer MotTrocaLength() {
			return 5;
		}

		public Integer MotTrocaPrecision() {
			return 0;
		}

		public String MotTrocaDefault() {

			return null;

		}

		public String MotTrocaComment() {

			return "";

		}

		public String MotTrocaPattern() {

			return "";

		}

		public String MotTrocaOriginalDbColumnName() {

			return "MotTroca";

		}

		public Double VeconCig;

		public Double getVeconCig() {
			return this.VeconCig;
		}

		public Boolean VeconCigIsNullable() {
			return true;
		}

		public Boolean VeconCigIsKey() {
			return false;
		}

		public Integer VeconCigLength() {
			return 15;
		}

		public Integer VeconCigPrecision() {
			return 0;
		}

		public String VeconCigDefault() {

			return "";

		}

		public String VeconCigComment() {

			return "";

		}

		public String VeconCigPattern() {

			return "";

		}

		public String VeconCigOriginalDbColumnName() {

			return "VeconCig";

		}

		public Double CustoMp;

		public Double getCustoMp() {
			return this.CustoMp;
		}

		public Boolean CustoMpIsNullable() {
			return true;
		}

		public Boolean CustoMpIsKey() {
			return false;
		}

		public Integer CustoMpLength() {
			return 15;
		}

		public Integer CustoMpPrecision() {
			return 0;
		}

		public String CustoMpDefault() {

			return "";

		}

		public String CustoMpComment() {

			return "";

		}

		public String CustoMpPattern() {

			return "";

		}

		public String CustoMpOriginalDbColumnName() {

			return "CustoMp";

		}

		public Short PliqAlt;

		public Short getPliqAlt() {
			return this.PliqAlt;
		}

		public Boolean PliqAltIsNullable() {
			return true;
		}

		public Boolean PliqAltIsKey() {
			return false;
		}

		public Integer PliqAltLength() {
			return 5;
		}

		public Integer PliqAltPrecision() {
			return 0;
		}

		public String PliqAltDefault() {

			return "";

		}

		public String PliqAltComment() {

			return "";

		}

		public String PliqAltPattern() {

			return "";

		}

		public String PliqAltOriginalDbColumnName() {

			return "PliqAlt";

		}

		public String CodPrMap;

		public String getCodPrMap() {
			return this.CodPrMap;
		}

		public Boolean CodPrMapIsNullable() {
			return true;
		}

		public Boolean CodPrMapIsKey() {
			return false;
		}

		public Integer CodPrMapLength() {
			return 10;
		}

		public Integer CodPrMapPrecision() {
			return 0;
		}

		public String CodPrMapDefault() {

			return null;

		}

		public String CodPrMapComment() {

			return "";

		}

		public String CodPrMapPattern() {

			return "";

		}

		public String CodPrMapOriginalDbColumnName() {

			return "CodPrMap";

		}

		public String CodCvdAg;

		public String getCodCvdAg() {
			return this.CodCvdAg;
		}

		public Boolean CodCvdAgIsNullable() {
			return true;
		}

		public Boolean CodCvdAgIsKey() {
			return false;
		}

		public Integer CodCvdAgLength() {
			return 10;
		}

		public Integer CodCvdAgPrecision() {
			return 0;
		}

		public String CodCvdAgDefault() {

			return null;

		}

		public String CodCvdAgComment() {

			return "";

		}

		public String CodCvdAgPattern() {

			return "";

		}

		public String CodCvdAgOriginalDbColumnName() {

			return "CodCvdAg";

		}

		public Double VuIcms;

		public Double getVuIcms() {
			return this.VuIcms;
		}

		public Boolean VuIcmsIsNullable() {
			return true;
		}

		public Boolean VuIcmsIsKey() {
			return false;
		}

		public Integer VuIcmsLength() {
			return 15;
		}

		public Integer VuIcmsPrecision() {
			return 0;
		}

		public String VuIcmsDefault() {

			return "";

		}

		public String VuIcmsComment() {

			return "";

		}

		public String VuIcmsPattern() {

			return "";

		}

		public String VuIcmsOriginalDbColumnName() {

			return "VuIcms";

		}

		public Double VuPis;

		public Double getVuPis() {
			return this.VuPis;
		}

		public Boolean VuPisIsNullable() {
			return true;
		}

		public Boolean VuPisIsKey() {
			return false;
		}

		public Integer VuPisLength() {
			return 15;
		}

		public Integer VuPisPrecision() {
			return 0;
		}

		public String VuPisDefault() {

			return "";

		}

		public String VuPisComment() {

			return "";

		}

		public String VuPisPattern() {

			return "";

		}

		public String VuPisOriginalDbColumnName() {

			return "VuPis";

		}

		public Double VuCofins;

		public Double getVuCofins() {
			return this.VuCofins;
		}

		public Boolean VuCofinsIsNullable() {
			return true;
		}

		public Boolean VuCofinsIsKey() {
			return false;
		}

		public Integer VuCofinsLength() {
			return 15;
		}

		public Integer VuCofinsPrecision() {
			return 0;
		}

		public String VuCofinsDefault() {

			return "";

		}

		public String VuCofinsComment() {

			return "";

		}

		public String VuCofinsPattern() {

			return "";

		}

		public String VuCofinsOriginalDbColumnName() {

			return "VuCofins";

		}

		public Short Altdtprev;

		public Short getAltdtprev() {
			return this.Altdtprev;
		}

		public Boolean AltdtprevIsNullable() {
			return true;
		}

		public Boolean AltdtprevIsKey() {
			return false;
		}

		public Integer AltdtprevLength() {
			return 5;
		}

		public Integer AltdtprevPrecision() {
			return 0;
		}

		public String AltdtprevDefault() {

			return "";

		}

		public String AltdtprevComment() {

			return "";

		}

		public String AltdtprevPattern() {

			return "";

		}

		public String AltdtprevOriginalDbColumnName() {

			return "Altdtprev";

		}

		public Double QtdeAloc;

		public Double getQtdeAloc() {
			return this.QtdeAloc;
		}

		public Boolean QtdeAlocIsNullable() {
			return true;
		}

		public Boolean QtdeAlocIsKey() {
			return false;
		}

		public Integer QtdeAlocLength() {
			return 15;
		}

		public Integer QtdeAlocPrecision() {
			return 0;
		}

		public String QtdeAlocDefault() {

			return "";

		}

		public String QtdeAlocComment() {

			return "";

		}

		public String QtdeAlocPattern() {

			return "";

		}

		public String QtdeAlocOriginalDbColumnName() {

			return "QtdeAloc";

		}

		public String RestrBonif;

		public String getRestrBonif() {
			return this.RestrBonif;
		}

		public Boolean RestrBonifIsNullable() {
			return true;
		}

		public Boolean RestrBonifIsKey() {
			return false;
		}

		public Integer RestrBonifLength() {
			return 5;
		}

		public Integer RestrBonifPrecision() {
			return 0;
		}

		public String RestrBonifDefault() {

			return "";

		}

		public String RestrBonifComment() {

			return "";

		}

		public String RestrBonifPattern() {

			return "";

		}

		public String RestrBonifOriginalDbColumnName() {

			return "RestrBonif";

		}

		public Double CustoMo;

		public Double getCustoMo() {
			return this.CustoMo;
		}

		public Boolean CustoMoIsNullable() {
			return true;
		}

		public Boolean CustoMoIsKey() {
			return false;
		}

		public Integer CustoMoLength() {
			return 15;
		}

		public Integer CustoMoPrecision() {
			return 0;
		}

		public String CustoMoDefault() {

			return "";

		}

		public String CustoMoComment() {

			return "";

		}

		public String CustoMoPattern() {

			return "";

		}

		public String CustoMoOriginalDbColumnName() {

			return "CustoMo";

		}

		public Short ItControl;

		public Short getItControl() {
			return this.ItControl;
		}

		public Boolean ItControlIsNullable() {
			return true;
		}

		public Boolean ItControlIsKey() {
			return false;
		}

		public Integer ItControlLength() {
			return 5;
		}

		public Integer ItControlPrecision() {
			return 0;
		}

		public String ItControlDefault() {

			return "";

		}

		public String ItControlComment() {

			return "";

		}

		public String ItControlPattern() {

			return "";

		}

		public String ItControlOriginalDbColumnName() {

			return "ItControl";

		}

		public Short ItPermBon;

		public Short getItPermBon() {
			return this.ItPermBon;
		}

		public Boolean ItPermBonIsNullable() {
			return true;
		}

		public Boolean ItPermBonIsKey() {
			return false;
		}

		public Integer ItPermBonLength() {
			return 5;
		}

		public Integer ItPermBonPrecision() {
			return 0;
		}

		public String ItPermBonDefault() {

			return "";

		}

		public String ItPermBonComment() {

			return "";

		}

		public String ItPermBonPattern() {

			return "";

		}

		public String ItPermBonOriginalDbColumnName() {

			return "ItPermBon";

		}

		public Double QtdPermBon;

		public Double getQtdPermBon() {
			return this.QtdPermBon;
		}

		public Boolean QtdPermBonIsNullable() {
			return true;
		}

		public Boolean QtdPermBonIsKey() {
			return false;
		}

		public Integer QtdPermBonLength() {
			return 15;
		}

		public Integer QtdPermBonPrecision() {
			return 0;
		}

		public String QtdPermBonDefault() {

			return "";

		}

		public String QtdPermBonComment() {

			return "";

		}

		public String QtdPermBonPattern() {

			return "";

		}

		public String QtdPermBonOriginalDbColumnName() {

			return "QtdPermBon";

		}

		public Double VlrPermBon;

		public Double getVlrPermBon() {
			return this.VlrPermBon;
		}

		public Boolean VlrPermBonIsNullable() {
			return true;
		}

		public Boolean VlrPermBonIsKey() {
			return false;
		}

		public Integer VlrPermBonLength() {
			return 15;
		}

		public Integer VlrPermBonPrecision() {
			return 0;
		}

		public String VlrPermBonDefault() {

			return "";

		}

		public String VlrPermBonComment() {

			return "";

		}

		public String VlrPermBonPattern() {

			return "";

		}

		public String VlrPermBonOriginalDbColumnName() {

			return "VlrPermBon";

		}

		public Short Itemfemb;

		public Short getItemfemb() {
			return this.Itemfemb;
		}

		public Boolean ItemfembIsNullable() {
			return true;
		}

		public Boolean ItemfembIsKey() {
			return false;
		}

		public Integer ItemfembLength() {
			return 5;
		}

		public Integer ItemfembPrecision() {
			return 0;
		}

		public String ItemfembDefault() {

			return "";

		}

		public String ItemfembComment() {

			return "";

		}

		public String ItemfembPattern() {

			return "";

		}

		public String ItemfembOriginalDbColumnName() {

			return "Itemfemb";

		}

		public java.util.Date Dtprevento;

		public java.util.Date getDtprevento() {
			return this.Dtprevento;
		}

		public Boolean DtpreventoIsNullable() {
			return true;
		}

		public Boolean DtpreventoIsKey() {
			return false;
		}

		public Integer DtpreventoLength() {
			return 23;
		}

		public Integer DtpreventoPrecision() {
			return 3;
		}

		public String DtpreventoDefault() {

			return "";

		}

		public String DtpreventoComment() {

			return "";

		}

		public String DtpreventoPattern() {

			return "dd-MM-yyyy";

		}

		public String DtpreventoOriginalDbColumnName() {

			return "Dtprevento";

		}

		public String Cfretit;

		public String getCfretit() {
			return this.Cfretit;
		}

		public Boolean CfretitIsNullable() {
			return true;
		}

		public Boolean CfretitIsKey() {
			return false;
		}

		public Integer CfretitLength() {
			return 4;
		}

		public Integer CfretitPrecision() {
			return 0;
		}

		public String CfretitDefault() {

			return "";

		}

		public String CfretitComment() {

			return "";

		}

		public String CfretitPattern() {

			return "";

		}

		public String CfretitOriginalDbColumnName() {

			return "Cfretit";

		}

		public Double SldVerba;

		public Double getSldVerba() {
			return this.SldVerba;
		}

		public Boolean SldVerbaIsNullable() {
			return true;
		}

		public Boolean SldVerbaIsKey() {
			return false;
		}

		public Integer SldVerbaLength() {
			return 15;
		}

		public Integer SldVerbaPrecision() {
			return 0;
		}

		public String SldVerbaDefault() {

			return "";

		}

		public String SldVerbaComment() {

			return "";

		}

		public String SldVerbaPattern() {

			return "";

		}

		public String SldVerbaOriginalDbColumnName() {

			return "SldVerba";

		}

		public Double VlrBonif;

		public Double getVlrBonif() {
			return this.VlrBonif;
		}

		public Boolean VlrBonifIsNullable() {
			return true;
		}

		public Boolean VlrBonifIsKey() {
			return false;
		}

		public Integer VlrBonifLength() {
			return 15;
		}

		public Integer VlrBonifPrecision() {
			return 0;
		}

		public String VlrBonifDefault() {

			return "";

		}

		public String VlrBonifComment() {

			return "";

		}

		public String VlrBonifPattern() {

			return "";

		}

		public String VlrBonifOriginalDbColumnName() {

			return "VlrBonif";

		}

		public Short Bonif;

		public Short getBonif() {
			return this.Bonif;
		}

		public Boolean BonifIsNullable() {
			return true;
		}

		public Boolean BonifIsKey() {
			return false;
		}

		public Integer BonifLength() {
			return 5;
		}

		public Integer BonifPrecision() {
			return 0;
		}

		public String BonifDefault() {

			return "";

		}

		public String BonifComment() {

			return "";

		}

		public String BonifPattern() {

			return "";

		}

		public String BonifOriginalDbColumnName() {

			return "Bonif";

		}

		public Short Tpproduto;

		public Short getTpproduto() {
			return this.Tpproduto;
		}

		public Boolean TpprodutoIsNullable() {
			return true;
		}

		public Boolean TpprodutoIsKey() {
			return false;
		}

		public Integer TpprodutoLength() {
			return 5;
		}

		public Integer TpprodutoPrecision() {
			return 0;
		}

		public String TpprodutoDefault() {

			return "";

		}

		public String TpprodutoComment() {

			return "";

		}

		public String TpprodutoPattern() {

			return "";

		}

		public String TpprodutoOriginalDbColumnName() {

			return "Tpproduto";

		}

		public String Backord;

		public String getBackord() {
			return this.Backord;
		}

		public Boolean BackordIsNullable() {
			return true;
		}

		public Boolean BackordIsKey() {
			return false;
		}

		public Integer BackordLength() {
			return 3;
		}

		public Integer BackordPrecision() {
			return 0;
		}

		public String BackordDefault() {

			return "";

		}

		public String BackordComment() {

			return "";

		}

		public String BackordPattern() {

			return "";

		}

		public String BackordOriginalDbColumnName() {

			return "Backord";

		}

		public Double Markup;

		public Double getMarkup() {
			return this.Markup;
		}

		public Boolean MarkupIsNullable() {
			return true;
		}

		public Boolean MarkupIsKey() {
			return false;
		}

		public Integer MarkupLength() {
			return 15;
		}

		public Integer MarkupPrecision() {
			return 0;
		}

		public String MarkupDefault() {

			return "";

		}

		public String MarkupComment() {

			return "";

		}

		public String MarkupPattern() {

			return "";

		}

		public String MarkupOriginalDbColumnName() {

			return "Markup";

		}

		public Double QtdeBkor;

		public Double getQtdeBkor() {
			return this.QtdeBkor;
		}

		public Boolean QtdeBkorIsNullable() {
			return true;
		}

		public Boolean QtdeBkorIsKey() {
			return false;
		}

		public Integer QtdeBkorLength() {
			return 15;
		}

		public Integer QtdeBkorPrecision() {
			return 0;
		}

		public String QtdeBkorDefault() {

			return "";

		}

		public String QtdeBkorComment() {

			return "";

		}

		public String QtdeBkorPattern() {

			return "";

		}

		public String QtdeBkorOriginalDbColumnName() {

			return "QtdeBkor";

		}

		public Double Prcpromoc;

		public Double getPrcpromoc() {
			return this.Prcpromoc;
		}

		public Boolean PrcpromocIsNullable() {
			return true;
		}

		public Boolean PrcpromocIsKey() {
			return false;
		}

		public Integer PrcpromocLength() {
			return 15;
		}

		public Integer PrcpromocPrecision() {
			return 0;
		}

		public String PrcpromocDefault() {

			return "";

		}

		public String PrcpromocComment() {

			return "";

		}

		public String PrcpromocPattern() {

			return "";

		}

		public String PrcpromocOriginalDbColumnName() {

			return "Prcpromoc";

		}

		public String Sitcorp1;

		public String getSitcorp1() {
			return this.Sitcorp1;
		}

		public Boolean Sitcorp1IsNullable() {
			return true;
		}

		public Boolean Sitcorp1IsKey() {
			return false;
		}

		public Integer Sitcorp1Length() {
			return 6;
		}

		public Integer Sitcorp1Precision() {
			return 0;
		}

		public String Sitcorp1Default() {

			return "";

		}

		public String Sitcorp1Comment() {

			return "";

		}

		public String Sitcorp1Pattern() {

			return "";

		}

		public String Sitcorp1OriginalDbColumnName() {

			return "Sitcorp1";

		}

		public String Sitcorp2;

		public String getSitcorp2() {
			return this.Sitcorp2;
		}

		public Boolean Sitcorp2IsNullable() {
			return true;
		}

		public Boolean Sitcorp2IsKey() {
			return false;
		}

		public Integer Sitcorp2Length() {
			return 6;
		}

		public Integer Sitcorp2Precision() {
			return 0;
		}

		public String Sitcorp2Default() {

			return "";

		}

		public String Sitcorp2Comment() {

			return "";

		}

		public String Sitcorp2Pattern() {

			return "";

		}

		public String Sitcorp2OriginalDbColumnName() {

			return "Sitcorp2";

		}

		public String LibMots;

		public String getLibMots() {
			return this.LibMots;
		}

		public Boolean LibMotsIsNullable() {
			return true;
		}

		public Boolean LibMotsIsKey() {
			return false;
		}

		public Integer LibMotsLength() {
			return 255;
		}

		public Integer LibMotsPrecision() {
			return 0;
		}

		public String LibMotsDefault() {

			return "";

		}

		public String LibMotsComment() {

			return "";

		}

		public String LibMotsPattern() {

			return "";

		}

		public String LibMotsOriginalDbColumnName() {

			return "LibMots";

		}

		public Double Dctoboleto;

		public Double getDctoboleto() {
			return this.Dctoboleto;
		}

		public Boolean DctoboletoIsNullable() {
			return true;
		}

		public Boolean DctoboletoIsKey() {
			return false;
		}

		public Integer DctoboletoLength() {
			return 15;
		}

		public Integer DctoboletoPrecision() {
			return 0;
		}

		public String DctoboletoDefault() {

			return "";

		}

		public String DctoboletoComment() {

			return "";

		}

		public String DctoboletoPattern() {

			return "";

		}

		public String DctoboletoOriginalDbColumnName() {

			return "Dctoboleto";

		}

		public Integer MPRISeq;

		public Integer getMPRISeq() {
			return this.MPRISeq;
		}

		public Boolean MPRISeqIsNullable() {
			return true;
		}

		public Boolean MPRISeqIsKey() {
			return false;
		}

		public Integer MPRISeqLength() {
			return 10;
		}

		public Integer MPRISeqPrecision() {
			return 0;
		}

		public String MPRISeqDefault() {

			return "";

		}

		public String MPRISeqComment() {

			return "";

		}

		public String MPRISeqPattern() {

			return "";

		}

		public String MPRISeqOriginalDbColumnName() {

			return "MPRISeq";

		}

		public Double Dctomax;

		public Double getDctomax() {
			return this.Dctomax;
		}

		public Boolean DctomaxIsNullable() {
			return true;
		}

		public Boolean DctomaxIsKey() {
			return false;
		}

		public Integer DctomaxLength() {
			return 15;
		}

		public Integer DctomaxPrecision() {
			return 0;
		}

		public String DctomaxDefault() {

			return "";

		}

		public String DctomaxComment() {

			return "";

		}

		public String DctomaxPattern() {

			return "";

		}

		public String DctomaxOriginalDbColumnName() {

			return "Dctomax";

		}

		public Double Comprim;

		public Double getComprim() {
			return this.Comprim;
		}

		public Boolean ComprimIsNullable() {
			return true;
		}

		public Boolean ComprimIsKey() {
			return false;
		}

		public Integer ComprimLength() {
			return 15;
		}

		public Integer ComprimPrecision() {
			return 0;
		}

		public String ComprimDefault() {

			return "";

		}

		public String ComprimComment() {

			return "";

		}

		public String ComprimPattern() {

			return "";

		}

		public String ComprimOriginalDbColumnName() {

			return "Comprim";

		}

		public Double QtdePeca;

		public Double getQtdePeca() {
			return this.QtdePeca;
		}

		public Boolean QtdePecaIsNullable() {
			return true;
		}

		public Boolean QtdePecaIsKey() {
			return false;
		}

		public Integer QtdePecaLength() {
			return 15;
		}

		public Integer QtdePecaPrecision() {
			return 0;
		}

		public String QtdePecaDefault() {

			return "";

		}

		public String QtdePecaComment() {

			return "";

		}

		public String QtdePecaPattern() {

			return "";

		}

		public String QtdePecaOriginalDbColumnName() {

			return "QtdePeca";

		}

		public String ProdRes;

		public String getProdRes() {
			return this.ProdRes;
		}

		public Boolean ProdResIsNullable() {
			return true;
		}

		public Boolean ProdResIsKey() {
			return false;
		}

		public Integer ProdResLength() {
			return 16;
		}

		public Integer ProdResPrecision() {
			return 0;
		}

		public String ProdResDefault() {

			return "";

		}

		public String ProdResComment() {

			return "";

		}

		public String ProdResPattern() {

			return "";

		}

		public String ProdResOriginalDbColumnName() {

			return "ProdRes";

		}

		public String EmpEstoq;

		public String getEmpEstoq() {
			return this.EmpEstoq;
		}

		public Boolean EmpEstoqIsNullable() {
			return true;
		}

		public Boolean EmpEstoqIsKey() {
			return false;
		}

		public Integer EmpEstoqLength() {
			return 3;
		}

		public Integer EmpEstoqPrecision() {
			return 0;
		}

		public String EmpEstoqDefault() {

			return "";

		}

		public String EmpEstoqComment() {

			return "";

		}

		public String EmpEstoqPattern() {

			return "";

		}

		public String EmpEstoqOriginalDbColumnName() {

			return "EmpEstoq";

		}

		public Double PrevProduc;

		public Double getPrevProduc() {
			return this.PrevProduc;
		}

		public Boolean PrevProducIsNullable() {
			return true;
		}

		public Boolean PrevProducIsKey() {
			return false;
		}

		public Integer PrevProducLength() {
			return 15;
		}

		public Integer PrevProducPrecision() {
			return 0;
		}

		public String PrevProducDefault() {

			return "";

		}

		public String PrevProducComment() {

			return "";

		}

		public String PrevProducPattern() {

			return "";

		}

		public String PrevProducOriginalDbColumnName() {

			return "PrevProduc";

		}

		public Float PrunitOri;

		public Float getPrunitOri() {
			return this.PrunitOri;
		}

		public Boolean PrunitOriIsNullable() {
			return true;
		}

		public Boolean PrunitOriIsKey() {
			return false;
		}

		public Integer PrunitOriLength() {
			return 7;
		}

		public Integer PrunitOriPrecision() {
			return 0;
		}

		public String PrunitOriDefault() {

			return "";

		}

		public String PrunitOriComment() {

			return "";

		}

		public String PrunitOriPattern() {

			return "";

		}

		public String PrunitOriOriginalDbColumnName() {

			return "PrunitOri";

		}

		public Float PrliqOri;

		public Float getPrliqOri() {
			return this.PrliqOri;
		}

		public Boolean PrliqOriIsNullable() {
			return true;
		}

		public Boolean PrliqOriIsKey() {
			return false;
		}

		public Integer PrliqOriLength() {
			return 7;
		}

		public Integer PrliqOriPrecision() {
			return 0;
		}

		public String PrliqOriDefault() {

			return "";

		}

		public String PrliqOriComment() {

			return "";

		}

		public String PrliqOriPattern() {

			return "";

		}

		public String PrliqOriOriginalDbColumnName() {

			return "PrliqOri";

		}

		public Double Mva;

		public Double getMva() {
			return this.Mva;
		}

		public Boolean MvaIsNullable() {
			return true;
		}

		public Boolean MvaIsKey() {
			return false;
		}

		public Integer MvaLength() {
			return 15;
		}

		public Integer MvaPrecision() {
			return 0;
		}

		public String MvaDefault() {

			return "";

		}

		public String MvaComment() {

			return "";

		}

		public String MvaPattern() {

			return "";

		}

		public String MvaOriginalDbColumnName() {

			return "Mva";

		}

		public Double ICMSOrig;

		public Double getICMSOrig() {
			return this.ICMSOrig;
		}

		public Boolean ICMSOrigIsNullable() {
			return true;
		}

		public Boolean ICMSOrigIsKey() {
			return false;
		}

		public Integer ICMSOrigLength() {
			return 15;
		}

		public Integer ICMSOrigPrecision() {
			return 0;
		}

		public String ICMSOrigDefault() {

			return "";

		}

		public String ICMSOrigComment() {

			return "";

		}

		public String ICMSOrigPattern() {

			return "";

		}

		public String ICMSOrigOriginalDbColumnName() {

			return "ICMSOrig";

		}

		public Double ICMSDest;

		public Double getICMSDest() {
			return this.ICMSDest;
		}

		public Boolean ICMSDestIsNullable() {
			return true;
		}

		public Boolean ICMSDestIsKey() {
			return false;
		}

		public Integer ICMSDestLength() {
			return 15;
		}

		public Integer ICMSDestPrecision() {
			return 0;
		}

		public String ICMSDestDefault() {

			return "";

		}

		public String ICMSDestComment() {

			return "";

		}

		public String ICMSDestPattern() {

			return "";

		}

		public String ICMSDestOriginalDbColumnName() {

			return "ICMSDest";

		}

		public Double Float1;

		public Double getFloat1() {
			return this.Float1;
		}

		public Boolean Float1IsNullable() {
			return true;
		}

		public Boolean Float1IsKey() {
			return false;
		}

		public Integer Float1Length() {
			return 15;
		}

		public Integer Float1Precision() {
			return 0;
		}

		public String Float1Default() {

			return "";

		}

		public String Float1Comment() {

			return "";

		}

		public String Float1Pattern() {

			return "";

		}

		public String Float1OriginalDbColumnName() {

			return "Float1";

		}

		public Double Float2;

		public Double getFloat2() {
			return this.Float2;
		}

		public Boolean Float2IsNullable() {
			return true;
		}

		public Boolean Float2IsKey() {
			return false;
		}

		public Integer Float2Length() {
			return 15;
		}

		public Integer Float2Precision() {
			return 0;
		}

		public String Float2Default() {

			return "";

		}

		public String Float2Comment() {

			return "";

		}

		public String Float2Pattern() {

			return "";

		}

		public String Float2OriginalDbColumnName() {

			return "Float2";

		}

		public java.util.Date DataAlter;

		public java.util.Date getDataAlter() {
			return this.DataAlter;
		}

		public Boolean DataAlterIsNullable() {
			return true;
		}

		public Boolean DataAlterIsKey() {
			return false;
		}

		public Integer DataAlterLength() {
			return 23;
		}

		public Integer DataAlterPrecision() {
			return 3;
		}

		public String DataAlterDefault() {

			return "";

		}

		public String DataAlterComment() {

			return "";

		}

		public String DataAlterPattern() {

			return "dd-MM-yyyy";

		}

		public String DataAlterOriginalDbColumnName() {

			return "DataAlter";

		}

		public String SeqOrig;

		public String getSeqOrig() {
			return this.SeqOrig;
		}

		public Boolean SeqOrigIsNullable() {
			return true;
		}

		public Boolean SeqOrigIsKey() {
			return false;
		}

		public Integer SeqOrigLength() {
			return 12;
		}

		public Integer SeqOrigPrecision() {
			return 0;
		}

		public String SeqOrigDefault() {

			return "";

		}

		public String SeqOrigComment() {

			return "";

		}

		public String SeqOrigPattern() {

			return "";

		}

		public String SeqOrigOriginalDbColumnName() {

			return "SeqOrig";

		}

		public Double PrecoNorm;

		public Double getPrecoNorm() {
			return this.PrecoNorm;
		}

		public Boolean PrecoNormIsNullable() {
			return true;
		}

		public Boolean PrecoNormIsKey() {
			return false;
		}

		public Integer PrecoNormLength() {
			return 15;
		}

		public Integer PrecoNormPrecision() {
			return 0;
		}

		public String PrecoNormDefault() {

			return "";

		}

		public String PrecoNormComment() {

			return "";

		}

		public String PrecoNormPattern() {

			return "";

		}

		public String PrecoNormOriginalDbColumnName() {

			return "PrecoNorm";

		}

		public Short Tipost;

		public Short getTipost() {
			return this.Tipost;
		}

		public Boolean TipostIsNullable() {
			return true;
		}

		public Boolean TipostIsKey() {
			return false;
		}

		public Integer TipostLength() {
			return 5;
		}

		public Integer TipostPrecision() {
			return 0;
		}

		public String TipostDefault() {

			return "";

		}

		public String TipostComment() {

			return "";

		}

		public String TipostPattern() {

			return "";

		}

		public String TipostOriginalDbColumnName() {

			return "Tipost";

		}

		public String CodProdcli;

		public String getCodProdcli() {
			return this.CodProdcli;
		}

		public Boolean CodProdcliIsNullable() {
			return true;
		}

		public Boolean CodProdcliIsKey() {
			return false;
		}

		public Integer CodProdcliLength() {
			return 20;
		}

		public Integer CodProdcliPrecision() {
			return 0;
		}

		public String CodProdcliDefault() {

			return "";

		}

		public String CodProdcliComment() {

			return "";

		}

		public String CodProdcliPattern() {

			return "";

		}

		public String CodProdcliOriginalDbColumnName() {

			return "CodProdcli";

		}

		public Double VlrDescarga;

		public Double getVlrDescarga() {
			return this.VlrDescarga;
		}

		public Boolean VlrDescargaIsNullable() {
			return true;
		}

		public Boolean VlrDescargaIsKey() {
			return false;
		}

		public Integer VlrDescargaLength() {
			return 15;
		}

		public Integer VlrDescargaPrecision() {
			return 0;
		}

		public String VlrDescargaDefault() {

			return "";

		}

		public String VlrDescargaComment() {

			return "";

		}

		public String VlrDescargaPattern() {

			return "";

		}

		public String VlrDescargaOriginalDbColumnName() {

			return "VlrDescarga";

		}

		public Short IcmsDscto;

		public Short getIcmsDscto() {
			return this.IcmsDscto;
		}

		public Boolean IcmsDsctoIsNullable() {
			return true;
		}

		public Boolean IcmsDsctoIsKey() {
			return false;
		}

		public Integer IcmsDsctoLength() {
			return 5;
		}

		public Integer IcmsDsctoPrecision() {
			return 0;
		}

		public String IcmsDsctoDefault() {

			return "";

		}

		public String IcmsDsctoComment() {

			return "";

		}

		public String IcmsDsctoPattern() {

			return "";

		}

		public String IcmsDsctoOriginalDbColumnName() {

			return "IcmsDscto";

		}

		public String EmpSplit;

		public String getEmpSplit() {
			return this.EmpSplit;
		}

		public Boolean EmpSplitIsNullable() {
			return true;
		}

		public Boolean EmpSplitIsKey() {
			return false;
		}

		public Integer EmpSplitLength() {
			return 3;
		}

		public Integer EmpSplitPrecision() {
			return 0;
		}

		public String EmpSplitDefault() {

			return "";

		}

		public String EmpSplitComment() {

			return "";

		}

		public String EmpSplitPattern() {

			return "";

		}

		public String EmpSplitOriginalDbColumnName() {

			return "EmpSplit";

		}

		public Integer Status;

		public Integer getStatus() {
			return this.Status;
		}

		public Boolean StatusIsNullable() {
			return true;
		}

		public Boolean StatusIsKey() {
			return false;
		}

		public Integer StatusLength() {
			return 10;
		}

		public Integer StatusPrecision() {
			return 0;
		}

		public String StatusDefault() {

			return "";

		}

		public String StatusComment() {

			return "";

		}

		public String StatusPattern() {

			return "";

		}

		public String StatusOriginalDbColumnName() {

			return "Status";

		}

		public Integer Obrigatorio;

		public Integer getObrigatorio() {
			return this.Obrigatorio;
		}

		public Boolean ObrigatorioIsNullable() {
			return true;
		}

		public Boolean ObrigatorioIsKey() {
			return false;
		}

		public Integer ObrigatorioLength() {
			return 10;
		}

		public Integer ObrigatorioPrecision() {
			return 0;
		}

		public String ObrigatorioDefault() {

			return "";

		}

		public String ObrigatorioComment() {

			return "";

		}

		public String ObrigatorioPattern() {

			return "";

		}

		public String ObrigatorioOriginalDbColumnName() {

			return "Obrigatorio";

		}

		public Integer PrecoFirme;

		public Integer getPrecoFirme() {
			return this.PrecoFirme;
		}

		public Boolean PrecoFirmeIsNullable() {
			return true;
		}

		public Boolean PrecoFirmeIsKey() {
			return false;
		}

		public Integer PrecoFirmeLength() {
			return 10;
		}

		public Integer PrecoFirmePrecision() {
			return 0;
		}

		public String PrecoFirmeDefault() {

			return "";

		}

		public String PrecoFirmeComment() {

			return "";

		}

		public String PrecoFirmePattern() {

			return "";

		}

		public String PrecoFirmeOriginalDbColumnName() {

			return "PrecoFirme";

		}

		public String OrdCompra;

		public String getOrdCompra() {
			return this.OrdCompra;
		}

		public Boolean OrdCompraIsNullable() {
			return true;
		}

		public Boolean OrdCompraIsKey() {
			return false;
		}

		public Integer OrdCompraLength() {
			return 25;
		}

		public Integer OrdCompraPrecision() {
			return 0;
		}

		public String OrdCompraDefault() {

			return "";

		}

		public String OrdCompraComment() {

			return "";

		}

		public String OrdCompraPattern() {

			return "";

		}

		public String OrdCompraOriginalDbColumnName() {

			return "OrdCompra";

		}

		public Double DesctoFiscal;

		public Double getDesctoFiscal() {
			return this.DesctoFiscal;
		}

		public Boolean DesctoFiscalIsNullable() {
			return true;
		}

		public Boolean DesctoFiscalIsKey() {
			return false;
		}

		public Integer DesctoFiscalLength() {
			return 15;
		}

		public Integer DesctoFiscalPrecision() {
			return 0;
		}

		public String DesctoFiscalDefault() {

			return "";

		}

		public String DesctoFiscalComment() {

			return "";

		}

		public String DesctoFiscalPattern() {

			return "";

		}

		public String DesctoFiscalOriginalDbColumnName() {

			return "DesctoFiscal";

		}

		public Double PercMlucr;

		public Double getPercMlucr() {
			return this.PercMlucr;
		}

		public Boolean PercMlucrIsNullable() {
			return true;
		}

		public Boolean PercMlucrIsKey() {
			return false;
		}

		public Integer PercMlucrLength() {
			return 15;
		}

		public Integer PercMlucrPrecision() {
			return 0;
		}

		public String PercMlucrDefault() {

			return "";

		}

		public String PercMlucrComment() {

			return "";

		}

		public String PercMlucrPattern() {

			return "";

		}

		public String PercMlucrOriginalDbColumnName() {

			return "PercMlucr";

		}

		public Double VlrDescargaCalc;

		public Double getVlrDescargaCalc() {
			return this.VlrDescargaCalc;
		}

		public Boolean VlrDescargaCalcIsNullable() {
			return true;
		}

		public Boolean VlrDescargaCalcIsKey() {
			return false;
		}

		public Integer VlrDescargaCalcLength() {
			return 15;
		}

		public Integer VlrDescargaCalcPrecision() {
			return 0;
		}

		public String VlrDescargaCalcDefault() {

			return "";

		}

		public String VlrDescargaCalcComment() {

			return "";

		}

		public String VlrDescargaCalcPattern() {

			return "";

		}

		public String VlrDescargaCalcOriginalDbColumnName() {

			return "VlrDescargaCalc";

		}

		public Double VlrFreTitCalc;

		public Double getVlrFreTitCalc() {
			return this.VlrFreTitCalc;
		}

		public Boolean VlrFreTitCalcIsNullable() {
			return true;
		}

		public Boolean VlrFreTitCalcIsKey() {
			return false;
		}

		public Integer VlrFreTitCalcLength() {
			return 15;
		}

		public Integer VlrFreTitCalcPrecision() {
			return 0;
		}

		public String VlrFreTitCalcDefault() {

			return "";

		}

		public String VlrFreTitCalcComment() {

			return "";

		}

		public String VlrFreTitCalcPattern() {

			return "";

		}

		public String VlrFreTitCalcOriginalDbColumnName() {

			return "VlrFreTitCalc";

		}

		public Double VuComissao;

		public Double getVuComissao() {
			return this.VuComissao;
		}

		public Boolean VuComissaoIsNullable() {
			return true;
		}

		public Boolean VuComissaoIsKey() {
			return false;
		}

		public Integer VuComissaoLength() {
			return 15;
		}

		public Integer VuComissaoPrecision() {
			return 0;
		}

		public String VuComissaoDefault() {

			return "";

		}

		public String VuComissaoComment() {

			return "";

		}

		public String VuComissaoPattern() {

			return "";

		}

		public String VuComissaoOriginalDbColumnName() {

			return "VuComissao";

		}

		public Double VuDebcli;

		public Double getVuDebcli() {
			return this.VuDebcli;
		}

		public Boolean VuDebcliIsNullable() {
			return true;
		}

		public Boolean VuDebcliIsKey() {
			return false;
		}

		public Integer VuDebcliLength() {
			return 15;
		}

		public Integer VuDebcliPrecision() {
			return 0;
		}

		public String VuDebcliDefault() {

			return "";

		}

		public String VuDebcliComment() {

			return "";

		}

		public String VuDebcliPattern() {

			return "";

		}

		public String VuDebcliOriginalDbColumnName() {

			return "VuDebcli";

		}

		public java.util.Date Data1Original;

		public java.util.Date getData1Original() {
			return this.Data1Original;
		}

		public Boolean Data1OriginalIsNullable() {
			return true;
		}

		public Boolean Data1OriginalIsKey() {
			return false;
		}

		public Integer Data1OriginalLength() {
			return 23;
		}

		public Integer Data1OriginalPrecision() {
			return 3;
		}

		public String Data1OriginalDefault() {

			return "";

		}

		public String Data1OriginalComment() {

			return "";

		}

		public String Data1OriginalPattern() {

			return "dd-MM-yyyy";

		}

		public String Data1OriginalOriginalDbColumnName() {

			return "Data1Original";

		}

		public Integer Visao;

		public Integer getVisao() {
			return this.Visao;
		}

		public Boolean VisaoIsNullable() {
			return true;
		}

		public Boolean VisaoIsKey() {
			return false;
		}

		public Integer VisaoLength() {
			return 10;
		}

		public Integer VisaoPrecision() {
			return 0;
		}

		public String VisaoDefault() {

			return "";

		}

		public String VisaoComment() {

			return "";

		}

		public String VisaoPattern() {

			return "";

		}

		public String VisaoOriginalDbColumnName() {

			return "Visao";

		}

		public Integer Periodo;

		public Integer getPeriodo() {
			return this.Periodo;
		}

		public Boolean PeriodoIsNullable() {
			return true;
		}

		public Boolean PeriodoIsKey() {
			return false;
		}

		public Integer PeriodoLength() {
			return 10;
		}

		public Integer PeriodoPrecision() {
			return 0;
		}

		public String PeriodoDefault() {

			return "";

		}

		public String PeriodoComment() {

			return "";

		}

		public String PeriodoPattern() {

			return "";

		}

		public String PeriodoOriginalDbColumnName() {

			return "Periodo";

		}

		public Double BaseIcms;

		public Double getBaseIcms() {
			return this.BaseIcms;
		}

		public Boolean BaseIcmsIsNullable() {
			return true;
		}

		public Boolean BaseIcmsIsKey() {
			return false;
		}

		public Integer BaseIcmsLength() {
			return 15;
		}

		public Integer BaseIcmsPrecision() {
			return 0;
		}

		public String BaseIcmsDefault() {

			return "";

		}

		public String BaseIcmsComment() {

			return "";

		}

		public String BaseIcmsPattern() {

			return "";

		}

		public String BaseIcmsOriginalDbColumnName() {

			return "BaseIcms";

		}

		public Double BaseSt;

		public Double getBaseSt() {
			return this.BaseSt;
		}

		public Boolean BaseStIsNullable() {
			return true;
		}

		public Boolean BaseStIsKey() {
			return false;
		}

		public Integer BaseStLength() {
			return 15;
		}

		public Integer BaseStPrecision() {
			return 0;
		}

		public String BaseStDefault() {

			return "";

		}

		public String BaseStComment() {

			return "";

		}

		public String BaseStPattern() {

			return "";

		}

		public String BaseStOriginalDbColumnName() {

			return "BaseSt";

		}

		public Integer CondPgto;

		public Integer getCondPgto() {
			return this.CondPgto;
		}

		public Boolean CondPgtoIsNullable() {
			return true;
		}

		public Boolean CondPgtoIsKey() {
			return false;
		}

		public Integer CondPgtoLength() {
			return 10;
		}

		public Integer CondPgtoPrecision() {
			return 0;
		}

		public String CondPgtoDefault() {

			return "";

		}

		public String CondPgtoComment() {

			return "";

		}

		public String CondPgtoPattern() {

			return "";

		}

		public String CondPgtoOriginalDbColumnName() {

			return "CondPgto";

		}

		public String UsuCanc;

		public String getUsuCanc() {
			return this.UsuCanc;
		}

		public Boolean UsuCancIsNullable() {
			return true;
		}

		public Boolean UsuCancIsKey() {
			return false;
		}

		public Integer UsuCancLength() {
			return 20;
		}

		public Integer UsuCancPrecision() {
			return 0;
		}

		public String UsuCancDefault() {

			return "";

		}

		public String UsuCancComment() {

			return "";

		}

		public String UsuCancPattern() {

			return "";

		}

		public String UsuCancOriginalDbColumnName() {

			return "UsuCanc";

		}

		public Double Resrede;

		public Double getResrede() {
			return this.Resrede;
		}

		public Boolean ResredeIsNullable() {
			return true;
		}

		public Boolean ResredeIsKey() {
			return false;
		}

		public Integer ResredeLength() {
			return 15;
		}

		public Integer ResredePrecision() {
			return 0;
		}

		public String ResredeDefault() {

			return "";

		}

		public String ResredeComment() {

			return "";

		}

		public String ResredePattern() {

			return "";

		}

		public String ResredeOriginalDbColumnName() {

			return "Resrede";

		}

		public String DocBloq;

		public String getDocBloq() {
			return this.DocBloq;
		}

		public Boolean DocBloqIsNullable() {
			return true;
		}

		public Boolean DocBloqIsKey() {
			return false;
		}

		public Integer DocBloqLength() {
			return 100;
		}

		public Integer DocBloqPrecision() {
			return 0;
		}

		public String DocBloqDefault() {

			return "";

		}

		public String DocBloqComment() {

			return "";

		}

		public String DocBloqPattern() {

			return "";

		}

		public String DocBloqOriginalDbColumnName() {

			return "DocBloq";

		}

		public String Regracomis;

		public String getRegracomis() {
			return this.Regracomis;
		}

		public Boolean RegracomisIsNullable() {
			return true;
		}

		public Boolean RegracomisIsKey() {
			return false;
		}

		public Integer RegracomisLength() {
			return 8;
		}

		public Integer RegracomisPrecision() {
			return 0;
		}

		public String RegracomisDefault() {

			return "";

		}

		public String RegracomisComment() {

			return "";

		}

		public String RegracomisPattern() {

			return "";

		}

		public String RegracomisOriginalDbColumnName() {

			return "Regracomis";

		}

		public Double BaseIcmsRed;

		public Double getBaseIcmsRed() {
			return this.BaseIcmsRed;
		}

		public Boolean BaseIcmsRedIsNullable() {
			return true;
		}

		public Boolean BaseIcmsRedIsKey() {
			return false;
		}

		public Integer BaseIcmsRedLength() {
			return 15;
		}

		public Integer BaseIcmsRedPrecision() {
			return 0;
		}

		public String BaseIcmsRedDefault() {

			return "";

		}

		public String BaseIcmsRedComment() {

			return "";

		}

		public String BaseIcmsRedPattern() {

			return "";

		}

		public String BaseIcmsRedOriginalDbColumnName() {

			return "BaseIcmsRed";

		}

		public Double IcmsSemRed;

		public Double getIcmsSemRed() {
			return this.IcmsSemRed;
		}

		public Boolean IcmsSemRedIsNullable() {
			return true;
		}

		public Boolean IcmsSemRedIsKey() {
			return false;
		}

		public Integer IcmsSemRedLength() {
			return 15;
		}

		public Integer IcmsSemRedPrecision() {
			return 0;
		}

		public String IcmsSemRedDefault() {

			return "";

		}

		public String IcmsSemRedComment() {

			return "";

		}

		public String IcmsSemRedPattern() {

			return "";

		}

		public String IcmsSemRedOriginalDbColumnName() {

			return "IcmsSemRed";

		}

		public String Obs1;

		public String getObs1() {
			return this.Obs1;
		}

		public Boolean Obs1IsNullable() {
			return true;
		}

		public Boolean Obs1IsKey() {
			return false;
		}

		public Integer Obs1Length() {
			return 255;
		}

		public Integer Obs1Precision() {
			return 0;
		}

		public String Obs1Default() {

			return "";

		}

		public String Obs1Comment() {

			return "";

		}

		public String Obs1Pattern() {

			return "";

		}

		public String Obs1OriginalDbColumnName() {

			return "Obs1";

		}

		public String Obs2;

		public String getObs2() {
			return this.Obs2;
		}

		public Boolean Obs2IsNullable() {
			return true;
		}

		public Boolean Obs2IsKey() {
			return false;
		}

		public Integer Obs2Length() {
			return 255;
		}

		public Integer Obs2Precision() {
			return 0;
		}

		public String Obs2Default() {

			return "";

		}

		public String Obs2Comment() {

			return "";

		}

		public String Obs2Pattern() {

			return "";

		}

		public String Obs2OriginalDbColumnName() {

			return "Obs2";

		}

		public String CodEmbal;

		public String getCodEmbal() {
			return this.CodEmbal;
		}

		public Boolean CodEmbalIsNullable() {
			return true;
		}

		public Boolean CodEmbalIsKey() {
			return false;
		}

		public Integer CodEmbalLength() {
			return 3;
		}

		public Integer CodEmbalPrecision() {
			return 0;
		}

		public String CodEmbalDefault() {

			return "";

		}

		public String CodEmbalComment() {

			return "";

		}

		public String CodEmbalPattern() {

			return "";

		}

		public String CodEmbalOriginalDbColumnName() {

			return "CodEmbal";

		}

		public Integer CondPgtoCalc;

		public Integer getCondPgtoCalc() {
			return this.CondPgtoCalc;
		}

		public Boolean CondPgtoCalcIsNullable() {
			return true;
		}

		public Boolean CondPgtoCalcIsKey() {
			return false;
		}

		public Integer CondPgtoCalcLength() {
			return 10;
		}

		public Integer CondPgtoCalcPrecision() {
			return 0;
		}

		public String CondPgtoCalcDefault() {

			return "";

		}

		public String CondPgtoCalcComment() {

			return "";

		}

		public String CondPgtoCalcPattern() {

			return "";

		}

		public String CondPgtoCalcOriginalDbColumnName() {

			return "CondPgtoCalc";

		}

		public Integer CodigoMix;

		public Integer getCodigoMix() {
			return this.CodigoMix;
		}

		public Boolean CodigoMixIsNullable() {
			return true;
		}

		public Boolean CodigoMixIsKey() {
			return false;
		}

		public Integer CodigoMixLength() {
			return 10;
		}

		public Integer CodigoMixPrecision() {
			return 0;
		}

		public String CodigoMixDefault() {

			return "";

		}

		public String CodigoMixComment() {

			return "";

		}

		public String CodigoMixPattern() {

			return "";

		}

		public String CodigoMixOriginalDbColumnName() {

			return "CodigoMix";

		}

		public Integer QtdeMix;

		public Integer getQtdeMix() {
			return this.QtdeMix;
		}

		public Boolean QtdeMixIsNullable() {
			return true;
		}

		public Boolean QtdeMixIsKey() {
			return false;
		}

		public Integer QtdeMixLength() {
			return 10;
		}

		public Integer QtdeMixPrecision() {
			return 0;
		}

		public String QtdeMixDefault() {

			return "";

		}

		public String QtdeMixComment() {

			return "";

		}

		public String QtdeMixPattern() {

			return "";

		}

		public String QtdeMixOriginalDbColumnName() {

			return "QtdeMix";

		}

		public String Lote;

		public String getLote() {
			return this.Lote;
		}

		public Boolean LoteIsNullable() {
			return true;
		}

		public Boolean LoteIsKey() {
			return false;
		}

		public Integer LoteLength() {
			return 25;
		}

		public Integer LotePrecision() {
			return 0;
		}

		public String LoteDefault() {

			return "";

		}

		public String LoteComment() {

			return "";

		}

		public String LotePattern() {

			return "";

		}

		public String LoteOriginalDbColumnName() {

			return "Lote";

		}

		public Double VlrDesctoFiscal;

		public Double getVlrDesctoFiscal() {
			return this.VlrDesctoFiscal;
		}

		public Boolean VlrDesctoFiscalIsNullable() {
			return true;
		}

		public Boolean VlrDesctoFiscalIsKey() {
			return false;
		}

		public Integer VlrDesctoFiscalLength() {
			return 15;
		}

		public Integer VlrDesctoFiscalPrecision() {
			return 0;
		}

		public String VlrDesctoFiscalDefault() {

			return "";

		}

		public String VlrDesctoFiscalComment() {

			return "";

		}

		public String VlrDesctoFiscalPattern() {

			return "";

		}

		public String VlrDesctoFiscalOriginalDbColumnName() {

			return "VlrDesctoFiscal";

		}

		public String Tamanho;

		public String getTamanho() {
			return this.Tamanho;
		}

		public Boolean TamanhoIsNullable() {
			return true;
		}

		public Boolean TamanhoIsKey() {
			return false;
		}

		public Integer TamanhoLength() {
			return 255;
		}

		public Integer TamanhoPrecision() {
			return 0;
		}

		public String TamanhoDefault() {

			return "";

		}

		public String TamanhoComment() {

			return "";

		}

		public String TamanhoPattern() {

			return "";

		}

		public String TamanhoOriginalDbColumnName() {

			return "Tamanho";

		}

		public Integer BloqVerba;

		public Integer getBloqVerba() {
			return this.BloqVerba;
		}

		public Boolean BloqVerbaIsNullable() {
			return true;
		}

		public Boolean BloqVerbaIsKey() {
			return false;
		}

		public Integer BloqVerbaLength() {
			return 10;
		}

		public Integer BloqVerbaPrecision() {
			return 0;
		}

		public String BloqVerbaDefault() {

			return "";

		}

		public String BloqVerbaComment() {

			return "";

		}

		public String BloqVerbaPattern() {

			return "";

		}

		public String BloqVerbaOriginalDbColumnName() {

			return "BloqVerba";

		}

		public Double VlrFreTitRateio;

		public Double getVlrFreTitRateio() {
			return this.VlrFreTitRateio;
		}

		public Boolean VlrFreTitRateioIsNullable() {
			return true;
		}

		public Boolean VlrFreTitRateioIsKey() {
			return false;
		}

		public Integer VlrFreTitRateioLength() {
			return 15;
		}

		public Integer VlrFreTitRateioPrecision() {
			return 0;
		}

		public String VlrFreTitRateioDefault() {

			return "";

		}

		public String VlrFreTitRateioComment() {

			return "";

		}

		public String VlrFreTitRateioPattern() {

			return "";

		}

		public String VlrFreTitRateioOriginalDbColumnName() {

			return "VlrFreTitRateio";

		}

		public Double VlrFreTitRateioCalc;

		public Double getVlrFreTitRateioCalc() {
			return this.VlrFreTitRateioCalc;
		}

		public Boolean VlrFreTitRateioCalcIsNullable() {
			return true;
		}

		public Boolean VlrFreTitRateioCalcIsKey() {
			return false;
		}

		public Integer VlrFreTitRateioCalcLength() {
			return 15;
		}

		public Integer VlrFreTitRateioCalcPrecision() {
			return 0;
		}

		public String VlrFreTitRateioCalcDefault() {

			return "";

		}

		public String VlrFreTitRateioCalcComment() {

			return "";

		}

		public String VlrFreTitRateioCalcPattern() {

			return "";

		}

		public String VlrFreTitRateioCalcOriginalDbColumnName() {

			return "VlrFreTitRateioCalc";

		}

		public Integer VisaoCota;

		public Integer getVisaoCota() {
			return this.VisaoCota;
		}

		public Boolean VisaoCotaIsNullable() {
			return true;
		}

		public Boolean VisaoCotaIsKey() {
			return false;
		}

		public Integer VisaoCotaLength() {
			return 10;
		}

		public Integer VisaoCotaPrecision() {
			return 0;
		}

		public String VisaoCotaDefault() {

			return "";

		}

		public String VisaoCotaComment() {

			return "";

		}

		public String VisaoCotaPattern() {

			return "";

		}

		public String VisaoCotaOriginalDbColumnName() {

			return "VisaoCota";

		}

		public Integer PeriodoCota;

		public Integer getPeriodoCota() {
			return this.PeriodoCota;
		}

		public Boolean PeriodoCotaIsNullable() {
			return true;
		}

		public Boolean PeriodoCotaIsKey() {
			return false;
		}

		public Integer PeriodoCotaLength() {
			return 10;
		}

		public Integer PeriodoCotaPrecision() {
			return 0;
		}

		public String PeriodoCotaDefault() {

			return "";

		}

		public String PeriodoCotaComment() {

			return "";

		}

		public String PeriodoCotaPattern() {

			return "";

		}

		public String PeriodoCotaOriginalDbColumnName() {

			return "PeriodoCota";

		}

		public Integer VisaoCotaOriginal;

		public Integer getVisaoCotaOriginal() {
			return this.VisaoCotaOriginal;
		}

		public Boolean VisaoCotaOriginalIsNullable() {
			return true;
		}

		public Boolean VisaoCotaOriginalIsKey() {
			return false;
		}

		public Integer VisaoCotaOriginalLength() {
			return 10;
		}

		public Integer VisaoCotaOriginalPrecision() {
			return 0;
		}

		public String VisaoCotaOriginalDefault() {

			return "";

		}

		public String VisaoCotaOriginalComment() {

			return "";

		}

		public String VisaoCotaOriginalPattern() {

			return "";

		}

		public String VisaoCotaOriginalOriginalDbColumnName() {

			return "VisaoCotaOriginal";

		}

		public Integer PeriodoCotaOriginal;

		public Integer getPeriodoCotaOriginal() {
			return this.PeriodoCotaOriginal;
		}

		public Boolean PeriodoCotaOriginalIsNullable() {
			return true;
		}

		public Boolean PeriodoCotaOriginalIsKey() {
			return false;
		}

		public Integer PeriodoCotaOriginalLength() {
			return 10;
		}

		public Integer PeriodoCotaOriginalPrecision() {
			return 0;
		}

		public String PeriodoCotaOriginalDefault() {

			return "";

		}

		public String PeriodoCotaOriginalComment() {

			return "";

		}

		public String PeriodoCotaOriginalPattern() {

			return "";

		}

		public String PeriodoCotaOriginalOriginalDbColumnName() {

			return "PeriodoCotaOriginal";

		}

		public Integer RegraEmpItem;

		public Integer getRegraEmpItem() {
			return this.RegraEmpItem;
		}

		public Boolean RegraEmpItemIsNullable() {
			return true;
		}

		public Boolean RegraEmpItemIsKey() {
			return false;
		}

		public Integer RegraEmpItemLength() {
			return 10;
		}

		public Integer RegraEmpItemPrecision() {
			return 0;
		}

		public String RegraEmpItemDefault() {

			return "";

		}

		public String RegraEmpItemComment() {

			return "";

		}

		public String RegraEmpItemPattern() {

			return "";

		}

		public String RegraEmpItemOriginalDbColumnName() {

			return "RegraEmpItem";

		}

		public Integer LjPeca;

		public Integer getLjPeca() {
			return this.LjPeca;
		}

		public Boolean LjPecaIsNullable() {
			return true;
		}

		public Boolean LjPecaIsKey() {
			return false;
		}

		public Integer LjPecaLength() {
			return 10;
		}

		public Integer LjPecaPrecision() {
			return 0;
		}

		public String LjPecaDefault() {

			return "";

		}

		public String LjPecaComment() {

			return "";

		}

		public String LjPecaPattern() {

			return "";

		}

		public String LjPecaOriginalDbColumnName() {

			return "LjPeca";

		}

		public Double LjTamanho;

		public Double getLjTamanho() {
			return this.LjTamanho;
		}

		public Boolean LjTamanhoIsNullable() {
			return true;
		}

		public Boolean LjTamanhoIsKey() {
			return false;
		}

		public Integer LjTamanhoLength() {
			return 15;
		}

		public Integer LjTamanhoPrecision() {
			return 0;
		}

		public String LjTamanhoDefault() {

			return "";

		}

		public String LjTamanhoComment() {

			return "";

		}

		public String LjTamanhoPattern() {

			return "";

		}

		public String LjTamanhoOriginalDbColumnName() {

			return "LjTamanho";

		}

		public Integer LjCarga;

		public Integer getLjCarga() {
			return this.LjCarga;
		}

		public Boolean LjCargaIsNullable() {
			return true;
		}

		public Boolean LjCargaIsKey() {
			return false;
		}

		public Integer LjCargaLength() {
			return 10;
		}

		public Integer LjCargaPrecision() {
			return 0;
		}

		public String LjCargaDefault() {

			return "";

		}

		public String LjCargaComment() {

			return "";

		}

		public String LjCargaPattern() {

			return "";

		}

		public String LjCargaOriginalDbColumnName() {

			return "LjCarga";

		}

		public Double LjMedidaA;

		public Double getLjMedidaA() {
			return this.LjMedidaA;
		}

		public Boolean LjMedidaAIsNullable() {
			return true;
		}

		public Boolean LjMedidaAIsKey() {
			return false;
		}

		public Integer LjMedidaALength() {
			return 15;
		}

		public Integer LjMedidaAPrecision() {
			return 0;
		}

		public String LjMedidaADefault() {

			return "";

		}

		public String LjMedidaAComment() {

			return "";

		}

		public String LjMedidaAPattern() {

			return "";

		}

		public String LjMedidaAOriginalDbColumnName() {

			return "LjMedidaA";

		}

		public Double LjVao;

		public Double getLjVao() {
			return this.LjVao;
		}

		public Boolean LjVaoIsNullable() {
			return true;
		}

		public Boolean LjVaoIsKey() {
			return false;
		}

		public Integer LjVaoLength() {
			return 15;
		}

		public Integer LjVaoPrecision() {
			return 0;
		}

		public String LjVaoDefault() {

			return "";

		}

		public String LjVaoComment() {

			return "";

		}

		public String LjVaoPattern() {

			return "";

		}

		public String LjVaoOriginalDbColumnName() {

			return "LjVao";

		}

		public Integer LjAngulo;

		public Integer getLjAngulo() {
			return this.LjAngulo;
		}

		public Boolean LjAnguloIsNullable() {
			return true;
		}

		public Boolean LjAnguloIsKey() {
			return false;
		}

		public Integer LjAnguloLength() {
			return 10;
		}

		public Integer LjAnguloPrecision() {
			return 0;
		}

		public String LjAnguloDefault() {

			return "";

		}

		public String LjAnguloComment() {

			return "";

		}

		public String LjAnguloPattern() {

			return "";

		}

		public String LjAnguloOriginalDbColumnName() {

			return "LjAngulo";

		}

		public Double LjMedidaB;

		public Double getLjMedidaB() {
			return this.LjMedidaB;
		}

		public Boolean LjMedidaBIsNullable() {
			return true;
		}

		public Boolean LjMedidaBIsKey() {
			return false;
		}

		public Integer LjMedidaBLength() {
			return 15;
		}

		public Integer LjMedidaBPrecision() {
			return 0;
		}

		public String LjMedidaBDefault() {

			return "";

		}

		public String LjMedidaBComment() {

			return "";

		}

		public String LjMedidaBPattern() {

			return "";

		}

		public String LjMedidaBOriginalDbColumnName() {

			return "LjMedidaB";

		}

		public Integer LjInicioViga;

		public Integer getLjInicioViga() {
			return this.LjInicioViga;
		}

		public Boolean LjInicioVigaIsNullable() {
			return true;
		}

		public Boolean LjInicioVigaIsKey() {
			return false;
		}

		public Integer LjInicioVigaLength() {
			return 10;
		}

		public Integer LjInicioVigaPrecision() {
			return 0;
		}

		public String LjInicioVigaDefault() {

			return "";

		}

		public String LjInicioVigaComment() {

			return "";

		}

		public String LjInicioVigaPattern() {

			return "";

		}

		public String LjInicioVigaOriginalDbColumnName() {

			return "LjInicioViga";

		}

		public Integer LjAba;

		public Integer getLjAba() {
			return this.LjAba;
		}

		public Boolean LjAbaIsNullable() {
			return true;
		}

		public Boolean LjAbaIsKey() {
			return false;
		}

		public Integer LjAbaLength() {
			return 10;
		}

		public Integer LjAbaPrecision() {
			return 0;
		}

		public String LjAbaDefault() {

			return "";

		}

		public String LjAbaComment() {

			return "";

		}

		public String LjAbaPattern() {

			return "";

		}

		public String LjAbaOriginalDbColumnName() {

			return "LjAba";

		}

		public Double LjAbaA;

		public Double getLjAbaA() {
			return this.LjAbaA;
		}

		public Boolean LjAbaAIsNullable() {
			return true;
		}

		public Boolean LjAbaAIsKey() {
			return false;
		}

		public Integer LjAbaALength() {
			return 15;
		}

		public Integer LjAbaAPrecision() {
			return 0;
		}

		public String LjAbaADefault() {

			return "";

		}

		public String LjAbaAComment() {

			return "";

		}

		public String LjAbaAPattern() {

			return "";

		}

		public String LjAbaAOriginalDbColumnName() {

			return "LjAbaA";

		}

		public Double LjAbaB;

		public Double getLjAbaB() {
			return this.LjAbaB;
		}

		public Boolean LjAbaBIsNullable() {
			return true;
		}

		public Boolean LjAbaBIsKey() {
			return false;
		}

		public Integer LjAbaBLength() {
			return 15;
		}

		public Integer LjAbaBPrecision() {
			return 0;
		}

		public String LjAbaBDefault() {

			return "";

		}

		public String LjAbaBComment() {

			return "";

		}

		public String LjAbaBPattern() {

			return "";

		}

		public String LjAbaBOriginalDbColumnName() {

			return "LjAbaB";

		}

		public Integer Bloqluc;

		public Integer getBloqluc() {
			return this.Bloqluc;
		}

		public Boolean BloqlucIsNullable() {
			return true;
		}

		public Boolean BloqlucIsKey() {
			return false;
		}

		public Integer BloqlucLength() {
			return 10;
		}

		public Integer BloqlucPrecision() {
			return 0;
		}

		public String BloqlucDefault() {

			return "";

		}

		public String BloqlucComment() {

			return "";

		}

		public String BloqlucPattern() {

			return "";

		}

		public String BloqlucOriginalDbColumnName() {

			return "Bloqluc";

		}

		public Integer OrigemCota;

		public Integer getOrigemCota() {
			return this.OrigemCota;
		}

		public Boolean OrigemCotaIsNullable() {
			return true;
		}

		public Boolean OrigemCotaIsKey() {
			return false;
		}

		public Integer OrigemCotaLength() {
			return 10;
		}

		public Integer OrigemCotaPrecision() {
			return 0;
		}

		public String OrigemCotaDefault() {

			return "";

		}

		public String OrigemCotaComment() {

			return "";

		}

		public String OrigemCotaPattern() {

			return "";

		}

		public String OrigemCotaOriginalDbColumnName() {

			return "OrigemCota";

		}

		public Integer OrigemCanc;

		public Integer getOrigemCanc() {
			return this.OrigemCanc;
		}

		public Boolean OrigemCancIsNullable() {
			return true;
		}

		public Boolean OrigemCancIsKey() {
			return false;
		}

		public Integer OrigemCancLength() {
			return 10;
		}

		public Integer OrigemCancPrecision() {
			return 0;
		}

		public String OrigemCancDefault() {

			return "";

		}

		public String OrigemCancComment() {

			return "";

		}

		public String OrigemCancPattern() {

			return "";

		}

		public String OrigemCancOriginalDbColumnName() {

			return "OrigemCanc";

		}

		public Integer Incsug;

		public Integer getIncsug() {
			return this.Incsug;
		}

		public Boolean IncsugIsNullable() {
			return true;
		}

		public Boolean IncsugIsKey() {
			return false;
		}

		public Integer IncsugLength() {
			return 10;
		}

		public Integer IncsugPrecision() {
			return 0;
		}

		public String IncsugDefault() {

			return "";

		}

		public String IncsugComment() {

			return "";

		}

		public String IncsugPattern() {

			return "";

		}

		public String IncsugOriginalDbColumnName() {

			return "Incsug";

		}

		public Integer LjIdent;

		public Integer getLjIdent() {
			return this.LjIdent;
		}

		public Boolean LjIdentIsNullable() {
			return true;
		}

		public Boolean LjIdentIsKey() {
			return false;
		}

		public Integer LjIdentLength() {
			return 10;
		}

		public Integer LjIdentPrecision() {
			return 0;
		}

		public String LjIdentDefault() {

			return "";

		}

		public String LjIdentComment() {

			return "";

		}

		public String LjIdentPattern() {

			return "";

		}

		public String LjIdentOriginalDbColumnName() {

			return "LjIdent";

		}

		public String CondFreteIt;

		public String getCondFreteIt() {
			return this.CondFreteIt;
		}

		public Boolean CondFreteItIsNullable() {
			return true;
		}

		public Boolean CondFreteItIsKey() {
			return false;
		}

		public Integer CondFreteItLength() {
			return 4;
		}

		public Integer CondFreteItPrecision() {
			return 0;
		}

		public String CondFreteItDefault() {

			return null;

		}

		public String CondFreteItComment() {

			return "";

		}

		public String CondFreteItPattern() {

			return "";

		}

		public String CondFreteItOriginalDbColumnName() {

			return "CondFreteIt";

		}

		public Double DesctotalCalc;

		public Double getDesctotalCalc() {
			return this.DesctotalCalc;
		}

		public Boolean DesctotalCalcIsNullable() {
			return true;
		}

		public Boolean DesctotalCalcIsKey() {
			return false;
		}

		public Integer DesctotalCalcLength() {
			return 15;
		}

		public Integer DesctotalCalcPrecision() {
			return 0;
		}

		public String DesctotalCalcDefault() {

			return "";

		}

		public String DesctotalCalcComment() {

			return "";

		}

		public String DesctotalCalcPattern() {

			return "";

		}

		public String DesctotalCalcOriginalDbColumnName() {

			return "DesctotalCalc";

		}

		public String IdPedAuto;

		public String getIdPedAuto() {
			return this.IdPedAuto;
		}

		public Boolean IdPedAutoIsNullable() {
			return true;
		}

		public Boolean IdPedAutoIsKey() {
			return false;
		}

		public Integer IdPedAutoLength() {
			return 50;
		}

		public Integer IdPedAutoPrecision() {
			return 0;
		}

		public String IdPedAutoDefault() {

			return null;

		}

		public String IdPedAutoComment() {

			return "";

		}

		public String IdPedAutoPattern() {

			return "";

		}

		public String IdPedAutoOriginalDbColumnName() {

			return "IdPedAuto";

		}

		public String LoteEstoque;

		public String getLoteEstoque() {
			return this.LoteEstoque;
		}

		public Boolean LoteEstoqueIsNullable() {
			return true;
		}

		public Boolean LoteEstoqueIsKey() {
			return false;
		}

		public Integer LoteEstoqueLength() {
			return 50;
		}

		public Integer LoteEstoquePrecision() {
			return 0;
		}

		public String LoteEstoqueDefault() {

			return null;

		}

		public String LoteEstoqueComment() {

			return "";

		}

		public String LoteEstoquePattern() {

			return "";

		}

		public String LoteEstoqueOriginalDbColumnName() {

			return "LoteEstoque";

		}

		public Double DesctotalAplicado;

		public Double getDesctotalAplicado() {
			return this.DesctotalAplicado;
		}

		public Boolean DesctotalAplicadoIsNullable() {
			return true;
		}

		public Boolean DesctotalAplicadoIsKey() {
			return false;
		}

		public Integer DesctotalAplicadoLength() {
			return 15;
		}

		public Integer DesctotalAplicadoPrecision() {
			return 0;
		}

		public String DesctotalAplicadoDefault() {

			return "";

		}

		public String DesctotalAplicadoComment() {

			return "";

		}

		public String DesctotalAplicadoPattern() {

			return "";

		}

		public String DesctotalAplicadoOriginalDbColumnName() {

			return "DesctotalAplicado";

		}

		public Integer SeqOrdemCompra;

		public Integer getSeqOrdemCompra() {
			return this.SeqOrdemCompra;
		}

		public Boolean SeqOrdemCompraIsNullable() {
			return true;
		}

		public Boolean SeqOrdemCompraIsKey() {
			return false;
		}

		public Integer SeqOrdemCompraLength() {
			return 10;
		}

		public Integer SeqOrdemCompraPrecision() {
			return 0;
		}

		public String SeqOrdemCompraDefault() {

			return "";

		}

		public String SeqOrdemCompraComment() {

			return "";

		}

		public String SeqOrdemCompraPattern() {

			return "";

		}

		public String SeqOrdemCompraOriginalDbColumnName() {

			return "SeqOrdemCompra";

		}

		public String SeqordCompra;

		public String getSeqordCompra() {
			return this.SeqordCompra;
		}

		public Boolean SeqordCompraIsNullable() {
			return true;
		}

		public Boolean SeqordCompraIsKey() {
			return false;
		}

		public Integer SeqordCompraLength() {
			return 30;
		}

		public Integer SeqordCompraPrecision() {
			return 0;
		}

		public String SeqordCompraDefault() {

			return null;

		}

		public String SeqordCompraComment() {

			return "";

		}

		public String SeqordCompraPattern() {

			return "";

		}

		public String SeqordCompraOriginalDbColumnName() {

			return "SeqordCompra";

		}

		public String Ordcomest;

		public String getOrdcomest() {
			return this.Ordcomest;
		}

		public Boolean OrdcomestIsNullable() {
			return true;
		}

		public Boolean OrdcomestIsKey() {
			return false;
		}

		public Integer OrdcomestLength() {
			return 255;
		}

		public Integer OrdcomestPrecision() {
			return 0;
		}

		public String OrdcomestDefault() {

			return null;

		}

		public String OrdcomestComment() {

			return "";

		}

		public String OrdcomestPattern() {

			return "";

		}

		public String OrdcomestOriginalDbColumnName() {

			return "Ordcomest";

		}

		public Double ValorICMSFrete;

		public Double getValorICMSFrete() {
			return this.ValorICMSFrete;
		}

		public Boolean ValorICMSFreteIsNullable() {
			return true;
		}

		public Boolean ValorICMSFreteIsKey() {
			return false;
		}

		public Integer ValorICMSFreteLength() {
			return 15;
		}

		public Integer ValorICMSFretePrecision() {
			return 0;
		}

		public String ValorICMSFreteDefault() {

			return "";

		}

		public String ValorICMSFreteComment() {

			return "";

		}

		public String ValorICMSFretePattern() {

			return "";

		}

		public String ValorICMSFreteOriginalDbColumnName() {

			return "ValorICMSFrete";

		}

		public Integer Campo1;

		public Integer getCampo1() {
			return this.Campo1;
		}

		public Boolean Campo1IsNullable() {
			return true;
		}

		public Boolean Campo1IsKey() {
			return false;
		}

		public Integer Campo1Length() {
			return 10;
		}

		public Integer Campo1Precision() {
			return 0;
		}

		public String Campo1Default() {

			return "";

		}

		public String Campo1Comment() {

			return "";

		}

		public String Campo1Pattern() {

			return "";

		}

		public String Campo1OriginalDbColumnName() {

			return "Campo1";

		}

		public String CentroCusto;

		public String getCentroCusto() {
			return this.CentroCusto;
		}

		public Boolean CentroCustoIsNullable() {
			return true;
		}

		public Boolean CentroCustoIsKey() {
			return false;
		}

		public Integer CentroCustoLength() {
			return 30;
		}

		public Integer CentroCustoPrecision() {
			return 0;
		}

		public String CentroCustoDefault() {

			return null;

		}

		public String CentroCustoComment() {

			return "";

		}

		public String CentroCustoPattern() {

			return "";

		}

		public String CentroCustoOriginalDbColumnName() {

			return "CentroCusto";

		}

		public Integer BloqDesc;

		public Integer getBloqDesc() {
			return this.BloqDesc;
		}

		public Boolean BloqDescIsNullable() {
			return true;
		}

		public Boolean BloqDescIsKey() {
			return false;
		}

		public Integer BloqDescLength() {
			return 10;
		}

		public Integer BloqDescPrecision() {
			return 0;
		}

		public String BloqDescDefault() {

			return "";

		}

		public String BloqDescComment() {

			return "";

		}

		public String BloqDescPattern() {

			return "";

		}

		public String BloqDescOriginalDbColumnName() {

			return "BloqDesc";

		}

		public Double QtdeDescBonif;

		public Double getQtdeDescBonif() {
			return this.QtdeDescBonif;
		}

		public Boolean QtdeDescBonifIsNullable() {
			return true;
		}

		public Boolean QtdeDescBonifIsKey() {
			return false;
		}

		public Integer QtdeDescBonifLength() {
			return 15;
		}

		public Integer QtdeDescBonifPrecision() {
			return 0;
		}

		public String QtdeDescBonifDefault() {

			return "";

		}

		public String QtdeDescBonifComment() {

			return "";

		}

		public String QtdeDescBonifPattern() {

			return "";

		}

		public String QtdeDescBonifOriginalDbColumnName() {

			return "QtdeDescBonif";

		}

		public Double PercDescBonif;

		public Double getPercDescBonif() {
			return this.PercDescBonif;
		}

		public Boolean PercDescBonifIsNullable() {
			return true;
		}

		public Boolean PercDescBonifIsKey() {
			return false;
		}

		public Integer PercDescBonifLength() {
			return 15;
		}

		public Integer PercDescBonifPrecision() {
			return 0;
		}

		public String PercDescBonifDefault() {

			return "";

		}

		public String PercDescBonifComment() {

			return "";

		}

		public String PercDescBonifPattern() {

			return "";

		}

		public String PercDescBonifOriginalDbColumnName() {

			return "PercDescBonif";

		}

		public Double ValorDesccUnit;

		public Double getValorDesccUnit() {
			return this.ValorDesccUnit;
		}

		public Boolean ValorDesccUnitIsNullable() {
			return true;
		}

		public Boolean ValorDesccUnitIsKey() {
			return false;
		}

		public Integer ValorDesccUnitLength() {
			return 15;
		}

		public Integer ValorDesccUnitPrecision() {
			return 0;
		}

		public String ValorDesccUnitDefault() {

			return "";

		}

		public String ValorDesccUnitComment() {

			return "";

		}

		public String ValorDesccUnitPattern() {

			return "";

		}

		public String ValorDesccUnitOriginalDbColumnName() {

			return "ValorDesccUnit";

		}

		public Integer SeqCota;

		public Integer getSeqCota() {
			return this.SeqCota;
		}

		public Boolean SeqCotaIsNullable() {
			return true;
		}

		public Boolean SeqCotaIsKey() {
			return false;
		}

		public Integer SeqCotaLength() {
			return 10;
		}

		public Integer SeqCotaPrecision() {
			return 0;
		}

		public String SeqCotaDefault() {

			return "";

		}

		public String SeqCotaComment() {

			return "";

		}

		public String SeqCotaPattern() {

			return "";

		}

		public String SeqCotaOriginalDbColumnName() {

			return "SeqCota";

		}

		public Integer SeqCotaOriginal;

		public Integer getSeqCotaOriginal() {
			return this.SeqCotaOriginal;
		}

		public Boolean SeqCotaOriginalIsNullable() {
			return true;
		}

		public Boolean SeqCotaOriginalIsKey() {
			return false;
		}

		public Integer SeqCotaOriginalLength() {
			return 10;
		}

		public Integer SeqCotaOriginalPrecision() {
			return 0;
		}

		public String SeqCotaOriginalDefault() {

			return "";

		}

		public String SeqCotaOriginalComment() {

			return "";

		}

		public String SeqCotaOriginalPattern() {

			return "";

		}

		public String SeqCotaOriginalOriginalDbColumnName() {

			return "SeqCotaOriginal";

		}

		public java.util.Date DtRegraCorte;

		public java.util.Date getDtRegraCorte() {
			return this.DtRegraCorte;
		}

		public Boolean DtRegraCorteIsNullable() {
			return true;
		}

		public Boolean DtRegraCorteIsKey() {
			return false;
		}

		public Integer DtRegraCorteLength() {
			return 23;
		}

		public Integer DtRegraCortePrecision() {
			return 3;
		}

		public String DtRegraCorteDefault() {

			return null;

		}

		public String DtRegraCorteComment() {

			return "";

		}

		public String DtRegraCortePattern() {

			return "dd-MM-yyyy";

		}

		public String DtRegraCorteOriginalDbColumnName() {

			return "DtRegraCorte";

		}

		public Integer CompoeMargem;

		public Integer getCompoeMargem() {
			return this.CompoeMargem;
		}

		public Boolean CompoeMargemIsNullable() {
			return true;
		}

		public Boolean CompoeMargemIsKey() {
			return false;
		}

		public Integer CompoeMargemLength() {
			return 10;
		}

		public Integer CompoeMargemPrecision() {
			return 0;
		}

		public String CompoeMargemDefault() {

			return "";

		}

		public String CompoeMargemComment() {

			return "";

		}

		public String CompoeMargemPattern() {

			return "";

		}

		public String CompoeMargemOriginalDbColumnName() {

			return "CompoeMargem";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

				result = prime * result + ((this.CodigoProduto == null) ? 0 : this.CodigoProduto.hashCode());

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

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			if (this.CodigoProduto == null) {
				if (other.CodigoProduto != null)
					return false;

			} else if (!this.CodigoProduto.equals(other.CodigoProduto))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.QuantidadeProduto = this.QuantidadeProduto;
			other.QuantidadeAtendida = this.QuantidadeAtendida;
			other.QuantidadeCancelada = this.QuantidadeCancelada;
			other.PrecoUnitario = this.PrecoUnitario;
			other.PrecoLiquido = this.PrecoLiquido;
			other.Desctop = this.Desctop;
			other.DesctoIcm = this.DesctoIcm;
			other.PrevEntr = this.PrevEntr;
			other.AtdData = this.AtdData;
			other.AtdQtde = this.AtdQtde;
			other.AtdDcto = this.AtdDcto;
			other.AtdData1 = this.AtdData1;
			other.AtdQtde1 = this.AtdQtde1;
			other.AtdDcto1 = this.AtdDcto1;
			other.AtdData2 = this.AtdData2;
			other.AtdQtde2 = this.AtdQtde2;
			other.AtdDcto2 = this.AtdDcto2;
			other.SituacaoPedido = this.SituacaoPedido;
			other.Ipi = this.Ipi;
			other.Dctoprom = this.Dctoprom;
			other.EconVlr = this.EconVlr;
			other.EconCalc = this.EconCalc;
			other.EconDcto = this.EconDcto;
			other.PrecoMin = this.PrecoMin;
			other.Tipo = this.Tipo;
			other.Dctovda = this.Dctovda;
			other.TipoPreco = this.TipoPreco;
			other.EstVlr = this.EstVlr;
			other.Acrescimo = this.Acrescimo;
			other.Lpreco = this.Lpreco;
			other.Grade = this.Grade;
			other.TpGrade = this.TpGrade;
			other.CodEtiq = this.CodEtiq;
			other.Embalagem = this.Embalagem;
			other.MotCanc = this.MotCanc;
			other.PercComis = this.PercComis;
			other.PrdCfgCar = this.PrdCfgCar;
			other.Observacao = this.Observacao;
			other.BonifEcon = this.BonifEcon;
			other.PcomisExt = this.PcomisExt;
			other.AplicDcto = this.AplicDcto;
			other.Operacao = this.Operacao;
			other.QtdeRes = this.QtdeRes;
			other.Bloqsug = this.Bloqsug;
			other.Dctoinc = this.Dctoinc;
			other.VlrIpi = this.VlrIpi;
			other.SeqGrd = this.SeqGrd;
			other.SeqErp = this.SeqErp;
			other.PrevCalc = this.PrevCalc;
			other.Almoxarif = this.Almoxarif;
			other.ComisGer = this.ComisGer;
			other.CredProp = this.CredProp;
			other.NroEnvio = this.NroEnvio;
			other.Chave = this.Chave;
			other.ItemConf = this.ItemConf;
			other.CvdaDcti = this.CvdaDcti;
			other.ObsNota = this.ObsNota;
			other.ObsExped = this.ObsExped;
			other.ObsSepar = this.ObsSepar;
			other.VlrSubst = this.VlrSubst;
			other.DesctoGer = this.DesctoGer;
			other.VlrFreTit = this.VlrFreTit;
			other.MotTroca = this.MotTroca;
			other.VeconCig = this.VeconCig;
			other.CustoMp = this.CustoMp;
			other.PliqAlt = this.PliqAlt;
			other.CodPrMap = this.CodPrMap;
			other.CodCvdAg = this.CodCvdAg;
			other.VuIcms = this.VuIcms;
			other.VuPis = this.VuPis;
			other.VuCofins = this.VuCofins;
			other.Altdtprev = this.Altdtprev;
			other.QtdeAloc = this.QtdeAloc;
			other.RestrBonif = this.RestrBonif;
			other.CustoMo = this.CustoMo;
			other.ItControl = this.ItControl;
			other.ItPermBon = this.ItPermBon;
			other.QtdPermBon = this.QtdPermBon;
			other.VlrPermBon = this.VlrPermBon;
			other.Itemfemb = this.Itemfemb;
			other.Dtprevento = this.Dtprevento;
			other.Cfretit = this.Cfretit;
			other.SldVerba = this.SldVerba;
			other.VlrBonif = this.VlrBonif;
			other.Bonif = this.Bonif;
			other.Tpproduto = this.Tpproduto;
			other.Backord = this.Backord;
			other.Markup = this.Markup;
			other.QtdeBkor = this.QtdeBkor;
			other.Prcpromoc = this.Prcpromoc;
			other.Sitcorp1 = this.Sitcorp1;
			other.Sitcorp2 = this.Sitcorp2;
			other.LibMots = this.LibMots;
			other.Dctoboleto = this.Dctoboleto;
			other.MPRISeq = this.MPRISeq;
			other.Dctomax = this.Dctomax;
			other.Comprim = this.Comprim;
			other.QtdePeca = this.QtdePeca;
			other.ProdRes = this.ProdRes;
			other.EmpEstoq = this.EmpEstoq;
			other.PrevProduc = this.PrevProduc;
			other.PrunitOri = this.PrunitOri;
			other.PrliqOri = this.PrliqOri;
			other.Mva = this.Mva;
			other.ICMSOrig = this.ICMSOrig;
			other.ICMSDest = this.ICMSDest;
			other.Float1 = this.Float1;
			other.Float2 = this.Float2;
			other.DataAlter = this.DataAlter;
			other.SeqOrig = this.SeqOrig;
			other.PrecoNorm = this.PrecoNorm;
			other.Tipost = this.Tipost;
			other.CodProdcli = this.CodProdcli;
			other.VlrDescarga = this.VlrDescarga;
			other.IcmsDscto = this.IcmsDscto;
			other.EmpSplit = this.EmpSplit;
			other.Status = this.Status;
			other.Obrigatorio = this.Obrigatorio;
			other.PrecoFirme = this.PrecoFirme;
			other.OrdCompra = this.OrdCompra;
			other.DesctoFiscal = this.DesctoFiscal;
			other.PercMlucr = this.PercMlucr;
			other.VlrDescargaCalc = this.VlrDescargaCalc;
			other.VlrFreTitCalc = this.VlrFreTitCalc;
			other.VuComissao = this.VuComissao;
			other.VuDebcli = this.VuDebcli;
			other.Data1Original = this.Data1Original;
			other.Visao = this.Visao;
			other.Periodo = this.Periodo;
			other.BaseIcms = this.BaseIcms;
			other.BaseSt = this.BaseSt;
			other.CondPgto = this.CondPgto;
			other.UsuCanc = this.UsuCanc;
			other.Resrede = this.Resrede;
			other.DocBloq = this.DocBloq;
			other.Regracomis = this.Regracomis;
			other.BaseIcmsRed = this.BaseIcmsRed;
			other.IcmsSemRed = this.IcmsSemRed;
			other.Obs1 = this.Obs1;
			other.Obs2 = this.Obs2;
			other.CodEmbal = this.CodEmbal;
			other.CondPgtoCalc = this.CondPgtoCalc;
			other.CodigoMix = this.CodigoMix;
			other.QtdeMix = this.QtdeMix;
			other.Lote = this.Lote;
			other.VlrDesctoFiscal = this.VlrDesctoFiscal;
			other.Tamanho = this.Tamanho;
			other.BloqVerba = this.BloqVerba;
			other.VlrFreTitRateio = this.VlrFreTitRateio;
			other.VlrFreTitRateioCalc = this.VlrFreTitRateioCalc;
			other.VisaoCota = this.VisaoCota;
			other.PeriodoCota = this.PeriodoCota;
			other.VisaoCotaOriginal = this.VisaoCotaOriginal;
			other.PeriodoCotaOriginal = this.PeriodoCotaOriginal;
			other.RegraEmpItem = this.RegraEmpItem;
			other.LjPeca = this.LjPeca;
			other.LjTamanho = this.LjTamanho;
			other.LjCarga = this.LjCarga;
			other.LjMedidaA = this.LjMedidaA;
			other.LjVao = this.LjVao;
			other.LjAngulo = this.LjAngulo;
			other.LjMedidaB = this.LjMedidaB;
			other.LjInicioViga = this.LjInicioViga;
			other.LjAba = this.LjAba;
			other.LjAbaA = this.LjAbaA;
			other.LjAbaB = this.LjAbaB;
			other.Bloqluc = this.Bloqluc;
			other.OrigemCota = this.OrigemCota;
			other.OrigemCanc = this.OrigemCanc;
			other.Incsug = this.Incsug;
			other.LjIdent = this.LjIdent;
			other.CondFreteIt = this.CondFreteIt;
			other.DesctotalCalc = this.DesctotalCalc;
			other.IdPedAuto = this.IdPedAuto;
			other.LoteEstoque = this.LoteEstoque;
			other.DesctotalAplicado = this.DesctotalAplicado;
			other.SeqOrdemCompra = this.SeqOrdemCompra;
			other.SeqordCompra = this.SeqordCompra;
			other.Ordcomest = this.Ordcomest;
			other.ValorICMSFrete = this.ValorICMSFrete;
			other.Campo1 = this.Campo1;
			other.CentroCusto = this.CentroCusto;
			other.BloqDesc = this.BloqDesc;
			other.QtdeDescBonif = this.QtdeDescBonif;
			other.PercDescBonif = this.PercDescBonif;
			other.ValorDesccUnit = this.ValorDesccUnit;
			other.SeqCota = this.SeqCota;
			other.SeqCotaOriginal = this.SeqCotaOriginal;
			other.DtRegraCorte = this.DtRegraCorte;
			other.CompoeMargem = this.CompoeMargem;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.CodigoProduto = this.CodigoProduto;

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
				if (length > commonByteArray_HYDRONORTH_Pedidos_mercanet.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Pedidos_mercanet.length == 0) {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Pedidos_mercanet.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Pedidos_mercanet.length == 0) {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Pedidos_mercanet) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					this.SequenciaItem = readInteger(dis);

					this.CodigoProduto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeProduto = null;
					} else {
						this.QuantidadeProduto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeAtendida = null;
					} else {
						this.QuantidadeAtendida = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeCancelada = null;
					} else {
						this.QuantidadeCancelada = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoLiquido = null;
					} else {
						this.PrecoLiquido = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Desctop = null;
					} else {
						this.Desctop = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoIcm = null;
					} else {
						this.DesctoIcm = dis.readDouble();
					}

					this.PrevEntr = readDate(dis);

					this.AtdData = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde = null;
					} else {
						this.AtdQtde = dis.readFloat();
					}

					this.AtdDcto = readString(dis);

					this.AtdData1 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde1 = null;
					} else {
						this.AtdQtde1 = dis.readFloat();
					}

					this.AtdDcto1 = readString(dis);

					this.AtdData2 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde2 = null;
					} else {
						this.AtdQtde2 = dis.readFloat();
					}

					this.AtdDcto2 = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Ipi = null;
					} else {
						this.Ipi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Dctoprom = null;
					} else {
						this.Dctoprom = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconVlr = null;
					} else {
						this.EconVlr = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconCalc = null;
					} else {
						this.EconCalc = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconDcto = null;
					} else {
						this.EconDcto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoMin = null;
					} else {
						this.PrecoMin = dis.readFloat();
					}

					this.Tipo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctovda = null;
					} else {
						this.Dctovda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TipoPreco = null;
					} else {
						this.TipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EstVlr = null;
					} else {
						this.EstVlr = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Acrescimo = null;
					} else {
						this.Acrescimo = dis.readDouble();
					}

					this.Lpreco = readString(dis);

					this.Grade = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TpGrade = null;
					} else {
						this.TpGrade = dis.readShort();
					}

					this.CodEtiq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Embalagem = null;
					} else {
						this.Embalagem = dis.readDouble();
					}

					this.MotCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PercComis = null;
					} else {
						this.PercComis = dis.readFloat();
					}

					this.PrdCfgCar = readString(dis);

					this.Observacao = readString(dis);

					this.BonifEcon = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PcomisExt = null;
					} else {
						this.PcomisExt = dis.readDouble();
					}

					this.AplicDcto = readString(dis);

					this.Operacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeRes = null;
					} else {
						this.QtdeRes = dis.readDouble();
					}

					this.Bloqsug = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoinc = null;
					} else {
						this.Dctoinc = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrIpi = null;
					} else {
						this.VlrIpi = dis.readDouble();
					}

					this.SeqGrd = readString(dis);

					this.SeqErp = readInteger(dis);

					this.PrevCalc = readDate(dis);

					this.Almoxarif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComisGer = null;
					} else {
						this.ComisGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CredProp = null;
					} else {
						this.CredProp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NroEnvio = null;
					} else {
						this.NroEnvio = dis.readDouble();
					}

					this.Chave = readString(dis);

					this.ItemConf = readString(dis);

					this.CvdaDcti = readString(dis);

					this.ObsNota = readString(dis);

					this.ObsExped = readString(dis);

					this.ObsSepar = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrSubst = null;
					} else {
						this.VlrSubst = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoGer = null;
					} else {
						this.DesctoGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTit = null;
					} else {
						this.VlrFreTit = dis.readDouble();
					}

					this.MotTroca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VeconCig = null;
					} else {
						this.VeconCig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CustoMp = null;
					} else {
						this.CustoMp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PliqAlt = null;
					} else {
						this.PliqAlt = dis.readShort();
					}

					this.CodPrMap = readString(dis);

					this.CodCvdAg = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VuIcms = null;
					} else {
						this.VuIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuPis = null;
					} else {
						this.VuPis = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuCofins = null;
					} else {
						this.VuCofins = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Altdtprev = null;
					} else {
						this.Altdtprev = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeAloc = null;
					} else {
						this.QtdeAloc = dis.readDouble();
					}

					this.RestrBonif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoMo = null;
					} else {
						this.CustoMo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItControl = null;
					} else {
						this.ItControl = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItPermBon = null;
					} else {
						this.ItPermBon = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdPermBon = null;
					} else {
						this.QtdPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrPermBon = null;
					} else {
						this.VlrPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Itemfemb = null;
					} else {
						this.Itemfemb = dis.readShort();
					}

					this.Dtprevento = readDate(dis);

					this.Cfretit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SldVerba = null;
					} else {
						this.SldVerba = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrBonif = null;
					} else {
						this.VlrBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Bonif = null;
					} else {
						this.Bonif = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tpproduto = null;
					} else {
						this.Tpproduto = dis.readShort();
					}

					this.Backord = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Markup = null;
					} else {
						this.Markup = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeBkor = null;
					} else {
						this.QtdeBkor = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prcpromoc = null;
					} else {
						this.Prcpromoc = dis.readDouble();
					}

					this.Sitcorp1 = readString(dis);

					this.Sitcorp2 = readString(dis);

					this.LibMots = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoboleto = null;
					} else {
						this.Dctoboleto = dis.readDouble();
					}

					this.MPRISeq = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctomax = null;
					} else {
						this.Dctomax = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comprim = null;
					} else {
						this.Comprim = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdePeca = null;
					} else {
						this.QtdePeca = dis.readDouble();
					}

					this.ProdRes = readString(dis);

					this.EmpEstoq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrevProduc = null;
					} else {
						this.PrevProduc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrunitOri = null;
					} else {
						this.PrunitOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrliqOri = null;
					} else {
						this.PrliqOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Mva = null;
					} else {
						this.Mva = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSOrig = null;
					} else {
						this.ICMSOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDest = null;
					} else {
						this.ICMSDest = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float1 = null;
					} else {
						this.Float1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float2 = null;
					} else {
						this.Float2 = dis.readDouble();
					}

					this.DataAlter = readDate(dis);

					this.SeqOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrecoNorm = null;
					} else {
						this.PrecoNorm = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tipost = null;
					} else {
						this.Tipost = dis.readShort();
					}

					this.CodProdcli = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescarga = null;
					} else {
						this.VlrDescarga = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsDscto = null;
					} else {
						this.IcmsDscto = dis.readShort();
					}

					this.EmpSplit = readString(dis);

					this.Status = readInteger(dis);

					this.Obrigatorio = readInteger(dis);

					this.PrecoFirme = readInteger(dis);

					this.OrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctoFiscal = null;
					} else {
						this.DesctoFiscal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercMlucr = null;
					} else {
						this.PercMlucr = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescargaCalc = null;
					} else {
						this.VlrDescargaCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitCalc = null;
					} else {
						this.VlrFreTitCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuComissao = null;
					} else {
						this.VuComissao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuDebcli = null;
					} else {
						this.VuDebcli = dis.readDouble();
					}

					this.Data1Original = readDate(dis);

					this.Visao = readInteger(dis);

					this.Periodo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcms = null;
					} else {
						this.BaseIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseSt = null;
					} else {
						this.BaseSt = dis.readDouble();
					}

					this.CondPgto = readInteger(dis);

					this.UsuCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Resrede = null;
					} else {
						this.Resrede = dis.readDouble();
					}

					this.DocBloq = readString(dis);

					this.Regracomis = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcmsRed = null;
					} else {
						this.BaseIcmsRed = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsSemRed = null;
					} else {
						this.IcmsSemRed = dis.readDouble();
					}

					this.Obs1 = readString(dis);

					this.Obs2 = readString(dis);

					this.CodEmbal = readString(dis);

					this.CondPgtoCalc = readInteger(dis);

					this.CodigoMix = readInteger(dis);

					this.QtdeMix = readInteger(dis);

					this.Lote = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDesctoFiscal = null;
					} else {
						this.VlrDesctoFiscal = dis.readDouble();
					}

					this.Tamanho = readString(dis);

					this.BloqVerba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateio = null;
					} else {
						this.VlrFreTitRateio = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateioCalc = null;
					} else {
						this.VlrFreTitRateioCalc = dis.readDouble();
					}

					this.VisaoCota = readInteger(dis);

					this.PeriodoCota = readInteger(dis);

					this.VisaoCotaOriginal = readInteger(dis);

					this.PeriodoCotaOriginal = readInteger(dis);

					this.RegraEmpItem = readInteger(dis);

					this.LjPeca = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjTamanho = null;
					} else {
						this.LjTamanho = dis.readDouble();
					}

					this.LjCarga = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaA = null;
					} else {
						this.LjMedidaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjVao = null;
					} else {
						this.LjVao = dis.readDouble();
					}

					this.LjAngulo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaB = null;
					} else {
						this.LjMedidaB = dis.readDouble();
					}

					this.LjInicioViga = readInteger(dis);

					this.LjAba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaA = null;
					} else {
						this.LjAbaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaB = null;
					} else {
						this.LjAbaB = dis.readDouble();
					}

					this.Bloqluc = readInteger(dis);

					this.OrigemCota = readInteger(dis);

					this.OrigemCanc = readInteger(dis);

					this.Incsug = readInteger(dis);

					this.LjIdent = readInteger(dis);

					this.CondFreteIt = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalCalc = null;
					} else {
						this.DesctotalCalc = dis.readDouble();
					}

					this.IdPedAuto = readString(dis);

					this.LoteEstoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalAplicado = null;
					} else {
						this.DesctotalAplicado = dis.readDouble();
					}

					this.SeqOrdemCompra = readInteger(dis);

					this.SeqordCompra = readString(dis);

					this.Ordcomest = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMSFrete = null;
					} else {
						this.ValorICMSFrete = dis.readDouble();
					}

					this.Campo1 = readInteger(dis);

					this.CentroCusto = readString(dis);

					this.BloqDesc = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeDescBonif = null;
					} else {
						this.QtdeDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercDescBonif = null;
					} else {
						this.PercDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDesccUnit = null;
					} else {
						this.ValorDesccUnit = dis.readDouble();
					}

					this.SeqCota = readInteger(dis);

					this.SeqCotaOriginal = readInteger(dis);

					this.DtRegraCorte = readDate(dis);

					this.CompoeMargem = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Pedidos_mercanet) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					this.SequenciaItem = readInteger(dis);

					this.CodigoProduto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeProduto = null;
					} else {
						this.QuantidadeProduto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeAtendida = null;
					} else {
						this.QuantidadeAtendida = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeCancelada = null;
					} else {
						this.QuantidadeCancelada = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoLiquido = null;
					} else {
						this.PrecoLiquido = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Desctop = null;
					} else {
						this.Desctop = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoIcm = null;
					} else {
						this.DesctoIcm = dis.readDouble();
					}

					this.PrevEntr = readDate(dis);

					this.AtdData = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde = null;
					} else {
						this.AtdQtde = dis.readFloat();
					}

					this.AtdDcto = readString(dis);

					this.AtdData1 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde1 = null;
					} else {
						this.AtdQtde1 = dis.readFloat();
					}

					this.AtdDcto1 = readString(dis);

					this.AtdData2 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde2 = null;
					} else {
						this.AtdQtde2 = dis.readFloat();
					}

					this.AtdDcto2 = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Ipi = null;
					} else {
						this.Ipi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Dctoprom = null;
					} else {
						this.Dctoprom = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconVlr = null;
					} else {
						this.EconVlr = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconCalc = null;
					} else {
						this.EconCalc = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconDcto = null;
					} else {
						this.EconDcto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoMin = null;
					} else {
						this.PrecoMin = dis.readFloat();
					}

					this.Tipo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctovda = null;
					} else {
						this.Dctovda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TipoPreco = null;
					} else {
						this.TipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EstVlr = null;
					} else {
						this.EstVlr = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Acrescimo = null;
					} else {
						this.Acrescimo = dis.readDouble();
					}

					this.Lpreco = readString(dis);

					this.Grade = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TpGrade = null;
					} else {
						this.TpGrade = dis.readShort();
					}

					this.CodEtiq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Embalagem = null;
					} else {
						this.Embalagem = dis.readDouble();
					}

					this.MotCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PercComis = null;
					} else {
						this.PercComis = dis.readFloat();
					}

					this.PrdCfgCar = readString(dis);

					this.Observacao = readString(dis);

					this.BonifEcon = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PcomisExt = null;
					} else {
						this.PcomisExt = dis.readDouble();
					}

					this.AplicDcto = readString(dis);

					this.Operacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeRes = null;
					} else {
						this.QtdeRes = dis.readDouble();
					}

					this.Bloqsug = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoinc = null;
					} else {
						this.Dctoinc = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrIpi = null;
					} else {
						this.VlrIpi = dis.readDouble();
					}

					this.SeqGrd = readString(dis);

					this.SeqErp = readInteger(dis);

					this.PrevCalc = readDate(dis);

					this.Almoxarif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComisGer = null;
					} else {
						this.ComisGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CredProp = null;
					} else {
						this.CredProp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NroEnvio = null;
					} else {
						this.NroEnvio = dis.readDouble();
					}

					this.Chave = readString(dis);

					this.ItemConf = readString(dis);

					this.CvdaDcti = readString(dis);

					this.ObsNota = readString(dis);

					this.ObsExped = readString(dis);

					this.ObsSepar = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrSubst = null;
					} else {
						this.VlrSubst = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoGer = null;
					} else {
						this.DesctoGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTit = null;
					} else {
						this.VlrFreTit = dis.readDouble();
					}

					this.MotTroca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VeconCig = null;
					} else {
						this.VeconCig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CustoMp = null;
					} else {
						this.CustoMp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PliqAlt = null;
					} else {
						this.PliqAlt = dis.readShort();
					}

					this.CodPrMap = readString(dis);

					this.CodCvdAg = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VuIcms = null;
					} else {
						this.VuIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuPis = null;
					} else {
						this.VuPis = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuCofins = null;
					} else {
						this.VuCofins = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Altdtprev = null;
					} else {
						this.Altdtprev = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeAloc = null;
					} else {
						this.QtdeAloc = dis.readDouble();
					}

					this.RestrBonif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoMo = null;
					} else {
						this.CustoMo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItControl = null;
					} else {
						this.ItControl = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItPermBon = null;
					} else {
						this.ItPermBon = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdPermBon = null;
					} else {
						this.QtdPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrPermBon = null;
					} else {
						this.VlrPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Itemfemb = null;
					} else {
						this.Itemfemb = dis.readShort();
					}

					this.Dtprevento = readDate(dis);

					this.Cfretit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SldVerba = null;
					} else {
						this.SldVerba = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrBonif = null;
					} else {
						this.VlrBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Bonif = null;
					} else {
						this.Bonif = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tpproduto = null;
					} else {
						this.Tpproduto = dis.readShort();
					}

					this.Backord = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Markup = null;
					} else {
						this.Markup = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeBkor = null;
					} else {
						this.QtdeBkor = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prcpromoc = null;
					} else {
						this.Prcpromoc = dis.readDouble();
					}

					this.Sitcorp1 = readString(dis);

					this.Sitcorp2 = readString(dis);

					this.LibMots = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoboleto = null;
					} else {
						this.Dctoboleto = dis.readDouble();
					}

					this.MPRISeq = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctomax = null;
					} else {
						this.Dctomax = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comprim = null;
					} else {
						this.Comprim = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdePeca = null;
					} else {
						this.QtdePeca = dis.readDouble();
					}

					this.ProdRes = readString(dis);

					this.EmpEstoq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrevProduc = null;
					} else {
						this.PrevProduc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrunitOri = null;
					} else {
						this.PrunitOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrliqOri = null;
					} else {
						this.PrliqOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Mva = null;
					} else {
						this.Mva = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSOrig = null;
					} else {
						this.ICMSOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDest = null;
					} else {
						this.ICMSDest = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float1 = null;
					} else {
						this.Float1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float2 = null;
					} else {
						this.Float2 = dis.readDouble();
					}

					this.DataAlter = readDate(dis);

					this.SeqOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrecoNorm = null;
					} else {
						this.PrecoNorm = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tipost = null;
					} else {
						this.Tipost = dis.readShort();
					}

					this.CodProdcli = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescarga = null;
					} else {
						this.VlrDescarga = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsDscto = null;
					} else {
						this.IcmsDscto = dis.readShort();
					}

					this.EmpSplit = readString(dis);

					this.Status = readInteger(dis);

					this.Obrigatorio = readInteger(dis);

					this.PrecoFirme = readInteger(dis);

					this.OrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctoFiscal = null;
					} else {
						this.DesctoFiscal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercMlucr = null;
					} else {
						this.PercMlucr = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescargaCalc = null;
					} else {
						this.VlrDescargaCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitCalc = null;
					} else {
						this.VlrFreTitCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuComissao = null;
					} else {
						this.VuComissao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuDebcli = null;
					} else {
						this.VuDebcli = dis.readDouble();
					}

					this.Data1Original = readDate(dis);

					this.Visao = readInteger(dis);

					this.Periodo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcms = null;
					} else {
						this.BaseIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseSt = null;
					} else {
						this.BaseSt = dis.readDouble();
					}

					this.CondPgto = readInteger(dis);

					this.UsuCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Resrede = null;
					} else {
						this.Resrede = dis.readDouble();
					}

					this.DocBloq = readString(dis);

					this.Regracomis = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcmsRed = null;
					} else {
						this.BaseIcmsRed = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsSemRed = null;
					} else {
						this.IcmsSemRed = dis.readDouble();
					}

					this.Obs1 = readString(dis);

					this.Obs2 = readString(dis);

					this.CodEmbal = readString(dis);

					this.CondPgtoCalc = readInteger(dis);

					this.CodigoMix = readInteger(dis);

					this.QtdeMix = readInteger(dis);

					this.Lote = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDesctoFiscal = null;
					} else {
						this.VlrDesctoFiscal = dis.readDouble();
					}

					this.Tamanho = readString(dis);

					this.BloqVerba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateio = null;
					} else {
						this.VlrFreTitRateio = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateioCalc = null;
					} else {
						this.VlrFreTitRateioCalc = dis.readDouble();
					}

					this.VisaoCota = readInteger(dis);

					this.PeriodoCota = readInteger(dis);

					this.VisaoCotaOriginal = readInteger(dis);

					this.PeriodoCotaOriginal = readInteger(dis);

					this.RegraEmpItem = readInteger(dis);

					this.LjPeca = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjTamanho = null;
					} else {
						this.LjTamanho = dis.readDouble();
					}

					this.LjCarga = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaA = null;
					} else {
						this.LjMedidaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjVao = null;
					} else {
						this.LjVao = dis.readDouble();
					}

					this.LjAngulo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaB = null;
					} else {
						this.LjMedidaB = dis.readDouble();
					}

					this.LjInicioViga = readInteger(dis);

					this.LjAba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaA = null;
					} else {
						this.LjAbaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaB = null;
					} else {
						this.LjAbaB = dis.readDouble();
					}

					this.Bloqluc = readInteger(dis);

					this.OrigemCota = readInteger(dis);

					this.OrigemCanc = readInteger(dis);

					this.Incsug = readInteger(dis);

					this.LjIdent = readInteger(dis);

					this.CondFreteIt = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalCalc = null;
					} else {
						this.DesctotalCalc = dis.readDouble();
					}

					this.IdPedAuto = readString(dis);

					this.LoteEstoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalAplicado = null;
					} else {
						this.DesctotalAplicado = dis.readDouble();
					}

					this.SeqOrdemCompra = readInteger(dis);

					this.SeqordCompra = readString(dis);

					this.Ordcomest = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMSFrete = null;
					} else {
						this.ValorICMSFrete = dis.readDouble();
					}

					this.Campo1 = readInteger(dis);

					this.CentroCusto = readString(dis);

					this.BloqDesc = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeDescBonif = null;
					} else {
						this.QtdeDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercDescBonif = null;
					} else {
						this.PercDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDesccUnit = null;
					} else {
						this.ValorDesccUnit = dis.readDouble();
					}

					this.SeqCota = readInteger(dis);

					this.SeqCotaOriginal = readInteger(dis);

					this.DtRegraCorte = readDate(dis);

					this.CompoeMargem = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// Float

				if (this.QuantidadeProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeProduto);
				}

				// Float

				if (this.QuantidadeAtendida == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeAtendida);
				}

				// Float

				if (this.QuantidadeCancelada == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeCancelada);
				}

				// Float

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoUnitario);
				}

				// Float

				if (this.PrecoLiquido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoLiquido);
				}

				// Float

				if (this.Desctop == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Desctop);
				}

				// Double

				if (this.DesctoIcm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoIcm);
				}

				// java.util.Date

				writeDate(this.PrevEntr, dos);

				// java.util.Date

				writeDate(this.AtdData, dos);

				// Float

				if (this.AtdQtde == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde);
				}

				// String

				writeString(this.AtdDcto, dos);

				// java.util.Date

				writeDate(this.AtdData1, dos);

				// Float

				if (this.AtdQtde1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde1);
				}

				// String

				writeString(this.AtdDcto1, dos);

				// java.util.Date

				writeDate(this.AtdData2, dos);

				// Float

				if (this.AtdQtde2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde2);
				}

				// String

				writeString(this.AtdDcto2, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// Double

				if (this.Ipi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Ipi);
				}

				// Float

				if (this.Dctoprom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoprom);
				}

				// Float

				if (this.EconVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconVlr);
				}

				// Short

				if (this.EconCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EconCalc);
				}

				// Float

				if (this.EconDcto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconDcto);
				}

				// Float

				if (this.PrecoMin == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoMin);
				}

				// String

				writeString(this.Tipo, dos);

				// Double

				if (this.Dctovda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctovda);
				}

				// Short

				if (this.TipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TipoPreco);
				}

				// Short

				if (this.EstVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EstVlr);
				}

				// Double

				if (this.Acrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Acrescimo);
				}

				// String

				writeString(this.Lpreco, dos);

				// String

				writeString(this.Grade, dos);

				// Short

				if (this.TpGrade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TpGrade);
				}

				// String

				writeString(this.CodEtiq, dos);

				// Double

				if (this.Embalagem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Embalagem);
				}

				// String

				writeString(this.MotCanc, dos);

				// Float

				if (this.PercComis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PercComis);
				}

				// String

				writeString(this.PrdCfgCar, dos);

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.BonifEcon, dos);

				// Double

				if (this.PcomisExt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PcomisExt);
				}

				// String

				writeString(this.AplicDcto, dos);

				// String

				writeString(this.Operacao, dos);

				// Double

				if (this.QtdeRes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeRes);
				}

				// String

				writeString(this.Bloqsug, dos);

				// Float

				if (this.Dctoinc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoinc);
				}

				// Double

				if (this.VlrIpi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrIpi);
				}

				// String

				writeString(this.SeqGrd, dos);

				// Integer

				writeInteger(this.SeqErp, dos);

				// java.util.Date

				writeDate(this.PrevCalc, dos);

				// String

				writeString(this.Almoxarif, dos);

				// Double

				if (this.ComisGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComisGer);
				}

				// Double

				if (this.CredProp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CredProp);
				}

				// Double

				if (this.NroEnvio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NroEnvio);
				}

				// String

				writeString(this.Chave, dos);

				// String

				writeString(this.ItemConf, dos);

				// String

				writeString(this.CvdaDcti, dos);

				// String

				writeString(this.ObsNota, dos);

				// String

				writeString(this.ObsExped, dos);

				// String

				writeString(this.ObsSepar, dos);

				// Double

				if (this.VlrSubst == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrSubst);
				}

				// Double

				if (this.DesctoGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoGer);
				}

				// Double

				if (this.VlrFreTit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTit);
				}

				// String

				writeString(this.MotTroca, dos);

				// Double

				if (this.VeconCig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VeconCig);
				}

				// Double

				if (this.CustoMp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMp);
				}

				// Short

				if (this.PliqAlt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.PliqAlt);
				}

				// String

				writeString(this.CodPrMap, dos);

				// String

				writeString(this.CodCvdAg, dos);

				// Double

				if (this.VuIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuIcms);
				}

				// Double

				if (this.VuPis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuPis);
				}

				// Double

				if (this.VuCofins == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuCofins);
				}

				// Short

				if (this.Altdtprev == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Altdtprev);
				}

				// Double

				if (this.QtdeAloc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeAloc);
				}

				// String

				writeString(this.RestrBonif, dos);

				// Double

				if (this.CustoMo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMo);
				}

				// Short

				if (this.ItControl == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItControl);
				}

				// Short

				if (this.ItPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItPermBon);
				}

				// Double

				if (this.QtdPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdPermBon);
				}

				// Double

				if (this.VlrPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrPermBon);
				}

				// Short

				if (this.Itemfemb == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Itemfemb);
				}

				// java.util.Date

				writeDate(this.Dtprevento, dos);

				// String

				writeString(this.Cfretit, dos);

				// Double

				if (this.SldVerba == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SldVerba);
				}

				// Double

				if (this.VlrBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrBonif);
				}

				// Short

				if (this.Bonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Bonif);
				}

				// Short

				if (this.Tpproduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tpproduto);
				}

				// String

				writeString(this.Backord, dos);

				// Double

				if (this.Markup == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Markup);
				}

				// Double

				if (this.QtdeBkor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeBkor);
				}

				// Double

				if (this.Prcpromoc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Prcpromoc);
				}

				// String

				writeString(this.Sitcorp1, dos);

				// String

				writeString(this.Sitcorp2, dos);

				// String

				writeString(this.LibMots, dos);

				// Double

				if (this.Dctoboleto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctoboleto);
				}

				// Integer

				writeInteger(this.MPRISeq, dos);

				// Double

				if (this.Dctomax == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctomax);
				}

				// Double

				if (this.Comprim == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comprim);
				}

				// Double

				if (this.QtdePeca == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdePeca);
				}

				// String

				writeString(this.ProdRes, dos);

				// String

				writeString(this.EmpEstoq, dos);

				// Double

				if (this.PrevProduc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrevProduc);
				}

				// Float

				if (this.PrunitOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrunitOri);
				}

				// Float

				if (this.PrliqOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrliqOri);
				}

				// Double

				if (this.Mva == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Mva);
				}

				// Double

				if (this.ICMSOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSOrig);
				}

				// Double

				if (this.ICMSDest == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDest);
				}

				// Double

				if (this.Float1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float1);
				}

				// Double

				if (this.Float2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float2);
				}

				// java.util.Date

				writeDate(this.DataAlter, dos);

				// String

				writeString(this.SeqOrig, dos);

				// Double

				if (this.PrecoNorm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoNorm);
				}

				// Short

				if (this.Tipost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tipost);
				}

				// String

				writeString(this.CodProdcli, dos);

				// Double

				if (this.VlrDescarga == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescarga);
				}

				// Short

				if (this.IcmsDscto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.IcmsDscto);
				}

				// String

				writeString(this.EmpSplit, dos);

				// Integer

				writeInteger(this.Status, dos);

				// Integer

				writeInteger(this.Obrigatorio, dos);

				// Integer

				writeInteger(this.PrecoFirme, dos);

				// String

				writeString(this.OrdCompra, dos);

				// Double

				if (this.DesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoFiscal);
				}

				// Double

				if (this.PercMlucr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercMlucr);
				}

				// Double

				if (this.VlrDescargaCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescargaCalc);
				}

				// Double

				if (this.VlrFreTitCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitCalc);
				}

				// Double

				if (this.VuComissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuComissao);
				}

				// Double

				if (this.VuDebcli == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuDebcli);
				}

				// java.util.Date

				writeDate(this.Data1Original, dos);

				// Integer

				writeInteger(this.Visao, dos);

				// Integer

				writeInteger(this.Periodo, dos);

				// Double

				if (this.BaseIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcms);
				}

				// Double

				if (this.BaseSt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseSt);
				}

				// Integer

				writeInteger(this.CondPgto, dos);

				// String

				writeString(this.UsuCanc, dos);

				// Double

				if (this.Resrede == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Resrede);
				}

				// String

				writeString(this.DocBloq, dos);

				// String

				writeString(this.Regracomis, dos);

				// Double

				if (this.BaseIcmsRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcmsRed);
				}

				// Double

				if (this.IcmsSemRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IcmsSemRed);
				}

				// String

				writeString(this.Obs1, dos);

				// String

				writeString(this.Obs2, dos);

				// String

				writeString(this.CodEmbal, dos);

				// Integer

				writeInteger(this.CondPgtoCalc, dos);

				// Integer

				writeInteger(this.CodigoMix, dos);

				// Integer

				writeInteger(this.QtdeMix, dos);

				// String

				writeString(this.Lote, dos);

				// Double

				if (this.VlrDesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDesctoFiscal);
				}

				// String

				writeString(this.Tamanho, dos);

				// Integer

				writeInteger(this.BloqVerba, dos);

				// Double

				if (this.VlrFreTitRateio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateio);
				}

				// Double

				if (this.VlrFreTitRateioCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateioCalc);
				}

				// Integer

				writeInteger(this.VisaoCota, dos);

				// Integer

				writeInteger(this.PeriodoCota, dos);

				// Integer

				writeInteger(this.VisaoCotaOriginal, dos);

				// Integer

				writeInteger(this.PeriodoCotaOriginal, dos);

				// Integer

				writeInteger(this.RegraEmpItem, dos);

				// Integer

				writeInteger(this.LjPeca, dos);

				// Double

				if (this.LjTamanho == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjTamanho);
				}

				// Integer

				writeInteger(this.LjCarga, dos);

				// Double

				if (this.LjMedidaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaA);
				}

				// Double

				if (this.LjVao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjVao);
				}

				// Integer

				writeInteger(this.LjAngulo, dos);

				// Double

				if (this.LjMedidaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaB);
				}

				// Integer

				writeInteger(this.LjInicioViga, dos);

				// Integer

				writeInteger(this.LjAba, dos);

				// Double

				if (this.LjAbaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaA);
				}

				// Double

				if (this.LjAbaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaB);
				}

				// Integer

				writeInteger(this.Bloqluc, dos);

				// Integer

				writeInteger(this.OrigemCota, dos);

				// Integer

				writeInteger(this.OrigemCanc, dos);

				// Integer

				writeInteger(this.Incsug, dos);

				// Integer

				writeInteger(this.LjIdent, dos);

				// String

				writeString(this.CondFreteIt, dos);

				// Double

				if (this.DesctotalCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalCalc);
				}

				// String

				writeString(this.IdPedAuto, dos);

				// String

				writeString(this.LoteEstoque, dos);

				// Double

				if (this.DesctotalAplicado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalAplicado);
				}

				// Integer

				writeInteger(this.SeqOrdemCompra, dos);

				// String

				writeString(this.SeqordCompra, dos);

				// String

				writeString(this.Ordcomest, dos);

				// Double

				if (this.ValorICMSFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMSFrete);
				}

				// Integer

				writeInteger(this.Campo1, dos);

				// String

				writeString(this.CentroCusto, dos);

				// Integer

				writeInteger(this.BloqDesc, dos);

				// Double

				if (this.QtdeDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeDescBonif);
				}

				// Double

				if (this.PercDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercDescBonif);
				}

				// Double

				if (this.ValorDesccUnit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDesccUnit);
				}

				// Integer

				writeInteger(this.SeqCota, dos);

				// Integer

				writeInteger(this.SeqCotaOriginal, dos);

				// java.util.Date

				writeDate(this.DtRegraCorte, dos);

				// Integer

				writeInteger(this.CompoeMargem, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// Float

				if (this.QuantidadeProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeProduto);
				}

				// Float

				if (this.QuantidadeAtendida == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeAtendida);
				}

				// Float

				if (this.QuantidadeCancelada == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeCancelada);
				}

				// Float

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoUnitario);
				}

				// Float

				if (this.PrecoLiquido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoLiquido);
				}

				// Float

				if (this.Desctop == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Desctop);
				}

				// Double

				if (this.DesctoIcm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoIcm);
				}

				// java.util.Date

				writeDate(this.PrevEntr, dos);

				// java.util.Date

				writeDate(this.AtdData, dos);

				// Float

				if (this.AtdQtde == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde);
				}

				// String

				writeString(this.AtdDcto, dos);

				// java.util.Date

				writeDate(this.AtdData1, dos);

				// Float

				if (this.AtdQtde1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde1);
				}

				// String

				writeString(this.AtdDcto1, dos);

				// java.util.Date

				writeDate(this.AtdData2, dos);

				// Float

				if (this.AtdQtde2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde2);
				}

				// String

				writeString(this.AtdDcto2, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// Double

				if (this.Ipi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Ipi);
				}

				// Float

				if (this.Dctoprom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoprom);
				}

				// Float

				if (this.EconVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconVlr);
				}

				// Short

				if (this.EconCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EconCalc);
				}

				// Float

				if (this.EconDcto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconDcto);
				}

				// Float

				if (this.PrecoMin == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoMin);
				}

				// String

				writeString(this.Tipo, dos);

				// Double

				if (this.Dctovda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctovda);
				}

				// Short

				if (this.TipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TipoPreco);
				}

				// Short

				if (this.EstVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EstVlr);
				}

				// Double

				if (this.Acrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Acrescimo);
				}

				// String

				writeString(this.Lpreco, dos);

				// String

				writeString(this.Grade, dos);

				// Short

				if (this.TpGrade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TpGrade);
				}

				// String

				writeString(this.CodEtiq, dos);

				// Double

				if (this.Embalagem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Embalagem);
				}

				// String

				writeString(this.MotCanc, dos);

				// Float

				if (this.PercComis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PercComis);
				}

				// String

				writeString(this.PrdCfgCar, dos);

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.BonifEcon, dos);

				// Double

				if (this.PcomisExt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PcomisExt);
				}

				// String

				writeString(this.AplicDcto, dos);

				// String

				writeString(this.Operacao, dos);

				// Double

				if (this.QtdeRes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeRes);
				}

				// String

				writeString(this.Bloqsug, dos);

				// Float

				if (this.Dctoinc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoinc);
				}

				// Double

				if (this.VlrIpi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrIpi);
				}

				// String

				writeString(this.SeqGrd, dos);

				// Integer

				writeInteger(this.SeqErp, dos);

				// java.util.Date

				writeDate(this.PrevCalc, dos);

				// String

				writeString(this.Almoxarif, dos);

				// Double

				if (this.ComisGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComisGer);
				}

				// Double

				if (this.CredProp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CredProp);
				}

				// Double

				if (this.NroEnvio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NroEnvio);
				}

				// String

				writeString(this.Chave, dos);

				// String

				writeString(this.ItemConf, dos);

				// String

				writeString(this.CvdaDcti, dos);

				// String

				writeString(this.ObsNota, dos);

				// String

				writeString(this.ObsExped, dos);

				// String

				writeString(this.ObsSepar, dos);

				// Double

				if (this.VlrSubst == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrSubst);
				}

				// Double

				if (this.DesctoGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoGer);
				}

				// Double

				if (this.VlrFreTit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTit);
				}

				// String

				writeString(this.MotTroca, dos);

				// Double

				if (this.VeconCig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VeconCig);
				}

				// Double

				if (this.CustoMp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMp);
				}

				// Short

				if (this.PliqAlt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.PliqAlt);
				}

				// String

				writeString(this.CodPrMap, dos);

				// String

				writeString(this.CodCvdAg, dos);

				// Double

				if (this.VuIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuIcms);
				}

				// Double

				if (this.VuPis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuPis);
				}

				// Double

				if (this.VuCofins == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuCofins);
				}

				// Short

				if (this.Altdtprev == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Altdtprev);
				}

				// Double

				if (this.QtdeAloc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeAloc);
				}

				// String

				writeString(this.RestrBonif, dos);

				// Double

				if (this.CustoMo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMo);
				}

				// Short

				if (this.ItControl == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItControl);
				}

				// Short

				if (this.ItPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItPermBon);
				}

				// Double

				if (this.QtdPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdPermBon);
				}

				// Double

				if (this.VlrPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrPermBon);
				}

				// Short

				if (this.Itemfemb == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Itemfemb);
				}

				// java.util.Date

				writeDate(this.Dtprevento, dos);

				// String

				writeString(this.Cfretit, dos);

				// Double

				if (this.SldVerba == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SldVerba);
				}

				// Double

				if (this.VlrBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrBonif);
				}

				// Short

				if (this.Bonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Bonif);
				}

				// Short

				if (this.Tpproduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tpproduto);
				}

				// String

				writeString(this.Backord, dos);

				// Double

				if (this.Markup == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Markup);
				}

				// Double

				if (this.QtdeBkor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeBkor);
				}

				// Double

				if (this.Prcpromoc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Prcpromoc);
				}

				// String

				writeString(this.Sitcorp1, dos);

				// String

				writeString(this.Sitcorp2, dos);

				// String

				writeString(this.LibMots, dos);

				// Double

				if (this.Dctoboleto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctoboleto);
				}

				// Integer

				writeInteger(this.MPRISeq, dos);

				// Double

				if (this.Dctomax == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctomax);
				}

				// Double

				if (this.Comprim == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comprim);
				}

				// Double

				if (this.QtdePeca == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdePeca);
				}

				// String

				writeString(this.ProdRes, dos);

				// String

				writeString(this.EmpEstoq, dos);

				// Double

				if (this.PrevProduc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrevProduc);
				}

				// Float

				if (this.PrunitOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrunitOri);
				}

				// Float

				if (this.PrliqOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrliqOri);
				}

				// Double

				if (this.Mva == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Mva);
				}

				// Double

				if (this.ICMSOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSOrig);
				}

				// Double

				if (this.ICMSDest == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDest);
				}

				// Double

				if (this.Float1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float1);
				}

				// Double

				if (this.Float2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float2);
				}

				// java.util.Date

				writeDate(this.DataAlter, dos);

				// String

				writeString(this.SeqOrig, dos);

				// Double

				if (this.PrecoNorm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoNorm);
				}

				// Short

				if (this.Tipost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tipost);
				}

				// String

				writeString(this.CodProdcli, dos);

				// Double

				if (this.VlrDescarga == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescarga);
				}

				// Short

				if (this.IcmsDscto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.IcmsDscto);
				}

				// String

				writeString(this.EmpSplit, dos);

				// Integer

				writeInteger(this.Status, dos);

				// Integer

				writeInteger(this.Obrigatorio, dos);

				// Integer

				writeInteger(this.PrecoFirme, dos);

				// String

				writeString(this.OrdCompra, dos);

				// Double

				if (this.DesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoFiscal);
				}

				// Double

				if (this.PercMlucr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercMlucr);
				}

				// Double

				if (this.VlrDescargaCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescargaCalc);
				}

				// Double

				if (this.VlrFreTitCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitCalc);
				}

				// Double

				if (this.VuComissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuComissao);
				}

				// Double

				if (this.VuDebcli == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuDebcli);
				}

				// java.util.Date

				writeDate(this.Data1Original, dos);

				// Integer

				writeInteger(this.Visao, dos);

				// Integer

				writeInteger(this.Periodo, dos);

				// Double

				if (this.BaseIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcms);
				}

				// Double

				if (this.BaseSt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseSt);
				}

				// Integer

				writeInteger(this.CondPgto, dos);

				// String

				writeString(this.UsuCanc, dos);

				// Double

				if (this.Resrede == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Resrede);
				}

				// String

				writeString(this.DocBloq, dos);

				// String

				writeString(this.Regracomis, dos);

				// Double

				if (this.BaseIcmsRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcmsRed);
				}

				// Double

				if (this.IcmsSemRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IcmsSemRed);
				}

				// String

				writeString(this.Obs1, dos);

				// String

				writeString(this.Obs2, dos);

				// String

				writeString(this.CodEmbal, dos);

				// Integer

				writeInteger(this.CondPgtoCalc, dos);

				// Integer

				writeInteger(this.CodigoMix, dos);

				// Integer

				writeInteger(this.QtdeMix, dos);

				// String

				writeString(this.Lote, dos);

				// Double

				if (this.VlrDesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDesctoFiscal);
				}

				// String

				writeString(this.Tamanho, dos);

				// Integer

				writeInteger(this.BloqVerba, dos);

				// Double

				if (this.VlrFreTitRateio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateio);
				}

				// Double

				if (this.VlrFreTitRateioCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateioCalc);
				}

				// Integer

				writeInteger(this.VisaoCota, dos);

				// Integer

				writeInteger(this.PeriodoCota, dos);

				// Integer

				writeInteger(this.VisaoCotaOriginal, dos);

				// Integer

				writeInteger(this.PeriodoCotaOriginal, dos);

				// Integer

				writeInteger(this.RegraEmpItem, dos);

				// Integer

				writeInteger(this.LjPeca, dos);

				// Double

				if (this.LjTamanho == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjTamanho);
				}

				// Integer

				writeInteger(this.LjCarga, dos);

				// Double

				if (this.LjMedidaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaA);
				}

				// Double

				if (this.LjVao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjVao);
				}

				// Integer

				writeInteger(this.LjAngulo, dos);

				// Double

				if (this.LjMedidaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaB);
				}

				// Integer

				writeInteger(this.LjInicioViga, dos);

				// Integer

				writeInteger(this.LjAba, dos);

				// Double

				if (this.LjAbaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaA);
				}

				// Double

				if (this.LjAbaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaB);
				}

				// Integer

				writeInteger(this.Bloqluc, dos);

				// Integer

				writeInteger(this.OrigemCota, dos);

				// Integer

				writeInteger(this.OrigemCanc, dos);

				// Integer

				writeInteger(this.Incsug, dos);

				// Integer

				writeInteger(this.LjIdent, dos);

				// String

				writeString(this.CondFreteIt, dos);

				// Double

				if (this.DesctotalCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalCalc);
				}

				// String

				writeString(this.IdPedAuto, dos);

				// String

				writeString(this.LoteEstoque, dos);

				// Double

				if (this.DesctotalAplicado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalAplicado);
				}

				// Integer

				writeInteger(this.SeqOrdemCompra, dos);

				// String

				writeString(this.SeqordCompra, dos);

				// String

				writeString(this.Ordcomest, dos);

				// Double

				if (this.ValorICMSFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMSFrete);
				}

				// Integer

				writeInteger(this.Campo1, dos);

				// String

				writeString(this.CentroCusto, dos);

				// Integer

				writeInteger(this.BloqDesc, dos);

				// Double

				if (this.QtdeDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeDescBonif);
				}

				// Double

				if (this.PercDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercDescBonif);
				}

				// Double

				if (this.ValorDesccUnit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDesccUnit);
				}

				// Integer

				writeInteger(this.SeqCota, dos);

				// Integer

				writeInteger(this.SeqCotaOriginal, dos);

				// java.util.Date

				writeDate(this.DtRegraCorte, dos);

				// Integer

				writeInteger(this.CompoeMargem, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",SequenciaItem=" + String.valueOf(SequenciaItem));
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",QuantidadeProduto=" + String.valueOf(QuantidadeProduto));
			sb.append(",QuantidadeAtendida=" + String.valueOf(QuantidadeAtendida));
			sb.append(",QuantidadeCancelada=" + String.valueOf(QuantidadeCancelada));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",PrecoLiquido=" + String.valueOf(PrecoLiquido));
			sb.append(",Desctop=" + String.valueOf(Desctop));
			sb.append(",DesctoIcm=" + String.valueOf(DesctoIcm));
			sb.append(",PrevEntr=" + String.valueOf(PrevEntr));
			sb.append(",AtdData=" + String.valueOf(AtdData));
			sb.append(",AtdQtde=" + String.valueOf(AtdQtde));
			sb.append(",AtdDcto=" + AtdDcto);
			sb.append(",AtdData1=" + String.valueOf(AtdData1));
			sb.append(",AtdQtde1=" + String.valueOf(AtdQtde1));
			sb.append(",AtdDcto1=" + AtdDcto1);
			sb.append(",AtdData2=" + String.valueOf(AtdData2));
			sb.append(",AtdQtde2=" + String.valueOf(AtdQtde2));
			sb.append(",AtdDcto2=" + AtdDcto2);
			sb.append(",SituacaoPedido=" + String.valueOf(SituacaoPedido));
			sb.append(",Ipi=" + String.valueOf(Ipi));
			sb.append(",Dctoprom=" + String.valueOf(Dctoprom));
			sb.append(",EconVlr=" + String.valueOf(EconVlr));
			sb.append(",EconCalc=" + String.valueOf(EconCalc));
			sb.append(",EconDcto=" + String.valueOf(EconDcto));
			sb.append(",PrecoMin=" + String.valueOf(PrecoMin));
			sb.append(",Tipo=" + Tipo);
			sb.append(",Dctovda=" + String.valueOf(Dctovda));
			sb.append(",TipoPreco=" + String.valueOf(TipoPreco));
			sb.append(",EstVlr=" + String.valueOf(EstVlr));
			sb.append(",Acrescimo=" + String.valueOf(Acrescimo));
			sb.append(",Lpreco=" + Lpreco);
			sb.append(",Grade=" + Grade);
			sb.append(",TpGrade=" + String.valueOf(TpGrade));
			sb.append(",CodEtiq=" + CodEtiq);
			sb.append(",Embalagem=" + String.valueOf(Embalagem));
			sb.append(",MotCanc=" + MotCanc);
			sb.append(",PercComis=" + String.valueOf(PercComis));
			sb.append(",PrdCfgCar=" + PrdCfgCar);
			sb.append(",Observacao=" + Observacao);
			sb.append(",BonifEcon=" + BonifEcon);
			sb.append(",PcomisExt=" + String.valueOf(PcomisExt));
			sb.append(",AplicDcto=" + AplicDcto);
			sb.append(",Operacao=" + Operacao);
			sb.append(",QtdeRes=" + String.valueOf(QtdeRes));
			sb.append(",Bloqsug=" + Bloqsug);
			sb.append(",Dctoinc=" + String.valueOf(Dctoinc));
			sb.append(",VlrIpi=" + String.valueOf(VlrIpi));
			sb.append(",SeqGrd=" + SeqGrd);
			sb.append(",SeqErp=" + String.valueOf(SeqErp));
			sb.append(",PrevCalc=" + String.valueOf(PrevCalc));
			sb.append(",Almoxarif=" + Almoxarif);
			sb.append(",ComisGer=" + String.valueOf(ComisGer));
			sb.append(",CredProp=" + String.valueOf(CredProp));
			sb.append(",NroEnvio=" + String.valueOf(NroEnvio));
			sb.append(",Chave=" + Chave);
			sb.append(",ItemConf=" + ItemConf);
			sb.append(",CvdaDcti=" + CvdaDcti);
			sb.append(",ObsNota=" + ObsNota);
			sb.append(",ObsExped=" + ObsExped);
			sb.append(",ObsSepar=" + ObsSepar);
			sb.append(",VlrSubst=" + String.valueOf(VlrSubst));
			sb.append(",DesctoGer=" + String.valueOf(DesctoGer));
			sb.append(",VlrFreTit=" + String.valueOf(VlrFreTit));
			sb.append(",MotTroca=" + MotTroca);
			sb.append(",VeconCig=" + String.valueOf(VeconCig));
			sb.append(",CustoMp=" + String.valueOf(CustoMp));
			sb.append(",PliqAlt=" + String.valueOf(PliqAlt));
			sb.append(",CodPrMap=" + CodPrMap);
			sb.append(",CodCvdAg=" + CodCvdAg);
			sb.append(",VuIcms=" + String.valueOf(VuIcms));
			sb.append(",VuPis=" + String.valueOf(VuPis));
			sb.append(",VuCofins=" + String.valueOf(VuCofins));
			sb.append(",Altdtprev=" + String.valueOf(Altdtprev));
			sb.append(",QtdeAloc=" + String.valueOf(QtdeAloc));
			sb.append(",RestrBonif=" + RestrBonif);
			sb.append(",CustoMo=" + String.valueOf(CustoMo));
			sb.append(",ItControl=" + String.valueOf(ItControl));
			sb.append(",ItPermBon=" + String.valueOf(ItPermBon));
			sb.append(",QtdPermBon=" + String.valueOf(QtdPermBon));
			sb.append(",VlrPermBon=" + String.valueOf(VlrPermBon));
			sb.append(",Itemfemb=" + String.valueOf(Itemfemb));
			sb.append(",Dtprevento=" + String.valueOf(Dtprevento));
			sb.append(",Cfretit=" + Cfretit);
			sb.append(",SldVerba=" + String.valueOf(SldVerba));
			sb.append(",VlrBonif=" + String.valueOf(VlrBonif));
			sb.append(",Bonif=" + String.valueOf(Bonif));
			sb.append(",Tpproduto=" + String.valueOf(Tpproduto));
			sb.append(",Backord=" + Backord);
			sb.append(",Markup=" + String.valueOf(Markup));
			sb.append(",QtdeBkor=" + String.valueOf(QtdeBkor));
			sb.append(",Prcpromoc=" + String.valueOf(Prcpromoc));
			sb.append(",Sitcorp1=" + Sitcorp1);
			sb.append(",Sitcorp2=" + Sitcorp2);
			sb.append(",LibMots=" + LibMots);
			sb.append(",Dctoboleto=" + String.valueOf(Dctoboleto));
			sb.append(",MPRISeq=" + String.valueOf(MPRISeq));
			sb.append(",Dctomax=" + String.valueOf(Dctomax));
			sb.append(",Comprim=" + String.valueOf(Comprim));
			sb.append(",QtdePeca=" + String.valueOf(QtdePeca));
			sb.append(",ProdRes=" + ProdRes);
			sb.append(",EmpEstoq=" + EmpEstoq);
			sb.append(",PrevProduc=" + String.valueOf(PrevProduc));
			sb.append(",PrunitOri=" + String.valueOf(PrunitOri));
			sb.append(",PrliqOri=" + String.valueOf(PrliqOri));
			sb.append(",Mva=" + String.valueOf(Mva));
			sb.append(",ICMSOrig=" + String.valueOf(ICMSOrig));
			sb.append(",ICMSDest=" + String.valueOf(ICMSDest));
			sb.append(",Float1=" + String.valueOf(Float1));
			sb.append(",Float2=" + String.valueOf(Float2));
			sb.append(",DataAlter=" + String.valueOf(DataAlter));
			sb.append(",SeqOrig=" + SeqOrig);
			sb.append(",PrecoNorm=" + String.valueOf(PrecoNorm));
			sb.append(",Tipost=" + String.valueOf(Tipost));
			sb.append(",CodProdcli=" + CodProdcli);
			sb.append(",VlrDescarga=" + String.valueOf(VlrDescarga));
			sb.append(",IcmsDscto=" + String.valueOf(IcmsDscto));
			sb.append(",EmpSplit=" + EmpSplit);
			sb.append(",Status=" + String.valueOf(Status));
			sb.append(",Obrigatorio=" + String.valueOf(Obrigatorio));
			sb.append(",PrecoFirme=" + String.valueOf(PrecoFirme));
			sb.append(",OrdCompra=" + OrdCompra);
			sb.append(",DesctoFiscal=" + String.valueOf(DesctoFiscal));
			sb.append(",PercMlucr=" + String.valueOf(PercMlucr));
			sb.append(",VlrDescargaCalc=" + String.valueOf(VlrDescargaCalc));
			sb.append(",VlrFreTitCalc=" + String.valueOf(VlrFreTitCalc));
			sb.append(",VuComissao=" + String.valueOf(VuComissao));
			sb.append(",VuDebcli=" + String.valueOf(VuDebcli));
			sb.append(",Data1Original=" + String.valueOf(Data1Original));
			sb.append(",Visao=" + String.valueOf(Visao));
			sb.append(",Periodo=" + String.valueOf(Periodo));
			sb.append(",BaseIcms=" + String.valueOf(BaseIcms));
			sb.append(",BaseSt=" + String.valueOf(BaseSt));
			sb.append(",CondPgto=" + String.valueOf(CondPgto));
			sb.append(",UsuCanc=" + UsuCanc);
			sb.append(",Resrede=" + String.valueOf(Resrede));
			sb.append(",DocBloq=" + DocBloq);
			sb.append(",Regracomis=" + Regracomis);
			sb.append(",BaseIcmsRed=" + String.valueOf(BaseIcmsRed));
			sb.append(",IcmsSemRed=" + String.valueOf(IcmsSemRed));
			sb.append(",Obs1=" + Obs1);
			sb.append(",Obs2=" + Obs2);
			sb.append(",CodEmbal=" + CodEmbal);
			sb.append(",CondPgtoCalc=" + String.valueOf(CondPgtoCalc));
			sb.append(",CodigoMix=" + String.valueOf(CodigoMix));
			sb.append(",QtdeMix=" + String.valueOf(QtdeMix));
			sb.append(",Lote=" + Lote);
			sb.append(",VlrDesctoFiscal=" + String.valueOf(VlrDesctoFiscal));
			sb.append(",Tamanho=" + Tamanho);
			sb.append(",BloqVerba=" + String.valueOf(BloqVerba));
			sb.append(",VlrFreTitRateio=" + String.valueOf(VlrFreTitRateio));
			sb.append(",VlrFreTitRateioCalc=" + String.valueOf(VlrFreTitRateioCalc));
			sb.append(",VisaoCota=" + String.valueOf(VisaoCota));
			sb.append(",PeriodoCota=" + String.valueOf(PeriodoCota));
			sb.append(",VisaoCotaOriginal=" + String.valueOf(VisaoCotaOriginal));
			sb.append(",PeriodoCotaOriginal=" + String.valueOf(PeriodoCotaOriginal));
			sb.append(",RegraEmpItem=" + String.valueOf(RegraEmpItem));
			sb.append(",LjPeca=" + String.valueOf(LjPeca));
			sb.append(",LjTamanho=" + String.valueOf(LjTamanho));
			sb.append(",LjCarga=" + String.valueOf(LjCarga));
			sb.append(",LjMedidaA=" + String.valueOf(LjMedidaA));
			sb.append(",LjVao=" + String.valueOf(LjVao));
			sb.append(",LjAngulo=" + String.valueOf(LjAngulo));
			sb.append(",LjMedidaB=" + String.valueOf(LjMedidaB));
			sb.append(",LjInicioViga=" + String.valueOf(LjInicioViga));
			sb.append(",LjAba=" + String.valueOf(LjAba));
			sb.append(",LjAbaA=" + String.valueOf(LjAbaA));
			sb.append(",LjAbaB=" + String.valueOf(LjAbaB));
			sb.append(",Bloqluc=" + String.valueOf(Bloqluc));
			sb.append(",OrigemCota=" + String.valueOf(OrigemCota));
			sb.append(",OrigemCanc=" + String.valueOf(OrigemCanc));
			sb.append(",Incsug=" + String.valueOf(Incsug));
			sb.append(",LjIdent=" + String.valueOf(LjIdent));
			sb.append(",CondFreteIt=" + CondFreteIt);
			sb.append(",DesctotalCalc=" + String.valueOf(DesctotalCalc));
			sb.append(",IdPedAuto=" + IdPedAuto);
			sb.append(",LoteEstoque=" + LoteEstoque);
			sb.append(",DesctotalAplicado=" + String.valueOf(DesctotalAplicado));
			sb.append(",SeqOrdemCompra=" + String.valueOf(SeqOrdemCompra));
			sb.append(",SeqordCompra=" + SeqordCompra);
			sb.append(",Ordcomest=" + Ordcomest);
			sb.append(",ValorICMSFrete=" + String.valueOf(ValorICMSFrete));
			sb.append(",Campo1=" + String.valueOf(Campo1));
			sb.append(",CentroCusto=" + CentroCusto);
			sb.append(",BloqDesc=" + String.valueOf(BloqDesc));
			sb.append(",QtdeDescBonif=" + String.valueOf(QtdeDescBonif));
			sb.append(",PercDescBonif=" + String.valueOf(PercDescBonif));
			sb.append(",ValorDesccUnit=" + String.valueOf(ValorDesccUnit));
			sb.append(",SeqCota=" + String.valueOf(SeqCota));
			sb.append(",SeqCotaOriginal=" + String.valueOf(SeqCotaOriginal));
			sb.append(",DtRegraCorte=" + String.valueOf(DtRegraCorte));
			sb.append(",CompoeMargem=" + String.valueOf(CompoeMargem));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (SequenciaItem == null) {
				sb.append("<null>");
			} else {
				sb.append(SequenciaItem);
			}

			sb.append("|");

			if (CodigoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoProduto);
			}

			sb.append("|");

			if (QuantidadeProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeProduto);
			}

			sb.append("|");

			if (QuantidadeAtendida == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeAtendida);
			}

			sb.append("|");

			if (QuantidadeCancelada == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeCancelada);
			}

			sb.append("|");

			if (PrecoUnitario == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoUnitario);
			}

			sb.append("|");

			if (PrecoLiquido == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoLiquido);
			}

			sb.append("|");

			if (Desctop == null) {
				sb.append("<null>");
			} else {
				sb.append(Desctop);
			}

			sb.append("|");

			if (DesctoIcm == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoIcm);
			}

			sb.append("|");

			if (PrevEntr == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevEntr);
			}

			sb.append("|");

			if (AtdData == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData);
			}

			sb.append("|");

			if (AtdQtde == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde);
			}

			sb.append("|");

			if (AtdDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto);
			}

			sb.append("|");

			if (AtdData1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData1);
			}

			sb.append("|");

			if (AtdQtde1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde1);
			}

			sb.append("|");

			if (AtdDcto1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto1);
			}

			sb.append("|");

			if (AtdData2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData2);
			}

			sb.append("|");

			if (AtdQtde2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde2);
			}

			sb.append("|");

			if (AtdDcto2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto2);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (Ipi == null) {
				sb.append("<null>");
			} else {
				sb.append(Ipi);
			}

			sb.append("|");

			if (Dctoprom == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoprom);
			}

			sb.append("|");

			if (EconVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(EconVlr);
			}

			sb.append("|");

			if (EconCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(EconCalc);
			}

			sb.append("|");

			if (EconDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(EconDcto);
			}

			sb.append("|");

			if (PrecoMin == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoMin);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (Dctovda == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctovda);
			}

			sb.append("|");

			if (TipoPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPreco);
			}

			sb.append("|");

			if (EstVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(EstVlr);
			}

			sb.append("|");

			if (Acrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(Acrescimo);
			}

			sb.append("|");

			if (Lpreco == null) {
				sb.append("<null>");
			} else {
				sb.append(Lpreco);
			}

			sb.append("|");

			if (Grade == null) {
				sb.append("<null>");
			} else {
				sb.append(Grade);
			}

			sb.append("|");

			if (TpGrade == null) {
				sb.append("<null>");
			} else {
				sb.append(TpGrade);
			}

			sb.append("|");

			if (CodEtiq == null) {
				sb.append("<null>");
			} else {
				sb.append(CodEtiq);
			}

			sb.append("|");

			if (Embalagem == null) {
				sb.append("<null>");
			} else {
				sb.append(Embalagem);
			}

			sb.append("|");

			if (MotCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(MotCanc);
			}

			sb.append("|");

			if (PercComis == null) {
				sb.append("<null>");
			} else {
				sb.append(PercComis);
			}

			sb.append("|");

			if (PrdCfgCar == null) {
				sb.append("<null>");
			} else {
				sb.append(PrdCfgCar);
			}

			sb.append("|");

			if (Observacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao);
			}

			sb.append("|");

			if (BonifEcon == null) {
				sb.append("<null>");
			} else {
				sb.append(BonifEcon);
			}

			sb.append("|");

			if (PcomisExt == null) {
				sb.append("<null>");
			} else {
				sb.append(PcomisExt);
			}

			sb.append("|");

			if (AplicDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(AplicDcto);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			if (QtdeRes == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeRes);
			}

			sb.append("|");

			if (Bloqsug == null) {
				sb.append("<null>");
			} else {
				sb.append(Bloqsug);
			}

			sb.append("|");

			if (Dctoinc == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoinc);
			}

			sb.append("|");

			if (VlrIpi == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrIpi);
			}

			sb.append("|");

			if (SeqGrd == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqGrd);
			}

			sb.append("|");

			if (SeqErp == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqErp);
			}

			sb.append("|");

			if (PrevCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevCalc);
			}

			sb.append("|");

			if (Almoxarif == null) {
				sb.append("<null>");
			} else {
				sb.append(Almoxarif);
			}

			sb.append("|");

			if (ComisGer == null) {
				sb.append("<null>");
			} else {
				sb.append(ComisGer);
			}

			sb.append("|");

			if (CredProp == null) {
				sb.append("<null>");
			} else {
				sb.append(CredProp);
			}

			sb.append("|");

			if (NroEnvio == null) {
				sb.append("<null>");
			} else {
				sb.append(NroEnvio);
			}

			sb.append("|");

			if (Chave == null) {
				sb.append("<null>");
			} else {
				sb.append(Chave);
			}

			sb.append("|");

			if (ItemConf == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemConf);
			}

			sb.append("|");

			if (CvdaDcti == null) {
				sb.append("<null>");
			} else {
				sb.append(CvdaDcti);
			}

			sb.append("|");

			if (ObsNota == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsNota);
			}

			sb.append("|");

			if (ObsExped == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsExped);
			}

			sb.append("|");

			if (ObsSepar == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsSepar);
			}

			sb.append("|");

			if (VlrSubst == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrSubst);
			}

			sb.append("|");

			if (DesctoGer == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoGer);
			}

			sb.append("|");

			if (VlrFreTit == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTit);
			}

			sb.append("|");

			if (MotTroca == null) {
				sb.append("<null>");
			} else {
				sb.append(MotTroca);
			}

			sb.append("|");

			if (VeconCig == null) {
				sb.append("<null>");
			} else {
				sb.append(VeconCig);
			}

			sb.append("|");

			if (CustoMp == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoMp);
			}

			sb.append("|");

			if (PliqAlt == null) {
				sb.append("<null>");
			} else {
				sb.append(PliqAlt);
			}

			sb.append("|");

			if (CodPrMap == null) {
				sb.append("<null>");
			} else {
				sb.append(CodPrMap);
			}

			sb.append("|");

			if (CodCvdAg == null) {
				sb.append("<null>");
			} else {
				sb.append(CodCvdAg);
			}

			sb.append("|");

			if (VuIcms == null) {
				sb.append("<null>");
			} else {
				sb.append(VuIcms);
			}

			sb.append("|");

			if (VuPis == null) {
				sb.append("<null>");
			} else {
				sb.append(VuPis);
			}

			sb.append("|");

			if (VuCofins == null) {
				sb.append("<null>");
			} else {
				sb.append(VuCofins);
			}

			sb.append("|");

			if (Altdtprev == null) {
				sb.append("<null>");
			} else {
				sb.append(Altdtprev);
			}

			sb.append("|");

			if (QtdeAloc == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeAloc);
			}

			sb.append("|");

			if (RestrBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(RestrBonif);
			}

			sb.append("|");

			if (CustoMo == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoMo);
			}

			sb.append("|");

			if (ItControl == null) {
				sb.append("<null>");
			} else {
				sb.append(ItControl);
			}

			sb.append("|");

			if (ItPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(ItPermBon);
			}

			sb.append("|");

			if (QtdPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdPermBon);
			}

			sb.append("|");

			if (VlrPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrPermBon);
			}

			sb.append("|");

			if (Itemfemb == null) {
				sb.append("<null>");
			} else {
				sb.append(Itemfemb);
			}

			sb.append("|");

			if (Dtprevento == null) {
				sb.append("<null>");
			} else {
				sb.append(Dtprevento);
			}

			sb.append("|");

			if (Cfretit == null) {
				sb.append("<null>");
			} else {
				sb.append(Cfretit);
			}

			sb.append("|");

			if (SldVerba == null) {
				sb.append("<null>");
			} else {
				sb.append(SldVerba);
			}

			sb.append("|");

			if (VlrBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrBonif);
			}

			sb.append("|");

			if (Bonif == null) {
				sb.append("<null>");
			} else {
				sb.append(Bonif);
			}

			sb.append("|");

			if (Tpproduto == null) {
				sb.append("<null>");
			} else {
				sb.append(Tpproduto);
			}

			sb.append("|");

			if (Backord == null) {
				sb.append("<null>");
			} else {
				sb.append(Backord);
			}

			sb.append("|");

			if (Markup == null) {
				sb.append("<null>");
			} else {
				sb.append(Markup);
			}

			sb.append("|");

			if (QtdeBkor == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeBkor);
			}

			sb.append("|");

			if (Prcpromoc == null) {
				sb.append("<null>");
			} else {
				sb.append(Prcpromoc);
			}

			sb.append("|");

			if (Sitcorp1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Sitcorp1);
			}

			sb.append("|");

			if (Sitcorp2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Sitcorp2);
			}

			sb.append("|");

			if (LibMots == null) {
				sb.append("<null>");
			} else {
				sb.append(LibMots);
			}

			sb.append("|");

			if (Dctoboleto == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoboleto);
			}

			sb.append("|");

			if (MPRISeq == null) {
				sb.append("<null>");
			} else {
				sb.append(MPRISeq);
			}

			sb.append("|");

			if (Dctomax == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctomax);
			}

			sb.append("|");

			if (Comprim == null) {
				sb.append("<null>");
			} else {
				sb.append(Comprim);
			}

			sb.append("|");

			if (QtdePeca == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdePeca);
			}

			sb.append("|");

			if (ProdRes == null) {
				sb.append("<null>");
			} else {
				sb.append(ProdRes);
			}

			sb.append("|");

			if (EmpEstoq == null) {
				sb.append("<null>");
			} else {
				sb.append(EmpEstoq);
			}

			sb.append("|");

			if (PrevProduc == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevProduc);
			}

			sb.append("|");

			if (PrunitOri == null) {
				sb.append("<null>");
			} else {
				sb.append(PrunitOri);
			}

			sb.append("|");

			if (PrliqOri == null) {
				sb.append("<null>");
			} else {
				sb.append(PrliqOri);
			}

			sb.append("|");

			if (Mva == null) {
				sb.append("<null>");
			} else {
				sb.append(Mva);
			}

			sb.append("|");

			if (ICMSOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSOrig);
			}

			sb.append("|");

			if (ICMSDest == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSDest);
			}

			sb.append("|");

			if (Float1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Float1);
			}

			sb.append("|");

			if (Float2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Float2);
			}

			sb.append("|");

			if (DataAlter == null) {
				sb.append("<null>");
			} else {
				sb.append(DataAlter);
			}

			sb.append("|");

			if (SeqOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqOrig);
			}

			sb.append("|");

			if (PrecoNorm == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoNorm);
			}

			sb.append("|");

			if (Tipost == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipost);
			}

			sb.append("|");

			if (CodProdcli == null) {
				sb.append("<null>");
			} else {
				sb.append(CodProdcli);
			}

			sb.append("|");

			if (VlrDescarga == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDescarga);
			}

			sb.append("|");

			if (IcmsDscto == null) {
				sb.append("<null>");
			} else {
				sb.append(IcmsDscto);
			}

			sb.append("|");

			if (EmpSplit == null) {
				sb.append("<null>");
			} else {
				sb.append(EmpSplit);
			}

			sb.append("|");

			if (Status == null) {
				sb.append("<null>");
			} else {
				sb.append(Status);
			}

			sb.append("|");

			if (Obrigatorio == null) {
				sb.append("<null>");
			} else {
				sb.append(Obrigatorio);
			}

			sb.append("|");

			if (PrecoFirme == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoFirme);
			}

			sb.append("|");

			if (OrdCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(OrdCompra);
			}

			sb.append("|");

			if (DesctoFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoFiscal);
			}

			sb.append("|");

			if (PercMlucr == null) {
				sb.append("<null>");
			} else {
				sb.append(PercMlucr);
			}

			sb.append("|");

			if (VlrDescargaCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDescargaCalc);
			}

			sb.append("|");

			if (VlrFreTitCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitCalc);
			}

			sb.append("|");

			if (VuComissao == null) {
				sb.append("<null>");
			} else {
				sb.append(VuComissao);
			}

			sb.append("|");

			if (VuDebcli == null) {
				sb.append("<null>");
			} else {
				sb.append(VuDebcli);
			}

			sb.append("|");

			if (Data1Original == null) {
				sb.append("<null>");
			} else {
				sb.append(Data1Original);
			}

			sb.append("|");

			if (Visao == null) {
				sb.append("<null>");
			} else {
				sb.append(Visao);
			}

			sb.append("|");

			if (Periodo == null) {
				sb.append("<null>");
			} else {
				sb.append(Periodo);
			}

			sb.append("|");

			if (BaseIcms == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIcms);
			}

			sb.append("|");

			if (BaseSt == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseSt);
			}

			sb.append("|");

			if (CondPgto == null) {
				sb.append("<null>");
			} else {
				sb.append(CondPgto);
			}

			sb.append("|");

			if (UsuCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(UsuCanc);
			}

			sb.append("|");

			if (Resrede == null) {
				sb.append("<null>");
			} else {
				sb.append(Resrede);
			}

			sb.append("|");

			if (DocBloq == null) {
				sb.append("<null>");
			} else {
				sb.append(DocBloq);
			}

			sb.append("|");

			if (Regracomis == null) {
				sb.append("<null>");
			} else {
				sb.append(Regracomis);
			}

			sb.append("|");

			if (BaseIcmsRed == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIcmsRed);
			}

			sb.append("|");

			if (IcmsSemRed == null) {
				sb.append("<null>");
			} else {
				sb.append(IcmsSemRed);
			}

			sb.append("|");

			if (Obs1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Obs1);
			}

			sb.append("|");

			if (Obs2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Obs2);
			}

			sb.append("|");

			if (CodEmbal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodEmbal);
			}

			sb.append("|");

			if (CondPgtoCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(CondPgtoCalc);
			}

			sb.append("|");

			if (CodigoMix == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMix);
			}

			sb.append("|");

			if (QtdeMix == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeMix);
			}

			sb.append("|");

			if (Lote == null) {
				sb.append("<null>");
			} else {
				sb.append(Lote);
			}

			sb.append("|");

			if (VlrDesctoFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDesctoFiscal);
			}

			sb.append("|");

			if (Tamanho == null) {
				sb.append("<null>");
			} else {
				sb.append(Tamanho);
			}

			sb.append("|");

			if (BloqVerba == null) {
				sb.append("<null>");
			} else {
				sb.append(BloqVerba);
			}

			sb.append("|");

			if (VlrFreTitRateio == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitRateio);
			}

			sb.append("|");

			if (VlrFreTitRateioCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitRateioCalc);
			}

			sb.append("|");

			if (VisaoCota == null) {
				sb.append("<null>");
			} else {
				sb.append(VisaoCota);
			}

			sb.append("|");

			if (PeriodoCota == null) {
				sb.append("<null>");
			} else {
				sb.append(PeriodoCota);
			}

			sb.append("|");

			if (VisaoCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(VisaoCotaOriginal);
			}

			sb.append("|");

			if (PeriodoCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(PeriodoCotaOriginal);
			}

			sb.append("|");

			if (RegraEmpItem == null) {
				sb.append("<null>");
			} else {
				sb.append(RegraEmpItem);
			}

			sb.append("|");

			if (LjPeca == null) {
				sb.append("<null>");
			} else {
				sb.append(LjPeca);
			}

			sb.append("|");

			if (LjTamanho == null) {
				sb.append("<null>");
			} else {
				sb.append(LjTamanho);
			}

			sb.append("|");

			if (LjCarga == null) {
				sb.append("<null>");
			} else {
				sb.append(LjCarga);
			}

			sb.append("|");

			if (LjMedidaA == null) {
				sb.append("<null>");
			} else {
				sb.append(LjMedidaA);
			}

			sb.append("|");

			if (LjVao == null) {
				sb.append("<null>");
			} else {
				sb.append(LjVao);
			}

			sb.append("|");

			if (LjAngulo == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAngulo);
			}

			sb.append("|");

			if (LjMedidaB == null) {
				sb.append("<null>");
			} else {
				sb.append(LjMedidaB);
			}

			sb.append("|");

			if (LjInicioViga == null) {
				sb.append("<null>");
			} else {
				sb.append(LjInicioViga);
			}

			sb.append("|");

			if (LjAba == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAba);
			}

			sb.append("|");

			if (LjAbaA == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAbaA);
			}

			sb.append("|");

			if (LjAbaB == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAbaB);
			}

			sb.append("|");

			if (Bloqluc == null) {
				sb.append("<null>");
			} else {
				sb.append(Bloqluc);
			}

			sb.append("|");

			if (OrigemCota == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemCota);
			}

			sb.append("|");

			if (OrigemCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemCanc);
			}

			sb.append("|");

			if (Incsug == null) {
				sb.append("<null>");
			} else {
				sb.append(Incsug);
			}

			sb.append("|");

			if (LjIdent == null) {
				sb.append("<null>");
			} else {
				sb.append(LjIdent);
			}

			sb.append("|");

			if (CondFreteIt == null) {
				sb.append("<null>");
			} else {
				sb.append(CondFreteIt);
			}

			sb.append("|");

			if (DesctotalCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctotalCalc);
			}

			sb.append("|");

			if (IdPedAuto == null) {
				sb.append("<null>");
			} else {
				sb.append(IdPedAuto);
			}

			sb.append("|");

			if (LoteEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(LoteEstoque);
			}

			sb.append("|");

			if (DesctotalAplicado == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctotalAplicado);
			}

			sb.append("|");

			if (SeqOrdemCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqOrdemCompra);
			}

			sb.append("|");

			if (SeqordCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqordCompra);
			}

			sb.append("|");

			if (Ordcomest == null) {
				sb.append("<null>");
			} else {
				sb.append(Ordcomest);
			}

			sb.append("|");

			if (ValorICMSFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorICMSFrete);
			}

			sb.append("|");

			if (Campo1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Campo1);
			}

			sb.append("|");

			if (CentroCusto == null) {
				sb.append("<null>");
			} else {
				sb.append(CentroCusto);
			}

			sb.append("|");

			if (BloqDesc == null) {
				sb.append("<null>");
			} else {
				sb.append(BloqDesc);
			}

			sb.append("|");

			if (QtdeDescBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeDescBonif);
			}

			sb.append("|");

			if (PercDescBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(PercDescBonif);
			}

			sb.append("|");

			if (ValorDesccUnit == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorDesccUnit);
			}

			sb.append("|");

			if (SeqCota == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqCota);
			}

			sb.append("|");

			if (SeqCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqCotaOriginal);
			}

			sb.append("|");

			if (DtRegraCorte == null) {
				sb.append("<null>");
			} else {
				sb.append(DtRegraCorte);
			}

			sb.append("|");

			if (CompoeMargem == null) {
				sb.append("<null>");
			} else {
				sb.append(CompoeMargem);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.CodigoProduto, other.CodigoProduto);
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
		final static byte[] commonByteArrayLock_HYDRONORTH_Pedidos_mercanet = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer NumeroPedido;

		public Integer getNumeroPedido() {
			return this.NumeroPedido;
		}

		public Boolean NumeroPedidoIsNullable() {
			return true;
		}

		public Boolean NumeroPedidoIsKey() {
			return true;
		}

		public Integer NumeroPedidoLength() {
			return 10;
		}

		public Integer NumeroPedidoPrecision() {
			return 0;
		}

		public String NumeroPedidoDefault() {

			return "";

		}

		public String NumeroPedidoComment() {

			return "";

		}

		public String NumeroPedidoPattern() {

			return "";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public Integer SequenciaItem;

		public Integer getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return false;
		}

		public Integer SequenciaItemLength() {
			return 10;
		}

		public Integer SequenciaItemPrecision() {
			return 0;
		}

		public String SequenciaItemDefault() {

			return "";

		}

		public String SequenciaItemComment() {

			return "";

		}

		public String SequenciaItemPattern() {

			return "";

		}

		public String SequenciaItemOriginalDbColumnName() {

			return "SequenciaItem";

		}

		public String CodigoProduto;

		public String getCodigoProduto() {
			return this.CodigoProduto;
		}

		public Boolean CodigoProdutoIsNullable() {
			return true;
		}

		public Boolean CodigoProdutoIsKey() {
			return true;
		}

		public Integer CodigoProdutoLength() {
			return 16;
		}

		public Integer CodigoProdutoPrecision() {
			return 0;
		}

		public String CodigoProdutoDefault() {

			return null;

		}

		public String CodigoProdutoComment() {

			return "";

		}

		public String CodigoProdutoPattern() {

			return "";

		}

		public String CodigoProdutoOriginalDbColumnName() {

			return "CodigoProduto";

		}

		public Float QuantidadeProduto;

		public Float getQuantidadeProduto() {
			return this.QuantidadeProduto;
		}

		public Boolean QuantidadeProdutoIsNullable() {
			return true;
		}

		public Boolean QuantidadeProdutoIsKey() {
			return false;
		}

		public Integer QuantidadeProdutoLength() {
			return 7;
		}

		public Integer QuantidadeProdutoPrecision() {
			return 0;
		}

		public String QuantidadeProdutoDefault() {

			return "";

		}

		public String QuantidadeProdutoComment() {

			return "";

		}

		public String QuantidadeProdutoPattern() {

			return "";

		}

		public String QuantidadeProdutoOriginalDbColumnName() {

			return "QuantidadeProduto";

		}

		public Float QuantidadeAtendida;

		public Float getQuantidadeAtendida() {
			return this.QuantidadeAtendida;
		}

		public Boolean QuantidadeAtendidaIsNullable() {
			return true;
		}

		public Boolean QuantidadeAtendidaIsKey() {
			return false;
		}

		public Integer QuantidadeAtendidaLength() {
			return 7;
		}

		public Integer QuantidadeAtendidaPrecision() {
			return 0;
		}

		public String QuantidadeAtendidaDefault() {

			return "";

		}

		public String QuantidadeAtendidaComment() {

			return "";

		}

		public String QuantidadeAtendidaPattern() {

			return "";

		}

		public String QuantidadeAtendidaOriginalDbColumnName() {

			return "QuantidadeAtendida";

		}

		public Float QuantidadeCancelada;

		public Float getQuantidadeCancelada() {
			return this.QuantidadeCancelada;
		}

		public Boolean QuantidadeCanceladaIsNullable() {
			return true;
		}

		public Boolean QuantidadeCanceladaIsKey() {
			return false;
		}

		public Integer QuantidadeCanceladaLength() {
			return 7;
		}

		public Integer QuantidadeCanceladaPrecision() {
			return 0;
		}

		public String QuantidadeCanceladaDefault() {

			return "";

		}

		public String QuantidadeCanceladaComment() {

			return "";

		}

		public String QuantidadeCanceladaPattern() {

			return "";

		}

		public String QuantidadeCanceladaOriginalDbColumnName() {

			return "QuantidadeCancelada";

		}

		public Float PrecoUnitario;

		public Float getPrecoUnitario() {
			return this.PrecoUnitario;
		}

		public Boolean PrecoUnitarioIsNullable() {
			return true;
		}

		public Boolean PrecoUnitarioIsKey() {
			return false;
		}

		public Integer PrecoUnitarioLength() {
			return 7;
		}

		public Integer PrecoUnitarioPrecision() {
			return 0;
		}

		public String PrecoUnitarioDefault() {

			return "";

		}

		public String PrecoUnitarioComment() {

			return "";

		}

		public String PrecoUnitarioPattern() {

			return "";

		}

		public String PrecoUnitarioOriginalDbColumnName() {

			return "PrecoUnitario";

		}

		public Float PrecoLiquido;

		public Float getPrecoLiquido() {
			return this.PrecoLiquido;
		}

		public Boolean PrecoLiquidoIsNullable() {
			return true;
		}

		public Boolean PrecoLiquidoIsKey() {
			return false;
		}

		public Integer PrecoLiquidoLength() {
			return 7;
		}

		public Integer PrecoLiquidoPrecision() {
			return 0;
		}

		public String PrecoLiquidoDefault() {

			return "";

		}

		public String PrecoLiquidoComment() {

			return "";

		}

		public String PrecoLiquidoPattern() {

			return "";

		}

		public String PrecoLiquidoOriginalDbColumnName() {

			return "PrecoLiquido";

		}

		public Float Desctop;

		public Float getDesctop() {
			return this.Desctop;
		}

		public Boolean DesctopIsNullable() {
			return true;
		}

		public Boolean DesctopIsKey() {
			return false;
		}

		public Integer DesctopLength() {
			return 7;
		}

		public Integer DesctopPrecision() {
			return 0;
		}

		public String DesctopDefault() {

			return "";

		}

		public String DesctopComment() {

			return "";

		}

		public String DesctopPattern() {

			return "";

		}

		public String DesctopOriginalDbColumnName() {

			return "Desctop";

		}

		public Double DesctoIcm;

		public Double getDesctoIcm() {
			return this.DesctoIcm;
		}

		public Boolean DesctoIcmIsNullable() {
			return true;
		}

		public Boolean DesctoIcmIsKey() {
			return false;
		}

		public Integer DesctoIcmLength() {
			return 15;
		}

		public Integer DesctoIcmPrecision() {
			return 0;
		}

		public String DesctoIcmDefault() {

			return "";

		}

		public String DesctoIcmComment() {

			return "";

		}

		public String DesctoIcmPattern() {

			return "";

		}

		public String DesctoIcmOriginalDbColumnName() {

			return "DesctoIcm";

		}

		public java.util.Date PrevEntr;

		public java.util.Date getPrevEntr() {
			return this.PrevEntr;
		}

		public Boolean PrevEntrIsNullable() {
			return true;
		}

		public Boolean PrevEntrIsKey() {
			return false;
		}

		public Integer PrevEntrLength() {
			return 23;
		}

		public Integer PrevEntrPrecision() {
			return 3;
		}

		public String PrevEntrDefault() {

			return null;

		}

		public String PrevEntrComment() {

			return "";

		}

		public String PrevEntrPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevEntrOriginalDbColumnName() {

			return "PrevEntr";

		}

		public java.util.Date AtdData;

		public java.util.Date getAtdData() {
			return this.AtdData;
		}

		public Boolean AtdDataIsNullable() {
			return true;
		}

		public Boolean AtdDataIsKey() {
			return false;
		}

		public Integer AtdDataLength() {
			return 23;
		}

		public Integer AtdDataPrecision() {
			return 3;
		}

		public String AtdDataDefault() {

			return null;

		}

		public String AtdDataComment() {

			return "";

		}

		public String AtdDataPattern() {

			return "dd-MM-yyyy";

		}

		public String AtdDataOriginalDbColumnName() {

			return "AtdData";

		}

		public Float AtdQtde;

		public Float getAtdQtde() {
			return this.AtdQtde;
		}

		public Boolean AtdQtdeIsNullable() {
			return true;
		}

		public Boolean AtdQtdeIsKey() {
			return false;
		}

		public Integer AtdQtdeLength() {
			return 7;
		}

		public Integer AtdQtdePrecision() {
			return 0;
		}

		public String AtdQtdeDefault() {

			return "";

		}

		public String AtdQtdeComment() {

			return "";

		}

		public String AtdQtdePattern() {

			return "";

		}

		public String AtdQtdeOriginalDbColumnName() {

			return "AtdQtde";

		}

		public String AtdDcto;

		public String getAtdDcto() {
			return this.AtdDcto;
		}

		public Boolean AtdDctoIsNullable() {
			return true;
		}

		public Boolean AtdDctoIsKey() {
			return false;
		}

		public Integer AtdDctoLength() {
			return 20;
		}

		public Integer AtdDctoPrecision() {
			return 0;
		}

		public String AtdDctoDefault() {

			return null;

		}

		public String AtdDctoComment() {

			return "";

		}

		public String AtdDctoPattern() {

			return "";

		}

		public String AtdDctoOriginalDbColumnName() {

			return "AtdDcto";

		}

		public java.util.Date AtdData1;

		public java.util.Date getAtdData1() {
			return this.AtdData1;
		}

		public Boolean AtdData1IsNullable() {
			return true;
		}

		public Boolean AtdData1IsKey() {
			return false;
		}

		public Integer AtdData1Length() {
			return 23;
		}

		public Integer AtdData1Precision() {
			return 3;
		}

		public String AtdData1Default() {

			return null;

		}

		public String AtdData1Comment() {

			return "";

		}

		public String AtdData1Pattern() {

			return "dd-MM-yyyy";

		}

		public String AtdData1OriginalDbColumnName() {

			return "AtdData1";

		}

		public Float AtdQtde1;

		public Float getAtdQtde1() {
			return this.AtdQtde1;
		}

		public Boolean AtdQtde1IsNullable() {
			return true;
		}

		public Boolean AtdQtde1IsKey() {
			return false;
		}

		public Integer AtdQtde1Length() {
			return 7;
		}

		public Integer AtdQtde1Precision() {
			return 0;
		}

		public String AtdQtde1Default() {

			return "";

		}

		public String AtdQtde1Comment() {

			return "";

		}

		public String AtdQtde1Pattern() {

			return "";

		}

		public String AtdQtde1OriginalDbColumnName() {

			return "AtdQtde1";

		}

		public String AtdDcto1;

		public String getAtdDcto1() {
			return this.AtdDcto1;
		}

		public Boolean AtdDcto1IsNullable() {
			return true;
		}

		public Boolean AtdDcto1IsKey() {
			return false;
		}

		public Integer AtdDcto1Length() {
			return 20;
		}

		public Integer AtdDcto1Precision() {
			return 0;
		}

		public String AtdDcto1Default() {

			return null;

		}

		public String AtdDcto1Comment() {

			return "";

		}

		public String AtdDcto1Pattern() {

			return "";

		}

		public String AtdDcto1OriginalDbColumnName() {

			return "AtdDcto1";

		}

		public java.util.Date AtdData2;

		public java.util.Date getAtdData2() {
			return this.AtdData2;
		}

		public Boolean AtdData2IsNullable() {
			return true;
		}

		public Boolean AtdData2IsKey() {
			return false;
		}

		public Integer AtdData2Length() {
			return 23;
		}

		public Integer AtdData2Precision() {
			return 3;
		}

		public String AtdData2Default() {

			return null;

		}

		public String AtdData2Comment() {

			return "";

		}

		public String AtdData2Pattern() {

			return "dd-MM-yyyy";

		}

		public String AtdData2OriginalDbColumnName() {

			return "AtdData2";

		}

		public Float AtdQtde2;

		public Float getAtdQtde2() {
			return this.AtdQtde2;
		}

		public Boolean AtdQtde2IsNullable() {
			return true;
		}

		public Boolean AtdQtde2IsKey() {
			return false;
		}

		public Integer AtdQtde2Length() {
			return 7;
		}

		public Integer AtdQtde2Precision() {
			return 0;
		}

		public String AtdQtde2Default() {

			return "";

		}

		public String AtdQtde2Comment() {

			return "";

		}

		public String AtdQtde2Pattern() {

			return "";

		}

		public String AtdQtde2OriginalDbColumnName() {

			return "AtdQtde2";

		}

		public String AtdDcto2;

		public String getAtdDcto2() {
			return this.AtdDcto2;
		}

		public Boolean AtdDcto2IsNullable() {
			return true;
		}

		public Boolean AtdDcto2IsKey() {
			return false;
		}

		public Integer AtdDcto2Length() {
			return 20;
		}

		public Integer AtdDcto2Precision() {
			return 0;
		}

		public String AtdDcto2Default() {

			return null;

		}

		public String AtdDcto2Comment() {

			return "";

		}

		public String AtdDcto2Pattern() {

			return "";

		}

		public String AtdDcto2OriginalDbColumnName() {

			return "AtdDcto2";

		}

		public Short SituacaoPedido;

		public Short getSituacaoPedido() {
			return this.SituacaoPedido;
		}

		public Boolean SituacaoPedidoIsNullable() {
			return true;
		}

		public Boolean SituacaoPedidoIsKey() {
			return false;
		}

		public Integer SituacaoPedidoLength() {
			return 5;
		}

		public Integer SituacaoPedidoPrecision() {
			return 0;
		}

		public String SituacaoPedidoDefault() {

			return "";

		}

		public String SituacaoPedidoComment() {

			return "";

		}

		public String SituacaoPedidoPattern() {

			return "";

		}

		public String SituacaoPedidoOriginalDbColumnName() {

			return "SituacaoPedido";

		}

		public Double Ipi;

		public Double getIpi() {
			return this.Ipi;
		}

		public Boolean IpiIsNullable() {
			return true;
		}

		public Boolean IpiIsKey() {
			return false;
		}

		public Integer IpiLength() {
			return 15;
		}

		public Integer IpiPrecision() {
			return 0;
		}

		public String IpiDefault() {

			return "";

		}

		public String IpiComment() {

			return "";

		}

		public String IpiPattern() {

			return "";

		}

		public String IpiOriginalDbColumnName() {

			return "Ipi";

		}

		public Float Dctoprom;

		public Float getDctoprom() {
			return this.Dctoprom;
		}

		public Boolean DctopromIsNullable() {
			return true;
		}

		public Boolean DctopromIsKey() {
			return false;
		}

		public Integer DctopromLength() {
			return 7;
		}

		public Integer DctopromPrecision() {
			return 0;
		}

		public String DctopromDefault() {

			return "";

		}

		public String DctopromComment() {

			return "";

		}

		public String DctopromPattern() {

			return "";

		}

		public String DctopromOriginalDbColumnName() {

			return "Dctoprom";

		}

		public Float EconVlr;

		public Float getEconVlr() {
			return this.EconVlr;
		}

		public Boolean EconVlrIsNullable() {
			return true;
		}

		public Boolean EconVlrIsKey() {
			return false;
		}

		public Integer EconVlrLength() {
			return 7;
		}

		public Integer EconVlrPrecision() {
			return 0;
		}

		public String EconVlrDefault() {

			return "";

		}

		public String EconVlrComment() {

			return "";

		}

		public String EconVlrPattern() {

			return "";

		}

		public String EconVlrOriginalDbColumnName() {

			return "EconVlr";

		}

		public Short EconCalc;

		public Short getEconCalc() {
			return this.EconCalc;
		}

		public Boolean EconCalcIsNullable() {
			return true;
		}

		public Boolean EconCalcIsKey() {
			return false;
		}

		public Integer EconCalcLength() {
			return 3;
		}

		public Integer EconCalcPrecision() {
			return 0;
		}

		public String EconCalcDefault() {

			return "";

		}

		public String EconCalcComment() {

			return "";

		}

		public String EconCalcPattern() {

			return "";

		}

		public String EconCalcOriginalDbColumnName() {

			return "EconCalc";

		}

		public Float EconDcto;

		public Float getEconDcto() {
			return this.EconDcto;
		}

		public Boolean EconDctoIsNullable() {
			return true;
		}

		public Boolean EconDctoIsKey() {
			return false;
		}

		public Integer EconDctoLength() {
			return 7;
		}

		public Integer EconDctoPrecision() {
			return 0;
		}

		public String EconDctoDefault() {

			return "";

		}

		public String EconDctoComment() {

			return "";

		}

		public String EconDctoPattern() {

			return "";

		}

		public String EconDctoOriginalDbColumnName() {

			return "EconDcto";

		}

		public Float PrecoMin;

		public Float getPrecoMin() {
			return this.PrecoMin;
		}

		public Boolean PrecoMinIsNullable() {
			return true;
		}

		public Boolean PrecoMinIsKey() {
			return false;
		}

		public Integer PrecoMinLength() {
			return 7;
		}

		public Integer PrecoMinPrecision() {
			return 0;
		}

		public String PrecoMinDefault() {

			return "";

		}

		public String PrecoMinComment() {

			return "";

		}

		public String PrecoMinPattern() {

			return "";

		}

		public String PrecoMinOriginalDbColumnName() {

			return "PrecoMin";

		}

		public String Tipo;

		public String getTipo() {
			return this.Tipo;
		}

		public Boolean TipoIsNullable() {
			return true;
		}

		public Boolean TipoIsKey() {
			return false;
		}

		public Integer TipoLength() {
			return 1;
		}

		public Integer TipoPrecision() {
			return 0;
		}

		public String TipoDefault() {

			return null;

		}

		public String TipoComment() {

			return "";

		}

		public String TipoPattern() {

			return "";

		}

		public String TipoOriginalDbColumnName() {

			return "Tipo";

		}

		public Double Dctovda;

		public Double getDctovda() {
			return this.Dctovda;
		}

		public Boolean DctovdaIsNullable() {
			return true;
		}

		public Boolean DctovdaIsKey() {
			return false;
		}

		public Integer DctovdaLength() {
			return 15;
		}

		public Integer DctovdaPrecision() {
			return 0;
		}

		public String DctovdaDefault() {

			return "";

		}

		public String DctovdaComment() {

			return "";

		}

		public String DctovdaPattern() {

			return "";

		}

		public String DctovdaOriginalDbColumnName() {

			return "Dctovda";

		}

		public Short TipoPreco;

		public Short getTipoPreco() {
			return this.TipoPreco;
		}

		public Boolean TipoPrecoIsNullable() {
			return true;
		}

		public Boolean TipoPrecoIsKey() {
			return false;
		}

		public Integer TipoPrecoLength() {
			return 5;
		}

		public Integer TipoPrecoPrecision() {
			return 0;
		}

		public String TipoPrecoDefault() {

			return "";

		}

		public String TipoPrecoComment() {

			return "";

		}

		public String TipoPrecoPattern() {

			return "";

		}

		public String TipoPrecoOriginalDbColumnName() {

			return "TipoPreco";

		}

		public Short EstVlr;

		public Short getEstVlr() {
			return this.EstVlr;
		}

		public Boolean EstVlrIsNullable() {
			return true;
		}

		public Boolean EstVlrIsKey() {
			return false;
		}

		public Integer EstVlrLength() {
			return 5;
		}

		public Integer EstVlrPrecision() {
			return 0;
		}

		public String EstVlrDefault() {

			return "";

		}

		public String EstVlrComment() {

			return "";

		}

		public String EstVlrPattern() {

			return "";

		}

		public String EstVlrOriginalDbColumnName() {

			return "EstVlr";

		}

		public Double Acrescimo;

		public Double getAcrescimo() {
			return this.Acrescimo;
		}

		public Boolean AcrescimoIsNullable() {
			return true;
		}

		public Boolean AcrescimoIsKey() {
			return false;
		}

		public Integer AcrescimoLength() {
			return 15;
		}

		public Integer AcrescimoPrecision() {
			return 0;
		}

		public String AcrescimoDefault() {

			return "";

		}

		public String AcrescimoComment() {

			return "";

		}

		public String AcrescimoPattern() {

			return "";

		}

		public String AcrescimoOriginalDbColumnName() {

			return "Acrescimo";

		}

		public String Lpreco;

		public String getLpreco() {
			return this.Lpreco;
		}

		public Boolean LprecoIsNullable() {
			return true;
		}

		public Boolean LprecoIsKey() {
			return false;
		}

		public Integer LprecoLength() {
			return 8;
		}

		public Integer LprecoPrecision() {
			return 0;
		}

		public String LprecoDefault() {

			return null;

		}

		public String LprecoComment() {

			return "";

		}

		public String LprecoPattern() {

			return "";

		}

		public String LprecoOriginalDbColumnName() {

			return "Lpreco";

		}

		public String Grade;

		public String getGrade() {
			return this.Grade;
		}

		public Boolean GradeIsNullable() {
			return true;
		}

		public Boolean GradeIsKey() {
			return false;
		}

		public Integer GradeLength() {
			return 4;
		}

		public Integer GradePrecision() {
			return 0;
		}

		public String GradeDefault() {

			return null;

		}

		public String GradeComment() {

			return "";

		}

		public String GradePattern() {

			return "";

		}

		public String GradeOriginalDbColumnName() {

			return "Grade";

		}

		public Short TpGrade;

		public Short getTpGrade() {
			return this.TpGrade;
		}

		public Boolean TpGradeIsNullable() {
			return true;
		}

		public Boolean TpGradeIsKey() {
			return false;
		}

		public Integer TpGradeLength() {
			return 5;
		}

		public Integer TpGradePrecision() {
			return 0;
		}

		public String TpGradeDefault() {

			return "";

		}

		public String TpGradeComment() {

			return "";

		}

		public String TpGradePattern() {

			return "";

		}

		public String TpGradeOriginalDbColumnName() {

			return "TpGrade";

		}

		public String CodEtiq;

		public String getCodEtiq() {
			return this.CodEtiq;
		}

		public Boolean CodEtiqIsNullable() {
			return true;
		}

		public Boolean CodEtiqIsKey() {
			return false;
		}

		public Integer CodEtiqLength() {
			return 5;
		}

		public Integer CodEtiqPrecision() {
			return 0;
		}

		public String CodEtiqDefault() {

			return null;

		}

		public String CodEtiqComment() {

			return "";

		}

		public String CodEtiqPattern() {

			return "";

		}

		public String CodEtiqOriginalDbColumnName() {

			return "CodEtiq";

		}

		public Double Embalagem;

		public Double getEmbalagem() {
			return this.Embalagem;
		}

		public Boolean EmbalagemIsNullable() {
			return true;
		}

		public Boolean EmbalagemIsKey() {
			return false;
		}

		public Integer EmbalagemLength() {
			return 15;
		}

		public Integer EmbalagemPrecision() {
			return 0;
		}

		public String EmbalagemDefault() {

			return "";

		}

		public String EmbalagemComment() {

			return "";

		}

		public String EmbalagemPattern() {

			return "";

		}

		public String EmbalagemOriginalDbColumnName() {

			return "Embalagem";

		}

		public String MotCanc;

		public String getMotCanc() {
			return this.MotCanc;
		}

		public Boolean MotCancIsNullable() {
			return true;
		}

		public Boolean MotCancIsKey() {
			return false;
		}

		public Integer MotCancLength() {
			return 5;
		}

		public Integer MotCancPrecision() {
			return 0;
		}

		public String MotCancDefault() {

			return null;

		}

		public String MotCancComment() {

			return "";

		}

		public String MotCancPattern() {

			return "";

		}

		public String MotCancOriginalDbColumnName() {

			return "MotCanc";

		}

		public Float PercComis;

		public Float getPercComis() {
			return this.PercComis;
		}

		public Boolean PercComisIsNullable() {
			return true;
		}

		public Boolean PercComisIsKey() {
			return false;
		}

		public Integer PercComisLength() {
			return 7;
		}

		public Integer PercComisPrecision() {
			return 0;
		}

		public String PercComisDefault() {

			return "";

		}

		public String PercComisComment() {

			return "";

		}

		public String PercComisPattern() {

			return "";

		}

		public String PercComisOriginalDbColumnName() {

			return "PercComis";

		}

		public String PrdCfgCar;

		public String getPrdCfgCar() {
			return this.PrdCfgCar;
		}

		public Boolean PrdCfgCarIsNullable() {
			return true;
		}

		public Boolean PrdCfgCarIsKey() {
			return false;
		}

		public Integer PrdCfgCarLength() {
			return 1024;
		}

		public Integer PrdCfgCarPrecision() {
			return 0;
		}

		public String PrdCfgCarDefault() {

			return null;

		}

		public String PrdCfgCarComment() {

			return "";

		}

		public String PrdCfgCarPattern() {

			return "";

		}

		public String PrdCfgCarOriginalDbColumnName() {

			return "PrdCfgCar";

		}

		public String Observacao;

		public String getObservacao() {
			return this.Observacao;
		}

		public Boolean ObservacaoIsNullable() {
			return true;
		}

		public Boolean ObservacaoIsKey() {
			return false;
		}

		public Integer ObservacaoLength() {
			return 100;
		}

		public Integer ObservacaoPrecision() {
			return 0;
		}

		public String ObservacaoDefault() {

			return null;

		}

		public String ObservacaoComment() {

			return "";

		}

		public String ObservacaoPattern() {

			return "";

		}

		public String ObservacaoOriginalDbColumnName() {

			return "Observacao";

		}

		public String BonifEcon;

		public String getBonifEcon() {
			return this.BonifEcon;
		}

		public Boolean BonifEconIsNullable() {
			return true;
		}

		public Boolean BonifEconIsKey() {
			return false;
		}

		public Integer BonifEconLength() {
			return 1;
		}

		public Integer BonifEconPrecision() {
			return 0;
		}

		public String BonifEconDefault() {

			return null;

		}

		public String BonifEconComment() {

			return "";

		}

		public String BonifEconPattern() {

			return "";

		}

		public String BonifEconOriginalDbColumnName() {

			return "BonifEcon";

		}

		public Double PcomisExt;

		public Double getPcomisExt() {
			return this.PcomisExt;
		}

		public Boolean PcomisExtIsNullable() {
			return true;
		}

		public Boolean PcomisExtIsKey() {
			return false;
		}

		public Integer PcomisExtLength() {
			return 15;
		}

		public Integer PcomisExtPrecision() {
			return 0;
		}

		public String PcomisExtDefault() {

			return "";

		}

		public String PcomisExtComment() {

			return "";

		}

		public String PcomisExtPattern() {

			return "";

		}

		public String PcomisExtOriginalDbColumnName() {

			return "PcomisExt";

		}

		public String AplicDcto;

		public String getAplicDcto() {
			return this.AplicDcto;
		}

		public Boolean AplicDctoIsNullable() {
			return true;
		}

		public Boolean AplicDctoIsKey() {
			return false;
		}

		public Integer AplicDctoLength() {
			return 10;
		}

		public Integer AplicDctoPrecision() {
			return 0;
		}

		public String AplicDctoDefault() {

			return null;

		}

		public String AplicDctoComment() {

			return "";

		}

		public String AplicDctoPattern() {

			return "";

		}

		public String AplicDctoOriginalDbColumnName() {

			return "AplicDcto";

		}

		public String Operacao;

		public String getOperacao() {
			return this.Operacao;
		}

		public Boolean OperacaoIsNullable() {
			return true;
		}

		public Boolean OperacaoIsKey() {
			return false;
		}

		public Integer OperacaoLength() {
			return 10;
		}

		public Integer OperacaoPrecision() {
			return 0;
		}

		public String OperacaoDefault() {

			return null;

		}

		public String OperacaoComment() {

			return "";

		}

		public String OperacaoPattern() {

			return "";

		}

		public String OperacaoOriginalDbColumnName() {

			return "Operacao";

		}

		public Double QtdeRes;

		public Double getQtdeRes() {
			return this.QtdeRes;
		}

		public Boolean QtdeResIsNullable() {
			return true;
		}

		public Boolean QtdeResIsKey() {
			return false;
		}

		public Integer QtdeResLength() {
			return 15;
		}

		public Integer QtdeResPrecision() {
			return 0;
		}

		public String QtdeResDefault() {

			return "";

		}

		public String QtdeResComment() {

			return "";

		}

		public String QtdeResPattern() {

			return "";

		}

		public String QtdeResOriginalDbColumnName() {

			return "QtdeRes";

		}

		public String Bloqsug;

		public String getBloqsug() {
			return this.Bloqsug;
		}

		public Boolean BloqsugIsNullable() {
			return true;
		}

		public Boolean BloqsugIsKey() {
			return false;
		}

		public Integer BloqsugLength() {
			return 1;
		}

		public Integer BloqsugPrecision() {
			return 0;
		}

		public String BloqsugDefault() {

			return null;

		}

		public String BloqsugComment() {

			return "";

		}

		public String BloqsugPattern() {

			return "";

		}

		public String BloqsugOriginalDbColumnName() {

			return "Bloqsug";

		}

		public Float Dctoinc;

		public Float getDctoinc() {
			return this.Dctoinc;
		}

		public Boolean DctoincIsNullable() {
			return true;
		}

		public Boolean DctoincIsKey() {
			return false;
		}

		public Integer DctoincLength() {
			return 7;
		}

		public Integer DctoincPrecision() {
			return 0;
		}

		public String DctoincDefault() {

			return "";

		}

		public String DctoincComment() {

			return "";

		}

		public String DctoincPattern() {

			return "";

		}

		public String DctoincOriginalDbColumnName() {

			return "Dctoinc";

		}

		public Double VlrIpi;

		public Double getVlrIpi() {
			return this.VlrIpi;
		}

		public Boolean VlrIpiIsNullable() {
			return true;
		}

		public Boolean VlrIpiIsKey() {
			return false;
		}

		public Integer VlrIpiLength() {
			return 15;
		}

		public Integer VlrIpiPrecision() {
			return 0;
		}

		public String VlrIpiDefault() {

			return "";

		}

		public String VlrIpiComment() {

			return "";

		}

		public String VlrIpiPattern() {

			return "";

		}

		public String VlrIpiOriginalDbColumnName() {

			return "VlrIpi";

		}

		public String SeqGrd;

		public String getSeqGrd() {
			return this.SeqGrd;
		}

		public Boolean SeqGrdIsNullable() {
			return true;
		}

		public Boolean SeqGrdIsKey() {
			return false;
		}

		public Integer SeqGrdLength() {
			return 7;
		}

		public Integer SeqGrdPrecision() {
			return 0;
		}

		public String SeqGrdDefault() {

			return null;

		}

		public String SeqGrdComment() {

			return "";

		}

		public String SeqGrdPattern() {

			return "";

		}

		public String SeqGrdOriginalDbColumnName() {

			return "SeqGrd";

		}

		public Integer SeqErp;

		public Integer getSeqErp() {
			return this.SeqErp;
		}

		public Boolean SeqErpIsNullable() {
			return true;
		}

		public Boolean SeqErpIsKey() {
			return false;
		}

		public Integer SeqErpLength() {
			return 10;
		}

		public Integer SeqErpPrecision() {
			return 0;
		}

		public String SeqErpDefault() {

			return "";

		}

		public String SeqErpComment() {

			return "";

		}

		public String SeqErpPattern() {

			return "";

		}

		public String SeqErpOriginalDbColumnName() {

			return "SeqErp";

		}

		public java.util.Date PrevCalc;

		public java.util.Date getPrevCalc() {
			return this.PrevCalc;
		}

		public Boolean PrevCalcIsNullable() {
			return true;
		}

		public Boolean PrevCalcIsKey() {
			return false;
		}

		public Integer PrevCalcLength() {
			return 23;
		}

		public Integer PrevCalcPrecision() {
			return 3;
		}

		public String PrevCalcDefault() {

			return null;

		}

		public String PrevCalcComment() {

			return "";

		}

		public String PrevCalcPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevCalcOriginalDbColumnName() {

			return "PrevCalc";

		}

		public String Almoxarif;

		public String getAlmoxarif() {
			return this.Almoxarif;
		}

		public Boolean AlmoxarifIsNullable() {
			return true;
		}

		public Boolean AlmoxarifIsKey() {
			return false;
		}

		public Integer AlmoxarifLength() {
			return 3;
		}

		public Integer AlmoxarifPrecision() {
			return 0;
		}

		public String AlmoxarifDefault() {

			return null;

		}

		public String AlmoxarifComment() {

			return "";

		}

		public String AlmoxarifPattern() {

			return "";

		}

		public String AlmoxarifOriginalDbColumnName() {

			return "Almoxarif";

		}

		public Double ComisGer;

		public Double getComisGer() {
			return this.ComisGer;
		}

		public Boolean ComisGerIsNullable() {
			return true;
		}

		public Boolean ComisGerIsKey() {
			return false;
		}

		public Integer ComisGerLength() {
			return 15;
		}

		public Integer ComisGerPrecision() {
			return 0;
		}

		public String ComisGerDefault() {

			return "";

		}

		public String ComisGerComment() {

			return "";

		}

		public String ComisGerPattern() {

			return "";

		}

		public String ComisGerOriginalDbColumnName() {

			return "ComisGer";

		}

		public Double CredProp;

		public Double getCredProp() {
			return this.CredProp;
		}

		public Boolean CredPropIsNullable() {
			return true;
		}

		public Boolean CredPropIsKey() {
			return false;
		}

		public Integer CredPropLength() {
			return 15;
		}

		public Integer CredPropPrecision() {
			return 0;
		}

		public String CredPropDefault() {

			return "";

		}

		public String CredPropComment() {

			return "";

		}

		public String CredPropPattern() {

			return "";

		}

		public String CredPropOriginalDbColumnName() {

			return "CredProp";

		}

		public Double NroEnvio;

		public Double getNroEnvio() {
			return this.NroEnvio;
		}

		public Boolean NroEnvioIsNullable() {
			return true;
		}

		public Boolean NroEnvioIsKey() {
			return false;
		}

		public Integer NroEnvioLength() {
			return 15;
		}

		public Integer NroEnvioPrecision() {
			return 0;
		}

		public String NroEnvioDefault() {

			return "";

		}

		public String NroEnvioComment() {

			return "";

		}

		public String NroEnvioPattern() {

			return "";

		}

		public String NroEnvioOriginalDbColumnName() {

			return "NroEnvio";

		}

		public String Chave;

		public String getChave() {
			return this.Chave;
		}

		public Boolean ChaveIsNullable() {
			return true;
		}

		public Boolean ChaveIsKey() {
			return false;
		}

		public Integer ChaveLength() {
			return 1;
		}

		public Integer ChavePrecision() {
			return 0;
		}

		public String ChaveDefault() {

			return null;

		}

		public String ChaveComment() {

			return "";

		}

		public String ChavePattern() {

			return "";

		}

		public String ChaveOriginalDbColumnName() {

			return "Chave";

		}

		public String ItemConf;

		public String getItemConf() {
			return this.ItemConf;
		}

		public Boolean ItemConfIsNullable() {
			return true;
		}

		public Boolean ItemConfIsKey() {
			return false;
		}

		public Integer ItemConfLength() {
			return 16;
		}

		public Integer ItemConfPrecision() {
			return 0;
		}

		public String ItemConfDefault() {

			return null;

		}

		public String ItemConfComment() {

			return "";

		}

		public String ItemConfPattern() {

			return "";

		}

		public String ItemConfOriginalDbColumnName() {

			return "ItemConf";

		}

		public String CvdaDcti;

		public String getCvdaDcti() {
			return this.CvdaDcti;
		}

		public Boolean CvdaDctiIsNullable() {
			return true;
		}

		public Boolean CvdaDctiIsKey() {
			return false;
		}

		public Integer CvdaDctiLength() {
			return 10;
		}

		public Integer CvdaDctiPrecision() {
			return 0;
		}

		public String CvdaDctiDefault() {

			return null;

		}

		public String CvdaDctiComment() {

			return "";

		}

		public String CvdaDctiPattern() {

			return "";

		}

		public String CvdaDctiOriginalDbColumnName() {

			return "CvdaDcti";

		}

		public String ObsNota;

		public String getObsNota() {
			return this.ObsNota;
		}

		public Boolean ObsNotaIsNullable() {
			return true;
		}

		public Boolean ObsNotaIsKey() {
			return false;
		}

		public Integer ObsNotaLength() {
			return 50;
		}

		public Integer ObsNotaPrecision() {
			return 0;
		}

		public String ObsNotaDefault() {

			return null;

		}

		public String ObsNotaComment() {

			return "";

		}

		public String ObsNotaPattern() {

			return "";

		}

		public String ObsNotaOriginalDbColumnName() {

			return "ObsNota";

		}

		public String ObsExped;

		public String getObsExped() {
			return this.ObsExped;
		}

		public Boolean ObsExpedIsNullable() {
			return true;
		}

		public Boolean ObsExpedIsKey() {
			return false;
		}

		public Integer ObsExpedLength() {
			return 50;
		}

		public Integer ObsExpedPrecision() {
			return 0;
		}

		public String ObsExpedDefault() {

			return null;

		}

		public String ObsExpedComment() {

			return "";

		}

		public String ObsExpedPattern() {

			return "";

		}

		public String ObsExpedOriginalDbColumnName() {

			return "ObsExped";

		}

		public String ObsSepar;

		public String getObsSepar() {
			return this.ObsSepar;
		}

		public Boolean ObsSeparIsNullable() {
			return true;
		}

		public Boolean ObsSeparIsKey() {
			return false;
		}

		public Integer ObsSeparLength() {
			return 255;
		}

		public Integer ObsSeparPrecision() {
			return 0;
		}

		public String ObsSeparDefault() {

			return null;

		}

		public String ObsSeparComment() {

			return "";

		}

		public String ObsSeparPattern() {

			return "";

		}

		public String ObsSeparOriginalDbColumnName() {

			return "ObsSepar";

		}

		public Double VlrSubst;

		public Double getVlrSubst() {
			return this.VlrSubst;
		}

		public Boolean VlrSubstIsNullable() {
			return true;
		}

		public Boolean VlrSubstIsKey() {
			return false;
		}

		public Integer VlrSubstLength() {
			return 15;
		}

		public Integer VlrSubstPrecision() {
			return 0;
		}

		public String VlrSubstDefault() {

			return "";

		}

		public String VlrSubstComment() {

			return "";

		}

		public String VlrSubstPattern() {

			return "";

		}

		public String VlrSubstOriginalDbColumnName() {

			return "VlrSubst";

		}

		public Double DesctoGer;

		public Double getDesctoGer() {
			return this.DesctoGer;
		}

		public Boolean DesctoGerIsNullable() {
			return true;
		}

		public Boolean DesctoGerIsKey() {
			return false;
		}

		public Integer DesctoGerLength() {
			return 15;
		}

		public Integer DesctoGerPrecision() {
			return 0;
		}

		public String DesctoGerDefault() {

			return "";

		}

		public String DesctoGerComment() {

			return "";

		}

		public String DesctoGerPattern() {

			return "";

		}

		public String DesctoGerOriginalDbColumnName() {

			return "DesctoGer";

		}

		public Double VlrFreTit;

		public Double getVlrFreTit() {
			return this.VlrFreTit;
		}

		public Boolean VlrFreTitIsNullable() {
			return true;
		}

		public Boolean VlrFreTitIsKey() {
			return false;
		}

		public Integer VlrFreTitLength() {
			return 15;
		}

		public Integer VlrFreTitPrecision() {
			return 0;
		}

		public String VlrFreTitDefault() {

			return "";

		}

		public String VlrFreTitComment() {

			return "";

		}

		public String VlrFreTitPattern() {

			return "";

		}

		public String VlrFreTitOriginalDbColumnName() {

			return "VlrFreTit";

		}

		public String MotTroca;

		public String getMotTroca() {
			return this.MotTroca;
		}

		public Boolean MotTrocaIsNullable() {
			return true;
		}

		public Boolean MotTrocaIsKey() {
			return false;
		}

		public Integer MotTrocaLength() {
			return 5;
		}

		public Integer MotTrocaPrecision() {
			return 0;
		}

		public String MotTrocaDefault() {

			return null;

		}

		public String MotTrocaComment() {

			return "";

		}

		public String MotTrocaPattern() {

			return "";

		}

		public String MotTrocaOriginalDbColumnName() {

			return "MotTroca";

		}

		public Double VeconCig;

		public Double getVeconCig() {
			return this.VeconCig;
		}

		public Boolean VeconCigIsNullable() {
			return true;
		}

		public Boolean VeconCigIsKey() {
			return false;
		}

		public Integer VeconCigLength() {
			return 15;
		}

		public Integer VeconCigPrecision() {
			return 0;
		}

		public String VeconCigDefault() {

			return "";

		}

		public String VeconCigComment() {

			return "";

		}

		public String VeconCigPattern() {

			return "";

		}

		public String VeconCigOriginalDbColumnName() {

			return "VeconCig";

		}

		public Double CustoMp;

		public Double getCustoMp() {
			return this.CustoMp;
		}

		public Boolean CustoMpIsNullable() {
			return true;
		}

		public Boolean CustoMpIsKey() {
			return false;
		}

		public Integer CustoMpLength() {
			return 15;
		}

		public Integer CustoMpPrecision() {
			return 0;
		}

		public String CustoMpDefault() {

			return "";

		}

		public String CustoMpComment() {

			return "";

		}

		public String CustoMpPattern() {

			return "";

		}

		public String CustoMpOriginalDbColumnName() {

			return "CustoMp";

		}

		public Short PliqAlt;

		public Short getPliqAlt() {
			return this.PliqAlt;
		}

		public Boolean PliqAltIsNullable() {
			return true;
		}

		public Boolean PliqAltIsKey() {
			return false;
		}

		public Integer PliqAltLength() {
			return 5;
		}

		public Integer PliqAltPrecision() {
			return 0;
		}

		public String PliqAltDefault() {

			return "";

		}

		public String PliqAltComment() {

			return "";

		}

		public String PliqAltPattern() {

			return "";

		}

		public String PliqAltOriginalDbColumnName() {

			return "PliqAlt";

		}

		public String CodPrMap;

		public String getCodPrMap() {
			return this.CodPrMap;
		}

		public Boolean CodPrMapIsNullable() {
			return true;
		}

		public Boolean CodPrMapIsKey() {
			return false;
		}

		public Integer CodPrMapLength() {
			return 10;
		}

		public Integer CodPrMapPrecision() {
			return 0;
		}

		public String CodPrMapDefault() {

			return null;

		}

		public String CodPrMapComment() {

			return "";

		}

		public String CodPrMapPattern() {

			return "";

		}

		public String CodPrMapOriginalDbColumnName() {

			return "CodPrMap";

		}

		public String CodCvdAg;

		public String getCodCvdAg() {
			return this.CodCvdAg;
		}

		public Boolean CodCvdAgIsNullable() {
			return true;
		}

		public Boolean CodCvdAgIsKey() {
			return false;
		}

		public Integer CodCvdAgLength() {
			return 10;
		}

		public Integer CodCvdAgPrecision() {
			return 0;
		}

		public String CodCvdAgDefault() {

			return null;

		}

		public String CodCvdAgComment() {

			return "";

		}

		public String CodCvdAgPattern() {

			return "";

		}

		public String CodCvdAgOriginalDbColumnName() {

			return "CodCvdAg";

		}

		public Double VuIcms;

		public Double getVuIcms() {
			return this.VuIcms;
		}

		public Boolean VuIcmsIsNullable() {
			return true;
		}

		public Boolean VuIcmsIsKey() {
			return false;
		}

		public Integer VuIcmsLength() {
			return 15;
		}

		public Integer VuIcmsPrecision() {
			return 0;
		}

		public String VuIcmsDefault() {

			return "";

		}

		public String VuIcmsComment() {

			return "";

		}

		public String VuIcmsPattern() {

			return "";

		}

		public String VuIcmsOriginalDbColumnName() {

			return "VuIcms";

		}

		public Double VuPis;

		public Double getVuPis() {
			return this.VuPis;
		}

		public Boolean VuPisIsNullable() {
			return true;
		}

		public Boolean VuPisIsKey() {
			return false;
		}

		public Integer VuPisLength() {
			return 15;
		}

		public Integer VuPisPrecision() {
			return 0;
		}

		public String VuPisDefault() {

			return "";

		}

		public String VuPisComment() {

			return "";

		}

		public String VuPisPattern() {

			return "";

		}

		public String VuPisOriginalDbColumnName() {

			return "VuPis";

		}

		public Double VuCofins;

		public Double getVuCofins() {
			return this.VuCofins;
		}

		public Boolean VuCofinsIsNullable() {
			return true;
		}

		public Boolean VuCofinsIsKey() {
			return false;
		}

		public Integer VuCofinsLength() {
			return 15;
		}

		public Integer VuCofinsPrecision() {
			return 0;
		}

		public String VuCofinsDefault() {

			return "";

		}

		public String VuCofinsComment() {

			return "";

		}

		public String VuCofinsPattern() {

			return "";

		}

		public String VuCofinsOriginalDbColumnName() {

			return "VuCofins";

		}

		public Short Altdtprev;

		public Short getAltdtprev() {
			return this.Altdtprev;
		}

		public Boolean AltdtprevIsNullable() {
			return true;
		}

		public Boolean AltdtprevIsKey() {
			return false;
		}

		public Integer AltdtprevLength() {
			return 5;
		}

		public Integer AltdtprevPrecision() {
			return 0;
		}

		public String AltdtprevDefault() {

			return "";

		}

		public String AltdtprevComment() {

			return "";

		}

		public String AltdtprevPattern() {

			return "";

		}

		public String AltdtprevOriginalDbColumnName() {

			return "Altdtprev";

		}

		public Double QtdeAloc;

		public Double getQtdeAloc() {
			return this.QtdeAloc;
		}

		public Boolean QtdeAlocIsNullable() {
			return true;
		}

		public Boolean QtdeAlocIsKey() {
			return false;
		}

		public Integer QtdeAlocLength() {
			return 15;
		}

		public Integer QtdeAlocPrecision() {
			return 0;
		}

		public String QtdeAlocDefault() {

			return "";

		}

		public String QtdeAlocComment() {

			return "";

		}

		public String QtdeAlocPattern() {

			return "";

		}

		public String QtdeAlocOriginalDbColumnName() {

			return "QtdeAloc";

		}

		public String RestrBonif;

		public String getRestrBonif() {
			return this.RestrBonif;
		}

		public Boolean RestrBonifIsNullable() {
			return true;
		}

		public Boolean RestrBonifIsKey() {
			return false;
		}

		public Integer RestrBonifLength() {
			return 5;
		}

		public Integer RestrBonifPrecision() {
			return 0;
		}

		public String RestrBonifDefault() {

			return "";

		}

		public String RestrBonifComment() {

			return "";

		}

		public String RestrBonifPattern() {

			return "";

		}

		public String RestrBonifOriginalDbColumnName() {

			return "RestrBonif";

		}

		public Double CustoMo;

		public Double getCustoMo() {
			return this.CustoMo;
		}

		public Boolean CustoMoIsNullable() {
			return true;
		}

		public Boolean CustoMoIsKey() {
			return false;
		}

		public Integer CustoMoLength() {
			return 15;
		}

		public Integer CustoMoPrecision() {
			return 0;
		}

		public String CustoMoDefault() {

			return "";

		}

		public String CustoMoComment() {

			return "";

		}

		public String CustoMoPattern() {

			return "";

		}

		public String CustoMoOriginalDbColumnName() {

			return "CustoMo";

		}

		public Short ItControl;

		public Short getItControl() {
			return this.ItControl;
		}

		public Boolean ItControlIsNullable() {
			return true;
		}

		public Boolean ItControlIsKey() {
			return false;
		}

		public Integer ItControlLength() {
			return 5;
		}

		public Integer ItControlPrecision() {
			return 0;
		}

		public String ItControlDefault() {

			return "";

		}

		public String ItControlComment() {

			return "";

		}

		public String ItControlPattern() {

			return "";

		}

		public String ItControlOriginalDbColumnName() {

			return "ItControl";

		}

		public Short ItPermBon;

		public Short getItPermBon() {
			return this.ItPermBon;
		}

		public Boolean ItPermBonIsNullable() {
			return true;
		}

		public Boolean ItPermBonIsKey() {
			return false;
		}

		public Integer ItPermBonLength() {
			return 5;
		}

		public Integer ItPermBonPrecision() {
			return 0;
		}

		public String ItPermBonDefault() {

			return "";

		}

		public String ItPermBonComment() {

			return "";

		}

		public String ItPermBonPattern() {

			return "";

		}

		public String ItPermBonOriginalDbColumnName() {

			return "ItPermBon";

		}

		public Double QtdPermBon;

		public Double getQtdPermBon() {
			return this.QtdPermBon;
		}

		public Boolean QtdPermBonIsNullable() {
			return true;
		}

		public Boolean QtdPermBonIsKey() {
			return false;
		}

		public Integer QtdPermBonLength() {
			return 15;
		}

		public Integer QtdPermBonPrecision() {
			return 0;
		}

		public String QtdPermBonDefault() {

			return "";

		}

		public String QtdPermBonComment() {

			return "";

		}

		public String QtdPermBonPattern() {

			return "";

		}

		public String QtdPermBonOriginalDbColumnName() {

			return "QtdPermBon";

		}

		public Double VlrPermBon;

		public Double getVlrPermBon() {
			return this.VlrPermBon;
		}

		public Boolean VlrPermBonIsNullable() {
			return true;
		}

		public Boolean VlrPermBonIsKey() {
			return false;
		}

		public Integer VlrPermBonLength() {
			return 15;
		}

		public Integer VlrPermBonPrecision() {
			return 0;
		}

		public String VlrPermBonDefault() {

			return "";

		}

		public String VlrPermBonComment() {

			return "";

		}

		public String VlrPermBonPattern() {

			return "";

		}

		public String VlrPermBonOriginalDbColumnName() {

			return "VlrPermBon";

		}

		public Short Itemfemb;

		public Short getItemfemb() {
			return this.Itemfemb;
		}

		public Boolean ItemfembIsNullable() {
			return true;
		}

		public Boolean ItemfembIsKey() {
			return false;
		}

		public Integer ItemfembLength() {
			return 5;
		}

		public Integer ItemfembPrecision() {
			return 0;
		}

		public String ItemfembDefault() {

			return "";

		}

		public String ItemfembComment() {

			return "";

		}

		public String ItemfembPattern() {

			return "";

		}

		public String ItemfembOriginalDbColumnName() {

			return "Itemfemb";

		}

		public java.util.Date Dtprevento;

		public java.util.Date getDtprevento() {
			return this.Dtprevento;
		}

		public Boolean DtpreventoIsNullable() {
			return true;
		}

		public Boolean DtpreventoIsKey() {
			return false;
		}

		public Integer DtpreventoLength() {
			return 23;
		}

		public Integer DtpreventoPrecision() {
			return 3;
		}

		public String DtpreventoDefault() {

			return "";

		}

		public String DtpreventoComment() {

			return "";

		}

		public String DtpreventoPattern() {

			return "dd-MM-yyyy";

		}

		public String DtpreventoOriginalDbColumnName() {

			return "Dtprevento";

		}

		public String Cfretit;

		public String getCfretit() {
			return this.Cfretit;
		}

		public Boolean CfretitIsNullable() {
			return true;
		}

		public Boolean CfretitIsKey() {
			return false;
		}

		public Integer CfretitLength() {
			return 4;
		}

		public Integer CfretitPrecision() {
			return 0;
		}

		public String CfretitDefault() {

			return "";

		}

		public String CfretitComment() {

			return "";

		}

		public String CfretitPattern() {

			return "";

		}

		public String CfretitOriginalDbColumnName() {

			return "Cfretit";

		}

		public Double SldVerba;

		public Double getSldVerba() {
			return this.SldVerba;
		}

		public Boolean SldVerbaIsNullable() {
			return true;
		}

		public Boolean SldVerbaIsKey() {
			return false;
		}

		public Integer SldVerbaLength() {
			return 15;
		}

		public Integer SldVerbaPrecision() {
			return 0;
		}

		public String SldVerbaDefault() {

			return "";

		}

		public String SldVerbaComment() {

			return "";

		}

		public String SldVerbaPattern() {

			return "";

		}

		public String SldVerbaOriginalDbColumnName() {

			return "SldVerba";

		}

		public Double VlrBonif;

		public Double getVlrBonif() {
			return this.VlrBonif;
		}

		public Boolean VlrBonifIsNullable() {
			return true;
		}

		public Boolean VlrBonifIsKey() {
			return false;
		}

		public Integer VlrBonifLength() {
			return 15;
		}

		public Integer VlrBonifPrecision() {
			return 0;
		}

		public String VlrBonifDefault() {

			return "";

		}

		public String VlrBonifComment() {

			return "";

		}

		public String VlrBonifPattern() {

			return "";

		}

		public String VlrBonifOriginalDbColumnName() {

			return "VlrBonif";

		}

		public Short Bonif;

		public Short getBonif() {
			return this.Bonif;
		}

		public Boolean BonifIsNullable() {
			return true;
		}

		public Boolean BonifIsKey() {
			return false;
		}

		public Integer BonifLength() {
			return 5;
		}

		public Integer BonifPrecision() {
			return 0;
		}

		public String BonifDefault() {

			return "";

		}

		public String BonifComment() {

			return "";

		}

		public String BonifPattern() {

			return "";

		}

		public String BonifOriginalDbColumnName() {

			return "Bonif";

		}

		public Short Tpproduto;

		public Short getTpproduto() {
			return this.Tpproduto;
		}

		public Boolean TpprodutoIsNullable() {
			return true;
		}

		public Boolean TpprodutoIsKey() {
			return false;
		}

		public Integer TpprodutoLength() {
			return 5;
		}

		public Integer TpprodutoPrecision() {
			return 0;
		}

		public String TpprodutoDefault() {

			return "";

		}

		public String TpprodutoComment() {

			return "";

		}

		public String TpprodutoPattern() {

			return "";

		}

		public String TpprodutoOriginalDbColumnName() {

			return "Tpproduto";

		}

		public String Backord;

		public String getBackord() {
			return this.Backord;
		}

		public Boolean BackordIsNullable() {
			return true;
		}

		public Boolean BackordIsKey() {
			return false;
		}

		public Integer BackordLength() {
			return 3;
		}

		public Integer BackordPrecision() {
			return 0;
		}

		public String BackordDefault() {

			return "";

		}

		public String BackordComment() {

			return "";

		}

		public String BackordPattern() {

			return "";

		}

		public String BackordOriginalDbColumnName() {

			return "Backord";

		}

		public Double Markup;

		public Double getMarkup() {
			return this.Markup;
		}

		public Boolean MarkupIsNullable() {
			return true;
		}

		public Boolean MarkupIsKey() {
			return false;
		}

		public Integer MarkupLength() {
			return 15;
		}

		public Integer MarkupPrecision() {
			return 0;
		}

		public String MarkupDefault() {

			return "";

		}

		public String MarkupComment() {

			return "";

		}

		public String MarkupPattern() {

			return "";

		}

		public String MarkupOriginalDbColumnName() {

			return "Markup";

		}

		public Double QtdeBkor;

		public Double getQtdeBkor() {
			return this.QtdeBkor;
		}

		public Boolean QtdeBkorIsNullable() {
			return true;
		}

		public Boolean QtdeBkorIsKey() {
			return false;
		}

		public Integer QtdeBkorLength() {
			return 15;
		}

		public Integer QtdeBkorPrecision() {
			return 0;
		}

		public String QtdeBkorDefault() {

			return "";

		}

		public String QtdeBkorComment() {

			return "";

		}

		public String QtdeBkorPattern() {

			return "";

		}

		public String QtdeBkorOriginalDbColumnName() {

			return "QtdeBkor";

		}

		public Double Prcpromoc;

		public Double getPrcpromoc() {
			return this.Prcpromoc;
		}

		public Boolean PrcpromocIsNullable() {
			return true;
		}

		public Boolean PrcpromocIsKey() {
			return false;
		}

		public Integer PrcpromocLength() {
			return 15;
		}

		public Integer PrcpromocPrecision() {
			return 0;
		}

		public String PrcpromocDefault() {

			return "";

		}

		public String PrcpromocComment() {

			return "";

		}

		public String PrcpromocPattern() {

			return "";

		}

		public String PrcpromocOriginalDbColumnName() {

			return "Prcpromoc";

		}

		public String Sitcorp1;

		public String getSitcorp1() {
			return this.Sitcorp1;
		}

		public Boolean Sitcorp1IsNullable() {
			return true;
		}

		public Boolean Sitcorp1IsKey() {
			return false;
		}

		public Integer Sitcorp1Length() {
			return 6;
		}

		public Integer Sitcorp1Precision() {
			return 0;
		}

		public String Sitcorp1Default() {

			return "";

		}

		public String Sitcorp1Comment() {

			return "";

		}

		public String Sitcorp1Pattern() {

			return "";

		}

		public String Sitcorp1OriginalDbColumnName() {

			return "Sitcorp1";

		}

		public String Sitcorp2;

		public String getSitcorp2() {
			return this.Sitcorp2;
		}

		public Boolean Sitcorp2IsNullable() {
			return true;
		}

		public Boolean Sitcorp2IsKey() {
			return false;
		}

		public Integer Sitcorp2Length() {
			return 6;
		}

		public Integer Sitcorp2Precision() {
			return 0;
		}

		public String Sitcorp2Default() {

			return "";

		}

		public String Sitcorp2Comment() {

			return "";

		}

		public String Sitcorp2Pattern() {

			return "";

		}

		public String Sitcorp2OriginalDbColumnName() {

			return "Sitcorp2";

		}

		public String LibMots;

		public String getLibMots() {
			return this.LibMots;
		}

		public Boolean LibMotsIsNullable() {
			return true;
		}

		public Boolean LibMotsIsKey() {
			return false;
		}

		public Integer LibMotsLength() {
			return 255;
		}

		public Integer LibMotsPrecision() {
			return 0;
		}

		public String LibMotsDefault() {

			return "";

		}

		public String LibMotsComment() {

			return "";

		}

		public String LibMotsPattern() {

			return "";

		}

		public String LibMotsOriginalDbColumnName() {

			return "LibMots";

		}

		public Double Dctoboleto;

		public Double getDctoboleto() {
			return this.Dctoboleto;
		}

		public Boolean DctoboletoIsNullable() {
			return true;
		}

		public Boolean DctoboletoIsKey() {
			return false;
		}

		public Integer DctoboletoLength() {
			return 15;
		}

		public Integer DctoboletoPrecision() {
			return 0;
		}

		public String DctoboletoDefault() {

			return "";

		}

		public String DctoboletoComment() {

			return "";

		}

		public String DctoboletoPattern() {

			return "";

		}

		public String DctoboletoOriginalDbColumnName() {

			return "Dctoboleto";

		}

		public Integer MPRISeq;

		public Integer getMPRISeq() {
			return this.MPRISeq;
		}

		public Boolean MPRISeqIsNullable() {
			return true;
		}

		public Boolean MPRISeqIsKey() {
			return false;
		}

		public Integer MPRISeqLength() {
			return 10;
		}

		public Integer MPRISeqPrecision() {
			return 0;
		}

		public String MPRISeqDefault() {

			return "";

		}

		public String MPRISeqComment() {

			return "";

		}

		public String MPRISeqPattern() {

			return "";

		}

		public String MPRISeqOriginalDbColumnName() {

			return "MPRISeq";

		}

		public Double Dctomax;

		public Double getDctomax() {
			return this.Dctomax;
		}

		public Boolean DctomaxIsNullable() {
			return true;
		}

		public Boolean DctomaxIsKey() {
			return false;
		}

		public Integer DctomaxLength() {
			return 15;
		}

		public Integer DctomaxPrecision() {
			return 0;
		}

		public String DctomaxDefault() {

			return "";

		}

		public String DctomaxComment() {

			return "";

		}

		public String DctomaxPattern() {

			return "";

		}

		public String DctomaxOriginalDbColumnName() {

			return "Dctomax";

		}

		public Double Comprim;

		public Double getComprim() {
			return this.Comprim;
		}

		public Boolean ComprimIsNullable() {
			return true;
		}

		public Boolean ComprimIsKey() {
			return false;
		}

		public Integer ComprimLength() {
			return 15;
		}

		public Integer ComprimPrecision() {
			return 0;
		}

		public String ComprimDefault() {

			return "";

		}

		public String ComprimComment() {

			return "";

		}

		public String ComprimPattern() {

			return "";

		}

		public String ComprimOriginalDbColumnName() {

			return "Comprim";

		}

		public Double QtdePeca;

		public Double getQtdePeca() {
			return this.QtdePeca;
		}

		public Boolean QtdePecaIsNullable() {
			return true;
		}

		public Boolean QtdePecaIsKey() {
			return false;
		}

		public Integer QtdePecaLength() {
			return 15;
		}

		public Integer QtdePecaPrecision() {
			return 0;
		}

		public String QtdePecaDefault() {

			return "";

		}

		public String QtdePecaComment() {

			return "";

		}

		public String QtdePecaPattern() {

			return "";

		}

		public String QtdePecaOriginalDbColumnName() {

			return "QtdePeca";

		}

		public String ProdRes;

		public String getProdRes() {
			return this.ProdRes;
		}

		public Boolean ProdResIsNullable() {
			return true;
		}

		public Boolean ProdResIsKey() {
			return false;
		}

		public Integer ProdResLength() {
			return 16;
		}

		public Integer ProdResPrecision() {
			return 0;
		}

		public String ProdResDefault() {

			return "";

		}

		public String ProdResComment() {

			return "";

		}

		public String ProdResPattern() {

			return "";

		}

		public String ProdResOriginalDbColumnName() {

			return "ProdRes";

		}

		public String EmpEstoq;

		public String getEmpEstoq() {
			return this.EmpEstoq;
		}

		public Boolean EmpEstoqIsNullable() {
			return true;
		}

		public Boolean EmpEstoqIsKey() {
			return false;
		}

		public Integer EmpEstoqLength() {
			return 3;
		}

		public Integer EmpEstoqPrecision() {
			return 0;
		}

		public String EmpEstoqDefault() {

			return "";

		}

		public String EmpEstoqComment() {

			return "";

		}

		public String EmpEstoqPattern() {

			return "";

		}

		public String EmpEstoqOriginalDbColumnName() {

			return "EmpEstoq";

		}

		public Double PrevProduc;

		public Double getPrevProduc() {
			return this.PrevProduc;
		}

		public Boolean PrevProducIsNullable() {
			return true;
		}

		public Boolean PrevProducIsKey() {
			return false;
		}

		public Integer PrevProducLength() {
			return 15;
		}

		public Integer PrevProducPrecision() {
			return 0;
		}

		public String PrevProducDefault() {

			return "";

		}

		public String PrevProducComment() {

			return "";

		}

		public String PrevProducPattern() {

			return "";

		}

		public String PrevProducOriginalDbColumnName() {

			return "PrevProduc";

		}

		public Float PrunitOri;

		public Float getPrunitOri() {
			return this.PrunitOri;
		}

		public Boolean PrunitOriIsNullable() {
			return true;
		}

		public Boolean PrunitOriIsKey() {
			return false;
		}

		public Integer PrunitOriLength() {
			return 7;
		}

		public Integer PrunitOriPrecision() {
			return 0;
		}

		public String PrunitOriDefault() {

			return "";

		}

		public String PrunitOriComment() {

			return "";

		}

		public String PrunitOriPattern() {

			return "";

		}

		public String PrunitOriOriginalDbColumnName() {

			return "PrunitOri";

		}

		public Float PrliqOri;

		public Float getPrliqOri() {
			return this.PrliqOri;
		}

		public Boolean PrliqOriIsNullable() {
			return true;
		}

		public Boolean PrliqOriIsKey() {
			return false;
		}

		public Integer PrliqOriLength() {
			return 7;
		}

		public Integer PrliqOriPrecision() {
			return 0;
		}

		public String PrliqOriDefault() {

			return "";

		}

		public String PrliqOriComment() {

			return "";

		}

		public String PrliqOriPattern() {

			return "";

		}

		public String PrliqOriOriginalDbColumnName() {

			return "PrliqOri";

		}

		public Double Mva;

		public Double getMva() {
			return this.Mva;
		}

		public Boolean MvaIsNullable() {
			return true;
		}

		public Boolean MvaIsKey() {
			return false;
		}

		public Integer MvaLength() {
			return 15;
		}

		public Integer MvaPrecision() {
			return 0;
		}

		public String MvaDefault() {

			return "";

		}

		public String MvaComment() {

			return "";

		}

		public String MvaPattern() {

			return "";

		}

		public String MvaOriginalDbColumnName() {

			return "Mva";

		}

		public Double ICMSOrig;

		public Double getICMSOrig() {
			return this.ICMSOrig;
		}

		public Boolean ICMSOrigIsNullable() {
			return true;
		}

		public Boolean ICMSOrigIsKey() {
			return false;
		}

		public Integer ICMSOrigLength() {
			return 15;
		}

		public Integer ICMSOrigPrecision() {
			return 0;
		}

		public String ICMSOrigDefault() {

			return "";

		}

		public String ICMSOrigComment() {

			return "";

		}

		public String ICMSOrigPattern() {

			return "";

		}

		public String ICMSOrigOriginalDbColumnName() {

			return "ICMSOrig";

		}

		public Double ICMSDest;

		public Double getICMSDest() {
			return this.ICMSDest;
		}

		public Boolean ICMSDestIsNullable() {
			return true;
		}

		public Boolean ICMSDestIsKey() {
			return false;
		}

		public Integer ICMSDestLength() {
			return 15;
		}

		public Integer ICMSDestPrecision() {
			return 0;
		}

		public String ICMSDestDefault() {

			return "";

		}

		public String ICMSDestComment() {

			return "";

		}

		public String ICMSDestPattern() {

			return "";

		}

		public String ICMSDestOriginalDbColumnName() {

			return "ICMSDest";

		}

		public Double Float1;

		public Double getFloat1() {
			return this.Float1;
		}

		public Boolean Float1IsNullable() {
			return true;
		}

		public Boolean Float1IsKey() {
			return false;
		}

		public Integer Float1Length() {
			return 15;
		}

		public Integer Float1Precision() {
			return 0;
		}

		public String Float1Default() {

			return "";

		}

		public String Float1Comment() {

			return "";

		}

		public String Float1Pattern() {

			return "";

		}

		public String Float1OriginalDbColumnName() {

			return "Float1";

		}

		public Double Float2;

		public Double getFloat2() {
			return this.Float2;
		}

		public Boolean Float2IsNullable() {
			return true;
		}

		public Boolean Float2IsKey() {
			return false;
		}

		public Integer Float2Length() {
			return 15;
		}

		public Integer Float2Precision() {
			return 0;
		}

		public String Float2Default() {

			return "";

		}

		public String Float2Comment() {

			return "";

		}

		public String Float2Pattern() {

			return "";

		}

		public String Float2OriginalDbColumnName() {

			return "Float2";

		}

		public java.util.Date DataAlter;

		public java.util.Date getDataAlter() {
			return this.DataAlter;
		}

		public Boolean DataAlterIsNullable() {
			return true;
		}

		public Boolean DataAlterIsKey() {
			return false;
		}

		public Integer DataAlterLength() {
			return 23;
		}

		public Integer DataAlterPrecision() {
			return 3;
		}

		public String DataAlterDefault() {

			return "";

		}

		public String DataAlterComment() {

			return "";

		}

		public String DataAlterPattern() {

			return "dd-MM-yyyy";

		}

		public String DataAlterOriginalDbColumnName() {

			return "DataAlter";

		}

		public String SeqOrig;

		public String getSeqOrig() {
			return this.SeqOrig;
		}

		public Boolean SeqOrigIsNullable() {
			return true;
		}

		public Boolean SeqOrigIsKey() {
			return false;
		}

		public Integer SeqOrigLength() {
			return 12;
		}

		public Integer SeqOrigPrecision() {
			return 0;
		}

		public String SeqOrigDefault() {

			return "";

		}

		public String SeqOrigComment() {

			return "";

		}

		public String SeqOrigPattern() {

			return "";

		}

		public String SeqOrigOriginalDbColumnName() {

			return "SeqOrig";

		}

		public Double PrecoNorm;

		public Double getPrecoNorm() {
			return this.PrecoNorm;
		}

		public Boolean PrecoNormIsNullable() {
			return true;
		}

		public Boolean PrecoNormIsKey() {
			return false;
		}

		public Integer PrecoNormLength() {
			return 15;
		}

		public Integer PrecoNormPrecision() {
			return 0;
		}

		public String PrecoNormDefault() {

			return "";

		}

		public String PrecoNormComment() {

			return "";

		}

		public String PrecoNormPattern() {

			return "";

		}

		public String PrecoNormOriginalDbColumnName() {

			return "PrecoNorm";

		}

		public Short Tipost;

		public Short getTipost() {
			return this.Tipost;
		}

		public Boolean TipostIsNullable() {
			return true;
		}

		public Boolean TipostIsKey() {
			return false;
		}

		public Integer TipostLength() {
			return 5;
		}

		public Integer TipostPrecision() {
			return 0;
		}

		public String TipostDefault() {

			return "";

		}

		public String TipostComment() {

			return "";

		}

		public String TipostPattern() {

			return "";

		}

		public String TipostOriginalDbColumnName() {

			return "Tipost";

		}

		public String CodProdcli;

		public String getCodProdcli() {
			return this.CodProdcli;
		}

		public Boolean CodProdcliIsNullable() {
			return true;
		}

		public Boolean CodProdcliIsKey() {
			return false;
		}

		public Integer CodProdcliLength() {
			return 20;
		}

		public Integer CodProdcliPrecision() {
			return 0;
		}

		public String CodProdcliDefault() {

			return "";

		}

		public String CodProdcliComment() {

			return "";

		}

		public String CodProdcliPattern() {

			return "";

		}

		public String CodProdcliOriginalDbColumnName() {

			return "CodProdcli";

		}

		public Double VlrDescarga;

		public Double getVlrDescarga() {
			return this.VlrDescarga;
		}

		public Boolean VlrDescargaIsNullable() {
			return true;
		}

		public Boolean VlrDescargaIsKey() {
			return false;
		}

		public Integer VlrDescargaLength() {
			return 15;
		}

		public Integer VlrDescargaPrecision() {
			return 0;
		}

		public String VlrDescargaDefault() {

			return "";

		}

		public String VlrDescargaComment() {

			return "";

		}

		public String VlrDescargaPattern() {

			return "";

		}

		public String VlrDescargaOriginalDbColumnName() {

			return "VlrDescarga";

		}

		public Short IcmsDscto;

		public Short getIcmsDscto() {
			return this.IcmsDscto;
		}

		public Boolean IcmsDsctoIsNullable() {
			return true;
		}

		public Boolean IcmsDsctoIsKey() {
			return false;
		}

		public Integer IcmsDsctoLength() {
			return 5;
		}

		public Integer IcmsDsctoPrecision() {
			return 0;
		}

		public String IcmsDsctoDefault() {

			return "";

		}

		public String IcmsDsctoComment() {

			return "";

		}

		public String IcmsDsctoPattern() {

			return "";

		}

		public String IcmsDsctoOriginalDbColumnName() {

			return "IcmsDscto";

		}

		public String EmpSplit;

		public String getEmpSplit() {
			return this.EmpSplit;
		}

		public Boolean EmpSplitIsNullable() {
			return true;
		}

		public Boolean EmpSplitIsKey() {
			return false;
		}

		public Integer EmpSplitLength() {
			return 3;
		}

		public Integer EmpSplitPrecision() {
			return 0;
		}

		public String EmpSplitDefault() {

			return "";

		}

		public String EmpSplitComment() {

			return "";

		}

		public String EmpSplitPattern() {

			return "";

		}

		public String EmpSplitOriginalDbColumnName() {

			return "EmpSplit";

		}

		public Integer Status;

		public Integer getStatus() {
			return this.Status;
		}

		public Boolean StatusIsNullable() {
			return true;
		}

		public Boolean StatusIsKey() {
			return false;
		}

		public Integer StatusLength() {
			return 10;
		}

		public Integer StatusPrecision() {
			return 0;
		}

		public String StatusDefault() {

			return "";

		}

		public String StatusComment() {

			return "";

		}

		public String StatusPattern() {

			return "";

		}

		public String StatusOriginalDbColumnName() {

			return "Status";

		}

		public Integer Obrigatorio;

		public Integer getObrigatorio() {
			return this.Obrigatorio;
		}

		public Boolean ObrigatorioIsNullable() {
			return true;
		}

		public Boolean ObrigatorioIsKey() {
			return false;
		}

		public Integer ObrigatorioLength() {
			return 10;
		}

		public Integer ObrigatorioPrecision() {
			return 0;
		}

		public String ObrigatorioDefault() {

			return "";

		}

		public String ObrigatorioComment() {

			return "";

		}

		public String ObrigatorioPattern() {

			return "";

		}

		public String ObrigatorioOriginalDbColumnName() {

			return "Obrigatorio";

		}

		public Integer PrecoFirme;

		public Integer getPrecoFirme() {
			return this.PrecoFirme;
		}

		public Boolean PrecoFirmeIsNullable() {
			return true;
		}

		public Boolean PrecoFirmeIsKey() {
			return false;
		}

		public Integer PrecoFirmeLength() {
			return 10;
		}

		public Integer PrecoFirmePrecision() {
			return 0;
		}

		public String PrecoFirmeDefault() {

			return "";

		}

		public String PrecoFirmeComment() {

			return "";

		}

		public String PrecoFirmePattern() {

			return "";

		}

		public String PrecoFirmeOriginalDbColumnName() {

			return "PrecoFirme";

		}

		public String OrdCompra;

		public String getOrdCompra() {
			return this.OrdCompra;
		}

		public Boolean OrdCompraIsNullable() {
			return true;
		}

		public Boolean OrdCompraIsKey() {
			return false;
		}

		public Integer OrdCompraLength() {
			return 25;
		}

		public Integer OrdCompraPrecision() {
			return 0;
		}

		public String OrdCompraDefault() {

			return "";

		}

		public String OrdCompraComment() {

			return "";

		}

		public String OrdCompraPattern() {

			return "";

		}

		public String OrdCompraOriginalDbColumnName() {

			return "OrdCompra";

		}

		public Double DesctoFiscal;

		public Double getDesctoFiscal() {
			return this.DesctoFiscal;
		}

		public Boolean DesctoFiscalIsNullable() {
			return true;
		}

		public Boolean DesctoFiscalIsKey() {
			return false;
		}

		public Integer DesctoFiscalLength() {
			return 15;
		}

		public Integer DesctoFiscalPrecision() {
			return 0;
		}

		public String DesctoFiscalDefault() {

			return "";

		}

		public String DesctoFiscalComment() {

			return "";

		}

		public String DesctoFiscalPattern() {

			return "";

		}

		public String DesctoFiscalOriginalDbColumnName() {

			return "DesctoFiscal";

		}

		public Double PercMlucr;

		public Double getPercMlucr() {
			return this.PercMlucr;
		}

		public Boolean PercMlucrIsNullable() {
			return true;
		}

		public Boolean PercMlucrIsKey() {
			return false;
		}

		public Integer PercMlucrLength() {
			return 15;
		}

		public Integer PercMlucrPrecision() {
			return 0;
		}

		public String PercMlucrDefault() {

			return "";

		}

		public String PercMlucrComment() {

			return "";

		}

		public String PercMlucrPattern() {

			return "";

		}

		public String PercMlucrOriginalDbColumnName() {

			return "PercMlucr";

		}

		public Double VlrDescargaCalc;

		public Double getVlrDescargaCalc() {
			return this.VlrDescargaCalc;
		}

		public Boolean VlrDescargaCalcIsNullable() {
			return true;
		}

		public Boolean VlrDescargaCalcIsKey() {
			return false;
		}

		public Integer VlrDescargaCalcLength() {
			return 15;
		}

		public Integer VlrDescargaCalcPrecision() {
			return 0;
		}

		public String VlrDescargaCalcDefault() {

			return "";

		}

		public String VlrDescargaCalcComment() {

			return "";

		}

		public String VlrDescargaCalcPattern() {

			return "";

		}

		public String VlrDescargaCalcOriginalDbColumnName() {

			return "VlrDescargaCalc";

		}

		public Double VlrFreTitCalc;

		public Double getVlrFreTitCalc() {
			return this.VlrFreTitCalc;
		}

		public Boolean VlrFreTitCalcIsNullable() {
			return true;
		}

		public Boolean VlrFreTitCalcIsKey() {
			return false;
		}

		public Integer VlrFreTitCalcLength() {
			return 15;
		}

		public Integer VlrFreTitCalcPrecision() {
			return 0;
		}

		public String VlrFreTitCalcDefault() {

			return "";

		}

		public String VlrFreTitCalcComment() {

			return "";

		}

		public String VlrFreTitCalcPattern() {

			return "";

		}

		public String VlrFreTitCalcOriginalDbColumnName() {

			return "VlrFreTitCalc";

		}

		public Double VuComissao;

		public Double getVuComissao() {
			return this.VuComissao;
		}

		public Boolean VuComissaoIsNullable() {
			return true;
		}

		public Boolean VuComissaoIsKey() {
			return false;
		}

		public Integer VuComissaoLength() {
			return 15;
		}

		public Integer VuComissaoPrecision() {
			return 0;
		}

		public String VuComissaoDefault() {

			return "";

		}

		public String VuComissaoComment() {

			return "";

		}

		public String VuComissaoPattern() {

			return "";

		}

		public String VuComissaoOriginalDbColumnName() {

			return "VuComissao";

		}

		public Double VuDebcli;

		public Double getVuDebcli() {
			return this.VuDebcli;
		}

		public Boolean VuDebcliIsNullable() {
			return true;
		}

		public Boolean VuDebcliIsKey() {
			return false;
		}

		public Integer VuDebcliLength() {
			return 15;
		}

		public Integer VuDebcliPrecision() {
			return 0;
		}

		public String VuDebcliDefault() {

			return "";

		}

		public String VuDebcliComment() {

			return "";

		}

		public String VuDebcliPattern() {

			return "";

		}

		public String VuDebcliOriginalDbColumnName() {

			return "VuDebcli";

		}

		public java.util.Date Data1Original;

		public java.util.Date getData1Original() {
			return this.Data1Original;
		}

		public Boolean Data1OriginalIsNullable() {
			return true;
		}

		public Boolean Data1OriginalIsKey() {
			return false;
		}

		public Integer Data1OriginalLength() {
			return 23;
		}

		public Integer Data1OriginalPrecision() {
			return 3;
		}

		public String Data1OriginalDefault() {

			return "";

		}

		public String Data1OriginalComment() {

			return "";

		}

		public String Data1OriginalPattern() {

			return "dd-MM-yyyy";

		}

		public String Data1OriginalOriginalDbColumnName() {

			return "Data1Original";

		}

		public Integer Visao;

		public Integer getVisao() {
			return this.Visao;
		}

		public Boolean VisaoIsNullable() {
			return true;
		}

		public Boolean VisaoIsKey() {
			return false;
		}

		public Integer VisaoLength() {
			return 10;
		}

		public Integer VisaoPrecision() {
			return 0;
		}

		public String VisaoDefault() {

			return "";

		}

		public String VisaoComment() {

			return "";

		}

		public String VisaoPattern() {

			return "";

		}

		public String VisaoOriginalDbColumnName() {

			return "Visao";

		}

		public Integer Periodo;

		public Integer getPeriodo() {
			return this.Periodo;
		}

		public Boolean PeriodoIsNullable() {
			return true;
		}

		public Boolean PeriodoIsKey() {
			return false;
		}

		public Integer PeriodoLength() {
			return 10;
		}

		public Integer PeriodoPrecision() {
			return 0;
		}

		public String PeriodoDefault() {

			return "";

		}

		public String PeriodoComment() {

			return "";

		}

		public String PeriodoPattern() {

			return "";

		}

		public String PeriodoOriginalDbColumnName() {

			return "Periodo";

		}

		public Double BaseIcms;

		public Double getBaseIcms() {
			return this.BaseIcms;
		}

		public Boolean BaseIcmsIsNullable() {
			return true;
		}

		public Boolean BaseIcmsIsKey() {
			return false;
		}

		public Integer BaseIcmsLength() {
			return 15;
		}

		public Integer BaseIcmsPrecision() {
			return 0;
		}

		public String BaseIcmsDefault() {

			return "";

		}

		public String BaseIcmsComment() {

			return "";

		}

		public String BaseIcmsPattern() {

			return "";

		}

		public String BaseIcmsOriginalDbColumnName() {

			return "BaseIcms";

		}

		public Double BaseSt;

		public Double getBaseSt() {
			return this.BaseSt;
		}

		public Boolean BaseStIsNullable() {
			return true;
		}

		public Boolean BaseStIsKey() {
			return false;
		}

		public Integer BaseStLength() {
			return 15;
		}

		public Integer BaseStPrecision() {
			return 0;
		}

		public String BaseStDefault() {

			return "";

		}

		public String BaseStComment() {

			return "";

		}

		public String BaseStPattern() {

			return "";

		}

		public String BaseStOriginalDbColumnName() {

			return "BaseSt";

		}

		public Integer CondPgto;

		public Integer getCondPgto() {
			return this.CondPgto;
		}

		public Boolean CondPgtoIsNullable() {
			return true;
		}

		public Boolean CondPgtoIsKey() {
			return false;
		}

		public Integer CondPgtoLength() {
			return 10;
		}

		public Integer CondPgtoPrecision() {
			return 0;
		}

		public String CondPgtoDefault() {

			return "";

		}

		public String CondPgtoComment() {

			return "";

		}

		public String CondPgtoPattern() {

			return "";

		}

		public String CondPgtoOriginalDbColumnName() {

			return "CondPgto";

		}

		public String UsuCanc;

		public String getUsuCanc() {
			return this.UsuCanc;
		}

		public Boolean UsuCancIsNullable() {
			return true;
		}

		public Boolean UsuCancIsKey() {
			return false;
		}

		public Integer UsuCancLength() {
			return 20;
		}

		public Integer UsuCancPrecision() {
			return 0;
		}

		public String UsuCancDefault() {

			return "";

		}

		public String UsuCancComment() {

			return "";

		}

		public String UsuCancPattern() {

			return "";

		}

		public String UsuCancOriginalDbColumnName() {

			return "UsuCanc";

		}

		public Double Resrede;

		public Double getResrede() {
			return this.Resrede;
		}

		public Boolean ResredeIsNullable() {
			return true;
		}

		public Boolean ResredeIsKey() {
			return false;
		}

		public Integer ResredeLength() {
			return 15;
		}

		public Integer ResredePrecision() {
			return 0;
		}

		public String ResredeDefault() {

			return "";

		}

		public String ResredeComment() {

			return "";

		}

		public String ResredePattern() {

			return "";

		}

		public String ResredeOriginalDbColumnName() {

			return "Resrede";

		}

		public String DocBloq;

		public String getDocBloq() {
			return this.DocBloq;
		}

		public Boolean DocBloqIsNullable() {
			return true;
		}

		public Boolean DocBloqIsKey() {
			return false;
		}

		public Integer DocBloqLength() {
			return 100;
		}

		public Integer DocBloqPrecision() {
			return 0;
		}

		public String DocBloqDefault() {

			return "";

		}

		public String DocBloqComment() {

			return "";

		}

		public String DocBloqPattern() {

			return "";

		}

		public String DocBloqOriginalDbColumnName() {

			return "DocBloq";

		}

		public String Regracomis;

		public String getRegracomis() {
			return this.Regracomis;
		}

		public Boolean RegracomisIsNullable() {
			return true;
		}

		public Boolean RegracomisIsKey() {
			return false;
		}

		public Integer RegracomisLength() {
			return 8;
		}

		public Integer RegracomisPrecision() {
			return 0;
		}

		public String RegracomisDefault() {

			return "";

		}

		public String RegracomisComment() {

			return "";

		}

		public String RegracomisPattern() {

			return "";

		}

		public String RegracomisOriginalDbColumnName() {

			return "Regracomis";

		}

		public Double BaseIcmsRed;

		public Double getBaseIcmsRed() {
			return this.BaseIcmsRed;
		}

		public Boolean BaseIcmsRedIsNullable() {
			return true;
		}

		public Boolean BaseIcmsRedIsKey() {
			return false;
		}

		public Integer BaseIcmsRedLength() {
			return 15;
		}

		public Integer BaseIcmsRedPrecision() {
			return 0;
		}

		public String BaseIcmsRedDefault() {

			return "";

		}

		public String BaseIcmsRedComment() {

			return "";

		}

		public String BaseIcmsRedPattern() {

			return "";

		}

		public String BaseIcmsRedOriginalDbColumnName() {

			return "BaseIcmsRed";

		}

		public Double IcmsSemRed;

		public Double getIcmsSemRed() {
			return this.IcmsSemRed;
		}

		public Boolean IcmsSemRedIsNullable() {
			return true;
		}

		public Boolean IcmsSemRedIsKey() {
			return false;
		}

		public Integer IcmsSemRedLength() {
			return 15;
		}

		public Integer IcmsSemRedPrecision() {
			return 0;
		}

		public String IcmsSemRedDefault() {

			return "";

		}

		public String IcmsSemRedComment() {

			return "";

		}

		public String IcmsSemRedPattern() {

			return "";

		}

		public String IcmsSemRedOriginalDbColumnName() {

			return "IcmsSemRed";

		}

		public String Obs1;

		public String getObs1() {
			return this.Obs1;
		}

		public Boolean Obs1IsNullable() {
			return true;
		}

		public Boolean Obs1IsKey() {
			return false;
		}

		public Integer Obs1Length() {
			return 255;
		}

		public Integer Obs1Precision() {
			return 0;
		}

		public String Obs1Default() {

			return "";

		}

		public String Obs1Comment() {

			return "";

		}

		public String Obs1Pattern() {

			return "";

		}

		public String Obs1OriginalDbColumnName() {

			return "Obs1";

		}

		public String Obs2;

		public String getObs2() {
			return this.Obs2;
		}

		public Boolean Obs2IsNullable() {
			return true;
		}

		public Boolean Obs2IsKey() {
			return false;
		}

		public Integer Obs2Length() {
			return 255;
		}

		public Integer Obs2Precision() {
			return 0;
		}

		public String Obs2Default() {

			return "";

		}

		public String Obs2Comment() {

			return "";

		}

		public String Obs2Pattern() {

			return "";

		}

		public String Obs2OriginalDbColumnName() {

			return "Obs2";

		}

		public String CodEmbal;

		public String getCodEmbal() {
			return this.CodEmbal;
		}

		public Boolean CodEmbalIsNullable() {
			return true;
		}

		public Boolean CodEmbalIsKey() {
			return false;
		}

		public Integer CodEmbalLength() {
			return 3;
		}

		public Integer CodEmbalPrecision() {
			return 0;
		}

		public String CodEmbalDefault() {

			return "";

		}

		public String CodEmbalComment() {

			return "";

		}

		public String CodEmbalPattern() {

			return "";

		}

		public String CodEmbalOriginalDbColumnName() {

			return "CodEmbal";

		}

		public Integer CondPgtoCalc;

		public Integer getCondPgtoCalc() {
			return this.CondPgtoCalc;
		}

		public Boolean CondPgtoCalcIsNullable() {
			return true;
		}

		public Boolean CondPgtoCalcIsKey() {
			return false;
		}

		public Integer CondPgtoCalcLength() {
			return 10;
		}

		public Integer CondPgtoCalcPrecision() {
			return 0;
		}

		public String CondPgtoCalcDefault() {

			return "";

		}

		public String CondPgtoCalcComment() {

			return "";

		}

		public String CondPgtoCalcPattern() {

			return "";

		}

		public String CondPgtoCalcOriginalDbColumnName() {

			return "CondPgtoCalc";

		}

		public Integer CodigoMix;

		public Integer getCodigoMix() {
			return this.CodigoMix;
		}

		public Boolean CodigoMixIsNullable() {
			return true;
		}

		public Boolean CodigoMixIsKey() {
			return false;
		}

		public Integer CodigoMixLength() {
			return 10;
		}

		public Integer CodigoMixPrecision() {
			return 0;
		}

		public String CodigoMixDefault() {

			return "";

		}

		public String CodigoMixComment() {

			return "";

		}

		public String CodigoMixPattern() {

			return "";

		}

		public String CodigoMixOriginalDbColumnName() {

			return "CodigoMix";

		}

		public Integer QtdeMix;

		public Integer getQtdeMix() {
			return this.QtdeMix;
		}

		public Boolean QtdeMixIsNullable() {
			return true;
		}

		public Boolean QtdeMixIsKey() {
			return false;
		}

		public Integer QtdeMixLength() {
			return 10;
		}

		public Integer QtdeMixPrecision() {
			return 0;
		}

		public String QtdeMixDefault() {

			return "";

		}

		public String QtdeMixComment() {

			return "";

		}

		public String QtdeMixPattern() {

			return "";

		}

		public String QtdeMixOriginalDbColumnName() {

			return "QtdeMix";

		}

		public String Lote;

		public String getLote() {
			return this.Lote;
		}

		public Boolean LoteIsNullable() {
			return true;
		}

		public Boolean LoteIsKey() {
			return false;
		}

		public Integer LoteLength() {
			return 25;
		}

		public Integer LotePrecision() {
			return 0;
		}

		public String LoteDefault() {

			return "";

		}

		public String LoteComment() {

			return "";

		}

		public String LotePattern() {

			return "";

		}

		public String LoteOriginalDbColumnName() {

			return "Lote";

		}

		public Double VlrDesctoFiscal;

		public Double getVlrDesctoFiscal() {
			return this.VlrDesctoFiscal;
		}

		public Boolean VlrDesctoFiscalIsNullable() {
			return true;
		}

		public Boolean VlrDesctoFiscalIsKey() {
			return false;
		}

		public Integer VlrDesctoFiscalLength() {
			return 15;
		}

		public Integer VlrDesctoFiscalPrecision() {
			return 0;
		}

		public String VlrDesctoFiscalDefault() {

			return "";

		}

		public String VlrDesctoFiscalComment() {

			return "";

		}

		public String VlrDesctoFiscalPattern() {

			return "";

		}

		public String VlrDesctoFiscalOriginalDbColumnName() {

			return "VlrDesctoFiscal";

		}

		public String Tamanho;

		public String getTamanho() {
			return this.Tamanho;
		}

		public Boolean TamanhoIsNullable() {
			return true;
		}

		public Boolean TamanhoIsKey() {
			return false;
		}

		public Integer TamanhoLength() {
			return 255;
		}

		public Integer TamanhoPrecision() {
			return 0;
		}

		public String TamanhoDefault() {

			return "";

		}

		public String TamanhoComment() {

			return "";

		}

		public String TamanhoPattern() {

			return "";

		}

		public String TamanhoOriginalDbColumnName() {

			return "Tamanho";

		}

		public Integer BloqVerba;

		public Integer getBloqVerba() {
			return this.BloqVerba;
		}

		public Boolean BloqVerbaIsNullable() {
			return true;
		}

		public Boolean BloqVerbaIsKey() {
			return false;
		}

		public Integer BloqVerbaLength() {
			return 10;
		}

		public Integer BloqVerbaPrecision() {
			return 0;
		}

		public String BloqVerbaDefault() {

			return "";

		}

		public String BloqVerbaComment() {

			return "";

		}

		public String BloqVerbaPattern() {

			return "";

		}

		public String BloqVerbaOriginalDbColumnName() {

			return "BloqVerba";

		}

		public Double VlrFreTitRateio;

		public Double getVlrFreTitRateio() {
			return this.VlrFreTitRateio;
		}

		public Boolean VlrFreTitRateioIsNullable() {
			return true;
		}

		public Boolean VlrFreTitRateioIsKey() {
			return false;
		}

		public Integer VlrFreTitRateioLength() {
			return 15;
		}

		public Integer VlrFreTitRateioPrecision() {
			return 0;
		}

		public String VlrFreTitRateioDefault() {

			return "";

		}

		public String VlrFreTitRateioComment() {

			return "";

		}

		public String VlrFreTitRateioPattern() {

			return "";

		}

		public String VlrFreTitRateioOriginalDbColumnName() {

			return "VlrFreTitRateio";

		}

		public Double VlrFreTitRateioCalc;

		public Double getVlrFreTitRateioCalc() {
			return this.VlrFreTitRateioCalc;
		}

		public Boolean VlrFreTitRateioCalcIsNullable() {
			return true;
		}

		public Boolean VlrFreTitRateioCalcIsKey() {
			return false;
		}

		public Integer VlrFreTitRateioCalcLength() {
			return 15;
		}

		public Integer VlrFreTitRateioCalcPrecision() {
			return 0;
		}

		public String VlrFreTitRateioCalcDefault() {

			return "";

		}

		public String VlrFreTitRateioCalcComment() {

			return "";

		}

		public String VlrFreTitRateioCalcPattern() {

			return "";

		}

		public String VlrFreTitRateioCalcOriginalDbColumnName() {

			return "VlrFreTitRateioCalc";

		}

		public Integer VisaoCota;

		public Integer getVisaoCota() {
			return this.VisaoCota;
		}

		public Boolean VisaoCotaIsNullable() {
			return true;
		}

		public Boolean VisaoCotaIsKey() {
			return false;
		}

		public Integer VisaoCotaLength() {
			return 10;
		}

		public Integer VisaoCotaPrecision() {
			return 0;
		}

		public String VisaoCotaDefault() {

			return "";

		}

		public String VisaoCotaComment() {

			return "";

		}

		public String VisaoCotaPattern() {

			return "";

		}

		public String VisaoCotaOriginalDbColumnName() {

			return "VisaoCota";

		}

		public Integer PeriodoCota;

		public Integer getPeriodoCota() {
			return this.PeriodoCota;
		}

		public Boolean PeriodoCotaIsNullable() {
			return true;
		}

		public Boolean PeriodoCotaIsKey() {
			return false;
		}

		public Integer PeriodoCotaLength() {
			return 10;
		}

		public Integer PeriodoCotaPrecision() {
			return 0;
		}

		public String PeriodoCotaDefault() {

			return "";

		}

		public String PeriodoCotaComment() {

			return "";

		}

		public String PeriodoCotaPattern() {

			return "";

		}

		public String PeriodoCotaOriginalDbColumnName() {

			return "PeriodoCota";

		}

		public Integer VisaoCotaOriginal;

		public Integer getVisaoCotaOriginal() {
			return this.VisaoCotaOriginal;
		}

		public Boolean VisaoCotaOriginalIsNullable() {
			return true;
		}

		public Boolean VisaoCotaOriginalIsKey() {
			return false;
		}

		public Integer VisaoCotaOriginalLength() {
			return 10;
		}

		public Integer VisaoCotaOriginalPrecision() {
			return 0;
		}

		public String VisaoCotaOriginalDefault() {

			return "";

		}

		public String VisaoCotaOriginalComment() {

			return "";

		}

		public String VisaoCotaOriginalPattern() {

			return "";

		}

		public String VisaoCotaOriginalOriginalDbColumnName() {

			return "VisaoCotaOriginal";

		}

		public Integer PeriodoCotaOriginal;

		public Integer getPeriodoCotaOriginal() {
			return this.PeriodoCotaOriginal;
		}

		public Boolean PeriodoCotaOriginalIsNullable() {
			return true;
		}

		public Boolean PeriodoCotaOriginalIsKey() {
			return false;
		}

		public Integer PeriodoCotaOriginalLength() {
			return 10;
		}

		public Integer PeriodoCotaOriginalPrecision() {
			return 0;
		}

		public String PeriodoCotaOriginalDefault() {

			return "";

		}

		public String PeriodoCotaOriginalComment() {

			return "";

		}

		public String PeriodoCotaOriginalPattern() {

			return "";

		}

		public String PeriodoCotaOriginalOriginalDbColumnName() {

			return "PeriodoCotaOriginal";

		}

		public Integer RegraEmpItem;

		public Integer getRegraEmpItem() {
			return this.RegraEmpItem;
		}

		public Boolean RegraEmpItemIsNullable() {
			return true;
		}

		public Boolean RegraEmpItemIsKey() {
			return false;
		}

		public Integer RegraEmpItemLength() {
			return 10;
		}

		public Integer RegraEmpItemPrecision() {
			return 0;
		}

		public String RegraEmpItemDefault() {

			return "";

		}

		public String RegraEmpItemComment() {

			return "";

		}

		public String RegraEmpItemPattern() {

			return "";

		}

		public String RegraEmpItemOriginalDbColumnName() {

			return "RegraEmpItem";

		}

		public Integer LjPeca;

		public Integer getLjPeca() {
			return this.LjPeca;
		}

		public Boolean LjPecaIsNullable() {
			return true;
		}

		public Boolean LjPecaIsKey() {
			return false;
		}

		public Integer LjPecaLength() {
			return 10;
		}

		public Integer LjPecaPrecision() {
			return 0;
		}

		public String LjPecaDefault() {

			return "";

		}

		public String LjPecaComment() {

			return "";

		}

		public String LjPecaPattern() {

			return "";

		}

		public String LjPecaOriginalDbColumnName() {

			return "LjPeca";

		}

		public Double LjTamanho;

		public Double getLjTamanho() {
			return this.LjTamanho;
		}

		public Boolean LjTamanhoIsNullable() {
			return true;
		}

		public Boolean LjTamanhoIsKey() {
			return false;
		}

		public Integer LjTamanhoLength() {
			return 15;
		}

		public Integer LjTamanhoPrecision() {
			return 0;
		}

		public String LjTamanhoDefault() {

			return "";

		}

		public String LjTamanhoComment() {

			return "";

		}

		public String LjTamanhoPattern() {

			return "";

		}

		public String LjTamanhoOriginalDbColumnName() {

			return "LjTamanho";

		}

		public Integer LjCarga;

		public Integer getLjCarga() {
			return this.LjCarga;
		}

		public Boolean LjCargaIsNullable() {
			return true;
		}

		public Boolean LjCargaIsKey() {
			return false;
		}

		public Integer LjCargaLength() {
			return 10;
		}

		public Integer LjCargaPrecision() {
			return 0;
		}

		public String LjCargaDefault() {

			return "";

		}

		public String LjCargaComment() {

			return "";

		}

		public String LjCargaPattern() {

			return "";

		}

		public String LjCargaOriginalDbColumnName() {

			return "LjCarga";

		}

		public Double LjMedidaA;

		public Double getLjMedidaA() {
			return this.LjMedidaA;
		}

		public Boolean LjMedidaAIsNullable() {
			return true;
		}

		public Boolean LjMedidaAIsKey() {
			return false;
		}

		public Integer LjMedidaALength() {
			return 15;
		}

		public Integer LjMedidaAPrecision() {
			return 0;
		}

		public String LjMedidaADefault() {

			return "";

		}

		public String LjMedidaAComment() {

			return "";

		}

		public String LjMedidaAPattern() {

			return "";

		}

		public String LjMedidaAOriginalDbColumnName() {

			return "LjMedidaA";

		}

		public Double LjVao;

		public Double getLjVao() {
			return this.LjVao;
		}

		public Boolean LjVaoIsNullable() {
			return true;
		}

		public Boolean LjVaoIsKey() {
			return false;
		}

		public Integer LjVaoLength() {
			return 15;
		}

		public Integer LjVaoPrecision() {
			return 0;
		}

		public String LjVaoDefault() {

			return "";

		}

		public String LjVaoComment() {

			return "";

		}

		public String LjVaoPattern() {

			return "";

		}

		public String LjVaoOriginalDbColumnName() {

			return "LjVao";

		}

		public Integer LjAngulo;

		public Integer getLjAngulo() {
			return this.LjAngulo;
		}

		public Boolean LjAnguloIsNullable() {
			return true;
		}

		public Boolean LjAnguloIsKey() {
			return false;
		}

		public Integer LjAnguloLength() {
			return 10;
		}

		public Integer LjAnguloPrecision() {
			return 0;
		}

		public String LjAnguloDefault() {

			return "";

		}

		public String LjAnguloComment() {

			return "";

		}

		public String LjAnguloPattern() {

			return "";

		}

		public String LjAnguloOriginalDbColumnName() {

			return "LjAngulo";

		}

		public Double LjMedidaB;

		public Double getLjMedidaB() {
			return this.LjMedidaB;
		}

		public Boolean LjMedidaBIsNullable() {
			return true;
		}

		public Boolean LjMedidaBIsKey() {
			return false;
		}

		public Integer LjMedidaBLength() {
			return 15;
		}

		public Integer LjMedidaBPrecision() {
			return 0;
		}

		public String LjMedidaBDefault() {

			return "";

		}

		public String LjMedidaBComment() {

			return "";

		}

		public String LjMedidaBPattern() {

			return "";

		}

		public String LjMedidaBOriginalDbColumnName() {

			return "LjMedidaB";

		}

		public Integer LjInicioViga;

		public Integer getLjInicioViga() {
			return this.LjInicioViga;
		}

		public Boolean LjInicioVigaIsNullable() {
			return true;
		}

		public Boolean LjInicioVigaIsKey() {
			return false;
		}

		public Integer LjInicioVigaLength() {
			return 10;
		}

		public Integer LjInicioVigaPrecision() {
			return 0;
		}

		public String LjInicioVigaDefault() {

			return "";

		}

		public String LjInicioVigaComment() {

			return "";

		}

		public String LjInicioVigaPattern() {

			return "";

		}

		public String LjInicioVigaOriginalDbColumnName() {

			return "LjInicioViga";

		}

		public Integer LjAba;

		public Integer getLjAba() {
			return this.LjAba;
		}

		public Boolean LjAbaIsNullable() {
			return true;
		}

		public Boolean LjAbaIsKey() {
			return false;
		}

		public Integer LjAbaLength() {
			return 10;
		}

		public Integer LjAbaPrecision() {
			return 0;
		}

		public String LjAbaDefault() {

			return "";

		}

		public String LjAbaComment() {

			return "";

		}

		public String LjAbaPattern() {

			return "";

		}

		public String LjAbaOriginalDbColumnName() {

			return "LjAba";

		}

		public Double LjAbaA;

		public Double getLjAbaA() {
			return this.LjAbaA;
		}

		public Boolean LjAbaAIsNullable() {
			return true;
		}

		public Boolean LjAbaAIsKey() {
			return false;
		}

		public Integer LjAbaALength() {
			return 15;
		}

		public Integer LjAbaAPrecision() {
			return 0;
		}

		public String LjAbaADefault() {

			return "";

		}

		public String LjAbaAComment() {

			return "";

		}

		public String LjAbaAPattern() {

			return "";

		}

		public String LjAbaAOriginalDbColumnName() {

			return "LjAbaA";

		}

		public Double LjAbaB;

		public Double getLjAbaB() {
			return this.LjAbaB;
		}

		public Boolean LjAbaBIsNullable() {
			return true;
		}

		public Boolean LjAbaBIsKey() {
			return false;
		}

		public Integer LjAbaBLength() {
			return 15;
		}

		public Integer LjAbaBPrecision() {
			return 0;
		}

		public String LjAbaBDefault() {

			return "";

		}

		public String LjAbaBComment() {

			return "";

		}

		public String LjAbaBPattern() {

			return "";

		}

		public String LjAbaBOriginalDbColumnName() {

			return "LjAbaB";

		}

		public Integer Bloqluc;

		public Integer getBloqluc() {
			return this.Bloqluc;
		}

		public Boolean BloqlucIsNullable() {
			return true;
		}

		public Boolean BloqlucIsKey() {
			return false;
		}

		public Integer BloqlucLength() {
			return 10;
		}

		public Integer BloqlucPrecision() {
			return 0;
		}

		public String BloqlucDefault() {

			return "";

		}

		public String BloqlucComment() {

			return "";

		}

		public String BloqlucPattern() {

			return "";

		}

		public String BloqlucOriginalDbColumnName() {

			return "Bloqluc";

		}

		public Integer OrigemCota;

		public Integer getOrigemCota() {
			return this.OrigemCota;
		}

		public Boolean OrigemCotaIsNullable() {
			return true;
		}

		public Boolean OrigemCotaIsKey() {
			return false;
		}

		public Integer OrigemCotaLength() {
			return 10;
		}

		public Integer OrigemCotaPrecision() {
			return 0;
		}

		public String OrigemCotaDefault() {

			return "";

		}

		public String OrigemCotaComment() {

			return "";

		}

		public String OrigemCotaPattern() {

			return "";

		}

		public String OrigemCotaOriginalDbColumnName() {

			return "OrigemCota";

		}

		public Integer OrigemCanc;

		public Integer getOrigemCanc() {
			return this.OrigemCanc;
		}

		public Boolean OrigemCancIsNullable() {
			return true;
		}

		public Boolean OrigemCancIsKey() {
			return false;
		}

		public Integer OrigemCancLength() {
			return 10;
		}

		public Integer OrigemCancPrecision() {
			return 0;
		}

		public String OrigemCancDefault() {

			return "";

		}

		public String OrigemCancComment() {

			return "";

		}

		public String OrigemCancPattern() {

			return "";

		}

		public String OrigemCancOriginalDbColumnName() {

			return "OrigemCanc";

		}

		public Integer Incsug;

		public Integer getIncsug() {
			return this.Incsug;
		}

		public Boolean IncsugIsNullable() {
			return true;
		}

		public Boolean IncsugIsKey() {
			return false;
		}

		public Integer IncsugLength() {
			return 10;
		}

		public Integer IncsugPrecision() {
			return 0;
		}

		public String IncsugDefault() {

			return "";

		}

		public String IncsugComment() {

			return "";

		}

		public String IncsugPattern() {

			return "";

		}

		public String IncsugOriginalDbColumnName() {

			return "Incsug";

		}

		public Integer LjIdent;

		public Integer getLjIdent() {
			return this.LjIdent;
		}

		public Boolean LjIdentIsNullable() {
			return true;
		}

		public Boolean LjIdentIsKey() {
			return false;
		}

		public Integer LjIdentLength() {
			return 10;
		}

		public Integer LjIdentPrecision() {
			return 0;
		}

		public String LjIdentDefault() {

			return "";

		}

		public String LjIdentComment() {

			return "";

		}

		public String LjIdentPattern() {

			return "";

		}

		public String LjIdentOriginalDbColumnName() {

			return "LjIdent";

		}

		public String CondFreteIt;

		public String getCondFreteIt() {
			return this.CondFreteIt;
		}

		public Boolean CondFreteItIsNullable() {
			return true;
		}

		public Boolean CondFreteItIsKey() {
			return false;
		}

		public Integer CondFreteItLength() {
			return 4;
		}

		public Integer CondFreteItPrecision() {
			return 0;
		}

		public String CondFreteItDefault() {

			return null;

		}

		public String CondFreteItComment() {

			return "";

		}

		public String CondFreteItPattern() {

			return "";

		}

		public String CondFreteItOriginalDbColumnName() {

			return "CondFreteIt";

		}

		public Double DesctotalCalc;

		public Double getDesctotalCalc() {
			return this.DesctotalCalc;
		}

		public Boolean DesctotalCalcIsNullable() {
			return true;
		}

		public Boolean DesctotalCalcIsKey() {
			return false;
		}

		public Integer DesctotalCalcLength() {
			return 15;
		}

		public Integer DesctotalCalcPrecision() {
			return 0;
		}

		public String DesctotalCalcDefault() {

			return "";

		}

		public String DesctotalCalcComment() {

			return "";

		}

		public String DesctotalCalcPattern() {

			return "";

		}

		public String DesctotalCalcOriginalDbColumnName() {

			return "DesctotalCalc";

		}

		public String IdPedAuto;

		public String getIdPedAuto() {
			return this.IdPedAuto;
		}

		public Boolean IdPedAutoIsNullable() {
			return true;
		}

		public Boolean IdPedAutoIsKey() {
			return false;
		}

		public Integer IdPedAutoLength() {
			return 50;
		}

		public Integer IdPedAutoPrecision() {
			return 0;
		}

		public String IdPedAutoDefault() {

			return null;

		}

		public String IdPedAutoComment() {

			return "";

		}

		public String IdPedAutoPattern() {

			return "";

		}

		public String IdPedAutoOriginalDbColumnName() {

			return "IdPedAuto";

		}

		public String LoteEstoque;

		public String getLoteEstoque() {
			return this.LoteEstoque;
		}

		public Boolean LoteEstoqueIsNullable() {
			return true;
		}

		public Boolean LoteEstoqueIsKey() {
			return false;
		}

		public Integer LoteEstoqueLength() {
			return 50;
		}

		public Integer LoteEstoquePrecision() {
			return 0;
		}

		public String LoteEstoqueDefault() {

			return null;

		}

		public String LoteEstoqueComment() {

			return "";

		}

		public String LoteEstoquePattern() {

			return "";

		}

		public String LoteEstoqueOriginalDbColumnName() {

			return "LoteEstoque";

		}

		public Double DesctotalAplicado;

		public Double getDesctotalAplicado() {
			return this.DesctotalAplicado;
		}

		public Boolean DesctotalAplicadoIsNullable() {
			return true;
		}

		public Boolean DesctotalAplicadoIsKey() {
			return false;
		}

		public Integer DesctotalAplicadoLength() {
			return 15;
		}

		public Integer DesctotalAplicadoPrecision() {
			return 0;
		}

		public String DesctotalAplicadoDefault() {

			return "";

		}

		public String DesctotalAplicadoComment() {

			return "";

		}

		public String DesctotalAplicadoPattern() {

			return "";

		}

		public String DesctotalAplicadoOriginalDbColumnName() {

			return "DesctotalAplicado";

		}

		public Integer SeqOrdemCompra;

		public Integer getSeqOrdemCompra() {
			return this.SeqOrdemCompra;
		}

		public Boolean SeqOrdemCompraIsNullable() {
			return true;
		}

		public Boolean SeqOrdemCompraIsKey() {
			return false;
		}

		public Integer SeqOrdemCompraLength() {
			return 10;
		}

		public Integer SeqOrdemCompraPrecision() {
			return 0;
		}

		public String SeqOrdemCompraDefault() {

			return "";

		}

		public String SeqOrdemCompraComment() {

			return "";

		}

		public String SeqOrdemCompraPattern() {

			return "";

		}

		public String SeqOrdemCompraOriginalDbColumnName() {

			return "SeqOrdemCompra";

		}

		public String SeqordCompra;

		public String getSeqordCompra() {
			return this.SeqordCompra;
		}

		public Boolean SeqordCompraIsNullable() {
			return true;
		}

		public Boolean SeqordCompraIsKey() {
			return false;
		}

		public Integer SeqordCompraLength() {
			return 30;
		}

		public Integer SeqordCompraPrecision() {
			return 0;
		}

		public String SeqordCompraDefault() {

			return null;

		}

		public String SeqordCompraComment() {

			return "";

		}

		public String SeqordCompraPattern() {

			return "";

		}

		public String SeqordCompraOriginalDbColumnName() {

			return "SeqordCompra";

		}

		public String Ordcomest;

		public String getOrdcomest() {
			return this.Ordcomest;
		}

		public Boolean OrdcomestIsNullable() {
			return true;
		}

		public Boolean OrdcomestIsKey() {
			return false;
		}

		public Integer OrdcomestLength() {
			return 255;
		}

		public Integer OrdcomestPrecision() {
			return 0;
		}

		public String OrdcomestDefault() {

			return null;

		}

		public String OrdcomestComment() {

			return "";

		}

		public String OrdcomestPattern() {

			return "";

		}

		public String OrdcomestOriginalDbColumnName() {

			return "Ordcomest";

		}

		public Double ValorICMSFrete;

		public Double getValorICMSFrete() {
			return this.ValorICMSFrete;
		}

		public Boolean ValorICMSFreteIsNullable() {
			return true;
		}

		public Boolean ValorICMSFreteIsKey() {
			return false;
		}

		public Integer ValorICMSFreteLength() {
			return 15;
		}

		public Integer ValorICMSFretePrecision() {
			return 0;
		}

		public String ValorICMSFreteDefault() {

			return "";

		}

		public String ValorICMSFreteComment() {

			return "";

		}

		public String ValorICMSFretePattern() {

			return "";

		}

		public String ValorICMSFreteOriginalDbColumnName() {

			return "ValorICMSFrete";

		}

		public Integer Campo1;

		public Integer getCampo1() {
			return this.Campo1;
		}

		public Boolean Campo1IsNullable() {
			return true;
		}

		public Boolean Campo1IsKey() {
			return false;
		}

		public Integer Campo1Length() {
			return 10;
		}

		public Integer Campo1Precision() {
			return 0;
		}

		public String Campo1Default() {

			return "";

		}

		public String Campo1Comment() {

			return "";

		}

		public String Campo1Pattern() {

			return "";

		}

		public String Campo1OriginalDbColumnName() {

			return "Campo1";

		}

		public String CentroCusto;

		public String getCentroCusto() {
			return this.CentroCusto;
		}

		public Boolean CentroCustoIsNullable() {
			return true;
		}

		public Boolean CentroCustoIsKey() {
			return false;
		}

		public Integer CentroCustoLength() {
			return 30;
		}

		public Integer CentroCustoPrecision() {
			return 0;
		}

		public String CentroCustoDefault() {

			return null;

		}

		public String CentroCustoComment() {

			return "";

		}

		public String CentroCustoPattern() {

			return "";

		}

		public String CentroCustoOriginalDbColumnName() {

			return "CentroCusto";

		}

		public Integer BloqDesc;

		public Integer getBloqDesc() {
			return this.BloqDesc;
		}

		public Boolean BloqDescIsNullable() {
			return true;
		}

		public Boolean BloqDescIsKey() {
			return false;
		}

		public Integer BloqDescLength() {
			return 10;
		}

		public Integer BloqDescPrecision() {
			return 0;
		}

		public String BloqDescDefault() {

			return "";

		}

		public String BloqDescComment() {

			return "";

		}

		public String BloqDescPattern() {

			return "";

		}

		public String BloqDescOriginalDbColumnName() {

			return "BloqDesc";

		}

		public Double QtdeDescBonif;

		public Double getQtdeDescBonif() {
			return this.QtdeDescBonif;
		}

		public Boolean QtdeDescBonifIsNullable() {
			return true;
		}

		public Boolean QtdeDescBonifIsKey() {
			return false;
		}

		public Integer QtdeDescBonifLength() {
			return 15;
		}

		public Integer QtdeDescBonifPrecision() {
			return 0;
		}

		public String QtdeDescBonifDefault() {

			return "";

		}

		public String QtdeDescBonifComment() {

			return "";

		}

		public String QtdeDescBonifPattern() {

			return "";

		}

		public String QtdeDescBonifOriginalDbColumnName() {

			return "QtdeDescBonif";

		}

		public Double PercDescBonif;

		public Double getPercDescBonif() {
			return this.PercDescBonif;
		}

		public Boolean PercDescBonifIsNullable() {
			return true;
		}

		public Boolean PercDescBonifIsKey() {
			return false;
		}

		public Integer PercDescBonifLength() {
			return 15;
		}

		public Integer PercDescBonifPrecision() {
			return 0;
		}

		public String PercDescBonifDefault() {

			return "";

		}

		public String PercDescBonifComment() {

			return "";

		}

		public String PercDescBonifPattern() {

			return "";

		}

		public String PercDescBonifOriginalDbColumnName() {

			return "PercDescBonif";

		}

		public Double ValorDesccUnit;

		public Double getValorDesccUnit() {
			return this.ValorDesccUnit;
		}

		public Boolean ValorDesccUnitIsNullable() {
			return true;
		}

		public Boolean ValorDesccUnitIsKey() {
			return false;
		}

		public Integer ValorDesccUnitLength() {
			return 15;
		}

		public Integer ValorDesccUnitPrecision() {
			return 0;
		}

		public String ValorDesccUnitDefault() {

			return "";

		}

		public String ValorDesccUnitComment() {

			return "";

		}

		public String ValorDesccUnitPattern() {

			return "";

		}

		public String ValorDesccUnitOriginalDbColumnName() {

			return "ValorDesccUnit";

		}

		public Integer SeqCota;

		public Integer getSeqCota() {
			return this.SeqCota;
		}

		public Boolean SeqCotaIsNullable() {
			return true;
		}

		public Boolean SeqCotaIsKey() {
			return false;
		}

		public Integer SeqCotaLength() {
			return 10;
		}

		public Integer SeqCotaPrecision() {
			return 0;
		}

		public String SeqCotaDefault() {

			return "";

		}

		public String SeqCotaComment() {

			return "";

		}

		public String SeqCotaPattern() {

			return "";

		}

		public String SeqCotaOriginalDbColumnName() {

			return "SeqCota";

		}

		public Integer SeqCotaOriginal;

		public Integer getSeqCotaOriginal() {
			return this.SeqCotaOriginal;
		}

		public Boolean SeqCotaOriginalIsNullable() {
			return true;
		}

		public Boolean SeqCotaOriginalIsKey() {
			return false;
		}

		public Integer SeqCotaOriginalLength() {
			return 10;
		}

		public Integer SeqCotaOriginalPrecision() {
			return 0;
		}

		public String SeqCotaOriginalDefault() {

			return "";

		}

		public String SeqCotaOriginalComment() {

			return "";

		}

		public String SeqCotaOriginalPattern() {

			return "";

		}

		public String SeqCotaOriginalOriginalDbColumnName() {

			return "SeqCotaOriginal";

		}

		public java.util.Date DtRegraCorte;

		public java.util.Date getDtRegraCorte() {
			return this.DtRegraCorte;
		}

		public Boolean DtRegraCorteIsNullable() {
			return true;
		}

		public Boolean DtRegraCorteIsKey() {
			return false;
		}

		public Integer DtRegraCorteLength() {
			return 23;
		}

		public Integer DtRegraCortePrecision() {
			return 3;
		}

		public String DtRegraCorteDefault() {

			return null;

		}

		public String DtRegraCorteComment() {

			return "";

		}

		public String DtRegraCortePattern() {

			return "dd-MM-yyyy";

		}

		public String DtRegraCorteOriginalDbColumnName() {

			return "DtRegraCorte";

		}

		public Integer CompoeMargem;

		public Integer getCompoeMargem() {
			return this.CompoeMargem;
		}

		public Boolean CompoeMargemIsNullable() {
			return true;
		}

		public Boolean CompoeMargemIsKey() {
			return false;
		}

		public Integer CompoeMargemLength() {
			return 10;
		}

		public Integer CompoeMargemPrecision() {
			return 0;
		}

		public String CompoeMargemDefault() {

			return "";

		}

		public String CompoeMargemComment() {

			return "";

		}

		public String CompoeMargemPattern() {

			return "";

		}

		public String CompoeMargemOriginalDbColumnName() {

			return "CompoeMargem";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

				result = prime * result + ((this.CodigoProduto == null) ? 0 : this.CodigoProduto.hashCode());

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
			final row1Struct other = (row1Struct) obj;

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			if (this.CodigoProduto == null) {
				if (other.CodigoProduto != null)
					return false;

			} else if (!this.CodigoProduto.equals(other.CodigoProduto))

				return false;

			return true;
		}

		public void copyDataTo(row1Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.QuantidadeProduto = this.QuantidadeProduto;
			other.QuantidadeAtendida = this.QuantidadeAtendida;
			other.QuantidadeCancelada = this.QuantidadeCancelada;
			other.PrecoUnitario = this.PrecoUnitario;
			other.PrecoLiquido = this.PrecoLiquido;
			other.Desctop = this.Desctop;
			other.DesctoIcm = this.DesctoIcm;
			other.PrevEntr = this.PrevEntr;
			other.AtdData = this.AtdData;
			other.AtdQtde = this.AtdQtde;
			other.AtdDcto = this.AtdDcto;
			other.AtdData1 = this.AtdData1;
			other.AtdQtde1 = this.AtdQtde1;
			other.AtdDcto1 = this.AtdDcto1;
			other.AtdData2 = this.AtdData2;
			other.AtdQtde2 = this.AtdQtde2;
			other.AtdDcto2 = this.AtdDcto2;
			other.SituacaoPedido = this.SituacaoPedido;
			other.Ipi = this.Ipi;
			other.Dctoprom = this.Dctoprom;
			other.EconVlr = this.EconVlr;
			other.EconCalc = this.EconCalc;
			other.EconDcto = this.EconDcto;
			other.PrecoMin = this.PrecoMin;
			other.Tipo = this.Tipo;
			other.Dctovda = this.Dctovda;
			other.TipoPreco = this.TipoPreco;
			other.EstVlr = this.EstVlr;
			other.Acrescimo = this.Acrescimo;
			other.Lpreco = this.Lpreco;
			other.Grade = this.Grade;
			other.TpGrade = this.TpGrade;
			other.CodEtiq = this.CodEtiq;
			other.Embalagem = this.Embalagem;
			other.MotCanc = this.MotCanc;
			other.PercComis = this.PercComis;
			other.PrdCfgCar = this.PrdCfgCar;
			other.Observacao = this.Observacao;
			other.BonifEcon = this.BonifEcon;
			other.PcomisExt = this.PcomisExt;
			other.AplicDcto = this.AplicDcto;
			other.Operacao = this.Operacao;
			other.QtdeRes = this.QtdeRes;
			other.Bloqsug = this.Bloqsug;
			other.Dctoinc = this.Dctoinc;
			other.VlrIpi = this.VlrIpi;
			other.SeqGrd = this.SeqGrd;
			other.SeqErp = this.SeqErp;
			other.PrevCalc = this.PrevCalc;
			other.Almoxarif = this.Almoxarif;
			other.ComisGer = this.ComisGer;
			other.CredProp = this.CredProp;
			other.NroEnvio = this.NroEnvio;
			other.Chave = this.Chave;
			other.ItemConf = this.ItemConf;
			other.CvdaDcti = this.CvdaDcti;
			other.ObsNota = this.ObsNota;
			other.ObsExped = this.ObsExped;
			other.ObsSepar = this.ObsSepar;
			other.VlrSubst = this.VlrSubst;
			other.DesctoGer = this.DesctoGer;
			other.VlrFreTit = this.VlrFreTit;
			other.MotTroca = this.MotTroca;
			other.VeconCig = this.VeconCig;
			other.CustoMp = this.CustoMp;
			other.PliqAlt = this.PliqAlt;
			other.CodPrMap = this.CodPrMap;
			other.CodCvdAg = this.CodCvdAg;
			other.VuIcms = this.VuIcms;
			other.VuPis = this.VuPis;
			other.VuCofins = this.VuCofins;
			other.Altdtprev = this.Altdtprev;
			other.QtdeAloc = this.QtdeAloc;
			other.RestrBonif = this.RestrBonif;
			other.CustoMo = this.CustoMo;
			other.ItControl = this.ItControl;
			other.ItPermBon = this.ItPermBon;
			other.QtdPermBon = this.QtdPermBon;
			other.VlrPermBon = this.VlrPermBon;
			other.Itemfemb = this.Itemfemb;
			other.Dtprevento = this.Dtprevento;
			other.Cfretit = this.Cfretit;
			other.SldVerba = this.SldVerba;
			other.VlrBonif = this.VlrBonif;
			other.Bonif = this.Bonif;
			other.Tpproduto = this.Tpproduto;
			other.Backord = this.Backord;
			other.Markup = this.Markup;
			other.QtdeBkor = this.QtdeBkor;
			other.Prcpromoc = this.Prcpromoc;
			other.Sitcorp1 = this.Sitcorp1;
			other.Sitcorp2 = this.Sitcorp2;
			other.LibMots = this.LibMots;
			other.Dctoboleto = this.Dctoboleto;
			other.MPRISeq = this.MPRISeq;
			other.Dctomax = this.Dctomax;
			other.Comprim = this.Comprim;
			other.QtdePeca = this.QtdePeca;
			other.ProdRes = this.ProdRes;
			other.EmpEstoq = this.EmpEstoq;
			other.PrevProduc = this.PrevProduc;
			other.PrunitOri = this.PrunitOri;
			other.PrliqOri = this.PrliqOri;
			other.Mva = this.Mva;
			other.ICMSOrig = this.ICMSOrig;
			other.ICMSDest = this.ICMSDest;
			other.Float1 = this.Float1;
			other.Float2 = this.Float2;
			other.DataAlter = this.DataAlter;
			other.SeqOrig = this.SeqOrig;
			other.PrecoNorm = this.PrecoNorm;
			other.Tipost = this.Tipost;
			other.CodProdcli = this.CodProdcli;
			other.VlrDescarga = this.VlrDescarga;
			other.IcmsDscto = this.IcmsDscto;
			other.EmpSplit = this.EmpSplit;
			other.Status = this.Status;
			other.Obrigatorio = this.Obrigatorio;
			other.PrecoFirme = this.PrecoFirme;
			other.OrdCompra = this.OrdCompra;
			other.DesctoFiscal = this.DesctoFiscal;
			other.PercMlucr = this.PercMlucr;
			other.VlrDescargaCalc = this.VlrDescargaCalc;
			other.VlrFreTitCalc = this.VlrFreTitCalc;
			other.VuComissao = this.VuComissao;
			other.VuDebcli = this.VuDebcli;
			other.Data1Original = this.Data1Original;
			other.Visao = this.Visao;
			other.Periodo = this.Periodo;
			other.BaseIcms = this.BaseIcms;
			other.BaseSt = this.BaseSt;
			other.CondPgto = this.CondPgto;
			other.UsuCanc = this.UsuCanc;
			other.Resrede = this.Resrede;
			other.DocBloq = this.DocBloq;
			other.Regracomis = this.Regracomis;
			other.BaseIcmsRed = this.BaseIcmsRed;
			other.IcmsSemRed = this.IcmsSemRed;
			other.Obs1 = this.Obs1;
			other.Obs2 = this.Obs2;
			other.CodEmbal = this.CodEmbal;
			other.CondPgtoCalc = this.CondPgtoCalc;
			other.CodigoMix = this.CodigoMix;
			other.QtdeMix = this.QtdeMix;
			other.Lote = this.Lote;
			other.VlrDesctoFiscal = this.VlrDesctoFiscal;
			other.Tamanho = this.Tamanho;
			other.BloqVerba = this.BloqVerba;
			other.VlrFreTitRateio = this.VlrFreTitRateio;
			other.VlrFreTitRateioCalc = this.VlrFreTitRateioCalc;
			other.VisaoCota = this.VisaoCota;
			other.PeriodoCota = this.PeriodoCota;
			other.VisaoCotaOriginal = this.VisaoCotaOriginal;
			other.PeriodoCotaOriginal = this.PeriodoCotaOriginal;
			other.RegraEmpItem = this.RegraEmpItem;
			other.LjPeca = this.LjPeca;
			other.LjTamanho = this.LjTamanho;
			other.LjCarga = this.LjCarga;
			other.LjMedidaA = this.LjMedidaA;
			other.LjVao = this.LjVao;
			other.LjAngulo = this.LjAngulo;
			other.LjMedidaB = this.LjMedidaB;
			other.LjInicioViga = this.LjInicioViga;
			other.LjAba = this.LjAba;
			other.LjAbaA = this.LjAbaA;
			other.LjAbaB = this.LjAbaB;
			other.Bloqluc = this.Bloqluc;
			other.OrigemCota = this.OrigemCota;
			other.OrigemCanc = this.OrigemCanc;
			other.Incsug = this.Incsug;
			other.LjIdent = this.LjIdent;
			other.CondFreteIt = this.CondFreteIt;
			other.DesctotalCalc = this.DesctotalCalc;
			other.IdPedAuto = this.IdPedAuto;
			other.LoteEstoque = this.LoteEstoque;
			other.DesctotalAplicado = this.DesctotalAplicado;
			other.SeqOrdemCompra = this.SeqOrdemCompra;
			other.SeqordCompra = this.SeqordCompra;
			other.Ordcomest = this.Ordcomest;
			other.ValorICMSFrete = this.ValorICMSFrete;
			other.Campo1 = this.Campo1;
			other.CentroCusto = this.CentroCusto;
			other.BloqDesc = this.BloqDesc;
			other.QtdeDescBonif = this.QtdeDescBonif;
			other.PercDescBonif = this.PercDescBonif;
			other.ValorDesccUnit = this.ValorDesccUnit;
			other.SeqCota = this.SeqCota;
			other.SeqCotaOriginal = this.SeqCotaOriginal;
			other.DtRegraCorte = this.DtRegraCorte;
			other.CompoeMargem = this.CompoeMargem;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.CodigoProduto = this.CodigoProduto;

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
				if (length > commonByteArray_HYDRONORTH_Pedidos_mercanet.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Pedidos_mercanet.length == 0) {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Pedidos_mercanet.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Pedidos_mercanet.length == 0) {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Pedidos_mercanet = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Pedidos_mercanet, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Pedidos_mercanet) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					this.SequenciaItem = readInteger(dis);

					this.CodigoProduto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeProduto = null;
					} else {
						this.QuantidadeProduto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeAtendida = null;
					} else {
						this.QuantidadeAtendida = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeCancelada = null;
					} else {
						this.QuantidadeCancelada = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoLiquido = null;
					} else {
						this.PrecoLiquido = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Desctop = null;
					} else {
						this.Desctop = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoIcm = null;
					} else {
						this.DesctoIcm = dis.readDouble();
					}

					this.PrevEntr = readDate(dis);

					this.AtdData = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde = null;
					} else {
						this.AtdQtde = dis.readFloat();
					}

					this.AtdDcto = readString(dis);

					this.AtdData1 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde1 = null;
					} else {
						this.AtdQtde1 = dis.readFloat();
					}

					this.AtdDcto1 = readString(dis);

					this.AtdData2 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde2 = null;
					} else {
						this.AtdQtde2 = dis.readFloat();
					}

					this.AtdDcto2 = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Ipi = null;
					} else {
						this.Ipi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Dctoprom = null;
					} else {
						this.Dctoprom = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconVlr = null;
					} else {
						this.EconVlr = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconCalc = null;
					} else {
						this.EconCalc = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconDcto = null;
					} else {
						this.EconDcto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoMin = null;
					} else {
						this.PrecoMin = dis.readFloat();
					}

					this.Tipo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctovda = null;
					} else {
						this.Dctovda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TipoPreco = null;
					} else {
						this.TipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EstVlr = null;
					} else {
						this.EstVlr = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Acrescimo = null;
					} else {
						this.Acrescimo = dis.readDouble();
					}

					this.Lpreco = readString(dis);

					this.Grade = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TpGrade = null;
					} else {
						this.TpGrade = dis.readShort();
					}

					this.CodEtiq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Embalagem = null;
					} else {
						this.Embalagem = dis.readDouble();
					}

					this.MotCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PercComis = null;
					} else {
						this.PercComis = dis.readFloat();
					}

					this.PrdCfgCar = readString(dis);

					this.Observacao = readString(dis);

					this.BonifEcon = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PcomisExt = null;
					} else {
						this.PcomisExt = dis.readDouble();
					}

					this.AplicDcto = readString(dis);

					this.Operacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeRes = null;
					} else {
						this.QtdeRes = dis.readDouble();
					}

					this.Bloqsug = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoinc = null;
					} else {
						this.Dctoinc = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrIpi = null;
					} else {
						this.VlrIpi = dis.readDouble();
					}

					this.SeqGrd = readString(dis);

					this.SeqErp = readInteger(dis);

					this.PrevCalc = readDate(dis);

					this.Almoxarif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComisGer = null;
					} else {
						this.ComisGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CredProp = null;
					} else {
						this.CredProp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NroEnvio = null;
					} else {
						this.NroEnvio = dis.readDouble();
					}

					this.Chave = readString(dis);

					this.ItemConf = readString(dis);

					this.CvdaDcti = readString(dis);

					this.ObsNota = readString(dis);

					this.ObsExped = readString(dis);

					this.ObsSepar = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrSubst = null;
					} else {
						this.VlrSubst = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoGer = null;
					} else {
						this.DesctoGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTit = null;
					} else {
						this.VlrFreTit = dis.readDouble();
					}

					this.MotTroca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VeconCig = null;
					} else {
						this.VeconCig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CustoMp = null;
					} else {
						this.CustoMp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PliqAlt = null;
					} else {
						this.PliqAlt = dis.readShort();
					}

					this.CodPrMap = readString(dis);

					this.CodCvdAg = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VuIcms = null;
					} else {
						this.VuIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuPis = null;
					} else {
						this.VuPis = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuCofins = null;
					} else {
						this.VuCofins = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Altdtprev = null;
					} else {
						this.Altdtprev = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeAloc = null;
					} else {
						this.QtdeAloc = dis.readDouble();
					}

					this.RestrBonif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoMo = null;
					} else {
						this.CustoMo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItControl = null;
					} else {
						this.ItControl = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItPermBon = null;
					} else {
						this.ItPermBon = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdPermBon = null;
					} else {
						this.QtdPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrPermBon = null;
					} else {
						this.VlrPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Itemfemb = null;
					} else {
						this.Itemfemb = dis.readShort();
					}

					this.Dtprevento = readDate(dis);

					this.Cfretit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SldVerba = null;
					} else {
						this.SldVerba = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrBonif = null;
					} else {
						this.VlrBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Bonif = null;
					} else {
						this.Bonif = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tpproduto = null;
					} else {
						this.Tpproduto = dis.readShort();
					}

					this.Backord = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Markup = null;
					} else {
						this.Markup = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeBkor = null;
					} else {
						this.QtdeBkor = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prcpromoc = null;
					} else {
						this.Prcpromoc = dis.readDouble();
					}

					this.Sitcorp1 = readString(dis);

					this.Sitcorp2 = readString(dis);

					this.LibMots = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoboleto = null;
					} else {
						this.Dctoboleto = dis.readDouble();
					}

					this.MPRISeq = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctomax = null;
					} else {
						this.Dctomax = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comprim = null;
					} else {
						this.Comprim = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdePeca = null;
					} else {
						this.QtdePeca = dis.readDouble();
					}

					this.ProdRes = readString(dis);

					this.EmpEstoq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrevProduc = null;
					} else {
						this.PrevProduc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrunitOri = null;
					} else {
						this.PrunitOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrliqOri = null;
					} else {
						this.PrliqOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Mva = null;
					} else {
						this.Mva = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSOrig = null;
					} else {
						this.ICMSOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDest = null;
					} else {
						this.ICMSDest = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float1 = null;
					} else {
						this.Float1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float2 = null;
					} else {
						this.Float2 = dis.readDouble();
					}

					this.DataAlter = readDate(dis);

					this.SeqOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrecoNorm = null;
					} else {
						this.PrecoNorm = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tipost = null;
					} else {
						this.Tipost = dis.readShort();
					}

					this.CodProdcli = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescarga = null;
					} else {
						this.VlrDescarga = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsDscto = null;
					} else {
						this.IcmsDscto = dis.readShort();
					}

					this.EmpSplit = readString(dis);

					this.Status = readInteger(dis);

					this.Obrigatorio = readInteger(dis);

					this.PrecoFirme = readInteger(dis);

					this.OrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctoFiscal = null;
					} else {
						this.DesctoFiscal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercMlucr = null;
					} else {
						this.PercMlucr = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescargaCalc = null;
					} else {
						this.VlrDescargaCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitCalc = null;
					} else {
						this.VlrFreTitCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuComissao = null;
					} else {
						this.VuComissao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuDebcli = null;
					} else {
						this.VuDebcli = dis.readDouble();
					}

					this.Data1Original = readDate(dis);

					this.Visao = readInteger(dis);

					this.Periodo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcms = null;
					} else {
						this.BaseIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseSt = null;
					} else {
						this.BaseSt = dis.readDouble();
					}

					this.CondPgto = readInteger(dis);

					this.UsuCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Resrede = null;
					} else {
						this.Resrede = dis.readDouble();
					}

					this.DocBloq = readString(dis);

					this.Regracomis = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcmsRed = null;
					} else {
						this.BaseIcmsRed = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsSemRed = null;
					} else {
						this.IcmsSemRed = dis.readDouble();
					}

					this.Obs1 = readString(dis);

					this.Obs2 = readString(dis);

					this.CodEmbal = readString(dis);

					this.CondPgtoCalc = readInteger(dis);

					this.CodigoMix = readInteger(dis);

					this.QtdeMix = readInteger(dis);

					this.Lote = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDesctoFiscal = null;
					} else {
						this.VlrDesctoFiscal = dis.readDouble();
					}

					this.Tamanho = readString(dis);

					this.BloqVerba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateio = null;
					} else {
						this.VlrFreTitRateio = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateioCalc = null;
					} else {
						this.VlrFreTitRateioCalc = dis.readDouble();
					}

					this.VisaoCota = readInteger(dis);

					this.PeriodoCota = readInteger(dis);

					this.VisaoCotaOriginal = readInteger(dis);

					this.PeriodoCotaOriginal = readInteger(dis);

					this.RegraEmpItem = readInteger(dis);

					this.LjPeca = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjTamanho = null;
					} else {
						this.LjTamanho = dis.readDouble();
					}

					this.LjCarga = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaA = null;
					} else {
						this.LjMedidaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjVao = null;
					} else {
						this.LjVao = dis.readDouble();
					}

					this.LjAngulo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaB = null;
					} else {
						this.LjMedidaB = dis.readDouble();
					}

					this.LjInicioViga = readInteger(dis);

					this.LjAba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaA = null;
					} else {
						this.LjAbaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaB = null;
					} else {
						this.LjAbaB = dis.readDouble();
					}

					this.Bloqluc = readInteger(dis);

					this.OrigemCota = readInteger(dis);

					this.OrigemCanc = readInteger(dis);

					this.Incsug = readInteger(dis);

					this.LjIdent = readInteger(dis);

					this.CondFreteIt = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalCalc = null;
					} else {
						this.DesctotalCalc = dis.readDouble();
					}

					this.IdPedAuto = readString(dis);

					this.LoteEstoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalAplicado = null;
					} else {
						this.DesctotalAplicado = dis.readDouble();
					}

					this.SeqOrdemCompra = readInteger(dis);

					this.SeqordCompra = readString(dis);

					this.Ordcomest = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMSFrete = null;
					} else {
						this.ValorICMSFrete = dis.readDouble();
					}

					this.Campo1 = readInteger(dis);

					this.CentroCusto = readString(dis);

					this.BloqDesc = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeDescBonif = null;
					} else {
						this.QtdeDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercDescBonif = null;
					} else {
						this.PercDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDesccUnit = null;
					} else {
						this.ValorDesccUnit = dis.readDouble();
					}

					this.SeqCota = readInteger(dis);

					this.SeqCotaOriginal = readInteger(dis);

					this.DtRegraCorte = readDate(dis);

					this.CompoeMargem = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Pedidos_mercanet) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					this.SequenciaItem = readInteger(dis);

					this.CodigoProduto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeProduto = null;
					} else {
						this.QuantidadeProduto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeAtendida = null;
					} else {
						this.QuantidadeAtendida = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeCancelada = null;
					} else {
						this.QuantidadeCancelada = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoLiquido = null;
					} else {
						this.PrecoLiquido = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Desctop = null;
					} else {
						this.Desctop = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoIcm = null;
					} else {
						this.DesctoIcm = dis.readDouble();
					}

					this.PrevEntr = readDate(dis);

					this.AtdData = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde = null;
					} else {
						this.AtdQtde = dis.readFloat();
					}

					this.AtdDcto = readString(dis);

					this.AtdData1 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde1 = null;
					} else {
						this.AtdQtde1 = dis.readFloat();
					}

					this.AtdDcto1 = readString(dis);

					this.AtdData2 = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AtdQtde2 = null;
					} else {
						this.AtdQtde2 = dis.readFloat();
					}

					this.AtdDcto2 = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Ipi = null;
					} else {
						this.Ipi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Dctoprom = null;
					} else {
						this.Dctoprom = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconVlr = null;
					} else {
						this.EconVlr = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconCalc = null;
					} else {
						this.EconCalc = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EconDcto = null;
					} else {
						this.EconDcto = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoMin = null;
					} else {
						this.PrecoMin = dis.readFloat();
					}

					this.Tipo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctovda = null;
					} else {
						this.Dctovda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TipoPreco = null;
					} else {
						this.TipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.EstVlr = null;
					} else {
						this.EstVlr = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Acrescimo = null;
					} else {
						this.Acrescimo = dis.readDouble();
					}

					this.Lpreco = readString(dis);

					this.Grade = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TpGrade = null;
					} else {
						this.TpGrade = dis.readShort();
					}

					this.CodEtiq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Embalagem = null;
					} else {
						this.Embalagem = dis.readDouble();
					}

					this.MotCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PercComis = null;
					} else {
						this.PercComis = dis.readFloat();
					}

					this.PrdCfgCar = readString(dis);

					this.Observacao = readString(dis);

					this.BonifEcon = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PcomisExt = null;
					} else {
						this.PcomisExt = dis.readDouble();
					}

					this.AplicDcto = readString(dis);

					this.Operacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeRes = null;
					} else {
						this.QtdeRes = dis.readDouble();
					}

					this.Bloqsug = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoinc = null;
					} else {
						this.Dctoinc = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrIpi = null;
					} else {
						this.VlrIpi = dis.readDouble();
					}

					this.SeqGrd = readString(dis);

					this.SeqErp = readInteger(dis);

					this.PrevCalc = readDate(dis);

					this.Almoxarif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComisGer = null;
					} else {
						this.ComisGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CredProp = null;
					} else {
						this.CredProp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NroEnvio = null;
					} else {
						this.NroEnvio = dis.readDouble();
					}

					this.Chave = readString(dis);

					this.ItemConf = readString(dis);

					this.CvdaDcti = readString(dis);

					this.ObsNota = readString(dis);

					this.ObsExped = readString(dis);

					this.ObsSepar = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrSubst = null;
					} else {
						this.VlrSubst = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DesctoGer = null;
					} else {
						this.DesctoGer = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTit = null;
					} else {
						this.VlrFreTit = dis.readDouble();
					}

					this.MotTroca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VeconCig = null;
					} else {
						this.VeconCig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.CustoMp = null;
					} else {
						this.CustoMp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PliqAlt = null;
					} else {
						this.PliqAlt = dis.readShort();
					}

					this.CodPrMap = readString(dis);

					this.CodCvdAg = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VuIcms = null;
					} else {
						this.VuIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuPis = null;
					} else {
						this.VuPis = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuCofins = null;
					} else {
						this.VuCofins = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Altdtprev = null;
					} else {
						this.Altdtprev = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeAloc = null;
					} else {
						this.QtdeAloc = dis.readDouble();
					}

					this.RestrBonif = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoMo = null;
					} else {
						this.CustoMo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItControl = null;
					} else {
						this.ItControl = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ItPermBon = null;
					} else {
						this.ItPermBon = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdPermBon = null;
					} else {
						this.QtdPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrPermBon = null;
					} else {
						this.VlrPermBon = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Itemfemb = null;
					} else {
						this.Itemfemb = dis.readShort();
					}

					this.Dtprevento = readDate(dis);

					this.Cfretit = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SldVerba = null;
					} else {
						this.SldVerba = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrBonif = null;
					} else {
						this.VlrBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Bonif = null;
					} else {
						this.Bonif = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tpproduto = null;
					} else {
						this.Tpproduto = dis.readShort();
					}

					this.Backord = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Markup = null;
					} else {
						this.Markup = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdeBkor = null;
					} else {
						this.QtdeBkor = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Prcpromoc = null;
					} else {
						this.Prcpromoc = dis.readDouble();
					}

					this.Sitcorp1 = readString(dis);

					this.Sitcorp2 = readString(dis);

					this.LibMots = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctoboleto = null;
					} else {
						this.Dctoboleto = dis.readDouble();
					}

					this.MPRISeq = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Dctomax = null;
					} else {
						this.Dctomax = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comprim = null;
					} else {
						this.Comprim = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QtdePeca = null;
					} else {
						this.QtdePeca = dis.readDouble();
					}

					this.ProdRes = readString(dis);

					this.EmpEstoq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrevProduc = null;
					} else {
						this.PrevProduc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrunitOri = null;
					} else {
						this.PrunitOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrliqOri = null;
					} else {
						this.PrliqOri = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Mva = null;
					} else {
						this.Mva = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSOrig = null;
					} else {
						this.ICMSOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDest = null;
					} else {
						this.ICMSDest = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float1 = null;
					} else {
						this.Float1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Float2 = null;
					} else {
						this.Float2 = dis.readDouble();
					}

					this.DataAlter = readDate(dis);

					this.SeqOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.PrecoNorm = null;
					} else {
						this.PrecoNorm = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Tipost = null;
					} else {
						this.Tipost = dis.readShort();
					}

					this.CodProdcli = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescarga = null;
					} else {
						this.VlrDescarga = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsDscto = null;
					} else {
						this.IcmsDscto = dis.readShort();
					}

					this.EmpSplit = readString(dis);

					this.Status = readInteger(dis);

					this.Obrigatorio = readInteger(dis);

					this.PrecoFirme = readInteger(dis);

					this.OrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctoFiscal = null;
					} else {
						this.DesctoFiscal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercMlucr = null;
					} else {
						this.PercMlucr = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrDescargaCalc = null;
					} else {
						this.VlrDescargaCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitCalc = null;
					} else {
						this.VlrFreTitCalc = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuComissao = null;
					} else {
						this.VuComissao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VuDebcli = null;
					} else {
						this.VuDebcli = dis.readDouble();
					}

					this.Data1Original = readDate(dis);

					this.Visao = readInteger(dis);

					this.Periodo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcms = null;
					} else {
						this.BaseIcms = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseSt = null;
					} else {
						this.BaseSt = dis.readDouble();
					}

					this.CondPgto = readInteger(dis);

					this.UsuCanc = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Resrede = null;
					} else {
						this.Resrede = dis.readDouble();
					}

					this.DocBloq = readString(dis);

					this.Regracomis = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseIcmsRed = null;
					} else {
						this.BaseIcmsRed = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IcmsSemRed = null;
					} else {
						this.IcmsSemRed = dis.readDouble();
					}

					this.Obs1 = readString(dis);

					this.Obs2 = readString(dis);

					this.CodEmbal = readString(dis);

					this.CondPgtoCalc = readInteger(dis);

					this.CodigoMix = readInteger(dis);

					this.QtdeMix = readInteger(dis);

					this.Lote = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrDesctoFiscal = null;
					} else {
						this.VlrDesctoFiscal = dis.readDouble();
					}

					this.Tamanho = readString(dis);

					this.BloqVerba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateio = null;
					} else {
						this.VlrFreTitRateio = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VlrFreTitRateioCalc = null;
					} else {
						this.VlrFreTitRateioCalc = dis.readDouble();
					}

					this.VisaoCota = readInteger(dis);

					this.PeriodoCota = readInteger(dis);

					this.VisaoCotaOriginal = readInteger(dis);

					this.PeriodoCotaOriginal = readInteger(dis);

					this.RegraEmpItem = readInteger(dis);

					this.LjPeca = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjTamanho = null;
					} else {
						this.LjTamanho = dis.readDouble();
					}

					this.LjCarga = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaA = null;
					} else {
						this.LjMedidaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjVao = null;
					} else {
						this.LjVao = dis.readDouble();
					}

					this.LjAngulo = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjMedidaB = null;
					} else {
						this.LjMedidaB = dis.readDouble();
					}

					this.LjInicioViga = readInteger(dis);

					this.LjAba = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaA = null;
					} else {
						this.LjAbaA = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.LjAbaB = null;
					} else {
						this.LjAbaB = dis.readDouble();
					}

					this.Bloqluc = readInteger(dis);

					this.OrigemCota = readInteger(dis);

					this.OrigemCanc = readInteger(dis);

					this.Incsug = readInteger(dis);

					this.LjIdent = readInteger(dis);

					this.CondFreteIt = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalCalc = null;
					} else {
						this.DesctotalCalc = dis.readDouble();
					}

					this.IdPedAuto = readString(dis);

					this.LoteEstoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DesctotalAplicado = null;
					} else {
						this.DesctotalAplicado = dis.readDouble();
					}

					this.SeqOrdemCompra = readInteger(dis);

					this.SeqordCompra = readString(dis);

					this.Ordcomest = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMSFrete = null;
					} else {
						this.ValorICMSFrete = dis.readDouble();
					}

					this.Campo1 = readInteger(dis);

					this.CentroCusto = readString(dis);

					this.BloqDesc = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QtdeDescBonif = null;
					} else {
						this.QtdeDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PercDescBonif = null;
					} else {
						this.PercDescBonif = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDesccUnit = null;
					} else {
						this.ValorDesccUnit = dis.readDouble();
					}

					this.SeqCota = readInteger(dis);

					this.SeqCotaOriginal = readInteger(dis);

					this.DtRegraCorte = readDate(dis);

					this.CompoeMargem = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// Float

				if (this.QuantidadeProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeProduto);
				}

				// Float

				if (this.QuantidadeAtendida == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeAtendida);
				}

				// Float

				if (this.QuantidadeCancelada == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeCancelada);
				}

				// Float

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoUnitario);
				}

				// Float

				if (this.PrecoLiquido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoLiquido);
				}

				// Float

				if (this.Desctop == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Desctop);
				}

				// Double

				if (this.DesctoIcm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoIcm);
				}

				// java.util.Date

				writeDate(this.PrevEntr, dos);

				// java.util.Date

				writeDate(this.AtdData, dos);

				// Float

				if (this.AtdQtde == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde);
				}

				// String

				writeString(this.AtdDcto, dos);

				// java.util.Date

				writeDate(this.AtdData1, dos);

				// Float

				if (this.AtdQtde1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde1);
				}

				// String

				writeString(this.AtdDcto1, dos);

				// java.util.Date

				writeDate(this.AtdData2, dos);

				// Float

				if (this.AtdQtde2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde2);
				}

				// String

				writeString(this.AtdDcto2, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// Double

				if (this.Ipi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Ipi);
				}

				// Float

				if (this.Dctoprom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoprom);
				}

				// Float

				if (this.EconVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconVlr);
				}

				// Short

				if (this.EconCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EconCalc);
				}

				// Float

				if (this.EconDcto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconDcto);
				}

				// Float

				if (this.PrecoMin == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoMin);
				}

				// String

				writeString(this.Tipo, dos);

				// Double

				if (this.Dctovda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctovda);
				}

				// Short

				if (this.TipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TipoPreco);
				}

				// Short

				if (this.EstVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EstVlr);
				}

				// Double

				if (this.Acrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Acrescimo);
				}

				// String

				writeString(this.Lpreco, dos);

				// String

				writeString(this.Grade, dos);

				// Short

				if (this.TpGrade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TpGrade);
				}

				// String

				writeString(this.CodEtiq, dos);

				// Double

				if (this.Embalagem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Embalagem);
				}

				// String

				writeString(this.MotCanc, dos);

				// Float

				if (this.PercComis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PercComis);
				}

				// String

				writeString(this.PrdCfgCar, dos);

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.BonifEcon, dos);

				// Double

				if (this.PcomisExt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PcomisExt);
				}

				// String

				writeString(this.AplicDcto, dos);

				// String

				writeString(this.Operacao, dos);

				// Double

				if (this.QtdeRes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeRes);
				}

				// String

				writeString(this.Bloqsug, dos);

				// Float

				if (this.Dctoinc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoinc);
				}

				// Double

				if (this.VlrIpi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrIpi);
				}

				// String

				writeString(this.SeqGrd, dos);

				// Integer

				writeInteger(this.SeqErp, dos);

				// java.util.Date

				writeDate(this.PrevCalc, dos);

				// String

				writeString(this.Almoxarif, dos);

				// Double

				if (this.ComisGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComisGer);
				}

				// Double

				if (this.CredProp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CredProp);
				}

				// Double

				if (this.NroEnvio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NroEnvio);
				}

				// String

				writeString(this.Chave, dos);

				// String

				writeString(this.ItemConf, dos);

				// String

				writeString(this.CvdaDcti, dos);

				// String

				writeString(this.ObsNota, dos);

				// String

				writeString(this.ObsExped, dos);

				// String

				writeString(this.ObsSepar, dos);

				// Double

				if (this.VlrSubst == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrSubst);
				}

				// Double

				if (this.DesctoGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoGer);
				}

				// Double

				if (this.VlrFreTit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTit);
				}

				// String

				writeString(this.MotTroca, dos);

				// Double

				if (this.VeconCig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VeconCig);
				}

				// Double

				if (this.CustoMp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMp);
				}

				// Short

				if (this.PliqAlt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.PliqAlt);
				}

				// String

				writeString(this.CodPrMap, dos);

				// String

				writeString(this.CodCvdAg, dos);

				// Double

				if (this.VuIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuIcms);
				}

				// Double

				if (this.VuPis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuPis);
				}

				// Double

				if (this.VuCofins == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuCofins);
				}

				// Short

				if (this.Altdtprev == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Altdtprev);
				}

				// Double

				if (this.QtdeAloc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeAloc);
				}

				// String

				writeString(this.RestrBonif, dos);

				// Double

				if (this.CustoMo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMo);
				}

				// Short

				if (this.ItControl == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItControl);
				}

				// Short

				if (this.ItPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItPermBon);
				}

				// Double

				if (this.QtdPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdPermBon);
				}

				// Double

				if (this.VlrPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrPermBon);
				}

				// Short

				if (this.Itemfemb == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Itemfemb);
				}

				// java.util.Date

				writeDate(this.Dtprevento, dos);

				// String

				writeString(this.Cfretit, dos);

				// Double

				if (this.SldVerba == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SldVerba);
				}

				// Double

				if (this.VlrBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrBonif);
				}

				// Short

				if (this.Bonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Bonif);
				}

				// Short

				if (this.Tpproduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tpproduto);
				}

				// String

				writeString(this.Backord, dos);

				// Double

				if (this.Markup == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Markup);
				}

				// Double

				if (this.QtdeBkor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeBkor);
				}

				// Double

				if (this.Prcpromoc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Prcpromoc);
				}

				// String

				writeString(this.Sitcorp1, dos);

				// String

				writeString(this.Sitcorp2, dos);

				// String

				writeString(this.LibMots, dos);

				// Double

				if (this.Dctoboleto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctoboleto);
				}

				// Integer

				writeInteger(this.MPRISeq, dos);

				// Double

				if (this.Dctomax == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctomax);
				}

				// Double

				if (this.Comprim == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comprim);
				}

				// Double

				if (this.QtdePeca == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdePeca);
				}

				// String

				writeString(this.ProdRes, dos);

				// String

				writeString(this.EmpEstoq, dos);

				// Double

				if (this.PrevProduc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrevProduc);
				}

				// Float

				if (this.PrunitOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrunitOri);
				}

				// Float

				if (this.PrliqOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrliqOri);
				}

				// Double

				if (this.Mva == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Mva);
				}

				// Double

				if (this.ICMSOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSOrig);
				}

				// Double

				if (this.ICMSDest == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDest);
				}

				// Double

				if (this.Float1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float1);
				}

				// Double

				if (this.Float2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float2);
				}

				// java.util.Date

				writeDate(this.DataAlter, dos);

				// String

				writeString(this.SeqOrig, dos);

				// Double

				if (this.PrecoNorm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoNorm);
				}

				// Short

				if (this.Tipost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tipost);
				}

				// String

				writeString(this.CodProdcli, dos);

				// Double

				if (this.VlrDescarga == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescarga);
				}

				// Short

				if (this.IcmsDscto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.IcmsDscto);
				}

				// String

				writeString(this.EmpSplit, dos);

				// Integer

				writeInteger(this.Status, dos);

				// Integer

				writeInteger(this.Obrigatorio, dos);

				// Integer

				writeInteger(this.PrecoFirme, dos);

				// String

				writeString(this.OrdCompra, dos);

				// Double

				if (this.DesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoFiscal);
				}

				// Double

				if (this.PercMlucr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercMlucr);
				}

				// Double

				if (this.VlrDescargaCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescargaCalc);
				}

				// Double

				if (this.VlrFreTitCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitCalc);
				}

				// Double

				if (this.VuComissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuComissao);
				}

				// Double

				if (this.VuDebcli == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuDebcli);
				}

				// java.util.Date

				writeDate(this.Data1Original, dos);

				// Integer

				writeInteger(this.Visao, dos);

				// Integer

				writeInteger(this.Periodo, dos);

				// Double

				if (this.BaseIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcms);
				}

				// Double

				if (this.BaseSt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseSt);
				}

				// Integer

				writeInteger(this.CondPgto, dos);

				// String

				writeString(this.UsuCanc, dos);

				// Double

				if (this.Resrede == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Resrede);
				}

				// String

				writeString(this.DocBloq, dos);

				// String

				writeString(this.Regracomis, dos);

				// Double

				if (this.BaseIcmsRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcmsRed);
				}

				// Double

				if (this.IcmsSemRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IcmsSemRed);
				}

				// String

				writeString(this.Obs1, dos);

				// String

				writeString(this.Obs2, dos);

				// String

				writeString(this.CodEmbal, dos);

				// Integer

				writeInteger(this.CondPgtoCalc, dos);

				// Integer

				writeInteger(this.CodigoMix, dos);

				// Integer

				writeInteger(this.QtdeMix, dos);

				// String

				writeString(this.Lote, dos);

				// Double

				if (this.VlrDesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDesctoFiscal);
				}

				// String

				writeString(this.Tamanho, dos);

				// Integer

				writeInteger(this.BloqVerba, dos);

				// Double

				if (this.VlrFreTitRateio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateio);
				}

				// Double

				if (this.VlrFreTitRateioCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateioCalc);
				}

				// Integer

				writeInteger(this.VisaoCota, dos);

				// Integer

				writeInteger(this.PeriodoCota, dos);

				// Integer

				writeInteger(this.VisaoCotaOriginal, dos);

				// Integer

				writeInteger(this.PeriodoCotaOriginal, dos);

				// Integer

				writeInteger(this.RegraEmpItem, dos);

				// Integer

				writeInteger(this.LjPeca, dos);

				// Double

				if (this.LjTamanho == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjTamanho);
				}

				// Integer

				writeInteger(this.LjCarga, dos);

				// Double

				if (this.LjMedidaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaA);
				}

				// Double

				if (this.LjVao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjVao);
				}

				// Integer

				writeInteger(this.LjAngulo, dos);

				// Double

				if (this.LjMedidaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaB);
				}

				// Integer

				writeInteger(this.LjInicioViga, dos);

				// Integer

				writeInteger(this.LjAba, dos);

				// Double

				if (this.LjAbaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaA);
				}

				// Double

				if (this.LjAbaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaB);
				}

				// Integer

				writeInteger(this.Bloqluc, dos);

				// Integer

				writeInteger(this.OrigemCota, dos);

				// Integer

				writeInteger(this.OrigemCanc, dos);

				// Integer

				writeInteger(this.Incsug, dos);

				// Integer

				writeInteger(this.LjIdent, dos);

				// String

				writeString(this.CondFreteIt, dos);

				// Double

				if (this.DesctotalCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalCalc);
				}

				// String

				writeString(this.IdPedAuto, dos);

				// String

				writeString(this.LoteEstoque, dos);

				// Double

				if (this.DesctotalAplicado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalAplicado);
				}

				// Integer

				writeInteger(this.SeqOrdemCompra, dos);

				// String

				writeString(this.SeqordCompra, dos);

				// String

				writeString(this.Ordcomest, dos);

				// Double

				if (this.ValorICMSFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMSFrete);
				}

				// Integer

				writeInteger(this.Campo1, dos);

				// String

				writeString(this.CentroCusto, dos);

				// Integer

				writeInteger(this.BloqDesc, dos);

				// Double

				if (this.QtdeDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeDescBonif);
				}

				// Double

				if (this.PercDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercDescBonif);
				}

				// Double

				if (this.ValorDesccUnit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDesccUnit);
				}

				// Integer

				writeInteger(this.SeqCota, dos);

				// Integer

				writeInteger(this.SeqCotaOriginal, dos);

				// java.util.Date

				writeDate(this.DtRegraCorte, dos);

				// Integer

				writeInteger(this.CompoeMargem, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Integer

				writeInteger(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// Float

				if (this.QuantidadeProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeProduto);
				}

				// Float

				if (this.QuantidadeAtendida == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeAtendida);
				}

				// Float

				if (this.QuantidadeCancelada == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeCancelada);
				}

				// Float

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoUnitario);
				}

				// Float

				if (this.PrecoLiquido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoLiquido);
				}

				// Float

				if (this.Desctop == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Desctop);
				}

				// Double

				if (this.DesctoIcm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoIcm);
				}

				// java.util.Date

				writeDate(this.PrevEntr, dos);

				// java.util.Date

				writeDate(this.AtdData, dos);

				// Float

				if (this.AtdQtde == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde);
				}

				// String

				writeString(this.AtdDcto, dos);

				// java.util.Date

				writeDate(this.AtdData1, dos);

				// Float

				if (this.AtdQtde1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde1);
				}

				// String

				writeString(this.AtdDcto1, dos);

				// java.util.Date

				writeDate(this.AtdData2, dos);

				// Float

				if (this.AtdQtde2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde2);
				}

				// String

				writeString(this.AtdDcto2, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// Double

				if (this.Ipi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Ipi);
				}

				// Float

				if (this.Dctoprom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoprom);
				}

				// Float

				if (this.EconVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconVlr);
				}

				// Short

				if (this.EconCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EconCalc);
				}

				// Float

				if (this.EconDcto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconDcto);
				}

				// Float

				if (this.PrecoMin == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoMin);
				}

				// String

				writeString(this.Tipo, dos);

				// Double

				if (this.Dctovda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctovda);
				}

				// Short

				if (this.TipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TipoPreco);
				}

				// Short

				if (this.EstVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EstVlr);
				}

				// Double

				if (this.Acrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Acrescimo);
				}

				// String

				writeString(this.Lpreco, dos);

				// String

				writeString(this.Grade, dos);

				// Short

				if (this.TpGrade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TpGrade);
				}

				// String

				writeString(this.CodEtiq, dos);

				// Double

				if (this.Embalagem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Embalagem);
				}

				// String

				writeString(this.MotCanc, dos);

				// Float

				if (this.PercComis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PercComis);
				}

				// String

				writeString(this.PrdCfgCar, dos);

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.BonifEcon, dos);

				// Double

				if (this.PcomisExt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PcomisExt);
				}

				// String

				writeString(this.AplicDcto, dos);

				// String

				writeString(this.Operacao, dos);

				// Double

				if (this.QtdeRes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeRes);
				}

				// String

				writeString(this.Bloqsug, dos);

				// Float

				if (this.Dctoinc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoinc);
				}

				// Double

				if (this.VlrIpi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrIpi);
				}

				// String

				writeString(this.SeqGrd, dos);

				// Integer

				writeInteger(this.SeqErp, dos);

				// java.util.Date

				writeDate(this.PrevCalc, dos);

				// String

				writeString(this.Almoxarif, dos);

				// Double

				if (this.ComisGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComisGer);
				}

				// Double

				if (this.CredProp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CredProp);
				}

				// Double

				if (this.NroEnvio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NroEnvio);
				}

				// String

				writeString(this.Chave, dos);

				// String

				writeString(this.ItemConf, dos);

				// String

				writeString(this.CvdaDcti, dos);

				// String

				writeString(this.ObsNota, dos);

				// String

				writeString(this.ObsExped, dos);

				// String

				writeString(this.ObsSepar, dos);

				// Double

				if (this.VlrSubst == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrSubst);
				}

				// Double

				if (this.DesctoGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoGer);
				}

				// Double

				if (this.VlrFreTit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTit);
				}

				// String

				writeString(this.MotTroca, dos);

				// Double

				if (this.VeconCig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VeconCig);
				}

				// Double

				if (this.CustoMp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMp);
				}

				// Short

				if (this.PliqAlt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.PliqAlt);
				}

				// String

				writeString(this.CodPrMap, dos);

				// String

				writeString(this.CodCvdAg, dos);

				// Double

				if (this.VuIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuIcms);
				}

				// Double

				if (this.VuPis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuPis);
				}

				// Double

				if (this.VuCofins == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuCofins);
				}

				// Short

				if (this.Altdtprev == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Altdtprev);
				}

				// Double

				if (this.QtdeAloc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeAloc);
				}

				// String

				writeString(this.RestrBonif, dos);

				// Double

				if (this.CustoMo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMo);
				}

				// Short

				if (this.ItControl == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItControl);
				}

				// Short

				if (this.ItPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItPermBon);
				}

				// Double

				if (this.QtdPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdPermBon);
				}

				// Double

				if (this.VlrPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrPermBon);
				}

				// Short

				if (this.Itemfemb == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Itemfemb);
				}

				// java.util.Date

				writeDate(this.Dtprevento, dos);

				// String

				writeString(this.Cfretit, dos);

				// Double

				if (this.SldVerba == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SldVerba);
				}

				// Double

				if (this.VlrBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrBonif);
				}

				// Short

				if (this.Bonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Bonif);
				}

				// Short

				if (this.Tpproduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tpproduto);
				}

				// String

				writeString(this.Backord, dos);

				// Double

				if (this.Markup == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Markup);
				}

				// Double

				if (this.QtdeBkor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeBkor);
				}

				// Double

				if (this.Prcpromoc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Prcpromoc);
				}

				// String

				writeString(this.Sitcorp1, dos);

				// String

				writeString(this.Sitcorp2, dos);

				// String

				writeString(this.LibMots, dos);

				// Double

				if (this.Dctoboleto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctoboleto);
				}

				// Integer

				writeInteger(this.MPRISeq, dos);

				// Double

				if (this.Dctomax == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctomax);
				}

				// Double

				if (this.Comprim == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comprim);
				}

				// Double

				if (this.QtdePeca == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdePeca);
				}

				// String

				writeString(this.ProdRes, dos);

				// String

				writeString(this.EmpEstoq, dos);

				// Double

				if (this.PrevProduc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrevProduc);
				}

				// Float

				if (this.PrunitOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrunitOri);
				}

				// Float

				if (this.PrliqOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrliqOri);
				}

				// Double

				if (this.Mva == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Mva);
				}

				// Double

				if (this.ICMSOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSOrig);
				}

				// Double

				if (this.ICMSDest == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDest);
				}

				// Double

				if (this.Float1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float1);
				}

				// Double

				if (this.Float2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float2);
				}

				// java.util.Date

				writeDate(this.DataAlter, dos);

				// String

				writeString(this.SeqOrig, dos);

				// Double

				if (this.PrecoNorm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoNorm);
				}

				// Short

				if (this.Tipost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tipost);
				}

				// String

				writeString(this.CodProdcli, dos);

				// Double

				if (this.VlrDescarga == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescarga);
				}

				// Short

				if (this.IcmsDscto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.IcmsDscto);
				}

				// String

				writeString(this.EmpSplit, dos);

				// Integer

				writeInteger(this.Status, dos);

				// Integer

				writeInteger(this.Obrigatorio, dos);

				// Integer

				writeInteger(this.PrecoFirme, dos);

				// String

				writeString(this.OrdCompra, dos);

				// Double

				if (this.DesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoFiscal);
				}

				// Double

				if (this.PercMlucr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercMlucr);
				}

				// Double

				if (this.VlrDescargaCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescargaCalc);
				}

				// Double

				if (this.VlrFreTitCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitCalc);
				}

				// Double

				if (this.VuComissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuComissao);
				}

				// Double

				if (this.VuDebcli == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuDebcli);
				}

				// java.util.Date

				writeDate(this.Data1Original, dos);

				// Integer

				writeInteger(this.Visao, dos);

				// Integer

				writeInteger(this.Periodo, dos);

				// Double

				if (this.BaseIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcms);
				}

				// Double

				if (this.BaseSt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseSt);
				}

				// Integer

				writeInteger(this.CondPgto, dos);

				// String

				writeString(this.UsuCanc, dos);

				// Double

				if (this.Resrede == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Resrede);
				}

				// String

				writeString(this.DocBloq, dos);

				// String

				writeString(this.Regracomis, dos);

				// Double

				if (this.BaseIcmsRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcmsRed);
				}

				// Double

				if (this.IcmsSemRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IcmsSemRed);
				}

				// String

				writeString(this.Obs1, dos);

				// String

				writeString(this.Obs2, dos);

				// String

				writeString(this.CodEmbal, dos);

				// Integer

				writeInteger(this.CondPgtoCalc, dos);

				// Integer

				writeInteger(this.CodigoMix, dos);

				// Integer

				writeInteger(this.QtdeMix, dos);

				// String

				writeString(this.Lote, dos);

				// Double

				if (this.VlrDesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDesctoFiscal);
				}

				// String

				writeString(this.Tamanho, dos);

				// Integer

				writeInteger(this.BloqVerba, dos);

				// Double

				if (this.VlrFreTitRateio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateio);
				}

				// Double

				if (this.VlrFreTitRateioCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateioCalc);
				}

				// Integer

				writeInteger(this.VisaoCota, dos);

				// Integer

				writeInteger(this.PeriodoCota, dos);

				// Integer

				writeInteger(this.VisaoCotaOriginal, dos);

				// Integer

				writeInteger(this.PeriodoCotaOriginal, dos);

				// Integer

				writeInteger(this.RegraEmpItem, dos);

				// Integer

				writeInteger(this.LjPeca, dos);

				// Double

				if (this.LjTamanho == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjTamanho);
				}

				// Integer

				writeInteger(this.LjCarga, dos);

				// Double

				if (this.LjMedidaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaA);
				}

				// Double

				if (this.LjVao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjVao);
				}

				// Integer

				writeInteger(this.LjAngulo, dos);

				// Double

				if (this.LjMedidaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaB);
				}

				// Integer

				writeInteger(this.LjInicioViga, dos);

				// Integer

				writeInteger(this.LjAba, dos);

				// Double

				if (this.LjAbaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaA);
				}

				// Double

				if (this.LjAbaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaB);
				}

				// Integer

				writeInteger(this.Bloqluc, dos);

				// Integer

				writeInteger(this.OrigemCota, dos);

				// Integer

				writeInteger(this.OrigemCanc, dos);

				// Integer

				writeInteger(this.Incsug, dos);

				// Integer

				writeInteger(this.LjIdent, dos);

				// String

				writeString(this.CondFreteIt, dos);

				// Double

				if (this.DesctotalCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalCalc);
				}

				// String

				writeString(this.IdPedAuto, dos);

				// String

				writeString(this.LoteEstoque, dos);

				// Double

				if (this.DesctotalAplicado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalAplicado);
				}

				// Integer

				writeInteger(this.SeqOrdemCompra, dos);

				// String

				writeString(this.SeqordCompra, dos);

				// String

				writeString(this.Ordcomest, dos);

				// Double

				if (this.ValorICMSFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMSFrete);
				}

				// Integer

				writeInteger(this.Campo1, dos);

				// String

				writeString(this.CentroCusto, dos);

				// Integer

				writeInteger(this.BloqDesc, dos);

				// Double

				if (this.QtdeDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeDescBonif);
				}

				// Double

				if (this.PercDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercDescBonif);
				}

				// Double

				if (this.ValorDesccUnit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDesccUnit);
				}

				// Integer

				writeInteger(this.SeqCota, dos);

				// Integer

				writeInteger(this.SeqCotaOriginal, dos);

				// java.util.Date

				writeDate(this.DtRegraCorte, dos);

				// Integer

				writeInteger(this.CompoeMargem, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",SequenciaItem=" + String.valueOf(SequenciaItem));
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",QuantidadeProduto=" + String.valueOf(QuantidadeProduto));
			sb.append(",QuantidadeAtendida=" + String.valueOf(QuantidadeAtendida));
			sb.append(",QuantidadeCancelada=" + String.valueOf(QuantidadeCancelada));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",PrecoLiquido=" + String.valueOf(PrecoLiquido));
			sb.append(",Desctop=" + String.valueOf(Desctop));
			sb.append(",DesctoIcm=" + String.valueOf(DesctoIcm));
			sb.append(",PrevEntr=" + String.valueOf(PrevEntr));
			sb.append(",AtdData=" + String.valueOf(AtdData));
			sb.append(",AtdQtde=" + String.valueOf(AtdQtde));
			sb.append(",AtdDcto=" + AtdDcto);
			sb.append(",AtdData1=" + String.valueOf(AtdData1));
			sb.append(",AtdQtde1=" + String.valueOf(AtdQtde1));
			sb.append(",AtdDcto1=" + AtdDcto1);
			sb.append(",AtdData2=" + String.valueOf(AtdData2));
			sb.append(",AtdQtde2=" + String.valueOf(AtdQtde2));
			sb.append(",AtdDcto2=" + AtdDcto2);
			sb.append(",SituacaoPedido=" + String.valueOf(SituacaoPedido));
			sb.append(",Ipi=" + String.valueOf(Ipi));
			sb.append(",Dctoprom=" + String.valueOf(Dctoprom));
			sb.append(",EconVlr=" + String.valueOf(EconVlr));
			sb.append(",EconCalc=" + String.valueOf(EconCalc));
			sb.append(",EconDcto=" + String.valueOf(EconDcto));
			sb.append(",PrecoMin=" + String.valueOf(PrecoMin));
			sb.append(",Tipo=" + Tipo);
			sb.append(",Dctovda=" + String.valueOf(Dctovda));
			sb.append(",TipoPreco=" + String.valueOf(TipoPreco));
			sb.append(",EstVlr=" + String.valueOf(EstVlr));
			sb.append(",Acrescimo=" + String.valueOf(Acrescimo));
			sb.append(",Lpreco=" + Lpreco);
			sb.append(",Grade=" + Grade);
			sb.append(",TpGrade=" + String.valueOf(TpGrade));
			sb.append(",CodEtiq=" + CodEtiq);
			sb.append(",Embalagem=" + String.valueOf(Embalagem));
			sb.append(",MotCanc=" + MotCanc);
			sb.append(",PercComis=" + String.valueOf(PercComis));
			sb.append(",PrdCfgCar=" + PrdCfgCar);
			sb.append(",Observacao=" + Observacao);
			sb.append(",BonifEcon=" + BonifEcon);
			sb.append(",PcomisExt=" + String.valueOf(PcomisExt));
			sb.append(",AplicDcto=" + AplicDcto);
			sb.append(",Operacao=" + Operacao);
			sb.append(",QtdeRes=" + String.valueOf(QtdeRes));
			sb.append(",Bloqsug=" + Bloqsug);
			sb.append(",Dctoinc=" + String.valueOf(Dctoinc));
			sb.append(",VlrIpi=" + String.valueOf(VlrIpi));
			sb.append(",SeqGrd=" + SeqGrd);
			sb.append(",SeqErp=" + String.valueOf(SeqErp));
			sb.append(",PrevCalc=" + String.valueOf(PrevCalc));
			sb.append(",Almoxarif=" + Almoxarif);
			sb.append(",ComisGer=" + String.valueOf(ComisGer));
			sb.append(",CredProp=" + String.valueOf(CredProp));
			sb.append(",NroEnvio=" + String.valueOf(NroEnvio));
			sb.append(",Chave=" + Chave);
			sb.append(",ItemConf=" + ItemConf);
			sb.append(",CvdaDcti=" + CvdaDcti);
			sb.append(",ObsNota=" + ObsNota);
			sb.append(",ObsExped=" + ObsExped);
			sb.append(",ObsSepar=" + ObsSepar);
			sb.append(",VlrSubst=" + String.valueOf(VlrSubst));
			sb.append(",DesctoGer=" + String.valueOf(DesctoGer));
			sb.append(",VlrFreTit=" + String.valueOf(VlrFreTit));
			sb.append(",MotTroca=" + MotTroca);
			sb.append(",VeconCig=" + String.valueOf(VeconCig));
			sb.append(",CustoMp=" + String.valueOf(CustoMp));
			sb.append(",PliqAlt=" + String.valueOf(PliqAlt));
			sb.append(",CodPrMap=" + CodPrMap);
			sb.append(",CodCvdAg=" + CodCvdAg);
			sb.append(",VuIcms=" + String.valueOf(VuIcms));
			sb.append(",VuPis=" + String.valueOf(VuPis));
			sb.append(",VuCofins=" + String.valueOf(VuCofins));
			sb.append(",Altdtprev=" + String.valueOf(Altdtprev));
			sb.append(",QtdeAloc=" + String.valueOf(QtdeAloc));
			sb.append(",RestrBonif=" + RestrBonif);
			sb.append(",CustoMo=" + String.valueOf(CustoMo));
			sb.append(",ItControl=" + String.valueOf(ItControl));
			sb.append(",ItPermBon=" + String.valueOf(ItPermBon));
			sb.append(",QtdPermBon=" + String.valueOf(QtdPermBon));
			sb.append(",VlrPermBon=" + String.valueOf(VlrPermBon));
			sb.append(",Itemfemb=" + String.valueOf(Itemfemb));
			sb.append(",Dtprevento=" + String.valueOf(Dtprevento));
			sb.append(",Cfretit=" + Cfretit);
			sb.append(",SldVerba=" + String.valueOf(SldVerba));
			sb.append(",VlrBonif=" + String.valueOf(VlrBonif));
			sb.append(",Bonif=" + String.valueOf(Bonif));
			sb.append(",Tpproduto=" + String.valueOf(Tpproduto));
			sb.append(",Backord=" + Backord);
			sb.append(",Markup=" + String.valueOf(Markup));
			sb.append(",QtdeBkor=" + String.valueOf(QtdeBkor));
			sb.append(",Prcpromoc=" + String.valueOf(Prcpromoc));
			sb.append(",Sitcorp1=" + Sitcorp1);
			sb.append(",Sitcorp2=" + Sitcorp2);
			sb.append(",LibMots=" + LibMots);
			sb.append(",Dctoboleto=" + String.valueOf(Dctoboleto));
			sb.append(",MPRISeq=" + String.valueOf(MPRISeq));
			sb.append(",Dctomax=" + String.valueOf(Dctomax));
			sb.append(",Comprim=" + String.valueOf(Comprim));
			sb.append(",QtdePeca=" + String.valueOf(QtdePeca));
			sb.append(",ProdRes=" + ProdRes);
			sb.append(",EmpEstoq=" + EmpEstoq);
			sb.append(",PrevProduc=" + String.valueOf(PrevProduc));
			sb.append(",PrunitOri=" + String.valueOf(PrunitOri));
			sb.append(",PrliqOri=" + String.valueOf(PrliqOri));
			sb.append(",Mva=" + String.valueOf(Mva));
			sb.append(",ICMSOrig=" + String.valueOf(ICMSOrig));
			sb.append(",ICMSDest=" + String.valueOf(ICMSDest));
			sb.append(",Float1=" + String.valueOf(Float1));
			sb.append(",Float2=" + String.valueOf(Float2));
			sb.append(",DataAlter=" + String.valueOf(DataAlter));
			sb.append(",SeqOrig=" + SeqOrig);
			sb.append(",PrecoNorm=" + String.valueOf(PrecoNorm));
			sb.append(",Tipost=" + String.valueOf(Tipost));
			sb.append(",CodProdcli=" + CodProdcli);
			sb.append(",VlrDescarga=" + String.valueOf(VlrDescarga));
			sb.append(",IcmsDscto=" + String.valueOf(IcmsDscto));
			sb.append(",EmpSplit=" + EmpSplit);
			sb.append(",Status=" + String.valueOf(Status));
			sb.append(",Obrigatorio=" + String.valueOf(Obrigatorio));
			sb.append(",PrecoFirme=" + String.valueOf(PrecoFirme));
			sb.append(",OrdCompra=" + OrdCompra);
			sb.append(",DesctoFiscal=" + String.valueOf(DesctoFiscal));
			sb.append(",PercMlucr=" + String.valueOf(PercMlucr));
			sb.append(",VlrDescargaCalc=" + String.valueOf(VlrDescargaCalc));
			sb.append(",VlrFreTitCalc=" + String.valueOf(VlrFreTitCalc));
			sb.append(",VuComissao=" + String.valueOf(VuComissao));
			sb.append(",VuDebcli=" + String.valueOf(VuDebcli));
			sb.append(",Data1Original=" + String.valueOf(Data1Original));
			sb.append(",Visao=" + String.valueOf(Visao));
			sb.append(",Periodo=" + String.valueOf(Periodo));
			sb.append(",BaseIcms=" + String.valueOf(BaseIcms));
			sb.append(",BaseSt=" + String.valueOf(BaseSt));
			sb.append(",CondPgto=" + String.valueOf(CondPgto));
			sb.append(",UsuCanc=" + UsuCanc);
			sb.append(",Resrede=" + String.valueOf(Resrede));
			sb.append(",DocBloq=" + DocBloq);
			sb.append(",Regracomis=" + Regracomis);
			sb.append(",BaseIcmsRed=" + String.valueOf(BaseIcmsRed));
			sb.append(",IcmsSemRed=" + String.valueOf(IcmsSemRed));
			sb.append(",Obs1=" + Obs1);
			sb.append(",Obs2=" + Obs2);
			sb.append(",CodEmbal=" + CodEmbal);
			sb.append(",CondPgtoCalc=" + String.valueOf(CondPgtoCalc));
			sb.append(",CodigoMix=" + String.valueOf(CodigoMix));
			sb.append(",QtdeMix=" + String.valueOf(QtdeMix));
			sb.append(",Lote=" + Lote);
			sb.append(",VlrDesctoFiscal=" + String.valueOf(VlrDesctoFiscal));
			sb.append(",Tamanho=" + Tamanho);
			sb.append(",BloqVerba=" + String.valueOf(BloqVerba));
			sb.append(",VlrFreTitRateio=" + String.valueOf(VlrFreTitRateio));
			sb.append(",VlrFreTitRateioCalc=" + String.valueOf(VlrFreTitRateioCalc));
			sb.append(",VisaoCota=" + String.valueOf(VisaoCota));
			sb.append(",PeriodoCota=" + String.valueOf(PeriodoCota));
			sb.append(",VisaoCotaOriginal=" + String.valueOf(VisaoCotaOriginal));
			sb.append(",PeriodoCotaOriginal=" + String.valueOf(PeriodoCotaOriginal));
			sb.append(",RegraEmpItem=" + String.valueOf(RegraEmpItem));
			sb.append(",LjPeca=" + String.valueOf(LjPeca));
			sb.append(",LjTamanho=" + String.valueOf(LjTamanho));
			sb.append(",LjCarga=" + String.valueOf(LjCarga));
			sb.append(",LjMedidaA=" + String.valueOf(LjMedidaA));
			sb.append(",LjVao=" + String.valueOf(LjVao));
			sb.append(",LjAngulo=" + String.valueOf(LjAngulo));
			sb.append(",LjMedidaB=" + String.valueOf(LjMedidaB));
			sb.append(",LjInicioViga=" + String.valueOf(LjInicioViga));
			sb.append(",LjAba=" + String.valueOf(LjAba));
			sb.append(",LjAbaA=" + String.valueOf(LjAbaA));
			sb.append(",LjAbaB=" + String.valueOf(LjAbaB));
			sb.append(",Bloqluc=" + String.valueOf(Bloqluc));
			sb.append(",OrigemCota=" + String.valueOf(OrigemCota));
			sb.append(",OrigemCanc=" + String.valueOf(OrigemCanc));
			sb.append(",Incsug=" + String.valueOf(Incsug));
			sb.append(",LjIdent=" + String.valueOf(LjIdent));
			sb.append(",CondFreteIt=" + CondFreteIt);
			sb.append(",DesctotalCalc=" + String.valueOf(DesctotalCalc));
			sb.append(",IdPedAuto=" + IdPedAuto);
			sb.append(",LoteEstoque=" + LoteEstoque);
			sb.append(",DesctotalAplicado=" + String.valueOf(DesctotalAplicado));
			sb.append(",SeqOrdemCompra=" + String.valueOf(SeqOrdemCompra));
			sb.append(",SeqordCompra=" + SeqordCompra);
			sb.append(",Ordcomest=" + Ordcomest);
			sb.append(",ValorICMSFrete=" + String.valueOf(ValorICMSFrete));
			sb.append(",Campo1=" + String.valueOf(Campo1));
			sb.append(",CentroCusto=" + CentroCusto);
			sb.append(",BloqDesc=" + String.valueOf(BloqDesc));
			sb.append(",QtdeDescBonif=" + String.valueOf(QtdeDescBonif));
			sb.append(",PercDescBonif=" + String.valueOf(PercDescBonif));
			sb.append(",ValorDesccUnit=" + String.valueOf(ValorDesccUnit));
			sb.append(",SeqCota=" + String.valueOf(SeqCota));
			sb.append(",SeqCotaOriginal=" + String.valueOf(SeqCotaOriginal));
			sb.append(",DtRegraCorte=" + String.valueOf(DtRegraCorte));
			sb.append(",CompoeMargem=" + String.valueOf(CompoeMargem));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (SequenciaItem == null) {
				sb.append("<null>");
			} else {
				sb.append(SequenciaItem);
			}

			sb.append("|");

			if (CodigoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoProduto);
			}

			sb.append("|");

			if (QuantidadeProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeProduto);
			}

			sb.append("|");

			if (QuantidadeAtendida == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeAtendida);
			}

			sb.append("|");

			if (QuantidadeCancelada == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeCancelada);
			}

			sb.append("|");

			if (PrecoUnitario == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoUnitario);
			}

			sb.append("|");

			if (PrecoLiquido == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoLiquido);
			}

			sb.append("|");

			if (Desctop == null) {
				sb.append("<null>");
			} else {
				sb.append(Desctop);
			}

			sb.append("|");

			if (DesctoIcm == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoIcm);
			}

			sb.append("|");

			if (PrevEntr == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevEntr);
			}

			sb.append("|");

			if (AtdData == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData);
			}

			sb.append("|");

			if (AtdQtde == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde);
			}

			sb.append("|");

			if (AtdDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto);
			}

			sb.append("|");

			if (AtdData1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData1);
			}

			sb.append("|");

			if (AtdQtde1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde1);
			}

			sb.append("|");

			if (AtdDcto1 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto1);
			}

			sb.append("|");

			if (AtdData2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdData2);
			}

			sb.append("|");

			if (AtdQtde2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdQtde2);
			}

			sb.append("|");

			if (AtdDcto2 == null) {
				sb.append("<null>");
			} else {
				sb.append(AtdDcto2);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (Ipi == null) {
				sb.append("<null>");
			} else {
				sb.append(Ipi);
			}

			sb.append("|");

			if (Dctoprom == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoprom);
			}

			sb.append("|");

			if (EconVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(EconVlr);
			}

			sb.append("|");

			if (EconCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(EconCalc);
			}

			sb.append("|");

			if (EconDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(EconDcto);
			}

			sb.append("|");

			if (PrecoMin == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoMin);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (Dctovda == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctovda);
			}

			sb.append("|");

			if (TipoPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPreco);
			}

			sb.append("|");

			if (EstVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(EstVlr);
			}

			sb.append("|");

			if (Acrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(Acrescimo);
			}

			sb.append("|");

			if (Lpreco == null) {
				sb.append("<null>");
			} else {
				sb.append(Lpreco);
			}

			sb.append("|");

			if (Grade == null) {
				sb.append("<null>");
			} else {
				sb.append(Grade);
			}

			sb.append("|");

			if (TpGrade == null) {
				sb.append("<null>");
			} else {
				sb.append(TpGrade);
			}

			sb.append("|");

			if (CodEtiq == null) {
				sb.append("<null>");
			} else {
				sb.append(CodEtiq);
			}

			sb.append("|");

			if (Embalagem == null) {
				sb.append("<null>");
			} else {
				sb.append(Embalagem);
			}

			sb.append("|");

			if (MotCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(MotCanc);
			}

			sb.append("|");

			if (PercComis == null) {
				sb.append("<null>");
			} else {
				sb.append(PercComis);
			}

			sb.append("|");

			if (PrdCfgCar == null) {
				sb.append("<null>");
			} else {
				sb.append(PrdCfgCar);
			}

			sb.append("|");

			if (Observacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao);
			}

			sb.append("|");

			if (BonifEcon == null) {
				sb.append("<null>");
			} else {
				sb.append(BonifEcon);
			}

			sb.append("|");

			if (PcomisExt == null) {
				sb.append("<null>");
			} else {
				sb.append(PcomisExt);
			}

			sb.append("|");

			if (AplicDcto == null) {
				sb.append("<null>");
			} else {
				sb.append(AplicDcto);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			if (QtdeRes == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeRes);
			}

			sb.append("|");

			if (Bloqsug == null) {
				sb.append("<null>");
			} else {
				sb.append(Bloqsug);
			}

			sb.append("|");

			if (Dctoinc == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoinc);
			}

			sb.append("|");

			if (VlrIpi == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrIpi);
			}

			sb.append("|");

			if (SeqGrd == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqGrd);
			}

			sb.append("|");

			if (SeqErp == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqErp);
			}

			sb.append("|");

			if (PrevCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevCalc);
			}

			sb.append("|");

			if (Almoxarif == null) {
				sb.append("<null>");
			} else {
				sb.append(Almoxarif);
			}

			sb.append("|");

			if (ComisGer == null) {
				sb.append("<null>");
			} else {
				sb.append(ComisGer);
			}

			sb.append("|");

			if (CredProp == null) {
				sb.append("<null>");
			} else {
				sb.append(CredProp);
			}

			sb.append("|");

			if (NroEnvio == null) {
				sb.append("<null>");
			} else {
				sb.append(NroEnvio);
			}

			sb.append("|");

			if (Chave == null) {
				sb.append("<null>");
			} else {
				sb.append(Chave);
			}

			sb.append("|");

			if (ItemConf == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemConf);
			}

			sb.append("|");

			if (CvdaDcti == null) {
				sb.append("<null>");
			} else {
				sb.append(CvdaDcti);
			}

			sb.append("|");

			if (ObsNota == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsNota);
			}

			sb.append("|");

			if (ObsExped == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsExped);
			}

			sb.append("|");

			if (ObsSepar == null) {
				sb.append("<null>");
			} else {
				sb.append(ObsSepar);
			}

			sb.append("|");

			if (VlrSubst == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrSubst);
			}

			sb.append("|");

			if (DesctoGer == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoGer);
			}

			sb.append("|");

			if (VlrFreTit == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTit);
			}

			sb.append("|");

			if (MotTroca == null) {
				sb.append("<null>");
			} else {
				sb.append(MotTroca);
			}

			sb.append("|");

			if (VeconCig == null) {
				sb.append("<null>");
			} else {
				sb.append(VeconCig);
			}

			sb.append("|");

			if (CustoMp == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoMp);
			}

			sb.append("|");

			if (PliqAlt == null) {
				sb.append("<null>");
			} else {
				sb.append(PliqAlt);
			}

			sb.append("|");

			if (CodPrMap == null) {
				sb.append("<null>");
			} else {
				sb.append(CodPrMap);
			}

			sb.append("|");

			if (CodCvdAg == null) {
				sb.append("<null>");
			} else {
				sb.append(CodCvdAg);
			}

			sb.append("|");

			if (VuIcms == null) {
				sb.append("<null>");
			} else {
				sb.append(VuIcms);
			}

			sb.append("|");

			if (VuPis == null) {
				sb.append("<null>");
			} else {
				sb.append(VuPis);
			}

			sb.append("|");

			if (VuCofins == null) {
				sb.append("<null>");
			} else {
				sb.append(VuCofins);
			}

			sb.append("|");

			if (Altdtprev == null) {
				sb.append("<null>");
			} else {
				sb.append(Altdtprev);
			}

			sb.append("|");

			if (QtdeAloc == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeAloc);
			}

			sb.append("|");

			if (RestrBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(RestrBonif);
			}

			sb.append("|");

			if (CustoMo == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoMo);
			}

			sb.append("|");

			if (ItControl == null) {
				sb.append("<null>");
			} else {
				sb.append(ItControl);
			}

			sb.append("|");

			if (ItPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(ItPermBon);
			}

			sb.append("|");

			if (QtdPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdPermBon);
			}

			sb.append("|");

			if (VlrPermBon == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrPermBon);
			}

			sb.append("|");

			if (Itemfemb == null) {
				sb.append("<null>");
			} else {
				sb.append(Itemfemb);
			}

			sb.append("|");

			if (Dtprevento == null) {
				sb.append("<null>");
			} else {
				sb.append(Dtprevento);
			}

			sb.append("|");

			if (Cfretit == null) {
				sb.append("<null>");
			} else {
				sb.append(Cfretit);
			}

			sb.append("|");

			if (SldVerba == null) {
				sb.append("<null>");
			} else {
				sb.append(SldVerba);
			}

			sb.append("|");

			if (VlrBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrBonif);
			}

			sb.append("|");

			if (Bonif == null) {
				sb.append("<null>");
			} else {
				sb.append(Bonif);
			}

			sb.append("|");

			if (Tpproduto == null) {
				sb.append("<null>");
			} else {
				sb.append(Tpproduto);
			}

			sb.append("|");

			if (Backord == null) {
				sb.append("<null>");
			} else {
				sb.append(Backord);
			}

			sb.append("|");

			if (Markup == null) {
				sb.append("<null>");
			} else {
				sb.append(Markup);
			}

			sb.append("|");

			if (QtdeBkor == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeBkor);
			}

			sb.append("|");

			if (Prcpromoc == null) {
				sb.append("<null>");
			} else {
				sb.append(Prcpromoc);
			}

			sb.append("|");

			if (Sitcorp1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Sitcorp1);
			}

			sb.append("|");

			if (Sitcorp2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Sitcorp2);
			}

			sb.append("|");

			if (LibMots == null) {
				sb.append("<null>");
			} else {
				sb.append(LibMots);
			}

			sb.append("|");

			if (Dctoboleto == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctoboleto);
			}

			sb.append("|");

			if (MPRISeq == null) {
				sb.append("<null>");
			} else {
				sb.append(MPRISeq);
			}

			sb.append("|");

			if (Dctomax == null) {
				sb.append("<null>");
			} else {
				sb.append(Dctomax);
			}

			sb.append("|");

			if (Comprim == null) {
				sb.append("<null>");
			} else {
				sb.append(Comprim);
			}

			sb.append("|");

			if (QtdePeca == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdePeca);
			}

			sb.append("|");

			if (ProdRes == null) {
				sb.append("<null>");
			} else {
				sb.append(ProdRes);
			}

			sb.append("|");

			if (EmpEstoq == null) {
				sb.append("<null>");
			} else {
				sb.append(EmpEstoq);
			}

			sb.append("|");

			if (PrevProduc == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevProduc);
			}

			sb.append("|");

			if (PrunitOri == null) {
				sb.append("<null>");
			} else {
				sb.append(PrunitOri);
			}

			sb.append("|");

			if (PrliqOri == null) {
				sb.append("<null>");
			} else {
				sb.append(PrliqOri);
			}

			sb.append("|");

			if (Mva == null) {
				sb.append("<null>");
			} else {
				sb.append(Mva);
			}

			sb.append("|");

			if (ICMSOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSOrig);
			}

			sb.append("|");

			if (ICMSDest == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSDest);
			}

			sb.append("|");

			if (Float1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Float1);
			}

			sb.append("|");

			if (Float2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Float2);
			}

			sb.append("|");

			if (DataAlter == null) {
				sb.append("<null>");
			} else {
				sb.append(DataAlter);
			}

			sb.append("|");

			if (SeqOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqOrig);
			}

			sb.append("|");

			if (PrecoNorm == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoNorm);
			}

			sb.append("|");

			if (Tipost == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipost);
			}

			sb.append("|");

			if (CodProdcli == null) {
				sb.append("<null>");
			} else {
				sb.append(CodProdcli);
			}

			sb.append("|");

			if (VlrDescarga == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDescarga);
			}

			sb.append("|");

			if (IcmsDscto == null) {
				sb.append("<null>");
			} else {
				sb.append(IcmsDscto);
			}

			sb.append("|");

			if (EmpSplit == null) {
				sb.append("<null>");
			} else {
				sb.append(EmpSplit);
			}

			sb.append("|");

			if (Status == null) {
				sb.append("<null>");
			} else {
				sb.append(Status);
			}

			sb.append("|");

			if (Obrigatorio == null) {
				sb.append("<null>");
			} else {
				sb.append(Obrigatorio);
			}

			sb.append("|");

			if (PrecoFirme == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoFirme);
			}

			sb.append("|");

			if (OrdCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(OrdCompra);
			}

			sb.append("|");

			if (DesctoFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctoFiscal);
			}

			sb.append("|");

			if (PercMlucr == null) {
				sb.append("<null>");
			} else {
				sb.append(PercMlucr);
			}

			sb.append("|");

			if (VlrDescargaCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDescargaCalc);
			}

			sb.append("|");

			if (VlrFreTitCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitCalc);
			}

			sb.append("|");

			if (VuComissao == null) {
				sb.append("<null>");
			} else {
				sb.append(VuComissao);
			}

			sb.append("|");

			if (VuDebcli == null) {
				sb.append("<null>");
			} else {
				sb.append(VuDebcli);
			}

			sb.append("|");

			if (Data1Original == null) {
				sb.append("<null>");
			} else {
				sb.append(Data1Original);
			}

			sb.append("|");

			if (Visao == null) {
				sb.append("<null>");
			} else {
				sb.append(Visao);
			}

			sb.append("|");

			if (Periodo == null) {
				sb.append("<null>");
			} else {
				sb.append(Periodo);
			}

			sb.append("|");

			if (BaseIcms == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIcms);
			}

			sb.append("|");

			if (BaseSt == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseSt);
			}

			sb.append("|");

			if (CondPgto == null) {
				sb.append("<null>");
			} else {
				sb.append(CondPgto);
			}

			sb.append("|");

			if (UsuCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(UsuCanc);
			}

			sb.append("|");

			if (Resrede == null) {
				sb.append("<null>");
			} else {
				sb.append(Resrede);
			}

			sb.append("|");

			if (DocBloq == null) {
				sb.append("<null>");
			} else {
				sb.append(DocBloq);
			}

			sb.append("|");

			if (Regracomis == null) {
				sb.append("<null>");
			} else {
				sb.append(Regracomis);
			}

			sb.append("|");

			if (BaseIcmsRed == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIcmsRed);
			}

			sb.append("|");

			if (IcmsSemRed == null) {
				sb.append("<null>");
			} else {
				sb.append(IcmsSemRed);
			}

			sb.append("|");

			if (Obs1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Obs1);
			}

			sb.append("|");

			if (Obs2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Obs2);
			}

			sb.append("|");

			if (CodEmbal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodEmbal);
			}

			sb.append("|");

			if (CondPgtoCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(CondPgtoCalc);
			}

			sb.append("|");

			if (CodigoMix == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMix);
			}

			sb.append("|");

			if (QtdeMix == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeMix);
			}

			sb.append("|");

			if (Lote == null) {
				sb.append("<null>");
			} else {
				sb.append(Lote);
			}

			sb.append("|");

			if (VlrDesctoFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrDesctoFiscal);
			}

			sb.append("|");

			if (Tamanho == null) {
				sb.append("<null>");
			} else {
				sb.append(Tamanho);
			}

			sb.append("|");

			if (BloqVerba == null) {
				sb.append("<null>");
			} else {
				sb.append(BloqVerba);
			}

			sb.append("|");

			if (VlrFreTitRateio == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitRateio);
			}

			sb.append("|");

			if (VlrFreTitRateioCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(VlrFreTitRateioCalc);
			}

			sb.append("|");

			if (VisaoCota == null) {
				sb.append("<null>");
			} else {
				sb.append(VisaoCota);
			}

			sb.append("|");

			if (PeriodoCota == null) {
				sb.append("<null>");
			} else {
				sb.append(PeriodoCota);
			}

			sb.append("|");

			if (VisaoCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(VisaoCotaOriginal);
			}

			sb.append("|");

			if (PeriodoCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(PeriodoCotaOriginal);
			}

			sb.append("|");

			if (RegraEmpItem == null) {
				sb.append("<null>");
			} else {
				sb.append(RegraEmpItem);
			}

			sb.append("|");

			if (LjPeca == null) {
				sb.append("<null>");
			} else {
				sb.append(LjPeca);
			}

			sb.append("|");

			if (LjTamanho == null) {
				sb.append("<null>");
			} else {
				sb.append(LjTamanho);
			}

			sb.append("|");

			if (LjCarga == null) {
				sb.append("<null>");
			} else {
				sb.append(LjCarga);
			}

			sb.append("|");

			if (LjMedidaA == null) {
				sb.append("<null>");
			} else {
				sb.append(LjMedidaA);
			}

			sb.append("|");

			if (LjVao == null) {
				sb.append("<null>");
			} else {
				sb.append(LjVao);
			}

			sb.append("|");

			if (LjAngulo == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAngulo);
			}

			sb.append("|");

			if (LjMedidaB == null) {
				sb.append("<null>");
			} else {
				sb.append(LjMedidaB);
			}

			sb.append("|");

			if (LjInicioViga == null) {
				sb.append("<null>");
			} else {
				sb.append(LjInicioViga);
			}

			sb.append("|");

			if (LjAba == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAba);
			}

			sb.append("|");

			if (LjAbaA == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAbaA);
			}

			sb.append("|");

			if (LjAbaB == null) {
				sb.append("<null>");
			} else {
				sb.append(LjAbaB);
			}

			sb.append("|");

			if (Bloqluc == null) {
				sb.append("<null>");
			} else {
				sb.append(Bloqluc);
			}

			sb.append("|");

			if (OrigemCota == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemCota);
			}

			sb.append("|");

			if (OrigemCanc == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemCanc);
			}

			sb.append("|");

			if (Incsug == null) {
				sb.append("<null>");
			} else {
				sb.append(Incsug);
			}

			sb.append("|");

			if (LjIdent == null) {
				sb.append("<null>");
			} else {
				sb.append(LjIdent);
			}

			sb.append("|");

			if (CondFreteIt == null) {
				sb.append("<null>");
			} else {
				sb.append(CondFreteIt);
			}

			sb.append("|");

			if (DesctotalCalc == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctotalCalc);
			}

			sb.append("|");

			if (IdPedAuto == null) {
				sb.append("<null>");
			} else {
				sb.append(IdPedAuto);
			}

			sb.append("|");

			if (LoteEstoque == null) {
				sb.append("<null>");
			} else {
				sb.append(LoteEstoque);
			}

			sb.append("|");

			if (DesctotalAplicado == null) {
				sb.append("<null>");
			} else {
				sb.append(DesctotalAplicado);
			}

			sb.append("|");

			if (SeqOrdemCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqOrdemCompra);
			}

			sb.append("|");

			if (SeqordCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqordCompra);
			}

			sb.append("|");

			if (Ordcomest == null) {
				sb.append("<null>");
			} else {
				sb.append(Ordcomest);
			}

			sb.append("|");

			if (ValorICMSFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorICMSFrete);
			}

			sb.append("|");

			if (Campo1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Campo1);
			}

			sb.append("|");

			if (CentroCusto == null) {
				sb.append("<null>");
			} else {
				sb.append(CentroCusto);
			}

			sb.append("|");

			if (BloqDesc == null) {
				sb.append("<null>");
			} else {
				sb.append(BloqDesc);
			}

			sb.append("|");

			if (QtdeDescBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(QtdeDescBonif);
			}

			sb.append("|");

			if (PercDescBonif == null) {
				sb.append("<null>");
			} else {
				sb.append(PercDescBonif);
			}

			sb.append("|");

			if (ValorDesccUnit == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorDesccUnit);
			}

			sb.append("|");

			if (SeqCota == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqCota);
			}

			sb.append("|");

			if (SeqCotaOriginal == null) {
				sb.append("<null>");
			} else {
				sb.append(SeqCotaOriginal);
			}

			sb.append("|");

			if (DtRegraCorte == null) {
				sb.append("<null>");
			} else {
				sb.append(DtRegraCorte);
			}

			sb.append("|");

			if (CompoeMargem == null) {
				sb.append("<null>");
			} else {
				sb.append(CompoeMargem);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.CodigoProduto, other.CodigoProduto);
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
		org.slf4j.MDC.put("_subJobPid", "NqY3CF_" + subJobPidCounter.getAndIncrement());

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
				row4Struct row4 = new row4Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

				int tos_count_tFileOutputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_1 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_1.append("Parameters:");
							log4jParamters_tFileOutputDelimited_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FILENAME" + " = "
									+ "\"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/PedidosForcaVenda.qvd\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_1 - " + (log4jParamters_tFileOutputDelimited_1));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_1", "tFileOutputDelimited_1", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
				}

				String fileName_tFileOutputDelimited_1 = "";
				class FileOutputDelimitedUtil_tFileOutputDelimited_1 {
					public void putHeaderValue_0(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("NumeroPedido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SequenciaItem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoProduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QuantidadeProduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QuantidadeAtendida");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QuantidadeCancelada");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoUnitario");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoLiquido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Desctop");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DesctoIcm");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrevEntr");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdData");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdQtde");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdDcto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdData1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdQtde1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdDcto1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdData2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdQtde2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AtdDcto2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SituacaoPedido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Ipi");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dctoprom");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EconVlr");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EconCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EconDcto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoMin");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tipo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dctovda");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoPreco");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EstVlr");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Acrescimo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Lpreco");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Grade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TpGrade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodEtiq");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Embalagem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MotCanc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PercComis");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrdCfgCar");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Observacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BonifEcon");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PcomisExt");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AplicDcto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Operacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdeRes");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Bloqsug");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dctoinc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrIpi");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqGrd");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqErp");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrevCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Almoxarif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ComisGer");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CredProp");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NroEnvio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Chave");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ItemConf");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CvdaDcti");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ObsNota");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ObsExped");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ObsSepar");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrSubst");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DesctoGer");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrFreTit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MotTroca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VeconCig");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CustoMp");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PliqAlt");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodPrMap");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodCvdAg");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VuIcms");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VuPis");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VuCofins");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Altdtprev");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdeAloc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RestrBonif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CustoMo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ItControl");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ItPermBon");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdPermBon");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrPermBon");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Itemfemb");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dtprevento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Cfretit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SldVerba");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrBonif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Bonif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tpproduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Backord");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_1(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("Markup");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdeBkor");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prcpromoc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sitcorp1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Sitcorp2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LibMots");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dctoboleto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MPRISeq");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Dctomax");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comprim");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdePeca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ProdRes");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EmpEstoq");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrevProduc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrunitOri");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrliqOri");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Mva");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMSOrig");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMSDest");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Float1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Float2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataAlter");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqOrig");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoNorm");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tipost");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodProdcli");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrDescarga");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("IcmsDscto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EmpSplit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Status");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Obrigatorio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoFirme");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("OrdCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DesctoFiscal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PercMlucr");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrDescargaCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrFreTitCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VuComissao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VuDebcli");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Data1Original");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Visao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Periodo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseIcms");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseSt");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CondPgto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UsuCanc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Resrede");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DocBloq");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Regracomis");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseIcmsRed");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("IcmsSemRed");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Obs1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Obs2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodEmbal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CondPgtoCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoMix");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdeMix");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Lote");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrDesctoFiscal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tamanho");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BloqVerba");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrFreTitRateio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VlrFreTitRateioCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VisaoCota");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PeriodoCota");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VisaoCotaOriginal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PeriodoCotaOriginal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RegraEmpItem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjPeca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjTamanho");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjCarga");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjMedidaA");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjVao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjAngulo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjMedidaB");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjInicioViga");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjAba");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjAbaA");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjAbaB");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Bloqluc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("OrigemCota");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("OrigemCanc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Incsug");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LjIdent");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CondFreteIt");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DesctotalCalc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("IdPedAuto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LoteEstoque");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DesctotalAplicado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqOrdemCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_2(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("SeqordCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Ordcomest");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorICMSFrete");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Campo1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CentroCusto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BloqDesc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QtdeDescBonif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PercDescBonif");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorDesccUnit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqCota");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SeqCotaOriginal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DtRegraCorte");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CompoeMargem");
					}

					public void putValue_0(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.NumeroPedido != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPedido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SequenciaItem != null) {
							sb_tFileOutputDelimited_1.append(row4.SequenciaItem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodigoProduto != null) {
							sb_tFileOutputDelimited_1.append(row4.CodigoProduto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QuantidadeProduto != null) {
							sb_tFileOutputDelimited_1.append(row4.QuantidadeProduto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QuantidadeAtendida != null) {
							sb_tFileOutputDelimited_1.append(row4.QuantidadeAtendida);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QuantidadeCancelada != null) {
							sb_tFileOutputDelimited_1.append(row4.QuantidadeCancelada);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoUnitario != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoUnitario);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoLiquido != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoLiquido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Desctop != null) {
							sb_tFileOutputDelimited_1.append(row4.Desctop);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DesctoIcm != null) {
							sb_tFileOutputDelimited_1.append(row4.DesctoIcm);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrevEntr != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.PrevEntr, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdData != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.AtdData, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdQtde != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdQtde);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdDcto != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdDcto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdData1 != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.AtdData1, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdQtde1 != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdQtde1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdDcto1 != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdDcto1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdData2 != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.AtdData2, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdQtde2 != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdQtde2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AtdDcto2 != null) {
							sb_tFileOutputDelimited_1.append(row4.AtdDcto2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SituacaoPedido != null) {
							sb_tFileOutputDelimited_1.append(row4.SituacaoPedido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Ipi != null) {
							sb_tFileOutputDelimited_1.append(row4.Ipi);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dctoprom != null) {
							sb_tFileOutputDelimited_1.append(row4.Dctoprom);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EconVlr != null) {
							sb_tFileOutputDelimited_1.append(row4.EconVlr);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EconCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.EconCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EconDcto != null) {
							sb_tFileOutputDelimited_1.append(row4.EconDcto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoMin != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoMin);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Tipo != null) {
							sb_tFileOutputDelimited_1.append(row4.Tipo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dctovda != null) {
							sb_tFileOutputDelimited_1.append(row4.Dctovda);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TipoPreco != null) {
							sb_tFileOutputDelimited_1.append(row4.TipoPreco);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EstVlr != null) {
							sb_tFileOutputDelimited_1.append(row4.EstVlr);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Acrescimo != null) {
							sb_tFileOutputDelimited_1.append(row4.Acrescimo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Lpreco != null) {
							sb_tFileOutputDelimited_1.append(row4.Lpreco);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Grade != null) {
							sb_tFileOutputDelimited_1.append(row4.Grade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TpGrade != null) {
							sb_tFileOutputDelimited_1.append(row4.TpGrade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodEtiq != null) {
							sb_tFileOutputDelimited_1.append(row4.CodEtiq);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Embalagem != null) {
							sb_tFileOutputDelimited_1.append(row4.Embalagem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.MotCanc != null) {
							sb_tFileOutputDelimited_1.append(row4.MotCanc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PercComis != null) {
							sb_tFileOutputDelimited_1.append(row4.PercComis);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrdCfgCar != null) {
							sb_tFileOutputDelimited_1.append(row4.PrdCfgCar);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Observacao != null) {
							sb_tFileOutputDelimited_1.append(row4.Observacao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BonifEcon != null) {
							sb_tFileOutputDelimited_1.append(row4.BonifEcon);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PcomisExt != null) {
							sb_tFileOutputDelimited_1.append(row4.PcomisExt);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AplicDcto != null) {
							sb_tFileOutputDelimited_1.append(row4.AplicDcto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Operacao != null) {
							sb_tFileOutputDelimited_1.append(row4.Operacao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdeRes != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdeRes);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Bloqsug != null) {
							sb_tFileOutputDelimited_1.append(row4.Bloqsug);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dctoinc != null) {
							sb_tFileOutputDelimited_1.append(row4.Dctoinc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrIpi != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrIpi);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqGrd != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqGrd);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqErp != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqErp);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrevCalc != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.PrevCalc, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Almoxarif != null) {
							sb_tFileOutputDelimited_1.append(row4.Almoxarif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ComisGer != null) {
							sb_tFileOutputDelimited_1.append(row4.ComisGer);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CredProp != null) {
							sb_tFileOutputDelimited_1.append(row4.CredProp);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NroEnvio != null) {
							sb_tFileOutputDelimited_1.append(row4.NroEnvio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Chave != null) {
							sb_tFileOutputDelimited_1.append(row4.Chave);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ItemConf != null) {
							sb_tFileOutputDelimited_1.append(row4.ItemConf);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CvdaDcti != null) {
							sb_tFileOutputDelimited_1.append(row4.CvdaDcti);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ObsNota != null) {
							sb_tFileOutputDelimited_1.append(row4.ObsNota);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ObsExped != null) {
							sb_tFileOutputDelimited_1.append(row4.ObsExped);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ObsSepar != null) {
							sb_tFileOutputDelimited_1.append(row4.ObsSepar);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrSubst != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrSubst);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DesctoGer != null) {
							sb_tFileOutputDelimited_1.append(row4.DesctoGer);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrFreTit != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrFreTit);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.MotTroca != null) {
							sb_tFileOutputDelimited_1.append(row4.MotTroca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VeconCig != null) {
							sb_tFileOutputDelimited_1.append(row4.VeconCig);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CustoMp != null) {
							sb_tFileOutputDelimited_1.append(row4.CustoMp);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PliqAlt != null) {
							sb_tFileOutputDelimited_1.append(row4.PliqAlt);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodPrMap != null) {
							sb_tFileOutputDelimited_1.append(row4.CodPrMap);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodCvdAg != null) {
							sb_tFileOutputDelimited_1.append(row4.CodCvdAg);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VuIcms != null) {
							sb_tFileOutputDelimited_1.append(row4.VuIcms);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VuPis != null) {
							sb_tFileOutputDelimited_1.append(row4.VuPis);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VuCofins != null) {
							sb_tFileOutputDelimited_1.append(row4.VuCofins);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Altdtprev != null) {
							sb_tFileOutputDelimited_1.append(row4.Altdtprev);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdeAloc != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdeAloc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.RestrBonif != null) {
							sb_tFileOutputDelimited_1.append(row4.RestrBonif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CustoMo != null) {
							sb_tFileOutputDelimited_1.append(row4.CustoMo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ItControl != null) {
							sb_tFileOutputDelimited_1.append(row4.ItControl);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ItPermBon != null) {
							sb_tFileOutputDelimited_1.append(row4.ItPermBon);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdPermBon != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdPermBon);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrPermBon != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrPermBon);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Itemfemb != null) {
							sb_tFileOutputDelimited_1.append(row4.Itemfemb);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dtprevento != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.Dtprevento, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Cfretit != null) {
							sb_tFileOutputDelimited_1.append(row4.Cfretit);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SldVerba != null) {
							sb_tFileOutputDelimited_1.append(row4.SldVerba);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrBonif != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrBonif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Bonif != null) {
							sb_tFileOutputDelimited_1.append(row4.Bonif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Tpproduto != null) {
							sb_tFileOutputDelimited_1.append(row4.Tpproduto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Backord != null) {
							sb_tFileOutputDelimited_1.append(row4.Backord);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_1(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.Markup != null) {
							sb_tFileOutputDelimited_1.append(row4.Markup);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdeBkor != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdeBkor);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Prcpromoc != null) {
							sb_tFileOutputDelimited_1.append(row4.Prcpromoc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sitcorp1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sitcorp1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Sitcorp2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Sitcorp2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LibMots != null) {
							sb_tFileOutputDelimited_1.append(row4.LibMots);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dctoboleto != null) {
							sb_tFileOutputDelimited_1.append(row4.Dctoboleto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.MPRISeq != null) {
							sb_tFileOutputDelimited_1.append(row4.MPRISeq);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Dctomax != null) {
							sb_tFileOutputDelimited_1.append(row4.Dctomax);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comprim != null) {
							sb_tFileOutputDelimited_1.append(row4.Comprim);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdePeca != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdePeca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ProdRes != null) {
							sb_tFileOutputDelimited_1.append(row4.ProdRes);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EmpEstoq != null) {
							sb_tFileOutputDelimited_1.append(row4.EmpEstoq);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrevProduc != null) {
							sb_tFileOutputDelimited_1.append(row4.PrevProduc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrunitOri != null) {
							sb_tFileOutputDelimited_1.append(row4.PrunitOri);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrliqOri != null) {
							sb_tFileOutputDelimited_1.append(row4.PrliqOri);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Mva != null) {
							sb_tFileOutputDelimited_1.append(row4.Mva);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMSOrig != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMSOrig);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMSDest != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMSDest);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Float1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Float1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Float2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Float2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataAlter != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.DataAlter, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqOrig != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqOrig);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoNorm != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoNorm);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Tipost != null) {
							sb_tFileOutputDelimited_1.append(row4.Tipost);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodProdcli != null) {
							sb_tFileOutputDelimited_1.append(row4.CodProdcli);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrDescarga != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrDescarga);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.IcmsDscto != null) {
							sb_tFileOutputDelimited_1.append(row4.IcmsDscto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.EmpSplit != null) {
							sb_tFileOutputDelimited_1.append(row4.EmpSplit);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Status != null) {
							sb_tFileOutputDelimited_1.append(row4.Status);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Obrigatorio != null) {
							sb_tFileOutputDelimited_1.append(row4.Obrigatorio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoFirme != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoFirme);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.OrdCompra != null) {
							sb_tFileOutputDelimited_1.append(row4.OrdCompra);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DesctoFiscal != null) {
							sb_tFileOutputDelimited_1.append(row4.DesctoFiscal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PercMlucr != null) {
							sb_tFileOutputDelimited_1.append(row4.PercMlucr);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrDescargaCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrDescargaCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrFreTitCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrFreTitCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VuComissao != null) {
							sb_tFileOutputDelimited_1.append(row4.VuComissao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VuDebcli != null) {
							sb_tFileOutputDelimited_1.append(row4.VuDebcli);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Data1Original != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.Data1Original, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Visao != null) {
							sb_tFileOutputDelimited_1.append(row4.Visao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Periodo != null) {
							sb_tFileOutputDelimited_1.append(row4.Periodo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseIcms != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseIcms);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseSt != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseSt);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CondPgto != null) {
							sb_tFileOutputDelimited_1.append(row4.CondPgto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.UsuCanc != null) {
							sb_tFileOutputDelimited_1.append(row4.UsuCanc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Resrede != null) {
							sb_tFileOutputDelimited_1.append(row4.Resrede);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DocBloq != null) {
							sb_tFileOutputDelimited_1.append(row4.DocBloq);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Regracomis != null) {
							sb_tFileOutputDelimited_1.append(row4.Regracomis);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseIcmsRed != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseIcmsRed);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.IcmsSemRed != null) {
							sb_tFileOutputDelimited_1.append(row4.IcmsSemRed);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Obs1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Obs1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Obs2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Obs2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodEmbal != null) {
							sb_tFileOutputDelimited_1.append(row4.CodEmbal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CondPgtoCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.CondPgtoCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodigoMix != null) {
							sb_tFileOutputDelimited_1.append(row4.CodigoMix);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdeMix != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdeMix);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Lote != null) {
							sb_tFileOutputDelimited_1.append(row4.Lote);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrDesctoFiscal != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrDesctoFiscal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Tamanho != null) {
							sb_tFileOutputDelimited_1.append(row4.Tamanho);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BloqVerba != null) {
							sb_tFileOutputDelimited_1.append(row4.BloqVerba);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrFreTitRateio != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrFreTitRateio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VlrFreTitRateioCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.VlrFreTitRateioCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VisaoCota != null) {
							sb_tFileOutputDelimited_1.append(row4.VisaoCota);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PeriodoCota != null) {
							sb_tFileOutputDelimited_1.append(row4.PeriodoCota);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VisaoCotaOriginal != null) {
							sb_tFileOutputDelimited_1.append(row4.VisaoCotaOriginal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PeriodoCotaOriginal != null) {
							sb_tFileOutputDelimited_1.append(row4.PeriodoCotaOriginal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.RegraEmpItem != null) {
							sb_tFileOutputDelimited_1.append(row4.RegraEmpItem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjPeca != null) {
							sb_tFileOutputDelimited_1.append(row4.LjPeca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjTamanho != null) {
							sb_tFileOutputDelimited_1.append(row4.LjTamanho);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjCarga != null) {
							sb_tFileOutputDelimited_1.append(row4.LjCarga);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjMedidaA != null) {
							sb_tFileOutputDelimited_1.append(row4.LjMedidaA);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjVao != null) {
							sb_tFileOutputDelimited_1.append(row4.LjVao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjAngulo != null) {
							sb_tFileOutputDelimited_1.append(row4.LjAngulo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjMedidaB != null) {
							sb_tFileOutputDelimited_1.append(row4.LjMedidaB);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjInicioViga != null) {
							sb_tFileOutputDelimited_1.append(row4.LjInicioViga);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjAba != null) {
							sb_tFileOutputDelimited_1.append(row4.LjAba);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjAbaA != null) {
							sb_tFileOutputDelimited_1.append(row4.LjAbaA);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjAbaB != null) {
							sb_tFileOutputDelimited_1.append(row4.LjAbaB);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Bloqluc != null) {
							sb_tFileOutputDelimited_1.append(row4.Bloqluc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.OrigemCota != null) {
							sb_tFileOutputDelimited_1.append(row4.OrigemCota);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.OrigemCanc != null) {
							sb_tFileOutputDelimited_1.append(row4.OrigemCanc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Incsug != null) {
							sb_tFileOutputDelimited_1.append(row4.Incsug);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LjIdent != null) {
							sb_tFileOutputDelimited_1.append(row4.LjIdent);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CondFreteIt != null) {
							sb_tFileOutputDelimited_1.append(row4.CondFreteIt);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DesctotalCalc != null) {
							sb_tFileOutputDelimited_1.append(row4.DesctotalCalc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.IdPedAuto != null) {
							sb_tFileOutputDelimited_1.append(row4.IdPedAuto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.LoteEstoque != null) {
							sb_tFileOutputDelimited_1.append(row4.LoteEstoque);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DesctotalAplicado != null) {
							sb_tFileOutputDelimited_1.append(row4.DesctotalAplicado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqOrdemCompra != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqOrdemCompra);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_2(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.SeqordCompra != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqordCompra);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Ordcomest != null) {
							sb_tFileOutputDelimited_1.append(row4.Ordcomest);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorICMSFrete != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorICMSFrete);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Campo1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Campo1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CentroCusto != null) {
							sb_tFileOutputDelimited_1.append(row4.CentroCusto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BloqDesc != null) {
							sb_tFileOutputDelimited_1.append(row4.BloqDesc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QtdeDescBonif != null) {
							sb_tFileOutputDelimited_1.append(row4.QtdeDescBonif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PercDescBonif != null) {
							sb_tFileOutputDelimited_1.append(row4.PercDescBonif);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorDesccUnit != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorDesccUnit);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqCota != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqCota);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SeqCotaOriginal != null) {
							sb_tFileOutputDelimited_1.append(row4.SeqCotaOriginal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DtRegraCorte != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DtRegraCorte, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CompoeMargem != null) {
							sb_tFileOutputDelimited_1.append(row4.CompoeMargem);
						}
					}
				}
				FileOutputDelimitedUtil_tFileOutputDelimited_1 fileOutputDelimitedUtil_tFileOutputDelimited_1 = new FileOutputDelimitedUtil_tFileOutputDelimited_1();
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/PedidosForcaVenda.qvd"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						log.info("tFileOutputDelimited_1 - Creating directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_1.mkdirs();
						log.info("tFileOutputDelimited_1 - The directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				if (filetFileOutputDelimited_1.length() == 0) {
					fileOutputDelimitedUtil_tFileOutputDelimited_1.putHeaderValue_0(outtFileOutputDelimited_1,
							OUT_DELIM_tFileOutputDelimited_1);
					fileOutputDelimitedUtil_tFileOutputDelimited_1.putHeaderValue_1(outtFileOutputDelimited_1,
							OUT_DELIM_tFileOutputDelimited_1);
					fileOutputDelimitedUtil_tFileOutputDelimited_1.putHeaderValue_2(outtFileOutputDelimited_1,
							OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tReplicate_1 begin ] start
				 */

				ok_Hash.put("tReplicate_1", false);
				start_Hash.put("tReplicate_1", System.currentTimeMillis());

				currentComponent = "tReplicate_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tReplicate_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tReplicate_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tReplicate_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tReplicate_1 = new StringBuilder();
							log4jParamters_tReplicate_1.append("Parameters:");
							if (log.isDebugEnabled())
								log.debug("tReplicate_1 - " + (log4jParamters_tReplicate_1));
						}
					}
					new BytesLimit65535_tReplicate_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tReplicate_1", "tReplicate_1", "tReplicate");
					talendJobLogProcess(globalMap);
				}

				/**
				 * [tReplicate_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				cLabel = "Mercanet_Producao";

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
									"enc:routine.encryption.key.v1:2htvydE3n6vciJtjXB3ywqYXsUMa+qcvSXT6dvfB31dKha9aKu9doYmm")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TABLE" + " = " + "\"DB_PEDIDO_PROD\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERY" + " = "
									+ "\"SELECT     DB_PEDI_PEDIDO AS NumeroPedido,    DB_PEDI_SEQUENCIA AS SequenciaItem,    DB_PEDI_PRODUTO AS CodigoProduto,    DB_PEDI_QTDE_SOLIC AS QuantidadeProduto,    DB_PEDI_QTDE_ATEND AS QuantidadeAtendida,    DB_PEDI_QTDE_CANC AS QuantidadeCancelada,    DB_PEDI_PRECO_UNIT AS PrecoUnitario,    DB_PEDI_PRECO_LIQ AS PrecoLiquido,    DB_PEDI_DESCTOP AS Desctop,    DB_PEDI_DESCTO_ICM AS DesctoIcm,    CAST(DB_PEDI_PREV_ENTR AS DATE) AS PrevEntr,    CAST(DB_PEDI_ATD_DATA AS DATE) AS AtdData,    DB_PEDI_ATD_QTDE AS AtdQtde,    DB_PEDI_ATD_DCTO AS AtdDcto,    DB_PEDI_ATD_DATA1 AS AtdData1,    DB_PEDI_ATD_QTDE1 AS AtdQtde1,    DB_PEDI_ATD_DCTO1 AS AtdDcto1,    DB_PEDI_ATD_DATA2 AS AtdData2,    DB_PEDI_ATD_QTDE2 AS AtdQtde2,    DB_PEDI_ATD_DCTO2 AS AtdDcto2,    DB_PEDI_SITUACAO AS SituacaoPedido,    DB_PEDI_IPI AS Ipi,    DB_PEDI_DCTOPROM AS Dctoprom,    DB_PEDI_ECON_VLR AS EconVlr,    DB_PEDI_ECON_CALC AS EconCalc,    DB_PEDI_ECON_DCTO AS EconDcto,    DB_PEDI_PRECO_MIN AS PrecoMin,    DB_PEDI_TIPO AS Tipo,    DB_PEDI_DCTOVDA AS Dctovda,    DB_PEDI_TIPO_PRECO AS TipoPreco,    DB_PEDI_ESTVLR AS EstVlr,    DB_PEDI_ACRESCIMO AS Acrescimo,    DB_PEDI_LPRECO AS Lpreco,    DB_PEDI_GRADE AS Grade,    DB_PEDI_TP_GRADE AS TpGrade,    DB_PEDI_COD_ETIQ AS CodEtiq,    DB_PEDI_EMBALAGEM AS Embalagem,    DB_PEDI_MOTCANC AS MotCanc,    DB_PEDI_PERC_COMIS AS PercComis,    DB_PEDI_PRDCFGCAR AS PrdCfgCar,    DB_PEDI_OBSERV AS Observacao,    DB_PEDI_BONIFECON AS BonifEcon,    DB_PEDI_PCOMIS_EXT AS PcomisExt,    DB_PEDI_APLICDCTO AS AplicDcto,    DB_PEDI_OPERACAO AS Operacao,    DB_PEDI_QTDE_RES AS QtdeRes,    DB_PEDI_BLOQSUG AS Bloqsug,    DB_PEDI_DCTOINC AS Dctoinc,    DB_PEDI_VLRIPI AS VlrIpi,    DB_PEDI_SEQGRD AS SeqGrd,    DB_PEDI_SEQ_ERP AS SeqErp,    DB_PEDI_PREV_CALC AS PrevCalc,    DB_PEDI_ALMOXARIF AS Almoxarif,    DB_PEDI_COMIS_GER AS ComisGer,    DB_PEDI_CRED_PROP AS CredProp,    DB_PEDI_NRO_ENVIO AS NroEnvio,    DB_PEDI_CHAVE AS Chave,    DB_PEDI_ITEMCONF AS ItemConf,    DB_PEDI_CVDA_DCTI AS CvdaDcti,    DB_PEDI_OBS_NOTA AS ObsNota,    DB_PEDI_OBS_EXPED AS ObsExped,    DB_PEDI_OBS_SEPAR AS ObsSepar,    DB_PEDI_VLR_SUBST AS VlrSubst,    DB_PEDI_DESCTO_GER AS DesctoGer,    DB_PEDI_VLR_FRETIT AS VlrFreTit,    DB_PEDI_MOT_TROCA AS MotTroca,    DB_PEDI_VECON_CIG AS VeconCig,    DB_PEDI_CUSTO_MP AS CustoMp,    DB_PEDI_PLIQ_ALT AS PliqAlt,    DB_PEDI_CODPRMAP AS CodPrMap,    DB_PEDI_CODCVDAG AS CodCvdAg,    DB_PEDI_VU_ICMS AS VuIcms,    DB_PEDI_VU_PIS AS VuPis,    DB_PEDI_VU_COFINS AS VuCofins,    DB_PEDI_ALTDTPREV AS Altdtprev,    DB_PEDI_QTDE_ALOC AS QtdeAloc,    DB_PEDI_RESTRBONIF AS RestrBonif,    DB_PEDI_CUSTO_MO AS CustoMo,    DB_PEDI_ITCONTROL AS ItControl,    DB_PEDI_ITPERMBON AS ItPermBon,    DB_PEDI_QTDPERMBON AS QtdPermBon,    DB_PEDI_VLRPERMBON AS VlrPermBon,    DB_PEDI_ITEMFEMB AS Itemfemb,    CAST(DB_PEDI_DTPREVENTO AS DATE) AS Dtprevento,    DB_PEDI_CFRETIT AS Cfretit,    DB_PEDI_SLDVERBA AS SldVerba,    DB_PEDI_VLRBONIF AS VlrBonif,    DB_PEDI_BONIF AS Bonif,    DB_PEDI_TPPRODUTO AS Tpproduto,    DB_PEDI_BACKORD AS Backord,    DB_PEDI_MARKUP AS Markup,    DB_PEDI_QTDE_BKOR AS QtdeBkor,    DB_PEDI_PRCPROMOC AS Prcpromoc,    DB_PEDI_SITCORP1 AS Sitcorp1,    DB_PEDI_SITCORP2 AS Sitcorp2,    DB_PEDI_LIB_MOTS AS LibMots,    DB_PEDI_DCTOBOLETO AS Dctoboleto,    DB_PedI_MPRISeq AS MPRISeq,    DB_PEDI_DCTOMAX AS Dctomax,    DB_PedI_Comprim AS Comprim,    DB_PedI_QtdePeca AS QtdePeca,    DB_PedI_Prod_Res AS ProdRes,    DB_PEDI_EMPESTOQ AS EmpEstoq,    DB_Pedi_PrevProduc AS PrevProduc,    DB_PEDI_PRUNIT_ORI AS PrunitOri,    DB_PEDI_PRLIQ_ORI AS PrliqOri,    Db_PedI_MVA AS Mva,    DB_PedI_ICMSOrig AS ICMSOrig,    Db_PedI_ICMSDest AS ICMSDest,    DB_PEDI_FLOAT1 AS Float1,    DB_PEDI_FLOAT2 AS Float2,    DB_PEDI_DATA_ALTER AS DataAlter,    DB_PEDI_SEQ_ORIG AS SeqOrig,    DB_PEDI_PRECO_NORM AS PrecoNorm,    DB_PEDI_TIPOST AS Tipost,    DB_PEDI_COD_PRODCLI AS CodProdcli,    DB_PEDI_VLR_DESCARGA AS VlrDescarga,    DB_PEDI_IcmsDscto AS IcmsDscto,    DB_PEDI_EmpSplit AS EmpSplit,    db_pedi_status AS Status,    DB_PEDI_OBRIGATORIO AS Obrigatorio,    DB_PEDI_PRECO_FIRME AS PrecoFirme,    DB_PEDI_ORD_COMPRA AS OrdCompra,    DB_PEDI_DESCTO_FISCAL AS DesctoFiscal,    DB_PEDI_PERC_MLUCR AS PercMlucr,    DB_PEDI_VLR_DESCARGA_CALC AS VlrDescargaCalc,    DB_PEDI_VLR_FRETIT_CALC AS VlrFreTitCalc,    DB_PEDI_VU_COMISSAO AS VuComissao,    DB_PEDI_VU_DEBCLI AS VuDebcli,    CAST(DB_PEDI_DATA1_ORIGINAL AS DATE) AS Data1Original,    DB_PEDI_VISAO AS Visao,    DB_PEDI_PERIODO AS Periodo,    DB_PEDI_BASEICMS AS BaseIcms,    DB_PEDI_BASEST AS BaseSt,    DB_PEDI_COND_PGTO AS CondPgto,    DB_PEDI_USU_CANC AS UsuCanc,    DB_PEDI_RESREDE AS Resrede,    DB_PEDI_DOC_BLOQ AS DocBloq,    DB_PEDI_REGRACOMIS AS Regracomis,    DB_PEDI_BASEICMSRED AS BaseIcmsRed,    DB_PEDI_ICMSSEMRED AS IcmsSemRed,    DB_PEDI_OBS1 AS Obs1,    DB_PEDI_OBS2 AS Obs2,    DB_PEDI_CODEMBAL AS CodEmbal,    DB_PEDI_COND_PGTOCALC AS CondPgtoCalc,    DB_PEDI_CODIGO_MIX AS CodigoMix,    DB_PEDI_QTDE_MIX AS QtdeMix,    DB_PEDI_LOTE AS Lote,    DB_PEDI_VLR_DESCTO_FISCAL AS VlrDesctoFiscal,    DB_PEDI_TAMANHO AS Tamanho,    DB_PEDI_BLOQVERBA AS BloqVerba,    DB_PEDI_VLRFRETIT_RATEIO AS VlrFreTitRateio,    DB_PEDI_VLRFRETIT_RATEIO_CALC AS VlrFreTitRateioCalc,    DB_PEDI_VISAO_COTA AS VisaoCota,    DB_PEDI_PERIODO_COTA AS PeriodoCota,    DB_PEDI_VISAO_COTA_ORIGINAL AS VisaoCotaOriginal,    DB_PEDI_PERIODO_COTA_ORIGINAL AS PeriodoCotaOriginal,    DB_PEDI_REGRAEMPITEM AS RegraEmpItem,    DB_PEDI_LJ_PECA AS LjPeca,    DB_PEDI_LJ_TAMANHO AS LjTamanho,    DB_PEDI_LJ_CARGA AS LjCarga,    DB_PEDI_LJ_MEDIDA_A AS LjMedidaA,    DB_PEDI_LJ_VAO AS LjVao,    DB_PEDI_LJ_ANGULO AS LjAngulo,    DB_PEDI_LJ_MEDIDA_B AS LjMedidaB,    DB_PEDI_LJ_INICIOVIGA AS LjInicioViga,    DB_PEDI_LJ_ABA AS LjAba,    DB_PEDI_LJ_ABA_A AS LjAbaA,    DB_PEDI_LJ_ABA_B AS LjAbaB,    DB_PEDI_BLOQLUC AS Bloqluc,    DB_PEDI_ORIGEM_COTA AS OrigemCota,    DB_PEDI_ORIGEM_CANC AS OrigemCanc,    DB_PEDI_INCSUG AS Incsug,    DB_PEDI_LJ_IDENT AS LjIdent,    DB_PEDI_COND_FRETE_IT AS CondFreteIt,    DB_PEDI_DESCTOTAL_CALC AS DesctotalCalc,    DB_PEDI_ID_PED_AUTO AS IdPedAuto,    DB_PEDI_LOTE_ESTOQUE AS LoteEstoque,    DB_PEDI_DESCTOTAL_APLICADO AS DesctotalAplicado,    DB_PEDI_SEQ_ORDEM_COMPRA AS SeqOrdemCompra,    DB_PEDI_SEQORD_COMPRA AS SeqordCompra,    DB_PEDI_ORDCOMEST AS Ordcomest,    DB_PEDI_VICMSFRETE AS ValorICMSFrete,    DB_PEDI_CAMPO1 AS Campo1,    DB_PEDI_CENTROCUSTO AS CentroCusto,    DB_PEDI_BLOQDESC AS BloqDesc,    DB_PEDI_QTDE_DESCBONIF AS QtdeDescBonif,    DB_PEDI_PERC_DESCBONIF AS PercDescBonif,    DB_PEDI_VALORDESCC_UNIT AS ValorDesccUnit,    DB_PEDI_SEQ_COTA AS SeqCota,    DB_PEDI_SEQ_COTA_ORIGINAL AS SeqCotaOriginal,    DB_PEDI_DT_REGRA_CORTE AS DtRegraCorte,    DB_PEDI_COMPOEMARGEM AS CompoeMargem  FROM DB_PEDIDO_PROD  WHERE DB_PEDI_PREV_ENTR >= GETDATE()-150;\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("NumeroPedido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SequenciaItem") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CodigoProduto") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeProduto") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeAtendida") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeCancelada") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PrecoUnitario") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PrecoLiquido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Desctop") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DesctoIcm") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrevEntr")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AtdData") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AtdQtde") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AtdDcto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AtdData1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AtdQtde1") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("AtdDcto1") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AtdData2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AtdQtde2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AtdDcto2")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SituacaoPedido") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Ipi") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Dctoprom") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("EconVlr") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EconCalc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EconDcto") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("PrecoMin") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Tipo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Dctovda") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TipoPreco")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EstVlr") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Acrescimo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Lpreco") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Grade") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("TpGrade") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CodEtiq") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Embalagem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("MotCanc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PercComis") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("PrdCfgCar") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Observacao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("BonifEcon") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PcomisExt")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AplicDcto") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Operacao") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QtdeRes") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Bloqsug") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Dctoinc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VlrIpi") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SeqGrd") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SeqErp") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrevCalc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Almoxarif") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ComisGer") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CredProp") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("NroEnvio") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Chave")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ItemConf") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("CvdaDcti") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ObsNota") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ObsExped") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ObsSepar")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VlrSubst") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DesctoGer") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("VlrFreTit") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("MotTroca") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VeconCig")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CustoMp") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PliqAlt") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CodPrMap") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodCvdAg")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VuIcms") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("VuPis") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VuCofins") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Altdtprev")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("QtdeAloc") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("RestrBonif") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CustoMo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ItControl") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ItPermBon")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("QtdPermBon") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("VlrPermBon") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Itemfemb") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Dtprevento") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Cfretit")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SldVerba") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("VlrBonif") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Bonif") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Tpproduto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Backord")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Markup") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QtdeBkor") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Prcpromoc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Sitcorp1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Sitcorp2") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("LibMots") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Dctoboleto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("MPRISeq") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Dctomax")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Comprim") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QtdePeca") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ProdRes") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EmpEstoq")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrevProduc") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("PrunitOri") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PrliqOri") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Mva") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ICMSOrig") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ICMSDest") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Float1") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Float2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataAlter")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SeqOrig") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PrecoNorm") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Tipost") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodProdcli")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VlrDescarga") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("IcmsDscto") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("EmpSplit") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Status") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Obrigatorio")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrecoFirme") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("OrdCompra") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DesctoFiscal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PercMlucr") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VlrDescargaCalc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VlrFreTitCalc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VuComissao")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VuDebcli") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Data1Original") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Visao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Periodo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BaseIcms")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BaseSt") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CondPgto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("UsuCanc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Resrede")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DocBloq") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Regracomis") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("BaseIcmsRed") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("IcmsSemRed")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Obs1") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Obs2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CodEmbal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CondPgtoCalc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodigoMix") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("QtdeMix") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Lote") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VlrDesctoFiscal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Tamanho")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BloqVerba") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("VlrFreTitRateio") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("VlrFreTitRateioCalc") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("VisaoCota") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PeriodoCota") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VisaoCotaOriginal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PeriodoCotaOriginal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("RegraEmpItem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjPeca")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjTamanho") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("LjCarga") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("LjMedidaA") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("LjVao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjAngulo")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjMedidaB") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("LjInicioViga") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("LjAba") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("LjAbaA") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjAbaB")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Bloqluc") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("OrigemCota") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("OrigemCanc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Incsug")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LjIdent") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CondFreteIt") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DesctotalCalc") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("IdPedAuto")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LoteEstoque") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DesctotalAplicado") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SeqOrdemCompra") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SeqordCompra") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Ordcomest") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ValorICMSFrete") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Campo1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CentroCusto") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("BloqDesc") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("QtdeDescBonif") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PercDescBonif") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorDesccUnit") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SeqCota") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SeqCotaOriginal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DtRegraCorte") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CompoeMargem")
									+ "}]");
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
					talendJobLog.addCM("tDBInput_1", "Mercanet_Producao", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:+/1DDZV4wsCjU7DsSz6SRoUMcNFpz8k3H1CBVwEoKCU9NTM9M9be6XLV");

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

				String dbquery_tDBInput_1 = "SELECT \n  DB_PEDI_PEDIDO AS NumeroPedido,\n  DB_PEDI_SEQUENCIA AS SequenciaItem,\n  DB_PEDI_PRODUTO AS CodigoProduto,"
						+ "\n  DB_PEDI_QTDE_SOLIC AS QuantidadeProduto,\n  DB_PEDI_QTDE_ATEND AS QuantidadeAtendida,\n  DB_PEDI_QTDE_CANC AS Quantid"
						+ "adeCancelada,\n  DB_PEDI_PRECO_UNIT AS PrecoUnitario,\n  DB_PEDI_PRECO_LIQ AS PrecoLiquido,\n  DB_PEDI_DESCTOP AS Descto"
						+ "p,\n  DB_PEDI_DESCTO_ICM AS DesctoIcm,\n  CAST(DB_PEDI_PREV_ENTR AS DATE) AS PrevEntr,\n  CAST(DB_PEDI_ATD_DATA AS DATE)"
						+ " AS AtdData,\n  DB_PEDI_ATD_QTDE AS AtdQtde,\n  DB_PEDI_ATD_DCTO AS AtdDcto,\n  DB_PEDI_ATD_DATA1 AS AtdData1,\n  DB_PED"
						+ "I_ATD_QTDE1 AS AtdQtde1,\n  DB_PEDI_ATD_DCTO1 AS AtdDcto1,\n  DB_PEDI_ATD_DATA2 AS AtdData2,\n  DB_PEDI_ATD_QTDE2 AS Atd"
						+ "Qtde2,\n  DB_PEDI_ATD_DCTO2 AS AtdDcto2,\n  DB_PEDI_SITUACAO AS SituacaoPedido,\n  DB_PEDI_IPI AS Ipi,\n  DB_PEDI_DCTOPR"
						+ "OM AS Dctoprom,\n  DB_PEDI_ECON_VLR AS EconVlr,\n  DB_PEDI_ECON_CALC AS EconCalc,\n  DB_PEDI_ECON_DCTO AS EconDcto,\n  D"
						+ "B_PEDI_PRECO_MIN AS PrecoMin,\n  DB_PEDI_TIPO AS Tipo,\n  DB_PEDI_DCTOVDA AS Dctovda,\n  DB_PEDI_TIPO_PRECO AS TipoPreco"
						+ ",\n  DB_PEDI_ESTVLR AS EstVlr,\n  DB_PEDI_ACRESCIMO AS Acrescimo,\n  DB_PEDI_LPRECO AS Lpreco,\n  DB_PEDI_GRADE AS Grade"
						+ ",\n  DB_PEDI_TP_GRADE AS TpGrade,\n  DB_PEDI_COD_ETIQ AS CodEtiq,\n  DB_PEDI_EMBALAGEM AS Embalagem,\n  DB_PEDI_MOTCANC "
						+ "AS MotCanc,\n  DB_PEDI_PERC_COMIS AS PercComis,\n  DB_PEDI_PRDCFGCAR AS PrdCfgCar,\n  DB_PEDI_OBSERV AS Observacao,\n  D"
						+ "B_PEDI_BONIFECON AS BonifEcon,\n  DB_PEDI_PCOMIS_EXT AS PcomisExt,\n  DB_PEDI_APLICDCTO AS AplicDcto,\n  DB_PEDI_OPERACA"
						+ "O AS Operacao,\n  DB_PEDI_QTDE_RES AS QtdeRes,\n  DB_PEDI_BLOQSUG AS Bloqsug,\n  DB_PEDI_DCTOINC AS Dctoinc,\n  DB_PEDI_"
						+ "VLRIPI AS VlrIpi,\n  DB_PEDI_SEQGRD AS SeqGrd,\n  DB_PEDI_SEQ_ERP AS SeqErp,\n  DB_PEDI_PREV_CALC AS PrevCalc,\n  DB_PED"
						+ "I_ALMOXARIF AS Almoxarif,\n  DB_PEDI_COMIS_GER AS ComisGer,\n  DB_PEDI_CRED_PROP AS CredProp,\n  DB_PEDI_NRO_ENVIO AS Nr"
						+ "oEnvio,\n  DB_PEDI_CHAVE AS Chave,\n  DB_PEDI_ITEMCONF AS ItemConf,\n  DB_PEDI_CVDA_DCTI AS CvdaDcti,\n  DB_PEDI_OBS_NOT"
						+ "A AS ObsNota,\n  DB_PEDI_OBS_EXPED AS ObsExped,\n  DB_PEDI_OBS_SEPAR AS ObsSepar,\n  DB_PEDI_VLR_SUBST AS VlrSubst,\n  D"
						+ "B_PEDI_DESCTO_GER AS DesctoGer,\n  DB_PEDI_VLR_FRETIT AS VlrFreTit,\n  DB_PEDI_MOT_TROCA AS MotTroca,\n  DB_PEDI_VECON_C"
						+ "IG AS VeconCig,\n  DB_PEDI_CUSTO_MP AS CustoMp,\n  DB_PEDI_PLIQ_ALT AS PliqAlt,\n  DB_PEDI_CODPRMAP AS CodPrMap,\n  DB_P"
						+ "EDI_CODCVDAG AS CodCvdAg,\n  DB_PEDI_VU_ICMS AS VuIcms,\n  DB_PEDI_VU_PIS AS VuPis,\n  DB_PEDI_VU_COFINS AS VuCofins,\n "
						+ " DB_PEDI_ALTDTPREV AS Altdtprev,\n  DB_PEDI_QTDE_ALOC AS QtdeAloc,\n  DB_PEDI_RESTRBONIF AS RestrBonif,\n  DB_PEDI_CUSTO"
						+ "_MO AS CustoMo,\n  DB_PEDI_ITCONTROL AS ItControl,\n  DB_PEDI_ITPERMBON AS ItPermBon,\n  DB_PEDI_QTDPERMBON AS QtdPermBo"
						+ "n,\n  DB_PEDI_VLRPERMBON AS VlrPermBon,\n  DB_PEDI_ITEMFEMB AS Itemfemb,\n  CAST(DB_PEDI_DTPREVENTO AS DATE) AS Dtpreven"
						+ "to,\n  DB_PEDI_CFRETIT AS Cfretit,\n  DB_PEDI_SLDVERBA AS SldVerba,\n  DB_PEDI_VLRBONIF AS VlrBonif,\n  DB_PEDI_BONIF AS"
						+ " Bonif,\n  DB_PEDI_TPPRODUTO AS Tpproduto,\n  DB_PEDI_BACKORD AS Backord,\n  DB_PEDI_MARKUP AS Markup,\n  DB_PEDI_QTDE_B"
						+ "KOR AS QtdeBkor,\n  DB_PEDI_PRCPROMOC AS Prcpromoc,\n  DB_PEDI_SITCORP1 AS Sitcorp1,\n  DB_PEDI_SITCORP2 AS Sitcorp2,\n "
						+ " DB_PEDI_LIB_MOTS AS LibMots,\n  DB_PEDI_DCTOBOLETO AS Dctoboleto,\n  DB_PedI_MPRISeq AS MPRISeq,\n  DB_PEDI_DCTOMAX AS "
						+ "Dctomax,\n  DB_PedI_Comprim AS Comprim,\n  DB_PedI_QtdePeca AS QtdePeca,\n  DB_PedI_Prod_Res AS ProdRes,\n  DB_PEDI_EMPE"
						+ "STOQ AS EmpEstoq,\n  DB_Pedi_PrevProduc AS PrevProduc,\n  DB_PEDI_PRUNIT_ORI AS PrunitOri,\n  DB_PEDI_PRLIQ_ORI AS Prliq"
						+ "Ori,\n  Db_PedI_MVA AS Mva,\n  DB_PedI_ICMSOrig AS ICMSOrig,\n  Db_PedI_ICMSDest AS ICMSDest,\n  DB_PEDI_FLOAT1 AS Float"
						+ "1,\n  DB_PEDI_FLOAT2 AS Float2,\n  DB_PEDI_DATA_ALTER AS DataAlter,\n  DB_PEDI_SEQ_ORIG AS SeqOrig,\n  DB_PEDI_PRECO_NOR"
						+ "M AS PrecoNorm,\n  DB_PEDI_TIPOST AS Tipost,\n  DB_PEDI_COD_PRODCLI AS CodProdcli,\n  DB_PEDI_VLR_DESCARGA AS VlrDescarg"
						+ "a,\n  DB_PEDI_IcmsDscto AS IcmsDscto,\n  DB_PEDI_EmpSplit AS EmpSplit,\n  db_pedi_status AS Status,\n  DB_PEDI_OBRIGATOR"
						+ "IO AS Obrigatorio,\n  DB_PEDI_PRECO_FIRME AS PrecoFirme,\n  DB_PEDI_ORD_COMPRA AS OrdCompra,\n  DB_PEDI_DESCTO_FISCAL AS"
						+ " DesctoFiscal,\n  DB_PEDI_PERC_MLUCR AS PercMlucr,\n  DB_PEDI_VLR_DESCARGA_CALC AS VlrDescargaCalc,\n  DB_PEDI_VLR_FRETI"
						+ "T_CALC AS VlrFreTitCalc,\n  DB_PEDI_VU_COMISSAO AS VuComissao,\n  DB_PEDI_VU_DEBCLI AS VuDebcli,\n  CAST(DB_PEDI_DATA1_O"
						+ "RIGINAL AS DATE) AS Data1Original,\n  DB_PEDI_VISAO AS Visao,\n  DB_PEDI_PERIODO AS Periodo,\n  DB_PEDI_BASEICMS AS Base"
						+ "Icms,\n  DB_PEDI_BASEST AS BaseSt,\n  DB_PEDI_COND_PGTO AS CondPgto,\n  DB_PEDI_USU_CANC AS UsuCanc,\n  DB_PEDI_RESREDE "
						+ "AS Resrede,\n  DB_PEDI_DOC_BLOQ AS DocBloq,\n  DB_PEDI_REGRACOMIS AS Regracomis,\n  DB_PEDI_BASEICMSRED AS BaseIcmsRed,"
						+ "\n  DB_PEDI_ICMSSEMRED AS IcmsSemRed,\n  DB_PEDI_OBS1 AS Obs1,\n  DB_PEDI_OBS2 AS Obs2,\n  DB_PEDI_CODEMBAL AS CodEmbal,"
						+ "\n  DB_PEDI_COND_PGTOCALC AS CondPgtoCalc,\n  DB_PEDI_CODIGO_MIX AS CodigoMix,\n  DB_PEDI_QTDE_MIX AS QtdeMix,\n  DB_PEDI"
						+ "_LOTE AS Lote,\n  DB_PEDI_VLR_DESCTO_FISCAL AS VlrDesctoFiscal,\n  DB_PEDI_TAMANHO AS Tamanho,\n  DB_PEDI_BLOQVERBA AS B"
						+ "loqVerba,\n  DB_PEDI_VLRFRETIT_RATEIO AS VlrFreTitRateio,\n  DB_PEDI_VLRFRETIT_RATEIO_CALC AS VlrFreTitRateioCalc,\n  DB"
						+ "_PEDI_VISAO_COTA AS VisaoCota,\n  DB_PEDI_PERIODO_COTA AS PeriodoCota,\n  DB_PEDI_VISAO_COTA_ORIGINAL AS VisaoCotaOrigin"
						+ "al,\n  DB_PEDI_PERIODO_COTA_ORIGINAL AS PeriodoCotaOriginal,\n  DB_PEDI_REGRAEMPITEM AS RegraEmpItem,\n  DB_PEDI_LJ_PECA"
						+ " AS LjPeca,\n  DB_PEDI_LJ_TAMANHO AS LjTamanho,\n  DB_PEDI_LJ_CARGA AS LjCarga,\n  DB_PEDI_LJ_MEDIDA_A AS LjMedidaA,\n  "
						+ "DB_PEDI_LJ_VAO AS LjVao,\n  DB_PEDI_LJ_ANGULO AS LjAngulo,\n  DB_PEDI_LJ_MEDIDA_B AS LjMedidaB,\n  DB_PEDI_LJ_INICIOVIGA"
						+ " AS LjInicioViga,\n  DB_PEDI_LJ_ABA AS LjAba,\n  DB_PEDI_LJ_ABA_A AS LjAbaA,\n  DB_PEDI_LJ_ABA_B AS LjAbaB,\n  DB_PEDI_B"
						+ "LOQLUC AS Bloqluc,\n  DB_PEDI_ORIGEM_COTA AS OrigemCota,\n  DB_PEDI_ORIGEM_CANC AS OrigemCanc,\n  DB_PEDI_INCSUG AS Incs"
						+ "ug,\n  DB_PEDI_LJ_IDENT AS LjIdent,\n  DB_PEDI_COND_FRETE_IT AS CondFreteIt,\n  DB_PEDI_DESCTOTAL_CALC AS DesctotalCalc,"
						+ "\n  DB_PEDI_ID_PED_AUTO AS IdPedAuto,\n  DB_PEDI_LOTE_ESTOQUE AS LoteEstoque,\n  DB_PEDI_DESCTOTAL_APLICADO AS Desctotal"
						+ "Aplicado,\n  DB_PEDI_SEQ_ORDEM_COMPRA AS SeqOrdemCompra,\n  DB_PEDI_SEQORD_COMPRA AS SeqordCompra,\n  DB_PEDI_ORDCOMEST "
						+ "AS Ordcomest,\n  DB_PEDI_VICMSFRETE AS ValorICMSFrete,\n  DB_PEDI_CAMPO1 AS Campo1,\n  DB_PEDI_CENTROCUSTO AS CentroCust"
						+ "o,\n  DB_PEDI_BLOQDESC AS BloqDesc,\n  DB_PEDI_QTDE_DESCBONIF AS QtdeDescBonif,\n  DB_PEDI_PERC_DESCBONIF AS PercDescBon"
						+ "if,\n  DB_PEDI_VALORDESCC_UNIT AS ValorDesccUnit,\n  DB_PEDI_SEQ_COTA AS SeqCota,\n  DB_PEDI_SEQ_COTA_ORIGINAL AS SeqCot"
						+ "aOriginal,\n  DB_PEDI_DT_REGRA_CORTE AS DtRegraCorte,\n  DB_PEDI_COMPOEMARGEM AS CompoeMargem\nFROM DB_PEDIDO_PROD\nWHER"
						+ "E DB_PEDI_PREV_ENTR >= GETDATE()-150;";

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
							row1.NumeroPedido = null;
						} else {

							row1.NumeroPedido = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								row1.NumeroPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.SequenciaItem = null;
						} else {

							row1.SequenciaItem = rs_tDBInput_1.getInt(2);
							if (rs_tDBInput_1.wasNull()) {
								row1.SequenciaItem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.CodigoProduto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoProduto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoProduto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.QuantidadeProduto = null;
						} else {

							row1.QuantidadeProduto = rs_tDBInput_1.getFloat(4);
							if (rs_tDBInput_1.wasNull()) {
								row1.QuantidadeProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.QuantidadeAtendida = null;
						} else {

							row1.QuantidadeAtendida = rs_tDBInput_1.getFloat(5);
							if (rs_tDBInput_1.wasNull()) {
								row1.QuantidadeAtendida = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.QuantidadeCancelada = null;
						} else {

							row1.QuantidadeCancelada = rs_tDBInput_1.getFloat(6);
							if (rs_tDBInput_1.wasNull()) {
								row1.QuantidadeCancelada = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.PrecoUnitario = null;
						} else {

							row1.PrecoUnitario = rs_tDBInput_1.getFloat(7);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoUnitario = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.PrecoLiquido = null;
						} else {

							row1.PrecoLiquido = rs_tDBInput_1.getFloat(8);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoLiquido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.Desctop = null;
						} else {

							row1.Desctop = rs_tDBInput_1.getFloat(9);
							if (rs_tDBInput_1.wasNull()) {
								row1.Desctop = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.DesctoIcm = null;
						} else {

							row1.DesctoIcm = rs_tDBInput_1.getDouble(10);
							if (rs_tDBInput_1.wasNull()) {
								row1.DesctoIcm = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.PrevEntr = null;
						} else {

							row1.PrevEntr = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 11);

						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.AtdData = null;
						} else {

							row1.AtdData = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 12);

						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.AtdQtde = null;
						} else {

							row1.AtdQtde = rs_tDBInput_1.getFloat(13);
							if (rs_tDBInput_1.wasNull()) {
								row1.AtdQtde = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.AtdDcto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.AtdDcto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.AtdDcto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.AtdDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.AtdData1 = null;
						} else {

							row1.AtdData1 = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 15);

						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.AtdQtde1 = null;
						} else {

							row1.AtdQtde1 = rs_tDBInput_1.getFloat(16);
							if (rs_tDBInput_1.wasNull()) {
								row1.AtdQtde1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.AtdDcto1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.AtdDcto1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.AtdDcto1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.AtdDcto1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.AtdData2 = null;
						} else {

							row1.AtdData2 = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 18);

						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.AtdQtde2 = null;
						} else {

							row1.AtdQtde2 = rs_tDBInput_1.getFloat(19);
							if (rs_tDBInput_1.wasNull()) {
								row1.AtdQtde2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.AtdDcto2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.AtdDcto2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.AtdDcto2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.AtdDcto2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.SituacaoPedido = null;
						} else {

							row1.SituacaoPedido = rs_tDBInput_1.getShort(21);
							if (rs_tDBInput_1.wasNull()) {
								row1.SituacaoPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.Ipi = null;
						} else {

							row1.Ipi = rs_tDBInput_1.getDouble(22);
							if (rs_tDBInput_1.wasNull()) {
								row1.Ipi = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.Dctoprom = null;
						} else {

							row1.Dctoprom = rs_tDBInput_1.getFloat(23);
							if (rs_tDBInput_1.wasNull()) {
								row1.Dctoprom = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.EconVlr = null;
						} else {

							row1.EconVlr = rs_tDBInput_1.getFloat(24);
							if (rs_tDBInput_1.wasNull()) {
								row1.EconVlr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.EconCalc = null;
						} else {

							row1.EconCalc = rs_tDBInput_1.getShort(25);
							if (rs_tDBInput_1.wasNull()) {
								row1.EconCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.EconDcto = null;
						} else {

							row1.EconDcto = rs_tDBInput_1.getFloat(26);
							if (rs_tDBInput_1.wasNull()) {
								row1.EconDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.PrecoMin = null;
						} else {

							row1.PrecoMin = rs_tDBInput_1.getFloat(27);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoMin = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.Tipo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Tipo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Tipo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Tipo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.Dctovda = null;
						} else {

							row1.Dctovda = rs_tDBInput_1.getDouble(29);
							if (rs_tDBInput_1.wasNull()) {
								row1.Dctovda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.TipoPreco = null;
						} else {

							row1.TipoPreco = rs_tDBInput_1.getShort(30);
							if (rs_tDBInput_1.wasNull()) {
								row1.TipoPreco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							row1.EstVlr = null;
						} else {

							row1.EstVlr = rs_tDBInput_1.getShort(31);
							if (rs_tDBInput_1.wasNull()) {
								row1.EstVlr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							row1.Acrescimo = null;
						} else {

							row1.Acrescimo = rs_tDBInput_1.getDouble(32);
							if (rs_tDBInput_1.wasNull()) {
								row1.Acrescimo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							row1.Lpreco = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(33);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Lpreco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Lpreco = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Lpreco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							row1.Grade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Grade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Grade = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Grade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							row1.TpGrade = null;
						} else {

							row1.TpGrade = rs_tDBInput_1.getShort(35);
							if (rs_tDBInput_1.wasNull()) {
								row1.TpGrade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							row1.CodEtiq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(36);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodEtiq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodEtiq = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodEtiq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							row1.Embalagem = null;
						} else {

							row1.Embalagem = rs_tDBInput_1.getDouble(37);
							if (rs_tDBInput_1.wasNull()) {
								row1.Embalagem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							row1.MotCanc = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.MotCanc = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.MotCanc = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.MotCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 39) {
							row1.PercComis = null;
						} else {

							row1.PercComis = rs_tDBInput_1.getFloat(39);
							if (rs_tDBInput_1.wasNull()) {
								row1.PercComis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 40) {
							row1.PrdCfgCar = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(40);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(40).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PrdCfgCar = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PrdCfgCar = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.PrdCfgCar = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							row1.Observacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(41);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(41).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Observacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Observacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Observacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 42) {
							row1.BonifEcon = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(42);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(42).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.BonifEcon = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.BonifEcon = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.BonifEcon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 43) {
							row1.PcomisExt = null;
						} else {

							row1.PcomisExt = rs_tDBInput_1.getDouble(43);
							if (rs_tDBInput_1.wasNull()) {
								row1.PcomisExt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 44) {
							row1.AplicDcto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(44);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(44).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.AplicDcto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.AplicDcto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.AplicDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 45) {
							row1.Operacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(45);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(45).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Operacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Operacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Operacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 46) {
							row1.QtdeRes = null;
						} else {

							row1.QtdeRes = rs_tDBInput_1.getDouble(46);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdeRes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 47) {
							row1.Bloqsug = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(47);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(47).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Bloqsug = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Bloqsug = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Bloqsug = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 48) {
							row1.Dctoinc = null;
						} else {

							row1.Dctoinc = rs_tDBInput_1.getFloat(48);
							if (rs_tDBInput_1.wasNull()) {
								row1.Dctoinc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 49) {
							row1.VlrIpi = null;
						} else {

							row1.VlrIpi = rs_tDBInput_1.getDouble(49);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrIpi = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 50) {
							row1.SeqGrd = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(50);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(50).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SeqGrd = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SeqGrd = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SeqGrd = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 51) {
							row1.SeqErp = null;
						} else {

							row1.SeqErp = rs_tDBInput_1.getInt(51);
							if (rs_tDBInput_1.wasNull()) {
								row1.SeqErp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 52) {
							row1.PrevCalc = null;
						} else {

							row1.PrevCalc = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 52);

						}
						if (colQtyInRs_tDBInput_1 < 53) {
							row1.Almoxarif = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(53);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Almoxarif = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Almoxarif = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Almoxarif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 54) {
							row1.ComisGer = null;
						} else {

							row1.ComisGer = rs_tDBInput_1.getDouble(54);
							if (rs_tDBInput_1.wasNull()) {
								row1.ComisGer = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 55) {
							row1.CredProp = null;
						} else {

							row1.CredProp = rs_tDBInput_1.getDouble(55);
							if (rs_tDBInput_1.wasNull()) {
								row1.CredProp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 56) {
							row1.NroEnvio = null;
						} else {

							row1.NroEnvio = rs_tDBInput_1.getDouble(56);
							if (rs_tDBInput_1.wasNull()) {
								row1.NroEnvio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 57) {
							row1.Chave = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(57);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(57).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Chave = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Chave = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Chave = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 58) {
							row1.ItemConf = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(58);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(58).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ItemConf = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ItemConf = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ItemConf = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 59) {
							row1.CvdaDcti = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(59);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(59).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CvdaDcti = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CvdaDcti = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CvdaDcti = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 60) {
							row1.ObsNota = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(60);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(60).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ObsNota = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ObsNota = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ObsNota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 61) {
							row1.ObsExped = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(61);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(61).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ObsExped = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ObsExped = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ObsExped = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 62) {
							row1.ObsSepar = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(62);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(62).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ObsSepar = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ObsSepar = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ObsSepar = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 63) {
							row1.VlrSubst = null;
						} else {

							row1.VlrSubst = rs_tDBInput_1.getDouble(63);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrSubst = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 64) {
							row1.DesctoGer = null;
						} else {

							row1.DesctoGer = rs_tDBInput_1.getDouble(64);
							if (rs_tDBInput_1.wasNull()) {
								row1.DesctoGer = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 65) {
							row1.VlrFreTit = null;
						} else {

							row1.VlrFreTit = rs_tDBInput_1.getDouble(65);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrFreTit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 66) {
							row1.MotTroca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(66);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(66).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.MotTroca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.MotTroca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.MotTroca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 67) {
							row1.VeconCig = null;
						} else {

							row1.VeconCig = rs_tDBInput_1.getDouble(67);
							if (rs_tDBInput_1.wasNull()) {
								row1.VeconCig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 68) {
							row1.CustoMp = null;
						} else {

							row1.CustoMp = rs_tDBInput_1.getDouble(68);
							if (rs_tDBInput_1.wasNull()) {
								row1.CustoMp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 69) {
							row1.PliqAlt = null;
						} else {

							row1.PliqAlt = rs_tDBInput_1.getShort(69);
							if (rs_tDBInput_1.wasNull()) {
								row1.PliqAlt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 70) {
							row1.CodPrMap = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(70);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(70).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodPrMap = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodPrMap = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodPrMap = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 71) {
							row1.CodCvdAg = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(71);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(71).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodCvdAg = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodCvdAg = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodCvdAg = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 72) {
							row1.VuIcms = null;
						} else {

							row1.VuIcms = rs_tDBInput_1.getDouble(72);
							if (rs_tDBInput_1.wasNull()) {
								row1.VuIcms = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 73) {
							row1.VuPis = null;
						} else {

							row1.VuPis = rs_tDBInput_1.getDouble(73);
							if (rs_tDBInput_1.wasNull()) {
								row1.VuPis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 74) {
							row1.VuCofins = null;
						} else {

							row1.VuCofins = rs_tDBInput_1.getDouble(74);
							if (rs_tDBInput_1.wasNull()) {
								row1.VuCofins = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 75) {
							row1.Altdtprev = null;
						} else {

							row1.Altdtprev = rs_tDBInput_1.getShort(75);
							if (rs_tDBInput_1.wasNull()) {
								row1.Altdtprev = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 76) {
							row1.QtdeAloc = null;
						} else {

							row1.QtdeAloc = rs_tDBInput_1.getDouble(76);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdeAloc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 77) {
							row1.RestrBonif = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(77);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(77).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RestrBonif = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RestrBonif = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RestrBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 78) {
							row1.CustoMo = null;
						} else {

							row1.CustoMo = rs_tDBInput_1.getDouble(78);
							if (rs_tDBInput_1.wasNull()) {
								row1.CustoMo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 79) {
							row1.ItControl = null;
						} else {

							row1.ItControl = rs_tDBInput_1.getShort(79);
							if (rs_tDBInput_1.wasNull()) {
								row1.ItControl = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 80) {
							row1.ItPermBon = null;
						} else {

							row1.ItPermBon = rs_tDBInput_1.getShort(80);
							if (rs_tDBInput_1.wasNull()) {
								row1.ItPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 81) {
							row1.QtdPermBon = null;
						} else {

							row1.QtdPermBon = rs_tDBInput_1.getDouble(81);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 82) {
							row1.VlrPermBon = null;
						} else {

							row1.VlrPermBon = rs_tDBInput_1.getDouble(82);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 83) {
							row1.Itemfemb = null;
						} else {

							row1.Itemfemb = rs_tDBInput_1.getShort(83);
							if (rs_tDBInput_1.wasNull()) {
								row1.Itemfemb = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 84) {
							row1.Dtprevento = null;
						} else {

							row1.Dtprevento = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 84);

						}
						if (colQtyInRs_tDBInput_1 < 85) {
							row1.Cfretit = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(85);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(85).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Cfretit = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Cfretit = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Cfretit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 86) {
							row1.SldVerba = null;
						} else {

							row1.SldVerba = rs_tDBInput_1.getDouble(86);
							if (rs_tDBInput_1.wasNull()) {
								row1.SldVerba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 87) {
							row1.VlrBonif = null;
						} else {

							row1.VlrBonif = rs_tDBInput_1.getDouble(87);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 88) {
							row1.Bonif = null;
						} else {

							row1.Bonif = rs_tDBInput_1.getShort(88);
							if (rs_tDBInput_1.wasNull()) {
								row1.Bonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 89) {
							row1.Tpproduto = null;
						} else {

							row1.Tpproduto = rs_tDBInput_1.getShort(89);
							if (rs_tDBInput_1.wasNull()) {
								row1.Tpproduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 90) {
							row1.Backord = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(90);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(90).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Backord = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Backord = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Backord = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 91) {
							row1.Markup = null;
						} else {

							row1.Markup = rs_tDBInput_1.getDouble(91);
							if (rs_tDBInput_1.wasNull()) {
								row1.Markup = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 92) {
							row1.QtdeBkor = null;
						} else {

							row1.QtdeBkor = rs_tDBInput_1.getDouble(92);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdeBkor = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 93) {
							row1.Prcpromoc = null;
						} else {

							row1.Prcpromoc = rs_tDBInput_1.getDouble(93);
							if (rs_tDBInput_1.wasNull()) {
								row1.Prcpromoc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 94) {
							row1.Sitcorp1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(94);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(94).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Sitcorp1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Sitcorp1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Sitcorp1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 95) {
							row1.Sitcorp2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(95);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(95).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Sitcorp2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Sitcorp2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Sitcorp2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 96) {
							row1.LibMots = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(96);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(96).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.LibMots = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.LibMots = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.LibMots = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 97) {
							row1.Dctoboleto = null;
						} else {

							row1.Dctoboleto = rs_tDBInput_1.getDouble(97);
							if (rs_tDBInput_1.wasNull()) {
								row1.Dctoboleto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 98) {
							row1.MPRISeq = null;
						} else {

							row1.MPRISeq = rs_tDBInput_1.getInt(98);
							if (rs_tDBInput_1.wasNull()) {
								row1.MPRISeq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 99) {
							row1.Dctomax = null;
						} else {

							row1.Dctomax = rs_tDBInput_1.getDouble(99);
							if (rs_tDBInput_1.wasNull()) {
								row1.Dctomax = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 100) {
							row1.Comprim = null;
						} else {

							row1.Comprim = rs_tDBInput_1.getDouble(100);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comprim = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 101) {
							row1.QtdePeca = null;
						} else {

							row1.QtdePeca = rs_tDBInput_1.getDouble(101);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdePeca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 102) {
							row1.ProdRes = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(102);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(102).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ProdRes = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ProdRes = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ProdRes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 103) {
							row1.EmpEstoq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(103);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(103).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EmpEstoq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EmpEstoq = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EmpEstoq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 104) {
							row1.PrevProduc = null;
						} else {

							row1.PrevProduc = rs_tDBInput_1.getDouble(104);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrevProduc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 105) {
							row1.PrunitOri = null;
						} else {

							row1.PrunitOri = rs_tDBInput_1.getFloat(105);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrunitOri = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 106) {
							row1.PrliqOri = null;
						} else {

							row1.PrliqOri = rs_tDBInput_1.getFloat(106);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrliqOri = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 107) {
							row1.Mva = null;
						} else {

							row1.Mva = rs_tDBInput_1.getDouble(107);
							if (rs_tDBInput_1.wasNull()) {
								row1.Mva = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 108) {
							row1.ICMSOrig = null;
						} else {

							row1.ICMSOrig = rs_tDBInput_1.getDouble(108);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMSOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 109) {
							row1.ICMSDest = null;
						} else {

							row1.ICMSDest = rs_tDBInput_1.getDouble(109);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMSDest = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 110) {
							row1.Float1 = null;
						} else {

							row1.Float1 = rs_tDBInput_1.getDouble(110);
							if (rs_tDBInput_1.wasNull()) {
								row1.Float1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 111) {
							row1.Float2 = null;
						} else {

							row1.Float2 = rs_tDBInput_1.getDouble(111);
							if (rs_tDBInput_1.wasNull()) {
								row1.Float2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 112) {
							row1.DataAlter = null;
						} else {

							row1.DataAlter = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 112);

						}
						if (colQtyInRs_tDBInput_1 < 113) {
							row1.SeqOrig = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(113);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(113).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SeqOrig = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SeqOrig = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SeqOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 114) {
							row1.PrecoNorm = null;
						} else {

							row1.PrecoNorm = rs_tDBInput_1.getDouble(114);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoNorm = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 115) {
							row1.Tipost = null;
						} else {

							row1.Tipost = rs_tDBInput_1.getShort(115);
							if (rs_tDBInput_1.wasNull()) {
								row1.Tipost = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 116) {
							row1.CodProdcli = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(116);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(116).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodProdcli = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodProdcli = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodProdcli = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 117) {
							row1.VlrDescarga = null;
						} else {

							row1.VlrDescarga = rs_tDBInput_1.getDouble(117);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrDescarga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 118) {
							row1.IcmsDscto = null;
						} else {

							row1.IcmsDscto = rs_tDBInput_1.getShort(118);
							if (rs_tDBInput_1.wasNull()) {
								row1.IcmsDscto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 119) {
							row1.EmpSplit = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(119);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(119).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EmpSplit = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EmpSplit = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EmpSplit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 120) {
							row1.Status = null;
						} else {

							row1.Status = rs_tDBInput_1.getInt(120);
							if (rs_tDBInput_1.wasNull()) {
								row1.Status = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 121) {
							row1.Obrigatorio = null;
						} else {

							row1.Obrigatorio = rs_tDBInput_1.getInt(121);
							if (rs_tDBInput_1.wasNull()) {
								row1.Obrigatorio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 122) {
							row1.PrecoFirme = null;
						} else {

							row1.PrecoFirme = rs_tDBInput_1.getInt(122);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoFirme = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 123) {
							row1.OrdCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(123);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(123).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.OrdCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.OrdCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.OrdCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 124) {
							row1.DesctoFiscal = null;
						} else {

							row1.DesctoFiscal = rs_tDBInput_1.getDouble(124);
							if (rs_tDBInput_1.wasNull()) {
								row1.DesctoFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 125) {
							row1.PercMlucr = null;
						} else {

							row1.PercMlucr = rs_tDBInput_1.getDouble(125);
							if (rs_tDBInput_1.wasNull()) {
								row1.PercMlucr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 126) {
							row1.VlrDescargaCalc = null;
						} else {

							row1.VlrDescargaCalc = rs_tDBInput_1.getDouble(126);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrDescargaCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 127) {
							row1.VlrFreTitCalc = null;
						} else {

							row1.VlrFreTitCalc = rs_tDBInput_1.getDouble(127);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrFreTitCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 128) {
							row1.VuComissao = null;
						} else {

							row1.VuComissao = rs_tDBInput_1.getDouble(128);
							if (rs_tDBInput_1.wasNull()) {
								row1.VuComissao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 129) {
							row1.VuDebcli = null;
						} else {

							row1.VuDebcli = rs_tDBInput_1.getDouble(129);
							if (rs_tDBInput_1.wasNull()) {
								row1.VuDebcli = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 130) {
							row1.Data1Original = null;
						} else {

							row1.Data1Original = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 130);

						}
						if (colQtyInRs_tDBInput_1 < 131) {
							row1.Visao = null;
						} else {

							row1.Visao = rs_tDBInput_1.getInt(131);
							if (rs_tDBInput_1.wasNull()) {
								row1.Visao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 132) {
							row1.Periodo = null;
						} else {

							row1.Periodo = rs_tDBInput_1.getInt(132);
							if (rs_tDBInput_1.wasNull()) {
								row1.Periodo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 133) {
							row1.BaseIcms = null;
						} else {

							row1.BaseIcms = rs_tDBInput_1.getDouble(133);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseIcms = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 134) {
							row1.BaseSt = null;
						} else {

							row1.BaseSt = rs_tDBInput_1.getDouble(134);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseSt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 135) {
							row1.CondPgto = null;
						} else {

							row1.CondPgto = rs_tDBInput_1.getInt(135);
							if (rs_tDBInput_1.wasNull()) {
								row1.CondPgto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 136) {
							row1.UsuCanc = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(136);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(136).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UsuCanc = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UsuCanc = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UsuCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 137) {
							row1.Resrede = null;
						} else {

							row1.Resrede = rs_tDBInput_1.getDouble(137);
							if (rs_tDBInput_1.wasNull()) {
								row1.Resrede = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 138) {
							row1.DocBloq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(138);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(138).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DocBloq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DocBloq = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DocBloq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 139) {
							row1.Regracomis = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(139);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(139).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Regracomis = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Regracomis = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Regracomis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 140) {
							row1.BaseIcmsRed = null;
						} else {

							row1.BaseIcmsRed = rs_tDBInput_1.getDouble(140);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseIcmsRed = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 141) {
							row1.IcmsSemRed = null;
						} else {

							row1.IcmsSemRed = rs_tDBInput_1.getDouble(141);
							if (rs_tDBInput_1.wasNull()) {
								row1.IcmsSemRed = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 142) {
							row1.Obs1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(142);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(142).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Obs1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Obs1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Obs1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 143) {
							row1.Obs2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(143);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(143).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Obs2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Obs2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Obs2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 144) {
							row1.CodEmbal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(144);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(144).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodEmbal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodEmbal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodEmbal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 145) {
							row1.CondPgtoCalc = null;
						} else {

							row1.CondPgtoCalc = rs_tDBInput_1.getInt(145);
							if (rs_tDBInput_1.wasNull()) {
								row1.CondPgtoCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 146) {
							row1.CodigoMix = null;
						} else {

							row1.CodigoMix = rs_tDBInput_1.getInt(146);
							if (rs_tDBInput_1.wasNull()) {
								row1.CodigoMix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 147) {
							row1.QtdeMix = null;
						} else {

							row1.QtdeMix = rs_tDBInput_1.getInt(147);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdeMix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 148) {
							row1.Lote = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(148);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(148).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Lote = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Lote = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Lote = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 149) {
							row1.VlrDesctoFiscal = null;
						} else {

							row1.VlrDesctoFiscal = rs_tDBInput_1.getDouble(149);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrDesctoFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 150) {
							row1.Tamanho = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(150);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(150).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Tamanho = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Tamanho = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Tamanho = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 151) {
							row1.BloqVerba = null;
						} else {

							row1.BloqVerba = rs_tDBInput_1.getInt(151);
							if (rs_tDBInput_1.wasNull()) {
								row1.BloqVerba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 152) {
							row1.VlrFreTitRateio = null;
						} else {

							row1.VlrFreTitRateio = rs_tDBInput_1.getDouble(152);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrFreTitRateio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 153) {
							row1.VlrFreTitRateioCalc = null;
						} else {

							row1.VlrFreTitRateioCalc = rs_tDBInput_1.getDouble(153);
							if (rs_tDBInput_1.wasNull()) {
								row1.VlrFreTitRateioCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 154) {
							row1.VisaoCota = null;
						} else {

							row1.VisaoCota = rs_tDBInput_1.getInt(154);
							if (rs_tDBInput_1.wasNull()) {
								row1.VisaoCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 155) {
							row1.PeriodoCota = null;
						} else {

							row1.PeriodoCota = rs_tDBInput_1.getInt(155);
							if (rs_tDBInput_1.wasNull()) {
								row1.PeriodoCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 156) {
							row1.VisaoCotaOriginal = null;
						} else {

							row1.VisaoCotaOriginal = rs_tDBInput_1.getInt(156);
							if (rs_tDBInput_1.wasNull()) {
								row1.VisaoCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 157) {
							row1.PeriodoCotaOriginal = null;
						} else {

							row1.PeriodoCotaOriginal = rs_tDBInput_1.getInt(157);
							if (rs_tDBInput_1.wasNull()) {
								row1.PeriodoCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 158) {
							row1.RegraEmpItem = null;
						} else {

							row1.RegraEmpItem = rs_tDBInput_1.getInt(158);
							if (rs_tDBInput_1.wasNull()) {
								row1.RegraEmpItem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 159) {
							row1.LjPeca = null;
						} else {

							row1.LjPeca = rs_tDBInput_1.getInt(159);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjPeca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 160) {
							row1.LjTamanho = null;
						} else {

							row1.LjTamanho = rs_tDBInput_1.getDouble(160);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjTamanho = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 161) {
							row1.LjCarga = null;
						} else {

							row1.LjCarga = rs_tDBInput_1.getInt(161);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjCarga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 162) {
							row1.LjMedidaA = null;
						} else {

							row1.LjMedidaA = rs_tDBInput_1.getDouble(162);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjMedidaA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 163) {
							row1.LjVao = null;
						} else {

							row1.LjVao = rs_tDBInput_1.getDouble(163);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjVao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 164) {
							row1.LjAngulo = null;
						} else {

							row1.LjAngulo = rs_tDBInput_1.getInt(164);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjAngulo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 165) {
							row1.LjMedidaB = null;
						} else {

							row1.LjMedidaB = rs_tDBInput_1.getDouble(165);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjMedidaB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 166) {
							row1.LjInicioViga = null;
						} else {

							row1.LjInicioViga = rs_tDBInput_1.getInt(166);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjInicioViga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 167) {
							row1.LjAba = null;
						} else {

							row1.LjAba = rs_tDBInput_1.getInt(167);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjAba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 168) {
							row1.LjAbaA = null;
						} else {

							row1.LjAbaA = rs_tDBInput_1.getDouble(168);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjAbaA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 169) {
							row1.LjAbaB = null;
						} else {

							row1.LjAbaB = rs_tDBInput_1.getDouble(169);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjAbaB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 170) {
							row1.Bloqluc = null;
						} else {

							row1.Bloqluc = rs_tDBInput_1.getInt(170);
							if (rs_tDBInput_1.wasNull()) {
								row1.Bloqluc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 171) {
							row1.OrigemCota = null;
						} else {

							row1.OrigemCota = rs_tDBInput_1.getInt(171);
							if (rs_tDBInput_1.wasNull()) {
								row1.OrigemCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 172) {
							row1.OrigemCanc = null;
						} else {

							row1.OrigemCanc = rs_tDBInput_1.getInt(172);
							if (rs_tDBInput_1.wasNull()) {
								row1.OrigemCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 173) {
							row1.Incsug = null;
						} else {

							row1.Incsug = rs_tDBInput_1.getInt(173);
							if (rs_tDBInput_1.wasNull()) {
								row1.Incsug = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 174) {
							row1.LjIdent = null;
						} else {

							row1.LjIdent = rs_tDBInput_1.getInt(174);
							if (rs_tDBInput_1.wasNull()) {
								row1.LjIdent = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 175) {
							row1.CondFreteIt = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(175);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(175).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CondFreteIt = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CondFreteIt = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CondFreteIt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 176) {
							row1.DesctotalCalc = null;
						} else {

							row1.DesctotalCalc = rs_tDBInput_1.getDouble(176);
							if (rs_tDBInput_1.wasNull()) {
								row1.DesctotalCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 177) {
							row1.IdPedAuto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(177);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(177).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.IdPedAuto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.IdPedAuto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.IdPedAuto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 178) {
							row1.LoteEstoque = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(178);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(178).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.LoteEstoque = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.LoteEstoque = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.LoteEstoque = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 179) {
							row1.DesctotalAplicado = null;
						} else {

							row1.DesctotalAplicado = rs_tDBInput_1.getDouble(179);
							if (rs_tDBInput_1.wasNull()) {
								row1.DesctotalAplicado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 180) {
							row1.SeqOrdemCompra = null;
						} else {

							row1.SeqOrdemCompra = rs_tDBInput_1.getInt(180);
							if (rs_tDBInput_1.wasNull()) {
								row1.SeqOrdemCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 181) {
							row1.SeqordCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(181);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(181).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SeqordCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SeqordCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SeqordCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 182) {
							row1.Ordcomest = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(182);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(182).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Ordcomest = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Ordcomest = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Ordcomest = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 183) {
							row1.ValorICMSFrete = null;
						} else {

							row1.ValorICMSFrete = rs_tDBInput_1.getDouble(183);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorICMSFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 184) {
							row1.Campo1 = null;
						} else {

							row1.Campo1 = rs_tDBInput_1.getInt(184);
							if (rs_tDBInput_1.wasNull()) {
								row1.Campo1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 185) {
							row1.CentroCusto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(185);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(185).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CentroCusto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CentroCusto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CentroCusto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 186) {
							row1.BloqDesc = null;
						} else {

							row1.BloqDesc = rs_tDBInput_1.getInt(186);
							if (rs_tDBInput_1.wasNull()) {
								row1.BloqDesc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 187) {
							row1.QtdeDescBonif = null;
						} else {

							row1.QtdeDescBonif = rs_tDBInput_1.getDouble(187);
							if (rs_tDBInput_1.wasNull()) {
								row1.QtdeDescBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 188) {
							row1.PercDescBonif = null;
						} else {

							row1.PercDescBonif = rs_tDBInput_1.getDouble(188);
							if (rs_tDBInput_1.wasNull()) {
								row1.PercDescBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 189) {
							row1.ValorDesccUnit = null;
						} else {

							row1.ValorDesccUnit = rs_tDBInput_1.getDouble(189);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorDesccUnit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 190) {
							row1.SeqCota = null;
						} else {

							row1.SeqCota = rs_tDBInput_1.getInt(190);
							if (rs_tDBInput_1.wasNull()) {
								row1.SeqCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 191) {
							row1.SeqCotaOriginal = null;
						} else {

							row1.SeqCotaOriginal = rs_tDBInput_1.getInt(191);
							if (rs_tDBInput_1.wasNull()) {
								row1.SeqCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 192) {
							row1.DtRegraCorte = null;
						} else {

							row1.DtRegraCorte = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 192);

						}
						if (colQtyInRs_tDBInput_1 < 193) {
							row1.CompoeMargem = null;
						} else {

							row1.CompoeMargem = rs_tDBInput_1.getInt(193);
							if (rs_tDBInput_1.wasNull()) {
								row1.CompoeMargem = null;
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

						cLabel = "Mercanet_Producao";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Mercanet_Producao";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tReplicate_1 main ] start
						 */

						currentComponent = "tReplicate_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row1", "tDBInput_1", "Mercanet_Producao", "tMSSqlInput", "tReplicate_1",
								"tReplicate_1", "tReplicate"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
						}

						row4 = new row4Struct();

						row4.NumeroPedido = row1.NumeroPedido;
						row4.SequenciaItem = row1.SequenciaItem;
						row4.CodigoProduto = row1.CodigoProduto;
						row4.QuantidadeProduto = row1.QuantidadeProduto;
						row4.QuantidadeAtendida = row1.QuantidadeAtendida;
						row4.QuantidadeCancelada = row1.QuantidadeCancelada;
						row4.PrecoUnitario = row1.PrecoUnitario;
						row4.PrecoLiquido = row1.PrecoLiquido;
						row4.Desctop = row1.Desctop;
						row4.DesctoIcm = row1.DesctoIcm;
						row4.PrevEntr = row1.PrevEntr;
						row4.AtdData = row1.AtdData;
						row4.AtdQtde = row1.AtdQtde;
						row4.AtdDcto = row1.AtdDcto;
						row4.AtdData1 = row1.AtdData1;
						row4.AtdQtde1 = row1.AtdQtde1;
						row4.AtdDcto1 = row1.AtdDcto1;
						row4.AtdData2 = row1.AtdData2;
						row4.AtdQtde2 = row1.AtdQtde2;
						row4.AtdDcto2 = row1.AtdDcto2;
						row4.SituacaoPedido = row1.SituacaoPedido;
						row4.Ipi = row1.Ipi;
						row4.Dctoprom = row1.Dctoprom;
						row4.EconVlr = row1.EconVlr;
						row4.EconCalc = row1.EconCalc;
						row4.EconDcto = row1.EconDcto;
						row4.PrecoMin = row1.PrecoMin;
						row4.Tipo = row1.Tipo;
						row4.Dctovda = row1.Dctovda;
						row4.TipoPreco = row1.TipoPreco;
						row4.EstVlr = row1.EstVlr;
						row4.Acrescimo = row1.Acrescimo;
						row4.Lpreco = row1.Lpreco;
						row4.Grade = row1.Grade;
						row4.TpGrade = row1.TpGrade;
						row4.CodEtiq = row1.CodEtiq;
						row4.Embalagem = row1.Embalagem;
						row4.MotCanc = row1.MotCanc;
						row4.PercComis = row1.PercComis;
						row4.PrdCfgCar = row1.PrdCfgCar;
						row4.Observacao = row1.Observacao;
						row4.BonifEcon = row1.BonifEcon;
						row4.PcomisExt = row1.PcomisExt;
						row4.AplicDcto = row1.AplicDcto;
						row4.Operacao = row1.Operacao;
						row4.QtdeRes = row1.QtdeRes;
						row4.Bloqsug = row1.Bloqsug;
						row4.Dctoinc = row1.Dctoinc;
						row4.VlrIpi = row1.VlrIpi;
						row4.SeqGrd = row1.SeqGrd;
						row4.SeqErp = row1.SeqErp;
						row4.PrevCalc = row1.PrevCalc;
						row4.Almoxarif = row1.Almoxarif;
						row4.ComisGer = row1.ComisGer;
						row4.CredProp = row1.CredProp;
						row4.NroEnvio = row1.NroEnvio;
						row4.Chave = row1.Chave;
						row4.ItemConf = row1.ItemConf;
						row4.CvdaDcti = row1.CvdaDcti;
						row4.ObsNota = row1.ObsNota;
						row4.ObsExped = row1.ObsExped;
						row4.ObsSepar = row1.ObsSepar;
						row4.VlrSubst = row1.VlrSubst;
						row4.DesctoGer = row1.DesctoGer;
						row4.VlrFreTit = row1.VlrFreTit;
						row4.MotTroca = row1.MotTroca;
						row4.VeconCig = row1.VeconCig;
						row4.CustoMp = row1.CustoMp;
						row4.PliqAlt = row1.PliqAlt;
						row4.CodPrMap = row1.CodPrMap;
						row4.CodCvdAg = row1.CodCvdAg;
						row4.VuIcms = row1.VuIcms;
						row4.VuPis = row1.VuPis;
						row4.VuCofins = row1.VuCofins;
						row4.Altdtprev = row1.Altdtprev;
						row4.QtdeAloc = row1.QtdeAloc;
						row4.RestrBonif = row1.RestrBonif;
						row4.CustoMo = row1.CustoMo;
						row4.ItControl = row1.ItControl;
						row4.ItPermBon = row1.ItPermBon;
						row4.QtdPermBon = row1.QtdPermBon;
						row4.VlrPermBon = row1.VlrPermBon;
						row4.Itemfemb = row1.Itemfemb;
						row4.Dtprevento = row1.Dtprevento;
						row4.Cfretit = row1.Cfretit;
						row4.SldVerba = row1.SldVerba;
						row4.VlrBonif = row1.VlrBonif;
						row4.Bonif = row1.Bonif;
						row4.Tpproduto = row1.Tpproduto;
						row4.Backord = row1.Backord;
						row4.Markup = row1.Markup;
						row4.QtdeBkor = row1.QtdeBkor;
						row4.Prcpromoc = row1.Prcpromoc;
						row4.Sitcorp1 = row1.Sitcorp1;
						row4.Sitcorp2 = row1.Sitcorp2;
						row4.LibMots = row1.LibMots;
						row4.Dctoboleto = row1.Dctoboleto;
						row4.MPRISeq = row1.MPRISeq;
						row4.Dctomax = row1.Dctomax;
						row4.Comprim = row1.Comprim;
						row4.QtdePeca = row1.QtdePeca;
						row4.ProdRes = row1.ProdRes;
						row4.EmpEstoq = row1.EmpEstoq;
						row4.PrevProduc = row1.PrevProduc;
						row4.PrunitOri = row1.PrunitOri;
						row4.PrliqOri = row1.PrliqOri;
						row4.Mva = row1.Mva;
						row4.ICMSOrig = row1.ICMSOrig;
						row4.ICMSDest = row1.ICMSDest;
						row4.Float1 = row1.Float1;
						row4.Float2 = row1.Float2;
						row4.DataAlter = row1.DataAlter;
						row4.SeqOrig = row1.SeqOrig;
						row4.PrecoNorm = row1.PrecoNorm;
						row4.Tipost = row1.Tipost;
						row4.CodProdcli = row1.CodProdcli;
						row4.VlrDescarga = row1.VlrDescarga;
						row4.IcmsDscto = row1.IcmsDscto;
						row4.EmpSplit = row1.EmpSplit;
						row4.Status = row1.Status;
						row4.Obrigatorio = row1.Obrigatorio;
						row4.PrecoFirme = row1.PrecoFirme;
						row4.OrdCompra = row1.OrdCompra;
						row4.DesctoFiscal = row1.DesctoFiscal;
						row4.PercMlucr = row1.PercMlucr;
						row4.VlrDescargaCalc = row1.VlrDescargaCalc;
						row4.VlrFreTitCalc = row1.VlrFreTitCalc;
						row4.VuComissao = row1.VuComissao;
						row4.VuDebcli = row1.VuDebcli;
						row4.Data1Original = row1.Data1Original;
						row4.Visao = row1.Visao;
						row4.Periodo = row1.Periodo;
						row4.BaseIcms = row1.BaseIcms;
						row4.BaseSt = row1.BaseSt;
						row4.CondPgto = row1.CondPgto;
						row4.UsuCanc = row1.UsuCanc;
						row4.Resrede = row1.Resrede;
						row4.DocBloq = row1.DocBloq;
						row4.Regracomis = row1.Regracomis;
						row4.BaseIcmsRed = row1.BaseIcmsRed;
						row4.IcmsSemRed = row1.IcmsSemRed;
						row4.Obs1 = row1.Obs1;
						row4.Obs2 = row1.Obs2;
						row4.CodEmbal = row1.CodEmbal;
						row4.CondPgtoCalc = row1.CondPgtoCalc;
						row4.CodigoMix = row1.CodigoMix;
						row4.QtdeMix = row1.QtdeMix;
						row4.Lote = row1.Lote;
						row4.VlrDesctoFiscal = row1.VlrDesctoFiscal;
						row4.Tamanho = row1.Tamanho;
						row4.BloqVerba = row1.BloqVerba;
						row4.VlrFreTitRateio = row1.VlrFreTitRateio;
						row4.VlrFreTitRateioCalc = row1.VlrFreTitRateioCalc;
						row4.VisaoCota = row1.VisaoCota;
						row4.PeriodoCota = row1.PeriodoCota;
						row4.VisaoCotaOriginal = row1.VisaoCotaOriginal;
						row4.PeriodoCotaOriginal = row1.PeriodoCotaOriginal;
						row4.RegraEmpItem = row1.RegraEmpItem;
						row4.LjPeca = row1.LjPeca;
						row4.LjTamanho = row1.LjTamanho;
						row4.LjCarga = row1.LjCarga;
						row4.LjMedidaA = row1.LjMedidaA;
						row4.LjVao = row1.LjVao;
						row4.LjAngulo = row1.LjAngulo;
						row4.LjMedidaB = row1.LjMedidaB;
						row4.LjInicioViga = row1.LjInicioViga;
						row4.LjAba = row1.LjAba;
						row4.LjAbaA = row1.LjAbaA;
						row4.LjAbaB = row1.LjAbaB;
						row4.Bloqluc = row1.Bloqluc;
						row4.OrigemCota = row1.OrigemCota;
						row4.OrigemCanc = row1.OrigemCanc;
						row4.Incsug = row1.Incsug;
						row4.LjIdent = row1.LjIdent;
						row4.CondFreteIt = row1.CondFreteIt;
						row4.DesctotalCalc = row1.DesctotalCalc;
						row4.IdPedAuto = row1.IdPedAuto;
						row4.LoteEstoque = row1.LoteEstoque;
						row4.DesctotalAplicado = row1.DesctotalAplicado;
						row4.SeqOrdemCompra = row1.SeqOrdemCompra;
						row4.SeqordCompra = row1.SeqordCompra;
						row4.Ordcomest = row1.Ordcomest;
						row4.ValorICMSFrete = row1.ValorICMSFrete;
						row4.Campo1 = row1.Campo1;
						row4.CentroCusto = row1.CentroCusto;
						row4.BloqDesc = row1.BloqDesc;
						row4.QtdeDescBonif = row1.QtdeDescBonif;
						row4.PercDescBonif = row1.PercDescBonif;
						row4.ValorDesccUnit = row1.ValorDesccUnit;
						row4.SeqCota = row1.SeqCota;
						row4.SeqCotaOriginal = row1.SeqCotaOriginal;
						row4.DtRegraCorte = row1.DtRegraCorte;
						row4.CompoeMargem = row1.CompoeMargem;

						tos_count_tReplicate_1++;

						/**
						 * [tReplicate_1 main ] stop
						 */

						/**
						 * [tReplicate_1 process_data_begin ] start
						 */

						currentComponent = "tReplicate_1";

						/**
						 * [tReplicate_1 process_data_begin ] stop
						 */

						/**
						 * [tFileOutputDelimited_1 main ] start
						 */

						currentComponent = "tFileOutputDelimited_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row4", "tReplicate_1", "tReplicate_1", "tReplicate", "tFileOutputDelimited_1",
								"tFileOutputDelimited_1", "tFileOutputDelimited"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
						}

						StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
						fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_0(row4, sb_tFileOutputDelimited_1,
								OUT_DELIM_tFileOutputDelimited_1);
						fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_1(row4, sb_tFileOutputDelimited_1,
								OUT_DELIM_tFileOutputDelimited_1);
						fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_2(row4, sb_tFileOutputDelimited_1,
								OUT_DELIM_tFileOutputDelimited_1);
						sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

						nb_line_tFileOutputDelimited_1++;
						resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

						outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());
						log.debug(
								"tFileOutputDelimited_1 - Writing the record " + nb_line_tFileOutputDelimited_1 + ".");

						tos_count_tFileOutputDelimited_1++;

						/**
						 * [tFileOutputDelimited_1 main ] stop
						 */

						/**
						 * [tFileOutputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileOutputDelimited_1";

						/**
						 * [tFileOutputDelimited_1 process_data_begin ] stop
						 */

						/**
						 * [tFileOutputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileOutputDelimited_1";

						/**
						 * [tFileOutputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tReplicate_1 process_data_end ] start
						 */

						currentComponent = "tReplicate_1";

						/**
						 * [tReplicate_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Mercanet_Producao";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Mercanet_Producao";

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
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tDBInput_1", "Mercanet_Producao", "tMSSqlInput", "tReplicate_1", "tReplicate_1", "tReplicate",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tReplicate_1 - " + ("Done."));

				ok_Hash.put("tReplicate_1", true);
				end_Hash.put("tReplicate_1", System.currentTimeMillis());

				/**
				 * [tReplicate_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				log.debug("tFileOutputDelimited_1 - Written records count: " + nb_line_tFileOutputDelimited_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tReplicate_1", "tReplicate_1", "tReplicate", "tFileOutputDelimited_1",
						"tFileOutputDelimited_1", "tFileOutputDelimited", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
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
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				cLabel = "Mercanet_Producao";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tReplicate_1 finally ] start
				 */

				currentComponent = "tReplicate_1";

				/**
				 * [tReplicate_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
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

	public void tDBOutput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBOutput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBOutput_2");
		org.slf4j.MDC.put("_subJobPid", "LAHsDC_" + subJobPidCounter.getAndIncrement());

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
				 * [tDBOutput_2 begin ] start
				 */

				ok_Hash.put("tDBOutput_2", false);
				start_Hash.put("tDBOutput_2", System.currentTimeMillis());

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				int tos_count_tDBOutput_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBOutput_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBOutput_2 = new StringBuilder();
							log4jParamters_tDBOutput_2.append("Parameters:");
							log4jParamters_tDBOutput_2.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("HOST" + " = " + "\"192.168.4.128\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("DBNAME" + " = " + "\"BRONZE\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("USER" + " = " + "\"sa\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:SxLvR8Lb4m/RuxJri6IAGstDEKDzDM9mlK5A5uCOFamixIHJuDfjlH0=")
									.substring(0, 4) + "...");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("TABLE" + " = " + "\"PedidosForcaVenda_RAW\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("IDENTITY_INSERT" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("DATA_ACTION" + " = " + "INSERT_OR_UPDATE");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("SPECIFY_IDENTITY_FIELD" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("COMMIT_EVERY" + " = " + "10000");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("ADD_COLS" + " = " + "[]");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("USE_FIELD_OPTIONS" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("IGNORE_DATE_OUTOF_RANGE" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("ENABLE_DEBUG_MODE" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("SUPPORT_NULL_WHERE" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBOutput_2.append(" | ");
							log4jParamters_tDBOutput_2.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlOutput");
							log4jParamters_tDBOutput_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBOutput_2 - " + (log4jParamters_tDBOutput_2));
						}
					}
					new BytesLimit65535_tDBOutput_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBOutput_2", "Datalake_Bronze", "tMSSqlOutput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tDBOutput_2 = 0;
				int nb_line_update_tDBOutput_2 = 0;
				int nb_line_inserted_tDBOutput_2 = 0;
				int nb_line_deleted_tDBOutput_2 = 0;
				int nb_line_rejected_tDBOutput_2 = 0;

				int deletedCount_tDBOutput_2 = 0;
				int updatedCount_tDBOutput_2 = 0;
				int insertedCount_tDBOutput_2 = 0;
				int rowsToCommitCount_tDBOutput_2 = 0;
				int rejectedCount_tDBOutput_2 = 0;
				String dbschema_tDBOutput_2 = null;
				String tableName_tDBOutput_2 = null;
				boolean whetherReject_tDBOutput_2 = false;

				java.util.Calendar calendar_tDBOutput_2 = java.util.Calendar.getInstance();
				long year1_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_2 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_2;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_2 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				int updateKeyCount_tDBOutput_2 = 2;
				if (updateKeyCount_tDBOutput_2 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_2 == 193 && true) {
					log.warn("For update, every Schema column can not be a key");
				}

				java.sql.Connection conn_tDBOutput_2 = null;
				String dbUser_tDBOutput_2 = null;
				dbschema_tDBOutput_2 = "";
				String driverClass_tDBOutput_2 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Driver ClassName: ") + (driverClass_tDBOutput_2) + ("."));
				java.lang.Class.forName(driverClass_tDBOutput_2);
				String port_tDBOutput_2 = "1433";
				String dbname_tDBOutput_2 = "BRONZE";
				String url_tDBOutput_2 = "jdbc:sqlserver://" + "192.168.4.128";
				if (!"".equals(port_tDBOutput_2)) {
					url_tDBOutput_2 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_2)) {
					url_tDBOutput_2 += ";databaseName=" + "BRONZE";

				}
				url_tDBOutput_2 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_2 = "sa";

				final String decryptedPassword_tDBOutput_2 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:XwViRtmDKRteYgZIILdlWSQXATmsVy8QpdOFoF1f3Is+sKazohIsDNY=");

				String dbPwd_tDBOutput_2 = decryptedPassword_tDBOutput_2;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Connection attempts to '") + (url_tDBOutput_2)
							+ ("' with the username '") + (dbUser_tDBOutput_2) + ("'."));
				conn_tDBOutput_2 = java.sql.DriverManager.getConnection(url_tDBOutput_2, dbUser_tDBOutput_2,
						dbPwd_tDBOutput_2);
				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Connection to '") + (url_tDBOutput_2) + ("' has succeeded."));

				resourceMap.put("conn_tDBOutput_2", conn_tDBOutput_2);

				conn_tDBOutput_2.setAutoCommit(false);
				int commitEvery_tDBOutput_2 = 10000;
				int commitCounter_tDBOutput_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Connection is set auto commit to '")
							+ (conn_tDBOutput_2.getAutoCommit()) + ("'."));

				if (dbschema_tDBOutput_2 == null || dbschema_tDBOutput_2.trim().length() == 0) {
					tableName_tDBOutput_2 = "PedidosForcaVenda_RAW";
				} else {
					tableName_tDBOutput_2 = dbschema_tDBOutput_2 + "].[" + "PedidosForcaVenda_RAW";
				}
				int count_tDBOutput_2 = 0;

				boolean whetherExist_tDBOutput_2 = false;
				try (java.sql.Statement isExistStmt_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					try {

						isExistStmt_tDBOutput_2.execute("SELECT TOP 1 1 FROM [" + tableName_tDBOutput_2 + "]");
						whetherExist_tDBOutput_2 = true;
					} catch (java.lang.Exception e) {
						globalMap.put("tDBOutput_2_ERROR_MESSAGE", e.getMessage());
						whetherExist_tDBOutput_2 = false;
					}
				}
				if (whetherExist_tDBOutput_2) {
					try (java.sql.Statement stmtDrop_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
						if (log.isDebugEnabled())
							log.debug("tDBOutput_2 - " + ("Dropping") + (" table '")
									+ ("[" + tableName_tDBOutput_2 + "]") + ("'."));
						stmtDrop_tDBOutput_2.execute("DROP TABLE [" + tableName_tDBOutput_2 + "]");
						if (log.isDebugEnabled())
							log.debug("tDBOutput_2 - " + ("Drop") + (" table '") + ("[" + tableName_tDBOutput_2 + "]")
									+ ("' has succeeded."));
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_2 = conn_tDBOutput_2.createStatement()) {
					if (log.isDebugEnabled())
						log.debug("tDBOutput_2 - " + ("Creating") + (" table '") + ("[" + tableName_tDBOutput_2 + "]")
								+ ("'."));
					stmtCreate_tDBOutput_2.execute("CREATE TABLE [" + tableName_tDBOutput_2
							+ "]([NumeroPedido] INT ,[SequenciaItem] INT ,[CodigoProduto] VARCHAR(16)  ,[QuantidadeProduto] REAL ,[QuantidadeAtendida] REAL ,[QuantidadeCancelada] REAL ,[PrecoUnitario] REAL ,[PrecoLiquido] REAL ,[Desctop] REAL ,[DesctoIcm] FLOAT(15)  ,[PrevEntr] DATETIME ,[AtdData] DATETIME ,[AtdQtde] REAL ,[AtdDcto] VARCHAR(20)  ,[AtdData1] DATETIME ,[AtdQtde1] REAL ,[AtdDcto1] VARCHAR(20)  ,[AtdData2] DATETIME ,[AtdQtde2] REAL ,[AtdDcto2] VARCHAR(20)  ,[SituacaoPedido] SMALLINT ,[Ipi] FLOAT(15)  ,[Dctoprom] REAL ,[EconVlr] REAL ,[EconCalc] TINYINT ,[EconDcto] REAL ,[PrecoMin] REAL ,[Tipo] VARCHAR(1)  ,[Dctovda] FLOAT(15)  ,[TipoPreco] SMALLINT ,[EstVlr] SMALLINT ,[Acrescimo] FLOAT(15)  ,[Lpreco] VARCHAR(8)  ,[Grade] VARCHAR(4)  ,[TpGrade] SMALLINT ,[CodEtiq] VARCHAR(5)  ,[Embalagem] FLOAT(15)  ,[MotCanc] VARCHAR(5)  ,[PercComis] REAL ,[PrdCfgCar] VARCHAR(1024)  ,[Observacao] VARCHAR(100)  ,[BonifEcon] VARCHAR(1)  ,[PcomisExt] FLOAT(15)  ,[AplicDcto] VARCHAR(10)  ,[Operacao] VARCHAR(10)  ,[QtdeRes] FLOAT(15)  ,[Bloqsug] VARCHAR(1)  ,[Dctoinc] REAL ,[VlrIpi] FLOAT(15)  ,[SeqGrd] VARCHAR(7)  ,[SeqErp] INT ,[PrevCalc] DATETIME ,[Almoxarif] VARCHAR(3)  ,[ComisGer] FLOAT(15)  ,[CredProp] FLOAT(15)  ,[NroEnvio] FLOAT(15)  ,[Chave] VARCHAR(1)  ,[ItemConf] VARCHAR(16)  ,[CvdaDcti] VARCHAR(10)  ,[ObsNota] VARCHAR(50)  ,[ObsExped] VARCHAR(50)  ,[ObsSepar] VARCHAR(255)  ,[VlrSubst] FLOAT(15)  ,[DesctoGer] FLOAT(15)  ,[VlrFreTit] FLOAT(15)  ,[MotTroca] VARCHAR(5)  ,[VeconCig] FLOAT(15)  ,[CustoMp] FLOAT(15)  ,[PliqAlt] SMALLINT ,[CodPrMap] VARCHAR(10)  ,[CodCvdAg] VARCHAR(10)  ,[VuIcms] FLOAT(15)  ,[VuPis] FLOAT(15)  ,[VuCofins] FLOAT(15)  ,[Altdtprev] SMALLINT ,[QtdeAloc] FLOAT(15)  ,[RestrBonif] VARCHAR(5)  ,[CustoMo] FLOAT(15)  ,[ItControl] SMALLINT ,[ItPermBon] SMALLINT ,[QtdPermBon] FLOAT(15)  ,[VlrPermBon] FLOAT(15)  ,[Itemfemb] SMALLINT ,[Dtprevento] DATETIME ,[Cfretit] VARCHAR(4)  ,[SldVerba] FLOAT(15)  ,[VlrBonif] FLOAT(15)  ,[Bonif] SMALLINT ,[Tpproduto] SMALLINT ,[Backord] VARCHAR(3)  ,[Markup] FLOAT(15)  ,[QtdeBkor] FLOAT(15)  ,[Prcpromoc] FLOAT(15)  ,[Sitcorp1] VARCHAR(6)  ,[Sitcorp2] VARCHAR(6)  ,[LibMots] VARCHAR(255)  ,[Dctoboleto] FLOAT(15)  ,[MPRISeq] INT ,[Dctomax] FLOAT(15)  ,[Comprim] FLOAT(15)  ,[QtdePeca] FLOAT(15)  ,[ProdRes] VARCHAR(16)  ,[EmpEstoq] VARCHAR(3)  ,[PrevProduc] FLOAT(15)  ,[PrunitOri] REAL ,[PrliqOri] REAL ,[Mva] FLOAT(15)  ,[ICMSOrig] FLOAT(15)  ,[ICMSDest] FLOAT(15)  ,[Float1] FLOAT(15)  ,[Float2] FLOAT(15)  ,[DataAlter] DATETIME ,[SeqOrig] VARCHAR(12)  ,[PrecoNorm] FLOAT(15)  ,[Tipost] SMALLINT ,[CodProdcli] VARCHAR(20)  ,[VlrDescarga] FLOAT(15)  ,[IcmsDscto] SMALLINT ,[EmpSplit] VARCHAR(3)  ,[Status] INT ,[Obrigatorio] INT ,[PrecoFirme] INT ,[OrdCompra] VARCHAR(25)  ,[DesctoFiscal] FLOAT(15)  ,[PercMlucr] FLOAT(15)  ,[VlrDescargaCalc] FLOAT(15)  ,[VlrFreTitCalc] FLOAT(15)  ,[VuComissao] FLOAT(15)  ,[VuDebcli] FLOAT(15)  ,[Data1Original] DATETIME ,[Visao] INT ,[Periodo] INT ,[BaseIcms] FLOAT(15)  ,[BaseSt] FLOAT(15)  ,[CondPgto] INT ,[UsuCanc] VARCHAR(20)  ,[Resrede] FLOAT(15)  ,[DocBloq] VARCHAR(100)  ,[Regracomis] VARCHAR(8)  ,[BaseIcmsRed] FLOAT(15)  ,[IcmsSemRed] FLOAT(15)  ,[Obs1] VARCHAR(255)  ,[Obs2] VARCHAR(255)  ,[CodEmbal] VARCHAR(3)  ,[CondPgtoCalc] INT ,[CodigoMix] INT ,[QtdeMix] INT ,[Lote] VARCHAR(25)  ,[VlrDesctoFiscal] FLOAT(15)  ,[Tamanho] VARCHAR(255)  ,[BloqVerba] INT ,[VlrFreTitRateio] FLOAT(15)  ,[VlrFreTitRateioCalc] FLOAT(15)  ,[VisaoCota] INT ,[PeriodoCota] INT ,[VisaoCotaOriginal] INT ,[PeriodoCotaOriginal] INT ,[RegraEmpItem] INT ,[LjPeca] INT ,[LjTamanho] FLOAT(15)  ,[LjCarga] INT ,[LjMedidaA] FLOAT(15)  ,[LjVao] FLOAT(15)  ,[LjAngulo] INT ,[LjMedidaB] FLOAT(15)  ,[LjInicioViga] INT ,[LjAba] INT ,[LjAbaA] FLOAT(15)  ,[LjAbaB] FLOAT(15)  ,[Bloqluc] INT ,[OrigemCota] INT ,[OrigemCanc] INT ,[Incsug] INT ,[LjIdent] INT ,[CondFreteIt] VARCHAR(4)  ,[DesctotalCalc] FLOAT(15)  ,[IdPedAuto] VARCHAR(50)  ,[LoteEstoque] VARCHAR(50)  ,[DesctotalAplicado] FLOAT(15)  ,[SeqOrdemCompra] INT ,[SeqordCompra] VARCHAR(30)  ,[Ordcomest] VARCHAR(255)  ,[ValorICMSFrete] FLOAT(15)  ,[Campo1] INT ,[CentroCusto] VARCHAR(30)  ,[BloqDesc] INT ,[QtdeDescBonif] FLOAT(15)  ,[PercDescBonif] FLOAT(15)  ,[ValorDesccUnit] FLOAT(15)  ,[SeqCota] INT ,[SeqCotaOriginal] INT ,[DtRegraCorte] DATETIME ,[CompoeMargem] INT ,primary key([NumeroPedido],[CodigoProduto]))");
					if (log.isDebugEnabled())
						log.debug("tDBOutput_2 - " + ("Create") + (" table '") + ("[" + tableName_tDBOutput_2 + "]")
								+ ("' has succeeded."));
				}
				java.sql.PreparedStatement pstmt_tDBOutput_2 = null;
				java.sql.PreparedStatement pstmtInsert_tDBOutput_2 = null;
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_2 = null;
				pstmt_tDBOutput_2 = conn_tDBOutput_2.prepareStatement("SELECT COUNT(1) FROM [" + tableName_tDBOutput_2
						+ "] WHERE [NumeroPedido] = ? AND [CodigoProduto] = ?");
				resourceMap.put("pstmt_tDBOutput_2", pstmt_tDBOutput_2);
				String insert_tDBOutput_2 = "INSERT INTO [" + tableName_tDBOutput_2
						+ "] ([NumeroPedido],[SequenciaItem],[CodigoProduto],[QuantidadeProduto],[QuantidadeAtendida],[QuantidadeCancelada],[PrecoUnitario],[PrecoLiquido],[Desctop],[DesctoIcm],[PrevEntr],[AtdData],[AtdQtde],[AtdDcto],[AtdData1],[AtdQtde1],[AtdDcto1],[AtdData2],[AtdQtde2],[AtdDcto2],[SituacaoPedido],[Ipi],[Dctoprom],[EconVlr],[EconCalc],[EconDcto],[PrecoMin],[Tipo],[Dctovda],[TipoPreco],[EstVlr],[Acrescimo],[Lpreco],[Grade],[TpGrade],[CodEtiq],[Embalagem],[MotCanc],[PercComis],[PrdCfgCar],[Observacao],[BonifEcon],[PcomisExt],[AplicDcto],[Operacao],[QtdeRes],[Bloqsug],[Dctoinc],[VlrIpi],[SeqGrd],[SeqErp],[PrevCalc],[Almoxarif],[ComisGer],[CredProp],[NroEnvio],[Chave],[ItemConf],[CvdaDcti],[ObsNota],[ObsExped],[ObsSepar],[VlrSubst],[DesctoGer],[VlrFreTit],[MotTroca],[VeconCig],[CustoMp],[PliqAlt],[CodPrMap],[CodCvdAg],[VuIcms],[VuPis],[VuCofins],[Altdtprev],[QtdeAloc],[RestrBonif],[CustoMo],[ItControl],[ItPermBon],[QtdPermBon],[VlrPermBon],[Itemfemb],[Dtprevento],[Cfretit],[SldVerba],[VlrBonif],[Bonif],[Tpproduto],[Backord],[Markup],[QtdeBkor],[Prcpromoc],[Sitcorp1],[Sitcorp2],[LibMots],[Dctoboleto],[MPRISeq],[Dctomax],[Comprim],[QtdePeca],[ProdRes],[EmpEstoq],[PrevProduc],[PrunitOri],[PrliqOri],[Mva],[ICMSOrig],[ICMSDest],[Float1],[Float2],[DataAlter],[SeqOrig],[PrecoNorm],[Tipost],[CodProdcli],[VlrDescarga],[IcmsDscto],[EmpSplit],[Status],[Obrigatorio],[PrecoFirme],[OrdCompra],[DesctoFiscal],[PercMlucr],[VlrDescargaCalc],[VlrFreTitCalc],[VuComissao],[VuDebcli],[Data1Original],[Visao],[Periodo],[BaseIcms],[BaseSt],[CondPgto],[UsuCanc],[Resrede],[DocBloq],[Regracomis],[BaseIcmsRed],[IcmsSemRed],[Obs1],[Obs2],[CodEmbal],[CondPgtoCalc],[CodigoMix],[QtdeMix],[Lote],[VlrDesctoFiscal],[Tamanho],[BloqVerba],[VlrFreTitRateio],[VlrFreTitRateioCalc],[VisaoCota],[PeriodoCota],[VisaoCotaOriginal],[PeriodoCotaOriginal],[RegraEmpItem],[LjPeca],[LjTamanho],[LjCarga],[LjMedidaA],[LjVao],[LjAngulo],[LjMedidaB],[LjInicioViga],[LjAba],[LjAbaA],[LjAbaB],[Bloqluc],[OrigemCota],[OrigemCanc],[Incsug],[LjIdent],[CondFreteIt],[DesctotalCalc],[IdPedAuto],[LoteEstoque],[DesctotalAplicado],[SeqOrdemCompra],[SeqordCompra],[Ordcomest],[ValorICMSFrete],[Campo1],[CentroCusto],[BloqDesc],[QtdeDescBonif],[PercDescBonif],[ValorDesccUnit],[SeqCota],[SeqCotaOriginal],[DtRegraCorte],[CompoeMargem]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmtInsert_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(insert_tDBOutput_2);
				resourceMap.put("pstmtInsert_tDBOutput_2", pstmtInsert_tDBOutput_2);
				String update_tDBOutput_2 = "UPDATE [" + tableName_tDBOutput_2
						+ "] SET [SequenciaItem] = ?,[QuantidadeProduto] = ?,[QuantidadeAtendida] = ?,[QuantidadeCancelada] = ?,[PrecoUnitario] = ?,[PrecoLiquido] = ?,[Desctop] = ?,[DesctoIcm] = ?,[PrevEntr] = ?,[AtdData] = ?,[AtdQtde] = ?,[AtdDcto] = ?,[AtdData1] = ?,[AtdQtde1] = ?,[AtdDcto1] = ?,[AtdData2] = ?,[AtdQtde2] = ?,[AtdDcto2] = ?,[SituacaoPedido] = ?,[Ipi] = ?,[Dctoprom] = ?,[EconVlr] = ?,[EconCalc] = ?,[EconDcto] = ?,[PrecoMin] = ?,[Tipo] = ?,[Dctovda] = ?,[TipoPreco] = ?,[EstVlr] = ?,[Acrescimo] = ?,[Lpreco] = ?,[Grade] = ?,[TpGrade] = ?,[CodEtiq] = ?,[Embalagem] = ?,[MotCanc] = ?,[PercComis] = ?,[PrdCfgCar] = ?,[Observacao] = ?,[BonifEcon] = ?,[PcomisExt] = ?,[AplicDcto] = ?,[Operacao] = ?,[QtdeRes] = ?,[Bloqsug] = ?,[Dctoinc] = ?,[VlrIpi] = ?,[SeqGrd] = ?,[SeqErp] = ?,[PrevCalc] = ?,[Almoxarif] = ?,[ComisGer] = ?,[CredProp] = ?,[NroEnvio] = ?,[Chave] = ?,[ItemConf] = ?,[CvdaDcti] = ?,[ObsNota] = ?,[ObsExped] = ?,[ObsSepar] = ?,[VlrSubst] = ?,[DesctoGer] = ?,[VlrFreTit] = ?,[MotTroca] = ?,[VeconCig] = ?,[CustoMp] = ?,[PliqAlt] = ?,[CodPrMap] = ?,[CodCvdAg] = ?,[VuIcms] = ?,[VuPis] = ?,[VuCofins] = ?,[Altdtprev] = ?,[QtdeAloc] = ?,[RestrBonif] = ?,[CustoMo] = ?,[ItControl] = ?,[ItPermBon] = ?,[QtdPermBon] = ?,[VlrPermBon] = ?,[Itemfemb] = ?,[Dtprevento] = ?,[Cfretit] = ?,[SldVerba] = ?,[VlrBonif] = ?,[Bonif] = ?,[Tpproduto] = ?,[Backord] = ?,[Markup] = ?,[QtdeBkor] = ?,[Prcpromoc] = ?,[Sitcorp1] = ?,[Sitcorp2] = ?,[LibMots] = ?,[Dctoboleto] = ?,[MPRISeq] = ?,[Dctomax] = ?,[Comprim] = ?,[QtdePeca] = ?,[ProdRes] = ?,[EmpEstoq] = ?,[PrevProduc] = ?,[PrunitOri] = ?,[PrliqOri] = ?,[Mva] = ?,[ICMSOrig] = ?,[ICMSDest] = ?,[Float1] = ?,[Float2] = ?,[DataAlter] = ?,[SeqOrig] = ?,[PrecoNorm] = ?,[Tipost] = ?,[CodProdcli] = ?,[VlrDescarga] = ?,[IcmsDscto] = ?,[EmpSplit] = ?,[Status] = ?,[Obrigatorio] = ?,[PrecoFirme] = ?,[OrdCompra] = ?,[DesctoFiscal] = ?,[PercMlucr] = ?,[VlrDescargaCalc] = ?,[VlrFreTitCalc] = ?,[VuComissao] = ?,[VuDebcli] = ?,[Data1Original] = ?,[Visao] = ?,[Periodo] = ?,[BaseIcms] = ?,[BaseSt] = ?,[CondPgto] = ?,[UsuCanc] = ?,[Resrede] = ?,[DocBloq] = ?,[Regracomis] = ?,[BaseIcmsRed] = ?,[IcmsSemRed] = ?,[Obs1] = ?,[Obs2] = ?,[CodEmbal] = ?,[CondPgtoCalc] = ?,[CodigoMix] = ?,[QtdeMix] = ?,[Lote] = ?,[VlrDesctoFiscal] = ?,[Tamanho] = ?,[BloqVerba] = ?,[VlrFreTitRateio] = ?,[VlrFreTitRateioCalc] = ?,[VisaoCota] = ?,[PeriodoCota] = ?,[VisaoCotaOriginal] = ?,[PeriodoCotaOriginal] = ?,[RegraEmpItem] = ?,[LjPeca] = ?,[LjTamanho] = ?,[LjCarga] = ?,[LjMedidaA] = ?,[LjVao] = ?,[LjAngulo] = ?,[LjMedidaB] = ?,[LjInicioViga] = ?,[LjAba] = ?,[LjAbaA] = ?,[LjAbaB] = ?,[Bloqluc] = ?,[OrigemCota] = ?,[OrigemCanc] = ?,[Incsug] = ?,[LjIdent] = ?,[CondFreteIt] = ?,[DesctotalCalc] = ?,[IdPedAuto] = ?,[LoteEstoque] = ?,[DesctotalAplicado] = ?,[SeqOrdemCompra] = ?,[SeqordCompra] = ?,[Ordcomest] = ?,[ValorICMSFrete] = ?,[Campo1] = ?,[CentroCusto] = ?,[BloqDesc] = ?,[QtdeDescBonif] = ?,[PercDescBonif] = ?,[ValorDesccUnit] = ?,[SeqCota] = ?,[SeqCotaOriginal] = ?,[DtRegraCorte] = ?,[CompoeMargem] = ? WHERE [NumeroPedido] = ? AND [CodigoProduto] = ?";
				pstmtUpdate_tDBOutput_2 = conn_tDBOutput_2.prepareStatement(update_tDBOutput_2);
				resourceMap.put("pstmtUpdate_tDBOutput_2", pstmtUpdate_tDBOutput_2);

				/**
				 * [tDBOutput_2 begin ] stop
				 */

				/**
				 * [tDBOutput_2 main ] start
				 */

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				tos_count_tDBOutput_2++;

				/**
				 * [tDBOutput_2 main ] stop
				 */

				/**
				 * [tDBOutput_2 process_data_begin ] start
				 */

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				/**
				 * [tDBOutput_2 process_data_begin ] stop
				 */

				/**
				 * [tDBOutput_2 process_data_end ] start
				 */

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				/**
				 * [tDBOutput_2 process_data_end ] stop
				 */

				/**
				 * [tDBOutput_2 end ] start
				 */

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				if (pstmtUpdate_tDBOutput_2 != null) {
					pstmtUpdate_tDBOutput_2.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_2");
				}
				if (pstmtInsert_tDBOutput_2 != null) {
					pstmtInsert_tDBOutput_2.close();
					resourceMap.remove("pstmtInsert_tDBOutput_2");
				}
				if (pstmt_tDBOutput_2 != null) {
					pstmt_tDBOutput_2.close();
					resourceMap.remove("pstmt_tDBOutput_2");
				}
				resourceMap.put("statementClosed_tDBOutput_2", true);
				if (rowsToCommitCount_tDBOutput_2 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_2 - " + ("Connection starting to commit ")
								+ (rowsToCommitCount_tDBOutput_2) + (" record(s)."));
				}
				conn_tDBOutput_2.commit();
				if (rowsToCommitCount_tDBOutput_2 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_2 - " + ("Connection commit has succeeded."));
					rowsToCommitCount_tDBOutput_2 = 0;
				}
				commitCounter_tDBOutput_2 = 0;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Closing the connection to the database."));
				conn_tDBOutput_2.close();
				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Connection to the database has closed."));
				resourceMap.put("finish_tDBOutput_2", true);

				nb_line_deleted_tDBOutput_2 = nb_line_deleted_tDBOutput_2 + deletedCount_tDBOutput_2;
				nb_line_update_tDBOutput_2 = nb_line_update_tDBOutput_2 + updatedCount_tDBOutput_2;
				nb_line_inserted_tDBOutput_2 = nb_line_inserted_tDBOutput_2 + insertedCount_tDBOutput_2;
				nb_line_rejected_tDBOutput_2 = nb_line_rejected_tDBOutput_2 + rejectedCount_tDBOutput_2;

				globalMap.put("tDBOutput_2_NB_LINE", nb_line_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_UPDATED", nb_line_update_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_DELETED", nb_line_deleted_tDBOutput_2);
				globalMap.put("tDBOutput_2_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_2);

				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Has ") + ("inserted") + (" ") + (nb_line_inserted_tDBOutput_2)
							+ (" record(s)."));
				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Has ") + ("updated") + (" ") + (nb_line_update_tDBOutput_2)
							+ (" record(s)."));

				if (log.isDebugEnabled())
					log.debug("tDBOutput_2 - " + ("Done."));

				ok_Hash.put("tDBOutput_2", true);
				end_Hash.put("tDBOutput_2", System.currentTimeMillis());

				/**
				 * [tDBOutput_2 end ] stop
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
				 * [tDBOutput_2 finally ] start
				 */

				currentComponent = "tDBOutput_2";

				cLabel = "Datalake_Bronze";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_2") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_2 = null;
						if ((pstmtUpdateToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_2")) != null) {
							pstmtUpdateToClose_tDBOutput_2.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_2 = null;
						if ((pstmtInsertToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_2")) != null) {
							pstmtInsertToClose_tDBOutput_2.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_2 = null;
						if ((pstmtToClose_tDBOutput_2 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_2")) != null) {
							pstmtToClose_tDBOutput_2.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_2") == null) {
						java.sql.Connection ctn_tDBOutput_2 = null;
						if ((ctn_tDBOutput_2 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_2")) != null) {
							try {
								if (log.isDebugEnabled())
									log.debug("tDBOutput_2 - " + ("Closing the connection to the database."));
								ctn_tDBOutput_2.close();
								if (log.isDebugEnabled())
									log.debug("tDBOutput_2 - " + ("Connection to the database has closed."));
							} catch (java.sql.SQLException sqlEx_tDBOutput_2) {
								String errorMessage_tDBOutput_2 = "failed to close the connection in tDBOutput_2 :"
										+ sqlEx_tDBOutput_2.getMessage();
								log.error("tDBOutput_2 - " + (errorMessage_tDBOutput_2));
								System.err.println(errorMessage_tDBOutput_2);
							}
						}
					}
				}

				/**
				 * [tDBOutput_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBOutput_2_SUBPROCESS_STATE", 1);
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
		final Pedidos_mercanet Pedidos_mercanetClass = new Pedidos_mercanet();

		int exitCode = Pedidos_mercanetClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Pedidos_mercanet' - Done.");
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
		log.info("TalendJob: 'Pedidos_mercanet' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_QWUlIERDEe-YS8RQVZZjcw");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-07-30T17:48:59.625812600Z");

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
			java.io.InputStream inContext = Pedidos_mercanet.class.getClassLoader()
					.getResourceAsStream("hydronorth/pedidos_mercanet_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Pedidos_mercanet.class.getClassLoader()
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
		log.info("TalendJob: 'Pedidos_mercanet' - Started.");
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
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Pedidos_mercanet");
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
		log.info("TalendJob: 'Pedidos_mercanet' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 948003 characters generated by Talend Cloud Data Integration on the 30 de
 * julho de 2024 14:48:59 BRT
 ************************************************************************************************/