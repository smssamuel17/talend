
package hydronorth.carteira_0_1;

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
 * Job: Carteira Purpose: <br>
 * Description: <br>
 * 
 * @author Oliveira Santi, Thiago
 * @version 8.0.1.20240619_0909-patch
 * @status TEST
 */
public class Carteira implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Carteira.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Carteira.class);

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
	private final String jobName = "Carteira";
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
			"_4A2EQE6kEe-Wi9BUu6mzew", "0.1");
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
					Carteira.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Carteira.this, new Object[] { e, currentComponent, globalMap });
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

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFilterRow_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_Itens_pedido_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_TIpo_operacao_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFilterRow_10_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class Itens_pedidoStruct
			implements routines.system.IPersistableComparableLookupRow<Itens_pedidoStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
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
			return true;
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
			return 10;
		}

		public Integer PrevEntrPrecision() {
			return 0;
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
			return 10;
		}

		public Integer AtdDataPrecision() {
			return 0;
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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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
			return 10;
		}

		public Integer DtpreventoPrecision() {
			return 0;
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
			return 10;
		}

		public Integer Data1OriginalPrecision() {
			return 0;
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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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

			return "";

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
			final Itens_pedidoStruct other = (Itens_pedidoStruct) obj;

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			return true;
		}

		public void copyDataTo(Itens_pedidoStruct other) {

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

		public void copyKeysDataTo(Itens_pedidoStruct other) {

			other.NumeroPedido = this.NumeroPedido;

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

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
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

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
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

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

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

				this.SequenciaItem = readInteger(dis, ois);

				this.CodigoProduto = readString(dis, ois);

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

				this.PrevEntr = readDate(dis, ois);

				this.AtdData = readDate(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.AtdQtde = null;
				} else {
					this.AtdQtde = dis.readFloat();
				}

				this.AtdDcto = readString(dis, ois);

				this.AtdData1 = readDate(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.AtdQtde1 = null;
				} else {
					this.AtdQtde1 = dis.readFloat();
				}

				this.AtdDcto1 = readString(dis, ois);

				this.AtdData2 = readDate(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.AtdQtde2 = null;
				} else {
					this.AtdQtde2 = dis.readFloat();
				}

				this.AtdDcto2 = readString(dis, ois);

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

				this.Tipo = readString(dis, ois);

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

				this.Lpreco = readString(dis, ois);

				this.Grade = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.TpGrade = null;
				} else {
					this.TpGrade = dis.readShort();
				}

				this.CodEtiq = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.Embalagem = null;
				} else {
					this.Embalagem = dis.readDouble();
				}

				this.MotCanc = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.PercComis = null;
				} else {
					this.PercComis = dis.readFloat();
				}

				this.PrdCfgCar = readString(dis, ois);

				this.Observacao = readString(dis, ois);

				this.BonifEcon = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.PcomisExt = null;
				} else {
					this.PcomisExt = dis.readDouble();
				}

				this.AplicDcto = readString(dis, ois);

				this.Operacao = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.QtdeRes = null;
				} else {
					this.QtdeRes = dis.readDouble();
				}

				this.Bloqsug = readString(dis, ois);

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

				this.SeqGrd = readString(dis, ois);

				this.SeqErp = readInteger(dis, ois);

				this.PrevCalc = readDate(dis, ois);

				this.Almoxarif = readString(dis, ois);

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

				this.Chave = readString(dis, ois);

				this.ItemConf = readString(dis, ois);

				this.CvdaDcti = readString(dis, ois);

				this.ObsNota = readString(dis, ois);

				this.ObsExped = readString(dis, ois);

				this.ObsSepar = readString(dis, ois);

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

				this.MotTroca = readString(dis, ois);

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

				this.CodPrMap = readString(dis, ois);

				this.CodCvdAg = readString(dis, ois);

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

				this.RestrBonif = readString(dis, ois);

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

				this.Dtprevento = readDate(dis, ois);

				this.Cfretit = readString(dis, ois);

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

				this.Backord = readString(dis, ois);

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

				this.Sitcorp1 = readString(dis, ois);

				this.Sitcorp2 = readString(dis, ois);

				this.LibMots = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.Dctoboleto = null;
				} else {
					this.Dctoboleto = dis.readDouble();
				}

				this.MPRISeq = readInteger(dis, ois);

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

				this.ProdRes = readString(dis, ois);

				this.EmpEstoq = readString(dis, ois);

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

				this.DataAlter = readDate(dis, ois);

				this.SeqOrig = readString(dis, ois);

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

				this.CodProdcli = readString(dis, ois);

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

				this.EmpSplit = readString(dis, ois);

				this.Status = readInteger(dis, ois);

				this.Obrigatorio = readInteger(dis, ois);

				this.PrecoFirme = readInteger(dis, ois);

				this.OrdCompra = readString(dis, ois);

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

				this.Data1Original = readDate(dis, ois);

				this.Visao = readInteger(dis, ois);

				this.Periodo = readInteger(dis, ois);

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

				this.CondPgto = readInteger(dis, ois);

				this.UsuCanc = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.Resrede = null;
				} else {
					this.Resrede = dis.readDouble();
				}

				this.DocBloq = readString(dis, ois);

				this.Regracomis = readString(dis, ois);

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

				this.Obs1 = readString(dis, ois);

				this.Obs2 = readString(dis, ois);

				this.CodEmbal = readString(dis, ois);

				this.CondPgtoCalc = readInteger(dis, ois);

				this.CodigoMix = readInteger(dis, ois);

				this.QtdeMix = readInteger(dis, ois);

				this.Lote = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.VlrDesctoFiscal = null;
				} else {
					this.VlrDesctoFiscal = dis.readDouble();
				}

				this.Tamanho = readString(dis, ois);

				this.BloqVerba = readInteger(dis, ois);

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

				this.VisaoCota = readInteger(dis, ois);

				this.PeriodoCota = readInteger(dis, ois);

				this.VisaoCotaOriginal = readInteger(dis, ois);

				this.PeriodoCotaOriginal = readInteger(dis, ois);

				this.RegraEmpItem = readInteger(dis, ois);

				this.LjPeca = readInteger(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.LjTamanho = null;
				} else {
					this.LjTamanho = dis.readDouble();
				}

				this.LjCarga = readInteger(dis, ois);

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

				this.LjAngulo = readInteger(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.LjMedidaB = null;
				} else {
					this.LjMedidaB = dis.readDouble();
				}

				this.LjInicioViga = readInteger(dis, ois);

				this.LjAba = readInteger(dis, ois);

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

				this.Bloqluc = readInteger(dis, ois);

				this.OrigemCota = readInteger(dis, ois);

				this.OrigemCanc = readInteger(dis, ois);

				this.Incsug = readInteger(dis, ois);

				this.LjIdent = readInteger(dis, ois);

				this.CondFreteIt = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.DesctotalCalc = null;
				} else {
					this.DesctotalCalc = dis.readDouble();
				}

				this.IdPedAuto = readString(dis, ois);

				this.LoteEstoque = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.DesctotalAplicado = null;
				} else {
					this.DesctotalAplicado = dis.readDouble();
				}

				this.SeqOrdemCompra = readInteger(dis, ois);

				this.SeqordCompra = readString(dis, ois);

				this.Ordcomest = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.ValorICMSFrete = null;
				} else {
					this.ValorICMSFrete = dis.readDouble();
				}

				this.Campo1 = readInteger(dis, ois);

				this.CentroCusto = readString(dis, ois);

				this.BloqDesc = readInteger(dis, ois);

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

				this.SeqCota = readInteger(dis, ois);

				this.SeqCotaOriginal = readInteger(dis, ois);

				this.DtRegraCorte = readDate(dis, ois);

				this.CompoeMargem = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.SequenciaItem = readInteger(dis, objectIn);

				this.CodigoProduto = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.QuantidadeProduto = null;
				} else {
					this.QuantidadeProduto = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QuantidadeAtendida = null;
				} else {
					this.QuantidadeAtendida = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QuantidadeCancelada = null;
				} else {
					this.QuantidadeCancelada = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PrecoUnitario = null;
				} else {
					this.PrecoUnitario = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PrecoLiquido = null;
				} else {
					this.PrecoLiquido = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Desctop = null;
				} else {
					this.Desctop = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.DesctoIcm = null;
				} else {
					this.DesctoIcm = objectIn.readDouble();
				}

				this.PrevEntr = readDate(dis, objectIn);

				this.AtdData = readDate(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.AtdQtde = null;
				} else {
					this.AtdQtde = objectIn.readFloat();
				}

				this.AtdDcto = readString(dis, objectIn);

				this.AtdData1 = readDate(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.AtdQtde1 = null;
				} else {
					this.AtdQtde1 = objectIn.readFloat();
				}

				this.AtdDcto1 = readString(dis, objectIn);

				this.AtdData2 = readDate(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.AtdQtde2 = null;
				} else {
					this.AtdQtde2 = objectIn.readFloat();
				}

				this.AtdDcto2 = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.SituacaoPedido = null;
				} else {
					this.SituacaoPedido = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Ipi = null;
				} else {
					this.Ipi = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Dctoprom = null;
				} else {
					this.Dctoprom = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.EconVlr = null;
				} else {
					this.EconVlr = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.EconCalc = null;
				} else {
					this.EconCalc = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.EconDcto = null;
				} else {
					this.EconDcto = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PrecoMin = null;
				} else {
					this.PrecoMin = objectIn.readFloat();
				}

				this.Tipo = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Dctovda = null;
				} else {
					this.Dctovda = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.TipoPreco = null;
				} else {
					this.TipoPreco = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.EstVlr = null;
				} else {
					this.EstVlr = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Acrescimo = null;
				} else {
					this.Acrescimo = objectIn.readDouble();
				}

				this.Lpreco = readString(dis, objectIn);

				this.Grade = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.TpGrade = null;
				} else {
					this.TpGrade = objectIn.readShort();
				}

				this.CodEtiq = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Embalagem = null;
				} else {
					this.Embalagem = objectIn.readDouble();
				}

				this.MotCanc = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.PercComis = null;
				} else {
					this.PercComis = objectIn.readFloat();
				}

				this.PrdCfgCar = readString(dis, objectIn);

				this.Observacao = readString(dis, objectIn);

				this.BonifEcon = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.PcomisExt = null;
				} else {
					this.PcomisExt = objectIn.readDouble();
				}

				this.AplicDcto = readString(dis, objectIn);

				this.Operacao = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdeRes = null;
				} else {
					this.QtdeRes = objectIn.readDouble();
				}

				this.Bloqsug = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Dctoinc = null;
				} else {
					this.Dctoinc = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrIpi = null;
				} else {
					this.VlrIpi = objectIn.readDouble();
				}

				this.SeqGrd = readString(dis, objectIn);

				this.SeqErp = readInteger(dis, objectIn);

				this.PrevCalc = readDate(dis, objectIn);

				this.Almoxarif = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.ComisGer = null;
				} else {
					this.ComisGer = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.CredProp = null;
				} else {
					this.CredProp = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.NroEnvio = null;
				} else {
					this.NroEnvio = objectIn.readDouble();
				}

				this.Chave = readString(dis, objectIn);

				this.ItemConf = readString(dis, objectIn);

				this.CvdaDcti = readString(dis, objectIn);

				this.ObsNota = readString(dis, objectIn);

				this.ObsExped = readString(dis, objectIn);

				this.ObsSepar = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrSubst = null;
				} else {
					this.VlrSubst = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.DesctoGer = null;
				} else {
					this.DesctoGer = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrFreTit = null;
				} else {
					this.VlrFreTit = objectIn.readDouble();
				}

				this.MotTroca = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VeconCig = null;
				} else {
					this.VeconCig = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.CustoMp = null;
				} else {
					this.CustoMp = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PliqAlt = null;
				} else {
					this.PliqAlt = objectIn.readShort();
				}

				this.CodPrMap = readString(dis, objectIn);

				this.CodCvdAg = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VuIcms = null;
				} else {
					this.VuIcms = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VuPis = null;
				} else {
					this.VuPis = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VuCofins = null;
				} else {
					this.VuCofins = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Altdtprev = null;
				} else {
					this.Altdtprev = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdeAloc = null;
				} else {
					this.QtdeAloc = objectIn.readDouble();
				}

				this.RestrBonif = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.CustoMo = null;
				} else {
					this.CustoMo = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.ItControl = null;
				} else {
					this.ItControl = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.ItPermBon = null;
				} else {
					this.ItPermBon = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdPermBon = null;
				} else {
					this.QtdPermBon = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrPermBon = null;
				} else {
					this.VlrPermBon = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Itemfemb = null;
				} else {
					this.Itemfemb = objectIn.readShort();
				}

				this.Dtprevento = readDate(dis, objectIn);

				this.Cfretit = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.SldVerba = null;
				} else {
					this.SldVerba = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrBonif = null;
				} else {
					this.VlrBonif = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Bonif = null;
				} else {
					this.Bonif = objectIn.readShort();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Tpproduto = null;
				} else {
					this.Tpproduto = objectIn.readShort();
				}

				this.Backord = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Markup = null;
				} else {
					this.Markup = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdeBkor = null;
				} else {
					this.QtdeBkor = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Prcpromoc = null;
				} else {
					this.Prcpromoc = objectIn.readDouble();
				}

				this.Sitcorp1 = readString(dis, objectIn);

				this.Sitcorp2 = readString(dis, objectIn);

				this.LibMots = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Dctoboleto = null;
				} else {
					this.Dctoboleto = objectIn.readDouble();
				}

				this.MPRISeq = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Dctomax = null;
				} else {
					this.Dctomax = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Comprim = null;
				} else {
					this.Comprim = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdePeca = null;
				} else {
					this.QtdePeca = objectIn.readDouble();
				}

				this.ProdRes = readString(dis, objectIn);

				this.EmpEstoq = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.PrevProduc = null;
				} else {
					this.PrevProduc = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PrunitOri = null;
				} else {
					this.PrunitOri = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PrliqOri = null;
				} else {
					this.PrliqOri = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Mva = null;
				} else {
					this.Mva = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.ICMSOrig = null;
				} else {
					this.ICMSOrig = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.ICMSDest = null;
				} else {
					this.ICMSDest = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Float1 = null;
				} else {
					this.Float1 = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Float2 = null;
				} else {
					this.Float2 = objectIn.readDouble();
				}

				this.DataAlter = readDate(dis, objectIn);

				this.SeqOrig = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.PrecoNorm = null;
				} else {
					this.PrecoNorm = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.Tipost = null;
				} else {
					this.Tipost = objectIn.readShort();
				}

				this.CodProdcli = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrDescarga = null;
				} else {
					this.VlrDescarga = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.IcmsDscto = null;
				} else {
					this.IcmsDscto = objectIn.readShort();
				}

				this.EmpSplit = readString(dis, objectIn);

				this.Status = readInteger(dis, objectIn);

				this.Obrigatorio = readInteger(dis, objectIn);

				this.PrecoFirme = readInteger(dis, objectIn);

				this.OrdCompra = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DesctoFiscal = null;
				} else {
					this.DesctoFiscal = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PercMlucr = null;
				} else {
					this.PercMlucr = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrDescargaCalc = null;
				} else {
					this.VlrDescargaCalc = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrFreTitCalc = null;
				} else {
					this.VlrFreTitCalc = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VuComissao = null;
				} else {
					this.VuComissao = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VuDebcli = null;
				} else {
					this.VuDebcli = objectIn.readDouble();
				}

				this.Data1Original = readDate(dis, objectIn);

				this.Visao = readInteger(dis, objectIn);

				this.Periodo = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.BaseIcms = null;
				} else {
					this.BaseIcms = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.BaseSt = null;
				} else {
					this.BaseSt = objectIn.readDouble();
				}

				this.CondPgto = readInteger(dis, objectIn);

				this.UsuCanc = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.Resrede = null;
				} else {
					this.Resrede = objectIn.readDouble();
				}

				this.DocBloq = readString(dis, objectIn);

				this.Regracomis = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.BaseIcmsRed = null;
				} else {
					this.BaseIcmsRed = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.IcmsSemRed = null;
				} else {
					this.IcmsSemRed = objectIn.readDouble();
				}

				this.Obs1 = readString(dis, objectIn);

				this.Obs2 = readString(dis, objectIn);

				this.CodEmbal = readString(dis, objectIn);

				this.CondPgtoCalc = readInteger(dis, objectIn);

				this.CodigoMix = readInteger(dis, objectIn);

				this.QtdeMix = readInteger(dis, objectIn);

				this.Lote = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrDesctoFiscal = null;
				} else {
					this.VlrDesctoFiscal = objectIn.readDouble();
				}

				this.Tamanho = readString(dis, objectIn);

				this.BloqVerba = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrFreTitRateio = null;
				} else {
					this.VlrFreTitRateio = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.VlrFreTitRateioCalc = null;
				} else {
					this.VlrFreTitRateioCalc = objectIn.readDouble();
				}

				this.VisaoCota = readInteger(dis, objectIn);

				this.PeriodoCota = readInteger(dis, objectIn);

				this.VisaoCotaOriginal = readInteger(dis, objectIn);

				this.PeriodoCotaOriginal = readInteger(dis, objectIn);

				this.RegraEmpItem = readInteger(dis, objectIn);

				this.LjPeca = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.LjTamanho = null;
				} else {
					this.LjTamanho = objectIn.readDouble();
				}

				this.LjCarga = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.LjMedidaA = null;
				} else {
					this.LjMedidaA = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.LjVao = null;
				} else {
					this.LjVao = objectIn.readDouble();
				}

				this.LjAngulo = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.LjMedidaB = null;
				} else {
					this.LjMedidaB = objectIn.readDouble();
				}

				this.LjInicioViga = readInteger(dis, objectIn);

				this.LjAba = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.LjAbaA = null;
				} else {
					this.LjAbaA = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.LjAbaB = null;
				} else {
					this.LjAbaB = objectIn.readDouble();
				}

				this.Bloqluc = readInteger(dis, objectIn);

				this.OrigemCota = readInteger(dis, objectIn);

				this.OrigemCanc = readInteger(dis, objectIn);

				this.Incsug = readInteger(dis, objectIn);

				this.LjIdent = readInteger(dis, objectIn);

				this.CondFreteIt = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DesctotalCalc = null;
				} else {
					this.DesctotalCalc = objectIn.readDouble();
				}

				this.IdPedAuto = readString(dis, objectIn);

				this.LoteEstoque = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.DesctotalAplicado = null;
				} else {
					this.DesctotalAplicado = objectIn.readDouble();
				}

				this.SeqOrdemCompra = readInteger(dis, objectIn);

				this.SeqordCompra = readString(dis, objectIn);

				this.Ordcomest = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.ValorICMSFrete = null;
				} else {
					this.ValorICMSFrete = objectIn.readDouble();
				}

				this.Campo1 = readInteger(dis, objectIn);

				this.CentroCusto = readString(dis, objectIn);

				this.BloqDesc = readInteger(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.QtdeDescBonif = null;
				} else {
					this.QtdeDescBonif = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.PercDescBonif = null;
				} else {
					this.PercDescBonif = objectIn.readDouble();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.ValorDesccUnit = null;
				} else {
					this.ValorDesccUnit = objectIn.readDouble();
				}

				this.SeqCota = readInteger(dis, objectIn);

				this.SeqCotaOriginal = readInteger(dis, objectIn);

				this.DtRegraCorte = readDate(dis, objectIn);

				this.CompoeMargem = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.SequenciaItem, dos, oos);

				writeString(this.CodigoProduto, dos, oos);

				if (this.QuantidadeProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeProduto);
				}

				if (this.QuantidadeAtendida == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeAtendida);
				}

				if (this.QuantidadeCancelada == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.QuantidadeCancelada);
				}

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoUnitario);
				}

				if (this.PrecoLiquido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoLiquido);
				}

				if (this.Desctop == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Desctop);
				}

				if (this.DesctoIcm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoIcm);
				}

				writeDate(this.PrevEntr, dos, oos);

				writeDate(this.AtdData, dos, oos);

				if (this.AtdQtde == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde);
				}

				writeString(this.AtdDcto, dos, oos);

				writeDate(this.AtdData1, dos, oos);

				if (this.AtdQtde1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde1);
				}

				writeString(this.AtdDcto1, dos, oos);

				writeDate(this.AtdData2, dos, oos);

				if (this.AtdQtde2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.AtdQtde2);
				}

				writeString(this.AtdDcto2, dos, oos);

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				if (this.Ipi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Ipi);
				}

				if (this.Dctoprom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoprom);
				}

				if (this.EconVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconVlr);
				}

				if (this.EconCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EconCalc);
				}

				if (this.EconDcto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EconDcto);
				}

				if (this.PrecoMin == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrecoMin);
				}

				writeString(this.Tipo, dos, oos);

				if (this.Dctovda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctovda);
				}

				if (this.TipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TipoPreco);
				}

				if (this.EstVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.EstVlr);
				}

				if (this.Acrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Acrescimo);
				}

				writeString(this.Lpreco, dos, oos);

				writeString(this.Grade, dos, oos);

				if (this.TpGrade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.TpGrade);
				}

				writeString(this.CodEtiq, dos, oos);

				if (this.Embalagem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Embalagem);
				}

				writeString(this.MotCanc, dos, oos);

				if (this.PercComis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PercComis);
				}

				writeString(this.PrdCfgCar, dos, oos);

				writeString(this.Observacao, dos, oos);

				writeString(this.BonifEcon, dos, oos);

				if (this.PcomisExt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PcomisExt);
				}

				writeString(this.AplicDcto, dos, oos);

				writeString(this.Operacao, dos, oos);

				if (this.QtdeRes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeRes);
				}

				writeString(this.Bloqsug, dos, oos);

				if (this.Dctoinc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.Dctoinc);
				}

				if (this.VlrIpi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrIpi);
				}

				writeString(this.SeqGrd, dos, oos);

				writeInteger(this.SeqErp, dos, oos);

				writeDate(this.PrevCalc, dos, oos);

				writeString(this.Almoxarif, dos, oos);

				if (this.ComisGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComisGer);
				}

				if (this.CredProp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CredProp);
				}

				if (this.NroEnvio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NroEnvio);
				}

				writeString(this.Chave, dos, oos);

				writeString(this.ItemConf, dos, oos);

				writeString(this.CvdaDcti, dos, oos);

				writeString(this.ObsNota, dos, oos);

				writeString(this.ObsExped, dos, oos);

				writeString(this.ObsSepar, dos, oos);

				if (this.VlrSubst == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrSubst);
				}

				if (this.DesctoGer == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoGer);
				}

				if (this.VlrFreTit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTit);
				}

				writeString(this.MotTroca, dos, oos);

				if (this.VeconCig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VeconCig);
				}

				if (this.CustoMp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMp);
				}

				if (this.PliqAlt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.PliqAlt);
				}

				writeString(this.CodPrMap, dos, oos);

				writeString(this.CodCvdAg, dos, oos);

				if (this.VuIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuIcms);
				}

				if (this.VuPis == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuPis);
				}

				if (this.VuCofins == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuCofins);
				}

				if (this.Altdtprev == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Altdtprev);
				}

				if (this.QtdeAloc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeAloc);
				}

				writeString(this.RestrBonif, dos, oos);

				if (this.CustoMo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoMo);
				}

				if (this.ItControl == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItControl);
				}

				if (this.ItPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.ItPermBon);
				}

				if (this.QtdPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdPermBon);
				}

				if (this.VlrPermBon == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrPermBon);
				}

				if (this.Itemfemb == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Itemfemb);
				}

				writeDate(this.Dtprevento, dos, oos);

				writeString(this.Cfretit, dos, oos);

				if (this.SldVerba == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SldVerba);
				}

				if (this.VlrBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrBonif);
				}

				if (this.Bonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Bonif);
				}

				if (this.Tpproduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tpproduto);
				}

				writeString(this.Backord, dos, oos);

				if (this.Markup == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Markup);
				}

				if (this.QtdeBkor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeBkor);
				}

				if (this.Prcpromoc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Prcpromoc);
				}

				writeString(this.Sitcorp1, dos, oos);

				writeString(this.Sitcorp2, dos, oos);

				writeString(this.LibMots, dos, oos);

				if (this.Dctoboleto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctoboleto);
				}

				writeInteger(this.MPRISeq, dos, oos);

				if (this.Dctomax == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Dctomax);
				}

				if (this.Comprim == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comprim);
				}

				if (this.QtdePeca == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdePeca);
				}

				writeString(this.ProdRes, dos, oos);

				writeString(this.EmpEstoq, dos, oos);

				if (this.PrevProduc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrevProduc);
				}

				if (this.PrunitOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrunitOri);
				}

				if (this.PrliqOri == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.PrliqOri);
				}

				if (this.Mva == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Mva);
				}

				if (this.ICMSOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSOrig);
				}

				if (this.ICMSDest == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDest);
				}

				if (this.Float1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float1);
				}

				if (this.Float2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Float2);
				}

				writeDate(this.DataAlter, dos, oos);

				writeString(this.SeqOrig, dos, oos);

				if (this.PrecoNorm == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoNorm);
				}

				if (this.Tipost == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.Tipost);
				}

				writeString(this.CodProdcli, dos, oos);

				if (this.VlrDescarga == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescarga);
				}

				if (this.IcmsDscto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.IcmsDscto);
				}

				writeString(this.EmpSplit, dos, oos);

				writeInteger(this.Status, dos, oos);

				writeInteger(this.Obrigatorio, dos, oos);

				writeInteger(this.PrecoFirme, dos, oos);

				writeString(this.OrdCompra, dos, oos);

				if (this.DesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctoFiscal);
				}

				if (this.PercMlucr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercMlucr);
				}

				if (this.VlrDescargaCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDescargaCalc);
				}

				if (this.VlrFreTitCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitCalc);
				}

				if (this.VuComissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuComissao);
				}

				if (this.VuDebcli == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VuDebcli);
				}

				writeDate(this.Data1Original, dos, oos);

				writeInteger(this.Visao, dos, oos);

				writeInteger(this.Periodo, dos, oos);

				if (this.BaseIcms == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcms);
				}

				if (this.BaseSt == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseSt);
				}

				writeInteger(this.CondPgto, dos, oos);

				writeString(this.UsuCanc, dos, oos);

				if (this.Resrede == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Resrede);
				}

				writeString(this.DocBloq, dos, oos);

				writeString(this.Regracomis, dos, oos);

				if (this.BaseIcmsRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIcmsRed);
				}

				if (this.IcmsSemRed == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IcmsSemRed);
				}

				writeString(this.Obs1, dos, oos);

				writeString(this.Obs2, dos, oos);

				writeString(this.CodEmbal, dos, oos);

				writeInteger(this.CondPgtoCalc, dos, oos);

				writeInteger(this.CodigoMix, dos, oos);

				writeInteger(this.QtdeMix, dos, oos);

				writeString(this.Lote, dos, oos);

				if (this.VlrDesctoFiscal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrDesctoFiscal);
				}

				writeString(this.Tamanho, dos, oos);

				writeInteger(this.BloqVerba, dos, oos);

				if (this.VlrFreTitRateio == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateio);
				}

				if (this.VlrFreTitRateioCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VlrFreTitRateioCalc);
				}

				writeInteger(this.VisaoCota, dos, oos);

				writeInteger(this.PeriodoCota, dos, oos);

				writeInteger(this.VisaoCotaOriginal, dos, oos);

				writeInteger(this.PeriodoCotaOriginal, dos, oos);

				writeInteger(this.RegraEmpItem, dos, oos);

				writeInteger(this.LjPeca, dos, oos);

				if (this.LjTamanho == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjTamanho);
				}

				writeInteger(this.LjCarga, dos, oos);

				if (this.LjMedidaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaA);
				}

				if (this.LjVao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjVao);
				}

				writeInteger(this.LjAngulo, dos, oos);

				if (this.LjMedidaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjMedidaB);
				}

				writeInteger(this.LjInicioViga, dos, oos);

				writeInteger(this.LjAba, dos, oos);

				if (this.LjAbaA == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaA);
				}

				if (this.LjAbaB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LjAbaB);
				}

				writeInteger(this.Bloqluc, dos, oos);

				writeInteger(this.OrigemCota, dos, oos);

				writeInteger(this.OrigemCanc, dos, oos);

				writeInteger(this.Incsug, dos, oos);

				writeInteger(this.LjIdent, dos, oos);

				writeString(this.CondFreteIt, dos, oos);

				if (this.DesctotalCalc == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalCalc);
				}

				writeString(this.IdPedAuto, dos, oos);

				writeString(this.LoteEstoque, dos, oos);

				if (this.DesctotalAplicado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DesctotalAplicado);
				}

				writeInteger(this.SeqOrdemCompra, dos, oos);

				writeString(this.SeqordCompra, dos, oos);

				writeString(this.Ordcomest, dos, oos);

				if (this.ValorICMSFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMSFrete);
				}

				writeInteger(this.Campo1, dos, oos);

				writeString(this.CentroCusto, dos, oos);

				writeInteger(this.BloqDesc, dos, oos);

				if (this.QtdeDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QtdeDescBonif);
				}

				if (this.PercDescBonif == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PercDescBonif);
				}

				if (this.ValorDesccUnit == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDesccUnit);
				}

				writeInteger(this.SeqCota, dos, oos);

				writeInteger(this.SeqCotaOriginal, dos, oos);

				writeDate(this.DtRegraCorte, dos, oos);

				writeInteger(this.CompoeMargem, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.SequenciaItem, dos, objectOut);

				writeString(this.CodigoProduto, dos, objectOut);

				if (this.QuantidadeProduto == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.QuantidadeProduto);
				}

				if (this.QuantidadeAtendida == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.QuantidadeAtendida);
				}

				if (this.QuantidadeCancelada == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.QuantidadeCancelada);
				}

				if (this.PrecoUnitario == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PrecoUnitario);
				}

				if (this.PrecoLiquido == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PrecoLiquido);
				}

				if (this.Desctop == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.Desctop);
				}

				if (this.DesctoIcm == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DesctoIcm);
				}

				writeDate(this.PrevEntr, dos, objectOut);

				writeDate(this.AtdData, dos, objectOut);

				if (this.AtdQtde == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.AtdQtde);
				}

				writeString(this.AtdDcto, dos, objectOut);

				writeDate(this.AtdData1, dos, objectOut);

				if (this.AtdQtde1 == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.AtdQtde1);
				}

				writeString(this.AtdDcto1, dos, objectOut);

				writeDate(this.AtdData2, dos, objectOut);

				if (this.AtdQtde2 == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.AtdQtde2);
				}

				writeString(this.AtdDcto2, dos, objectOut);

				if (this.SituacaoPedido == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.SituacaoPedido);
				}

				if (this.Ipi == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Ipi);
				}

				if (this.Dctoprom == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.Dctoprom);
				}

				if (this.EconVlr == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.EconVlr);
				}

				if (this.EconCalc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.EconCalc);
				}

				if (this.EconDcto == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.EconDcto);
				}

				if (this.PrecoMin == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PrecoMin);
				}

				writeString(this.Tipo, dos, objectOut);

				if (this.Dctovda == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Dctovda);
				}

				if (this.TipoPreco == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.TipoPreco);
				}

				if (this.EstVlr == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.EstVlr);
				}

				if (this.Acrescimo == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Acrescimo);
				}

				writeString(this.Lpreco, dos, objectOut);

				writeString(this.Grade, dos, objectOut);

				if (this.TpGrade == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.TpGrade);
				}

				writeString(this.CodEtiq, dos, objectOut);

				if (this.Embalagem == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Embalagem);
				}

				writeString(this.MotCanc, dos, objectOut);

				if (this.PercComis == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PercComis);
				}

				writeString(this.PrdCfgCar, dos, objectOut);

				writeString(this.Observacao, dos, objectOut);

				writeString(this.BonifEcon, dos, objectOut);

				if (this.PcomisExt == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.PcomisExt);
				}

				writeString(this.AplicDcto, dos, objectOut);

				writeString(this.Operacao, dos, objectOut);

				if (this.QtdeRes == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdeRes);
				}

				writeString(this.Bloqsug, dos, objectOut);

				if (this.Dctoinc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.Dctoinc);
				}

				if (this.VlrIpi == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrIpi);
				}

				writeString(this.SeqGrd, dos, objectOut);

				writeInteger(this.SeqErp, dos, objectOut);

				writeDate(this.PrevCalc, dos, objectOut);

				writeString(this.Almoxarif, dos, objectOut);

				if (this.ComisGer == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ComisGer);
				}

				if (this.CredProp == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.CredProp);
				}

				if (this.NroEnvio == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.NroEnvio);
				}

				writeString(this.Chave, dos, objectOut);

				writeString(this.ItemConf, dos, objectOut);

				writeString(this.CvdaDcti, dos, objectOut);

				writeString(this.ObsNota, dos, objectOut);

				writeString(this.ObsExped, dos, objectOut);

				writeString(this.ObsSepar, dos, objectOut);

				if (this.VlrSubst == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrSubst);
				}

				if (this.DesctoGer == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DesctoGer);
				}

				if (this.VlrFreTit == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrFreTit);
				}

				writeString(this.MotTroca, dos, objectOut);

				if (this.VeconCig == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VeconCig);
				}

				if (this.CustoMp == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.CustoMp);
				}

				if (this.PliqAlt == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.PliqAlt);
				}

				writeString(this.CodPrMap, dos, objectOut);

				writeString(this.CodCvdAg, dos, objectOut);

				if (this.VuIcms == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VuIcms);
				}

				if (this.VuPis == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VuPis);
				}

				if (this.VuCofins == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VuCofins);
				}

				if (this.Altdtprev == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.Altdtprev);
				}

				if (this.QtdeAloc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdeAloc);
				}

				writeString(this.RestrBonif, dos, objectOut);

				if (this.CustoMo == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.CustoMo);
				}

				if (this.ItControl == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.ItControl);
				}

				if (this.ItPermBon == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.ItPermBon);
				}

				if (this.QtdPermBon == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdPermBon);
				}

				if (this.VlrPermBon == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrPermBon);
				}

				if (this.Itemfemb == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.Itemfemb);
				}

				writeDate(this.Dtprevento, dos, objectOut);

				writeString(this.Cfretit, dos, objectOut);

				if (this.SldVerba == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.SldVerba);
				}

				if (this.VlrBonif == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrBonif);
				}

				if (this.Bonif == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.Bonif);
				}

				if (this.Tpproduto == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.Tpproduto);
				}

				writeString(this.Backord, dos, objectOut);

				if (this.Markup == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Markup);
				}

				if (this.QtdeBkor == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdeBkor);
				}

				if (this.Prcpromoc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Prcpromoc);
				}

				writeString(this.Sitcorp1, dos, objectOut);

				writeString(this.Sitcorp2, dos, objectOut);

				writeString(this.LibMots, dos, objectOut);

				if (this.Dctoboleto == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Dctoboleto);
				}

				writeInteger(this.MPRISeq, dos, objectOut);

				if (this.Dctomax == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Dctomax);
				}

				if (this.Comprim == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Comprim);
				}

				if (this.QtdePeca == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdePeca);
				}

				writeString(this.ProdRes, dos, objectOut);

				writeString(this.EmpEstoq, dos, objectOut);

				if (this.PrevProduc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.PrevProduc);
				}

				if (this.PrunitOri == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PrunitOri);
				}

				if (this.PrliqOri == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.PrliqOri);
				}

				if (this.Mva == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Mva);
				}

				if (this.ICMSOrig == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ICMSOrig);
				}

				if (this.ICMSDest == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ICMSDest);
				}

				if (this.Float1 == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Float1);
				}

				if (this.Float2 == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Float2);
				}

				writeDate(this.DataAlter, dos, objectOut);

				writeString(this.SeqOrig, dos, objectOut);

				if (this.PrecoNorm == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.PrecoNorm);
				}

				if (this.Tipost == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.Tipost);
				}

				writeString(this.CodProdcli, dos, objectOut);

				if (this.VlrDescarga == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrDescarga);
				}

				if (this.IcmsDscto == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeShort(this.IcmsDscto);
				}

				writeString(this.EmpSplit, dos, objectOut);

				writeInteger(this.Status, dos, objectOut);

				writeInteger(this.Obrigatorio, dos, objectOut);

				writeInteger(this.PrecoFirme, dos, objectOut);

				writeString(this.OrdCompra, dos, objectOut);

				if (this.DesctoFiscal == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DesctoFiscal);
				}

				if (this.PercMlucr == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.PercMlucr);
				}

				if (this.VlrDescargaCalc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrDescargaCalc);
				}

				if (this.VlrFreTitCalc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrFreTitCalc);
				}

				if (this.VuComissao == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VuComissao);
				}

				if (this.VuDebcli == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VuDebcli);
				}

				writeDate(this.Data1Original, dos, objectOut);

				writeInteger(this.Visao, dos, objectOut);

				writeInteger(this.Periodo, dos, objectOut);

				if (this.BaseIcms == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.BaseIcms);
				}

				if (this.BaseSt == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.BaseSt);
				}

				writeInteger(this.CondPgto, dos, objectOut);

				writeString(this.UsuCanc, dos, objectOut);

				if (this.Resrede == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.Resrede);
				}

				writeString(this.DocBloq, dos, objectOut);

				writeString(this.Regracomis, dos, objectOut);

				if (this.BaseIcmsRed == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.BaseIcmsRed);
				}

				if (this.IcmsSemRed == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.IcmsSemRed);
				}

				writeString(this.Obs1, dos, objectOut);

				writeString(this.Obs2, dos, objectOut);

				writeString(this.CodEmbal, dos, objectOut);

				writeInteger(this.CondPgtoCalc, dos, objectOut);

				writeInteger(this.CodigoMix, dos, objectOut);

				writeInteger(this.QtdeMix, dos, objectOut);

				writeString(this.Lote, dos, objectOut);

				if (this.VlrDesctoFiscal == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrDesctoFiscal);
				}

				writeString(this.Tamanho, dos, objectOut);

				writeInteger(this.BloqVerba, dos, objectOut);

				if (this.VlrFreTitRateio == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrFreTitRateio);
				}

				if (this.VlrFreTitRateioCalc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.VlrFreTitRateioCalc);
				}

				writeInteger(this.VisaoCota, dos, objectOut);

				writeInteger(this.PeriodoCota, dos, objectOut);

				writeInteger(this.VisaoCotaOriginal, dos, objectOut);

				writeInteger(this.PeriodoCotaOriginal, dos, objectOut);

				writeInteger(this.RegraEmpItem, dos, objectOut);

				writeInteger(this.LjPeca, dos, objectOut);

				if (this.LjTamanho == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjTamanho);
				}

				writeInteger(this.LjCarga, dos, objectOut);

				if (this.LjMedidaA == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjMedidaA);
				}

				if (this.LjVao == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjVao);
				}

				writeInteger(this.LjAngulo, dos, objectOut);

				if (this.LjMedidaB == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjMedidaB);
				}

				writeInteger(this.LjInicioViga, dos, objectOut);

				writeInteger(this.LjAba, dos, objectOut);

				if (this.LjAbaA == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjAbaA);
				}

				if (this.LjAbaB == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.LjAbaB);
				}

				writeInteger(this.Bloqluc, dos, objectOut);

				writeInteger(this.OrigemCota, dos, objectOut);

				writeInteger(this.OrigemCanc, dos, objectOut);

				writeInteger(this.Incsug, dos, objectOut);

				writeInteger(this.LjIdent, dos, objectOut);

				writeString(this.CondFreteIt, dos, objectOut);

				if (this.DesctotalCalc == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DesctotalCalc);
				}

				writeString(this.IdPedAuto, dos, objectOut);

				writeString(this.LoteEstoque, dos, objectOut);

				if (this.DesctotalAplicado == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.DesctotalAplicado);
				}

				writeInteger(this.SeqOrdemCompra, dos, objectOut);

				writeString(this.SeqordCompra, dos, objectOut);

				writeString(this.Ordcomest, dos, objectOut);

				if (this.ValorICMSFrete == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ValorICMSFrete);
				}

				writeInteger(this.Campo1, dos, objectOut);

				writeString(this.CentroCusto, dos, objectOut);

				writeInteger(this.BloqDesc, dos, objectOut);

				if (this.QtdeDescBonif == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.QtdeDescBonif);
				}

				if (this.PercDescBonif == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.PercDescBonif);
				}

				if (this.ValorDesccUnit == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.ValorDesccUnit);
				}

				writeInteger(this.SeqCota, dos, objectOut);

				writeInteger(this.SeqCotaOriginal, dos, objectOut);

				writeDate(this.DtRegraCorte, dos, objectOut);

				writeInteger(this.CompoeMargem, dos, objectOut);

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
		public int compareTo(Itens_pedidoStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
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
		org.slf4j.MDC.put("_subJobPid", "EQgsTk_" + subJobPidCounter.getAndIncrement());

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

				Itens_pedidoStruct Itens_pedido = new Itens_pedidoStruct();

				/**
				 * [tAdvancedHash_Itens_pedido begin ] start
				 */

				ok_Hash.put("tAdvancedHash_Itens_pedido", false);
				start_Hash.put("tAdvancedHash_Itens_pedido", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_Itens_pedido";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Itens_pedido");

				int tos_count_tAdvancedHash_Itens_pedido = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_Itens_pedido", "tAdvancedHash_Itens_pedido", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:Itens_pedido
				// source node:tDBInput_1 - inputs:(after_tDBInput_2)
				// outputs:(Itens_pedido,Itens_pedido) | target node:tAdvancedHash_Itens_pedido
				// - inputs:(Itens_pedido) outputs:()
				// linked node: tMap_1 - inputs:(Itens_pedido,Capa_pedido,TIpo_operacao)
				// outputs:(Carteira)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_Itens_pedido = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<Itens_pedidoStruct> tHash_Lookup_Itens_pedido = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<Itens_pedidoStruct>getLookup(matchingModeEnum_Itens_pedido);

				globalMap.put("tHash_Lookup_Itens_pedido", tHash_Lookup_Itens_pedido);

				/**
				 * [tAdvancedHash_Itens_pedido begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				cLabel = "Itens_do_pedido";

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
									"enc:routine.encryption.key.v1:Rxou34y4a7b6aGIJzT7WkR3++KKlulh28WZd3n4rxareAR2H+cis6+Y2")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TABLE" + " = " + "\"DB_PEDIDO_PROD\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERY" + " = "
									+ "\"SELECT     DB_PEDI_PEDIDO AS NumeroPedido,    DB_PEDI_SEQUENCIA AS SequenciaItem,    DB_PEDI_PRODUTO AS CodigoProduto,    DB_PEDI_QTDE_SOLIC AS QuantidadeProduto,    DB_PEDI_QTDE_ATEND AS QuantidadeAtendida,    DB_PEDI_QTDE_CANC AS QuantidadeCancelada,    DB_PEDI_PRECO_UNIT AS PrecoUnitario,    DB_PEDI_PRECO_LIQ AS PrecoLiquido,    DB_PEDI_DESCTOP AS Desctop,    DB_PEDI_DESCTO_ICM AS DesctoIcm,    CAST(DB_PEDI_PREV_ENTR AS DATE) AS PrevEntr,    CAST(DB_PEDI_ATD_DATA AS DATE) AS AtdData,    DB_PEDI_ATD_QTDE AS AtdQtde,    DB_PEDI_ATD_DCTO AS AtdDcto,    DB_PEDI_ATD_DATA1 AS AtdData1,    DB_PEDI_ATD_QTDE1 AS AtdQtde1,    DB_PEDI_ATD_DCTO1 AS AtdDcto1,    DB_PEDI_ATD_DATA2 AS AtdData2,    DB_PEDI_ATD_QTDE2 AS AtdQtde2,    DB_PEDI_ATD_DCTO2 AS AtdDcto2,    DB_PEDI_SITUACAO AS SituacaoPedido,    DB_PEDI_IPI AS Ipi,    DB_PEDI_DCTOPROM AS Dctoprom,    DB_PEDI_ECON_VLR AS EconVlr,    DB_PEDI_ECON_CALC AS EconCalc,    DB_PEDI_ECON_DCTO AS EconDcto,    DB_PEDI_PRECO_MIN AS PrecoMin,    DB_PEDI_TIPO AS Tipo,    DB_PEDI_DCTOVDA AS Dctovda,    DB_PEDI_TIPO_PRECO AS TipoPreco,    DB_PEDI_ESTVLR AS EstVlr,    DB_PEDI_ACRESCIMO AS Acrescimo,    DB_PEDI_LPRECO AS Lpreco,    DB_PEDI_GRADE AS Grade,    DB_PEDI_TP_GRADE AS TpGrade,    DB_PEDI_COD_ETIQ AS CodEtiq,    DB_PEDI_EMBALAGEM AS Embalagem,    DB_PEDI_MOTCANC AS MotCanc,    DB_PEDI_PERC_COMIS AS PercComis,    DB_PEDI_PRDCFGCAR AS PrdCfgCar,    DB_PEDI_OBSERV AS Observacao,    DB_PEDI_BONIFECON AS BonifEcon,    DB_PEDI_PCOMIS_EXT AS PcomisExt,    DB_PEDI_APLICDCTO AS AplicDcto,    DB_PEDI_OPERACAO AS Operacao,    DB_PEDI_QTDE_RES AS QtdeRes,    DB_PEDI_BLOQSUG AS Bloqsug,    DB_PEDI_DCTOINC AS Dctoinc,    DB_PEDI_VLRIPI AS VlrIpi,    DB_PEDI_SEQGRD AS SeqGrd,    DB_PEDI_SEQ_ERP AS SeqErp,    DB_PEDI_PREV_CALC AS PrevCalc,    DB_PEDI_ALMOXARIF AS Almoxarif,    DB_PEDI_COMIS_GER AS ComisGer,    DB_PEDI_CRED_PROP AS CredProp,    DB_PEDI_NRO_ENVIO AS NroEnvio,    DB_PEDI_CHAVE AS Chave,    DB_PEDI_ITEMCONF AS ItemConf,    DB_PEDI_CVDA_DCTI AS CvdaDcti,    DB_PEDI_OBS_NOTA AS ObsNota,    DB_PEDI_OBS_EXPED AS ObsExped,    DB_PEDI_OBS_SEPAR AS ObsSepar,    DB_PEDI_VLR_SUBST AS VlrSubst,    DB_PEDI_DESCTO_GER AS DesctoGer,    DB_PEDI_VLR_FRETIT AS VlrFreTit,    DB_PEDI_MOT_TROCA AS MotTroca,    DB_PEDI_VECON_CIG AS VeconCig,    DB_PEDI_CUSTO_MP AS CustoMp,    DB_PEDI_PLIQ_ALT AS PliqAlt,    DB_PEDI_CODPRMAP AS CodPrMap,    DB_PEDI_CODCVDAG AS CodCvdAg,    DB_PEDI_VU_ICMS AS VuIcms,    DB_PEDI_VU_PIS AS VuPis,    DB_PEDI_VU_COFINS AS VuCofins,    DB_PEDI_ALTDTPREV AS Altdtprev,    DB_PEDI_QTDE_ALOC AS QtdeAloc,    DB_PEDI_RESTRBONIF AS RestrBonif,    DB_PEDI_CUSTO_MO AS CustoMo,    DB_PEDI_ITCONTROL AS ItControl,    DB_PEDI_ITPERMBON AS ItPermBon,    DB_PEDI_QTDPERMBON AS QtdPermBon,    DB_PEDI_VLRPERMBON AS VlrPermBon,    DB_PEDI_ITEMFEMB AS Itemfemb,    CAST(DB_PEDI_DTPREVENTO AS DATE) AS Dtprevento,    DB_PEDI_CFRETIT AS Cfretit,    DB_PEDI_SLDVERBA AS SldVerba,    DB_PEDI_VLRBONIF AS VlrBonif,    DB_PEDI_BONIF AS Bonif,    DB_PEDI_TPPRODUTO AS Tpproduto,    DB_PEDI_BACKORD AS Backord,    DB_PEDI_MARKUP AS Markup,    DB_PEDI_QTDE_BKOR AS QtdeBkor,    DB_PEDI_PRCPROMOC AS Prcpromoc,    DB_PEDI_SITCORP1 AS Sitcorp1,    DB_PEDI_SITCORP2 AS Sitcorp2,    DB_PEDI_LIB_MOTS AS LibMots,    DB_PEDI_DCTOBOLETO AS Dctoboleto,    DB_PedI_MPRISeq AS MPRISeq,    DB_PEDI_DCTOMAX AS Dctomax,    DB_PedI_Comprim AS Comprim,    DB_PedI_QtdePeca AS QtdePeca,    DB_PedI_Prod_Res AS ProdRes,    DB_PEDI_EMPESTOQ AS EmpEstoq,    DB_Pedi_PrevProduc AS PrevProduc,    DB_PEDI_PRUNIT_ORI AS PrunitOri,    DB_PEDI_PRLIQ_ORI AS PrliqOri,    Db_PedI_MVA AS Mva,    DB_PedI_ICMSOrig AS ICMSOrig,    Db_PedI_ICMSDest AS ICMSDest,    DB_PEDI_FLOAT1 AS Float1,    DB_PEDI_FLOAT2 AS Float2,    DB_PEDI_DATA_ALTER AS DataAlter,    DB_PEDI_SEQ_ORIG AS SeqOrig,    DB_PEDI_PRECO_NORM AS PrecoNorm,    DB_PEDI_TIPOST AS Tipost,    DB_PEDI_COD_PRODCLI AS CodProdcli,    DB_PEDI_VLR_DESCARGA AS VlrDescarga,    DB_PEDI_IcmsDscto AS IcmsDscto,    DB_PEDI_EmpSplit AS EmpSplit,    db_pedi_status AS Status,    DB_PEDI_OBRIGATORIO AS Obrigatorio,    DB_PEDI_PRECO_FIRME AS PrecoFirme,    DB_PEDI_ORD_COMPRA AS OrdCompra,    DB_PEDI_DESCTO_FISCAL AS DesctoFiscal,    DB_PEDI_PERC_MLUCR AS PercMlucr,    DB_PEDI_VLR_DESCARGA_CALC AS VlrDescargaCalc,    DB_PEDI_VLR_FRETIT_CALC AS VlrFreTitCalc,    DB_PEDI_VU_COMISSAO AS VuComissao,    DB_PEDI_VU_DEBCLI AS VuDebcli,    CAST(DB_PEDI_DATA1_ORIGINAL AS DATE) AS Data1Original,    DB_PEDI_VISAO AS Visao,    DB_PEDI_PERIODO AS Periodo,    DB_PEDI_BASEICMS AS BaseIcms,    DB_PEDI_BASEST AS BaseSt,    DB_PEDI_COND_PGTO AS CondPgto,    DB_PEDI_USU_CANC AS UsuCanc,    DB_PEDI_RESREDE AS Resrede,    DB_PEDI_DOC_BLOQ AS DocBloq,    DB_PEDI_REGRACOMIS AS Regracomis,    DB_PEDI_BASEICMSRED AS BaseIcmsRed,    DB_PEDI_ICMSSEMRED AS IcmsSemRed,    DB_PEDI_OBS1 AS Obs1,    DB_PEDI_OBS2 AS Obs2,    DB_PEDI_CODEMBAL AS CodEmbal,    DB_PEDI_COND_PGTOCALC AS CondPgtoCalc,    DB_PEDI_CODIGO_MIX AS CodigoMix,    DB_PEDI_QTDE_MIX AS QtdeMix,    DB_PEDI_LOTE AS Lote,    DB_PEDI_VLR_DESCTO_FISCAL AS VlrDesctoFiscal,    DB_PEDI_TAMANHO AS Tamanho,    DB_PEDI_BLOQVERBA AS BloqVerba,    DB_PEDI_VLRFRETIT_RATEIO AS VlrFreTitRateio,    DB_PEDI_VLRFRETIT_RATEIO_CALC AS VlrFreTitRateioCalc,    DB_PEDI_VISAO_COTA AS VisaoCota,    DB_PEDI_PERIODO_COTA AS PeriodoCota,    DB_PEDI_VISAO_COTA_ORIGINAL AS VisaoCotaOriginal,    DB_PEDI_PERIODO_COTA_ORIGINAL AS PeriodoCotaOriginal,    DB_PEDI_REGRAEMPITEM AS RegraEmpItem,    DB_PEDI_LJ_PECA AS LjPeca,    DB_PEDI_LJ_TAMANHO AS LjTamanho,    DB_PEDI_LJ_CARGA AS LjCarga,    DB_PEDI_LJ_MEDIDA_A AS LjMedidaA,    DB_PEDI_LJ_VAO AS LjVao,    DB_PEDI_LJ_ANGULO AS LjAngulo,    DB_PEDI_LJ_MEDIDA_B AS LjMedidaB,    DB_PEDI_LJ_INICIOVIGA AS LjInicioViga,    DB_PEDI_LJ_ABA AS LjAba,    DB_PEDI_LJ_ABA_A AS LjAbaA,    DB_PEDI_LJ_ABA_B AS LjAbaB,    DB_PEDI_BLOQLUC AS Bloqluc,    DB_PEDI_ORIGEM_COTA AS OrigemCota,    DB_PEDI_ORIGEM_CANC AS OrigemCanc,    DB_PEDI_INCSUG AS Incsug,    DB_PEDI_LJ_IDENT AS LjIdent,    DB_PEDI_COND_FRETE_IT AS CondFreteIt,    DB_PEDI_DESCTOTAL_CALC AS DesctotalCalc,    DB_PEDI_ID_PED_AUTO AS IdPedAuto,    DB_PEDI_LOTE_ESTOQUE AS LoteEstoque,    DB_PEDI_DESCTOTAL_APLICADO AS DesctotalAplicado,    DB_PEDI_SEQ_ORDEM_COMPRA AS SeqOrdemCompra,    DB_PEDI_SEQORD_COMPRA AS SeqordCompra,    DB_PEDI_ORDCOMEST AS Ordcomest,    DB_PEDI_VICMSFRETE AS ValorICMSFrete,    DB_PEDI_CAMPO1 AS Campo1,    DB_PEDI_CENTROCUSTO AS CentroCusto,    DB_PEDI_BLOQDESC AS BloqDesc,    DB_PEDI_QTDE_DESCBONIF AS QtdeDescBonif,    DB_PEDI_PERC_DESCBONIF AS PercDescBonif,    DB_PEDI_VALORDESCC_UNIT AS ValorDesccUnit,    DB_PEDI_SEQ_COTA AS SeqCota,    DB_PEDI_SEQ_COTA_ORIGINAL AS SeqCotaOriginal,    DB_PEDI_DT_REGRA_CORTE AS DtRegraCorte,    DB_PEDI_COMPOEMARGEM AS CompoeMargem  FROM DB_PEDIDO_PROD  WHERE DB_PEDI_PREV_ENTR >= GETDATE()-90;\"");
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
					talendJobLog.addCM("tDBInput_1", "Itens_do_pedido", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:LRG2d4u1jQT0hwz3lEO3k79ZyciBRDHQV1v6O1rwEfjbUq+Pyjs3e9lC");

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
						+ "E DB_PEDI_PREV_ENTR >= GETDATE()-90;";

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
							Itens_pedido.NumeroPedido = null;
						} else {

							Itens_pedido.NumeroPedido = rs_tDBInput_1.getInt(1);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.NumeroPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							Itens_pedido.SequenciaItem = null;
						} else {

							Itens_pedido.SequenciaItem = rs_tDBInput_1.getInt(2);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SequenciaItem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							Itens_pedido.CodigoProduto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodigoProduto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodigoProduto = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodigoProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							Itens_pedido.QuantidadeProduto = null;
						} else {

							Itens_pedido.QuantidadeProduto = rs_tDBInput_1.getFloat(4);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QuantidadeProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							Itens_pedido.QuantidadeAtendida = null;
						} else {

							Itens_pedido.QuantidadeAtendida = rs_tDBInput_1.getFloat(5);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QuantidadeAtendida = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							Itens_pedido.QuantidadeCancelada = null;
						} else {

							Itens_pedido.QuantidadeCancelada = rs_tDBInput_1.getFloat(6);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QuantidadeCancelada = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							Itens_pedido.PrecoUnitario = null;
						} else {

							Itens_pedido.PrecoUnitario = rs_tDBInput_1.getFloat(7);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrecoUnitario = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							Itens_pedido.PrecoLiquido = null;
						} else {

							Itens_pedido.PrecoLiquido = rs_tDBInput_1.getFloat(8);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrecoLiquido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							Itens_pedido.Desctop = null;
						} else {

							Itens_pedido.Desctop = rs_tDBInput_1.getFloat(9);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Desctop = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							Itens_pedido.DesctoIcm = null;
						} else {

							Itens_pedido.DesctoIcm = rs_tDBInput_1.getDouble(10);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.DesctoIcm = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							Itens_pedido.PrevEntr = null;
						} else {

							Itens_pedido.PrevEntr = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 11);

						}
						if (colQtyInRs_tDBInput_1 < 12) {
							Itens_pedido.AtdData = null;
						} else {

							Itens_pedido.AtdData = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 12);

						}
						if (colQtyInRs_tDBInput_1 < 13) {
							Itens_pedido.AtdQtde = null;
						} else {

							Itens_pedido.AtdQtde = rs_tDBInput_1.getFloat(13);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.AtdQtde = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							Itens_pedido.AtdDcto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.AtdDcto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.AtdDcto = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.AtdDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							Itens_pedido.AtdData1 = null;
						} else {

							Itens_pedido.AtdData1 = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 15);

						}
						if (colQtyInRs_tDBInput_1 < 16) {
							Itens_pedido.AtdQtde1 = null;
						} else {

							Itens_pedido.AtdQtde1 = rs_tDBInput_1.getFloat(16);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.AtdQtde1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							Itens_pedido.AtdDcto1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.AtdDcto1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.AtdDcto1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.AtdDcto1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							Itens_pedido.AtdData2 = null;
						} else {

							Itens_pedido.AtdData2 = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 18);

						}
						if (colQtyInRs_tDBInput_1 < 19) {
							Itens_pedido.AtdQtde2 = null;
						} else {

							Itens_pedido.AtdQtde2 = rs_tDBInput_1.getFloat(19);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.AtdQtde2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							Itens_pedido.AtdDcto2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.AtdDcto2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.AtdDcto2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.AtdDcto2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							Itens_pedido.SituacaoPedido = null;
						} else {

							Itens_pedido.SituacaoPedido = rs_tDBInput_1.getShort(21);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SituacaoPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							Itens_pedido.Ipi = null;
						} else {

							Itens_pedido.Ipi = rs_tDBInput_1.getDouble(22);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Ipi = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							Itens_pedido.Dctoprom = null;
						} else {

							Itens_pedido.Dctoprom = rs_tDBInput_1.getFloat(23);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Dctoprom = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							Itens_pedido.EconVlr = null;
						} else {

							Itens_pedido.EconVlr = rs_tDBInput_1.getFloat(24);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.EconVlr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							Itens_pedido.EconCalc = null;
						} else {

							Itens_pedido.EconCalc = rs_tDBInput_1.getShort(25);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.EconCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							Itens_pedido.EconDcto = null;
						} else {

							Itens_pedido.EconDcto = rs_tDBInput_1.getFloat(26);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.EconDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							Itens_pedido.PrecoMin = null;
						} else {

							Itens_pedido.PrecoMin = rs_tDBInput_1.getFloat(27);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrecoMin = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							Itens_pedido.Tipo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Tipo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Tipo = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Tipo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							Itens_pedido.Dctovda = null;
						} else {

							Itens_pedido.Dctovda = rs_tDBInput_1.getDouble(29);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Dctovda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							Itens_pedido.TipoPreco = null;
						} else {

							Itens_pedido.TipoPreco = rs_tDBInput_1.getShort(30);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.TipoPreco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							Itens_pedido.EstVlr = null;
						} else {

							Itens_pedido.EstVlr = rs_tDBInput_1.getShort(31);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.EstVlr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							Itens_pedido.Acrescimo = null;
						} else {

							Itens_pedido.Acrescimo = rs_tDBInput_1.getDouble(32);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Acrescimo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							Itens_pedido.Lpreco = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(33);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Lpreco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Lpreco = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Lpreco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							Itens_pedido.Grade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Grade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Grade = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Grade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							Itens_pedido.TpGrade = null;
						} else {

							Itens_pedido.TpGrade = rs_tDBInput_1.getShort(35);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.TpGrade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							Itens_pedido.CodEtiq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(36);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodEtiq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodEtiq = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodEtiq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							Itens_pedido.Embalagem = null;
						} else {

							Itens_pedido.Embalagem = rs_tDBInput_1.getDouble(37);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Embalagem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							Itens_pedido.MotCanc = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.MotCanc = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.MotCanc = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.MotCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 39) {
							Itens_pedido.PercComis = null;
						} else {

							Itens_pedido.PercComis = rs_tDBInput_1.getFloat(39);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PercComis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 40) {
							Itens_pedido.PrdCfgCar = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(40);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(40).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.PrdCfgCar = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.PrdCfgCar = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.PrdCfgCar = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							Itens_pedido.Observacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(41);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(41).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Observacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Observacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Observacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 42) {
							Itens_pedido.BonifEcon = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(42);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(42).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.BonifEcon = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.BonifEcon = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.BonifEcon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 43) {
							Itens_pedido.PcomisExt = null;
						} else {

							Itens_pedido.PcomisExt = rs_tDBInput_1.getDouble(43);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PcomisExt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 44) {
							Itens_pedido.AplicDcto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(44);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(44).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.AplicDcto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.AplicDcto = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.AplicDcto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 45) {
							Itens_pedido.Operacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(45);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(45).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Operacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Operacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Operacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 46) {
							Itens_pedido.QtdeRes = null;
						} else {

							Itens_pedido.QtdeRes = rs_tDBInput_1.getDouble(46);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdeRes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 47) {
							Itens_pedido.Bloqsug = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(47);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(47).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Bloqsug = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Bloqsug = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Bloqsug = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 48) {
							Itens_pedido.Dctoinc = null;
						} else {

							Itens_pedido.Dctoinc = rs_tDBInput_1.getFloat(48);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Dctoinc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 49) {
							Itens_pedido.VlrIpi = null;
						} else {

							Itens_pedido.VlrIpi = rs_tDBInput_1.getDouble(49);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrIpi = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 50) {
							Itens_pedido.SeqGrd = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(50);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(50).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.SeqGrd = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.SeqGrd = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.SeqGrd = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 51) {
							Itens_pedido.SeqErp = null;
						} else {

							Itens_pedido.SeqErp = rs_tDBInput_1.getInt(51);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SeqErp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 52) {
							Itens_pedido.PrevCalc = null;
						} else {

							Itens_pedido.PrevCalc = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 52);

						}
						if (colQtyInRs_tDBInput_1 < 53) {
							Itens_pedido.Almoxarif = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(53);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Almoxarif = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Almoxarif = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Almoxarif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 54) {
							Itens_pedido.ComisGer = null;
						} else {

							Itens_pedido.ComisGer = rs_tDBInput_1.getDouble(54);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ComisGer = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 55) {
							Itens_pedido.CredProp = null;
						} else {

							Itens_pedido.CredProp = rs_tDBInput_1.getDouble(55);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CredProp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 56) {
							Itens_pedido.NroEnvio = null;
						} else {

							Itens_pedido.NroEnvio = rs_tDBInput_1.getDouble(56);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.NroEnvio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 57) {
							Itens_pedido.Chave = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(57);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(57).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Chave = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Chave = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Chave = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 58) {
							Itens_pedido.ItemConf = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(58);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(58).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.ItemConf = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.ItemConf = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.ItemConf = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 59) {
							Itens_pedido.CvdaDcti = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(59);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(59).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CvdaDcti = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CvdaDcti = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CvdaDcti = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 60) {
							Itens_pedido.ObsNota = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(60);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(60).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.ObsNota = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.ObsNota = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.ObsNota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 61) {
							Itens_pedido.ObsExped = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(61);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(61).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.ObsExped = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.ObsExped = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.ObsExped = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 62) {
							Itens_pedido.ObsSepar = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(62);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(62).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.ObsSepar = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.ObsSepar = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.ObsSepar = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 63) {
							Itens_pedido.VlrSubst = null;
						} else {

							Itens_pedido.VlrSubst = rs_tDBInput_1.getDouble(63);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrSubst = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 64) {
							Itens_pedido.DesctoGer = null;
						} else {

							Itens_pedido.DesctoGer = rs_tDBInput_1.getDouble(64);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.DesctoGer = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 65) {
							Itens_pedido.VlrFreTit = null;
						} else {

							Itens_pedido.VlrFreTit = rs_tDBInput_1.getDouble(65);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrFreTit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 66) {
							Itens_pedido.MotTroca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(66);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(66).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.MotTroca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.MotTroca = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.MotTroca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 67) {
							Itens_pedido.VeconCig = null;
						} else {

							Itens_pedido.VeconCig = rs_tDBInput_1.getDouble(67);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VeconCig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 68) {
							Itens_pedido.CustoMp = null;
						} else {

							Itens_pedido.CustoMp = rs_tDBInput_1.getDouble(68);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CustoMp = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 69) {
							Itens_pedido.PliqAlt = null;
						} else {

							Itens_pedido.PliqAlt = rs_tDBInput_1.getShort(69);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PliqAlt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 70) {
							Itens_pedido.CodPrMap = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(70);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(70).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodPrMap = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodPrMap = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodPrMap = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 71) {
							Itens_pedido.CodCvdAg = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(71);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(71).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodCvdAg = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodCvdAg = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodCvdAg = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 72) {
							Itens_pedido.VuIcms = null;
						} else {

							Itens_pedido.VuIcms = rs_tDBInput_1.getDouble(72);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VuIcms = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 73) {
							Itens_pedido.VuPis = null;
						} else {

							Itens_pedido.VuPis = rs_tDBInput_1.getDouble(73);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VuPis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 74) {
							Itens_pedido.VuCofins = null;
						} else {

							Itens_pedido.VuCofins = rs_tDBInput_1.getDouble(74);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VuCofins = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 75) {
							Itens_pedido.Altdtprev = null;
						} else {

							Itens_pedido.Altdtprev = rs_tDBInput_1.getShort(75);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Altdtprev = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 76) {
							Itens_pedido.QtdeAloc = null;
						} else {

							Itens_pedido.QtdeAloc = rs_tDBInput_1.getDouble(76);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdeAloc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 77) {
							Itens_pedido.RestrBonif = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(77);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(77).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.RestrBonif = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.RestrBonif = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.RestrBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 78) {
							Itens_pedido.CustoMo = null;
						} else {

							Itens_pedido.CustoMo = rs_tDBInput_1.getDouble(78);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CustoMo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 79) {
							Itens_pedido.ItControl = null;
						} else {

							Itens_pedido.ItControl = rs_tDBInput_1.getShort(79);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ItControl = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 80) {
							Itens_pedido.ItPermBon = null;
						} else {

							Itens_pedido.ItPermBon = rs_tDBInput_1.getShort(80);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ItPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 81) {
							Itens_pedido.QtdPermBon = null;
						} else {

							Itens_pedido.QtdPermBon = rs_tDBInput_1.getDouble(81);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 82) {
							Itens_pedido.VlrPermBon = null;
						} else {

							Itens_pedido.VlrPermBon = rs_tDBInput_1.getDouble(82);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrPermBon = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 83) {
							Itens_pedido.Itemfemb = null;
						} else {

							Itens_pedido.Itemfemb = rs_tDBInput_1.getShort(83);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Itemfemb = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 84) {
							Itens_pedido.Dtprevento = null;
						} else {

							Itens_pedido.Dtprevento = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 84);

						}
						if (colQtyInRs_tDBInput_1 < 85) {
							Itens_pedido.Cfretit = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(85);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(85).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Cfretit = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Cfretit = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Cfretit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 86) {
							Itens_pedido.SldVerba = null;
						} else {

							Itens_pedido.SldVerba = rs_tDBInput_1.getDouble(86);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SldVerba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 87) {
							Itens_pedido.VlrBonif = null;
						} else {

							Itens_pedido.VlrBonif = rs_tDBInput_1.getDouble(87);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 88) {
							Itens_pedido.Bonif = null;
						} else {

							Itens_pedido.Bonif = rs_tDBInput_1.getShort(88);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Bonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 89) {
							Itens_pedido.Tpproduto = null;
						} else {

							Itens_pedido.Tpproduto = rs_tDBInput_1.getShort(89);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Tpproduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 90) {
							Itens_pedido.Backord = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(90);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(90).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Backord = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Backord = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Backord = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 91) {
							Itens_pedido.Markup = null;
						} else {

							Itens_pedido.Markup = rs_tDBInput_1.getDouble(91);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Markup = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 92) {
							Itens_pedido.QtdeBkor = null;
						} else {

							Itens_pedido.QtdeBkor = rs_tDBInput_1.getDouble(92);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdeBkor = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 93) {
							Itens_pedido.Prcpromoc = null;
						} else {

							Itens_pedido.Prcpromoc = rs_tDBInput_1.getDouble(93);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Prcpromoc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 94) {
							Itens_pedido.Sitcorp1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(94);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(94).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Sitcorp1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Sitcorp1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Sitcorp1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 95) {
							Itens_pedido.Sitcorp2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(95);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(95).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Sitcorp2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Sitcorp2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Sitcorp2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 96) {
							Itens_pedido.LibMots = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(96);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(96).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.LibMots = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.LibMots = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.LibMots = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 97) {
							Itens_pedido.Dctoboleto = null;
						} else {

							Itens_pedido.Dctoboleto = rs_tDBInput_1.getDouble(97);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Dctoboleto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 98) {
							Itens_pedido.MPRISeq = null;
						} else {

							Itens_pedido.MPRISeq = rs_tDBInput_1.getInt(98);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.MPRISeq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 99) {
							Itens_pedido.Dctomax = null;
						} else {

							Itens_pedido.Dctomax = rs_tDBInput_1.getDouble(99);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Dctomax = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 100) {
							Itens_pedido.Comprim = null;
						} else {

							Itens_pedido.Comprim = rs_tDBInput_1.getDouble(100);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Comprim = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 101) {
							Itens_pedido.QtdePeca = null;
						} else {

							Itens_pedido.QtdePeca = rs_tDBInput_1.getDouble(101);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdePeca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 102) {
							Itens_pedido.ProdRes = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(102);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(102).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.ProdRes = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.ProdRes = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.ProdRes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 103) {
							Itens_pedido.EmpEstoq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(103);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(103).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.EmpEstoq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.EmpEstoq = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.EmpEstoq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 104) {
							Itens_pedido.PrevProduc = null;
						} else {

							Itens_pedido.PrevProduc = rs_tDBInput_1.getDouble(104);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrevProduc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 105) {
							Itens_pedido.PrunitOri = null;
						} else {

							Itens_pedido.PrunitOri = rs_tDBInput_1.getFloat(105);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrunitOri = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 106) {
							Itens_pedido.PrliqOri = null;
						} else {

							Itens_pedido.PrliqOri = rs_tDBInput_1.getFloat(106);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrliqOri = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 107) {
							Itens_pedido.Mva = null;
						} else {

							Itens_pedido.Mva = rs_tDBInput_1.getDouble(107);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Mva = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 108) {
							Itens_pedido.ICMSOrig = null;
						} else {

							Itens_pedido.ICMSOrig = rs_tDBInput_1.getDouble(108);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ICMSOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 109) {
							Itens_pedido.ICMSDest = null;
						} else {

							Itens_pedido.ICMSDest = rs_tDBInput_1.getDouble(109);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ICMSDest = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 110) {
							Itens_pedido.Float1 = null;
						} else {

							Itens_pedido.Float1 = rs_tDBInput_1.getDouble(110);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Float1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 111) {
							Itens_pedido.Float2 = null;
						} else {

							Itens_pedido.Float2 = rs_tDBInput_1.getDouble(111);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Float2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 112) {
							Itens_pedido.DataAlter = null;
						} else {

							Itens_pedido.DataAlter = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 112);

						}
						if (colQtyInRs_tDBInput_1 < 113) {
							Itens_pedido.SeqOrig = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(113);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(113).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.SeqOrig = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.SeqOrig = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.SeqOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 114) {
							Itens_pedido.PrecoNorm = null;
						} else {

							Itens_pedido.PrecoNorm = rs_tDBInput_1.getDouble(114);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrecoNorm = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 115) {
							Itens_pedido.Tipost = null;
						} else {

							Itens_pedido.Tipost = rs_tDBInput_1.getShort(115);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Tipost = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 116) {
							Itens_pedido.CodProdcli = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(116);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(116).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodProdcli = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodProdcli = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodProdcli = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 117) {
							Itens_pedido.VlrDescarga = null;
						} else {

							Itens_pedido.VlrDescarga = rs_tDBInput_1.getDouble(117);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrDescarga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 118) {
							Itens_pedido.IcmsDscto = null;
						} else {

							Itens_pedido.IcmsDscto = rs_tDBInput_1.getShort(118);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.IcmsDscto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 119) {
							Itens_pedido.EmpSplit = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(119);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(119).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.EmpSplit = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.EmpSplit = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.EmpSplit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 120) {
							Itens_pedido.Status = null;
						} else {

							Itens_pedido.Status = rs_tDBInput_1.getInt(120);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Status = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 121) {
							Itens_pedido.Obrigatorio = null;
						} else {

							Itens_pedido.Obrigatorio = rs_tDBInput_1.getInt(121);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Obrigatorio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 122) {
							Itens_pedido.PrecoFirme = null;
						} else {

							Itens_pedido.PrecoFirme = rs_tDBInput_1.getInt(122);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PrecoFirme = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 123) {
							Itens_pedido.OrdCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(123);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(123).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.OrdCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.OrdCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.OrdCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 124) {
							Itens_pedido.DesctoFiscal = null;
						} else {

							Itens_pedido.DesctoFiscal = rs_tDBInput_1.getDouble(124);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.DesctoFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 125) {
							Itens_pedido.PercMlucr = null;
						} else {

							Itens_pedido.PercMlucr = rs_tDBInput_1.getDouble(125);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PercMlucr = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 126) {
							Itens_pedido.VlrDescargaCalc = null;
						} else {

							Itens_pedido.VlrDescargaCalc = rs_tDBInput_1.getDouble(126);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrDescargaCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 127) {
							Itens_pedido.VlrFreTitCalc = null;
						} else {

							Itens_pedido.VlrFreTitCalc = rs_tDBInput_1.getDouble(127);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrFreTitCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 128) {
							Itens_pedido.VuComissao = null;
						} else {

							Itens_pedido.VuComissao = rs_tDBInput_1.getDouble(128);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VuComissao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 129) {
							Itens_pedido.VuDebcli = null;
						} else {

							Itens_pedido.VuDebcli = rs_tDBInput_1.getDouble(129);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VuDebcli = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 130) {
							Itens_pedido.Data1Original = null;
						} else {

							Itens_pedido.Data1Original = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1,
									130);

						}
						if (colQtyInRs_tDBInput_1 < 131) {
							Itens_pedido.Visao = null;
						} else {

							Itens_pedido.Visao = rs_tDBInput_1.getInt(131);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Visao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 132) {
							Itens_pedido.Periodo = null;
						} else {

							Itens_pedido.Periodo = rs_tDBInput_1.getInt(132);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Periodo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 133) {
							Itens_pedido.BaseIcms = null;
						} else {

							Itens_pedido.BaseIcms = rs_tDBInput_1.getDouble(133);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.BaseIcms = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 134) {
							Itens_pedido.BaseSt = null;
						} else {

							Itens_pedido.BaseSt = rs_tDBInput_1.getDouble(134);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.BaseSt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 135) {
							Itens_pedido.CondPgto = null;
						} else {

							Itens_pedido.CondPgto = rs_tDBInput_1.getInt(135);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CondPgto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 136) {
							Itens_pedido.UsuCanc = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(136);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(136).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.UsuCanc = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.UsuCanc = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.UsuCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 137) {
							Itens_pedido.Resrede = null;
						} else {

							Itens_pedido.Resrede = rs_tDBInput_1.getDouble(137);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Resrede = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 138) {
							Itens_pedido.DocBloq = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(138);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(138).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.DocBloq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.DocBloq = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.DocBloq = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 139) {
							Itens_pedido.Regracomis = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(139);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(139).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Regracomis = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Regracomis = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Regracomis = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 140) {
							Itens_pedido.BaseIcmsRed = null;
						} else {

							Itens_pedido.BaseIcmsRed = rs_tDBInput_1.getDouble(140);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.BaseIcmsRed = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 141) {
							Itens_pedido.IcmsSemRed = null;
						} else {

							Itens_pedido.IcmsSemRed = rs_tDBInput_1.getDouble(141);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.IcmsSemRed = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 142) {
							Itens_pedido.Obs1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(142);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(142).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Obs1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Obs1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Obs1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 143) {
							Itens_pedido.Obs2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(143);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(143).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Obs2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Obs2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Obs2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 144) {
							Itens_pedido.CodEmbal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(144);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(144).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CodEmbal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CodEmbal = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CodEmbal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 145) {
							Itens_pedido.CondPgtoCalc = null;
						} else {

							Itens_pedido.CondPgtoCalc = rs_tDBInput_1.getInt(145);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CondPgtoCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 146) {
							Itens_pedido.CodigoMix = null;
						} else {

							Itens_pedido.CodigoMix = rs_tDBInput_1.getInt(146);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CodigoMix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 147) {
							Itens_pedido.QtdeMix = null;
						} else {

							Itens_pedido.QtdeMix = rs_tDBInput_1.getInt(147);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdeMix = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 148) {
							Itens_pedido.Lote = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(148);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(148).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Lote = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Lote = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Lote = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 149) {
							Itens_pedido.VlrDesctoFiscal = null;
						} else {

							Itens_pedido.VlrDesctoFiscal = rs_tDBInput_1.getDouble(149);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrDesctoFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 150) {
							Itens_pedido.Tamanho = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(150);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(150).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Tamanho = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Tamanho = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Tamanho = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 151) {
							Itens_pedido.BloqVerba = null;
						} else {

							Itens_pedido.BloqVerba = rs_tDBInput_1.getInt(151);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.BloqVerba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 152) {
							Itens_pedido.VlrFreTitRateio = null;
						} else {

							Itens_pedido.VlrFreTitRateio = rs_tDBInput_1.getDouble(152);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrFreTitRateio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 153) {
							Itens_pedido.VlrFreTitRateioCalc = null;
						} else {

							Itens_pedido.VlrFreTitRateioCalc = rs_tDBInput_1.getDouble(153);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VlrFreTitRateioCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 154) {
							Itens_pedido.VisaoCota = null;
						} else {

							Itens_pedido.VisaoCota = rs_tDBInput_1.getInt(154);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VisaoCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 155) {
							Itens_pedido.PeriodoCota = null;
						} else {

							Itens_pedido.PeriodoCota = rs_tDBInput_1.getInt(155);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PeriodoCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 156) {
							Itens_pedido.VisaoCotaOriginal = null;
						} else {

							Itens_pedido.VisaoCotaOriginal = rs_tDBInput_1.getInt(156);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.VisaoCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 157) {
							Itens_pedido.PeriodoCotaOriginal = null;
						} else {

							Itens_pedido.PeriodoCotaOriginal = rs_tDBInput_1.getInt(157);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PeriodoCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 158) {
							Itens_pedido.RegraEmpItem = null;
						} else {

							Itens_pedido.RegraEmpItem = rs_tDBInput_1.getInt(158);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.RegraEmpItem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 159) {
							Itens_pedido.LjPeca = null;
						} else {

							Itens_pedido.LjPeca = rs_tDBInput_1.getInt(159);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjPeca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 160) {
							Itens_pedido.LjTamanho = null;
						} else {

							Itens_pedido.LjTamanho = rs_tDBInput_1.getDouble(160);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjTamanho = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 161) {
							Itens_pedido.LjCarga = null;
						} else {

							Itens_pedido.LjCarga = rs_tDBInput_1.getInt(161);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjCarga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 162) {
							Itens_pedido.LjMedidaA = null;
						} else {

							Itens_pedido.LjMedidaA = rs_tDBInput_1.getDouble(162);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjMedidaA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 163) {
							Itens_pedido.LjVao = null;
						} else {

							Itens_pedido.LjVao = rs_tDBInput_1.getDouble(163);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjVao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 164) {
							Itens_pedido.LjAngulo = null;
						} else {

							Itens_pedido.LjAngulo = rs_tDBInput_1.getInt(164);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjAngulo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 165) {
							Itens_pedido.LjMedidaB = null;
						} else {

							Itens_pedido.LjMedidaB = rs_tDBInput_1.getDouble(165);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjMedidaB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 166) {
							Itens_pedido.LjInicioViga = null;
						} else {

							Itens_pedido.LjInicioViga = rs_tDBInput_1.getInt(166);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjInicioViga = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 167) {
							Itens_pedido.LjAba = null;
						} else {

							Itens_pedido.LjAba = rs_tDBInput_1.getInt(167);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjAba = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 168) {
							Itens_pedido.LjAbaA = null;
						} else {

							Itens_pedido.LjAbaA = rs_tDBInput_1.getDouble(168);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjAbaA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 169) {
							Itens_pedido.LjAbaB = null;
						} else {

							Itens_pedido.LjAbaB = rs_tDBInput_1.getDouble(169);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjAbaB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 170) {
							Itens_pedido.Bloqluc = null;
						} else {

							Itens_pedido.Bloqluc = rs_tDBInput_1.getInt(170);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Bloqluc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 171) {
							Itens_pedido.OrigemCota = null;
						} else {

							Itens_pedido.OrigemCota = rs_tDBInput_1.getInt(171);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.OrigemCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 172) {
							Itens_pedido.OrigemCanc = null;
						} else {

							Itens_pedido.OrigemCanc = rs_tDBInput_1.getInt(172);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.OrigemCanc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 173) {
							Itens_pedido.Incsug = null;
						} else {

							Itens_pedido.Incsug = rs_tDBInput_1.getInt(173);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Incsug = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 174) {
							Itens_pedido.LjIdent = null;
						} else {

							Itens_pedido.LjIdent = rs_tDBInput_1.getInt(174);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.LjIdent = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 175) {
							Itens_pedido.CondFreteIt = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(175);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(175).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CondFreteIt = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CondFreteIt = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CondFreteIt = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 176) {
							Itens_pedido.DesctotalCalc = null;
						} else {

							Itens_pedido.DesctotalCalc = rs_tDBInput_1.getDouble(176);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.DesctotalCalc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 177) {
							Itens_pedido.IdPedAuto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(177);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(177).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.IdPedAuto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.IdPedAuto = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.IdPedAuto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 178) {
							Itens_pedido.LoteEstoque = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(178);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(178).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.LoteEstoque = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.LoteEstoque = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.LoteEstoque = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 179) {
							Itens_pedido.DesctotalAplicado = null;
						} else {

							Itens_pedido.DesctotalAplicado = rs_tDBInput_1.getDouble(179);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.DesctotalAplicado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 180) {
							Itens_pedido.SeqOrdemCompra = null;
						} else {

							Itens_pedido.SeqOrdemCompra = rs_tDBInput_1.getInt(180);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SeqOrdemCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 181) {
							Itens_pedido.SeqordCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(181);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(181).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.SeqordCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.SeqordCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.SeqordCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 182) {
							Itens_pedido.Ordcomest = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(182);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(182).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.Ordcomest = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.Ordcomest = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.Ordcomest = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 183) {
							Itens_pedido.ValorICMSFrete = null;
						} else {

							Itens_pedido.ValorICMSFrete = rs_tDBInput_1.getDouble(183);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ValorICMSFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 184) {
							Itens_pedido.Campo1 = null;
						} else {

							Itens_pedido.Campo1 = rs_tDBInput_1.getInt(184);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.Campo1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 185) {
							Itens_pedido.CentroCusto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(185);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(185).toUpperCase(java.util.Locale.ENGLISH))) {
									Itens_pedido.CentroCusto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									Itens_pedido.CentroCusto = tmpContent_tDBInput_1.trim();
								}
							} else {
								Itens_pedido.CentroCusto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 186) {
							Itens_pedido.BloqDesc = null;
						} else {

							Itens_pedido.BloqDesc = rs_tDBInput_1.getInt(186);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.BloqDesc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 187) {
							Itens_pedido.QtdeDescBonif = null;
						} else {

							Itens_pedido.QtdeDescBonif = rs_tDBInput_1.getDouble(187);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.QtdeDescBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 188) {
							Itens_pedido.PercDescBonif = null;
						} else {

							Itens_pedido.PercDescBonif = rs_tDBInput_1.getDouble(188);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.PercDescBonif = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 189) {
							Itens_pedido.ValorDesccUnit = null;
						} else {

							Itens_pedido.ValorDesccUnit = rs_tDBInput_1.getDouble(189);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.ValorDesccUnit = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 190) {
							Itens_pedido.SeqCota = null;
						} else {

							Itens_pedido.SeqCota = rs_tDBInput_1.getInt(190);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SeqCota = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 191) {
							Itens_pedido.SeqCotaOriginal = null;
						} else {

							Itens_pedido.SeqCotaOriginal = rs_tDBInput_1.getInt(191);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.SeqCotaOriginal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 192) {
							Itens_pedido.DtRegraCorte = null;
						} else {

							Itens_pedido.DtRegraCorte = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1,
									192);

						}
						if (colQtyInRs_tDBInput_1 < 193) {
							Itens_pedido.CompoeMargem = null;
						} else {

							Itens_pedido.CompoeMargem = rs_tDBInput_1.getInt(193);
							if (rs_tDBInput_1.wasNull()) {
								Itens_pedido.CompoeMargem = null;
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

						cLabel = "Itens_do_pedido";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Itens_do_pedido";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_Itens_pedido main ] start
						 */

						currentComponent = "tAdvancedHash_Itens_pedido";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "Itens_pedido", "tDBInput_1", "Itens_do_pedido", "tMSSqlInput",
								"tAdvancedHash_Itens_pedido", "tAdvancedHash_Itens_pedido", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("Itens_pedido - " + (Itens_pedido == null ? "" : Itens_pedido.toLogString()));
						}

						Itens_pedidoStruct Itens_pedido_HashRow = new Itens_pedidoStruct();

						Itens_pedido_HashRow.NumeroPedido = Itens_pedido.NumeroPedido;

						Itens_pedido_HashRow.SequenciaItem = Itens_pedido.SequenciaItem;

						Itens_pedido_HashRow.CodigoProduto = Itens_pedido.CodigoProduto;

						Itens_pedido_HashRow.QuantidadeProduto = Itens_pedido.QuantidadeProduto;

						Itens_pedido_HashRow.QuantidadeAtendida = Itens_pedido.QuantidadeAtendida;

						Itens_pedido_HashRow.QuantidadeCancelada = Itens_pedido.QuantidadeCancelada;

						Itens_pedido_HashRow.PrecoUnitario = Itens_pedido.PrecoUnitario;

						Itens_pedido_HashRow.PrecoLiquido = Itens_pedido.PrecoLiquido;

						Itens_pedido_HashRow.Desctop = Itens_pedido.Desctop;

						Itens_pedido_HashRow.DesctoIcm = Itens_pedido.DesctoIcm;

						Itens_pedido_HashRow.PrevEntr = Itens_pedido.PrevEntr;

						Itens_pedido_HashRow.AtdData = Itens_pedido.AtdData;

						Itens_pedido_HashRow.AtdQtde = Itens_pedido.AtdQtde;

						Itens_pedido_HashRow.AtdDcto = Itens_pedido.AtdDcto;

						Itens_pedido_HashRow.AtdData1 = Itens_pedido.AtdData1;

						Itens_pedido_HashRow.AtdQtde1 = Itens_pedido.AtdQtde1;

						Itens_pedido_HashRow.AtdDcto1 = Itens_pedido.AtdDcto1;

						Itens_pedido_HashRow.AtdData2 = Itens_pedido.AtdData2;

						Itens_pedido_HashRow.AtdQtde2 = Itens_pedido.AtdQtde2;

						Itens_pedido_HashRow.AtdDcto2 = Itens_pedido.AtdDcto2;

						Itens_pedido_HashRow.SituacaoPedido = Itens_pedido.SituacaoPedido;

						Itens_pedido_HashRow.Ipi = Itens_pedido.Ipi;

						Itens_pedido_HashRow.Dctoprom = Itens_pedido.Dctoprom;

						Itens_pedido_HashRow.EconVlr = Itens_pedido.EconVlr;

						Itens_pedido_HashRow.EconCalc = Itens_pedido.EconCalc;

						Itens_pedido_HashRow.EconDcto = Itens_pedido.EconDcto;

						Itens_pedido_HashRow.PrecoMin = Itens_pedido.PrecoMin;

						Itens_pedido_HashRow.Tipo = Itens_pedido.Tipo;

						Itens_pedido_HashRow.Dctovda = Itens_pedido.Dctovda;

						Itens_pedido_HashRow.TipoPreco = Itens_pedido.TipoPreco;

						Itens_pedido_HashRow.EstVlr = Itens_pedido.EstVlr;

						Itens_pedido_HashRow.Acrescimo = Itens_pedido.Acrescimo;

						Itens_pedido_HashRow.Lpreco = Itens_pedido.Lpreco;

						Itens_pedido_HashRow.Grade = Itens_pedido.Grade;

						Itens_pedido_HashRow.TpGrade = Itens_pedido.TpGrade;

						Itens_pedido_HashRow.CodEtiq = Itens_pedido.CodEtiq;

						Itens_pedido_HashRow.Embalagem = Itens_pedido.Embalagem;

						Itens_pedido_HashRow.MotCanc = Itens_pedido.MotCanc;

						Itens_pedido_HashRow.PercComis = Itens_pedido.PercComis;

						Itens_pedido_HashRow.PrdCfgCar = Itens_pedido.PrdCfgCar;

						Itens_pedido_HashRow.Observacao = Itens_pedido.Observacao;

						Itens_pedido_HashRow.BonifEcon = Itens_pedido.BonifEcon;

						Itens_pedido_HashRow.PcomisExt = Itens_pedido.PcomisExt;

						Itens_pedido_HashRow.AplicDcto = Itens_pedido.AplicDcto;

						Itens_pedido_HashRow.Operacao = Itens_pedido.Operacao;

						Itens_pedido_HashRow.QtdeRes = Itens_pedido.QtdeRes;

						Itens_pedido_HashRow.Bloqsug = Itens_pedido.Bloqsug;

						Itens_pedido_HashRow.Dctoinc = Itens_pedido.Dctoinc;

						Itens_pedido_HashRow.VlrIpi = Itens_pedido.VlrIpi;

						Itens_pedido_HashRow.SeqGrd = Itens_pedido.SeqGrd;

						Itens_pedido_HashRow.SeqErp = Itens_pedido.SeqErp;

						Itens_pedido_HashRow.PrevCalc = Itens_pedido.PrevCalc;

						Itens_pedido_HashRow.Almoxarif = Itens_pedido.Almoxarif;

						Itens_pedido_HashRow.ComisGer = Itens_pedido.ComisGer;

						Itens_pedido_HashRow.CredProp = Itens_pedido.CredProp;

						Itens_pedido_HashRow.NroEnvio = Itens_pedido.NroEnvio;

						Itens_pedido_HashRow.Chave = Itens_pedido.Chave;

						Itens_pedido_HashRow.ItemConf = Itens_pedido.ItemConf;

						Itens_pedido_HashRow.CvdaDcti = Itens_pedido.CvdaDcti;

						Itens_pedido_HashRow.ObsNota = Itens_pedido.ObsNota;

						Itens_pedido_HashRow.ObsExped = Itens_pedido.ObsExped;

						Itens_pedido_HashRow.ObsSepar = Itens_pedido.ObsSepar;

						Itens_pedido_HashRow.VlrSubst = Itens_pedido.VlrSubst;

						Itens_pedido_HashRow.DesctoGer = Itens_pedido.DesctoGer;

						Itens_pedido_HashRow.VlrFreTit = Itens_pedido.VlrFreTit;

						Itens_pedido_HashRow.MotTroca = Itens_pedido.MotTroca;

						Itens_pedido_HashRow.VeconCig = Itens_pedido.VeconCig;

						Itens_pedido_HashRow.CustoMp = Itens_pedido.CustoMp;

						Itens_pedido_HashRow.PliqAlt = Itens_pedido.PliqAlt;

						Itens_pedido_HashRow.CodPrMap = Itens_pedido.CodPrMap;

						Itens_pedido_HashRow.CodCvdAg = Itens_pedido.CodCvdAg;

						Itens_pedido_HashRow.VuIcms = Itens_pedido.VuIcms;

						Itens_pedido_HashRow.VuPis = Itens_pedido.VuPis;

						Itens_pedido_HashRow.VuCofins = Itens_pedido.VuCofins;

						Itens_pedido_HashRow.Altdtprev = Itens_pedido.Altdtprev;

						Itens_pedido_HashRow.QtdeAloc = Itens_pedido.QtdeAloc;

						Itens_pedido_HashRow.RestrBonif = Itens_pedido.RestrBonif;

						Itens_pedido_HashRow.CustoMo = Itens_pedido.CustoMo;

						Itens_pedido_HashRow.ItControl = Itens_pedido.ItControl;

						Itens_pedido_HashRow.ItPermBon = Itens_pedido.ItPermBon;

						Itens_pedido_HashRow.QtdPermBon = Itens_pedido.QtdPermBon;

						Itens_pedido_HashRow.VlrPermBon = Itens_pedido.VlrPermBon;

						Itens_pedido_HashRow.Itemfemb = Itens_pedido.Itemfemb;

						Itens_pedido_HashRow.Dtprevento = Itens_pedido.Dtprevento;

						Itens_pedido_HashRow.Cfretit = Itens_pedido.Cfretit;

						Itens_pedido_HashRow.SldVerba = Itens_pedido.SldVerba;

						Itens_pedido_HashRow.VlrBonif = Itens_pedido.VlrBonif;

						Itens_pedido_HashRow.Bonif = Itens_pedido.Bonif;

						Itens_pedido_HashRow.Tpproduto = Itens_pedido.Tpproduto;

						Itens_pedido_HashRow.Backord = Itens_pedido.Backord;

						Itens_pedido_HashRow.Markup = Itens_pedido.Markup;

						Itens_pedido_HashRow.QtdeBkor = Itens_pedido.QtdeBkor;

						Itens_pedido_HashRow.Prcpromoc = Itens_pedido.Prcpromoc;

						Itens_pedido_HashRow.Sitcorp1 = Itens_pedido.Sitcorp1;

						Itens_pedido_HashRow.Sitcorp2 = Itens_pedido.Sitcorp2;

						Itens_pedido_HashRow.LibMots = Itens_pedido.LibMots;

						Itens_pedido_HashRow.Dctoboleto = Itens_pedido.Dctoboleto;

						Itens_pedido_HashRow.MPRISeq = Itens_pedido.MPRISeq;

						Itens_pedido_HashRow.Dctomax = Itens_pedido.Dctomax;

						Itens_pedido_HashRow.Comprim = Itens_pedido.Comprim;

						Itens_pedido_HashRow.QtdePeca = Itens_pedido.QtdePeca;

						Itens_pedido_HashRow.ProdRes = Itens_pedido.ProdRes;

						Itens_pedido_HashRow.EmpEstoq = Itens_pedido.EmpEstoq;

						Itens_pedido_HashRow.PrevProduc = Itens_pedido.PrevProduc;

						Itens_pedido_HashRow.PrunitOri = Itens_pedido.PrunitOri;

						Itens_pedido_HashRow.PrliqOri = Itens_pedido.PrliqOri;

						Itens_pedido_HashRow.Mva = Itens_pedido.Mva;

						Itens_pedido_HashRow.ICMSOrig = Itens_pedido.ICMSOrig;

						Itens_pedido_HashRow.ICMSDest = Itens_pedido.ICMSDest;

						Itens_pedido_HashRow.Float1 = Itens_pedido.Float1;

						Itens_pedido_HashRow.Float2 = Itens_pedido.Float2;

						Itens_pedido_HashRow.DataAlter = Itens_pedido.DataAlter;

						Itens_pedido_HashRow.SeqOrig = Itens_pedido.SeqOrig;

						Itens_pedido_HashRow.PrecoNorm = Itens_pedido.PrecoNorm;

						Itens_pedido_HashRow.Tipost = Itens_pedido.Tipost;

						Itens_pedido_HashRow.CodProdcli = Itens_pedido.CodProdcli;

						Itens_pedido_HashRow.VlrDescarga = Itens_pedido.VlrDescarga;

						Itens_pedido_HashRow.IcmsDscto = Itens_pedido.IcmsDscto;

						Itens_pedido_HashRow.EmpSplit = Itens_pedido.EmpSplit;

						Itens_pedido_HashRow.Status = Itens_pedido.Status;

						Itens_pedido_HashRow.Obrigatorio = Itens_pedido.Obrigatorio;

						Itens_pedido_HashRow.PrecoFirme = Itens_pedido.PrecoFirme;

						Itens_pedido_HashRow.OrdCompra = Itens_pedido.OrdCompra;

						Itens_pedido_HashRow.DesctoFiscal = Itens_pedido.DesctoFiscal;

						Itens_pedido_HashRow.PercMlucr = Itens_pedido.PercMlucr;

						Itens_pedido_HashRow.VlrDescargaCalc = Itens_pedido.VlrDescargaCalc;

						Itens_pedido_HashRow.VlrFreTitCalc = Itens_pedido.VlrFreTitCalc;

						Itens_pedido_HashRow.VuComissao = Itens_pedido.VuComissao;

						Itens_pedido_HashRow.VuDebcli = Itens_pedido.VuDebcli;

						Itens_pedido_HashRow.Data1Original = Itens_pedido.Data1Original;

						Itens_pedido_HashRow.Visao = Itens_pedido.Visao;

						Itens_pedido_HashRow.Periodo = Itens_pedido.Periodo;

						Itens_pedido_HashRow.BaseIcms = Itens_pedido.BaseIcms;

						Itens_pedido_HashRow.BaseSt = Itens_pedido.BaseSt;

						Itens_pedido_HashRow.CondPgto = Itens_pedido.CondPgto;

						Itens_pedido_HashRow.UsuCanc = Itens_pedido.UsuCanc;

						Itens_pedido_HashRow.Resrede = Itens_pedido.Resrede;

						Itens_pedido_HashRow.DocBloq = Itens_pedido.DocBloq;

						Itens_pedido_HashRow.Regracomis = Itens_pedido.Regracomis;

						Itens_pedido_HashRow.BaseIcmsRed = Itens_pedido.BaseIcmsRed;

						Itens_pedido_HashRow.IcmsSemRed = Itens_pedido.IcmsSemRed;

						Itens_pedido_HashRow.Obs1 = Itens_pedido.Obs1;

						Itens_pedido_HashRow.Obs2 = Itens_pedido.Obs2;

						Itens_pedido_HashRow.CodEmbal = Itens_pedido.CodEmbal;

						Itens_pedido_HashRow.CondPgtoCalc = Itens_pedido.CondPgtoCalc;

						Itens_pedido_HashRow.CodigoMix = Itens_pedido.CodigoMix;

						Itens_pedido_HashRow.QtdeMix = Itens_pedido.QtdeMix;

						Itens_pedido_HashRow.Lote = Itens_pedido.Lote;

						Itens_pedido_HashRow.VlrDesctoFiscal = Itens_pedido.VlrDesctoFiscal;

						Itens_pedido_HashRow.Tamanho = Itens_pedido.Tamanho;

						Itens_pedido_HashRow.BloqVerba = Itens_pedido.BloqVerba;

						Itens_pedido_HashRow.VlrFreTitRateio = Itens_pedido.VlrFreTitRateio;

						Itens_pedido_HashRow.VlrFreTitRateioCalc = Itens_pedido.VlrFreTitRateioCalc;

						Itens_pedido_HashRow.VisaoCota = Itens_pedido.VisaoCota;

						Itens_pedido_HashRow.PeriodoCota = Itens_pedido.PeriodoCota;

						Itens_pedido_HashRow.VisaoCotaOriginal = Itens_pedido.VisaoCotaOriginal;

						Itens_pedido_HashRow.PeriodoCotaOriginal = Itens_pedido.PeriodoCotaOriginal;

						Itens_pedido_HashRow.RegraEmpItem = Itens_pedido.RegraEmpItem;

						Itens_pedido_HashRow.LjPeca = Itens_pedido.LjPeca;

						Itens_pedido_HashRow.LjTamanho = Itens_pedido.LjTamanho;

						Itens_pedido_HashRow.LjCarga = Itens_pedido.LjCarga;

						Itens_pedido_HashRow.LjMedidaA = Itens_pedido.LjMedidaA;

						Itens_pedido_HashRow.LjVao = Itens_pedido.LjVao;

						Itens_pedido_HashRow.LjAngulo = Itens_pedido.LjAngulo;

						Itens_pedido_HashRow.LjMedidaB = Itens_pedido.LjMedidaB;

						Itens_pedido_HashRow.LjInicioViga = Itens_pedido.LjInicioViga;

						Itens_pedido_HashRow.LjAba = Itens_pedido.LjAba;

						Itens_pedido_HashRow.LjAbaA = Itens_pedido.LjAbaA;

						Itens_pedido_HashRow.LjAbaB = Itens_pedido.LjAbaB;

						Itens_pedido_HashRow.Bloqluc = Itens_pedido.Bloqluc;

						Itens_pedido_HashRow.OrigemCota = Itens_pedido.OrigemCota;

						Itens_pedido_HashRow.OrigemCanc = Itens_pedido.OrigemCanc;

						Itens_pedido_HashRow.Incsug = Itens_pedido.Incsug;

						Itens_pedido_HashRow.LjIdent = Itens_pedido.LjIdent;

						Itens_pedido_HashRow.CondFreteIt = Itens_pedido.CondFreteIt;

						Itens_pedido_HashRow.DesctotalCalc = Itens_pedido.DesctotalCalc;

						Itens_pedido_HashRow.IdPedAuto = Itens_pedido.IdPedAuto;

						Itens_pedido_HashRow.LoteEstoque = Itens_pedido.LoteEstoque;

						Itens_pedido_HashRow.DesctotalAplicado = Itens_pedido.DesctotalAplicado;

						Itens_pedido_HashRow.SeqOrdemCompra = Itens_pedido.SeqOrdemCompra;

						Itens_pedido_HashRow.SeqordCompra = Itens_pedido.SeqordCompra;

						Itens_pedido_HashRow.Ordcomest = Itens_pedido.Ordcomest;

						Itens_pedido_HashRow.ValorICMSFrete = Itens_pedido.ValorICMSFrete;

						Itens_pedido_HashRow.Campo1 = Itens_pedido.Campo1;

						Itens_pedido_HashRow.CentroCusto = Itens_pedido.CentroCusto;

						Itens_pedido_HashRow.BloqDesc = Itens_pedido.BloqDesc;

						Itens_pedido_HashRow.QtdeDescBonif = Itens_pedido.QtdeDescBonif;

						Itens_pedido_HashRow.PercDescBonif = Itens_pedido.PercDescBonif;

						Itens_pedido_HashRow.ValorDesccUnit = Itens_pedido.ValorDesccUnit;

						Itens_pedido_HashRow.SeqCota = Itens_pedido.SeqCota;

						Itens_pedido_HashRow.SeqCotaOriginal = Itens_pedido.SeqCotaOriginal;

						Itens_pedido_HashRow.DtRegraCorte = Itens_pedido.DtRegraCorte;

						Itens_pedido_HashRow.CompoeMargem = Itens_pedido.CompoeMargem;

						tHash_Lookup_Itens_pedido.put(Itens_pedido_HashRow);

						tos_count_tAdvancedHash_Itens_pedido++;

						/**
						 * [tAdvancedHash_Itens_pedido main ] stop
						 */

						/**
						 * [tAdvancedHash_Itens_pedido process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_Itens_pedido";

						/**
						 * [tAdvancedHash_Itens_pedido process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_Itens_pedido process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_Itens_pedido";

						/**
						 * [tAdvancedHash_Itens_pedido process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Itens_do_pedido";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Itens_do_pedido";

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
				 * [tAdvancedHash_Itens_pedido end ] start
				 */

				currentComponent = "tAdvancedHash_Itens_pedido";

				tHash_Lookup_Itens_pedido.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Itens_pedido", 2, 0,
						"tDBInput_1", "Itens_do_pedido", "tMSSqlInput", "tAdvancedHash_Itens_pedido",
						"tAdvancedHash_Itens_pedido", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_Itens_pedido", true);
				end_Hash.put("tAdvancedHash_Itens_pedido", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_Itens_pedido end ] stop
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

				cLabel = "Itens_do_pedido";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_Itens_pedido finally ] start
				 */

				currentComponent = "tAdvancedHash_Itens_pedido";

				/**
				 * [tAdvancedHash_Itens_pedido finally ] stop
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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
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

		public Double CodigoCliente;

		public Double getCodigoCliente() {
			return this.CodigoCliente;
		}

		public Boolean CodigoClienteIsNullable() {
			return true;
		}

		public Boolean CodigoClienteIsKey() {
			return false;
		}

		public Integer CodigoClienteLength() {
			return 15;
		}

		public Integer CodigoClientePrecision() {
			return 0;
		}

		public String CodigoClienteDefault() {

			return "";

		}

		public String CodigoClienteComment() {

			return "";

		}

		public String CodigoClientePattern() {

			return "";

		}

		public String CodigoClienteOriginalDbColumnName() {

			return "CodigoCliente";

		}

		public java.util.Date DataEmissao;

		public java.util.Date getDataEmissao() {
			return this.DataEmissao;
		}

		public Boolean DataEmissaoIsNullable() {
			return true;
		}

		public Boolean DataEmissaoIsKey() {
			return false;
		}

		public Integer DataEmissaoLength() {
			return 23;
		}

		public Integer DataEmissaoPrecision() {
			return 3;
		}

		public String DataEmissaoDefault() {

			return null;

		}

		public String DataEmissaoComment() {

			return "";

		}

		public String DataEmissaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataEmissaoOriginalDbColumnName() {

			return "DataEmissao";

		}

		public java.util.Date PrevisaoEntrega;

		public java.util.Date getPrevisaoEntrega() {
			return this.PrevisaoEntrega;
		}

		public Boolean PrevisaoEntregaIsNullable() {
			return true;
		}

		public Boolean PrevisaoEntregaIsKey() {
			return false;
		}

		public Integer PrevisaoEntregaLength() {
			return 23;
		}

		public Integer PrevisaoEntregaPrecision() {
			return 3;
		}

		public String PrevisaoEntregaDefault() {

			return null;

		}

		public String PrevisaoEntregaComment() {

			return "";

		}

		public String PrevisaoEntregaPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevisaoEntregaOriginalDbColumnName() {

			return "PrevisaoEntrega";

		}

		public String TabelaPreco;

		public String getTabelaPreco() {
			return this.TabelaPreco;
		}

		public Boolean TabelaPrecoIsNullable() {
			return true;
		}

		public Boolean TabelaPrecoIsKey() {
			return false;
		}

		public Integer TabelaPrecoLength() {
			return 8;
		}

		public Integer TabelaPrecoPrecision() {
			return 0;
		}

		public String TabelaPrecoDefault() {

			return null;

		}

		public String TabelaPrecoComment() {

			return "";

		}

		public String TabelaPrecoPattern() {

			return "";

		}

		public String TabelaPrecoOriginalDbColumnName() {

			return "TabelaPreco";

		}

		public Integer CondicaoPagamento;

		public Integer getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 10;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return "";

		}

		public String CondicaoPagamentoComment() {

			return "";

		}

		public String CondicaoPagamentoPattern() {

			return "";

		}

		public String CondicaoPagamentoOriginalDbColumnName() {

			return "CondicaoPagamento";

		}

		public String TipoOperacao;

		public String getTipoOperacao() {
			return this.TipoOperacao;
		}

		public Boolean TipoOperacaoIsNullable() {
			return true;
		}

		public Boolean TipoOperacaoIsKey() {
			return false;
		}

		public Integer TipoOperacaoLength() {
			return 10;
		}

		public Integer TipoOperacaoPrecision() {
			return 0;
		}

		public String TipoOperacaoDefault() {

			return null;

		}

		public String TipoOperacaoComment() {

			return "";

		}

		public String TipoOperacaoPattern() {

			return "";

		}

		public String TipoOperacaoOriginalDbColumnName() {

			return "TipoOperacao";

		}

		public Integer Representante;

		public Integer getRepresentante() {
			return this.Representante;
		}

		public Boolean RepresentanteIsNullable() {
			return true;
		}

		public Boolean RepresentanteIsKey() {
			return false;
		}

		public Integer RepresentanteLength() {
			return 10;
		}

		public Integer RepresentantePrecision() {
			return 0;
		}

		public String RepresentanteDefault() {

			return "";

		}

		public String RepresentanteComment() {

			return "";

		}

		public String RepresentantePattern() {

			return "";

		}

		public String RepresentanteOriginalDbColumnName() {

			return "Representante";

		}

		public String SituacaoPedido;

		public String getSituacaoPedido() {
			return this.SituacaoPedido;
		}

		public Boolean SituacaoPedidoIsNullable() {
			return true;
		}

		public Boolean SituacaoPedidoIsKey() {
			return false;
		}

		public Integer SituacaoPedidoLength() {
			return 3;
		}

		public Integer SituacaoPedidoPrecision() {
			return 0;
		}

		public String SituacaoPedidoDefault() {

			return null;

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

		public String SituacaoCorporativa;

		public String getSituacaoCorporativa() {
			return this.SituacaoCorporativa;
		}

		public Boolean SituacaoCorporativaIsNullable() {
			return true;
		}

		public Boolean SituacaoCorporativaIsKey() {
			return false;
		}

		public Integer SituacaoCorporativaLength() {
			return 20;
		}

		public Integer SituacaoCorporativaPrecision() {
			return 0;
		}

		public String SituacaoCorporativaDefault() {

			return null;

		}

		public String SituacaoCorporativaComment() {

			return "";

		}

		public String SituacaoCorporativaPattern() {

			return "";

		}

		public String SituacaoCorporativaOriginalDbColumnName() {

			return "SituacaoCorporativa";

		}

		public Integer SequenciaItem;

		public Integer getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return true;
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

		public String CodigoTES;

		public String getCodigoTES() {
			return this.CodigoTES;
		}

		public Boolean CodigoTESIsNullable() {
			return true;
		}

		public Boolean CodigoTESIsKey() {
			return false;
		}

		public Integer CodigoTESLength() {
			return 10;
		}

		public Integer CodigoTESPrecision() {
			return 0;
		}

		public String CodigoTESDefault() {

			return null;

		}

		public String CodigoTESComment() {

			return "";

		}

		public String CodigoTESPattern() {

			return "";

		}

		public String CodigoTESOriginalDbColumnName() {

			return "CodigoTES";

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
			return 15;
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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

				result = prime * result + ((this.SequenciaItem == null) ? 0 : this.SequenciaItem.hashCode());

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
			final row3Struct other = (row3Struct) obj;

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			if (this.SequenciaItem == null) {
				if (other.SequenciaItem != null)
					return false;

			} else if (!this.SequenciaItem.equals(other.SequenciaItem))

				return false;

			if (this.CodigoProduto == null) {
				if (other.CodigoProduto != null)
					return false;

			} else if (!this.CodigoProduto.equals(other.CodigoProduto))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.CodigoCliente = this.CodigoCliente;
			other.DataEmissao = this.DataEmissao;
			other.PrevisaoEntrega = this.PrevisaoEntrega;
			other.TabelaPreco = this.TabelaPreco;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.TipoOperacao = this.TipoOperacao;
			other.Representante = this.Representante;
			other.SituacaoPedido = this.SituacaoPedido;
			other.SituacaoCorporativa = this.SituacaoCorporativa;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.QuantidadeProduto = this.QuantidadeProduto;
			other.QuantidadeAtendida = this.QuantidadeAtendida;
			other.QuantidadeCancelada = this.QuantidadeCancelada;
			other.PrecoUnitario = this.PrecoUnitario;
			other.PrecoLiquido = this.PrecoLiquido;
			other.CodigoTES = this.CodigoTES;
			other.Operacao = this.Operacao;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.SequenciaItem = this.SequenciaItem;
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",CodigoCliente=" + String.valueOf(CodigoCliente));
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",PrevisaoEntrega=" + String.valueOf(PrevisaoEntrega));
			sb.append(",TabelaPreco=" + TabelaPreco);
			sb.append(",CondicaoPagamento=" + String.valueOf(CondicaoPagamento));
			sb.append(",TipoOperacao=" + TipoOperacao);
			sb.append(",Representante=" + String.valueOf(Representante));
			sb.append(",SituacaoPedido=" + SituacaoPedido);
			sb.append(",SituacaoCorporativa=" + SituacaoCorporativa);
			sb.append(",SequenciaItem=" + String.valueOf(SequenciaItem));
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",QuantidadeProduto=" + String.valueOf(QuantidadeProduto));
			sb.append(",QuantidadeAtendida=" + String.valueOf(QuantidadeAtendida));
			sb.append(",QuantidadeCancelada=" + String.valueOf(QuantidadeCancelada));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",PrecoLiquido=" + String.valueOf(PrecoLiquido));
			sb.append(",CodigoTES=" + CodigoTES);
			sb.append(",Operacao=" + Operacao);
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

			if (CodigoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCliente);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (PrevisaoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevisaoEntrega);
			}

			sb.append("|");

			if (TabelaPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(TabelaPreco);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (TipoOperacao == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoOperacao);
			}

			sb.append("|");

			if (Representante == null) {
				sb.append("<null>");
			} else {
				sb.append(Representante);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (SituacaoCorporativa == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoCorporativa);
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

			if (CodigoTES == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoTES);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.SequenciaItem, other.SequenciaItem);
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
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
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

		public Double CodigoCliente;

		public Double getCodigoCliente() {
			return this.CodigoCliente;
		}

		public Boolean CodigoClienteIsNullable() {
			return true;
		}

		public Boolean CodigoClienteIsKey() {
			return false;
		}

		public Integer CodigoClienteLength() {
			return 15;
		}

		public Integer CodigoClientePrecision() {
			return 0;
		}

		public String CodigoClienteDefault() {

			return "";

		}

		public String CodigoClienteComment() {

			return "";

		}

		public String CodigoClientePattern() {

			return "";

		}

		public String CodigoClienteOriginalDbColumnName() {

			return "CodigoCliente";

		}

		public java.util.Date DataEmissao;

		public java.util.Date getDataEmissao() {
			return this.DataEmissao;
		}

		public Boolean DataEmissaoIsNullable() {
			return true;
		}

		public Boolean DataEmissaoIsKey() {
			return false;
		}

		public Integer DataEmissaoLength() {
			return 23;
		}

		public Integer DataEmissaoPrecision() {
			return 3;
		}

		public String DataEmissaoDefault() {

			return null;

		}

		public String DataEmissaoComment() {

			return "";

		}

		public String DataEmissaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataEmissaoOriginalDbColumnName() {

			return "DataEmissao";

		}

		public java.util.Date PrevisaoEntrega;

		public java.util.Date getPrevisaoEntrega() {
			return this.PrevisaoEntrega;
		}

		public Boolean PrevisaoEntregaIsNullable() {
			return true;
		}

		public Boolean PrevisaoEntregaIsKey() {
			return false;
		}

		public Integer PrevisaoEntregaLength() {
			return 23;
		}

		public Integer PrevisaoEntregaPrecision() {
			return 3;
		}

		public String PrevisaoEntregaDefault() {

			return null;

		}

		public String PrevisaoEntregaComment() {

			return "";

		}

		public String PrevisaoEntregaPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevisaoEntregaOriginalDbColumnName() {

			return "PrevisaoEntrega";

		}

		public String TabelaPreco;

		public String getTabelaPreco() {
			return this.TabelaPreco;
		}

		public Boolean TabelaPrecoIsNullable() {
			return true;
		}

		public Boolean TabelaPrecoIsKey() {
			return false;
		}

		public Integer TabelaPrecoLength() {
			return 8;
		}

		public Integer TabelaPrecoPrecision() {
			return 0;
		}

		public String TabelaPrecoDefault() {

			return null;

		}

		public String TabelaPrecoComment() {

			return "";

		}

		public String TabelaPrecoPattern() {

			return "";

		}

		public String TabelaPrecoOriginalDbColumnName() {

			return "TabelaPreco";

		}

		public Integer CondicaoPagamento;

		public Integer getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 10;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return "";

		}

		public String CondicaoPagamentoComment() {

			return "";

		}

		public String CondicaoPagamentoPattern() {

			return "";

		}

		public String CondicaoPagamentoOriginalDbColumnName() {

			return "CondicaoPagamento";

		}

		public String TipoOperacao;

		public String getTipoOperacao() {
			return this.TipoOperacao;
		}

		public Boolean TipoOperacaoIsNullable() {
			return true;
		}

		public Boolean TipoOperacaoIsKey() {
			return false;
		}

		public Integer TipoOperacaoLength() {
			return 10;
		}

		public Integer TipoOperacaoPrecision() {
			return 0;
		}

		public String TipoOperacaoDefault() {

			return null;

		}

		public String TipoOperacaoComment() {

			return "";

		}

		public String TipoOperacaoPattern() {

			return "";

		}

		public String TipoOperacaoOriginalDbColumnName() {

			return "TipoOperacao";

		}

		public Integer Representante;

		public Integer getRepresentante() {
			return this.Representante;
		}

		public Boolean RepresentanteIsNullable() {
			return true;
		}

		public Boolean RepresentanteIsKey() {
			return false;
		}

		public Integer RepresentanteLength() {
			return 10;
		}

		public Integer RepresentantePrecision() {
			return 0;
		}

		public String RepresentanteDefault() {

			return "";

		}

		public String RepresentanteComment() {

			return "";

		}

		public String RepresentantePattern() {

			return "";

		}

		public String RepresentanteOriginalDbColumnName() {

			return "Representante";

		}

		public String SituacaoPedido;

		public String getSituacaoPedido() {
			return this.SituacaoPedido;
		}

		public Boolean SituacaoPedidoIsNullable() {
			return true;
		}

		public Boolean SituacaoPedidoIsKey() {
			return false;
		}

		public Integer SituacaoPedidoLength() {
			return 3;
		}

		public Integer SituacaoPedidoPrecision() {
			return 0;
		}

		public String SituacaoPedidoDefault() {

			return null;

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

		public String SituacaoCorporativa;

		public String getSituacaoCorporativa() {
			return this.SituacaoCorporativa;
		}

		public Boolean SituacaoCorporativaIsNullable() {
			return true;
		}

		public Boolean SituacaoCorporativaIsKey() {
			return false;
		}

		public Integer SituacaoCorporativaLength() {
			return 20;
		}

		public Integer SituacaoCorporativaPrecision() {
			return 0;
		}

		public String SituacaoCorporativaDefault() {

			return null;

		}

		public String SituacaoCorporativaComment() {

			return "";

		}

		public String SituacaoCorporativaPattern() {

			return "";

		}

		public String SituacaoCorporativaOriginalDbColumnName() {

			return "SituacaoCorporativa";

		}

		public Integer SequenciaItem;

		public Integer getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return true;
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

		public String CodigoTES;

		public String getCodigoTES() {
			return this.CodigoTES;
		}

		public Boolean CodigoTESIsNullable() {
			return true;
		}

		public Boolean CodigoTESIsKey() {
			return false;
		}

		public Integer CodigoTESLength() {
			return 10;
		}

		public Integer CodigoTESPrecision() {
			return 0;
		}

		public String CodigoTESDefault() {

			return null;

		}

		public String CodigoTESComment() {

			return "";

		}

		public String CodigoTESPattern() {

			return "";

		}

		public String CodigoTESOriginalDbColumnName() {

			return "CodigoTES";

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
			return 15;
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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

				result = prime * result + ((this.SequenciaItem == null) ? 0 : this.SequenciaItem.hashCode());

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

			if (this.SequenciaItem == null) {
				if (other.SequenciaItem != null)
					return false;

			} else if (!this.SequenciaItem.equals(other.SequenciaItem))

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
			other.CodigoCliente = this.CodigoCliente;
			other.DataEmissao = this.DataEmissao;
			other.PrevisaoEntrega = this.PrevisaoEntrega;
			other.TabelaPreco = this.TabelaPreco;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.TipoOperacao = this.TipoOperacao;
			other.Representante = this.Representante;
			other.SituacaoPedido = this.SituacaoPedido;
			other.SituacaoCorporativa = this.SituacaoCorporativa;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.QuantidadeProduto = this.QuantidadeProduto;
			other.QuantidadeAtendida = this.QuantidadeAtendida;
			other.QuantidadeCancelada = this.QuantidadeCancelada;
			other.PrecoUnitario = this.PrecoUnitario;
			other.PrecoLiquido = this.PrecoLiquido;
			other.CodigoTES = this.CodigoTES;
			other.Operacao = this.Operacao;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.SequenciaItem = this.SequenciaItem;
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",CodigoCliente=" + String.valueOf(CodigoCliente));
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",PrevisaoEntrega=" + String.valueOf(PrevisaoEntrega));
			sb.append(",TabelaPreco=" + TabelaPreco);
			sb.append(",CondicaoPagamento=" + String.valueOf(CondicaoPagamento));
			sb.append(",TipoOperacao=" + TipoOperacao);
			sb.append(",Representante=" + String.valueOf(Representante));
			sb.append(",SituacaoPedido=" + SituacaoPedido);
			sb.append(",SituacaoCorporativa=" + SituacaoCorporativa);
			sb.append(",SequenciaItem=" + String.valueOf(SequenciaItem));
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",QuantidadeProduto=" + String.valueOf(QuantidadeProduto));
			sb.append(",QuantidadeAtendida=" + String.valueOf(QuantidadeAtendida));
			sb.append(",QuantidadeCancelada=" + String.valueOf(QuantidadeCancelada));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",PrecoLiquido=" + String.valueOf(PrecoLiquido));
			sb.append(",CodigoTES=" + CodigoTES);
			sb.append(",Operacao=" + Operacao);
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

			if (CodigoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCliente);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (PrevisaoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevisaoEntrega);
			}

			sb.append("|");

			if (TabelaPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(TabelaPreco);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (TipoOperacao == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoOperacao);
			}

			sb.append("|");

			if (Representante == null) {
				sb.append("<null>");
			} else {
				sb.append(Representante);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (SituacaoCorporativa == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoCorporativa);
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

			if (CodigoTES == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoTES);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
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

			returnValue = checkNullsAndCompare(this.SequenciaItem, other.SequenciaItem);
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

	public static class CarteiraStruct implements routines.system.IPersistableRow<CarteiraStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
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

		public Double CodigoCliente;

		public Double getCodigoCliente() {
			return this.CodigoCliente;
		}

		public Boolean CodigoClienteIsNullable() {
			return true;
		}

		public Boolean CodigoClienteIsKey() {
			return false;
		}

		public Integer CodigoClienteLength() {
			return 15;
		}

		public Integer CodigoClientePrecision() {
			return 0;
		}

		public String CodigoClienteDefault() {

			return "";

		}

		public String CodigoClienteComment() {

			return "";

		}

		public String CodigoClientePattern() {

			return "";

		}

		public String CodigoClienteOriginalDbColumnName() {

			return "CodigoCliente";

		}

		public java.util.Date DataEmissao;

		public java.util.Date getDataEmissao() {
			return this.DataEmissao;
		}

		public Boolean DataEmissaoIsNullable() {
			return true;
		}

		public Boolean DataEmissaoIsKey() {
			return false;
		}

		public Integer DataEmissaoLength() {
			return 23;
		}

		public Integer DataEmissaoPrecision() {
			return 3;
		}

		public String DataEmissaoDefault() {

			return null;

		}

		public String DataEmissaoComment() {

			return "";

		}

		public String DataEmissaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataEmissaoOriginalDbColumnName() {

			return "DataEmissao";

		}

		public java.util.Date PrevisaoEntrega;

		public java.util.Date getPrevisaoEntrega() {
			return this.PrevisaoEntrega;
		}

		public Boolean PrevisaoEntregaIsNullable() {
			return true;
		}

		public Boolean PrevisaoEntregaIsKey() {
			return false;
		}

		public Integer PrevisaoEntregaLength() {
			return 23;
		}

		public Integer PrevisaoEntregaPrecision() {
			return 3;
		}

		public String PrevisaoEntregaDefault() {

			return null;

		}

		public String PrevisaoEntregaComment() {

			return "";

		}

		public String PrevisaoEntregaPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevisaoEntregaOriginalDbColumnName() {

			return "PrevisaoEntrega";

		}

		public String TabelaPreco;

		public String getTabelaPreco() {
			return this.TabelaPreco;
		}

		public Boolean TabelaPrecoIsNullable() {
			return true;
		}

		public Boolean TabelaPrecoIsKey() {
			return false;
		}

		public Integer TabelaPrecoLength() {
			return 8;
		}

		public Integer TabelaPrecoPrecision() {
			return 0;
		}

		public String TabelaPrecoDefault() {

			return null;

		}

		public String TabelaPrecoComment() {

			return "";

		}

		public String TabelaPrecoPattern() {

			return "";

		}

		public String TabelaPrecoOriginalDbColumnName() {

			return "TabelaPreco";

		}

		public Integer CondicaoPagamento;

		public Integer getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 10;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return "";

		}

		public String CondicaoPagamentoComment() {

			return "";

		}

		public String CondicaoPagamentoPattern() {

			return "";

		}

		public String CondicaoPagamentoOriginalDbColumnName() {

			return "CondicaoPagamento";

		}

		public String TipoOperacao;

		public String getTipoOperacao() {
			return this.TipoOperacao;
		}

		public Boolean TipoOperacaoIsNullable() {
			return true;
		}

		public Boolean TipoOperacaoIsKey() {
			return false;
		}

		public Integer TipoOperacaoLength() {
			return 10;
		}

		public Integer TipoOperacaoPrecision() {
			return 0;
		}

		public String TipoOperacaoDefault() {

			return null;

		}

		public String TipoOperacaoComment() {

			return "";

		}

		public String TipoOperacaoPattern() {

			return "";

		}

		public String TipoOperacaoOriginalDbColumnName() {

			return "TipoOperacao";

		}

		public Integer Representante;

		public Integer getRepresentante() {
			return this.Representante;
		}

		public Boolean RepresentanteIsNullable() {
			return true;
		}

		public Boolean RepresentanteIsKey() {
			return false;
		}

		public Integer RepresentanteLength() {
			return 10;
		}

		public Integer RepresentantePrecision() {
			return 0;
		}

		public String RepresentanteDefault() {

			return "";

		}

		public String RepresentanteComment() {

			return "";

		}

		public String RepresentantePattern() {

			return "";

		}

		public String RepresentanteOriginalDbColumnName() {

			return "Representante";

		}

		public String SituacaoPedido;

		public String getSituacaoPedido() {
			return this.SituacaoPedido;
		}

		public Boolean SituacaoPedidoIsNullable() {
			return true;
		}

		public Boolean SituacaoPedidoIsKey() {
			return false;
		}

		public Integer SituacaoPedidoLength() {
			return 3;
		}

		public Integer SituacaoPedidoPrecision() {
			return 0;
		}

		public String SituacaoPedidoDefault() {

			return null;

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

		public String SituacaoCorporativa;

		public String getSituacaoCorporativa() {
			return this.SituacaoCorporativa;
		}

		public Boolean SituacaoCorporativaIsNullable() {
			return true;
		}

		public Boolean SituacaoCorporativaIsKey() {
			return false;
		}

		public Integer SituacaoCorporativaLength() {
			return 20;
		}

		public Integer SituacaoCorporativaPrecision() {
			return 0;
		}

		public String SituacaoCorporativaDefault() {

			return null;

		}

		public String SituacaoCorporativaComment() {

			return "";

		}

		public String SituacaoCorporativaPattern() {

			return "";

		}

		public String SituacaoCorporativaOriginalDbColumnName() {

			return "SituacaoCorporativa";

		}

		public Integer SequenciaItem;

		public Integer getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return true;
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

		public String CodigoTES;

		public String getCodigoTES() {
			return this.CodigoTES;
		}

		public Boolean CodigoTESIsNullable() {
			return true;
		}

		public Boolean CodigoTESIsKey() {
			return false;
		}

		public Integer CodigoTESLength() {
			return 10;
		}

		public Integer CodigoTESPrecision() {
			return 0;
		}

		public String CodigoTESDefault() {

			return null;

		}

		public String CodigoTESComment() {

			return "";

		}

		public String CodigoTESPattern() {

			return "";

		}

		public String CodigoTESOriginalDbColumnName() {

			return "CodigoTES";

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
			return 15;
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

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

				result = prime * result + ((this.SequenciaItem == null) ? 0 : this.SequenciaItem.hashCode());

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
			final CarteiraStruct other = (CarteiraStruct) obj;

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			if (this.SequenciaItem == null) {
				if (other.SequenciaItem != null)
					return false;

			} else if (!this.SequenciaItem.equals(other.SequenciaItem))

				return false;

			if (this.CodigoProduto == null) {
				if (other.CodigoProduto != null)
					return false;

			} else if (!this.CodigoProduto.equals(other.CodigoProduto))

				return false;

			return true;
		}

		public void copyDataTo(CarteiraStruct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.CodigoCliente = this.CodigoCliente;
			other.DataEmissao = this.DataEmissao;
			other.PrevisaoEntrega = this.PrevisaoEntrega;
			other.TabelaPreco = this.TabelaPreco;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.TipoOperacao = this.TipoOperacao;
			other.Representante = this.Representante;
			other.SituacaoPedido = this.SituacaoPedido;
			other.SituacaoCorporativa = this.SituacaoCorporativa;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.QuantidadeProduto = this.QuantidadeProduto;
			other.QuantidadeAtendida = this.QuantidadeAtendida;
			other.QuantidadeCancelada = this.QuantidadeCancelada;
			other.PrecoUnitario = this.PrecoUnitario;
			other.PrecoLiquido = this.PrecoLiquido;
			other.CodigoTES = this.CodigoTES;
			other.Operacao = this.Operacao;

		}

		public void copyKeysDataTo(CarteiraStruct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.SequenciaItem = this.SequenciaItem;
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.TabelaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.TipoOperacao = readString(dis);

					this.Representante = readInteger(dis);

					this.SituacaoPedido = readString(dis);

					this.SituacaoCorporativa = readString(dis);

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

					this.CodigoTES = readString(dis);

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.TabelaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.TipoOperacao, dos);

				// Integer

				writeInteger(this.Representante, dos);

				// String

				writeString(this.SituacaoPedido, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

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

				// String

				writeString(this.CodigoTES, dos);

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",CodigoCliente=" + String.valueOf(CodigoCliente));
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",PrevisaoEntrega=" + String.valueOf(PrevisaoEntrega));
			sb.append(",TabelaPreco=" + TabelaPreco);
			sb.append(",CondicaoPagamento=" + String.valueOf(CondicaoPagamento));
			sb.append(",TipoOperacao=" + TipoOperacao);
			sb.append(",Representante=" + String.valueOf(Representante));
			sb.append(",SituacaoPedido=" + SituacaoPedido);
			sb.append(",SituacaoCorporativa=" + SituacaoCorporativa);
			sb.append(",SequenciaItem=" + String.valueOf(SequenciaItem));
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",QuantidadeProduto=" + String.valueOf(QuantidadeProduto));
			sb.append(",QuantidadeAtendida=" + String.valueOf(QuantidadeAtendida));
			sb.append(",QuantidadeCancelada=" + String.valueOf(QuantidadeCancelada));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",PrecoLiquido=" + String.valueOf(PrecoLiquido));
			sb.append(",CodigoTES=" + CodigoTES);
			sb.append(",Operacao=" + Operacao);
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

			if (CodigoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCliente);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (PrevisaoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevisaoEntrega);
			}

			sb.append("|");

			if (TabelaPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(TabelaPreco);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (TipoOperacao == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoOperacao);
			}

			sb.append("|");

			if (Representante == null) {
				sb.append("<null>");
			} else {
				sb.append(Representante);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (SituacaoCorporativa == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoCorporativa);
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

			if (CodigoTES == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoTES);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(CarteiraStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.SequenciaItem, other.SequenciaItem);
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

	public static class Capa_pedidoStruct implements routines.system.IPersistableRow<Capa_pedidoStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];

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

		public Double CodigoCliente;

		public Double getCodigoCliente() {
			return this.CodigoCliente;
		}

		public Boolean CodigoClienteIsNullable() {
			return true;
		}

		public Boolean CodigoClienteIsKey() {
			return false;
		}

		public Integer CodigoClienteLength() {
			return 15;
		}

		public Integer CodigoClientePrecision() {
			return 0;
		}

		public String CodigoClienteDefault() {

			return "";

		}

		public String CodigoClienteComment() {

			return "";

		}

		public String CodigoClientePattern() {

			return "";

		}

		public String CodigoClienteOriginalDbColumnName() {

			return "CodigoCliente";

		}

		public java.util.Date DataEmissao;

		public java.util.Date getDataEmissao() {
			return this.DataEmissao;
		}

		public Boolean DataEmissaoIsNullable() {
			return true;
		}

		public Boolean DataEmissaoIsKey() {
			return false;
		}

		public Integer DataEmissaoLength() {
			return 23;
		}

		public Integer DataEmissaoPrecision() {
			return 3;
		}

		public String DataEmissaoDefault() {

			return null;

		}

		public String DataEmissaoComment() {

			return "";

		}

		public String DataEmissaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataEmissaoOriginalDbColumnName() {

			return "DataEmissao";

		}

		public java.util.Date PrevisaoEntrega;

		public java.util.Date getPrevisaoEntrega() {
			return this.PrevisaoEntrega;
		}

		public Boolean PrevisaoEntregaIsNullable() {
			return true;
		}

		public Boolean PrevisaoEntregaIsKey() {
			return false;
		}

		public Integer PrevisaoEntregaLength() {
			return 23;
		}

		public Integer PrevisaoEntregaPrecision() {
			return 3;
		}

		public String PrevisaoEntregaDefault() {

			return null;

		}

		public String PrevisaoEntregaComment() {

			return "";

		}

		public String PrevisaoEntregaPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevisaoEntregaOriginalDbColumnName() {

			return "PrevisaoEntrega";

		}

		public String ListaPreco;

		public String getListaPreco() {
			return this.ListaPreco;
		}

		public Boolean ListaPrecoIsNullable() {
			return true;
		}

		public Boolean ListaPrecoIsKey() {
			return false;
		}

		public Integer ListaPrecoLength() {
			return 8;
		}

		public Integer ListaPrecoPrecision() {
			return 0;
		}

		public String ListaPrecoDefault() {

			return null;

		}

		public String ListaPrecoComment() {

			return "";

		}

		public String ListaPrecoPattern() {

			return "";

		}

		public String ListaPrecoOriginalDbColumnName() {

			return "ListaPreco";

		}

		public Integer CondicaoPagamento;

		public Integer getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 10;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return "";

		}

		public String CondicaoPagamentoComment() {

			return "";

		}

		public String CondicaoPagamentoPattern() {

			return "";

		}

		public String CondicaoPagamentoOriginalDbColumnName() {

			return "CondicaoPagamento";

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

		public Integer DbPedPortador;

		public Integer getDbPedPortador() {
			return this.DbPedPortador;
		}

		public Boolean DbPedPortadorIsNullable() {
			return true;
		}

		public Boolean DbPedPortadorIsKey() {
			return false;
		}

		public Integer DbPedPortadorLength() {
			return 10;
		}

		public Integer DbPedPortadorPrecision() {
			return 0;
		}

		public String DbPedPortadorDefault() {

			return "";

		}

		public String DbPedPortadorComment() {

			return "";

		}

		public String DbPedPortadorPattern() {

			return "";

		}

		public String DbPedPortadorOriginalDbColumnName() {

			return "DbPedPortador";

		}

		public Integer DbPedCodMoeda;

		public Integer getDbPedCodMoeda() {
			return this.DbPedCodMoeda;
		}

		public Boolean DbPedCodMoedaIsNullable() {
			return true;
		}

		public Boolean DbPedCodMoedaIsKey() {
			return false;
		}

		public Integer DbPedCodMoedaLength() {
			return 10;
		}

		public Integer DbPedCodMoedaPrecision() {
			return 0;
		}

		public String DbPedCodMoedaDefault() {

			return "";

		}

		public String DbPedCodMoedaComment() {

			return "";

		}

		public String DbPedCodMoedaPattern() {

			return "";

		}

		public String DbPedCodMoedaOriginalDbColumnName() {

			return "DbPedCodMoeda";

		}

		public Short DbPedTabFino;

		public Short getDbPedTabFino() {
			return this.DbPedTabFino;
		}

		public Boolean DbPedTabFinoIsNullable() {
			return true;
		}

		public Boolean DbPedTabFinoIsKey() {
			return false;
		}

		public Integer DbPedTabFinoLength() {
			return 5;
		}

		public Integer DbPedTabFinoPrecision() {
			return 0;
		}

		public String DbPedTabFinoDefault() {

			return "";

		}

		public String DbPedTabFinoComment() {

			return "";

		}

		public String DbPedTabFinoPattern() {

			return "";

		}

		public String DbPedTabFinoOriginalDbColumnName() {

			return "DbPedTabFino";

		}

		public Short DbPedTabFindo;

		public Short getDbPedTabFindo() {
			return this.DbPedTabFindo;
		}

		public Boolean DbPedTabFindoIsNullable() {
			return true;
		}

		public Boolean DbPedTabFindoIsKey() {
			return false;
		}

		public Integer DbPedTabFindoLength() {
			return 5;
		}

		public Integer DbPedTabFindoPrecision() {
			return 0;
		}

		public String DbPedTabFindoDefault() {

			return "";

		}

		public String DbPedTabFindoComment() {

			return "";

		}

		public String DbPedTabFindoPattern() {

			return "";

		}

		public String DbPedTabFindoOriginalDbColumnName() {

			return "DbPedTabFindo";

		}

		public Short DbPedTabFini;

		public Short getDbPedTabFini() {
			return this.DbPedTabFini;
		}

		public Boolean DbPedTabFiniIsNullable() {
			return true;
		}

		public Boolean DbPedTabFiniIsKey() {
			return false;
		}

		public Integer DbPedTabFiniLength() {
			return 5;
		}

		public Integer DbPedTabFiniPrecision() {
			return 0;
		}

		public String DbPedTabFiniDefault() {

			return "";

		}

		public String DbPedTabFiniComment() {

			return "";

		}

		public String DbPedTabFiniPattern() {

			return "";

		}

		public String DbPedTabFiniOriginalDbColumnName() {

			return "DbPedTabFini";

		}

		public Short DbPedTabFindi;

		public Short getDbPedTabFindi() {
			return this.DbPedTabFindi;
		}

		public Boolean DbPedTabFindiIsNullable() {
			return true;
		}

		public Boolean DbPedTabFindiIsKey() {
			return false;
		}

		public Integer DbPedTabFindiLength() {
			return 5;
		}

		public Integer DbPedTabFindiPrecision() {
			return 0;
		}

		public String DbPedTabFindiDefault() {

			return "";

		}

		public String DbPedTabFindiComment() {

			return "";

		}

		public String DbPedTabFindiPattern() {

			return "";

		}

		public String DbPedTabFindiOriginalDbColumnName() {

			return "DbPedTabFindi";

		}

		public Integer Representante;

		public Integer getRepresentante() {
			return this.Representante;
		}

		public Boolean RepresentanteIsNullable() {
			return true;
		}

		public Boolean RepresentanteIsKey() {
			return false;
		}

		public Integer RepresentanteLength() {
			return 10;
		}

		public Integer RepresentantePrecision() {
			return 0;
		}

		public String RepresentanteDefault() {

			return "";

		}

		public String RepresentanteComment() {

			return "";

		}

		public String RepresentantePattern() {

			return "";

		}

		public String RepresentanteOriginalDbColumnName() {

			return "Representante";

		}

		public Double DbPedComiso;

		public Double getDbPedComiso() {
			return this.DbPedComiso;
		}

		public Boolean DbPedComisoIsNullable() {
			return true;
		}

		public Boolean DbPedComisoIsKey() {
			return false;
		}

		public Integer DbPedComisoLength() {
			return 15;
		}

		public Integer DbPedComisoPrecision() {
			return 0;
		}

		public String DbPedComisoDefault() {

			return "";

		}

		public String DbPedComisoComment() {

			return "";

		}

		public String DbPedComisoPattern() {

			return "";

		}

		public String DbPedComisoOriginalDbColumnName() {

			return "DbPedComiso";

		}

		public Double DbPedComisi;

		public Double getDbPedComisi() {
			return this.DbPedComisi;
		}

		public Boolean DbPedComisiIsNullable() {
			return true;
		}

		public Boolean DbPedComisiIsKey() {
			return false;
		}

		public Integer DbPedComisiLength() {
			return 15;
		}

		public Integer DbPedComisiPrecision() {
			return 0;
		}

		public String DbPedComisiDefault() {

			return "";

		}

		public String DbPedComisiComment() {

			return "";

		}

		public String DbPedComisiPattern() {

			return "";

		}

		public String DbPedComisiOriginalDbColumnName() {

			return "DbPedComisi";

		}

		public Double DbPedPercFrete;

		public Double getDbPedPercFrete() {
			return this.DbPedPercFrete;
		}

		public Boolean DbPedPercFreteIsNullable() {
			return true;
		}

		public Boolean DbPedPercFreteIsKey() {
			return false;
		}

		public Integer DbPedPercFreteLength() {
			return 15;
		}

		public Integer DbPedPercFretePrecision() {
			return 0;
		}

		public String DbPedPercFreteDefault() {

			return "";

		}

		public String DbPedPercFreteComment() {

			return "";

		}

		public String DbPedPercFretePattern() {

			return "";

		}

		public String DbPedPercFreteOriginalDbColumnName() {

			return "DbPedPercFrete";

		}

		public Double DbPedCodTransp;

		public Double getDbPedCodTransp() {
			return this.DbPedCodTransp;
		}

		public Boolean DbPedCodTranspIsNullable() {
			return true;
		}

		public Boolean DbPedCodTranspIsKey() {
			return false;
		}

		public Integer DbPedCodTranspLength() {
			return 15;
		}

		public Integer DbPedCodTranspPrecision() {
			return 0;
		}

		public String DbPedCodTranspDefault() {

			return "";

		}

		public String DbPedCodTranspComment() {

			return "";

		}

		public String DbPedCodTranspPattern() {

			return "";

		}

		public String DbPedCodTranspOriginalDbColumnName() {

			return "DbPedCodTransp";

		}

		public Double DbPedCodRedesp;

		public Double getDbPedCodRedesp() {
			return this.DbPedCodRedesp;
		}

		public Boolean DbPedCodRedespIsNullable() {
			return true;
		}

		public Boolean DbPedCodRedespIsKey() {
			return false;
		}

		public Integer DbPedCodRedespLength() {
			return 15;
		}

		public Integer DbPedCodRedespPrecision() {
			return 0;
		}

		public String DbPedCodRedespDefault() {

			return "";

		}

		public String DbPedCodRedespComment() {

			return "";

		}

		public String DbPedCodRedespPattern() {

			return "";

		}

		public String DbPedCodRedespOriginalDbColumnName() {

			return "DbPedCodRedesp";

		}

		public Short DbPedTipoFrete;

		public Short getDbPedTipoFrete() {
			return this.DbPedTipoFrete;
		}

		public Boolean DbPedTipoFreteIsNullable() {
			return true;
		}

		public Boolean DbPedTipoFreteIsKey() {
			return false;
		}

		public Integer DbPedTipoFreteLength() {
			return 5;
		}

		public Integer DbPedTipoFretePrecision() {
			return 0;
		}

		public String DbPedTipoFreteDefault() {

			return "";

		}

		public String DbPedTipoFreteComment() {

			return "";

		}

		public String DbPedTipoFretePattern() {

			return "";

		}

		public String DbPedTipoFreteOriginalDbColumnName() {

			return "DbPedTipoFrete";

		}

		public String DbPedOrdCompra;

		public String getDbPedOrdCompra() {
			return this.DbPedOrdCompra;
		}

		public Boolean DbPedOrdCompraIsNullable() {
			return true;
		}

		public Boolean DbPedOrdCompraIsKey() {
			return false;
		}

		public Integer DbPedOrdCompraLength() {
			return 25;
		}

		public Integer DbPedOrdCompraPrecision() {
			return 0;
		}

		public String DbPedOrdCompraDefault() {

			return "";

		}

		public String DbPedOrdCompraComment() {

			return "";

		}

		public String DbPedOrdCompraPattern() {

			return "";

		}

		public String DbPedOrdCompraOriginalDbColumnName() {

			return "DbPedOrdCompra";

		}

		public Double DbPedDescto;

		public Double getDbPedDescto() {
			return this.DbPedDescto;
		}

		public Boolean DbPedDesctoIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoIsKey() {
			return false;
		}

		public Integer DbPedDesctoLength() {
			return 15;
		}

		public Integer DbPedDesctoPrecision() {
			return 0;
		}

		public String DbPedDesctoDefault() {

			return "";

		}

		public String DbPedDesctoComment() {

			return "";

		}

		public String DbPedDesctoPattern() {

			return "";

		}

		public String DbPedDesctoOriginalDbColumnName() {

			return "DbPedDescto";

		}

		public Double DbPedDescto2;

		public Double getDbPedDescto2() {
			return this.DbPedDescto2;
		}

		public Boolean DbPedDescto2IsNullable() {
			return true;
		}

		public Boolean DbPedDescto2IsKey() {
			return false;
		}

		public Integer DbPedDescto2Length() {
			return 15;
		}

		public Integer DbPedDescto2Precision() {
			return 0;
		}

		public String DbPedDescto2Default() {

			return "";

		}

		public String DbPedDescto2Comment() {

			return "";

		}

		public String DbPedDescto2Pattern() {

			return "";

		}

		public String DbPedDescto2OriginalDbColumnName() {

			return "DbPedDescto2";

		}

		public Double DbPedDescto3;

		public Double getDbPedDescto3() {
			return this.DbPedDescto3;
		}

		public Boolean DbPedDescto3IsNullable() {
			return true;
		}

		public Boolean DbPedDescto3IsKey() {
			return false;
		}

		public Integer DbPedDescto3Length() {
			return 15;
		}

		public Integer DbPedDescto3Precision() {
			return 0;
		}

		public String DbPedDescto3Default() {

			return "";

		}

		public String DbPedDescto3Comment() {

			return "";

		}

		public String DbPedDescto3Pattern() {

			return "";

		}

		public String DbPedDescto3OriginalDbColumnName() {

			return "DbPedDescto3";

		}

		public Double DbPedDescto4;

		public Double getDbPedDescto4() {
			return this.DbPedDescto4;
		}

		public Boolean DbPedDescto4IsNullable() {
			return true;
		}

		public Boolean DbPedDescto4IsKey() {
			return false;
		}

		public Integer DbPedDescto4Length() {
			return 15;
		}

		public Integer DbPedDescto4Precision() {
			return 0;
		}

		public String DbPedDescto4Default() {

			return "";

		}

		public String DbPedDescto4Comment() {

			return "";

		}

		public String DbPedDescto4Pattern() {

			return "";

		}

		public String DbPedDescto4OriginalDbColumnName() {

			return "DbPedDescto4";

		}

		public Double DbPedDescto5;

		public Double getDbPedDescto5() {
			return this.DbPedDescto5;
		}

		public Boolean DbPedDescto5IsNullable() {
			return true;
		}

		public Boolean DbPedDescto5IsKey() {
			return false;
		}

		public Integer DbPedDescto5Length() {
			return 15;
		}

		public Integer DbPedDescto5Precision() {
			return 0;
		}

		public String DbPedDescto5Default() {

			return "";

		}

		public String DbPedDescto5Comment() {

			return "";

		}

		public String DbPedDescto5Pattern() {

			return "";

		}

		public String DbPedDescto5OriginalDbColumnName() {

			return "DbPedDescto5";

		}

		public Double DbPedDescto6;

		public Double getDbPedDescto6() {
			return this.DbPedDescto6;
		}

		public Boolean DbPedDescto6IsNullable() {
			return true;
		}

		public Boolean DbPedDescto6IsKey() {
			return false;
		}

		public Integer DbPedDescto6Length() {
			return 15;
		}

		public Integer DbPedDescto6Precision() {
			return 0;
		}

		public String DbPedDescto6Default() {

			return "";

		}

		public String DbPedDescto6Comment() {

			return "";

		}

		public String DbPedDescto6Pattern() {

			return "";

		}

		public String DbPedDescto6OriginalDbColumnName() {

			return "DbPedDescto6";

		}

		public Double DbPedDescto7;

		public Double getDbPedDescto7() {
			return this.DbPedDescto7;
		}

		public Boolean DbPedDescto7IsNullable() {
			return true;
		}

		public Boolean DbPedDescto7IsKey() {
			return false;
		}

		public Integer DbPedDescto7Length() {
			return 15;
		}

		public Integer DbPedDescto7Precision() {
			return 0;
		}

		public String DbPedDescto7Default() {

			return "";

		}

		public String DbPedDescto7Comment() {

			return "";

		}

		public String DbPedDescto7Pattern() {

			return "";

		}

		public String DbPedDescto7OriginalDbColumnName() {

			return "DbPedDescto7";

		}

		public Double DbPedAcrescimo;

		public Double getDbPedAcrescimo() {
			return this.DbPedAcrescimo;
		}

		public Boolean DbPedAcrescimoIsNullable() {
			return true;
		}

		public Boolean DbPedAcrescimoIsKey() {
			return false;
		}

		public Integer DbPedAcrescimoLength() {
			return 15;
		}

		public Integer DbPedAcrescimoPrecision() {
			return 0;
		}

		public String DbPedAcrescimoDefault() {

			return "";

		}

		public String DbPedAcrescimoComment() {

			return "";

		}

		public String DbPedAcrescimoPattern() {

			return "";

		}

		public String DbPedAcrescimoOriginalDbColumnName() {

			return "DbPedAcrescimo";

		}

		public String DbPedMotivoBloq;

		public String getDbPedMotivoBloq() {
			return this.DbPedMotivoBloq;
		}

		public Boolean DbPedMotivoBloqIsNullable() {
			return true;
		}

		public Boolean DbPedMotivoBloqIsKey() {
			return false;
		}

		public Integer DbPedMotivoBloqLength() {
			return 15;
		}

		public Integer DbPedMotivoBloqPrecision() {
			return 0;
		}

		public String DbPedMotivoBloqDefault() {

			return null;

		}

		public String DbPedMotivoBloqComment() {

			return "";

		}

		public String DbPedMotivoBloqPattern() {

			return "";

		}

		public String DbPedMotivoBloqOriginalDbColumnName() {

			return "DbPedMotivoBloq";

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
			return 3;
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

		public String DbPedLibUsus;

		public String getDbPedLibUsus() {
			return this.DbPedLibUsus;
		}

		public Boolean DbPedLibUsusIsNullable() {
			return true;
		}

		public Boolean DbPedLibUsusIsKey() {
			return false;
		}

		public Integer DbPedLibUsusLength() {
			return 255;
		}

		public Integer DbPedLibUsusPrecision() {
			return 0;
		}

		public String DbPedLibUsusDefault() {

			return null;

		}

		public String DbPedLibUsusComment() {

			return "";

		}

		public String DbPedLibUsusPattern() {

			return "";

		}

		public String DbPedLibUsusOriginalDbColumnName() {

			return "DbPedLibUsus";

		}

		public String DbPedLibMots;

		public String getDbPedLibMots() {
			return this.DbPedLibMots;
		}

		public Boolean DbPedLibMotsIsNullable() {
			return true;
		}

		public Boolean DbPedLibMotsIsKey() {
			return false;
		}

		public Integer DbPedLibMotsLength() {
			return 255;
		}

		public Integer DbPedLibMotsPrecision() {
			return 0;
		}

		public String DbPedLibMotsDefault() {

			return null;

		}

		public String DbPedLibMotsComment() {

			return "";

		}

		public String DbPedLibMotsPattern() {

			return "";

		}

		public String DbPedLibMotsOriginalDbColumnName() {

			return "DbPedLibMots";

		}

		public String DbPedLibDatas;

		public String getDbPedLibDatas() {
			return this.DbPedLibDatas;
		}

		public Boolean DbPedLibDatasIsNullable() {
			return true;
		}

		public Boolean DbPedLibDatasIsKey() {
			return false;
		}

		public Integer DbPedLibDatasLength() {
			return 255;
		}

		public Integer DbPedLibDatasPrecision() {
			return 0;
		}

		public String DbPedLibDatasDefault() {

			return null;

		}

		public String DbPedLibDatasComment() {

			return "";

		}

		public String DbPedLibDatasPattern() {

			return "";

		}

		public String DbPedLibDatasOriginalDbColumnName() {

			return "DbPedLibDatas";

		}

		public java.util.Date DbPedDataAlter;

		public java.util.Date getDbPedDataAlter() {
			return this.DbPedDataAlter;
		}

		public Boolean DbPedDataAlterIsNullable() {
			return true;
		}

		public Boolean DbPedDataAlterIsKey() {
			return false;
		}

		public Integer DbPedDataAlterLength() {
			return 23;
		}

		public Integer DbPedDataAlterPrecision() {
			return 3;
		}

		public String DbPedDataAlterDefault() {

			return null;

		}

		public String DbPedDataAlterComment() {

			return "";

		}

		public String DbPedDataAlterPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataAlterOriginalDbColumnName() {

			return "DbPedDataAlter";

		}

		public java.util.Date DbPedDataEnvio;

		public java.util.Date getDbPedDataEnvio() {
			return this.DbPedDataEnvio;
		}

		public Boolean DbPedDataEnvioIsNullable() {
			return true;
		}

		public Boolean DbPedDataEnvioIsKey() {
			return false;
		}

		public Integer DbPedDataEnvioLength() {
			return 23;
		}

		public Integer DbPedDataEnvioPrecision() {
			return 3;
		}

		public String DbPedDataEnvioDefault() {

			return null;

		}

		public String DbPedDataEnvioComment() {

			return "";

		}

		public String DbPedDataEnvioPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataEnvioOriginalDbColumnName() {

			return "DbPedDataEnvio";

		}

		public String DbPedObserv;

		public String getDbPedObserv() {
			return this.DbPedObserv;
		}

		public Boolean DbPedObservIsNullable() {
			return true;
		}

		public Boolean DbPedObservIsKey() {
			return false;
		}

		public Integer DbPedObservLength() {
			return 255;
		}

		public Integer DbPedObservPrecision() {
			return 0;
		}

		public String DbPedObservDefault() {

			return null;

		}

		public String DbPedObservComment() {

			return "";

		}

		public String DbPedObservPattern() {

			return "";

		}

		public String DbPedObservOriginalDbColumnName() {

			return "DbPedObserv";

		}

		public String DbPedTexto;

		public String getDbPedTexto() {
			return this.DbPedTexto;
		}

		public Boolean DbPedTextoIsNullable() {
			return true;
		}

		public Boolean DbPedTextoIsKey() {
			return false;
		}

		public Integer DbPedTextoLength() {
			return 255;
		}

		public Integer DbPedTextoPrecision() {
			return 0;
		}

		public String DbPedTextoDefault() {

			return null;

		}

		public String DbPedTextoComment() {

			return "";

		}

		public String DbPedTextoPattern() {

			return "";

		}

		public String DbPedTextoOriginalDbColumnName() {

			return "DbPedTexto";

		}

		public String DbPedNroOrig;

		public String getDbPedNroOrig() {
			return this.DbPedNroOrig;
		}

		public Boolean DbPedNroOrigIsNullable() {
			return true;
		}

		public Boolean DbPedNroOrigIsKey() {
			return false;
		}

		public Integer DbPedNroOrigLength() {
			return 10;
		}

		public Integer DbPedNroOrigPrecision() {
			return 0;
		}

		public String DbPedNroOrigDefault() {

			return null;

		}

		public String DbPedNroOrigComment() {

			return "";

		}

		public String DbPedNroOrigPattern() {

			return "";

		}

		public String DbPedNroOrigOriginalDbColumnName() {

			return "DbPedNroOrig";

		}

		public Short DbPedTipoPreco;

		public Short getDbPedTipoPreco() {
			return this.DbPedTipoPreco;
		}

		public Boolean DbPedTipoPrecoIsNullable() {
			return true;
		}

		public Boolean DbPedTipoPrecoIsKey() {
			return false;
		}

		public Integer DbPedTipoPrecoLength() {
			return 5;
		}

		public Integer DbPedTipoPrecoPrecision() {
			return 0;
		}

		public String DbPedTipoPrecoDefault() {

			return "";

		}

		public String DbPedTipoPrecoComment() {

			return "";

		}

		public String DbPedTipoPrecoPattern() {

			return "";

		}

		public String DbPedTipoPrecoOriginalDbColumnName() {

			return "DbPedTipoPreco";

		}

		public Short DbPedFatur;

		public Short getDbPedFatur() {
			return this.DbPedFatur;
		}

		public Boolean DbPedFaturIsNullable() {
			return true;
		}

		public Boolean DbPedFaturIsKey() {
			return false;
		}

		public Integer DbPedFaturLength() {
			return 3;
		}

		public Integer DbPedFaturPrecision() {
			return 0;
		}

		public String DbPedFaturDefault() {

			return "";

		}

		public String DbPedFaturComment() {

			return "";

		}

		public String DbPedFaturPattern() {

			return "";

		}

		public String DbPedFaturOriginalDbColumnName() {

			return "DbPedFatur";

		}

		public java.util.Date DbPedDataReceb;

		public java.util.Date getDbPedDataReceb() {
			return this.DbPedDataReceb;
		}

		public Boolean DbPedDataRecebIsNullable() {
			return true;
		}

		public Boolean DbPedDataRecebIsKey() {
			return false;
		}

		public Integer DbPedDataRecebLength() {
			return 23;
		}

		public Integer DbPedDataRecebPrecision() {
			return 3;
		}

		public String DbPedDataRecebDefault() {

			return null;

		}

		public String DbPedDataRecebComment() {

			return "";

		}

		public String DbPedDataRecebPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataRecebOriginalDbColumnName() {

			return "DbPedDataReceb";

		}

		public String DbPedNroRe;

		public String getDbPedNroRe() {
			return this.DbPedNroRe;
		}

		public Boolean DbPedNroReIsNullable() {
			return true;
		}

		public Boolean DbPedNroReIsKey() {
			return false;
		}

		public Integer DbPedNroReLength() {
			return 25;
		}

		public Integer DbPedNroRePrecision() {
			return 0;
		}

		public String DbPedNroReDefault() {

			return null;

		}

		public String DbPedNroReComment() {

			return "";

		}

		public String DbPedNroRePattern() {

			return "";

		}

		public String DbPedNroReOriginalDbColumnName() {

			return "DbPedNroRe";

		}

		public Integer DbPedEndPgto;

		public Integer getDbPedEndPgto() {
			return this.DbPedEndPgto;
		}

		public Boolean DbPedEndPgtoIsNullable() {
			return true;
		}

		public Boolean DbPedEndPgtoIsKey() {
			return false;
		}

		public Integer DbPedEndPgtoLength() {
			return 10;
		}

		public Integer DbPedEndPgtoPrecision() {
			return 0;
		}

		public String DbPedEndPgtoDefault() {

			return "";

		}

		public String DbPedEndPgtoComment() {

			return "";

		}

		public String DbPedEndPgtoPattern() {

			return "";

		}

		public String DbPedEndPgtoOriginalDbColumnName() {

			return "DbPedEndPgto";

		}

		public String DbPedMeioTransp;

		public String getDbPedMeioTransp() {
			return this.DbPedMeioTransp;
		}

		public Boolean DbPedMeioTranspIsNullable() {
			return true;
		}

		public Boolean DbPedMeioTranspIsKey() {
			return false;
		}

		public Integer DbPedMeioTranspLength() {
			return 20;
		}

		public Integer DbPedMeioTranspPrecision() {
			return 0;
		}

		public String DbPedMeioTranspDefault() {

			return "";

		}

		public String DbPedMeioTranspComment() {

			return "";

		}

		public String DbPedMeioTranspPattern() {

			return "";

		}

		public String DbPedMeioTranspOriginalDbColumnName() {

			return "DbPedMeioTransp";

		}

		public String DbPedLclEmbarq;

		public String getDbPedLclEmbarq() {
			return this.DbPedLclEmbarq;
		}

		public Boolean DbPedLclEmbarqIsNullable() {
			return true;
		}

		public Boolean DbPedLclEmbarqIsKey() {
			return false;
		}

		public Integer DbPedLclEmbarqLength() {
			return 12;
		}

		public Integer DbPedLclEmbarqPrecision() {
			return 0;
		}

		public String DbPedLclEmbarqDefault() {

			return null;

		}

		public String DbPedLclEmbarqComment() {

			return "";

		}

		public String DbPedLclEmbarqPattern() {

			return "";

		}

		public String DbPedLclEmbarqOriginalDbColumnName() {

			return "DbPedLclEmbarq";

		}

		public String DbPedLclDesemb;

		public String getDbPedLclDesemb() {
			return this.DbPedLclDesemb;
		}

		public Boolean DbPedLclDesembIsNullable() {
			return true;
		}

		public Boolean DbPedLclDesembIsKey() {
			return false;
		}

		public Integer DbPedLclDesembLength() {
			return 12;
		}

		public Integer DbPedLclDesembPrecision() {
			return 0;
		}

		public String DbPedLclDesembDefault() {

			return null;

		}

		public String DbPedLclDesembComment() {

			return "";

		}

		public String DbPedLclDesembPattern() {

			return "";

		}

		public String DbPedLclDesembOriginalDbColumnName() {

			return "DbPedLclDesemb";

		}

		public Double DbPedVlrFrete;

		public Double getDbPedVlrFrete() {
			return this.DbPedVlrFrete;
		}

		public Boolean DbPedVlrFreteIsNullable() {
			return true;
		}

		public Boolean DbPedVlrFreteIsKey() {
			return false;
		}

		public Integer DbPedVlrFreteLength() {
			return 15;
		}

		public Integer DbPedVlrFretePrecision() {
			return 0;
		}

		public String DbPedVlrFreteDefault() {

			return "";

		}

		public String DbPedVlrFreteComment() {

			return "";

		}

		public String DbPedVlrFretePattern() {

			return "";

		}

		public String DbPedVlrFreteOriginalDbColumnName() {

			return "DbPedVlrFrete";

		}

		public Double DbPedVlrSeguro;

		public Double getDbPedVlrSeguro() {
			return this.DbPedVlrSeguro;
		}

		public Boolean DbPedVlrSeguroIsNullable() {
			return true;
		}

		public Boolean DbPedVlrSeguroIsKey() {
			return false;
		}

		public Integer DbPedVlrSeguroLength() {
			return 15;
		}

		public Integer DbPedVlrSeguroPrecision() {
			return 0;
		}

		public String DbPedVlrSeguroDefault() {

			return "";

		}

		public String DbPedVlrSeguroComment() {

			return "";

		}

		public String DbPedVlrSeguroPattern() {

			return "";

		}

		public String DbPedVlrSeguroOriginalDbColumnName() {

			return "DbPedVlrSeguro";

		}

		public String DbPedEntEnder;

		public String getDbPedEntEnder() {
			return this.DbPedEntEnder;
		}

		public Boolean DbPedEntEnderIsNullable() {
			return true;
		}

		public Boolean DbPedEntEnderIsKey() {
			return false;
		}

		public Integer DbPedEntEnderLength() {
			return 50;
		}

		public Integer DbPedEntEnderPrecision() {
			return 0;
		}

		public String DbPedEntEnderDefault() {

			return null;

		}

		public String DbPedEntEnderComment() {

			return "";

		}

		public String DbPedEntEnderPattern() {

			return "";

		}

		public String DbPedEntEnderOriginalDbColumnName() {

			return "DbPedEntEnder";

		}

		public String DbPedEntBairro;

		public String getDbPedEntBairro() {
			return this.DbPedEntBairro;
		}

		public Boolean DbPedEntBairroIsNullable() {
			return true;
		}

		public Boolean DbPedEntBairroIsKey() {
			return false;
		}

		public Integer DbPedEntBairroLength() {
			return 40;
		}

		public Integer DbPedEntBairroPrecision() {
			return 0;
		}

		public String DbPedEntBairroDefault() {

			return null;

		}

		public String DbPedEntBairroComment() {

			return "";

		}

		public String DbPedEntBairroPattern() {

			return "";

		}

		public String DbPedEntBairroOriginalDbColumnName() {

			return "DbPedEntBairro";

		}

		public String DbPedEntCidade;

		public String getDbPedEntCidade() {
			return this.DbPedEntCidade;
		}

		public Boolean DbPedEntCidadeIsNullable() {
			return true;
		}

		public Boolean DbPedEntCidadeIsKey() {
			return false;
		}

		public Integer DbPedEntCidadeLength() {
			return 30;
		}

		public Integer DbPedEntCidadePrecision() {
			return 0;
		}

		public String DbPedEntCidadeDefault() {

			return null;

		}

		public String DbPedEntCidadeComment() {

			return "";

		}

		public String DbPedEntCidadePattern() {

			return "";

		}

		public String DbPedEntCidadeOriginalDbColumnName() {

			return "DbPedEntCidade";

		}

		public String DbPedEntCep;

		public String getDbPedEntCep() {
			return this.DbPedEntCep;
		}

		public Boolean DbPedEntCepIsNullable() {
			return true;
		}

		public Boolean DbPedEntCepIsKey() {
			return false;
		}

		public Integer DbPedEntCepLength() {
			return 9;
		}

		public Integer DbPedEntCepPrecision() {
			return 0;
		}

		public String DbPedEntCepDefault() {

			return null;

		}

		public String DbPedEntCepComment() {

			return "";

		}

		public String DbPedEntCepPattern() {

			return "";

		}

		public String DbPedEntCepOriginalDbColumnName() {

			return "DbPedEntCep";

		}

		public String DbPedEntUf;

		public String getDbPedEntUf() {
			return this.DbPedEntUf;
		}

		public Boolean DbPedEntUfIsNullable() {
			return true;
		}

		public Boolean DbPedEntUfIsKey() {
			return false;
		}

		public Integer DbPedEntUfLength() {
			return 8;
		}

		public Integer DbPedEntUfPrecision() {
			return 0;
		}

		public String DbPedEntUfDefault() {

			return null;

		}

		public String DbPedEntUfComment() {

			return "";

		}

		public String DbPedEntUfPattern() {

			return "";

		}

		public String DbPedEntUfOriginalDbColumnName() {

			return "DbPedEntUf";

		}

		public String DbPedEntCgcmf;

		public String getDbPedEntCgcmf() {
			return this.DbPedEntCgcmf;
		}

		public Boolean DbPedEntCgcmfIsNullable() {
			return true;
		}

		public Boolean DbPedEntCgcmfIsKey() {
			return false;
		}

		public Integer DbPedEntCgcmfLength() {
			return 19;
		}

		public Integer DbPedEntCgcmfPrecision() {
			return 0;
		}

		public String DbPedEntCgcmfDefault() {

			return null;

		}

		public String DbPedEntCgcmfComment() {

			return "";

		}

		public String DbPedEntCgcmfPattern() {

			return "";

		}

		public String DbPedEntCgcmfOriginalDbColumnName() {

			return "DbPedEntCgcmf";

		}

		public String DbPedEntCgcte;

		public String getDbPedEntCgcte() {
			return this.DbPedEntCgcte;
		}

		public Boolean DbPedEntCgcteIsNullable() {
			return true;
		}

		public Boolean DbPedEntCgcteIsKey() {
			return false;
		}

		public Integer DbPedEntCgcteLength() {
			return 19;
		}

		public Integer DbPedEntCgctePrecision() {
			return 0;
		}

		public String DbPedEntCgcteDefault() {

			return null;

		}

		public String DbPedEntCgcteComment() {

			return "";

		}

		public String DbPedEntCgctePattern() {

			return "";

		}

		public String DbPedEntCgcteOriginalDbColumnName() {

			return "DbPedEntCgcte";

		}

		public String DbPedEmpresa;

		public String getDbPedEmpresa() {
			return this.DbPedEmpresa;
		}

		public Boolean DbPedEmpresaIsNullable() {
			return true;
		}

		public Boolean DbPedEmpresaIsKey() {
			return false;
		}

		public Integer DbPedEmpresaLength() {
			return 3;
		}

		public Integer DbPedEmpresaPrecision() {
			return 0;
		}

		public String DbPedEmpresaDefault() {

			return null;

		}

		public String DbPedEmpresaComment() {

			return "";

		}

		public String DbPedEmpresaPattern() {

			return "";

		}

		public String DbPedEmpresaOriginalDbColumnName() {

			return "DbPedEmpresa";

		}

		public Double DbPedCdv;

		public Double getDbPedCdv() {
			return this.DbPedCdv;
		}

		public Boolean DbPedCdvIsNullable() {
			return true;
		}

		public Boolean DbPedCdvIsKey() {
			return false;
		}

		public Integer DbPedCdvLength() {
			return 15;
		}

		public Integer DbPedCdvPrecision() {
			return 0;
		}

		public String DbPedCdvDefault() {

			return "";

		}

		public String DbPedCdvComment() {

			return "";

		}

		public String DbPedCdvPattern() {

			return "";

		}

		public String DbPedCdvOriginalDbColumnName() {

			return "DbPedCdv";

		}

		public String DbPedTipo;

		public String getDbPedTipo() {
			return this.DbPedTipo;
		}

		public Boolean DbPedTipoIsNullable() {
			return true;
		}

		public Boolean DbPedTipoIsKey() {
			return false;
		}

		public Integer DbPedTipoLength() {
			return 3;
		}

		public Integer DbPedTipoPrecision() {
			return 0;
		}

		public String DbPedTipoDefault() {

			return null;

		}

		public String DbPedTipoComment() {

			return "";

		}

		public String DbPedTipoPattern() {

			return "";

		}

		public String DbPedTipoOriginalDbColumnName() {

			return "DbPedTipo";

		}

		public String DbPedPeriodo;

		public String getDbPedPeriodo() {
			return this.DbPedPeriodo;
		}

		public Boolean DbPedPeriodoIsNullable() {
			return true;
		}

		public Boolean DbPedPeriodoIsKey() {
			return false;
		}

		public Integer DbPedPeriodoLength() {
			return 10;
		}

		public Integer DbPedPeriodoPrecision() {
			return 0;
		}

		public String DbPedPeriodoDefault() {

			return null;

		}

		public String DbPedPeriodoComment() {

			return "";

		}

		public String DbPedPeriodoPattern() {

			return "";

		}

		public String DbPedPeriodoOriginalDbColumnName() {

			return "DbPedPeriodo";

		}

		public Integer DbPedPreposto;

		public Integer getDbPedPreposto() {
			return this.DbPedPreposto;
		}

		public Boolean DbPedPrepostoIsNullable() {
			return true;
		}

		public Boolean DbPedPrepostoIsKey() {
			return false;
		}

		public Integer DbPedPrepostoLength() {
			return 10;
		}

		public Integer DbPedPrepostoPrecision() {
			return 0;
		}

		public String DbPedPrepostoDefault() {

			return "";

		}

		public String DbPedPrepostoComment() {

			return "";

		}

		public String DbPedPrepostoPattern() {

			return "";

		}

		public String DbPedPrepostoOriginalDbColumnName() {

			return "DbPedPreposto";

		}

		public Integer DbPedNrpevda;

		public Integer getDbPedNrpevda() {
			return this.DbPedNrpevda;
		}

		public Boolean DbPedNrpevdaIsNullable() {
			return true;
		}

		public Boolean DbPedNrpevdaIsKey() {
			return false;
		}

		public Integer DbPedNrpevdaLength() {
			return 10;
		}

		public Integer DbPedNrpevdaPrecision() {
			return 0;
		}

		public String DbPedNrpevdaDefault() {

			return "";

		}

		public String DbPedNrpevdaComment() {

			return "";

		}

		public String DbPedNrpevdaPattern() {

			return "";

		}

		public String DbPedNrpevdaOriginalDbColumnName() {

			return "DbPedNrpevda";

		}

		public Integer DbPedSeqEntrega;

		public Integer getDbPedSeqEntrega() {
			return this.DbPedSeqEntrega;
		}

		public Boolean DbPedSeqEntregaIsNullable() {
			return true;
		}

		public Boolean DbPedSeqEntregaIsKey() {
			return false;
		}

		public Integer DbPedSeqEntregaLength() {
			return 10;
		}

		public Integer DbPedSeqEntregaPrecision() {
			return 0;
		}

		public String DbPedSeqEntregaDefault() {

			return "";

		}

		public String DbPedSeqEntregaComment() {

			return "";

		}

		public String DbPedSeqEntregaPattern() {

			return "";

		}

		public String DbPedSeqEntregaOriginalDbColumnName() {

			return "DbPedSeqEntrega";

		}

		public String DbPedRedespacho;

		public String getDbPedRedespacho() {
			return this.DbPedRedespacho;
		}

		public Boolean DbPedRedespachoIsNullable() {
			return true;
		}

		public Boolean DbPedRedespachoIsKey() {
			return false;
		}

		public Integer DbPedRedespachoLength() {
			return 100;
		}

		public Integer DbPedRedespachoPrecision() {
			return 0;
		}

		public String DbPedRedespachoDefault() {

			return null;

		}

		public String DbPedRedespachoComment() {

			return "";

		}

		public String DbPedRedespachoPattern() {

			return "";

		}

		public String DbPedRedespachoOriginalDbColumnName() {

			return "DbPedRedespacho";

		}

		public java.util.Date DbPedDtProduc;

		public java.util.Date getDbPedDtProduc() {
			return this.DbPedDtProduc;
		}

		public Boolean DbPedDtProducIsNullable() {
			return true;
		}

		public Boolean DbPedDtProducIsKey() {
			return false;
		}

		public Integer DbPedDtProducLength() {
			return 23;
		}

		public Integer DbPedDtProducPrecision() {
			return 3;
		}

		public String DbPedDtProducDefault() {

			return null;

		}

		public String DbPedDtProducComment() {

			return "";

		}

		public String DbPedDtProducPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDtProducOriginalDbColumnName() {

			return "DbPedDtProduc";

		}

		public String SituacaoCorporativa;

		public String getSituacaoCorporativa() {
			return this.SituacaoCorporativa;
		}

		public Boolean SituacaoCorporativaIsNullable() {
			return true;
		}

		public Boolean SituacaoCorporativaIsKey() {
			return false;
		}

		public Integer SituacaoCorporativaLength() {
			return 2;
		}

		public Integer SituacaoCorporativaPrecision() {
			return 0;
		}

		public String SituacaoCorporativaDefault() {

			return null;

		}

		public String SituacaoCorporativaComment() {

			return "";

		}

		public String SituacaoCorporativaPattern() {

			return "";

		}

		public String SituacaoCorporativaOriginalDbColumnName() {

			return "SituacaoCorporativa";

		}

		public String DbPedPrzpgto;

		public String getDbPedPrzpgto() {
			return this.DbPedPrzpgto;
		}

		public Boolean DbPedPrzpgtoIsNullable() {
			return true;
		}

		public Boolean DbPedPrzpgtoIsKey() {
			return false;
		}

		public Integer DbPedPrzpgtoLength() {
			return 255;
		}

		public Integer DbPedPrzpgtoPrecision() {
			return 0;
		}

		public String DbPedPrzpgtoDefault() {

			return null;

		}

		public String DbPedPrzpgtoComment() {

			return "";

		}

		public String DbPedPrzpgtoPattern() {

			return "";

		}

		public String DbPedPrzpgtoOriginalDbColumnName() {

			return "DbPedPrzpgto";

		}

		public Short DbPedForaregra;

		public Short getDbPedForaregra() {
			return this.DbPedForaregra;
		}

		public Boolean DbPedForaregraIsNullable() {
			return true;
		}

		public Boolean DbPedForaregraIsKey() {
			return false;
		}

		public Integer DbPedForaregraLength() {
			return 3;
		}

		public Integer DbPedForaregraPrecision() {
			return 0;
		}

		public String DbPedForaregraDefault() {

			return "";

		}

		public String DbPedForaregraComment() {

			return "";

		}

		public String DbPedForaregraPattern() {

			return "";

		}

		public String DbPedForaregraOriginalDbColumnName() {

			return "DbPedForaregra";

		}

		public Double DbPedDesctoVlr;

		public Double getDbPedDesctoVlr() {
			return this.DbPedDesctoVlr;
		}

		public Boolean DbPedDesctoVlrIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoVlrIsKey() {
			return false;
		}

		public Integer DbPedDesctoVlrLength() {
			return 15;
		}

		public Integer DbPedDesctoVlrPrecision() {
			return 0;
		}

		public String DbPedDesctoVlrDefault() {

			return "";

		}

		public String DbPedDesctoVlrComment() {

			return "";

		}

		public String DbPedDesctoVlrPattern() {

			return "";

		}

		public String DbPedDesctoVlrOriginalDbColumnName() {

			return "DbPedDesctoVlr";

		}

		public String DbPedEntRazao;

		public String getDbPedEntRazao() {
			return this.DbPedEntRazao;
		}

		public Boolean DbPedEntRazaoIsNullable() {
			return true;
		}

		public Boolean DbPedEntRazaoIsKey() {
			return false;
		}

		public Integer DbPedEntRazaoLength() {
			return 80;
		}

		public Integer DbPedEntRazaoPrecision() {
			return 0;
		}

		public String DbPedEntRazaoDefault() {

			return null;

		}

		public String DbPedEntRazaoComment() {

			return "";

		}

		public String DbPedEntRazaoPattern() {

			return "";

		}

		public String DbPedEntRazaoOriginalDbColumnName() {

			return "DbPedEntRazao";

		}

		public Double DbPedCotacao;

		public Double getDbPedCotacao() {
			return this.DbPedCotacao;
		}

		public Boolean DbPedCotacaoIsNullable() {
			return true;
		}

		public Boolean DbPedCotacaoIsKey() {
			return false;
		}

		public Integer DbPedCotacaoLength() {
			return 15;
		}

		public Integer DbPedCotacaoPrecision() {
			return 0;
		}

		public String DbPedCotacaoDefault() {

			return "";

		}

		public String DbPedCotacaoComment() {

			return "";

		}

		public String DbPedCotacaoPattern() {

			return "";

		}

		public String DbPedCotacaoOriginalDbColumnName() {

			return "DbPedCotacao";

		}

		public Double DbPedCliFinal;

		public Double getDbPedCliFinal() {
			return this.DbPedCliFinal;
		}

		public Boolean DbPedCliFinalIsNullable() {
			return true;
		}

		public Boolean DbPedCliFinalIsKey() {
			return false;
		}

		public Integer DbPedCliFinalLength() {
			return 15;
		}

		public Integer DbPedCliFinalPrecision() {
			return 0;
		}

		public String DbPedCliFinalDefault() {

			return "";

		}

		public String DbPedCliFinalComment() {

			return "";

		}

		public String DbPedCliFinalPattern() {

			return "";

		}

		public String DbPedCliFinalOriginalDbColumnName() {

			return "DbPedCliFinal";

		}

		public Short DbPedNUtiPBoni;

		public Short getDbPedNUtiPBoni() {
			return this.DbPedNUtiPBoni;
		}

		public Boolean DbPedNUtiPBoniIsNullable() {
			return true;
		}

		public Boolean DbPedNUtiPBoniIsKey() {
			return false;
		}

		public Integer DbPedNUtiPBoniLength() {
			return 5;
		}

		public Integer DbPedNUtiPBoniPrecision() {
			return 0;
		}

		public String DbPedNUtiPBoniDefault() {

			return "";

		}

		public String DbPedNUtiPBoniComment() {

			return "";

		}

		public String DbPedNUtiPBoniPattern() {

			return "";

		}

		public String DbPedNUtiPBoniOriginalDbColumnName() {

			return "DbPedNUtiPBoni";

		}

		public String DbPedEstrutura;

		public String getDbPedEstrutura() {
			return this.DbPedEstrutura;
		}

		public Boolean DbPedEstruturaIsNullable() {
			return true;
		}

		public Boolean DbPedEstruturaIsKey() {
			return false;
		}

		public Integer DbPedEstruturaLength() {
			return 16;
		}

		public Integer DbPedEstruturaPrecision() {
			return 0;
		}

		public String DbPedEstruturaDefault() {

			return null;

		}

		public String DbPedEstruturaComment() {

			return "";

		}

		public String DbPedEstruturaPattern() {

			return "";

		}

		public String DbPedEstruturaOriginalDbColumnName() {

			return "DbPedEstrutura";

		}

		public String DbPedEntCompl;

		public String getDbPedEntCompl() {
			return this.DbPedEntCompl;
		}

		public Boolean DbPedEntComplIsNullable() {
			return true;
		}

		public Boolean DbPedEntComplIsKey() {
			return false;
		}

		public Integer DbPedEntComplLength() {
			return 50;
		}

		public Integer DbPedEntComplPrecision() {
			return 0;
		}

		public String DbPedEntComplDefault() {

			return null;

		}

		public String DbPedEntComplComment() {

			return "";

		}

		public String DbPedEntComplPattern() {

			return "";

		}

		public String DbPedEntComplOriginalDbColumnName() {

			return "DbPedEntCompl";

		}

		public String DbPedEntPontoRefer;

		public String getDbPedEntPontoRefer() {
			return this.DbPedEntPontoRefer;
		}

		public Boolean DbPedEntPontoReferIsNullable() {
			return true;
		}

		public Boolean DbPedEntPontoReferIsKey() {
			return false;
		}

		public Integer DbPedEntPontoReferLength() {
			return 40;
		}

		public Integer DbPedEntPontoReferPrecision() {
			return 0;
		}

		public String DbPedEntPontoReferDefault() {

			return null;

		}

		public String DbPedEntPontoReferComment() {

			return "";

		}

		public String DbPedEntPontoReferPattern() {

			return "";

		}

		public String DbPedEntPontoReferOriginalDbColumnName() {

			return "DbPedEntPontoRefer";

		}

		public String DbPedEntCxPostal;

		public String getDbPedEntCxPostal() {
			return this.DbPedEntCxPostal;
		}

		public Boolean DbPedEntCxPostalIsNullable() {
			return true;
		}

		public Boolean DbPedEntCxPostalIsKey() {
			return false;
		}

		public Integer DbPedEntCxPostalLength() {
			return 10;
		}

		public Integer DbPedEntCxPostalPrecision() {
			return 0;
		}

		public String DbPedEntCxPostalDefault() {

			return "";

		}

		public String DbPedEntCxPostalComment() {

			return "";

		}

		public String DbPedEntCxPostalPattern() {

			return "";

		}

		public String DbPedEntCxPostalOriginalDbColumnName() {

			return "DbPedEntCxPostal";

		}

		public String DbPedEntPais;

		public String getDbPedEntPais() {
			return this.DbPedEntPais;
		}

		public Boolean DbPedEntPaisIsNullable() {
			return true;
		}

		public Boolean DbPedEntPaisIsKey() {
			return false;
		}

		public Integer DbPedEntPaisLength() {
			return 20;
		}

		public Integer DbPedEntPaisPrecision() {
			return 0;
		}

		public String DbPedEntPaisDefault() {

			return "";

		}

		public String DbPedEntPaisComment() {

			return "";

		}

		public String DbPedEntPaisPattern() {

			return "";

		}

		public String DbPedEntPaisOriginalDbColumnName() {

			return "DbPedEntPais";

		}

		public String DbPedEntTelefone;

		public String getDbPedEntTelefone() {
			return this.DbPedEntTelefone;
		}

		public Boolean DbPedEntTelefoneIsNullable() {
			return true;
		}

		public Boolean DbPedEntTelefoneIsKey() {
			return false;
		}

		public Integer DbPedEntTelefoneLength() {
			return 15;
		}

		public Integer DbPedEntTelefonePrecision() {
			return 0;
		}

		public String DbPedEntTelefoneDefault() {

			return "";

		}

		public String DbPedEntTelefoneComment() {

			return "";

		}

		public String DbPedEntTelefonePattern() {

			return "";

		}

		public String DbPedEntTelefoneOriginalDbColumnName() {

			return "DbPedEntTelefone";

		}

		public String DbPedEntRamal;

		public String getDbPedEntRamal() {
			return this.DbPedEntRamal;
		}

		public Boolean DbPedEntRamalIsNullable() {
			return true;
		}

		public Boolean DbPedEntRamalIsKey() {
			return false;
		}

		public Integer DbPedEntRamalLength() {
			return 5;
		}

		public Integer DbPedEntRamalPrecision() {
			return 0;
		}

		public String DbPedEntRamalDefault() {

			return "";

		}

		public String DbPedEntRamalComment() {

			return "";

		}

		public String DbPedEntRamalPattern() {

			return "";

		}

		public String DbPedEntRamalOriginalDbColumnName() {

			return "DbPedEntRamal";

		}

		public Double DbPedDesctoCampanha;

		public Double getDbPedDesctoCampanha() {
			return this.DbPedDesctoCampanha;
		}

		public Boolean DbPedDesctoCampanhaIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoCampanhaIsKey() {
			return false;
		}

		public Integer DbPedDesctoCampanhaLength() {
			return 15;
		}

		public Integer DbPedDesctoCampanhaPrecision() {
			return 0;
		}

		public String DbPedDesctoCampanhaDefault() {

			return "";

		}

		public String DbPedDesctoCampanhaComment() {

			return "";

		}

		public String DbPedDesctoCampanhaPattern() {

			return "";

		}

		public String DbPedDesctoCampanhaOriginalDbColumnName() {

			return "DbPedDesctoCampanha";

		}

		public Integer DbPedCancelando;

		public Integer getDbPedCancelando() {
			return this.DbPedCancelando;
		}

		public Boolean DbPedCancelandoIsNullable() {
			return true;
		}

		public Boolean DbPedCancelandoIsKey() {
			return false;
		}

		public Integer DbPedCancelandoLength() {
			return 10;
		}

		public Integer DbPedCancelandoPrecision() {
			return 0;
		}

		public String DbPedCancelandoDefault() {

			return "";

		}

		public String DbPedCancelandoComment() {

			return "";

		}

		public String DbPedCancelandoPattern() {

			return "";

		}

		public String DbPedCancelandoOriginalDbColumnName() {

			return "DbPedCancelando";

		}

		public java.util.Date DbPedDataHora;

		public java.util.Date getDbPedDataHora() {
			return this.DbPedDataHora;
		}

		public Boolean DbPedDataHoraIsNullable() {
			return true;
		}

		public Boolean DbPedDataHoraIsKey() {
			return false;
		}

		public Integer DbPedDataHoraLength() {
			return 23;
		}

		public Integer DbPedDataHoraPrecision() {
			return 3;
		}

		public String DbPedDataHoraDefault() {

			return "";

		}

		public String DbPedDataHoraComment() {

			return "";

		}

		public String DbPedDataHoraPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataHoraOriginalDbColumnName() {

			return "DbPedDataHora";

		}

		public Integer DbPedMotDesc;

		public Integer getDbPedMotDesc() {
			return this.DbPedMotDesc;
		}

		public Boolean DbPedMotDescIsNullable() {
			return true;
		}

		public Boolean DbPedMotDescIsKey() {
			return false;
		}

		public Integer DbPedMotDescLength() {
			return 10;
		}

		public Integer DbPedMotDescPrecision() {
			return 0;
		}

		public String DbPedMotDescDefault() {

			return "";

		}

		public String DbPedMotDescComment() {

			return "";

		}

		public String DbPedMotDescPattern() {

			return "";

		}

		public String DbPedMotDescOriginalDbColumnName() {

			return "DbPedMotDesc";

		}

		public Integer DbPedNumeroOC;

		public Integer getDbPedNumeroOC() {
			return this.DbPedNumeroOC;
		}

		public Boolean DbPedNumeroOCIsNullable() {
			return true;
		}

		public Boolean DbPedNumeroOCIsKey() {
			return false;
		}

		public Integer DbPedNumeroOCLength() {
			return 10;
		}

		public Integer DbPedNumeroOCPrecision() {
			return 0;
		}

		public String DbPedNumeroOCDefault() {

			return "";

		}

		public String DbPedNumeroOCComment() {

			return "";

		}

		public String DbPedNumeroOCPattern() {

			return "";

		}

		public String DbPedNumeroOCOriginalDbColumnName() {

			return "DbPedNumeroOC";

		}

		public java.util.Date DbPedDtRegraCorte;

		public java.util.Date getDbPedDtRegraCorte() {
			return this.DbPedDtRegraCorte;
		}

		public Boolean DbPedDtRegraCorteIsNullable() {
			return true;
		}

		public Boolean DbPedDtRegraCorteIsKey() {
			return false;
		}

		public Integer DbPedDtRegraCorteLength() {
			return 23;
		}

		public Integer DbPedDtRegraCortePrecision() {
			return 3;
		}

		public String DbPedDtRegraCorteDefault() {

			return "";

		}

		public String DbPedDtRegraCorteComment() {

			return "";

		}

		public String DbPedDtRegraCortePattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDtRegraCorteOriginalDbColumnName() {

			return "DbPedDtRegraCorte";

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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.ListaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.Operacao = readString(dis);

					this.DbPedPortador = readInteger(dis);

					this.DbPedCodMoeda = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFino = null;
					} else {
						this.DbPedTabFino = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindo = null;
					} else {
						this.DbPedTabFindo = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFini = null;
					} else {
						this.DbPedTabFini = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindi = null;
					} else {
						this.DbPedTabFindi = dis.readShort();
					}

					this.Representante = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComiso = null;
					} else {
						this.DbPedComiso = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComisi = null;
					} else {
						this.DbPedComisi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedPercFrete = null;
					} else {
						this.DbPedPercFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodTransp = null;
					} else {
						this.DbPedCodTransp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodRedesp = null;
					} else {
						this.DbPedCodRedesp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoFrete = null;
					} else {
						this.DbPedTipoFrete = dis.readShort();
					}

					this.DbPedOrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto = null;
					} else {
						this.DbPedDescto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto2 = null;
					} else {
						this.DbPedDescto2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto3 = null;
					} else {
						this.DbPedDescto3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto4 = null;
					} else {
						this.DbPedDescto4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto5 = null;
					} else {
						this.DbPedDescto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto6 = null;
					} else {
						this.DbPedDescto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto7 = null;
					} else {
						this.DbPedDescto7 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedAcrescimo = null;
					} else {
						this.DbPedAcrescimo = dis.readDouble();
					}

					this.DbPedMotivoBloq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					this.DbPedLibUsus = readString(dis);

					this.DbPedLibMots = readString(dis);

					this.DbPedLibDatas = readString(dis);

					this.DbPedDataAlter = readDate(dis);

					this.DbPedDataEnvio = readDate(dis);

					this.DbPedObserv = readString(dis);

					this.DbPedTexto = readString(dis);

					this.DbPedNroOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoPreco = null;
					} else {
						this.DbPedTipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedFatur = null;
					} else {
						this.DbPedFatur = dis.readShort();
					}

					this.DbPedDataReceb = readDate(dis);

					this.DbPedNroRe = readString(dis);

					this.DbPedEndPgto = readInteger(dis);

					this.DbPedMeioTransp = readString(dis);

					this.DbPedLclEmbarq = readString(dis);

					this.DbPedLclDesemb = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrFrete = null;
					} else {
						this.DbPedVlrFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrSeguro = null;
					} else {
						this.DbPedVlrSeguro = dis.readDouble();
					}

					this.DbPedEntEnder = readString(dis);

					this.DbPedEntBairro = readString(dis);

					this.DbPedEntCidade = readString(dis);

					this.DbPedEntCep = readString(dis);

					this.DbPedEntUf = readString(dis);

					this.DbPedEntCgcmf = readString(dis);

					this.DbPedEntCgcte = readString(dis);

					this.DbPedEmpresa = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCdv = null;
					} else {
						this.DbPedCdv = dis.readDouble();
					}

					this.DbPedTipo = readString(dis);

					this.DbPedPeriodo = readString(dis);

					this.DbPedPreposto = readInteger(dis);

					this.DbPedNrpevda = readInteger(dis);

					this.DbPedSeqEntrega = readInteger(dis);

					this.DbPedRedespacho = readString(dis);

					this.DbPedDtProduc = readDate(dis);

					this.SituacaoCorporativa = readString(dis);

					this.DbPedPrzpgto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedForaregra = null;
					} else {
						this.DbPedForaregra = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoVlr = null;
					} else {
						this.DbPedDesctoVlr = dis.readDouble();
					}

					this.DbPedEntRazao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCotacao = null;
					} else {
						this.DbPedCotacao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCliFinal = null;
					} else {
						this.DbPedCliFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedNUtiPBoni = null;
					} else {
						this.DbPedNUtiPBoni = dis.readShort();
					}

					this.DbPedEstrutura = readString(dis);

					this.DbPedEntCompl = readString(dis);

					this.DbPedEntPontoRefer = readString(dis);

					this.DbPedEntCxPostal = readString(dis);

					this.DbPedEntPais = readString(dis);

					this.DbPedEntTelefone = readString(dis);

					this.DbPedEntRamal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoCampanha = null;
					} else {
						this.DbPedDesctoCampanha = dis.readDouble();
					}

					this.DbPedCancelando = readInteger(dis);

					this.DbPedDataHora = readDate(dis);

					this.DbPedMotDesc = readInteger(dis);

					this.DbPedNumeroOC = readInteger(dis);

					this.DbPedDtRegraCorte = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.ListaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.Operacao = readString(dis);

					this.DbPedPortador = readInteger(dis);

					this.DbPedCodMoeda = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFino = null;
					} else {
						this.DbPedTabFino = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindo = null;
					} else {
						this.DbPedTabFindo = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFini = null;
					} else {
						this.DbPedTabFini = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindi = null;
					} else {
						this.DbPedTabFindi = dis.readShort();
					}

					this.Representante = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComiso = null;
					} else {
						this.DbPedComiso = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComisi = null;
					} else {
						this.DbPedComisi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedPercFrete = null;
					} else {
						this.DbPedPercFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodTransp = null;
					} else {
						this.DbPedCodTransp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodRedesp = null;
					} else {
						this.DbPedCodRedesp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoFrete = null;
					} else {
						this.DbPedTipoFrete = dis.readShort();
					}

					this.DbPedOrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto = null;
					} else {
						this.DbPedDescto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto2 = null;
					} else {
						this.DbPedDescto2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto3 = null;
					} else {
						this.DbPedDescto3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto4 = null;
					} else {
						this.DbPedDescto4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto5 = null;
					} else {
						this.DbPedDescto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto6 = null;
					} else {
						this.DbPedDescto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto7 = null;
					} else {
						this.DbPedDescto7 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedAcrescimo = null;
					} else {
						this.DbPedAcrescimo = dis.readDouble();
					}

					this.DbPedMotivoBloq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					this.DbPedLibUsus = readString(dis);

					this.DbPedLibMots = readString(dis);

					this.DbPedLibDatas = readString(dis);

					this.DbPedDataAlter = readDate(dis);

					this.DbPedDataEnvio = readDate(dis);

					this.DbPedObserv = readString(dis);

					this.DbPedTexto = readString(dis);

					this.DbPedNroOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoPreco = null;
					} else {
						this.DbPedTipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedFatur = null;
					} else {
						this.DbPedFatur = dis.readShort();
					}

					this.DbPedDataReceb = readDate(dis);

					this.DbPedNroRe = readString(dis);

					this.DbPedEndPgto = readInteger(dis);

					this.DbPedMeioTransp = readString(dis);

					this.DbPedLclEmbarq = readString(dis);

					this.DbPedLclDesemb = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrFrete = null;
					} else {
						this.DbPedVlrFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrSeguro = null;
					} else {
						this.DbPedVlrSeguro = dis.readDouble();
					}

					this.DbPedEntEnder = readString(dis);

					this.DbPedEntBairro = readString(dis);

					this.DbPedEntCidade = readString(dis);

					this.DbPedEntCep = readString(dis);

					this.DbPedEntUf = readString(dis);

					this.DbPedEntCgcmf = readString(dis);

					this.DbPedEntCgcte = readString(dis);

					this.DbPedEmpresa = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCdv = null;
					} else {
						this.DbPedCdv = dis.readDouble();
					}

					this.DbPedTipo = readString(dis);

					this.DbPedPeriodo = readString(dis);

					this.DbPedPreposto = readInteger(dis);

					this.DbPedNrpevda = readInteger(dis);

					this.DbPedSeqEntrega = readInteger(dis);

					this.DbPedRedespacho = readString(dis);

					this.DbPedDtProduc = readDate(dis);

					this.SituacaoCorporativa = readString(dis);

					this.DbPedPrzpgto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedForaregra = null;
					} else {
						this.DbPedForaregra = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoVlr = null;
					} else {
						this.DbPedDesctoVlr = dis.readDouble();
					}

					this.DbPedEntRazao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCotacao = null;
					} else {
						this.DbPedCotacao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCliFinal = null;
					} else {
						this.DbPedCliFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedNUtiPBoni = null;
					} else {
						this.DbPedNUtiPBoni = dis.readShort();
					}

					this.DbPedEstrutura = readString(dis);

					this.DbPedEntCompl = readString(dis);

					this.DbPedEntPontoRefer = readString(dis);

					this.DbPedEntCxPostal = readString(dis);

					this.DbPedEntPais = readString(dis);

					this.DbPedEntTelefone = readString(dis);

					this.DbPedEntRamal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoCampanha = null;
					} else {
						this.DbPedDesctoCampanha = dis.readDouble();
					}

					this.DbPedCancelando = readInteger(dis);

					this.DbPedDataHora = readDate(dis);

					this.DbPedMotDesc = readInteger(dis);

					this.DbPedNumeroOC = readInteger(dis);

					this.DbPedDtRegraCorte = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.ListaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.Operacao, dos);

				// Integer

				writeInteger(this.DbPedPortador, dos);

				// Integer

				writeInteger(this.DbPedCodMoeda, dos);

				// Short

				if (this.DbPedTabFino == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFino);
				}

				// Short

				if (this.DbPedTabFindo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindo);
				}

				// Short

				if (this.DbPedTabFini == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFini);
				}

				// Short

				if (this.DbPedTabFindi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindi);
				}

				// Integer

				writeInteger(this.Representante, dos);

				// Double

				if (this.DbPedComiso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComiso);
				}

				// Double

				if (this.DbPedComisi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComisi);
				}

				// Double

				if (this.DbPedPercFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedPercFrete);
				}

				// Double

				if (this.DbPedCodTransp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodTransp);
				}

				// Double

				if (this.DbPedCodRedesp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodRedesp);
				}

				// Short

				if (this.DbPedTipoFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoFrete);
				}

				// String

				writeString(this.DbPedOrdCompra, dos);

				// Double

				if (this.DbPedDescto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto);
				}

				// Double

				if (this.DbPedDescto2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto2);
				}

				// Double

				if (this.DbPedDescto3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto3);
				}

				// Double

				if (this.DbPedDescto4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto4);
				}

				// Double

				if (this.DbPedDescto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto5);
				}

				// Double

				if (this.DbPedDescto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto6);
				}

				// Double

				if (this.DbPedDescto7 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto7);
				}

				// Double

				if (this.DbPedAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedAcrescimo);
				}

				// String

				writeString(this.DbPedMotivoBloq, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// String

				writeString(this.DbPedLibUsus, dos);

				// String

				writeString(this.DbPedLibMots, dos);

				// String

				writeString(this.DbPedLibDatas, dos);

				// java.util.Date

				writeDate(this.DbPedDataAlter, dos);

				// java.util.Date

				writeDate(this.DbPedDataEnvio, dos);

				// String

				writeString(this.DbPedObserv, dos);

				// String

				writeString(this.DbPedTexto, dos);

				// String

				writeString(this.DbPedNroOrig, dos);

				// Short

				if (this.DbPedTipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoPreco);
				}

				// Short

				if (this.DbPedFatur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedFatur);
				}

				// java.util.Date

				writeDate(this.DbPedDataReceb, dos);

				// String

				writeString(this.DbPedNroRe, dos);

				// Integer

				writeInteger(this.DbPedEndPgto, dos);

				// String

				writeString(this.DbPedMeioTransp, dos);

				// String

				writeString(this.DbPedLclEmbarq, dos);

				// String

				writeString(this.DbPedLclDesemb, dos);

				// Double

				if (this.DbPedVlrFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrFrete);
				}

				// Double

				if (this.DbPedVlrSeguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrSeguro);
				}

				// String

				writeString(this.DbPedEntEnder, dos);

				// String

				writeString(this.DbPedEntBairro, dos);

				// String

				writeString(this.DbPedEntCidade, dos);

				// String

				writeString(this.DbPedEntCep, dos);

				// String

				writeString(this.DbPedEntUf, dos);

				// String

				writeString(this.DbPedEntCgcmf, dos);

				// String

				writeString(this.DbPedEntCgcte, dos);

				// String

				writeString(this.DbPedEmpresa, dos);

				// Double

				if (this.DbPedCdv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCdv);
				}

				// String

				writeString(this.DbPedTipo, dos);

				// String

				writeString(this.DbPedPeriodo, dos);

				// Integer

				writeInteger(this.DbPedPreposto, dos);

				// Integer

				writeInteger(this.DbPedNrpevda, dos);

				// Integer

				writeInteger(this.DbPedSeqEntrega, dos);

				// String

				writeString(this.DbPedRedespacho, dos);

				// java.util.Date

				writeDate(this.DbPedDtProduc, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

				// String

				writeString(this.DbPedPrzpgto, dos);

				// Short

				if (this.DbPedForaregra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedForaregra);
				}

				// Double

				if (this.DbPedDesctoVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoVlr);
				}

				// String

				writeString(this.DbPedEntRazao, dos);

				// Double

				if (this.DbPedCotacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCotacao);
				}

				// Double

				if (this.DbPedCliFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCliFinal);
				}

				// Short

				if (this.DbPedNUtiPBoni == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedNUtiPBoni);
				}

				// String

				writeString(this.DbPedEstrutura, dos);

				// String

				writeString(this.DbPedEntCompl, dos);

				// String

				writeString(this.DbPedEntPontoRefer, dos);

				// String

				writeString(this.DbPedEntCxPostal, dos);

				// String

				writeString(this.DbPedEntPais, dos);

				// String

				writeString(this.DbPedEntTelefone, dos);

				// String

				writeString(this.DbPedEntRamal, dos);

				// Double

				if (this.DbPedDesctoCampanha == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoCampanha);
				}

				// Integer

				writeInteger(this.DbPedCancelando, dos);

				// java.util.Date

				writeDate(this.DbPedDataHora, dos);

				// Integer

				writeInteger(this.DbPedMotDesc, dos);

				// Integer

				writeInteger(this.DbPedNumeroOC, dos);

				// java.util.Date

				writeDate(this.DbPedDtRegraCorte, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.ListaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.Operacao, dos);

				// Integer

				writeInteger(this.DbPedPortador, dos);

				// Integer

				writeInteger(this.DbPedCodMoeda, dos);

				// Short

				if (this.DbPedTabFino == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFino);
				}

				// Short

				if (this.DbPedTabFindo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindo);
				}

				// Short

				if (this.DbPedTabFini == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFini);
				}

				// Short

				if (this.DbPedTabFindi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindi);
				}

				// Integer

				writeInteger(this.Representante, dos);

				// Double

				if (this.DbPedComiso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComiso);
				}

				// Double

				if (this.DbPedComisi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComisi);
				}

				// Double

				if (this.DbPedPercFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedPercFrete);
				}

				// Double

				if (this.DbPedCodTransp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodTransp);
				}

				// Double

				if (this.DbPedCodRedesp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodRedesp);
				}

				// Short

				if (this.DbPedTipoFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoFrete);
				}

				// String

				writeString(this.DbPedOrdCompra, dos);

				// Double

				if (this.DbPedDescto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto);
				}

				// Double

				if (this.DbPedDescto2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto2);
				}

				// Double

				if (this.DbPedDescto3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto3);
				}

				// Double

				if (this.DbPedDescto4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto4);
				}

				// Double

				if (this.DbPedDescto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto5);
				}

				// Double

				if (this.DbPedDescto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto6);
				}

				// Double

				if (this.DbPedDescto7 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto7);
				}

				// Double

				if (this.DbPedAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedAcrescimo);
				}

				// String

				writeString(this.DbPedMotivoBloq, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// String

				writeString(this.DbPedLibUsus, dos);

				// String

				writeString(this.DbPedLibMots, dos);

				// String

				writeString(this.DbPedLibDatas, dos);

				// java.util.Date

				writeDate(this.DbPedDataAlter, dos);

				// java.util.Date

				writeDate(this.DbPedDataEnvio, dos);

				// String

				writeString(this.DbPedObserv, dos);

				// String

				writeString(this.DbPedTexto, dos);

				// String

				writeString(this.DbPedNroOrig, dos);

				// Short

				if (this.DbPedTipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoPreco);
				}

				// Short

				if (this.DbPedFatur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedFatur);
				}

				// java.util.Date

				writeDate(this.DbPedDataReceb, dos);

				// String

				writeString(this.DbPedNroRe, dos);

				// Integer

				writeInteger(this.DbPedEndPgto, dos);

				// String

				writeString(this.DbPedMeioTransp, dos);

				// String

				writeString(this.DbPedLclEmbarq, dos);

				// String

				writeString(this.DbPedLclDesemb, dos);

				// Double

				if (this.DbPedVlrFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrFrete);
				}

				// Double

				if (this.DbPedVlrSeguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrSeguro);
				}

				// String

				writeString(this.DbPedEntEnder, dos);

				// String

				writeString(this.DbPedEntBairro, dos);

				// String

				writeString(this.DbPedEntCidade, dos);

				// String

				writeString(this.DbPedEntCep, dos);

				// String

				writeString(this.DbPedEntUf, dos);

				// String

				writeString(this.DbPedEntCgcmf, dos);

				// String

				writeString(this.DbPedEntCgcte, dos);

				// String

				writeString(this.DbPedEmpresa, dos);

				// Double

				if (this.DbPedCdv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCdv);
				}

				// String

				writeString(this.DbPedTipo, dos);

				// String

				writeString(this.DbPedPeriodo, dos);

				// Integer

				writeInteger(this.DbPedPreposto, dos);

				// Integer

				writeInteger(this.DbPedNrpevda, dos);

				// Integer

				writeInteger(this.DbPedSeqEntrega, dos);

				// String

				writeString(this.DbPedRedespacho, dos);

				// java.util.Date

				writeDate(this.DbPedDtProduc, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

				// String

				writeString(this.DbPedPrzpgto, dos);

				// Short

				if (this.DbPedForaregra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedForaregra);
				}

				// Double

				if (this.DbPedDesctoVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoVlr);
				}

				// String

				writeString(this.DbPedEntRazao, dos);

				// Double

				if (this.DbPedCotacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCotacao);
				}

				// Double

				if (this.DbPedCliFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCliFinal);
				}

				// Short

				if (this.DbPedNUtiPBoni == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedNUtiPBoni);
				}

				// String

				writeString(this.DbPedEstrutura, dos);

				// String

				writeString(this.DbPedEntCompl, dos);

				// String

				writeString(this.DbPedEntPontoRefer, dos);

				// String

				writeString(this.DbPedEntCxPostal, dos);

				// String

				writeString(this.DbPedEntPais, dos);

				// String

				writeString(this.DbPedEntTelefone, dos);

				// String

				writeString(this.DbPedEntRamal, dos);

				// Double

				if (this.DbPedDesctoCampanha == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoCampanha);
				}

				// Integer

				writeInteger(this.DbPedCancelando, dos);

				// java.util.Date

				writeDate(this.DbPedDataHora, dos);

				// Integer

				writeInteger(this.DbPedMotDesc, dos);

				// Integer

				writeInteger(this.DbPedNumeroOC, dos);

				// java.util.Date

				writeDate(this.DbPedDtRegraCorte, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",CodigoCliente=" + String.valueOf(CodigoCliente));
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",PrevisaoEntrega=" + String.valueOf(PrevisaoEntrega));
			sb.append(",ListaPreco=" + ListaPreco);
			sb.append(",CondicaoPagamento=" + String.valueOf(CondicaoPagamento));
			sb.append(",Operacao=" + Operacao);
			sb.append(",DbPedPortador=" + String.valueOf(DbPedPortador));
			sb.append(",DbPedCodMoeda=" + String.valueOf(DbPedCodMoeda));
			sb.append(",DbPedTabFino=" + String.valueOf(DbPedTabFino));
			sb.append(",DbPedTabFindo=" + String.valueOf(DbPedTabFindo));
			sb.append(",DbPedTabFini=" + String.valueOf(DbPedTabFini));
			sb.append(",DbPedTabFindi=" + String.valueOf(DbPedTabFindi));
			sb.append(",Representante=" + String.valueOf(Representante));
			sb.append(",DbPedComiso=" + String.valueOf(DbPedComiso));
			sb.append(",DbPedComisi=" + String.valueOf(DbPedComisi));
			sb.append(",DbPedPercFrete=" + String.valueOf(DbPedPercFrete));
			sb.append(",DbPedCodTransp=" + String.valueOf(DbPedCodTransp));
			sb.append(",DbPedCodRedesp=" + String.valueOf(DbPedCodRedesp));
			sb.append(",DbPedTipoFrete=" + String.valueOf(DbPedTipoFrete));
			sb.append(",DbPedOrdCompra=" + DbPedOrdCompra);
			sb.append(",DbPedDescto=" + String.valueOf(DbPedDescto));
			sb.append(",DbPedDescto2=" + String.valueOf(DbPedDescto2));
			sb.append(",DbPedDescto3=" + String.valueOf(DbPedDescto3));
			sb.append(",DbPedDescto4=" + String.valueOf(DbPedDescto4));
			sb.append(",DbPedDescto5=" + String.valueOf(DbPedDescto5));
			sb.append(",DbPedDescto6=" + String.valueOf(DbPedDescto6));
			sb.append(",DbPedDescto7=" + String.valueOf(DbPedDescto7));
			sb.append(",DbPedAcrescimo=" + String.valueOf(DbPedAcrescimo));
			sb.append(",DbPedMotivoBloq=" + DbPedMotivoBloq);
			sb.append(",SituacaoPedido=" + String.valueOf(SituacaoPedido));
			sb.append(",DbPedLibUsus=" + DbPedLibUsus);
			sb.append(",DbPedLibMots=" + DbPedLibMots);
			sb.append(",DbPedLibDatas=" + DbPedLibDatas);
			sb.append(",DbPedDataAlter=" + String.valueOf(DbPedDataAlter));
			sb.append(",DbPedDataEnvio=" + String.valueOf(DbPedDataEnvio));
			sb.append(",DbPedObserv=" + DbPedObserv);
			sb.append(",DbPedTexto=" + DbPedTexto);
			sb.append(",DbPedNroOrig=" + DbPedNroOrig);
			sb.append(",DbPedTipoPreco=" + String.valueOf(DbPedTipoPreco));
			sb.append(",DbPedFatur=" + String.valueOf(DbPedFatur));
			sb.append(",DbPedDataReceb=" + String.valueOf(DbPedDataReceb));
			sb.append(",DbPedNroRe=" + DbPedNroRe);
			sb.append(",DbPedEndPgto=" + String.valueOf(DbPedEndPgto));
			sb.append(",DbPedMeioTransp=" + DbPedMeioTransp);
			sb.append(",DbPedLclEmbarq=" + DbPedLclEmbarq);
			sb.append(",DbPedLclDesemb=" + DbPedLclDesemb);
			sb.append(",DbPedVlrFrete=" + String.valueOf(DbPedVlrFrete));
			sb.append(",DbPedVlrSeguro=" + String.valueOf(DbPedVlrSeguro));
			sb.append(",DbPedEntEnder=" + DbPedEntEnder);
			sb.append(",DbPedEntBairro=" + DbPedEntBairro);
			sb.append(",DbPedEntCidade=" + DbPedEntCidade);
			sb.append(",DbPedEntCep=" + DbPedEntCep);
			sb.append(",DbPedEntUf=" + DbPedEntUf);
			sb.append(",DbPedEntCgcmf=" + DbPedEntCgcmf);
			sb.append(",DbPedEntCgcte=" + DbPedEntCgcte);
			sb.append(",DbPedEmpresa=" + DbPedEmpresa);
			sb.append(",DbPedCdv=" + String.valueOf(DbPedCdv));
			sb.append(",DbPedTipo=" + DbPedTipo);
			sb.append(",DbPedPeriodo=" + DbPedPeriodo);
			sb.append(",DbPedPreposto=" + String.valueOf(DbPedPreposto));
			sb.append(",DbPedNrpevda=" + String.valueOf(DbPedNrpevda));
			sb.append(",DbPedSeqEntrega=" + String.valueOf(DbPedSeqEntrega));
			sb.append(",DbPedRedespacho=" + DbPedRedespacho);
			sb.append(",DbPedDtProduc=" + String.valueOf(DbPedDtProduc));
			sb.append(",SituacaoCorporativa=" + SituacaoCorporativa);
			sb.append(",DbPedPrzpgto=" + DbPedPrzpgto);
			sb.append(",DbPedForaregra=" + String.valueOf(DbPedForaregra));
			sb.append(",DbPedDesctoVlr=" + String.valueOf(DbPedDesctoVlr));
			sb.append(",DbPedEntRazao=" + DbPedEntRazao);
			sb.append(",DbPedCotacao=" + String.valueOf(DbPedCotacao));
			sb.append(",DbPedCliFinal=" + String.valueOf(DbPedCliFinal));
			sb.append(",DbPedNUtiPBoni=" + String.valueOf(DbPedNUtiPBoni));
			sb.append(",DbPedEstrutura=" + DbPedEstrutura);
			sb.append(",DbPedEntCompl=" + DbPedEntCompl);
			sb.append(",DbPedEntPontoRefer=" + DbPedEntPontoRefer);
			sb.append(",DbPedEntCxPostal=" + DbPedEntCxPostal);
			sb.append(",DbPedEntPais=" + DbPedEntPais);
			sb.append(",DbPedEntTelefone=" + DbPedEntTelefone);
			sb.append(",DbPedEntRamal=" + DbPedEntRamal);
			sb.append(",DbPedDesctoCampanha=" + String.valueOf(DbPedDesctoCampanha));
			sb.append(",DbPedCancelando=" + String.valueOf(DbPedCancelando));
			sb.append(",DbPedDataHora=" + String.valueOf(DbPedDataHora));
			sb.append(",DbPedMotDesc=" + String.valueOf(DbPedMotDesc));
			sb.append(",DbPedNumeroOC=" + String.valueOf(DbPedNumeroOC));
			sb.append(",DbPedDtRegraCorte=" + String.valueOf(DbPedDtRegraCorte));
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

			if (CodigoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCliente);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (PrevisaoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevisaoEntrega);
			}

			sb.append("|");

			if (ListaPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(ListaPreco);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			if (DbPedPortador == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPortador);
			}

			sb.append("|");

			if (DbPedCodMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodMoeda);
			}

			sb.append("|");

			if (DbPedTabFino == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFino);
			}

			sb.append("|");

			if (DbPedTabFindo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFindo);
			}

			sb.append("|");

			if (DbPedTabFini == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFini);
			}

			sb.append("|");

			if (DbPedTabFindi == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFindi);
			}

			sb.append("|");

			if (Representante == null) {
				sb.append("<null>");
			} else {
				sb.append(Representante);
			}

			sb.append("|");

			if (DbPedComiso == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedComiso);
			}

			sb.append("|");

			if (DbPedComisi == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedComisi);
			}

			sb.append("|");

			if (DbPedPercFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPercFrete);
			}

			sb.append("|");

			if (DbPedCodTransp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodTransp);
			}

			sb.append("|");

			if (DbPedCodRedesp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodRedesp);
			}

			sb.append("|");

			if (DbPedTipoFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipoFrete);
			}

			sb.append("|");

			if (DbPedOrdCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedOrdCompra);
			}

			sb.append("|");

			if (DbPedDescto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto);
			}

			sb.append("|");

			if (DbPedDescto2 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto2);
			}

			sb.append("|");

			if (DbPedDescto3 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto3);
			}

			sb.append("|");

			if (DbPedDescto4 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto4);
			}

			sb.append("|");

			if (DbPedDescto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto5);
			}

			sb.append("|");

			if (DbPedDescto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto6);
			}

			sb.append("|");

			if (DbPedDescto7 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto7);
			}

			sb.append("|");

			if (DbPedAcrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedAcrescimo);
			}

			sb.append("|");

			if (DbPedMotivoBloq == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMotivoBloq);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (DbPedLibUsus == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibUsus);
			}

			sb.append("|");

			if (DbPedLibMots == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibMots);
			}

			sb.append("|");

			if (DbPedLibDatas == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibDatas);
			}

			sb.append("|");

			if (DbPedDataAlter == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataAlter);
			}

			sb.append("|");

			if (DbPedDataEnvio == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataEnvio);
			}

			sb.append("|");

			if (DbPedObserv == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedObserv);
			}

			sb.append("|");

			if (DbPedTexto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTexto);
			}

			sb.append("|");

			if (DbPedNroOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNroOrig);
			}

			sb.append("|");

			if (DbPedTipoPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipoPreco);
			}

			sb.append("|");

			if (DbPedFatur == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedFatur);
			}

			sb.append("|");

			if (DbPedDataReceb == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataReceb);
			}

			sb.append("|");

			if (DbPedNroRe == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNroRe);
			}

			sb.append("|");

			if (DbPedEndPgto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEndPgto);
			}

			sb.append("|");

			if (DbPedMeioTransp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMeioTransp);
			}

			sb.append("|");

			if (DbPedLclEmbarq == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLclEmbarq);
			}

			sb.append("|");

			if (DbPedLclDesemb == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLclDesemb);
			}

			sb.append("|");

			if (DbPedVlrFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedVlrFrete);
			}

			sb.append("|");

			if (DbPedVlrSeguro == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedVlrSeguro);
			}

			sb.append("|");

			if (DbPedEntEnder == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntEnder);
			}

			sb.append("|");

			if (DbPedEntBairro == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntBairro);
			}

			sb.append("|");

			if (DbPedEntCidade == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCidade);
			}

			sb.append("|");

			if (DbPedEntCep == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCep);
			}

			sb.append("|");

			if (DbPedEntUf == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntUf);
			}

			sb.append("|");

			if (DbPedEntCgcmf == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCgcmf);
			}

			sb.append("|");

			if (DbPedEntCgcte == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCgcte);
			}

			sb.append("|");

			if (DbPedEmpresa == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEmpresa);
			}

			sb.append("|");

			if (DbPedCdv == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCdv);
			}

			sb.append("|");

			if (DbPedTipo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipo);
			}

			sb.append("|");

			if (DbPedPeriodo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPeriodo);
			}

			sb.append("|");

			if (DbPedPreposto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPreposto);
			}

			sb.append("|");

			if (DbPedNrpevda == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNrpevda);
			}

			sb.append("|");

			if (DbPedSeqEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedSeqEntrega);
			}

			sb.append("|");

			if (DbPedRedespacho == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedRedespacho);
			}

			sb.append("|");

			if (DbPedDtProduc == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDtProduc);
			}

			sb.append("|");

			if (SituacaoCorporativa == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoCorporativa);
			}

			sb.append("|");

			if (DbPedPrzpgto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPrzpgto);
			}

			sb.append("|");

			if (DbPedForaregra == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedForaregra);
			}

			sb.append("|");

			if (DbPedDesctoVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDesctoVlr);
			}

			sb.append("|");

			if (DbPedEntRazao == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntRazao);
			}

			sb.append("|");

			if (DbPedCotacao == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCotacao);
			}

			sb.append("|");

			if (DbPedCliFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCliFinal);
			}

			sb.append("|");

			if (DbPedNUtiPBoni == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNUtiPBoni);
			}

			sb.append("|");

			if (DbPedEstrutura == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEstrutura);
			}

			sb.append("|");

			if (DbPedEntCompl == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCompl);
			}

			sb.append("|");

			if (DbPedEntPontoRefer == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntPontoRefer);
			}

			sb.append("|");

			if (DbPedEntCxPostal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCxPostal);
			}

			sb.append("|");

			if (DbPedEntPais == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntPais);
			}

			sb.append("|");

			if (DbPedEntTelefone == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntTelefone);
			}

			sb.append("|");

			if (DbPedEntRamal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntRamal);
			}

			sb.append("|");

			if (DbPedDesctoCampanha == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDesctoCampanha);
			}

			sb.append("|");

			if (DbPedCancelando == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCancelando);
			}

			sb.append("|");

			if (DbPedDataHora == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataHora);
			}

			sb.append("|");

			if (DbPedMotDesc == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMotDesc);
			}

			sb.append("|");

			if (DbPedNumeroOC == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNumeroOC);
			}

			sb.append("|");

			if (DbPedDtRegraCorte == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDtRegraCorte);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Capa_pedidoStruct other) {

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

	public static class after_tDBInput_2Struct implements routines.system.IPersistableRow<after_tDBInput_2Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
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

		public Double CodigoCliente;

		public Double getCodigoCliente() {
			return this.CodigoCliente;
		}

		public Boolean CodigoClienteIsNullable() {
			return true;
		}

		public Boolean CodigoClienteIsKey() {
			return false;
		}

		public Integer CodigoClienteLength() {
			return 15;
		}

		public Integer CodigoClientePrecision() {
			return 0;
		}

		public String CodigoClienteDefault() {

			return "";

		}

		public String CodigoClienteComment() {

			return "";

		}

		public String CodigoClientePattern() {

			return "";

		}

		public String CodigoClienteOriginalDbColumnName() {

			return "CodigoCliente";

		}

		public java.util.Date DataEmissao;

		public java.util.Date getDataEmissao() {
			return this.DataEmissao;
		}

		public Boolean DataEmissaoIsNullable() {
			return true;
		}

		public Boolean DataEmissaoIsKey() {
			return false;
		}

		public Integer DataEmissaoLength() {
			return 23;
		}

		public Integer DataEmissaoPrecision() {
			return 3;
		}

		public String DataEmissaoDefault() {

			return null;

		}

		public String DataEmissaoComment() {

			return "";

		}

		public String DataEmissaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataEmissaoOriginalDbColumnName() {

			return "DataEmissao";

		}

		public java.util.Date PrevisaoEntrega;

		public java.util.Date getPrevisaoEntrega() {
			return this.PrevisaoEntrega;
		}

		public Boolean PrevisaoEntregaIsNullable() {
			return true;
		}

		public Boolean PrevisaoEntregaIsKey() {
			return false;
		}

		public Integer PrevisaoEntregaLength() {
			return 23;
		}

		public Integer PrevisaoEntregaPrecision() {
			return 3;
		}

		public String PrevisaoEntregaDefault() {

			return null;

		}

		public String PrevisaoEntregaComment() {

			return "";

		}

		public String PrevisaoEntregaPattern() {

			return "dd-MM-yyyy";

		}

		public String PrevisaoEntregaOriginalDbColumnName() {

			return "PrevisaoEntrega";

		}

		public String ListaPreco;

		public String getListaPreco() {
			return this.ListaPreco;
		}

		public Boolean ListaPrecoIsNullable() {
			return true;
		}

		public Boolean ListaPrecoIsKey() {
			return false;
		}

		public Integer ListaPrecoLength() {
			return 8;
		}

		public Integer ListaPrecoPrecision() {
			return 0;
		}

		public String ListaPrecoDefault() {

			return null;

		}

		public String ListaPrecoComment() {

			return "";

		}

		public String ListaPrecoPattern() {

			return "";

		}

		public String ListaPrecoOriginalDbColumnName() {

			return "ListaPreco";

		}

		public Integer CondicaoPagamento;

		public Integer getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 10;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return "";

		}

		public String CondicaoPagamentoComment() {

			return "";

		}

		public String CondicaoPagamentoPattern() {

			return "";

		}

		public String CondicaoPagamentoOriginalDbColumnName() {

			return "CondicaoPagamento";

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

		public Integer DbPedPortador;

		public Integer getDbPedPortador() {
			return this.DbPedPortador;
		}

		public Boolean DbPedPortadorIsNullable() {
			return true;
		}

		public Boolean DbPedPortadorIsKey() {
			return false;
		}

		public Integer DbPedPortadorLength() {
			return 10;
		}

		public Integer DbPedPortadorPrecision() {
			return 0;
		}

		public String DbPedPortadorDefault() {

			return "";

		}

		public String DbPedPortadorComment() {

			return "";

		}

		public String DbPedPortadorPattern() {

			return "";

		}

		public String DbPedPortadorOriginalDbColumnName() {

			return "DbPedPortador";

		}

		public Integer DbPedCodMoeda;

		public Integer getDbPedCodMoeda() {
			return this.DbPedCodMoeda;
		}

		public Boolean DbPedCodMoedaIsNullable() {
			return true;
		}

		public Boolean DbPedCodMoedaIsKey() {
			return false;
		}

		public Integer DbPedCodMoedaLength() {
			return 10;
		}

		public Integer DbPedCodMoedaPrecision() {
			return 0;
		}

		public String DbPedCodMoedaDefault() {

			return "";

		}

		public String DbPedCodMoedaComment() {

			return "";

		}

		public String DbPedCodMoedaPattern() {

			return "";

		}

		public String DbPedCodMoedaOriginalDbColumnName() {

			return "DbPedCodMoeda";

		}

		public Short DbPedTabFino;

		public Short getDbPedTabFino() {
			return this.DbPedTabFino;
		}

		public Boolean DbPedTabFinoIsNullable() {
			return true;
		}

		public Boolean DbPedTabFinoIsKey() {
			return false;
		}

		public Integer DbPedTabFinoLength() {
			return 5;
		}

		public Integer DbPedTabFinoPrecision() {
			return 0;
		}

		public String DbPedTabFinoDefault() {

			return "";

		}

		public String DbPedTabFinoComment() {

			return "";

		}

		public String DbPedTabFinoPattern() {

			return "";

		}

		public String DbPedTabFinoOriginalDbColumnName() {

			return "DbPedTabFino";

		}

		public Short DbPedTabFindo;

		public Short getDbPedTabFindo() {
			return this.DbPedTabFindo;
		}

		public Boolean DbPedTabFindoIsNullable() {
			return true;
		}

		public Boolean DbPedTabFindoIsKey() {
			return false;
		}

		public Integer DbPedTabFindoLength() {
			return 5;
		}

		public Integer DbPedTabFindoPrecision() {
			return 0;
		}

		public String DbPedTabFindoDefault() {

			return "";

		}

		public String DbPedTabFindoComment() {

			return "";

		}

		public String DbPedTabFindoPattern() {

			return "";

		}

		public String DbPedTabFindoOriginalDbColumnName() {

			return "DbPedTabFindo";

		}

		public Short DbPedTabFini;

		public Short getDbPedTabFini() {
			return this.DbPedTabFini;
		}

		public Boolean DbPedTabFiniIsNullable() {
			return true;
		}

		public Boolean DbPedTabFiniIsKey() {
			return false;
		}

		public Integer DbPedTabFiniLength() {
			return 5;
		}

		public Integer DbPedTabFiniPrecision() {
			return 0;
		}

		public String DbPedTabFiniDefault() {

			return "";

		}

		public String DbPedTabFiniComment() {

			return "";

		}

		public String DbPedTabFiniPattern() {

			return "";

		}

		public String DbPedTabFiniOriginalDbColumnName() {

			return "DbPedTabFini";

		}

		public Short DbPedTabFindi;

		public Short getDbPedTabFindi() {
			return this.DbPedTabFindi;
		}

		public Boolean DbPedTabFindiIsNullable() {
			return true;
		}

		public Boolean DbPedTabFindiIsKey() {
			return false;
		}

		public Integer DbPedTabFindiLength() {
			return 5;
		}

		public Integer DbPedTabFindiPrecision() {
			return 0;
		}

		public String DbPedTabFindiDefault() {

			return "";

		}

		public String DbPedTabFindiComment() {

			return "";

		}

		public String DbPedTabFindiPattern() {

			return "";

		}

		public String DbPedTabFindiOriginalDbColumnName() {

			return "DbPedTabFindi";

		}

		public Integer Representante;

		public Integer getRepresentante() {
			return this.Representante;
		}

		public Boolean RepresentanteIsNullable() {
			return true;
		}

		public Boolean RepresentanteIsKey() {
			return false;
		}

		public Integer RepresentanteLength() {
			return 10;
		}

		public Integer RepresentantePrecision() {
			return 0;
		}

		public String RepresentanteDefault() {

			return "";

		}

		public String RepresentanteComment() {

			return "";

		}

		public String RepresentantePattern() {

			return "";

		}

		public String RepresentanteOriginalDbColumnName() {

			return "Representante";

		}

		public Double DbPedComiso;

		public Double getDbPedComiso() {
			return this.DbPedComiso;
		}

		public Boolean DbPedComisoIsNullable() {
			return true;
		}

		public Boolean DbPedComisoIsKey() {
			return false;
		}

		public Integer DbPedComisoLength() {
			return 15;
		}

		public Integer DbPedComisoPrecision() {
			return 0;
		}

		public String DbPedComisoDefault() {

			return "";

		}

		public String DbPedComisoComment() {

			return "";

		}

		public String DbPedComisoPattern() {

			return "";

		}

		public String DbPedComisoOriginalDbColumnName() {

			return "DbPedComiso";

		}

		public Double DbPedComisi;

		public Double getDbPedComisi() {
			return this.DbPedComisi;
		}

		public Boolean DbPedComisiIsNullable() {
			return true;
		}

		public Boolean DbPedComisiIsKey() {
			return false;
		}

		public Integer DbPedComisiLength() {
			return 15;
		}

		public Integer DbPedComisiPrecision() {
			return 0;
		}

		public String DbPedComisiDefault() {

			return "";

		}

		public String DbPedComisiComment() {

			return "";

		}

		public String DbPedComisiPattern() {

			return "";

		}

		public String DbPedComisiOriginalDbColumnName() {

			return "DbPedComisi";

		}

		public Double DbPedPercFrete;

		public Double getDbPedPercFrete() {
			return this.DbPedPercFrete;
		}

		public Boolean DbPedPercFreteIsNullable() {
			return true;
		}

		public Boolean DbPedPercFreteIsKey() {
			return false;
		}

		public Integer DbPedPercFreteLength() {
			return 15;
		}

		public Integer DbPedPercFretePrecision() {
			return 0;
		}

		public String DbPedPercFreteDefault() {

			return "";

		}

		public String DbPedPercFreteComment() {

			return "";

		}

		public String DbPedPercFretePattern() {

			return "";

		}

		public String DbPedPercFreteOriginalDbColumnName() {

			return "DbPedPercFrete";

		}

		public Double DbPedCodTransp;

		public Double getDbPedCodTransp() {
			return this.DbPedCodTransp;
		}

		public Boolean DbPedCodTranspIsNullable() {
			return true;
		}

		public Boolean DbPedCodTranspIsKey() {
			return false;
		}

		public Integer DbPedCodTranspLength() {
			return 15;
		}

		public Integer DbPedCodTranspPrecision() {
			return 0;
		}

		public String DbPedCodTranspDefault() {

			return "";

		}

		public String DbPedCodTranspComment() {

			return "";

		}

		public String DbPedCodTranspPattern() {

			return "";

		}

		public String DbPedCodTranspOriginalDbColumnName() {

			return "DbPedCodTransp";

		}

		public Double DbPedCodRedesp;

		public Double getDbPedCodRedesp() {
			return this.DbPedCodRedesp;
		}

		public Boolean DbPedCodRedespIsNullable() {
			return true;
		}

		public Boolean DbPedCodRedespIsKey() {
			return false;
		}

		public Integer DbPedCodRedespLength() {
			return 15;
		}

		public Integer DbPedCodRedespPrecision() {
			return 0;
		}

		public String DbPedCodRedespDefault() {

			return "";

		}

		public String DbPedCodRedespComment() {

			return "";

		}

		public String DbPedCodRedespPattern() {

			return "";

		}

		public String DbPedCodRedespOriginalDbColumnName() {

			return "DbPedCodRedesp";

		}

		public Short DbPedTipoFrete;

		public Short getDbPedTipoFrete() {
			return this.DbPedTipoFrete;
		}

		public Boolean DbPedTipoFreteIsNullable() {
			return true;
		}

		public Boolean DbPedTipoFreteIsKey() {
			return false;
		}

		public Integer DbPedTipoFreteLength() {
			return 5;
		}

		public Integer DbPedTipoFretePrecision() {
			return 0;
		}

		public String DbPedTipoFreteDefault() {

			return "";

		}

		public String DbPedTipoFreteComment() {

			return "";

		}

		public String DbPedTipoFretePattern() {

			return "";

		}

		public String DbPedTipoFreteOriginalDbColumnName() {

			return "DbPedTipoFrete";

		}

		public String DbPedOrdCompra;

		public String getDbPedOrdCompra() {
			return this.DbPedOrdCompra;
		}

		public Boolean DbPedOrdCompraIsNullable() {
			return true;
		}

		public Boolean DbPedOrdCompraIsKey() {
			return false;
		}

		public Integer DbPedOrdCompraLength() {
			return 25;
		}

		public Integer DbPedOrdCompraPrecision() {
			return 0;
		}

		public String DbPedOrdCompraDefault() {

			return "";

		}

		public String DbPedOrdCompraComment() {

			return "";

		}

		public String DbPedOrdCompraPattern() {

			return "";

		}

		public String DbPedOrdCompraOriginalDbColumnName() {

			return "DbPedOrdCompra";

		}

		public Double DbPedDescto;

		public Double getDbPedDescto() {
			return this.DbPedDescto;
		}

		public Boolean DbPedDesctoIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoIsKey() {
			return false;
		}

		public Integer DbPedDesctoLength() {
			return 15;
		}

		public Integer DbPedDesctoPrecision() {
			return 0;
		}

		public String DbPedDesctoDefault() {

			return "";

		}

		public String DbPedDesctoComment() {

			return "";

		}

		public String DbPedDesctoPattern() {

			return "";

		}

		public String DbPedDesctoOriginalDbColumnName() {

			return "DbPedDescto";

		}

		public Double DbPedDescto2;

		public Double getDbPedDescto2() {
			return this.DbPedDescto2;
		}

		public Boolean DbPedDescto2IsNullable() {
			return true;
		}

		public Boolean DbPedDescto2IsKey() {
			return false;
		}

		public Integer DbPedDescto2Length() {
			return 15;
		}

		public Integer DbPedDescto2Precision() {
			return 0;
		}

		public String DbPedDescto2Default() {

			return "";

		}

		public String DbPedDescto2Comment() {

			return "";

		}

		public String DbPedDescto2Pattern() {

			return "";

		}

		public String DbPedDescto2OriginalDbColumnName() {

			return "DbPedDescto2";

		}

		public Double DbPedDescto3;

		public Double getDbPedDescto3() {
			return this.DbPedDescto3;
		}

		public Boolean DbPedDescto3IsNullable() {
			return true;
		}

		public Boolean DbPedDescto3IsKey() {
			return false;
		}

		public Integer DbPedDescto3Length() {
			return 15;
		}

		public Integer DbPedDescto3Precision() {
			return 0;
		}

		public String DbPedDescto3Default() {

			return "";

		}

		public String DbPedDescto3Comment() {

			return "";

		}

		public String DbPedDescto3Pattern() {

			return "";

		}

		public String DbPedDescto3OriginalDbColumnName() {

			return "DbPedDescto3";

		}

		public Double DbPedDescto4;

		public Double getDbPedDescto4() {
			return this.DbPedDescto4;
		}

		public Boolean DbPedDescto4IsNullable() {
			return true;
		}

		public Boolean DbPedDescto4IsKey() {
			return false;
		}

		public Integer DbPedDescto4Length() {
			return 15;
		}

		public Integer DbPedDescto4Precision() {
			return 0;
		}

		public String DbPedDescto4Default() {

			return "";

		}

		public String DbPedDescto4Comment() {

			return "";

		}

		public String DbPedDescto4Pattern() {

			return "";

		}

		public String DbPedDescto4OriginalDbColumnName() {

			return "DbPedDescto4";

		}

		public Double DbPedDescto5;

		public Double getDbPedDescto5() {
			return this.DbPedDescto5;
		}

		public Boolean DbPedDescto5IsNullable() {
			return true;
		}

		public Boolean DbPedDescto5IsKey() {
			return false;
		}

		public Integer DbPedDescto5Length() {
			return 15;
		}

		public Integer DbPedDescto5Precision() {
			return 0;
		}

		public String DbPedDescto5Default() {

			return "";

		}

		public String DbPedDescto5Comment() {

			return "";

		}

		public String DbPedDescto5Pattern() {

			return "";

		}

		public String DbPedDescto5OriginalDbColumnName() {

			return "DbPedDescto5";

		}

		public Double DbPedDescto6;

		public Double getDbPedDescto6() {
			return this.DbPedDescto6;
		}

		public Boolean DbPedDescto6IsNullable() {
			return true;
		}

		public Boolean DbPedDescto6IsKey() {
			return false;
		}

		public Integer DbPedDescto6Length() {
			return 15;
		}

		public Integer DbPedDescto6Precision() {
			return 0;
		}

		public String DbPedDescto6Default() {

			return "";

		}

		public String DbPedDescto6Comment() {

			return "";

		}

		public String DbPedDescto6Pattern() {

			return "";

		}

		public String DbPedDescto6OriginalDbColumnName() {

			return "DbPedDescto6";

		}

		public Double DbPedDescto7;

		public Double getDbPedDescto7() {
			return this.DbPedDescto7;
		}

		public Boolean DbPedDescto7IsNullable() {
			return true;
		}

		public Boolean DbPedDescto7IsKey() {
			return false;
		}

		public Integer DbPedDescto7Length() {
			return 15;
		}

		public Integer DbPedDescto7Precision() {
			return 0;
		}

		public String DbPedDescto7Default() {

			return "";

		}

		public String DbPedDescto7Comment() {

			return "";

		}

		public String DbPedDescto7Pattern() {

			return "";

		}

		public String DbPedDescto7OriginalDbColumnName() {

			return "DbPedDescto7";

		}

		public Double DbPedAcrescimo;

		public Double getDbPedAcrescimo() {
			return this.DbPedAcrescimo;
		}

		public Boolean DbPedAcrescimoIsNullable() {
			return true;
		}

		public Boolean DbPedAcrescimoIsKey() {
			return false;
		}

		public Integer DbPedAcrescimoLength() {
			return 15;
		}

		public Integer DbPedAcrescimoPrecision() {
			return 0;
		}

		public String DbPedAcrescimoDefault() {

			return "";

		}

		public String DbPedAcrescimoComment() {

			return "";

		}

		public String DbPedAcrescimoPattern() {

			return "";

		}

		public String DbPedAcrescimoOriginalDbColumnName() {

			return "DbPedAcrescimo";

		}

		public String DbPedMotivoBloq;

		public String getDbPedMotivoBloq() {
			return this.DbPedMotivoBloq;
		}

		public Boolean DbPedMotivoBloqIsNullable() {
			return true;
		}

		public Boolean DbPedMotivoBloqIsKey() {
			return false;
		}

		public Integer DbPedMotivoBloqLength() {
			return 15;
		}

		public Integer DbPedMotivoBloqPrecision() {
			return 0;
		}

		public String DbPedMotivoBloqDefault() {

			return null;

		}

		public String DbPedMotivoBloqComment() {

			return "";

		}

		public String DbPedMotivoBloqPattern() {

			return "";

		}

		public String DbPedMotivoBloqOriginalDbColumnName() {

			return "DbPedMotivoBloq";

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
			return 3;
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

		public String DbPedLibUsus;

		public String getDbPedLibUsus() {
			return this.DbPedLibUsus;
		}

		public Boolean DbPedLibUsusIsNullable() {
			return true;
		}

		public Boolean DbPedLibUsusIsKey() {
			return false;
		}

		public Integer DbPedLibUsusLength() {
			return 255;
		}

		public Integer DbPedLibUsusPrecision() {
			return 0;
		}

		public String DbPedLibUsusDefault() {

			return null;

		}

		public String DbPedLibUsusComment() {

			return "";

		}

		public String DbPedLibUsusPattern() {

			return "";

		}

		public String DbPedLibUsusOriginalDbColumnName() {

			return "DbPedLibUsus";

		}

		public String DbPedLibMots;

		public String getDbPedLibMots() {
			return this.DbPedLibMots;
		}

		public Boolean DbPedLibMotsIsNullable() {
			return true;
		}

		public Boolean DbPedLibMotsIsKey() {
			return false;
		}

		public Integer DbPedLibMotsLength() {
			return 255;
		}

		public Integer DbPedLibMotsPrecision() {
			return 0;
		}

		public String DbPedLibMotsDefault() {

			return null;

		}

		public String DbPedLibMotsComment() {

			return "";

		}

		public String DbPedLibMotsPattern() {

			return "";

		}

		public String DbPedLibMotsOriginalDbColumnName() {

			return "DbPedLibMots";

		}

		public String DbPedLibDatas;

		public String getDbPedLibDatas() {
			return this.DbPedLibDatas;
		}

		public Boolean DbPedLibDatasIsNullable() {
			return true;
		}

		public Boolean DbPedLibDatasIsKey() {
			return false;
		}

		public Integer DbPedLibDatasLength() {
			return 255;
		}

		public Integer DbPedLibDatasPrecision() {
			return 0;
		}

		public String DbPedLibDatasDefault() {

			return null;

		}

		public String DbPedLibDatasComment() {

			return "";

		}

		public String DbPedLibDatasPattern() {

			return "";

		}

		public String DbPedLibDatasOriginalDbColumnName() {

			return "DbPedLibDatas";

		}

		public java.util.Date DbPedDataAlter;

		public java.util.Date getDbPedDataAlter() {
			return this.DbPedDataAlter;
		}

		public Boolean DbPedDataAlterIsNullable() {
			return true;
		}

		public Boolean DbPedDataAlterIsKey() {
			return false;
		}

		public Integer DbPedDataAlterLength() {
			return 23;
		}

		public Integer DbPedDataAlterPrecision() {
			return 3;
		}

		public String DbPedDataAlterDefault() {

			return null;

		}

		public String DbPedDataAlterComment() {

			return "";

		}

		public String DbPedDataAlterPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataAlterOriginalDbColumnName() {

			return "DbPedDataAlter";

		}

		public java.util.Date DbPedDataEnvio;

		public java.util.Date getDbPedDataEnvio() {
			return this.DbPedDataEnvio;
		}

		public Boolean DbPedDataEnvioIsNullable() {
			return true;
		}

		public Boolean DbPedDataEnvioIsKey() {
			return false;
		}

		public Integer DbPedDataEnvioLength() {
			return 23;
		}

		public Integer DbPedDataEnvioPrecision() {
			return 3;
		}

		public String DbPedDataEnvioDefault() {

			return null;

		}

		public String DbPedDataEnvioComment() {

			return "";

		}

		public String DbPedDataEnvioPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataEnvioOriginalDbColumnName() {

			return "DbPedDataEnvio";

		}

		public String DbPedObserv;

		public String getDbPedObserv() {
			return this.DbPedObserv;
		}

		public Boolean DbPedObservIsNullable() {
			return true;
		}

		public Boolean DbPedObservIsKey() {
			return false;
		}

		public Integer DbPedObservLength() {
			return 255;
		}

		public Integer DbPedObservPrecision() {
			return 0;
		}

		public String DbPedObservDefault() {

			return null;

		}

		public String DbPedObservComment() {

			return "";

		}

		public String DbPedObservPattern() {

			return "";

		}

		public String DbPedObservOriginalDbColumnName() {

			return "DbPedObserv";

		}

		public String DbPedTexto;

		public String getDbPedTexto() {
			return this.DbPedTexto;
		}

		public Boolean DbPedTextoIsNullable() {
			return true;
		}

		public Boolean DbPedTextoIsKey() {
			return false;
		}

		public Integer DbPedTextoLength() {
			return 255;
		}

		public Integer DbPedTextoPrecision() {
			return 0;
		}

		public String DbPedTextoDefault() {

			return null;

		}

		public String DbPedTextoComment() {

			return "";

		}

		public String DbPedTextoPattern() {

			return "";

		}

		public String DbPedTextoOriginalDbColumnName() {

			return "DbPedTexto";

		}

		public String DbPedNroOrig;

		public String getDbPedNroOrig() {
			return this.DbPedNroOrig;
		}

		public Boolean DbPedNroOrigIsNullable() {
			return true;
		}

		public Boolean DbPedNroOrigIsKey() {
			return false;
		}

		public Integer DbPedNroOrigLength() {
			return 10;
		}

		public Integer DbPedNroOrigPrecision() {
			return 0;
		}

		public String DbPedNroOrigDefault() {

			return null;

		}

		public String DbPedNroOrigComment() {

			return "";

		}

		public String DbPedNroOrigPattern() {

			return "";

		}

		public String DbPedNroOrigOriginalDbColumnName() {

			return "DbPedNroOrig";

		}

		public Short DbPedTipoPreco;

		public Short getDbPedTipoPreco() {
			return this.DbPedTipoPreco;
		}

		public Boolean DbPedTipoPrecoIsNullable() {
			return true;
		}

		public Boolean DbPedTipoPrecoIsKey() {
			return false;
		}

		public Integer DbPedTipoPrecoLength() {
			return 5;
		}

		public Integer DbPedTipoPrecoPrecision() {
			return 0;
		}

		public String DbPedTipoPrecoDefault() {

			return "";

		}

		public String DbPedTipoPrecoComment() {

			return "";

		}

		public String DbPedTipoPrecoPattern() {

			return "";

		}

		public String DbPedTipoPrecoOriginalDbColumnName() {

			return "DbPedTipoPreco";

		}

		public Short DbPedFatur;

		public Short getDbPedFatur() {
			return this.DbPedFatur;
		}

		public Boolean DbPedFaturIsNullable() {
			return true;
		}

		public Boolean DbPedFaturIsKey() {
			return false;
		}

		public Integer DbPedFaturLength() {
			return 3;
		}

		public Integer DbPedFaturPrecision() {
			return 0;
		}

		public String DbPedFaturDefault() {

			return "";

		}

		public String DbPedFaturComment() {

			return "";

		}

		public String DbPedFaturPattern() {

			return "";

		}

		public String DbPedFaturOriginalDbColumnName() {

			return "DbPedFatur";

		}

		public java.util.Date DbPedDataReceb;

		public java.util.Date getDbPedDataReceb() {
			return this.DbPedDataReceb;
		}

		public Boolean DbPedDataRecebIsNullable() {
			return true;
		}

		public Boolean DbPedDataRecebIsKey() {
			return false;
		}

		public Integer DbPedDataRecebLength() {
			return 23;
		}

		public Integer DbPedDataRecebPrecision() {
			return 3;
		}

		public String DbPedDataRecebDefault() {

			return null;

		}

		public String DbPedDataRecebComment() {

			return "";

		}

		public String DbPedDataRecebPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataRecebOriginalDbColumnName() {

			return "DbPedDataReceb";

		}

		public String DbPedNroRe;

		public String getDbPedNroRe() {
			return this.DbPedNroRe;
		}

		public Boolean DbPedNroReIsNullable() {
			return true;
		}

		public Boolean DbPedNroReIsKey() {
			return false;
		}

		public Integer DbPedNroReLength() {
			return 25;
		}

		public Integer DbPedNroRePrecision() {
			return 0;
		}

		public String DbPedNroReDefault() {

			return null;

		}

		public String DbPedNroReComment() {

			return "";

		}

		public String DbPedNroRePattern() {

			return "";

		}

		public String DbPedNroReOriginalDbColumnName() {

			return "DbPedNroRe";

		}

		public Integer DbPedEndPgto;

		public Integer getDbPedEndPgto() {
			return this.DbPedEndPgto;
		}

		public Boolean DbPedEndPgtoIsNullable() {
			return true;
		}

		public Boolean DbPedEndPgtoIsKey() {
			return false;
		}

		public Integer DbPedEndPgtoLength() {
			return 10;
		}

		public Integer DbPedEndPgtoPrecision() {
			return 0;
		}

		public String DbPedEndPgtoDefault() {

			return "";

		}

		public String DbPedEndPgtoComment() {

			return "";

		}

		public String DbPedEndPgtoPattern() {

			return "";

		}

		public String DbPedEndPgtoOriginalDbColumnName() {

			return "DbPedEndPgto";

		}

		public String DbPedMeioTransp;

		public String getDbPedMeioTransp() {
			return this.DbPedMeioTransp;
		}

		public Boolean DbPedMeioTranspIsNullable() {
			return true;
		}

		public Boolean DbPedMeioTranspIsKey() {
			return false;
		}

		public Integer DbPedMeioTranspLength() {
			return 20;
		}

		public Integer DbPedMeioTranspPrecision() {
			return 0;
		}

		public String DbPedMeioTranspDefault() {

			return "";

		}

		public String DbPedMeioTranspComment() {

			return "";

		}

		public String DbPedMeioTranspPattern() {

			return "";

		}

		public String DbPedMeioTranspOriginalDbColumnName() {

			return "DbPedMeioTransp";

		}

		public String DbPedLclEmbarq;

		public String getDbPedLclEmbarq() {
			return this.DbPedLclEmbarq;
		}

		public Boolean DbPedLclEmbarqIsNullable() {
			return true;
		}

		public Boolean DbPedLclEmbarqIsKey() {
			return false;
		}

		public Integer DbPedLclEmbarqLength() {
			return 12;
		}

		public Integer DbPedLclEmbarqPrecision() {
			return 0;
		}

		public String DbPedLclEmbarqDefault() {

			return null;

		}

		public String DbPedLclEmbarqComment() {

			return "";

		}

		public String DbPedLclEmbarqPattern() {

			return "";

		}

		public String DbPedLclEmbarqOriginalDbColumnName() {

			return "DbPedLclEmbarq";

		}

		public String DbPedLclDesemb;

		public String getDbPedLclDesemb() {
			return this.DbPedLclDesemb;
		}

		public Boolean DbPedLclDesembIsNullable() {
			return true;
		}

		public Boolean DbPedLclDesembIsKey() {
			return false;
		}

		public Integer DbPedLclDesembLength() {
			return 12;
		}

		public Integer DbPedLclDesembPrecision() {
			return 0;
		}

		public String DbPedLclDesembDefault() {

			return null;

		}

		public String DbPedLclDesembComment() {

			return "";

		}

		public String DbPedLclDesembPattern() {

			return "";

		}

		public String DbPedLclDesembOriginalDbColumnName() {

			return "DbPedLclDesemb";

		}

		public Double DbPedVlrFrete;

		public Double getDbPedVlrFrete() {
			return this.DbPedVlrFrete;
		}

		public Boolean DbPedVlrFreteIsNullable() {
			return true;
		}

		public Boolean DbPedVlrFreteIsKey() {
			return false;
		}

		public Integer DbPedVlrFreteLength() {
			return 15;
		}

		public Integer DbPedVlrFretePrecision() {
			return 0;
		}

		public String DbPedVlrFreteDefault() {

			return "";

		}

		public String DbPedVlrFreteComment() {

			return "";

		}

		public String DbPedVlrFretePattern() {

			return "";

		}

		public String DbPedVlrFreteOriginalDbColumnName() {

			return "DbPedVlrFrete";

		}

		public Double DbPedVlrSeguro;

		public Double getDbPedVlrSeguro() {
			return this.DbPedVlrSeguro;
		}

		public Boolean DbPedVlrSeguroIsNullable() {
			return true;
		}

		public Boolean DbPedVlrSeguroIsKey() {
			return false;
		}

		public Integer DbPedVlrSeguroLength() {
			return 15;
		}

		public Integer DbPedVlrSeguroPrecision() {
			return 0;
		}

		public String DbPedVlrSeguroDefault() {

			return "";

		}

		public String DbPedVlrSeguroComment() {

			return "";

		}

		public String DbPedVlrSeguroPattern() {

			return "";

		}

		public String DbPedVlrSeguroOriginalDbColumnName() {

			return "DbPedVlrSeguro";

		}

		public String DbPedEntEnder;

		public String getDbPedEntEnder() {
			return this.DbPedEntEnder;
		}

		public Boolean DbPedEntEnderIsNullable() {
			return true;
		}

		public Boolean DbPedEntEnderIsKey() {
			return false;
		}

		public Integer DbPedEntEnderLength() {
			return 50;
		}

		public Integer DbPedEntEnderPrecision() {
			return 0;
		}

		public String DbPedEntEnderDefault() {

			return null;

		}

		public String DbPedEntEnderComment() {

			return "";

		}

		public String DbPedEntEnderPattern() {

			return "";

		}

		public String DbPedEntEnderOriginalDbColumnName() {

			return "DbPedEntEnder";

		}

		public String DbPedEntBairro;

		public String getDbPedEntBairro() {
			return this.DbPedEntBairro;
		}

		public Boolean DbPedEntBairroIsNullable() {
			return true;
		}

		public Boolean DbPedEntBairroIsKey() {
			return false;
		}

		public Integer DbPedEntBairroLength() {
			return 40;
		}

		public Integer DbPedEntBairroPrecision() {
			return 0;
		}

		public String DbPedEntBairroDefault() {

			return null;

		}

		public String DbPedEntBairroComment() {

			return "";

		}

		public String DbPedEntBairroPattern() {

			return "";

		}

		public String DbPedEntBairroOriginalDbColumnName() {

			return "DbPedEntBairro";

		}

		public String DbPedEntCidade;

		public String getDbPedEntCidade() {
			return this.DbPedEntCidade;
		}

		public Boolean DbPedEntCidadeIsNullable() {
			return true;
		}

		public Boolean DbPedEntCidadeIsKey() {
			return false;
		}

		public Integer DbPedEntCidadeLength() {
			return 30;
		}

		public Integer DbPedEntCidadePrecision() {
			return 0;
		}

		public String DbPedEntCidadeDefault() {

			return null;

		}

		public String DbPedEntCidadeComment() {

			return "";

		}

		public String DbPedEntCidadePattern() {

			return "";

		}

		public String DbPedEntCidadeOriginalDbColumnName() {

			return "DbPedEntCidade";

		}

		public String DbPedEntCep;

		public String getDbPedEntCep() {
			return this.DbPedEntCep;
		}

		public Boolean DbPedEntCepIsNullable() {
			return true;
		}

		public Boolean DbPedEntCepIsKey() {
			return false;
		}

		public Integer DbPedEntCepLength() {
			return 9;
		}

		public Integer DbPedEntCepPrecision() {
			return 0;
		}

		public String DbPedEntCepDefault() {

			return null;

		}

		public String DbPedEntCepComment() {

			return "";

		}

		public String DbPedEntCepPattern() {

			return "";

		}

		public String DbPedEntCepOriginalDbColumnName() {

			return "DbPedEntCep";

		}

		public String DbPedEntUf;

		public String getDbPedEntUf() {
			return this.DbPedEntUf;
		}

		public Boolean DbPedEntUfIsNullable() {
			return true;
		}

		public Boolean DbPedEntUfIsKey() {
			return false;
		}

		public Integer DbPedEntUfLength() {
			return 8;
		}

		public Integer DbPedEntUfPrecision() {
			return 0;
		}

		public String DbPedEntUfDefault() {

			return null;

		}

		public String DbPedEntUfComment() {

			return "";

		}

		public String DbPedEntUfPattern() {

			return "";

		}

		public String DbPedEntUfOriginalDbColumnName() {

			return "DbPedEntUf";

		}

		public String DbPedEntCgcmf;

		public String getDbPedEntCgcmf() {
			return this.DbPedEntCgcmf;
		}

		public Boolean DbPedEntCgcmfIsNullable() {
			return true;
		}

		public Boolean DbPedEntCgcmfIsKey() {
			return false;
		}

		public Integer DbPedEntCgcmfLength() {
			return 19;
		}

		public Integer DbPedEntCgcmfPrecision() {
			return 0;
		}

		public String DbPedEntCgcmfDefault() {

			return null;

		}

		public String DbPedEntCgcmfComment() {

			return "";

		}

		public String DbPedEntCgcmfPattern() {

			return "";

		}

		public String DbPedEntCgcmfOriginalDbColumnName() {

			return "DbPedEntCgcmf";

		}

		public String DbPedEntCgcte;

		public String getDbPedEntCgcte() {
			return this.DbPedEntCgcte;
		}

		public Boolean DbPedEntCgcteIsNullable() {
			return true;
		}

		public Boolean DbPedEntCgcteIsKey() {
			return false;
		}

		public Integer DbPedEntCgcteLength() {
			return 19;
		}

		public Integer DbPedEntCgctePrecision() {
			return 0;
		}

		public String DbPedEntCgcteDefault() {

			return null;

		}

		public String DbPedEntCgcteComment() {

			return "";

		}

		public String DbPedEntCgctePattern() {

			return "";

		}

		public String DbPedEntCgcteOriginalDbColumnName() {

			return "DbPedEntCgcte";

		}

		public String DbPedEmpresa;

		public String getDbPedEmpresa() {
			return this.DbPedEmpresa;
		}

		public Boolean DbPedEmpresaIsNullable() {
			return true;
		}

		public Boolean DbPedEmpresaIsKey() {
			return false;
		}

		public Integer DbPedEmpresaLength() {
			return 3;
		}

		public Integer DbPedEmpresaPrecision() {
			return 0;
		}

		public String DbPedEmpresaDefault() {

			return null;

		}

		public String DbPedEmpresaComment() {

			return "";

		}

		public String DbPedEmpresaPattern() {

			return "";

		}

		public String DbPedEmpresaOriginalDbColumnName() {

			return "DbPedEmpresa";

		}

		public Double DbPedCdv;

		public Double getDbPedCdv() {
			return this.DbPedCdv;
		}

		public Boolean DbPedCdvIsNullable() {
			return true;
		}

		public Boolean DbPedCdvIsKey() {
			return false;
		}

		public Integer DbPedCdvLength() {
			return 15;
		}

		public Integer DbPedCdvPrecision() {
			return 0;
		}

		public String DbPedCdvDefault() {

			return "";

		}

		public String DbPedCdvComment() {

			return "";

		}

		public String DbPedCdvPattern() {

			return "";

		}

		public String DbPedCdvOriginalDbColumnName() {

			return "DbPedCdv";

		}

		public String DbPedTipo;

		public String getDbPedTipo() {
			return this.DbPedTipo;
		}

		public Boolean DbPedTipoIsNullable() {
			return true;
		}

		public Boolean DbPedTipoIsKey() {
			return false;
		}

		public Integer DbPedTipoLength() {
			return 3;
		}

		public Integer DbPedTipoPrecision() {
			return 0;
		}

		public String DbPedTipoDefault() {

			return null;

		}

		public String DbPedTipoComment() {

			return "";

		}

		public String DbPedTipoPattern() {

			return "";

		}

		public String DbPedTipoOriginalDbColumnName() {

			return "DbPedTipo";

		}

		public String DbPedPeriodo;

		public String getDbPedPeriodo() {
			return this.DbPedPeriodo;
		}

		public Boolean DbPedPeriodoIsNullable() {
			return true;
		}

		public Boolean DbPedPeriodoIsKey() {
			return false;
		}

		public Integer DbPedPeriodoLength() {
			return 10;
		}

		public Integer DbPedPeriodoPrecision() {
			return 0;
		}

		public String DbPedPeriodoDefault() {

			return null;

		}

		public String DbPedPeriodoComment() {

			return "";

		}

		public String DbPedPeriodoPattern() {

			return "";

		}

		public String DbPedPeriodoOriginalDbColumnName() {

			return "DbPedPeriodo";

		}

		public Integer DbPedPreposto;

		public Integer getDbPedPreposto() {
			return this.DbPedPreposto;
		}

		public Boolean DbPedPrepostoIsNullable() {
			return true;
		}

		public Boolean DbPedPrepostoIsKey() {
			return false;
		}

		public Integer DbPedPrepostoLength() {
			return 10;
		}

		public Integer DbPedPrepostoPrecision() {
			return 0;
		}

		public String DbPedPrepostoDefault() {

			return "";

		}

		public String DbPedPrepostoComment() {

			return "";

		}

		public String DbPedPrepostoPattern() {

			return "";

		}

		public String DbPedPrepostoOriginalDbColumnName() {

			return "DbPedPreposto";

		}

		public Integer DbPedNrpevda;

		public Integer getDbPedNrpevda() {
			return this.DbPedNrpevda;
		}

		public Boolean DbPedNrpevdaIsNullable() {
			return true;
		}

		public Boolean DbPedNrpevdaIsKey() {
			return false;
		}

		public Integer DbPedNrpevdaLength() {
			return 10;
		}

		public Integer DbPedNrpevdaPrecision() {
			return 0;
		}

		public String DbPedNrpevdaDefault() {

			return "";

		}

		public String DbPedNrpevdaComment() {

			return "";

		}

		public String DbPedNrpevdaPattern() {

			return "";

		}

		public String DbPedNrpevdaOriginalDbColumnName() {

			return "DbPedNrpevda";

		}

		public Integer DbPedSeqEntrega;

		public Integer getDbPedSeqEntrega() {
			return this.DbPedSeqEntrega;
		}

		public Boolean DbPedSeqEntregaIsNullable() {
			return true;
		}

		public Boolean DbPedSeqEntregaIsKey() {
			return false;
		}

		public Integer DbPedSeqEntregaLength() {
			return 10;
		}

		public Integer DbPedSeqEntregaPrecision() {
			return 0;
		}

		public String DbPedSeqEntregaDefault() {

			return "";

		}

		public String DbPedSeqEntregaComment() {

			return "";

		}

		public String DbPedSeqEntregaPattern() {

			return "";

		}

		public String DbPedSeqEntregaOriginalDbColumnName() {

			return "DbPedSeqEntrega";

		}

		public String DbPedRedespacho;

		public String getDbPedRedespacho() {
			return this.DbPedRedespacho;
		}

		public Boolean DbPedRedespachoIsNullable() {
			return true;
		}

		public Boolean DbPedRedespachoIsKey() {
			return false;
		}

		public Integer DbPedRedespachoLength() {
			return 100;
		}

		public Integer DbPedRedespachoPrecision() {
			return 0;
		}

		public String DbPedRedespachoDefault() {

			return null;

		}

		public String DbPedRedespachoComment() {

			return "";

		}

		public String DbPedRedespachoPattern() {

			return "";

		}

		public String DbPedRedespachoOriginalDbColumnName() {

			return "DbPedRedespacho";

		}

		public java.util.Date DbPedDtProduc;

		public java.util.Date getDbPedDtProduc() {
			return this.DbPedDtProduc;
		}

		public Boolean DbPedDtProducIsNullable() {
			return true;
		}

		public Boolean DbPedDtProducIsKey() {
			return false;
		}

		public Integer DbPedDtProducLength() {
			return 23;
		}

		public Integer DbPedDtProducPrecision() {
			return 3;
		}

		public String DbPedDtProducDefault() {

			return null;

		}

		public String DbPedDtProducComment() {

			return "";

		}

		public String DbPedDtProducPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDtProducOriginalDbColumnName() {

			return "DbPedDtProduc";

		}

		public String SituacaoCorporativa;

		public String getSituacaoCorporativa() {
			return this.SituacaoCorporativa;
		}

		public Boolean SituacaoCorporativaIsNullable() {
			return true;
		}

		public Boolean SituacaoCorporativaIsKey() {
			return false;
		}

		public Integer SituacaoCorporativaLength() {
			return 2;
		}

		public Integer SituacaoCorporativaPrecision() {
			return 0;
		}

		public String SituacaoCorporativaDefault() {

			return null;

		}

		public String SituacaoCorporativaComment() {

			return "";

		}

		public String SituacaoCorporativaPattern() {

			return "";

		}

		public String SituacaoCorporativaOriginalDbColumnName() {

			return "SituacaoCorporativa";

		}

		public String DbPedPrzpgto;

		public String getDbPedPrzpgto() {
			return this.DbPedPrzpgto;
		}

		public Boolean DbPedPrzpgtoIsNullable() {
			return true;
		}

		public Boolean DbPedPrzpgtoIsKey() {
			return false;
		}

		public Integer DbPedPrzpgtoLength() {
			return 255;
		}

		public Integer DbPedPrzpgtoPrecision() {
			return 0;
		}

		public String DbPedPrzpgtoDefault() {

			return null;

		}

		public String DbPedPrzpgtoComment() {

			return "";

		}

		public String DbPedPrzpgtoPattern() {

			return "";

		}

		public String DbPedPrzpgtoOriginalDbColumnName() {

			return "DbPedPrzpgto";

		}

		public Short DbPedForaregra;

		public Short getDbPedForaregra() {
			return this.DbPedForaregra;
		}

		public Boolean DbPedForaregraIsNullable() {
			return true;
		}

		public Boolean DbPedForaregraIsKey() {
			return false;
		}

		public Integer DbPedForaregraLength() {
			return 3;
		}

		public Integer DbPedForaregraPrecision() {
			return 0;
		}

		public String DbPedForaregraDefault() {

			return "";

		}

		public String DbPedForaregraComment() {

			return "";

		}

		public String DbPedForaregraPattern() {

			return "";

		}

		public String DbPedForaregraOriginalDbColumnName() {

			return "DbPedForaregra";

		}

		public Double DbPedDesctoVlr;

		public Double getDbPedDesctoVlr() {
			return this.DbPedDesctoVlr;
		}

		public Boolean DbPedDesctoVlrIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoVlrIsKey() {
			return false;
		}

		public Integer DbPedDesctoVlrLength() {
			return 15;
		}

		public Integer DbPedDesctoVlrPrecision() {
			return 0;
		}

		public String DbPedDesctoVlrDefault() {

			return "";

		}

		public String DbPedDesctoVlrComment() {

			return "";

		}

		public String DbPedDesctoVlrPattern() {

			return "";

		}

		public String DbPedDesctoVlrOriginalDbColumnName() {

			return "DbPedDesctoVlr";

		}

		public String DbPedEntRazao;

		public String getDbPedEntRazao() {
			return this.DbPedEntRazao;
		}

		public Boolean DbPedEntRazaoIsNullable() {
			return true;
		}

		public Boolean DbPedEntRazaoIsKey() {
			return false;
		}

		public Integer DbPedEntRazaoLength() {
			return 80;
		}

		public Integer DbPedEntRazaoPrecision() {
			return 0;
		}

		public String DbPedEntRazaoDefault() {

			return null;

		}

		public String DbPedEntRazaoComment() {

			return "";

		}

		public String DbPedEntRazaoPattern() {

			return "";

		}

		public String DbPedEntRazaoOriginalDbColumnName() {

			return "DbPedEntRazao";

		}

		public Double DbPedCotacao;

		public Double getDbPedCotacao() {
			return this.DbPedCotacao;
		}

		public Boolean DbPedCotacaoIsNullable() {
			return true;
		}

		public Boolean DbPedCotacaoIsKey() {
			return false;
		}

		public Integer DbPedCotacaoLength() {
			return 15;
		}

		public Integer DbPedCotacaoPrecision() {
			return 0;
		}

		public String DbPedCotacaoDefault() {

			return "";

		}

		public String DbPedCotacaoComment() {

			return "";

		}

		public String DbPedCotacaoPattern() {

			return "";

		}

		public String DbPedCotacaoOriginalDbColumnName() {

			return "DbPedCotacao";

		}

		public Double DbPedCliFinal;

		public Double getDbPedCliFinal() {
			return this.DbPedCliFinal;
		}

		public Boolean DbPedCliFinalIsNullable() {
			return true;
		}

		public Boolean DbPedCliFinalIsKey() {
			return false;
		}

		public Integer DbPedCliFinalLength() {
			return 15;
		}

		public Integer DbPedCliFinalPrecision() {
			return 0;
		}

		public String DbPedCliFinalDefault() {

			return "";

		}

		public String DbPedCliFinalComment() {

			return "";

		}

		public String DbPedCliFinalPattern() {

			return "";

		}

		public String DbPedCliFinalOriginalDbColumnName() {

			return "DbPedCliFinal";

		}

		public Short DbPedNUtiPBoni;

		public Short getDbPedNUtiPBoni() {
			return this.DbPedNUtiPBoni;
		}

		public Boolean DbPedNUtiPBoniIsNullable() {
			return true;
		}

		public Boolean DbPedNUtiPBoniIsKey() {
			return false;
		}

		public Integer DbPedNUtiPBoniLength() {
			return 5;
		}

		public Integer DbPedNUtiPBoniPrecision() {
			return 0;
		}

		public String DbPedNUtiPBoniDefault() {

			return "";

		}

		public String DbPedNUtiPBoniComment() {

			return "";

		}

		public String DbPedNUtiPBoniPattern() {

			return "";

		}

		public String DbPedNUtiPBoniOriginalDbColumnName() {

			return "DbPedNUtiPBoni";

		}

		public String DbPedEstrutura;

		public String getDbPedEstrutura() {
			return this.DbPedEstrutura;
		}

		public Boolean DbPedEstruturaIsNullable() {
			return true;
		}

		public Boolean DbPedEstruturaIsKey() {
			return false;
		}

		public Integer DbPedEstruturaLength() {
			return 16;
		}

		public Integer DbPedEstruturaPrecision() {
			return 0;
		}

		public String DbPedEstruturaDefault() {

			return null;

		}

		public String DbPedEstruturaComment() {

			return "";

		}

		public String DbPedEstruturaPattern() {

			return "";

		}

		public String DbPedEstruturaOriginalDbColumnName() {

			return "DbPedEstrutura";

		}

		public String DbPedEntCompl;

		public String getDbPedEntCompl() {
			return this.DbPedEntCompl;
		}

		public Boolean DbPedEntComplIsNullable() {
			return true;
		}

		public Boolean DbPedEntComplIsKey() {
			return false;
		}

		public Integer DbPedEntComplLength() {
			return 50;
		}

		public Integer DbPedEntComplPrecision() {
			return 0;
		}

		public String DbPedEntComplDefault() {

			return null;

		}

		public String DbPedEntComplComment() {

			return "";

		}

		public String DbPedEntComplPattern() {

			return "";

		}

		public String DbPedEntComplOriginalDbColumnName() {

			return "DbPedEntCompl";

		}

		public String DbPedEntPontoRefer;

		public String getDbPedEntPontoRefer() {
			return this.DbPedEntPontoRefer;
		}

		public Boolean DbPedEntPontoReferIsNullable() {
			return true;
		}

		public Boolean DbPedEntPontoReferIsKey() {
			return false;
		}

		public Integer DbPedEntPontoReferLength() {
			return 40;
		}

		public Integer DbPedEntPontoReferPrecision() {
			return 0;
		}

		public String DbPedEntPontoReferDefault() {

			return null;

		}

		public String DbPedEntPontoReferComment() {

			return "";

		}

		public String DbPedEntPontoReferPattern() {

			return "";

		}

		public String DbPedEntPontoReferOriginalDbColumnName() {

			return "DbPedEntPontoRefer";

		}

		public String DbPedEntCxPostal;

		public String getDbPedEntCxPostal() {
			return this.DbPedEntCxPostal;
		}

		public Boolean DbPedEntCxPostalIsNullable() {
			return true;
		}

		public Boolean DbPedEntCxPostalIsKey() {
			return false;
		}

		public Integer DbPedEntCxPostalLength() {
			return 10;
		}

		public Integer DbPedEntCxPostalPrecision() {
			return 0;
		}

		public String DbPedEntCxPostalDefault() {

			return "";

		}

		public String DbPedEntCxPostalComment() {

			return "";

		}

		public String DbPedEntCxPostalPattern() {

			return "";

		}

		public String DbPedEntCxPostalOriginalDbColumnName() {

			return "DbPedEntCxPostal";

		}

		public String DbPedEntPais;

		public String getDbPedEntPais() {
			return this.DbPedEntPais;
		}

		public Boolean DbPedEntPaisIsNullable() {
			return true;
		}

		public Boolean DbPedEntPaisIsKey() {
			return false;
		}

		public Integer DbPedEntPaisLength() {
			return 20;
		}

		public Integer DbPedEntPaisPrecision() {
			return 0;
		}

		public String DbPedEntPaisDefault() {

			return "";

		}

		public String DbPedEntPaisComment() {

			return "";

		}

		public String DbPedEntPaisPattern() {

			return "";

		}

		public String DbPedEntPaisOriginalDbColumnName() {

			return "DbPedEntPais";

		}

		public String DbPedEntTelefone;

		public String getDbPedEntTelefone() {
			return this.DbPedEntTelefone;
		}

		public Boolean DbPedEntTelefoneIsNullable() {
			return true;
		}

		public Boolean DbPedEntTelefoneIsKey() {
			return false;
		}

		public Integer DbPedEntTelefoneLength() {
			return 15;
		}

		public Integer DbPedEntTelefonePrecision() {
			return 0;
		}

		public String DbPedEntTelefoneDefault() {

			return "";

		}

		public String DbPedEntTelefoneComment() {

			return "";

		}

		public String DbPedEntTelefonePattern() {

			return "";

		}

		public String DbPedEntTelefoneOriginalDbColumnName() {

			return "DbPedEntTelefone";

		}

		public String DbPedEntRamal;

		public String getDbPedEntRamal() {
			return this.DbPedEntRamal;
		}

		public Boolean DbPedEntRamalIsNullable() {
			return true;
		}

		public Boolean DbPedEntRamalIsKey() {
			return false;
		}

		public Integer DbPedEntRamalLength() {
			return 5;
		}

		public Integer DbPedEntRamalPrecision() {
			return 0;
		}

		public String DbPedEntRamalDefault() {

			return "";

		}

		public String DbPedEntRamalComment() {

			return "";

		}

		public String DbPedEntRamalPattern() {

			return "";

		}

		public String DbPedEntRamalOriginalDbColumnName() {

			return "DbPedEntRamal";

		}

		public Double DbPedDesctoCampanha;

		public Double getDbPedDesctoCampanha() {
			return this.DbPedDesctoCampanha;
		}

		public Boolean DbPedDesctoCampanhaIsNullable() {
			return true;
		}

		public Boolean DbPedDesctoCampanhaIsKey() {
			return false;
		}

		public Integer DbPedDesctoCampanhaLength() {
			return 15;
		}

		public Integer DbPedDesctoCampanhaPrecision() {
			return 0;
		}

		public String DbPedDesctoCampanhaDefault() {

			return "";

		}

		public String DbPedDesctoCampanhaComment() {

			return "";

		}

		public String DbPedDesctoCampanhaPattern() {

			return "";

		}

		public String DbPedDesctoCampanhaOriginalDbColumnName() {

			return "DbPedDesctoCampanha";

		}

		public Integer DbPedCancelando;

		public Integer getDbPedCancelando() {
			return this.DbPedCancelando;
		}

		public Boolean DbPedCancelandoIsNullable() {
			return true;
		}

		public Boolean DbPedCancelandoIsKey() {
			return false;
		}

		public Integer DbPedCancelandoLength() {
			return 10;
		}

		public Integer DbPedCancelandoPrecision() {
			return 0;
		}

		public String DbPedCancelandoDefault() {

			return "";

		}

		public String DbPedCancelandoComment() {

			return "";

		}

		public String DbPedCancelandoPattern() {

			return "";

		}

		public String DbPedCancelandoOriginalDbColumnName() {

			return "DbPedCancelando";

		}

		public java.util.Date DbPedDataHora;

		public java.util.Date getDbPedDataHora() {
			return this.DbPedDataHora;
		}

		public Boolean DbPedDataHoraIsNullable() {
			return true;
		}

		public Boolean DbPedDataHoraIsKey() {
			return false;
		}

		public Integer DbPedDataHoraLength() {
			return 23;
		}

		public Integer DbPedDataHoraPrecision() {
			return 3;
		}

		public String DbPedDataHoraDefault() {

			return "";

		}

		public String DbPedDataHoraComment() {

			return "";

		}

		public String DbPedDataHoraPattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDataHoraOriginalDbColumnName() {

			return "DbPedDataHora";

		}

		public Integer DbPedMotDesc;

		public Integer getDbPedMotDesc() {
			return this.DbPedMotDesc;
		}

		public Boolean DbPedMotDescIsNullable() {
			return true;
		}

		public Boolean DbPedMotDescIsKey() {
			return false;
		}

		public Integer DbPedMotDescLength() {
			return 10;
		}

		public Integer DbPedMotDescPrecision() {
			return 0;
		}

		public String DbPedMotDescDefault() {

			return "";

		}

		public String DbPedMotDescComment() {

			return "";

		}

		public String DbPedMotDescPattern() {

			return "";

		}

		public String DbPedMotDescOriginalDbColumnName() {

			return "DbPedMotDesc";

		}

		public Integer DbPedNumeroOC;

		public Integer getDbPedNumeroOC() {
			return this.DbPedNumeroOC;
		}

		public Boolean DbPedNumeroOCIsNullable() {
			return true;
		}

		public Boolean DbPedNumeroOCIsKey() {
			return false;
		}

		public Integer DbPedNumeroOCLength() {
			return 10;
		}

		public Integer DbPedNumeroOCPrecision() {
			return 0;
		}

		public String DbPedNumeroOCDefault() {

			return "";

		}

		public String DbPedNumeroOCComment() {

			return "";

		}

		public String DbPedNumeroOCPattern() {

			return "";

		}

		public String DbPedNumeroOCOriginalDbColumnName() {

			return "DbPedNumeroOC";

		}

		public java.util.Date DbPedDtRegraCorte;

		public java.util.Date getDbPedDtRegraCorte() {
			return this.DbPedDtRegraCorte;
		}

		public Boolean DbPedDtRegraCorteIsNullable() {
			return true;
		}

		public Boolean DbPedDtRegraCorteIsKey() {
			return false;
		}

		public Integer DbPedDtRegraCorteLength() {
			return 23;
		}

		public Integer DbPedDtRegraCortePrecision() {
			return 3;
		}

		public String DbPedDtRegraCorteDefault() {

			return "";

		}

		public String DbPedDtRegraCorteComment() {

			return "";

		}

		public String DbPedDtRegraCortePattern() {

			return "dd-MM-yyyy";

		}

		public String DbPedDtRegraCorteOriginalDbColumnName() {

			return "DbPedDtRegraCorte";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.NumeroPedido == null) ? 0 : this.NumeroPedido.hashCode());

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
			final after_tDBInput_2Struct other = (after_tDBInput_2Struct) obj;

			if (this.NumeroPedido == null) {
				if (other.NumeroPedido != null)
					return false;

			} else if (!this.NumeroPedido.equals(other.NumeroPedido))

				return false;

			return true;
		}

		public void copyDataTo(after_tDBInput_2Struct other) {

			other.NumeroPedido = this.NumeroPedido;
			other.CodigoCliente = this.CodigoCliente;
			other.DataEmissao = this.DataEmissao;
			other.PrevisaoEntrega = this.PrevisaoEntrega;
			other.ListaPreco = this.ListaPreco;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.Operacao = this.Operacao;
			other.DbPedPortador = this.DbPedPortador;
			other.DbPedCodMoeda = this.DbPedCodMoeda;
			other.DbPedTabFino = this.DbPedTabFino;
			other.DbPedTabFindo = this.DbPedTabFindo;
			other.DbPedTabFini = this.DbPedTabFini;
			other.DbPedTabFindi = this.DbPedTabFindi;
			other.Representante = this.Representante;
			other.DbPedComiso = this.DbPedComiso;
			other.DbPedComisi = this.DbPedComisi;
			other.DbPedPercFrete = this.DbPedPercFrete;
			other.DbPedCodTransp = this.DbPedCodTransp;
			other.DbPedCodRedesp = this.DbPedCodRedesp;
			other.DbPedTipoFrete = this.DbPedTipoFrete;
			other.DbPedOrdCompra = this.DbPedOrdCompra;
			other.DbPedDescto = this.DbPedDescto;
			other.DbPedDescto2 = this.DbPedDescto2;
			other.DbPedDescto3 = this.DbPedDescto3;
			other.DbPedDescto4 = this.DbPedDescto4;
			other.DbPedDescto5 = this.DbPedDescto5;
			other.DbPedDescto6 = this.DbPedDescto6;
			other.DbPedDescto7 = this.DbPedDescto7;
			other.DbPedAcrescimo = this.DbPedAcrescimo;
			other.DbPedMotivoBloq = this.DbPedMotivoBloq;
			other.SituacaoPedido = this.SituacaoPedido;
			other.DbPedLibUsus = this.DbPedLibUsus;
			other.DbPedLibMots = this.DbPedLibMots;
			other.DbPedLibDatas = this.DbPedLibDatas;
			other.DbPedDataAlter = this.DbPedDataAlter;
			other.DbPedDataEnvio = this.DbPedDataEnvio;
			other.DbPedObserv = this.DbPedObserv;
			other.DbPedTexto = this.DbPedTexto;
			other.DbPedNroOrig = this.DbPedNroOrig;
			other.DbPedTipoPreco = this.DbPedTipoPreco;
			other.DbPedFatur = this.DbPedFatur;
			other.DbPedDataReceb = this.DbPedDataReceb;
			other.DbPedNroRe = this.DbPedNroRe;
			other.DbPedEndPgto = this.DbPedEndPgto;
			other.DbPedMeioTransp = this.DbPedMeioTransp;
			other.DbPedLclEmbarq = this.DbPedLclEmbarq;
			other.DbPedLclDesemb = this.DbPedLclDesemb;
			other.DbPedVlrFrete = this.DbPedVlrFrete;
			other.DbPedVlrSeguro = this.DbPedVlrSeguro;
			other.DbPedEntEnder = this.DbPedEntEnder;
			other.DbPedEntBairro = this.DbPedEntBairro;
			other.DbPedEntCidade = this.DbPedEntCidade;
			other.DbPedEntCep = this.DbPedEntCep;
			other.DbPedEntUf = this.DbPedEntUf;
			other.DbPedEntCgcmf = this.DbPedEntCgcmf;
			other.DbPedEntCgcte = this.DbPedEntCgcte;
			other.DbPedEmpresa = this.DbPedEmpresa;
			other.DbPedCdv = this.DbPedCdv;
			other.DbPedTipo = this.DbPedTipo;
			other.DbPedPeriodo = this.DbPedPeriodo;
			other.DbPedPreposto = this.DbPedPreposto;
			other.DbPedNrpevda = this.DbPedNrpevda;
			other.DbPedSeqEntrega = this.DbPedSeqEntrega;
			other.DbPedRedespacho = this.DbPedRedespacho;
			other.DbPedDtProduc = this.DbPedDtProduc;
			other.SituacaoCorporativa = this.SituacaoCorporativa;
			other.DbPedPrzpgto = this.DbPedPrzpgto;
			other.DbPedForaregra = this.DbPedForaregra;
			other.DbPedDesctoVlr = this.DbPedDesctoVlr;
			other.DbPedEntRazao = this.DbPedEntRazao;
			other.DbPedCotacao = this.DbPedCotacao;
			other.DbPedCliFinal = this.DbPedCliFinal;
			other.DbPedNUtiPBoni = this.DbPedNUtiPBoni;
			other.DbPedEstrutura = this.DbPedEstrutura;
			other.DbPedEntCompl = this.DbPedEntCompl;
			other.DbPedEntPontoRefer = this.DbPedEntPontoRefer;
			other.DbPedEntCxPostal = this.DbPedEntCxPostal;
			other.DbPedEntPais = this.DbPedEntPais;
			other.DbPedEntTelefone = this.DbPedEntTelefone;
			other.DbPedEntRamal = this.DbPedEntRamal;
			other.DbPedDesctoCampanha = this.DbPedDesctoCampanha;
			other.DbPedCancelando = this.DbPedCancelando;
			other.DbPedDataHora = this.DbPedDataHora;
			other.DbPedMotDesc = this.DbPedMotDesc;
			other.DbPedNumeroOC = this.DbPedNumeroOC;
			other.DbPedDtRegraCorte = this.DbPedDtRegraCorte;

		}

		public void copyKeysDataTo(after_tDBInput_2Struct other) {

			other.NumeroPedido = this.NumeroPedido;

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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.ListaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.Operacao = readString(dis);

					this.DbPedPortador = readInteger(dis);

					this.DbPedCodMoeda = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFino = null;
					} else {
						this.DbPedTabFino = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindo = null;
					} else {
						this.DbPedTabFindo = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFini = null;
					} else {
						this.DbPedTabFini = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindi = null;
					} else {
						this.DbPedTabFindi = dis.readShort();
					}

					this.Representante = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComiso = null;
					} else {
						this.DbPedComiso = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComisi = null;
					} else {
						this.DbPedComisi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedPercFrete = null;
					} else {
						this.DbPedPercFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodTransp = null;
					} else {
						this.DbPedCodTransp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodRedesp = null;
					} else {
						this.DbPedCodRedesp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoFrete = null;
					} else {
						this.DbPedTipoFrete = dis.readShort();
					}

					this.DbPedOrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto = null;
					} else {
						this.DbPedDescto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto2 = null;
					} else {
						this.DbPedDescto2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto3 = null;
					} else {
						this.DbPedDescto3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto4 = null;
					} else {
						this.DbPedDescto4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto5 = null;
					} else {
						this.DbPedDescto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto6 = null;
					} else {
						this.DbPedDescto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto7 = null;
					} else {
						this.DbPedDescto7 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedAcrescimo = null;
					} else {
						this.DbPedAcrescimo = dis.readDouble();
					}

					this.DbPedMotivoBloq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					this.DbPedLibUsus = readString(dis);

					this.DbPedLibMots = readString(dis);

					this.DbPedLibDatas = readString(dis);

					this.DbPedDataAlter = readDate(dis);

					this.DbPedDataEnvio = readDate(dis);

					this.DbPedObserv = readString(dis);

					this.DbPedTexto = readString(dis);

					this.DbPedNroOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoPreco = null;
					} else {
						this.DbPedTipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedFatur = null;
					} else {
						this.DbPedFatur = dis.readShort();
					}

					this.DbPedDataReceb = readDate(dis);

					this.DbPedNroRe = readString(dis);

					this.DbPedEndPgto = readInteger(dis);

					this.DbPedMeioTransp = readString(dis);

					this.DbPedLclEmbarq = readString(dis);

					this.DbPedLclDesemb = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrFrete = null;
					} else {
						this.DbPedVlrFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrSeguro = null;
					} else {
						this.DbPedVlrSeguro = dis.readDouble();
					}

					this.DbPedEntEnder = readString(dis);

					this.DbPedEntBairro = readString(dis);

					this.DbPedEntCidade = readString(dis);

					this.DbPedEntCep = readString(dis);

					this.DbPedEntUf = readString(dis);

					this.DbPedEntCgcmf = readString(dis);

					this.DbPedEntCgcte = readString(dis);

					this.DbPedEmpresa = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCdv = null;
					} else {
						this.DbPedCdv = dis.readDouble();
					}

					this.DbPedTipo = readString(dis);

					this.DbPedPeriodo = readString(dis);

					this.DbPedPreposto = readInteger(dis);

					this.DbPedNrpevda = readInteger(dis);

					this.DbPedSeqEntrega = readInteger(dis);

					this.DbPedRedespacho = readString(dis);

					this.DbPedDtProduc = readDate(dis);

					this.SituacaoCorporativa = readString(dis);

					this.DbPedPrzpgto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedForaregra = null;
					} else {
						this.DbPedForaregra = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoVlr = null;
					} else {
						this.DbPedDesctoVlr = dis.readDouble();
					}

					this.DbPedEntRazao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCotacao = null;
					} else {
						this.DbPedCotacao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCliFinal = null;
					} else {
						this.DbPedCliFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedNUtiPBoni = null;
					} else {
						this.DbPedNUtiPBoni = dis.readShort();
					}

					this.DbPedEstrutura = readString(dis);

					this.DbPedEntCompl = readString(dis);

					this.DbPedEntPontoRefer = readString(dis);

					this.DbPedEntCxPostal = readString(dis);

					this.DbPedEntPais = readString(dis);

					this.DbPedEntTelefone = readString(dis);

					this.DbPedEntRamal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoCampanha = null;
					} else {
						this.DbPedDesctoCampanha = dis.readDouble();
					}

					this.DbPedCancelando = readInteger(dis);

					this.DbPedDataHora = readDate(dis);

					this.DbPedMotDesc = readInteger(dis);

					this.DbPedNumeroOC = readInteger(dis);

					this.DbPedDtRegraCorte = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.NumeroPedido = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CodigoCliente = null;
					} else {
						this.CodigoCliente = dis.readDouble();
					}

					this.DataEmissao = readDate(dis);

					this.PrevisaoEntrega = readDate(dis);

					this.ListaPreco = readString(dis);

					this.CondicaoPagamento = readInteger(dis);

					this.Operacao = readString(dis);

					this.DbPedPortador = readInteger(dis);

					this.DbPedCodMoeda = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFino = null;
					} else {
						this.DbPedTabFino = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindo = null;
					} else {
						this.DbPedTabFindo = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFini = null;
					} else {
						this.DbPedTabFini = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTabFindi = null;
					} else {
						this.DbPedTabFindi = dis.readShort();
					}

					this.Representante = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComiso = null;
					} else {
						this.DbPedComiso = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedComisi = null;
					} else {
						this.DbPedComisi = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedPercFrete = null;
					} else {
						this.DbPedPercFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodTransp = null;
					} else {
						this.DbPedCodTransp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCodRedesp = null;
					} else {
						this.DbPedCodRedesp = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoFrete = null;
					} else {
						this.DbPedTipoFrete = dis.readShort();
					}

					this.DbPedOrdCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto = null;
					} else {
						this.DbPedDescto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto2 = null;
					} else {
						this.DbPedDescto2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto3 = null;
					} else {
						this.DbPedDescto3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto4 = null;
					} else {
						this.DbPedDescto4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto5 = null;
					} else {
						this.DbPedDescto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto6 = null;
					} else {
						this.DbPedDescto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDescto7 = null;
					} else {
						this.DbPedDescto7 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedAcrescimo = null;
					} else {
						this.DbPedAcrescimo = dis.readDouble();
					}

					this.DbPedMotivoBloq = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SituacaoPedido = null;
					} else {
						this.SituacaoPedido = dis.readShort();
					}

					this.DbPedLibUsus = readString(dis);

					this.DbPedLibMots = readString(dis);

					this.DbPedLibDatas = readString(dis);

					this.DbPedDataAlter = readDate(dis);

					this.DbPedDataEnvio = readDate(dis);

					this.DbPedObserv = readString(dis);

					this.DbPedTexto = readString(dis);

					this.DbPedNroOrig = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedTipoPreco = null;
					} else {
						this.DbPedTipoPreco = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedFatur = null;
					} else {
						this.DbPedFatur = dis.readShort();
					}

					this.DbPedDataReceb = readDate(dis);

					this.DbPedNroRe = readString(dis);

					this.DbPedEndPgto = readInteger(dis);

					this.DbPedMeioTransp = readString(dis);

					this.DbPedLclEmbarq = readString(dis);

					this.DbPedLclDesemb = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrFrete = null;
					} else {
						this.DbPedVlrFrete = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedVlrSeguro = null;
					} else {
						this.DbPedVlrSeguro = dis.readDouble();
					}

					this.DbPedEntEnder = readString(dis);

					this.DbPedEntBairro = readString(dis);

					this.DbPedEntCidade = readString(dis);

					this.DbPedEntCep = readString(dis);

					this.DbPedEntUf = readString(dis);

					this.DbPedEntCgcmf = readString(dis);

					this.DbPedEntCgcte = readString(dis);

					this.DbPedEmpresa = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCdv = null;
					} else {
						this.DbPedCdv = dis.readDouble();
					}

					this.DbPedTipo = readString(dis);

					this.DbPedPeriodo = readString(dis);

					this.DbPedPreposto = readInteger(dis);

					this.DbPedNrpevda = readInteger(dis);

					this.DbPedSeqEntrega = readInteger(dis);

					this.DbPedRedespacho = readString(dis);

					this.DbPedDtProduc = readDate(dis);

					this.SituacaoCorporativa = readString(dis);

					this.DbPedPrzpgto = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedForaregra = null;
					} else {
						this.DbPedForaregra = dis.readShort();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoVlr = null;
					} else {
						this.DbPedDesctoVlr = dis.readDouble();
					}

					this.DbPedEntRazao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCotacao = null;
					} else {
						this.DbPedCotacao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedCliFinal = null;
					} else {
						this.DbPedCliFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.DbPedNUtiPBoni = null;
					} else {
						this.DbPedNUtiPBoni = dis.readShort();
					}

					this.DbPedEstrutura = readString(dis);

					this.DbPedEntCompl = readString(dis);

					this.DbPedEntPontoRefer = readString(dis);

					this.DbPedEntCxPostal = readString(dis);

					this.DbPedEntPais = readString(dis);

					this.DbPedEntTelefone = readString(dis);

					this.DbPedEntRamal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DbPedDesctoCampanha = null;
					} else {
						this.DbPedDesctoCampanha = dis.readDouble();
					}

					this.DbPedCancelando = readInteger(dis);

					this.DbPedDataHora = readDate(dis);

					this.DbPedMotDesc = readInteger(dis);

					this.DbPedNumeroOC = readInteger(dis);

					this.DbPedDtRegraCorte = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.ListaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.Operacao, dos);

				// Integer

				writeInteger(this.DbPedPortador, dos);

				// Integer

				writeInteger(this.DbPedCodMoeda, dos);

				// Short

				if (this.DbPedTabFino == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFino);
				}

				// Short

				if (this.DbPedTabFindo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindo);
				}

				// Short

				if (this.DbPedTabFini == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFini);
				}

				// Short

				if (this.DbPedTabFindi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindi);
				}

				// Integer

				writeInteger(this.Representante, dos);

				// Double

				if (this.DbPedComiso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComiso);
				}

				// Double

				if (this.DbPedComisi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComisi);
				}

				// Double

				if (this.DbPedPercFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedPercFrete);
				}

				// Double

				if (this.DbPedCodTransp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodTransp);
				}

				// Double

				if (this.DbPedCodRedesp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodRedesp);
				}

				// Short

				if (this.DbPedTipoFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoFrete);
				}

				// String

				writeString(this.DbPedOrdCompra, dos);

				// Double

				if (this.DbPedDescto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto);
				}

				// Double

				if (this.DbPedDescto2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto2);
				}

				// Double

				if (this.DbPedDescto3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto3);
				}

				// Double

				if (this.DbPedDescto4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto4);
				}

				// Double

				if (this.DbPedDescto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto5);
				}

				// Double

				if (this.DbPedDescto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto6);
				}

				// Double

				if (this.DbPedDescto7 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto7);
				}

				// Double

				if (this.DbPedAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedAcrescimo);
				}

				// String

				writeString(this.DbPedMotivoBloq, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// String

				writeString(this.DbPedLibUsus, dos);

				// String

				writeString(this.DbPedLibMots, dos);

				// String

				writeString(this.DbPedLibDatas, dos);

				// java.util.Date

				writeDate(this.DbPedDataAlter, dos);

				// java.util.Date

				writeDate(this.DbPedDataEnvio, dos);

				// String

				writeString(this.DbPedObserv, dos);

				// String

				writeString(this.DbPedTexto, dos);

				// String

				writeString(this.DbPedNroOrig, dos);

				// Short

				if (this.DbPedTipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoPreco);
				}

				// Short

				if (this.DbPedFatur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedFatur);
				}

				// java.util.Date

				writeDate(this.DbPedDataReceb, dos);

				// String

				writeString(this.DbPedNroRe, dos);

				// Integer

				writeInteger(this.DbPedEndPgto, dos);

				// String

				writeString(this.DbPedMeioTransp, dos);

				// String

				writeString(this.DbPedLclEmbarq, dos);

				// String

				writeString(this.DbPedLclDesemb, dos);

				// Double

				if (this.DbPedVlrFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrFrete);
				}

				// Double

				if (this.DbPedVlrSeguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrSeguro);
				}

				// String

				writeString(this.DbPedEntEnder, dos);

				// String

				writeString(this.DbPedEntBairro, dos);

				// String

				writeString(this.DbPedEntCidade, dos);

				// String

				writeString(this.DbPedEntCep, dos);

				// String

				writeString(this.DbPedEntUf, dos);

				// String

				writeString(this.DbPedEntCgcmf, dos);

				// String

				writeString(this.DbPedEntCgcte, dos);

				// String

				writeString(this.DbPedEmpresa, dos);

				// Double

				if (this.DbPedCdv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCdv);
				}

				// String

				writeString(this.DbPedTipo, dos);

				// String

				writeString(this.DbPedPeriodo, dos);

				// Integer

				writeInteger(this.DbPedPreposto, dos);

				// Integer

				writeInteger(this.DbPedNrpevda, dos);

				// Integer

				writeInteger(this.DbPedSeqEntrega, dos);

				// String

				writeString(this.DbPedRedespacho, dos);

				// java.util.Date

				writeDate(this.DbPedDtProduc, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

				// String

				writeString(this.DbPedPrzpgto, dos);

				// Short

				if (this.DbPedForaregra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedForaregra);
				}

				// Double

				if (this.DbPedDesctoVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoVlr);
				}

				// String

				writeString(this.DbPedEntRazao, dos);

				// Double

				if (this.DbPedCotacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCotacao);
				}

				// Double

				if (this.DbPedCliFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCliFinal);
				}

				// Short

				if (this.DbPedNUtiPBoni == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedNUtiPBoni);
				}

				// String

				writeString(this.DbPedEstrutura, dos);

				// String

				writeString(this.DbPedEntCompl, dos);

				// String

				writeString(this.DbPedEntPontoRefer, dos);

				// String

				writeString(this.DbPedEntCxPostal, dos);

				// String

				writeString(this.DbPedEntPais, dos);

				// String

				writeString(this.DbPedEntTelefone, dos);

				// String

				writeString(this.DbPedEntRamal, dos);

				// Double

				if (this.DbPedDesctoCampanha == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoCampanha);
				}

				// Integer

				writeInteger(this.DbPedCancelando, dos);

				// java.util.Date

				writeDate(this.DbPedDataHora, dos);

				// Integer

				writeInteger(this.DbPedMotDesc, dos);

				// Integer

				writeInteger(this.DbPedNumeroOC, dos);

				// java.util.Date

				writeDate(this.DbPedDtRegraCorte, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.NumeroPedido, dos);

				// Double

				if (this.CodigoCliente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CodigoCliente);
				}

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// java.util.Date

				writeDate(this.PrevisaoEntrega, dos);

				// String

				writeString(this.ListaPreco, dos);

				// Integer

				writeInteger(this.CondicaoPagamento, dos);

				// String

				writeString(this.Operacao, dos);

				// Integer

				writeInteger(this.DbPedPortador, dos);

				// Integer

				writeInteger(this.DbPedCodMoeda, dos);

				// Short

				if (this.DbPedTabFino == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFino);
				}

				// Short

				if (this.DbPedTabFindo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindo);
				}

				// Short

				if (this.DbPedTabFini == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFini);
				}

				// Short

				if (this.DbPedTabFindi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTabFindi);
				}

				// Integer

				writeInteger(this.Representante, dos);

				// Double

				if (this.DbPedComiso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComiso);
				}

				// Double

				if (this.DbPedComisi == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedComisi);
				}

				// Double

				if (this.DbPedPercFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedPercFrete);
				}

				// Double

				if (this.DbPedCodTransp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodTransp);
				}

				// Double

				if (this.DbPedCodRedesp == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCodRedesp);
				}

				// Short

				if (this.DbPedTipoFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoFrete);
				}

				// String

				writeString(this.DbPedOrdCompra, dos);

				// Double

				if (this.DbPedDescto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto);
				}

				// Double

				if (this.DbPedDescto2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto2);
				}

				// Double

				if (this.DbPedDescto3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto3);
				}

				// Double

				if (this.DbPedDescto4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto4);
				}

				// Double

				if (this.DbPedDescto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto5);
				}

				// Double

				if (this.DbPedDescto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto6);
				}

				// Double

				if (this.DbPedDescto7 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDescto7);
				}

				// Double

				if (this.DbPedAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedAcrescimo);
				}

				// String

				writeString(this.DbPedMotivoBloq, dos);

				// Short

				if (this.SituacaoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.SituacaoPedido);
				}

				// String

				writeString(this.DbPedLibUsus, dos);

				// String

				writeString(this.DbPedLibMots, dos);

				// String

				writeString(this.DbPedLibDatas, dos);

				// java.util.Date

				writeDate(this.DbPedDataAlter, dos);

				// java.util.Date

				writeDate(this.DbPedDataEnvio, dos);

				// String

				writeString(this.DbPedObserv, dos);

				// String

				writeString(this.DbPedTexto, dos);

				// String

				writeString(this.DbPedNroOrig, dos);

				// Short

				if (this.DbPedTipoPreco == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedTipoPreco);
				}

				// Short

				if (this.DbPedFatur == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedFatur);
				}

				// java.util.Date

				writeDate(this.DbPedDataReceb, dos);

				// String

				writeString(this.DbPedNroRe, dos);

				// Integer

				writeInteger(this.DbPedEndPgto, dos);

				// String

				writeString(this.DbPedMeioTransp, dos);

				// String

				writeString(this.DbPedLclEmbarq, dos);

				// String

				writeString(this.DbPedLclDesemb, dos);

				// Double

				if (this.DbPedVlrFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrFrete);
				}

				// Double

				if (this.DbPedVlrSeguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedVlrSeguro);
				}

				// String

				writeString(this.DbPedEntEnder, dos);

				// String

				writeString(this.DbPedEntBairro, dos);

				// String

				writeString(this.DbPedEntCidade, dos);

				// String

				writeString(this.DbPedEntCep, dos);

				// String

				writeString(this.DbPedEntUf, dos);

				// String

				writeString(this.DbPedEntCgcmf, dos);

				// String

				writeString(this.DbPedEntCgcte, dos);

				// String

				writeString(this.DbPedEmpresa, dos);

				// Double

				if (this.DbPedCdv == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCdv);
				}

				// String

				writeString(this.DbPedTipo, dos);

				// String

				writeString(this.DbPedPeriodo, dos);

				// Integer

				writeInteger(this.DbPedPreposto, dos);

				// Integer

				writeInteger(this.DbPedNrpevda, dos);

				// Integer

				writeInteger(this.DbPedSeqEntrega, dos);

				// String

				writeString(this.DbPedRedespacho, dos);

				// java.util.Date

				writeDate(this.DbPedDtProduc, dos);

				// String

				writeString(this.SituacaoCorporativa, dos);

				// String

				writeString(this.DbPedPrzpgto, dos);

				// Short

				if (this.DbPedForaregra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedForaregra);
				}

				// Double

				if (this.DbPedDesctoVlr == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoVlr);
				}

				// String

				writeString(this.DbPedEntRazao, dos);

				// Double

				if (this.DbPedCotacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCotacao);
				}

				// Double

				if (this.DbPedCliFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedCliFinal);
				}

				// Short

				if (this.DbPedNUtiPBoni == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeShort(this.DbPedNUtiPBoni);
				}

				// String

				writeString(this.DbPedEstrutura, dos);

				// String

				writeString(this.DbPedEntCompl, dos);

				// String

				writeString(this.DbPedEntPontoRefer, dos);

				// String

				writeString(this.DbPedEntCxPostal, dos);

				// String

				writeString(this.DbPedEntPais, dos);

				// String

				writeString(this.DbPedEntTelefone, dos);

				// String

				writeString(this.DbPedEntRamal, dos);

				// Double

				if (this.DbPedDesctoCampanha == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DbPedDesctoCampanha);
				}

				// Integer

				writeInteger(this.DbPedCancelando, dos);

				// java.util.Date

				writeDate(this.DbPedDataHora, dos);

				// Integer

				writeInteger(this.DbPedMotDesc, dos);

				// Integer

				writeInteger(this.DbPedNumeroOC, dos);

				// java.util.Date

				writeDate(this.DbPedDtRegraCorte, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("NumeroPedido=" + String.valueOf(NumeroPedido));
			sb.append(",CodigoCliente=" + String.valueOf(CodigoCliente));
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",PrevisaoEntrega=" + String.valueOf(PrevisaoEntrega));
			sb.append(",ListaPreco=" + ListaPreco);
			sb.append(",CondicaoPagamento=" + String.valueOf(CondicaoPagamento));
			sb.append(",Operacao=" + Operacao);
			sb.append(",DbPedPortador=" + String.valueOf(DbPedPortador));
			sb.append(",DbPedCodMoeda=" + String.valueOf(DbPedCodMoeda));
			sb.append(",DbPedTabFino=" + String.valueOf(DbPedTabFino));
			sb.append(",DbPedTabFindo=" + String.valueOf(DbPedTabFindo));
			sb.append(",DbPedTabFini=" + String.valueOf(DbPedTabFini));
			sb.append(",DbPedTabFindi=" + String.valueOf(DbPedTabFindi));
			sb.append(",Representante=" + String.valueOf(Representante));
			sb.append(",DbPedComiso=" + String.valueOf(DbPedComiso));
			sb.append(",DbPedComisi=" + String.valueOf(DbPedComisi));
			sb.append(",DbPedPercFrete=" + String.valueOf(DbPedPercFrete));
			sb.append(",DbPedCodTransp=" + String.valueOf(DbPedCodTransp));
			sb.append(",DbPedCodRedesp=" + String.valueOf(DbPedCodRedesp));
			sb.append(",DbPedTipoFrete=" + String.valueOf(DbPedTipoFrete));
			sb.append(",DbPedOrdCompra=" + DbPedOrdCompra);
			sb.append(",DbPedDescto=" + String.valueOf(DbPedDescto));
			sb.append(",DbPedDescto2=" + String.valueOf(DbPedDescto2));
			sb.append(",DbPedDescto3=" + String.valueOf(DbPedDescto3));
			sb.append(",DbPedDescto4=" + String.valueOf(DbPedDescto4));
			sb.append(",DbPedDescto5=" + String.valueOf(DbPedDescto5));
			sb.append(",DbPedDescto6=" + String.valueOf(DbPedDescto6));
			sb.append(",DbPedDescto7=" + String.valueOf(DbPedDescto7));
			sb.append(",DbPedAcrescimo=" + String.valueOf(DbPedAcrescimo));
			sb.append(",DbPedMotivoBloq=" + DbPedMotivoBloq);
			sb.append(",SituacaoPedido=" + String.valueOf(SituacaoPedido));
			sb.append(",DbPedLibUsus=" + DbPedLibUsus);
			sb.append(",DbPedLibMots=" + DbPedLibMots);
			sb.append(",DbPedLibDatas=" + DbPedLibDatas);
			sb.append(",DbPedDataAlter=" + String.valueOf(DbPedDataAlter));
			sb.append(",DbPedDataEnvio=" + String.valueOf(DbPedDataEnvio));
			sb.append(",DbPedObserv=" + DbPedObserv);
			sb.append(",DbPedTexto=" + DbPedTexto);
			sb.append(",DbPedNroOrig=" + DbPedNroOrig);
			sb.append(",DbPedTipoPreco=" + String.valueOf(DbPedTipoPreco));
			sb.append(",DbPedFatur=" + String.valueOf(DbPedFatur));
			sb.append(",DbPedDataReceb=" + String.valueOf(DbPedDataReceb));
			sb.append(",DbPedNroRe=" + DbPedNroRe);
			sb.append(",DbPedEndPgto=" + String.valueOf(DbPedEndPgto));
			sb.append(",DbPedMeioTransp=" + DbPedMeioTransp);
			sb.append(",DbPedLclEmbarq=" + DbPedLclEmbarq);
			sb.append(",DbPedLclDesemb=" + DbPedLclDesemb);
			sb.append(",DbPedVlrFrete=" + String.valueOf(DbPedVlrFrete));
			sb.append(",DbPedVlrSeguro=" + String.valueOf(DbPedVlrSeguro));
			sb.append(",DbPedEntEnder=" + DbPedEntEnder);
			sb.append(",DbPedEntBairro=" + DbPedEntBairro);
			sb.append(",DbPedEntCidade=" + DbPedEntCidade);
			sb.append(",DbPedEntCep=" + DbPedEntCep);
			sb.append(",DbPedEntUf=" + DbPedEntUf);
			sb.append(",DbPedEntCgcmf=" + DbPedEntCgcmf);
			sb.append(",DbPedEntCgcte=" + DbPedEntCgcte);
			sb.append(",DbPedEmpresa=" + DbPedEmpresa);
			sb.append(",DbPedCdv=" + String.valueOf(DbPedCdv));
			sb.append(",DbPedTipo=" + DbPedTipo);
			sb.append(",DbPedPeriodo=" + DbPedPeriodo);
			sb.append(",DbPedPreposto=" + String.valueOf(DbPedPreposto));
			sb.append(",DbPedNrpevda=" + String.valueOf(DbPedNrpevda));
			sb.append(",DbPedSeqEntrega=" + String.valueOf(DbPedSeqEntrega));
			sb.append(",DbPedRedespacho=" + DbPedRedespacho);
			sb.append(",DbPedDtProduc=" + String.valueOf(DbPedDtProduc));
			sb.append(",SituacaoCorporativa=" + SituacaoCorporativa);
			sb.append(",DbPedPrzpgto=" + DbPedPrzpgto);
			sb.append(",DbPedForaregra=" + String.valueOf(DbPedForaregra));
			sb.append(",DbPedDesctoVlr=" + String.valueOf(DbPedDesctoVlr));
			sb.append(",DbPedEntRazao=" + DbPedEntRazao);
			sb.append(",DbPedCotacao=" + String.valueOf(DbPedCotacao));
			sb.append(",DbPedCliFinal=" + String.valueOf(DbPedCliFinal));
			sb.append(",DbPedNUtiPBoni=" + String.valueOf(DbPedNUtiPBoni));
			sb.append(",DbPedEstrutura=" + DbPedEstrutura);
			sb.append(",DbPedEntCompl=" + DbPedEntCompl);
			sb.append(",DbPedEntPontoRefer=" + DbPedEntPontoRefer);
			sb.append(",DbPedEntCxPostal=" + DbPedEntCxPostal);
			sb.append(",DbPedEntPais=" + DbPedEntPais);
			sb.append(",DbPedEntTelefone=" + DbPedEntTelefone);
			sb.append(",DbPedEntRamal=" + DbPedEntRamal);
			sb.append(",DbPedDesctoCampanha=" + String.valueOf(DbPedDesctoCampanha));
			sb.append(",DbPedCancelando=" + String.valueOf(DbPedCancelando));
			sb.append(",DbPedDataHora=" + String.valueOf(DbPedDataHora));
			sb.append(",DbPedMotDesc=" + String.valueOf(DbPedMotDesc));
			sb.append(",DbPedNumeroOC=" + String.valueOf(DbPedNumeroOC));
			sb.append(",DbPedDtRegraCorte=" + String.valueOf(DbPedDtRegraCorte));
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

			if (CodigoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCliente);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (PrevisaoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(PrevisaoEntrega);
			}

			sb.append("|");

			if (ListaPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(ListaPreco);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			if (DbPedPortador == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPortador);
			}

			sb.append("|");

			if (DbPedCodMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodMoeda);
			}

			sb.append("|");

			if (DbPedTabFino == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFino);
			}

			sb.append("|");

			if (DbPedTabFindo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFindo);
			}

			sb.append("|");

			if (DbPedTabFini == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFini);
			}

			sb.append("|");

			if (DbPedTabFindi == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTabFindi);
			}

			sb.append("|");

			if (Representante == null) {
				sb.append("<null>");
			} else {
				sb.append(Representante);
			}

			sb.append("|");

			if (DbPedComiso == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedComiso);
			}

			sb.append("|");

			if (DbPedComisi == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedComisi);
			}

			sb.append("|");

			if (DbPedPercFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPercFrete);
			}

			sb.append("|");

			if (DbPedCodTransp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodTransp);
			}

			sb.append("|");

			if (DbPedCodRedesp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCodRedesp);
			}

			sb.append("|");

			if (DbPedTipoFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipoFrete);
			}

			sb.append("|");

			if (DbPedOrdCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedOrdCompra);
			}

			sb.append("|");

			if (DbPedDescto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto);
			}

			sb.append("|");

			if (DbPedDescto2 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto2);
			}

			sb.append("|");

			if (DbPedDescto3 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto3);
			}

			sb.append("|");

			if (DbPedDescto4 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto4);
			}

			sb.append("|");

			if (DbPedDescto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto5);
			}

			sb.append("|");

			if (DbPedDescto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto6);
			}

			sb.append("|");

			if (DbPedDescto7 == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDescto7);
			}

			sb.append("|");

			if (DbPedAcrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedAcrescimo);
			}

			sb.append("|");

			if (DbPedMotivoBloq == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMotivoBloq);
			}

			sb.append("|");

			if (SituacaoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoPedido);
			}

			sb.append("|");

			if (DbPedLibUsus == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibUsus);
			}

			sb.append("|");

			if (DbPedLibMots == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibMots);
			}

			sb.append("|");

			if (DbPedLibDatas == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLibDatas);
			}

			sb.append("|");

			if (DbPedDataAlter == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataAlter);
			}

			sb.append("|");

			if (DbPedDataEnvio == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataEnvio);
			}

			sb.append("|");

			if (DbPedObserv == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedObserv);
			}

			sb.append("|");

			if (DbPedTexto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTexto);
			}

			sb.append("|");

			if (DbPedNroOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNroOrig);
			}

			sb.append("|");

			if (DbPedTipoPreco == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipoPreco);
			}

			sb.append("|");

			if (DbPedFatur == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedFatur);
			}

			sb.append("|");

			if (DbPedDataReceb == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataReceb);
			}

			sb.append("|");

			if (DbPedNroRe == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNroRe);
			}

			sb.append("|");

			if (DbPedEndPgto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEndPgto);
			}

			sb.append("|");

			if (DbPedMeioTransp == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMeioTransp);
			}

			sb.append("|");

			if (DbPedLclEmbarq == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLclEmbarq);
			}

			sb.append("|");

			if (DbPedLclDesemb == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedLclDesemb);
			}

			sb.append("|");

			if (DbPedVlrFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedVlrFrete);
			}

			sb.append("|");

			if (DbPedVlrSeguro == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedVlrSeguro);
			}

			sb.append("|");

			if (DbPedEntEnder == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntEnder);
			}

			sb.append("|");

			if (DbPedEntBairro == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntBairro);
			}

			sb.append("|");

			if (DbPedEntCidade == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCidade);
			}

			sb.append("|");

			if (DbPedEntCep == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCep);
			}

			sb.append("|");

			if (DbPedEntUf == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntUf);
			}

			sb.append("|");

			if (DbPedEntCgcmf == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCgcmf);
			}

			sb.append("|");

			if (DbPedEntCgcte == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCgcte);
			}

			sb.append("|");

			if (DbPedEmpresa == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEmpresa);
			}

			sb.append("|");

			if (DbPedCdv == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCdv);
			}

			sb.append("|");

			if (DbPedTipo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedTipo);
			}

			sb.append("|");

			if (DbPedPeriodo == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPeriodo);
			}

			sb.append("|");

			if (DbPedPreposto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPreposto);
			}

			sb.append("|");

			if (DbPedNrpevda == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNrpevda);
			}

			sb.append("|");

			if (DbPedSeqEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedSeqEntrega);
			}

			sb.append("|");

			if (DbPedRedespacho == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedRedespacho);
			}

			sb.append("|");

			if (DbPedDtProduc == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDtProduc);
			}

			sb.append("|");

			if (SituacaoCorporativa == null) {
				sb.append("<null>");
			} else {
				sb.append(SituacaoCorporativa);
			}

			sb.append("|");

			if (DbPedPrzpgto == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedPrzpgto);
			}

			sb.append("|");

			if (DbPedForaregra == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedForaregra);
			}

			sb.append("|");

			if (DbPedDesctoVlr == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDesctoVlr);
			}

			sb.append("|");

			if (DbPedEntRazao == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntRazao);
			}

			sb.append("|");

			if (DbPedCotacao == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCotacao);
			}

			sb.append("|");

			if (DbPedCliFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCliFinal);
			}

			sb.append("|");

			if (DbPedNUtiPBoni == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNUtiPBoni);
			}

			sb.append("|");

			if (DbPedEstrutura == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEstrutura);
			}

			sb.append("|");

			if (DbPedEntCompl == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCompl);
			}

			sb.append("|");

			if (DbPedEntPontoRefer == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntPontoRefer);
			}

			sb.append("|");

			if (DbPedEntCxPostal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntCxPostal);
			}

			sb.append("|");

			if (DbPedEntPais == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntPais);
			}

			sb.append("|");

			if (DbPedEntTelefone == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntTelefone);
			}

			sb.append("|");

			if (DbPedEntRamal == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedEntRamal);
			}

			sb.append("|");

			if (DbPedDesctoCampanha == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDesctoCampanha);
			}

			sb.append("|");

			if (DbPedCancelando == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedCancelando);
			}

			sb.append("|");

			if (DbPedDataHora == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDataHora);
			}

			sb.append("|");

			if (DbPedMotDesc == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedMotDesc);
			}

			sb.append("|");

			if (DbPedNumeroOC == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedNumeroOC);
			}

			sb.append("|");

			if (DbPedDtRegraCorte == null) {
				sb.append("<null>");
			} else {
				sb.append(DbPedDtRegraCorte);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.NumeroPedido, other.NumeroPedido);
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
		org.slf4j.MDC.put("_subJobPid", "Y2bSpN_" + subJobPidCounter.getAndIncrement());

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

				tDBInput_1Process(globalMap);
				tDBInput_3Process(globalMap);

				Capa_pedidoStruct Capa_pedido = new Capa_pedidoStruct();
				CarteiraStruct Carteira = new CarteiraStruct();
				row1Struct row1 = new row1Struct();
				row1Struct row3 = row1;

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_1", false);
				start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

				int tos_count_tFileOutputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputExcel_1 = new StringBuilder();
							log4jParamters_tFileOutputExcel_1.append("Parameters:");
							log4jParamters_tFileOutputExcel_1.append("VERSION_2007" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("FILENAME" + " = "
									+ "\"C:/Program Files (x86)/Talend-Studio/studio/workspace/out.xls\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("SHEETNAME" + " = " + "\"Sheet1\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("INCLUDEHEADER" + " = " + "false");
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
									+ ("false") + ", SCHEMA_COLUMN=" + ("NumeroPedido") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("CodigoCliente") + "}, {IS_AUTO_SIZE="
									+ ("false") + ", SCHEMA_COLUMN=" + ("DataEmissao") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PrevisaoEntrega") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("TabelaPreco") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CondicaoPagamento") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("TipoOperacao") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Representante") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SituacaoPedido") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SituacaoCorporativa") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SequenciaItem") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CodigoProduto") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeProduto") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeAtendida") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("QuantidadeCancelada") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PrecoUnitario") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PrecoLiquido") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CodigoTES") + "}, {IS_AUTO_SIZE=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("Operacao") + "}]");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputExcel_1.append(" | ");
							log4jParamters_tFileOutputExcel_1.append("DELETE_EMPTYFILE" + " = " + "false");
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

				int nb_line_tFileOutputExcel_1 = 0;

				String fileName_tFileOutputExcel_1 = "C:/Program Files (x86)/Talend-Studio/studio/workspace/out.xls";
				java.io.File file_tFileOutputExcel_1 = new java.io.File(fileName_tFileOutputExcel_1);
				boolean isFileGenerated_tFileOutputExcel_1 = true;
//create directory only if not exists
				java.io.File parentFile_tFileOutputExcel_1 = file_tFileOutputExcel_1.getParentFile();
				if (parentFile_tFileOutputExcel_1 != null && !parentFile_tFileOutputExcel_1.exists()) {

					log.info("tFileOutputExcel_1 - Creating directory '"
							+ parentFile_tFileOutputExcel_1.getCanonicalPath() + "'.");

					parentFile_tFileOutputExcel_1.mkdirs();

					log.info("tFileOutputExcel_1 - Create directory '"
							+ parentFile_tFileOutputExcel_1.getCanonicalPath() + "' has succeeded.");

				}

				jxl.write.WritableWorkbook writeableWorkbook_tFileOutputExcel_1 = null;
				jxl.write.WritableSheet writableSheet_tFileOutputExcel_1 = null;

				jxl.WorkbookSettings workbookSettings_tFileOutputExcel_1 = new jxl.WorkbookSettings();
				workbookSettings_tFileOutputExcel_1.setEncoding("ISO-8859-15");
				writeableWorkbook_tFileOutputExcel_1 = new jxl.write.biff.WritableWorkbookImpl(
						new java.io.BufferedOutputStream(new java.io.FileOutputStream(fileName_tFileOutputExcel_1)),
						true, workbookSettings_tFileOutputExcel_1);

				writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.getSheet("Sheet1");
				if (writableSheet_tFileOutputExcel_1 == null) {
					writableSheet_tFileOutputExcel_1 = writeableWorkbook_tFileOutputExcel_1.createSheet("Sheet1",
							writeableWorkbook_tFileOutputExcel_1.getNumberOfSheets());
				}

				// modif start
				int startRowNum_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1.getRows();
				// modif end

				int[] fitWidth_tFileOutputExcel_1 = new int[19];
				for (int i_tFileOutputExcel_1 = 0; i_tFileOutputExcel_1 < 19; i_tFileOutputExcel_1++) {
					int fitCellViewSize_tFileOutputExcel_1 = writableSheet_tFileOutputExcel_1
							.getColumnView(i_tFileOutputExcel_1).getSize();
					fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] = fitCellViewSize_tFileOutputExcel_1 / 256;
					if (fitCellViewSize_tFileOutputExcel_1 % 256 != 0) {
						fitWidth_tFileOutputExcel_1[i_tFileOutputExcel_1] += 1;
					}
				}

				final jxl.write.WritableCellFormat cell_format_DataEmissao_tFileOutputExcel_1 = new jxl.write.WritableCellFormat(
						new jxl.write.DateFormat("dd-MM-yyyy"));
				final jxl.write.WritableCellFormat cell_format_PrevisaoEntrega_tFileOutputExcel_1 = new jxl.write.WritableCellFormat(
						new jxl.write.DateFormat("dd-MM-yyyy"));

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

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
							log4jParamters_tLogRow_1.append("PRINT_HEADER" + " = " + "false");
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

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tFilterRow_8 begin ] start
				 */

				ok_Hash.put("tFilterRow_8", false);
				start_Hash.put("tFilterRow_8", System.currentTimeMillis());

				currentComponent = "tFilterRow_8";

				cLabel = "Filtor Tipo Operacao";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Carteira");

				int tos_count_tFilterRow_8 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_8 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_8 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_8 = new StringBuilder();
							log4jParamters_tFilterRow_8.append("Parameters:");
							log4jParamters_tFilterRow_8.append("LOGICAL_OP" + " = " + "||");
							log4jParamters_tFilterRow_8.append(" | ");
							log4jParamters_tFilterRow_8.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==")
									+ ", RVALUE=" + ("\"Aberto\"") + ", INPUT_COLUMN=" + ("SituacaoPedido")
									+ ", FUNCTION=" + ("") + "}, {OPERATOR=" + ("==") + ", RVALUE=" + ("\"Bloqueado\"")
									+ ", INPUT_COLUMN=" + ("SituacaoPedido") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_8.append(" | ");
							log4jParamters_tFilterRow_8.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_8.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_8 - " + (log4jParamters_tFilterRow_8));
						}
					}
					new BytesLimit65535_tFilterRow_8().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_8", "Filtor Tipo Operacao", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_8 = 0;
				int nb_line_ok_tFilterRow_8 = 0;
				int nb_line_reject_tFilterRow_8 = 0;

				class Operator_tFilterRow_8 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_8(String unionFlag) {
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
				 * [tFilterRow_8 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				cLabel = "Jun\u00E7\u00E3o";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Capa_pedido");

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
					talendJobLog.addCM("tMap_1", "Jun\u00E7\u00E3o", "tMap");
					talendJobLogProcess(globalMap);
				}

// ###############################
// # Lookup's keys initialization
				int count_Capa_pedido_tMap_1 = 0;

				int count_Itens_pedido_tMap_1 = 0;

				int count_TIpo_operacao_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<Itens_pedidoStruct> tHash_Lookup_Itens_pedido = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<Itens_pedidoStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<Itens_pedidoStruct>) globalMap
						.get("tHash_Lookup_Itens_pedido"));

				Itens_pedidoStruct Itens_pedidoHashKey = new Itens_pedidoStruct();
				Itens_pedidoStruct Itens_pedidoDefault = new Itens_pedidoStruct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<TIpo_operacaoStruct> tHash_Lookup_TIpo_operacao = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<TIpo_operacaoStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<TIpo_operacaoStruct>) globalMap
						.get("tHash_Lookup_TIpo_operacao"));

				TIpo_operacaoStruct TIpo_operacaoHashKey = new TIpo_operacaoStruct();
				TIpo_operacaoStruct TIpo_operacaoDefault = new TIpo_operacaoStruct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_Carteira_tMap_1 = 0;

				CarteiraStruct Carteira_tmp = new CarteiraStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				ok_Hash.put("tDBInput_2", false);
				start_Hash.put("tDBInput_2", System.currentTimeMillis());

				currentComponent = "tDBInput_2";

				cLabel = "Capa_pedido";

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
									"enc:routine.encryption.key.v1:+76/KWcnl5jbU4gAuN9rXy1m74+yM+ljy7/b/ZIhQxYYG4co4euRVcwS")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TABLE" + " = " + "\"DB_PEDIDO\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERY" + " = "
									+ "\"SELECT     DB_PED_NRO AS NumeroPedido,    DB_PED_CLIENTE AS CodigoCliente,    DB_PED_DT_EMISSAO AS DataEmissao,    DB_PED_DT_PREVENT AS PrevisaoEntrega,    DB_PED_LISTA_PRECO AS ListaPreco,    DB_PED_COND_PGTO AS CondicaoPagamento,    DB_PED_OPERACAO AS Operacao,    DB_PED_PORTADOR AS DbPedPortador,    DB_PED_COD_MOEDA AS DbPedCodMoeda,    DB_PED_TAB_FINO AS DbPedTabFino,    DB_PED_TAB_FINDO AS DbPedTabFindo,    DB_PED_TAB_FINI AS DbPedTabFini,    DB_PED_TAB_FINDI AS DbPedTabFindi,    DB_PED_REPRES AS Representante,    DB_PED_COMISO AS DbPedComiso,    DB_PED_COMISI AS DbPedComisi,    DB_PED_PERC_FRETE AS DbPedPercFrete,    DB_PED_COD_TRANSP AS DbPedCodTransp,    DB_PED_COD_REDESP AS DbPedCodRedesp,    DB_PED_TIPO_FRETE AS DbPedTipoFrete,    DB_PED_ORD_COMPRA AS DbPedOrdCompra,    DB_PED_DESCTO AS DbPedDescto,    DB_PED_DESCTO2 AS DbPedDescto2,    DB_PED_DESCTO3 AS DbPedDescto3,    DB_PED_DESCTO4 AS DbPedDescto4,    DB_PED_DESCTO5 AS DbPedDescto5,    DB_PED_DESCTO6 AS DbPedDescto6,    DB_PED_DESCTO7 AS DbPedDescto7,    DB_PED_ACRESCIMO AS DbPedAcrescimo,    DB_PED_MOTIVO_BLOQ AS DbPedMotivoBloq,    DB_PED_SITUACAO AS SituacaoPedido,    DB_PED_LIB_USUS AS DbPedLibUsus,    DB_PED_LIB_MOTS AS DbPedLibMots,    DB_PED_LIB_DATAS AS DbPedLibDatas,    DB_PED_DATA_ALTER AS DbPedDataAlter,    DB_PED_DATA_ENVIO AS DbPedDataEnvio,    DB_PED_OBSERV AS DbPedObserv,    DB_PED_TEXTO AS DbPedTexto,    DB_PED_NRO_ORIG AS DbPedNroOrig,    DB_PED_TIPO_PRECO AS DbPedTipoPreco,    DB_PED_FATUR AS DbPedFatur,    DB_PED_DATA_RECEB AS DbPedDataReceb,    DB_Ped_NroRE AS DbPedNroRe,    DB_Ped_End_Pgto AS DbPedEndPgto,    DB_Ped_Meio_Transp AS DbPedMeioTransp,    DB_Ped_Lcl_Embarq AS DbPedLclEmbarq,    DB_Ped_Lcl_Desemb AS DbPedLclDesemb,    DB_Ped_Vlr_Frete AS DbPedVlrFrete,    DB_Ped_Vlr_Seguro AS DbPedVlrSeguro,    DB_Ped_Ent_Ender AS DbPedEntEnder,    DB_Ped_Ent_Bairro AS DbPedEntBairro,    DB_Ped_Ent_Cidade AS DbPedEntCidade,    DB_Ped_Ent_CEP AS DbPedEntCep,    DB_Ped_Ent_UF AS DbPedEntUf,    DB_Ped_Ent_CGCMF AS DbPedEntCgcmf,    DB_Ped_Ent_CGCTE AS DbPedEntCgcte,    DB_Ped_Empresa AS DbPedEmpresa,    DB_Ped_CDV AS DbPedCdv,    DB_Ped_Tipo AS DbPedTipo,    DB_Ped_Periodo AS DbPedPeriodo,    DB_Ped_Preposto AS DbPedPreposto,    DB_Ped_nrpevda AS DbPedNrpevda,    DB_PED_SEQ_ENTREGA AS DbPedSeqEntrega,    DB_PED_REDESPACHO AS DbPedRedespacho,    DB_PED_DT_PRODUC AS DbPedDtProduc,    DB_PED_SITCORP AS SituacaoCorporativa,    DB_PED_PRZPGTO AS DbPedPrzpgto,    DB_PED_FORAREGRA AS DbPedForaregra,    DB_PED_DESCTO_VLR AS DbPedDesctoVlr,    DB_Ped_Ent_Razao AS DbPedEntRazao,    DB_Ped_Cotacao AS DbPedCotacao,    DB_Ped_CliFinal AS DbPedCliFinal,    Db_Ped_NUtiPBoni AS DbPedNUtiPBoni,    DB_PED_ESTRUTURA AS DbPedEstrutura,    DB_PED_ENT_COMPL AS DbPedEntCompl,    DB_PED_ENT_PONTOREFER AS DbPedEntPontoRefer,    DB_PED_ENT_CXPOSTAL AS DbPedEntCxPostal,    DB_PED_ENT_PAIS AS DbPedEntPais,    DB_PED_ENT_TELEFONE AS DbPedEntTelefone,    DB_PED_ENT_RAMAL AS DbPedEntRamal,    DB_PED_DESCTO_CAMPANHA AS DbPedDesctoCampanha,    DB_PED_CANCELANDO AS DbPedCancelando,    DB_PED_DATAHORA AS DbPedDataHora,    DB_PED_MOT_DESC AS DbPedMotDesc,    DB_PED_NUMERO_OC AS DbPedNumeroOC,    DB_PED_DT_REGRA_CORTE AS DbPedDtRegraCorte  FROM DB_PEDIDO    WHERE DB_PED_DT_EMISSAO >= GETDATE() - 90;\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("NumeroPedido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CodigoCliente") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DataEmissao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PrevisaoEntrega") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ListaPreco")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CondicaoPagamento") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Operacao") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedPortador") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedCodMoeda") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedTabFino") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedTabFindo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedTabFini") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedTabFindi") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Representante") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedComiso") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedComisi") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedPercFrete")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedCodTransp") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DbPedCodRedesp") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedTipoFrete") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedOrdCompra") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDescto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedDescto2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedDescto3")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedDescto4") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DbPedDescto5") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDescto6") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDescto7") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedAcrescimo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedMotivoBloq") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SituacaoPedido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedLibUsus") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedLibMots") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedLibDatas") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDataAlter") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDataEnvio") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedObserv") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedTexto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedNroOrig")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedTipoPreco") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DbPedFatur") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDataReceb") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedNroRe") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedEndPgto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedMeioTransp") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedLclEmbarq") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedLclDesemb") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedVlrFrete") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedVlrSeguro") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedEntEnder") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedEntBairro") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedEntCidade") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedEntCep")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedEntUf") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DbPedEntCgcmf") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntCgcte") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEmpresa") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedCdv") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DbPedTipo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedPeriodo")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DbPedPreposto") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DbPedNrpevda") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedSeqEntrega") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedRedespacho") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDtProduc") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SituacaoCorporativa") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedPrzpgto") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedForaregra") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDesctoVlr") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntRazao") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedCotacao") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedCliFinal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedNUtiPBoni") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEstrutura") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntCompl") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntPontoRefer") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntCxPostal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntPais") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntTelefone") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedEntRamal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDesctoCampanha") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedCancelando") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDataHora") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedMotDesc") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedNumeroOC") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DbPedDtRegraCorte") + "}]");
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
					talendJobLog.addCM("tDBInput_2", "Capa_pedido", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:dql9Q7hQshU/DL/DBeDGR6DTYOf8X+3Mjmy2xFBfd1OXmueeEU4dcGmc");

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

				String dbquery_tDBInput_2 = "SELECT \n  DB_PED_NRO AS NumeroPedido,\n  DB_PED_CLIENTE AS CodigoCliente,\n  DB_PED_DT_EMISSAO AS DataEmissao,\n  DB_P"
						+ "ED_DT_PREVENT AS PrevisaoEntrega,\n  DB_PED_LISTA_PRECO AS ListaPreco,\n  DB_PED_COND_PGTO AS CondicaoPagamento,\n  DB_P"
						+ "ED_OPERACAO AS Operacao,\n  DB_PED_PORTADOR AS DbPedPortador,\n  DB_PED_COD_MOEDA AS DbPedCodMoeda,\n  DB_PED_TAB_FINO A"
						+ "S DbPedTabFino,\n  DB_PED_TAB_FINDO AS DbPedTabFindo,\n  DB_PED_TAB_FINI AS DbPedTabFini,\n  DB_PED_TAB_FINDI AS DbPedTa"
						+ "bFindi,\n  DB_PED_REPRES AS Representante,\n  DB_PED_COMISO AS DbPedComiso,\n  DB_PED_COMISI AS DbPedComisi,\n  DB_PED_P"
						+ "ERC_FRETE AS DbPedPercFrete,\n  DB_PED_COD_TRANSP AS DbPedCodTransp,\n  DB_PED_COD_REDESP AS DbPedCodRedesp,\n  DB_PED_T"
						+ "IPO_FRETE AS DbPedTipoFrete,\n  DB_PED_ORD_COMPRA AS DbPedOrdCompra,\n  DB_PED_DESCTO AS DbPedDescto,\n  DB_PED_DESCTO2 "
						+ "AS DbPedDescto2,\n  DB_PED_DESCTO3 AS DbPedDescto3,\n  DB_PED_DESCTO4 AS DbPedDescto4,\n  DB_PED_DESCTO5 AS DbPedDescto5"
						+ ",\n  DB_PED_DESCTO6 AS DbPedDescto6,\n  DB_PED_DESCTO7 AS DbPedDescto7,\n  DB_PED_ACRESCIMO AS DbPedAcrescimo,\n  DB_PED"
						+ "_MOTIVO_BLOQ AS DbPedMotivoBloq,\n  DB_PED_SITUACAO AS SituacaoPedido,\n  DB_PED_LIB_USUS AS DbPedLibUsus,\n  DB_PED_LIB"
						+ "_MOTS AS DbPedLibMots,\n  DB_PED_LIB_DATAS AS DbPedLibDatas,\n  DB_PED_DATA_ALTER AS DbPedDataAlter,\n  DB_PED_DATA_ENVI"
						+ "O AS DbPedDataEnvio,\n  DB_PED_OBSERV AS DbPedObserv,\n  DB_PED_TEXTO AS DbPedTexto,\n  DB_PED_NRO_ORIG AS DbPedNroOrig,"
						+ "\n  DB_PED_TIPO_PRECO AS DbPedTipoPreco,\n  DB_PED_FATUR AS DbPedFatur,\n  DB_PED_DATA_RECEB AS DbPedDataReceb,\n  DB_Pe"
						+ "d_NroRE AS DbPedNroRe,\n  DB_Ped_End_Pgto AS DbPedEndPgto,\n  DB_Ped_Meio_Transp AS DbPedMeioTransp,\n  DB_Ped_Lcl_Embar"
						+ "q AS DbPedLclEmbarq,\n  DB_Ped_Lcl_Desemb AS DbPedLclDesemb,\n  DB_Ped_Vlr_Frete AS DbPedVlrFrete,\n  DB_Ped_Vlr_Seguro "
						+ "AS DbPedVlrSeguro,\n  DB_Ped_Ent_Ender AS DbPedEntEnder,\n  DB_Ped_Ent_Bairro AS DbPedEntBairro,\n  DB_Ped_Ent_Cidade AS"
						+ " DbPedEntCidade,\n  DB_Ped_Ent_CEP AS DbPedEntCep,\n  DB_Ped_Ent_UF AS DbPedEntUf,\n  DB_Ped_Ent_CGCMF AS DbPedEntCgcmf,"
						+ "\n  DB_Ped_Ent_CGCTE AS DbPedEntCgcte,\n  DB_Ped_Empresa AS DbPedEmpresa,\n  DB_Ped_CDV AS DbPedCdv,\n  DB_Ped_Tipo AS D"
						+ "bPedTipo,\n  DB_Ped_Periodo AS DbPedPeriodo,\n  DB_Ped_Preposto AS DbPedPreposto,\n  DB_Ped_nrpevda AS DbPedNrpevda,\n  "
						+ "DB_PED_SEQ_ENTREGA AS DbPedSeqEntrega,\n  DB_PED_REDESPACHO AS DbPedRedespacho,\n  DB_PED_DT_PRODUC AS DbPedDtProduc,\n "
						+ " DB_PED_SITCORP AS SituacaoCorporativa,\n  DB_PED_PRZPGTO AS DbPedPrzpgto,\n  DB_PED_FORAREGRA AS DbPedForaregra,\n  DB_"
						+ "PED_DESCTO_VLR AS DbPedDesctoVlr,\n  DB_Ped_Ent_Razao AS DbPedEntRazao,\n  DB_Ped_Cotacao AS DbPedCotacao,\n  DB_Ped_Cli"
						+ "Final AS DbPedCliFinal,\n  Db_Ped_NUtiPBoni AS DbPedNUtiPBoni,\n  DB_PED_ESTRUTURA AS DbPedEstrutura,\n  DB_PED_ENT_COMP"
						+ "L AS DbPedEntCompl,\n  DB_PED_ENT_PONTOREFER AS DbPedEntPontoRefer,\n  DB_PED_ENT_CXPOSTAL AS DbPedEntCxPostal,\n  DB_PE"
						+ "D_ENT_PAIS AS DbPedEntPais,\n  DB_PED_ENT_TELEFONE AS DbPedEntTelefone,\n  DB_PED_ENT_RAMAL AS DbPedEntRamal,\n  DB_PED_"
						+ "DESCTO_CAMPANHA AS DbPedDesctoCampanha,\n  DB_PED_CANCELANDO AS DbPedCancelando,\n  DB_PED_DATAHORA AS DbPedDataHora,\n "
						+ " DB_PED_MOT_DESC AS DbPedMotDesc,\n  DB_PED_NUMERO_OC AS DbPedNumeroOC,\n  DB_PED_DT_REGRA_CORTE AS DbPedDtRegraCorte\nF"
						+ "ROM DB_PEDIDO\n  WHERE DB_PED_DT_EMISSAO >= GETDATE() - 90;";

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
							Capa_pedido.NumeroPedido = null;
						} else {

							Capa_pedido.NumeroPedido = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.NumeroPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							Capa_pedido.CodigoCliente = null;
						} else {

							Capa_pedido.CodigoCliente = rs_tDBInput_2.getDouble(2);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.CodigoCliente = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							Capa_pedido.DataEmissao = null;
						} else {

							Capa_pedido.DataEmissao = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 3);

						}
						if (colQtyInRs_tDBInput_2 < 4) {
							Capa_pedido.PrevisaoEntrega = null;
						} else {

							Capa_pedido.PrevisaoEntrega = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2,
									4);

						}
						if (colQtyInRs_tDBInput_2 < 5) {
							Capa_pedido.ListaPreco = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(5);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.ListaPreco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.ListaPreco = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.ListaPreco = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 6) {
							Capa_pedido.CondicaoPagamento = null;
						} else {

							Capa_pedido.CondicaoPagamento = rs_tDBInput_2.getInt(6);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.CondicaoPagamento = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 7) {
							Capa_pedido.Operacao = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(7);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.Operacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.Operacao = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.Operacao = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 8) {
							Capa_pedido.DbPedPortador = null;
						} else {

							Capa_pedido.DbPedPortador = rs_tDBInput_2.getInt(8);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedPortador = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 9) {
							Capa_pedido.DbPedCodMoeda = null;
						} else {

							Capa_pedido.DbPedCodMoeda = rs_tDBInput_2.getInt(9);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCodMoeda = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 10) {
							Capa_pedido.DbPedTabFino = null;
						} else {

							Capa_pedido.DbPedTabFino = rs_tDBInput_2.getShort(10);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTabFino = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 11) {
							Capa_pedido.DbPedTabFindo = null;
						} else {

							Capa_pedido.DbPedTabFindo = rs_tDBInput_2.getShort(11);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTabFindo = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 12) {
							Capa_pedido.DbPedTabFini = null;
						} else {

							Capa_pedido.DbPedTabFini = rs_tDBInput_2.getShort(12);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTabFini = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 13) {
							Capa_pedido.DbPedTabFindi = null;
						} else {

							Capa_pedido.DbPedTabFindi = rs_tDBInput_2.getShort(13);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTabFindi = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 14) {
							Capa_pedido.Representante = null;
						} else {

							Capa_pedido.Representante = rs_tDBInput_2.getInt(14);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.Representante = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 15) {
							Capa_pedido.DbPedComiso = null;
						} else {

							Capa_pedido.DbPedComiso = rs_tDBInput_2.getDouble(15);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedComiso = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 16) {
							Capa_pedido.DbPedComisi = null;
						} else {

							Capa_pedido.DbPedComisi = rs_tDBInput_2.getDouble(16);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedComisi = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 17) {
							Capa_pedido.DbPedPercFrete = null;
						} else {

							Capa_pedido.DbPedPercFrete = rs_tDBInput_2.getDouble(17);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedPercFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 18) {
							Capa_pedido.DbPedCodTransp = null;
						} else {

							Capa_pedido.DbPedCodTransp = rs_tDBInput_2.getDouble(18);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCodTransp = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 19) {
							Capa_pedido.DbPedCodRedesp = null;
						} else {

							Capa_pedido.DbPedCodRedesp = rs_tDBInput_2.getDouble(19);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCodRedesp = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 20) {
							Capa_pedido.DbPedTipoFrete = null;
						} else {

							Capa_pedido.DbPedTipoFrete = rs_tDBInput_2.getShort(20);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTipoFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 21) {
							Capa_pedido.DbPedOrdCompra = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(21);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedOrdCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedOrdCompra = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedOrdCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 22) {
							Capa_pedido.DbPedDescto = null;
						} else {

							Capa_pedido.DbPedDescto = rs_tDBInput_2.getDouble(22);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 23) {
							Capa_pedido.DbPedDescto2 = null;
						} else {

							Capa_pedido.DbPedDescto2 = rs_tDBInput_2.getDouble(23);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto2 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 24) {
							Capa_pedido.DbPedDescto3 = null;
						} else {

							Capa_pedido.DbPedDescto3 = rs_tDBInput_2.getDouble(24);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto3 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 25) {
							Capa_pedido.DbPedDescto4 = null;
						} else {

							Capa_pedido.DbPedDescto4 = rs_tDBInput_2.getDouble(25);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto4 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 26) {
							Capa_pedido.DbPedDescto5 = null;
						} else {

							Capa_pedido.DbPedDescto5 = rs_tDBInput_2.getDouble(26);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto5 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 27) {
							Capa_pedido.DbPedDescto6 = null;
						} else {

							Capa_pedido.DbPedDescto6 = rs_tDBInput_2.getDouble(27);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto6 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 28) {
							Capa_pedido.DbPedDescto7 = null;
						} else {

							Capa_pedido.DbPedDescto7 = rs_tDBInput_2.getDouble(28);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDescto7 = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 29) {
							Capa_pedido.DbPedAcrescimo = null;
						} else {

							Capa_pedido.DbPedAcrescimo = rs_tDBInput_2.getDouble(29);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedAcrescimo = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 30) {
							Capa_pedido.DbPedMotivoBloq = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(30);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(30).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedMotivoBloq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedMotivoBloq = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedMotivoBloq = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 31) {
							Capa_pedido.SituacaoPedido = null;
						} else {

							Capa_pedido.SituacaoPedido = rs_tDBInput_2.getShort(31);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.SituacaoPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 32) {
							Capa_pedido.DbPedLibUsus = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(32);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(32).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedLibUsus = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedLibUsus = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedLibUsus = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 33) {
							Capa_pedido.DbPedLibMots = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(33);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedLibMots = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedLibMots = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedLibMots = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 34) {
							Capa_pedido.DbPedLibDatas = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(34);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedLibDatas = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedLibDatas = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedLibDatas = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 35) {
							Capa_pedido.DbPedDataAlter = null;
						} else {

							Capa_pedido.DbPedDataAlter = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2,
									35);

						}
						if (colQtyInRs_tDBInput_2 < 36) {
							Capa_pedido.DbPedDataEnvio = null;
						} else {

							Capa_pedido.DbPedDataEnvio = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2,
									36);

						}
						if (colQtyInRs_tDBInput_2 < 37) {
							Capa_pedido.DbPedObserv = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(37);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(37).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedObserv = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedObserv = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedObserv = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 38) {
							Capa_pedido.DbPedTexto = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(38);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedTexto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedTexto = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedTexto = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 39) {
							Capa_pedido.DbPedNroOrig = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(39);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(39).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedNroOrig = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedNroOrig = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedNroOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 40) {
							Capa_pedido.DbPedTipoPreco = null;
						} else {

							Capa_pedido.DbPedTipoPreco = rs_tDBInput_2.getShort(40);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedTipoPreco = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 41) {
							Capa_pedido.DbPedFatur = null;
						} else {

							Capa_pedido.DbPedFatur = rs_tDBInput_2.getShort(41);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedFatur = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 42) {
							Capa_pedido.DbPedDataReceb = null;
						} else {

							Capa_pedido.DbPedDataReceb = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2,
									42);

						}
						if (colQtyInRs_tDBInput_2 < 43) {
							Capa_pedido.DbPedNroRe = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(43);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(43).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedNroRe = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedNroRe = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedNroRe = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 44) {
							Capa_pedido.DbPedEndPgto = null;
						} else {

							Capa_pedido.DbPedEndPgto = rs_tDBInput_2.getInt(44);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedEndPgto = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 45) {
							Capa_pedido.DbPedMeioTransp = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(45);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(45).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedMeioTransp = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedMeioTransp = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedMeioTransp = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 46) {
							Capa_pedido.DbPedLclEmbarq = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(46);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(46).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedLclEmbarq = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedLclEmbarq = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedLclEmbarq = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 47) {
							Capa_pedido.DbPedLclDesemb = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(47);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(47).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedLclDesemb = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedLclDesemb = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedLclDesemb = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 48) {
							Capa_pedido.DbPedVlrFrete = null;
						} else {

							Capa_pedido.DbPedVlrFrete = rs_tDBInput_2.getDouble(48);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedVlrFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 49) {
							Capa_pedido.DbPedVlrSeguro = null;
						} else {

							Capa_pedido.DbPedVlrSeguro = rs_tDBInput_2.getDouble(49);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedVlrSeguro = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 50) {
							Capa_pedido.DbPedEntEnder = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(50);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(50).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntEnder = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntEnder = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntEnder = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 51) {
							Capa_pedido.DbPedEntBairro = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(51);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(51).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntBairro = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntBairro = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntBairro = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 52) {
							Capa_pedido.DbPedEntCidade = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(52);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(52).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCidade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCidade = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCidade = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 53) {
							Capa_pedido.DbPedEntCep = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(53);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCep = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCep = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCep = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 54) {
							Capa_pedido.DbPedEntUf = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(54);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(54).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntUf = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntUf = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntUf = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 55) {
							Capa_pedido.DbPedEntCgcmf = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(55);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(55).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCgcmf = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCgcmf = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCgcmf = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 56) {
							Capa_pedido.DbPedEntCgcte = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(56);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(56).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCgcte = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCgcte = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCgcte = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 57) {
							Capa_pedido.DbPedEmpresa = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(57);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(57).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEmpresa = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEmpresa = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEmpresa = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 58) {
							Capa_pedido.DbPedCdv = null;
						} else {

							Capa_pedido.DbPedCdv = rs_tDBInput_2.getDouble(58);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCdv = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 59) {
							Capa_pedido.DbPedTipo = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(59);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(59).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedTipo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedTipo = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedTipo = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 60) {
							Capa_pedido.DbPedPeriodo = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(60);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(60).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedPeriodo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedPeriodo = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedPeriodo = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 61) {
							Capa_pedido.DbPedPreposto = null;
						} else {

							Capa_pedido.DbPedPreposto = rs_tDBInput_2.getInt(61);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedPreposto = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 62) {
							Capa_pedido.DbPedNrpevda = null;
						} else {

							Capa_pedido.DbPedNrpevda = rs_tDBInput_2.getInt(62);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedNrpevda = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 63) {
							Capa_pedido.DbPedSeqEntrega = null;
						} else {

							Capa_pedido.DbPedSeqEntrega = rs_tDBInput_2.getInt(63);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedSeqEntrega = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 64) {
							Capa_pedido.DbPedRedespacho = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(64);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(64).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedRedespacho = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedRedespacho = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedRedespacho = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 65) {
							Capa_pedido.DbPedDtProduc = null;
						} else {

							Capa_pedido.DbPedDtProduc = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 65);

						}
						if (colQtyInRs_tDBInput_2 < 66) {
							Capa_pedido.SituacaoCorporativa = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(66);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(66).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.SituacaoCorporativa = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.SituacaoCorporativa = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.SituacaoCorporativa = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 67) {
							Capa_pedido.DbPedPrzpgto = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(67);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(67).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedPrzpgto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedPrzpgto = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedPrzpgto = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 68) {
							Capa_pedido.DbPedForaregra = null;
						} else {

							Capa_pedido.DbPedForaregra = rs_tDBInput_2.getShort(68);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedForaregra = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 69) {
							Capa_pedido.DbPedDesctoVlr = null;
						} else {

							Capa_pedido.DbPedDesctoVlr = rs_tDBInput_2.getDouble(69);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDesctoVlr = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 70) {
							Capa_pedido.DbPedEntRazao = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(70);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(70).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntRazao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntRazao = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntRazao = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 71) {
							Capa_pedido.DbPedCotacao = null;
						} else {

							Capa_pedido.DbPedCotacao = rs_tDBInput_2.getDouble(71);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCotacao = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 72) {
							Capa_pedido.DbPedCliFinal = null;
						} else {

							Capa_pedido.DbPedCliFinal = rs_tDBInput_2.getDouble(72);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCliFinal = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 73) {
							Capa_pedido.DbPedNUtiPBoni = null;
						} else {

							Capa_pedido.DbPedNUtiPBoni = rs_tDBInput_2.getShort(73);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedNUtiPBoni = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 74) {
							Capa_pedido.DbPedEstrutura = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(74);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(74).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEstrutura = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEstrutura = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEstrutura = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 75) {
							Capa_pedido.DbPedEntCompl = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(75);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(75).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCompl = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCompl = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCompl = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 76) {
							Capa_pedido.DbPedEntPontoRefer = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(76);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(76).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntPontoRefer = FormatterUtils
											.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntPontoRefer = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntPontoRefer = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 77) {
							Capa_pedido.DbPedEntCxPostal = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(77);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(77).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntCxPostal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntCxPostal = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntCxPostal = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 78) {
							Capa_pedido.DbPedEntPais = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(78);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(78).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntPais = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntPais = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntPais = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 79) {
							Capa_pedido.DbPedEntTelefone = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(79);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(79).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntTelefone = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntTelefone = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntTelefone = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 80) {
							Capa_pedido.DbPedEntRamal = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(80);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(80).toUpperCase(java.util.Locale.ENGLISH))) {
									Capa_pedido.DbPedEntRamal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									Capa_pedido.DbPedEntRamal = tmpContent_tDBInput_2.trim();
								}
							} else {
								Capa_pedido.DbPedEntRamal = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 81) {
							Capa_pedido.DbPedDesctoCampanha = null;
						} else {

							Capa_pedido.DbPedDesctoCampanha = rs_tDBInput_2.getDouble(81);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedDesctoCampanha = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 82) {
							Capa_pedido.DbPedCancelando = null;
						} else {

							Capa_pedido.DbPedCancelando = rs_tDBInput_2.getInt(82);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedCancelando = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 83) {
							Capa_pedido.DbPedDataHora = null;
						} else {

							Capa_pedido.DbPedDataHora = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 83);

						}
						if (colQtyInRs_tDBInput_2 < 84) {
							Capa_pedido.DbPedMotDesc = null;
						} else {

							Capa_pedido.DbPedMotDesc = rs_tDBInput_2.getInt(84);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedMotDesc = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 85) {
							Capa_pedido.DbPedNumeroOC = null;
						} else {

							Capa_pedido.DbPedNumeroOC = rs_tDBInput_2.getInt(85);
							if (rs_tDBInput_2.wasNull()) {
								Capa_pedido.DbPedNumeroOC = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 86) {
							Capa_pedido.DbPedDtRegraCorte = null;
						} else {

							Capa_pedido.DbPedDtRegraCorte = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2,
									86);

						}

						log.debug("tDBInput_2 - Retrieving the record " + nb_line_tDBInput_2 + ".");

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Capa_pedido";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Capa_pedido";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						cLabel = "Jun\u00E7\u00E3o";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "Capa_pedido", "tDBInput_2", "Capa_pedido", "tMSSqlInput", "tMap_1",
								"Jun\u00E7\u00E3o", "tMap"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("Capa_pedido - " + (Capa_pedido == null ? "" : Capa_pedido.toLogString()));
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						Itens_pedidoStruct Itens_pedido = null;

						TIpo_operacaoStruct TIpo_operacao = null;

						// ###############################
						// # Input tables (lookups)

						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "Itens_pedido"
						///////////////////////////////////////////////

						boolean forceLoopItens_pedido = false;

						Itens_pedidoStruct Itens_pedidoObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

							hasCasePrimitiveKeyWithNull_tMap_1 = false;

							Itens_pedidoHashKey.NumeroPedido = Capa_pedido.NumeroPedido;

							Itens_pedidoHashKey.hashCodeDirty = true;

							tHash_Lookup_Itens_pedido.lookup(Itens_pedidoHashKey);

							if (!tHash_Lookup_Itens_pedido.hasNext()) { // G_TM_M_090

								forceLoopItens_pedido = true;

							} // G_TM_M_090

						} // G_TM_M_020

						else { // G 20 - G 21
							forceLoopItens_pedido = true;
						} // G 21

						while ((tHash_Lookup_Itens_pedido != null && tHash_Lookup_Itens_pedido.hasNext())
								|| forceLoopItens_pedido) { // G_TM_M_043

							// CALL close loop of lookup 'Itens_pedido'

							Itens_pedidoStruct fromLookup_Itens_pedido = null;
							Itens_pedido = Itens_pedidoDefault;

							if (!forceLoopItens_pedido) { // G 46

								fromLookup_Itens_pedido = tHash_Lookup_Itens_pedido.next();

								if (fromLookup_Itens_pedido != null) {
									Itens_pedido = fromLookup_Itens_pedido;
								}

							} // G 46

							forceLoopItens_pedido = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "TIpo_operacao"
							///////////////////////////////////////////////

							boolean forceLoopTIpo_operacao = false;

							TIpo_operacaoStruct TIpo_operacaoObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								TIpo_operacaoHashKey.Operacao = Capa_pedido.Operacao;

								TIpo_operacaoHashKey.hashCodeDirty = true;

								tHash_Lookup_TIpo_operacao.lookup(TIpo_operacaoHashKey);

								if (!tHash_Lookup_TIpo_operacao.hasNext()) { // G_TM_M_090

									forceLoopTIpo_operacao = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLoopTIpo_operacao = true;
							} // G 21

							while ((tHash_Lookup_TIpo_operacao != null && tHash_Lookup_TIpo_operacao.hasNext())
									|| forceLoopTIpo_operacao) { // G_TM_M_043

								// CALL close loop of lookup 'TIpo_operacao'

								TIpo_operacaoStruct fromLookup_TIpo_operacao = null;
								TIpo_operacao = TIpo_operacaoDefault;

								if (!forceLoopTIpo_operacao) { // G 46

									fromLookup_TIpo_operacao = tHash_Lookup_TIpo_operacao.next();

									if (fromLookup_TIpo_operacao != null) {
										TIpo_operacao = fromLookup_TIpo_operacao;
									}

								} // G 46

								forceLoopTIpo_operacao = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									Carteira = null;

// # Output table : 'Carteira'
									count_Carteira_tMap_1++;

									Carteira_tmp.NumeroPedido = Capa_pedido.NumeroPedido;
									Carteira_tmp.CodigoCliente = Capa_pedido.CodigoCliente;
									Carteira_tmp.DataEmissao = Capa_pedido.DataEmissao;
									Carteira_tmp.PrevisaoEntrega = Capa_pedido.PrevisaoEntrega;
									Carteira_tmp.TabelaPreco = Capa_pedido.ListaPreco;
									Carteira_tmp.CondicaoPagamento = Capa_pedido.CondicaoPagamento;
									Carteira_tmp.TipoOperacao = Capa_pedido.Operacao;
									Carteira_tmp.Representante = Capa_pedido.Representante;
									Carteira_tmp.SituacaoPedido = (Capa_pedido.SituacaoPedido == 0) ? "Aberto"
											: (Capa_pedido.SituacaoPedido == 1) ? "Bloqueado"
													: (Capa_pedido.SituacaoPedido == 2) ? "FaturadoParcial"
															: (Capa_pedido.SituacaoPedido == 4) ? "Valor"
																	: (Capa_pedido.SituacaoPedido == 9) ? "Bloqueado"
																			: "ND";
									Carteira_tmp.SituacaoCorporativa = (Capa_pedido.SituacaoCorporativa == "21") ? "Sim"
											: Capa_pedido.SituacaoCorporativa;
									Carteira_tmp.SequenciaItem = Itens_pedido.SequenciaItem;
									Carteira_tmp.CodigoProduto = Itens_pedido.CodigoProduto;
									Carteira_tmp.QuantidadeProduto = Itens_pedido.QuantidadeProduto;
									Carteira_tmp.QuantidadeAtendida = Itens_pedido.QuantidadeAtendida;
									Carteira_tmp.QuantidadeCancelada = Itens_pedido.QuantidadeCancelada;
									Carteira_tmp.PrecoUnitario = Itens_pedido.PrecoUnitario;
									Carteira_tmp.PrecoLiquido = Itens_pedido.PrecoLiquido;
									Carteira_tmp.CodigoTES = Itens_pedido.Operacao;
									Carteira_tmp.Operacao = TIpo_operacao.TipoOperacao;
									Carteira = Carteira_tmp;
									log.debug("tMap_1 - Outputting the record " + count_Carteira_tMap_1
											+ " of the output table 'Carteira'.");

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

								cLabel = "Jun\u00E7\u00E3o";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */

// Start of branch "Carteira"
								if (Carteira != null) {

									/**
									 * [tFilterRow_8 main ] start
									 */

									currentComponent = "tFilterRow_8";

									cLabel = "Filtor Tipo Operacao";

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "Carteira", "tMap_1", "Jun\u00E7\u00E3o", "tMap", "tFilterRow_8",
											"Filtor Tipo Operacao", "tFilterRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("Carteira - " + (Carteira == null ? "" : Carteira.toLogString()));
									}

									row1 = null;
									Operator_tFilterRow_8 ope_tFilterRow_8 = new Operator_tFilterRow_8("||");
									ope_tFilterRow_8.matches(
											(Carteira.SituacaoPedido == null ? false
													: Carteira.SituacaoPedido.compareTo("Aberto") == 0),
											"SituacaoPedido.compareTo(\"Aberto\") == 0 failed");
									ope_tFilterRow_8.matches(
											(Carteira.SituacaoPedido == null ? false
													: Carteira.SituacaoPedido.compareTo("Bloqueado") == 0),
											"SituacaoPedido.compareTo(\"Bloqueado\") == 0 failed");

									if (ope_tFilterRow_8.getMatchFlag()) {
										if (row1 == null) {
											row1 = new row1Struct();
										}
										row1.NumeroPedido = Carteira.NumeroPedido;
										row1.CodigoCliente = Carteira.CodigoCliente;
										row1.DataEmissao = Carteira.DataEmissao;
										row1.PrevisaoEntrega = Carteira.PrevisaoEntrega;
										row1.TabelaPreco = Carteira.TabelaPreco;
										row1.CondicaoPagamento = Carteira.CondicaoPagamento;
										row1.TipoOperacao = Carteira.TipoOperacao;
										row1.Representante = Carteira.Representante;
										row1.SituacaoPedido = Carteira.SituacaoPedido;
										row1.SituacaoCorporativa = Carteira.SituacaoCorporativa;
										row1.SequenciaItem = Carteira.SequenciaItem;
										row1.CodigoProduto = Carteira.CodigoProduto;
										row1.QuantidadeProduto = Carteira.QuantidadeProduto;
										row1.QuantidadeAtendida = Carteira.QuantidadeAtendida;
										row1.QuantidadeCancelada = Carteira.QuantidadeCancelada;
										row1.PrecoUnitario = Carteira.PrecoUnitario;
										row1.PrecoLiquido = Carteira.PrecoLiquido;
										row1.CodigoTES = Carteira.CodigoTES;
										row1.Operacao = Carteira.Operacao;
										log.debug("tFilterRow_8 - Process the record " + (nb_line_tFilterRow_8 + 1)
												+ ".");

										nb_line_ok_tFilterRow_8++;
									} else {
										nb_line_reject_tFilterRow_8++;
									}

									nb_line_tFilterRow_8++;

									tos_count_tFilterRow_8++;

									/**
									 * [tFilterRow_8 main ] stop
									 */

									/**
									 * [tFilterRow_8 process_data_begin ] start
									 */

									currentComponent = "tFilterRow_8";

									cLabel = "Filtor Tipo Operacao";

									/**
									 * [tFilterRow_8 process_data_begin ] stop
									 */

// Start of branch "row1"
									if (row1 != null) {

										/**
										 * [tLogRow_1 main ] start
										 */

										currentComponent = "tLogRow_1";

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row1", "tFilterRow_8", "Filtor Tipo Operacao", "tFilterRow",
												"tLogRow_1", "tLogRow_1", "tLogRow"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
										}

///////////////////////		

										strBuffer_tLogRow_1 = new StringBuilder();

										if (row1.NumeroPedido != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.NumeroPedido));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.CodigoCliente != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.formatUnwithE(row1.CodigoCliente));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.DataEmissao != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.format_Date(row1.DataEmissao, "dd-MM-yyyy"));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.PrevisaoEntrega != null) { //

											strBuffer_tLogRow_1.append(
													FormatterUtils.format_Date(row1.PrevisaoEntrega, "dd-MM-yyyy"));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.TabelaPreco != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.TabelaPreco));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.CondicaoPagamento != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.CondicaoPagamento));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.TipoOperacao != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.TipoOperacao));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.Representante != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.Representante));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.SituacaoPedido != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.SituacaoPedido));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.SituacaoCorporativa != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.SituacaoCorporativa));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.SequenciaItem != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.SequenciaItem));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.CodigoProduto != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.CodigoProduto));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.QuantidadeProduto != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.formatUnwithE(row1.QuantidadeProduto));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.QuantidadeAtendida != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.formatUnwithE(row1.QuantidadeAtendida));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.QuantidadeCancelada != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.formatUnwithE(row1.QuantidadeCancelada));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.PrecoUnitario != null) { //

											strBuffer_tLogRow_1
													.append(FormatterUtils.formatUnwithE(row1.PrecoUnitario));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.PrecoLiquido != null) { //

											strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(row1.PrecoLiquido));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.CodigoTES != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.CodigoTES));

										} //

										strBuffer_tLogRow_1.append("|");

										if (row1.Operacao != null) { //

											strBuffer_tLogRow_1.append(String.valueOf(row1.Operacao));

										} //

										if (globalMap.get("tLogRow_CONSOLE") != null) {
											consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap
													.get("tLogRow_CONSOLE");
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

										row3 = row1;

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
										 * [tFileOutputExcel_1 main ] start
										 */

										currentComponent = "tFileOutputExcel_1";

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row3", "tLogRow_1", "tLogRow_1", "tLogRow", "tFileOutputExcel_1",
												"tFileOutputExcel_1", "tFileOutputExcel"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
										}

										if (row3.NumeroPedido != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 0;

											jxl.write.WritableCell cell_0_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.NumeroPedido);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_0_tFileOutputExcel_1);
											int currentWith_0_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_0_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_0_tFileOutputExcel_1 = currentWith_0_tFileOutputExcel_1 > 10
													? 10
													: currentWith_0_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[0] = fitWidth_tFileOutputExcel_1[0] > currentWith_0_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[0]
													: currentWith_0_tFileOutputExcel_1 + 2;
										}

										if (row3.CodigoCliente != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 1;

											jxl.write.WritableCell cell_1_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.CodigoCliente);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_1_tFileOutputExcel_1);
											int currentWith_1_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_1_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_1_tFileOutputExcel_1 = currentWith_1_tFileOutputExcel_1 > 10
													? 10
													: currentWith_1_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[1] = fitWidth_tFileOutputExcel_1[1] > currentWith_1_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[1]
													: currentWith_1_tFileOutputExcel_1 + 2;
										}

										if (row3.DataEmissao != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 2;

											jxl.write.WritableCell cell_2_tFileOutputExcel_1 = new jxl.write.DateTime(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.DataEmissao, cell_format_DataEmissao_tFileOutputExcel_1);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_2_tFileOutputExcel_1);
											int currentWith_2_tFileOutputExcel_1 = cell_2_tFileOutputExcel_1
													.getContents().trim().length();
											currentWith_2_tFileOutputExcel_1 = 12;
											fitWidth_tFileOutputExcel_1[2] = fitWidth_tFileOutputExcel_1[2] > currentWith_2_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[2]
													: currentWith_2_tFileOutputExcel_1 + 2;
										}

										if (row3.PrevisaoEntrega != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 3;

											jxl.write.WritableCell cell_3_tFileOutputExcel_1 = new jxl.write.DateTime(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.PrevisaoEntrega,
													cell_format_PrevisaoEntrega_tFileOutputExcel_1);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_3_tFileOutputExcel_1);
											int currentWith_3_tFileOutputExcel_1 = cell_3_tFileOutputExcel_1
													.getContents().trim().length();
											currentWith_3_tFileOutputExcel_1 = 12;
											fitWidth_tFileOutputExcel_1[3] = fitWidth_tFileOutputExcel_1[3] > currentWith_3_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[3]
													: currentWith_3_tFileOutputExcel_1 + 2;
										}

										if (row3.TabelaPreco != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 4;

											jxl.write.WritableCell cell_4_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.TabelaPreco);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_4_tFileOutputExcel_1);
											int currentWith_4_tFileOutputExcel_1 = cell_4_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[4] = fitWidth_tFileOutputExcel_1[4] > currentWith_4_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[4]
													: currentWith_4_tFileOutputExcel_1 + 2;
										}

										if (row3.CondicaoPagamento != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 5;

											jxl.write.WritableCell cell_5_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.CondicaoPagamento);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_5_tFileOutputExcel_1);
											int currentWith_5_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_5_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_5_tFileOutputExcel_1 = currentWith_5_tFileOutputExcel_1 > 10
													? 10
													: currentWith_5_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[5] = fitWidth_tFileOutputExcel_1[5] > currentWith_5_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[5]
													: currentWith_5_tFileOutputExcel_1 + 2;
										}

										if (row3.TipoOperacao != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 6;

											jxl.write.WritableCell cell_6_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.TipoOperacao);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_6_tFileOutputExcel_1);
											int currentWith_6_tFileOutputExcel_1 = cell_6_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[6] = fitWidth_tFileOutputExcel_1[6] > currentWith_6_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[6]
													: currentWith_6_tFileOutputExcel_1 + 2;
										}

										if (row3.Representante != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 7;

											jxl.write.WritableCell cell_7_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.Representante);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_7_tFileOutputExcel_1);
											int currentWith_7_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_7_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_7_tFileOutputExcel_1 = currentWith_7_tFileOutputExcel_1 > 10
													? 10
													: currentWith_7_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[7] = fitWidth_tFileOutputExcel_1[7] > currentWith_7_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[7]
													: currentWith_7_tFileOutputExcel_1 + 2;
										}

										if (row3.SituacaoPedido != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 8;

											jxl.write.WritableCell cell_8_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.SituacaoPedido);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_8_tFileOutputExcel_1);
											int currentWith_8_tFileOutputExcel_1 = cell_8_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[8] = fitWidth_tFileOutputExcel_1[8] > currentWith_8_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[8]
													: currentWith_8_tFileOutputExcel_1 + 2;
										}

										if (row3.SituacaoCorporativa != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 9;

											jxl.write.WritableCell cell_9_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.SituacaoCorporativa);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_9_tFileOutputExcel_1);
											int currentWith_9_tFileOutputExcel_1 = cell_9_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[9] = fitWidth_tFileOutputExcel_1[9] > currentWith_9_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[9]
													: currentWith_9_tFileOutputExcel_1 + 2;
										}

										if (row3.SequenciaItem != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 10;

											jxl.write.WritableCell cell_10_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.SequenciaItem);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_10_tFileOutputExcel_1);
											int currentWith_10_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_10_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_10_tFileOutputExcel_1 = currentWith_10_tFileOutputExcel_1 > 10
													? 10
													: currentWith_10_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[10] = fitWidth_tFileOutputExcel_1[10] > currentWith_10_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[10]
													: currentWith_10_tFileOutputExcel_1 + 2;
										}

										if (row3.CodigoProduto != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 11;

											jxl.write.WritableCell cell_11_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.CodigoProduto);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_11_tFileOutputExcel_1);
											int currentWith_11_tFileOutputExcel_1 = cell_11_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[11] = fitWidth_tFileOutputExcel_1[11] > currentWith_11_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[11]
													: currentWith_11_tFileOutputExcel_1 + 2;
										}

										if (row3.QuantidadeProduto != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 12;

											jxl.write.WritableCell cell_12_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.QuantidadeProduto);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_12_tFileOutputExcel_1);
											int currentWith_12_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_12_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_12_tFileOutputExcel_1 = currentWith_12_tFileOutputExcel_1 > 10
													? 10
													: currentWith_12_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[12] = fitWidth_tFileOutputExcel_1[12] > currentWith_12_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[12]
													: currentWith_12_tFileOutputExcel_1 + 2;
										}

										if (row3.QuantidadeAtendida != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 13;

											jxl.write.WritableCell cell_13_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.QuantidadeAtendida);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_13_tFileOutputExcel_1);
											int currentWith_13_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_13_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_13_tFileOutputExcel_1 = currentWith_13_tFileOutputExcel_1 > 10
													? 10
													: currentWith_13_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[13] = fitWidth_tFileOutputExcel_1[13] > currentWith_13_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[13]
													: currentWith_13_tFileOutputExcel_1 + 2;
										}

										if (row3.QuantidadeCancelada != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 14;

											jxl.write.WritableCell cell_14_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.QuantidadeCancelada);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_14_tFileOutputExcel_1);
											int currentWith_14_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_14_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_14_tFileOutputExcel_1 = currentWith_14_tFileOutputExcel_1 > 10
													? 10
													: currentWith_14_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[14] = fitWidth_tFileOutputExcel_1[14] > currentWith_14_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[14]
													: currentWith_14_tFileOutputExcel_1 + 2;
										}

										if (row3.PrecoUnitario != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 15;

											jxl.write.WritableCell cell_15_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.PrecoUnitario);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_15_tFileOutputExcel_1);
											int currentWith_15_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_15_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_15_tFileOutputExcel_1 = currentWith_15_tFileOutputExcel_1 > 10
													? 10
													: currentWith_15_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[15] = fitWidth_tFileOutputExcel_1[15] > currentWith_15_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[15]
													: currentWith_15_tFileOutputExcel_1 + 2;
										}

										if (row3.PrecoLiquido != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 16;

											jxl.write.WritableCell cell_16_tFileOutputExcel_1 = new jxl.write.Number(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.PrecoLiquido);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_16_tFileOutputExcel_1);
											int currentWith_16_tFileOutputExcel_1 = String
													.valueOf(((jxl.write.Number) cell_16_tFileOutputExcel_1).getValue())
													.trim().length();
											currentWith_16_tFileOutputExcel_1 = currentWith_16_tFileOutputExcel_1 > 10
													? 10
													: currentWith_16_tFileOutputExcel_1;
											fitWidth_tFileOutputExcel_1[16] = fitWidth_tFileOutputExcel_1[16] > currentWith_16_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[16]
													: currentWith_16_tFileOutputExcel_1 + 2;
										}

										if (row3.CodigoTES != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 17;

											jxl.write.WritableCell cell_17_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.CodigoTES);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_17_tFileOutputExcel_1);
											int currentWith_17_tFileOutputExcel_1 = cell_17_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[17] = fitWidth_tFileOutputExcel_1[17] > currentWith_17_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[17]
													: currentWith_17_tFileOutputExcel_1 + 2;
										}

										if (row3.Operacao != null) {

//modif start

											columnIndex_tFileOutputExcel_1 = 18;

											jxl.write.WritableCell cell_18_tFileOutputExcel_1 = new jxl.write.Label(
													columnIndex_tFileOutputExcel_1,
													startRowNum_tFileOutputExcel_1 + nb_line_tFileOutputExcel_1,

//modif end
													row3.Operacao);
//modif start					
											// If we keep the cell format from the existing cell in sheet

//modif ends							
											writableSheet_tFileOutputExcel_1.addCell(cell_18_tFileOutputExcel_1);
											int currentWith_18_tFileOutputExcel_1 = cell_18_tFileOutputExcel_1
													.getContents().trim().length();
											fitWidth_tFileOutputExcel_1[18] = fitWidth_tFileOutputExcel_1[18] > currentWith_18_tFileOutputExcel_1
													? fitWidth_tFileOutputExcel_1[18]
													: currentWith_18_tFileOutputExcel_1 + 2;
										}

										nb_line_tFileOutputExcel_1++;

										log.debug("tFileOutputExcel_1 - Writing the record "
												+ nb_line_tFileOutputExcel_1 + " to the file.");

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
										 * [tLogRow_1 process_data_end ] start
										 */

										currentComponent = "tLogRow_1";

										/**
										 * [tLogRow_1 process_data_end ] stop
										 */

									} // End of branch "row1"

									/**
									 * [tFilterRow_8 process_data_end ] start
									 */

									currentComponent = "tFilterRow_8";

									cLabel = "Filtor Tipo Operacao";

									/**
									 * [tFilterRow_8 process_data_end ] stop
									 */

								} // End of branch "Carteira"

							} // close loop of lookup 'TIpo_operacao' // G_TM_M_043

						} // close loop of lookup 'Itens_pedido' // G_TM_M_043

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						cLabel = "Jun\u00E7\u00E3o";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Capa_pedido";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						currentComponent = "tDBInput_2";

						cLabel = "Capa_pedido";

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
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

				cLabel = "Jun\u00E7\u00E3o";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_Itens_pedido != null) {
					tHash_Lookup_Itens_pedido.endGet();
				}
				globalMap.remove("tHash_Lookup_Itens_pedido");

				if (tHash_Lookup_TIpo_operacao != null) {
					tHash_Lookup_TIpo_operacao.endGet();
				}
				globalMap.remove("tHash_Lookup_TIpo_operacao");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'Carteira': " + count_Carteira_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Capa_pedido", 2, 0,
						"tDBInput_2", "Capa_pedido", "tMSSqlInput", "tMap_1", "Jun\u00E7\u00E3o", "tMap", "output")) {
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
				 * [tFilterRow_8 end ] start
				 */

				currentComponent = "tFilterRow_8";

				cLabel = "Filtor Tipo Operacao";

				globalMap.put("tFilterRow_8_NB_LINE", nb_line_tFilterRow_8);
				globalMap.put("tFilterRow_8_NB_LINE_OK", nb_line_ok_tFilterRow_8);
				globalMap.put("tFilterRow_8_NB_LINE_REJECT", nb_line_reject_tFilterRow_8);

				log.info("tFilterRow_8 - Processed records count:" + nb_line_tFilterRow_8 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_8 + ". Rejected records count:" + nb_line_reject_tFilterRow_8 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Carteira", 2, 0,
						"tMap_1", "Jun\u00E7\u00E3o", "tMap", "tFilterRow_8", "Filtor Tipo Operacao", "tFilterRow",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_8 - " + ("Done."));

				ok_Hash.put("tFilterRow_8", true);
				end_Hash.put("tFilterRow_8", System.currentTimeMillis());

				/**
				 * [tFilterRow_8 end ] stop
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFilterRow_8", "Filtor Tipo Operacao", "tFilterRow", "tLogRow_1", "tLogRow_1", "tLogRow",
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
				 * [tFileOutputExcel_1 end ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				writeableWorkbook_tFileOutputExcel_1.write();
				writeableWorkbook_tFileOutputExcel_1.close();
				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				log.debug("tFileOutputExcel_1 - Written records count: " + nb_line_tFileOutputExcel_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tLogRow_1", "tLogRow_1", "tLogRow", "tFileOutputExcel_1", "tFileOutputExcel_1",
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
			globalMap.remove("tHash_Lookup_Itens_pedido");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_TIpo_operacao");

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				currentComponent = "tDBInput_2";

				cLabel = "Capa_pedido";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				cLabel = "Jun\u00E7\u00E3o";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFilterRow_8 finally ] start
				 */

				currentComponent = "tFilterRow_8";

				cLabel = "Filtor Tipo Operacao";

				/**
				 * [tFilterRow_8 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
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

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public static class TIpo_operacaoStruct
			implements routines.system.IPersistableComparableLookupRow<TIpo_operacaoStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Carteira = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Carteira = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Operacao;

		public String getOperacao() {
			return this.Operacao;
		}

		public Boolean OperacaoIsNullable() {
			return true;
		}

		public Boolean OperacaoIsKey() {
			return true;
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

		public String TipoOperacao;

		public String getTipoOperacao() {
			return this.TipoOperacao;
		}

		public Boolean TipoOperacaoIsNullable() {
			return true;
		}

		public Boolean TipoOperacaoIsKey() {
			return false;
		}

		public Integer TipoOperacaoLength() {
			return 1;
		}

		public Integer TipoOperacaoPrecision() {
			return 0;
		}

		public String TipoOperacaoDefault() {

			return null;

		}

		public String TipoOperacaoComment() {

			return "";

		}

		public String TipoOperacaoPattern() {

			return "";

		}

		public String TipoOperacaoOriginalDbColumnName() {

			return "TipoOperacao";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Operacao == null) ? 0 : this.Operacao.hashCode());

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
			final TIpo_operacaoStruct other = (TIpo_operacaoStruct) obj;

			if (this.Operacao == null) {
				if (other.Operacao != null)
					return false;

			} else if (!this.Operacao.equals(other.Operacao))

				return false;

			return true;
		}

		public void copyDataTo(TIpo_operacaoStruct other) {

			other.Operacao = this.Operacao;
			other.TipoOperacao = this.TipoOperacao;

		}

		public void copyKeysDataTo(TIpo_operacaoStruct other) {

			other.Operacao = this.Operacao;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Carteira.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Carteira.length == 0) {
						commonByteArray_HYDRONORTH_Carteira = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Carteira = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Carteira, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Carteira, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Carteira) {

				try {

					int length = 0;

					this.Operacao = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Operacao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Operacao, dos);

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

				this.TipoOperacao = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.TipoOperacao = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.TipoOperacao, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.TipoOperacao, dos, objectOut);

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
			sb.append("Operacao=" + Operacao);
			sb.append(",TipoOperacao=" + TipoOperacao);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Operacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Operacao);
			}

			sb.append("|");

			if (TipoOperacao == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoOperacao);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(TIpo_operacaoStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Operacao, other.Operacao);
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
		org.slf4j.MDC.put("_subJobPid", "7It6oy_" + subJobPidCounter.getAndIncrement());

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

				TIpo_operacaoStruct TIpo_operacao = new TIpo_operacaoStruct();

				/**
				 * [tAdvancedHash_TIpo_operacao begin ] start
				 */

				ok_Hash.put("tAdvancedHash_TIpo_operacao", false);
				start_Hash.put("tAdvancedHash_TIpo_operacao", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_TIpo_operacao";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "TIpo_operacao");

				int tos_count_tAdvancedHash_TIpo_operacao = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_TIpo_operacao", "tAdvancedHash_TIpo_operacao", "tAdvancedHash");
					talendJobLogProcess(globalMap);
				}

				// connection name:TIpo_operacao
				// source node:tDBInput_3 - inputs:(after_tDBInput_2)
				// outputs:(TIpo_operacao,TIpo_operacao) | target
				// node:tAdvancedHash_TIpo_operacao - inputs:(TIpo_operacao) outputs:()
				// linked node: tMap_1 - inputs:(Itens_pedido,Capa_pedido,TIpo_operacao)
				// outputs:(Carteira)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_TIpo_operacao = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_MATCHES;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<TIpo_operacaoStruct> tHash_Lookup_TIpo_operacao = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<TIpo_operacaoStruct>getLookup(matchingModeEnum_TIpo_operacao);

				globalMap.put("tHash_Lookup_TIpo_operacao", tHash_Lookup_TIpo_operacao);

				/**
				 * [tAdvancedHash_TIpo_operacao begin ] stop
				 */

				/**
				 * [tDBInput_3 begin ] start
				 */

				ok_Hash.put("tDBInput_3", false);
				start_Hash.put("tDBInput_3", System.currentTimeMillis());

				currentComponent = "tDBInput_3";

				cLabel = "Tipo_opera\u00E7\u00F5es";

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
									"enc:routine.encryption.key.v1:3hkRvJpCAsMfacbWsKqoxdx4UUNh3Q4lRcq9ZLRO4kfxcmhpL+9ZU3lx")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("TABLE" + " = " + "\"DB_TB_OPERS\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("QUERY" + " = "
									+ "\"SELECT DB_TBOPS_COD AS Operacao,  	DB_TBOPS_FAT AS TipoOperacao  FROM DB_TB_OPERS\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("PROPERTIES" + " = " + "\"instanceName=mercanet;\"");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_3.append(" | ");
							log4jParamters_tDBInput_3.append(
									"TRIM_COLUMN" + " = " + "[{TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Operacao")
											+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TipoOperacao") + "}]");
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
					talendJobLog.addCM("tDBInput_3", "Tipo_opera\u00E7\u00F5es", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:fkwuu8D20WRitZkhvvYRVnhpmX0lkDwAoQHuAiLM7H7KUneYZVWczDLB");

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

				String dbquery_tDBInput_3 = "SELECT DB_TBOPS_COD AS Operacao,\n	DB_TBOPS_FAT AS TipoOperacao\nFROM DB_TB_OPERS";

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
							TIpo_operacao.Operacao = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(1);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									TIpo_operacao.Operacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									TIpo_operacao.Operacao = tmpContent_tDBInput_3.trim();
								}
							} else {
								TIpo_operacao.Operacao = null;
							}
						}
						if (colQtyInRs_tDBInput_3 < 2) {
							TIpo_operacao.TipoOperacao = null;
						} else {

							tmpContent_tDBInput_3 = rs_tDBInput_3.getString(2);
							if (tmpContent_tDBInput_3 != null) {
								if (talendToDBList_tDBInput_3.contains(
										rsmd_tDBInput_3.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									TIpo_operacao.TipoOperacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_3);
								} else {
									TIpo_operacao.TipoOperacao = tmpContent_tDBInput_3.trim();
								}
							} else {
								TIpo_operacao.TipoOperacao = null;
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

						cLabel = "Tipo_opera\u00E7\u00F5es";

						tos_count_tDBInput_3++;

						/**
						 * [tDBInput_3 main ] stop
						 */

						/**
						 * [tDBInput_3 process_data_begin ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Tipo_opera\u00E7\u00F5es";

						/**
						 * [tDBInput_3 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_TIpo_operacao main ] start
						 */

						currentComponent = "tAdvancedHash_TIpo_operacao";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "TIpo_operacao", "tDBInput_3", "Tipo_opera\u00E7\u00F5es", "tMSSqlInput",
								"tAdvancedHash_TIpo_operacao", "tAdvancedHash_TIpo_operacao", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("TIpo_operacao - " + (TIpo_operacao == null ? "" : TIpo_operacao.toLogString()));
						}

						TIpo_operacaoStruct TIpo_operacao_HashRow = new TIpo_operacaoStruct();

						TIpo_operacao_HashRow.Operacao = TIpo_operacao.Operacao;

						TIpo_operacao_HashRow.TipoOperacao = TIpo_operacao.TipoOperacao;

						tHash_Lookup_TIpo_operacao.put(TIpo_operacao_HashRow);

						tos_count_tAdvancedHash_TIpo_operacao++;

						/**
						 * [tAdvancedHash_TIpo_operacao main ] stop
						 */

						/**
						 * [tAdvancedHash_TIpo_operacao process_data_begin ] start
						 */

						currentComponent = "tAdvancedHash_TIpo_operacao";

						/**
						 * [tAdvancedHash_TIpo_operacao process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_TIpo_operacao process_data_end ] start
						 */

						currentComponent = "tAdvancedHash_TIpo_operacao";

						/**
						 * [tAdvancedHash_TIpo_operacao process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 process_data_end ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Tipo_opera\u00E7\u00F5es";

						/**
						 * [tDBInput_3 process_data_end ] stop
						 */

						/**
						 * [tDBInput_3 end ] start
						 */

						currentComponent = "tDBInput_3";

						cLabel = "Tipo_opera\u00E7\u00F5es";

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
				 * [tAdvancedHash_TIpo_operacao end ] start
				 */

				currentComponent = "tAdvancedHash_TIpo_operacao";

				tHash_Lookup_TIpo_operacao.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "TIpo_operacao", 2, 0,
						"tDBInput_3", "Tipo_opera\u00E7\u00F5es", "tMSSqlInput", "tAdvancedHash_TIpo_operacao",
						"tAdvancedHash_TIpo_operacao", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_TIpo_operacao", true);
				end_Hash.put("tAdvancedHash_TIpo_operacao", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_TIpo_operacao end ] stop
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

				cLabel = "Tipo_opera\u00E7\u00F5es";

				/**
				 * [tDBInput_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_TIpo_operacao finally ] start
				 */

				currentComponent = "tAdvancedHash_TIpo_operacao";

				/**
				 * [tAdvancedHash_TIpo_operacao finally ] stop
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

	public void tFilterRow_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFilterRow_10_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFilterRow_10");
		org.slf4j.MDC.put("_subJobPid", "RLV49g_" + subJobPidCounter.getAndIncrement());

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
				 * [tFilterRow_10 begin ] start
				 */

				ok_Hash.put("tFilterRow_10", false);
				start_Hash.put("tFilterRow_10", System.currentTimeMillis());

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				int tos_count_tFilterRow_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_10 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_10 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_10 = new StringBuilder();
							log4jParamters_tFilterRow_10.append("Parameters:");
							log4jParamters_tFilterRow_10.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_10.append(" | ");
							log4jParamters_tFilterRow_10
									.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("==") + ", RVALUE=" + ("\"Venda\"")
											+ ", INPUT_COLUMN=" + ("") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_10.append(" | ");
							log4jParamters_tFilterRow_10.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_10.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_10 - " + (log4jParamters_tFilterRow_10));
						}
					}
					new BytesLimit65535_tFilterRow_10().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_10", "Filtro vendas", "tFilterRow");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tFilterRow_10 = 0;
				int nb_line_ok_tFilterRow_10 = 0;
				int nb_line_reject_tFilterRow_10 = 0;

				class Operator_tFilterRow_10 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_10(String unionFlag) {
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
				 * [tFilterRow_10 begin ] stop
				 */

				/**
				 * [tFilterRow_10 main ] start
				 */

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				nb_line_tFilterRow_10++;

				tos_count_tFilterRow_10++;

				/**
				 * [tFilterRow_10 main ] stop
				 */

				/**
				 * [tFilterRow_10 process_data_begin ] start
				 */

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				/**
				 * [tFilterRow_10 process_data_begin ] stop
				 */

				/**
				 * [tFilterRow_10 process_data_end ] start
				 */

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				/**
				 * [tFilterRow_10 process_data_end ] stop
				 */

				/**
				 * [tFilterRow_10 end ] start
				 */

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				globalMap.put("tFilterRow_10_NB_LINE", nb_line_tFilterRow_10);
				globalMap.put("tFilterRow_10_NB_LINE_OK", nb_line_ok_tFilterRow_10);
				globalMap.put("tFilterRow_10_NB_LINE_REJECT", nb_line_reject_tFilterRow_10);

				log.info("tFilterRow_10 - Processed records count:" + nb_line_tFilterRow_10 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_10 + ". Rejected records count:" + nb_line_reject_tFilterRow_10 + ".");

				if (log.isDebugEnabled())
					log.debug("tFilterRow_10 - " + ("Done."));

				ok_Hash.put("tFilterRow_10", true);
				end_Hash.put("tFilterRow_10", System.currentTimeMillis());

				/**
				 * [tFilterRow_10 end ] stop
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
				 * [tFilterRow_10 finally ] start
				 */

				currentComponent = "tFilterRow_10";

				cLabel = "Filtro vendas";

				/**
				 * [tFilterRow_10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFilterRow_10_SUBPROCESS_STATE", 1);
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
		final Carteira CarteiraClass = new Carteira();

		int exitCode = CarteiraClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Carteira' - Done.");
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
		log.info("TalendJob: 'Carteira' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_4A2EQE6kEe-Wi9BUu6mzew");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-07-30T21:30:08.995505900Z");

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
			java.io.InputStream inContext = Carteira.class.getClassLoader()
					.getResourceAsStream("hydronorth/carteira_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Carteira.class.getClassLoader()
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
		log.info("TalendJob: 'Carteira' - Started.");
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
			tDBInput_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_2) {
			globalMap.put("tDBInput_2_SUBPROCESS_STATE", -1);

			e_tDBInput_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Carteira");
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
		log.info("TalendJob: 'Carteira' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 1051924 characters generated by Talend Cloud Data Integration on the 30 de
 * julho de 2024 18:30:08 BRT
 ************************************************************************************************/