
package hydronorth.itensnotasaida_0_1;

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
 * Job: ItensNotaSaida Purpose: <br>
 * Description: <br>
 * 
 * @author Oliveira Santi, Thiago
 * @version 8.0.1.20240619_0909-patch
 * @status DEV
 */
public class ItensNotaSaida implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "ItensNotaSaida.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(ItensNotaSaida.class);

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
	private final String jobName = "ItensNotaSaida";
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
			"_DY438ELbEe-YS8RQVZZjcw", "0.1");
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
					ItensNotaSaida.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(ItensNotaSaida.this, new Object[] { e, currentComponent, globalMap });
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

	public void tDBOutput_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBOutput_3_onSubJobError(exception, errorComponent, globalMap);
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

	public void tDBOutput_3_onSubJobError(Exception exception, String errorComponent,
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
		final static byte[] commonByteArrayLock_HYDRONORTH_ItensNotaSaida = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Filial;

		public String getFilial() {
			return this.Filial;
		}

		public Boolean FilialIsNullable() {
			return true;
		}

		public Boolean FilialIsKey() {
			return true;
		}

		public Integer FilialLength() {
			return 2;
		}

		public Integer FilialPrecision() {
			return 0;
		}

		public String FilialDefault() {

			return null;

		}

		public String FilialComment() {

			return "";

		}

		public String FilialPattern() {

			return "";

		}

		public String FilialOriginalDbColumnName() {

			return "Filial";

		}

		public String SequenciaItem;

		public String getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return true;
		}

		public Integer SequenciaItemLength() {
			return 2;
		}

		public Integer SequenciaItemPrecision() {
			return 0;
		}

		public String SequenciaItemDefault() {

			return null;

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
			return 15;
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

		public String SegundaUnidade;

		public String getSegundaUnidade() {
			return this.SegundaUnidade;
		}

		public Boolean SegundaUnidadeIsNullable() {
			return true;
		}

		public Boolean SegundaUnidadeIsKey() {
			return false;
		}

		public Integer SegundaUnidadeLength() {
			return 6;
		}

		public Integer SegundaUnidadePrecision() {
			return 0;
		}

		public String SegundaUnidadeDefault() {

			return null;

		}

		public String SegundaUnidadeComment() {

			return "";

		}

		public String SegundaUnidadePattern() {

			return "";

		}

		public String SegundaUnidadeOriginalDbColumnName() {

			return "SegundaUnidade";

		}

		public String UnidadeMedida;

		public String getUnidadeMedida() {
			return this.UnidadeMedida;
		}

		public Boolean UnidadeMedidaIsNullable() {
			return true;
		}

		public Boolean UnidadeMedidaIsKey() {
			return false;
		}

		public Integer UnidadeMedidaLength() {
			return 6;
		}

		public Integer UnidadeMedidaPrecision() {
			return 0;
		}

		public String UnidadeMedidaDefault() {

			return null;

		}

		public String UnidadeMedidaComment() {

			return "";

		}

		public String UnidadeMedidaPattern() {

			return "";

		}

		public String UnidadeMedidaOriginalDbColumnName() {

			return "UnidadeMedida";

		}

		public Double Quantidade;

		public Double getQuantidade() {
			return this.Quantidade;
		}

		public Boolean QuantidadeIsNullable() {
			return true;
		}

		public Boolean QuantidadeIsKey() {
			return false;
		}

		public Integer QuantidadeLength() {
			return 15;
		}

		public Integer QuantidadePrecision() {
			return 0;
		}

		public String QuantidadeDefault() {

			return "";

		}

		public String QuantidadeComment() {

			return "";

		}

		public String QuantidadePattern() {

			return "";

		}

		public String QuantidadeOriginalDbColumnName() {

			return "Quantidade";

		}

		public Double PrecoVenda;

		public Double getPrecoVenda() {
			return this.PrecoVenda;
		}

		public Boolean PrecoVendaIsNullable() {
			return true;
		}

		public Boolean PrecoVendaIsKey() {
			return false;
		}

		public Integer PrecoVendaLength() {
			return 15;
		}

		public Integer PrecoVendaPrecision() {
			return 0;
		}

		public String PrecoVendaDefault() {

			return "";

		}

		public String PrecoVendaComment() {

			return "";

		}

		public String PrecoVendaPattern() {

			return "";

		}

		public String PrecoVendaOriginalDbColumnName() {

			return "PrecoVenda";

		}

		public Double PrecoTotal;

		public Double getPrecoTotal() {
			return this.PrecoTotal;
		}

		public Boolean PrecoTotalIsNullable() {
			return true;
		}

		public Boolean PrecoTotalIsKey() {
			return false;
		}

		public Integer PrecoTotalLength() {
			return 15;
		}

		public Integer PrecoTotalPrecision() {
			return 0;
		}

		public String PrecoTotalDefault() {

			return "";

		}

		public String PrecoTotalComment() {

			return "";

		}

		public String PrecoTotalPattern() {

			return "";

		}

		public String PrecoTotalOriginalDbColumnName() {

			return "PrecoTotal";

		}

		public Double ValorIPI;

		public Double getValorIPI() {
			return this.ValorIPI;
		}

		public Boolean ValorIPIIsNullable() {
			return true;
		}

		public Boolean ValorIPIIsKey() {
			return false;
		}

		public Integer ValorIPILength() {
			return 15;
		}

		public Integer ValorIPIPrecision() {
			return 0;
		}

		public String ValorIPIDefault() {

			return "";

		}

		public String ValorIPIComment() {

			return "";

		}

		public String ValorIPIPattern() {

			return "";

		}

		public String ValorIPIOriginalDbColumnName() {

			return "ValorIPI";

		}

		public Double ValorICMS;

		public Double getValorICMS() {
			return this.ValorICMS;
		}

		public Boolean ValorICMSIsNullable() {
			return true;
		}

		public Boolean ValorICMSIsKey() {
			return false;
		}

		public Integer ValorICMSLength() {
			return 15;
		}

		public Integer ValorICMSPrecision() {
			return 0;
		}

		public String ValorICMSDefault() {

			return "";

		}

		public String ValorICMSComment() {

			return "";

		}

		public String ValorICMSPattern() {

			return "";

		}

		public String ValorICMSOriginalDbColumnName() {

			return "ValorICMS";

		}

		public String CodigoTes;

		public String getCodigoTes() {
			return this.CodigoTes;
		}

		public Boolean CodigoTesIsNullable() {
			return true;
		}

		public Boolean CodigoTesIsKey() {
			return false;
		}

		public Integer CodigoTesLength() {
			return 3;
		}

		public Integer CodigoTesPrecision() {
			return 0;
		}

		public String CodigoTesDefault() {

			return null;

		}

		public String CodigoTesComment() {

			return "";

		}

		public String CodigoTesPattern() {

			return "";

		}

		public String CodigoTesOriginalDbColumnName() {

			return "CodigoTes";

		}

		public String CFOP;

		public String getCFOP() {
			return this.CFOP;
		}

		public Boolean CFOPIsNullable() {
			return true;
		}

		public Boolean CFOPIsKey() {
			return false;
		}

		public Integer CFOPLength() {
			return 5;
		}

		public Integer CFOPPrecision() {
			return 0;
		}

		public String CFOPDefault() {

			return null;

		}

		public String CFOPComment() {

			return "";

		}

		public String CFOPPattern() {

			return "";

		}

		public String CFOPOriginalDbColumnName() {

			return "CFOP";

		}

		public Double Desconto;

		public Double getDesconto() {
			return this.Desconto;
		}

		public Boolean DescontoIsNullable() {
			return true;
		}

		public Boolean DescontoIsKey() {
			return false;
		}

		public Integer DescontoLength() {
			return 15;
		}

		public Integer DescontoPrecision() {
			return 0;
		}

		public String DescontoDefault() {

			return "";

		}

		public String DescontoComment() {

			return "";

		}

		public String DescontoPattern() {

			return "";

		}

		public String DescontoOriginalDbColumnName() {

			return "Desconto";

		}

		public Double IPI;

		public Double getIPI() {
			return this.IPI;
		}

		public Boolean IPIIsNullable() {
			return true;
		}

		public Boolean IPIIsKey() {
			return false;
		}

		public Integer IPILength() {
			return 15;
		}

		public Integer IPIPrecision() {
			return 0;
		}

		public String IPIDefault() {

			return "";

		}

		public String IPIComment() {

			return "";

		}

		public String IPIPattern() {

			return "";

		}

		public String IPIOriginalDbColumnName() {

			return "IPI";

		}

		public Double ValorCSSL;

		public Double getValorCSSL() {
			return this.ValorCSSL;
		}

		public Boolean ValorCSSLIsNullable() {
			return true;
		}

		public Boolean ValorCSSLIsKey() {
			return false;
		}

		public Integer ValorCSSLLength() {
			return 15;
		}

		public Integer ValorCSSLPrecision() {
			return 0;
		}

		public String ValorCSSLDefault() {

			return "";

		}

		public String ValorCSSLComment() {

			return "";

		}

		public String ValorCSSLPattern() {

			return "";

		}

		public String ValorCSSLOriginalDbColumnName() {

			return "ValorCSSL";

		}

		public Double AliquotaICM;

		public Double getAliquotaICM() {
			return this.AliquotaICM;
		}

		public Boolean AliquotaICMIsNullable() {
			return true;
		}

		public Boolean AliquotaICMIsKey() {
			return false;
		}

		public Integer AliquotaICMLength() {
			return 15;
		}

		public Integer AliquotaICMPrecision() {
			return 0;
		}

		public String AliquotaICMDefault() {

			return "";

		}

		public String AliquotaICMComment() {

			return "";

		}

		public String AliquotaICMPattern() {

			return "";

		}

		public String AliquotaICMOriginalDbColumnName() {

			return "AliquotaICM";

		}

		public Double Peso;

		public Double getPeso() {
			return this.Peso;
		}

		public Boolean PesoIsNullable() {
			return true;
		}

		public Boolean PesoIsKey() {
			return false;
		}

		public Integer PesoLength() {
			return 15;
		}

		public Integer PesoPrecision() {
			return 0;
		}

		public String PesoDefault() {

			return "";

		}

		public String PesoComment() {

			return "";

		}

		public String PesoPattern() {

			return "";

		}

		public String PesoOriginalDbColumnName() {

			return "Peso";

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
			return 6;
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

			return "";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public String ItemPV;

		public String getItemPV() {
			return this.ItemPV;
		}

		public Boolean ItemPVIsNullable() {
			return true;
		}

		public Boolean ItemPVIsKey() {
			return false;
		}

		public Integer ItemPVLength() {
			return 2;
		}

		public Integer ItemPVPrecision() {
			return 0;
		}

		public String ItemPVDefault() {

			return null;

		}

		public String ItemPVComment() {

			return "";

		}

		public String ItemPVPattern() {

			return "";

		}

		public String ItemPVOriginalDbColumnName() {

			return "ItemPV";

		}

		public String Cliente;

		public String getCliente() {
			return this.Cliente;
		}

		public Boolean ClienteIsNullable() {
			return true;
		}

		public Boolean ClienteIsKey() {
			return false;
		}

		public Integer ClienteLength() {
			return 9;
		}

		public Integer ClientePrecision() {
			return 0;
		}

		public String ClienteDefault() {

			return null;

		}

		public String ClienteComment() {

			return "";

		}

		public String ClientePattern() {

			return "";

		}

		public String ClienteOriginalDbColumnName() {

			return "Cliente";

		}

		public String Loja;

		public String getLoja() {
			return this.Loja;
		}

		public Boolean LojaIsNullable() {
			return true;
		}

		public Boolean LojaIsKey() {
			return false;
		}

		public Integer LojaLength() {
			return 3;
		}

		public Integer LojaPrecision() {
			return 0;
		}

		public String LojaDefault() {

			return null;

		}

		public String LojaComment() {

			return "";

		}

		public String LojaPattern() {

			return "";

		}

		public String LojaOriginalDbColumnName() {

			return "Loja";

		}

		public String Armazem;

		public String getArmazem() {
			return this.Armazem;
		}

		public Boolean ArmazemIsNullable() {
			return true;
		}

		public Boolean ArmazemIsKey() {
			return false;
		}

		public Integer ArmazemLength() {
			return 2;
		}

		public Integer ArmazemPrecision() {
			return 0;
		}

		public String ArmazemDefault() {

			return null;

		}

		public String ArmazemComment() {

			return "";

		}

		public String ArmazemPattern() {

			return "";

		}

		public String ArmazemOriginalDbColumnName() {

			return "Armazem";

		}

		public String NotaFiscal;

		public String getNotaFiscal() {
			return this.NotaFiscal;
		}

		public Boolean NotaFiscalIsNullable() {
			return true;
		}

		public Boolean NotaFiscalIsKey() {
			return true;
		}

		public Integer NotaFiscalLength() {
			return 9;
		}

		public Integer NotaFiscalPrecision() {
			return 0;
		}

		public String NotaFiscalDefault() {

			return null;

		}

		public String NotaFiscalComment() {

			return "";

		}

		public String NotaFiscalPattern() {

			return "";

		}

		public String NotaFiscalOriginalDbColumnName() {

			return "NotaFiscal";

		}

		public String Serie;

		public String getSerie() {
			return this.Serie;
		}

		public Boolean SerieIsNullable() {
			return true;
		}

		public Boolean SerieIsKey() {
			return false;
		}

		public Integer SerieLength() {
			return 3;
		}

		public Integer SeriePrecision() {
			return 0;
		}

		public String SerieDefault() {

			return null;

		}

		public String SerieComment() {

			return "";

		}

		public String SeriePattern() {

			return "";

		}

		public String SerieOriginalDbColumnName() {

			return "Serie";

		}

		public String Grupo;

		public String getGrupo() {
			return this.Grupo;
		}

		public Boolean GrupoIsNullable() {
			return true;
		}

		public Boolean GrupoIsKey() {
			return false;
		}

		public Integer GrupoLength() {
			return 4;
		}

		public Integer GrupoPrecision() {
			return 0;
		}

		public String GrupoDefault() {

			return null;

		}

		public String GrupoComment() {

			return "";

		}

		public String GrupoPattern() {

			return "";

		}

		public String GrupoOriginalDbColumnName() {

			return "Grupo";

		}

		public String TipoProduto;

		public String getTipoProduto() {
			return this.TipoProduto;
		}

		public Boolean TipoProdutoIsNullable() {
			return true;
		}

		public Boolean TipoProdutoIsKey() {
			return false;
		}

		public Integer TipoProdutoLength() {
			return 2;
		}

		public Integer TipoProdutoPrecision() {
			return 0;
		}

		public String TipoProdutoDefault() {

			return null;

		}

		public String TipoProdutoComment() {

			return "";

		}

		public String TipoProdutoPattern() {

			return "";

		}

		public String TipoProdutoOriginalDbColumnName() {

			return "TipoProduto";

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
			return 10;
		}

		public Integer DataEmissaoPrecision() {
			return 0;
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

		public Double CustoProduto;

		public Double getCustoProduto() {
			return this.CustoProduto;
		}

		public Boolean CustoProdutoIsNullable() {
			return true;
		}

		public Boolean CustoProdutoIsKey() {
			return false;
		}

		public Integer CustoProdutoLength() {
			return 15;
		}

		public Integer CustoProdutoPrecision() {
			return 0;
		}

		public String CustoProdutoDefault() {

			return "";

		}

		public String CustoProdutoComment() {

			return "";

		}

		public String CustoProdutoPattern() {

			return "";

		}

		public String CustoProdutoOriginalDbColumnName() {

			return "CustoProduto";

		}

		public Double Custo2;

		public Double getCusto2() {
			return this.Custo2;
		}

		public Boolean Custo2IsNullable() {
			return true;
		}

		public Boolean Custo2IsKey() {
			return false;
		}

		public Integer Custo2Length() {
			return 15;
		}

		public Integer Custo2Precision() {
			return 0;
		}

		public String Custo2Default() {

			return "";

		}

		public String Custo2Comment() {

			return "";

		}

		public String Custo2Pattern() {

			return "";

		}

		public String Custo2OriginalDbColumnName() {

			return "Custo2";

		}

		public Double Custo3;

		public Double getCusto3() {
			return this.Custo3;
		}

		public Boolean Custo3IsNullable() {
			return true;
		}

		public Boolean Custo3IsKey() {
			return false;
		}

		public Integer Custo3Length() {
			return 15;
		}

		public Integer Custo3Precision() {
			return 0;
		}

		public String Custo3Default() {

			return "";

		}

		public String Custo3Comment() {

			return "";

		}

		public String Custo3Pattern() {

			return "";

		}

		public String Custo3OriginalDbColumnName() {

			return "Custo3";

		}

		public Double Custo4;

		public Double getCusto4() {
			return this.Custo4;
		}

		public Boolean Custo4IsNullable() {
			return true;
		}

		public Boolean Custo4IsKey() {
			return false;
		}

		public Integer Custo4Length() {
			return 15;
		}

		public Integer Custo4Precision() {
			return 0;
		}

		public String Custo4Default() {

			return "";

		}

		public String Custo4Comment() {

			return "";

		}

		public String Custo4Pattern() {

			return "";

		}

		public String Custo4OriginalDbColumnName() {

			return "Custo4";

		}

		public Double Custo5;

		public Double getCusto5() {
			return this.Custo5;
		}

		public Boolean Custo5IsNullable() {
			return true;
		}

		public Boolean Custo5IsKey() {
			return false;
		}

		public Integer Custo5Length() {
			return 15;
		}

		public Integer Custo5Precision() {
			return 0;
		}

		public String Custo5Default() {

			return "";

		}

		public String Custo5Comment() {

			return "";

		}

		public String Custo5Pattern() {

			return "";

		}

		public String Custo5OriginalDbColumnName() {

			return "Custo5";

		}

		public Double PrecoUnitario;

		public Double getPrecoUnitario() {
			return this.PrecoUnitario;
		}

		public Boolean PrecoUnitarioIsNullable() {
			return true;
		}

		public Boolean PrecoUnitarioIsKey() {
			return false;
		}

		public Integer PrecoUnitarioLength() {
			return 15;
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

		public Double QuantidadeSegundaUnidade;

		public Double getQuantidadeSegundaUnidade() {
			return this.QuantidadeSegundaUnidade;
		}

		public Boolean QuantidadeSegundaUnidadeIsNullable() {
			return true;
		}

		public Boolean QuantidadeSegundaUnidadeIsKey() {
			return false;
		}

		public Integer QuantidadeSegundaUnidadeLength() {
			return 15;
		}

		public Integer QuantidadeSegundaUnidadePrecision() {
			return 0;
		}

		public String QuantidadeSegundaUnidadeDefault() {

			return "";

		}

		public String QuantidadeSegundaUnidadeComment() {

			return "";

		}

		public String QuantidadeSegundaUnidadePattern() {

			return "";

		}

		public String QuantidadeSegundaUnidadeOriginalDbColumnName() {

			return "QuantidadeSegundaUnidade";

		}

		public String NumeroSequencial;

		public String getNumeroSequencial() {
			return this.NumeroSequencial;
		}

		public Boolean NumeroSequencialIsNullable() {
			return true;
		}

		public Boolean NumeroSequencialIsKey() {
			return false;
		}

		public Integer NumeroSequencialLength() {
			return 6;
		}

		public Integer NumeroSequencialPrecision() {
			return 0;
		}

		public String NumeroSequencialDefault() {

			return null;

		}

		public String NumeroSequencialComment() {

			return "";

		}

		public String NumeroSequencialPattern() {

			return "";

		}

		public String NumeroSequencialOriginalDbColumnName() {

			return "NumeroSequencial";

		}

		public String Estado;

		public String getEstado() {
			return this.Estado;
		}

		public Boolean EstadoIsNullable() {
			return true;
		}

		public Boolean EstadoIsKey() {
			return false;
		}

		public Integer EstadoLength() {
			return 2;
		}

		public Integer EstadoPrecision() {
			return 0;
		}

		public String EstadoDefault() {

			return null;

		}

		public String EstadoComment() {

			return "";

		}

		public String EstadoPattern() {

			return "";

		}

		public String EstadoOriginalDbColumnName() {

			return "Estado";

		}

		public Double DescontoValor;

		public Double getDescontoValor() {
			return this.DescontoValor;
		}

		public Boolean DescontoValorIsNullable() {
			return true;
		}

		public Boolean DescontoValorIsKey() {
			return false;
		}

		public Integer DescontoValorLength() {
			return 15;
		}

		public Integer DescontoValorPrecision() {
			return 0;
		}

		public String DescontoValorDefault() {

			return "";

		}

		public String DescontoValorComment() {

			return "";

		}

		public String DescontoValorPattern() {

			return "";

		}

		public String DescontoValorOriginalDbColumnName() {

			return "DescontoValor";

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

		public String NotaFiscalOrigem;

		public String getNotaFiscalOrigem() {
			return this.NotaFiscalOrigem;
		}

		public Boolean NotaFiscalOrigemIsNullable() {
			return true;
		}

		public Boolean NotaFiscalOrigemIsKey() {
			return false;
		}

		public Integer NotaFiscalOrigemLength() {
			return 9;
		}

		public Integer NotaFiscalOrigemPrecision() {
			return 0;
		}

		public String NotaFiscalOrigemDefault() {

			return null;

		}

		public String NotaFiscalOrigemComment() {

			return "";

		}

		public String NotaFiscalOrigemPattern() {

			return "";

		}

		public String NotaFiscalOrigemOriginalDbColumnName() {

			return "NotaFiscalOrigem";

		}

		public String SerieOrigem;

		public String getSerieOrigem() {
			return this.SerieOrigem;
		}

		public Boolean SerieOrigemIsNullable() {
			return true;
		}

		public Boolean SerieOrigemIsKey() {
			return false;
		}

		public Integer SerieOrigemLength() {
			return 3;
		}

		public Integer SerieOrigemPrecision() {
			return 0;
		}

		public String SerieOrigemDefault() {

			return null;

		}

		public String SerieOrigemComment() {

			return "";

		}

		public String SerieOrigemPattern() {

			return "";

		}

		public String SerieOrigemOriginalDbColumnName() {

			return "SerieOrigem";

		}

		public Double QuantidadeDevolucao;

		public Double getQuantidadeDevolucao() {
			return this.QuantidadeDevolucao;
		}

		public Boolean QuantidadeDevolucaoIsNullable() {
			return true;
		}

		public Boolean QuantidadeDevolucaoIsKey() {
			return false;
		}

		public Integer QuantidadeDevolucaoLength() {
			return 15;
		}

		public Integer QuantidadeDevolucaoPrecision() {
			return 0;
		}

		public String QuantidadeDevolucaoDefault() {

			return "";

		}

		public String QuantidadeDevolucaoComment() {

			return "";

		}

		public String QuantidadeDevolucaoPattern() {

			return "";

		}

		public String QuantidadeDevolucaoOriginalDbColumnName() {

			return "QuantidadeDevolucao";

		}

		public Double ValorDevolucao;

		public Double getValorDevolucao() {
			return this.ValorDevolucao;
		}

		public Boolean ValorDevolucaoIsNullable() {
			return true;
		}

		public Boolean ValorDevolucaoIsKey() {
			return false;
		}

		public Integer ValorDevolucaoLength() {
			return 15;
		}

		public Integer ValorDevolucaoPrecision() {
			return 0;
		}

		public String ValorDevolucaoDefault() {

			return "";

		}

		public String ValorDevolucaoComment() {

			return "";

		}

		public String ValorDevolucaoPattern() {

			return "";

		}

		public String ValorDevolucaoOriginalDbColumnName() {

			return "ValorDevolucao";

		}

		public String OrigemLancamento;

		public String getOrigemLancamento() {
			return this.OrigemLancamento;
		}

		public Boolean OrigemLancamentoIsNullable() {
			return true;
		}

		public Boolean OrigemLancamentoIsKey() {
			return false;
		}

		public Integer OrigemLancamentoLength() {
			return 2;
		}

		public Integer OrigemLancamentoPrecision() {
			return 0;
		}

		public String OrigemLancamentoDefault() {

			return null;

		}

		public String OrigemLancamentoComment() {

			return "";

		}

		public String OrigemLancamentoPattern() {

			return "";

		}

		public String OrigemLancamentoOriginalDbColumnName() {

			return "OrigemLancamento";

		}

		public Double BaseICMS;

		public Double getBaseICMS() {
			return this.BaseICMS;
		}

		public Boolean BaseICMSIsNullable() {
			return true;
		}

		public Boolean BaseICMSIsKey() {
			return false;
		}

		public Integer BaseICMSLength() {
			return 15;
		}

		public Integer BaseICMSPrecision() {
			return 0;
		}

		public String BaseICMSDefault() {

			return "";

		}

		public String BaseICMSComment() {

			return "";

		}

		public String BaseICMSPattern() {

			return "";

		}

		public String BaseICMSOriginalDbColumnName() {

			return "BaseICMS";

		}

		public Double BaseICM;

		public Double getBaseICM() {
			return this.BaseICM;
		}

		public Boolean BaseICMIsNullable() {
			return true;
		}

		public Boolean BaseICMIsKey() {
			return false;
		}

		public Integer BaseICMLength() {
			return 15;
		}

		public Integer BaseICMPrecision() {
			return 0;
		}

		public String BaseICMDefault() {

			return "";

		}

		public String BaseICMComment() {

			return "";

		}

		public String BaseICMPattern() {

			return "";

		}

		public String BaseICMOriginalDbColumnName() {

			return "BaseICM";

		}

		public Double ValorAcrescimo;

		public Double getValorAcrescimo() {
			return this.ValorAcrescimo;
		}

		public Boolean ValorAcrescimoIsNullable() {
			return true;
		}

		public Boolean ValorAcrescimoIsKey() {
			return false;
		}

		public Integer ValorAcrescimoLength() {
			return 15;
		}

		public Integer ValorAcrescimoPrecision() {
			return 0;
		}

		public String ValorAcrescimoDefault() {

			return "";

		}

		public String ValorAcrescimoComment() {

			return "";

		}

		public String ValorAcrescimoPattern() {

			return "";

		}

		public String ValorAcrescimoOriginalDbColumnName() {

			return "ValorAcrescimo";

		}

		public Double ICMSRetido;

		public Double getICMSRetido() {
			return this.ICMSRetido;
		}

		public Boolean ICMSRetidoIsNullable() {
			return true;
		}

		public Boolean ICMSRetidoIsKey() {
			return false;
		}

		public Integer ICMSRetidoLength() {
			return 15;
		}

		public Integer ICMSRetidoPrecision() {
			return 0;
		}

		public String ICMSRetidoDefault() {

			return "";

		}

		public String ICMSRetidoComment() {

			return "";

		}

		public String ICMSRetidoPattern() {

			return "";

		}

		public String ICMSRetidoOriginalDbColumnName() {

			return "ICMSRetido";

		}

		public Double Comissao1;

		public Double getComissao1() {
			return this.Comissao1;
		}

		public Boolean Comissao1IsNullable() {
			return true;
		}

		public Boolean Comissao1IsKey() {
			return false;
		}

		public Integer Comissao1Length() {
			return 15;
		}

		public Integer Comissao1Precision() {
			return 0;
		}

		public String Comissao1Default() {

			return "";

		}

		public String Comissao1Comment() {

			return "";

		}

		public String Comissao1Pattern() {

			return "";

		}

		public String Comissao1OriginalDbColumnName() {

			return "Comissao1";

		}

		public Double Comissao2;

		public Double getComissao2() {
			return this.Comissao2;
		}

		public Boolean Comissao2IsNullable() {
			return true;
		}

		public Boolean Comissao2IsKey() {
			return false;
		}

		public Integer Comissao2Length() {
			return 15;
		}

		public Integer Comissao2Precision() {
			return 0;
		}

		public String Comissao2Default() {

			return "";

		}

		public String Comissao2Comment() {

			return "";

		}

		public String Comissao2Pattern() {

			return "";

		}

		public String Comissao2OriginalDbColumnName() {

			return "Comissao2";

		}

		public Double Comissao3;

		public Double getComissao3() {
			return this.Comissao3;
		}

		public Boolean Comissao3IsNullable() {
			return true;
		}

		public Boolean Comissao3IsKey() {
			return false;
		}

		public Integer Comissao3Length() {
			return 15;
		}

		public Integer Comissao3Precision() {
			return 0;
		}

		public String Comissao3Default() {

			return "";

		}

		public String Comissao3Comment() {

			return "";

		}

		public String Comissao3Pattern() {

			return "";

		}

		public String Comissao3OriginalDbColumnName() {

			return "Comissao3";

		}

		public Double Comissao4;

		public Double getComissao4() {
			return this.Comissao4;
		}

		public Boolean Comissao4IsNullable() {
			return true;
		}

		public Boolean Comissao4IsKey() {
			return false;
		}

		public Integer Comissao4Length() {
			return 15;
		}

		public Integer Comissao4Precision() {
			return 0;
		}

		public String Comissao4Default() {

			return "";

		}

		public String Comissao4Comment() {

			return "";

		}

		public String Comissao4Pattern() {

			return "";

		}

		public String Comissao4OriginalDbColumnName() {

			return "Comissao4";

		}

		public Double Comissao5;

		public Double getComissao5() {
			return this.Comissao5;
		}

		public Boolean Comissao5IsNullable() {
			return true;
		}

		public Boolean Comissao5IsKey() {
			return false;
		}

		public Integer Comissao5Length() {
			return 15;
		}

		public Integer Comissao5Precision() {
			return 0;
		}

		public String Comissao5Default() {

			return "";

		}

		public String Comissao5Comment() {

			return "";

		}

		public String Comissao5Pattern() {

			return "";

		}

		public String Comissao5OriginalDbColumnName() {

			return "Comissao5";

		}

		public String NumeroLote;

		public String getNumeroLote() {
			return this.NumeroLote;
		}

		public Boolean NumeroLoteIsNullable() {
			return true;
		}

		public Boolean NumeroLoteIsKey() {
			return false;
		}

		public Integer NumeroLoteLength() {
			return 10;
		}

		public Integer NumeroLotePrecision() {
			return 0;
		}

		public String NumeroLoteDefault() {

			return null;

		}

		public String NumeroLoteComment() {

			return "";

		}

		public String NumeroLotePattern() {

			return "";

		}

		public String NumeroLoteOriginalDbColumnName() {

			return "NumeroLote";

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
			return 6;
		}

		public Integer LotePrecision() {
			return 0;
		}

		public String LoteDefault() {

			return null;

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

		public java.util.Date DataValidade;

		public java.util.Date getDataValidade() {
			return this.DataValidade;
		}

		public Boolean DataValidadeIsNullable() {
			return true;
		}

		public Boolean DataValidadeIsKey() {
			return false;
		}

		public Integer DataValidadeLength() {
			return 10;
		}

		public Integer DataValidadePrecision() {
			return 0;
		}

		public String DataValidadeDefault() {

			return null;

		}

		public String DataValidadeComment() {

			return "";

		}

		public String DataValidadePattern() {

			return "dd-MM-yyyy";

		}

		public String DataValidadeOriginalDbColumnName() {

			return "DataValidade";

		}

		public String ClasseFiscal;

		public String getClasseFiscal() {
			return this.ClasseFiscal;
		}

		public Boolean ClasseFiscalIsNullable() {
			return true;
		}

		public Boolean ClasseFiscalIsKey() {
			return false;
		}

		public Integer ClasseFiscalLength() {
			return 3;
		}

		public Integer ClasseFiscalPrecision() {
			return 0;
		}

		public String ClasseFiscalDefault() {

			return null;

		}

		public String ClasseFiscalComment() {

			return "";

		}

		public String ClasseFiscalPattern() {

			return "";

		}

		public String ClasseFiscalOriginalDbColumnName() {

			return "ClasseFiscal";

		}

		public Double BaseImposto5;

		public Double getBaseImposto5() {
			return this.BaseImposto5;
		}

		public Boolean BaseImposto5IsNullable() {
			return true;
		}

		public Boolean BaseImposto5IsKey() {
			return false;
		}

		public Integer BaseImposto5Length() {
			return 15;
		}

		public Integer BaseImposto5Precision() {
			return 0;
		}

		public String BaseImposto5Default() {

			return "";

		}

		public String BaseImposto5Comment() {

			return "";

		}

		public String BaseImposto5Pattern() {

			return "";

		}

		public String BaseImposto5OriginalDbColumnName() {

			return "BaseImposto5";

		}

		public Double BaseImposto6;

		public Double getBaseImposto6() {
			return this.BaseImposto6;
		}

		public Boolean BaseImposto6IsNullable() {
			return true;
		}

		public Boolean BaseImposto6IsKey() {
			return false;
		}

		public Integer BaseImposto6Length() {
			return 15;
		}

		public Integer BaseImposto6Precision() {
			return 0;
		}

		public String BaseImposto6Default() {

			return "";

		}

		public String BaseImposto6Comment() {

			return "";

		}

		public String BaseImposto6Pattern() {

			return "";

		}

		public String BaseImposto6OriginalDbColumnName() {

			return "BaseImposto6";

		}

		public Double ValorImposto5;

		public Double getValorImposto5() {
			return this.ValorImposto5;
		}

		public Boolean ValorImposto5IsNullable() {
			return true;
		}

		public Boolean ValorImposto5IsKey() {
			return false;
		}

		public Integer ValorImposto5Length() {
			return 15;
		}

		public Integer ValorImposto5Precision() {
			return 0;
		}

		public String ValorImposto5Default() {

			return "";

		}

		public String ValorImposto5Comment() {

			return "";

		}

		public String ValorImposto5Pattern() {

			return "";

		}

		public String ValorImposto5OriginalDbColumnName() {

			return "ValorImposto5";

		}

		public Double ValorImposto6;

		public Double getValorImposto6() {
			return this.ValorImposto6;
		}

		public Boolean ValorImposto6IsNullable() {
			return true;
		}

		public Boolean ValorImposto6IsKey() {
			return false;
		}

		public Integer ValorImposto6Length() {
			return 15;
		}

		public Integer ValorImposto6Precision() {
			return 0;
		}

		public String ValorImposto6Default() {

			return "";

		}

		public String ValorImposto6Comment() {

			return "";

		}

		public String ValorImposto6Pattern() {

			return "";

		}

		public String ValorImposto6OriginalDbColumnName() {

			return "ValorImposto6";

		}

		public Double AliquotaImposto5;

		public Double getAliquotaImposto5() {
			return this.AliquotaImposto5;
		}

		public Boolean AliquotaImposto5IsNullable() {
			return true;
		}

		public Boolean AliquotaImposto5IsKey() {
			return false;
		}

		public Integer AliquotaImposto5Length() {
			return 15;
		}

		public Integer AliquotaImposto5Precision() {
			return 0;
		}

		public String AliquotaImposto5Default() {

			return "";

		}

		public String AliquotaImposto5Comment() {

			return "";

		}

		public String AliquotaImposto5Pattern() {

			return "";

		}

		public String AliquotaImposto5OriginalDbColumnName() {

			return "AliquotaImposto5";

		}

		public Double AliquotaImposto6;

		public Double getAliquotaImposto6() {
			return this.AliquotaImposto6;
		}

		public Boolean AliquotaImposto6IsNullable() {
			return true;
		}

		public Boolean AliquotaImposto6IsKey() {
			return false;
		}

		public Integer AliquotaImposto6Length() {
			return 15;
		}

		public Integer AliquotaImposto6Precision() {
			return 0;
		}

		public String AliquotaImposto6Default() {

			return "";

		}

		public String AliquotaImposto6Comment() {

			return "";

		}

		public String AliquotaImposto6Pattern() {

			return "";

		}

		public String AliquotaImposto6OriginalDbColumnName() {

			return "AliquotaImposto6";

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
			return 9;
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

		public String Endereco;

		public String getEndereco() {
			return this.Endereco;
		}

		public Boolean EnderecoIsNullable() {
			return true;
		}

		public Boolean EnderecoIsKey() {
			return false;
		}

		public Integer EnderecoLength() {
			return 15;
		}

		public Integer EnderecoPrecision() {
			return 0;
		}

		public String EnderecoDefault() {

			return null;

		}

		public String EnderecoComment() {

			return "";

		}

		public String EnderecoPattern() {

			return "";

		}

		public String EnderecoOriginalDbColumnName() {

			return "Endereco";

		}

		public Double ValorImportacao;

		public Double getValorImportacao() {
			return this.ValorImportacao;
		}

		public Boolean ValorImportacaoIsNullable() {
			return true;
		}

		public Boolean ValorImportacaoIsKey() {
			return false;
		}

		public Integer ValorImportacaoLength() {
			return 15;
		}

		public Integer ValorImportacaoPrecision() {
			return 0;
		}

		public String ValorImportacaoDefault() {

			return "";

		}

		public String ValorImportacaoComment() {

			return "";

		}

		public String ValorImportacaoPattern() {

			return "";

		}

		public String ValorImportacaoOriginalDbColumnName() {

			return "ValorImportacao";

		}

		public java.util.Date DataFabricacao;

		public java.util.Date getDataFabricacao() {
			return this.DataFabricacao;
		}

		public Boolean DataFabricacaoIsNullable() {
			return true;
		}

		public Boolean DataFabricacaoIsKey() {
			return false;
		}

		public Integer DataFabricacaoLength() {
			return 10;
		}

		public Integer DataFabricacaoPrecision() {
			return 0;
		}

		public String DataFabricacaoDefault() {

			return null;

		}

		public String DataFabricacaoComment() {

			return "";

		}

		public String DataFabricacaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataFabricacaoOriginalDbColumnName() {

			return "DataFabricacao";

		}

		public Double AliquotaINSS;

		public Double getAliquotaINSS() {
			return this.AliquotaINSS;
		}

		public Boolean AliquotaINSSIsNullable() {
			return true;
		}

		public Boolean AliquotaINSSIsKey() {
			return false;
		}

		public Integer AliquotaINSSLength() {
			return 15;
		}

		public Integer AliquotaINSSPrecision() {
			return 0;
		}

		public String AliquotaINSSDefault() {

			return "";

		}

		public String AliquotaINSSComment() {

			return "";

		}

		public String AliquotaINSSPattern() {

			return "";

		}

		public String AliquotaINSSOriginalDbColumnName() {

			return "AliquotaINSS";

		}

		public Double AbatimentoINSS;

		public Double getAbatimentoINSS() {
			return this.AbatimentoINSS;
		}

		public Boolean AbatimentoINSSIsNullable() {
			return true;
		}

		public Boolean AbatimentoINSSIsKey() {
			return false;
		}

		public Integer AbatimentoINSSLength() {
			return 15;
		}

		public Integer AbatimentoINSSPrecision() {
			return 0;
		}

		public String AbatimentoINSSDefault() {

			return "";

		}

		public String AbatimentoINSSComment() {

			return "";

		}

		public String AbatimentoINSSPattern() {

			return "";

		}

		public String AbatimentoINSSOriginalDbColumnName() {

			return "AbatimentoINSS";

		}

		public String PrecoEmbarque;

		public String getPrecoEmbarque() {
			return this.PrecoEmbarque;
		}

		public Boolean PrecoEmbarqueIsNullable() {
			return true;
		}

		public Boolean PrecoEmbarqueIsKey() {
			return false;
		}

		public Integer PrecoEmbarqueLength() {
			return 20;
		}

		public Integer PrecoEmbarquePrecision() {
			return 0;
		}

		public String PrecoEmbarqueDefault() {

			return null;

		}

		public String PrecoEmbarqueComment() {

			return "";

		}

		public String PrecoEmbarquePattern() {

			return "";

		}

		public String PrecoEmbarqueOriginalDbColumnName() {

			return "PrecoEmbarque";

		}

		public Double AliquotaISS;

		public Double getAliquotaISS() {
			return this.AliquotaISS;
		}

		public Boolean AliquotaISSIsNullable() {
			return true;
		}

		public Boolean AliquotaISSIsKey() {
			return false;
		}

		public Integer AliquotaISSLength() {
			return 15;
		}

		public Integer AliquotaISSPrecision() {
			return 0;
		}

		public String AliquotaISSDefault() {

			return "";

		}

		public String AliquotaISSComment() {

			return "";

		}

		public String AliquotaISSPattern() {

			return "";

		}

		public String AliquotaISSOriginalDbColumnName() {

			return "AliquotaISS";

		}

		public Double BaseIPI;

		public Double getBaseIPI() {
			return this.BaseIPI;
		}

		public Boolean BaseIPIIsNullable() {
			return true;
		}

		public Boolean BaseIPIIsKey() {
			return false;
		}

		public Integer BaseIPILength() {
			return 15;
		}

		public Integer BaseIPIPrecision() {
			return 0;
		}

		public String BaseIPIDefault() {

			return "";

		}

		public String BaseIPIComment() {

			return "";

		}

		public String BaseIPIPattern() {

			return "";

		}

		public String BaseIPIOriginalDbColumnName() {

			return "BaseIPI";

		}

		public Double BaseISS;

		public Double getBaseISS() {
			return this.BaseISS;
		}

		public Boolean BaseISSIsNullable() {
			return true;
		}

		public Boolean BaseISSIsKey() {
			return false;
		}

		public Integer BaseISSLength() {
			return 15;
		}

		public Integer BaseISSPrecision() {
			return 0;
		}

		public String BaseISSDefault() {

			return "";

		}

		public String BaseISSComment() {

			return "";

		}

		public String BaseISSPattern() {

			return "";

		}

		public String BaseISSOriginalDbColumnName() {

			return "BaseISS";

		}

		public Double ValorISS;

		public Double getValorISS() {
			return this.ValorISS;
		}

		public Boolean ValorISSIsNullable() {
			return true;
		}

		public Boolean ValorISSIsKey() {
			return false;
		}

		public Integer ValorISSLength() {
			return 15;
		}

		public Integer ValorISSPrecision() {
			return 0;
		}

		public String ValorISSDefault() {

			return "";

		}

		public String ValorISSComment() {

			return "";

		}

		public String ValorISSPattern() {

			return "";

		}

		public String ValorISSOriginalDbColumnName() {

			return "ValorISS";

		}

		public Double Seguro;

		public Double getSeguro() {
			return this.Seguro;
		}

		public Boolean SeguroIsNullable() {
			return true;
		}

		public Boolean SeguroIsKey() {
			return false;
		}

		public Integer SeguroLength() {
			return 15;
		}

		public Integer SeguroPrecision() {
			return 0;
		}

		public String SeguroDefault() {

			return "";

		}

		public String SeguroComment() {

			return "";

		}

		public String SeguroPattern() {

			return "";

		}

		public String SeguroOriginalDbColumnName() {

			return "Seguro";

		}

		public Double ValorFrete;

		public Double getValorFrete() {
			return this.ValorFrete;
		}

		public Boolean ValorFreteIsNullable() {
			return true;
		}

		public Boolean ValorFreteIsKey() {
			return false;
		}

		public Integer ValorFreteLength() {
			return 15;
		}

		public Integer ValorFretePrecision() {
			return 0;
		}

		public String ValorFreteDefault() {

			return "";

		}

		public String ValorFreteComment() {

			return "";

		}

		public String ValorFretePattern() {

			return "";

		}

		public String ValorFreteOriginalDbColumnName() {

			return "ValorFrete";

		}

		public String TipoDocumentoEnv;

		public String getTipoDocumentoEnv() {
			return this.TipoDocumentoEnv;
		}

		public Boolean TipoDocumentoEnvIsNullable() {
			return true;
		}

		public Boolean TipoDocumentoEnvIsKey() {
			return false;
		}

		public Integer TipoDocumentoEnvLength() {
			return 1;
		}

		public Integer TipoDocumentoEnvPrecision() {
			return 0;
		}

		public String TipoDocumentoEnvDefault() {

			return null;

		}

		public String TipoDocumentoEnvComment() {

			return "";

		}

		public String TipoDocumentoEnvPattern() {

			return "";

		}

		public String TipoDocumentoEnvOriginalDbColumnName() {

			return "TipoDocumentoEnv";

		}

		public Double Despesa;

		public Double getDespesa() {
			return this.Despesa;
		}

		public Boolean DespesaIsNullable() {
			return true;
		}

		public Boolean DespesaIsKey() {
			return false;
		}

		public Integer DespesaLength() {
			return 15;
		}

		public Integer DespesaPrecision() {
			return 0;
		}

		public String DespesaDefault() {

			return "";

		}

		public String DespesaComment() {

			return "";

		}

		public String DespesaPattern() {

			return "";

		}

		public String DespesaOriginalDbColumnName() {

			return "Despesa";

		}

		public String OK;

		public String getOK() {
			return this.OK;
		}

		public Boolean OKIsNullable() {
			return true;
		}

		public Boolean OKIsKey() {
			return false;
		}

		public Integer OKLength() {
			return 2;
		}

		public Integer OKPrecision() {
			return 0;
		}

		public String OKDefault() {

			return null;

		}

		public String OKComment() {

			return "";

		}

		public String OKPattern() {

			return "";

		}

		public String OKOriginalDbColumnName() {

			return "OK";

		}

		public String CLVL;

		public String getCLVL() {
			return this.CLVL;
		}

		public Boolean CLVLIsNullable() {
			return true;
		}

		public Boolean CLVLIsKey() {
			return false;
		}

		public Integer CLVLLength() {
			return 9;
		}

		public Integer CLVLPrecision() {
			return 0;
		}

		public String CLVLDefault() {

			return null;

		}

		public String CLVLComment() {

			return "";

		}

		public String CLVLPattern() {

			return "";

		}

		public String CLVLOriginalDbColumnName() {

			return "CLVL";

		}

		public Double BaseINSS;

		public Double getBaseINSS() {
			return this.BaseINSS;
		}

		public Boolean BaseINSSIsNullable() {
			return true;
		}

		public Boolean BaseINSSIsKey() {
			return false;
		}

		public Integer BaseINSSLength() {
			return 15;
		}

		public Integer BaseINSSPrecision() {
			return 0;
		}

		public String BaseINSSDefault() {

			return "";

		}

		public String BaseINSSComment() {

			return "";

		}

		public String BaseINSSPattern() {

			return "";

		}

		public String BaseINSSOriginalDbColumnName() {

			return "BaseINSS";

		}

		public Double ICMFrete;

		public Double getICMFrete() {
			return this.ICMFrete;
		}

		public Boolean ICMFreteIsNullable() {
			return true;
		}

		public Boolean ICMFreteIsKey() {
			return false;
		}

		public Integer ICMFreteLength() {
			return 15;
		}

		public Integer ICMFretePrecision() {
			return 0;
		}

		public String ICMFreteDefault() {

			return "";

		}

		public String ICMFreteComment() {

			return "";

		}

		public String ICMFretePattern() {

			return "";

		}

		public String ICMFreteOriginalDbColumnName() {

			return "ICMFrete";

		}

		public String Servico;

		public String getServico() {
			return this.Servico;
		}

		public Boolean ServicoIsNullable() {
			return true;
		}

		public Boolean ServicoIsKey() {
			return false;
		}

		public Integer ServicoLength() {
			return 3;
		}

		public Integer ServicoPrecision() {
			return 0;
		}

		public String ServicoDefault() {

			return null;

		}

		public String ServicoComment() {

			return "";

		}

		public String ServicoPattern() {

			return "";

		}

		public String ServicoOriginalDbColumnName() {

			return "Servico";

		}

		public String STServ;

		public String getSTServ() {
			return this.STServ;
		}

		public Boolean STServIsNullable() {
			return true;
		}

		public Boolean STServIsKey() {
			return false;
		}

		public Integer STServLength() {
			return 1;
		}

		public Integer STServPrecision() {
			return 0;
		}

		public String STServDefault() {

			return null;

		}

		public String STServComment() {

			return "";

		}

		public String STServPattern() {

			return "";

		}

		public String STServOriginalDbColumnName() {

			return "STServ";

		}

		public Double ValorINSS;

		public Double getValorINSS() {
			return this.ValorINSS;
		}

		public Boolean ValorINSSIsNullable() {
			return true;
		}

		public Boolean ValorINSSIsKey() {
			return false;
		}

		public Integer ValorINSSLength() {
			return 15;
		}

		public Integer ValorINSSPrecision() {
			return 0;
		}

		public String ValorINSSDefault() {

			return "";

		}

		public String ValorINSSComment() {

			return "";

		}

		public String ValorINSSPattern() {

			return "";

		}

		public String ValorINSSOriginalDbColumnName() {

			return "ValorINSS";

		}

		public String TipoDoc;

		public String getTipoDoc() {
			return this.TipoDoc;
		}

		public Boolean TipoDocIsNullable() {
			return true;
		}

		public Boolean TipoDocIsKey() {
			return false;
		}

		public Integer TipoDocLength() {
			return 2;
		}

		public Integer TipoDocPrecision() {
			return 0;
		}

		public String TipoDocDefault() {

			return null;

		}

		public String TipoDocComment() {

			return "";

		}

		public String TipoDocPattern() {

			return "";

		}

		public String TipoDocOriginalDbColumnName() {

			return "TipoDoc";

		}

		public String TipoRem;

		public String getTipoRem() {
			return this.TipoRem;
		}

		public Boolean TipoRemIsNullable() {
			return true;
		}

		public Boolean TipoRemIsKey() {
			return false;
		}

		public Integer TipoRemLength() {
			return 1;
		}

		public Integer TipoRemPrecision() {
			return 0;
		}

		public String TipoRemDefault() {

			return null;

		}

		public String TipoRemComment() {

			return "";

		}

		public String TipoRemPattern() {

			return "";

		}

		public String TipoRemOriginalDbColumnName() {

			return "TipoRem";

		}

		public Double ValorBruto;

		public Double getValorBruto() {
			return this.ValorBruto;
		}

		public Boolean ValorBrutoIsNullable() {
			return true;
		}

		public Boolean ValorBrutoIsKey() {
			return false;
		}

		public Integer ValorBrutoLength() {
			return 15;
		}

		public Integer ValorBrutoPrecision() {
			return 0;
		}

		public String ValorBrutoDefault() {

			return "";

		}

		public String ValorBrutoComment() {

			return "";

		}

		public String ValorBrutoPattern() {

			return "";

		}

		public String ValorBrutoOriginalDbColumnName() {

			return "ValorBruto";

		}

		public java.util.Date DataDigit;

		public java.util.Date getDataDigit() {
			return this.DataDigit;
		}

		public Boolean DataDigitIsNullable() {
			return true;
		}

		public Boolean DataDigitIsKey() {
			return false;
		}

		public Integer DataDigitLength() {
			return 10;
		}

		public Integer DataDigitPrecision() {
			return 0;
		}

		public String DataDigitDefault() {

			return null;

		}

		public String DataDigitComment() {

			return "";

		}

		public String DataDigitPattern() {

			return "dd-MM-yyyy";

		}

		public String DataDigitOriginalDbColumnName() {

			return "DataDigit";

		}

		public String SitTrib;

		public String getSitTrib() {
			return this.SitTrib;
		}

		public Boolean SitTribIsNullable() {
			return true;
		}

		public Boolean SitTribIsKey() {
			return false;
		}

		public Integer SitTribLength() {
			return 5;
		}

		public Integer SitTribPrecision() {
			return 0;
		}

		public String SitTribDefault() {

			return null;

		}

		public String SitTribComment() {

			return "";

		}

		public String SitTribPattern() {

			return "";

		}

		public String SitTribOriginalDbColumnName() {

			return "SitTrib";

		}

		public String GrpCST;

		public String getGrpCST() {
			return this.GrpCST;
		}

		public Boolean GrpCSTIsNullable() {
			return true;
		}

		public Boolean GrpCSTIsKey() {
			return false;
		}

		public Integer GrpCSTLength() {
			return 3;
		}

		public Integer GrpCSTPrecision() {
			return 0;
		}

		public String GrpCSTDefault() {

			return null;

		}

		public String GrpCSTComment() {

			return "";

		}

		public String GrpCSTPattern() {

			return "";

		}

		public String GrpCSTOriginalDbColumnName() {

			return "GrpCST";

		}

		public String FCICOD;

		public String getFCICOD() {
			return this.FCICOD;
		}

		public Boolean FCICODIsNullable() {
			return true;
		}

		public Boolean FCICODIsKey() {
			return false;
		}

		public Integer FCICODLength() {
			return 36;
		}

		public Integer FCICODPrecision() {
			return 0;
		}

		public String FCICODDefault() {

			return null;

		}

		public String FCICODComment() {

			return "";

		}

		public String FCICODPattern() {

			return "";

		}

		public String FCICODOriginalDbColumnName() {

			return "FCICOD";

		}

		public Double AliquotaSOL;

		public Double getAliquotaSOL() {
			return this.AliquotaSOL;
		}

		public Boolean AliquotaSOLIsNullable() {
			return true;
		}

		public Boolean AliquotaSOLIsKey() {
			return false;
		}

		public Integer AliquotaSOLLength() {
			return 15;
		}

		public Integer AliquotaSOLPrecision() {
			return 0;
		}

		public String AliquotaSOLDefault() {

			return "";

		}

		public String AliquotaSOLComment() {

			return "";

		}

		public String AliquotaSOLPattern() {

			return "";

		}

		public String AliquotaSOLOriginalDbColumnName() {

			return "AliquotaSOL";

		}

		public String TNatRec;

		public String getTNatRec() {
			return this.TNatRec;
		}

		public Boolean TNatRecIsNullable() {
			return true;
		}

		public Boolean TNatRecIsKey() {
			return false;
		}

		public Integer TNatRecLength() {
			return 4;
		}

		public Integer TNatRecPrecision() {
			return 0;
		}

		public String TNatRecDefault() {

			return null;

		}

		public String TNatRecComment() {

			return "";

		}

		public String TNatRecPattern() {

			return "";

		}

		public String TNatRecOriginalDbColumnName() {

			return "TNatRec";

		}

		public String CNatRec;

		public String getCNatRec() {
			return this.CNatRec;
		}

		public Boolean CNatRecIsNullable() {
			return true;
		}

		public Boolean CNatRecIsKey() {
			return false;
		}

		public Integer CNatRecLength() {
			return 3;
		}

		public Integer CNatRecPrecision() {
			return 0;
		}

		public String CNatRecDefault() {

			return null;

		}

		public String CNatRecComment() {

			return "";

		}

		public String CNatRecPattern() {

			return "";

		}

		public String CNatRecOriginalDbColumnName() {

			return "CNatRec";

		}

		public String Estoque;

		public String getEstoque() {
			return this.Estoque;
		}

		public Boolean EstoqueIsNullable() {
			return true;
		}

		public Boolean EstoqueIsKey() {
			return false;
		}

		public Integer EstoqueLength() {
			return 1;
		}

		public Integer EstoquePrecision() {
			return 0;
		}

		public String EstoqueDefault() {

			return null;

		}

		public String EstoqueComment() {

			return "";

		}

		public String EstoquePattern() {

			return "";

		}

		public String EstoqueOriginalDbColumnName() {

			return "Estoque";

		}

		public Double AliqCSL;

		public Double getAliqCSL() {
			return this.AliqCSL;
		}

		public Boolean AliqCSLIsNullable() {
			return true;
		}

		public Boolean AliqCSLIsKey() {
			return false;
		}

		public Integer AliqCSLLength() {
			return 15;
		}

		public Integer AliqCSLPrecision() {
			return 0;
		}

		public String AliqCSLDefault() {

			return "";

		}

		public String AliqCSLComment() {

			return "";

		}

		public String AliqCSLPattern() {

			return "";

		}

		public String AliqCSLOriginalDbColumnName() {

			return "AliqCSL";

		}

		public Double AliqPIS;

		public Double getAliqPIS() {
			return this.AliqPIS;
		}

		public Boolean AliqPISIsNullable() {
			return true;
		}

		public Boolean AliqPISIsKey() {
			return false;
		}

		public Integer AliqPISLength() {
			return 15;
		}

		public Integer AliqPISPrecision() {
			return 0;
		}

		public String AliqPISDefault() {

			return "";

		}

		public String AliqPISComment() {

			return "";

		}

		public String AliqPISPattern() {

			return "";

		}

		public String AliqPISOriginalDbColumnName() {

			return "AliqPIS";

		}

		public Double AliqCOF;

		public Double getAliqCOF() {
			return this.AliqCOF;
		}

		public Boolean AliqCOFIsNullable() {
			return true;
		}

		public Boolean AliqCOFIsKey() {
			return false;
		}

		public Integer AliqCOFLength() {
			return 15;
		}

		public Integer AliqCOFPrecision() {
			return 0;
		}

		public String AliqCOFDefault() {

			return "";

		}

		public String AliqCOFComment() {

			return "";

		}

		public String AliqCOFPattern() {

			return "";

		}

		public String AliqCOFOriginalDbColumnName() {

			return "AliqCOF";

		}

		public Double BaseDes;

		public Double getBaseDes() {
			return this.BaseDes;
		}

		public Boolean BaseDesIsNullable() {
			return true;
		}

		public Boolean BaseDesIsKey() {
			return false;
		}

		public Integer BaseDesLength() {
			return 15;
		}

		public Integer BaseDesPrecision() {
			return 0;
		}

		public String BaseDesDefault() {

			return "";

		}

		public String BaseDesComment() {

			return "";

		}

		public String BaseDesPattern() {

			return "";

		}

		public String BaseDesOriginalDbColumnName() {

			return "BaseDes";

		}

		public Double AliqCMP;

		public Double getAliqCMP() {
			return this.AliqCMP;
		}

		public Boolean AliqCMPIsNullable() {
			return true;
		}

		public Boolean AliqCMPIsKey() {
			return false;
		}

		public Integer AliqCMPLength() {
			return 15;
		}

		public Integer AliqCMPPrecision() {
			return 0;
		}

		public String AliqCMPDefault() {

			return "";

		}

		public String AliqCMPComment() {

			return "";

		}

		public String AliqCMPPattern() {

			return "";

		}

		public String AliqCMPOriginalDbColumnName() {

			return "AliqCMP";

		}

		public Double Difal;

		public Double getDifal() {
			return this.Difal;
		}

		public Boolean DifalIsNullable() {
			return true;
		}

		public Boolean DifalIsKey() {
			return false;
		}

		public Integer DifalLength() {
			return 15;
		}

		public Integer DifalPrecision() {
			return 0;
		}

		public String DifalDefault() {

			return "";

		}

		public String DifalComment() {

			return "";

		}

		public String DifalPattern() {

			return "";

		}

		public String DifalOriginalDbColumnName() {

			return "Difal";

		}

		public Double ICMSCom;

		public Double getICMSCom() {
			return this.ICMSCom;
		}

		public Boolean ICMSComIsNullable() {
			return true;
		}

		public Boolean ICMSComIsKey() {
			return false;
		}

		public Integer ICMSComLength() {
			return 15;
		}

		public Integer ICMSComPrecision() {
			return 0;
		}

		public String ICMSComDefault() {

			return "";

		}

		public String ICMSComComment() {

			return "";

		}

		public String ICMSComPattern() {

			return "";

		}

		public String ICMSComOriginalDbColumnName() {

			return "ICMSCom";

		}

		public Double PDDes;

		public Double getPDDes() {
			return this.PDDes;
		}

		public Boolean PDDesIsNullable() {
			return true;
		}

		public Boolean PDDesIsKey() {
			return false;
		}

		public Integer PDDesLength() {
			return 15;
		}

		public Integer PDDesPrecision() {
			return 0;
		}

		public String PDDesDefault() {

			return "";

		}

		public String PDDesComment() {

			return "";

		}

		public String PDDesPattern() {

			return "";

		}

		public String PDDesOriginalDbColumnName() {

			return "PDDes";

		}

		public Double PDOrig;

		public Double getPDOrig() {
			return this.PDOrig;
		}

		public Boolean PDOrigIsNullable() {
			return true;
		}

		public Boolean PDOrigIsKey() {
			return false;
		}

		public Integer PDOrigLength() {
			return 15;
		}

		public Integer PDOrigPrecision() {
			return 0;
		}

		public String PDOrigDefault() {

			return "";

		}

		public String PDOrigComment() {

			return "";

		}

		public String PDOrigPattern() {

			return "";

		}

		public String PDOrigOriginalDbColumnName() {

			return "PDOrig";

		}

		public Double VFCPDIF;

		public Double getVFCPDIF() {
			return this.VFCPDIF;
		}

		public Boolean VFCPDIFIsNullable() {
			return true;
		}

		public Boolean VFCPDIFIsKey() {
			return false;
		}

		public Integer VFCPDIFLength() {
			return 15;
		}

		public Integer VFCPDIFPrecision() {
			return 0;
		}

		public String VFCPDIFDefault() {

			return "";

		}

		public String VFCPDIFComment() {

			return "";

		}

		public String VFCPDIFPattern() {

			return "";

		}

		public String VFCPDIFOriginalDbColumnName() {

			return "VFCPDIF";

		}

		public Double AlFCCMP;

		public Double getAlFCCMP() {
			return this.AlFCCMP;
		}

		public Boolean AlFCCMPIsNullable() {
			return true;
		}

		public Boolean AlFCCMPIsKey() {
			return false;
		}

		public Integer AlFCCMPLength() {
			return 15;
		}

		public Integer AlFCCMPPrecision() {
			return 0;
		}

		public String AlFCCMPDefault() {

			return "";

		}

		public String AlFCCMPComment() {

			return "";

		}

		public String AlFCCMPPattern() {

			return "";

		}

		public String AlFCCMPOriginalDbColumnName() {

			return "AlFCCMP";

		}

		public Double BaseCPB;

		public Double getBaseCPB() {
			return this.BaseCPB;
		}

		public Boolean BaseCPBIsNullable() {
			return true;
		}

		public Boolean BaseCPBIsKey() {
			return false;
		}

		public Integer BaseCPBLength() {
			return 15;
		}

		public Integer BaseCPBPrecision() {
			return 0;
		}

		public String BaseCPBDefault() {

			return "";

		}

		public String BaseCPBComment() {

			return "";

		}

		public String BaseCPBPattern() {

			return "";

		}

		public String BaseCPBOriginalDbColumnName() {

			return "BaseCPB";

		}

		public Double AliqPro;

		public Double getAliqPro() {
			return this.AliqPro;
		}

		public Boolean AliqProIsNullable() {
			return true;
		}

		public Boolean AliqProIsKey() {
			return false;
		}

		public Integer AliqProLength() {
			return 15;
		}

		public Integer AliqProPrecision() {
			return 0;
		}

		public String AliqProDefault() {

			return "";

		}

		public String AliqProComment() {

			return "";

		}

		public String AliqProPattern() {

			return "";

		}

		public String AliqProOriginalDbColumnName() {

			return "AliqPro";

		}

		public Double Margem;

		public Double getMargem() {
			return this.Margem;
		}

		public Boolean MargemIsNullable() {
			return true;
		}

		public Boolean MargemIsKey() {
			return false;
		}

		public Integer MargemLength() {
			return 15;
		}

		public Integer MargemPrecision() {
			return 0;
		}

		public String MargemDefault() {

			return "";

		}

		public String MargemComment() {

			return "";

		}

		public String MargemPattern() {

			return "";

		}

		public String MargemOriginalDbColumnName() {

			return "Margem";

		}

		public Double UBCSALD;

		public Double getUBCSALD() {
			return this.UBCSALD;
		}

		public Boolean UBCSALDIsNullable() {
			return true;
		}

		public Boolean UBCSALDIsKey() {
			return false;
		}

		public Integer UBCSALDLength() {
			return 15;
		}

		public Integer UBCSALDPrecision() {
			return 0;
		}

		public String UBCSALDDefault() {

			return "";

		}

		public String UBCSALDComment() {

			return "";

		}

		public String UBCSALDPattern() {

			return "";

		}

		public String UBCSALDOriginalDbColumnName() {

			return "UBCSALD";

		}

		public String UBCCHBX;

		public String getUBCCHBX() {
			return this.UBCCHBX;
		}

		public Boolean UBCCHBXIsNullable() {
			return true;
		}

		public Boolean UBCCHBXIsKey() {
			return false;
		}

		public Integer UBCCHBXLength() {
			return 40;
		}

		public Integer UBCCHBXPrecision() {
			return 0;
		}

		public String UBCCHBXDefault() {

			return null;

		}

		public String UBCCHBXComment() {

			return "";

		}

		public String UBCCHBXPattern() {

			return "";

		}

		public String UBCCHBXOriginalDbColumnName() {

			return "UBCCHBX";

		}

		public Double ALFCPST;

		public Double getALFCPST() {
			return this.ALFCPST;
		}

		public Boolean ALFCPSTIsNullable() {
			return true;
		}

		public Boolean ALFCPSTIsKey() {
			return false;
		}

		public Integer ALFCPSTLength() {
			return 15;
		}

		public Integer ALFCPSTPrecision() {
			return 0;
		}

		public String ALFCPSTDefault() {

			return "";

		}

		public String ALFCPSTComment() {

			return "";

		}

		public String ALFCPSTPattern() {

			return "";

		}

		public String ALFCPSTOriginalDbColumnName() {

			return "ALFCPST";

		}

		public Double ALQFECP;

		public Double getALQFECP() {
			return this.ALQFECP;
		}

		public Boolean ALQFECPIsNullable() {
			return true;
		}

		public Boolean ALQFECPIsKey() {
			return false;
		}

		public Integer ALQFECPLength() {
			return 15;
		}

		public Integer ALQFECPPrecision() {
			return 0;
		}

		public String ALQFECPDefault() {

			return "";

		}

		public String ALQFECPComment() {

			return "";

		}

		public String ALQFECPPattern() {

			return "";

		}

		public String ALQFECPOriginalDbColumnName() {

			return "ALQFECP";

		}

		public Double ICMSDIF;

		public Double getICMSDIF() {
			return this.ICMSDIF;
		}

		public Boolean ICMSDIFIsNullable() {
			return true;
		}

		public Boolean ICMSDIFIsKey() {
			return false;
		}

		public Integer ICMSDIFLength() {
			return 15;
		}

		public Integer ICMSDIFPrecision() {
			return 0;
		}

		public String ICMSDIFDefault() {

			return "";

		}

		public String ICMSDIFComment() {

			return "";

		}

		public String ICMSDIFPattern() {

			return "";

		}

		public String ICMSDIFOriginalDbColumnName() {

			return "ICMSDIF";

		}

		public Double VALFECP;

		public Double getVALFECP() {
			return this.VALFECP;
		}

		public Boolean VALFECPIsNullable() {
			return true;
		}

		public Boolean VALFECPIsKey() {
			return false;
		}

		public Integer VALFECPLength() {
			return 15;
		}

		public Integer VALFECPPrecision() {
			return 0;
		}

		public String VALFECPDefault() {

			return "";

		}

		public String VALFECPComment() {

			return "";

		}

		public String VALFECPPattern() {

			return "";

		}

		public String VALFECPOriginalDbColumnName() {

			return "VALFECP";

		}

		public Double VFECPST;

		public Double getVFECPST() {
			return this.VFECPST;
		}

		public Boolean VFECPSTIsNullable() {
			return true;
		}

		public Boolean VFECPSTIsKey() {
			return false;
		}

		public Integer VFECPSTLength() {
			return 15;
		}

		public Integer VFECPSTPrecision() {
			return 0;
		}

		public String VFECPSTDefault() {

			return "";

		}

		public String VFECPSTComment() {

			return "";

		}

		public String VFECPSTPattern() {

			return "";

		}

		public String VFECPSTOriginalDbColumnName() {

			return "VFECPST";

		}

		public Double VOPDIF;

		public Double getVOPDIF() {
			return this.VOPDIF;
		}

		public Boolean VOPDIFIsNullable() {
			return true;
		}

		public Boolean VOPDIFIsKey() {
			return false;
		}

		public Integer VOPDIFLength() {
			return 15;
		}

		public Integer VOPDIFPrecision() {
			return 0;
		}

		public String VOPDIFDefault() {

			return "";

		}

		public String VOPDIFComment() {

			return "";

		}

		public String VOPDIFPattern() {

			return "";

		}

		public String VOPDIFOriginalDbColumnName() {

			return "VOPDIF";

		}

		public String Deletado;

		public String getDeletado() {
			return this.Deletado;
		}

		public Boolean DeletadoIsNullable() {
			return true;
		}

		public Boolean DeletadoIsKey() {
			return false;
		}

		public Integer DeletadoLength() {
			return 1;
		}

		public Integer DeletadoPrecision() {
			return 0;
		}

		public String DeletadoDefault() {

			return null;

		}

		public String DeletadoComment() {

			return "";

		}

		public String DeletadoPattern() {

			return "";

		}

		public String DeletadoOriginalDbColumnName() {

			return "Deletado";

		}

		public Integer Ukey;

		public Integer getUkey() {
			return this.Ukey;
		}

		public Boolean UkeyIsNullable() {
			return true;
		}

		public Boolean UkeyIsKey() {
			return false;
		}

		public Integer UkeyLength() {
			return 10;
		}

		public Integer UkeyPrecision() {
			return 0;
		}

		public String UkeyDefault() {

			return "";

		}

		public String UkeyComment() {

			return "";

		}

		public String UkeyPattern() {

			return "";

		}

		public String UkeyOriginalDbColumnName() {

			return "Ukey";

		}

		public Integer Registro;

		public Integer getRegistro() {
			return this.Registro;
		}

		public Boolean RegistroIsNullable() {
			return true;
		}

		public Boolean RegistroIsKey() {
			return false;
		}

		public Integer RegistroLength() {
			return 10;
		}

		public Integer RegistroPrecision() {
			return 0;
		}

		public String RegistroDefault() {

			return "";

		}

		public String RegistroComment() {

			return "";

		}

		public String RegistroPattern() {

			return "";

		}

		public String RegistroOriginalDbColumnName() {

			return "Registro";

		}

		public java.util.Date DataUltimaAlteracao;

		public java.util.Date getDataUltimaAlteracao() {
			return this.DataUltimaAlteracao;
		}

		public Boolean DataUltimaAlteracaoIsNullable() {
			return true;
		}

		public Boolean DataUltimaAlteracaoIsKey() {
			return false;
		}

		public Integer DataUltimaAlteracaoLength() {
			return 23;
		}

		public Integer DataUltimaAlteracaoPrecision() {
			return 3;
		}

		public String DataUltimaAlteracaoDefault() {

			return null;

		}

		public String DataUltimaAlteracaoComment() {

			return "";

		}

		public String DataUltimaAlteracaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataUltimaAlteracaoOriginalDbColumnName() {

			return "DataUltimaAlteracao";

		}

		public java.util.Date DataInsercao;

		public java.util.Date getDataInsercao() {
			return this.DataInsercao;
		}

		public Boolean DataInsercaoIsNullable() {
			return true;
		}

		public Boolean DataInsercaoIsKey() {
			return false;
		}

		public Integer DataInsercaoLength() {
			return 23;
		}

		public Integer DataInsercaoPrecision() {
			return 3;
		}

		public String DataInsercaoDefault() {

			return null;

		}

		public String DataInsercaoComment() {

			return "";

		}

		public String DataInsercaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataInsercaoOriginalDbColumnName() {

			return "DataInsercao";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Filial == null) ? 0 : this.Filial.hashCode());

				result = prime * result + ((this.SequenciaItem == null) ? 0 : this.SequenciaItem.hashCode());

				result = prime * result + ((this.CodigoProduto == null) ? 0 : this.CodigoProduto.hashCode());

				result = prime * result + ((this.NotaFiscal == null) ? 0 : this.NotaFiscal.hashCode());

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

			if (this.Filial == null) {
				if (other.Filial != null)
					return false;

			} else if (!this.Filial.equals(other.Filial))

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

			if (this.NotaFiscal == null) {
				if (other.NotaFiscal != null)
					return false;

			} else if (!this.NotaFiscal.equals(other.NotaFiscal))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.Filial = this.Filial;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.SegundaUnidade = this.SegundaUnidade;
			other.UnidadeMedida = this.UnidadeMedida;
			other.Quantidade = this.Quantidade;
			other.PrecoVenda = this.PrecoVenda;
			other.PrecoTotal = this.PrecoTotal;
			other.ValorIPI = this.ValorIPI;
			other.ValorICMS = this.ValorICMS;
			other.CodigoTes = this.CodigoTes;
			other.CFOP = this.CFOP;
			other.Desconto = this.Desconto;
			other.IPI = this.IPI;
			other.ValorCSSL = this.ValorCSSL;
			other.AliquotaICM = this.AliquotaICM;
			other.Peso = this.Peso;
			other.NumeroPedido = this.NumeroPedido;
			other.ItemPV = this.ItemPV;
			other.Cliente = this.Cliente;
			other.Loja = this.Loja;
			other.Armazem = this.Armazem;
			other.NotaFiscal = this.NotaFiscal;
			other.Serie = this.Serie;
			other.Grupo = this.Grupo;
			other.TipoProduto = this.TipoProduto;
			other.DataEmissao = this.DataEmissao;
			other.CustoProduto = this.CustoProduto;
			other.Custo2 = this.Custo2;
			other.Custo3 = this.Custo3;
			other.Custo4 = this.Custo4;
			other.Custo5 = this.Custo5;
			other.PrecoUnitario = this.PrecoUnitario;
			other.QuantidadeSegundaUnidade = this.QuantidadeSegundaUnidade;
			other.NumeroSequencial = this.NumeroSequencial;
			other.Estado = this.Estado;
			other.DescontoValor = this.DescontoValor;
			other.Tipo = this.Tipo;
			other.NotaFiscalOrigem = this.NotaFiscalOrigem;
			other.SerieOrigem = this.SerieOrigem;
			other.QuantidadeDevolucao = this.QuantidadeDevolucao;
			other.ValorDevolucao = this.ValorDevolucao;
			other.OrigemLancamento = this.OrigemLancamento;
			other.BaseICMS = this.BaseICMS;
			other.BaseICM = this.BaseICM;
			other.ValorAcrescimo = this.ValorAcrescimo;
			other.ICMSRetido = this.ICMSRetido;
			other.Comissao1 = this.Comissao1;
			other.Comissao2 = this.Comissao2;
			other.Comissao3 = this.Comissao3;
			other.Comissao4 = this.Comissao4;
			other.Comissao5 = this.Comissao5;
			other.NumeroLote = this.NumeroLote;
			other.Lote = this.Lote;
			other.DataValidade = this.DataValidade;
			other.ClasseFiscal = this.ClasseFiscal;
			other.BaseImposto5 = this.BaseImposto5;
			other.BaseImposto6 = this.BaseImposto6;
			other.ValorImposto5 = this.ValorImposto5;
			other.ValorImposto6 = this.ValorImposto6;
			other.AliquotaImposto5 = this.AliquotaImposto5;
			other.AliquotaImposto6 = this.AliquotaImposto6;
			other.CentroCusto = this.CentroCusto;
			other.Endereco = this.Endereco;
			other.ValorImportacao = this.ValorImportacao;
			other.DataFabricacao = this.DataFabricacao;
			other.AliquotaINSS = this.AliquotaINSS;
			other.AbatimentoINSS = this.AbatimentoINSS;
			other.PrecoEmbarque = this.PrecoEmbarque;
			other.AliquotaISS = this.AliquotaISS;
			other.BaseIPI = this.BaseIPI;
			other.BaseISS = this.BaseISS;
			other.ValorISS = this.ValorISS;
			other.Seguro = this.Seguro;
			other.ValorFrete = this.ValorFrete;
			other.TipoDocumentoEnv = this.TipoDocumentoEnv;
			other.Despesa = this.Despesa;
			other.OK = this.OK;
			other.CLVL = this.CLVL;
			other.BaseINSS = this.BaseINSS;
			other.ICMFrete = this.ICMFrete;
			other.Servico = this.Servico;
			other.STServ = this.STServ;
			other.ValorINSS = this.ValorINSS;
			other.TipoDoc = this.TipoDoc;
			other.TipoRem = this.TipoRem;
			other.ValorBruto = this.ValorBruto;
			other.DataDigit = this.DataDigit;
			other.SitTrib = this.SitTrib;
			other.GrpCST = this.GrpCST;
			other.FCICOD = this.FCICOD;
			other.AliquotaSOL = this.AliquotaSOL;
			other.TNatRec = this.TNatRec;
			other.CNatRec = this.CNatRec;
			other.Estoque = this.Estoque;
			other.AliqCSL = this.AliqCSL;
			other.AliqPIS = this.AliqPIS;
			other.AliqCOF = this.AliqCOF;
			other.BaseDes = this.BaseDes;
			other.AliqCMP = this.AliqCMP;
			other.Difal = this.Difal;
			other.ICMSCom = this.ICMSCom;
			other.PDDes = this.PDDes;
			other.PDOrig = this.PDOrig;
			other.VFCPDIF = this.VFCPDIF;
			other.AlFCCMP = this.AlFCCMP;
			other.BaseCPB = this.BaseCPB;
			other.AliqPro = this.AliqPro;
			other.Margem = this.Margem;
			other.UBCSALD = this.UBCSALD;
			other.UBCCHBX = this.UBCCHBX;
			other.ALFCPST = this.ALFCPST;
			other.ALQFECP = this.ALQFECP;
			other.ICMSDIF = this.ICMSDIF;
			other.VALFECP = this.VALFECP;
			other.VFECPST = this.VFECPST;
			other.VOPDIF = this.VOPDIF;
			other.Deletado = this.Deletado;
			other.Ukey = this.Ukey;
			other.Registro = this.Registro;
			other.DataUltimaAlteracao = this.DataUltimaAlteracao;
			other.DataInsercao = this.DataInsercao;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.Filial = this.Filial;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.NotaFiscal = this.NotaFiscal;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_ItensNotaSaida.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_ItensNotaSaida.length == 0) {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_ItensNotaSaida.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_ItensNotaSaida.length == 0) {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_ItensNotaSaida) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.SequenciaItem = readString(dis);

					this.CodigoProduto = readString(dis);

					this.SegundaUnidade = readString(dis);

					this.UnidadeMedida = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantidade = null;
					} else {
						this.Quantidade = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoVenda = null;
					} else {
						this.PrecoVenda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoTotal = null;
					} else {
						this.PrecoTotal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorIPI = null;
					} else {
						this.ValorIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMS = null;
					} else {
						this.ValorICMS = dis.readDouble();
					}

					this.CodigoTes = readString(dis);

					this.CFOP = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Desconto = null;
					} else {
						this.Desconto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IPI = null;
					} else {
						this.IPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorCSSL = null;
					} else {
						this.ValorCSSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaICM = null;
					} else {
						this.AliquotaICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Peso = null;
					} else {
						this.Peso = dis.readDouble();
					}

					this.NumeroPedido = readString(dis);

					this.ItemPV = readString(dis);

					this.Cliente = readString(dis);

					this.Loja = readString(dis);

					this.Armazem = readString(dis);

					this.NotaFiscal = readString(dis);

					this.Serie = readString(dis);

					this.Grupo = readString(dis);

					this.TipoProduto = readString(dis);

					this.DataEmissao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoProduto = null;
					} else {
						this.CustoProduto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo2 = null;
					} else {
						this.Custo2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo3 = null;
					} else {
						this.Custo3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo4 = null;
					} else {
						this.Custo4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo5 = null;
					} else {
						this.Custo5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeSegundaUnidade = null;
					} else {
						this.QuantidadeSegundaUnidade = dis.readDouble();
					}

					this.NumeroSequencial = readString(dis);

					this.Estado = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DescontoValor = null;
					} else {
						this.DescontoValor = dis.readDouble();
					}

					this.Tipo = readString(dis);

					this.NotaFiscalOrigem = readString(dis);

					this.SerieOrigem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeDevolucao = null;
					} else {
						this.QuantidadeDevolucao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDevolucao = null;
					} else {
						this.ValorDevolucao = dis.readDouble();
					}

					this.OrigemLancamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseICMS = null;
					} else {
						this.BaseICMS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseICM = null;
					} else {
						this.BaseICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcrescimo = null;
					} else {
						this.ValorAcrescimo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSRetido = null;
					} else {
						this.ICMSRetido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao1 = null;
					} else {
						this.Comissao1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao2 = null;
					} else {
						this.Comissao2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao3 = null;
					} else {
						this.Comissao3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao4 = null;
					} else {
						this.Comissao4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao5 = null;
					} else {
						this.Comissao5 = dis.readDouble();
					}

					this.NumeroLote = readString(dis);

					this.Lote = readString(dis);

					this.DataValidade = readDate(dis);

					this.ClasseFiscal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto5 = null;
					} else {
						this.BaseImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto6 = null;
					} else {
						this.BaseImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto5 = null;
					} else {
						this.ValorImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto6 = null;
					} else {
						this.ValorImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto5 = null;
					} else {
						this.AliquotaImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto6 = null;
					} else {
						this.AliquotaImposto6 = dis.readDouble();
					}

					this.CentroCusto = readString(dis);

					this.Endereco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorImportacao = null;
					} else {
						this.ValorImportacao = dis.readDouble();
					}

					this.DataFabricacao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaINSS = null;
					} else {
						this.AliquotaINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AbatimentoINSS = null;
					} else {
						this.AbatimentoINSS = dis.readDouble();
					}

					this.PrecoEmbarque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaISS = null;
					} else {
						this.AliquotaISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseIPI = null;
					} else {
						this.BaseIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseISS = null;
					} else {
						this.BaseISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorISS = null;
					} else {
						this.ValorISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Seguro = null;
					} else {
						this.Seguro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorFrete = null;
					} else {
						this.ValorFrete = dis.readDouble();
					}

					this.TipoDocumentoEnv = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Despesa = null;
					} else {
						this.Despesa = dis.readDouble();
					}

					this.OK = readString(dis);

					this.CLVL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseINSS = null;
					} else {
						this.BaseINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMFrete = null;
					} else {
						this.ICMFrete = dis.readDouble();
					}

					this.Servico = readString(dis);

					this.STServ = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorINSS = null;
					} else {
						this.ValorINSS = dis.readDouble();
					}

					this.TipoDoc = readString(dis);

					this.TipoRem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorBruto = null;
					} else {
						this.ValorBruto = dis.readDouble();
					}

					this.DataDigit = readDate(dis);

					this.SitTrib = readString(dis);

					this.GrpCST = readString(dis);

					this.FCICOD = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaSOL = null;
					} else {
						this.AliquotaSOL = dis.readDouble();
					}

					this.TNatRec = readString(dis);

					this.CNatRec = readString(dis);

					this.Estoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliqCSL = null;
					} else {
						this.AliqCSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPIS = null;
					} else {
						this.AliqPIS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCOF = null;
					} else {
						this.AliqCOF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseDes = null;
					} else {
						this.BaseDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCMP = null;
					} else {
						this.AliqCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Difal = null;
					} else {
						this.Difal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSCom = null;
					} else {
						this.ICMSCom = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDDes = null;
					} else {
						this.PDDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDOrig = null;
					} else {
						this.PDOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFCPDIF = null;
					} else {
						this.VFCPDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AlFCCMP = null;
					} else {
						this.AlFCCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseCPB = null;
					} else {
						this.BaseCPB = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPro = null;
					} else {
						this.AliqPro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Margem = null;
					} else {
						this.Margem = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.UBCSALD = null;
					} else {
						this.UBCSALD = dis.readDouble();
					}

					this.UBCCHBX = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ALFCPST = null;
					} else {
						this.ALFCPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ALQFECP = null;
					} else {
						this.ALQFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDIF = null;
					} else {
						this.ICMSDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VALFECP = null;
					} else {
						this.VALFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFECPST = null;
					} else {
						this.VFECPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VOPDIF = null;
					} else {
						this.VOPDIF = dis.readDouble();
					}

					this.Deletado = readString(dis);

					this.Ukey = readInteger(dis);

					this.Registro = readInteger(dis);

					this.DataUltimaAlteracao = readDate(dis);

					this.DataInsercao = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_ItensNotaSaida) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.SequenciaItem = readString(dis);

					this.CodigoProduto = readString(dis);

					this.SegundaUnidade = readString(dis);

					this.UnidadeMedida = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantidade = null;
					} else {
						this.Quantidade = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoVenda = null;
					} else {
						this.PrecoVenda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoTotal = null;
					} else {
						this.PrecoTotal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorIPI = null;
					} else {
						this.ValorIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMS = null;
					} else {
						this.ValorICMS = dis.readDouble();
					}

					this.CodigoTes = readString(dis);

					this.CFOP = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Desconto = null;
					} else {
						this.Desconto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IPI = null;
					} else {
						this.IPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorCSSL = null;
					} else {
						this.ValorCSSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaICM = null;
					} else {
						this.AliquotaICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Peso = null;
					} else {
						this.Peso = dis.readDouble();
					}

					this.NumeroPedido = readString(dis);

					this.ItemPV = readString(dis);

					this.Cliente = readString(dis);

					this.Loja = readString(dis);

					this.Armazem = readString(dis);

					this.NotaFiscal = readString(dis);

					this.Serie = readString(dis);

					this.Grupo = readString(dis);

					this.TipoProduto = readString(dis);

					this.DataEmissao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoProduto = null;
					} else {
						this.CustoProduto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo2 = null;
					} else {
						this.Custo2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo3 = null;
					} else {
						this.Custo3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo4 = null;
					} else {
						this.Custo4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo5 = null;
					} else {
						this.Custo5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeSegundaUnidade = null;
					} else {
						this.QuantidadeSegundaUnidade = dis.readDouble();
					}

					this.NumeroSequencial = readString(dis);

					this.Estado = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DescontoValor = null;
					} else {
						this.DescontoValor = dis.readDouble();
					}

					this.Tipo = readString(dis);

					this.NotaFiscalOrigem = readString(dis);

					this.SerieOrigem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeDevolucao = null;
					} else {
						this.QuantidadeDevolucao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDevolucao = null;
					} else {
						this.ValorDevolucao = dis.readDouble();
					}

					this.OrigemLancamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseICMS = null;
					} else {
						this.BaseICMS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseICM = null;
					} else {
						this.BaseICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcrescimo = null;
					} else {
						this.ValorAcrescimo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSRetido = null;
					} else {
						this.ICMSRetido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao1 = null;
					} else {
						this.Comissao1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao2 = null;
					} else {
						this.Comissao2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao3 = null;
					} else {
						this.Comissao3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao4 = null;
					} else {
						this.Comissao4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao5 = null;
					} else {
						this.Comissao5 = dis.readDouble();
					}

					this.NumeroLote = readString(dis);

					this.Lote = readString(dis);

					this.DataValidade = readDate(dis);

					this.ClasseFiscal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto5 = null;
					} else {
						this.BaseImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto6 = null;
					} else {
						this.BaseImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto5 = null;
					} else {
						this.ValorImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto6 = null;
					} else {
						this.ValorImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto5 = null;
					} else {
						this.AliquotaImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto6 = null;
					} else {
						this.AliquotaImposto6 = dis.readDouble();
					}

					this.CentroCusto = readString(dis);

					this.Endereco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorImportacao = null;
					} else {
						this.ValorImportacao = dis.readDouble();
					}

					this.DataFabricacao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaINSS = null;
					} else {
						this.AliquotaINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AbatimentoINSS = null;
					} else {
						this.AbatimentoINSS = dis.readDouble();
					}

					this.PrecoEmbarque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaISS = null;
					} else {
						this.AliquotaISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseIPI = null;
					} else {
						this.BaseIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseISS = null;
					} else {
						this.BaseISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorISS = null;
					} else {
						this.ValorISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Seguro = null;
					} else {
						this.Seguro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorFrete = null;
					} else {
						this.ValorFrete = dis.readDouble();
					}

					this.TipoDocumentoEnv = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Despesa = null;
					} else {
						this.Despesa = dis.readDouble();
					}

					this.OK = readString(dis);

					this.CLVL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseINSS = null;
					} else {
						this.BaseINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMFrete = null;
					} else {
						this.ICMFrete = dis.readDouble();
					}

					this.Servico = readString(dis);

					this.STServ = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorINSS = null;
					} else {
						this.ValorINSS = dis.readDouble();
					}

					this.TipoDoc = readString(dis);

					this.TipoRem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorBruto = null;
					} else {
						this.ValorBruto = dis.readDouble();
					}

					this.DataDigit = readDate(dis);

					this.SitTrib = readString(dis);

					this.GrpCST = readString(dis);

					this.FCICOD = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaSOL = null;
					} else {
						this.AliquotaSOL = dis.readDouble();
					}

					this.TNatRec = readString(dis);

					this.CNatRec = readString(dis);

					this.Estoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliqCSL = null;
					} else {
						this.AliqCSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPIS = null;
					} else {
						this.AliqPIS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCOF = null;
					} else {
						this.AliqCOF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseDes = null;
					} else {
						this.BaseDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCMP = null;
					} else {
						this.AliqCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Difal = null;
					} else {
						this.Difal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSCom = null;
					} else {
						this.ICMSCom = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDDes = null;
					} else {
						this.PDDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDOrig = null;
					} else {
						this.PDOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFCPDIF = null;
					} else {
						this.VFCPDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AlFCCMP = null;
					} else {
						this.AlFCCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseCPB = null;
					} else {
						this.BaseCPB = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPro = null;
					} else {
						this.AliqPro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Margem = null;
					} else {
						this.Margem = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.UBCSALD = null;
					} else {
						this.UBCSALD = dis.readDouble();
					}

					this.UBCCHBX = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ALFCPST = null;
					} else {
						this.ALFCPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ALQFECP = null;
					} else {
						this.ALQFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDIF = null;
					} else {
						this.ICMSDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VALFECP = null;
					} else {
						this.VALFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFECPST = null;
					} else {
						this.VFECPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VOPDIF = null;
					} else {
						this.VOPDIF = dis.readDouble();
					}

					this.Deletado = readString(dis);

					this.Ukey = readInteger(dis);

					this.Registro = readInteger(dis);

					this.DataUltimaAlteracao = readDate(dis);

					this.DataInsercao = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// String

				writeString(this.SegundaUnidade, dos);

				// String

				writeString(this.UnidadeMedida, dos);

				// Double

				if (this.Quantidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Quantidade);
				}

				// Double

				if (this.PrecoVenda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoVenda);
				}

				// Double

				if (this.PrecoTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoTotal);
				}

				// Double

				if (this.ValorIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorIPI);
				}

				// Double

				if (this.ValorICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMS);
				}

				// String

				writeString(this.CodigoTes, dos);

				// String

				writeString(this.CFOP, dos);

				// Double

				if (this.Desconto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Desconto);
				}

				// Double

				if (this.IPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IPI);
				}

				// Double

				if (this.ValorCSSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorCSSL);
				}

				// Double

				if (this.AliquotaICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaICM);
				}

				// Double

				if (this.Peso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Peso);
				}

				// String

				writeString(this.NumeroPedido, dos);

				// String

				writeString(this.ItemPV, dos);

				// String

				writeString(this.Cliente, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Armazem, dos);

				// String

				writeString(this.NotaFiscal, dos);

				// String

				writeString(this.Serie, dos);

				// String

				writeString(this.Grupo, dos);

				// String

				writeString(this.TipoProduto, dos);

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// Double

				if (this.CustoProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoProduto);
				}

				// Double

				if (this.Custo2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo2);
				}

				// Double

				if (this.Custo3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo3);
				}

				// Double

				if (this.Custo4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo4);
				}

				// Double

				if (this.Custo5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo5);
				}

				// Double

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoUnitario);
				}

				// Double

				if (this.QuantidadeSegundaUnidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeSegundaUnidade);
				}

				// String

				writeString(this.NumeroSequencial, dos);

				// String

				writeString(this.Estado, dos);

				// Double

				if (this.DescontoValor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DescontoValor);
				}

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.NotaFiscalOrigem, dos);

				// String

				writeString(this.SerieOrigem, dos);

				// Double

				if (this.QuantidadeDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeDevolucao);
				}

				// Double

				if (this.ValorDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDevolucao);
				}

				// String

				writeString(this.OrigemLancamento, dos);

				// Double

				if (this.BaseICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICMS);
				}

				// Double

				if (this.BaseICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICM);
				}

				// Double

				if (this.ValorAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcrescimo);
				}

				// Double

				if (this.ICMSRetido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSRetido);
				}

				// Double

				if (this.Comissao1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao1);
				}

				// Double

				if (this.Comissao2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao2);
				}

				// Double

				if (this.Comissao3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao3);
				}

				// Double

				if (this.Comissao4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao4);
				}

				// Double

				if (this.Comissao5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao5);
				}

				// String

				writeString(this.NumeroLote, dos);

				// String

				writeString(this.Lote, dos);

				// java.util.Date

				writeDate(this.DataValidade, dos);

				// String

				writeString(this.ClasseFiscal, dos);

				// Double

				if (this.BaseImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto5);
				}

				// Double

				if (this.BaseImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto6);
				}

				// Double

				if (this.ValorImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto5);
				}

				// Double

				if (this.ValorImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto6);
				}

				// Double

				if (this.AliquotaImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto5);
				}

				// Double

				if (this.AliquotaImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto6);
				}

				// String

				writeString(this.CentroCusto, dos);

				// String

				writeString(this.Endereco, dos);

				// Double

				if (this.ValorImportacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImportacao);
				}

				// java.util.Date

				writeDate(this.DataFabricacao, dos);

				// Double

				if (this.AliquotaINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaINSS);
				}

				// Double

				if (this.AbatimentoINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AbatimentoINSS);
				}

				// String

				writeString(this.PrecoEmbarque, dos);

				// Double

				if (this.AliquotaISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaISS);
				}

				// Double

				if (this.BaseIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIPI);
				}

				// Double

				if (this.BaseISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseISS);
				}

				// Double

				if (this.ValorISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorISS);
				}

				// Double

				if (this.Seguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Seguro);
				}

				// Double

				if (this.ValorFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorFrete);
				}

				// String

				writeString(this.TipoDocumentoEnv, dos);

				// Double

				if (this.Despesa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Despesa);
				}

				// String

				writeString(this.OK, dos);

				// String

				writeString(this.CLVL, dos);

				// Double

				if (this.BaseINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseINSS);
				}

				// Double

				if (this.ICMFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMFrete);
				}

				// String

				writeString(this.Servico, dos);

				// String

				writeString(this.STServ, dos);

				// Double

				if (this.ValorINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorINSS);
				}

				// String

				writeString(this.TipoDoc, dos);

				// String

				writeString(this.TipoRem, dos);

				// Double

				if (this.ValorBruto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorBruto);
				}

				// java.util.Date

				writeDate(this.DataDigit, dos);

				// String

				writeString(this.SitTrib, dos);

				// String

				writeString(this.GrpCST, dos);

				// String

				writeString(this.FCICOD, dos);

				// Double

				if (this.AliquotaSOL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaSOL);
				}

				// String

				writeString(this.TNatRec, dos);

				// String

				writeString(this.CNatRec, dos);

				// String

				writeString(this.Estoque, dos);

				// Double

				if (this.AliqCSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCSL);
				}

				// Double

				if (this.AliqPIS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPIS);
				}

				// Double

				if (this.AliqCOF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCOF);
				}

				// Double

				if (this.BaseDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseDes);
				}

				// Double

				if (this.AliqCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCMP);
				}

				// Double

				if (this.Difal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Difal);
				}

				// Double

				if (this.ICMSCom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSCom);
				}

				// Double

				if (this.PDDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDDes);
				}

				// Double

				if (this.PDOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDOrig);
				}

				// Double

				if (this.VFCPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFCPDIF);
				}

				// Double

				if (this.AlFCCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AlFCCMP);
				}

				// Double

				if (this.BaseCPB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseCPB);
				}

				// Double

				if (this.AliqPro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPro);
				}

				// Double

				if (this.Margem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Margem);
				}

				// Double

				if (this.UBCSALD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.UBCSALD);
				}

				// String

				writeString(this.UBCCHBX, dos);

				// Double

				if (this.ALFCPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALFCPST);
				}

				// Double

				if (this.ALQFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALQFECP);
				}

				// Double

				if (this.ICMSDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDIF);
				}

				// Double

				if (this.VALFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VALFECP);
				}

				// Double

				if (this.VFECPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFECPST);
				}

				// Double

				if (this.VOPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VOPDIF);
				}

				// String

				writeString(this.Deletado, dos);

				// Integer

				writeInteger(this.Ukey, dos);

				// Integer

				writeInteger(this.Registro, dos);

				// java.util.Date

				writeDate(this.DataUltimaAlteracao, dos);

				// java.util.Date

				writeDate(this.DataInsercao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// String

				writeString(this.SegundaUnidade, dos);

				// String

				writeString(this.UnidadeMedida, dos);

				// Double

				if (this.Quantidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Quantidade);
				}

				// Double

				if (this.PrecoVenda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoVenda);
				}

				// Double

				if (this.PrecoTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoTotal);
				}

				// Double

				if (this.ValorIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorIPI);
				}

				// Double

				if (this.ValorICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMS);
				}

				// String

				writeString(this.CodigoTes, dos);

				// String

				writeString(this.CFOP, dos);

				// Double

				if (this.Desconto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Desconto);
				}

				// Double

				if (this.IPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IPI);
				}

				// Double

				if (this.ValorCSSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorCSSL);
				}

				// Double

				if (this.AliquotaICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaICM);
				}

				// Double

				if (this.Peso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Peso);
				}

				// String

				writeString(this.NumeroPedido, dos);

				// String

				writeString(this.ItemPV, dos);

				// String

				writeString(this.Cliente, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Armazem, dos);

				// String

				writeString(this.NotaFiscal, dos);

				// String

				writeString(this.Serie, dos);

				// String

				writeString(this.Grupo, dos);

				// String

				writeString(this.TipoProduto, dos);

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// Double

				if (this.CustoProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoProduto);
				}

				// Double

				if (this.Custo2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo2);
				}

				// Double

				if (this.Custo3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo3);
				}

				// Double

				if (this.Custo4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo4);
				}

				// Double

				if (this.Custo5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo5);
				}

				// Double

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoUnitario);
				}

				// Double

				if (this.QuantidadeSegundaUnidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeSegundaUnidade);
				}

				// String

				writeString(this.NumeroSequencial, dos);

				// String

				writeString(this.Estado, dos);

				// Double

				if (this.DescontoValor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DescontoValor);
				}

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.NotaFiscalOrigem, dos);

				// String

				writeString(this.SerieOrigem, dos);

				// Double

				if (this.QuantidadeDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeDevolucao);
				}

				// Double

				if (this.ValorDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDevolucao);
				}

				// String

				writeString(this.OrigemLancamento, dos);

				// Double

				if (this.BaseICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICMS);
				}

				// Double

				if (this.BaseICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICM);
				}

				// Double

				if (this.ValorAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcrescimo);
				}

				// Double

				if (this.ICMSRetido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSRetido);
				}

				// Double

				if (this.Comissao1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao1);
				}

				// Double

				if (this.Comissao2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao2);
				}

				// Double

				if (this.Comissao3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao3);
				}

				// Double

				if (this.Comissao4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao4);
				}

				// Double

				if (this.Comissao5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao5);
				}

				// String

				writeString(this.NumeroLote, dos);

				// String

				writeString(this.Lote, dos);

				// java.util.Date

				writeDate(this.DataValidade, dos);

				// String

				writeString(this.ClasseFiscal, dos);

				// Double

				if (this.BaseImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto5);
				}

				// Double

				if (this.BaseImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto6);
				}

				// Double

				if (this.ValorImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto5);
				}

				// Double

				if (this.ValorImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto6);
				}

				// Double

				if (this.AliquotaImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto5);
				}

				// Double

				if (this.AliquotaImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto6);
				}

				// String

				writeString(this.CentroCusto, dos);

				// String

				writeString(this.Endereco, dos);

				// Double

				if (this.ValorImportacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImportacao);
				}

				// java.util.Date

				writeDate(this.DataFabricacao, dos);

				// Double

				if (this.AliquotaINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaINSS);
				}

				// Double

				if (this.AbatimentoINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AbatimentoINSS);
				}

				// String

				writeString(this.PrecoEmbarque, dos);

				// Double

				if (this.AliquotaISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaISS);
				}

				// Double

				if (this.BaseIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIPI);
				}

				// Double

				if (this.BaseISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseISS);
				}

				// Double

				if (this.ValorISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorISS);
				}

				// Double

				if (this.Seguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Seguro);
				}

				// Double

				if (this.ValorFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorFrete);
				}

				// String

				writeString(this.TipoDocumentoEnv, dos);

				// Double

				if (this.Despesa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Despesa);
				}

				// String

				writeString(this.OK, dos);

				// String

				writeString(this.CLVL, dos);

				// Double

				if (this.BaseINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseINSS);
				}

				// Double

				if (this.ICMFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMFrete);
				}

				// String

				writeString(this.Servico, dos);

				// String

				writeString(this.STServ, dos);

				// Double

				if (this.ValorINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorINSS);
				}

				// String

				writeString(this.TipoDoc, dos);

				// String

				writeString(this.TipoRem, dos);

				// Double

				if (this.ValorBruto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorBruto);
				}

				// java.util.Date

				writeDate(this.DataDigit, dos);

				// String

				writeString(this.SitTrib, dos);

				// String

				writeString(this.GrpCST, dos);

				// String

				writeString(this.FCICOD, dos);

				// Double

				if (this.AliquotaSOL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaSOL);
				}

				// String

				writeString(this.TNatRec, dos);

				// String

				writeString(this.CNatRec, dos);

				// String

				writeString(this.Estoque, dos);

				// Double

				if (this.AliqCSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCSL);
				}

				// Double

				if (this.AliqPIS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPIS);
				}

				// Double

				if (this.AliqCOF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCOF);
				}

				// Double

				if (this.BaseDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseDes);
				}

				// Double

				if (this.AliqCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCMP);
				}

				// Double

				if (this.Difal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Difal);
				}

				// Double

				if (this.ICMSCom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSCom);
				}

				// Double

				if (this.PDDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDDes);
				}

				// Double

				if (this.PDOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDOrig);
				}

				// Double

				if (this.VFCPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFCPDIF);
				}

				// Double

				if (this.AlFCCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AlFCCMP);
				}

				// Double

				if (this.BaseCPB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseCPB);
				}

				// Double

				if (this.AliqPro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPro);
				}

				// Double

				if (this.Margem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Margem);
				}

				// Double

				if (this.UBCSALD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.UBCSALD);
				}

				// String

				writeString(this.UBCCHBX, dos);

				// Double

				if (this.ALFCPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALFCPST);
				}

				// Double

				if (this.ALQFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALQFECP);
				}

				// Double

				if (this.ICMSDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDIF);
				}

				// Double

				if (this.VALFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VALFECP);
				}

				// Double

				if (this.VFECPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFECPST);
				}

				// Double

				if (this.VOPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VOPDIF);
				}

				// String

				writeString(this.Deletado, dos);

				// Integer

				writeInteger(this.Ukey, dos);

				// Integer

				writeInteger(this.Registro, dos);

				// java.util.Date

				writeDate(this.DataUltimaAlteracao, dos);

				// java.util.Date

				writeDate(this.DataInsercao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Filial=" + Filial);
			sb.append(",SequenciaItem=" + SequenciaItem);
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",SegundaUnidade=" + SegundaUnidade);
			sb.append(",UnidadeMedida=" + UnidadeMedida);
			sb.append(",Quantidade=" + String.valueOf(Quantidade));
			sb.append(",PrecoVenda=" + String.valueOf(PrecoVenda));
			sb.append(",PrecoTotal=" + String.valueOf(PrecoTotal));
			sb.append(",ValorIPI=" + String.valueOf(ValorIPI));
			sb.append(",ValorICMS=" + String.valueOf(ValorICMS));
			sb.append(",CodigoTes=" + CodigoTes);
			sb.append(",CFOP=" + CFOP);
			sb.append(",Desconto=" + String.valueOf(Desconto));
			sb.append(",IPI=" + String.valueOf(IPI));
			sb.append(",ValorCSSL=" + String.valueOf(ValorCSSL));
			sb.append(",AliquotaICM=" + String.valueOf(AliquotaICM));
			sb.append(",Peso=" + String.valueOf(Peso));
			sb.append(",NumeroPedido=" + NumeroPedido);
			sb.append(",ItemPV=" + ItemPV);
			sb.append(",Cliente=" + Cliente);
			sb.append(",Loja=" + Loja);
			sb.append(",Armazem=" + Armazem);
			sb.append(",NotaFiscal=" + NotaFiscal);
			sb.append(",Serie=" + Serie);
			sb.append(",Grupo=" + Grupo);
			sb.append(",TipoProduto=" + TipoProduto);
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",CustoProduto=" + String.valueOf(CustoProduto));
			sb.append(",Custo2=" + String.valueOf(Custo2));
			sb.append(",Custo3=" + String.valueOf(Custo3));
			sb.append(",Custo4=" + String.valueOf(Custo4));
			sb.append(",Custo5=" + String.valueOf(Custo5));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",QuantidadeSegundaUnidade=" + String.valueOf(QuantidadeSegundaUnidade));
			sb.append(",NumeroSequencial=" + NumeroSequencial);
			sb.append(",Estado=" + Estado);
			sb.append(",DescontoValor=" + String.valueOf(DescontoValor));
			sb.append(",Tipo=" + Tipo);
			sb.append(",NotaFiscalOrigem=" + NotaFiscalOrigem);
			sb.append(",SerieOrigem=" + SerieOrigem);
			sb.append(",QuantidadeDevolucao=" + String.valueOf(QuantidadeDevolucao));
			sb.append(",ValorDevolucao=" + String.valueOf(ValorDevolucao));
			sb.append(",OrigemLancamento=" + OrigemLancamento);
			sb.append(",BaseICMS=" + String.valueOf(BaseICMS));
			sb.append(",BaseICM=" + String.valueOf(BaseICM));
			sb.append(",ValorAcrescimo=" + String.valueOf(ValorAcrescimo));
			sb.append(",ICMSRetido=" + String.valueOf(ICMSRetido));
			sb.append(",Comissao1=" + String.valueOf(Comissao1));
			sb.append(",Comissao2=" + String.valueOf(Comissao2));
			sb.append(",Comissao3=" + String.valueOf(Comissao3));
			sb.append(",Comissao4=" + String.valueOf(Comissao4));
			sb.append(",Comissao5=" + String.valueOf(Comissao5));
			sb.append(",NumeroLote=" + NumeroLote);
			sb.append(",Lote=" + Lote);
			sb.append(",DataValidade=" + String.valueOf(DataValidade));
			sb.append(",ClasseFiscal=" + ClasseFiscal);
			sb.append(",BaseImposto5=" + String.valueOf(BaseImposto5));
			sb.append(",BaseImposto6=" + String.valueOf(BaseImposto6));
			sb.append(",ValorImposto5=" + String.valueOf(ValorImposto5));
			sb.append(",ValorImposto6=" + String.valueOf(ValorImposto6));
			sb.append(",AliquotaImposto5=" + String.valueOf(AliquotaImposto5));
			sb.append(",AliquotaImposto6=" + String.valueOf(AliquotaImposto6));
			sb.append(",CentroCusto=" + CentroCusto);
			sb.append(",Endereco=" + Endereco);
			sb.append(",ValorImportacao=" + String.valueOf(ValorImportacao));
			sb.append(",DataFabricacao=" + String.valueOf(DataFabricacao));
			sb.append(",AliquotaINSS=" + String.valueOf(AliquotaINSS));
			sb.append(",AbatimentoINSS=" + String.valueOf(AbatimentoINSS));
			sb.append(",PrecoEmbarque=" + PrecoEmbarque);
			sb.append(",AliquotaISS=" + String.valueOf(AliquotaISS));
			sb.append(",BaseIPI=" + String.valueOf(BaseIPI));
			sb.append(",BaseISS=" + String.valueOf(BaseISS));
			sb.append(",ValorISS=" + String.valueOf(ValorISS));
			sb.append(",Seguro=" + String.valueOf(Seguro));
			sb.append(",ValorFrete=" + String.valueOf(ValorFrete));
			sb.append(",TipoDocumentoEnv=" + TipoDocumentoEnv);
			sb.append(",Despesa=" + String.valueOf(Despesa));
			sb.append(",OK=" + OK);
			sb.append(",CLVL=" + CLVL);
			sb.append(",BaseINSS=" + String.valueOf(BaseINSS));
			sb.append(",ICMFrete=" + String.valueOf(ICMFrete));
			sb.append(",Servico=" + Servico);
			sb.append(",STServ=" + STServ);
			sb.append(",ValorINSS=" + String.valueOf(ValorINSS));
			sb.append(",TipoDoc=" + TipoDoc);
			sb.append(",TipoRem=" + TipoRem);
			sb.append(",ValorBruto=" + String.valueOf(ValorBruto));
			sb.append(",DataDigit=" + String.valueOf(DataDigit));
			sb.append(",SitTrib=" + SitTrib);
			sb.append(",GrpCST=" + GrpCST);
			sb.append(",FCICOD=" + FCICOD);
			sb.append(",AliquotaSOL=" + String.valueOf(AliquotaSOL));
			sb.append(",TNatRec=" + TNatRec);
			sb.append(",CNatRec=" + CNatRec);
			sb.append(",Estoque=" + Estoque);
			sb.append(",AliqCSL=" + String.valueOf(AliqCSL));
			sb.append(",AliqPIS=" + String.valueOf(AliqPIS));
			sb.append(",AliqCOF=" + String.valueOf(AliqCOF));
			sb.append(",BaseDes=" + String.valueOf(BaseDes));
			sb.append(",AliqCMP=" + String.valueOf(AliqCMP));
			sb.append(",Difal=" + String.valueOf(Difal));
			sb.append(",ICMSCom=" + String.valueOf(ICMSCom));
			sb.append(",PDDes=" + String.valueOf(PDDes));
			sb.append(",PDOrig=" + String.valueOf(PDOrig));
			sb.append(",VFCPDIF=" + String.valueOf(VFCPDIF));
			sb.append(",AlFCCMP=" + String.valueOf(AlFCCMP));
			sb.append(",BaseCPB=" + String.valueOf(BaseCPB));
			sb.append(",AliqPro=" + String.valueOf(AliqPro));
			sb.append(",Margem=" + String.valueOf(Margem));
			sb.append(",UBCSALD=" + String.valueOf(UBCSALD));
			sb.append(",UBCCHBX=" + UBCCHBX);
			sb.append(",ALFCPST=" + String.valueOf(ALFCPST));
			sb.append(",ALQFECP=" + String.valueOf(ALQFECP));
			sb.append(",ICMSDIF=" + String.valueOf(ICMSDIF));
			sb.append(",VALFECP=" + String.valueOf(VALFECP));
			sb.append(",VFECPST=" + String.valueOf(VFECPST));
			sb.append(",VOPDIF=" + String.valueOf(VOPDIF));
			sb.append(",Deletado=" + Deletado);
			sb.append(",Ukey=" + String.valueOf(Ukey));
			sb.append(",Registro=" + String.valueOf(Registro));
			sb.append(",DataUltimaAlteracao=" + String.valueOf(DataUltimaAlteracao));
			sb.append(",DataInsercao=" + String.valueOf(DataInsercao));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Filial == null) {
				sb.append("<null>");
			} else {
				sb.append(Filial);
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

			if (SegundaUnidade == null) {
				sb.append("<null>");
			} else {
				sb.append(SegundaUnidade);
			}

			sb.append("|");

			if (UnidadeMedida == null) {
				sb.append("<null>");
			} else {
				sb.append(UnidadeMedida);
			}

			sb.append("|");

			if (Quantidade == null) {
				sb.append("<null>");
			} else {
				sb.append(Quantidade);
			}

			sb.append("|");

			if (PrecoVenda == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoVenda);
			}

			sb.append("|");

			if (PrecoTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoTotal);
			}

			sb.append("|");

			if (ValorIPI == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorIPI);
			}

			sb.append("|");

			if (ValorICMS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorICMS);
			}

			sb.append("|");

			if (CodigoTes == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoTes);
			}

			sb.append("|");

			if (CFOP == null) {
				sb.append("<null>");
			} else {
				sb.append(CFOP);
			}

			sb.append("|");

			if (Desconto == null) {
				sb.append("<null>");
			} else {
				sb.append(Desconto);
			}

			sb.append("|");

			if (IPI == null) {
				sb.append("<null>");
			} else {
				sb.append(IPI);
			}

			sb.append("|");

			if (ValorCSSL == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorCSSL);
			}

			sb.append("|");

			if (AliquotaICM == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaICM);
			}

			sb.append("|");

			if (Peso == null) {
				sb.append("<null>");
			} else {
				sb.append(Peso);
			}

			sb.append("|");

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (ItemPV == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemPV);
			}

			sb.append("|");

			if (Cliente == null) {
				sb.append("<null>");
			} else {
				sb.append(Cliente);
			}

			sb.append("|");

			if (Loja == null) {
				sb.append("<null>");
			} else {
				sb.append(Loja);
			}

			sb.append("|");

			if (Armazem == null) {
				sb.append("<null>");
			} else {
				sb.append(Armazem);
			}

			sb.append("|");

			if (NotaFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(NotaFiscal);
			}

			sb.append("|");

			if (Serie == null) {
				sb.append("<null>");
			} else {
				sb.append(Serie);
			}

			sb.append("|");

			if (Grupo == null) {
				sb.append("<null>");
			} else {
				sb.append(Grupo);
			}

			sb.append("|");

			if (TipoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoProduto);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (CustoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoProduto);
			}

			sb.append("|");

			if (Custo2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo2);
			}

			sb.append("|");

			if (Custo3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo3);
			}

			sb.append("|");

			if (Custo4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo4);
			}

			sb.append("|");

			if (Custo5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo5);
			}

			sb.append("|");

			if (PrecoUnitario == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoUnitario);
			}

			sb.append("|");

			if (QuantidadeSegundaUnidade == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeSegundaUnidade);
			}

			sb.append("|");

			if (NumeroSequencial == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroSequencial);
			}

			sb.append("|");

			if (Estado == null) {
				sb.append("<null>");
			} else {
				sb.append(Estado);
			}

			sb.append("|");

			if (DescontoValor == null) {
				sb.append("<null>");
			} else {
				sb.append(DescontoValor);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (NotaFiscalOrigem == null) {
				sb.append("<null>");
			} else {
				sb.append(NotaFiscalOrigem);
			}

			sb.append("|");

			if (SerieOrigem == null) {
				sb.append("<null>");
			} else {
				sb.append(SerieOrigem);
			}

			sb.append("|");

			if (QuantidadeDevolucao == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeDevolucao);
			}

			sb.append("|");

			if (ValorDevolucao == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorDevolucao);
			}

			sb.append("|");

			if (OrigemLancamento == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemLancamento);
			}

			sb.append("|");

			if (BaseICMS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseICMS);
			}

			sb.append("|");

			if (BaseICM == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseICM);
			}

			sb.append("|");

			if (ValorAcrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorAcrescimo);
			}

			sb.append("|");

			if (ICMSRetido == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSRetido);
			}

			sb.append("|");

			if (Comissao1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao1);
			}

			sb.append("|");

			if (Comissao2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao2);
			}

			sb.append("|");

			if (Comissao3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao3);
			}

			sb.append("|");

			if (Comissao4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao4);
			}

			sb.append("|");

			if (Comissao5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao5);
			}

			sb.append("|");

			if (NumeroLote == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroLote);
			}

			sb.append("|");

			if (Lote == null) {
				sb.append("<null>");
			} else {
				sb.append(Lote);
			}

			sb.append("|");

			if (DataValidade == null) {
				sb.append("<null>");
			} else {
				sb.append(DataValidade);
			}

			sb.append("|");

			if (ClasseFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(ClasseFiscal);
			}

			sb.append("|");

			if (BaseImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseImposto5);
			}

			sb.append("|");

			if (BaseImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseImposto6);
			}

			sb.append("|");

			if (ValorImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImposto5);
			}

			sb.append("|");

			if (ValorImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImposto6);
			}

			sb.append("|");

			if (AliquotaImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaImposto5);
			}

			sb.append("|");

			if (AliquotaImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaImposto6);
			}

			sb.append("|");

			if (CentroCusto == null) {
				sb.append("<null>");
			} else {
				sb.append(CentroCusto);
			}

			sb.append("|");

			if (Endereco == null) {
				sb.append("<null>");
			} else {
				sb.append(Endereco);
			}

			sb.append("|");

			if (ValorImportacao == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImportacao);
			}

			sb.append("|");

			if (DataFabricacao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataFabricacao);
			}

			sb.append("|");

			if (AliquotaINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaINSS);
			}

			sb.append("|");

			if (AbatimentoINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(AbatimentoINSS);
			}

			sb.append("|");

			if (PrecoEmbarque == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoEmbarque);
			}

			sb.append("|");

			if (AliquotaISS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaISS);
			}

			sb.append("|");

			if (BaseIPI == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIPI);
			}

			sb.append("|");

			if (BaseISS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseISS);
			}

			sb.append("|");

			if (ValorISS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorISS);
			}

			sb.append("|");

			if (Seguro == null) {
				sb.append("<null>");
			} else {
				sb.append(Seguro);
			}

			sb.append("|");

			if (ValorFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorFrete);
			}

			sb.append("|");

			if (TipoDocumentoEnv == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoDocumentoEnv);
			}

			sb.append("|");

			if (Despesa == null) {
				sb.append("<null>");
			} else {
				sb.append(Despesa);
			}

			sb.append("|");

			if (OK == null) {
				sb.append("<null>");
			} else {
				sb.append(OK);
			}

			sb.append("|");

			if (CLVL == null) {
				sb.append("<null>");
			} else {
				sb.append(CLVL);
			}

			sb.append("|");

			if (BaseINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseINSS);
			}

			sb.append("|");

			if (ICMFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMFrete);
			}

			sb.append("|");

			if (Servico == null) {
				sb.append("<null>");
			} else {
				sb.append(Servico);
			}

			sb.append("|");

			if (STServ == null) {
				sb.append("<null>");
			} else {
				sb.append(STServ);
			}

			sb.append("|");

			if (ValorINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorINSS);
			}

			sb.append("|");

			if (TipoDoc == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoDoc);
			}

			sb.append("|");

			if (TipoRem == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoRem);
			}

			sb.append("|");

			if (ValorBruto == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorBruto);
			}

			sb.append("|");

			if (DataDigit == null) {
				sb.append("<null>");
			} else {
				sb.append(DataDigit);
			}

			sb.append("|");

			if (SitTrib == null) {
				sb.append("<null>");
			} else {
				sb.append(SitTrib);
			}

			sb.append("|");

			if (GrpCST == null) {
				sb.append("<null>");
			} else {
				sb.append(GrpCST);
			}

			sb.append("|");

			if (FCICOD == null) {
				sb.append("<null>");
			} else {
				sb.append(FCICOD);
			}

			sb.append("|");

			if (AliquotaSOL == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaSOL);
			}

			sb.append("|");

			if (TNatRec == null) {
				sb.append("<null>");
			} else {
				sb.append(TNatRec);
			}

			sb.append("|");

			if (CNatRec == null) {
				sb.append("<null>");
			} else {
				sb.append(CNatRec);
			}

			sb.append("|");

			if (Estoque == null) {
				sb.append("<null>");
			} else {
				sb.append(Estoque);
			}

			sb.append("|");

			if (AliqCSL == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCSL);
			}

			sb.append("|");

			if (AliqPIS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqPIS);
			}

			sb.append("|");

			if (AliqCOF == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCOF);
			}

			sb.append("|");

			if (BaseDes == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseDes);
			}

			sb.append("|");

			if (AliqCMP == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCMP);
			}

			sb.append("|");

			if (Difal == null) {
				sb.append("<null>");
			} else {
				sb.append(Difal);
			}

			sb.append("|");

			if (ICMSCom == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSCom);
			}

			sb.append("|");

			if (PDDes == null) {
				sb.append("<null>");
			} else {
				sb.append(PDDes);
			}

			sb.append("|");

			if (PDOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(PDOrig);
			}

			sb.append("|");

			if (VFCPDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(VFCPDIF);
			}

			sb.append("|");

			if (AlFCCMP == null) {
				sb.append("<null>");
			} else {
				sb.append(AlFCCMP);
			}

			sb.append("|");

			if (BaseCPB == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseCPB);
			}

			sb.append("|");

			if (AliqPro == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqPro);
			}

			sb.append("|");

			if (Margem == null) {
				sb.append("<null>");
			} else {
				sb.append(Margem);
			}

			sb.append("|");

			if (UBCSALD == null) {
				sb.append("<null>");
			} else {
				sb.append(UBCSALD);
			}

			sb.append("|");

			if (UBCCHBX == null) {
				sb.append("<null>");
			} else {
				sb.append(UBCCHBX);
			}

			sb.append("|");

			if (ALFCPST == null) {
				sb.append("<null>");
			} else {
				sb.append(ALFCPST);
			}

			sb.append("|");

			if (ALQFECP == null) {
				sb.append("<null>");
			} else {
				sb.append(ALQFECP);
			}

			sb.append("|");

			if (ICMSDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSDIF);
			}

			sb.append("|");

			if (VALFECP == null) {
				sb.append("<null>");
			} else {
				sb.append(VALFECP);
			}

			sb.append("|");

			if (VFECPST == null) {
				sb.append("<null>");
			} else {
				sb.append(VFECPST);
			}

			sb.append("|");

			if (VOPDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(VOPDIF);
			}

			sb.append("|");

			if (Deletado == null) {
				sb.append("<null>");
			} else {
				sb.append(Deletado);
			}

			sb.append("|");

			if (Ukey == null) {
				sb.append("<null>");
			} else {
				sb.append(Ukey);
			}

			sb.append("|");

			if (Registro == null) {
				sb.append("<null>");
			} else {
				sb.append(Registro);
			}

			sb.append("|");

			if (DataUltimaAlteracao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimaAlteracao);
			}

			sb.append("|");

			if (DataInsercao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataInsercao);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Filial, other.Filial);
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

			returnValue = checkNullsAndCompare(this.NotaFiscal, other.NotaFiscal);
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
		final static byte[] commonByteArrayLock_HYDRONORTH_ItensNotaSaida = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Filial;

		public String getFilial() {
			return this.Filial;
		}

		public Boolean FilialIsNullable() {
			return true;
		}

		public Boolean FilialIsKey() {
			return true;
		}

		public Integer FilialLength() {
			return 2;
		}

		public Integer FilialPrecision() {
			return 0;
		}

		public String FilialDefault() {

			return null;

		}

		public String FilialComment() {

			return "";

		}

		public String FilialPattern() {

			return "";

		}

		public String FilialOriginalDbColumnName() {

			return "Filial";

		}

		public String SequenciaItem;

		public String getSequenciaItem() {
			return this.SequenciaItem;
		}

		public Boolean SequenciaItemIsNullable() {
			return true;
		}

		public Boolean SequenciaItemIsKey() {
			return true;
		}

		public Integer SequenciaItemLength() {
			return 2;
		}

		public Integer SequenciaItemPrecision() {
			return 0;
		}

		public String SequenciaItemDefault() {

			return null;

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
			return 15;
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

		public String SegundaUnidade;

		public String getSegundaUnidade() {
			return this.SegundaUnidade;
		}

		public Boolean SegundaUnidadeIsNullable() {
			return true;
		}

		public Boolean SegundaUnidadeIsKey() {
			return false;
		}

		public Integer SegundaUnidadeLength() {
			return 6;
		}

		public Integer SegundaUnidadePrecision() {
			return 0;
		}

		public String SegundaUnidadeDefault() {

			return null;

		}

		public String SegundaUnidadeComment() {

			return "";

		}

		public String SegundaUnidadePattern() {

			return "";

		}

		public String SegundaUnidadeOriginalDbColumnName() {

			return "SegundaUnidade";

		}

		public String UnidadeMedida;

		public String getUnidadeMedida() {
			return this.UnidadeMedida;
		}

		public Boolean UnidadeMedidaIsNullable() {
			return true;
		}

		public Boolean UnidadeMedidaIsKey() {
			return false;
		}

		public Integer UnidadeMedidaLength() {
			return 6;
		}

		public Integer UnidadeMedidaPrecision() {
			return 0;
		}

		public String UnidadeMedidaDefault() {

			return null;

		}

		public String UnidadeMedidaComment() {

			return "";

		}

		public String UnidadeMedidaPattern() {

			return "";

		}

		public String UnidadeMedidaOriginalDbColumnName() {

			return "UnidadeMedida";

		}

		public Double Quantidade;

		public Double getQuantidade() {
			return this.Quantidade;
		}

		public Boolean QuantidadeIsNullable() {
			return true;
		}

		public Boolean QuantidadeIsKey() {
			return false;
		}

		public Integer QuantidadeLength() {
			return 15;
		}

		public Integer QuantidadePrecision() {
			return 0;
		}

		public String QuantidadeDefault() {

			return "";

		}

		public String QuantidadeComment() {

			return "";

		}

		public String QuantidadePattern() {

			return "";

		}

		public String QuantidadeOriginalDbColumnName() {

			return "Quantidade";

		}

		public Double PrecoVenda;

		public Double getPrecoVenda() {
			return this.PrecoVenda;
		}

		public Boolean PrecoVendaIsNullable() {
			return true;
		}

		public Boolean PrecoVendaIsKey() {
			return false;
		}

		public Integer PrecoVendaLength() {
			return 15;
		}

		public Integer PrecoVendaPrecision() {
			return 0;
		}

		public String PrecoVendaDefault() {

			return "";

		}

		public String PrecoVendaComment() {

			return "";

		}

		public String PrecoVendaPattern() {

			return "";

		}

		public String PrecoVendaOriginalDbColumnName() {

			return "PrecoVenda";

		}

		public Double PrecoTotal;

		public Double getPrecoTotal() {
			return this.PrecoTotal;
		}

		public Boolean PrecoTotalIsNullable() {
			return true;
		}

		public Boolean PrecoTotalIsKey() {
			return false;
		}

		public Integer PrecoTotalLength() {
			return 15;
		}

		public Integer PrecoTotalPrecision() {
			return 0;
		}

		public String PrecoTotalDefault() {

			return "";

		}

		public String PrecoTotalComment() {

			return "";

		}

		public String PrecoTotalPattern() {

			return "";

		}

		public String PrecoTotalOriginalDbColumnName() {

			return "PrecoTotal";

		}

		public Double ValorIPI;

		public Double getValorIPI() {
			return this.ValorIPI;
		}

		public Boolean ValorIPIIsNullable() {
			return true;
		}

		public Boolean ValorIPIIsKey() {
			return false;
		}

		public Integer ValorIPILength() {
			return 15;
		}

		public Integer ValorIPIPrecision() {
			return 0;
		}

		public String ValorIPIDefault() {

			return "";

		}

		public String ValorIPIComment() {

			return "";

		}

		public String ValorIPIPattern() {

			return "";

		}

		public String ValorIPIOriginalDbColumnName() {

			return "ValorIPI";

		}

		public Double ValorICMS;

		public Double getValorICMS() {
			return this.ValorICMS;
		}

		public Boolean ValorICMSIsNullable() {
			return true;
		}

		public Boolean ValorICMSIsKey() {
			return false;
		}

		public Integer ValorICMSLength() {
			return 15;
		}

		public Integer ValorICMSPrecision() {
			return 0;
		}

		public String ValorICMSDefault() {

			return "";

		}

		public String ValorICMSComment() {

			return "";

		}

		public String ValorICMSPattern() {

			return "";

		}

		public String ValorICMSOriginalDbColumnName() {

			return "ValorICMS";

		}

		public String CodigoTes;

		public String getCodigoTes() {
			return this.CodigoTes;
		}

		public Boolean CodigoTesIsNullable() {
			return true;
		}

		public Boolean CodigoTesIsKey() {
			return false;
		}

		public Integer CodigoTesLength() {
			return 3;
		}

		public Integer CodigoTesPrecision() {
			return 0;
		}

		public String CodigoTesDefault() {

			return null;

		}

		public String CodigoTesComment() {

			return "";

		}

		public String CodigoTesPattern() {

			return "";

		}

		public String CodigoTesOriginalDbColumnName() {

			return "CodigoTes";

		}

		public String CFOP;

		public String getCFOP() {
			return this.CFOP;
		}

		public Boolean CFOPIsNullable() {
			return true;
		}

		public Boolean CFOPIsKey() {
			return false;
		}

		public Integer CFOPLength() {
			return 5;
		}

		public Integer CFOPPrecision() {
			return 0;
		}

		public String CFOPDefault() {

			return null;

		}

		public String CFOPComment() {

			return "";

		}

		public String CFOPPattern() {

			return "";

		}

		public String CFOPOriginalDbColumnName() {

			return "CFOP";

		}

		public Double Desconto;

		public Double getDesconto() {
			return this.Desconto;
		}

		public Boolean DescontoIsNullable() {
			return true;
		}

		public Boolean DescontoIsKey() {
			return false;
		}

		public Integer DescontoLength() {
			return 15;
		}

		public Integer DescontoPrecision() {
			return 0;
		}

		public String DescontoDefault() {

			return "";

		}

		public String DescontoComment() {

			return "";

		}

		public String DescontoPattern() {

			return "";

		}

		public String DescontoOriginalDbColumnName() {

			return "Desconto";

		}

		public Double IPI;

		public Double getIPI() {
			return this.IPI;
		}

		public Boolean IPIIsNullable() {
			return true;
		}

		public Boolean IPIIsKey() {
			return false;
		}

		public Integer IPILength() {
			return 15;
		}

		public Integer IPIPrecision() {
			return 0;
		}

		public String IPIDefault() {

			return "";

		}

		public String IPIComment() {

			return "";

		}

		public String IPIPattern() {

			return "";

		}

		public String IPIOriginalDbColumnName() {

			return "IPI";

		}

		public Double ValorCSSL;

		public Double getValorCSSL() {
			return this.ValorCSSL;
		}

		public Boolean ValorCSSLIsNullable() {
			return true;
		}

		public Boolean ValorCSSLIsKey() {
			return false;
		}

		public Integer ValorCSSLLength() {
			return 15;
		}

		public Integer ValorCSSLPrecision() {
			return 0;
		}

		public String ValorCSSLDefault() {

			return "";

		}

		public String ValorCSSLComment() {

			return "";

		}

		public String ValorCSSLPattern() {

			return "";

		}

		public String ValorCSSLOriginalDbColumnName() {

			return "ValorCSSL";

		}

		public Double AliquotaICM;

		public Double getAliquotaICM() {
			return this.AliquotaICM;
		}

		public Boolean AliquotaICMIsNullable() {
			return true;
		}

		public Boolean AliquotaICMIsKey() {
			return false;
		}

		public Integer AliquotaICMLength() {
			return 15;
		}

		public Integer AliquotaICMPrecision() {
			return 0;
		}

		public String AliquotaICMDefault() {

			return "";

		}

		public String AliquotaICMComment() {

			return "";

		}

		public String AliquotaICMPattern() {

			return "";

		}

		public String AliquotaICMOriginalDbColumnName() {

			return "AliquotaICM";

		}

		public Double Peso;

		public Double getPeso() {
			return this.Peso;
		}

		public Boolean PesoIsNullable() {
			return true;
		}

		public Boolean PesoIsKey() {
			return false;
		}

		public Integer PesoLength() {
			return 15;
		}

		public Integer PesoPrecision() {
			return 0;
		}

		public String PesoDefault() {

			return "";

		}

		public String PesoComment() {

			return "";

		}

		public String PesoPattern() {

			return "";

		}

		public String PesoOriginalDbColumnName() {

			return "Peso";

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
			return 6;
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

			return "";

		}

		public String NumeroPedidoOriginalDbColumnName() {

			return "NumeroPedido";

		}

		public String ItemPV;

		public String getItemPV() {
			return this.ItemPV;
		}

		public Boolean ItemPVIsNullable() {
			return true;
		}

		public Boolean ItemPVIsKey() {
			return false;
		}

		public Integer ItemPVLength() {
			return 2;
		}

		public Integer ItemPVPrecision() {
			return 0;
		}

		public String ItemPVDefault() {

			return null;

		}

		public String ItemPVComment() {

			return "";

		}

		public String ItemPVPattern() {

			return "";

		}

		public String ItemPVOriginalDbColumnName() {

			return "ItemPV";

		}

		public String Cliente;

		public String getCliente() {
			return this.Cliente;
		}

		public Boolean ClienteIsNullable() {
			return true;
		}

		public Boolean ClienteIsKey() {
			return false;
		}

		public Integer ClienteLength() {
			return 9;
		}

		public Integer ClientePrecision() {
			return 0;
		}

		public String ClienteDefault() {

			return null;

		}

		public String ClienteComment() {

			return "";

		}

		public String ClientePattern() {

			return "";

		}

		public String ClienteOriginalDbColumnName() {

			return "Cliente";

		}

		public String Loja;

		public String getLoja() {
			return this.Loja;
		}

		public Boolean LojaIsNullable() {
			return true;
		}

		public Boolean LojaIsKey() {
			return false;
		}

		public Integer LojaLength() {
			return 3;
		}

		public Integer LojaPrecision() {
			return 0;
		}

		public String LojaDefault() {

			return null;

		}

		public String LojaComment() {

			return "";

		}

		public String LojaPattern() {

			return "";

		}

		public String LojaOriginalDbColumnName() {

			return "Loja";

		}

		public String Armazem;

		public String getArmazem() {
			return this.Armazem;
		}

		public Boolean ArmazemIsNullable() {
			return true;
		}

		public Boolean ArmazemIsKey() {
			return false;
		}

		public Integer ArmazemLength() {
			return 2;
		}

		public Integer ArmazemPrecision() {
			return 0;
		}

		public String ArmazemDefault() {

			return null;

		}

		public String ArmazemComment() {

			return "";

		}

		public String ArmazemPattern() {

			return "";

		}

		public String ArmazemOriginalDbColumnName() {

			return "Armazem";

		}

		public String NotaFiscal;

		public String getNotaFiscal() {
			return this.NotaFiscal;
		}

		public Boolean NotaFiscalIsNullable() {
			return true;
		}

		public Boolean NotaFiscalIsKey() {
			return true;
		}

		public Integer NotaFiscalLength() {
			return 9;
		}

		public Integer NotaFiscalPrecision() {
			return 0;
		}

		public String NotaFiscalDefault() {

			return null;

		}

		public String NotaFiscalComment() {

			return "";

		}

		public String NotaFiscalPattern() {

			return "";

		}

		public String NotaFiscalOriginalDbColumnName() {

			return "NotaFiscal";

		}

		public String Serie;

		public String getSerie() {
			return this.Serie;
		}

		public Boolean SerieIsNullable() {
			return true;
		}

		public Boolean SerieIsKey() {
			return false;
		}

		public Integer SerieLength() {
			return 3;
		}

		public Integer SeriePrecision() {
			return 0;
		}

		public String SerieDefault() {

			return null;

		}

		public String SerieComment() {

			return "";

		}

		public String SeriePattern() {

			return "";

		}

		public String SerieOriginalDbColumnName() {

			return "Serie";

		}

		public String Grupo;

		public String getGrupo() {
			return this.Grupo;
		}

		public Boolean GrupoIsNullable() {
			return true;
		}

		public Boolean GrupoIsKey() {
			return false;
		}

		public Integer GrupoLength() {
			return 4;
		}

		public Integer GrupoPrecision() {
			return 0;
		}

		public String GrupoDefault() {

			return null;

		}

		public String GrupoComment() {

			return "";

		}

		public String GrupoPattern() {

			return "";

		}

		public String GrupoOriginalDbColumnName() {

			return "Grupo";

		}

		public String TipoProduto;

		public String getTipoProduto() {
			return this.TipoProduto;
		}

		public Boolean TipoProdutoIsNullable() {
			return true;
		}

		public Boolean TipoProdutoIsKey() {
			return false;
		}

		public Integer TipoProdutoLength() {
			return 2;
		}

		public Integer TipoProdutoPrecision() {
			return 0;
		}

		public String TipoProdutoDefault() {

			return null;

		}

		public String TipoProdutoComment() {

			return "";

		}

		public String TipoProdutoPattern() {

			return "";

		}

		public String TipoProdutoOriginalDbColumnName() {

			return "TipoProduto";

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
			return 10;
		}

		public Integer DataEmissaoPrecision() {
			return 0;
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

		public Double CustoProduto;

		public Double getCustoProduto() {
			return this.CustoProduto;
		}

		public Boolean CustoProdutoIsNullable() {
			return true;
		}

		public Boolean CustoProdutoIsKey() {
			return false;
		}

		public Integer CustoProdutoLength() {
			return 15;
		}

		public Integer CustoProdutoPrecision() {
			return 0;
		}

		public String CustoProdutoDefault() {

			return "";

		}

		public String CustoProdutoComment() {

			return "";

		}

		public String CustoProdutoPattern() {

			return "";

		}

		public String CustoProdutoOriginalDbColumnName() {

			return "CustoProduto";

		}

		public Double Custo2;

		public Double getCusto2() {
			return this.Custo2;
		}

		public Boolean Custo2IsNullable() {
			return true;
		}

		public Boolean Custo2IsKey() {
			return false;
		}

		public Integer Custo2Length() {
			return 15;
		}

		public Integer Custo2Precision() {
			return 0;
		}

		public String Custo2Default() {

			return "";

		}

		public String Custo2Comment() {

			return "";

		}

		public String Custo2Pattern() {

			return "";

		}

		public String Custo2OriginalDbColumnName() {

			return "Custo2";

		}

		public Double Custo3;

		public Double getCusto3() {
			return this.Custo3;
		}

		public Boolean Custo3IsNullable() {
			return true;
		}

		public Boolean Custo3IsKey() {
			return false;
		}

		public Integer Custo3Length() {
			return 15;
		}

		public Integer Custo3Precision() {
			return 0;
		}

		public String Custo3Default() {

			return "";

		}

		public String Custo3Comment() {

			return "";

		}

		public String Custo3Pattern() {

			return "";

		}

		public String Custo3OriginalDbColumnName() {

			return "Custo3";

		}

		public Double Custo4;

		public Double getCusto4() {
			return this.Custo4;
		}

		public Boolean Custo4IsNullable() {
			return true;
		}

		public Boolean Custo4IsKey() {
			return false;
		}

		public Integer Custo4Length() {
			return 15;
		}

		public Integer Custo4Precision() {
			return 0;
		}

		public String Custo4Default() {

			return "";

		}

		public String Custo4Comment() {

			return "";

		}

		public String Custo4Pattern() {

			return "";

		}

		public String Custo4OriginalDbColumnName() {

			return "Custo4";

		}

		public Double Custo5;

		public Double getCusto5() {
			return this.Custo5;
		}

		public Boolean Custo5IsNullable() {
			return true;
		}

		public Boolean Custo5IsKey() {
			return false;
		}

		public Integer Custo5Length() {
			return 15;
		}

		public Integer Custo5Precision() {
			return 0;
		}

		public String Custo5Default() {

			return "";

		}

		public String Custo5Comment() {

			return "";

		}

		public String Custo5Pattern() {

			return "";

		}

		public String Custo5OriginalDbColumnName() {

			return "Custo5";

		}

		public Double PrecoUnitario;

		public Double getPrecoUnitario() {
			return this.PrecoUnitario;
		}

		public Boolean PrecoUnitarioIsNullable() {
			return true;
		}

		public Boolean PrecoUnitarioIsKey() {
			return false;
		}

		public Integer PrecoUnitarioLength() {
			return 15;
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

		public Double QuantidadeSegundaUnidade;

		public Double getQuantidadeSegundaUnidade() {
			return this.QuantidadeSegundaUnidade;
		}

		public Boolean QuantidadeSegundaUnidadeIsNullable() {
			return true;
		}

		public Boolean QuantidadeSegundaUnidadeIsKey() {
			return false;
		}

		public Integer QuantidadeSegundaUnidadeLength() {
			return 15;
		}

		public Integer QuantidadeSegundaUnidadePrecision() {
			return 0;
		}

		public String QuantidadeSegundaUnidadeDefault() {

			return "";

		}

		public String QuantidadeSegundaUnidadeComment() {

			return "";

		}

		public String QuantidadeSegundaUnidadePattern() {

			return "";

		}

		public String QuantidadeSegundaUnidadeOriginalDbColumnName() {

			return "QuantidadeSegundaUnidade";

		}

		public String NumeroSequencial;

		public String getNumeroSequencial() {
			return this.NumeroSequencial;
		}

		public Boolean NumeroSequencialIsNullable() {
			return true;
		}

		public Boolean NumeroSequencialIsKey() {
			return false;
		}

		public Integer NumeroSequencialLength() {
			return 6;
		}

		public Integer NumeroSequencialPrecision() {
			return 0;
		}

		public String NumeroSequencialDefault() {

			return null;

		}

		public String NumeroSequencialComment() {

			return "";

		}

		public String NumeroSequencialPattern() {

			return "";

		}

		public String NumeroSequencialOriginalDbColumnName() {

			return "NumeroSequencial";

		}

		public String Estado;

		public String getEstado() {
			return this.Estado;
		}

		public Boolean EstadoIsNullable() {
			return true;
		}

		public Boolean EstadoIsKey() {
			return false;
		}

		public Integer EstadoLength() {
			return 2;
		}

		public Integer EstadoPrecision() {
			return 0;
		}

		public String EstadoDefault() {

			return null;

		}

		public String EstadoComment() {

			return "";

		}

		public String EstadoPattern() {

			return "";

		}

		public String EstadoOriginalDbColumnName() {

			return "Estado";

		}

		public Double DescontoValor;

		public Double getDescontoValor() {
			return this.DescontoValor;
		}

		public Boolean DescontoValorIsNullable() {
			return true;
		}

		public Boolean DescontoValorIsKey() {
			return false;
		}

		public Integer DescontoValorLength() {
			return 15;
		}

		public Integer DescontoValorPrecision() {
			return 0;
		}

		public String DescontoValorDefault() {

			return "";

		}

		public String DescontoValorComment() {

			return "";

		}

		public String DescontoValorPattern() {

			return "";

		}

		public String DescontoValorOriginalDbColumnName() {

			return "DescontoValor";

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

		public String NotaFiscalOrigem;

		public String getNotaFiscalOrigem() {
			return this.NotaFiscalOrigem;
		}

		public Boolean NotaFiscalOrigemIsNullable() {
			return true;
		}

		public Boolean NotaFiscalOrigemIsKey() {
			return false;
		}

		public Integer NotaFiscalOrigemLength() {
			return 9;
		}

		public Integer NotaFiscalOrigemPrecision() {
			return 0;
		}

		public String NotaFiscalOrigemDefault() {

			return null;

		}

		public String NotaFiscalOrigemComment() {

			return "";

		}

		public String NotaFiscalOrigemPattern() {

			return "";

		}

		public String NotaFiscalOrigemOriginalDbColumnName() {

			return "NotaFiscalOrigem";

		}

		public String SerieOrigem;

		public String getSerieOrigem() {
			return this.SerieOrigem;
		}

		public Boolean SerieOrigemIsNullable() {
			return true;
		}

		public Boolean SerieOrigemIsKey() {
			return false;
		}

		public Integer SerieOrigemLength() {
			return 3;
		}

		public Integer SerieOrigemPrecision() {
			return 0;
		}

		public String SerieOrigemDefault() {

			return null;

		}

		public String SerieOrigemComment() {

			return "";

		}

		public String SerieOrigemPattern() {

			return "";

		}

		public String SerieOrigemOriginalDbColumnName() {

			return "SerieOrigem";

		}

		public Double QuantidadeDevolucao;

		public Double getQuantidadeDevolucao() {
			return this.QuantidadeDevolucao;
		}

		public Boolean QuantidadeDevolucaoIsNullable() {
			return true;
		}

		public Boolean QuantidadeDevolucaoIsKey() {
			return false;
		}

		public Integer QuantidadeDevolucaoLength() {
			return 15;
		}

		public Integer QuantidadeDevolucaoPrecision() {
			return 0;
		}

		public String QuantidadeDevolucaoDefault() {

			return "";

		}

		public String QuantidadeDevolucaoComment() {

			return "";

		}

		public String QuantidadeDevolucaoPattern() {

			return "";

		}

		public String QuantidadeDevolucaoOriginalDbColumnName() {

			return "QuantidadeDevolucao";

		}

		public Double ValorDevolucao;

		public Double getValorDevolucao() {
			return this.ValorDevolucao;
		}

		public Boolean ValorDevolucaoIsNullable() {
			return true;
		}

		public Boolean ValorDevolucaoIsKey() {
			return false;
		}

		public Integer ValorDevolucaoLength() {
			return 15;
		}

		public Integer ValorDevolucaoPrecision() {
			return 0;
		}

		public String ValorDevolucaoDefault() {

			return "";

		}

		public String ValorDevolucaoComment() {

			return "";

		}

		public String ValorDevolucaoPattern() {

			return "";

		}

		public String ValorDevolucaoOriginalDbColumnName() {

			return "ValorDevolucao";

		}

		public String OrigemLancamento;

		public String getOrigemLancamento() {
			return this.OrigemLancamento;
		}

		public Boolean OrigemLancamentoIsNullable() {
			return true;
		}

		public Boolean OrigemLancamentoIsKey() {
			return false;
		}

		public Integer OrigemLancamentoLength() {
			return 2;
		}

		public Integer OrigemLancamentoPrecision() {
			return 0;
		}

		public String OrigemLancamentoDefault() {

			return null;

		}

		public String OrigemLancamentoComment() {

			return "";

		}

		public String OrigemLancamentoPattern() {

			return "";

		}

		public String OrigemLancamentoOriginalDbColumnName() {

			return "OrigemLancamento";

		}

		public Double BaseICMS;

		public Double getBaseICMS() {
			return this.BaseICMS;
		}

		public Boolean BaseICMSIsNullable() {
			return true;
		}

		public Boolean BaseICMSIsKey() {
			return false;
		}

		public Integer BaseICMSLength() {
			return 15;
		}

		public Integer BaseICMSPrecision() {
			return 0;
		}

		public String BaseICMSDefault() {

			return "";

		}

		public String BaseICMSComment() {

			return "";

		}

		public String BaseICMSPattern() {

			return "";

		}

		public String BaseICMSOriginalDbColumnName() {

			return "BaseICMS";

		}

		public Double BaseICM;

		public Double getBaseICM() {
			return this.BaseICM;
		}

		public Boolean BaseICMIsNullable() {
			return true;
		}

		public Boolean BaseICMIsKey() {
			return false;
		}

		public Integer BaseICMLength() {
			return 15;
		}

		public Integer BaseICMPrecision() {
			return 0;
		}

		public String BaseICMDefault() {

			return "";

		}

		public String BaseICMComment() {

			return "";

		}

		public String BaseICMPattern() {

			return "";

		}

		public String BaseICMOriginalDbColumnName() {

			return "BaseICM";

		}

		public Double ValorAcrescimo;

		public Double getValorAcrescimo() {
			return this.ValorAcrescimo;
		}

		public Boolean ValorAcrescimoIsNullable() {
			return true;
		}

		public Boolean ValorAcrescimoIsKey() {
			return false;
		}

		public Integer ValorAcrescimoLength() {
			return 15;
		}

		public Integer ValorAcrescimoPrecision() {
			return 0;
		}

		public String ValorAcrescimoDefault() {

			return "";

		}

		public String ValorAcrescimoComment() {

			return "";

		}

		public String ValorAcrescimoPattern() {

			return "";

		}

		public String ValorAcrescimoOriginalDbColumnName() {

			return "ValorAcrescimo";

		}

		public Double ICMSRetido;

		public Double getICMSRetido() {
			return this.ICMSRetido;
		}

		public Boolean ICMSRetidoIsNullable() {
			return true;
		}

		public Boolean ICMSRetidoIsKey() {
			return false;
		}

		public Integer ICMSRetidoLength() {
			return 15;
		}

		public Integer ICMSRetidoPrecision() {
			return 0;
		}

		public String ICMSRetidoDefault() {

			return "";

		}

		public String ICMSRetidoComment() {

			return "";

		}

		public String ICMSRetidoPattern() {

			return "";

		}

		public String ICMSRetidoOriginalDbColumnName() {

			return "ICMSRetido";

		}

		public Double Comissao1;

		public Double getComissao1() {
			return this.Comissao1;
		}

		public Boolean Comissao1IsNullable() {
			return true;
		}

		public Boolean Comissao1IsKey() {
			return false;
		}

		public Integer Comissao1Length() {
			return 15;
		}

		public Integer Comissao1Precision() {
			return 0;
		}

		public String Comissao1Default() {

			return "";

		}

		public String Comissao1Comment() {

			return "";

		}

		public String Comissao1Pattern() {

			return "";

		}

		public String Comissao1OriginalDbColumnName() {

			return "Comissao1";

		}

		public Double Comissao2;

		public Double getComissao2() {
			return this.Comissao2;
		}

		public Boolean Comissao2IsNullable() {
			return true;
		}

		public Boolean Comissao2IsKey() {
			return false;
		}

		public Integer Comissao2Length() {
			return 15;
		}

		public Integer Comissao2Precision() {
			return 0;
		}

		public String Comissao2Default() {

			return "";

		}

		public String Comissao2Comment() {

			return "";

		}

		public String Comissao2Pattern() {

			return "";

		}

		public String Comissao2OriginalDbColumnName() {

			return "Comissao2";

		}

		public Double Comissao3;

		public Double getComissao3() {
			return this.Comissao3;
		}

		public Boolean Comissao3IsNullable() {
			return true;
		}

		public Boolean Comissao3IsKey() {
			return false;
		}

		public Integer Comissao3Length() {
			return 15;
		}

		public Integer Comissao3Precision() {
			return 0;
		}

		public String Comissao3Default() {

			return "";

		}

		public String Comissao3Comment() {

			return "";

		}

		public String Comissao3Pattern() {

			return "";

		}

		public String Comissao3OriginalDbColumnName() {

			return "Comissao3";

		}

		public Double Comissao4;

		public Double getComissao4() {
			return this.Comissao4;
		}

		public Boolean Comissao4IsNullable() {
			return true;
		}

		public Boolean Comissao4IsKey() {
			return false;
		}

		public Integer Comissao4Length() {
			return 15;
		}

		public Integer Comissao4Precision() {
			return 0;
		}

		public String Comissao4Default() {

			return "";

		}

		public String Comissao4Comment() {

			return "";

		}

		public String Comissao4Pattern() {

			return "";

		}

		public String Comissao4OriginalDbColumnName() {

			return "Comissao4";

		}

		public Double Comissao5;

		public Double getComissao5() {
			return this.Comissao5;
		}

		public Boolean Comissao5IsNullable() {
			return true;
		}

		public Boolean Comissao5IsKey() {
			return false;
		}

		public Integer Comissao5Length() {
			return 15;
		}

		public Integer Comissao5Precision() {
			return 0;
		}

		public String Comissao5Default() {

			return "";

		}

		public String Comissao5Comment() {

			return "";

		}

		public String Comissao5Pattern() {

			return "";

		}

		public String Comissao5OriginalDbColumnName() {

			return "Comissao5";

		}

		public String NumeroLote;

		public String getNumeroLote() {
			return this.NumeroLote;
		}

		public Boolean NumeroLoteIsNullable() {
			return true;
		}

		public Boolean NumeroLoteIsKey() {
			return false;
		}

		public Integer NumeroLoteLength() {
			return 10;
		}

		public Integer NumeroLotePrecision() {
			return 0;
		}

		public String NumeroLoteDefault() {

			return null;

		}

		public String NumeroLoteComment() {

			return "";

		}

		public String NumeroLotePattern() {

			return "";

		}

		public String NumeroLoteOriginalDbColumnName() {

			return "NumeroLote";

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
			return 6;
		}

		public Integer LotePrecision() {
			return 0;
		}

		public String LoteDefault() {

			return null;

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

		public java.util.Date DataValidade;

		public java.util.Date getDataValidade() {
			return this.DataValidade;
		}

		public Boolean DataValidadeIsNullable() {
			return true;
		}

		public Boolean DataValidadeIsKey() {
			return false;
		}

		public Integer DataValidadeLength() {
			return 10;
		}

		public Integer DataValidadePrecision() {
			return 0;
		}

		public String DataValidadeDefault() {

			return null;

		}

		public String DataValidadeComment() {

			return "";

		}

		public String DataValidadePattern() {

			return "dd-MM-yyyy";

		}

		public String DataValidadeOriginalDbColumnName() {

			return "DataValidade";

		}

		public String ClasseFiscal;

		public String getClasseFiscal() {
			return this.ClasseFiscal;
		}

		public Boolean ClasseFiscalIsNullable() {
			return true;
		}

		public Boolean ClasseFiscalIsKey() {
			return false;
		}

		public Integer ClasseFiscalLength() {
			return 3;
		}

		public Integer ClasseFiscalPrecision() {
			return 0;
		}

		public String ClasseFiscalDefault() {

			return null;

		}

		public String ClasseFiscalComment() {

			return "";

		}

		public String ClasseFiscalPattern() {

			return "";

		}

		public String ClasseFiscalOriginalDbColumnName() {

			return "ClasseFiscal";

		}

		public Double BaseImposto5;

		public Double getBaseImposto5() {
			return this.BaseImposto5;
		}

		public Boolean BaseImposto5IsNullable() {
			return true;
		}

		public Boolean BaseImposto5IsKey() {
			return false;
		}

		public Integer BaseImposto5Length() {
			return 15;
		}

		public Integer BaseImposto5Precision() {
			return 0;
		}

		public String BaseImposto5Default() {

			return "";

		}

		public String BaseImposto5Comment() {

			return "";

		}

		public String BaseImposto5Pattern() {

			return "";

		}

		public String BaseImposto5OriginalDbColumnName() {

			return "BaseImposto5";

		}

		public Double BaseImposto6;

		public Double getBaseImposto6() {
			return this.BaseImposto6;
		}

		public Boolean BaseImposto6IsNullable() {
			return true;
		}

		public Boolean BaseImposto6IsKey() {
			return false;
		}

		public Integer BaseImposto6Length() {
			return 15;
		}

		public Integer BaseImposto6Precision() {
			return 0;
		}

		public String BaseImposto6Default() {

			return "";

		}

		public String BaseImposto6Comment() {

			return "";

		}

		public String BaseImposto6Pattern() {

			return "";

		}

		public String BaseImposto6OriginalDbColumnName() {

			return "BaseImposto6";

		}

		public Double ValorImposto5;

		public Double getValorImposto5() {
			return this.ValorImposto5;
		}

		public Boolean ValorImposto5IsNullable() {
			return true;
		}

		public Boolean ValorImposto5IsKey() {
			return false;
		}

		public Integer ValorImposto5Length() {
			return 15;
		}

		public Integer ValorImposto5Precision() {
			return 0;
		}

		public String ValorImposto5Default() {

			return "";

		}

		public String ValorImposto5Comment() {

			return "";

		}

		public String ValorImposto5Pattern() {

			return "";

		}

		public String ValorImposto5OriginalDbColumnName() {

			return "ValorImposto5";

		}

		public Double ValorImposto6;

		public Double getValorImposto6() {
			return this.ValorImposto6;
		}

		public Boolean ValorImposto6IsNullable() {
			return true;
		}

		public Boolean ValorImposto6IsKey() {
			return false;
		}

		public Integer ValorImposto6Length() {
			return 15;
		}

		public Integer ValorImposto6Precision() {
			return 0;
		}

		public String ValorImposto6Default() {

			return "";

		}

		public String ValorImposto6Comment() {

			return "";

		}

		public String ValorImposto6Pattern() {

			return "";

		}

		public String ValorImposto6OriginalDbColumnName() {

			return "ValorImposto6";

		}

		public Double AliquotaImposto5;

		public Double getAliquotaImposto5() {
			return this.AliquotaImposto5;
		}

		public Boolean AliquotaImposto5IsNullable() {
			return true;
		}

		public Boolean AliquotaImposto5IsKey() {
			return false;
		}

		public Integer AliquotaImposto5Length() {
			return 15;
		}

		public Integer AliquotaImposto5Precision() {
			return 0;
		}

		public String AliquotaImposto5Default() {

			return "";

		}

		public String AliquotaImposto5Comment() {

			return "";

		}

		public String AliquotaImposto5Pattern() {

			return "";

		}

		public String AliquotaImposto5OriginalDbColumnName() {

			return "AliquotaImposto5";

		}

		public Double AliquotaImposto6;

		public Double getAliquotaImposto6() {
			return this.AliquotaImposto6;
		}

		public Boolean AliquotaImposto6IsNullable() {
			return true;
		}

		public Boolean AliquotaImposto6IsKey() {
			return false;
		}

		public Integer AliquotaImposto6Length() {
			return 15;
		}

		public Integer AliquotaImposto6Precision() {
			return 0;
		}

		public String AliquotaImposto6Default() {

			return "";

		}

		public String AliquotaImposto6Comment() {

			return "";

		}

		public String AliquotaImposto6Pattern() {

			return "";

		}

		public String AliquotaImposto6OriginalDbColumnName() {

			return "AliquotaImposto6";

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
			return 9;
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

		public String Endereco;

		public String getEndereco() {
			return this.Endereco;
		}

		public Boolean EnderecoIsNullable() {
			return true;
		}

		public Boolean EnderecoIsKey() {
			return false;
		}

		public Integer EnderecoLength() {
			return 15;
		}

		public Integer EnderecoPrecision() {
			return 0;
		}

		public String EnderecoDefault() {

			return null;

		}

		public String EnderecoComment() {

			return "";

		}

		public String EnderecoPattern() {

			return "";

		}

		public String EnderecoOriginalDbColumnName() {

			return "Endereco";

		}

		public Double ValorImportacao;

		public Double getValorImportacao() {
			return this.ValorImportacao;
		}

		public Boolean ValorImportacaoIsNullable() {
			return true;
		}

		public Boolean ValorImportacaoIsKey() {
			return false;
		}

		public Integer ValorImportacaoLength() {
			return 15;
		}

		public Integer ValorImportacaoPrecision() {
			return 0;
		}

		public String ValorImportacaoDefault() {

			return "";

		}

		public String ValorImportacaoComment() {

			return "";

		}

		public String ValorImportacaoPattern() {

			return "";

		}

		public String ValorImportacaoOriginalDbColumnName() {

			return "ValorImportacao";

		}

		public java.util.Date DataFabricacao;

		public java.util.Date getDataFabricacao() {
			return this.DataFabricacao;
		}

		public Boolean DataFabricacaoIsNullable() {
			return true;
		}

		public Boolean DataFabricacaoIsKey() {
			return false;
		}

		public Integer DataFabricacaoLength() {
			return 10;
		}

		public Integer DataFabricacaoPrecision() {
			return 0;
		}

		public String DataFabricacaoDefault() {

			return null;

		}

		public String DataFabricacaoComment() {

			return "";

		}

		public String DataFabricacaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataFabricacaoOriginalDbColumnName() {

			return "DataFabricacao";

		}

		public Double AliquotaINSS;

		public Double getAliquotaINSS() {
			return this.AliquotaINSS;
		}

		public Boolean AliquotaINSSIsNullable() {
			return true;
		}

		public Boolean AliquotaINSSIsKey() {
			return false;
		}

		public Integer AliquotaINSSLength() {
			return 15;
		}

		public Integer AliquotaINSSPrecision() {
			return 0;
		}

		public String AliquotaINSSDefault() {

			return "";

		}

		public String AliquotaINSSComment() {

			return "";

		}

		public String AliquotaINSSPattern() {

			return "";

		}

		public String AliquotaINSSOriginalDbColumnName() {

			return "AliquotaINSS";

		}

		public Double AbatimentoINSS;

		public Double getAbatimentoINSS() {
			return this.AbatimentoINSS;
		}

		public Boolean AbatimentoINSSIsNullable() {
			return true;
		}

		public Boolean AbatimentoINSSIsKey() {
			return false;
		}

		public Integer AbatimentoINSSLength() {
			return 15;
		}

		public Integer AbatimentoINSSPrecision() {
			return 0;
		}

		public String AbatimentoINSSDefault() {

			return "";

		}

		public String AbatimentoINSSComment() {

			return "";

		}

		public String AbatimentoINSSPattern() {

			return "";

		}

		public String AbatimentoINSSOriginalDbColumnName() {

			return "AbatimentoINSS";

		}

		public String PrecoEmbarque;

		public String getPrecoEmbarque() {
			return this.PrecoEmbarque;
		}

		public Boolean PrecoEmbarqueIsNullable() {
			return true;
		}

		public Boolean PrecoEmbarqueIsKey() {
			return false;
		}

		public Integer PrecoEmbarqueLength() {
			return 20;
		}

		public Integer PrecoEmbarquePrecision() {
			return 0;
		}

		public String PrecoEmbarqueDefault() {

			return null;

		}

		public String PrecoEmbarqueComment() {

			return "";

		}

		public String PrecoEmbarquePattern() {

			return "";

		}

		public String PrecoEmbarqueOriginalDbColumnName() {

			return "PrecoEmbarque";

		}

		public Double AliquotaISS;

		public Double getAliquotaISS() {
			return this.AliquotaISS;
		}

		public Boolean AliquotaISSIsNullable() {
			return true;
		}

		public Boolean AliquotaISSIsKey() {
			return false;
		}

		public Integer AliquotaISSLength() {
			return 15;
		}

		public Integer AliquotaISSPrecision() {
			return 0;
		}

		public String AliquotaISSDefault() {

			return "";

		}

		public String AliquotaISSComment() {

			return "";

		}

		public String AliquotaISSPattern() {

			return "";

		}

		public String AliquotaISSOriginalDbColumnName() {

			return "AliquotaISS";

		}

		public Double BaseIPI;

		public Double getBaseIPI() {
			return this.BaseIPI;
		}

		public Boolean BaseIPIIsNullable() {
			return true;
		}

		public Boolean BaseIPIIsKey() {
			return false;
		}

		public Integer BaseIPILength() {
			return 15;
		}

		public Integer BaseIPIPrecision() {
			return 0;
		}

		public String BaseIPIDefault() {

			return "";

		}

		public String BaseIPIComment() {

			return "";

		}

		public String BaseIPIPattern() {

			return "";

		}

		public String BaseIPIOriginalDbColumnName() {

			return "BaseIPI";

		}

		public Double BaseISS;

		public Double getBaseISS() {
			return this.BaseISS;
		}

		public Boolean BaseISSIsNullable() {
			return true;
		}

		public Boolean BaseISSIsKey() {
			return false;
		}

		public Integer BaseISSLength() {
			return 15;
		}

		public Integer BaseISSPrecision() {
			return 0;
		}

		public String BaseISSDefault() {

			return "";

		}

		public String BaseISSComment() {

			return "";

		}

		public String BaseISSPattern() {

			return "";

		}

		public String BaseISSOriginalDbColumnName() {

			return "BaseISS";

		}

		public Double ValorISS;

		public Double getValorISS() {
			return this.ValorISS;
		}

		public Boolean ValorISSIsNullable() {
			return true;
		}

		public Boolean ValorISSIsKey() {
			return false;
		}

		public Integer ValorISSLength() {
			return 15;
		}

		public Integer ValorISSPrecision() {
			return 0;
		}

		public String ValorISSDefault() {

			return "";

		}

		public String ValorISSComment() {

			return "";

		}

		public String ValorISSPattern() {

			return "";

		}

		public String ValorISSOriginalDbColumnName() {

			return "ValorISS";

		}

		public Double Seguro;

		public Double getSeguro() {
			return this.Seguro;
		}

		public Boolean SeguroIsNullable() {
			return true;
		}

		public Boolean SeguroIsKey() {
			return false;
		}

		public Integer SeguroLength() {
			return 15;
		}

		public Integer SeguroPrecision() {
			return 0;
		}

		public String SeguroDefault() {

			return "";

		}

		public String SeguroComment() {

			return "";

		}

		public String SeguroPattern() {

			return "";

		}

		public String SeguroOriginalDbColumnName() {

			return "Seguro";

		}

		public Double ValorFrete;

		public Double getValorFrete() {
			return this.ValorFrete;
		}

		public Boolean ValorFreteIsNullable() {
			return true;
		}

		public Boolean ValorFreteIsKey() {
			return false;
		}

		public Integer ValorFreteLength() {
			return 15;
		}

		public Integer ValorFretePrecision() {
			return 0;
		}

		public String ValorFreteDefault() {

			return "";

		}

		public String ValorFreteComment() {

			return "";

		}

		public String ValorFretePattern() {

			return "";

		}

		public String ValorFreteOriginalDbColumnName() {

			return "ValorFrete";

		}

		public String TipoDocumentoEnv;

		public String getTipoDocumentoEnv() {
			return this.TipoDocumentoEnv;
		}

		public Boolean TipoDocumentoEnvIsNullable() {
			return true;
		}

		public Boolean TipoDocumentoEnvIsKey() {
			return false;
		}

		public Integer TipoDocumentoEnvLength() {
			return 1;
		}

		public Integer TipoDocumentoEnvPrecision() {
			return 0;
		}

		public String TipoDocumentoEnvDefault() {

			return null;

		}

		public String TipoDocumentoEnvComment() {

			return "";

		}

		public String TipoDocumentoEnvPattern() {

			return "";

		}

		public String TipoDocumentoEnvOriginalDbColumnName() {

			return "TipoDocumentoEnv";

		}

		public Double Despesa;

		public Double getDespesa() {
			return this.Despesa;
		}

		public Boolean DespesaIsNullable() {
			return true;
		}

		public Boolean DespesaIsKey() {
			return false;
		}

		public Integer DespesaLength() {
			return 15;
		}

		public Integer DespesaPrecision() {
			return 0;
		}

		public String DespesaDefault() {

			return "";

		}

		public String DespesaComment() {

			return "";

		}

		public String DespesaPattern() {

			return "";

		}

		public String DespesaOriginalDbColumnName() {

			return "Despesa";

		}

		public String OK;

		public String getOK() {
			return this.OK;
		}

		public Boolean OKIsNullable() {
			return true;
		}

		public Boolean OKIsKey() {
			return false;
		}

		public Integer OKLength() {
			return 2;
		}

		public Integer OKPrecision() {
			return 0;
		}

		public String OKDefault() {

			return null;

		}

		public String OKComment() {

			return "";

		}

		public String OKPattern() {

			return "";

		}

		public String OKOriginalDbColumnName() {

			return "OK";

		}

		public String CLVL;

		public String getCLVL() {
			return this.CLVL;
		}

		public Boolean CLVLIsNullable() {
			return true;
		}

		public Boolean CLVLIsKey() {
			return false;
		}

		public Integer CLVLLength() {
			return 9;
		}

		public Integer CLVLPrecision() {
			return 0;
		}

		public String CLVLDefault() {

			return null;

		}

		public String CLVLComment() {

			return "";

		}

		public String CLVLPattern() {

			return "";

		}

		public String CLVLOriginalDbColumnName() {

			return "CLVL";

		}

		public Double BaseINSS;

		public Double getBaseINSS() {
			return this.BaseINSS;
		}

		public Boolean BaseINSSIsNullable() {
			return true;
		}

		public Boolean BaseINSSIsKey() {
			return false;
		}

		public Integer BaseINSSLength() {
			return 15;
		}

		public Integer BaseINSSPrecision() {
			return 0;
		}

		public String BaseINSSDefault() {

			return "";

		}

		public String BaseINSSComment() {

			return "";

		}

		public String BaseINSSPattern() {

			return "";

		}

		public String BaseINSSOriginalDbColumnName() {

			return "BaseINSS";

		}

		public Double ICMFrete;

		public Double getICMFrete() {
			return this.ICMFrete;
		}

		public Boolean ICMFreteIsNullable() {
			return true;
		}

		public Boolean ICMFreteIsKey() {
			return false;
		}

		public Integer ICMFreteLength() {
			return 15;
		}

		public Integer ICMFretePrecision() {
			return 0;
		}

		public String ICMFreteDefault() {

			return "";

		}

		public String ICMFreteComment() {

			return "";

		}

		public String ICMFretePattern() {

			return "";

		}

		public String ICMFreteOriginalDbColumnName() {

			return "ICMFrete";

		}

		public String Servico;

		public String getServico() {
			return this.Servico;
		}

		public Boolean ServicoIsNullable() {
			return true;
		}

		public Boolean ServicoIsKey() {
			return false;
		}

		public Integer ServicoLength() {
			return 3;
		}

		public Integer ServicoPrecision() {
			return 0;
		}

		public String ServicoDefault() {

			return null;

		}

		public String ServicoComment() {

			return "";

		}

		public String ServicoPattern() {

			return "";

		}

		public String ServicoOriginalDbColumnName() {

			return "Servico";

		}

		public String STServ;

		public String getSTServ() {
			return this.STServ;
		}

		public Boolean STServIsNullable() {
			return true;
		}

		public Boolean STServIsKey() {
			return false;
		}

		public Integer STServLength() {
			return 1;
		}

		public Integer STServPrecision() {
			return 0;
		}

		public String STServDefault() {

			return null;

		}

		public String STServComment() {

			return "";

		}

		public String STServPattern() {

			return "";

		}

		public String STServOriginalDbColumnName() {

			return "STServ";

		}

		public Double ValorINSS;

		public Double getValorINSS() {
			return this.ValorINSS;
		}

		public Boolean ValorINSSIsNullable() {
			return true;
		}

		public Boolean ValorINSSIsKey() {
			return false;
		}

		public Integer ValorINSSLength() {
			return 15;
		}

		public Integer ValorINSSPrecision() {
			return 0;
		}

		public String ValorINSSDefault() {

			return "";

		}

		public String ValorINSSComment() {

			return "";

		}

		public String ValorINSSPattern() {

			return "";

		}

		public String ValorINSSOriginalDbColumnName() {

			return "ValorINSS";

		}

		public String TipoDoc;

		public String getTipoDoc() {
			return this.TipoDoc;
		}

		public Boolean TipoDocIsNullable() {
			return true;
		}

		public Boolean TipoDocIsKey() {
			return false;
		}

		public Integer TipoDocLength() {
			return 2;
		}

		public Integer TipoDocPrecision() {
			return 0;
		}

		public String TipoDocDefault() {

			return null;

		}

		public String TipoDocComment() {

			return "";

		}

		public String TipoDocPattern() {

			return "";

		}

		public String TipoDocOriginalDbColumnName() {

			return "TipoDoc";

		}

		public String TipoRem;

		public String getTipoRem() {
			return this.TipoRem;
		}

		public Boolean TipoRemIsNullable() {
			return true;
		}

		public Boolean TipoRemIsKey() {
			return false;
		}

		public Integer TipoRemLength() {
			return 1;
		}

		public Integer TipoRemPrecision() {
			return 0;
		}

		public String TipoRemDefault() {

			return null;

		}

		public String TipoRemComment() {

			return "";

		}

		public String TipoRemPattern() {

			return "";

		}

		public String TipoRemOriginalDbColumnName() {

			return "TipoRem";

		}

		public Double ValorBruto;

		public Double getValorBruto() {
			return this.ValorBruto;
		}

		public Boolean ValorBrutoIsNullable() {
			return true;
		}

		public Boolean ValorBrutoIsKey() {
			return false;
		}

		public Integer ValorBrutoLength() {
			return 15;
		}

		public Integer ValorBrutoPrecision() {
			return 0;
		}

		public String ValorBrutoDefault() {

			return "";

		}

		public String ValorBrutoComment() {

			return "";

		}

		public String ValorBrutoPattern() {

			return "";

		}

		public String ValorBrutoOriginalDbColumnName() {

			return "ValorBruto";

		}

		public java.util.Date DataDigit;

		public java.util.Date getDataDigit() {
			return this.DataDigit;
		}

		public Boolean DataDigitIsNullable() {
			return true;
		}

		public Boolean DataDigitIsKey() {
			return false;
		}

		public Integer DataDigitLength() {
			return 10;
		}

		public Integer DataDigitPrecision() {
			return 0;
		}

		public String DataDigitDefault() {

			return null;

		}

		public String DataDigitComment() {

			return "";

		}

		public String DataDigitPattern() {

			return "dd-MM-yyyy";

		}

		public String DataDigitOriginalDbColumnName() {

			return "DataDigit";

		}

		public String SitTrib;

		public String getSitTrib() {
			return this.SitTrib;
		}

		public Boolean SitTribIsNullable() {
			return true;
		}

		public Boolean SitTribIsKey() {
			return false;
		}

		public Integer SitTribLength() {
			return 5;
		}

		public Integer SitTribPrecision() {
			return 0;
		}

		public String SitTribDefault() {

			return null;

		}

		public String SitTribComment() {

			return "";

		}

		public String SitTribPattern() {

			return "";

		}

		public String SitTribOriginalDbColumnName() {

			return "SitTrib";

		}

		public String GrpCST;

		public String getGrpCST() {
			return this.GrpCST;
		}

		public Boolean GrpCSTIsNullable() {
			return true;
		}

		public Boolean GrpCSTIsKey() {
			return false;
		}

		public Integer GrpCSTLength() {
			return 3;
		}

		public Integer GrpCSTPrecision() {
			return 0;
		}

		public String GrpCSTDefault() {

			return null;

		}

		public String GrpCSTComment() {

			return "";

		}

		public String GrpCSTPattern() {

			return "";

		}

		public String GrpCSTOriginalDbColumnName() {

			return "GrpCST";

		}

		public String FCICOD;

		public String getFCICOD() {
			return this.FCICOD;
		}

		public Boolean FCICODIsNullable() {
			return true;
		}

		public Boolean FCICODIsKey() {
			return false;
		}

		public Integer FCICODLength() {
			return 36;
		}

		public Integer FCICODPrecision() {
			return 0;
		}

		public String FCICODDefault() {

			return null;

		}

		public String FCICODComment() {

			return "";

		}

		public String FCICODPattern() {

			return "";

		}

		public String FCICODOriginalDbColumnName() {

			return "FCICOD";

		}

		public Double AliquotaSOL;

		public Double getAliquotaSOL() {
			return this.AliquotaSOL;
		}

		public Boolean AliquotaSOLIsNullable() {
			return true;
		}

		public Boolean AliquotaSOLIsKey() {
			return false;
		}

		public Integer AliquotaSOLLength() {
			return 15;
		}

		public Integer AliquotaSOLPrecision() {
			return 0;
		}

		public String AliquotaSOLDefault() {

			return "";

		}

		public String AliquotaSOLComment() {

			return "";

		}

		public String AliquotaSOLPattern() {

			return "";

		}

		public String AliquotaSOLOriginalDbColumnName() {

			return "AliquotaSOL";

		}

		public String TNatRec;

		public String getTNatRec() {
			return this.TNatRec;
		}

		public Boolean TNatRecIsNullable() {
			return true;
		}

		public Boolean TNatRecIsKey() {
			return false;
		}

		public Integer TNatRecLength() {
			return 4;
		}

		public Integer TNatRecPrecision() {
			return 0;
		}

		public String TNatRecDefault() {

			return null;

		}

		public String TNatRecComment() {

			return "";

		}

		public String TNatRecPattern() {

			return "";

		}

		public String TNatRecOriginalDbColumnName() {

			return "TNatRec";

		}

		public String CNatRec;

		public String getCNatRec() {
			return this.CNatRec;
		}

		public Boolean CNatRecIsNullable() {
			return true;
		}

		public Boolean CNatRecIsKey() {
			return false;
		}

		public Integer CNatRecLength() {
			return 3;
		}

		public Integer CNatRecPrecision() {
			return 0;
		}

		public String CNatRecDefault() {

			return null;

		}

		public String CNatRecComment() {

			return "";

		}

		public String CNatRecPattern() {

			return "";

		}

		public String CNatRecOriginalDbColumnName() {

			return "CNatRec";

		}

		public String Estoque;

		public String getEstoque() {
			return this.Estoque;
		}

		public Boolean EstoqueIsNullable() {
			return true;
		}

		public Boolean EstoqueIsKey() {
			return false;
		}

		public Integer EstoqueLength() {
			return 1;
		}

		public Integer EstoquePrecision() {
			return 0;
		}

		public String EstoqueDefault() {

			return null;

		}

		public String EstoqueComment() {

			return "";

		}

		public String EstoquePattern() {

			return "";

		}

		public String EstoqueOriginalDbColumnName() {

			return "Estoque";

		}

		public Double AliqCSL;

		public Double getAliqCSL() {
			return this.AliqCSL;
		}

		public Boolean AliqCSLIsNullable() {
			return true;
		}

		public Boolean AliqCSLIsKey() {
			return false;
		}

		public Integer AliqCSLLength() {
			return 15;
		}

		public Integer AliqCSLPrecision() {
			return 0;
		}

		public String AliqCSLDefault() {

			return "";

		}

		public String AliqCSLComment() {

			return "";

		}

		public String AliqCSLPattern() {

			return "";

		}

		public String AliqCSLOriginalDbColumnName() {

			return "AliqCSL";

		}

		public Double AliqPIS;

		public Double getAliqPIS() {
			return this.AliqPIS;
		}

		public Boolean AliqPISIsNullable() {
			return true;
		}

		public Boolean AliqPISIsKey() {
			return false;
		}

		public Integer AliqPISLength() {
			return 15;
		}

		public Integer AliqPISPrecision() {
			return 0;
		}

		public String AliqPISDefault() {

			return "";

		}

		public String AliqPISComment() {

			return "";

		}

		public String AliqPISPattern() {

			return "";

		}

		public String AliqPISOriginalDbColumnName() {

			return "AliqPIS";

		}

		public Double AliqCOF;

		public Double getAliqCOF() {
			return this.AliqCOF;
		}

		public Boolean AliqCOFIsNullable() {
			return true;
		}

		public Boolean AliqCOFIsKey() {
			return false;
		}

		public Integer AliqCOFLength() {
			return 15;
		}

		public Integer AliqCOFPrecision() {
			return 0;
		}

		public String AliqCOFDefault() {

			return "";

		}

		public String AliqCOFComment() {

			return "";

		}

		public String AliqCOFPattern() {

			return "";

		}

		public String AliqCOFOriginalDbColumnName() {

			return "AliqCOF";

		}

		public Double BaseDes;

		public Double getBaseDes() {
			return this.BaseDes;
		}

		public Boolean BaseDesIsNullable() {
			return true;
		}

		public Boolean BaseDesIsKey() {
			return false;
		}

		public Integer BaseDesLength() {
			return 15;
		}

		public Integer BaseDesPrecision() {
			return 0;
		}

		public String BaseDesDefault() {

			return "";

		}

		public String BaseDesComment() {

			return "";

		}

		public String BaseDesPattern() {

			return "";

		}

		public String BaseDesOriginalDbColumnName() {

			return "BaseDes";

		}

		public Double AliqCMP;

		public Double getAliqCMP() {
			return this.AliqCMP;
		}

		public Boolean AliqCMPIsNullable() {
			return true;
		}

		public Boolean AliqCMPIsKey() {
			return false;
		}

		public Integer AliqCMPLength() {
			return 15;
		}

		public Integer AliqCMPPrecision() {
			return 0;
		}

		public String AliqCMPDefault() {

			return "";

		}

		public String AliqCMPComment() {

			return "";

		}

		public String AliqCMPPattern() {

			return "";

		}

		public String AliqCMPOriginalDbColumnName() {

			return "AliqCMP";

		}

		public Double Difal;

		public Double getDifal() {
			return this.Difal;
		}

		public Boolean DifalIsNullable() {
			return true;
		}

		public Boolean DifalIsKey() {
			return false;
		}

		public Integer DifalLength() {
			return 15;
		}

		public Integer DifalPrecision() {
			return 0;
		}

		public String DifalDefault() {

			return "";

		}

		public String DifalComment() {

			return "";

		}

		public String DifalPattern() {

			return "";

		}

		public String DifalOriginalDbColumnName() {

			return "Difal";

		}

		public Double ICMSCom;

		public Double getICMSCom() {
			return this.ICMSCom;
		}

		public Boolean ICMSComIsNullable() {
			return true;
		}

		public Boolean ICMSComIsKey() {
			return false;
		}

		public Integer ICMSComLength() {
			return 15;
		}

		public Integer ICMSComPrecision() {
			return 0;
		}

		public String ICMSComDefault() {

			return "";

		}

		public String ICMSComComment() {

			return "";

		}

		public String ICMSComPattern() {

			return "";

		}

		public String ICMSComOriginalDbColumnName() {

			return "ICMSCom";

		}

		public Double PDDes;

		public Double getPDDes() {
			return this.PDDes;
		}

		public Boolean PDDesIsNullable() {
			return true;
		}

		public Boolean PDDesIsKey() {
			return false;
		}

		public Integer PDDesLength() {
			return 15;
		}

		public Integer PDDesPrecision() {
			return 0;
		}

		public String PDDesDefault() {

			return "";

		}

		public String PDDesComment() {

			return "";

		}

		public String PDDesPattern() {

			return "";

		}

		public String PDDesOriginalDbColumnName() {

			return "PDDes";

		}

		public Double PDOrig;

		public Double getPDOrig() {
			return this.PDOrig;
		}

		public Boolean PDOrigIsNullable() {
			return true;
		}

		public Boolean PDOrigIsKey() {
			return false;
		}

		public Integer PDOrigLength() {
			return 15;
		}

		public Integer PDOrigPrecision() {
			return 0;
		}

		public String PDOrigDefault() {

			return "";

		}

		public String PDOrigComment() {

			return "";

		}

		public String PDOrigPattern() {

			return "";

		}

		public String PDOrigOriginalDbColumnName() {

			return "PDOrig";

		}

		public Double VFCPDIF;

		public Double getVFCPDIF() {
			return this.VFCPDIF;
		}

		public Boolean VFCPDIFIsNullable() {
			return true;
		}

		public Boolean VFCPDIFIsKey() {
			return false;
		}

		public Integer VFCPDIFLength() {
			return 15;
		}

		public Integer VFCPDIFPrecision() {
			return 0;
		}

		public String VFCPDIFDefault() {

			return "";

		}

		public String VFCPDIFComment() {

			return "";

		}

		public String VFCPDIFPattern() {

			return "";

		}

		public String VFCPDIFOriginalDbColumnName() {

			return "VFCPDIF";

		}

		public Double AlFCCMP;

		public Double getAlFCCMP() {
			return this.AlFCCMP;
		}

		public Boolean AlFCCMPIsNullable() {
			return true;
		}

		public Boolean AlFCCMPIsKey() {
			return false;
		}

		public Integer AlFCCMPLength() {
			return 15;
		}

		public Integer AlFCCMPPrecision() {
			return 0;
		}

		public String AlFCCMPDefault() {

			return "";

		}

		public String AlFCCMPComment() {

			return "";

		}

		public String AlFCCMPPattern() {

			return "";

		}

		public String AlFCCMPOriginalDbColumnName() {

			return "AlFCCMP";

		}

		public Double BaseCPB;

		public Double getBaseCPB() {
			return this.BaseCPB;
		}

		public Boolean BaseCPBIsNullable() {
			return true;
		}

		public Boolean BaseCPBIsKey() {
			return false;
		}

		public Integer BaseCPBLength() {
			return 15;
		}

		public Integer BaseCPBPrecision() {
			return 0;
		}

		public String BaseCPBDefault() {

			return "";

		}

		public String BaseCPBComment() {

			return "";

		}

		public String BaseCPBPattern() {

			return "";

		}

		public String BaseCPBOriginalDbColumnName() {

			return "BaseCPB";

		}

		public Double AliqPro;

		public Double getAliqPro() {
			return this.AliqPro;
		}

		public Boolean AliqProIsNullable() {
			return true;
		}

		public Boolean AliqProIsKey() {
			return false;
		}

		public Integer AliqProLength() {
			return 15;
		}

		public Integer AliqProPrecision() {
			return 0;
		}

		public String AliqProDefault() {

			return "";

		}

		public String AliqProComment() {

			return "";

		}

		public String AliqProPattern() {

			return "";

		}

		public String AliqProOriginalDbColumnName() {

			return "AliqPro";

		}

		public Double Margem;

		public Double getMargem() {
			return this.Margem;
		}

		public Boolean MargemIsNullable() {
			return true;
		}

		public Boolean MargemIsKey() {
			return false;
		}

		public Integer MargemLength() {
			return 15;
		}

		public Integer MargemPrecision() {
			return 0;
		}

		public String MargemDefault() {

			return "";

		}

		public String MargemComment() {

			return "";

		}

		public String MargemPattern() {

			return "";

		}

		public String MargemOriginalDbColumnName() {

			return "Margem";

		}

		public Double UBCSALD;

		public Double getUBCSALD() {
			return this.UBCSALD;
		}

		public Boolean UBCSALDIsNullable() {
			return true;
		}

		public Boolean UBCSALDIsKey() {
			return false;
		}

		public Integer UBCSALDLength() {
			return 15;
		}

		public Integer UBCSALDPrecision() {
			return 0;
		}

		public String UBCSALDDefault() {

			return "";

		}

		public String UBCSALDComment() {

			return "";

		}

		public String UBCSALDPattern() {

			return "";

		}

		public String UBCSALDOriginalDbColumnName() {

			return "UBCSALD";

		}

		public String UBCCHBX;

		public String getUBCCHBX() {
			return this.UBCCHBX;
		}

		public Boolean UBCCHBXIsNullable() {
			return true;
		}

		public Boolean UBCCHBXIsKey() {
			return false;
		}

		public Integer UBCCHBXLength() {
			return 40;
		}

		public Integer UBCCHBXPrecision() {
			return 0;
		}

		public String UBCCHBXDefault() {

			return null;

		}

		public String UBCCHBXComment() {

			return "";

		}

		public String UBCCHBXPattern() {

			return "";

		}

		public String UBCCHBXOriginalDbColumnName() {

			return "UBCCHBX";

		}

		public Double ALFCPST;

		public Double getALFCPST() {
			return this.ALFCPST;
		}

		public Boolean ALFCPSTIsNullable() {
			return true;
		}

		public Boolean ALFCPSTIsKey() {
			return false;
		}

		public Integer ALFCPSTLength() {
			return 15;
		}

		public Integer ALFCPSTPrecision() {
			return 0;
		}

		public String ALFCPSTDefault() {

			return "";

		}

		public String ALFCPSTComment() {

			return "";

		}

		public String ALFCPSTPattern() {

			return "";

		}

		public String ALFCPSTOriginalDbColumnName() {

			return "ALFCPST";

		}

		public Double ALQFECP;

		public Double getALQFECP() {
			return this.ALQFECP;
		}

		public Boolean ALQFECPIsNullable() {
			return true;
		}

		public Boolean ALQFECPIsKey() {
			return false;
		}

		public Integer ALQFECPLength() {
			return 15;
		}

		public Integer ALQFECPPrecision() {
			return 0;
		}

		public String ALQFECPDefault() {

			return "";

		}

		public String ALQFECPComment() {

			return "";

		}

		public String ALQFECPPattern() {

			return "";

		}

		public String ALQFECPOriginalDbColumnName() {

			return "ALQFECP";

		}

		public Double ICMSDIF;

		public Double getICMSDIF() {
			return this.ICMSDIF;
		}

		public Boolean ICMSDIFIsNullable() {
			return true;
		}

		public Boolean ICMSDIFIsKey() {
			return false;
		}

		public Integer ICMSDIFLength() {
			return 15;
		}

		public Integer ICMSDIFPrecision() {
			return 0;
		}

		public String ICMSDIFDefault() {

			return "";

		}

		public String ICMSDIFComment() {

			return "";

		}

		public String ICMSDIFPattern() {

			return "";

		}

		public String ICMSDIFOriginalDbColumnName() {

			return "ICMSDIF";

		}

		public Double VALFECP;

		public Double getVALFECP() {
			return this.VALFECP;
		}

		public Boolean VALFECPIsNullable() {
			return true;
		}

		public Boolean VALFECPIsKey() {
			return false;
		}

		public Integer VALFECPLength() {
			return 15;
		}

		public Integer VALFECPPrecision() {
			return 0;
		}

		public String VALFECPDefault() {

			return "";

		}

		public String VALFECPComment() {

			return "";

		}

		public String VALFECPPattern() {

			return "";

		}

		public String VALFECPOriginalDbColumnName() {

			return "VALFECP";

		}

		public Double VFECPST;

		public Double getVFECPST() {
			return this.VFECPST;
		}

		public Boolean VFECPSTIsNullable() {
			return true;
		}

		public Boolean VFECPSTIsKey() {
			return false;
		}

		public Integer VFECPSTLength() {
			return 15;
		}

		public Integer VFECPSTPrecision() {
			return 0;
		}

		public String VFECPSTDefault() {

			return "";

		}

		public String VFECPSTComment() {

			return "";

		}

		public String VFECPSTPattern() {

			return "";

		}

		public String VFECPSTOriginalDbColumnName() {

			return "VFECPST";

		}

		public Double VOPDIF;

		public Double getVOPDIF() {
			return this.VOPDIF;
		}

		public Boolean VOPDIFIsNullable() {
			return true;
		}

		public Boolean VOPDIFIsKey() {
			return false;
		}

		public Integer VOPDIFLength() {
			return 15;
		}

		public Integer VOPDIFPrecision() {
			return 0;
		}

		public String VOPDIFDefault() {

			return "";

		}

		public String VOPDIFComment() {

			return "";

		}

		public String VOPDIFPattern() {

			return "";

		}

		public String VOPDIFOriginalDbColumnName() {

			return "VOPDIF";

		}

		public String Deletado;

		public String getDeletado() {
			return this.Deletado;
		}

		public Boolean DeletadoIsNullable() {
			return true;
		}

		public Boolean DeletadoIsKey() {
			return false;
		}

		public Integer DeletadoLength() {
			return 1;
		}

		public Integer DeletadoPrecision() {
			return 0;
		}

		public String DeletadoDefault() {

			return null;

		}

		public String DeletadoComment() {

			return "";

		}

		public String DeletadoPattern() {

			return "";

		}

		public String DeletadoOriginalDbColumnName() {

			return "Deletado";

		}

		public Integer Ukey;

		public Integer getUkey() {
			return this.Ukey;
		}

		public Boolean UkeyIsNullable() {
			return true;
		}

		public Boolean UkeyIsKey() {
			return false;
		}

		public Integer UkeyLength() {
			return 10;
		}

		public Integer UkeyPrecision() {
			return 0;
		}

		public String UkeyDefault() {

			return "";

		}

		public String UkeyComment() {

			return "";

		}

		public String UkeyPattern() {

			return "";

		}

		public String UkeyOriginalDbColumnName() {

			return "Ukey";

		}

		public Integer Registro;

		public Integer getRegistro() {
			return this.Registro;
		}

		public Boolean RegistroIsNullable() {
			return true;
		}

		public Boolean RegistroIsKey() {
			return false;
		}

		public Integer RegistroLength() {
			return 10;
		}

		public Integer RegistroPrecision() {
			return 0;
		}

		public String RegistroDefault() {

			return "";

		}

		public String RegistroComment() {

			return "";

		}

		public String RegistroPattern() {

			return "";

		}

		public String RegistroOriginalDbColumnName() {

			return "Registro";

		}

		public java.util.Date DataUltimaAlteracao;

		public java.util.Date getDataUltimaAlteracao() {
			return this.DataUltimaAlteracao;
		}

		public Boolean DataUltimaAlteracaoIsNullable() {
			return true;
		}

		public Boolean DataUltimaAlteracaoIsKey() {
			return false;
		}

		public Integer DataUltimaAlteracaoLength() {
			return 23;
		}

		public Integer DataUltimaAlteracaoPrecision() {
			return 3;
		}

		public String DataUltimaAlteracaoDefault() {

			return null;

		}

		public String DataUltimaAlteracaoComment() {

			return "";

		}

		public String DataUltimaAlteracaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataUltimaAlteracaoOriginalDbColumnName() {

			return "DataUltimaAlteracao";

		}

		public java.util.Date DataInsercao;

		public java.util.Date getDataInsercao() {
			return this.DataInsercao;
		}

		public Boolean DataInsercaoIsNullable() {
			return true;
		}

		public Boolean DataInsercaoIsKey() {
			return false;
		}

		public Integer DataInsercaoLength() {
			return 23;
		}

		public Integer DataInsercaoPrecision() {
			return 3;
		}

		public String DataInsercaoDefault() {

			return null;

		}

		public String DataInsercaoComment() {

			return "";

		}

		public String DataInsercaoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataInsercaoOriginalDbColumnName() {

			return "DataInsercao";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Filial == null) ? 0 : this.Filial.hashCode());

				result = prime * result + ((this.SequenciaItem == null) ? 0 : this.SequenciaItem.hashCode());

				result = prime * result + ((this.CodigoProduto == null) ? 0 : this.CodigoProduto.hashCode());

				result = prime * result + ((this.NotaFiscal == null) ? 0 : this.NotaFiscal.hashCode());

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

			if (this.Filial == null) {
				if (other.Filial != null)
					return false;

			} else if (!this.Filial.equals(other.Filial))

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

			if (this.NotaFiscal == null) {
				if (other.NotaFiscal != null)
					return false;

			} else if (!this.NotaFiscal.equals(other.NotaFiscal))

				return false;

			return true;
		}

		public void copyDataTo(row1Struct other) {

			other.Filial = this.Filial;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.SegundaUnidade = this.SegundaUnidade;
			other.UnidadeMedida = this.UnidadeMedida;
			other.Quantidade = this.Quantidade;
			other.PrecoVenda = this.PrecoVenda;
			other.PrecoTotal = this.PrecoTotal;
			other.ValorIPI = this.ValorIPI;
			other.ValorICMS = this.ValorICMS;
			other.CodigoTes = this.CodigoTes;
			other.CFOP = this.CFOP;
			other.Desconto = this.Desconto;
			other.IPI = this.IPI;
			other.ValorCSSL = this.ValorCSSL;
			other.AliquotaICM = this.AliquotaICM;
			other.Peso = this.Peso;
			other.NumeroPedido = this.NumeroPedido;
			other.ItemPV = this.ItemPV;
			other.Cliente = this.Cliente;
			other.Loja = this.Loja;
			other.Armazem = this.Armazem;
			other.NotaFiscal = this.NotaFiscal;
			other.Serie = this.Serie;
			other.Grupo = this.Grupo;
			other.TipoProduto = this.TipoProduto;
			other.DataEmissao = this.DataEmissao;
			other.CustoProduto = this.CustoProduto;
			other.Custo2 = this.Custo2;
			other.Custo3 = this.Custo3;
			other.Custo4 = this.Custo4;
			other.Custo5 = this.Custo5;
			other.PrecoUnitario = this.PrecoUnitario;
			other.QuantidadeSegundaUnidade = this.QuantidadeSegundaUnidade;
			other.NumeroSequencial = this.NumeroSequencial;
			other.Estado = this.Estado;
			other.DescontoValor = this.DescontoValor;
			other.Tipo = this.Tipo;
			other.NotaFiscalOrigem = this.NotaFiscalOrigem;
			other.SerieOrigem = this.SerieOrigem;
			other.QuantidadeDevolucao = this.QuantidadeDevolucao;
			other.ValorDevolucao = this.ValorDevolucao;
			other.OrigemLancamento = this.OrigemLancamento;
			other.BaseICMS = this.BaseICMS;
			other.BaseICM = this.BaseICM;
			other.ValorAcrescimo = this.ValorAcrescimo;
			other.ICMSRetido = this.ICMSRetido;
			other.Comissao1 = this.Comissao1;
			other.Comissao2 = this.Comissao2;
			other.Comissao3 = this.Comissao3;
			other.Comissao4 = this.Comissao4;
			other.Comissao5 = this.Comissao5;
			other.NumeroLote = this.NumeroLote;
			other.Lote = this.Lote;
			other.DataValidade = this.DataValidade;
			other.ClasseFiscal = this.ClasseFiscal;
			other.BaseImposto5 = this.BaseImposto5;
			other.BaseImposto6 = this.BaseImposto6;
			other.ValorImposto5 = this.ValorImposto5;
			other.ValorImposto6 = this.ValorImposto6;
			other.AliquotaImposto5 = this.AliquotaImposto5;
			other.AliquotaImposto6 = this.AliquotaImposto6;
			other.CentroCusto = this.CentroCusto;
			other.Endereco = this.Endereco;
			other.ValorImportacao = this.ValorImportacao;
			other.DataFabricacao = this.DataFabricacao;
			other.AliquotaINSS = this.AliquotaINSS;
			other.AbatimentoINSS = this.AbatimentoINSS;
			other.PrecoEmbarque = this.PrecoEmbarque;
			other.AliquotaISS = this.AliquotaISS;
			other.BaseIPI = this.BaseIPI;
			other.BaseISS = this.BaseISS;
			other.ValorISS = this.ValorISS;
			other.Seguro = this.Seguro;
			other.ValorFrete = this.ValorFrete;
			other.TipoDocumentoEnv = this.TipoDocumentoEnv;
			other.Despesa = this.Despesa;
			other.OK = this.OK;
			other.CLVL = this.CLVL;
			other.BaseINSS = this.BaseINSS;
			other.ICMFrete = this.ICMFrete;
			other.Servico = this.Servico;
			other.STServ = this.STServ;
			other.ValorINSS = this.ValorINSS;
			other.TipoDoc = this.TipoDoc;
			other.TipoRem = this.TipoRem;
			other.ValorBruto = this.ValorBruto;
			other.DataDigit = this.DataDigit;
			other.SitTrib = this.SitTrib;
			other.GrpCST = this.GrpCST;
			other.FCICOD = this.FCICOD;
			other.AliquotaSOL = this.AliquotaSOL;
			other.TNatRec = this.TNatRec;
			other.CNatRec = this.CNatRec;
			other.Estoque = this.Estoque;
			other.AliqCSL = this.AliqCSL;
			other.AliqPIS = this.AliqPIS;
			other.AliqCOF = this.AliqCOF;
			other.BaseDes = this.BaseDes;
			other.AliqCMP = this.AliqCMP;
			other.Difal = this.Difal;
			other.ICMSCom = this.ICMSCom;
			other.PDDes = this.PDDes;
			other.PDOrig = this.PDOrig;
			other.VFCPDIF = this.VFCPDIF;
			other.AlFCCMP = this.AlFCCMP;
			other.BaseCPB = this.BaseCPB;
			other.AliqPro = this.AliqPro;
			other.Margem = this.Margem;
			other.UBCSALD = this.UBCSALD;
			other.UBCCHBX = this.UBCCHBX;
			other.ALFCPST = this.ALFCPST;
			other.ALQFECP = this.ALQFECP;
			other.ICMSDIF = this.ICMSDIF;
			other.VALFECP = this.VALFECP;
			other.VFECPST = this.VFECPST;
			other.VOPDIF = this.VOPDIF;
			other.Deletado = this.Deletado;
			other.Ukey = this.Ukey;
			other.Registro = this.Registro;
			other.DataUltimaAlteracao = this.DataUltimaAlteracao;
			other.DataInsercao = this.DataInsercao;

		}

		public void copyKeysDataTo(row1Struct other) {

			other.Filial = this.Filial;
			other.SequenciaItem = this.SequenciaItem;
			other.CodigoProduto = this.CodigoProduto;
			other.NotaFiscal = this.NotaFiscal;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_ItensNotaSaida.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_ItensNotaSaida.length == 0) {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_ItensNotaSaida.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_ItensNotaSaida.length == 0) {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_ItensNotaSaida = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_ItensNotaSaida, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_ItensNotaSaida) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.SequenciaItem = readString(dis);

					this.CodigoProduto = readString(dis);

					this.SegundaUnidade = readString(dis);

					this.UnidadeMedida = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantidade = null;
					} else {
						this.Quantidade = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoVenda = null;
					} else {
						this.PrecoVenda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoTotal = null;
					} else {
						this.PrecoTotal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorIPI = null;
					} else {
						this.ValorIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMS = null;
					} else {
						this.ValorICMS = dis.readDouble();
					}

					this.CodigoTes = readString(dis);

					this.CFOP = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Desconto = null;
					} else {
						this.Desconto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IPI = null;
					} else {
						this.IPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorCSSL = null;
					} else {
						this.ValorCSSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaICM = null;
					} else {
						this.AliquotaICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Peso = null;
					} else {
						this.Peso = dis.readDouble();
					}

					this.NumeroPedido = readString(dis);

					this.ItemPV = readString(dis);

					this.Cliente = readString(dis);

					this.Loja = readString(dis);

					this.Armazem = readString(dis);

					this.NotaFiscal = readString(dis);

					this.Serie = readString(dis);

					this.Grupo = readString(dis);

					this.TipoProduto = readString(dis);

					this.DataEmissao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoProduto = null;
					} else {
						this.CustoProduto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo2 = null;
					} else {
						this.Custo2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo3 = null;
					} else {
						this.Custo3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo4 = null;
					} else {
						this.Custo4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo5 = null;
					} else {
						this.Custo5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeSegundaUnidade = null;
					} else {
						this.QuantidadeSegundaUnidade = dis.readDouble();
					}

					this.NumeroSequencial = readString(dis);

					this.Estado = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DescontoValor = null;
					} else {
						this.DescontoValor = dis.readDouble();
					}

					this.Tipo = readString(dis);

					this.NotaFiscalOrigem = readString(dis);

					this.SerieOrigem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeDevolucao = null;
					} else {
						this.QuantidadeDevolucao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDevolucao = null;
					} else {
						this.ValorDevolucao = dis.readDouble();
					}

					this.OrigemLancamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseICMS = null;
					} else {
						this.BaseICMS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseICM = null;
					} else {
						this.BaseICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcrescimo = null;
					} else {
						this.ValorAcrescimo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSRetido = null;
					} else {
						this.ICMSRetido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao1 = null;
					} else {
						this.Comissao1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao2 = null;
					} else {
						this.Comissao2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao3 = null;
					} else {
						this.Comissao3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao4 = null;
					} else {
						this.Comissao4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao5 = null;
					} else {
						this.Comissao5 = dis.readDouble();
					}

					this.NumeroLote = readString(dis);

					this.Lote = readString(dis);

					this.DataValidade = readDate(dis);

					this.ClasseFiscal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto5 = null;
					} else {
						this.BaseImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto6 = null;
					} else {
						this.BaseImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto5 = null;
					} else {
						this.ValorImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto6 = null;
					} else {
						this.ValorImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto5 = null;
					} else {
						this.AliquotaImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto6 = null;
					} else {
						this.AliquotaImposto6 = dis.readDouble();
					}

					this.CentroCusto = readString(dis);

					this.Endereco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorImportacao = null;
					} else {
						this.ValorImportacao = dis.readDouble();
					}

					this.DataFabricacao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaINSS = null;
					} else {
						this.AliquotaINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AbatimentoINSS = null;
					} else {
						this.AbatimentoINSS = dis.readDouble();
					}

					this.PrecoEmbarque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaISS = null;
					} else {
						this.AliquotaISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseIPI = null;
					} else {
						this.BaseIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseISS = null;
					} else {
						this.BaseISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorISS = null;
					} else {
						this.ValorISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Seguro = null;
					} else {
						this.Seguro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorFrete = null;
					} else {
						this.ValorFrete = dis.readDouble();
					}

					this.TipoDocumentoEnv = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Despesa = null;
					} else {
						this.Despesa = dis.readDouble();
					}

					this.OK = readString(dis);

					this.CLVL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseINSS = null;
					} else {
						this.BaseINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMFrete = null;
					} else {
						this.ICMFrete = dis.readDouble();
					}

					this.Servico = readString(dis);

					this.STServ = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorINSS = null;
					} else {
						this.ValorINSS = dis.readDouble();
					}

					this.TipoDoc = readString(dis);

					this.TipoRem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorBruto = null;
					} else {
						this.ValorBruto = dis.readDouble();
					}

					this.DataDigit = readDate(dis);

					this.SitTrib = readString(dis);

					this.GrpCST = readString(dis);

					this.FCICOD = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaSOL = null;
					} else {
						this.AliquotaSOL = dis.readDouble();
					}

					this.TNatRec = readString(dis);

					this.CNatRec = readString(dis);

					this.Estoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliqCSL = null;
					} else {
						this.AliqCSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPIS = null;
					} else {
						this.AliqPIS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCOF = null;
					} else {
						this.AliqCOF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseDes = null;
					} else {
						this.BaseDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCMP = null;
					} else {
						this.AliqCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Difal = null;
					} else {
						this.Difal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSCom = null;
					} else {
						this.ICMSCom = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDDes = null;
					} else {
						this.PDDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDOrig = null;
					} else {
						this.PDOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFCPDIF = null;
					} else {
						this.VFCPDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AlFCCMP = null;
					} else {
						this.AlFCCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseCPB = null;
					} else {
						this.BaseCPB = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPro = null;
					} else {
						this.AliqPro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Margem = null;
					} else {
						this.Margem = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.UBCSALD = null;
					} else {
						this.UBCSALD = dis.readDouble();
					}

					this.UBCCHBX = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ALFCPST = null;
					} else {
						this.ALFCPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ALQFECP = null;
					} else {
						this.ALQFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDIF = null;
					} else {
						this.ICMSDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VALFECP = null;
					} else {
						this.VALFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFECPST = null;
					} else {
						this.VFECPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VOPDIF = null;
					} else {
						this.VOPDIF = dis.readDouble();
					}

					this.Deletado = readString(dis);

					this.Ukey = readInteger(dis);

					this.Registro = readInteger(dis);

					this.DataUltimaAlteracao = readDate(dis);

					this.DataInsercao = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_ItensNotaSaida) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.SequenciaItem = readString(dis);

					this.CodigoProduto = readString(dis);

					this.SegundaUnidade = readString(dis);

					this.UnidadeMedida = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Quantidade = null;
					} else {
						this.Quantidade = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoVenda = null;
					} else {
						this.PrecoVenda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoTotal = null;
					} else {
						this.PrecoTotal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorIPI = null;
					} else {
						this.ValorIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorICMS = null;
					} else {
						this.ValorICMS = dis.readDouble();
					}

					this.CodigoTes = readString(dis);

					this.CFOP = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Desconto = null;
					} else {
						this.Desconto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.IPI = null;
					} else {
						this.IPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorCSSL = null;
					} else {
						this.ValorCSSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaICM = null;
					} else {
						this.AliquotaICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Peso = null;
					} else {
						this.Peso = dis.readDouble();
					}

					this.NumeroPedido = readString(dis);

					this.ItemPV = readString(dis);

					this.Cliente = readString(dis);

					this.Loja = readString(dis);

					this.Armazem = readString(dis);

					this.NotaFiscal = readString(dis);

					this.Serie = readString(dis);

					this.Grupo = readString(dis);

					this.TipoProduto = readString(dis);

					this.DataEmissao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.CustoProduto = null;
					} else {
						this.CustoProduto = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo2 = null;
					} else {
						this.Custo2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo3 = null;
					} else {
						this.Custo3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo4 = null;
					} else {
						this.Custo4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Custo5 = null;
					} else {
						this.Custo5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PrecoUnitario = null;
					} else {
						this.PrecoUnitario = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeSegundaUnidade = null;
					} else {
						this.QuantidadeSegundaUnidade = dis.readDouble();
					}

					this.NumeroSequencial = readString(dis);

					this.Estado = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DescontoValor = null;
					} else {
						this.DescontoValor = dis.readDouble();
					}

					this.Tipo = readString(dis);

					this.NotaFiscalOrigem = readString(dis);

					this.SerieOrigem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.QuantidadeDevolucao = null;
					} else {
						this.QuantidadeDevolucao = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorDevolucao = null;
					} else {
						this.ValorDevolucao = dis.readDouble();
					}

					this.OrigemLancamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseICMS = null;
					} else {
						this.BaseICMS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseICM = null;
					} else {
						this.BaseICM = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcrescimo = null;
					} else {
						this.ValorAcrescimo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSRetido = null;
					} else {
						this.ICMSRetido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao1 = null;
					} else {
						this.Comissao1 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao2 = null;
					} else {
						this.Comissao2 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao3 = null;
					} else {
						this.Comissao3 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao4 = null;
					} else {
						this.Comissao4 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Comissao5 = null;
					} else {
						this.Comissao5 = dis.readDouble();
					}

					this.NumeroLote = readString(dis);

					this.Lote = readString(dis);

					this.DataValidade = readDate(dis);

					this.ClasseFiscal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto5 = null;
					} else {
						this.BaseImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseImposto6 = null;
					} else {
						this.BaseImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto5 = null;
					} else {
						this.ValorImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorImposto6 = null;
					} else {
						this.ValorImposto6 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto5 = null;
					} else {
						this.AliquotaImposto5 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaImposto6 = null;
					} else {
						this.AliquotaImposto6 = dis.readDouble();
					}

					this.CentroCusto = readString(dis);

					this.Endereco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorImportacao = null;
					} else {
						this.ValorImportacao = dis.readDouble();
					}

					this.DataFabricacao = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaINSS = null;
					} else {
						this.AliquotaINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AbatimentoINSS = null;
					} else {
						this.AbatimentoINSS = dis.readDouble();
					}

					this.PrecoEmbarque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaISS = null;
					} else {
						this.AliquotaISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseIPI = null;
					} else {
						this.BaseIPI = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseISS = null;
					} else {
						this.BaseISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorISS = null;
					} else {
						this.ValorISS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Seguro = null;
					} else {
						this.Seguro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorFrete = null;
					} else {
						this.ValorFrete = dis.readDouble();
					}

					this.TipoDocumentoEnv = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Despesa = null;
					} else {
						this.Despesa = dis.readDouble();
					}

					this.OK = readString(dis);

					this.CLVL = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.BaseINSS = null;
					} else {
						this.BaseINSS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMFrete = null;
					} else {
						this.ICMFrete = dis.readDouble();
					}

					this.Servico = readString(dis);

					this.STServ = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorINSS = null;
					} else {
						this.ValorINSS = dis.readDouble();
					}

					this.TipoDoc = readString(dis);

					this.TipoRem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ValorBruto = null;
					} else {
						this.ValorBruto = dis.readDouble();
					}

					this.DataDigit = readDate(dis);

					this.SitTrib = readString(dis);

					this.GrpCST = readString(dis);

					this.FCICOD = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaSOL = null;
					} else {
						this.AliquotaSOL = dis.readDouble();
					}

					this.TNatRec = readString(dis);

					this.CNatRec = readString(dis);

					this.Estoque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliqCSL = null;
					} else {
						this.AliqCSL = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPIS = null;
					} else {
						this.AliqPIS = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCOF = null;
					} else {
						this.AliqCOF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseDes = null;
					} else {
						this.BaseDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqCMP = null;
					} else {
						this.AliqCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Difal = null;
					} else {
						this.Difal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSCom = null;
					} else {
						this.ICMSCom = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDDes = null;
					} else {
						this.PDDes = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PDOrig = null;
					} else {
						this.PDOrig = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFCPDIF = null;
					} else {
						this.VFCPDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AlFCCMP = null;
					} else {
						this.AlFCCMP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.BaseCPB = null;
					} else {
						this.BaseCPB = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.AliqPro = null;
					} else {
						this.AliqPro = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.Margem = null;
					} else {
						this.Margem = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.UBCSALD = null;
					} else {
						this.UBCSALD = dis.readDouble();
					}

					this.UBCCHBX = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ALFCPST = null;
					} else {
						this.ALFCPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ALQFECP = null;
					} else {
						this.ALQFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ICMSDIF = null;
					} else {
						this.ICMSDIF = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VALFECP = null;
					} else {
						this.VALFECP = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VFECPST = null;
					} else {
						this.VFECPST = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.VOPDIF = null;
					} else {
						this.VOPDIF = dis.readDouble();
					}

					this.Deletado = readString(dis);

					this.Ukey = readInteger(dis);

					this.Registro = readInteger(dis);

					this.DataUltimaAlteracao = readDate(dis);

					this.DataInsercao = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// String

				writeString(this.SegundaUnidade, dos);

				// String

				writeString(this.UnidadeMedida, dos);

				// Double

				if (this.Quantidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Quantidade);
				}

				// Double

				if (this.PrecoVenda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoVenda);
				}

				// Double

				if (this.PrecoTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoTotal);
				}

				// Double

				if (this.ValorIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorIPI);
				}

				// Double

				if (this.ValorICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMS);
				}

				// String

				writeString(this.CodigoTes, dos);

				// String

				writeString(this.CFOP, dos);

				// Double

				if (this.Desconto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Desconto);
				}

				// Double

				if (this.IPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IPI);
				}

				// Double

				if (this.ValorCSSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorCSSL);
				}

				// Double

				if (this.AliquotaICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaICM);
				}

				// Double

				if (this.Peso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Peso);
				}

				// String

				writeString(this.NumeroPedido, dos);

				// String

				writeString(this.ItemPV, dos);

				// String

				writeString(this.Cliente, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Armazem, dos);

				// String

				writeString(this.NotaFiscal, dos);

				// String

				writeString(this.Serie, dos);

				// String

				writeString(this.Grupo, dos);

				// String

				writeString(this.TipoProduto, dos);

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// Double

				if (this.CustoProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoProduto);
				}

				// Double

				if (this.Custo2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo2);
				}

				// Double

				if (this.Custo3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo3);
				}

				// Double

				if (this.Custo4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo4);
				}

				// Double

				if (this.Custo5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo5);
				}

				// Double

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoUnitario);
				}

				// Double

				if (this.QuantidadeSegundaUnidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeSegundaUnidade);
				}

				// String

				writeString(this.NumeroSequencial, dos);

				// String

				writeString(this.Estado, dos);

				// Double

				if (this.DescontoValor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DescontoValor);
				}

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.NotaFiscalOrigem, dos);

				// String

				writeString(this.SerieOrigem, dos);

				// Double

				if (this.QuantidadeDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeDevolucao);
				}

				// Double

				if (this.ValorDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDevolucao);
				}

				// String

				writeString(this.OrigemLancamento, dos);

				// Double

				if (this.BaseICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICMS);
				}

				// Double

				if (this.BaseICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICM);
				}

				// Double

				if (this.ValorAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcrescimo);
				}

				// Double

				if (this.ICMSRetido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSRetido);
				}

				// Double

				if (this.Comissao1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao1);
				}

				// Double

				if (this.Comissao2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao2);
				}

				// Double

				if (this.Comissao3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao3);
				}

				// Double

				if (this.Comissao4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao4);
				}

				// Double

				if (this.Comissao5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao5);
				}

				// String

				writeString(this.NumeroLote, dos);

				// String

				writeString(this.Lote, dos);

				// java.util.Date

				writeDate(this.DataValidade, dos);

				// String

				writeString(this.ClasseFiscal, dos);

				// Double

				if (this.BaseImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto5);
				}

				// Double

				if (this.BaseImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto6);
				}

				// Double

				if (this.ValorImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto5);
				}

				// Double

				if (this.ValorImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto6);
				}

				// Double

				if (this.AliquotaImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto5);
				}

				// Double

				if (this.AliquotaImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto6);
				}

				// String

				writeString(this.CentroCusto, dos);

				// String

				writeString(this.Endereco, dos);

				// Double

				if (this.ValorImportacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImportacao);
				}

				// java.util.Date

				writeDate(this.DataFabricacao, dos);

				// Double

				if (this.AliquotaINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaINSS);
				}

				// Double

				if (this.AbatimentoINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AbatimentoINSS);
				}

				// String

				writeString(this.PrecoEmbarque, dos);

				// Double

				if (this.AliquotaISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaISS);
				}

				// Double

				if (this.BaseIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIPI);
				}

				// Double

				if (this.BaseISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseISS);
				}

				// Double

				if (this.ValorISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorISS);
				}

				// Double

				if (this.Seguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Seguro);
				}

				// Double

				if (this.ValorFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorFrete);
				}

				// String

				writeString(this.TipoDocumentoEnv, dos);

				// Double

				if (this.Despesa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Despesa);
				}

				// String

				writeString(this.OK, dos);

				// String

				writeString(this.CLVL, dos);

				// Double

				if (this.BaseINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseINSS);
				}

				// Double

				if (this.ICMFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMFrete);
				}

				// String

				writeString(this.Servico, dos);

				// String

				writeString(this.STServ, dos);

				// Double

				if (this.ValorINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorINSS);
				}

				// String

				writeString(this.TipoDoc, dos);

				// String

				writeString(this.TipoRem, dos);

				// Double

				if (this.ValorBruto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorBruto);
				}

				// java.util.Date

				writeDate(this.DataDigit, dos);

				// String

				writeString(this.SitTrib, dos);

				// String

				writeString(this.GrpCST, dos);

				// String

				writeString(this.FCICOD, dos);

				// Double

				if (this.AliquotaSOL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaSOL);
				}

				// String

				writeString(this.TNatRec, dos);

				// String

				writeString(this.CNatRec, dos);

				// String

				writeString(this.Estoque, dos);

				// Double

				if (this.AliqCSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCSL);
				}

				// Double

				if (this.AliqPIS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPIS);
				}

				// Double

				if (this.AliqCOF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCOF);
				}

				// Double

				if (this.BaseDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseDes);
				}

				// Double

				if (this.AliqCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCMP);
				}

				// Double

				if (this.Difal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Difal);
				}

				// Double

				if (this.ICMSCom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSCom);
				}

				// Double

				if (this.PDDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDDes);
				}

				// Double

				if (this.PDOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDOrig);
				}

				// Double

				if (this.VFCPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFCPDIF);
				}

				// Double

				if (this.AlFCCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AlFCCMP);
				}

				// Double

				if (this.BaseCPB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseCPB);
				}

				// Double

				if (this.AliqPro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPro);
				}

				// Double

				if (this.Margem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Margem);
				}

				// Double

				if (this.UBCSALD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.UBCSALD);
				}

				// String

				writeString(this.UBCCHBX, dos);

				// Double

				if (this.ALFCPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALFCPST);
				}

				// Double

				if (this.ALQFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALQFECP);
				}

				// Double

				if (this.ICMSDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDIF);
				}

				// Double

				if (this.VALFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VALFECP);
				}

				// Double

				if (this.VFECPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFECPST);
				}

				// Double

				if (this.VOPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VOPDIF);
				}

				// String

				writeString(this.Deletado, dos);

				// Integer

				writeInteger(this.Ukey, dos);

				// Integer

				writeInteger(this.Registro, dos);

				// java.util.Date

				writeDate(this.DataUltimaAlteracao, dos);

				// java.util.Date

				writeDate(this.DataInsercao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.SequenciaItem, dos);

				// String

				writeString(this.CodigoProduto, dos);

				// String

				writeString(this.SegundaUnidade, dos);

				// String

				writeString(this.UnidadeMedida, dos);

				// Double

				if (this.Quantidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Quantidade);
				}

				// Double

				if (this.PrecoVenda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoVenda);
				}

				// Double

				if (this.PrecoTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoTotal);
				}

				// Double

				if (this.ValorIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorIPI);
				}

				// Double

				if (this.ValorICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorICMS);
				}

				// String

				writeString(this.CodigoTes, dos);

				// String

				writeString(this.CFOP, dos);

				// Double

				if (this.Desconto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Desconto);
				}

				// Double

				if (this.IPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.IPI);
				}

				// Double

				if (this.ValorCSSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorCSSL);
				}

				// Double

				if (this.AliquotaICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaICM);
				}

				// Double

				if (this.Peso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Peso);
				}

				// String

				writeString(this.NumeroPedido, dos);

				// String

				writeString(this.ItemPV, dos);

				// String

				writeString(this.Cliente, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Armazem, dos);

				// String

				writeString(this.NotaFiscal, dos);

				// String

				writeString(this.Serie, dos);

				// String

				writeString(this.Grupo, dos);

				// String

				writeString(this.TipoProduto, dos);

				// java.util.Date

				writeDate(this.DataEmissao, dos);

				// Double

				if (this.CustoProduto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.CustoProduto);
				}

				// Double

				if (this.Custo2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo2);
				}

				// Double

				if (this.Custo3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo3);
				}

				// Double

				if (this.Custo4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo4);
				}

				// Double

				if (this.Custo5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Custo5);
				}

				// Double

				if (this.PrecoUnitario == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PrecoUnitario);
				}

				// Double

				if (this.QuantidadeSegundaUnidade == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeSegundaUnidade);
				}

				// String

				writeString(this.NumeroSequencial, dos);

				// String

				writeString(this.Estado, dos);

				// Double

				if (this.DescontoValor == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DescontoValor);
				}

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.NotaFiscalOrigem, dos);

				// String

				writeString(this.SerieOrigem, dos);

				// Double

				if (this.QuantidadeDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.QuantidadeDevolucao);
				}

				// Double

				if (this.ValorDevolucao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorDevolucao);
				}

				// String

				writeString(this.OrigemLancamento, dos);

				// Double

				if (this.BaseICMS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICMS);
				}

				// Double

				if (this.BaseICM == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseICM);
				}

				// Double

				if (this.ValorAcrescimo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcrescimo);
				}

				// Double

				if (this.ICMSRetido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSRetido);
				}

				// Double

				if (this.Comissao1 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao1);
				}

				// Double

				if (this.Comissao2 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao2);
				}

				// Double

				if (this.Comissao3 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao3);
				}

				// Double

				if (this.Comissao4 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao4);
				}

				// Double

				if (this.Comissao5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao5);
				}

				// String

				writeString(this.NumeroLote, dos);

				// String

				writeString(this.Lote, dos);

				// java.util.Date

				writeDate(this.DataValidade, dos);

				// String

				writeString(this.ClasseFiscal, dos);

				// Double

				if (this.BaseImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto5);
				}

				// Double

				if (this.BaseImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseImposto6);
				}

				// Double

				if (this.ValorImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto5);
				}

				// Double

				if (this.ValorImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImposto6);
				}

				// Double

				if (this.AliquotaImposto5 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto5);
				}

				// Double

				if (this.AliquotaImposto6 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaImposto6);
				}

				// String

				writeString(this.CentroCusto, dos);

				// String

				writeString(this.Endereco, dos);

				// Double

				if (this.ValorImportacao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorImportacao);
				}

				// java.util.Date

				writeDate(this.DataFabricacao, dos);

				// Double

				if (this.AliquotaINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaINSS);
				}

				// Double

				if (this.AbatimentoINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AbatimentoINSS);
				}

				// String

				writeString(this.PrecoEmbarque, dos);

				// Double

				if (this.AliquotaISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaISS);
				}

				// Double

				if (this.BaseIPI == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseIPI);
				}

				// Double

				if (this.BaseISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseISS);
				}

				// Double

				if (this.ValorISS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorISS);
				}

				// Double

				if (this.Seguro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Seguro);
				}

				// Double

				if (this.ValorFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorFrete);
				}

				// String

				writeString(this.TipoDocumentoEnv, dos);

				// Double

				if (this.Despesa == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Despesa);
				}

				// String

				writeString(this.OK, dos);

				// String

				writeString(this.CLVL, dos);

				// Double

				if (this.BaseINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseINSS);
				}

				// Double

				if (this.ICMFrete == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMFrete);
				}

				// String

				writeString(this.Servico, dos);

				// String

				writeString(this.STServ, dos);

				// Double

				if (this.ValorINSS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorINSS);
				}

				// String

				writeString(this.TipoDoc, dos);

				// String

				writeString(this.TipoRem, dos);

				// Double

				if (this.ValorBruto == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorBruto);
				}

				// java.util.Date

				writeDate(this.DataDigit, dos);

				// String

				writeString(this.SitTrib, dos);

				// String

				writeString(this.GrpCST, dos);

				// String

				writeString(this.FCICOD, dos);

				// Double

				if (this.AliquotaSOL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaSOL);
				}

				// String

				writeString(this.TNatRec, dos);

				// String

				writeString(this.CNatRec, dos);

				// String

				writeString(this.Estoque, dos);

				// Double

				if (this.AliqCSL == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCSL);
				}

				// Double

				if (this.AliqPIS == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPIS);
				}

				// Double

				if (this.AliqCOF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCOF);
				}

				// Double

				if (this.BaseDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseDes);
				}

				// Double

				if (this.AliqCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqCMP);
				}

				// Double

				if (this.Difal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Difal);
				}

				// Double

				if (this.ICMSCom == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSCom);
				}

				// Double

				if (this.PDDes == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDDes);
				}

				// Double

				if (this.PDOrig == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PDOrig);
				}

				// Double

				if (this.VFCPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFCPDIF);
				}

				// Double

				if (this.AlFCCMP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AlFCCMP);
				}

				// Double

				if (this.BaseCPB == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.BaseCPB);
				}

				// Double

				if (this.AliqPro == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliqPro);
				}

				// Double

				if (this.Margem == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Margem);
				}

				// Double

				if (this.UBCSALD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.UBCSALD);
				}

				// String

				writeString(this.UBCCHBX, dos);

				// Double

				if (this.ALFCPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALFCPST);
				}

				// Double

				if (this.ALQFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ALQFECP);
				}

				// Double

				if (this.ICMSDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ICMSDIF);
				}

				// Double

				if (this.VALFECP == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VALFECP);
				}

				// Double

				if (this.VFECPST == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VFECPST);
				}

				// Double

				if (this.VOPDIF == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.VOPDIF);
				}

				// String

				writeString(this.Deletado, dos);

				// Integer

				writeInteger(this.Ukey, dos);

				// Integer

				writeInteger(this.Registro, dos);

				// java.util.Date

				writeDate(this.DataUltimaAlteracao, dos);

				// java.util.Date

				writeDate(this.DataInsercao, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Filial=" + Filial);
			sb.append(",SequenciaItem=" + SequenciaItem);
			sb.append(",CodigoProduto=" + CodigoProduto);
			sb.append(",SegundaUnidade=" + SegundaUnidade);
			sb.append(",UnidadeMedida=" + UnidadeMedida);
			sb.append(",Quantidade=" + String.valueOf(Quantidade));
			sb.append(",PrecoVenda=" + String.valueOf(PrecoVenda));
			sb.append(",PrecoTotal=" + String.valueOf(PrecoTotal));
			sb.append(",ValorIPI=" + String.valueOf(ValorIPI));
			sb.append(",ValorICMS=" + String.valueOf(ValorICMS));
			sb.append(",CodigoTes=" + CodigoTes);
			sb.append(",CFOP=" + CFOP);
			sb.append(",Desconto=" + String.valueOf(Desconto));
			sb.append(",IPI=" + String.valueOf(IPI));
			sb.append(",ValorCSSL=" + String.valueOf(ValorCSSL));
			sb.append(",AliquotaICM=" + String.valueOf(AliquotaICM));
			sb.append(",Peso=" + String.valueOf(Peso));
			sb.append(",NumeroPedido=" + NumeroPedido);
			sb.append(",ItemPV=" + ItemPV);
			sb.append(",Cliente=" + Cliente);
			sb.append(",Loja=" + Loja);
			sb.append(",Armazem=" + Armazem);
			sb.append(",NotaFiscal=" + NotaFiscal);
			sb.append(",Serie=" + Serie);
			sb.append(",Grupo=" + Grupo);
			sb.append(",TipoProduto=" + TipoProduto);
			sb.append(",DataEmissao=" + String.valueOf(DataEmissao));
			sb.append(",CustoProduto=" + String.valueOf(CustoProduto));
			sb.append(",Custo2=" + String.valueOf(Custo2));
			sb.append(",Custo3=" + String.valueOf(Custo3));
			sb.append(",Custo4=" + String.valueOf(Custo4));
			sb.append(",Custo5=" + String.valueOf(Custo5));
			sb.append(",PrecoUnitario=" + String.valueOf(PrecoUnitario));
			sb.append(",QuantidadeSegundaUnidade=" + String.valueOf(QuantidadeSegundaUnidade));
			sb.append(",NumeroSequencial=" + NumeroSequencial);
			sb.append(",Estado=" + Estado);
			sb.append(",DescontoValor=" + String.valueOf(DescontoValor));
			sb.append(",Tipo=" + Tipo);
			sb.append(",NotaFiscalOrigem=" + NotaFiscalOrigem);
			sb.append(",SerieOrigem=" + SerieOrigem);
			sb.append(",QuantidadeDevolucao=" + String.valueOf(QuantidadeDevolucao));
			sb.append(",ValorDevolucao=" + String.valueOf(ValorDevolucao));
			sb.append(",OrigemLancamento=" + OrigemLancamento);
			sb.append(",BaseICMS=" + String.valueOf(BaseICMS));
			sb.append(",BaseICM=" + String.valueOf(BaseICM));
			sb.append(",ValorAcrescimo=" + String.valueOf(ValorAcrescimo));
			sb.append(",ICMSRetido=" + String.valueOf(ICMSRetido));
			sb.append(",Comissao1=" + String.valueOf(Comissao1));
			sb.append(",Comissao2=" + String.valueOf(Comissao2));
			sb.append(",Comissao3=" + String.valueOf(Comissao3));
			sb.append(",Comissao4=" + String.valueOf(Comissao4));
			sb.append(",Comissao5=" + String.valueOf(Comissao5));
			sb.append(",NumeroLote=" + NumeroLote);
			sb.append(",Lote=" + Lote);
			sb.append(",DataValidade=" + String.valueOf(DataValidade));
			sb.append(",ClasseFiscal=" + ClasseFiscal);
			sb.append(",BaseImposto5=" + String.valueOf(BaseImposto5));
			sb.append(",BaseImposto6=" + String.valueOf(BaseImposto6));
			sb.append(",ValorImposto5=" + String.valueOf(ValorImposto5));
			sb.append(",ValorImposto6=" + String.valueOf(ValorImposto6));
			sb.append(",AliquotaImposto5=" + String.valueOf(AliquotaImposto5));
			sb.append(",AliquotaImposto6=" + String.valueOf(AliquotaImposto6));
			sb.append(",CentroCusto=" + CentroCusto);
			sb.append(",Endereco=" + Endereco);
			sb.append(",ValorImportacao=" + String.valueOf(ValorImportacao));
			sb.append(",DataFabricacao=" + String.valueOf(DataFabricacao));
			sb.append(",AliquotaINSS=" + String.valueOf(AliquotaINSS));
			sb.append(",AbatimentoINSS=" + String.valueOf(AbatimentoINSS));
			sb.append(",PrecoEmbarque=" + PrecoEmbarque);
			sb.append(",AliquotaISS=" + String.valueOf(AliquotaISS));
			sb.append(",BaseIPI=" + String.valueOf(BaseIPI));
			sb.append(",BaseISS=" + String.valueOf(BaseISS));
			sb.append(",ValorISS=" + String.valueOf(ValorISS));
			sb.append(",Seguro=" + String.valueOf(Seguro));
			sb.append(",ValorFrete=" + String.valueOf(ValorFrete));
			sb.append(",TipoDocumentoEnv=" + TipoDocumentoEnv);
			sb.append(",Despesa=" + String.valueOf(Despesa));
			sb.append(",OK=" + OK);
			sb.append(",CLVL=" + CLVL);
			sb.append(",BaseINSS=" + String.valueOf(BaseINSS));
			sb.append(",ICMFrete=" + String.valueOf(ICMFrete));
			sb.append(",Servico=" + Servico);
			sb.append(",STServ=" + STServ);
			sb.append(",ValorINSS=" + String.valueOf(ValorINSS));
			sb.append(",TipoDoc=" + TipoDoc);
			sb.append(",TipoRem=" + TipoRem);
			sb.append(",ValorBruto=" + String.valueOf(ValorBruto));
			sb.append(",DataDigit=" + String.valueOf(DataDigit));
			sb.append(",SitTrib=" + SitTrib);
			sb.append(",GrpCST=" + GrpCST);
			sb.append(",FCICOD=" + FCICOD);
			sb.append(",AliquotaSOL=" + String.valueOf(AliquotaSOL));
			sb.append(",TNatRec=" + TNatRec);
			sb.append(",CNatRec=" + CNatRec);
			sb.append(",Estoque=" + Estoque);
			sb.append(",AliqCSL=" + String.valueOf(AliqCSL));
			sb.append(",AliqPIS=" + String.valueOf(AliqPIS));
			sb.append(",AliqCOF=" + String.valueOf(AliqCOF));
			sb.append(",BaseDes=" + String.valueOf(BaseDes));
			sb.append(",AliqCMP=" + String.valueOf(AliqCMP));
			sb.append(",Difal=" + String.valueOf(Difal));
			sb.append(",ICMSCom=" + String.valueOf(ICMSCom));
			sb.append(",PDDes=" + String.valueOf(PDDes));
			sb.append(",PDOrig=" + String.valueOf(PDOrig));
			sb.append(",VFCPDIF=" + String.valueOf(VFCPDIF));
			sb.append(",AlFCCMP=" + String.valueOf(AlFCCMP));
			sb.append(",BaseCPB=" + String.valueOf(BaseCPB));
			sb.append(",AliqPro=" + String.valueOf(AliqPro));
			sb.append(",Margem=" + String.valueOf(Margem));
			sb.append(",UBCSALD=" + String.valueOf(UBCSALD));
			sb.append(",UBCCHBX=" + UBCCHBX);
			sb.append(",ALFCPST=" + String.valueOf(ALFCPST));
			sb.append(",ALQFECP=" + String.valueOf(ALQFECP));
			sb.append(",ICMSDIF=" + String.valueOf(ICMSDIF));
			sb.append(",VALFECP=" + String.valueOf(VALFECP));
			sb.append(",VFECPST=" + String.valueOf(VFECPST));
			sb.append(",VOPDIF=" + String.valueOf(VOPDIF));
			sb.append(",Deletado=" + Deletado);
			sb.append(",Ukey=" + String.valueOf(Ukey));
			sb.append(",Registro=" + String.valueOf(Registro));
			sb.append(",DataUltimaAlteracao=" + String.valueOf(DataUltimaAlteracao));
			sb.append(",DataInsercao=" + String.valueOf(DataInsercao));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Filial == null) {
				sb.append("<null>");
			} else {
				sb.append(Filial);
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

			if (SegundaUnidade == null) {
				sb.append("<null>");
			} else {
				sb.append(SegundaUnidade);
			}

			sb.append("|");

			if (UnidadeMedida == null) {
				sb.append("<null>");
			} else {
				sb.append(UnidadeMedida);
			}

			sb.append("|");

			if (Quantidade == null) {
				sb.append("<null>");
			} else {
				sb.append(Quantidade);
			}

			sb.append("|");

			if (PrecoVenda == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoVenda);
			}

			sb.append("|");

			if (PrecoTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoTotal);
			}

			sb.append("|");

			if (ValorIPI == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorIPI);
			}

			sb.append("|");

			if (ValorICMS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorICMS);
			}

			sb.append("|");

			if (CodigoTes == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoTes);
			}

			sb.append("|");

			if (CFOP == null) {
				sb.append("<null>");
			} else {
				sb.append(CFOP);
			}

			sb.append("|");

			if (Desconto == null) {
				sb.append("<null>");
			} else {
				sb.append(Desconto);
			}

			sb.append("|");

			if (IPI == null) {
				sb.append("<null>");
			} else {
				sb.append(IPI);
			}

			sb.append("|");

			if (ValorCSSL == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorCSSL);
			}

			sb.append("|");

			if (AliquotaICM == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaICM);
			}

			sb.append("|");

			if (Peso == null) {
				sb.append("<null>");
			} else {
				sb.append(Peso);
			}

			sb.append("|");

			if (NumeroPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPedido);
			}

			sb.append("|");

			if (ItemPV == null) {
				sb.append("<null>");
			} else {
				sb.append(ItemPV);
			}

			sb.append("|");

			if (Cliente == null) {
				sb.append("<null>");
			} else {
				sb.append(Cliente);
			}

			sb.append("|");

			if (Loja == null) {
				sb.append("<null>");
			} else {
				sb.append(Loja);
			}

			sb.append("|");

			if (Armazem == null) {
				sb.append("<null>");
			} else {
				sb.append(Armazem);
			}

			sb.append("|");

			if (NotaFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(NotaFiscal);
			}

			sb.append("|");

			if (Serie == null) {
				sb.append("<null>");
			} else {
				sb.append(Serie);
			}

			sb.append("|");

			if (Grupo == null) {
				sb.append("<null>");
			} else {
				sb.append(Grupo);
			}

			sb.append("|");

			if (TipoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoProduto);
			}

			sb.append("|");

			if (DataEmissao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataEmissao);
			}

			sb.append("|");

			if (CustoProduto == null) {
				sb.append("<null>");
			} else {
				sb.append(CustoProduto);
			}

			sb.append("|");

			if (Custo2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo2);
			}

			sb.append("|");

			if (Custo3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo3);
			}

			sb.append("|");

			if (Custo4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo4);
			}

			sb.append("|");

			if (Custo5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Custo5);
			}

			sb.append("|");

			if (PrecoUnitario == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoUnitario);
			}

			sb.append("|");

			if (QuantidadeSegundaUnidade == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeSegundaUnidade);
			}

			sb.append("|");

			if (NumeroSequencial == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroSequencial);
			}

			sb.append("|");

			if (Estado == null) {
				sb.append("<null>");
			} else {
				sb.append(Estado);
			}

			sb.append("|");

			if (DescontoValor == null) {
				sb.append("<null>");
			} else {
				sb.append(DescontoValor);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (NotaFiscalOrigem == null) {
				sb.append("<null>");
			} else {
				sb.append(NotaFiscalOrigem);
			}

			sb.append("|");

			if (SerieOrigem == null) {
				sb.append("<null>");
			} else {
				sb.append(SerieOrigem);
			}

			sb.append("|");

			if (QuantidadeDevolucao == null) {
				sb.append("<null>");
			} else {
				sb.append(QuantidadeDevolucao);
			}

			sb.append("|");

			if (ValorDevolucao == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorDevolucao);
			}

			sb.append("|");

			if (OrigemLancamento == null) {
				sb.append("<null>");
			} else {
				sb.append(OrigemLancamento);
			}

			sb.append("|");

			if (BaseICMS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseICMS);
			}

			sb.append("|");

			if (BaseICM == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseICM);
			}

			sb.append("|");

			if (ValorAcrescimo == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorAcrescimo);
			}

			sb.append("|");

			if (ICMSRetido == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSRetido);
			}

			sb.append("|");

			if (Comissao1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao1);
			}

			sb.append("|");

			if (Comissao2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao2);
			}

			sb.append("|");

			if (Comissao3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao3);
			}

			sb.append("|");

			if (Comissao4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao4);
			}

			sb.append("|");

			if (Comissao5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao5);
			}

			sb.append("|");

			if (NumeroLote == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroLote);
			}

			sb.append("|");

			if (Lote == null) {
				sb.append("<null>");
			} else {
				sb.append(Lote);
			}

			sb.append("|");

			if (DataValidade == null) {
				sb.append("<null>");
			} else {
				sb.append(DataValidade);
			}

			sb.append("|");

			if (ClasseFiscal == null) {
				sb.append("<null>");
			} else {
				sb.append(ClasseFiscal);
			}

			sb.append("|");

			if (BaseImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseImposto5);
			}

			sb.append("|");

			if (BaseImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseImposto6);
			}

			sb.append("|");

			if (ValorImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImposto5);
			}

			sb.append("|");

			if (ValorImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImposto6);
			}

			sb.append("|");

			if (AliquotaImposto5 == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaImposto5);
			}

			sb.append("|");

			if (AliquotaImposto6 == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaImposto6);
			}

			sb.append("|");

			if (CentroCusto == null) {
				sb.append("<null>");
			} else {
				sb.append(CentroCusto);
			}

			sb.append("|");

			if (Endereco == null) {
				sb.append("<null>");
			} else {
				sb.append(Endereco);
			}

			sb.append("|");

			if (ValorImportacao == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorImportacao);
			}

			sb.append("|");

			if (DataFabricacao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataFabricacao);
			}

			sb.append("|");

			if (AliquotaINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaINSS);
			}

			sb.append("|");

			if (AbatimentoINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(AbatimentoINSS);
			}

			sb.append("|");

			if (PrecoEmbarque == null) {
				sb.append("<null>");
			} else {
				sb.append(PrecoEmbarque);
			}

			sb.append("|");

			if (AliquotaISS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaISS);
			}

			sb.append("|");

			if (BaseIPI == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseIPI);
			}

			sb.append("|");

			if (BaseISS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseISS);
			}

			sb.append("|");

			if (ValorISS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorISS);
			}

			sb.append("|");

			if (Seguro == null) {
				sb.append("<null>");
			} else {
				sb.append(Seguro);
			}

			sb.append("|");

			if (ValorFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorFrete);
			}

			sb.append("|");

			if (TipoDocumentoEnv == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoDocumentoEnv);
			}

			sb.append("|");

			if (Despesa == null) {
				sb.append("<null>");
			} else {
				sb.append(Despesa);
			}

			sb.append("|");

			if (OK == null) {
				sb.append("<null>");
			} else {
				sb.append(OK);
			}

			sb.append("|");

			if (CLVL == null) {
				sb.append("<null>");
			} else {
				sb.append(CLVL);
			}

			sb.append("|");

			if (BaseINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseINSS);
			}

			sb.append("|");

			if (ICMFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMFrete);
			}

			sb.append("|");

			if (Servico == null) {
				sb.append("<null>");
			} else {
				sb.append(Servico);
			}

			sb.append("|");

			if (STServ == null) {
				sb.append("<null>");
			} else {
				sb.append(STServ);
			}

			sb.append("|");

			if (ValorINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorINSS);
			}

			sb.append("|");

			if (TipoDoc == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoDoc);
			}

			sb.append("|");

			if (TipoRem == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoRem);
			}

			sb.append("|");

			if (ValorBruto == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorBruto);
			}

			sb.append("|");

			if (DataDigit == null) {
				sb.append("<null>");
			} else {
				sb.append(DataDigit);
			}

			sb.append("|");

			if (SitTrib == null) {
				sb.append("<null>");
			} else {
				sb.append(SitTrib);
			}

			sb.append("|");

			if (GrpCST == null) {
				sb.append("<null>");
			} else {
				sb.append(GrpCST);
			}

			sb.append("|");

			if (FCICOD == null) {
				sb.append("<null>");
			} else {
				sb.append(FCICOD);
			}

			sb.append("|");

			if (AliquotaSOL == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaSOL);
			}

			sb.append("|");

			if (TNatRec == null) {
				sb.append("<null>");
			} else {
				sb.append(TNatRec);
			}

			sb.append("|");

			if (CNatRec == null) {
				sb.append("<null>");
			} else {
				sb.append(CNatRec);
			}

			sb.append("|");

			if (Estoque == null) {
				sb.append("<null>");
			} else {
				sb.append(Estoque);
			}

			sb.append("|");

			if (AliqCSL == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCSL);
			}

			sb.append("|");

			if (AliqPIS == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqPIS);
			}

			sb.append("|");

			if (AliqCOF == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCOF);
			}

			sb.append("|");

			if (BaseDes == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseDes);
			}

			sb.append("|");

			if (AliqCMP == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqCMP);
			}

			sb.append("|");

			if (Difal == null) {
				sb.append("<null>");
			} else {
				sb.append(Difal);
			}

			sb.append("|");

			if (ICMSCom == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSCom);
			}

			sb.append("|");

			if (PDDes == null) {
				sb.append("<null>");
			} else {
				sb.append(PDDes);
			}

			sb.append("|");

			if (PDOrig == null) {
				sb.append("<null>");
			} else {
				sb.append(PDOrig);
			}

			sb.append("|");

			if (VFCPDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(VFCPDIF);
			}

			sb.append("|");

			if (AlFCCMP == null) {
				sb.append("<null>");
			} else {
				sb.append(AlFCCMP);
			}

			sb.append("|");

			if (BaseCPB == null) {
				sb.append("<null>");
			} else {
				sb.append(BaseCPB);
			}

			sb.append("|");

			if (AliqPro == null) {
				sb.append("<null>");
			} else {
				sb.append(AliqPro);
			}

			sb.append("|");

			if (Margem == null) {
				sb.append("<null>");
			} else {
				sb.append(Margem);
			}

			sb.append("|");

			if (UBCSALD == null) {
				sb.append("<null>");
			} else {
				sb.append(UBCSALD);
			}

			sb.append("|");

			if (UBCCHBX == null) {
				sb.append("<null>");
			} else {
				sb.append(UBCCHBX);
			}

			sb.append("|");

			if (ALFCPST == null) {
				sb.append("<null>");
			} else {
				sb.append(ALFCPST);
			}

			sb.append("|");

			if (ALQFECP == null) {
				sb.append("<null>");
			} else {
				sb.append(ALQFECP);
			}

			sb.append("|");

			if (ICMSDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(ICMSDIF);
			}

			sb.append("|");

			if (VALFECP == null) {
				sb.append("<null>");
			} else {
				sb.append(VALFECP);
			}

			sb.append("|");

			if (VFECPST == null) {
				sb.append("<null>");
			} else {
				sb.append(VFECPST);
			}

			sb.append("|");

			if (VOPDIF == null) {
				sb.append("<null>");
			} else {
				sb.append(VOPDIF);
			}

			sb.append("|");

			if (Deletado == null) {
				sb.append("<null>");
			} else {
				sb.append(Deletado);
			}

			sb.append("|");

			if (Ukey == null) {
				sb.append("<null>");
			} else {
				sb.append(Ukey);
			}

			sb.append("|");

			if (Registro == null) {
				sb.append("<null>");
			} else {
				sb.append(Registro);
			}

			sb.append("|");

			if (DataUltimaAlteracao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimaAlteracao);
			}

			sb.append("|");

			if (DataInsercao == null) {
				sb.append("<null>");
			} else {
				sb.append(DataInsercao);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Filial, other.Filial);
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

			returnValue = checkNullsAndCompare(this.NotaFiscal, other.NotaFiscal);
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
		org.slf4j.MDC.put("_subJobPid", "yHFshJ_" + subJobPidCounter.getAndIncrement());

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
									+ "\"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/NotasSaidas.qvd\"");
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
						outtFileOutputDelimited_1.write("Filial");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SequenciaItem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoProduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegundaUnidade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UnidadeMedida");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Quantidade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoVenda");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoTotal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorIPI");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorICMS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoTes");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CFOP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Desconto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("IPI");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorCSSL");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaICM");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Peso");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPedido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ItemPV");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Cliente");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Loja");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Armazem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NotaFiscal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Serie");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Grupo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoProduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataEmissao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CustoProduto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Custo2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Custo3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Custo4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Custo5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoUnitario");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QuantidadeSegundaUnidade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroSequencial");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Estado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DescontoValor");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tipo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NotaFiscalOrigem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SerieOrigem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("QuantidadeDevolucao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorDevolucao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("OrigemLancamento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseICMS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseICM");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorAcrescimo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMSRetido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroLote");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Lote");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataValidade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ClasseFiscal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseImposto5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseImposto6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorImposto5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorImposto6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaImposto5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaImposto6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CentroCusto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Endereco");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorImportacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataFabricacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaINSS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AbatimentoINSS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrecoEmbarque");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaISS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseIPI");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseISS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorISS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Seguro");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorFrete");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoDocumentoEnv");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Despesa");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("OK");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CLVL");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseINSS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMFrete");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Servico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("STServ");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorINSS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoDoc");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoRem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorBruto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataDigit");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SitTrib");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("GrpCST");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_1(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("FCICOD");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaSOL");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TNatRec");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CNatRec");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Estoque");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliqCSL");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliqPIS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliqCOF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseDes");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliqCMP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Difal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMSCom");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PDDes");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PDOrig");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VFCPDIF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AlFCCMP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BaseCPB");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliqPro");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Margem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UBCSALD");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UBCCHBX");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ALFCPST");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ALQFECP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ICMSDIF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VALFECP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VFECPST");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VOPDIF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Deletado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Ukey");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Registro");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataUltimaAlteracao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataInsercao");
					}

					public void putValue_0(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.Filial != null) {
							sb_tFileOutputDelimited_1.append(row4.Filial);
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
						if (row4.SegundaUnidade != null) {
							sb_tFileOutputDelimited_1.append(row4.SegundaUnidade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.UnidadeMedida != null) {
							sb_tFileOutputDelimited_1.append(row4.UnidadeMedida);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Quantidade != null) {
							sb_tFileOutputDelimited_1.append(row4.Quantidade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoVenda != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoVenda);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoTotal != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoTotal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorIPI != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorIPI);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorICMS != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorICMS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CodigoTes != null) {
							sb_tFileOutputDelimited_1.append(row4.CodigoTes);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CFOP != null) {
							sb_tFileOutputDelimited_1.append(row4.CFOP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Desconto != null) {
							sb_tFileOutputDelimited_1.append(row4.Desconto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.IPI != null) {
							sb_tFileOutputDelimited_1.append(row4.IPI);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorCSSL != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorCSSL);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaICM != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaICM);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Peso != null) {
							sb_tFileOutputDelimited_1.append(row4.Peso);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroPedido != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroPedido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ItemPV != null) {
							sb_tFileOutputDelimited_1.append(row4.ItemPV);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Cliente != null) {
							sb_tFileOutputDelimited_1.append(row4.Cliente);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Loja != null) {
							sb_tFileOutputDelimited_1.append(row4.Loja);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Armazem != null) {
							sb_tFileOutputDelimited_1.append(row4.Armazem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NotaFiscal != null) {
							sb_tFileOutputDelimited_1.append(row4.NotaFiscal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Serie != null) {
							sb_tFileOutputDelimited_1.append(row4.Serie);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Grupo != null) {
							sb_tFileOutputDelimited_1.append(row4.Grupo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TipoProduto != null) {
							sb_tFileOutputDelimited_1.append(row4.TipoProduto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataEmissao != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DataEmissao, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CustoProduto != null) {
							sb_tFileOutputDelimited_1.append(row4.CustoProduto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Custo2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Custo2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Custo3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Custo3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Custo4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Custo4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Custo5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Custo5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoUnitario != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoUnitario);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QuantidadeSegundaUnidade != null) {
							sb_tFileOutputDelimited_1.append(row4.QuantidadeSegundaUnidade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroSequencial != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroSequencial);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Estado != null) {
							sb_tFileOutputDelimited_1.append(row4.Estado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DescontoValor != null) {
							sb_tFileOutputDelimited_1.append(row4.DescontoValor);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Tipo != null) {
							sb_tFileOutputDelimited_1.append(row4.Tipo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NotaFiscalOrigem != null) {
							sb_tFileOutputDelimited_1.append(row4.NotaFiscalOrigem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SerieOrigem != null) {
							sb_tFileOutputDelimited_1.append(row4.SerieOrigem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.QuantidadeDevolucao != null) {
							sb_tFileOutputDelimited_1.append(row4.QuantidadeDevolucao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorDevolucao != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorDevolucao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.OrigemLancamento != null) {
							sb_tFileOutputDelimited_1.append(row4.OrigemLancamento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseICMS != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseICMS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseICM != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseICM);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorAcrescimo != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorAcrescimo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMSRetido != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMSRetido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comissao1 != null) {
							sb_tFileOutputDelimited_1.append(row4.Comissao1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comissao2 != null) {
							sb_tFileOutputDelimited_1.append(row4.Comissao2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comissao3 != null) {
							sb_tFileOutputDelimited_1.append(row4.Comissao3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comissao4 != null) {
							sb_tFileOutputDelimited_1.append(row4.Comissao4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Comissao5 != null) {
							sb_tFileOutputDelimited_1.append(row4.Comissao5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.NumeroLote != null) {
							sb_tFileOutputDelimited_1.append(row4.NumeroLote);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Lote != null) {
							sb_tFileOutputDelimited_1.append(row4.Lote);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataValidade != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DataValidade, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ClasseFiscal != null) {
							sb_tFileOutputDelimited_1.append(row4.ClasseFiscal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseImposto5 != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseImposto5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseImposto6 != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseImposto6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorImposto5 != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorImposto5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorImposto6 != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorImposto6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaImposto5 != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaImposto5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaImposto6 != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaImposto6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CentroCusto != null) {
							sb_tFileOutputDelimited_1.append(row4.CentroCusto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Endereco != null) {
							sb_tFileOutputDelimited_1.append(row4.Endereco);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorImportacao != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorImportacao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataFabricacao != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DataFabricacao, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaINSS != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaINSS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AbatimentoINSS != null) {
							sb_tFileOutputDelimited_1.append(row4.AbatimentoINSS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PrecoEmbarque != null) {
							sb_tFileOutputDelimited_1.append(row4.PrecoEmbarque);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaISS != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaISS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseIPI != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseIPI);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseISS != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseISS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorISS != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorISS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Seguro != null) {
							sb_tFileOutputDelimited_1.append(row4.Seguro);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorFrete != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorFrete);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TipoDocumentoEnv != null) {
							sb_tFileOutputDelimited_1.append(row4.TipoDocumentoEnv);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Despesa != null) {
							sb_tFileOutputDelimited_1.append(row4.Despesa);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.OK != null) {
							sb_tFileOutputDelimited_1.append(row4.OK);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CLVL != null) {
							sb_tFileOutputDelimited_1.append(row4.CLVL);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseINSS != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseINSS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMFrete != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMFrete);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Servico != null) {
							sb_tFileOutputDelimited_1.append(row4.Servico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.STServ != null) {
							sb_tFileOutputDelimited_1.append(row4.STServ);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorINSS != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorINSS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TipoDoc != null) {
							sb_tFileOutputDelimited_1.append(row4.TipoDoc);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TipoRem != null) {
							sb_tFileOutputDelimited_1.append(row4.TipoRem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ValorBruto != null) {
							sb_tFileOutputDelimited_1.append(row4.ValorBruto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataDigit != null) {
							sb_tFileOutputDelimited_1.append(FormatterUtils.format_Date(row4.DataDigit, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.SitTrib != null) {
							sb_tFileOutputDelimited_1.append(row4.SitTrib);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.GrpCST != null) {
							sb_tFileOutputDelimited_1.append(row4.GrpCST);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_1(final row4Struct row4, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row4.FCICOD != null) {
							sb_tFileOutputDelimited_1.append(row4.FCICOD);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliquotaSOL != null) {
							sb_tFileOutputDelimited_1.append(row4.AliquotaSOL);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.TNatRec != null) {
							sb_tFileOutputDelimited_1.append(row4.TNatRec);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.CNatRec != null) {
							sb_tFileOutputDelimited_1.append(row4.CNatRec);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Estoque != null) {
							sb_tFileOutputDelimited_1.append(row4.Estoque);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliqCSL != null) {
							sb_tFileOutputDelimited_1.append(row4.AliqCSL);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliqPIS != null) {
							sb_tFileOutputDelimited_1.append(row4.AliqPIS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliqCOF != null) {
							sb_tFileOutputDelimited_1.append(row4.AliqCOF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseDes != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseDes);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliqCMP != null) {
							sb_tFileOutputDelimited_1.append(row4.AliqCMP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Difal != null) {
							sb_tFileOutputDelimited_1.append(row4.Difal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMSCom != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMSCom);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PDDes != null) {
							sb_tFileOutputDelimited_1.append(row4.PDDes);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.PDOrig != null) {
							sb_tFileOutputDelimited_1.append(row4.PDOrig);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VFCPDIF != null) {
							sb_tFileOutputDelimited_1.append(row4.VFCPDIF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AlFCCMP != null) {
							sb_tFileOutputDelimited_1.append(row4.AlFCCMP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.BaseCPB != null) {
							sb_tFileOutputDelimited_1.append(row4.BaseCPB);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.AliqPro != null) {
							sb_tFileOutputDelimited_1.append(row4.AliqPro);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Margem != null) {
							sb_tFileOutputDelimited_1.append(row4.Margem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.UBCSALD != null) {
							sb_tFileOutputDelimited_1.append(row4.UBCSALD);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.UBCCHBX != null) {
							sb_tFileOutputDelimited_1.append(row4.UBCCHBX);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ALFCPST != null) {
							sb_tFileOutputDelimited_1.append(row4.ALFCPST);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ALQFECP != null) {
							sb_tFileOutputDelimited_1.append(row4.ALQFECP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.ICMSDIF != null) {
							sb_tFileOutputDelimited_1.append(row4.ICMSDIF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VALFECP != null) {
							sb_tFileOutputDelimited_1.append(row4.VALFECP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VFECPST != null) {
							sb_tFileOutputDelimited_1.append(row4.VFECPST);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.VOPDIF != null) {
							sb_tFileOutputDelimited_1.append(row4.VOPDIF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Deletado != null) {
							sb_tFileOutputDelimited_1.append(row4.Deletado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Ukey != null) {
							sb_tFileOutputDelimited_1.append(row4.Ukey);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.Registro != null) {
							sb_tFileOutputDelimited_1.append(row4.Registro);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataUltimaAlteracao != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DataUltimaAlteracao, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row4.DataInsercao != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row4.DataInsercao, "dd-MM-yyyy"));
						}
					}
				}
				FileOutputDelimitedUtil_tFileOutputDelimited_1 fileOutputDelimitedUtil_tFileOutputDelimited_1 = new FileOutputDelimitedUtil_tFileOutputDelimited_1();
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/NotasSaidas.qvd"))
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

				cLabel = "Protheus_Producao";

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
							log4jParamters_tDBInput_1.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("DBNAME" + " = " + "\"TOTVS\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("USER" + " = " + "\"analytics\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:x3WDRh5nb5LueaAW2xra2J56xB8TCW2WjK4ZygkF2hp4cvnuUDqGbw4a")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TABLE" + " = " + "\"SD2010\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERY" + " = "
									+ "\"SELECT       D2_FILIAL		AS Filial,      D2_ITEM			AS SequenciaItem,      D2_COD			AS CodigoProduto,      D2_SEGUM		AS SegundaUnidade,      D2_UM			AS UnidadeMedida,      D2_QUANT		AS Quantidade,      D2_PRCVEN		AS PrecoVenda,      D2_TOTAL		AS PrecoTotal,      D2_VALIPI		AS ValorIPI,      D2_VALICM		AS ValorICMS,      D2_TES			AS CodigoTes,      D2_CF			AS CFOP,      D2_DESC			AS Desconto,      D2_IPI			AS IPI,      D2_VALCSL		AS ValorCSSL,      D2_PICM			AS AliquotaICM,      D2_PESO			AS Peso,      D2_PEDIDO		AS NumeroPedido,      D2_ITEMPV		AS ItemPV,      D2_CLIENTE		AS Cliente,      D2_LOJA			AS Loja,      D2_LOCAL		AS Armazem,      D2_DOC			AS NotaFiscal,      D2_SERIE		AS Serie,      D2_GRUPO		AS Grupo,      D2_TP			AS TipoProduto,      CAST(D2_EMISSAO AS DATE)		AS DataEmissao,      D2_CUSTO1		AS CustoProduto,      D2_CUSTO2		AS Custo2,      D2_CUSTO3		AS Custo3,      D2_CUSTO4		AS Custo4,      D2_CUSTO5		AS Custo5,      D2_PRUNIT		AS PrecoUnitario,      D2_QTSEGUM		AS QuantidadeSegundaUnidade,      D2_NUMSEQ		AS NumeroSequencial,      D2_EST			AS Estado,      D2_DESCON		AS DescontoValor,      D2_TIPO			AS Tipo,      D2_NFORI		AS NotaFiscalOrigem,      D2_SERIORI		AS SerieOrigem,      D2_QTDEDEV		AS QuantidadeDevolucao,      D2_VALDEV		AS ValorDevolucao,      D2_ORIGLAN		AS OrigemLancamento,      D2_BRICMS		AS BaseICMS,      D2_BASEICM		AS BaseICM,      D2_VALACRS		AS ValorAcrescimo,      D2_ICMSRET		AS ICMSRetido,      D2_COMIS1		AS Comissao1,      D2_COMIS2		AS Comissao2,      D2_COMIS3		AS Comissao3,      D2_COMIS4		AS Comissao4,      D2_COMIS5		AS Comissao5,      D2_LOTECTL		AS NumeroLote,      D2_NUMLOTE		AS Lote,      CAST(D2_DTVALID	AS DATE)	AS DataValidade,      D2_CLASFIS		AS ClasseFiscal,      D2_BASIMP5		AS BaseImposto5,      D2_BASIMP6		AS BaseImposto6,      D2_VALIMP5		AS ValorImposto5,      D2_VALIMP6		AS ValorImposto6,      D2_ALQIMP5		AS AliquotaImposto5,      D2_ALQIMP6		AS AliquotaImposto6,      D2_CCUSTO		AS CentroCusto,      D2_LOCALIZ		AS Endereco,      D2_VLIMPOR		AS ValorImportacao,      CAST(D2_DFABRIC AS DATE)		AS DataFabricacao,      D2_ALIQINS		AS AliquotaINSS,      D2_ABSCINS		AS AbatimentoINSS,      D2_PREEMB		AS PrecoEmbarque,      D2_ALIQISS		AS AliquotaISS,      D2_BASEIPI		AS BaseIPI,      D2_BASEISS		AS BaseISS,      D2_VALISS		AS ValorISS,      D2_SEGURO		AS Seguro,      D2_VALFRE		AS ValorFrete,      D2_TPDCENV		AS TipoDocumentoEnv,      D2_DESPESA		AS Despesa,      D2_OK			AS OK,      D2_CLVL			AS CLVL,      D2_BASEINS		AS BaseINSS,      D2_ICMFRET		AS ICMFrete,      D2_SERVIC		AS Servico,      D2_STSERV		AS STServ,      D2_VALINS		AS ValorINSS,      D2_TIPODOC		AS TipoDoc,      D2_TIPOREM		AS TipoRem,      D2_VALBRUT		AS ValorBruto,      CAST(D2_DTDIGIT	AS DATE)	AS DataDigit,      D2_SITTRIB		AS SitTrib,      D2_GRPCST		AS GrpCST,      D2_FCICOD		AS FCICOD,      D2_ALIQSOL		AS AliquotaSOL,      D2_TNATREC		AS TNatRec,      D2_CNATREC		AS CNatRec,      D2_ESTOQUE		AS Estoque,      D2_ALQCSL		AS AliqCSL,      D2_ALQPIS		AS AliqPIS,      D2_ALQCOF		AS AliqCOF,      D2_BASEDES		AS BaseDes,      D2_ALIQCMP		AS AliqCMP,      D2_DIFAL		AS Difal,      D2_ICMSCOM		AS ICMSCom,      D2_PDDES		AS PDDes,      D2_PDORI		AS PDOrig,      D2_VFCPDIF		AS VFCPDIF,      D2_ALFCCMP		AS AlFCCMP,      D2_BASECPB		AS BaseCPB,      D2_ALIQPRO		AS AliqPro,      D2_MARGEM		AS Margem,      D2_UBCSALD		AS UBCSALD,      D2_UBCCHBX		AS UBCCHBX,      D2_ALFCPST		AS ALFCPST,      D2_ALQFECP		AS ALQFECP,      D2_ICMSDIF		AS ICMSDIF,      D2_VALFECP		AS VALFECP,      D2_VFECPST		AS VFECPST,      D2_VOPDIF		AS VOPDIF,      D_E_L_E_T_		AS Deletado,      R_E_C_N_O_		AS Ukey,      R_E_C_D_E_L_	AS Registro,      S_T_A_M_P_		AS DataUltimaAlteracao,      I_N_S_D_T_		AS DataInsercao  FROM SD2010  WHERE  	S_T_A_M_P_ >= GETDATE()-90\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Filial") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SequenciaItem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CodigoProduto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegundaUnidade") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("UnidadeMedida") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Quantidade")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrecoVenda") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("PrecoTotal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorIPI") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ValorICMS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodigoTes")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CFOP") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Desconto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("IPI") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ValorCSSL")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AliquotaICM") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Peso") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("NumeroPedido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ItemPV") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Cliente") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Loja") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Armazem") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("NotaFiscal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Serie") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Grupo") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("TipoProduto") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DataEmissao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CustoProduto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Custo2")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Custo3") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Custo4") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Custo5") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrecoUnitario")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("QuantidadeSegundaUnidade")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("NumeroSequencial") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Estado") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DescontoValor") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Tipo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("NotaFiscalOrigem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SerieOrigem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("QuantidadeDevolucao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ValorDevolucao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("OrigemLancamento") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BaseICMS")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BaseICM") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorAcrescimo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ICMSRetido") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Comissao1") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Comissao2")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Comissao3") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Comissao4") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Comissao5") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("NumeroLote") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Lote")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataValidade") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ClasseFiscal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("BaseImposto5") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("BaseImposto6") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorImposto5") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorImposto6") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AliquotaImposto5") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AliquotaImposto6") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CentroCusto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Endereco") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ValorImportacao")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataFabricacao") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("AliquotaINSS") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AbatimentoINSS") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PrecoEmbarque") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("AliquotaISS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("BaseIPI") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("BaseISS")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ValorISS") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Seguro") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorFrete") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("TipoDocumentoEnv") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Despesa")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("OK") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CLVL") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("BaseINSS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ICMFrete")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Servico") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("STServ") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ValorINSS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TipoDoc")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TipoRem") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ValorBruto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DataDigit") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SitTrib")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("GrpCST") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("FCICOD") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AliquotaSOL") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TNatRec")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CNatRec") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Estoque") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AliqCSL") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AliqPIS")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AliqCOF") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("BaseDes") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AliqCMP") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Difal")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ICMSCom") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("PDDes") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PDOrig") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VFCPDIF")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AlFCCMP") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("BaseCPB") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("AliqPro") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Margem")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("UBCSALD") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("UBCCHBX") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ALFCPST") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ALQFECP")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ICMSDIF") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("VALFECP") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VFECPST") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("VOPDIF")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Deletado") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Ukey") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Registro") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DataUltimaAlteracao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("DataInsercao") + "}]");
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
					talendJobLog.addCM("tDBInput_1", "Protheus_Producao", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:wYz4ApDQbclbeOVvRbNnAPUEKRD19BfOwBmDnw44PbXR6Qmjybs8Jub7");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String port_tDBInput_1 = "1433";
				String dbname_tDBInput_1 = "TOTVS";
				String url_tDBInput_1 = "jdbc:sqlserver://" + "192.168.4.66";
				if (!"".equals(port_tDBInput_1)) {
					url_tDBInput_1 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBInput_1)) {
					url_tDBInput_1 += ";databaseName=" + "TOTVS";
				}
				url_tDBInput_1 += ";appName=" + projectName + ";" + "";
				String dbschema_tDBInput_1 = "";

				log.debug("tDBInput_1 - Driver ClassName: " + driverClass_tDBInput_1 + ".");

				log.debug("tDBInput_1 - Connection attempt to '" + url_tDBInput_1 + "' with the username '"
						+ dbUser_tDBInput_1 + "'.");

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);
				log.debug("tDBInput_1 - Connection to '" + url_tDBInput_1 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "SELECT \n    D2_FILIAL		AS Filial,\n    D2_ITEM			AS SequenciaItem,\n    D2_COD			AS CodigoProduto,\n    D2_SEGUM		AS S"
						+ "egundaUnidade,\n    D2_UM			AS UnidadeMedida,\n    D2_QUANT		AS Quantidade,\n    D2_PRCVEN		AS PrecoVenda,\n    D2_TOTAL"
						+ "		AS PrecoTotal,\n    D2_VALIPI		AS ValorIPI,\n    D2_VALICM		AS ValorICMS,\n    D2_TES			AS CodigoTes,\n    D2_CF			AS "
						+ "CFOP,\n    D2_DESC			AS Desconto,\n    D2_IPI			AS IPI,\n    D2_VALCSL		AS ValorCSSL,\n    D2_PICM			AS AliquotaICM,\n  "
						+ "  D2_PESO			AS Peso,\n    D2_PEDIDO		AS NumeroPedido,\n    D2_ITEMPV		AS ItemPV,\n    D2_CLIENTE		AS Cliente,\n    D2_LO"
						+ "JA			AS Loja,\n    D2_LOCAL		AS Armazem,\n    D2_DOC			AS NotaFiscal,\n    D2_SERIE		AS Serie,\n    D2_GRUPO		AS Grupo,"
						+ "\n    D2_TP			AS TipoProduto,\n    CAST(D2_EMISSAO AS DATE)		AS DataEmissao,\n    D2_CUSTO1		AS CustoProduto,\n    D2_CUS"
						+ "TO2		AS Custo2,\n    D2_CUSTO3		AS Custo3,\n    D2_CUSTO4		AS Custo4,\n    D2_CUSTO5		AS Custo5,\n    D2_PRUNIT		AS Prec"
						+ "oUnitario,\n    D2_QTSEGUM		AS QuantidadeSegundaUnidade,\n    D2_NUMSEQ		AS NumeroSequencial,\n    D2_EST			AS Estado,\n"
						+ "    D2_DESCON		AS DescontoValor,\n    D2_TIPO			AS Tipo,\n    D2_NFORI		AS NotaFiscalOrigem,\n    D2_SERIORI		AS SerieOr"
						+ "igem,\n    D2_QTDEDEV		AS QuantidadeDevolucao,\n    D2_VALDEV		AS ValorDevolucao,\n    D2_ORIGLAN		AS OrigemLancamento,"
						+ "\n    D2_BRICMS		AS BaseICMS,\n    D2_BASEICM		AS BaseICM,\n    D2_VALACRS		AS ValorAcrescimo,\n    D2_ICMSRET		AS ICMSRe"
						+ "tido,\n    D2_COMIS1		AS Comissao1,\n    D2_COMIS2		AS Comissao2,\n    D2_COMIS3		AS Comissao3,\n    D2_COMIS4		AS Comis"
						+ "sao4,\n    D2_COMIS5		AS Comissao5,\n    D2_LOTECTL		AS NumeroLote,\n    D2_NUMLOTE		AS Lote,\n    CAST(D2_DTVALID	AS DA"
						+ "TE)	AS DataValidade,\n    D2_CLASFIS		AS ClasseFiscal,\n    D2_BASIMP5		AS BaseImposto5,\n    D2_BASIMP6		AS BaseImposto"
						+ "6,\n    D2_VALIMP5		AS ValorImposto5,\n    D2_VALIMP6		AS ValorImposto6,\n    D2_ALQIMP5		AS AliquotaImposto5,\n    D2_A"
						+ "LQIMP6		AS AliquotaImposto6,\n    D2_CCUSTO		AS CentroCusto,\n    D2_LOCALIZ		AS Endereco,\n    D2_VLIMPOR		AS ValorImpo"
						+ "rtacao,\n    CAST(D2_DFABRIC AS DATE)		AS DataFabricacao,\n    D2_ALIQINS		AS AliquotaINSS,\n    D2_ABSCINS		AS Abatimen"
						+ "toINSS,\n    D2_PREEMB		AS PrecoEmbarque,\n    D2_ALIQISS		AS AliquotaISS,\n    D2_BASEIPI		AS BaseIPI,\n    D2_BASEISS	"
						+ "	AS BaseISS,\n    D2_VALISS		AS ValorISS,\n    D2_SEGURO		AS Seguro,\n    D2_VALFRE		AS ValorFrete,\n    D2_TPDCENV		AS "
						+ "TipoDocumentoEnv,\n    D2_DESPESA		AS Despesa,\n    D2_OK			AS OK,\n    D2_CLVL			AS CLVL,\n    D2_BASEINS		AS BaseINSS,"
						+ "\n    D2_ICMFRET		AS ICMFrete,\n    D2_SERVIC		AS Servico,\n    D2_STSERV		AS STServ,\n    D2_VALINS		AS ValorINSS,\n   "
						+ " D2_TIPODOC		AS TipoDoc,\n    D2_TIPOREM		AS TipoRem,\n    D2_VALBRUT		AS ValorBruto,\n    CAST(D2_DTDIGIT	AS DATE)	AS D"
						+ "ataDigit,\n    D2_SITTRIB		AS SitTrib,\n    D2_GRPCST		AS GrpCST,\n    D2_FCICOD		AS FCICOD,\n    D2_ALIQSOL		AS Aliquot"
						+ "aSOL,\n    D2_TNATREC		AS TNatRec,\n    D2_CNATREC		AS CNatRec,\n    D2_ESTOQUE		AS Estoque,\n    D2_ALQCSL		AS AliqCSL,"
						+ "\n    D2_ALQPIS		AS AliqPIS,\n    D2_ALQCOF		AS AliqCOF,\n    D2_BASEDES		AS BaseDes,\n    D2_ALIQCMP		AS AliqCMP,\n    "
						+ "D2_DIFAL		AS Difal,\n    D2_ICMSCOM		AS ICMSCom,\n    D2_PDDES		AS PDDes,\n    D2_PDORI		AS PDOrig,\n    D2_VFCPDIF		AS "
						+ "VFCPDIF,\n    D2_ALFCCMP		AS AlFCCMP,\n    D2_BASECPB		AS BaseCPB,\n    D2_ALIQPRO		AS AliqPro,\n    D2_MARGEM		AS Marge"
						+ "m,\n    D2_UBCSALD		AS UBCSALD,\n    D2_UBCCHBX		AS UBCCHBX,\n    D2_ALFCPST		AS ALFCPST,\n    D2_ALQFECP		AS ALQFECP,\n"
						+ "    D2_ICMSDIF		AS ICMSDIF,\n    D2_VALFECP		AS VALFECP,\n    D2_VFECPST		AS VFECPST,\n    D2_VOPDIF		AS VOPDIF,\n    D_"
						+ "E_L_E_T_		AS Deletado,\n    R_E_C_N_O_		AS Ukey,\n    R_E_C_D_E_L_	AS Registro,\n    S_T_A_M_P_		AS DataUltimaAlteracao,"
						+ "\n    I_N_S_D_T_		AS DataInsercao\nFROM SD2010\nWHERE\n	S_T_A_M_P_ >= GETDATE()-90";

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
							row1.Filial = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(1);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(1).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Filial = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Filial = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Filial = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							row1.SequenciaItem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SequenciaItem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SequenciaItem = tmpContent_tDBInput_1.trim();
								}
							} else {
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
							row1.SegundaUnidade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegundaUnidade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegundaUnidade = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegundaUnidade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.UnidadeMedida = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UnidadeMedida = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UnidadeMedida = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UnidadeMedida = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.Quantidade = null;
						} else {

							row1.Quantidade = rs_tDBInput_1.getDouble(6);
							if (rs_tDBInput_1.wasNull()) {
								row1.Quantidade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.PrecoVenda = null;
						} else {

							row1.PrecoVenda = rs_tDBInput_1.getDouble(7);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoVenda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.PrecoTotal = null;
						} else {

							row1.PrecoTotal = rs_tDBInput_1.getDouble(8);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoTotal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.ValorIPI = null;
						} else {

							row1.ValorIPI = rs_tDBInput_1.getDouble(9);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorIPI = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.ValorICMS = null;
						} else {

							row1.ValorICMS = rs_tDBInput_1.getDouble(10);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorICMS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.CodigoTes = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(11);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(11).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoTes = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoTes = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoTes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.CFOP = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CFOP = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CFOP = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CFOP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.Desconto = null;
						} else {

							row1.Desconto = rs_tDBInput_1.getDouble(13);
							if (rs_tDBInput_1.wasNull()) {
								row1.Desconto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.IPI = null;
						} else {

							row1.IPI = rs_tDBInput_1.getDouble(14);
							if (rs_tDBInput_1.wasNull()) {
								row1.IPI = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.ValorCSSL = null;
						} else {

							row1.ValorCSSL = rs_tDBInput_1.getDouble(15);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorCSSL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.AliquotaICM = null;
						} else {

							row1.AliquotaICM = rs_tDBInput_1.getDouble(16);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaICM = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.Peso = null;
						} else {

							row1.Peso = rs_tDBInput_1.getDouble(17);
							if (rs_tDBInput_1.wasNull()) {
								row1.Peso = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.NumeroPedido = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NumeroPedido = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NumeroPedido = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NumeroPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.ItemPV = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(19);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ItemPV = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ItemPV = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ItemPV = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.Cliente = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Cliente = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Cliente = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Cliente = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.Loja = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Loja = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Loja = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Loja = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.Armazem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Armazem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Armazem = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Armazem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.NotaFiscal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NotaFiscal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NotaFiscal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NotaFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.Serie = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Serie = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Serie = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Serie = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.Grupo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(25);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(25).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Grupo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Grupo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Grupo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.TipoProduto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(26);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(26).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoProduto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoProduto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.DataEmissao = null;
						} else {

							row1.DataEmissao = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 27);

						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.CustoProduto = null;
						} else {

							row1.CustoProduto = rs_tDBInput_1.getDouble(28);
							if (rs_tDBInput_1.wasNull()) {
								row1.CustoProduto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.Custo2 = null;
						} else {

							row1.Custo2 = rs_tDBInput_1.getDouble(29);
							if (rs_tDBInput_1.wasNull()) {
								row1.Custo2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.Custo3 = null;
						} else {

							row1.Custo3 = rs_tDBInput_1.getDouble(30);
							if (rs_tDBInput_1.wasNull()) {
								row1.Custo3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							row1.Custo4 = null;
						} else {

							row1.Custo4 = rs_tDBInput_1.getDouble(31);
							if (rs_tDBInput_1.wasNull()) {
								row1.Custo4 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							row1.Custo5 = null;
						} else {

							row1.Custo5 = rs_tDBInput_1.getDouble(32);
							if (rs_tDBInput_1.wasNull()) {
								row1.Custo5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							row1.PrecoUnitario = null;
						} else {

							row1.PrecoUnitario = rs_tDBInput_1.getDouble(33);
							if (rs_tDBInput_1.wasNull()) {
								row1.PrecoUnitario = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							row1.QuantidadeSegundaUnidade = null;
						} else {

							row1.QuantidadeSegundaUnidade = rs_tDBInput_1.getDouble(34);
							if (rs_tDBInput_1.wasNull()) {
								row1.QuantidadeSegundaUnidade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							row1.NumeroSequencial = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(35);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NumeroSequencial = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NumeroSequencial = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NumeroSequencial = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							row1.Estado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(36);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(36).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Estado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Estado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Estado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							row1.DescontoValor = null;
						} else {

							row1.DescontoValor = rs_tDBInput_1.getDouble(37);
							if (rs_tDBInput_1.wasNull()) {
								row1.DescontoValor = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							row1.Tipo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Tipo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Tipo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Tipo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 39) {
							row1.NotaFiscalOrigem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(39);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(39).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NotaFiscalOrigem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NotaFiscalOrigem = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NotaFiscalOrigem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 40) {
							row1.SerieOrigem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(40);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(40).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SerieOrigem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SerieOrigem = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SerieOrigem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							row1.QuantidadeDevolucao = null;
						} else {

							row1.QuantidadeDevolucao = rs_tDBInput_1.getDouble(41);
							if (rs_tDBInput_1.wasNull()) {
								row1.QuantidadeDevolucao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 42) {
							row1.ValorDevolucao = null;
						} else {

							row1.ValorDevolucao = rs_tDBInput_1.getDouble(42);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorDevolucao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 43) {
							row1.OrigemLancamento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(43);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(43).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.OrigemLancamento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.OrigemLancamento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.OrigemLancamento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 44) {
							row1.BaseICMS = null;
						} else {

							row1.BaseICMS = rs_tDBInput_1.getDouble(44);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseICMS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 45) {
							row1.BaseICM = null;
						} else {

							row1.BaseICM = rs_tDBInput_1.getDouble(45);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseICM = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 46) {
							row1.ValorAcrescimo = null;
						} else {

							row1.ValorAcrescimo = rs_tDBInput_1.getDouble(46);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorAcrescimo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 47) {
							row1.ICMSRetido = null;
						} else {

							row1.ICMSRetido = rs_tDBInput_1.getDouble(47);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMSRetido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 48) {
							row1.Comissao1 = null;
						} else {

							row1.Comissao1 = rs_tDBInput_1.getDouble(48);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 49) {
							row1.Comissao2 = null;
						} else {

							row1.Comissao2 = rs_tDBInput_1.getDouble(49);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 50) {
							row1.Comissao3 = null;
						} else {

							row1.Comissao3 = rs_tDBInput_1.getDouble(50);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 51) {
							row1.Comissao4 = null;
						} else {

							row1.Comissao4 = rs_tDBInput_1.getDouble(51);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao4 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 52) {
							row1.Comissao5 = null;
						} else {

							row1.Comissao5 = rs_tDBInput_1.getDouble(52);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 53) {
							row1.NumeroLote = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(53);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NumeroLote = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NumeroLote = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NumeroLote = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 54) {
							row1.Lote = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(54);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(54).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Lote = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Lote = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Lote = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 55) {
							row1.DataValidade = null;
						} else {

							row1.DataValidade = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 55);

						}
						if (colQtyInRs_tDBInput_1 < 56) {
							row1.ClasseFiscal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(56);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(56).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ClasseFiscal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ClasseFiscal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ClasseFiscal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 57) {
							row1.BaseImposto5 = null;
						} else {

							row1.BaseImposto5 = rs_tDBInput_1.getDouble(57);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseImposto5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 58) {
							row1.BaseImposto6 = null;
						} else {

							row1.BaseImposto6 = rs_tDBInput_1.getDouble(58);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseImposto6 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 59) {
							row1.ValorImposto5 = null;
						} else {

							row1.ValorImposto5 = rs_tDBInput_1.getDouble(59);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorImposto5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 60) {
							row1.ValorImposto6 = null;
						} else {

							row1.ValorImposto6 = rs_tDBInput_1.getDouble(60);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorImposto6 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 61) {
							row1.AliquotaImposto5 = null;
						} else {

							row1.AliquotaImposto5 = rs_tDBInput_1.getDouble(61);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaImposto5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 62) {
							row1.AliquotaImposto6 = null;
						} else {

							row1.AliquotaImposto6 = rs_tDBInput_1.getDouble(62);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaImposto6 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 63) {
							row1.CentroCusto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(63);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(63).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CentroCusto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CentroCusto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CentroCusto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 64) {
							row1.Endereco = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(64);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(64).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Endereco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Endereco = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Endereco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 65) {
							row1.ValorImportacao = null;
						} else {

							row1.ValorImportacao = rs_tDBInput_1.getDouble(65);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorImportacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 66) {
							row1.DataFabricacao = null;
						} else {

							row1.DataFabricacao = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 66);

						}
						if (colQtyInRs_tDBInput_1 < 67) {
							row1.AliquotaINSS = null;
						} else {

							row1.AliquotaINSS = rs_tDBInput_1.getDouble(67);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaINSS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 68) {
							row1.AbatimentoINSS = null;
						} else {

							row1.AbatimentoINSS = rs_tDBInput_1.getDouble(68);
							if (rs_tDBInput_1.wasNull()) {
								row1.AbatimentoINSS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 69) {
							row1.PrecoEmbarque = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(69);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(69).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PrecoEmbarque = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PrecoEmbarque = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.PrecoEmbarque = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 70) {
							row1.AliquotaISS = null;
						} else {

							row1.AliquotaISS = rs_tDBInput_1.getDouble(70);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaISS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 71) {
							row1.BaseIPI = null;
						} else {

							row1.BaseIPI = rs_tDBInput_1.getDouble(71);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseIPI = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 72) {
							row1.BaseISS = null;
						} else {

							row1.BaseISS = rs_tDBInput_1.getDouble(72);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseISS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 73) {
							row1.ValorISS = null;
						} else {

							row1.ValorISS = rs_tDBInput_1.getDouble(73);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorISS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 74) {
							row1.Seguro = null;
						} else {

							row1.Seguro = rs_tDBInput_1.getDouble(74);
							if (rs_tDBInput_1.wasNull()) {
								row1.Seguro = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 75) {
							row1.ValorFrete = null;
						} else {

							row1.ValorFrete = rs_tDBInput_1.getDouble(75);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 76) {
							row1.TipoDocumentoEnv = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(76);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(76).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoDocumentoEnv = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoDocumentoEnv = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoDocumentoEnv = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 77) {
							row1.Despesa = null;
						} else {

							row1.Despesa = rs_tDBInput_1.getDouble(77);
							if (rs_tDBInput_1.wasNull()) {
								row1.Despesa = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 78) {
							row1.OK = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(78);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(78).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.OK = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.OK = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.OK = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 79) {
							row1.CLVL = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(79);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(79).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CLVL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CLVL = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CLVL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 80) {
							row1.BaseINSS = null;
						} else {

							row1.BaseINSS = rs_tDBInput_1.getDouble(80);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseINSS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 81) {
							row1.ICMFrete = null;
						} else {

							row1.ICMFrete = rs_tDBInput_1.getDouble(81);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 82) {
							row1.Servico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(82);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(82).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Servico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Servico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Servico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 83) {
							row1.STServ = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(83);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(83).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.STServ = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.STServ = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.STServ = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 84) {
							row1.ValorINSS = null;
						} else {

							row1.ValorINSS = rs_tDBInput_1.getDouble(84);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorINSS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 85) {
							row1.TipoDoc = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(85);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(85).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoDoc = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoDoc = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoDoc = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 86) {
							row1.TipoRem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(86);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(86).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoRem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoRem = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoRem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 87) {
							row1.ValorBruto = null;
						} else {

							row1.ValorBruto = rs_tDBInput_1.getDouble(87);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorBruto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 88) {
							row1.DataDigit = null;
						} else {

							row1.DataDigit = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 88);

						}
						if (colQtyInRs_tDBInput_1 < 89) {
							row1.SitTrib = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(89);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(89).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SitTrib = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SitTrib = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SitTrib = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 90) {
							row1.GrpCST = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(90);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(90).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.GrpCST = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.GrpCST = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.GrpCST = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 91) {
							row1.FCICOD = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(91);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(91).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FCICOD = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FCICOD = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FCICOD = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 92) {
							row1.AliquotaSOL = null;
						} else {

							row1.AliquotaSOL = rs_tDBInput_1.getDouble(92);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaSOL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 93) {
							row1.TNatRec = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(93);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(93).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TNatRec = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TNatRec = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TNatRec = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 94) {
							row1.CNatRec = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(94);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(94).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CNatRec = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CNatRec = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CNatRec = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 95) {
							row1.Estoque = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(95);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(95).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Estoque = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Estoque = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Estoque = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 96) {
							row1.AliqCSL = null;
						} else {

							row1.AliqCSL = rs_tDBInput_1.getDouble(96);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliqCSL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 97) {
							row1.AliqPIS = null;
						} else {

							row1.AliqPIS = rs_tDBInput_1.getDouble(97);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliqPIS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 98) {
							row1.AliqCOF = null;
						} else {

							row1.AliqCOF = rs_tDBInput_1.getDouble(98);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliqCOF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 99) {
							row1.BaseDes = null;
						} else {

							row1.BaseDes = rs_tDBInput_1.getDouble(99);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseDes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 100) {
							row1.AliqCMP = null;
						} else {

							row1.AliqCMP = rs_tDBInput_1.getDouble(100);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliqCMP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 101) {
							row1.Difal = null;
						} else {

							row1.Difal = rs_tDBInput_1.getDouble(101);
							if (rs_tDBInput_1.wasNull()) {
								row1.Difal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 102) {
							row1.ICMSCom = null;
						} else {

							row1.ICMSCom = rs_tDBInput_1.getDouble(102);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMSCom = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 103) {
							row1.PDDes = null;
						} else {

							row1.PDDes = rs_tDBInput_1.getDouble(103);
							if (rs_tDBInput_1.wasNull()) {
								row1.PDDes = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 104) {
							row1.PDOrig = null;
						} else {

							row1.PDOrig = rs_tDBInput_1.getDouble(104);
							if (rs_tDBInput_1.wasNull()) {
								row1.PDOrig = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 105) {
							row1.VFCPDIF = null;
						} else {

							row1.VFCPDIF = rs_tDBInput_1.getDouble(105);
							if (rs_tDBInput_1.wasNull()) {
								row1.VFCPDIF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 106) {
							row1.AlFCCMP = null;
						} else {

							row1.AlFCCMP = rs_tDBInput_1.getDouble(106);
							if (rs_tDBInput_1.wasNull()) {
								row1.AlFCCMP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 107) {
							row1.BaseCPB = null;
						} else {

							row1.BaseCPB = rs_tDBInput_1.getDouble(107);
							if (rs_tDBInput_1.wasNull()) {
								row1.BaseCPB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 108) {
							row1.AliqPro = null;
						} else {

							row1.AliqPro = rs_tDBInput_1.getDouble(108);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliqPro = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 109) {
							row1.Margem = null;
						} else {

							row1.Margem = rs_tDBInput_1.getDouble(109);
							if (rs_tDBInput_1.wasNull()) {
								row1.Margem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 110) {
							row1.UBCSALD = null;
						} else {

							row1.UBCSALD = rs_tDBInput_1.getDouble(110);
							if (rs_tDBInput_1.wasNull()) {
								row1.UBCSALD = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 111) {
							row1.UBCCHBX = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(111);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(111).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UBCCHBX = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UBCCHBX = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UBCCHBX = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 112) {
							row1.ALFCPST = null;
						} else {

							row1.ALFCPST = rs_tDBInput_1.getDouble(112);
							if (rs_tDBInput_1.wasNull()) {
								row1.ALFCPST = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 113) {
							row1.ALQFECP = null;
						} else {

							row1.ALQFECP = rs_tDBInput_1.getDouble(113);
							if (rs_tDBInput_1.wasNull()) {
								row1.ALQFECP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 114) {
							row1.ICMSDIF = null;
						} else {

							row1.ICMSDIF = rs_tDBInput_1.getDouble(114);
							if (rs_tDBInput_1.wasNull()) {
								row1.ICMSDIF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 115) {
							row1.VALFECP = null;
						} else {

							row1.VALFECP = rs_tDBInput_1.getDouble(115);
							if (rs_tDBInput_1.wasNull()) {
								row1.VALFECP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 116) {
							row1.VFECPST = null;
						} else {

							row1.VFECPST = rs_tDBInput_1.getDouble(116);
							if (rs_tDBInput_1.wasNull()) {
								row1.VFECPST = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 117) {
							row1.VOPDIF = null;
						} else {

							row1.VOPDIF = rs_tDBInput_1.getDouble(117);
							if (rs_tDBInput_1.wasNull()) {
								row1.VOPDIF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 118) {
							row1.Deletado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(118);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(118).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Deletado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Deletado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Deletado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 119) {
							row1.Ukey = null;
						} else {

							row1.Ukey = rs_tDBInput_1.getInt(119);
							if (rs_tDBInput_1.wasNull()) {
								row1.Ukey = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 120) {
							row1.Registro = null;
						} else {

							row1.Registro = rs_tDBInput_1.getInt(120);
							if (rs_tDBInput_1.wasNull()) {
								row1.Registro = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 121) {
							row1.DataUltimaAlteracao = null;
						} else {

							row1.DataUltimaAlteracao = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 121);

						}
						if (colQtyInRs_tDBInput_1 < 122) {
							row1.DataInsercao = null;
						} else {

							row1.DataInsercao = mssqlGTU_tDBInput_1.getDate(rsmd_tDBInput_1, rs_tDBInput_1, 122);

						}

						log.debug("tDBInput_1 - Retrieving the record " + nb_line_tDBInput_1 + ".");

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Protheus_Producao";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Protheus_Producao";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tReplicate_1 main ] start
						 */

						currentComponent = "tReplicate_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row1", "tDBInput_1", "Protheus_Producao", "tMSSqlInput", "tReplicate_1",
								"tReplicate_1", "tReplicate"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
						}

						row4 = new row4Struct();

						row4.Filial = row1.Filial;
						row4.SequenciaItem = row1.SequenciaItem;
						row4.CodigoProduto = row1.CodigoProduto;
						row4.SegundaUnidade = row1.SegundaUnidade;
						row4.UnidadeMedida = row1.UnidadeMedida;
						row4.Quantidade = row1.Quantidade;
						row4.PrecoVenda = row1.PrecoVenda;
						row4.PrecoTotal = row1.PrecoTotal;
						row4.ValorIPI = row1.ValorIPI;
						row4.ValorICMS = row1.ValorICMS;
						row4.CodigoTes = row1.CodigoTes;
						row4.CFOP = row1.CFOP;
						row4.Desconto = row1.Desconto;
						row4.IPI = row1.IPI;
						row4.ValorCSSL = row1.ValorCSSL;
						row4.AliquotaICM = row1.AliquotaICM;
						row4.Peso = row1.Peso;
						row4.NumeroPedido = row1.NumeroPedido;
						row4.ItemPV = row1.ItemPV;
						row4.Cliente = row1.Cliente;
						row4.Loja = row1.Loja;
						row4.Armazem = row1.Armazem;
						row4.NotaFiscal = row1.NotaFiscal;
						row4.Serie = row1.Serie;
						row4.Grupo = row1.Grupo;
						row4.TipoProduto = row1.TipoProduto;
						row4.DataEmissao = row1.DataEmissao;
						row4.CustoProduto = row1.CustoProduto;
						row4.Custo2 = row1.Custo2;
						row4.Custo3 = row1.Custo3;
						row4.Custo4 = row1.Custo4;
						row4.Custo5 = row1.Custo5;
						row4.PrecoUnitario = row1.PrecoUnitario;
						row4.QuantidadeSegundaUnidade = row1.QuantidadeSegundaUnidade;
						row4.NumeroSequencial = row1.NumeroSequencial;
						row4.Estado = row1.Estado;
						row4.DescontoValor = row1.DescontoValor;
						row4.Tipo = row1.Tipo;
						row4.NotaFiscalOrigem = row1.NotaFiscalOrigem;
						row4.SerieOrigem = row1.SerieOrigem;
						row4.QuantidadeDevolucao = row1.QuantidadeDevolucao;
						row4.ValorDevolucao = row1.ValorDevolucao;
						row4.OrigemLancamento = row1.OrigemLancamento;
						row4.BaseICMS = row1.BaseICMS;
						row4.BaseICM = row1.BaseICM;
						row4.ValorAcrescimo = row1.ValorAcrescimo;
						row4.ICMSRetido = row1.ICMSRetido;
						row4.Comissao1 = row1.Comissao1;
						row4.Comissao2 = row1.Comissao2;
						row4.Comissao3 = row1.Comissao3;
						row4.Comissao4 = row1.Comissao4;
						row4.Comissao5 = row1.Comissao5;
						row4.NumeroLote = row1.NumeroLote;
						row4.Lote = row1.Lote;
						row4.DataValidade = row1.DataValidade;
						row4.ClasseFiscal = row1.ClasseFiscal;
						row4.BaseImposto5 = row1.BaseImposto5;
						row4.BaseImposto6 = row1.BaseImposto6;
						row4.ValorImposto5 = row1.ValorImposto5;
						row4.ValorImposto6 = row1.ValorImposto6;
						row4.AliquotaImposto5 = row1.AliquotaImposto5;
						row4.AliquotaImposto6 = row1.AliquotaImposto6;
						row4.CentroCusto = row1.CentroCusto;
						row4.Endereco = row1.Endereco;
						row4.ValorImportacao = row1.ValorImportacao;
						row4.DataFabricacao = row1.DataFabricacao;
						row4.AliquotaINSS = row1.AliquotaINSS;
						row4.AbatimentoINSS = row1.AbatimentoINSS;
						row4.PrecoEmbarque = row1.PrecoEmbarque;
						row4.AliquotaISS = row1.AliquotaISS;
						row4.BaseIPI = row1.BaseIPI;
						row4.BaseISS = row1.BaseISS;
						row4.ValorISS = row1.ValorISS;
						row4.Seguro = row1.Seguro;
						row4.ValorFrete = row1.ValorFrete;
						row4.TipoDocumentoEnv = row1.TipoDocumentoEnv;
						row4.Despesa = row1.Despesa;
						row4.OK = row1.OK;
						row4.CLVL = row1.CLVL;
						row4.BaseINSS = row1.BaseINSS;
						row4.ICMFrete = row1.ICMFrete;
						row4.Servico = row1.Servico;
						row4.STServ = row1.STServ;
						row4.ValorINSS = row1.ValorINSS;
						row4.TipoDoc = row1.TipoDoc;
						row4.TipoRem = row1.TipoRem;
						row4.ValorBruto = row1.ValorBruto;
						row4.DataDigit = row1.DataDigit;
						row4.SitTrib = row1.SitTrib;
						row4.GrpCST = row1.GrpCST;
						row4.FCICOD = row1.FCICOD;
						row4.AliquotaSOL = row1.AliquotaSOL;
						row4.TNatRec = row1.TNatRec;
						row4.CNatRec = row1.CNatRec;
						row4.Estoque = row1.Estoque;
						row4.AliqCSL = row1.AliqCSL;
						row4.AliqPIS = row1.AliqPIS;
						row4.AliqCOF = row1.AliqCOF;
						row4.BaseDes = row1.BaseDes;
						row4.AliqCMP = row1.AliqCMP;
						row4.Difal = row1.Difal;
						row4.ICMSCom = row1.ICMSCom;
						row4.PDDes = row1.PDDes;
						row4.PDOrig = row1.PDOrig;
						row4.VFCPDIF = row1.VFCPDIF;
						row4.AlFCCMP = row1.AlFCCMP;
						row4.BaseCPB = row1.BaseCPB;
						row4.AliqPro = row1.AliqPro;
						row4.Margem = row1.Margem;
						row4.UBCSALD = row1.UBCSALD;
						row4.UBCCHBX = row1.UBCCHBX;
						row4.ALFCPST = row1.ALFCPST;
						row4.ALQFECP = row1.ALQFECP;
						row4.ICMSDIF = row1.ICMSDIF;
						row4.VALFECP = row1.VALFECP;
						row4.VFECPST = row1.VFECPST;
						row4.VOPDIF = row1.VOPDIF;
						row4.Deletado = row1.Deletado;
						row4.Ukey = row1.Ukey;
						row4.Registro = row1.Registro;
						row4.DataUltimaAlteracao = row1.DataUltimaAlteracao;
						row4.DataInsercao = row1.DataInsercao;

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

						cLabel = "Protheus_Producao";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "Protheus_Producao";

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
						"tDBInput_1", "Protheus_Producao", "tMSSqlInput", "tReplicate_1", "tReplicate_1", "tReplicate",
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

				cLabel = "Protheus_Producao";

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

	public void tDBOutput_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBOutput_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBOutput_3");
		org.slf4j.MDC.put("_subJobPid", "2knQ4m_" + subJobPidCounter.getAndIncrement());

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
				 * [tDBOutput_3 begin ] start
				 */

				ok_Hash.put("tDBOutput_3", false);
				start_Hash.put("tDBOutput_3", System.currentTimeMillis());

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				int tos_count_tDBOutput_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBOutput_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBOutput_3 = new StringBuilder();
							log4jParamters_tDBOutput_3.append("Parameters:");
							log4jParamters_tDBOutput_3.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("HOST" + " = " + "\"192.168.4.128\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("PORT" + " = " + "\"1433\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("DB_SCHEMA" + " = " + "\"\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("DBNAME" + " = " + "\"BRONZE\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("USER" + " = " + "\"sa\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("PASS" + " = " + String.valueOf(
									"enc:routine.encryption.key.v1:yjFiCXEhMjgYf5QpQXYSNQF6p6T9B+w2Zxsi1S7NlJnW2ogTdZh2GvQ=")
									.substring(0, 4) + "...");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("TABLE" + " = " + "\"NotaSaida_RAW\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("IDENTITY_INSERT" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("DATA_ACTION" + " = " + "INSERT_OR_UPDATE");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("SPECIFY_IDENTITY_FIELD" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("PROPERTIES" + " = " + "\"\"");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("COMMIT_EVERY" + " = " + "10000");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("ADD_COLS" + " = " + "[]");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("USE_FIELD_OPTIONS" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("IGNORE_DATE_OUTOF_RANGE" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("ENABLE_DEBUG_MODE" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("SUPPORT_NULL_WHERE" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBOutput_3.append(" | ");
							log4jParamters_tDBOutput_3.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlOutput");
							log4jParamters_tDBOutput_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBOutput_3 - " + (log4jParamters_tDBOutput_3));
						}
					}
					new BytesLimit65535_tDBOutput_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBOutput_3", "Datalake_Bronze", "tMSSqlOutput");
					talendJobLogProcess(globalMap);
				}

				int nb_line_tDBOutput_3 = 0;
				int nb_line_update_tDBOutput_3 = 0;
				int nb_line_inserted_tDBOutput_3 = 0;
				int nb_line_deleted_tDBOutput_3 = 0;
				int nb_line_rejected_tDBOutput_3 = 0;

				int deletedCount_tDBOutput_3 = 0;
				int updatedCount_tDBOutput_3 = 0;
				int insertedCount_tDBOutput_3 = 0;
				int rowsToCommitCount_tDBOutput_3 = 0;
				int rejectedCount_tDBOutput_3 = 0;
				String dbschema_tDBOutput_3 = null;
				String tableName_tDBOutput_3 = null;
				boolean whetherReject_tDBOutput_3 = false;

				java.util.Calendar calendar_tDBOutput_3 = java.util.Calendar.getInstance();
				long year1_tDBOutput_3 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_3 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_3 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_3;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_3 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				int updateKeyCount_tDBOutput_3 = 4;
				if (updateKeyCount_tDBOutput_3 < 1) {
					throw new RuntimeException("For update, Schema must have a key");
				} else if (updateKeyCount_tDBOutput_3 == 122 && true) {
					log.warn("For update, every Schema column can not be a key");
				}

				java.sql.Connection conn_tDBOutput_3 = null;
				String dbUser_tDBOutput_3 = null;
				dbschema_tDBOutput_3 = "";
				String driverClass_tDBOutput_3 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Driver ClassName: ") + (driverClass_tDBOutput_3) + ("."));
				java.lang.Class.forName(driverClass_tDBOutput_3);
				String port_tDBOutput_3 = "1433";
				String dbname_tDBOutput_3 = "BRONZE";
				String url_tDBOutput_3 = "jdbc:sqlserver://" + "192.168.4.128";
				if (!"".equals(port_tDBOutput_3)) {
					url_tDBOutput_3 += ":" + "1433";
				}
				if (!"".equals(dbname_tDBOutput_3)) {
					url_tDBOutput_3 += ";databaseName=" + "BRONZE";

				}
				url_tDBOutput_3 += ";appName=" + projectName + ";" + "";
				dbUser_tDBOutput_3 = "sa";

				final String decryptedPassword_tDBOutput_3 = routines.system.PasswordEncryptUtil.decryptPassword(
						"enc:routine.encryption.key.v1:zNe3wciOEiBEOllIy+uHZnKZb1M6yi4+NbYJ6j6NTCAQSmo++B4rD+0=");

				String dbPwd_tDBOutput_3 = decryptedPassword_tDBOutput_3;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Connection attempts to '") + (url_tDBOutput_3)
							+ ("' with the username '") + (dbUser_tDBOutput_3) + ("'."));
				conn_tDBOutput_3 = java.sql.DriverManager.getConnection(url_tDBOutput_3, dbUser_tDBOutput_3,
						dbPwd_tDBOutput_3);
				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Connection to '") + (url_tDBOutput_3) + ("' has succeeded."));

				resourceMap.put("conn_tDBOutput_3", conn_tDBOutput_3);

				conn_tDBOutput_3.setAutoCommit(false);
				int commitEvery_tDBOutput_3 = 10000;
				int commitCounter_tDBOutput_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Connection is set auto commit to '")
							+ (conn_tDBOutput_3.getAutoCommit()) + ("'."));

				if (dbschema_tDBOutput_3 == null || dbschema_tDBOutput_3.trim().length() == 0) {
					tableName_tDBOutput_3 = "NotaSaida_RAW";
				} else {
					tableName_tDBOutput_3 = dbschema_tDBOutput_3 + "].[" + "NotaSaida_RAW";
				}
				int count_tDBOutput_3 = 0;

				boolean whetherExist_tDBOutput_3 = false;
				try (java.sql.Statement isExistStmt_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					try {

						isExistStmt_tDBOutput_3.execute("SELECT TOP 1 1 FROM [" + tableName_tDBOutput_3 + "]");
						whetherExist_tDBOutput_3 = true;
					} catch (java.lang.Exception e) {
						globalMap.put("tDBOutput_3_ERROR_MESSAGE", e.getMessage());
						whetherExist_tDBOutput_3 = false;
					}
				}
				if (whetherExist_tDBOutput_3) {
					try (java.sql.Statement stmtDrop_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
						if (log.isDebugEnabled())
							log.debug("tDBOutput_3 - " + ("Dropping") + (" table '")
									+ ("[" + tableName_tDBOutput_3 + "]") + ("'."));
						stmtDrop_tDBOutput_3.execute("DROP TABLE [" + tableName_tDBOutput_3 + "]");
						if (log.isDebugEnabled())
							log.debug("tDBOutput_3 - " + ("Drop") + (" table '") + ("[" + tableName_tDBOutput_3 + "]")
									+ ("' has succeeded."));
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_3 = conn_tDBOutput_3.createStatement()) {
					if (log.isDebugEnabled())
						log.debug("tDBOutput_3 - " + ("Creating") + (" table '") + ("[" + tableName_tDBOutput_3 + "]")
								+ ("'."));
					stmtCreate_tDBOutput_3.execute("CREATE TABLE [" + tableName_tDBOutput_3
							+ "]([Filial] VARCHAR(2)  ,[SequenciaItem] VARCHAR(2)  ,[CodigoProduto] VARCHAR(15)  ,[SegundaUnidade] VARCHAR(6)  ,[UnidadeMedida] VARCHAR(6)  ,[Quantidade] FLOAT(15)  ,[PrecoVenda] FLOAT(15)  ,[PrecoTotal] FLOAT(15)  ,[ValorIPI] FLOAT(15)  ,[ValorICMS] FLOAT(15)  ,[CodigoTes] VARCHAR(3)  ,[CFOP] VARCHAR(5)  ,[Desconto] FLOAT(15)  ,[IPI] FLOAT(15)  ,[ValorCSSL] FLOAT(15)  ,[AliquotaICM] FLOAT(15)  ,[Peso] FLOAT(15)  ,[NumeroPedido] VARCHAR(6)  ,[ItemPV] VARCHAR(2)  ,[Cliente] VARCHAR(9)  ,[Loja] VARCHAR(3)  ,[Armazem] VARCHAR(2)  ,[NotaFiscal] VARCHAR(9)  ,[Serie] VARCHAR(3)  ,[Grupo] VARCHAR(4)  ,[TipoProduto] VARCHAR(2)  ,[DataEmissao] DATE ,[CustoProduto] FLOAT(15)  ,[Custo2] FLOAT(15)  ,[Custo3] FLOAT(15)  ,[Custo4] FLOAT(15)  ,[Custo5] FLOAT(15)  ,[PrecoUnitario] FLOAT(15)  ,[QuantidadeSegundaUnidade] FLOAT(15)  ,[NumeroSequencial] VARCHAR(6)  ,[Estado] VARCHAR(2)  ,[DescontoValor] FLOAT(15)  ,[Tipo] VARCHAR(1)  ,[NotaFiscalOrigem] VARCHAR(9)  ,[SerieOrigem] VARCHAR(3)  ,[QuantidadeDevolucao] FLOAT(15)  ,[ValorDevolucao] FLOAT(15)  ,[OrigemLancamento] VARCHAR(2)  ,[BaseICMS] FLOAT(15)  ,[BaseICM] FLOAT(15)  ,[ValorAcrescimo] FLOAT(15)  ,[ICMSRetido] FLOAT(15)  ,[Comissao1] FLOAT(15)  ,[Comissao2] FLOAT(15)  ,[Comissao3] FLOAT(15)  ,[Comissao4] FLOAT(15)  ,[Comissao5] FLOAT(15)  ,[NumeroLote] VARCHAR(10)  ,[Lote] VARCHAR(6)  ,[DataValidade] DATE ,[ClasseFiscal] VARCHAR(3)  ,[BaseImposto5] FLOAT(15)  ,[BaseImposto6] FLOAT(15)  ,[ValorImposto5] FLOAT(15)  ,[ValorImposto6] FLOAT(15)  ,[AliquotaImposto5] FLOAT(15)  ,[AliquotaImposto6] FLOAT(15)  ,[CentroCusto] VARCHAR(9)  ,[Endereco] VARCHAR(15)  ,[ValorImportacao] FLOAT(15)  ,[DataFabricacao] DATE ,[AliquotaINSS] FLOAT(15)  ,[AbatimentoINSS] FLOAT(15)  ,[PrecoEmbarque] VARCHAR(20)  ,[AliquotaISS] FLOAT(15)  ,[BaseIPI] FLOAT(15)  ,[BaseISS] FLOAT(15)  ,[ValorISS] FLOAT(15)  ,[Seguro] FLOAT(15)  ,[ValorFrete] FLOAT(15)  ,[TipoDocumentoEnv] VARCHAR(1)  ,[Despesa] FLOAT(15)  ,[OK] VARCHAR(2)  ,[CLVL] VARCHAR(9)  ,[BaseINSS] FLOAT(15)  ,[ICMFrete] FLOAT(15)  ,[Servico] VARCHAR(3)  ,[STServ] VARCHAR(1)  ,[ValorINSS] FLOAT(15)  ,[TipoDoc] VARCHAR(2)  ,[TipoRem] VARCHAR(1)  ,[ValorBruto] FLOAT(15)  ,[DataDigit] DATE ,[SitTrib] VARCHAR(5)  ,[GrpCST] VARCHAR(3)  ,[FCICOD] VARCHAR(36)  ,[AliquotaSOL] FLOAT(15)  ,[TNatRec] VARCHAR(4)  ,[CNatRec] VARCHAR(3)  ,[Estoque] VARCHAR(1)  ,[AliqCSL] FLOAT(15)  ,[AliqPIS] FLOAT(15)  ,[AliqCOF] FLOAT(15)  ,[BaseDes] FLOAT(15)  ,[AliqCMP] FLOAT(15)  ,[Difal] FLOAT(15)  ,[ICMSCom] FLOAT(15)  ,[PDDes] FLOAT(15)  ,[PDOrig] FLOAT(15)  ,[VFCPDIF] FLOAT(15)  ,[AlFCCMP] FLOAT(15)  ,[BaseCPB] FLOAT(15)  ,[AliqPro] FLOAT(15)  ,[Margem] FLOAT(15)  ,[UBCSALD] FLOAT(15)  ,[UBCCHBX] VARCHAR(40)  ,[ALFCPST] FLOAT(15)  ,[ALQFECP] FLOAT(15)  ,[ICMSDIF] FLOAT(15)  ,[VALFECP] FLOAT(15)  ,[VFECPST] FLOAT(15)  ,[VOPDIF] FLOAT(15)  ,[Deletado] VARCHAR(1)  ,[Ukey] INT ,[Registro] INT ,[DataUltimaAlteracao] DATETIME ,[DataInsercao] DATETIME ,primary key([Filial],[SequenciaItem],[CodigoProduto],[NotaFiscal]))");
					if (log.isDebugEnabled())
						log.debug("tDBOutput_3 - " + ("Create") + (" table '") + ("[" + tableName_tDBOutput_3 + "]")
								+ ("' has succeeded."));
				}
				java.sql.PreparedStatement pstmt_tDBOutput_3 = null;
				java.sql.PreparedStatement pstmtInsert_tDBOutput_3 = null;
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_3 = null;
				pstmt_tDBOutput_3 = conn_tDBOutput_3.prepareStatement("SELECT COUNT(1) FROM [" + tableName_tDBOutput_3
						+ "] WHERE [Filial] = ? AND [SequenciaItem] = ? AND [CodigoProduto] = ? AND [NotaFiscal] = ?");
				resourceMap.put("pstmt_tDBOutput_3", pstmt_tDBOutput_3);
				String insert_tDBOutput_3 = "INSERT INTO [" + tableName_tDBOutput_3
						+ "] ([Filial],[SequenciaItem],[CodigoProduto],[SegundaUnidade],[UnidadeMedida],[Quantidade],[PrecoVenda],[PrecoTotal],[ValorIPI],[ValorICMS],[CodigoTes],[CFOP],[Desconto],[IPI],[ValorCSSL],[AliquotaICM],[Peso],[NumeroPedido],[ItemPV],[Cliente],[Loja],[Armazem],[NotaFiscal],[Serie],[Grupo],[TipoProduto],[DataEmissao],[CustoProduto],[Custo2],[Custo3],[Custo4],[Custo5],[PrecoUnitario],[QuantidadeSegundaUnidade],[NumeroSequencial],[Estado],[DescontoValor],[Tipo],[NotaFiscalOrigem],[SerieOrigem],[QuantidadeDevolucao],[ValorDevolucao],[OrigemLancamento],[BaseICMS],[BaseICM],[ValorAcrescimo],[ICMSRetido],[Comissao1],[Comissao2],[Comissao3],[Comissao4],[Comissao5],[NumeroLote],[Lote],[DataValidade],[ClasseFiscal],[BaseImposto5],[BaseImposto6],[ValorImposto5],[ValorImposto6],[AliquotaImposto5],[AliquotaImposto6],[CentroCusto],[Endereco],[ValorImportacao],[DataFabricacao],[AliquotaINSS],[AbatimentoINSS],[PrecoEmbarque],[AliquotaISS],[BaseIPI],[BaseISS],[ValorISS],[Seguro],[ValorFrete],[TipoDocumentoEnv],[Despesa],[OK],[CLVL],[BaseINSS],[ICMFrete],[Servico],[STServ],[ValorINSS],[TipoDoc],[TipoRem],[ValorBruto],[DataDigit],[SitTrib],[GrpCST],[FCICOD],[AliquotaSOL],[TNatRec],[CNatRec],[Estoque],[AliqCSL],[AliqPIS],[AliqCOF],[BaseDes],[AliqCMP],[Difal],[ICMSCom],[PDDes],[PDOrig],[VFCPDIF],[AlFCCMP],[BaseCPB],[AliqPro],[Margem],[UBCSALD],[UBCCHBX],[ALFCPST],[ALQFECP],[ICMSDIF],[VALFECP],[VFECPST],[VOPDIF],[Deletado],[Ukey],[Registro],[DataUltimaAlteracao],[DataInsercao]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmtInsert_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(insert_tDBOutput_3);
				resourceMap.put("pstmtInsert_tDBOutput_3", pstmtInsert_tDBOutput_3);
				String update_tDBOutput_3 = "UPDATE [" + tableName_tDBOutput_3
						+ "] SET [SegundaUnidade] = ?,[UnidadeMedida] = ?,[Quantidade] = ?,[PrecoVenda] = ?,[PrecoTotal] = ?,[ValorIPI] = ?,[ValorICMS] = ?,[CodigoTes] = ?,[CFOP] = ?,[Desconto] = ?,[IPI] = ?,[ValorCSSL] = ?,[AliquotaICM] = ?,[Peso] = ?,[NumeroPedido] = ?,[ItemPV] = ?,[Cliente] = ?,[Loja] = ?,[Armazem] = ?,[Serie] = ?,[Grupo] = ?,[TipoProduto] = ?,[DataEmissao] = ?,[CustoProduto] = ?,[Custo2] = ?,[Custo3] = ?,[Custo4] = ?,[Custo5] = ?,[PrecoUnitario] = ?,[QuantidadeSegundaUnidade] = ?,[NumeroSequencial] = ?,[Estado] = ?,[DescontoValor] = ?,[Tipo] = ?,[NotaFiscalOrigem] = ?,[SerieOrigem] = ?,[QuantidadeDevolucao] = ?,[ValorDevolucao] = ?,[OrigemLancamento] = ?,[BaseICMS] = ?,[BaseICM] = ?,[ValorAcrescimo] = ?,[ICMSRetido] = ?,[Comissao1] = ?,[Comissao2] = ?,[Comissao3] = ?,[Comissao4] = ?,[Comissao5] = ?,[NumeroLote] = ?,[Lote] = ?,[DataValidade] = ?,[ClasseFiscal] = ?,[BaseImposto5] = ?,[BaseImposto6] = ?,[ValorImposto5] = ?,[ValorImposto6] = ?,[AliquotaImposto5] = ?,[AliquotaImposto6] = ?,[CentroCusto] = ?,[Endereco] = ?,[ValorImportacao] = ?,[DataFabricacao] = ?,[AliquotaINSS] = ?,[AbatimentoINSS] = ?,[PrecoEmbarque] = ?,[AliquotaISS] = ?,[BaseIPI] = ?,[BaseISS] = ?,[ValorISS] = ?,[Seguro] = ?,[ValorFrete] = ?,[TipoDocumentoEnv] = ?,[Despesa] = ?,[OK] = ?,[CLVL] = ?,[BaseINSS] = ?,[ICMFrete] = ?,[Servico] = ?,[STServ] = ?,[ValorINSS] = ?,[TipoDoc] = ?,[TipoRem] = ?,[ValorBruto] = ?,[DataDigit] = ?,[SitTrib] = ?,[GrpCST] = ?,[FCICOD] = ?,[AliquotaSOL] = ?,[TNatRec] = ?,[CNatRec] = ?,[Estoque] = ?,[AliqCSL] = ?,[AliqPIS] = ?,[AliqCOF] = ?,[BaseDes] = ?,[AliqCMP] = ?,[Difal] = ?,[ICMSCom] = ?,[PDDes] = ?,[PDOrig] = ?,[VFCPDIF] = ?,[AlFCCMP] = ?,[BaseCPB] = ?,[AliqPro] = ?,[Margem] = ?,[UBCSALD] = ?,[UBCCHBX] = ?,[ALFCPST] = ?,[ALQFECP] = ?,[ICMSDIF] = ?,[VALFECP] = ?,[VFECPST] = ?,[VOPDIF] = ?,[Deletado] = ?,[Ukey] = ?,[Registro] = ?,[DataUltimaAlteracao] = ?,[DataInsercao] = ? WHERE [Filial] = ? AND [SequenciaItem] = ? AND [CodigoProduto] = ? AND [NotaFiscal] = ?";
				pstmtUpdate_tDBOutput_3 = conn_tDBOutput_3.prepareStatement(update_tDBOutput_3);
				resourceMap.put("pstmtUpdate_tDBOutput_3", pstmtUpdate_tDBOutput_3);

				/**
				 * [tDBOutput_3 begin ] stop
				 */

				/**
				 * [tDBOutput_3 main ] start
				 */

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				tos_count_tDBOutput_3++;

				/**
				 * [tDBOutput_3 main ] stop
				 */

				/**
				 * [tDBOutput_3 process_data_begin ] start
				 */

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				/**
				 * [tDBOutput_3 process_data_begin ] stop
				 */

				/**
				 * [tDBOutput_3 process_data_end ] start
				 */

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				/**
				 * [tDBOutput_3 process_data_end ] stop
				 */

				/**
				 * [tDBOutput_3 end ] start
				 */

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				if (pstmtUpdate_tDBOutput_3 != null) {
					pstmtUpdate_tDBOutput_3.close();
					resourceMap.remove("pstmtUpdate_tDBOutput_3");
				}
				if (pstmtInsert_tDBOutput_3 != null) {
					pstmtInsert_tDBOutput_3.close();
					resourceMap.remove("pstmtInsert_tDBOutput_3");
				}
				if (pstmt_tDBOutput_3 != null) {
					pstmt_tDBOutput_3.close();
					resourceMap.remove("pstmt_tDBOutput_3");
				}
				resourceMap.put("statementClosed_tDBOutput_3", true);
				if (rowsToCommitCount_tDBOutput_3 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_3 - " + ("Connection starting to commit ")
								+ (rowsToCommitCount_tDBOutput_3) + (" record(s)."));
				}
				conn_tDBOutput_3.commit();
				if (rowsToCommitCount_tDBOutput_3 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_3 - " + ("Connection commit has succeeded."));
					rowsToCommitCount_tDBOutput_3 = 0;
				}
				commitCounter_tDBOutput_3 = 0;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Closing the connection to the database."));
				conn_tDBOutput_3.close();
				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Connection to the database has closed."));
				resourceMap.put("finish_tDBOutput_3", true);

				nb_line_deleted_tDBOutput_3 = nb_line_deleted_tDBOutput_3 + deletedCount_tDBOutput_3;
				nb_line_update_tDBOutput_3 = nb_line_update_tDBOutput_3 + updatedCount_tDBOutput_3;
				nb_line_inserted_tDBOutput_3 = nb_line_inserted_tDBOutput_3 + insertedCount_tDBOutput_3;
				nb_line_rejected_tDBOutput_3 = nb_line_rejected_tDBOutput_3 + rejectedCount_tDBOutput_3;

				globalMap.put("tDBOutput_3_NB_LINE", nb_line_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_UPDATED", nb_line_update_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_DELETED", nb_line_deleted_tDBOutput_3);
				globalMap.put("tDBOutput_3_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_3);

				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Has ") + ("inserted") + (" ") + (nb_line_inserted_tDBOutput_3)
							+ (" record(s)."));
				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Has ") + ("updated") + (" ") + (nb_line_update_tDBOutput_3)
							+ (" record(s)."));

				if (log.isDebugEnabled())
					log.debug("tDBOutput_3 - " + ("Done."));

				ok_Hash.put("tDBOutput_3", true);
				end_Hash.put("tDBOutput_3", System.currentTimeMillis());

				/**
				 * [tDBOutput_3 end ] stop
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
				 * [tDBOutput_3 finally ] start
				 */

				currentComponent = "tDBOutput_3";

				cLabel = "Datalake_Bronze";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_3") == null) {
						java.sql.PreparedStatement pstmtUpdateToClose_tDBOutput_3 = null;
						if ((pstmtUpdateToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtUpdate_tDBOutput_3")) != null) {
							pstmtUpdateToClose_tDBOutput_3.close();
						}
						java.sql.PreparedStatement pstmtInsertToClose_tDBOutput_3 = null;
						if ((pstmtInsertToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmtInsert_tDBOutput_3")) != null) {
							pstmtInsertToClose_tDBOutput_3.close();
						}
						java.sql.PreparedStatement pstmtToClose_tDBOutput_3 = null;
						if ((pstmtToClose_tDBOutput_3 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_3")) != null) {
							pstmtToClose_tDBOutput_3.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_3") == null) {
						java.sql.Connection ctn_tDBOutput_3 = null;
						if ((ctn_tDBOutput_3 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_3")) != null) {
							try {
								if (log.isDebugEnabled())
									log.debug("tDBOutput_3 - " + ("Closing the connection to the database."));
								ctn_tDBOutput_3.close();
								if (log.isDebugEnabled())
									log.debug("tDBOutput_3 - " + ("Connection to the database has closed."));
							} catch (java.sql.SQLException sqlEx_tDBOutput_3) {
								String errorMessage_tDBOutput_3 = "failed to close the connection in tDBOutput_3 :"
										+ sqlEx_tDBOutput_3.getMessage();
								log.error("tDBOutput_3 - " + (errorMessage_tDBOutput_3));
								System.err.println(errorMessage_tDBOutput_3);
							}
						}
					}
				}

				/**
				 * [tDBOutput_3 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBOutput_3_SUBPROCESS_STATE", 1);
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
		final ItensNotaSaida ItensNotaSaidaClass = new ItensNotaSaida();

		int exitCode = ItensNotaSaidaClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'ItensNotaSaida' - Done.");
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
		log.info("TalendJob: 'ItensNotaSaida' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_DY438ELbEe-YS8RQVZZjcw");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-08-01T17:52:57.905230400Z");

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
			java.io.InputStream inContext = ItensNotaSaida.class.getClassLoader()
					.getResourceAsStream("hydronorth/itensnotasaida_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = ItensNotaSaida.class.getClassLoader()
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
		log.info("TalendJob: 'ItensNotaSaida' - Started.");
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
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : ItensNotaSaida");
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
		log.info("TalendJob: 'ItensNotaSaida' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 657267 characters generated by Talend Cloud Data Integration on the 1 de
 * agosto de 2024 14:52:57 BRT
 ************************************************************************************************/