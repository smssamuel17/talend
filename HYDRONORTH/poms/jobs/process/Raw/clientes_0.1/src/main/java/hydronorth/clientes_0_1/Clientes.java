
package hydronorth.clientes_0_1;

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
 * Job: Clientes Purpose: <br>
 * Description: <br>
 * 
 * @author Oliveira Santi, Thiago
 * @version 8.0.1.20240619_0909-patch
 * @status DEV
 */
public class Clientes implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Clientes.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(Clientes.class);

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
	private final String jobName = "Clientes";
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
			"_se7FUEVFEe-WLtL1mGd32Q", "0.1");
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
					Clientes.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Clientes.this, new Object[] { e, currentComponent, globalMap });
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

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

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

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Clientes = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Clientes = new byte[0];
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

		public String Codigo;

		public String getCodigo() {
			return this.Codigo;
		}

		public Boolean CodigoIsNullable() {
			return true;
		}

		public Boolean CodigoIsKey() {
			return true;
		}

		public Integer CodigoLength() {
			return 9;
		}

		public Integer CodigoPrecision() {
			return 0;
		}

		public String CodigoDefault() {

			return null;

		}

		public String CodigoComment() {

			return "";

		}

		public String CodigoPattern() {

			return "";

		}

		public String CodigoOriginalDbColumnName() {

			return "Codigo";

		}

		public String Loja;

		public String getLoja() {
			return this.Loja;
		}

		public Boolean LojaIsNullable() {
			return true;
		}

		public Boolean LojaIsKey() {
			return true;
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

		public String Pessoa;

		public String getPessoa() {
			return this.Pessoa;
		}

		public Boolean PessoaIsNullable() {
			return true;
		}

		public Boolean PessoaIsKey() {
			return false;
		}

		public Integer PessoaLength() {
			return 1;
		}

		public Integer PessoaPrecision() {
			return 0;
		}

		public String PessoaDefault() {

			return null;

		}

		public String PessoaComment() {

			return "";

		}

		public String PessoaPattern() {

			return "";

		}

		public String PessoaOriginalDbColumnName() {

			return "Pessoa";

		}

		public String Natureza;

		public String getNatureza() {
			return this.Natureza;
		}

		public Boolean NaturezaIsNullable() {
			return true;
		}

		public Boolean NaturezaIsKey() {
			return false;
		}

		public Integer NaturezaLength() {
			return 10;
		}

		public Integer NaturezaPrecision() {
			return 0;
		}

		public String NaturezaDefault() {

			return null;

		}

		public String NaturezaComment() {

			return "";

		}

		public String NaturezaPattern() {

			return "";

		}

		public String NaturezaOriginalDbColumnName() {

			return "Natureza";

		}

		public String CNPJ;

		public String getCNPJ() {
			return this.CNPJ;
		}

		public Boolean CNPJIsNullable() {
			return true;
		}

		public Boolean CNPJIsKey() {
			return false;
		}

		public Integer CNPJLength() {
			return 14;
		}

		public Integer CNPJPrecision() {
			return 0;
		}

		public String CNPJDefault() {

			return null;

		}

		public String CNPJComment() {

			return "";

		}

		public String CNPJPattern() {

			return "";

		}

		public String CNPJOriginalDbColumnName() {

			return "CNPJ";

		}

		public String Nome;

		public String getNome() {
			return this.Nome;
		}

		public Boolean NomeIsNullable() {
			return true;
		}

		public Boolean NomeIsKey() {
			return false;
		}

		public Integer NomeLength() {
			return 40;
		}

		public Integer NomePrecision() {
			return 0;
		}

		public String NomeDefault() {

			return null;

		}

		public String NomeComment() {

			return "";

		}

		public String NomePattern() {

			return "";

		}

		public String NomeOriginalDbColumnName() {

			return "Nome";

		}

		public String NomeReduzido;

		public String getNomeReduzido() {
			return this.NomeReduzido;
		}

		public Boolean NomeReduzidoIsNullable() {
			return true;
		}

		public Boolean NomeReduzidoIsKey() {
			return false;
		}

		public Integer NomeReduzidoLength() {
			return 20;
		}

		public Integer NomeReduzidoPrecision() {
			return 0;
		}

		public String NomeReduzidoDefault() {

			return null;

		}

		public String NomeReduzidoComment() {

			return "";

		}

		public String NomeReduzidoPattern() {

			return "";

		}

		public String NomeReduzidoOriginalDbColumnName() {

			return "NomeReduzido";

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
			return 80;
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

		public String Complemento;

		public String getComplemento() {
			return this.Complemento;
		}

		public Boolean ComplementoIsNullable() {
			return true;
		}

		public Boolean ComplementoIsKey() {
			return false;
		}

		public Integer ComplementoLength() {
			return 50;
		}

		public Integer ComplementoPrecision() {
			return 0;
		}

		public String ComplementoDefault() {

			return null;

		}

		public String ComplementoComment() {

			return "";

		}

		public String ComplementoPattern() {

			return "";

		}

		public String ComplementoOriginalDbColumnName() {

			return "Complemento";

		}

		public String Bairro;

		public String getBairro() {
			return this.Bairro;
		}

		public Boolean BairroIsNullable() {
			return true;
		}

		public Boolean BairroIsKey() {
			return false;
		}

		public Integer BairroLength() {
			return 40;
		}

		public Integer BairroPrecision() {
			return 0;
		}

		public String BairroDefault() {

			return null;

		}

		public String BairroComment() {

			return "";

		}

		public String BairroPattern() {

			return "";

		}

		public String BairroOriginalDbColumnName() {

			return "Bairro";

		}

		public String CEP;

		public String getCEP() {
			return this.CEP;
		}

		public Boolean CEPIsNullable() {
			return true;
		}

		public Boolean CEPIsKey() {
			return false;
		}

		public Integer CEPLength() {
			return 8;
		}

		public Integer CEPPrecision() {
			return 0;
		}

		public String CEPDefault() {

			return null;

		}

		public String CEPComment() {

			return "";

		}

		public String CEPPattern() {

			return "";

		}

		public String CEPOriginalDbColumnName() {

			return "CEP";

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

		public String Regiao;

		public String getRegiao() {
			return this.Regiao;
		}

		public Boolean RegiaoIsNullable() {
			return true;
		}

		public Boolean RegiaoIsKey() {
			return false;
		}

		public Integer RegiaoLength() {
			return 3;
		}

		public Integer RegiaoPrecision() {
			return 0;
		}

		public String RegiaoDefault() {

			return null;

		}

		public String RegiaoComment() {

			return "";

		}

		public String RegiaoPattern() {

			return "";

		}

		public String RegiaoOriginalDbColumnName() {

			return "Regiao";

		}

		public String DescricaoRegiao;

		public String getDescricaoRegiao() {
			return this.DescricaoRegiao;
		}

		public Boolean DescricaoRegiaoIsNullable() {
			return true;
		}

		public Boolean DescricaoRegiaoIsKey() {
			return false;
		}

		public Integer DescricaoRegiaoLength() {
			return 15;
		}

		public Integer DescricaoRegiaoPrecision() {
			return 0;
		}

		public String DescricaoRegiaoDefault() {

			return null;

		}

		public String DescricaoRegiaoComment() {

			return "";

		}

		public String DescricaoRegiaoPattern() {

			return "";

		}

		public String DescricaoRegiaoOriginalDbColumnName() {

			return "DescricaoRegiao";

		}

		public String CodigoMunicipio;

		public String getCodigoMunicipio() {
			return this.CodigoMunicipio;
		}

		public Boolean CodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean CodigoMunicipioIsKey() {
			return false;
		}

		public Integer CodigoMunicipioLength() {
			return 5;
		}

		public Integer CodigoMunicipioPrecision() {
			return 0;
		}

		public String CodigoMunicipioDefault() {

			return null;

		}

		public String CodigoMunicipioComment() {

			return "";

		}

		public String CodigoMunicipioPattern() {

			return "";

		}

		public String CodigoMunicipioOriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String Municipio;

		public String getMunicipio() {
			return this.Municipio;
		}

		public Boolean MunicipioIsNullable() {
			return true;
		}

		public Boolean MunicipioIsKey() {
			return false;
		}

		public Integer MunicipioLength() {
			return 60;
		}

		public Integer MunicipioPrecision() {
			return 0;
		}

		public String MunicipioDefault() {

			return null;

		}

		public String MunicipioComment() {

			return "";

		}

		public String MunicipioPattern() {

			return "";

		}

		public String MunicipioOriginalDbColumnName() {

			return "Municipio";

		}

		public String CodigoPais;

		public String getCodigoPais() {
			return this.CodigoPais;
		}

		public Boolean CodigoPaisIsNullable() {
			return true;
		}

		public Boolean CodigoPaisIsKey() {
			return false;
		}

		public Integer CodigoPaisLength() {
			return 5;
		}

		public Integer CodigoPaisPrecision() {
			return 0;
		}

		public String CodigoPaisDefault() {

			return null;

		}

		public String CodigoPaisComment() {

			return "";

		}

		public String CodigoPaisPattern() {

			return "";

		}

		public String CodigoPaisOriginalDbColumnName() {

			return "CodigoPais";

		}

		public String NomeEstado;

		public String getNomeEstado() {
			return this.NomeEstado;
		}

		public Boolean NomeEstadoIsNullable() {
			return true;
		}

		public Boolean NomeEstadoIsKey() {
			return false;
		}

		public Integer NomeEstadoLength() {
			return 20;
		}

		public Integer NomeEstadoPrecision() {
			return 0;
		}

		public String NomeEstadoDefault() {

			return null;

		}

		public String NomeEstadoComment() {

			return "";

		}

		public String NomeEstadoPattern() {

			return "";

		}

		public String NomeEstadoOriginalDbColumnName() {

			return "NomeEstado";

		}

		public String DDD;

		public String getDDD() {
			return this.DDD;
		}

		public Boolean DDDIsNullable() {
			return true;
		}

		public Boolean DDDIsKey() {
			return false;
		}

		public Integer DDDLength() {
			return 3;
		}

		public Integer DDDPrecision() {
			return 0;
		}

		public String DDDDefault() {

			return null;

		}

		public String DDDComment() {

			return "";

		}

		public String DDDPattern() {

			return "";

		}

		public String DDDOriginalDbColumnName() {

			return "DDD";

		}

		public String TributacaoFavoravel;

		public String getTributacaoFavoravel() {
			return this.TributacaoFavoravel;
		}

		public Boolean TributacaoFavoravelIsNullable() {
			return true;
		}

		public Boolean TributacaoFavoravelIsKey() {
			return false;
		}

		public Integer TributacaoFavoravelLength() {
			return 1;
		}

		public Integer TributacaoFavoravelPrecision() {
			return 0;
		}

		public String TributacaoFavoravelDefault() {

			return null;

		}

		public String TributacaoFavoravelComment() {

			return "";

		}

		public String TributacaoFavoravelPattern() {

			return "";

		}

		public String TributacaoFavoravelOriginalDbColumnName() {

			return "TributacaoFavoravel";

		}

		public String DDI;

		public String getDDI() {
			return this.DDI;
		}

		public Boolean DDIIsNullable() {
			return true;
		}

		public Boolean DDIIsKey() {
			return false;
		}

		public Integer DDILength() {
			return 6;
		}

		public Integer DDIPrecision() {
			return 0;
		}

		public String DDIDefault() {

			return null;

		}

		public String DDIComment() {

			return "";

		}

		public String DDIPattern() {

			return "";

		}

		public String DDIOriginalDbColumnName() {

			return "DDI";

		}

		public String CodIBGE;

		public String getCodIBGE() {
			return this.CodIBGE;
		}

		public Boolean CodIBGEIsNullable() {
			return true;
		}

		public Boolean CodIBGEIsKey() {
			return false;
		}

		public Integer CodIBGELength() {
			return 11;
		}

		public Integer CodIBGEPrecision() {
			return 0;
		}

		public String CodIBGEDefault() {

			return null;

		}

		public String CodIBGEComment() {

			return "";

		}

		public String CodIBGEPattern() {

			return "";

		}

		public String CodIBGEOriginalDbColumnName() {

			return "CodIBGE";

		}

		public String UCodigoMunicipio;

		public String getUCodigoMunicipio() {
			return this.UCodigoMunicipio;
		}

		public Boolean UCodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioLength() {
			return 5;
		}

		public Integer UCodigoMunicipioPrecision() {
			return 0;
		}

		public String UCodigoMunicipioDefault() {

			return null;

		}

		public String UCodigoMunicipioComment() {

			return "";

		}

		public String UCodigoMunicipioPattern() {

			return "";

		}

		public String UCodigoMunicipioOriginalDbColumnName() {

			return "UCodigoMunicipio";

		}

		public String EnderecoRecebimento;

		public String getEnderecoRecebimento() {
			return this.EnderecoRecebimento;
		}

		public Boolean EnderecoRecebimentoIsNullable() {
			return true;
		}

		public Boolean EnderecoRecebimentoIsKey() {
			return false;
		}

		public Integer EnderecoRecebimentoLength() {
			return 40;
		}

		public Integer EnderecoRecebimentoPrecision() {
			return 0;
		}

		public String EnderecoRecebimentoDefault() {

			return null;

		}

		public String EnderecoRecebimentoComment() {

			return "";

		}

		public String EnderecoRecebimentoPattern() {

			return "";

		}

		public String EnderecoRecebimentoOriginalDbColumnName() {

			return "EnderecoRecebimento";

		}

		public String Telefone;

		public String getTelefone() {
			return this.Telefone;
		}

		public Boolean TelefoneIsNullable() {
			return true;
		}

		public Boolean TelefoneIsKey() {
			return false;
		}

		public Integer TelefoneLength() {
			return 15;
		}

		public Integer TelefonePrecision() {
			return 0;
		}

		public String TelefoneDefault() {

			return null;

		}

		public String TelefoneComment() {

			return "";

		}

		public String TelefonePattern() {

			return "";

		}

		public String TelefoneOriginalDbColumnName() {

			return "Telefone";

		}

		public String FAX;

		public String getFAX() {
			return this.FAX;
		}

		public Boolean FAXIsNullable() {
			return true;
		}

		public Boolean FAXIsKey() {
			return false;
		}

		public Integer FAXLength() {
			return 15;
		}

		public Integer FAXPrecision() {
			return 0;
		}

		public String FAXDefault() {

			return null;

		}

		public String FAXComment() {

			return "";

		}

		public String FAXPattern() {

			return "";

		}

		public String FAXOriginalDbColumnName() {

			return "FAX";

		}

		public String FoneCP;

		public String getFoneCP() {
			return this.FoneCP;
		}

		public Boolean FoneCPIsNullable() {
			return true;
		}

		public Boolean FoneCPIsKey() {
			return false;
		}

		public Integer FoneCPLength() {
			return 15;
		}

		public Integer FoneCPPrecision() {
			return 0;
		}

		public String FoneCPDefault() {

			return null;

		}

		public String FoneCPComment() {

			return "";

		}

		public String FoneCPPattern() {

			return "";

		}

		public String FoneCPOriginalDbColumnName() {

			return "FoneCP";

		}

		public String Contato;

		public String getContato() {
			return this.Contato;
		}

		public Boolean ContatoIsNullable() {
			return true;
		}

		public Boolean ContatoIsKey() {
			return false;
		}

		public Integer ContatoLength() {
			return 80;
		}

		public Integer ContatoPrecision() {
			return 0;
		}

		public String ContatoDefault() {

			return null;

		}

		public String ContatoComment() {

			return "";

		}

		public String ContatoPattern() {

			return "";

		}

		public String ContatoOriginalDbColumnName() {

			return "Contato";

		}

		public String PessoaFisica;

		public String getPessoaFisica() {
			return this.PessoaFisica;
		}

		public Boolean PessoaFisicaIsNullable() {
			return true;
		}

		public Boolean PessoaFisicaIsKey() {
			return false;
		}

		public Integer PessoaFisicaLength() {
			return 18;
		}

		public Integer PessoaFisicaPrecision() {
			return 0;
		}

		public String PessoaFisicaDefault() {

			return null;

		}

		public String PessoaFisicaComment() {

			return "";

		}

		public String PessoaFisicaPattern() {

			return "";

		}

		public String PessoaFisicaOriginalDbColumnName() {

			return "PessoaFisica";

		}

		public String Pais;

		public String getPais() {
			return this.Pais;
		}

		public Boolean PaisIsNullable() {
			return true;
		}

		public Boolean PaisIsKey() {
			return false;
		}

		public Integer PaisLength() {
			return 3;
		}

		public Integer PaisPrecision() {
			return 0;
		}

		public String PaisDefault() {

			return null;

		}

		public String PaisComment() {

			return "";

		}

		public String PaisPattern() {

			return "";

		}

		public String PaisOriginalDbColumnName() {

			return "Pais";

		}

		public String Inscricao;

		public String getInscricao() {
			return this.Inscricao;
		}

		public Boolean InscricaoIsNullable() {
			return true;
		}

		public Boolean InscricaoIsKey() {
			return false;
		}

		public Integer InscricaoLength() {
			return 18;
		}

		public Integer InscricaoPrecision() {
			return 0;
		}

		public String InscricaoDefault() {

			return null;

		}

		public String InscricaoComment() {

			return "";

		}

		public String InscricaoPattern() {

			return "";

		}

		public String InscricaoOriginalDbColumnName() {

			return "Inscricao";

		}

		public String InscricaoMunicipal;

		public String getInscricaoMunicipal() {
			return this.InscricaoMunicipal;
		}

		public Boolean InscricaoMunicipalIsNullable() {
			return true;
		}

		public Boolean InscricaoMunicipalIsKey() {
			return false;
		}

		public Integer InscricaoMunicipalLength() {
			return 18;
		}

		public Integer InscricaoMunicipalPrecision() {
			return 0;
		}

		public String InscricaoMunicipalDefault() {

			return null;

		}

		public String InscricaoMunicipalComment() {

			return "";

		}

		public String InscricaoMunicipalPattern() {

			return "";

		}

		public String InscricaoMunicipalOriginalDbColumnName() {

			return "InscricaoMunicipal";

		}

		public String Vendedor;

		public String getVendedor() {
			return this.Vendedor;
		}

		public Boolean VendedorIsNullable() {
			return true;
		}

		public Boolean VendedorIsKey() {
			return false;
		}

		public Integer VendedorLength() {
			return 6;
		}

		public Integer VendedorPrecision() {
			return 0;
		}

		public String VendedorDefault() {

			return null;

		}

		public String VendedorComment() {

			return "";

		}

		public String VendedorPattern() {

			return "";

		}

		public String VendedorOriginalDbColumnName() {

			return "Vendedor";

		}

		public Double Comissao;

		public Double getComissao() {
			return this.Comissao;
		}

		public Boolean ComissaoIsNullable() {
			return true;
		}

		public Boolean ComissaoIsKey() {
			return false;
		}

		public Integer ComissaoLength() {
			return 15;
		}

		public Integer ComissaoPrecision() {
			return 0;
		}

		public String ComissaoDefault() {

			return "";

		}

		public String ComissaoComment() {

			return "";

		}

		public String ComissaoPattern() {

			return "";

		}

		public String ComissaoOriginalDbColumnName() {

			return "Comissao";

		}

		public String Conta;

		public String getConta() {
			return this.Conta;
		}

		public Boolean ContaIsNullable() {
			return true;
		}

		public Boolean ContaIsKey() {
			return false;
		}

		public Integer ContaLength() {
			return 20;
		}

		public Integer ContaPrecision() {
			return 0;
		}

		public String ContaDefault() {

			return null;

		}

		public String ContaComment() {

			return "";

		}

		public String ContaPattern() {

			return "";

		}

		public String ContaOriginalDbColumnName() {

			return "Conta";

		}

		public String Banco1;

		public String getBanco1() {
			return this.Banco1;
		}

		public Boolean Banco1IsNullable() {
			return true;
		}

		public Boolean Banco1IsKey() {
			return false;
		}

		public Integer Banco1Length() {
			return 3;
		}

		public Integer Banco1Precision() {
			return 0;
		}

		public String Banco1Default() {

			return null;

		}

		public String Banco1Comment() {

			return "";

		}

		public String Banco1Pattern() {

			return "";

		}

		public String Banco1OriginalDbColumnName() {

			return "Banco1";

		}

		public String Banco2;

		public String getBanco2() {
			return this.Banco2;
		}

		public Boolean Banco2IsNullable() {
			return true;
		}

		public Boolean Banco2IsKey() {
			return false;
		}

		public Integer Banco2Length() {
			return 3;
		}

		public Integer Banco2Precision() {
			return 0;
		}

		public String Banco2Default() {

			return null;

		}

		public String Banco2Comment() {

			return "";

		}

		public String Banco2Pattern() {

			return "";

		}

		public String Banco2OriginalDbColumnName() {

			return "Banco2";

		}

		public String Banco3;

		public String getBanco3() {
			return this.Banco3;
		}

		public Boolean Banco3IsNullable() {
			return true;
		}

		public Boolean Banco3IsKey() {
			return false;
		}

		public Integer Banco3Length() {
			return 3;
		}

		public Integer Banco3Precision() {
			return 0;
		}

		public String Banco3Default() {

			return null;

		}

		public String Banco3Comment() {

			return "";

		}

		public String Banco3Pattern() {

			return "";

		}

		public String Banco3OriginalDbColumnName() {

			return "Banco3";

		}

		public String Banco4;

		public String getBanco4() {
			return this.Banco4;
		}

		public Boolean Banco4IsNullable() {
			return true;
		}

		public Boolean Banco4IsKey() {
			return false;
		}

		public Integer Banco4Length() {
			return 3;
		}

		public Integer Banco4Precision() {
			return 0;
		}

		public String Banco4Default() {

			return null;

		}

		public String Banco4Comment() {

			return "";

		}

		public String Banco4Pattern() {

			return "";

		}

		public String Banco4OriginalDbColumnName() {

			return "Banco4";

		}

		public String Banco5;

		public String getBanco5() {
			return this.Banco5;
		}

		public Boolean Banco5IsNullable() {
			return true;
		}

		public Boolean Banco5IsKey() {
			return false;
		}

		public Integer Banco5Length() {
			return 3;
		}

		public Integer Banco5Precision() {
			return 0;
		}

		public String Banco5Default() {

			return null;

		}

		public String Banco5Comment() {

			return "";

		}

		public String Banco5Pattern() {

			return "";

		}

		public String Banco5OriginalDbColumnName() {

			return "Banco5";

		}

		public String Transporte;

		public String getTransporte() {
			return this.Transporte;
		}

		public Boolean TransporteIsNullable() {
			return true;
		}

		public Boolean TransporteIsKey() {
			return false;
		}

		public Integer TransporteLength() {
			return 6;
		}

		public Integer TransportePrecision() {
			return 0;
		}

		public String TransporteDefault() {

			return null;

		}

		public String TransporteComment() {

			return "";

		}

		public String TransportePattern() {

			return "";

		}

		public String TransporteOriginalDbColumnName() {

			return "Transporte";

		}

		public String TipoFrete;

		public String getTipoFrete() {
			return this.TipoFrete;
		}

		public Boolean TipoFreteIsNullable() {
			return true;
		}

		public Boolean TipoFreteIsKey() {
			return false;
		}

		public Integer TipoFreteLength() {
			return 1;
		}

		public Integer TipoFretePrecision() {
			return 0;
		}

		public String TipoFreteDefault() {

			return null;

		}

		public String TipoFreteComment() {

			return "";

		}

		public String TipoFretePattern() {

			return "";

		}

		public String TipoFreteOriginalDbColumnName() {

			return "TipoFrete";

		}

		public String EnderecoCobranca;

		public String getEnderecoCobranca() {
			return this.EnderecoCobranca;
		}

		public Boolean EnderecoCobrancaIsNullable() {
			return true;
		}

		public Boolean EnderecoCobrancaIsKey() {
			return false;
		}

		public Integer EnderecoCobrancaLength() {
			return 40;
		}

		public Integer EnderecoCobrancaPrecision() {
			return 0;
		}

		public String EnderecoCobrancaDefault() {

			return null;

		}

		public String EnderecoCobrancaComment() {

			return "";

		}

		public String EnderecoCobrancaPattern() {

			return "";

		}

		public String EnderecoCobrancaOriginalDbColumnName() {

			return "EnderecoCobranca";

		}

		public String Condicao;

		public String getCondicao() {
			return this.Condicao;
		}

		public Boolean CondicaoIsNullable() {
			return true;
		}

		public Boolean CondicaoIsKey() {
			return false;
		}

		public Integer CondicaoLength() {
			return 3;
		}

		public Integer CondicaoPrecision() {
			return 0;
		}

		public String CondicaoDefault() {

			return null;

		}

		public String CondicaoComment() {

			return "";

		}

		public String CondicaoPattern() {

			return "";

		}

		public String CondicaoOriginalDbColumnName() {

			return "Condicao";

		}

		public String BairroCobranca;

		public String getBairroCobranca() {
			return this.BairroCobranca;
		}

		public Boolean BairroCobrancaIsNullable() {
			return true;
		}

		public Boolean BairroCobrancaIsKey() {
			return false;
		}

		public Integer BairroCobrancaLength() {
			return 30;
		}

		public Integer BairroCobrancaPrecision() {
			return 0;
		}

		public String BairroCobrancaDefault() {

			return null;

		}

		public String BairroCobrancaComment() {

			return "";

		}

		public String BairroCobrancaPattern() {

			return "";

		}

		public String BairroCobrancaOriginalDbColumnName() {

			return "BairroCobranca";

		}

		public Double Descricao;

		public Double getDescricao() {
			return this.Descricao;
		}

		public Boolean DescricaoIsNullable() {
			return true;
		}

		public Boolean DescricaoIsKey() {
			return false;
		}

		public Integer DescricaoLength() {
			return 15;
		}

		public Integer DescricaoPrecision() {
			return 0;
		}

		public String DescricaoDefault() {

			return "";

		}

		public String DescricaoComment() {

			return "";

		}

		public String DescricaoPattern() {

			return "";

		}

		public String DescricaoOriginalDbColumnName() {

			return "Descricao";

		}

		public String CEPCobranca;

		public String getCEPCobranca() {
			return this.CEPCobranca;
		}

		public Boolean CEPCobrancaIsNullable() {
			return true;
		}

		public Boolean CEPCobrancaIsKey() {
			return false;
		}

		public Integer CEPCobrancaLength() {
			return 8;
		}

		public Integer CEPCobrancaPrecision() {
			return 0;
		}

		public String CEPCobrancaDefault() {

			return null;

		}

		public String CEPCobrancaComment() {

			return "";

		}

		public String CEPCobrancaPattern() {

			return "";

		}

		public String CEPCobrancaOriginalDbColumnName() {

			return "CEPCobranca";

		}

		public String EstadoCobranca;

		public String getEstadoCobranca() {
			return this.EstadoCobranca;
		}

		public Boolean EstadoCobrancaIsNullable() {
			return true;
		}

		public Boolean EstadoCobrancaIsKey() {
			return false;
		}

		public Integer EstadoCobrancaLength() {
			return 2;
		}

		public Integer EstadoCobrancaPrecision() {
			return 0;
		}

		public String EstadoCobrancaDefault() {

			return null;

		}

		public String EstadoCobrancaComment() {

			return "";

		}

		public String EstadoCobrancaPattern() {

			return "";

		}

		public String EstadoCobrancaOriginalDbColumnName() {

			return "EstadoCobranca";

		}

		public String UCodigoMunicipioCobranca;

		public String getUCodigoMunicipioCobranca() {
			return this.UCodigoMunicipioCobranca;
		}

		public Boolean UCodigoMunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioCobrancaIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioCobrancaLength() {
			return 5;
		}

		public Integer UCodigoMunicipioCobrancaPrecision() {
			return 0;
		}

		public String UCodigoMunicipioCobrancaDefault() {

			return null;

		}

		public String UCodigoMunicipioCobrancaComment() {

			return "";

		}

		public String UCodigoMunicipioCobrancaPattern() {

			return "";

		}

		public String UCodigoMunicipioCobrancaOriginalDbColumnName() {

			return "UCodigoMunicipioCobranca";

		}

		public String MunicipioCobranca;

		public String getMunicipioCobranca() {
			return this.MunicipioCobranca;
		}

		public Boolean MunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean MunicipioCobrancaIsKey() {
			return false;
		}

		public Integer MunicipioCobrancaLength() {
			return 60;
		}

		public Integer MunicipioCobrancaPrecision() {
			return 0;
		}

		public String MunicipioCobrancaDefault() {

			return null;

		}

		public String MunicipioCobrancaComment() {

			return "";

		}

		public String MunicipioCobrancaPattern() {

			return "";

		}

		public String MunicipioCobrancaOriginalDbColumnName() {

			return "MunicipioCobranca";

		}

		public String Prioridade;

		public String getPrioridade() {
			return this.Prioridade;
		}

		public Boolean PrioridadeIsNullable() {
			return true;
		}

		public Boolean PrioridadeIsKey() {
			return false;
		}

		public Integer PrioridadeLength() {
			return 1;
		}

		public Integer PrioridadePrecision() {
			return 0;
		}

		public String PrioridadeDefault() {

			return null;

		}

		public String PrioridadeComment() {

			return "";

		}

		public String PrioridadePattern() {

			return "";

		}

		public String PrioridadeOriginalDbColumnName() {

			return "Prioridade";

		}

		public String Risco;

		public String getRisco() {
			return this.Risco;
		}

		public Boolean RiscoIsNullable() {
			return true;
		}

		public Boolean RiscoIsKey() {
			return false;
		}

		public Integer RiscoLength() {
			return 1;
		}

		public Integer RiscoPrecision() {
			return 0;
		}

		public String RiscoDefault() {

			return null;

		}

		public String RiscoComment() {

			return "";

		}

		public String RiscoPattern() {

			return "";

		}

		public String RiscoOriginalDbColumnName() {

			return "Risco";

		}

		public Double LimiteCredito;

		public Double getLimiteCredito() {
			return this.LimiteCredito;
		}

		public Boolean LimiteCreditoIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoIsKey() {
			return false;
		}

		public Integer LimiteCreditoLength() {
			return 15;
		}

		public Integer LimiteCreditoPrecision() {
			return 0;
		}

		public String LimiteCreditoDefault() {

			return "";

		}

		public String LimiteCreditoComment() {

			return "";

		}

		public String LimiteCreditoPattern() {

			return "";

		}

		public String LimiteCreditoOriginalDbColumnName() {

			return "LimiteCredito";

		}

		public java.util.Date VencimentoLimiteCredito;

		public java.util.Date getVencimentoLimiteCredito() {
			return this.VencimentoLimiteCredito;
		}

		public Boolean VencimentoLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean VencimentoLimiteCreditoIsKey() {
			return false;
		}

		public Integer VencimentoLimiteCreditoLength() {
			return 8;
		}

		public Integer VencimentoLimiteCreditoPrecision() {
			return 0;
		}

		public String VencimentoLimiteCreditoDefault() {

			return null;

		}

		public String VencimentoLimiteCreditoComment() {

			return "";

		}

		public String VencimentoLimiteCreditoPattern() {

			return "dd-MM-yyyy";

		}

		public String VencimentoLimiteCreditoOriginalDbColumnName() {

			return "VencimentoLimiteCredito";

		}

		public String Classe;

		public String getClasse() {
			return this.Classe;
		}

		public Boolean ClasseIsNullable() {
			return true;
		}

		public Boolean ClasseIsKey() {
			return false;
		}

		public Integer ClasseLength() {
			return 1;
		}

		public Integer ClassePrecision() {
			return 0;
		}

		public String ClasseDefault() {

			return null;

		}

		public String ClasseComment() {

			return "";

		}

		public String ClassePattern() {

			return "";

		}

		public String ClasseOriginalDbColumnName() {

			return "Classe";

		}

		public Double LimiteCreditoFinal;

		public Double getLimiteCreditoFinal() {
			return this.LimiteCreditoFinal;
		}

		public Boolean LimiteCreditoFinalIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoFinalIsKey() {
			return false;
		}

		public Integer LimiteCreditoFinalLength() {
			return 15;
		}

		public Integer LimiteCreditoFinalPrecision() {
			return 0;
		}

		public String LimiteCreditoFinalDefault() {

			return "";

		}

		public String LimiteCreditoFinalComment() {

			return "";

		}

		public String LimiteCreditoFinalPattern() {

			return "";

		}

		public String LimiteCreditoFinalOriginalDbColumnName() {

			return "LimiteCreditoFinal";

		}

		public Double MoedaLimiteCredito;

		public Double getMoedaLimiteCredito() {
			return this.MoedaLimiteCredito;
		}

		public Boolean MoedaLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean MoedaLimiteCreditoIsKey() {
			return false;
		}

		public Integer MoedaLimiteCreditoLength() {
			return 15;
		}

		public Integer MoedaLimiteCreditoPrecision() {
			return 0;
		}

		public String MoedaLimiteCreditoDefault() {

			return "";

		}

		public String MoedaLimiteCreditoComment() {

			return "";

		}

		public String MoedaLimiteCreditoPattern() {

			return "";

		}

		public String MoedaLimiteCreditoOriginalDbColumnName() {

			return "MoedaLimiteCredito";

		}

		public Double MaiorSaldo;

		public Double getMaiorSaldo() {
			return this.MaiorSaldo;
		}

		public Boolean MaiorSaldoIsNullable() {
			return true;
		}

		public Boolean MaiorSaldoIsKey() {
			return false;
		}

		public Integer MaiorSaldoLength() {
			return 15;
		}

		public Integer MaiorSaldoPrecision() {
			return 0;
		}

		public String MaiorSaldoDefault() {

			return "";

		}

		public String MaiorSaldoComment() {

			return "";

		}

		public String MaiorSaldoPattern() {

			return "";

		}

		public String MaiorSaldoOriginalDbColumnName() {

			return "MaiorSaldo";

		}

		public Double MaiorCompra;

		public Double getMaiorCompra() {
			return this.MaiorCompra;
		}

		public Boolean MaiorCompraIsNullable() {
			return true;
		}

		public Boolean MaiorCompraIsKey() {
			return false;
		}

		public Integer MaiorCompraLength() {
			return 15;
		}

		public Integer MaiorCompraPrecision() {
			return 0;
		}

		public String MaiorCompraDefault() {

			return "";

		}

		public String MaiorCompraComment() {

			return "";

		}

		public String MaiorCompraPattern() {

			return "";

		}

		public String MaiorCompraOriginalDbColumnName() {

			return "MaiorCompra";

		}

		public Double MediaAtraso;

		public Double getMediaAtraso() {
			return this.MediaAtraso;
		}

		public Boolean MediaAtrasoIsNullable() {
			return true;
		}

		public Boolean MediaAtrasoIsKey() {
			return false;
		}

		public Integer MediaAtrasoLength() {
			return 15;
		}

		public Integer MediaAtrasoPrecision() {
			return 0;
		}

		public String MediaAtrasoDefault() {

			return "";

		}

		public String MediaAtrasoComment() {

			return "";

		}

		public String MediaAtrasoPattern() {

			return "";

		}

		public String MediaAtrasoOriginalDbColumnName() {

			return "MediaAtraso";

		}

		public java.util.Date PrimeiraCompra;

		public java.util.Date getPrimeiraCompra() {
			return this.PrimeiraCompra;
		}

		public Boolean PrimeiraCompraIsNullable() {
			return true;
		}

		public Boolean PrimeiraCompraIsKey() {
			return false;
		}

		public Integer PrimeiraCompraLength() {
			return 8;
		}

		public Integer PrimeiraCompraPrecision() {
			return 0;
		}

		public String PrimeiraCompraDefault() {

			return null;

		}

		public String PrimeiraCompraComment() {

			return "";

		}

		public String PrimeiraCompraPattern() {

			return "dd-MM-yyyy";

		}

		public String PrimeiraCompraOriginalDbColumnName() {

			return "PrimeiraCompra";

		}

		public java.util.Date UltimaCompra;

		public java.util.Date getUltimaCompra() {
			return this.UltimaCompra;
		}

		public Boolean UltimaCompraIsNullable() {
			return true;
		}

		public Boolean UltimaCompraIsKey() {
			return false;
		}

		public Integer UltimaCompraLength() {
			return 8;
		}

		public Integer UltimaCompraPrecision() {
			return 0;
		}

		public String UltimaCompraDefault() {

			return null;

		}

		public String UltimaCompraComment() {

			return "";

		}

		public String UltimaCompraPattern() {

			return "dd-MM-yyyy";

		}

		public String UltimaCompraOriginalDbColumnName() {

			return "UltimaCompra";

		}

		public Double NumeroCompras;

		public Double getNumeroCompras() {
			return this.NumeroCompras;
		}

		public Boolean NumeroComprasIsNullable() {
			return true;
		}

		public Boolean NumeroComprasIsKey() {
			return false;
		}

		public Integer NumeroComprasLength() {
			return 15;
		}

		public Integer NumeroComprasPrecision() {
			return 0;
		}

		public String NumeroComprasDefault() {

			return "";

		}

		public String NumeroComprasComment() {

			return "";

		}

		public String NumeroComprasPattern() {

			return "";

		}

		public String NumeroComprasOriginalDbColumnName() {

			return "NumeroCompras";

		}

		public String FormularioVisita;

		public String getFormularioVisita() {
			return this.FormularioVisita;
		}

		public Boolean FormularioVisitaIsNullable() {
			return true;
		}

		public Boolean FormularioVisitaIsKey() {
			return false;
		}

		public Integer FormularioVisitaLength() {
			return 3;
		}

		public Integer FormularioVisitaPrecision() {
			return 0;
		}

		public String FormularioVisitaDefault() {

			return null;

		}

		public String FormularioVisitaComment() {

			return "";

		}

		public String FormularioVisitaPattern() {

			return "";

		}

		public String FormularioVisitaOriginalDbColumnName() {

			return "FormularioVisita";

		}

		public Double TempoVisita;

		public Double getTempoVisita() {
			return this.TempoVisita;
		}

		public Boolean TempoVisitaIsNullable() {
			return true;
		}

		public Boolean TempoVisitaIsKey() {
			return false;
		}

		public Integer TempoVisitaLength() {
			return 15;
		}

		public Integer TempoVisitaPrecision() {
			return 0;
		}

		public String TempoVisitaDefault() {

			return "";

		}

		public String TempoVisitaComment() {

			return "";

		}

		public String TempoVisitaPattern() {

			return "";

		}

		public String TempoVisitaOriginalDbColumnName() {

			return "TempoVisita";

		}

		public String UltimaVisita;

		public String getUltimaVisita() {
			return this.UltimaVisita;
		}

		public Boolean UltimaVisitaIsNullable() {
			return true;
		}

		public Boolean UltimaVisitaIsKey() {
			return false;
		}

		public Integer UltimaVisitaLength() {
			return 8;
		}

		public Integer UltimaVisitaPrecision() {
			return 0;
		}

		public String UltimaVisitaDefault() {

			return null;

		}

		public String UltimaVisitaComment() {

			return "";

		}

		public String UltimaVisitaPattern() {

			return "";

		}

		public String UltimaVisitaOriginalDbColumnName() {

			return "UltimaVisita";

		}

		public String TempoVisita1;

		public String getTempoVisita1() {
			return this.TempoVisita1;
		}

		public Boolean TempoVisita1IsNullable() {
			return true;
		}

		public Boolean TempoVisita1IsKey() {
			return false;
		}

		public Integer TempoVisita1Length() {
			return 5;
		}

		public Integer TempoVisita1Precision() {
			return 0;
		}

		public String TempoVisita1Default() {

			return null;

		}

		public String TempoVisita1Comment() {

			return "";

		}

		public String TempoVisita1Pattern() {

			return "";

		}

		public String TempoVisita1OriginalDbColumnName() {

			return "TempoVisita";

		}

		public String TempoPadrao;

		public String getTempoPadrao() {
			return this.TempoPadrao;
		}

		public Boolean TempoPadraoIsNullable() {
			return true;
		}

		public Boolean TempoPadraoIsKey() {
			return false;
		}

		public Integer TempoPadraoLength() {
			return 5;
		}

		public Integer TempoPadraoPrecision() {
			return 0;
		}

		public String TempoPadraoDefault() {

			return null;

		}

		public String TempoPadraoComment() {

			return "";

		}

		public String TempoPadraoPattern() {

			return "";

		}

		public String TempoPadraoOriginalDbColumnName() {

			return "TempoPadrao";

		}

		public String ClassificacaoVenda;

		public String getClassificacaoVenda() {
			return this.ClassificacaoVenda;
		}

		public Boolean ClassificacaoVendaIsNullable() {
			return true;
		}

		public Boolean ClassificacaoVendaIsKey() {
			return false;
		}

		public Integer ClassificacaoVendaLength() {
			return 1;
		}

		public Integer ClassificacaoVendaPrecision() {
			return 0;
		}

		public String ClassificacaoVendaDefault() {

			return null;

		}

		public String ClassificacaoVendaComment() {

			return "";

		}

		public String ClassificacaoVendaPattern() {

			return "";

		}

		public String ClassificacaoVendaOriginalDbColumnName() {

			return "ClassificacaoVenda";

		}

		public String Mensagem;

		public String getMensagem() {
			return this.Mensagem;
		}

		public Boolean MensagemIsNullable() {
			return true;
		}

		public Boolean MensagemIsKey() {
			return false;
		}

		public Integer MensagemLength() {
			return 3;
		}

		public Integer MensagemPrecision() {
			return 0;
		}

		public String MensagemDefault() {

			return null;

		}

		public String MensagemComment() {

			return "";

		}

		public String MensagemPattern() {

			return "";

		}

		public String MensagemOriginalDbColumnName() {

			return "Mensagem";

		}

		public Double SaldoTitulo;

		public Double getSaldoTitulo() {
			return this.SaldoTitulo;
		}

		public Boolean SaldoTituloIsNullable() {
			return true;
		}

		public Boolean SaldoTituloIsKey() {
			return false;
		}

		public Integer SaldoTituloLength() {
			return 15;
		}

		public Integer SaldoTituloPrecision() {
			return 0;
		}

		public String SaldoTituloDefault() {

			return "";

		}

		public String SaldoTituloComment() {

			return "";

		}

		public String SaldoTituloPattern() {

			return "";

		}

		public String SaldoTituloOriginalDbColumnName() {

			return "SaldoTitulo";

		}

		public String RecolheISS;

		public String getRecolheISS() {
			return this.RecolheISS;
		}

		public Boolean RecolheISSIsNullable() {
			return true;
		}

		public Boolean RecolheISSIsKey() {
			return false;
		}

		public Integer RecolheISSLength() {
			return 1;
		}

		public Integer RecolheISSPrecision() {
			return 0;
		}

		public String RecolheISSDefault() {

			return null;

		}

		public String RecolheISSComment() {

			return "";

		}

		public String RecolheISSPattern() {

			return "";

		}

		public String RecolheISSOriginalDbColumnName() {

			return "RecolheISS";

		}

		public Double SaldoPedidoLiberado;

		public Double getSaldoPedidoLiberado() {
			return this.SaldoPedidoLiberado;
		}

		public Boolean SaldoPedidoLiberadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoLiberadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLiberadoLength() {
			return 15;
		}

		public Integer SaldoPedidoLiberadoPrecision() {
			return 0;
		}

		public String SaldoPedidoLiberadoDefault() {

			return "";

		}

		public String SaldoPedidoLiberadoComment() {

			return "";

		}

		public String SaldoPedidoLiberadoPattern() {

			return "";

		}

		public String SaldoPedidoLiberadoOriginalDbColumnName() {

			return "SaldoPedidoLiberado";

		}

		public Double NumeroPagamentos;

		public Double getNumeroPagamentos() {
			return this.NumeroPagamentos;
		}

		public Boolean NumeroPagamentosIsNullable() {
			return true;
		}

		public Boolean NumeroPagamentosIsKey() {
			return false;
		}

		public Integer NumeroPagamentosLength() {
			return 15;
		}

		public Integer NumeroPagamentosPrecision() {
			return 0;
		}

		public String NumeroPagamentosDefault() {

			return "";

		}

		public String NumeroPagamentosComment() {

			return "";

		}

		public String NumeroPagamentosPattern() {

			return "";

		}

		public String NumeroPagamentosOriginalDbColumnName() {

			return "NumeroPagamentos";

		}

		public String Transferencia;

		public String getTransferencia() {
			return this.Transferencia;
		}

		public Boolean TransferenciaIsNullable() {
			return true;
		}

		public Boolean TransferenciaIsKey() {
			return false;
		}

		public Integer TransferenciaLength() {
			return 1;
		}

		public Integer TransferenciaPrecision() {
			return 0;
		}

		public String TransferenciaDefault() {

			return null;

		}

		public String TransferenciaComment() {

			return "";

		}

		public String TransferenciaPattern() {

			return "";

		}

		public String TransferenciaOriginalDbColumnName() {

			return "Transferencia";

		}

		public String SUFRAMA;

		public String getSUFRAMA() {
			return this.SUFRAMA;
		}

		public Boolean SUFRAMAIsNullable() {
			return true;
		}

		public Boolean SUFRAMAIsKey() {
			return false;
		}

		public Integer SUFRAMALength() {
			return 12;
		}

		public Integer SUFRAMAPrecision() {
			return 0;
		}

		public String SUFRAMADefault() {

			return null;

		}

		public String SUFRAMAComment() {

			return "";

		}

		public String SUFRAMAPattern() {

			return "";

		}

		public String SUFRAMAOriginalDbColumnName() {

			return "SUFRAMA";

		}

		public Double ATR;

		public Double getATR() {
			return this.ATR;
		}

		public Boolean ATRIsNullable() {
			return true;
		}

		public Boolean ATRIsKey() {
			return false;
		}

		public Integer ATRLength() {
			return 15;
		}

		public Integer ATRPrecision() {
			return 0;
		}

		public String ATRDefault() {

			return "";

		}

		public String ATRComment() {

			return "";

		}

		public String ATRPattern() {

			return "";

		}

		public String ATROriginalDbColumnName() {

			return "ATR";

		}

		public Double ValorAcumulado;

		public Double getValorAcumulado() {
			return this.ValorAcumulado;
		}

		public Boolean ValorAcumuladoIsNullable() {
			return true;
		}

		public Boolean ValorAcumuladoIsKey() {
			return false;
		}

		public Integer ValorAcumuladoLength() {
			return 15;
		}

		public Integer ValorAcumuladoPrecision() {
			return 0;
		}

		public String ValorAcumuladoDefault() {

			return "";

		}

		public String ValorAcumuladoComment() {

			return "";

		}

		public String ValorAcumuladoPattern() {

			return "";

		}

		public String ValorAcumuladoOriginalDbColumnName() {

			return "ValorAcumulado";

		}

		public Double SaldoPedido;

		public Double getSaldoPedido() {
			return this.SaldoPedido;
		}

		public Boolean SaldoPedidoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLength() {
			return 15;
		}

		public Integer SaldoPedidoPrecision() {
			return 0;
		}

		public String SaldoPedidoDefault() {

			return "";

		}

		public String SaldoPedidoComment() {

			return "";

		}

		public String SaldoPedidoPattern() {

			return "";

		}

		public String SaldoPedidoOriginalDbColumnName() {

			return "SaldoPedido";

		}

		public Double TituloProtestado;

		public Double getTituloProtestado() {
			return this.TituloProtestado;
		}

		public Boolean TituloProtestadoIsNullable() {
			return true;
		}

		public Boolean TituloProtestadoIsKey() {
			return false;
		}

		public Integer TituloProtestadoLength() {
			return 15;
		}

		public Integer TituloProtestadoPrecision() {
			return 0;
		}

		public String TituloProtestadoDefault() {

			return "";

		}

		public String TituloProtestadoComment() {

			return "";

		}

		public String TituloProtestadoPattern() {

			return "";

		}

		public String TituloProtestadoOriginalDbColumnName() {

			return "TituloProtestado";

		}

		public java.util.Date DataUltimoTitulo;

		public java.util.Date getDataUltimoTitulo() {
			return this.DataUltimoTitulo;
		}

		public Boolean DataUltimoTituloIsNullable() {
			return true;
		}

		public Boolean DataUltimoTituloIsKey() {
			return false;
		}

		public Integer DataUltimoTituloLength() {
			return 8;
		}

		public Integer DataUltimoTituloPrecision() {
			return 0;
		}

		public String DataUltimoTituloDefault() {

			return null;

		}

		public String DataUltimoTituloComment() {

			return "";

		}

		public String DataUltimoTituloPattern() {

			return "dd-MM-yyyy";

		}

		public String DataUltimoTituloOriginalDbColumnName() {

			return "DataUltimoTitulo";

		}

		public Double ChequeDevolvido;

		public Double getChequeDevolvido() {
			return this.ChequeDevolvido;
		}

		public Boolean ChequeDevolvidoIsNullable() {
			return true;
		}

		public Boolean ChequeDevolvidoIsKey() {
			return false;
		}

		public Integer ChequeDevolvidoLength() {
			return 15;
		}

		public Integer ChequeDevolvidoPrecision() {
			return 0;
		}

		public String ChequeDevolvidoDefault() {

			return "";

		}

		public String ChequeDevolvidoComment() {

			return "";

		}

		public String ChequeDevolvidoPattern() {

			return "";

		}

		public String ChequeDevolvidoOriginalDbColumnName() {

			return "ChequeDevolvido";

		}

		public String CaixaPostal;

		public String getCaixaPostal() {
			return this.CaixaPostal;
		}

		public Boolean CaixaPostalIsNullable() {
			return true;
		}

		public Boolean CaixaPostalIsKey() {
			return false;
		}

		public Integer CaixaPostalLength() {
			return 20;
		}

		public Integer CaixaPostalPrecision() {
			return 0;
		}

		public String CaixaPostalDefault() {

			return null;

		}

		public String CaixaPostalComment() {

			return "";

		}

		public String CaixaPostalPattern() {

			return "";

		}

		public String CaixaPostalOriginalDbColumnName() {

			return "CaixaPostal";

		}

		public Double Matriz;

		public Double getMatriz() {
			return this.Matriz;
		}

		public Boolean MatrizIsNullable() {
			return true;
		}

		public Boolean MatrizIsKey() {
			return false;
		}

		public Integer MatrizLength() {
			return 15;
		}

		public Integer MatrizPrecision() {
			return 0;
		}

		public String MatrizDefault() {

			return "";

		}

		public String MatrizComment() {

			return "";

		}

		public String MatrizPattern() {

			return "";

		}

		public String MatrizOriginalDbColumnName() {

			return "Matriz";

		}

		public String DataUltimoCheque;

		public String getDataUltimoCheque() {
			return this.DataUltimoCheque;
		}

		public Boolean DataUltimoChequeIsNullable() {
			return true;
		}

		public Boolean DataUltimoChequeIsKey() {
			return false;
		}

		public Integer DataUltimoChequeLength() {
			return 8;
		}

		public Integer DataUltimoChequePrecision() {
			return 0;
		}

		public String DataUltimoChequeDefault() {

			return null;

		}

		public String DataUltimoChequeComment() {

			return "";

		}

		public String DataUltimoChequePattern() {

			return "";

		}

		public String DataUltimoChequeOriginalDbColumnName() {

			return "DataUltimoCheque";

		}

		public Double MaiorDuplicata;

		public Double getMaiorDuplicata() {
			return this.MaiorDuplicata;
		}

		public Boolean MaiorDuplicataIsNullable() {
			return true;
		}

		public Boolean MaiorDuplicataIsKey() {
			return false;
		}

		public Integer MaiorDuplicataLength() {
			return 15;
		}

		public Integer MaiorDuplicataPrecision() {
			return 0;
		}

		public String MaiorDuplicataDefault() {

			return "";

		}

		public String MaiorDuplicataComment() {

			return "";

		}

		public String MaiorDuplicataPattern() {

			return "";

		}

		public String MaiorDuplicataOriginalDbColumnName() {

			return "MaiorDuplicata";

		}

		public String Tabela;

		public String getTabela() {
			return this.Tabela;
		}

		public Boolean TabelaIsNullable() {
			return true;
		}

		public Boolean TabelaIsKey() {
			return false;
		}

		public Integer TabelaLength() {
			return 3;
		}

		public Integer TabelaPrecision() {
			return 0;
		}

		public String TabelaDefault() {

			return null;

		}

		public String TabelaComment() {

			return "";

		}

		public String TabelaPattern() {

			return "";

		}

		public String TabelaOriginalDbColumnName() {

			return "Tabela";

		}

		public String ISSIncluso;

		public String getISSIncluso() {
			return this.ISSIncluso;
		}

		public Boolean ISSInclusoIsNullable() {
			return true;
		}

		public Boolean ISSInclusoIsKey() {
			return false;
		}

		public Integer ISSInclusoLength() {
			return 1;
		}

		public Integer ISSInclusoPrecision() {
			return 0;
		}

		public String ISSInclusoDefault() {

			return null;

		}

		public String ISSInclusoComment() {

			return "";

		}

		public String ISSInclusoPattern() {

			return "";

		}

		public String ISSInclusoOriginalDbColumnName() {

			return "ISSIncluso";

		}

		public Double SaldoDuplicataMoeda;

		public Double getSaldoDuplicataMoeda() {
			return this.SaldoDuplicataMoeda;
		}

		public Boolean SaldoDuplicataMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoDuplicataMoedaIsKey() {
			return false;
		}

		public Integer SaldoDuplicataMoedaLength() {
			return 15;
		}

		public Integer SaldoDuplicataMoedaPrecision() {
			return 0;
		}

		public String SaldoDuplicataMoedaDefault() {

			return "";

		}

		public String SaldoDuplicataMoedaComment() {

			return "";

		}

		public String SaldoDuplicataMoedaPattern() {

			return "";

		}

		public String SaldoDuplicataMoedaOriginalDbColumnName() {

			return "SaldoDuplicataMoeda";

		}

		public Double PagamentoAtrasado;

		public Double getPagamentoAtrasado() {
			return this.PagamentoAtrasado;
		}

		public Boolean PagamentoAtrasadoIsNullable() {
			return true;
		}

		public Boolean PagamentoAtrasadoIsKey() {
			return false;
		}

		public Integer PagamentoAtrasadoLength() {
			return 15;
		}

		public Integer PagamentoAtrasadoPrecision() {
			return 0;
		}

		public String PagamentoAtrasadoDefault() {

			return "";

		}

		public String PagamentoAtrasadoComment() {

			return "";

		}

		public String PagamentoAtrasadoPattern() {

			return "";

		}

		public String PagamentoAtrasadoOriginalDbColumnName() {

			return "PagamentoAtrasado";

		}

		public String Atividade;

		public String getAtividade() {
			return this.Atividade;
		}

		public Boolean AtividadeIsNullable() {
			return true;
		}

		public Boolean AtividadeIsKey() {
			return false;
		}

		public Integer AtividadeLength() {
			return 7;
		}

		public Integer AtividadePrecision() {
			return 0;
		}

		public String AtividadeDefault() {

			return null;

		}

		public String AtividadeComment() {

			return "";

		}

		public String AtividadePattern() {

			return "";

		}

		public String AtividadeOriginalDbColumnName() {

			return "Atividade";

		}

		public String Cargo1;

		public String getCargo1() {
			return this.Cargo1;
		}

		public Boolean Cargo1IsNullable() {
			return true;
		}

		public Boolean Cargo1IsKey() {
			return false;
		}

		public Integer Cargo1Length() {
			return 15;
		}

		public Integer Cargo1Precision() {
			return 0;
		}

		public String Cargo1Default() {

			return null;

		}

		public String Cargo1Comment() {

			return "";

		}

		public String Cargo1Pattern() {

			return "";

		}

		public String Cargo1OriginalDbColumnName() {

			return "Cargo1";

		}

		public String Cargo2;

		public String getCargo2() {
			return this.Cargo2;
		}

		public Boolean Cargo2IsNullable() {
			return true;
		}

		public Boolean Cargo2IsKey() {
			return false;
		}

		public Integer Cargo2Length() {
			return 15;
		}

		public Integer Cargo2Precision() {
			return 0;
		}

		public String Cargo2Default() {

			return null;

		}

		public String Cargo2Comment() {

			return "";

		}

		public String Cargo2Pattern() {

			return "";

		}

		public String Cargo2OriginalDbColumnName() {

			return "Cargo2";

		}

		public String Cargo3;

		public String getCargo3() {
			return this.Cargo3;
		}

		public Boolean Cargo3IsNullable() {
			return true;
		}

		public Boolean Cargo3IsKey() {
			return false;
		}

		public Integer Cargo3Length() {
			return 15;
		}

		public Integer Cargo3Precision() {
			return 0;
		}

		public String Cargo3Default() {

			return null;

		}

		public String Cargo3Comment() {

			return "";

		}

		public String Cargo3Pattern() {

			return "";

		}

		public String Cargo3OriginalDbColumnName() {

			return "Cargo3";

		}

		public String RepresentanteTecnico;

		public String getRepresentanteTecnico() {
			return this.RepresentanteTecnico;
		}

		public Boolean RepresentanteTecnicoIsNullable() {
			return true;
		}

		public Boolean RepresentanteTecnicoIsKey() {
			return false;
		}

		public Integer RepresentanteTecnicoLength() {
			return 6;
		}

		public Integer RepresentanteTecnicoPrecision() {
			return 0;
		}

		public String RepresentanteTecnicoDefault() {

			return null;

		}

		public String RepresentanteTecnicoComment() {

			return "";

		}

		public String RepresentanteTecnicoPattern() {

			return "";

		}

		public String RepresentanteTecnicoOriginalDbColumnName() {

			return "RepresentanteTecnico";

		}

		public String Supervisor;

		public String getSupervisor() {
			return this.Supervisor;
		}

		public Boolean SupervisorIsNullable() {
			return true;
		}

		public Boolean SupervisorIsKey() {
			return false;
		}

		public Integer SupervisorLength() {
			return 6;
		}

		public Integer SupervisorPrecision() {
			return 0;
		}

		public String SupervisorDefault() {

			return null;

		}

		public String SupervisorComment() {

			return "";

		}

		public String SupervisorPattern() {

			return "";

		}

		public String SupervisorOriginalDbColumnName() {

			return "Supervisor";

		}

		public Double AliquotaIR;

		public Double getAliquotaIR() {
			return this.AliquotaIR;
		}

		public Boolean AliquotaIRIsNullable() {
			return true;
		}

		public Boolean AliquotaIRIsKey() {
			return false;
		}

		public Integer AliquotaIRLength() {
			return 15;
		}

		public Integer AliquotaIRPrecision() {
			return 0;
		}

		public String AliquotaIRDefault() {

			return "";

		}

		public String AliquotaIRComment() {

			return "";

		}

		public String AliquotaIRPattern() {

			return "";

		}

		public String AliquotaIROriginalDbColumnName() {

			return "AliquotaIR";

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
			return 40;
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

		public String RG;

		public String getRG() {
			return this.RG;
		}

		public Boolean RGIsNullable() {
			return true;
		}

		public Boolean RGIsKey() {
			return false;
		}

		public Integer RGLength() {
			return 15;
		}

		public Integer RGPrecision() {
			return 0;
		}

		public String RGDefault() {

			return null;

		}

		public String RGComment() {

			return "";

		}

		public String RGPattern() {

			return "";

		}

		public String RGOriginalDbColumnName() {

			return "RG";

		}

		public String CalculaSUFRAMA;

		public String getCalculaSUFRAMA() {
			return this.CalculaSUFRAMA;
		}

		public Boolean CalculaSUFRAMAIsNullable() {
			return true;
		}

		public Boolean CalculaSUFRAMAIsKey() {
			return false;
		}

		public Integer CalculaSUFRAMALength() {
			return 1;
		}

		public Integer CalculaSUFRAMAPrecision() {
			return 0;
		}

		public String CalculaSUFRAMADefault() {

			return null;

		}

		public String CalculaSUFRAMAComment() {

			return "";

		}

		public String CalculaSUFRAMAPattern() {

			return "";

		}

		public String CalculaSUFRAMAOriginalDbColumnName() {

			return "CalculaSUFRAMA";

		}

		public java.util.Date DataNascimento;

		public java.util.Date getDataNascimento() {
			return this.DataNascimento;
		}

		public Boolean DataNascimentoIsNullable() {
			return true;
		}

		public Boolean DataNascimentoIsKey() {
			return false;
		}

		public Integer DataNascimentoLength() {
			return 8;
		}

		public Integer DataNascimentoPrecision() {
			return 0;
		}

		public String DataNascimentoDefault() {

			return null;

		}

		public String DataNascimentoComment() {

			return "";

		}

		public String DataNascimentoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataNascimentoOriginalDbColumnName() {

			return "DataNascimento";

		}

		public Double SaldoPedidoBloqueado;

		public Double getSaldoPedidoBloqueado() {
			return this.SaldoPedidoBloqueado;
		}

		public Boolean SaldoPedidoBloqueadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoBloqueadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoBloqueadoLength() {
			return 15;
		}

		public Integer SaldoPedidoBloqueadoPrecision() {
			return 0;
		}

		public String SaldoPedidoBloqueadoDefault() {

			return "";

		}

		public String SaldoPedidoBloqueadoComment() {

			return "";

		}

		public String SaldoPedidoBloqueadoPattern() {

			return "";

		}

		public String SaldoPedidoBloqueadoOriginalDbColumnName() {

			return "SaldoPedidoBloqueado";

		}

		public String GrupoTributario;

		public String getGrupoTributario() {
			return this.GrupoTributario;
		}

		public Boolean GrupoTributarioIsNullable() {
			return true;
		}

		public Boolean GrupoTributarioIsKey() {
			return false;
		}

		public Integer GrupoTributarioLength() {
			return 3;
		}

		public Integer GrupoTributarioPrecision() {
			return 0;
		}

		public String GrupoTributarioDefault() {

			return null;

		}

		public String GrupoTributarioComment() {

			return "";

		}

		public String GrupoTributarioPattern() {

			return "";

		}

		public String GrupoTributarioOriginalDbColumnName() {

			return "GrupoTributario";

		}

		public String SegmentoAtividade1;

		public String getSegmentoAtividade1() {
			return this.SegmentoAtividade1;
		}

		public Boolean SegmentoAtividade1IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade1IsKey() {
			return false;
		}

		public Integer SegmentoAtividade1Length() {
			return 6;
		}

		public Integer SegmentoAtividade1Precision() {
			return 0;
		}

		public String SegmentoAtividade1Default() {

			return null;

		}

		public String SegmentoAtividade1Comment() {

			return "";

		}

		public String SegmentoAtividade1Pattern() {

			return "";

		}

		public String SegmentoAtividade1OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String ClienteFaturamento;

		public String getClienteFaturamento() {
			return this.ClienteFaturamento;
		}

		public Boolean ClienteFaturamentoIsNullable() {
			return true;
		}

		public Boolean ClienteFaturamentoIsKey() {
			return false;
		}

		public Integer ClienteFaturamentoLength() {
			return 9;
		}

		public Integer ClienteFaturamentoPrecision() {
			return 0;
		}

		public String ClienteFaturamentoDefault() {

			return null;

		}

		public String ClienteFaturamentoComment() {

			return "";

		}

		public String ClienteFaturamentoPattern() {

			return "";

		}

		public String ClienteFaturamentoOriginalDbColumnName() {

			return "ClienteFaturamento";

		}

		public String EnderecoEntrega;

		public String getEnderecoEntrega() {
			return this.EnderecoEntrega;
		}

		public Boolean EnderecoEntregaIsNullable() {
			return true;
		}

		public Boolean EnderecoEntregaIsKey() {
			return false;
		}

		public Integer EnderecoEntregaLength() {
			return 80;
		}

		public Integer EnderecoEntregaPrecision() {
			return 0;
		}

		public String EnderecoEntregaDefault() {

			return null;

		}

		public String EnderecoEntregaComment() {

			return "";

		}

		public String EnderecoEntregaPattern() {

			return "";

		}

		public String EnderecoEntregaOriginalDbColumnName() {

			return "EnderecoEntrega";

		}

		public String UCompleto;

		public String getUCompleto() {
			return this.UCompleto;
		}

		public Boolean UCompletoIsNullable() {
			return true;
		}

		public Boolean UCompletoIsKey() {
			return false;
		}

		public Integer UCompletoLength() {
			return 50;
		}

		public Integer UCompletoPrecision() {
			return 0;
		}

		public String UCompletoDefault() {

			return null;

		}

		public String UCompletoComment() {

			return "";

		}

		public String UCompletoPattern() {

			return "";

		}

		public String UCompletoOriginalDbColumnName() {

			return "UCompleto";

		}

		public String BairroEntrega;

		public String getBairroEntrega() {
			return this.BairroEntrega;
		}

		public Boolean BairroEntregaIsNullable() {
			return true;
		}

		public Boolean BairroEntregaIsKey() {
			return false;
		}

		public Integer BairroEntregaLength() {
			return 20;
		}

		public Integer BairroEntregaPrecision() {
			return 0;
		}

		public String BairroEntregaDefault() {

			return null;

		}

		public String BairroEntregaComment() {

			return "";

		}

		public String BairroEntregaPattern() {

			return "";

		}

		public String BairroEntregaOriginalDbColumnName() {

			return "BairroEntrega";

		}

		public String CEPEntrada;

		public String getCEPEntrada() {
			return this.CEPEntrada;
		}

		public Boolean CEPEntradaIsNullable() {
			return true;
		}

		public Boolean CEPEntradaIsKey() {
			return false;
		}

		public Integer CEPEntradaLength() {
			return 8;
		}

		public Integer CEPEntradaPrecision() {
			return 0;
		}

		public String CEPEntradaDefault() {

			return null;

		}

		public String CEPEntradaComment() {

			return "";

		}

		public String CEPEntradaPattern() {

			return "";

		}

		public String CEPEntradaOriginalDbColumnName() {

			return "CEPEntrada";

		}

		public String EstadoEntrada;

		public String getEstadoEntrada() {
			return this.EstadoEntrada;
		}

		public Boolean EstadoEntradaIsNullable() {
			return true;
		}

		public Boolean EstadoEntradaIsKey() {
			return false;
		}

		public Integer EstadoEntradaLength() {
			return 2;
		}

		public Integer EstadoEntradaPrecision() {
			return 0;
		}

		public String EstadoEntradaDefault() {

			return null;

		}

		public String EstadoEntradaComment() {

			return "";

		}

		public String EstadoEntradaPattern() {

			return "";

		}

		public String EstadoEntradaOriginalDbColumnName() {

			return "EstadoEntrada";

		}

		public String CodigoLocal;

		public String getCodigoLocal() {
			return this.CodigoLocal;
		}

		public Boolean CodigoLocalIsNullable() {
			return true;
		}

		public Boolean CodigoLocalIsKey() {
			return false;
		}

		public Integer CodigoLocalLength() {
			return 8;
		}

		public Integer CodigoLocalPrecision() {
			return 0;
		}

		public String CodigoLocalDefault() {

			return null;

		}

		public String CodigoLocalComment() {

			return "";

		}

		public String CodigoLocalPattern() {

			return "";

		}

		public String CodigoLocalOriginalDbColumnName() {

			return "CodigoLocal";

		}

		public String SegmentoAtividade2;

		public String getSegmentoAtividade2() {
			return this.SegmentoAtividade2;
		}

		public Boolean SegmentoAtividade2IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade2IsKey() {
			return false;
		}

		public Integer SegmentoAtividade2Length() {
			return 6;
		}

		public Integer SegmentoAtividade2Precision() {
			return 0;
		}

		public String SegmentoAtividade2Default() {

			return null;

		}

		public String SegmentoAtividade2Comment() {

			return "";

		}

		public String SegmentoAtividade2Pattern() {

			return "";

		}

		public String SegmentoAtividade2OriginalDbColumnName() {

			return "SegmentoAtividade2";

		}

		public String TipoPessoa;

		public String getTipoPessoa() {
			return this.TipoPessoa;
		}

		public Boolean TipoPessoaIsNullable() {
			return true;
		}

		public Boolean TipoPessoaIsKey() {
			return false;
		}

		public Integer TipoPessoaLength() {
			return 2;
		}

		public Integer TipoPessoaPrecision() {
			return 0;
		}

		public String TipoPessoaDefault() {

			return null;

		}

		public String TipoPessoaComment() {

			return "";

		}

		public String TipoPessoaPattern() {

			return "";

		}

		public String TipoPessoaOriginalDbColumnName() {

			return "TipoPessoa";

		}

		public String TipoISSRS;

		public String getTipoISSRS() {
			return this.TipoISSRS;
		}

		public Boolean TipoISSRSIsNullable() {
			return true;
		}

		public Boolean TipoISSRSIsKey() {
			return false;
		}

		public Integer TipoISSRSLength() {
			return 2;
		}

		public Integer TipoISSRSPrecision() {
			return 0;
		}

		public String TipoISSRSDefault() {

			return null;

		}

		public String TipoISSRSComment() {

			return "";

		}

		public String TipoISSRSPattern() {

			return "";

		}

		public String TipoISSRSOriginalDbColumnName() {

			return "TipoISSRS";

		}

		public String SegmentoAtividade3;

		public String getSegmentoAtividade3() {
			return this.SegmentoAtividade3;
		}

		public Boolean SegmentoAtividade3IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade3IsKey() {
			return false;
		}

		public Integer SegmentoAtividade3Length() {
			return 6;
		}

		public Integer SegmentoAtividade3Precision() {
			return 0;
		}

		public String SegmentoAtividade3Default() {

			return null;

		}

		public String SegmentoAtividade3Comment() {

			return "";

		}

		public String SegmentoAtividade3Pattern() {

			return "";

		}

		public String SegmentoAtividade3OriginalDbColumnName() {

			return "SegmentoAtividade3";

		}

		public String SegmentoAtividade4;

		public String getSegmentoAtividade4() {
			return this.SegmentoAtividade4;
		}

		public Boolean SegmentoAtividade4IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade4IsKey() {
			return false;
		}

		public Integer SegmentoAtividade4Length() {
			return 6;
		}

		public Integer SegmentoAtividade4Precision() {
			return 0;
		}

		public String SegmentoAtividade4Default() {

			return null;

		}

		public String SegmentoAtividade4Comment() {

			return "";

		}

		public String SegmentoAtividade4Pattern() {

			return "";

		}

		public String SegmentoAtividade4OriginalDbColumnName() {

			return "SegmentoAtividade4";

		}

		public String SegmentoAtividade5;

		public String getSegmentoAtividade5() {
			return this.SegmentoAtividade5;
		}

		public Boolean SegmentoAtividade5IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade5IsKey() {
			return false;
		}

		public Integer SegmentoAtividade5Length() {
			return 6;
		}

		public Integer SegmentoAtividade5Precision() {
			return 0;
		}

		public String SegmentoAtividade5Default() {

			return null;

		}

		public String SegmentoAtividade5Comment() {

			return "";

		}

		public String SegmentoAtividade5Pattern() {

			return "";

		}

		public String SegmentoAtividade5OriginalDbColumnName() {

			return "SegmentoAtividade5";

		}

		public String SegmentoAtividade6;

		public String getSegmentoAtividade6() {
			return this.SegmentoAtividade6;
		}

		public Boolean SegmentoAtividade6IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade6IsKey() {
			return false;
		}

		public Integer SegmentoAtividade6Length() {
			return 6;
		}

		public Integer SegmentoAtividade6Precision() {
			return 0;
		}

		public String SegmentoAtividade6Default() {

			return null;

		}

		public String SegmentoAtividade6Comment() {

			return "";

		}

		public String SegmentoAtividade6Pattern() {

			return "";

		}

		public String SegmentoAtividade6OriginalDbColumnName() {

			return "SegmentoAtividade6";

		}

		public String SegmentoAtividade7;

		public String getSegmentoAtividade7() {
			return this.SegmentoAtividade7;
		}

		public Boolean SegmentoAtividade7IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade7IsKey() {
			return false;
		}

		public Integer SegmentoAtividade7Length() {
			return 6;
		}

		public Integer SegmentoAtividade7Precision() {
			return 0;
		}

		public String SegmentoAtividade7Default() {

			return null;

		}

		public String SegmentoAtividade7Comment() {

			return "";

		}

		public String SegmentoAtividade7Pattern() {

			return "";

		}

		public String SegmentoAtividade7OriginalDbColumnName() {

			return "SegmentoAtividade7";

		}

		public String CodigoAgente;

		public String getCodigoAgente() {
			return this.CodigoAgente;
		}

		public Boolean CodigoAgenteIsNullable() {
			return true;
		}

		public Boolean CodigoAgenteIsKey() {
			return false;
		}

		public Integer CodigoAgenteLength() {
			return 9;
		}

		public Integer CodigoAgentePrecision() {
			return 0;
		}

		public String CodigoAgenteDefault() {

			return null;

		}

		public String CodigoAgenteComment() {

			return "";

		}

		public String CodigoAgentePattern() {

			return "";

		}

		public String CodigoAgenteOriginalDbColumnName() {

			return "CodigoAgente";

		}

		public String SegmentoAtividade8;

		public String getSegmentoAtividade8() {
			return this.SegmentoAtividade8;
		}

		public Boolean SegmentoAtividade8IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade8IsKey() {
			return false;
		}

		public Integer SegmentoAtividade8Length() {
			return 6;
		}

		public Integer SegmentoAtividade8Precision() {
			return 0;
		}

		public String SegmentoAtividade8Default() {

			return null;

		}

		public String SegmentoAtividade8Comment() {

			return "";

		}

		public String SegmentoAtividade8Pattern() {

			return "";

		}

		public String SegmentoAtividade8OriginalDbColumnName() {

			return "SegmentoAtividade8";

		}

		public String CodigoMarcacao;

		public String getCodigoMarcacao() {
			return this.CodigoMarcacao;
		}

		public Boolean CodigoMarcacaoIsNullable() {
			return true;
		}

		public Boolean CodigoMarcacaoIsKey() {
			return false;
		}

		public Integer CodigoMarcacaoLength() {
			return 6;
		}

		public Integer CodigoMarcacaoPrecision() {
			return 0;
		}

		public String CodigoMarcacaoDefault() {

			return null;

		}

		public String CodigoMarcacaoComment() {

			return "";

		}

		public String CodigoMarcacaoPattern() {

			return "";

		}

		public String CodigoMarcacaoOriginalDbColumnName() {

			return "CodigoMarcacao";

		}

		public Double ComissaoAgente;

		public Double getComissaoAgente() {
			return this.ComissaoAgente;
		}

		public Boolean ComissaoAgenteIsNullable() {
			return true;
		}

		public Boolean ComissaoAgenteIsKey() {
			return false;
		}

		public Integer ComissaoAgenteLength() {
			return 15;
		}

		public Integer ComissaoAgentePrecision() {
			return 0;
		}

		public String ComissaoAgenteDefault() {

			return "";

		}

		public String ComissaoAgenteComment() {

			return "";

		}

		public String ComissaoAgentePattern() {

			return "";

		}

		public String ComissaoAgenteOriginalDbColumnName() {

			return "ComissaoAgente";

		}

		public String HomePage;

		public String getHomePage() {
			return this.HomePage;
		}

		public Boolean HomePageIsNullable() {
			return true;
		}

		public Boolean HomePageIsKey() {
			return false;
		}

		public Integer HomePageLength() {
			return 30;
		}

		public Integer HomePagePrecision() {
			return 0;
		}

		public String HomePageDefault() {

			return null;

		}

		public String HomePageComment() {

			return "";

		}

		public String HomePagePattern() {

			return "";

		}

		public String HomePageOriginalDbColumnName() {

			return "HomePage";

		}

		public String CodigoMunicipio1;

		public String getCodigoMunicipio1() {
			return this.CodigoMunicipio1;
		}

		public Boolean CodigoMunicipio1IsNullable() {
			return true;
		}

		public Boolean CodigoMunicipio1IsKey() {
			return false;
		}

		public Integer CodigoMunicipio1Length() {
			return 5;
		}

		public Integer CodigoMunicipio1Precision() {
			return 0;
		}

		public String CodigoMunicipio1Default() {

			return null;

		}

		public String CodigoMunicipio1Comment() {

			return "";

		}

		public String CodigoMunicipio1Pattern() {

			return "";

		}

		public String CodigoMunicipio1OriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String TipoCliente;

		public String getTipoCliente() {
			return this.TipoCliente;
		}

		public Boolean TipoClienteIsNullable() {
			return true;
		}

		public Boolean TipoClienteIsKey() {
			return false;
		}

		public Integer TipoClienteLength() {
			return 1;
		}

		public Integer TipoClientePrecision() {
			return 0;
		}

		public String TipoClienteDefault() {

			return null;

		}

		public String TipoClienteComment() {

			return "";

		}

		public String TipoClientePattern() {

			return "";

		}

		public String TipoClienteOriginalDbColumnName() {

			return "TipoCliente";

		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public Boolean EmailIsNullable() {
			return true;
		}

		public Boolean EmailIsKey() {
			return false;
		}

		public Integer EmailLength() {
			return 250;
		}

		public Integer EmailPrecision() {
			return 0;
		}

		public String EmailDefault() {

			return null;

		}

		public String EmailComment() {

			return "";

		}

		public String EmailPattern() {

			return "";

		}

		public String EmailOriginalDbColumnName() {

			return "Email";

		}

		public String Destino1;

		public String getDestino1() {
			return this.Destino1;
		}

		public Boolean Destino1IsNullable() {
			return true;
		}

		public Boolean Destino1IsKey() {
			return false;
		}

		public Integer Destino1Length() {
			return 3;
		}

		public Integer Destino1Precision() {
			return 0;
		}

		public String Destino1Default() {

			return null;

		}

		public String Destino1Comment() {

			return "";

		}

		public String Destino1Pattern() {

			return "";

		}

		public String Destino1OriginalDbColumnName() {

			return "Destino1";

		}

		public String Destino2;

		public String getDestino2() {
			return this.Destino2;
		}

		public Boolean Destino2IsNullable() {
			return true;
		}

		public Boolean Destino2IsKey() {
			return false;
		}

		public Integer Destino2Length() {
			return 3;
		}

		public Integer Destino2Precision() {
			return 0;
		}

		public String Destino2Default() {

			return null;

		}

		public String Destino2Comment() {

			return "";

		}

		public String Destino2Pattern() {

			return "";

		}

		public String Destino2OriginalDbColumnName() {

			return "Destino2";

		}

		public String Destino3;

		public String getDestino3() {
			return this.Destino3;
		}

		public Boolean Destino3IsNullable() {
			return true;
		}

		public Boolean Destino3IsKey() {
			return false;
		}

		public Integer Destino3Length() {
			return 3;
		}

		public Integer Destino3Precision() {
			return 0;
		}

		public String Destino3Default() {

			return null;

		}

		public String Destino3Comment() {

			return "";

		}

		public String Destino3Pattern() {

			return "";

		}

		public String Destino3OriginalDbColumnName() {

			return "Destino3";

		}

		public String CBO;

		public String getCBO() {
			return this.CBO;
		}

		public Boolean CBOIsNullable() {
			return true;
		}

		public Boolean CBOIsKey() {
			return false;
		}

		public Integer CBOLength() {
			return 7;
		}

		public Integer CBOPrecision() {
			return 0;
		}

		public String CBODefault() {

			return null;

		}

		public String CBOComment() {

			return "";

		}

		public String CBOPattern() {

			return "";

		}

		public String CBOOriginalDbColumnName() {

			return "CBO";

		}

		public String Observacao1;

		public String getObservacao1() {
			return this.Observacao1;
		}

		public Boolean Observacao1IsNullable() {
			return true;
		}

		public Boolean Observacao1IsKey() {
			return false;
		}

		public Integer Observacao1Length() {
			return 6;
		}

		public Integer Observacao1Precision() {
			return 0;
		}

		public String Observacao1Default() {

			return null;

		}

		public String Observacao1Comment() {

			return "";

		}

		public String Observacao1Pattern() {

			return "";

		}

		public String Observacao1OriginalDbColumnName() {

			return "Observacao";

		}

		public String CNAE;

		public String getCNAE() {
			return this.CNAE;
		}

		public Boolean CNAEIsNullable() {
			return true;
		}

		public Boolean CNAEIsKey() {
			return false;
		}

		public Integer CNAELength() {
			return 9;
		}

		public Integer CNAEPrecision() {
			return 0;
		}

		public String CNAEDefault() {

			return null;

		}

		public String CNAEComment() {

			return "";

		}

		public String CNAEPattern() {

			return "";

		}

		public String CNAEOriginalDbColumnName() {

			return "CNAE";

		}

		public String CodigoHistorico;

		public String getCodigoHistorico() {
			return this.CodigoHistorico;
		}

		public Boolean CodigoHistoricoIsNullable() {
			return true;
		}

		public Boolean CodigoHistoricoIsKey() {
			return false;
		}

		public Integer CodigoHistoricoLength() {
			return 6;
		}

		public Integer CodigoHistoricoPrecision() {
			return 0;
		}

		public String CodigoHistoricoDefault() {

			return null;

		}

		public String CodigoHistoricoComment() {

			return "";

		}

		public String CodigoHistoricoPattern() {

			return "";

		}

		public String CodigoHistoricoOriginalDbColumnName() {

			return "CodigoHistorico";

		}

		public String CondicaoPagamento;

		public String getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 5;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return null;

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

		public Double DiasPagamento;

		public Double getDiasPagamento() {
			return this.DiasPagamento;
		}

		public Boolean DiasPagamentoIsNullable() {
			return true;
		}

		public Boolean DiasPagamentoIsKey() {
			return false;
		}

		public Integer DiasPagamentoLength() {
			return 15;
		}

		public Integer DiasPagamentoPrecision() {
			return 0;
		}

		public String DiasPagamentoDefault() {

			return "";

		}

		public String DiasPagamentoComment() {

			return "";

		}

		public String DiasPagamentoPattern() {

			return "";

		}

		public String DiasPagamentoOriginalDbColumnName() {

			return "DiasPagamento";

		}

		public String Agregado;

		public String getAgregado() {
			return this.Agregado;
		}

		public Boolean AgregadoIsNullable() {
			return true;
		}

		public Boolean AgregadoIsKey() {
			return false;
		}

		public Integer AgregadoLength() {
			return 4;
		}

		public Integer AgregadoPrecision() {
			return 0;
		}

		public String AgregadoDefault() {

			return null;

		}

		public String AgregadoComment() {

			return "";

		}

		public String AgregadoPattern() {

			return "";

		}

		public String AgregadoOriginalDbColumnName() {

			return "Agregado";

		}

		public String RecolheINSS;

		public String getRecolheINSS() {
			return this.RecolheINSS;
		}

		public Boolean RecolheINSSIsNullable() {
			return true;
		}

		public Boolean RecolheINSSIsKey() {
			return false;
		}

		public Integer RecolheINSSLength() {
			return 1;
		}

		public Integer RecolheINSSPrecision() {
			return 0;
		}

		public String RecolheINSSDefault() {

			return null;

		}

		public String RecolheINSSComment() {

			return "";

		}

		public String RecolheINSSPattern() {

			return "";

		}

		public String RecolheINSSOriginalDbColumnName() {

			return "RecolheINSS";

		}

		public String RecolheCOFINS;

		public String getRecolheCOFINS() {
			return this.RecolheCOFINS;
		}

		public Boolean RecolheCOFINSIsNullable() {
			return true;
		}

		public Boolean RecolheCOFINSIsKey() {
			return false;
		}

		public Integer RecolheCOFINSLength() {
			return 1;
		}

		public Integer RecolheCOFINSPrecision() {
			return 0;
		}

		public String RecolheCOFINSDefault() {

			return null;

		}

		public String RecolheCOFINSComment() {

			return "";

		}

		public String RecolheCOFINSPattern() {

			return "";

		}

		public String RecolheCOFINSOriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL;

		public String getRecolheCSLL() {
			return this.RecolheCSLL;
		}

		public Boolean RecolheCSLLIsNullable() {
			return true;
		}

		public Boolean RecolheCSLLIsKey() {
			return false;
		}

		public Integer RecolheCSLLLength() {
			return 1;
		}

		public Integer RecolheCSLLPrecision() {
			return 0;
		}

		public String RecolheCSLLDefault() {

			return null;

		}

		public String RecolheCSLLComment() {

			return "";

		}

		public String RecolheCSLLPattern() {

			return "";

		}

		public String RecolheCSLLOriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String RecolhePIS;

		public String getRecolhePIS() {
			return this.RecolhePIS;
		}

		public Boolean RecolhePISIsNullable() {
			return true;
		}

		public Boolean RecolhePISIsKey() {
			return false;
		}

		public Integer RecolhePISLength() {
			return 1;
		}

		public Integer RecolhePISPrecision() {
			return 0;
		}

		public String RecolhePISDefault() {

			return null;

		}

		public String RecolhePISComment() {

			return "";

		}

		public String RecolhePISPattern() {

			return "";

		}

		public String RecolhePISOriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String TipoPeriodo;

		public String getTipoPeriodo() {
			return this.TipoPeriodo;
		}

		public Boolean TipoPeriodoIsNullable() {
			return true;
		}

		public Boolean TipoPeriodoIsKey() {
			return false;
		}

		public Integer TipoPeriodoLength() {
			return 2;
		}

		public Integer TipoPeriodoPrecision() {
			return 0;
		}

		public String TipoPeriodoDefault() {

			return null;

		}

		public String TipoPeriodoComment() {

			return "";

		}

		public String TipoPeriodoPattern() {

			return "";

		}

		public String TipoPeriodoOriginalDbColumnName() {

			return "TipoPeriodo";

		}

		public Double SaldoFinal;

		public Double getSaldoFinal() {
			return this.SaldoFinal;
		}

		public Boolean SaldoFinalIsNullable() {
			return true;
		}

		public Boolean SaldoFinalIsKey() {
			return false;
		}

		public Integer SaldoFinalLength() {
			return 15;
		}

		public Integer SaldoFinalPrecision() {
			return 0;
		}

		public String SaldoFinalDefault() {

			return "";

		}

		public String SaldoFinalComment() {

			return "";

		}

		public String SaldoFinalPattern() {

			return "";

		}

		public String SaldoFinalOriginalDbColumnName() {

			return "SaldoFinal";

		}

		public Double SaldoFinalMoeda;

		public Double getSaldoFinalMoeda() {
			return this.SaldoFinalMoeda;
		}

		public Boolean SaldoFinalMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoFinalMoedaIsKey() {
			return false;
		}

		public Integer SaldoFinalMoedaLength() {
			return 15;
		}

		public Integer SaldoFinalMoedaPrecision() {
			return 0;
		}

		public String SaldoFinalMoedaDefault() {

			return "";

		}

		public String SaldoFinalMoedaComment() {

			return "";

		}

		public String SaldoFinalMoedaPattern() {

			return "";

		}

		public String SaldoFinalMoedaOriginalDbColumnName() {

			return "SaldoFinalMoeda";

		}

		public String Contabilidade;

		public String getContabilidade() {
			return this.Contabilidade;
		}

		public Boolean ContabilidadeIsNullable() {
			return true;
		}

		public Boolean ContabilidadeIsKey() {
			return false;
		}

		public Integer ContabilidadeLength() {
			return 15;
		}

		public Integer ContabilidadePrecision() {
			return 0;
		}

		public String ContabilidadeDefault() {

			return null;

		}

		public String ContabilidadeComment() {

			return "";

		}

		public String ContabilidadePattern() {

			return "";

		}

		public String ContabilidadeOriginalDbColumnName() {

			return "Contabilidade";

		}

		public String ClienteConvenio;

		public String getClienteConvenio() {
			return this.ClienteConvenio;
		}

		public Boolean ClienteConvenioIsNullable() {
			return true;
		}

		public Boolean ClienteConvenioIsKey() {
			return false;
		}

		public Integer ClienteConvenioLength() {
			return 1;
		}

		public Integer ClienteConvenioPrecision() {
			return 0;
		}

		public String ClienteConvenioDefault() {

			return null;

		}

		public String ClienteConvenioComment() {

			return "";

		}

		public String ClienteConvenioPattern() {

			return "";

		}

		public String ClienteConvenioOriginalDbColumnName() {

			return "ClienteConvenio";

		}

		public String B2B;

		public String getB2B() {
			return this.B2B;
		}

		public Boolean B2BIsNullable() {
			return true;
		}

		public Boolean B2BIsKey() {
			return false;
		}

		public Integer B2BLength() {
			return 1;
		}

		public Integer B2BPrecision() {
			return 0;
		}

		public String B2BDefault() {

			return null;

		}

		public String B2BComment() {

			return "";

		}

		public String B2BPattern() {

			return "";

		}

		public String B2BOriginalDbColumnName() {

			return "B2B";

		}

		public String CBO1;

		public String getCBO1() {
			return this.CBO1;
		}

		public Boolean CBO1IsNullable() {
			return true;
		}

		public Boolean CBO1IsKey() {
			return false;
		}

		public Integer CBO1Length() {
			return 7;
		}

		public Integer CBO1Precision() {
			return 0;
		}

		public String CBO1Default() {

			return null;

		}

		public String CBO1Comment() {

			return "";

		}

		public String CBO1Pattern() {

			return "";

		}

		public String CBO1OriginalDbColumnName() {

			return "CBO";

		}

		public String CNAE1;

		public String getCNAE1() {
			return this.CNAE1;
		}

		public Boolean CNAE1IsNullable() {
			return true;
		}

		public Boolean CNAE1IsKey() {
			return false;
		}

		public Integer CNAE1Length() {
			return 9;
		}

		public Integer CNAE1Precision() {
			return 0;
		}

		public String CNAE1Default() {

			return null;

		}

		public String CNAE1Comment() {

			return "";

		}

		public String CNAE1Pattern() {

			return "";

		}

		public String CNAE1OriginalDbColumnName() {

			return "CNAE";

		}

		public String SegmentoAtividade11;

		public String getSegmentoAtividade11() {
			return this.SegmentoAtividade11;
		}

		public Boolean SegmentoAtividade11IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade11IsKey() {
			return false;
		}

		public Integer SegmentoAtividade11Length() {
			return 6;
		}

		public Integer SegmentoAtividade11Precision() {
			return 0;
		}

		public String SegmentoAtividade11Default() {

			return null;

		}

		public String SegmentoAtividade11Comment() {

			return "";

		}

		public String SegmentoAtividade11Pattern() {

			return "";

		}

		public String SegmentoAtividade11OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String MensagemBloqueio;

		public String getMensagemBloqueio() {
			return this.MensagemBloqueio;
		}

		public Boolean MensagemBloqueioIsNullable() {
			return true;
		}

		public Boolean MensagemBloqueioIsKey() {
			return false;
		}

		public Integer MensagemBloqueioLength() {
			return 1;
		}

		public Integer MensagemBloqueioPrecision() {
			return 0;
		}

		public String MensagemBloqueioDefault() {

			return null;

		}

		public String MensagemBloqueioComment() {

			return "";

		}

		public String MensagemBloqueioPattern() {

			return "";

		}

		public String MensagemBloqueioOriginalDbColumnName() {

			return "MensagemBloqueio";

		}

		public String SubCodigo;

		public String getSubCodigo() {
			return this.SubCodigo;
		}

		public Boolean SubCodigoIsNullable() {
			return true;
		}

		public Boolean SubCodigoIsKey() {
			return false;
		}

		public Integer SubCodigoLength() {
			return 1;
		}

		public Integer SubCodigoPrecision() {
			return 0;
		}

		public String SubCodigoDefault() {

			return null;

		}

		public String SubCodigoComment() {

			return "";

		}

		public String SubCodigoPattern() {

			return "";

		}

		public String SubCodigoOriginalDbColumnName() {

			return "SubCodigo";

		}

		public String FilialDebito;

		public String getFilialDebito() {
			return this.FilialDebito;
		}

		public Boolean FilialDebitoIsNullable() {
			return true;
		}

		public Boolean FilialDebitoIsKey() {
			return false;
		}

		public Integer FilialDebitoLength() {
			return 2;
		}

		public Integer FilialDebitoPrecision() {
			return 0;
		}

		public String FilialDebitoDefault() {

			return null;

		}

		public String FilialDebitoComment() {

			return "";

		}

		public String FilialDebitoPattern() {

			return "";

		}

		public String FilialDebitoOriginalDbColumnName() {

			return "FilialDebito";

		}

		public String RecolhePIS1;

		public String getRecolhePIS1() {
			return this.RecolhePIS1;
		}

		public Boolean RecolhePIS1IsNullable() {
			return true;
		}

		public Boolean RecolhePIS1IsKey() {
			return false;
		}

		public Integer RecolhePIS1Length() {
			return 1;
		}

		public Integer RecolhePIS1Precision() {
			return 0;
		}

		public String RecolhePIS1Default() {

			return null;

		}

		public String RecolhePIS1Comment() {

			return "";

		}

		public String RecolhePIS1Pattern() {

			return "";

		}

		public String RecolhePIS1OriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String RecolheCOFINS1;

		public String getRecolheCOFINS1() {
			return this.RecolheCOFINS1;
		}

		public Boolean RecolheCOFINS1IsNullable() {
			return true;
		}

		public Boolean RecolheCOFINS1IsKey() {
			return false;
		}

		public Integer RecolheCOFINS1Length() {
			return 1;
		}

		public Integer RecolheCOFINS1Precision() {
			return 0;
		}

		public String RecolheCOFINS1Default() {

			return null;

		}

		public String RecolheCOFINS1Comment() {

			return "";

		}

		public String RecolheCOFINS1Pattern() {

			return "";

		}

		public String RecolheCOFINS1OriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL1;

		public String getRecolheCSLL1() {
			return this.RecolheCSLL1;
		}

		public Boolean RecolheCSLL1IsNullable() {
			return true;
		}

		public Boolean RecolheCSLL1IsKey() {
			return false;
		}

		public Integer RecolheCSLL1Length() {
			return 1;
		}

		public Integer RecolheCSLL1Precision() {
			return 0;
		}

		public String RecolheCSLL1Default() {

			return null;

		}

		public String RecolheCSLL1Comment() {

			return "";

		}

		public String RecolheCSLL1Pattern() {

			return "";

		}

		public String RecolheCSLL1OriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String ABICS;

		public String getABICS() {
			return this.ABICS;
		}

		public Boolean ABICSIsNullable() {
			return true;
		}

		public Boolean ABICSIsKey() {
			return false;
		}

		public Integer ABICSLength() {
			return 4;
		}

		public Integer ABICSPrecision() {
			return 0;
		}

		public String ABICSDefault() {

			return null;

		}

		public String ABICSComment() {

			return "";

		}

		public String ABICSPattern() {

			return "";

		}

		public String ABICSOriginalDbColumnName() {

			return "ABICS";

		}

		public String Vinculo;

		public String getVinculo() {
			return this.Vinculo;
		}

		public Boolean VinculoIsNullable() {
			return true;
		}

		public Boolean VinculoIsKey() {
			return false;
		}

		public Integer VinculoLength() {
			return 2;
		}

		public Integer VinculoPrecision() {
			return 0;
		}

		public String VinculoDefault() {

			return null;

		}

		public String VinculoComment() {

			return "";

		}

		public String VinculoPattern() {

			return "";

		}

		public String VinculoOriginalDbColumnName() {

			return "Vinculo";

		}

		public String DataInicioVinculo;

		public String getDataInicioVinculo() {
			return this.DataInicioVinculo;
		}

		public Boolean DataInicioVinculoIsNullable() {
			return true;
		}

		public Boolean DataInicioVinculoIsKey() {
			return false;
		}

		public Integer DataInicioVinculoLength() {
			return 8;
		}

		public Integer DataInicioVinculoPrecision() {
			return 0;
		}

		public String DataInicioVinculoDefault() {

			return null;

		}

		public String DataInicioVinculoComment() {

			return "";

		}

		public String DataInicioVinculoPattern() {

			return "";

		}

		public String DataInicioVinculoOriginalDbColumnName() {

			return "DataInicioVinculo";

		}

		public String DataFimVinculo;

		public String getDataFimVinculo() {
			return this.DataFimVinculo;
		}

		public Boolean DataFimVinculoIsNullable() {
			return true;
		}

		public Boolean DataFimVinculoIsKey() {
			return false;
		}

		public Integer DataFimVinculoLength() {
			return 8;
		}

		public Integer DataFimVinculoPrecision() {
			return 0;
		}

		public String DataFimVinculoDefault() {

			return null;

		}

		public String DataFimVinculoComment() {

			return "";

		}

		public String DataFimVinculoPattern() {

			return "";

		}

		public String DataFimVinculoOriginalDbColumnName() {

			return "DataFimVinculo";

		}

		public String ISSRSLC;

		public String getISSRSLC() {
			return this.ISSRSLC;
		}

		public Boolean ISSRSLCIsNullable() {
			return true;
		}

		public Boolean ISSRSLCIsKey() {
			return false;
		}

		public Integer ISSRSLCLength() {
			return 1;
		}

		public Integer ISSRSLCPrecision() {
			return 0;
		}

		public String ISSRSLCDefault() {

			return null;

		}

		public String ISSRSLCComment() {

			return "";

		}

		public String ISSRSLCPattern() {

			return "";

		}

		public String ISSRSLCOriginalDbColumnName() {

			return "ISSRSLC";

		}

		public String TipoRegistro;

		public String getTipoRegistro() {
			return this.TipoRegistro;
		}

		public Boolean TipoRegistroIsNullable() {
			return true;
		}

		public Boolean TipoRegistroIsKey() {
			return false;
		}

		public Integer TipoRegistroLength() {
			return 1;
		}

		public Integer TipoRegistroPrecision() {
			return 0;
		}

		public String TipoRegistroDefault() {

			return null;

		}

		public String TipoRegistroComment() {

			return "";

		}

		public String TipoRegistroPattern() {

			return "";

		}

		public String TipoRegistroOriginalDbColumnName() {

			return "TipoRegistro";

		}

		public String RFASEMT;

		public String getRFASEMT() {
			return this.RFASEMT;
		}

		public Boolean RFASEMTIsNullable() {
			return true;
		}

		public Boolean RFASEMTIsKey() {
			return false;
		}

		public Integer RFASEMTLength() {
			return 1;
		}

		public Integer RFASEMTPrecision() {
			return 0;
		}

		public String RFASEMTDefault() {

			return null;

		}

		public String RFASEMTComment() {

			return "";

		}

		public String RFASEMTPattern() {

			return "";

		}

		public String RFASEMTOriginalDbColumnName() {

			return "RFASEMT";

		}

		public String RIMAMT;

		public String getRIMAMT() {
			return this.RIMAMT;
		}

		public Boolean RIMAMTIsNullable() {
			return true;
		}

		public Boolean RIMAMTIsKey() {
			return false;
		}

		public Integer RIMAMTLength() {
			return 1;
		}

		public Integer RIMAMTPrecision() {
			return 0;
		}

		public String RIMAMTDefault() {

			return null;

		}

		public String RIMAMTComment() {

			return "";

		}

		public String RIMAMTPattern() {

			return "";

		}

		public String RIMAMTOriginalDbColumnName() {

			return "RIMAMT";

		}

		public String REGESIM;

		public String getREGESIM() {
			return this.REGESIM;
		}

		public Boolean REGESIMIsNullable() {
			return true;
		}

		public Boolean REGESIMIsKey() {
			return false;
		}

		public Integer REGESIMLength() {
			return 1;
		}

		public Integer REGESIMPrecision() {
			return 0;
		}

		public String REGESIMDefault() {

			return null;

		}

		public String REGESIMComment() {

			return "";

		}

		public String REGESIMPattern() {

			return "";

		}

		public String REGESIMOriginalDbColumnName() {

			return "REGESIM";

		}

		public String ContaReceita;

		public String getContaReceita() {
			return this.ContaReceita;
		}

		public Boolean ContaReceitaIsNullable() {
			return true;
		}

		public Boolean ContaReceitaIsKey() {
			return false;
		}

		public Integer ContaReceitaLength() {
			return 1;
		}

		public Integer ContaReceitaPrecision() {
			return 0;
		}

		public String ContaReceitaDefault() {

			return null;

		}

		public String ContaReceitaComment() {

			return "";

		}

		public String ContaReceitaPattern() {

			return "";

		}

		public String ContaReceitaOriginalDbColumnName() {

			return "ContaReceita";

		}

		public String SIMPNacional;

		public String getSIMPNacional() {
			return this.SIMPNacional;
		}

		public Boolean SIMPNacionalIsNullable() {
			return true;
		}

		public Boolean SIMPNacionalIsKey() {
			return false;
		}

		public Integer SIMPNacionalLength() {
			return 1;
		}

		public Integer SIMPNacionalPrecision() {
			return 0;
		}

		public String SIMPNacionalDefault() {

			return null;

		}

		public String SIMPNacionalComment() {

			return "";

		}

		public String SIMPNacionalPattern() {

			return "";

		}

		public String SIMPNacionalOriginalDbColumnName() {

			return "SIMPNacional";

		}

		public String RecolheFETHAB;

		public String getRecolheFETHAB() {
			return this.RecolheFETHAB;
		}

		public Boolean RecolheFETHABIsNullable() {
			return true;
		}

		public Boolean RecolheFETHABIsKey() {
			return false;
		}

		public Integer RecolheFETHABLength() {
			return 1;
		}

		public Integer RecolheFETHABPrecision() {
			return 0;
		}

		public String RecolheFETHABDefault() {

			return null;

		}

		public String RecolheFETHABComment() {

			return "";

		}

		public String RecolheFETHABPattern() {

			return "";

		}

		public String RecolheFETHABOriginalDbColumnName() {

			return "RecolheFETHAB";

		}

		public String RFABOV;

		public String getRFABOV() {
			return this.RFABOV;
		}

		public Boolean RFABOVIsNullable() {
			return true;
		}

		public Boolean RFABOVIsKey() {
			return false;
		}

		public Integer RFABOVLength() {
			return 1;
		}

		public Integer RFABOVPrecision() {
			return 0;
		}

		public String RFABOVDefault() {

			return null;

		}

		public String RFABOVComment() {

			return "";

		}

		public String RFABOVPattern() {

			return "";

		}

		public String RFABOVOriginalDbColumnName() {

			return "RFABOV";

		}

		public String RFACS;

		public String getRFACS() {
			return this.RFACS;
		}

		public Boolean RFACSIsNullable() {
			return true;
		}

		public Boolean RFACSIsKey() {
			return false;
		}

		public Integer RFACSLength() {
			return 1;
		}

		public Integer RFACSPrecision() {
			return 0;
		}

		public String RFACSDefault() {

			return null;

		}

		public String RFACSComment() {

			return "";

		}

		public String RFACSPattern() {

			return "";

		}

		public String RFACSOriginalDbColumnName() {

			return "RFACS";

		}

		public String DataNascimento1;

		public String getDataNascimento1() {
			return this.DataNascimento1;
		}

		public Boolean DataNascimento1IsNullable() {
			return true;
		}

		public Boolean DataNascimento1IsKey() {
			return false;
		}

		public Integer DataNascimento1Length() {
			return 8;
		}

		public Integer DataNascimento1Precision() {
			return 0;
		}

		public String DataNascimento1Default() {

			return null;

		}

		public String DataNascimento1Comment() {

			return "";

		}

		public String DataNascimento1Pattern() {

			return "";

		}

		public String DataNascimento1OriginalDbColumnName() {

			return "DataNascimento";

		}

		public String Contribuinte;

		public String getContribuinte() {
			return this.Contribuinte;
		}

		public Boolean ContribuinteIsNullable() {
			return true;
		}

		public Boolean ContribuinteIsKey() {
			return false;
		}

		public Integer ContribuinteLength() {
			return 1;
		}

		public Integer ContribuintePrecision() {
			return 0;
		}

		public String ContribuinteDefault() {

			return null;

		}

		public String ContribuinteComment() {

			return "";

		}

		public String ContribuintePattern() {

			return "";

		}

		public String ContribuinteOriginalDbColumnName() {

			return "Contribuinte";

		}

		public String RecolheFMD;

		public String getRecolheFMD() {
			return this.RecolheFMD;
		}

		public Boolean RecolheFMDIsNullable() {
			return true;
		}

		public Boolean RecolheFMDIsKey() {
			return false;
		}

		public Integer RecolheFMDLength() {
			return 1;
		}

		public Integer RecolheFMDPrecision() {
			return 0;
		}

		public String RecolheFMDDefault() {

			return null;

		}

		public String RecolheFMDComment() {

			return "";

		}

		public String RecolheFMDPattern() {

			return "";

		}

		public String RecolheFMDOriginalDbColumnName() {

			return "RecolheFMD";

		}

		public String TipoJuridico;

		public String getTipoJuridico() {
			return this.TipoJuridico;
		}

		public Boolean TipoJuridicoIsNullable() {
			return true;
		}

		public Boolean TipoJuridicoIsKey() {
			return false;
		}

		public Integer TipoJuridicoLength() {
			return 1;
		}

		public Integer TipoJuridicoPrecision() {
			return 0;
		}

		public String TipoJuridicoDefault() {

			return null;

		}

		public String TipoJuridicoComment() {

			return "";

		}

		public String TipoJuridicoPattern() {

			return "";

		}

		public String TipoJuridicoOriginalDbColumnName() {

			return "TipoJuridico";

		}

		public String INCULT;

		public String getINCULT() {
			return this.INCULT;
		}

		public Boolean INCULTIsNullable() {
			return true;
		}

		public Boolean INCULTIsKey() {
			return false;
		}

		public Integer INCULTLength() {
			return 1;
		}

		public Integer INCULTPrecision() {
			return 0;
		}

		public String INCULTDefault() {

			return null;

		}

		public String INCULTComment() {

			return "";

		}

		public String INCULTPattern() {

			return "";

		}

		public String INCULTOriginalDbColumnName() {

			return "INCULT";

		}

		public String IDHistorico;

		public String getIDHistorico() {
			return this.IDHistorico;
		}

		public Boolean IDHistoricoIsNullable() {
			return true;
		}

		public Boolean IDHistoricoIsKey() {
			return false;
		}

		public Integer IDHistoricoLength() {
			return 20;
		}

		public Integer IDHistoricoPrecision() {
			return 0;
		}

		public String IDHistoricoDefault() {

			return null;

		}

		public String IDHistoricoComment() {

			return "";

		}

		public String IDHistoricoPattern() {

			return "";

		}

		public String IDHistoricoOriginalDbColumnName() {

			return "IDHistorico";

		}

		public String PrestacaoServico;

		public String getPrestacaoServico() {
			return this.PrestacaoServico;
		}

		public Boolean PrestacaoServicoIsNullable() {
			return true;
		}

		public Boolean PrestacaoServicoIsKey() {
			return false;
		}

		public Integer PrestacaoServicoLength() {
			return 1;
		}

		public Integer PrestacaoServicoPrecision() {
			return 0;
		}

		public String PrestacaoServicoDefault() {

			return null;

		}

		public String PrestacaoServicoComment() {

			return "";

		}

		public String PrestacaoServicoPattern() {

			return "";

		}

		public String PrestacaoServicoOriginalDbColumnName() {

			return "PrestacaoServico";

		}

		public String NUMRA;

		public String getNUMRA() {
			return this.NUMRA;
		}

		public Boolean NUMRAIsNullable() {
			return true;
		}

		public Boolean NUMRAIsKey() {
			return false;
		}

		public Integer NUMRALength() {
			return 15;
		}

		public Integer NUMRAPrecision() {
			return 0;
		}

		public String NUMRADefault() {

			return null;

		}

		public String NUMRAComment() {

			return "";

		}

		public String NUMRAPattern() {

			return "";

		}

		public String NUMRAOriginalDbColumnName() {

			return "NUMRA";

		}

		public String MINIRF;

		public String getMINIRF() {
			return this.MINIRF;
		}

		public Boolean MINIRFIsNullable() {
			return true;
		}

		public Boolean MINIRFIsKey() {
			return false;
		}

		public Integer MINIRFLength() {
			return 1;
		}

		public Integer MINIRFPrecision() {
			return 0;
		}

		public String MINIRFDefault() {

			return null;

		}

		public String MINIRFComment() {

			return "";

		}

		public String MINIRFPattern() {

			return "";

		}

		public String MINIRFOriginalDbColumnName() {

			return "MINIRF";

		}

		public String CODSIAF;

		public String getCODSIAF() {
			return this.CODSIAF;
		}

		public Boolean CODSIAFIsNullable() {
			return true;
		}

		public Boolean CODSIAFIsKey() {
			return false;
		}

		public Integer CODSIAFLength() {
			return 4;
		}

		public Integer CODSIAFPrecision() {
			return 0;
		}

		public String CODSIAFDefault() {

			return null;

		}

		public String CODSIAFComment() {

			return "";

		}

		public String CODSIAFPattern() {

			return "";

		}

		public String CODSIAFOriginalDbColumnName() {

			return "CODSIAF";

		}

		public String ENDNOT;

		public String getENDNOT() {
			return this.ENDNOT;
		}

		public Boolean ENDNOTIsNullable() {
			return true;
		}

		public Boolean ENDNOTIsKey() {
			return false;
		}

		public Integer ENDNOTLength() {
			return 1;
		}

		public Integer ENDNOTPrecision() {
			return 0;
		}

		public String ENDNOTDefault() {

			return null;

		}

		public String ENDNOTComment() {

			return "";

		}

		public String ENDNOTPattern() {

			return "";

		}

		public String ENDNOTOriginalDbColumnName() {

			return "ENDNOT";

		}

		public String FOMEZER;

		public String getFOMEZER() {
			return this.FOMEZER;
		}

		public Boolean FOMEZERIsNullable() {
			return true;
		}

		public Boolean FOMEZERIsKey() {
			return false;
		}

		public Integer FOMEZERLength() {
			return 1;
		}

		public Integer FOMEZERPrecision() {
			return 0;
		}

		public String FOMEZERDefault() {

			return null;

		}

		public String FOMEZERComment() {

			return "";

		}

		public String FOMEZERPattern() {

			return "";

		}

		public String FOMEZEROriginalDbColumnName() {

			return "FOMEZER";

		}

		public String FRETISS;

		public String getFRETISS() {
			return this.FRETISS;
		}

		public Boolean FRETISSIsNullable() {
			return true;
		}

		public Boolean FRETISSIsKey() {
			return false;
		}

		public Integer FRETISSLength() {
			return 1;
		}

		public Integer FRETISSPrecision() {
			return 0;
		}

		public String FRETISSDefault() {

			return null;

		}

		public String FRETISSComment() {

			return "";

		}

		public String FRETISSPattern() {

			return "";

		}

		public String FRETISSOriginalDbColumnName() {

			return "FRETISS";

		}

		public String COMPLEM;

		public String getCOMPLEM() {
			return this.COMPLEM;
		}

		public Boolean COMPLEMIsNullable() {
			return true;
		}

		public Boolean COMPLEMIsKey() {
			return false;
		}

		public Integer COMPLEMLength() {
			return 50;
		}

		public Integer COMPLEMPrecision() {
			return 0;
		}

		public String COMPLEMDefault() {

			return null;

		}

		public String COMPLEMComment() {

			return "";

		}

		public String COMPLEMPattern() {

			return "";

		}

		public String COMPLEMOriginalDbColumnName() {

			return "COMPLEM";

		}

		public String INCLTMG;

		public String getINCLTMG() {
			return this.INCLTMG;
		}

		public Boolean INCLTMGIsNullable() {
			return true;
		}

		public Boolean INCLTMGIsKey() {
			return false;
		}

		public Integer INCLTMGLength() {
			return 1;
		}

		public Integer INCLTMGPrecision() {
			return 0;
		}

		public String INCLTMGDefault() {

			return null;

		}

		public String INCLTMGComment() {

			return "";

		}

		public String INCLTMGPattern() {

			return "";

		}

		public String INCLTMGOriginalDbColumnName() {

			return "INCLTMG";

		}

		public String FILTRF;

		public String getFILTRF() {
			return this.FILTRF;
		}

		public Boolean FILTRFIsNullable() {
			return true;
		}

		public Boolean FILTRFIsKey() {
			return false;
		}

		public Integer FILTRFLength() {
			return 2;
		}

		public Integer FILTRFPrecision() {
			return 0;
		}

		public String FILTRFDefault() {

			return null;

		}

		public String FILTRFComment() {

			return "";

		}

		public String FILTRFPattern() {

			return "";

		}

		public String FILTRFOriginalDbColumnName() {

			return "FILTRF";

		}

		public String TRIBFAV;

		public String getTRIBFAV() {
			return this.TRIBFAV;
		}

		public Boolean TRIBFAVIsNullable() {
			return true;
		}

		public Boolean TRIBFAVIsKey() {
			return false;
		}

		public Integer TRIBFAVLength() {
			return 1;
		}

		public Integer TRIBFAVPrecision() {
			return 0;
		}

		public String TRIBFAVDefault() {

			return null;

		}

		public String TRIBFAVComment() {

			return "";

		}

		public String TRIBFAVPattern() {

			return "";

		}

		public String TRIBFAVOriginalDbColumnName() {

			return "TRIBFAV";

		}

		public String REGPB;

		public String getREGPB() {
			return this.REGPB;
		}

		public Boolean REGPBIsNullable() {
			return true;
		}

		public Boolean REGPBIsKey() {
			return false;
		}

		public Integer REGPBLength() {
			return 1;
		}

		public Integer REGPBPrecision() {
			return 0;
		}

		public String REGPBDefault() {

			return null;

		}

		public String REGPBComment() {

			return "";

		}

		public String REGPBPattern() {

			return "";

		}

		public String REGPBOriginalDbColumnName() {

			return "REGPB";

		}

		public String INOVAUT;

		public String getINOVAUT() {
			return this.INOVAUT;
		}

		public Boolean INOVAUTIsNullable() {
			return true;
		}

		public Boolean INOVAUTIsKey() {
			return false;
		}

		public Integer INOVAUTLength() {
			return 1;
		}

		public Integer INOVAUTPrecision() {
			return 0;
		}

		public String INOVAUTDefault() {

			return null;

		}

		public String INOVAUTComment() {

			return "";

		}

		public String INOVAUTPattern() {

			return "";

		}

		public String INOVAUTOriginalDbColumnName() {

			return "INOVAUT";

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

		public String Ukey;

		public String getUkey() {
			return this.Ukey;
		}

		public Boolean UkeyIsNullable() {
			return true;
		}

		public Boolean UkeyIsKey() {
			return true;
		}

		public Integer UkeyLength() {
			return 10;
		}

		public Integer UkeyPrecision() {
			return 0;
		}

		public String UkeyDefault() {

			return null;

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

		public Integer RecDeletado;

		public Integer getRecDeletado() {
			return this.RecDeletado;
		}

		public Boolean RecDeletadoIsNullable() {
			return true;
		}

		public Boolean RecDeletadoIsKey() {
			return false;
		}

		public Integer RecDeletadoLength() {
			return 10;
		}

		public Integer RecDeletadoPrecision() {
			return 0;
		}

		public String RecDeletadoDefault() {

			return "";

		}

		public String RecDeletadoComment() {

			return "";

		}

		public String RecDeletadoPattern() {

			return "";

		}

		public String RecDeletadoOriginalDbColumnName() {

			return "RecDeletado";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Filial == null) ? 0 : this.Filial.hashCode());

				result = prime * result + ((this.Codigo == null) ? 0 : this.Codigo.hashCode());

				result = prime * result + ((this.Loja == null) ? 0 : this.Loja.hashCode());

				result = prime * result + ((this.Ukey == null) ? 0 : this.Ukey.hashCode());

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

			if (this.Filial == null) {
				if (other.Filial != null)
					return false;

			} else if (!this.Filial.equals(other.Filial))

				return false;

			if (this.Codigo == null) {
				if (other.Codigo != null)
					return false;

			} else if (!this.Codigo.equals(other.Codigo))

				return false;

			if (this.Loja == null) {
				if (other.Loja != null)
					return false;

			} else if (!this.Loja.equals(other.Loja))

				return false;

			if (this.Ukey == null) {
				if (other.Ukey != null)
					return false;

			} else if (!this.Ukey.equals(other.Ukey))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.Filial = this.Filial;
			other.Codigo = this.Codigo;
			other.Loja = this.Loja;
			other.Tipo = this.Tipo;
			other.Pessoa = this.Pessoa;
			other.Natureza = this.Natureza;
			other.CNPJ = this.CNPJ;
			other.Nome = this.Nome;
			other.NomeReduzido = this.NomeReduzido;
			other.Endereco = this.Endereco;
			other.Complemento = this.Complemento;
			other.Bairro = this.Bairro;
			other.CEP = this.CEP;
			other.Estado = this.Estado;
			other.Regiao = this.Regiao;
			other.DescricaoRegiao = this.DescricaoRegiao;
			other.CodigoMunicipio = this.CodigoMunicipio;
			other.Municipio = this.Municipio;
			other.CodigoPais = this.CodigoPais;
			other.NomeEstado = this.NomeEstado;
			other.DDD = this.DDD;
			other.TributacaoFavoravel = this.TributacaoFavoravel;
			other.DDI = this.DDI;
			other.CodIBGE = this.CodIBGE;
			other.UCodigoMunicipio = this.UCodigoMunicipio;
			other.EnderecoRecebimento = this.EnderecoRecebimento;
			other.Telefone = this.Telefone;
			other.FAX = this.FAX;
			other.FoneCP = this.FoneCP;
			other.Contato = this.Contato;
			other.PessoaFisica = this.PessoaFisica;
			other.Pais = this.Pais;
			other.Inscricao = this.Inscricao;
			other.InscricaoMunicipal = this.InscricaoMunicipal;
			other.Vendedor = this.Vendedor;
			other.Comissao = this.Comissao;
			other.Conta = this.Conta;
			other.Banco1 = this.Banco1;
			other.Banco2 = this.Banco2;
			other.Banco3 = this.Banco3;
			other.Banco4 = this.Banco4;
			other.Banco5 = this.Banco5;
			other.Transporte = this.Transporte;
			other.TipoFrete = this.TipoFrete;
			other.EnderecoCobranca = this.EnderecoCobranca;
			other.Condicao = this.Condicao;
			other.BairroCobranca = this.BairroCobranca;
			other.Descricao = this.Descricao;
			other.CEPCobranca = this.CEPCobranca;
			other.EstadoCobranca = this.EstadoCobranca;
			other.UCodigoMunicipioCobranca = this.UCodigoMunicipioCobranca;
			other.MunicipioCobranca = this.MunicipioCobranca;
			other.Prioridade = this.Prioridade;
			other.Risco = this.Risco;
			other.LimiteCredito = this.LimiteCredito;
			other.VencimentoLimiteCredito = this.VencimentoLimiteCredito;
			other.Classe = this.Classe;
			other.LimiteCreditoFinal = this.LimiteCreditoFinal;
			other.MoedaLimiteCredito = this.MoedaLimiteCredito;
			other.MaiorSaldo = this.MaiorSaldo;
			other.MaiorCompra = this.MaiorCompra;
			other.MediaAtraso = this.MediaAtraso;
			other.PrimeiraCompra = this.PrimeiraCompra;
			other.UltimaCompra = this.UltimaCompra;
			other.NumeroCompras = this.NumeroCompras;
			other.FormularioVisita = this.FormularioVisita;
			other.TempoVisita = this.TempoVisita;
			other.UltimaVisita = this.UltimaVisita;
			other.TempoVisita1 = this.TempoVisita1;
			other.TempoPadrao = this.TempoPadrao;
			other.ClassificacaoVenda = this.ClassificacaoVenda;
			other.Mensagem = this.Mensagem;
			other.SaldoTitulo = this.SaldoTitulo;
			other.RecolheISS = this.RecolheISS;
			other.SaldoPedidoLiberado = this.SaldoPedidoLiberado;
			other.NumeroPagamentos = this.NumeroPagamentos;
			other.Transferencia = this.Transferencia;
			other.SUFRAMA = this.SUFRAMA;
			other.ATR = this.ATR;
			other.ValorAcumulado = this.ValorAcumulado;
			other.SaldoPedido = this.SaldoPedido;
			other.TituloProtestado = this.TituloProtestado;
			other.DataUltimoTitulo = this.DataUltimoTitulo;
			other.ChequeDevolvido = this.ChequeDevolvido;
			other.CaixaPostal = this.CaixaPostal;
			other.Matriz = this.Matriz;
			other.DataUltimoCheque = this.DataUltimoCheque;
			other.MaiorDuplicata = this.MaiorDuplicata;
			other.Tabela = this.Tabela;
			other.ISSIncluso = this.ISSIncluso;
			other.SaldoDuplicataMoeda = this.SaldoDuplicataMoeda;
			other.PagamentoAtrasado = this.PagamentoAtrasado;
			other.Atividade = this.Atividade;
			other.Cargo1 = this.Cargo1;
			other.Cargo2 = this.Cargo2;
			other.Cargo3 = this.Cargo3;
			other.RepresentanteTecnico = this.RepresentanteTecnico;
			other.Supervisor = this.Supervisor;
			other.AliquotaIR = this.AliquotaIR;
			other.Observacao = this.Observacao;
			other.RG = this.RG;
			other.CalculaSUFRAMA = this.CalculaSUFRAMA;
			other.DataNascimento = this.DataNascimento;
			other.SaldoPedidoBloqueado = this.SaldoPedidoBloqueado;
			other.GrupoTributario = this.GrupoTributario;
			other.SegmentoAtividade1 = this.SegmentoAtividade1;
			other.ClienteFaturamento = this.ClienteFaturamento;
			other.EnderecoEntrega = this.EnderecoEntrega;
			other.UCompleto = this.UCompleto;
			other.BairroEntrega = this.BairroEntrega;
			other.CEPEntrada = this.CEPEntrada;
			other.EstadoEntrada = this.EstadoEntrada;
			other.CodigoLocal = this.CodigoLocal;
			other.SegmentoAtividade2 = this.SegmentoAtividade2;
			other.TipoPessoa = this.TipoPessoa;
			other.TipoISSRS = this.TipoISSRS;
			other.SegmentoAtividade3 = this.SegmentoAtividade3;
			other.SegmentoAtividade4 = this.SegmentoAtividade4;
			other.SegmentoAtividade5 = this.SegmentoAtividade5;
			other.SegmentoAtividade6 = this.SegmentoAtividade6;
			other.SegmentoAtividade7 = this.SegmentoAtividade7;
			other.CodigoAgente = this.CodigoAgente;
			other.SegmentoAtividade8 = this.SegmentoAtividade8;
			other.CodigoMarcacao = this.CodigoMarcacao;
			other.ComissaoAgente = this.ComissaoAgente;
			other.HomePage = this.HomePage;
			other.CodigoMunicipio1 = this.CodigoMunicipio1;
			other.TipoCliente = this.TipoCliente;
			other.Email = this.Email;
			other.Destino1 = this.Destino1;
			other.Destino2 = this.Destino2;
			other.Destino3 = this.Destino3;
			other.CBO = this.CBO;
			other.Observacao1 = this.Observacao1;
			other.CNAE = this.CNAE;
			other.CodigoHistorico = this.CodigoHistorico;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.DiasPagamento = this.DiasPagamento;
			other.Agregado = this.Agregado;
			other.RecolheINSS = this.RecolheINSS;
			other.RecolheCOFINS = this.RecolheCOFINS;
			other.RecolheCSLL = this.RecolheCSLL;
			other.RecolhePIS = this.RecolhePIS;
			other.TipoPeriodo = this.TipoPeriodo;
			other.SaldoFinal = this.SaldoFinal;
			other.SaldoFinalMoeda = this.SaldoFinalMoeda;
			other.Contabilidade = this.Contabilidade;
			other.ClienteConvenio = this.ClienteConvenio;
			other.B2B = this.B2B;
			other.CBO1 = this.CBO1;
			other.CNAE1 = this.CNAE1;
			other.SegmentoAtividade11 = this.SegmentoAtividade11;
			other.MensagemBloqueio = this.MensagemBloqueio;
			other.SubCodigo = this.SubCodigo;
			other.FilialDebito = this.FilialDebito;
			other.RecolhePIS1 = this.RecolhePIS1;
			other.RecolheCOFINS1 = this.RecolheCOFINS1;
			other.RecolheCSLL1 = this.RecolheCSLL1;
			other.ABICS = this.ABICS;
			other.Vinculo = this.Vinculo;
			other.DataInicioVinculo = this.DataInicioVinculo;
			other.DataFimVinculo = this.DataFimVinculo;
			other.ISSRSLC = this.ISSRSLC;
			other.TipoRegistro = this.TipoRegistro;
			other.RFASEMT = this.RFASEMT;
			other.RIMAMT = this.RIMAMT;
			other.REGESIM = this.REGESIM;
			other.ContaReceita = this.ContaReceita;
			other.SIMPNacional = this.SIMPNacional;
			other.RecolheFETHAB = this.RecolheFETHAB;
			other.RFABOV = this.RFABOV;
			other.RFACS = this.RFACS;
			other.DataNascimento1 = this.DataNascimento1;
			other.Contribuinte = this.Contribuinte;
			other.RecolheFMD = this.RecolheFMD;
			other.TipoJuridico = this.TipoJuridico;
			other.INCULT = this.INCULT;
			other.IDHistorico = this.IDHistorico;
			other.PrestacaoServico = this.PrestacaoServico;
			other.NUMRA = this.NUMRA;
			other.MINIRF = this.MINIRF;
			other.CODSIAF = this.CODSIAF;
			other.ENDNOT = this.ENDNOT;
			other.FOMEZER = this.FOMEZER;
			other.FRETISS = this.FRETISS;
			other.COMPLEM = this.COMPLEM;
			other.INCLTMG = this.INCLTMG;
			other.FILTRF = this.FILTRF;
			other.TRIBFAV = this.TRIBFAV;
			other.REGPB = this.REGPB;
			other.INOVAUT = this.INOVAUT;
			other.Deletado = this.Deletado;
			other.Ukey = this.Ukey;
			other.RecDeletado = this.RecDeletado;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.Filial = this.Filial;
			other.Codigo = this.Codigo;
			other.Loja = this.Loja;
			other.Ukey = this.Ukey;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readDate(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readDate(dis);

					this.UltimaCompra = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readDate(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readDate(dis);

					this.UltimaCompra = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

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

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// java.util.Date

				writeDate(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// java.util.Date

				writeDate(this.PrimeiraCompra, dos);

				// java.util.Date

				writeDate(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// java.util.Date

				writeDate(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// java.util.Date

				writeDate(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// java.util.Date

				writeDate(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// java.util.Date

				writeDate(this.PrimeiraCompra, dos);

				// java.util.Date

				writeDate(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// java.util.Date

				writeDate(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// java.util.Date

				writeDate(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Filial=" + Filial);
			sb.append(",Codigo=" + Codigo);
			sb.append(",Loja=" + Loja);
			sb.append(",Tipo=" + Tipo);
			sb.append(",Pessoa=" + Pessoa);
			sb.append(",Natureza=" + Natureza);
			sb.append(",CNPJ=" + CNPJ);
			sb.append(",Nome=" + Nome);
			sb.append(",NomeReduzido=" + NomeReduzido);
			sb.append(",Endereco=" + Endereco);
			sb.append(",Complemento=" + Complemento);
			sb.append(",Bairro=" + Bairro);
			sb.append(",CEP=" + CEP);
			sb.append(",Estado=" + Estado);
			sb.append(",Regiao=" + Regiao);
			sb.append(",DescricaoRegiao=" + DescricaoRegiao);
			sb.append(",CodigoMunicipio=" + CodigoMunicipio);
			sb.append(",Municipio=" + Municipio);
			sb.append(",CodigoPais=" + CodigoPais);
			sb.append(",NomeEstado=" + NomeEstado);
			sb.append(",DDD=" + DDD);
			sb.append(",TributacaoFavoravel=" + TributacaoFavoravel);
			sb.append(",DDI=" + DDI);
			sb.append(",CodIBGE=" + CodIBGE);
			sb.append(",UCodigoMunicipio=" + UCodigoMunicipio);
			sb.append(",EnderecoRecebimento=" + EnderecoRecebimento);
			sb.append(",Telefone=" + Telefone);
			sb.append(",FAX=" + FAX);
			sb.append(",FoneCP=" + FoneCP);
			sb.append(",Contato=" + Contato);
			sb.append(",PessoaFisica=" + PessoaFisica);
			sb.append(",Pais=" + Pais);
			sb.append(",Inscricao=" + Inscricao);
			sb.append(",InscricaoMunicipal=" + InscricaoMunicipal);
			sb.append(",Vendedor=" + Vendedor);
			sb.append(",Comissao=" + String.valueOf(Comissao));
			sb.append(",Conta=" + Conta);
			sb.append(",Banco1=" + Banco1);
			sb.append(",Banco2=" + Banco2);
			sb.append(",Banco3=" + Banco3);
			sb.append(",Banco4=" + Banco4);
			sb.append(",Banco5=" + Banco5);
			sb.append(",Transporte=" + Transporte);
			sb.append(",TipoFrete=" + TipoFrete);
			sb.append(",EnderecoCobranca=" + EnderecoCobranca);
			sb.append(",Condicao=" + Condicao);
			sb.append(",BairroCobranca=" + BairroCobranca);
			sb.append(",Descricao=" + String.valueOf(Descricao));
			sb.append(",CEPCobranca=" + CEPCobranca);
			sb.append(",EstadoCobranca=" + EstadoCobranca);
			sb.append(",UCodigoMunicipioCobranca=" + UCodigoMunicipioCobranca);
			sb.append(",MunicipioCobranca=" + MunicipioCobranca);
			sb.append(",Prioridade=" + Prioridade);
			sb.append(",Risco=" + Risco);
			sb.append(",LimiteCredito=" + String.valueOf(LimiteCredito));
			sb.append(",VencimentoLimiteCredito=" + String.valueOf(VencimentoLimiteCredito));
			sb.append(",Classe=" + Classe);
			sb.append(",LimiteCreditoFinal=" + String.valueOf(LimiteCreditoFinal));
			sb.append(",MoedaLimiteCredito=" + String.valueOf(MoedaLimiteCredito));
			sb.append(",MaiorSaldo=" + String.valueOf(MaiorSaldo));
			sb.append(",MaiorCompra=" + String.valueOf(MaiorCompra));
			sb.append(",MediaAtraso=" + String.valueOf(MediaAtraso));
			sb.append(",PrimeiraCompra=" + String.valueOf(PrimeiraCompra));
			sb.append(",UltimaCompra=" + String.valueOf(UltimaCompra));
			sb.append(",NumeroCompras=" + String.valueOf(NumeroCompras));
			sb.append(",FormularioVisita=" + FormularioVisita);
			sb.append(",TempoVisita=" + String.valueOf(TempoVisita));
			sb.append(",UltimaVisita=" + UltimaVisita);
			sb.append(",TempoVisita1=" + TempoVisita1);
			sb.append(",TempoPadrao=" + TempoPadrao);
			sb.append(",ClassificacaoVenda=" + ClassificacaoVenda);
			sb.append(",Mensagem=" + Mensagem);
			sb.append(",SaldoTitulo=" + String.valueOf(SaldoTitulo));
			sb.append(",RecolheISS=" + RecolheISS);
			sb.append(",SaldoPedidoLiberado=" + String.valueOf(SaldoPedidoLiberado));
			sb.append(",NumeroPagamentos=" + String.valueOf(NumeroPagamentos));
			sb.append(",Transferencia=" + Transferencia);
			sb.append(",SUFRAMA=" + SUFRAMA);
			sb.append(",ATR=" + String.valueOf(ATR));
			sb.append(",ValorAcumulado=" + String.valueOf(ValorAcumulado));
			sb.append(",SaldoPedido=" + String.valueOf(SaldoPedido));
			sb.append(",TituloProtestado=" + String.valueOf(TituloProtestado));
			sb.append(",DataUltimoTitulo=" + String.valueOf(DataUltimoTitulo));
			sb.append(",ChequeDevolvido=" + String.valueOf(ChequeDevolvido));
			sb.append(",CaixaPostal=" + CaixaPostal);
			sb.append(",Matriz=" + String.valueOf(Matriz));
			sb.append(",DataUltimoCheque=" + DataUltimoCheque);
			sb.append(",MaiorDuplicata=" + String.valueOf(MaiorDuplicata));
			sb.append(",Tabela=" + Tabela);
			sb.append(",ISSIncluso=" + ISSIncluso);
			sb.append(",SaldoDuplicataMoeda=" + String.valueOf(SaldoDuplicataMoeda));
			sb.append(",PagamentoAtrasado=" + String.valueOf(PagamentoAtrasado));
			sb.append(",Atividade=" + Atividade);
			sb.append(",Cargo1=" + Cargo1);
			sb.append(",Cargo2=" + Cargo2);
			sb.append(",Cargo3=" + Cargo3);
			sb.append(",RepresentanteTecnico=" + RepresentanteTecnico);
			sb.append(",Supervisor=" + Supervisor);
			sb.append(",AliquotaIR=" + String.valueOf(AliquotaIR));
			sb.append(",Observacao=" + Observacao);
			sb.append(",RG=" + RG);
			sb.append(",CalculaSUFRAMA=" + CalculaSUFRAMA);
			sb.append(",DataNascimento=" + String.valueOf(DataNascimento));
			sb.append(",SaldoPedidoBloqueado=" + String.valueOf(SaldoPedidoBloqueado));
			sb.append(",GrupoTributario=" + GrupoTributario);
			sb.append(",SegmentoAtividade1=" + SegmentoAtividade1);
			sb.append(",ClienteFaturamento=" + ClienteFaturamento);
			sb.append(",EnderecoEntrega=" + EnderecoEntrega);
			sb.append(",UCompleto=" + UCompleto);
			sb.append(",BairroEntrega=" + BairroEntrega);
			sb.append(",CEPEntrada=" + CEPEntrada);
			sb.append(",EstadoEntrada=" + EstadoEntrada);
			sb.append(",CodigoLocal=" + CodigoLocal);
			sb.append(",SegmentoAtividade2=" + SegmentoAtividade2);
			sb.append(",TipoPessoa=" + TipoPessoa);
			sb.append(",TipoISSRS=" + TipoISSRS);
			sb.append(",SegmentoAtividade3=" + SegmentoAtividade3);
			sb.append(",SegmentoAtividade4=" + SegmentoAtividade4);
			sb.append(",SegmentoAtividade5=" + SegmentoAtividade5);
			sb.append(",SegmentoAtividade6=" + SegmentoAtividade6);
			sb.append(",SegmentoAtividade7=" + SegmentoAtividade7);
			sb.append(",CodigoAgente=" + CodigoAgente);
			sb.append(",SegmentoAtividade8=" + SegmentoAtividade8);
			sb.append(",CodigoMarcacao=" + CodigoMarcacao);
			sb.append(",ComissaoAgente=" + String.valueOf(ComissaoAgente));
			sb.append(",HomePage=" + HomePage);
			sb.append(",CodigoMunicipio1=" + CodigoMunicipio1);
			sb.append(",TipoCliente=" + TipoCliente);
			sb.append(",Email=" + Email);
			sb.append(",Destino1=" + Destino1);
			sb.append(",Destino2=" + Destino2);
			sb.append(",Destino3=" + Destino3);
			sb.append(",CBO=" + CBO);
			sb.append(",Observacao1=" + Observacao1);
			sb.append(",CNAE=" + CNAE);
			sb.append(",CodigoHistorico=" + CodigoHistorico);
			sb.append(",CondicaoPagamento=" + CondicaoPagamento);
			sb.append(",DiasPagamento=" + String.valueOf(DiasPagamento));
			sb.append(",Agregado=" + Agregado);
			sb.append(",RecolheINSS=" + RecolheINSS);
			sb.append(",RecolheCOFINS=" + RecolheCOFINS);
			sb.append(",RecolheCSLL=" + RecolheCSLL);
			sb.append(",RecolhePIS=" + RecolhePIS);
			sb.append(",TipoPeriodo=" + TipoPeriodo);
			sb.append(",SaldoFinal=" + String.valueOf(SaldoFinal));
			sb.append(",SaldoFinalMoeda=" + String.valueOf(SaldoFinalMoeda));
			sb.append(",Contabilidade=" + Contabilidade);
			sb.append(",ClienteConvenio=" + ClienteConvenio);
			sb.append(",B2B=" + B2B);
			sb.append(",CBO1=" + CBO1);
			sb.append(",CNAE1=" + CNAE1);
			sb.append(",SegmentoAtividade11=" + SegmentoAtividade11);
			sb.append(",MensagemBloqueio=" + MensagemBloqueio);
			sb.append(",SubCodigo=" + SubCodigo);
			sb.append(",FilialDebito=" + FilialDebito);
			sb.append(",RecolhePIS1=" + RecolhePIS1);
			sb.append(",RecolheCOFINS1=" + RecolheCOFINS1);
			sb.append(",RecolheCSLL1=" + RecolheCSLL1);
			sb.append(",ABICS=" + ABICS);
			sb.append(",Vinculo=" + Vinculo);
			sb.append(",DataInicioVinculo=" + DataInicioVinculo);
			sb.append(",DataFimVinculo=" + DataFimVinculo);
			sb.append(",ISSRSLC=" + ISSRSLC);
			sb.append(",TipoRegistro=" + TipoRegistro);
			sb.append(",RFASEMT=" + RFASEMT);
			sb.append(",RIMAMT=" + RIMAMT);
			sb.append(",REGESIM=" + REGESIM);
			sb.append(",ContaReceita=" + ContaReceita);
			sb.append(",SIMPNacional=" + SIMPNacional);
			sb.append(",RecolheFETHAB=" + RecolheFETHAB);
			sb.append(",RFABOV=" + RFABOV);
			sb.append(",RFACS=" + RFACS);
			sb.append(",DataNascimento1=" + DataNascimento1);
			sb.append(",Contribuinte=" + Contribuinte);
			sb.append(",RecolheFMD=" + RecolheFMD);
			sb.append(",TipoJuridico=" + TipoJuridico);
			sb.append(",INCULT=" + INCULT);
			sb.append(",IDHistorico=" + IDHistorico);
			sb.append(",PrestacaoServico=" + PrestacaoServico);
			sb.append(",NUMRA=" + NUMRA);
			sb.append(",MINIRF=" + MINIRF);
			sb.append(",CODSIAF=" + CODSIAF);
			sb.append(",ENDNOT=" + ENDNOT);
			sb.append(",FOMEZER=" + FOMEZER);
			sb.append(",FRETISS=" + FRETISS);
			sb.append(",COMPLEM=" + COMPLEM);
			sb.append(",INCLTMG=" + INCLTMG);
			sb.append(",FILTRF=" + FILTRF);
			sb.append(",TRIBFAV=" + TRIBFAV);
			sb.append(",REGPB=" + REGPB);
			sb.append(",INOVAUT=" + INOVAUT);
			sb.append(",Deletado=" + Deletado);
			sb.append(",Ukey=" + Ukey);
			sb.append(",RecDeletado=" + String.valueOf(RecDeletado));
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

			if (Codigo == null) {
				sb.append("<null>");
			} else {
				sb.append(Codigo);
			}

			sb.append("|");

			if (Loja == null) {
				sb.append("<null>");
			} else {
				sb.append(Loja);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (Pessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(Pessoa);
			}

			sb.append("|");

			if (Natureza == null) {
				sb.append("<null>");
			} else {
				sb.append(Natureza);
			}

			sb.append("|");

			if (CNPJ == null) {
				sb.append("<null>");
			} else {
				sb.append(CNPJ);
			}

			sb.append("|");

			if (Nome == null) {
				sb.append("<null>");
			} else {
				sb.append(Nome);
			}

			sb.append("|");

			if (NomeReduzido == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeReduzido);
			}

			sb.append("|");

			if (Endereco == null) {
				sb.append("<null>");
			} else {
				sb.append(Endereco);
			}

			sb.append("|");

			if (Complemento == null) {
				sb.append("<null>");
			} else {
				sb.append(Complemento);
			}

			sb.append("|");

			if (Bairro == null) {
				sb.append("<null>");
			} else {
				sb.append(Bairro);
			}

			sb.append("|");

			if (CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(CEP);
			}

			sb.append("|");

			if (Estado == null) {
				sb.append("<null>");
			} else {
				sb.append(Estado);
			}

			sb.append("|");

			if (Regiao == null) {
				sb.append("<null>");
			} else {
				sb.append(Regiao);
			}

			sb.append("|");

			if (DescricaoRegiao == null) {
				sb.append("<null>");
			} else {
				sb.append(DescricaoRegiao);
			}

			sb.append("|");

			if (CodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio);
			}

			sb.append("|");

			if (Municipio == null) {
				sb.append("<null>");
			} else {
				sb.append(Municipio);
			}

			sb.append("|");

			if (CodigoPais == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoPais);
			}

			sb.append("|");

			if (NomeEstado == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeEstado);
			}

			sb.append("|");

			if (DDD == null) {
				sb.append("<null>");
			} else {
				sb.append(DDD);
			}

			sb.append("|");

			if (TributacaoFavoravel == null) {
				sb.append("<null>");
			} else {
				sb.append(TributacaoFavoravel);
			}

			sb.append("|");

			if (DDI == null) {
				sb.append("<null>");
			} else {
				sb.append(DDI);
			}

			sb.append("|");

			if (CodIBGE == null) {
				sb.append("<null>");
			} else {
				sb.append(CodIBGE);
			}

			sb.append("|");

			if (UCodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipio);
			}

			sb.append("|");

			if (EnderecoRecebimento == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoRecebimento);
			}

			sb.append("|");

			if (Telefone == null) {
				sb.append("<null>");
			} else {
				sb.append(Telefone);
			}

			sb.append("|");

			if (FAX == null) {
				sb.append("<null>");
			} else {
				sb.append(FAX);
			}

			sb.append("|");

			if (FoneCP == null) {
				sb.append("<null>");
			} else {
				sb.append(FoneCP);
			}

			sb.append("|");

			if (Contato == null) {
				sb.append("<null>");
			} else {
				sb.append(Contato);
			}

			sb.append("|");

			if (PessoaFisica == null) {
				sb.append("<null>");
			} else {
				sb.append(PessoaFisica);
			}

			sb.append("|");

			if (Pais == null) {
				sb.append("<null>");
			} else {
				sb.append(Pais);
			}

			sb.append("|");

			if (Inscricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Inscricao);
			}

			sb.append("|");

			if (InscricaoMunicipal == null) {
				sb.append("<null>");
			} else {
				sb.append(InscricaoMunicipal);
			}

			sb.append("|");

			if (Vendedor == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendedor);
			}

			sb.append("|");

			if (Comissao == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao);
			}

			sb.append("|");

			if (Conta == null) {
				sb.append("<null>");
			} else {
				sb.append(Conta);
			}

			sb.append("|");

			if (Banco1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco1);
			}

			sb.append("|");

			if (Banco2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco2);
			}

			sb.append("|");

			if (Banco3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco3);
			}

			sb.append("|");

			if (Banco4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco4);
			}

			sb.append("|");

			if (Banco5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco5);
			}

			sb.append("|");

			if (Transporte == null) {
				sb.append("<null>");
			} else {
				sb.append(Transporte);
			}

			sb.append("|");

			if (TipoFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoFrete);
			}

			sb.append("|");

			if (EnderecoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoCobranca);
			}

			sb.append("|");

			if (Condicao == null) {
				sb.append("<null>");
			} else {
				sb.append(Condicao);
			}

			sb.append("|");

			if (BairroCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroCobranca);
			}

			sb.append("|");

			if (Descricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Descricao);
			}

			sb.append("|");

			if (CEPCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPCobranca);
			}

			sb.append("|");

			if (EstadoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoCobranca);
			}

			sb.append("|");

			if (UCodigoMunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipioCobranca);
			}

			sb.append("|");

			if (MunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(MunicipioCobranca);
			}

			sb.append("|");

			if (Prioridade == null) {
				sb.append("<null>");
			} else {
				sb.append(Prioridade);
			}

			sb.append("|");

			if (Risco == null) {
				sb.append("<null>");
			} else {
				sb.append(Risco);
			}

			sb.append("|");

			if (LimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCredito);
			}

			sb.append("|");

			if (VencimentoLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(VencimentoLimiteCredito);
			}

			sb.append("|");

			if (Classe == null) {
				sb.append("<null>");
			} else {
				sb.append(Classe);
			}

			sb.append("|");

			if (LimiteCreditoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCreditoFinal);
			}

			sb.append("|");

			if (MoedaLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(MoedaLimiteCredito);
			}

			sb.append("|");

			if (MaiorSaldo == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorSaldo);
			}

			sb.append("|");

			if (MaiorCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorCompra);
			}

			sb.append("|");

			if (MediaAtraso == null) {
				sb.append("<null>");
			} else {
				sb.append(MediaAtraso);
			}

			sb.append("|");

			if (PrimeiraCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(PrimeiraCompra);
			}

			sb.append("|");

			if (UltimaCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaCompra);
			}

			sb.append("|");

			if (NumeroCompras == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroCompras);
			}

			sb.append("|");

			if (FormularioVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(FormularioVisita);
			}

			sb.append("|");

			if (TempoVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita);
			}

			sb.append("|");

			if (UltimaVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaVisita);
			}

			sb.append("|");

			if (TempoVisita1 == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita1);
			}

			sb.append("|");

			if (TempoPadrao == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoPadrao);
			}

			sb.append("|");

			if (ClassificacaoVenda == null) {
				sb.append("<null>");
			} else {
				sb.append(ClassificacaoVenda);
			}

			sb.append("|");

			if (Mensagem == null) {
				sb.append("<null>");
			} else {
				sb.append(Mensagem);
			}

			sb.append("|");

			if (SaldoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoTitulo);
			}

			sb.append("|");

			if (RecolheISS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheISS);
			}

			sb.append("|");

			if (SaldoPedidoLiberado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoLiberado);
			}

			sb.append("|");

			if (NumeroPagamentos == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPagamentos);
			}

			sb.append("|");

			if (Transferencia == null) {
				sb.append("<null>");
			} else {
				sb.append(Transferencia);
			}

			sb.append("|");

			if (SUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(SUFRAMA);
			}

			sb.append("|");

			if (ATR == null) {
				sb.append("<null>");
			} else {
				sb.append(ATR);
			}

			sb.append("|");

			if (ValorAcumulado == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorAcumulado);
			}

			sb.append("|");

			if (SaldoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedido);
			}

			sb.append("|");

			if (TituloProtestado == null) {
				sb.append("<null>");
			} else {
				sb.append(TituloProtestado);
			}

			sb.append("|");

			if (DataUltimoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoTitulo);
			}

			sb.append("|");

			if (ChequeDevolvido == null) {
				sb.append("<null>");
			} else {
				sb.append(ChequeDevolvido);
			}

			sb.append("|");

			if (CaixaPostal == null) {
				sb.append("<null>");
			} else {
				sb.append(CaixaPostal);
			}

			sb.append("|");

			if (Matriz == null) {
				sb.append("<null>");
			} else {
				sb.append(Matriz);
			}

			sb.append("|");

			if (DataUltimoCheque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoCheque);
			}

			sb.append("|");

			if (MaiorDuplicata == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorDuplicata);
			}

			sb.append("|");

			if (Tabela == null) {
				sb.append("<null>");
			} else {
				sb.append(Tabela);
			}

			sb.append("|");

			if (ISSIncluso == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSIncluso);
			}

			sb.append("|");

			if (SaldoDuplicataMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoDuplicataMoeda);
			}

			sb.append("|");

			if (PagamentoAtrasado == null) {
				sb.append("<null>");
			} else {
				sb.append(PagamentoAtrasado);
			}

			sb.append("|");

			if (Atividade == null) {
				sb.append("<null>");
			} else {
				sb.append(Atividade);
			}

			sb.append("|");

			if (Cargo1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo1);
			}

			sb.append("|");

			if (Cargo2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo2);
			}

			sb.append("|");

			if (Cargo3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo3);
			}

			sb.append("|");

			if (RepresentanteTecnico == null) {
				sb.append("<null>");
			} else {
				sb.append(RepresentanteTecnico);
			}

			sb.append("|");

			if (Supervisor == null) {
				sb.append("<null>");
			} else {
				sb.append(Supervisor);
			}

			sb.append("|");

			if (AliquotaIR == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaIR);
			}

			sb.append("|");

			if (Observacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao);
			}

			sb.append("|");

			if (RG == null) {
				sb.append("<null>");
			} else {
				sb.append(RG);
			}

			sb.append("|");

			if (CalculaSUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(CalculaSUFRAMA);
			}

			sb.append("|");

			if (DataNascimento == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento);
			}

			sb.append("|");

			if (SaldoPedidoBloqueado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoBloqueado);
			}

			sb.append("|");

			if (GrupoTributario == null) {
				sb.append("<null>");
			} else {
				sb.append(GrupoTributario);
			}

			sb.append("|");

			if (SegmentoAtividade1 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade1);
			}

			sb.append("|");

			if (ClienteFaturamento == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteFaturamento);
			}

			sb.append("|");

			if (EnderecoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoEntrega);
			}

			sb.append("|");

			if (UCompleto == null) {
				sb.append("<null>");
			} else {
				sb.append(UCompleto);
			}

			sb.append("|");

			if (BairroEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroEntrega);
			}

			sb.append("|");

			if (CEPEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPEntrada);
			}

			sb.append("|");

			if (EstadoEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoEntrada);
			}

			sb.append("|");

			if (CodigoLocal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoLocal);
			}

			sb.append("|");

			if (SegmentoAtividade2 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade2);
			}

			sb.append("|");

			if (TipoPessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPessoa);
			}

			sb.append("|");

			if (TipoISSRS == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoISSRS);
			}

			sb.append("|");

			if (SegmentoAtividade3 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade3);
			}

			sb.append("|");

			if (SegmentoAtividade4 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade4);
			}

			sb.append("|");

			if (SegmentoAtividade5 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade5);
			}

			sb.append("|");

			if (SegmentoAtividade6 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade6);
			}

			sb.append("|");

			if (SegmentoAtividade7 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade7);
			}

			sb.append("|");

			if (CodigoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoAgente);
			}

			sb.append("|");

			if (SegmentoAtividade8 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade8);
			}

			sb.append("|");

			if (CodigoMarcacao == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMarcacao);
			}

			sb.append("|");

			if (ComissaoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(ComissaoAgente);
			}

			sb.append("|");

			if (HomePage == null) {
				sb.append("<null>");
			} else {
				sb.append(HomePage);
			}

			sb.append("|");

			if (CodigoMunicipio1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio1);
			}

			sb.append("|");

			if (TipoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoCliente);
			}

			sb.append("|");

			if (Email == null) {
				sb.append("<null>");
			} else {
				sb.append(Email);
			}

			sb.append("|");

			if (Destino1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino1);
			}

			sb.append("|");

			if (Destino2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino2);
			}

			sb.append("|");

			if (Destino3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino3);
			}

			sb.append("|");

			if (CBO == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO);
			}

			sb.append("|");

			if (Observacao1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao1);
			}

			sb.append("|");

			if (CNAE == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE);
			}

			sb.append("|");

			if (CodigoHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoHistorico);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (DiasPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(DiasPagamento);
			}

			sb.append("|");

			if (Agregado == null) {
				sb.append("<null>");
			} else {
				sb.append(Agregado);
			}

			sb.append("|");

			if (RecolheINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheINSS);
			}

			sb.append("|");

			if (RecolheCOFINS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS);
			}

			sb.append("|");

			if (RecolheCSLL == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL);
			}

			sb.append("|");

			if (RecolhePIS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS);
			}

			sb.append("|");

			if (TipoPeriodo == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPeriodo);
			}

			sb.append("|");

			if (SaldoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinal);
			}

			sb.append("|");

			if (SaldoFinalMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinalMoeda);
			}

			sb.append("|");

			if (Contabilidade == null) {
				sb.append("<null>");
			} else {
				sb.append(Contabilidade);
			}

			sb.append("|");

			if (ClienteConvenio == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteConvenio);
			}

			sb.append("|");

			if (B2B == null) {
				sb.append("<null>");
			} else {
				sb.append(B2B);
			}

			sb.append("|");

			if (CBO1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO1);
			}

			sb.append("|");

			if (CNAE1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE1);
			}

			sb.append("|");

			if (SegmentoAtividade11 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade11);
			}

			sb.append("|");

			if (MensagemBloqueio == null) {
				sb.append("<null>");
			} else {
				sb.append(MensagemBloqueio);
			}

			sb.append("|");

			if (SubCodigo == null) {
				sb.append("<null>");
			} else {
				sb.append(SubCodigo);
			}

			sb.append("|");

			if (FilialDebito == null) {
				sb.append("<null>");
			} else {
				sb.append(FilialDebito);
			}

			sb.append("|");

			if (RecolhePIS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS1);
			}

			sb.append("|");

			if (RecolheCOFINS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS1);
			}

			sb.append("|");

			if (RecolheCSLL1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL1);
			}

			sb.append("|");

			if (ABICS == null) {
				sb.append("<null>");
			} else {
				sb.append(ABICS);
			}

			sb.append("|");

			if (Vinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(Vinculo);
			}

			sb.append("|");

			if (DataInicioVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataInicioVinculo);
			}

			sb.append("|");

			if (DataFimVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataFimVinculo);
			}

			sb.append("|");

			if (ISSRSLC == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSRSLC);
			}

			sb.append("|");

			if (TipoRegistro == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoRegistro);
			}

			sb.append("|");

			if (RFASEMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RFASEMT);
			}

			sb.append("|");

			if (RIMAMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RIMAMT);
			}

			sb.append("|");

			if (REGESIM == null) {
				sb.append("<null>");
			} else {
				sb.append(REGESIM);
			}

			sb.append("|");

			if (ContaReceita == null) {
				sb.append("<null>");
			} else {
				sb.append(ContaReceita);
			}

			sb.append("|");

			if (SIMPNacional == null) {
				sb.append("<null>");
			} else {
				sb.append(SIMPNacional);
			}

			sb.append("|");

			if (RecolheFETHAB == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFETHAB);
			}

			sb.append("|");

			if (RFABOV == null) {
				sb.append("<null>");
			} else {
				sb.append(RFABOV);
			}

			sb.append("|");

			if (RFACS == null) {
				sb.append("<null>");
			} else {
				sb.append(RFACS);
			}

			sb.append("|");

			if (DataNascimento1 == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento1);
			}

			sb.append("|");

			if (Contribuinte == null) {
				sb.append("<null>");
			} else {
				sb.append(Contribuinte);
			}

			sb.append("|");

			if (RecolheFMD == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFMD);
			}

			sb.append("|");

			if (TipoJuridico == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoJuridico);
			}

			sb.append("|");

			if (INCULT == null) {
				sb.append("<null>");
			} else {
				sb.append(INCULT);
			}

			sb.append("|");

			if (IDHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(IDHistorico);
			}

			sb.append("|");

			if (PrestacaoServico == null) {
				sb.append("<null>");
			} else {
				sb.append(PrestacaoServico);
			}

			sb.append("|");

			if (NUMRA == null) {
				sb.append("<null>");
			} else {
				sb.append(NUMRA);
			}

			sb.append("|");

			if (MINIRF == null) {
				sb.append("<null>");
			} else {
				sb.append(MINIRF);
			}

			sb.append("|");

			if (CODSIAF == null) {
				sb.append("<null>");
			} else {
				sb.append(CODSIAF);
			}

			sb.append("|");

			if (ENDNOT == null) {
				sb.append("<null>");
			} else {
				sb.append(ENDNOT);
			}

			sb.append("|");

			if (FOMEZER == null) {
				sb.append("<null>");
			} else {
				sb.append(FOMEZER);
			}

			sb.append("|");

			if (FRETISS == null) {
				sb.append("<null>");
			} else {
				sb.append(FRETISS);
			}

			sb.append("|");

			if (COMPLEM == null) {
				sb.append("<null>");
			} else {
				sb.append(COMPLEM);
			}

			sb.append("|");

			if (INCLTMG == null) {
				sb.append("<null>");
			} else {
				sb.append(INCLTMG);
			}

			sb.append("|");

			if (FILTRF == null) {
				sb.append("<null>");
			} else {
				sb.append(FILTRF);
			}

			sb.append("|");

			if (TRIBFAV == null) {
				sb.append("<null>");
			} else {
				sb.append(TRIBFAV);
			}

			sb.append("|");

			if (REGPB == null) {
				sb.append("<null>");
			} else {
				sb.append(REGPB);
			}

			sb.append("|");

			if (INOVAUT == null) {
				sb.append("<null>");
			} else {
				sb.append(INOVAUT);
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

			if (RecDeletado == null) {
				sb.append("<null>");
			} else {
				sb.append(RecDeletado);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Filial, other.Filial);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Codigo, other.Codigo);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Loja, other.Loja);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Ukey, other.Ukey);
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

	public static class Ajuste_dataStruct implements routines.system.IPersistableRow<Ajuste_dataStruct> {
		final static byte[] commonByteArrayLock_HYDRONORTH_Clientes = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Clientes = new byte[0];
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

		public String Codigo;

		public String getCodigo() {
			return this.Codigo;
		}

		public Boolean CodigoIsNullable() {
			return true;
		}

		public Boolean CodigoIsKey() {
			return true;
		}

		public Integer CodigoLength() {
			return 9;
		}

		public Integer CodigoPrecision() {
			return 0;
		}

		public String CodigoDefault() {

			return null;

		}

		public String CodigoComment() {

			return "";

		}

		public String CodigoPattern() {

			return "";

		}

		public String CodigoOriginalDbColumnName() {

			return "Codigo";

		}

		public String Loja;

		public String getLoja() {
			return this.Loja;
		}

		public Boolean LojaIsNullable() {
			return true;
		}

		public Boolean LojaIsKey() {
			return true;
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

		public String Pessoa;

		public String getPessoa() {
			return this.Pessoa;
		}

		public Boolean PessoaIsNullable() {
			return true;
		}

		public Boolean PessoaIsKey() {
			return false;
		}

		public Integer PessoaLength() {
			return 1;
		}

		public Integer PessoaPrecision() {
			return 0;
		}

		public String PessoaDefault() {

			return null;

		}

		public String PessoaComment() {

			return "";

		}

		public String PessoaPattern() {

			return "";

		}

		public String PessoaOriginalDbColumnName() {

			return "Pessoa";

		}

		public String Natureza;

		public String getNatureza() {
			return this.Natureza;
		}

		public Boolean NaturezaIsNullable() {
			return true;
		}

		public Boolean NaturezaIsKey() {
			return false;
		}

		public Integer NaturezaLength() {
			return 10;
		}

		public Integer NaturezaPrecision() {
			return 0;
		}

		public String NaturezaDefault() {

			return null;

		}

		public String NaturezaComment() {

			return "";

		}

		public String NaturezaPattern() {

			return "";

		}

		public String NaturezaOriginalDbColumnName() {

			return "Natureza";

		}

		public String CNPJ;

		public String getCNPJ() {
			return this.CNPJ;
		}

		public Boolean CNPJIsNullable() {
			return true;
		}

		public Boolean CNPJIsKey() {
			return false;
		}

		public Integer CNPJLength() {
			return 14;
		}

		public Integer CNPJPrecision() {
			return 0;
		}

		public String CNPJDefault() {

			return null;

		}

		public String CNPJComment() {

			return "";

		}

		public String CNPJPattern() {

			return "";

		}

		public String CNPJOriginalDbColumnName() {

			return "CNPJ";

		}

		public String Nome;

		public String getNome() {
			return this.Nome;
		}

		public Boolean NomeIsNullable() {
			return true;
		}

		public Boolean NomeIsKey() {
			return false;
		}

		public Integer NomeLength() {
			return 40;
		}

		public Integer NomePrecision() {
			return 0;
		}

		public String NomeDefault() {

			return null;

		}

		public String NomeComment() {

			return "";

		}

		public String NomePattern() {

			return "";

		}

		public String NomeOriginalDbColumnName() {

			return "Nome";

		}

		public String NomeReduzido;

		public String getNomeReduzido() {
			return this.NomeReduzido;
		}

		public Boolean NomeReduzidoIsNullable() {
			return true;
		}

		public Boolean NomeReduzidoIsKey() {
			return false;
		}

		public Integer NomeReduzidoLength() {
			return 20;
		}

		public Integer NomeReduzidoPrecision() {
			return 0;
		}

		public String NomeReduzidoDefault() {

			return null;

		}

		public String NomeReduzidoComment() {

			return "";

		}

		public String NomeReduzidoPattern() {

			return "";

		}

		public String NomeReduzidoOriginalDbColumnName() {

			return "NomeReduzido";

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
			return 80;
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

		public String Complemento;

		public String getComplemento() {
			return this.Complemento;
		}

		public Boolean ComplementoIsNullable() {
			return true;
		}

		public Boolean ComplementoIsKey() {
			return false;
		}

		public Integer ComplementoLength() {
			return 50;
		}

		public Integer ComplementoPrecision() {
			return 0;
		}

		public String ComplementoDefault() {

			return null;

		}

		public String ComplementoComment() {

			return "";

		}

		public String ComplementoPattern() {

			return "";

		}

		public String ComplementoOriginalDbColumnName() {

			return "Complemento";

		}

		public String Bairro;

		public String getBairro() {
			return this.Bairro;
		}

		public Boolean BairroIsNullable() {
			return true;
		}

		public Boolean BairroIsKey() {
			return false;
		}

		public Integer BairroLength() {
			return 40;
		}

		public Integer BairroPrecision() {
			return 0;
		}

		public String BairroDefault() {

			return null;

		}

		public String BairroComment() {

			return "";

		}

		public String BairroPattern() {

			return "";

		}

		public String BairroOriginalDbColumnName() {

			return "Bairro";

		}

		public String CEP;

		public String getCEP() {
			return this.CEP;
		}

		public Boolean CEPIsNullable() {
			return true;
		}

		public Boolean CEPIsKey() {
			return false;
		}

		public Integer CEPLength() {
			return 8;
		}

		public Integer CEPPrecision() {
			return 0;
		}

		public String CEPDefault() {

			return null;

		}

		public String CEPComment() {

			return "";

		}

		public String CEPPattern() {

			return "";

		}

		public String CEPOriginalDbColumnName() {

			return "CEP";

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

		public String Regiao;

		public String getRegiao() {
			return this.Regiao;
		}

		public Boolean RegiaoIsNullable() {
			return true;
		}

		public Boolean RegiaoIsKey() {
			return false;
		}

		public Integer RegiaoLength() {
			return 3;
		}

		public Integer RegiaoPrecision() {
			return 0;
		}

		public String RegiaoDefault() {

			return null;

		}

		public String RegiaoComment() {

			return "";

		}

		public String RegiaoPattern() {

			return "";

		}

		public String RegiaoOriginalDbColumnName() {

			return "Regiao";

		}

		public String DescricaoRegiao;

		public String getDescricaoRegiao() {
			return this.DescricaoRegiao;
		}

		public Boolean DescricaoRegiaoIsNullable() {
			return true;
		}

		public Boolean DescricaoRegiaoIsKey() {
			return false;
		}

		public Integer DescricaoRegiaoLength() {
			return 15;
		}

		public Integer DescricaoRegiaoPrecision() {
			return 0;
		}

		public String DescricaoRegiaoDefault() {

			return null;

		}

		public String DescricaoRegiaoComment() {

			return "";

		}

		public String DescricaoRegiaoPattern() {

			return "";

		}

		public String DescricaoRegiaoOriginalDbColumnName() {

			return "DescricaoRegiao";

		}

		public String CodigoMunicipio;

		public String getCodigoMunicipio() {
			return this.CodigoMunicipio;
		}

		public Boolean CodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean CodigoMunicipioIsKey() {
			return false;
		}

		public Integer CodigoMunicipioLength() {
			return 5;
		}

		public Integer CodigoMunicipioPrecision() {
			return 0;
		}

		public String CodigoMunicipioDefault() {

			return null;

		}

		public String CodigoMunicipioComment() {

			return "";

		}

		public String CodigoMunicipioPattern() {

			return "";

		}

		public String CodigoMunicipioOriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String Municipio;

		public String getMunicipio() {
			return this.Municipio;
		}

		public Boolean MunicipioIsNullable() {
			return true;
		}

		public Boolean MunicipioIsKey() {
			return false;
		}

		public Integer MunicipioLength() {
			return 60;
		}

		public Integer MunicipioPrecision() {
			return 0;
		}

		public String MunicipioDefault() {

			return null;

		}

		public String MunicipioComment() {

			return "";

		}

		public String MunicipioPattern() {

			return "";

		}

		public String MunicipioOriginalDbColumnName() {

			return "Municipio";

		}

		public String CodigoPais;

		public String getCodigoPais() {
			return this.CodigoPais;
		}

		public Boolean CodigoPaisIsNullable() {
			return true;
		}

		public Boolean CodigoPaisIsKey() {
			return false;
		}

		public Integer CodigoPaisLength() {
			return 5;
		}

		public Integer CodigoPaisPrecision() {
			return 0;
		}

		public String CodigoPaisDefault() {

			return null;

		}

		public String CodigoPaisComment() {

			return "";

		}

		public String CodigoPaisPattern() {

			return "";

		}

		public String CodigoPaisOriginalDbColumnName() {

			return "CodigoPais";

		}

		public String NomeEstado;

		public String getNomeEstado() {
			return this.NomeEstado;
		}

		public Boolean NomeEstadoIsNullable() {
			return true;
		}

		public Boolean NomeEstadoIsKey() {
			return false;
		}

		public Integer NomeEstadoLength() {
			return 20;
		}

		public Integer NomeEstadoPrecision() {
			return 0;
		}

		public String NomeEstadoDefault() {

			return null;

		}

		public String NomeEstadoComment() {

			return "";

		}

		public String NomeEstadoPattern() {

			return "";

		}

		public String NomeEstadoOriginalDbColumnName() {

			return "NomeEstado";

		}

		public String DDD;

		public String getDDD() {
			return this.DDD;
		}

		public Boolean DDDIsNullable() {
			return true;
		}

		public Boolean DDDIsKey() {
			return false;
		}

		public Integer DDDLength() {
			return 3;
		}

		public Integer DDDPrecision() {
			return 0;
		}

		public String DDDDefault() {

			return null;

		}

		public String DDDComment() {

			return "";

		}

		public String DDDPattern() {

			return "";

		}

		public String DDDOriginalDbColumnName() {

			return "DDD";

		}

		public String TributacaoFavoravel;

		public String getTributacaoFavoravel() {
			return this.TributacaoFavoravel;
		}

		public Boolean TributacaoFavoravelIsNullable() {
			return true;
		}

		public Boolean TributacaoFavoravelIsKey() {
			return false;
		}

		public Integer TributacaoFavoravelLength() {
			return 1;
		}

		public Integer TributacaoFavoravelPrecision() {
			return 0;
		}

		public String TributacaoFavoravelDefault() {

			return null;

		}

		public String TributacaoFavoravelComment() {

			return "";

		}

		public String TributacaoFavoravelPattern() {

			return "";

		}

		public String TributacaoFavoravelOriginalDbColumnName() {

			return "TributacaoFavoravel";

		}

		public String DDI;

		public String getDDI() {
			return this.DDI;
		}

		public Boolean DDIIsNullable() {
			return true;
		}

		public Boolean DDIIsKey() {
			return false;
		}

		public Integer DDILength() {
			return 6;
		}

		public Integer DDIPrecision() {
			return 0;
		}

		public String DDIDefault() {

			return null;

		}

		public String DDIComment() {

			return "";

		}

		public String DDIPattern() {

			return "";

		}

		public String DDIOriginalDbColumnName() {

			return "DDI";

		}

		public String CodIBGE;

		public String getCodIBGE() {
			return this.CodIBGE;
		}

		public Boolean CodIBGEIsNullable() {
			return true;
		}

		public Boolean CodIBGEIsKey() {
			return false;
		}

		public Integer CodIBGELength() {
			return 11;
		}

		public Integer CodIBGEPrecision() {
			return 0;
		}

		public String CodIBGEDefault() {

			return null;

		}

		public String CodIBGEComment() {

			return "";

		}

		public String CodIBGEPattern() {

			return "";

		}

		public String CodIBGEOriginalDbColumnName() {

			return "CodIBGE";

		}

		public String UCodigoMunicipio;

		public String getUCodigoMunicipio() {
			return this.UCodigoMunicipio;
		}

		public Boolean UCodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioLength() {
			return 5;
		}

		public Integer UCodigoMunicipioPrecision() {
			return 0;
		}

		public String UCodigoMunicipioDefault() {

			return null;

		}

		public String UCodigoMunicipioComment() {

			return "";

		}

		public String UCodigoMunicipioPattern() {

			return "";

		}

		public String UCodigoMunicipioOriginalDbColumnName() {

			return "UCodigoMunicipio";

		}

		public String EnderecoRecebimento;

		public String getEnderecoRecebimento() {
			return this.EnderecoRecebimento;
		}

		public Boolean EnderecoRecebimentoIsNullable() {
			return true;
		}

		public Boolean EnderecoRecebimentoIsKey() {
			return false;
		}

		public Integer EnderecoRecebimentoLength() {
			return 40;
		}

		public Integer EnderecoRecebimentoPrecision() {
			return 0;
		}

		public String EnderecoRecebimentoDefault() {

			return null;

		}

		public String EnderecoRecebimentoComment() {

			return "";

		}

		public String EnderecoRecebimentoPattern() {

			return "";

		}

		public String EnderecoRecebimentoOriginalDbColumnName() {

			return "EnderecoRecebimento";

		}

		public String Telefone;

		public String getTelefone() {
			return this.Telefone;
		}

		public Boolean TelefoneIsNullable() {
			return true;
		}

		public Boolean TelefoneIsKey() {
			return false;
		}

		public Integer TelefoneLength() {
			return 15;
		}

		public Integer TelefonePrecision() {
			return 0;
		}

		public String TelefoneDefault() {

			return null;

		}

		public String TelefoneComment() {

			return "";

		}

		public String TelefonePattern() {

			return "";

		}

		public String TelefoneOriginalDbColumnName() {

			return "Telefone";

		}

		public String FAX;

		public String getFAX() {
			return this.FAX;
		}

		public Boolean FAXIsNullable() {
			return true;
		}

		public Boolean FAXIsKey() {
			return false;
		}

		public Integer FAXLength() {
			return 15;
		}

		public Integer FAXPrecision() {
			return 0;
		}

		public String FAXDefault() {

			return null;

		}

		public String FAXComment() {

			return "";

		}

		public String FAXPattern() {

			return "";

		}

		public String FAXOriginalDbColumnName() {

			return "FAX";

		}

		public String FoneCP;

		public String getFoneCP() {
			return this.FoneCP;
		}

		public Boolean FoneCPIsNullable() {
			return true;
		}

		public Boolean FoneCPIsKey() {
			return false;
		}

		public Integer FoneCPLength() {
			return 15;
		}

		public Integer FoneCPPrecision() {
			return 0;
		}

		public String FoneCPDefault() {

			return null;

		}

		public String FoneCPComment() {

			return "";

		}

		public String FoneCPPattern() {

			return "";

		}

		public String FoneCPOriginalDbColumnName() {

			return "FoneCP";

		}

		public String Contato;

		public String getContato() {
			return this.Contato;
		}

		public Boolean ContatoIsNullable() {
			return true;
		}

		public Boolean ContatoIsKey() {
			return false;
		}

		public Integer ContatoLength() {
			return 80;
		}

		public Integer ContatoPrecision() {
			return 0;
		}

		public String ContatoDefault() {

			return null;

		}

		public String ContatoComment() {

			return "";

		}

		public String ContatoPattern() {

			return "";

		}

		public String ContatoOriginalDbColumnName() {

			return "Contato";

		}

		public String PessoaFisica;

		public String getPessoaFisica() {
			return this.PessoaFisica;
		}

		public Boolean PessoaFisicaIsNullable() {
			return true;
		}

		public Boolean PessoaFisicaIsKey() {
			return false;
		}

		public Integer PessoaFisicaLength() {
			return 18;
		}

		public Integer PessoaFisicaPrecision() {
			return 0;
		}

		public String PessoaFisicaDefault() {

			return null;

		}

		public String PessoaFisicaComment() {

			return "";

		}

		public String PessoaFisicaPattern() {

			return "";

		}

		public String PessoaFisicaOriginalDbColumnName() {

			return "PessoaFisica";

		}

		public String Pais;

		public String getPais() {
			return this.Pais;
		}

		public Boolean PaisIsNullable() {
			return true;
		}

		public Boolean PaisIsKey() {
			return false;
		}

		public Integer PaisLength() {
			return 3;
		}

		public Integer PaisPrecision() {
			return 0;
		}

		public String PaisDefault() {

			return null;

		}

		public String PaisComment() {

			return "";

		}

		public String PaisPattern() {

			return "";

		}

		public String PaisOriginalDbColumnName() {

			return "Pais";

		}

		public String Inscricao;

		public String getInscricao() {
			return this.Inscricao;
		}

		public Boolean InscricaoIsNullable() {
			return true;
		}

		public Boolean InscricaoIsKey() {
			return false;
		}

		public Integer InscricaoLength() {
			return 18;
		}

		public Integer InscricaoPrecision() {
			return 0;
		}

		public String InscricaoDefault() {

			return null;

		}

		public String InscricaoComment() {

			return "";

		}

		public String InscricaoPattern() {

			return "";

		}

		public String InscricaoOriginalDbColumnName() {

			return "Inscricao";

		}

		public String InscricaoMunicipal;

		public String getInscricaoMunicipal() {
			return this.InscricaoMunicipal;
		}

		public Boolean InscricaoMunicipalIsNullable() {
			return true;
		}

		public Boolean InscricaoMunicipalIsKey() {
			return false;
		}

		public Integer InscricaoMunicipalLength() {
			return 18;
		}

		public Integer InscricaoMunicipalPrecision() {
			return 0;
		}

		public String InscricaoMunicipalDefault() {

			return null;

		}

		public String InscricaoMunicipalComment() {

			return "";

		}

		public String InscricaoMunicipalPattern() {

			return "";

		}

		public String InscricaoMunicipalOriginalDbColumnName() {

			return "InscricaoMunicipal";

		}

		public String Vendedor;

		public String getVendedor() {
			return this.Vendedor;
		}

		public Boolean VendedorIsNullable() {
			return true;
		}

		public Boolean VendedorIsKey() {
			return false;
		}

		public Integer VendedorLength() {
			return 6;
		}

		public Integer VendedorPrecision() {
			return 0;
		}

		public String VendedorDefault() {

			return null;

		}

		public String VendedorComment() {

			return "";

		}

		public String VendedorPattern() {

			return "";

		}

		public String VendedorOriginalDbColumnName() {

			return "Vendedor";

		}

		public Double Comissao;

		public Double getComissao() {
			return this.Comissao;
		}

		public Boolean ComissaoIsNullable() {
			return true;
		}

		public Boolean ComissaoIsKey() {
			return false;
		}

		public Integer ComissaoLength() {
			return 15;
		}

		public Integer ComissaoPrecision() {
			return 0;
		}

		public String ComissaoDefault() {

			return "";

		}

		public String ComissaoComment() {

			return "";

		}

		public String ComissaoPattern() {

			return "";

		}

		public String ComissaoOriginalDbColumnName() {

			return "Comissao";

		}

		public String Conta;

		public String getConta() {
			return this.Conta;
		}

		public Boolean ContaIsNullable() {
			return true;
		}

		public Boolean ContaIsKey() {
			return false;
		}

		public Integer ContaLength() {
			return 20;
		}

		public Integer ContaPrecision() {
			return 0;
		}

		public String ContaDefault() {

			return null;

		}

		public String ContaComment() {

			return "";

		}

		public String ContaPattern() {

			return "";

		}

		public String ContaOriginalDbColumnName() {

			return "Conta";

		}

		public String Banco1;

		public String getBanco1() {
			return this.Banco1;
		}

		public Boolean Banco1IsNullable() {
			return true;
		}

		public Boolean Banco1IsKey() {
			return false;
		}

		public Integer Banco1Length() {
			return 3;
		}

		public Integer Banco1Precision() {
			return 0;
		}

		public String Banco1Default() {

			return null;

		}

		public String Banco1Comment() {

			return "";

		}

		public String Banco1Pattern() {

			return "";

		}

		public String Banco1OriginalDbColumnName() {

			return "Banco1";

		}

		public String Banco2;

		public String getBanco2() {
			return this.Banco2;
		}

		public Boolean Banco2IsNullable() {
			return true;
		}

		public Boolean Banco2IsKey() {
			return false;
		}

		public Integer Banco2Length() {
			return 3;
		}

		public Integer Banco2Precision() {
			return 0;
		}

		public String Banco2Default() {

			return null;

		}

		public String Banco2Comment() {

			return "";

		}

		public String Banco2Pattern() {

			return "";

		}

		public String Banco2OriginalDbColumnName() {

			return "Banco2";

		}

		public String Banco3;

		public String getBanco3() {
			return this.Banco3;
		}

		public Boolean Banco3IsNullable() {
			return true;
		}

		public Boolean Banco3IsKey() {
			return false;
		}

		public Integer Banco3Length() {
			return 3;
		}

		public Integer Banco3Precision() {
			return 0;
		}

		public String Banco3Default() {

			return null;

		}

		public String Banco3Comment() {

			return "";

		}

		public String Banco3Pattern() {

			return "";

		}

		public String Banco3OriginalDbColumnName() {

			return "Banco3";

		}

		public String Banco4;

		public String getBanco4() {
			return this.Banco4;
		}

		public Boolean Banco4IsNullable() {
			return true;
		}

		public Boolean Banco4IsKey() {
			return false;
		}

		public Integer Banco4Length() {
			return 3;
		}

		public Integer Banco4Precision() {
			return 0;
		}

		public String Banco4Default() {

			return null;

		}

		public String Banco4Comment() {

			return "";

		}

		public String Banco4Pattern() {

			return "";

		}

		public String Banco4OriginalDbColumnName() {

			return "Banco4";

		}

		public String Banco5;

		public String getBanco5() {
			return this.Banco5;
		}

		public Boolean Banco5IsNullable() {
			return true;
		}

		public Boolean Banco5IsKey() {
			return false;
		}

		public Integer Banco5Length() {
			return 3;
		}

		public Integer Banco5Precision() {
			return 0;
		}

		public String Banco5Default() {

			return null;

		}

		public String Banco5Comment() {

			return "";

		}

		public String Banco5Pattern() {

			return "";

		}

		public String Banco5OriginalDbColumnName() {

			return "Banco5";

		}

		public String Transporte;

		public String getTransporte() {
			return this.Transporte;
		}

		public Boolean TransporteIsNullable() {
			return true;
		}

		public Boolean TransporteIsKey() {
			return false;
		}

		public Integer TransporteLength() {
			return 6;
		}

		public Integer TransportePrecision() {
			return 0;
		}

		public String TransporteDefault() {

			return null;

		}

		public String TransporteComment() {

			return "";

		}

		public String TransportePattern() {

			return "";

		}

		public String TransporteOriginalDbColumnName() {

			return "Transporte";

		}

		public String TipoFrete;

		public String getTipoFrete() {
			return this.TipoFrete;
		}

		public Boolean TipoFreteIsNullable() {
			return true;
		}

		public Boolean TipoFreteIsKey() {
			return false;
		}

		public Integer TipoFreteLength() {
			return 1;
		}

		public Integer TipoFretePrecision() {
			return 0;
		}

		public String TipoFreteDefault() {

			return null;

		}

		public String TipoFreteComment() {

			return "";

		}

		public String TipoFretePattern() {

			return "";

		}

		public String TipoFreteOriginalDbColumnName() {

			return "TipoFrete";

		}

		public String EnderecoCobranca;

		public String getEnderecoCobranca() {
			return this.EnderecoCobranca;
		}

		public Boolean EnderecoCobrancaIsNullable() {
			return true;
		}

		public Boolean EnderecoCobrancaIsKey() {
			return false;
		}

		public Integer EnderecoCobrancaLength() {
			return 40;
		}

		public Integer EnderecoCobrancaPrecision() {
			return 0;
		}

		public String EnderecoCobrancaDefault() {

			return null;

		}

		public String EnderecoCobrancaComment() {

			return "";

		}

		public String EnderecoCobrancaPattern() {

			return "";

		}

		public String EnderecoCobrancaOriginalDbColumnName() {

			return "EnderecoCobranca";

		}

		public String Condicao;

		public String getCondicao() {
			return this.Condicao;
		}

		public Boolean CondicaoIsNullable() {
			return true;
		}

		public Boolean CondicaoIsKey() {
			return false;
		}

		public Integer CondicaoLength() {
			return 3;
		}

		public Integer CondicaoPrecision() {
			return 0;
		}

		public String CondicaoDefault() {

			return null;

		}

		public String CondicaoComment() {

			return "";

		}

		public String CondicaoPattern() {

			return "";

		}

		public String CondicaoOriginalDbColumnName() {

			return "Condicao";

		}

		public String BairroCobranca;

		public String getBairroCobranca() {
			return this.BairroCobranca;
		}

		public Boolean BairroCobrancaIsNullable() {
			return true;
		}

		public Boolean BairroCobrancaIsKey() {
			return false;
		}

		public Integer BairroCobrancaLength() {
			return 30;
		}

		public Integer BairroCobrancaPrecision() {
			return 0;
		}

		public String BairroCobrancaDefault() {

			return null;

		}

		public String BairroCobrancaComment() {

			return "";

		}

		public String BairroCobrancaPattern() {

			return "";

		}

		public String BairroCobrancaOriginalDbColumnName() {

			return "BairroCobranca";

		}

		public Double Descricao;

		public Double getDescricao() {
			return this.Descricao;
		}

		public Boolean DescricaoIsNullable() {
			return true;
		}

		public Boolean DescricaoIsKey() {
			return false;
		}

		public Integer DescricaoLength() {
			return 15;
		}

		public Integer DescricaoPrecision() {
			return 0;
		}

		public String DescricaoDefault() {

			return "";

		}

		public String DescricaoComment() {

			return "";

		}

		public String DescricaoPattern() {

			return "";

		}

		public String DescricaoOriginalDbColumnName() {

			return "Descricao";

		}

		public String CEPCobranca;

		public String getCEPCobranca() {
			return this.CEPCobranca;
		}

		public Boolean CEPCobrancaIsNullable() {
			return true;
		}

		public Boolean CEPCobrancaIsKey() {
			return false;
		}

		public Integer CEPCobrancaLength() {
			return 8;
		}

		public Integer CEPCobrancaPrecision() {
			return 0;
		}

		public String CEPCobrancaDefault() {

			return null;

		}

		public String CEPCobrancaComment() {

			return "";

		}

		public String CEPCobrancaPattern() {

			return "";

		}

		public String CEPCobrancaOriginalDbColumnName() {

			return "CEPCobranca";

		}

		public String EstadoCobranca;

		public String getEstadoCobranca() {
			return this.EstadoCobranca;
		}

		public Boolean EstadoCobrancaIsNullable() {
			return true;
		}

		public Boolean EstadoCobrancaIsKey() {
			return false;
		}

		public Integer EstadoCobrancaLength() {
			return 2;
		}

		public Integer EstadoCobrancaPrecision() {
			return 0;
		}

		public String EstadoCobrancaDefault() {

			return null;

		}

		public String EstadoCobrancaComment() {

			return "";

		}

		public String EstadoCobrancaPattern() {

			return "";

		}

		public String EstadoCobrancaOriginalDbColumnName() {

			return "EstadoCobranca";

		}

		public String UCodigoMunicipioCobranca;

		public String getUCodigoMunicipioCobranca() {
			return this.UCodigoMunicipioCobranca;
		}

		public Boolean UCodigoMunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioCobrancaIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioCobrancaLength() {
			return 5;
		}

		public Integer UCodigoMunicipioCobrancaPrecision() {
			return 0;
		}

		public String UCodigoMunicipioCobrancaDefault() {

			return null;

		}

		public String UCodigoMunicipioCobrancaComment() {

			return "";

		}

		public String UCodigoMunicipioCobrancaPattern() {

			return "";

		}

		public String UCodigoMunicipioCobrancaOriginalDbColumnName() {

			return "UCodigoMunicipioCobranca";

		}

		public String MunicipioCobranca;

		public String getMunicipioCobranca() {
			return this.MunicipioCobranca;
		}

		public Boolean MunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean MunicipioCobrancaIsKey() {
			return false;
		}

		public Integer MunicipioCobrancaLength() {
			return 60;
		}

		public Integer MunicipioCobrancaPrecision() {
			return 0;
		}

		public String MunicipioCobrancaDefault() {

			return null;

		}

		public String MunicipioCobrancaComment() {

			return "";

		}

		public String MunicipioCobrancaPattern() {

			return "";

		}

		public String MunicipioCobrancaOriginalDbColumnName() {

			return "MunicipioCobranca";

		}

		public String Prioridade;

		public String getPrioridade() {
			return this.Prioridade;
		}

		public Boolean PrioridadeIsNullable() {
			return true;
		}

		public Boolean PrioridadeIsKey() {
			return false;
		}

		public Integer PrioridadeLength() {
			return 1;
		}

		public Integer PrioridadePrecision() {
			return 0;
		}

		public String PrioridadeDefault() {

			return null;

		}

		public String PrioridadeComment() {

			return "";

		}

		public String PrioridadePattern() {

			return "";

		}

		public String PrioridadeOriginalDbColumnName() {

			return "Prioridade";

		}

		public String Risco;

		public String getRisco() {
			return this.Risco;
		}

		public Boolean RiscoIsNullable() {
			return true;
		}

		public Boolean RiscoIsKey() {
			return false;
		}

		public Integer RiscoLength() {
			return 1;
		}

		public Integer RiscoPrecision() {
			return 0;
		}

		public String RiscoDefault() {

			return null;

		}

		public String RiscoComment() {

			return "";

		}

		public String RiscoPattern() {

			return "";

		}

		public String RiscoOriginalDbColumnName() {

			return "Risco";

		}

		public Double LimiteCredito;

		public Double getLimiteCredito() {
			return this.LimiteCredito;
		}

		public Boolean LimiteCreditoIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoIsKey() {
			return false;
		}

		public Integer LimiteCreditoLength() {
			return 15;
		}

		public Integer LimiteCreditoPrecision() {
			return 0;
		}

		public String LimiteCreditoDefault() {

			return "";

		}

		public String LimiteCreditoComment() {

			return "";

		}

		public String LimiteCreditoPattern() {

			return "";

		}

		public String LimiteCreditoOriginalDbColumnName() {

			return "LimiteCredito";

		}

		public java.util.Date VencimentoLimiteCredito;

		public java.util.Date getVencimentoLimiteCredito() {
			return this.VencimentoLimiteCredito;
		}

		public Boolean VencimentoLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean VencimentoLimiteCreditoIsKey() {
			return false;
		}

		public Integer VencimentoLimiteCreditoLength() {
			return 8;
		}

		public Integer VencimentoLimiteCreditoPrecision() {
			return 0;
		}

		public String VencimentoLimiteCreditoDefault() {

			return null;

		}

		public String VencimentoLimiteCreditoComment() {

			return "";

		}

		public String VencimentoLimiteCreditoPattern() {

			return "dd-MM-yyyy";

		}

		public String VencimentoLimiteCreditoOriginalDbColumnName() {

			return "VencimentoLimiteCredito";

		}

		public String Classe;

		public String getClasse() {
			return this.Classe;
		}

		public Boolean ClasseIsNullable() {
			return true;
		}

		public Boolean ClasseIsKey() {
			return false;
		}

		public Integer ClasseLength() {
			return 1;
		}

		public Integer ClassePrecision() {
			return 0;
		}

		public String ClasseDefault() {

			return null;

		}

		public String ClasseComment() {

			return "";

		}

		public String ClassePattern() {

			return "";

		}

		public String ClasseOriginalDbColumnName() {

			return "Classe";

		}

		public Double LimiteCreditoFinal;

		public Double getLimiteCreditoFinal() {
			return this.LimiteCreditoFinal;
		}

		public Boolean LimiteCreditoFinalIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoFinalIsKey() {
			return false;
		}

		public Integer LimiteCreditoFinalLength() {
			return 15;
		}

		public Integer LimiteCreditoFinalPrecision() {
			return 0;
		}

		public String LimiteCreditoFinalDefault() {

			return "";

		}

		public String LimiteCreditoFinalComment() {

			return "";

		}

		public String LimiteCreditoFinalPattern() {

			return "";

		}

		public String LimiteCreditoFinalOriginalDbColumnName() {

			return "LimiteCreditoFinal";

		}

		public Double MoedaLimiteCredito;

		public Double getMoedaLimiteCredito() {
			return this.MoedaLimiteCredito;
		}

		public Boolean MoedaLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean MoedaLimiteCreditoIsKey() {
			return false;
		}

		public Integer MoedaLimiteCreditoLength() {
			return 15;
		}

		public Integer MoedaLimiteCreditoPrecision() {
			return 0;
		}

		public String MoedaLimiteCreditoDefault() {

			return "";

		}

		public String MoedaLimiteCreditoComment() {

			return "";

		}

		public String MoedaLimiteCreditoPattern() {

			return "";

		}

		public String MoedaLimiteCreditoOriginalDbColumnName() {

			return "MoedaLimiteCredito";

		}

		public Double MaiorSaldo;

		public Double getMaiorSaldo() {
			return this.MaiorSaldo;
		}

		public Boolean MaiorSaldoIsNullable() {
			return true;
		}

		public Boolean MaiorSaldoIsKey() {
			return false;
		}

		public Integer MaiorSaldoLength() {
			return 15;
		}

		public Integer MaiorSaldoPrecision() {
			return 0;
		}

		public String MaiorSaldoDefault() {

			return "";

		}

		public String MaiorSaldoComment() {

			return "";

		}

		public String MaiorSaldoPattern() {

			return "";

		}

		public String MaiorSaldoOriginalDbColumnName() {

			return "MaiorSaldo";

		}

		public Double MaiorCompra;

		public Double getMaiorCompra() {
			return this.MaiorCompra;
		}

		public Boolean MaiorCompraIsNullable() {
			return true;
		}

		public Boolean MaiorCompraIsKey() {
			return false;
		}

		public Integer MaiorCompraLength() {
			return 15;
		}

		public Integer MaiorCompraPrecision() {
			return 0;
		}

		public String MaiorCompraDefault() {

			return "";

		}

		public String MaiorCompraComment() {

			return "";

		}

		public String MaiorCompraPattern() {

			return "";

		}

		public String MaiorCompraOriginalDbColumnName() {

			return "MaiorCompra";

		}

		public Double MediaAtraso;

		public Double getMediaAtraso() {
			return this.MediaAtraso;
		}

		public Boolean MediaAtrasoIsNullable() {
			return true;
		}

		public Boolean MediaAtrasoIsKey() {
			return false;
		}

		public Integer MediaAtrasoLength() {
			return 15;
		}

		public Integer MediaAtrasoPrecision() {
			return 0;
		}

		public String MediaAtrasoDefault() {

			return "";

		}

		public String MediaAtrasoComment() {

			return "";

		}

		public String MediaAtrasoPattern() {

			return "";

		}

		public String MediaAtrasoOriginalDbColumnName() {

			return "MediaAtraso";

		}

		public java.util.Date PrimeiraCompra;

		public java.util.Date getPrimeiraCompra() {
			return this.PrimeiraCompra;
		}

		public Boolean PrimeiraCompraIsNullable() {
			return true;
		}

		public Boolean PrimeiraCompraIsKey() {
			return false;
		}

		public Integer PrimeiraCompraLength() {
			return 8;
		}

		public Integer PrimeiraCompraPrecision() {
			return 0;
		}

		public String PrimeiraCompraDefault() {

			return null;

		}

		public String PrimeiraCompraComment() {

			return "";

		}

		public String PrimeiraCompraPattern() {

			return "dd-MM-yyyy";

		}

		public String PrimeiraCompraOriginalDbColumnName() {

			return "PrimeiraCompra";

		}

		public java.util.Date UltimaCompra;

		public java.util.Date getUltimaCompra() {
			return this.UltimaCompra;
		}

		public Boolean UltimaCompraIsNullable() {
			return true;
		}

		public Boolean UltimaCompraIsKey() {
			return false;
		}

		public Integer UltimaCompraLength() {
			return 8;
		}

		public Integer UltimaCompraPrecision() {
			return 0;
		}

		public String UltimaCompraDefault() {

			return null;

		}

		public String UltimaCompraComment() {

			return "";

		}

		public String UltimaCompraPattern() {

			return "dd-MM-yyyy";

		}

		public String UltimaCompraOriginalDbColumnName() {

			return "UltimaCompra";

		}

		public Double NumeroCompras;

		public Double getNumeroCompras() {
			return this.NumeroCompras;
		}

		public Boolean NumeroComprasIsNullable() {
			return true;
		}

		public Boolean NumeroComprasIsKey() {
			return false;
		}

		public Integer NumeroComprasLength() {
			return 15;
		}

		public Integer NumeroComprasPrecision() {
			return 0;
		}

		public String NumeroComprasDefault() {

			return "";

		}

		public String NumeroComprasComment() {

			return "";

		}

		public String NumeroComprasPattern() {

			return "";

		}

		public String NumeroComprasOriginalDbColumnName() {

			return "NumeroCompras";

		}

		public String FormularioVisita;

		public String getFormularioVisita() {
			return this.FormularioVisita;
		}

		public Boolean FormularioVisitaIsNullable() {
			return true;
		}

		public Boolean FormularioVisitaIsKey() {
			return false;
		}

		public Integer FormularioVisitaLength() {
			return 3;
		}

		public Integer FormularioVisitaPrecision() {
			return 0;
		}

		public String FormularioVisitaDefault() {

			return null;

		}

		public String FormularioVisitaComment() {

			return "";

		}

		public String FormularioVisitaPattern() {

			return "";

		}

		public String FormularioVisitaOriginalDbColumnName() {

			return "FormularioVisita";

		}

		public Double TempoVisita;

		public Double getTempoVisita() {
			return this.TempoVisita;
		}

		public Boolean TempoVisitaIsNullable() {
			return true;
		}

		public Boolean TempoVisitaIsKey() {
			return false;
		}

		public Integer TempoVisitaLength() {
			return 15;
		}

		public Integer TempoVisitaPrecision() {
			return 0;
		}

		public String TempoVisitaDefault() {

			return "";

		}

		public String TempoVisitaComment() {

			return "";

		}

		public String TempoVisitaPattern() {

			return "";

		}

		public String TempoVisitaOriginalDbColumnName() {

			return "TempoVisita";

		}

		public String UltimaVisita;

		public String getUltimaVisita() {
			return this.UltimaVisita;
		}

		public Boolean UltimaVisitaIsNullable() {
			return true;
		}

		public Boolean UltimaVisitaIsKey() {
			return false;
		}

		public Integer UltimaVisitaLength() {
			return 8;
		}

		public Integer UltimaVisitaPrecision() {
			return 0;
		}

		public String UltimaVisitaDefault() {

			return null;

		}

		public String UltimaVisitaComment() {

			return "";

		}

		public String UltimaVisitaPattern() {

			return "";

		}

		public String UltimaVisitaOriginalDbColumnName() {

			return "UltimaVisita";

		}

		public String TempoVisita1;

		public String getTempoVisita1() {
			return this.TempoVisita1;
		}

		public Boolean TempoVisita1IsNullable() {
			return true;
		}

		public Boolean TempoVisita1IsKey() {
			return false;
		}

		public Integer TempoVisita1Length() {
			return 5;
		}

		public Integer TempoVisita1Precision() {
			return 0;
		}

		public String TempoVisita1Default() {

			return null;

		}

		public String TempoVisita1Comment() {

			return "";

		}

		public String TempoVisita1Pattern() {

			return "";

		}

		public String TempoVisita1OriginalDbColumnName() {

			return "TempoVisita";

		}

		public String TempoPadrao;

		public String getTempoPadrao() {
			return this.TempoPadrao;
		}

		public Boolean TempoPadraoIsNullable() {
			return true;
		}

		public Boolean TempoPadraoIsKey() {
			return false;
		}

		public Integer TempoPadraoLength() {
			return 5;
		}

		public Integer TempoPadraoPrecision() {
			return 0;
		}

		public String TempoPadraoDefault() {

			return null;

		}

		public String TempoPadraoComment() {

			return "";

		}

		public String TempoPadraoPattern() {

			return "";

		}

		public String TempoPadraoOriginalDbColumnName() {

			return "TempoPadrao";

		}

		public String ClassificacaoVenda;

		public String getClassificacaoVenda() {
			return this.ClassificacaoVenda;
		}

		public Boolean ClassificacaoVendaIsNullable() {
			return true;
		}

		public Boolean ClassificacaoVendaIsKey() {
			return false;
		}

		public Integer ClassificacaoVendaLength() {
			return 1;
		}

		public Integer ClassificacaoVendaPrecision() {
			return 0;
		}

		public String ClassificacaoVendaDefault() {

			return null;

		}

		public String ClassificacaoVendaComment() {

			return "";

		}

		public String ClassificacaoVendaPattern() {

			return "";

		}

		public String ClassificacaoVendaOriginalDbColumnName() {

			return "ClassificacaoVenda";

		}

		public String Mensagem;

		public String getMensagem() {
			return this.Mensagem;
		}

		public Boolean MensagemIsNullable() {
			return true;
		}

		public Boolean MensagemIsKey() {
			return false;
		}

		public Integer MensagemLength() {
			return 3;
		}

		public Integer MensagemPrecision() {
			return 0;
		}

		public String MensagemDefault() {

			return null;

		}

		public String MensagemComment() {

			return "";

		}

		public String MensagemPattern() {

			return "";

		}

		public String MensagemOriginalDbColumnName() {

			return "Mensagem";

		}

		public Double SaldoTitulo;

		public Double getSaldoTitulo() {
			return this.SaldoTitulo;
		}

		public Boolean SaldoTituloIsNullable() {
			return true;
		}

		public Boolean SaldoTituloIsKey() {
			return false;
		}

		public Integer SaldoTituloLength() {
			return 15;
		}

		public Integer SaldoTituloPrecision() {
			return 0;
		}

		public String SaldoTituloDefault() {

			return "";

		}

		public String SaldoTituloComment() {

			return "";

		}

		public String SaldoTituloPattern() {

			return "";

		}

		public String SaldoTituloOriginalDbColumnName() {

			return "SaldoTitulo";

		}

		public String RecolheISS;

		public String getRecolheISS() {
			return this.RecolheISS;
		}

		public Boolean RecolheISSIsNullable() {
			return true;
		}

		public Boolean RecolheISSIsKey() {
			return false;
		}

		public Integer RecolheISSLength() {
			return 1;
		}

		public Integer RecolheISSPrecision() {
			return 0;
		}

		public String RecolheISSDefault() {

			return null;

		}

		public String RecolheISSComment() {

			return "";

		}

		public String RecolheISSPattern() {

			return "";

		}

		public String RecolheISSOriginalDbColumnName() {

			return "RecolheISS";

		}

		public Double SaldoPedidoLiberado;

		public Double getSaldoPedidoLiberado() {
			return this.SaldoPedidoLiberado;
		}

		public Boolean SaldoPedidoLiberadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoLiberadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLiberadoLength() {
			return 15;
		}

		public Integer SaldoPedidoLiberadoPrecision() {
			return 0;
		}

		public String SaldoPedidoLiberadoDefault() {

			return "";

		}

		public String SaldoPedidoLiberadoComment() {

			return "";

		}

		public String SaldoPedidoLiberadoPattern() {

			return "";

		}

		public String SaldoPedidoLiberadoOriginalDbColumnName() {

			return "SaldoPedidoLiberado";

		}

		public Double NumeroPagamentos;

		public Double getNumeroPagamentos() {
			return this.NumeroPagamentos;
		}

		public Boolean NumeroPagamentosIsNullable() {
			return true;
		}

		public Boolean NumeroPagamentosIsKey() {
			return false;
		}

		public Integer NumeroPagamentosLength() {
			return 15;
		}

		public Integer NumeroPagamentosPrecision() {
			return 0;
		}

		public String NumeroPagamentosDefault() {

			return "";

		}

		public String NumeroPagamentosComment() {

			return "";

		}

		public String NumeroPagamentosPattern() {

			return "";

		}

		public String NumeroPagamentosOriginalDbColumnName() {

			return "NumeroPagamentos";

		}

		public String Transferencia;

		public String getTransferencia() {
			return this.Transferencia;
		}

		public Boolean TransferenciaIsNullable() {
			return true;
		}

		public Boolean TransferenciaIsKey() {
			return false;
		}

		public Integer TransferenciaLength() {
			return 1;
		}

		public Integer TransferenciaPrecision() {
			return 0;
		}

		public String TransferenciaDefault() {

			return null;

		}

		public String TransferenciaComment() {

			return "";

		}

		public String TransferenciaPattern() {

			return "";

		}

		public String TransferenciaOriginalDbColumnName() {

			return "Transferencia";

		}

		public String SUFRAMA;

		public String getSUFRAMA() {
			return this.SUFRAMA;
		}

		public Boolean SUFRAMAIsNullable() {
			return true;
		}

		public Boolean SUFRAMAIsKey() {
			return false;
		}

		public Integer SUFRAMALength() {
			return 12;
		}

		public Integer SUFRAMAPrecision() {
			return 0;
		}

		public String SUFRAMADefault() {

			return null;

		}

		public String SUFRAMAComment() {

			return "";

		}

		public String SUFRAMAPattern() {

			return "";

		}

		public String SUFRAMAOriginalDbColumnName() {

			return "SUFRAMA";

		}

		public Double ATR;

		public Double getATR() {
			return this.ATR;
		}

		public Boolean ATRIsNullable() {
			return true;
		}

		public Boolean ATRIsKey() {
			return false;
		}

		public Integer ATRLength() {
			return 15;
		}

		public Integer ATRPrecision() {
			return 0;
		}

		public String ATRDefault() {

			return "";

		}

		public String ATRComment() {

			return "";

		}

		public String ATRPattern() {

			return "";

		}

		public String ATROriginalDbColumnName() {

			return "ATR";

		}

		public Double ValorAcumulado;

		public Double getValorAcumulado() {
			return this.ValorAcumulado;
		}

		public Boolean ValorAcumuladoIsNullable() {
			return true;
		}

		public Boolean ValorAcumuladoIsKey() {
			return false;
		}

		public Integer ValorAcumuladoLength() {
			return 15;
		}

		public Integer ValorAcumuladoPrecision() {
			return 0;
		}

		public String ValorAcumuladoDefault() {

			return "";

		}

		public String ValorAcumuladoComment() {

			return "";

		}

		public String ValorAcumuladoPattern() {

			return "";

		}

		public String ValorAcumuladoOriginalDbColumnName() {

			return "ValorAcumulado";

		}

		public Double SaldoPedido;

		public Double getSaldoPedido() {
			return this.SaldoPedido;
		}

		public Boolean SaldoPedidoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLength() {
			return 15;
		}

		public Integer SaldoPedidoPrecision() {
			return 0;
		}

		public String SaldoPedidoDefault() {

			return "";

		}

		public String SaldoPedidoComment() {

			return "";

		}

		public String SaldoPedidoPattern() {

			return "";

		}

		public String SaldoPedidoOriginalDbColumnName() {

			return "SaldoPedido";

		}

		public Double TituloProtestado;

		public Double getTituloProtestado() {
			return this.TituloProtestado;
		}

		public Boolean TituloProtestadoIsNullable() {
			return true;
		}

		public Boolean TituloProtestadoIsKey() {
			return false;
		}

		public Integer TituloProtestadoLength() {
			return 15;
		}

		public Integer TituloProtestadoPrecision() {
			return 0;
		}

		public String TituloProtestadoDefault() {

			return "";

		}

		public String TituloProtestadoComment() {

			return "";

		}

		public String TituloProtestadoPattern() {

			return "";

		}

		public String TituloProtestadoOriginalDbColumnName() {

			return "TituloProtestado";

		}

		public java.util.Date DataUltimoTitulo;

		public java.util.Date getDataUltimoTitulo() {
			return this.DataUltimoTitulo;
		}

		public Boolean DataUltimoTituloIsNullable() {
			return true;
		}

		public Boolean DataUltimoTituloIsKey() {
			return false;
		}

		public Integer DataUltimoTituloLength() {
			return 8;
		}

		public Integer DataUltimoTituloPrecision() {
			return 0;
		}

		public String DataUltimoTituloDefault() {

			return null;

		}

		public String DataUltimoTituloComment() {

			return "";

		}

		public String DataUltimoTituloPattern() {

			return "dd-MM-yyyy";

		}

		public String DataUltimoTituloOriginalDbColumnName() {

			return "DataUltimoTitulo";

		}

		public Double ChequeDevolvido;

		public Double getChequeDevolvido() {
			return this.ChequeDevolvido;
		}

		public Boolean ChequeDevolvidoIsNullable() {
			return true;
		}

		public Boolean ChequeDevolvidoIsKey() {
			return false;
		}

		public Integer ChequeDevolvidoLength() {
			return 15;
		}

		public Integer ChequeDevolvidoPrecision() {
			return 0;
		}

		public String ChequeDevolvidoDefault() {

			return "";

		}

		public String ChequeDevolvidoComment() {

			return "";

		}

		public String ChequeDevolvidoPattern() {

			return "";

		}

		public String ChequeDevolvidoOriginalDbColumnName() {

			return "ChequeDevolvido";

		}

		public String CaixaPostal;

		public String getCaixaPostal() {
			return this.CaixaPostal;
		}

		public Boolean CaixaPostalIsNullable() {
			return true;
		}

		public Boolean CaixaPostalIsKey() {
			return false;
		}

		public Integer CaixaPostalLength() {
			return 20;
		}

		public Integer CaixaPostalPrecision() {
			return 0;
		}

		public String CaixaPostalDefault() {

			return null;

		}

		public String CaixaPostalComment() {

			return "";

		}

		public String CaixaPostalPattern() {

			return "";

		}

		public String CaixaPostalOriginalDbColumnName() {

			return "CaixaPostal";

		}

		public Double Matriz;

		public Double getMatriz() {
			return this.Matriz;
		}

		public Boolean MatrizIsNullable() {
			return true;
		}

		public Boolean MatrizIsKey() {
			return false;
		}

		public Integer MatrizLength() {
			return 15;
		}

		public Integer MatrizPrecision() {
			return 0;
		}

		public String MatrizDefault() {

			return "";

		}

		public String MatrizComment() {

			return "";

		}

		public String MatrizPattern() {

			return "";

		}

		public String MatrizOriginalDbColumnName() {

			return "Matriz";

		}

		public String DataUltimoCheque;

		public String getDataUltimoCheque() {
			return this.DataUltimoCheque;
		}

		public Boolean DataUltimoChequeIsNullable() {
			return true;
		}

		public Boolean DataUltimoChequeIsKey() {
			return false;
		}

		public Integer DataUltimoChequeLength() {
			return 8;
		}

		public Integer DataUltimoChequePrecision() {
			return 0;
		}

		public String DataUltimoChequeDefault() {

			return null;

		}

		public String DataUltimoChequeComment() {

			return "";

		}

		public String DataUltimoChequePattern() {

			return "";

		}

		public String DataUltimoChequeOriginalDbColumnName() {

			return "DataUltimoCheque";

		}

		public Double MaiorDuplicata;

		public Double getMaiorDuplicata() {
			return this.MaiorDuplicata;
		}

		public Boolean MaiorDuplicataIsNullable() {
			return true;
		}

		public Boolean MaiorDuplicataIsKey() {
			return false;
		}

		public Integer MaiorDuplicataLength() {
			return 15;
		}

		public Integer MaiorDuplicataPrecision() {
			return 0;
		}

		public String MaiorDuplicataDefault() {

			return "";

		}

		public String MaiorDuplicataComment() {

			return "";

		}

		public String MaiorDuplicataPattern() {

			return "";

		}

		public String MaiorDuplicataOriginalDbColumnName() {

			return "MaiorDuplicata";

		}

		public String Tabela;

		public String getTabela() {
			return this.Tabela;
		}

		public Boolean TabelaIsNullable() {
			return true;
		}

		public Boolean TabelaIsKey() {
			return false;
		}

		public Integer TabelaLength() {
			return 3;
		}

		public Integer TabelaPrecision() {
			return 0;
		}

		public String TabelaDefault() {

			return null;

		}

		public String TabelaComment() {

			return "";

		}

		public String TabelaPattern() {

			return "";

		}

		public String TabelaOriginalDbColumnName() {

			return "Tabela";

		}

		public String ISSIncluso;

		public String getISSIncluso() {
			return this.ISSIncluso;
		}

		public Boolean ISSInclusoIsNullable() {
			return true;
		}

		public Boolean ISSInclusoIsKey() {
			return false;
		}

		public Integer ISSInclusoLength() {
			return 1;
		}

		public Integer ISSInclusoPrecision() {
			return 0;
		}

		public String ISSInclusoDefault() {

			return null;

		}

		public String ISSInclusoComment() {

			return "";

		}

		public String ISSInclusoPattern() {

			return "";

		}

		public String ISSInclusoOriginalDbColumnName() {

			return "ISSIncluso";

		}

		public Double SaldoDuplicataMoeda;

		public Double getSaldoDuplicataMoeda() {
			return this.SaldoDuplicataMoeda;
		}

		public Boolean SaldoDuplicataMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoDuplicataMoedaIsKey() {
			return false;
		}

		public Integer SaldoDuplicataMoedaLength() {
			return 15;
		}

		public Integer SaldoDuplicataMoedaPrecision() {
			return 0;
		}

		public String SaldoDuplicataMoedaDefault() {

			return "";

		}

		public String SaldoDuplicataMoedaComment() {

			return "";

		}

		public String SaldoDuplicataMoedaPattern() {

			return "";

		}

		public String SaldoDuplicataMoedaOriginalDbColumnName() {

			return "SaldoDuplicataMoeda";

		}

		public Double PagamentoAtrasado;

		public Double getPagamentoAtrasado() {
			return this.PagamentoAtrasado;
		}

		public Boolean PagamentoAtrasadoIsNullable() {
			return true;
		}

		public Boolean PagamentoAtrasadoIsKey() {
			return false;
		}

		public Integer PagamentoAtrasadoLength() {
			return 15;
		}

		public Integer PagamentoAtrasadoPrecision() {
			return 0;
		}

		public String PagamentoAtrasadoDefault() {

			return "";

		}

		public String PagamentoAtrasadoComment() {

			return "";

		}

		public String PagamentoAtrasadoPattern() {

			return "";

		}

		public String PagamentoAtrasadoOriginalDbColumnName() {

			return "PagamentoAtrasado";

		}

		public String Atividade;

		public String getAtividade() {
			return this.Atividade;
		}

		public Boolean AtividadeIsNullable() {
			return true;
		}

		public Boolean AtividadeIsKey() {
			return false;
		}

		public Integer AtividadeLength() {
			return 7;
		}

		public Integer AtividadePrecision() {
			return 0;
		}

		public String AtividadeDefault() {

			return null;

		}

		public String AtividadeComment() {

			return "";

		}

		public String AtividadePattern() {

			return "";

		}

		public String AtividadeOriginalDbColumnName() {

			return "Atividade";

		}

		public String Cargo1;

		public String getCargo1() {
			return this.Cargo1;
		}

		public Boolean Cargo1IsNullable() {
			return true;
		}

		public Boolean Cargo1IsKey() {
			return false;
		}

		public Integer Cargo1Length() {
			return 15;
		}

		public Integer Cargo1Precision() {
			return 0;
		}

		public String Cargo1Default() {

			return null;

		}

		public String Cargo1Comment() {

			return "";

		}

		public String Cargo1Pattern() {

			return "";

		}

		public String Cargo1OriginalDbColumnName() {

			return "Cargo1";

		}

		public String Cargo2;

		public String getCargo2() {
			return this.Cargo2;
		}

		public Boolean Cargo2IsNullable() {
			return true;
		}

		public Boolean Cargo2IsKey() {
			return false;
		}

		public Integer Cargo2Length() {
			return 15;
		}

		public Integer Cargo2Precision() {
			return 0;
		}

		public String Cargo2Default() {

			return null;

		}

		public String Cargo2Comment() {

			return "";

		}

		public String Cargo2Pattern() {

			return "";

		}

		public String Cargo2OriginalDbColumnName() {

			return "Cargo2";

		}

		public String Cargo3;

		public String getCargo3() {
			return this.Cargo3;
		}

		public Boolean Cargo3IsNullable() {
			return true;
		}

		public Boolean Cargo3IsKey() {
			return false;
		}

		public Integer Cargo3Length() {
			return 15;
		}

		public Integer Cargo3Precision() {
			return 0;
		}

		public String Cargo3Default() {

			return null;

		}

		public String Cargo3Comment() {

			return "";

		}

		public String Cargo3Pattern() {

			return "";

		}

		public String Cargo3OriginalDbColumnName() {

			return "Cargo3";

		}

		public String RepresentanteTecnico;

		public String getRepresentanteTecnico() {
			return this.RepresentanteTecnico;
		}

		public Boolean RepresentanteTecnicoIsNullable() {
			return true;
		}

		public Boolean RepresentanteTecnicoIsKey() {
			return false;
		}

		public Integer RepresentanteTecnicoLength() {
			return 6;
		}

		public Integer RepresentanteTecnicoPrecision() {
			return 0;
		}

		public String RepresentanteTecnicoDefault() {

			return null;

		}

		public String RepresentanteTecnicoComment() {

			return "";

		}

		public String RepresentanteTecnicoPattern() {

			return "";

		}

		public String RepresentanteTecnicoOriginalDbColumnName() {

			return "RepresentanteTecnico";

		}

		public String Supervisor;

		public String getSupervisor() {
			return this.Supervisor;
		}

		public Boolean SupervisorIsNullable() {
			return true;
		}

		public Boolean SupervisorIsKey() {
			return false;
		}

		public Integer SupervisorLength() {
			return 6;
		}

		public Integer SupervisorPrecision() {
			return 0;
		}

		public String SupervisorDefault() {

			return null;

		}

		public String SupervisorComment() {

			return "";

		}

		public String SupervisorPattern() {

			return "";

		}

		public String SupervisorOriginalDbColumnName() {

			return "Supervisor";

		}

		public Double AliquotaIR;

		public Double getAliquotaIR() {
			return this.AliquotaIR;
		}

		public Boolean AliquotaIRIsNullable() {
			return true;
		}

		public Boolean AliquotaIRIsKey() {
			return false;
		}

		public Integer AliquotaIRLength() {
			return 15;
		}

		public Integer AliquotaIRPrecision() {
			return 0;
		}

		public String AliquotaIRDefault() {

			return "";

		}

		public String AliquotaIRComment() {

			return "";

		}

		public String AliquotaIRPattern() {

			return "";

		}

		public String AliquotaIROriginalDbColumnName() {

			return "AliquotaIR";

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
			return 40;
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

		public String RG;

		public String getRG() {
			return this.RG;
		}

		public Boolean RGIsNullable() {
			return true;
		}

		public Boolean RGIsKey() {
			return false;
		}

		public Integer RGLength() {
			return 15;
		}

		public Integer RGPrecision() {
			return 0;
		}

		public String RGDefault() {

			return null;

		}

		public String RGComment() {

			return "";

		}

		public String RGPattern() {

			return "";

		}

		public String RGOriginalDbColumnName() {

			return "RG";

		}

		public String CalculaSUFRAMA;

		public String getCalculaSUFRAMA() {
			return this.CalculaSUFRAMA;
		}

		public Boolean CalculaSUFRAMAIsNullable() {
			return true;
		}

		public Boolean CalculaSUFRAMAIsKey() {
			return false;
		}

		public Integer CalculaSUFRAMALength() {
			return 1;
		}

		public Integer CalculaSUFRAMAPrecision() {
			return 0;
		}

		public String CalculaSUFRAMADefault() {

			return null;

		}

		public String CalculaSUFRAMAComment() {

			return "";

		}

		public String CalculaSUFRAMAPattern() {

			return "";

		}

		public String CalculaSUFRAMAOriginalDbColumnName() {

			return "CalculaSUFRAMA";

		}

		public java.util.Date DataNascimento;

		public java.util.Date getDataNascimento() {
			return this.DataNascimento;
		}

		public Boolean DataNascimentoIsNullable() {
			return true;
		}

		public Boolean DataNascimentoIsKey() {
			return false;
		}

		public Integer DataNascimentoLength() {
			return 8;
		}

		public Integer DataNascimentoPrecision() {
			return 0;
		}

		public String DataNascimentoDefault() {

			return null;

		}

		public String DataNascimentoComment() {

			return "";

		}

		public String DataNascimentoPattern() {

			return "dd-MM-yyyy";

		}

		public String DataNascimentoOriginalDbColumnName() {

			return "DataNascimento";

		}

		public Double SaldoPedidoBloqueado;

		public Double getSaldoPedidoBloqueado() {
			return this.SaldoPedidoBloqueado;
		}

		public Boolean SaldoPedidoBloqueadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoBloqueadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoBloqueadoLength() {
			return 15;
		}

		public Integer SaldoPedidoBloqueadoPrecision() {
			return 0;
		}

		public String SaldoPedidoBloqueadoDefault() {

			return "";

		}

		public String SaldoPedidoBloqueadoComment() {

			return "";

		}

		public String SaldoPedidoBloqueadoPattern() {

			return "";

		}

		public String SaldoPedidoBloqueadoOriginalDbColumnName() {

			return "SaldoPedidoBloqueado";

		}

		public String GrupoTributario;

		public String getGrupoTributario() {
			return this.GrupoTributario;
		}

		public Boolean GrupoTributarioIsNullable() {
			return true;
		}

		public Boolean GrupoTributarioIsKey() {
			return false;
		}

		public Integer GrupoTributarioLength() {
			return 3;
		}

		public Integer GrupoTributarioPrecision() {
			return 0;
		}

		public String GrupoTributarioDefault() {

			return null;

		}

		public String GrupoTributarioComment() {

			return "";

		}

		public String GrupoTributarioPattern() {

			return "";

		}

		public String GrupoTributarioOriginalDbColumnName() {

			return "GrupoTributario";

		}

		public String SegmentoAtividade1;

		public String getSegmentoAtividade1() {
			return this.SegmentoAtividade1;
		}

		public Boolean SegmentoAtividade1IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade1IsKey() {
			return false;
		}

		public Integer SegmentoAtividade1Length() {
			return 6;
		}

		public Integer SegmentoAtividade1Precision() {
			return 0;
		}

		public String SegmentoAtividade1Default() {

			return null;

		}

		public String SegmentoAtividade1Comment() {

			return "";

		}

		public String SegmentoAtividade1Pattern() {

			return "";

		}

		public String SegmentoAtividade1OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String ClienteFaturamento;

		public String getClienteFaturamento() {
			return this.ClienteFaturamento;
		}

		public Boolean ClienteFaturamentoIsNullable() {
			return true;
		}

		public Boolean ClienteFaturamentoIsKey() {
			return false;
		}

		public Integer ClienteFaturamentoLength() {
			return 9;
		}

		public Integer ClienteFaturamentoPrecision() {
			return 0;
		}

		public String ClienteFaturamentoDefault() {

			return null;

		}

		public String ClienteFaturamentoComment() {

			return "";

		}

		public String ClienteFaturamentoPattern() {

			return "";

		}

		public String ClienteFaturamentoOriginalDbColumnName() {

			return "ClienteFaturamento";

		}

		public String EnderecoEntrega;

		public String getEnderecoEntrega() {
			return this.EnderecoEntrega;
		}

		public Boolean EnderecoEntregaIsNullable() {
			return true;
		}

		public Boolean EnderecoEntregaIsKey() {
			return false;
		}

		public Integer EnderecoEntregaLength() {
			return 80;
		}

		public Integer EnderecoEntregaPrecision() {
			return 0;
		}

		public String EnderecoEntregaDefault() {

			return null;

		}

		public String EnderecoEntregaComment() {

			return "";

		}

		public String EnderecoEntregaPattern() {

			return "";

		}

		public String EnderecoEntregaOriginalDbColumnName() {

			return "EnderecoEntrega";

		}

		public String UCompleto;

		public String getUCompleto() {
			return this.UCompleto;
		}

		public Boolean UCompletoIsNullable() {
			return true;
		}

		public Boolean UCompletoIsKey() {
			return false;
		}

		public Integer UCompletoLength() {
			return 50;
		}

		public Integer UCompletoPrecision() {
			return 0;
		}

		public String UCompletoDefault() {

			return null;

		}

		public String UCompletoComment() {

			return "";

		}

		public String UCompletoPattern() {

			return "";

		}

		public String UCompletoOriginalDbColumnName() {

			return "UCompleto";

		}

		public String BairroEntrega;

		public String getBairroEntrega() {
			return this.BairroEntrega;
		}

		public Boolean BairroEntregaIsNullable() {
			return true;
		}

		public Boolean BairroEntregaIsKey() {
			return false;
		}

		public Integer BairroEntregaLength() {
			return 20;
		}

		public Integer BairroEntregaPrecision() {
			return 0;
		}

		public String BairroEntregaDefault() {

			return null;

		}

		public String BairroEntregaComment() {

			return "";

		}

		public String BairroEntregaPattern() {

			return "";

		}

		public String BairroEntregaOriginalDbColumnName() {

			return "BairroEntrega";

		}

		public String CEPEntrada;

		public String getCEPEntrada() {
			return this.CEPEntrada;
		}

		public Boolean CEPEntradaIsNullable() {
			return true;
		}

		public Boolean CEPEntradaIsKey() {
			return false;
		}

		public Integer CEPEntradaLength() {
			return 8;
		}

		public Integer CEPEntradaPrecision() {
			return 0;
		}

		public String CEPEntradaDefault() {

			return null;

		}

		public String CEPEntradaComment() {

			return "";

		}

		public String CEPEntradaPattern() {

			return "";

		}

		public String CEPEntradaOriginalDbColumnName() {

			return "CEPEntrada";

		}

		public String EstadoEntrada;

		public String getEstadoEntrada() {
			return this.EstadoEntrada;
		}

		public Boolean EstadoEntradaIsNullable() {
			return true;
		}

		public Boolean EstadoEntradaIsKey() {
			return false;
		}

		public Integer EstadoEntradaLength() {
			return 2;
		}

		public Integer EstadoEntradaPrecision() {
			return 0;
		}

		public String EstadoEntradaDefault() {

			return null;

		}

		public String EstadoEntradaComment() {

			return "";

		}

		public String EstadoEntradaPattern() {

			return "";

		}

		public String EstadoEntradaOriginalDbColumnName() {

			return "EstadoEntrada";

		}

		public String CodigoLocal;

		public String getCodigoLocal() {
			return this.CodigoLocal;
		}

		public Boolean CodigoLocalIsNullable() {
			return true;
		}

		public Boolean CodigoLocalIsKey() {
			return false;
		}

		public Integer CodigoLocalLength() {
			return 8;
		}

		public Integer CodigoLocalPrecision() {
			return 0;
		}

		public String CodigoLocalDefault() {

			return null;

		}

		public String CodigoLocalComment() {

			return "";

		}

		public String CodigoLocalPattern() {

			return "";

		}

		public String CodigoLocalOriginalDbColumnName() {

			return "CodigoLocal";

		}

		public String SegmentoAtividade2;

		public String getSegmentoAtividade2() {
			return this.SegmentoAtividade2;
		}

		public Boolean SegmentoAtividade2IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade2IsKey() {
			return false;
		}

		public Integer SegmentoAtividade2Length() {
			return 6;
		}

		public Integer SegmentoAtividade2Precision() {
			return 0;
		}

		public String SegmentoAtividade2Default() {

			return null;

		}

		public String SegmentoAtividade2Comment() {

			return "";

		}

		public String SegmentoAtividade2Pattern() {

			return "";

		}

		public String SegmentoAtividade2OriginalDbColumnName() {

			return "SegmentoAtividade2";

		}

		public String TipoPessoa;

		public String getTipoPessoa() {
			return this.TipoPessoa;
		}

		public Boolean TipoPessoaIsNullable() {
			return true;
		}

		public Boolean TipoPessoaIsKey() {
			return false;
		}

		public Integer TipoPessoaLength() {
			return 2;
		}

		public Integer TipoPessoaPrecision() {
			return 0;
		}

		public String TipoPessoaDefault() {

			return null;

		}

		public String TipoPessoaComment() {

			return "";

		}

		public String TipoPessoaPattern() {

			return "";

		}

		public String TipoPessoaOriginalDbColumnName() {

			return "TipoPessoa";

		}

		public String TipoISSRS;

		public String getTipoISSRS() {
			return this.TipoISSRS;
		}

		public Boolean TipoISSRSIsNullable() {
			return true;
		}

		public Boolean TipoISSRSIsKey() {
			return false;
		}

		public Integer TipoISSRSLength() {
			return 2;
		}

		public Integer TipoISSRSPrecision() {
			return 0;
		}

		public String TipoISSRSDefault() {

			return null;

		}

		public String TipoISSRSComment() {

			return "";

		}

		public String TipoISSRSPattern() {

			return "";

		}

		public String TipoISSRSOriginalDbColumnName() {

			return "TipoISSRS";

		}

		public String SegmentoAtividade3;

		public String getSegmentoAtividade3() {
			return this.SegmentoAtividade3;
		}

		public Boolean SegmentoAtividade3IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade3IsKey() {
			return false;
		}

		public Integer SegmentoAtividade3Length() {
			return 6;
		}

		public Integer SegmentoAtividade3Precision() {
			return 0;
		}

		public String SegmentoAtividade3Default() {

			return null;

		}

		public String SegmentoAtividade3Comment() {

			return "";

		}

		public String SegmentoAtividade3Pattern() {

			return "";

		}

		public String SegmentoAtividade3OriginalDbColumnName() {

			return "SegmentoAtividade3";

		}

		public String SegmentoAtividade4;

		public String getSegmentoAtividade4() {
			return this.SegmentoAtividade4;
		}

		public Boolean SegmentoAtividade4IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade4IsKey() {
			return false;
		}

		public Integer SegmentoAtividade4Length() {
			return 6;
		}

		public Integer SegmentoAtividade4Precision() {
			return 0;
		}

		public String SegmentoAtividade4Default() {

			return null;

		}

		public String SegmentoAtividade4Comment() {

			return "";

		}

		public String SegmentoAtividade4Pattern() {

			return "";

		}

		public String SegmentoAtividade4OriginalDbColumnName() {

			return "SegmentoAtividade4";

		}

		public String SegmentoAtividade5;

		public String getSegmentoAtividade5() {
			return this.SegmentoAtividade5;
		}

		public Boolean SegmentoAtividade5IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade5IsKey() {
			return false;
		}

		public Integer SegmentoAtividade5Length() {
			return 6;
		}

		public Integer SegmentoAtividade5Precision() {
			return 0;
		}

		public String SegmentoAtividade5Default() {

			return null;

		}

		public String SegmentoAtividade5Comment() {

			return "";

		}

		public String SegmentoAtividade5Pattern() {

			return "";

		}

		public String SegmentoAtividade5OriginalDbColumnName() {

			return "SegmentoAtividade5";

		}

		public String SegmentoAtividade6;

		public String getSegmentoAtividade6() {
			return this.SegmentoAtividade6;
		}

		public Boolean SegmentoAtividade6IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade6IsKey() {
			return false;
		}

		public Integer SegmentoAtividade6Length() {
			return 6;
		}

		public Integer SegmentoAtividade6Precision() {
			return 0;
		}

		public String SegmentoAtividade6Default() {

			return null;

		}

		public String SegmentoAtividade6Comment() {

			return "";

		}

		public String SegmentoAtividade6Pattern() {

			return "";

		}

		public String SegmentoAtividade6OriginalDbColumnName() {

			return "SegmentoAtividade6";

		}

		public String SegmentoAtividade7;

		public String getSegmentoAtividade7() {
			return this.SegmentoAtividade7;
		}

		public Boolean SegmentoAtividade7IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade7IsKey() {
			return false;
		}

		public Integer SegmentoAtividade7Length() {
			return 6;
		}

		public Integer SegmentoAtividade7Precision() {
			return 0;
		}

		public String SegmentoAtividade7Default() {

			return null;

		}

		public String SegmentoAtividade7Comment() {

			return "";

		}

		public String SegmentoAtividade7Pattern() {

			return "";

		}

		public String SegmentoAtividade7OriginalDbColumnName() {

			return "SegmentoAtividade7";

		}

		public String CodigoAgente;

		public String getCodigoAgente() {
			return this.CodigoAgente;
		}

		public Boolean CodigoAgenteIsNullable() {
			return true;
		}

		public Boolean CodigoAgenteIsKey() {
			return false;
		}

		public Integer CodigoAgenteLength() {
			return 9;
		}

		public Integer CodigoAgentePrecision() {
			return 0;
		}

		public String CodigoAgenteDefault() {

			return null;

		}

		public String CodigoAgenteComment() {

			return "";

		}

		public String CodigoAgentePattern() {

			return "";

		}

		public String CodigoAgenteOriginalDbColumnName() {

			return "CodigoAgente";

		}

		public String SegmentoAtividade8;

		public String getSegmentoAtividade8() {
			return this.SegmentoAtividade8;
		}

		public Boolean SegmentoAtividade8IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade8IsKey() {
			return false;
		}

		public Integer SegmentoAtividade8Length() {
			return 6;
		}

		public Integer SegmentoAtividade8Precision() {
			return 0;
		}

		public String SegmentoAtividade8Default() {

			return null;

		}

		public String SegmentoAtividade8Comment() {

			return "";

		}

		public String SegmentoAtividade8Pattern() {

			return "";

		}

		public String SegmentoAtividade8OriginalDbColumnName() {

			return "SegmentoAtividade8";

		}

		public String CodigoMarcacao;

		public String getCodigoMarcacao() {
			return this.CodigoMarcacao;
		}

		public Boolean CodigoMarcacaoIsNullable() {
			return true;
		}

		public Boolean CodigoMarcacaoIsKey() {
			return false;
		}

		public Integer CodigoMarcacaoLength() {
			return 6;
		}

		public Integer CodigoMarcacaoPrecision() {
			return 0;
		}

		public String CodigoMarcacaoDefault() {

			return null;

		}

		public String CodigoMarcacaoComment() {

			return "";

		}

		public String CodigoMarcacaoPattern() {

			return "";

		}

		public String CodigoMarcacaoOriginalDbColumnName() {

			return "CodigoMarcacao";

		}

		public Double ComissaoAgente;

		public Double getComissaoAgente() {
			return this.ComissaoAgente;
		}

		public Boolean ComissaoAgenteIsNullable() {
			return true;
		}

		public Boolean ComissaoAgenteIsKey() {
			return false;
		}

		public Integer ComissaoAgenteLength() {
			return 15;
		}

		public Integer ComissaoAgentePrecision() {
			return 0;
		}

		public String ComissaoAgenteDefault() {

			return "";

		}

		public String ComissaoAgenteComment() {

			return "";

		}

		public String ComissaoAgentePattern() {

			return "";

		}

		public String ComissaoAgenteOriginalDbColumnName() {

			return "ComissaoAgente";

		}

		public String HomePage;

		public String getHomePage() {
			return this.HomePage;
		}

		public Boolean HomePageIsNullable() {
			return true;
		}

		public Boolean HomePageIsKey() {
			return false;
		}

		public Integer HomePageLength() {
			return 30;
		}

		public Integer HomePagePrecision() {
			return 0;
		}

		public String HomePageDefault() {

			return null;

		}

		public String HomePageComment() {

			return "";

		}

		public String HomePagePattern() {

			return "";

		}

		public String HomePageOriginalDbColumnName() {

			return "HomePage";

		}

		public String CodigoMunicipio1;

		public String getCodigoMunicipio1() {
			return this.CodigoMunicipio1;
		}

		public Boolean CodigoMunicipio1IsNullable() {
			return true;
		}

		public Boolean CodigoMunicipio1IsKey() {
			return false;
		}

		public Integer CodigoMunicipio1Length() {
			return 5;
		}

		public Integer CodigoMunicipio1Precision() {
			return 0;
		}

		public String CodigoMunicipio1Default() {

			return null;

		}

		public String CodigoMunicipio1Comment() {

			return "";

		}

		public String CodigoMunicipio1Pattern() {

			return "";

		}

		public String CodigoMunicipio1OriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String TipoCliente;

		public String getTipoCliente() {
			return this.TipoCliente;
		}

		public Boolean TipoClienteIsNullable() {
			return true;
		}

		public Boolean TipoClienteIsKey() {
			return false;
		}

		public Integer TipoClienteLength() {
			return 1;
		}

		public Integer TipoClientePrecision() {
			return 0;
		}

		public String TipoClienteDefault() {

			return null;

		}

		public String TipoClienteComment() {

			return "";

		}

		public String TipoClientePattern() {

			return "";

		}

		public String TipoClienteOriginalDbColumnName() {

			return "TipoCliente";

		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public Boolean EmailIsNullable() {
			return true;
		}

		public Boolean EmailIsKey() {
			return false;
		}

		public Integer EmailLength() {
			return 250;
		}

		public Integer EmailPrecision() {
			return 0;
		}

		public String EmailDefault() {

			return null;

		}

		public String EmailComment() {

			return "";

		}

		public String EmailPattern() {

			return "";

		}

		public String EmailOriginalDbColumnName() {

			return "Email";

		}

		public String Destino1;

		public String getDestino1() {
			return this.Destino1;
		}

		public Boolean Destino1IsNullable() {
			return true;
		}

		public Boolean Destino1IsKey() {
			return false;
		}

		public Integer Destino1Length() {
			return 3;
		}

		public Integer Destino1Precision() {
			return 0;
		}

		public String Destino1Default() {

			return null;

		}

		public String Destino1Comment() {

			return "";

		}

		public String Destino1Pattern() {

			return "";

		}

		public String Destino1OriginalDbColumnName() {

			return "Destino1";

		}

		public String Destino2;

		public String getDestino2() {
			return this.Destino2;
		}

		public Boolean Destino2IsNullable() {
			return true;
		}

		public Boolean Destino2IsKey() {
			return false;
		}

		public Integer Destino2Length() {
			return 3;
		}

		public Integer Destino2Precision() {
			return 0;
		}

		public String Destino2Default() {

			return null;

		}

		public String Destino2Comment() {

			return "";

		}

		public String Destino2Pattern() {

			return "";

		}

		public String Destino2OriginalDbColumnName() {

			return "Destino2";

		}

		public String Destino3;

		public String getDestino3() {
			return this.Destino3;
		}

		public Boolean Destino3IsNullable() {
			return true;
		}

		public Boolean Destino3IsKey() {
			return false;
		}

		public Integer Destino3Length() {
			return 3;
		}

		public Integer Destino3Precision() {
			return 0;
		}

		public String Destino3Default() {

			return null;

		}

		public String Destino3Comment() {

			return "";

		}

		public String Destino3Pattern() {

			return "";

		}

		public String Destino3OriginalDbColumnName() {

			return "Destino3";

		}

		public String CBO;

		public String getCBO() {
			return this.CBO;
		}

		public Boolean CBOIsNullable() {
			return true;
		}

		public Boolean CBOIsKey() {
			return false;
		}

		public Integer CBOLength() {
			return 7;
		}

		public Integer CBOPrecision() {
			return 0;
		}

		public String CBODefault() {

			return null;

		}

		public String CBOComment() {

			return "";

		}

		public String CBOPattern() {

			return "";

		}

		public String CBOOriginalDbColumnName() {

			return "CBO";

		}

		public String Observacao1;

		public String getObservacao1() {
			return this.Observacao1;
		}

		public Boolean Observacao1IsNullable() {
			return true;
		}

		public Boolean Observacao1IsKey() {
			return false;
		}

		public Integer Observacao1Length() {
			return 6;
		}

		public Integer Observacao1Precision() {
			return 0;
		}

		public String Observacao1Default() {

			return null;

		}

		public String Observacao1Comment() {

			return "";

		}

		public String Observacao1Pattern() {

			return "";

		}

		public String Observacao1OriginalDbColumnName() {

			return "Observacao";

		}

		public String CNAE;

		public String getCNAE() {
			return this.CNAE;
		}

		public Boolean CNAEIsNullable() {
			return true;
		}

		public Boolean CNAEIsKey() {
			return false;
		}

		public Integer CNAELength() {
			return 9;
		}

		public Integer CNAEPrecision() {
			return 0;
		}

		public String CNAEDefault() {

			return null;

		}

		public String CNAEComment() {

			return "";

		}

		public String CNAEPattern() {

			return "";

		}

		public String CNAEOriginalDbColumnName() {

			return "CNAE";

		}

		public String CodigoHistorico;

		public String getCodigoHistorico() {
			return this.CodigoHistorico;
		}

		public Boolean CodigoHistoricoIsNullable() {
			return true;
		}

		public Boolean CodigoHistoricoIsKey() {
			return false;
		}

		public Integer CodigoHistoricoLength() {
			return 6;
		}

		public Integer CodigoHistoricoPrecision() {
			return 0;
		}

		public String CodigoHistoricoDefault() {

			return null;

		}

		public String CodigoHistoricoComment() {

			return "";

		}

		public String CodigoHistoricoPattern() {

			return "";

		}

		public String CodigoHistoricoOriginalDbColumnName() {

			return "CodigoHistorico";

		}

		public String CondicaoPagamento;

		public String getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 5;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return null;

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

		public Double DiasPagamento;

		public Double getDiasPagamento() {
			return this.DiasPagamento;
		}

		public Boolean DiasPagamentoIsNullable() {
			return true;
		}

		public Boolean DiasPagamentoIsKey() {
			return false;
		}

		public Integer DiasPagamentoLength() {
			return 15;
		}

		public Integer DiasPagamentoPrecision() {
			return 0;
		}

		public String DiasPagamentoDefault() {

			return "";

		}

		public String DiasPagamentoComment() {

			return "";

		}

		public String DiasPagamentoPattern() {

			return "";

		}

		public String DiasPagamentoOriginalDbColumnName() {

			return "DiasPagamento";

		}

		public String Agregado;

		public String getAgregado() {
			return this.Agregado;
		}

		public Boolean AgregadoIsNullable() {
			return true;
		}

		public Boolean AgregadoIsKey() {
			return false;
		}

		public Integer AgregadoLength() {
			return 4;
		}

		public Integer AgregadoPrecision() {
			return 0;
		}

		public String AgregadoDefault() {

			return null;

		}

		public String AgregadoComment() {

			return "";

		}

		public String AgregadoPattern() {

			return "";

		}

		public String AgregadoOriginalDbColumnName() {

			return "Agregado";

		}

		public String RecolheINSS;

		public String getRecolheINSS() {
			return this.RecolheINSS;
		}

		public Boolean RecolheINSSIsNullable() {
			return true;
		}

		public Boolean RecolheINSSIsKey() {
			return false;
		}

		public Integer RecolheINSSLength() {
			return 1;
		}

		public Integer RecolheINSSPrecision() {
			return 0;
		}

		public String RecolheINSSDefault() {

			return null;

		}

		public String RecolheINSSComment() {

			return "";

		}

		public String RecolheINSSPattern() {

			return "";

		}

		public String RecolheINSSOriginalDbColumnName() {

			return "RecolheINSS";

		}

		public String RecolheCOFINS;

		public String getRecolheCOFINS() {
			return this.RecolheCOFINS;
		}

		public Boolean RecolheCOFINSIsNullable() {
			return true;
		}

		public Boolean RecolheCOFINSIsKey() {
			return false;
		}

		public Integer RecolheCOFINSLength() {
			return 1;
		}

		public Integer RecolheCOFINSPrecision() {
			return 0;
		}

		public String RecolheCOFINSDefault() {

			return null;

		}

		public String RecolheCOFINSComment() {

			return "";

		}

		public String RecolheCOFINSPattern() {

			return "";

		}

		public String RecolheCOFINSOriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL;

		public String getRecolheCSLL() {
			return this.RecolheCSLL;
		}

		public Boolean RecolheCSLLIsNullable() {
			return true;
		}

		public Boolean RecolheCSLLIsKey() {
			return false;
		}

		public Integer RecolheCSLLLength() {
			return 1;
		}

		public Integer RecolheCSLLPrecision() {
			return 0;
		}

		public String RecolheCSLLDefault() {

			return null;

		}

		public String RecolheCSLLComment() {

			return "";

		}

		public String RecolheCSLLPattern() {

			return "";

		}

		public String RecolheCSLLOriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String RecolhePIS;

		public String getRecolhePIS() {
			return this.RecolhePIS;
		}

		public Boolean RecolhePISIsNullable() {
			return true;
		}

		public Boolean RecolhePISIsKey() {
			return false;
		}

		public Integer RecolhePISLength() {
			return 1;
		}

		public Integer RecolhePISPrecision() {
			return 0;
		}

		public String RecolhePISDefault() {

			return null;

		}

		public String RecolhePISComment() {

			return "";

		}

		public String RecolhePISPattern() {

			return "";

		}

		public String RecolhePISOriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String TipoPeriodo;

		public String getTipoPeriodo() {
			return this.TipoPeriodo;
		}

		public Boolean TipoPeriodoIsNullable() {
			return true;
		}

		public Boolean TipoPeriodoIsKey() {
			return false;
		}

		public Integer TipoPeriodoLength() {
			return 2;
		}

		public Integer TipoPeriodoPrecision() {
			return 0;
		}

		public String TipoPeriodoDefault() {

			return null;

		}

		public String TipoPeriodoComment() {

			return "";

		}

		public String TipoPeriodoPattern() {

			return "";

		}

		public String TipoPeriodoOriginalDbColumnName() {

			return "TipoPeriodo";

		}

		public Double SaldoFinal;

		public Double getSaldoFinal() {
			return this.SaldoFinal;
		}

		public Boolean SaldoFinalIsNullable() {
			return true;
		}

		public Boolean SaldoFinalIsKey() {
			return false;
		}

		public Integer SaldoFinalLength() {
			return 15;
		}

		public Integer SaldoFinalPrecision() {
			return 0;
		}

		public String SaldoFinalDefault() {

			return "";

		}

		public String SaldoFinalComment() {

			return "";

		}

		public String SaldoFinalPattern() {

			return "";

		}

		public String SaldoFinalOriginalDbColumnName() {

			return "SaldoFinal";

		}

		public Double SaldoFinalMoeda;

		public Double getSaldoFinalMoeda() {
			return this.SaldoFinalMoeda;
		}

		public Boolean SaldoFinalMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoFinalMoedaIsKey() {
			return false;
		}

		public Integer SaldoFinalMoedaLength() {
			return 15;
		}

		public Integer SaldoFinalMoedaPrecision() {
			return 0;
		}

		public String SaldoFinalMoedaDefault() {

			return "";

		}

		public String SaldoFinalMoedaComment() {

			return "";

		}

		public String SaldoFinalMoedaPattern() {

			return "";

		}

		public String SaldoFinalMoedaOriginalDbColumnName() {

			return "SaldoFinalMoeda";

		}

		public String Contabilidade;

		public String getContabilidade() {
			return this.Contabilidade;
		}

		public Boolean ContabilidadeIsNullable() {
			return true;
		}

		public Boolean ContabilidadeIsKey() {
			return false;
		}

		public Integer ContabilidadeLength() {
			return 15;
		}

		public Integer ContabilidadePrecision() {
			return 0;
		}

		public String ContabilidadeDefault() {

			return null;

		}

		public String ContabilidadeComment() {

			return "";

		}

		public String ContabilidadePattern() {

			return "";

		}

		public String ContabilidadeOriginalDbColumnName() {

			return "Contabilidade";

		}

		public String ClienteConvenio;

		public String getClienteConvenio() {
			return this.ClienteConvenio;
		}

		public Boolean ClienteConvenioIsNullable() {
			return true;
		}

		public Boolean ClienteConvenioIsKey() {
			return false;
		}

		public Integer ClienteConvenioLength() {
			return 1;
		}

		public Integer ClienteConvenioPrecision() {
			return 0;
		}

		public String ClienteConvenioDefault() {

			return null;

		}

		public String ClienteConvenioComment() {

			return "";

		}

		public String ClienteConvenioPattern() {

			return "";

		}

		public String ClienteConvenioOriginalDbColumnName() {

			return "ClienteConvenio";

		}

		public String B2B;

		public String getB2B() {
			return this.B2B;
		}

		public Boolean B2BIsNullable() {
			return true;
		}

		public Boolean B2BIsKey() {
			return false;
		}

		public Integer B2BLength() {
			return 1;
		}

		public Integer B2BPrecision() {
			return 0;
		}

		public String B2BDefault() {

			return null;

		}

		public String B2BComment() {

			return "";

		}

		public String B2BPattern() {

			return "";

		}

		public String B2BOriginalDbColumnName() {

			return "B2B";

		}

		public String CBO1;

		public String getCBO1() {
			return this.CBO1;
		}

		public Boolean CBO1IsNullable() {
			return true;
		}

		public Boolean CBO1IsKey() {
			return false;
		}

		public Integer CBO1Length() {
			return 7;
		}

		public Integer CBO1Precision() {
			return 0;
		}

		public String CBO1Default() {

			return null;

		}

		public String CBO1Comment() {

			return "";

		}

		public String CBO1Pattern() {

			return "";

		}

		public String CBO1OriginalDbColumnName() {

			return "CBO";

		}

		public String CNAE1;

		public String getCNAE1() {
			return this.CNAE1;
		}

		public Boolean CNAE1IsNullable() {
			return true;
		}

		public Boolean CNAE1IsKey() {
			return false;
		}

		public Integer CNAE1Length() {
			return 9;
		}

		public Integer CNAE1Precision() {
			return 0;
		}

		public String CNAE1Default() {

			return null;

		}

		public String CNAE1Comment() {

			return "";

		}

		public String CNAE1Pattern() {

			return "";

		}

		public String CNAE1OriginalDbColumnName() {

			return "CNAE";

		}

		public String SegmentoAtividade11;

		public String getSegmentoAtividade11() {
			return this.SegmentoAtividade11;
		}

		public Boolean SegmentoAtividade11IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade11IsKey() {
			return false;
		}

		public Integer SegmentoAtividade11Length() {
			return 6;
		}

		public Integer SegmentoAtividade11Precision() {
			return 0;
		}

		public String SegmentoAtividade11Default() {

			return null;

		}

		public String SegmentoAtividade11Comment() {

			return "";

		}

		public String SegmentoAtividade11Pattern() {

			return "";

		}

		public String SegmentoAtividade11OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String MensagemBloqueio;

		public String getMensagemBloqueio() {
			return this.MensagemBloqueio;
		}

		public Boolean MensagemBloqueioIsNullable() {
			return true;
		}

		public Boolean MensagemBloqueioIsKey() {
			return false;
		}

		public Integer MensagemBloqueioLength() {
			return 1;
		}

		public Integer MensagemBloqueioPrecision() {
			return 0;
		}

		public String MensagemBloqueioDefault() {

			return null;

		}

		public String MensagemBloqueioComment() {

			return "";

		}

		public String MensagemBloqueioPattern() {

			return "";

		}

		public String MensagemBloqueioOriginalDbColumnName() {

			return "MensagemBloqueio";

		}

		public String SubCodigo;

		public String getSubCodigo() {
			return this.SubCodigo;
		}

		public Boolean SubCodigoIsNullable() {
			return true;
		}

		public Boolean SubCodigoIsKey() {
			return false;
		}

		public Integer SubCodigoLength() {
			return 1;
		}

		public Integer SubCodigoPrecision() {
			return 0;
		}

		public String SubCodigoDefault() {

			return null;

		}

		public String SubCodigoComment() {

			return "";

		}

		public String SubCodigoPattern() {

			return "";

		}

		public String SubCodigoOriginalDbColumnName() {

			return "SubCodigo";

		}

		public String FilialDebito;

		public String getFilialDebito() {
			return this.FilialDebito;
		}

		public Boolean FilialDebitoIsNullable() {
			return true;
		}

		public Boolean FilialDebitoIsKey() {
			return false;
		}

		public Integer FilialDebitoLength() {
			return 2;
		}

		public Integer FilialDebitoPrecision() {
			return 0;
		}

		public String FilialDebitoDefault() {

			return null;

		}

		public String FilialDebitoComment() {

			return "";

		}

		public String FilialDebitoPattern() {

			return "";

		}

		public String FilialDebitoOriginalDbColumnName() {

			return "FilialDebito";

		}

		public String RecolhePIS1;

		public String getRecolhePIS1() {
			return this.RecolhePIS1;
		}

		public Boolean RecolhePIS1IsNullable() {
			return true;
		}

		public Boolean RecolhePIS1IsKey() {
			return false;
		}

		public Integer RecolhePIS1Length() {
			return 1;
		}

		public Integer RecolhePIS1Precision() {
			return 0;
		}

		public String RecolhePIS1Default() {

			return null;

		}

		public String RecolhePIS1Comment() {

			return "";

		}

		public String RecolhePIS1Pattern() {

			return "";

		}

		public String RecolhePIS1OriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String RecolheCOFINS1;

		public String getRecolheCOFINS1() {
			return this.RecolheCOFINS1;
		}

		public Boolean RecolheCOFINS1IsNullable() {
			return true;
		}

		public Boolean RecolheCOFINS1IsKey() {
			return false;
		}

		public Integer RecolheCOFINS1Length() {
			return 1;
		}

		public Integer RecolheCOFINS1Precision() {
			return 0;
		}

		public String RecolheCOFINS1Default() {

			return null;

		}

		public String RecolheCOFINS1Comment() {

			return "";

		}

		public String RecolheCOFINS1Pattern() {

			return "";

		}

		public String RecolheCOFINS1OriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL1;

		public String getRecolheCSLL1() {
			return this.RecolheCSLL1;
		}

		public Boolean RecolheCSLL1IsNullable() {
			return true;
		}

		public Boolean RecolheCSLL1IsKey() {
			return false;
		}

		public Integer RecolheCSLL1Length() {
			return 1;
		}

		public Integer RecolheCSLL1Precision() {
			return 0;
		}

		public String RecolheCSLL1Default() {

			return null;

		}

		public String RecolheCSLL1Comment() {

			return "";

		}

		public String RecolheCSLL1Pattern() {

			return "";

		}

		public String RecolheCSLL1OriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String ABICS;

		public String getABICS() {
			return this.ABICS;
		}

		public Boolean ABICSIsNullable() {
			return true;
		}

		public Boolean ABICSIsKey() {
			return false;
		}

		public Integer ABICSLength() {
			return 4;
		}

		public Integer ABICSPrecision() {
			return 0;
		}

		public String ABICSDefault() {

			return null;

		}

		public String ABICSComment() {

			return "";

		}

		public String ABICSPattern() {

			return "";

		}

		public String ABICSOriginalDbColumnName() {

			return "ABICS";

		}

		public String Vinculo;

		public String getVinculo() {
			return this.Vinculo;
		}

		public Boolean VinculoIsNullable() {
			return true;
		}

		public Boolean VinculoIsKey() {
			return false;
		}

		public Integer VinculoLength() {
			return 2;
		}

		public Integer VinculoPrecision() {
			return 0;
		}

		public String VinculoDefault() {

			return null;

		}

		public String VinculoComment() {

			return "";

		}

		public String VinculoPattern() {

			return "";

		}

		public String VinculoOriginalDbColumnName() {

			return "Vinculo";

		}

		public String DataInicioVinculo;

		public String getDataInicioVinculo() {
			return this.DataInicioVinculo;
		}

		public Boolean DataInicioVinculoIsNullable() {
			return true;
		}

		public Boolean DataInicioVinculoIsKey() {
			return false;
		}

		public Integer DataInicioVinculoLength() {
			return 8;
		}

		public Integer DataInicioVinculoPrecision() {
			return 0;
		}

		public String DataInicioVinculoDefault() {

			return null;

		}

		public String DataInicioVinculoComment() {

			return "";

		}

		public String DataInicioVinculoPattern() {

			return "";

		}

		public String DataInicioVinculoOriginalDbColumnName() {

			return "DataInicioVinculo";

		}

		public String DataFimVinculo;

		public String getDataFimVinculo() {
			return this.DataFimVinculo;
		}

		public Boolean DataFimVinculoIsNullable() {
			return true;
		}

		public Boolean DataFimVinculoIsKey() {
			return false;
		}

		public Integer DataFimVinculoLength() {
			return 8;
		}

		public Integer DataFimVinculoPrecision() {
			return 0;
		}

		public String DataFimVinculoDefault() {

			return null;

		}

		public String DataFimVinculoComment() {

			return "";

		}

		public String DataFimVinculoPattern() {

			return "";

		}

		public String DataFimVinculoOriginalDbColumnName() {

			return "DataFimVinculo";

		}

		public String ISSRSLC;

		public String getISSRSLC() {
			return this.ISSRSLC;
		}

		public Boolean ISSRSLCIsNullable() {
			return true;
		}

		public Boolean ISSRSLCIsKey() {
			return false;
		}

		public Integer ISSRSLCLength() {
			return 1;
		}

		public Integer ISSRSLCPrecision() {
			return 0;
		}

		public String ISSRSLCDefault() {

			return null;

		}

		public String ISSRSLCComment() {

			return "";

		}

		public String ISSRSLCPattern() {

			return "";

		}

		public String ISSRSLCOriginalDbColumnName() {

			return "ISSRSLC";

		}

		public String TipoRegistro;

		public String getTipoRegistro() {
			return this.TipoRegistro;
		}

		public Boolean TipoRegistroIsNullable() {
			return true;
		}

		public Boolean TipoRegistroIsKey() {
			return false;
		}

		public Integer TipoRegistroLength() {
			return 1;
		}

		public Integer TipoRegistroPrecision() {
			return 0;
		}

		public String TipoRegistroDefault() {

			return null;

		}

		public String TipoRegistroComment() {

			return "";

		}

		public String TipoRegistroPattern() {

			return "";

		}

		public String TipoRegistroOriginalDbColumnName() {

			return "TipoRegistro";

		}

		public String RFASEMT;

		public String getRFASEMT() {
			return this.RFASEMT;
		}

		public Boolean RFASEMTIsNullable() {
			return true;
		}

		public Boolean RFASEMTIsKey() {
			return false;
		}

		public Integer RFASEMTLength() {
			return 1;
		}

		public Integer RFASEMTPrecision() {
			return 0;
		}

		public String RFASEMTDefault() {

			return null;

		}

		public String RFASEMTComment() {

			return "";

		}

		public String RFASEMTPattern() {

			return "";

		}

		public String RFASEMTOriginalDbColumnName() {

			return "RFASEMT";

		}

		public String RIMAMT;

		public String getRIMAMT() {
			return this.RIMAMT;
		}

		public Boolean RIMAMTIsNullable() {
			return true;
		}

		public Boolean RIMAMTIsKey() {
			return false;
		}

		public Integer RIMAMTLength() {
			return 1;
		}

		public Integer RIMAMTPrecision() {
			return 0;
		}

		public String RIMAMTDefault() {

			return null;

		}

		public String RIMAMTComment() {

			return "";

		}

		public String RIMAMTPattern() {

			return "";

		}

		public String RIMAMTOriginalDbColumnName() {

			return "RIMAMT";

		}

		public String REGESIM;

		public String getREGESIM() {
			return this.REGESIM;
		}

		public Boolean REGESIMIsNullable() {
			return true;
		}

		public Boolean REGESIMIsKey() {
			return false;
		}

		public Integer REGESIMLength() {
			return 1;
		}

		public Integer REGESIMPrecision() {
			return 0;
		}

		public String REGESIMDefault() {

			return null;

		}

		public String REGESIMComment() {

			return "";

		}

		public String REGESIMPattern() {

			return "";

		}

		public String REGESIMOriginalDbColumnName() {

			return "REGESIM";

		}

		public String ContaReceita;

		public String getContaReceita() {
			return this.ContaReceita;
		}

		public Boolean ContaReceitaIsNullable() {
			return true;
		}

		public Boolean ContaReceitaIsKey() {
			return false;
		}

		public Integer ContaReceitaLength() {
			return 1;
		}

		public Integer ContaReceitaPrecision() {
			return 0;
		}

		public String ContaReceitaDefault() {

			return null;

		}

		public String ContaReceitaComment() {

			return "";

		}

		public String ContaReceitaPattern() {

			return "";

		}

		public String ContaReceitaOriginalDbColumnName() {

			return "ContaReceita";

		}

		public String SIMPNacional;

		public String getSIMPNacional() {
			return this.SIMPNacional;
		}

		public Boolean SIMPNacionalIsNullable() {
			return true;
		}

		public Boolean SIMPNacionalIsKey() {
			return false;
		}

		public Integer SIMPNacionalLength() {
			return 1;
		}

		public Integer SIMPNacionalPrecision() {
			return 0;
		}

		public String SIMPNacionalDefault() {

			return null;

		}

		public String SIMPNacionalComment() {

			return "";

		}

		public String SIMPNacionalPattern() {

			return "";

		}

		public String SIMPNacionalOriginalDbColumnName() {

			return "SIMPNacional";

		}

		public String RecolheFETHAB;

		public String getRecolheFETHAB() {
			return this.RecolheFETHAB;
		}

		public Boolean RecolheFETHABIsNullable() {
			return true;
		}

		public Boolean RecolheFETHABIsKey() {
			return false;
		}

		public Integer RecolheFETHABLength() {
			return 1;
		}

		public Integer RecolheFETHABPrecision() {
			return 0;
		}

		public String RecolheFETHABDefault() {

			return null;

		}

		public String RecolheFETHABComment() {

			return "";

		}

		public String RecolheFETHABPattern() {

			return "";

		}

		public String RecolheFETHABOriginalDbColumnName() {

			return "RecolheFETHAB";

		}

		public String RFABOV;

		public String getRFABOV() {
			return this.RFABOV;
		}

		public Boolean RFABOVIsNullable() {
			return true;
		}

		public Boolean RFABOVIsKey() {
			return false;
		}

		public Integer RFABOVLength() {
			return 1;
		}

		public Integer RFABOVPrecision() {
			return 0;
		}

		public String RFABOVDefault() {

			return null;

		}

		public String RFABOVComment() {

			return "";

		}

		public String RFABOVPattern() {

			return "";

		}

		public String RFABOVOriginalDbColumnName() {

			return "RFABOV";

		}

		public String RFACS;

		public String getRFACS() {
			return this.RFACS;
		}

		public Boolean RFACSIsNullable() {
			return true;
		}

		public Boolean RFACSIsKey() {
			return false;
		}

		public Integer RFACSLength() {
			return 1;
		}

		public Integer RFACSPrecision() {
			return 0;
		}

		public String RFACSDefault() {

			return null;

		}

		public String RFACSComment() {

			return "";

		}

		public String RFACSPattern() {

			return "";

		}

		public String RFACSOriginalDbColumnName() {

			return "RFACS";

		}

		public String DataNascimento1;

		public String getDataNascimento1() {
			return this.DataNascimento1;
		}

		public Boolean DataNascimento1IsNullable() {
			return true;
		}

		public Boolean DataNascimento1IsKey() {
			return false;
		}

		public Integer DataNascimento1Length() {
			return 8;
		}

		public Integer DataNascimento1Precision() {
			return 0;
		}

		public String DataNascimento1Default() {

			return null;

		}

		public String DataNascimento1Comment() {

			return "";

		}

		public String DataNascimento1Pattern() {

			return "";

		}

		public String DataNascimento1OriginalDbColumnName() {

			return "DataNascimento";

		}

		public String Contribuinte;

		public String getContribuinte() {
			return this.Contribuinte;
		}

		public Boolean ContribuinteIsNullable() {
			return true;
		}

		public Boolean ContribuinteIsKey() {
			return false;
		}

		public Integer ContribuinteLength() {
			return 1;
		}

		public Integer ContribuintePrecision() {
			return 0;
		}

		public String ContribuinteDefault() {

			return null;

		}

		public String ContribuinteComment() {

			return "";

		}

		public String ContribuintePattern() {

			return "";

		}

		public String ContribuinteOriginalDbColumnName() {

			return "Contribuinte";

		}

		public String RecolheFMD;

		public String getRecolheFMD() {
			return this.RecolheFMD;
		}

		public Boolean RecolheFMDIsNullable() {
			return true;
		}

		public Boolean RecolheFMDIsKey() {
			return false;
		}

		public Integer RecolheFMDLength() {
			return 1;
		}

		public Integer RecolheFMDPrecision() {
			return 0;
		}

		public String RecolheFMDDefault() {

			return null;

		}

		public String RecolheFMDComment() {

			return "";

		}

		public String RecolheFMDPattern() {

			return "";

		}

		public String RecolheFMDOriginalDbColumnName() {

			return "RecolheFMD";

		}

		public String TipoJuridico;

		public String getTipoJuridico() {
			return this.TipoJuridico;
		}

		public Boolean TipoJuridicoIsNullable() {
			return true;
		}

		public Boolean TipoJuridicoIsKey() {
			return false;
		}

		public Integer TipoJuridicoLength() {
			return 1;
		}

		public Integer TipoJuridicoPrecision() {
			return 0;
		}

		public String TipoJuridicoDefault() {

			return null;

		}

		public String TipoJuridicoComment() {

			return "";

		}

		public String TipoJuridicoPattern() {

			return "";

		}

		public String TipoJuridicoOriginalDbColumnName() {

			return "TipoJuridico";

		}

		public String INCULT;

		public String getINCULT() {
			return this.INCULT;
		}

		public Boolean INCULTIsNullable() {
			return true;
		}

		public Boolean INCULTIsKey() {
			return false;
		}

		public Integer INCULTLength() {
			return 1;
		}

		public Integer INCULTPrecision() {
			return 0;
		}

		public String INCULTDefault() {

			return null;

		}

		public String INCULTComment() {

			return "";

		}

		public String INCULTPattern() {

			return "";

		}

		public String INCULTOriginalDbColumnName() {

			return "INCULT";

		}

		public String IDHistorico;

		public String getIDHistorico() {
			return this.IDHistorico;
		}

		public Boolean IDHistoricoIsNullable() {
			return true;
		}

		public Boolean IDHistoricoIsKey() {
			return false;
		}

		public Integer IDHistoricoLength() {
			return 20;
		}

		public Integer IDHistoricoPrecision() {
			return 0;
		}

		public String IDHistoricoDefault() {

			return null;

		}

		public String IDHistoricoComment() {

			return "";

		}

		public String IDHistoricoPattern() {

			return "";

		}

		public String IDHistoricoOriginalDbColumnName() {

			return "IDHistorico";

		}

		public String PrestacaoServico;

		public String getPrestacaoServico() {
			return this.PrestacaoServico;
		}

		public Boolean PrestacaoServicoIsNullable() {
			return true;
		}

		public Boolean PrestacaoServicoIsKey() {
			return false;
		}

		public Integer PrestacaoServicoLength() {
			return 1;
		}

		public Integer PrestacaoServicoPrecision() {
			return 0;
		}

		public String PrestacaoServicoDefault() {

			return null;

		}

		public String PrestacaoServicoComment() {

			return "";

		}

		public String PrestacaoServicoPattern() {

			return "";

		}

		public String PrestacaoServicoOriginalDbColumnName() {

			return "PrestacaoServico";

		}

		public String NUMRA;

		public String getNUMRA() {
			return this.NUMRA;
		}

		public Boolean NUMRAIsNullable() {
			return true;
		}

		public Boolean NUMRAIsKey() {
			return false;
		}

		public Integer NUMRALength() {
			return 15;
		}

		public Integer NUMRAPrecision() {
			return 0;
		}

		public String NUMRADefault() {

			return null;

		}

		public String NUMRAComment() {

			return "";

		}

		public String NUMRAPattern() {

			return "";

		}

		public String NUMRAOriginalDbColumnName() {

			return "NUMRA";

		}

		public String MINIRF;

		public String getMINIRF() {
			return this.MINIRF;
		}

		public Boolean MINIRFIsNullable() {
			return true;
		}

		public Boolean MINIRFIsKey() {
			return false;
		}

		public Integer MINIRFLength() {
			return 1;
		}

		public Integer MINIRFPrecision() {
			return 0;
		}

		public String MINIRFDefault() {

			return null;

		}

		public String MINIRFComment() {

			return "";

		}

		public String MINIRFPattern() {

			return "";

		}

		public String MINIRFOriginalDbColumnName() {

			return "MINIRF";

		}

		public String CODSIAF;

		public String getCODSIAF() {
			return this.CODSIAF;
		}

		public Boolean CODSIAFIsNullable() {
			return true;
		}

		public Boolean CODSIAFIsKey() {
			return false;
		}

		public Integer CODSIAFLength() {
			return 4;
		}

		public Integer CODSIAFPrecision() {
			return 0;
		}

		public String CODSIAFDefault() {

			return null;

		}

		public String CODSIAFComment() {

			return "";

		}

		public String CODSIAFPattern() {

			return "";

		}

		public String CODSIAFOriginalDbColumnName() {

			return "CODSIAF";

		}

		public String ENDNOT;

		public String getENDNOT() {
			return this.ENDNOT;
		}

		public Boolean ENDNOTIsNullable() {
			return true;
		}

		public Boolean ENDNOTIsKey() {
			return false;
		}

		public Integer ENDNOTLength() {
			return 1;
		}

		public Integer ENDNOTPrecision() {
			return 0;
		}

		public String ENDNOTDefault() {

			return null;

		}

		public String ENDNOTComment() {

			return "";

		}

		public String ENDNOTPattern() {

			return "";

		}

		public String ENDNOTOriginalDbColumnName() {

			return "ENDNOT";

		}

		public String FOMEZER;

		public String getFOMEZER() {
			return this.FOMEZER;
		}

		public Boolean FOMEZERIsNullable() {
			return true;
		}

		public Boolean FOMEZERIsKey() {
			return false;
		}

		public Integer FOMEZERLength() {
			return 1;
		}

		public Integer FOMEZERPrecision() {
			return 0;
		}

		public String FOMEZERDefault() {

			return null;

		}

		public String FOMEZERComment() {

			return "";

		}

		public String FOMEZERPattern() {

			return "";

		}

		public String FOMEZEROriginalDbColumnName() {

			return "FOMEZER";

		}

		public String FRETISS;

		public String getFRETISS() {
			return this.FRETISS;
		}

		public Boolean FRETISSIsNullable() {
			return true;
		}

		public Boolean FRETISSIsKey() {
			return false;
		}

		public Integer FRETISSLength() {
			return 1;
		}

		public Integer FRETISSPrecision() {
			return 0;
		}

		public String FRETISSDefault() {

			return null;

		}

		public String FRETISSComment() {

			return "";

		}

		public String FRETISSPattern() {

			return "";

		}

		public String FRETISSOriginalDbColumnName() {

			return "FRETISS";

		}

		public String COMPLEM;

		public String getCOMPLEM() {
			return this.COMPLEM;
		}

		public Boolean COMPLEMIsNullable() {
			return true;
		}

		public Boolean COMPLEMIsKey() {
			return false;
		}

		public Integer COMPLEMLength() {
			return 50;
		}

		public Integer COMPLEMPrecision() {
			return 0;
		}

		public String COMPLEMDefault() {

			return null;

		}

		public String COMPLEMComment() {

			return "";

		}

		public String COMPLEMPattern() {

			return "";

		}

		public String COMPLEMOriginalDbColumnName() {

			return "COMPLEM";

		}

		public String INCLTMG;

		public String getINCLTMG() {
			return this.INCLTMG;
		}

		public Boolean INCLTMGIsNullable() {
			return true;
		}

		public Boolean INCLTMGIsKey() {
			return false;
		}

		public Integer INCLTMGLength() {
			return 1;
		}

		public Integer INCLTMGPrecision() {
			return 0;
		}

		public String INCLTMGDefault() {

			return null;

		}

		public String INCLTMGComment() {

			return "";

		}

		public String INCLTMGPattern() {

			return "";

		}

		public String INCLTMGOriginalDbColumnName() {

			return "INCLTMG";

		}

		public String FILTRF;

		public String getFILTRF() {
			return this.FILTRF;
		}

		public Boolean FILTRFIsNullable() {
			return true;
		}

		public Boolean FILTRFIsKey() {
			return false;
		}

		public Integer FILTRFLength() {
			return 2;
		}

		public Integer FILTRFPrecision() {
			return 0;
		}

		public String FILTRFDefault() {

			return null;

		}

		public String FILTRFComment() {

			return "";

		}

		public String FILTRFPattern() {

			return "";

		}

		public String FILTRFOriginalDbColumnName() {

			return "FILTRF";

		}

		public String TRIBFAV;

		public String getTRIBFAV() {
			return this.TRIBFAV;
		}

		public Boolean TRIBFAVIsNullable() {
			return true;
		}

		public Boolean TRIBFAVIsKey() {
			return false;
		}

		public Integer TRIBFAVLength() {
			return 1;
		}

		public Integer TRIBFAVPrecision() {
			return 0;
		}

		public String TRIBFAVDefault() {

			return null;

		}

		public String TRIBFAVComment() {

			return "";

		}

		public String TRIBFAVPattern() {

			return "";

		}

		public String TRIBFAVOriginalDbColumnName() {

			return "TRIBFAV";

		}

		public String REGPB;

		public String getREGPB() {
			return this.REGPB;
		}

		public Boolean REGPBIsNullable() {
			return true;
		}

		public Boolean REGPBIsKey() {
			return false;
		}

		public Integer REGPBLength() {
			return 1;
		}

		public Integer REGPBPrecision() {
			return 0;
		}

		public String REGPBDefault() {

			return null;

		}

		public String REGPBComment() {

			return "";

		}

		public String REGPBPattern() {

			return "";

		}

		public String REGPBOriginalDbColumnName() {

			return "REGPB";

		}

		public String INOVAUT;

		public String getINOVAUT() {
			return this.INOVAUT;
		}

		public Boolean INOVAUTIsNullable() {
			return true;
		}

		public Boolean INOVAUTIsKey() {
			return false;
		}

		public Integer INOVAUTLength() {
			return 1;
		}

		public Integer INOVAUTPrecision() {
			return 0;
		}

		public String INOVAUTDefault() {

			return null;

		}

		public String INOVAUTComment() {

			return "";

		}

		public String INOVAUTPattern() {

			return "";

		}

		public String INOVAUTOriginalDbColumnName() {

			return "INOVAUT";

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

		public String Ukey;

		public String getUkey() {
			return this.Ukey;
		}

		public Boolean UkeyIsNullable() {
			return true;
		}

		public Boolean UkeyIsKey() {
			return true;
		}

		public Integer UkeyLength() {
			return 10;
		}

		public Integer UkeyPrecision() {
			return 0;
		}

		public String UkeyDefault() {

			return null;

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

		public Integer RecDeletado;

		public Integer getRecDeletado() {
			return this.RecDeletado;
		}

		public Boolean RecDeletadoIsNullable() {
			return true;
		}

		public Boolean RecDeletadoIsKey() {
			return false;
		}

		public Integer RecDeletadoLength() {
			return 10;
		}

		public Integer RecDeletadoPrecision() {
			return 0;
		}

		public String RecDeletadoDefault() {

			return "";

		}

		public String RecDeletadoComment() {

			return "";

		}

		public String RecDeletadoPattern() {

			return "";

		}

		public String RecDeletadoOriginalDbColumnName() {

			return "RecDeletado";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Filial == null) ? 0 : this.Filial.hashCode());

				result = prime * result + ((this.Codigo == null) ? 0 : this.Codigo.hashCode());

				result = prime * result + ((this.Loja == null) ? 0 : this.Loja.hashCode());

				result = prime * result + ((this.Ukey == null) ? 0 : this.Ukey.hashCode());

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
			final Ajuste_dataStruct other = (Ajuste_dataStruct) obj;

			if (this.Filial == null) {
				if (other.Filial != null)
					return false;

			} else if (!this.Filial.equals(other.Filial))

				return false;

			if (this.Codigo == null) {
				if (other.Codigo != null)
					return false;

			} else if (!this.Codigo.equals(other.Codigo))

				return false;

			if (this.Loja == null) {
				if (other.Loja != null)
					return false;

			} else if (!this.Loja.equals(other.Loja))

				return false;

			if (this.Ukey == null) {
				if (other.Ukey != null)
					return false;

			} else if (!this.Ukey.equals(other.Ukey))

				return false;

			return true;
		}

		public void copyDataTo(Ajuste_dataStruct other) {

			other.Filial = this.Filial;
			other.Codigo = this.Codigo;
			other.Loja = this.Loja;
			other.Tipo = this.Tipo;
			other.Pessoa = this.Pessoa;
			other.Natureza = this.Natureza;
			other.CNPJ = this.CNPJ;
			other.Nome = this.Nome;
			other.NomeReduzido = this.NomeReduzido;
			other.Endereco = this.Endereco;
			other.Complemento = this.Complemento;
			other.Bairro = this.Bairro;
			other.CEP = this.CEP;
			other.Estado = this.Estado;
			other.Regiao = this.Regiao;
			other.DescricaoRegiao = this.DescricaoRegiao;
			other.CodigoMunicipio = this.CodigoMunicipio;
			other.Municipio = this.Municipio;
			other.CodigoPais = this.CodigoPais;
			other.NomeEstado = this.NomeEstado;
			other.DDD = this.DDD;
			other.TributacaoFavoravel = this.TributacaoFavoravel;
			other.DDI = this.DDI;
			other.CodIBGE = this.CodIBGE;
			other.UCodigoMunicipio = this.UCodigoMunicipio;
			other.EnderecoRecebimento = this.EnderecoRecebimento;
			other.Telefone = this.Telefone;
			other.FAX = this.FAX;
			other.FoneCP = this.FoneCP;
			other.Contato = this.Contato;
			other.PessoaFisica = this.PessoaFisica;
			other.Pais = this.Pais;
			other.Inscricao = this.Inscricao;
			other.InscricaoMunicipal = this.InscricaoMunicipal;
			other.Vendedor = this.Vendedor;
			other.Comissao = this.Comissao;
			other.Conta = this.Conta;
			other.Banco1 = this.Banco1;
			other.Banco2 = this.Banco2;
			other.Banco3 = this.Banco3;
			other.Banco4 = this.Banco4;
			other.Banco5 = this.Banco5;
			other.Transporte = this.Transporte;
			other.TipoFrete = this.TipoFrete;
			other.EnderecoCobranca = this.EnderecoCobranca;
			other.Condicao = this.Condicao;
			other.BairroCobranca = this.BairroCobranca;
			other.Descricao = this.Descricao;
			other.CEPCobranca = this.CEPCobranca;
			other.EstadoCobranca = this.EstadoCobranca;
			other.UCodigoMunicipioCobranca = this.UCodigoMunicipioCobranca;
			other.MunicipioCobranca = this.MunicipioCobranca;
			other.Prioridade = this.Prioridade;
			other.Risco = this.Risco;
			other.LimiteCredito = this.LimiteCredito;
			other.VencimentoLimiteCredito = this.VencimentoLimiteCredito;
			other.Classe = this.Classe;
			other.LimiteCreditoFinal = this.LimiteCreditoFinal;
			other.MoedaLimiteCredito = this.MoedaLimiteCredito;
			other.MaiorSaldo = this.MaiorSaldo;
			other.MaiorCompra = this.MaiorCompra;
			other.MediaAtraso = this.MediaAtraso;
			other.PrimeiraCompra = this.PrimeiraCompra;
			other.UltimaCompra = this.UltimaCompra;
			other.NumeroCompras = this.NumeroCompras;
			other.FormularioVisita = this.FormularioVisita;
			other.TempoVisita = this.TempoVisita;
			other.UltimaVisita = this.UltimaVisita;
			other.TempoVisita1 = this.TempoVisita1;
			other.TempoPadrao = this.TempoPadrao;
			other.ClassificacaoVenda = this.ClassificacaoVenda;
			other.Mensagem = this.Mensagem;
			other.SaldoTitulo = this.SaldoTitulo;
			other.RecolheISS = this.RecolheISS;
			other.SaldoPedidoLiberado = this.SaldoPedidoLiberado;
			other.NumeroPagamentos = this.NumeroPagamentos;
			other.Transferencia = this.Transferencia;
			other.SUFRAMA = this.SUFRAMA;
			other.ATR = this.ATR;
			other.ValorAcumulado = this.ValorAcumulado;
			other.SaldoPedido = this.SaldoPedido;
			other.TituloProtestado = this.TituloProtestado;
			other.DataUltimoTitulo = this.DataUltimoTitulo;
			other.ChequeDevolvido = this.ChequeDevolvido;
			other.CaixaPostal = this.CaixaPostal;
			other.Matriz = this.Matriz;
			other.DataUltimoCheque = this.DataUltimoCheque;
			other.MaiorDuplicata = this.MaiorDuplicata;
			other.Tabela = this.Tabela;
			other.ISSIncluso = this.ISSIncluso;
			other.SaldoDuplicataMoeda = this.SaldoDuplicataMoeda;
			other.PagamentoAtrasado = this.PagamentoAtrasado;
			other.Atividade = this.Atividade;
			other.Cargo1 = this.Cargo1;
			other.Cargo2 = this.Cargo2;
			other.Cargo3 = this.Cargo3;
			other.RepresentanteTecnico = this.RepresentanteTecnico;
			other.Supervisor = this.Supervisor;
			other.AliquotaIR = this.AliquotaIR;
			other.Observacao = this.Observacao;
			other.RG = this.RG;
			other.CalculaSUFRAMA = this.CalculaSUFRAMA;
			other.DataNascimento = this.DataNascimento;
			other.SaldoPedidoBloqueado = this.SaldoPedidoBloqueado;
			other.GrupoTributario = this.GrupoTributario;
			other.SegmentoAtividade1 = this.SegmentoAtividade1;
			other.ClienteFaturamento = this.ClienteFaturamento;
			other.EnderecoEntrega = this.EnderecoEntrega;
			other.UCompleto = this.UCompleto;
			other.BairroEntrega = this.BairroEntrega;
			other.CEPEntrada = this.CEPEntrada;
			other.EstadoEntrada = this.EstadoEntrada;
			other.CodigoLocal = this.CodigoLocal;
			other.SegmentoAtividade2 = this.SegmentoAtividade2;
			other.TipoPessoa = this.TipoPessoa;
			other.TipoISSRS = this.TipoISSRS;
			other.SegmentoAtividade3 = this.SegmentoAtividade3;
			other.SegmentoAtividade4 = this.SegmentoAtividade4;
			other.SegmentoAtividade5 = this.SegmentoAtividade5;
			other.SegmentoAtividade6 = this.SegmentoAtividade6;
			other.SegmentoAtividade7 = this.SegmentoAtividade7;
			other.CodigoAgente = this.CodigoAgente;
			other.SegmentoAtividade8 = this.SegmentoAtividade8;
			other.CodigoMarcacao = this.CodigoMarcacao;
			other.ComissaoAgente = this.ComissaoAgente;
			other.HomePage = this.HomePage;
			other.CodigoMunicipio1 = this.CodigoMunicipio1;
			other.TipoCliente = this.TipoCliente;
			other.Email = this.Email;
			other.Destino1 = this.Destino1;
			other.Destino2 = this.Destino2;
			other.Destino3 = this.Destino3;
			other.CBO = this.CBO;
			other.Observacao1 = this.Observacao1;
			other.CNAE = this.CNAE;
			other.CodigoHistorico = this.CodigoHistorico;
			other.CondicaoPagamento = this.CondicaoPagamento;
			other.DiasPagamento = this.DiasPagamento;
			other.Agregado = this.Agregado;
			other.RecolheINSS = this.RecolheINSS;
			other.RecolheCOFINS = this.RecolheCOFINS;
			other.RecolheCSLL = this.RecolheCSLL;
			other.RecolhePIS = this.RecolhePIS;
			other.TipoPeriodo = this.TipoPeriodo;
			other.SaldoFinal = this.SaldoFinal;
			other.SaldoFinalMoeda = this.SaldoFinalMoeda;
			other.Contabilidade = this.Contabilidade;
			other.ClienteConvenio = this.ClienteConvenio;
			other.B2B = this.B2B;
			other.CBO1 = this.CBO1;
			other.CNAE1 = this.CNAE1;
			other.SegmentoAtividade11 = this.SegmentoAtividade11;
			other.MensagemBloqueio = this.MensagemBloqueio;
			other.SubCodigo = this.SubCodigo;
			other.FilialDebito = this.FilialDebito;
			other.RecolhePIS1 = this.RecolhePIS1;
			other.RecolheCOFINS1 = this.RecolheCOFINS1;
			other.RecolheCSLL1 = this.RecolheCSLL1;
			other.ABICS = this.ABICS;
			other.Vinculo = this.Vinculo;
			other.DataInicioVinculo = this.DataInicioVinculo;
			other.DataFimVinculo = this.DataFimVinculo;
			other.ISSRSLC = this.ISSRSLC;
			other.TipoRegistro = this.TipoRegistro;
			other.RFASEMT = this.RFASEMT;
			other.RIMAMT = this.RIMAMT;
			other.REGESIM = this.REGESIM;
			other.ContaReceita = this.ContaReceita;
			other.SIMPNacional = this.SIMPNacional;
			other.RecolheFETHAB = this.RecolheFETHAB;
			other.RFABOV = this.RFABOV;
			other.RFACS = this.RFACS;
			other.DataNascimento1 = this.DataNascimento1;
			other.Contribuinte = this.Contribuinte;
			other.RecolheFMD = this.RecolheFMD;
			other.TipoJuridico = this.TipoJuridico;
			other.INCULT = this.INCULT;
			other.IDHistorico = this.IDHistorico;
			other.PrestacaoServico = this.PrestacaoServico;
			other.NUMRA = this.NUMRA;
			other.MINIRF = this.MINIRF;
			other.CODSIAF = this.CODSIAF;
			other.ENDNOT = this.ENDNOT;
			other.FOMEZER = this.FOMEZER;
			other.FRETISS = this.FRETISS;
			other.COMPLEM = this.COMPLEM;
			other.INCLTMG = this.INCLTMG;
			other.FILTRF = this.FILTRF;
			other.TRIBFAV = this.TRIBFAV;
			other.REGPB = this.REGPB;
			other.INOVAUT = this.INOVAUT;
			other.Deletado = this.Deletado;
			other.Ukey = this.Ukey;
			other.RecDeletado = this.RecDeletado;

		}

		public void copyKeysDataTo(Ajuste_dataStruct other) {

			other.Filial = this.Filial;
			other.Codigo = this.Codigo;
			other.Loja = this.Loja;
			other.Ukey = this.Ukey;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readDate(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readDate(dis);

					this.UltimaCompra = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readDate(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readDate(dis);

					this.UltimaCompra = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readDate(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

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

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// java.util.Date

				writeDate(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// java.util.Date

				writeDate(this.PrimeiraCompra, dos);

				// java.util.Date

				writeDate(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// java.util.Date

				writeDate(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// java.util.Date

				writeDate(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// java.util.Date

				writeDate(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// java.util.Date

				writeDate(this.PrimeiraCompra, dos);

				// java.util.Date

				writeDate(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// java.util.Date

				writeDate(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// java.util.Date

				writeDate(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Filial=" + Filial);
			sb.append(",Codigo=" + Codigo);
			sb.append(",Loja=" + Loja);
			sb.append(",Tipo=" + Tipo);
			sb.append(",Pessoa=" + Pessoa);
			sb.append(",Natureza=" + Natureza);
			sb.append(",CNPJ=" + CNPJ);
			sb.append(",Nome=" + Nome);
			sb.append(",NomeReduzido=" + NomeReduzido);
			sb.append(",Endereco=" + Endereco);
			sb.append(",Complemento=" + Complemento);
			sb.append(",Bairro=" + Bairro);
			sb.append(",CEP=" + CEP);
			sb.append(",Estado=" + Estado);
			sb.append(",Regiao=" + Regiao);
			sb.append(",DescricaoRegiao=" + DescricaoRegiao);
			sb.append(",CodigoMunicipio=" + CodigoMunicipio);
			sb.append(",Municipio=" + Municipio);
			sb.append(",CodigoPais=" + CodigoPais);
			sb.append(",NomeEstado=" + NomeEstado);
			sb.append(",DDD=" + DDD);
			sb.append(",TributacaoFavoravel=" + TributacaoFavoravel);
			sb.append(",DDI=" + DDI);
			sb.append(",CodIBGE=" + CodIBGE);
			sb.append(",UCodigoMunicipio=" + UCodigoMunicipio);
			sb.append(",EnderecoRecebimento=" + EnderecoRecebimento);
			sb.append(",Telefone=" + Telefone);
			sb.append(",FAX=" + FAX);
			sb.append(",FoneCP=" + FoneCP);
			sb.append(",Contato=" + Contato);
			sb.append(",PessoaFisica=" + PessoaFisica);
			sb.append(",Pais=" + Pais);
			sb.append(",Inscricao=" + Inscricao);
			sb.append(",InscricaoMunicipal=" + InscricaoMunicipal);
			sb.append(",Vendedor=" + Vendedor);
			sb.append(",Comissao=" + String.valueOf(Comissao));
			sb.append(",Conta=" + Conta);
			sb.append(",Banco1=" + Banco1);
			sb.append(",Banco2=" + Banco2);
			sb.append(",Banco3=" + Banco3);
			sb.append(",Banco4=" + Banco4);
			sb.append(",Banco5=" + Banco5);
			sb.append(",Transporte=" + Transporte);
			sb.append(",TipoFrete=" + TipoFrete);
			sb.append(",EnderecoCobranca=" + EnderecoCobranca);
			sb.append(",Condicao=" + Condicao);
			sb.append(",BairroCobranca=" + BairroCobranca);
			sb.append(",Descricao=" + String.valueOf(Descricao));
			sb.append(",CEPCobranca=" + CEPCobranca);
			sb.append(",EstadoCobranca=" + EstadoCobranca);
			sb.append(",UCodigoMunicipioCobranca=" + UCodigoMunicipioCobranca);
			sb.append(",MunicipioCobranca=" + MunicipioCobranca);
			sb.append(",Prioridade=" + Prioridade);
			sb.append(",Risco=" + Risco);
			sb.append(",LimiteCredito=" + String.valueOf(LimiteCredito));
			sb.append(",VencimentoLimiteCredito=" + String.valueOf(VencimentoLimiteCredito));
			sb.append(",Classe=" + Classe);
			sb.append(",LimiteCreditoFinal=" + String.valueOf(LimiteCreditoFinal));
			sb.append(",MoedaLimiteCredito=" + String.valueOf(MoedaLimiteCredito));
			sb.append(",MaiorSaldo=" + String.valueOf(MaiorSaldo));
			sb.append(",MaiorCompra=" + String.valueOf(MaiorCompra));
			sb.append(",MediaAtraso=" + String.valueOf(MediaAtraso));
			sb.append(",PrimeiraCompra=" + String.valueOf(PrimeiraCompra));
			sb.append(",UltimaCompra=" + String.valueOf(UltimaCompra));
			sb.append(",NumeroCompras=" + String.valueOf(NumeroCompras));
			sb.append(",FormularioVisita=" + FormularioVisita);
			sb.append(",TempoVisita=" + String.valueOf(TempoVisita));
			sb.append(",UltimaVisita=" + UltimaVisita);
			sb.append(",TempoVisita1=" + TempoVisita1);
			sb.append(",TempoPadrao=" + TempoPadrao);
			sb.append(",ClassificacaoVenda=" + ClassificacaoVenda);
			sb.append(",Mensagem=" + Mensagem);
			sb.append(",SaldoTitulo=" + String.valueOf(SaldoTitulo));
			sb.append(",RecolheISS=" + RecolheISS);
			sb.append(",SaldoPedidoLiberado=" + String.valueOf(SaldoPedidoLiberado));
			sb.append(",NumeroPagamentos=" + String.valueOf(NumeroPagamentos));
			sb.append(",Transferencia=" + Transferencia);
			sb.append(",SUFRAMA=" + SUFRAMA);
			sb.append(",ATR=" + String.valueOf(ATR));
			sb.append(",ValorAcumulado=" + String.valueOf(ValorAcumulado));
			sb.append(",SaldoPedido=" + String.valueOf(SaldoPedido));
			sb.append(",TituloProtestado=" + String.valueOf(TituloProtestado));
			sb.append(",DataUltimoTitulo=" + String.valueOf(DataUltimoTitulo));
			sb.append(",ChequeDevolvido=" + String.valueOf(ChequeDevolvido));
			sb.append(",CaixaPostal=" + CaixaPostal);
			sb.append(",Matriz=" + String.valueOf(Matriz));
			sb.append(",DataUltimoCheque=" + DataUltimoCheque);
			sb.append(",MaiorDuplicata=" + String.valueOf(MaiorDuplicata));
			sb.append(",Tabela=" + Tabela);
			sb.append(",ISSIncluso=" + ISSIncluso);
			sb.append(",SaldoDuplicataMoeda=" + String.valueOf(SaldoDuplicataMoeda));
			sb.append(",PagamentoAtrasado=" + String.valueOf(PagamentoAtrasado));
			sb.append(",Atividade=" + Atividade);
			sb.append(",Cargo1=" + Cargo1);
			sb.append(",Cargo2=" + Cargo2);
			sb.append(",Cargo3=" + Cargo3);
			sb.append(",RepresentanteTecnico=" + RepresentanteTecnico);
			sb.append(",Supervisor=" + Supervisor);
			sb.append(",AliquotaIR=" + String.valueOf(AliquotaIR));
			sb.append(",Observacao=" + Observacao);
			sb.append(",RG=" + RG);
			sb.append(",CalculaSUFRAMA=" + CalculaSUFRAMA);
			sb.append(",DataNascimento=" + String.valueOf(DataNascimento));
			sb.append(",SaldoPedidoBloqueado=" + String.valueOf(SaldoPedidoBloqueado));
			sb.append(",GrupoTributario=" + GrupoTributario);
			sb.append(",SegmentoAtividade1=" + SegmentoAtividade1);
			sb.append(",ClienteFaturamento=" + ClienteFaturamento);
			sb.append(",EnderecoEntrega=" + EnderecoEntrega);
			sb.append(",UCompleto=" + UCompleto);
			sb.append(",BairroEntrega=" + BairroEntrega);
			sb.append(",CEPEntrada=" + CEPEntrada);
			sb.append(",EstadoEntrada=" + EstadoEntrada);
			sb.append(",CodigoLocal=" + CodigoLocal);
			sb.append(",SegmentoAtividade2=" + SegmentoAtividade2);
			sb.append(",TipoPessoa=" + TipoPessoa);
			sb.append(",TipoISSRS=" + TipoISSRS);
			sb.append(",SegmentoAtividade3=" + SegmentoAtividade3);
			sb.append(",SegmentoAtividade4=" + SegmentoAtividade4);
			sb.append(",SegmentoAtividade5=" + SegmentoAtividade5);
			sb.append(",SegmentoAtividade6=" + SegmentoAtividade6);
			sb.append(",SegmentoAtividade7=" + SegmentoAtividade7);
			sb.append(",CodigoAgente=" + CodigoAgente);
			sb.append(",SegmentoAtividade8=" + SegmentoAtividade8);
			sb.append(",CodigoMarcacao=" + CodigoMarcacao);
			sb.append(",ComissaoAgente=" + String.valueOf(ComissaoAgente));
			sb.append(",HomePage=" + HomePage);
			sb.append(",CodigoMunicipio1=" + CodigoMunicipio1);
			sb.append(",TipoCliente=" + TipoCliente);
			sb.append(",Email=" + Email);
			sb.append(",Destino1=" + Destino1);
			sb.append(",Destino2=" + Destino2);
			sb.append(",Destino3=" + Destino3);
			sb.append(",CBO=" + CBO);
			sb.append(",Observacao1=" + Observacao1);
			sb.append(",CNAE=" + CNAE);
			sb.append(",CodigoHistorico=" + CodigoHistorico);
			sb.append(",CondicaoPagamento=" + CondicaoPagamento);
			sb.append(",DiasPagamento=" + String.valueOf(DiasPagamento));
			sb.append(",Agregado=" + Agregado);
			sb.append(",RecolheINSS=" + RecolheINSS);
			sb.append(",RecolheCOFINS=" + RecolheCOFINS);
			sb.append(",RecolheCSLL=" + RecolheCSLL);
			sb.append(",RecolhePIS=" + RecolhePIS);
			sb.append(",TipoPeriodo=" + TipoPeriodo);
			sb.append(",SaldoFinal=" + String.valueOf(SaldoFinal));
			sb.append(",SaldoFinalMoeda=" + String.valueOf(SaldoFinalMoeda));
			sb.append(",Contabilidade=" + Contabilidade);
			sb.append(",ClienteConvenio=" + ClienteConvenio);
			sb.append(",B2B=" + B2B);
			sb.append(",CBO1=" + CBO1);
			sb.append(",CNAE1=" + CNAE1);
			sb.append(",SegmentoAtividade11=" + SegmentoAtividade11);
			sb.append(",MensagemBloqueio=" + MensagemBloqueio);
			sb.append(",SubCodigo=" + SubCodigo);
			sb.append(",FilialDebito=" + FilialDebito);
			sb.append(",RecolhePIS1=" + RecolhePIS1);
			sb.append(",RecolheCOFINS1=" + RecolheCOFINS1);
			sb.append(",RecolheCSLL1=" + RecolheCSLL1);
			sb.append(",ABICS=" + ABICS);
			sb.append(",Vinculo=" + Vinculo);
			sb.append(",DataInicioVinculo=" + DataInicioVinculo);
			sb.append(",DataFimVinculo=" + DataFimVinculo);
			sb.append(",ISSRSLC=" + ISSRSLC);
			sb.append(",TipoRegistro=" + TipoRegistro);
			sb.append(",RFASEMT=" + RFASEMT);
			sb.append(",RIMAMT=" + RIMAMT);
			sb.append(",REGESIM=" + REGESIM);
			sb.append(",ContaReceita=" + ContaReceita);
			sb.append(",SIMPNacional=" + SIMPNacional);
			sb.append(",RecolheFETHAB=" + RecolheFETHAB);
			sb.append(",RFABOV=" + RFABOV);
			sb.append(",RFACS=" + RFACS);
			sb.append(",DataNascimento1=" + DataNascimento1);
			sb.append(",Contribuinte=" + Contribuinte);
			sb.append(",RecolheFMD=" + RecolheFMD);
			sb.append(",TipoJuridico=" + TipoJuridico);
			sb.append(",INCULT=" + INCULT);
			sb.append(",IDHistorico=" + IDHistorico);
			sb.append(",PrestacaoServico=" + PrestacaoServico);
			sb.append(",NUMRA=" + NUMRA);
			sb.append(",MINIRF=" + MINIRF);
			sb.append(",CODSIAF=" + CODSIAF);
			sb.append(",ENDNOT=" + ENDNOT);
			sb.append(",FOMEZER=" + FOMEZER);
			sb.append(",FRETISS=" + FRETISS);
			sb.append(",COMPLEM=" + COMPLEM);
			sb.append(",INCLTMG=" + INCLTMG);
			sb.append(",FILTRF=" + FILTRF);
			sb.append(",TRIBFAV=" + TRIBFAV);
			sb.append(",REGPB=" + REGPB);
			sb.append(",INOVAUT=" + INOVAUT);
			sb.append(",Deletado=" + Deletado);
			sb.append(",Ukey=" + Ukey);
			sb.append(",RecDeletado=" + String.valueOf(RecDeletado));
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

			if (Codigo == null) {
				sb.append("<null>");
			} else {
				sb.append(Codigo);
			}

			sb.append("|");

			if (Loja == null) {
				sb.append("<null>");
			} else {
				sb.append(Loja);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (Pessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(Pessoa);
			}

			sb.append("|");

			if (Natureza == null) {
				sb.append("<null>");
			} else {
				sb.append(Natureza);
			}

			sb.append("|");

			if (CNPJ == null) {
				sb.append("<null>");
			} else {
				sb.append(CNPJ);
			}

			sb.append("|");

			if (Nome == null) {
				sb.append("<null>");
			} else {
				sb.append(Nome);
			}

			sb.append("|");

			if (NomeReduzido == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeReduzido);
			}

			sb.append("|");

			if (Endereco == null) {
				sb.append("<null>");
			} else {
				sb.append(Endereco);
			}

			sb.append("|");

			if (Complemento == null) {
				sb.append("<null>");
			} else {
				sb.append(Complemento);
			}

			sb.append("|");

			if (Bairro == null) {
				sb.append("<null>");
			} else {
				sb.append(Bairro);
			}

			sb.append("|");

			if (CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(CEP);
			}

			sb.append("|");

			if (Estado == null) {
				sb.append("<null>");
			} else {
				sb.append(Estado);
			}

			sb.append("|");

			if (Regiao == null) {
				sb.append("<null>");
			} else {
				sb.append(Regiao);
			}

			sb.append("|");

			if (DescricaoRegiao == null) {
				sb.append("<null>");
			} else {
				sb.append(DescricaoRegiao);
			}

			sb.append("|");

			if (CodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio);
			}

			sb.append("|");

			if (Municipio == null) {
				sb.append("<null>");
			} else {
				sb.append(Municipio);
			}

			sb.append("|");

			if (CodigoPais == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoPais);
			}

			sb.append("|");

			if (NomeEstado == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeEstado);
			}

			sb.append("|");

			if (DDD == null) {
				sb.append("<null>");
			} else {
				sb.append(DDD);
			}

			sb.append("|");

			if (TributacaoFavoravel == null) {
				sb.append("<null>");
			} else {
				sb.append(TributacaoFavoravel);
			}

			sb.append("|");

			if (DDI == null) {
				sb.append("<null>");
			} else {
				sb.append(DDI);
			}

			sb.append("|");

			if (CodIBGE == null) {
				sb.append("<null>");
			} else {
				sb.append(CodIBGE);
			}

			sb.append("|");

			if (UCodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipio);
			}

			sb.append("|");

			if (EnderecoRecebimento == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoRecebimento);
			}

			sb.append("|");

			if (Telefone == null) {
				sb.append("<null>");
			} else {
				sb.append(Telefone);
			}

			sb.append("|");

			if (FAX == null) {
				sb.append("<null>");
			} else {
				sb.append(FAX);
			}

			sb.append("|");

			if (FoneCP == null) {
				sb.append("<null>");
			} else {
				sb.append(FoneCP);
			}

			sb.append("|");

			if (Contato == null) {
				sb.append("<null>");
			} else {
				sb.append(Contato);
			}

			sb.append("|");

			if (PessoaFisica == null) {
				sb.append("<null>");
			} else {
				sb.append(PessoaFisica);
			}

			sb.append("|");

			if (Pais == null) {
				sb.append("<null>");
			} else {
				sb.append(Pais);
			}

			sb.append("|");

			if (Inscricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Inscricao);
			}

			sb.append("|");

			if (InscricaoMunicipal == null) {
				sb.append("<null>");
			} else {
				sb.append(InscricaoMunicipal);
			}

			sb.append("|");

			if (Vendedor == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendedor);
			}

			sb.append("|");

			if (Comissao == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao);
			}

			sb.append("|");

			if (Conta == null) {
				sb.append("<null>");
			} else {
				sb.append(Conta);
			}

			sb.append("|");

			if (Banco1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco1);
			}

			sb.append("|");

			if (Banco2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco2);
			}

			sb.append("|");

			if (Banco3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco3);
			}

			sb.append("|");

			if (Banco4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco4);
			}

			sb.append("|");

			if (Banco5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco5);
			}

			sb.append("|");

			if (Transporte == null) {
				sb.append("<null>");
			} else {
				sb.append(Transporte);
			}

			sb.append("|");

			if (TipoFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoFrete);
			}

			sb.append("|");

			if (EnderecoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoCobranca);
			}

			sb.append("|");

			if (Condicao == null) {
				sb.append("<null>");
			} else {
				sb.append(Condicao);
			}

			sb.append("|");

			if (BairroCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroCobranca);
			}

			sb.append("|");

			if (Descricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Descricao);
			}

			sb.append("|");

			if (CEPCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPCobranca);
			}

			sb.append("|");

			if (EstadoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoCobranca);
			}

			sb.append("|");

			if (UCodigoMunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipioCobranca);
			}

			sb.append("|");

			if (MunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(MunicipioCobranca);
			}

			sb.append("|");

			if (Prioridade == null) {
				sb.append("<null>");
			} else {
				sb.append(Prioridade);
			}

			sb.append("|");

			if (Risco == null) {
				sb.append("<null>");
			} else {
				sb.append(Risco);
			}

			sb.append("|");

			if (LimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCredito);
			}

			sb.append("|");

			if (VencimentoLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(VencimentoLimiteCredito);
			}

			sb.append("|");

			if (Classe == null) {
				sb.append("<null>");
			} else {
				sb.append(Classe);
			}

			sb.append("|");

			if (LimiteCreditoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCreditoFinal);
			}

			sb.append("|");

			if (MoedaLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(MoedaLimiteCredito);
			}

			sb.append("|");

			if (MaiorSaldo == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorSaldo);
			}

			sb.append("|");

			if (MaiorCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorCompra);
			}

			sb.append("|");

			if (MediaAtraso == null) {
				sb.append("<null>");
			} else {
				sb.append(MediaAtraso);
			}

			sb.append("|");

			if (PrimeiraCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(PrimeiraCompra);
			}

			sb.append("|");

			if (UltimaCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaCompra);
			}

			sb.append("|");

			if (NumeroCompras == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroCompras);
			}

			sb.append("|");

			if (FormularioVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(FormularioVisita);
			}

			sb.append("|");

			if (TempoVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita);
			}

			sb.append("|");

			if (UltimaVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaVisita);
			}

			sb.append("|");

			if (TempoVisita1 == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita1);
			}

			sb.append("|");

			if (TempoPadrao == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoPadrao);
			}

			sb.append("|");

			if (ClassificacaoVenda == null) {
				sb.append("<null>");
			} else {
				sb.append(ClassificacaoVenda);
			}

			sb.append("|");

			if (Mensagem == null) {
				sb.append("<null>");
			} else {
				sb.append(Mensagem);
			}

			sb.append("|");

			if (SaldoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoTitulo);
			}

			sb.append("|");

			if (RecolheISS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheISS);
			}

			sb.append("|");

			if (SaldoPedidoLiberado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoLiberado);
			}

			sb.append("|");

			if (NumeroPagamentos == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPagamentos);
			}

			sb.append("|");

			if (Transferencia == null) {
				sb.append("<null>");
			} else {
				sb.append(Transferencia);
			}

			sb.append("|");

			if (SUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(SUFRAMA);
			}

			sb.append("|");

			if (ATR == null) {
				sb.append("<null>");
			} else {
				sb.append(ATR);
			}

			sb.append("|");

			if (ValorAcumulado == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorAcumulado);
			}

			sb.append("|");

			if (SaldoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedido);
			}

			sb.append("|");

			if (TituloProtestado == null) {
				sb.append("<null>");
			} else {
				sb.append(TituloProtestado);
			}

			sb.append("|");

			if (DataUltimoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoTitulo);
			}

			sb.append("|");

			if (ChequeDevolvido == null) {
				sb.append("<null>");
			} else {
				sb.append(ChequeDevolvido);
			}

			sb.append("|");

			if (CaixaPostal == null) {
				sb.append("<null>");
			} else {
				sb.append(CaixaPostal);
			}

			sb.append("|");

			if (Matriz == null) {
				sb.append("<null>");
			} else {
				sb.append(Matriz);
			}

			sb.append("|");

			if (DataUltimoCheque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoCheque);
			}

			sb.append("|");

			if (MaiorDuplicata == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorDuplicata);
			}

			sb.append("|");

			if (Tabela == null) {
				sb.append("<null>");
			} else {
				sb.append(Tabela);
			}

			sb.append("|");

			if (ISSIncluso == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSIncluso);
			}

			sb.append("|");

			if (SaldoDuplicataMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoDuplicataMoeda);
			}

			sb.append("|");

			if (PagamentoAtrasado == null) {
				sb.append("<null>");
			} else {
				sb.append(PagamentoAtrasado);
			}

			sb.append("|");

			if (Atividade == null) {
				sb.append("<null>");
			} else {
				sb.append(Atividade);
			}

			sb.append("|");

			if (Cargo1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo1);
			}

			sb.append("|");

			if (Cargo2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo2);
			}

			sb.append("|");

			if (Cargo3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo3);
			}

			sb.append("|");

			if (RepresentanteTecnico == null) {
				sb.append("<null>");
			} else {
				sb.append(RepresentanteTecnico);
			}

			sb.append("|");

			if (Supervisor == null) {
				sb.append("<null>");
			} else {
				sb.append(Supervisor);
			}

			sb.append("|");

			if (AliquotaIR == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaIR);
			}

			sb.append("|");

			if (Observacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao);
			}

			sb.append("|");

			if (RG == null) {
				sb.append("<null>");
			} else {
				sb.append(RG);
			}

			sb.append("|");

			if (CalculaSUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(CalculaSUFRAMA);
			}

			sb.append("|");

			if (DataNascimento == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento);
			}

			sb.append("|");

			if (SaldoPedidoBloqueado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoBloqueado);
			}

			sb.append("|");

			if (GrupoTributario == null) {
				sb.append("<null>");
			} else {
				sb.append(GrupoTributario);
			}

			sb.append("|");

			if (SegmentoAtividade1 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade1);
			}

			sb.append("|");

			if (ClienteFaturamento == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteFaturamento);
			}

			sb.append("|");

			if (EnderecoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoEntrega);
			}

			sb.append("|");

			if (UCompleto == null) {
				sb.append("<null>");
			} else {
				sb.append(UCompleto);
			}

			sb.append("|");

			if (BairroEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroEntrega);
			}

			sb.append("|");

			if (CEPEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPEntrada);
			}

			sb.append("|");

			if (EstadoEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoEntrada);
			}

			sb.append("|");

			if (CodigoLocal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoLocal);
			}

			sb.append("|");

			if (SegmentoAtividade2 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade2);
			}

			sb.append("|");

			if (TipoPessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPessoa);
			}

			sb.append("|");

			if (TipoISSRS == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoISSRS);
			}

			sb.append("|");

			if (SegmentoAtividade3 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade3);
			}

			sb.append("|");

			if (SegmentoAtividade4 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade4);
			}

			sb.append("|");

			if (SegmentoAtividade5 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade5);
			}

			sb.append("|");

			if (SegmentoAtividade6 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade6);
			}

			sb.append("|");

			if (SegmentoAtividade7 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade7);
			}

			sb.append("|");

			if (CodigoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoAgente);
			}

			sb.append("|");

			if (SegmentoAtividade8 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade8);
			}

			sb.append("|");

			if (CodigoMarcacao == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMarcacao);
			}

			sb.append("|");

			if (ComissaoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(ComissaoAgente);
			}

			sb.append("|");

			if (HomePage == null) {
				sb.append("<null>");
			} else {
				sb.append(HomePage);
			}

			sb.append("|");

			if (CodigoMunicipio1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio1);
			}

			sb.append("|");

			if (TipoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoCliente);
			}

			sb.append("|");

			if (Email == null) {
				sb.append("<null>");
			} else {
				sb.append(Email);
			}

			sb.append("|");

			if (Destino1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino1);
			}

			sb.append("|");

			if (Destino2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino2);
			}

			sb.append("|");

			if (Destino3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino3);
			}

			sb.append("|");

			if (CBO == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO);
			}

			sb.append("|");

			if (Observacao1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao1);
			}

			sb.append("|");

			if (CNAE == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE);
			}

			sb.append("|");

			if (CodigoHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoHistorico);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (DiasPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(DiasPagamento);
			}

			sb.append("|");

			if (Agregado == null) {
				sb.append("<null>");
			} else {
				sb.append(Agregado);
			}

			sb.append("|");

			if (RecolheINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheINSS);
			}

			sb.append("|");

			if (RecolheCOFINS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS);
			}

			sb.append("|");

			if (RecolheCSLL == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL);
			}

			sb.append("|");

			if (RecolhePIS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS);
			}

			sb.append("|");

			if (TipoPeriodo == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPeriodo);
			}

			sb.append("|");

			if (SaldoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinal);
			}

			sb.append("|");

			if (SaldoFinalMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinalMoeda);
			}

			sb.append("|");

			if (Contabilidade == null) {
				sb.append("<null>");
			} else {
				sb.append(Contabilidade);
			}

			sb.append("|");

			if (ClienteConvenio == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteConvenio);
			}

			sb.append("|");

			if (B2B == null) {
				sb.append("<null>");
			} else {
				sb.append(B2B);
			}

			sb.append("|");

			if (CBO1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO1);
			}

			sb.append("|");

			if (CNAE1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE1);
			}

			sb.append("|");

			if (SegmentoAtividade11 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade11);
			}

			sb.append("|");

			if (MensagemBloqueio == null) {
				sb.append("<null>");
			} else {
				sb.append(MensagemBloqueio);
			}

			sb.append("|");

			if (SubCodigo == null) {
				sb.append("<null>");
			} else {
				sb.append(SubCodigo);
			}

			sb.append("|");

			if (FilialDebito == null) {
				sb.append("<null>");
			} else {
				sb.append(FilialDebito);
			}

			sb.append("|");

			if (RecolhePIS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS1);
			}

			sb.append("|");

			if (RecolheCOFINS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS1);
			}

			sb.append("|");

			if (RecolheCSLL1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL1);
			}

			sb.append("|");

			if (ABICS == null) {
				sb.append("<null>");
			} else {
				sb.append(ABICS);
			}

			sb.append("|");

			if (Vinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(Vinculo);
			}

			sb.append("|");

			if (DataInicioVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataInicioVinculo);
			}

			sb.append("|");

			if (DataFimVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataFimVinculo);
			}

			sb.append("|");

			if (ISSRSLC == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSRSLC);
			}

			sb.append("|");

			if (TipoRegistro == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoRegistro);
			}

			sb.append("|");

			if (RFASEMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RFASEMT);
			}

			sb.append("|");

			if (RIMAMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RIMAMT);
			}

			sb.append("|");

			if (REGESIM == null) {
				sb.append("<null>");
			} else {
				sb.append(REGESIM);
			}

			sb.append("|");

			if (ContaReceita == null) {
				sb.append("<null>");
			} else {
				sb.append(ContaReceita);
			}

			sb.append("|");

			if (SIMPNacional == null) {
				sb.append("<null>");
			} else {
				sb.append(SIMPNacional);
			}

			sb.append("|");

			if (RecolheFETHAB == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFETHAB);
			}

			sb.append("|");

			if (RFABOV == null) {
				sb.append("<null>");
			} else {
				sb.append(RFABOV);
			}

			sb.append("|");

			if (RFACS == null) {
				sb.append("<null>");
			} else {
				sb.append(RFACS);
			}

			sb.append("|");

			if (DataNascimento1 == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento1);
			}

			sb.append("|");

			if (Contribuinte == null) {
				sb.append("<null>");
			} else {
				sb.append(Contribuinte);
			}

			sb.append("|");

			if (RecolheFMD == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFMD);
			}

			sb.append("|");

			if (TipoJuridico == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoJuridico);
			}

			sb.append("|");

			if (INCULT == null) {
				sb.append("<null>");
			} else {
				sb.append(INCULT);
			}

			sb.append("|");

			if (IDHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(IDHistorico);
			}

			sb.append("|");

			if (PrestacaoServico == null) {
				sb.append("<null>");
			} else {
				sb.append(PrestacaoServico);
			}

			sb.append("|");

			if (NUMRA == null) {
				sb.append("<null>");
			} else {
				sb.append(NUMRA);
			}

			sb.append("|");

			if (MINIRF == null) {
				sb.append("<null>");
			} else {
				sb.append(MINIRF);
			}

			sb.append("|");

			if (CODSIAF == null) {
				sb.append("<null>");
			} else {
				sb.append(CODSIAF);
			}

			sb.append("|");

			if (ENDNOT == null) {
				sb.append("<null>");
			} else {
				sb.append(ENDNOT);
			}

			sb.append("|");

			if (FOMEZER == null) {
				sb.append("<null>");
			} else {
				sb.append(FOMEZER);
			}

			sb.append("|");

			if (FRETISS == null) {
				sb.append("<null>");
			} else {
				sb.append(FRETISS);
			}

			sb.append("|");

			if (COMPLEM == null) {
				sb.append("<null>");
			} else {
				sb.append(COMPLEM);
			}

			sb.append("|");

			if (INCLTMG == null) {
				sb.append("<null>");
			} else {
				sb.append(INCLTMG);
			}

			sb.append("|");

			if (FILTRF == null) {
				sb.append("<null>");
			} else {
				sb.append(FILTRF);
			}

			sb.append("|");

			if (TRIBFAV == null) {
				sb.append("<null>");
			} else {
				sb.append(TRIBFAV);
			}

			sb.append("|");

			if (REGPB == null) {
				sb.append("<null>");
			} else {
				sb.append(REGPB);
			}

			sb.append("|");

			if (INOVAUT == null) {
				sb.append("<null>");
			} else {
				sb.append(INOVAUT);
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

			if (RecDeletado == null) {
				sb.append("<null>");
			} else {
				sb.append(RecDeletado);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Ajuste_dataStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Filial, other.Filial);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Codigo, other.Codigo);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Loja, other.Loja);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Ukey, other.Ukey);
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
		final static byte[] commonByteArrayLock_HYDRONORTH_Clientes = new byte[0];
		static byte[] commonByteArray_HYDRONORTH_Clientes = new byte[0];

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

		public String Codigo;

		public String getCodigo() {
			return this.Codigo;
		}

		public Boolean CodigoIsNullable() {
			return true;
		}

		public Boolean CodigoIsKey() {
			return true;
		}

		public Integer CodigoLength() {
			return 9;
		}

		public Integer CodigoPrecision() {
			return 0;
		}

		public String CodigoDefault() {

			return null;

		}

		public String CodigoComment() {

			return "";

		}

		public String CodigoPattern() {

			return "";

		}

		public String CodigoOriginalDbColumnName() {

			return "Codigo";

		}

		public String Loja;

		public String getLoja() {
			return this.Loja;
		}

		public Boolean LojaIsNullable() {
			return true;
		}

		public Boolean LojaIsKey() {
			return true;
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

		public String Pessoa;

		public String getPessoa() {
			return this.Pessoa;
		}

		public Boolean PessoaIsNullable() {
			return true;
		}

		public Boolean PessoaIsKey() {
			return false;
		}

		public Integer PessoaLength() {
			return 1;
		}

		public Integer PessoaPrecision() {
			return 0;
		}

		public String PessoaDefault() {

			return null;

		}

		public String PessoaComment() {

			return "";

		}

		public String PessoaPattern() {

			return "";

		}

		public String PessoaOriginalDbColumnName() {

			return "Pessoa";

		}

		public String Natureza;

		public String getNatureza() {
			return this.Natureza;
		}

		public Boolean NaturezaIsNullable() {
			return true;
		}

		public Boolean NaturezaIsKey() {
			return false;
		}

		public Integer NaturezaLength() {
			return 10;
		}

		public Integer NaturezaPrecision() {
			return 0;
		}

		public String NaturezaDefault() {

			return null;

		}

		public String NaturezaComment() {

			return "";

		}

		public String NaturezaPattern() {

			return "";

		}

		public String NaturezaOriginalDbColumnName() {

			return "Natureza";

		}

		public String CNPJ;

		public String getCNPJ() {
			return this.CNPJ;
		}

		public Boolean CNPJIsNullable() {
			return true;
		}

		public Boolean CNPJIsKey() {
			return false;
		}

		public Integer CNPJLength() {
			return 14;
		}

		public Integer CNPJPrecision() {
			return 0;
		}

		public String CNPJDefault() {

			return null;

		}

		public String CNPJComment() {

			return "";

		}

		public String CNPJPattern() {

			return "";

		}

		public String CNPJOriginalDbColumnName() {

			return "CNPJ";

		}

		public String Nome;

		public String getNome() {
			return this.Nome;
		}

		public Boolean NomeIsNullable() {
			return true;
		}

		public Boolean NomeIsKey() {
			return false;
		}

		public Integer NomeLength() {
			return 40;
		}

		public Integer NomePrecision() {
			return 0;
		}

		public String NomeDefault() {

			return null;

		}

		public String NomeComment() {

			return "";

		}

		public String NomePattern() {

			return "";

		}

		public String NomeOriginalDbColumnName() {

			return "Nome";

		}

		public String NomeReduzido;

		public String getNomeReduzido() {
			return this.NomeReduzido;
		}

		public Boolean NomeReduzidoIsNullable() {
			return true;
		}

		public Boolean NomeReduzidoIsKey() {
			return false;
		}

		public Integer NomeReduzidoLength() {
			return 20;
		}

		public Integer NomeReduzidoPrecision() {
			return 0;
		}

		public String NomeReduzidoDefault() {

			return null;

		}

		public String NomeReduzidoComment() {

			return "";

		}

		public String NomeReduzidoPattern() {

			return "";

		}

		public String NomeReduzidoOriginalDbColumnName() {

			return "NomeReduzido";

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
			return 80;
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

		public String Complemento;

		public String getComplemento() {
			return this.Complemento;
		}

		public Boolean ComplementoIsNullable() {
			return true;
		}

		public Boolean ComplementoIsKey() {
			return false;
		}

		public Integer ComplementoLength() {
			return 50;
		}

		public Integer ComplementoPrecision() {
			return 0;
		}

		public String ComplementoDefault() {

			return null;

		}

		public String ComplementoComment() {

			return "";

		}

		public String ComplementoPattern() {

			return "";

		}

		public String ComplementoOriginalDbColumnName() {

			return "Complemento";

		}

		public String Bairro;

		public String getBairro() {
			return this.Bairro;
		}

		public Boolean BairroIsNullable() {
			return true;
		}

		public Boolean BairroIsKey() {
			return false;
		}

		public Integer BairroLength() {
			return 40;
		}

		public Integer BairroPrecision() {
			return 0;
		}

		public String BairroDefault() {

			return null;

		}

		public String BairroComment() {

			return "";

		}

		public String BairroPattern() {

			return "";

		}

		public String BairroOriginalDbColumnName() {

			return "Bairro";

		}

		public String CEP;

		public String getCEP() {
			return this.CEP;
		}

		public Boolean CEPIsNullable() {
			return true;
		}

		public Boolean CEPIsKey() {
			return false;
		}

		public Integer CEPLength() {
			return 8;
		}

		public Integer CEPPrecision() {
			return 0;
		}

		public String CEPDefault() {

			return null;

		}

		public String CEPComment() {

			return "";

		}

		public String CEPPattern() {

			return "";

		}

		public String CEPOriginalDbColumnName() {

			return "CEP";

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

		public String Regiao;

		public String getRegiao() {
			return this.Regiao;
		}

		public Boolean RegiaoIsNullable() {
			return true;
		}

		public Boolean RegiaoIsKey() {
			return false;
		}

		public Integer RegiaoLength() {
			return 3;
		}

		public Integer RegiaoPrecision() {
			return 0;
		}

		public String RegiaoDefault() {

			return null;

		}

		public String RegiaoComment() {

			return "";

		}

		public String RegiaoPattern() {

			return "";

		}

		public String RegiaoOriginalDbColumnName() {

			return "Regiao";

		}

		public String DescricaoRegiao;

		public String getDescricaoRegiao() {
			return this.DescricaoRegiao;
		}

		public Boolean DescricaoRegiaoIsNullable() {
			return true;
		}

		public Boolean DescricaoRegiaoIsKey() {
			return false;
		}

		public Integer DescricaoRegiaoLength() {
			return 15;
		}

		public Integer DescricaoRegiaoPrecision() {
			return 0;
		}

		public String DescricaoRegiaoDefault() {

			return null;

		}

		public String DescricaoRegiaoComment() {

			return "";

		}

		public String DescricaoRegiaoPattern() {

			return "";

		}

		public String DescricaoRegiaoOriginalDbColumnName() {

			return "DescricaoRegiao";

		}

		public String CodigoMunicipio;

		public String getCodigoMunicipio() {
			return this.CodigoMunicipio;
		}

		public Boolean CodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean CodigoMunicipioIsKey() {
			return false;
		}

		public Integer CodigoMunicipioLength() {
			return 5;
		}

		public Integer CodigoMunicipioPrecision() {
			return 0;
		}

		public String CodigoMunicipioDefault() {

			return null;

		}

		public String CodigoMunicipioComment() {

			return "";

		}

		public String CodigoMunicipioPattern() {

			return "";

		}

		public String CodigoMunicipioOriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String Municipio;

		public String getMunicipio() {
			return this.Municipio;
		}

		public Boolean MunicipioIsNullable() {
			return true;
		}

		public Boolean MunicipioIsKey() {
			return false;
		}

		public Integer MunicipioLength() {
			return 60;
		}

		public Integer MunicipioPrecision() {
			return 0;
		}

		public String MunicipioDefault() {

			return null;

		}

		public String MunicipioComment() {

			return "";

		}

		public String MunicipioPattern() {

			return "";

		}

		public String MunicipioOriginalDbColumnName() {

			return "Municipio";

		}

		public String CodigoPais;

		public String getCodigoPais() {
			return this.CodigoPais;
		}

		public Boolean CodigoPaisIsNullable() {
			return true;
		}

		public Boolean CodigoPaisIsKey() {
			return false;
		}

		public Integer CodigoPaisLength() {
			return 5;
		}

		public Integer CodigoPaisPrecision() {
			return 0;
		}

		public String CodigoPaisDefault() {

			return null;

		}

		public String CodigoPaisComment() {

			return "";

		}

		public String CodigoPaisPattern() {

			return "";

		}

		public String CodigoPaisOriginalDbColumnName() {

			return "CodigoPais";

		}

		public String NomeEstado;

		public String getNomeEstado() {
			return this.NomeEstado;
		}

		public Boolean NomeEstadoIsNullable() {
			return true;
		}

		public Boolean NomeEstadoIsKey() {
			return false;
		}

		public Integer NomeEstadoLength() {
			return 20;
		}

		public Integer NomeEstadoPrecision() {
			return 0;
		}

		public String NomeEstadoDefault() {

			return null;

		}

		public String NomeEstadoComment() {

			return "";

		}

		public String NomeEstadoPattern() {

			return "";

		}

		public String NomeEstadoOriginalDbColumnName() {

			return "NomeEstado";

		}

		public String DDD;

		public String getDDD() {
			return this.DDD;
		}

		public Boolean DDDIsNullable() {
			return true;
		}

		public Boolean DDDIsKey() {
			return false;
		}

		public Integer DDDLength() {
			return 3;
		}

		public Integer DDDPrecision() {
			return 0;
		}

		public String DDDDefault() {

			return null;

		}

		public String DDDComment() {

			return "";

		}

		public String DDDPattern() {

			return "";

		}

		public String DDDOriginalDbColumnName() {

			return "DDD";

		}

		public String TributacaoFavoravel;

		public String getTributacaoFavoravel() {
			return this.TributacaoFavoravel;
		}

		public Boolean TributacaoFavoravelIsNullable() {
			return true;
		}

		public Boolean TributacaoFavoravelIsKey() {
			return false;
		}

		public Integer TributacaoFavoravelLength() {
			return 1;
		}

		public Integer TributacaoFavoravelPrecision() {
			return 0;
		}

		public String TributacaoFavoravelDefault() {

			return null;

		}

		public String TributacaoFavoravelComment() {

			return "";

		}

		public String TributacaoFavoravelPattern() {

			return "";

		}

		public String TributacaoFavoravelOriginalDbColumnName() {

			return "TributacaoFavoravel";

		}

		public String DDI;

		public String getDDI() {
			return this.DDI;
		}

		public Boolean DDIIsNullable() {
			return true;
		}

		public Boolean DDIIsKey() {
			return false;
		}

		public Integer DDILength() {
			return 6;
		}

		public Integer DDIPrecision() {
			return 0;
		}

		public String DDIDefault() {

			return null;

		}

		public String DDIComment() {

			return "";

		}

		public String DDIPattern() {

			return "";

		}

		public String DDIOriginalDbColumnName() {

			return "DDI";

		}

		public String CodIBGE;

		public String getCodIBGE() {
			return this.CodIBGE;
		}

		public Boolean CodIBGEIsNullable() {
			return true;
		}

		public Boolean CodIBGEIsKey() {
			return false;
		}

		public Integer CodIBGELength() {
			return 11;
		}

		public Integer CodIBGEPrecision() {
			return 0;
		}

		public String CodIBGEDefault() {

			return null;

		}

		public String CodIBGEComment() {

			return "";

		}

		public String CodIBGEPattern() {

			return "";

		}

		public String CodIBGEOriginalDbColumnName() {

			return "CodIBGE";

		}

		public String UCodigoMunicipio;

		public String getUCodigoMunicipio() {
			return this.UCodigoMunicipio;
		}

		public Boolean UCodigoMunicipioIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioLength() {
			return 5;
		}

		public Integer UCodigoMunicipioPrecision() {
			return 0;
		}

		public String UCodigoMunicipioDefault() {

			return null;

		}

		public String UCodigoMunicipioComment() {

			return "";

		}

		public String UCodigoMunicipioPattern() {

			return "";

		}

		public String UCodigoMunicipioOriginalDbColumnName() {

			return "UCodigoMunicipio";

		}

		public String EnderecoRecebimento;

		public String getEnderecoRecebimento() {
			return this.EnderecoRecebimento;
		}

		public Boolean EnderecoRecebimentoIsNullable() {
			return true;
		}

		public Boolean EnderecoRecebimentoIsKey() {
			return false;
		}

		public Integer EnderecoRecebimentoLength() {
			return 40;
		}

		public Integer EnderecoRecebimentoPrecision() {
			return 0;
		}

		public String EnderecoRecebimentoDefault() {

			return null;

		}

		public String EnderecoRecebimentoComment() {

			return "";

		}

		public String EnderecoRecebimentoPattern() {

			return "";

		}

		public String EnderecoRecebimentoOriginalDbColumnName() {

			return "EnderecoRecebimento";

		}

		public String Telefone;

		public String getTelefone() {
			return this.Telefone;
		}

		public Boolean TelefoneIsNullable() {
			return true;
		}

		public Boolean TelefoneIsKey() {
			return false;
		}

		public Integer TelefoneLength() {
			return 15;
		}

		public Integer TelefonePrecision() {
			return 0;
		}

		public String TelefoneDefault() {

			return null;

		}

		public String TelefoneComment() {

			return "";

		}

		public String TelefonePattern() {

			return "";

		}

		public String TelefoneOriginalDbColumnName() {

			return "Telefone";

		}

		public String FAX;

		public String getFAX() {
			return this.FAX;
		}

		public Boolean FAXIsNullable() {
			return true;
		}

		public Boolean FAXIsKey() {
			return false;
		}

		public Integer FAXLength() {
			return 15;
		}

		public Integer FAXPrecision() {
			return 0;
		}

		public String FAXDefault() {

			return null;

		}

		public String FAXComment() {

			return "";

		}

		public String FAXPattern() {

			return "";

		}

		public String FAXOriginalDbColumnName() {

			return "FAX";

		}

		public String FoneCP;

		public String getFoneCP() {
			return this.FoneCP;
		}

		public Boolean FoneCPIsNullable() {
			return true;
		}

		public Boolean FoneCPIsKey() {
			return false;
		}

		public Integer FoneCPLength() {
			return 15;
		}

		public Integer FoneCPPrecision() {
			return 0;
		}

		public String FoneCPDefault() {

			return null;

		}

		public String FoneCPComment() {

			return "";

		}

		public String FoneCPPattern() {

			return "";

		}

		public String FoneCPOriginalDbColumnName() {

			return "FoneCP";

		}

		public String Contato;

		public String getContato() {
			return this.Contato;
		}

		public Boolean ContatoIsNullable() {
			return true;
		}

		public Boolean ContatoIsKey() {
			return false;
		}

		public Integer ContatoLength() {
			return 80;
		}

		public Integer ContatoPrecision() {
			return 0;
		}

		public String ContatoDefault() {

			return null;

		}

		public String ContatoComment() {

			return "";

		}

		public String ContatoPattern() {

			return "";

		}

		public String ContatoOriginalDbColumnName() {

			return "Contato";

		}

		public String PessoaFisica;

		public String getPessoaFisica() {
			return this.PessoaFisica;
		}

		public Boolean PessoaFisicaIsNullable() {
			return true;
		}

		public Boolean PessoaFisicaIsKey() {
			return false;
		}

		public Integer PessoaFisicaLength() {
			return 18;
		}

		public Integer PessoaFisicaPrecision() {
			return 0;
		}

		public String PessoaFisicaDefault() {

			return null;

		}

		public String PessoaFisicaComment() {

			return "";

		}

		public String PessoaFisicaPattern() {

			return "";

		}

		public String PessoaFisicaOriginalDbColumnName() {

			return "PessoaFisica";

		}

		public String Pais;

		public String getPais() {
			return this.Pais;
		}

		public Boolean PaisIsNullable() {
			return true;
		}

		public Boolean PaisIsKey() {
			return false;
		}

		public Integer PaisLength() {
			return 3;
		}

		public Integer PaisPrecision() {
			return 0;
		}

		public String PaisDefault() {

			return null;

		}

		public String PaisComment() {

			return "";

		}

		public String PaisPattern() {

			return "";

		}

		public String PaisOriginalDbColumnName() {

			return "Pais";

		}

		public String Inscricao;

		public String getInscricao() {
			return this.Inscricao;
		}

		public Boolean InscricaoIsNullable() {
			return true;
		}

		public Boolean InscricaoIsKey() {
			return false;
		}

		public Integer InscricaoLength() {
			return 18;
		}

		public Integer InscricaoPrecision() {
			return 0;
		}

		public String InscricaoDefault() {

			return null;

		}

		public String InscricaoComment() {

			return "";

		}

		public String InscricaoPattern() {

			return "";

		}

		public String InscricaoOriginalDbColumnName() {

			return "Inscricao";

		}

		public String InscricaoMunicipal;

		public String getInscricaoMunicipal() {
			return this.InscricaoMunicipal;
		}

		public Boolean InscricaoMunicipalIsNullable() {
			return true;
		}

		public Boolean InscricaoMunicipalIsKey() {
			return false;
		}

		public Integer InscricaoMunicipalLength() {
			return 18;
		}

		public Integer InscricaoMunicipalPrecision() {
			return 0;
		}

		public String InscricaoMunicipalDefault() {

			return null;

		}

		public String InscricaoMunicipalComment() {

			return "";

		}

		public String InscricaoMunicipalPattern() {

			return "";

		}

		public String InscricaoMunicipalOriginalDbColumnName() {

			return "InscricaoMunicipal";

		}

		public String Vendedor;

		public String getVendedor() {
			return this.Vendedor;
		}

		public Boolean VendedorIsNullable() {
			return true;
		}

		public Boolean VendedorIsKey() {
			return false;
		}

		public Integer VendedorLength() {
			return 6;
		}

		public Integer VendedorPrecision() {
			return 0;
		}

		public String VendedorDefault() {

			return null;

		}

		public String VendedorComment() {

			return "";

		}

		public String VendedorPattern() {

			return "";

		}

		public String VendedorOriginalDbColumnName() {

			return "Vendedor";

		}

		public Double Comissao;

		public Double getComissao() {
			return this.Comissao;
		}

		public Boolean ComissaoIsNullable() {
			return true;
		}

		public Boolean ComissaoIsKey() {
			return false;
		}

		public Integer ComissaoLength() {
			return 15;
		}

		public Integer ComissaoPrecision() {
			return 0;
		}

		public String ComissaoDefault() {

			return "";

		}

		public String ComissaoComment() {

			return "";

		}

		public String ComissaoPattern() {

			return "";

		}

		public String ComissaoOriginalDbColumnName() {

			return "Comissao";

		}

		public String Conta;

		public String getConta() {
			return this.Conta;
		}

		public Boolean ContaIsNullable() {
			return true;
		}

		public Boolean ContaIsKey() {
			return false;
		}

		public Integer ContaLength() {
			return 20;
		}

		public Integer ContaPrecision() {
			return 0;
		}

		public String ContaDefault() {

			return null;

		}

		public String ContaComment() {

			return "";

		}

		public String ContaPattern() {

			return "";

		}

		public String ContaOriginalDbColumnName() {

			return "Conta";

		}

		public String Banco1;

		public String getBanco1() {
			return this.Banco1;
		}

		public Boolean Banco1IsNullable() {
			return true;
		}

		public Boolean Banco1IsKey() {
			return false;
		}

		public Integer Banco1Length() {
			return 3;
		}

		public Integer Banco1Precision() {
			return 0;
		}

		public String Banco1Default() {

			return null;

		}

		public String Banco1Comment() {

			return "";

		}

		public String Banco1Pattern() {

			return "";

		}

		public String Banco1OriginalDbColumnName() {

			return "Banco1";

		}

		public String Banco2;

		public String getBanco2() {
			return this.Banco2;
		}

		public Boolean Banco2IsNullable() {
			return true;
		}

		public Boolean Banco2IsKey() {
			return false;
		}

		public Integer Banco2Length() {
			return 3;
		}

		public Integer Banco2Precision() {
			return 0;
		}

		public String Banco2Default() {

			return null;

		}

		public String Banco2Comment() {

			return "";

		}

		public String Banco2Pattern() {

			return "";

		}

		public String Banco2OriginalDbColumnName() {

			return "Banco2";

		}

		public String Banco3;

		public String getBanco3() {
			return this.Banco3;
		}

		public Boolean Banco3IsNullable() {
			return true;
		}

		public Boolean Banco3IsKey() {
			return false;
		}

		public Integer Banco3Length() {
			return 3;
		}

		public Integer Banco3Precision() {
			return 0;
		}

		public String Banco3Default() {

			return null;

		}

		public String Banco3Comment() {

			return "";

		}

		public String Banco3Pattern() {

			return "";

		}

		public String Banco3OriginalDbColumnName() {

			return "Banco3";

		}

		public String Banco4;

		public String getBanco4() {
			return this.Banco4;
		}

		public Boolean Banco4IsNullable() {
			return true;
		}

		public Boolean Banco4IsKey() {
			return false;
		}

		public Integer Banco4Length() {
			return 3;
		}

		public Integer Banco4Precision() {
			return 0;
		}

		public String Banco4Default() {

			return null;

		}

		public String Banco4Comment() {

			return "";

		}

		public String Banco4Pattern() {

			return "";

		}

		public String Banco4OriginalDbColumnName() {

			return "Banco4";

		}

		public String Banco5;

		public String getBanco5() {
			return this.Banco5;
		}

		public Boolean Banco5IsNullable() {
			return true;
		}

		public Boolean Banco5IsKey() {
			return false;
		}

		public Integer Banco5Length() {
			return 3;
		}

		public Integer Banco5Precision() {
			return 0;
		}

		public String Banco5Default() {

			return null;

		}

		public String Banco5Comment() {

			return "";

		}

		public String Banco5Pattern() {

			return "";

		}

		public String Banco5OriginalDbColumnName() {

			return "Banco5";

		}

		public String Transporte;

		public String getTransporte() {
			return this.Transporte;
		}

		public Boolean TransporteIsNullable() {
			return true;
		}

		public Boolean TransporteIsKey() {
			return false;
		}

		public Integer TransporteLength() {
			return 6;
		}

		public Integer TransportePrecision() {
			return 0;
		}

		public String TransporteDefault() {

			return null;

		}

		public String TransporteComment() {

			return "";

		}

		public String TransportePattern() {

			return "";

		}

		public String TransporteOriginalDbColumnName() {

			return "Transporte";

		}

		public String TipoFrete;

		public String getTipoFrete() {
			return this.TipoFrete;
		}

		public Boolean TipoFreteIsNullable() {
			return true;
		}

		public Boolean TipoFreteIsKey() {
			return false;
		}

		public Integer TipoFreteLength() {
			return 1;
		}

		public Integer TipoFretePrecision() {
			return 0;
		}

		public String TipoFreteDefault() {

			return null;

		}

		public String TipoFreteComment() {

			return "";

		}

		public String TipoFretePattern() {

			return "";

		}

		public String TipoFreteOriginalDbColumnName() {

			return "TipoFrete";

		}

		public String EnderecoCobranca;

		public String getEnderecoCobranca() {
			return this.EnderecoCobranca;
		}

		public Boolean EnderecoCobrancaIsNullable() {
			return true;
		}

		public Boolean EnderecoCobrancaIsKey() {
			return false;
		}

		public Integer EnderecoCobrancaLength() {
			return 40;
		}

		public Integer EnderecoCobrancaPrecision() {
			return 0;
		}

		public String EnderecoCobrancaDefault() {

			return null;

		}

		public String EnderecoCobrancaComment() {

			return "";

		}

		public String EnderecoCobrancaPattern() {

			return "";

		}

		public String EnderecoCobrancaOriginalDbColumnName() {

			return "EnderecoCobranca";

		}

		public String Condicao;

		public String getCondicao() {
			return this.Condicao;
		}

		public Boolean CondicaoIsNullable() {
			return true;
		}

		public Boolean CondicaoIsKey() {
			return false;
		}

		public Integer CondicaoLength() {
			return 3;
		}

		public Integer CondicaoPrecision() {
			return 0;
		}

		public String CondicaoDefault() {

			return null;

		}

		public String CondicaoComment() {

			return "";

		}

		public String CondicaoPattern() {

			return "";

		}

		public String CondicaoOriginalDbColumnName() {

			return "Condicao";

		}

		public String BairroCobranca;

		public String getBairroCobranca() {
			return this.BairroCobranca;
		}

		public Boolean BairroCobrancaIsNullable() {
			return true;
		}

		public Boolean BairroCobrancaIsKey() {
			return false;
		}

		public Integer BairroCobrancaLength() {
			return 30;
		}

		public Integer BairroCobrancaPrecision() {
			return 0;
		}

		public String BairroCobrancaDefault() {

			return null;

		}

		public String BairroCobrancaComment() {

			return "";

		}

		public String BairroCobrancaPattern() {

			return "";

		}

		public String BairroCobrancaOriginalDbColumnName() {

			return "BairroCobranca";

		}

		public Double Descricao;

		public Double getDescricao() {
			return this.Descricao;
		}

		public Boolean DescricaoIsNullable() {
			return true;
		}

		public Boolean DescricaoIsKey() {
			return false;
		}

		public Integer DescricaoLength() {
			return 15;
		}

		public Integer DescricaoPrecision() {
			return 0;
		}

		public String DescricaoDefault() {

			return "";

		}

		public String DescricaoComment() {

			return "";

		}

		public String DescricaoPattern() {

			return "";

		}

		public String DescricaoOriginalDbColumnName() {

			return "Descricao";

		}

		public String CEPCobranca;

		public String getCEPCobranca() {
			return this.CEPCobranca;
		}

		public Boolean CEPCobrancaIsNullable() {
			return true;
		}

		public Boolean CEPCobrancaIsKey() {
			return false;
		}

		public Integer CEPCobrancaLength() {
			return 8;
		}

		public Integer CEPCobrancaPrecision() {
			return 0;
		}

		public String CEPCobrancaDefault() {

			return null;

		}

		public String CEPCobrancaComment() {

			return "";

		}

		public String CEPCobrancaPattern() {

			return "";

		}

		public String CEPCobrancaOriginalDbColumnName() {

			return "CEPCobranca";

		}

		public String EstadoCobranca;

		public String getEstadoCobranca() {
			return this.EstadoCobranca;
		}

		public Boolean EstadoCobrancaIsNullable() {
			return true;
		}

		public Boolean EstadoCobrancaIsKey() {
			return false;
		}

		public Integer EstadoCobrancaLength() {
			return 2;
		}

		public Integer EstadoCobrancaPrecision() {
			return 0;
		}

		public String EstadoCobrancaDefault() {

			return null;

		}

		public String EstadoCobrancaComment() {

			return "";

		}

		public String EstadoCobrancaPattern() {

			return "";

		}

		public String EstadoCobrancaOriginalDbColumnName() {

			return "EstadoCobranca";

		}

		public String UCodigoMunicipioCobranca;

		public String getUCodigoMunicipioCobranca() {
			return this.UCodigoMunicipioCobranca;
		}

		public Boolean UCodigoMunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean UCodigoMunicipioCobrancaIsKey() {
			return false;
		}

		public Integer UCodigoMunicipioCobrancaLength() {
			return 5;
		}

		public Integer UCodigoMunicipioCobrancaPrecision() {
			return 0;
		}

		public String UCodigoMunicipioCobrancaDefault() {

			return null;

		}

		public String UCodigoMunicipioCobrancaComment() {

			return "";

		}

		public String UCodigoMunicipioCobrancaPattern() {

			return "";

		}

		public String UCodigoMunicipioCobrancaOriginalDbColumnName() {

			return "UCodigoMunicipioCobranca";

		}

		public String MunicipioCobranca;

		public String getMunicipioCobranca() {
			return this.MunicipioCobranca;
		}

		public Boolean MunicipioCobrancaIsNullable() {
			return true;
		}

		public Boolean MunicipioCobrancaIsKey() {
			return false;
		}

		public Integer MunicipioCobrancaLength() {
			return 60;
		}

		public Integer MunicipioCobrancaPrecision() {
			return 0;
		}

		public String MunicipioCobrancaDefault() {

			return null;

		}

		public String MunicipioCobrancaComment() {

			return "";

		}

		public String MunicipioCobrancaPattern() {

			return "";

		}

		public String MunicipioCobrancaOriginalDbColumnName() {

			return "MunicipioCobranca";

		}

		public String Prioridade;

		public String getPrioridade() {
			return this.Prioridade;
		}

		public Boolean PrioridadeIsNullable() {
			return true;
		}

		public Boolean PrioridadeIsKey() {
			return false;
		}

		public Integer PrioridadeLength() {
			return 1;
		}

		public Integer PrioridadePrecision() {
			return 0;
		}

		public String PrioridadeDefault() {

			return null;

		}

		public String PrioridadeComment() {

			return "";

		}

		public String PrioridadePattern() {

			return "";

		}

		public String PrioridadeOriginalDbColumnName() {

			return "Prioridade";

		}

		public String Risco;

		public String getRisco() {
			return this.Risco;
		}

		public Boolean RiscoIsNullable() {
			return true;
		}

		public Boolean RiscoIsKey() {
			return false;
		}

		public Integer RiscoLength() {
			return 1;
		}

		public Integer RiscoPrecision() {
			return 0;
		}

		public String RiscoDefault() {

			return null;

		}

		public String RiscoComment() {

			return "";

		}

		public String RiscoPattern() {

			return "";

		}

		public String RiscoOriginalDbColumnName() {

			return "Risco";

		}

		public Double LimiteCredito;

		public Double getLimiteCredito() {
			return this.LimiteCredito;
		}

		public Boolean LimiteCreditoIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoIsKey() {
			return false;
		}

		public Integer LimiteCreditoLength() {
			return 15;
		}

		public Integer LimiteCreditoPrecision() {
			return 0;
		}

		public String LimiteCreditoDefault() {

			return "";

		}

		public String LimiteCreditoComment() {

			return "";

		}

		public String LimiteCreditoPattern() {

			return "";

		}

		public String LimiteCreditoOriginalDbColumnName() {

			return "LimiteCredito";

		}

		public String VencimentoLimiteCredito;

		public String getVencimentoLimiteCredito() {
			return this.VencimentoLimiteCredito;
		}

		public Boolean VencimentoLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean VencimentoLimiteCreditoIsKey() {
			return false;
		}

		public Integer VencimentoLimiteCreditoLength() {
			return 8;
		}

		public Integer VencimentoLimiteCreditoPrecision() {
			return 0;
		}

		public String VencimentoLimiteCreditoDefault() {

			return null;

		}

		public String VencimentoLimiteCreditoComment() {

			return "";

		}

		public String VencimentoLimiteCreditoPattern() {

			return "";

		}

		public String VencimentoLimiteCreditoOriginalDbColumnName() {

			return "VencimentoLimiteCredito";

		}

		public String Classe;

		public String getClasse() {
			return this.Classe;
		}

		public Boolean ClasseIsNullable() {
			return true;
		}

		public Boolean ClasseIsKey() {
			return false;
		}

		public Integer ClasseLength() {
			return 1;
		}

		public Integer ClassePrecision() {
			return 0;
		}

		public String ClasseDefault() {

			return null;

		}

		public String ClasseComment() {

			return "";

		}

		public String ClassePattern() {

			return "";

		}

		public String ClasseOriginalDbColumnName() {

			return "Classe";

		}

		public Double LimiteCreditoFinal;

		public Double getLimiteCreditoFinal() {
			return this.LimiteCreditoFinal;
		}

		public Boolean LimiteCreditoFinalIsNullable() {
			return true;
		}

		public Boolean LimiteCreditoFinalIsKey() {
			return false;
		}

		public Integer LimiteCreditoFinalLength() {
			return 15;
		}

		public Integer LimiteCreditoFinalPrecision() {
			return 0;
		}

		public String LimiteCreditoFinalDefault() {

			return "";

		}

		public String LimiteCreditoFinalComment() {

			return "";

		}

		public String LimiteCreditoFinalPattern() {

			return "";

		}

		public String LimiteCreditoFinalOriginalDbColumnName() {

			return "LimiteCreditoFinal";

		}

		public Double MoedaLimiteCredito;

		public Double getMoedaLimiteCredito() {
			return this.MoedaLimiteCredito;
		}

		public Boolean MoedaLimiteCreditoIsNullable() {
			return true;
		}

		public Boolean MoedaLimiteCreditoIsKey() {
			return false;
		}

		public Integer MoedaLimiteCreditoLength() {
			return 15;
		}

		public Integer MoedaLimiteCreditoPrecision() {
			return 0;
		}

		public String MoedaLimiteCreditoDefault() {

			return "";

		}

		public String MoedaLimiteCreditoComment() {

			return "";

		}

		public String MoedaLimiteCreditoPattern() {

			return "";

		}

		public String MoedaLimiteCreditoOriginalDbColumnName() {

			return "MoedaLimiteCredito";

		}

		public Double MaiorSaldo;

		public Double getMaiorSaldo() {
			return this.MaiorSaldo;
		}

		public Boolean MaiorSaldoIsNullable() {
			return true;
		}

		public Boolean MaiorSaldoIsKey() {
			return false;
		}

		public Integer MaiorSaldoLength() {
			return 15;
		}

		public Integer MaiorSaldoPrecision() {
			return 0;
		}

		public String MaiorSaldoDefault() {

			return "";

		}

		public String MaiorSaldoComment() {

			return "";

		}

		public String MaiorSaldoPattern() {

			return "";

		}

		public String MaiorSaldoOriginalDbColumnName() {

			return "MaiorSaldo";

		}

		public Double MaiorCompra;

		public Double getMaiorCompra() {
			return this.MaiorCompra;
		}

		public Boolean MaiorCompraIsNullable() {
			return true;
		}

		public Boolean MaiorCompraIsKey() {
			return false;
		}

		public Integer MaiorCompraLength() {
			return 15;
		}

		public Integer MaiorCompraPrecision() {
			return 0;
		}

		public String MaiorCompraDefault() {

			return "";

		}

		public String MaiorCompraComment() {

			return "";

		}

		public String MaiorCompraPattern() {

			return "";

		}

		public String MaiorCompraOriginalDbColumnName() {

			return "MaiorCompra";

		}

		public Double MediaAtraso;

		public Double getMediaAtraso() {
			return this.MediaAtraso;
		}

		public Boolean MediaAtrasoIsNullable() {
			return true;
		}

		public Boolean MediaAtrasoIsKey() {
			return false;
		}

		public Integer MediaAtrasoLength() {
			return 15;
		}

		public Integer MediaAtrasoPrecision() {
			return 0;
		}

		public String MediaAtrasoDefault() {

			return "";

		}

		public String MediaAtrasoComment() {

			return "";

		}

		public String MediaAtrasoPattern() {

			return "";

		}

		public String MediaAtrasoOriginalDbColumnName() {

			return "MediaAtraso";

		}

		public String PrimeiraCompra;

		public String getPrimeiraCompra() {
			return this.PrimeiraCompra;
		}

		public Boolean PrimeiraCompraIsNullable() {
			return true;
		}

		public Boolean PrimeiraCompraIsKey() {
			return false;
		}

		public Integer PrimeiraCompraLength() {
			return 8;
		}

		public Integer PrimeiraCompraPrecision() {
			return 0;
		}

		public String PrimeiraCompraDefault() {

			return null;

		}

		public String PrimeiraCompraComment() {

			return "";

		}

		public String PrimeiraCompraPattern() {

			return "";

		}

		public String PrimeiraCompraOriginalDbColumnName() {

			return "PrimeiraCompra";

		}

		public String UltimaCompra;

		public String getUltimaCompra() {
			return this.UltimaCompra;
		}

		public Boolean UltimaCompraIsNullable() {
			return true;
		}

		public Boolean UltimaCompraIsKey() {
			return false;
		}

		public Integer UltimaCompraLength() {
			return 8;
		}

		public Integer UltimaCompraPrecision() {
			return 0;
		}

		public String UltimaCompraDefault() {

			return null;

		}

		public String UltimaCompraComment() {

			return "";

		}

		public String UltimaCompraPattern() {

			return "";

		}

		public String UltimaCompraOriginalDbColumnName() {

			return "UltimaCompra";

		}

		public Double NumeroCompras;

		public Double getNumeroCompras() {
			return this.NumeroCompras;
		}

		public Boolean NumeroComprasIsNullable() {
			return true;
		}

		public Boolean NumeroComprasIsKey() {
			return false;
		}

		public Integer NumeroComprasLength() {
			return 15;
		}

		public Integer NumeroComprasPrecision() {
			return 0;
		}

		public String NumeroComprasDefault() {

			return "";

		}

		public String NumeroComprasComment() {

			return "";

		}

		public String NumeroComprasPattern() {

			return "";

		}

		public String NumeroComprasOriginalDbColumnName() {

			return "NumeroCompras";

		}

		public String FormularioVisita;

		public String getFormularioVisita() {
			return this.FormularioVisita;
		}

		public Boolean FormularioVisitaIsNullable() {
			return true;
		}

		public Boolean FormularioVisitaIsKey() {
			return false;
		}

		public Integer FormularioVisitaLength() {
			return 3;
		}

		public Integer FormularioVisitaPrecision() {
			return 0;
		}

		public String FormularioVisitaDefault() {

			return null;

		}

		public String FormularioVisitaComment() {

			return "";

		}

		public String FormularioVisitaPattern() {

			return "";

		}

		public String FormularioVisitaOriginalDbColumnName() {

			return "FormularioVisita";

		}

		public Double TempoVisita;

		public Double getTempoVisita() {
			return this.TempoVisita;
		}

		public Boolean TempoVisitaIsNullable() {
			return true;
		}

		public Boolean TempoVisitaIsKey() {
			return false;
		}

		public Integer TempoVisitaLength() {
			return 15;
		}

		public Integer TempoVisitaPrecision() {
			return 0;
		}

		public String TempoVisitaDefault() {

			return "";

		}

		public String TempoVisitaComment() {

			return "";

		}

		public String TempoVisitaPattern() {

			return "";

		}

		public String TempoVisitaOriginalDbColumnName() {

			return "TempoVisita";

		}

		public String UltimaVisita;

		public String getUltimaVisita() {
			return this.UltimaVisita;
		}

		public Boolean UltimaVisitaIsNullable() {
			return true;
		}

		public Boolean UltimaVisitaIsKey() {
			return false;
		}

		public Integer UltimaVisitaLength() {
			return 8;
		}

		public Integer UltimaVisitaPrecision() {
			return 0;
		}

		public String UltimaVisitaDefault() {

			return null;

		}

		public String UltimaVisitaComment() {

			return "";

		}

		public String UltimaVisitaPattern() {

			return "";

		}

		public String UltimaVisitaOriginalDbColumnName() {

			return "UltimaVisita";

		}

		public String TempoVisita1;

		public String getTempoVisita1() {
			return this.TempoVisita1;
		}

		public Boolean TempoVisita1IsNullable() {
			return true;
		}

		public Boolean TempoVisita1IsKey() {
			return false;
		}

		public Integer TempoVisita1Length() {
			return 5;
		}

		public Integer TempoVisita1Precision() {
			return 0;
		}

		public String TempoVisita1Default() {

			return null;

		}

		public String TempoVisita1Comment() {

			return "";

		}

		public String TempoVisita1Pattern() {

			return "";

		}

		public String TempoVisita1OriginalDbColumnName() {

			return "TempoVisita";

		}

		public String TempoPadrao;

		public String getTempoPadrao() {
			return this.TempoPadrao;
		}

		public Boolean TempoPadraoIsNullable() {
			return true;
		}

		public Boolean TempoPadraoIsKey() {
			return false;
		}

		public Integer TempoPadraoLength() {
			return 5;
		}

		public Integer TempoPadraoPrecision() {
			return 0;
		}

		public String TempoPadraoDefault() {

			return null;

		}

		public String TempoPadraoComment() {

			return "";

		}

		public String TempoPadraoPattern() {

			return "";

		}

		public String TempoPadraoOriginalDbColumnName() {

			return "TempoPadrao";

		}

		public String ClassificacaoVenda;

		public String getClassificacaoVenda() {
			return this.ClassificacaoVenda;
		}

		public Boolean ClassificacaoVendaIsNullable() {
			return true;
		}

		public Boolean ClassificacaoVendaIsKey() {
			return false;
		}

		public Integer ClassificacaoVendaLength() {
			return 1;
		}

		public Integer ClassificacaoVendaPrecision() {
			return 0;
		}

		public String ClassificacaoVendaDefault() {

			return null;

		}

		public String ClassificacaoVendaComment() {

			return "";

		}

		public String ClassificacaoVendaPattern() {

			return "";

		}

		public String ClassificacaoVendaOriginalDbColumnName() {

			return "ClassificacaoVenda";

		}

		public String Mensagem;

		public String getMensagem() {
			return this.Mensagem;
		}

		public Boolean MensagemIsNullable() {
			return true;
		}

		public Boolean MensagemIsKey() {
			return false;
		}

		public Integer MensagemLength() {
			return 3;
		}

		public Integer MensagemPrecision() {
			return 0;
		}

		public String MensagemDefault() {

			return "";

		}

		public String MensagemComment() {

			return "";

		}

		public String MensagemPattern() {

			return "";

		}

		public String MensagemOriginalDbColumnName() {

			return "Mensagem";

		}

		public Double SaldoTitulo;

		public Double getSaldoTitulo() {
			return this.SaldoTitulo;
		}

		public Boolean SaldoTituloIsNullable() {
			return true;
		}

		public Boolean SaldoTituloIsKey() {
			return false;
		}

		public Integer SaldoTituloLength() {
			return 15;
		}

		public Integer SaldoTituloPrecision() {
			return 0;
		}

		public String SaldoTituloDefault() {

			return "";

		}

		public String SaldoTituloComment() {

			return "";

		}

		public String SaldoTituloPattern() {

			return "";

		}

		public String SaldoTituloOriginalDbColumnName() {

			return "SaldoTitulo";

		}

		public String RecolheISS;

		public String getRecolheISS() {
			return this.RecolheISS;
		}

		public Boolean RecolheISSIsNullable() {
			return true;
		}

		public Boolean RecolheISSIsKey() {
			return false;
		}

		public Integer RecolheISSLength() {
			return 1;
		}

		public Integer RecolheISSPrecision() {
			return 0;
		}

		public String RecolheISSDefault() {

			return null;

		}

		public String RecolheISSComment() {

			return "";

		}

		public String RecolheISSPattern() {

			return "";

		}

		public String RecolheISSOriginalDbColumnName() {

			return "RecolheISS";

		}

		public Double SaldoPedidoLiberado;

		public Double getSaldoPedidoLiberado() {
			return this.SaldoPedidoLiberado;
		}

		public Boolean SaldoPedidoLiberadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoLiberadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLiberadoLength() {
			return 15;
		}

		public Integer SaldoPedidoLiberadoPrecision() {
			return 0;
		}

		public String SaldoPedidoLiberadoDefault() {

			return "";

		}

		public String SaldoPedidoLiberadoComment() {

			return "";

		}

		public String SaldoPedidoLiberadoPattern() {

			return "";

		}

		public String SaldoPedidoLiberadoOriginalDbColumnName() {

			return "SaldoPedidoLiberado";

		}

		public Double NumeroPagamentos;

		public Double getNumeroPagamentos() {
			return this.NumeroPagamentos;
		}

		public Boolean NumeroPagamentosIsNullable() {
			return true;
		}

		public Boolean NumeroPagamentosIsKey() {
			return false;
		}

		public Integer NumeroPagamentosLength() {
			return 15;
		}

		public Integer NumeroPagamentosPrecision() {
			return 0;
		}

		public String NumeroPagamentosDefault() {

			return "";

		}

		public String NumeroPagamentosComment() {

			return "";

		}

		public String NumeroPagamentosPattern() {

			return "";

		}

		public String NumeroPagamentosOriginalDbColumnName() {

			return "NumeroPagamentos";

		}

		public String Transferencia;

		public String getTransferencia() {
			return this.Transferencia;
		}

		public Boolean TransferenciaIsNullable() {
			return true;
		}

		public Boolean TransferenciaIsKey() {
			return false;
		}

		public Integer TransferenciaLength() {
			return 1;
		}

		public Integer TransferenciaPrecision() {
			return 0;
		}

		public String TransferenciaDefault() {

			return null;

		}

		public String TransferenciaComment() {

			return "";

		}

		public String TransferenciaPattern() {

			return "";

		}

		public String TransferenciaOriginalDbColumnName() {

			return "Transferencia";

		}

		public String SUFRAMA;

		public String getSUFRAMA() {
			return this.SUFRAMA;
		}

		public Boolean SUFRAMAIsNullable() {
			return true;
		}

		public Boolean SUFRAMAIsKey() {
			return false;
		}

		public Integer SUFRAMALength() {
			return 12;
		}

		public Integer SUFRAMAPrecision() {
			return 0;
		}

		public String SUFRAMADefault() {

			return null;

		}

		public String SUFRAMAComment() {

			return "";

		}

		public String SUFRAMAPattern() {

			return "";

		}

		public String SUFRAMAOriginalDbColumnName() {

			return "SUFRAMA";

		}

		public Double ATR;

		public Double getATR() {
			return this.ATR;
		}

		public Boolean ATRIsNullable() {
			return true;
		}

		public Boolean ATRIsKey() {
			return false;
		}

		public Integer ATRLength() {
			return 15;
		}

		public Integer ATRPrecision() {
			return 0;
		}

		public String ATRDefault() {

			return "";

		}

		public String ATRComment() {

			return "";

		}

		public String ATRPattern() {

			return "";

		}

		public String ATROriginalDbColumnName() {

			return "ATR";

		}

		public Double ValorAcumulado;

		public Double getValorAcumulado() {
			return this.ValorAcumulado;
		}

		public Boolean ValorAcumuladoIsNullable() {
			return true;
		}

		public Boolean ValorAcumuladoIsKey() {
			return false;
		}

		public Integer ValorAcumuladoLength() {
			return 15;
		}

		public Integer ValorAcumuladoPrecision() {
			return 0;
		}

		public String ValorAcumuladoDefault() {

			return "";

		}

		public String ValorAcumuladoComment() {

			return "";

		}

		public String ValorAcumuladoPattern() {

			return "";

		}

		public String ValorAcumuladoOriginalDbColumnName() {

			return "ValorAcumulado";

		}

		public Double SaldoPedido;

		public Double getSaldoPedido() {
			return this.SaldoPedido;
		}

		public Boolean SaldoPedidoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoIsKey() {
			return false;
		}

		public Integer SaldoPedidoLength() {
			return 15;
		}

		public Integer SaldoPedidoPrecision() {
			return 0;
		}

		public String SaldoPedidoDefault() {

			return "";

		}

		public String SaldoPedidoComment() {

			return "";

		}

		public String SaldoPedidoPattern() {

			return "";

		}

		public String SaldoPedidoOriginalDbColumnName() {

			return "SaldoPedido";

		}

		public Double TituloProtestado;

		public Double getTituloProtestado() {
			return this.TituloProtestado;
		}

		public Boolean TituloProtestadoIsNullable() {
			return true;
		}

		public Boolean TituloProtestadoIsKey() {
			return false;
		}

		public Integer TituloProtestadoLength() {
			return 15;
		}

		public Integer TituloProtestadoPrecision() {
			return 0;
		}

		public String TituloProtestadoDefault() {

			return "";

		}

		public String TituloProtestadoComment() {

			return "";

		}

		public String TituloProtestadoPattern() {

			return "";

		}

		public String TituloProtestadoOriginalDbColumnName() {

			return "TituloProtestado";

		}

		public String DataUltimoTitulo;

		public String getDataUltimoTitulo() {
			return this.DataUltimoTitulo;
		}

		public Boolean DataUltimoTituloIsNullable() {
			return true;
		}

		public Boolean DataUltimoTituloIsKey() {
			return false;
		}

		public Integer DataUltimoTituloLength() {
			return 8;
		}

		public Integer DataUltimoTituloPrecision() {
			return 0;
		}

		public String DataUltimoTituloDefault() {

			return null;

		}

		public String DataUltimoTituloComment() {

			return "";

		}

		public String DataUltimoTituloPattern() {

			return "";

		}

		public String DataUltimoTituloOriginalDbColumnName() {

			return "DataUltimoTitulo";

		}

		public Double ChequeDevolvido;

		public Double getChequeDevolvido() {
			return this.ChequeDevolvido;
		}

		public Boolean ChequeDevolvidoIsNullable() {
			return true;
		}

		public Boolean ChequeDevolvidoIsKey() {
			return false;
		}

		public Integer ChequeDevolvidoLength() {
			return 15;
		}

		public Integer ChequeDevolvidoPrecision() {
			return 0;
		}

		public String ChequeDevolvidoDefault() {

			return "";

		}

		public String ChequeDevolvidoComment() {

			return "";

		}

		public String ChequeDevolvidoPattern() {

			return "";

		}

		public String ChequeDevolvidoOriginalDbColumnName() {

			return "ChequeDevolvido";

		}

		public String CaixaPostal;

		public String getCaixaPostal() {
			return this.CaixaPostal;
		}

		public Boolean CaixaPostalIsNullable() {
			return true;
		}

		public Boolean CaixaPostalIsKey() {
			return false;
		}

		public Integer CaixaPostalLength() {
			return 20;
		}

		public Integer CaixaPostalPrecision() {
			return 0;
		}

		public String CaixaPostalDefault() {

			return null;

		}

		public String CaixaPostalComment() {

			return "";

		}

		public String CaixaPostalPattern() {

			return "";

		}

		public String CaixaPostalOriginalDbColumnName() {

			return "CaixaPostal";

		}

		public Double Matriz;

		public Double getMatriz() {
			return this.Matriz;
		}

		public Boolean MatrizIsNullable() {
			return true;
		}

		public Boolean MatrizIsKey() {
			return false;
		}

		public Integer MatrizLength() {
			return 15;
		}

		public Integer MatrizPrecision() {
			return 0;
		}

		public String MatrizDefault() {

			return "";

		}

		public String MatrizComment() {

			return "";

		}

		public String MatrizPattern() {

			return "";

		}

		public String MatrizOriginalDbColumnName() {

			return "Matriz";

		}

		public String DataUltimoCheque;

		public String getDataUltimoCheque() {
			return this.DataUltimoCheque;
		}

		public Boolean DataUltimoChequeIsNullable() {
			return true;
		}

		public Boolean DataUltimoChequeIsKey() {
			return false;
		}

		public Integer DataUltimoChequeLength() {
			return 8;
		}

		public Integer DataUltimoChequePrecision() {
			return 0;
		}

		public String DataUltimoChequeDefault() {

			return null;

		}

		public String DataUltimoChequeComment() {

			return "";

		}

		public String DataUltimoChequePattern() {

			return "";

		}

		public String DataUltimoChequeOriginalDbColumnName() {

			return "DataUltimoCheque";

		}

		public Double MaiorDuplicata;

		public Double getMaiorDuplicata() {
			return this.MaiorDuplicata;
		}

		public Boolean MaiorDuplicataIsNullable() {
			return true;
		}

		public Boolean MaiorDuplicataIsKey() {
			return false;
		}

		public Integer MaiorDuplicataLength() {
			return 15;
		}

		public Integer MaiorDuplicataPrecision() {
			return 0;
		}

		public String MaiorDuplicataDefault() {

			return "";

		}

		public String MaiorDuplicataComment() {

			return "";

		}

		public String MaiorDuplicataPattern() {

			return "";

		}

		public String MaiorDuplicataOriginalDbColumnName() {

			return "MaiorDuplicata";

		}

		public String Tabela;

		public String getTabela() {
			return this.Tabela;
		}

		public Boolean TabelaIsNullable() {
			return true;
		}

		public Boolean TabelaIsKey() {
			return false;
		}

		public Integer TabelaLength() {
			return 3;
		}

		public Integer TabelaPrecision() {
			return 0;
		}

		public String TabelaDefault() {

			return null;

		}

		public String TabelaComment() {

			return "";

		}

		public String TabelaPattern() {

			return "";

		}

		public String TabelaOriginalDbColumnName() {

			return "Tabela";

		}

		public String ISSIncluso;

		public String getISSIncluso() {
			return this.ISSIncluso;
		}

		public Boolean ISSInclusoIsNullable() {
			return true;
		}

		public Boolean ISSInclusoIsKey() {
			return false;
		}

		public Integer ISSInclusoLength() {
			return 1;
		}

		public Integer ISSInclusoPrecision() {
			return 0;
		}

		public String ISSInclusoDefault() {

			return null;

		}

		public String ISSInclusoComment() {

			return "";

		}

		public String ISSInclusoPattern() {

			return "";

		}

		public String ISSInclusoOriginalDbColumnName() {

			return "ISSIncluso";

		}

		public Double SaldoDuplicataMoeda;

		public Double getSaldoDuplicataMoeda() {
			return this.SaldoDuplicataMoeda;
		}

		public Boolean SaldoDuplicataMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoDuplicataMoedaIsKey() {
			return false;
		}

		public Integer SaldoDuplicataMoedaLength() {
			return 15;
		}

		public Integer SaldoDuplicataMoedaPrecision() {
			return 0;
		}

		public String SaldoDuplicataMoedaDefault() {

			return "";

		}

		public String SaldoDuplicataMoedaComment() {

			return "";

		}

		public String SaldoDuplicataMoedaPattern() {

			return "";

		}

		public String SaldoDuplicataMoedaOriginalDbColumnName() {

			return "SaldoDuplicataMoeda";

		}

		public Double PagamentoAtrasado;

		public Double getPagamentoAtrasado() {
			return this.PagamentoAtrasado;
		}

		public Boolean PagamentoAtrasadoIsNullable() {
			return true;
		}

		public Boolean PagamentoAtrasadoIsKey() {
			return false;
		}

		public Integer PagamentoAtrasadoLength() {
			return 15;
		}

		public Integer PagamentoAtrasadoPrecision() {
			return 0;
		}

		public String PagamentoAtrasadoDefault() {

			return "";

		}

		public String PagamentoAtrasadoComment() {

			return "";

		}

		public String PagamentoAtrasadoPattern() {

			return "";

		}

		public String PagamentoAtrasadoOriginalDbColumnName() {

			return "PagamentoAtrasado";

		}

		public String Atividade;

		public String getAtividade() {
			return this.Atividade;
		}

		public Boolean AtividadeIsNullable() {
			return true;
		}

		public Boolean AtividadeIsKey() {
			return false;
		}

		public Integer AtividadeLength() {
			return 7;
		}

		public Integer AtividadePrecision() {
			return 0;
		}

		public String AtividadeDefault() {

			return "";

		}

		public String AtividadeComment() {

			return "";

		}

		public String AtividadePattern() {

			return "";

		}

		public String AtividadeOriginalDbColumnName() {

			return "Atividade";

		}

		public String Cargo1;

		public String getCargo1() {
			return this.Cargo1;
		}

		public Boolean Cargo1IsNullable() {
			return true;
		}

		public Boolean Cargo1IsKey() {
			return false;
		}

		public Integer Cargo1Length() {
			return 15;
		}

		public Integer Cargo1Precision() {
			return 0;
		}

		public String Cargo1Default() {

			return "";

		}

		public String Cargo1Comment() {

			return "";

		}

		public String Cargo1Pattern() {

			return "";

		}

		public String Cargo1OriginalDbColumnName() {

			return "Cargo1";

		}

		public String Cargo2;

		public String getCargo2() {
			return this.Cargo2;
		}

		public Boolean Cargo2IsNullable() {
			return true;
		}

		public Boolean Cargo2IsKey() {
			return false;
		}

		public Integer Cargo2Length() {
			return 15;
		}

		public Integer Cargo2Precision() {
			return 0;
		}

		public String Cargo2Default() {

			return "";

		}

		public String Cargo2Comment() {

			return "";

		}

		public String Cargo2Pattern() {

			return "";

		}

		public String Cargo2OriginalDbColumnName() {

			return "Cargo2";

		}

		public String Cargo3;

		public String getCargo3() {
			return this.Cargo3;
		}

		public Boolean Cargo3IsNullable() {
			return true;
		}

		public Boolean Cargo3IsKey() {
			return false;
		}

		public Integer Cargo3Length() {
			return 15;
		}

		public Integer Cargo3Precision() {
			return 0;
		}

		public String Cargo3Default() {

			return "";

		}

		public String Cargo3Comment() {

			return "";

		}

		public String Cargo3Pattern() {

			return "";

		}

		public String Cargo3OriginalDbColumnName() {

			return "Cargo3";

		}

		public String RepresentanteTecnico;

		public String getRepresentanteTecnico() {
			return this.RepresentanteTecnico;
		}

		public Boolean RepresentanteTecnicoIsNullable() {
			return true;
		}

		public Boolean RepresentanteTecnicoIsKey() {
			return false;
		}

		public Integer RepresentanteTecnicoLength() {
			return 6;
		}

		public Integer RepresentanteTecnicoPrecision() {
			return 0;
		}

		public String RepresentanteTecnicoDefault() {

			return null;

		}

		public String RepresentanteTecnicoComment() {

			return "";

		}

		public String RepresentanteTecnicoPattern() {

			return "";

		}

		public String RepresentanteTecnicoOriginalDbColumnName() {

			return "RepresentanteTecnico";

		}

		public String Supervisor;

		public String getSupervisor() {
			return this.Supervisor;
		}

		public Boolean SupervisorIsNullable() {
			return true;
		}

		public Boolean SupervisorIsKey() {
			return false;
		}

		public Integer SupervisorLength() {
			return 6;
		}

		public Integer SupervisorPrecision() {
			return 0;
		}

		public String SupervisorDefault() {

			return null;

		}

		public String SupervisorComment() {

			return "";

		}

		public String SupervisorPattern() {

			return "";

		}

		public String SupervisorOriginalDbColumnName() {

			return "Supervisor";

		}

		public Double AliquotaIR;

		public Double getAliquotaIR() {
			return this.AliquotaIR;
		}

		public Boolean AliquotaIRIsNullable() {
			return true;
		}

		public Boolean AliquotaIRIsKey() {
			return false;
		}

		public Integer AliquotaIRLength() {
			return 15;
		}

		public Integer AliquotaIRPrecision() {
			return 0;
		}

		public String AliquotaIRDefault() {

			return "";

		}

		public String AliquotaIRComment() {

			return "";

		}

		public String AliquotaIRPattern() {

			return "";

		}

		public String AliquotaIROriginalDbColumnName() {

			return "AliquotaIR";

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
			return 40;
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

		public String RG;

		public String getRG() {
			return this.RG;
		}

		public Boolean RGIsNullable() {
			return true;
		}

		public Boolean RGIsKey() {
			return false;
		}

		public Integer RGLength() {
			return 15;
		}

		public Integer RGPrecision() {
			return 0;
		}

		public String RGDefault() {

			return null;

		}

		public String RGComment() {

			return "";

		}

		public String RGPattern() {

			return "";

		}

		public String RGOriginalDbColumnName() {

			return "RG";

		}

		public String CalculaSUFRAMA;

		public String getCalculaSUFRAMA() {
			return this.CalculaSUFRAMA;
		}

		public Boolean CalculaSUFRAMAIsNullable() {
			return true;
		}

		public Boolean CalculaSUFRAMAIsKey() {
			return false;
		}

		public Integer CalculaSUFRAMALength() {
			return 1;
		}

		public Integer CalculaSUFRAMAPrecision() {
			return 0;
		}

		public String CalculaSUFRAMADefault() {

			return null;

		}

		public String CalculaSUFRAMAComment() {

			return "";

		}

		public String CalculaSUFRAMAPattern() {

			return "";

		}

		public String CalculaSUFRAMAOriginalDbColumnName() {

			return "CalculaSUFRAMA";

		}

		public String DataNascimento;

		public String getDataNascimento() {
			return this.DataNascimento;
		}

		public Boolean DataNascimentoIsNullable() {
			return true;
		}

		public Boolean DataNascimentoIsKey() {
			return false;
		}

		public Integer DataNascimentoLength() {
			return 8;
		}

		public Integer DataNascimentoPrecision() {
			return 0;
		}

		public String DataNascimentoDefault() {

			return null;

		}

		public String DataNascimentoComment() {

			return "";

		}

		public String DataNascimentoPattern() {

			return "";

		}

		public String DataNascimentoOriginalDbColumnName() {

			return "DataNascimento";

		}

		public Double SaldoPedidoBloqueado;

		public Double getSaldoPedidoBloqueado() {
			return this.SaldoPedidoBloqueado;
		}

		public Boolean SaldoPedidoBloqueadoIsNullable() {
			return true;
		}

		public Boolean SaldoPedidoBloqueadoIsKey() {
			return false;
		}

		public Integer SaldoPedidoBloqueadoLength() {
			return 15;
		}

		public Integer SaldoPedidoBloqueadoPrecision() {
			return 0;
		}

		public String SaldoPedidoBloqueadoDefault() {

			return "";

		}

		public String SaldoPedidoBloqueadoComment() {

			return "";

		}

		public String SaldoPedidoBloqueadoPattern() {

			return "";

		}

		public String SaldoPedidoBloqueadoOriginalDbColumnName() {

			return "SaldoPedidoBloqueado";

		}

		public String GrupoTributario;

		public String getGrupoTributario() {
			return this.GrupoTributario;
		}

		public Boolean GrupoTributarioIsNullable() {
			return true;
		}

		public Boolean GrupoTributarioIsKey() {
			return false;
		}

		public Integer GrupoTributarioLength() {
			return 3;
		}

		public Integer GrupoTributarioPrecision() {
			return 0;
		}

		public String GrupoTributarioDefault() {

			return null;

		}

		public String GrupoTributarioComment() {

			return "";

		}

		public String GrupoTributarioPattern() {

			return "";

		}

		public String GrupoTributarioOriginalDbColumnName() {

			return "GrupoTributario";

		}

		public String SegmentoAtividade1;

		public String getSegmentoAtividade1() {
			return this.SegmentoAtividade1;
		}

		public Boolean SegmentoAtividade1IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade1IsKey() {
			return false;
		}

		public Integer SegmentoAtividade1Length() {
			return 6;
		}

		public Integer SegmentoAtividade1Precision() {
			return 0;
		}

		public String SegmentoAtividade1Default() {

			return null;

		}

		public String SegmentoAtividade1Comment() {

			return "";

		}

		public String SegmentoAtividade1Pattern() {

			return "";

		}

		public String SegmentoAtividade1OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String ClienteFaturamento;

		public String getClienteFaturamento() {
			return this.ClienteFaturamento;
		}

		public Boolean ClienteFaturamentoIsNullable() {
			return true;
		}

		public Boolean ClienteFaturamentoIsKey() {
			return false;
		}

		public Integer ClienteFaturamentoLength() {
			return 9;
		}

		public Integer ClienteFaturamentoPrecision() {
			return 0;
		}

		public String ClienteFaturamentoDefault() {

			return null;

		}

		public String ClienteFaturamentoComment() {

			return "";

		}

		public String ClienteFaturamentoPattern() {

			return "";

		}

		public String ClienteFaturamentoOriginalDbColumnName() {

			return "ClienteFaturamento";

		}

		public String EnderecoEntrega;

		public String getEnderecoEntrega() {
			return this.EnderecoEntrega;
		}

		public Boolean EnderecoEntregaIsNullable() {
			return true;
		}

		public Boolean EnderecoEntregaIsKey() {
			return false;
		}

		public Integer EnderecoEntregaLength() {
			return 80;
		}

		public Integer EnderecoEntregaPrecision() {
			return 0;
		}

		public String EnderecoEntregaDefault() {

			return null;

		}

		public String EnderecoEntregaComment() {

			return "";

		}

		public String EnderecoEntregaPattern() {

			return "";

		}

		public String EnderecoEntregaOriginalDbColumnName() {

			return "EnderecoEntrega";

		}

		public String UCompleto;

		public String getUCompleto() {
			return this.UCompleto;
		}

		public Boolean UCompletoIsNullable() {
			return true;
		}

		public Boolean UCompletoIsKey() {
			return false;
		}

		public Integer UCompletoLength() {
			return 50;
		}

		public Integer UCompletoPrecision() {
			return 0;
		}

		public String UCompletoDefault() {

			return null;

		}

		public String UCompletoComment() {

			return "";

		}

		public String UCompletoPattern() {

			return "";

		}

		public String UCompletoOriginalDbColumnName() {

			return "UCompleto";

		}

		public String BairroEntrega;

		public String getBairroEntrega() {
			return this.BairroEntrega;
		}

		public Boolean BairroEntregaIsNullable() {
			return true;
		}

		public Boolean BairroEntregaIsKey() {
			return false;
		}

		public Integer BairroEntregaLength() {
			return 20;
		}

		public Integer BairroEntregaPrecision() {
			return 0;
		}

		public String BairroEntregaDefault() {

			return null;

		}

		public String BairroEntregaComment() {

			return "";

		}

		public String BairroEntregaPattern() {

			return "";

		}

		public String BairroEntregaOriginalDbColumnName() {

			return "BairroEntrega";

		}

		public String CEPEntrada;

		public String getCEPEntrada() {
			return this.CEPEntrada;
		}

		public Boolean CEPEntradaIsNullable() {
			return true;
		}

		public Boolean CEPEntradaIsKey() {
			return false;
		}

		public Integer CEPEntradaLength() {
			return 8;
		}

		public Integer CEPEntradaPrecision() {
			return 0;
		}

		public String CEPEntradaDefault() {

			return null;

		}

		public String CEPEntradaComment() {

			return "";

		}

		public String CEPEntradaPattern() {

			return "";

		}

		public String CEPEntradaOriginalDbColumnName() {

			return "CEPEntrada";

		}

		public String EstadoEntrada;

		public String getEstadoEntrada() {
			return this.EstadoEntrada;
		}

		public Boolean EstadoEntradaIsNullable() {
			return true;
		}

		public Boolean EstadoEntradaIsKey() {
			return false;
		}

		public Integer EstadoEntradaLength() {
			return 2;
		}

		public Integer EstadoEntradaPrecision() {
			return 0;
		}

		public String EstadoEntradaDefault() {

			return null;

		}

		public String EstadoEntradaComment() {

			return "";

		}

		public String EstadoEntradaPattern() {

			return "";

		}

		public String EstadoEntradaOriginalDbColumnName() {

			return "EstadoEntrada";

		}

		public String CodigoLocal;

		public String getCodigoLocal() {
			return this.CodigoLocal;
		}

		public Boolean CodigoLocalIsNullable() {
			return true;
		}

		public Boolean CodigoLocalIsKey() {
			return false;
		}

		public Integer CodigoLocalLength() {
			return 8;
		}

		public Integer CodigoLocalPrecision() {
			return 0;
		}

		public String CodigoLocalDefault() {

			return null;

		}

		public String CodigoLocalComment() {

			return "";

		}

		public String CodigoLocalPattern() {

			return "";

		}

		public String CodigoLocalOriginalDbColumnName() {

			return "CodigoLocal";

		}

		public String SegmentoAtividade2;

		public String getSegmentoAtividade2() {
			return this.SegmentoAtividade2;
		}

		public Boolean SegmentoAtividade2IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade2IsKey() {
			return false;
		}

		public Integer SegmentoAtividade2Length() {
			return 6;
		}

		public Integer SegmentoAtividade2Precision() {
			return 0;
		}

		public String SegmentoAtividade2Default() {

			return null;

		}

		public String SegmentoAtividade2Comment() {

			return "";

		}

		public String SegmentoAtividade2Pattern() {

			return "";

		}

		public String SegmentoAtividade2OriginalDbColumnName() {

			return "SegmentoAtividade2";

		}

		public String TipoPessoa;

		public String getTipoPessoa() {
			return this.TipoPessoa;
		}

		public Boolean TipoPessoaIsNullable() {
			return true;
		}

		public Boolean TipoPessoaIsKey() {
			return false;
		}

		public Integer TipoPessoaLength() {
			return 2;
		}

		public Integer TipoPessoaPrecision() {
			return 0;
		}

		public String TipoPessoaDefault() {

			return null;

		}

		public String TipoPessoaComment() {

			return "";

		}

		public String TipoPessoaPattern() {

			return "";

		}

		public String TipoPessoaOriginalDbColumnName() {

			return "TipoPessoa";

		}

		public String TipoISSRS;

		public String getTipoISSRS() {
			return this.TipoISSRS;
		}

		public Boolean TipoISSRSIsNullable() {
			return true;
		}

		public Boolean TipoISSRSIsKey() {
			return false;
		}

		public Integer TipoISSRSLength() {
			return 2;
		}

		public Integer TipoISSRSPrecision() {
			return 0;
		}

		public String TipoISSRSDefault() {

			return null;

		}

		public String TipoISSRSComment() {

			return "";

		}

		public String TipoISSRSPattern() {

			return "";

		}

		public String TipoISSRSOriginalDbColumnName() {

			return "TipoISSRS";

		}

		public String SegmentoAtividade3;

		public String getSegmentoAtividade3() {
			return this.SegmentoAtividade3;
		}

		public Boolean SegmentoAtividade3IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade3IsKey() {
			return false;
		}

		public Integer SegmentoAtividade3Length() {
			return 6;
		}

		public Integer SegmentoAtividade3Precision() {
			return 0;
		}

		public String SegmentoAtividade3Default() {

			return "";

		}

		public String SegmentoAtividade3Comment() {

			return "";

		}

		public String SegmentoAtividade3Pattern() {

			return "";

		}

		public String SegmentoAtividade3OriginalDbColumnName() {

			return "SegmentoAtividade3";

		}

		public String SegmentoAtividade4;

		public String getSegmentoAtividade4() {
			return this.SegmentoAtividade4;
		}

		public Boolean SegmentoAtividade4IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade4IsKey() {
			return false;
		}

		public Integer SegmentoAtividade4Length() {
			return 6;
		}

		public Integer SegmentoAtividade4Precision() {
			return 0;
		}

		public String SegmentoAtividade4Default() {

			return null;

		}

		public String SegmentoAtividade4Comment() {

			return "";

		}

		public String SegmentoAtividade4Pattern() {

			return "";

		}

		public String SegmentoAtividade4OriginalDbColumnName() {

			return "SegmentoAtividade4";

		}

		public String SegmentoAtividade5;

		public String getSegmentoAtividade5() {
			return this.SegmentoAtividade5;
		}

		public Boolean SegmentoAtividade5IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade5IsKey() {
			return false;
		}

		public Integer SegmentoAtividade5Length() {
			return 6;
		}

		public Integer SegmentoAtividade5Precision() {
			return 0;
		}

		public String SegmentoAtividade5Default() {

			return null;

		}

		public String SegmentoAtividade5Comment() {

			return "";

		}

		public String SegmentoAtividade5Pattern() {

			return "";

		}

		public String SegmentoAtividade5OriginalDbColumnName() {

			return "SegmentoAtividade5";

		}

		public String SegmentoAtividade6;

		public String getSegmentoAtividade6() {
			return this.SegmentoAtividade6;
		}

		public Boolean SegmentoAtividade6IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade6IsKey() {
			return false;
		}

		public Integer SegmentoAtividade6Length() {
			return 6;
		}

		public Integer SegmentoAtividade6Precision() {
			return 0;
		}

		public String SegmentoAtividade6Default() {

			return null;

		}

		public String SegmentoAtividade6Comment() {

			return "";

		}

		public String SegmentoAtividade6Pattern() {

			return "";

		}

		public String SegmentoAtividade6OriginalDbColumnName() {

			return "SegmentoAtividade6";

		}

		public String SegmentoAtividade7;

		public String getSegmentoAtividade7() {
			return this.SegmentoAtividade7;
		}

		public Boolean SegmentoAtividade7IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade7IsKey() {
			return false;
		}

		public Integer SegmentoAtividade7Length() {
			return 6;
		}

		public Integer SegmentoAtividade7Precision() {
			return 0;
		}

		public String SegmentoAtividade7Default() {

			return null;

		}

		public String SegmentoAtividade7Comment() {

			return "";

		}

		public String SegmentoAtividade7Pattern() {

			return "";

		}

		public String SegmentoAtividade7OriginalDbColumnName() {

			return "SegmentoAtividade7";

		}

		public String CodigoAgente;

		public String getCodigoAgente() {
			return this.CodigoAgente;
		}

		public Boolean CodigoAgenteIsNullable() {
			return true;
		}

		public Boolean CodigoAgenteIsKey() {
			return false;
		}

		public Integer CodigoAgenteLength() {
			return 9;
		}

		public Integer CodigoAgentePrecision() {
			return 0;
		}

		public String CodigoAgenteDefault() {

			return null;

		}

		public String CodigoAgenteComment() {

			return "";

		}

		public String CodigoAgentePattern() {

			return "";

		}

		public String CodigoAgenteOriginalDbColumnName() {

			return "CodigoAgente";

		}

		public String SegmentoAtividade8;

		public String getSegmentoAtividade8() {
			return this.SegmentoAtividade8;
		}

		public Boolean SegmentoAtividade8IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade8IsKey() {
			return false;
		}

		public Integer SegmentoAtividade8Length() {
			return 6;
		}

		public Integer SegmentoAtividade8Precision() {
			return 0;
		}

		public String SegmentoAtividade8Default() {

			return null;

		}

		public String SegmentoAtividade8Comment() {

			return "";

		}

		public String SegmentoAtividade8Pattern() {

			return "";

		}

		public String SegmentoAtividade8OriginalDbColumnName() {

			return "SegmentoAtividade8";

		}

		public String CodigoMarcacao;

		public String getCodigoMarcacao() {
			return this.CodigoMarcacao;
		}

		public Boolean CodigoMarcacaoIsNullable() {
			return true;
		}

		public Boolean CodigoMarcacaoIsKey() {
			return false;
		}

		public Integer CodigoMarcacaoLength() {
			return 6;
		}

		public Integer CodigoMarcacaoPrecision() {
			return 0;
		}

		public String CodigoMarcacaoDefault() {

			return null;

		}

		public String CodigoMarcacaoComment() {

			return "";

		}

		public String CodigoMarcacaoPattern() {

			return "";

		}

		public String CodigoMarcacaoOriginalDbColumnName() {

			return "CodigoMarcacao";

		}

		public Double ComissaoAgente;

		public Double getComissaoAgente() {
			return this.ComissaoAgente;
		}

		public Boolean ComissaoAgenteIsNullable() {
			return true;
		}

		public Boolean ComissaoAgenteIsKey() {
			return false;
		}

		public Integer ComissaoAgenteLength() {
			return 15;
		}

		public Integer ComissaoAgentePrecision() {
			return 0;
		}

		public String ComissaoAgenteDefault() {

			return "";

		}

		public String ComissaoAgenteComment() {

			return "";

		}

		public String ComissaoAgentePattern() {

			return "";

		}

		public String ComissaoAgenteOriginalDbColumnName() {

			return "ComissaoAgente";

		}

		public String HomePage;

		public String getHomePage() {
			return this.HomePage;
		}

		public Boolean HomePageIsNullable() {
			return true;
		}

		public Boolean HomePageIsKey() {
			return false;
		}

		public Integer HomePageLength() {
			return 30;
		}

		public Integer HomePagePrecision() {
			return 0;
		}

		public String HomePageDefault() {

			return null;

		}

		public String HomePageComment() {

			return "";

		}

		public String HomePagePattern() {

			return "";

		}

		public String HomePageOriginalDbColumnName() {

			return "HomePage";

		}

		public String CodigoMunicipio1;

		public String getCodigoMunicipio1() {
			return this.CodigoMunicipio1;
		}

		public Boolean CodigoMunicipio1IsNullable() {
			return true;
		}

		public Boolean CodigoMunicipio1IsKey() {
			return false;
		}

		public Integer CodigoMunicipio1Length() {
			return 5;
		}

		public Integer CodigoMunicipio1Precision() {
			return 0;
		}

		public String CodigoMunicipio1Default() {

			return null;

		}

		public String CodigoMunicipio1Comment() {

			return "";

		}

		public String CodigoMunicipio1Pattern() {

			return "";

		}

		public String CodigoMunicipio1OriginalDbColumnName() {

			return "CodigoMunicipio";

		}

		public String TipoCliente;

		public String getTipoCliente() {
			return this.TipoCliente;
		}

		public Boolean TipoClienteIsNullable() {
			return true;
		}

		public Boolean TipoClienteIsKey() {
			return false;
		}

		public Integer TipoClienteLength() {
			return 1;
		}

		public Integer TipoClientePrecision() {
			return 0;
		}

		public String TipoClienteDefault() {

			return null;

		}

		public String TipoClienteComment() {

			return "";

		}

		public String TipoClientePattern() {

			return "";

		}

		public String TipoClienteOriginalDbColumnName() {

			return "TipoCliente";

		}

		public String Email;

		public String getEmail() {
			return this.Email;
		}

		public Boolean EmailIsNullable() {
			return true;
		}

		public Boolean EmailIsKey() {
			return false;
		}

		public Integer EmailLength() {
			return 250;
		}

		public Integer EmailPrecision() {
			return 0;
		}

		public String EmailDefault() {

			return null;

		}

		public String EmailComment() {

			return "";

		}

		public String EmailPattern() {

			return "";

		}

		public String EmailOriginalDbColumnName() {

			return "Email";

		}

		public String Destino1;

		public String getDestino1() {
			return this.Destino1;
		}

		public Boolean Destino1IsNullable() {
			return true;
		}

		public Boolean Destino1IsKey() {
			return false;
		}

		public Integer Destino1Length() {
			return 3;
		}

		public Integer Destino1Precision() {
			return 0;
		}

		public String Destino1Default() {

			return null;

		}

		public String Destino1Comment() {

			return "";

		}

		public String Destino1Pattern() {

			return "";

		}

		public String Destino1OriginalDbColumnName() {

			return "Destino1";

		}

		public String Destino2;

		public String getDestino2() {
			return this.Destino2;
		}

		public Boolean Destino2IsNullable() {
			return true;
		}

		public Boolean Destino2IsKey() {
			return false;
		}

		public Integer Destino2Length() {
			return 3;
		}

		public Integer Destino2Precision() {
			return 0;
		}

		public String Destino2Default() {

			return null;

		}

		public String Destino2Comment() {

			return "";

		}

		public String Destino2Pattern() {

			return "";

		}

		public String Destino2OriginalDbColumnName() {

			return "Destino2";

		}

		public String Destino3;

		public String getDestino3() {
			return this.Destino3;
		}

		public Boolean Destino3IsNullable() {
			return true;
		}

		public Boolean Destino3IsKey() {
			return false;
		}

		public Integer Destino3Length() {
			return 3;
		}

		public Integer Destino3Precision() {
			return 0;
		}

		public String Destino3Default() {

			return null;

		}

		public String Destino3Comment() {

			return "";

		}

		public String Destino3Pattern() {

			return "";

		}

		public String Destino3OriginalDbColumnName() {

			return "Destino3";

		}

		public String CBO;

		public String getCBO() {
			return this.CBO;
		}

		public Boolean CBOIsNullable() {
			return true;
		}

		public Boolean CBOIsKey() {
			return false;
		}

		public Integer CBOLength() {
			return 7;
		}

		public Integer CBOPrecision() {
			return 0;
		}

		public String CBODefault() {

			return null;

		}

		public String CBOComment() {

			return "";

		}

		public String CBOPattern() {

			return "";

		}

		public String CBOOriginalDbColumnName() {

			return "CBO";

		}

		public String Observacao1;

		public String getObservacao1() {
			return this.Observacao1;
		}

		public Boolean Observacao1IsNullable() {
			return true;
		}

		public Boolean Observacao1IsKey() {
			return false;
		}

		public Integer Observacao1Length() {
			return 6;
		}

		public Integer Observacao1Precision() {
			return 0;
		}

		public String Observacao1Default() {

			return null;

		}

		public String Observacao1Comment() {

			return "";

		}

		public String Observacao1Pattern() {

			return "";

		}

		public String Observacao1OriginalDbColumnName() {

			return "Observacao";

		}

		public String CNAE;

		public String getCNAE() {
			return this.CNAE;
		}

		public Boolean CNAEIsNullable() {
			return true;
		}

		public Boolean CNAEIsKey() {
			return false;
		}

		public Integer CNAELength() {
			return 9;
		}

		public Integer CNAEPrecision() {
			return 0;
		}

		public String CNAEDefault() {

			return null;

		}

		public String CNAEComment() {

			return "";

		}

		public String CNAEPattern() {

			return "";

		}

		public String CNAEOriginalDbColumnName() {

			return "CNAE";

		}

		public String CodigoHistorico;

		public String getCodigoHistorico() {
			return this.CodigoHistorico;
		}

		public Boolean CodigoHistoricoIsNullable() {
			return true;
		}

		public Boolean CodigoHistoricoIsKey() {
			return false;
		}

		public Integer CodigoHistoricoLength() {
			return 6;
		}

		public Integer CodigoHistoricoPrecision() {
			return 0;
		}

		public String CodigoHistoricoDefault() {

			return null;

		}

		public String CodigoHistoricoComment() {

			return "";

		}

		public String CodigoHistoricoPattern() {

			return "";

		}

		public String CodigoHistoricoOriginalDbColumnName() {

			return "CodigoHistorico";

		}

		public String CondicaoPagamento;

		public String getCondicaoPagamento() {
			return this.CondicaoPagamento;
		}

		public Boolean CondicaoPagamentoIsNullable() {
			return true;
		}

		public Boolean CondicaoPagamentoIsKey() {
			return false;
		}

		public Integer CondicaoPagamentoLength() {
			return 5;
		}

		public Integer CondicaoPagamentoPrecision() {
			return 0;
		}

		public String CondicaoPagamentoDefault() {

			return null;

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

		public Double DiasPagamento;

		public Double getDiasPagamento() {
			return this.DiasPagamento;
		}

		public Boolean DiasPagamentoIsNullable() {
			return true;
		}

		public Boolean DiasPagamentoIsKey() {
			return false;
		}

		public Integer DiasPagamentoLength() {
			return 15;
		}

		public Integer DiasPagamentoPrecision() {
			return 0;
		}

		public String DiasPagamentoDefault() {

			return "";

		}

		public String DiasPagamentoComment() {

			return "";

		}

		public String DiasPagamentoPattern() {

			return "";

		}

		public String DiasPagamentoOriginalDbColumnName() {

			return "DiasPagamento";

		}

		public String Agregado;

		public String getAgregado() {
			return this.Agregado;
		}

		public Boolean AgregadoIsNullable() {
			return true;
		}

		public Boolean AgregadoIsKey() {
			return false;
		}

		public Integer AgregadoLength() {
			return 4;
		}

		public Integer AgregadoPrecision() {
			return 0;
		}

		public String AgregadoDefault() {

			return null;

		}

		public String AgregadoComment() {

			return "";

		}

		public String AgregadoPattern() {

			return "";

		}

		public String AgregadoOriginalDbColumnName() {

			return "Agregado";

		}

		public String RecolheINSS;

		public String getRecolheINSS() {
			return this.RecolheINSS;
		}

		public Boolean RecolheINSSIsNullable() {
			return true;
		}

		public Boolean RecolheINSSIsKey() {
			return false;
		}

		public Integer RecolheINSSLength() {
			return 1;
		}

		public Integer RecolheINSSPrecision() {
			return 0;
		}

		public String RecolheINSSDefault() {

			return null;

		}

		public String RecolheINSSComment() {

			return "";

		}

		public String RecolheINSSPattern() {

			return "";

		}

		public String RecolheINSSOriginalDbColumnName() {

			return "RecolheINSS";

		}

		public String RecolheCOFINS;

		public String getRecolheCOFINS() {
			return this.RecolheCOFINS;
		}

		public Boolean RecolheCOFINSIsNullable() {
			return true;
		}

		public Boolean RecolheCOFINSIsKey() {
			return false;
		}

		public Integer RecolheCOFINSLength() {
			return 1;
		}

		public Integer RecolheCOFINSPrecision() {
			return 0;
		}

		public String RecolheCOFINSDefault() {

			return null;

		}

		public String RecolheCOFINSComment() {

			return "";

		}

		public String RecolheCOFINSPattern() {

			return "";

		}

		public String RecolheCOFINSOriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL;

		public String getRecolheCSLL() {
			return this.RecolheCSLL;
		}

		public Boolean RecolheCSLLIsNullable() {
			return true;
		}

		public Boolean RecolheCSLLIsKey() {
			return false;
		}

		public Integer RecolheCSLLLength() {
			return 1;
		}

		public Integer RecolheCSLLPrecision() {
			return 0;
		}

		public String RecolheCSLLDefault() {

			return null;

		}

		public String RecolheCSLLComment() {

			return "";

		}

		public String RecolheCSLLPattern() {

			return "";

		}

		public String RecolheCSLLOriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String RecolhePIS;

		public String getRecolhePIS() {
			return this.RecolhePIS;
		}

		public Boolean RecolhePISIsNullable() {
			return true;
		}

		public Boolean RecolhePISIsKey() {
			return false;
		}

		public Integer RecolhePISLength() {
			return 1;
		}

		public Integer RecolhePISPrecision() {
			return 0;
		}

		public String RecolhePISDefault() {

			return null;

		}

		public String RecolhePISComment() {

			return "";

		}

		public String RecolhePISPattern() {

			return "";

		}

		public String RecolhePISOriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String TipoPeriodo;

		public String getTipoPeriodo() {
			return this.TipoPeriodo;
		}

		public Boolean TipoPeriodoIsNullable() {
			return true;
		}

		public Boolean TipoPeriodoIsKey() {
			return false;
		}

		public Integer TipoPeriodoLength() {
			return 2;
		}

		public Integer TipoPeriodoPrecision() {
			return 0;
		}

		public String TipoPeriodoDefault() {

			return null;

		}

		public String TipoPeriodoComment() {

			return "";

		}

		public String TipoPeriodoPattern() {

			return "";

		}

		public String TipoPeriodoOriginalDbColumnName() {

			return "TipoPeriodo";

		}

		public Double SaldoFinal;

		public Double getSaldoFinal() {
			return this.SaldoFinal;
		}

		public Boolean SaldoFinalIsNullable() {
			return true;
		}

		public Boolean SaldoFinalIsKey() {
			return false;
		}

		public Integer SaldoFinalLength() {
			return 15;
		}

		public Integer SaldoFinalPrecision() {
			return 0;
		}

		public String SaldoFinalDefault() {

			return "";

		}

		public String SaldoFinalComment() {

			return "";

		}

		public String SaldoFinalPattern() {

			return "";

		}

		public String SaldoFinalOriginalDbColumnName() {

			return "SaldoFinal";

		}

		public Double SaldoFinalMoeda;

		public Double getSaldoFinalMoeda() {
			return this.SaldoFinalMoeda;
		}

		public Boolean SaldoFinalMoedaIsNullable() {
			return true;
		}

		public Boolean SaldoFinalMoedaIsKey() {
			return false;
		}

		public Integer SaldoFinalMoedaLength() {
			return 15;
		}

		public Integer SaldoFinalMoedaPrecision() {
			return 0;
		}

		public String SaldoFinalMoedaDefault() {

			return "";

		}

		public String SaldoFinalMoedaComment() {

			return "";

		}

		public String SaldoFinalMoedaPattern() {

			return "";

		}

		public String SaldoFinalMoedaOriginalDbColumnName() {

			return "SaldoFinalMoeda";

		}

		public String Contabilidade;

		public String getContabilidade() {
			return this.Contabilidade;
		}

		public Boolean ContabilidadeIsNullable() {
			return true;
		}

		public Boolean ContabilidadeIsKey() {
			return false;
		}

		public Integer ContabilidadeLength() {
			return 15;
		}

		public Integer ContabilidadePrecision() {
			return 0;
		}

		public String ContabilidadeDefault() {

			return null;

		}

		public String ContabilidadeComment() {

			return "";

		}

		public String ContabilidadePattern() {

			return "";

		}

		public String ContabilidadeOriginalDbColumnName() {

			return "Contabilidade";

		}

		public String ClienteConvenio;

		public String getClienteConvenio() {
			return this.ClienteConvenio;
		}

		public Boolean ClienteConvenioIsNullable() {
			return true;
		}

		public Boolean ClienteConvenioIsKey() {
			return false;
		}

		public Integer ClienteConvenioLength() {
			return 1;
		}

		public Integer ClienteConvenioPrecision() {
			return 0;
		}

		public String ClienteConvenioDefault() {

			return null;

		}

		public String ClienteConvenioComment() {

			return "";

		}

		public String ClienteConvenioPattern() {

			return "";

		}

		public String ClienteConvenioOriginalDbColumnName() {

			return "ClienteConvenio";

		}

		public String B2B;

		public String getB2B() {
			return this.B2B;
		}

		public Boolean B2BIsNullable() {
			return true;
		}

		public Boolean B2BIsKey() {
			return false;
		}

		public Integer B2BLength() {
			return 1;
		}

		public Integer B2BPrecision() {
			return 0;
		}

		public String B2BDefault() {

			return null;

		}

		public String B2BComment() {

			return "";

		}

		public String B2BPattern() {

			return "";

		}

		public String B2BOriginalDbColumnName() {

			return "B2B";

		}

		public String CBO1;

		public String getCBO1() {
			return this.CBO1;
		}

		public Boolean CBO1IsNullable() {
			return true;
		}

		public Boolean CBO1IsKey() {
			return false;
		}

		public Integer CBO1Length() {
			return 7;
		}

		public Integer CBO1Precision() {
			return 0;
		}

		public String CBO1Default() {

			return null;

		}

		public String CBO1Comment() {

			return "";

		}

		public String CBO1Pattern() {

			return "";

		}

		public String CBO1OriginalDbColumnName() {

			return "CBO";

		}

		public String CNAE1;

		public String getCNAE1() {
			return this.CNAE1;
		}

		public Boolean CNAE1IsNullable() {
			return true;
		}

		public Boolean CNAE1IsKey() {
			return false;
		}

		public Integer CNAE1Length() {
			return 9;
		}

		public Integer CNAE1Precision() {
			return 0;
		}

		public String CNAE1Default() {

			return null;

		}

		public String CNAE1Comment() {

			return "";

		}

		public String CNAE1Pattern() {

			return "";

		}

		public String CNAE1OriginalDbColumnName() {

			return "CNAE";

		}

		public String SegmentoAtividade11;

		public String getSegmentoAtividade11() {
			return this.SegmentoAtividade11;
		}

		public Boolean SegmentoAtividade11IsNullable() {
			return true;
		}

		public Boolean SegmentoAtividade11IsKey() {
			return false;
		}

		public Integer SegmentoAtividade11Length() {
			return 6;
		}

		public Integer SegmentoAtividade11Precision() {
			return 0;
		}

		public String SegmentoAtividade11Default() {

			return null;

		}

		public String SegmentoAtividade11Comment() {

			return "";

		}

		public String SegmentoAtividade11Pattern() {

			return "";

		}

		public String SegmentoAtividade11OriginalDbColumnName() {

			return "SegmentoAtividade1";

		}

		public String MensagemBloqueio;

		public String getMensagemBloqueio() {
			return this.MensagemBloqueio;
		}

		public Boolean MensagemBloqueioIsNullable() {
			return true;
		}

		public Boolean MensagemBloqueioIsKey() {
			return false;
		}

		public Integer MensagemBloqueioLength() {
			return 1;
		}

		public Integer MensagemBloqueioPrecision() {
			return 0;
		}

		public String MensagemBloqueioDefault() {

			return null;

		}

		public String MensagemBloqueioComment() {

			return "";

		}

		public String MensagemBloqueioPattern() {

			return "";

		}

		public String MensagemBloqueioOriginalDbColumnName() {

			return "MensagemBloqueio";

		}

		public String SubCodigo;

		public String getSubCodigo() {
			return this.SubCodigo;
		}

		public Boolean SubCodigoIsNullable() {
			return true;
		}

		public Boolean SubCodigoIsKey() {
			return false;
		}

		public Integer SubCodigoLength() {
			return 1;
		}

		public Integer SubCodigoPrecision() {
			return 0;
		}

		public String SubCodigoDefault() {

			return null;

		}

		public String SubCodigoComment() {

			return "";

		}

		public String SubCodigoPattern() {

			return "";

		}

		public String SubCodigoOriginalDbColumnName() {

			return "SubCodigo";

		}

		public String FilialDebito;

		public String getFilialDebito() {
			return this.FilialDebito;
		}

		public Boolean FilialDebitoIsNullable() {
			return true;
		}

		public Boolean FilialDebitoIsKey() {
			return false;
		}

		public Integer FilialDebitoLength() {
			return 2;
		}

		public Integer FilialDebitoPrecision() {
			return 0;
		}

		public String FilialDebitoDefault() {

			return null;

		}

		public String FilialDebitoComment() {

			return "";

		}

		public String FilialDebitoPattern() {

			return "";

		}

		public String FilialDebitoOriginalDbColumnName() {

			return "FilialDebito";

		}

		public String RecolhePIS1;

		public String getRecolhePIS1() {
			return this.RecolhePIS1;
		}

		public Boolean RecolhePIS1IsNullable() {
			return true;
		}

		public Boolean RecolhePIS1IsKey() {
			return false;
		}

		public Integer RecolhePIS1Length() {
			return 1;
		}

		public Integer RecolhePIS1Precision() {
			return 0;
		}

		public String RecolhePIS1Default() {

			return null;

		}

		public String RecolhePIS1Comment() {

			return "";

		}

		public String RecolhePIS1Pattern() {

			return "";

		}

		public String RecolhePIS1OriginalDbColumnName() {

			return "RecolhePIS";

		}

		public String RecolheCOFINS1;

		public String getRecolheCOFINS1() {
			return this.RecolheCOFINS1;
		}

		public Boolean RecolheCOFINS1IsNullable() {
			return true;
		}

		public Boolean RecolheCOFINS1IsKey() {
			return false;
		}

		public Integer RecolheCOFINS1Length() {
			return 1;
		}

		public Integer RecolheCOFINS1Precision() {
			return 0;
		}

		public String RecolheCOFINS1Default() {

			return null;

		}

		public String RecolheCOFINS1Comment() {

			return "";

		}

		public String RecolheCOFINS1Pattern() {

			return "";

		}

		public String RecolheCOFINS1OriginalDbColumnName() {

			return "RecolheCOFINS";

		}

		public String RecolheCSLL1;

		public String getRecolheCSLL1() {
			return this.RecolheCSLL1;
		}

		public Boolean RecolheCSLL1IsNullable() {
			return true;
		}

		public Boolean RecolheCSLL1IsKey() {
			return false;
		}

		public Integer RecolheCSLL1Length() {
			return 1;
		}

		public Integer RecolheCSLL1Precision() {
			return 0;
		}

		public String RecolheCSLL1Default() {

			return null;

		}

		public String RecolheCSLL1Comment() {

			return "";

		}

		public String RecolheCSLL1Pattern() {

			return "";

		}

		public String RecolheCSLL1OriginalDbColumnName() {

			return "RecolheCSLL";

		}

		public String ABICS;

		public String getABICS() {
			return this.ABICS;
		}

		public Boolean ABICSIsNullable() {
			return true;
		}

		public Boolean ABICSIsKey() {
			return false;
		}

		public Integer ABICSLength() {
			return 4;
		}

		public Integer ABICSPrecision() {
			return 0;
		}

		public String ABICSDefault() {

			return null;

		}

		public String ABICSComment() {

			return "";

		}

		public String ABICSPattern() {

			return "";

		}

		public String ABICSOriginalDbColumnName() {

			return "ABICS";

		}

		public String Vinculo;

		public String getVinculo() {
			return this.Vinculo;
		}

		public Boolean VinculoIsNullable() {
			return true;
		}

		public Boolean VinculoIsKey() {
			return false;
		}

		public Integer VinculoLength() {
			return 2;
		}

		public Integer VinculoPrecision() {
			return 0;
		}

		public String VinculoDefault() {

			return null;

		}

		public String VinculoComment() {

			return "";

		}

		public String VinculoPattern() {

			return "";

		}

		public String VinculoOriginalDbColumnName() {

			return "Vinculo";

		}

		public String DataInicioVinculo;

		public String getDataInicioVinculo() {
			return this.DataInicioVinculo;
		}

		public Boolean DataInicioVinculoIsNullable() {
			return true;
		}

		public Boolean DataInicioVinculoIsKey() {
			return false;
		}

		public Integer DataInicioVinculoLength() {
			return 8;
		}

		public Integer DataInicioVinculoPrecision() {
			return 0;
		}

		public String DataInicioVinculoDefault() {

			return null;

		}

		public String DataInicioVinculoComment() {

			return "";

		}

		public String DataInicioVinculoPattern() {

			return "";

		}

		public String DataInicioVinculoOriginalDbColumnName() {

			return "DataInicioVinculo";

		}

		public String DataFimVinculo;

		public String getDataFimVinculo() {
			return this.DataFimVinculo;
		}

		public Boolean DataFimVinculoIsNullable() {
			return true;
		}

		public Boolean DataFimVinculoIsKey() {
			return false;
		}

		public Integer DataFimVinculoLength() {
			return 8;
		}

		public Integer DataFimVinculoPrecision() {
			return 0;
		}

		public String DataFimVinculoDefault() {

			return null;

		}

		public String DataFimVinculoComment() {

			return "";

		}

		public String DataFimVinculoPattern() {

			return "";

		}

		public String DataFimVinculoOriginalDbColumnName() {

			return "DataFimVinculo";

		}

		public String ISSRSLC;

		public String getISSRSLC() {
			return this.ISSRSLC;
		}

		public Boolean ISSRSLCIsNullable() {
			return true;
		}

		public Boolean ISSRSLCIsKey() {
			return false;
		}

		public Integer ISSRSLCLength() {
			return 1;
		}

		public Integer ISSRSLCPrecision() {
			return 0;
		}

		public String ISSRSLCDefault() {

			return null;

		}

		public String ISSRSLCComment() {

			return "";

		}

		public String ISSRSLCPattern() {

			return "";

		}

		public String ISSRSLCOriginalDbColumnName() {

			return "ISSRSLC";

		}

		public String TipoRegistro;

		public String getTipoRegistro() {
			return this.TipoRegistro;
		}

		public Boolean TipoRegistroIsNullable() {
			return true;
		}

		public Boolean TipoRegistroIsKey() {
			return false;
		}

		public Integer TipoRegistroLength() {
			return 1;
		}

		public Integer TipoRegistroPrecision() {
			return 0;
		}

		public String TipoRegistroDefault() {

			return null;

		}

		public String TipoRegistroComment() {

			return "";

		}

		public String TipoRegistroPattern() {

			return "";

		}

		public String TipoRegistroOriginalDbColumnName() {

			return "TipoRegistro";

		}

		public String RFASEMT;

		public String getRFASEMT() {
			return this.RFASEMT;
		}

		public Boolean RFASEMTIsNullable() {
			return true;
		}

		public Boolean RFASEMTIsKey() {
			return false;
		}

		public Integer RFASEMTLength() {
			return 1;
		}

		public Integer RFASEMTPrecision() {
			return 0;
		}

		public String RFASEMTDefault() {

			return null;

		}

		public String RFASEMTComment() {

			return "";

		}

		public String RFASEMTPattern() {

			return "";

		}

		public String RFASEMTOriginalDbColumnName() {

			return "RFASEMT";

		}

		public String RIMAMT;

		public String getRIMAMT() {
			return this.RIMAMT;
		}

		public Boolean RIMAMTIsNullable() {
			return true;
		}

		public Boolean RIMAMTIsKey() {
			return false;
		}

		public Integer RIMAMTLength() {
			return 1;
		}

		public Integer RIMAMTPrecision() {
			return 0;
		}

		public String RIMAMTDefault() {

			return null;

		}

		public String RIMAMTComment() {

			return "";

		}

		public String RIMAMTPattern() {

			return "";

		}

		public String RIMAMTOriginalDbColumnName() {

			return "RIMAMT";

		}

		public String REGESIM;

		public String getREGESIM() {
			return this.REGESIM;
		}

		public Boolean REGESIMIsNullable() {
			return true;
		}

		public Boolean REGESIMIsKey() {
			return false;
		}

		public Integer REGESIMLength() {
			return 1;
		}

		public Integer REGESIMPrecision() {
			return 0;
		}

		public String REGESIMDefault() {

			return null;

		}

		public String REGESIMComment() {

			return "";

		}

		public String REGESIMPattern() {

			return "";

		}

		public String REGESIMOriginalDbColumnName() {

			return "REGESIM";

		}

		public String ContaReceita;

		public String getContaReceita() {
			return this.ContaReceita;
		}

		public Boolean ContaReceitaIsNullable() {
			return true;
		}

		public Boolean ContaReceitaIsKey() {
			return false;
		}

		public Integer ContaReceitaLength() {
			return 1;
		}

		public Integer ContaReceitaPrecision() {
			return 0;
		}

		public String ContaReceitaDefault() {

			return null;

		}

		public String ContaReceitaComment() {

			return "";

		}

		public String ContaReceitaPattern() {

			return "";

		}

		public String ContaReceitaOriginalDbColumnName() {

			return "ContaReceita";

		}

		public String SIMPNacional;

		public String getSIMPNacional() {
			return this.SIMPNacional;
		}

		public Boolean SIMPNacionalIsNullable() {
			return true;
		}

		public Boolean SIMPNacionalIsKey() {
			return false;
		}

		public Integer SIMPNacionalLength() {
			return 1;
		}

		public Integer SIMPNacionalPrecision() {
			return 0;
		}

		public String SIMPNacionalDefault() {

			return null;

		}

		public String SIMPNacionalComment() {

			return "";

		}

		public String SIMPNacionalPattern() {

			return "";

		}

		public String SIMPNacionalOriginalDbColumnName() {

			return "SIMPNacional";

		}

		public String RecolheFETHAB;

		public String getRecolheFETHAB() {
			return this.RecolheFETHAB;
		}

		public Boolean RecolheFETHABIsNullable() {
			return true;
		}

		public Boolean RecolheFETHABIsKey() {
			return false;
		}

		public Integer RecolheFETHABLength() {
			return 1;
		}

		public Integer RecolheFETHABPrecision() {
			return 0;
		}

		public String RecolheFETHABDefault() {

			return null;

		}

		public String RecolheFETHABComment() {

			return "";

		}

		public String RecolheFETHABPattern() {

			return "";

		}

		public String RecolheFETHABOriginalDbColumnName() {

			return "RecolheFETHAB";

		}

		public String RFABOV;

		public String getRFABOV() {
			return this.RFABOV;
		}

		public Boolean RFABOVIsNullable() {
			return true;
		}

		public Boolean RFABOVIsKey() {
			return false;
		}

		public Integer RFABOVLength() {
			return 1;
		}

		public Integer RFABOVPrecision() {
			return 0;
		}

		public String RFABOVDefault() {

			return null;

		}

		public String RFABOVComment() {

			return "";

		}

		public String RFABOVPattern() {

			return "";

		}

		public String RFABOVOriginalDbColumnName() {

			return "RFABOV";

		}

		public String RFACS;

		public String getRFACS() {
			return this.RFACS;
		}

		public Boolean RFACSIsNullable() {
			return true;
		}

		public Boolean RFACSIsKey() {
			return false;
		}

		public Integer RFACSLength() {
			return 1;
		}

		public Integer RFACSPrecision() {
			return 0;
		}

		public String RFACSDefault() {

			return null;

		}

		public String RFACSComment() {

			return "";

		}

		public String RFACSPattern() {

			return "";

		}

		public String RFACSOriginalDbColumnName() {

			return "RFACS";

		}

		public String DataNascimento1;

		public String getDataNascimento1() {
			return this.DataNascimento1;
		}

		public Boolean DataNascimento1IsNullable() {
			return true;
		}

		public Boolean DataNascimento1IsKey() {
			return false;
		}

		public Integer DataNascimento1Length() {
			return 8;
		}

		public Integer DataNascimento1Precision() {
			return 0;
		}

		public String DataNascimento1Default() {

			return null;

		}

		public String DataNascimento1Comment() {

			return "";

		}

		public String DataNascimento1Pattern() {

			return "";

		}

		public String DataNascimento1OriginalDbColumnName() {

			return "DataNascimento";

		}

		public String Contribuinte;

		public String getContribuinte() {
			return this.Contribuinte;
		}

		public Boolean ContribuinteIsNullable() {
			return true;
		}

		public Boolean ContribuinteIsKey() {
			return false;
		}

		public Integer ContribuinteLength() {
			return 1;
		}

		public Integer ContribuintePrecision() {
			return 0;
		}

		public String ContribuinteDefault() {

			return null;

		}

		public String ContribuinteComment() {

			return "";

		}

		public String ContribuintePattern() {

			return "";

		}

		public String ContribuinteOriginalDbColumnName() {

			return "Contribuinte";

		}

		public String RecolheFMD;

		public String getRecolheFMD() {
			return this.RecolheFMD;
		}

		public Boolean RecolheFMDIsNullable() {
			return true;
		}

		public Boolean RecolheFMDIsKey() {
			return false;
		}

		public Integer RecolheFMDLength() {
			return 1;
		}

		public Integer RecolheFMDPrecision() {
			return 0;
		}

		public String RecolheFMDDefault() {

			return null;

		}

		public String RecolheFMDComment() {

			return "";

		}

		public String RecolheFMDPattern() {

			return "";

		}

		public String RecolheFMDOriginalDbColumnName() {

			return "RecolheFMD";

		}

		public String TipoJuridico;

		public String getTipoJuridico() {
			return this.TipoJuridico;
		}

		public Boolean TipoJuridicoIsNullable() {
			return true;
		}

		public Boolean TipoJuridicoIsKey() {
			return false;
		}

		public Integer TipoJuridicoLength() {
			return 1;
		}

		public Integer TipoJuridicoPrecision() {
			return 0;
		}

		public String TipoJuridicoDefault() {

			return null;

		}

		public String TipoJuridicoComment() {

			return "";

		}

		public String TipoJuridicoPattern() {

			return "";

		}

		public String TipoJuridicoOriginalDbColumnName() {

			return "TipoJuridico";

		}

		public String INCULT;

		public String getINCULT() {
			return this.INCULT;
		}

		public Boolean INCULTIsNullable() {
			return true;
		}

		public Boolean INCULTIsKey() {
			return false;
		}

		public Integer INCULTLength() {
			return 1;
		}

		public Integer INCULTPrecision() {
			return 0;
		}

		public String INCULTDefault() {

			return null;

		}

		public String INCULTComment() {

			return "";

		}

		public String INCULTPattern() {

			return "";

		}

		public String INCULTOriginalDbColumnName() {

			return "INCULT";

		}

		public String IDHistorico;

		public String getIDHistorico() {
			return this.IDHistorico;
		}

		public Boolean IDHistoricoIsNullable() {
			return true;
		}

		public Boolean IDHistoricoIsKey() {
			return false;
		}

		public Integer IDHistoricoLength() {
			return 20;
		}

		public Integer IDHistoricoPrecision() {
			return 0;
		}

		public String IDHistoricoDefault() {

			return null;

		}

		public String IDHistoricoComment() {

			return "";

		}

		public String IDHistoricoPattern() {

			return "";

		}

		public String IDHistoricoOriginalDbColumnName() {

			return "IDHistorico";

		}

		public String PrestacaoServico;

		public String getPrestacaoServico() {
			return this.PrestacaoServico;
		}

		public Boolean PrestacaoServicoIsNullable() {
			return true;
		}

		public Boolean PrestacaoServicoIsKey() {
			return false;
		}

		public Integer PrestacaoServicoLength() {
			return 1;
		}

		public Integer PrestacaoServicoPrecision() {
			return 0;
		}

		public String PrestacaoServicoDefault() {

			return null;

		}

		public String PrestacaoServicoComment() {

			return "";

		}

		public String PrestacaoServicoPattern() {

			return "";

		}

		public String PrestacaoServicoOriginalDbColumnName() {

			return "PrestacaoServico";

		}

		public String NUMRA;

		public String getNUMRA() {
			return this.NUMRA;
		}

		public Boolean NUMRAIsNullable() {
			return true;
		}

		public Boolean NUMRAIsKey() {
			return false;
		}

		public Integer NUMRALength() {
			return 15;
		}

		public Integer NUMRAPrecision() {
			return 0;
		}

		public String NUMRADefault() {

			return null;

		}

		public String NUMRAComment() {

			return "";

		}

		public String NUMRAPattern() {

			return "";

		}

		public String NUMRAOriginalDbColumnName() {

			return "NUMRA";

		}

		public String MINIRF;

		public String getMINIRF() {
			return this.MINIRF;
		}

		public Boolean MINIRFIsNullable() {
			return true;
		}

		public Boolean MINIRFIsKey() {
			return false;
		}

		public Integer MINIRFLength() {
			return 1;
		}

		public Integer MINIRFPrecision() {
			return 0;
		}

		public String MINIRFDefault() {

			return null;

		}

		public String MINIRFComment() {

			return "";

		}

		public String MINIRFPattern() {

			return "";

		}

		public String MINIRFOriginalDbColumnName() {

			return "MINIRF";

		}

		public String CODSIAF;

		public String getCODSIAF() {
			return this.CODSIAF;
		}

		public Boolean CODSIAFIsNullable() {
			return true;
		}

		public Boolean CODSIAFIsKey() {
			return false;
		}

		public Integer CODSIAFLength() {
			return 4;
		}

		public Integer CODSIAFPrecision() {
			return 0;
		}

		public String CODSIAFDefault() {

			return null;

		}

		public String CODSIAFComment() {

			return "";

		}

		public String CODSIAFPattern() {

			return "";

		}

		public String CODSIAFOriginalDbColumnName() {

			return "CODSIAF";

		}

		public String ENDNOT;

		public String getENDNOT() {
			return this.ENDNOT;
		}

		public Boolean ENDNOTIsNullable() {
			return true;
		}

		public Boolean ENDNOTIsKey() {
			return false;
		}

		public Integer ENDNOTLength() {
			return 1;
		}

		public Integer ENDNOTPrecision() {
			return 0;
		}

		public String ENDNOTDefault() {

			return null;

		}

		public String ENDNOTComment() {

			return "";

		}

		public String ENDNOTPattern() {

			return "";

		}

		public String ENDNOTOriginalDbColumnName() {

			return "ENDNOT";

		}

		public String FOMEZER;

		public String getFOMEZER() {
			return this.FOMEZER;
		}

		public Boolean FOMEZERIsNullable() {
			return true;
		}

		public Boolean FOMEZERIsKey() {
			return false;
		}

		public Integer FOMEZERLength() {
			return 1;
		}

		public Integer FOMEZERPrecision() {
			return 0;
		}

		public String FOMEZERDefault() {

			return null;

		}

		public String FOMEZERComment() {

			return "";

		}

		public String FOMEZERPattern() {

			return "";

		}

		public String FOMEZEROriginalDbColumnName() {

			return "FOMEZER";

		}

		public String FRETISS;

		public String getFRETISS() {
			return this.FRETISS;
		}

		public Boolean FRETISSIsNullable() {
			return true;
		}

		public Boolean FRETISSIsKey() {
			return false;
		}

		public Integer FRETISSLength() {
			return 1;
		}

		public Integer FRETISSPrecision() {
			return 0;
		}

		public String FRETISSDefault() {

			return null;

		}

		public String FRETISSComment() {

			return "";

		}

		public String FRETISSPattern() {

			return "";

		}

		public String FRETISSOriginalDbColumnName() {

			return "FRETISS";

		}

		public String COMPLEM;

		public String getCOMPLEM() {
			return this.COMPLEM;
		}

		public Boolean COMPLEMIsNullable() {
			return true;
		}

		public Boolean COMPLEMIsKey() {
			return false;
		}

		public Integer COMPLEMLength() {
			return 50;
		}

		public Integer COMPLEMPrecision() {
			return 0;
		}

		public String COMPLEMDefault() {

			return null;

		}

		public String COMPLEMComment() {

			return "";

		}

		public String COMPLEMPattern() {

			return "";

		}

		public String COMPLEMOriginalDbColumnName() {

			return "COMPLEM";

		}

		public String INCLTMG;

		public String getINCLTMG() {
			return this.INCLTMG;
		}

		public Boolean INCLTMGIsNullable() {
			return true;
		}

		public Boolean INCLTMGIsKey() {
			return false;
		}

		public Integer INCLTMGLength() {
			return 1;
		}

		public Integer INCLTMGPrecision() {
			return 0;
		}

		public String INCLTMGDefault() {

			return null;

		}

		public String INCLTMGComment() {

			return "";

		}

		public String INCLTMGPattern() {

			return "";

		}

		public String INCLTMGOriginalDbColumnName() {

			return "INCLTMG";

		}

		public String FILTRF;

		public String getFILTRF() {
			return this.FILTRF;
		}

		public Boolean FILTRFIsNullable() {
			return true;
		}

		public Boolean FILTRFIsKey() {
			return false;
		}

		public Integer FILTRFLength() {
			return 2;
		}

		public Integer FILTRFPrecision() {
			return 0;
		}

		public String FILTRFDefault() {

			return null;

		}

		public String FILTRFComment() {

			return "";

		}

		public String FILTRFPattern() {

			return "";

		}

		public String FILTRFOriginalDbColumnName() {

			return "FILTRF";

		}

		public String TRIBFAV;

		public String getTRIBFAV() {
			return this.TRIBFAV;
		}

		public Boolean TRIBFAVIsNullable() {
			return true;
		}

		public Boolean TRIBFAVIsKey() {
			return false;
		}

		public Integer TRIBFAVLength() {
			return 1;
		}

		public Integer TRIBFAVPrecision() {
			return 0;
		}

		public String TRIBFAVDefault() {

			return null;

		}

		public String TRIBFAVComment() {

			return "";

		}

		public String TRIBFAVPattern() {

			return "";

		}

		public String TRIBFAVOriginalDbColumnName() {

			return "TRIBFAV";

		}

		public String REGPB;

		public String getREGPB() {
			return this.REGPB;
		}

		public Boolean REGPBIsNullable() {
			return true;
		}

		public Boolean REGPBIsKey() {
			return false;
		}

		public Integer REGPBLength() {
			return 1;
		}

		public Integer REGPBPrecision() {
			return 0;
		}

		public String REGPBDefault() {

			return null;

		}

		public String REGPBComment() {

			return "";

		}

		public String REGPBPattern() {

			return "";

		}

		public String REGPBOriginalDbColumnName() {

			return "REGPB";

		}

		public String INOVAUT;

		public String getINOVAUT() {
			return this.INOVAUT;
		}

		public Boolean INOVAUTIsNullable() {
			return true;
		}

		public Boolean INOVAUTIsKey() {
			return false;
		}

		public Integer INOVAUTLength() {
			return 1;
		}

		public Integer INOVAUTPrecision() {
			return 0;
		}

		public String INOVAUTDefault() {

			return null;

		}

		public String INOVAUTComment() {

			return "";

		}

		public String INOVAUTPattern() {

			return "";

		}

		public String INOVAUTOriginalDbColumnName() {

			return "INOVAUT";

		}

		public String PrazoMedio;

		public String getPrazoMedio() {
			return this.PrazoMedio;
		}

		public Boolean PrazoMedioIsNullable() {
			return true;
		}

		public Boolean PrazoMedioIsKey() {
			return false;
		}

		public Integer PrazoMedioLength() {
			return 3;
		}

		public Integer PrazoMedioPrecision() {
			return 0;
		}

		public String PrazoMedioDefault() {

			return null;

		}

		public String PrazoMedioComment() {

			return "";

		}

		public String PrazoMedioPattern() {

			return "";

		}

		public String PrazoMedioOriginalDbColumnName() {

			return "PrazoMedio";

		}

		public String CodigoCanal;

		public String getCodigoCanal() {
			return this.CodigoCanal;
		}

		public Boolean CodigoCanalIsNullable() {
			return true;
		}

		public Boolean CodigoCanalIsKey() {
			return false;
		}

		public Integer CodigoCanalLength() {
			return 6;
		}

		public Integer CodigoCanalPrecision() {
			return 0;
		}

		public String CodigoCanalDefault() {

			return null;

		}

		public String CodigoCanalComment() {

			return "";

		}

		public String CodigoCanalPattern() {

			return "";

		}

		public String CodigoCanalOriginalDbColumnName() {

			return "CodigoCanal";

		}

		public String CodigoSegmento;

		public String getCodigoSegmento() {
			return this.CodigoSegmento;
		}

		public Boolean CodigoSegmentoIsNullable() {
			return true;
		}

		public Boolean CodigoSegmentoIsKey() {
			return false;
		}

		public Integer CodigoSegmentoLength() {
			return 6;
		}

		public Integer CodigoSegmentoPrecision() {
			return 0;
		}

		public String CodigoSegmentoDefault() {

			return null;

		}

		public String CodigoSegmentoComment() {

			return "";

		}

		public String CodigoSegmentoPattern() {

			return "";

		}

		public String CodigoSegmentoOriginalDbColumnName() {

			return "CodigoSegmento";

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

		public String Ukey;

		public String getUkey() {
			return this.Ukey;
		}

		public Boolean UkeyIsNullable() {
			return true;
		}

		public Boolean UkeyIsKey() {
			return true;
		}

		public Integer UkeyLength() {
			return 10;
		}

		public Integer UkeyPrecision() {
			return 0;
		}

		public String UkeyDefault() {

			return null;

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

		public Integer RecDeletado;

		public Integer getRecDeletado() {
			return this.RecDeletado;
		}

		public Boolean RecDeletadoIsNullable() {
			return true;
		}

		public Boolean RecDeletadoIsKey() {
			return false;
		}

		public Integer RecDeletadoLength() {
			return 10;
		}

		public Integer RecDeletadoPrecision() {
			return 0;
		}

		public String RecDeletadoDefault() {

			return "";

		}

		public String RecDeletadoComment() {

			return "";

		}

		public String RecDeletadoPattern() {

			return "";

		}

		public String RecDeletadoOriginalDbColumnName() {

			return "RecDeletado";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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
				if (length > commonByteArray_HYDRONORTH_Clientes.length) {
					if (length < 1024 && commonByteArray_HYDRONORTH_Clientes.length == 0) {
						commonByteArray_HYDRONORTH_Clientes = new byte[1024];
					} else {
						commonByteArray_HYDRONORTH_Clientes = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_HYDRONORTH_Clientes, 0, length);
				strReturn = new String(commonByteArray_HYDRONORTH_Clientes, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readString(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readString(dis);

					this.UltimaCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.PrazoMedio = readString(dis);

					this.CodigoCanal = readString(dis);

					this.CodigoSegmento = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_HYDRONORTH_Clientes) {

				try {

					int length = 0;

					this.Filial = readString(dis);

					this.Codigo = readString(dis);

					this.Loja = readString(dis);

					this.Tipo = readString(dis);

					this.Pessoa = readString(dis);

					this.Natureza = readString(dis);

					this.CNPJ = readString(dis);

					this.Nome = readString(dis);

					this.NomeReduzido = readString(dis);

					this.Endereco = readString(dis);

					this.Complemento = readString(dis);

					this.Bairro = readString(dis);

					this.CEP = readString(dis);

					this.Estado = readString(dis);

					this.Regiao = readString(dis);

					this.DescricaoRegiao = readString(dis);

					this.CodigoMunicipio = readString(dis);

					this.Municipio = readString(dis);

					this.CodigoPais = readString(dis);

					this.NomeEstado = readString(dis);

					this.DDD = readString(dis);

					this.TributacaoFavoravel = readString(dis);

					this.DDI = readString(dis);

					this.CodIBGE = readString(dis);

					this.UCodigoMunicipio = readString(dis);

					this.EnderecoRecebimento = readString(dis);

					this.Telefone = readString(dis);

					this.FAX = readString(dis);

					this.FoneCP = readString(dis);

					this.Contato = readString(dis);

					this.PessoaFisica = readString(dis);

					this.Pais = readString(dis);

					this.Inscricao = readString(dis);

					this.InscricaoMunicipal = readString(dis);

					this.Vendedor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Comissao = null;
					} else {
						this.Comissao = dis.readDouble();
					}

					this.Conta = readString(dis);

					this.Banco1 = readString(dis);

					this.Banco2 = readString(dis);

					this.Banco3 = readString(dis);

					this.Banco4 = readString(dis);

					this.Banco5 = readString(dis);

					this.Transporte = readString(dis);

					this.TipoFrete = readString(dis);

					this.EnderecoCobranca = readString(dis);

					this.Condicao = readString(dis);

					this.BairroCobranca = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Descricao = null;
					} else {
						this.Descricao = dis.readDouble();
					}

					this.CEPCobranca = readString(dis);

					this.EstadoCobranca = readString(dis);

					this.UCodigoMunicipioCobranca = readString(dis);

					this.MunicipioCobranca = readString(dis);

					this.Prioridade = readString(dis);

					this.Risco = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCredito = null;
					} else {
						this.LimiteCredito = dis.readDouble();
					}

					this.VencimentoLimiteCredito = readString(dis);

					this.Classe = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.LimiteCreditoFinal = null;
					} else {
						this.LimiteCreditoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MoedaLimiteCredito = null;
					} else {
						this.MoedaLimiteCredito = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorSaldo = null;
					} else {
						this.MaiorSaldo = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MaiorCompra = null;
					} else {
						this.MaiorCompra = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.MediaAtraso = null;
					} else {
						this.MediaAtraso = dis.readDouble();
					}

					this.PrimeiraCompra = readString(dis);

					this.UltimaCompra = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.NumeroCompras = null;
					} else {
						this.NumeroCompras = dis.readDouble();
					}

					this.FormularioVisita = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TempoVisita = null;
					} else {
						this.TempoVisita = dis.readDouble();
					}

					this.UltimaVisita = readString(dis);

					this.TempoVisita1 = readString(dis);

					this.TempoPadrao = readString(dis);

					this.ClassificacaoVenda = readString(dis);

					this.Mensagem = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoTitulo = null;
					} else {
						this.SaldoTitulo = dis.readDouble();
					}

					this.RecolheISS = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoLiberado = null;
					} else {
						this.SaldoPedidoLiberado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.NumeroPagamentos = null;
					} else {
						this.NumeroPagamentos = dis.readDouble();
					}

					this.Transferencia = readString(dis);

					this.SUFRAMA = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ATR = null;
					} else {
						this.ATR = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.ValorAcumulado = null;
					} else {
						this.ValorAcumulado = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedido = null;
					} else {
						this.SaldoPedido = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.TituloProtestado = null;
					} else {
						this.TituloProtestado = dis.readDouble();
					}

					this.DataUltimoTitulo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ChequeDevolvido = null;
					} else {
						this.ChequeDevolvido = dis.readDouble();
					}

					this.CaixaPostal = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.Matriz = null;
					} else {
						this.Matriz = dis.readDouble();
					}

					this.DataUltimoCheque = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.MaiorDuplicata = null;
					} else {
						this.MaiorDuplicata = dis.readDouble();
					}

					this.Tabela = readString(dis);

					this.ISSIncluso = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoDuplicataMoeda = null;
					} else {
						this.SaldoDuplicataMoeda = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.PagamentoAtrasado = null;
					} else {
						this.PagamentoAtrasado = dis.readDouble();
					}

					this.Atividade = readString(dis);

					this.Cargo1 = readString(dis);

					this.Cargo2 = readString(dis);

					this.Cargo3 = readString(dis);

					this.RepresentanteTecnico = readString(dis);

					this.Supervisor = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.AliquotaIR = null;
					} else {
						this.AliquotaIR = dis.readDouble();
					}

					this.Observacao = readString(dis);

					this.RG = readString(dis);

					this.CalculaSUFRAMA = readString(dis);

					this.DataNascimento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoPedidoBloqueado = null;
					} else {
						this.SaldoPedidoBloqueado = dis.readDouble();
					}

					this.GrupoTributario = readString(dis);

					this.SegmentoAtividade1 = readString(dis);

					this.ClienteFaturamento = readString(dis);

					this.EnderecoEntrega = readString(dis);

					this.UCompleto = readString(dis);

					this.BairroEntrega = readString(dis);

					this.CEPEntrada = readString(dis);

					this.EstadoEntrada = readString(dis);

					this.CodigoLocal = readString(dis);

					this.SegmentoAtividade2 = readString(dis);

					this.TipoPessoa = readString(dis);

					this.TipoISSRS = readString(dis);

					this.SegmentoAtividade3 = readString(dis);

					this.SegmentoAtividade4 = readString(dis);

					this.SegmentoAtividade5 = readString(dis);

					this.SegmentoAtividade6 = readString(dis);

					this.SegmentoAtividade7 = readString(dis);

					this.CodigoAgente = readString(dis);

					this.SegmentoAtividade8 = readString(dis);

					this.CodigoMarcacao = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.ComissaoAgente = null;
					} else {
						this.ComissaoAgente = dis.readDouble();
					}

					this.HomePage = readString(dis);

					this.CodigoMunicipio1 = readString(dis);

					this.TipoCliente = readString(dis);

					this.Email = readString(dis);

					this.Destino1 = readString(dis);

					this.Destino2 = readString(dis);

					this.Destino3 = readString(dis);

					this.CBO = readString(dis);

					this.Observacao1 = readString(dis);

					this.CNAE = readString(dis);

					this.CodigoHistorico = readString(dis);

					this.CondicaoPagamento = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.DiasPagamento = null;
					} else {
						this.DiasPagamento = dis.readDouble();
					}

					this.Agregado = readString(dis);

					this.RecolheINSS = readString(dis);

					this.RecolheCOFINS = readString(dis);

					this.RecolheCSLL = readString(dis);

					this.RecolhePIS = readString(dis);

					this.TipoPeriodo = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinal = null;
					} else {
						this.SaldoFinal = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.SaldoFinalMoeda = null;
					} else {
						this.SaldoFinalMoeda = dis.readDouble();
					}

					this.Contabilidade = readString(dis);

					this.ClienteConvenio = readString(dis);

					this.B2B = readString(dis);

					this.CBO1 = readString(dis);

					this.CNAE1 = readString(dis);

					this.SegmentoAtividade11 = readString(dis);

					this.MensagemBloqueio = readString(dis);

					this.SubCodigo = readString(dis);

					this.FilialDebito = readString(dis);

					this.RecolhePIS1 = readString(dis);

					this.RecolheCOFINS1 = readString(dis);

					this.RecolheCSLL1 = readString(dis);

					this.ABICS = readString(dis);

					this.Vinculo = readString(dis);

					this.DataInicioVinculo = readString(dis);

					this.DataFimVinculo = readString(dis);

					this.ISSRSLC = readString(dis);

					this.TipoRegistro = readString(dis);

					this.RFASEMT = readString(dis);

					this.RIMAMT = readString(dis);

					this.REGESIM = readString(dis);

					this.ContaReceita = readString(dis);

					this.SIMPNacional = readString(dis);

					this.RecolheFETHAB = readString(dis);

					this.RFABOV = readString(dis);

					this.RFACS = readString(dis);

					this.DataNascimento1 = readString(dis);

					this.Contribuinte = readString(dis);

					this.RecolheFMD = readString(dis);

					this.TipoJuridico = readString(dis);

					this.INCULT = readString(dis);

					this.IDHistorico = readString(dis);

					this.PrestacaoServico = readString(dis);

					this.NUMRA = readString(dis);

					this.MINIRF = readString(dis);

					this.CODSIAF = readString(dis);

					this.ENDNOT = readString(dis);

					this.FOMEZER = readString(dis);

					this.FRETISS = readString(dis);

					this.COMPLEM = readString(dis);

					this.INCLTMG = readString(dis);

					this.FILTRF = readString(dis);

					this.TRIBFAV = readString(dis);

					this.REGPB = readString(dis);

					this.INOVAUT = readString(dis);

					this.PrazoMedio = readString(dis);

					this.CodigoCanal = readString(dis);

					this.CodigoSegmento = readString(dis);

					this.Deletado = readString(dis);

					this.Ukey = readString(dis);

					this.RecDeletado = readInteger(dis);

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

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// String

				writeString(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// String

				writeString(this.PrimeiraCompra, dos);

				// String

				writeString(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// String

				writeString(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// String

				writeString(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.PrazoMedio, dos);

				// String

				writeString(this.CodigoCanal, dos);

				// String

				writeString(this.CodigoSegmento, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Filial, dos);

				// String

				writeString(this.Codigo, dos);

				// String

				writeString(this.Loja, dos);

				// String

				writeString(this.Tipo, dos);

				// String

				writeString(this.Pessoa, dos);

				// String

				writeString(this.Natureza, dos);

				// String

				writeString(this.CNPJ, dos);

				// String

				writeString(this.Nome, dos);

				// String

				writeString(this.NomeReduzido, dos);

				// String

				writeString(this.Endereco, dos);

				// String

				writeString(this.Complemento, dos);

				// String

				writeString(this.Bairro, dos);

				// String

				writeString(this.CEP, dos);

				// String

				writeString(this.Estado, dos);

				// String

				writeString(this.Regiao, dos);

				// String

				writeString(this.DescricaoRegiao, dos);

				// String

				writeString(this.CodigoMunicipio, dos);

				// String

				writeString(this.Municipio, dos);

				// String

				writeString(this.CodigoPais, dos);

				// String

				writeString(this.NomeEstado, dos);

				// String

				writeString(this.DDD, dos);

				// String

				writeString(this.TributacaoFavoravel, dos);

				// String

				writeString(this.DDI, dos);

				// String

				writeString(this.CodIBGE, dos);

				// String

				writeString(this.UCodigoMunicipio, dos);

				// String

				writeString(this.EnderecoRecebimento, dos);

				// String

				writeString(this.Telefone, dos);

				// String

				writeString(this.FAX, dos);

				// String

				writeString(this.FoneCP, dos);

				// String

				writeString(this.Contato, dos);

				// String

				writeString(this.PessoaFisica, dos);

				// String

				writeString(this.Pais, dos);

				// String

				writeString(this.Inscricao, dos);

				// String

				writeString(this.InscricaoMunicipal, dos);

				// String

				writeString(this.Vendedor, dos);

				// Double

				if (this.Comissao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Comissao);
				}

				// String

				writeString(this.Conta, dos);

				// String

				writeString(this.Banco1, dos);

				// String

				writeString(this.Banco2, dos);

				// String

				writeString(this.Banco3, dos);

				// String

				writeString(this.Banco4, dos);

				// String

				writeString(this.Banco5, dos);

				// String

				writeString(this.Transporte, dos);

				// String

				writeString(this.TipoFrete, dos);

				// String

				writeString(this.EnderecoCobranca, dos);

				// String

				writeString(this.Condicao, dos);

				// String

				writeString(this.BairroCobranca, dos);

				// Double

				if (this.Descricao == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Descricao);
				}

				// String

				writeString(this.CEPCobranca, dos);

				// String

				writeString(this.EstadoCobranca, dos);

				// String

				writeString(this.UCodigoMunicipioCobranca, dos);

				// String

				writeString(this.MunicipioCobranca, dos);

				// String

				writeString(this.Prioridade, dos);

				// String

				writeString(this.Risco, dos);

				// Double

				if (this.LimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCredito);
				}

				// String

				writeString(this.VencimentoLimiteCredito, dos);

				// String

				writeString(this.Classe, dos);

				// Double

				if (this.LimiteCreditoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.LimiteCreditoFinal);
				}

				// Double

				if (this.MoedaLimiteCredito == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MoedaLimiteCredito);
				}

				// Double

				if (this.MaiorSaldo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorSaldo);
				}

				// Double

				if (this.MaiorCompra == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorCompra);
				}

				// Double

				if (this.MediaAtraso == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MediaAtraso);
				}

				// String

				writeString(this.PrimeiraCompra, dos);

				// String

				writeString(this.UltimaCompra, dos);

				// Double

				if (this.NumeroCompras == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroCompras);
				}

				// String

				writeString(this.FormularioVisita, dos);

				// Double

				if (this.TempoVisita == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TempoVisita);
				}

				// String

				writeString(this.UltimaVisita, dos);

				// String

				writeString(this.TempoVisita1, dos);

				// String

				writeString(this.TempoPadrao, dos);

				// String

				writeString(this.ClassificacaoVenda, dos);

				// String

				writeString(this.Mensagem, dos);

				// Double

				if (this.SaldoTitulo == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoTitulo);
				}

				// String

				writeString(this.RecolheISS, dos);

				// Double

				if (this.SaldoPedidoLiberado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoLiberado);
				}

				// Double

				if (this.NumeroPagamentos == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.NumeroPagamentos);
				}

				// String

				writeString(this.Transferencia, dos);

				// String

				writeString(this.SUFRAMA, dos);

				// Double

				if (this.ATR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ATR);
				}

				// Double

				if (this.ValorAcumulado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ValorAcumulado);
				}

				// Double

				if (this.SaldoPedido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedido);
				}

				// Double

				if (this.TituloProtestado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TituloProtestado);
				}

				// String

				writeString(this.DataUltimoTitulo, dos);

				// Double

				if (this.ChequeDevolvido == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ChequeDevolvido);
				}

				// String

				writeString(this.CaixaPostal, dos);

				// Double

				if (this.Matriz == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.Matriz);
				}

				// String

				writeString(this.DataUltimoCheque, dos);

				// Double

				if (this.MaiorDuplicata == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.MaiorDuplicata);
				}

				// String

				writeString(this.Tabela, dos);

				// String

				writeString(this.ISSIncluso, dos);

				// Double

				if (this.SaldoDuplicataMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoDuplicataMoeda);
				}

				// Double

				if (this.PagamentoAtrasado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.PagamentoAtrasado);
				}

				// String

				writeString(this.Atividade, dos);

				// String

				writeString(this.Cargo1, dos);

				// String

				writeString(this.Cargo2, dos);

				// String

				writeString(this.Cargo3, dos);

				// String

				writeString(this.RepresentanteTecnico, dos);

				// String

				writeString(this.Supervisor, dos);

				// Double

				if (this.AliquotaIR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.AliquotaIR);
				}

				// String

				writeString(this.Observacao, dos);

				// String

				writeString(this.RG, dos);

				// String

				writeString(this.CalculaSUFRAMA, dos);

				// String

				writeString(this.DataNascimento, dos);

				// Double

				if (this.SaldoPedidoBloqueado == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoPedidoBloqueado);
				}

				// String

				writeString(this.GrupoTributario, dos);

				// String

				writeString(this.SegmentoAtividade1, dos);

				// String

				writeString(this.ClienteFaturamento, dos);

				// String

				writeString(this.EnderecoEntrega, dos);

				// String

				writeString(this.UCompleto, dos);

				// String

				writeString(this.BairroEntrega, dos);

				// String

				writeString(this.CEPEntrada, dos);

				// String

				writeString(this.EstadoEntrada, dos);

				// String

				writeString(this.CodigoLocal, dos);

				// String

				writeString(this.SegmentoAtividade2, dos);

				// String

				writeString(this.TipoPessoa, dos);

				// String

				writeString(this.TipoISSRS, dos);

				// String

				writeString(this.SegmentoAtividade3, dos);

				// String

				writeString(this.SegmentoAtividade4, dos);

				// String

				writeString(this.SegmentoAtividade5, dos);

				// String

				writeString(this.SegmentoAtividade6, dos);

				// String

				writeString(this.SegmentoAtividade7, dos);

				// String

				writeString(this.CodigoAgente, dos);

				// String

				writeString(this.SegmentoAtividade8, dos);

				// String

				writeString(this.CodigoMarcacao, dos);

				// Double

				if (this.ComissaoAgente == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.ComissaoAgente);
				}

				// String

				writeString(this.HomePage, dos);

				// String

				writeString(this.CodigoMunicipio1, dos);

				// String

				writeString(this.TipoCliente, dos);

				// String

				writeString(this.Email, dos);

				// String

				writeString(this.Destino1, dos);

				// String

				writeString(this.Destino2, dos);

				// String

				writeString(this.Destino3, dos);

				// String

				writeString(this.CBO, dos);

				// String

				writeString(this.Observacao1, dos);

				// String

				writeString(this.CNAE, dos);

				// String

				writeString(this.CodigoHistorico, dos);

				// String

				writeString(this.CondicaoPagamento, dos);

				// Double

				if (this.DiasPagamento == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.DiasPagamento);
				}

				// String

				writeString(this.Agregado, dos);

				// String

				writeString(this.RecolheINSS, dos);

				// String

				writeString(this.RecolheCOFINS, dos);

				// String

				writeString(this.RecolheCSLL, dos);

				// String

				writeString(this.RecolhePIS, dos);

				// String

				writeString(this.TipoPeriodo, dos);

				// Double

				if (this.SaldoFinal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinal);
				}

				// Double

				if (this.SaldoFinalMoeda == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.SaldoFinalMoeda);
				}

				// String

				writeString(this.Contabilidade, dos);

				// String

				writeString(this.ClienteConvenio, dos);

				// String

				writeString(this.B2B, dos);

				// String

				writeString(this.CBO1, dos);

				// String

				writeString(this.CNAE1, dos);

				// String

				writeString(this.SegmentoAtividade11, dos);

				// String

				writeString(this.MensagemBloqueio, dos);

				// String

				writeString(this.SubCodigo, dos);

				// String

				writeString(this.FilialDebito, dos);

				// String

				writeString(this.RecolhePIS1, dos);

				// String

				writeString(this.RecolheCOFINS1, dos);

				// String

				writeString(this.RecolheCSLL1, dos);

				// String

				writeString(this.ABICS, dos);

				// String

				writeString(this.Vinculo, dos);

				// String

				writeString(this.DataInicioVinculo, dos);

				// String

				writeString(this.DataFimVinculo, dos);

				// String

				writeString(this.ISSRSLC, dos);

				// String

				writeString(this.TipoRegistro, dos);

				// String

				writeString(this.RFASEMT, dos);

				// String

				writeString(this.RIMAMT, dos);

				// String

				writeString(this.REGESIM, dos);

				// String

				writeString(this.ContaReceita, dos);

				// String

				writeString(this.SIMPNacional, dos);

				// String

				writeString(this.RecolheFETHAB, dos);

				// String

				writeString(this.RFABOV, dos);

				// String

				writeString(this.RFACS, dos);

				// String

				writeString(this.DataNascimento1, dos);

				// String

				writeString(this.Contribuinte, dos);

				// String

				writeString(this.RecolheFMD, dos);

				// String

				writeString(this.TipoJuridico, dos);

				// String

				writeString(this.INCULT, dos);

				// String

				writeString(this.IDHistorico, dos);

				// String

				writeString(this.PrestacaoServico, dos);

				// String

				writeString(this.NUMRA, dos);

				// String

				writeString(this.MINIRF, dos);

				// String

				writeString(this.CODSIAF, dos);

				// String

				writeString(this.ENDNOT, dos);

				// String

				writeString(this.FOMEZER, dos);

				// String

				writeString(this.FRETISS, dos);

				// String

				writeString(this.COMPLEM, dos);

				// String

				writeString(this.INCLTMG, dos);

				// String

				writeString(this.FILTRF, dos);

				// String

				writeString(this.TRIBFAV, dos);

				// String

				writeString(this.REGPB, dos);

				// String

				writeString(this.INOVAUT, dos);

				// String

				writeString(this.PrazoMedio, dos);

				// String

				writeString(this.CodigoCanal, dos);

				// String

				writeString(this.CodigoSegmento, dos);

				// String

				writeString(this.Deletado, dos);

				// String

				writeString(this.Ukey, dos);

				// Integer

				writeInteger(this.RecDeletado, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Filial=" + Filial);
			sb.append(",Codigo=" + Codigo);
			sb.append(",Loja=" + Loja);
			sb.append(",Tipo=" + Tipo);
			sb.append(",Pessoa=" + Pessoa);
			sb.append(",Natureza=" + Natureza);
			sb.append(",CNPJ=" + CNPJ);
			sb.append(",Nome=" + Nome);
			sb.append(",NomeReduzido=" + NomeReduzido);
			sb.append(",Endereco=" + Endereco);
			sb.append(",Complemento=" + Complemento);
			sb.append(",Bairro=" + Bairro);
			sb.append(",CEP=" + CEP);
			sb.append(",Estado=" + Estado);
			sb.append(",Regiao=" + Regiao);
			sb.append(",DescricaoRegiao=" + DescricaoRegiao);
			sb.append(",CodigoMunicipio=" + CodigoMunicipio);
			sb.append(",Municipio=" + Municipio);
			sb.append(",CodigoPais=" + CodigoPais);
			sb.append(",NomeEstado=" + NomeEstado);
			sb.append(",DDD=" + DDD);
			sb.append(",TributacaoFavoravel=" + TributacaoFavoravel);
			sb.append(",DDI=" + DDI);
			sb.append(",CodIBGE=" + CodIBGE);
			sb.append(",UCodigoMunicipio=" + UCodigoMunicipio);
			sb.append(",EnderecoRecebimento=" + EnderecoRecebimento);
			sb.append(",Telefone=" + Telefone);
			sb.append(",FAX=" + FAX);
			sb.append(",FoneCP=" + FoneCP);
			sb.append(",Contato=" + Contato);
			sb.append(",PessoaFisica=" + PessoaFisica);
			sb.append(",Pais=" + Pais);
			sb.append(",Inscricao=" + Inscricao);
			sb.append(",InscricaoMunicipal=" + InscricaoMunicipal);
			sb.append(",Vendedor=" + Vendedor);
			sb.append(",Comissao=" + String.valueOf(Comissao));
			sb.append(",Conta=" + Conta);
			sb.append(",Banco1=" + Banco1);
			sb.append(",Banco2=" + Banco2);
			sb.append(",Banco3=" + Banco3);
			sb.append(",Banco4=" + Banco4);
			sb.append(",Banco5=" + Banco5);
			sb.append(",Transporte=" + Transporte);
			sb.append(",TipoFrete=" + TipoFrete);
			sb.append(",EnderecoCobranca=" + EnderecoCobranca);
			sb.append(",Condicao=" + Condicao);
			sb.append(",BairroCobranca=" + BairroCobranca);
			sb.append(",Descricao=" + String.valueOf(Descricao));
			sb.append(",CEPCobranca=" + CEPCobranca);
			sb.append(",EstadoCobranca=" + EstadoCobranca);
			sb.append(",UCodigoMunicipioCobranca=" + UCodigoMunicipioCobranca);
			sb.append(",MunicipioCobranca=" + MunicipioCobranca);
			sb.append(",Prioridade=" + Prioridade);
			sb.append(",Risco=" + Risco);
			sb.append(",LimiteCredito=" + String.valueOf(LimiteCredito));
			sb.append(",VencimentoLimiteCredito=" + VencimentoLimiteCredito);
			sb.append(",Classe=" + Classe);
			sb.append(",LimiteCreditoFinal=" + String.valueOf(LimiteCreditoFinal));
			sb.append(",MoedaLimiteCredito=" + String.valueOf(MoedaLimiteCredito));
			sb.append(",MaiorSaldo=" + String.valueOf(MaiorSaldo));
			sb.append(",MaiorCompra=" + String.valueOf(MaiorCompra));
			sb.append(",MediaAtraso=" + String.valueOf(MediaAtraso));
			sb.append(",PrimeiraCompra=" + PrimeiraCompra);
			sb.append(",UltimaCompra=" + UltimaCompra);
			sb.append(",NumeroCompras=" + String.valueOf(NumeroCompras));
			sb.append(",FormularioVisita=" + FormularioVisita);
			sb.append(",TempoVisita=" + String.valueOf(TempoVisita));
			sb.append(",UltimaVisita=" + UltimaVisita);
			sb.append(",TempoVisita1=" + TempoVisita1);
			sb.append(",TempoPadrao=" + TempoPadrao);
			sb.append(",ClassificacaoVenda=" + ClassificacaoVenda);
			sb.append(",Mensagem=" + Mensagem);
			sb.append(",SaldoTitulo=" + String.valueOf(SaldoTitulo));
			sb.append(",RecolheISS=" + RecolheISS);
			sb.append(",SaldoPedidoLiberado=" + String.valueOf(SaldoPedidoLiberado));
			sb.append(",NumeroPagamentos=" + String.valueOf(NumeroPagamentos));
			sb.append(",Transferencia=" + Transferencia);
			sb.append(",SUFRAMA=" + SUFRAMA);
			sb.append(",ATR=" + String.valueOf(ATR));
			sb.append(",ValorAcumulado=" + String.valueOf(ValorAcumulado));
			sb.append(",SaldoPedido=" + String.valueOf(SaldoPedido));
			sb.append(",TituloProtestado=" + String.valueOf(TituloProtestado));
			sb.append(",DataUltimoTitulo=" + DataUltimoTitulo);
			sb.append(",ChequeDevolvido=" + String.valueOf(ChequeDevolvido));
			sb.append(",CaixaPostal=" + CaixaPostal);
			sb.append(",Matriz=" + String.valueOf(Matriz));
			sb.append(",DataUltimoCheque=" + DataUltimoCheque);
			sb.append(",MaiorDuplicata=" + String.valueOf(MaiorDuplicata));
			sb.append(",Tabela=" + Tabela);
			sb.append(",ISSIncluso=" + ISSIncluso);
			sb.append(",SaldoDuplicataMoeda=" + String.valueOf(SaldoDuplicataMoeda));
			sb.append(",PagamentoAtrasado=" + String.valueOf(PagamentoAtrasado));
			sb.append(",Atividade=" + Atividade);
			sb.append(",Cargo1=" + Cargo1);
			sb.append(",Cargo2=" + Cargo2);
			sb.append(",Cargo3=" + Cargo3);
			sb.append(",RepresentanteTecnico=" + RepresentanteTecnico);
			sb.append(",Supervisor=" + Supervisor);
			sb.append(",AliquotaIR=" + String.valueOf(AliquotaIR));
			sb.append(",Observacao=" + Observacao);
			sb.append(",RG=" + RG);
			sb.append(",CalculaSUFRAMA=" + CalculaSUFRAMA);
			sb.append(",DataNascimento=" + DataNascimento);
			sb.append(",SaldoPedidoBloqueado=" + String.valueOf(SaldoPedidoBloqueado));
			sb.append(",GrupoTributario=" + GrupoTributario);
			sb.append(",SegmentoAtividade1=" + SegmentoAtividade1);
			sb.append(",ClienteFaturamento=" + ClienteFaturamento);
			sb.append(",EnderecoEntrega=" + EnderecoEntrega);
			sb.append(",UCompleto=" + UCompleto);
			sb.append(",BairroEntrega=" + BairroEntrega);
			sb.append(",CEPEntrada=" + CEPEntrada);
			sb.append(",EstadoEntrada=" + EstadoEntrada);
			sb.append(",CodigoLocal=" + CodigoLocal);
			sb.append(",SegmentoAtividade2=" + SegmentoAtividade2);
			sb.append(",TipoPessoa=" + TipoPessoa);
			sb.append(",TipoISSRS=" + TipoISSRS);
			sb.append(",SegmentoAtividade3=" + SegmentoAtividade3);
			sb.append(",SegmentoAtividade4=" + SegmentoAtividade4);
			sb.append(",SegmentoAtividade5=" + SegmentoAtividade5);
			sb.append(",SegmentoAtividade6=" + SegmentoAtividade6);
			sb.append(",SegmentoAtividade7=" + SegmentoAtividade7);
			sb.append(",CodigoAgente=" + CodigoAgente);
			sb.append(",SegmentoAtividade8=" + SegmentoAtividade8);
			sb.append(",CodigoMarcacao=" + CodigoMarcacao);
			sb.append(",ComissaoAgente=" + String.valueOf(ComissaoAgente));
			sb.append(",HomePage=" + HomePage);
			sb.append(",CodigoMunicipio1=" + CodigoMunicipio1);
			sb.append(",TipoCliente=" + TipoCliente);
			sb.append(",Email=" + Email);
			sb.append(",Destino1=" + Destino1);
			sb.append(",Destino2=" + Destino2);
			sb.append(",Destino3=" + Destino3);
			sb.append(",CBO=" + CBO);
			sb.append(",Observacao1=" + Observacao1);
			sb.append(",CNAE=" + CNAE);
			sb.append(",CodigoHistorico=" + CodigoHistorico);
			sb.append(",CondicaoPagamento=" + CondicaoPagamento);
			sb.append(",DiasPagamento=" + String.valueOf(DiasPagamento));
			sb.append(",Agregado=" + Agregado);
			sb.append(",RecolheINSS=" + RecolheINSS);
			sb.append(",RecolheCOFINS=" + RecolheCOFINS);
			sb.append(",RecolheCSLL=" + RecolheCSLL);
			sb.append(",RecolhePIS=" + RecolhePIS);
			sb.append(",TipoPeriodo=" + TipoPeriodo);
			sb.append(",SaldoFinal=" + String.valueOf(SaldoFinal));
			sb.append(",SaldoFinalMoeda=" + String.valueOf(SaldoFinalMoeda));
			sb.append(",Contabilidade=" + Contabilidade);
			sb.append(",ClienteConvenio=" + ClienteConvenio);
			sb.append(",B2B=" + B2B);
			sb.append(",CBO1=" + CBO1);
			sb.append(",CNAE1=" + CNAE1);
			sb.append(",SegmentoAtividade11=" + SegmentoAtividade11);
			sb.append(",MensagemBloqueio=" + MensagemBloqueio);
			sb.append(",SubCodigo=" + SubCodigo);
			sb.append(",FilialDebito=" + FilialDebito);
			sb.append(",RecolhePIS1=" + RecolhePIS1);
			sb.append(",RecolheCOFINS1=" + RecolheCOFINS1);
			sb.append(",RecolheCSLL1=" + RecolheCSLL1);
			sb.append(",ABICS=" + ABICS);
			sb.append(",Vinculo=" + Vinculo);
			sb.append(",DataInicioVinculo=" + DataInicioVinculo);
			sb.append(",DataFimVinculo=" + DataFimVinculo);
			sb.append(",ISSRSLC=" + ISSRSLC);
			sb.append(",TipoRegistro=" + TipoRegistro);
			sb.append(",RFASEMT=" + RFASEMT);
			sb.append(",RIMAMT=" + RIMAMT);
			sb.append(",REGESIM=" + REGESIM);
			sb.append(",ContaReceita=" + ContaReceita);
			sb.append(",SIMPNacional=" + SIMPNacional);
			sb.append(",RecolheFETHAB=" + RecolheFETHAB);
			sb.append(",RFABOV=" + RFABOV);
			sb.append(",RFACS=" + RFACS);
			sb.append(",DataNascimento1=" + DataNascimento1);
			sb.append(",Contribuinte=" + Contribuinte);
			sb.append(",RecolheFMD=" + RecolheFMD);
			sb.append(",TipoJuridico=" + TipoJuridico);
			sb.append(",INCULT=" + INCULT);
			sb.append(",IDHistorico=" + IDHistorico);
			sb.append(",PrestacaoServico=" + PrestacaoServico);
			sb.append(",NUMRA=" + NUMRA);
			sb.append(",MINIRF=" + MINIRF);
			sb.append(",CODSIAF=" + CODSIAF);
			sb.append(",ENDNOT=" + ENDNOT);
			sb.append(",FOMEZER=" + FOMEZER);
			sb.append(",FRETISS=" + FRETISS);
			sb.append(",COMPLEM=" + COMPLEM);
			sb.append(",INCLTMG=" + INCLTMG);
			sb.append(",FILTRF=" + FILTRF);
			sb.append(",TRIBFAV=" + TRIBFAV);
			sb.append(",REGPB=" + REGPB);
			sb.append(",INOVAUT=" + INOVAUT);
			sb.append(",PrazoMedio=" + PrazoMedio);
			sb.append(",CodigoCanal=" + CodigoCanal);
			sb.append(",CodigoSegmento=" + CodigoSegmento);
			sb.append(",Deletado=" + Deletado);
			sb.append(",Ukey=" + Ukey);
			sb.append(",RecDeletado=" + String.valueOf(RecDeletado));
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

			if (Codigo == null) {
				sb.append("<null>");
			} else {
				sb.append(Codigo);
			}

			sb.append("|");

			if (Loja == null) {
				sb.append("<null>");
			} else {
				sb.append(Loja);
			}

			sb.append("|");

			if (Tipo == null) {
				sb.append("<null>");
			} else {
				sb.append(Tipo);
			}

			sb.append("|");

			if (Pessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(Pessoa);
			}

			sb.append("|");

			if (Natureza == null) {
				sb.append("<null>");
			} else {
				sb.append(Natureza);
			}

			sb.append("|");

			if (CNPJ == null) {
				sb.append("<null>");
			} else {
				sb.append(CNPJ);
			}

			sb.append("|");

			if (Nome == null) {
				sb.append("<null>");
			} else {
				sb.append(Nome);
			}

			sb.append("|");

			if (NomeReduzido == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeReduzido);
			}

			sb.append("|");

			if (Endereco == null) {
				sb.append("<null>");
			} else {
				sb.append(Endereco);
			}

			sb.append("|");

			if (Complemento == null) {
				sb.append("<null>");
			} else {
				sb.append(Complemento);
			}

			sb.append("|");

			if (Bairro == null) {
				sb.append("<null>");
			} else {
				sb.append(Bairro);
			}

			sb.append("|");

			if (CEP == null) {
				sb.append("<null>");
			} else {
				sb.append(CEP);
			}

			sb.append("|");

			if (Estado == null) {
				sb.append("<null>");
			} else {
				sb.append(Estado);
			}

			sb.append("|");

			if (Regiao == null) {
				sb.append("<null>");
			} else {
				sb.append(Regiao);
			}

			sb.append("|");

			if (DescricaoRegiao == null) {
				sb.append("<null>");
			} else {
				sb.append(DescricaoRegiao);
			}

			sb.append("|");

			if (CodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio);
			}

			sb.append("|");

			if (Municipio == null) {
				sb.append("<null>");
			} else {
				sb.append(Municipio);
			}

			sb.append("|");

			if (CodigoPais == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoPais);
			}

			sb.append("|");

			if (NomeEstado == null) {
				sb.append("<null>");
			} else {
				sb.append(NomeEstado);
			}

			sb.append("|");

			if (DDD == null) {
				sb.append("<null>");
			} else {
				sb.append(DDD);
			}

			sb.append("|");

			if (TributacaoFavoravel == null) {
				sb.append("<null>");
			} else {
				sb.append(TributacaoFavoravel);
			}

			sb.append("|");

			if (DDI == null) {
				sb.append("<null>");
			} else {
				sb.append(DDI);
			}

			sb.append("|");

			if (CodIBGE == null) {
				sb.append("<null>");
			} else {
				sb.append(CodIBGE);
			}

			sb.append("|");

			if (UCodigoMunicipio == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipio);
			}

			sb.append("|");

			if (EnderecoRecebimento == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoRecebimento);
			}

			sb.append("|");

			if (Telefone == null) {
				sb.append("<null>");
			} else {
				sb.append(Telefone);
			}

			sb.append("|");

			if (FAX == null) {
				sb.append("<null>");
			} else {
				sb.append(FAX);
			}

			sb.append("|");

			if (FoneCP == null) {
				sb.append("<null>");
			} else {
				sb.append(FoneCP);
			}

			sb.append("|");

			if (Contato == null) {
				sb.append("<null>");
			} else {
				sb.append(Contato);
			}

			sb.append("|");

			if (PessoaFisica == null) {
				sb.append("<null>");
			} else {
				sb.append(PessoaFisica);
			}

			sb.append("|");

			if (Pais == null) {
				sb.append("<null>");
			} else {
				sb.append(Pais);
			}

			sb.append("|");

			if (Inscricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Inscricao);
			}

			sb.append("|");

			if (InscricaoMunicipal == null) {
				sb.append("<null>");
			} else {
				sb.append(InscricaoMunicipal);
			}

			sb.append("|");

			if (Vendedor == null) {
				sb.append("<null>");
			} else {
				sb.append(Vendedor);
			}

			sb.append("|");

			if (Comissao == null) {
				sb.append("<null>");
			} else {
				sb.append(Comissao);
			}

			sb.append("|");

			if (Conta == null) {
				sb.append("<null>");
			} else {
				sb.append(Conta);
			}

			sb.append("|");

			if (Banco1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco1);
			}

			sb.append("|");

			if (Banco2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco2);
			}

			sb.append("|");

			if (Banco3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco3);
			}

			sb.append("|");

			if (Banco4 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco4);
			}

			sb.append("|");

			if (Banco5 == null) {
				sb.append("<null>");
			} else {
				sb.append(Banco5);
			}

			sb.append("|");

			if (Transporte == null) {
				sb.append("<null>");
			} else {
				sb.append(Transporte);
			}

			sb.append("|");

			if (TipoFrete == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoFrete);
			}

			sb.append("|");

			if (EnderecoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoCobranca);
			}

			sb.append("|");

			if (Condicao == null) {
				sb.append("<null>");
			} else {
				sb.append(Condicao);
			}

			sb.append("|");

			if (BairroCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroCobranca);
			}

			sb.append("|");

			if (Descricao == null) {
				sb.append("<null>");
			} else {
				sb.append(Descricao);
			}

			sb.append("|");

			if (CEPCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPCobranca);
			}

			sb.append("|");

			if (EstadoCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoCobranca);
			}

			sb.append("|");

			if (UCodigoMunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(UCodigoMunicipioCobranca);
			}

			sb.append("|");

			if (MunicipioCobranca == null) {
				sb.append("<null>");
			} else {
				sb.append(MunicipioCobranca);
			}

			sb.append("|");

			if (Prioridade == null) {
				sb.append("<null>");
			} else {
				sb.append(Prioridade);
			}

			sb.append("|");

			if (Risco == null) {
				sb.append("<null>");
			} else {
				sb.append(Risco);
			}

			sb.append("|");

			if (LimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCredito);
			}

			sb.append("|");

			if (VencimentoLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(VencimentoLimiteCredito);
			}

			sb.append("|");

			if (Classe == null) {
				sb.append("<null>");
			} else {
				sb.append(Classe);
			}

			sb.append("|");

			if (LimiteCreditoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(LimiteCreditoFinal);
			}

			sb.append("|");

			if (MoedaLimiteCredito == null) {
				sb.append("<null>");
			} else {
				sb.append(MoedaLimiteCredito);
			}

			sb.append("|");

			if (MaiorSaldo == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorSaldo);
			}

			sb.append("|");

			if (MaiorCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorCompra);
			}

			sb.append("|");

			if (MediaAtraso == null) {
				sb.append("<null>");
			} else {
				sb.append(MediaAtraso);
			}

			sb.append("|");

			if (PrimeiraCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(PrimeiraCompra);
			}

			sb.append("|");

			if (UltimaCompra == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaCompra);
			}

			sb.append("|");

			if (NumeroCompras == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroCompras);
			}

			sb.append("|");

			if (FormularioVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(FormularioVisita);
			}

			sb.append("|");

			if (TempoVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita);
			}

			sb.append("|");

			if (UltimaVisita == null) {
				sb.append("<null>");
			} else {
				sb.append(UltimaVisita);
			}

			sb.append("|");

			if (TempoVisita1 == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoVisita1);
			}

			sb.append("|");

			if (TempoPadrao == null) {
				sb.append("<null>");
			} else {
				sb.append(TempoPadrao);
			}

			sb.append("|");

			if (ClassificacaoVenda == null) {
				sb.append("<null>");
			} else {
				sb.append(ClassificacaoVenda);
			}

			sb.append("|");

			if (Mensagem == null) {
				sb.append("<null>");
			} else {
				sb.append(Mensagem);
			}

			sb.append("|");

			if (SaldoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoTitulo);
			}

			sb.append("|");

			if (RecolheISS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheISS);
			}

			sb.append("|");

			if (SaldoPedidoLiberado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoLiberado);
			}

			sb.append("|");

			if (NumeroPagamentos == null) {
				sb.append("<null>");
			} else {
				sb.append(NumeroPagamentos);
			}

			sb.append("|");

			if (Transferencia == null) {
				sb.append("<null>");
			} else {
				sb.append(Transferencia);
			}

			sb.append("|");

			if (SUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(SUFRAMA);
			}

			sb.append("|");

			if (ATR == null) {
				sb.append("<null>");
			} else {
				sb.append(ATR);
			}

			sb.append("|");

			if (ValorAcumulado == null) {
				sb.append("<null>");
			} else {
				sb.append(ValorAcumulado);
			}

			sb.append("|");

			if (SaldoPedido == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedido);
			}

			sb.append("|");

			if (TituloProtestado == null) {
				sb.append("<null>");
			} else {
				sb.append(TituloProtestado);
			}

			sb.append("|");

			if (DataUltimoTitulo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoTitulo);
			}

			sb.append("|");

			if (ChequeDevolvido == null) {
				sb.append("<null>");
			} else {
				sb.append(ChequeDevolvido);
			}

			sb.append("|");

			if (CaixaPostal == null) {
				sb.append("<null>");
			} else {
				sb.append(CaixaPostal);
			}

			sb.append("|");

			if (Matriz == null) {
				sb.append("<null>");
			} else {
				sb.append(Matriz);
			}

			sb.append("|");

			if (DataUltimoCheque == null) {
				sb.append("<null>");
			} else {
				sb.append(DataUltimoCheque);
			}

			sb.append("|");

			if (MaiorDuplicata == null) {
				sb.append("<null>");
			} else {
				sb.append(MaiorDuplicata);
			}

			sb.append("|");

			if (Tabela == null) {
				sb.append("<null>");
			} else {
				sb.append(Tabela);
			}

			sb.append("|");

			if (ISSIncluso == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSIncluso);
			}

			sb.append("|");

			if (SaldoDuplicataMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoDuplicataMoeda);
			}

			sb.append("|");

			if (PagamentoAtrasado == null) {
				sb.append("<null>");
			} else {
				sb.append(PagamentoAtrasado);
			}

			sb.append("|");

			if (Atividade == null) {
				sb.append("<null>");
			} else {
				sb.append(Atividade);
			}

			sb.append("|");

			if (Cargo1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo1);
			}

			sb.append("|");

			if (Cargo2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo2);
			}

			sb.append("|");

			if (Cargo3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Cargo3);
			}

			sb.append("|");

			if (RepresentanteTecnico == null) {
				sb.append("<null>");
			} else {
				sb.append(RepresentanteTecnico);
			}

			sb.append("|");

			if (Supervisor == null) {
				sb.append("<null>");
			} else {
				sb.append(Supervisor);
			}

			sb.append("|");

			if (AliquotaIR == null) {
				sb.append("<null>");
			} else {
				sb.append(AliquotaIR);
			}

			sb.append("|");

			if (Observacao == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao);
			}

			sb.append("|");

			if (RG == null) {
				sb.append("<null>");
			} else {
				sb.append(RG);
			}

			sb.append("|");

			if (CalculaSUFRAMA == null) {
				sb.append("<null>");
			} else {
				sb.append(CalculaSUFRAMA);
			}

			sb.append("|");

			if (DataNascimento == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento);
			}

			sb.append("|");

			if (SaldoPedidoBloqueado == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoPedidoBloqueado);
			}

			sb.append("|");

			if (GrupoTributario == null) {
				sb.append("<null>");
			} else {
				sb.append(GrupoTributario);
			}

			sb.append("|");

			if (SegmentoAtividade1 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade1);
			}

			sb.append("|");

			if (ClienteFaturamento == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteFaturamento);
			}

			sb.append("|");

			if (EnderecoEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(EnderecoEntrega);
			}

			sb.append("|");

			if (UCompleto == null) {
				sb.append("<null>");
			} else {
				sb.append(UCompleto);
			}

			sb.append("|");

			if (BairroEntrega == null) {
				sb.append("<null>");
			} else {
				sb.append(BairroEntrega);
			}

			sb.append("|");

			if (CEPEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(CEPEntrada);
			}

			sb.append("|");

			if (EstadoEntrada == null) {
				sb.append("<null>");
			} else {
				sb.append(EstadoEntrada);
			}

			sb.append("|");

			if (CodigoLocal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoLocal);
			}

			sb.append("|");

			if (SegmentoAtividade2 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade2);
			}

			sb.append("|");

			if (TipoPessoa == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPessoa);
			}

			sb.append("|");

			if (TipoISSRS == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoISSRS);
			}

			sb.append("|");

			if (SegmentoAtividade3 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade3);
			}

			sb.append("|");

			if (SegmentoAtividade4 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade4);
			}

			sb.append("|");

			if (SegmentoAtividade5 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade5);
			}

			sb.append("|");

			if (SegmentoAtividade6 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade6);
			}

			sb.append("|");

			if (SegmentoAtividade7 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade7);
			}

			sb.append("|");

			if (CodigoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoAgente);
			}

			sb.append("|");

			if (SegmentoAtividade8 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade8);
			}

			sb.append("|");

			if (CodigoMarcacao == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMarcacao);
			}

			sb.append("|");

			if (ComissaoAgente == null) {
				sb.append("<null>");
			} else {
				sb.append(ComissaoAgente);
			}

			sb.append("|");

			if (HomePage == null) {
				sb.append("<null>");
			} else {
				sb.append(HomePage);
			}

			sb.append("|");

			if (CodigoMunicipio1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoMunicipio1);
			}

			sb.append("|");

			if (TipoCliente == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoCliente);
			}

			sb.append("|");

			if (Email == null) {
				sb.append("<null>");
			} else {
				sb.append(Email);
			}

			sb.append("|");

			if (Destino1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino1);
			}

			sb.append("|");

			if (Destino2 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino2);
			}

			sb.append("|");

			if (Destino3 == null) {
				sb.append("<null>");
			} else {
				sb.append(Destino3);
			}

			sb.append("|");

			if (CBO == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO);
			}

			sb.append("|");

			if (Observacao1 == null) {
				sb.append("<null>");
			} else {
				sb.append(Observacao1);
			}

			sb.append("|");

			if (CNAE == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE);
			}

			sb.append("|");

			if (CodigoHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoHistorico);
			}

			sb.append("|");

			if (CondicaoPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(CondicaoPagamento);
			}

			sb.append("|");

			if (DiasPagamento == null) {
				sb.append("<null>");
			} else {
				sb.append(DiasPagamento);
			}

			sb.append("|");

			if (Agregado == null) {
				sb.append("<null>");
			} else {
				sb.append(Agregado);
			}

			sb.append("|");

			if (RecolheINSS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheINSS);
			}

			sb.append("|");

			if (RecolheCOFINS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS);
			}

			sb.append("|");

			if (RecolheCSLL == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL);
			}

			sb.append("|");

			if (RecolhePIS == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS);
			}

			sb.append("|");

			if (TipoPeriodo == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoPeriodo);
			}

			sb.append("|");

			if (SaldoFinal == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinal);
			}

			sb.append("|");

			if (SaldoFinalMoeda == null) {
				sb.append("<null>");
			} else {
				sb.append(SaldoFinalMoeda);
			}

			sb.append("|");

			if (Contabilidade == null) {
				sb.append("<null>");
			} else {
				sb.append(Contabilidade);
			}

			sb.append("|");

			if (ClienteConvenio == null) {
				sb.append("<null>");
			} else {
				sb.append(ClienteConvenio);
			}

			sb.append("|");

			if (B2B == null) {
				sb.append("<null>");
			} else {
				sb.append(B2B);
			}

			sb.append("|");

			if (CBO1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CBO1);
			}

			sb.append("|");

			if (CNAE1 == null) {
				sb.append("<null>");
			} else {
				sb.append(CNAE1);
			}

			sb.append("|");

			if (SegmentoAtividade11 == null) {
				sb.append("<null>");
			} else {
				sb.append(SegmentoAtividade11);
			}

			sb.append("|");

			if (MensagemBloqueio == null) {
				sb.append("<null>");
			} else {
				sb.append(MensagemBloqueio);
			}

			sb.append("|");

			if (SubCodigo == null) {
				sb.append("<null>");
			} else {
				sb.append(SubCodigo);
			}

			sb.append("|");

			if (FilialDebito == null) {
				sb.append("<null>");
			} else {
				sb.append(FilialDebito);
			}

			sb.append("|");

			if (RecolhePIS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolhePIS1);
			}

			sb.append("|");

			if (RecolheCOFINS1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCOFINS1);
			}

			sb.append("|");

			if (RecolheCSLL1 == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheCSLL1);
			}

			sb.append("|");

			if (ABICS == null) {
				sb.append("<null>");
			} else {
				sb.append(ABICS);
			}

			sb.append("|");

			if (Vinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(Vinculo);
			}

			sb.append("|");

			if (DataInicioVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataInicioVinculo);
			}

			sb.append("|");

			if (DataFimVinculo == null) {
				sb.append("<null>");
			} else {
				sb.append(DataFimVinculo);
			}

			sb.append("|");

			if (ISSRSLC == null) {
				sb.append("<null>");
			} else {
				sb.append(ISSRSLC);
			}

			sb.append("|");

			if (TipoRegistro == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoRegistro);
			}

			sb.append("|");

			if (RFASEMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RFASEMT);
			}

			sb.append("|");

			if (RIMAMT == null) {
				sb.append("<null>");
			} else {
				sb.append(RIMAMT);
			}

			sb.append("|");

			if (REGESIM == null) {
				sb.append("<null>");
			} else {
				sb.append(REGESIM);
			}

			sb.append("|");

			if (ContaReceita == null) {
				sb.append("<null>");
			} else {
				sb.append(ContaReceita);
			}

			sb.append("|");

			if (SIMPNacional == null) {
				sb.append("<null>");
			} else {
				sb.append(SIMPNacional);
			}

			sb.append("|");

			if (RecolheFETHAB == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFETHAB);
			}

			sb.append("|");

			if (RFABOV == null) {
				sb.append("<null>");
			} else {
				sb.append(RFABOV);
			}

			sb.append("|");

			if (RFACS == null) {
				sb.append("<null>");
			} else {
				sb.append(RFACS);
			}

			sb.append("|");

			if (DataNascimento1 == null) {
				sb.append("<null>");
			} else {
				sb.append(DataNascimento1);
			}

			sb.append("|");

			if (Contribuinte == null) {
				sb.append("<null>");
			} else {
				sb.append(Contribuinte);
			}

			sb.append("|");

			if (RecolheFMD == null) {
				sb.append("<null>");
			} else {
				sb.append(RecolheFMD);
			}

			sb.append("|");

			if (TipoJuridico == null) {
				sb.append("<null>");
			} else {
				sb.append(TipoJuridico);
			}

			sb.append("|");

			if (INCULT == null) {
				sb.append("<null>");
			} else {
				sb.append(INCULT);
			}

			sb.append("|");

			if (IDHistorico == null) {
				sb.append("<null>");
			} else {
				sb.append(IDHistorico);
			}

			sb.append("|");

			if (PrestacaoServico == null) {
				sb.append("<null>");
			} else {
				sb.append(PrestacaoServico);
			}

			sb.append("|");

			if (NUMRA == null) {
				sb.append("<null>");
			} else {
				sb.append(NUMRA);
			}

			sb.append("|");

			if (MINIRF == null) {
				sb.append("<null>");
			} else {
				sb.append(MINIRF);
			}

			sb.append("|");

			if (CODSIAF == null) {
				sb.append("<null>");
			} else {
				sb.append(CODSIAF);
			}

			sb.append("|");

			if (ENDNOT == null) {
				sb.append("<null>");
			} else {
				sb.append(ENDNOT);
			}

			sb.append("|");

			if (FOMEZER == null) {
				sb.append("<null>");
			} else {
				sb.append(FOMEZER);
			}

			sb.append("|");

			if (FRETISS == null) {
				sb.append("<null>");
			} else {
				sb.append(FRETISS);
			}

			sb.append("|");

			if (COMPLEM == null) {
				sb.append("<null>");
			} else {
				sb.append(COMPLEM);
			}

			sb.append("|");

			if (INCLTMG == null) {
				sb.append("<null>");
			} else {
				sb.append(INCLTMG);
			}

			sb.append("|");

			if (FILTRF == null) {
				sb.append("<null>");
			} else {
				sb.append(FILTRF);
			}

			sb.append("|");

			if (TRIBFAV == null) {
				sb.append("<null>");
			} else {
				sb.append(TRIBFAV);
			}

			sb.append("|");

			if (REGPB == null) {
				sb.append("<null>");
			} else {
				sb.append(REGPB);
			}

			sb.append("|");

			if (INOVAUT == null) {
				sb.append("<null>");
			} else {
				sb.append(INOVAUT);
			}

			sb.append("|");

			if (PrazoMedio == null) {
				sb.append("<null>");
			} else {
				sb.append(PrazoMedio);
			}

			sb.append("|");

			if (CodigoCanal == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoCanal);
			}

			sb.append("|");

			if (CodigoSegmento == null) {
				sb.append("<null>");
			} else {
				sb.append(CodigoSegmento);
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

			if (RecDeletado == null) {
				sb.append("<null>");
			} else {
				sb.append(RecDeletado);
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

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tDBInput_1");
		org.slf4j.MDC.put("_subJobPid", "9B9YGs_" + subJobPidCounter.getAndIncrement());

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
				Ajuste_dataStruct Ajuste_data = new Ajuste_dataStruct();
				row3Struct row3 = new row3Struct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

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
									+ "\"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/Clientes.qvd\"");
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
						outtFileOutputDelimited_1.write("Codigo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Loja");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tipo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Pessoa");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Natureza");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CNPJ");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Nome");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NomeReduzido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Endereco");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Complemento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Bairro");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CEP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Estado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Regiao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DescricaoRegiao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoMunicipio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Municipio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoPais");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NomeEstado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DDD");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TributacaoFavoravel");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DDI");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodIBGE");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UCodigoMunicipio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EnderecoRecebimento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Telefone");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FAX");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FoneCP");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Contato");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PessoaFisica");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Pais");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Inscricao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("InscricaoMunicipal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Vendedor");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Comissao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Conta");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Banco1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Banco2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Banco3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Banco4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Banco5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Transporte");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoFrete");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EnderecoCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Condicao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BairroCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Descricao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CEPCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EstadoCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UCodigoMunicipioCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MunicipioCobranca");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Prioridade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Risco");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LimiteCredito");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("VencimentoLimiteCredito");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Classe");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("LimiteCreditoFinal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MoedaLimiteCredito");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MaiorSaldo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MaiorCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MediaAtraso");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrimeiraCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UltimaCompra");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroCompras");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FormularioVisita");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TempoVisita");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UltimaVisita");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TempoVisita1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TempoPadrao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ClassificacaoVenda");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Mensagem");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoTitulo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheISS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoPedidoLiberado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NumeroPagamentos");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Transferencia");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SUFRAMA");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ATR");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ValorAcumulado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoPedido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TituloProtestado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataUltimoTitulo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ChequeDevolvido");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CaixaPostal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Matriz");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataUltimoCheque");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MaiorDuplicata");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Tabela");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ISSIncluso");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_1(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("SaldoDuplicataMoeda");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PagamentoAtrasado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Atividade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Cargo1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Cargo2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Cargo3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RepresentanteTecnico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Supervisor");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("AliquotaIR");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Observacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RG");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CalculaSUFRAMA");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataNascimento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoPedidoBloqueado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("GrupoTributario");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ClienteFaturamento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EnderecoEntrega");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("UCompleto");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("BairroEntrega");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CEPEntrada");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("EstadoEntrada");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoLocal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoPessoa");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoISSRS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade4");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade5");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade6");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade7");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoAgente");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade8");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoMarcacao");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ComissaoAgente");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("HomePage");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoMunicipio1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoCliente");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Email");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Destino1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Destino2");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Destino3");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CBO");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Observacao1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CNAE");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CodigoHistorico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CondicaoPagamento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DiasPagamento");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Agregado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheINSS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheCOFINS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheCSLL");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolhePIS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoPeriodo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoFinal");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SaldoFinalMoeda");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Contabilidade");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ClienteConvenio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("B2B");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CBO1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CNAE1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SegmentoAtividade11");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("MensagemBloqueio");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SubCodigo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FilialDebito");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolhePIS1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheCOFINS1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheCSLL1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ABICS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Vinculo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataInicioVinculo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataFimVinculo");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ISSRSLC");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoRegistro");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RFASEMT");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RIMAMT");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("REGESIM");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ContaReceita");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("SIMPNacional");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheFETHAB");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RFABOV");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RFACS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("DataNascimento1");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Contribuinte");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecolheFMD");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TipoJuridico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("INCULT");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("IDHistorico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("PrestacaoServico");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("NUMRA");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putHeaderValue_2(java.io.Writer outtFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						outtFileOutputDelimited_1.write("MINIRF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("CODSIAF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("ENDNOT");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FOMEZER");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FRETISS");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("COMPLEM");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("INCLTMG");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("FILTRF");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("TRIBFAV");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("REGPB");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("INOVAUT");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Deletado");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("Ukey");
						outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
						outtFileOutputDelimited_1.write("RecDeletado");
					}

					public void putValue_0(final row3Struct row3, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row3.Filial != null) {
							sb_tFileOutputDelimited_1.append(row3.Filial);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Codigo != null) {
							sb_tFileOutputDelimited_1.append(row3.Codigo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Loja != null) {
							sb_tFileOutputDelimited_1.append(row3.Loja);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Tipo != null) {
							sb_tFileOutputDelimited_1.append(row3.Tipo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Pessoa != null) {
							sb_tFileOutputDelimited_1.append(row3.Pessoa);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Natureza != null) {
							sb_tFileOutputDelimited_1.append(row3.Natureza);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CNPJ != null) {
							sb_tFileOutputDelimited_1.append(row3.CNPJ);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Nome != null) {
							sb_tFileOutputDelimited_1.append(row3.Nome);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.NomeReduzido != null) {
							sb_tFileOutputDelimited_1.append(row3.NomeReduzido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Endereco != null) {
							sb_tFileOutputDelimited_1.append(row3.Endereco);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Complemento != null) {
							sb_tFileOutputDelimited_1.append(row3.Complemento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Bairro != null) {
							sb_tFileOutputDelimited_1.append(row3.Bairro);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CEP != null) {
							sb_tFileOutputDelimited_1.append(row3.CEP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Estado != null) {
							sb_tFileOutputDelimited_1.append(row3.Estado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Regiao != null) {
							sb_tFileOutputDelimited_1.append(row3.Regiao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DescricaoRegiao != null) {
							sb_tFileOutputDelimited_1.append(row3.DescricaoRegiao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoMunicipio != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoMunicipio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Municipio != null) {
							sb_tFileOutputDelimited_1.append(row3.Municipio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoPais != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoPais);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.NomeEstado != null) {
							sb_tFileOutputDelimited_1.append(row3.NomeEstado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DDD != null) {
							sb_tFileOutputDelimited_1.append(row3.DDD);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TributacaoFavoravel != null) {
							sb_tFileOutputDelimited_1.append(row3.TributacaoFavoravel);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DDI != null) {
							sb_tFileOutputDelimited_1.append(row3.DDI);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodIBGE != null) {
							sb_tFileOutputDelimited_1.append(row3.CodIBGE);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.UCodigoMunicipio != null) {
							sb_tFileOutputDelimited_1.append(row3.UCodigoMunicipio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.EnderecoRecebimento != null) {
							sb_tFileOutputDelimited_1.append(row3.EnderecoRecebimento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Telefone != null) {
							sb_tFileOutputDelimited_1.append(row3.Telefone);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FAX != null) {
							sb_tFileOutputDelimited_1.append(row3.FAX);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FoneCP != null) {
							sb_tFileOutputDelimited_1.append(row3.FoneCP);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Contato != null) {
							sb_tFileOutputDelimited_1.append(row3.Contato);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.PessoaFisica != null) {
							sb_tFileOutputDelimited_1.append(row3.PessoaFisica);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Pais != null) {
							sb_tFileOutputDelimited_1.append(row3.Pais);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Inscricao != null) {
							sb_tFileOutputDelimited_1.append(row3.Inscricao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.InscricaoMunicipal != null) {
							sb_tFileOutputDelimited_1.append(row3.InscricaoMunicipal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Vendedor != null) {
							sb_tFileOutputDelimited_1.append(row3.Vendedor);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Comissao != null) {
							sb_tFileOutputDelimited_1.append(row3.Comissao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Conta != null) {
							sb_tFileOutputDelimited_1.append(row3.Conta);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Banco1 != null) {
							sb_tFileOutputDelimited_1.append(row3.Banco1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Banco2 != null) {
							sb_tFileOutputDelimited_1.append(row3.Banco2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Banco3 != null) {
							sb_tFileOutputDelimited_1.append(row3.Banco3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Banco4 != null) {
							sb_tFileOutputDelimited_1.append(row3.Banco4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Banco5 != null) {
							sb_tFileOutputDelimited_1.append(row3.Banco5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Transporte != null) {
							sb_tFileOutputDelimited_1.append(row3.Transporte);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoFrete != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoFrete);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.EnderecoCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.EnderecoCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Condicao != null) {
							sb_tFileOutputDelimited_1.append(row3.Condicao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.BairroCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.BairroCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Descricao != null) {
							sb_tFileOutputDelimited_1.append(row3.Descricao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CEPCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.CEPCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.EstadoCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.EstadoCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.UCodigoMunicipioCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.UCodigoMunicipioCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MunicipioCobranca != null) {
							sb_tFileOutputDelimited_1.append(row3.MunicipioCobranca);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Prioridade != null) {
							sb_tFileOutputDelimited_1.append(row3.Prioridade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Risco != null) {
							sb_tFileOutputDelimited_1.append(row3.Risco);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.LimiteCredito != null) {
							sb_tFileOutputDelimited_1.append(row3.LimiteCredito);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.VencimentoLimiteCredito != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row3.VencimentoLimiteCredito, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Classe != null) {
							sb_tFileOutputDelimited_1.append(row3.Classe);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.LimiteCreditoFinal != null) {
							sb_tFileOutputDelimited_1.append(row3.LimiteCreditoFinal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MoedaLimiteCredito != null) {
							sb_tFileOutputDelimited_1.append(row3.MoedaLimiteCredito);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MaiorSaldo != null) {
							sb_tFileOutputDelimited_1.append(row3.MaiorSaldo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MaiorCompra != null) {
							sb_tFileOutputDelimited_1.append(row3.MaiorCompra);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MediaAtraso != null) {
							sb_tFileOutputDelimited_1.append(row3.MediaAtraso);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.PrimeiraCompra != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row3.PrimeiraCompra, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.UltimaCompra != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row3.UltimaCompra, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.NumeroCompras != null) {
							sb_tFileOutputDelimited_1.append(row3.NumeroCompras);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FormularioVisita != null) {
							sb_tFileOutputDelimited_1.append(row3.FormularioVisita);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TempoVisita != null) {
							sb_tFileOutputDelimited_1.append(row3.TempoVisita);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.UltimaVisita != null) {
							sb_tFileOutputDelimited_1.append(row3.UltimaVisita);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TempoVisita1 != null) {
							sb_tFileOutputDelimited_1.append(row3.TempoVisita1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TempoPadrao != null) {
							sb_tFileOutputDelimited_1.append(row3.TempoPadrao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ClassificacaoVenda != null) {
							sb_tFileOutputDelimited_1.append(row3.ClassificacaoVenda);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Mensagem != null) {
							sb_tFileOutputDelimited_1.append(row3.Mensagem);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoTitulo != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoTitulo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheISS != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheISS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoPedidoLiberado != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoPedidoLiberado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.NumeroPagamentos != null) {
							sb_tFileOutputDelimited_1.append(row3.NumeroPagamentos);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Transferencia != null) {
							sb_tFileOutputDelimited_1.append(row3.Transferencia);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SUFRAMA != null) {
							sb_tFileOutputDelimited_1.append(row3.SUFRAMA);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ATR != null) {
							sb_tFileOutputDelimited_1.append(row3.ATR);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ValorAcumulado != null) {
							sb_tFileOutputDelimited_1.append(row3.ValorAcumulado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoPedido != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoPedido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TituloProtestado != null) {
							sb_tFileOutputDelimited_1.append(row3.TituloProtestado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataUltimoTitulo != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row3.DataUltimoTitulo, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ChequeDevolvido != null) {
							sb_tFileOutputDelimited_1.append(row3.ChequeDevolvido);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CaixaPostal != null) {
							sb_tFileOutputDelimited_1.append(row3.CaixaPostal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Matriz != null) {
							sb_tFileOutputDelimited_1.append(row3.Matriz);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataUltimoCheque != null) {
							sb_tFileOutputDelimited_1.append(row3.DataUltimoCheque);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MaiorDuplicata != null) {
							sb_tFileOutputDelimited_1.append(row3.MaiorDuplicata);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Tabela != null) {
							sb_tFileOutputDelimited_1.append(row3.Tabela);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ISSIncluso != null) {
							sb_tFileOutputDelimited_1.append(row3.ISSIncluso);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_1(final row3Struct row3, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row3.SaldoDuplicataMoeda != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoDuplicataMoeda);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.PagamentoAtrasado != null) {
							sb_tFileOutputDelimited_1.append(row3.PagamentoAtrasado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Atividade != null) {
							sb_tFileOutputDelimited_1.append(row3.Atividade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Cargo1 != null) {
							sb_tFileOutputDelimited_1.append(row3.Cargo1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Cargo2 != null) {
							sb_tFileOutputDelimited_1.append(row3.Cargo2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Cargo3 != null) {
							sb_tFileOutputDelimited_1.append(row3.Cargo3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RepresentanteTecnico != null) {
							sb_tFileOutputDelimited_1.append(row3.RepresentanteTecnico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Supervisor != null) {
							sb_tFileOutputDelimited_1.append(row3.Supervisor);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.AliquotaIR != null) {
							sb_tFileOutputDelimited_1.append(row3.AliquotaIR);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Observacao != null) {
							sb_tFileOutputDelimited_1.append(row3.Observacao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RG != null) {
							sb_tFileOutputDelimited_1.append(row3.RG);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CalculaSUFRAMA != null) {
							sb_tFileOutputDelimited_1.append(row3.CalculaSUFRAMA);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataNascimento != null) {
							sb_tFileOutputDelimited_1
									.append(FormatterUtils.format_Date(row3.DataNascimento, "dd-MM-yyyy"));
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoPedidoBloqueado != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoPedidoBloqueado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.GrupoTributario != null) {
							sb_tFileOutputDelimited_1.append(row3.GrupoTributario);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade1 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ClienteFaturamento != null) {
							sb_tFileOutputDelimited_1.append(row3.ClienteFaturamento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.EnderecoEntrega != null) {
							sb_tFileOutputDelimited_1.append(row3.EnderecoEntrega);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.UCompleto != null) {
							sb_tFileOutputDelimited_1.append(row3.UCompleto);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.BairroEntrega != null) {
							sb_tFileOutputDelimited_1.append(row3.BairroEntrega);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CEPEntrada != null) {
							sb_tFileOutputDelimited_1.append(row3.CEPEntrada);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.EstadoEntrada != null) {
							sb_tFileOutputDelimited_1.append(row3.EstadoEntrada);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoLocal != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoLocal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade2 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoPessoa != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoPessoa);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoISSRS != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoISSRS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade3 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade4 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade4);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade5 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade5);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade6 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade6);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade7 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade7);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoAgente != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoAgente);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade8 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade8);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoMarcacao != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoMarcacao);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ComissaoAgente != null) {
							sb_tFileOutputDelimited_1.append(row3.ComissaoAgente);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.HomePage != null) {
							sb_tFileOutputDelimited_1.append(row3.HomePage);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoMunicipio1 != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoMunicipio1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoCliente != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoCliente);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Email != null) {
							sb_tFileOutputDelimited_1.append(row3.Email);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Destino1 != null) {
							sb_tFileOutputDelimited_1.append(row3.Destino1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Destino2 != null) {
							sb_tFileOutputDelimited_1.append(row3.Destino2);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Destino3 != null) {
							sb_tFileOutputDelimited_1.append(row3.Destino3);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CBO != null) {
							sb_tFileOutputDelimited_1.append(row3.CBO);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Observacao1 != null) {
							sb_tFileOutputDelimited_1.append(row3.Observacao1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CNAE != null) {
							sb_tFileOutputDelimited_1.append(row3.CNAE);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CodigoHistorico != null) {
							sb_tFileOutputDelimited_1.append(row3.CodigoHistorico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CondicaoPagamento != null) {
							sb_tFileOutputDelimited_1.append(row3.CondicaoPagamento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DiasPagamento != null) {
							sb_tFileOutputDelimited_1.append(row3.DiasPagamento);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Agregado != null) {
							sb_tFileOutputDelimited_1.append(row3.Agregado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheINSS != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheINSS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheCOFINS != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheCOFINS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheCSLL != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheCSLL);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolhePIS != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolhePIS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoPeriodo != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoPeriodo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoFinal != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoFinal);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SaldoFinalMoeda != null) {
							sb_tFileOutputDelimited_1.append(row3.SaldoFinalMoeda);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Contabilidade != null) {
							sb_tFileOutputDelimited_1.append(row3.Contabilidade);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ClienteConvenio != null) {
							sb_tFileOutputDelimited_1.append(row3.ClienteConvenio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.B2B != null) {
							sb_tFileOutputDelimited_1.append(row3.B2B);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CBO1 != null) {
							sb_tFileOutputDelimited_1.append(row3.CBO1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CNAE1 != null) {
							sb_tFileOutputDelimited_1.append(row3.CNAE1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SegmentoAtividade11 != null) {
							sb_tFileOutputDelimited_1.append(row3.SegmentoAtividade11);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.MensagemBloqueio != null) {
							sb_tFileOutputDelimited_1.append(row3.MensagemBloqueio);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SubCodigo != null) {
							sb_tFileOutputDelimited_1.append(row3.SubCodigo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FilialDebito != null) {
							sb_tFileOutputDelimited_1.append(row3.FilialDebito);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolhePIS1 != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolhePIS1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheCOFINS1 != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheCOFINS1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheCSLL1 != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheCSLL1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ABICS != null) {
							sb_tFileOutputDelimited_1.append(row3.ABICS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Vinculo != null) {
							sb_tFileOutputDelimited_1.append(row3.Vinculo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataInicioVinculo != null) {
							sb_tFileOutputDelimited_1.append(row3.DataInicioVinculo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataFimVinculo != null) {
							sb_tFileOutputDelimited_1.append(row3.DataFimVinculo);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ISSRSLC != null) {
							sb_tFileOutputDelimited_1.append(row3.ISSRSLC);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoRegistro != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoRegistro);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RFASEMT != null) {
							sb_tFileOutputDelimited_1.append(row3.RFASEMT);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RIMAMT != null) {
							sb_tFileOutputDelimited_1.append(row3.RIMAMT);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.REGESIM != null) {
							sb_tFileOutputDelimited_1.append(row3.REGESIM);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ContaReceita != null) {
							sb_tFileOutputDelimited_1.append(row3.ContaReceita);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.SIMPNacional != null) {
							sb_tFileOutputDelimited_1.append(row3.SIMPNacional);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheFETHAB != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheFETHAB);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RFABOV != null) {
							sb_tFileOutputDelimited_1.append(row3.RFABOV);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RFACS != null) {
							sb_tFileOutputDelimited_1.append(row3.RFACS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.DataNascimento1 != null) {
							sb_tFileOutputDelimited_1.append(row3.DataNascimento1);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Contribuinte != null) {
							sb_tFileOutputDelimited_1.append(row3.Contribuinte);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecolheFMD != null) {
							sb_tFileOutputDelimited_1.append(row3.RecolheFMD);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TipoJuridico != null) {
							sb_tFileOutputDelimited_1.append(row3.TipoJuridico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.INCULT != null) {
							sb_tFileOutputDelimited_1.append(row3.INCULT);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.IDHistorico != null) {
							sb_tFileOutputDelimited_1.append(row3.IDHistorico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.PrestacaoServico != null) {
							sb_tFileOutputDelimited_1.append(row3.PrestacaoServico);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.NUMRA != null) {
							sb_tFileOutputDelimited_1.append(row3.NUMRA);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
					}

					public void putValue_2(final row3Struct row3, StringBuilder sb_tFileOutputDelimited_1,
							final String OUT_DELIM_tFileOutputDelimited_1) throws java.lang.Exception {
						if (row3.MINIRF != null) {
							sb_tFileOutputDelimited_1.append(row3.MINIRF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.CODSIAF != null) {
							sb_tFileOutputDelimited_1.append(row3.CODSIAF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.ENDNOT != null) {
							sb_tFileOutputDelimited_1.append(row3.ENDNOT);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FOMEZER != null) {
							sb_tFileOutputDelimited_1.append(row3.FOMEZER);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FRETISS != null) {
							sb_tFileOutputDelimited_1.append(row3.FRETISS);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.COMPLEM != null) {
							sb_tFileOutputDelimited_1.append(row3.COMPLEM);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.INCLTMG != null) {
							sb_tFileOutputDelimited_1.append(row3.INCLTMG);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.FILTRF != null) {
							sb_tFileOutputDelimited_1.append(row3.FILTRF);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.TRIBFAV != null) {
							sb_tFileOutputDelimited_1.append(row3.TRIBFAV);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.REGPB != null) {
							sb_tFileOutputDelimited_1.append(row3.REGPB);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.INOVAUT != null) {
							sb_tFileOutputDelimited_1.append(row3.INOVAUT);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Deletado != null) {
							sb_tFileOutputDelimited_1.append(row3.Deletado);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.Ukey != null) {
							sb_tFileOutputDelimited_1.append(row3.Ukey);
						}
						sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
						if (row3.RecDeletado != null) {
							sb_tFileOutputDelimited_1.append(row3.RecDeletado);
						}
					}
				}
				FileOutputDelimitedUtil_tFileOutputDelimited_1 fileOutputDelimitedUtil_tFileOutputDelimited_1 = new FileOutputDelimitedUtil_tFileOutputDelimited_1();
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/joao.santos/OneDrive - hydronorth.com.br/Área de Trabalho/QVD/Clientes.qvd"))
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

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "Ajuste_data");

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
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

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
				int count_row1_tMap_1 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_Ajuste_data_tMap_1 = 0;

				Ajuste_dataStruct Ajuste_data_tmp = new Ajuste_dataStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				cLabel = "InputClientes";

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
									"enc:routine.encryption.key.v1:2hwC2AI37Wwu0d1tvFs2/vvJUNq7gfexhiD89mlmQ9cj87rloNE9TclQ")
									.substring(0, 4) + "...");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("TABLE" + " = " + "\"SA1010\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_1.append(" | ");
							log4jParamters_tDBInput_1.append("QUERY" + " = "
									+ "\"SELECT       A1_FILIAL AS Filial,      A1_COD AS Codigo,      A1_LOJA AS Loja,      A1_TIPO AS Tipo,      A1_PESSOA AS Pessoa,      A1_NATUREZ AS Natureza,      A1_CGC AS CNPJ,      A1_NOME AS Nome,      A1_NREDUZ AS NomeReduzido,      A1_END AS Endereco,      A1_COMPLEM AS Complemento,      A1_BAIRRO AS Bairro,      A1_CEP AS CEP,      A1_EST AS Estado,      A1_REGIAO AS Regiao,      A1_DSCREG AS DescricaoRegiao,      A1_COD_MUN AS CodigoMunicipio,      A1_MUN AS Municipio,      A1_CODPAIS AS CodigoPais,      A1_ESTADO AS NomeEstado,      A1_DDD AS DDD,      A1_TRIBFAV AS TributacaoFavoravel,      A1_DDI AS DDI,      A1_IBGE AS CodIBGE,      A1_UCODMUC AS UCodigoMunicipio,      A1_ENDREC AS EnderecoRecebimento,      A1_TEL AS Telefone,      A1_FAX AS FAX,      A1_FONECP AS FoneCP,      A1_CONTATO AS Contato,      A1_PFISICA AS PessoaFisica,      A1_PAIS AS Pais,      A1_INSCR AS Inscricao,      A1_INSCRM AS InscricaoMunicipal,      A1_VEND AS Vendedor,      A1_COMIS AS Comissao,      A1_CONTA AS Conta,      A1_BCO1 AS Banco1,      A1_BCO2 AS Banco2,      A1_BCO3 AS Banco3,      A1_BCO4 AS Banco4,      A1_BCO5 AS Banco5,      A1_TRANSP AS Transporte,      A1_TPFRET AS TipoFrete,      A1_ENDCOB AS EnderecoCobranca,      A1_COND AS Condicao,      A1_BAIRROC AS BairroCobranca,      A1_DESC AS Descricao,      A1_CEPC AS CEPCobranca,      A1_ESTC AS EstadoCobranca,      A1_UCODMCO AS UCodigoMunicipioCobranca,      A1_MUNC AS MunicipioCobranca,      A1_PRIOR AS Prioridade,      A1_RISCO AS Risco,      A1_LC AS LimiteCredito,      A1_VENCLC AS VencimentoLimiteCredito,      A1_CLASSE AS Classe,      A1_LCFIN AS LimiteCreditoFinal,      A1_MOEDALC AS MoedaLimiteCredito,      A1_MSALDO AS MaiorSaldo,      A1_MCOMPRA AS MaiorCompra,      A1_METR AS MediaAtraso,      A1_PRICOM AS PrimeiraCompra,      A1_ULTCOM AS UltimaCompra,      A1_NROCOM AS NumeroCompras,      A1_FORMVIS AS FormularioVisita,      A1_TEMVIS AS TempoVisita,      A1_ULTVIS AS UltimaVisita,      A1_TMPVIS AS TempoVisita,      A1_TMPSTD AS TempoPadrao,      A1_CLASVEN AS ClassificacaoVenda,      A1_MENSAGE AS Mensagem,      A1_SALDUP AS SaldoTitulo,      A1_RECISS AS RecolheISS,      A1_SALPEDL AS SaldoPedidoLiberado,      A1_NROPAG AS NumeroPagamentos,      A1_TRANSF AS Transferencia,      A1_SUFRAMA AS SUFRAMA,      A1_ATR AS ATR,      A1_VACUM AS ValorAcumulado,      A1_SALPED AS SaldoPedido,      A1_TITPROT AS TituloProtestado,      A1_DTULTIT AS DataUltimoTitulo,      A1_CHQDEVO AS ChequeDevolvido,      A1_CXPOSTA AS CaixaPostal,      A1_MATR AS Matriz,      A1_DTULCHQ AS DataUltimoCheque,      A1_MAIDUPL AS MaiorDuplicata,      A1_TABELA AS Tabela,      A1_INCISS AS ISSIncluso,      A1_SALDUPM AS SaldoDuplicataMoeda,      A1_PAGATR AS PagamentoAtrasado,      A1_ATIVIDA AS Atividade,      A1_CARGO1 AS Cargo1,      A1_CARGO2 AS Cargo2,      A1_CARGO3 AS Cargo3,      A1_RTEC AS RepresentanteTecnico,      A1_SUPER AS Supervisor,      A1_ALIQIR AS AliquotaIR,      A1_OBSERV AS Observacao,      A1_RG AS RG,      A1_CALCSUF AS CalculaSUFRAMA,      A1_DTNASC AS DataNascimento,      A1_SALPEDB AS SaldoPedidoBloqueado,      A1_GRPTRIB AS GrupoTributario,      A1_SATIV1 AS SegmentoAtividade1,      A1_CLIFAT AS ClienteFaturamento,      A1_ENDENT AS EnderecoEntrega,      A1_UCOMPLE AS UCompleto,      A1_BAIRROE AS BairroEntrega,      A1_CEPE AS CEPEntrada,      A1_ESTE AS EstadoEntrada,      A1_CODLOC AS CodigoLocal,      A1_SATIV2 AS SegmentoAtividade2,      A1_TPESSOA AS TipoPessoa,      A1_TPISSRS AS TipoISSRS,      A1_SATIV3 AS SegmentoAtividade3,      A1_SATIV4 AS SegmentoAtividade4,      A1_SATIV5 AS SegmentoAtividade5,      A1_SATIV6 AS SegmentoAtividade6,      A1_SATIV7 AS SegmentoAtividade7,      A1_CODAGE AS CodigoAgente,      A1_SATIV8 AS SegmentoAtividade8,      A1_CODMARC AS CodigoMarcacao,      A1_COMAGE AS ComissaoAgente,      A1_HPAGE AS HomePage,      A1_CODMUN AS CodigoMunicipio,      A1_TIPCLI AS TipoCliente,      A1_EMAIL AS Email,      A1_DEST_1 AS Destino1,      A1_DEST_2 AS Destino2,      A1_DEST_3 AS Destino3,      A1_CBO AS CBO,      A1_OBS AS Observacao,      A1_CNAE AS CNAE,      A1_CODHIST AS CodigoHistorico,      A1_CONDPAG AS CondicaoPagamento,      A1_DIASPAG AS DiasPagamento,      A1_AGREG AS Agregado,      A1_RECINSS AS RecolheINSS,      A1_RECCOFI AS RecolheCOFINS,      A1_RECCSLL AS RecolheCSLL,      A1_RECPIS AS RecolhePIS,      A1_TIPPER AS TipoPeriodo,      A1_SALFIN AS SaldoFinal,      A1_SALFINM AS SaldoFinalMoeda,      A1_CONTAB AS Contabilidade,      A1_CLICNV AS ClienteConvenio,  	A1_B2B AS B2B,      A1_CBO AS CBO,      A1_CNAE AS CNAE,      A1_SATIV1 AS SegmentoAtividade1,      A1_MSBLQL AS MensagemBloqueio,      A1_SUBCOD AS SubCodigo,      A1_FILDEB AS FilialDebito,      A1_RECPIS AS RecolhePIS,      A1_RECCOFI AS RecolheCOFINS,      A1_RECCSLL AS RecolheCSLL,      A1_ABICS AS ABICS,      A1_VINCULO AS Vinculo,      A1_DTINIV AS DataInicioVinculo,      A1_DTFIMV AS DataFimVinculo,      A1_ISSRSLC AS ISSRSLC,      A1_TPREG AS TipoRegistro,      A1_RFASEMT AS RFASEMT,      A1_RIMAMT AS RIMAMT,      A1_REGESIM AS REGESIM,      A1_CTARE AS ContaReceita,      A1_SIMPNAC AS SIMPNacional,      A1_RECFET AS RecolheFETHAB,      A1_RFABOV AS RFABOV,      A1_RFACS AS RFACS,      A1_DTNASC AS DataNascimento,      A1_CONTRIB AS Contribuinte,      A1_RECFMD AS RecolheFMD,      A1_TPJ AS TipoJuridico,      A1_INCULT AS INCULT,      A1_IDHIST AS IDHistorico,      A1_PRSTSER AS PrestacaoServico,      A1_NUMRA AS NUMRA,      A1_MINIRF AS MINIRF,      A1_CODSIAF AS CODSIAF,      A1_ENDNOT AS ENDNOT,      A1_FOMEZER AS FOMEZER,      A1_FRETISS AS FRETISS,      A1_COMPLEM AS COMPLEM,      A1_INCLTMG AS INCLTMG,      A1_FILTRF AS FILTRF,      A1_TRIBFAV AS TRIBFAV,      A1_REGPB AS REGPB,      A1_INOVAUT AS INOVAUT,  	A1_UPZMPRA  AS PrazoMedio,  	A1_UCODCAN AS CodigoCanal,  	A1_UCODSEG AS CodigoSegmento,      D_E_L_E_T_ AS Deletado,      R_E_C_N_O_ AS Ukey,      R_E_C_D_E_L_ AS RecDeletado  FROM       SA1010\"");
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
									+ ("Codigo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Loja") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Tipo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Pessoa") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Natureza") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CNPJ")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Nome") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("NomeReduzido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Endereco") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Complemento") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Bairro")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CEP") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Estado") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Regiao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DescricaoRegiao")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodigoMunicipio") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Municipio") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CodigoPais") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("NomeEstado") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DDD")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TributacaoFavoravel")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DDI") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CodIBGE") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("UCodigoMunicipio") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("EnderecoRecebimento") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Telefone") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("FAX") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("FoneCP") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Contato") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PessoaFisica") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Pais")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Inscricao") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("InscricaoMunicipal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Vendedor") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Comissao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Conta")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Banco1") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Banco2") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Banco3") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Banco4")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Banco5") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Transporte") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("TipoFrete") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EnderecoCobranca")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Condicao") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("BairroCobranca") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Descricao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CEPCobranca") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EstadoCobranca")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("UCodigoMunicipioCobranca")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("MunicipioCobranca") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Prioridade") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Risco") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("LimiteCredito") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("VencimentoLimiteCredito") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Classe") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("LimiteCreditoFinal")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("MoedaLimiteCredito") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("MaiorSaldo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("MaiorCompra") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("MediaAtraso") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PrimeiraCompra")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("UltimaCompra") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("NumeroCompras") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("FormularioVisita") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("TempoVisita") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("UltimaVisita") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TempoVisita1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TempoPadrao") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ClassificacaoVenda") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Mensagem") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SaldoTitulo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecolheISS")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SaldoPedidoLiberado")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("NumeroPagamentos") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Transferencia") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SUFRAMA") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ATR") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ValorAcumulado")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SaldoPedido") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("TituloProtestado") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DataUltimoTitulo") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ChequeDevolvido") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CaixaPostal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Matriz") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataUltimoCheque")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("MaiorDuplicata") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("Tabela") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ISSIncluso") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SaldoDuplicataMoeda") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PagamentoAtrasado") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Atividade") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Cargo1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Cargo2") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Cargo3") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("RepresentanteTecnico") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Supervisor") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("AliquotaIR")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Observacao") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("RG") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CalculaSUFRAMA") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("DataNascimento") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SaldoPedidoBloqueado") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("GrupoTributario") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SegmentoAtividade1") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ClienteFaturamento") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("EnderecoEntrega") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("UCompleto") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("BairroEntrega") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CEPEntrada")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("EstadoEntrada") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("CodigoLocal") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SegmentoAtividade2") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("TipoPessoa") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("TipoISSRS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade3") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade4") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade5") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade6") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade7") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CodigoAgente") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SegmentoAtividade8") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CodigoMarcacao") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ComissaoAgente") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("HomePage")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodigoMunicipio1") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("TipoCliente") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Email") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Destino1") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Destino2")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Destino3") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("CBO") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Observacao1") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CNAE") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CodigoHistorico")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CondicaoPagamento") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("DiasPagamento") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Agregado") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("RecolheINSS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecolheCOFINS")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecolheCSLL") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("RecolhePIS") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("TipoPeriodo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("SaldoFinal") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("SaldoFinalMoeda")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Contabilidade") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ClienteConvenio") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("B2B") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("CBO1") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CNAE1") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("SegmentoAtividade11") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("MensagemBloqueio") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("SubCodigo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("FilialDebito") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecolhePIS1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecolheCOFINS1") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("RecolheCSLL1") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("ABICS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Vinculo") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataInicioVinculo")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataFimVinculo") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("ISSRSLC") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("TipoRegistro") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("RFASEMT") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("RIMAMT") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("REGESIM")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("ContaReceita") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("SIMPNacional") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("RecolheFETHAB") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("RFABOV") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("RFACS") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("DataNascimento1")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Contribuinte") + "}, {TRIM="
									+ ("true") + ", SCHEMA_COLUMN=" + ("RecolheFMD") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("TipoJuridico") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("INCULT") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("IDHistorico") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("PrestacaoServico") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("NUMRA")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("MINIRF") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("CODSIAF") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("ENDNOT") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("FOMEZER")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("FRETISS") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("COMPLEM") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("INCLTMG") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("FILTRF")
									+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TRIBFAV") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("REGPB") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("INOVAUT") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("PrazoMedio")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("CodigoCanal") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("CodigoSegmento") + "}, {TRIM=" + ("true")
									+ ", SCHEMA_COLUMN=" + ("Deletado") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN="
									+ ("Ukey") + "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("RecDeletado") + "}]");
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
					talendJobLog.addCM("tDBInput_1", "InputClientes", "tMSSqlInput");
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
						"enc:routine.encryption.key.v1:8Oh4TYHpSRK3qgQgGreP5Y6x/DQ/t+td3pc5472sKGXH/2rQiMxao43H");

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

				String dbquery_tDBInput_1 = "SELECT \n    A1_FILIAL AS Filial,\n    A1_COD AS Codigo,\n    A1_LOJA AS Loja,\n    A1_TIPO AS Tipo,\n    A1_PESSOA AS "
						+ "Pessoa,\n    A1_NATUREZ AS Natureza,\n    A1_CGC AS CNPJ,\n    A1_NOME AS Nome,\n    A1_NREDUZ AS NomeReduzido,\n    A1_"
						+ "END AS Endereco,\n    A1_COMPLEM AS Complemento,\n    A1_BAIRRO AS Bairro,\n    A1_CEP AS CEP,\n    A1_EST AS Estado,\n "
						+ "   A1_REGIAO AS Regiao,\n    A1_DSCREG AS DescricaoRegiao,\n    A1_COD_MUN AS CodigoMunicipio,\n    A1_MUN AS Municipio,"
						+ "\n    A1_CODPAIS AS CodigoPais,\n    A1_ESTADO AS NomeEstado,\n    A1_DDD AS DDD,\n    A1_TRIBFAV AS TributacaoFavoravel"
						+ ",\n    A1_DDI AS DDI,\n    A1_IBGE AS CodIBGE,\n    A1_UCODMUC AS UCodigoMunicipio,\n    A1_ENDREC AS EnderecoRecebiment"
						+ "o,\n    A1_TEL AS Telefone,\n    A1_FAX AS FAX,\n    A1_FONECP AS FoneCP,\n    A1_CONTATO AS Contato,\n    A1_PFISICA AS"
						+ " PessoaFisica,\n    A1_PAIS AS Pais,\n    A1_INSCR AS Inscricao,\n    A1_INSCRM AS InscricaoMunicipal,\n    A1_VEND AS V"
						+ "endedor,\n    A1_COMIS AS Comissao,\n    A1_CONTA AS Conta,\n    A1_BCO1 AS Banco1,\n    A1_BCO2 AS Banco2,\n    A1_BCO3"
						+ " AS Banco3,\n    A1_BCO4 AS Banco4,\n    A1_BCO5 AS Banco5,\n    A1_TRANSP AS Transporte,\n    A1_TPFRET AS TipoFrete,\n"
						+ "    A1_ENDCOB AS EnderecoCobranca,\n    A1_COND AS Condicao,\n    A1_BAIRROC AS BairroCobranca,\n    A1_DESC AS Descrica"
						+ "o,\n    A1_CEPC AS CEPCobranca,\n    A1_ESTC AS EstadoCobranca,\n    A1_UCODMCO AS UCodigoMunicipioCobranca,\n    A1_MUN"
						+ "C AS MunicipioCobranca,\n    A1_PRIOR AS Prioridade,\n    A1_RISCO AS Risco,\n    A1_LC AS LimiteCredito,\n    A1_VENCLC"
						+ " AS VencimentoLimiteCredito,\n    A1_CLASSE AS Classe,\n    A1_LCFIN AS LimiteCreditoFinal,\n    A1_MOEDALC AS MoedaLimi"
						+ "teCredito,\n    A1_MSALDO AS MaiorSaldo,\n    A1_MCOMPRA AS MaiorCompra,\n    A1_METR AS MediaAtraso,\n    A1_PRICOM AS "
						+ "PrimeiraCompra,\n    A1_ULTCOM AS UltimaCompra,\n    A1_NROCOM AS NumeroCompras,\n    A1_FORMVIS AS FormularioVisita,\n "
						+ "   A1_TEMVIS AS TempoVisita,\n    A1_ULTVIS AS UltimaVisita,\n    A1_TMPVIS AS TempoVisita,\n    A1_TMPSTD AS TempoPadra"
						+ "o,\n    A1_CLASVEN AS ClassificacaoVenda,\n    A1_MENSAGE AS Mensagem,\n    A1_SALDUP AS SaldoTitulo,\n    A1_RECISS AS "
						+ "RecolheISS,\n    A1_SALPEDL AS SaldoPedidoLiberado,\n    A1_NROPAG AS NumeroPagamentos,\n    A1_TRANSF AS Transferencia,"
						+ "\n    A1_SUFRAMA AS SUFRAMA,\n    A1_ATR AS ATR,\n    A1_VACUM AS ValorAcumulado,\n    A1_SALPED AS SaldoPedido,\n    A1"
						+ "_TITPROT AS TituloProtestado,\n    A1_DTULTIT AS DataUltimoTitulo,\n    A1_CHQDEVO AS ChequeDevolvido,\n    A1_CXPOSTA A"
						+ "S CaixaPostal,\n    A1_MATR AS Matriz,\n    A1_DTULCHQ AS DataUltimoCheque,\n    A1_MAIDUPL AS MaiorDuplicata,\n    A1_T"
						+ "ABELA AS Tabela,\n    A1_INCISS AS ISSIncluso,\n    A1_SALDUPM AS SaldoDuplicataMoeda,\n    A1_PAGATR AS PagamentoAtrasa"
						+ "do,\n    A1_ATIVIDA AS Atividade,\n    A1_CARGO1 AS Cargo1,\n    A1_CARGO2 AS Cargo2,\n    A1_CARGO3 AS Cargo3,\n    A1_"
						+ "RTEC AS RepresentanteTecnico,\n    A1_SUPER AS Supervisor,\n    A1_ALIQIR AS AliquotaIR,\n    A1_OBSERV AS Observacao,\n"
						+ "    A1_RG AS RG,\n    A1_CALCSUF AS CalculaSUFRAMA,\n    A1_DTNASC AS DataNascimento,\n    A1_SALPEDB AS SaldoPedidoBloq"
						+ "ueado,\n    A1_GRPTRIB AS GrupoTributario,\n    A1_SATIV1 AS SegmentoAtividade1,\n    A1_CLIFAT AS ClienteFaturamento,\n"
						+ "    A1_ENDENT AS EnderecoEntrega,\n    A1_UCOMPLE AS UCompleto,\n    A1_BAIRROE AS BairroEntrega,\n    A1_CEPE AS CEPEnt"
						+ "rada,\n    A1_ESTE AS EstadoEntrada,\n    A1_CODLOC AS CodigoLocal,\n    A1_SATIV2 AS SegmentoAtividade2,\n    A1_TPESSO"
						+ "A AS TipoPessoa,\n    A1_TPISSRS AS TipoISSRS,\n    A1_SATIV3 AS SegmentoAtividade3,\n    A1_SATIV4 AS SegmentoAtividade"
						+ "4,\n    A1_SATIV5 AS SegmentoAtividade5,\n    A1_SATIV6 AS SegmentoAtividade6,\n    A1_SATIV7 AS SegmentoAtividade7,\n  "
						+ "  A1_CODAGE AS CodigoAgente,\n    A1_SATIV8 AS SegmentoAtividade8,\n    A1_CODMARC AS CodigoMarcacao,\n    A1_COMAGE AS "
						+ "ComissaoAgente,\n    A1_HPAGE AS HomePage,\n    A1_CODMUN AS CodigoMunicipio,\n    A1_TIPCLI AS TipoCliente,\n    A1_EMA"
						+ "IL AS Email,\n    A1_DEST_1 AS Destino1,\n    A1_DEST_2 AS Destino2,\n    A1_DEST_3 AS Destino3,\n    A1_CBO AS CBO,\n  "
						+ "  A1_OBS AS Observacao,\n    A1_CNAE AS CNAE,\n    A1_CODHIST AS CodigoHistorico,\n    A1_CONDPAG AS CondicaoPagamento,"
						+ "\n    A1_DIASPAG AS DiasPagamento,\n    A1_AGREG AS Agregado,\n    A1_RECINSS AS RecolheINSS,\n    A1_RECCOFI AS RecolheC"
						+ "OFINS,\n    A1_RECCSLL AS RecolheCSLL,\n    A1_RECPIS AS RecolhePIS,\n    A1_TIPPER AS TipoPeriodo,\n    A1_SALFIN AS Sa"
						+ "ldoFinal,\n    A1_SALFINM AS SaldoFinalMoeda,\n    A1_CONTAB AS Contabilidade,\n    A1_CLICNV AS ClienteConvenio,\n	A1_B"
						+ "2B AS B2B,\n    A1_CBO AS CBO,\n    A1_CNAE AS CNAE,\n    A1_SATIV1 AS SegmentoAtividade1,\n    A1_MSBLQL AS MensagemBlo"
						+ "queio,\n    A1_SUBCOD AS SubCodigo,\n    A1_FILDEB AS FilialDebito,\n    A1_RECPIS AS RecolhePIS,\n    A1_RECCOFI AS Rec"
						+ "olheCOFINS,\n    A1_RECCSLL AS RecolheCSLL,\n    A1_ABICS AS ABICS,\n    A1_VINCULO AS Vinculo,\n    A1_DTINIV AS DataIn"
						+ "icioVinculo,\n    A1_DTFIMV AS DataFimVinculo,\n    A1_ISSRSLC AS ISSRSLC,\n    A1_TPREG AS TipoRegistro,\n    A1_RFASEM"
						+ "T AS RFASEMT,\n    A1_RIMAMT AS RIMAMT,\n    A1_REGESIM AS REGESIM,\n    A1_CTARE AS ContaReceita,\n    A1_SIMPNAC AS SI"
						+ "MPNacional,\n    A1_RECFET AS RecolheFETHAB,\n    A1_RFABOV AS RFABOV,\n    A1_RFACS AS RFACS,\n    A1_DTNASC AS DataNas"
						+ "cimento,\n    A1_CONTRIB AS Contribuinte,\n    A1_RECFMD AS RecolheFMD,\n    A1_TPJ AS TipoJuridico,\n    A1_INCULT AS I"
						+ "NCULT,\n    A1_IDHIST AS IDHistorico,\n    A1_PRSTSER AS PrestacaoServico,\n    A1_NUMRA AS NUMRA,\n    A1_MINIRF AS MIN"
						+ "IRF,\n    A1_CODSIAF AS CODSIAF,\n    A1_ENDNOT AS ENDNOT,\n    A1_FOMEZER AS FOMEZER,\n    A1_FRETISS AS FRETISS,\n    "
						+ "A1_COMPLEM AS COMPLEM,\n    A1_INCLTMG AS INCLTMG,\n    A1_FILTRF AS FILTRF,\n    A1_TRIBFAV AS TRIBFAV,\n    A1_REGPB A"
						+ "S REGPB,\n    A1_INOVAUT AS INOVAUT,\n	A1_UPZMPRA  AS PrazoMedio,\n	A1_UCODCAN AS CodigoCanal,\n	A1_UCODSEG AS CodigoSeg"
						+ "mento,\n    D_E_L_E_T_ AS Deletado,\n    R_E_C_N_O_ AS Ukey,\n    R_E_C_D_E_L_ AS RecDeletado\nFROM \n    SA1010";

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
							row1.Codigo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(2);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(2).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Codigo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Codigo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Codigo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							row1.Loja = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(3);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(3).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Loja = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Loja = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Loja = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							row1.Tipo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(4);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(4).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Tipo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Tipo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Tipo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							row1.Pessoa = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(5);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(5).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Pessoa = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Pessoa = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Pessoa = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 6) {
							row1.Natureza = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(6);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(6).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Natureza = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Natureza = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Natureza = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 7) {
							row1.CNPJ = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(7);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(7).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CNPJ = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CNPJ = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CNPJ = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 8) {
							row1.Nome = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(8);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Nome = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Nome = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Nome = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 9) {
							row1.NomeReduzido = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(9);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NomeReduzido = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NomeReduzido = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NomeReduzido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 10) {
							row1.Endereco = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(10);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Endereco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Endereco = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Endereco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 11) {
							row1.Complemento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(11);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(11).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Complemento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Complemento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Complemento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 12) {
							row1.Bairro = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(12);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(12).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Bairro = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Bairro = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Bairro = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 13) {
							row1.CEP = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(13);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(13).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CEP = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CEP = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CEP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 14) {
							row1.Estado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(14);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(14).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Estado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Estado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Estado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 15) {
							row1.Regiao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(15);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(15).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Regiao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Regiao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Regiao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 16) {
							row1.DescricaoRegiao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(16);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(16).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DescricaoRegiao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DescricaoRegiao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DescricaoRegiao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 17) {
							row1.CodigoMunicipio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(17);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(17).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoMunicipio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoMunicipio = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoMunicipio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 18) {
							row1.Municipio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(18);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Municipio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Municipio = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Municipio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 19) {
							row1.CodigoPais = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(19);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(19).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoPais = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoPais = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoPais = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 20) {
							row1.NomeEstado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(20);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NomeEstado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NomeEstado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NomeEstado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 21) {
							row1.DDD = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(21);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DDD = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DDD = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DDD = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 22) {
							row1.TributacaoFavoravel = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(22);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TributacaoFavoravel = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TributacaoFavoravel = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TributacaoFavoravel = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 23) {
							row1.DDI = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(23);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DDI = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DDI = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DDI = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 24) {
							row1.CodIBGE = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(24);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodIBGE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodIBGE = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodIBGE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 25) {
							row1.UCodigoMunicipio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(25);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(25).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UCodigoMunicipio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UCodigoMunicipio = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UCodigoMunicipio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 26) {
							row1.EnderecoRecebimento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(26);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(26).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EnderecoRecebimento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EnderecoRecebimento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EnderecoRecebimento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 27) {
							row1.Telefone = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(27);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(27).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Telefone = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Telefone = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Telefone = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 28) {
							row1.FAX = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(28);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(28).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FAX = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FAX = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FAX = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 29) {
							row1.FoneCP = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(29);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(29).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FoneCP = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FoneCP = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FoneCP = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 30) {
							row1.Contato = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(30);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(30).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Contato = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Contato = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Contato = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 31) {
							row1.PessoaFisica = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(31);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(31).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PessoaFisica = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PessoaFisica = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.PessoaFisica = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 32) {
							row1.Pais = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(32);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(32).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Pais = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Pais = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Pais = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 33) {
							row1.Inscricao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(33);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(33).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Inscricao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Inscricao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Inscricao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 34) {
							row1.InscricaoMunicipal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(34);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(34).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.InscricaoMunicipal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.InscricaoMunicipal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.InscricaoMunicipal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 35) {
							row1.Vendedor = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(35);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(35).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Vendedor = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Vendedor = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Vendedor = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 36) {
							row1.Comissao = null;
						} else {

							row1.Comissao = rs_tDBInput_1.getDouble(36);
							if (rs_tDBInput_1.wasNull()) {
								row1.Comissao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 37) {
							row1.Conta = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(37);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(37).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Conta = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Conta = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Conta = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 38) {
							row1.Banco1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(38);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(38).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Banco1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Banco1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Banco1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 39) {
							row1.Banco2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(39);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(39).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Banco2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Banco2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Banco2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 40) {
							row1.Banco3 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(40);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(40).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Banco3 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Banco3 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Banco3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 41) {
							row1.Banco4 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(41);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(41).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Banco4 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Banco4 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Banco4 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 42) {
							row1.Banco5 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(42);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(42).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Banco5 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Banco5 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Banco5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 43) {
							row1.Transporte = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(43);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(43).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Transporte = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Transporte = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Transporte = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 44) {
							row1.TipoFrete = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(44);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(44).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoFrete = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoFrete = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoFrete = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 45) {
							row1.EnderecoCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(45);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(45).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EnderecoCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EnderecoCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EnderecoCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 46) {
							row1.Condicao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(46);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(46).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Condicao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Condicao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Condicao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 47) {
							row1.BairroCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(47);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(47).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.BairroCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.BairroCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.BairroCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 48) {
							row1.Descricao = null;
						} else {

							row1.Descricao = rs_tDBInput_1.getDouble(48);
							if (rs_tDBInput_1.wasNull()) {
								row1.Descricao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 49) {
							row1.CEPCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(49);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(49).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CEPCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CEPCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CEPCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 50) {
							row1.EstadoCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(50);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(50).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EstadoCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EstadoCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EstadoCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 51) {
							row1.UCodigoMunicipioCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(51);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(51).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UCodigoMunicipioCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UCodigoMunicipioCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UCodigoMunicipioCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 52) {
							row1.MunicipioCobranca = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(52);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(52).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.MunicipioCobranca = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.MunicipioCobranca = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.MunicipioCobranca = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 53) {
							row1.Prioridade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(53);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(53).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Prioridade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Prioridade = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Prioridade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 54) {
							row1.Risco = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(54);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(54).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Risco = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Risco = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Risco = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 55) {
							row1.LimiteCredito = null;
						} else {

							row1.LimiteCredito = rs_tDBInput_1.getDouble(55);
							if (rs_tDBInput_1.wasNull()) {
								row1.LimiteCredito = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 56) {
							row1.VencimentoLimiteCredito = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(56);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(56).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.VencimentoLimiteCredito = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.VencimentoLimiteCredito = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.VencimentoLimiteCredito = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 57) {
							row1.Classe = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(57);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(57).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Classe = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Classe = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Classe = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 58) {
							row1.LimiteCreditoFinal = null;
						} else {

							row1.LimiteCreditoFinal = rs_tDBInput_1.getDouble(58);
							if (rs_tDBInput_1.wasNull()) {
								row1.LimiteCreditoFinal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 59) {
							row1.MoedaLimiteCredito = null;
						} else {

							row1.MoedaLimiteCredito = rs_tDBInput_1.getDouble(59);
							if (rs_tDBInput_1.wasNull()) {
								row1.MoedaLimiteCredito = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 60) {
							row1.MaiorSaldo = null;
						} else {

							row1.MaiorSaldo = rs_tDBInput_1.getDouble(60);
							if (rs_tDBInput_1.wasNull()) {
								row1.MaiorSaldo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 61) {
							row1.MaiorCompra = null;
						} else {

							row1.MaiorCompra = rs_tDBInput_1.getDouble(61);
							if (rs_tDBInput_1.wasNull()) {
								row1.MaiorCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 62) {
							row1.MediaAtraso = null;
						} else {

							row1.MediaAtraso = rs_tDBInput_1.getDouble(62);
							if (rs_tDBInput_1.wasNull()) {
								row1.MediaAtraso = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 63) {
							row1.PrimeiraCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(63);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(63).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PrimeiraCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PrimeiraCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.PrimeiraCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 64) {
							row1.UltimaCompra = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(64);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(64).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UltimaCompra = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UltimaCompra = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UltimaCompra = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 65) {
							row1.NumeroCompras = null;
						} else {

							row1.NumeroCompras = rs_tDBInput_1.getDouble(65);
							if (rs_tDBInput_1.wasNull()) {
								row1.NumeroCompras = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 66) {
							row1.FormularioVisita = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(66);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(66).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FormularioVisita = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FormularioVisita = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FormularioVisita = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 67) {
							row1.TempoVisita = null;
						} else {

							row1.TempoVisita = rs_tDBInput_1.getDouble(67);
							if (rs_tDBInput_1.wasNull()) {
								row1.TempoVisita = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 68) {
							row1.UltimaVisita = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(68);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(68).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UltimaVisita = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UltimaVisita = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UltimaVisita = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 69) {
							row1.TempoVisita1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(69);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(69).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TempoVisita1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TempoVisita1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TempoVisita1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 70) {
							row1.TempoPadrao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(70);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(70).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TempoPadrao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TempoPadrao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TempoPadrao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 71) {
							row1.ClassificacaoVenda = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(71);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(71).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ClassificacaoVenda = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ClassificacaoVenda = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ClassificacaoVenda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 72) {
							row1.Mensagem = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(72);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(72).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Mensagem = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Mensagem = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Mensagem = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 73) {
							row1.SaldoTitulo = null;
						} else {

							row1.SaldoTitulo = rs_tDBInput_1.getDouble(73);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoTitulo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 74) {
							row1.RecolheISS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(74);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(74).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheISS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheISS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheISS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 75) {
							row1.SaldoPedidoLiberado = null;
						} else {

							row1.SaldoPedidoLiberado = rs_tDBInput_1.getDouble(75);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoPedidoLiberado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 76) {
							row1.NumeroPagamentos = null;
						} else {

							row1.NumeroPagamentos = rs_tDBInput_1.getDouble(76);
							if (rs_tDBInput_1.wasNull()) {
								row1.NumeroPagamentos = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 77) {
							row1.Transferencia = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(77);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(77).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Transferencia = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Transferencia = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Transferencia = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 78) {
							row1.SUFRAMA = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(78);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(78).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SUFRAMA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SUFRAMA = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SUFRAMA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 79) {
							row1.ATR = null;
						} else {

							row1.ATR = rs_tDBInput_1.getDouble(79);
							if (rs_tDBInput_1.wasNull()) {
								row1.ATR = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 80) {
							row1.ValorAcumulado = null;
						} else {

							row1.ValorAcumulado = rs_tDBInput_1.getDouble(80);
							if (rs_tDBInput_1.wasNull()) {
								row1.ValorAcumulado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 81) {
							row1.SaldoPedido = null;
						} else {

							row1.SaldoPedido = rs_tDBInput_1.getDouble(81);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoPedido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 82) {
							row1.TituloProtestado = null;
						} else {

							row1.TituloProtestado = rs_tDBInput_1.getDouble(82);
							if (rs_tDBInput_1.wasNull()) {
								row1.TituloProtestado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 83) {
							row1.DataUltimoTitulo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(83);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(83).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataUltimoTitulo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataUltimoTitulo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataUltimoTitulo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 84) {
							row1.ChequeDevolvido = null;
						} else {

							row1.ChequeDevolvido = rs_tDBInput_1.getDouble(84);
							if (rs_tDBInput_1.wasNull()) {
								row1.ChequeDevolvido = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 85) {
							row1.CaixaPostal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(85);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(85).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CaixaPostal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CaixaPostal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CaixaPostal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 86) {
							row1.Matriz = null;
						} else {

							row1.Matriz = rs_tDBInput_1.getDouble(86);
							if (rs_tDBInput_1.wasNull()) {
								row1.Matriz = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 87) {
							row1.DataUltimoCheque = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(87);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(87).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataUltimoCheque = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataUltimoCheque = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataUltimoCheque = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 88) {
							row1.MaiorDuplicata = null;
						} else {

							row1.MaiorDuplicata = rs_tDBInput_1.getDouble(88);
							if (rs_tDBInput_1.wasNull()) {
								row1.MaiorDuplicata = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 89) {
							row1.Tabela = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(89);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(89).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Tabela = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Tabela = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Tabela = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 90) {
							row1.ISSIncluso = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(90);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(90).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ISSIncluso = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ISSIncluso = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ISSIncluso = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 91) {
							row1.SaldoDuplicataMoeda = null;
						} else {

							row1.SaldoDuplicataMoeda = rs_tDBInput_1.getDouble(91);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoDuplicataMoeda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 92) {
							row1.PagamentoAtrasado = null;
						} else {

							row1.PagamentoAtrasado = rs_tDBInput_1.getDouble(92);
							if (rs_tDBInput_1.wasNull()) {
								row1.PagamentoAtrasado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 93) {
							row1.Atividade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(93);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(93).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Atividade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Atividade = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Atividade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 94) {
							row1.Cargo1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(94);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(94).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Cargo1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Cargo1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Cargo1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 95) {
							row1.Cargo2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(95);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(95).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Cargo2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Cargo2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Cargo2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 96) {
							row1.Cargo3 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(96);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(96).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Cargo3 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Cargo3 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Cargo3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 97) {
							row1.RepresentanteTecnico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(97);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(97).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RepresentanteTecnico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RepresentanteTecnico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RepresentanteTecnico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 98) {
							row1.Supervisor = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(98);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(98).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Supervisor = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Supervisor = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Supervisor = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 99) {
							row1.AliquotaIR = null;
						} else {

							row1.AliquotaIR = rs_tDBInput_1.getDouble(99);
							if (rs_tDBInput_1.wasNull()) {
								row1.AliquotaIR = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 100) {
							row1.Observacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(100);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(100).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Observacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Observacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Observacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 101) {
							row1.RG = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(101);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(101).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RG = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RG = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RG = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 102) {
							row1.CalculaSUFRAMA = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(102);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(102).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CalculaSUFRAMA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CalculaSUFRAMA = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CalculaSUFRAMA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 103) {
							row1.DataNascimento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(103);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(103).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataNascimento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataNascimento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataNascimento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 104) {
							row1.SaldoPedidoBloqueado = null;
						} else {

							row1.SaldoPedidoBloqueado = rs_tDBInput_1.getDouble(104);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoPedidoBloqueado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 105) {
							row1.GrupoTributario = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(105);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(105).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.GrupoTributario = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.GrupoTributario = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.GrupoTributario = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 106) {
							row1.SegmentoAtividade1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(106);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(106).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 107) {
							row1.ClienteFaturamento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(107);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(107).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ClienteFaturamento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ClienteFaturamento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ClienteFaturamento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 108) {
							row1.EnderecoEntrega = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(108);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(108).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EnderecoEntrega = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EnderecoEntrega = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EnderecoEntrega = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 109) {
							row1.UCompleto = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(109);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(109).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.UCompleto = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.UCompleto = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.UCompleto = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 110) {
							row1.BairroEntrega = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(110);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(110).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.BairroEntrega = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.BairroEntrega = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.BairroEntrega = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 111) {
							row1.CEPEntrada = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(111);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(111).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CEPEntrada = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CEPEntrada = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CEPEntrada = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 112) {
							row1.EstadoEntrada = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(112);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(112).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.EstadoEntrada = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.EstadoEntrada = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.EstadoEntrada = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 113) {
							row1.CodigoLocal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(113);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(113).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoLocal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoLocal = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoLocal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 114) {
							row1.SegmentoAtividade2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(114);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(114).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 115) {
							row1.TipoPessoa = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(115);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(115).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoPessoa = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoPessoa = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoPessoa = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 116) {
							row1.TipoISSRS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(116);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(116).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoISSRS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoISSRS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoISSRS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 117) {
							row1.SegmentoAtividade3 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(117);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(117).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade3 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade3 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 118) {
							row1.SegmentoAtividade4 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(118);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(118).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade4 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade4 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade4 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 119) {
							row1.SegmentoAtividade5 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(119);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(119).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade5 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade5 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade5 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 120) {
							row1.SegmentoAtividade6 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(120);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(120).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade6 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade6 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade6 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 121) {
							row1.SegmentoAtividade7 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(121);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(121).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade7 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade7 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade7 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 122) {
							row1.CodigoAgente = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(122);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(122).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoAgente = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoAgente = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoAgente = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 123) {
							row1.SegmentoAtividade8 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(123);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(123).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade8 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade8 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade8 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 124) {
							row1.CodigoMarcacao = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(124);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(124).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoMarcacao = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoMarcacao = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoMarcacao = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 125) {
							row1.ComissaoAgente = null;
						} else {

							row1.ComissaoAgente = rs_tDBInput_1.getDouble(125);
							if (rs_tDBInput_1.wasNull()) {
								row1.ComissaoAgente = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 126) {
							row1.HomePage = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(126);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(126).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.HomePage = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.HomePage = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.HomePage = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 127) {
							row1.CodigoMunicipio1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(127);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(127).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoMunicipio1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoMunicipio1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoMunicipio1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 128) {
							row1.TipoCliente = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(128);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(128).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoCliente = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoCliente = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoCliente = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 129) {
							row1.Email = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(129);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(129).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Email = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Email = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Email = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 130) {
							row1.Destino1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(130);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(130).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Destino1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Destino1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Destino1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 131) {
							row1.Destino2 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(131);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(131).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Destino2 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Destino2 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Destino2 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 132) {
							row1.Destino3 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(132);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(132).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Destino3 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Destino3 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Destino3 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 133) {
							row1.CBO = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(133);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(133).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CBO = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CBO = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CBO = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 134) {
							row1.Observacao1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(134);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(134).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Observacao1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Observacao1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Observacao1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 135) {
							row1.CNAE = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(135);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(135).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CNAE = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CNAE = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CNAE = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 136) {
							row1.CodigoHistorico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(136);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(136).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoHistorico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoHistorico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CodigoHistorico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 137) {
							row1.CondicaoPagamento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(137);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(137).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CondicaoPagamento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CondicaoPagamento = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CondicaoPagamento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 138) {
							row1.DiasPagamento = null;
						} else {

							row1.DiasPagamento = rs_tDBInput_1.getDouble(138);
							if (rs_tDBInput_1.wasNull()) {
								row1.DiasPagamento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 139) {
							row1.Agregado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(139);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(139).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Agregado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Agregado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Agregado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 140) {
							row1.RecolheINSS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(140);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(140).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheINSS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheINSS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheINSS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 141) {
							row1.RecolheCOFINS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(141);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(141).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheCOFINS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheCOFINS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheCOFINS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 142) {
							row1.RecolheCSLL = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(142);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(142).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheCSLL = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheCSLL = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheCSLL = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 143) {
							row1.RecolhePIS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(143);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(143).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolhePIS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolhePIS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolhePIS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 144) {
							row1.TipoPeriodo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(144);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(144).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoPeriodo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoPeriodo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoPeriodo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 145) {
							row1.SaldoFinal = null;
						} else {

							row1.SaldoFinal = rs_tDBInput_1.getDouble(145);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoFinal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 146) {
							row1.SaldoFinalMoeda = null;
						} else {

							row1.SaldoFinalMoeda = rs_tDBInput_1.getDouble(146);
							if (rs_tDBInput_1.wasNull()) {
								row1.SaldoFinalMoeda = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 147) {
							row1.Contabilidade = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(147);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(147).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Contabilidade = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Contabilidade = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Contabilidade = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 148) {
							row1.ClienteConvenio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(148);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(148).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ClienteConvenio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ClienteConvenio = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ClienteConvenio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 149) {
							row1.B2B = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(149);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(149).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.B2B = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.B2B = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.B2B = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 150) {
							row1.CBO1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(150);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(150).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CBO1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CBO1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CBO1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 151) {
							row1.CNAE1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(151);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(151).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CNAE1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CNAE1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CNAE1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 152) {
							row1.SegmentoAtividade11 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(152);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(152).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SegmentoAtividade11 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SegmentoAtividade11 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SegmentoAtividade11 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 153) {
							row1.MensagemBloqueio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(153);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(153).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.MensagemBloqueio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.MensagemBloqueio = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.MensagemBloqueio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 154) {
							row1.SubCodigo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(154);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(154).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SubCodigo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SubCodigo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SubCodigo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 155) {
							row1.FilialDebito = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(155);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(155).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FilialDebito = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FilialDebito = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FilialDebito = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 156) {
							row1.RecolhePIS1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(156);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(156).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolhePIS1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolhePIS1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolhePIS1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 157) {
							row1.RecolheCOFINS1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(157);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(157).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheCOFINS1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheCOFINS1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheCOFINS1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 158) {
							row1.RecolheCSLL1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(158);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(158).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheCSLL1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheCSLL1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheCSLL1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 159) {
							row1.ABICS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(159);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(159).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ABICS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ABICS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ABICS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 160) {
							row1.Vinculo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(160);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(160).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Vinculo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Vinculo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Vinculo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 161) {
							row1.DataInicioVinculo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(161);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(161).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataInicioVinculo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataInicioVinculo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataInicioVinculo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 162) {
							row1.DataFimVinculo = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(162);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(162).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataFimVinculo = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataFimVinculo = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataFimVinculo = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 163) {
							row1.ISSRSLC = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(163);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(163).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ISSRSLC = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ISSRSLC = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ISSRSLC = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 164) {
							row1.TipoRegistro = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(164);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(164).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoRegistro = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoRegistro = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoRegistro = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 165) {
							row1.RFASEMT = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(165);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(165).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RFASEMT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RFASEMT = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RFASEMT = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 166) {
							row1.RIMAMT = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(166);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(166).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RIMAMT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RIMAMT = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RIMAMT = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 167) {
							row1.REGESIM = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(167);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(167).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.REGESIM = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.REGESIM = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.REGESIM = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 168) {
							row1.ContaReceita = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(168);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(168).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ContaReceita = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ContaReceita = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ContaReceita = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 169) {
							row1.SIMPNacional = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(169);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(169).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SIMPNacional = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.SIMPNacional = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.SIMPNacional = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 170) {
							row1.RecolheFETHAB = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(170);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(170).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheFETHAB = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheFETHAB = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheFETHAB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 171) {
							row1.RFABOV = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(171);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(171).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RFABOV = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RFABOV = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RFABOV = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 172) {
							row1.RFACS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(172);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(172).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RFACS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RFACS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RFACS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 173) {
							row1.DataNascimento1 = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(173);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(173).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.DataNascimento1 = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.DataNascimento1 = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.DataNascimento1 = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 174) {
							row1.Contribuinte = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(174);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(174).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Contribuinte = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Contribuinte = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Contribuinte = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 175) {
							row1.RecolheFMD = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(175);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(175).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.RecolheFMD = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.RecolheFMD = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.RecolheFMD = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 176) {
							row1.TipoJuridico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(176);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(176).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TipoJuridico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TipoJuridico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TipoJuridico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 177) {
							row1.INCULT = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(177);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(177).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.INCULT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.INCULT = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.INCULT = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 178) {
							row1.IDHistorico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(178);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(178).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.IDHistorico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.IDHistorico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.IDHistorico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 179) {
							row1.PrestacaoServico = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(179);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(179).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PrestacaoServico = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PrestacaoServico = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.PrestacaoServico = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 180) {
							row1.NUMRA = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(180);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(180).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.NUMRA = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.NUMRA = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.NUMRA = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 181) {
							row1.MINIRF = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(181);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(181).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.MINIRF = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.MINIRF = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.MINIRF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 182) {
							row1.CODSIAF = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(182);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(182).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CODSIAF = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CODSIAF = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.CODSIAF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 183) {
							row1.ENDNOT = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(183);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(183).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.ENDNOT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.ENDNOT = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.ENDNOT = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 184) {
							row1.FOMEZER = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(184);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(184).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FOMEZER = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FOMEZER = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FOMEZER = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 185) {
							row1.FRETISS = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(185);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(185).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FRETISS = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FRETISS = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FRETISS = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 186) {
							row1.COMPLEM = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(186);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(186).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.COMPLEM = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.COMPLEM = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.COMPLEM = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 187) {
							row1.INCLTMG = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(187);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(187).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.INCLTMG = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.INCLTMG = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.INCLTMG = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 188) {
							row1.FILTRF = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(188);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(188).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.FILTRF = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.FILTRF = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.FILTRF = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 189) {
							row1.TRIBFAV = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(189);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(189).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TRIBFAV = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.TRIBFAV = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.TRIBFAV = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 190) {
							row1.REGPB = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(190);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(190).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.REGPB = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.REGPB = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.REGPB = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 191) {
							row1.INOVAUT = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(191);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(191).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.INOVAUT = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.INOVAUT = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.INOVAUT = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 192) {
							row1.PrazoMedio = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(192);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(192).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PrazoMedio = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.PrazoMedio = tmpContent_tDBInput_1;
								}
							} else {
								row1.PrazoMedio = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 193) {
							row1.CodigoCanal = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(193);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(193).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoCanal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoCanal = tmpContent_tDBInput_1;
								}
							} else {
								row1.CodigoCanal = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 194) {
							row1.CodigoSegmento = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(194);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(194).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CodigoSegmento = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.CodigoSegmento = tmpContent_tDBInput_1;
								}
							} else {
								row1.CodigoSegmento = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 195) {
							row1.Deletado = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(195);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(195).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Deletado = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Deletado = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Deletado = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 196) {
							row1.Ukey = null;
						} else {

							tmpContent_tDBInput_1 = rs_tDBInput_1.getString(196);
							if (tmpContent_tDBInput_1 != null) {
								if (talendToDBList_tDBInput_1.contains(
										rsmd_tDBInput_1.getColumnTypeName(196).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Ukey = FormatterUtils.formatUnwithE(tmpContent_tDBInput_1);
								} else {
									row1.Ukey = tmpContent_tDBInput_1.trim();
								}
							} else {
								row1.Ukey = null;
							}
						}
						if (colQtyInRs_tDBInput_1 < 197) {
							row1.RecDeletado = null;
						} else {

							row1.RecDeletado = rs_tDBInput_1.getInt(197);
							if (rs_tDBInput_1.wasNull()) {
								row1.RecDeletado = null;
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

						cLabel = "InputClientes";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "InputClientes";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row1", "tDBInput_1", "InputClientes", "tMSSqlInput", "tMap_1", "tMap_1", "tMap"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
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

							Ajuste_data = null;

// # Output table : 'Ajuste_data'
							count_Ajuste_data_tMap_1++;

							Ajuste_data_tmp.Filial = row1.Filial;
							Ajuste_data_tmp.Codigo = row1.Codigo;
							Ajuste_data_tmp.Loja = row1.Loja;
							Ajuste_data_tmp.Tipo = row1.Tipo;
							Ajuste_data_tmp.Pessoa = row1.Pessoa;
							Ajuste_data_tmp.Natureza = row1.Natureza;
							Ajuste_data_tmp.CNPJ = row1.CNPJ;
							Ajuste_data_tmp.Nome = row1.Nome;
							Ajuste_data_tmp.NomeReduzido = row1.NomeReduzido;
							Ajuste_data_tmp.Endereco = row1.Endereco;
							Ajuste_data_tmp.Complemento = row1.Complemento;
							Ajuste_data_tmp.Bairro = row1.Bairro;
							Ajuste_data_tmp.CEP = row1.CEP;
							Ajuste_data_tmp.Estado = row1.Estado;
							Ajuste_data_tmp.Regiao = row1.Regiao;
							Ajuste_data_tmp.DescricaoRegiao = row1.DescricaoRegiao;
							Ajuste_data_tmp.CodigoMunicipio = row1.CodigoMunicipio;
							Ajuste_data_tmp.Municipio = row1.Municipio;
							Ajuste_data_tmp.CodigoPais = row1.CodigoPais;
							Ajuste_data_tmp.NomeEstado = row1.NomeEstado;
							Ajuste_data_tmp.DDD = row1.DDD;
							Ajuste_data_tmp.TributacaoFavoravel = row1.TributacaoFavoravel;
							Ajuste_data_tmp.DDI = row1.DDI;
							Ajuste_data_tmp.CodIBGE = row1.CodIBGE;
							Ajuste_data_tmp.UCodigoMunicipio = row1.UCodigoMunicipio;
							Ajuste_data_tmp.EnderecoRecebimento = row1.EnderecoRecebimento;
							Ajuste_data_tmp.Telefone = row1.Telefone;
							Ajuste_data_tmp.FAX = row1.FAX;
							Ajuste_data_tmp.FoneCP = row1.FoneCP;
							Ajuste_data_tmp.Contato = row1.Contato;
							Ajuste_data_tmp.PessoaFisica = row1.PessoaFisica;
							Ajuste_data_tmp.Pais = row1.Pais;
							Ajuste_data_tmp.Inscricao = row1.Inscricao;
							Ajuste_data_tmp.InscricaoMunicipal = row1.InscricaoMunicipal;
							Ajuste_data_tmp.Vendedor = row1.Vendedor;
							Ajuste_data_tmp.Comissao = row1.Comissao;
							Ajuste_data_tmp.Conta = row1.Conta;
							Ajuste_data_tmp.Banco1 = row1.Banco1;
							Ajuste_data_tmp.Banco2 = row1.Banco2;
							Ajuste_data_tmp.Banco3 = row1.Banco3;
							Ajuste_data_tmp.Banco4 = row1.Banco4;
							Ajuste_data_tmp.Banco5 = row1.Banco5;
							Ajuste_data_tmp.Transporte = row1.Transporte;
							Ajuste_data_tmp.TipoFrete = row1.TipoFrete;
							Ajuste_data_tmp.EnderecoCobranca = row1.EnderecoCobranca;
							Ajuste_data_tmp.Condicao = row1.Condicao;
							Ajuste_data_tmp.BairroCobranca = row1.BairroCobranca;
							Ajuste_data_tmp.Descricao = row1.Descricao;
							Ajuste_data_tmp.CEPCobranca = row1.CEPCobranca;
							Ajuste_data_tmp.EstadoCobranca = row1.EstadoCobranca;
							Ajuste_data_tmp.UCodigoMunicipioCobranca = row1.UCodigoMunicipioCobranca;
							Ajuste_data_tmp.MunicipioCobranca = row1.MunicipioCobranca;
							Ajuste_data_tmp.Prioridade = row1.Prioridade;
							Ajuste_data_tmp.Risco = row1.Risco;
							Ajuste_data_tmp.LimiteCredito = row1.LimiteCredito;
							Ajuste_data_tmp.VencimentoLimiteCredito = row1.VencimentoLimiteCredito.trim().isEmpty()
									? TalendDate.parseDate("yyyyMMdd", "19000101")
									: TalendDate.parseDate("yyyyMMdd", row1.VencimentoLimiteCredito);
							Ajuste_data_tmp.Classe = row1.Classe;
							Ajuste_data_tmp.LimiteCreditoFinal = row1.LimiteCreditoFinal;
							Ajuste_data_tmp.MoedaLimiteCredito = row1.MoedaLimiteCredito;
							Ajuste_data_tmp.MaiorSaldo = row1.MaiorSaldo;
							Ajuste_data_tmp.MaiorCompra = row1.MaiorCompra;
							Ajuste_data_tmp.MediaAtraso = row1.MediaAtraso;
							Ajuste_data_tmp.PrimeiraCompra = row1.PrimeiraCompra.trim().isEmpty()
									? TalendDate.parseDate("yyyyMMdd", "19000101")
									: TalendDate.parseDate("yyyyMMdd", row1.PrimeiraCompra);
							Ajuste_data_tmp.UltimaCompra = row1.UltimaCompra.trim().isEmpty()
									? TalendDate.parseDate("yyyyMMdd", "19000101")
									: TalendDate.parseDate("yyyyMMdd", row1.UltimaCompra);
							Ajuste_data_tmp.NumeroCompras = row1.NumeroCompras;
							Ajuste_data_tmp.FormularioVisita = row1.FormularioVisita;
							Ajuste_data_tmp.TempoVisita = row1.TempoVisita;
							Ajuste_data_tmp.UltimaVisita = row1.UltimaVisita;
							Ajuste_data_tmp.TempoVisita1 = row1.TempoVisita1;
							Ajuste_data_tmp.TempoPadrao = row1.TempoPadrao;
							Ajuste_data_tmp.ClassificacaoVenda = row1.ClassificacaoVenda;
							Ajuste_data_tmp.Mensagem = row1.Mensagem;
							Ajuste_data_tmp.SaldoTitulo = row1.SaldoTitulo;
							Ajuste_data_tmp.RecolheISS = row1.RecolheISS;
							Ajuste_data_tmp.SaldoPedidoLiberado = row1.SaldoPedidoLiberado;
							Ajuste_data_tmp.NumeroPagamentos = row1.NumeroPagamentos;
							Ajuste_data_tmp.Transferencia = row1.Transferencia;
							Ajuste_data_tmp.SUFRAMA = row1.SUFRAMA;
							Ajuste_data_tmp.ATR = row1.ATR;
							Ajuste_data_tmp.ValorAcumulado = row1.ValorAcumulado;
							Ajuste_data_tmp.SaldoPedido = row1.SaldoPedido;
							Ajuste_data_tmp.TituloProtestado = row1.TituloProtestado;
							Ajuste_data_tmp.DataUltimoTitulo = row1.DataUltimoTitulo.trim().isEmpty()
									? TalendDate.parseDate("yyyyMMdd", "19000101")
									: TalendDate.parseDate("yyyyMMdd", row1.DataUltimoTitulo);
							Ajuste_data_tmp.ChequeDevolvido = row1.ChequeDevolvido;
							Ajuste_data_tmp.CaixaPostal = row1.CaixaPostal;
							Ajuste_data_tmp.Matriz = row1.Matriz;
							Ajuste_data_tmp.DataUltimoCheque = row1.DataUltimoCheque;
							Ajuste_data_tmp.MaiorDuplicata = row1.MaiorDuplicata;
							Ajuste_data_tmp.Tabela = row1.Tabela;
							Ajuste_data_tmp.ISSIncluso = row1.ISSIncluso;
							Ajuste_data_tmp.SaldoDuplicataMoeda = row1.SaldoDuplicataMoeda;
							Ajuste_data_tmp.PagamentoAtrasado = row1.PagamentoAtrasado;
							Ajuste_data_tmp.Atividade = row1.Atividade;
							Ajuste_data_tmp.Cargo1 = row1.Cargo1;
							Ajuste_data_tmp.Cargo2 = row1.Cargo2;
							Ajuste_data_tmp.Cargo3 = row1.Cargo3;
							Ajuste_data_tmp.RepresentanteTecnico = row1.RepresentanteTecnico;
							Ajuste_data_tmp.Supervisor = row1.Supervisor;
							Ajuste_data_tmp.AliquotaIR = row1.AliquotaIR;
							Ajuste_data_tmp.Observacao = row1.Observacao;
							Ajuste_data_tmp.RG = row1.RG;
							Ajuste_data_tmp.CalculaSUFRAMA = row1.CalculaSUFRAMA;
							Ajuste_data_tmp.DataNascimento = row1.DataNascimento.trim().isEmpty()
									? TalendDate.parseDate("yyyyMMdd", "19000101")
									: TalendDate.parseDate("yyyyMMdd", row1.DataNascimento);
							Ajuste_data_tmp.SaldoPedidoBloqueado = row1.SaldoPedidoBloqueado;
							Ajuste_data_tmp.GrupoTributario = row1.GrupoTributario;
							Ajuste_data_tmp.SegmentoAtividade1 = row1.SegmentoAtividade1;
							Ajuste_data_tmp.ClienteFaturamento = row1.ClienteFaturamento;
							Ajuste_data_tmp.EnderecoEntrega = row1.EnderecoEntrega;
							Ajuste_data_tmp.UCompleto = row1.UCompleto;
							Ajuste_data_tmp.BairroEntrega = row1.BairroEntrega;
							Ajuste_data_tmp.CEPEntrada = row1.CEPEntrada;
							Ajuste_data_tmp.EstadoEntrada = row1.EstadoEntrada;
							Ajuste_data_tmp.CodigoLocal = row1.CodigoLocal;
							Ajuste_data_tmp.SegmentoAtividade2 = row1.SegmentoAtividade2;
							Ajuste_data_tmp.TipoPessoa = row1.TipoPessoa;
							Ajuste_data_tmp.TipoISSRS = row1.TipoISSRS;
							Ajuste_data_tmp.SegmentoAtividade3 = row1.SegmentoAtividade3;
							Ajuste_data_tmp.SegmentoAtividade4 = row1.SegmentoAtividade4;
							Ajuste_data_tmp.SegmentoAtividade5 = row1.SegmentoAtividade5;
							Ajuste_data_tmp.SegmentoAtividade6 = row1.SegmentoAtividade6;
							Ajuste_data_tmp.SegmentoAtividade7 = row1.SegmentoAtividade7;
							Ajuste_data_tmp.CodigoAgente = row1.CodigoAgente;
							Ajuste_data_tmp.SegmentoAtividade8 = row1.SegmentoAtividade8;
							Ajuste_data_tmp.CodigoMarcacao = row1.CodigoMarcacao;
							Ajuste_data_tmp.ComissaoAgente = row1.ComissaoAgente;
							Ajuste_data_tmp.HomePage = row1.HomePage;
							Ajuste_data_tmp.CodigoMunicipio1 = row1.CodigoMunicipio1;
							Ajuste_data_tmp.TipoCliente = row1.TipoCliente;
							Ajuste_data_tmp.Email = row1.Email;
							Ajuste_data_tmp.Destino1 = row1.Destino1;
							Ajuste_data_tmp.Destino2 = row1.Destino2;
							Ajuste_data_tmp.Destino3 = row1.Destino3;
							Ajuste_data_tmp.CBO = row1.CBO;
							Ajuste_data_tmp.Observacao1 = row1.Observacao1;
							Ajuste_data_tmp.CNAE = row1.CNAE;
							Ajuste_data_tmp.CodigoHistorico = row1.CodigoHistorico;
							Ajuste_data_tmp.CondicaoPagamento = row1.CondicaoPagamento;
							Ajuste_data_tmp.DiasPagamento = row1.DiasPagamento;
							Ajuste_data_tmp.Agregado = row1.Agregado;
							Ajuste_data_tmp.RecolheINSS = row1.RecolheINSS;
							Ajuste_data_tmp.RecolheCOFINS = row1.RecolheCOFINS;
							Ajuste_data_tmp.RecolheCSLL = row1.RecolheCSLL;
							Ajuste_data_tmp.RecolhePIS = row1.RecolhePIS;
							Ajuste_data_tmp.TipoPeriodo = row1.TipoPeriodo;
							Ajuste_data_tmp.SaldoFinal = row1.SaldoFinal;
							Ajuste_data_tmp.SaldoFinalMoeda = row1.SaldoFinalMoeda;
							Ajuste_data_tmp.Contabilidade = row1.Contabilidade;
							Ajuste_data_tmp.ClienteConvenio = row1.ClienteConvenio;
							Ajuste_data_tmp.B2B = row1.B2B;
							Ajuste_data_tmp.CBO1 = row1.CBO1;
							Ajuste_data_tmp.CNAE1 = row1.CNAE1;
							Ajuste_data_tmp.SegmentoAtividade11 = row1.SegmentoAtividade11;
							Ajuste_data_tmp.MensagemBloqueio = row1.MensagemBloqueio;
							Ajuste_data_tmp.SubCodigo = row1.SubCodigo;
							Ajuste_data_tmp.FilialDebito = row1.FilialDebito;
							Ajuste_data_tmp.RecolhePIS1 = row1.RecolhePIS1;
							Ajuste_data_tmp.RecolheCOFINS1 = row1.RecolheCOFINS1;
							Ajuste_data_tmp.RecolheCSLL1 = row1.RecolheCSLL1;
							Ajuste_data_tmp.ABICS = row1.ABICS;
							Ajuste_data_tmp.Vinculo = row1.Vinculo;
							Ajuste_data_tmp.DataInicioVinculo = row1.DataInicioVinculo;
							Ajuste_data_tmp.DataFimVinculo = row1.DataFimVinculo;
							Ajuste_data_tmp.ISSRSLC = row1.ISSRSLC;
							Ajuste_data_tmp.TipoRegistro = row1.TipoRegistro;
							Ajuste_data_tmp.RFASEMT = row1.RFASEMT;
							Ajuste_data_tmp.RIMAMT = row1.RIMAMT;
							Ajuste_data_tmp.REGESIM = row1.REGESIM;
							Ajuste_data_tmp.ContaReceita = row1.ContaReceita;
							Ajuste_data_tmp.SIMPNacional = row1.SIMPNacional;
							Ajuste_data_tmp.RecolheFETHAB = row1.RecolheFETHAB;
							Ajuste_data_tmp.RFABOV = row1.RFABOV;
							Ajuste_data_tmp.RFACS = row1.RFACS;
							Ajuste_data_tmp.DataNascimento1 = row1.DataNascimento1;
							Ajuste_data_tmp.Contribuinte = row1.Contribuinte;
							Ajuste_data_tmp.RecolheFMD = row1.RecolheFMD;
							Ajuste_data_tmp.TipoJuridico = row1.TipoJuridico;
							Ajuste_data_tmp.INCULT = row1.INCULT;
							Ajuste_data_tmp.IDHistorico = row1.IDHistorico;
							Ajuste_data_tmp.PrestacaoServico = row1.PrestacaoServico;
							Ajuste_data_tmp.NUMRA = row1.NUMRA;
							Ajuste_data_tmp.MINIRF = row1.MINIRF;
							Ajuste_data_tmp.CODSIAF = row1.CODSIAF;
							Ajuste_data_tmp.ENDNOT = row1.ENDNOT;
							Ajuste_data_tmp.FOMEZER = row1.FOMEZER;
							Ajuste_data_tmp.FRETISS = row1.FRETISS;
							Ajuste_data_tmp.COMPLEM = row1.COMPLEM;
							Ajuste_data_tmp.INCLTMG = row1.INCLTMG;
							Ajuste_data_tmp.FILTRF = row1.FILTRF;
							Ajuste_data_tmp.TRIBFAV = row1.TRIBFAV;
							Ajuste_data_tmp.REGPB = row1.REGPB;
							Ajuste_data_tmp.INOVAUT = row1.INOVAUT;
							Ajuste_data_tmp.Deletado = row1.Deletado;
							Ajuste_data_tmp.Ukey = row1.Ukey;
							Ajuste_data_tmp.RecDeletado = row1.RecDeletado;
							Ajuste_data = Ajuste_data_tmp;
							log.debug("tMap_1 - Outputting the record " + count_Ajuste_data_tMap_1
									+ " of the output table 'Ajuste_data'.");

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
// Start of branch "Ajuste_data"
						if (Ajuste_data != null) {

							/**
							 * [tReplicate_1 main ] start
							 */

							currentComponent = "tReplicate_1";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "Ajuste_data", "tMap_1", "tMap_1", "tMap", "tReplicate_1", "tReplicate_1",
									"tReplicate"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("Ajuste_data - " + (Ajuste_data == null ? "" : Ajuste_data.toLogString()));
							}

							row3 = new row3Struct();

							row3.Filial = Ajuste_data.Filial;
							row3.Codigo = Ajuste_data.Codigo;
							row3.Loja = Ajuste_data.Loja;
							row3.Tipo = Ajuste_data.Tipo;
							row3.Pessoa = Ajuste_data.Pessoa;
							row3.Natureza = Ajuste_data.Natureza;
							row3.CNPJ = Ajuste_data.CNPJ;
							row3.Nome = Ajuste_data.Nome;
							row3.NomeReduzido = Ajuste_data.NomeReduzido;
							row3.Endereco = Ajuste_data.Endereco;
							row3.Complemento = Ajuste_data.Complemento;
							row3.Bairro = Ajuste_data.Bairro;
							row3.CEP = Ajuste_data.CEP;
							row3.Estado = Ajuste_data.Estado;
							row3.Regiao = Ajuste_data.Regiao;
							row3.DescricaoRegiao = Ajuste_data.DescricaoRegiao;
							row3.CodigoMunicipio = Ajuste_data.CodigoMunicipio;
							row3.Municipio = Ajuste_data.Municipio;
							row3.CodigoPais = Ajuste_data.CodigoPais;
							row3.NomeEstado = Ajuste_data.NomeEstado;
							row3.DDD = Ajuste_data.DDD;
							row3.TributacaoFavoravel = Ajuste_data.TributacaoFavoravel;
							row3.DDI = Ajuste_data.DDI;
							row3.CodIBGE = Ajuste_data.CodIBGE;
							row3.UCodigoMunicipio = Ajuste_data.UCodigoMunicipio;
							row3.EnderecoRecebimento = Ajuste_data.EnderecoRecebimento;
							row3.Telefone = Ajuste_data.Telefone;
							row3.FAX = Ajuste_data.FAX;
							row3.FoneCP = Ajuste_data.FoneCP;
							row3.Contato = Ajuste_data.Contato;
							row3.PessoaFisica = Ajuste_data.PessoaFisica;
							row3.Pais = Ajuste_data.Pais;
							row3.Inscricao = Ajuste_data.Inscricao;
							row3.InscricaoMunicipal = Ajuste_data.InscricaoMunicipal;
							row3.Vendedor = Ajuste_data.Vendedor;
							row3.Comissao = Ajuste_data.Comissao;
							row3.Conta = Ajuste_data.Conta;
							row3.Banco1 = Ajuste_data.Banco1;
							row3.Banco2 = Ajuste_data.Banco2;
							row3.Banco3 = Ajuste_data.Banco3;
							row3.Banco4 = Ajuste_data.Banco4;
							row3.Banco5 = Ajuste_data.Banco5;
							row3.Transporte = Ajuste_data.Transporte;
							row3.TipoFrete = Ajuste_data.TipoFrete;
							row3.EnderecoCobranca = Ajuste_data.EnderecoCobranca;
							row3.Condicao = Ajuste_data.Condicao;
							row3.BairroCobranca = Ajuste_data.BairroCobranca;
							row3.Descricao = Ajuste_data.Descricao;
							row3.CEPCobranca = Ajuste_data.CEPCobranca;
							row3.EstadoCobranca = Ajuste_data.EstadoCobranca;
							row3.UCodigoMunicipioCobranca = Ajuste_data.UCodigoMunicipioCobranca;
							row3.MunicipioCobranca = Ajuste_data.MunicipioCobranca;
							row3.Prioridade = Ajuste_data.Prioridade;
							row3.Risco = Ajuste_data.Risco;
							row3.LimiteCredito = Ajuste_data.LimiteCredito;
							row3.VencimentoLimiteCredito = Ajuste_data.VencimentoLimiteCredito;
							row3.Classe = Ajuste_data.Classe;
							row3.LimiteCreditoFinal = Ajuste_data.LimiteCreditoFinal;
							row3.MoedaLimiteCredito = Ajuste_data.MoedaLimiteCredito;
							row3.MaiorSaldo = Ajuste_data.MaiorSaldo;
							row3.MaiorCompra = Ajuste_data.MaiorCompra;
							row3.MediaAtraso = Ajuste_data.MediaAtraso;
							row3.PrimeiraCompra = Ajuste_data.PrimeiraCompra;
							row3.UltimaCompra = Ajuste_data.UltimaCompra;
							row3.NumeroCompras = Ajuste_data.NumeroCompras;
							row3.FormularioVisita = Ajuste_data.FormularioVisita;
							row3.TempoVisita = Ajuste_data.TempoVisita;
							row3.UltimaVisita = Ajuste_data.UltimaVisita;
							row3.TempoVisita1 = Ajuste_data.TempoVisita1;
							row3.TempoPadrao = Ajuste_data.TempoPadrao;
							row3.ClassificacaoVenda = Ajuste_data.ClassificacaoVenda;
							row3.Mensagem = Ajuste_data.Mensagem;
							row3.SaldoTitulo = Ajuste_data.SaldoTitulo;
							row3.RecolheISS = Ajuste_data.RecolheISS;
							row3.SaldoPedidoLiberado = Ajuste_data.SaldoPedidoLiberado;
							row3.NumeroPagamentos = Ajuste_data.NumeroPagamentos;
							row3.Transferencia = Ajuste_data.Transferencia;
							row3.SUFRAMA = Ajuste_data.SUFRAMA;
							row3.ATR = Ajuste_data.ATR;
							row3.ValorAcumulado = Ajuste_data.ValorAcumulado;
							row3.SaldoPedido = Ajuste_data.SaldoPedido;
							row3.TituloProtestado = Ajuste_data.TituloProtestado;
							row3.DataUltimoTitulo = Ajuste_data.DataUltimoTitulo;
							row3.ChequeDevolvido = Ajuste_data.ChequeDevolvido;
							row3.CaixaPostal = Ajuste_data.CaixaPostal;
							row3.Matriz = Ajuste_data.Matriz;
							row3.DataUltimoCheque = Ajuste_data.DataUltimoCheque;
							row3.MaiorDuplicata = Ajuste_data.MaiorDuplicata;
							row3.Tabela = Ajuste_data.Tabela;
							row3.ISSIncluso = Ajuste_data.ISSIncluso;
							row3.SaldoDuplicataMoeda = Ajuste_data.SaldoDuplicataMoeda;
							row3.PagamentoAtrasado = Ajuste_data.PagamentoAtrasado;
							row3.Atividade = Ajuste_data.Atividade;
							row3.Cargo1 = Ajuste_data.Cargo1;
							row3.Cargo2 = Ajuste_data.Cargo2;
							row3.Cargo3 = Ajuste_data.Cargo3;
							row3.RepresentanteTecnico = Ajuste_data.RepresentanteTecnico;
							row3.Supervisor = Ajuste_data.Supervisor;
							row3.AliquotaIR = Ajuste_data.AliquotaIR;
							row3.Observacao = Ajuste_data.Observacao;
							row3.RG = Ajuste_data.RG;
							row3.CalculaSUFRAMA = Ajuste_data.CalculaSUFRAMA;
							row3.DataNascimento = Ajuste_data.DataNascimento;
							row3.SaldoPedidoBloqueado = Ajuste_data.SaldoPedidoBloqueado;
							row3.GrupoTributario = Ajuste_data.GrupoTributario;
							row3.SegmentoAtividade1 = Ajuste_data.SegmentoAtividade1;
							row3.ClienteFaturamento = Ajuste_data.ClienteFaturamento;
							row3.EnderecoEntrega = Ajuste_data.EnderecoEntrega;
							row3.UCompleto = Ajuste_data.UCompleto;
							row3.BairroEntrega = Ajuste_data.BairroEntrega;
							row3.CEPEntrada = Ajuste_data.CEPEntrada;
							row3.EstadoEntrada = Ajuste_data.EstadoEntrada;
							row3.CodigoLocal = Ajuste_data.CodigoLocal;
							row3.SegmentoAtividade2 = Ajuste_data.SegmentoAtividade2;
							row3.TipoPessoa = Ajuste_data.TipoPessoa;
							row3.TipoISSRS = Ajuste_data.TipoISSRS;
							row3.SegmentoAtividade3 = Ajuste_data.SegmentoAtividade3;
							row3.SegmentoAtividade4 = Ajuste_data.SegmentoAtividade4;
							row3.SegmentoAtividade5 = Ajuste_data.SegmentoAtividade5;
							row3.SegmentoAtividade6 = Ajuste_data.SegmentoAtividade6;
							row3.SegmentoAtividade7 = Ajuste_data.SegmentoAtividade7;
							row3.CodigoAgente = Ajuste_data.CodigoAgente;
							row3.SegmentoAtividade8 = Ajuste_data.SegmentoAtividade8;
							row3.CodigoMarcacao = Ajuste_data.CodigoMarcacao;
							row3.ComissaoAgente = Ajuste_data.ComissaoAgente;
							row3.HomePage = Ajuste_data.HomePage;
							row3.CodigoMunicipio1 = Ajuste_data.CodigoMunicipio1;
							row3.TipoCliente = Ajuste_data.TipoCliente;
							row3.Email = Ajuste_data.Email;
							row3.Destino1 = Ajuste_data.Destino1;
							row3.Destino2 = Ajuste_data.Destino2;
							row3.Destino3 = Ajuste_data.Destino3;
							row3.CBO = Ajuste_data.CBO;
							row3.Observacao1 = Ajuste_data.Observacao1;
							row3.CNAE = Ajuste_data.CNAE;
							row3.CodigoHistorico = Ajuste_data.CodigoHistorico;
							row3.CondicaoPagamento = Ajuste_data.CondicaoPagamento;
							row3.DiasPagamento = Ajuste_data.DiasPagamento;
							row3.Agregado = Ajuste_data.Agregado;
							row3.RecolheINSS = Ajuste_data.RecolheINSS;
							row3.RecolheCOFINS = Ajuste_data.RecolheCOFINS;
							row3.RecolheCSLL = Ajuste_data.RecolheCSLL;
							row3.RecolhePIS = Ajuste_data.RecolhePIS;
							row3.TipoPeriodo = Ajuste_data.TipoPeriodo;
							row3.SaldoFinal = Ajuste_data.SaldoFinal;
							row3.SaldoFinalMoeda = Ajuste_data.SaldoFinalMoeda;
							row3.Contabilidade = Ajuste_data.Contabilidade;
							row3.ClienteConvenio = Ajuste_data.ClienteConvenio;
							row3.B2B = Ajuste_data.B2B;
							row3.CBO1 = Ajuste_data.CBO1;
							row3.CNAE1 = Ajuste_data.CNAE1;
							row3.SegmentoAtividade11 = Ajuste_data.SegmentoAtividade11;
							row3.MensagemBloqueio = Ajuste_data.MensagemBloqueio;
							row3.SubCodigo = Ajuste_data.SubCodigo;
							row3.FilialDebito = Ajuste_data.FilialDebito;
							row3.RecolhePIS1 = Ajuste_data.RecolhePIS1;
							row3.RecolheCOFINS1 = Ajuste_data.RecolheCOFINS1;
							row3.RecolheCSLL1 = Ajuste_data.RecolheCSLL1;
							row3.ABICS = Ajuste_data.ABICS;
							row3.Vinculo = Ajuste_data.Vinculo;
							row3.DataInicioVinculo = Ajuste_data.DataInicioVinculo;
							row3.DataFimVinculo = Ajuste_data.DataFimVinculo;
							row3.ISSRSLC = Ajuste_data.ISSRSLC;
							row3.TipoRegistro = Ajuste_data.TipoRegistro;
							row3.RFASEMT = Ajuste_data.RFASEMT;
							row3.RIMAMT = Ajuste_data.RIMAMT;
							row3.REGESIM = Ajuste_data.REGESIM;
							row3.ContaReceita = Ajuste_data.ContaReceita;
							row3.SIMPNacional = Ajuste_data.SIMPNacional;
							row3.RecolheFETHAB = Ajuste_data.RecolheFETHAB;
							row3.RFABOV = Ajuste_data.RFABOV;
							row3.RFACS = Ajuste_data.RFACS;
							row3.DataNascimento1 = Ajuste_data.DataNascimento1;
							row3.Contribuinte = Ajuste_data.Contribuinte;
							row3.RecolheFMD = Ajuste_data.RecolheFMD;
							row3.TipoJuridico = Ajuste_data.TipoJuridico;
							row3.INCULT = Ajuste_data.INCULT;
							row3.IDHistorico = Ajuste_data.IDHistorico;
							row3.PrestacaoServico = Ajuste_data.PrestacaoServico;
							row3.NUMRA = Ajuste_data.NUMRA;
							row3.MINIRF = Ajuste_data.MINIRF;
							row3.CODSIAF = Ajuste_data.CODSIAF;
							row3.ENDNOT = Ajuste_data.ENDNOT;
							row3.FOMEZER = Ajuste_data.FOMEZER;
							row3.FRETISS = Ajuste_data.FRETISS;
							row3.COMPLEM = Ajuste_data.COMPLEM;
							row3.INCLTMG = Ajuste_data.INCLTMG;
							row3.FILTRF = Ajuste_data.FILTRF;
							row3.TRIBFAV = Ajuste_data.TRIBFAV;
							row3.REGPB = Ajuste_data.REGPB;
							row3.INOVAUT = Ajuste_data.INOVAUT;
							row3.Deletado = Ajuste_data.Deletado;
							row3.Ukey = Ajuste_data.Ukey;
							row3.RecDeletado = Ajuste_data.RecDeletado;

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

									, "row3", "tReplicate_1", "tReplicate_1", "tReplicate", "tFileOutputDelimited_1",
									"tFileOutputDelimited_1", "tFileOutputDelimited"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
							}

							StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
							fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_0(row3, sb_tFileOutputDelimited_1,
									OUT_DELIM_tFileOutputDelimited_1);
							fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_1(row3, sb_tFileOutputDelimited_1,
									OUT_DELIM_tFileOutputDelimited_1);
							fileOutputDelimitedUtil_tFileOutputDelimited_1.putValue_2(row3, sb_tFileOutputDelimited_1,
									OUT_DELIM_tFileOutputDelimited_1);
							sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

							nb_line_tFileOutputDelimited_1++;
							resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

							outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());
							log.debug("tFileOutputDelimited_1 - Writing the record " + nb_line_tFileOutputDelimited_1
									+ ".");

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

						} // End of branch "Ajuste_data"

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "InputClientes";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

						cLabel = "InputClientes";

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
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug(
						"tMap_1 - Written records count in the table 'Ajuste_data': " + count_Ajuste_data_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tDBInput_1", "InputClientes", "tMSSqlInput", "tMap_1", "tMap_1", "tMap", "output")) {
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
				 * [tReplicate_1 end ] start
				 */

				currentComponent = "tReplicate_1";

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "Ajuste_data", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tReplicate_1", "tReplicate_1", "tReplicate", "output")) {
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
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

				cLabel = "InputClientes";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
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
		final Clientes ClientesClass = new Clientes();

		int exitCode = ClientesClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Clientes' - Done.");
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
		log.info("TalendJob: 'Clientes' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_se7FUEVFEe-WLtL1mGd32Q");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2024-08-02T14:09:15.562011Z");

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
			java.io.InputStream inContext = Clientes.class.getClassLoader()
					.getResourceAsStream("hydronorth/clientes_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Clientes.class.getClassLoader()
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
		log.info("TalendJob: 'Clientes' - Started.");
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
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Clientes");
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
		log.info("TalendJob: 'Clientes' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 1170225 characters generated by Talend Cloud Data Integration on the 2 de
 * agosto de 2024 11:09:15 BRT
 ************************************************************************************************/